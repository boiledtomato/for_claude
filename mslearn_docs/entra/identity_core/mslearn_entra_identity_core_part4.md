# Microsoft Learn — Microsoft Entra / ユーザー・デバイス・ロール・マネージド ID (part 4)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 13

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/delegate-by-task"} -->
## タスク別の最小特権ロール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/delegate-by-task
- Service: entra-id / role-based-access-control
- Article date: 2026-06-17
- Summary: Microsoft Entra ID のタスクに委任する最小特権ロール

この記事では、Microsoft Entra ID のいくつかのタスクに使用する必要がある最小限の特権ロールについて説明します。 機能領域ごとのタスクと、各タスクを実行するために必要な最小特権ロールのほか、そのタスクを実行できる非グローバル管理者ロールも別途記載しています。

より小さなスコープでロールを割り当てるか、独自のカスタム ロールを作成することで、アクセス許可をさらに制限できます。 詳細については、「[Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal) 割り当てる」または「[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)でカスタム ロールを作成する」を参照してください。

### アプリケーション プロキシの最小特権ロール

[Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| アプリケーション プロキシ アプリを構成する | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |
| コネクタ グループのプロパティを構成する | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |
| アプリケーションの登録を作成する (すべてのユーザーについて権限が無効になっている場合) | [アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| コネクタ グループを作成する | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |
| コネクタ グループを削除する | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |
| アプリケーション プロキシの無効化 | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |
| コネクタ サービスをダウンロードする | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |
| すべての構成を読み取る | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |

### 外部 ID/Azure AD B2C の最小特権ロール

[Microsoft Entra 外部 ID](https://learn.microsoft.com/ja-jp/entra/external-id/external-identities-overview) と [Azure Active Directory B2C](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/overview) でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| Azure AD B2C のディレクトリを作成する | [ゲスト以外のすべてのユーザー](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |  |
| エンタープライズ アプリケーションを作成する | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| B2C のポリシーの作成、読み取り、更新、削除を実行する | [B2C IEF ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#b2c-ief-policy-administrator) |  |
| ID プロバイダーの作成、読み取り、更新、削除を実行する | [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator) |  |
| パスワード リセット ユーザー フローの作成、読み取り、更新、削除を実行する | [外部 ID ユーザー フロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-id-user-flow-administrator) |  |
| プロファイル編集ユーザー フローの作成、読み取り、更新、削除を実行する | [外部 ID ユーザー フロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-id-user-flow-administrator) |  |
| サインイン ユーザー フローの作成、読み取り、更新、削除を実行する | [外部 ID ユーザー フロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-id-user-flow-administrator) |  |
| サインアップ ユーザー フローの作成、読み取り、更新、削除を実行する | [外部 ID ユーザー フロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-id-user-flow-administrator) |  |
| ユーザー属性の作成、読み取り、更新、削除を実行する | [外部 ID ユーザー フロー属性管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-id-user-flow-attribute-administrator) |  |
| ユーザーの作成、読み取り、更新、削除を実行する | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| [B2B 外部コラボレーションの設定を構成する - ゲスト ユーザー アクセス](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure) | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| [B2B 外部コラボレーションの設定を構成する - ゲスト招待の設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure) | [ゲスト招待元](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#guest-inviter) | [外部 ID ユーザー フロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-id-user-flow-administrator) |
| [B2B 外部コラボレーションの設定を構成する - 外部ユーザーの脱退設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure) | [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator) |  |
| [B2B 外部コラボレーションの設定を構成する - コラボレーションの制限](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure) | [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) |  |
| すべての構成を読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |
| [B2C 監査ログを読み取る](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/faq) | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |

Note

Azure AD B2C の全体管理者には、Microsoft Entra の全体管理者と同じアクセス許可はありません。 Azure AD B2C の全体管理者権限がある場合、ユーザーが Microsoft Entra ディレクトリではなく Azure AD B2C ディレクトリにいることを確認してください。

### 最小限の特権ロールをブランド化する会社

Microsoft Entra ID で [会社のブランド化](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-customize-branding) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 会社のブランドの構成 | [組織ブランド化管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#organizational-branding-administrator) |  |
| すべての構成を読み取る | [ディレクトリ 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-readers) | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |

### 最小特権ロールを接続する

[Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| パススルー認証 | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |  |
| すべての構成を読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |
| シームレス シングル サインオン | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |  |

### Sync の最小特権ロールの接続

[Microsoft Entra Connect Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| オンプレミスのディレクトリ同期を管理する | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |  |

### クラウド プロビジョニングの最小特権ロール

Microsoft Entra ID で [ID プロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/what-is-provisioning) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| パススルー認証 | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |  |
| すべての構成を読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |
| シームレス シングル サインオン | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |  |

### 正常性の最小特権ロールを接続する

[Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect) でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| [サービスを追加または削除する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations) | [Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |  |
| 同期エラーに対する修正プログラムを適用する | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor) | [Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| 通知の構成 | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor) | [Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| [設定の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations) | [Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |  |
| 同期の通知を構成する | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor) | [Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| ADFS セキュリティ レポートを読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#security-reader) | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor)[Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| すべての構成を読み取る | [Reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader) | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor)[Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| 同期エラーを読み取る | [Reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader) | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor)[Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| 同期サービスを読み取る | [Reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader) | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor)[Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| メトリックとアラートを表示する | [Reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader) | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor)[Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| メトリックとアラートを表示する | [Reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader) | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor)[Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |
| 同期サービスのメトリックとアラートを表示する | [Reader](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#reader) | [Contributor](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#contributor)[Owner](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#owner) |

### カスタム ドメイン名の最小特権ロール

Microsoft Entra ID で [カスタム ドメイン名](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ドメインの管理 | [ドメイン名管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#domain-name-administrator) |  |
| すべての構成を読み取る | [ディレクトリ 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-readers) | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |

### Domain Services の最小特権ロール

[Microsoft Entra Domain Services](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/overview) でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| Microsoft Entra Domain Services のインスタンスを作成する | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)[ドメイン サービス共同作成者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles#domain-services-contributor) |  |
| すべての Microsoft Entra Domain Services のタスクを実行する | [AAD DC 管理者グループ](https://learn.microsoft.com/ja-jp/entra/identity/domain-services/tutorial-create-management-vm#administrative-tasks-you-can-perform-on-a-managed-domain) |  |
| すべての構成を読み取る | AD DS サービスを含む Azure サブスクリプションの閲覧者 |  |

### デバイスの最小特権ロール

Microsoft Entra ID で [デバイス ID](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview) のタスクを実行するときに使用する必要がある最小特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| デバイスを削除する | [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) | [Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator) |
| デバイスを無効にする | [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) | [Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator) |
| デバイスを有効にする | [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) | [Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator) |
| 基本構成を読み取る | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |  |
| BitLocker キーを読み取る | [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) | [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)[Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |
| IoT デバイスのプロビジョニングと管理 | IoT デバイス管理者 [を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#iot-device-administrator) する | [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) |
| IoT デバイス テンプレートの管理 | IoT デバイス管理者 [を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#iot-device-administrator) する | [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) |

### エンタープライズ アプリケーションの最小特権ロール

Microsoft Entra ID で [アプリケーション管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 委任された任意のアクセス許可に同意する | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| アプリケーションのアクセス許可に同意する (Microsoft Graph を除く) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| Microsoft Graph へのアプリケーションのアクセス許可に同意する | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| アプリケーションが自分のデータにアクセスすることに同意する | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |  |
| エンタープライズ アプリケーションを作成する | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| アプリケーション プロキシを管理する | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |  |
| グループまたはアプリのアクセス レビューを読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| すべての構成を読み取る | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |  |
| エンタープライズ アプリケーションの割り当てを更新する | [エンタープライズ アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| エンタープライズ アプリケーション所有者を更新する | [エンタープライズ アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| エンタープライズ アプリケーションのプロパティを更新する | [エンタープライズ アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| エンタープライズ アプリケーションのプロビジョニングを更新する | [エンタープライズ アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| エンタープライズ アプリケーションのセルフ サービスを更新する | [エンタープライズ アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| シングル サインオンのプロパティを更新する | [エンタープライズ アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| カスタム認証拡張機能を作成して管理する | [認証拡張性の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-extensibility-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |

Note

実際には、Microsoft Graph アプリケーションのアクセス許可に同意するには、通常、全体管理者ロールが必要です。 特権ロール管理者は、テナントの同意ポリシー、アクセス許可スコープ、または Graph 保護の要件によっては不十分な場合があります。

### エンタイトルメント管理の最小特権ロール

Microsoft Entra ID ガバナンスで [エンタイトルメント管理](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-overview) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| エンタイトルメント管理のタスク | [ID ガバナンス管理者の](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)。 エンタイトルメント管理システム内のこれより低い特権のロールについては、「 [エンタイトルメント管理での委任とロール](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate)」を参照してください。 |  |

### 最小特権ロールをグループ化する

Microsoft Entra ID で [グループ](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-groups) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ライセンスを割り当てる | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| グループの作成 | [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| グループまたはアプリのアクセス レビューを作成、更新、削除する | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| グループの有効期限を管理する | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| グループ設定の管理 | [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| すべての構成を読み取る (非表示のメンバーシップを除く) | [ディレクトリ 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-readers) | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |
| 非表示のメンバーシップを読み取る | グループメンバー | [グループ所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership)[パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#password-administrator)[Exchange 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#exchange-administrator)[SharePoint 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator)[Teams 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| 非表示のメンバーシップを含むグループのメンバーシップを読み取る | [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)[Teams 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-administrator) |
| ライセンスの取り消し | [ライセンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| 動的メンバーシップ グループを更新する | [グループ所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| グループ所有者を更新する | [グループ所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| グループのプロパティを更新する | [グループ所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| グループの削除 | [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |

### 最小限の特権ロールのライセンス

[Microsoft Entra ライセンス](https://learn.microsoft.com/ja-jp/entra/fundamentals/licensing)のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ライセンスを割り当てる | [ライセンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| すべての構成を読み取る | [ディレクトリ 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-readers) | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |
| ライセンスの取り消し | [ライセンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| サブスクリプションを試用または購入する | [課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator) |  |

### ライフサイクル ワークフローの最小特権ロール

Microsoft Entra ID ガバナンス で [ライフサイクル ワークフロー](https://learn.microsoft.com/ja-jp/entra/id-governance/what-are-lifecycle-workflows) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ワークフローを作成する | [ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator) |  |
| ワークフローにカスタム拡張機能を追加する | [ライフサイクル ワークフロー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#lifecycle-workflows-administrator)。 また、ロジック アプリの共同作成者 か、Azure Resource Manager ロール 所有者 する必要があります。 |  |

### Microsoft Entra Health の最小特権ロール

[Microsoft Entra Health 監視](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-microsoft-entra-health)でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| シナリオ監視シグナルとアラート構成を表示する | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)[セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |
| アラートとアラートの電子メール構成を更新する | [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) |  |

### Microsoft Entra ID 保護 の最小特権ロール

[Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| アラート通知を構成する | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| MFA ポリシーを構成し、有効または無効にする | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| サインイン リスク ポリシーを構成し、有効または無効にする | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| ユーザー リスク ポリシーを構成し、有効または無効にする | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| 週刊ダイジェストを構成する | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| すべてのリスク検出を無視する | [セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator) |  |
| 脆弱性を修正または無視する | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| すべての構成を読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |
| すべてのリスク検出を読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |
| 脆弱性の読み取り | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |

### 監視と正常性 - 監査ログとサインイン ログの最小特権ロール

[Microsoft Entra 監視](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-monitoring-health)で監査ログとサインイン ログのタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 監査ログとサインイン ログの読み取り | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator)[グローバル セキュア アクセス 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-administrator)[ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |

### 監視と正常性 - 最小特権ロールのログのプロビジョニング

[Microsoft Entra プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| プロビジョニング ログの読み取り | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) | [エンタープライズ アプリケーション所有者](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#object-ownership)[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator)[ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |

### 監視と正常性 - 推奨事項の最小特権ロール

[Microsoft Entra ID の推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/overview-recommendations)のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 推奨事項の読み取り | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader) | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)[サービス サポート管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#service-support-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| 推奨事項を更新する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)[Exchange 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#exchange-administrator)[ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)[Identity Governance 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#identity-governance-administrator)[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)[SharePoint 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator) |
| ID セキュリティ スコアの改善アクションの読み取り | [サービス サポート管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#service-support-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[Exchange 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#exchange-administrator) |
| ID セキュリティ スコアの改善アクションを更新する | [SharePoint 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator) | [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader)[セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |

### 監視と正常性 - サインイン診断ツール

[サインイン診断ツール](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-sign-in-diagnostics)の実行時に使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| **問題の診断と解決**からサインイン診断を使用する | [課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator)[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)[カスタマー ロックボックスのアクセス承認者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#customer-lockbox-access-approver)[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)[ライセンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator)[グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader)[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| **サインイン ログ**からサインイン診断を使用する | [レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)と[課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator)の両方 | [グローバル セキュア アクセス 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-secure-access-administrator)[ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[セキュリティ オペレーター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-operator)[セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |

### 多要素認証の最小特権ロール

[Microsoft Entra 認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication)でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 選択したユーザーによって生成されたすべての既存のアプリケーション パスワードを削除する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) |
| [ユーザーごとの MFA を無効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates) | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |
| [ユーザーごとの MFA の有効化](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-userstates) | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |
| MFA サービスの設定を管理する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| 選択したユーザーについて連絡方法の再指定を必須にする | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) |  |
| 記憶されているすべてのデバイスで多要素認証を復元する | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) |  |

### MFA サーバーの最小特権ロール

[MFA Server](https://learn.microsoft.com/ja-jp/entra/identity/authentication/how-to-migrate-mfa-server-to-azure-mfa) でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ユーザーのブロック/ブロック解除 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| アカウント ロックアウトを構成する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| キャッシュ規則を構成する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| 不正アクセスのアラートを構成する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| 通知の構成 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| ワンタイム バイパスを構成する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| 電話の設定を構成する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| プロバイダーの構成 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| サーバー設定の構成 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| アクティビティ レポートを読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |
| すべての構成を読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |
| サーバーの状態を読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |

### 組織のリレーションシップの最小特権ロール

Microsoft Entra 外部 ID で [外部コラボレーション設定](https://learn.microsoft.com/ja-jp/entra/external-id/external-collaboration-settings-configure) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ID プロバイダーを管理する | [外部 ID プロバイダー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#external-identity-provider-administrator) |  |
| すべての構成を読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |

### パスワード リセットの最小特権ロール

Microsoft Entra ID で [パスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 認証方法を構成する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| カスタマイズの構成 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| 通知の構成 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| オンプレミスの統合を構成する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| パスワードのリセット プロパティを構成する | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |
| 登録の構成 | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| すべての構成を読み取る | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |

### Privileged Identity Management の最小特権ロール

Microsoft Entra ID ガバナンスで [Microsoft Entra Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ユーザーをロールに割り当てる | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| ロール設定を構成する | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| 監査アクティビティを表示する | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |
| ロールのメンバーシップを表示する | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |

### ロールと管理者の最小特権ロール

Microsoft Entra ID でロールと管理者のタスクを実行するときに使用する必要がある最小限 [の特権ロールを](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal) 次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ロールの割り当てを管理する | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| Microsoft Entra ロールのアクセス レビューを読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |
| すべての構成を読み取る | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |  |

### セキュリティ - 認証方法の最小特権ロール

Microsoft Entra ID で [認証方法](https://learn.microsoft.com/ja-jp/entra/identity/authentication/overview-authentication) のタスクを実行するときに使用する必要がある最小特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 認証方法を有効または無効にする | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |
| 個々のユーザー認証方法の表示、代理プロビジョニング、および管理を行う | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |
| パスワード保護を構成する | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| スマート ロックアウトを構成する | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| すべての構成を読み取る | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |

### セキュリティ - 条件付きアクセスの最小特権ロール

Microsoft Entra ID で [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| MFA の信頼できる IP アドレスを構成する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) |  |
| カスタム コントロールを作成する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| ネームド ロケーションを作成する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| ポリシーを作成する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| 利用規約を作成する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| VPN 接続の証明書を作成する | [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) |
| クラシック ポリシーを削除する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| 論理的に削除されたポリシーを復元する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| 利用規約を削除する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| VPN 接続の証明書を削除する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| クラシック ポリシーを無効にする | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| カスタム コントロールを管理する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| ネームド ロケーションを管理する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| 利用規約を管理する | [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| すべての構成を読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |
| ネームド ロケーションを読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |
| 使用条件の読み取り | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |
| サインインしているユーザーが同意した使用条件を確認する | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |  |

### セキュリティ - ID セキュリティ スコアの最小特権ロール

Microsoft Entra ID で [ID セキュリティ スコア](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-identity-secure-score) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| すべての構成を読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| セキュリティ スコアを読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| イベントの状態を更新する | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |

### セキュリティ - 危険なサインインの最小特権ロール

Microsoft Entra ID 保護 で [危険なサインイン](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| すべての構成を読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |
| 危険なサインインを読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |

### セキュリティ - リスクが最も低い特権ロールのフラグが設定されたユーザー

Microsoft Entra ID 保護 で [リスクのフラグが設定されたユーザー](https://learn.microsoft.com/ja-jp/entra/id-protection/howto-identity-protection-configure-notifications) に対してタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| すべてのイベントを閉じる | [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |  |
| SOC インシデント対応の ID 包含アクションを実行する | [Entra SOC Identity Responder](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#entra-soc-identity-responder) |  |
| すべての構成を読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |
| リスクのフラグ付きユーザーを読み取る | [セキュリティリーダー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) |  |

### 一時アクセス パスの最小特権ロール

Microsoft Entra ID で [一時アクセス パス](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-authentication-temporary-access-pass) のタスクを実行する場合に使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| 管理者またはメンバー (自分を除く) の一時アクセス パスを作成、削除、または表示する | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |  |
| メンバー (自分を除く) の一時アクセス パスを作成、削除、または表示する | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) |  |
| ユーザーの一時アクセス パスの詳細を表示する (コード自体は表示しない) | [グローバル読者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-reader) |  |
| 一時アクセス パスの認証方法ポリシーを構成または更新する | [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) |  |

### テナントの最小特権ロール

[Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)でタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| Microsoft Entra ID または Azure AD B2C テナントを作成する | [テナント作成者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#tenant-creator) |  |
| Microsoft Entra テナントのプロパティを更新する | [課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator) |  |
| [プライバシーに関する声明と連絡先を管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/properties-area) | [課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator) |  |

### ユーザーの最小特権ロール

Microsoft Entra ID で [ユーザー](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-create-delete-users) のタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| ディレクトリ ロールにユーザーを追加する | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| ユーザーをグループに追加する | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| ライセンスを割り当てる | [ライセンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| ゲスト ユーザーを作成する | [ゲスト招待元](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#guest-inviter) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| ゲスト ユーザーの招待をリセットする | [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| ユーザーの作成 | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| ユーザーの削除 | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| 制限付き管理者の更新トークンを無効にする | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| 非管理者の更新トークンを無効にする | [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) |
| 特権管理者の更新トークンを無効にする | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |  |
| 基本構成を読み取る | [既定のユーザー ロール](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions) |  |
| 制限付き管理者のパスワードをリセットする | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| 非管理者のパスワードをリセットする | [パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#password-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| 特権管理者のパスワードをリセットする | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |  |
| ライセンスの取り消し | [ライセンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator) | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
| ユーザー プリンシパル名を除くすべてのプロパティを更新する | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| オンプレミスの同期が有効なプロパティを更新する | [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator) |  |
| プロフィール写真とユーザー設定を更新する | [人事管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#people-administrator) |  |
| 制限付き管理者のユーザー プリンシパル名を更新する | [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |  |
| 特権管理者のユーザー プリンシパル名プロパティを更新する | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |  |
| ユーザー設定の更新 - 既定のユーザー ロールのアクセス許可 | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| [ユーザー設定を更新する - ゲスト ユーザー アクセス](https://learn.microsoft.com/ja-jp/entra/identity/users/users-restrict-guest-permissions) | [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) |  |
| ユーザー設定を更新する - 管理センター | [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) |  |
| [ユーザー設定を更新する - LinkedIn アカウント接続](https://learn.microsoft.com/ja-jp/entra/identity/users/linkedin-integration) | [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) |  |
| [ユーザー設定を更新する - \[サインインしたままにする\] を表示する](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-manage-stay-signed-in-prompt) | [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) |  |
| 認証方法を更新する | [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) |

### 最小限の特権ロールをサポートする

Microsoft Entra ID で [サポート](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support) するタスクを実行するときに使用する必要がある最小限の特権ロールを次に示します。

| Task | 最小特権ロール | その他のロール |
| --- | --- | --- |
| サポート チケットを送信する | [サービス サポート管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#service-support-administrator) | [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)[Azure Information Protection 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#azure-information-protection-administrator)[課金管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#billing-administrator)[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)[コンプライアンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#compliance-administrator)[Dynamics 365 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#dynamics-365-administrator)[デスクトップ Analytics 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#desktop-analytics-administrator)[Exchange 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#exchange-administrator)[ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)[Intune 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#intune-administrator)[パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#password-administrator)[ファブリック管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#fabric-administrator)[特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)[SharePoint 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator)[Skype for Business 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#skype-for-business-administrator)[Teams 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-administrator)[Teams 通信管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-communications-administrator)[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/groups-concept"} -->
## Microsoft Entra グループを使用してロールの割り当てを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept
- Service: entra-id / role-based-access-control
- Article date: 2026-03-16
- Summary: Microsoft Entra グループを使用して、Microsoft Entra ID でのロールの割り当て管理を簡略化します。

Microsoft Entra ID P1 または P2 では、ロールを割り当て可能なグループを作成して、これらのグループに Microsoft Entra ロールを割り当てることができます。 この機能により、ロール管理の簡素化、一貫性のあるアクセスの確保、より簡単な監査アクセス許可が可能になります。 個人ではなくグループにロールを割り当てると、ロールに対してユーザーを簡単に追加または削除でき、グループのすべてのメンバーに対して一貫したアクセス許可が作成されます。 特定のアクセス許可を持つカスタム ロールを作成し、グループに割り当てることもできます。

### ロールをグループに割り当てる理由

次の例を考えてみましょう。Contoso 社では地域を超えて従業員を雇っており、従業員のパスワードの管理およびリセットを Microsoft Entra 組織内で行っています。 特権ロール管理者に対して、各ユーザーにヘルプデスク管理者ロールを個別に割り当てるよう依頼する代わりに、Contoso\_Helpdesk\_Administrators グループを作成し、このグループにロールを割り当てることができます。 ユーザーがこのグループに参加すると、このロールが間接的に割り当てられます。 その後、既存のガバナンス ワークフローによってグループのメンバーシップの承認プロセスと監査を行い、正当なユーザーだけがグループのメンバーとなっていて、ヘルプデスク管理者ロールを割り当てられていることを確認できます。

### グループへのロール割り当てのしくみ

グループにロールを割り当てるには、`isAssignableToRole` プロパティが `true` に設定された新しいセキュリティ グループまたは Microsoft 365 グループを作成する必要があります。 Microsoft Entra 管理センターで、**[グループに Microsoft Entra ロールを割り当てることができる]** オプションを **[はい]** に設定します。 どちらの場合も、ユーザーにロールを割り当てるのと同じ方法で、1 つまたは複数の Microsoft Entra ロールをグループに割り当てることができます。

[Image: [ロールと管理者] ページのスクリーンショット]

### ロール割り当て可能なグループに関する制限事項

ロール割り当て可能なグループには、以下の制限があります。

- 新しいグループには、`isAssignableToRole` プロパティまたは **[グループに Microsoft Entra ロールを割り当てることができる]** オプションのみを設定できます。
- `isAssignableToRole` プロパティは**変更できません**。 このプロパティを設定してグループを作成した後に、これを変更することはできません。
- 既存のグループを、ロール割り当て可能なグループにすることはできません。
- 1 つの Microsoft Entra 組織 (テナント) には、最大 500 個のロール割り当て可能なグループを作成できます。

### ロール割り当て可能グループはどのように保護されているか

グループにロールが割り当てられている場合、動的メンバーシップ グループを管理できるすべての IT 管理者も、そのロールのメンバーシップを間接的に管理できます。 たとえば、Contoso\_User\_Administrators グループにユーザー管理者ロールが割り当てられているとします。 動的メンバーシップ グループを変更できる Exchange 管理者は、自身を Contoso\_User\_Administrators グループに追加することで、ユーザー管理者になることができます。 このように、管理者は意図していない方法で特権を昇格させることができます。

作成時に `isAssignableToRole` プロパティが `true` に設定されているグループに対してのみ、ロールを割り当てることができます。 このプロパティは変更できません。 このプロパティを設定してグループを作成した後に、これを変更することはできません。 既存のグループにこのプロパティを設定することはできません。

ロール割り当て可能なグループには以下の制限があり、潜在的な侵害を防げるように設計されています。

- ロール割り当て可能なグループを作成するには、少なくとも特権ロール管理者ロールが割り当てられている必要があります。
- ロール割り当て可能なグループのメンバーシップの種類を [割り当て済み] とする必要があり、Microsoft Entra 動的グループとすることはできません。 動的メンバーシップ グループの自動作成により、不要なアカウントがグループに追加され、そのロールに割り当てられる可能性があります。
- 既定では、ロールを割り当て可能なグループのメンバーシップを管理するのは特権ロール管理者ですが、グループ所有者を追加して、ロールを割り当て可能なグループの管理を委任することができます。
- Microsoft Graph の場合、ロール割り当て可能なグループのメンバーシップを管理するには、*RoleManagement.ReadWrite.Directory* アクセス許可が必要です。 *Group.ReadWrite.All* アクセス許可は機能しません。
- 特権の昇格を防ぐには、少なくとも特権認証管理者ロールを割り当てて、資格情報の変更、MFA のリセット、ロール割り当て可能なグループのメンバーと所有者の機密性の高い属性の変更を行う必要があります。
- グループの入れ子化はサポートされていません。 グループは、ロール割り当て可能なグループのメンバーとして追加することはできません。

### 削除と復元の動作

ロール割り当て可能なグループが削除されると、論理的に削除され、30 日以内に復元できます。 グループ所有者は、削除されたロール割り当て可能なグループを復元できます。 削除されたグループを復元する方法については、「 [Microsoft Entra ID で削除された Microsoft 365 グループまたはクラウド セキュリティ グループを復元](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-restore-deleted)する」を参照してください。

### PIM を使用して、グループをロール割り当ての対象にする

グループのメンバーにロールへの継続的なアクセスを許可したくない場合は、[Microsoft Entra Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) を使用して、グループをロール割り当ての対象にすることができます。 その後、グループの各メンバーは定められた期間、ロール割り当てのアクティブ化の対象となります。

注

Microsoft Entra ロールへの昇格に使われるグループの場合、資格のあるメンバーの割り当てに対して承認プロセスを要求することをお勧めします。 承認なしでアクティブ化できる割り当ては、ユーザーの資格情報をリセットし、そのユーザーに代わって割り当てをアクティブ化できる可能性がある、特権の低い管理者からのセキュリティ リスクに対して脆弱になる可能性があります。

ロールの昇格に使用されるグループが [ロール割り当て可能](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)として作成されていることを確認します。 ロールを割り当て可能なグループのメンバーと所有者の資格情報を変更するには、少なくとも特権認証管理者ロールを割り当てる必要があります。

### サポートされていないシナリオ

以下のシナリオはサポートされていません。

- Microsoft Entra のロール (組み込みまたはカスタム) をオンプレミスのグループに割り当てる。

### 既知の問題

ロール割り当て可能なグループに関する既知の問題を以下に示します。

- *Microsoft Entra ID P2 ライセンスをお持ちのお客様のみが*: グループを削除した後でも、PIM UI でロールの資格のあるメンバーとして表示されます。 機能的には問題はありません。これは、Microsoft Entra 管理センターのキャッシュの問題にすぎません。
- 動的メンバーシップ グループを使用したロールの割り当てには、[Exchange 管理センター](https://learn.microsoft.com/ja-jp/exchange/exchange-admin-center)を使用します。 古い Exchange 管理センターにアクセスする必要がある場合、資格のあるロールを (ロール割り当て可能なグループを介してではなく) ユーザーに直接割り当てます。 Exchange PowerShell コマンドレットは想定どおりに機能します。
- 管理者ロールが個々のユーザーではなくロール割り当て可能なグループに割り当てられている場合、グループのメンバーは、新しい [Exchange 管理センター](https://learn.microsoft.com/ja-jp/exchange/exchange-admin-center)のルール、組織、またはパブリック フォルダーにアクセスできなくなります。 回避策として、グループではなくユーザーにロールを直接割り当てます。
- Azure Information Protection ポータル (クラシック ポータル) では、グループを介したロール メンバーシップがまだ認識されません。 [統合秘密度ラベル付けプラットフォームに移行](https://learn.microsoft.com/ja-jp/azure/information-protection/configure-policy-migrate-labels)した後、Microsoft Purview ポータルを使用し、グループの割り当てでロールを管理できます。

### ライセンスの要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 Just-In-Time のロールのアクティブ化用の Privileged Identity Management には、Microsoft Entra ID P2 ライセンスが必要です。 ご自分の要件に対して適切なライセンスを探すには、[一般公開されている Free および Premium エディションの機能比較](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)に関するページをご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/groups-create-eligible"} -->
## Microsoft Entra ID でロール割り当てが可能なグループを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-create-eligible
- Service: entra-id / role-based-access-control
- Article date: 2025-01-03
- Summary: Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、Microsoft Entra ID でロール割り当て可能なグループを作成する方法について説明します。

この記事では、Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用してロール割り当て可能なグループを作成する方法について説明します。

Microsoft Entra ID P1 または P2 を使用すると、 [ロール割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept) を作成し、これらのグループに Microsoft Entra ロールを割り当てることができます。 新しいロール割り当て可能なグループを作成するには、Microsoft Entra ロールをグループに割り当てられるようにして [**はい**] に設定するか、 プロパティを `isAssignableToRole` に設定します。 ロール割り当て可能なグループを [動的メンバーシップ グループ](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-membership) の種類の一部にすることはできません。 Microsoft Entra では、1 つのテナントに最大 500 個のロール割り当て可能なグループを含めることができます。

### 前提条件

- Microsoft Entra ID P1 または P2 ライセンス
- [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API の Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

### ロール割り当て可能なグループを作成する

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[グループ]**&gt;**[すべてのグループ]** に移動します。
3. [ **新しいグループ]** を選択します。
4. [新しいグループ] ページで、グループの種類、名前、説明を指定します。
5. **[Microsoft Entra ロールをグループに割り当てることができる] を** **[はい**] に設定します。

    このオプションは、このオプションを設定できるロールである特権ロール管理者に表示されます。

    [Image: グループをロール割り当て可能なグループにするオプションのスクリーンショット。]
6. グループのメンバーと所有者を選択します。 オプションでグループにロールを割り当てることもできますが、こちらでロールを割り当てる必要はありません。
7. **作成** を選択します。

    次のメッセージが表示されます。

    Microsoft Entra ロール割り当ての対象となるグループの作成は、後で変更できない設定です。 この機能を追加しますか?

    [Image: ロール割り当て可能なグループを作成するときの確認メッセージのスクリーンショット。]
8. [ **はい] を選択します**。

    ロールを割り当てていた場合にはそれが付いた状態で、グループが作成されます。

## [PowerShell](#tab/ms-powershell)
[New-MgGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/new-mggroup?branch=main) コマンドを使用して、ロール割り当て可能なグループを作成します。

この例は、セキュリティ ロールを割り当て可能なグループの作成方法を示しています。

```powershell
Connect-MgGraph -Scopes "Group.ReadWrite.All"
$group = New-MgGroup -DisplayName "Contoso_Helpdesk_Administrators" -Description "Helpdesk Administrator role assigned to group" -MailEnabled:$false -SecurityEnabled -MailNickName "contosohelpdeskadministrators" -IsAssignableToRole:$true
```

この例は、Microsoft 365 ロールを割り当て可能なグループの作成方法を示しています。

```powershell
Connect-MgGraph -Scopes "Group.ReadWrite.All"
$group = New-MgGroup -DisplayName "Contoso_Helpdesk_Administrators" -Description "Helpdesk Administrator role assigned to group" -MailEnabled:$true -SecurityEnabled -MailNickName "contosohelpdeskadministrators" -IsAssignableToRole:$true -GroupTypes "Unified"
```

## [Graph API](#tab/ms-graph)
グループの [作成](https://learn.microsoft.com/ja-jp/graph/api/group-post-groups?branch=main) API を使用して、ロール割り当て可能なグループを作成します。

この例は、セキュリティ ロールを割り当て可能なグループの作成方法を示しています。

```http
POST https://graph.microsoft.com/v1.0/groups
{
    "description": "Helpdesk Administrator role assigned to group",
    "displayName": "Contoso_Helpdesk_Administrators",
    "isAssignableToRole": true,
    "mailEnabled": false,
    "mailNickname": "contosohelpdeskadministrators",
    "securityEnabled": true
}
```

応答

```http
HTTP/1.1 201 Created
```

この例は、Microsoft 365 ロールを割り当て可能なグループの作成方法を示しています。

```http
POST https://graph.microsoft.com/v1.0/groups
{
  "description": "Helpdesk Administrator role assigned to group",
  "displayName": "Contoso_Helpdesk_Administrators",
  "groupTypes": [
    "Unified"
  ],
  "isAssignableToRole": true,
  "mailEnabled": true,
  "mailNickname": "contosohelpdeskadministrators",
  "securityEnabled": true,
  "visibility" : "Private"
}
```

この種類のグループでは、`isPublic` は常に false で、`isSecurityEnabled` は常に true です。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/groups-faq-troubleshooting"} -->
## グループに割り当てられている Microsoft Entra ロールのトラブルシューティングを行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-faq-troubleshooting
- Service: entra-id / role-based-access-control
- Article date: 2026-03-16
- Summary: Microsoft Entra ID のグループにロールを割り当てる際の一般的な質問とトラブルシューティングのヒントをいくつか紹介します。

Microsoft Entra グループに Microsoft Entra ロールを割り当てる際の一般的な質問とトラブルシューティングのヒントを次に示します。

### グループ管理者ですが、[グループに Microsoft Entra ロールを割り当てることができる] スイッチが表示されません。

特権ロール管理者は、ロールの割り当ての対象となるグループを作成できます。 このロールのユーザーには、このスイッチが表示されます。

### Microsoft Entra ロールに割り当てられているグループのメンバーシップを変更できるのは誰ですか?

既定では、特権ロール管理者はロール割り当て可能なグループのメンバーシップを管理しますが、グループ所有者を追加することでロール割り当て可能なグループの管理を委任できます。

### 組織のヘルプデスク管理者ですが、ディレクトリ閲覧者であるユーザーのパスワードを更新できません。 なぜですか?

そのユーザーは、ロールを割り当て可能なグループ経由でディレクトリ閲覧者となった可能性があります。 ロールを割り当て可能なグループのすべてのメンバーと所有者は保護されています。 特権認証管理者ロールのユーザーは、保護されたユーザーの資格情報をリセットできます。

### あるユーザーのパスワードを更新できません。 そのユーザーには、高い特権ロールは一切割り当てられていません。 なぜ起きているのですか?

そのユーザーは、ロールを割り当て可能なグループの所有者である可能性があります。 特権の昇格を防ぐため、ロールを割り当て可能なグループの所有者を保護しています。 たとえば、Contoso\_Security\_Admins というグループがセキュリティ管理者ロールに割り当てられており、Bob がそのグループの所有者で、Alice は組織内のパスワード管理者であるとします。 この保護がなければ、Alice が Bob の資格情報をリセットし、彼の ID を乗っ取ることができます。 その後、Alice は自身や他のユーザーを Contoso\_Security\_Admins グループに追加して、その組織のセキュリティ管理者になることができてしまいます。 あるユーザーがグループ所有者であるかどうかを確認するには、そのユーザーが所有するオブジェクトの一覧にアクセスし、いずれかのグループで isAssignableToRole が true に設定されているかどうかを確認します。 設定されている場合、そのユーザーは保護対象であり、この動作は仕様によるものです。 所有オブジェクトへのアクセスについては、次のドキュメントを参照してください。

- [取得-MgUserOwnedObject](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguserownedobject)
- [OwnedObjects を一覧表示する](https://learn.microsoft.com/ja-jp/graph/api/user-list-ownedobjects?tabs=http)

### Microsoft Entra ロールに割り当てることができるグループ (具体的には、isAssignableToRole プロパティが true に設定されているグループ) にアクセス レビューを作成できますか?

はい、できます。 特権ロール管理者は、ロール割り当て可能なグループにアクセス レビューを作成できます。

### アクセス パッケージを作成して、Microsoft Entra ロールに割り当てることができるグループをその中に配置することはできますか?

はい、できます。 ユーザー管理者には、任意のグループをアクセス パッケージに配置するアクセス許可があります。 グローバル管理者には何も変更はありませんが、ユーザー管理者ロールのアクセス許可には若干の変更が加えられています。 ロールを割り当て可能なグループをアクセス パッケージに配置するには、ユーザー管理者であると同時に、ロールを割り当て可能なそのグループの所有者でもある必要があります。 エンタープライズ ライセンス管理でアクセス パッケージを作成できるユーザーの完全な表を次に示します。

| Microsoft Entra ディレクトリ ロール | 権利管理役割 | セキュリティグループを追加できます | Microsoft 365 グループの追加\* | アプリを追加できる | SharePoint Online サイトを追加できる |
| --- | --- | --- | --- | --- | --- |
| グローバル管理者 | 該当なし | ✔️ | ✔️ | ✔️ | ✔️ |
| ユーザー管理者 | 該当なし | ✔️ | ✔️ | ✔️ |  |
| Intune 管理者 | カタログ所有者 | ✔️ | ✔️ |  |  |
| Exchange 管理者 | カタログ所有者 |  | ✔️ |  |  |
| Teams サービス管理者 | カタログ所有者 |  | ✔️ |  |  |
| SharePoint 管理者 | カタログ所有者 |  | ✔️ |  | ✔️ |
| アプリケーション管理者 | カタログ所有者 |  |  | ✔️ |  |
| クラウド アプリケーション管理者 | カタログ所有者 |  |  | ✔️ |  |
| ユーザー | カタログ所有者 | グループ所有者の場合のみ | グループ所有者の場合のみ | アプリ所有者の場合のみ |  |

\*グループはロールを割り当て可能ではありません。つまり、isAssignableToRole = false です。 グループがロールを割り当て可能な場合は、アクセス パッケージを作成するユーザーは、ロールを割り当て可能なそのグループの所有者でもある必要があります。

### [割り当てられたロール] に [割り当ての削除] オプションが見つかりません。 ユーザーへのロールの割り当てをどうすれば削除できますか?

この回答は、Microsoft Entra ID P1 組織にのみ適用されます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. ユーザーを選択します。
4. **割り当てられたロール** を選択します。
5. 削除するロールの割り当てを選択します。
6. **割り当ての削除**を選択し、直接の役割の割り当てを削除します。

間接的なロールの割り当てを削除するには、そのロールに割り当てられているグループからユーザーを削除します。

### ロールを割り当て可能なすべてのグループを表示するにはどうすればよいですか?

次の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
3. [ **フィルターの追加] を選択します**。
4. **割り当て可能なロール**にフィルターします。

### プリンシパルに直接または間接的に割り当てられているロールを確認するにはどうすればよいですか?

次の手順のようにします。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. ユーザーを選択します。
4. **割り当てられたロール** を選択します。
5. Microsoft Entra ID P1 ライセンスをお持ちの場合は、**割り当てパス** 列を表示します。
6. Microsoft Entra ID P2 ライセンスをお持ちの場合は、[ **メンバーシップ** ] 列を表示します。

### ロールに割り当てるための新しいグループを作成する必要があるのはなぜですか?

既存のグループをロールに割り当てると、その既存のグループの所有者がこのグループに他のメンバーを追加し、それらの新しいメンバーが自分はロールを持つことになるということを認識しない事態が起こり得ます。 ロールを割り当て可能なグループは強力であるため、それを保護するために多くの制限が設けられています。 グループを管理しているユーザーが驚くような変更をグループに加えることは望ましくありません。

### グループ所有者は、削除されたロール割り当て可能なグループを復元できますか?

Yes. グループ所有者は、30 日間の論理的な削除ウィンドウ内で、削除されたロール割り当て可能なグループを復元できます。 削除されたグループを復元する方法については、「 [Microsoft Entra ID で削除された Microsoft 365 グループまたはクラウド セキュリティ グループを復元](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-restore-deleted)する」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/groups-remove-assignment"} -->
## Microsoft Entra ロールの割り当てを削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-remove-assignment
- Service: entra-id / role-based-access-control
- Article date: 2025-05-25
- Summary: Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、Microsoft Entra ID のロールの割り当てを削除します。

この記事では、Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して Microsoft Entra ロールの割り当てを削除する方法について説明します。

ユーザーの直接ロールと間接ロールの割り当ての両方を削除できます。 ユーザーにグループ メンバーシップによってロールが割り当てられている場合は、そのユーザーをグループから削除してロールの割り当てを削除します。 詳細については、「[Microsoft Entra グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)を参照してください。

### PIM での Microsoft Entra ロール

Microsoft Entra ID P2 ライセンスと [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure)がある場合は、ロールの割り当ての追加機能があります。 PIM で Microsoft Entra ロールの割り当てを削除する方法については、次の記事を参照してください。

| 方式 | 情報 |
| --- | --- |
| Microsoft Entra 管理センター | [PIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user#update-or-remove-an-existing-role-assignment) で既存のロールの割り当てを更新または削除する |
| Microsoft Graph PowerShell | [適格な割り当て](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/tutorial-pim#step-6-admin-removes-an-eligible-assignment) を削除する |
| Microsoft Graph API | [PIM API を使用して Microsoft Entra ロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview)[Microsoft Graph API](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user#remove-eligible-assignment-via-microsoft-graph-api) を使用して適格な割り当てを削除する |

### 前提条件

- Microsoft Entra ID P1 または P2 ライセンス
- 特権ロール管理者
- PowerShell を使用する場合の [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) モジュール
- Microsoft Graph API の Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「[PowerShell または Graph エクスプローラーを使用するための前提条件](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/prerequisites)」をご覧ください。

### Microsoft Entra ロールの割り当てを削除する

## [管理センター](#tab/admin-center)
1. [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)以上として [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**役割と管理者**に移動します。
3. ロール名を選択してロールを開きます。
4. ロールの割り当てを削除するユーザーまたはグループの横にチェック マークを追加します。
5. **[割り当ての削除]**を選択します。

    次のスクリーンショットとエクスペリエンスが異なる場合は、Microsoft Entra ID P2 と PIM を使用している可能性があります。 詳細については、「[PIM](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user#update-or-remove-an-existing-role-assignment)で既存のロールの割り当てを更新または削除する」を参照してください。

    [Image: ロールの割り当てを削除する [割り当て] ページのスクリーンショット。]
6. アクションを確認するよう求められたら、 **[はい]** を選択します。

## [PowerShell](#tab/ms-powershell)
[Get-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroleassignment) コマンドを使用して、削除するロールの割り当て ID を一覧表示します。 例については、「 [Microsoft Entra ロールの割り当ての一覧表示](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments?tabs=ms-powershell)」を参照してください。

ロールの割り当て ID で、 [Remove-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/remove-mgrolemanagementdirectoryroleassignment) コマンドを使用してロールの割り当てを削除します。

```powershell
Remove-MgRoleManagementDirectoryRoleAssignment -UnifiedRoleAssignmentId $roleAssignment.Id
```

## [Graph API](#tab/ms-graph)
[List unifiedRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roleassignments) API を使用して、削除するロールの割り当て ID を一覧表示します。 例については、「 [Microsoft Entra ロールの割り当ての一覧表示](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/view-assignments?tabs=ms-graph)」を参照してください。

ロールの割り当て ID を使用して、 [Delete unifiedRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/unifiedroleassignment-delete) API を使用してロールの割り当てを削除します。

#### ユーザーのロールの割り当てを削除する

```http
DELETE https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments/lAPpYvVpN0KRkAEhdxReEJC2sEqbR_9Hr48lds9SGHI-1
```

応答

```http
HTTP/1.1 204 No Content
```

#### 存在しなくなったロールの割り当てを削除する

```http
DELETE https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments/lAPpYvVpN0KRkAEhdxReEJC2sEqbR_9Hr48lds9SGHI-1
```

応答

```http
HTTP/1.1 404 Not Found
```

#### 現在のユーザーのグローバル管理者ロールの割り当てを削除する

```http
DELETE https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments/lAPpYvVpN0KRkAEhdxReEJC2sEqbR_9Hr48lds9SGHI-1
```

応答

```http
HTTP/1.1 400 Bad Request
{
    "odata.error":
    {
        "code":"Request_BadRequest",
        "message":
        {
            "lang":"en",
            "value":"Removing self from Global Administrator built-in role is not allowed"},
            "values":null
        }
    }
}
```

テナントにグローバル管理者が 0 人いるシナリオを回避するために、独自のグローバル管理者ロールの割り当てを削除することはできません。 自分に割り当てられている他のロールの削除は許可されます。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/m365-workload-docs"} -->
## Microsoft サービス全体のロール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/m365-workload-docs
- Service: entra-id / role-based-access-control
- Article date: 2024-08-31
- Summary: Microsoft 365 およびその他のサービスのロールベースのアクセス制御 (RBAC) に関連するコンテンツ、API リファレンス、監査および監視リファレンスを検索する

Microsoft 365 のサービスは、Microsoft Entra ID の管理者ロールで管理できます。 一部のサービスでは、そのサービスに固有の追加のロールも提供されます。 この記事では、Microsoft 365 およびその他のサービスのロールベースのアクセス制御 (RBAC) に関連するコンテンツ、API リファレンス、監査と監視リファレンスを示します。

### Microsoft Entra

Microsoft Entra での Microsoft Entra ID と関連サービス。

#### マイクロソフト エントラ ID

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) |
| Management API リファレンス | **Microsoft Entra ロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• ロールがグループに割り当てられている場合は、[Microsoft Graph v1.0 グループ API](https://learn.microsoft.com/ja-jp/graph/api/resources/groups-overview) を使用してグループ メンバーシップを管理します |
| 監査と監視リファレンス | **Microsoft Entra ロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査• ロールがグループに割り当てられている場合、グループ メンバーシップの変更を監査するには、カテゴリの `GroupManagement` とアクティビティの `Add member to group` と `Remove member from group` を含む監査を参照します。 |

#### エンタイトルメント管理

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [エンタイトルメント管理のロール](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-delegate#entitlement-management-roles) |
| Management API リファレンス | **Microsoft Entra ID でのエンタイトルメント管理に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.directory/entitlementManagement` で始まるアクセス許可を持つロールを参照する**エンタイトルメント管理に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `entitlementManagement` プロバイダーを使用する |
| 監査と監視リファレンス | **Microsoft Entra ID でのエンタイトルメント管理に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査**エンタイトルメント管理に固有のロール**Microsoft Entra 監査ログでは、カテゴリ `EntitlementManagement` とアクティビティは次のいずれかです。• `Remove Entitlement Management role assignment`• `Add Entitlement Management role assignment` |

### Microsoft 365

Microsoft 365 スイートのサービス。

#### 交換

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Exchange Online のアクセス許可](https://learn.microsoft.com/ja-jp/exchange/permissions-exo/permissions-exo) |
| Management API リファレンス | **Microsoft Entra ID での Exchange に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.office365.exchange` で始まるアクセス許可を持つロール**Exchange に固有のロール**[Microsoft Graph Beta roleManagement API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement?view=graph-rest-beta&preserve-view=true)• `exchange` プロバイダーを使用する |
| 監査と監視リファレンス | **Microsoft Entra ID での Exchange に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査**Exchange に固有のロール**[Microsoft Graph Beta Security API](https://learn.microsoft.com/ja-jp/graph/api/resources/security-api-overview?view=graph-rest-beta&preserve-view=true#audit-logs-query-preview) ([監査ログ クエリ](https://learn.microsoft.com/ja-jp/graph/api/resources/security-auditlogquery)) を使用して、recordType == `ExchangeAdmin` で Operation が次のいずれかである監査イベントを一覧表示します。`Add-RoleGroupMember`、 `Remove-RoleGroupMember`、 `Update-RoleGroupMember`、 `New-RoleGroup`、 `Remove-RoleGroup`、 `New-ManagementRole`、 `Remove-ManagementRoleEntry`、 `New-ManagementRoleAssignment` |

#### SharePoint

SharePoint、OneDrive、Delve、リスト、Project Online、Loop が含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft 365 での SharePoint 管理者ロールについて](https://learn.microsoft.com/ja-jp/sharepoint/sharepoint-admin-role)[管理者向けの Delve](https://learn.microsoft.com/ja-jp/sharepoint/delve-for-office-365-admins)[Microsoft Lists の制御設定](https://learn.microsoft.com/ja-jp/sharepoint/control-lists)[Project Online のアクセス許可管理を変更する](https://learn.microsoft.com/ja-jp/projectonline/change-permission-management-in-project-online) |
| Management API リファレンス | **Microsoft Entra ID での SharePoint に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.office365.sharepoint` で始まるアクセス許可を持つロール |
| 監査と監視リファレンス | **Microsoft Entra ID での SharePoint に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### Intune の

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Intune でのロールベースのアクセス制御 (RBAC)](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control) |
| Management API リファレンス | **Microsoft Entra ID での Intune に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.intune` で始まるアクセス許可を持つロール**Intune に固有のロール**[Microsoft Graph Beta roleManagement API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement?view=graph-rest-beta&preserve-view=true)• `deviceManagement` プロバイダーを使用する• または、Intune に固有の [Microsoft Graph Beta RBAC 管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/intune-rbac-conceptual?view=graph-rest-beta&preserve-view=true) を使用します |
| 監査と監視リファレンス | **Microsoft Entra ID での Intune に固有のロール**[Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリ**Intune に固有のロール**[Intune 監査の概要](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/monitor-audit-logs)Intune に固有の監査ログへの API アクセス:• [Microsoft Graph Beta の getAuditActivityTypes API](https://learn.microsoft.com/ja-jp/graph/api/intune-auditing-auditevent-getauditactivitytypes?view=graph-rest-beta&preserve-view=true)• 最初に category=`Role` のアクティビティの種類を一覧表示し、次に [Microsoft Graph Beta auditEvents API](https://learn.microsoft.com/ja-jp/graph/api/intune-auditing-auditevent-list?view=graph-rest-beta&preserve-view=true) を使用して各アクティビティの種類のすべての auditEvent を一覧表示します |

#### チーム

Teams、Bookings、Copilot Studio for Teams、Shifts が含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Teams 管理者ロールを使用して Teams を管理する](https://learn.microsoft.com/ja-jp/microsoftteams/using-admin-roles) |
| Management API リファレンス | **Microsoft Entra ID での Teams に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.teams` で始まるアクセス許可を持つロール |
| 監査と監視リファレンス | **Microsoft Entra ID での Teams に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### Purview スイート

Purview スイート、Azure Information Protection、および情報バリアが含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Defender for Office 365 および Microsoft Purview のロールとロール グループ](https://learn.microsoft.com/ja-jp/defender-office-365/scc-permissions) |
| Management API リファレンス | **Microsoft Entra ID での Purview に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• 次の内容で始まるアクセス許可を持つロールを参照する`microsoft.office365.complianceManager``microsoft.office365.protectionCenter``microsoft.office365.securityComplianceCenter`**Purview に固有のロール**PowerShell を使用する: [セキュリティとコンプライアンス PowerShell](https://learn.microsoft.com/ja-jp/powershell/exchange/scc-powershell)。 具体的なコマンドレットは次のとおりです。[Get-RoleGroup](https://learn.microsoft.com/ja-jp/powershell/module/exchange/get-rolegroup)[Get-RoleGroupMember (ロール グループ メンバーの取得)](https://learn.microsoft.com/ja-jp/powershell/module/exchange/get-rolegroupmember)[新しい役割グループ](https://learn.microsoft.com/ja-jp/powershell/module/exchange/new-rolegroup)[ロールグループメンバーの追加](https://learn.microsoft.com/ja-jp/powershell/module/exchange/add-rolegroupmember)[Update-RoleGroupMember (ロール グループ メンバーの更新)](https://learn.microsoft.com/ja-jp/powershell/module/exchange/update-rolegroupmember)[Remove-RoleGroupMember (ロール グループのメンバーの削除)](https://learn.microsoft.com/ja-jp/powershell/module/exchange/remove-rolegroupmember)[Remove-RoleGroup (ロール グループの削除)](https://learn.microsoft.com/ja-jp/powershell/module/exchange/remove-rolegroup) |
| 監査と監視リファレンス | **Microsoft Entra ID での Purview に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査**Purview に固有のロール**[Microsoft Graph Beta Security API](https://learn.microsoft.com/ja-jp/graph/api/resources/security-api-overview?view=graph-rest-beta&preserve-view=true#audit-logs-query-preview) ([監査ログ クエリ](https://learn.microsoft.com/ja-jp/graph/api/resources/security-auditlogquery?view=graph-rest-beta&preserve-view=true)) を使用して、recordType == `SecurityComplianceRBAC` で Operation が `Add-RoleGroupMember`、`Remove-RoleGroupMember`、`Update-RoleGroupMember`、`New-RoleGroup`、`Remove-RoleGroup` のいずれかである監査イベントを一覧表示します。 |

#### Power Platform（パワープラットフォーム）

Power Platform、Dynamics 365、Flow、Dataverse for Teams が含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [サービス管理者のロールを使用してテナントを管理する](https://learn.microsoft.com/ja-jp/power-platform/admin/use-service-admin-role-manage-tenant)[セキュリティ ロールと権限](https://learn.microsoft.com/ja-jp/power-platform/admin/security-roles-privileges) |
| Management API リファレンス | **Microsoft Entra ID での Power Platform に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• 次の内容で始まるアクセス許可を持つロールを参照する`microsoft.powerApps``microsoft.dynamics365``microsoft.flow`**Dataverse に固有のロール**[Web API を使用して演算を実行する](https://learn.microsoft.com/ja-jp/power-apps/developer/data-platform/webapi/perform-operations-web-api)• [ユーザー (SystemUser) テーブル/エンティティ参照](https://learn.microsoft.com/ja-jp/power-apps/developer/data-platform/reference/entities/systemuser)を照会する• ロールの割り当ては、[systemuserroles_association](https://learn.microsoft.com/ja-jp/power-apps/developer/data-platform/reference/entities/systemuser#BKMK_systemuserroles_association) テーブルの一部です |
| 監査と監視リファレンス | **Microsoft Entra ID での Power Platform に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査**Dataverse に固有のロール**[Dataverse 監査の概要](https://learn.microsoft.com/ja-jp/power-platform/admin/manage-dataverse-auditing)Dataverse に固有の監査ログにアクセスするための API[DataverseWeb API](https://learn.microsoft.com/ja-jp/power-apps/developer/data-platform/webapi/perform-operations-web-api)• [監査テーブル リファレンス](https://learn.microsoft.com/ja-jp/power-apps/developer/data-platform/reference/entities/audit)• [アクション コード](https://learn.microsoft.com/ja-jp/power-apps/developer/data-platform/reference/entities/audit#action-choicesoptions)を使用した監査 :53 – チームへロールを割り当て54 – チームからロールを削除55 – ユーザーにロールを割り当て56 – ユーザーからロールを削除57 – ロールに特権を追加58 – ロールから特権を削除59 – ロールの特権を置き換え |

#### Defender スイート

Defender スイート、セキュア スコア、Cloud App Security、脅威インテリジェンスが含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Defender XDR 統合ロールベースのアクセス制御 (RBAC)](https://learn.microsoft.com/ja-jp/defender-xdr/manage-rbac) |
| Management API リファレンス | **Microsoft Entra ID での Defender に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• 次のロールにはアクセス許可が付与されています ([参照](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/m365d-permissions)): セキュリティ管理者、セキュリティ オペレーター、セキュリティ閲覧者、グローバル管理者、グローバル閲覧者**Defender に固有のロール**Defender 統合 RBAC を使用するには、ワークロードをアクティブ化する必要があります。 「[Microsoft Defender XDR 統合ロールベースのアクセス制御 (RBAC) のアクティブ化](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/activate-defender-rbac)」をご覧ください。 Defender 統合 RBAC をアクティブ化すると、個々の Defender ソリューション ロールが無効になります。• security.microsoft.com ポータル経由でのみ管理できます。 |
| 監査と監視リファレンス | **Microsoft Entra ID での Defender に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### Viva Engage

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Viva Engage で管理者ロールを管理する](https://learn.microsoft.com/ja-jp/viva/engage/eac-key-admin-roles-permissions) |
| Management API リファレンス | **Microsoft Entra ID での Viva Engage に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.office365.yammer` で始まるアクセス許可を持つロールを参照する**Viva Engage に固有のロール**• 検証済みの管理者とネットワーク管理者のロールは、Yammer 管理センターを介して管理できます。• 企業コミュニケーターのロールは、Viva Engage 管理センターを介して割り当てることができます。• [Yammer データ エクスポート API](https://learn.microsoft.com/ja-jp/rest/api/yammer/network-data-export) を使用してadmins.csvをエクスポートし、管理者の一覧を読み出すことができます |
| 監査と監視リファレンス | **Microsoft Entra ID での Viva Engage に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査**Viva Engage に固有のロール**• [Yammer データ エクスポート API](https://learn.microsoft.com/ja-jp/rest/api/yammer/network-data-export) を使用して、管理者一覧の admins.csv を段階的にエクスポートします |

#### Viva コネクション

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Viva での管理者ロールとタスク](https://learn.microsoft.com/ja-jp/viva/microsoft-viva-admin-roles#viva-connections) |
| Management API リファレンス | **Microsoft Entra ID での Viva Connections に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• 次のロールにはアクセス許可が付与されます: SharePoint 管理者、Teams 管理者、グローバル管理者 |
| 監査と監視リファレンス | **Microsoft Entra ID での Viva Connections に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### Viva ラーニング

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Teams 管理センターで Microsoft Viva Learning をセットアップする](https://learn.microsoft.com/ja-jp/viva/learning/set-up-viva-learning#admin-roles-and-permissions) |
| Management API リファレンス | **Microsoft Entra ID での Viva Learning に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.office365.knowledge` で始まるアクセス許可を持つロールを参照する |
| 監査と監視リファレンス | **Microsoft Entra ID での Viva Learning に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### Viva インサイト

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Viva Insights でのロール](https://learn.microsoft.com/ja-jp/viva/insights/advanced/setup-maint/user-roles) |
| Management API リファレンス | **Microsoft Entra ID での Viva Insights に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.office365.insights` で始まるアクセス許可を持つロールを参照する |
| 監査と監視リファレンス | **Microsoft Entra ID での Viva Insights に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### 検索する

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Search のセットアップ](https://learn.microsoft.com/ja-jp/microsoftsearch/setup-microsoft-search) |
| Management API リファレンス | **Microsoft Entra ID での Search に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.office365.search` で始まるアクセス許可を持つロールを参照する |
| 監査と監視リファレンス | **Microsoft Entra ID での Search に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### ユニバーサル プリント

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [ユニバーサル プリントの管理者のロール](https://learn.microsoft.com/ja-jp/universal-print/fundamentals/universal-print-administrator-roles) |
| Management API リファレンス | Microsoft Entra ID **でのユニバーサル印刷固有のロールの**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.azure.print` で始まるアクセス許可を持つロールを参照する |
| 監査と監視リファレンス | Microsoft Entra ID **でのユニバーサル印刷固有のロールの**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### Microsoft 365 アプリ スイートの管理

Microsoft 365 アプリ スイートの管理と Forms が含まれています。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft 365 アプリ管理センターの概要](https://learn.microsoft.com/ja-jp/microsoft-365-apps/admin-center/overview)[Microsoft Forms の管理者設定](https://learn.microsoft.com/ja-jp/microsoft-forms/administrator-settings-microsoft-forms) |
| Management API リファレンス | **Microsoft Entra ID での Microsoft 365 アプリに固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• 次のロールにはアクセス許可が付与されています: Office アプリ管理者、セキュリティ管理者、グローバル管理者 |
| 監査と監視リファレンス | **Microsoft Entra ID での Microsoft 365 アプリに固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

### 紺碧

Azure コントロール プレーンとサブスクリプション情報用の Azure ロールベースのアクセス制御 (Azure RBAC)。

#### 紺碧

Azure と Sentinel が含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Azure ロールベースのアクセス制御 (Azure RBAC) とは](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/overview)[Microsoft Sentinel のロールとアクセス許可](https://learn.microsoft.com/ja-jp/azure/sentinel/roles) |
| Management API リファレンス | **Azure での Azure サービスに固有のロール**[Azure Resource Manager 認可 API](https://learn.microsoft.com/ja-jp/rest/api/authorization)• ロールの割り当て: [リスト](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-list-rest)、[作成/更新](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-rest)、[削除](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/role-assignments-remove#rest-api)• ロールの定義: [リスト](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-definitions/list)、[作成/更新](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-definitions/create-or-update)、[削除](https://learn.microsoft.com/ja-jp/rest/api/authorization/role-definitions/delete)• [従来の管理者](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/classic-administrators)と呼ばれる Azure リソースへのアクセスを許可する従来の方法があります。 従来の管理者は、Azure RBAC での所有者ロールと同等です。 従来の管理者は 2024 年 8 月に廃止されます。• Microsoft Entra グローバル管理者は、[アクセス権の昇格](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)により、Azure への一方的なアクセス権を取得できます。 |
| 監査と監視リファレンス | **Azure での Azure サービスに固有のロール**[Azure アクティビティ ログで Azure RBAC の変更を監視する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/change-history-report)• [Azure アクティビティ ログ API](https://learn.microsoft.com/ja-jp/rest/api/monitor/activity-logs/list)• イベント カテゴリの `Administrative` と操作の `Create role assignment`、`Delete role assignment`、`Create or update custom role definition`、`Delete custom role definition` での監査。[テナント レベルの Azure アクティビティ ログでアクセス権の昇格ログを表示する](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin#view-elevate-access-log-entries-in-the-directory-activity-logs)• [Azure アクティビティ ログ API – テナント アクティビティ ログ](https://learn.microsoft.com/ja-jp/rest/api/monitor/tenant-activity-logs/list)• イベント カテゴリの `Administrative` で文字列 `elevateAccess` を含む監査。• テナント レベルのアクティビティ ログにアクセスするには、[アクセス権の昇格](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)を使用して少なくとも 1 回はテナント レベルのアクセス権を取得する必要があります。 |

### 商業

購入と請求に関連するサービス。

#### Cost Management and Billing – エンタープライズ契約

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Azure におけるマイクロソフト エンタープライズ契約のロールの管理](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-ea-roles) |
| Management API リファレンス | **Microsoft Entra ID でのエンタープライズ契約に固有のロール**エンタープライズ契約では、Microsoft Entra ロールはサポートされていません。**エンタープライズ契約に固有のロール**[課金ロールの割り当て API](https://learn.microsoft.com/ja-jp/rest/api/billing/role-assignments)• エンタープライズ管理者 (ロール ID: 9f1983cb-2574-400c-87e9-34cf8e2280db)• エンタープライズ管理者 (読み取り専用) (ロール ID: 24f8edb6-1668-4659-b5e2-40bb5f3a7d7e)• EA 購入者 (ロール ID: da6647fb-7651-49ee-be91-c43c4877f0c4)[登録部署のロールの割り当て API](https://learn.microsoft.com/ja-jp/rest/api/billing/enrollment-department-role-assignments)• 部署管理者 (ロール ID: fb2cf67f-be5b-42e7-8025-4683c668f840)• 部署閲覧者 (ロール ID: db609904-a47f-4794-9be8-9bd86fbffd8a)[登録アカウント ロールの割り当て API](https://learn.microsoft.com/ja-jp/rest/api/billing/enrollment-account-role-assignments)• アカウント オーナー (ロール ID: c15c22c0-9faf-424c-9b7e-bd91c06a240b) |
| 監査と監視リファレンス | **エンタープライズ契約に固有のロール**[Azure アクティビティ ログ API – テナント アクティビティ ログ](https://learn.microsoft.com/ja-jp/rest/api/monitor/tenant-activity-logs/list)• テナント レベルのアクティビティ ログにアクセスするには、[アクセス権の昇格](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)を使用して少なくとも 1 回はテナント レベルのアクセス権を取得する必要があります。• resourceProvider == `Microsoft.Billing` で operationName に `billingRoleAssignments` または `EnrollmentAccount` が含まれる場合の監査 |

#### Cost Management and Billing – Microsoft 顧客契約

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Azure での Microsoft 顧客契約の管理ロールを理解する](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-mca-roles)[Microsoft ビジネス課金アカウントについて](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/manage-billing-accounts#what-are-billing-account-roles) |
| Management API リファレンス | **Microsoft Entra ID でのMicrosoft 顧客契約に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• 次のロールにはアクセス許可が付与されています: 課金管理者、グローバル管理者。**Microsoft 顧客契約に固有のロール**• デフォルトでは、Microsoft Entra グローバル管理者ロールと課金管理者ロールには、Microsoft 顧客契約に固有の RBAC での課金アカウント所有者ロールが自動的に割り当てられます。• [課金ロールの割り当て API](https://learn.microsoft.com/ja-jp/rest/api/billing/billing-role-assignments) |
| 監査と監視リファレンス | **Microsoft Entra ID でのMicrosoft 顧客契約に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査**Microsoft 顧客契約に固有のロール**[Azure アクティビティ ログ API – テナント アクティビティ ログ](https://learn.microsoft.com/ja-jp/rest/api/monitor/tenant-activity-logs/list)• テナント レベルのアクティビティ ログにアクセスするには、[アクセス権の昇格](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)を使用して少なくとも 1 回はテナント レベルのアクセス権を取得する必要があります。• resourceProvider == `Microsoft.Billing` で operationName が次のいずれか (すべて `Microsoft.Billing` で始まる) である場合の監査。`/permissionRequests/write``/billingAccounts/createBillingRoleAssignment/action``/billingAccounts/billingProfiles/createBillingRoleAssignment/action``/billingAccounts/billingProfiles/invoiceSections/createBillingRoleAssignment/action``/billingAccounts/customers/createBillingRoleAssignment/action``/billingAccounts/billingRoleAssignments/write``/billingAccounts/billingRoleAssignments/delete``/billingAccounts/billingProfiles/billingRoleAssignments/delete``/billingAccounts/billingProfiles/customers/createBillingRoleAssignment/action``/billingAccounts/billingProfiles/invoiceSections/billingRoleAssignments/delete``/billingAccounts/departments/billingRoleAssignments/write``/billingAccounts/departments/billingRoleAssignments/delete``/billingAccounts/enrollmentAccounts/transferBillingSubscriptions/action``/billingAccounts/enrollmentAccounts/billingRoleAssignments/write``/billingAccounts/enrollmentAccounts/billingRoleAssignments/delete``/billingAccounts/billingProfiles/invoiceSections/billingSubscriptions/transfer/action``/billingAccounts/billingProfiles/invoiceSections/initiateTransfer/action``/billingAccounts/billingProfiles/invoiceSections/transfers/delete``/billingAccounts/billingProfiles/invoiceSections/transfers/cancel/action``/billingAccounts/billingProfiles/invoiceSections/transfers/write``/transfers/acceptTransfer/action``/transfers/accept/action``/transfers/decline/action``/transfers/declineTransfer/action``/billingAccounts/customers/initiateTransfer/action``/billingAccounts/customers/transfers/delete``/billingAccounts/customers/transfers/cancel/action``/billingAccounts/customers/transfers/write``/billingAccounts/billingProfiles/invoiceSections/products/transfer/action``/billingAccounts/billingSubscriptions/elevateRole/action` |

#### ビジネス サブスクリプションと課金 – ボリューム ライセンス

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [ボリューム ライセンス ユーザー ロールの管理に関するよく寄せられる質問](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/licenses/user-roles-faq) |
| Management API リファレンス | **Microsoft Entra ID でのボリューム ライセンスに固有のロール**ボリューム ライセンスでは、Microsoft Entra ロールはサポートされていません。**ボリューム ライセンスに固有のロール**[ボリューム ライセンス ユーザーとロール](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/licenses/user-roles-faq#how-do-i-manage-vl-users-and-roles)は M365 管理センターで管理されます。 |

#### パートナー センター

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [ユーザーのロール、アクセス許可、ワークスペース アクセス](https://learn.microsoft.com/ja-jp/partner-center/permissions-overview) |
| Management API リファレンス | **Microsoft Entra ID でのパートナー センターに固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• 次のロールにはアクセス許可が付与されています: グローバル管理者、ユーザー管理者。**パートナー センターに固有のロール**[パートナー センターに固有のロール](https://learn.microsoft.com/ja-jp/partner-center/permissions-overview#microsoft-entra-tenant-roles-and-non-azure-ad-roles)はパートナー センターを介してのみ管理できます。 |
| 監査と監視リファレンス | **Microsoft Entra ID でのパートナー センターに固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

### その他のサービス

#### Azure DevOps

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [アクセス許可とセキュリティ グループについて](https://learn.microsoft.com/ja-jp/azure/devops/organizations/security/about-permissions) |
| Management API リファレンス | **Microsoft Entra ID での Azure DevOps に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.azure.devOps` で始まるアクセス許可を持つロールを参照する**Azure DevOps に固有のロール**[Roleassignments API](https://learn.microsoft.com/ja-jp/rest/api/azure/devops/securityroles/roleassignments) を使用して付与されたアクセス許可の作成/読み取り/更新/削除• [Roledefinitions API](https://learn.microsoft.com/ja-jp/rest/api/azure/devops/securityroles/roledefinitions) を使用してロールのアクセス許可を表示する• [アクセス許可のリファレンス トピック](https://learn.microsoft.com/ja-jp/azure/devops/organizations/security/permissions)• Azure DevOps グループ (注: Microsoft Entra グループとは異なる) がロールに割り当てられている場合は、[Memberships API](https://learn.microsoft.com/ja-jp/rest/api/azure/devops/graph/memberships) を使用してグループ メンバーシップを作成、読み取り、更新、削除します |
| 監査と監視リファレンス | **Microsoft Entra ID での Azure DevOps に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査**Azure DevOps に固有のロール**• [AzureDevOps 監査ログへのアクセス](https://learn.microsoft.com/ja-jp/azure/devops/organizations/audit/azure-devops-auditing)• [監査 API リファレンス](https://learn.microsoft.com/ja-jp/rest/api/azure/devops/audit/)• [AuditId リファレンス](https://learn.microsoft.com/ja-jp/azure/devops/organizations/audit/auditing-events)• ActionId `Security.ModifyPermission`、`Security.RemovePermission` を使用した監査。• ロールに割り当てられたグループへの変更については、ActionId `Group.UpdateGroupMembership`、`Group.UpdateGroupMembership.Add`、`Group.UpdateGroupMembership.Remove` を使用した監査 |

#### ファブリック

Fabric と Power BI が含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Fabric 管理者ロールを理解する](https://learn.microsoft.com/ja-jp/fabric/admin/roles) |
| Management API リファレンス | **Microsoft Entra ID での Fabric に固有のロール**[Microsoft Graph v1.0 ロール管理 API](https://learn.microsoft.com/ja-jp/graph/api/resources/rolemanagement)• `directory` プロバイダーを使用する• `microsoft.powerApps.powerBI` で始まるアクセス許可を持つロールを参照する |
| 監査と監視リファレンス | **Microsoft Entra ID での Fabric に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• `RoleManagement` カテゴリを使用した監査 |

#### カスタマー サポート ケースを管理するための統合サポート ポータル

統合サポート ポータルとサービス ハブが含まれます。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [サービス ハブのロールとアクセス許可](https://learn.microsoft.com/ja-jp/services-hub/unified/getting-started/roles-permissions) |
| Management API リファレンス | これらのロールをサービス ハブ ポータル https://serviceshub.microsoft.com で管理します。 |

### Microsoft Graph アプリケーション アクセス許可

前述の RBAC システムに加えて、アプリケーションのアクセス許可を使用して、Microsoft Entra アプリケーションの登録とサービス プリンシパルに昇格されたアクセス許可を付与できます。 たとえば、非対話型の非人間型アプリケーション ID には、テナント内のすべてのメールを読み取る機能 ( `Mail.Read` アプリケーションのアクセス許可) を付与できます。 アプリケーションのアクセス許可を管理および監視する方法を次の表に示します。

| 面グラフ | コンテンツ |
| --- | --- |
| 概要 | [Microsoft Graph のアクセス許可の概要](https://learn.microsoft.com/ja-jp/graph/permissions-overview?tabs=http#application-permissions) |
| Management API リファレンス | **Microsoft Entra ID での Microsoft Graph に固有のロール**[Microsoft Graph v1.0 servicePrincipal API](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal)• テナント内の各 [servicePrincipal](https://learn.microsoft.com/ja-jp/graph/api/resources/approleassignment) の [appRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/resources/serviceprincipal) を列挙します。• appRoleAssignment ごとに、appRoleAssignment の resourceId と appRoleId によって参照される servicePrincipal オブジェクトの appRole プロパティを読み取って、割り当てによって付与されたアクセス許可に関する情報を取得します。• 特に重要なのは、Microsoft Graph (appID == "00000003-0000-0000-c000-000000000000") に対するアプリのアクセス許可であり、Exchange、SharePoint、Teams などのアクセス許可を付与します。 [Microsoft Graph のアクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-reference)のリファレンスを次に示します。• [アプリケーションのための Microsoft Entra セキュリティ オペレーション](https://learn.microsoft.com/ja-jp/entra/architecture/security-operations-applications)もご覧ください。 |
| 監査と監視リファレンス | **Microsoft Entra ID での Microsoft Graph に固有のロール**[Microsoft Entra アクティビティ ログの概要](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)Microsoft Entra 監査ログへの API アクセス:• [Microsoft Graph v1.0 directoryAudit API](https://learn.microsoft.com/ja-jp/graph/api/resources/directoryaudit)• カテゴリ `ApplicationManagement` とアクティビティ名 `Add app role assignment to service principal` を使用した監査 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/manage-roles-portal"} -->
## Microsoft Entra ロールを割り当てる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal
- Service: entra-id / role-based-access-control
- Article date: 2025-06-04
- Summary: Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、テナント、アプリケーション登録、管理単位のスコープでユーザーとグループに Microsoft Entra ロールを割り当てる方法について説明します。

この記事では、Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、ユーザーとグループに Microsoft Entra ロールを割り当てる方法について説明します。 また、テナント、アプリケーションの登録、管理単位のスコープなど、さまざまなスコープでロールを割り当てる方法についても説明します。

直接ロールと間接ロールの両方の割り当てをユーザーに割り当てることができます。 ユーザーにグループ メンバーシップによってロールが割り当てられている場合は、そのユーザーをグループに追加してロールの割り当てを追加します。 詳細については、「 [Microsoft Entra グループを使用してロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)」を参照してください。

Microsoft Entra ID では、通常、ロールはテナント全体に適用されるように割り当てられます。 ただし、アプリケーションの登録や管理単位など、さまざまなリソースに Microsoft Entra ロールを割り当てることもできます。 たとえば、ヘルプデスク管理者ロールを割り当てて、テナント全体ではなく特定の管理単位にのみ適用されるようにすることができます。 ロールの割り当てが適用されるリソースは、スコープとも呼ばれます。 ロールの割り当てのスコープの制限は、組み込みロールとカスタム ロールでサポートされています。 スコープの詳細については、「 [Microsoft Entra ID でのロールベースのアクセス制御 (RBAC) の概要」を](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-overview#scope)参照してください。

### PIM における Microsoft Entra のロール

Microsoft Entra ID P2 ライセンスと [Privileged Identity Management (PIM)](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) をお持ちの場合は、ロールを割り当てるときに、ユーザーにロールの割り当ての資格を与えたり、ロールの割り当ての開始時刻と終了時刻を定義したりするなどの追加機能があります。 PIM で Microsoft Entra ロールを割り当てる方法については、次の記事を参照してください。

| 方式 | 情報 |
| --- | --- |
| Microsoft Entra 管理センター | [Privileged Identity Management で Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user) |
| Microsoft Graph PowerShell | [チュートリアル: Microsoft Graph PowerShell を使用して Privileged Identity Management で Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/tutorial-pim) |
| Microsoft Graph API | [PIM API を使用して Microsoft Entra ロールの割り当てを管理する](https://learn.microsoft.com/ja-jp/graph/api/resources/privilegedidentitymanagementv3-overview)[Privileged Identity Management で Microsoft Entra ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-add-role-to-user#assign-a-role-using-microsoft-graph-api) |

### 前提 条件

- 特権ロール管理者
- [PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API 用 Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

### テナント スコープでロールを割り当てる

このセクションでは、テナント スコープでロールを割り当てる方法について説明します。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. アイデンティティ役割 & 管理者に移動します。

    [Image: Microsoft Entra 管理センターの [ロールと管理者] ページのスクリーンショット。]
3. ロール名を選択してロールを開きます。 ロールの横にチェック マークを追加しないでください。

    [Image: ロール名の上にマウス ポインターを合わせて表示された [ロールと管理者] ページのスクリーンショット。]
4. [ **割り当ての追加]** を選択し、このロールに割り当てるユーザー、グループ、またはエージェント ID を選択します。

    ロール割り当て可能なグループのみが表示されます。 グループが一覧にない場合は、ロール割り当て可能なグループを作成する必要があります。 詳細については、「 [Microsoft Entra ID でロール割り当て可能なグループを作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-create-eligible)する」を参照してください。

    エージェント ID に割り当てることができるロールの一覧については、「 [Microsoft Entra Agent ID での承認](https://learn.microsoft.com/ja-jp/entra/agent-id/identity-professional/authorization-agent-id)」を参照してください。

    次のスクリーンショットとエクスペリエンスが異なる場合は、Microsoft Entra ID P2 と PIM を使用している可能性があります。 詳細については、「Microsoft Entra の役割を Privileged Identity Managementに割り当てる 」を参照してください。

    [Image: 選択したロールの [割り当ての追加] ウィンドウのスクリーンショット。]
5. [ **追加]** を選択してロールを割り当てます。

## [PowerShell](#tab/ms-powershell)
PowerShell を使用して Microsoft Entra ロールを割り当てるには、次の手順に従います。

1. PowerShell ウィンドウを開きます。 必要に応じて、 [Install-Module](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) を使用して Microsoft Graph PowerShell をインストールします。 詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
2. PowerShell ウィンドウで、 [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) を使用してテナントにサインインします。

    ```powershell
    Connect-MgGraph -Scopes "RoleManagement.ReadWrite.Directory"
    ```
3. [Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser) を使用してユーザーを取得します。

    ```powershell
    $user = Get-MgUser -Filter "userPrincipalName eq 'alice@contoso.com'"
    ```

    [Get-MgGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.groups/get-mggroup) を使用して、ロール割り当て可能なグループを取得します。

    ```powershell
    $group = Get-MgGroup -Filter "DisplayName eq 'Contoso Helpdesk'"
    ```
4. [Get-MgRoleManagementDirectoryRoleDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroledefinition) を使用して、割り当てるロールを取得します。

    すべての組み込みロールのロール定義 ID の一覧については、Microsoft Entra 組み込みロール を参照してください。

    ```powershell
    $roleDefinition = Get-MgRoleManagementDirectoryRoleDefinition -Filter "displayName eq 'Billing Administrator'"
    ```
5. テナントをロールの割り当てのスコープとして設定します。

    ```powershell
    $directoryScope = '/'
    ```
6. [New-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/new-mgrolemanagementdirectoryroleassignment) を使用してロールを割り当てます。

    ```powershell
    $roleAssignment = New-MgRoleManagementDirectoryRoleAssignment `
       -DirectoryScopeId $directoryScope -PrincipalId $user.Id `
       -RoleDefinitionId $roleDefinition.Id
    ```

    ```powershell
    $roleAssignment = New-MgRoleManagementDirectoryRoleAssignment `
        -DirectoryScopeId $directoryScope -PrincipalId $group.Id `
        -RoleDefinitionId $roleDefinition.Id
    ```

    ロールを割り当てる別の方法を次に示します。

    ```powershell
    $params = @{
       "directoryScopeId" = "/" 
       "principalId" = $group.Id
       "roleDefinitionId" = $roleDefinition.Id
    }
    $roleAssignment = New-MgRoleManagementDirectoryRoleAssignment -BodyParameter $params
    ```

## [Graph API](#tab/ms-graph)
[Graph エクスプローラー](https://aka.ms/ge)で Microsoft Graph API を使用してロールを割り当てるには、次の手順に従います。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. [List users](https://learn.microsoft.com/ja-jp/graph/api/user-list) API を使用してユーザーを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/users?$filter=userPrincipalName eq 'alice@contoso.com'
    ```

    [List groups](https://learn.microsoft.com/ja-jp/graph/api/group-list) API を使用して、ロール割り当て可能なグループを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/groups?$filter=displayName eq 'Contoso Helpdesk'
    ```
3. [List unifiedRoleDefinitions](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roledefinitions) API を使用して、割り当てるロールを取得します。

    すべての組み込みロールのロール定義 ID の一覧については、Microsoft Entra 組み込みロール を参照してください。

    ```http
    GET https://graph.microsoft.com/v1.0/rolemanagement/directory/roleDefinitions?$filter=displayName eq 'Billing Administrator'
    ```
4. [Create unifiedRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments) API を使用してロールを割り当てます。

    ```http
    POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments
    {
        "@odata.type": "#microsoft.graph.unifiedRoleAssignment",
        "principalId": "<Object ID of user or group>",
        "roleDefinitionId": "<ID of role definition>",
        "directoryScopeId": "/"
    }
    ```

    応答

    ```http
    HTTP/1.1 201 Created
    Content-type: application/json
    {
        "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#roleManagement/directory/roleAssignments/$entity",
        "id": "<Role assignment ID>",
        "roleDefinitionId": "<ID of role definition>",
        "principalId": "<Object ID of user or group>",
        "directoryScopeId": "/"
    }
    ```

    プリンシパルまたはロールの定義が存在しない場合、応答は見つかりません。

    応答

    ```http
    HTTP/1.1 404 Not Found
    ```

---

### アプリ登録スコープでロールを割り当てる

組み込みロールとカスタム ロールは、組織内のすべてのアプリ登録に対するアクセス許可を付与するために、テナント スコープで既定で割り当てられます。 さらに、カスタム ロールと関連する組み込みロール (Microsoft Entra リソースの種類に応じて) を、1 つの Microsoft Entra リソースのスコープで割り当てることもできます。 これにより、2 つ目のカスタム ロールを作成しなくても、1 つのアプリの資格情報と基本プロパティを更新するアクセス許可をユーザーに付与できます。

このセクションでは、アプリケーション登録スコープでロールを割り当てる方法について説明します。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[アプリケーション開発者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-developer)としてサインインします。
2. **Entra ID**&gt;**アプリ登録**に移動します。
3. アプリケーションを選択します。 検索ボックスを使用して、目的のアプリを見つけることができます。

    テナント内のアプリ登録の完全な一覧を表示するには、[すべてのアプリケーションを選択する必要がある場合があります。

    [Image: Microsoft Entra ID のアプリ登録のスクリーンショット。]
4. 左側のナビゲーション メニューから [ **ロールと管理者** ] を選択すると、アプリの登録で割り当てることができるすべてのロールの一覧が表示されます。

    [Image: Microsoft Entra ID でのアプリ登録のロールのスクリーンショット。]
5. 目的のロールを選択します。

    ヒント

    Microsoft Entra の組み込みロールまたはカスタム ロールの一覧はここには表示されません。 これは想定されています。 アプリの登録の管理にのみ関連するアクセス許可を持つロールが表示されます。
6. [ **割り当ての追加]** を選択し、このロールを割り当てるユーザーまたはグループを選択します。

    [Image: Microsoft Entra ID でのアプリ登録をスコープとするロール割り当てを追加するスクリーンショット。]
7. [ **追加]** を選択して、アプリの登録をスコープにしたロールを割り当てます。

## [PowerShell](#tab/ms-powershell)
PowerShell を使用してアプリケーション スコープで Microsoft Entra ロールを割り当てるには、次の手順に従います。

1. PowerShell ウィンドウを開きます。 必要に応じて、 [Install-Module](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) を使用して Microsoft Graph PowerShell をインストールします。 詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
2. PowerShell ウィンドウで、 [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) を使用してテナントにサインインします。

    ```powershell
    Connect-MgGraph -Scopes "Application.Read.All","RoleManagement.Read.Directory","User.Read.All","RoleManagement.ReadWrite.Directory"
    ```
3. [Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser) を使用してユーザーを取得します。

    ```powershell
    $user = Get-MgUser -Filter "userPrincipalName eq 'alice@contoso.com'"
    ```

    ユーザーではなくサービス プリンシパルにロールを割り当てるには、 [Get-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/Microsoft.Graph.Applications/Get-MgServicePrincipal) コマンドを使用します。
4. [Get-MgRoleManagementDirectoryRoleDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroledefinition) を使用して、割り当てるロールを取得します。

    ```powershell
    $roleDefinition = Get-MgRoleManagementDirectoryRoleDefinition `
       -Filter "displayName eq 'Application Administrator'"
    ```
5. [Get-MgApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgapplication) を使用して、ロールの割り当てのスコープを設定するアプリの登録を取得します。

    ```powershell
    $appRegistration = Get-MgApplication -Filter "displayName eq 'f/128 Filter Photos'"
    $directoryScope = '/' + $appRegistration.Id
    ```
6. [New-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/new-mgrolemanagementdirectoryroleassignment) を使用してロールを割り当てます。

    ```powershell
    $roleAssignment = New-MgRoleManagementDirectoryRoleAssignment `
       -DirectoryScopeId $directoryScope -PrincipalId $user.Id `
       -RoleDefinitionId $roleDefinition.Id 
    ```

## [Graph API](#tab/ms-graph)
[Graph Explorer](https://aka.ms/ge) で Microsoft Graph API を使用して、アプリケーション スコープでロールを割り当てるには、次の手順に従います。

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. [List users](https://learn.microsoft.com/ja-jp/graph/api/user-list) API を使用してユーザーを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/users?$filter=userPrincipalName eq 'alice@contoso.com'
    ```
3. [List unifiedRoleDefinitions](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roledefinitions) API を使用して、割り当てるロールを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/rolemanagement/directory/roleDefinitions?$filter=displayName eq 'Application Administrator'
    ```
4. [List applications](https://learn.microsoft.com/ja-jp/graph/api/application-list) API を使用して、ロールの割り当てのスコープを設定するアプリケーションを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/applications?$filter=displayName eq 'f/128 Filter Photos'
    ```
5. [Create unifiedRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments) API を使用してロールを割り当てます。

    ```http
    POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments
    
    {
        "@odata.type": "#microsoft.graph.unifiedRoleAssignment",
        "principalId": "<Object ID of user>",
        "roleDefinitionId": "<ID of role definition>",
        "directoryScopeId": "/<Object ID of app registration>"
    }
    ```

    応答

    ```http
    HTTP/1.1 201 Created
    ```

    手記

    この例では、管理単位セクションとは異なり、`directoryScopeId` は `/<ID>`として指定されています。 これは意図的な設計です。 `/<ID>` のスコープは、プリンシパルがその Microsoft Entra オブジェクトを管理できることを意味します。 スコープ `/administrativeUnits/<ID>` は、プリンシパルが管理単位自体ではなく、(プリンシパルが割り当てられているロールに基づいて) 管理単位のメンバーを管理できることを意味します。

---

### 管理単位スコープでロールを割り当てる

Microsoft Entra ID では、より詳細な管理制御のために、1 つ以上の [管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)に制限されたスコープを持つ Microsoft Entra ロールを割り当てることができます。 管理単位のスコープで Microsoft Entra ロールが割り当てられている場合、役割のアクセス許可は管理単位自体のメンバーを管理する場合にのみ適用され、テナント全体の設定や構成には適用されません。

たとえば、管理単位のスコープでグループ管理者ロールが割り当てられている管理者は、管理単位のメンバーであるグループを管理できますが、テナント内の他のグループを管理することはできません。 また、有効期限やグループの名前付けポリシーなど、グループに関連するテナント レベルの設定を管理することもできません。

このセクションでは、管理単位スコープで Microsoft Entra ロールを割り当てる方法について説明します。

#### 前提 条件

- 各管理単位管理者の Microsoft Entra ID P1 または P2 ライセンス
- 管理単位メンバー向けの Microsoft Entra ID 無料ライセンス
- 特権ロール管理者
- PowerShell を使用する場合の Microsoft Graph PowerShell モジュール
- Microsoft Graph API 用 Graph エクスプローラーを使用する場合の管理者の同意

詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

#### 管理単位スコープで割り当てることができるロール

次の Microsoft Entra ロールは、管理単位スコープで割り当てることができます。 さらに、 [カスタム ロールのアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create) にユーザー、グループ、またはデバイスに関連するアクセス許可が少なくとも 1 つ含まれている限り、任意のカスタム ロールを管理単位スコープで割り当てることができます。

| 役割 | 説明 |
| --- | --- |
| [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | 割り当てられた管理単位でのみ、管理者以外のユーザーの認証方法情報を表示、設定、リセットするためのアクセス権を持ちます。 |
| [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) | 管理単位内のユーザーまたはサービス プリンシパルに対してのみ、カスタム セキュリティ属性の割り当てを (任意の属性セットから) 読み取って更新できます。 |
| [アトリビュート割り当てビューア](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader) | 管理単位内のユーザーまたはサービス プリンシパルのカスタム セキュリティ属性を (任意の属性セットから) 読み取ることができます。 |
| [クラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) | Microsoft Entra ID でデバイスを管理するための制限付きアクセス。 |
| [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) | 割り当てられた管理単位内のグループのすべての側面のみを管理できます。 |
| [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) | 割り当てられた管理単位でのみ、管理者以外のパスワードをリセットできます。 |
| [ライセンス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#license-administrator) | 管理単位内でのみ、ライセンスの割り当てを割り当て、削除、および更新できます。 |
| [パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#password-administrator) | 割り当てられた管理単位内の管理者以外のパスワードのみをリセットできます。 |
| [プリンター管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#printer-administrator) | プリンターとプリンター コネクタを管理できます。 詳細については、「 [ユニバーサル印刷でのプリンターの管理の委任](https://learn.microsoft.com/ja-jp/universal-print/portal/delegated-admin#scoped-admin-vs-tenant-printer-admin)」を参照してください。 |
| [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) | 任意のユーザー (管理者または非管理者を含む) の認証方法の情報を表示、設定、リセットするためにアクセスできます。 |
| [SharePoint 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#sharepoint-administrator) | 割り当てられた管理単位でのみ、Microsoft 365 グループを管理できます。 管理単位の Microsoft 365 グループに関連付けられている SharePoint サイトの場合、Microsoft 365 管理センターを使用してサイトのプロパティ (サイト名、URL、外部共有ポリシー) を更新することもできます。 SharePoint 管理センターまたは SharePoint API を使用してサイトを管理することはできません。 |
| [Teams 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-administrator) | 割り当てられた管理単位でのみ、Microsoft 365 グループを管理できます。 割り当てられた管理単位内のグループに関連付けられているチームの Microsoft 365 管理センターでチーム メンバーを管理できます。 Teams 管理センターを使用できません。 |
| [Teams デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#teams-devices-administrator) | Teams 認定デバイスで管理関連のタスクを実行できます。 |
| [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | 割り当てられた管理単位内の制限付き管理者のパスワードのリセットなど、ユーザーとグループのすべての側面を管理できます。 現在、ユーザーのプロフィール写真を管理することはできません。 |
| [&lt;カスタム ロール&gt;](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create) | カスタム ロールの定義に従って、ユーザー、グループ、またはデバイスに適用されるアクションを実行できます。 |

特定のロールのアクセス許可は、管理単位のスコープで割り当てられている場合、管理者以外のユーザーにのみ適用されます。 言い換えると、ヘルプデスク管理者は、管理単位のユーザーが [管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator) ロールを持っていない場合にのみ、管理単位のユーザーのパスワードをリセットできます。 アクションのターゲットが別のロールまたは管理者のユーザーである場合、次のアクセス許可が制限されます。

- ユーザー認証方法の読み取りと変更
- ユーザー パスワードのリセット
- 電話番号、連絡用メール アドレス、Open Authorization (OAuth) 秘密鍵などの機密性の高いユーザー プロパティを変更する
- ユーザー アカウントを削除または復元する

#### 管理単位スコープで割り当てることができるセキュリティ プリンシパル

次のセキュリティ プリンシパルは、管理単位スコープを持つロールに割り当てることができます。

- ユーザー
- Microsoft Entra のロール割り当て可能グループ
- サービス プリンシパル

#### サービス プリンシパルとゲスト ユーザー

サービス プリンシパルとゲスト ユーザーは、オブジェクトの読み取りに対応するアクセス許可も割り当てられない限り、管理単位をスコープとしたロールの割り当てを使用できません。 これは、サービス プリンシパルとゲスト ユーザーは、管理アクションを実行するために必要なディレクトリ読み取りアクセス許可を既定で受け取らないためです。 サービス プリンシパルまたはゲスト ユーザーが管理単位をスコープとするロールの割り当てを使用できるようにするには、テナント スコープで [ディレクトリ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#directory-readers) ロール (または読み取りアクセス許可を含む別のロール) を割り当てる必要があります。

現在、管理単位をスコープにしたディレクトリ読み取りアクセス許可を割り当てることはできません。 ユーザーの既定のアクセス許可の詳細については、「既定のユーザーアクセス許可 」を参照してください。

#### 管理単位スコープでロールを割り当てる

このセクションでは、管理単位スコープでロールを割り当てる方法について説明します。

## [管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインします。
2. **Entra ID**&gt;**役割と管理者**&gt;**管理単位**を閲覧する。
3. 管理単位を選択します。

    [Image: Microsoft Entra ID の管理単位のスクリーンショット。]
4. 左側のナビゲーション メニューから [ **ロールと管理者** ] を選択すると、管理単位で割り当てることができるすべてのロールの一覧が表示されます。

    [Image: Microsoft Entra ID の管理単位の下にある [ロールと管理者] メニューのスクリーンショット。]
5. 目的のロールを選択します。

    ヒント

    Microsoft Entra の組み込みロールまたはカスタム ロールの一覧はここには表示されません。 これは想定されています。 管理単位内でサポートされているオブジェクトに関連するアクセス許可を持つロールを示します。 管理単位内でサポートされているオブジェクトの一覧については、「Microsoft Entra IDの管理単位 参照してください。
6. [ **割り当ての追加]** を選択し、このロールを割り当てるユーザーまたはグループを選択します。
7. [ **追加]** を選択して、管理単位にスコープが設定されたロールを割り当てます。

## [PowerShell](#tab/ms-powershell)
PowerShell を使用して管理単位スコープで Microsoft Entra ロールを割り当てるには、次の手順に従います。

1. PowerShell ウィンドウを開きます。 必要に応じて、 [Install-Module](https://learn.microsoft.com/ja-jp/powershell/module/powershellget/install-module) を使用して Microsoft Graph PowerShell をインストールします。 詳細については、「powerShell または Graph Explorerを使用するための 前提条件」を参照してください。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
2. PowerShell ウィンドウで、 [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-connect-mggraph) を使用してテナントにサインインします。

    ```powershell
    Connect-MgGraph -Scopes "Directory.Read.All","RoleManagement.Read.Directory","User.Read.All","RoleManagement.ReadWrite.Directory"
    ```
3. [Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser) を使用してユーザーを取得します。

    ```powershell
    $user = Get-MgUser -Filter "userPrincipalName eq 'alice@contoso.com'"
    ```
4. [Get-MgRoleManagementDirectoryRoleDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/get-mgrolemanagementdirectoryroledefinition) を使用して、割り当てるロールを取得します。

    ```powershell
    $roleDefinition = Get-MgRoleManagementDirectoryRoleDefinition `
       -Filter "displayName eq 'User Administrator'"
    ```
5. [Get-MgDirectoryAdministrativeUnit](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.directorymanagement/get-mgdirectoryadministrativeunit) を使用して、ロールの割り当てのスコープを設定する管理単位を取得します。

    ```powershell
    $adminUnit = Get-MgDirectoryAdministrativeUnit -Filter "displayName eq 'Seattle Admin Unit'"
    $directoryScope = '/administrativeUnits/' + $adminUnit.Id
    ```
6. [New-MgRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.governance/new-mgrolemanagementdirectoryroleassignment) を使用してロールを割り当てます。

    ```powershell
    $roleAssignment = New-MgRoleManagementDirectoryRoleAssignment `
       -DirectoryScopeId $directoryScope -PrincipalId $user.Id `
       -RoleDefinitionId $roleDefinition.Id
    ```

## [Graph API](#tab/ms-graph)
[Graph エクスプローラー](https://aka.ms/ge)で Microsoft Graph API を使用して、管理単位スコープでロールを割り当てるには、次の手順に従います。

##### Create unifiedRoleAssignment API を使用してロールを割り当てる

1. [Graph エクスプローラー](https://aka.ms/ge)にサインインします。
2. [List users](https://learn.microsoft.com/ja-jp/graph/api/user-list) API を使用してユーザーを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/users?$filter=userPrincipalName eq 'alice@contoso.com'
    ```
3. [List unifiedRoleDefinitions](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roledefinitions) API を使用して、割り当てるロールを取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/rolemanagement/directory/roleDefinitions?$filter=displayName eq 'User Administrator'
    ```
4. [List administrativeUnits](https://learn.microsoft.com/ja-jp/graph/api/directory-list-administrativeunits) API を使用して、ロールの割り当てのスコープを設定する管理単位を取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/directory/administrativeUnits?$filter=displayName eq 'Seattle Admin Unit'
    ```
5. [Create unifiedRoleAssignment](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-post-roleassignments) API を使用してロールを割り当てます。

    ```http
    POST https://graph.microsoft.com/v1.0/roleManagement/directory/roleAssignments
    {
        "@odata.type": "#microsoft.graph.unifiedRoleAssignment",
        "principalId": "<Object ID of user>",
        "roleDefinitionId": "<ID of role definition>",
        "directoryScopeId": "/administrativeUnits/<Object ID of administrative unit>"
    }
    ```

    応答

    ```http
    HTTP/1.1 201 Created
    ```

    ロールがサポートされていない場合、応答は不適切な要求です。

    ```http
    HTTP/1.1 400 Bad Request
    {
        "odata.error":
        {
            "code":"Request_BadRequest",
            "message":
            {
                "message":"The given built-in role is not supported to be assigned to a single resource scope."
            }
        }
    }
    ```

    手記

    この例では、`directoryScopeId` は `/administrativeUnits/<ID>`ではなく `/<ID>`として指定されています。 これは意図的な設計です。 スコープ `/administrativeUnits/<ID>` は、プリンシパルが管理単位自体ではなく、(プリンシパルが割り当てられているロールに基づいて) 管理単位のメンバーを管理できることを意味します。 `/<ID>` のスコープは、プリンシパルがその Microsoft Entra オブジェクト自体を管理できることを意味します。 アプリ登録セクションでは、アプリの登録をスコープとするロールによってオブジェクト自体を管理する権限が付与されるため、スコープが `/<ID>` されていることがわかります。

##### scopedRoleMember API の追加を使用したロールの割り当て

または、 [scopedRoleMember](https://learn.microsoft.com/ja-jp/graph/api/administrativeunit-post-scopedrolemembers) API を使用して、管理単位スコープを持つロールを割り当てることができます。

依頼

```http
POST /directory/administrativeUnits/{admin-unit-id}/scopedRoleMembers
```

本文

```http
{
  "roleId": "roleId-value",
  "roleMemberInfo": {
    "id": "id-value"
  }
}
```

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/my-staff-configure"} -->
## マイ スタッフを使用してユーザー管理を委任する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/my-staff-configure
- Service: entra-id / role-based-access-control
- Article date: 2026-09-04
- Summary: マイ スタッフと管理単位を使用して、ユーザーの管理を委任します

マイ スタッフを使用すると、ストア マネージャーやチーム リーダーなどの権限のある人に、スタッフ メンバーが Microsoft Entra アカウントにアクセスできるようにするためのアクセス許可を委任することができます。 組織では、パスワードのリセットや電話番号の変更などの一般的なタスクを、中央のヘルプデスクに頼るのではなく、現場のチーム マネージャーに任せることができます。 マイ スタッフを使用すると、自分のアカウントにアクセスできないユーザーは、数回のクリックでアクセスを回復することができ、ヘルプデスクや IT スタッフは必要ありません。

組織のためにマイ スタッフを構成する前に、このドキュメントと[ユーザー ドキュメント](https://support.microsoft.com/account-billing/manage-front-line-users-with-my-staff-c65b9673-7e1c-4ad6-812b-1a31ce4460bd)を確認し、この機能と、それがユーザーに及ぼす影響を、理解することをお勧めします。 ユーザーのドキュメントを利用して、新しいエクスペリエンスに備えてユーザーを訓練し、準備することで、ロールアウトを成功させることができます。

### マイ スタッフのしくみ

マイ スタッフの基になっている管理単位は、ロール割り当ての管理制御の範囲を制限するために使用できるリソースのコンテナーです。 詳細については、「[Microsoft Entra ID の管理単位の管理](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)」を参照してください。 マイ スタッフでは、店舗や部署のユーザーのグループで管理単位を構成できます。 これにより、1 つまたは複数の単位のスコープの管理者ロールにチーム マネージャーを割り当てることができます。

### はじめに

この記事の手順を完了するには、次のリソースと特権が必要です。

- 有効な Azure サブスクリプション。

    - Azure サブスクリプションをお持ちでない場合は、[アカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)してください。
- サブスクリプションに関連付けられている Microsoft Entra テナント。

    - 必要に応じて、[Microsoft Entra テナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/sign-up-organization)するか、[ご利用のアカウントに Azure サブスクリプションを関連付けます](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-subscriptions-associated-directory)。
- SMS ベースの認証を有効にするには、Microsoft Entra テナントの*認証ポリシー管理者*特権が必要です。
- テキスト メッセージ認証方法ポリシーで有効になっている各ユーザーは、その方法を使用しない場合でも、ライセンスを取得している必要があります。 有効な各ユーザーは、次の Microsoft Entra ID または Microsoft 365 ライセンスのいずれかを保持している必要があります。

    - [Microsoft Entra ID P1 または P2](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)
    - [Microsoft 365 F1 または F3](https://www.microsoft.com/licensing/news/m365-firstline-workers)
    - [Enterprise Mobility + Security (EMS) E3 または E5](https://www.microsoft.com/microsoft-365/enterprise-mobility-security/compare-plans-and-pricing) または [Microsoft 365 E3 または E5](https://www.microsoft.com/microsoft-365/compare-microsoft-365-enterprise-plans)

### マイ スタッフにアクセスできるユーザー

マイ スタッフへのアクセスは、管理者ロールの割り当てによって決まります。 管理ロールが割り当てられているユーザーのみがマイ スタッフにアクセスでき、そのロールの割り当ての管理単位スコープによって、管理できるユーザーが決まります。 管理単位を構成し、ロールを割り当てた後、それらのユーザーは https://mystaff.microsoft.com でマイ スタッフにサインインできます。

Note

管理者などの従来のマイ アプリとマイ スタッフエクスペリエンスの設定は、**マイ スタッフにアクセスでき**なくなり、サービスによって使用されなくなり、ユーザーの動作には影響しません。 これらの設定は、Microsoft Entra 管理センターから削除されます。 管理者によるアクションは必要ありません。

### 条件付きアクセス

Microsoft Entra 条件付きアクセス ポリシーを使用して、マイ スタッフ ポータルを保護することができます。 これは、マイ スタッフにアクセスする前に多要素認証を要求するなどのタスクに使用します。

Microsoft では、[Microsoft Entra 条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/)を使用してマイ スタッフを保護することを強くお勧めします。 マイ スタッフに条件付きアクセス ポリシーを適用するには、まずマイ スタッフ サイトに 1 回アクセスする必要があります。その際、条件付きアクセスで使用するためにテナントのサービス プリンシパルを自動的にプロビジョニングするのに数分かかります。

マイ スタッフ クラウド アプリケーションに適用する条件付きアクセス ポリシーを作成すると、サービス プリンシパルが表示されます。

[Image: マイ スタッフ アプリの条件付きアクセス ポリシーを作成する]

### マイ スタッフの使用

ユーザーがマイ スタッフを選択すると、自分が管理者アクセス許可を持つ[管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)の名前が表示されます。 [マイ スタッフのユーザー ドキュメント](https://support.microsoft.com/account-billing/manage-front-line-users-with-my-staff-c65b9673-7e1c-4ad6-812b-1a31ce4460bd)では、"場所" という用語を使用して管理単位が示されています。 管理者のアクセス許可に管理単位スコープがない場合は、組織全体にアクセス許可が適用されます。

管理ロールが割り当てられているユーザーは、 https://mystaff.microsoft.comを介してマイ スタッフにアクセスできます。 管理単位を選択してその管理単位内のユーザーを表示したり、ユーザーを選択してプロファイルを開いたりすることができます。

#### 制限

マイ スタッフには、管理単位ごとに最大 999 人のユーザーが表示されます。

### ユーザーのパスワードのリセット

オンプレミスのユーザーのパスワードをリセットする前に、次の前提条件を満たす必要があります。 詳細な手順については、「[セルフサービス パスワード リセットを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr-writeback)」のチュートリアルを参照してください。

- パスワード ライトバックのアクセス許可を構成する
- Microsoft Entra Connect でパスワード ライトバックを有効にします。
- Microsoft Entra セルフサービス パスワード リセット (SSPR) でパスワード ライトバックを有効にする

次のロールには、ユーザーのパスワードをリセットするアクセス許可があります。

- [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)
- [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)
- [ヘルプデスク管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#helpdesk-administrator)
- [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)
- [パスワード管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#password-administrator)

**[マイ スタッフ]** で、ユーザーのプロファイルを開きます。 **[パスワードのリセット]** を選択します。

- ユーザーがクラウドのみの場合は、ユーザーに提供できる一時的なパスワードを確認できます。
- ユーザーがオンプレミスの Active Directory から同期されている場合は、オンプレミスのドメイン ポリシーを満たすパスワードを入力できます。 その後、そのパスワードをユーザーに提供できます。

    [Image: パスワード リセット進行状況インジケーターと成功通知]

ユーザーは、次回サインインする際にパスワードを変更する必要があります。

### 電話番号を管理する

**[マイ スタッフ]** で、ユーザーのプロファイルを開きます。

- ユーザーに電話番号を追加するには、 **[電話番号の追加]** セクションを選択します
- 電話番号を変更するには、 **[電話番号の編集]** を選択します
- ユーザーの電話番号を削除するには、 **[電話番号の削除]** を選択します

設定に応じて、ユーザーは、設定した電話番号を使用して SMS でサインインし、多要素認証を実行して、セルフサービス パスワード リセットを実行できます。

ユーザーの電話番号を管理するには、次のいずれかのロールが割り当てられている必要があります。

- [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator)
- [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator)

### QR コード認証を管理する

**マイ スタッフ**を使用して、ユーザーの QR コード認証方法を管理できます。

#### マイ スタッフでユーザーの QR コード認証方法を追加する

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

#### マイ スタッフのユーザーの QR コード認証方法を削除する

1. QR コード認証方法自体を削除するには、[ **QR コードメソッドの削除**] をクリックします。

    [Image: マイ スタッフの QR コード認証方法を削除する方法を示すスクリーンショット。]
2. [ **削除** ] をクリックしてアクションを確定します。

    [Image: マイ スタッフの QR コード認証方法の削除を確認する方法を示すスクリーンショット。]

### Search

マイ スタッフの検索バーを使用して、組織内の管理単位やユーザーを検索できます。 組織内のすべての管理単位やユーザーを検索できますが、変更できるのは、自分が管理者アクセス許可を付与されている管理単位内のユーザーだけです。

### 監査ログ

Microsoft Entra 管理センターでは、マイ スタッフにおいて行われた操作の監査ログを見ることができます。 マイ スタッフで行われた操作によって監査ログが生成された場合、監査イベントの [ADDITIONAL DETAILS](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/追加の詳細) の下にこのことが示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/permissions-reference"} -->
## 組み込みロールのMicrosoft Entra - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference
- Service: entra-id / role-based-access-control
- Article date: 2026-07-17
- Summary: グローバル管理者からレポート閲覧者まで、各Microsoft Entra組み込みロールでできることについて説明します。 ロールの説明、アクセス許可、テンプレート ID を検索します。

Microsoft Entra ID では、別の管理者または管理者以外の管理者が Microsoft Entra リソースを管理する必要がある場合は、必要なアクセス許可を提供する Microsoft Entra ロールを割り当てます。 たとえば、ユーザーの追加または変更、ユーザーのパスワードのリセット、ユーザーのライセンスの管理、ドメイン名の管理を行えるように、ロールを割り当てることができます。

この記事では、Microsoft Entra リソースを管理できるようにするために割り当てることができる、Microsoft Entra の組み込みロールについて説明します。 ロールを割り当てる方法については、「[Microsoft Entra ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal)割り当てる」を参照してください。 Azure リソースを管理するロールをお探しの場合は、「[Azure 組み込み ロール](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/built-in-roles)」を参照してください。

この記事では、 **組み込み** ロールで使用されるアクセス許可の一覧を示します。 現在、 **カスタム** ロールで使用できるのは、これらのアクセス許可のサブセットのみです。 カスタム ロールの作成の詳細については、「[Microsoft Entra IDでのカスタム ロールの作成](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/custom-create)」を参照してください。

### すべてのロール

| Role | Description | テンプレート ID |
| --- | --- | --- |
| エージェントID管理者 | エージェント ブループリントの ID ライフサイクル操作、エージェント ID ブループリント プリンシパル、エージェント ID、エージェント ユーザーなど、テナント内のエージェントのすべての側面を管理します。[Image: 特権ラベル アイコン。] | DB506228-D27E-4B7D-95E5-295956D6615f |
| エージェントIDデベロッパー | テナントにエージェント ID ブループリントとそのエージェント ID ブループリント プリンシパルを作成します。 作成されたエージェント ID ブループリントとそのエージェント ID ブループリント プリンシパルの所有者としてユーザーが追加されます。 | ADB2368D-A9BE-41B5-8667-D96778E081B0 |
| エージェント・レジストリ管理者 | Microsoft Entra IDにおけるエージェントレジストリサービスのすべての側面を管理 | 6b942400-691f-4bf0-9d12-d8a254a2baf5 |
| AI 管理者 | Microsoft 365でMicrosoft Copilotおよび AI 関連のエンタープライズ サービスのすべての側面を管理します。[Image: 特権ラベル アイコン。] | d2562ede-74db-457e-a7b6-544e236ebb61 |
| AI リーダー | Microsoft 365のMicrosoft Copilotおよび AI 関連のエンタープライズ サービスのすべての側面を読み取る。[Image: 特権ラベル アイコン。] | 1fe13547-53f6-408d-ac04-7f8eed167b38 |
| アプリケーション管理者 | アプリ登録とエンタープライズ アプリのすべての側面を作成して管理できます。[Image: 特権ラベル アイコン。] | 9b895d92-2cd3-44c7-9d02-a6ac2d5ea5c3 |
| アプリケーション開発者 | [ユーザーはアプリケーションを登録できる] の設定とは無関係にアプリケーション登録を作成できます。[Image: 特権ラベル アイコン。] | cf1c38e5-3621-4004-a7cb-879624dced7c |
| 攻撃のペイロードの作成者 | 管理者が後で開始できる攻撃のペイロードを作成できます。 | 9c6df0f2-1e7c-4dc3-b195-66dfbd24aa8f |
| 攻撃のシミュレーションの管理者 | 攻撃のシミュレーション キャンペーンのすべての側面を作成および管理できます。 | c430b396-e693-46cc-96f3-db01bf8bb62a |
| 属性割り当て管理者 | サポートされている Microsoft Entra オブジェクトにカスタム セキュリティ属性のキーと値を割り当てます。 | 58a13ea3-c632-46ae-9ee0-9c0d43cd7f3d |
| 属性割り当て閲覧者 | サポートされている Microsoft Entra オブジェクトのカスタム セキュリティ属性のキーと値を読み取ります。 | ffd52fa5-98dc-465c-991d-fc073eb59f8f |
| 属性定義管理者 | カスタム セキュリティ属性を定義して管理します。 | 8424c6f0-a189-499e-bbd0-26c1753c96d4 |
| 属性定義閲覧者 | カスタム セキュリティ属性の定義を読み取ります。 | 1d336d2c-4ae8-42ef-9711-b3604ce3fc2c |
| 属性ログ管理者 | 監査ログを読み取り、カスタム セキュリティ属性に関連するイベントの診断設定を構成します。 | 5b784334-f94b-471a-a387-e7219fc49ca2 |
| 属性ログ閲覧者 | カスタム セキュリティ属性に関連する監査ログを読み取ります。 | 9c99539d-8186-4804-835f-fd51ef9e2dcd |
| 属性プロビジョニング管理者 | アプリケーションのすべてのアクティブなカスタム セキュリティ属性のプロビジョニング構成を読み取って編集します。[Image: 特権ラベル アイコン。] | ecb2c6bf-0ab6-418e-bd87-7986f8d63bbe |
| 属性プロビジョニング 閲覧者 | アプリケーションのすべてのアクティブなカスタム セキュリティ属性のプロビジョニング構成を読み取ります。[Image: 特権ラベル アイコン。] | 422218e4-db15-4ef9-bbe0-8afb41546d79 |
| 認証管理者 | 管理者以外のユーザーの認証方法の情報を表示、設定、リセットするためにアクセスできます。[Image: 特権ラベル アイコン。] | c4e39bd9-1100-46d3-8c65-fb160da0071f |
| 認証拡張性の管理者 | カスタム認証拡張機能を作成および管理することで、ユーザーのサインインとサインアップのエクスペリエンスをカスタマイズします。[Image: 特権ラベル アイコン。] | 25a516ed-2fa0-40ea-a2d0-12923a21473a |
| 認証機能拡張パスワード管理者 | カスタム認証のパスワード送信イベントをトリガーします。[Image: 特権ラベル アイコン。] | 0b00bede-4072-4d22-b441-e7df02a1ef63 |
| 認証ポリシー管理者 | 認証方法のポリシー、テナント全体の MFA 設定、パスワード保護ポリシー、検証可能な資格情報を作成および管理できます。 | 0526716b-113d-4c15-b2c8-68e3c22b9f80 |
| Azure DevOps 管理者 | Azure DevOpsポリシーと設定を管理します。 | e3973bdf-4987-49ae-837a-ba8e231c7286 |
| Azure Information Protection 管理者 | Azure Information Protection 製品のすべての側面を管理できます。 | 7495fdc4-34c4-4d15-a289-98788ce399fd |
| B2C IEF キーセット管理者 | Identity Experience Framework (IEF) でフェデレーションおよび暗号化のシークレットを管理できます。[Image: 特権ラベル アイコン。] | aaf43236-0c0d-4d5f-883a-6955382ac081 |
| B2C IEF ポリシー管理者 | Identity Experience Framework (IEF) で信頼フレームワーク ポリシーを作成および管理できます。 | 3edaf663-341e-4475-9f94-5c398ef6c070 |
| 課金管理者 | 支払情報の更新など、よく利用する課金関連タスクを実行できます。 | b0f54661-2d74-4c50-afa3-1ec803f12efe |
| Cloud App Security 管理者 | Defender for Cloud Apps製品のすべての側面を管理します。 | 892c5842-a9a6-463a-8041-72aa08ca3cf6 |
| クラウド アプリケーション管理者 | アプリ登録とエンタープライズ アプリのすべての側面 (アプリ プロキシを除く) を作成して管理できます。[Image: 特権ラベル アイコン。] | 158c047a-c907-4556-b7ef-446551a6b5f7 |
| クラウド デバイス管理者 | Microsoft Entra ID でデバイスを管理するための制限付きアクセス。[Image: 特権ラベル アイコン。] | 7698a772-787b-4ac8-901f-60d6b08affd2 |
| コンプライアンス管理者 | Microsoft Entra ID および Microsoft 365 のコンプライアンスの構成とレポートを読み取り、管理できます。 | 17315797-102d-40b4-93e0-432062caca18 |
| コンプライアンス データ管理者 | コンプライアンス コンテンツを作成、管理します。 | e6d1a23a-da11-4be4-9570-befc86d067a7 |
| 条件付きアクセス管理者 | 条件付きアクセスの機能を管理できます。[Image: 特権ラベル アイコン。] | b1be1c3e-b65d-4f19-8427-f6fa0d97feb9 |
| 顧客代理管理者リレーションシップ管理者 | 顧客テナントの詳細な委任された管理者特権 (GDAP) リレーションシップのすべての側面を管理します。 | fc8ad4e2-40e4-4724-8317-bcda7503ecbf |
| カスタマー ロックボックス アクセス承認者 | Microsoft サポートがお客様の組織データにアクセスする要求を承認することができます。 | 5c4f9dcd-47dc-4cf7-8c9a-9e4207cbfc91 |
| デスクトップ Analytics 管理者 | デスクトップの管理ツールとサービスにアクセスして管理できます。 | 38a96431-2bdf-4b4c-8b6e-5d3d8abac1a4 |
| ディレクトリ 閲覧者 | 基本的なディレクトリ情報を読み取ることができます 通常、アプリケーションとゲストへのディレクトリ 読み取りアクセスを許可するために使用されます。 | 88d8e3e3-8f55-4a1e-953a-9b9898b8876b |
| ディレクトリ同期アカウント | Microsoft Entra Connect サービスでのみ使用されます。 | d29b2b05-8046-44ba-8758-1e26182fcf32 |
| ディレクトリ ライター | 基本的なディレクトリ情報の読み取りと書き込みを実行できます。 ユーザーではなく、アプリケーションへのアクセスを許可する場合。[Image: 特権ラベル アイコン。] | 9360feb5-f418-4baa-8175-e2a00bac4301 |
| ドメイン名管理者 | クラウドおよびオンプレミスのドメイン名を管理できます。[Image: 特権ラベル アイコン。] | 8329153b-31d0-4727-b945-745eb3bc5f31 |
| ドラゴン管理者 | Microsoft Dragon 管理センターのすべての側面を管理します。 | e93e3737-fa85-474a-aee4-7d3fb86510f3 |
| Dynamics 365 管理者 | Dynamics 365 製品のすべての側面を管理できます。 | 44367163-eba1-44c3-98af-f5787879f96a |
| Dynamics 365 Business Central 管理者 | Dynamics 365 Business Central 環境にアクセスし、すべての管理タスクを実行します。 | 963797fb-eb3b-4cde-8ce3-5878b3f32a3f |
| Edge 管理者 | Microsoft Edge のすべての側面を管理します。 | 3f1acade-1e04-4fbc-9b69-f0302cd84aef |
| Entra Backup Administrator | 復旧ジョブの作成やバックアップ スナップショットの管理など、Microsoft Entra Backup のすべての側面を管理します。 | b6a27b2b-f905-4b2e-81b5-0d90e0ef1fdb |
| Entra Backup Reader | すべてのプレビュー ジョブ、復旧ジョブ、バックアップ スナップショットの一覧表示、プレビュー ジョブの作成など、Microsoft Entra Backup のすべての側面を読み取ります。 | f42252d9-5400-4d7b-b9ef-cc582dbb8577 |
| Entra SOC Identity Responder | ユーザーの無効化、アクティブなサインイン セッションの取り消し、パスワードのリセットなど、SOC インシデント対応の ID 包含アクションを実行します。[Image: 特権ラベル アイコン。] | 58f930cc-fcf4-4152-852c-1d7dbf502139 |
| Exchange 管理者 | Exchange 製品のすべての側面を管理できます。 | 29232cdf-9323-42fd-ade2-1d097af3e4de |
| Exchange バックアップ管理者 | Microsoft 365 バックアップ での Exchange のコンテンツのバックアップと復元 (詳細な復元を含む) | 49eb8f75-97e9-4e37-9b2b-6c3ebfcffa31 |
| Exchange 受信者管理者 | Exchange Online 組織内で Exchange Online 受信者を作成または更新できます。 | 31392ffb-586c-42d1-9346-e59415a2cc4e |
| 拡張ディレクトリ ユーザー管理者 | Teams の拡張ディレクトリで外部ユーザー プロファイルのすべての側面を管理します。 | dd13091a-6207-4fc0-82ba-3641e056ab95 |
| 外部 ID ユーザー フロー管理者 | ユーザー フローのすべての側面を作成および管理できます。 | 6e591065-9bad-43ed-90f3-e9424366d2f0 |
| 外部 ID ユーザー フロー属性管理者 | すべてのユーザー フローに対して使用可能な属性スキーマを作成および管理できます。 | 0f971eea-41eb-4569-a71e-57bb8a3eff1e |
| 外部 ID プロバイダー管理者 | 直接フェデレーションで使用する ID プロバイダーを構成できます。[Image: 特権ラベル アイコン。] | be2f45a1-457d-42af-a067-6ec1fa63bc45 |
| ファブリック管理者 | FabricおよびPower BI製品のすべての側面を管理します。 | a9ea8996-122f-4c74-9520-8edcd192826c |
| グローバル管理者 | Microsoft Entra ID のすべての側面と、Microsoft Entra の ID が使用される Microsoft サービスを管理できます。[Image: 特権ラベル アイコン。] | 62e90394-69f5-4237-9190-012177145e10 |
| グローバル閲覧者 | グローバル管理者が読み取れるものすべての読み取りが可能ですが、更新することはできません。[Image: 特権ラベル アイコン。] | f2ef992c-3afb-46b9-b7cf-a126ee74c451 |
| Global Secure Access 管理者 | パブリックおよびプライベート エンドポイントへのアクセスの管理を含め、グローバル セキュリティで保護されたインターネット アクセスと Microsoft Global Secure Private Access のすべての側面を作成および管理します。 | ac434307-12b9-4fa1-a708-88bf58caabc1 |
| グローバル セキュリティで保護されたアクセス ログ リーダーの を する | 詳細な分析のために、指定されたセキュリティ担当者に Microsoft Entra Internet Access と Microsoft Entra Private Access のネットワーク トラフィック ログへの読み取り専用アクセスを提供します。 | 843318fb-79a6-4168-9e6f-aa9a07481cc4 |
| グループ管理者 | このロールのメンバーは、グループの作成と管理、名前付けと有効期限ポリシーなどのグループ設定の作成と管理、グループのアクティビティと監査レポートの表示を行うことができます。 | fdd7a751-b60b-444a-984c-02652fe8fa1c |
| ゲスト招待元 | 「メンバーはゲストを招待できます」の設定とは関係なく、ゲストユーザーを招待できます。 | 95e79109-95c0-4d8e-aee3-d01accf2d47b |
| ヘルプデスク管理者 | 管理者以外のユーザーとヘルプデスク管理者のパスワードをリセットできます。[Image: 特権ラベル アイコン。] | 729827e3-9c14-49f7-bb1b-9608f156bbb8 |
| ハイブリッド ID の管理者 | Active Directory を管理して、Microsoft Entra クラウド プロビジョニング、Microsoft Entra Connect、パススルー認証 (PTA)、パスワード ハッシュ同期 (PHS)、シームレス シングル サインオン (シームレス SSO)、フェデレーション設定を行います。 Microsoft Entra Connect Health を管理するアクセス権がありません。[Image: 特権ラベル アイコン。] | 8ac3fc64-6eca-42ea-9e69-59f4c7b60eb2 |
| Identity Governance 管理者 | ID ガバナンス シナリオで Microsoft Entra ID を使用してアクセスを管理します。[Image: 特権ラベル アイコン。] | 45d8d3c5-c802-45c6-b32a-1d70b5e1e86e |
| Insights 管理者 | Microsoft 365 Insights アプリへの管理アクセス権があります。 | eb1f4a8d-243a-41f0-9fbd-c7cdf6c5ef7c |
| 分析情報アナリスト | Microsoft Viva Insights の分析機能にアクセスし、カスタム クエリを実行します。 | 25df335f-86eb-4119-b717-0ff02de207e9 |
| Insights ビジネス リーダー | Microsoft Viva Insights アプリを使用してダッシュボードと分析情報を表示および共有します。 | 31e939ad-9672-4796-9c2e-873181342d2d |
| Intune 管理者 | Intune 製品のすべての側面を管理できます。[Image: 特権ラベル アイコン。] | 3a2c62db-5318-420d-8d74-23affee5d9d5 |
| IoT デバイス管理者 を する | 新しい IoT デバイスのプロビジョニング、ライフサイクルの管理、証明書の構成、デバイス テンプレートの管理を行います。 | 2ea5ce4c-b2d8-4668-bd81-3680bd2d227a |
| Kaizala 管理者 | Microsoft Kaizala の設定を管理できます。 | 74ef975b-6605-40af-a5d2-b9539d836353 |
| ナレッジ管理者 | 知識、学習、その他のインテリジェントな機能を構成できます。 | b5a8dcf3-09d5-43a9-a639-8e29ef291470 |
| ナレッジマネージャー | トピックと知識を整理、作成、管理、および昇格します。 | 744ec460-397e-42ad-a462-8b3f9747a02c |
| ライセンス管理者 | ユーザーおよびグループの製品ライセンスを管理できます。 | 4d6ac14f-3453-41d0-bef9-a3e0c569773a |
| ライフサイクル ワークフロー管理者 | Microsoft Entra ID のライフサイクル ワークフローに関連付けられているワークフローとタスクのすべての側面を作成および管理します。[Image: 特権ラベル アイコン。] | 59d46f88-662b-457b-bceb-5c3809e5908f |
| メッセージ センターのプライバシー閲覧者 | Office 365 メッセージ センター内でのみセキュリティ メッセージと更新情報を閲覧することができます。 | ac16e43d-7b2d-40e0-ac05-243ff356ab5b |
| メッセージ センター閲覧者 | Office 365 メッセージ センター内でのみ自分の組織のメッセージと更新情報を閲覧することができます。 | 790c1fb9-7f7d-4f88-86a1-ef1f95c05c1b |
| Microsoft 365 バックアップ管理者 | Microsoft 365 バックアップ でサポートされているサービス (SharePoint、OneDrive、Exchange Online) 間でコンテンツをバックアップおよび復元する | 1707125e-0aa2-4d4d-8655-a7c786c76a25 |
| Microsoft 365 移行管理者 | 移行マネージャーを使用してコンテンツを Microsoft 365 に移行するには、すべての移行機能を実行します。 | 8c8b803f-96e1-4129-9349-20738d9f9652 |
| Microsoft Entra 参加済みデバイスのローカル管理者 | このロールに割り当てられたユーザーは、Microsoft Entra 参加済みデバイスのローカル管理者グループに追加されます。 | 9f06204d-73c1-4d4c-880a-6edb90606fd8 |
| Microsoft Graph データ接続管理者 | テナント内の Microsoft Graph データ接続サービスの側面を管理します。 | ee67aa9c-e510-4759-b906-227085a7fd4d |
| Microsoft ハードウェア保証管理者 | Surface や HoloLens などの Microsoft 製ハードウェアに関するすべての側面の保証請求と権利を作成および管理します。 | 1501b917-7653-4ff9-a4b5-203eaf33784f |
| Microsoft ハードウェア保証スペシャリスト | Surface や HoloLens などの Microsoft 製ハードウェアの保証請求を作成して読み取ります。 | 281fe777-fb20-4fbb-b7a3-ccebce5b0d96 |
| ネットワーク管理者 | ネットワークの場所を管理し、Microsoft 365 SaaS アプリケーションのエンタープライズ ネットワーク設計の分析情報を確認できます。 | d37c8bed-0711-4417-ba38-b4abe66ce4c2 |
| Office アプリ管理者 | Office アプリのクラウド サービスを管理すること (ポリシーと設定の管理を含む) や、"新機能" のコンテンツを選択、選択解除、エンドユーザーのデバイスに公開する機能を管理できます。 | 2b745bdf-0803-4d80-aa65-822c4493daac |
| 組織ブランド化管理者 | テナントにおける組織のブランド化の全側面を管理します。 | 92ed04bf-c94a-4b82-9729-b799a7a4c178 |
| 組織データ ソース管理者 | Microsoft 365 への組織データの取り込みを設定および管理します。 | 9d70768a-0cbc-4b4c-aea3-2e124b2477f4 |
| 組織メッセージ承認者 | Microsoft 365 管理センターで配信する新しい組織メッセージがユーザーに送信される前に、確認、承認、または拒否します。 | e48398e2-f4bb-4074-8f31-4586725e205b |
| 組織メッセージ ライター | Microsoft 製品でエンド ユーザーに対して表示される組織のメッセージの書き込み、発行、管理、レビューを行います。 | 507f53e4-4e52-4077-abd3-d2e1558b6ea2 |
| パートナー レベル 1 のサポート | 使用しないでください。一般的な使用は想定されていません。[Image: 特権ラベル アイコン。] | 4ba39ca4-527c-499a-b93d-d9b492c50246 |
| パートナー レベル 2 のサポート | 使用しないでください。一般的な使用は想定されていません。[Image: 特権ラベル アイコン。] | e00e864a-17c5-4a4b-9c06-f5b95a8d5bd8 |
| パスワード管理者 | 管理者以外とパスワード管理者のパスワードをリセットできます。[Image: 特権ラベル アイコン。] | 966707d0-3269-4727-9be2-8c3a10f19b9d |
| 人事管理者 | 組織内のすべてのユーザーのユーザーとユーザー設定のプロファイル写真を管理します。 | 024906de-61e5-49c8-8572-40335f1e0e10 |
| Permissions Management の管理者 | Microsoft Entra Permissions Management のすべての側面を管理します。 | af78dc32-cf4d-46f9-ba4e-4428526346b5 |
| 管理者の配置 | Microsoft Places サービスのすべての側面を管理します。 | 78b0ccd1-afc2-4f92-9116-b41aedd09592 |
| Power Platform 管理者 | Microsoft Dynamics 365、Power Apps、Power Automateのすべての側面を管理します。 | 11648597-926c-4cf3-9c36-bcebb0ba8dcc |
| プリンター管理者 | プリンターとプリンター コネクタのすべての側面を管理できます。 | 644ef478-e28f-4e28-b9dc-3fdde9aa0b1f |
| プリンター技術者 | プリンターの登録と登録解除、またプリンターの状態の更新を行うことができます。 | e8cef6f1-e4bd-4ea8-bc07-4b8d950f4477 |
| 特権認証管理者 | 任意のユーザー (管理者でも管理者以外でも) の認証方法の情報を表示、設定、リセットするためにアクセスできます。[Image: 特権ラベル アイコン。] | 7be44c8a-adaf-4e2a-84d6-ab2649e08a13 |
| 特権ロール管理者 | Microsoft Entra ID でのロールの割り当てと、Privileged Identity Management のすべての側面を管理できます。[Image: 特権ラベル アイコン。] | e8611ab8-c189-46e8-94e1-60213ab1f814 |
| Purview ワークロード コンテンツ管理者 | Microsoft Purview ポータルからアクセスするときに、Microsoft 365からデータを管理または消去します。 | 3f04f91a-4ad7-4bd3-bcfa-49882ea1a88a |
| Purview ワークロード コンテンツ 閲覧者 | Microsoft Purview ポータルからアクセスするときに、Microsoft 365からデータを読み取る。 | e07494ad-1654-4dd2-922e-6f81a71bf00f |
| Purview ワークロード コンテンツ ライター | Microsoft Purview ポータルからアクセスするときに、Microsoft 365からデータを読み取り、編集します。 | 02d5655b-c1cf-4e5f-98da-5fb919085bf6 |
| レポート閲覧者 | サインインと監査のレポートを読み取ることができます。 | 4a5d8f65-41da-4de4-8968-e035b65339cf |
| 管理者の検索 | Microsoft Search 設定のすべての側面を作成および管理できます。 | 0964bb5e-9bdb-4d7b-ac29-58e794862a40 |
| 検索エディター | ブックマーク、Q&A、場所、フロアプランなどの編集コンテンツを作成および管理することができます。 | 8835291a-918c-4fd7-a9ce-faa49f0cf7d9 |
| セキュリティ管理者 | セキュリティ情報とレポートを読み取り、Microsoft Entra ID と Office 365 で構成を管理することができます。[Image: 特権ラベル アイコン。] | 194ae4cb-b126-40b2-bd5b-6091b380977d |
| セキュリティ オペレーター | セキュリティ イベントを作成および管理し、セキュリティ インシデント中に ID 封じ込めアクションを実行します。[Image: 特権ラベル アイコン。] | 5f2222b1-57c3-48ba-8ad5-d4759f1fde6f |
| セキュリティ閲覧者 | Microsoft Entra ID と Office 365 のセキュリティ情報とレポートを読み取ることができます。[Image: 特権ラベル アイコン。] | 5d6b6bb7-de71-4623-b4af-96380a352509 |
| サービス サポート管理者 | サービス正常性に関する情報を読み取り、サポート チケットを管理することができます。 | f023fd81-a637-4b56-95fd-791ac0226033 |
| SharePoint 管理者 | SharePoint サービスのすべての側面を管理できます。 | f28a1f50-f6e7-4571-818b-6a12f2af6b6c |
| SharePoint 高度な管理 管理者 | SharePoint 高度な管理のすべての側面を管理します。 | 99009C4a-3B3F-4957-82a9-9d35E12db77e |
| SharePoint バックアップ管理者 | Microsoft 365 バックアップ での SharePoint および OneDrive のコンテンツのバックアップと復元 (詳細な復元を含む) | 9d3e04ba-3ee4-4d1b-a3a7-9aef423a09be |
| SharePoint Embedded 管理者 | SharePoint Embedded コンテナーのすべての側面を管理します。 | 1a7d78b6-429f-476b-b8eb-35fb715fffd4 |
| Skype for Business 管理者 | Skype for Business 製品のすべての側面を管理できます。 | 75941009-915a-4869-abe7-691bff18279e |
| Teams 管理者 | Microsoft Teams サービスを管理できます。 | 69091246-20e8-4a56-aa4d-066075b2a7a8 |
| Teams 通信管理者 | Microsoft Teams サービス内での通話と会議の機能を管理できます。 | baf37b3a-610e-45da-9e62-d9d1e5e8914b |
| Teams コミュニケーション サポート エンジニア | 高度なツールを使用して、Teams 内の通信の問題のトラブルシューティングを行えます。 | f70938a0-fc10-4177-9e90-2178f8765737 |
| Teams コミュニケーション サポート スペシャリスト | 基本的なツールを使用して、Teams 内の通信の問題のトラブルシューティングを行えます。 | fcf91098-03e3-41a9-b5ba-6f0ec8188a12 |
| Teams デバイス管理者 | Teams 認定デバイスで管理関連タスクを実行できます。 | 3d762c5a-1b6c-493f-843e-55a3b42923d4 |
| Teams 外部コラボレーション管理者 | 外部ドメインの構成や、組織と対話できるグループとユーザーの制御など、Teams の外部コラボレーション ポリシーと設定を管理します。 | 2fe872fb-daa8-4afc-8f6c-53c4565cfef4 |
| Teams 閲覧者 | Teams 管理センターですべてを読み取りますが、何も更新しません。 | 1076ac91-f3d9-41a7-a339-dcdf5f480acc |
| Teams テレフォニー管理者 | 音声とテレフォニーの機能を管理し、Microsoft Teams サービス内の通信に関する問題のトラブルシューティングを行います。 | aa38014f-0993-46e9-9b45-30501a20909d |
| テナント作成者 | 新しい Microsoft Entra または Azure AD B2C テナントを作成します。 | 112ca1a2-15ad-4102-995e-45b0bc479a6a |
| テナント ガバナンス管理者 | Microsoft Entra テナント ガバナンス サービスのすべての機能を管理します。[Image: 特権ラベル アイコン。] | 1981f584-96e9-4a6f-95b0-f522373f8fae |
| テナント ガバナンス閲覧者 | すべてのテナント ガバナンス データを読み取ることができます。 | e0a4caa6-fe82-443f-b92f-d87341d17b2e |
| テナント ガバナンス関係管理者 | ガバナンス関係を開始し、それらを終了できます。 | b8e31d83-1534-480f-9b10-0338ded51b7e |
| テナント ガバナンス関係閲覧者 | テナント ガバナンスの関係と関連オブジェクトを読み取ることができます。 | 124577f8-48ed-456a-839f-13b419002e33 |
| 使用状況の概要のレポート閲覧者 | 使用状況レポートと導入スコアを読むことができますが、ユーザーの詳細にアクセスすることはできません。 | 75934031-6c7e-415a-99d7-48dbd49e875e |
| ユーザー管理者 | ユーザーとグループのすべての側面を、制限付きの管理者のパスワードをリセットすることも含めて、管理できます。[Image: 特権ラベル アイコン。] | fe930be7-5e62-47db-91af-98c3a49a38b1 |
| ユーザー エクスペリエンス サクセス マネージャー | 製品のフィードバック、アンケートの結果、およびレポートを表示して、トレーニングやコミュニケーションの機会を見つけます。 | 27460883-1df1-4691-b032-3b79643e5e63 |
| 仮想アクセス管理者 | 管理センターまたはVirtual VisitsアプリからVirtual Visitsの情報とメトリックを管理および共有する。 | e300d9e7-4a2b-4295-9eff-f1c78b36cc98 |
| Viva Glint テナント管理者 | Microsoft 365 管理センターで Microsoft Viva Glint 設定を管理および構成します。 | 0ec3f692-38d6-4d14-9e69-0377ca7797ad |
| Viva Goals 管理者 | Microsoft Viva Goals のあらゆる側面を管理および構成します。 | 92b086b3-e367-4ef2-b869-1de128fb986e |
| Viva Pulse 管理者 | Microsoft Viva Pulse アプリのすべての設定を管理できます。 | 87761b17-1ed2-4af3-9acd-92a150038160 |
| Windows 365 管理者 | クラウド PC のすべての側面をプロビジョニングして管理できます。 | 11451d60-acb2-45eb-a7d6-43d0f0125c13 |
| Windows Update デプロイ管理者 | Windows Update for Business のデプロイ サービスを使用して、Windows Update デプロイのすべての側面を作成および管理できます。 | 32696413-001a-46ae-978c-ce0f6b3620d2 |
| Yammer 管理者 | Yammer サービスのすべての側面を管理します。 | 810a2642-a034-447f-a5e8-41beaa378541 |

### エージェントID管理者

[Image: 特権ラベル アイコン。]

以下の作業が必要なユーザーにエージェントID管理者の役割を割り当てます:

- エージェント ID、エージェント ID ブループリント プリンシパル、エージェント ID ブループリント、およびテナント内のエージェント ユーザーの完全なライフサイクルを管理する
- 削除されたエージェント ID、エージェント ID ブループリント プリンシパル、エージェント ID ブループリント、およびエージェント ユーザーを完全に削除して復元する
- エージェント ユーザーのライセンスの管理、更新トークンの無効化、サインイン セッションの取り消し
- 監査ログとサインイン レポートのすべてのプロパティを読み取る
- 組織、ポリシー、外部ユーザー プロファイル、非表示のグループ メンバー、およびユーザーの一括ジョブの標準プロパティを読み取ります
- 所有者としてMicrosoft 365 グループを作成する
- サービスの正常性チケットとサポート チケットMicrosoft 365 Azureを読み取って構成する
- AzureとMicrosoft 365サービスの正常性とサポート チケットを作成および管理する

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/agentIdentities/appRoleAssignedTo/update | エージェント ID ロールの割り当てを更新する |
| microsoft.directory/agentIdentities/authentication/update | エージェント ID の認証を更新する |
| microsoft.directory/agentIdentities/basic/update | エージェント ID の基本プロパティを更新する |
| microsoft.directory/agentIdentities/create | エージェント ID の作成 |
| microsoft.directory/agentIdentities/delete | エージェント ID の削除 |
| microsoft.directory/agentIdentities/disable | エージェント ID を無効にする |
| microsoft.directory/agentIdentities/enable | エージェント ID を有効にする |
| microsoft.directory/agentIdentities/owners/update | エージェント ID の所有者を更新する |
| microsoft.directory/agentIdentities/tag/update | エージェント ID のタグを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/appRoleAssignedTo/update | エージェント ID ブループリント プリンシパルロールの割り当てを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/authentication/update | エージェント ID ブループリント プリンシパルの認証を更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/basic/update | エージェント ID ブループリント プリンシパルの基本プロパティを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/create | エージェント ID ブループリント プリンシパルを作成する |
| microsoft.directory/agentIdentityBlueprintPrincipals/delete | エージェント ID ブループリント プリンシパルを削除する |
| microsoft.directory/agentIdentityBlueprintPrincipals/disable | エージェント ID ブループリント プリンシパルを無効にする |
| microsoft.directory/agentIdentityBlueprintPrincipals/enable | エージェント ID ブループリント プリンシパルを有効にする |
| microsoft.directory/agentIdentityBlueprintPrincipals/owners/update | エージェント ID ブループリント プリンシパルの所有者を更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/tag/update | エージェント ID ブループリント プリンシパルのタグを更新する |
| microsoft.directory/agentIdentityBlueprints/allProperties/read | エージェント ID ブループリントのすべてのプロパティを読み取ります |
| microsoft.directory/agentIdentityBlueprints/allProperties/update | エージェント ID ブループリントのすべてのプロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentIdentityBlueprints/appRoles/update | エージェント ID ブループリントで appRoles を更新する |
| microsoft.directory/agentIdentityBlueprints/audience/update | エージェント ID ブループリントの対象ユーザーを更新する |
| microsoft.directory/agentIdentityBlueprints/authentication/update | エージェント ID ブループリントの認証を更新する |
| microsoft.directory/agentIdentityBlueprints/basic/update | エージェント ID ブループリントの基本プロパティを更新する |
| microsoft.directory/agentIdentityBlueprints/create | エージェント ID ブループリントを作成する |
| microsoft.directory/agentIdentityBlueprints/credentials/update | エージェント ID ブループリントの資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentIdentityBlueprints/delete | エージェント ID ブループリントを削除する |
| microsoft.directory/agentIdentityBlueprints/owners/update | エージェント ID ブループリントの所有者を更新する |
| microsoft.directory/agentIdentityBlueprints/permissions/update | エージェント ID ブループリントで公開されているアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/agentIdentityBlueprints/tag/update | エージェント ID ブループリントのタグを更新する |
| microsoft.directory/agentUsers/assignLicense | エージェント ユーザーに製品ライセンスを割り当てる |
| microsoft.directory/agentUsers/basic/update | エージェント ユーザーの基本プロパティ (表示名、ユーザーの種類、メールニックネームなど) を更新する |
| microsoft.directory/agentUsers/create | エージェント ユーザーの作成[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/delete | エージェントユーザーを削除してください[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/disable | エージェントユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/enable | エージェントユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/invalidateAllRefreshTokens | エージェントのユーザーリフレッシュトークンを無効化して強制サインアウト[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/lifeCycleInfo/read | employeeLeaveDateTimeのようなエージェントユーザーのライフサイクル情報を読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/lifeCycleInfo/update | employeeLeaveDateTimeのようなエージェントユーザーのライフサイクル情報を更新してください[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/manager/update | エージェントユーザー向けのアップデートマネージャー |
| microsoft.directory/agentUsers/photo/update | エージェントユーザーの写真更新 |
| microsoft.directory/agentUsers/reprocessLicenseAssignment | エージェントユーザー向けの再処理ライセンス割り当て |
| microsoft.directory/agentUsers/restore | 削除されたエージェントユーザーを復元する |
| microsoft.directory/agentUsers/revokeSignInSessions | エージェント ユーザーのサインイン セッションを取り消す |
| microsoft.directory/agentUsers/sponsors/update | エージェントユーザーのスポンサーを更新してください |
| microsoft.directory/agentUsers/usageLocation/update | エージェントユーザーの使用場所を更新する |
| microsoft.directory/agentUsers/userPrincipalName/update | エージェント ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/deletedItems.agentIdentities/delete | 復元できなくなったエージェント ID を完全に削除する |
| microsoft.directory/deletedItems.agentIdentities/restore | 論理的に削除されたエージェント ID を元の状態に復元する |
| microsoft.directory/deletedItems.agentIdentityBlueprintPrincipals/delete | 復元できなくなったエージェント ID ブループリント プリンシパルを完全に削除する |
| microsoft.directory/deletedItems.agentIdentityBlueprintPrincipals/restore | 論理的に削除されたエージェント ID ブループリント プリンシパルを元の状態に復元する |
| microsoft.directory/deletedItems.agentIdentityBlueprints/delete | エージェントの識別設計図は永久に削除し、復元できません |
| microsoft.directory/deletedItems.agentIdentityBlueprints/restore | 論理的に削除されたエージェント ID ブループリントを元の状態に復元する |
| microsoft.directory/externalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/groups.unified/createAsOwner | Microsoft 365グループを作成し、役割割り当て可能なグループは除外します。 作成者は最初の所有者として追加されます。 |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.directory/organization/standard/read | 組織で基本プロパティを読み取る |
| microsoft.directory/policies/standard/read | ポリシーの基本プロパティを読み取る |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |

### エージェントIDデベロッパー

以下の作業が必要なユーザーにエージェントID開発者の役割を割り当てます:

- テナントにエージェント ID ブループリントとそのエージェント ID ブループリント プリンシパルを作成します。 作成されたエージェント ID ブループリントとそのエージェント ID ブループリント プリンシパルの所有者としてユーザーが追加されます。

| Actions | Description |
| --- | --- |
| microsoft.directory/agentIdentityBlueprints/createAsOwner | エージェント ID ブループリントを作成し、作成者を最初の所有者として追加する |
| microsoft.directory/servicePrincipals/standard/read | サービス プリンシパルの基本プロパティを読み取る |

### エージェント・レジストリ管理者

以下のタスクを行う必要があるユーザーにエージェントレジストリ管理者の役割を割り当てます:

- Microsoft Entra IDでAIエージェントのメタデータ管理
- エージェントのコレクションと可視化管理
- エージェントレジストリ固有の役割を他のユーザーやエージェントに割り当ててレジストリにアクセスする

| Actions | Description |
| --- | --- |
| microsoft.agentRegistry/allEntities/allProperties/allTasks | Microsoft Entra IDでエージェントレジストリのすべての側面を管理 |

### AI 管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次のタスクを行う必要があるユーザーに、AI 管理者の役割を割り当てます。

- Microsoft Copilot のすべての側面を管理する
- Microsoft 365 管理センターの統合アプリ ページから、AI 関連のエンタープライズ サービス、拡張性、および Copilot エージェントを管理する
- Microsoft Entra ID で管理者の同意要求ポリシーを管理する
- 基幹業務 Copilot エージェントを承認して発行する
- アプリにアクセス許可が必要ない場合は、ユーザーがアプリをインストールするか、組織内のユーザー用のアプリをインストールすることを許可する
- Azure と Microsoft 365 Service Health のダッシュボードを読み取りおよび構成する
- 使用状況レポート、導入の分析情報、組織の分析情報を参照する
- Azure と Microsoft 365 管理センターでのサポート チケットの作成および管理
- エージェント ID、エージェント ID ブループリント、エージェント ID ブループリント プリンシパル、および削除済みアイテムの復元を含むエージェント ユーザーの完全なライフサイクルを管理する

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/adminConsentRequestPolicy/allProperties/allTasks | Microsoft Entra ID で管理者の同意要求ポリシーを管理する |
| microsoft.directory/agentIdentities/allProperties/read | エージェント ID のすべてのプロパティの読み取り |
| microsoft.directory/agentIdentities/appRoleAssignedTo/update | エージェント ID ロールの割り当てを更新する |
| microsoft.directory/agentIdentities/authentication/update | エージェント ID の認証を更新する |
| microsoft.directory/agentIdentities/basic/update | エージェント ID の基本プロパティを更新する |
| microsoft.directory/agentIdentities/create | エージェント ID の作成 |
| microsoft.directory/agentIdentities/delete | エージェント ID の削除 |
| microsoft.directory/agentIdentities/disable | エージェント ID を無効にする |
| microsoft.directory/agentIdentities/enable | エージェント ID を有効にする |
| microsoft.directory/agentIdentities/owners/update | エージェント ID の所有者を更新する |
| microsoft.directory/agentIdentities/tag/update | エージェント ID のタグを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/allProperties/read | エージェント ID ブループリント プリンシパルのすべてのプロパティを読み取ります |
| microsoft.directory/agentIdentityBlueprintPrincipals/appRoleAssignedTo/update | エージェント ID ブループリント プリンシパルロールの割り当てを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/authentication/update | エージェント ID ブループリント プリンシパルの認証を更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/basic/update | エージェント ID ブループリント プリンシパルの基本プロパティを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/create | エージェント ID ブループリント プリンシパルを作成する |
| microsoft.directory/agentIdentityBlueprintPrincipals/delete | エージェント ID ブループリント プリンシパルを削除する |
| microsoft.directory/agentIdentityBlueprintPrincipals/disable | エージェント ID ブループリント プリンシパルを無効にする |
| microsoft.directory/agentIdentityBlueprintPrincipals/enable | エージェント ID ブループリント プリンシパルを有効にする |
| microsoft.directory/agentIdentityBlueprintPrincipals/owners/update | エージェント ID ブループリント プリンシパルの所有者を更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/tag/update | エージェント ID ブループリント プリンシパルのタグを更新する |
| microsoft.directory/agentIdentityBlueprints/allProperties/read | エージェント ID ブループリントのすべてのプロパティを読み取ります |
| microsoft.directory/agentIdentityBlueprints/allProperties/update | エージェント ID ブループリントのすべてのプロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentIdentityBlueprints/appRoles/update | エージェント ID ブループリントで appRoles を更新する |
| microsoft.directory/agentIdentityBlueprints/audience/update | エージェント ID ブループリントの対象ユーザーを更新する |
| microsoft.directory/agentIdentityBlueprints/authentication/update | エージェント ID ブループリントの認証を更新する |
| microsoft.directory/agentIdentityBlueprints/basic/update | エージェント ID ブループリントの基本プロパティを更新する |
| microsoft.directory/agentIdentityBlueprints/create | エージェント ID ブループリントを作成する |
| microsoft.directory/agentIdentityBlueprints/credentials/update | エージェント ID ブループリントの資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentIdentityBlueprints/delete | エージェント ID ブループリントを削除する |
| microsoft.directory/agentIdentityBlueprints/owners/update | エージェント ID ブループリントの所有者を更新する |
| microsoft.directory/agentIdentityBlueprints/permissions/update | エージェント ID ブループリントで公開されているアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/agentIdentityBlueprints/tag/update | エージェント ID ブループリントのタグを更新する |
| microsoft.directory/agentIdentityBlueprints/verification/update | エージェント ID ブループリントでの更新の検証 |
| microsoft.directory/agentUsers/assignLicense | エージェント ユーザーに製品ライセンスを割り当てる |
| microsoft.directory/agentUsers/basic/update | エージェント ユーザーの基本プロパティ (表示名、ユーザーの種類、メールニックネームなど) を更新する |
| microsoft.directory/agentUsers/create | エージェント ユーザーの作成[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/delete | エージェントユーザーを削除してください[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/disable | エージェントユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/enable | エージェントユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/invalidateAllRefreshTokens | エージェントのユーザーリフレッシュトークンを無効化して強制サインアウト[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/lifeCycleInfo/read | employeeLeaveDateTimeのようなエージェントユーザーのライフサイクル情報を読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/lifeCycleInfo/update | employeeLeaveDateTimeのようなエージェントユーザーのライフサイクル情報を更新してください[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentUsers/manager/update | エージェントユーザー向けのアップデートマネージャー |
| microsoft.directory/agentUsers/photo/update | エージェントユーザーの写真更新 |
| microsoft.directory/agentUsers/reprocessLicenseAssignment | エージェントユーザー向けの再処理ライセンス割り当て |
| microsoft.directory/agentUsers/restore | 削除されたエージェントユーザーを復元する |
| microsoft.directory/agentUsers/revokeSignInSessions | エージェント ユーザーのサインイン セッションを取り消す |
| microsoft.directory/agentUsers/sponsors/update | エージェントユーザーのスポンサーを更新してください |
| microsoft.directory/agentUsers/usageLocation/update | エージェントユーザーの使用場所を更新する |
| microsoft.directory/agentUsers/userPrincipalName/update | エージェント ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/deletedItems.agentIdentities/delete | 復元できなくなったエージェント ID を完全に削除する |
| microsoft.directory/deletedItems.agentIdentities/restore | 論理的に削除されたエージェント ID を元の状態に復元する |
| microsoft.directory/deletedItems.agentIdentityBlueprintPrincipals/delete | 復元できなくなったエージェント ID ブループリント プリンシパルを完全に削除する |
| microsoft.directory/deletedItems.agentIdentityBlueprintPrincipals/restore | 論理的に削除されたエージェント ID ブループリント プリンシパルを元の状態に復元する |
| microsoft.directory/deletedItems.agentIdentityBlueprints/delete | エージェントの識別設計図は永久に削除し、復元できません |
| microsoft.directory/deletedItems.agentIdentityBlueprints/restore | 論理的に削除されたエージェント ID ブループリントを元の状態に復元する |
| microsoft.directory/entitlementManagement/allProperties/read | Microsoft Entra エンタイトルメント管理ですべてのプロパティを読み取る |
| microsoft.directory/subscribedSkus/standard/read | サブスクリプションの基本プロパティの読み取り |
| microsoft.directory/users/allProperties/read | ユーザーのすべてのプロパティを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.office365.copilot/allEntities/allProperties/allTasks | Microsoft Copilotのすべての設定を作成および管理する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.search/content/manage | Microsoft Search でコンテンツの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### AI リーダー

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次のタスクを実行する必要があるユーザーに AI 閲覧者ロールを割り当てます。

- Microsoft Copilot のすべての側面を読む
- AI 関連のエンタープライズ サービス、拡張性、および代理エージェントを読み取る
- アプリケーション、ユーザー、グループ、エージェント ID、エージェント ID ブループリント、エージェント ID ブループリント プリンシパル、エージェント ユーザーなどのディレクトリ オブジェクトの情報を読み取ります
- Azure と Microsoft 365 Service Health のダッシュボードを読み取りおよび構成する

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/administrativeUnits/members/read | 管理単位のメンバーを読み取る |
| microsoft.directory/administrativeUnits/standard/read | 管理単位の基本プロパティを読み取る |
| microsoft.directory/adminConsentRequestPolicy/allProperties/read | Microsoft Entra ID で管理者の同意要求ポリシーのすべてのプロパティを読み取る |
| microsoft.directory/agentIdentities/allProperties/read | エージェント ID のすべてのプロパティの読み取り |
| microsoft.directory/agentIdentityBlueprintPrincipals/allProperties/read | エージェント ID ブループリント プリンシパルのすべてのプロパティを読み取ります |
| microsoft.directory/agentIdentityBlueprints/allProperties/read | エージェント ID ブループリントのすべてのプロパティを読み取ります |
| microsoft.directory/agentUsers/lifeCycleInfo/read | employeeLeaveDateTimeのようなエージェントユーザーのライフサイクル情報を読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/applicationPolicies/standard/read | アプリケーション ポリシーの標準プロパティを読み取る |
| microsoft.directory/applications/owners/read | アプリケーションの所有者を読み取る |
| microsoft.directory/applications/policies/read | アプリケーションのポリシーを読み取る |
| microsoft.directory/applications/standard/read | アプリケーションの標準プロパティを読み取る |
| microsoft.directory/contacts/memberOf/read | Microsoft Entra ID ですべての連絡先のグループ メンバーシップを読み取る |
| microsoft.directory/contacts/standard/read | Microsoft Entra ID で連絡先の基本プロパティを読み取る |
| microsoft.directory/contracts/standard/read | パートナー コントラクトの基本プロパティを読み取る |
| microsoft.directory/domains/standard/read | ドメインで基本プロパティを読み取る |
| microsoft.directory/entitlementManagement/allProperties/read | Microsoft Entra エンタイトルメント管理ですべてのプロパティを読み取る |
| microsoft.directory/groups/appRoleAssignments/read | グループのアプリケーション ロールの割り当てを読み取る |
| microsoft.directory/groups/memberOf/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの memberOf プロパティを読み取る |
| microsoft.directory/groups/members/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループのメンバーを読み取る |
| microsoft.directory/groups/owners/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの所有者を読み取る |
| microsoft.directory/groups/settings/read | グループの設定を読み取る |
| microsoft.directory/groups/standard/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの標準プロパティを読み取る |
| microsoft.directory/groupSettings/standard/read | グループ設定の基本プロパティを読み取る |
| microsoft.directory/groupSettingTemplates/standard/read | グループ設定テンプレートの基本プロパティを読み取る |
| microsoft.directory/oAuth2PermissionGrants/standard/read | OAuth 2.0 アクセス許可付与の基本プロパティを読み取る |
| microsoft.directory/organization/standard/read | 組織で基本プロパティを読み取る |
| microsoft.directory/organization/trustedCAsForPasswordlessAuth/read | パスワードレス認証用に信頼された証明機関を読み取る |
| microsoft.directory/roleAssignments/standard/read | ロールの割り当ての基本プロパティを読み取る |
| microsoft.directory/roleDefinitions/standard/read | ロールの定義の基本プロパティを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/read | サービス プリンシパルのロールの割り当てを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignments/read | サービス プリンシパルに割り当てられたロールの割り当てを読み取る |
| microsoft.directory/servicePrincipals/memberOf/read | サービス プリンシパルのグループ メンバーシップを読み取る |
| microsoft.directory/servicePrincipals/oAuth2PermissionGrants/read | サービス プリンシパルの委任されたアクセス許可付与を読み取る |
| microsoft.directory/servicePrincipals/ownedObjects/read | サービスプリンシパルの所有オブジェクトを読み取る |
| microsoft.directory/servicePrincipals/owners/read | サービス プリンシパルの所有者を読み取る |
| microsoft.directory/servicePrincipals/policies/read | サービス プリンシパルのポリシーを読み取る |
| microsoft.directory/servicePrincipals/standard/read | サービス プリンシパルの基本プロパティを読み取る |
| microsoft.directory/subscribedSkus/standard/read | サブスクリプションの基本プロパティの読み取り |
| microsoft.directory/users/allProperties/read | ユーザーのすべてのプロパティを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.office365.copilot/allEntities/allProperties/read | Microsoft Copilotのすべての設定を読み取る |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### アプリケーション管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、エンタープライズ アプリケーション、アプリケーション登録、アプリケーション プロキシの設定の全側面を作成して管理できます。 このロールに割り当てられたユーザーは、新しいアプリケーション登録またはエンタープライズ アプリケーションを作成する際に、所有者として追加されないことに注意してください。

さらに、このロールは、委任されたアクセス許可とアプリケーション アクセス許可 (Azure AD Graph と Microsoft Graph に対するアプリケーション アクセス許可を除く) に同意する権限を付与します。

Important

この例外は、 *他* のアプリ (他の Microsoft アプリ、サード パーティ製アプリ、登録したアプリなど) に対するアプリケーションのアクセス許可に同意できることを意味します。 これらのアクセス許可はアプリの登録の一部として引き続き *要求* できますが、これらのアクセス許可を *付与* する (つまり、同意する) には、特権ロール管理者などのより特権のある管理者が必要です。

このロールは、アプリケーションの資格情報を管理する権限を付与します。 このロールが割り当てられているユーザーは、アプリケーションに資格情報を追加し、それらの資格情報を使用してアプリケーションの ID を偽装できます。 ユーザーまたはその他のオブジェクトを作成または更新する機能など、アプリケーションの ID にリソースへのアクセスが許可されている場合、このロールに割り当てられたユーザーは、アプリケーションの偽装中にこれらのアクションを実行できます。 アプリケーションの ID を偽装するこの機能は、ユーザーがロールの割り当てを介して実行できる操作に対する特権の昇格である可能性があります。 アプリケーション管理者ロールにユーザーを割り当てると、アプリケーションの ID を偽装できることを理解しておくことが重要です。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/adminConsentRequestPolicy/allProperties/allTasks | Microsoft Entra ID で管理者の同意要求ポリシーを管理する |
| microsoft.directory/appConsent/appConsentRequests/allProperties/read | Microsoft Entra ID に登録されているアプリケーションに対する同意要求のすべてのプロパティを読み取る |
| microsoft.directory/applicationPolicies/basic/update | アプリケーション ポリシーの標準プロパティを更新する |
| microsoft.directory/applicationPolicies/create | アプリケーション ポリシーを作成する |
| microsoft.directory/applicationPolicies/delete | アプリケーション ポリシーを削除する |
| microsoft.directory/applicationPolicies/owners/read | アプリケーション ポリシーの所有者を読み取る |
| microsoft.directory/applicationPolicies/owners/update | アプリケーション ポリシーの所有者プロパティを更新する |
| microsoft.directory/applicationPolicies/policyAppliedTo/read | オブジェクト リストに適用されているアプリケーション ポリシーを読み取る |
| microsoft.directory/applicationPolicies/standard/read | アプリケーション ポリシーの標準プロパティを読み取る |
| microsoft.directory/applications/applicationProxy/read | すべてのアプリケーション プロキシ プロパティを読み取る |
| microsoft.directory/applications/applicationProxy/update | すべてのアプリケーション プロキシ プロパティを更新する |
| microsoft.directory/applications/applicationProxyAuthentication/update | すべての種類のアプリケーションで認証を更新する |
| microsoft.directory/applications/applicationProxySslCertificate/update | アプリケーション プロキシの SSL 証明書の設定を更新する |
| microsoft.directory/applications/applicationProxyUrlSettings/update | アプリケーション プロキシの URL 設定を更新する |
| microsoft.directory/applications/appRoles/update | すべての種類のアプリケーションで appRoles プロパティを更新する |
| microsoft.directory/applications/audience/update | アプリケーションの対象ユーザー プロパティを更新する |
| microsoft.directory/applications/authentication/update | すべての種類のアプリケーションで認証を更新する |
| microsoft.directory/applications/basic/update | アプリケーションの基本プロパティを更新する |
| microsoft.directory/applications/create | すべての種類のアプリケーションを作成する |
| microsoft.directory/applications/credentials/update | アプリケーション資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/applications/delete | すべての種類のアプリケーションを削除する |
| microsoft.directory/applications/disablement/update | ユーザーがサインインできるようにアプリケーションが有効になっているかどうかを更新する |
| microsoft.directory/applications/extensionProperties/update | アプリケーションの拡張機能プロパティを更新する |
| microsoft.directory/applications/notes/update | アプリケーションのメモを更新する |
| microsoft.directory/applications/owners/update | アプリケーションの所有者を更新する |
| microsoft.directory/applications/permissions/update | すべての種類のアプリケーションで、公開されたアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/applications/policies/update | アプリケーションのポリシーを更新する |
| microsoft.directory/applications/synchronization/standard/read | アプリケーション オブジェクトに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/applications/tag/update | アプリケーションのタグを更新する |
| microsoft.directory/applications/verification/update | applicationsverification プロパティを更新する |
| microsoft.directory/applicationTemplates/instantiate | アプリケーション テンプレートからギャラリー アプリケーションのインスタンスを作成する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/connectorGroups/allProperties/read | アプリケーション プロキシ コネクタ グループのすべてのプロパティの読み取り |
| microsoft.directory/connectorGroups/allProperties/update | アプリケーション プロキシ コネクタ グループのすべてのプロパティを更新する |
| microsoft.directory/connectorGroups/create | アプリケーション プロキシ コネクタ グループを作成する |
| microsoft.directory/connectorGroups/delete | アプリケーション プロキシ コネクタ グループを削除する |
| microsoft.directory/connectors/allProperties/read | アプリケーション プロキシ コネクタのすべてのプロパティの読み取り |
| microsoft.directory/connectors/create | アプリケーション プロキシ コネクタを作成する |
| microsoft.directory/customAuthenticationExtensions/allProperties/allTasks | カスタム認証拡張機能の作成と管理する[Image: 特権ラベル アイコン。] |
| microsoft.directory/deletedItems.applications/delete | 復元できなくなったアプリケーションを完全に削除する |
| microsoft.directory/deletedItems.applications/restore | 論理的に削除されたアプリケーションを元の状態に復元する |
| microsoft.directory/oAuth2PermissionGrants/allProperties/allTasks | OAuth 2.0 アクセス許可の付与の作成と削除、およびすべてのプロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/servicePrincipals/audience/update | サービス プリンシパルで対象ユーザー プロパティを更新する |
| microsoft.directory/servicePrincipals/authentication/update | サービス プリンシパルで認証プロパティを更新する |
| microsoft.directory/servicePrincipals/basic/update | サービス プリンシパルで基本プロパティを更新する |
| microsoft.directory/servicePrincipals/create | サービス プリンシパルを作成する |
| microsoft.directory/servicePrincipals/credentials/update | サービス プリンシパルの資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/delete | サービス プリンシパルを削除する |
| microsoft.directory/servicePrincipals/disable | サービス プリンシパルを無効にする |
| microsoft.directory/servicePrincipals/enable | サービス プリンシパルを有効にする |
| microsoft.directory/servicePrincipals/getPasswordSingleSignOnCredentials | サービス プリンシパルのパスワード シングル サインオン資格情報を管理する |
| microsoft.directory/servicePrincipals/managePasswordSingleSignOnCredentials | サービス プリンシパルのパスワード シングル サインオン資格情報を読み取る |
| microsoft.directory/servicePrincipals/managePermissionGrantsForAll.microsoft-application-admin | Microsoft Graph と Azure AD Graph のアプリケーションアクセス許可を除き、すべてのユーザーまたはすべてのユーザーに代わって、アプリケーションのアクセス許可と委任されたアクセス許可に同意する |
| microsoft.directory/servicePrincipals/notes/update | サービス プリンシパルのメモを更新する |
| microsoft.directory/servicePrincipals/owners/update | サービス プリンシパルの所有者を更新する |
| microsoft.directory/servicePrincipals/permissions/update | サービスプリンシパルのアクセス許可を更新する |
| microsoft.directory/servicePrincipals/policies/update | サービス プリンシパルのポリシーを更新する |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/credentials/manage | アプリケーション プロビジョニングのシークレットと資格情報の管理。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/jobs/manage | アプリケーション プロビジョニングの同期ジョブの開始、再開、および一時停止。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/schema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/servicePrincipals/synchronizationCredentials/manage | アプリケーション プロビジョニングのシークレットと資格情報を管理する |
| microsoft.directory/servicePrincipals/synchronizationJobs/manage | アプリケーション プロビジョニングの同期ジョブを開始、再開、および一時停止する |
| microsoft.directory/servicePrincipals/synchronizationSchema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成、管理する |
| microsoft.directory/servicePrincipals/tag/update | サービスプリンシパルのタグ プロパティを更新する |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### アプリケーション開発者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、[ユーザーはアプリケーションを登録できる] 設定が [いいえ] に設定されている場合に、アプリケーション登録を作成できます。 さらにこのロールでは、[ユーザーはアプリが自身の代わりに会社のデータにアクセスすることを許可できる] 設定が [いいえ] に設定されている場合に、代わりに同意する権限を付与します。 このロールに割り当てられたユーザーは、新しいアプリケーション登録を作成する際に、所有者として追加されます。

| Actions | Description |
| --- | --- |
| microsoft.directory/applications/createAsOwner | すべての種類のアプリケーションを作成し、作成者が最初の所有者として追加される |
| microsoft.directory/oAuth2PermissionGrants/createAsOwner | OAuth 2.0 アクセス許可付与を作成し、作成者を最初の所有者にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/createAsOwner | サービス プリンシパルを作成し、作成者を最初の所有者にする |

### 攻撃のペイロードの作成者

このロールのユーザーは、攻撃のペイロードを作成することはできますが、それらを実際に起動することやスケジュールすることはできません。 攻撃のペイロードは、それらを使用してシミュレーション作成できるテナントの管理者全員が利用できます。 レポートへのアクセスはユーザーによって実行されるシミュレーションに限定され、このロールでは、トレーニングの有効性、反復違反者、トレーニング完了、ユーザー カバレッジなどの集計レポートへのアクセスは許可されません。

詳細については、次の記事を参照してください。

- [攻撃シミュレーション トレーニング](https://learn.microsoft.com/ja-jp/defender-office-365/attack-simulation-training-get-started) の使用を開始する
- [Microsoft Defender ポータルでのアクセス許可のMicrosoft Defender for Office 365](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/mdo-portal-permissions)
- [Microsoft Purview ポータルのアクセス許可](https://learn.microsoft.com/ja-jp/purview/purview-permissions)

| Actions | Description |
| --- | --- |
| microsoft.office365.protectionCenter/attackSimulator/payload/allProperties/allTasks | 攻撃シミュレーターで攻撃ペイロードを作成および管理する |
| microsoft.office365.protectionCenter/attackSimulator/reports/allProperties/read | 攻撃のシミュレーション、応答、関連付けられているトレーニングのレポートを読み取る |

### 攻撃のシミュレーションの管理者

このロールのユーザーは、攻撃シミュレーションの作成、シミュレーションの開始とスケジューリング、シミュレーション結果の確認のすべての側面を作成および管理できます。 このロールのメンバーは、テナント内のすべてのシミュレーションに対してこのアクセス権を所有します。

詳細については、次の記事を参照してください。

- [Microsoft Defender ポータルでのアクセス許可のMicrosoft Defender for Office 365](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/mdo-portal-permissions)
- [Microsoft Purview ポータルのアクセス許可](https://learn.microsoft.com/ja-jp/purview/purview-permissions)

| Actions | Description |
| --- | --- |
| microsoft.office365.protectionCenter/attackSimulator/payload/allProperties/allTasks | 攻撃シミュレーターで攻撃ペイロードを作成および管理する |
| microsoft.office365.protectionCenter/attackSimulator/reports/allProperties/read | 攻撃のシミュレーション、応答、関連付けられているトレーニングのレポートを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/simulation/allProperties/allTasks | 攻撃シミュレーターで攻撃のシミュレーション テンプレートを作成および管理する |

### 属性割り当て管理者

このロールのユーザーは、ユーザー、サービス プリンシパル、デバイスなどのサポートされている Microsoft Entra オブジェクトについて、属性のキーと値を割り当てたり、割り当て解除したりできます。

Important

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.directory/attributeSets/allProperties/read | 属性セットのすべてのプロパティを読み取ります |
| microsoft.directory/azureManagedIdentities/customSecurityAttributes/read | Microsoft Entra マネージド ID のカスタム セキュリティ属性値を読み取ります |
| microsoft.directory/azureManagedIdentities/customSecurityAttributes/update | Microsoft Entra マネージド ID のカスタム セキュリティ属性値を更新する |
| microsoft.directory/customSecurityAttributeDefinitions/allProperties/read | カスタム セキュリティ属性定義のすべてのプロパティを読み取ります |
| microsoft.directory/devices/customSecurityAttributes/read | デバイスのカスタム セキュリティ属性の値を読み取ります |
| microsoft.directory/devices/customSecurityAttributes/update | デバイスのカスタム セキュリティ属性の値を更新します |
| microsoft.directory/servicePrincipals/customSecurityAttributes/read | サービス プリンシパルのカスタム セキュリティ属性の値を読み取ります |
| microsoft.directory/servicePrincipals/customSecurityAttributes/update | サービス プリンシパルのカスタム セキュリティ属性の値を更新します |
| microsoft.directory/users/customSecurityAttributes/read | ユーザーのカスタム セキュリティ属性の値を読み取ります |
| microsoft.directory/users/customSecurityAttributes/update | ユーザーのカスタム セキュリティ属性の値を更新します |

### 属性割り当て閲覧者

このロールのユーザーは、サポートされている Microsoft Entra オブジェクトのカスタム セキュリティ属性のキーと値を読み取ることができます。

Important

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.directory/attributeSets/allProperties/read | 属性セットのすべてのプロパティを読み取ります |
| microsoft.directory/azureManagedIdentities/customSecurityAttributes/read | Microsoft Entra マネージド ID のカスタム セキュリティ属性値を読み取ります |
| microsoft.directory/customSecurityAttributeDefinitions/allProperties/read | カスタム セキュリティ属性定義のすべてのプロパティを読み取ります |
| microsoft.directory/devices/customSecurityAttributes/read | デバイスのカスタム セキュリティ属性の値を読み取ります |
| microsoft.directory/servicePrincipals/customSecurityAttributes/read | サービス プリンシパルのカスタム セキュリティ属性の値を読み取ります |
| microsoft.directory/users/customSecurityAttributes/read | ユーザーのカスタム セキュリティ属性の値を読み取ります |

### 属性定義管理者

このロールが割り当てられたユーザーは、サポート対象 Microsoft Entra オブジェクトに割り当てることができる有効なカスタム セキュリティ属性一式を定義できます。 カスタム セキュリティ属性のアクティブ化と非アクティブ化もこのロールで行うことができます。

Important

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.directory/attributeSets/allProperties/allTasks | 属性セットのすべての側面を管理する |
| microsoft.directory/customSecurityAttributeDefinitions/allProperties/allTasks | カスタム セキュリティ属性定義のすべての側面を管理する |

### 属性定義閲覧者

このロールのユーザーは、カスタム セキュリティ属性の定義を読み取ることができます。

Important

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.directory/attributeSets/allProperties/read | 属性セットのすべてのプロパティを読み取ります |
| microsoft.directory/customSecurityAttributeDefinitions/allProperties/read | カスタム セキュリティ属性定義のすべてのプロパティを読み取ります |

### 属性ログ管理者

以下のタスクを行う必要があるユーザーに、監査ログ閲覧者ロールを割り当てます。

- カスタム セキュリティ属性値の変更に関する監査ログを読み取る
- カスタム セキュリティ属性の定義の変更と割り当てに関する監査ログを読み取る
- カスタム セキュリティ属性の診断設定を構成する

このロールを持つユーザー **は、** 他のイベントの監査ログを読み取ることができません。

Important

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.azure.customSecurityAttributeDiagnosticSettings/allEntities/allProperties/allTasks | カスタム セキュリティ属性の診断設定のすべての側面を構成する |
| microsoft.directory/customSecurityAttributeAuditLogs/allProperties/read | カスタムの secruity 属性に関連する監査ログを読み取ります |

### 属性ログ閲覧者

以下のタスクを行う必要があるユーザーに、監査ログ閲覧者ロールを割り当てます。

- カスタム セキュリティ属性値の変更に関する監査ログを読み取る
- カスタム セキュリティ属性の定義の変更と割り当てに関する監査ログを読み取る

このロールを持つユーザー **は、** 次のタスクを実行できません。

- カスタム セキュリティ属性の診断設定を構成する
- 他のイベントの監査ログを読み取る

Important

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

詳細については、「[Microsoft Entra ID でカスタム セキュリティ属性へのアクセスを管理する](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-manage)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.directory/customSecurityAttributeAuditLogs/allProperties/read | カスタムの secruity 属性に関連する監査ログを読み取ります |

### 属性プロビジョニング管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 属性プロビジョニング管理者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- アプリケーションでのプロビジョニング時のカスタム セキュリティ属性の属性マッピングの読み取りと書き込み。
- アプリケーションでのプロビジョニング時のカスタム セキュリティ属性のプロビジョニングと監査ログの読み取りと書き込み。

このロールを持つユーザーは、他のイベントの監査ログを読み取ることができません。 プロビジョニング構成を読み取るために、このロールをクラウド アプリケーション管理者ロールまたはアプリケーション管理者ロール (最小から最も特権まで) と組み合わせて使用する必要があります。

Important

このロールには、カスタム セキュリティ属性セットを作成したり、ユーザー オブジェクトのカスタム セキュリティ属性値を直接割り当てたり更新したりすることはできません。 このロールは、プロビジョニング アプリのカスタム セキュリティ属性のフローのみを構成できます。

[詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-custom-security-attributes)

| Actions | Description |
| --- | --- |
| microsoft.directory/servicePrincipals/synchronization.customSecurityAttributes/schema/read | 同期スキーマ内のすべてのカスタム セキュリティ属性を読み取ります[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/synchronization.customSecurityAttributes/schema/update | 同期スキーマのカスタム セキュリティ属性マッピングを更新する[Image: 特権ラベル アイコン。] |

### 属性プロビジョニング 閲覧者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次のタスクを実行する必要があるユーザーに属性プロビジョニング閲覧者ロールを割り当てます。

- アプリケーションでプロビジョニングするときに、カスタム セキュリティ属性の属性マッピングを読み取ります。
- アプリケーションでプロビジョニングするときに、カスタム セキュリティ属性のプロビジョニングと監査のログを読み取ります。

このロールを持つユーザーは、他のイベントの監査ログを読み取ることはできません。 このロールは、プロビジョニング構成を読み取るために、クラウド アプリケーション管理者ロールまたはアプリケーション管理者ロール (最小から最も特権まで) と共に使用する必要があります。

[詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/provision-custom-security-attributes)

| Actions | Description |
| --- | --- |
| microsoft.directory/servicePrincipals/synchronization.customSecurityAttributes/schema/read | 同期スキーマ内のすべてのカスタム セキュリティ属性を読み取ります[Image: 特権ラベル アイコン。] |

### 認証管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次の作業を行う必要があるユーザーに、認証管理者ロールを割り当てます。

- 管理者以外と一部のロールの認証方法 (パスワードを含む) を設定またはリセットします。 認証管理者が認証方法を読み取りまたは更新できるロールの一覧については、「[パスワードをリセットできるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-reset-passwords)」を参照してください。
- 管理者以外のユーザーまたは一部のロールに割り当てられているユーザーに、既存の非パスワード資格情報 (MFA や FIDO など) に対する再登録を要求し、 **デバイス上の MFA の記憶**を取り消すこともできます。この場合、次のサインイン時に MFA の入力を求められます。
- レガシの MFA 管理ポータルで MFA 設定を管理します。
- 一部のユーザーに対する機密性の高いアクションの実行。 詳細については、「[機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)」を参照してください。
- Azure と Microsoft 365 管理センターでのサポート チケットの作成および管理。

このロールを持つユーザー **は、** 次の操作を実行できません。

- [ロールを割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)のメンバーと所有者の資格情報を変更したり、MFA をリセットしたりすることはできません。
- ハードウェアの OATH トークンは管理できません。

認証関連ロールの機能との比較を次の表に示します。

| Role | ユーザーの認証方法の管理 | ユーザーごとの MFA の管理 | MFA 設定の管理 | 認証方法ポリシーの管理 | パスワード保護ポリシーの管理 | 機密性の高いプロパティの更新 | ユーザーの削除と復元 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | No | No | No | No | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] |
| [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) | すべてのユーザーに対してはい | No | No | No | No | すべてのユーザーに対してはい | すべてのユーザーに対してはい |
| [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) | No | Yes | Yes | Yes | Yes | No | No |
| [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | No | No | No | No | No | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] |

Important

このロールのユーザーは、機密情報や個人情報または Microsoft Entra ID の内外の重要な構成にアクセスできるユーザーのパスワードを変更できます。 ユーザーの資格情報を変更することは、そのユーザーの ID とアクセス許可を引き受けることができることを意味します。 例えば次が挙げられます。

- 所有しているアプリの資格情報を管理できる、アプリケーションの登録とエンタープライズ アプリケーションの所有者。 これらのアプリには、認証管理者に付与されていない Microsoft Entra ID およびその他の場所への特権アクセス許可がある場合があります。 このパスを通じて、認証管理者はアプリケーション所有者の ID を引き受け、さらにアプリケーションの資格情報を更新することで特権アプリケーションの ID を引き受けることができます。
- 機密情報や個人情報または Azure の重要な構成にアクセスできる Azure サブスクリプション所有者。
- グループ メンバーシップを管理できるセキュリティ グループと Microsoft 365 グループの所有者。 これらのグループは、機密情報や個人情報または Microsoft Entra ID や別の場所の重要な構成へのアクセス権を付与される場合があります。
- Exchange Online、Microsoft Defender XDR ポータル、Microsoft Purview ポータル、人事システムなど、Microsoft Entra ID 以外の他のサービスの管理者。
- 機密情報や個人情報にアクセスできる可能性のある役員、弁護士、人事部の従業員などの非管理者。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/deletedItems.users/restore | 論理的に削除されたユーザーを元の状態に復元する |
| microsoft.directory/users/authenticationMethods/basic/update | ユーザーの認証方法の基本プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/create | ユーザーの認証方法を更新します[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/delete | ユーザーの認証方法を削除する[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/standard/restrictedRead | ユーザーの個人を特定できる情報を含まない認証方法の標準プロパティを読み取る |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/delete | ユーザーの削除[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/disable | ユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/enable | ユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/restore | 削除されたユーザーを復元する |
| microsoft.directory/users/userPrincipalName/update | ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 認証拡張性の管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次のタスクを行う必要があるユーザーに、認証拡張性の管理者ロールを割り当てます。

- カスタム認証拡張機能のすべての側面を作成および管理します。

このロールを持つユーザー **は、** 次の操作を実行できません。

- カスタム認証拡張機能をアプリケーションに割り当てて認証エクスペリエンスを変更することはできません。また、アプリケーションのアクセス許可に同意したり、カスタム認証拡張機能に関連付けられたアプリの登録を作成したりすることはできません。 代わりに、アプリケーション管理者、アプリケーション開発者、またはクラウド アプリケーション管理者ロールを使用する必要があります。

カスタム認証拡張機能は、認証イベント用に開発者が作成する API エンドポイントであり、Microsoft Entra ID に登録されます。 アプリケーション管理者とアプリケーション所有者は、カスタム認証拡張機能を使用して、サインイン、サインアップ、パスワード リセットなど、アプリケーションの認証エクスペリエンスをカスタマイズすることができます。

[詳細情報](https://learn.microsoft.com/ja-jp/entra/identity-platform/custom-extension-tokenissuancestart-configuration)

| Actions | Description |
| --- | --- |
| microsoft.directory/customAuthenticationExtensions/allProperties/allTasks | カスタム認証拡張機能の作成と管理する[Image: 特権ラベル アイコン。] |

### 認証機能拡張パスワード管理者

次のタスクを実行する必要があるユーザーに認証機能拡張パスワード管理者ロールを割り当てます。

- カスタム認証のパスワード送信イベントをトリガーして、ユーザー パスワードを外部 ID システムから Microsoft Entra 外部 ID に移行します。

| Actions | Description |
| --- | --- |
| microsoft.directory/onPasswordSubmitCustomAuthenticationExtension/allProperties/allTasks | onPasswordSubmit イベントのカスタム認証拡張機能を作成および管理する[Image: 特権ラベル アイコン。] |

### 認証ポリシー管理者

次の作業を行う必要があるユーザーに、認証ポリシー管理者ロールを割り当てます。

- 認証方法ポリシー、テナント全体の MFA 設定、および各ユーザーが登録して使用できる方法を決定するパスワード保護ポリシーの構成。
- パスワード保護設定の管理 (スマート ロックアウトの構成とカスタムの禁止パスワード リストの更新)。
- レガシの MFA 管理ポータルで MFA 設定を管理します。
- 検証可能な資格情報の作成と管理。
- Azure サポート チケットの作成と管理。

このロールを持つユーザー **は、** 次の操作を実行できません。

- 機密性の高いプロパティを更新できません。 詳細については、「[機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)」を参照してください。
- ユーザーの削除や復元はできません。 詳細については、「[機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)」を参照してください。
- ハードウェアの OATH トークンは管理できません。

認証関連ロールの機能との比較を次の表に示します。

| Role | ユーザーの認証方法の管理 | ユーザーごとの MFA の管理 | MFA 設定の管理 | 認証方法ポリシーの管理 | パスワード保護ポリシーの管理 | 機密性の高いプロパティの更新 | ユーザーの削除と復元 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | No | No | No | No | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] |
| [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) | すべてのユーザーに対してはい | No | No | No | No | すべてのユーザーに対してはい | すべてのユーザーに対してはい |
| [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) | No | Yes | Yes | Yes | Yes | No | No |
| [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | No | No | No | No | No | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] |

| Actions | Description |
| --- | --- |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/organization/strongAuthentication/allTasks | 組織の強力な認証プロパティのすべての側面を管理する |
| microsoft.directory/userCredentialPolicies/basic/update | ユーザーの基本ポリシーを更新する |
| microsoft.directory/userCredentialPolicies/create | ユーザーの資格情報ポリシーを作成する |
| microsoft.directory/userCredentialPolicies/delete | ユーザーの資格情報ポリシーを削除する |
| microsoft.directory/userCredentialPolicies/owners/read | ユーザーの資格情報ポリシーの所有者を読み取る |
| microsoft.directory/userCredentialPolicies/owners/update | ユーザーの資格情報ポリシーの所有者を更新する |
| microsoft.directory/userCredentialPolicies/policyAppliedTo/read | policy.appliesTo navigation リンクを読み取る |
| microsoft.directory/userCredentialPolicies/standard/read | ユーザーの資格情報ポリシーの標準プロパティを読み取る |
| microsoft.directory/userCredentialPolicies/tenantDefault/update | policy.isOrganizationDefault プロパティを更新する |
| microsoft.directory/verifiableCredentials/configuration/allProperties/read | 検証可能な資格情報を作成および管理するために必要な構成を読み取る |
| microsoft.directory/verifiableCredentials/configuration/allProperties/update | 検証可能な資格情報を作成および管理するために必要な構成を更新する |
| microsoft.directory/verifiableCredentials/configuration/contracts/allProperties/read | 検証可能な資格情報コントラクトを読み取る |
| microsoft.directory/verifiableCredentials/configuration/contracts/allProperties/update | 検証可能な資格情報コントラクトを更新する |
| microsoft.directory/verifiableCredentials/configuration/contracts/cards/allProperties/read | 検証可能な資格情報カードを読み取る |
| microsoft.directory/verifiableCredentials/configuration/contracts/cards/revoke | 検証可能な資格情報カードを取り消す |
| microsoft.directory/verifiableCredentials/configuration/contracts/create | 検証可能な資格情報コントラクトを作成する |
| microsoft.directory/verifiableCredentials/configuration/create | 検証可能な資格情報を作成および管理するために必要な構成を作成する |
| microsoft.directory/verifiableCredentials/configuration/delete | 検証可能な資格情報を作成および管理するために必要な構成を削除し、その検証可能な資格情報をすべて削除する |

### Azure DevOps 管理者

このロールが割り当てられたユーザーは、Microsoft Entra ID によって支えられているあらゆる Azure DevOps 組織を対象としたエンタープライズ Azure DevOps ポリシーをすべて管理できます。 このロールのユーザーは、会社の Microsoft Entra ID によってサポートされている Azure DevOps 組織に移動することで、これらのポリシーを管理できます。 さらに、このロールのユーザーは、孤立した Azure DevOps 組織の所有権を要求できます。 このロールは、会社の Microsoft Entra 組織によってサポートされている Azure DevOps 組織の中で、Azure DevOps 固有の他のアクセス許可 (プロジェクト コレクション管理者など) を付与しません。

| Actions | Description |
| --- | --- |
| microsoft.azure.devOps/allEntities/allTasks | Azure DevOps を読み取り、構成する |

### Azure Information Protection 管理者

このロールが割り当てられたユーザーは、Azure Information Protection サービスのすべてのアクセス許可を持ちます。 このロールでは、Azure Information Protection ポリシーのラベルの構成、保護テンプレートの管理、保護のアクティブ化を行うことができます。 このロールには、Microsoft Entra ID 保護、Privileged Identity Management、Monitor Microsoft 365 Service Health、Microsoft Defender XDR ポータル、または Microsoft Purview ポータルのアクセス許可は付与されません。

| Actions | Description |
| --- | --- |
| microsoft.azure.informationProtection/allEntities/allTasks | Azure Information Protection のすべての側面を管理する |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### B2C IEF キーセット管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールに割り当てられたユーザーは、トークンの暗号化、トークン署名、および要求の暗号化/暗号化解除に使用されるポリシー キーとシークレットを作成および管理できます。 既存のキー コンテナーに新しいキーを追加できるため、既存のアプリケーションに影響を与えずにシークレット ロールオーバーを有効にすることができます。 さらに、このロールのユーザーは、作成後でも、有効期限など、これらのシークレットの完全な詳細を表示できます。

Important

これは機密性の高いロールです。 キーセット管理者ロールは、運用前と運用環境で慎重に監査し、慎重に割り当てる必要があります。

| Actions | Description |
| --- | --- |
| microsoft.directory/b2cTrustFrameworkKeySet/allProperties/allTasks | Azure Active Directory B2C のキー セットを読み取り、構成する[Image: 特権ラベル アイコン。] |

### B2C IEF ポリシー管理者

このロールが割り当てられたユーザーは、Azure AD B2C のすべてのカスタム ポリシーを作成、読み取り、更新、および削除することができます。そのため、関連する Azure AD B2C 組織内の Identity Experience Framework を完全に制御できます。 このユーザーは、ポリシーを編集することで、外部の ID プロバイダーとの直接フェデレーションの確立、ディレクトリ スキーマの変更、ユーザー向けのすべてのコンテンツ (HTML、CSS、JavaScript) の変更、認証を完了するための要件の変更、新しいユーザーの作成、ユーザー データの外部システムへの送信 (完全な移行を含む)、パスワードや電話番号などの機密フィールドを含むすべてのユーザー情報の編集を行うことができます。 逆に、このロールでは、暗号化キーを変更したり、組織内のフェデレーションに使用されているシークレットを編集したりすることはできません。

Important

B2 IEF ポリシー管理者は機密性の高いロールであり、運用環境の組織に対して非常に限定的に割り当てる必要があります。 これらのユーザーによるアクティビティは、とりわけ運用環境の組織に対するものについては、注意深く監査 する必要があります。

| Actions | Description |
| --- | --- |
| microsoft.directory/b2cTrustFrameworkPolicy/allProperties/allTasks | Azure Active Directory B2C のカスタム ポリシーを読み取り、構成する |

### 課金管理者

購入、サブスクリプションの管理、サポート チケットの管理、サービスの正常性の監視を行います。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.commerce.billing/allEntities/allProperties/allTasks | Office 365 課金のすべての側面を管理する |
| microsoft.directory/organization/basic/update | 組織で基本プロパティを更新する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Cloud App Security 管理者

このロールが割り当てられたユーザーは、Defender for Cloud Apps でフル アクセス許可を持ちます。 管理者の追加、Microsoft Defender for Cloud Apps のポリシーと設定の追加、ログのアップロードを行うことができ、ガバナンス アクションを実行できます。

| Actions | Description |
| --- | --- |
| microsoft.directory/cloudAppSecurity/allProperties/allTasks | Microsoft Defender for Cloud Apps ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### クラウド アプリケーション管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、(アプリケーション プロキシを管理する権限を除き) アプリケーション管理者ロールと同じアクセス許可を持ちます。 このロールは、エンタープライズ アプリケーションとアプリケーション登録の全側面を作成して管理する権限を付与します。 このロールに割り当てられたユーザーは、新しいアプリケーション登録またはエンタープライズ アプリケーションを作成する際に、所有者として追加されません。

さらに、このロールは、委任されたアクセス許可とアプリケーション アクセス許可 (Azure AD Graph と Microsoft Graph に対するアプリケーション アクセス許可を除く) に同意する権限を付与します。

Important

この例外は、 *他* のアプリ (他の Microsoft アプリ、サード パーティ製アプリ、登録したアプリなど) に対するアプリケーションのアクセス許可に同意できることを意味します。 これらのアクセス許可はアプリの登録の一部として引き続き *要求* できますが、これらのアクセス許可を *付与* する (つまり、同意する) には、特権ロール管理者などのより特権のある管理者が必要です。

このロールは、アプリケーションの資格情報を管理する権限を付与します。 このロールが割り当てられているユーザーは、アプリケーションに資格情報を追加し、それらの資格情報を使用してアプリケーションの ID を偽装できます。 ユーザーまたはその他のオブジェクトを作成または更新する機能など、アプリケーションの ID にリソースへのアクセスが許可されている場合、このロールに割り当てられたユーザーは、アプリケーションの偽装中にこれらのアクションを実行できます。 アプリケーションの ID を偽装するこの機能は、ユーザーがロールの割り当てを介して実行できる操作に対する特権の昇格である可能性があります。 アプリケーション管理者ロールにユーザーを割り当てると、アプリケーションの ID を偽装できることを理解しておくことが重要です。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/adminConsentRequestPolicy/allProperties/allTasks | Microsoft Entra ID で管理者の同意要求ポリシーを管理する |
| microsoft.directory/appConsent/appConsentRequests/allProperties/read | Microsoft Entra ID に登録されているアプリケーションに対する同意要求のすべてのプロパティを読み取る |
| microsoft.directory/applicationPolicies/basic/update | アプリケーション ポリシーの標準プロパティを更新する |
| microsoft.directory/applicationPolicies/create | アプリケーション ポリシーを作成する |
| microsoft.directory/applicationPolicies/delete | アプリケーション ポリシーを削除する |
| microsoft.directory/applicationPolicies/owners/read | アプリケーション ポリシーの所有者を読み取る |
| microsoft.directory/applicationPolicies/owners/update | アプリケーション ポリシーの所有者プロパティを更新する |
| microsoft.directory/applicationPolicies/policyAppliedTo/read | オブジェクト リストに適用されているアプリケーション ポリシーを読み取る |
| microsoft.directory/applicationPolicies/standard/read | アプリケーション ポリシーの標準プロパティを読み取る |
| microsoft.directory/applications/appRoles/update | すべての種類のアプリケーションで appRoles プロパティを更新する |
| microsoft.directory/applications/audience/update | アプリケーションの対象ユーザー プロパティを更新する |
| microsoft.directory/applications/authentication/update | すべての種類のアプリケーションで認証を更新する |
| microsoft.directory/applications/basic/update | アプリケーションの基本プロパティを更新する |
| microsoft.directory/applications/create | すべての種類のアプリケーションを作成する |
| microsoft.directory/applications/credentials/update | アプリケーション資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/applications/delete | すべての種類のアプリケーションを削除する |
| microsoft.directory/applications/disablement/update | ユーザーがサインインできるようにアプリケーションが有効になっているかどうかを更新する |
| microsoft.directory/applications/extensionProperties/update | アプリケーションの拡張機能プロパティを更新する |
| microsoft.directory/applications/notes/update | アプリケーションのメモを更新する |
| microsoft.directory/applications/owners/update | アプリケーションの所有者を更新する |
| microsoft.directory/applications/permissions/update | すべての種類のアプリケーションで、公開されたアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/applications/policies/update | アプリケーションのポリシーを更新する |
| microsoft.directory/applications/synchronization/standard/read | アプリケーション オブジェクトに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/applications/tag/update | アプリケーションのタグを更新する |
| microsoft.directory/applications/verification/update | applicationsverification プロパティを更新する |
| microsoft.directory/applicationTemplates/instantiate | アプリケーション テンプレートからギャラリー アプリケーションのインスタンスを作成する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/deletedItems.applications/delete | 復元できなくなったアプリケーションを完全に削除する |
| microsoft.directory/deletedItems.applications/restore | 論理的に削除されたアプリケーションを元の状態に復元する |
| microsoft.directory/oAuth2PermissionGrants/allProperties/allTasks | OAuth 2.0 アクセス許可の付与の作成と削除、およびすべてのプロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/servicePrincipals/audience/update | サービス プリンシパルで対象ユーザー プロパティを更新する |
| microsoft.directory/servicePrincipals/authentication/update | サービス プリンシパルで認証プロパティを更新する |
| microsoft.directory/servicePrincipals/basic/update | サービス プリンシパルで基本プロパティを更新する |
| microsoft.directory/servicePrincipals/create | サービス プリンシパルを作成する |
| microsoft.directory/servicePrincipals/credentials/update | サービス プリンシパルの資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/delete | サービス プリンシパルを削除する |
| microsoft.directory/servicePrincipals/disable | サービス プリンシパルを無効にする |
| microsoft.directory/servicePrincipals/enable | サービス プリンシパルを有効にする |
| microsoft.directory/servicePrincipals/getPasswordSingleSignOnCredentials | サービス プリンシパルのパスワード シングル サインオン資格情報を管理する |
| microsoft.directory/servicePrincipals/managePasswordSingleSignOnCredentials | サービス プリンシパルのパスワード シングル サインオン資格情報を読み取る |
| microsoft.directory/servicePrincipals/managePermissionGrantsForAll.microsoft-application-admin | Microsoft Graph と Azure AD Graph のアプリケーションアクセス許可を除き、すべてのユーザーまたはすべてのユーザーに代わって、アプリケーションのアクセス許可と委任されたアクセス許可に同意する |
| microsoft.directory/servicePrincipals/notes/update | サービス プリンシパルのメモを更新する |
| microsoft.directory/servicePrincipals/owners/update | サービス プリンシパルの所有者を更新する |
| microsoft.directory/servicePrincipals/permissions/update | サービスプリンシパルのアクセス許可を更新する |
| microsoft.directory/servicePrincipals/policies/update | サービス プリンシパルのポリシーを更新する |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/credentials/manage | アプリケーション プロビジョニングのシークレットと資格情報の管理。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/jobs/manage | アプリケーション プロビジョニングの同期ジョブの開始、再開、および一時停止。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/schema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/servicePrincipals/synchronizationCredentials/manage | アプリケーション プロビジョニングのシークレットと資格情報を管理する |
| microsoft.directory/servicePrincipals/synchronizationJobs/manage | アプリケーション プロビジョニングの同期ジョブを開始、再開、および一時停止する |
| microsoft.directory/servicePrincipals/synchronizationSchema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成、管理する |
| microsoft.directory/servicePrincipals/tag/update | サービスプリンシパルのタグ プロパティを更新する |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### クラウド デバイス管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、Microsoft Entra ID でデバイスを有効化、無効化、および削除することができ、Azure portal で Windows 10 の BitLocker キーを読み取る (ある場合) ことができます。 このロールでは、デバイス上の他のプロパティを管理するアクセス許可の付与は行いません。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/deletedItems.devices/delete | 復元できなくなったデバイスを完全に削除する |
| microsoft.directory/deletedItems.devices/restore | 論理的に削除されたデバイスを元の状態に復元する |
| microsoft.directory/deviceLocalCredentials/password/read | Microsoft Entra 参加済みデバイスのバックアップされたローカル管理者アカウント資格情報 (パスワードを含む) のすべてのプロパティを読み取る |
| microsoft.directory/deviceManagementPolicies/basic/update | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する基本的なプロパティを更新します[Image: 特権ラベル アイコン。] |
| microsoft.directory/deviceManagementPolicies/standard/read | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する標準のプロパティを読み取る |
| microsoft.directory/deviceRegistrationPolicy/basic/update | デバイス登録ポリシーの基本プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/deviceRegistrationPolicy/standard/read | デバイス登録ポリシーの標準プロパティを読み取る |
| microsoft.directory/devices/delete | Microsoft Entra ID からデバイスを削除する |
| microsoft.directory/devices/disable | Microsoft Entra ID でデバイスを無効にする |
| microsoft.directory/devices/enable | Microsoft Entra ID でデバイスを有効にする |
| microsoft.directory/devices/permissions/update | IoT デバイスの代替名プロパティを更新する |
| microsoft.directory/deviceTemplates/owners/read | モノのインターネット (IoT) デバイス テンプレートの所有者を読み取る |
| microsoft.directory/deviceTemplates/owners/update | モノのインターネット (IoT) デバイス テンプレートの所有者を更新する |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |

### コンプライアンス管理者

このロールを持つユーザーには、Microsoft Purview ポータル、Microsoft 365 管理センター、Azure、および Microsoft Defender ポータルでコンプライアンス関連の機能を管理するためのアクセス許可があります。 割り当て先は Exchange 管理センター内のすべての機能を管理し、Azure と Microsoft 365 のサポート チケットを作成することもできます。 詳細については、「 [Microsoft Defender for Office 365 および Microsoft Purview の役割と役割グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions)」を参照してください。

| In | 実行できる |
| --- | --- |
| [Microsoft Purview ポータル](https://learn.microsoft.com/ja-jp/purview/purview-portal) | Microsoft 365 サービス全体での組織のデータの保護および管理コンプライアンス アラートの管理 |
| [Microsoft Purview コンプライアンス マネージャー](https://learn.microsoft.com/ja-jp/purview/compliance-manager) | 組織の法令遵守活動の追跡、割り当て、確認 |
| [Microsoft Defender ポータル](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-365-defender-portal) | データ ガバナンスの管理法律およびデータ調査の実行データ主体の要求の管理このロールには、Microsoft Defender ポータルの役割ベースのアクセス制御の [コンプライアンス管理者役割グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions) と同じアクセス許可があります。 |
| [Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control) | Intune のすべての監査データの表示 |
| [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/manage-admins) | 読み取り専用アクセス許可があり、アラートを管理できるファイル ポリシーの作成と変更、ファイル ガバナンス アクションの許可データ管理下のすべての組み込みレポートの表示 |

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/entitlementManagement/allProperties/read | Microsoft Entra エンタイトルメント管理ですべてのプロパティを読み取る |
| microsoft.office365.complianceManager/allEntities/allTasks | Office 365 コンプライアンス マネージャーの全側面の管理 |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### コンプライアンス データ管理者

このロールを持つユーザーには、Microsoft Purview ポータル、Microsoft 365 管理センター、Azure でデータを追跡するアクセス許可があります。 ユーザーは、Exchange 管理センター、Compliance Manager、Teams および Skype for Business の管理センター内のコンプライアンス データを追跡したり、Azure および Microsoft 365 のサポート チケットを作成したりすることもできます。 コンプライアンス管理者とコンプライアンス データ管理者の違いの詳細については、「[Microsoft Defender for Office 365 および Microsoft Purview コンプライアンスのロールとロール グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions)」を参照してください。

| In | 実行できる |
| --- | --- |
| [Microsoft Purview ポータル](https://learn.microsoft.com/ja-jp/purview/purview-portal) | Microsoft 365 サービス全体のコンプライアンス関連ポリシーの監視コンプライアンス アラートの管理 |
| [Microsoft Purview コンプライアンス マネージャー](https://learn.microsoft.com/ja-jp/purview/compliance-manager) | 組織の法令遵守活動の追跡、割り当て、確認 |
| [Microsoft 365 Defender ポータル](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-365-defender-portal) | データ ガバナンスの管理法律およびデータ調査の実行データ主体の要求の管理このロールには、Microsoft 365 Defender ポータルのロールベースのアクセス制御の[コンプライアンス データ管理者ロール グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions)と同じアクセス許可が付与されています。 |
| [Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control) | Intune のすべての監査データの表示 |
| [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/manage-admins) | 読み取り専用アクセス許可があり、アラートを管理できるファイル ポリシーの作成と変更、ファイル ガバナンス アクションの許可データ管理下のすべての組み込みレポートの表示 |

| Actions | Description |
| --- | --- |
| microsoft.azure.informationProtection/allEntities/allTasks | Azure Information Protection のすべての側面を管理する |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/cloudAppSecurity/allProperties/allTasks | Microsoft Defender for Cloud Apps ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.office365.complianceManager/allEntities/allTasks | Office 365 コンプライアンス マネージャーの全側面の管理 |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 条件付きアクセス管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、Microsoft Entra の条件付きアクセスの設定を管理できます。

| Actions | Description |
| --- | --- |
| microsoft.directory/conditionalAccessPolicies/basic/update | 条件付きアクセス ポリシーの基本プロパティを更新する |
| microsoft.directory/conditionalAccessPolicies/create | 条件付きアクセス ポリシーを作成する |
| microsoft.directory/conditionalAccessPolicies/delete | 条件付きアクセス ポリシーを削除する |
| microsoft.directory/conditionalAccessPolicies/owners/read | 条件付きアクセス ポリシーの所有者を読み取る |
| microsoft.directory/conditionalAccessPolicies/owners/update | 条件付きアクセス ポリシーの所有者を更新する |
| microsoft.directory/conditionalAccessPolicies/policyAppliedTo/read | 条件付きアクセス ポリシーの "適用先" プロパティを読み取る |
| microsoft.directory/conditionalAccessPolicies/standard/read | ポリシーの条件付きアクセスを読み取る |
| microsoft.directory/conditionalAccessPolicies/tenantDefault/update | 条件付きアクセス ポリシーのデフォルト テナントを更新する |
| microsoft.directory/namedLocations/basic/update | ネットワークの場所を定義するカスタム ルールの基本プロパティを更新する |
| microsoft.directory/namedLocations/create | ネットワークの場所を定義するカスタム ルールを作成する |
| microsoft.directory/namedLocations/delete | ネットワークの場所を定義するカスタム ルールを削除する |
| microsoft.directory/namedLocations/standard/read | ネットワークの場所を定義するカスタム ルールの基本プロパティを読み取る |
| microsoft.directory/resourceNamespaces/resourceActions/authenticationContext/update | Microsoft 365 ロールベースのアクセス制御 (RBAC) リソース アクションの条件付きアクセス認証コンテキストを更新します[Image: 特権ラベル アイコン。] |

### 顧客代理管理者リレーションシップ管理者

次のタスクを実行する必要があるユーザーに、顧客代理管理者リレーションシップ管理者ロールを割り当てます。

- テナントのパートナーから詳細な委任された管理者特権 (GDAP) 関係を受け入れます。
- パートナーとの GDAP 関係を一覧表示および表示します。
- パートナーとの GDAP 関係を終了します。

| Actions | Description |
| --- | --- |
| microsoft.commerce.tenantRelationships/customerDelegatedAdminPrivileges/allProperties/allTasks | 顧客テナントの詳細な委任された管理者特権 (GDAP) リレーションシップのすべての側面を管理します。 |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### カスタマー ロックボックス アクセス承認者

組織内の [Microsoft Purview カスタマー ロックボックス要求](https://learn.microsoft.com/ja-jp/purview/customer-lockbox-requests)を管理します。 カスタマー ロックボックス要求の電子メール通知を受信し、Microsoft 365 管理センターから要求の承認と拒否を行うことができます。 カスタマー ロックボックス機能を有効または無効にすることもできます。 グローバル管理者のみが、このロールに割り当てられているユーザーのパスワードをリセットできます。

| Actions | Description |
| --- | --- |
| microsoft.office365.lockbox/allEntities/allTasks | カスタマー ロックボックスのすべての側面を管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### デスクトップ Analytics 管理者

このロールのユーザーは、Desktop Analytics サービスを管理できます。 これには、資産インベントリの表示、デプロイ プランの作成、およびデプロイと正常性の状態の表示に対する権限が含まれます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.desktopAnalytics/allEntities/allTasks | Desktop Analytics のすべての側面を管理する |

### ディレクトリ 閲覧者

このロールのユーザーは、基本的なディレクトリ情報を読み取ることができます。 このロールは次の目的で使用してください。

- 読み取りアクセスをすべてのゲスト ユーザーに付与せず、特定のゲスト ユーザー セットに付与する。
- [Restrict access to Microsoft Entra 管理センター] (Microsoft Entra 管理センターへのアクセスを制限する) が [はい] に設定されている場合に、管理者以外の特定のユーザー セットに Entra ポータルへのアクセスを付与する。
- Directory.Read.All を選択できないディレクトリへのアクセスをサービス プリンシパルに付与する。

| Actions | Description |
| --- | --- |
| microsoft.directory/administrativeUnits/members/read | 管理単位のメンバーを読み取る |
| microsoft.directory/administrativeUnits/standard/read | 管理単位の基本プロパティを読み取る |
| microsoft.directory/applicationPolicies/standard/read | アプリケーション ポリシーの標準プロパティを読み取る |
| microsoft.directory/applications/owners/read | アプリケーションの所有者を読み取る |
| microsoft.directory/applications/policies/read | アプリケーションのポリシーを読み取る |
| microsoft.directory/applications/standard/read | アプリケーションの標準プロパティを読み取る |
| microsoft.directory/contacts/memberOf/read | Microsoft Entra ID ですべての連絡先のグループ メンバーシップを読み取る |
| microsoft.directory/contacts/standard/read | Microsoft Entra ID で連絡先の基本プロパティを読み取る |
| microsoft.directory/contracts/standard/read | パートナー コントラクトの基本プロパティを読み取る |
| microsoft.directory/devices/memberOf/read | デバイス メンバーシップを読み取る |
| microsoft.directory/devices/registeredOwners/read | デバイスの登録済み所有者を読み取る |
| microsoft.directory/devices/registeredUsers/read | デバイスの登録済みユーザーを読み取る |
| microsoft.directory/devices/standard/read | デバイスで基本プロパティを読み取る |
| microsoft.directory/directoryRoles/eligibleMembers/read | Microsoft Entra ロールの対象メンバーを読み取る |
| microsoft.directory/directoryRoles/members/read | Microsoft Entra ロールのすべてのメンバーを読み取る |
| microsoft.directory/directoryRoles/standard/read | Microsoft Entra ロールの基本プロパティを読み取る |
| microsoft.directory/domains/standard/read | ドメインで基本プロパティを読み取る |
| microsoft.directory/groups/appRoleAssignments/read | グループのアプリケーション ロールの割り当てを読み取る |
| microsoft.directory/groups/memberOf/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの memberOf プロパティを読み取る |
| microsoft.directory/groups/members/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループのメンバーを読み取る |
| microsoft.directory/groups/owners/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの所有者を読み取る |
| microsoft.directory/groups/settings/read | グループの設定を読み取る |
| microsoft.directory/groups/standard/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの標準プロパティを読み取る |
| microsoft.directory/groupSettings/standard/read | グループ設定の基本プロパティを読み取る |
| microsoft.directory/groupSettingTemplates/standard/read | グループ設定テンプレートの基本プロパティを読み取る |
| microsoft.directory/oAuth2PermissionGrants/standard/read | OAuth 2.0 アクセス許可付与の基本プロパティを読み取る |
| microsoft.directory/organization/standard/read | 組織で基本プロパティを読み取る |
| microsoft.directory/organization/trustedCAsForPasswordlessAuth/read | パスワードレス認証用に信頼された証明機関を読み取る |
| microsoft.directory/roleAssignments/standard/read | ロールの割り当ての基本プロパティを読み取る |
| microsoft.directory/roleDefinitions/standard/read | ロールの定義の基本プロパティを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/read | サービス プリンシパルのロールの割り当てを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignments/read | サービス プリンシパルに割り当てられたロールの割り当てを読み取る |
| microsoft.directory/servicePrincipals/memberOf/read | サービス プリンシパルのグループ メンバーシップを読み取る |
| microsoft.directory/servicePrincipals/oAuth2PermissionGrants/read | サービス プリンシパルの委任されたアクセス許可付与を読み取る |
| microsoft.directory/servicePrincipals/ownedObjects/read | サービスプリンシパルの所有オブジェクトを読み取る |
| microsoft.directory/servicePrincipals/owners/read | サービス プリンシパルの所有者を読み取る |
| microsoft.directory/servicePrincipals/policies/read | サービス プリンシパルのポリシーを読み取る |
| microsoft.directory/servicePrincipals/standard/read | サービス プリンシパルの基本プロパティを読み取る |
| microsoft.directory/subscribedSkus/standard/read | サブスクリプションの基本プロパティの読み取り |
| microsoft.directory/users/appRoleAssignments/read | ユーザーのアプリケーション ロールの割り当てを読み取る |
| microsoft.directory/users/deviceForResourceAccount/read | ユーザーの deviceForResourceAccount を読み取る |
| microsoft.directory/users/directReports/read | ユーザーの直属の部下を読み取る |
| microsoft.directory/users/invitedBy/read | 外部ユーザーをテナントに招待したユーザーを読み取る |
| microsoft.directory/users/licenseDetails/read | ユーザーのライセンスの詳細を読み取る |
| microsoft.directory/users/manager/read | ユーザーのマネージャーを読み取る |
| microsoft.directory/users/memberOf/read | ユーザーのグループ メンバーシップを読み取る |
| microsoft.directory/users/oAuth2PermissionGrants/read | ユーザーの委任されたアクセス許可付与を読み取る |
| microsoft.directory/users/ownedDevices/read | ユーザーの所有デバイスを読み取る |
| microsoft.directory/users/ownedObjects/read | ユーザーの所有オブジェクトを読み取る |
| microsoft.directory/users/photo/read | ユーザーの写真を読み取る |
| microsoft.directory/users/registeredDevices/read | ユーザーの登録済みデバイスを読み取る |
| microsoft.directory/users/scopedRoleMemberOf/read | 管理単位にスコープが設定されている Microsoft Entra ロールのユーザーのメンバーシップを読み取る |
| microsoft.directory/users/sponsorOf/read | ユーザーがスポンサーになっているすべてのエージェントまたはアプリケーションを読み取ります。 |
| microsoft.directory/users/sponsors/read | ユーザーのスポンサーを読み取る |
| microsoft.directory/users/standard/read | ユーザーの基本プロパティを読み取る |

### ディレクトリ同期アカウント

使用しないでください。 このロールは、自動的に Microsoft Entra Connect サービスに割り当てられます。他の用途に使用するためのものではなく、他の用途ではサポートされていません。

| Actions | Description |
| --- | --- |
| microsoft.directory/onPremisesSynchronization/standard/read | 標準のオンプレミス ディレクトリ同期情報を読み取る |

### ディレクトリ ライター

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、ユーザー、グループ、およびサービス プリンシパルの基本情報の読み取りと更新が可能です。

| Actions | Description |
| --- | --- |
| microsoft.directory/applications/extensionProperties/update | アプリケーションの拡張機能プロパティを更新する |
| microsoft.directory/contacts/create | 連絡先を作成する |
| microsoft.directory/groups/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類のグループの割り当てられたラベル プロパティを更新する |
| microsoft.directory/groups/assignLicense | グループベースのライセンスのグループに製品ライセンスを割り当てる |
| microsoft.directory/groups/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups/classification/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの分類プロパティを更新する |
| microsoft.directory/groups/create | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを作成する |
| microsoft.directory/groups/dynamicMembershipRule/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの動的メンバーシップ ルールを更新する |
| microsoft.directory/groups/groupType/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのグループの種類に影響を与えるプロパティを更新する |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups/onPremWriteBack/update | Microsoft Entra Connect を使用してオンプレミスに書き戻す Microsoft Entra グループを更新する |
| microsoft.directory/groups/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups/reprocessLicenseAssignment | グループベースのライセンスのライセンス割り当てを再処理する |
| microsoft.directory/groups/settings/update | グループの設定を更新する |
| microsoft.directory/groups/visibility/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの可視性プロパティを更新する |
| microsoft.directory/groupSettings/basic/update | グループ設定の基本プロパティを更新する |
| microsoft.directory/groupSettings/create | グループ設定の作成 |
| microsoft.directory/groupSettings/delete | グループ設定を削除する |
| microsoft.directory/oAuth2PermissionGrants/basic/update | OAuth 2.0 アクセス許可付与を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/oAuth2PermissionGrants/create | OAuth 2.0 アクセス許可付与を作成する[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/credentials/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングのシークレットと資格情報を管理します。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/jobs/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングの同期ジョブを開始、再起動、一時停止します。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/schema/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/credentials/manage | アプリケーション プロビジョニングのシークレットと資格情報の管理。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/jobs/manage | アプリケーション プロビジョニングの同期ジョブの開始、再開、および一時停止。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/schema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronizationCredentials/manage | アプリケーション プロビジョニングのシークレットと資格情報を管理する |
| microsoft.directory/servicePrincipals/synchronizationJobs/manage | アプリケーション プロビジョニングの同期ジョブを開始、再開、および一時停止する |
| microsoft.directory/servicePrincipals/synchronizationSchema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成、管理する |
| microsoft.directory/users/assignLicense | ユーザー ライセンスの管理 |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/create | ユーザーの追加[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/disable | ユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/enable | ユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/inviteGuest | ゲスト ユーザーを招待する |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/photo/update | ユーザーの写真を更新する |
| microsoft.directory/users/reprocessLicenseAssignment | ユーザー ライセンス割り当てを再処理する |
| microsoft.directory/users/sponsors/update | ユーザーのスポンサーを更新する |
| microsoft.directory/users/userPrincipalName/update | ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |

### ドメイン名管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、ドメイン名の管理 (読み取り、追加、検証、更新、および削除) を行うことができます。 また、ユーザー、グループ、およびアプリケーションに関するディレクトリ情報も読み取ることができます。これらのオブジェクトはドメインの依存関係を所有しているためです。 オンプレミス環境の場合、このロールを持つユーザーは、関連付けられたユーザーが常にオンプレミスで認証されるように、フェデレーションのドメイン名を構成できます。 これらのユーザーは、シングル サインオンを介してオンプレミスのパスワードを使用して Microsoft Entra ベースのサービスにサインインできます。 フェデレーション設定を Microsoft Entra Connect 経由で同期する必要があるため、ユーザーは Microsoft Entra Connect を管理する権限も持っています。

| Actions | Description |
| --- | --- |
| microsoft.directory/domains/allProperties/allTasks | ドメインの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ドラゴン管理者

次のタスクを実行する必要があるユーザーに Dragon Administrator ロールを割り当てます。

- Dragon 管理センターで管理エクスペリエンスのすべての側面を管理する
- 臨床アプリケーションのプロビジョニング
- 組織階層の作成と管理
- 医療グループを監視する
- 電子健康記録 (EHR) システムに組み込まれているさまざまな臨床アプリケーションのエクスペリエンスを管理する
- 設定の管理、分析の表示、ライブラリ オブジェクトの処理など、臨床アプリケーションを構成する
- Dragon 管理センターで組織のサポート チケットを作成、管理、表示する
- 組織が購入したライセンスの課金プランを作成、表示、管理、監視する (追加のロールが必要になる場合があります)

[詳細情報](https://learn.microsoft.com/ja-jp/industry/healthcare/dragon-admin-center/get-started/)

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.healthPlatform/allEntities/allProperties/allTasks | Microsoft Dragon 管理センターのすべての側面を管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Dynamics 365 管理者

構成、ユーザー管理、サポート チケットなど、Dynamics 365 サービスのすべての側面を管理する必要があるユーザーに Dynamics 365 管理者ロールを割り当てます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.dynamics365/allEntities/allTasks | Dynamics 365 のすべての側面を管理する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Dynamics 365 Business Central 管理者

次のタスクを行う必要があるユーザーに、Dynamics 365 Business Central 管理者ロールを割り当てます。

- Dynamics 365 Business Central 環境にアクセスする
- 環境ですべての管理タスクを実行する
- 顧客の環境のライフサイクルを管理する
- 環境にインストールされている拡張機能を監視する
- 環境のアップグレードを制御する
- 環境のデータ エクスポートを実行する
- Azure と Microsoft 365 Service Health のダッシュボードを読み取りおよび構成する

このロールでは、他の Dynamics 365 製品に対するアクセス許可は提供されません。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/domains/standard/read | ドメインで基本プロパティを読み取る |
| microsoft.directory/organization/standard/read | 組織で基本プロパティを読み取る |
| microsoft.directory/subscribedSkus/standard/read | サブスクリプションの基本プロパティの読み取り |
| microsoft.directory/users/standard/read | ユーザーの基本プロパティを読み取る |
| microsoft.dynamics365.businessCentral/allEntities/allProperties/allTasks | Dynamics 365 Business Central のすべての側面を管理する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Edge 管理者

このロールのユーザーは、Microsoft Edge で Internet Explorer モードに必要なエンタープライズ サイト リストを作成および管理できます。 このロールには、サイト リストを作成、編集、発行するアクセス許可が付与され、さらにサポート チケットを管理するためのアクセスが許可されます。

[詳細情報](https://go.microsoft.com/fwlink/?linkid=2165707)

| Actions | Description |
| --- | --- |
| microsoft.edge/allEntities/allProperties/allTasks | Microsoft Edge のすべての側面を管理する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Entra バックアップ管理者

次のタスクを実行する必要があるユーザーに Entra Backup Administrator ロールを割り当てます。

- テナント内のすべてのスナップショットを一覧表示する
- 過去のバックアップの差分レポート (プレビュー ジョブ) を作成し、必要に応じてスコープ フィルターを含める
- バックアップと回復のスコープ内にある変更されたディレクトリ オブジェクトの状態を比較する
- サポートされているスコープ フィルターを使用してディレクトリ オブジェクトをフィルター処理する
- ジョブの読み取り状態
- プレビュー ジョブと回復ジョブを含むすべてのジョブを一覧表示する
- 回復ジョブをトリガーし、必要に応じてスコープ フィルターを含める

| Actions | Description |
| --- | --- |
| microsoft.directory/auditLogs/standard/read | カスタム セキュリティ属性監査ログを除く監査ログの標準プロパティの読み取り |
| microsoft.directory/backup/preview/cancel | Microsoft Entra バックアップ操作を取り消して、バックアップ スナップショットを現在の状態と比較します。 |
| microsoft.directory/backup/preview/create | ユーザーがバックアップ スナップショットを現在の状態と比較できるようにする Microsoft Entra バックアップ操作を作成します。 |
| microsoft.directory/backup/recovery/cancel | Microsoft Entra 回復操作を取り消してバックアップ スナップショットの内容を回復する |
| microsoft.directory/backup/recovery/create | ユーザーがバックアップ スナップショットの内容を回復できるようにする Microsoft Entra 回復操作を作成します。 |
| microsoft.directory/backup/standard/read | Microsoft Entra バックアップ (バックアップ ID やタイムスタンプなど) を一覧表示し、差分レポートを表示し、回復ジョブとそれに関連するプロパティを一覧表示します。 |

### Entra バックアップ リーダー

次のタスクを実行する必要があるユーザーに Entra Backup 閲覧者ロールを割り当てます。

- テナント内のすべてのスナップショットを一覧表示する
- 過去のバックアップの差分レポート (プレビュー ジョブ) を作成し、必要に応じてスコープ フィルターを含める
- バックアップと回復のスコープ内にある変更されたディレクトリ オブジェクトの状態を比較する
- サポートされているスコープ フィルターを使用してディレクトリ オブジェクトをフィルター処理する
- ジョブの読み取り状態
- プレビュー ジョブや回復ジョブを含むすべてのジョブを表示する

| Actions | Description |
| --- | --- |
| microsoft.directory/auditLogs/standard/read | カスタム セキュリティ属性監査ログを除く監査ログの標準プロパティの読み取り |
| microsoft.directory/backup/preview/cancel | Microsoft Entra バックアップ操作を取り消して、バックアップ スナップショットを現在の状態と比較します。 |
| microsoft.directory/backup/preview/create | ユーザーがバックアップ スナップショットを現在の状態と比較できるようにする Microsoft Entra バックアップ操作を作成します。 |
| microsoft.directory/backup/standard/read | Microsoft Entra バックアップ (バックアップ ID やタイムスタンプなど) を一覧表示し、差分レポートを表示し、回復ジョブとそれに関連するプロパティを一覧表示します。 |

### Entra SOC Identity Responder

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 Microsoft Defender ポータルから次のタスクを実行する必要があるユーザーに Entra SOC Identity Responder ロールを割り当てます。

- アクティブなセキュリティ インシデント中にユーザー アカウントを無効にして有効にする
- 更新トークンを無効にして、侵害されたユーザーのアクティブなサインイン セッションを取り消す
- 侵害されたユーザー アカウントのパスワードをリセットする

| Actions | Description |
| --- | --- |
| microsoft.directory/users/disable | ユーザーを無効にする |
| microsoft.directory/users/enable | ユーザーを有効にする |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |

### Exchange 管理者

このロールが割り当てられたユーザーは、Microsoft Exchange Online 内でグローバル アクセス許可を持ちます (このサービスが存在する場合)。 また、すべての Microsoft 365 グループの作成および管理、サポート チケットの管理、サービスの正常性の監視を行うこともできます。 詳しくは、「[Microsoft 365 管理センターでの管理者ロールについて](https://learn.microsoft.com/ja-jp/microsoft-365/admin/add-users/about-admin-roles)」をご覧ください。

Note

Microsoft Graph API と Microsoft Graph PowerShell では、このロールは Exchange サービス管理者という名前です。 [Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-overview) では、Exchange Administrator という名前が付けられています。 [Exchange 管理センター](https://learn.microsoft.com/ja-jp/exchange/exchange-admin-center)では、Exchange Online 管理者という名前です。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.backup/exchangeProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で Exchange Online 保護ポリシーを作成および管理する |
| microsoft.backup/exchangeRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での Exchange Online の復元セッションを読み取りおよび構成する |
| microsoft.backup/restorePoints/userMailboxes/allProperties/allTasks | M365 バックアップで選択した Exchange Online メールボックスに関連付けられているすべての復元ポイントを管理する |
| microsoft.backup/userMailboxProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で Exchange Online 保護ポリシーに追加されたメールボックスを管理する |
| microsoft.backup/userMailboxRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で Exchange Online の復元セッションに追加されたメールボックスを管理する |
| microsoft.directory/contacts/allProperties/read | 連絡先のすべてのプロパティを読み取る |
| microsoft.directory/contacts/memberOf/read | Microsoft Entra ID ですべての連絡先のグループ メンバーシップを読み取る |
| microsoft.directory/contacts/standard/read | Microsoft Entra ID で連絡先の基本プロパティを読み取る |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups.unified/basic/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.unified/create | ロールを割り当て可能なグループを除き、Microsoft 365 グループを作成する |
| microsoft.directory/groups.unified/delete | ロールを割り当て可能なグループを除き、Microsoft 365 グループを削除する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified/owners/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups.unified/restore | ロール割り当て可能なグループを除く、論理的に削除されたコンテナーから Microsoft 365 グループを復元する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.directory/onPremisesSynchronization/standard/read | 標準のオンプレミス ディレクトリ同期情報を読み取る |
| microsoft.office365.exchange/allEntities/basic/allTasks | Exchange Online のすべての側面を管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Exchange バックアップ管理者

次のタスクを実行する必要があるユーザーに Exchange Backup 管理者ロールを割り当てます。

- Microsoft 365 バックアップ for Exchange Online のすべての側面を管理する
- Exchange Online の詳細な復元を含むコンテンツのバックアップと復元
- Exchange Online のバックアップ構成ポリシーを作成、編集、管理する
- Exchange Online の復元操作を実行する

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.backup/exchangeProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で Exchange Online 保護ポリシーを作成および管理する |
| microsoft.backup/exchangeRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での Exchange Online の復元セッションを読み取りおよび構成する |
| microsoft.backup/restorePoints/userMailboxes/allProperties/allTasks | M365 バックアップで選択した Exchange Online メールボックスに関連付けられているすべての復元ポイントを管理する |
| microsoft.backup/userMailboxProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で Exchange Online 保護ポリシーに追加されたメールボックスを管理する |
| microsoft.backup/userMailboxRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で Exchange Online の復元セッションに追加されたメールボックスを管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Exchange 受信者管理者

このロールのユーザーには、Exchange Online の受信者に対する読み取りアクセスと、それらの受信者の属性に対する書き込みアクセスがあります。 詳しくは、「[Exchange Server の受信者](https://learn.microsoft.com/ja-jp/exchange/recipients/recipients)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.office365.exchange/migration/allProperties/allTasks | Exchange Online での受信者の移行に関連するすべてのタスクを管理する |
| microsoft.office365.exchange/recipients/allProperties/allTasks | Exchange Online でのすべての受信者の作成と削除、および受信者のすべてのプロパティの読み取りと更新を行う |

### 拡張ディレクトリ ユーザー管理者

| Actions | Description |
| --- | --- |
| microsoft.directory/externalUserProfiles/basic/update | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの基本プロパティを更新する |
| microsoft.directory/externalUserProfiles/delete | Teams の拡張ディレクトリ内の外部ユーザー プロファイルを削除する |
| microsoft.directory/externalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/pendingExternalUserProfiles/basic/update | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの基本プロパティを更新する |
| microsoft.directory/pendingExternalUserProfiles/create | Teams の拡張ディレクトリに外部ユーザー プロファイルを作成する |
| microsoft.directory/pendingExternalUserProfiles/delete | Teams の拡張ディレクトリ内の外部ユーザー プロファイルを削除する |
| microsoft.directory/pendingExternalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |

### 外部 ID ユーザー フロー管理者

このロールが割り当てられたユーザーは、Azure portal でユーザー フロー ("組み込み" ポリシーとも呼ばれます) を作成および管理することができます。 これらのユーザーは、HTML/CSS/JavaScript コンテンツのカスタマイズ、MFA 要件の変更、トークン内のクレームの選択、API コネクタおよび資格証明の管理、および Microsoft Entra 組織内のすべてのユーザー フローのセッション設定の構成を行うことができます。 その一方で、このロールには、ユーザーのデータを確認したり、組織スキーマに含まれている属性を変更したりする機能は含まれていません。 Identity Experience Framework ポリシー (カスタム ポリシーとも呼ばれます) の変更も、このロールの範囲外です。

| Actions | Description |
| --- | --- |
| microsoft.directory/b2cUserFlow/allProperties/allTasks | Azure Active Directory B2Cのユーザーフローの読み取りと構成 |

### 外部 ID ユーザー フロー属性管理者

このロールが割り当てられたユーザーは、Microsoft Entra 組織内のすべてのユーザー フローで使用可能なカスタム属性を追加または削除できます。 そのため、このロールを持つユーザーは、エンド ユーザー スキーマに新しい要素を変更または追加し、すべてのユーザー フローの動作に影響を与え、間接的にエンド ユーザーに要求されるデータが変更され、最終的にアプリケーションに要求として送信される可能性があります。 このロールでは、ユーザー フローを編集できません。

| Actions | Description |
| --- | --- |
| microsoft.directory/b2cUserAttribute/allProperties/allTasks | Azure Active Directory B2Cのユーザー属性を読み取り、構成する |

### 外部 ID プロバイダー管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 この管理者は、Microsoft Entra 組織と外部 ID プロバイダー間のフェデレーションを管理します。 このロールが割り当てられたユーザーは、新しい ID プロバイダーを追加し、使用可能なすべての設定 (認証パス、サービス ID、割り当てられたキー コンテナーなど) を構成できます。 このユーザーは、Microsoft Entra 組織が外部 ID プロバイダーからの認証を信頼できるようにすることができます。 その結果としてエンド ユーザー エクスペリエンスに及ぼす影響は、組織の種類によって異なります。

- 従業員とパートナー向けの Microsoft Entra 組織:(たとえば Gmail との) フェデレーションの追加は、まだ招待に応じていないすべてのゲストの招待にすぐに影響します。 「[Google を B2B ゲスト ユーザーの ID プロバイダーとして追加する](https://learn.microsoft.com/ja-jp/entra/external-id/google-federation)」を参照してください。
- Azure Active Directory B2C 組織:(たとえば Facebook、または別の Microsoft Entra 組織との) フェデレーションの追加は、ユーザー フロー (組み込みポリシーとも呼ばれます) で ID プロバイダーがオプションとして追加されるまで、エンド ユーザー フローにすぐに影響することはありません。 例については、[ID プロバイダーとしての Microsoft アカウントの構成](https://learn.microsoft.com/ja-jp/azure/active-directory-b2c/identity-provider-microsoft-account)に関するページを参照してください。 ユーザー フローを変更するには、"B2C ユーザー フロー管理者" の制限されたロールが必要です。

| Actions | Description |
| --- | --- |
| microsoft.directory/domains/federation/update | ドメインのフェデレーション プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/identityProviders/allProperties/allTasks | Azure Active Directory B2C の ID プロバイダーを読み取り、構成する[Image: 特権ラベル アイコン。] |

### ファブリック管理者

このロールが割り当てられたユーザーは、Microsoft Fabric と Power BI 内でグローバル アクセス許可を持ちます (該当サービスが存在するとき)。また、サポート チケットを管理し、サービス正常性を監視できます。 詳細については、[Fabric 管理者ロール](https://learn.microsoft.com/ja-jp/fabric/admin/roles)に関する記事を参照してください。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.powerApps.powerBI/allEntities/allTasks | Fabric と Power BI のすべての側面を管理します |

### グローバル管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールを持つユーザーは、Microsoft Entra ID のすべての管理機能、および Microsoft Defender ポータル、Microsoft Purview ポータル、Exchange Online、SharePoint Online、Skype for Business Online などの Microsoft Entra ID を使用するサービスにアクセスできます。 グローバル管理者は、Directory アクティビティ ログを表示できます。 さらにグローバル管理者は、[アクセスを昇格させる](https://learn.microsoft.com/ja-jp/azure/role-based-access-control/elevate-access-global-admin)ことで、すべての Azure サブスクリプションと管理グループを管理できます。 これにより、グローバル管理者は各 Microsoft Entra テナントを使用して、すべての Azure リソースに対するフル アクセスを取得できます。 Microsoft Entra 組織にサインアップしたユーザーがグローバル管理者になります。 会社に複数のグローバル管理者がいてもかまいません。 グローバル管理者は、すべてのユーザーと他のすべての管理者のパスワードをリセットできます。 グローバル管理者は、自分のグローバル管理者の割り当てを削除することはできません。 これは、組織に全体管理者がいなくなる状況を防ぐためです。

Note

ベスト プラクティスとして、組織内でグローバル管理者ロールを割り当てるユーザーは 5 人未満にすることをお勧めします。 詳細については、「[Microsoft Entra のロールのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/best-practices)」を参照してください。

| Actions | Description |
| --- | --- |
| microsoft.agentRegistry/allEntities/allProperties/allTasks | Microsoft Entra IDでエージェントレジストリのすべての側面を管理 |
| microsoft.azure.advancedThreatProtection/allEntities/allTasks | Azure 高度な脅威保護 のすべての側面を管理する |
| microsoft.azure.informationProtection/allEntities/allTasks | Azure Information Protection のすべての側面を管理する |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.backup/allEntities/allProperties/allTasks | Microsoft 365 バックアップ のすべての側面を管理する |
| microsoft.cloudPC/allEntities/allProperties/allTasks | Windows 365 のすべての側面を管理する |
| microsoft.commerce.billing/allEntities/allProperties/allTasks | Office 365 課金のすべての側面を管理する |
| microsoft.commerce.billing/purchases/standard/read | Microsoft 365 管理センターで購入サービスを読み取る。 |
| microsoft.commerce.tenantRelationships/customerDelegatedAdminPrivileges/allProperties/allTasks | 顧客テナントの詳細な委任された管理者特権 (GDAP) リレーションシップのすべての側面を管理します。 |
| microsoft.directory/accessReviews/allProperties/allTasks | Microsoft Entra ID でのアクセス レビューの作成と削除、およびアクセス レビューのすべてのプロパティの読み取りと更新 |
| microsoft.directory/accessReviews/definitions/allProperties/allTasks | Microsoft Entra ID ですべてのレビュー可能なリソースのアクセス レビューを管理する |
| microsoft.directory/adminConsentRequestPolicy/allProperties/allTasks | Microsoft Entra ID で管理者の同意要求ポリシーを管理する |
| microsoft.directory/administrativeUnits/allProperties/allTasks | 管理単位 (メンバーを含む) の作成と管理する |
| microsoft.directory/agentIdentities/appRoleAssignedTo/update | エージェント ID ロールの割り当てを更新する |
| microsoft.directory/agentIdentities/authentication/update | エージェント ID の認証を更新する |
| microsoft.directory/agentIdentities/basic/update | エージェント ID の基本プロパティを更新する |
| microsoft.directory/agentIdentities/create | エージェント ID の作成 |
| microsoft.directory/agentIdentities/delete | エージェント ID の削除 |
| microsoft.directory/agentIdentities/disable | エージェント ID を無効にする |
| microsoft.directory/agentIdentities/enable | エージェント ID を有効にする |
| microsoft.directory/agentIdentities/owners/update | エージェント ID の所有者を更新する |
| microsoft.directory/agentIdentities/tag/update | エージェント ID のタグを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/appRoleAssignedTo/update | エージェント ID ブループリント プリンシパルロールの割り当てを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/authentication/update | エージェント ID ブループリント プリンシパルの認証を更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/basic/update | エージェント ID ブループリント プリンシパルの基本プロパティを更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/create | エージェント ID ブループリント プリンシパルを作成する |
| microsoft.directory/agentIdentityBlueprintPrincipals/delete | エージェント ID ブループリント プリンシパルを削除する |
| microsoft.directory/agentIdentityBlueprintPrincipals/disable | エージェント ID ブループリント プリンシパルを無効にする |
| microsoft.directory/agentIdentityBlueprintPrincipals/enable | エージェント ID ブループリント プリンシパルを有効にする |
| microsoft.directory/agentIdentityBlueprintPrincipals/owners/update | エージェント ID ブループリント プリンシパルの所有者を更新する |
| microsoft.directory/agentIdentityBlueprintPrincipals/tag/update | エージェント ID ブループリント プリンシパルのタグを更新する |
| microsoft.directory/agentIdentityBlueprints/allProperties/update | エージェント ID ブループリントのすべてのプロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentIdentityBlueprints/appRoles/update | エージェント ID ブループリントで appRoles を更新する |
| microsoft.directory/agentIdentityBlueprints/audience/update | エージェント ID ブループリントの対象ユーザーを更新する |
| microsoft.directory/agentIdentityBlueprints/authentication/update | エージェント ID ブループリントの認証を更新する |
| microsoft.directory/agentIdentityBlueprints/basic/update | エージェント ID ブループリントの基本プロパティを更新する |
| microsoft.directory/agentIdentityBlueprints/create | エージェント ID ブループリントを作成する |
| microsoft.directory/agentIdentityBlueprints/credentials/update | エージェント ID ブループリントの資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/agentIdentityBlueprints/delete | エージェント ID ブループリントを削除する |
| microsoft.directory/agentIdentityBlueprints/owners/update | エージェント ID ブループリントの所有者を更新する |
| microsoft.directory/agentIdentityBlueprints/permissions/update | エージェント ID ブループリントで公開されているアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/agentIdentityBlueprints/tag/update | エージェント ID ブループリントのタグを更新する |
| microsoft.directory/appConsent/appConsentRequests/allProperties/read | Microsoft Entra ID に登録されているアプリケーションに対する同意要求のすべてのプロパティを読み取る |
| microsoft.directory/applications/allProperties/allTasks | アプリケーションの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/applications/disablement/update | ユーザーがサインインできるようにアプリケーションが有効になっているかどうかを更新する |
| microsoft.directory/applications/synchronization/standard/read | アプリケーション オブジェクトに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/applicationTemplates/instantiate | アプリケーション テンプレートからギャラリー アプリケーションのインスタンスを作成する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/authorizationPolicy/allProperties/allTasks | 認可ポリシーのすべての側面を管理する[Image: 特権ラベル アイコン。] |
| microsoft.directory/backup/preview/cancel | Microsoft Entra バックアップ操作を取り消して、バックアップ スナップショットを現在の状態と比較します。 |
| microsoft.directory/backup/preview/create | ユーザーがバックアップ スナップショットを現在の状態と比較できるようにする Microsoft Entra バックアップ操作を作成します。 |
| microsoft.directory/backup/recovery/cancel | Microsoft Entra 回復操作を取り消してバックアップ スナップショットの内容を回復する |
| microsoft.directory/backup/recovery/create | ユーザーがバックアップ スナップショットの内容を回復できるようにする Microsoft Entra 回復操作を作成します。 |
| microsoft.directory/backup/standard/read | Microsoft Entra バックアップ (バックアップ ID やタイムスタンプなど) を一覧表示し、差分レポートを表示し、回復ジョブとそれに関連するプロパティを一覧表示します。 |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/bulkJobs/basic/update | ディレクトリ内のすべての一括ジョブを更新する |
| microsoft.directory/bulkJobs/create | ディレクトリ内のすべての一括ジョブを作成する |
| microsoft.directory/cloudAppSecurity/allProperties/allTasks | Microsoft Defender for Cloud Apps ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.directory/conditionalAccessPolicies/allProperties/allTasks | 条件付きアクセス ポリシーのすべてのプロパティを管理する |
| microsoft.directory/connectorGroups/allProperties/read | アプリケーション プロキシ コネクタ グループのすべてのプロパティの読み取り |
| microsoft.directory/connectorGroups/allProperties/update | アプリケーション プロキシ コネクタ グループのすべてのプロパティを更新する |
| microsoft.directory/connectorGroups/create | アプリケーション プロキシ コネクタ グループを作成する |
| microsoft.directory/connectorGroups/delete | アプリケーション プロキシ コネクタ グループを削除する |
| microsoft.directory/connectors/allProperties/read | アプリケーション プロキシ コネクタのすべてのプロパティの読み取り |
| microsoft.directory/connectors/create | アプリケーション プロキシ コネクタを作成する |
| microsoft.directory/contacts/allProperties/allTasks | 連絡先の作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/contracts/allProperties/allTasks | パートナー コントラクトの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/crossTenantAccessPolicy/allowedCloudEndpoints/update | テナント間アクセスポリシーの許可されたクラウドエンドポイントを更新する |
| microsoft.directory/crossTenantAccessPolicy/basic/update | テナント間アクセスポリシーの基本設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/b2bCollaboration/update | 既定のテナント間アクセスポリシーの Microsoft Entra B2B コラボレーション設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/b2bDirectConnect/update | 既定のテナント間アクセスポリシーの Microsoft Entra B2B 直接接続設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/crossCloudMeetings/update | 既定のクロステナントアクセスポリシーのクロスクラウドTeams会議設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read | 既定のテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/default/tenantRestrictions/update | 既定のテナント間アクセスポリシーのテナント制限を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bCollaboration/update | パートナー向けテナント間アクセス ポリシーの Microsoft Entra B2B コラボレーション設定を更新します |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bDirectConnect/update | パートナー向けテナント間アクセス ポリシーの Microsoft Entra B2B 直接接続設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/create | パートナーのテナント間アクセスポリシーを作成する |
| microsoft.directory/crossTenantAccessPolicy/partners/crossCloudMeetings/update | パートナーのテナント間アクセスポリシーのクロスクラウドTeams会議設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/delete | パートナーのテナント間アクセスポリシーを削除する |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/basic/update | テナント間同期ポリシーの基本設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/create | パートナーのテナント間同期ポリシーを作成する |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/standard/read | テナント間同期ポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read | パートナーのテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/basic/update | マルチテナント組織のテナント間同期ポリシー テンプレートを更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/resetToDefaultSettings | マルチテナント組織のテナント間同期ポリシー テンプレートを既定の設定にリセットする |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/standard/read | マルチテナント組織のテナント間同期ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/basic/update | マルチテナント組織のテナント間アクセス ポリシー テンプレートを更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/resetToDefaultSettings | マルチテナント組織のテナント間アクセス ポリシー テンプレートを既定の設定にリセットする |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/standard/read | マルチテナント組織のテナント間アクセス ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/tenantRestrictions/update | パートナーのテナント間アクセスポリシーのテナント制限を更新する |
| microsoft.directory/crossTenantAccessPolicy/standard/read | テナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/customAuthenticationExtensions/allProperties/allTasks | カスタム認証拡張機能の作成と管理する[Image: 特権ラベル アイコン。] |
| microsoft.directory/deletedItems/delete | 復元できなくなったオブジェクトを完全に削除する |
| microsoft.directory/deletedItems/restore | 論理的に削除されたオブジェクトを元の状態に復元する |
| microsoft.directory/deviceLocalCredentials/password/read | Microsoft Entra 参加済みデバイスのバックアップされたローカル管理者アカウント資格情報 (パスワードを含む) のすべてのプロパティを読み取る |
| microsoft.directory/deviceManagementPolicies/basic/update | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する基本的なプロパティを更新します[Image: 特権ラベル アイコン。] |
| microsoft.directory/deviceManagementPolicies/standard/read | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する標準のプロパティを読み取る |
| microsoft.directory/deviceRegistrationPolicy/basic/update | デバイス登録ポリシーの基本プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/deviceRegistrationPolicy/standard/read | デバイス登録ポリシーの標準プロパティを読み取る |
| microsoft.directory/devices/allProperties/allTasks | デバイスの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/devices/permissions/update | IoT デバイスの代替名プロパティを更新する |
| microsoft.directory/deviceTemplates/owners/read | モノのインターネット (IoT) デバイス テンプレートの所有者を読み取る |
| microsoft.directory/deviceTemplates/owners/update | モノのインターネット (IoT) デバイス テンプレートの所有者を更新する |
| microsoft.directory/directoryRoles/allProperties/allTasks | ディレクトリ ロールの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/directoryRoleTemplates/allProperties/allTasks | Microsoft Entra ロール テンプレートの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/domains/allProperties/allTasks | ドメインの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/domains/federationConfiguration/basic/update | ドメインの基本的なフェデレーション構成を更新する |
| microsoft.directory/domains/federationConfiguration/create | ドメインのフェデレーション構成を作成する |
| microsoft.directory/domains/federationConfiguration/delete | ドメインのフェデレーション構成を削除する |
| microsoft.directory/domains/federationConfiguration/standard/read | ドメインのフェデレーション構成の標準プロパティを読み取る |
| microsoft.directory/entitlementManagement/allProperties/allTasks | Microsoft Entra エンタイトルメント管理でのリソースの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/externalUserProfiles/basic/update | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの基本プロパティを更新する |
| microsoft.directory/externalUserProfiles/delete | Teams の拡張ディレクトリ内の外部ユーザー プロファイルを削除する |
| microsoft.directory/externalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/groups/allProperties/allTasks | グループの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/groupsAssignableToRoles/allProperties/update | ロールを割り当て可能なグループを更新する |
| microsoft.directory/groupsAssignableToRoles/assignLicense | ロール割り当て可能なグループにライセンスを割り当てる |
| microsoft.directory/groupsAssignableToRoles/create | ロールを割り当て可能なグループを作成する |
| microsoft.directory/groupsAssignableToRoles/delete | ロールを割り当て可能なグループを削除する |
| microsoft.directory/groupsAssignableToRoles/reprocessLicenseAssignment | ロール割り当て可能なグループへのライセンス割り当てを再処理する |
| microsoft.directory/groupsAssignableToRoles/restore | ロールを割り当て可能なグループを復元する |
| microsoft.directory/groupSettings/allProperties/allTasks | グループ設定の作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/groupSettingTemplates/allProperties/allTasks | グループ設定テンプレートの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/hybridAuthenticationPolicy/allProperties/allTasks | Microsoft Entra ID でハイブリッド認証ポリシーを管理する[Image: 特権ラベル アイコン。] |
| microsoft.directory/identityProtection/allProperties/allTasks | Microsoft Entra ID 保護 でのすべてのリソースの作成と削除、および標準プロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/lifecycleWorkflows/workflows/allProperties/allTasks | ライフサイクル ワークフローとタスクのすべての側面を Microsoft Entra ID で管理する |
| microsoft.directory/loginOrganizationBranding/allProperties/allTasks | loginTenantBranding の作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/multiTenantOrganization/basic/update | マルチテナント組織の基本プロパティを更新する |
| microsoft.directory/multiTenantOrganization/create | マルチテナント組織を作成する |
| microsoft.directory/multiTenantOrganization/joinRequest/organizationDetails/update | マルチテナント組織に参加する |
| microsoft.directory/multiTenantOrganization/joinRequest/standard/read | マルチテナント組織の参加要求のプロパティを読み取る |
| microsoft.directory/multiTenantOrganization/standard/read | マルチテナント組織の基本プロパティを読み取る |
| microsoft.directory/multiTenantOrganization/tenants/create | マルチテナント組織でテナントを作成する |
| microsoft.directory/multiTenantOrganization/tenants/delete | マルチテナント組織に参加しているテナントを削除する |
| microsoft.directory/multiTenantOrganization/tenants/organizationDetails/read | マルチテナント組織に参加しているテナントの組織の詳細を読み取る |
| microsoft.directory/multiTenantOrganization/tenants/organizationDetails/update | マルチテナント組織に参加しているテナントの基本プロパティを更新する |
| microsoft.directory/multiTenantOrganization/tenants/standard/read | マルチテナント組織に参加しているテナントの基本プロパティを読み取る |
| microsoft.directory/namedLocations/basic/update | ネットワークの場所を定義するカスタム ルールの基本プロパティを更新する |
| microsoft.directory/namedLocations/create | ネットワークの場所を定義するカスタム ルールを作成する |
| microsoft.directory/namedLocations/delete | ネットワークの場所を定義するカスタム ルールを削除する |
| microsoft.directory/namedLocations/standard/read | ネットワークの場所を定義するカスタム ルールの基本プロパティを読み取る |
| microsoft.directory/oAuth2PermissionGrants/allProperties/allTasks | OAuth 2.0 アクセス許可の付与の作成と削除、およびすべてのプロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/onPremisesSynchronization/basic/update | オンプレミスのディレクトリ同期に関する基本的な情報を更新する |
| microsoft.directory/onPremisesSynchronization/standard/read | 標準のオンプレミス ディレクトリ同期情報を読み取る |
| microsoft.directory/organization/allProperties/allTasks | 組織のすべてのプロパティの読み取りと更新を行う |
| microsoft.directory/passwordHashSync/allProperties/allTasks | Microsoft Entra ID でパスワード ハッシュ同期 (PHS) のすべての側面の管理する |
| microsoft.directory/pendingExternalUserProfiles/basic/update | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの基本プロパティを更新する |
| microsoft.directory/pendingExternalUserProfiles/create | Teams の拡張ディレクトリに外部ユーザー プロファイルを作成する |
| microsoft.directory/pendingExternalUserProfiles/delete | Teams の拡張ディレクトリ内の外部ユーザー プロファイルを削除する |
| microsoft.directory/pendingExternalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/permissionGrantPolicies/basic/update | アクセス許可付与ポリシーの基本プロパティを更新する |
| microsoft.directory/permissionGrantPolicies/create | アクセス許可付与ポリシーを作成する |
| microsoft.directory/permissionGrantPolicies/delete | アクセス許可付与ポリシーを削除する |
| microsoft.directory/permissionGrantPolicies/standard/read | アクセス許可付与ポリシーの標準プロパティを読み取る |
| microsoft.directory/policies/allProperties/allTasks | ポリシーの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/privilegedIdentityManagement/allProperties/read | Privileged Identity Management のすべてのリソースを読み取る |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/resourceNamespaces/resourceActions/authenticationContext/update | Microsoft 365 ロールベースのアクセス制御 (RBAC) リソース アクションの条件付きアクセス認証コンテキストを更新します[Image: 特権ラベル アイコン。] |
| microsoft.directory/roleAssignments/allProperties/allTasks | ロールの割り当ての作成と削除、およびすべてのロールの割り当てプロパティの読み取りと更新 |
| microsoft.directory/roleDefinitions/allProperties/allTasks | ロールの定義の作成と削除、およびすべてのプロパティの読み取りと更新 |
| microsoft.directory/scopedRoleMemberships/allProperties/allTasks | scopedRoleMemberships の作成と削除、およびすべてのプロパティの読み取りと更新 |
| microsoft.directory/serviceAction/activateService | サービスに対して "サービスのアクティブ化" アクションを実行できる |
| microsoft.directory/serviceAction/disableDirectoryFeature | "ディレクトリ機能を無効にする" サービス アクションを実行できる |
| microsoft.directory/serviceAction/enableDirectoryFeature | "ディレクトリ機能を有効にする" サービス アクションを実行できる |
| microsoft.directory/serviceAction/getAvailableExtentionProperties | getAvailableExtentionProperties サービス アクションを実行できる |
| microsoft.directory/servicePrincipalCreationPolicies/basic/update | サービス プリンシパル作成ポリシーの基本プロパティを読み取る |
| microsoft.directory/servicePrincipalCreationPolicies/create | サービス プリンシパル作成ポリシーを作成する |
| microsoft.directory/servicePrincipalCreationPolicies/delete | サービス プリンシパル作成ポリシーを削除する |
| microsoft.directory/servicePrincipalCreationPolicies/standard/read | サービス プリンシパル作成ポリシーの標準プロパティを読み取る |
| microsoft.directory/servicePrincipals/allProperties/allTasks | サービス プリンシパルの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/managePermissionGrantsForAll.microsoft-company-admin | 任意のアプリケーションに対するすべてのアクセス許可に同意を付与する |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/credentials/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングのシークレットと資格情報を管理します。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/jobs/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングの同期ジョブを開始、再起動、一時停止します。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/schema/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/credentials/manage | アプリケーション プロビジョニングのシークレットと資格情報の管理。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/jobs/manage | アプリケーション プロビジョニングの同期ジョブの開始、再開、および一時停止。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/schema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/subscribedSkus/allProperties/allTasks | サブスクリプションの購入と管理、サブスクリプションの削除を行う |
| microsoft.directory/tenantManagement/tenants/create | Microsoft Entra ID で新しいテナントを作成する |
| microsoft.directory/users/allProperties/allTasks | ユーザーの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/basic/update | ユーザーの認証方法の基本プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/create | ユーザーの認証方法を更新します[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/delete | ユーザーの認証方法を削除する[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/standard/read | ユーザーの認証方法の標準プロパティを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/convertExternalToInternalMemberUser | 外部ユーザーを内部ユーザーに変換する |
| microsoft.directory/verifiableCredentials/configuration/allProperties/read | 検証可能な資格情報を作成および管理するために必要な構成を読み取る |
| microsoft.directory/verifiableCredentials/configuration/allProperties/update | 検証可能な資格情報を作成および管理するために必要な構成を更新する |
| microsoft.directory/verifiableCredentials/configuration/contracts/allProperties/read | 検証可能な資格情報コントラクトを読み取る |
| microsoft.directory/verifiableCredentials/configuration/contracts/allProperties/update | 検証可能な資格情報コントラクトを更新する |
| microsoft.directory/verifiableCredentials/configuration/contracts/cards/allProperties/read | 検証可能な資格情報カードを読み取る |
| microsoft.directory/verifiableCredentials/configuration/contracts/cards/revoke | 検証可能な資格情報カードを取り消す |
| microsoft.directory/verifiableCredentials/configuration/contracts/create | 検証可能な資格情報コントラクトを作成する |
| microsoft.directory/verifiableCredentials/configuration/create | 検証可能な資格情報を作成および管理するために必要な構成を作成する |
| microsoft.directory/verifiableCredentials/configuration/delete | 検証可能な資格情報を作成および管理するために必要な構成を削除し、その検証可能な資格情報をすべて削除する |
| microsoft.dynamics365/allEntities/allTasks | Dynamics 365 のすべての側面を管理する |
| microsoft.edge/allEntities/allProperties/allTasks | Microsoft Edge のすべての側面を管理する |
| microsoft.flow/allEntities/allTasks | Power Automate のすべての側面を管理する |
| microsoft.graph.dataConnect/allEntities/allProperties/allTasks | Microsoft Graph データ接続の側面を管理する |
| microsoft.hardware.support/shippingAddress/allProperties/allTasks | 他のユーザーが作成した配送先住所を含む、Microsoft ハードウェア保証クレームの配送先住所の作成、読み取り、更新、削除を行います |
| microsoft.hardware.support/shippingStatus/allProperties/read | オープンな Microsoft ハードウェア保証クレームの出荷ステータスを読み取ります |
| microsoft.hardware.support/warrantyClaims/allProperties/allTasks | Microsoft ハードウェア保証クレームのすべての側面を作成および管理します |
| microsoft.healthPlatform/allEntities/allProperties/allTasks | Microsoft Dragon 管理センターのすべての側面を管理する |
| microsoft.insights/allEntities/allProperties/allTasks | Insights アプリのすべての側面を管理する |
| microsoft.intune/allEntities/allTasks | Microsoft Intune のすべての側面を管理する |
| microsoft.microsoft365.organizationalData/allEntities/allProperties/allTasks | Microsoft 365 で組織データのすべての側面を管理する |
| microsoft.networkAccess/allEntities/allProperties/allTasks | Microsoft Entra ネットワーク アクセスのすべての側面を管理する |
| microsoft.networkAccess/trafficLogs/standard/read | DeviceId、DestinationIp、PolicyRuleId などのトラフィック ログの標準プロパティの読み取り |
| microsoft.office365.complianceManager/allEntities/allTasks | Office 365 コンプライアンス マネージャーの全側面の管理 |
| microsoft.office365.copilot/allEntities/allProperties/allTasks | Microsoft Copilotのすべての設定を作成および管理する |
| microsoft.office365.desktopAnalytics/allEntities/allTasks | Desktop Analytics のすべての側面を管理する |
| microsoft.office365.exchange/allEntities/basic/allTasks | Exchange Online のすべての側面を管理する |
| microsoft.office365.fileStorageContainers/allEntities/allProperties/allTasks | SharePoint Embedded コンテナーのすべての側面を管理します |
| microsoft.office365.knowledge/contentUnderstanding/allProperties/allTasks | Microsoft 365 管理センターのコンテンツの解釈のすべてのプロパティを読み取り、更新する |
| microsoft.office365.knowledge/contentUnderstanding/analytics/allProperties/read | Microsoft 365 管理センターでコンテンツの解釈の分析レポートを読み取る |
| microsoft.office365.knowledge/knowledgeNetwork/allProperties/allTasks | Microsoft 365 管理センターの知識ネットワークのすべてのプロパティを読み取り、更新する |
| microsoft.office365.knowledge/knowledgeNetwork/topicVisibility/allProperties/allTasks | Microsoft 365 管理センターで知識ネットワークのトピックの可視性を管理する |
| microsoft.office365.knowledge/learningSources/allProperties/allTasks | Learning アプリで学習ソースとそのすべてのプロパティを管理する |
| microsoft.office365.lockbox/allEntities/allTasks | カスタマー ロックボックスのすべての側面を管理する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.messageCenter/securityMessages/read | Microsoft 365 管理センターのメッセージ センターでセキュリティ メッセージを読み取る |
| microsoft.office365.migrations/allEntities/allProperties/allTasks | Microsoft 365 移行のすべての側面を管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.organizationalMessages/allEntities/allProperties/allTasks | Microsoft 365 組織メッセージのすべての作成側面を管理する |
| microsoft.office365.protectionCenter/allEntities/allProperties/allTasks | セキュリティおよびコンプライアンス センターのすべての側面を管理する |
| microsoft.office365.search/content/manage | Microsoft Search でコンテンツの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.office365.securityComplianceCenter/allEntities/allTasks | すべてのリソースの作成と削除、および Microsoft 365 セキュリティ/コンプライアンス センターでの標準プロパティの読み取りと更新 |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.sharePoint/allEntities/allTasks | SharePoint ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.office365.sharePointAdvancedManagement/allEntities/allProperties/allTasks | SharePoint 高度な管理のすべての側面を管理します |
| microsoft.office365.skypeForBusiness/allEntities/allTasks | Skype for Business Online の全側面の管理 |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.userCommunication/allEntities/allTasks | 新機能のメッセージを表示できるかどうかを読み取り、更新する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.office365.yammer/allEntities/allProperties/allTasks | Yammer の全側面の管理 |
| microsoft.people/users/photo/read | ユーザーのプロフィール写真を読み取る |
| microsoft.people/users/photo/update | ユーザーのプロファイル写真を更新する |
| microsoft.peopleAdmin/organization/allProperties/read | 代名詞、名前の発音、プロファイル カードの設定など、ユーザーのユーザー設定を読み取ります |
| microsoft.peopleAdmin/organization/allProperties/update | 代名詞、名前の発音、プロファイル カードの設定など、ユーザーのユーザー設定を更新する |
| microsoft.permissionsManagement/allEntities/allProperties/allTasks | Microsoft Entra Permissions Management のすべての側面を管理する |
| microsoft.powerApps.powerBI/allEntities/allTasks | Fabric と Power BI のすべての側面を管理します |
| microsoft.powerApps/allEntities/allTasks | Power Apps のすべての側面を管理する |
| microsoft.teams/allEntities/allProperties/allTasks | Teams のすべてのリソースを管理する |
| microsoft.virtualVisits/allEntities/allProperties/allTasks | 管理センターまたは Virtual Visits アプリから Virtual Visits の情報とメトリックを管理および共有する |
| microsoft.viva.glint/allEntities/allProperties/allTasks | Microsoft 365 管理センターですべての Microsoft Viva Glint 設定を管理および構成する |
| microsoft.viva.goals/allEntities/allProperties/allTasks | Microsoft Viva Goals のすべての側面を管理する |
| microsoft.viva.pulse/allEntities/allProperties/allTasks | Microsoft Viva Pulse のすべての側面を管理します |
| microsoft.windows.defenderAdvancedThreatProtection/allEntities/allTasks | エンドポイントに対して Microsoft Defender のすべての側面を管理する |
| microsoft.windows.updatesDeployments/allEntities/allProperties/allTasks | Windows Update Service のすべての側面の読み取りと構成を行う |

### グローバル閲覧者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、Microsoft 365 の各サービスにわたって設定と管理情報を読み取ることができますが、管理アクションを実行することはできません。 グローバル閲覧者は、全体管理者に対応する読み取り専用のロールです。 計画、監査、調査については、全体管理者ではなくグローバル閲覧者を割り当てます。 Exchange 管理者など、他の制限付き管理者ロールとグローバル閲覧者を組み合わせて使用すると、全体管理者ロールを割り当てずに作業を簡単に行うことができます。 グローバル閲覧者は、Microsoft 365 管理センター、Exchange 管理センター、SharePoint 管理センター、Teams 管理センター、Microsoft Defender ポータル、Microsoft Purview ポータル、Azure portal、デバイス管理管理センターと連携します。

このロールを持つユーザー **は、** 次の操作を実行できません。

- Microsoft 365 管理センターの [サービスを購入する] 領域にはアクセスできません。

Note

グローバル閲覧者ロールには、次の制限があります。

- OneDrive 管理センター - OneDrive 管理センターでは、グローバル閲覧者ロールはサポートされていません。
- [Microsoft Defender ポータル](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-365-defender-portal) - グローバル 閲覧者はコンテンツ検索を実行したり、セキュリティ スコアを表示したりできません。
- [Teams 管理センター](https://learn.microsoft.com/ja-jp/microsoftteams/manage-teams-in-modern-portal) - グローバル閲覧者は、**Teams ライフサイクル**、**分析およびレポート**、**IP 電話デバイス管理**、**アプリ カタログ**を閲覧できません。 詳細については、「[Teams の管理に Microsoft Teams 管理者ロールを使用する](https://learn.microsoft.com/ja-jp/microsoftteams/using-admin-roles)」を参照してください。
- [Privileged Access Management](https://learn.microsoft.com/ja-jp/purview/privileged-access-management) では、グローバル閲覧者ロールはサポートされていません。
- [Azure Information Protection](https://learn.microsoft.com/ja-jp/azure/information-protection/what-is-information-protection) - グローバル閲覧者は、[中央レポート](https://learn.microsoft.com/ja-jp/azure/information-protection/reports-aip)のみでサポートされ、Microsoft Entra 組織が[統合ラベル付けプラットフォーム](https://learn.microsoft.com/ja-jp/azure/information-protection/faqs#how-can-i-determine-if-my-tenant-is-on-the-unified-labeling-platform)にない場合にサポートされます。
- [SharePoint](https://learn.microsoft.com/ja-jp/sharepoint/get-started-new-admin-center) - グローバル 閲覧者は、SharePoint Online PowerShell コマンドレットと読み取り API への読み取りアクセス権を持っています。
- [Power Platform 管理センター](https://learn.microsoft.com/ja-jp/power-platform/admin/admin-documentation) - グローバル閲覧者は、Power Platform 管理センターではまだサポートされていません。
- Microsoft Purview では、グローバル閲覧者ロールはサポートされていません。

| Actions | Description |
| --- | --- |
| microsoft.agentRegistry/allEntities/allProperties/read | Microsoft Entra IDのエージェントレジストリのすべてのプロパティを読む |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.backup/allEntities/allProperties/read | Microsoft 365 バックアップ のすべての側面を読み取る |
| microsoft.cloudPC/allEntities/allProperties/read | Windows 365 のすべての側面を読み取る |
| microsoft.commerce.billing/allEntities/allProperties/read | Office 365 課金情報のすべてのリソースを読み取る |
| microsoft.commerce.billing/purchases/standard/read | Microsoft 365 管理センターで購入サービスを読み取る。 |
| microsoft.directory/accessReviews/allProperties/read | アクセス レビューのすべてのプロパティの読み取り |
| microsoft.directory/accessReviews/definitions/allProperties/read | Microsoft Entra ID ですべてのレビュー可能なリソースのアクセス レビューのすべてのプロパティを読み取る |
| microsoft.directory/adminConsentRequestPolicy/allProperties/read | Microsoft Entra ID で管理者の同意要求ポリシーのすべてのプロパティを読み取る |
| microsoft.directory/administrativeUnits/allProperties/read | メンバーを含めた、管理単位のすべてのプロパティを読み取る |
| microsoft.directory/appConsent/appConsentRequests/allProperties/read | Microsoft Entra ID に登録されているアプリケーションに対する同意要求のすべてのプロパティを読み取る |
| microsoft.directory/applications/allProperties/read | すべての種類のアプリケーションのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/applications/synchronization/standard/read | アプリケーション オブジェクトに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/cloudAppSecurity/allProperties/read | Cloud App Security のすべてのプロパティの読み取り |
| microsoft.directory/conditionalAccessPolicies/allProperties/read | 条件付きアクセス ポリシーのすべてのプロパティを読み取る |
| microsoft.directory/connectorGroups/allProperties/read | アプリケーション プロキシ コネクタ グループのすべてのプロパティの読み取り |
| microsoft.directory/connectors/allProperties/read | アプリケーション プロキシ コネクタのすべてのプロパティの読み取り |
| microsoft.directory/contacts/allProperties/read | 連絡先のすべてのプロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read | 既定のテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/standard/read | テナント間同期ポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read | パートナーのテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/standard/read | マルチテナント組織のテナント間同期ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/standard/read | マルチテナント組織のテナント間アクセス ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/standard/read | テナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/customAuthenticationExtensions/allProperties/read | カスタム認証拡張機能を読み取る |
| microsoft.directory/deviceLocalCredentials/standard/read | Microsoft Entra 参加済みデバイスのバックアップされたローカル管理者アカウント資格情報のすべてのプロパティを読み取る (パスワードを除く) |
| microsoft.directory/deviceManagementPolicies/standard/read | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する標準のプロパティを読み取る |
| microsoft.directory/deviceRegistrationPolicy/standard/read | デバイス登録ポリシーの標準プロパティを読み取る |
| microsoft.directory/devices/allProperties/read | デバイスのすべてのプロパティを読み取る |
| microsoft.directory/directoryRoles/allProperties/read | ディレクトリ ロールのすべてのプロパティを読み取る |
| microsoft.directory/directoryRoleTemplates/allProperties/read | ディレクトリ ロール テンプレートのすべてのプロパティを読み取る |
| microsoft.directory/domains/allProperties/read | ドメインのすべてのプロパティの読み取る |
| microsoft.directory/domains/federationConfiguration/standard/read | ドメインのフェデレーション構成の標準プロパティを読み取る |
| microsoft.directory/entitlementManagement/allProperties/read | Microsoft Entra エンタイトルメント管理ですべてのプロパティを読み取る |
| microsoft.directory/externalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/groups/allProperties/read | ロールを割り当て可能なグループを含む、セキュリティ グループと Microsoft 365 グループのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/groupSettings/allProperties/read | グループ設定のすべてのプロパティを読み取る |
| microsoft.directory/groupSettingTemplates/allProperties/read | グループ設定テンプレートのすべてのプロパティを読み取る |
| microsoft.directory/identityProtection/allProperties/read | Microsoft Entra ID 保護 のすべてのリソースを読み取る |
| microsoft.directory/lifecycleWorkflows/workflows/allProperties/read | ライフサイクル ワークフローとタスクのすべてのプロパティを Microsoft Entra ID で読み取る |
| microsoft.directory/loginOrganizationBranding/allProperties/read | 組織のブランド化されたサインイン ページのすべてのプロパティを読み取る |
| microsoft.directory/multiTenantOrganization/joinRequest/standard/read | マルチテナント組織の参加要求のプロパティを読み取る |
| microsoft.directory/multiTenantOrganization/standard/read | マルチテナント組織の基本プロパティを読み取る |
| microsoft.directory/multiTenantOrganization/tenants/organizationDetails/read | マルチテナント組織に参加しているテナントの組織の詳細を読み取る |
| microsoft.directory/multiTenantOrganization/tenants/standard/read | マルチテナント組織に参加しているテナントの基本プロパティを読み取る |
| microsoft.directory/namedLocations/standard/read | ネットワークの場所を定義するカスタム ルールの基本プロパティを読み取る |
| microsoft.directory/oAuth2PermissionGrants/allProperties/read | OAuth 2.0 アクセス許可付与のすべてのプロパティを読み取る |
| microsoft.directory/onPremisesSynchronization/standard/read | 標準のオンプレミス ディレクトリ同期情報を読み取る |
| microsoft.directory/organization/allProperties/read | 組織のすべてのプロパティを読み取る |
| microsoft.directory/pendingExternalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/permissionGrantPolicies/standard/read | アクセス許可付与ポリシーの標準プロパティを読み取る |
| microsoft.directory/policies/allProperties/read | ポリシーのすべてのプロパティを読み取る |
| microsoft.directory/privilegedIdentityManagement/allProperties/read | Privileged Identity Management のすべてのリソースを読み取る |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/roleAssignments/allProperties/read | ロールの割り当てのすべてのプロパティを読み取る |
| microsoft.directory/roleDefinitions/allProperties/read | ロールの定義のすべてのプロパティを読み取る |
| microsoft.directory/scopedRoleMemberships/allProperties/read | 管理単位のメンバーを表示する |
| microsoft.directory/serviceAction/getAvailableExtentionProperties | getAvailableExtentionProperties サービス アクションを実行できる |
| microsoft.directory/servicePrincipalCreationPolicies/standard/read | サービス プリンシパル作成ポリシーの標準プロパティを読み取る |
| microsoft.directory/servicePrincipals/allProperties/read | servicePrincipals のすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/subscribedSkus/allProperties/read | 製品サブスクリプションのすべてのプロパティを読み取る |
| microsoft.directory/users/allProperties/read | ユーザーのすべてのプロパティを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/standard/restrictedRead | ユーザーの個人を特定できる情報を含まない認証方法の標準プロパティを読み取る |
| microsoft.directory/verifiableCredentials/configuration/allProperties/read | 検証可能な資格情報を作成および管理するために必要な構成を読み取る |
| microsoft.directory/verifiableCredentials/configuration/contracts/allProperties/read | 検証可能な資格情報コントラクトを読み取る |
| microsoft.directory/verifiableCredentials/configuration/contracts/cards/allProperties/read | 検証可能な資格情報カードを読み取る |
| microsoft.edge/allEntities/allProperties/read | Microsoft Edge のすべての側面を読み取る |
| microsoft.graph.dataConnect/allEntities/allProperties/read | Microsoft Graph データ接続の側面を読み取る |
| microsoft.hardware.support/shippingAddress/allProperties/read | 他のユーザーが作成した既存の配送先住所を含む、Microsoft ハードウェア保証クレームの配送先住所を読み取ります |
| microsoft.hardware.support/shippingStatus/allProperties/read | オープンな Microsoft ハードウェア保証クレームの出荷ステータスを読み取ります |
| microsoft.hardware.support/warrantyClaims/allProperties/read | Microsoft ハードウェア保証クレームを読み取ります |
| microsoft.healthPlatform/allEntities/allProperties/read | Microsoft Dragon 管理センターのすべての側面を読む |
| microsoft.insights/allEntities/allProperties/read | Viva Insights の全側面を読み取る |
| microsoft.microsoft365.organizationalData/allEntities/allProperties/read | Microsoft 365 で組織データのすべての側面を読み取る |
| microsoft.networkAccess/allEntities/allProperties/read | Microsoft Entra ネットワーク アクセスのすべての側面を読み取る |
| microsoft.office365.copilot/allEntities/allProperties/read | Microsoft Copilotのすべての設定を読み取る |
| microsoft.office365.fileStorageContainers/allEntities/allProperties/read | SharePoint Embedded コンテナーのエンティティとアクセス許可を読み取ります |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.messageCenter/securityMessages/read | Microsoft 365 管理センターのメッセージ センターでセキュリティ メッセージを読み取る |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.organizationalMessages/allEntities/allProperties/read | Microsoft 365 組織メッセージのすべての側面を読み取る |
| microsoft.office365.protectionCenter/allEntities/allProperties/read | セキュリティおよびコンプライアンス センターのすべてのプロパティを読み取る |
| microsoft.office365.securityComplianceCenter/allEntities/read | Microsoft 365 セキュリティおよびコンプライアンス センターで標準プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.office365.yammer/allEntities/allProperties/read | Yammer のすべてのアスペクトを読み取る |
| microsoft.permissionsManagement/allEntities/allProperties/read | Microsoft Entra Permissions Management のすべての側面を読み取る |
| microsoft.teams/allEntities/allProperties/read | Microsoft Teams のすべてのプロパティを読み取る |
| microsoft.virtualVisits/allEntities/allProperties/read | 仮想アクセスのすべての側面を読み取る |
| microsoft.viva.glint/allEntities/allProperties/read | Microsoft 365 管理センターですべての Microsoft Viva Glint 設定を読み取る |
| microsoft.viva.goals/allEntities/allProperties/read | Microsoft Viva Goals のあらゆる側面を読み取る |
| microsoft.viva.pulse/allEntities/allProperties/read | Microsoft Viva Pulse のすべての側面を読み取る |
| microsoft.windows.updatesDeployments/allEntities/allProperties/read | Windows Update Service のすべての側面を読み取る |

### Global Secure Access 管理者

次のことを行う必要があるユーザーに、Global Secure Access 管理者ロールを割り当てます。

- Microsoft Entra インターネット アクセスと Microsoft Entra プライベート アクセスのすべての側面を作成して管理する
- パブリックおよびプライベート エンドポイントへのアクセスを管理する

このロールを持つユーザー **は、** 次の操作を実行できません。

- エンタープライズ アプリケーション、アプリケーションの登録、条件付きアクセス、またはアプリケーション プロキシ設定を管理することはできない

[詳細情報](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-role-based-permissions)

| Actions | Description |
| --- | --- |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/applicationPolicies/standard/read | アプリケーション ポリシーの標準プロパティを読み取る |
| microsoft.directory/applications/applicationProxy/read | すべてのアプリケーション プロキシ プロパティを読み取る |
| microsoft.directory/applications/owners/read | アプリケーションの所有者を読み取る |
| microsoft.directory/applications/policies/read | アプリケーションのポリシーを読み取る |
| microsoft.directory/applications/standard/read | アプリケーションの標準プロパティを読み取る |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/conditionalAccessPolicies/standard/read | ポリシーの条件付きアクセスを読み取る |
| microsoft.directory/connectorGroups/allProperties/read | アプリケーション プロキシ コネクタ グループのすべてのプロパティの読み取り |
| microsoft.directory/connectors/allProperties/read | アプリケーション プロキシ コネクタのすべてのプロパティの読み取り |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read | 既定のテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read | パートナーのテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/standard/read | テナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/namedLocations/standard/read | ネットワークの場所を定義するカスタム ルールの基本プロパティを読み取る |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.networkAccess/allEntities/allProperties/allTasks | Microsoft Entra ネットワーク アクセスのすべての側面を管理する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### グローバルなセキュリティで保護されたアクセス ログ 閲覧者

次の操作を行う必要があるユーザーに、グローバルなセキュリティで保護されたアクセス ログ閲覧者ロールを割り当てます。

- 指定されたセキュリティ担当者による分析のために、Microsoft Entra Internet Access と Microsoft Entra Private Access のネットワーク トラフィック ログを読み取ります
- セッション、接続、トランザクションなどのログの詳細を表示する
- IP アドレスやドメインなどの条件に基づいてログをフィルター処理する

[詳細情報](https://learn.microsoft.com/ja-jp/entra/global-secure-access/reference-role-based-permissions)

| Actions | Description |
| --- | --- |
| microsoft.networkAccess/trafficLogs/standard/read | DeviceId、DestinationIp、PolicyRuleId などのトラフィック ログの標準プロパティの読み取り |

### グループ管理者

このロールのユーザーは、グループとその設定 (命名ポリシーや有効期限ポリシーなど) を作成/管理できます。 このロールにユーザーを割り当てることにより、Outlook だけでなく、Teams、SharePoint、Yammer などのさまざまなワークロードにわたって、組織内のすべてのグループを管理する機能がユーザーに付与されるということを理解しておくことが重要です。 また、そのユーザーは、Microsoft 管理センター、Azure portal などのさまざまな管理ポータル、および Teams 管理センターや SharePoint 管理センターなどのワークロード固有の管理ポータルでさまざまなグループ設定を管理できます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/bulkJobs.groups/basic/update | グループに関連する一括ジョブを更新する |
| microsoft.directory/bulkJobs.groups/create | グループに関連する一括ジョブを作成する |
| microsoft.directory/bulkJobs.groups/standard/read | グループに関連する一括ジョブの読み取り |
| microsoft.directory/deletedItems.groups/delete | 復元できなくなったグループを完全に削除する |
| microsoft.directory/deletedItems.groups/restore | 論理的に削除されたグループを元の状態に復元する |
| microsoft.directory/groups/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類のグループの割り当てられたラベル プロパティを更新する |
| microsoft.directory/groups/assignLicense | グループベースのライセンスのグループに製品ライセンスを割り当てる |
| microsoft.directory/groups/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups/classification/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの分類プロパティを更新する |
| microsoft.directory/groups/create | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを作成する |
| microsoft.directory/groups/delete | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを削除する |
| microsoft.directory/groups/dynamicMembershipRule/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの動的メンバーシップ ルールを更新する |
| microsoft.directory/groups/groupType/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのグループの種類に影響を与えるプロパティを更新する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups/onPremWriteBack/update | Microsoft Entra Connect を使用してオンプレミスに書き戻す Microsoft Entra グループを更新する |
| microsoft.directory/groups/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups/reprocessLicenseAssignment | グループベースのライセンスのライセンス割り当てを再処理する |
| microsoft.directory/groups/restore | ソフト削除されたコンテナーからグループを復元する |
| microsoft.directory/groups/settings/update | グループの設定を更新する |
| microsoft.directory/groups/visibility/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの可視性プロパティを更新する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ゲスト招待元

このロールが割り当てられたユーザーは、**[メンバーは招待ができる]** ユーザー設定が [いいえ] に設定されている場合に、Microsoft Entra B2B ゲスト ユーザーの招待を管理できます。 B2B コラボレーションの詳細については、「[Microsoft Entra B2B コラボレーションとは](https://learn.microsoft.com/ja-jp/entra/external-id/what-is-b2b)」をご覧ください。 その他の権限は含まれません。

| Actions | Description |
| --- | --- |
| microsoft.directory/users/appRoleAssignments/read | ユーザーのアプリケーション ロールの割り当てを読み取る |
| microsoft.directory/users/deviceForResourceAccount/read | ユーザーの deviceForResourceAccount を読み取る |
| microsoft.directory/users/directReports/read | ユーザーの直属の部下を読み取る |
| microsoft.directory/users/invitedBy/read | 外部ユーザーをテナントに招待したユーザーを読み取る |
| microsoft.directory/users/inviteGuest | ゲスト ユーザーを招待する |
| microsoft.directory/users/licenseDetails/read | ユーザーのライセンスの詳細を読み取る |
| microsoft.directory/users/manager/read | ユーザーのマネージャーを読み取る |
| microsoft.directory/users/memberOf/read | ユーザーのグループ メンバーシップを読み取る |
| microsoft.directory/users/oAuth2PermissionGrants/read | ユーザーの委任されたアクセス許可付与を読み取る |
| microsoft.directory/users/ownedDevices/read | ユーザーの所有デバイスを読み取る |
| microsoft.directory/users/ownedObjects/read | ユーザーの所有オブジェクトを読み取る |
| microsoft.directory/users/photo/read | ユーザーの写真を読み取る |
| microsoft.directory/users/registeredDevices/read | ユーザーの登録済みデバイスを読み取る |
| microsoft.directory/users/scopedRoleMemberOf/read | 管理単位にスコープが設定されている Microsoft Entra ロールのユーザーのメンバーシップを読み取る |
| microsoft.directory/users/sponsorOf/read | ユーザーがスポンサーになっているすべてのエージェントまたはアプリケーションを読み取ります。 |
| microsoft.directory/users/sponsors/read | ユーザーのスポンサーを読み取る |
| microsoft.directory/users/standard/read | ユーザーの基本プロパティを読み取る |

### ヘルプデスク管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールを持つユーザーは、パスワードの変更、更新トークンの無効化、Microsoft for Azure および Microsoft 365 サービスに対するサポート要求の作成と管理、サービス正常性の監視を行うことができます。 更新トークンを無効にすると、ユーザーは再度サインインすることを強制されます。 ヘルプデスク管理者がユーザーのパスワードをリセットして更新トークンを無効にできるかどうかは、ユーザーが割り当てられているロールに依存します。 ヘルプデスク管理者がパスワードをリセットして更新トークンを無効にできるロールの一覧については、「[パスワードをリセットできるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-reset-passwords)」を参照してください。

このロールを持つユーザー **は、** 次の操作を実行できません。

- [ロールを割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)のメンバーと所有者の資格情報を変更したり、MFA をリセットしたりすることはできません。

Important

このロールを持つユーザーは、機密情報や個人情報または Microsoft Entra ID の内外の重要な構成にアクセスできるユーザーのパスワードを変更できます。 ユーザーのパスワードを変更することは、そのユーザーの ID およびアクセス許可を取得できることを意味します。 例えば次が挙げられます。

- 所有しているアプリの資格情報を管理できる、アプリケーションの登録とエンタープライズ アプリケーションの所有者。 これらのアプリには、Microsoft Entra ID およびヘルプデスク管理者に付与されていない場所への特権アクセス許可がある場合があります。 ヘルプデスク管理者は、このパスからアプリケーション所有者の ID を取得し、さらにそのアプリケーションの資格情報を更新して特権アプリケーションの ID を取得できる場合があります。
- 機密情報や個人情報または Azure の重要な構成にアクセスする可能性がある Azure サブスクリプション所有者。
- グループ メンバーシップを管理できるセキュリティ グループと Microsoft 365 グループの所有者。 これらのグループは、機密情報や個人情報または Microsoft Entra ID や別の場所の重要な構成へのアクセス権を付与される場合があります。
- Exchange Online、Microsoft Defender ポータル、Microsoft Purview ポータル、人事システムなど、Microsoft Entra ID 以外の他のサービスの管理者。
- 機密情報や個人情報にアクセスできる場合がある役員、弁護士、人事担当者のような非管理者。

管理 [単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/administrative-units)では、ユーザーのサブセットに対する管理アクセス許可の委任と、ユーザーのサブセットへのポリシーの適用が可能です。

このロールは、以前 [は Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-overview) でパスワード管理者という名前でした。 Microsoft Graph API と Microsoft Graph PowerShell での既存の名前に合わせて、ヘルプデスク管理者という名前に変更されました。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/deviceLocalCredentials/standard/read | Microsoft Entra 参加済みデバイスのバックアップされたローカル管理者アカウント資格情報のすべてのプロパティを読み取る (パスワードを除く) |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ハイブリッド ID の管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、クラウド プロビジョニングを使用して Active Directory から Microsoft Entra ID にプロビジョニング構成のセットアップを作成、管理、デプロイできるだけでなく、Microsoft Entra Connect、パススルー認証 (PTA)、パスワード ハッシュ同期 (PHS)、シームレス シングル サインオン (シームレス SSO)、フェデレーション設定を管理できます。 Microsoft Entra Connect Health を管理するアクセス権がありません。 ユーザーはこのロールを使用して、ログのトラブルシューティングと監視を行うこともできます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/applications/appRoles/update | すべての種類のアプリケーションで appRoles プロパティを更新する |
| microsoft.directory/applications/audience/update | アプリケーションの対象ユーザー プロパティを更新する |
| microsoft.directory/applications/authentication/update | すべての種類のアプリケーションで認証を更新する |
| microsoft.directory/applications/basic/update | アプリケーションの基本プロパティを更新する |
| microsoft.directory/applications/create | すべての種類のアプリケーションを作成する |
| microsoft.directory/applications/delete | すべての種類のアプリケーションを削除する |
| microsoft.directory/applications/disablement/update | ユーザーがサインインできるようにアプリケーションが有効になっているかどうかを更新する |
| microsoft.directory/applications/notes/update | アプリケーションのメモを更新する |
| microsoft.directory/applications/owners/update | アプリケーションの所有者を更新する |
| microsoft.directory/applications/permissions/update | すべての種類のアプリケーションで、公開されたアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/applications/policies/update | アプリケーションのポリシーを更新する |
| microsoft.directory/applications/synchronization/standard/read | アプリケーション オブジェクトに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/applications/tag/update | アプリケーションのタグを更新する |
| microsoft.directory/applicationTemplates/instantiate | アプリケーション テンプレートからギャラリー アプリケーションのインスタンスを作成する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/cloudProvisioning/allProperties/allTasks | Microsoft Entra クラウド プロビジョニング サービスのすべてのプロパティを読み取りと構成。 |
| microsoft.directory/deletedItems.applications/delete | 復元できなくなったアプリケーションを完全に削除する |
| microsoft.directory/deletedItems.applications/restore | 論理的に削除されたアプリケーションを元の状態に復元する |
| microsoft.directory/domains/allProperties/read | ドメインのすべてのプロパティの読み取る |
| microsoft.directory/domains/federation/update | ドメインのフェデレーション プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/domains/federationConfiguration/basic/update | ドメインの基本的なフェデレーション構成を更新する |
| microsoft.directory/domains/federationConfiguration/create | ドメインのフェデレーション構成を作成する |
| microsoft.directory/domains/federationConfiguration/delete | ドメインのフェデレーション構成を削除する |
| microsoft.directory/domains/federationConfiguration/standard/read | ドメインのフェデレーション構成の標準プロパティを読み取る |
| microsoft.directory/hybridAuthenticationPolicy/allProperties/allTasks | Microsoft Entra ID でハイブリッド認証ポリシーを管理する[Image: 特権ラベル アイコン。] |
| microsoft.directory/onPremisesSynchronization/basic/update | オンプレミスのディレクトリ同期に関する基本的な情報を更新する |
| microsoft.directory/onPremisesSynchronization/standard/read | 標準のオンプレミス ディレクトリ同期情報を読み取る |
| microsoft.directory/organization/dirSync/update | 組織のディレクトリ同期プロパティを更新する |
| microsoft.directory/passwordHashSync/allProperties/allTasks | Microsoft Entra ID でパスワード ハッシュ同期 (PHS) のすべての側面の管理する |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/servicePrincipals/audience/update | サービス プリンシパルで対象ユーザー プロパティを更新する |
| microsoft.directory/servicePrincipals/authentication/update | サービス プリンシパルで認証プロパティを更新する |
| microsoft.directory/servicePrincipals/basic/update | サービス プリンシパルで基本プロパティを更新する |
| microsoft.directory/servicePrincipals/create | サービス プリンシパルを作成する |
| microsoft.directory/servicePrincipals/delete | サービス プリンシパルを削除する |
| microsoft.directory/servicePrincipals/disable | サービス プリンシパルを無効にする |
| microsoft.directory/servicePrincipals/enable | サービス プリンシパルを有効にする |
| microsoft.directory/servicePrincipals/notes/update | サービス プリンシパルのメモを更新する |
| microsoft.directory/servicePrincipals/owners/update | サービス プリンシパルの所有者を更新する |
| microsoft.directory/servicePrincipals/permissions/update | サービスプリンシパルのアクセス許可を更新する |
| microsoft.directory/servicePrincipals/policies/update | サービス プリンシパルのポリシーを更新する |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/credentials/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングのシークレットと資格情報を管理します。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/jobs/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングの同期ジョブを開始、再起動、一時停止します。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToCloudTenant/schema/manage | クラウド テナントからクラウド テナントへのアプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/credentials/manage | アプリケーション プロビジョニングのシークレットと資格情報の管理。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/jobs/manage | アプリケーション プロビジョニングの同期ジョブの開始、再開、および一時停止。 |
| microsoft.directory/servicePrincipals/synchronization.cloudTenantToExternalSystem/schema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成および管理する |
| microsoft.directory/servicePrincipals/synchronization/standard/read | サービス プリンシパルに関連付けられているプロビジョニング設定を読み取る |
| microsoft.directory/servicePrincipals/synchronizationCredentials/manage | アプリケーション プロビジョニングのシークレットと資格情報を管理する |
| microsoft.directory/servicePrincipals/synchronizationJobs/manage | アプリケーション プロビジョニングの同期ジョブを開始、再開、および一時停止する |
| microsoft.directory/servicePrincipals/synchronizationSchema/manage | アプリケーション プロビジョニングの同期ジョブとスキーマを作成、管理する |
| microsoft.directory/servicePrincipals/tag/update | サービスプリンシパルのタグ プロパティを更新する |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/users/authorizationInfo/update | 複数の値を持つ、ユーザーの証明書ユーザー ID プロパティを更新する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Identity Governance 管理者

[Image: 特権ラベル アイコン。]

このロールのユーザーは、アクセス パッケージ、アクセス レビュー、カタログとポリシーを含む Microsoft Entra ID ガバナンス 構成を管理することができ、アクセスの承認や確認、アクセスの必要がなくなったゲスト ユーザーの削除が確実に行われるようにすることができます。

| Actions | Description |
| --- | --- |
| microsoft.directory/accessReviews/allProperties/allTasks | Microsoft Entra ID でのアクセス レビューの作成と削除、およびアクセス レビューのすべてのプロパティの読み取りと更新 |
| microsoft.directory/accessReviews/definitions.applications/allProperties/allTasks | Microsoft Entra ID のアプリケーション ロールの割り当てのアクセス レビューを管理 |
| microsoft.directory/accessReviews/definitions.entitlementManagement/allProperties/allTasks | エンタイトルメント管理でアクセス パッケージの割り当てに対するアクセス レビューを管理する |
| microsoft.directory/accessReviews/definitions.groups/allProperties/read | ロールを割り当て可能なグループを含む、セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューのすべてのプロパティを読み取る。 |
| microsoft.directory/accessReviews/definitions.groups/allProperties/update | ロールを割り当て可能なグループを除く、セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューのすべてのプロパティを更新する。 |
| microsoft.directory/accessReviews/definitions.groups/create | セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューを作成する。 |
| microsoft.directory/accessReviews/definitions.groups/delete | セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューを削除する |
| microsoft.directory/entitlementManagement/allProperties/allTasks | Microsoft Entra エンタイトルメント管理でのリソースの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |

### Insights 管理者

このロールのユーザーは、Microsoft Viva Insights アプリの管理機能の完全なセットにアクセスできます。 このロールでは、ディレクトリ情報の読み取り、サービスの正常性の監視、サポート チケットの提出、Insights 管理者設定の側面へのアクセスを行うことができます。

[詳細情報](https://go.microsoft.com/fwlink/?linkid=2129521)

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.insights/allEntities/allProperties/allTasks | Insights アプリのすべての側面を管理する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 分析情報アナリスト

以下を実行する必要があるユーザーには Insights アナリスト ロールを割り当てます。

- Microsoft Viva Insights アプリでデータを分析するが、構成設定の管理は許可されていない
- クエリを作成、管理、実行する
- Microsoft 365 管理センターで基本設定とレポートを表示する
- Microsoft 365 管理センターでサービス要求を作成および管理する

[詳細情報](https://go.microsoft.com/fwlink/?linkid=2129521)

| Actions | Description |
| --- | --- |
| microsoft.insights/queries/allProperties/allTasks | Viva Insights でクエリを実行および管理する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Insights ビジネス リーダー

このロールのユーザーは、Microsoft Viva Insights アプリを使用して、一連のダッシュボードと分析情報にアクセスできます。 これには、すべてのダッシュボード、表示される分析情報、およびデータ探索機能へのフル アクセスが含まれます。 このロールのユーザーには、製品の構成設定へのアクセス権がありません (これは Insights 管理者ロールの責任範囲です)。

[詳細情報](https://go.microsoft.com/fwlink/?linkid=2129521)

| Actions | Description |
| --- | --- |
| microsoft.insights/programs/allProperties/update | Insights アプリでプログラムをデプロイし、管理する |
| microsoft.insights/reports/allProperties/read | Insights アプリでレポートとダッシュボードを表示する |

### Intune 管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールが割り当てられたユーザーは、Microsoft Intune Online 内でグローバル アクセス許可を持ちます (このサービスが存在する場合)。 さらに、このロールはポリシーを関連付けるためにユーザーとデバイスを管理することができ、グループを作成および管理することもできます。 詳細については、「[Microsoft Intune でのロール ベースの管理制御 (RBAC)](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control)」を参照してください。

このロールでは、すべてのセキュリティ グループを作成および管理できます。 ただし、Intune 管理者には、Microsoft 365 グループに対する管理者権限はありません。 つまり、管理者は、組織内のすべての Microsoft 365 グループの所有者およびメンバーシップを更新することはできません。 ただし、自分で作成した Microsoft 365 グループを管理することはできます。これは、エンド ユーザーの特権の一部として提供されます。 そのため、自分が作成したすべての Microsoft 365 グループ (セキュリティ グループではありません) は、自分の 250 のクォータに対してカウントする必要があります。

Note

Microsoft Graph API と Microsoft Graph PowerShell では、このロールは Intune サービス管理者という名前です。 [Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-overview) では、Intune 管理者という名前が付けられています。

| Actions | Description |
| --- | --- |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.cloudPC/allEntities/allProperties/allTasks | Windows 365 のすべての側面を管理する |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/contacts/basic/update | 連絡先の基本プロパティを更新する |
| microsoft.directory/contacts/create | 連絡先を作成する |
| microsoft.directory/contacts/delete | 連絡先を削除する |
| microsoft.directory/deletedItems.devices/delete | 復元できなくなったデバイスを完全に削除する |
| microsoft.directory/deletedItems.devices/restore | 論理的に削除されたデバイスを元の状態に復元する |
| microsoft.directory/deviceLocalCredentials/password/read | Microsoft Entra 参加済みデバイスのバックアップされたローカル管理者アカウント資格情報 (パスワードを含む) のすべてのプロパティを読み取る |
| microsoft.directory/deviceManagementPolicies/standard/read | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する標準のプロパティを読み取る |
| microsoft.directory/deviceRegistrationPolicy/standard/read | デバイス登録ポリシーの標準プロパティを読み取る |
| microsoft.directory/devices/basic/update | デバイスの基本プロパティを更新する |
| microsoft.directory/devices/create | デバイスを作成する (Microsoft Entra ID に登録する) |
| microsoft.directory/devices/delete | Microsoft Entra ID からデバイスを削除する |
| microsoft.directory/devices/disable | Microsoft Entra ID でデバイスを無効にする |
| microsoft.directory/devices/enable | Microsoft Entra ID でデバイスを有効にする |
| microsoft.directory/devices/extensionAttributeSet1/update | デバイスの extensionAttribute1 から extensionAttribute5 プロパティを更新する |
| microsoft.directory/devices/extensionAttributeSet2/update | デバイスの extensionAttribute6 から extensionAttribute10 プロパティを更新する |
| microsoft.directory/devices/extensionAttributeSet3/update | デバイスの extensionAttribute11 から extensionAttribute15 プロパティを更新する |
| microsoft.directory/devices/registeredOwners/update | デバイスの登録済み所有者を更新する |
| microsoft.directory/devices/registeredUsers/update | デバイスの登録済みユーザーを更新する |
| microsoft.directory/groups.security/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類のセキュリティ グループの割り当てられたラベル プロパティを更新する |
| microsoft.directory/groups.security/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループの基本プロパティを更新する |
| microsoft.directory/groups.security/classification/update | ロールを割り当て可能なグループを除き、セキュリティ グループの分類プロパティを更新する |
| microsoft.directory/groups.security/create | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する |
| microsoft.directory/groups.security/delete | ロールを割り当て可能なグループを除き、セキュリティ グループを削除する |
| microsoft.directory/groups.security/dynamicMembershipRule/update | ロール割り当て可能なグループを除き、セキュリティ グループの動的メンバーシップ ルールを更新する |
| microsoft.directory/groups.security/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/groups.security/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループの所有者を更新する |
| microsoft.directory/groups.security/visibility/update | ロールを割り当て可能なグループを除き、セキュリティ グループの可視性プロパティを更新する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/photo/update | ユーザーの写真を更新する |
| microsoft.intune/allEntities/allTasks | Microsoft Intune のすべての側面を管理する |
| microsoft.office365.organizationalMessages/allEntities/allProperties/read | Microsoft 365 組織メッセージのすべての側面を読み取る |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### IoT デバイス管理者

次のタスクを実行する必要があるユーザーに IoT デバイス管理者ロールを割り当てます。

- デバイス テンプレートを使用して新しい IoT デバイスをプロビジョニングする
- IoT デバイスのライフサイクルを管理する
- IoT デバイス認証に使用する証明書を構成する
- IoT デバイス テンプレートのライフサイクルを管理する

[詳細情報](https://learn.microsoft.com/ja-jp/graph/api/resources/devicetemplate)

| Actions | Description |
| --- | --- |
| microsoft.directory/certificateBasedDeviceAuthConfigurations/create | IoT デバイスの信頼と認証のための証明機関の構成を作成する |
| microsoft.directory/certificateBasedDeviceAuthConfigurations/credentials/update | モノのインターネット (IoT) デバイスの信頼と認証に関する証明機関の構成に関する資格情報関連プロパティを更新する |
| microsoft.directory/certificateBasedDeviceAuthConfigurations/delete | モノのインターネット (IoT) デバイスの証明機関の構成を削除する |
| microsoft.directory/certificateBasedDeviceAuthConfigurations/standard/read | モノのインターネット (IoT) デバイスの信頼と認証に関する証明機関の構成に関する標準プロパティの読み取り |
| microsoft.directory/deviceTemplates/create | モノのインターネット (IoT) デバイス テンプレートを作成する |
| microsoft.directory/deviceTemplates/createDeviceFromTemplate | モノのインターネット (IoT) デバイス テンプレートから IoT デバイスを作成する |
| microsoft.directory/deviceTemplates/delete | モノのインターネット (IoT) デバイス テンプレートを削除する |
| microsoft.directory/deviceTemplates/deviceInstances/read | モノのインターネット (IoT) デバイス リンクからデバイス インスタンスを読み取る |
| microsoft.directory/deviceTemplates/owners/read | モノのインターネット (IoT) デバイス テンプレートの所有者を読み取る |
| microsoft.directory/deviceTemplates/owners/update | モノのインターネット (IoT) デバイス テンプレートの所有者を更新する |

### Kaizala 管理者

このロールが割り当てられたユーザーは、Microsoft Kaizala 内で設定を管理するグローバル アクセス許可を持ちます (このサービスが存在する場合)。また、サポート チケットを管理し、サービス正常性を監視できます。 さらに、このユーザーは、組織のメンバーによる Kaizala の導入と使用法に関連したレポート、および Kaizala アクションを使用して生成されるビジネス レポートにもアクセスできます。

| Actions | Description |
| --- | --- |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ナレッジ管理者

このロールのユーザーには、Microsoft 365 管理センター内のすべての知識、学習およびインテリジェント機能の設定へのフル アクセスがあります。 また、製品のスイート、ライセンスの詳細の全般的な知識があり、アクセスを制御する責任があります。 知識管理者は、トピック、頭字語、学習リソースなどのコンテンツを作成および管理できます。 さらに、これらのユーザーは、コンテンツ センターの作成、サービス正常性の監視、サービス要求の作成を行うことができます。

| Actions | Description |
| --- | --- |
| microsoft.directory/groups.security/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループの基本プロパティを更新する |
| microsoft.directory/groups.security/create | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する |
| microsoft.directory/groups.security/createAsOwner | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する 作成者は最初の所有者として追加されます。 |
| microsoft.directory/groups.security/delete | ロールを割り当て可能なグループを除き、セキュリティ グループを削除する |
| microsoft.directory/groups.security/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/groups.security/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループの所有者を更新する |
| microsoft.office365.knowledge/contentUnderstanding/allProperties/allTasks | Microsoft 365 管理センターのコンテンツの解釈のすべてのプロパティを読み取り、更新する |
| microsoft.office365.knowledge/knowledgeNetwork/allProperties/allTasks | Microsoft 365 管理センターの知識ネットワークのすべてのプロパティを読み取り、更新する |
| microsoft.office365.knowledge/learningSources/allProperties/allTasks | Learning アプリで学習ソースとそのすべてのプロパティを管理する |
| microsoft.office365.protectionCenter/sensitivityLabels/allProperties/read | セキュリティおよびコンプライアンス センターの秘密度ラベルのすべてのプロパティを読み取る |
| microsoft.office365.sharePoint/allEntities/allTasks | SharePoint ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ナレッジ マネージャー

このロールのユーザーは、トピック、頭字語、学習コンテンツなどのコンテンツを作成および管理できます。 これらのユーザーは、主に知識の品質と構造を担当します。 このユーザーは、トピックを確認したり、編集を承認したり、トピックを削除したりするトピック管理アクションに対する完全な権限を持っています。 このロールはまた、用語ストア管理ツールの一部として分類を管理したり、コンテンツ センターを作成したりすることもできます。

| Actions | Description |
| --- | --- |
| microsoft.directory/groups.security/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループの基本プロパティを更新する |
| microsoft.directory/groups.security/create | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する |
| microsoft.directory/groups.security/createAsOwner | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する 作成者は最初の所有者として追加されます。 |
| microsoft.directory/groups.security/delete | ロールを割り当て可能なグループを除き、セキュリティ グループを削除する |
| microsoft.directory/groups.security/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/groups.security/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループの所有者を更新する |
| microsoft.office365.knowledge/contentUnderstanding/analytics/allProperties/read | Microsoft 365 管理センターでコンテンツの解釈の分析レポートを読み取る |
| microsoft.office365.knowledge/knowledgeNetwork/topicVisibility/allProperties/allTasks | Microsoft 365 管理センターで知識ネットワークのトピックの可視性を管理する |
| microsoft.office365.sharePoint/allEntities/allTasks | SharePoint ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ライセンス管理者

このロールのユーザーは、ユーザーに対するライセンス割り当ての読み取り、追加、削除、更新、グループに対する (グループベースのライセンスを使用した) ライセンス割り当ての追加、削除、更新に加え、ユーザーに対する利用場所の管理を行うことができます。 このロールでは、サブスクリプションの購入と管理、グループの作成と管理を行う権限は与えられません。また、利用場所を超える範囲でのユーザーの作成と管理を行う権限も与えられません。 このロールには、サポート チケットの表示、作成、管理のためのアクセス権がありません。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/groups/assignLicense | グループベースのライセンスのグループに製品ライセンスを割り当てる |
| microsoft.directory/groups/reprocessLicenseAssignment | グループベースのライセンスのライセンス割り当てを再処理する |
| microsoft.directory/users/assignLicense | ユーザー ライセンスの管理 |
| microsoft.directory/users/reprocessLicenseAssignment | ユーザー ライセンス割り当てを再処理する |
| microsoft.directory/users/usageLocation/update | ユーザーの利用場所を更新する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ライフサイクル ワークフロー管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次のタスクを行う必要があるユーザーに、ライフサイクル ワークフロー管理者ロールを割り当てます。

- Microsoft Entra ID のライフサイクル ワークフローに関連付けられているワークフローとタスクのすべての側面を作成および管理する
- スケジュールされたワークフローの実行を確認する
- オンデマンドのワークフロー実行を起動する
- ワークフロー実行ログを検査する

| Actions | Description |
| --- | --- |
| microsoft.directory/lifecycleWorkflows/workflows/allProperties/allTasks | ライフサイクル ワークフローとタスクのすべての側面を Microsoft Entra ID で管理する |
| microsoft.directory/organization/strongAuthentication/read | 組織の強力な認証プロパティを読み取ります |
| microsoft.directory/users/lifeCycleInfo/read | employeeLeaveDateTime などのユーザーのライフサイクル情報を読み取る[Image: 特権ラベル アイコン。] |

### メッセージ センターのプライバシー閲覧者

このロールのユーザーは、データのプライバシー メッセージを含め、メッセージ センター内のすべての通知を監視できます。 メッセージ センターのプライバシー閲覧者は、データのプライバシーに関連したものも含めてメール通知を受け取り、メッセージ センターの設定を使用して登録を解除することができます。 データのプライバシー メッセージを読み取ることができるのは、グローバル管理者とメッセージ センターのプライバシー閲覧者のみになります。 さらに、このロールには、グループ、ドメイン、サブスクリプションを表示する権限が含まれています。 このロールには、サービス要求を表示、作成、または管理するアクセス許可はありません。

| Actions | Description |
| --- | --- |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.messageCenter/securityMessages/read | Microsoft 365 管理センターのメッセージ センターでセキュリティ メッセージを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### メッセージ センター閲覧者

このロールのユーザーは、Exchange、Intune、Microsoft Teamsなどの構成済みサービスで、組織の [メッセージ センター](https://learn.microsoft.com/ja-jp/microsoft-365/admin/manage/message-center) で通知とアドバイザリの正常性の更新を監視できます。 メッセージ センター閲覧者は、投稿の毎週のメール ダイジェストを受け取り、Microsoft 365 でメッセージ センターの投稿を共有できます。 Microsoft Entra ID では、このロールに割り当てられているユーザーは、ユーザーやグループなどの Microsoft Entra サービスへの読み取り専用アクセスのみを持ちます。 このロールには、サポート チケットの表示、作成、管理のためのアクセス権がありません。

| Actions | Description |
| --- | --- |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Microsoft 365 バックアップ管理者

次のタスクを実行する必要があるユーザーに Microsoft 365 バックアップ管理者ロールを割り当てます。

- Microsoft 365 バックアップ のすべての側面を管理する
- SharePoint、OneDrive、Exchange Online のバックアップ構成ポリシーを作成、編集、管理する
- バックアップされた SharePoint サイト、OneDrive アカウント、Exchange メールボックスの復元操作を実行する

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.backup/allEntities/allProperties/allTasks | Microsoft 365 バックアップ のすべての側面を管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Microsoft 365 移行管理者

次のタスクを行う必要があるユーザーに、Microsoft 365 移行管理者ロールを割り当てます。

- Microsoft 365 管理センターの移行マネージャーを使用して、Google ドライブ、Dropbox、Box、Egnyte から Teams、OneDrive for Business、SharePoint サイトを含む Microsoft 365 へのコンテンツ移行を管理する
- 移行ソースを選択し、移行インベントリ (Google ドライブ ユーザー リストなど) を作成し、移行をスケジュールして実行し、レポートをダウンロードする
- 移行先サイトがまだ存在しない場合は新しい SharePoint サイトを作成し、SharePoint 管理サイトで SharePoint リストを作成し、SharePoint リストでアイテムを作成および更新する
- タスクの移行プロジェクト設定と移行ライフサイクルを管理する
- ソースから宛先へのアクセス許可マッピングを管理する

Note

このロールでは、SharePoint 管理センターを使用してファイル共有ソースから移行することはできません。 SharePoint 管理者ロールを使用して、ファイル共有ソースから移行できます。

[詳細情報](https://learn.microsoft.com/ja-jp/sharepointmigration/mm-migration-admin-role)

| Actions | Description |
| --- | --- |
| microsoft.office365.migrations/allEntities/allProperties/allTasks | Microsoft 365 移行のすべての側面を管理する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Microsoft Entra 参加済みデバイスのローカル管理者

このロールは、 [デバイス設定](https://learn.microsoft.com/ja-jp/entra/identity/devices/assign-local-admin)の追加のローカル管理者としてのみ割り当てに使用できます。 このロールを持つユーザーは、Microsoft Entra IDに参加しているすべてのWindows 10以降のデバイスのローカル コンピューター管理者になります。 Microsoft Entra ID内のデバイス オブジェクトを管理する機能はありません。

| Actions | Description |
| --- | --- |
| microsoft.directory/groupSettings/standard/read | グループ設定の基本プロパティを読み取る |
| microsoft.directory/groupSettingTemplates/standard/read | グループ設定テンプレートの基本プロパティを読み取る |

### Microsoft Graph データ接続管理者

Microsoft Graph データ接続管理者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- Microsoft Graph データ接続のすべての管理機能にアクセスする
- テナントで Microsoft Graph データ接続の設定を管理する
- Microsoft Graph データ接続サービスを有効または無効にする
- Microsoft Graph データ接続でデータセット ワークロードの選択を構成する
- Microsoft Graph データ接続でテナント間データ移動設定を構成する
- Microsoft Graph データ接続のアプリケーション承認要求を表示、承認、または拒否する
- Microsoft Graph データ接続のアプリケーション登録を表示、作成、更新、または削除する

[詳細情報](https://learn.microsoft.com/ja-jp/graph/data-connect-concept-overview)

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.graph.dataConnect/allEntities/allProperties/allTasks | Microsoft Graph データ接続の側面を管理する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Microsoft ハードウェア保証管理者

次のタスクを行う必要があるユーザーに、Microsoft ハードウェア保証管理者ロールを割り当てます。

- Surface や HoloLens などの Microsoft 製ハードウェアの新しい保証要求を作成する
- 開封済みまたは終了した保証要求を検索して読み取る
- シリアル番号で保証要求を検索して読み取る
- 配送先住所の作成、読み取り、更新、削除を行う
- オープン保証要求の出荷状態を読み取る
- Microsoft 365 管理センターでサービス要求を作成および管理する
- Microsoft 365 管理センターでメッセージ センターのお知らせを読み取る

保証要求とは、保証の条項に従ってハードウェアの修理または交換を要求することです。 詳しくは、「[Surface の保証とサービス要求のセルフサービス](https://learn.microsoft.com/ja-jp/surface/self-serve-warranty-service)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.hardware.support/shippingAddress/allProperties/allTasks | 他のユーザーが作成した配送先住所を含む、Microsoft ハードウェア保証クレームの配送先住所の作成、読み取り、更新、削除を行います |
| microsoft.hardware.support/shippingStatus/allProperties/read | オープンな Microsoft ハードウェア保証クレームの出荷ステータスを読み取ります |
| microsoft.hardware.support/warrantyClaims/allProperties/allTasks | Microsoft ハードウェア保証クレームのすべての側面を作成および管理します |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Microsoft ハードウェア保証スペシャリスト

次のタスクを行う必要があるユーザーに、Microsoft ハードウェア保証スペシャリスト ロールを割り当てます。

- Surface や HoloLens などの Microsoft 製ハードウェアの新しい保証要求を作成する
- 作成した保証要求を読み取る
- 既存の配送先住所を読み取って更新する
- 作成したオープン保証要求の出荷状態を読み取る
- Microsoft 365 管理センターでサービス要求を作成および管理する

保証要求とは、保証の条項に従ってハードウェアの修理または交換を要求することです。 詳しくは、「[Surface の保証とサービス要求のセルフサービス](https://learn.microsoft.com/ja-jp/surface/self-serve-warranty-service)」をご覧ください。

| Actions | Description |
| --- | --- |
| microsoft.hardware.support/shippingAddress/allProperties/read | 他のユーザーが作成した既存の配送先住所を含む、Microsoft ハードウェア保証クレームの配送先住所を読み取ります |
| microsoft.hardware.support/shippingStatus/allProperties/read | オープンな Microsoft ハードウェア保証クレームの出荷ステータスを読み取ります |
| microsoft.hardware.support/warrantyClaims/allProperties/read | Microsoft ハードウェア保証クレームを読み取ります |
| microsoft.hardware.support/warrantyClaims/createAsOwner | 作成者が所有者である Microsoft ハードウェア保証クレームを作成します |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ネットワーク管理者

このロールのユーザーは、ユーザーの場所からのネットワーク テレメトリに基づいて、Microsoft によるネットワーク境界アーキテクチャに関する推奨事項を確認できます。 Microsoft 365 のネットワーク パフォーマンスは、通常はユーザーの場所に固有である、企業の顧客の慎重なネットワーク境界アーキテクチャに依存します。 このロールを使用すると、検出されたユーザーの場所の編集と、それらの場所のネットワーク パラメーターの構成が可能になり、テレメトリの測定と設計に関する推奨事項が向上します。

| Actions | Description |
| --- | --- |
| microsoft.office365.network/locations/allProperties/allTasks | ネットワークの場所のすべての側面を管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Office アプリ管理者

このロールのユーザーは、Microsoft 365 アプリのクラウド設定を管理できます。 これには、クラウド ポリシーの管理、セルフサービス ダウンロードの管理、Office アプリ関連レポートを表示する機能が含まれます。 このロールではさらに、サポート チケットの管理とメインの管理センター内でのサービスの正常性の監視を行うこともできます。 このロールに割り当てられたユーザーは、Office アプリの新機能の通知も管理できます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.userCommunication/allEntities/allTasks | 新機能のメッセージを表示できるかどうかを読み取り、更新する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 組織ブランド化管理者

次のタスクを行う必要があるユーザーに、組織ブランド化管理者ロールを割り当てます。

- テナントにおける組織のブランド化の全側面を管理する
- ブランド化のテーマの読み取り、作成、更新、削除を行う
- 既定のブランド化のテーマとすべてのブランド化ローカライズのテーマを管理する

| Actions | Description |
| --- | --- |
| microsoft.directory/loginOrganizationBranding/allProperties/allTasks | loginTenantBranding の作成と削除、すべてのプロパティの読み取りと更新を行う |

### 組織データ ソース管理者

組織データ ソース管理者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- Microsoft 365 および Microsoft Viva アプリケーションの組織データの取り込みと管理に関連する設定を管理する
- Microsoft 365 および Microsoft Viva アプリケーションで取り込まれた組織データをアップロード、更新、削除する
- 承認されたアプリケーションから組織データをエクスポートする

[詳細情報](https://learn.microsoft.com/ja-jp/viva/import-orgdata)

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.microsoft365.organizationalData/allEntities/allProperties/allTasks | Microsoft 365 で組織データのすべての側面を管理する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 組織メッセージ承認者

次のタスクを行う必要があるユーザーに、組織メッセージ承認者ロールを割り当てます。

- Microsoft 365 管理センターで配信する新しい組織メッセージが Microsoft 365 組織メッセージ プラットフォームを使用してユーザーに送信される前に、確認、承認、または拒否する
- 組織メッセージのすべての側面を読み取る
- Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る

| Actions | Description |
| --- | --- |
| microsoft.office365.organizationalMessages/allEntities/allProperties/read | Microsoft 365 組織メッセージのすべての側面を読み取る |
| microsoft.office365.organizationalMessages/allEntities/allProperties/update | Microsoft 365 管理センターで配信する新しい組織メッセージがユーザーに送信される前に、承認または拒否します |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 組織メッセージ ライター

次のタスクを行う必要があるユーザーに、組織メッセージ ライター ロールを割り当てます。

- Microsoft 365 管理センターまたは Microsoft Intune を使って組織のメッセージの書き込み、発行、削除を行う
- Microsoft 365 管理センターまたは Microsoft Intune を使って、組織のメッセージ配信オプションを管理する
- Microsoft 365 管理センターまたは Microsoft Intune を使って、組織のメッセージ配信結果を読み取る
- Microsoft 365 管理センターで使用状況レポートとほとんどの設定を表示するが、変更することはできない

| Actions | Description |
| --- | --- |
| microsoft.office365.organizationalMessages/allEntities/allProperties/allTasks | Microsoft 365 組織メッセージのすべての作成側面を管理する |
| microsoft.office365.usageReports/allEntities/standard/read | テナントレベルの集計された Office 365 利用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### パートナー レベル 1 のサポート

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 使用しないでください。 このロールは非推奨となっており、将来的に Microsoft Entra ID から削除されます。 このロールは少数の Microsoft 再販パートナーを対象としており、一般的な使用を目的としたものではありません。

Important

このロールでは、管理者以外のパスワードをリセットし、更新トークンを無効にすることができます。 このロールは非推奨であるため、使用しないでください。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/applications/appRoles/update | すべての種類のアプリケーションで appRoles プロパティを更新する |
| microsoft.directory/applications/audience/update | アプリケーションの対象ユーザー プロパティを更新する |
| microsoft.directory/applications/authentication/update | すべての種類のアプリケーションで認証を更新する |
| microsoft.directory/applications/basic/update | アプリケーションの基本プロパティを更新する |
| microsoft.directory/applications/credentials/update | アプリケーション資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/applications/notes/update | アプリケーションのメモを更新する |
| microsoft.directory/applications/owners/update | アプリケーションの所有者を更新する |
| microsoft.directory/applications/permissions/update | すべての種類のアプリケーションで、公開されたアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/applications/policies/update | アプリケーションのポリシーを更新する |
| microsoft.directory/applications/tag/update | アプリケーションのタグを更新する |
| microsoft.directory/contacts/basic/update | 連絡先の基本プロパティを更新する |
| microsoft.directory/contacts/create | 連絡先を作成する |
| microsoft.directory/contacts/delete | 連絡先を削除する |
| microsoft.directory/deletedItems.groups/restore | 論理的に削除されたグループを元の状態に復元する |
| microsoft.directory/deletedItems.users/restore | 論理的に削除されたユーザーを元の状態に復元する |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups/create | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを作成する |
| microsoft.directory/groups/delete | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを削除する |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups/restore | ソフト削除されたコンテナーからグループを復元する |
| microsoft.directory/oAuth2PermissionGrants/allProperties/allTasks | OAuth 2.0 アクセス許可の付与の作成と削除、およびすべてのプロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/users/assignLicense | ユーザー ライセンスの管理 |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/create | ユーザーの追加[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/delete | ユーザーの削除[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/disable | ユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/enable | ユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/photo/update | ユーザーの写真を更新する |
| microsoft.directory/users/restore | 削除されたユーザーを復元する |
| microsoft.directory/users/userPrincipalName/update | ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### パートナー レベル 2 のサポート

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 使用しないでください。 このロールは非推奨となっており、将来的に Microsoft Entra ID から削除されます。 このロールは少数の Microsoft 再販パートナーを対象としており、一般的な使用を目的としたものではありません。

Important

このロールは、すべての非管理者および管理者 (グローバル管理者を含む) のパスワードをリセットし、更新トークンを無効にすることができます。 このロールは非推奨であるため、使用しないでください。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/applications/appRoles/update | すべての種類のアプリケーションで appRoles プロパティを更新する |
| microsoft.directory/applications/audience/update | アプリケーションの対象ユーザー プロパティを更新する |
| microsoft.directory/applications/authentication/update | すべての種類のアプリケーションで認証を更新する |
| microsoft.directory/applications/basic/update | アプリケーションの基本プロパティを更新する |
| microsoft.directory/applications/credentials/update | アプリケーション資格情報を更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/applications/notes/update | アプリケーションのメモを更新する |
| microsoft.directory/applications/owners/update | アプリケーションの所有者を更新する |
| microsoft.directory/applications/permissions/update | すべての種類のアプリケーションで、公開されたアクセス許可と必要なアクセス許可を更新する |
| microsoft.directory/applications/policies/update | アプリケーションのポリシーを更新する |
| microsoft.directory/applications/tag/update | アプリケーションのタグを更新する |
| microsoft.directory/contacts/basic/update | 連絡先の基本プロパティを更新する |
| microsoft.directory/contacts/create | 連絡先を作成する |
| microsoft.directory/contacts/delete | 連絡先を削除する |
| microsoft.directory/deletedItems.groups/restore | 論理的に削除されたグループを元の状態に復元する |
| microsoft.directory/deletedItems.users/restore | 論理的に削除されたユーザーを元の状態に復元する |
| microsoft.directory/domains/allProperties/allTasks | ドメインの作成と削除、すべてのプロパティの読み取りと更新を行う[Image: 特権ラベル アイコン。] |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups/create | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを作成する |
| microsoft.directory/groups/delete | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを削除する |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups/restore | ソフト削除されたコンテナーからグループを復元する |
| microsoft.directory/oAuth2PermissionGrants/allProperties/allTasks | OAuth 2.0 アクセス許可の付与の作成と削除、およびすべてのプロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/organization/basic/update | 組織で基本プロパティを更新する |
| microsoft.directory/roleAssignments/allProperties/allTasks | ロールの割り当ての作成と削除、およびすべてのロールの割り当てプロパティの読み取りと更新 |
| microsoft.directory/roleDefinitions/allProperties/allTasks | ロールの定義の作成と削除、およびすべてのプロパティの読み取りと更新 |
| microsoft.directory/scopedRoleMemberships/allProperties/allTasks | scopedRoleMemberships の作成と削除、およびすべてのプロパティの読み取りと更新 |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/subscribedSkus/standard/read | サブスクリプションの基本プロパティの読み取り |
| microsoft.directory/users/assignLicense | ユーザー ライセンスの管理 |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/create | ユーザーの追加[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/delete | ユーザーの削除[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/disable | ユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/enable | ユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/photo/update | ユーザーの写真を更新する |
| microsoft.directory/users/restore | 削除されたユーザーを復元する |
| microsoft.directory/users/userPrincipalName/update | ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### パスワード管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールのユーザーは、制限付きでパスワードを管理することができます。 このロールでは、サービス要求を管理したり、サービスの正常性を監視したりすることはできません。 パスワード管理者がユーザーのパスワードをリセットできるかどうかは、ユーザーが割り当てられているロールに依存します。 パスワード管理者がパスワードをリセットできるロールの一覧については、「[パスワードをリセットできるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-reset-passwords)」を参照してください。

このロールを持つユーザー **は、** 次の操作を実行できません。

- [ロールを割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)のメンバーと所有者の資格情報を変更したり、MFA をリセットしたりすることはできません。

| Actions | Description |
| --- | --- |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 人事管理者

ユーザー管理者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- 管理者を含むすべてのユーザーのプロファイル写真を更新する
- 代名詞、名前の発音、プロファイル カードの設定など、すべてのユーザーのユーザー設定を更新する

| Actions | Description |
| --- | --- |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.people/users/photo/read | ユーザーのプロフィール写真を読み取る |
| microsoft.people/users/photo/update | ユーザーのプロファイル写真を更新する |
| microsoft.peopleAdmin/organization/allProperties/read | 代名詞、名前の発音、プロファイル カードの設定など、ユーザーのユーザー設定を読み取ります |
| microsoft.peopleAdmin/organization/allProperties/update | 代名詞、名前の発音、プロファイル カードの設定など、ユーザーのユーザー設定を更新する |

### Permissions Management の管理者

次のタスクを行う必要があるユーザーに、Permissions Management の管理者ロールを割り当てます。

- Entra Permissions Management のすべての側面を管理する (サービスが存在するとき)

Permissions Management のロールとポリシーの詳細については、[ロール/ポリシーに関する情報を表示する](https://learn.microsoft.com/ja-jp/entra/permissions-management/how-to-view-role-policy)方法に関するページを参照してください。

| Actions | Description |
| --- | --- |
| microsoft.permissionsManagement/allEntities/allProperties/allTasks | Microsoft Entra Permissions Management のすべての側面を管理する |

### 管理者の配置

次のタスクを実行する必要があるユーザーに Places Administrator ロールを割り当てます。

- Microsoft Places サービスのすべての側面を管理する
- 建物、フロア、会議室、デスクの構成と管理
- 関連する予約ポリシーを監視および管理する

[詳細情報](https://learn.microsoft.com/ja-jp/microsoft-365/places/configure-admin-roles)

| Actions | Description |
| --- | --- |
| microsoft.places/allEntities/allProperties/allTasks | Microsoft Places サービスのすべての側面を管理する |

### Power Platform 管理者

このロールのユーザーは、環境、Power Apps、フロー、データ損失防止ポリシーのすべての側面を作成および管理できます。 さらに、このロールを持つユーザーは、サポート チケットを管理し、サービス正常性を監視できます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.dynamics365/allEntities/allTasks | Dynamics 365 のすべての側面を管理する |
| microsoft.flow/allEntities/allTasks | Power Automate のすべての側面を管理する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.powerApps/allEntities/allTasks | Power Apps のすべての側面を管理する |

### プリンター管理者

このロールのユーザーは、プリンターを登録して、ユニバーサル印刷コネクタの設定など、Microsoft ユニバーサル印刷ソリューションのすべてのプリンター構成のすべての側面を管理できます。 すべての委任された印刷アクセス許可要求に同意することができます。 プリンター管理者は、印刷レポートにアクセスすることもできます。

| Actions | Description |
| --- | --- |
| microsoft.azure.print/allEntities/allProperties/allTasks | Microsoft Print でプリンターとコネクタの作成と削除、すべてのプロパティの読み取りと更新を行う |

### プリンター技術者

このロールのユーザーは、プリンターを登録して、Microsoft ユニバーサル印刷ソリューションでプリンターの状態を管理できます。 すべてのコネクタ情報を読み取ることもできます。 プリンター技術者が行うことができない主要なタスクは、プリンターに対するユーザー アクセス許可の設定と、プリンターの共有です。

| Actions | Description |
| --- | --- |
| microsoft.azure.print/connectors/allProperties/read | Microsoft Print でコネクタのすべてのプロパティを読み取る |
| microsoft.azure.print/printers/allProperties/read | Microsoft Print でプリンターのすべてのプロパティを読み取る |
| microsoft.azure.print/printers/basic/update | Microsoft Print でプリンターの基本プロパティを更新する |
| microsoft.azure.print/printers/register | Microsoft Print でプリンターを登録する |
| microsoft.azure.print/printers/unregister | Microsoft Print でプリンターを登録解除する |

### 特権認証管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次の作業を行う必要があるユーザーに、特権認証管理者ロールを割り当てます。

- グローバル管理者を含むユーザーの認証方法 (パスワードを含む) の設定またはリセット。
- グローバル管理者を含むユーザーの削除または復元。 詳細については、「[機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)」を参照してください。
- 既存のパスワード以外の資格情報 (MFA や FIDO2 など) に対する再登録をユーザーに強制し、**このデバイスに MFA を記憶する**機能を取り消し、すべてのユーザーの次のサインイン時に MFA の入力を求めます。
- すべてのユーザーの機密性の高いプロパティの更新。 詳細については、「[機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)」を参照してください。
- Azure と Microsoft 365 管理センターでのサポート チケットの作成および管理。
- PKI ベースの信頼ストアを使用して証明機関を構成する (プレビュー)

このロールを持つユーザー **は、** 次の操作を実行できません。

- 従来の MFA 管理ポータルでは、ユーザーごとの MFA を管理できません。

認証関連ロールの機能との比較を次の表に示します。

| Role | ユーザーの認証方法の管理 | ユーザーごとの MFA の管理 | MFA 設定の管理 | 認証方法ポリシーの管理 | パスワード保護ポリシーの管理 | 機密性の高いプロパティの更新 | ユーザーの削除と復元 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | No | No | No | No | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] |
| [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) | すべてのユーザーに対してはい | No | No | No | No | すべてのユーザーに対してはい | すべてのユーザーに対してはい |
| [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) | No | Yes | Yes | Yes | Yes | No | No |
| [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | No | No | No | No | No | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)の場合は [はい] |

Important

このロールのユーザーは、機密情報や個人情報または Microsoft Entra ID の内外の重要な構成にアクセスできるユーザーのパスワードを変更できます。 ユーザーの資格情報を変更することは、そのユーザーの ID とアクセス許可を引き受けることができることを意味します。 例えば次が挙げられます。

- 所有しているアプリの資格情報を管理できる、アプリケーションの登録とエンタープライズ アプリケーションの所有者。 これらのアプリには、認証管理者に付与されていない Microsoft Entra ID およびその他の場所への特権アクセス許可がある場合があります。 認証管理者は、このパスからアプリケーション所有者の ID を引き受け、さらにそのアプリケーションの資格情報を更新して特権アプリケーションの ID を引き受けることができます。
- 機密情報や個人情報または Azure の重要な構成にアクセスできる Azure サブスクリプション所有者。
- グループ メンバーシップを管理できるセキュリティ グループと Microsoft 365 グループの所有者。 これらのグループは、機密情報や個人情報または Microsoft Entra ID や別の場所の重要な構成へのアクセス権を付与される場合があります。
- Exchange Online、Microsoft Defender ポータル、Microsoft Purview ポータル、人事システムなど、Microsoft Entra ID 以外の他のサービスの管理者。
- 機密情報や個人情報にアクセスできる場合がある役員、弁護士、人事担当者のような非管理者。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/deletedItems.users/restore | 論理的に削除されたユーザーを元の状態に復元する |
| microsoft.directory/users/authenticationMethods/basic/update | ユーザーの認証方法の基本プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/create | ユーザーの認証方法を更新します[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/delete | ユーザーの認証方法を削除する[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authenticationMethods/standard/read | ユーザーの認証方法の標準プロパティを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/authorizationInfo/update | 複数の値を持つ、ユーザーの証明書ユーザー ID プロパティを更新する |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/delete | ユーザーの削除[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/disable | ユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/enable | ユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/restore | 削除されたユーザーを復元する |
| microsoft.directory/users/userPrincipalName/update | ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 特権ロール管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールが割り当てられたユーザーは、Microsoft Entra ID と Microsoft Entra Privileged Identity Management 内でロールの割り当てを管理できます。 Microsoft Entra ロールに割り当てることができるグループの作成と管理が可能です。 さらに、このロールは、Privileged Identity Management と管理単位のすべての側面を管理できます。

Important

このロールは、全体管理者ロールを含むすべての Microsoft Entra ロールの割り当てを管理する権限を付与します。 このロールには、Microsoft Entra ID でユーザーの作成や更新などの特権付きの機能が含まれていません。 ただし、このロールが割り当てられたユーザーは、追加のロールを割り当てることで、自分または他のユーザーの追加の特権を付与できます。

| Actions | Description |
| --- | --- |
| microsoft.directory/accessReviews/definitions.applications/allProperties/read | Microsoft Entra ID でアプリケーション ロール割り当てのアクセス レビューのすべてのプロパティを読み取る |
| microsoft.directory/accessReviews/definitions.directoryRoles/allProperties/allTasks | Microsoft Entra ロール割り当てに対するアクセス レビューを管理する |
| microsoft.directory/accessReviews/definitions.groups/allProperties/read | ロールを割り当て可能なグループを含む、セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューのすべてのプロパティを読み取る。 |
| microsoft.directory/accessReviews/definitions.groupsAssignableToRoles/allProperties/update | Microsoft Entra ロールに割り当て可能なグループ内のメンバーシップに対するアクセス レビューのすべてのプロパティを更新する |
| microsoft.directory/accessReviews/definitions.groupsAssignableToRoles/create | Microsoft Entra ロールに割り当て可能なグループ内のメンバーシップに対するアクセス レビューを作成する |
| microsoft.directory/accessReviews/definitions.groupsAssignableToRoles/delete | Microsoft Entra ロールに割り当て可能なグループ内のメンバーシップに対するアクセス レビューを削除する |
| microsoft.directory/administrativeUnits/allProperties/allTasks | 管理単位 (メンバーを含む) の作成と管理する |
| microsoft.directory/authorizationPolicy/allProperties/allTasks | 認可ポリシーのすべての側面を管理する[Image: 特権ラベル アイコン。] |
| microsoft.directory/directoryRoles/allProperties/allTasks | ディレクトリ ロールの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/groupsAssignableToRoles/allProperties/update | ロールを割り当て可能なグループを更新する |
| microsoft.directory/groupsAssignableToRoles/assignLicense | ロール割り当て可能なグループにライセンスを割り当てる |
| microsoft.directory/groupsAssignableToRoles/create | ロールを割り当て可能なグループを作成する |
| microsoft.directory/groupsAssignableToRoles/delete | ロールを割り当て可能なグループを削除する |
| microsoft.directory/groupsAssignableToRoles/reprocessLicenseAssignment | ロール割り当て可能なグループへのライセンス割り当てを再処理する |
| microsoft.directory/groupsAssignableToRoles/restore | ロールを割り当て可能なグループを復元する |
| microsoft.directory/oAuth2PermissionGrants/allProperties/allTasks | OAuth 2.0 アクセス許可の付与の作成と削除、およびすべてのプロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/permissionGrantPolicies/allProperties/read | アクセス許可付与ポリシーのすべてのプロパティを読み取ります。 |
| microsoft.directory/permissionGrantPolicies/allProperties/update | アクセス許可付与ポリシーのすべてのプロパティを更新する |
| microsoft.directory/permissionGrantPolicies/create | アクセス許可付与ポリシーを作成する |
| microsoft.directory/permissionGrantPolicies/delete | アクセス許可付与ポリシーを削除する |
| microsoft.directory/privilegedIdentityManagement/allProperties/allTasks | Privileged Identity Management ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.directory/roleAssignments/allProperties/allTasks | ロールの割り当ての作成と削除、およびすべてのロールの割り当てプロパティの読み取りと更新 |
| microsoft.directory/roleDefinitions/allProperties/allTasks | ロールの定義の作成と削除、およびすべてのプロパティの読み取りと更新 |
| microsoft.directory/scopedRoleMemberships/allProperties/allTasks | scopedRoleMemberships の作成と削除、およびすべてのプロパティの読み取りと更新 |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/servicePrincipals/managePermissionGrantsForAll.microsoft-company-admin | 任意のアプリケーションに対するすべてのアクセス許可に同意を付与する |
| microsoft.directory/servicePrincipals/permissions/update | サービスプリンシパルのアクセス許可を更新する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Purview ワークロード コンテンツ管理者

Purview ワークロード コンテンツ管理者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- Microsoft Purview ポータルからアクセスするときにMicrosoft 365データ (SharePoint、Teams、OneDrive、Exchangeなど) を管理または消去する

Important

Microsoft Purview ポータルを使用してこのロールを割り当てます。 Microsoft Entra 管理センターを使用してこのロールを割り当てようとすると、上書きされる可能性があります。

Microsoft Purviewは、ロール グループを使用して、ユーザーがMicrosoft Purview ポータルで実行できるタスクを制限します。 Purview ワークロード コンテンツ管理者ロールがMicrosoft Purview ポータルのロールにマップされる方法の詳細については、「[Purview ロールの割り当て移行ツール」](https://learn.microsoft.com/ja-jp/purview/purview-role-assignment-migrator)を参照してください。

### Purview ワークロード コンテンツ リーダー

次のタスクを実行する必要があるユーザーに Purview ワークロード コンテンツ閲覧者ロールを割り当てます。

- Microsoft Purview ポータルから送信された実行時間の長い操作を処理するときに、Microsoft 365 (SharePoint、Teams、OneDrive、Exchangeなど) からデータを読み取ります。

Important

Microsoft Purview ポータルを使用してこのロールを割り当てます。 Microsoft Entra 管理センターを使用してこのロールを割り当てようとすると、上書きされる可能性があります。

Microsoft Purviewは、ロール グループを使用して、ユーザーがMicrosoft Purview ポータルで実行できるタスクを制限します。 Purview ワークロード コンテンツ閲覧者ロールがMicrosoft Purview ポータルのロールにマップされる方法の詳細については、「[Purview ロールの割り当て移行ツール」](https://learn.microsoft.com/ja-jp/purview/purview-role-assignment-migrator)を参照してください。

### Purview ワークロード コンテンツ ライター

Purview ワークロード コンテンツ ライター ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- Microsoft Purview ポータルからアクセスするときにMicrosoft 365データ (SharePoint、Teams、OneDrive、Exchangeなど) を読み取って編集する

Important

Microsoft Purview ポータルを使用してこのロールを割り当てます。 Microsoft Entra 管理センターを使用してこのロールを割り当てようとすると、上書きされる可能性があります。

Microsoft Purviewは、ロール グループを使用して、ユーザーがMicrosoft Purview ポータルで実行できるタスクを制限します。 Purview ワークロード コンテンツ ライター ロールがMicrosoft Purview ポータルのロールにマップされる方法の詳細については、「[Purview ロールの割り当て移行ツール」](https://learn.microsoft.com/ja-jp/purview/purview-role-assignment-migrator)を参照してください。

### レポート閲覧者

このロールが割り当てられたユーザーは、Microsoft 365 管理センターで使用状況のレポート データとレポート ダッシュボードを表示できます。また、Fabric と Power BI で導入コンテキスト パックを表示できます。 さらに、このロールからは、Microsoft Entra 内のすべてのサインイン ログ、監査ログ、アクティビティ レポートと、Microsoft Graph レポート API から返されるデータにもアクセスできます。 レポート閲覧者ロールが割り当てられたユーザーは、関連する使用状況と導入メトリックにのみアクセスできます。 製品固有の管理センター (Exchange など) の設定を構成したり、アクセスしたりする管理者アクセス許可はありません。 このロールには、サポート チケットの表示、作成、管理のためのアクセス権がありません。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 管理者の検索

このロールのユーザーには、Microsoft 365 管理センター内のすべての Microsoft Search 管理機能へのフル アクセスがあります。 さらに、これらのユーザーは、メッセージ センターを表示し、サービス正常性を監視し、サービス要求を作成することができます。

| Actions | Description |
| --- | --- |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.search/content/manage | Microsoft Search でコンテンツの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 検索エディター

このロールのユーザーは、ブックマーク、Q&A、および場所を含め、Microsoft 365 管理センターで Microsoft Search のコンテンツを作成、管理、および削除できます。

| Actions | Description |
| --- | --- |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.search/content/manage | Microsoft Search でコンテンツの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### セキュリティ管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールを持つユーザーには、Microsoft Defender ポータル、Microsoft Entra ID 保護、Microsoft Entra Authentication、Azure Information Protection、および Microsoft Purview ポータルでセキュリティ関連の機能を管理するためのアクセス許可があります。 Office 365 のアクセス許可の詳細については、「[Microsoft Defender for Office 365 および Microsoft Purview コンプライアンスのロールとロール グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions)」を参照してください。

| In | 実行できる |
| --- | --- |
| [Microsoft Defender ポータル](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-365-defender-portal) | Microsoft 365サービス全体のセキュリティ関連ポリシーの監視セキュリティ脅威とアラートの管理レポートを表示する |
| [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) | セキュリティ閲覧者ロールのすべてのアクセス許可パスワードのリセットを除く Identity Protection のすべての操作を実行します |
| [特権アイデンティティ管理](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) | セキュリティ閲覧者ロールのすべてのアクセス許可Microsoft Entra ロールの割り当てまたは設定を管理**できない** |
| [Microsoft Purview ポータル](https://learn.microsoft.com/ja-jp/purview/purview-portal) | セキュリティ ポリシーの管理セキュリティの脅威の表示、調査、対応レポートを表示する |
| Azure 高度な脅威保護 | 疑わしいセキュリティ アクティビティの監視と対応 |
| [Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/prepare-deployment) | ロールの割り当てコンピューター グループを管理するエンドポイントの脅威の検出と自動修復の構成アラートの表示、調査、対応マシンまたはデバイス インベントリを表示する |
| [Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control) | [Intune Endpoint Security Manager ロール](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control-reference)にマップする |
| [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/manage-admins) | 管理者の追加、ポリシーと設定の追加、ログのアップロード、ガバナンス アクションの実行 |
| [Microsoft 365 サービス正常性](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/view-service-health) | Microsoft 365 サービスの正常性の表示 |
| [スマート ロックアウト](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-password-smart-lockout) | サインイン イベントが失敗したときのロックアウトのしきい値と期間を定義します。 |
| [パスワード保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-password-ban-bad) | カスタムの禁止パスワードの一覧またはオンプレミスのパスワード保護を構成します。 |
| [テナント間同期](https://learn.microsoft.com/ja-jp/entra/identity/multi-tenant-organizations/cross-tenant-synchronization-overview) | 別のテナント内のユーザーのテナント間アクセス設定を構成します。 セキュリティ管理者は、ユーザーを直接作成および削除することはできませんが、両方のテナントでテナント間同期 (特権アクセス許可) が構成されている場合、同期ユーザーを別のテナントから間接的に作成および削除することはできます。 |

| Actions | Description |
| --- | --- |
| microsoft.agentRegistry/allEntities/allProperties/read | Microsoft Entra IDのエージェントレジストリのすべてのプロパティを読む |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/applications/policies/update | アプリケーションのポリシーを更新する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/conditionalAccessPolicies/basic/update | 条件付きアクセス ポリシーの基本プロパティを更新する |
| microsoft.directory/conditionalAccessPolicies/create | 条件付きアクセス ポリシーを作成する |
| microsoft.directory/conditionalAccessPolicies/delete | 条件付きアクセス ポリシーを削除する |
| microsoft.directory/conditionalAccessPolicies/owners/read | 条件付きアクセス ポリシーの所有者を読み取る |
| microsoft.directory/conditionalAccessPolicies/owners/update | 条件付きアクセス ポリシーの所有者を更新する |
| microsoft.directory/conditionalAccessPolicies/policyAppliedTo/read | 条件付きアクセス ポリシーの "適用先" プロパティを読み取る |
| microsoft.directory/conditionalAccessPolicies/standard/read | ポリシーの条件付きアクセスを読み取る |
| microsoft.directory/conditionalAccessPolicies/tenantDefault/update | 条件付きアクセス ポリシーのデフォルト テナントを更新する |
| microsoft.directory/crossTenantAccessPolicy/allowedCloudEndpoints/update | テナント間アクセスポリシーの許可されたクラウドエンドポイントを更新する |
| microsoft.directory/crossTenantAccessPolicy/basic/update | テナント間アクセスポリシーの基本設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/b2bCollaboration/update | 既定のテナント間アクセスポリシーの Microsoft Entra B2B コラボレーション設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/b2bDirectConnect/update | 既定のテナント間アクセスポリシーの Microsoft Entra B2B 直接接続設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/crossCloudMeetings/update | 既定のクロステナントアクセスポリシーのクロスクラウドTeams会議設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read | 既定のテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/default/tenantRestrictions/update | 既定のテナント間アクセスポリシーのテナント制限を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bCollaboration/update | パートナー向けテナント間アクセス ポリシーの Microsoft Entra B2B コラボレーション設定を更新します |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bDirectConnect/update | パートナー向けテナント間アクセス ポリシーの Microsoft Entra B2B 直接接続設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/create | パートナーのテナント間アクセスポリシーを作成する |
| microsoft.directory/crossTenantAccessPolicy/partners/crossCloudMeetings/update | パートナーのテナント間アクセスポリシーのクロスクラウドTeams会議設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/delete | パートナーのテナント間アクセスポリシーを削除する |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/basic/update | テナント間同期ポリシーの基本設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/create | パートナーのテナント間同期ポリシーを作成する |
| microsoft.directory/crossTenantAccessPolicy/partners/identitySynchronization/standard/read | テナント間同期ポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read | パートナーのテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/basic/update | マルチテナント組織のテナント間同期ポリシー テンプレートを更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/resetToDefaultSettings | マルチテナント組織のテナント間同期ポリシー テンプレートを既定の設定にリセットする |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/standard/read | マルチテナント組織のテナント間同期ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/basic/update | マルチテナント組織のテナント間アクセス ポリシー テンプレートを更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/resetToDefaultSettings | マルチテナント組織のテナント間アクセス ポリシー テンプレートを既定の設定にリセットする |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/standard/read | マルチテナント組織のテナント間アクセス ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/tenantRestrictions/update | パートナーのテナント間アクセスポリシーのテナント制限を更新する |
| microsoft.directory/crossTenantAccessPolicy/standard/read | テナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/deviceLocalCredentials/standard/read | Microsoft Entra 参加済みデバイスのバックアップされたローカル管理者アカウント資格情報のすべてのプロパティを読み取る (パスワードを除く) |
| microsoft.directory/domains/federation/update | ドメインのフェデレーション プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/domains/federationConfiguration/basic/update | ドメインの基本的なフェデレーション構成を更新する |
| microsoft.directory/domains/federationConfiguration/create | ドメインのフェデレーション構成を作成する |
| microsoft.directory/domains/federationConfiguration/delete | ドメインのフェデレーション構成を削除する |
| microsoft.directory/domains/federationConfiguration/standard/read | ドメインのフェデレーション構成の標準プロパティを読み取る |
| microsoft.directory/entitlementManagement/allProperties/read | Microsoft Entra エンタイトルメント管理ですべてのプロパティを読み取る |
| microsoft.directory/identityProtection/allProperties/read | Microsoft Entra ID 保護 のすべてのリソースを読み取る |
| microsoft.directory/identityProtection/allProperties/update | Microsoft Entra ID 保護 のすべてのリソースを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/multiTenantOrganization/basic/update | マルチテナント組織の基本プロパティを更新する |
| microsoft.directory/multiTenantOrganization/create | マルチテナント組織を作成する |
| microsoft.directory/multiTenantOrganization/joinRequest/organizationDetails/update | マルチテナント組織に参加する |
| microsoft.directory/multiTenantOrganization/joinRequest/standard/read | マルチテナント組織の参加要求のプロパティを読み取る |
| microsoft.directory/multiTenantOrganization/standard/read | マルチテナント組織の基本プロパティを読み取る |
| microsoft.directory/multiTenantOrganization/tenants/create | マルチテナント組織でテナントを作成する |
| microsoft.directory/multiTenantOrganization/tenants/delete | マルチテナント組織に参加しているテナントを削除する |
| microsoft.directory/multiTenantOrganization/tenants/organizationDetails/read | マルチテナント組織に参加しているテナントの組織の詳細を読み取る |
| microsoft.directory/multiTenantOrganization/tenants/organizationDetails/update | マルチテナント組織に参加しているテナントの基本プロパティを更新する |
| microsoft.directory/multiTenantOrganization/tenants/standard/read | マルチテナント組織に参加しているテナントの基本プロパティを読み取る |
| microsoft.directory/namedLocations/basic/update | ネットワークの場所を定義するカスタム ルールの基本プロパティを更新する |
| microsoft.directory/namedLocations/create | ネットワークの場所を定義するカスタム ルールを作成する |
| microsoft.directory/namedLocations/delete | ネットワークの場所を定義するカスタム ルールを削除する |
| microsoft.directory/namedLocations/standard/read | ネットワークの場所を定義するカスタム ルールの基本プロパティを読み取る |
| microsoft.directory/policies/basic/update | ポリシーの基本プロパティを更新する[Image: 特権ラベル アイコン。] |
| microsoft.directory/policies/create | Microsoft Entra ID でポリシーを作成する |
| microsoft.directory/policies/delete | Microsoft Entra ID でポリシーを削除します。 |
| microsoft.directory/policies/owners/update | ポリシーの所有者を更新する |
| microsoft.directory/policies/tenantDefault/update | 既定の組織ポリシーを更新する |
| microsoft.directory/privilegedIdentityManagement/allProperties/read | Privileged Identity Management のすべてのリソースを読み取る |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/resourceNamespaces/resourceActions/authenticationContext/update | Microsoft 365 ロールベースのアクセス制御 (RBAC) リソース アクションの条件付きアクセス認証コンテキストを更新します[Image: 特権ラベル アイコン。] |
| microsoft.directory/servicePrincipals/policies/update | サービス プリンシパルのポリシーを更新する |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.networkAccess/allEntities/allProperties/allTasks | Microsoft Entra ネットワーク アクセスのすべての側面を管理する |
| microsoft.office365.protectionCenter/allEntities/basic/update | セキュリティおよびコンプライアンス センターのすべてリソースの基本プロパティを読み取る |
| microsoft.office365.protectionCenter/allEntities/standard/read | セキュリティおよびコンプライアンス センターのすべてリソースの標準プロパティを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/payload/allProperties/allTasks | 攻撃シミュレーターで攻撃ペイロードを作成および管理する |
| microsoft.office365.protectionCenter/attackSimulator/reports/allProperties/read | 攻撃のシミュレーション、応答、関連付けられているトレーニングのレポートを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/simulation/allProperties/allTasks | 攻撃シミュレーターで攻撃のシミュレーション テンプレートを作成および管理する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### セキュリティ オペレーター

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールを持つユーザーは、アラートの管理、セキュリティ インシデント時の ID 封じ込めアクションの実行、およびセキュリティ関連機能に対するグローバル読み取り専用アクセス (Microsoft Defender ポータル、Microsoft Entra ID 保護、Privileged Identity Management、およびMicrosoft Purview ポータル。 Office 365 のアクセス許可の詳細については、「[Microsoft Defender for Office 365 および Microsoft Purview コンプライアンスのロールとロール グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions)」を参照してください。

| In | 実行できる |
| --- | --- |
| [Microsoft Defender ポータル](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-365-defender-portal) | セキュリティ閲覧者ロールのすべてのアクセス許可セキュリティの脅威アラートの表示、調査、対応セキュリティ インシデントに対して ID 包含アクションを実行する  Microsoft Defender ポータルでセキュリティ設定を管理する |
| [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) | セキュリティ閲覧者ロールのすべてのアクセス許可リスクベースのポリシーの構成または変更、パスワードのリセット、アラート メールの構成を除く、Identity Protection のすべての操作の実行 |
| [特権アイデンティティ管理](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) | セキュリティ閲覧者ロールのすべてのアクセス許可 |
| [Microsoft Purview ポータル](https://learn.microsoft.com/ja-jp/purview/purview-portal) | セキュリティ閲覧者ロールのすべてのアクセス許可セキュリティ アラートの表示、調査、対応 |
| [Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/prepare-deployment) | セキュリティ閲覧者ロールのすべてのアクセス許可セキュリティ アラートの表示、調査、対応Microsoft Defender for Endpoint でロールベースのアクセス制御を有効にすると、セキュリティ閲覧者ロールなどの読み取り専用アクセス許可を持つユーザーは、Microsoft Defender for Endpoint ロールが割り当てられるまでアクセスできなくなります。 |
| [Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control) | セキュリティ閲覧者ロールのすべてのアクセス許可 |
| [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/manage-admins) | セキュリティ閲覧者ロールのすべてのアクセス許可セキュリティ アラートの表示、調査、対応 |
| [Microsoft 365 サービス正常性](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/view-service-health) | Microsoft 365 サービスの正常性の表示 |

| Actions | Description |
| --- | --- |
| microsoft.azure.advancedThreatProtection/allEntities/allTasks | Azure 高度な脅威保護 のすべての側面を管理する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/cloudAppSecurity/allProperties/allTasks | Microsoft Defender for Cloud Apps ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.directory/identityProtection/allProperties/allTasks | Microsoft Entra ID 保護 でのすべてのリソースの作成と削除、および標準プロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/privilegedIdentityManagement/allProperties/read | Privileged Identity Management のすべてのリソースを読み取る |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/users/disable | ユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/enable | ユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.intune/allEntities/read | Microsoft Intune のすべてのリソースを読み取る |
| microsoft.office365.securityComplianceCenter/allEntities/allTasks | すべてのリソースの作成と削除、および Microsoft 365 セキュリティ/コンプライアンス センターでの標準プロパティの読み取りと更新 |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.windows.defenderAdvancedThreatProtection/allEntities/allTasks | エンドポイントに対して Microsoft Defender のすべての側面を管理する |

### セキュリティ閲覧者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 このロールを持つユーザーは、Microsoft Defender ポータル、Microsoft Entra ID 保護、Privileged Identity Management のすべての情報、Microsoft Entra サインイン レポートと監査ログを読み取る機能、Microsoft Purview ポータルなど、セキュリティ関連の機能に対するグローバル読み取り専用アクセス権を持ちます。 Office 365 のアクセス許可の詳細については、「[Microsoft Defender for Office 365 および Microsoft Purview コンプライアンスのロールとロール グループ](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/scc-permissions)」を参照してください。

| In | 実行できる |
| --- | --- |
| [Microsoft Defender ポータル](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender/microsoft-365-defender-portal) | Microsoft 365 サービス全体のセキュリティ関連ポリシーの表示セキュリティの脅威とアラートの表示レポートを表示する |
| [Microsoft Entra ID 保護](https://learn.microsoft.com/ja-jp/entra/id-protection/overview-identity-protection) | すべての Identity Protection レポートと [概要] を表示する |
| [特権アイデンティティ管理](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-configure) | Microsoft Entra Privileged Identity Management で表示されるすべての情報への読み取り専用アクセス権があります: Microsoft Entra ロールの割り当てとセキュリティ レビューのポリシーとレポート。Microsoft Entra Privileged Identity Management にサインアップしたり、変更を加えたり**することはできません**。 このロールのユーザーは、追加のロール (特権ロール管理者など) の資格がある場合、Privileged Identity Management ポータルまたは PowerShell からそれらのロールを有効化することができます。 |
| [Microsoft Purview ポータル](https://learn.microsoft.com/ja-jp/purview/purview-portal) | セキュリティ ポリシーの表示セキュリティの脅威の表示および調査レポートを表示する |
| [Microsoft Defender for Endpoint](https://learn.microsoft.com/ja-jp/microsoft-365/security/defender-endpoint/prepare-deployment) | アラートの表示と調査Microsoft Defender for Endpoint でロールベースのアクセス制御を有効にすると、セキュリティ閲覧者ロールなどの読み取り専用アクセス許可を持つユーザーは、Microsoft Defender for Endpoint ロールが割り当てられるまでアクセスできなくなります。 |
| [Intune](https://learn.microsoft.com/ja-jp/mem/intune/fundamentals/role-based-access-control) | ユーザー、デバイス、登録、構成、アプリケーション情報の表示。 Intune に変更を加えることはできません。 |
| [Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/manage-admins) | 読み取りアクセス許可があります。 |
| [Microsoft 365 サービス正常性](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/view-service-health) | Microsoft 365 サービスの正常性の表示 |

| Actions | Description |
| --- | --- |
| microsoft.agentRegistry/allEntities/allProperties/read | Microsoft Entra IDのエージェントレジストリのすべてのプロパティを読む |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/accessReviews/definitions/allProperties/read | Microsoft Entra ID ですべてのレビュー可能なリソースのアクセス レビューのすべてのプロパティを読み取る |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/bitlockerKeys/key/read | デバイス上の bitlocker メタデータとキーを読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/conditionalAccessPolicies/owners/read | 条件付きアクセス ポリシーの所有者を読み取る |
| microsoft.directory/conditionalAccessPolicies/policyAppliedTo/read | 条件付きアクセス ポリシーの "適用先" プロパティを読み取る |
| microsoft.directory/conditionalAccessPolicies/standard/read | ポリシーの条件付きアクセスを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationIdentitySynchronization/standard/read | マルチテナント組織のテナント間同期ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/templates/multiTenantOrganizationPartnerConfiguration/standard/read | マルチテナント組織のテナント間アクセス ポリシー テンプレートの基本プロパティを読み取る |
| microsoft.directory/deviceLocalCredentials/standard/read | Microsoft Entra 参加済みデバイスのバックアップされたローカル管理者アカウント資格情報のすべてのプロパティを読み取る (パスワードを除く) |
| microsoft.directory/domains/federationConfiguration/standard/read | ドメインのフェデレーション構成の標準プロパティを読み取る |
| microsoft.directory/entitlementManagement/allProperties/read | Microsoft Entra エンタイトルメント管理ですべてのプロパティを読み取る |
| microsoft.directory/identityProtection/allProperties/read | Microsoft Entra ID 保護 のすべてのリソースを読み取る |
| microsoft.directory/multiTenantOrganization/joinRequest/standard/read | マルチテナント組織の参加要求のプロパティを読み取る |
| microsoft.directory/multiTenantOrganization/standard/read | マルチテナント組織の基本プロパティを読み取る |
| microsoft.directory/multiTenantOrganization/tenants/organizationDetails/read | マルチテナント組織に参加しているテナントの組織の詳細を読み取る |
| microsoft.directory/multiTenantOrganization/tenants/standard/read | マルチテナント組織に参加しているテナントの基本プロパティを読み取る |
| microsoft.directory/namedLocations/standard/read | ネットワークの場所を定義するカスタム ルールの基本プロパティを読み取る |
| microsoft.directory/policies/owners/read | ポリシーの所有者を読み取る |
| microsoft.directory/policies/policyAppliedTo/read | policies.policyAppliedTo プロパティを読み取る |
| microsoft.directory/policies/standard/read | ポリシーの基本プロパティを読み取る |
| microsoft.directory/privilegedIdentityManagement/allProperties/read | Privileged Identity Management のすべてのリソースを読み取る |
| microsoft.directory/provisioningLogs/allProperties/read | プロビジョニング ログのすべてのプロパティを読み取る |
| microsoft.directory/signInReports/allProperties/read | サインイン情報レポートのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.networkAccess/allEntities/allProperties/read | Microsoft Entra ネットワーク アクセスのすべての側面を読み取る |
| microsoft.office365.protectionCenter/allEntities/standard/read | セキュリティおよびコンプライアンス センターのすべてリソースの標準プロパティを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/payload/allProperties/read | 攻撃シミュレーターで攻撃ペイロードのすべてのプロパティを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/reports/allProperties/read | 攻撃のシミュレーション、応答、関連付けられているトレーニングのレポートを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/simulation/allProperties/read | 攻撃シミュレーターで攻撃シミュレーション テンプレートのすべてのプロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### サービス サポート管理者

このロールを持つユーザーは、Microsoft for Azure および Microsoft 365 サービスでサポート要求を作成および管理し、 [Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-overview) と [Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/admin/admin-overview/admin-center-overview)でサービス ダッシュボードとメッセージ センターを表示できます。 詳しくは、「[Microsoft 365 管理センターでの管理者ロールについて](https://learn.microsoft.com/ja-jp/microsoft-365/admin/add-users/about-admin-roles)」をご覧ください。

Note

このロールは、以前 [は Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-overview) と [Microsoft 365 管理センター](https://learn.microsoft.com/ja-jp/microsoft-365/admin/admin-overview/admin-center-overview)でサービス管理者という名前でした。 Microsoft Graph API と Microsoft Graph PowerShell での既存の名前に合わせて、サービス サポート管理者という名前に変更されました。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### SharePoint 管理者

このロールが割り当てられたユーザーは、Microsoft Office SharePoint Online 内でグローバル アクセス許可を持ちます (このサービスが存在する場合)。また、すべての Microsoft 365 グループを作成および管理し、サポート チケットを管理し、サービス正常性を監視できます。 詳しくは、「[Microsoft 365 管理センターでの管理者ロールについて](https://learn.microsoft.com/ja-jp/microsoft-365/admin/add-users/about-admin-roles)」をご覧ください。

Note

Microsoft Graph API と Microsoft Graph PowerShell では、このロールは SharePoint サービス管理者という名前です。 [Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-overview) では、SharePoint 管理者という名前が付けられています。

Note

このロールでは、Microsoft Intune 用の Microsoft Graph API に対するスコープ付きのアクセス許可も付与されます。これにより、SharePoint リソースと OneDrive リソースに関連するポリシーの管理と構成を行うことができます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.backup/oneDriveForBusinessProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive 保護ポリシーを作成および管理する |
| microsoft.backup/oneDriveForBusinessRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での OneDrive の復元セッションを読み取りおよび構成する |
| microsoft.backup/restorePoints/sites/allProperties/allTasks | Microsoft 365バックアップで選択したSharePointサイトに関連するすべての復元ポイントを管理してください |
| microsoft.backup/restorePoints/userDrives/allProperties/allTasks | Microsoft 365バックアップで選択したOneDriveアカウントに関連するすべての復元ポイントを管理します |
| microsoft.backup/sharePointProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint 保護ポリシーを作成および管理する |
| microsoft.backup/sharePointRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での SharePoint の復元セッションを読み取りおよび構成する |
| microsoft.backup/siteProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint 保護ポリシーに追加されたサイトを管理する |
| microsoft.backup/siteRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint の復元セッションに追加されたサイトを管理する |
| microsoft.backup/userDriveProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive 保護ポリシーに追加されたアカウントを管理する |
| microsoft.backup/userDriveRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive の復元セッションに追加されたアカウントを管理する |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups.unified/basic/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.unified/create | ロールを割り当て可能なグループを除き、Microsoft 365 グループを作成する |
| microsoft.directory/groups.unified/delete | ロールを割り当て可能なグループを除き、Microsoft 365 グループを削除する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified/owners/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups.unified/restore | ロール割り当て可能なグループを除く、論理的に削除されたコンテナーから Microsoft 365 グループを復元する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.office365.migrations/allEntities/allProperties/allTasks | Microsoft 365 移行のすべての側面を管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.sharePoint/allEntities/allTasks | SharePoint ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### SharePoint 高度な管理管理者

以下のタスクを行う必要があるユーザーにSharePointのアドバンスドマネジメント管理者役割を割り当ててください:

- SharePoint Onlineのグローバル管理、サポートチケット処理、サービス健康管理を含むSharePoint管理者が利用可能なすべてのアクションを実行します
- ファイルやアイテムの内容にアクセスしずに、SharePointサイト内のファイル、フォルダ、ライブラリ、ドキュメント、リストの名前、パス、URLを閲覧できます
- SharePointサイト内のファイル、フォルダ、ライブラリ、ドキュメント、リストから権限を削除してください

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.backup/oneDriveForBusinessProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive 保護ポリシーを作成および管理する |
| microsoft.backup/oneDriveForBusinessRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での OneDrive の復元セッションを読み取りおよび構成する |
| microsoft.backup/restorePoints/sites/allProperties/allTasks | Microsoft 365バックアップで選択したSharePointサイトに関連するすべての復元ポイントを管理してください |
| microsoft.backup/restorePoints/userDrives/allProperties/allTasks | Microsoft 365バックアップで選択したOneDriveアカウントに関連するすべての復元ポイントを管理します |
| microsoft.backup/sharePointProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint 保護ポリシーを作成および管理する |
| microsoft.backup/sharePointRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での SharePoint の復元セッションを読み取りおよび構成する |
| microsoft.backup/siteProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint 保護ポリシーに追加されたサイトを管理する |
| microsoft.backup/siteRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint の復元セッションに追加されたサイトを管理する |
| microsoft.backup/userDriveProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive 保護ポリシーに追加されたアカウントを管理する |
| microsoft.backup/userDriveRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive の復元セッションに追加されたアカウントを管理する |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups.unified/basic/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.unified/create | ロールを割り当て可能なグループを除き、Microsoft 365 グループを作成する |
| microsoft.directory/groups.unified/delete | ロールを割り当て可能なグループを除き、Microsoft 365 グループを削除する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified/owners/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups.unified/restore | ロール割り当て可能なグループを除く、論理的に削除されたコンテナーから Microsoft 365 グループを復元する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.office365.migrations/allEntities/allProperties/allTasks | Microsoft 365 移行のすべての側面を管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.sharePoint/allEntities/allTasks | SharePoint ですべてのリソースの作成と削除、標準プロパティの読み取りと更新を行う |
| microsoft.office365.sharePointAdvancedManagement/allEntities/allProperties/allTasks | SharePoint 高度な管理のすべての側面を管理します |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### SharePoint バックアップ管理者

次のタスクを実行する必要があるユーザーに SharePoint Backup 管理者ロールを割り当てます。

- Microsoft 365 バックアップ for SharePoint および OneDrive のすべての側面を管理する
- SharePoint と OneDrive 間での詳細な復元を含むコンテンツのバックアップと復元
- SharePoint と OneDrive のバックアップ構成ポリシーを作成、編集、管理する
- SharePoint と OneDrive の復元操作を実行する

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.backup/oneDriveForBusinessProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive 保護ポリシーを作成および管理する |
| microsoft.backup/oneDriveForBusinessRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での OneDrive の復元セッションを読み取りおよび構成する |
| microsoft.backup/restorePoints/sites/allProperties/allTasks | Microsoft 365バックアップで選択したSharePointサイトに関連するすべての復元ポイントを管理してください |
| microsoft.backup/restorePoints/userDrives/allProperties/allTasks | Microsoft 365バックアップで選択したOneDriveアカウントに関連するすべての復元ポイントを管理します |
| microsoft.backup/sharePointProtectionPolicies/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint 保護ポリシーを作成および管理する |
| microsoft.backup/sharePointRestoreSessions/allProperties/allTasks | Microsoft 365 バックアップ での SharePoint の復元セッションを読み取りおよび構成する |
| microsoft.backup/siteProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint 保護ポリシーに追加されたサイトを管理する |
| microsoft.backup/siteRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で SharePoint の復元セッションに追加されたサイトを管理する |
| microsoft.backup/userDriveProtectionUnits/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive 保護ポリシーに追加されたアカウントを管理する |
| microsoft.backup/userDriveRestoreArtifacts/allProperties/allTasks | Microsoft 365 バックアップ で OneDrive の復元セッションに追加されたアカウントを管理する |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### SharePoint Embedded 管理者

次のタスクを実行する必要があるユーザーに SharePoint Embedded 管理者ロールを割り当てます。

- PowerShell、Microsoft Graph API、または SharePoint 管理センターを使用してすべてのタスクを実行する
- SharePoint Embedded コンテナーを管理、構成、および維持する
- SharePoint Embedded コンテナーを列挙および管理する
- SharePoint Embedded コンテナーのアクセス許可を列挙および管理する
- テナント内の SharePoint Embedded コンテナーのストレージを管理する
- SharePoint Embedded コンテナーにセキュリティおよびコンプライアンス ポリシーを割り当てる
- テナント内の SharePoint Embedded コンテナーにセキュリティおよびコンプライアンス ポリシーを適用する

[詳細情報](https://learn.microsoft.com/ja-jp/sharepoint/dev/embedded/concepts/admin-exp/cta)

| Actions | Description |
| --- | --- |
| microsoft.office365.fileStorageContainers/allEntities/allProperties/allTasks | SharePoint Embedded コンテナーのすべての側面を管理します |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Skype for Business 管理者

このロールが割り当てられたユーザーは、Microsoft Skype for Business 内でグローバル アクセス許可を持ちます (このサービスが存在する場合)。また、Microsoft Entra ID で Skype 固有のユーザー属性を管理します。 さらに、このロールはサポート チケットを管理し、サービスの正常性を監視することができ、Teams および Skype for Business 管理センターにアクセスできます。 アカウントには、Teams のライセンスが付与されている必要もあります。そうでないと、Teams の PowerShell コマンドレットを実行できません。 詳しくは、「[Skype for Business Online の管理](https://learn.microsoft.com/ja-jp/skypeforbusiness/skype-for-business-online)」と、「[Skype for Business アドオンのライセンス](https://learn.microsoft.com/ja-jp/skypeforbusiness/skype-for-business-and-microsoft-teams-add-on-licensing/skype-for-business-and-microsoft-teams-add-on-licensing)」の Teams のライセンス情報をご覧ください。

Note

Microsoft Graph API と Microsoft Graph PowerShell では、このロールは Lync サービス管理者という名前です。 [Azure portal](https://learn.microsoft.com/ja-jp/azure/azure-portal/azure-portal-overview) では、Skype for Business Administrator という名前が付けられています。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.skypeForBusiness/allEntities/allTasks | Skype for Business Online の全側面の管理 |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Teams 管理者

このロールのユーザーは、Microsoft Teams および Skype for Business の管理センターと、対応する PowerShell モジュールを使用して、Microsoft Teams のワークロードの全側面を管理できます。 これにはその他の領域の、テレフォニー、メッセージング、会議、およびチーム自体に関連するすべての管理ツールが含まれます。 このロールはさらに、すべての Microsoft 365 グループの作成および管理、サポート チケットの管理、サービスの正常性の監視を行うこともできます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/allowedCloudEndpoints/update | テナント間アクセスポリシーの許可されたクラウドエンドポイントを更新する |
| microsoft.directory/crossTenantAccessPolicy/default/crossCloudMeetings/update | 既定のクロステナントアクセスポリシーのクロスクラウドTeams会議設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read | 既定のテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/create | パートナーのテナント間アクセスポリシーを作成する |
| microsoft.directory/crossTenantAccessPolicy/partners/crossCloudMeetings/update | パートナーのテナント間アクセスポリシーのクロスクラウドTeams会議設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read | パートナーのテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/standard/read | テナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/externalUserProfiles/basic/update | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの基本プロパティを更新する |
| microsoft.directory/externalUserProfiles/delete | Teams の拡張ディレクトリ内の外部ユーザー プロファイルを削除する |
| microsoft.directory/externalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups.unified/basic/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.unified/create | ロールを割り当て可能なグループを除き、Microsoft 365 グループを作成する |
| microsoft.directory/groups.unified/delete | ロールを割り当て可能なグループを除き、Microsoft 365 グループを削除する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified/owners/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups.unified/restore | ロール割り当て可能なグループを除く、論理的に削除されたコンテナーから Microsoft 365 グループを復元する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.directory/pendingExternalUserProfiles/basic/update | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの基本プロパティを更新する |
| microsoft.directory/pendingExternalUserProfiles/create | Teams の拡張ディレクトリに外部ユーザー プロファイルを作成する |
| microsoft.directory/pendingExternalUserProfiles/delete | Teams の拡張ディレクトリ内の外部ユーザー プロファイルを削除する |
| microsoft.directory/pendingExternalUserProfiles/standard/read | Teams の拡張ディレクトリ内の外部ユーザー プロファイルの標準プロパティを読み取る |
| microsoft.directory/permissionGrantPolicies/standard/read | アクセス許可付与ポリシーの標準プロパティを読み取る |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.skypeForBusiness/allEntities/allTasks | Skype for Business Online の全側面の管理 |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/allEntities/allProperties/allTasks | Teams のすべてのリソースを管理する |

### Teams 通信管理者

このロールのユーザーは、音声とテレフォニーに関連する Microsoft Teams のワークロードの各側面を管理できます。 これには、電話番号の割り当て、音声と会議のポリシー、および通話分析ツールセットへのフル アクセスのための管理ツールが含まれます。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.skypeForBusiness/allEntities/allTasks | Skype for Business Online の全側面の管理 |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/callQuality/allProperties/read | 通話品質ダッシュボード (CQD) のすべてのデータを読み取る |
| microsoft.teams/meetings/allProperties/allTasks | 会議ポリシー、構成、会議ブリッジを含む、会議を管理する |
| microsoft.teams/voice/allProperties/allTasks | 通話ポリシーや電話番号のインベントリおよび割り当てを含む、音声を管理する |

### Teams 通信サポート エンジニア

このロールのユーザーは、Microsoft Teams と Skype for Business の管理センターでユーザー通話のトラブルシューティング ツールを使用して、Microsoft Teams と Skype for Business での通信に関する問題をトラブルシューティングできます。 このロールのユーザーは、関係するすべての参加者の完全な通話記録情報を表示できます。 このロールには、サポート チケットの表示、作成、管理のためのアクセス権がありません。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.skypeForBusiness/allEntities/allTasks | Skype for Business Online の全側面の管理 |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/callQuality/allProperties/read | 通話品質ダッシュボード (CQD) のすべてのデータを読み取る |

### Teams 通信サポート スペシャリスト

このロールのユーザーは、Microsoft Teams と Skype for Business の管理センターでユーザー通話のトラブルシューティング ツールを使用して、Microsoft Teams と Skype for Business での通信に関する問題をトラブルシューティングできます。 このロールのユーザーが表示できるのは、調査した特定ユーザーの通話における詳細のみです。 このロールには、サポート チケットの表示、作成、管理のためのアクセス権がありません。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.skypeForBusiness/allEntities/allTasks | Skype for Business Online の全側面の管理 |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/callQuality/standard/read | 通話品質ダッシュボード (CQD) の基本データを読み取る |

### Teams デバイス管理者

このロールを持つユーザーは、Teams 管理センターから [Teams 認定デバイス](https://www.microsoft.com/microsoft-teams/across-devices/devices) を管理できます。 このロールでは、すべてのデバイスを一目で見ることができ、デバイスの検索とフィルター処理を行えます。 ユーザーは、ログイン アカウント、デバイスの製造元やモデルなど、各デバイスの詳細を確認できます。 ユーザーは、デバイスの設定を変更したり、ソフトウェアのバージョンを更新したりできます。 このロールでは、Teams アクティビティとデバイスの通話品質を調べるアクセス許可は付与されません。

| Actions | Description |
| --- | --- |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/devices/standard/read | 構成ポリシーを含む、Teams 認定デバイスのすべての側面を管理する |

### Teams 外部コラボレーション管理者

次のタスクを実行する必要があるユーザーに Teams 外部コラボレーション管理者ロールを割り当てます。

- チャット、会議、通話での外部ユーザーとの組織の対話を制御する Teams 外部コラボレーション設定を管理します。
- ユーザーが外部組織と連携する方法を指定するドメイン エントリの作成、編集、削除など、外部ドメインを構成します。
- ユーザー レベルとグループ レベルの両方で、外部コラボレーションの許可リスト ポリシーとブロック リスト ポリシーを確立して管理します。
- セキュリティで保護され、準拠している外部コラボレーションのために組織と共同作業できる外部ドメインとユーザーを制御します。

| Actions | Description |
| --- | --- |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/policies/externalAccessPolicy/allTasks | 外部アクセスを管理するポリシーを作成および管理します。 |

### Teams 閲覧者

次のタスクを実行する必要があるユーザーに Teams 閲覧者ロールを割り当てます。

- Teams 管理センターで設定と管理情報を読み取りますが、管理操作は実行しません
- Microsoft Call Quality Dashboard (CQD) を読み取りますが、トラブルシューティング機能にはアクセスしません

このロールを持つユーザー **は、** 次のタスクを実行できません。

- Teams 管理を表示できない
- 会議にアクセスできない & ユーザーの通話の詳細
- 通知にアクセスできない & ルール管理
- 現場担当者展開管理にアクセスできない
- 高度なコラボレーション分析情報ダッシュボードにアクセスできない

[詳細情報](https://learn.microsoft.com/ja-jp/microsoftteams/using-admin-roles)

| Actions | Description |
| --- | --- |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/allEntities/allProperties/read | Microsoft Teams のすべてのプロパティを読み取る |

### Teams テレフォニー管理者

Teams テレフォニー管理者ロールは、以下のタスクを行う必要があるユーザーに割り当てます。

- 通話ポリシー、電話番号の管理と割り当て、音声アプリケーションを含め、音声とテレフォニーを管理する
- Teams 管理センターからの公衆交換電話網 (PSTN) 使用状況レポートへのアクセスのみ
- ユーザー プロファイル ページを表示する
- Azure と Microsoft 365 管理センターでのサポート チケットの作成および管理

[詳細情報](https://learn.microsoft.com/ja-jp/microsoftteams/using-admin-roles)

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.skypeForBusiness/allEntities/allTasks | Skype for Business Online の全側面の管理 |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.teams/callQuality/allProperties/read | 通話品質ダッシュボード (CQD) のすべてのデータを読み取る |
| microsoft.teams/voice/allProperties/allTasks | 通話ポリシーや電話番号のインベントリおよび割り当てを含む、音声を管理する |

### テナント作成者

次のタスクを行う必要があるユーザーに、テナント作成者ロールを割り当てます。

- ユーザー設定でテナント作成の切り替えがオフになっている場合でも、Microsoft Entra と Azure Active Directory B2C の両方のテナントを作成する

Note

テナント作成者には、作成した新しいテナントでグローバル管理者ロールが割り当てられます。

| Actions | Description |
| --- | --- |
| microsoft.directory/tenantManagement/tenants/create | Microsoft Entra ID で新しいテナントを作成する |

### テナント ガバナンス管理者

[Image: 特権ラベル アイコン。]

テナント ガバナンス管理者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- Microsoft Entra テナント ガバナンス サービスのすべての機能を管理する

| Actions | Description |
| --- | --- |
| microsoft.directory/crossTenantAccessPolicy/basic/update | テナント間アクセスポリシーの基本設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read | 既定のテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/partners/create | パートナーのテナント間アクセスポリシーを作成する |
| microsoft.directory/crossTenantAccessPolicy/partners/delete | パートナーのテナント間アクセスポリシーを削除する |
| microsoft.directory/crossTenantAccessPolicy/partners/standard/read | パートナーのテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/crossTenantAccessPolicy/standard/read | テナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/tenantGovernance/invitations/create | テナント ガバナンスの招待を作成する |
| microsoft.directory/tenantGovernance/invitations/delete | テナント ガバナンスの招待を削除する |
| microsoft.directory/tenantGovernance/invitations/standard/read | テナント ガバナンスの招待の読み取り |
| microsoft.directory/tenantGovernance/policyTemplates/allProperties/update | テナント ガバナンス ポリシー テンプレートを更新する |
| microsoft.directory/tenantGovernance/policyTemplates/create | テナント ガバナンス ポリシー テンプレートを作成する |
| microsoft.directory/tenantGovernance/policyTemplates/delete | テナント ガバナンス ポリシー テンプレートを削除する |
| microsoft.directory/tenantGovernance/policyTemplates/standard/read | テナント ガバナンス ポリシー テンプレートの読み取り |
| microsoft.directory/tenantGovernance/relatedTenants/refresh | Microsoft Entra テナント ガバナンス サービスによって検出された関連テナントに関するデータ更新をトリガーする |
| microsoft.directory/tenantGovernance/relatedTenants/standard/read | Microsoft Entra テナント ガバナンス サービスによって検出された関連テナントに関するデータを読み取ります |
| microsoft.directory/tenantGovernance/relationships/allProperties/update | ガバナンス関係の状態を更新する |
| microsoft.directory/tenantGovernance/relationships/create | テナント ガバナンス関係を作成する |
| microsoft.directory/tenantGovernance/relationships/standard/read | テナント ガバナンス関係の読み取り |
| microsoft.directory/tenantGovernance/requests/allProperties/update | テナント ガバナンス要求の状態を更新する |
| microsoft.directory/tenantGovernance/requests/create | テナント ガバナンス要求を作成する |
| microsoft.directory/tenantGovernance/requests/standard/read | テナント ガバナンス要求の読み取り |
| microsoft.directory/tenantGovernance/settings/allProperties/update | Microsoft Entra テナント ガバナンス設定の管理 |
| microsoft.directory/tenantGovernance/settings/standard/read | テナント ガバナンス設定の読み取り |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### テナント ガバナンス閲覧者

テナント ガバナンス閲覧者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- すべてのテナント ガバナンス データを読み取る

| Actions | Description |
| --- | --- |
| microsoft.directory/tenantGovernance/invitations/standard/read | テナント ガバナンスの招待の読み取り |
| microsoft.directory/tenantGovernance/policyTemplates/standard/read | テナント ガバナンス ポリシー テンプレートの読み取り |
| microsoft.directory/tenantGovernance/relatedTenants/standard/read | Microsoft Entra テナント ガバナンス サービスによって検出された関連テナントに関するデータを読み取ります |
| microsoft.directory/tenantGovernance/relationships/standard/read | テナント ガバナンス関係の読み取り |
| microsoft.directory/tenantGovernance/requests/standard/read | テナント ガバナンス要求の読み取り |
| microsoft.directory/tenantGovernance/settings/standard/read | テナント ガバナンス設定の読み取り |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### テナント ガバナンス関係管理者

テナント ガバナンス関係管理者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- 要求の受け入れ以外のテナント ガバナンス関係のすべての側面を管理する

| Actions | Description |
| --- | --- |
| microsoft.directory/tenantGovernance/invitations/standard/read | テナント ガバナンスの招待の読み取り |
| microsoft.directory/tenantGovernance/policyTemplates/allProperties/update | テナント ガバナンス ポリシー テンプレートを更新する |
| microsoft.directory/tenantGovernance/policyTemplates/create | テナント ガバナンス ポリシー テンプレートを作成する |
| microsoft.directory/tenantGovernance/policyTemplates/delete | テナント ガバナンス ポリシー テンプレートを削除する |
| microsoft.directory/tenantGovernance/policyTemplates/standard/read | テナント ガバナンス ポリシー テンプレートの読み取り |
| microsoft.directory/tenantGovernance/relatedTenants/standard/read | Microsoft Entra テナント ガバナンス サービスによって検出された関連テナントに関するデータを読み取ります |
| microsoft.directory/tenantGovernance/relationships/allProperties/update | ガバナンス関係の状態を更新する |
| microsoft.directory/tenantGovernance/relationships/create | テナント ガバナンス関係を作成する |
| microsoft.directory/tenantGovernance/relationships/standard/read | テナント ガバナンス関係の読み取り |
| microsoft.directory/tenantGovernance/requests/create | テナント ガバナンス要求を作成する |
| microsoft.directory/tenantGovernance/requests/standard/read | テナント ガバナンス要求の読み取り |
| microsoft.directory/tenantGovernance/settings/standard/read | テナント ガバナンス設定の読み取り |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### テナント ガバナンス関係閲覧者

テナント ガバナンス関係閲覧者ロールを、次のタスクを実行する必要があるユーザーに割り当てます。

- テナント ガバナンスリレーションシップと関連オブジェクトの読み取り

| Actions | Description |
| --- | --- |
| microsoft.directory/tenantGovernance/invitations/standard/read | テナント ガバナンスの招待の読み取り |
| microsoft.directory/tenantGovernance/policyTemplates/standard/read | テナント ガバナンス ポリシー テンプレートの読み取り |
| microsoft.directory/tenantGovernance/relationships/standard/read | テナント ガバナンス関係の読み取り |
| microsoft.directory/tenantGovernance/requests/standard/read | テナント ガバナンス要求の読み取り |
| microsoft.directory/tenantGovernance/settings/standard/read | テナント ガバナンス設定の読み取り |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 使用状況の概要レポート閲覧者

使用状況の概要レポート閲覧者ロールを、Microsoft 365 管理センターで次のタスクを実行する必要があるユーザーに割り当てます:

- 使用状況レポートと導入スコアを表示する
- 組織の分析情報を読み取るが、ユーザーの個人を特定できる情報 (PII) は読み取らない

このロールでは、ユーザーは以下の例外を除いて、組織レベルのデータのみを表示できます:

- メンバー ユーザーは、ユーザー管理データと設定を表示できます。
- このロールが割り当てられているゲスト ユーザーは、ユーザー管理データと設定を閲覧できません。

| Actions | Description |
| --- | --- |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.usageReports/allEntities/standard/read | テナントレベルの集計された Office 365 利用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ユーザー管理者

[Image: 特権ラベル アイコン。]

これは [特権ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions)です。 次の作業を行う必要があるユーザーに、ユーザー管理者ロールを割り当てます。

| Permission | 詳細 |
| --- | --- |
| ユーザーの作成 |  |
| すべてのユーザー (すべての管理者を含む) のほとんどのユーザー プロパティの更新 | [機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions) |
| 一部のユーザーの機密性の高いプロパティ (ユーザー プリンシパル名を含む) の更新 | [機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions) |
| 一部のユーザーの無効化または有効化 | [機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions) |
| 一部のユーザーの削除または復元 | [機密性の高いアクションを実行できるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions) |
| ユーザー ビューの作成と管理 |  |
| すべてのグループの作成と管理 |  |
| すべての管理者を含むすべてのユーザーのライセンスの割り当ておよび読み取り |  |
| パスワードのリセット | [パスワードをリセットできるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-reset-passwords) |
| 更新トークンを無効にする | [パスワードをリセットできるロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-reset-passwords) |
| (FIDO) デバイス キーを更新する |  |
| パスワードの有効期限ポリシーを更新する |  |
| Azure と Microsoft 365 管理センターでのサポート チケットの作成および管理 |  |
| サービス正常性の監視 |  |

このロールを持つユーザー **は、** 次の操作を実行できません。

- MFA を管理できません。
- [ロールを割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)のメンバーと所有者の資格情報を変更したり、MFA をリセットしたりすることはできません。
- 共有メールボックスを管理できません。
- パスワード リセット操作でのセキュリティの質問は変更できません。

Important

このロールを持つユーザーは、機密情報や個人情報または Microsoft Entra ID の内外の重要な構成にアクセスできるユーザーのパスワードを変更できます。 ユーザーのパスワードを変更することは、そのユーザーの ID およびアクセス許可を取得できることを意味します。 例えば次が挙げられます。

- 所有しているアプリの資格情報を管理できる、アプリケーションの登録とエンタープライズ アプリケーションの所有者。 これらのアプリには、Microsoft Entra ID およびユーザー管理者に付与されていない場所への特権アクセス許可がある場合があります。 ユーザー管理者は、このパスからアプリケーション所有者の ID を取得し、さらにそのアプリケーションの資格情報を更新して特権アプリケーションの ID を取得できる場合があります。
- 機密情報や個人情報または Azure の重要な構成にアクセスできる Azure サブスクリプション所有者。
- グループ メンバーシップを管理できるセキュリティ グループと Microsoft 365 グループの所有者。 これらのグループは、機密情報や個人情報または Microsoft Entra ID や別の場所の重要な構成へのアクセス権を付与される場合があります。
- Exchange Online、Microsoft Defender ポータル、Microsoft Purview ポータル、人事システムなど、Microsoft Entra ID 以外の他のサービスの管理者。
- 機密情報や個人情報にアクセスできる場合がある役員、弁護士、人事担当者のような非管理者。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.directory/accessReviews/definitions.applications/allProperties/allTasks | Microsoft Entra ID のアプリケーション ロールの割り当てのアクセス レビューを管理 |
| microsoft.directory/accessReviews/definitions.directoryRoles/allProperties/read | Microsoft Entra ロール割り当てに対するアクセス レビューのすべてのプロパティを読み取る |
| microsoft.directory/accessReviews/definitions.entitlementManagement/allProperties/allTasks | エンタイトルメント管理でアクセス パッケージの割り当てに対するアクセス レビューを管理する |
| microsoft.directory/accessReviews/definitions.groups/allProperties/read | ロールを割り当て可能なグループを含む、セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューのすべてのプロパティを読み取る。 |
| microsoft.directory/accessReviews/definitions.groups/allProperties/update | ロールを割り当て可能なグループを除く、セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューのすべてのプロパティを更新する。 |
| microsoft.directory/accessReviews/definitions.groups/create | セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューを作成する。 |
| microsoft.directory/accessReviews/definitions.groups/delete | セキュリティおよび Microsoft 365 グループ内のメンバーシップに対するアクセス レビューを削除する |
| microsoft.directory/contacts/basic/update | 連絡先の基本プロパティを更新する |
| microsoft.directory/contacts/create | 連絡先を作成する |
| microsoft.directory/contacts/delete | 連絡先を削除する |
| microsoft.directory/deletedItems.groups/restore | 論理的に削除されたグループを元の状態に復元する |
| microsoft.directory/deletedItems.users/restore | 論理的に削除されたユーザーを元の状態に復元する |
| microsoft.directory/entitlementManagement/allProperties/allTasks | Microsoft Entra エンタイトルメント管理でのリソースの作成と削除、すべてのプロパティの読み取りと更新を行う |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups/assignLicense | グループベースのライセンスのグループに製品ライセンスを割り当てる |
| microsoft.directory/groups/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups/classification/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの分類プロパティを更新する |
| microsoft.directory/groups/create | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを作成する |
| microsoft.directory/groups/delete | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループを削除する |
| microsoft.directory/groups/dynamicMembershipRule/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの動的メンバーシップ ルールを更新する |
| microsoft.directory/groups/groupType/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのグループの種類に影響を与えるプロパティを更新する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.directory/groups/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups/onPremWriteBack/update | Microsoft Entra Connect を使用してオンプレミスに書き戻す Microsoft Entra グループを更新する |
| microsoft.directory/groups/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups/reprocessLicenseAssignment | グループベースのライセンスのライセンス割り当てを再処理する |
| microsoft.directory/groups/restore | ソフト削除されたコンテナーからグループを復元する |
| microsoft.directory/groups/settings/update | グループの設定を更新する |
| microsoft.directory/groups/visibility/update | ロールを割り当て可能なグループを除き、セキュリティ グループと Microsoft 365 グループの可視性プロパティを更新する |
| microsoft.directory/oAuth2PermissionGrants/allProperties/allTasks | OAuth 2.0 アクセス許可の付与の作成と削除、およびすべてのプロパティの読み取りと更新[Image: 特権ラベル アイコン。] |
| microsoft.directory/onPremisesSynchronization/standard/read | 標準のオンプレミス ディレクトリ同期情報を読み取る |
| microsoft.directory/policies/standard/read | ポリシーの基本プロパティを読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignedTo/update | サービス プリンシパルのロールの割り当ての更新 |
| microsoft.directory/users/assignLicense | ユーザー ライセンスの管理 |
| microsoft.directory/users/basic/update | ユーザーの基本プロパティを更新する |
| microsoft.directory/users/convertExternalToInternalMemberUser | 外部ユーザーを内部ユーザーに変換する |
| microsoft.directory/users/create | ユーザーの追加[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/delete | ユーザーの削除[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/disable | ユーザーを無効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/enable | ユーザーを有効にする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/invalidateAllRefreshTokens | ユーザー更新トークンを無効にして強制的にサインアウトする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/inviteGuest | ゲスト ユーザーを招待する |
| microsoft.directory/users/lifeCycleInfo/read | employeeLeaveDateTime などのユーザーのライフサイクル情報を読み取る[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/manager/update | ユーザーのマネージャーを更新する |
| microsoft.directory/users/password/update | すべてのユーザーのパスワードをリセットする[Image: 特権ラベル アイコン。] |
| microsoft.directory/users/photo/update | ユーザーの写真を更新する |
| microsoft.directory/users/reprocessLicenseAssignment | ユーザー ライセンス割り当てを再処理する |
| microsoft.directory/users/restore | 削除されたユーザーを復元する |
| microsoft.directory/users/sponsors/update | ユーザーのスポンサーを更新する |
| microsoft.directory/users/usageLocation/update | ユーザーの利用場所を更新する |
| microsoft.directory/users/userPrincipalName/update | ユーザーのユーザー プリンシパル名を更新する[Image: 特権ラベル アイコン。] |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### ユーザー エクスペリエンス成功マネージャー

次のタスクを行う必要があるユーザーに User Experience Success Manager ロールを割り当てます。

- Microsoft 365 アプリとサービスの組織レベルの使用状況レポートを読み取るが、ユーザーの詳細は読み取らない
- 組織の製品フィードバック、Net Promoter Score (NPS) のアンケート結果、およびコミュニケーションとトレーニングの機会を特定するためのヘルプ記事ビューを表示する
- メッセージ センターの投稿とサービス正常性データを読み取る

[詳細情報](https://learn.microsoft.com/ja-jp/microsoft-365/admin/add-users/about-admin-roles)

| Actions | Description |
| --- | --- |
| microsoft.commerce.billing/purchases/standard/read | Microsoft 365 管理センターで購入サービスを読み取る。 |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.organizationalMessages/allEntities/allProperties/read | Microsoft 365 組織メッセージのすべての側面を読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.usageReports/allEntities/standard/read | テナントレベルの集計された Office 365 利用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### 仮想アクセス管理者

このロールのユーザーは、次のタスクを実行できます。

- Microsoft 365管理センターとTeams EHRコネクタで、BookingsでのVirtual Visitsのすべての側面を管理および構成する
- Teams 管理センター、Microsoft 365 管理センター、Fabric、Power BI で仮想アクセスの使用状況レポートを表示します
- Microsoft 365管理センターで機能と設定を表示しますが、設定を編集することはできません

仮想アクセスは、スタッフと出席者のオンラインおよびビデオの予定をスケジュールおよび管理するための簡単な方法です。 たとえば、使用状況レポートでは、予定の前にSMSテキストメッセージを送信すると、予定に表示されないユーザーの数をどのように減らすことができるか示すことができます。

| Actions | Description |
| --- | --- |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.virtualVisits/allEntities/allProperties/allTasks | 管理センターまたは Virtual Visits アプリから Virtual Visits の情報とメトリックを管理および共有する |

| Actions | Description |
| --- | --- |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.virtualVisits/allEntities/allProperties/allTasks | 管理センターまたは Virtual Visits アプリから Virtual Visits の情報とメトリックを管理および共有する |

### テナント管理者Viva Glint

次のタスクを実行する必要があるユーザーに Viva Glint テナント管理者ロールを割り当てます。

- Microsoft 365 管理センターで Viva Glint 設定を読み取って構成する
- Viva Glint サービス管理者の割り当てまたは削除
- Viva 機能アクセス管理ポリシーの作成と管理
- Viva Glint エクスペリエンスの表示と管理 (該当する場合)
- Azure サポート チケットを作成および管理する

詳細については、「 [Viva Glint の主要な役割](https://learn.microsoft.com/ja-jp/viva/glint/start/role-definitions) 」および [「Viva Glint テナントとサービス管理者の割り当て](https://learn.microsoft.com/ja-jp/viva/glint/setup/post-provisioning-next-steps)」を参照してください。

| Actions | Description |
| --- | --- |
| microsoft.azure.serviceHealth/allEntities/allTasks | Azure Service Health を読み取り、構成する |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.viva.glint/allEntities/allProperties/allTasks | Microsoft 365 管理センターですべての Microsoft Viva Glint 設定を管理および構成する |

### Viva Goals 管理者

次のタスクを行う必要があるユーザーに、Viva Goals 管理者ロールを割り当てます。

- Microsoft Viva Goals アプリケーションのあらゆる側面を管理および構成する
- Microsoft Viva Goals 管理者設定を構成する
- Microsoft Entra テナント情報を読み取る
- Microsoft 365 サービスの正常性を監視する
- Microsoft 365 サービス要求を作成および管理する

詳細については、「[Viva Goals のロールとアクセス許可](https://learn.microsoft.com/ja-jp/viva/goals/roles-permissions-in-viva-goals)」および「[Microsoft Viva Goals の概要](https://learn.microsoft.com/ja-jp/viva/goals/intro-to-ms-viva-goals)」を参照してください。

| Actions | Description |
| --- | --- |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.viva.goals/allEntities/allProperties/allTasks | Microsoft Viva Goals のすべての側面を管理する |

### Viva Pulse 管理者

次のタスクを行う必要があるユーザーに、Viva Pulse 管理者ロールを割り当てます:

- Viva Pulse のすべての設定を読み取って構成する
- Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る
- Azure Service Health を読み取り、構成する
- Azure サポート チケットを作成および管理する
- Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る
- Microsoft 365 管理センターで使用状況レポートを読み取る

詳細については、[「Microsoft 365 管理センターで Viva Pulse 管理者を割り当てる」](https://learn.microsoft.com/ja-jp/viva/pulse/setup-admin-access/assign-a-viva-pulse-admin-in-m365-admin-center) を参照してください。

| Actions | Description |
| --- | --- |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.viva.pulse/allEntities/allProperties/allTasks | Microsoft Viva Pulse のすべての側面を管理します |

### Windows 365 管理者

このロールのユーザーには Windows 365 リソースに対するグローバル アクセス許可があります (そのサービスが存在する場合)。 さらに、このロールはポリシーを関連付けるためにユーザーとデバイスを管理することができ、グループを作成および管理することもできます。

このロールはセキュリティ グループを作成および管理できますが、Microsoft 365 グループに対する管理者権限はありません。 つまり、管理者は、組織内の Microsoft 365 グループの所有者およびメンバーシップを更新することはできません。 ただし、自分で作成した Microsoft 365 グループを管理することはできます。これは、エンドユーザーの特権の一部です。 そのため、自分で作成したすべての Microsoft 365 グループ (セキュリティ グループではない) は、自分の 250 のクォータに対してカウントされます。

次のタスクを行う必要があるユーザーに、Windows 365 管理者ロールを割り当てます。

- Microsoft Intune で Windows 365 クラウド PC を管理する
- Microsoft Entra ID でデバイスを登録および管理する (ユーザーとポリシーの割り当てを含む)
- セキュリティ グループを作成および管理する (ロールを割り当て可能なグループを除く)
- Microsoft 365 管理センターで基本プロパティを表示する
- Microsoft 365 管理センターで使用状況レポートを読み取る
- Azure と Microsoft 365 管理センターでのサポート チケットの作成および管理

| Actions | Description |
| --- | --- |
| microsoft.azure.supportTickets/allEntities/allTasks | Azure サポート チケットを作成および管理する |
| microsoft.cloudPC/allEntities/allProperties/allTasks | Windows 365 のすべての側面を管理する |
| microsoft.directory/deletedItems.devices/delete | 復元できなくなったデバイスを完全に削除する |
| microsoft.directory/deletedItems.devices/restore | 論理的に削除されたデバイスを元の状態に復元する |
| microsoft.directory/deviceManagementPolicies/standard/read | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する標準のプロパティを読み取る |
| microsoft.directory/deviceRegistrationPolicy/standard/read | デバイス登録ポリシーの標準プロパティを読み取る |
| microsoft.directory/devices/basic/update | デバイスの基本プロパティを更新する |
| microsoft.directory/devices/create | デバイスを作成する (Microsoft Entra ID に登録する) |
| microsoft.directory/devices/delete | Microsoft Entra ID からデバイスを削除する |
| microsoft.directory/devices/disable | Microsoft Entra ID でデバイスを無効にする |
| microsoft.directory/devices/enable | Microsoft Entra ID でデバイスを有効にする |
| microsoft.directory/devices/extensionAttributeSet1/update | デバイスの extensionAttribute1 から extensionAttribute5 プロパティを更新する |
| microsoft.directory/devices/extensionAttributeSet2/update | デバイスの extensionAttribute6 から extensionAttribute10 プロパティを更新する |
| microsoft.directory/devices/extensionAttributeSet3/update | デバイスの extensionAttribute11 から extensionAttribute15 プロパティを更新する |
| microsoft.directory/devices/registeredOwners/update | デバイスの登録済み所有者を更新する |
| microsoft.directory/devices/registeredUsers/update | デバイスの登録済みユーザーを更新する |
| microsoft.directory/groups.security/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類のセキュリティ グループの割り当てられたラベル プロパティを更新する |
| microsoft.directory/groups.security/basic/update | ロールを割り当て可能なグループを除き、セキュリティ グループの基本プロパティを更新する |
| microsoft.directory/groups.security/classification/update | ロールを割り当て可能なグループを除き、セキュリティ グループの分類プロパティを更新する |
| microsoft.directory/groups.security/create | ロールを割り当て可能なグループを除き、セキュリティ グループを作成する |
| microsoft.directory/groups.security/delete | ロールを割り当て可能なグループを除き、セキュリティ グループを削除する |
| microsoft.directory/groups.security/dynamicMembershipRule/update | ロール割り当て可能なグループを除き、セキュリティ グループの動的メンバーシップ ルールを更新する |
| microsoft.directory/groups.security/members/update | ロールを割り当て可能なグループを除き、セキュリティ グループのメンバーを更新する |
| microsoft.directory/groups.security/owners/update | ロールを割り当て可能なグループを除き、セキュリティ グループの所有者を更新する |
| microsoft.directory/groups.security/visibility/update | ロールを割り当て可能なグループを除き、セキュリティ グループの可視性プロパティを更新する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |

### Windows Update デプロイ管理者

このロールの UUsers は、Windows Update for Business 展開サービスを使用して、Windows Update の展開のすべての側面を作成および管理できます。 このデプロイ サービスを使用すると、ユーザーは更新プログラムをいつ、どのようにデプロイするかの設定を定義でき、テナント内のデバイスのグループに提供する更新プログラムを指定できます。 それだけでなく、ユーザーは更新の進捗状況を監視することもできます。

| Actions | Description |
| --- | --- |
| microsoft.windows.updatesDeployments/allEntities/allProperties/allTasks | Windows Update Service のすべての側面の読み取りと構成を行う |

### Yammer 管理者

次のタスクを行う必要があるユーザーに、Yammer 管理者ロールを割り当てます。

- Yammer の全側面の管理
- Microsoft 365 グループ (ただし、ロール割り当て可能なグループ以外) の作成、管理、復元
- ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを確認する
- Microsoft 365 管理センターで使用状況レポートを読み取る
- Microsoft 365 管理センターでサービス要求を作成および管理する
- メッセージ センターのお知らせ (セキュリティのお知らせ以外) を確認する
- サービスの正常性を表示する

[詳細情報](https://learn.microsoft.com/ja-jp/viva/engage/eac-key-admin-roles-permissions)

| Actions | Description |
| --- | --- |
| microsoft.directory/groups.unified/assignedLabels/update | ロール割り当て可能なグループを除く、割り当てられたメンバーシップの種類の Microsoft 365 グループの割り当てられたラベル プロパティを更新します |
| microsoft.directory/groups.unified/basic/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの基本プロパティを更新する |
| microsoft.directory/groups.unified/create | ロールを割り当て可能なグループを除き、Microsoft 365 グループを作成する |
| microsoft.directory/groups.unified/delete | ロールを割り当て可能なグループを除き、Microsoft 365 グループを削除する |
| microsoft.directory/groups.unified/members/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループのメンバーを更新する |
| microsoft.directory/groups.unified/owners/update | ロールを割り当て可能なグループを除き、Microsoft 365 グループの所有者を更新する |
| microsoft.directory/groups.unified/restore | ロール割り当て可能なグループを除く、論理的に削除されたコンテナーから Microsoft 365 グループを復元する |
| microsoft.directory/groups/hiddenMembers/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの非表示メンバーを読み取る |
| microsoft.office365.messageCenter/messages/read | Microsoft 365 管理センターのメッセージ センターで、セキュリティ メッセージを除くメッセージを読み取る |
| microsoft.office365.network/performance/allProperties/read | Microsoft 365 管理センターで、すべてのネットワーク パフォーマンス プロパティを読み取る |
| microsoft.office365.serviceHealth/allEntities/allTasks | Microsoft 365 管理センターで Service Health を読み取り、構成する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.usageReports/allEntities/allProperties/read | Office 365 の使用状況レポートを読み取る |
| microsoft.office365.webPortal/allEntities/standard/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
| microsoft.office365.yammer/allEntities/allProperties/allTasks | Yammer の全側面の管理 |

### 非推奨のロール

次のロールは使用しないでください。 これらは非推奨となっており、将来的に Microsoft Entra ID から削除されます。

- アドホック ライセンス管理者
- デバイス参加
- デバイス マネージャー
- デバイス ユーザー
- メールで確認済みのユーザー作成者
- メールボックス管理者
- デバイスの社内参加

### ポータルに表示されないロール

PowerShellやMicrosoft Graph APIから返されるすべてのロールがMicrosoft Entraの役割インターフェースで見えるわけではありません。 それらの違いを次の表にまとめます。

| API 名 | Microsoft Entra 管理センター ポータル名 | Notes |
| --- | --- | --- |
| エージェントユーザー | エージェントのユーザーに暗的に割り当てられているため表示されません | NA |
| デバイス参加 | Deprecated | 非推奨になったロールのドキュメント |
| デバイス マネージャー | Deprecated | 非推奨になったロールのドキュメント |
| デバイス ユーザー | Deprecated | 非推奨になったロールのドキュメント |
| ディレクトリ同期アカウント | 使用するべきではないため、表示されません | ディレクトリ同期アカウントのドキュメント |
| ゲスト ユーザー | 使用できないため、表示されません | NA |
| Microsoft 365 サポート エンジニア | 使用するべきではないため、表示されません | Microsoft 365 サポート エンジニアのドキュメント |
| Modern Commerce 管理者 | 使用できないため、表示されません | Modern Commerce 管理者 |
| Partner Tier 1 サポート | 使用するべきではないため、表示されません | Partner Tier 1 サポートのドキュメント |
| Partner Tier 2 サポート | 使用するべきではないため、表示されません | Partner Tier 2 サポートのドキュメント |
| 制限されたゲストユーザー | 使用できないため、表示されません | NA |
| User | 使用できないため、表示されません | NA |
| デバイスの社内参加 | Deprecated | 非推奨になったロールのドキュメント |

#### Microsoft 365 サポート エンジニア

テンプレート ID: 00cf5c54-4693-4f59-a0ac-ab79ef0a974d

使用しないでください。一般的な使用は想定されていません。

| Actions | Description |
| --- | --- |
| microsoft.directory/applications/allProperties/read | すべての種類のアプリケーションのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/auditLogs/allProperties/read | カスタム セキュリティ属性監査ログを除く、監査ログのすべてのプロパティを読み取る |
| microsoft.directory/authorizationPolicy/standard/read | 認証ポリシーの標準プロパティを読み取る |
| microsoft.directory/conditionalAccessPolicies/standard/read | ポリシーの条件付きアクセスを読み取る |
| microsoft.directory/crossTenantAccessPolicy/default/standard/read | 既定のテナント間アクセスポリシーの基本プロパティを読み取る |
| microsoft.directory/deviceManagementPolicies/standard/read | モバイル デバイス管理とモバイル アプリ管理のポリシーに関する標準のプロパティを読み取る |
| microsoft.directory/deviceRegistrationPolicy/standard/read | デバイス登録ポリシーの標準プロパティを読み取る |
| microsoft.directory/devices/standard/read | デバイスで基本プロパティを読み取る |
| microsoft.directory/directoryRoles/allProperties/read | ディレクトリ ロールのすべてのプロパティを読み取る |
| microsoft.directory/directoryRoles/members/read | Microsoft Entra ロールのすべてのメンバーを読み取る |
| microsoft.directory/domains/allProperties/read | ドメインのすべてのプロパティの読み取る |
| microsoft.directory/domains/standard/read | ドメインで基本プロパティを読み取る |
| microsoft.directory/groups/allProperties/read | ロールを割り当て可能なグループを含む、セキュリティ グループと Microsoft 365 グループのすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/groupSettings/allProperties/read | グループ設定のすべてのプロパティを読み取る |
| microsoft.directory/groups/members/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループのメンバーを読み取る |
| microsoft.directory/groups/owners/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの所有者を読み取る |
| microsoft.directory/groups/standard/read | ロールを割り当て可能なグループを含め、セキュリティ グループと Microsoft 365 グループの標準プロパティを読み取る |
| microsoft.directory/organization/allProperties/read | 組織のすべてのプロパティを読み取る |
| microsoft.directory/policies/standard/read | ポリシーの基本プロパティを読み取る |
| microsoft.directory/securityRiskPolicy/standard/read | Microsoft Entra セキュリティの既定値、強力な認証、アカウント侵害を含むセキュリティ リスク ポリシーの基本プロパティを読み取ります |
| microsoft.directory/servicePrincipals/allProperties/read | servicePrincipals のすべてのプロパティ (特権プロパティを含む) を読み取る |
| microsoft.directory/servicePrincipals/appRoleAssignments/limitedRead | 特定のサービス プリンシパルに割り当てられたアプリケーション ロールを読み取りますが、サービス プリンシパルを列挙することはできません |
| microsoft.directory/servicePrincipals/standard/read | サービス プリンシパルの基本プロパティを読み取る |
| microsoft.directory/subscribedSkus/allProperties/read | 製品サブスクリプションのすべてのプロパティを読み取る |
| microsoft.directory/users/directReports/read | ユーザーの直属の部下を読み取る |
| microsoft.directory/users/licenseDetails/read | ユーザーのライセンスの詳細を読み取る |
| microsoft.directory/users/manager/read | ユーザーのマネージャーを読み取る |
| microsoft.directory/users/memberOf/read | ユーザーのグループ メンバーシップを読み取る |
| microsoft.directory/users/registeredDevices/read | ユーザーの登録済みデバイスを読み取る |
| microsoft.directory/users/standard/read | ユーザーの基本プロパティを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/payload/allProperties/read | 攻撃シミュレーターで攻撃ペイロードのすべてのプロパティを読み取る |
| microsoft.office365.protectionCenter/attackSimulator/reports/allProperties/read | 攻撃のシミュレーション、応答、関連付けられているトレーニングのレポートを読み取る |
| microsoft.teams/allEntities/allProperties/read | Microsoft Teams のすべてのプロパティを読み取る |

#### Modern Commerce 管理者

テンプレート ID: d24aef57-1500-4070-84db-2666f29cf966

使用しないでください。 このロールは、PowerShell または Microsoft Graph API からは返されません。 Commerce から自動的に割り当てられ、その他の用途には使用できません。

Modern Commerce 管理者ロールは、特定のユーザーに Microsoft 365 管理センターへのアクセス許可を付与し、 **ホーム**、 **課金**、 **サポート**の左側のナビゲーション エントリを表示します。 これらの領域で利用できるコンテンツは、ユーザーが自分または組織で購入した製品を管理するためにユーザーに割り当てられた [コマース固有のロール](https://learn.microsoft.com/ja-jp/azure/cost-management-billing/manage/understand-mca-roles) によって制御されます。 これには、請求書の支払いや、課金アカウントや課金プロファイルへのアクセスなどのタスクが含まれる場合があります。

Modern Commerce Administrator ロールを持つユーザーは、通常、他の Microsoft 購入システムで管理アクセス許可を持ちますが、管理センターへのアクセスに使用されるグローバル管理者ロールや課金管理者ロールはありません。

**Modern Commerce 管理者ロールはいつ割り当てられますか?**

- **Microsoft 365 管理センターでのセルフサービス購入** – セルフサービス購入により、ユーザーは新製品を自分で購入またはサインアップして、新製品を試す機会が得られます。 これらの製品は管理センターで管理されています。 セルフサービス購入を行うユーザーには、コマース システムでのロールと Modern Commerce 管理者ロールが割り当てられ、これにより、管理センターで購入を管理できるようになります。 管理者は、 [PowerShell](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/subscriptions/allowselfservicepurchase-powershell) を使用してセルフサービス購入 (Fabric、Power BI、Power Apps、Power Automate 用) をブロックできます。 詳細については、「[セルフサービスによる購入に関するよくあるご質問](https://learn.microsoft.com/ja-jp/microsoft-365/commerce/subscriptions/self-service-purchase-faq)」を参照してください。
- **Microsoft コマーシャル マーケットプレースからの購入** – セルフサービス購入と同様に、ユーザーが Microsoft AppSource または Azure Marketplace から製品またはサービスを購入すると、グローバル管理者または課金管理者ロールがない場合、Modern Commerce 管理者ロールが割り当てられます。 場合によっては、ユーザーがこれらの購入をブロックされる場合があります。 詳細については、[Microsoft コマーシャル マーケットプレース](https://learn.microsoft.com/ja-jp/azure/marketplace/marketplace-faq-publisher-guide#what-could-block-a-customer-from-completing-a-purchase-)に関するページを参照してください。
- **Microsoft からの提案** – 提案は、組織が Microsoft の製品やサービスを購入するための Microsoft からの正式なオファーです。 提案に同意するユーザーに Microsoft Entra ID のグローバル管理者または課金管理者ロールがない場合、提案を完了するためのコマース固有のロールと、管理センターにアクセスするための Modern Commerce 管理者ロールの両方が割り当てられます。 管理センターにアクセスすると、コマース固有のロールによって承認された機能のみを使用できます。
- **コマース固有のロール** – 一部のユーザーには商取引固有のロールが割り当てられます。 ユーザーは、全体管理者または課金管理者でない場合、管理センターにアクセスできるように、Modern Commerce 管理者ロールを取得します。

ユーザーから Modern Commerce 管理者ロールが割り当て解除されると、Microsoft 365 管理センターにアクセスできなくなります。 自分用または組織用の製品を管理している場合、それらの製品は管理できません。 これには、ライセンスの割り当て、支払い方法の変更、請求書の支払い、サブスクリプションを管理するためのその他のタスクが含まれます。

| Actions | Description |
| --- | --- |
| microsoft.commerce.billing/partners/read |  |
| microsoft.commerce.volumeLicenseServiceCenter/allEntities/allTasks | ボリューム ライセンス サービス センターのすべての側面を管理する |
| microsoft.office365.supportTickets/allEntities/allTasks | Microsoft 365 サービス要求を作成および管理する |
| microsoft.office365.webPortal/allEntities/basic/read | Microsoft 365 管理センターですべてのリソースの基本プロパティを読み取る |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/prerequisites"} -->
## Microsoft Entra ロール用に PowerShell または Graph エクスプローラーを使用するための前提条件 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/prerequisites
- Service: entra-id / role-based-access-control
- Article date: 2025-03-30
- Summary: Microsoft Entra ロール用に PowerShell または Graph エクスプローラーを使用するための前提条件。

PowerShell または Graph エクスプローラーを使用して Microsoft Entra ロールを管理する場合は、必要な前提条件が満たされている必要があります。 この記事では、さまざまな Microsoft Entra ロール機能の PowerShell と Graph Explorer の前提条件の一覧を示します。

### Microsoft Graph PowerShell

次を実行するための PowerShell コマンドを使用するには、

- ユーザー、グループ、またはデバイスを管理単位に追加する
- 管理単位に新しいグループを作成する

Microsoft Graph PowerShell SDK をインストールしている必要があります。

- [Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)

### グラフ エクスプローラー

[Microsoft Graph API](https://learn.microsoft.com/ja-jp/graph/overview) と [Graph エクスプローラー](https://learn.microsoft.com/ja-jp/graph/graph-explorer/graph-explorer-overview)を使用して Microsoft Entra ロールを管理するには、次の操作を行う必要があります。

1. Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。
2. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
3. **Entra ID**&gt;**Enterprise アプリ**をブラウズする。
4. アプリケーションの一覧で、 **Graph エクスプローラー**を見つけて選択します。
5. [ **アクセス許可] を選択します**。
6. **Graph エクスプローラーの [管理者の同意を付与する] を選択します**。

    [Image: [Graph エクスプローラーに管理者の同意を付与する] リンクを示すスクリーンショット。]
7. [Graph エクスプローラー ツール](https://aka.ms/ge)を使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/privileged-roles-permissions"} -->
## Microsoft Entra ID の特権ロールとアクセス許可 (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions
- Service: entra-id / role-based-access-control
- Article date: 2026-06-05
- Summary: Microsoft Entra ID の特権ロールとアクセス許可。

重要

特権ロールとアクセス許可に関するラベルは、現在プレビュー段階です。 ベータ版、プレビュー版、または一般提供としてまだリリースされていない Azure の機能に適用される法律条項については、「[Microsoft Azure プレビューの追加使用条件](https://azure.microsoft.com/support/legal/preview-supplemental-terms/)」を参照してください。

Microsoft Entra ID には、特権として識別されるロールとアクセス許可があります。 これらのロールとアクセス許可を使用し、ディレクトリ リソースの管理を他のユーザーに委任したり、資格情報ポリシー、認証ポリシー、認可ポリシーを変更したり、制限付きデータにアクセスしたりできます。 特権ロールの割り当ては、セキュリティで保護された意図した方法で使用しないと、特権の昇格につながる可能性があります。 この記事では、特権ロールとアクセス許可、および使用方法のベスト プラクティスについて説明します。

### 特権を持つロールとアクセス許可はどれですか?

特権を持つロールとアクセス許可の一覧については、「[Microsoft Entra の組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)」を参照してください。 Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、特権として識別されるロール、アクセス許可、ロールの割り当てを識別することもできます。

## [管理センター](#tab/admin-center)
Microsoft Entra 管理センターで、 **PRIVILEGED** ラベルを探します。

[Image: 特権ラベル アイコン。]

**[ロールと管理者]** ページで、特権ロールが **Privileged** 列で識別されます。 **割り当て** 列には、ロールの割り当ての数が一覧表示されます。 特権ロールをフィルター処理することもできます。

[Image: [特権] と [割り当て] の列を示す Microsoft Entra のロールと管理者のページのスクリーンショット。]

特権ロールのアクセス許可を表示すると、特権を持つアクセス許可を確認できます。 アクセス許可を既定のユーザーとして表示した場合、特権を持つアクセス許可を確認することはできません。

[Image: ロールの特権アクセス許可を示す Microsoft Entra のロールと管理者のページのスクリーンショット。]

カスタム ロールを作成すると、どのアクセス許可が特権を持ち、カスタム ロールが特権としてラベル付けされているかを確認できます。

[Image: 特権アクセス許可を持つカスタム ロールを示す [新しいカスタム ロール] ページのスクリーンショット。]

## [PowerShell](#tab/ms-powershell)
Microsoft Graph PowerShell で、`IsPrivileged` プロパティが `True` に設定されているかどうかを確認します。

特権ロールを一覧表示するには、[Get-MgBetaRoleManagementDirectoryRoleDefinition](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.identity.governance/get-mgbetarolemanagementdirectoryroledefinition) コマンドを使用します。

```powershell
Get-MgBetaRoleManagementDirectoryRoleDefinition -Filter "isPrivileged eq true" | Format-List
```

```Output
AllowedPrincipalTypes   :
Description             : Can create and manage all aspects of app registrations and enterprise apps.
DisplayName             : Application Administrator
Id                      : 9b895d92-2cd3-44c7-9d02-a6ac2d5ea5c3
InheritsPermissionsFrom : {88d8e3e3-8f55-4a1e-953a-9b9898b8876b}
IsBuiltIn               : True
IsEnabled               : True
IsPrivileged            : True
ResourceScopes          : {/}
RolePermissions         : {Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphUnifiedRolePermission}
TemplateId              : 9b895d92-2cd3-44c7-9d02-a6ac2d5ea5c3
Version                 : 1
AdditionalProperties    : {[assignmentMode, allowed], [categories, identity], [richDescription, Users in this role can
                          add, manage, and configureenterprise applications, app registrations and manage on-premises
                          like app proxy.], [inheritsPermissionsFrom@odata.context, https://graph.microsoft.com/beta/$m
                          etadata#roleManagement/directory/roleDefinitions('9b895d92-2cd3-44c7-9d02-a6ac2d5ea5c3')/inhe
                          ritsPermissionsFrom]}

AllowedPrincipalTypes   :
Description             : Can reset passwords for non-administrators and Helpdesk Administrators.
DisplayName             : Helpdesk Administrator
Id                      : 729827e3-9c14-49f7-bb1b-9608f156bbb8
InheritsPermissionsFrom : {88d8e3e3-8f55-4a1e-953a-9b9898b8876b}
IsBuiltIn               : True
IsEnabled               : True
IsPrivileged            : True
ResourceScopes          : {/}
RolePermissions         : {Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphUnifiedRolePermission}
TemplateId              : 729827e3-9c14-49f7-bb1b-9608f156bbb8
Version                 : 1
AdditionalProperties    : {[assignmentMode, allowed], [categories, identity], [richDescription, Users with this role
                          can change passwords, invalidate refresh tokens, manage service requests, and monitor
                          service health. Invalidating a refresh token forces the user to sign in again. Helpdesk
                          administrators can reset passwords and invalidate refresh tokens of other users who are
                          non-administrators or assigned the following roles only:
                          * Directory Readers
                          * Guest Inviter
                          * Helpdesk Administrator
                          * Message Center Reader
                          * Password Administrator
                          * Reports Reader], [inheritsPermissionsFrom@odata.context, https://graph.microsoft.com/beta/$
                          metadata#roleManagement/directory/roleDefinitions('729827e3-9c14-49f7-bb1b-9608f156bbb8')/inh
                          eritsPermissionsFrom]}

...
```

特権アクセス許可を一覧表示するには、[Get-MgBetaRoleManagementDirectoryResourceNamespaceResourceAction](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.identity.governance/get-mgbetarolemanagementdirectoryresourcenamespaceresourceaction) コマンドを使用します。

```powershell
Get-MgBetaRoleManagementDirectoryResourceNamespaceResourceAction -UnifiedRbacResourceNamespaceId "microsoft.directory" -Filter "isPrivileged eq true" | Format-List
```

```Output
ActionVerb                      : PATCH
AuthenticationContext           : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphAuthenticationContextClassReference
AuthenticationContextId         :
Description                     : Update all properties (including privileged properties) on single-directory applications
Id                              : microsoft.directory-applications.myOrganization-allProperties-update-patch
IsAuthenticationContextSettable :
IsPrivileged                    : True
Name                            : microsoft.directory/applications.myOrganization/allProperties/update
ResourceScope                   : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphUnifiedRbacResourceScope
ResourceScopeId                 :
AdditionalProperties            : {}

ActionVerb                      : PATCH
AuthenticationContext           : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphAuthenticationContextClassReference
AuthenticationContextId         :
Description                     : Update credentials on single-directory applications
Id                              : microsoft.directory-applications.myOrganization-credentials-update-patch
IsAuthenticationContextSettable :
IsPrivileged                    : True
Name                            : microsoft.directory/applications.myOrganization/credentials/update
ResourceScope                   : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphUnifiedRbacResourceScope
ResourceScopeId                 :
AdditionalProperties            : {}

...
```

特権ロールの割り当てを一覧表示するには、[Get-MgBetaRoleManagementDirectoryRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.identity.governance/get-mgbetarolemanagementdirectoryroleassignment) コマンドを使用します。

```powershell
Get-MgBetaRoleManagementDirectoryRoleAssignment -ExpandProperty "roleDefinition" -Filter "roleDefinition/isPrivileged eq true" | Format-List
```

```Output
AppScope                : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphAppScope
AppScopeId              :
Condition               :
DirectoryScope          : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphDirectoryObject
DirectoryScopeId        : /
Id                      : <Id>
Principal               : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphDirectoryObject
PrincipalId             : <PrincipalId>
PrincipalOrganizationId : <PrincipalOrganizationId>
ResourceScope           : /
RoleDefinition          : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphUnifiedRoleDefinition
RoleDefinitionId        : 62e90394-69f5-4237-9190-012177145e10
AdditionalProperties    : {}

AppScope                : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphAppScope
AppScopeId              :
Condition               :
DirectoryScope          : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphDirectoryObject
DirectoryScopeId        : /
Id                      : <Id>
Principal               : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphDirectoryObject
PrincipalId             : <PrincipalId>
PrincipalOrganizationId : <PrincipalOrganizationId>
ResourceScope           : /
RoleDefinition          : Microsoft.Graph.Beta.PowerShell.Models.MicrosoftGraphUnifiedRoleDefinition
RoleDefinitionId        : 62e90394-69f5-4237-9190-012177145e10
AdditionalProperties    : {}

...
```

## [Graph API](#tab/ms-graph)
Microsoft Graph API で、`isPrivileged` プロパティが `true` に設定されているかどうかを確認します。

特権ロールを一覧表示するには、[List roleDefinitions](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roledefinitions?view=graph-rest-beta&preserve-view=true&branch=pr-en-us-18827) API を使用します。

```http
GET https://graph.microsoft.com/beta/roleManagement/directory/roleDefinitions?$filter=isPrivileged eq true
```

**回答**

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#roleManagement/directory/roleDefinitions",
    "value": [
        {
            "id": "aaf43236-0c0d-4d5f-883a-6955382ac081",
            "description": "Can manage secrets for federation and encryption in the Identity Experience Framework (IEF).",
            "displayName": "B2C IEF Keyset Administrator",
            "isBuiltIn": true,
            "isEnabled": true,
            "isPrivileged": true,
            "resourceScopes": [
                "/"
            ],
            "templateId": "aaf43236-0c0d-4d5f-883a-6955382ac081",
            "version": "1",
            "rolePermissions": [
                {
                    "allowedResourceActions": [
                        "microsoft.directory/b2cTrustFrameworkKeySet/allProperties/allTasks"
                    ],
                    "condition": null
                }
            ],
            "inheritsPermissionsFrom@odata.context": "https://graph.microsoft.com/beta/$metadata#roleManagement/directory/roleDefinitions('aaf43236-0c0d-4d5f-883a-6955382ac081')/inheritsPermissionsFrom",
            "inheritsPermissionsFrom": [
                {
                    "id": "88d8e3e3-8f55-4a1e-953a-9b9898b8876b"
                }
            ]
        },
        {
            "id": "be2f45a1-457d-42af-a067-6ec1fa63bc45",
            "description": "Can configure identity providers for use in direct federation.",
            "displayName": "External Identity Provider Administrator",
            "isBuiltIn": true,
            "isEnabled": true,
            "isPrivileged": true,
            "resourceScopes": [
                "/"
            ],
            "templateId": "be2f45a1-457d-42af-a067-6ec1fa63bc45",
            "version": "1",
            "rolePermissions": [
                {
                    "allowedResourceActions": [
                        "microsoft.directory/domains/federation/update",
                        "microsoft.directory/identityProviders/allProperties/allTasks"
                    ],
                    "condition": null
                }
            ],
            "inheritsPermissionsFrom@odata.context": "https://graph.microsoft.com/beta/$metadata#roleManagement/directory/roleDefinitions('be2f45a1-457d-42af-a067-6ec1fa63bc45')/inheritsPermissionsFrom",
            "inheritsPermissionsFrom": [
                {
                    "id": "88d8e3e3-8f55-4a1e-953a-9b9898b8876b"
                }
            ]
        }
    ]
}
```

特権アクセス許可を一覧表示するには、[List resourceActions](https://learn.microsoft.com/ja-jp/graph/api/unifiedrbacresourcenamespace-list-resourceactions?view=graph-rest-beta&preserve-view=true&branch=pr-en-us-18827) API を使用します。

```http
GET https://graph.microsoft.com/beta/roleManagement/directory/resourceNamespaces/microsoft.directory/resourceActions?$filter=isPrivileged eq true
```

**回答**

```http
HTTP/1.1 200 OK
Content-Type: application/json

{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#roleManagement/directory/resourceNamespaces('microsoft.directory')/resourceActions",
    "value": [
        {
            "actionVerb": "PATCH",
            "description": "Update application credentials",
            "id": "microsoft.directory-applications-credentials-update-patch",
            "isPrivileged": true,
            "name": "microsoft.directory/applications/credentials/update",
            "resourceScopeId": null
        },
        {
            "actionVerb": null,
            "description": "Manage all aspects of authorization policy",
            "id": "microsoft.directory-authorizationPolicy-allProperties-allTasks",
            "isPrivileged": true,
            "name": "microsoft.directory/authorizationPolicy/allProperties/allTasks",
            "resourceScopeId": null
        }
    ]
}
```

特権ロールの割り当てを一覧表示するには、[List unifiedRoleAssignments](https://learn.microsoft.com/ja-jp/graph/api/rbacapplication-list-roleassignments?view=graph-rest-beta&preserve-view=true&branch=pr-en-us-18827) API を使用します。

```http
GET https://graph.microsoft.com/beta/roleManagement/directory/roleAssignments?$expand=roleDefinition&$filter=roleDefinition/isPrivileged eq true
```

**回答**

```http
HTTP/1.1 200 OK
Content-type: application/json

{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#roleManagement/directory/roleAssignments(roleDefinition())",
    "value": [
        {
            "id": "{id}",
            "principalId": "{principalId}",
            "principalOrganizationId": "{principalOrganizationId}",
            "resourceScope": "/",
            "directoryScopeId": "/",
            "roleDefinitionId": "b1be1c3e-b65d-4f19-8427-f6fa0d97feb9",
            "roleDefinition": {
                "id": "b1be1c3e-b65d-4f19-8427-f6fa0d97feb9",
                "description": "Can manage Conditional Access capabilities.",
                "displayName": "Conditional Access Administrator",
                "isBuiltIn": true,
                "isEnabled": true,
                "isPrivileged": true,
                "resourceScopes": [
                    "/"
                ],
                "templateId": "b1be1c3e-b65d-4f19-8427-f6fa0d97feb9",
                "version": "1",
                "rolePermissions": [
                    {
                        "allowedResourceActions": [
                            "microsoft.directory/namedLocations/create",
                            "microsoft.directory/namedLocations/delete",
                            "microsoft.directory/namedLocations/standard/read",
                            "microsoft.directory/namedLocations/basic/update",
                            "microsoft.directory/conditionalAccessPolicies/create",
                            "microsoft.directory/conditionalAccessPolicies/delete",
                            "microsoft.directory/conditionalAccessPolicies/standard/read",
                            "microsoft.directory/conditionalAccessPolicies/owners/read",
                            "microsoft.directory/conditionalAccessPolicies/policyAppliedTo/read",
                            "microsoft.directory/conditionalAccessPolicies/basic/update",
                            "microsoft.directory/conditionalAccessPolicies/owners/update",
                            "microsoft.directory/conditionalAccessPolicies/tenantDefault/update"
                        ],
                        "condition": null
                    }
                ]
            }
        },
        {
            "id": "{id}",
            "principalId": "{principalId}",
            "principalOrganizationId": "{principalOrganizationId}",
            "resourceScope": "/",
            "directoryScopeId": "/",
            "roleDefinitionId": "c4e39bd9-1100-46d3-8c65-fb160da0071f",
            "roleDefinition": {
                "id": "c4e39bd9-1100-46d3-8c65-fb160da0071f",
                "description": "Can access to view, set and reset authentication method information for any non-admin user.",
                "displayName": "Authentication Administrator",
                "isBuiltIn": true,
                "isEnabled": true,
                "isPrivileged": true,
                "resourceScopes": [
                    "/"
                ],
                "templateId": "c4e39bd9-1100-46d3-8c65-fb160da0071f",
                "version": "1",
                "rolePermissions": [
                    {
                        "allowedResourceActions": [
                            "microsoft.directory/users/authenticationMethods/create",
                            "microsoft.directory/users/authenticationMethods/delete",
                            "microsoft.directory/users/authenticationMethods/standard/restrictedRead",
                            "microsoft.directory/users/authenticationMethods/basic/update",
                            "microsoft.directory/deletedItems.users/restore",
                            "microsoft.directory/users/delete",
                            "microsoft.directory/users/disable",
                            "microsoft.directory/users/enable",
                            "microsoft.directory/users/invalidateAllRefreshTokens",
                            "microsoft.directory/users/restore",
                            "microsoft.directory/users/basic/update",
                            "microsoft.directory/users/manager/update",
                            "microsoft.directory/users/password/update",
                            "microsoft.directory/users/userPrincipalName/update",
                            "microsoft.azure.serviceHealth/allEntities/allTasks",
                            "microsoft.azure.supportTickets/allEntities/allTasks",
                            "microsoft.office365.serviceHealth/allEntities/allTasks",
                            "microsoft.office365.supportTickets/allEntities/allTasks",
                            "microsoft.office365.webPortal/allEntities/standard/read"
                        ],
                        "condition": null
                    }
                ]
            }
        }
    ]
}
```

---

### 特権ロールを使用するためのベスト プラクティス

特権ロールを使用するためのベスト プラクティスをいくつか次に示します。

- 最小特権の原則を適用する
- Privileged Identity Management を使用して Just-In-Time アクセスを許可する
- すべての管理者アカウントに対して多要素認証を有効にする
- 定期的なアクセス レビューを構成し、時間が経って不要になったアクセス許可を取り消す
- 全体管理者の数を 5 人未満に制限する
- 特権ロールの割り当ての数を 10 未満に制限する

詳細については、「[Microsoft Entra のロールのベスト プラクティス](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/best-practices)」を参照してください。

#### 特権付き中間層をコントロールプレーンの資産として分離する

特権ロールへのアクセスを管理または仲介できるシステムは、コントロール プレーンの一部であり、そのレベルで保護する必要があります。 たとえば、特権アクセス管理 (PAM) ソリューション、ジャンプ ホストとセッション ホスト、Automation Runbook、高い特権ロールを保持するサービス プリンシパルまたはアプリケーションなどがあります。

[Enterprise アクセス モデル](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/privileged-access-access-model)では、階層 0 がコントロール プレーンになるように拡張されます。これは、上位のプレーンの制御を下位のプレーンから取得できないように、管理プレーンとデータ/ワークロード プレーンから分離する必要があります。 下位レベルのシステムがコントロール プレーン資産を管理できる場合、そのシステムを侵害した攻撃者は特権をエスカレートできます。 これらの仲介者をコントロール プレーン (階層 0) 資産として扱い、同等に信頼されたコントロール プレーン システムからのみ管理します。

### 特権アクセス許可と保護されたアクション

特権アクセス許可と保護されたアクションは、さまざまな目的を持つセキュリティ関連の機能です。 **PRIVILEGED** ラベルを持つアクセス許可は、セキュリティで保護された意図した方法で使用されていない場合に特権の昇格につながる可能性があるアクセス許可を特定するのに役立ちます。 保護されたアクションは、多要素認証の要求など、セキュリティを強化するために条件付きアクセス ポリシーが割り当てられているロールのアクセス許可です。 条件付きアクセスの要件は、ユーザーが保護されたアクションを実行するときに適用されます。 保護されたアクションは現在プレビュー段階です。 詳細については、「[Microsoft Entra ID 内の保護されたアクションとは](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)」を参照してください。

| 機能 | 特権アクセス許可 | 保護されたアクション |
| --- | --- | --- |
| セキュリティで保護された方法で使用する必要があるアクセス許可を特定する | ✅ |  |
| アクションを実行するために追加のセキュリティを要求する |  | ✅ |

### 用語

Microsoft Entra ID の特権を持つロールとアクセス許可を理解するために、次の用語のいくつかを把握しておくことが役立ちます。

| 任期 | 定義 |
| --- | --- |
| アクション | セキュリティ プリンシパルがオブジェクト型に対して実行できるアクティビティ。 操作と呼ばれることもあります。 |
| アクセス許可 | セキュリティ プリンシパルがオブジェクト型に対して実行できるアクティビティを指定する定義。 アクセス許可には、1 つ以上のアクションが含まれます。 |
| 特権アクセス許可 | Microsoft Entra ID で、ディレクトリ リソースの管理を他のユーザーに委任したり、資格情報、認証、または認可ポリシーを変更したり、制限付きデータにアクセスしたりする目的で使用できるアクセス許可。 |
| 特権ロール | 1 つ以上の特権アクセス許可を持つ組み込みロールまたはカスタム ロール。 |
| 特権ロールの割り当て | 特権ロールを使用するロールの割り当て。 |
| 権限の昇格 | セキュリティ プリンシパルが、最初に別のロールを偽装することによって提供された割り当てロールよりも多くのアクセス許可を取得する場合。 |
| 保護されたアクション | セキュリティを強化するために条件付きアクセスが適用されたアクセス許可。 |

### ロールのアクセス許可を理解する方法

アクセス許可のスキーマは、Microsoft Graph の REST 形式にほぼ従っています。

`<namespace>/<entity>/<propertySet>/<action>`

次に例を示します。

`microsoft.directory/applications/credentials/update`

| アクセス許可の要素 | 説明 |
| --- | --- |
| 名前空間 | タスクを公開し、`microsoft` が先頭に付加された製品またはサービス。 たとえば、Microsoft Entra ID 内のすべてのタスクには、`microsoft.directory` 名前空間が使用されます。 |
| エンティティ | Microsoft Graph でサービスによって公開される論理機能またはコンポーネント。 たとえば、Microsoft Entra ID からはユーザーとグループ、OneNote からはメモ、Exchange からはメールボックスと予定表が公開されます。 名前空間内のすべてのエンティティを指定するための特別な `allEntities` キーワードがあります。 これは、製品全体へのアクセスを許可するロールでよく使用されます。 |
| プロパティセット | アクセスが許可されているエンティティの特定のプロパティまたは側面。 たとえば、`microsoft.directory/applications/authentication/read` は、Microsoft Entra ID でアプリケーション オブジェクトの応答 URL、ログアウト URL、および暗黙的なフロー プロパティを読み取る機能を付与します。<br>- `allProperties` は、特権プロパティを含む、エンティティのすべてのプロパティを指定します。<br>- `standard` は、一般的なプロパティを指定しますが、`read` アクションに関連する特権プロパティは除外されます。 たとえば、`microsoft.directory/user/standard/read` には、公開用の電話番号やメール アドレスなどの標準プロパティを読み取る機能は含まれますが、多要素認証に使用されるプライベートの二次的な電話番号やメール アドレスを読み取ることはできません。<br>- `basic` は、一般的なプロパティを指定しますが、`update` アクションに関連する特権プロパティは除外されます。 読み取ることができるプロパティのセットは、更新できるものと異なる場合があります。 そのため、それを反映するために `standard` と `basic` のキーワードが用意されています。 |
| アクション | 許可される操作。最も一般的なものは、作成、読み取り、更新、または削除 (CRUD) です。 上記のすべての能力 (作成、読み取り、更新、削除) を指定するための特別な `allTasks` キーワードがあります。 |

### 認証ロールの比較

認証関連ロールの機能との比較を次の表に示します。

| 役割 | ユーザーの認証方法の管理 | ユーザーごとの MFA の管理 | MFA 設定の管理 | 認証方法ポリシーの管理 | パスワード保護ポリシーの管理 | 機密性の高いプロパティの更新 | ユーザーの削除と復元 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| [認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-administrator) | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)に対してはい | いいえ | いいえ | いいえ | いいえ | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)に対してはい | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)に対してはい |
| [特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) | すべてのユーザーに対してはい | いいえ | いいえ | いいえ | いいえ | すべてのユーザーに対してはい | すべてのユーザーに対してはい |
| [認証ポリシー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#authentication-policy-administrator) | いいえ | はい | はい | はい | はい | いいえ | いいえ |
| [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) | いいえ | いいえ | いいえ | いいえ | いいえ | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)に対してはい | [一部のユーザー](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/privileged-roles-permissions#who-can-perform-sensitive-actions)に対してはい |

### パスワードをリセットできるのは誰ですか

次の表の列には、パスワードをリセットして、更新トークンを無効にできるロールが一覧表示されています。 行には、パスワードをリセットできる対象のロールが一覧表示されています。 例えば、パスワード管理者は、ディレクトリ閲覧者、ゲスト招待元、パスワード管理者、管理者ロールのないユーザーのパスワードをリセットできます。 ユーザーに他のロールが割り当てられている場合、パスワード管理者はそれらのパスワードをリセットできません。

次の表は、テナントのスコープで割り当てられたロールを対象にしています。 管理単位のスコープで割り当てられたロールについては、[さらに制限が適用されます](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal#roles-that-can-be-assigned-with-administrative-unit-scope)。

| パスワードをリセット可能なロール | パスワード管理者 | ヘルプデスク管理者 | 認証管理者 | [ユーザー管理者] | 特権認証管理者 | 全体管理者 |
| --- | --- | --- | --- | --- | --- | --- |
| 認証管理者 |  |  | ✅ |  | ✅ | ✅ |
| ディレクトリの読み手 | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| 全体管理者 |  |  |  |  | ✅ | ✅\* |
| グループ管理者 |  |  |  | ✅ | ✅ | ✅ |
| ゲスト招待者 | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| ヘルプデスク管理者 |  | ✅ |  | ✅ | ✅ | ✅ |
| メッセージ センター閲覧者 |  | ✅ | ✅ | ✅ | ✅ | ✅ |
| パスワード管理者 | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| 特権認証管理者 |  |  |  |  | ✅ | ✅ |
| 特権役割管理者 |  |  |  |  | ✅ | ✅ |
| レポート閲覧者 |  | ✅ | ✅ | ✅ | ✅ | ✅ |
| ユーザー(管理者ロールなし) | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| ユーザー(管理者ロールはないが、[ロールを割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)のメンバーまたは所有者) |  |  |  |  | ✅ | ✅ |
| [制限付き管理の管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management)をスコープとするロールを持つユーザー |  |  |  |  | ✅ | ✅ |
| [ユーザー管理者] |  |  |  | ✅ | ✅ | ✅ |
| ユーザー エクスペリエンス成功マネージャー |  | ✅ | ✅ | ✅ | ✅ | ✅ |
| 使用状況の概要レポート閲覧者 |  | ✅ | ✅ | ✅ | ✅ | ✅ |
| その他すべての組み込みロールおよびカスタムロール |  |  |  |  | ✅ | ✅ |

セキュリティ管理者、セキュリティ オペレーター、および Entra SOC ID レスポンダーは、管理者以外のユーザー アカウントに限定されており、特権アカウントに対してアクションを実行することはできません。

重要

[パートナー レベル 2 のサポート](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#partner-tier2-support) ロールでは、すべての非管理者および管理者 (全体管理者を含む) のパスワードをリセットし、更新トークンを無効にすることができます。 [パートナー レベル 1 のサポート](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#partner-tier1-support) ロールでは、非管理者のみに対してパスワードをリセットし、更新トークンを無効にすることができます。 これらのロールは非推奨であるため、使用しないでください。

パスワードのリセット機能には、[セルフサービスのパスワード リセット](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-howitworks)に必要な次の機密プロパティを更新する機能が含まれています。

- ビジネスフォン
- 携帯電話
- その他のメール

### 機密性の高いアクションを実行できるのは誰か

一部の管理者は、一部のユーザーに対して次の機密性の高いアクションを実行できます。 すべてのユーザーは、機密性の高いプロパティを読み取ることができます。

| 機密性の高いアクション | 機密性の高いプロパティ名 |
| --- | --- |
| ユーザーの無効化または有効化 | `accountEnabled` |
| 勤務先の電話番号の更新 | `businessPhones` |
| 携帯電話番号の更新 | `mobilePhone` |
| オンプレミスの不変 ID の更新 | `onPremisesImmutableId` |
| その他のメールアドレスの更新 | `otherMails` |
| パスワード プロファイルの更新 | `passwordProfile` |
| ユーザー プリンシパル名の更新 | `userPrincipalName` |
| ユーザーの削除または復元 | 該当なし |

次の表の列には、機密性の高いアクションを実行できるロールが一覧表示されています。 行には、機密性の高いアクションを実行できる対象のロールが一覧表示されています。

次の表は、テナントのスコープで割り当てられたロールを対象にしています。 管理単位のスコープで割り当てられたロールについては、[さらに制限が適用されます](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal#roles-that-can-be-assigned-with-administrative-unit-scope)。

| 機密性の高いアクションを実行できる役割 | 認証管理者 | [ユーザー管理者] | 特権認証管理者 | 全体管理者 |
| --- | --- | --- | --- | --- |
| 認証管理者 | ✅ |  | ✅ | ✅ |
| ディレクトリの読み手 | ✅ | ✅ | ✅ | ✅ |
| 全体管理者 |  |  | ✅ | ✅ |
| グループ管理者 |  | ✅ | ✅ | ✅ |
| ゲスト招待者 | ✅ | ✅ | ✅ | ✅ |
| ヘルプデスク管理者 |  | ✅ | ✅ | ✅ |
| メッセージ センター閲覧者 | ✅ | ✅ | ✅ | ✅ |
| パスワード管理者 | ✅ | ✅ | ✅ | ✅ |
| 特権認証管理者 |  |  | ✅ | ✅ |
| 特権役割管理者 |  |  | ✅ | ✅ |
| レポート閲覧者 | ✅ | ✅ | ✅ | ✅ |
| ユーザー(管理者ロールなし) | ✅ | ✅ | ✅ | ✅ |
| ユーザー(管理者ロールはないが、[ロールを割り当て可能なグループ](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/groups-concept)のメンバーまたは所有者) |  |  | ✅ | ✅ |
| [制限付き管理の管理単位](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/admin-units-restricted-management)をスコープとするロールを持つユーザー |  |  | ✅ | ✅ |
| [ユーザー管理者] |  | ✅ | ✅ | ✅ |
| ユーザー エクスペリエンス成功マネージャー | ✅ | ✅ | ✅ | ✅ |
| 使用状況の概要レポート閲覧者 | ✅ | ✅ | ✅ | ✅ |
| その他すべての組み込みロールおよびカスタムロール |  |  | ✅ | ✅ |

セキュリティ管理者、セキュリティ オペレーター、および Entra SOC ID レスポンダーは、管理者以外のユーザー アカウントに限定されており、特権アカウントに対してアクションを実行することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/protected-actions-add"} -->
## Microsoft Entra ID 内の保護されたアクションを追加、テスト、または削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-add
- Service: entra-id / role-based-access-control
- Article date: 2025-03-30
- Summary: Microsoft Entra ID 内の保護されたアクションを追加、テスト、または削除する方法について説明します。

Microsoft Entra ID の[保護されたアクション](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview)は、ユーザーがアクションを実行しようとしたときに適用される条件付きアクセス ポリシーが割り当てられているアクセス許可です。 この記事では、保護されたアクションを追加、テスト、または削除する方法について説明します。

注

保護されたアクションが適切に構成され、適用されるようにするには、次の順序でこれらの手順を実行する必要があります。 この順序に従わないと、 再認証要求が繰り返されるなど、予期しない動作が発生する可能性があります。

### [前提条件]

保護されたアクションを追加または削除するには、次の役割が必要です。

- Microsoft Entra ID P1 または P2 ライセンス
- [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) または [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) ロール

### 手順 1: 条件付きアクセス ポリシーを構成する

保護されたアクションでは条件付きアクセス認証コンテキストが使用されるため、認証コンテキストを構成し、それを条件付きアクセス ポリシーに追加する必要があります。 認証コンテキストを持つポリシーが既にある場合は、次のセクションに進むことができます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. [ **Entra ID**&gt;**Conditional Access**&gt;**Authentication context**&gt;**Authentication context**] を選択します。
3. [ **新しい認証コンテキスト** ] を選択して、[ **認証コンテキストの追加]** ウィンドウを開きます。
4. 名前と説明を入力し、[ **保存]** を選択します。

    [Image: 新しい認証コンテキストを追加するための [認証コンテキストの追加] ペインのスクリーンショット。]
5. [**ポリシー**]&gt;**新しいポリシー**を選択して新しいポリシーを作成します。
6. 新しいポリシーを作成し、認証コンテキストを選択します。

    詳細については、「 [条件付きアクセス: クラウド アプリ、アクション、認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)」を参照してください。

    [Image: 認証コンテキストを使用して新しいポリシーを作成するための [新しいポリシー] ページのスクリーンショット。]

### 手順 2: 保護されたアクションを追加する

保護アクションを追加するには、条件付きアクセス認証コンテキストを使用して、条件付きアクセス ポリシーを 1 つ以上のアクセス許可に割り当てます。

1. [ **Entra ID**&gt;**Conditional Access**&gt;**Policies** を選択します。
2. 保護されたアクションで使用する予定の条件付きアクセス ポリシーの状態が、[**オフ**] または [**レポートのみ**] ではなく **[オン]** に設定されていることを確認します。
3. **Entra ID**&gt;**ロールと管理者**&gt;**保護されたアクション**を選択します。

    [Image: ロールと管理者の [保護されたアクションの追加] ページのスクリーンショット。]
4. [ **保護されたアクションの追加]** を選択して、新しい保護されたアクションを追加します。

    **[保護されたアクションの追加]** が無効になっている場合は、条件付きアクセス管理者またはセキュリティ管理者ロールが割り当てられていることを確認します。 詳細については、「 保護されたアクションのトラブルシューティング」を参照してください。
5. 構成された条件付きアクセス認証コンテキストを選択します。
6. [ **アクセス許可の選択]** を選択し、条件付きアクセスで保護するアクセス許可を選択します。

    [Image: アクセス許可が選択されている [保護されたアクションの追加] ページのスクリーンショット。]
7. 「**追加**」を選択します。
8. 完了したら、[ **保存]** を選択します。

    保護されたアクションの一覧に、新しい保護されたアクションが表示されます

### 手順 3: 保護されたアクションをテストする

ユーザーが保護されたアクションを実行するときには、条件付きアクセス ポリシー要件を満たす必要があります。 このセクションでは、ポリシーを満たすためのプロンプトが表示されるユーザーのエクスペリエンスを示します。 この例では、ユーザーが条件付きアクセス ポリシーを更新する前に、FIDO セキュリティ キーを使用して認証する必要があります。

1. ポリシーを満たす必要があるユーザーとして [Microsoft Entra 管理センター](https://entra.microsoft.com) にサインインします。
2. [ **Entra ID**&gt;**Conditional Access**] を選択します。
3. 条件付きアクセス ポリシーを選択して表示してください。

    認証要件が満たされていないため、ポリシーの編集は無効になっています。 ページの下部には、次の注意事項があります。

    編集は、追加のアクセス要件によって保護されます。 再認証するには、ここをクリックします。

    [Image: 無効になっている条件付きアクセス ポリシーのスクリーンショット。再認証を示すメモが表示されています。]
4. [ **ここをクリックして再認証する**] を選択します。
5. ブラウザーが Microsoft Entra サインイン ページにリダイレクトされたら、認証要件を完了します。

    [Image: 再認証するサインイン ページのスクリーンショット。]

    認証要件を完了した後で、ポリシーを編集できます。
6. ポリシーを編集し、変更を保存します。

    [Image: 編集できる有効な条件付きアクセス ポリシーのスクリーンショット。]

### 保護されたアクションの削除

保護アクションを削除するには、アクセス許可から条件付きアクセス ポリシー要件の割り当てを解除します。

1. **Entra ID**&gt;**ロールと管理者**&gt;**保護されたアクション**を選択します。
2. 割り当てを解除するアクセス許可条件付きアクセス ポリシーを見つけて選択します。

    [Image: 削除するアクセス許可が選択されている [保護されたアクション] ページのスクリーンショット。]
3. ツール バーの [削除] を選択 **します**。

    保護されたアクションを削除した後で、アクセス許可に条件付きアクセス要件はありません。 新しい条件付きアクセス ポリシーをアクセス許可に割り当てることができます。

### Microsoft Graph

#### 保護されたアクションの追加

保護されたアクションは、認証コンテキスト値をアクセス許可に割り当てることによって追加されます。 テナントで使用できる認証コンテキスト値は、 [authenticationContextClassReference](https://learn.microsoft.com/ja-jp/graph/api/resources/authenticationcontextclassreference?branch=main) API を呼び出すことによって検出できます。

認証コンテキストは、 [unifiedRbacResourceAction](https://learn.microsoft.com/ja-jp/graph/api/resources/unifiedrbacresourceaction?branch=main) API ベータ エンドポイントを使用してアクセス許可に割り当てることができます。

```http
https://graph.microsoft.com/beta/roleManagement/directory/resourceNamespaces/microsoft.directory/resourceActions/
```

次の例は、`microsoft.directory/conditionalAccessPolicies/delete` アクセス許可に設定された認証コンテキスト ID を取得する方法を示しています。

```http
GET https://graph.microsoft.com/beta/roleManagement/directory/resourceNamespaces/microsoft.directory/resourceActions/microsoft.directory-conditionalAccessPolicies-delete-delete?$select=authenticationContextId,isAuthenticationContextSettable
```

プロパティ `isAuthenticationContextSettable` が true に設定されたリソース アクションは、認証コンテキストをサポートします。 プロパティ `authenticationContextId` の値を持つリソース アクションは、アクションに割り当てられている認証コンテキスト ID を持っています。

`isAuthenticationContextSettable` プロパティと `authenticationContextId` プロパティを表示するには、リソース アクション API への要求を行うときに、それらのプロパティを select ステートメントに含める必要があります。

### 保護されたアクションのトラブルシューティング

#### 症状 - 認証コンテキスト値を選択できない

条件付きアクセス認証コンテキストを選択しようとしても、選択できる値がありません。

[Image: 選択する認証コンテキストがない [保護されたアクションの追加] ページのスクリーンショット。]

**原因**

テナントで条件付きアクセス認証コンテキスト値が有効になっていません。

**解決**

新しい認証コンテキストを追加して、テナントの認証コンテキストを有効にします。 [ **アプリに発行]** がオンになっていることを確認して、値を選択できるようにします。 詳細については、「 [認証コンテキスト」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)を参照してください。

#### 症状 - ポリシーがトリガーされない

場合によっては、保護されたアクションが追加された後で、予期されたようにユーザーにプロンプトが表示されないことがあります。 たとえば、ポリシーで多要素認証が必要な場合に、ユーザーにサインイン プロンプトが表示されないことがあります。

**原因 1**

ユーザーは、保護されたアクションに使用される条件付きアクセス ポリシーに割り当てられていません。

**解決策 1**

条件付きアクセス [What If](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool) ツールを使用して、ユーザーにポリシーが割り当てられているかどうかを確認します。 ツールを使用するときには、ユーザーと、保護されたアクションで使用された認証コンテキストを選択します。 [What If]\(What If\)を選択し、必要なポリシーが **適用されるポリシー** の一覧に表示されていることを確認します。 ポリシーが適用されていない場合は、ポリシー ユーザーの割り当て条件を確認し、ユーザーを追加します。

**原因 2**

ユーザーは以前にポリシーを満たしていました。 たとえば、同じセッションで以前に多要素認証を完了していました。

**解決策 2**

トラブルシューティングを行うには、 [Microsoft Entra サインイン イベント](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access) を確認してください。 サインイン イベントには、セッションに関する詳細情報 (例: ユーザーが既に多要素認証を完了したかどうか) が含まれます。 サインイン ログを使用してトラブルシューティングを行うときには、ポリシーの詳細ページを確認して、認証コンテキストが要求されたことを確認することもできます。

#### 現象 - ポリシーが満たされない

条件付きアクセス ポリシーの要件を実行しようとすると、ポリシーが満たされず、再認証を要求され続けます。

**原因**

条件付きアクセス ポリシーが作成されていないか、ポリシーの状態が **オフ** または **レポート専用**です。

**解決**

条件付きアクセス ポリシーが存在しない場合は作成し、状態を **[オン]** に設定します。

アクションが保護され、再認証の要求が繰り返されるために [条件付きアクセス] ページにアクセスできない場合は、次のリンクを使用して [条件付きアクセス] ページを開きます。

- https://aka.ms/MSALProtectedActions

#### 症状 - 保護されたアクションを追加するためのアクセス権がない

サインインしているときに、保護されたアクションを追加または削除するためのアクセス許可がありません。

**原因**

保護されたアクションを管理するためのアクセス許可がありません。

**解決**

[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)または[セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロールが割り当てられていることを確認します。

#### 症状 - 保護されたアクションを実行するために PowerShell を使用しているときにエラーが返された

保護されたアクションを実行するために PowerShell を使用しているときに、エラーが返され、条件付きアクセス ポリシーを満たすためのプロンプトがありません。

**原因**

Microsoft Graph PowerShell は、ポリシー プロンプトを許可するために必要なステップアップ認証をサポートしています。 Azure PowerShell は、ステップアップ認証ではサポートされていません。

**解決**

Microsoft Graph PowerShell を使用していることを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/role-based-access-control/protected-actions-overview"} -->
## Microsoft Entra ID の保護されたアクションとは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-overview
- Service: entra-id / role-based-access-control
- Article date: 2025-11-03
- Summary: Microsoft Entra ID で保護されたアクションについて説明します。

Microsoft Entra ID の保護されたアクションは、[条件付きアクセス ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)に割り当てられている権限です。 ユーザーが保護されたアクションを実行しようとすると、まず、必要なアクセス許可に割り当てられている条件付きアクセス ポリシーを満たす必要があります。 たとえば、管理者が条件付きアクセス ポリシーを更新できるようにするには、最初にフィッシング詐欺に強い MFA [ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths#built-in-authentication-strengths) 満たす必要があります。

この記事では、保護されたアクションの概要と、それらの使用を開始する方法について説明します。

### 保護されたアクションを使用する理由

保護のレイヤーを追加する場合は、保護されたアクションを使用します。 保護されたアクションは、使用されているロールやユーザーにアクセス許可が与えられた方法に関係なく、強力な条件付きアクセス ポリシー保護を必要とするアクセス許可に適用できます。 ポリシーの適用は、ユーザーがユーザーのサインインまたはルールのアクティブ化中ではなく、保護されたアクションを実行しようとしたときに行われるため、ユーザーは必要な場合にのみプロンプトが表示されます。

### 保護されたアクションで通常使用されるポリシーは何ですか?

すべてのアカウント (特に特権ロールを持つアカウント) で多要素認証を使用することをお勧めします。 保護されたアクションを使用して、追加のセキュリティを要求できます。 一般的な強力な条件付きアクセス ポリシーを次に示します。

- [パスワードレス MFA](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-authentication-strengths#built-in-authentication-strengths) やフィッシングに強い MFA など、より強力な MFA 認証の強度
- 特権アクセスワークステーションは、条件付きアクセスポリシー[とデバイスフィルター](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-condition-filters-for-devices)を使用して管理されます。
- 条件付きアクセス [サインイン頻度セッション制御を使用して、セッションタイムアウトを短縮](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-session-lifetime#user-sign-in-frequency)。

### 保護されたアクションで使用できるアクセス許可は何ですか？

条件付きアクセス ポリシーは、制限されたアクセス許可のセットに適用できます。 保護されたアクションは、次の領域で使用できます。

- 条件付きアクセス ポリシーの管理
- テナント間アクセス設定の管理
- 一部のディレクトリ オブジェクトのハード削除
- ネットワークの場所を定義するカスタム ルール
- 保護されたアクション管理

アクセス許可の初期セットを次に示します。

| 許可 | 説明 |
| --- | --- |
| microsoft.directory/conditionalAccessPolicies/basic/update | 条件付きアクセス ポリシーの基本プロパティを更新する |
| microsoft.directory/conditionalAccessPolicies/create | 条件付きアクセス ポリシーを作成する |
| microsoft.directory/conditionalAccessPolicies/delete (条件付きアクセス ポリシー削除) | 条件付きアクセス ポリシーを削除する |
| microsoft.directory/conditionalAccessPolicies/basic/update | 条件付きアクセス ポリシーの基本プロパティを更新する |
| microsoft.directory/conditionalAccessPolicies/create | 条件付きアクセス ポリシーを作成する |
| microsoft.directory/conditionalAccessPolicies/delete (条件付きアクセス ポリシー削除) | 条件付きアクセス ポリシーを削除する |
| microsoft.directory/crossTenantAccessPolicy/allowedCloudEndpoints/update | テナント間アクセス ポリシーの許可されたクラウド エンドポイントを更新する |
| microsoft.directory/crossTenantAccessPolicy/default/b2bCollaboration/update | 既定のテナント間アクセス ポリシーの Microsoft Entra B2B コラボレーション設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/b2bDirectConnect/update | 既定のテナント間アクセス ポリシーの Microsoft Entra B2B 直接接続設定を更新する |
| microsoft.directory/crossTenantAccessPolicy/default/crossCloudMeetings/update | 既定のテナント間アクセス ポリシーのクラウド間 Teams 会議設定を更新します。 |
| microsoft.directory/crossTenantAccessPolicy/default/tenantRestrictions/update | 既定のテナント間アクセス ポリシーのテナント制限を更新します。 |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bCollaboration/update | パートナーのテナント間アクセス ポリシーの Microsoft Entra B2B コラボレーション設定を更新します。 |
| microsoft.directory/crossTenantAccessPolicy/partners/b2bDirectConnect/update | パートナー向けのテナント間アクセス ポリシーの Microsoft Entra B2B 直接接続設定を更新します。 |
| microsoft.directory/crossTenantAccessPolicy/partners/create | パートナーのテナント間アクセス ポリシーを作成します。 |
| microsoft.directory/crossTenantAccessPolicy/partners/crossCloudMeetings/update | パートナーのテナント間アクセス ポリシーのクラウド間 Teams 会議設定を更新します。 |
| microsoft.directory/crossTenantAccessPolicy/partners/delete | パートナーのテナント間アクセス ポリシーを削除します。 |
| microsoft.directory/crossTenantAccessPolicy/partners/tenantRestrictions/update | パートナーのテナント間アクセス ポリシーのテナント制限を更新します。 |
| microsoft.directory/deletedItems/delete | 復元できなくなったオブジェクトを完全に削除する |
| microsoft.directory/namedLocations/basic/update | ネットワークの場所を定義するカスタム 規則の基本プロパティを更新する |
| microsoft.directory/namedLocations/create | ネットワークの場所を定義するカスタム ルールを作成する |
| microsoft.directory/namedLocations/delete | ネットワークの場所を定義するカスタム ルールを削除する |
| microsoft.directory/resourceNamespaces/resourceActions/authenticationContext/update | Microsoft 365 ロールベースのアクセス制御 (RBAC) リソース アクションの条件付きアクセス認証コンテキストを更新する |

### ディレクトリ オブジェクトの削除

Microsoft Entra ID では、ほとんどのディレクトリ オブジェクトに対して、論理的な削除とハード削除という 2 種類の削除がサポートされています。 ディレクトリ オブジェクトが論理的に削除されると、そのオブジェクトのプロパティ値とリレーションシップはごみ箱に 30 日間保持されます。 論理的に削除されたオブジェクトは、同じ ID とすべてのプロパティ値とリレーションシップをそのまま使用して復元できます。 論理的に削除されたオブジェクトがハード削除されると、オブジェクトは完全に削除され、同じオブジェクト ID で再作成することはできません。

ごみ箱からソフト削除されたディレクトリ オブジェクトが偶発的または悪意のあるハード削除や永続的なデータ損失から保護されるようにするため、次のアクセス許可に保護アクションを追加できます。 この削除は、ユーザー、Microsoft 365 グループ、クラウド セキュリティ グループ、アプリケーションに適用されます。

- microsoft.directory/deletedItems/delete

### 保護されたアクションと Privileged Identity Management ロールのアクティブ化の比較

[Privileged Identity Management ロールのアクティブ化](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-how-to-change-default-settings) には、条件付きアクセス ポリシーを割り当てることもできます。 この機能を使用すると、ユーザーがロールをアクティブ化した場合にのみポリシーを適用でき、最も包括的な保護が提供されます。 保護されたアクションは、条件付きアクセス ポリシーが割り当てられているアクセス許可を必要とするアクションをユーザーが実行した場合にのみ適用されます。 保護されたアクションを使用すると、ユーザー ロールに関係なく、影響の大きいアクセス許可を保護できます。 Privileged Identity Management ロールのアクティブ化と保護されたアクションを一緒に使用して、より強力なカバレッジを実現できます。

### 保護されたアクションを使用する手順

手記

保護されたアクションが適切に構成され、適用されるようにするには、次の順序でこれらの手順を実行する必要があります。 この順序に従わないと、[を再認証する要求を繰り返し受け取る](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-add#symptom---policy-is-never-satisfied)など、予期しない動作が発生する可能性があります。

1. **アクセス許可を調べる**

    [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) または [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator) のロールが割り当てられていることを確認します。 そうでない場合は、管理者に確認して適切なロールを割り当てます。
2. 条件付きアクセス ポリシー **を構成する**

    条件付きアクセス認証コンテキストと、関連付けられている条件付きアクセス ポリシーを構成します。 保護されたアクションは認証コンテキストを使用します。これにより、Microsoft Entra のアクセス許可など、サービス内のきめ細かなリソースに対するポリシーの適用が可能になります。 最初に、パスワードなしの MFA を要求し、緊急アカウントを除外することをお勧めします。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-add#step-1-configure-conditional-access-policy)
3. **保護されたアクション** を追加する

    選択したアクセス許可に条件付きアクセス認証コンテキスト値を割り当てることで、保護されたアクションを追加します。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-add#step-2-add-protected-actions)
4. **保護されたアクション** をテストする

    ユーザーとしてサインインし、保護されたアクションを実行してユーザー エクスペリエンスをテストします。 条件付きアクセス ポリシーの要件を満たすように求められます。 たとえば、ポリシーで多要素認証が必要な場合は、サインイン ページにリダイレクトされ、強力な認証を求められます。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/protected-actions-add#step-3-test-protected-actions)

### 保護されたアクションとアプリケーションはどうなりますか?

アプリケーションまたはサービスが保護アクションを実行しようとすると、必要な条件付きアクセス ポリシーを処理できる必要があります。 場合によっては、ユーザーが介入してポリシーを満たす必要がある場合があります。 たとえば、多要素認証を完了するために必要な場合があります。 次のアプリケーションでは、保護されたアクションのステップアップ認証がサポートされています。

- [Microsoft Entra 管理センターの操作における管理者向けのエクスペリエンス](https://entra.microsoft.com)
- [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview?branch=main)
- [グラフ エクスプローラー](https://learn.microsoft.com/ja-jp/graph/graph-explorer/graph-explorer-overview?branch=main)

既知の制限事項と予想される制限事項がいくつかあります。 次のアプリケーションは、保護されたアクションを実行しようとすると失敗します。

- [Azure PowerShell](https://learn.microsoft.com/ja-jp/powershell/azure/what-is-azure-powershell?branch=main)
- 新しい [使用条件](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use) ページを作成するか、Microsoft Entra 管理センターで [カスタム コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/controls) を作成します。 新しい利用規約ページまたはカスタム コントロールは条件付きアクセスに登録されるため、条件付きアクセスによる保護されたアクションの作成、更新、削除の対象となります。 条件付きアクセスの作成、更新、および削除アクションからポリシー要件を一時的に削除すると、新しい利用規約ページまたはカスタム コントロールを作成できます。

組織で Microsoft Graph API を呼び出して保護されたアクションを実行するアプリケーションを開発している場合は、ステップアップ認証を使用して要求チャレンジを処理する方法のコード サンプルを確認する必要があります。 詳細については、「条件付きアクセス認証コンテキストの [に関する開発者ガイド](https://learn.microsoft.com/ja-jp/entra/identity-platform/developer-guide-conditional-access-authentication-context)参照してください。

### ベスト プラクティス

保護されたアクションを使用するためのベスト プラクティスを次に示します。

- **緊急アカウント** がある

    保護されたアクションに対して条件付きアクセス ポリシーを構成する場合は、ポリシーから除外される緊急アカウントがあることを確認してください。 これにより、偶発的なロックアウトに対する軽減策が提供されます。
- **ユーザーとサインインのリスク ポリシーを条件付きアクセス** に移動する

    条件付きアクセスのアクセス許可は、Microsoft Entra ID Protection リスク ポリシーを管理するときに使用されません。 ユーザーとサインインのリスク ポリシーを条件付きアクセスに移行することをお勧めします。
- **名前付きネットワークの場所を使用する**

    多要素認証の信頼できる IP を管理する場合、名前付きネットワークの場所のアクセス許可は使用されません。 [名前付きのネットワークの場所](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-assignment-network)を使用することをお勧めします。
- **保護されたアクションを使用して、ID またはグループ メンバーシップの** に基づいてアクセスをブロックしない

    保護されたアクションは、保護されたアクションを実行するためのアクセス要件を適用するために使用されます。 ユーザー ID またはグループ メンバーシップのみに基づいてアクセス許可の使用をブロックすることを目的としていません。 特定のアクセス許可にアクセスできるユーザーは承認の決定であり、ロールの割り当てによって制御する必要があります。

### ライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)の一般公開機能を比較する」を参照してください。
<!-- /MSL-PAGE -->
