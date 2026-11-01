# Microsoft Learn — Microsoft Entra / 条件付きアクセス (part 2)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 6

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/terms-of-use"} -->
## Microsoft Entraの使用条件を条件付きアクセスで設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/terms-of-use
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: 条件付きアクセスMicrosoft Entra使用条件を設定して、リソース アクセスの前にポリシーの受け入れを要求します。 前提条件、詳細な構成、トラブルシューティングのヒントを含む完全なガイド。

### 概要

Microsoft Entra利用規約ポリシーは、リソースへのアクセスを許可する前にエンド ユーザーに情報を提示する簡単な方法を提供します。 このガイドでは、条件付きアクセス ポリシーでMicrosoft Entra使用条件を設定し、アプリケーションとデータにアクセスする前に利用規約ポリシーへの同意を要求する方法について説明します。 これらの利用規約文は一般的なものであっても、グループやユーザーに特化したものであってもよく、複数の言語で提供できます。 管理者は、利用規約に誰が同意して誰が同意しなかったが、提供されたログや API でわかります。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### 前提条件

Microsoft Entra使用条件ポリシーを使用して構成するには、以下が必要です。

- Microsoft Entra ID の P1 ライセンス。
- 利用規約の構成と条件付きアクセス ポリシーを読み取る必要がある管理者には、少なくとも [セキュリティ閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) ロールが割り当てられている必要があります。
- 使用条件と条件付きアクセス ポリシーを作成または変更する必要がある管理者には、少なくとも [条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) ロールが割り当てられている必要があります。
- PDF 形式での使用条件のドキュメント。 PDF ファイルには、表示するコンテンツを自由に指定できます。 モバイル デバイスのユーザーをサポートする場合、PDF の推奨フォント サイズは 24 ポイントです。

#### サービスの制限

追加できる用語数はテナントあたり 40 個以下です。

### 利用規約の追加

使用条件ポリシーのドキュメントが完成したら、次の手順を使って追加します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。
3. [ **新しい用語**] を選択します。

    [Image: 使用条件の設定を指定する新しい使用条件ウィンドウを示すスクリーンショット。]
4. [ **名前** ] ボックスに、使用条件ポリシーの名前を入力します。
5. **利用規約ドキュメントについては**、最終利用規約ポリシー PDF を参照して選択します。
6. 利用規約ポリシーのドキュメントの言語を選択します。 言語オプションを使用して、言語が異なる複数の使用条件ドキュメントをアップロードできます。 エンド ユーザーに表示される利用規約ポリシーのバージョンは、ブラウザーの設定に基づきます。
7. [ **表示名** ] ボックスに、ユーザーがサインインしたときに表示されるタイトルを入力します。
8. エンド ユーザーに同意する前に使用条件ポリシーの表示を要求するには、[ **ユーザーに使用条件の展開を要求** する] を **[オン]** に設定します。
9. エンド ユーザーがアクセスするすべてのデバイスで利用規約ポリシーに同意することを要求するには、[ **すべてのデバイスでユーザーに同意を要求** する] を **[オン] に**設定します。 このオプションが有効な場合、ユーザーは他のアプリケーションのインストールが必要になる可能性があります。 詳細については、「 デバイスごとの使用条件」を参照してください。
10. スケジュールに従って利用規約ポリシーの同意を期限切れにする場合は、[ **有効期限の同意** ] を **[オン] に**設定します。 オンに設定すると、さらに 2 つのスケジュール設定が表示されます。

    1. 使用条件ポリシーの有効期限のスケジュールを指定するには、[ **有効期限の開始日** ] と [ **頻度** ] 設定を使用します。 次の表に、いくつかの設定例とその結果を示します。

        | 期限切れの開始日 | 頻度 | 結果 |
        | --- | --- | --- |
        | 今日の日付 | 月単位 | ユーザーは本日から利用規約ポリシーに同意する必要があります。また、毎月再同意する必要があります。 |
        | 未来の日付 | 月単位 | ユーザーは本日から利用規約ポリシーに同意する必要があります。 未来の日付が来ると、同意の有効期限が切れ、ユーザーは毎月再同意する必要があります。 |

        たとえば、期限切れの開始日を **1 月 1** 日に設定し、頻度を **月単位**に設定した場合、次の例は 2 人のユーザーに対して有効期限が発生する可能性があります。

        | ユーザー | 最初の受諾日 | 最初に有効期限が切れる日付 | 2 回目に有効期限が切れる日付 | 第三の有効期限 |
        | --- | --- | --- | --- | --- |
        | アリス | 1 月 1 日 | 2 月 1 日 | 3 月 1 日 | 4 月 1 日 |
        | ボブ | 1 月 15 日 | 2 月 1 日 | 3 月 1 日 | 4 月 1 日 |
    2. [ **再同意前の期間 (日数)]** 設定を使用して、ユーザーが利用規約ポリシーを再同意する必要がある日数を指定します。 このオプションにより、ユーザーは自分たちのスケジュールに従うことが可能になります。 たとえば、期間を **30** 日に設定した場合、次の例は、2 人のユーザーの有効期限が発生する方法です。

        | ユーザー | 最初の受諾日 | 最初に有効期限が切れる日付 | 2 回目に有効期限が切れる日付 | 第三の有効期限 |
        | --- | --- | --- | --- | --- |
        | アリス | 1 月 1 日 | 1 月 31 日 | 3 月 2 日 | 4 月 1 日 |
        | ボブ | 1 月 15 日 | 2 月 14 日 | 3 月 16 日 | 4 月 15 日 |

        複数の設定を一緒に使用することもできますが、通常は「有効期限の同意」または「再同意が必要な期間（日）」のいずれかを使用します。

        重要

        使用されている設定に関係なく、同意の**期限**が切れたユーザーや**再同意が必要な期間（日数）**が過ぎたユーザーは、セッションが期限切れになった場合にのみ、再度同意を求められます。
11. [ **条件付きアクセス**] **で、[条件付きアクセスで適用] ポリシー テンプレート** の一覧を使用して、使用条件ポリシーを適用するテンプレートを選択します。

    | テンプレート | 説明 |
    | --- | --- |
    | **カスタム ポリシー** | 使用条件ポリシーが適用されるユーザー、グループ、およびアプリを選択します。 |
    | **後で条件付きアクセス ポリシーを作成する** | この利用規約ポリシーは、条件付きアクセス ポリシーを作成するときに、制御の許可一覧に表示されます。 |

    重要

    条件付きアクセス ポリシー制御 (使用条件ポリシーを含む) では、サービス アカウントの適用はサポートされていません。 条件付きアクセス ポリシーからすべてのサービス アカウントを除外します。

    カスタム条件付きアクセス ポリシーを使うと、特定のクラウド アプリケーションやユーザー グループまで、きめ細かい利用規約ポリシーを有効にできます。 詳細については、「 [クイック スタート: クラウド アプリにアクセスする前に利用規約に同意する必要がある」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-require-terms-of-use)参照してください。
12. **を選択して**を作成します。

    カスタム条件付きアクセス テンプレートを選んだ場合、表示される新しい画面でカスタム条件付きアクセス ポリシーを作成できます。 新しい利用規約ポリシーが表示されるようになります。

#### デバイスごとの利用規約

[ **すべてのデバイスでユーザーに同意を要求する** ] 設定を使用すると、エンド ユーザーがリソースへのアクセスに使用するすべてのデバイスで使用条件ポリシーに同意するように要求できます。 エンド ユーザーのデバイスをMicrosoft Entra IDに登録する必要があります。 デバイスが登録されると、デバイス ID を使用して各デバイスに利用規約ポリシーが適用されます。 これらのエクスペリエンスは、デバイスと使用されるプラットフォームまたはソフトウェアを参加させるアクセス許可に依存します。 詳細については、「Microsoft Entra ID の device ID」を参照してください。

デバイスごとの利用規約には、次の制約があります。

- Microsoft Intune登録アプリ `Application ID: d4ebce55-015a-49b5-a083-c84d1797ae8c` はサポートされていません。 利用規約を必要とするすべての条件付きアクセス ポリシーからこれが確実に除外されているようにします。
- Microsoft Entra B2B ユーザーはサポートされていません。
- デバイスごとの使用条件を使用していないお客様には、Microsoft Edgeのすべてのサインイン時に使用条件に同意するように求められるユーザーがいる場合があります。 仕事用プロファイルを Microsoft Edge プロファイルと同期してシングル サインオンを有効にするようにユーザーに指示します。これにより、Edge プロファイルには、ユーザーが使用条件に同意したと表示されます。

#### ポリシーの変更

条件付きアクセス ポリシーは直ちに有効になります。 この強制が行われると、管理者はMicrosoft Entra管理センターにエラーが表示されることがあります。 新しいポリシーを満たすには、管理者はサインアウトしてサインインし直す必要があります。

重要

スコープ内のユーザーは、次の場合に新しいポリシーを満たすためにサインアウトしてサインインし直す必要があります。

- 利用規約ポリシーで条件付きアクセス ポリシーが有効になった場合
- または、第 2 の利用規約ポリシーが作成された場合

### 利用規約の詳細を編集する

利用規約ポリシーの詳細の一部を編集できますが、既存のドキュメントを変更することはできません。 詳細の編集方法を次の手順で説明します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。
3. 編集する利用規約ポリシーを選択します。
4. [ **用語の編集] を選択します**。
5. [利用規約の編集] ペインでは、次のオプションを変更できます。

    - **名前** – エンド ユーザーと共有されていない使用条件の内部名。
    - **表示名** – 使用条件を表示するときにエンド ユーザーが表示できる名前。
    - **使用条件の拡張をユーザーに要求** する – このオプションを **[オン]** に設定すると、エンド ユーザーは同意する前に利用規約ポリシー ドキュメントを展開するように強制されます。
    - **既存の使用条件ドキュメントを更新**できます。
    - 既存の使用条件に **言語を追加** できます。
6. 完了したら、[ **保存]** を選択して変更を保存します。

その他の設定を変更する場合は、新しい使用条件を作成する必要があります。

#### 既存の使用条件のバージョンまたは PDF を更新する

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。
3. 編集する利用規約ポリシーを選択します。
4. [ **用語の編集] を選択します**。
5. 新しいバージョンを更新する言語の場合は、アクション列の下にある **[更新** ] を選択します。
6. 右側のウィンドウで、新しいバージョンの PDF をアップロードします。
7. ユーザーが次回サインインするときにこの新しいバージョンを受け入れるように要求する場合は、[ **再同意** を要求する] のトグル オプションもあります。

    - ユーザーに再同意を要求する場合は、次回、条件付きアクセス ポリシーに定義されているリソースにアクセスしようとしたときに、この新しいバージョンに同意するように求めるメッセージが表示されます。
    - ユーザーに再同意を要求しない場合は、以前の同意が最新状態となり、前に同意していない新しいユーザー、または同意の有効期限が切れたユーザーにのみ、新しいバージョンが表示されます。 セッションの有効期限が切れるまで、 **再同意を要求** する場合、ユーザーは新しい使用条件に同意する必要はありません。 確実に再同意するには、このケースの使用条件を削除して再作成するか、新規作成します。

    [Image: 再同意オプションが強調表示されている [使用条件の編集] ウィンドウを示すスクリーンショット。]
8. 新しい PDF をアップロードして再同意を決定したら、ウィンドウの下部にある [ **追加]** を選択します。
9. ドキュメント列に、最新のバージョンが表示されます。

#### 言語の追加

次の手順では、使用条件に言語を追加する方法を説明します。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。
3. 編集する利用規約ポリシーを選択します。
4. [ **用語の編集] を選択します**。
5. ページの下部にある [ **言語の追加]** を選択します。
6. [ **使用条件言語の追加** ] ウィンドウで、ローカライズした PDF をアップロードし、言語を選択します。

    [Image: 使用条件が選択され、詳細ウィンドウに [言語] タブが表示されているスクリーンショット。]
7. 「 **言語を追加する**」を選択します。
8. **保存**を選びます。
9. [ **追加]** を選択して言語を追加します。

##### 以前のバージョンの使用条件を表示する

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Security Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。
3. バージョン履歴を表示する利用規約ポリシーを選択します。
4. **[言語とバージョン履歴]** を選択します。
5. [ **以前のバージョンを表示] を選択します**。
6. ドキュメントの名前を選択して、そのバージョンをダウンロードできます。

### 同意したユーザーと拒否したユーザーのレポートの表示

**[利用規約]** ページには、同意して拒否したユーザーの数が表示されます。 これらの数および同意したユーザーと拒否したユーザーは、利用規約ポリシーが有効な間、保存されます。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Security Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-reader) としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。

    [Image: 利用規約ページで同意または拒否したユーザーの数を示すスクリーンショット。]
3. 使用条件ポリシーの場合は、[ **承諾済み** ] または [ **拒否済** み] の下の数値を選択して、ユーザーの現在の状態を表示します。

    1. 既定では、次のページに、使用条件への同意に関する各ユーザーの現在の状態が表示されます。
    2. 以前の同意イベントを表示する場合は、[**現在の状態**] ドロップダウンから **[すべて**] を選択できます。 これで、各ユーザーのイベントを各バージョンと内容について詳しく確認できます。
    3. または、[ **バージョン** ] ドロップダウンから特定のバージョンを選択して、その特定のバージョンを受け入れたユーザーを確認することもできます。
4. 個々のユーザーの履歴を表示するには、省略記号 (**...**) を選択し、[ **履歴の表示]** を選択します。 履歴の表示ウィンドウでは、すべての同意、拒否、および有効期限切れの履歴を確認できます。

#### ユーザー受け入れレコードの削除

次の場合に、ユーザー受け入れレコードが削除されます。

- 管理者が使用条件を明示的に削除した場合。
    - この変更が発生した場合、その特定の使用条件に関連付けられているすべての受け入れレコードも削除されます。
- テナントは、Microsoft Entra ID P1 または P2 ライセンスを失います。
- テナントは削除されます。

### Microsoft Entraの監査ログを表示する

Microsoft Entra使用条件ポリシーの変更は、Microsoft Entra監査ログにキャプチャされます。 各ユーザーの同意によって、監査ログ内のイベントがトリガーされます。 ログのデータ保持は、ライセンスによって異なります。 詳細については、「[Microsoft Entra データ保持](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-reports-data-retention)」を参照してください。

管理センターでMicrosoft Entraの監査ログを使用するには、次の手順に従います。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Reports 閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。
3. 利用規約ポリシーを選択します。
4. [ **監査ログの表示]** を選択します。
5. Microsoft Entra監査ログ画面では、指定されたリストを使用して情報をフィルター処理して、特定の監査ログ情報を対象にすることができます。

    [ **ダウンロード** ] を選択して、ローカルで使用するために .csv ファイルに情報をダウンロードすることもできます。

    [Image: Microsoft Entra監査ログ画面には、日付、ターゲットポリシー、開始者、およびアクティビティが一覧表示されています。]

    ログを選択すると、その他のアクティビティの詳細を含むウィンドウが表示されます。

    [Image: アクティビティ、アクティビティの状態、開始者、ターゲット ポリシーを示すログのアクティビティの詳細を示すスクリーンショット。]

### ユーザーに表示される利用規約の外観

使用条件ポリシーが作成され、適用されると、スコープ内のユーザーには、サインイン中に次の画面が表示されます。

[Image: ユーザーがサインインしたときに表示される使用条件ポリシーの例を示すスクリーンショット。]

ユーザーは利用規約ポリシーを表示し、必要な場合はボタンを使用して拡大/縮小できます。ユーザーは、使用条件ポリシーに 1 回だけ同意すればよく、以降のサインインで使用条件ポリシーが再び表示されることはありません。

#### ユーザーが利用規約を確認する方法

ユーザーは、以下の手順を使用して、同意した使用条件ポリシーを確認できます。

1. https://myaccount.microsoft.com/ にサインインします。
2. **[設定] と [プライバシー] を選択します**。
3. [**プライバシー**] を選択します。
4. [ **組織の通知**] で、確認する使用条件ステートメントの横にある **[表示** ] を選択します。

### 利用規約を削除する

次の手順を使って、古い利用規約ポリシーを削除できます。

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Terms of use** に移動します。
3. 削除する利用規約ポリシーを選びます。
4. [ **用語の削除] を選択します**。
5. 続行するかどうかを確認するメッセージが表示されたら、[ **はい**] を選択します。
    1. 利用規約ポリシーが表示されなくなります。

### プラットフォームとブラウザーのサポート

Microsoft Entra使用条件は、次のプラットフォームとブラウザーで最適に機能するように設計されています。

サポートされているデスクトップおよびモバイル オペレーティング システム (最新の安定バージョン):

- Windows
- macOS
- Linux
- Android
- iOS

サポートされている最新の Web ブラウザー (最新の安定バージョン):

- Microsoft Edge
- Mozilla Firefox
- グーグルクローム
- Safari

#### その他の構成の機能

以前に一覧に記載されていないオペレーティング システムまたはブラウザー上のユーザー、またはサポートされているオペレーティング システムまたはブラウザーの以前のバージョンを使用しているユーザーは、サインイン プロセス中に引き続き使用条件に同意できる場合があります。 サポートされていない構成では、一部の機能、ビジュアル レンダリング、またはパフォーマンスが低下する可能性があります。 EdgeHTML や Trident/MSHTML などのレガシ エンジンに基づくブラウザーなど、サポート対象として明示的に一覧表示されていないオペレーティング システムとブラウザーは、アクティブにテストまたはサポートされていません。

#### Web ビューを使用するデスクトップ アプリケーションとモバイル アプリケーション

最新の HTML5 準拠ブラウザー エンジン (Blink、Gecko、WebKit など) の最新の安定したバージョンに基づいて Web ビューを利用するアプリケーションでは、通常、ユーザーがサインイン時に使用条件に同意できる必要があります。 ただし、一部の機能、ビジュアル レンダリング、またはパフォーマンスの低下が発生する可能性があります。 すべての特定の Web ビューの実装がアクティブにテストまたはサポートされているわけではありません。

#### 問題を報告する

アプリケーション内の特定のオペレーティング システム、ブラウザー、ブラウザー エンジン、または Web ビューの実装で利用規約への同意に関する問題が発生した場合は、サポート ケースを開きます。 サポート可能性は、ケース バイ ケースで評価されます。

### B2B ゲスト

条件付きアクセスと利用規約ポリシーを使用すると、B2B ゲスト ユーザーに直接ポリシーを適用できます。 招待の利用フロー中に、ユーザーに対して利用規約ポリシーが表示されます。

利用規約ポリシーは、ユーザーがMicrosoft Entra IDにゲスト アカウントを持っている場合にのみ表示されます。 SharePoint Online には現在、[外部共有受信者エクスペリエンス](https://learn.microsoft.com/ja-jp/sharepoint/what-s-new-in-sharing-in-targeted-release)があり、ユーザーがゲスト アカウントを持つ必要のないドキュメントまたはフォルダーを共有できます。 この場合、使用条件ポリシーは表示されません。

#### Azure Information Protection

Azure Information Protection アプリの条件付きアクセス ポリシーを構成し、ユーザーが保護されたドキュメントにアクセスするときに利用規約ポリシーを要求できます。 この構成では、ユーザーが保護されたドキュメントに初めてアクセスする前に、使用条件ポリシーがトリガーされます。

#### Microsoft Intune登録

Microsoft Intune登録アプリの条件付きアクセス ポリシーを構成し、Intune でデバイスを登録する前に使用条件ポリシーを要求できます。 詳細については、 [組織のブログ投稿に適した用語の選択ソリューションを参照してください](https://go.microsoft.com/fwlink/?linkid=2010506&amp;clcid=0x409)。

注

Intune 登録アプリは、 デバイスごとの使用条件ではサポートされていません。

iOS/iPadOS 自動デバイス登録の場合、Microsoft Entra使用条件ポリシーにカスタム URL を追加しても、ユーザーはセットアップ アシスタントの URL からポリシーを開いて読み取ることはできません。 このポリシーは、Company Portal Web サイトまたは Company Portal アプリからセットアップ アシスタントが完了した後、ユーザーが読み取ることができます。

### よく寄せられる質問

**Q: ユーザーのサインインが 2 つ表示されるのはなぜですか? 1 回の中断と 1 回の成功です。** A: ユーザーがまだ使用条件に同意していない場合、管理者に 2 つのサインインが表示される可能性がありますが、このシナリオは設計によって異なります。 これらのエントリは関連付け ID を共有します。

[Image: 利用規約が適用されている場合のサインイン ログでの失敗と成功の例のスクリーンショット。]

ユーザーがトークンで使用条件ポリシーの承諾を証明できないため、サインインが 1 回中断されます。 サインイン ログの **追加の詳細** フィールドには、次のメッセージが含まれます。

>
> ユーザーは認証を終了する前に追加の要件を満たす必要があり、別のページ (使用条件やサードパーティの MFA プロバイダーなど) にリダイレクトされました。 このコードだけで、ユーザー側のサインインの失敗が示されるわけではありません。 サインイン ログは、このチャレンジが成功したか失敗したかを示す場合があります。

ユーザーが使用条件に同意すると、他のサインインは成功します。

**Q: 使用条件が有効になっている場合、PowerShell を使用してサインインできません。** A:利用規約は、対話形式で認証する場合にのみ同意できます。

**Q: ユーザーが利用規約に同意したタイミングと確認方法を教えてください。** A: [使用条件] ページで、[ **同意済み**] の下の番号を選択します。 Microsoft Entra監査ログで、受け入れられたアクティビティを表示または検索することもできます。 詳細については、「同意したユーザーと拒否したユーザーのレポートを表示」と「Microsoft Entra の監査ログを表示」を参照してください。

**Q: 情報の保存期間はどのくらいですか?** A:利用規約レポートのユーザーの数および同意したユーザーと拒否したユーザーの数は、利用規約が有効な間、保存されます。 Microsoft Entra監査ログは 30 日間保存されます。

**Q: 使用条件の詳細の概要とMicrosoft Entra監査ログで異なる数の同意が表示されるのはなぜですか?** A: 使用条件の詳細の概要は、その使用条件ポリシーの有効期間中保存されます。 Microsoft Entra監査ログは 30 日間保存されます。

**Q: 利用規約の詳細の概要とエクスポートされた CSV レポートで異なる数の同意が表示されるのはなぜですか。** A: 利用規約の詳細の概要には、ポリシーの現在のバージョンの集計された同意数が反映されます (毎日 1 回更新されます)。 有効期限が有効になっているか、使用条件の同意が更新された場合 (再同意が必要です)、同意が期限切れになっているため、詳細の概要のカウントがリセットされ、このページに現在のバージョンの数が表示されます。 同意のすべての履歴は引き続き CSV レポートで取得できます。

**Q: ハイパーリンクが利用規約ポリシー PDF ドキュメントに含まれている場合、エンド ユーザーはそれらを選択できますか。** A: はい。エンド ユーザーは、他のページへのハイパーリンクを選択できますが、ドキュメント内のセクションへのリンクはサポートされていません。 また、Microsoft Entra My Apps/MyAccount ポータルからアクセスした場合、使用ポリシー PDF のハイパーリンクは機能しません。

**Q: 利用規約ポリシーで複数の言語をサポートできますか?** A:はい。 管理者は、複数の PDF ドキュメントをアップロードして各言語に対応付けることができます。 エンド ユーザーがサインインすると、利用規約機能によってブラウザーの言語設定が確認され、一致するドキュメントが表示されます。 一致するものがない場合は、アップロードされた最初のドキュメントである既定のドキュメントが表示されます。 Microsoft Teamsなどの [Web アカウント マネージャー](https://learn.microsoft.com/ja-jp/windows/uwp/security/web-account-manager) を使用するデスクトップ アプリケーションWindowsは、アプリケーション固有の言語設定ではなく、オペレーティング システム言語を使用します。

**Q: 利用規約ポリシーはいつトリガーされますか?** A: 使用条件ポリシーは、サインイン エクスペリエンスの間にトリガーされます。

**Q: 使用条件ポリシーの対象となるアプリケーションは何ですか?** A:最新の認証を使ったエンタープライズ アプリケーションに条件付きアクセス ポリシーを作成できます。 詳細については、 [エンタープライズ アプリケーションを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal)参照してください。

**Q: 特定のユーザーまたはアプリに複数の利用規約ポリシーを追加できますか。** A:はい、そのグループやアプリケーションを対象とする複数の条件付きアクセス ポリシーを作成することで可能です。 複数の使用条件の対象になっているユーザーは、一度に 1 つのポリシーに同意する必要があります。

**Q: ユーザーが利用規約ポリシーを拒否した場合はどうなりますか?** A: ユーザーは、アプリケーションへのアクセスをブロックされます。 ユーザーは、アクセスするには、もう一度サインインして使用条件に同意する必要があります。

**Q: 以前に同意した使用条件ポリシーを受け入れ解除することは可能ですか?** A: 以前に同意した使用条件ポリシーを確認できますが、現在、同意を取り消す方法はありません。

**Q: Intune の使用条件も使用している場合はどうなりますか?** A: Microsoft Entra使用条件と [Intune の使用条件](https://learn.microsoft.com/ja-jp/mem/intune/enrollment/terms-and-conditions-create) の両方を構成する場合、ユーザーは両方に同意する必要があります。 詳細については、 [組織のブログ投稿に適した用語の選択ソリューションを参照してください](https://go.microsoft.com/fwlink/?linkid=2010506&amp;clcid=0x409)。

**Q: サービス利用規約で認証に使用されるエンドポイントは何ですか?** A: 使用条件では、認証に https://tokenprovider.termsofuse.identitygovernance.azure.com、 `https://myaccount.microsoft.com`、 https://account.activedirectory.windowsazure.comの各エンドポイントを使用します。 組織に登録用の URL の許可リストがある場合は、これらのエンドポイントを、サインイン用のMicrosoft Entra エンドポイントと共に許可リストに追加する必要があります。

**Q: ユーザーは、Microsoft Edgeにサインインするたびに利用規約に同意する必要があることを報告しています。 なぜこれが起こっているのか。** A: ユーザーが職場アカウントを使用して Edge プロファイルにサインインすると、Edge はシングル サインオンを有効にするプライマリ更新トークン (PRT) を取得します。 この PRT により、認証システムはユーザーと以前の利用規約への同意を認識できます。

Edge プロファイルにサインインしないと、各認証に適切なセッション コンテキストが含まれていない可能性があるため、Microsoft Entraは新しいセッションの外観を確認し、利用規約への同意を再度求めます。 ユーザーは、仕事用プロファイルを自分の Edge プロファイルと同期して ID を確立し、使用条件に同意したことを Edge が記憶するようにする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/troubleshoot-conditional-access"} -->
## 条件付きアクセスでのサインインに関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: この記事では、条件付きアクセス ポリシーによって予期しない結果が発生した場合の対処方法について説明します。

### 概要

この記事では、エラー メッセージと Microsoft Entra サインイン ログを確認することで、条件付きアクセスに関連する予期しないサインイン結果を修正します。

### "すべて" の結果を選択する

条件付きアクセス フレームワークを使用すると、構成の柔軟性が大幅に向上します。 ただし、この柔軟性は、不要な結果を避けるために、リリースする前に各構成ポリシーを慎重に確認する必要があります。 このコンテキストでは、 **すべてのユーザー/グループ/リソース**などの完全なセットに影響する割り当てに特別な注意を払います。

次の構成は使用しないでください。

**すべてのユーザー、すべてのリソースに対して:**

- **アクセスをブロック** する - この構成により、組織全体がブロックされます。
- **デバイスを準拠としてマークする必要がある** - デバイスをまだ登録していないユーザーの場合、このポリシーは Intune ポータルへのアクセスを含むすべてのアクセスをブロックします。 デバイスが登録されていない管理者の場合、このポリシーを使用すると、ポリシーを変更するために再びサインインできなくなります。
- **ハイブリッド Microsoft Entra ドメイン参加済みデバイスが必要** - このポリシーは、Microsoft Entra ハイブリッド参加済みデバイスがない場合に、組織内のすべてのユーザーのアクセスをブロックすることもできます。
- **アプリ保護ポリシーが必要** - Intune ポリシーを持っていない場合、このポリシーは組織内のすべてのユーザーのアクセスをブロックすることもできます。 [Intune アプリ保護ポリシー](https://learn.microsoft.com/ja-jp/intune/intune-service/apps/app-protection-policy)を持つクライアント アプリがない管理者の場合、このポリシーを使用すると、Intune や Azure などのポータルに戻ることはできなくなります。

**すべてのユーザー、すべてのリソース、すべてのデバイス プラットフォームに対して:**

- **アクセスのブロック** - この構成では組織全体がブロックされます。

### 条件付きアクセスのサインインの割り込み

表示されるエラー メッセージを確認します。 Web ブラウザーでサインインする場合は、通常、エラー ページに詳細情報が表示されます。 多くの場合、この情報は問題を説明し、解決策を提案します。

[Image: 準拠しているデバイスが必要であることを示すサインイン エラーのスクリーンショット。]

このエラーでは、会社のモバイル デバイス管理ポリシーを満たすデバイスまたはクライアント アプリケーションからのみアプリケーションを使用できることを示すメッセージが表示されます。 ここでは、アプリケーションとデバイスがポリシーを満たしていません。

### Microsoft Entra サインイン イベント

サインインの中断に関する詳細情報を取得するには、Microsoft Entra サインイン イベントを確認して、適用された条件付きアクセス ポリシーとその理由を確認します。

問題の詳細については、最初のエラー ページで **[詳細** ] を選択します。 **[詳細]** を選択すると、Microsoft Entra サインイン イベントを検索して、ユーザーが見た特定のエラー イベントや Microsoft でサポート インシデントを開くときに役立つトラブルシューティング情報が表示されます。

[Image: Web ブラウザーのサインインが中断された条件付きアクセスの詳細をより示すスクリーンショット。]

次の手順に従って、適用された条件付きアクセス ポリシーとその理由を確認します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;に移動し、**モニタリングとヘルス**&gt;の**サインイン ログ**を参照します。
3. 確認するサインインのイベントを見つけます。 フィルターと列を追加または削除して、不要な情報を除外します。

    1. 次のようなフィルターを追加して範囲を絞り込みます。
        1. **関連付け ID**: 調査対象の特定のイベントがある場合。
        2. **条件付きアクセス**: ポリシーの失敗/成功を表示します。 フィルターの範囲を設定して、失敗のみを表示するように結果を制限します。
        3. **ユーザー名**: 特定のユーザーに関連する情報を表示します。
        4. **日付**: 問題の概算時間に範囲を設定します。
        5. 呼び出されたリソースに関連する情報を表示する**リソース**。

    [Image: サインイン ログで条件付きアクセス フィルターを選択することを示すスクリーンショット。]
4. ユーザーのサインインエラーに対応するサインイン イベントを見つけたら、[ **条件付きアクセス** ] タブを選択します。[条件付きアクセス] タブには、サインインが中断された特定のポリシーが表示されます。

    1. **[トラブルシューティングおよびサポート]** タブの情報により、コンプライアンス要件を満たしていなかったデバイスなど、サインインが失敗した理由が明確に示される場合があります。
    2. さらに調査するには、[ **ポリシー名**] を選択してポリシーの構成をドリルダウンします。 **[ポリシー名]** を選択すると、選択したポリシーのポリシー構成ユーザー インターフェイスが表示され、確認と編集が行われます。
    3. 条件付きアクセス ポリシーの評価に使用された **クライアント ユーザー**と**デバイスの詳細**は、サインイン イベントの **[基本情報]** 、 **[場所]** 、 **[デバイス情報]** 、 **[認証の詳細]** 、 **[追加情報]** タブでも参照できます。

#### ポリシーが意図したとおりに機能しない

サインイン イベントでポリシーの右側にある省略記号を選択すると、ポリシーの詳細が表示されます。 このオプションは、ポリシーが適用された理由の詳細を管理者に提供します。

[Image: 条件付きアクセス ポリシーの詳細を示すスクリーンショット。ポリシーが適用されたか否かの理由を確認します。]

左側にはサインイン時に収集された詳細情報が表示され、右側には適用された条件付きアクセス ポリシーの要件をそれらの詳細が満たすかどうかが示されます。 条件付きアクセス ポリシーは、すべての条件が満たされているか、構成されていない場合にのみ適用されます。

イベント内の情報がサインイン結果を理解したり、目的の結果を得るためにポリシーを調整したりするのに十分でない場合は、サインイン診断ツールを使用します。 サインイン診断は**基本情報**&gt;**トラブルシューティングイベント**の下にあります。 サインイン診断の詳細については、「 [Microsoft Entra ID でのサインイン診断とは」を](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-use-sign-in-diagnostics)参照してください。 [What If ツールを使って条件付きアクセス ポリシーのトラブルシューティングを行う](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool)こともできます。

サポート インシデントを送信する必要がある場合は、サインイン イベントの要求 ID、時刻、日付をインシデントの詳細に含めます。 この情報は、Microsoft サポートが懸念している特定のイベントを見つけるのに役立ちます。

#### 条件付きアクセスの一般的なエラー コード

| サインイン エラー コード | エラー文字列 |
| --- | --- |
| 53000 | デバイスが準拠していない |
| 53001 | DeviceNotDomainJoined |
| 53002 | 使用したアプリケーションは承認されたアプリではありません |
| 53003 | 条件付きアクセスによりブロックされています |
| 53004 | リスクによりProofUpがブロックされました |
| 53009 | アプリケーションで Intune 保護ポリシーを適用する必要がある |

[Microsoft Entra 認証および承認](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)のエラーコードについて詳しく学びましょう。 一覧のエラー コードには、 `AADSTS` のプレフィックスと、ブラウザーに表示されるコード ( `AADSTS53002`など) が表示されます。

### サービスの依存関係

一部のシナリオでは、クラウド アプリが条件付きアクセス ポリシーによってブロックされるリソースに依存しているため、ユーザーがブロックされます。

サービスの依存関係を確認するには、サインインによって呼び出されたアプリケーションとリソースのサインイン ログを確認します。 次のスクリーンショットでは、アプリケーションは **Azure portal** ですが、リソースは **Azure Resource Manager** です。 このシナリオを対象にするには、条件付きアクセス ポリシー内のすべてのアプリケーションとリソースを結合します。

[Image: リソースを呼び出すアプリケーションを示すサインイン ログのスクリーンショット。このシナリオは、サービスの依存関係とも呼ばれます。]

### オーディエンス報告

ユーザーが Microsoft Teams などのアプリにサインインすると、実際には Teams チャット、Outlook 予定表、Excel ドキュメントなどの複数のリソースへのアクセスが要求されます。 ユーザーは Teams クライアントにのみサインインしていると思われるかもしれませんが、条件付きアクセス ポリシーはこれらすべてのリソースに適用されます。 たとえば、管理者が SharePoint または特定の SharePoint サイトへのアクセスを制限する場合、ユーザーが Teams にのみサインインしていると思う場合でも、ポリシーが適用されます。

サインイン ログで対象ユーザーがレポートすると、管理者はサインイン イベントの一部として要求されたすべてのリソースを確認できます。 これは、すべての有効なポリシーまたはレポート専用ポリシーの [リソース] セクションの下に **[対象ユーザー** ] として表示されます。

[Image: 条件付きアクセス ポリシーの詳細と展開されたリソースと対象ユーザーの情報を示すサインイン ログ エントリのスクリーンショット。]

管理者は、[条件付きアクセス] タブでポリシーを選択した後、サインイン ログで **対象ユーザー** を検索します。

管理者は対象ユーザー レポートを使用して、CA ポリシーがサインイン イベントに適用される理由または適用されない理由を確認します。 たとえば、一覧の対象ユーザーの 1 つがポリシーのスコープ内にあるため、ポリシーは特定のサインイン イベントに適用されます。

### ロックアウトされた場合の対処方法

条件付きアクセス ポリシーの設定が正しくないためロックアウトされている場合:

- 組織内の他の管理者がまだブロックされていないかどうかを確認します。 アクセス権を持つ管理者は、サインインに影響を与えるポリシーを無効にすることができます。
- 組織内の管理者がポリシーを更新できない場合は、 [サポート リクエストを送信](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support#open-a-support-request)します。 Microsoft はレビューをサポートし、確認後に、アクセスを妨げる条件付きアクセス ポリシーを更新します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/troubleshoot-policy-changes-audit-log"} -->
## 条件付きアクセス ポリシーの変更に関するトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-policy-changes-audit-log
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: Microsoft Entra 監査ログを使用して、環境内の条件付きアクセス ポリシーの変更を特定してトラブルシューティングする方法について説明します。

### 概要

Microsoft Entra 監査ログは、環境内で条件付きアクセス ポリシーの変更が発生した理由とその方法のトラブルシューティングを行う際の貴重な情報源です。

監査ログ データは既定で 30 日間保持されるため、組織によっては不十分な場合があります。 組織は、Microsoft Entra ID の診断設定を次のように変更することで、データを長く格納できます。

- データを Log Analytics ワークスペースに送信する
- データをストレージ アカウントにアーカイブする
- Event Hubs へのデータのストリーム配信
- データをパートナー ソリューションに送信する

これらのオプションは、**Entra ID**&gt;**監視とヘルス**&gt;**診断設定**&gt;**設定を編集**にあります。 診断設定がない場合は、「診断 [設定を作成してプラットフォームのログとメトリックを別の宛先に送信する」](https://learn.microsoft.com/ja-jp/azure/azure-monitor/essentials/diagnostic-settings) を参照してください。

### 監査ログの使用

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[レポート閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)としてサインインします。
2. **Entra ID**&gt;**監視と健康状態**&gt;**監査ログ**にアクセスします。
3. クエリする **日付** 範囲を選択します。
4. **[サービス**] フィルターで [**条件付きアクセス**] を選択し、[**適用**] ボタンを選択します。

    既定では、監査ログにはすべてのアクティビティが表示されます。 **アクティビティ** フィルターを使用して、アクティビティを絞り込みます。 条件付きアクセスの監査ログ アクティビティの完全な一覧については、 [監査ログ アクティビティ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/reference-audit-activities#conditional-access)を参照してください。
5. 行を選択して詳細を表示します。 [ **変更されたプロパティ** ] タブには、選択した監査アクティビティの変更された JSON 値が一覧表示されます。

[Image: 条件付きアクセス ポリシーの古い JSON 値と新しい JSON 値を示す監査ログ エントリのスクリーンショット。]

### Log Analytics の使用

Log Analytics を使用すると、組み込みのクエリまたはカスタムで作成された Kusto クエリを使用してデータを照会できます。 詳細については、「 [Azure Monitor でのログ クエリの概要](https://learn.microsoft.com/ja-jp/azure/azure-monitor/logs/get-started-queries)」を参照してください。

[Image: 条件付きアクセス ポリシーの更新に関する Log Analytics クエリのスクリーンショット。新しい値と古い値の場所が表示されています。]

\*\* 有効にした後、**Entra ID** の **Monitoring & health** で **Log Analytics** を見つけます。 条件付きアクセス管理者に最も関連するテーブルは **AuditLogs です**。

```kusto
AuditLogs 
| where OperationName == "Update Conditional Access policy"
```

**TargetResources**&gt;**modifiedProperties** で変更を検索します。

### 値の読み取り

監査ログと Log Analytics の古いおよび新しい値は JSON 形式です。 2 つの値を比較して、ポリシーへの変更を識別します。

古いポリシーの例:

```json
{
    "conditions": {
        "applications": {
            "applicationFilter": null,
            "excludeApplications": [
            ],
            "includeApplications": [
                "797f4846-ba00-4fd7-ba43-dac1f8f63013"
            ],
            "includeAuthenticationContextClassReferences": [
            ],
            "includeUserActions": [
            ]
        },
        "clientAppTypes": [
            "browser",
            "mobileAppsAndDesktopClients"
        ],
        "servicePrincipalRiskLevels": [
        ],
        "signInRiskLevels": [
        ],
        "userRiskLevels": [
        ],
        "users": {
            "excludeGroups": [
                "eedad040-3722-4bcb-bde5-bc7c857f4983"
            ],
            "excludeRoles": [
            ],
            "excludeUsers": [
            ],
            "includeGroups": [
            ],
            "includeRoles": [
            ],
            "includeUsers": [
                "All"
            ]
        }
    },
    "displayName": "Common Policy - Require MFA for Azure management",
    "grantControls": {
        "builtInControls": [
            "mfa"
        ],
        "customAuthenticationFactors": [
        ],
        "operator": "OR",
        "termsOfUse": [
            "a0d3eb5b-6cbe-472b-a960-0baacbd02b51"
        ]
    },
    "id": "334e26e9-9622-4e0a-a424-102ed4b185b3",
    "modifiedDateTime": "2021-08-09T17:52:40.781994+00:00",
    "state": "enabled"
}

```

更新されたポリシーの例:

```json
{
    "conditions": {
        "applications": {
            "applicationFilter": null,
            "excludeApplications": [
            ],
            "includeApplications": [
                "797f4846-ba00-4fd7-ba43-dac1f8f63013"
            ],
            "includeAuthenticationContextClassReferences": [
            ],
            "includeUserActions": [
            ]
        },
        "clientAppTypes": [
            "browser",
            "mobileAppsAndDesktopClients"
        ],
        "servicePrincipalRiskLevels": [
        ],
        "signInRiskLevels": [
        ],
        "userRiskLevels": [
        ],
        "users": {
            "excludeGroups": [
                "eedad040-3722-4bcb-bde5-bc7c857f4983"
            ],
            "excludeRoles": [
            ],
            "excludeUsers": [
            ],
            "includeGroups": [
            ],
            "includeRoles": [
            ],
            "includeUsers": [
                "All"
            ]
        }
    },
    "displayName": "Common Policy - Require MFA for Azure management",
    "grantControls": {
        "builtInControls": [
            "mfa"
        ],
        "customAuthenticationFactors": [
        ],
        "operator": "OR",
        "termsOfUse": [
        ]
    },
    "id": "334e26e9-9622-4e0a-a424-102ed4b185b3",
    "modifiedDateTime": "2021-08-09T17:52:54.9739405+00:00",
    "state": "enabled"
}

```

前の例では、更新されたポリシーには、許可コントロールの使用条件は含まれません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/troubleshoot-security-copilot-policies"} -->
## Microsoft Security Copilotの条件付きアクセス ポリシーのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-security-copilot-policies
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: Security Copilot条件付きアクセス - 保護を強化するために、カスタム セキュリティ属性を使用してポリシーを作成、割り当て、トラブルシューティングする方法について説明します。

### 概要

[生成的人工知能（AI）](https://learn.microsoft.com/ja-jp/ai/playbook/technology-guidance/generative-ai/)サービスのような[Microsoft Security Copilot](https://learn.microsoft.com/ja-jp/copilot/security/microsoft-security-copilot)は、適切に使用すると、組織に価値をもたらすことができます。

[すべてのリソースを対象とする推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#conditional-access-for-all-resources)に従って、これらの生成 AI サービスに条件付きアクセス ポリシーを適用します。 これらのポリシーには、 [すべてのユーザー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-mfa-strength)、危険な [ユーザー](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-user)、 [サインイン](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-sign-in)、 [デバイス コンプライアンス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-all-users-device-compliance)、 [インサイダー リスク](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/policy-risk-based-insider-block)のあるユーザーに対するポリシーが含まれる場合があります。

一部の組織では、条件付きアクセス ポリシーで基になるサービス プリンシパルとカスタム セキュリティ属性を使用して、これらのサービスを直接対象とします。

- 43d7b169-1d9e-4d32-8cd8-06c5974ed90c - セキュリティコパイロットエージェント管理
- bb5ffd56-39eb-458c-a53a-775ba21277da - Security Copilot Portal
- bb3d68c2-d09e-4455-94a0-e323996dbaa3 - Security Copilot API
- b0cf1501-8e0f-4fbb-b70a-52ca5ea7bda6 - セキュリティ コパイロット ロジックアプリ コネクタ

このような場合、管理者は、カスタム セキュリティ属性を使用して、これらの基になるサービス プリンシパルを作成、割り当て、対象とします。

### 必要な役割

カスタム セキュリティ属性はセキュリティの機密性が高く、委任されたユーザーのみが管理できます。 これらの属性を管理またはレポートするユーザーに、次のロールのうち 1 つ以上を割り当てます。

| 役割名 | 説明 |
| --- | --- |
| [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) | サポートされているMicrosoft Entra オブジェクトにカスタム セキュリティ属性キーと値を割り当てます。 |
| [アトリビュート割り当てビューア](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader) | サポートされているMicrosoft Entra オブジェクトのカスタム セキュリティ属性キーと値を読み取ります。 |
| [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator) | カスタム セキュリティ属性を定義して管理します。 |
| [属性定義閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader) | カスタム セキュリティ属性の定義を読み取ります。 |

ディレクトリ スコープでこれらの属性を管理またはレポートするユーザーに適切なロールを割り当てます。 詳細な手順については、「[assign Microsoft Entra roles](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/manage-roles-portal#assign-roles-with-tenant-scope)を参照してください。

Von Bedeutung

既定では、[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、割り当てを行う権限がありません。

### カスタム セキュリティ属性を作成する

記事 [「Microsoft Entra ID のカスタム セキュリティ属性を追加または非アクティブ化する」](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add) の指示に従って、次の**属性セット**と**新しい属性**を追加します。

- **SecurityCopilotAttributeSet** という名前の*属性セット*を作成します。
- **SecurityCopilotAttribute** という名前の*新しい属性*を作成し、[**複数の値の割り当てを許可する**] を **[いいえ**] に設定し、[**定義済みの値のみ割り当て**可能] を **[はい**] に設定します。 次の定義済みの値を追加します。
    - 多要素認証必須

注

アプリケーションの条件付きアクセス フィルターは、"string" 型のカスタム セキュリティ属性でのみ機能します。 カスタム セキュリティ属性ではブール型の作成がサポートされていますが、条件付きアクセス ポリシーでは "string" のみがサポートされます。

### カスタム セキュリティ属性をアプリケーションに割り当てる

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Conditional Access Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) および [Attribute Assignment Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**に移動します。
3. カスタム セキュリティ属性を適用するアプリを選択します。
    1. 43d7b169-1d9e-4d32-8cd8-06c5974ed90c - セキュリティコパイロットエージェント管理
    2. bb5ffd56-39eb-458c-a53a-775ba21277da - セキュリティコパイロットポータル
    3. bb3d68c2-d09e-4455-94a0-e323996dbaa3 - Security Copilot API
    4. b0cf1501-8e0f-4fbb-b70a-52ca5ea7bda6 - セキュリティ コパイロット ロジック アプリ コネクタ
4. **[管理]**&gt;**[カスタム セキュリティ属性]** で、**[割り当ての追加]** を選択します。
5. [ **属性セット]** で、作成した属性セットを選択します。
6. [ **属性名**] で、作成した属性を選択します。
7. [ **割り当てられた値**] で [ **値の追加**] を選択し、一覧から作成した値を選択し、[ **完了]** を選択します。
8. **保存** を選択します。

### 条件付きアクセス ポリシーでのカスタム セキュリティ属性のターゲット設定

1. 少なくとも [Microsoft Entra 管理センター](https://entra.microsoft.com)[Conditional Access Administrator](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator) および [Attribute Definition Reader](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-reader) としてサインインします。
2. **Entra ID**&gt;**Conditional Access** に移動します。
3. **[新しいポリシー**] を選択するか、更新する既存のポリシーを選択します。
4. **ターゲット リソース**を構成するときに、次のオプションを選択します。
    1. このポリシーが **リソース (以前のクラウド アプリ) に適用される内容を**選択します。
    2. **[リソースの選択]** を含めます。
    3. **[フィルターの編集]** を選択します。
    4. **[構成]** を **[はい]** に設定します。
    5. 作成した **属性** を選択します。
    6. **演算子**を **Contains** に設定します。
    7. **Value** をカスタム属性のいずれかに設定します。
    8. **完了**を選択します。

### 問題の原因となっているポリシーはどれですか?

管理者が問題が発生したときに更新するポリシーを確認するのが難しい場合があります。 [条件付きアクセスに関するサインインの問題のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/troubleshoot-conditional-access#microsoft-entra-sign-in-events)のガイダンスを使用して、適用されるポリシー、適用されないポリシーを確認し、継続的な問題を回避するためにサインイン診断を実行します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/what-if-tool"} -->
## 条件付きアクセスの What If ツール - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/what-if-tool
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: 条件付きアクセス ポリシーの結果を What If ツールを使用してシミュレートし、環境のトラブルシューティングと最適化を行います。

### 概要

**条件付きアクセス What If ポリシー ツール**は、環境内の[条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) ポリシーの結果を理解するのに役立ちます。 これは、一般的でないシナリオをシミュレートする場合に役立ち、より包括的なセキュリティ ポリシーを設計できます。 このツールは、複数のサインインを使用してポリシーを手動でテストする代わりに、ユーザー、エージェント ID、またはシングル テナント サービス プリンシパルのサインインをシミュレートするのに役立ちます。 シミュレーションでは、ポリシーがこのサインインに与える影響を推定し、レポートを生成します。

**What If** ツールと [API を](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-evaluate)使用すると、特定のユーザー、エージェント ID、またはシングル テナント サービス プリンシパルに適用されるポリシーをすばやく特定できます。 この情報を使用して、問題のトラブルシューティングを行い、特定のサインイン条件に適用されるポリシーを理解し、複雑なサインイン シナリオをテストします。

### しくみ

条件付きアクセスの What If ツールは、[What If 評価 API](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-evaluate)を利用して動作します。 このツールを使用するには、まず、シミュレートするサインイン シナリオの条件を構成します。 構成には、次のものが含まれている必要があります。

- テストするユーザー、エージェント ID (プレビュー)、またはシングル テナント サービス プリンシパル。
- クラウド アプリ、実行しようとするユーザー アクション、またはアクセスを試みる認証コンテキストによって保護された機密データ。
- アクセスが試行されるサインイン条件。

重要

What If ツールでは、[条件付きアクセスのサービスの依存関係](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/service-dependencies)はテストされません。 たとえば、 **What If** を使用してMicrosoft Teamsの条件付きアクセス ポリシーをテストする場合、結果では、Microsoft Teamsの条件付きアクセス サービスの依存関係である Office 365 Exchange Online に適用されるポリシーは考慮されません。

次に、設定を評価するシミュレーション実行を開始します。 評価実行には、有効になっているポリシーまたはレポート専用モードのポリシーのみが含まれます。

評価が完了すると、ツールは、影響を受けたポリシーのレポートを生成します。 条件付きアクセス ポリシーの詳細を収集するには、 [ポリシーごとの条件付きアクセスレポート、](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-report-only#policy-impact-preview) または [条件付きアクセスの分析情報とレポート ブックを](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/howto-conditional-access-insights-reporting) 使用して、レポート専用モードまたは現在有効になっているポリシーの詳細を確認します。

### What If ツールを実行する

**What If** ツールは**、Microsoft Entra 管理センター**&gt;**Entra ID**&gt;**Conditional Access**&gt;**Policies**&gt;**Whatt If** にあります。

[Image: ツール バーの [What If] ツールが強調表示されている [条件付きアクセス ポリシー] ページのスクリーンショット。]

What If 評価を実行するには、評価する条件を指定します。

### 条件

ID、ターゲット リソース、デバイス プラットフォーム、クライアント アプリの条件が必要です。 その他の条件はすべて省略可能であり、値が指定されていない場合、既定では **none** に設定されていると見なされます。 これらの条件の定義については、 [条件付きアクセス ポリシーの構築](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies)に関する記事を参照してください。

[Image: 条件を入力するためのフィールドが表示されている [What If] ページのスクリーンショット。]

### 評価

**[What If**]\(What If\)を選択して評価を開始します。 以下で構成されるレポートによって評価結果が示されます。

- 環境内にクラシック ポリシーが存在するかどうかを示すインジケーター。
- ユーザー、エージェント、またはワークロード ID に適用されるポリシー。
- ユーザーまたはワークロード ID には適用されないポリシー。

[Image: What If ツールのポリシー評価の例を示すスクリーンショット。適用されるポリシーが表示されています。]

適用されるポリシーの一覧には、満たす必要がある [許可コントロール](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-grant) と [セッション制御](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-session) も含まれます。

適用されないポリシーの一覧には、これらのポリシーが適用されない理由が含まれます。 表示されている各ポリシーの理由は、満たされていない最初の条件を表します。

**[フィルターあり] は** 、ポリシーにカスタム セキュリティ属性を使用するアプリ フィルターがあるかどうかを示します。

### What If 評価 API とレガシ エクスペリエンスの主な違い

[What If Evaluation API](https://learn.microsoft.com/ja-jp/graph/api/conditionalaccessroot-evaluate) は、条件付きアクセス エクスペリエンスによって呼び出される Microsoft Graph APIです。 API は、いくつかの点で従来の What If 評価とは異なります。

1. What If API は、パブリックで完全にサポートされている API です。 条件付きアクセス エクスペリエンスと Microsoft Graph APIで使用できます。
2. このロジックは、より正確なポリシー評価を提供するために、サインイン時に使用される認証ロジックと一致します。
3. What If API では、最も正確な結果を得るために、すべてのサインイン パラメーターが評価用に定義されることを想定しています。 テナントに特定の条件を持つポリシーがあり、それらの条件のサインインの詳細が指定されていない場合、What If API はこれらの条件を評価できません。

注

アプリケーションの仕様については、アプリ ID を指定します。 **Office 365** や **Microsoft 管理ポータル**などのアプリのグループは、一致しません。

#### 例示

この例では、主な違いを強調しています。

次の構成の条件付きアクセス ポリシーがあるとします。

- ユーザー: すべてのユーザー
- リソース: Office 365
- 場所: 米国
- サインイン リスク: 高

| 例 | パラメーター | レガシ What If 評価に基づく結果 | 新しい What If 評価 API に基づく結果 |
| --- | --- | --- | --- |
| 1 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee" | 適用 | 該当しません |
| 2 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"  ApplicationId = "00000003-0000-0ff1-ce00-000000000000" | 適用 | 該当しません |
| 3 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"  ApplicationId = "00000003-0000-0ff1-ce00-000000000000"  Location = "US" | 適用 | 該当しません |
| 4 | UserId = "00aa00aa-bb11-cc22-dd33-44ee44ee44ee"  ApplicationId = "00000003-0000-0ff1-ce00-000000000000"  Location = "US"  サインイン リスク = "高" | 適用 | 適用 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/conditional-access/workload-identity"} -->
## ワークロード ID 用の Microsoft Entra 条件付きアクセス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/workload-identity
- Service: entra-id / conditional-access
- Article date: 2026-03-24
- Summary: 条件付きアクセス ポリシーを使用したワークロード ID の保護

### 概要

従来、条件付きアクセス ポリシーは、ユーザーが SharePoint Online などのアプリやサービスにアクセスするときにのみ適用されていました。 現在、組織が所有するサービス プリンシパルに適用される条件付きアクセス ポリシーのサポートを拡張しています。 この機能を、ワークロード ID 用の条件付きアクセスと呼びます。

[ワークロード ID](https://learn.microsoft.com/ja-jp/entra/workload-id/workload-identities-overview) は、アプリケーションまたはサービス プリンシパルがリソースにアクセスできるようにする ID です。場合によっては、ユーザーのコンテキストでアクセスできます。 これらのワークロード ID は、次の理由から、従来のユーザー アカウントとは異なります。

- 多要素認証を実行できません。
- 正式なライフサイクル プロセスがないことが多い。
- 資格情報またはシークレットをどこかに保存する必要があります。

これらの違いによって、ワークロード ID の管理が難しくなり、侵害リスクが高くなります。

重要

サービス プリンシパルを対象とする条件付きアクセス ポリシーを作成または変更するには、Workload Identities Premium ライセンスが必要です。 適切なライセンスがないディレクトリでは、ワークロード ID の既存の条件付きアクセス ポリシーは引き続き機能しますが、変更することはできません。 詳細については、「 [Microsoft Entra ワークロード ID」を](https://www.microsoft.com/security/business/identity-access/microsoft-entra-workload-identities#office-StandaloneSKU-k3hubfz)参照してください。

注

ポリシーは、お使いのテナントに登録されているシングル テナント サービス プリンシパルに適用できます。 マルチテナント アプリを含むMicrosoftおよびサード パーティの SaaS アプリケーションは、これらのポリシーの対象ではありません。 マネージド ID は、ポリシーの対象にはなりません。 代わりに、マネージド ID を [アクセス レビュー](https://learn.microsoft.com/ja-jp/entra/id-governance/access-reviews-overview) に含めることができました。

注

サービス プリンシパルはグループに追加できますが、サービス プリンシパルを含むグループに割り当てられた条件付きアクセス ポリシーは、そのサービス プリンシパルに適用されません。 サービス プリンシパルに条件付きアクセス ポリシーを適用するには、ワークロード ID としてポリシーに直接割り当てる必要があります。

ワークロード ID の条件付きアクセスにより、ブロックしているサービス プリンシパルが次の条件で有効になります。

- 既知のパブリック IP 範囲の外部から。
- Microsoft Entra ID 保護によって検出されたリスクに基づいて。
- [認証コンテキスト](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps#authentication-context)と組み合わせて。

### 実装

#### 場所ベースの条件付きアクセス ポリシーを作成する

サービス プリンシパルに適用される、場所ベースの条件付きアクセス ポリシーを作成します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. [ **新しいポリシー]** を選択します。
4. ポリシーに名前を付けます。 ポリシーの名前にわかりやすい標準を作成します。
5. [ **割り当て]** で、[ **ユーザーまたはワークロード ID] を選択します**。
    1. [ **このポリシーの適用対象] で**、[ **ワークロード ID] を選択します**。
    2. [ **含める**] で[ **サービス プリンシパルの選択**] を選択し、一覧から適切なサービス プリンシパルを選択します。
6. **ターゲット リソース**&gt;**リソース (旧称クラウド アプリ)**&gt;**の中で**、「**すべてのリソース (旧称 'すべてのクラウド アプリ')**」を選択します。 このポリシーは、サービス プリンシパルがトークンを要求した場合にのみ適用されます。
7. [ **条件**&gt;**場所** ] で、**任意の場所** を含め、**選択した場所** を除外してアクセスを許可します。
8. [ **許可]** で、[ **アクセスのブロック** ] が使用可能な唯一のオプションです。 トークン要求が許容範囲外から行われた場合、アクセスはブロックされます。
9. ポリシーは **レポート専用** モードで保存でき、管理者は効果を見積もることができます。または、ポリシーを **オン**にすることでポリシーが適用されます。
10. [ **作成]** を選択してポリシーを完了します。

#### リスクベースの条件付きアクセス ポリシーを作成する

サービス プリンシパルに適用される、場所ベースの条件付きアクセス ポリシーを作成します。

[Image: ワークロード ID とリスクを条件として持つ条件付きアクセス ポリシーを作成する。]

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **Entra ID**&gt;**Conditional Access**&gt;**Policies** に移動します。
3. [ **新しいポリシー]** を選択します。
4. ポリシーに名前を付けます。 ポリシーの名前にわかりやすい標準を作成します。
5. [ **割り当て]** で、[ **ユーザーまたはワークロード ID] を選択します**。
    1. [ **このポリシーの適用対象] で**、[ **ワークロード ID] を選択します**。
    2. [ **含める**] で[ **サービス プリンシパルの選択**] を選択し、一覧から適切なサービス プリンシパルを選択します。
6. **ターゲット リソース**&gt;**リソース (旧称クラウド アプリ)**&gt;**の中で**、「**すべてのリソース (旧称 'すべてのクラウド アプリ')**」を選択します。 このポリシーは、サービス プリンシパルがトークンを要求した場合にのみ適用されます。
7. **[条件]**&gt;**[サービス プリンシパル リスク]**の下で次のようにします
    1. [ **構成** ] トグルを **[はい**] に設定します。
    2. このポリシーをトリガーするリスクのレベルを選択します。
    3. [ **完了] を選択します**。
8. [ **許可]** で、[ **アクセスのブロック** ] が使用可能な唯一のオプションです。 指定したリスク レベルが表示されると、アクセスはブロックされます。
9. ポリシーは **レポート専用** モードで保存でき、管理者は効果を見積もることができます。または、ポリシーを **オン**にすることでポリシーが適用されます。
10. [ **作成]** を選択してポリシーを完了します。

### ロールバック

この機能をロールバックする場合は、作成したポリシーを削除または無効にすることができます。

### サインイン ログ

サインイン ログを使用して、サービス プリンシパルに対してポリシーがどのように適用されているか、またはレポート専用モードを使用している場合には予想されるポリシーの影響を確認できます。

1. **Entra ID**&gt;**モニタリング&健康**&gt;**サインインログ**&gt;**サービス プリンシパルのサインイン**に移動します。
2. ログ エントリを選択し、[ **条件付きアクセス** ] タブを選択して評価情報を表示します。

条件付きアクセスによってサービス プリンシパルがブロックされた場合のエラーの理由: "条件付きアクセス ポリシーのため、アクセスがブロックされました。"

#### レポート専用モード

場所ベースのポリシーの結果を表示するには、**サインイン レポート**のイベントの **[レポートのみ**] タブに移動するか、**条件付きアクセスの分析情報とレポート ブックを**使用します。

リスクベースのポリシーの結果を表示するには、**サインイン レポート**のイベントの [**レポートのみ**] タブを参照してください。

### 関連項目

#### ObjectID の検索

サービス プリンシパルの objectID は Microsoft Entra エンタープライズ アプリケーションから取得できます。 Microsoft Entra アプリの登録のオブジェクト ID は使用できません。 この識別子は、サービス プリンシパルではなく、アプリの登録のオブジェクト ID です。

1. **Entra ID**&gt;**Enterprise アプリ**に移動し、登録したアプリケーションを見つけます。
2. [ **概要** ] タブで、アプリケーションの **オブジェクト ID を** コピーします。 この識別子はサービス プリンシパルに固有のもので、呼び出し元のアプリを検索するために条件付きアクセス ポリシーによって使用されます。

#### Microsoft Graph

Microsoft Graph ベータ版エンドポイントを使用した場所ベースの構成用のサンプル JSON。

```json
{
  "displayName": "Name",
  "state": "enabled OR disabled OR enabledForReportingButNotEnforced",
  "conditions": {
    "applications": {
      "includeApplications": [
        "All"
      ]
    },
    "clientApplications": {
      "includeServicePrincipals": [
        "[Service principal Object ID] OR ServicePrincipalsInMyTenant"
      ],
      "excludeServicePrincipals": [
        "[Service principal Object ID]"
      ]
    },
    "locations": {
      "includeLocations": [
        "All"
      ],
      "excludeLocations": [
        "[Named location ID] OR AllTrusted"
      ]
    }
  },
  "grantControls": {
    "operator": "and",
    "builtInControls": [
      "block"
    ]
  }
}
```
<!-- /MSL-PAGE -->
