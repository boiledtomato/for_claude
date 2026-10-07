# Microsoft Learn — Microsoft Entra / ユーザー・デバイス・ロール・マネージド ID (part 7)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 21

---

<!-- MSL-PAGE {"url":"entra/identity/users/linkedin-integration"} -->
## LinkedIn アカウント接続に対する管理者の同意 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/linkedin-integration
- Service: entra-id / users
- Article date: 2024-12-13
- Summary: Microsoft Entra ID で Microsoft アプリの LinkedIn 統合アカウント接続を有効または無効にする方法について説明します

### 概要

組織内のユーザーに、一部の Microsoft アプリ内で自分の LinkedIn 接続へのアクセスを許可することができます。 ユーザーが自分のアカウントへの接続に同意するまで、データは共有されません。 Microsoft Entra の一部である Microsoft Entra ID を使って組織を統合できます。

重要

LinkedIn アカウント接続の設定は、現在 Microsoft Entra 組織にロールアウト中です。 組織にロールアウトされると、既定で有効になります。

例外:

- この設定は、Microsoft Cloud for US Government、Microsoft Cloud Germany、または中国の 21Vianet が運営する Azure および Microsoft 365 を使用しているお客様には使用できません。
- ドイツでプロビジョニングされた Microsoft Entra 組織の場合、この設定は既定でオフになっています。 この設定は、Microsoft Cloud Germany を使用しているお客様には使用できないことに注意してください。
- フランスにプロビジョニングされた組織の場合、この設定は既定でオフです。

組織で LinkedIn アカウント接続が有効になると、アプリがユーザーに代わって会社のデータにアクセスすることにユーザーが同意した後にアカウント接続が機能します。 ユーザーの同意設定については、「[アプリケーションへのユーザー アクセスの削除方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/methods-for-removing-user-access)」を参照してください。

### Azure portal での LinkedIn アカウント接続の有効化

組織全体に対してでも、組織内の選択したユーザーのみに対してでも、アクセス権を付与したいユーザーにのみ LinkedIn アカウント接続を有効にできます。

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. [Microsoft Entra ID] を選びます。
3. **[ユーザー]**&gt;**[すべてのユーザー]** の順に選択します。
4. **[ユーザー設定]** を選択します。
5. **[LinkedIn アカウント接続]** で、ユーザーが自分のアカウントに接続して一部の Microsoft アプリ内で自分の LinkedIn 接続にアクセスすることを許可します。 ユーザーが自分のアカウントへの接続に同意するまで、データは共有されません。

    - **[はい]** を選択して組織内のすべてのユーザーに対してサービスを有効にします。
    - **[選択したグループ]** を選択して、組織内の選択したユーザー グループに対してのみサービスを有効にします。
    - **[いいえ]** を選択して組織内のすべてのユーザーからの同意を取り消します。

    [Image: 組織内の LinkedIn アカウント接続の統合のスクリーンショット。]
6. 完了したら、 **[保存]** を選択して設定を保存します。

重要

LinkedIn 統合は、ユーザーが自分のアカウントへの接続に同意するまで完全には有効になりませんが、個々の同意を必要とせずに、公開されている LinkedIn プロファイル情報にアクセスできます。 完全統合 (双方向の同意と追加フィールド) は、各ユーザーの同意なしには有効になりません。 ユーザーは、その一致が同じ有効なグループにあるかどうかに関係なく、検索された名前に一致するすべてのユーザーの利用可能な LinkedIn プロファイルを表示できます。

#### グループを使用して選択したユーザーを割り当てる

ユーザーの一覧を指定する [選択済み] オプションは、ユーザーのグループを選択するオプションに置き換えられました。そのため、多数の個々のユーザーではなく、1 つのグループに対して LinkedIn と Microsoft アカウントを接続できます。 選択した個々のユーザーに対して有効になっている LinkedIn アカウント接続を持っていない場合は、何もする必要はありません。 選択した個々のユーザーに対する LinkedIn アカウント接続を以前に有効にした場合には、次のようにする必要があります。

1. 個々のユーザーの現在の一覧を取得します。
2. 現在有効になっている個々のユーザーをグループに移行します。
3. Azure portal の LinkedIn アカウント接続設定で、選択したグループとして以前からのグループを使用します。

注意

現在選択されている個々のユーザーをグループに移行しない場合でも、Microsoft アプリで LinkedIn 情報を確認できます。

#### 選択したユーザーをグループに移動する

1. LinkedIn アカウント接続に選択されているユーザーの CSV ファイルを作成します。
2. 管理者アカウントで Microsoft 365 にサインインします。
3. PowerShell を起動します。
4. `Install-Module Microsoft.Graph -Scope CurrentUser` を実行して Microsoft Graph PowerShell モジュールをインストールします。
5. 次のスクリプトを実行します。

    ```PowerShell
    $groupId = "GUID of the target group"
    
    $users = Get-Content
    Path to the CSV file
    
    $i = 1
    foreach($user in $users) { 
      New-MgGroupMember -GroupId "$groupId" -DirectoryObjectId "$user" ;
      Write-Host $i Added $user ; $i++ ;
      Start-Sleep -Milliseconds 10
    }
    ```

手順 2 からのグループを、Azure portal の LinkedIn アカウント接続設定で選択したグループとして使用するには、「Azure portal で LinkedIn アカウント接続を有効にする」を参照してください。

### グループ ポリシーを使用して LinkedIn アカウント接続を有効にする

1. [Office 2016 管理用テンプレート ファイル (ADMX/ADML)](https://www.microsoft.com/download/details.aspx?id=49030) をダウンロードします。
2. **ADMX** ファイルを抽出して中央のストアにコピーします。
3. [グループ ポリシーの管理] を開きます。
4. 次の設定を使用してグループ ポリシー オブジェクトを作成します。 **[ユーザーの構成]**&gt;**[管理用テンプレート]**&gt;**[Microsoft Office 2016]**&gt;**[その他]**&gt;**[LinkedIn の機能を Office アプリケーションで表示]** 。
5. **[有効]** または **[無効]** を選択します。

    | 状態 | 影響 |
    | --- | --- |
    | **有効** | Office 2016 オプションで **Office アプリケーションでの LinkedIn の表示機能** 設定が有効になります。 組織内のユーザーは、各自の Office 2016 アプリケーションで LinkedIn の機能を使用できます。 |
    | **無効** | Office 2016 オプションで **Office アプリケーションでの LinkedIn の表示機能** 設定が無効になり、エンドユーザーはこの設定を変更できません。 組織内のユーザーがその Office 2016 アプリケーションで LinkedIn の機能を使用することはできません。 |

このグループ ポリシーが適用されるのは、ローカル コンピューターの Office 2016 アプリだけです。 ユーザーが各自の Office 2016 アプリで LinkedIn を無効にした場合でも、Microsoft 365 で LinkedIn の機能が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/linkedin-user-consent"} -->
## LinkedIn データ共有と同意 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/linkedin-user-consent
- Service: entra-id / users
- Article date: 2024-12-19
- Summary: Microsoft Entra ID で Microsoft のアプリを介して LinkedIn 統合がデータを共有する方法について説明します

### 概要

Microsoft Entra の一部である Microsoft Entra ID では、組織内のユーザーが、自分の Microsoft の職場または学校アカウントを LinkedIn アカウントに接続することに同意することを可能にできます。 ユーザーは、自分のアカウントを接続すると、LinkedIn からの情報や注意を、一部の Microsoft のアプリとサービスで見ることができます。 また、ユーザーは、LinkedIn での自分のネットワーク エクスペリエンスが、Microsoft からの情報で改善および拡充されることも期待できます。

Microsoft のアプリやサービスで LinkedIn の情報を表示するには、ユーザーは自分の Microsoft アカウントと LinkedIn アカウントを接続することに同意する必要があります。 ユーザーは、Outlook、OneDrive、または SharePoint Online のプロファイル カードに他のユーザーの LinkedIn 情報を初めて表示するように選択すると、自分のアカウントを接続するように求められます。 LinkedIn アカウント接続は、ユーザーがエクスペリエンスに同意してアカウントを接続するまで、完全には有効になりません。

注

個人データの表示または削除の詳細については、GDPR サイトに [対する Windows データ主体の要求](https://learn.microsoft.com/ja-jp/microsoft-365/compliance/gdpr-dsr-windows) に関する Microsoft のガイダンスを確認してください。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### LinkedIn の情報を共有するメリット

Microsoft のアプリおよびサービス内で LinkedIn の情報にアクセスすると、ユーザーは、組織の内部および外部の同僚、顧客、パートナーとの間でいっそう簡単にプロフェッショナルな関係を結び、協力して、構築することができるようになります。 新しいユーザーは、同僚と関係を結び、同僚について知り、より多くの情報に簡単にアクセスできることで、より速く組織にとけ込むことができます。

この画像は、Microsoft アプリのプロファイル カードに LinkedIn 情報がどのように表示されるかの例を示しています。

[Image: 組織内で LinkedIn 統合を有効にするスクリーンショット。]

### LinkedIn の統合を有効にして発表する

組織の設定を管理するには、Microsoft Entra 管理者である必要があります。 すべてのユーザーについて有効にすることも、特定のユーザー セットだけにすることもできます。

1. 統合を有効または無効にするには、[Microsoft Entra 組織の LinkedIn の統合の同意](https://learn.microsoft.com/ja-jp/entra/identity/users/linkedin-integration)に関するページに記載されている手順に従います。
2. LinkedIn の統合を組織で発表するときは、[Microsoft のアプリおよびサービスで LinkedIn の情報](https://support.office.com/article/about-linkedin-information-and-features-in-microsoft-apps-and-services-dc81cc70-4d64-4755-9f1c-b9536e34d381)に関する FAQ にユーザーの注意を促します。 この記事では、LinkedIn の情報が表示される場所、[データの共有とプライバシー](https://support.microsoft.com/office/your-data-ae9c08a7-4d06-45b5-a065-320a97bc1400)、[アカウントの接続方法](https://support.microsoft.com/office/connect-your-linkedin-and-work-or-school-accounts-c7c245f2-fa56-4c9b-ba20-3fceb23c5772)などについて説明します。

LinkedIn 統合をユーザーに発表し、 [LinkedIn 統合によるデータ共有とプライバシー](https://support.microsoft.com/office/your-data-ae9c08a7-4d06-45b5-a065-320a97bc1400)に関連するすべての情報を提供する必要があります。

### Microsoft と LinkedIn でのデータ アクセスに対するユーザーの同意

LinkedIn からアクセスされたデータは、Microsoft サービスに永続的に保存されません。 Microsoft からアクセスされたデータは、LinkedIn で永続的に保存されません。

ユーザーは、自分のアカウントを接続すると、LinkedIn からの情報や分析情報を、プロファイル カードなどの Microsoft のアプリで見ることができます。 また、ユーザーは、LinkedIn での自分のネットワーク エクスペリエンスが、Microsoft からの情報で改善および拡充されることも期待できます。 組織のユーザーが、自分の LinkedIn アカウントと職場または学校の Microsoft アカウントに接続する際は、2 つのオプションがあります。

- 両方のアカウントからアクセスされるデータに対するアクセス許可を付与します。 つまり、ユーザーは、Microsoft の職場または学校アカウントに LinkedIn アカウントのデータにアクセスする許可を与え、[LinkedIn アカウントに Microsoft の職場または学校のデータにアクセスする](https://www.linkedin.com/help/linkedin/answer/84077)許可を与えます。
- Microsoft の職場または学校アカウントによってアクセスされる LinkedIn のデータだけに対するアクセス許可を付与します。

ユーザーは、いつでもアカウントの接続を解除してデータのアクセス許可を削除することができ、各自の LinkedIn プロフィールを Microsoft のアプリで閲覧できるかどうかなど、[自分の LinkedIn プロフィールの公開方法を制御](https://www.linkedin.com/help/linkedin/answer/83)することができます。

#### LinkedIn アカウントのデータ

Microsoft のアカウントと LinkedIn のアカウントを接続するときに、次のデータを Microsoft に提供することを LinkedIn に許可します。

- プロファイル データ - LinkedIn の ID、連絡先情報、[LinkedIn プロファイル](https://www.linkedin.com/help/linkedin/answer/15493)上で他のユーザーと共有する情報などです。
- 関心についてのデータ - フォローしている人やトピック、コース グループ、お気に入りで共有するコンテンツなど、LinkedIn での関心を含みます。
- サブスクリプション データ - LinkedIn のアプリケーションとサービスおよび関連付けられたデータに対するサブスクリプションです。
- 接続データ - これは、1次接続のプロファイルや連絡先情報を含む[LinkedInネットワーク](https://www.linkedin.com/help/linkedin/answer/110)です。

LinkedIn からアクセスされたデータは、Microsoft サービスに永続的に保存されません。 Microsoft による個人データの使用について詳しくは、「[Microsoft のプライバシーに関する声明](https://privacy.microsoft.com/privacystatement/)」をご覧ください。

#### Microsoft の職場または学校アカウントのデータ

Microsoft のアカウントと LinkedIn のアカウントを接続するときに、次のデータを LinkedIn に提供することを Microsoft に許可します。

- プロファイル データ - 名、姓、プロフィール写真、メール アドレス、上司、部下などの情報です。
- 予定表データ - 予定表の会議、日時、場所、出席者の連絡先情報などです。 議題、コンテンツ、会議のタイトルなどの会議に関する情報は、予定表データには含まれません。
- 関心についてのデータ - Cortana や Bing for Business などの Microsoft のサービスの使用に基づく、アカウントに関連付けられている関心に関する情報です。
- サブスクリプション データ - Microsoft 365 などの Microsoft のアプリとサービスに対して組織によって提供するサブスクリプションです。
- 連絡先データ - 頻繁に通信または共同作業する人の連絡先情報など、Outlook、Skype、他の Microsoft アカウント サービスでの連絡先リストです。 連絡先は、LinkedIn によって定期的にインポート、保存、使用されます。たとえば、接続の提案、連絡先の整理、連絡先に関する更新プログラムの表示に役立ちます。

Microsoft からアクセスされたデータは、LinkedIn で永続的に保存されません。 LinkedIn での個人データの使用に関して詳しくは、[LinkedIn のプライバシー ポリシー](https://www.linkedin.com/legal/privacy-policy)をご覧ください。 LinkedIn のサービス、データ転送、およびストレージの場合、データを欧州連合と米国の間で双方向に伝送でき、プライバシーは[欧州連合のデータ転送](https://www.linkedin.com/help/linkedin/answer/62533)に従って保護されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/manage-dynamic-group"} -->
## Microsoft Entra ID での動的なグループ処理を理解して管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/manage-dynamic-group
- Service: entra-id / users
- Article date: 2025-04-08
- Summary: 動的グループ管理のしくみについて説明します。

### 概要

Microsoft Entra ID の動的メンバーシップ グループは、管理者がグループ メンバーシップの管理を自動化できる強力な機能です。 通常、メンバーシップの変更は数時間以内に処理されます。

ただし、特定の条件下では、メンバーシップの更新が遅れる可能性があります。 処理には 24 時間以上かかることがあります。 基になる原因を理解することは、管理者が構成を最適化し、不要な処理のボトルネックを回避するのに役立ちます。

### 動的グループ処理のしくみ

動的なグループ処理は、順番に動作します。 1 つのテナントの変更は、一度にすべてではなく順番に評価および適用されます。 大量の変更 (特に多くのユーザーまたはデバイスに影響を与える場合) は、長い処理キューにつながる可能性があります。 長いキューは、更新が処理を完了するために必要な時間を延長できます。

#### 処理時間に影響する主な要因

処理に影響を与え、メンバーシップの更新に時間がかかる可能性がある 3 つの最大の要因は次のとおりです。

- **動的グループの数: 多数の動的グループ**を持つテナントでは、より多くの評価が必要で、処理時間が長くなります。
- **オブジェクト変更の数**: 大量のユーザーまたはデバイスの変更により、長い処理キューが作成され、処理時間が長くなる可能性があります。 たとえば、拡張機能の属性の変更、デバイスの追加または削除、ユーザーの一括更新などがあります。
- **ルールの構成**: 特定のルール構成が処理時間に影響を与える可能性があります。 たとえば、 `Match`、 `Contains`、 `memberOf` などの非効率的な演算子を選択すると、処理時間が長くなる可能性があります。 ルールの複雑さも一因です。

Note

古いデバイスと非アクティブなユーザー アカウントは、動的メンバーシップ ルールのスコープ内に留まり、ルールの条件を満たす場合にグループに追加できます。 [古いデバイス](https://learn.microsoft.com/ja-jp/entra/identity/devices/manage-stale-devices)と[非アクティブなユーザー](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-manage-inactive-user-accounts)を確認してクリーンアップし、動的グループに管理するオブジェクトのみが含まれるようにします。

### テナント内の動的メンバーシップ グループのベスト プラクティス

効率的な処理を確保し、遅延を最小限に抑えるには、次のベスト プラクティスを検討してください。

#### テナント内の動的メンバーシップ グループの数を監視する

テナント内のグループの数を定期的に確認します。 非アクティブなグループまたは古いグループを削除します。

#### 不要なグループを一時停止する

不要なグループを一時停止して、処理のパフォーマンスを向上させることができます。 このような状況では、グループ処理を一時停止することを検討してください。

- **計画された大規模な更新**: グループ メンバーシップに多数の変更を加える予定です。 たとえば、500 を超えるグループに変更を加えたり、20,000 を超えるメンバーシップを変更したりする予定です。
- **予期しない遅延**: グループ メンバーシップが変更されておらず、予期しない遅延が発生していることがわかります。

処理を一時的に停止するには、[ **すべてのグループの一時停止]** スクリプトを使用します。 再開する前に、サービスの復旧を許可します。

すぐにグループの一時停止を解除しないでください。 グループ処理が追いつくことを許可するには、少なくとも 24 時間待ちます。 次に、監査ログを調べて、それらがベースラインに戻っているかどうかを確認します。 必要に応じて、一度にすべてではなく、段階的にグループの一時停止を解除します。

#### ルールの効率を最適化する

- ルールで `Match` 演算子を使用することは、できるだけ避けてください。 代わりに、 `StartsWith`、 `Equals`、または `EndsWith` 演算子を使用します。
- ルールで `Contains` 演算子を使用することは、できるだけ避けてください。 処理時間の増加につながる可能性があります。
- 使用する `-or` 演算子の数を減らします。 代わりに、 `-in` 演算子を使用して、ルールを 1 つの条件にグループ化します。 ルールをグループ化すると、簡単に評価できます。
- 可能であれば、 [`memberOf`](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-rule-member-of) 演算子を使用しないでください。 現在プレビュー段階であり、バグと制限事項が付属しています。 また、特にテナントに多数のグループや頻繁な更新がある場合は、より複雑になる可能性があります。 テナント内の既存の `memberOf` グループを削除することをお勧めします。

動的グループ処理の最適化に関する詳細については、「 [Microsoft Entra ID での動的メンバーシップ グループに対するよりシンプルで効率的なルールの作成」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-dynamic-rule-more-efficient)。

### 概要

動的グループ処理の遅延は、主に大量の変更と多数のグループが原因で発生します。 IT 管理者は、ルールの効率の最適化、変更の監視、必要に応じて不要なグループの一時停止などのベスト プラクティスに従うことで、処理のパフォーマンスを向上させ、不要な遅延を回避できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/scripts/powershell-pause-all-dynamic-membership"} -->
## PowerShell サンプル - 動的メンバーシップを使用してすべてのグループと管理単位を一時停止する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-pause-all-dynamic-membership
- Service: entra-id / users
- Article date: 2026-06-11
- Summary: 継続的なメンバーシップ処理の問題を軽減するために、Microsoft Entra テナントの動的メンバーシップを持つすべてのグループと管理単位を一時停止する PowerShell サンプル。

テナント全体の動的メンバーシップに問題が疑われる場合は、調査している間、すべての動的メンバーシップ コレクションを一度に一時停止することで、それ以上のルール評価を停止できます。

### 概要

この PowerShell サンプルでは、Microsoft Entra テナントに動的メンバーシップ ルールがあるすべてのグループと管理単位を一時停止します。 動的メンバーシップ処理を一時停止すると、ルールの評価が停止され、インシデントの調査または復旧中に意図しないメンバーシップの変更が防止されます。 スクリプトは 2 つのフェーズで実行されるため、グループ、管理単位、またはその両方を一時停止できます。

### 重要な考慮事項

- PowerShell 5.1 (x64) 以降からスクリプトを実行します。
- Microsoft Graph PowerShell モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
- 対象となるコレクションを管理できるアカウントでサインインします。 グループ フェーズには [Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) Microsoft Entra ロールが必要であり、`Group.ReadWrite.All` Microsoft Graph スコープが要求されます。 管理単位フェーズには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) Microsoft Entra ロールが必要であり、`AdministrativeUnit.ReadWrite.All` スコープを要求します。 スクリプトは、実行するフェーズのスコープのみを要求します。
- このスクリプトは、最初に動的メンバーシップを持つグループ、次に動的メンバーシップを持つ管理単位という 2 つのフェーズで実行されます。 各フェーズの開始時に、スクリプトによって確認が求められます。 `yes`を入力して、そのフェーズを実行するか、他の何かを入力してスキップします。 これにより、1 回の実行でグループのみ、管理単位のみ、またはその両方を対象にできます。
- 大規模なテナントでは、このスクリプトによって Microsoft Graph のスロットリングが発生する可能性があります。 スクリプトには再試行処理が組み込まれているため、失敗するというよりは、実行時間が長くなると考えてください。 明示的なエラーが表示されない限り、実行中にスクリプトを取り消さないでください。
- 運用環境でスクリプトを実行する前に、テスト環境のすべての手順を確認します。

### サンプル スクリプト

```powershell
# DISCLAIMER:
# Copyright (c) Microsoft Corporation. All rights reserved. This
# script is made available to you without any express, implied or
# statutory warranty, not even the implied warranty of
# merchantability or fitness for a particular purpose, or the
# warranty of title or non-infringement. The entire risk of the
# use or the results from the use of this script remains with you.
#
# Usage: powershell.exe .\PauseAll.ps1
# This script allows you to pause all dynamic membership collections (groups and administrative units).
# It can be helpful when you need to mitigate ongoing issues with your dynamic membership collections.

# This function checks if you are already connected to Microsoft Graph.
#     If yes,
#         It disconnects to fetch your current information. Then, it prompts you to confirm the your current information.
#             If you confirm, it reconnects to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#             If you don't confirm, it will not reconnect and will prompt you to connect manually using Connect-MgGraph.
#     If not,
#         It attempts to connect to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#         If it fails to connect, it informs you that the Microsoft.Graph module might not be installed and provides the command to install it.
# Microsoft Graph API version used for all requests in this script.
# Change to "beta" if you need to call beta endpoints. Default: "v1.0".
$graphApiVersion = "v1.0"

function ConnectToGraph {
    param (
        [string]$environment,
        [string[]]$scopes
    )
    # Check if already connected to Microsoft Graph
    if (Get-MgContext) {
        # Disconnect to fetch your current information
        $accountInfo = Disconnect-MgGraph
        Write-Host "MAKE SURE THE BELOW ACCOUNT/CLIENT APPLICATION HAS THE RIGHT SET OF PERMISSIONS TO PAUSE DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND ADMINISTRATIVE UNITS)" -ForegroundColor Yellow
        Write-Host "Confirm the account: $($accountInfo.Account), TenantId: $($accountInfo.TenantId), and ClientId: $($accountInfo.ClientId)" -ForegroundColor Yellow
        $input = Read-Host "Type 'yes' to confirm: "
        if ($input.Trim().ToLower() -eq "yes") {
            # Reconnect with the scopes required by the phases the operator selected.
            Connect-MgGraph -Environment $environment -Scopes $scopes
        } else {
            # Inform you to reconnect manually
            Write-Host "Information not confirmed. Either re-run the script to confirm again or call <Connect-MgGraph> to log in using a different account." -ForegroundColor Yellow
            exit 1
        }
    } else {
        # Attempt to connect with the scopes required by the phases the operator selected.
        Connect-MgGraph -Environment $environment -Scopes $scopes
        if (Get-MgContext) {
            # Recursive call to confirm your information
            ConnectToGraph -environment $environment -scopes $scopes
        } else {
            # Inform you to install Microsoft.Graph module if not connected
            Write-Host "If the Microsoft.Graph module is not installed, you need to install it to run this script." -ForegroundColor Yellow
            Write-Host "Run <Install-Module Microsoft.Graph -Scope CurrentUser> as an administrator." -ForegroundColor Yellow
            exit 1
        }
    }
}

# This function fetches a page of items from Microsoft Graph.
# It returns the items and the next page token if available.
function PageableFetchFromGraph {
    param (
        [string] $uri,
        [hashtable] $headers = @{},
        [string] $kindLabel = "item"
    )
    # Save the request args so we can retry the same call after throttling.
    $invokeArgs = @{
        Method = 'GET'
        Uri    = $uri
    }
    if ($headers.Count -gt 0) {
        $invokeArgs.Headers = $headers
    }
    try {
        # Make GET request to fetch a page of items
        $response = Invoke-MgGraphRequest @invokeArgs
        $items = $response.value
        $nextPage = $response.'@odata.nextLink'
        Write-Host "Found $($items.count) $kindLabel(s) on this page." -ForegroundColor Green
        return $items, $nextPage
    } catch {
        # Handle any errors during fetch, including throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $null
        try { $statusCode = [int]$_.Exception.Response.StatusCode } catch {}
        if ($statusCode -eq 429) {
            try {
                # Sleep then retry the same request once after throttling
                HandleThrottling -ErrorRecord $_
                $response = Invoke-MgGraphRequest @invokeArgs
                $items = $response.value
                $nextPage = $response.'@odata.nextLink'
                Write-Host "Throttling mitigated. Found $($items.count) $kindLabel(s) on this page." -ForegroundColor Green
                return $items, $nextPage
            } catch {
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to fetch $kindLabel(s) after throttling mitigation. URI: $uri" -ForegroundColor Red
                Write-Host "Error message: $($errorMessage)" -ForegroundColor Red
                return $null
            }
        }
        $responseBody = $null
        try {
            if ($_.ErrorDetails -and $_.ErrorDetails.Message) {
                $responseBody = $_.ErrorDetails.Message
            } elseif ($_.Exception.Response) {
                $stream = $_.Exception.Response.GetResponseStream()
                if ($stream) {
                    $reader = New-Object System.IO.StreamReader($stream)
                    $responseBody = $reader.ReadToEnd()
                }
            }
        } catch {}
        Write-Host "Failed to fetch $kindLabel(s). URI: $uri" -ForegroundColor Red
        Write-Host "Error message: $($errorMessage)" -ForegroundColor Red
        if ($responseBody) {
            Write-Host "Response body: $responseBody" -ForegroundColor Red
        }
        return $null
    }
}

# This function pauses a specific group with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function PauseGroup {
    param (
        [string] $url, [string] $groupId
    )
    $invokeArgs = @{
        Uri     = "$url/$groupId"
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $pauseDGjson
    }
    try {
        # Make PATCH request to pause the group
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully paused group with dynamic membership. Id: $groupId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully paused group with dynamic membership. Id: $groupId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to pause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to pause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# This function pauses a specific administrative unit with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function PauseAdministrativeUnit {
    param (
        [string] $url, [string] $auId
    )
    $invokeArgs = @{
        Uri     = "$url/$auId"
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $pauseDGjson
    }
    try {
        # Make PATCH request to pause the administrative unit
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully paused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully paused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to pause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to pause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# This function iterates through all groups with dynamic membership and pauses each one.
# It returns a hashtable with Success and Failure counts.
function PauseAllDynamicMembershipGroups {
    param (
        [string] $graphEndpoint
    )
    # Initialize URI for the first page. The groupTypes/any(...) lambda filter
    # requires advanced query options (ConsistencyLevel: eventual and $count=true).
    # The filter value must be pre-encoded because Invoke-MgGraphRequest double-encodes
    # URLs containing unescaped (, ), or : characters.
    $url = "$graphEndpoint/$graphApiVersion/groups"
    $filterValue = "groupTypes/any(c:c eq 'DynamicMembership')"
    $encodedFilter = [uri]::EscapeDataString($filterValue)
    $uri = "$url`?`$filter=$encodedFilter&`$count=true"
    $advancedHeaders = @{ ConsistencyLevel = 'eventual' }

    # Initialize success and failure count
    $successCount = 0
    $failureCount = 0

    # Pause all groups page by page
    do {
        # Fetch a page of groups
        $groupsData = PageableFetchFromGraph -uri $uri -headers $advancedHeaders -kindLabel "group with dynamic membership"
        if ($groupsData -eq $null) {
            # If failed to fetch groups, break;
            break
        }
        $dynamicGroups = $groupsData[0]
        $nextPage = $groupsData[1]

        # Pause each group in the current page
        foreach ($group in $dynamicGroups) {
            if ($group.membershipRuleProcessingState -ceq "On") {
                $result = PauseGroup -url $url -groupId $group.id
                if ($result) {
                    $successCount++
                } else {
                    $failureCount++
                }
            } else {
                # Skip groups that are not in "On" state
                Write-Host "Group skipped because it was found to be in $($group.membershipRuleProcessingState) state. Id: $($group.Id)" -ForegroundColor Yellow
            }
        }

        # Move to the next page
        $uri = $nextPage
        Write-Host "Checking if more groups with dynamic membership are present on the next page." -ForegroundColor Yellow
    } while ($uri -ne $null)
    Write-Host "No more groups with dynamic membership found." -ForegroundColor Yellow
    return @{ Success = $successCount; Failure = $failureCount }
}

# This function iterates through all administrative units with dynamic membership and pauses each one.
# It returns a hashtable with Success and Failure counts.
function PauseAllDynamicMembershipAdministrativeUnits {
    param (
        [string] $graphEndpoint
    )
    # Initialize URI for the first page. The membershipType filter on administrative units
    # requires advanced query options (ConsistencyLevel: eventual and $count=true).
    $url = "$graphEndpoint/$graphApiVersion/directory/administrativeUnits"
    $filter = "?`$filter=membershipType eq 'Dynamic'&`$count=true"
    $uri = "$url$filter"
    $advancedHeaders = @{ ConsistencyLevel = 'eventual' }

    # Initialize success and failure count
    $successCount = 0
    $failureCount = 0

    # Pause all administrative units page by page
    do {
        # Fetch a page of administrative units
        $auData = PageableFetchFromGraph -uri $uri -headers $advancedHeaders -kindLabel "administrative unit with dynamic membership"
        if ($auData -eq $null) {
            # If failed to fetch administrative units, break;
            break
        }
        $dynamicAUs = $auData[0]
        $nextPage = $auData[1]

        # Pause each administrative unit in the current page
        foreach ($au in $dynamicAUs) {
            if ($au.membershipRuleProcessingState -ceq "On") {
                $result = PauseAdministrativeUnit -url $url -auId $au.id
                if ($result) {
                    $successCount++
                } else {
                    $failureCount++
                }
            } else {
                # Skip administrative units that are not in "On" state
                Write-Host "Administrative unit skipped because it was found to be in $($au.membershipRuleProcessingState) state. Id: $($au.Id)" -ForegroundColor Yellow
            }
        }

        # Move to the next page
        $uri = $nextPage
        Write-Host "Checking if more administrative units with dynamic membership are present on the next page." -ForegroundColor Yellow
    } while ($uri -ne $null)
    Write-Host "No more administrative units with dynamic membership found." -ForegroundColor Yellow
    return @{ Success = $successCount; Failure = $failureCount }
}

# This function handles throttling by sleeping for the duration indicated by the Retry-After
# header (or a default of 60 seconds). The caller is responsible for retrying the request.
function HandleThrottling {
    param (
        [System.Management.Automation.ErrorRecord]$ErrorRecord
    )
    # Throttling occurred, extract Retry-After header if available
    $retryAfter = $ErrorRecord.Exception.Response.Headers.'Retry-After'
    if ($retryAfter) {
        Write-Host "Throttling detected. Waiting for $retryAfter seconds before retrying..."
        Start-Sleep -Seconds $retryAfter
    } else {
        # If Retry-After header is not available, wait for a default time
        Write-Host "Throttling detected. Waiting for default time i.e. 60 seconds before retrying..."
        Start-Sleep -Seconds 60  # Wait for 60 seconds by default
    }
}

# This function helps you choose the correct environment and returns the corresponding endpoint.
function GetEnvironmentAndEndpoint {
    # Prompt you to select the environment
    try {
        $environmentChoice = Read-Host "Please select the environment (default is 'Global'): `nOptions: Global, USGov, USGovDoD, China"
    } catch {
        $environmentChoice = 'Global'
    }

    # Normalize the input to lower case
    $environmentChoice = $environmentChoice.Trim().ToLower()

    # Set the default environment to global
    $selectedEnvironment = "Global"

    # Map your choice to the corresponding environment
    switch ($environmentChoice) {
        "usgov" { $selectedEnvironment = "USGov" }
        "usgovdod" { $selectedEnvironment = "USGovDoD" }
        "china" { $selectedEnvironment = "China" }
        default { $selectedEnvironment = "Global" }
    }

    # Dictionary to map environment names to their endpoints
    $endpoints = @{
        "Global"   = "https://graph.microsoft.com"
        "USGov"    = "https://graph.microsoft.us"
        "USGovDoD" = "https://dod-graph.microsoft.us"
        "China"    = "https://microsoftgraph.chinacloudapi.cn"
    }

    $graphEndpoint = $endpoints[$selectedEnvironment]

    Write-Host "Environment Selected: $($selectedEnvironment). It maps to the graph endpoint: $($graphEndpoint)" -ForegroundColor Green

    # Return the selected environment and graph endpoint
    return @{ "SelectedEnvironment" = $selectedEnvironment; "GraphEndpoint" = $graphEndpoint }
}

# Running the script:
# Prompt you to confirm if you want to run the pause all flow.
Write-Host "DO YOU WANT TO PAUSE ALL DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND ADMINISTRATIVE UNITS)?" -ForegroundColor Yellow
Write-Host "NOTE: If you are running this script as part of a mitigation exercise recommended by Microsoft, we strongly recommend performing this operation for BOTH groups and administrative units with dynamic membership (i.e. answer 'yes' to both phases below)." -ForegroundColor Cyan
$input = Read-Host "Type 'yes' to confirm: "

# Global variable for JSON change.
$global:pauseDGjson = '{"membershipRuleProcessingState":"Paused"}'

# Start the pause all flow if confirmed.
if ($input.Trim().ToLower() -eq "yes") {
    $result = GetEnvironmentAndEndpoint
    $selectedEnvironment = $result.SelectedEnvironment
    $graphEndpoint = $result.GraphEndpoint
    # Determine which phases the operator wants to run BEFORE connecting to Microsoft Graph,
    # so we only request the scopes needed for the selected phases. This avoids requiring
    # AdministrativeUnit.ReadWrite.All consent when the operator only wants to run the groups phase.
    Write-Host "" -ForegroundColor Yellow
    Write-Host "Before connecting to Microsoft Graph, please choose which phases to include in this run." -ForegroundColor Yellow
    Write-Host "Only the scopes required by the selected phases will be requested." -ForegroundColor Yellow

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the groups phase (pause all groups with dynamic membership)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $groupsPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runGroupsPhase = $groupsPhaseInput.Trim().ToLower() -eq "yes"

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the administrative units phase (pause all administrative units with dynamic membership)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $ausPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runAusPhase = $ausPhaseInput.Trim().ToLower() -eq "yes"

    # Build the scopes list based on the selected phases.
    $selectedScopes = @()
    if ($runGroupsPhase) { $selectedScopes += "Group.ReadWrite.All" }
    if ($runAusPhase) { $selectedScopes += "AdministrativeUnit.ReadWrite.All" }

    if ($selectedScopes.Count -eq 0) {
        Write-Host "" -ForegroundColor Yellow
        Write-Host "No phases selected. Exiting without connecting to Microsoft Graph." -ForegroundColor Yellow
        exit 0
    }

    try {
        # Show the operator which scopes will be requested so they know what consent to expect.
        Write-Host "" -ForegroundColor Yellow
        Write-Host "Requesting the following Microsoft Graph scope(s) for this run: $($selectedScopes -join ', ')" -ForegroundColor Yellow

        # Connect to Microsoft Graph requesting only the scopes needed for the selected phases.
        ConnectToGraph -environment $selectedEnvironment -scopes $selectedScopes

        # Track per-phase results so a combined summary can be printed at the end.
        $groupResult = $null
        $auResult = $null

        # Phase 1: groups with dynamic membership
        if ($runGroupsPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 1 OF 2: GROUPS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $groupResult = PauseAllDynamicMembershipGroups -graphEndpoint $graphEndpoint
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 1 skipped. No groups with dynamic membership were paused." -ForegroundColor Yellow
        }

        # Phase 2: administrative units with dynamic membership
        if ($runAusPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 2 OF 2: ADMINISTRATIVE UNITS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $auResult = PauseAllDynamicMembershipAdministrativeUnits -graphEndpoint $graphEndpoint
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 2 skipped. No administrative units with dynamic membership were paused." -ForegroundColor Yellow
        }

        # Combined summary
        Write-Host "" -ForegroundColor Yellow
        Write-Host "PauseAll Operation Complete." -ForegroundColor Yellow
        if ($groupResult) {
            Write-Host "Groups with dynamic membership - Successfully Paused: $($groupResult.Success), Failed: $($groupResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Groups with dynamic membership - Phase skipped." -ForegroundColor Yellow
        }
        if ($auResult) {
            Write-Host "Administrative units with dynamic membership - Successfully Paused: $($auResult.Success), Failed: $($auResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Administrative units with dynamic membership - Phase skipped." -ForegroundColor Yellow
        }
    } catch {
        # Handle any errors during the process
        $errorMessage = $_.Exception.Message
        Write-Host "PauseAll operation failed. Error message: $($errorMessage)" -ForegroundColor Red
    }
# Inform you that the input was not accepted.
} else {
    Write-Host "PauseAll script terminated. Please re-run the script and input 'yes' to run the PauseAll script." -ForegroundColor Red
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/scripts/powershell-pause-all-except-dynamic-membership"} -->
## PowerShell サンプル - 指定を除き、動的メンバーシップを持つすべてのグループと管理単位を一時停止する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-pause-all-except-dynamic-membership
- Service: entra-id / users
- Article date: 2026-06-11
- Summary: 指定した ID を除き、Microsoft Entra テナントの動的メンバーシップを持つすべてのグループと管理単位を一時停止する PowerShell サンプル。

動的メンバーシップ処理を広範囲に停止する必要があるが、いくつかの重要なコレクションを実行したままにする必要がある場合は、指定した除外リストを除くすべてを一時停止できます。

### 概要

この PowerShell サンプルでは、指定した ID の一覧を除き、Microsoft Entra テナント内のすべてのグループと管理単位を動的メンバーシップ ルールで一時停止します。 このサンプルを使用して、特定の重要なコレクションを実行したまま、他のすべての評価を停止します。 スクリプトは 2 つのフェーズ (グループ、管理単位) で実行されます。各フェーズの除外を指定することも、フェーズをスキップすることもできます。

### 重要な考慮事項

- PowerShell 5.1 (x64) 以降からスクリプトを実行します。
- Microsoft Graph PowerShell モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
- 対象となるコレクションを管理できるアカウントでサインインします。 グループ フェーズには [Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) Microsoft Entra ロールが必要であり、`Group.ReadWrite.All` Microsoft Graph スコープが要求されます。 管理単位フェーズには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) Microsoft Entra ロールが必要であり、`AdministrativeUnit.ReadWrite.All` スコープを要求します。 スクリプトは、実行するフェーズのスコープのみを要求します。
- このスクリプトは、最初に動的メンバーシップを持つグループ、次に動的メンバーシップを持つ管理単位という 2 つのフェーズで実行されます。 各フェーズの開始時に、スクリプトによって確認が求められます。 `yes`を入力して、そのフェーズを実行するか、他の何かを入力してスキップします。 これにより、1 回の実行でグループのみ、管理単位のみ、またはその両方を対象にできます。
- 大規模なテナントでは、このスクリプトによって Microsoft Graph のスロットリングが発生する可能性があります。 スクリプトには再試行処理が組み込まれているため、失敗するというよりは、実行時間が長くなると考えてください。 明示的なエラーが表示されない限り、実行中にスクリプトを取り消さないでください。
- このスクリプトは、除外された各 ID が有効な GUID であることを検証します。
- 運用環境でスクリプトを実行する前に、テスト環境のすべての手順を確認します。

### サンプル スクリプト

```powershell
# DISCLAIMER:
# Copyright (c) Microsoft Corporation. All rights reserved. This
# script is made available to you without any express, implied or
# statutory warranty, not even the implied warranty of
# merchantability or fitness for a particular purpose, or the
# warranty of title or non-infringement. The entire risk of the
# use or the results from the use of this script remains with you.
#
# Usage: powershell.exe .\PauseAllExcept.ps1
# This script allows you to pause all dynamic membership collections (groups and administrative units)
# except the ones you provide IDs for.
# It can be helpful when you need to mitigate ongoing issues with your dynamic membership collections.

# This function checks if you are already connected to Microsoft Graph.
#     If yes,
#         It disconnects to fetch your current information. Then, it prompts you to confirm the your current information.
#             If you confirm, it reconnects to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#             If you don't confirm, it will not reconnect and will prompt you to connect manually using Connect-MgGraph.
#     If not,
#         It attempts to connect to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#         If it fails to connect, it informs you that the Microsoft.Graph module might not be installed and provides the command to install it.
# Microsoft Graph API version used for all requests in this script.
# Change to "beta" if you need to call beta endpoints. Default: "v1.0".
$graphApiVersion = "v1.0"

function ConnectToGraph {
    param (
        [string]$environment,
        [string[]]$scopes
    )
    # Check if already connected to Microsoft Graph
    if (Get-MgContext) {
        # Disconnect to fetch your current information
        $accountInfo = Disconnect-MgGraph
        Write-Host "MAKE SURE THE BELOW ACCOUNT/CLIENT APPLICATION HAS THE RIGHT SET OF PERMISSIONS TO PAUSE DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND ADMINISTRATIVE UNITS)" -ForegroundColor Yellow
        Write-Host "Confirm the account: $($accountInfo.Account), TenantId: $($accountInfo.TenantId), and ClientId: $($accountInfo.ClientId)" -ForegroundColor Yellow
        $input = Read-Host "Type 'yes' to confirm: "
        if ($input.Trim().ToLower() -eq "yes") {
            # Reconnect with the scopes required by the phases the operator selected.
            Connect-MgGraph -Environment $environment -Scopes $scopes
        } else {
            # Inform you to reconnect manually
            Write-Host "Information not confirmed. Either re-run the script to confirm again or call <Connect-MgGraph> to log in using a different account." -ForegroundColor Yellow
            exit 1
        }
    } else {
        # Attempt to connect with the scopes required by the phases the operator selected.
        Connect-MgGraph -Environment $environment -Scopes $scopes
        if (Get-MgContext) {
            # Recursive call to confirm your information
            ConnectToGraph -environment $environment -scopes $scopes
        } else {
            # Inform you to install Microsoft.Graph module if not connected
            Write-Host "If the Microsoft.Graph module is not installed, you need to install it to run this script." -ForegroundColor Yellow
            Write-Host "Run <Install-Module Microsoft.Graph -Scope CurrentUser> as an administrator." -ForegroundColor Yellow
            exit 1
        }
    }
}

# This function fetches a page of items from Microsoft Graph.
# It returns the items and the next page token if available.
function PageableFetchFromGraph {
    param (
        [string] $uri,
        [hashtable] $headers = @{},
        [string] $kindLabel = "item"
    )
    # Save the request args so we can retry the same call after throttling.
    $invokeArgs = @{
        Method = 'GET'
        Uri    = $uri
    }
    if ($headers.Count -gt 0) {
        $invokeArgs.Headers = $headers
    }
    try {
        # Make GET request to fetch a page of items
        $response = Invoke-MgGraphRequest @invokeArgs
        $items = $response.value
        $nextPage = $response.'@odata.nextLink'
        Write-Host "Found $($items.count) $kindLabel(s) on this page." -ForegroundColor Green
        return $items, $nextPage
    } catch {
        # Handle any errors during fetch, including throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $null
        try { $statusCode = [int]$_.Exception.Response.StatusCode } catch {}
        if ($statusCode -eq 429) {
            try {
                # Sleep then retry the same request once after throttling
                HandleThrottling -ErrorRecord $_
                $response = Invoke-MgGraphRequest @invokeArgs
                $items = $response.value
                $nextPage = $response.'@odata.nextLink'
                Write-Host "Throttling mitigated. Found $($items.count) $kindLabel(s) on this page." -ForegroundColor Green
                return $items, $nextPage
            } catch {
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to fetch $kindLabel(s) after throttling mitigation. URI: $uri" -ForegroundColor Red
                Write-Host "Error message: $($errorMessage)" -ForegroundColor Red
                return $null
            }
        }
        $responseBody = $null
        try {
            if ($_.ErrorDetails -and $_.ErrorDetails.Message) {
                $responseBody = $_.ErrorDetails.Message
            } elseif ($_.Exception.Response) {
                $stream = $_.Exception.Response.GetResponseStream()
                if ($stream) {
                    $reader = New-Object System.IO.StreamReader($stream)
                    $responseBody = $reader.ReadToEnd()
                }
            }
        } catch {}
        Write-Host "Failed to fetch $kindLabel(s). URI: $uri" -ForegroundColor Red
        Write-Host "Error message: $($errorMessage)" -ForegroundColor Red
        if ($responseBody) {
            Write-Host "Response body: $responseBody" -ForegroundColor Red
        }
        return $null
    }
}

# This function pauses a specific group with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function PauseGroup {
    param (
        [string] $url, [string] $groupId
    )
    $invokeArgs = @{
        Uri     = "$url/$groupId"
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $pauseDGjson
    }
    try {
        # Make PATCH request to pause the group
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully paused group with dynamic membership. Id: $groupId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully paused group with dynamic membership. Id: $groupId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to pause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to pause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# This function pauses a specific administrative unit with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function PauseAdministrativeUnit {
    param (
        [string] $url, [string] $auId
    )
    $invokeArgs = @{
        Uri     = "$url/$auId"
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $pauseDGjson
    }
    try {
        # Make PATCH request to pause the administrative unit
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully paused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully paused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to pause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to pause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# Function to validate GUID
function Is-Guid {
    param (
        [string]$Guid
    )
    return [guid]::TryParse($Guid, [ref]([guid]::Empty))
}

# Helper that prompts you for a comma-separated list of IDs of a given kind, validates them as GUIDs,
# echoes them back for confirmation, and returns the validated list (empty array means "no exclusions").
# Returns $null if you decline to confirm.
function PromptForExclusionIds {
    param (
        [string] $kindLabel
    )
    Write-Host "Enter the $kindLabel IDs to EXCLUDE from pausing (comma-separated). Press Enter on an empty line to exclude none:" -ForegroundColor Yellow
    $inputIds = Read-Host

    if ([string]::IsNullOrWhiteSpace($inputIds)) {
        Write-Host "No exclusions provided. All $kindLabel(s) with dynamic membership currently in 'On' state will be paused." -ForegroundColor Yellow
        $confirm = Read-Host "Type 'yes' to confirm: "
        if ($confirm.Trim().ToLower() -ne "yes") {
            return $null
        }
        # Use the comma operator so the empty array survives function-return enumeration
        # and the caller receives @() (not $null) for the "exclude none" path.
        return ,@()
    }

    # Extracting individual IDs
    $idList = $inputIds -split ',' | ForEach-Object { $_.Trim() } | Where-Object { $_ -ne '' }

    # Validate each ID and remove invalid ones
    $validIdList = @()
    $invalidIds = @()
    foreach ($id in $idList) {
        if (Is-Guid $id) {
            $validIdList += $id
        } else {
            $invalidIds += $id
        }
    }

    if ($invalidIds.Count -gt 0) {
        Write-Host "The following IDs are not valid GUIDs and will be ignored:" -ForegroundColor Red
        foreach ($invalidId in $invalidIds) {
            Write-Host $invalidId
        }
    }

    if ($validIdList.Count -eq 0) {
        Write-Host "No valid IDs entered. No exclusions will be applied for this phase." -ForegroundColor Red
        $confirm = Read-Host "Proceed anyway and pause all $kindLabel(s) in 'On' state? Type 'yes' to confirm: "
        if ($confirm.Trim().ToLower() -ne "yes") {
            return $null
        }
        # Use the comma operator so the empty array survives function-return enumeration.
        return ,@()
    }

    Write-Host "Confirm that you entered $($validIdList.count) valid $kindLabel ID(s) to exclude, as displayed here:" -ForegroundColor Yellow
    foreach ($id in $validIdList) {
        Write-Host $id
    }

    $confirm = Read-Host "Type 'yes' to confirm: "
    if ($confirm.Trim().ToLower() -ne "yes") {
        return $null
    }

    Write-Host "You have confirmed the entry of valid $kindLabel IDs." -ForegroundColor Green
    return $validIdList
}

# This function pauses all groups with dynamic membership except the ones in the supplied exclusion list.
# It returns a hashtable with Success and Failure counts.
function PauseAllDynamicMembershipGroupsExceptSpecified {
    param (
        [string] $graphEndpoint,
        [string[]] $excludedGroupIdList
    )
    # Initialize URI for the first page. The groupTypes/any(...) lambda filter
    # requires advanced query options (ConsistencyLevel: eventual and $count=true).
    # The filter value must be pre-encoded because Invoke-MgGraphRequest double-encodes
    # URLs containing unescaped (, ), or : characters.
    $url = "$graphEndpoint/$graphApiVersion/groups"
    $filterValue = "groupTypes/any(c:c eq 'DynamicMembership')"
    $encodedFilter = [uri]::EscapeDataString($filterValue)
    $uri = "$url`?`$filter=$encodedFilter&`$count=true"
    $advancedHeaders = @{ ConsistencyLevel = 'eventual' }

    # Initialize success and failure count.
    $successCount = 0
    $failureCount = 0

    # Pause all groups page by page except specified ones
    do {
        # Fetch a page of groups
        $groupsData = PageableFetchFromGraph -uri $uri -headers $advancedHeaders -kindLabel "group with dynamic membership"
        if ($groupsData -eq $null) {
            # If failed to fetch groups, break;
            break
        }
        $dynamicGroups = $groupsData[0]
        $nextPage = $groupsData[1]

        # Pause each group in the current page except specified ones
        foreach ($group in $dynamicGroups) {
            if ($excludedGroupIdList -contains $group.id) {
                Write-Host "Group excluded as per your request. Id: $($group.Id)" -ForegroundColor Yellow
                continue
            }
            if ($group.membershipRuleProcessingState -ceq "On") {
                $result = PauseGroup -url $url -groupId $group.id
                if ($result) {
                    $successCount++
                } else {
                    $failureCount++
                }
            } else {
                Write-Host "Group skipped because it was found to be in $($group.membershipRuleProcessingState) state. Id: $($group.Id)" -ForegroundColor Yellow
            }
        }

        # Move to the next page
        $uri = $nextPage
        Write-Host "Checking if more groups with dynamic membership are present on next page." -ForegroundColor Yellow
    } while ($uri -ne $null)
    Write-Host "No more groups with dynamic membership found." -ForegroundColor Yellow
    return @{ Success = $successCount; Failure = $failureCount }
}

# This function pauses all administrative units with dynamic membership except the ones in the supplied exclusion list.
# It returns a hashtable with Success and Failure counts.
function PauseAllDynamicMembershipAdministrativeUnitsExceptSpecified {
    param (
        [string] $graphEndpoint,
        [string[]] $excludedAuIdList
    )
    # Initialize URI for the first page. The membershipType filter on administrative units
    # requires advanced query options (ConsistencyLevel: eventual and $count=true).
    $url = "$graphEndpoint/$graphApiVersion/directory/administrativeUnits"
    $filter = "?`$filter=membershipType eq 'Dynamic'&`$count=true"
    $uri = "$url$filter"
    $advancedHeaders = @{ ConsistencyLevel = 'eventual' }

    # Initialize success and failure count.
    $successCount = 0
    $failureCount = 0

    # Pause all administrative units page by page except specified ones
    do {
        # Fetch a page of administrative units
        $auData = PageableFetchFromGraph -uri $uri -headers $advancedHeaders -kindLabel "administrative unit with dynamic membership"
        if ($auData -eq $null) {
            # If failed to fetch administrative units, break;
            break
        }
        $dynamicAUs = $auData[0]
        $nextPage = $auData[1]

        # Pause each administrative unit in the current page except specified ones
        foreach ($au in $dynamicAUs) {
            if ($excludedAuIdList -contains $au.id) {
                Write-Host "Administrative unit excluded as per your request. Id: $($au.Id)" -ForegroundColor Yellow
                continue
            }
            if ($au.membershipRuleProcessingState -ceq "On") {
                $result = PauseAdministrativeUnit -url $url -auId $au.id
                if ($result) {
                    $successCount++
                } else {
                    $failureCount++
                }
            } else {
                Write-Host "Administrative unit skipped because it was found to be in $($au.membershipRuleProcessingState) state. Id: $($au.Id)" -ForegroundColor Yellow
            }
        }

        # Move to the next page
        $uri = $nextPage
        Write-Host "Checking if more administrative units with dynamic membership are present on next page." -ForegroundColor Yellow
    } while ($uri -ne $null)
    Write-Host "No more administrative units with dynamic membership found." -ForegroundColor Yellow
    return @{ Success = $successCount; Failure = $failureCount }
}

# Internal function to handle throttling and retry requests
# This function handles throttling by sleeping for the duration indicated by the Retry-After
# header (or a default of 60 seconds). The caller is responsible for retrying the request.
function HandleThrottling {
    param (
        [System.Management.Automation.ErrorRecord]$ErrorRecord
    )
    # Throttling occurred, extract Retry-After header if available
    $retryAfter = $ErrorRecord.Exception.Response.Headers.'Retry-After'
    if ($retryAfter) {
        Write-Host "Throttling detected. Waiting for $retryAfter seconds before retrying..."
        Start-Sleep -Seconds $retryAfter
    } else {
        # If Retry-After header is not available, wait for a default time
        Write-Host "Throttling detected. Waiting for default time i.e. 60 seconds before retrying..."
        Start-Sleep -Seconds 60  # Wait for 60 seconds by default
    }
}

# Function to prompt you to select the environment for determining the Microsoft Graph endpoint.
# This function helps you choose the correct environment and returns the corresponding endpoint.
function GetEnvironmentAndEndpoint {
    # Prompt you to select the environment
    try {
        $environmentChoice = Read-Host "Please select the environment (default is 'Global'): `nOptions: Global, USGov, USGovDoD, China"
    } catch {
        $environmentChoice = 'Global'
    }

    # Normalize the input to lower case
    $environmentChoice = $environmentChoice.Trim().ToLower()

    # Set the default environment to global
    $selectedEnvironment = "Global"

    # Map your choice to the corresponding environment
    switch ($environmentChoice) {
        "usgov" { $selectedEnvironment = "USGov" }
        "usgovdod" { $selectedEnvironment = "USGovDoD" }
        "china" { $selectedEnvironment = "China" }
        default { $selectedEnvironment = "Global" }
    }

    # Dictionary to map environment names to their endpoints
    $endpoints = @{
        "Global"   = "https://graph.microsoft.com"
        "USGov"    = "https://graph.microsoft.us"
        "USGovDoD" = "https://dod-graph.microsoft.us"
        "China"    = "https://microsoftgraph.chinacloudapi.cn"
    }

    $graphEndpoint = $endpoints[$selectedEnvironment]

    Write-Host "Environment Selected: $($selectedEnvironment). It maps to the graph endpoint: $($graphEndpoint)" -ForegroundColor Green

    # Return the selected environment and graph endpoint
    return @{ "SelectedEnvironment" = $selectedEnvironment; "GraphEndpoint" = $graphEndpoint }
}

# Running the script:
# Prompt you to confirm if you want to run the pause all except flow.
Write-Host "DO YOU WANT TO PAUSE ALL DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND ADMINISTRATIVE UNITS) EXCEPT SPECIFIED ONES?" -ForegroundColor Yellow
Write-Host "NOTE: If you are running this script as part of a mitigation exercise recommended by Microsoft, we strongly recommend performing this operation for BOTH groups and administrative units with dynamic membership (i.e. answer 'yes' to both phases below)." -ForegroundColor Cyan
$input = Read-Host "Type 'yes' to confirm: "

#Global variable for JSON change.
$global:pauseDGjson = '{"membershipRuleProcessingState":"Paused"}'

# Start the pause all except flow if confirmed.
if ($input.Trim().ToLower() -eq "yes") {
    $result = GetEnvironmentAndEndpoint
    $selectedEnvironment = $result.SelectedEnvironment
    $graphEndpoint = $result.GraphEndpoint
    # Determine which phases the operator wants to run BEFORE connecting to Microsoft Graph,
    # so we only request the scopes needed for the selected phases. This avoids requiring
    # AdministrativeUnit.ReadWrite.All consent when the operator only wants to run the groups phase.
    Write-Host "" -ForegroundColor Yellow
    Write-Host "Before connecting to Microsoft Graph, please choose which phases to include in this run." -ForegroundColor Yellow
    Write-Host "Only the scopes required by the selected phases will be requested." -ForegroundColor Yellow

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the groups phase (pause all groups with dynamic membership except specified ones)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $groupsPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runGroupsPhase = $groupsPhaseInput.Trim().ToLower() -eq "yes"

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the administrative units phase (pause all administrative units with dynamic membership except specified ones)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $ausPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runAusPhase = $ausPhaseInput.Trim().ToLower() -eq "yes"

    # Build the scopes list based on the selected phases.
    $selectedScopes = @()
    if ($runGroupsPhase) { $selectedScopes += "Group.ReadWrite.All" }
    if ($runAusPhase) { $selectedScopes += "AdministrativeUnit.ReadWrite.All" }

    if ($selectedScopes.Count -eq 0) {
        Write-Host "" -ForegroundColor Yellow
        Write-Host "No phases selected. Exiting without connecting to Microsoft Graph." -ForegroundColor Yellow
        exit 0
    }

    try {
        # Show the operator which scopes will be requested so they know what consent to expect.
        Write-Host "" -ForegroundColor Yellow
        Write-Host "Requesting the following Microsoft Graph scope(s) for this run: $($selectedScopes -join ', ')" -ForegroundColor Yellow

        # Connect to Microsoft Graph requesting only the scopes needed for the selected phases.
        ConnectToGraph -environment $selectedEnvironment -scopes $selectedScopes

        # Track per-phase results so a combined summary can be printed at the end.
        $groupResult = $null
        $auResult = $null

        # Phase 1: groups with dynamic membership
        if ($runGroupsPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 1 OF 2: GROUPS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $excludedGroupIds = PromptForExclusionIds -kindLabel "group"
            if ($excludedGroupIds -eq $null) {
                Write-Host "Phase 1 cancelled by user. No groups with dynamic membership were paused." -ForegroundColor Yellow
            } else {
                $groupResult = PauseAllDynamicMembershipGroupsExceptSpecified -graphEndpoint $graphEndpoint -excludedGroupIdList $excludedGroupIds
            }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 1 skipped. No groups with dynamic membership were paused." -ForegroundColor Yellow
        }

        # Phase 2: administrative units with dynamic membership
        if ($runAusPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 2 OF 2: ADMINISTRATIVE UNITS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $excludedAuIds = PromptForExclusionIds -kindLabel "administrative unit"
            if ($excludedAuIds -eq $null) {
                Write-Host "Phase 2 cancelled by user. No administrative units with dynamic membership were paused." -ForegroundColor Yellow
            } else {
                $auResult = PauseAllDynamicMembershipAdministrativeUnitsExceptSpecified -graphEndpoint $graphEndpoint -excludedAuIdList $excludedAuIds
            }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 2 skipped. No administrative units with dynamic membership were paused." -ForegroundColor Yellow
        }

        # Combined summary
        Write-Host "" -ForegroundColor Yellow
        Write-Host "PauseAllExcept Operation Complete." -ForegroundColor Yellow
        if ($groupResult) {
            Write-Host "Groups with dynamic membership - Successfully Paused: $($groupResult.Success), Failed: $($groupResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Groups with dynamic membership - Phase skipped or cancelled." -ForegroundColor Yellow
        }
        if ($auResult) {
            Write-Host "Administrative units with dynamic membership - Successfully Paused: $($auResult.Success), Failed: $($auResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Administrative units with dynamic membership - Phase skipped or cancelled." -ForegroundColor Yellow
        }
    } catch {
        # Handle any errors during the process
        $errorMessage = $_.Exception.Message
        Write-Host "PauseAllExcept operation failed. Error message: $($errorMessage)" -ForegroundColor Red
    }
# Inform you that the input was not accepted.
} else {
    Write-Host "PauseAllExcept script terminated. Please re-run the script and input 'yes' to run the PauseAllExcept script." -ForegroundColor Red
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/scripts/powershell-pause-specific-dynamic-membership"} -->
## PowerShell サンプル - 動的メンバーシップを使用して特定のグループと管理単位を一時停止する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-pause-specific-dynamic-membership
- Service: entra-id / users
- Article date: 2026-06-11
- Summary: ID の一覧を受け入れることで、Microsoft Entra テナントの動的メンバーシップを持つ特定のグループと管理単位を一時停止する PowerShell サンプル。

動的メンバーシップの問題を既知のコレクション セットに分離した場合は、テナント全体ではなく、これらのコレクションだけを一時停止できます。

### 概要

この PowerShell サンプルでは、ID で指定した動的メンバーシップ ルールを使用して、1 つ以上のグループと管理単位を一時停止します。 このサンプルは、動的メンバーシップ コレクションの既知のサブセットの処理を一時停止するだけで済む場合に使用します。 スクリプトは 2 つのフェーズで実行されます。最初にグループ ID を指定してから管理単位 ID を指定し、フェーズまたは両方を実行できます。

### 重要な考慮事項

- PowerShell 5.1 (x64) 以降からスクリプトを実行します。
- Microsoft Graph PowerShell モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
- 対象となるコレクションを管理できるアカウントでサインインします。 グループ フェーズには [Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) Microsoft Entra ロールが必要であり、`Group.ReadWrite.All` Microsoft Graph スコープが要求されます。 管理単位フェーズには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) Microsoft Entra ロールが必要であり、`AdministrativeUnit.ReadWrite.All` スコープを要求します。 スクリプトは、実行するフェーズのスコープのみを要求します。
- このスクリプトは、最初に動的メンバーシップを持つグループ、次に動的メンバーシップを持つ管理単位という 2 つのフェーズで実行されます。 各フェーズの開始時に、スクリプトによって確認が求められます。 `yes`を入力して、そのフェーズを実行するか、他の何かを入力してスキップします。 これにより、1 回の実行でグループのみ、管理単位のみ、またはその両方を対象にできます。
- 大規模なテナントでは、このスクリプトによって Microsoft Graph のスロットリングが発生する可能性があります。 スクリプトには再試行処理が組み込まれているため、失敗するというよりは、実行時間が長くなると考えてください。 明示的なエラーが表示されない限り、実行中にスクリプトを取り消さないでください。
- このスクリプトは、各 ID が有効な GUID であることを検証し、動的ではないコレクションまたは既に一時停止されているコレクションをスキップします。
- 運用環境でスクリプトを実行する前に、テスト環境のすべての手順を確認します。

### サンプル スクリプト

```powershell
# DISCLAIMER:
# Copyright (c) Microsoft Corporation. All rights reserved. This
# script is made available to you without any express, implied or
# statutory warranty, not even the implied warranty of
# merchantability or fitness for a particular purpose, or the
# warranty of title or non-infringement. The entire risk of the
# use or the results from the use of this script remains with you.
#
# Usage: powershell.exe .\PauseSpecific.ps1
# This script allows you to pause specific dynamic membership collections (groups and administrative units).
# It can be helpful when you need to mitigate ongoing issues with your dynamic membership collections.

# This function checks if you are already connected to Microsoft Graph.
#     If yes,
#         It disconnects to fetch your current information. Then, it prompts you to confirm the your current information.
#             If you confirm, it reconnects to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#             If you don't confirm, it will not reconnect and will prompt you to connect manually using Connect-MgGraph.
#     If not,
#         It attempts to connect to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#         If it fails to connect, it informs you that the Microsoft.Graph module might not be installed and provides the command to install it.
# Microsoft Graph API version used for all requests in this script.
# Change to "beta" if you need to call beta endpoints. Default: "v1.0".
$graphApiVersion = "v1.0"

function ConnectToGraph {
    param (
        [string]$environment,
        [string[]]$scopes
    )
    # Check if already connected to Microsoft Graph
    if (Get-MgContext) {
        # Disconnect to fetch your current information
        $accountInfo = Disconnect-MgGraph
        Write-Host "MAKE SURE THE BELOW ACCOUNT/CLIENT APPLICATION HAS THE RIGHT SET OF PERMISSIONS TO PAUSE DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND ADMINISTRATIVE UNITS)" -ForegroundColor Yellow
        Write-Host "Confirm the account: $($accountInfo.Account), TenantId: $($accountInfo.TenantId), and ClientId: $($accountInfo.ClientId)" -ForegroundColor Yellow
        $input = Read-Host "Type 'yes' to confirm: "
        if ($input.Trim().ToLower() -eq "yes") {
            # Reconnect with the scopes required by the phases the operator selected.
            Connect-MgGraph -Environment $environment -Scopes $scopes
        } else {
            # Inform you to reconnect manually
            Write-Host "Information not confirmed. Either re-run the script to confirm again or call <Connect-MgGraph> to log in using a different account." -ForegroundColor Yellow
            exit 1
        }
    } else {
        # Attempt to connect with the scopes required by the phases the operator selected.
        Connect-MgGraph -Environment $environment -Scopes $scopes
        if (Get-MgContext) {
            # Recursive call to confirm your information
            ConnectToGraph -environment $environment -scopes $scopes
        } else {
            # Inform you to install Microsoft.Graph module if not connected
            Write-Host "If the Microsoft.Graph module is not installed, you need to install it to run this script." -ForegroundColor Yellow
            Write-Host "Run <Install-Module Microsoft.Graph -Scope CurrentUser> as an administrator." -ForegroundColor Yellow
            exit 1
        }
    }
}

# This function pauses a specific group with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function PauseGroup {
    param (
        [string] $uri, [string] $groupId
    )
    $invokeArgs = @{
        Uri     = $uri
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $pauseDGjson
    }
    try {
        # Make PATCH request to pause the group
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully paused group with dynamic membership. Id: $groupId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully paused group with dynamic membership. Id: $groupId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to pause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to pause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# This function pauses a specific administrative unit with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function PauseAdministrativeUnit {
    param (
        [string] $uri, [string] $auId
    )
    $invokeArgs = @{
        Uri     = $uri
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $pauseDGjson
    }
    try {
        # Make PATCH request to pause the administrative unit
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully paused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully paused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to pause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to pause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# Function to validate GUID
function Is-Guid {
    param (
        [string]$Guid
    )
    return [guid]::TryParse($Guid, [ref]([guid]::Empty))
}

# Helper that prompts you for a comma-separated list of IDs of a given kind, validates them as GUIDs,
# echoes them back for confirmation, and returns the validated list.
# Returns $null if the user enters no valid IDs or declines to confirm.
function PromptForIdList {
    param (
        [string] $kindLabel
    )
    Write-Host "Enter the $kindLabel IDs (separated by comma if multiple):" -ForegroundColor Yellow
    $inputIds = Read-Host

    if ([string]::IsNullOrWhiteSpace($inputIds)) {
        Write-Host "No IDs entered." -ForegroundColor Red
        return $null
    }

    # Extracting individual IDs
    $idList = $inputIds -split ',' | ForEach-Object { $_.Trim() } | Where-Object { $_ -ne '' }

    # Validate each ID and remove invalid ones
    $validIdList = @()
    $invalidIds = @()
    foreach ($id in $idList) {
        if (Is-Guid $id) {
            $validIdList += $id
        } else {
            $invalidIds += $id
        }
    }

    if ($invalidIds.Count -gt 0) {
        Write-Host "The following IDs are not valid GUIDs and will be removed:" -ForegroundColor Red
        foreach ($invalidId in $invalidIds) {
            Write-Host $invalidId
        }
    }

    if ($validIdList.Count -eq 0) {
        Write-Host "No valid IDs entered. Please re-run the script and provide IDs in GUID format." -ForegroundColor Red
        return $null
    }

    Write-Host "Confirm that you entered $($validIdList.count) valid $kindLabel ID(s), as displayed here:" -ForegroundColor Yellow
    foreach ($id in $validIdList) {
        Write-Host $id
    }

    $confirm = Read-Host "Type 'yes' to confirm: "
    if ($confirm.Trim().ToLower() -ne "yes") {
        return $null
    }

    Write-Host "You have confirmed the entry of valid $kindLabel IDs." -ForegroundColor Green
    return $validIdList
}

# Pauses each of the supplied groups (by ID) if it is a dynamic membership group currently in "On" state.
# Returns a hashtable with Success and Failure counts.
function PauseSpecificDynamicMembershipGroups {
    param (
        [string] $graphEndpoint,
        [string[]] $groupIdList
    )
    $successCount = 0
    $failureCount = 0

    foreach ($groupId in $groupIdList) {
        # Fetch the group with the id given by you.
        $uri = "$graphEndpoint/$graphApiVersion/groups/$groupId"
        try {
            $group = Invoke-MgGraphRequest -Method GET -Uri $uri
        } catch {
            # Branch on status so 429 retries, 404 reports as not-found, and other
            # errors are not silently mislabelled as "Could not find".
            $errorMessage = $_.Exception.Message
            $statusCode = $null
            try { $statusCode = [int]$_.Exception.Response.StatusCode } catch {}
            if ($statusCode -eq 429) {
                try {
                    HandleThrottling -ErrorRecord $_
                    $group = Invoke-MgGraphRequest -Method GET -Uri $uri
                } catch {
                    $errorMessage = $_.Exception.Message
                    Write-Host "Failed to fetch the group with Id: $($groupId) after throttling mitigation. Error message: $($errorMessage)" -ForegroundColor Red
                    $failureCount++
                    continue
                }
            } elseif ($statusCode -eq 404) {
                Write-Host "Could not find the group with Id: $($groupId). Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            } else {
                $statusText = if ($statusCode) { "$statusCode" } else { "Unknown" }
                Write-Host "Failed to fetch the group with Id: $($groupId). Status: $statusText. Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            }
        }
        $isDynamic = $group.groupTypes -contains "DynamicMembership"
        $isUnpaused = $group.membershipRuleProcessingState -ceq "On"

        # Pause this group if it has a dynamic membership rule and is currently unpaused.
        if ($isDynamic -and $isUnpaused) {
            $result = PauseGroup -uri $uri -groupId $groupId
            if ($result) {
                $successCount++
            } else {
                $failureCount++
            }
        } else {
            if (-not $isDynamic) {
                Write-Host "Group skipped because it was found to be not a group with dynamic membership. Id: $($group.Id)" -ForegroundColor Yellow
                continue
            }
            Write-Host "Group skipped because it was found to be in $($group.membershipRuleProcessingState) state. Id: $($group.Id)" -ForegroundColor Yellow
        }
    }
    return @{ Success = $successCount; Failure = $failureCount }
}

# Pauses each of the supplied administrative units (by ID) if it is a dynamic membership AU currently in "On" state.
# Returns a hashtable with Success and Failure counts.
function PauseSpecificDynamicMembershipAdministrativeUnits {
    param (
        [string] $graphEndpoint,
        [string[]] $auIdList
    )
    $successCount = 0
    $failureCount = 0

    foreach ($auId in $auIdList) {
        # Fetch the administrative unit with the id given by you.
        $uri = "$graphEndpoint/$graphApiVersion/directory/administrativeUnits/$auId"
        try {
            $au = Invoke-MgGraphRequest -Method GET -Uri $uri
        } catch {
            $errorMessage = $_.Exception.Message
            $statusCode = $null
            try { $statusCode = [int]$_.Exception.Response.StatusCode } catch {}
            if ($statusCode -eq 429) {
                try {
                    HandleThrottling -ErrorRecord $_
                    $au = Invoke-MgGraphRequest -Method GET -Uri $uri
                } catch {
                    $errorMessage = $_.Exception.Message
                    Write-Host "Failed to fetch the administrative unit with Id: $($auId) after throttling mitigation. Error message: $($errorMessage)" -ForegroundColor Red
                    $failureCount++
                    continue
                }
            } elseif ($statusCode -eq 404) {
                Write-Host "Could not find the administrative unit with Id: $($auId). Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            } else {
                $statusText = if ($statusCode) { "$statusCode" } else { "Unknown" }
                Write-Host "Failed to fetch the administrative unit with Id: $($auId). Status: $statusText. Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            }
        }
        $isDynamic = $au.membershipType -ceq "Dynamic"
        $isUnpaused = $au.membershipRuleProcessingState -ceq "On"

        # Pause this administrative unit if it has a dynamic membership rule and is currently unpaused.
        if ($isDynamic -and $isUnpaused) {
            $result = PauseAdministrativeUnit -uri $uri -auId $auId
            if ($result) {
                $successCount++
            } else {
                $failureCount++
            }
        } else {
            if (-not $isDynamic) {
                Write-Host "Administrative unit skipped because it was found to be not an administrative unit with dynamic membership. Id: $($au.Id)" -ForegroundColor Yellow
                continue
            }
            Write-Host "Administrative unit skipped because it was found to be in $($au.membershipRuleProcessingState) state. Id: $($au.Id)" -ForegroundColor Yellow
        }
    }
    return @{ Success = $successCount; Failure = $failureCount }
}

# Internal function to handle throttling and retry requests
# This function handles throttling by sleeping for the duration indicated by the Retry-After
# header (or a default of 60 seconds). The caller is responsible for retrying the request.
function HandleThrottling {
    param (
        [System.Management.Automation.ErrorRecord]$ErrorRecord
    )
    # Throttling occurred, extract Retry-After header if available
    $retryAfter = $ErrorRecord.Exception.Response.Headers.'Retry-After'
    if ($retryAfter) {
        Write-Host "Throttling detected. Waiting for $retryAfter seconds before retrying..."
        Start-Sleep -Seconds $retryAfter
    } else {
        # If Retry-After header is not available, wait for a default time
        Write-Host "Throttling detected. Waiting for default time i.e. 60 seconds before retrying..."
        Start-Sleep -Seconds 60  # Wait for 60 seconds by default
    }
}

# Function to prompt you to select the environment for determining the Microsoft Graph endpoint.
# This function helps you choose the correct environment and returns the corresponding endpoint.
function GetEnvironmentAndEndpoint {
    # Prompt you to select the environment
    try {
        $environmentChoice = Read-Host "Please select the environment (default is 'Global'): `nOptions: Global, USGov, USGovDoD, China"
    } catch {
        $environmentChoice = 'Global'
    }

    # Normalize the input to lower case
    $environmentChoice = $environmentChoice.Trim().ToLower()

    # Set the default environment to global
    $selectedEnvironment = "Global"

    # Map your choice to the corresponding environment
    switch ($environmentChoice) {
        "usgov" { $selectedEnvironment = "USGov" }
        "usgovdod" { $selectedEnvironment = "USGovDoD" }
        "china" { $selectedEnvironment = "China" }
        default { $selectedEnvironment = "Global" }
    }

    # Dictionary to map environment names to their endpoints
    $endpoints = @{
        "Global"   = "https://graph.microsoft.com"
        "USGov"    = "https://graph.microsoft.us"
        "USGovDoD" = "https://dod-graph.microsoft.us"
        "China"    = "https://microsoftgraph.chinacloudapi.cn"
    }

    $graphEndpoint = $endpoints[$selectedEnvironment]

    Write-Host "Environment Selected: $($selectedEnvironment). It maps to the graph endpoint: $($graphEndpoint)" -ForegroundColor Green

    # Return the selected environment and graph endpoint
    return @{ "SelectedEnvironment" = $selectedEnvironment; "GraphEndpoint" = $graphEndpoint }
}

#Running the script:
#Prompt you to confirm if you want to run the pause specific flow.
Write-Host "DO YOU WANT TO PAUSE SPECIFIC DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND/OR ADMINISTRATIVE UNITS)?" -ForegroundColor Yellow
Write-Host "NOTE: If you are running this script as part of a mitigation exercise recommended by Microsoft, we strongly recommend performing this operation for BOTH groups and administrative units with dynamic membership (i.e. answer 'yes' to both phases below)." -ForegroundColor Cyan
$input = Read-Host "Type 'yes' to confirm: "

#Global variable for JSON change.
$global:pauseDGjson = '{"membershipRuleProcessingState":"Paused"}'

# Start the pause specific flow if confirmed.
if (($input.Trim().ToLower() -eq "yes")) {
    $result = GetEnvironmentAndEndpoint
    $selectedEnvironment = $result.SelectedEnvironment
    $graphEndpoint = $result.GraphEndpoint
    # Determine which phases the operator wants to run BEFORE connecting to Microsoft Graph,
    # so we only request the scopes needed for the selected phases. This avoids requiring
    # AdministrativeUnit.ReadWrite.All consent when the operator only wants to run the groups phase.
    Write-Host "" -ForegroundColor Yellow
    Write-Host "Before connecting to Microsoft Graph, please choose which phases to include in this run." -ForegroundColor Yellow
    Write-Host "Only the scopes required by the selected phases will be requested." -ForegroundColor Yellow

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the groups phase (pause specific groups with dynamic membership by ID)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $groupsPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runGroupsPhase = $groupsPhaseInput.Trim().ToLower() -eq "yes"

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the administrative units phase (pause specific administrative units with dynamic membership by ID)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $ausPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runAusPhase = $ausPhaseInput.Trim().ToLower() -eq "yes"

    # Build the scopes list based on the selected phases.
    $selectedScopes = @()
    if ($runGroupsPhase) { $selectedScopes += "Group.ReadWrite.All" }
    if ($runAusPhase) { $selectedScopes += "AdministrativeUnit.ReadWrite.All" }

    if ($selectedScopes.Count -eq 0) {
        Write-Host "" -ForegroundColor Yellow
        Write-Host "No phases selected. Exiting without connecting to Microsoft Graph." -ForegroundColor Yellow
        exit 0
    }

    try {
        # Show the operator which scopes will be requested so they know what consent to expect.
        Write-Host "" -ForegroundColor Yellow
        Write-Host "Requesting the following Microsoft Graph scope(s) for this run: $($selectedScopes -join ', ')" -ForegroundColor Yellow

        # Connect to Microsoft Graph requesting only the scopes needed for the selected phases.
        ConnectToGraph -environment $selectedEnvironment -scopes $selectedScopes

        # Track per-phase results so a combined summary can be printed at the end.
        $groupResult = $null
        $auResult = $null

        # Phase 1: groups with dynamic membership
        if ($runGroupsPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 1 OF 2: GROUPS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $groupIds = PromptForIdList -kindLabel "group"
            if ($groupIds -eq $null) {
                Write-Host "Phase 1 cancelled by user. No groups with dynamic membership were paused." -ForegroundColor Yellow
            } else {
                $groupResult = PauseSpecificDynamicMembershipGroups -graphEndpoint $graphEndpoint -groupIdList $groupIds
            }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 1 skipped. No groups with dynamic membership were paused." -ForegroundColor Yellow
        }

        # Phase 2: administrative units with dynamic membership
        if ($runAusPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 2 OF 2: ADMINISTRATIVE UNITS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $auIds = PromptForIdList -kindLabel "administrative unit"
            if ($auIds -eq $null) {
                Write-Host "Phase 2 cancelled by user. No administrative units with dynamic membership were paused." -ForegroundColor Yellow
            } else {
                $auResult = PauseSpecificDynamicMembershipAdministrativeUnits -graphEndpoint $graphEndpoint -auIdList $auIds
            }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 2 skipped. No administrative units with dynamic membership were paused." -ForegroundColor Yellow
        }

        # Combined summary
        Write-Host "" -ForegroundColor Yellow
        Write-Host "PauseSpecific Operation Complete." -ForegroundColor Yellow
        if ($groupResult) {
            Write-Host "Groups with dynamic membership - Successfully Paused: $($groupResult.Success), Failed: $($groupResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Groups with dynamic membership - Phase skipped or cancelled." -ForegroundColor Yellow
        }
        if ($auResult) {
            Write-Host "Administrative units with dynamic membership - Successfully Paused: $($auResult.Success), Failed: $($auResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Administrative units with dynamic membership - Phase skipped or cancelled." -ForegroundColor Yellow
        }
    } catch {
        # Handle any errors during the process
        $errorMessage = $_.Exception.Message
        Write-Host "PauseSpecific operation failed. Error message: $($errorMessage)" -ForegroundColor Red
    }
# Inform you that the input was not accepted.
} else {
    Write-Host "PauseSpecific script terminated. Please re-run the script and input 'yes' to run the PauseSpecific script." -ForegroundColor Red
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/scripts/powershell-resume-noncritical-dynamic-membership"} -->
## PowerShell サンプル - 動的メンバーシップをバッチで使用して重要でないグループと管理単位を再開する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-resume-noncritical-dynamic-membership
- Service: entra-id / users
- Article date: 2026-06-11
- Summary: Microsoft Entra テナント内の重要でないグループと管理単位の動的メンバーシップ処理を再開する PowerShell サンプル。各実行ごとに最大 100 個。

重要なコレクションがオンラインに戻ったら、制御されたバッチ内の残りの重要でないコレクションを再開して、動的メンバーシップ処理の圧倒的な処理を回避します。

### Overview

この PowerShell サンプルでは、一時停止された重要でないグループと管理単位に対する動的メンバーシップ ルールの処理を再開します。各実行ごとに最大 100 個。 重要なコレクションを再開し、一時停止してから少なくとも 12 時間待機した後、このサンプルを実行します。 バッチ処理を使用すると、多くのコレクションが同時に処理を再開するときに、サービスが過剰に処理されるのを防ぐことができます。 スクリプトは、最初に最大 100 グループ、次に最大 100 個の管理単位の 2 つのフェーズで実行されます。

### 重要な考慮事項

- PowerShell 5.1 (x64) 以降からスクリプトを実行します。
- Microsoft Graph PowerShell モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
- 対象となるコレクションを管理できるアカウントでサインインします。 グループ フェーズには [Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) Microsoft Entra ロールが必要であり、`Group.ReadWrite.All` Microsoft Graph スコープが要求されます。 管理単位フェーズには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) Microsoft Entra ロールが必要であり、`AdministrativeUnit.ReadWrite.All` スコープを要求します。 スクリプトは、実行するフェーズのスコープのみを要求します。
- このスクリプトは、最初に動的メンバーシップを持つグループ、次に動的メンバーシップを持つ管理単位という 2 つのフェーズで実行されます。 各フェーズの開始時に、スクリプトによって確認が求められます。 `yes`を入力して、そのフェーズを実行するか、他の何かを入力してスキップします。 これにより、1 回の実行でグループのみ、管理単位のみ、またはその両方を対象にできます。
- 大規模なテナントでは、このスクリプトによって Microsoft Graph のスロットリングが発生する可能性があります。 スクリプトには再試行処理が組み込まれているため、失敗するというよりは、実行時間が長くなると考えてください。 明示的なエラーが表示されない限り、実行中にスクリプトを取り消さないでください。
- まず、[resume-specific-critical sample](https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-resume-specific-critical-dynamic-membership) を使用して重要なコレクションを再開します。
- スクリプトは、1 回の実行で最大 100 個のグループと最大 100 個の管理単位を処理します。 すべての重要でないコレクションが再開されるまで繰り返し実行します。
- 運用環境でスクリプトを実行する前に、テスト環境のすべての手順を確認します。

### サンプル スクリプト

```powershell
# DISCLAIMER:
# Copyright (c) Microsoft Corporation. All rights reserved. This
# script is made available to you without any express, implied or
# statutory warranty, not even the implied warranty of
# merchantability or fitness for a particular purpose, or the
# warranty of title or non-infringement. The entire risk of the
# use or the results from the use of this script remains with you.
#
# Usage: powershell.exe .\UnpauseNonCritical.ps1
# This script allows you to unpause non critical dynamic membership collections (groups and administrative units), 100 at a time per phase.
# It can be helpful when you need to mitigate ongoing issues with your collections that have dynamic membership rules.

# This function checks if you are already connected to Microsoft Graph.
#     If yes,
#         It disconnects to fetch your current information. Then, it prompts you to confirm the your current information.
#             If you confirm, it reconnects to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#             If you don't confirm, it will not reconnect and will prompt you to connect manually using Connect-MgGraph.
#     If not,
#         It attempts to connect to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#         If it fails to connect, it informs you that the Microsoft.Graph module might not be installed and provides the command to install it.
# Microsoft Graph API version used for all requests in this script.
# Change to "beta" if you need to call beta endpoints. Default: "v1.0".
$graphApiVersion = "v1.0"

function ConnectToGraph {
    param (
        [string]$environment,
        [string[]]$scopes
    )
    # Check if already connected to Microsoft Graph
    if (Get-MgContext) {
        # Disconnect to fetch your current information
        $accountInfo = Disconnect-MgGraph
        Write-Host "MAKE SURE THE BELOW ACCOUNT/CLIENT APPLICATION HAS THE RIGHT SET OF PERMISSIONS TO UNPAUSE DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND ADMINISTRATIVE UNITS)" -ForegroundColor Yellow
        Write-Host "Confirm the account: $($accountInfo.Account), TenantId: $($accountInfo.TenantId), and ClientId: $($accountInfo.ClientId)" -ForegroundColor Yellow
        $input = Read-Host "Type 'yes' to confirm: "
        if ($input.Trim().ToLower() -eq "yes") {
            # Reconnect with the scopes required by the phases the operator selected.
            Connect-MgGraph -Environment $environment -Scopes $scopes
        } else {
            # Inform you to reconnect manually
            Write-Host "Information not confirmed. Either re-run the script to confirm again or call <Connect-MgGraph> to log in using a different account." -ForegroundColor Yellow
            exit 1
        }
    } else {
        # Attempt to connect with the scopes required by the phases the operator selected.
        Connect-MgGraph -Environment $environment -Scopes $scopes
        if (Get-MgContext) {
            # Recursive call to confirm your information
            ConnectToGraph -environment $environment -scopes $scopes
        } else {
            # Inform you to install Microsoft.Graph module if not connected
            Write-Host "If the Microsoft.Graph module is not installed, you need to install it to run this script." -ForegroundColor Yellow
            Write-Host "Run <Install-Module Microsoft.Graph -Scope CurrentUser> as an administrator." -ForegroundColor Yellow
            exit 1
        }
    }
}

# This function fetches a single page of items (groups or administrative units) from Microsoft Graph.
# It returns the items and the next page token if available.
function PageableFetchFromGraph {
    param (
        [string] $uri,
        [hashtable] $headers = @{},
        [string] $kindLabel = "item"
    )
    # Save the request args so we can retry the same call after throttling.
    $invokeArgs = @{
        Method = 'GET'
        Uri    = $uri
    }
    if ($headers.Count -gt 0) {
        $invokeArgs.Headers = $headers
    }
    try {
        # Make GET request to fetch items. AU queries require additional advanced query option headers.
        $response = Invoke-MgGraphRequest @invokeArgs
        $items = $response.value
        $nextPage = $response.'@odata.nextLink'
        Write-Host "Found $($items.count) $kindLabel(s) with dynamic membership." -ForegroundColor Green
        return $items, $nextPage
    } catch {
        # Handle any errors during fetch, including throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $null
        try { $statusCode = [int]$_.Exception.Response.StatusCode } catch {}
        if ($statusCode -eq 429) {
            try {
                # Sleep then retry the same request once after throttling
                HandleThrottling -ErrorRecord $_
                $response = Invoke-MgGraphRequest @invokeArgs
                $items = $response.value
                $nextPage = $response.'@odata.nextLink'
                Write-Host "Throttling mitigated. Found $($items.count) $kindLabel(s) with dynamic membership." -ForegroundColor Green
                return $items, $nextPage
            } catch {
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to fetch $kindLabel(s) with dynamic membership after throttling mitigation. URI: $uri" -ForegroundColor Red
                Write-Host "Error message: $($errorMessage)" -ForegroundColor Red
                return $null
            }
        }
        $responseBody = $null
        try {
            if ($_.ErrorDetails -and $_.ErrorDetails.Message) {
                $responseBody = $_.ErrorDetails.Message
            } elseif ($_.Exception.Response) {
                $stream = $_.Exception.Response.GetResponseStream()
                if ($stream) {
                    $reader = New-Object System.IO.StreamReader($stream)
                    $responseBody = $reader.ReadToEnd()
                }
            }
        } catch {}
        Write-Host "Failed to fetch $kindLabel(s) with dynamic membership. URI: $uri" -ForegroundColor Red
        Write-Host "Error message: $($errorMessage)" -ForegroundColor Red
        if ($responseBody) {
            Write-Host "Response body: $responseBody" -ForegroundColor Red
        }
        return $null
    }
}

# This function unpauses a specific group with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function UnpauseGroup {
    param (
        [string] $uri, [string] $groupId
    )
    $invokeArgs = @{
        Uri     = "$uri/$groupId"
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $unpauseDGjson
    }
    try {
        # Make PATCH request to unpause the group
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully unpaused group with dynamic membership. Id: $groupId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully unpaused group with dynamic membership. Id: $groupId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to unpause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to unpause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# This function unpauses a specific administrative unit with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function UnpauseAdministrativeUnit {
    param (
        [string] $uri, [string] $auId
    )
    $invokeArgs = @{
        Uri     = "$uri/$auId"
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $unpauseDGjson
    }
    try {
        # Make PATCH request to unpause the administrative unit
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully unpaused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully unpaused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to unpause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to unpause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# Unpauses up to 100 non-critical paused groups with dynamic membership in a single page.
# Returns a hashtable with Success and Failure counts, or $null if the fetch failed.
function UnpauseNonCriticalDynamicMembershipGroups {
    param (
        [string] $graphEndpoint
    )
    # Fetch up to 100 paused groups with dynamic membership (single page; cap at 100 by design).
    # The groupTypes/any(...) lambda filter requires advanced query options (ConsistencyLevel: eventual and $count=true).
    # The filter value must be pre-encoded because Invoke-MgGraphRequest double-encodes
    # URLs containing unescaped (, ), or : characters.
    $url = "$graphEndpoint/$graphApiVersion/groups"
    $filterValue = "groupTypes/any(c:c eq 'DynamicMembership') and membershipRuleProcessingState eq 'Paused'"
    $encodedFilter = [uri]::EscapeDataString($filterValue)
    $uri = "$url`?`$filter=$encodedFilter&`$count=true&`$top=100"
    $advancedHeaders = @{ ConsistencyLevel = 'eventual' }

    $successCount = 0
    $failureCount = 0

    $groupsData = PageableFetchFromGraph -uri $uri -headers $advancedHeaders -kindLabel "group"
    if ($groupsData -eq $null) {
        Write-Host "Phase 1 terminated due to fetch failure." -ForegroundColor Red
        return $null
    }
    $dynamicGroups = $groupsData[0]

    foreach ($group in $dynamicGroups) {
        if ($group.membershipRuleProcessingState -ceq "Paused") {
            $result = UnpauseGroup -uri $url -groupId $group.id
            if ($result) {
                $successCount++
            } else {
                $failureCount++
            }
        } else {
            # Skip groups that are not in "Paused" state
            Write-Host "Group skipped because it was found to be in $($group.membershipRuleProcessingState) state. Id: $($group.Id)" -ForegroundColor Yellow
        }
    }
    return @{ Success = $successCount; Failure = $failureCount }
}

# Unpauses up to 100 non-critical paused administrative units with dynamic membership in a single page.
# Returns a hashtable with Success and Failure counts, or $null if the fetch failed.
function UnpauseNonCriticalDynamicMembershipAdministrativeUnits {
    param (
        [string] $graphEndpoint
    )
    # Fetch up to 100 paused administrative units with dynamic membership (single page; cap at 100 by design).
    # AU dynamic membership queries require advanced query options ($count + ConsistencyLevel: eventual).
    $url = "$graphEndpoint/$graphApiVersion/directory/administrativeUnits"
    $filter = "?`$filter=membershipType eq 'Dynamic' and membershipRuleProcessingState eq 'Paused'"
    $countAndTop = "&`$count=true&`$top=100"
    $uri = "$url$filter$countAndTop"
    $headers = @{ ConsistencyLevel = 'eventual' }

    $successCount = 0
    $failureCount = 0

    $ausData = PageableFetchFromGraph -uri $uri -headers $headers -kindLabel "administrative unit"
    if ($ausData -eq $null) {
        Write-Host "Phase 2 terminated due to fetch failure." -ForegroundColor Red
        return $null
    }
    $dynamicAUs = $ausData[0]

    foreach ($au in $dynamicAUs) {
        if ($au.membershipRuleProcessingState -ceq "Paused") {
            $result = UnpauseAdministrativeUnit -uri $url -auId $au.id
            if ($result) {
                $successCount++
            } else {
                $failureCount++
            }
        } else {
            # Skip administrative units that are not in "Paused" state
            Write-Host "Administrative unit skipped because it was found to be in $($au.membershipRuleProcessingState) state. Id: $($au.Id)" -ForegroundColor Yellow
        }
    }
    return @{ Success = $successCount; Failure = $failureCount }
}

# Internal function to handle throttling and retry requests
# This function handles throttling by sleeping for the duration indicated by the Retry-After
# header (or a default of 60 seconds). The caller is responsible for retrying the request.
function HandleThrottling {
    param (
        [System.Management.Automation.ErrorRecord]$ErrorRecord
    )
    # Throttling occurred, extract Retry-After header if available
    $retryAfter = $ErrorRecord.Exception.Response.Headers.'Retry-After'
    if ($retryAfter) {
        Write-Host "Throttling detected. Waiting for $retryAfter seconds before retrying..."
        Start-Sleep -Seconds $retryAfter
    } else {
        # If Retry-After header is not available, wait for a default time
        Write-Host "Throttling detected. Waiting for default time i.e. 60 seconds before retrying..."
        Start-Sleep -Seconds 60  # Wait for 60 seconds by default
    }
}

# Function to prompt you to select the environment for determining the Microsoft Graph endpoint.
# This function helps you choose the correct environment and returns the corresponding endpoint.
function GetEnvironmentAndEndpoint {
    # Prompt you to select the environment
    try {
        $environmentChoice = Read-Host "Please select the environment (default is 'Global'): `nOptions: Global, USGov, USGovDoD, China"
    } catch {
        $environmentChoice = 'Global'
    }

    # Normalize the input to lower case
    $environmentChoice = $environmentChoice.Trim().ToLower()

    # Set the default environment to global
    $selectedEnvironment = "Global"

    # Map your choice to the corresponding environment
    switch ($environmentChoice) {
        "usgov" { $selectedEnvironment = "USGov" }
        "usgovdod" { $selectedEnvironment = "USGovDoD" }
        "china" { $selectedEnvironment = "China" }
        default { $selectedEnvironment = "Global" }
    }

    # Dictionary to map environment names to their endpoints
    $endpoints = @{
        "Global"   = "https://graph.microsoft.com"
        "USGov"    = "https://graph.microsoft.us"
        "USGovDoD" = "https://dod-graph.microsoft.us"
        "China"    = "https://microsoftgraph.chinacloudapi.cn"
    }

    $graphEndpoint = $endpoints[$selectedEnvironment]

    Write-Host "Environment Selected: $($selectedEnvironment). It maps to the graph endpoint: $($graphEndpoint)" -ForegroundColor Green

    # Return the selected environment and graph endpoint
    return @{ "SelectedEnvironment" = $selectedEnvironment; "GraphEndpoint" = $graphEndpoint }
}

#Running the script:
#Prompt you to confirm if you want to run the unpause non-critical flow.
Write-Host "DO YOU WANT TO UNPAUSE NON-CRITICAL DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND/OR ADMINISTRATIVE UNITS)?" -ForegroundColor Yellow
Write-Host "NOTE: If you are running this script as part of a mitigation exercise recommended by Microsoft, we strongly recommend performing this operation for BOTH groups and administrative units with dynamic membership (i.e. answer 'yes' to both phases below)." -ForegroundColor Cyan
Write-Host "NOTE THAT IF YOU UNPAUSE A HIGH NUMBER OF DYNAMIC MEMBERSHIP COLLECTIONS, YOU MIGHT SEE A MASSIVE BACKLOG OF PROCESSING." -ForegroundColor Yellow
Write-Host "IT IS HIGHLY RECOMMENDED THAT YOU MANUALLY UNPAUSE COLLECTIONS ON THE AZURE PORTAL BASED ON THEIR PRIORITY." -ForegroundColor Yellow
Write-Host "YOU CAN ONLY UNPAUSE UP TO 100 GROUPS AND 100 ADMINISTRATIVE UNITS PER RUN OF THIS SCRIPT." -ForegroundColor Yellow
Write-Host "PLEASE MAKE SURE YOU HAVE ALREADY UNPAUSED THE CRITICAL COLLECTIONS. THIS SCRIPT IS RECOMMENDED TO BE USED FOR NON-CRITICAL COLLECTIONS ONLY." -ForegroundColor Yellow
$input = Read-Host "Type 'yes' to confirm: "

#Global variable for JSON change.
$global:unpauseDGjson = '{"membershipRuleProcessingState":"On"}'

# Start the unpause non-critical flow if confirmed.
if (($input.Trim().ToLower() -eq "yes")) {
    $result = GetEnvironmentAndEndpoint
    $selectedEnvironment = $result.SelectedEnvironment
    $graphEndpoint = $result.GraphEndpoint
    # Determine which phases the operator wants to run BEFORE connecting to Microsoft Graph,
    # so we only request the scopes needed for the selected phases. This avoids requiring
    # AdministrativeUnit.ReadWrite.All consent when the operator only wants to run the groups phase.
    Write-Host "" -ForegroundColor Yellow
    Write-Host "Before connecting to Microsoft Graph, please choose which phases to include in this run." -ForegroundColor Yellow
    Write-Host "Only the scopes required by the selected phases will be requested." -ForegroundColor Yellow

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the groups phase (unpause up to 100 non-critical paused groups with dynamic membership)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $groupsPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runGroupsPhase = $groupsPhaseInput.Trim().ToLower() -eq "yes"

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the administrative units phase (unpause up to 100 non-critical paused administrative units with dynamic membership)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $ausPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runAusPhase = $ausPhaseInput.Trim().ToLower() -eq "yes"

    # Build the scopes list based on the selected phases.
    $selectedScopes = @()
    if ($runGroupsPhase) { $selectedScopes += "Group.ReadWrite.All" }
    if ($runAusPhase) { $selectedScopes += "AdministrativeUnit.ReadWrite.All" }

    if ($selectedScopes.Count -eq 0) {
        Write-Host "" -ForegroundColor Yellow
        Write-Host "No phases selected. Exiting without connecting to Microsoft Graph." -ForegroundColor Yellow
        exit 0
    }

    try {
        # Show the operator which scopes will be requested so they know what consent to expect.
        Write-Host "" -ForegroundColor Yellow
        Write-Host "Requesting the following Microsoft Graph scope(s) for this run: $($selectedScopes -join ', ')" -ForegroundColor Yellow

        # Connect to Microsoft Graph requesting only the scopes needed for the selected phases.
        ConnectToGraph -environment $selectedEnvironment -scopes $selectedScopes

        # Track per-phase results so a combined summary can be printed at the end.
        # Status values: "Skipped" (user declined), "FetchFailed" (LIST call failed), "Completed".
        $groupResult = $null
        $auResult = $null
        $groupPhaseStatus = "Skipped"
        $auPhaseStatus = "Skipped"

        # Phase 1: groups with dynamic membership
        if ($runGroupsPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 1 OF 2: GROUPS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $groupResult = UnpauseNonCriticalDynamicMembershipGroups -graphEndpoint $graphEndpoint
            if ($null -eq $groupResult) { $groupPhaseStatus = "FetchFailed" } else { $groupPhaseStatus = "Completed" }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 1 skipped. No groups with dynamic membership were unpaused." -ForegroundColor Yellow
        }

        # Phase 2: administrative units with dynamic membership
        if ($runAusPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 2 OF 2: ADMINISTRATIVE UNITS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $auResult = UnpauseNonCriticalDynamicMembershipAdministrativeUnits -graphEndpoint $graphEndpoint
            if ($null -eq $auResult) { $auPhaseStatus = "FetchFailed" } else { $auPhaseStatus = "Completed" }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 2 skipped. No administrative units with dynamic membership were unpaused." -ForegroundColor Yellow
        }

        # Combined summary
        Write-Host "" -ForegroundColor Yellow
        Write-Host "UnpauseNonCritical Operation Complete." -ForegroundColor Yellow
        if ($groupPhaseStatus -eq "Completed") {
            Write-Host "Groups with dynamic membership - Successfully Unpaused: $($groupResult.Success), Failed: $($groupResult.Failure)" -ForegroundColor Yellow
        } elseif ($groupPhaseStatus -eq "FetchFailed") {
            Write-Host "Groups with dynamic membership - Phase failed: could not list groups with dynamic membership. See error above." -ForegroundColor Red
        } else {
            Write-Host "Groups with dynamic membership - Phase skipped." -ForegroundColor Yellow
        }
        if ($auPhaseStatus -eq "Completed") {
            Write-Host "Administrative units with dynamic membership - Successfully Unpaused: $($auResult.Success), Failed: $($auResult.Failure)" -ForegroundColor Yellow
        } elseif ($auPhaseStatus -eq "FetchFailed") {
            Write-Host "Administrative units with dynamic membership - Phase failed: could not list administrative units with dynamic membership. See error above." -ForegroundColor Red
        } else {
            Write-Host "Administrative units with dynamic membership - Phase skipped." -ForegroundColor Yellow
        }
    } catch {
        # Handle any errors during the process
        $errorMessage = $_.Exception.Message
        Write-Host "UnpauseNonCritical operation failed. Error message: $($errorMessage)" -ForegroundColor Red
    }
# Inform you that the input was not accepted.
} else {
    Write-Host "UnpauseNonCritical script terminated. Please re-run the script and input 'yes' to run the UnpauseNonCritical script." -ForegroundColor Red
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/scripts/powershell-resume-specific-critical-dynamic-membership"} -->
## PowerShell サンプル - 動的メンバーシップを使用して特定の重要なグループと管理単位を再開する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/scripts/powershell-resume-specific-critical-dynamic-membership
- Service: entra-id / users
- Article date: 2026-06-11
- Summary: 一時停止後に、Microsoft Entra テナント内の特定の重要なグループと管理単位の動的メンバーシップ処理を再開する PowerShell サンプル。

一時停止から回復を開始する場合は、最も重要なコレクションを最初にオンラインに戻して、最も優先度の高いメンバーシップ ルールが他のすべての前に再開されるようにします。

### Overview

この PowerShell サンプルでは、ID で指定した重要なグループと管理単位の動的メンバーシップ ルール処理を再開します。 このサンプルは、最初に一時停止から回復する場合に使用します。重要でないコレクションを一括で再アクティブ化する前に、最も重要なコレクションを再開してください。 スクリプトは 2 つのフェーズで実行されるため、グループ、管理単位、またはその両方を再開できます。

### 重要な考慮事項

- PowerShell 5.1 (x64) 以降からスクリプトを実行します。
- Microsoft Graph PowerShell モジュールをインストールします。

    ```powershell
    Install-Module Microsoft.Graph -Scope CurrentUser
    ```
- 対象となるコレクションを管理できるアカウントでサインインします。 グループ フェーズには [Groups Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) Microsoft Entra ロールが必要であり、`Group.ReadWrite.All` Microsoft Graph スコープが要求されます。 管理単位フェーズには、[特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator) Microsoft Entra ロールが必要であり、`AdministrativeUnit.ReadWrite.All` スコープを要求します。 スクリプトは、実行するフェーズのスコープのみを要求します。
- このスクリプトは、最初に動的メンバーシップを持つグループ、次に動的メンバーシップを持つ管理単位という 2 つのフェーズで実行されます。 各フェーズの開始時に、スクリプトによって確認が求められます。 `yes`を入力して、そのフェーズを実行するか、他の何かを入力してスキップします。 これにより、1 回の実行でグループのみ、管理単位のみ、またはその両方を対象にできます。
- 大規模なテナントでは、このスクリプトによって Microsoft Graph のスロットリングが発生する可能性があります。 スクリプトには再試行処理が組み込まれているため、失敗するというよりは、実行時間が長くなると考えてください。 明示的なエラーが表示されない限り、実行中にスクリプトを取り消さないでください。
- 多数のコレクションを一度に再開すると、処理バックログを作成できます。 このサンプルを使用して最も優先度の高いグループと管理単位のみを再開し、このサンプルを再度実行するか、Microsoft Entra 管理センターで、優先順位の高い順序で残りのグループを再アクティブ化します。
- サービスが復旧できるように、処理を再開する前に、一時停止してから少なくとも 12 時間待ちます。
- 運用環境でスクリプトを実行する前に、テスト環境のすべての手順を確認します。

### サンプル スクリプト

```powershell
# DISCLAIMER:
# Copyright (c) Microsoft Corporation. All rights reserved. This
# script is made available to you without any express, implied or
# statutory warranty, not even the implied warranty of
# merchantability or fitness for a particular purpose, or the
# warranty of title or non-infringement. The entire risk of the
# use or the results from the use of this script remains with you.
#
# Usage: powershell.exe .\UnpauseSpecificCritical.ps1
# This script allows you to unpause specific critical dynamic membership collections (groups and administrative units).
# It can be helpful when you need to mitigate ongoing issues with your dynamic membership collections.

# This function checks if you are already connected to Microsoft Graph.
#     If yes,
#         It disconnects to fetch your current information. Then, it prompts you to confirm the your current information.
#             If you confirm, it reconnects to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#             If you don't confirm, it will not reconnect and will prompt you to connect manually using Connect-MgGraph.
#     If not,
#         It attempts to connect to Microsoft Graph with Group.ReadWrite.All and AdministrativeUnit.ReadWrite.All permissions.
#         If it fails to connect, it informs you that the Microsoft.Graph module might not be installed and provides the command to install it.
# Microsoft Graph API version used for all requests in this script.
# Change to "beta" if you need to call beta endpoints. Default: "v1.0".
$graphApiVersion = "v1.0"

function ConnectToGraph {
    param (
        [string]$environment,
        [string[]]$scopes
    )
    # Check if already connected to Microsoft Graph
    if (Get-MgContext) {
        # Disconnect to fetch your current information
        $accountInfo = Disconnect-MgGraph
        Write-Host "MAKE SURE THE BELOW ACCOUNT/CLIENT APPLICATION HAS THE RIGHT SET OF PERMISSIONS TO UNPAUSE DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND ADMINISTRATIVE UNITS)" -ForegroundColor Yellow
        Write-Host "Confirm the account: $($accountInfo.Account), TenantId: $($accountInfo.TenantId), and ClientId: $($accountInfo.ClientId)" -ForegroundColor Yellow
        $input = Read-Host "Type 'yes' to confirm: "
        if ($input.Trim().ToLower() -eq "yes") {
            # Reconnect with the scopes required by the phases the operator selected.
            Connect-MgGraph -Environment $environment -Scopes $scopes
        } else {
            # Inform you to reconnect manually
            Write-Host "Information not confirmed. Either re-run the script to confirm again or call <Connect-MgGraph> to log in using a different account." -ForegroundColor Yellow
            exit 1
        }
    } else {
        # Attempt to connect with the scopes required by the phases the operator selected.
        Connect-MgGraph -Environment $environment -Scopes $scopes
        if (Get-MgContext) {
            # Recursive call to confirm your information
            ConnectToGraph -environment $environment -scopes $scopes
        } else {
            # Inform you to install Microsoft.Graph module if not connected
            Write-Host "If the Microsoft.Graph module is not installed, you need to install it to run this script." -ForegroundColor Yellow
            Write-Host "Run <Install-Module Microsoft.Graph -Scope CurrentUser> as an administrator." -ForegroundColor Yellow
            exit 1
        }
    }
}

# This function unpauses a specific critical group with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function UnpauseGroup {
    param (
        [string] $uri, [string] $groupId
    )
    $invokeArgs = @{
        Uri     = $uri
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $unpauseDGjson
    }
    try {
        # Make PATCH request to unpause the group
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully unpaused group with dynamic membership. Id: $groupId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully unpaused group with dynamic membership. Id: $groupId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to unpause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to unpause group with dynamic membership. Id: $groupId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# This function unpauses a specific critical administrative unit with dynamic membership.
# It handles any errors, including throttling, and retries if necessary.
function UnpauseAdministrativeUnit {
    param (
        [string] $uri, [string] $auId
    )
    $invokeArgs = @{
        Uri     = $uri
        Method  = 'PATCH'
        Headers = @{ ConsistencyLevel = 'eventual' }
        Body    = $unpauseDGjson
    }
    try {
        # Make PATCH request to unpause the administrative unit
        Invoke-MgGraphRequest @invokeArgs
        Write-Host "Successfully unpaused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
        return $true
    } catch {
        # Handle errors and throttling
        $errorMessage = $_.Exception.Message
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode -eq 429) {
            try {
                # Handle throttling if status code is 429 by sleeping then retrying once
                HandleThrottling -ErrorRecord $_
                Invoke-MgGraphRequest @invokeArgs
                Write-Host "Throttling mitigated. Successfully unpaused administrative unit with dynamic membership. Id: $auId" -ForegroundColor Green
                return $true
            } catch {
                # Handle failure after throttling mitigation
                $errorMessage = $_.Exception.Message
                Write-Host "Failed to unpause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
                return $false
            }
        } else {
            # Handle other errors
            Write-Host "Failed to unpause administrative unit with dynamic membership. Id: $auId. Error message: $errorMessage" -ForegroundColor Red
            return $false
        }
    }
}

# Function to validate GUID
function Is-Guid {
    param (
        [string]$Guid
    )
    return [guid]::TryParse($Guid, [ref]([guid]::Empty))
}

# Helper that prompts you for a comma-separated list of IDs of a given kind, validates them as GUIDs,
# echoes them back for confirmation, and returns the validated list.
# Returns $null if the user enters no valid IDs or declines to confirm.
function PromptForIdList {
    param (
        [string] $kindLabel
    )
    Write-Host "Enter the $kindLabel IDs (separated by comma if multiple):" -ForegroundColor Yellow
    $inputIds = Read-Host

    if ([string]::IsNullOrWhiteSpace($inputIds)) {
        Write-Host "No IDs entered." -ForegroundColor Red
        return $null
    }

    # Extracting individual IDs
    $idList = $inputIds -split ',' | ForEach-Object { $_.Trim() } | Where-Object { $_ -ne '' }

    # Validate each ID and remove invalid ones
    $validIdList = @()
    $invalidIds = @()
    foreach ($id in $idList) {
        if (Is-Guid $id) {
            $validIdList += $id
        } else {
            $invalidIds += $id
        }
    }

    if ($invalidIds.Count -gt 0) {
        Write-Host "The following IDs are not valid GUIDs and will be removed:" -ForegroundColor Red
        foreach ($invalidId in $invalidIds) {
            Write-Host $invalidId
        }
    }

    if ($validIdList.Count -eq 0) {
        Write-Host "No valid IDs entered. Please re-run the script and provide IDs in GUID format." -ForegroundColor Red
        return $null
    }

    Write-Host "Confirm that you entered $($validIdList.count) valid $kindLabel ID(s), as displayed here:" -ForegroundColor Yellow
    foreach ($id in $validIdList) {
        Write-Host $id
    }

    $confirm = Read-Host "Type 'yes' to confirm: "
    if ($confirm.Trim().ToLower() -ne "yes") {
        return $null
    }

    Write-Host "You have confirmed the entry of valid $kindLabel IDs." -ForegroundColor Green
    return $validIdList
}

# Unpauses each of the supplied critical groups (by ID) if it is a dynamic membership group currently in "Paused" state.
# Returns a hashtable with Success and Failure counts.
function UnpauseSpecificCriticalDynamicMembershipGroups {
    param (
        [string] $graphEndpoint,
        [string[]] $groupIdList
    )
    $successCount = 0
    $failureCount = 0

    foreach ($groupId in $groupIdList) {
        # Fetch the group with the id given by you.
        $uri = "$graphEndpoint/$graphApiVersion/groups/$groupId"
        try {
            $group = Invoke-MgGraphRequest -Method GET -Uri $uri
        } catch {
            # Branch on status so 429 retries, 404 reports as not-found, and other
            # errors are not silently mislabelled as "Could not find".
            $errorMessage = $_.Exception.Message
            $statusCode = $null
            try { $statusCode = [int]$_.Exception.Response.StatusCode } catch {}
            if ($statusCode -eq 429) {
                try {
                    HandleThrottling -ErrorRecord $_
                    $group = Invoke-MgGraphRequest -Method GET -Uri $uri
                } catch {
                    $errorMessage = $_.Exception.Message
                    Write-Host "Failed to fetch the group with Id: $($groupId) after throttling mitigation. Error message: $($errorMessage)" -ForegroundColor Red
                    $failureCount++
                    continue
                }
            } elseif ($statusCode -eq 404) {
                Write-Host "Could not find the group with Id: $($groupId). Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            } else {
                $statusText = if ($statusCode) { "$statusCode" } else { "Unknown" }
                Write-Host "Failed to fetch the group with Id: $($groupId). Status: $statusText. Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            }
        }
        $isDynamic = $group.groupTypes -contains "DynamicMembership"
        $isPaused = $group.membershipRuleProcessingState -ceq "Paused"

        # Unpause this group if it has a dynamic membership rule and is currently paused.
        if ($isDynamic -and $isPaused) {
            $result = UnpauseGroup -uri $uri -groupId $group.id
            if ($result) {
                $successCount++
            } else {
                $failureCount++
            }
        } else {
            if (-not $isDynamic) {
                Write-Host "Group skipped because it was found to be not a group with dynamic membership. Id: $($group.Id)" -ForegroundColor Yellow
                continue
            }
            Write-Host "Group skipped because it was found to be in $($group.membershipRuleProcessingState) state. Id: $($group.Id)" -ForegroundColor Yellow
        }
    }
    return @{ Success = $successCount; Failure = $failureCount }
}

# Unpauses each of the supplied critical administrative units (by ID) if it is a dynamic membership AU currently in "Paused" state.
# Returns a hashtable with Success and Failure counts.
function UnpauseSpecificCriticalDynamicMembershipAdministrativeUnits {
    param (
        [string] $graphEndpoint,
        [string[]] $auIdList
    )
    $successCount = 0
    $failureCount = 0

    foreach ($auId in $auIdList) {
        # Fetch the administrative unit with the id given by you.
        $uri = "$graphEndpoint/$graphApiVersion/directory/administrativeUnits/$auId"
        try {
            $au = Invoke-MgGraphRequest -Method GET -Uri $uri
        } catch {
            $errorMessage = $_.Exception.Message
            $statusCode = $null
            try { $statusCode = [int]$_.Exception.Response.StatusCode } catch {}
            if ($statusCode -eq 429) {
                try {
                    HandleThrottling -ErrorRecord $_
                    $au = Invoke-MgGraphRequest -Method GET -Uri $uri
                } catch {
                    $errorMessage = $_.Exception.Message
                    Write-Host "Failed to fetch the administrative unit with Id: $($auId) after throttling mitigation. Error message: $($errorMessage)" -ForegroundColor Red
                    $failureCount++
                    continue
                }
            } elseif ($statusCode -eq 404) {
                Write-Host "Could not find the administrative unit with Id: $($auId). Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            } else {
                $statusText = if ($statusCode) { "$statusCode" } else { "Unknown" }
                Write-Host "Failed to fetch the administrative unit with Id: $($auId). Status: $statusText. Error message: $($errorMessage)" -ForegroundColor Red
                $failureCount++
                continue
            }
        }
        $isDynamic = $au.membershipType -ceq "Dynamic"
        $isPaused = $au.membershipRuleProcessingState -ceq "Paused"

        # Unpause this administrative unit if it has a dynamic membership rule and is currently paused.
        if ($isDynamic -and $isPaused) {
            $result = UnpauseAdministrativeUnit -uri $uri -auId $au.id
            if ($result) {
                $successCount++
            } else {
                $failureCount++
            }
        } else {
            if (-not $isDynamic) {
                Write-Host "Administrative unit skipped because it was found to be not an administrative unit with dynamic membership. Id: $($au.Id)" -ForegroundColor Yellow
                continue
            }
            Write-Host "Administrative unit skipped because it was found to be in $($au.membershipRuleProcessingState) state. Id: $($au.Id)" -ForegroundColor Yellow
        }
    }
    return @{ Success = $successCount; Failure = $failureCount }
}

# Internal function to handle throttling and retry requests
# This function handles throttling by sleeping for the duration indicated by the Retry-After
# header (or a default of 60 seconds). The caller is responsible for retrying the request.
function HandleThrottling {
    param (
        [System.Management.Automation.ErrorRecord]$ErrorRecord
    )
    # Throttling occurred, extract Retry-After header if available
    $retryAfter = $ErrorRecord.Exception.Response.Headers.'Retry-After'
    if ($retryAfter) {
        Write-Host "Throttling detected. Waiting for $retryAfter seconds before retrying..."
        Start-Sleep -Seconds $retryAfter
    } else {
        # If Retry-After header is not available, wait for a default time
        Write-Host "Throttling detected. Waiting for default time i.e. 60 seconds before retrying..."
        Start-Sleep -Seconds 60  # Wait for 60 seconds by default
    }
}

# Function to prompt you to select the environment for determining the Microsoft Graph endpoint.
# This function helps you choose the correct environment and returns the corresponding endpoint.
function GetEnvironmentAndEndpoint {
    # Prompt you to select the environment
    try {
        $environmentChoice = Read-Host "Please select the environment (default is 'Global'): `nOptions: Global, USGov, USGovDoD, China"
    } catch {
        $environmentChoice = 'Global'
    }

    # Normalize the input to lower case
    $environmentChoice = $environmentChoice.Trim().ToLower()

    # Set the default environment to global
    $selectedEnvironment = "Global"

    # Map your choice to the corresponding environment
    switch ($environmentChoice) {
        "usgov" { $selectedEnvironment = "USGov" }
        "usgovdod" { $selectedEnvironment = "USGovDoD" }
        "china" { $selectedEnvironment = "China" }
        default { $selectedEnvironment = "Global" }
    }

    # Dictionary to map environment names to their endpoints
    $endpoints = @{
        "Global"   = "https://graph.microsoft.com"
        "USGov"    = "https://graph.microsoft.us"
        "USGovDoD" = "https://dod-graph.microsoft.us"
        "China"    = "https://microsoftgraph.chinacloudapi.cn"
    }

    $graphEndpoint = $endpoints[$selectedEnvironment]

    Write-Host "Environment Selected: $($selectedEnvironment). It maps to the graph endpoint: $($graphEndpoint)" -ForegroundColor Green

    # Return the selected environment and graph endpoint
    return @{ "SelectedEnvironment" = $selectedEnvironment; "GraphEndpoint" = $graphEndpoint }
}

#Running the script:
#Prompt you to confirm if you want to run the unpause specific critical flow.
Write-Host "DO YOU WANT TO UNPAUSE SPECIFIC CRITICAL DYNAMIC MEMBERSHIP COLLECTIONS (GROUPS AND/OR ADMINISTRATIVE UNITS)?" -ForegroundColor Yellow
Write-Host "NOTE: If you are running this script as part of a mitigation exercise recommended by Microsoft, we strongly recommend performing this operation for BOTH groups and administrative units with dynamic membership (i.e. answer 'yes' to both phases below)." -ForegroundColor Cyan
Write-Host "NOTE THAT IF YOU UNPAUSE A HIGH NUMBER OF DYNAMIC MEMBERSHIP COLLECTIONS, YOU MIGHT SEE A MASSIVE BACKLOG OF PROCESSING." -ForegroundColor Yellow
Write-Host "IT IS HIGHLY RECOMMENDED THAT YOU MANUALLY UNPAUSE COLLECTIONS ON THE AZURE PORTAL BASED ON THEIR PRIORITY." -ForegroundColor Yellow
$input = Read-Host "Type 'yes' to confirm: "

#Global variable for JSON change.
$global:unpauseDGjson = '{"membershipRuleProcessingState":"On"}'

# Start the unpause specific critical flow if confirmed.
if (($input.Trim().ToLower() -eq "yes")) {
    $result = GetEnvironmentAndEndpoint
    $selectedEnvironment = $result.SelectedEnvironment
    $graphEndpoint = $result.GraphEndpoint
    # Determine which phases the operator wants to run BEFORE connecting to Microsoft Graph,
    # so we only request the scopes needed for the selected phases. This avoids requiring
    # AdministrativeUnit.ReadWrite.All consent when the operator only wants to run the groups phase.
    Write-Host "" -ForegroundColor Yellow
    Write-Host "Before connecting to Microsoft Graph, please choose which phases to include in this run." -ForegroundColor Yellow
    Write-Host "Only the scopes required by the selected phases will be requested." -ForegroundColor Yellow

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the groups phase (unpause specific critical groups with dynamic membership by ID)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $groupsPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runGroupsPhase = $groupsPhaseInput.Trim().ToLower() -eq "yes"

    Write-Host "" -ForegroundColor Yellow
    Write-Host "Include the administrative units phase (unpause specific critical administrative units with dynamic membership by ID)?" -ForegroundColor Yellow
    Write-Host "(Type 'yes' to include, anything else to skip.)" -ForegroundColor Yellow
    $ausPhaseInput = Read-Host "Type 'yes' to confirm: "
    $runAusPhase = $ausPhaseInput.Trim().ToLower() -eq "yes"

    # Build the scopes list based on the selected phases.
    $selectedScopes = @()
    if ($runGroupsPhase) { $selectedScopes += "Group.ReadWrite.All" }
    if ($runAusPhase) { $selectedScopes += "AdministrativeUnit.ReadWrite.All" }

    if ($selectedScopes.Count -eq 0) {
        Write-Host "" -ForegroundColor Yellow
        Write-Host "No phases selected. Exiting without connecting to Microsoft Graph." -ForegroundColor Yellow
        exit 0
    }

    try {
        # Show the operator which scopes will be requested so they know what consent to expect.
        Write-Host "" -ForegroundColor Yellow
        Write-Host "Requesting the following Microsoft Graph scope(s) for this run: $($selectedScopes -join ', ')" -ForegroundColor Yellow

        # Connect to Microsoft Graph requesting only the scopes needed for the selected phases.
        ConnectToGraph -environment $selectedEnvironment -scopes $selectedScopes

        # Track per-phase results so a combined summary can be printed at the end.
        $groupResult = $null
        $auResult = $null

        # Phase 1: groups with dynamic membership
        if ($runGroupsPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 1 OF 2: GROUPS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $groupIds = PromptForIdList -kindLabel "group"
            if ($groupIds -eq $null) {
                Write-Host "Phase 1 cancelled by user. No groups with dynamic membership were unpaused." -ForegroundColor Yellow
            } else {
                $groupResult = UnpauseSpecificCriticalDynamicMembershipGroups -graphEndpoint $graphEndpoint -groupIdList $groupIds
            }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 1 skipped. No groups with dynamic membership were unpaused." -ForegroundColor Yellow
        }

        # Phase 2: administrative units with dynamic membership
        if ($runAusPhase) {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "PHASE 2 OF 2: ADMINISTRATIVE UNITS WITH DYNAMIC MEMBERSHIP" -ForegroundColor Yellow
            $auIds = PromptForIdList -kindLabel "administrative unit"
            if ($auIds -eq $null) {
                Write-Host "Phase 2 cancelled by user. No administrative units with dynamic membership were unpaused." -ForegroundColor Yellow
            } else {
                $auResult = UnpauseSpecificCriticalDynamicMembershipAdministrativeUnits -graphEndpoint $graphEndpoint -auIdList $auIds
            }
        } else {
            Write-Host "" -ForegroundColor Yellow
            Write-Host "Phase 2 skipped. No administrative units with dynamic membership were unpaused." -ForegroundColor Yellow
        }

        # Combined summary
        Write-Host "" -ForegroundColor Yellow
        Write-Host "UnpauseSpecificCritical Operation Complete." -ForegroundColor Yellow
        if ($groupResult) {
            Write-Host "Groups with dynamic membership - Successfully Unpaused: $($groupResult.Success), Failed: $($groupResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Groups with dynamic membership - Phase skipped or cancelled." -ForegroundColor Yellow
        }
        if ($auResult) {
            Write-Host "Administrative units with dynamic membership - Successfully Unpaused: $($auResult.Success), Failed: $($auResult.Failure)" -ForegroundColor Yellow
        } else {
            Write-Host "Administrative units with dynamic membership - Phase skipped or cancelled." -ForegroundColor Yellow
        }
    } catch {
        # Handle any errors during the process
        $errorMessage = $_.Exception.Message
        Write-Host "UnpauseSpecificCritical operation failed. Error message: $($errorMessage)" -ForegroundColor Red
    }
# Inform you that the input was not accepted.
} else {
    Write-Host "UnpauseSpecificCritical script terminated. Please re-run the script and input 'yes' to run the UnpauseSpecificCritical script." -ForegroundColor Red
}
```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/signin-account-support"} -->
## Microsoft Entra サインイン ページで Microsoft アカウントが受け入れられるか - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/signin-account-support
- Service: entra-id / users
- Article date: 2024-12-16
- Summary: サインイン中にユーザー名の参照が画面のメッセージに反映されるしくみ

### 概要

Microsoft Entra の一部である Microsoft Entra ID の Microsoft 365 サインイン ページでは、職場または学校アカウントと Microsoft アカウントがサポートされていますが、ユーザーの状況に応じて、それはどちらか一方または両方になる可能性があります。 たとえば、Microsoft Entraサインイン ページでは次の情報がサポートされます。

- 両方の種類のアカウントからのサインインを受け入れるアプリ
- ゲストを受け入れる組織

### 識別

ユーザー名フィールドのヒント テキストを見ると、組織が使用しているサインイン ページで Microsoft アカウントがサポートされているかどうかを判断できます。 ヒント テキストに "メール、電話、または Skype" と表示されている場合は、サインイン ページで Microsoft アカウントがサポートされています。

[Image: アカウントのサインイン ページの違いのスクリーンショット。]

[追加のサインイン オプションは個人の Microsoft アカウントでのみ機能](https://azure.microsoft.com/updates/microsoft-account-signin-options/)し、職場または学校アカウントのリソースへのサインインには使用できません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/signin-realm-discovery"} -->
## サインイン時のユーザー名の参照 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/signin-realm-discovery
- Service: entra-id / users
- Article date: 2024-12-17
- Summary: Microsoft Entra ID でのサインイン時に画面上のメッセージングでユーザー名の参照が反映されるしくみ

### 概要

Microsoft Entra の一部である Microsoft Entra ID のサインイン動作は、新しい認証方法の余地を作り、使いやすさを向上させるために変更されています。 サインイン時に、ユーザーが認証する必要がある場所が Microsoft Entra ID によって決定されます。 Microsoft Entra ID は、サインイン ページで入力したユーザー名の組織とユーザー設定を読み取ることで、インテリジェントな意思決定を行います。 これは、FIDO 2.0 などの他の資格情報を有効にする、パスワードのない未来への一歩です。

### ホーム領域検出の動作

従来、ホーム領域の検出は、サインイン時に提供されるドメインまたはレガシ アプリケーションのホーム領域検出ポリシーに依存します。 たとえば、Microsoft Entra ユーザーがユーザー名を誤って入力したが、"contoso.com" などの組織のドメイン名が含まれていた場合、そのユーザーは引き続き組織の資格情報収集画面に送られます。 この方法では、個々のユーザー レベルでカスタマイズされたエクスペリエンスを使用できませんでした。

使いやすさを高め、幅広い資格情報をサポートするために、Microsoft Entra ID は別のプロセスを使用します。 サインイン時の Microsoft Entra ID のユーザー名参照動作は、入力されたユーザー名に基づいて組織レベルとユーザー レベルの設定をインテリジェントに評価します。 指定されたドメイン内でユーザー名が見つかった場合、ユーザーはそれに応じて指示されます。それ以外の場合、ユーザーは自分の資格情報を提供するようにリダイレクトされます。

この作業のもう 1 つの利点は、エラー メッセージの改善です。 Microsoft Entra ユーザーのみをサポートするアプリケーションにサインインするときのエラー メッセージの改善例を次に示します。

- ユーザー名の入力が間違っているか、ユーザー名がまだ Microsoft Entra ID に同期されていません。

    [Image: ユーザー名の入力ミスまたは見つからないスクリーンショット。]
- ドメイン名が誤って入力されています。

    [Image: ドメイン名の入力ミスまたは見つからないスクリーンショット。]
- ユーザーは、既知のコンシューマー ドメインを使用してサインインを試みます。

    [Image: 既知のコンシューマー ドメインでのサインインのスクリーンショット。]
- パスワードの入力が間違っていますが、ユーザー名は正確です。

    [Image: パスワードが正しいユーザー名と共に誤って入力されているスクリーンショット。]

重要

この機能は、フェデレーションを強制するために、古いドメイン レベルのホーム領域検出に依存するフェデレーション ドメインに影響を与える可能性があります。 この新しい動作に対するフェデレーション ドメインのサポートは現在使用できません。 それまでの間、一部の組織では、Microsoft Entra ID には存在しないが、適切なドメイン名を含むユーザー名でサインインするように従業員をトレーニングしました。これは、ドメイン名が現在組織のドメイン エンドポイントにユーザーをルーティングするためです。 新しいサインイン動作では、これを許可しません。 ユーザー名を修正するようにユーザーに通知され、Microsoft Entra ID に存在しないユーザー名でサインインすることはできません。 以前の動作に依存するプラクティスがある場合は、組織管理者が従業員のサインインと認証のドキュメントを更新し、Microsoft Entra ユーザー名を使用してサインインするように従業員をトレーニングすることが重要です。

新しい動作に関する懸念がある場合は、この記事の「**フィードバック**」セクションにコメントを残してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/strengthen-federated-sign-in-security"} -->
## フェデレーション サインインのセキュリティを強化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/strengthen-federated-sign-in-security
- Service: entra-id / users
- Article date: 2026-08-25
- Summary: フェデレーション トークン検証ポリシーが、Microsoft Entra IDでのクロスドメイン認証を防ぐことによってフェデレーション サインインセキュリティを強化する方法について説明します。

組織は、ユーザー認証のために外部 ID プロバイダー (Active Directory フェデレーション サービス (AD FS) (AD FS) や別の SAML ID プロバイダーなど) を信頼するようにMicrosoft Entra IDを構成できます。 フェデレーション認証は、Microsoftガイダンスに従って構成および管理されている場合、セキュリティで保護され、推奨されるデプロイ モデルのままです。 フェデレーション サインインでは、ID プロバイダーがユーザーを認証し、フェデレーション トークンを発行します。 Microsoft Entra IDは、受信トークンを検証し、テナント内のユーザー アカウントにマップします。 このプロセスの一環として、Entra は、トークン署名の検証、信頼された発行者とフェデレーション信頼の検証、トークンの有効期間の検証、アカウント マッピング、適用可能な条件付きアクセスと多要素認証の要件の評価など、一連のセキュリティとポリシーのチェックを実行します。 すべての検証とポリシーの要件が正常に満たされると、サインインが完了します。

フェデレーション トークン検証ポリシーにより、多層防御コントロールが追加されます。 このポリシーは、信頼されたフェデレーション領域によって表されるルート ドメインが、マップされたMicrosoft Entraユーザー アカウントのルート ドメインと一致していることを確認するのに役立ちます。 これにより、ドメイン整合性検証が追加され、複数のフェデレーション ドメインを使用するテナントの信頼境界が強化されます。

Important

`federatedTokenValidationPolicy` リソースとそれに関連するMicrosoft Graph API は、`/beta` エンドポイントを通じてプレビューで利用できます。 プレビュー API は変更される可能性があります。 運用アプリケーションでのこれらの API の使用はサポートされていません。

### フェデレーション ドメインのしくみ

フェデレーション ドメインは、認証が信頼された ID プロバイダーに委任される検証済みのMicrosoft Entra ドメインです。 フェデレーション構成は、 [`internalDomainFederation`](https://learn.microsoft.com/ja-jp/graph/api/resources/internaldomainfederation) オブジェクトによって表されます。 このオブジェクトは通常、一般的な AD FS やその他の ID プロバイダー構成エクスペリエンスを含む、フェデレーション セットアップの一部として作成されます。 管理者が直接作成しない場合があります。

#### フェデレーション サインインフローの簡略化

1. ユーザーが UPN ( `user@contoso.com`など) を入力します。
2. Microsoft Entra IDは、ドメインをフェデレーションとして識別し、構成された ID プロバイダーに認証を送信します。
3. ID プロバイダーはユーザーを認証し、署名付きフェデレーション トークンを返します。
4. Microsoft Entra IDは、署名、発行者、有効期間、信頼関係など、トークンを検証します。
5. Microsoft Entra ID は、アサーションを Microsoft Entra ユーザー アカウントにマッピングします。
6. Microsoft Entra IDは、適用可能な認証、条件付きアクセス、多要素認証の要件を適用します。
7. フェデレーション トークン検証ポリシーでは、サインインが完了する前に、追加のドメイン整合性検証を適用できます。

### フェデレーション サインインにおける既存の検証

フェデレーション認証は、Microsoft Entra IDと構成された ID プロバイダー間の確立された信頼に依存します。 フェデレーション サインインが成功する前に、Microsoft Entra IDは、トークンが信頼されたフェデレーション構成によって発行されたこと、およびトークンをテナント内のユーザーにマップできることを検証します。

テナントの構成とサインインコンテキストに応じて、Microsoft Entra IDでは、条件付きアクセス、多要素認証、サインイン リスク ポリシー、デバイス要件、アプリケーション承認などの追加の制御を適用することもできます。 フェデレーション トークン検証ポリシーでは、これらの既存の保護は置き換えられません。 これにより、使用されているフェデレーション信頼が、アクセスされるMicrosoft Entra アカウントのドメインと一致することを確認するのに役立つ検証レイヤーが追加されます。

### フェデレーション トークン検証ポリシーがフェデレーション セキュリティを強化する方法

[`federatedTokenValidationPolicy`](https://learn.microsoft.com/ja-jp/graph/api/resources/federatedtokenvalidationpolicy) リソースは、フェデレーション認証トークンの追加検証を制御します。 これは、オンプレミスのフェデレーション ドメインを対応するMicrosoft Entra ドメインにマップする`internalDomainFederation` オブジェクトで動作します。

検証が適用されると、Microsoft Entra IDは、フェデレーション アカウントまたは信頼された領域のルート ドメインと、マップされたMicrosoft Entra アカウントのルート ドメインを比較します。

ルート ドメインが一致する場合は、他のすべての認証とポリシーのチェックに従って、サインインを続行できます。 ルート ドメインが一致しない場合、Microsoft Entra IDは認証要求を拒否します。

| Scenario | 厳密なドメイン マッチングで予想される結果 |
| --- | --- |
| フェデレーション領域のルート ドメインは、ユーザーの UPN ルート ドメインと一致します。 | サインインは、他のすべての認証とポリシーのチェックに従って続行できます。 |
| フェデレーション領域のルート ドメインは、ユーザーの UPN ルート ドメインとは異なります。 | フェデレーション トークン検証ポリシーによってサインインがブロックされます。 |
| ユーザー UPN は、同じルート ドメインの子ドメインを使用します。 | ポリシーはルート ドメインを評価します。 子ドメインは、別のルート ドメインとして扱われません。 |
| カスタム構成では、クロスドメイン動作が明示的に許可されます。 | クロスドメイン サインインは許可できますが、テナントはこの追加のドメイン整合性保護を受け取りません。 |

この保護は、複数のフェデレーション ドメインを持つテナントで特に便利です。これは、侵害されたフェデレーション ID プロバイダーまたは正しく構成されていないフェデレーション ID プロバイダーの爆発半径を減らすのに役立ちます。 また、同じテナント内の独立して管理されるフェデレーション ドメイン間の信頼境界も強化されます。

#### クロスドメイン サインインとは

クロスドメイン サインインは、フェデレーション トークンが 1 つの信頼されたフェデレーション領域から受け入れられたが、UPN が別のルート ドメインに属しているMicrosoft Entra ユーザーにマップされるときに発生します。

たとえば、 `domainB.com` の信頼を通じて発行されたトークンは、UPN が `domainA.com`に属するユーザーにマップされます。

一部の組織では、レガシ、合併、買収、共存、または移行のシナリオで、意図的にクロスドメインの動作に依存しています。 フェデレーション トークン検証ポリシーは、管理者がこの動作をより明示的に特定して制御するのに役立ちます。

Note

比較はルート ドメインに基づいています。 `user@child.contoso.com`などの UPN には、ルート ドメイン`contoso.com`があります。 子ドメインとその親ルート ドメインは、このポリシー チェックの関連のないドメインとして自動的には扱われません。

### ポリシーによって提供される追加の保護

フェデレーション トークン検証ポリシーは、それぞれのドメインを個別に管理すべき場合に、ある信頼されたフェデレーション レルムが別のルート ドメインに割り当てられたユーザーとして認証するために使用されることを防止するのに役立ちます。

関連する攻撃シナリオには、次のようないくつかの前提条件が必要です。

- 複数のフェデレーション ドメインまたはフェデレーション領域を持つテナント。
- 侵害された、悪意のある、または不適切に制御された信頼できる ID プロバイダー。
- ターゲット ユーザーの不変 ID またはソース アンカーに関する知識。
- 任意のソース アンカー値を発行または構成できる ID プロバイダーまたは ID ストア。

ハイブリッド ID 環境では、ユーザーは不変 ID を使用してMicrosoft Entra アカウントにリンクされます。これは、オンプレミスのディレクトリとクラウド全体で永続的な識別子として機能します。 この識別子は、通常、Microsoft Entra IDのユーザー オブジェクトの`onPremisesImmutableId` プロパティに格納され、多くの場合、`ms-DS-ConsistencyGuid`属性に由来します。

すべての前提条件が満たされている場合、ある信頼されたフェデレーション領域を制御する攻撃者は、そのユーザーの [ソース アンカー](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#sourceanchor)をアサートすることによって、別の領域のユーザーにマップされるトークンを発行しようとする可能性があります。 フェデレーション トークン検証ポリシーは、信頼されたフェデレーション領域のルート ドメインがマップされたMicrosoft Entra ユーザーのルート ドメインと一致しない場合に、この種類のクロスドメイン アクセスをブロックするのに役立ちます。

#### 領域間偽装シナリオの例

Microsoft Entra テナントに次のものが含まれているとします。

- フェデレーション ドメイン `idpA.com`。
- フェデレーション ドメイン `idpB.com`。
- `idpA.com`の信頼された領域オブジェクト。
- `idpB.com`の信頼された領域オブジェクト。
- 外部の信頼された ID プロバイダー A。
- 外部の信頼できる ID プロバイダー B。

[Image: idpA.com と idpB.com のフェデレーション信頼を持つMicrosoft Entra テナントを示す図。ID プロバイダー B には、ID プロバイダー A の user1 と同じソース アンカーを持つ user3 が含まれています。]

正当な構成には、次のものが含まれます。

| 場所 | User | ソース アンカー |
| --- | --- | --- |
| Microsoft Entra テナント | `user1@idpA.com` | `user1A` |
| Microsoft Entra テナント | `user2@idpB.com` | `user2B` |
| ID プロバイダー A | `user1@idpA.com` | `user1A` |
| ID プロバイダー B | `user2@idpB.com` | `user2B` |

ID プロバイダー B が侵害された場合、または任意のソース アンカー値を許可した場合、攻撃者は別のユーザーを作成する可能性があります。

| 場所 | User | ソース アンカー |
| --- | --- | --- |
| ID プロバイダー B | `user3@idpB.com` | `user1A` |

ソース アンカー マッピングのみが成功し、ドメイン整合性検証が適用されていない場合、 `user3@idpB.com` の ID プロバイダー B によって発行されたトークンは、 `user1@idpA.com` のクラウド アカウントにマップされる可能性があります。

フェデレーション トークン検証ポリシーを設定してルート ドメインを検証すると、トークンのフェデレーション領域のルート ドメインとマップされたユーザーの UPN ルート ドメインが一致しないため、このクロスドメイン アクセスを防ぐことができます。

#### サブドメインのサインイン動作

`user@test.contoso.com`などのサブドメインからのサインインは、ドメインが同じルート ドメイン (`contoso.com` など) を共有している場合、クロスドメイン サインインとは見なされません。 フェデレーション トークン検証ポリシーが有効になっている場合、これらのサインインは引き続き許可されます。

ユーザーがサインインすると、Microsoft Entra IDは UPN からルート ドメインを抽出し、フェデレーション ID プロバイダーによって使用されるルート ドメインに対して検証します。 ルート ドメインが一致する場合、サインインは許可されます。 それ以外の場合、サインインはブロックされます。

サブドメインは通常、ルート ドメインと同じフェデレーション構成と発行者を共有するため、この方法では適切なドメイン検証が提供されます。 個別のフェデレーション構成が必要な場合は、最初 [にサブドメインをルート ドメインに昇格させます](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-verify-custom-subdomain#change-subdomain-to-a-root-domain)。 その後、ポリシーによって個別に検証されます。

### Microsoftがセキュリティを強化する方法

Microsoftでは、フェデレーション領域のルート ドメインとマップされたユーザー UPN ルート ドメインが一致しない場合にクロスドメイン サインインをブロックする、より厳密な既定値が使用されます。 この変更は、 `internalDomainFederation` オブジェクトが関連付けられているフェデレーション ドメインに適用されます。

ブロックされた認証要求は、**フェデレーション トークン検証ポリシーによってブロックされたサインイン`AADSTS5000820`エラーを返す必要があります。詳細については、管理者に問い合わせてください。**

カスタム構成では、意図的なシナリオでクロスドメイン サインインを許可できます。 ただし、クロスドメイン サインインを許可すると、以前の動作が復元され、この追加のドメイン整合性検証レイヤーが削除されます。

Important

テナントは、複数のフェデレーション領域を信頼できます。 厳密なドメイン マッチングは、他のトークン検証とアカウント マッピングのチェックが成功したという理由だけで、ある信頼された領域のトークンを使用して、別のルート ドメインに割り当てられたアカウントとしてサインインできないようにするのに役立ちます。

### 強制適用に備える

#### フェデレーション ドメインとフェデレーション構成を確認する

テナント内のすべてのフェデレーション ドメインを特定し、関連付けられた `internalDomainFederation` 構成を持つドメインを確認します。

#### 意図的なクロスドメイン サインインの依存関係を特定する

次のような意図的なクロスドメイン サインイン シナリオを確認します。

- 合併と買収。
- 共存環境。
- 共有 ID プロバイダー。
- 移行プロジェクト。
- UPN ドメインとは異なるフェデレーション領域を介してユーザーが認証を行う構成。

#### 代表的なサインイン シナリオを検証する

親ドメインと子ドメインの代表的なユーザーをテストして、一般的な構成の問題を特定します。 ルート ドメインの比較により、子ドメインの動作が別のルート ドメインとは異なる場合があります。

テストが成功しても、テナント内のすべてのサインイン パスが実行されたこと、または適用が有効になった後にすべてのユーザーが影響を受けないという保証はありません。

#### AADSTS5000820の監視

強制が有効になった後、Microsoft Entraサインイン ログで`AADSTS5000820`エラーを監視します。 影響を受けたユーザーを調査して、以前に検出されなかったクロスドメイン サインインの依存関係または構成の問題を特定します。

#### ポリシー例外を慎重に評価する

制限の緩い検証設定は、文書化されたビジネス要件がある場合にのみ構成します。 検証を無効または縮小すると、意図的なクロスドメイン シナリオの以前の動作を復元できますが、ドメインの信頼境界を強化するのに役立つ追加の保護レイヤーも削除されます。

クロスドメイン サインインを許可する組織は、多要素認証、条件付きアクセス、信頼された ID プロバイダーのガバナンス、特権アクセス制御、アプリケーション承認ポリシーなどの補正制御を評価する必要があります。

### ポリシー管理

Microsoft Graph プレビューでは、 に対する `federatedTokenValidationPolicy`、[List](https://learn.microsoft.com/ja-jp/graph/api/federatedtokenvalidationpolicy-get)、および [Update](https://learn.microsoft.com/ja-jp/graph/api/policyroot-list-federatedtokenvalidationpolicy) 操作が提供されます。

Get 操作および List 操作では、ドキュメントに記載されている最小権限のアクセス許可は `Policy.Read.All` です。 より高い特権レベルのアクセス許可が `Policy.ReadWrite.FedTokenValidation`。 委任されたアクセスに対してサポートされる組み込みロールは次のとおりです。

- セキュリティ管理者。
- ハイブリッド ID 管理者。
- 外部 ID プロバイダー管理者。

[`validatingDomains`](https://learn.microsoft.com/ja-jp/graph/api/resources/validatingdomains) リソースでは、次の用語が使用されます。

- **フェデレーション アカウントまたはドメイン:** 受信フェデレーション トークンに関連付けられているドメイン。これは、使用されている ID プロバイダーの信頼です。
- **マップされた Microsoft Entra アカウントまたはドメイン:** Microsoft Entra トークン サービスがユーザーの解決先として特定する Microsoft Entra ユーザーの UPN ドメイン。
- **検証:** 2 つのルート ドメインが一致することを確認します。 一致しない場合、サインインはブロックされます。

`rootDomains` プロパティは、検証を適用するドメインの種類を定義します。

#### `all`

テナント内のすべての検証済みドメインを検証します。 このオプションでは、ドメイン整合性の検証が最も広範になります。 このオプションは、クロスドメイン サインイン シナリオをブロックする組織や、最も強力な検証体制を必要とする組織に適しています。

- alice@domainA.com domainA.com の ID プロバイダー A のトークンを使用してサインインします。 ルート ドメインが一致するため、サインインが許可されます。
- alice@domainB.com domainA.com の ID プロバイダー A のトークンを使用してサインインします。 トークンは domainA.com に関連付けられていますが、マップされたアカウントは domainB.com に属しています。 サインインがブロックされています。

#### `allFederated`

マップされたMicrosoft Entra アカウント ドメインがフェデレーションされているユーザーのみを検証します。 フェデレーション ドメインは保護されますが、マネージド ドメインは検証から除外されます。 このオプションは、多くのフェデレーション ドメインがあり、マネージド アカウントを影響を受けずにフェデレーション信頼を保護する必要がある組織に適しています。

domainA.com と domainB.com がフェデレーションされ、domainC.com が管理されているとします。

- alice@domainB.com には、ID プロバイダー A からのトークンがあります。マップされたアカウント ドメインはフェデレーションされているため、検証が適用されます。 ルート ドメインが一致しないため、サインインはブロックされます。
- alice@domainA.com には、ID プロバイダー A からのトークンがあります。マップされたアカウント ドメインはフェデレーションされているため、検証が適用されます。 ルート ドメインが一致するため、サインインが許可されます。
- alice@domainC.com には、ID プロバイダー A からのトークンがあります。マップされたアカウント ドメインは管理されているため、検証は適用されません。サインインは許可されます。

#### `allManaged`

マップされたMicrosoft Entra アカウント ドメインが管理されているユーザーのみを検証します。 マネージド ドメインは保護されますが、フェデレーション ドメインは検証から除外されます。 このオプションは、組織が関連のないフェデレーション ドメインからトークンからマネージド ID を保護したいが、中断する準備ができていない過去のフェデレーション間クロスドメイン シナリオがある場合に適しています。

- alice@domainC.com には、ID プロバイダー A からのトークンがあります。マップされたアカウント ドメインは管理されているため、検証が適用されます。 ドメインが一致しないため、サインインはブロックされます。
- alice@domainD.com には、ID プロバイダー A からのトークンがあります。マップされたアカウント ドメインは管理されているため、検証が適用されます。 ドメインが一致しないため、サインインはブロックされます。
- alice@domainB.com には、ID プロバイダー A からのトークンがあります。マップされたアカウント ドメインはフェデレーションされているため、検証は適用されません。サインインは許可されます。

#### `enumerated`

ユーザーのマップされたアカウント ドメインが指定されたドメイン リストに含まれており、受信トークンのルート ドメインが一致しない場合は、サインインをブロックします。 このオプションでは、段階的なロールアウトがサポートされます。 たとえば、組織は、追加のテストまたは移行を必要とするドメインを一時的に除外しながら、適用の準備ができている選択したドメインを検証できます。

次の例では、3 つのドメインを検証します。

```json
{
  "validatingDomains": {
    "@odata.type": "#microsoft.graph.enumeratedDomains",
    "rootDomains": "enumerated",
    "domainNames": [
      "domainA.com",
      "domainB.com",
      "domainC.com"
    ]
  }
}
```

テナントに `domainD.com` と `domainE.com` も含まれているとします。

- `alice@domainA.com` には、ID プロバイダー B からのトークンがあります。マップされたアカウント ドメインは列挙リストに含まれているため、検証が適用されます。 ドメインが一致しないため、サインインはブロックされます。
- `alice@domainD.com` には、ID プロバイダー B からのトークンがあります。マップされたアカウント ドメインは列挙リストに含まれていないため、検証は適用されず、サインインが許可されます。

#### `allManagedAndEnumeratedFederated`

すべてのマネージド ドメインと、ポリシーに明示的に一覧表示されているフェデレーション ドメインのみを検証します。 一覧にないフェデレーション ドメインは、検証から除外されます。 このオプションでは、すべてのマネージド ドメインに対する即時保護と、フェデレーション ドメインの段階的なロールアウトが提供されます。

`domainA.com`、`domainB.com`、および`domainC.com`がフェデレーションされ、`domainD.com`と`domainE.com`が管理されているとします。 次の例では、2 つのフェデレーション ドメインを列挙します。

```json
{
  "validatingDomains": {
    "@odata.type": "#microsoft.graph.enumeratedDomains",
    "rootDomains": "allManagedAndEnumeratedFederated",
    "domainNames": [
      "domainA.com",
      "domainB.com"
    ]
  }
}
```

検証の適用対象:

- `domainD.com` と `domainE.com` は、どちらも管理されているためです。
- `domainA.com` と `domainB.com`。フェデレーション ドメインが列挙されているためです。

#### `none`

クロスドメイン検証を無効にします。 ルート ドメインの照合は行われません。 この設定を使用すると、特定のレガシ、取得、共存、移行のシナリオなど、意図的なクロスドメイン サインイン動作を保持できます。 ただし、フェデレーション トークン検証ポリシーによって提供される追加のドメイン整合性保護は削除されます。

たとえば、`alice@domainB.com``domainA.com`の ID プロバイダー A のトークンを使用してサインインします。 トークンは `domainA.com`に関連付けられていますが、マップされたユーザー アカウントは `domainB.com`に属します。 検証が無効になっているため、サインインが許可されます。

Warning

このオプションでは、Microsoft Entra IDでは、サインイン ドメインがマップされたアカウントの予想されるルート ドメインと一致するかどうかを確認する追加の検証は適用されません。 信頼されたフェデレーション ID プロバイダーが侵害された、悪意のある、または不適切に構成された場合、その影響は目的のドメイン スコープを超える可能性があります。 この設定は、文書化されたビジネス要件があり、適切な補正制御が実施されている場合にのみ使用します。

### よく寄せられる質問

#### これにより、ID プロバイダーがユーザーを認証する方法が変わりますか?

No. ID プロバイダーは、構成された認証を引き続き実行します。 追加の保護は、Microsoft Entra IDが返されたフェデレーション トークンを検証してマップするときに適用されます。

#### このポリシーは、テナントに複数のフェデレーション ドメインがある場合にのみ適用されますか?

`federatedTokenValidationPolicy`は、ドメインの数に関係なく存在できます。 トークンの信頼された領域のルート ドメインがマップされたユーザーの UPN ルート ドメインと異なる場合、そのクロスドメイン ブロック動作が関連するようになります。

#### サブドメインは別々のドメインとして扱われますか?

No. フェデレーション トークン検証ポリシーは、ルート ドメインを比較します。 `child.contoso.com` などの子ドメインは、この比較では `contoso.com` ルートドメインに解決されます。

#### サインインがブロックされるとどうなりますか?

予期されるエラーは `AADSTS5000820`であり、フェデレーション トークン検証ポリシーによってサインインがブロックされたことを示します。

#### 管理者はクロスドメイン サインインを許可できますか?

Yes. 管理者は、[`none`](https://learn.microsoft.com/ja-jp/entra/identity/users/strengthen-federated-sign-in-security#none)を含む制限の緩い設定を使用するように`validatingDomains` リソースを構成することで、クロスドメイン サインインを許可できます。

この構成は、意図的なクロスドメイン シナリオに使用できますが、追加のドメイン整合性検証レイヤーは削除されます。 組織では、必要な場合にのみ制限の緩い設定を使用し、適切な補正制御を適用する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-bulk-add"} -->
## Azure portal でユーザーを一括作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-add
- Service: entra-id / users
- Article date: 2026-09-25
- Summary: Microsoft Entra ID でユーザーを一括追加する

Microsoft Entra の一部である Microsoft Entra ID では、ユーザーの一括作成および削除操作がサポートされており、ユーザーのリストのダウンロードがサポートされています。 Microsoft Entra ID からダウンロードできるコンマ区切り値 (CSV) テンプレートを入力するだけです。

### 前提条件

Microsoft Entra 管理センターでユーザーを一括作成するには、少なくともユーザー管理者としてサインインします。

### CSV テンプレートについて

一括アップロード CSV テンプレートをダウンロードして入力すると、Microsoft Entra ユーザーを正常に一括作成できます。 ダウンロードする CSV テンプレートは、次の例のようになります。

[Image: 必要な [名前]、[ユーザー名]、[初期パスワード] 列を含む一括作成 CSV テンプレートのスクリーンショット。]

警告

`userPrincipalName`ファイル拡張子を追加し、`passwordProfile` および `.csv` の前にある先頭スペースを削除してください。

#### CSV テンプレートの構造

ダウンロードした CSV テンプレート内の行は次のとおりです。

- **列見出し**: ダウンロードしたとおりに列見出しを保持します。 必要な列は、 `Name [displayName] Required`、 `User name [userPrincipalName] Required`、および `Initial password [passwordProfile] Required`です。
- **[Examples]\(例**\) 行: CSV ファイルに examples 行を保持できます。 次の行に作成するユーザーを追加します。

#### その他のガイダンス

- ダウンロードしたとおりに列ヘッダーを保持します。 テンプレートにバージョン行が含まれている場合は、保持します。
- 必須の列が最初に示されています。
- テンプレートに新しい列を追加することはお勧めしません。 列を追加しても無視され、処理されません。
- できる限り、常に最新バージョンの CSV テンプレートをダウンロードすることをお勧めします。

- フィールドの前後に意図しない空白がないか確認してください。 **ユーザー プリンシパル名**の場合、そのような空白があると、インポートに失敗します。
- **[初期パスワード]** の値が、現在アクティブな[パスワード ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-sspr-policy#username-policies)に準拠していることを確認します。
- 1 行に 1 人のユーザーを入力します。

#### CSV ファイルの例

アップロードの準備ができている完成した CSV ファイルの例を次に示します。

```csv
Name [displayName] Required,User name [userPrincipalName] Required,Initial password [passwordProfile] Required
Chris Green,chris@contoso.com,Example-Password-Only!1
Alain Charon,alain@contoso.com,Example-Password-Only!1
Isabella Simonsen,isabella@contoso.com,Example-Password-Only!1
Joseph Price,joseph@contoso.com,Example-Password-Only!1
```

Important

**[名前]**、[**ユーザー名**]、[**初期パスワード**] のみが必要です。 他のすべての列は省略可能であり、空のままにすることができます。

### ユーザーを一括で作成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. **[Entra ID]**&gt;**[ユーザー]**&gt;**[一括作成]** に移動します。
3. **[ユーザーの一括作成]** ページで **[ダウンロード]** を選択し、ユーザー プロパティの有効な CSV (コンマ区切り値) ファイルを取得し、作成するユーザーを追加します。

    [Image: 追加するユーザーが列記されているローカル CSV ファイルを選ぶ方法を示すスクリーンショット。]
4. CSV ファイルを開き、ダウンロードしたとおりに列ヘッダーを保持し、作成するユーザーごとに行を追加します。 必要な値は **、名前**、 **ユーザー名**、 **および初期パスワード**のみです。 そのうえでファイルを保存します。
5. **[ユーザーの一括作成]** ページの [CSV ファイルをアップロード] で、そのファイルを参照します。 ファイルを選択し、[送信] 選択すると、CSV ファイルの検証が開始されます。
6. ファイルの内容が検証された後、"**ファイルが正常にアップロードされました**" と表示されます。 エラーが存在する場合は、ジョブを送信する前にそれらを修正する必要があります。
7. ファイルが検証に合格したら、 **[送信]** を選択して、新しいユーザーをインポートする一括操作を開始します。
8. インポート操作が完了すると、一括操作ジョブの状態の通知が表示されます。

注

一括作成操作では、CSV ファイルで指定されたパスワードを使用して内部メンバー アカウントが作成されます。 招待メールは新しいユーザーに送信されません。 独自のプロセスを通じて、サインイン資格情報をユーザーに伝える必要があります。 外部ゲスト ユーザーを一括招待し、招待メールを送信するには、「 [B2B ユーザーを一括招待](https://learn.microsoft.com/ja-jp/entra/external-id/tutorial-bulk-invite)する」を参照してください。

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして表示できます。 このファイルには、各エラーの理由が含まれています。 ファイルの送信は、指定されたテンプレートと一致し、正確な列名が含まれている必要があります。

一括操作の制限の詳細については、「一括インポート サービスの制限」を参照してください。

### 状態の確認

**[一括操作の結果]** ページでは、保留中のすべての一括要求の状態を確認できます。

[Image: 一括操作の結果ページで操作の状態を調べる方法を示すスクリーンショット。]

次に、作成したユーザーが Microsoft Entra 管理センターまたは PowerShell を使用して Microsoft Entra 組織に存在することを確認できます。

### ユーザーを確認する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. [Microsoft Entra ID] を選びます。
3. **[ユーザー]**&gt;**[すべてのユーザー]** の順に選択します。
4. **[表示]** で **[すべてのユーザー]** を選択し、作成したユーザーが一覧に表示されていることを確認します。

#### PowerShell でユーザーを確認する

次のコマンドを実行します。

```PowerShell
Get-MgUser -Filter "UserType eq 'Member'"
```

作成したユーザーがリストされているのを確認できます。

### 一括インポート サービスの制限

注

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合に問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターを絞り込むことで、基本的に一括操作によって返されるデータを制限します。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-bulk-delete"} -->
## Microsoft Entra IDでユーザーを一括削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-delete
- Service: entra-id / users
- Article date: 2026-09-25
- Summary: Microsoft Entra IDでユーザーを一括削除する

Microsoft Entraの一部であるMicrosoft Entra IDの管理センターを使用すると、コンマ区切り値 (CSV) ファイルを使用してユーザーを一括削除することで、多数のユーザーを削除できます。

### 前提条件

Microsoft Entra 管理センター内のユーザーを一括削除するには、少なくともユーザー管理者としてサインインします。

### ユーザーの一括削除

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [User Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) としてサインインします。
2. **Microsoft Entra ID** を選択します。
3. **ユーザー**&gt;**すべてのユーザー**&gt;**一括操作**&gt;**一括削除** を選択します。

    [Image: [一括削除] オプションが選ばれている [ユーザー] ページのスクリーンショット。]
4. **[Bulk delete user](https://learn.microsoft.com/ja-jp/entra/identity/users/ユーザーの一括削除)** ページで、 **[Download](https://learn.microsoft.com/ja-jp/entra/identity/users/ダウンロード)** をクリックして、最新バージョンの CSV テンプレートをダウンロードします。
5. CSV ファイルを開き、ダウンロードしたとおりに列ヘッダーを保持し、削除する各ユーザーの行を追加します。 ユーザーごとに、 **ユーザー プリンシパル名** または **オブジェクト ID を**入力します。 ファイルを保存します。
6. **[ユーザーの一括削除]** ページの **[CSV ファイルをアップロード]** で、そのファイルを参照します。 ファイルを選択して **[送信]** を選択すると、CSV ファイルの検証が開始されます。
7. ファイルの内容が検証されると、"**ファイルが正常にアップロードされました**" と表示されます。 エラーが存在する場合は、ジョブを送信する前にそれらを修正する必要があります。
8. ファイルの検証に合格したら、**[送信]** を選択して、ユーザーを削除する一括操作を開始してください。
9. 削除操作が完了すると、一括操作が成功したことを示す通知が表示されます。

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして確認できます。 このファイルには、各エラーの理由が含まれています。 ファイルの送信は、指定されたテンプレートと一致し、正確な列名が含まれている必要があります。

一括操作の制限の詳細については、「一括削除サービスの制限」を参照してください。

### CSV テンプレートの構造

以下のダウンロードした CSV テンプレートの例の行は次のとおりです。

- **列見出し**: ダウンロードしたとおりに `UserPrincipalName or Object ID [UPN or objectId] Required` を保持します。
- **[Examples]\(例**\) 行: CSV ファイルに examples 行を保持できます。 削除するユーザーを次の行に追加します。 ユーザーごとに、ユーザー プリンシパル名 (UPN) またはオブジェクト ID を入力します。

[Image: 必要な UserPrincipalName 列またはオブジェクト ID 列を含む一括削除 CSV テンプレートのスクリーンショット。]

#### CSV ファイルの例

アップロードの準備ができている完成した CSV ファイルの例を次に示します。

```csv
UserPrincipalName or Object ID [UPN or objectId] Required
chris@contoso.com
alain@contoso.com
00aa00aa-bb11-cc22-dd33-44ee44ee44ee
```

#### CSV テンプレートの追加ガイド

- ダウンロードしたとおりに列ヘッダーを保持します。 テンプレートにバージョン行が含まれている場合は、保持します。
- 必須の列が最初に示されています。
- テンプレートに新しい列を追加することはお勧めしません。 列を追加しても無視され、処理されません。
- できるだけ頻繁に最新バーションの CSV テンプレートをダウンロードすることをお勧めします。

- 1 行に 1 人のユーザーを入力します。

### 状態の確認

**[一括操作の結果]** ページでは、保留中のすべての一括要求の状態を確認できます。

[Image: [一括操作の結果] ページで削除の状態を確認するスクリーンショット。]

次に、削除したユーザーがポータルまたは PowerShell を使用して、Microsoft Entra組織に存在することを確認できます。

### 削除されたユーザーを確認する

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [User Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) としてサインインします。
2. **Microsoft Entra ID** を選択します。
3. **すべてのユーザー** のみを選択し、削除したユーザーがリストされなくなったことを確認します。

#### PowerShell で削除済みユーザーを確認する

次のコマンドを実行します。

```PowerShell
Get-MgUser -Filter "UserType eq 'Member'"
```

削除したユーザーがリストされなくなったことを確認します。

### 一括削除サービスの制限

注

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合に問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターを絞り込むことで、基本的に一括操作によって返されるデータを制限します。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-bulk-download"} -->
## Azure portal でユーザーの一覧をダウンロードする (プレビュー) - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-download
- Service: entra-id / users
- Article date: 2025-08-19
- Summary: Microsoft Entra ID の Azure 管理センターでユーザー レコードを一括ダウンロードします。

### 概要

Microsoft Entra の一部である Microsoft Entra ID では、ユーザーの一覧を一括にダウンロードする操作がサポートされています。

### 必要なアクセス許可

管理者ユーザーと標準ユーザーのどちらもユーザーの一覧をダウンロードできます。

### ユーザーの一覧をダウンロードするには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. [Microsoft Entra ID] を選びます。
3. **ユーザー**&gt;**すべてのユーザー**&gt;**ユーザーをダウンロード** を選択します。 既定では、すべてのユーザー プロファイルがエクスポートされます。
4. **[ユーザーをダウンロード]** ページで **[開始]** を選択し、ユーザー プロファイルのプロパティが一覧表示された CSV ファイルを受け取ります。 エラーがある場合は、 **[一括操作の結果]** ページで結果ファイルをダウンロードして表示できます。 このファイルには、各エラーの理由が含まれています。

    [Image: ダウンロードするユーザーを一覧表示する場所を選択するスクリーンショット。]

メモ

ダウンロード ファイルには、適用したフィルターのスコープに基づいてフィルター処理されたユーザーの一覧が含まれます。

次のユーザー属性が含まれます。

- `userPrincipalName`
- `displayName`
- `surname`
- `mail`
- `givenName`
- `objectId`
- `userType`
- `jobTitle`
- `department`
- `accountEnabled`
- `usageLocation`
- `streetAddress`
- `state`
- `country`
- `physicalDeliveryOfficeName`
- `city`
- `postalCode`
- `telephoneNumber`
- `mobile`
- `authenticationAlternativePhoneNumber`
- `authenticationEmail`
- `alternateEmailAddress`
- `ageGroup`
- `consentProvidedForMinor`
- `legalAgeGroupClassification`

### 状態の確認

**[一括操作の結果]** ページでは、保留中の一括要求の状態を確認できます。

[Image: [一括操作の結果] ページの状態を示すスクリーンショット。]

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして確認できます。 このファイルには、各エラーの理由が含まれています。 ファイルの送信は、指定されたテンプレートと一致し、正確な列名が含まれている必要があります。 一括操作の制限の詳細については、「一括ダウンロード サービスの制限」を参照してください。

### 一括ダウンロード サービスの制限

メモ

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合、問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターで絞り込むことは、実質的には一括操作によって返されるデータを制限することになります。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。

### Microsoft Entra 管理センターでの一括ユーザー ダウンロードの改善 (プレビュー)

強化された一括ユーザー ダウンロード エクスペリエンスには、次のものが含まれます。

**エクスポート用のカスタマイズ可能な列**: 以前は、ユーザーのエクスポートには定義済みの属性の固定セットのみが含まれていました。 この更新プログラムでは、管理者はユーザー リスト ビューの列をカスタマイズでき、エクスポートによって選択した列がミラー化されます。 これにより、IT 管理者は、ダウンロードするデータをより詳細に制御し、関連性を高めます。

**拡張された属性カバレッジ**: エクスポート可能なリストに 27 個の新しいユーザー属性が追加され、より深い分析情報とよりカスタマイズされたエクスポートが可能になりました。 これらの新しくエクスポート可能な属性の完全な一覧を参照してください。

1. 割り当てられたライセンス
2. 承認情報
3. 従業員 ID
4. 従業員の雇用日
5. 従業員の組織データ
6. 従業員の種類
7. 拡張属性
8. 外部ユーザー状態の変更日時
9. FAX 番号
10. IM アドレス
11. パスワードの最終変更日時
12. 最後の対話型サインイン日
13. 非インタラクティブな最後のサインイン日
14. オンプレミスの SAM アカウント名
15. オンプレミスの識別名
16. オンプレミス ドメイン名
17. オンプレミスの不変 ID
18. オンプレミスの最終同期日時
19. オンプレミスのプロビジョニング エラー
20. オンプレミスのセキュリティ識別子
21. オンプレミスのユーザー プリンシパル名
22. パスワード ポリシー
23. パスワード プロファイル
24. 優先されるデータの場所
25. 優先言語
26. プロキシ アドレス
27. ログイン セッションが日時から有効

メモ

複合プロパティから派生した列は、複合プロパティ全体としてエクスポートされます。 たとえば、[ **最後の対話型サインイン日** ] 列をダウンロードすると、signInActivity プロパティ全体と関連するすべてのデータ フィールドが CSV ファイルに含められます。

#### パフォーマンスの向上

一括ダウンロード プロセスは最大で 12 ~ 15 倍高速になり、大規模なユーザー リストのエクスペリエンスと効率が向上しました。 この改善により、現在存在するサービスの制限も排除され、一括操作が 1 時間以内に完了しない場合に問題が発生します。

#### ユーザー エクスペリエンス

1. [**すべての**ユーザー] ページのコマンド バーから [**ユーザーのダウンロード (プレビュー)]** ボタンを選択します。

    [Image: [ユーザーのダウンロード] オプションのスクリーンショット]
2. コンテキスト ウィンドウが開いたら、[ **ダウンロードの開始** ] を選択し、成功したら [ **ファイルの準備完了] を選択します。CSV をダウンロードするには、ここをクリック** してください。

    [Image: ダウンロードを開始するダウンロード ボタンのスクリーンショット]
3. 左側のメニューで、 **一括操作の結果 (プレビュー)** に移動して、一括ダウンロード要求の状態を表示します。 プレビューのダウンロード結果のみがこのプレビュー タブに表示されます。

    [Image: [一括操作の結果] ページでの状態の確認のスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-bulk-restore"} -->
## Azure portal で削除済みユーザーを一括復元する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-bulk-restore
- Service: entra-id / users
- Article date: 2026-09-25
- Summary: Microsoft Entra ID で Azure portal で、削除されたユーザーを一括復元する

Microsoft Entra ID では、ユーザーの一括復元操作と、ユーザー、グループ、およびグループ メンバーのリストのダウンロードがサポートされています。

### 前提条件

Microsoft Entra 管理センターでユーザーを一括復元するには、少なくともユーザー管理者としてサインインします。

### CSV テンプレートについて

CSV テンプレートをダウンロードして入力し、Microsoft Entra ユーザーを正常に一括復元できるようにします。 ダウンロードする CSV テンプレートは、次の例のようになります。

[Image: 必要な [オブジェクト ID] 列を含む一括復元 CSV テンプレートのスクリーンショット。]

#### CSV テンプレートの構造

ダウンロードした CSV テンプレート内の行は次のとおりです。

- **列見出し**: ダウンロードしたとおりに `Object ID [objectId] Required` を保持します。
- **[Examples]\(例**\) 行: CSV ファイルに examples 行を保持できます。 次の行に復元するユーザーのオブジェクト ID を追加します。

#### CSV ファイルの例

アップロードの準備ができている完成した CSV ファイルの例を次に示します。

```csv
Object ID [objectId] Required
aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb
00aa00aa-bb11-cc22-dd33-44ee44ee44ee
11bb11bb-cc22-dd33-ee44-55ff55ff55ff
22cc22cc-dd33-ee44-ff55-66aa66aa66aa
```

#### その他のガイダンス

- ダウンロードしたとおりに列ヘッダーを保持します。 テンプレートにバージョン行が含まれている場合は、保持します。
- 必須の列が最初に示されています。
- テンプレートに新しい列を追加することはお勧めしません。 列を追加しても無視され、処理されません。
- 出来る限り、常に最新の CSV テンプレートをダウンロードすることをお勧めします。

### ユーザーの一括復元

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. [Microsoft Entra ID] を選びます。
3. [ **ユーザー**&gt;**すべてのユーザー**&gt;**削除済み**] を選択します。
4. **[削除済みのユーザー]** ページで、 **[一括復元]** を選択して、復元するユーザーのプロパティの有効な CSV ファイルをアップロードします。

    [Image: [削除済みのユーザー] ページでの一括復元コマンドの選択のスクリーンショット。]
5. CSV テンプレートを開き、ダウンロードしたとおりに列ヘッダーを保持し、復元する各ユーザーの行を追加します。 必要な値は **オブジェクト ID** のみです。 そのうえでファイルを保存します。
6. **[一括復元]** ページの **[CSV ファイルをアップロード]** の下で、ファイルまでブラウズします。 ファイルを選択して **[送信]** を選択すると、CSV ファイルの検証が開始されます。
7. ファイルの内容が検証されると、"**ファイルが正常にアップロードされました**" と表示されます。 エラーが存在する場合は、ジョブを送信する前にそれらを修正する必要があります。
8. ファイルの検証に合格したら、 **[送信]** を選択して、ユーザーを復元する一括操作を開始します。
9. 復元操作が完了すると、一括操作が成功したという通知が表示されます。

エラーが発生する場合は、**[一括操作の結果]** ページで結果ファイルをダウンロードして確認できます。 このファイルには、各エラーの理由が含まれています。 ファイルの送信は、指定されたテンプレートと一致し、正確な列名が含まれている必要があります。

一括操作の制限の詳細については、「一括復元サービスの制限」を参照してください。

### 状態の確認

**[一括操作の結果]** ページでは、保留中のすべての一括要求の状態を確認できます。

[Image: [一括操作の結果] ページでの状態の確認のスクリーンショット。]

次に、復元したユーザーが Microsoft Entra 組織に存在するかどうかを、Microsoft Entra ID または PowerShell を使用して確認します。

### Microsoft Entra 管理センターで復元されたユーザーを表示する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)以上でサインインしてください。
2. [Microsoft Entra ID] を選びます。
3. [ **管理**] で、[ **ユーザー**&gt;**すべてのユーザー**] を選択します。
4. **[表示]** で **[すべてのユーザー]** を選択し、復元したユーザーがリストされていることを確認します。

#### PowerShell でユーザーを表示する

次のコマンドを実行します。

```PowerShell
Get-MgUser -Filter "UserType eq 'Member'"
```

復元したユーザーがリストされていることがわかります。

注

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨の最新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正プログラムに限定されます。 非推奨になるモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) を使用するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、「[移行に関する FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq)」を参照してください。 *注:* バージョン 1.0.x の MSOnline では、2024 年 6 月 30 日以降に中断が発生する可能性があります。

### 一括復元サービスの制限

注

インポートや作成などの一括操作を実行するときに、一括操作が 1 時間以内に完了しない場合に問題が発生する可能性があります。 この問題を回避するには、バッチごとに処理されるレコードの数を分割することをお勧めします。 たとえば、エクスポートの開始前に、グループの種類またはユーザー名でフィルター処理して結果セットを制限し、結果のサイズを小さくすることができます。 フィルターを絞り込むことで、基本的に一括操作によって返されるデータを制限します。 詳しくは、[一括操作サービスの制限](https://learn.microsoft.com/ja-jp/entra/fundamentals/bulk-operations-service-limitations)に関する記事をご覧ください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-close-account"} -->
## アンマネージド Microsoft Entra 組織の職場または学校アカウントを削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-close-account
- Service: entra-id / users
- Article date: 2021-05-04
- Summary: アンマネージド Microsoft Entra ID の職場または学校アカウントを削除する方法。

### 概要

Microsoft Entra ID の管理されていない組織 (テナント) のユーザーであり、その組織のアプリを使用したり、関連付けを維持したりする必要がなくなった場合は、いつでもアカウントを閉じることができます。 管理されていない組織には管理者がいません。 アンマネージド組織のユーザーは自分のアカウントをご自身で削除できます。その際、管理者に連絡する必要はありません。

アンマネージド組織のユーザーは、多くの場合、セルフサービス サインアップ中に作成されます。 これが発生する例としては、組織のインフォメーション ワーカーが無料サービスにサインアップする場合が挙げられます。 セルフサービス サインアップの詳細については、「[Microsoft Entra ID のセルフサービス サインアップについて](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-self-service-signup)」を参照してください。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### 開始する前に

ご自身のアカウントを削除するには、事前に次の項目を確認しておく必要があります。

- アンマネージド Microsoft Entra 組織のユーザーであることを確認します。 マネージド組織のユーザーの場合、自分のアカウントを削除することはできません。 マネージド組織のユーザーがアカウントを削除する必要がある場合は、管理者に連絡する必要があります。 ご自身がアンマネージド組織のユーザーかどうかを確認する方法については、「[アンマネージド テナントからユーザーを削除する](https://learn.microsoft.com/ja-jp/power-automate/privacy-dsr-delete#delete-the-user-from-unmanaged-tenant)」を参照してください。
- 保持する必要があるすべてのデータを保存します。 エクスポート要求を送信する方法については、「[Accessing and exporting system-generated logs for Unmanaged Tenants (アンマネージド テナントのシステム生成ログへのアクセスとエクスポート)](https://learn.microsoft.com/ja-jp/power-platform/admin/powerapps-privacy-dsr-guide-systemlogs#accessing-and-exporting-system-generated-logs-for-unmanaged-tenants)」を参照してください。

警告

一度アカウントを削除すると、元に戻すことはできません。 アカウントを閉じると、サービスはすべての個人データを削除します。 アカウントにアクセスできなくなり、アカウントに関連付けられているデータにアクセスできなくなります。

### アカウントを削除する

職場または学校のアンマネージド アカウントを削除するには、次の手順を実行します。

1. 削除するアカウントでサインインし、[アカウントを閉じる](https://portal.azure.com/#blade/Microsoft_AAD_IAM/PrivacyDataRequests)手続きを行ってください。
2. **[データ要求]** で **[アカウントの削除]** を選択します。

    [Image: データ要求 - アカウントを閉じる]
3. 確認メッセージを確認して、 **[はい]** を選択します。

    [Image: 私のデータ要求 - 閉じるの確認]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-custom-security-attributes"} -->
## ユーザーのカスタム セキュリティ属性の割り当て、更新、一覧表示、削除 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes
- Service: entra-id / users
- Article date: 2026-05-11
- Summary: Microsoft Entra ID でのユーザーのカスタムセキュリティ属性の割り当て、更新、一覧表示、削除。

### 概要

Microsoft Entra の一部である Microsoft Entra ID の[カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)は、ビジネス固有の属性 (キーと値のペア) であり、Microsoft Entra のオブジェクトに対して定義し割り当てることができます。 たとえば、カスタム セキュリティ属性を割り当てて従業員をフィルター処理したり、リソースにアクセスできるユーザーを決定したりすることができます。 この記事では、Microsoft Entra ID でのカスタム セキュリティ属性の割り当て、更新、一覧表示、削除の方法について説明します。

### 前提条件

Microsoft Entra テナント内のユーザーのカスタム セキュリティ属性の割り当てや削除には、次のものが必要です。

- [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)
- [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用する場合、Microsoft.Graph モジュール
- 必要なスコープを使用して Microsoft Graph に接続します。

    ```PowerShell
    Connect-MgGraph -Scopes "CustomSecAttributeAssignment.ReadWrite.All"
    ```

重要

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

### カスタム セキュリティ属性のユーザーへの割り当て

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)としてサインインしてください。
2. カスタム セキュリティ属性が定義されていることを確認します。 詳細については、「[Microsoft Entra ID でのカスタム セキュリティ属性定義の追加または非アクティブ化](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)」を参照してください。
3. **Identity**&gt;**Users**&gt;**All users** に移動します。
4. カスタム セキュリティ属性を割り当てるユーザーを検索して選択します。
5. [管理] セクションで、**[カスタム セキュリティ属性]** を選択してください。
6. **[割り当ての追加]** を選択します。
7. **[Attribute set](https://learn.microsoft.com/ja-jp/entra/identity/users/属性セット)** で、一覧から属性セットを選択します。
8. **[属性名]** で、一覧からカスタム セキュリティ属性を選択します。
9. 選択したカスタム セキュリティ属性のプロパティに応じて、1 つの値を入力したり、定義済みの一覧から値を選択したり、複数の値を追加したりできます。

    - 自由形式の単一値カスタム セキュリティ属性の場合は、**[Assigned values](https://learn.microsoft.com/ja-jp/entra/identity/users/割り当てられた値)** ボックスに値を入力します。
    - 定義済みのカスタム セキュリティ属性値については、**[Assigned values](https://learn.microsoft.com/ja-jp/entra/identity/users/割り当てられた値)** リストから値を選択します。
    - 複数値のカスタム セキュリティ属性の場合は、**[Add values](https://learn.microsoft.com/ja-jp/entra/identity/users/値の追加)** を選択して **[Attribute values](https://learn.microsoft.com/ja-jp/entra/identity/users/属性値)** ペインを開き、値を追加します。 値の入力が完了したら、**[OK]** を選択します。

    [Image: カスタム セキュリティ属性のユーザーへの割り当てを示すスクリーンショット。]
10. 完了したら、**[保存]** を選択して、カスタム セキュリティ属性をユーザーに割り当てます。

### ユーザーのカスタム セキュリティ属性の割り当て値を更新する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)としてサインインしてください。
2. **Identity**&gt;**Users**&gt;**All users** に移動します。
3. 更新するカスタム セキュリティ属性の割り当て値を持つユーザーを検索して選択します。
4. [管理] セクションで、**[カスタム セキュリティ属性]** を選択してください。
5. 更新するカスタム セキュリティ属性の割り当て値を見つけます。

    カスタム セキュリティ属性をユーザーに割り当てた後は、カスタム セキュリティ属性の値のみを変更できます。 属性セットや属性名など、カスタム セキュリティ属性のその他のプロパティを変更することはできません。
6. 選択したカスタム セキュリティ属性のプロパティに応じて、1つの値を更新したり、定義済みの一覧から値を選択したり、複数の値を更新したりできます。
7. 終わったら、 **[保存]** を選択します。

### カスタム セキュリティ属性割り当てに基づいてユーザーをフィルター処理する

[すべてのユーザー] ページで、ユーザーに割り当てられているカスタム セキュリティ属性のリストをフィルター処理できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性割り当て閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)としてサインインしてください。
2. **Identity**&gt;**Users**&gt;**All users** に移動します。
3. **[フィルターを追加する]** を選択して、[フィルターを追加する] ウィンドウを開きます。
4. **[Custom security attributes](カスタム セキュリティ属性)** を選択します。
5. 属性セットと属性名を選択します。
6. **[演算子]**には、等号 (**==**)、不等号 (**!=**)、または **[次の値で始まる]** が選択できます。
7. **[値]** には、値を入力または選択します。

    [Image: ユーザーのカスタム セキュリティ属性フィルターを表示するスクリーンショット。]
8. **[適用]** を選択して、フィルターを適用します。

### ユーザーからカスタム セキュリティ属性の割り当てを削除する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)としてサインインしてください。
2. **Identity**&gt;**Users**&gt;**All users** に移動します。
3. 削除するカスタム セキュリティ属性の割り当て値を持つユーザーを検索して選択します。
4. [管理] セクションで、**[カスタム セキュリティ属性]** を選択してください。
5. 削除するすべてのカスタム セキュリティ属性の割り当ての横にチェックマークを追加します。
6. **[割り当ての削除]**を選択します。

### PowerShell または Microsoft Graph API

Microsoft Entra 組織内のユーザーのカスタム セキュリティ属性の割り当てを管理するには、PowerShell または Microsoft Graph API を使用できます。 割り当ての管理には、次の例を使用できます。

#### 文字列値を持つカスタム セキュリティ属性をユーザーに割り当てる

次の例では、文字列値を持つカスタム セキュリティ属性をユーザーに割り当てます。

- 属性セット: `Engineering`
- 属性: `ProjectDate`
- 属性のデータ型: 文字列
- 属性値: `"2024-11-15"`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "ProjectDate" = "2024-11-15"
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "ProjectDate":"2024-11-15"
        }
    }
}
```

---

#### 複数の文字列値を持つカスタム セキュリティ属性をユーザーに割り当てる

次の例では、複数文字列値を持つカスタム セキュリティ属性をユーザーに割り当てます。

- 属性セット: `Engineering`
- 属性: `Project`
- 属性のデータ型: 文字列のコレクション
- 属性値: `["Baker","Cascade"]`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "Project@odata.type" = "#Collection(String)"
        "Project" = @("Baker","Cascade")
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "Project@odata.type":"#Collection(String)",
            "Project":["Baker","Cascade"]
        }
    }
}
```

---

#### 整数値を持つカスタム セキュリティ属性をユーザーに割り当てる

次の例では、整数値を持つカスタム セキュリティ属性をユーザーに割り当てます。

- 属性セット: `Engineering`
- 属性: `NumVendors`
- 属性のデータ型: 整数
- 属性値: `4`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "NumVendors@odata.type" = "#Int32"
        "NumVendors" = 4
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "NumVendors@odata.type":"#Int32",
            "NumVendors":4
        }
    }
}
```

---

#### 複数の整数値を持つカスタム セキュリティ属性をユーザーに割り当てる

次の例では、複数整数値を持つカスタム セキュリティ属性をユーザーに割り当てます。

- 属性セット: `Engineering`
- 属性: `CostCenter`
- 属性のデータ型: 整数のコレクション
- 属性値: `[1001,1003]`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "CostCenter@odata.type" = "#Collection(Int32)"
        "CostCenter" = @(1001,1003)
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "CostCenter@odata.type":"#Collection(Int32)",
            "CostCenter":[1001,1003]
        }
    }
}
```

---

#### ブール値を持つカスタム セキュリティ属性をユーザーに割り当てる

次の例では、ブール値を持つカスタム セキュリティ属性をユーザーに割り当てます。

- 属性セット: `Engineering`
- 属性: `Certification`
- 属性のデータ型: ブール値
- 属性値: `true`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "Certification" = $true
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "Certification":true
        }
    }
}
```

---

#### ユーザーの整数値を持つカスタム セキュリティ属性割り当てを更新する

次の例では、ユーザーの整数値を持つカスタム セキュリティ属性割り当てを更新します。

- 属性セット: `Engineering`
- 属性: `NumVendors`
- 属性のデータ型: 整数
- 属性値: `8`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "NumVendors@odata.type" = "#Int32"
        "NumVendors" = 8
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "NumVendors@odata.type":"#Int32",
            "NumVendors":8
        }
    }
}
```

---

#### ユーザーに対するブール値でカスタムセキュリティ属性の割り当てを更新する

次の例では、ユーザーのブール値のカスタム セキュリティ属性割り当てを更新します。

- 属性セット: `Engineering`
- 属性: `Certification`
- 属性のデータ型: ブール値
- 属性値: `false`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "Certification" = $false
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "Certification":false
        }
    }
}
```

---

#### ユーザーの複数文字列値を持つカスタム セキュリティ属性割り当てを更新する

次の例では、ユーザーの複数文字列値を持つカスタム セキュリティ属性割り当てを更新します。

- 属性セット: `Engineering`
- 属性: `Project`
- 属性のデータ型: 文字列のコレクション
- 属性値: `("Alpine","Baker")`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "Project@odata.type" = "#Collection(String)"
        "Project" = @("Alpine","Baker")
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "Project@odata.type":"#Collection(String)",
            "Project":["Alpine","Baker"]
        }
    }
}
```

---

#### ユーザーからカスタム セキュリティ属性の割り当てを取得する

次の例では、ユーザーのカスタム セキュリティ属性の割り当てを取得します。

## [PowerShell](#tab/ms-powershell)
[Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser)

```powershell
$userAttributes = Get-MgUser -UserId $userId -Property "customSecurityAttributes"
$userAttributes.CustomSecurityAttributes.AdditionalProperties | Format-List
$userAttributes.CustomSecurityAttributes.AdditionalProperties.Engineering
$userAttributes.CustomSecurityAttributes.AdditionalProperties.Marketing
```

```Output
Key   : Engineering
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [Project@odata.type, #Collection(String)], [Project, System.Object[]],
        [ProjectDate, 2024-11-15]…}

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [EmployeeId, GS45897]}

Key                   Value
---                   -----
@odata.type           #microsoft.graph.customSecurityAttributeValue
Project@odata.type    #Collection(String)
Project               {Baker, Alpine}
ProjectDate           2024-11-15
NumVendors            8
CostCenter@odata.type #Collection(Int32)
CostCenter            {1001, 1003}
Certification         False

Key         Value
---         -----
@odata.type #microsoft.graph.customSecurityAttributeValue
EmployeeId  KX45897
```

ユーザーにカスタム セキュリティ属性が割り当てられていない場合、または呼び出し元プリンシパルにアクセス権がない場合、応答は空になります。

## [Microsoft Graph](#tab/ms-graph)
[ユーザーを取得する](https://learn.microsoft.com/ja-jp/graph/api/user-get)

```http
GET https://graph.microsoft.com/v1.0/users/{id}?$select=customSecurityAttributes
```

```http
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users(customSecurityAttributes)/$entity",
    "customSecurityAttributes": {
        "Engineering": {
            "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
            "Project@odata.type": "#Collection(String)",
            "Project": [
                "Baker",
                "Alpine"
            ],
            "ProjectDate": "2024-11-15",
            "NumVendors": 8,
            "CostCenter@odata.type": "#Collection(Int32)",
            "CostCenter": [
                1001,
                1003
            ],
            "Certification": false
        },
        "Marketing": {
            "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
            "EmployeeId": "GS45897"
        }
    }
}
```

ユーザーにカスタム セキュリティ属性が割り当てられていない場合、または呼び出し元プリンシパルにアクセス権がない場合、応答は次のようになります。

```http
{
    "customSecurityAttributes": null
}
```

---

#### すべてのユーザーとカスタム セキュリティ属性の割り当てを一覧表示する

次の例では、すべてのユーザーとカスタム セキュリティ属性の割り当てを一覧表示します。 この例では `$select` ( `$filter`、 `$search`、 `$count`、または `$orderby`を使用しないため)、高度なクエリ パラメーターは必要ありません。 `$filter`で`customSecurityAttributes`を使用するリストの例では、`ConsistencyLevel=eventual`と`$count=true`が必要です。 詳細については、「[ディレクトリ オブジェクトの詳細クエリ機能](https://learn.microsoft.com/ja-jp/graph/aad-advanced-queries)」を参照してください。

## [PowerShell](#tab/ms-powershell)
[Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser)

```powershell
$userAttributes = Get-MgUser -All -Property "id,displayName,customSecurityAttributes"
$userAttributes | select Id,DisplayName,CustomSecurityAttributes
$userAttributes.CustomSecurityAttributes.AdditionalProperties | Format-List
```

```Output
Id                                   DisplayName CustomSecurityAttributes
--                                   ----------- ------------------------
00aa00aa-bb11-cc22-dd33-44ee44ee44ee Alain
11bb11bb-cc22-dd33-ee44-55ff55ff55ff Joe         Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
22cc22cc-dd33-ee44-ff55-66aa66aa66aa Isabella    Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
33dd33dd-ee44-ff55-aa66-77bb77bb77bb Jiya        Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
44ee44ee-ff55-aa66-bb77-88cc88cc88cc Admin

Key   : Engineering
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [Project@odata.type, #Collection(String)], [Project, System.Object[]],
        [CostCenter@odata.type, #Collection(Int32)], [CostCenter, System.Object[]], [Certification, True]}

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [EmployeeId, QN26904]}

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [AppCountry@odata.type, #Collection(String)], [AppCountry, System.Object[]]}

Key   : Engineering
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [ProjectDate, 2026-04-23]}
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの一覧表示](https://learn.microsoft.com/ja-jp/graph/api/user-list)

```http
GET https://graph.microsoft.com/v1.0/users?$select=id,displayName,customSecurityAttributes
```

テナントのユーザー数が 1 ページに収まらない場合、応答には `@odata.nextLink` プロパティが含まれます。 そのリンクに従って、結果の次のページを取得します。 詳細については、[アプリで Microsoft Graph データをページングする](https://learn.microsoft.com/ja-jp/graph/paging)を参照してください。

```http
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users(id,displayName,customSecurityAttributes)",
    "value": [
        {
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
            "displayName": "Alain",
            "customSecurityAttributes": null
        },
        {
            "id": "11bb11bb-cc22-dd33-ee44-55ff55ff55ff",
            "displayName": "Joe",
            "customSecurityAttributes": {
                "Engineering": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "Project@odata.type": "#Collection(String)",
                    "Project": [
                        "Baker",
                        "Cascade"
                    ],
                    "CostCenter@odata.type": "#Collection(Int32)",
                    "CostCenter": [
                        1001
                    ],
                    "Certification": true
                },
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "EmployeeId": "QN26904"
                }
            }
        },
        {
            "id": "22cc22cc-dd33-ee44-ff55-66aa66aa66aa",
            "displayName": "Isabella",
            "customSecurityAttributes": {
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "AppCountry@odata.type": "#Collection(String)",
                    "AppCountry": [
                        "France"
                    ]
                }
            }
        },
        {
            "id": "33dd33dd-ee44-ff55-aa66-77bb77bb77bb",
            "displayName": "Jiya",
            "customSecurityAttributes": {
                "Engineering": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "ProjectDate": "2026-04-23"
                }
            }
        },
        {
            "id": "44ee44ee-ff55-aa66-bb77-88cc88cc88cc",
            "displayName": "Admin",
            "customSecurityAttributes": null
        }
    ]
}
```

---

#### 値と等しいカスタム セキュリティ属性の割り当てを持つすべてのユーザーを一覧表示する

次の例では、値と等しいカスタム セキュリティ属性の割り当てを持つすべてのユーザーを一覧表示します。 値が `AppCountry` と等しい `Canada` という名前のカスタム セキュリティ属性を持つユーザーを取得します。 フィルター値は大文字と小文字が区別されます。 要求またはヘッダーに `ConsistencyLevel=eventual` を追加する必要があります。 要求が正しくルーティングされるようにするには、 `$count=true` も含める必要があります。

- 属性セット: `Marketing`
- 属性: `AppCountry`
- フィルター: AppCountry eq 'Canada'

## [PowerShell](#tab/ms-powershell)
[Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser)

```powershell
$userAttributes = Get-MgUser -CountVariable CountVar -Property "id,displayName,customSecurityAttributes" -Filter "customSecurityAttributes/Marketing/AppCountry eq 'Canada'" -ConsistencyLevel eventual
$userAttributes | select Id,DisplayName,CustomSecurityAttributes
$userAttributes.CustomSecurityAttributes.AdditionalProperties | Format-List
```

```Output
Id                                   DisplayName CustomSecurityAttributes
--                                   ----------- ------------------------
00aa00aa-bb11-cc22-dd33-44ee44ee44ee Jiya        Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
11bb11bb-cc22-dd33-ee44-55ff55ff55ff Jana        Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue

Key   : Engineering
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [Datacenter@odata.type, #Collection(String)], [Datacenter, System.Object[]]}

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [AppCountry@odata.type, #Collection(String)], [AppCountry, System.Object[]],
        [EmployeeId, KX19476]}

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [AppCountry@odata.type, #Collection(String)], [AppCountry, System.Object[]],
        [EmployeeId, GS46982]}
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの一覧表示](https://learn.microsoft.com/ja-jp/graph/api/user-list)

```http
GET https://graph.microsoft.com/v1.0/users?$count=true&$select=id,displayName,customSecurityAttributes&$filter=customSecurityAttributes/Marketing/AppCountry eq 'Canada'
ConsistencyLevel: eventual
```

```http
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users(id,displayName,customSecurityAttributes)",
    "@odata.count": 2,
    "value": [
        {
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
            "displayName": "Jiya",
            "customSecurityAttributes": {
                "Engineering": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "Datacenter@odata.type": "#Collection(String)",
                    "Datacenter": [
                        "India"
                    ]
                },
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "AppCountry@odata.type": "#Collection(String)",
                    "AppCountry": [
                        "India",
                        "Canada"
                    ],
                    "EmployeeId": "KX19476"
                }
            }
        },
        {
            "id": "11bb11bb-cc22-dd33-ee44-55ff55ff55ff",
            "displayName": "Jana",
            "customSecurityAttributes": {
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "AppCountry@odata.type": "#Collection(String)",
                    "AppCountry": [
                        "Canada",
                        "Mexico"
                    ],
                    "EmployeeId": "GS46982"
                }
            }
        }
    ]
}
```

---

#### 値で始まるカスタム セキュリティ属性の割り当てを持つすべてのユーザーを一覧表示する

次の例では、値で始まるカスタム セキュリティ属性の割り当てを持つすべてのユーザーを一覧表示します。 値が `EmployeeId` で始まる `GS` という名前のカスタム セキュリティ属性を持つユーザーを取得します。 フィルター値は大文字と小文字が区別されます。 要求またはヘッダーに `ConsistencyLevel=eventual` を追加する必要があります。 要求が正しくルーティングされるようにするには、 `$count=true` も含める必要があります。

- 属性セット: `Marketing`
- 属性: `EmployeeId`
- フィルター: EmployeeId が 'GS' で始まる

## [PowerShell](#tab/ms-powershell)
[Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser)

```powershell
$userAttributes = Get-MgUser -CountVariable CountVar -Property "id,displayName,customSecurityAttributes" -Filter "startsWith(customSecurityAttributes/Marketing/EmployeeId,'GS')" -ConsistencyLevel eventual
$userAttributes | select Id,DisplayName,CustomSecurityAttributes
$userAttributes.CustomSecurityAttributes.AdditionalProperties | Format-List
```

```Output
Id                                   DisplayName CustomSecurityAttributes
--                                   ----------- ------------------------
22cc22cc-dd33-ee44-ff55-66aa66aa66aa Chandra     Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
11bb11bb-cc22-dd33-ee44-55ff55ff55ff Jana        Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
33dd33dd-ee44-ff55-aa66-77bb77bb77bb Joe         Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [EmployeeId, GS36348]}

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [AppCountry@odata.type, #Collection(String)], [AppCountry, System.Object[]],
        [EmployeeId, GS46982]}

Key   : Engineering
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [Project@odata.type, #Collection(String)], [Project, System.Object[]],
        [ProjectDate, 2024-11-15]…}

Key   : Marketing
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [EmployeeId, GS45897]}
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの一覧表示](https://learn.microsoft.com/ja-jp/graph/api/user-list)

```http
GET https://graph.microsoft.com/v1.0/users?$count=true&$select=id,displayName,customSecurityAttributes&$filter=startsWith(customSecurityAttributes/Marketing/EmployeeId,'GS')
ConsistencyLevel: eventual
```

```http
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users(id,displayName,customSecurityAttributes)",
    "@odata.count": 3,
    "value": [
        {
            "id": "22cc22cc-dd33-ee44-ff55-66aa66aa66aa",
            "displayName": "Chandra",
            "customSecurityAttributes": {
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "EmployeeId": "GS36348"
                }
            }
        },
        {
            "id": "11bb11bb-cc22-dd33-ee44-55ff55ff55ff",
            "displayName": "Jana",
            "customSecurityAttributes": {
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "AppCountry@odata.type": "#Collection(String)",
                    "AppCountry": [
                        "Canada",
                        "Mexico"
                    ],
                    "EmployeeId": "GS46982"
                }
            }
        },
        {
            "id": "33dd33dd-ee44-ff55-aa66-77bb77bb77bb",
            "displayName": "Joe",
            "customSecurityAttributes": {
                "Engineering": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "Project@odata.type": "#Collection(String)",
                    "Project": [
                        "Baker",
                        "Alpine"
                    ],
                    "ProjectDate": "2024-11-15",
                    "NumVendors": 8,
                    "CostCenter@odata.type": "#Collection(Int32)",
                    "CostCenter": [
                        1001,
                        1003
                    ],
                    "Certification": false
                },
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "EmployeeId": "GS45897"
                }
            }
        }
    ]
}
```

---

#### 値と等しくないカスタム セキュリティ属性の割り当てを持つすべてのユーザーを一覧表示する

次の例では、値と等しくないカスタム セキュリティ属性の割り当てを持つすべてのユーザーを一覧表示します。 `AppCountry`と等しくない値を持つ `Canada` という名前のカスタム セキュリティ属性を持つユーザーを取得します。 フィルター値は大文字と小文字が区別されます。 要求またはヘッダーに `ConsistencyLevel=eventual` を追加する必要があります。 要求が正しくルーティングされるようにするには、 `$count=true` も含める必要があります。

- 属性セット: `Marketing`
- 属性: `AppCountry`
- フィルター: AppCountry ≠ 'Canada'

## [PowerShell](#tab/ms-powershell)
[Get-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguser)

```powershell
$userAttributes = Get-MgUser -CountVariable CountVar -Property "id,displayName,customSecurityAttributes" -Filter "customSecurityAttributes/Marketing/AppCountry ne 'Canada'" -ConsistencyLevel eventual
$userAttributes | select Id,DisplayName,CustomSecurityAttributes
```

```Output
Id                                   DisplayName              CustomSecurityAttributes
--                                   -----------              ------------------------
22cc22cc-dd33-ee44-ff55-66aa66aa66aa Chandra                  Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
44ee44ee-ff55-aa66-bb77-88cc88cc88cc Isabella                 Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
00aa00aa-bb11-cc22-dd33-44ee44ee44ee Alain                    Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
33dd33dd-ee44-ff55-aa66-77bb77bb77bb Joe                      Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
00aa00aa-bb11-cc22-dd33-44ee44ee44ee Dara                     Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの一覧表示](https://learn.microsoft.com/ja-jp/graph/api/user-list)

```http
GET https://graph.microsoft.com/v1.0/users?$count=true&$select=id,displayName,customSecurityAttributes&$filter=customSecurityAttributes/Marketing/AppCountry ne 'Canada'
ConsistencyLevel: eventual
```

```http
{
    "@odata.context": "https://graph.microsoft.com/v1.0/$metadata#users(id,displayName,customSecurityAttributes)",
    "@odata.count": 47,
    "value": [
        {
            "id": "22cc22cc-dd33-ee44-ff55-66aa66aa66aa",
            "displayName": "Chandra",
            "customSecurityAttributes": {
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "EmployeeId": "GS36348"
                }
            }
        },
        {
            "id": "44ee44ee-ff55-aa66-bb77-88cc88cc88cc",
            "displayName": "Isabella",
            "customSecurityAttributes": {
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "AppCountry@odata.type": "#Collection(String)",
                    "AppCountry": [
                        "France"
                    ]
                }
            }
        },
        {
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
            "displayName": "Alain",
            "customSecurityAttributes": {
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "AppCountry@odata.type": "#Collection(String)",
                    "AppCountry": [
                        "Germany",
                        "Japan"
                    ]
                }
            }
        },
        {
            "id": "33dd33dd-ee44-ff55-aa66-77bb77bb77bb",
            "displayName": "Joe",
            "customSecurityAttributes": {
                "Engineering": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "Project@odata.type": "#Collection(String)",
                    "Project": [
                        "Baker",
                        "Alpine"
                    ],
                    "ProjectDate": "2024-11-15",
                    "NumVendors": 8,
                    "CostCenter@odata.type": "#Collection(Int32)",
                    "CostCenter": [
                        1001,
                        1003
                    ],
                    "Certification": false
                },
                "Marketing": {
                    "@odata.type": "#microsoft.graph.customSecurityAttributeValue",
                    "EmployeeId": "GS45897"
                }
            }
        },
        {
            "id": "00aa00aa-bb11-cc22-dd33-44ee44ee44ee",
            "displayName": "Dara",
            "customSecurityAttributes": null
        }
    ]
}
```

---

#### ユーザーから単一値のカスタム セキュリティ属性の割り当てを削除する

次の例では、値を null に設定することによって、ユーザーから単一値のカスタム セキュリティ属性の割り当てを削除します。

- 属性セット: `Engineering`
- 属性: `ProjectDate`
- 属性値: `null`

## [PowerShell](#tab/ms-powershell)
[MgGraphRequest を呼び出す](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/authentication-commands#using-invoke-mggraphrequest)

```powershell
$params = @{
    "customSecurityAttributes" = @{
        "Engineering" = @{
            "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
            "ProjectDate" = $null
        }
    }
}
Invoke-MgGraphRequest -Method PATCH -Uri "https://graph.microsoft.com/v1.0/users/$userId" -Body $params
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "ProjectDate":null
        }
    }
}
```

---

#### ユーザーから複数値のカスタム セキュリティ属性の割り当てを削除する

次の例では、値を空のコレクションに設定することによって、ユーザーから複数値のカスタム セキュリティ属性の割り当てを削除します。

- 属性セット: `Engineering`
- 属性: `Project`
- 属性値: `[]`

## [PowerShell](#tab/ms-powershell)
[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)

```powershell
$customSecurityAttributes = @{
    "Engineering" = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "Project" = @()
    }
}
Update-MgUser -UserId $userId -CustomSecurityAttributes $customSecurityAttributes
```

## [Microsoft Graph](#tab/ms-graph)
[ユーザーの更新](https://learn.microsoft.com/ja-jp/graph/api/user-update)

```http
PATCH https://graph.microsoft.com/v1.0/users/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "Project":[]
        }
    }
}
```

---

### よく寄せられる質問

**ユーザーのカスタム セキュリティ属性の割り当てはどこでサポートされていますか。**

ユーザーのカスタム セキュリティ属性の割り当ては、Microsoft Entra 管理センター、PowerShell、Microsoft Graph API でサポートされています。 カスタム セキュリティ属性の割り当ては、マイ アプリまたは Microsoft 365 管理センターではサポートされていません。

**ユーザーに割り当てられているカスタム セキュリティ属性は誰が表示できますか。**

テナント内の任意のユーザーに割り当てられたカスタム セキュリティ属性を表示できるのは、テナント スコープで属性割り当て管理者ロールまたは属性割り当て閲覧者ロールが割り当てられているユーザーだけです。 ユーザーは、自分のプロファイルまたは他のユーザーに割り当てられているカスタム セキュリティ属性を表示できません。 ゲストは、テナントに設定されているゲストアクセス許可に関係なく、カスタム セキュリティ属性を表示できません。

**カスタム セキュリティ属性の割り当てを追加するのに、アプリを作成する必要がありますか。**

いいえ。カスタム セキュリティ属性は、アプリケーションを必要とせずにユーザー オブジェクトに割り当てることができます。

**カスタム セキュリティ属性の割り当てを保存しようとするとエラーが発生し続けるのはなぜですか。**

カスタム セキュリティ属性をユーザーに割り当てるアクセス許可がありません。 必ず属性割り当て管理者ロールが自分に割り当てられていることを確認してください。

**ゲストにカスタム セキュリティ属性を割り当てることはできますか。**

はい。カスタム セキュリティ属性は、テナント内のメンバーまたはゲストに割り当てることができます。

**ディレクトリ同期済みのユーザーにカスタム セキュリティ属性を割り当てることはできますか。**

はい。オンプレミスの Active Directory のディレクトリ同期済みのユーザーにカスタム セキュリティ属性を割り当てることができます。

**動的メンバーシップ グループの規則では、カスタム セキュリティ属性の割り当てを使用できますか。**

いいえ。動的メンバーシップ グループの規則を構成するために、ユーザーに割り当てられたカスタム セキュリティ属性はサポートされていません。

**カスタム セキュリティ属性は B2C テナントのカスタム属性と同じですか。**

いいえ。カスタム セキュリティ属性は B2C テナントではサポートされておらず、B2C 機能には関連しません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-restrict-guest-permissions"} -->
## ゲスト ユーザーのアクセス許可を制限する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-restrict-guest-permissions
- Service: entra-id / users
- Article date: 2024-12-19
- Summary: Microsoft Entra ID で Azure portal、PowerShell、または Microsoft Graph を使って、ゲスト ユーザーのアクセス許可を制限します

### 概要

Microsoft Entra の一部である Microsoft Entra ID を使用すると、Microsoft Entra ID で組織内でゲスト ユーザーが表示できる内容を制限できます。 既定では、ゲスト ユーザーは Microsoft Entra ID で制限されたアクセス許可レベルに設定されますが、メンバー ユーザーの既定値は、ユーザー アクセス許可の完全なセットになります。 Microsoft Entra 組織の外部コラボレーション設定には、さらに制限されたアクセス用の別のゲスト ユーザーのアクセス許可レベルがあるため、ゲスト アクセス レベルは次のようになります。

| アクセス許可レベル | アクセス レベル | 価値 |
| --- | --- | --- |
| メンバー ユーザーと同じ | ゲストは、Microsoft Entra リソースに対してメンバー ユーザーと同じアクセス権を持っています | A0B1B346-4D3E-4E8B-98F8-753987BE4970 |
| 制限付きアクセス (既定) | ゲストは、非表示でないすべてのグループのメンバーシップを表示できます | 10DAE51F-B6AF-4016-8D66-8C2A99B929B3 |
| **制限付きアクセス (新規)** | **ゲストは、どのグループのメンバーシップも表示できません** | **2AF84B1E-32C8-42B7-82BC-DAA82404023B** |

ゲスト アクセスが制限されている場合、ゲストは自分のユーザー プロファイルのみを表示できます。 ゲストがユーザー プリンシパル名または objectId で検索している場合でも、他のユーザーを表示するアクセス許可は許可されません。 制限付きアクセスでは、ゲスト ユーザーが所属しているグループのメンバーシップも表示できないように制限されます。 ゲスト ユーザーのアクセス許可を含めた全体的な既定のユーザー アクセス許可については、「[Microsoft Entra ID の既定のユーザー アクセス許可とは](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions)」を参照してください。

### Microsoft Entra 管理センターの更新

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. 「**Entra ID**&gt;**External Identities**」を選択します。
3. **[外部コラボレーションの設定]** を選択します。
4. **[外部コラボレーションの設定]** ページで、 **[Guest user access is restricted to properties and memberships of their own directory objects] (ゲスト ユーザーのアクセスを、自分のディレクトリ オブジェクトのプロパティとメンバーシップに制限する)** オプションを選択します。

    [Image: Microsoft Entra の [外部コラボレーション設定] ページのスクリーンショット。]
5. **[保存]** を選択します。 変更は、ゲスト ユーザーに対して有効になるまでに最大 15 分かかることがあります。

### Microsoft Graph API を使用して更新する

Microsoft Entra 組織でゲストアクセス許可を構成するための新しい Microsoft Graph API があります。 次の API 呼び出しを実行して、任意のアクセス許可レベルを割り当てることができます。 ここで使用する guestUserRoleId の値は、最も制限の厳しいゲスト ユーザー設定を示すためのものです。 Microsoft Graph を使用してゲストのアクセス許可を設定する方法の詳細については、[`authorizationPolicy` リソース タイプ](https://learn.microsoft.com/ja-jp/graph/api/resources/authorizationpolicy)に関するページを参照してください。

#### 初めての設定を行う

```PowerShell
POST https://graph.microsoft.com/beta/policies/authorizationPolicy/authorizationPolicy

{
  "guestUserRoleId": "2af84b1e-32c8-42b7-82bc-daa82404023b"
}
```

応答は Success 204 です。

メモ

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨の最新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正プログラムに限定されます。 非推奨になるモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) を使用するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、「[移行に関する FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq)」を参照してください。 *注:* バージョン 1.0.x の MSOnline では、2024 年 6 月 30 日以降に中断が発生する可能性があります。

#### 既存の値の更新

```PowerShell
PATCH https://graph.microsoft.com/beta/policies/authorizationPolicy/authorizationPolicy

{
  "guestUserRoleId": "2af84b1e-32c8-42b7-82bc-daa82404023b"
}
```

応答は Success 204 です。

#### 現在の値を表示する

```PowerShell
GET https://graph.microsoft.com/beta/policies/authorizationPolicy/authorizationPolicy
```

応答の例:

```PowerShell
{
    "@odata.context": "https://graph.microsoft.com/beta/$metadata#policies/authorizationPolicy/$entity",
    "id": "authorizationPolicy",
    "displayName": "Authorization Policy",
    "description": "Used to manage authorization related settings across the company.",
    "enabledPreviewFeatures": [],
    "guestUserRoleId": "10dae51f-b6af-4016-8d66-8c2a99b929b3",
    "permissionGrantPolicyIdsAssignedToDefaultUserRole": [
        "user-default-legacy"
    ]
}
```

### PowerShell コマンドレットで更新する

この機能により、PowerShell v2 コマンドレットを使用して制限されたアクセス許可を構成する機能が追加されました。 PowerShell のGetおよびUpdateコマンドレットは、バージョン`2.0.2.85`で公開されています。

#### Get コマンド: Get-MgPolicyAuthorizationPolicy

例：

```powershell
Get-MgPolicyAuthorizationPolicy | Format-List
```

```output
AllowEmailVerifiedUsersToJoinOrganization : True
AllowInvitesFrom                          : everyone
AllowUserConsentForRiskyApps              :
AllowedToSignUpEmailBasedSubscriptions    : True
AllowedToUseSspr                          : True
BlockMsolPowerShell                       : False
DefaultUserRolePermissions                : Microsoft.Graph.PowerShell.Models.MicrosoftGraphDefaultUserRolePermissions
DeletedDateTime                           :
Description                               : Used to manage authorization related settings across the company.
DisplayName                               : Authorization Policy
GuestUserRoleId                           : 10dae51f-b6af-4016-8d66-8c2a99b929b3
Id                                        : authorizationPolicy
AdditionalProperties                      : {[@odata.context, https://graph.microsoft.com/v1.0/$metadata#policies/authorizationPolicy/$entity]}
```

#### Update コマンド:Update-MgPolicyAuthorizationPolicy

例：

```powershell
Update-MgPolicyAuthorizationPolicy -GuestUserRoleId '2af84b1e-32c8-42b7-82bc-daa82404023b'
```

### サポートされている Microsoft 365 サービス

#### サポートされているサービス

サポートされている場合、エクスペリエンスは期待どおりに行われます。具体的には、現在のゲスト エクスペリエンスと同じです。

- チーム
- Outlook (OWA)
- SharePoint
- Teams 内の Planner
- Planner モバイル アプリ
- Planner Web アプリ
- ウェブ用のプロジェクト
- プロジェクト運営

#### 現時点ではサポートされていないサービス

現在サポートされていないサービスでは、新しいゲスト制限設定との互換性の問題が発生する可能性があります。

- フォーム
- プロジェクトオンライン
- Viva Engage
- SharePoint の Planner

### よく寄せられる質問 (FAQ)

| 質問 | 答え |
| --- | --- |
| これらのアクセス許可はどこに適用されますか。 | これらのディレクトリ レベルのアクセス許可は、Microsoft Graph、PowerShell v2、Azure portal、マイ アプリ ポータルなどの Microsoft Entra サービスに適用されます。 コラボレーション シナリオに Microsoft 365 グループを使用する Microsoft 365 サービスも影響を受けます(特に Outlook、Microsoft Teams、SharePoint)。 |
| 制限付きアクセス許可は、ゲストが表示できるグループにどのように影響しますか。 | 既定または制限付きのゲスト アクセス許可に関係なく、ゲストはグループまたはユーザーの一覧を列挙できません。 ゲストは、アクセス許可に応じて、Azure portal とマイ アプリ ポータルの両方で自身がメンバーであるグループを表示できます。<br>- **既定のアクセス許可**:ゲストが Azure portal でメンバーであるグループを見つけるには、 **[すべてのユーザー]** 一覧でオブジェクト ID を検索し、 **[グループ]** を選択する必要があります。 ここでは、名前やメール アドレスなど、グループのすべての詳細を含め、メンバーであるグループの一覧を確認できます。 マイ アプリ ポータルでは、所有しているグループと所属するグループの一覧を表示できます。<br>- **制限付きゲストのアクセス許可**: Azure portal で、**[すべてのユーザー]** 一覧でオブジェクト ID を検索し、**[グループ]** を選択することで、自分が属するグループの一覧を検索できます。 グループについて確認できるのは、限られた詳細 (特にオブジェクト ID) のみです。 仕様として、[名前] と [メール] の列は空白であり、[グループの種類] は "認識不可" です。 マイ アプリ ポータルでは、所有しているグループまたはメンバーであるグループの一覧にアクセスできません。<br><br>Graph API から取得するディレクトリ アクセス許可の詳細な比較については、[既定のユーザー アクセス許可](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#member-and-guest-users)に関するページを参照してください。 |
| この機能によって影響を受けるのは、マイ アプリ ポータルのどの部分ですか。 | マイ アプリ ポータルのグループ機能では、これらの新しいアクセス許可が適用されます。 この機能には、マイ アプリのグループ一覧とグループ メンバーシップを表示するためのすべてのパスが含まれます。 グループ タイルの可用性に変更は加えられていません。 グループ タイルの可用性は、Azure portal の既存のグループ設定によって引き続き制御されます。 |
| これらのアクセス許可は、SharePoint または Microsoft Teams のゲスト設定をオーバーライドしますか。 | いいえ。 これらの既存の設定は、引き続きこれらのアプリケーションのエクスペリエンスとアクセスを制御します。 たとえば、SharePoint で問題が発生した場合は、外部共有設定を再確認します。 チーム レベルでチーム所有者によって追加されたゲストは、プライベート チャネルと共有チャネルを除き、標準チャネルでのみチャネル会議チャットにアクセスできます。 |
| Viva Engage の既知の互換性の問題は何ですか? | アクセス許可が "制限済み" に設定されている場合、Viva Engage にサインインしたゲストはグループを離れることができません。 |
| テナントの既存のゲスト アクセス許可は変更されますか。 | 現在の設定に変更は加えられていません。 既存の設定との下位互換性は維持されます。 変更を行うタイミングを決定してください。 |
| これらのアクセス許可は既定で設定されますか。 | いいえ。 既存の既定のアクセス許可は変更されていません。 必要に応じて、より制限の厳しいアクセス許可を設定することもできます。 |
| この機能のライセンス要件はありますか。 | いいえ。この機能には新しいライセンスの要件はありません。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-revoke-access"} -->
## Microsoft Entra ID で緊急時にユーザー アクセスを取り消す - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-revoke-access
- Service: entra-id / users
- Article date: 2026-06-19
- Summary: Microsoft Entra ID でユーザーのすべてのアクセスを取り消す方法

### 概要

管理者がユーザーのすべてのアクセス権を取り消す必要があるシナリオとしては、侵害されたアカウント、従業員の解雇、およびその他のインサイダーの脅威があります。 環境の複雑さに応じて、管理者はアクセスが確実に取り消されるようにいくつかの手順を実行できます。 シナリオによっては、アクセスの取り消しが開始されてから、アクセスが実質的に取り消されるまでに一定の時間がかかることがあります。

リスクを軽減するには、トークンのしくみを理解しておく必要があります。 トークンにはさまざまな種類があり、この記事で説明するパターンの 1 つに分類されます。

### 前提条件

適切なロールを持つアカウントでサインインします。 手順ごとに異なるロールが必要です。

- ユーザー アカウントを無効にする: 管理者以外のユーザーの場合は [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator) 、管理者アカウントの [場合は特権認証管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-authentication-administrator) 。
- デバイスを無効にする: [少なくともクラウド デバイス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-device-administrator) 。

この記事の PowerShell の手順では[、Microsoft Graph PowerShell SDK](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation?view=graph-powershell-1.0&preserve-view=true)も必要です。 必要なモジュールをインストールします。

```PowerShell
Install-Module Microsoft.Graph.Users
Install-Module Microsoft.Graph.Users.Actions
Install-Module Microsoft.Graph.Identity.DirectoryManagement
```

必要なスコープを使用して Microsoft Graph に接続します。

```PowerShell
Connect-MgGraph -Scopes "User.ReadWrite.All","Directory.AccessAsUser.All"
```

### アクセス トークンと更新トークン

アクセス トークンと更新トークンは、シック クライアント アプリケーションでよく使用されます。また、シングル ページ アプリなどのブラウザーベースのアプリケーションでも使用されます。

- ユーザーが Microsoft Entra の一部である Microsoft Entra ID に対して認証を行うと、承認ポリシーが評価され、そのユーザーに特定のリソースへのアクセスを許可できるかどうかが判断されます。
- 承認後、Microsoft Entra ID はリソースのアクセス トークンと更新トークンを発行します。
- 認証プロトコルで許可されている場合、アプリは、アクセス トークンの有効期限が切れたときに Microsoft Entra ID に更新トークンを渡すことで、ユーザーを自動的に再認証できます。 既定では、Microsoft Entra ID によって発行されたアクセス トークンは 1 時間続きます。
- その後、Microsoft Entra ID によってその承認ポリシーが再評価されます。 ユーザーがまだ承認されている場合、Microsoft Entra ID は新しいアクセス トークンと更新トークンを発行します。

アクセス トークンは、一般的な 1 時間の有効期間よりも短い期間内に取り消す必要がある場合、セキュリティ リスクを引き起こす可能性があります。 このため、Microsoft は、Office 365 アプリケーションに[継続的アクセス評価](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation)を行うために積極的に取り組んでいます。これにより、アクセス トークンをほぼリアルタイムで確実に無効化できます。

### セッション トークン (Cookie)

ほとんどのブラウザーベースのアプリケーションでは、アクセス トークンと更新トークンではなく、セッション トークンが使用されます。

- ユーザーがブラウザーを開いて、Microsoft Entra ID 経由でアプリケーションに対して認証を行うと、ユーザーは 2 つのセッション トークンを受け取ります。 1 つは Microsoft Entra ID から、もう 1 つはアプリケーションから受け取ります。
- アプリケーションが独自のセッション トークンを発行すると、アプリケーションは承認ポリシーに基づいてアクセスを制御します。
- Microsoft Entra ID の承認ポリシーは、アプリケーションがユーザーを Microsoft Entra ID に送り返すたびに再評価されます。 通常、再評価は自動的に行われますが、頻度はアプリケーションの構成方法によって異なります。 セッション トークンが有効である限り、アプリがユーザーを Microsoft Entra ID に送り返さない可能性があります。
- セッション トークンを取り消すには、アプリケーション独自の承認ポリシーに基づいてアクセスを取り消す必要があります。 Microsoft Entra ID では、アプリケーションによって発行されたセッション トークンを直接取り消すことはできません。

### ハイブリッド環境でユーザーのアクセスを取り消す

オンプレミスの Active Directory が Microsoft Entra ID と同期されているハイブリッド環境の場合、IT 管理者は次のアクションを実行することをお勧めします。 **Microsoft Entra 専用の環境**を持っている場合は、「Microsoft Entra 環境」セクションまでスキップしてください。

#### オンプレミスの Active Directory 環境

Active Directory の管理者として、オンプレミス ネットワークに接続し、PowerShell を開き、次の操作を実行します。

1. Active Directory でユーザーを無効にします。 「[Disable-ADAccount](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/disable-adaccount)」を参照してください。

    ```PowerShell
    Disable-ADAccount -Identity johndoe  
    ```
2. Active Directory でユーザーのパスワードを 2 回リセットします。 「[Set-ADAccountPassword](https://learn.microsoft.com/ja-jp/powershell/module/activedirectory/set-adaccountpassword)」を参照してください。

    注

    ユーザーのパスワードを 2 回変更する理由は、特にオンプレミスのパスワード レプリケーションで遅延が発生した場合に、Pass-the-Hash のリスクを軽減するためです。 このアカウントが侵害されていないと安全に想定できる場合は、パスワードを 1 回だけリセットできます。

    重要

    次のコマンドレット内のサンプルのパスワードは使用しないでください。 パスワードは必ずランダムな文字列に変更してください。

    ```PowerShell
    Set-ADAccountPassword -Identity johndoe -Reset -NewPassword (ConvertTo-SecureString -AsPlainText "p@ssw0rd1" -Force)
    Set-ADAccountPassword -Identity johndoe -Reset -NewPassword (ConvertTo-SecureString -AsPlainText "p@ssw0rd2" -Force)
    ```

#### Microsoft Entra 環境

個々のユーザーについては、Microsoft Entra 管理センターを使用して新しいサインインをブロックし、更新トークンを取り消すことができます。

1. 適切なロールを持つアカウントで[Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。 詳細については、「前提条件」を参照してください。
2. **Entra ID**&gt;**ユーザー**&gt;**すべてのユーザー**を参照し、ユーザーを選択します。
3. [ **アカウントの状態**] で [編集] を選択 **します**。
4. **プロパティ** で、**アカウントを有効にする** のチェックを外し、**保存** を選択します。
5. ユーザーの **[概要** ] ページで、[ **セッションの取り消し**] を選択します。

反復可能な応答アクション、一括応答、またはユーザーの登録済みデバイスの無効化については、PowerShell を開き、必要なスコープ (前提条件を参照) を使用してMicrosoft Graphに接続し、次のアクションを実行します。

1. Microsoft Entra ID でユーザーを無効にします。 「[Update-MgUser](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/update-mguser)」を参照してください。

    ```PowerShell
    $User = Get-MgUser -Search UserPrincipalName:'johndoe@contoso.com' -ConsistencyLevel eventual
    Update-MgUser -UserId $User.Id -AccountEnabled:$false
    ```
2. ユーザーの Microsoft Entra ID 更新トークンを取り消します。 「[Revoke-MgUserSignInSession](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users.actions/revoke-mgusersigninsession)」を参照してください。

    ```PowerShell
    Revoke-MgUserSignInSession -UserId $User.Id
    ```
3. ユーザーのデバイスを無効にします。 「[Get-MgUserRegisteredDevice](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.users/get-mguserregistereddevice)」を参照してください。

    ```PowerShell
    Get-MgUserRegisteredDevice -UserId $User.Id -All | ForEach-Object {
        Update-MgDevice -DeviceId $_.Id -AccountEnabled:$false
    }
    ```

注

これらのステップを実行できる特定のロールの詳細については、[Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)を参照してください

注

Azure AD および MSOnline PowerShell モジュールは、2024 年 3 月 30 日の時点で非推奨となります。 詳細については、[非推奨の最新情報](https://techcommunity.microsoft.com/t5/microsoft-entra-blog/important-azure-ad-graph-retirement-and-powershell-module/ba-p/3848270)を参照してください。 この日以降、これらのモジュールのサポートは、Microsoft Graph PowerShell SDK への移行支援とセキュリティ修正プログラムに限定されます。 非推奨になるモジュールは、2025 年 3 月 30 日まで引き続き機能します。

Microsoft Entra ID (旧称 Azure AD) を使用するには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) に移行することをお勧めします。 移行に関する一般的な質問については、「[移行に関する FAQ](https://learn.microsoft.com/ja-jp/powershell/azure/active-directory/migration-faq)」を参照してください。 *注:* バージョン 1.0.x の MSOnline では、2024 年 6 月 30 日以降に中断が発生する可能性があります。

### アクセスが取り消されたとき

管理者が上記の手順を実行すると、ユーザーは Microsoft Entra ID に関連付けられているアプリケーションの新しいトークンを取得できなくなります。 取り消してからユーザーがアクセスを失うまでの経過時間は、アプリケーションがアクセスを許可する方法によって異なります。

- **アクセス トークンを使用するアプリケーション**の場合、アクセス トークンの有効期限が切れると、ユーザーはアクセスを失います。
- **セッション トークンを使用するアプリケーション**では、トークンの有効期限が切れるとすぐに既存のセッションが終了します。 ユーザーの無効状態がアプリケーションに同期されている場合、そのアプリケーションはユーザーの既存のセッションを自動的に取り消すことができます (そのように構成されている場合)。 所要時間は、アプリケーションと Microsoft Entra ID 間の同期の頻度によって異なります。

### ベスト プラクティス

- 自動化されたプロビジョニングとプロビジョニング解除ソリューションをデプロイします。 アプリケーションからユーザーのプロビジョニングを解除することは、特にセッション トークンを使用するアプリケーションや、ユーザーが Microsoft Entra または Windows Server AD トークンを使用せずに直接サインインできるようにするアプリケーションの場合、アクセスを取り消す効果的な方法です。 自動プロビジョニングとプロビジョニング解除がサポートされていないアプリに対してもユーザーのプロビジョニング解除を行うプロセスを開発します。 アプリケーションがユーザー独自のセッション トークンを取り消し、まだ有効であっても Microsoft Entra アクセス トークンの受け入れを停止するようにします。

    - [Microsoft Entra アプリのプロビジョニング](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)を使用します。 Microsoft Entra アプリ プロビジョニングは、通常、20 から 40 分ごとに自動的に実行されます。 SaaS およびオンプレミスのアプリケーションでユーザーをプロビジョニング解除または非アクティブ化するように、[Microsoft Entra プロビジョニングを構成](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)します。 [Microsoft Identity Manager](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/mim-how-provision-users-adds) を使用してオンプレミス アプリケーションからのユーザーのプロビジョニング解除を自動化していた場合は、Microsoft Entra アプリ プロビジョニングを使用して、[SQL データベース](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-sql-connector-configure)、[AD 以外のディレクトリ サーバー](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-ldap-connector-configure)または[その他のコネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/on-premises-custom-connector)を使用してオンプレミス アプリケーションに接続できます。
    - Windows Server AD を使用するオンプレミス アプリケーションの場合、従業員が退職したときに [AD 内のユーザーを更新する (プレビュー)](https://learn.microsoft.com/ja-jp/entra/id-governance/lifecycle-workflow-on-premises)ように Microsoft Entra ライフサイクル ワークフローを構成できます。
    - 手動プロビジョニング解除を必要とするアプリケーションのプロセスを特定して開発します。 たとえば、Microsoft Entra Entitlement Management を使用して ServiceNow チケットの自動作成を すると、従業員がアクセスできなくなったときにチケットを開くことができます。 管理者とアプリケーション所有者が、必要に応じて、これらのアプリからユーザーをプロビジョニング解除するために必要な手動タスクを迅速に実行できるようにします。
- [Microsoft Intune を使用してデバイスとアプリケーションを管理します](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/device-management)。 Intune で管理されている[デバイスは、出荷時の設定にリセットできます](https://learn.microsoft.com/ja-jp/mem/intune/remote-actions/devices-wipe)。 デバイスが管理されていない場合は、[管理対象アプリから会社のデータをワイプ](https://learn.microsoft.com/ja-jp/mem/intune/apps/apps-selective-wipe)できます。 これらのプロセスは、機密の可能性があるデータをエンド ユーザーのデバイスから削除するのに効果的です。 ただし、いずれのプロセスをトリガーするにも、デバイスがインターネットに接続されている必要があります。 デバイスがオフラインの場合でも、ローカルに保存されているデータにアクセスできます。

注

ワイプ後にデバイス上のデータを回復することはできません。

- 必要に応じて、[データのダウンロードをブロックするために Microsoft Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/defender-cloud-apps/use-case-proxy-block-session-aad) を使用します。 オンラインでのみデータにアクセスできる場合、組織はセッションを監視し、リアルタイム ポリシーの適用を実現できます。
- [Microsoft Entra ID での継続的アクセス評価 (CAE)](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-continuous-access-evaluation) を使用します。 CAE を使用すると、管理者は CAE 対応アプリケーションのセッション トークンとアクセス トークンを取り消すことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-search-enhanced"} -->
## ユーザー管理の機能強化 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-search-enhanced
- Service: entra-id / users
- Article date: 2025-01-06
- Summary: Microsoft Entra ID で利用可能になった、ユーザーの検索、フィルター処理、およびユーザーに関するより多くの情報について説明します。

### 概要

この記事では、Microsoft Entra 管理センターでユーザー管理の機能強化を使用する方法について説明します。 この記事では、**[すべてのユーザー]** ページと **[ユーザー プロファイル]** ページを確認します。

次のような機能が強化されました。

- **さらに読み込む**を選択しなくても、スクロールによって自動でユーザーがより多く表示されるようになりました。
- 市区町村、国/地域、従業員 ID、従業員の種類、外部ユーザーの状態など、その他のユーザー プロパティを列として追加できます。
- カスタム セキュリティ属性、オンプレミス拡張機能属性、マネージャーなど、その他のユーザー プロパティをフィルター処理できます。
- ドラッグ アンド ドロップを使用して列を並べ替えるなど、ビューをカスタマイズするその他の方法。
- カスタマイズした [すべてのユーザー] ビューをコピーして他のユーザーと共有します。
- ユーザーに関する簡単な分析情報を提供し、その他のプロパティを表示および編集できる、強化されたユーザー プロファイル エクスペリエンス。

Note

これらの機能強化は、現在、Azure AD B2C テナントでは使用できません。

### [すべてのユーザー] ページ

**[すべてのユーザー**] ページで使用できる列とフィルターが更新されました。 ユーザーの一覧を管理するための既存の列に加えて、従業員 ID、従業員の雇用日、オンプレミス属性などの列とフィルターとしてユーザー プロパティを追加するオプションが追加されました。

[Image: [すべてのユーザー] ページと [ユーザー プロファイル] ページに表示される新しいユーザー プロパティのスクリーンショット。]

#### 列の並べ替え

2 つの方法のいずれかでページの列を並べ替えることで、リスト ビューをカスタマイズできます。 1 つの方法は、ページ上の列を直接ドラッグ アンド ドロップすることです。 もう 1 つの方法は、[ **列** ] を選択して列ピッカーを開き、特定の列の横にある 3 点の "ハンドル" をドラッグ アンド ドロップすることです。

#### ビューの共有

カスタマイズしたリスト ビューを別のユーザーと共有する場合は、右上にある **[Copy link to current view](https://learn.microsoft.com/ja-jp/entra/identity/users/現在のビューへのリンクをコピーする)** を選択して、ビューへのリンクを共有できます。

### ユーザー プロファイルの機能強化

ユーザー プロファイル ページは、**[概要]**、**[監視]**、**[プロパティ]** の 3 つのタブに整理されました。

#### [概要] タブ

[概要] タブには、次のようなユーザーに関する重要なプロパティと分析情報が含まれています。

- ユーザー プリンシパル名、オブジェクト ID、作成日時、ユーザーの種類などのプロパティ
- ユーザーが所属するグループの数、アクセス権を持つアプリの数、それらに割り当てられているライセンスの数などの選択可能な集計値
- 現在のアカウントが有効になっている状態、最後にサインインした時刻、多要素認証の使用可否、B2B コラボレーション オプションなどのユーザーに関するクイック アラートと分析情報

[Image: [概要] タブの内容を表示する新しいユーザー プロファイルのスクリーンショット。]

Note

十分なロールのアクセス許可がない限り、ユーザーに関する分析情報のいくつかは表示されない場合があります。

#### [監視] タブ

[監視] タブは、過去 30 日間のユーザー サインインを表すグラフの新しいホームです。

#### [プロパティ] タブ

[プロパティ] タブに、さらに多くのユーザー プロパティが含まれるようになりました。 プロパティは、ID、ジョブ情報、連絡先情報、保護者による制限、設定、オンプレミスなどのカテゴリに分割されます。

[Image: [プロパティ] タブの内容を表示する新しいユーザー プロファイルのスクリーンショット。]

プロパティを編集するには、任意のカテゴリの横にある鉛筆アイコンを選択します。そうすることで、新しいエディターにリダイレクトされます。 ここでは、特定のプロパティを検索したり、プロパティ カテゴリをスクロールしたりできます。 **[保存]** を選択する前に、カテゴリ間で 1 つまたは複数のプロパティを編集できます。

[Image: 編集用に開いているユーザー プロファイル プロパティのスクリーンショット。]

Note

一部のプロパティは、読み取り専用の場合、または編集するための十分なロールアクセス許可がない場合、表示または編集できません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/users/users-sharing-accounts"} -->
## アカウントと資格情報の共有 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/users/users-sharing-accounts
- Service: entra-id / users
- Article date: 2026-03-18
- Summary: パスワードベースのシングル サインオンを使用して Microsoft Entra ID で共有アカウントを構成し、複数のユーザーがパスワードを直接共有せずに安全にアプリにアクセスできるようにする方法について説明します。

### 概要

Microsoft Entra の一部である Microsoft Entra ID では、組織が複数のユーザーに対して 1 つのユーザー名とパスワードを使用する必要がある場合があります。これは、多くの場合、次のケースで発生します:

- オンプレミスのアプリケーションであろうと、コンシューマー クラウド サービス (企業のソーシャル メディア アカウントなど) であろうと、ユーザーごとに一意のサインインおよびパスワードを必要とするアプリケーションにアクセスする場合。
- マルチユーザー環境を作成する場合。 昇格された特権を持ち、中心となるセットアップ、管理、および回復アクティビティに使われる、単一のローカルなアカウントが用意されている場合があります。 たとえば、Microsoft 365 のアプリケーション管理者アカウントや、Salesforce のルート アカウントなどです。

従来、このようなアカウントを共有するには、適切なユーザーに資格情報 (ユーザー名とパスワード) を配布するか、複数の信頼できるエージェントがアクセスできる共有の場所にそれらを格納します。

従来の共有のモデルには、以下に示すようないくつかの欠点があります。

- 新しいアプリケーションへのアクセスを有効にするには、アクセスを必要としているすべてのユーザーに資格情報を配布する必要があります。
- 共有されるアプリケーションごとに、固有の共有資格情報のセットが必要な場合があり、ユーザーは複数の資格情報セットを覚えておく必要があります。 ユーザーが多くの資格情報を覚えておく必要がある場合、リスクが高まり、危険な習慣に頼る可能性が増加します（たとえば、パスワードを書き留めるなど）。
- どのユーザーがアプリケーションにアクセスできるのかはっきりしません。
- アプリケーションに "アクセスした" ユーザーを知ることはできません。
- アプリケーションへのアクセス権を削除する必要がある場合は、資格情報を更新して、アプリケーションへのアクセスを必要としているすべてのユーザーに再配布する必要があります。

### 前提条件

共有アカウントを構成するには、次のリソースとロールが必要です。

- 共有アカウントにアクセスする各ユーザーのための Enterprise Mobility Suite (EMS) または Microsoft Entra ID P1、P2 いずれかのライセンス プラン。 詳細については、 [Microsoft Entra のプランと価格に関する説明を](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)参照してください。
- SSO を構成してユーザーを割り当てるための、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) ロールを持つユーザー アカウント。 [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロールも機能します。
- 少なくともセキュリティ グループを作成するための [グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) ロール。 テナントでユーザーがセキュリティ グループを作成できる場合、このロールは必要ありません。
- パスワード ベースのシングル サインオン (SSO) をサポートするアプリケーション。

### Microsoft Entra アカウントの共有

Microsoft Entra ID では、共有のアカウントを使用する上で、上記の欠点を排除した新しい方法を提供します。

Microsoft Entra 管理者は、アクセス パネルを使用して、ユーザーがどのアプリケーションにアクセスできるかを構成し、そのアプリケーションに最適なシングル サインオンの種類を選択します。 これらの種類の 1 つである *パスワード ベースのシングル サインオン*を使用すると、Microsoft Entra ID は、そのアプリのサインイン プロセス中に一種の "ブローカー" として機能します。

ユーザーは、組織アカウントを使用して 1 度サインインします。 このアカウントは、ユーザーがデスクトップまたは電子メールにアクセスするためによく使うものと同じです。 ユーザーは、自分が割り当てられているアプリケーションだけを検出してアクセスできます。 共有アカウントの場合は、このアプリケーション リストに任意の数の共有資格情報を含めることができます。 エンド ユーザーは、使う可能性があるさまざまなアカウントを覚えることも、書き留めておくことも必要ありません。

共有アカウントは、監視を強化し、使いやすさを向上させ、セキュリティを強化します。 資格情報の使用権限を持つユーザーには、共有パスワードが表示されるのではなく、パスワードを調整された認証フローの一部として使用する権限が与えられます。 さらに、一部のパスワード SSO アプリケーションには、Microsoft Entra ID を使って定期的にパスワードをロールオーバー (更新) するオプションがあります。 システムは大きくて複雑なパスワードを使用し、アカウントのセキュリティが強化されます。 管理者は、アプリケーションへのアクセスの許可または取り消しを簡単に行うことができ、アカウントへのアクセス権を持っているユーザーと、過去にアクセスしたユーザーを把握できます。

Microsoft Entra ID では、あらゆる種類のパスワード シングル サインオン アプリケーションについて、Enterprise Mobility Suite (EMS) または Microsoft Entra ID P1 および P2 ライセンス プランを対象とする共有アカウントがサポートされます。 アプリケーション ギャラリーに事前に統合された何千ものアプリケーションのどれとでもアカウントを共有でき、[カスタム SSO アプリ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)を使って独自のパスワード認証アプリケーションを追加できます。

アカウントの共有を有効にする Microsoft Entra の機能は、次のとおりです:

- [パスワード シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-sso-deployment#single-sign-on-options)
- パスワード シングル サインオン エージェント
- [グループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management)
- カスタム パスワード アプリ
- [使用状況と分析情報のレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report)
- エンド ユーザー アクセス ポータル
- [アプリ プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)
- [Azure Marketplace](https://azuremarketplace.microsoft.com/marketplace/apps/category/azure-active-directory-apps)

### 共有アカウントを構成する

パスワード ベースの SSO を使用して共有アカウントを設定するには、次の手順を実行します。

#### 手順 1: アプリケーションを追加する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. **新規アプリケーション** を選択します。
4. 追加するアプリケーションをギャラリーで検索するか、アプリが一覧にない場合は [ **独自のアプリケーションの作成** ] を選択します。 詳細については、「 [エンタープライズ アプリケーションの追加」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)参照してください。

#### 手順 2: パスワードベースの SSO を構成する

1. 追加したアプリケーションを選択し、左側のメニューで [ **シングル サインオン** ] を選択します。
2. シングル サインオン モードとして **[パスワードベース** ] を選択します。
3. アプリケーションのサインイン ページの URL を入力します。
4. **保存**を選びます。

Microsoft Entra ID は、サインイン ページのユーザー名とパスワードの入力フィールドの HTML を解析します。 自動解析が失敗した場合は、サインイン フィールドを手動で構成できます。 詳細な手順については、「 [パスワードベースのシングル サインオンをアプリケーションに追加する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications)参照してください。

#### 手順 3: セキュリティ グループを作成する

同じアプリケーション資格情報を共有するユーザーのセットごとにセキュリティ グループを作成します。 テナントでユーザーがセキュリティ グループを作成できる場合を除き、グループを作成するには、少なくともグループ [管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator) ロールが必要です。

1. **Entra ID**&gt;**Groups**&gt;**All groups** に移動します。
2. **新しいグループ**を選択します。
3. **[グループの種類]** を **[セキュリティ]** に設定します。
4. 共有アカウントとアプリケーション ("Marketing - ソーシャル メディア アカウント" など) を識別する名前を指定します。
5. 共有アカウントへのアクセスを必要とするユーザーをメンバーとして追加します。
6. **を選択して**を作成します。

詳細については、「 [グループを使用して SaaS アプリケーションへのアクセスを管理する」を](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-saasapps)参照してください。

#### 手順 4: グループを割り当てて共有資格情報を設定する

1. **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**を参照し、アプリケーションを選択します。
2. **ユーザーとグループ**を選択し、その後 **ユーザー/グループの追加**を選択します。
3. 作成したセキュリティ グループを選択し、割り当てを完了します。
4. [ **ユーザーとグループ** ] をもう一度選択し、グループの行のチェック ボックスをオンにして、[ **資格情報の更新**] を選択します。
5. アプリケーションの共有ユーザー名とパスワードを入力します。 Microsoft Entra ID は、資格情報を安全に格納し、サインイン時にグループ メンバーに提供します。

ヒント

アプリケーションをデプロイした後、個人は共有アカウントのパスワードを必要としません。 長く複雑なパスワードを設定することを検討してください。 Microsoft Entra ID にはパスワードが格納され、ユーザーにはパスワードが表示されません。

#### 手順 5: パスワードのローテーションを構成する (省略可能)

アプリケーションでサポートされている場合は、パスワードの自動ロールオーバーを構成します。 共有アカウントを設定した管理者でも初期構成後にパスワードを知る必要がないため、パスワードの自動ローテーションによって別のセキュリティ層が提供されます。

### 共有アカウントにアクセスする

管理者が共有アカウントを構成した後、エンド ユーザーは次の方法でアプリケーションにアクセスします。

1. [マイ アプリ ポータル](https://myapps.microsoft.com)に移動し、組織のアカウントでサインインします。
2. 共有アプリケーション タイルを見つけて選択します。 マイ アプリのセキュリティで保護されたサインイン拡張機能がインストールされている場合、アプリケーションが起動し、Microsoft Entra ID によって共有資格情報が自動的に送信されます。

注

パスワードベースの SSO アプリケーションには、My Apps ブラウザー拡張機能が必要です。 ユーザーは、最初にパスワードベースの SSO アプリを起動するときに拡張機能をインストールするように求められます。 この拡張機能は、 [Microsoft Edge](https://microsoftedge.microsoft.com/addons/detail/my-apps-secure-signin-ex/gaaceiggkkiffbfdpmfapegoiohkiipl) と [Google Chrome](https://chrome.google.com/webstore/detail/my-apps-secure-sign-in-ex/ggjhpefgjjfobnfoldnjipclpcfbgbhl) で使用できます。 モバイル デバイスの場合は、Microsoft Edge モバイルを使用し、[**設定]** でパスワード ベースの SSO を有効にします&gt;**プライベートとセキュリティ**&gt;**Microsoft Entra Password SSO**。

エンド ユーザーは、共有資格情報を直接表示または操作しません。 Microsoft Entra ID は、調整された認証フローの一部として資格情報の送信を処理します。

### セキュリティに関する考慮事項

共有アカウントを使用する場合は、次のセキュリティプラクティスに留意してください。

- **資格情報の可視性**: 共有資格情報を使用するアクセス許可を持つユーザーには、実際のパスワードは表示されません。 Microsoft Entra ID は、ユーザーに代わって認証を仲介します。
- **多要素認証 (MFA):** 共有アカウントにアクセスするユーザーに対して MFA を要求して、別の保護層を提供できます。 詳細については、「 [Microsoft Entra 多要素認証のしくみ](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)」を参照してください。
- **アクセス管理**: [Microsoft Entra セルフサービス グループ管理](https://learn.microsoft.com/ja-jp/entra/identity/users/groups-self-service-management) を使用して、アプリケーションにアクセスできるユーザーを管理する機能を委任します。 グループ所有者は、管理者の関与なしにメンバーを追加または削除できます。
- **パスワードの複雑さ**: エンド ユーザーがパスワードを知ったり入力したりする必要がないため、共有アカウントに長く複雑なパスワードを設定します。
- **監査と監視**: Microsoft Entra ID はサインイン アクティビティをログに記録するため、管理者はアプリケーションにアクセスしたユーザーとタイミングを確認できます。
<!-- /MSL-PAGE -->
