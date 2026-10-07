# Microsoft Learn — Microsoft Entra / ハイブリッド ID (Connect / Cloud Sync) (part 4)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 37

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-adsynctools"} -->
## Microsoft Entra Connect: ADSyncTools PowerShell リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adsynctools
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、ADSyncTools.psm1 PowerShell モジュールのリファレンス情報を提供します。

次のドキュメントでは、Microsoft Entra Connect に含まれる `ADSyncTools.psm1` PowerShell モジュールのリファレンス情報を提供します。

### ADSyncTools PowerShell モジュールをインストールする

ADSyncTools PowerShell モジュールをインストールするには、次の手順を実行します。

1. 管理者特権で Windows PowerShell を開く
2. 次のように入力するか、コピーして貼り付けます。

    ```powershell
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    Install-Module -Name ADSyncTools
    ```
3. Enter キーを押します。
4. モジュールがインストールされたことを確認するには、次のように入力するか、コピーして貼り付けます。

    ```powershell
    Get-module ADSyncTools
    ```
5. 今、モジュールに関する情報が表示されるはずです。

### Clear-ADSyncToolsMsDsConsistencyGuid

#### 概要

Active Directory オブジェクト mS-DS-ConsistencyGuid をクリアする

#### 構文

```
Clear-ADSyncToolsMsDsConsistencyGuid [-Identity] <Object> [<CommonParameters>]
```

#### Description

ターゲット Active Directory オブジェクトの mS-DS-ConsistencyGuid の値をクリアします。 マルチドメイン フォレスト内の Active Directory オブジェクトをサポートします。

#### 例の数々

##### 例 1

```
Clear-ADSyncToolsMsDsConsistencyGuid -Identity 'CN=User1,OU=Sync,DC=Contoso,DC=com'
```

##### 例 2

```
Clear-ADSyncToolsMsDsConsistencyGuid -Identity 'User1@Contoso.com'
```

##### 例 3

```
'User1@Contoso.com' | Clear-ADSyncToolsMsDsConsistencyGuid
```

#### パラメーター

##### -同一性

mS-DS-ConsistencyGuid をクリアする AD のターゲット オブジェクト

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Connect-ADSyncToolsSqlDatabase

#### 概要

テスト目的で SQL データベースに接続する

#### 構文

```
Connect-ADSyncToolsSqlDatabase [-Server] <String> [[-Instance] <String>] [[-Database] <String>]
 [[-Port] <String>] [[-UserName] <String>] [[-Password] <String>] [<CommonParameters>]
```

#### Description

SQL 診断関連の関数とユーティリティ

#### 例の数々

##### 例 1

```
Connect-ADSyncToolsSqlDatabase -Server 'sqlserver01.contoso.com' -Database 'ADSync'
```

##### 例 2

```
Connect-ADSyncToolsSqlDatabase -Server 'sqlserver01.contoso.com' -Instance 'INTANCE01' -Database 'ADSync'
```

#### パラメーター

##### -サーバー

SQL Server 名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -例

SQL Server インスタンス名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -データベース

SQL Server データベース名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 3
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -港

SQL Server ポート (たとえば、 `49823`)

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 4
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -UserName

SQL Server ログイン ユーザー名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 5
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -パスワード

SQL Server ログイン パスワード

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 6
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### ConvertFrom-ADSyncToolsAadDistinguishedName

#### 概要

Microsoft Entra Connector DistinguishedName を ImmutableId に変換する

#### 構文

```
ConvertFrom-ADSyncToolsAadDistinguishedName [-DistinguishedName] <String> [<CommonParameters>]
```

#### Description

CN={514635484D4B376E38307176645973555049486139513D3D} のような Microsoft Entra Connector DistinguishedName を受け取り、それぞれの base64 ImmutableID 値に変換します (例: `QF5HMK7n80qvdYsUPIHa9Q==`

#### 例の数々

##### 例 1

```
ConvertFrom-ADSyncToolsAadDistinguishedName 'CN={514635484D4B376E38307176645973555049486139513D3D}'
```

#### パラメーター

##### -DistinguishedName

Microsoft Entra Connector Space DistinguishedName

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### ConvertFrom-ADSyncToolsImmutableID

#### 概要

Base64 ImmutableId (SourceAnchor) を GUID 値に変換する

#### 構文

```
ConvertFrom-ADSyncToolsImmutableID [-Value] <String> [<CommonParameters>]
```

#### Description

Base64 文字列から ImmutableID の値を変換し、GUID 値を返します。Base64 文字列を GUID に変換できない場合は、バイト配列を返します。

#### 例の数々

##### 例 1

```
ConvertFrom-ADSyncToolsImmutableID 'iGhmiAEBERG7uxI0VniQqw=='
```

##### 例 2

```
'iGhmiAEBERG7uxI0VniQqw==' | ConvertFrom-ADSyncToolsImmutableID
```

#### パラメーター

##### -価値

Base64 形式の ImmutableId

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### ConvertTo-ADSyncToolsAadDistinguishedName

#### 概要

ImmutableId を Microsoft Entra Connector DistinguishedName に変換する

#### 構文

```
ConvertTo-ADSyncToolsAadDistinguishedName [-ImmutableId] <String> [<CommonParameters>]
```

#### Description

QF5HMK7n80qvdYsUPIHa9Q== のような ImmutableId (SourceAnchor) を受け取り、それぞれの Microsoft Entra Connector DistinguishedName 値に変換します (例: `CN={514635484D4B376E38307176645973555049486139513D3D}`

#### 例の数々

##### 例 1

```
ConvertTo-ADSyncToolsAadDistinguishedName 'QF5HMK7n80qvdYsUPIHa9Q=='
```

#### パラメーター

##### -ImmutableId

ImmutableId (SourceAnchor)

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### ConvertTo-ADSyncToolsCloudAnchor

#### 概要

Base64 アンカーを CloudAnchor に変換する

#### 構文

```
ConvertTo-ADSyncToolsCloudAnchor [-Anchor] <String> [<CommonParameters>]
```

#### Description

VAAAAFUAcwBlAHIAXwBjADcAMgA5ADAAMwBlAGQALQA3ADgAMQA2AC0ANAAxAGMAZAAtADkAMAA2ADYALQBlAGEAYwAzADMAZAAxADcAMQBkADcANwAAAA== などの Base64 アンカーを取得し、対応する CloudAnchor 値 (例: CloudAnchor) に変換します。 `User_00aa00aa-bb11-cc22-dd33-44ee44ee44ee`

#### 例の数々

##### 例 1

```
ConvertTo-ADSyncToolsCloudAnchor "VAAAAFUAcwBlAHIAXwBjADcAMgA5ADAAMwBlAGQALQA3ADgAMQA2AC0ANAAxAGMAZAAtADkAMAA2ADYALQBlAGEAYwAzADMAZAAxADcAMQBkADcANwAAAA=="
```

##### 例 2

```
"VAAAAFUAcwBlAHIAXwBjADcAMgA5ADAAMwBlAGQALQA3ADgAMQA2AC0ANAAxAGMAZAAtADkAMAA2ADYALQBlAGEAYwAzADMAZAAxADcAMQBkADcANwAAAA==" | ConvertTo-ADSyncToolsCloudAnchor
```

#### パラメーター

##### -錨

Base64 アンカー

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### ConvertTo-ADSyncToolsImmutableID

#### 概要

GUID (ObjectGUID/ms-Ds-Consistency-Guid) を Base64 文字列に変換する

#### 構文

```
ConvertTo-ADSyncToolsImmutableID [-Value] <Object> [<CommonParameters>]
```

#### Description

GUID、GUID 文字列、またはバイト配列形式の値を Base64 文字列に変換します

#### 例の数々

##### 例 1

```
ConvertTo-ADSyncToolsImmutableID '00aa00aa-bb11-cc22-dd33-44ee44ee44ee'
```

##### 例 2

```
'00aa00aa-bb11-cc22-dd33-44ee44ee44ee' | ConvertTo-ADSyncToolsImmutableID
```

#### パラメーター

##### -価値

GUID、GUID 文字列、またはバイト配列

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Export-ADSyncToolsAadDisconnectors

#### 概要

Microsoft Entra Disconnector オブジェクトをエクスポートする

#### 構文

```
Export-ADSyncToolsAadDisconnectors [[-SyncObjectType] <Object>] [<CommonParameters>]
```

#### Description

CSExport ツールを実行してすべての Disconnector を XML にエクスポートし、この XML 出力を受け取り、UserPrincipalName、Mail、SourceAnchor、DistinguishedName、CsObjectId、ObjectType、ConnectorId、CloudAnchor を使用して CSV ファイルに変換します。

#### 例の数々

##### 例 1

```
Export-ADSyncToolsAadDisconnectors -SyncObjectType 'PublicFolder'
```

すべての PublicFolder Disconnector オブジェクトを CSV にエクスポートする

##### 例 2

```
Export-ADSyncToolsAadDisconnectors
```

CSV へのすべての Disconnector オブジェクトへのエクスポート

#### パラメーター

##### -SyncObjectType

出力に含める ObjectType

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: False
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

指定したオブジェクト型に対してのみ Disconnector をエクスポートする場合は、ObjectType 引数を使用します。

#### 出力

UserPrincipalName、Mail、SourceAnchor、DistinguishedName、CsObjectId、ObjectType、ConnectorId、CloudAnchor を含む Disconnector オブジェクトを含む CSV ファイルをエクスポートします。

### Export-ADSyncToolsAadPublicFolders

#### 概要

同期されたすべての Mail-Enabled パブリック フォルダー オブジェクトを Microsoft Entra ID から CSV ファイルにエクスポートします

#### 構文

```
Export-ADSyncToolsAadPublicFolders [-Credential] <PSCredential> [-Path] <Object> [<CommonParameters>]
```

#### Description

この関数は、Microsoft Entra ID に存在するすべての同期 Mail-Enabled パブリック フォルダー (MEPF) を CSV ファイルにエクスポートします。 Remove-ADSyncToolsAadPublicFolders と共に使用して、Microsoft Entra ID で孤立した Mail-Enabled パブリック フォルダーを識別および削除できます。 この関数には、Microsoft Entra ID のグローバル管理者の資格情報が必要であり、MFA による認証はサポートされていません。 注: テナントで DirSync が無効になっている場合は、孤立したメールが有効なパブリック フォルダーを Microsoft Entra ID から削除するために、DirSync を一時的に再度有効にする必要があります。

#### 例の数々

##### 例 1

```
Export-ADSyncToolsAadPublicFolders -Credential $(Get-Credential) -Path <file_name>
```

#### パラメーター

##### -資格 情報

Microsoft Entra グローバル管理者の資格情報

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: true
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -パス

出力ファイルのパス

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: true
Position: 2
Default value: None
Accept pipeline input: false (ByPropertyName)
Accept wildcard characters: false
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

#### 出力

このコマンドレットは、同期されたすべての Mail-Enabled PublicFolder オブジェクトを CSV 形式で含む `<filename>` を作成します。

### Export-ADSyncToolsHybridAadJoinReport

#### 概要

Active Directory コンピューター オブジェクト (具体的には、Microsoft Entra ハイブリッド参加機能によって発行された証明書) に格納されている証明書のレポートを生成します。

#### 構文

##### SingleObject

```
Export-ADSyncToolsHybridAadJoinReport [-DN] <String> [[-Filename] <String>] [<CommonParameters>]
```

##### MultipleObjects

```
Export-ADSyncToolsHybridAadJoinReport [-OU] <String> [[-Filename] <String>] [<CommonParameters>]
```

#### Description

このツールは、AD の Computer オブジェクトの UserCertificate プロパティに存在するすべての証明書をチェックし、有効期限が切れていない証明書ごとに、証明書が Microsoft Entra ハイブリッド結合機能 (つまり、サブジェクト名は CN={ObjectGUID}) に対して発行されたかどうかを検証します。 バージョン 1.4 より前の Microsoft Entra Connect は、少なくとも 1 つの証明書を含むコンピューターを Microsoft Entra に同期していましたが、Microsoft Entra Connect バージョン 1.4 以降では、ADSync エンジンは Microsoft Entra ハイブリッド参加証明書を識別でき、有効な Microsoft Entra ハイブリッド参加証明書がない限り、コンピューター オブジェクトが Microsoft Entra ID に同期されないように "cloudfilter" (除外) します。 既に AD に同期されているが、有効な Microsoft Entra ハイブリッド参加証明書がない Microsoft Entra Device オブジェクトは、Microsoft Entra Connect によって Microsoft Entra ID (CloudFiltered=TRUE) から削除されます。

#### 例の数々

##### 例 1

```
Export-ADSyncToolsHybridAzureADjoinCertificateReport -DN 'CN=Computer1,OU=SYNC,DC=Fabrikam,DC=com'
```

##### 例 2

```
Export-ADSyncToolsHybridAzureADjoinCertificateReport -OU 'OU=SYNC,DC=Fabrikam,DC=com' -Filename "MyHybridAzureADjoinReport.csv" -Verbose
```

#### パラメーター

##### -DN

Computer DistinguishedName

```yaml
Type: String
Parameter Sets: SingleObject
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -OU

AD OrganizationalUnit

```yaml
Type: String
Parameter Sets: MultipleObjects
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -Filename

CSV ファイル名の出力 (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 関連リンク

詳細情報: [Microsoft Entra Connect 1.4.xx.x とデバイスの消失](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/reference-connect-device-disappearance)について

### Export-ADSyncToolsObjects

#### 概要

Microsoft Entra Connect オブジェクトを XML ファイルにエクスポートする

#### 構文

##### オブジェクト識別子

```
Export-ADSyncToolsObjects [-ObjectId] <Object> [-Source] <Object> [-ExportSerialized] [<CommonParameters>]
```

##### DistinguishedName

```
Export-ADSyncToolsObjects [-DistinguishedName] <Object> [-ConnectorName] <Object> [-ExportSerialized]
 [<CommonParameters>]
```

#### Description

メタバースから内部 ADSync オブジェクトをエクスポートし、コネクタ スペースから関連する接続オブジェクトをエクスポートします。

#### 例の数々

##### 例 1

```
Export-ADSyncToolsObjects -ObjectId 'aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb' -Source Metaverse
```

##### 例 2

```
Export-ADSyncToolsObjects -ObjectId 'bbbbbbbb-1111-2222-3333-cccccccccccc' -Source ConnectorSpace
```

##### 例 3

```
Export-ADSyncToolsObjects -DistinguishedName 'CN=User1,OU=ADSync,DC=Contoso,DC=com' -ConnectorName 'Contoso.com'
```

#### パラメーター

##### -ObjectId

ObjectId は、それぞれのコネクタ スペースまたはメタバース内のオブジェクトの一意識別子です。

```yaml
Type: Object
Parameter Sets: ObjectId
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -源

ソースは、ConnectorSpace または Metaverse を使用できるオブジェクトが存在するテーブルです。

```yaml
Type: Object
Parameter Sets: ObjectId
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -DistinguishedName

DistinguishedName は、それぞれのコネクタ スペース内のオブジェクトの識別子です

```yaml
Type: Object
Parameter Sets: DistinguishedName
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -ConnectorName

ConnectorName は、オブジェクトが存在するコネクタ スペースの名前です。

```yaml
Type: Object
Parameter Sets: DistinguishedName
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -ExportSerialized

ExportSerialized は、シリアル化されたオブジェクト データを含む追加の XML ファイルをエクスポートします

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:
Required: False
Position: 3
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Export-ADSyncToolsRunHistory

#### 概要

Microsoft Entra Connect の実行履歴をエクスポートする

#### 構文

```
Export-ADSyncToolsRunHistory [-TargetName] <String> [<CommonParameters>]
```

#### Description

Microsoft Entra Connect 実行プロファイルと実行ステップの結果をそれぞれ CSV および XML 形式にエクスポートする関数。 結果の実行プロファイル CSV ファイルはスプレッドシートにインポートでき、実行ステップ XML ファイルは Import-Clixml

#### 例の数々

##### 例 1

```
Export-ADSyncToolsRunHistory -TargetName MyADSyncHistory
```

#### パラメーター

##### -TargetName

出力ファイルの名前

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Export-ADSyncToolsSourceAnchorReport

#### 概要

ms-dsのエクスポート -Consistency-Guid レポート

#### 構文

```
Export-ADSyncToolsSourceAnchorReport [-AlternativeLoginId] [-UserPrincipalName] <String>
 [-ImmutableIdGUID] <String> [-Output] <String> [<CommonParameters>]
```

#### Description

Import-ADSyncToolsSourceAnchor からの CSV ファイルのインポートに基づいて、ms-ds-Consistency-Guid レポートを生成します

#### 例の数々

##### 例 1

```
Import-Csv .\AllSyncUsers.csv | Export-ADSyncToolsSourceAnchorReport -Output ".\AllSyncUsers-Report"
```

##### 例 2

```
Another example of how to use this cmdlet
```

#### パラメーター

##### -AlternativeLoginId

代替ログイン ID (メール) を使用する

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:
Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ユーザープリンシパルネーム

ユーザープリンシパルネーム

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### -ImmutableIdGUID

ImmutableIdGUID

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### -アウトプット

CSV ファイルと LOG ファイルの出力ファイル名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 3
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Get-ADSyncToolsAadObject

#### 概要

特定の SyncObjectType の同期されたオブジェクトを取得する

#### 構文

```
Get-ADSyncToolsAadObject [-SyncObjectType] <Object> [-Credential] <PSCredential> [<CommonParameters>]
```

#### Description

特定のオブジェクト クラス (SyncObjectType) に対して同期されたすべてのオブジェクトを Microsoft Entra から読み取ります。

#### 例の数々

##### 例 1

```
Get-ADSyncToolsAadObject -SyncObjectType 'publicFolder' -Credential $(Get-Credential)
```

#### パラメーター

##### -SyncObjectType

Object Type/オブジェクトの種類

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -資格 情報

Microsoft Entra グローバル管理者の資格情報

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 出力

このコマンドレットは、同期クライアントによって同期される "Shadow" プロパティを返します。これは、Microsoft Entra ID のそれぞれのプロパティに格納されている実際の値とは異なる場合があります。 たとえば、検証されていないドメイン サフィックス 'user@nonverified.domain' と同期されるユーザーの UPN には、Microsoft Entra ID の UPN サフィックスがテナントの既定のドメイン 'user@tenantname.onmicrosoft.com' に変換されます。 この場合、Get-ADSyncToolsAadObject は、Microsoft Entra ID 'user@nonverified.domain' の実際の値ではなく、"user@tenantname.onmicrosoft.com" の "Shadow" 値を返します。

### Get-ADSyncToolsMsDsConsistencyGuid

#### 概要

-ConsistencyGuid ms-dsActive Directory オブジェクトを取得する

#### 構文

```
Get-ADSyncToolsMsDsConsistencyGuid [-Identity] <Object> [<CommonParameters>]
```

#### Description

ターゲット Active Directory オブジェクトの mS-DS-ConsistencyGuid 属性の値を GUID 形式で返します。 マルチドメイン フォレスト内の Active Directory オブジェクトをサポートします。

#### 例の数々

##### 例 1

```
Get-ADSyncToolsMsDsConsistencyGuid -Identity 'CN=User1,OU=Sync,DC=Contoso,DC=com'
```

##### 例 2

```
Get-ADSyncToolsMsDsConsistencyGuid -Identity 'User1@Contoso.com'
```

##### 例 3

```
'User1@Contoso.com' | Get-ADSyncToolsMsDsConsistencyGuid
```

#### パラメーター

##### -同一性

取得する AD のターゲット オブジェクト

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Get-ADSyncToolsRunHistory

#### 概要

Microsoft Entra Connect の実行履歴を取得する

#### 構文

```
Get-ADSyncToolsRunHistory [[-Days] <Int32>] [<CommonParameters>]
```

#### Description

Microsoft Entra Connect の実行履歴を XML 形式で返す関数

#### 例の数々

##### 例 1

```
Get-ADSyncToolsRunHistory
```

##### 例 2

```
Get-ADSyncToolsRunHistory -Days 3
```

#### パラメーター

##### -日

履歴を収集する日数 (既定値 = 1)

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:
Required: False
Position: 1
Default value: 1
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Get-ADSyncToolsRunHistoryLegacyWmi

#### 概要

古いバージョンの Microsoft Entra Connect (WMI) の Microsoft Entra Connect 実行履歴を取得する

#### 構文

```
Get-ADSyncToolsRunHistoryLegacyWmi [[-Days] <Int32>] [<CommonParameters>]
```

#### Description

Microsoft Entra Connect の実行履歴を XML 形式で返す関数

#### 例の数々

##### 例 1

```
Get-ADSyncToolsRunHistory
```

##### 例 2

```
Get-ADSyncToolsRunHistory -Days 3
```

#### パラメーター

##### -日

履歴を収集する日数 (既定値 = 1)

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:
Required: False
Position: 1
Default value: 1
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Get-ADSyncToolsSqlBrowserInstances

#### 概要

SQL Browser サービスから SQL Server インスタンスを取得する

#### 構文

```
Get-ADSyncToolsSqlBrowserInstances [[-Server] <String>]
```

#### Description

SQL 診断関連の関数とユーティリティ

#### 例の数々

##### 例 1

```
Get-ADSyncToolsSqlBrowserInstances -Server 'sqlserver01'
```

#### パラメーター

##### -サーバー

SQL Server 名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### Get-ADSyncToolsTenantAzureEnvironment

#### 概要

ユーザーが属している Azure 環境を取得するヘルパー関数。

#### 構文

```
Get-ADSyncToolsTenantAzureEnvironment [-Credential] <PSCredential> [<CommonParameters>]
```

#### Description

この関数は、Oauth 検出エンドポイントを呼び出して CloudInstance を取得し、tenant\_region\_scopeして Azure 環境を決定します。 `https://login.microsoftonline.com/{tenant}/.well-known/openid-configuration`

#### 例の数々

##### 例 1

```
Get-ADSyncToolsTenantAzureEnvironment -Credential (Get-Credential)
```

#### パラメーター

##### -資格 情報

ユーザーの PowerShell 資格情報オブジェクト:

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

ユーザーの PowerShell 資格情報オブジェクト

#### 出力

Azure 環境 (文字列)

### Get-ADSyncToolsTls12

#### 概要

.NET Framework の Client\Server TLS 1.2 設定を取得します

#### 構文

```
Get-ADSyncToolsTls12 [<CommonParameters>]
```

#### Description

.NET Framework の TLS 1.2 に関する情報をレジストリから読み取ります。

| 経路 | 名前 |
| --- | --- |
| HKLM:\SOFTWARE\WOW6432Node\Microsoft.NETFramework\v4.0.30319 | システムデフォルトTLSバージョン |
| HKLM:\SOFTWARE\WOW6432Node\Microsoft.NETFramework\v4.0.30319 | SchUseStrongCrypto |
| HKLM:\SOFTWARE\Microsoft.NETFramework\v4.0.30319 | システムデフォルトTLSバージョン |
| HKLM:\SOFTWARE\Microsoft.NETFramework\v4.0.30319 | SchUseStrongCrypto |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server | 有効化済み |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server | デフォルトで無効 |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client | 有効化済み |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client | デフォルトで無効 |

#### 例の数々

##### 例 1

```
Get-ADSyncToolsTls12
```

#### パラメーター

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 関連リンク

詳細情報: [Microsoft Entra Connect の TLS 1.2 の適用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement)

### Import-ADSyncToolsObjects

#### 概要

XML ファイルから Microsoft Entra Connect オブジェクトをインポートする

#### 構文

```
Import-ADSyncToolsObjects [-Path] <String> [<CommonParameters>]
```

#### Description

Export-ADSyncToolsObjects を使用してエクスポートされた XML ファイルから内部 ADSync オブジェクトをインポートします。

#### 例の数々

##### 例 1

```
Import-ADSyncToolsObjects -Path .\20210224-003104_81275a23-0168-eb11-80de-00155d188c11_MV.xml
```

#### パラメーター

##### -パス

インポートする XML ファイルのパス

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Import-ADSyncToolsRunHistory

#### 概要

Microsoft Entra Connect の実行履歴をインポートする

#### 構文

```
Import-ADSyncToolsRunHistory [-Path] <String> [<CommonParameters>]
```

#### Description

Export-ADSyncToolsRunHistory を使用して作成された XML から Microsoft Entra Connect 実行ステップの結果をインポートする関数

#### 例の数々

##### 例 1

```
Export-ADSyncToolsRunHistory -Path .\RunHistory-RunStep.xml
```

#### パラメーター

##### -パス

インポートする XML ファイルのパス

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Import-ADSyncToolsSourceAnchor

#### 概要

Microsoft Entra ID から ImmutableID をインポートする

#### 構文

```
Import-ADSyncToolsSourceAnchor [-Output] <String> [-IncludeSyncUsersFromRecycleBin] [<CommonParameters>]
```

#### Description

Guid 形式の ImmutableID 値を含むすべての Microsoft Entra ID 同期ユーザーを含むファイルを生成します。

#### 例の数々

##### 例 1

```
Import-ADSyncToolsSourceAnchor -OutputFile '.\AllSyncUsers.csv'
```

##### 例 2

```
Import-ADSyncToolsSourceAnchor -OutputFile '.\AllSyncUsers.csv' -IncludeSyncUsersFromRecycleBin
```

#### パラメーター

##### -アウトプット

CSV ファイルの出力

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -IncludeSyncUsersFromRecycleBin

Microsoft Entra ID のごみ箱から同期されたユーザーを取得する

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:
Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Invoke-ADSyncToolsSqlQuery

#### 概要

テスト目的でデータベースに対して SQL クエリを呼び出す

#### 構文

```
Invoke-ADSyncToolsSqlQuery [-SqlConnection] <SqlConnection> [[-Query] <String>] [<CommonParameters>]
```

#### Description

SQL 診断関連の関数とユーティリティ

#### 例の数々

##### 例 1

```
New-ADSyncToolsSqlConnection -Server SQLserver01.Contoso.com -Port 49823 | Invoke-ADSyncToolsSqlQuery
```

##### 例 2

```
$sqlConn = New-ADSyncToolsSqlConnection -Server SQLserver01.Contoso.com -Port 49823
Invoke-ADSyncToolsSqlQuery -SqlConnection $sqlConn -Query 'SELECT *, database_id FROM sys.databases'
```

#### パラメーター

##### -SqlConnection

SQL 接続

```yaml
Type: SqlConnection
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### -クエリ

SQL クエリ

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 2
Default value: SELECT name, database_id FROM sys.databases
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Remove-ADSyncToolsAadObject

#### 概要

Microsoft Entra ID から孤立した同期オブジェクトを削除します。

**注意:**

この関数には、Microsoft Entra ID のグローバル管理者の資格情報が必要であり、MFA による認証はサポートされていません。 テナントで DirSync が無効になっている場合は、この関数を使用するために DirSync を一時的に再度有効にする必要があります。

#### 構文

##### CsvInput

```
Remove-ADSyncToolsAadObject [-Credential] <PSCredential> [-InputCsvFilename] <Object> [-WhatIf] [-Confirm]
 [<CommonParameters>]
```

##### ObjectInput

```
Remove-ADSyncToolsAadObject [-Credential] <PSCredential> [-SourceAnchor] <Object> [-SyncObjectType] <Object>
 [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

SourceAnchor と ObjectType に基づいて、1 つ以上の同期されたオブジェクトを Microsoft Entra ID から削除します。 CSV ファイルは、 `Export-ADSyncToolsAadDisconnectors`を使用して生成できます。 **大事な：** この操作は元に戻せない可能性があります。 ごみ箱を持つユーザー オブジェクト以外は、この関数で削除された他のオブジェクトの種類 **は回復できません**。

#### 例の数々

##### 例 1

```
Remove-ADSyncToolsAadObject -InputCsvFilename .\DeleteObjects.csv -Credential (Get-Credential)
```

##### 例 2

```
Remove-ADSyncToolsAadObject -SourceAnchor '2epFRNMCPUqhysJL3SWL1A==' -SyncObjectType 'publicFolder' -Credential (Get-Credential)
```

#### パラメーター

##### -資格 情報

Microsoft Entra グローバル管理者の資格情報

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -InputCsvFilename

CSV 入力ファイル名

```yaml
Type: Object
Parameter Sets: CsvInput
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -SourceAnchor

オブジェクト SourceAnchor

```yaml
Type: Object
Parameter Sets: ObjectInput
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -SyncObjectType

Object Type/オブジェクトの種類

```yaml
Type: Object
Parameter Sets: ObjectInput
Aliases:
Required: True
Position: 3
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi
Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf
Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

InputCsvFilename は、少なくとも 2 つの列を持つ CSV ファイルを指す必要があります: SourceAnchor、SyncObjectType

#### 出力

ExportDeletions 操作の結果を表示します

### Remove-ADSyncToolsAadPublicFolders

#### 概要

Microsoft Entra ID に存在する同期 Mail-Enabled パブリック フォルダー (MEPF) を削除します。

削除するターゲット MEPF オブジェクトの SourceAnchor/ImmutableID を指定するか、削除するオブジェクトのバッチを含む CSV リストを提供し、 `Export-ADSyncToolsAadPublicFolders`で取得できます。 **注意:**

この関数には、Microsoft Entra ID のグローバル管理者の資格情報が必要であり、MFA による認証はサポートされていません。 テナントで DirSync が無効になっている場合は、孤立したメールが有効なパブリック フォルダーを Microsoft Entra ID から削除するために、DirSync を一時的に再度有効にする必要があります。

#### 構文

##### CsvInput

```
Remove-ADSyncToolsAadPublicFolders [-Credential] <PSCredential> [-InputCsvFilename] <Object> [-WhatIf] [-Confirm] [<CommonParameters>]
```

##### ObjectInput

```
Remove-ADSyncToolsAadPublicFolders [-Credential] <PSCredential> [-SourceAnchor] <Object> [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

SourceAnchor または CSV リストに基づいて、同期 Mail-Enabled パブリック フォルダー オブジェクトを Microsoft Entra ID から削除します。 CSV リストは、Microsoft Entra ID 内のすべての孤立した Mail-Enabled パブリック フォルダーを識別して削除する `Export-ADSyncToolsAadPublicFolders` を使用して取得できます。 **重要**: この操作は元に戻すことができません。 削除された Mail-Enabled パブリック フォルダー オブジェクトは、Microsoft Entra ID から復元できません。

#### 例の数々

##### 例 1

```
Remove-ADSyncToolsAadPublicFolders -InputCsvFilename .\DeleteObjects.csv -Credential (Get-Credential)
```

##### 例 2

```
Remove-ADSyncToolsAadPublicFolders -SourceAnchor '2epFRNMCPUqhysJL3SWL1A==' -Credential (Get-Credential)
```

#### パラメーター

##### -資格 情報

Microsoft Entra グローバル管理者の資格情報

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: true
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -InputCsvFilename

入力 CSV ファイルのパス

```yaml
Type: String
Parameter Sets: InputCsv
Aliases:
Required: true
Position: 2
Default value: None
Accept pipeline input: true (ByPropertyName)
Accept wildcard characters: false
```

##### -SourceAnchor

ターゲット SourceAnchor/ImmutableID

```yaml
Type: String
Parameter Sets: SourceAnchor
Aliases:
Required: true
Position: 2
Default value: None
Accept pipeline input: true (ByPropertyName)
Accept wildcard characters: false
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 入力

CSV 入力ファイルは、Export-ADSyncToolsAadPublicFolders を使用して生成できます。 パス パラメーターは、少なくとも 2 つの列 (SourceAnchor、SyncObjectType) を含む CSV ファイルを指す必要があります。

#### 出力

ExportDeletions 操作の結果を表示します。

### Remove-ADSyncToolsExpiredCertificates

#### 概要

UserCertificate 属性から期限切れの証明書を削除するスクリプト

#### 構文

```
Remove-ADSyncToolsExpiredCertificates [-TargetOU] <String> [[-BackupOnly] <Boolean>] [-ObjectClass] <String>
 [<CommonParameters>]
```

#### Description

このスクリプトは、Active Directory ドメイン内のターゲット組織単位からすべてのオブジェクトを取得します。オブジェクト クラス (ユーザー/コンピューター) でフィルター処理され、UserCertificate 属性に存在するすべての期限切れの証明書が削除されます。 既定 (BackupOnly モード) では、期限切れの証明書のみがファイルにバックアップされ、AD で変更は行われません。 `-BackupOnly $false`を使用する場合、これらのオブジェクトの UserCertificate 属性に存在する期限切れの証明書は、ファイルにコピーされた後、Active Directory から削除されます。 各証明書は、別のファイル名 ( `ObjectClass_ObjectGUID_CertThumprint.cer`) にバックアップされます。 また、スクリプトによって CSV 形式のログ ファイルが作成され、実際に実行されたアクション (スキップ/エクスポート/削除) を含め、有効または期限切れの証明書を持つすべてのユーザーが表示されます。

#### 例の数々

##### 例 1

ターゲット OU 内のすべてのユーザーを確認する - 期限切れの証明書は別のファイルにコピーされ、証明書は削除されません

```
Remove-ADSyncToolsExpiredCertificates -TargetOU "OU=Users,OU=Corp,DC=Contoso,DC=com" -ObjectClass user
```

##### 例 2

ターゲット OU 内のすべてのコンピューター オブジェクトから期限切れの証明書を削除する - 期限切れの証明書はファイルにコピーされ、AD から削除されます

```
Remove-ADSyncToolsExpiredCertificates -TargetOU "OU=Computers,OU=Corp,DC=Contoso,DC=com" -ObjectClass computer -BackupOnly $false
```

#### パラメーター

##### -TargetOU

AD オブジェクトを検索するターゲット OU

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -BackupOnly

BackupOnly は AD から証明書を削除しません

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:
Required: False
Position: 2
Default value: True
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ObjectClass

オブジェクト クラス フィルター

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 3
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Repair-ADSyncToolsAutoUpgradeState

#### 概要

Microsoft Entra Connect AutoUpgrade の状態を修復する

#### 構文

```
Repair-ADSyncToolsAutoUpgradeState
```

#### Description

ビルド 1.1.524 (2017 年 5 月) で導入された AutoUpgrade の問題を修正しました。この問題により、AutoUpgrade が有効になっている間に新しいバージョンのオンライン チェックが無効になります。

#### 例の数々

##### 例 1

```
Repair-ADSyncToolsAutoUpgradeState
```

### Resolve-ADSyncToolsSqlHostAddress

#### 概要

SQL サーバー名を解決する

#### 構文

```
Resolve-ADSyncToolsSqlHostAddress [-Server] <String> [<CommonParameters>]
```

#### Description

SQL 診断関連の関数とユーティリティ

#### 例の数々

##### 例 1

```
Resolve-ADSyncToolsSqlHostAddress -Server 'sqlserver01'
```

#### パラメーター

##### -サーバー

SQL Server 名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Search-ADSyncToolsADobject

#### 概要

Active Directory フォレスト内の Active Directory オブジェクトを UserPrincipalName、sAMAccountName、または DistinguishedName で検索する

#### 構文

```
Search-ADSyncToolsADobject [-Identity] <Object> [<CommonParameters>]
```

#### Description

マルチドメイン クエリをサポートし、mS-DS-ConsistencyGuid を含むすべての必須プロパティを返します。

#### 例の数々

##### 例 1

```
Search-ADSyncToolsADobject 'CN=user1,OU=Sync,DC=Contoso,DC=com'
```

##### 例 2

```
Search-ADSyncToolsADobject -Identity "user1@Contoso.com"
```

##### 例 3

```
Get-ADUser 'CN=user1,OU=Sync,DC=Contoso,DC=com' | Search-ADSyncToolsADobject
```

#### パラメーター

##### -同一性

ConsistencyGuid を設定する AD のターゲット ユーザー

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Set-ADSyncToolsMsDsConsistencyGuid

#### 概要

Active Directory オブジェクトを -ConsistencyGuid ms-ds設定する

#### 構文

```
Set-ADSyncToolsMsDsConsistencyGuid [-Identity] <Object> [-Value] <Object> [<CommonParameters>]
```

#### Description

ターゲット Active Directory ユーザーの mS-DS-ConsistencyGuid 属性の値を設定します。 マルチドメイン フォレスト内の Active Directory オブジェクトをサポートします。

#### 例の数々

##### 例 1

```
Set-ADSyncToolsMsDsConsistencyGuid -Identity 'CN=User1,OU=Sync,DC=Contoso,DC=com' -Value '00aa00aa-bb11-cc22-dd33-44ee44ee44ee'
```

##### 例 2

```
Set-ADSyncToolsMsDsConsistencyGuid -Identity 'CN=User1,OU=Sync,DC=Contoso,DC=com' -Value 'GGhsjYwBEU+buBsE4sqhtg=='
```

##### 例 3

```
Set-ADSyncToolsMsDsConsistencyGuid 'User1@Contoso.com' '11bb11bb-cc22-dd33-ee44-55ff55ff55ff'
```

##### 例 4

```
Set-ADSyncToolsMsDsConsistencyGuid 'User1@Contoso.com' 'GGhsjYwBEU+buBsE4sqhtg=='
```

##### 例 5

```
'00aa00aa-bb11-cc22-dd33-44ee44ee44ee' | Set-ADSyncToolsMsDsConsistencyGuid -Identity User1
```

##### 例 6

```
'GGhsjYwBEU+buBsE4sqhtg==' | Set-ADSyncToolsMsDsConsistencyGuid User1
```

#### パラメーター

##### -同一性

mS-DS-ConsistencyGuid を設定する AD のターゲット オブジェクト

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### -価値

設定する値 (ImmutableId、Byte 配列、GUID、GUID 文字列、または Base64 文字列)

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Set-ADSyncToolsTls12

#### 概要

.NET Framework の Client\Server TLS 1.2 設定を設定します

#### 構文

```
Set-ADSyncToolsTls12 [[-Enabled] <Boolean>] [<CommonParameters>]
```

#### Description

.NET Framework の TLS 1.2 を有効または無効にするレジストリ エントリを設定します。

| 経路 | 名前 |
| --- | --- |
| HKLM:\SOFTWARE\WOW6432Node\Microsoft.NETFramework\v4.0.30319 | システムデフォルトTLSバージョン |
| HKLM:\SOFTWARE\WOW6432Node\Microsoft.NETFramework\v4.0.30319 | SchUseStrongCrypto |
| HKLM:\SOFTWARE\Microsoft.NETFramework\v4.0.30319 | システムデフォルトTLSバージョン |
| HKLM:\SOFTWARE\Microsoft.NETFramework\v4.0.30319 | SchUseStrongCrypto |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server | 有効化済み |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server | デフォルトで無効 |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client | 有効化済み |
| HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client | デフォルトで無効 |

パラメーターを指定せずにコマンドレットを実行すると、.NET Framework の TLS 1.2 が有効になります

#### 例の数々

##### 例 1

```
Set-ADSyncToolsTls12
```

##### 例 2

```
Set-ADSyncToolsTls12 -Enabled $true
```

#### パラメーター

##### -有効

TLS 1.2 が有効

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:
Required: False
Position: 1
Default value: True
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

#### 関連リンク

詳細情報: [Microsoft Entra Connect の TLS 1.2 の適用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement)

### Test-ADSyncToolsSqlNetworkPort

#### 概要

SQL Server ネットワーク ポートをテストする

#### 構文

```
Test-ADSyncToolsSqlNetworkPort [[-Server] <String>] [[-Port] <String>]
```

#### Description

SQL 診断関連の関数とユーティリティ

#### 例の数々

##### 例 1

```
Test-ADSyncToolsSqlNetworkPort -Server 'sqlserver01'
```

##### 例 2

```
Test-ADSyncToolsSqlNetworkPort -Server 'sqlserver01' -Port 1433
```

#### パラメーター

##### -サーバー

SQL Server 名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -港

SQL Server ポート

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

### Trace-ADSyncToolsADImport

#### 概要

Active Directory インポート ステップからトレース ファイルを作成します

#### 構文

##### ADConnectorXML

```
Trace-ADSyncToolsADImport [-DC] <String> [-RootDN] <String> [[-Filter] <String>] [[-Credential] <PSCredential>]
 [-SSL] [-ADConnectorXML] <String> [<CommonParameters>]
```

##### ADwatermarkInput

```
Trace-ADSyncToolsADImport [-DC] <String> [-RootDN] <String> [[-Filter] <String>] [[-Credential] <PSCredential>]
 [-SSL] [-ADwatermark] <String> [<CommonParameters>]
```

#### Description

特定の Active Directory 透かしチェックポイント (パーティション Cookie とも呼ばれます) から実行される Active Directory インポートのすべての LDAP クエリをトレースします。 現在のフォルダーにトレース ファイル '.\ADimportTrace\_yyyyMMddHHmmss.log' を作成します。 -ADConnectorXML を使用するには、Synchronization Service Manager に移動し、AD コネクタを右クリックし、[Export Connector...] を選択します。

#### 例の数々

##### 例 1

AD Connector XML ファイルを指定してユーザー オブジェクトの Active Directory インポートをトレースする

```
Trace-ADSyncToolsADImport -DC 'DC1.contoso.com' -RootDN 'DC=Contoso,DC=com' -Filter '(&(objectClass=user))' -ADConnectorXML .\ADConnector.xml
```

##### 例 2

Active Directory 透かし (Cookie) と AD コネクタの資格情報を指定して、すべてのオブジェクトの Active Directory インポートをトレースする

```
$creds = Get-Credential
Trace-ADSyncToolsADImport -DC 'DC1.contoso.com' -RootDN 'DC=Contoso,DC=com' -Credential $creds -ADwatermark "TVNEUwMAAAAXyK9ir1zSAQAAAAAAAAAA(...)"
```

#### パラメーター

##### -直流

ターゲット ドメイン コントローラー

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -RootDN

フォレスト ルート DN

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -フィルター

トレースする AD オブジェクトの種類。 すべてのオブジェクト型に '(&(objectClass=\*))' を使用する

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 3
Default value: (&(objectClass=*))
Accept pipeline input: False
Accept wildcard characters: False
```

##### -資格 情報

AD に対して LDAP クエリを実行するための資格情報を指定します

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: False
Position: 4
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -SSL

SSL 接続

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases:
Required: False
Position: 5
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADConnectorXML

AD コネクタの XML ファイルのエクスポート - [AD コネクタ] を右クリックし、[Export Connector...] を選択します。

```yaml
Type: String
Parameter Sets: ADConnectorXML
Aliases:
Required: True
Position: 6
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -ADwatermark

たとえば、XML ファイルの代わりに透かしを手動で入力する `$ADwatermark = "TVNEUwMAAAAXyK9ir1zSAQAAAAAAAAAA(...)"`

```yaml
Type: String
Parameter Sets: ADwatermarkInput
Aliases:
Required: True
Position: 6
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Trace-ADSyncToolsLdapQuery

#### 概要

LDAP クエリのトレース

#### 構文

```
Trace-ADSyncToolsLdapQuery [-RootDN] <String> [-Credential] <PSCredential> [[-Server] <String>]
 [[-Port] <Int32>] [-Filter <String>] [<CommonParameters>]
```

#### Description

{{ 説明を入力 }}

#### 例の数々

##### 例 1

```
Trace-ADSyncToolsLdapQuery -RootDN "DC=Contoso,DC=com" -Credential $Credential
```

#### パラメーター

##### -RootDN

Forest/Domain DistinguishedName

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -資格 情報

AD 資格情報

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -サーバー

ドメイン コントローラー名 (省略可能)

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 3
Default value: None
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -港

ドメイン コントローラー ポート (既定値: 389)

```yaml
Type: Int32
Parameter Sets: (All)
Aliases:
Required: False
Position: 3
Default value: 389
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### -フィルター

LDAP フィルター (既定値: objectClass=\*)

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: Named
Default value: (objectClass=*)
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Update-ADSyncToolsSourceAnchor

#### 概要

新しい ConsistencyGuid (ImmutableId) を使用してユーザーを更新します

#### 構文

```
Update-ADSyncToolsSourceAnchor [[-DistinguishedName] <String>] [-ImmutableIdGUID] <String> [-Action] <String>
 [-Output] <String> [-WhatIf] [-Confirm] [<CommonParameters>]
```

#### Description

ConsistencyGuid レポートから取得した新しい ConsistencyGuid (ImmutableId) 値でユーザーを更新します。 この関数は、 `-WhatIf` スイッチをサポートします。

注: ConsistencyGuid レポートは、タブ区切り記号を使用してインポートする必要があります。

#### 例の数々

##### 例 1

```
Import-Csv .\AllSyncUsers-Report.csv -Delimiter "`t"| Update-ADSyncToolsSourceAnchor -Output .\AllSyncUsersTEST-Result2 -WhatIf
```

##### 例 2

```
Import-Csv .\AllSyncUsers-Report.csv -Delimiter "`t"| Update-ADSyncToolsSourceAnchor -Output .\AllSyncUsersTEST-Result2
```

#### パラメーター

##### -DistinguishedName

DistinguishedName

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: False
Position: 1
Default value: False
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### -ImmutableIdGUID

ImmutableIdGUID

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 2
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### -アクション

アクション

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 3
Default value: None
Accept pipeline input: True (ByPropertyName, ByValue)
Accept wildcard characters: False
```

##### -アウトプット

LOG ファイルの出力ファイル名

```yaml
Type: String
Parameter Sets: (All)
Aliases:
Required: True
Position: 4
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -WhatIf

コマンドレットを実行した場合の動作を示します。 コマンドレットは実行されません。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: wi
Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### -確認

コマンドレットを実行する前に確認を求めるメッセージが表示されます。

```yaml
Type: SwitchParameter
Parameter Sets: (All)
Aliases: cf
Required: False
Position: Named
Default value: None
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Get-ADSyncToolsDuplicateUsersSourceAnchor

#### 概要

"ソース アンカーが変更されました" エラーが発生したすべてのオブジェクトの一覧を取得します。

#### 構文

```
Get-ADSyncToolsDuplicateUsersSourceAnchor [-ADConnectorName] <Object> [<CommonParameters>]
```

#### Description

M&A のような特定のシナリオでは、顧客が重複するユーザー オブジェクトを含む新しいフォレストを Microsoft Entra Connect に追加します。 これにより、新しく参加したユーザーの新しいコネクタの優先順位が高い場合、複数の同期エラーが発生します。 このコマンドレットは、"ソース アンカーが変更されました" というエラーを含むすべてのオブジェクトの一覧を提供します。

#### 例の数々

##### 例 1

```
Get-ADSyncToolsDuplicateUsersSourceAnchor -ADConnectorName Contoso.com
```

#### パラメーター

##### -ADConnectorName

ユーザー ソース アンカーを修復する必要がある AD コネクタ名

```yaml
Type: Object
Parameter Sets: (All)
Aliases:
Required: true
Position: 1
Default value: 
Accept pipeline input: True (ByPropertyName)
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。

### Set-ADSyncToolsDuplicateUsersSourceAnchor

#### 概要

"ソース アンカーが変更されました" エラーを含むすべてのオブジェクトを修正します。

#### 構文

```
et-ADSyncToolsDuplicateUsersSourceAnchor [-DuplicateUserSourceAnchorInfo] <DuplicateUserSourceAnchorInfo> [-ActiveDirectoryCredential <PSCredential>] [-OverridePrompt <Boolean>] [<CommonParameters>]
```

#### Description

このコマンドレットは、パイプライン入力として Get-ADSyncToolsDuplicateUsersSourceAnchor からオブジェクトの一覧を受け取ります。 次に、msDS-ConsistencyGuid 属性を元のオブジェクトの sourceAnchor/immutableID で更新することで、同期エラーを修正します。 このコマンドレットには、省略可能なパラメーター "Override prompt" (既定では False) があります。 True に設定されている場合、msDS-ConsistencyGuid 属性を更新するときにユーザーにメッセージが表示されません。

#### 例の数々

##### 例 1

```
Get-ADSyncToolsDuplicateUsersSourceAnchor -ADConnectorName Contoso.lab | Set-ADSyncToolsDuplicateUsersSourceAnchor
```

##### 例 2

```
Get-ADSyncToolsDuplicateUsersSourceAnchor -ADConnectorName Contoso.lab | Set-ADSyncToolsDuplicateUsersSourceAnchor -OverridePrompt $true
```

#### パラメーター

##### -DuplicateUserSourceAnchorInfo

ソース アンカーを修正する必要があるユーザー リスト

```yaml
Type: DuplicateUserSourceAnchorInfo
Parameter Sets: (All)
Aliases:
Required: True
Position: 1
Default value: 
Accept pipeline input: True (ByValue, ByPropertyName)
Accept wildcard characters: False
```

##### -ActiveDirectoryCredential

AD EA/DA 管理者資格情報、既定の資格情報が指定されていない場合は使用されます

```yaml
Type: PSCredential
Parameter Sets: (All)
Aliases:
Required: False
Position: Named
Default value: 
Accept pipeline input: False
Accept wildcard characters: False
```

##### -OverridePrompt

```yaml
Type: Boolean
Parameter Sets: (All)
Aliases:
Required: False
Position: Named
Default value: False
Accept pipeline input: False
Accept wildcard characters: False
```

##### 共通パラメーター

このコマンドレットは、一般的なパラメーター -Debug、-ErrorAction、-ErrorVariable、-InformationAction、-InformationVariable、-OutVariable、-OutBuffer、-PipelineVariable、-Verbose、-WarningAction、および -WarningVariable をサポートしています。 詳細については、[about_CommonParameters](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.powershell.core/about/about_commonparameters)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-faq"} -->
## Microsoft Entra Connect に関する FAQ - - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-faq
- Service: entra-id / hybrid-connect
- Article date: 2024-12-27
- Summary: この記事では、Microsoft Entra Connect についてよく寄せられる質問に回答します。

### 一般的なインストール

#### セキュリティ攻撃面を縮小するために Microsoft Entra Connect サーバーを強化するにはどうすればよいですか。

Microsoft では、Microsoft Entra Connect サーバーを強化して、IT 環境に含まれるこの重要なコンポーネントに対する、セキュリティの攻撃面を縮小することをお勧めしています。 これらの推奨事項に従うことで、組織に対するセキュリティ上のリスクは減少します。

- ドメイン参加済みサーバーに Microsoft Entra Connect をデプロイし、管理アクセス権を、ドメイン管理者または他の厳格に管理されたセキュリティ グループに制限します。

詳細については、以下をご覧ください。

- [管理者グループのセキュリティ保護](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/appendix-g--securing-administrators-groups-in-active-directory)
- [ビルトイン Administrator アカウントのセキュリティ保護](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/appendix-d--securing-built-in-administrator-accounts-in-active-directory)
- [攻撃対象領域の縮小によるセキュリティの向上と維持](https://learn.microsoft.com/ja-jp/security/privileged-access-workstations/overview#2-reduce-attack-surfaces)
- [Active Directory の攻撃対象領域の縮小](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-ds/plan/security-best-practices/reducing-the-active-directory-attack-surface)

#### Microsoft Entra ハイブリッド ID 管理者が 2 要素認証 (2FA) を有効にしている場合、インストールは機能しますか。

2016 年 2 月のビルド時点で、このシナリオがサポートされています。

#### Microsoft Entra Connect を無人インストールする方法はありますか。

インストール ウィザードを使用する場合にのみ、Microsoft Entra Connect のインストールがサポートされます。 サイレント モードでの無人インストールはサポートされていません。

#### ドメインに接続できないフォレストがあります。 Microsoft Entra Connect をインストールするにはどうすればよいですか。

2016 年 2 月のビルド時点で、このシナリオがサポートされています。

#### Microsoft Entra Domain Services 正常性エージェントはサーバー コア上で動作しますか。

はい。 エージェントをインストールした後、次の PowerShell コマンドレットを使って登録プロセスを実行できます。

`Register-AzureADConnectHealthADDSAgent -Credentials $cred`

#### 2 つのドメインから Microsoft Entra ID への同期は Microsoft Entra Connect でサポートされていますか。

はい、このシナリオはサポートされています。 [複数のドメイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-multiple-domains)に関するページを参照してください。

#### Microsoft Entra Connect では同じ Active Directory ドメインに対して複数のコネクタを使用できますか。

いいえ、同じ AD ドメインの複数のコネクタはサポートされていません。

#### Microsoft Entra Connect データベースをローカル データベースからリモート SQL Server インスタンスに移行できますか。

はい、次の手順は、この移動の方法に関する一般的なガイダンスです。 詳細については、「[Microsoft Entra Connect データベースを SQL Server Express からリモート SQL Server に移動する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-move-db)」を参照してください。

1. LocalDB ADSync データベースをバックアップします。 このバックアップを行う最も簡単な方法は、Microsoft Entra Connect と同じコンピューターにインストールされている SQL Server Management Studio を使うことです。 *(LocalDb).\ADSync* に接続し、ADSync データベースをバックアップします。
2. リモート SQL Server インスタンスに ADSync データベースを復元します
3. 既存の[リモート SQL データベース](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-database)に対して Microsoft Entra Connect をインストールします。 この記事では、ローカルの SQL Database を使用して移行する方法について説明します。 リモート SQL Database を使用して移行する場合は、手順 5 で Windows ADSync サービスを使用する既存のサービス アカウントも入力する必要があります。 この同期エンジンのサービス アカウントを次に示します。

    **既存のサービス アカウントを使用する**: 既定では、Microsoft Entra Connect は同期サービスで使われる仮想サービス アカウントを使用します。 リモート SQL Server インスタンスまたは認証を使用する Web プロキシの場合は、ドメイン内のマネージド サービス アカウントまたはサービス アカウントを使用します。 このような場合は、使用するアカウントを入力します。 サービス アカウントのログイン資格情報を作成できるように、インストールを実行しているユーザーが SQL のシステム管理者であることを確認します。 詳しくは、「[Microsoft Entra Connect: アカウントとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions#adsync-service-account)」をご覧ください。

    最新のビルドでは、SQL 管理者がデータベースのプロビジョニングを実行し、Microsoft Entra Connect 管理者にデータベース所有者権限を付与して、製品をインストールできます。 詳しくは、「[SQL によって委任された管理者のアクセス許可を使用した Microsoft Entra Connect のインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-sql-delegation)」をご覧ください。

シンプルにするため、Microsoft Entra Connect をインストールするユーザーに SQL のシステム管理者権限が付与されていることをお勧めします。 ただし、最近のビルドでは、「[SQL の委任された管理者権限を使用した Microsoft Entra Connect をインストールする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-sql-delegation)」で説明されているように、委任された SQL 管理者を使用できるようになりました。

#### フィールドからのベスト プラクティスにはどのようなものがありますか?

以下は、エンジニアリング、サポート、および Microsoft のコンサルタントが長年にわたり開発してきたベスト プラクティスのいくつかについて情報を提供する文書です。 すばやく参照できる箇条書きで示されています。 この一覧を包括的なものとすることが試みられていますが、今後一覧に追加されるその他のベスト プラクティスが存在する可能性があります。

- 完全な SQL を使用する場合も、ローカルとリモートの比較には変わりがありません。
    - ホップ数がより少ない
    - トラブルシューティングがより簡単
    - 複雑さがより軽減されている
    - リソースを SQL に指定し、Microsoft Entra Connect と OS に対するオーバーヘッドを許可する必要があります。
- 可能な場合は Web プロキシをバイパスします。それ以外の場合は、Web プロキシのタイムアウト値が 5 分以上であることを確認します。
- Web プロキシが必要な場合は、machine.config ファイルに Web プロキシ構成を追加する必要があります。
- ローカルの SQL ジョブとメンテナンス、およびそれらが Microsoft Entra Connect に与える影響 (特に再インデックス作成) に注意を払います。
- DNS が外部で確実に名前を解決できるようにします。
- 物理サーバーまたは仮想サーバーのどちらを使用しているかに関わらず、[サーバー仕様](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#hardware-requirements-for-azure-ad-connect)が確実に推奨に従っているようにします。
- 仮想サーバーを使用している場合、必要なリソースは確実に専用にします。
- ディスクおよびディスク構成が確実に SQL Server のベスト プラクティスに対応するようにします。
- Microsoft Entra Connect Health をインストールおよび構成して監視を行います。
- Microsoft Entra Connect に組み込まれているエクスポート削除しきい値を使用して、誤って削除されないように保護します。
- 追加される可能性があるすべての変更および新しい属性に対して準備するために、リリースの更新情報を慎重に確認してください。
- すべてをバックアップする:
    - キーをバックアップする
    - 同期規則をバックアップする
    - サーバーの構成をバックアップする
    - SQL データベースをバックアップする
- デッドロックが発生し、実行プロファイルがフリーズする可能性があるため、ADSync データベースに Azure Backup を使用しないでください。
- SQL VSS Writer (サード パーティ スナップショットを使用した仮想サーバーでは一般的) なしで SQL をバックアップしているサード パーティ製バックアップ エージェントが決して存在しないようにします。
- 使用されているカスタム同期規則によって複雑さが増す場合は、その規則数を制限します。
- Microsoft Entra Connect サーバーを階層 0 サーバーとして扱います。
- 影響と適切なビジネス ドライバーをよく理解せずにクラウド同期規則を変更することに用心します。
- Microsoft Entra Connect および Microsoft Entra Connect Health をサポートするために適切な URL およびファイアウォールのポートが開かれていることを確認します。
- クラウドでフィルター処理される属性を活用して、ファントム オブジェクトのトラブルシューティングを行い、そのオブジェクトを防止します。
- ステージング サーバーでは、サーバー間の一貫性を保つために Microsoft Entra Connect Configuration Documenter ツールを確実に使用します。
- ステージング サーバーは個別のデータ センター (物理的な場所) に置く必要があります。
- ステージング サーバーは高可用性ソリューションになることを想定していませんが、複数のステージング サーバーを備えることができます。
- "ラグ" ステージング サーバーを導入すれば、問題が発生した場合の潜在的なダウンタイムを軽減することが可能です。
- まず、ステージング サーバー上のすべてのアップグレードをテストおよび検証します。
- ステージング サーバーに切り替える前に、必ずエクスポートを検証してください。 完全インポートおよび完全同期用のステージング サーバーを活用して、ビジネスへの影響を軽減します。
- Microsoft Entra Connect サーバー間でのバージョンの一貫性を可能な限り保持します。

#### ワークグループ コンピューター上に Microsoft Entra Connector アカウントを作成することを Microsoft Entra Connect に許可することができますか。

いいえ、Microsoft Entra Connector アカウントの自動作成を Microsoft Entra Connect に許可するには、該当するコンピューターがドメインに参加している必要があります。

### ネットワーク

#### ファイアウォールやネットワーク デバイスなど、ネットワーク上で接続を開いたままにすることができる時間を制限するものがあります。 Microsoft Entra Connect を使用する場合、クライアント側のタイムアウトしきい値はどのくらいにすればいいでしょうか。

すべてのネットワーク ソフトウェアや物理デバイスなど、接続を開ける最大時間を制限するものは、Microsoft Entra Connect クライアントがインストールされているサーバーと Microsoft Entra ID 間の接続に対して少なくとも 5 分 (300 秒) のしきい値を使用する必要があります。 この推奨事項は、以前リリースされたすべての Microsoft ID 同期ツールにも適用されます。

#### シングル ラベル ドメイン (SLD) はサポートされていますか。

シングル ラベル ドメインのネットワーク構成が適切に機能している限り、このネットワーク構成に対しては、シングル ラベル ドメインを利用した Microsoft Entra Connect Sync の利用がサポートされることを強くお勧めします ([こちらの記事を参照](https://support.microsoft.com/help/2269810/microsoft-support-for-single-label-domains))。 Active Directory NetBIOS ドメイン名が FQDN ドメイン名と異なる SLD シナリオでは、Microsoft Entra Connect のインストールはサポートされていません。

#### 切り離された AD ドメインを持つフォレストはサポートされますか。

いいえ。Microsoft Entra Connect は、切り離された名前空間を持つオンプレミスのフォレストはサポートしていません。

#### "ドット形式" の NetBios 名はサポートされていますか。

いいえ。Microsoft Entra Connect では、NetBios 名にドット (.) が含まれているオンプレミスのフォレストやドメインはサポートしていません。

#### 純粋な IPv6 環境はサポートされますか。

いいえ。Microsoft Entra Connect は、純粋な IPv6 環境はサポートしていません。

#### マルチフォレスト環境を使用しており、2 つのフォレスト間のネットワークでは NAT (ネットワーク アドレス変換) を使用しています。 2 つのフォレスト間で Microsoft Entra Connect の使用はサポートされますか。

いいえ。NAT をサポートしていない Active Directory に依存しているため、NAT 経由での Microsoft Entra Connect の使用はサポートされていません。 [NAT 経由の Active Directory のサポート境界](https://learn.microsoft.com/troubleshoot/windows-server/active-directory/support-for-active-directory-over-nat)を参照してください。

### フェデレーション

#### Microsoft 365 の証明書を更新するように求める電子メールを受け取った場合はどうすればいいですか?

証明書の更新方法については、[証明書の更新](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-o365-certs)に関する記事を参照してください。

#### Microsoft 365 証明書利用者の "証明書利用者の自動更新" を設定しました。 トークン署名証明書が自動的にロールオーバーされるときに、何か必要な操作はありますか。

[証明書の更新](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-o365-certs)に関する記事に記載されているガイダンスに従ってください。

### 環境

#### Microsoft Entra Connect のインストール後のサーバー名の変更はサポートされていますか。

不正解です。 サーバー名を変更すると、同期エンジンは SQL データベース インスタンスに接続できなくなり、サービスを開始できなくなります。

#### FIPS 対応のコンピューター上で次世代暗号化 (NGC) 同期規則はサポートされていますか?

不正解です。 これらはサポートされていません。

#### [Microsoft Entra 管理センター]https://entra.microsoft.com) で同期されたデバイスを無効にした場合、再び有効になっているのはなぜですか?

同期されているデバイスは、オンプレミスで作成または管理されている場合があります。 同期されているデバイスがオンプレミスで有効になっているときは、前に管理者によって無効になった場合でも、[Microsoft Entra 管理センター](https://entra.microsoft.com)で再び有効になることがあります。 同期されているデバイスを無効にするには、オンプレミスの Active Directory を使用して、コンピューター アカウントを無効にします。

#### Microsoft 365 または [Microsoft Entra 管理センター]https://entra.microsoft.com) で同期されているユーザーのサインインをブロックした場合、サインイン時に再びブロックが解除されるのはなぜですか?

同期されているユーザーは、オンプレミスで作成または管理されている場合があります。 アカウントがオンプレミスで有効になっている場合、管理者によって実行されたサインイン ブロックは解除される可能性があります。

### ID データ

#### Microsoft Entra ID の userPrincipalName (UPN) 属性がオンプレミス UPN と一致しないのはなぜですか。

詳細については、以下の記事を参照してください。

- [Microsoft 365、Azure、Intune におけるユーザー名が、オンプレミスの UPN または代替ログイン ID と一致しない](https://mskb.pkisolutions.com/kb/2523192)
- [異なるフェデレーション ドメインを使用するようにユーザー アカウントの UPN を変更した後、Azure Active Directory 同期ツールによって変更が同期されない](https://mskb.pkisolutions.com/kb/2669550)

また、「[Microsoft Entra Connect 同期サービスの機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-features)」で説明されているように、同期エンジンが UPN を更新できるよう Microsoft Entra ID を構成することもできます。

#### オンプレミスの Microsoft Entra グループまたは連絡先オブジェクトと既存の Microsoft Entra グループまたは連絡先オブジェクトとのあいまい一致はサポートされていますか。

はい。このあいまい一致は proxyAddress に基づいています。 メールが有効でないグループに対して、あいまい一致はサポートされていません。

#### 既存の Microsoft Entra グループまたは連絡先オブジェクトに対して手動で設定された ImmutableId 属性とオンプレミスの Microsoft Entra グループまたは連絡先オブジェクトとの完全一致はサポートされていますか。

いいえ。既存の Microsoft Entra グループまたは連絡先オブジェクトに対して手動で設定された ImmutableId 属性の完全一致は現在サポートされていません。

### カスタム構成

#### Microsoft Entra Connect 用の PowerShell コマンドレットのドキュメントはどこにありますか。

このサイトに記載されているコマンドレットを除き、Microsoft Entra Connect で使用されている PowerShell コマンドレットは、ユーザーによる使用をサポートしていません。

#### Synchronization Service Manager の [サーバーのエクスポート/インポート] オプションを使用して、サーバー間で構成を移動できますか。

不正解です。 このオプションはすべての構成設定を取得しないため、使用すべきではありません。 代わりに、2 台目のサーバーでウィザードを使用して基本構成を作成し、同期ルール エディターを使用して PowerShell スクリプトを生成し、サーバー間でカスタム ルールを移動してください。 詳細については、「[スウィング移行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version#swing-migration)」を参照してください。

#### Azure サインイン ページではパスワードがキャッシュされますか。また、パスワード入力要素の autocomplete 属性を "false" に設定することで、このキャッシュを防ぐことはできますか。

現在、オートコンプリート タグを含め、**[パスワード]** フィールドの HTML 属性を変更することはできません。 現在、**[パスワード]** フィールドに属性を追加できるようカスタム Javascript を許可する機能の開発に取り組んでいます。

#### Azure サインイン ページでは、以前正常にサインインしたユーザーのユーザー名が表示されますか。 また、この動作は無効にできますか。

現在、オートコンプリート タグを含め、**[パスワード]** 入力フィールドの HTML 属性を変更することはできません。 現在、**[パスワード]** フィールドに属性を追加できるようカスタム Javascript を許可する機能の開発に取り組んでいます。

#### 同時セッションを防ぐ方法はありますか。

不正解です。

### 自動アップグレード

#### 自動アップグレードを使用した場合の利点と結果について教えてください。

Microsoft では、すべてのお客様に対し、Microsoft Entra Connect インストールの自動アップグレードを有効にするようお勧めしています。 自動アップグレードの利点は、常に最新のパッチを適用できることです (Microsoft Entra Connect で確認された脆弱性に対応するセキュリティ更新プログラムなど)。 アップグレード プロセスは簡単で、新しいバージョンがリリースされ次第、自動的に実行されます。 Microsoft Entra Connect の何千ものお客様が、新しいリリースのたびに自動アップグレードを使用しています。

自動アップグレード プロセスでは、まずインストールが自動アップグレードの対象かどうかを確認します。 対象の場合は、アップグレードが実行され、テストされます。 このプロセスには、ルールおよび特定の環境要因に対するカスタム変更を検索する処理も含まれています。 テストの結果、アップグレードが失敗していた場合は、旧バージョンが自動的に復元されます。

環境の規模によっては、処理に数時間かかることがあります。 アップグレードの実行中は、Windows Server Active Directory と Microsoft Entra ID 間の同期は実行されません。

#### 自動アップグレードが動作しなくなっているため、新しいバージョンをインストールする必要があるという内容の電子メールが送られてきました。 必要な作業

昨年リリースされた Microsoft Entra Connect のバージョンでは、特定の状況において、自動アップグレード機能がサーバー上で無効になることがあります。 この問題は、Microsoft Entra Connect バージョン 1.1.750.0 で修正しました。 この問題の影響を受けている場合は、PowerShell スクリプトを実行して修復するか、手動で最新バージョンの Microsoft Entra Connect にアップグレードすることで軽減できます。

PowerShell スクリプトを実行するには、[スクリプトをダウンロード](https://learn.microsoft.com/ja-jp/samples/browse/?redirectedfrom=TechNet-Gallery)して、Microsoft Entra Connect サーバーの管理 PowerShell ウィンドウで実行します。 スクリプトの実行方法については、[この短い動画](https://aka.ms/repairaadcau)をご覧ください。

手動でアップグレードするには、`AADConnect.msi` ファイルの最新バージョンをダウンロードして実行する必要があります。

- 現在のバージョンが 1.1.750.0 よりも前の場合は、[最新バージョン](https://www.microsoft.com/download/details.aspx?id=47594)をダウンロードしてアップグレードしてください。
- Microsoft Entra Connect のバージョンが 1.1.750.0 以降の場合、追加の操作は必要ありません。 自動アップグレードの修正プログラムを含むバージョンを既に使用しています。

#### 最新バージョンにアップグレードして、自動アップグレードを再度有効にするよう求める内容の電子メールが送られてきました。 バージョン 1.1.654.0 を使用しています。 アップグレードする必要はありますか。

はい。バージョン 1.1.750.0 以降にアップグレードして、自動アップグレードを再度有効にする必要があります。 [最新バージョンをダウンロードしてアップグレード](https://www.microsoft.com/download/details.aspx?id=47594)してください。

#### 最新バージョンにアップグレードして、自動アップグレードを再度有効にするよう求める内容の電子メールが送られてきました。 PowerShell を使用して自動アップグレードを有効にしたのですが、最新バージョンをインストールする必要があるのでしょうか。

はい。1.1.750.0 以降のバージョンにアップグレードする必要があります。 PowerShell を使用して自動アップグレード サービスを有効にした場合、1.1.750.0 より前のバージョンで見つかった自動アップグレードの問題は回避されません

#### 新しいバージョンにアップグレードする必要があるのですが、Microsoft Entra Connect を誰がインストールしたのかわからないため、ユーザー名とパスワードがわかりません。 これらの情報は必要ですか。

Microsoft Entra Connect の初回アップグレードに使用したユーザー名とパスワードを把握しておく必要はありません。 全体管理者ロールを持つ任意の Microsoft Entra アカウントを使用します。

#### 現在使用している Microsoft Entra Connect のバージョンはどうすれば確認できますか。

サーバーにインストールされている Microsoft Entra Connect のバージョンを確認するには、コントロール パネルに移動し、次のように **[プログラム]**&gt;**[Programs and Features] (プログラムと機能)** の順に選択して、インストールされている Microsoft Entra Connect のバージョンを調べてください。

[Image: コントロール パネルの Microsoft Entra Connect のバージョン]

#### 最新バージョンの Microsoft Entra Connect にアップグレードするにはどうすればよいですか。

最新バージョンにアップグレードする方法については、「[Microsoft Entra Connect: 旧バージョンから最新バージョンにアップグレードする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)」を参照してください。

#### 昨年、Microsoft Entra Connect を最新バージョンにアップグレードしたのですが、 再度アップグレードする必要はありますか。

Microsoft Entra Connect チームは、このサービスを頻繁に更新しています。 バグの修正プログラムやセキュリティ更新プログラムだけでなく、新機能を利用するには、サーバーを最新バージョンの状態に保つことが重要です。 自動アップグレードを有効にしておけば、ソフトウェアのバージョンは自動的に更新されます。 Microsoft Entra Connect のバージョン リリース履歴を確認するには、[Microsoft Entra Connect のバージョン リリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)に関するページを参照してください。

#### アップグレードにかかる時間と、ユーザーへの影響について教えてください。

アップグレードに必要な時間は、テナントのサイズによって変わります。 大規模な組織の場合は、夜間や週末にアップグレードを実行することをお勧めします。 アップグレード中は、同期アクティビティは実行されません。

#### Microsoft Entra Connect のアップグレードは行ったと思うのですが、Office ポータルにはまだ DirSync と表示されます。 なぜですか?

Office チームでは、Office ポータルに現在の製品名が反映されるようにするべく取り組んでいます。 使用している同期ツールは反映されません。

#### 自動アップグレードの状況に "中断" と表示されています。 なぜ中断されているのでしょうか。 自分で有効化する必要があるのでしょうか。

以前のバージョンでは、特定の状況において、自動アップグレードの状態が "中断" に設定されたままになるというバグが確認されています。 技術的には手動で有効にすることはできますが、いくつかの複雑な手順が必要です。 Microsoft Entra Connect の最新バージョンをインストールすることをお勧めします。

#### 私の会社では変更管理の要件が厳しく設定されているため、変更がプッシュアウトされるタイミングを制御したいのですが、自動アップグレードが起動されるタイミングを制御することはできますか。

いいえ。現在、そのような機能はありません。 この機能は、今後のリリースのために評価されています。

#### 自動アップグレードが失敗した場合、電子メールによる通知は送られてきますか。 アップグレードが成功したかどうかを確認する方法について教えてください。

アップグレードの結果は通知されません。 この機能は、今後のリリースのために評価されています。

#### 自動アップグレードのプッシュ アウト予定に関するタイム ラインは公表されていますか。

自動アップグレードは、新しいバージョンのリリース プロセスの第一歩です。 新しいリリースがあるたびに、アップグレードが自動的にプッシュされます。 Microsoft Entra Connect の新しいバージョンは、[Microsoft Entra ロードマップ](https://learn.microsoft.com/ja-jp/entra/fundamentals/whats-new)に関するページで事前に案内されます。

#### 自動アップグレードでは、Microsoft Entra Connect Health もアップグレードされますか。

はい。自動アップグレードでは、Microsoft Entra Connect Health もアップグレードされます。

#### ステージング モードの Microsoft Entra Connect サーバーも自動アップグレードされますか。

はい、ステージング モードの Microsoft Entra Connect サーバーは自動アップグレードされます。

#### 自動アップグレードが失敗し、Microsoft Entra Connect サーバーが開始されない場合は、どうすればよいですか。

まれに、アップグレードの実行後、Microsoft Entra Connect サービスが開始されないことがあります。 そのような場合は、サーバーを再起動してください。通常はこれで問題が解決します。 それでも Microsoft Entra Connect サービスが開始されない場合は、サポート チケットを発行してください。 詳細については、[サービス要求を作成して Microsoft 365 サポートに問い合わせる](https://learn.microsoft.com/ja-jp/archive/blogs/praveenkumar/how-to-create-service-requests-to-contact-office-365-support)方法に関する記事を参照してください。

#### Microsoft Entra Connect を新しいバージョンにアップグレードする際のリスクがよくわかりません。 アップグレードについて電話で説明を受けることはできますか。

新しいバージョンの Microsoft Entra Connect にアップグレードする場合は、[サービス要求を作成して Microsoft 365 サポートに問い合わせる](https://learn.microsoft.com/ja-jp/archive/blogs/praveenkumar/how-to-create-service-requests-to-contact-office-365-support)方法に関する記事を参照してサポート チケットを発行してください。

#### 操作のベスト プラクティス

Windows Server Active Directory と Microsoft Entra Directory 間で同期を行う場合に実行する必要があるベスト プラクティスを次に示します。

**同期されるすべてのアカウントに多要素認証を適用する** Microsoft Entra の多要素認証は、ユーザーに対する簡便さを保ちながら、データやアプリケーションへのアクセスを保護するのに役立ちます。 第 2 の認証方式を要求することでセキュリティが向上し、使用が簡単なさまざまな認証方法によって強力な認証を実現しています。 ユーザーは、管理者が行う構成上の決定に基づいて、MFA で認証が行われる場合と行われない場合があります。 MFA の詳細は、こちら https://www.microsoft.com/security/business/identity/mfa?rtc=1 を参照してください。

**Microsoft Entra Connect サーバーのセキュリティ ガイドラインに従う** Microsoft Entra Connect サーバーは、重要な ID データが格納されているため、[Active Directory 管理階層モデル](https://learn.microsoft.com/ja-jp/windows-server/identity/securing-privileged-access/securing-privileged-access-reference-material)の説明に従い、階層 0 のコンポーネントとして取り扱う必要があります。 [Microsoft Entra Connect サーバーのセキュリティ保護についてのガイドライン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#azure-ad-connect-server)に関する記事もご覧ください。

**資格情報の漏洩に対して PHS を有効にする** パスワード ハッシュの同期では、ご自分のハイブリッド アカウントの[漏洩資格情報検出](https://learn.microsoft.com/ja-jp/entra/id-protection/concept-identity-protection-risks)も有効になります。 Microsoft はダーク Web の研究者や法執行機関と協力し、公で利用できるユーザー名とパスワードのペアを見つけています。 これらの任意のペアがご自分のユーザーのものと一致する場合、関連付けられているアカウントは高リスクに移されます。

### トラブルシューティング

#### Microsoft Entra Connect に関するサポートはどのように受けることができますか。

[Microsoft サポート技術情報 (KB) の検索](https://www.microsoft.com/en-us/search/result.aspx?q=azure+active+directory+connect)

- Microsoft Entra Connect のサポートに関する一般的な破損時補償の技術的な解決策について、KB を検索してください。

[Microsoft Entra ID に関する Microsoft &QA の質問ページ](https://learn.microsoft.com/ja-jp/answers/topics/azure-active-directory.html)

- [Microsoft Entra コミュニティ](https://learn.microsoft.com/ja-jp/answers/topics/azure-active-directory.html)にアクセスして、技術的な質問と回答を探し、質問をすることができます。

[Microsoft Entra ID のサポートを受ける](https://learn.microsoft.com/ja-jp/entra/fundamentals/how-to-get-support)

#### 同期ステップのエラーの後にイベント 6311 と 6401 が発生するのはなぜですか。

イベント 6311 - "**The server encountered an unexpected error while performing a callback (コールバックの実行中にサーバーで予期しないエラーが発生しました)** " および 6401 - "**管理エージェント コントローラーで予期しないエラーが発生しました**" は、同期ステップのエラー後に常にログに記録されます。 これらのエラーを解決するには、同期ステップのエラーをクリーンアップする必要があります。 詳細については、[同期中のエラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sync-errors)に関する記事、および「[Microsoft Entra Connect Sync を使用したオブジェクト同期のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-objectsync)」を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-government-cloud"} -->
## Microsoft Entra Connect: Azure Government クラウドのハイブリッド ID に関する考慮事項 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-government-cloud
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect と Azure Government クラウドをデプロイするための特別な考慮事項。

この記事では、ハイブリッド環境と Microsoft Azure Government クラウドの統合に関する考慮事項について説明します。 この情報は、Azure Government クラウドを使用する管理者とアーキテクト向けのリファレンスとして提供されます。

注

(オンプレミスまたは同じクラウド インスタンスの一部である IaaS でホストされている) Microsoft Active Directory 環境を Azure Government クラウドと統合するには、 [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) の最新リリースにアップグレードする必要があります。

米国国防総省エンドポイントの完全な一覧については、ドキュメントを参照 [してください](https://learn.microsoft.com/ja-jp/microsoft-365/enterprise/microsoft-365-u-s-government-dod-endpoints)。

### Microsoft Entra パススルー認証

次の情報では、パススルー認証と Azure Government クラウドの実装について説明します。

#### URL へのアクセスを許可する

パススルー認証エージェントを展開する前に、サーバーと Microsoft Entra ID の間にファイアウォールが存在するかどうかを確認します。 ファイアウォールまたはプロキシでドメイン ネーム システム (DNS) のブロックまたは安全なプログラムが許可されている場合は、次の接続を追加します。

Von Bedeutung

次のガイダンスは、次の場合にのみ適用されます。

- パススルー認証エージェント
- [Microsoft Entra プライベート ネットワーク コネクタ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/overview-what-is-app-proxy)

Microsoft Entra プロビジョニング エージェントの URL については、クラウド同期の [インストールの前提条件](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/how-to-prerequisites) を参照してください。

| URL | 使用方法 |
| --- | --- |
| \*.msappproxy.us\*.servicebus.usgovcloudapi.net | エージェントは、これらの URL を使用して Microsoft Entra クラウド サービスと通信します。 |
| `mscrl.microsoft.us:80``crl.microsoft.us:80``ocsp.msocsp.us:80``www.microsoft.us:80` | エージェントは、これらの URL を使用して証明書を検証します。 |
| login.windows.us secure.aadcdn.microsoftonline-p.com \*.microsoftonline.us \*.microsoftonline-p.us \*.msauth.net \*.msauthimages.net \*.msecnd.net\*.msftauth.net \*.msftauthimages.net\*.phonefactor.net enterpriseregistration.windows.netmanagement.azure.com policykeyservice.dc.ad.msft.netctldl.windowsupdate.us:80 | エージェントは、登録プロセス中にこれらの URL を使用します。 |

#### Azure Government クラウドのエージェントをインストールする

Azure Government クラウドのエージェントをインストールするには、次の手順に従います。

1. コマンド ライン ターミナルで、エージェントをインストールする実行可能ファイルを含むフォルダーに移動します。
2. 次のコマンドを実行して、インストールが Azure Government 用であることを指定します。

    パススルー認証の場合:

    ```
    AADConnectAuthAgentSetup.exe ENVIRONMENTNAME="AzureUSGovernment"
    ```

    アプリケーション プロキシの場合:

    ```
    MicrosoftEntraPrivateNetworkConnectorInstaller.exe ENVIRONMENTNAME="AzureUSGovernment" 
    ```

### 単一サインイン

#### Microsoft Entra Connect サーバーを設定する

サインオン方法としてパススルー認証を使用する場合、追加の前提条件チェックは必要ありません。 サインオン方法としてパスワード ハッシュ同期を使用し、Microsoft Entra Connect と Microsoft Entra ID の間にファイアウォールがある場合は、次のことを確認します。

- Microsoft Entra Connect のバージョンは 1.1.644.0 以降を使用します。
- ファイアウォールまたはプロキシで DNS のブロックまたは安全なプログラムが許可されている場合は、ポート 443 経由で \*.msappproxy.us URL に接続を追加します。

    そうでない場合は、毎週更新される Azure データセンターの IP 範囲へのアクセスを許可します。 この前提条件は、この機能を有効にした場合にのみ適用されます。 実際のユーザー のサインオンには必要ありません。

#### シームレスな単一 Sign-On をロールアウトする

次の手順に従って、Microsoft Entra シームレス シングル サインオンをユーザーに段階的にロールアウトできます。 まず、Active Directory のグループ ポリシーを使用して、Microsoft Entra URL `https://autologon.microsoft.us` をすべてのユーザーまたは選択したユーザーのイントラネット ゾーン設定に追加します。

また、[グループ ポリシーを **使用してスクリプトを使用してステータス バーの更新を許可する]** イントラネット ゾーン ポリシー設定を有効にする必要もあります。

### ブラウザーの考慮事項

#### Mozilla Firefox (すべてのプラットフォーム)

Mozilla Firefox は Kerberos 認証を自動的に使用しません。 各ユーザーは、次の手順に従って、Firefox 設定に Microsoft Entra URL を手動で追加する必要があります。

1. Firefox を実行し、アドレス バーに **about:config** と入力します。 表示される可能性がある通知をすべて無視します。
2. **network.negotiate-auth.trusted-uris 基本設定を**検索します。 この設定では、Kerberos 認証のために Firefox によって信頼されているサイトが一覧表示されます。
3. 基本設定名を右クリックし、[ **変更**] を選択します。
4. ボックスに「 `https://autologon.microsoft.us` 」と入力します。
5. [ **OK] を** 選択し、ブラウザーをもう一度開きます。

#### Chromium に基づく Microsoft Edge (すべてのプラットフォーム)

環境内の `AuthNegotiateDelegateAllowlist` または `AuthServerAllowlist` ポリシー設定をオーバーライドした場合は、必ず Microsoft Entra URL `https://autologon.microsoft.us` を追加してください。

#### Google Chrome (すべてのプラットフォーム)

環境内の `AuthNegotiateDelegateWhitelist` または `AuthServerWhitelist` ポリシー設定をオーバーライドした場合は、必ず Microsoft Entra URL `https://autologon.microsoft.us` を追加してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-health-faq"} -->
## Microsoft Entra Connect Health に関する FAQ - Azure - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-health-faq
- Service: entra-id / hybrid-connect
- Article date: 2026-05-15
- Summary: この FAQ は、Microsoft Entra Connect Health について寄せられる質問とその回答です。 サービスの課金モデル、機能、制限、サポートなど、その使用に関して多く寄せられる質問を取り上げています。

この記事では、Microsoft Entra Connect Health に関してよく寄せられる質問 (FAQ) に回答しています。 これらの FAQ では、課金モデル、機能、制限、サポートなど、サービスの使用方法に関する質問を取り上げています。

### 一般的な質問

#### 複数の Microsoft Entra ディレクトリを管理しています。 Microsoft Entra ID P1 または P2 を持つものに切り替えるにはどうすればよいですか?

異なる Microsoft Entra テナントを切り替えるには、現在サインインしている**ユーザー名**を右上隅で選択して、適切なアカウントを選択します。 ここにアカウントが表示されていない場合は、**[サインアウト]** を選択します。次に、Microsoft Entra ID P1 または P2 (P1 または P2) が有効になっているディレクトリのグローバル管理者の資格情報を使用してサインインします。

#### Microsoft Entra Connect Health では、どのバージョンの ID の役割がサポートされていますか?

次の表は、役割と、サポートされているオペレーティング システムのバージョンの一覧です。

| 役割 | オペレーティング システム/バージョン |
| --- | --- |
| Active Directory フェデレーション サービス (AD FS) | Windows Server 2016、2019、2022、または 2025 |
| Microsoft Entra Connect | [バージョン 2.5.79.0 以降](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history) |
| Active Directory Domain Services (AD DS) | Windows Server 2016、2019、2022、または 2025 |

Windows Server Core インストールはサポートされていません。

サービスが提供する機能は、役割とオペレーティング システムによって異なる場合があります。 すべての機能をすべてのオペレーティング システムのバージョンで利用できるとは限りません。 詳しくは、機能の説明をご覧ください。

#### インフラストラクチャの監視には、いくつライセンスが必要ですか。

- 最初の Connect Health エージェントに、少なくとも 1 つの Microsoft Entra P1 または P2 ライセンスが必要です。
- 追加登録されるエージェントごとに、さらに 25 個の Microsoft Entra P1 または P2 ライセンスが必要です。
- エージェントの数は、監視対象のすべての役割 (AD FS、Microsoft Entra Connect、AD DS) で登録されるエージェントの合計数に相当します。
- Microsoft Entra Connect Health のライセンスでは、特定のユーザーにライセンスを割り当てる必要はありません。 必要な数の有効なライセンスのみが必要です。

ライセンスの情報については、[Microsoft Entra の価格ページ](https://aka.ms/aadpricing)も参照してください。

例:

| 登録されているエージェント | 必要なライセンスの数 | 監視構成の例 |
| --- | --- | --- |
| 1 | 1 | Microsoft Entra Connect サーバー × 1 |
| 2 | 26 | Microsoft Entra Connect サーバー × 1、ドメイン コントローラー × 1 |
| 3 | 51 | Active Directory フェデレーション サービス (AD FS) サーバー × 1、AD FS プロキシ × 1、ドメイン コントローラー × 1 |
| 4 | 76 | AD FS サーバー × 1、AD FS プロキシ × 1、ドメイン コントローラー × 2 |
| 5 | 101 | Microsoft Entra Connect サーバー × 1、AD FS サーバー × 1、AD FS プロキシ × 1、ドメイン コントローラー × 2 |

### インストールに関する質問

#### エージェントの新しいバージョンがあるときは、使用しているエージェントが自動的に更新されますか?

はい。エージェントの新しいバージョンがある場合、すべてのエージェントが自動的に更新されます。

#### エージェントの自動アップグレードをオプトアウトまたは無効にすることはできますか?

いいえ、自動アップグレードは必須です。 新しいバージョンがリリースされたときにエージェントをアップグレードしない場合は、エージェントをアンインストールする必要があります。

#### 各サーバーに Microsoft Entra Connect Health エージェントをインストールすると、どのような影響がありますか?

Microsoft Entra Connect Health エージェント、AD FS、Web アプリケーション プロキシ サーバー、Microsoft Entra Connect (同期) サーバー、およびドメイン コントローラーをインストールする場合の影響は、CPU、メモリ消費量、ネットワーク帯域幅、ストレージに関して最小限です。

おおよその数値を以下に示します。

- CPU 消費率: 約 1 ～ 5% 上昇。
- メモリ使用量:システム メモリ合計の最大 10%。

注

Azure と通信できない場合、エージェントは定義済みの上限までデータをローカルに保存します。 エージェントは、"最も長く使用されていない" という基準で、"キャッシュされた" データを上書きします。

- Microsoft Entra Connect Health エージェントのローカル バッファー記憶域: 最大 20 MB。
- AD FS サーバーの場合、Microsoft Entra Connect Health エージェントの AD FS 監査チャネルがすべての監査データを上書き前に処理できるように、1,024 MB (1 GB) のディスク領域をプロビジョニングすることをお勧めします。

#### Microsoft Entra Connect Health エージェントのインストール時にサーバーを再起動する必要はありますか?

いいえ。 エージェントをインストールする場合、サーバーを再起動する必要はありません。 ただし、前提条件の一部のインストール手順では、サーバーの再起動が必要です。

たとえば、.NET 4.6.2 Framework のインストールでは、サーバーの再起動が必要になる場合があります。

#### Microsoft Entra Connect Health はパススルー HTTP プロキシで動作しますか?

はい。 実行中の操作については、HTTP プロキシを使用して送信 HTTP 要求を転送するように Health エージェントを構成できます。 詳しくは、[Health エージェント対応の HTTP プロキシの構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install#configure-azure-ad-connect-health-agents-to-use-http-proxy)に関するページをご覧ください。

エージェントの登録時にプロキシを構成する必要がある場合は、Internet Explorer のプロキシ設定を事前に変更する必要があることがあります。

1. Internet Explorer を開いて、&gt;**[設定]**&gt;**[インターネット オプション]**&gt;**[接続]**&gt;**[LAN の設定]** の順に移動します。
2. **[LAN にプロキシ サーバーを使用する]** をオンにします。
3. HTTP と HTTPS/Secure でプロキシ ポートが異なる場合は、 **[詳細設定]** を選択します。

#### Microsoft Entra Connect Health では、HTTP プロキシに接続するときの基本認証がサポートされていますか?

いいえ。 基本認証に対して任意のユーザー名とパスワードを指定するメカニズムは、現在サポートされていません。

#### Microsoft Entra Connect Health エージェントを使用するには、どのファイアウォール ポートを開放すればよいですか?

ファイアウォール ポートとその他の接続要件の一覧については、「 [前提条件」セクション](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install#prerequisites) を参照してください。

#### Microsoft Entra Connect Health ポータルに同じ名前のサーバーが 2 つ表示されるのはなぜですか。?

サーバーからエージェントを削除しても、サーバーは Microsoft Entra Connect Health ポータルから自動的には削除されません。 サーバーから手動でエージェントを削除したか、サーバー自体を削除した場合は、Microsoft Entra Connect Health ポータルから手動でサーバー エントリを削除する必要があります。 Microsoft Entra Connect Health から監視対象サーバーを削除するには、Microsoft Entra 全体管理者アカウントのアクセス許可、または Azure ロールベースのアクセス制御での共同作成者ロールのいずれかを持っている必要があります。

サーバーを再イメージ化したり、詳細情報 (マシン名など) が同じになっているサーバーを新規に作成したりすることがありますが、 登録済みサーバーを Microsoft Entra Connect Health ポータルから削除せずに新しいサーバーにエージェントをインストールした場合、同じ名前の 2 つのエントリが表示される可能性があります。

この場合、古いサーバーに対応するエントリを手動で削除します。 間違いなく、このサーバーのデータは古くなっています。

#### Microsoft Entra Connect Health エージェントを Windows Server Core にインストールできますか?

いいえ。 Server Core へのインストールはサポートされていません。

#### ハイブリッド管理者ロールを持つアカウントで Microsoft Entra Connect Sync をインストールした後、Connect Health for Sync エージェントがサービスで無効になっているのはなぜですか?

Connect Health for Sync エージェントをインストールするには、グローバル管理者である必要があります。 エージェントをアクティブ化するには、グローバル管理者アカウントを使用してエージェントを再インストールする必要があります。

### ヘルスエージェントの登録とデータの最新性

#### Health エージェントの登録が失敗する一般的な理由と、問題をトラブルシューティングする方法を教えてください。

ヘルスエージェントの登録は、次の理由により失敗することがあります。

- ファイアウォールによってトラフィックがブロックされているため、エージェントが必要なエンドポイントと通信できません。 この問題は、Web アプリケーション プロキシ サーバーで起こりがちです。 必要なエンドポイントとポートへの送信通信が許可されていることを確認します。 詳細については、 [前提条件のセクション](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install#prerequisites) を参照してください。
- 発信通信がネットワーク層による TLS 検査の対象になっています。 これが原因で、エージェントによって使われる証明書が検査サーバー/エンティティによって置き換えられ、エージェントの登録を完了する手順が失敗します。
- ユーザーはエージェントの登録を実行できません。 グローバル管理者には、デフォルトで実行できます。 [Azure ロール ベースのアクセス制御 (Azure RBAC)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations#manage-access-with-azure-rbac) を使って、他のユーザーにアクセスを委任できます。

#### "Health サービス データが最新ではありません。" というアラートが通知されます。 この問題をトラブルシューティングする方法を教えてください。

Microsoft Entra Connect Health では、過去 2 時間でサーバーから一部のデータ ポイントが送られなかったときに、このアラートが生成されます。 詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-data-freshness)を参照してください。

### 操作に関する質問

#### Web アプリケーション プロキシ サーバーで監査を有効にする必要はありますか。

いいえ。Web アプリケーション プロキシ サーバーで監査を有効にする必要はありません。

#### Microsoft Entra Connect Health アラートは、どのように解決されますか?

Microsoft Entra Connect Health アラートは、成功条件を満たすと解決されます。 Microsoft Entra Connect Health エージェントは、定期的に成功条件を検出してサービスにレポートします。 一部のアラートは、時間に基づいて抑制されます。 つまり、アラートの生成から 72 時間以内に同じエラー条件が観察されない場合、アラートは自動的に解決されます。

#### "テスト認証要求 (代理トランザクション) は、トークンの取得に失敗しました。" というアラートが表示されます。 この問題をトラブルシューティングする方法を教えてください。

AD FS サーバーにインストールされた正常性エージェントが、正常性エージェントによって開始されたシンセティックトランザクションの一環としてトークンの取得に失敗した場合、Microsoft Entra Connect Health for AD FS はこのアラートを生成します。 ヘルスエージェントは、ローカルシステムのコンテキストを使用して、自己依存のパーティーへのトークンを取得しようとします。 この動作は、AD FS がトークンを発行できる状態であることを確認する包括的なテストです。

このテストは多くの場合、正常性エージェントが AD FS ファーム名を解決できないために失敗します。 この状態は、AD FS サーバーがネットワーク ロード バランサーの背後にあり、要求が (ロード バランサーの前にある通常のクライアントではなく) ロード バランサーの背後にあるノードから開始された場合に発生します。 この問題は、`C:\Windows\System32\drivers\etc` の下にある "hosts" ファイルを AD FS サーバーの IP アドレスまたは (`127.0.0.1` など) AD FS ファーム名のループバック IP アドレス (`sts.contoso.com`) を含めるように更新すると修正できます。 ホストファイルを追加すると、ネットワーク呼び出しを迂回できるようになり、その結果、ヘルスエージェントがトークンを取得できるようになります。

#### 最近のランサムウェア攻撃の修正プログラムが自分のコンピューターに適用されていないと記載された電子メールを受信しました。 なぜこの電子メールを受信したのですか。

Microsoft Entra Connect Health サービスでは、必要な修正プログラムがインストールされていることを確認するため、監視するすべてのコンピューターをスキャンします。 重要な修正プログラムが最低 1 台のコンピューターにインストールされていない場合、そのテナント管理者にその電子メールが送信されます。 この判断には、次のロジックが使用されます。

1. コンピューターにインストールされているすべての修正プログラムが検索されます。
2. 定義されたリストにあるホットフィックスが少なくとも 1 つ存在するか確認してください。
3. ある場合、マシンは保護されています。 ない場合、マシンは攻撃される可能性があります。

このチェックは、次の PowerShell スクリプトを使用して、手動で実行できます。 上記のロジックが実装されます。

```powershell
Function CheckForMS17-010 ()
{
    $hotfixes = "KB3205409", "KB3210720", "KB3210721", "KB3212646", "KB3213986", "KB4012212", "KB4012213", "KB4012214", "KB4012215", "KB4012216", "KB4012217", "KB4012218", "KB4012220", "KB4012598", "KB4012606", "KB4013198", "KB4013389", "KB4013429", "KB4015217", "KB4015438", "KB4015546", "KB4015547", "KB4015548", "KB4015549", "KB4015550", "KB4015551", "KB4015552", "KB4015553", "KB4015554", "KB4016635", "KB4019213", "KB4019214", "KB4019215", "KB4019216", "KB4019263", "KB4019264", "KB4019472", "KB4015221", "KB4019474", "KB4015219", "KB4019473"

    #checks the computer it's run on if any of the listed hotfixes are present
    $hotfix = Get-HotFix -ComputerName $env:computername | Where-Object {$hotfixes -contains $_.HotfixID} | Select-Object -property "HotFixID"

    #confirms whether hotfix is found or not
    if (Get-HotFix | Where-Object {$hotfixes -contains $_.HotfixID})
    {
        "Found HotFix: " + $hotfix.HotFixID
    } else {
        "Didn't Find HotFix"
    }
}

CheckForMS17-010

```

#### AD FS 監査が生成されていないのはなぜですか?

*Get-AdfsProperties -AuditLevel* PowerShell コマンドレットを使用して、監査ログが無効な状態になっていないことを確認してください。 詳しくは、[AD FS 監査ログ](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/technical-reference/auditing-enhancements-to-ad-fs-in-windows-server#auditing-levels-in-ad-fs-for-windows-server-2016)に関する記事をご覧ください。 AD FS サーバーに監査設定がプッシュされている場合、auditpol.exe のすべての変更は上書きされます (たとえアプリケーション生成が構成されていなくても)。 その場合は、ローカル セキュリティ ポリシーを "生成されたアプリケーション" の失敗と成功を記録するように設定してください。

#### エージェント認定資格証は有効期限が切れる前に自動更新されますか?

エージェント認定は、有効期限 **6 か月** 前に自動的に更新されます。 更新されない場合は、エージェントのネットワーク接続が安定していることを確認してください。 エージェントのサービスを再起動する、または最新バージョンを更新してもこの問題を解決できる場合があります。

#### Microsoft Entra Connect Health エージェントを実行しているサーバーから PKS 証明書を削除しても安全ですか?

はい。 PKS 証明書は、Microsoft Entra Connect Health エージェントによって使用されなくなり、安全に削除できます。

エージェントのレガシ バージョンでは、実行したサーバーに基づいて名前が付けられた証明書がインストールされています。 現在のバージョンのエージェントではこれらの証明書は使用されませんが、アップグレード後は残ります。 証明書はローカル証明書ストアに残るため、有効期限の監視ツールは有効期限に近づくと誤ったアラートを生成する可能性があります。

エージェント サーバー上のローカル証明書ストアからこれらの証明書を安全に削除して、誤ったアラートを停止できます。 削除しても、エージェントの操作には影響しません。

### 関連リンク

- [Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect)
- [Microsoft Entra Connect Health エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install)
- [Microsoft Entra Connect Health の操作](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations)
- [AD FS での Microsoft Entra Connect Health の使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs)
- [Microsoft Entra Connect Health for Sync の使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync)
- [AD DS での Microsoft Entra Connect Health の使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adds)
- [Microsoft Entra Connect Health のバージョン履歴](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-health-version-history)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-health-user-privacy"} -->
## Microsoft Entra Connect Health とユーザー プライバシー - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-health-user-privacy
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Health を使ったユーザー プライバシーとデータ収集について説明します。

この記事では、Microsoft Entra Connect Health とユーザー プライバシーについて説明します。 Microsoft Entra Connect とユーザー プライバシーについては、「[ユーザー プライバシーと Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-user-privacy)」を参照してください。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

### ユーザー プライバシーの分類

Microsoft Entra Connect Health は、GDPR の*データ プロセッサ* カテゴリに分類されます。 このサービスは、データ プロセッサ パイプラインとして、重要なパートナーやエンド コンシューマーにデータ処理サービスを提供するものです。 Microsoft Entra Connect Health は、ユーザー データを生成するものではなく、どのような個人データが収集されるかや、それらのデータの用途について、独立した制御を持つものでもありません。 Microsoft Entra Connect Health でのデータの取得、集計、分析、およびレポートは、既存のオンプレミス データに基づいて行われます。

### データ リテンション期間ポリシー

Microsoft Entra Connect Health は、過去 30 日より前のレポートの生成、分析の実行、または分析情報の提供を行いません。 そのため、Microsoft Entra Connect Health は、過去 30 日より前のデータを格納、処理、または保持しません。 この設計は、GDPR の規制、Microsoft のプライバシー コンプライアンス規則、および Microsoft Entra のデータ保持ポリシーに準拠したものです。

サーバーの "**Health サービス データが最新ではありません**" エラー アラートが連続 30 日を超えてアクティブな場合、その期間に Connect Health にデータが届いていないことを示しています。 このようなサーバーは無効になり、Connect Health ポータルに表示されなくなります。 サーバーを再度有効にするには、[正常性エージェントをアンインストールし、再インストールする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install)必要があります。 これは、同じアラートの種類の "アラート" には適用されません。 警告は、一部のデータがアラート対象のサーバーに見つからないことを示します。

### データ収集と監視を無効にする

Microsoft Entra Connect Health を使うと、特定の監視対象サーバーや、監視対象サービスのインスタンスについて、データ収集を停止することができます。 たとえば、Microsoft Entra Connect Health を使用して監視されている個々の Active Directory フェデレーション サービス (AD FS) サーバーについて、データ収集を停止することもできます。 また、Microsoft Entra Connect Health を使用して監視されている AD FS インスタンス全体について、データ収集を停止することもできます。 特定の監視対象サーバーについてデータ収集の停止を選んだ場合、データ収集が停止した後、そのサーバーは Microsoft Entra Connect Health ポータルから削除されます。

重要

Microsoft Entra Connect Health から監視対象サーバーを削除するには、Microsoft Entra 全体管理者アカウントのアクセス許可、または Azure ロールベースのアクセス制御での共同作成者ロールのいずれかを持っている必要があります。

Microsoft Entra Connect Health からサーバーまたはサービス インスタンスを削除した場合、その操作を元に戻すことは "できません"。

#### ウィザードの内容

個々の監視対象サーバー、または監視対象サービスのインスタンスについてデータの収集と監視を停止する場合には、次の結果を想定できます。

- 監視対象サービスのインスタンスを削除すると、そのインスタンスは、ポータルにある Microsoft Entra Connect Health 監視サービスの一覧から削除されます。
- 監視対象サーバー、または監視対象サービスのインスタンスを削除しても、正常性エージェントがサーバーからアンインストールされたり、削除されたりすることは "ありません"。 そうではなく、Microsoft Entra Connect Health にデータを送信しないように正常性エージェントが構成されます。 以前に監視対象だったサーバー上の正常性エージェントは手動でアンインストールする必要があります。
- 監視対象サーバー、または監視対象サービスのインスタンスを削除する前に正常性エージェントをアンインストールしない場合、サーバー上の正常性エージェントに関連するエラー イベントが表示されることがあります。
- 監視対象サービスのインスタンスに属するすべてのデータは、Microsoft Azure のデータ リテンション期間ポリシーに従って削除されます。

#### 監視対象サーバーのデータ収集と監視を無効にする

「[Microsoft Entra Connect Health サービスからサーバーを削除するには](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations#delete-a-server-from-the-azure-ad-connect-health-service)」をご覧ください。

#### 監視対象サービスのインスタンスのデータ収集と監視を無効にする

「[Microsoft Entra Connect Health サービスからのサービス インスタンスの削除](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations#delete-a-service-instance-from-azure-ad-connect-health-service)」をご覧ください。

#### すべての監視対象サービスのデータ収集と監視を無効にする

Microsoft Entra Connect Health では、テナントの "すべて" の登録済みサービスのデータ収集を停止することができます。 このアクションを実行する前に、慎重な検討を行い、すべてのグローバル管理者の完全な了承を得ることをお勧めします。 このプロセスが始まると、Microsoft Entra Connect Health サービスは、全サービスのすべてのデータの受信、処理、報告を停止します。 Microsoft Entra Connect Health サービスの既存のデータは 30 日を超えない範囲で保持されます。

特定のサーバー上でデータ収集を停止する場合は、特定のサーバーを削除する手順を実行します。 テナントのデータ収集を停止するには、以下の手順を実行してデータ収集を停止し、そのテナントのすべてのサービスを削除します。

1. メイン メニューの **[構成]** の下にある **[全般設定]** を選びます。
2. コマンド バーで **[データ収集の停止]** を選びます。 プロセスが始まると、テナント設定を構成する他のオプションは無効になります。

    [Image: ポータルのデータ収集を停止するコマンドを示すスクリーンショット。]
3. データ収集の停止による影響を受けるオンボード サービスの一覧を確認します。
4. 正確なテナント名を入力し、**[削除]** ボタンを有効にします。
5. **[削除]** を選んで、すべてのサービスの削除を開始します。 Microsoft Entra Connect Health は、オンボード サービスから送信されたすべてのデータの受信、処理、レポートを停止します。 プロセス全体で最大 24 時間かかることがあります。 *この手順は元に戻せません*。

このプロセスが完了すると、Microsoft Entra Connect Health に登録されたサービスは表示されなくなります。

[Image: データ収集の停止後に表示されるメッセージを示すスクリーンショット。]

### データ収集と監視を再度有効にする

以前に削除された監視対象サービスについて Microsoft Entra Connect Health での監視を再度有効にするには、正常性エージェントをアンインストールし、すべてのサーバーに[正常性エージェントを再インストールする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install)必要があります。

#### すべての監視対象サービスのデータ収集と監視を再度有効にする

Microsoft Entra Connect Health でテナントのデータ収集を再開することができます。 このアクションを実行する前に、慎重な検討を行い、すべてのグローバル管理者の完全な了承を得ることをお勧めします。

重要

以下の手順は、無効化アクションの 24 時間後から使用できます。 データ収集を有効にした後、Microsoft Entra Connect Health に表示される分析情報と監視データには、無効化アクションの前に収集されたデータは表示されません。

1. メイン メニューの **[構成]** の下にある **[全般設定]** を選びます。
2. コマンド バーで **[データ収集の有効化]** を選びます。

    [Image: ポータルの [データ収集の有効化] コマンドを示すスクリーンショット。]
3. 正確なテナント名を入力し、 **[有効化]** ボタンをアクティブにします。
4. **[有効にする]** を選んで、Microsoft Entra Connect Health サービスでデータ収集のアクセス許可を付与します。 変更はすぐに適用されます。
5. [インストール プロセス](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install)に従い、監視対象のサーバーにエージェントを再インストールします。 ポータルにサービスが表示されるようになります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-health-version-history"} -->
## Microsoft Entra Connect Health のバージョン履歴 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-health-version-history
- Service: entra-id / hybrid-connect
- Article date: 2026-02-25
- Summary: このドキュメントでは、Microsoft Entra Connect Health のリリースと、それらのリリースに含まれる内容について説明します。

Microsoft Entra チームは、Microsoft Entra Connect Health を定期的に更新し、新機能を提供しています。 この記事では、リリースされたバージョンと機能の一覧を示します。

注

Microsoft Entra Connect Health エージェントは、新しいバージョンがリリースされると自動的に更新されます。

Microsoft Entra Connect Health for Sync は、Microsoft Entra Connect インストールと統合されています。 [Microsoft Entra Connect のリリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)の詳細を確認してください。

機能に関するフィードバックについては、[Connect Health User Voice チャンネル](https://feedback.azure.com/d365community/forum/22920db1-ad25-ec11-b6e6-000d3a4f0789)で投票してください

### 2026 年 8 月

**エージェントの更新プログラム**

Microsoft Entra Connect Health (バージョン 4.5.2614.0)

- エージェント資格情報のセキュリティとキーローテーションのサポートが強化されました。
- クラウドの互換性とテレメトリアップロードの回復性が向上しました。
- インストール、登録、信頼性、品質の向上。

### 2026 年 2 月

**エージェントの更新プログラム**

Microsoft Entra Connect Health (バージョン 4.5.2549.0)

- セキュリティ更新プログラム: TLS 1.0 と TLS 1.1 のサポートは廃止されました。 このサービスでは、TLS 1.2 のみがサポートされるようになりました。
- 品質の向上。

### 2025 年 9 月

**エージェントの更新プログラム**

Microsoft Entra Connect Health (バージョン 4.5.2528.0)

- 米国政府クラウド ユーザー向けの既定の US Government クラウドにインストーラーを更新しました
- 品質の向上とバグ修正

### 2025 年 3 月

**エージェントの更新プログラム**

Microsoft Entra Connect Health (バージョン 4.5.2520.0)

- データ アップロード サイズ制限の問題に対処する
- 品質の向上

### 2024 年 5 月

**エージェントの更新プログラム**

Microsoft Entra Connect Health (バージョン 4.5.2487.0)

- プロキシ サーバーの構成を指定できるようにインストーラーを更新しました
- インストーラーの既定を、米国政府のクラウドを指定するオプションを持つパブリック クラウドに更新しました
- コマンド ラインからのインストールのサポートを追加しました
- バグ修正

### 2024 年 1 月から 3 月

**エージェントの更新プログラム**

Microsoft Entra Connect Health ADDS エージェント (バージョン 4.5.x - 4.5.2462)

- 更新されたアーキテクチャを使用する、新しいバージョンの Microsoft Entra Connect Health ADDS エージェント。
    - 更新されたインストーラー パッケージ
    - MSAL 認証ライブラリへの移行
    - 新しい前提条件の確認
    - ログ記録の強化

Microsoft Entra Connect Health ADFS エージェント (バージョン 4.5.x - 4.5.2462)

- バグ修正
- 追加ログ

### 2023 年 5 月/6 月

**エージェントの更新プログラム**

Microsoft Entra Connect Health ADFS エージェント (バージョン 4.5.x)

- 更新されたアーキテクチャを使用する、新しいバージョンの Microsoft Entra Connect Health ADFS エージェント。
    - 更新されたインストーラー パッケージ
    - MSAL 認証ライブラリへの移行
    - 新しい前提条件の確認
    - ログ記録の強化

### 2023 年 3 月 27 日

**エージェントの更新**

Microsoft Entra Connect Health AD DS および ADFS Health エージェント (バージョン 3.2.2256.26、ダウンロード センターのみ)

- それに対して修正プログラムを作成して、エージェントが FIPS に準拠するようにしました
    - この変更は、エージェントに 'CloudStorageAccount.UseV1MD5 = false' を使用してもらうため、エージェントは FIPS 準拠の暗号のみを使用します。それ以外の場合は Azure BLOB クライアントによって FIP 例外がスローされます。
- コンポーネント ガバナンス アラートを解決するために、Newtonsoft.json ライブラリを 12.0.1 から 13.0.1 に更新しました。
- ADFS 正常性エージェントでは、テストが信頼性が低かったため、TestADFSDuplicateSPN テストが無効になっており、サーバーで一時的な接続性イシューが発生したときに誤解を招くようなアラートが生成されます。

### 2023 年 1 月 19 日

**エージェントの更新**

- Microsoft Entra DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.2.2188.23)
    - 特定の状況で、Microsoft Entra Connect 同期エラーがアップロードされない、またはポータルに表示されないバグを修正しました。

### 2021 年 9 月

**エージェントの更新**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.113.0)
    - 特定のデバイス ベースの認証シナリオにおいて、AD FS 監査からデバイスのコンプライアンスや管理状態、デバイスの OS、デバイスの OS バージョンなどのデバイス情報を抽出するよう修正。
    - エラーが発生した場合に OAuth アプリケーション情報を入力し、OAuth のエラーをより具体的なエラー コードで分類するよう修正
    - お客様のマシンで発生した WMI 呼び出しの破損に関するアラートを修正。 このような呼び出しでは、結果または状態が "notRun "に設定されます。

### 2021 年 5 月

**エージェントの更新**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.99.0)
    - AD FS アプリケーション アクティビティ レポートで一意のユーザー数の値が低いことの修正
    - 空または既定の GUID CorrelationId を使用したサインインの修正

### 2021 年 3 月

**エージェントの更新**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.95.0)

    - サインイン イベント時に NT4 形式のユーザー名が UPN に解決されるように修正しました。
    - 専用のエラー コードで不適切なアプリケーション識別子シナリオが示されるように修正しました。
    - OAuth クライアント識別子の新しいプロパティを追加するために変更を加えました。
    - 特定のサインイン シナリオに関する Microsoft Entra サインイン レポートの **[プロトコル]** および **[認証の種類]** フィールドに正しい値が表示されるように修正しました。
    - Microsoft Entra サインイン レポートの [IP チェーン] フィールドに IP アドレスが要求の順序で表示されるように修正しました。
    - サインイン時にセカンダリ認証が要求されたかどうかを区別する新しいフィールドを導入するために変更を加えました。
    - Microsoft Entra サインイン レポートに AD FS アプリケーション識別子のプロパティが表示されるように修正しました。

### 2020 年 4 月

**エージェントの更新**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.77.0)

    - アラートが誤って報告されていた "AD FS サービスのサービス プリンシパル名 (SPN) が無効" アラートのバグを修正しました。

### 2019 年 7 月

**エージェントの更新**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.59.0)

    1. TestWindowsTransport でのテキスト変更
    2. AD FS RP アップロードに関する変更
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.56.0)

    1. CheckOffice365Endpoints テストでの TestWindowsTransport のテストの追加と WsTrust エンドポイントのチェックの削除
    2. OS と .NET に関する情報のログ記録
    3. RP 構成メッセージのアップロード サイズの 1MB への増加。
    4. バグ修正
- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.56.0)

    1. OS と .NET に関する情報のログ記録
    2. バグ修正

### 2019 年 5 月

**エージェントの更新:**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.51.0)
    1. 同じ client-request-id を共有する複数のサインインを区別するためのバグ修正。
    2. 言語がローカライズされたサーバーで不適切なユーザー名/パスワードのエラーを解析するためのバグ修正。

### 2019 年 4 月

**エージェントの更新:**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.46.0)
    1. ADFS の重複する SPN アラート プロセスの修正確認

### 2019 年 3 月

**エージェントの更新:**

- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.41.0)

    1. .NET バージョンのコレクション
    2. 特定のカテゴリが不足している場合のパフォーマンス カウンター収集の機能強化。
    3. 複数の Monitoring Agent インスタンスの生成を防ぐためのバグ修正。
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.41.0)

    1. ADFSToolBox を使用した AD FS テスト スクリプトの統合およびアップグレード。
    2. .NET バージョンのコレクションの実装。
    3. 特定のカテゴリが不足している場合のパフォーマンス カウンター収集の機能強化。
    4. 複数の Monitoring Agent インスタンスの生成を防ぐためのバグ修正。

### 2018 年 11 月

**新しい GA 機能:**

- Microsoft Entra Connect Health for Sync - ポータルから重複する属性の同期エラーを診断して修復します。

**エージェントの更新:**

- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.24.0)

    1. トランスポート層セキュリティ (TLS) プロトコル バージョン 1.2 の対応と適用
    2. グローバル カタログのアラート ノイズの削減
    3. 正常性エージェント登録のバグの修正
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.24.0)

    1. トランスポート層セキュリティ (TLS) プロトコル バージョン 1.2 の対応と適用
    2. ローカライズされたオペレーティング システムに対する Test-ADFSRequestToken のサポート
    3. 診断エージェント EventHandler のロックの問題の解決
    4. 正常性エージェント登録のバグの修正

### 2018 年 8 月

- Microsoft Entra Connect バージョン 1.1.880.0 と共にリリースされた同期用の Microsoft Entra Connect Health エージェント (バージョン 3.1.7.0)
    1. [.NET Framework KB リリースを使用してエージェントを監視する場合に CPU が高くなる問題](https://support.microsoft.com/help/4346822/high-cpu-issue-in-azure-active-directory-connect-health-for-sync)の修正プログラム

### 2018 年 6 月

**新しいプレビュー機能:**

- Microsoft Entra Connect Health for Sync - ポータルから重複する属性の同期エラーを診断して修復します。

**エージェントの更新:**

- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.7.0)

    1. [.NET Framework KB リリースを使用してエージェントを監視する場合に CPU が高くなる問題](https://support.microsoft.com/help/4346822/high-cpu-issue-in-azure-active-directory-connect-health-for-sync)の修正プログラム
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.7.0)

    1. [.NET Framework KB リリースを使用してエージェントを監視する場合に CPU が高くなる問題](https://support.microsoft.com/help/4346822/high-cpu-issue-in-azure-active-directory-connect-health-for-sync)の修正プログラム
    2. ADFS Server 2016 セカンダリ サーバーのテスト結果の修正
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.1.2.0)

    1. バージョン 3.0.244.0 のエージェントのメモリ管理の修正プログラムと関連する警告

### 2018 年 5 月

**エージェントの更新:**

- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.244.0)

    1. エージェントのプライバシーの向上
    2. バグの修正と一般的な機能強化
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.244.0)

    1. エージェントの診断サービスと関連する PowerShell モジュールの機能強化
    2. エージェントのプライバシーの向上
    3. バグの修正と一般的な機能強化
- Microsoft Entra Connect バージョン 1.1.819.0 と共にリリースされた同期用の Microsoft Entra Connect Health エージェント (バージョン 3.0.164.0)

    1. エージェントのプライバシーの向上
    2. バグの修正と一般的な機能強化

### 2018 年 3 月

**新しいプレビュー機能:**

- AD FS 用の Microsoft Entra Connect Health - 危険な IP に関するレポートおよびアラート。

**エージェントの更新:**

- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.176.0)
    1. エージェントの可用性の向上
    2. バグの修正と一般的な機能強化
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.176.0)
    1. エージェントの可用性の向上
    2. バグの修正と一般的な機能強化
- Microsoft Entra Connect バージョン 1.1.750.0 と共にリリースされた同期用の Microsoft Entra Connect Health エージェント (バージョン 3.0.129.0)
    1. エージェントの可用性の向上
    2. バグの修正と一般的な機能強化

### 2017 年 12 月

**エージェントの更新:**

- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.145.0)
    1. エージェントの可用性の向上
    2. 新しいエージェントのトラブルシューティング コマンドの追加
    3. バグの修正と一般的な機能強化
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.145.0)
    1. 新しいエージェントのトラブルシューティング コマンドの追加
    2. エージェントの可用性の向上
    3. バグの修正と一般的な機能強化

### 2017 年 10 月

**エージェントの更新:**

- Microsoft Entra Connect バージョン 1.1.649.0 と共にリリースされた同期用の Microsoft Entra Connect Health エージェント (バージョン 3.0.129.0)  Microsoft Entra Connect と同期用の Microsoft Entra Connect Health エージェント間の、バージョンの互換性に関する問題が修正されました。この問題は、Microsoft Entra Connect のバージョン 1.1.647.0 へのインプレース アップグレードを実行していて、現在の Health エージェントのバージョンが 3.0.127.0 である場合に影響があります。 アップグレード後に、Health エージェントは Microsoft Entra Connect 同期サービスについての正常性データを Microsoft Entra Health サービスに送信できなくなります。 この修正により、Microsoft Entra Connect のインプレース アップグレード中に Health エージェントのバージョン 3.0.129.0 がインストールされます。 Health エージェントのバージョン 3.0.129.0 には、Microsoft Entra Connect バージョン 1.1.649.0 との互換性の問題はありません。

### 2017 年 7 月

**エージェントの更新:**

- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.68.0)
    1. バグの修正と一般的な機能強化
    2. ソブリン クラウドのサポート
- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.68.0)
    1. バグの修正と一般的な機能強化
    2. ソブリン クラウドのサポート
- Microsoft Entra Connect バージョン 1.1.614.0 と共にリリースされた同期用の Microsoft Entra Connect Health エージェント (バージョン 3.0.68.0)
    1. Microsoft Azure Government Cloud と Microsoft Cloud Germany のサポート

### 2017 年 4 月

**エージェントの更新:**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.12.0)
    1. バグの修正と一般的な機能強化
- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 3.0.12.0)
    1. パフォーマンス カウンター アップロードの強化
    2. バグの修正と一般的な機能強化

### 2016 年 10 月

**エージェントの更新:**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 2.6.408.0)
- 認証要求でクライアント IP アドレスを検出する機能の強化
- アラートに関連するバグの修正
- AD DS 用の Microsoft Entra Connect Health エージェント (バージョン 2.6.408.0)
- アラートに関連するバグの修正。
- Microsoft Entra Connect バージョン 1.1.281.0 と共にリリースされた同期用の Microsoft Entra Connect Health エージェント (バージョン 2.6.353.0)
- 同期エラー レポートに必要なデータを提供
- アラートに関連するバグの修正

**新しいプレビュー機能:**

- Microsoft Entra Connect の同期エラー レポート

**新機能:**

- AD FS 用の Microsoft Entra Connect Health - ユーザー名/パスワードが正しくない上位 50 名のユーザーについてのレポートで、[IP アドレス] フィールドを使用できます。

### 2016 年 7 月

**新しいプレビュー機能:**

- [AD DS 用の Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adds)。

### 2016 年 1 月

**エージェントの更新:**

- AD FS 用の Microsoft Entra Connect Health エージェント (バージョン 2.6.91.1512)

**新機能:**

- [Microsoft Entra Connect Health エージェント用のテスト接続ツール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install#test-connectivity-to-azure-ad-connect-health-service)

### 2015 年 11 月

**新機能:**

- [Azure ロールベースのアクセス制御 (Azure RBAC)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-operations#manage-access-with-azure-rbac) のサポート

**新しいプレビュー機能:**

- [同期用の Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync)。

**修正された問題:**

- エージェントの登録中に検出されたエラーのバグの修正。

### 2015 年 9 月

**新機能:**

- AD FS の不正なユーザー名パスワード レポート
- 非認証 HTTP プロキシの構成のサポート
- Server コア上のエージェントの構成のサポート
- AD FS のアラートの改善
- AD FS 用の Microsoft Entra Connect Health エージェントの接続性とデータ アップロードの改善。

**修正された問題:**

- AD FS のエラーの種類に対する利用状況インサイトのバグ修正。

### 2015 年 6 月

**AD FS 用の Microsoft Entra Connect Health および AD FS プロキシの初期リリース。**

**新機能:**

- 電子メール通知による AD FS および AD FS プロキシ サーバーの監視のアラート。
- AD FS パフォーマンス カウンターでの AD FS トポロジおよびパターンへの簡単なアクセス。
- アプリケーション、認証方法、要求ネットワークの場所などによってグループ化された AD FS サーバーへの成功したトークン要求の傾向。
- アプリケーション、エラーの種類などによってグループ化された AD FS サーバーへの失敗した要求の傾向。
- Microsoft Entra 全体管理者の資格情報を使用した簡単なエージェントのデプロイメント。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-instances"} -->
## Microsoft Entra Connect: サービス インスタンスを同期する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-instances
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このページでは、Microsoft Entra インスタンスに関する特別な考慮事項について説明します。

Microsoft Entra Connect は、Microsoft Entra ID と Microsoft 365 の世界中のインスタンスで最もよく使用されます。 ただし、他のインスタンスもあり、URL の要件が異なり、その他の特別な考慮事項もあります。

### Microsoft Cloud Germany

[Microsoft Cloud Germany](https://www.microsoft.com/de-de/microsoft-cloud) は、ドイツのデータ トラスティが運営するソブリン クラウドです。

| プロキシ サーバーで開く URL |
| --- |
| \*.microsoftonline.de |
| \*.windows.net |
| +証明書失効リスト |

Microsoft Entra テナントにサインインするときは、onmicrosoft.de ドメイン内のアカウントを使用する必要があります。

現在、Microsoft Cloud Germany には存在しない機能:

- **パスワード ライトバック** は、Microsoft Entra Connect バージョン 1.1.570.0 以降でプレビューできます。
- その他の Microsoft Entra ID P1 または P2 サービスは利用できません。

### Microsoft Azure Government

Microsoft Azure Government クラウド  は、米国政府機関向けのクラウドです。

このクラウドは、DirSync の以前のリリースでサポートされています。 Microsoft Entra Connect のビルド 1.1.180 から、次世代のクラウドがサポートされています。 この世代では、米国専用のエンドポイントが使用されており、プロキシ サーバーで開く URL の一覧が異なります。

| プロキシ サーバーで開く URL |
| --- |
| \*.microsoftonline.com |
| \*.microsoftonline.us |
| \*.windows.net (Azure Government テナントの自動検出に必要) |
| \*.gov.us.microsoftonline.com |
| +証明書失効リスト |

手記

Microsoft Entra Connect バージョン 1.1.647.0 以降、\*.windows.net がプロキシ サーバーで開かれている場合、レジストリで AzureInstance 値を設定する必要はなくなりました。 ただし、Microsoft Entra Connect サーバーからのインターネット接続を許可していないお客様の場合は、次の手動構成を使用できます。

#### 手動構成

次の手動構成手順は、Microsoft Entra Connect が Azure Government 同期エンドポイントを確実に使用するために使用されます。

1. Microsoft Entra Connect のインストールを開始します。
2. EULA に同意する必要がある最初のページが表示されたら、続行せず、インストール ウィザードを実行したままにします。
3. regedit を起動し、レジストリ キーの `HKLM\SOFTWARE\Microsoft\Azure AD Connect\AzureInstance` を `4`値に変更します。
4. Microsoft Entra Connect インストール ウィザードに戻り、EULA に同意して続行します。 インストール中は、**カスタム構成** インストール パス (Express インストールではなく) を使用し、通常どおりインストールを続行してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-msexchuserholdpolicies"} -->
## Microsoft Entra Connect: msExchUserHoldPolicies と cloudMsExchUserHoldPolicies - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-msexchuserholdpolicies
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、msExchUserHoldPolicies 属性と cloudMsExchUserHoldPolicies 属性の属性の動作について説明します

次のリファレンス ドキュメントでは、Exchange で使用されるこれらの属性と、既定の同期ルールを適切に編集する方法について説明します。

### msExchUserHoldPolicies と cloudMsExchUserHoldPolicies とは何ですか?

Exchange Server で使用できる [保留](https://learn.microsoft.com/ja-jp/exchange/policy-and-compliance/holds/holds) には、訴訟ホールドと In-Place ホールドの 2 種類があります。 訴訟ホールドを有効にすると、すべてのメールボックスのすべてのアイテムが保留になります。 In-Place 保留は、In-Place 電子情報開示ツールを使用して定義した検索クエリの条件を満たすアイテムのみを保持するために使用されます。

MsExchUserHoldPolicies 属性と cloudMsExchUserHoldPolicies 属性を使用すると、オンプレミスの AD と Microsoft Entra ID は、オンプレミスの Exchange を使用しているか、オンラインで Exchange を使用しているかに応じて、どのユーザーが保留下にあるかを判断できます。

### msExchUserHoldPolicies 同期フロー

既定では、MsExchUserHoldPolicies は Microsoft Entra Connect によってメタバースの msExchUserHoldPolicies 属性に直接同期され、次に Microsoft Entra ID の msExchUserHoldPolicies 属性に同期されます

次の表では、フローについて説明します。

オンプレミスの Active Directory からの受信:

| Active Directory 属性 | 属性名 | フローの種類 | メタバース属性 | 同期ルール |
| --- | --- | --- | --- | --- |
| オンプレミスの Active Directory | msExchUserHoldPolicies | 直接 | msExchUserHoldPolicies | AD から - User Exchange |

Microsoft Entra ID への送信:

| メタバース属性 | 属性名 | フローの種類 | Microsoft Entra 属性 | 同期ルール |
| --- | --- | --- | --- | --- |
| マイクロソフト エントラ ID | msExchUserHoldPolicies | 直接 | msExchUserHoldPolicies | Microsoft Entra IDに発信 – UserExchangeOnline |

### cloudMsExchUserHoldPolicies 同期フロー

既定では、cloudMsExchUserHoldPolicies は、Microsoft Entra Connect によってメタバースの cloudMsExchUserHoldPolicies 属性に直接同期されます。 その後、メタバースで msExchUserHoldPolicies が null でない場合、属性は Active Directory に流れ出しました。

次の表では、フローについて説明します。

Microsoft Entra ID からの受信:

| Active Directory 属性 | 属性名 | フローの種類 | メタバース属性 | 同期ルール |
| --- | --- | --- | --- | --- |
| オンプレミスの Active Directory | cloudMsExchUserHoldPolicies (英語) | 直接 | cloudMsExchUserHoldPolicies (英語) | Microsoft Entra ID から入力 - User Exchange |

オンプレミスの Active Directory への送信:

| メタバース属性 | 属性名 | フローの種類 | Microsoft Entra 属性 | 同期ルール |
| --- | --- | --- | --- | --- |
| マイクロソフト エントラ ID | cloudMsExchUserHoldPolicies (英語) | IF(NULL でない) | msExchUserHoldPolicies | ADへの発信 – UserExchangeOnline |

### 属性の動作に関する情報

msExchangeUserHoldPolicies は 1 つの機関属性です。 単一の権限属性は、オンプレミスディレクトリまたはクラウドディレクトリ内のオブジェクト(この場合はユーザーオブジェクト)に設定できます。 Start of Authority ルールでは、属性がオンプレミスから同期されている場合、Microsoft Entra ID はこの属性を更新できないと規定されています。

ユーザーがクラウド内のユーザー オブジェクトに保留ポリシーを設定できるようにするには、cloudMSExchangeUserHoldPolicies 属性を使用します。 この属性が使用されるのは、Microsoft Entra ID では、上記で説明したルールに基づいて msExchangeUserHoldPolicies を直接設定できないためです。 この属性は、msExchangeUserHoldPolicies が null でない場合、オンプレミスのディレクトリに同期し、msExchangeUserHoldPolicies の現在の値を置き換えます。

特定の状況下では、たとえば、オンプレミスと Azure の両方で同時に変更された場合、問題が発生する可能性があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-ports"} -->
## ハイブリッド ID に必要なポートとプロトコル - Azure - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-ports
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このページは、Microsoft Entra Connect で開く必要があるポートのテクニカル リファレンス ページです

次のドキュメントは、ハイブリッド ID ソリューションを実装するために必要なポートとプロトコルに関するテクニカル リファレンスです。 次の図を使用して、対応する表を参照してください。

[Image: Microsoft Entra Connect とは]

### 表 1 - Microsoft Entra Connect とオンプレミス AD

次の表では、Microsoft Entra Connect サーバーとオンプレミス AD の間の通信に必要なポートとプロトコルについて説明します。

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| DNS | 53 (TCP/UDP) | 宛先フォレストでの DNS 参照。 |
| Kerberos | 88 (TCP/UDP) | AD フォレストに対する Kerberos 認証。 |
| MS-RPC | 135 (TCP) | Microsoft Entra Connect ウィザードの初期構成時に、AD フォレストにバインドするとき、およびパスワード同期中に使用されます。 |
| LDAP | 389 (TCP/UDP) | AD からのデータインポートに使用されます。 データは Kerberos の署名とシールで暗号化されます。 |
| SMB | 445 (TCP) | シームレス SSO によって、AD フォレスト内およびパスワード ライトバック中にコンピューター アカウントを作成するために使用されます。 詳細については、「 [ユーザー アカウントのパスワードを変更する](https://learn.microsoft.com/ja-jp/openspecs/windows_protocols/ms-adod/d211aaba-d188-4836-8007-8c62f7c9402d)」を参照してください。 |
| LDAP/SSL | 636 (TCP/UDP) | AD からのデータインポートに使用されます。 データ転送は署名され、暗号化されます。 TLS を使用している場合にのみ使用されます。 |
| RPC | 49152 - 65535 (ランダム高 RPC ポート) (TCP) | Ad フォレストにバインドするとき、およびパスワード同期中に、Microsoft Entra Connect の初期構成中に使用されます。 動的ポートが変更されている場合は、そのポートを開く必要があります。 詳細については、「 [KB929851](https://support.microsoft.com/kb/929851)、 [KB832017](https://support.microsoft.com/kb/832017)、 [およびKB224196](https://support.microsoft.com/kb/224196) 」を参照してください。 |
| WinRM | 5985 (TCP) | Microsoft Entra Connect ウィザードで gMSA を使用して AD FS をインストールする場合にのみ使用します。 |
| AD DS Web サービス | 9389 (TCP) | Microsoft Entra Connect ウィザードで gMSA を使用して AD FS をインストールする場合にのみ使用します。 |
| グローバル カタログ | 3268 (TCP) | シームレス SSO によって、ドメインにコンピューター アカウントを作成する前に、フォレスト内のグローバル カタログに対してクエリを実行するために使用されます。 |

### 表 2 - Microsoft Entra Connect と Microsoft Entra ID

次の表では、Microsoft Entra Connect サーバーと Microsoft Entra ID の間の通信に必要なポートとプロトコルについて説明します。

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| HTTP | 80 (TCP) | TLS/SSL 証明書を確認するために CRL (証明書失効リスト) をダウンロードするために使用されます。 |
| HTTPS | 443 (TCP) | Microsoft Entra ID と同期するために使用されます。 |

ファイアウォールで開く必要がある URL と IP アドレスの一覧については、「[Office 365 URL と IP アドレス範囲」および](https://support.office.com/article/Office-365-URLs-and-IP-address-ranges-8548a211-3fe7-47cb-abb1-355ea5aa88a2)[「Microsoft Entra Connect 接続のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-connectivity#connectivity-issues-in-the-installation-wizard)」を参照してください。

### 表 3 - Microsoft Entra Connect と AD FS フェデレーション サーバー/WAP

次の表では、Microsoft Entra Connect サーバーと AD FS フェデレーション/WAP サーバー間の通信に必要なポートとプロトコルについて説明します。

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| HTTP | 80 (TCP) | TLS/SSL 証明書を確認するために CRL (証明書失効リスト) をダウンロードするために使用されます。 |
| HTTPS | 443 (TCP) | Microsoft Entra ID と同期するために使用されます。 |
| WinRM | 5985 | WinRM リスナー |

### 表 4 - WAP およびフェデレーション サーバー

次の表では、フェデレーション サーバーと WAP サーバー間の通信に必要なポートとプロトコルについて説明します。

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| HTTPS | 443 (TCP) | 認証に使用されます。 |

### 表 5 - WAP とユーザー

次の表では、ユーザーと WAP サーバー間の通信に必要なポートとプロトコルについて説明します。

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| HTTPS | 443 (TCP) | デバイス認証に使用されます。 |
| TCP | 49443 (TCP) | 証明書認証に使用されます。 |

### 表 6a & 6b - シングル サインオン (SSO) によるパススルー認証とシングル サインオン (SSO) によるパスワード ハッシュ同期

次の表では、Microsoft Entra Connect と Microsoft Entra ID の間の通信に必要なポートとプロトコルについて説明します。

#### 表 6a - SSO を使用したパススルー認証

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| HTTP | 80 (TCP) | TLS/SSL 証明書を確認するために CRL (証明書失効リスト) をダウンロードするために使用されます。 コネクタの自動更新機能が正常に機能するためにも必要です。 |
| HTTPS | 443 (TCP) | 機能の有効化と無効化、コネクタの登録、コネクタの更新プログラムのダウンロード、およびすべてのユーザー サインイン要求の処理に使用されます。 |

さらに、Microsoft Entra Connect は [、Azure データ センター](https://www.microsoft.com/download/details.aspx?id=41653)の IP 範囲に直接 IP 接続できる必要があります。

#### 表 6b - SSO を使用したパスワード ハッシュ同期

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| HTTPS | 443 (TCP) | SSO 登録を有効にするために使用されます (SSO 登録プロセスにのみ必要)。 |

さらに、Microsoft Entra Connect は [、Azure データ センター](https://www.microsoft.com/download/details.aspx?id=41653)の IP 範囲に直接 IP 接続できる必要があります。 ここでも、これは SSO 登録プロセスにのみ必要です。

### 表 7a および 7b - Microsoft Entra Connect Health エージェント (AD FS/Sync) と Microsoft Entra ID

次の表では、Microsoft Entra Connect Health エージェントと Microsoft Entra ID の間の通信に必要なエンドポイント、ポート、プロトコルについて説明します

#### 表 7a - Microsoft Entra Connect Health エージェント (AD FS/Sync) および Microsoft Entra ID のポートとプロトコル

この表では、Microsoft Entra Connect Health エージェントと Microsoft Entra ID の間の通信に必要な次の送信ポートとプロトコルについて説明します。

| プロトコル | ポート | 説明 |
| --- | --- | --- |
| Azure Service Bus | 5671 (TCP) | Microsoft Entra ID に正常性情報を送信するために使用されます。 (推奨されますが、最新バージョンでは必須ではありません) |
| HTTPS | 443 (TCP) | Microsoft Entra ID に正常性情報を送信するために使用されます。 (フェールバック) |

5671 がブロックされている場合、エージェントは 443 にフォールバックしますが、5671 を使用することをお勧めします。 このエンドポイントは、最新バージョンのエージェントでは必要ありません。 Microsoft Entra Connect Health エージェントの最新バージョンでは、ポート 443 のみが必要です。

#### 7b - Microsoft Entra Connect Health エージェントのエンドポイント (AD FS/Sync) と Microsoft Entra ID

エンドポイントの一覧については、「[Microsoft Entra Connect Health エージェントの前提条件」セクション](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install#prerequisites)を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-pta-version-history"} -->
## Microsoft Entra パススルー認証: バージョンリリース履歴 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-pta-version-history
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra パススルー認証エージェントのすべてのリリースの一覧を示します

パススルー認証を有効にするオンプレミスにインストールされているエージェントは、新しい機能を提供するために定期的に更新されます。 この記事では、新しい機能が導入されたときに追加されるバージョンと機能の一覧を示します。 パススルー認証エージェントは、新しいバージョンがリリースされると自動的に更新されます。

関連トピックを次に示します。

- [Microsoft Entra パススルー認証を使用したユーザー サインイン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta)
- [Microsoft Entra パススルー認証エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start)

### 1.5.2482.0

#### リリースの状態:

2021 年 7 月 7 日: ダウンロード用にリリース

#### 新機能と機能強化

- SHA-256RSA を使用して署名された新しいバージョンにパッケージ/ライブラリをアップグレードしました。

### 1.5.1742.0

#### リリースの状態:

2020 年 4 月 9 日ダウンロード対象としてリリース済み

#### 新機能と機能強化

- インストール時にクラウド環境をターゲットにするためのサポートを追加しました。 バンドルは、特定のクラウド環境に固定できます。

### 1.5.1007.0

#### リリース状態

2019 年 1 月 22 日: ダウンロード用にリリース

#### 新機能と機能強化

- Service Bus の信頼できるチャネルのサポートを追加して、送信接続に対する接続の回復性の別の層を追加しました
- エージェントの登録中に TLS 1.2 を適用する

### 1.5.643.0

#### リリース状態

2018 年 4 月 10 日: ダウンロード用にリリース

#### 新機能と機能強化

- Web ソケット接続のサポート
- TLS 1.2 をエージェントの既定のプロトコルとして設定する

### 1.5.405.0

#### リリース状態

2018 年 1 月 31 日: ダウンロード用にリリース

#### 修正された問題

- エージェントでメモリ リークが発生する原因となったバグを修正しました。
- Azure Service Bus のバージョンを更新しました。これには、コネクタのタイムアウトの問題に関するバグ修正が含まれています。

#### 新機能と機能強化

- 接続の回復性を向上させるために、エージェントと Microsoft Entra サービス間の Websocket ベースの接続のサポートを追加しました

### 1.5.402.0

#### リリース状態

2017 年 11 月 25 日: ダウンロード用にリリース

#### 修正された問題

- 既定のプロキシ シナリオの DNS キャッシュに関連するバグを修正しました

### 1.5.389.0

#### リリース状態

2017 年 10 月 17 日: ダウンロード用にリリース

#### 新機能と機能強化

- 送信接続用の DNS キャッシュ機能を追加し、DNS エラーからの回復性を追加しました

### 1.5.261.0

#### リリース状態

2017 年 8 月 31 日: ダウンロード用にリリース

#### 新機能と機能強化

- Microsoft Entra パススルー認証エージェントの GA バージョン
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized"} -->
## Microsoft Entra Connect によって同期される属性 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-attributes-synchronized
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra ID に同期される属性を一覧表示します。

このトピックでは、Microsoft Entra Connect Sync によって同期される属性の一覧を示します。 属性は、関連する Microsoft Entra アプリによってグループ化されます。

### 同期する属性

一般的な質問は、*を同期する最小属性の一覧*です。 既定の推奨される方法は、完全な GAL (グローバル アドレス一覧) をクラウドで構築できるように既定の属性を保持し、Microsoft 365 ワークロードのすべての機能を取得することです。 場合によっては、次の例のように、機密性の高い個人データが含まれるため、組織がクラウドに同期したくない属性がいくつかあります。[Image: 無効な属性]

この場合は、このトピックの属性の一覧から始めて、個人データを含み、同期できない属性を特定します。 次に、[Microsoft Entra アプリと属性フィルター](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#azure-ad-app-and-attribute-filtering) を使用して、インストール中にこれらの属性の選択を解除します。

警告

属性の選択を解除する場合は注意が必要であり、それらの属性の選択を解除するだけでは同期できません。 他の属性の選択を解除すると、特徴に悪影響を及ぼす可能性があります。

### エンタープライズ向け Microsoft 365 Apps

| 属性名 | ユーザー | コメント |
| --- | --- | --- |
| accountEnabled | X | アカウントが有効かどうかを定義します。 |
| cn | X |  |
| displayName | X |  |
| objectSID | X | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| pwdLastSet | X | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 パスワード ハッシュ同期、パススルー認証、フェデレーションの両方で使用されます。 |
| samAccountName | X |  |
| sourceAnchor | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| usageLocation | X | 機械的特性。 ユーザーの国/地域。 ライセンスの割り当てに使用されます。 |
| userPrincipalName | X | UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |

### Exchange Online

| 属性名 | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- |
| accountEnabled | X |  |  | アカウントが有効かどうかを定義します。 |
| altRecipient | X |  |  | Microsoft Entra Connect ビルド 1.1.552.0 以降が必要です。 |
| authOrig | X | X | X |  |
| c | X | X |  |  |
| cn | X |  | X |  |
| co | X | X |  |  |
| company | X | X |  |  |
| countryCode | X | X |  |  |
| department | X | X |  |  |
| description |  |  | X |  |
| displayName | X | X | X |  |
| dLMemRejectPerms | X | X | X |  |
| dLMemSubmitPerms | X | X | X |  |
| extensionAttribute1 | X | X | X |  |
| extensionAttribute10 | X | X | X |  |
| extensionAttribute11 | X | X | X |  |
| extensionAttribute12 | X | X | X |  |
| extensionAttribute13 | X | X | X |  |
| extensionAttribute14 | X | X | X |  |
| extensionAttribute15 | X | X | X |  |
| extensionAttribute2 | X | X | X |  |
| extensionAttribute3 | X | X | X |  |
| extensionAttribute4 | X | X | X |  |
| extensionAttribute5 | X | X | X |  |
| extensionAttribute6 | X | X | X |  |
| extensionAttribute7 | X | X | X |  |
| extensionAttribute8 | X | X | X |  |
| extensionAttribute9 | X | X | X |  |
| facsimiletelephonenumber | X | X |  |  |
| givenName | X | X |  |  |
| homePhone | X | X |  |  |
| info | X | X | X | この属性は現在、グループには使用されません。 |
| Initials | X | X |  |  |
| l | X | X |  |  |
| legacyExchangeDN | X | X | X |  |
| mailNickname | X | X | X |  |
| managedBy |  |  | X |  |
| manager | X | X |  |  |
| member |  |  | X |  |
| mobile | X | X |  |  |
| msDS-HABSeniorityIndex | X | X | X |  |
| msDS-PhoneticDisplayName | X | X | X |  |
| msExchArchiveGUID | X |  |  |  |
| msExchArchiveName | X |  |  |  |
| msExchAssistantName | X | X |  |  |
| msExchAuditAdmin | X |  |  |  |
| msExchAuditDelegate | X |  |  |  |
| msExchAuditDelegateAdmin | X |  |  |  |
| msExchAuditOwner | X |  |  |  |
| msExchBlockedSendersHash | X | X |  |  |
| msExchBypassAudit | X |  |  |  |
| msExchBypassModerationLink |  |  | X | Microsoft Entra Connect バージョン 1.1.524.0 で利用可能 |
| msExchCoManagedByLink |  |  | X |  |
| msExchDelegateListLink | X |  |  |  |
| msExchELCExpirySuspensionEnd | X |  |  |  |
| msExchELCExpirySuspensionStart | X |  |  |  |
| msExchELCMailboxFlags | X |  |  |  |
| msExchEnableModeration | X |  | X |  |
| msExchExtensionCustomAttribute1 | X | X | X | 現在、この属性は Exchange Online では使用されていません。 |
| msExchExtensionCustomAttribute2 | X | X | X | 現在、この属性は Exchange Online では使用されていません。 |
| msExchExtensionCustomAttribute3 | X | X | X | 現在、この属性は Exchange Online では使用されていません。 |
| msExchExtensionCustomAttribute4 | X | X | X | 現在、この属性は Exchange Online では使用されていません。 |
| msExchExtensionCustomAttribute5 | X | X | X | 現在、この属性は Exchange Online では使用されていません。 |
| msExchHideFromAddressLists | X | X | X |  |
| msExchImmutableID | X |  |  |  |
| msExchLitigationHoldDate | X | X | X |  |
| msExchLitigationHoldOwner | X | X | X |  |
| msExchMailboxAuditEnable | X |  |  |  |
| msExchMailboxAuditLogAgeLimit | X |  |  |  |
| msExchMailboxGuid | X |  |  |  |
| msExchModeratedByLink | X | X | X |  |
| msExchModerationFlags | X | X | X |  |
| msExchRecipientDisplayType | X | X | X |  |
| msExchRecipientTypeDetails | X | X | X |  |
| msExchRemoteRecipientType | X |  |  |  |
| msExchRequireAuthToSendTo | X | X | X |  |
| msExchResourceCapacity | X |  |  | 現在、この属性は Exchange Online では使用されていません。 |
| msExchResourceDisplay | X |  |  |  |
| msExchResourceMetaData | X |  |  |  |
| msExchResourceSearchProperties | X |  |  |  |
| msExchRetentionComment | X | X | X |  |
| msExchRetentionURL | X | X | X |  |
| msExchSafeRecipientsHash | X | X |  |  |
| msExchSafeSendersHash | X | X |  |  |
| msExchSenderHintTranslations | X | X | X |  |
| msExchTeamMailboxExpiration | X |  |  |  |
| msExchTeamMailboxOwners | X |  |  |  |
| msExchTeamMailboxSharePointUrl | X |  |  |  |
| msExchUserHoldPolicies | X |  |  |  |
| msOrg-IsOrganizational |  |  | X |  |
| objectSID | X |  | X | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| oOFReplyToOriginator |  |  | X |  |
| otherFacsimileTelephone | X | X |  |  |
| otherHomePhone | X | X |  |  |
| otherTelephone | X | X |  |  |
| pager | X | X |  |  |
| physicalDeliveryOfficeName | X | X |  |  |
| postalCode | X | X |  |  |
| proxyAddresses | X | X | X |  |
| publicDelegates | X | X | X |  |
| pwdLastSet | X |  |  | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 パスワード同期とフェデレーションの両方で使用されます。 |
| reportToOriginator |  |  | X |  |
| reportToOwner |  |  | X |  |
| securityEnabled |  |  | X |  |
| sn | X | X |  |  |
| sourceAnchor | X | X | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| st | X | X |  |  |
| streetAddress | X | X |  |  |
| targetAddress | X | X |  |  |
| telephoneAssistant | X | X |  |  |
| telephoneNumber | X | X |  |  |
| thumbnailphoto | X | X |  | M365プロファイル写真に定期的に同期されます。 管理者は、Microsoft Entra Connect の値を変更することで、同期の頻度を設定できます。 ユーザーがオンプレミスとクラウドの両方で、Microsoft Entra Connect の値より短い期間で写真を変更した場合、最新の写真が提供される保証はありません。 |
| title | X | X |  |  |
| unauthOrig | X | X | X |  |
| usageLocation | X |  |  | 機械的特性。 ユーザーの国/地域。 ライセンスの割り当てに使用されます。 |
| userCertificate | X | X |  |  |
| userPrincipalName | X |  |  | UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |
| userSMIMECertificates | X | X |  |  |
| wWWHomePage | X | X |  |  |

### SharePoint オンライン

| 属性名 | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- |
| accountEnabled | X |  |  | アカウントが有効かどうかを定義します。 |
| authOrig | X | X | X |  |
| c | X | X |  |  |
| cn | X |  | X |  |
| co | X | X |  |  |
| company | X | X |  |  |
| countryCode | X | X |  |  |
| department | X | X |  |  |
| description | X | X | X |  |
| displayName | X | X | X |  |
| dLMemRejectPerms | X | X | X |  |
| dLMemSubmitPerms | X | X | X |  |
| extensionAttribute1 | X | X | X |  |
| extensionAttribute10 | X | X | X |  |
| extensionAttribute11 | X | X | X |  |
| extensionAttribute12 | X | X | X |  |
| extensionAttribute13 | X | X | X |  |
| extensionAttribute14 | X | X | X |  |
| extensionAttribute15 | X | X | X |  |
| extensionAttribute2 | X | X | X |  |
| extensionAttribute3 | X | X | X |  |
| extensionAttribute4 | X | X | X |  |
| extensionAttribute5 | X | X | X |  |
| extensionAttribute6 | X | X | X |  |
| extensionAttribute7 | X | X | X |  |
| extensionAttribute8 | X | X | X |  |
| extensionAttribute9 | X | X | X |  |
| facsimiletelephonenumber | X | X |  |  |
| givenName | X | X |  |  |
| hideDLMembership |  |  | X |  |
| homephone | X | X |  |  |
| info | X | X | X |  |
| initials | X | X |  |  |
| ipPhone | X | X |  |  |
| l | X | X |  |  |
| mail | X | X | X |  |
| mailnickname | X | X | X |  |
| managedBy |  |  | X |  |
| manager | X | X |  |  |
| member |  |  | X |  |
| middleName | X | X |  |  |
| mobile | X | X |  |  |
| msExchTeamMailboxExpiration | X |  |  |  |
| msExchTeamMailboxOwners | X |  |  |  |
| msExchTeamMailboxSharePointLinkedBy | X |  |  |  |
| msExchTeamMailboxSharePointUrl | X |  |  |  |
| objectSID | X |  | X | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| oOFReplyToOriginator |  |  | X |  |
| otherFacsimileTelephone | X | X |  |  |
| otherHomePhone | X | X |  |  |
| otherIpPhone | X | X |  |  |
| otherMobile | X | X |  |  |
| otherPager | X | X |  |  |
| otherTelephone | X | X |  |  |
| pager | X | X |  |  |
| physicalDeliveryOfficeName | X | X |  |  |
| postalCode | X | X |  |  |
| postOfficeBox | X | X |  | 現在、この属性は SharePoint Online では使用されていません。 |
| preferredLanguage | X |  |  |  |
| proxyAddresses | X | X | X |  |
| pwdLastSet | X |  |  | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 パスワード ハッシュ同期、パススルー認証、フェデレーションの両方で使用されます。 |
| reportToOriginator |  |  | X |  |
| reportToOwner |  |  | X |  |
| securityEnabled |  |  | X |  |
| sn | X | X |  |  |
| sourceAnchor | X | X | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| st | X | X |  |  |
| streetAddress | X | X |  |  |
| targetAddress | X | X |  |  |
| telephoneAssistant | X | X |  |  |
| telephoneNumber | X | X |  |  |
| thumbnailphoto | X | X |  | M365プロファイル写真に定期的に同期されます。 管理者は、Microsoft Entra Connect の値を変更することで、同期の頻度を設定できます。 ユーザーがオンプレミスとクラウドの両方で、Microsoft Entra Connect の値より短い期間で写真を変更した場合、最新の写真が提供される保証はありません。 |
| title | X | X |  |  |
| unauthOrig | X | X | X |  |
| url | X | X |  |  |
| usageLocation | X |  |  | 機械的特性。 ユーザーの国/地域 |
| . ライセンスの割り当てに使用されます。 |  |  |  |  |
| userPrincipalName | X |  |  | UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |
| wWWHomePage | X | X |  |  |

### Teams と Skype for Business Online

| 属性名 | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- |
| accountEnabled | X |  |  | アカウントが有効かどうかを定義します。 |
| c | X | X |  |  |
| cn | X |  | X |  |
| co | X | X |  |  |
| company | X | X |  |  |
| department | X | X |  |  |
| description | X | X | X |  |
| displayName | X | X | X |  |
| facsimiletelephonenumber | X | X | X |  |
| givenName | X | X |  |  |
| homephone | X | X |  |  |
| ipPhone | X | X |  |  |
| l | X | X |  |  |
| mail | X | X | X |  |
| mailNickname | X | X | X |  |
| managedBy |  |  | X |  |
| manager | X | X |  |  |
| member |  |  | X |  |
| mobile | X | X |  |  |
| msExchHideFromAddressLists | X | X | X |  |
| msRTCSIP-ApplicationOptions | X |  |  |  |
| msRTCSIP-DeploymentLocator | X | X |  |  |
| msRTCSIP-Line | X | X |  |  |
| msRTCSIP-OptionFlags | X | X |  |  |
| msRTCSIP-OwnerUrn | X |  |  |  |
| msRTCSIP-PrimaryUserAddress | X | X |  |  |
| msRTCSIP-UserEnabled | X | X |  |  |
| objectSID | X |  | X | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| otherTelephone | X | X |  |  |
| physicalDeliveryOfficeName | X | X |  |  |
| postalCode | X | X |  |  |
| preferredLanguage | X |  |  |  |
| proxyAddresses | X | X | X |  |
| pwdLastSet | X |  |  | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 パスワード ハッシュ同期、パススルー認証、フェデレーションの両方で使用されます。 |
| securityEnabled |  |  | X |  |
| sn | X | X |  |  |
| sourceAnchor | X | X | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| st | X | X |  |  |
| streetAddress | X | X |  |  |
| telephoneNumber | X | X |  |  |
| thumbnailphoto | X | X |  | M365プロファイル写真に定期的に同期されます。 管理者は、Microsoft Entra Connect の値を変更することで、同期の頻度を設定できます。 ユーザーがオンプレミスとクラウドの両方で、Microsoft Entra Connect の値より短い期間で写真を変更した場合、最新の写真が提供される保証はありません。 |
| title | X | X |  |  |
| usageLocation | X |  |  | 機械的特性。 ユーザーの国/地域。 ライセンスの割り当てに使用されます。 |
| userPrincipalName | X |  |  | UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |
| wWWHomePage | X | X |  |  |

### Azure RMS

| 属性名 | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- |
| accountEnabled | X |  |  | アカウントが有効かどうかを定義します。 |
| cn | X |  | X | 共通名またはエイリアス。 ほとんどの場合、[mail] 値のプレフィックス。 |
| displayName | X | X | X | フレンドリ名 (名の姓) として表示される名前を表す文字列。 |
| mail | X | X | X | 完全な電子メール アドレス。 |
| member |  |  | X |  |
| objectSID | X |  | X | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| proxyAddresses | X | X | X | 機械的特性。 Microsoft Entra ID によって使用されます。 ユーザーのすべてのセカンダリ 電子メール アドレスが含まれます。 |
| pwdLastSet | X |  |  | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 |
| securityEnabled |  |  | X |  |
| sourceAnchor | X | X | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| usageLocation | X |  |  | 機械的特性。 ユーザーの国/地域。 ライセンスの割り当てに使用されます。 |
| userPrincipalName | X |  |  | この UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |

### Intune

| 属性名 | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- |
| accountEnabled | X |  |  | アカウントが有効かどうかを定義します。 |
| c | X | X |  |  |
| cn | X |  | X |  |
| description | X | X | X |  |
| displayName | X | X | X |  |
| mail | X | X | X |  |
| mailnickname | X | X | X |  |
| member |  |  | X |  |
| objectSID | X |  | X | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| proxyAddresses | X | X | X |  |
| pwdLastSet | X |  |  | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 パスワード ハッシュ同期、パススルー認証、フェデレーションの両方で使用されます。 |
| securityEnabled |  |  | X |  |
| sourceAnchor | X | X | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| usageLocation | X |  |  | 機械的特性。 ユーザーの国/地域。 ライセンスの割り当てに使用されます。 |
| userPrincipalName | X |  |  | UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |

### Dynamics CRM

| 属性名 | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- |
| accountEnabled | X |  |  | アカウントが有効かどうかを定義します。 |
| c | X | X |  |  |
| cn | X |  | X |  |
| co | X | X |  |  |
| company | X | X |  |  |
| countryCode | X | X |  |  |
| description | X | X | X |  |
| displayName | X | X | X |  |
| facsimiletelephonenumber | X | X |  |  |
| givenName | X | X |  |  |
| l | X | X |  |  |
| managedBy |  |  | X |  |
| manager | X | X |  |  |
| member |  |  | X |  |
| mobile | X | X |  |  |
| objectSID | X |  | X | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| physicalDeliveryOfficeName | X | X |  |  |
| postalCode | X | X |  |  |
| preferredLanguage | X |  |  |  |
| pwdLastSet | X |  |  | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 パスワード ハッシュ同期、パススルー認証、フェデレーションの両方で使用されます。 |
| securityEnabled |  |  | X |  |
| sn | X | X |  |  |
| sourceAnchor | X | X | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| st | X | X |  |  |
| streetAddress | X | X |  |  |
| telephoneNumber | X | X |  |  |
| title | X | X |  |  |
| usageLocation | X |  |  | 機械的特性。 ユーザーの国/地域。 ライセンスの割り当てに使用されます。 |
| userPrincipalName | X |  |  | UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |

### サード パーティ製アプリケーション

このグループは、汎用ワークロードまたはアプリケーションに必要な最小限の属性として使用される属性のセットです。 これは、別のセクションに記載されていないワークロードまたは Microsoft 以外のアプリに使用できます。 これは、次の場合に明示的に使用されます。

- Yammer (ユーザーのみが使用されます)
- [SharePoint](https://learn.microsoft.com/ja-jp/sharepoint/create-b2b-extranet) などのリソースによって提供されるハイブリッド 企業間 (B2B) のクロス組織コラボレーション シナリオ

このグループは、Microsoft Entra ディレクトリが Microsoft 365、Dynamics、または Intune をサポートするために使用されていない場合に使用できる属性のセットです。 コア属性の小さなセットがあります。 一部のサード パーティ製アプリケーションへのシングル サインオンまたはプロビジョニングでは、ここで説明する属性に加えて属性の同期を構成する必要があることに注意してください。 アプリケーションの要件については、各アプリケーションの [SaaS アプリのチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list) で説明されています。

| 属性名 | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- |
| accountEnabled | X |  |  | アカウントが有効かどうかを定義します。 |
| cn | X |  | X |  |
| displayName | X | X | X |  |
| employeeID | X |  |  |  |
| givenName | X | X |  |  |
| mail | X |  | X |  |
| managedBy |  |  | X |  |
| mailNickName | X | X | X |  |
| member |  |  | X |  |
| objectSID | X |  |  | 機械的特性。 Microsoft Entra ID と AD の間の同期を維持するために使用される AD ユーザー識別子。 |
| proxyAddresses | X | X | X |  |
| pwdLastSet | X |  |  | 機械的特性。 既に発行されているトークンを無効にするタイミングを把握するために使用されます。 パスワード ハッシュ同期、パススルー認証、フェデレーションの両方で使用されます。 |
| securityEnabled |  |  | X |  |
| sn | X | X |  |  |
| sourceAnchor | X | X | X | 機械的特性。 ADDS と Microsoft Entra ID の間の関係を維持するための不変識別子。 |
| usageLocation | X |  |  | 機械的特性。 ユーザーの国/地域。 ライセンスの割り当てに使用されます。 |
| userPrincipalName | X |  |  | UPN は、ユーザーのログイン ID です。 ほとんどの場合、[メール] 値と同じです。 |

### Windows 10

Windows 10 ドメインに参加しているコンピューター (デバイス) は、一部の属性を Microsoft Entra ID に同期します。 シナリオの詳細については、「[ドメイン参加済みデバイスを Microsoft Entra ID for Windows 10 エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)に接続する」を参照してください。 これらの属性は常に同期され、Windows 10 は選択を解除できるアプリとして表示されません。 Windows 10 ドメインに参加しているコンピューターは、userCertificate 属性が設定されていることで識別されます。

| 属性名 | デバイス | コメント |
| --- | --- | --- |
| accountEnabled | X |  |
| deviceTrustType | X | ドメインに参加しているコンピューターのハードコードされた値。 |
| displayName | X |  |
| ms-DS-CreatorSID | X | registeredOwnerReference とも呼ばれます。 |
| objectGUID | X | deviceID とも呼ばれます。 |
| objectSID | X | onPremisesSecurityIdentifier とも呼ばれます。 |
| operatingSystem | X | deviceOSType とも呼ばれます。 |
| operatingSystemVersion | X | deviceOSVersion とも呼ばれます。 |
| userCertificate | X |  |

**ユーザー** のこれらの属性は、選択した他のアプリに追加されます。

| 属性名 | ユーザー | コメント |
| --- | --- | --- |
| domainFQDN | X | dnsDomainName とも呼ばれます。 たとえば、contoso.com。 |
| domainNetBios | X | netBiosName とも呼ばれます。 たとえば、CONTOSO です。 |
| msDS-KeyCredentialLink | X | ユーザーが Windows Hello for Business に登録されたら。 |

### Exchange ハイブリッド ライトバック

これらの属性は、Exchange ハイブリッド **を有効にすることを選択すると、Microsoft Entra ID からオンプレミス Active Directory**書き戻されます。 Exchange のバージョンによっては、同期される属性が少なくなる場合があります。

| 属性名 (オンプレミス AD) | 属性名 (接続 UI) | ユーザー | 連絡先 | グループ | コメント |
| --- | --- | --- | --- | --- | --- |
| msDS-ExternalDirectoryObjectID | ms-DS-External-Directory-Object-Id | X |  |  | Microsoft Entra ID の cloudAnchor から派生します。 この属性は、Exchange 2016 および Windows Server 2016 AD の新機能です。 |
| msExchArchiveStatus | ms-Exch-ArchiveStatus | X |  |  | オンライン アーカイブ: 顧客がメールをアーカイブできるようにします。 |
| msExchBlockedSendersHash | ms-Exch-BlockedSendersHash | X |  |  | フィルター処理: オンプレミスのフィルター処理と、クライアントからのオンラインの安全でブロックされた送信者データを書き戻します。 |
| msExchSafeRecipientsHash | ms-Exch-SafeRecipientsHash | X |  |  | フィルター処理: オンプレミスのフィルター処理と、クライアントからのオンラインの安全でブロックされた送信者データを書き戻します。 |
| msExchSafeSendersHash | ms-Exch-SafeSendersHash | X |  |  | フィルター処理: オンプレミスのフィルター処理と、クライアントからのオンラインの安全でブロックされた送信者データを書き戻します。 |
| msExchUCVoiceMailSettings | ms-Exch-UCVoiceMailSettings | X |  |  | ユニファイド メッセージング (UM) - オンライン ボイス メールを有効にする: Microsoft Lync Server 統合によって使用され、ユーザーがオンライン サービスでボイス メールを使用していることをオンプレミスの Lync Server に示します。 |
| msExchUserHoldPolicies | ms-Exch-UserHoldPolicies | X |  |  | 訴訟ホールド: クラウド サービスが訴訟ホールドを受けているユーザーを特定できるようにします。 |
| proxyAddresses | プロキシアドレス | X | X | X | Exchange Online の x500 アドレスのみが挿入されます。 |
| publicDelegates | ms-Exch-Public-Delegates | X |  |  | Exchange Online メールボックスに、オンプレミスの Exchange メールボックスを持つユーザーに SendOnBehalfTo 権限を付与できるようにします。 Microsoft Entra Connect ビルド 1.1.552.0 以降が必要です。 |

### Exchange メールパブリック フォルダー

これらの属性は、Exchange メール パブリック フォルダー **を有効にすることを選択すると、オンプレミスの Active Directory から Microsoft Entra ID**同期されます。

| 属性名 | パブリックフォルダー | コメント |
| --- | --- | --- |
| displayName | X |  |
| mail | X |  |
| msExchRecipientTypeDetails | X |  |
| objectGUID | X |  |
| proxyAddresses | X |  |
| targetAddress | X |  |

### デバイスの書き戻し

デバイス オブジェクトは Active Directory に作成されます。 これらのオブジェクトには、Microsoft Entra ID に参加しているデバイスや、ドメインに参加している Windows 10 コンピューターを指定できます。

| 属性名 | デバイス | コメント |
| --- | --- | --- |
| altSecurityIdentities | X |  |
| displayName | X |  |
| dn | X |  |
| msDS-CloudAnchor | X |  |
| msDS-DeviceID | X |  |
| msDS-DeviceObjectVersion | X |  |
| msDS-DeviceOSType | X |  |
| msDS-DeviceOSVersion | X |  |
| msDS-DevicePhysicalIDs | X |  |
| msDS-KeyCredentialLink | X | Windows Server 2016 AD スキーマでのみ |
| msDS-IsCompliant | X |  |
| msDS-IsEnabled | X |  |
| msDS-IsManaged | X |  |
| msDS-RegisteredOwner | X |  |

### 筆記

- 代替 ID を使用する場合、オンプレミス属性 userPrincipalName は Microsoft Entra 属性 onPremisesUserPrincipalName と同期されます。 メールなどの代替 ID 属性は、Microsoft Entra 属性 userPrincipalName と同期されます。
- Microsoft Entra onPremisesUserPrincipalName 属性に一意性は適用されませんが、複数の異なる Microsoft Entra ユーザーに対して同じ UserPrincipalName 値を Microsoft Entra onPremisesUserPrincipalName 属性に同期することはサポートされていません。
- 上記の一覧では、ユーザー  オブジェクトの種類は、iNetOrgPerson オブジェクトの種類にも適用されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-sync-functions-reference"} -->
## Microsoft Entra Connect Sync: 関数リファレンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-sync-functions-reference
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect Sync での宣言型プロビジョニング式のリファレンス。

Microsoft Entra Connect では、同期時の属性値を操作するために関数を使用します。 関数の構文は、次の形式を使用して表されます。`<output type> FunctionName(<input type> <position name>, ..)`

関数がオーバーロード状態の場合に複数の構文を受け入れると、すべての有効な構文が一覧表示されます。 関数は厳密に型指定され、渡された型が文書化された型と一致することを確認します。 型が一致しない場合は、エラーがスローされます。

型は次の構文で表されます。

- **bin** – バイナリ
- **bool** – ブール値
- **dt** – UTC の日付/時刻
- **enum** – 既知の定数の列挙
- **exp** – 評価結果にブール値が想定される式
- **mvbin** – 複数値のバイナリ
- **mvstr** – 複数値の文字列
- **mvref** – 複数値の参照
- **num** – 数値
- **ref** – 参照
- **str** – 文字列
- **var** – その他の (ほとんど) すべての型のバリアント
- **void** – 値を返しません

**mvbin**、**mvstr**、**mvref** 型の関数は、複数値の属性のみに有効です。 **bin**、**str**、**ref** 型の関数は、単一値と複数値両方の属性に有効です。

### 関数参照

- **[MSSQLSERVER のプロトコルのプロパティ]**
    - CertExtensionOids
    - CertFormat
    - CertFriendlyName
    - CertHashString
    - CertIssuer
    - CertIssuerDN
    - CertIssuerOid
    - CertKeyAlgorithm
    - CertKeyAlgorithmParams
    - CertNameInfo
    - CertNotAfter
    - CertNotBefore
    - CertPublicKeyOid
    - CertPublicKeyParametersOid
    - CertSerialNumber
    - CertSignatureAlgorithmOid
    - CertSubject
    - CertSubjectNameDN
    - CertSubjectNameOid
    - CertThumbprint
    - CertVersion
    - IsCert
- **変換**
    - CBool
    - CDate
    - CGuid
    - ConvertFromBase64
    - ConvertToBase64
    - ConvertFromUTF8Hex
    - ConvertToUTF8Hex
    - CNum
    - CRef
    - CStr
    - StringFromGuid
    - StringFromSid
- **日付/時刻**
    - DateAdd
    - DateFromNum
    - FormatDateTime
    - 今
    - NumFromDate
- **ディレクトリ**
    - DNComponent
    - DNComponentRev
    - EscapeDNComponent
- **評価**
    - IsBitSet
    - IsDate
    - IsEmpty
    - IsGuid
    - IsNull
    - IsNullOrEmpty
    - IsNumeric
    - IsPresent
    - IsString
- **算術**
    - BitAnd
    - BitOr
    - RandomNum
- **Multi\*valued**
    - 含む
    - 数える
    - アイテム
    - ItemOrNull
    - 接続
    - RemoveDuplicates
    - 割る
- **プログラム フロー**
    - エラー
    - IIF
    - 選ぶ
    - スイッチ
    - どこ
    - で
- **[テキスト]**
    - GUID
    - InStr
    - InStrRev
    - LCase
    - 左
    - Len
    - LTrim
    - 半ば
    - PadLeft
    - PadRight
    - PCase
    - 取り替える
    - ReplaceChars
    - 右
    - RTrim
    - 刈る
    - UCase
    - 言葉

#### BitAnd

**説明:** BitAnd 関数は、値に指定のビットを設定します。

**構文 :**`num BitAnd(num value1, num value2)`

- value1, value2: ともに AND になる数値

**備考:** この関数は両方のパラメーターをバイナリ表現に変換し、ビットを次に設定します。

- 0 - *value1* と *value2* 内の対応するビットの 1 つまたは両方が 0 の場合
- 1 - 対応するビットの両方が 1 の場合。

つまり、両方のパラメーターの対応するビットが 1 の場合を除くすべてのケースで 0 を返します。

**例:**`BitAnd(&HF, &HF7)` 16 進数の "F" と "F7" の AND は 7 と評価されるため、7 を返します。

#### BitOr

**説明:** BitOr 関数は、値に指定のビットを設定します。

**構文 :**`num BitOr(num value1, num value2)`

- value1, value2: ともに OR になる数値

**備考:** この関数は両方のパラメーターをバイナリ表現に変換して、マスクとフラグで対応するビットの 1 つまたは両方が 1 の場合はビットを 1 に設定し、対応する両方のビットが 0 の場合は 0 に設定します。 つまり、両方のパラメーターの対応するビットが 0 の場合を除くすべてのケースで 1 を返します。

#### CBool

**説明:** CBool 関数は、式の評価結果に基づいてブール値を返します。

**構文 :**`bool CBool(exp Expression)`

**備考:** 式の評価結果が 0 以外の値の場合は CBool によって True が返され、それ以外の場合は False が返されます。

**例:**`CBool([attrib1] = [attrib2])`

両方の属性が同じ値を持つ場合は、True を返します。

#### CDate

**説明:** CDate 関数は、文字列から UTC DateTime を返します。 DateTime は Sync ではネイティブの属性の型ではありませんが、一部の関数で使用されます。

**構文 :**`dt CDate(str value)`

- 値:日付、時刻、オプションでタイム ゾーンを含む文字列

**備考:** 文字列は常に UTC で返されます。

**例:**`CDate([employeeStartTime])` 従業員の作業開始時刻に基づいて、DateTime を返します。

`CDate("2013-01-10 4:00 PM -8")` "2013-01-11 12:00 AM" を表す DateTime を返します。

#### CertExtensionOids

**説明:** 証明書オブジェクトのすべての重要な拡張機能の Oid 値を返します。

**構文 :**`mvstr CertExtensionOids(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertFormat

**説明:** この X.509v3 証明書の形式の名前を返します。

**構文 :**`str CertFormat(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertFriendlyName

**説明:** 証明書に関連付けられている別名を返します。

**構文 :**`str CertFriendlyName(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertHashString

**説明:** X.509v3 証明書の SHA1 ハッシュ値を 16 進数文字列で返します。

**構文 :**`str CertHashString(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertIssuer

**説明:** X.509v3 証明書を発行した証明機関の名前を返します。

**構文 :**`str CertIssuer(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertIssuerDN

**説明:** 証明書の発行者の識別名を返します。

**構文 :**`str CertIssuerDN(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertIssuerOid

**説明:** 証明書の発行者の識別証明書の発行者の Oid を返します。

**構文 :**`str CertIssuerOid(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertKeyAlgorithm

**説明:** この X.509v3 証明書のキー アルゴリズム情報を文字列で返します。

**構文 :**`str CertKeyAlgorithm(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertKeyAlgorithmParams

**説明:** X.509v3 証明書のキー アルゴリズム パラメーターを 16 進数文字列で返します。

**構文 :**`str CertKeyAlgorithm(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertNameInfo

**説明:** 証明書のサブジェクトと発行者名を返します。

**構文 :**`str CertNameInfo(binary certificateRawData, str x509NameType, bool includesIssuerName)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。
- X509NameType:サブジェクトの X509NameType 値。
- includesIssuerName: 発行者名を含める場合は true、それ以外の場合は false。

#### CertNotAfter

**説明:** その後は証明書が有効ではなくなる日付を現地時刻で返します。

**構文 :**`dt CertNotAfter(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertNotBefore

**説明:** 証明書が有効になる日付を現地時刻で返します。

**構文 :**`dt CertNotBefore(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertPublicKeyOid

**説明:** X.509v3 証明書の公開キーの Oid を返します。

**構文 :**`str CertKeyAlgorithm(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertPublicKeyParametersOid

**説明:** X.509v3 証明書の公開キーのパラメーターの Oid を返します。

**構文 :**`str CertPublicKeyParametersOid(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### 証明書シリアル番号

**説明:** X.509v3 証明書のシリアル番号を返します。

**構文 :**`str CertSerialNumber(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertSignatureAlgorithmOid

**説明:** 証明書の署名の作成に使用されるアルゴリズムの Oid を返します。

**構文 :**`str CertSignatureAlgorithmOid(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertSubject

**説明:** 証明書のサブジェクト識別名を取得します。

**構文 :**`str CertSubject(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertSubjectNameDN

**説明:** 証明書のサブジェクト識別名を返します。

**構文 :**`str CertSubjectNameDN(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertSubjectNameOid

**説明:** 証明書のサブジェクト名の Oid を返します。

**構文 :**`str CertSubjectNameOid(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertThumbprint

**説明:** 証明書の拇印を返します。

**構文 :**`str CertThumbprint(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CertVersion

**説明:** 証明書の X.509 形式のバージョンを返します。

**構文 :**`str CertThumbprint(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### CGuid

**説明:** CGuid 関数は、GUID の文字列表現をそのバイナリ表現に変換します。

**構文 :**`bin CGuid(str GUID)`

- 次のパターンで書式設定された文字列: 00001111-aaaa-2222-bbbb-3333cccc4444 または {00001111-aaaa-2222-bbbb-3333cccc4444}

#### 次を含む

**説明:** Contains 関数は、複数値の属性内で文字列を検索します。

**構文 :**`num Contains (mvstring attribute, str search)` - 大文字小文字を区別`num Contains (mvstring attribute, str search, enum Casetype)``num Contains (mvref attribute, str search)` - 大文字小文字を区別

- attribute: 検索対象の複数値の属性。
- search: 属性で検索する文字列。
- Casetype:CaseInsensitive または CaseSensitive。

文字列が見つかった複数値の属性にインデックスを返します。 文字列が見つからない場合は 0 を返します。

**備考:** 複数値の文字列属性の場合、検索では、値の部分文字列が検出されます。 参照属性の場合、一致と見なされるためには、検索された文字列は正確に値と一致する必要があります。

**例:**`IIF(Contains([proxyAddresses],"SMTP:")>0,[proxyAddresses],Error("No primary SMTP address found."))` proxyAddresses 属性にプライマリ メール アドレスが含まれている場合は (大文字の "SMTP:" で表されます) proxyAddress 属性を返し、それ以外の場合はエラーを返します。

#### ConvertFromBase64

**説明:** ConvertFromBase64 関数は、指定した base64 でエンコードされた値を正規の文字列に変換します。

**構文 :**`str ConvertFromBase64(str source)` - エンコードには Unicode を想定しています`str ConvertFromBase64(str source, enum Encoding)`

- source:Base64 でエンコードされた文字列
- Encoding:Unicode、ASCII、UTF8

**例**`ConvertFromBase64("SABlAGwAbABvACAAdwBvAHIAbABkACEA")``ConvertFromBase64("SGVsbG8gd29ybGQh", UTF8)`

どちらの例でも "*Hello world!* " を返します。

#### ConvertFromUTF8Hex

**説明:** ConvertFromUTF8Hex 関数は、指定した UTF8 の 16 進数でエンコードされた値を文字列に変換します。

**構文 :**`str ConvertFromUTF8Hex(str source)`

- source:UTF8 の 2 バイトでエンコードされた文字列

**備考:** この関数と ConvertFromBase64([],UTF8) との違いは、結果が DN 属性で表示される点です。 この形式は、Microsoft Entra ID で DN として使用されます。

**例:**`ConvertFromUTF8Hex("48656C6C6F20776F726C6421")` "*Hello world!* " を返します。

#### ConvertToBase64

**説明:** ConvertToBase64 関数は、文字列を Unicode の base64 文字列に変換します。 整数の配列の値を、base 64 桁でエンコードされているそれと同等の文字列表現に変換します。

**構文 :**`str ConvertToBase64(str source)`

**例:**`ConvertToBase64("Hello world!")` "SABlAGwAbABvACAAdwBvAHIAbABkACEA" を返します。

#### ConvertToUTF8Hex

**説明:** ConvertToUTF8Hex 関数は、文字列を UTF8 の 16 進数でエンコードされた値に変換します。

**構文 :**`str ConvertToUTF8Hex(str source)`

**備考:** この関数の出力形式は、DN 属性の形式として Microsoft Entra ID で使用されます。

**例:**`ConvertToUTF8Hex("Hello world!")` 48656C6C6F20776F726C6421 を返します。

#### 数える

**説明:** Count 関数は、複数値の属性内の要素数を返します。

**構文 :**`num Count(mvstr attribute)`

#### CNum

**説明:** CNum 関数は、文字列を受け取り、数値データ型を返します。

**構文 :**`num CNum(str value)`

#### CRef

**説明:** 文字列を参照属性に変換します。

**構文 :**`ref CRef(str value)`

**例:**`CRef("CN=LC Services,CN=Microsoft,CN=lcspool01,CN=Pools,CN=RTC Service," & %Forest.LDAP%)`

#### CStr

**説明:** CStr 関数は、文字列データ型に変換します。

**構文 :**`str CStr(num value)``str CStr(ref value)``str CStr(bool value)`

- value: 数値、参照属性、ブール値を指定できます。

**例:**`CStr([dn])` "cn=Joe,dc=contoso,dc=com" を返します。

#### DateAdd（日付追加）

**説明:** 指定した時間間隔が追加された日付を含む Date を返します。

**構文 :**`dt DateAdd(str interval, num value, dt date)`

- interval:追加する時間間隔を表す文字列式。 文字列には次のいずれかの値が必要です。
    - yyyy: 年
    - q: 四半期
    - m: 月
    - y: 年間通算日
    - d: 日
    - w: 週日
    - ww: 週
    - h: 時
    - n: 分
    - s: 秒
- value: 追加する単位の数。 正の数 (将来の日時を取得する場合) または負の数 (過去の日時を取得する場合) を指定できます。
- date:間隔が追加される日付を表す DateTime。

**例:**`DateAdd("m", 3, CDate("2001-01-01"))` 3 か月を追加した結果の "2001-04-01" を表す DateTime を返します。

#### DateFromNum

**説明:** DateFromNum 関数は、AD の日付形式の値を DateTime 型に変換します。

**構文 :**`dt DateFromNum(num value)`

**例:**`DateFromNum([lastLogonTimestamp])``DateFromNum(129699324000000000)` 2012-01-01 23:00:00 を表す DateTime を返します。

#### DNComponent

**説明:** DNComponent 関数は、指定した DN コンポーネントの値を左から返します。

**構文 :**`str DNComponent(ref dn, num ComponentNumber)`

- dn: 解釈する参照属性
- ComponentNumber:返される DN のコンポーネント

**例:**`DNComponent(CRef([dn]),1)` dn が "cn=Joe,ou=…," の場合は、Joe が返されます。

#### DNComponentRev

**説明:** DNComponentRev 関数は、指定した DN コンポーネントの値を右 (端) から返します。

**構文 :**`str DNComponentRev(ref dn, num ComponentNumber)``str DNComponentRev(ref dn, num ComponentNumber, enum Options)`

- dn: 解釈する参照属性
- ComponentNumber - 返される DN のコンポーネント
- オプション:DC – "dc =" ですべてのコンポーネントを無視します。

**例:** dn が "cn=Joe,ou=Atlanta,ou=GA,ou=US, dc=contoso,dc=com" の場合、`DNComponentRev(CRef([dn]),3)``DNComponentRev(CRef([dn]),1,"DC")` 両方が US を返します。

#### エラー

**説明:** Error 関数は、カスタム エラーを返すために使用します。

**構文 :**`void Error(str ErrorMessage)`

**例:**`IIF(IsPresent([accountName]),[accountName],Error("AccountName is required"))` 属性 accountName が存在しない場合は、オブジェクトでエラーをスローします。

#### EscapeDNComponent

**説明:** EscapeDNComponent 関数は、DN のコンポーネントを 1 つ受け取り、LDAP で表示できるようにそれをエスケープします。

**構文 :**`str EscapeDNComponent(str value)`

**例:**`EscapeDNComponent("cn=" & [displayName]) & "," & %ForestLDAP%)` displayName 属性に LDAP でエスケープする必要のある文字が含まれている場合でも、LDAP ディレクトリでオブジェクトを作成できるようにします。

#### 日付と時間のフォーマット

**説明:** FormatDateTime 関数は、DateTime を指定した形式の文字列に設定するために使用します。

**構文 :**`str FormatDateTime(dt value, str format)`

- value: DateTime 形式の値
- format: 変換する形式を表す文字列。

**備考:** 形式で有効な値については、「[Custom date and time formats for the FORMAT function](https://learn.microsoft.com/ja-jp/dax/format-function-dax)」(FORMAT 関数用のカスタム日付/時刻形式) を参照してください。

**例:**

`FormatDateTime(CDate("12/25/2007"),"yyyy-MM-dd")` 結果は "2007-12-25" となります。

`FormatDateTime(DateFromNum([pwdLastSet]),"yyyyMMddHHmmss.0Z")` "20140905081453.0Z" などの結果になります。

#### Guid

**説明:** GUID 関数は、新しいランダムな GUID を生成します。

**構文 :**`str Guid()`

#### IIF

**説明:** IIF 関数は、指定した条件に基づいて、使用できる一連の値のうち、いずれかを返します。

**構文 :**`var IIF(exp condition, var valueIfTrue, var valueIfFalse)`

- condition: 評価結果が true または false になる任意の値または式。
- valueIfTrue:条件の評価結果が true の場合に返される値。
- valueIfFalse:条件の評価結果が false の場合に返される値。

**例:**`IIF([employeeType]="Intern","t-" & [alias],[alias])` ユーザーがインターンの場合はユーザーのエイリアスの先頭に "t-" を付けて返し、そうでない場合はユーザーのエイリアスをそのまま返します。

#### InStr（文字列内検索関数）

**説明:** InStr 関数は文字列内の最初の部分文字列を検索します。

**構文 :**

`num InStr(str stringcheck, str stringmatch)``num InStr(str stringcheck, str stringmatch, num start)``num InStr(str stringcheck, str stringmatch, num start, enum compare)`

- stringcheck: 検索対象の文字列
- stringmatch: 検出対象の文字列
- start: 部分文字列の検索開始位置
- compare: vbTextCompare または vbBinaryCompare

**備考:** 部分文字列が見つかった位置を返します。見つからなかった場合は 0 を返します。

**例:**`InStr("The quick brown fox","quick")` 評価結果は 5 になります。

`InStr("repEated","e",3,vbBinaryCompare)` 評価結果は 7 になります。

#### InStrRev

**説明:** InStrRev 関数は文字列内の最後の部分文字列を検索します。

**構文 :**`num InstrRev(str stringcheck, str stringmatch)``num InstrRev(str stringcheck, str stringmatch, num start)``num InstrRev(str stringcheck, str stringmatch, num start, enum compare)`

- stringcheck: 検索対象の文字列
- stringmatch: 検出対象の文字列
- start: 部分文字列の検索開始位置
- compare: vbTextCompare または vbBinaryCompare

**備考:** 部分文字列が見つかった位置を返します。見つからなかった場合は 0 を返します。

**例:**`InStrRev("abbcdbbbef","bb")` 7 を返します。

#### IsBitSet

**説明:** IsBitSet 関数は、ビットが設定されているかどうかをテストします。

**構文 :**`bool IsBitSet(num value, num flag)`

- value: 評価対象の数値。flag: 評価対象のビットがある数値。

**例:**`IsBitSet(&HF,4)` ビット "4" が 16 進数値 "F" で設定されているため True が返されます。

#### IsDate

**説明:** 式が DateTime 型として評価できる場合、IsDate 関数の評価結果は True になります。

**構文 :**`bool IsDate(var Expression)`

**備考:** CDate() が成功するかどうかを判断するために使用します。

#### IsCert

**説明:** 生データを .NET X509Certificate2 証明書オブジェクトにシリアル化できる場合は true を返します。

**構文 :**`bool CertThumbprint(binary certificateRawData)`

- certificateRawData:X.509 証明書のバイト配列の表現。 バイト配列には、バイナリ (DER) または Base64 でエンコードされた X.509 データを指定できます。

#### 空である

**説明:** 属性が CS または MV に存在しても評価結果が空の文字列である場合、IsEmpty 関数の評価結果は True になります。

**構文 :**`bool IsEmpty(var Expression)`

#### IsGuid

**説明:** 文字列が GUID に変換できる場合、IsGUID 関数の評価結果は true になります。

**構文 :**`bool IsGuid(str GUID)`

**備考:** GUID は、次のいずれかのパターンに従って文字列として定義されます: 00001111-aaaa-2222-bbbb-3333cccc4444 または {00001111-aaaa-2222-bbbb-3333cccc4444}

CGuid() が成功するかどうかを判断するために使用します。

**例:**`IIF(IsGuid([strAttribute]),CGuid([strAttribute]),NULL)` StrAttribute に GUID 形式がある場合はバイナリ表現を返します。それ以外の場合は Null を返します。

#### IsNull

**説明:** 式の評価結果が Null の場合、IsNull 関数は true を返します。

**構文 :**`bool IsNull(var Expression)`

**備考:** 属性の場合、Null は属性の不在によって表されます。

**例:**`IsNull([displayName])` CS または MV に属性がない場合は True を返します。

#### IsNullOrEmpty

**説明:** 式が null または空の文字列の場合、IsNullOrEmpty 関数は true を返します。

**構文 :**`bool IsNullOrEmpty(var Expression)`

**備考:** 属性の場合は、属性がないか、存在しても空の文字列の場合、評価結果は True になります。 この関数の逆の関数は IsPresent です。

**例:**`IsNullOrEmpty([displayName])` CS または MV に属性がないか、空の文字列の場合は True を返します。

#### IsNumeric

**説明:** IsNumeric 関数は、式を数値型として評価できるかどうかを示すブール値を返します。

**構文 :**`bool IsNumeric(var Expression)`

**備考:** CNum() が式の解析に成功するかどうかを判断するために使用します。

#### IsString

**説明:** 式を文字列型として評価できる場合、IsString 関数の評価結果は True になります。

**構文 :**`bool IsString(var expression)`

**備考:** CStr() が式の解析に成功するかどうかを判断するために使用します。

#### IsPresent

**説明:** 式の評価結果が Null でもなく、空でもない文字列の場合、IsPresent 関数は true を返します。

**構文 :**`bool IsPresent(var expression)`

**備考:** この関数の逆関数は IsNullOrEmpty です。

**例:**`Switch(IsPresent([directManager]),[directManager], IsPresent([skiplevelManager]),[skiplevelManager], IsPresent([director]),[director])`

#### アイテム

**説明:** Item 関数は複数値の文字列/属性から 1 つの項目を返します。

**構文 :**`var Item(mvstr attribute, num index)`

- attribute: 複数値の属性
- index: 複数値の文字列内の項目へのインデックス。

**備考:** Contains 関数は複数値の属性内の項目に対するインデックスを返すため、Item 関数を Contains 関数と共に使用すると便利です。

インデックスが範囲外にある場合は、エラーをスローします。

**例:**`Mid(Item([proxyAddresses],Contains([proxyAddresses], "SMTP:")),6)` プライマリ メール アドレスを返します。

#### ItemOrNull

**説明:** ItemOrNull 関数は複数値の文字列/属性から 1 つの項目を返します。

**構文 :**`var ItemOrNull(mvstr attribute, num index)`

- attribute: 複数値の属性
- index: 複数値の文字列内の項目へのインデックス。

**備考:** ItemOrNull 関数は複数値の属性内の項目に対するインデックスを返すため、ItemOrNull 関数を Contains 関数と共に使用すると便利です。

インデックスが範囲外にある場合は、Null 値を返します。

#### 参加する

**説明:** Join 関数は、複数値の文字列を受け取り、指定した区切り記号が項目間に挿入された、単一値の文字列を返します。

**構文 :**`str Join(mvstr attribute)``str Join(mvstr attribute, str Delimiter)`

- 属性を探します。結合対象の文字列が含まれる複数値の属性。
- delimiter:返される文字列内で部分文字列を区切るために使用する任意の文字列。 省略した場合は、空白文字 (" ") が使用されます。 delimiter が長さ 0 の文字列 ("") または Nothing の場合、リスト内のすべての項目は、区切り記号なしで連結されます。

**解説** Join 関数と Split 関数の間には類似点があります。 Join 関数は、文字列の配列を受け取り、区切り文字列を使用してそれらを結合し、単一の文字列を返します。 Split 関数は、文字列を受け取って区切り記号で分割し、文字列の配列を返します。 ただし、Join 関数が任意の区切り文字列を使った文字列を連結できるのに対し、Split 関数で文字列を分割する際には 1 文字の区切り記号しか使用できないという大きな違いがあります。

**例:**`Join([proxyAddresses],",")` "SMTP:john.doe@contoso.com,smtp:jd@contoso.com" などを返します

#### LCase

**説明:** LCase 関数は、文字列内のすべての文字を小文字に変換します。

**構文 :**`str LCase(str value)`

**例:**`LCase("TeSt")` "test" を返します。

#### 左

**説明:** Left 関数は文字列の左端から数えて指定した文字数分の文字を返します。

**構文 :**`str Left(str string, num NumChars)`

- string: 返される文字を含む文字列
- NumChars: 文字列の左端から数えて返される文字数を指定する数値

**備考:** string 内の最初の numChars 文字分の文字を含む文字列。

- numChars = 0 の場合、空の文字列を返します。
- numChars &lt; 0 の場合、入力文字列を返します。
- string が null の場合、空の文字列を返します。

string に含まれる文字数が numChars で指定した数より少ない場合は、string と同一の文字列 (パラメーター 1 のすべての文字が含まれる) が返されます。

**例:**`Left("John Doe", 3)` "Joh" を返します。

#### レン

**説明:** Len 関数は文字列の文字数を返します。

**構文 :**`num Len(str value)`

**例:**`Len("John Doe")` 8 を返します。

#### LTrim

**説明:** LTrim 関数は文字列の先頭の空白文字を削除します。

**構文 :**`str LTrim(str value)`

**例:**`LTrim(" Test ")` "Test " を返します。

#### 中間

**説明:** Mid 関数は文字列の指定した位置から数えて、指定した文字数分の文字を返します。

**構文 :**`str Mid(str string, num start, num NumChars)`

- string: 返される文字を含む文字列
- start: 文字列内で返される文字の開始位置を指定する数値
- NumChars: 文字列の位置から数えて返される文字数を指定する数値

**備考:** string 内の start 位置から数えて numChars 文字分の文字を返します。 文字列内の start 位置から数えて numChars 文字分の文字が含まれる文字列。

- numChars = 0 の場合、空の文字列を返します。
- numChars &lt; 0 の場合、入力文字列を返します。
- start &gt; 文字列の長さの場合、入力文字列を返します。
- start &lt; = 0 の場合、入力文字列を返します。
- string が null の場合、空の文字列を返します。

string で start 位置から後に numChar 文字が残っていない場合、できるだけ多くの文字が返されます。

**例:**`Mid("John Doe", 3, 5)` "hn Do" を返します。

`Mid("John Doe", 6, 999)` "Doe" を返します。

#### 今

**説明:** Now 関数は、コンピューターのシステムの日付と時刻に従って、現在の日付と時刻を指定する DateTime を返します。

**構文 :**`dt Now()`

#### NumFromDate

**説明:** NumFromDate 関数は、AD の日付形式で日付を返します。

**構文 :**`num NumFromDate(dt value)`

**例:**`NumFromDate(CDate("2012-01-01 23:00:00"))` 129699324000000000 を返します。

#### PadLeft

**説明:** PadLeft 関数は、指定した埋め込み文字を使用して、指定した長さになるまで左側に文字列を埋め込みます。

**構文 :**`str PadLeft(str string, num length, str padCharacter)`

- string: 埋め込む文字列。
- length:文字列の必要な長さを表す整数。
- padCharacter:埋め込み文字として使用する 1 文字で構成された文字列。

**備考:**

- 文字列の長さが length 未満の場合、長さが length と等しくなるまで文字列の左端に padCharacter が繰り返し追加されます。
- PadCharacter には空白文字を指定できますが、null 値を指定することはできません。
- 文字列の長さが length 以上の場合、文字列は変更されない状態で返されます。
- 文字列に length 以上の長さがある場合、文字列と同一の文字列が返されます。
- 文字列の長さが length 未満の場合、padCharacter が埋め込まれた文字列が含まれる、必要な長さの新しい文字列が返されます。
- string が null の場合、この関数は空の文字列を返します。

**例:**`PadLeft("User", 10, "0")` "000000User" を返します。

#### PadRight

**説明:** PadRight 関数は、指定した埋め込み文字を使用して、指定した長さになるまで右側に文字列を埋め込みます。

**構文 :**`str PadRight(str string, num length, str padCharacter)`

- string: 埋め込む文字列。
- length:文字列の必要な長さを表す整数。
- padCharacter:埋め込み文字として使用する 1 文字で構成された文字列。

**備考:**

- 文字列の長さが length 未満の場合、長さが length と等しくなるまで文字列の右端に padCharacter が繰り返し追加されます。
- PadCharacter には空白文字を指定できますが、null 値を指定することはできません。
- 文字列の長さが length 以上の場合、文字列は変更されない状態で返されます。
- 文字列に length 以上の長さがある場合、文字列と同一の文字列が返されます。
- 文字列の長さが length 未満の場合、padCharacter が埋め込まれた文字列が含まれる、必要な長さの新しい文字列が返されます。
- string が null の場合、この関数は空の文字列を返します。

**例:**`PadRight("User", 10, "0")` "User000000" を返します。

#### PCase

**説明:** PCase 関数は、文字列内のスペースで区切られた単語の最初の文字を大文字に変換し、その他のすべての文字を小文字に変換します。

**構文 :**`String PCase(string)`

**備考:**

- 現在この関数では、頭字語などの大文字のみで構成された単語を変換する際に、大文字と小文字を正しく区別することができません。

**例:**`PCase("TEsT")` "test" を返します。

`PCase(LCase("TEST"))` "Test" を返します。

#### RandomNum

**説明:** RandomNum 関数は、指定した範囲内の乱数を返します。

**構文 :**`num RandomNum(num start, num end)`

- start: 生成するランダムな値の下限を指定する数値
- end: 生成するランダムな値の上限を指定する数値

**例:**`Random(100,999)` 734 などを返します。

#### RemoveDuplicates

**説明:** RemoveDuplicates 関数は複数値の文字列を受け取り、各値が一意になるように処理します。

**構文 :**`mvstr RemoveDuplicates(mvstr attribute)`

**例:**`RemoveDuplicates([proxyAddresses])` 重複する値がすべて削除された、校正済みの proxyAddress 属性を返します。

#### 取り替える

**説明:** Replace 関数は、見つかった文字列をすべて別の文字列に置き換えます。

**構文 :**`str Replace(str string, str OldValue, str NewValue)`

- string:値を置換する文字列。
- OldValue:検索し、置換される文字列。
- NewValue:置換する文字列。

**備考:** この関数は次の特殊なモニカーを認識します。

- \n – 新しい行
- \r – キャリッジ リターン
- \t – タブ

**例:**`Replace([address],"\r\n",", ")` CRLF をコンマとスペースで置き換え、"One Microsoft Way, Redmond, WA, USA" などの文字列にします。

#### ReplaceChars

**説明:** ReplaceChars 関数は、ReplacePattern 文字列に見つかったすべての文字を置き換えます。

**構文 :**`str ReplaceChars(str string, str ReplacePattern)`

- string:文字を置換する文字列。
- ReplacePattern: 置換する文字のディクショナリが含まれる文字列。

形式は {source1}:{target1},{source2}:{target2},{sourceN},{targetN} です。source は検索対象の文字、target は置換する文字列です。

**備考:**

- この関数は定義した source の見つかった位置を受け取り、target と置き換えます。
- source は、厳密に 1 文字 (unicode) である必要があります。
- source は、空にすることも 2 文字以上にすることもできません (解析エラー)。
- target は、ö:oe、 β:ss などの複数の文字を持つことができます。
- target を空にして、文字を削除する必要があることを指定できます。
- source は、大文字と小文字を区別し、完全に一致する必要があります。
- 「,」(コンマ) と「:」(コロン) は予約文字であり、この関数を使用して置き換えることはできません。
- ReplacePattern 文字列内のスペースやその他の空白文字は無視されます。

**例:**`%ReplaceString% = ’:,Å:A,Ä:A,Ö:O,å:a,ä:a,ö,o`

`ReplaceChars("Räksmörgås",%ReplaceString%)` Raksmorgas を返します。

`ReplaceChars("O’Neil",%ReplaceString%)` "ONeil" を返します。単一引用符を削除するように定義されています。

#### はい

**説明:** Right 関数は文字列の右端から数えて指定した文字数分の文字を返します。

**構文 :**`str Right(str string, num NumChars)`

- string: 返される文字を含む文字列
- NumChars: 文字列の右端から数えて返される文字数を指定する数値

**備考:** string の末尾から数えて numChars 文字分の文字が返されます。

文字列内の最後の numChars 文字分の文字を含む文字列。

- numChars = 0 の場合、空の文字列を返します。
- numChars &lt; 0 の場合、入力文字列を返します。
- string が null の場合、空の文字列を返します。

文字列に含まれる文字数が NumChars に指定した数より少ない場合は、文字列と同一の文字列が返されます。

**例:**`Right("John Doe", 3)` "Doe" を返します。

#### RTrim

**説明:** RTrim 関数は文字列の末尾の空白文字を削除します。

**構文 :**`str RTrim(str value)`

**例:**`RTrim(" Test ")` " Test" を返します。

#### 選択する

**説明:** 指定された関数に基づいて、複数値の属性 (または式の出力) 内のすべての値を処理します。

**構文 :**`mvattr Select(variable item, mvattr attribute, func function)``mvattr Select(variable item, exp expression, func function)`

- item:複数値の属性内の要素を表します
- attribute: 複数値の属性
- expression: 値のコレクションを返す式
- condition: 属性内の項目を処理できる任意の関数

**例:**`Select($item,[otherPhone],Replace($item,"-",""))` ハイフン (-) の削除後に、複数値の属性 otherPhone 内のすべての値を返します。

#### 割る

**説明:** Split 関数は区切り記号で区切られた文字列を受け取り、複数値の文字列にします。

**構文 :**`mvstr Split(str value, str delimiter)``mvstr Split(str value, str delimiter, num limit)`

- value: 分離する区切り文字が含まれる文字列。
- delimiter: 区切り記号として使用される 1 文字。
- limit: 返すことができる値の最大数。

**例:**`Split("SMTP:john.doe@contoso.com,smtp:jd@contoso.com",",")` proxyAddress 属性に有用な 2 つの要素が含まれる複数値の文字列を返します。

#### StringFromGuid

**説明:** StringFromGuid 関数は、バイナリ GUID を受け取り、文字列に変換します。

**構文 :**`str StringFromGuid(bin GUID)`

#### StringFromSid

**説明:** StringFromSid 関数は、セキュリティ識別子が含まれるバイト配列を文字列に変換します。

**構文 :**`str StringFromSid(bin ObjectSID)`

#### スイッチ

**説明:** Switch 関数は、条件の評価結果に基づいて 1 つの値を返すために使用します。

**構文 :**`var Switch(exp expr1, var value1[, exp expr2, var value … [, exp expr, var valueN]])`

- expr:評価する必要のあるバリアント型の式。
- value: 対応する式が True の場合に返される値。

**備考:** Switch 関数の引数リストは、式と値のペアで構成されます。 式は左から右に評価され、評価結果が True になる最初の式に関連付けられている値が返されます。 各部分が正しくペアリングされていないと、実行時エラーが発生します。

たとえば、expr1 が True の場合、Switch は value1 を返します。 たとえば、expr1 が False でも expr2 が True の場合、Switch は value2 を返します。

次の場合、Switch は Nothing を返します。

- すべての式が True でない場合。
- 最初の True 式に、Null である対応する値がある場合。

Switch は、返される式が 1 つであってもすべての式を評価します。 このため、望ましくない影響が発生しないよう注意する必要があります。 たとえば、式の評価結果が 0 除算のエラーの場合、エラーが発生します。

値には、カスタム文字列を返す Error 関数を指定することもできます。

**例:**`Switch([city] = "London", "English", [city] = "Rome", "Italian", [city] = "Paris", "French", True, Error("Unknown city"))` 一部の主要都市で話される言語を返します。それ以外の場合はエラーを返します。

#### トリミング

**説明:** Trim 関数は、文字列の先頭と末尾の空白文字を削除します。

**構文 :**`str Trim(str value)`

**例:**`Trim(" Test ")` "test" を返します。

`Trim([proxyAddresses])` proxyAddress 属性の値ごとに先頭と末尾の空白文字を削除します。

#### UCase

**説明:** UCase 関数は文字列内のすべての文字を大文字に変換します。

**構文 :**`str UCase(str string)`

**例:**`UCase("TeSt")` "test" を返します。

#### どこ

**説明:** 特定の条件に基づいて、複数値の属性 (または式の出力) の値のサブセットを返します。

**構文 :**`mvattr Where(variable item, mvattr attribute, exp condition)``mvattr Where(variable item, exp expression, exp condition)`

- item:複数値の属性内の要素を表します
- attribute: 複数値の属性
- condition: 評価結果が true または false になる任意の式
- expression: 値のコレクションを返す式

**例:**`Where($item,[userCertificate],CertNotAfter($item)>Now())` 有効期限が切れていない、複数値の属性 userCertificate 内の証明書の値を返します。

#### 次で置換します

**説明:** With 関数は、複雑な式の中に 1 回以上現れる部分式を表す変数を使用することで、複雑な式を簡略化する方法となります。

**構文**`With(var variable, exp subExpression, exp complexExpression)`：

- variable:部分式を表します。
- subExpression: 変数によって表される部分式。
- complexExpression:複雑な式。

**例:**`With($unExpiredCerts,Where($item,[userCertificate],CertNotAfter($item)>Now()),IIF(Count($unExpiredCerts)>0,$unExpiredCerts,NULL))` 上の記述は下の記述と機能的に同等です。`IIF (Count(Where($item,[userCertificate],CertNotAfter($item)>Now()))>0, Where($item,[userCertificate],CertNotAfter($item)>Now()),NULL)` userCertificate 属性内の、期限が切れていない証明書の値のみが返されます。

#### ワード

**説明:** Word 関数は、使用する区切り記号と返す単語の番号を表すパラメーターに基づいて、文字列内に含まれる単語を返します。

**構文 :**`str Word(str string, num WordNumber, str delimiters)`

- string: 返される単語を含む文字列。
- WordNumber: 返すべき単語の番号を指定する数値。
- delimiters: 単語を識別するために使用される区切り記号を表す文字列

**備考:** delimiters 内のいずれかの文字で区切られた string 内の各文字列が、単語として識別されます。

- 数値 &lt; 1 の場合、空の文字列を返します。
- string が null の場合、空の文字列を返します。

string に含まれる単語の数が指定より少ないか、区切り記号文字で識別されるどの単語も string に含まれていない場合は、空の文字列が返されます。

**例:**`Word("The quick brown fox",3," ")` "brown" を返します。

`Word("This,string!has&many separators",3,",!&#")` "has" を返します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-tls-enforcement"} -->
## Microsoft Entra Connect: Microsoft Entra Connect に対する TLS 1.2 の強制 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: トランスポート層セキュリティ (TLS) 1.2 のみを使用するように Microsoft Entra Connect サーバーを強制する方法について説明します。

重要

この要件は、バージョン 2.3.20.0 から 2.4.129.0 にのみ適用されます。 Entra Connect バージョン 2.4.131.0 以降では、インストールまたはアップグレード前に TLS 強制は必要ありません。

トランスポート層セキュリティ (TLS) プロトコル バージョン 1.2 は、セキュリティで保護された通信を提供するように設計された暗号化プロトコルです。 TLS プロトコルの主な目的は、プライバシーとデータの整合性を提供することです。 TLS では、[RFC 5246](https://tools.ietf.org/html/rfc5246) で定義されているバージョン 1.2 で、多くの反復処理が実行されています。 Microsoft Entra Connect のバージョン 1.2.65.0 以降では、Azure との通信に対して TLS 1.2 のみの使用が完全にサポートされています。 この記事では、Microsoft Entra Connect サーバーで強制的に TLS 1.2 のみを使用する方法に関する情報を提供します。

### レジストリを更新する

Microsoft Entra Connect サーバーで強制的に TLS 1.2 のみを使用するには、Windows サーバーのレジストリを更新する必要があります。 Microsoft Entra Connect サーバーで次のレジストリ キーを設定します。

重要

レジストリを更新したら、Windows サーバーを再起動して変更を有効にする必要があります。

#### TLS 1.2 を有効にする

- [HKEY\_LOCAL\_MACHINE\SOFTWARE\WOW6432Node\Microsoft\.NETFramework\v4.0.30319]
    - "SystemDefaultTlsVersions"=dword:00000001
    - "SchUseStrongCrypto"=dword:0000001
- [HKEY\_LOCAL\_MACHINE\SOFTWARE\Microsoft\.NETFramework\v4.0.30319]
    - "SystemDefaultTlsVersions"=dword:00000001
    - "SchUseStrongCrypto"=dword:00000001
- [HKEY\_LOCAL\_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server]
    - "Enabled"=dword:00000001
- [HKEY\_LOCAL\_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server]
    - "DisabledByDefault"=dword:00000000
- [HKEY\_LOCAL\_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client]
    - "Enabled"=dword:00000001
- [HKEY\_LOCAL\_MACHINE\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client]
    - "DisabledByDefault"=dword:00000000

#### TLS 1.2 をチェックするための PowerShell スクリプト

Microsoft Entra Connect サーバーで現在の TLS 1.2 設定をチェックするには、次の PowerShell スクリプトを使用できます。

```powershell

Function Get-ADSyncToolsTls12RegValue
{
    [CmdletBinding()]
    Param
    (
        # Registry Path
        [Parameter(Mandatory=$true,
                   Position=0)]
        [string]
        $RegPath,

        # Registry Name
        [Parameter(Mandatory=$true,
                   Position=1)]
        [string]
        $RegName
    )
    $regItem = Get-ItemProperty -Path $RegPath -Name $RegName -ErrorAction Ignore
    $output = "" | select Path,Name,Value
    $output.Path = $RegPath
    $output.Name = $RegName

    If ($regItem -eq $null)
    {
        $output.Value = "Not Found"
    }
    Else
    {
        $output.Value = $regItem.$RegName
    }
    $output
}

$regSettings = @()
$regKey = 'HKLM:\SOFTWARE\WOW6432Node\Microsoft\.NETFramework\v4.0.30319'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'SystemDefaultTlsVersions'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'SchUseStrongCrypto'

$regKey = 'HKLM:\SOFTWARE\Microsoft\.NETFramework\v4.0.30319'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'SystemDefaultTlsVersions'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'SchUseStrongCrypto'

$regKey = 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'Enabled'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'DisabledByDefault'

$regKey = 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'Enabled'
$regSettings += Get-ADSyncToolsTls12RegValue $regKey 'DisabledByDefault'

$regSettings

```

適切な TLS 1.2 構成を示す出力の例

[Image: 画像]

#### TLS 1.2 を有効にする PowerShell スクリプト

次の PowerShell スクリプトを使用して、Microsoft Entra Connect サーバーで TLS 1.2 を有効にすることができます。

```powershell

If (-Not (Test-Path 'HKLM:\SOFTWARE\WOW6432Node\Microsoft\.NETFramework\v4.0.30319'))
{
    New-Item 'HKLM:\SOFTWARE\WOW6432Node\Microsoft\.NETFramework\v4.0.30319' -Force | Out-Null
}
New-ItemProperty -Path 'HKLM:\SOFTWARE\WOW6432Node\Microsoft\.NETFramework\v4.0.30319' -Name 'SystemDefaultTlsVersions' -Value '1' -PropertyType 'DWord' -Force | Out-Null
New-ItemProperty -Path 'HKLM:\SOFTWARE\WOW6432Node\Microsoft\.NETFramework\v4.0.30319' -Name 'SchUseStrongCrypto' -Value '1' -PropertyType 'DWord' -Force | Out-Null

If (-Not (Test-Path 'HKLM:\SOFTWARE\Microsoft\.NETFramework\v4.0.30319'))
{
    New-Item 'HKLM:\SOFTWARE\Microsoft\.NETFramework\v4.0.30319' -Force | Out-Null
}
New-ItemProperty -Path 'HKLM:\SOFTWARE\Microsoft\.NETFramework\v4.0.30319' -Name 'SystemDefaultTlsVersions' -Value '1' -PropertyType 'DWord' -Force | Out-Null
New-ItemProperty -Path 'HKLM:\SOFTWARE\Microsoft\.NETFramework\v4.0.30319' -Name 'SchUseStrongCrypto' -Value '1' -PropertyType 'DWord' -Force | Out-Null

If (-Not (Test-Path 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server'))
{
    New-Item 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server' -Force | Out-Null
}
New-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server' -Name 'Enabled' -Value '1' -PropertyType 'DWord' -Force | Out-Null
New-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Server' -Name 'DisabledByDefault' -Value '0' -PropertyType 'DWord' -Force | Out-Null

If (-Not (Test-Path 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client'))
{
    New-Item 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client' -Force | Out-Null
}
New-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client' -Name 'Enabled' -Value '1' -PropertyType 'DWord' -Force | Out-Null
New-ItemProperty -Path 'HKLM:\SYSTEM\CurrentControlSet\Control\SecurityProviders\SCHANNEL\Protocols\TLS 1.2\Client' -Name 'DisabledByDefault' -Value '0' -PropertyType 'DWord' -Force | Out-Null

Write-Host 'TLS 1.2 has been enabled. You must restart the Windows Server for the changes to take affect.' -ForegroundColor Cyan

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-user-privacy"} -->
## Microsoft Entra Connect とユーザーのプライバシー - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-user-privacy
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このドキュメントでは、Microsoft Entra Connect で GDPR コンプライアンスを取得する方法について説明します。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

注

この記事では、Microsoft Entra Connect とユーザーのプライバシーについて説明します。 Microsoft Entra Connect Health とユーザーのプライバシーについては、こちらの記事を参照 [してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-health-user-privacy)。

次の 2 つの方法で、Microsoft Entra Connect のインストールのユーザー プライバシーを向上させます。

1. 要求に応じて、ユーザーのデータを抽出し、インストールからそのユーザーからデータを削除します
2. 48時間以上データを保存しないようにしてください。

Microsoft Entra Connect チームは、実装と保守がはるかに簡単であるため、2 番目のオプションをお勧めします。

Microsoft Entra Connect Sync サーバーには、次のユーザー プライバシー データが格納されます。

1. **Microsoft Entra Connect データベース**内の人物に関するデータ
2. ユーザーに関する情報を含む **可能性がある Windows イベント ログ** ファイル内のデータ
3. **Microsoft Entra Connect インストール ログ ファイル**内の個人に関するデータ

Microsoft Entra Connect のお客様は、ユーザー データを削除するときに次のガイドラインを使用する必要があります。

1. Microsoft Entra Connect インストール ログ ファイルを含むフォルダーの内容を、少なくとも 48 時間ごとに定期的に削除します
2. この製品では、イベント ログを作成することもできます。 イベント ログログの詳細については、 [こちらのドキュメント](https://learn.microsoft.com/ja-jp/windows/win32/wes/windows-event-log)を参照してください。

ユーザーに関するデータは、元のソース システムからそのユーザーのデータが削除されると、Microsoft Entra Connect データベースから自動的に削除されます。 GDPR に準拠するために管理者からの具体的なアクションは必要ありません。 ただし、Microsoft Entra Connect データは、少なくとも 2 日ごとにデータ ソースと同期する必要があります。

### Microsoft Entra Connect インストール ログ ファイル フォルダーの内容を削除する

**PersistedState.Xml** ファイルを除き、**c:\programdata\aadconnect** フォルダーの内容を定期的に確認して削除します。 このファイルは、Microsoft Entra Connect の以前のインストールの状態を維持し、アップグレード インストールが実行されるときに使用されます。 このファイルにはユーザーに関するデータは含まれず、削除しないでください。

Von Bedeutung

PersistedState.xml ファイルは削除しないでください。 このファイルにはユーザー情報が含まれておりず、以前のインストールの状態が維持されます。

Windows エクスプローラーを使用してこれらのファイルを確認および削除するか、次のようなスクリプトを使用して必要なアクションを実行できます。

```
$Files = ((Get-ChildItem -Path "$env:programdata\aadconnect" -Recurse).VersionInfo).FileName
Foreach ($file in $files) {
If ($File.ToUpper() -ne "$env:programdata\aadconnect\PERSISTEDSTATE.XML".toupper()) # Do not delete this file
    {Remove-Item -Path $File -Force}
    } 
```

#### このスクリプトを 48 時間ごとに実行するようにスケジュールする

スクリプトを 48 時間ごとに実行するようにスケジュールするには、次の手順に従います。

1. **拡張子が .PS1** のファイルにスクリプトを保存し、コントロール パネルを開き、[**システムとセキュリティ**] をクリックします。 [Image: システム]
2. [管理ツール] 見出しの下にある [ **タスクのスケジュール**] をクリックします。 [Image: タスク]
3. タスク スケジューラで、[**タスク スケジュール ライブラリ**] を右クリックし、[**基本タスクの作成**]をクリックします。
4. 新しいタスクの名前を入力し、[ **次へ**] をクリックします。
5. タスク トリガーの **[毎日** ] を選択し、[ **次へ**] をクリックします。
6. 繰り返しを **2 日** に設定し、[ **次へ**] をクリックします。
7. アクションとして [ **プログラムの開始** ] を選択し、[ **次へ**] をクリックします。
8. [プログラム/スクリプト] ボックスに **「PowerShell** 」と入力し、[引数の **追加 ] (省略可能)** というラベルのボックスに、先ほど作成したスクリプトの完全なパスを入力して、[ **次へ**] をクリックします。
9. 次の画面には、作成しようとしているタスクの概要が表示されます。 値を確認し、[ **完了]** をクリックしてタスクを作成します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-version-history"} -->
## Microsoft Entra Connect: バージョン リリース履歴 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history
- Service: entra-id / hybrid-connect
- Article date: 2026-09-23
- Summary: この記事では、Microsoft Entra Connect と Azure AD Sync のすべてのリリースの一覧を示します。

Microsoft Entra チームは、Microsoft Entra Connect を定期的に更新し、新機能を提供しています。 すべての追加がすべての対象ユーザーに適用されるわけではありません。

この記事は、リリースされたバージョンとそれらのバージョンの変更を追跡するのに役立ちます。

重要

**必須のアップグレードが必要です。**Connect Sync Microsoft Entraバージョン 2.6.84.0 以降にアップグレードし、2027 年 4 月 7 日までにアプリケーション ベースの認証を構成します。 レガシ認証は廃止され、これらの要件が満たされていない場合、同期サービスはこの日以降動作を停止します。

同期が停止した場合は、最新バージョンにアップグレードし、サービスを復元するようにアプリケーション ベースの認証を構成します。 Microsoft Entra Connect Sync .msi インストール ファイルは、 [Microsoft Entra Admin Center](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) でのみ使用できます。 .NET Framework 4.7.2 や TLS 1.2 などの最小要件を満たしていることを確認します。

### 既知の問題: miiserver.exe.config が以前に変更された場合、アップグレード後に同期が失敗する

対象

- Microsoft Entra Connect 2.5.190.0
- Microsoft Entra Connect 2.6.1.0

#### 問題点

Microsoft Entra Connect をアップグレードした後、 `miiserver.exe.config` ファイルが以前に変更された場合、同期が失敗する可能性があります。

#### 症状

`Synchronization fails after upgrade with the following error: System.IO.FileLoadException: Could not load file or assembly 'System.Diagnostics.DiagnosticSource, Version=6.0.0.1' or one of its dependencies. The located assembly's manifest definition does not match the assembly reference. (Exception from HRESULT: 0x80131040)`

#### 原因

アップグレード中、Microsoft Entra Connect は、 `miiserver.exe.config` が変更されたことを検出し、ファイルを更新しません。 これにより、同期に必要な依存関係バインディングが不足します。 このシナリオは、構成ファイルを手動で更新する回避策として、FIPS 対応環境でパスワード ハッシュ同期 (PHS) をサポートするための以前のガイダンスに基づいてファイルが変更されたときに確認されました。

1. 移動先: \Microsoft Azure AD Sync\Bin %programfiles%
2. `miiserver.exe.config`をバックアップします。
3. `miiserver.exe.config`開き、assemblyBinding セクション内に次のエントリを追加します。`<dependentAssembly> <assemblyIdentity name="System.Diagnostics.DiagnosticSource" publicKeyToken="cc7b13ffcd2ddd51" culture="neutral" /> <bindingRedirect oldVersion="0.0.0.0-8.0.0.0" newVersion="8.0.0.0" />  </dependentAssembly>`
4. ファイルを保存します。
5. ADSync サービスを再起動します。

### 最新バージョンをお探しの場合

Microsoft Entra Connect サーバーは、サポートされているすべてのバージョンから最新バージョンでアップグレードできます。

[Microsoft Entra 管理センターの Microsoft Entra](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) Connect の [**管理**] タブから最新バージョンをダウンロードできます **。[作業の開始]** ページ。

URL `https://aka.ms/aadconnectrss` をコピーして、お使いの [Image: RSS フィード リーダー アイコン] フィード リーダーに貼り付け、更新内容を確認するためにこのページに再度アクセスするタイミングに関する通知を受け取るようにしてください。

次の表に、関連するトピックを示します。

| トピック | 詳細 |
| --- | --- |
| Microsoft Entra Connect からのアップグレード手順 | Microsoft Entra Connect リリースの[以前のバージョンから最新バージョンにアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)するさまざまな方法について説明します。 |
| 必要なアクセス許可 | 更新プログラムの適用に必要なアクセス許可については、「[Microsoft Entra Connect: アカウントとアクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions#upgrade)」を参照してください。 |

### 機能しない Microsoft Entra Connect 1.x バージョン

重要

すべての Microsoft Entra Connect Sync 1.x バージョンはサポートされておらず、同期は機能しません。 クラウド同期またはサポートされているバージョンの Microsoft Entra Connect 2.x をお使いのお客様は、引き続き完全に動作します。 すべての 1.x バージョンの廃止について詳しくは、「[Azure AD Connect V1 の使用停止](https://aka.ms/DecommissionAADConnectV1)」をご覧ください。

### Microsoft Entra Connect 2.x バージョンの廃止

重要

Microsoft Entra Connect Sync 2.x のバージョンは、新しいバージョンのリリース日から 12 か月後に廃止されます。 このポリシーは、2023 年 3 月 15 日に有効になりました。

新規インストールの場合は、常に最新バージョンをインストールしてください。 アップグレードの場合は、現在のバージョンが廃止される前に、必ず最新バージョンにアップグレードしてください。

| バージョン | サポート終了日 | リリース日 |
| --- | --- | --- |
| 2.3.2.0 | 2025 年 4 月 30 日 (バージョン 2.4.18.0 でリリースされたセキュリティ変更に合わせて) | 2023 年 12 月 12 日 |
| 2.3.6.0 | 2025 年 4 月 30 日 (バージョン 2.4.18.0 でリリースされたセキュリティ変更に合わせて) | 2024 年 2 月 21 日 |
| 2.3.8.0 | 2025 年 4 月 30 日 (バージョン 2.4.18.0 でリリースされたセキュリティ変更に合わせて) | 2024 年 4 月 1 日 |
| 2.3.20.0 | 2025 年 4 月 30 日 (バージョン 2.4.18.0 でリリースされたセキュリティ変更に合わせて) | 2024 年 7 月 15 日 |
| 2.4.18.0 | 2025 年 10 月 9 日 (2.4.21.0 のリリースから 12 か月後) | 2024 年 10 月 7 日 |
| 2.4.21.0 | 2025 年 11 月 15 日 (2.4.27.0 のリリースから 12 か月後) | 2024 年 10 月 9 日 |
| 2.4.27.0 | 2026 年 1 月 15 日 (2.4.129.0 のリリースから 12 か月後) | 2024 年 11 月 14 日 |
| 2.4.129.0 | 2026 年 3 月 27 日 (2.4.131.0 のリリースから 12 か月後) | 2025 年 1 月 15 日 |
| 2.4.131.0 | 2026 年 5 月 26 日 (2.5.3.0 のリリースから 12 か月後) | 2025 年 3 月 27 日 |
| 2.5.3.0 | 2026 年 7 月 31 日 (2.5.76.0 のリリースから 12 か月後) | 2025 年 5 月 27 日 |
| 2.5.76.0 | 2026 年 9 月 1 日 (2.5.79.0 のリリースから 12 か月後) | 2025 年 7 月 31 日 |
| 2.5.79.0 | 2026年10月23日(2.5.190.0リリースから12ヶ月後) | 2025 年 9 月 1 日 |
| 2.5.190.0 | 2027 年 2 月 2 日 (2.6.1.0 のリリースから 12 か月後) | 2025 年 11 月 19 日 |
| 2.6.1.0 | 2027 年 3 月 10 日 (2.6.3.0 リリースから 12 か月後) | 2026 年 2 月 2 日 |
| 2.6.3.0 | 2027 年 7 月 7 日 (2.6.84.0 のリリースから 12 か月後) | 2026 年 3 月 10 日 |
| 2.6.84.0 | 2027 年 9 月 16 日 (2.6.91.0 リリースから 12 か月後) | 2026 年 7 月 7 日 |
| 2.6.91.0 | 2027 年 9 月 23 日 (2.6.92.0 のリリースから 12 か月後) | 2026 年 9 月 16 日 |
| 2.6.92.0 |  | 2026 年 9 月 23 日 |

**他のバージョンはすべて、サポートされていません**

廃止されたバージョンの Microsoft Entra Connect を実行すると、予期せず動作が停止する可能性があります。 また、最新のセキュリティ修正プログラム、パフォーマンスの向上、トラブルシューティングおよび診断ツール、サービスの機能強化が受けられない場合があります。 サポートが必要な場合は、組織が必要とするレベルのサービスを受けられない場合があります。

バージョン 2.x の詳細と、この変更による影響については、Microsoft Entra Connect v2参照してください。

Microsoft Entra Connect を最新バージョンにアップグレードする方法については、「[Microsoft Entra Connect: 旧バージョンから最新バージョンにアップグレードする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)」を参照してください。

廃止されたバージョンのバージョン履歴情報については、[Microsoft Entra Connect: バージョンのリリース履歴アーカイブ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history-archive)に関する記事を参照してください。

注

Microsoft Entra Connect の新しいバージョンのリリースは、サービスの操作の機能性を確保するために、いくつかの品質管理手順が必要です。 このプロセスを進めている間、新しいリリースのバージョン番号とリリースの状態が更新され最新の状態を反映されます。

Microsoft Entra Connect のすべてのリリースが自動アップグレードに対応しているわけではありません。 リリースの状態により、リリースが自動アップグレードに対応しているか、ダウンロードにのみ対応しているかが分かります。 Microsoft Entra Connect サーバーに対して自動アップグレードが有効になっている場合、そのサーバーは、自動アップグレード用にリリースされた Microsoft Entra Connect の最新バージョンに自動的にアップグレードされます。 Microsoft Entra Connect のすべての構成が自動アップグレードの対象となっているわけではありません。

自動アップグレードは、すべての重要な更新プログラムとクリティカルな修正プログラムをユーザーにプッシュすることを意図しています。 必ずしも最新バージョンとは限りません。その理由は、すべてのバージョンにおいて、重大なセキュリティ問題に対する修正プログラムが必要になったり、含まれたりするわけではないからです。 (この例は多くの例の 1 つです)。重大な問題は、自動アップグレードによって提供される新しいバージョンで解決されます。 このような問題がない場合は、自動アップグレードを使用してプッシュアウトされる更新プログラムはありません。 一般に、最新の自動アップグレード バージョンを使用している場合は問題ありません。

すべての最新の機能と更新プログラムが必要な場合は、このページを確認し、必要なものをインストールします。

自動アップグレードの詳細については、「[Microsoft Entra Connect: 自動アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)」を参照してください。

### 2.6.92.0

重要

このリリースには、セキュリティ修正プログラムが含まれています。 できるだけ早くこのバージョンにアップグレードすることをお勧めします。

#### リリースの状態

2026 年 9 月 23 日: Microsoft Entra 管理センター経由でダウンロード用にリリースされました。 これは修正プログラムのリリースです。

#### バグ修正

- Microsoft Entra Connect ウィザードを使用してパススルー認証を有効にすると、ローカルにインストールされている Microsoft Entra Connect 認証エージェントの登録中に失敗する可能性があるバージョン 2.6.91.0 の問題を修正しました。

### 2.6.91.0

重要

Microsoft Graphアクセス許可が Microsoft Entra Connect に追加されました。 アプリ スコープの条件付きアクセス ポリシーを使用する場合は、Microsoft.Azure を対象とするポリシーを確認します。SyncFabric または Microsoft 365 Reporting Service。

#### リリースの状態

2026 年 9 月 16 日: Microsoft Entra 管理センター経由でダウンロード用にリリースされました。

#### 追加機能

- Microsoft Entra Connect Sync から Microsoft Entra Cloud Sync へのガイド付き移行ワークフローを追加しました。ワークフローには、構成評価、プロビジョニング エージェントのセットアップ、段階的なアクティブ化、検証が含まれます。 この機能は、Azureパブリック クラウドでのみ使用できます。
- パススルー認証、シームレス シングル サインオン、パスワード ライトバック、正常性エージェントの監視など、追加のソブリン クラウド環境のサポートが追加されました。

#### 更新された機能

- Microsoft Entra Connect セットアップ ウィザードのフィッシング対策認証が一般公開され、既定で有効になりました。 Windows Web アカウント マネージャープロンプトは、パスキー、FIDO2 セキュリティ キー、パスワードをサポートし、Microsoft Entra サービス間でサインイン セッションを再利用し、シームレスな単一 Sign-On Kerberos キーローテーションを保持します。
- スタンドアロン PowerShell モジュールを使用してシームレス シングル Sign-On を構成する場合は、`AzureADSSO.psd1`する前に`ADSync.psd1`をインポートする必要があります。 [詳細については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/cloud-sync/how-to-sso)。
- クラウド構成コマンドレットでは、明示的な `-AADUserName`は不要になります。 パラメーターを省略すると、Microsoft Entra Connect はコネクタ構成からサインイン ヒントを派生させ、対話型のサインイン プロンプトを開きます。 この動作は、 `Set-ADSyncAADCompanyFeature`、 `Set-ADSyncAADPasswordSyncState`、 `Enable-ADSyncExportDeletionThreshold`、 `Set-ADSyncScheduler`、および `Set-ADSyncDirSyncConfiguration`に適用されます。
- 同期Service Managerの [コンテナーの選択] ダイアログが読み取り専用になりました。 ダイアログを使用して、現在の選択内容を表示することもできます。 変更するには、Microsoft Entra接続ウィザードの [同期オプションのカスタマイズ] を使用します。 [詳細については、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-installation-wizard#customize-synchronization-options)。
- バンドルされた SQL Server 2022 LocalDB をバージョン 16.0.4250.1 から 16.0.4262.2 に更新しました。

#### バグ修正

- 既存の ADSync データベースとの接続Microsoft Entraインストールまたはアップグレードがエラー `0xE0474352`で失敗する可能性がある問題を修正しました。
- Microsoft Entra Connect をアップグレードしても、14.41 より前の Visual C++ 2015-2022 再頒布可能パッケージバージョンMicrosoftインストール済みバージョンが更新されない問題を修正しました。
- Windows PowerShell 文字起こしポリシーに無効な出力ディレクトリが含まれている場合に、Microsoft Entra Connect ウィザードが起動中に閉じる可能性がある問題を修正しました。
- ディレクトリ レプリカの遅延中に最後に使用可能なアプリケーション キーが削除される可能性がある、Application-Based 認証証明書のローテーションの問題を修正しました。
- コネクタのプロパティと一覧表示されたディレクトリ パーティションを開くと、同期Service Managerが予期せず閉じる可能性がある問題を修正しました。
- 多数のActive Directory コネクタを使用する構成で、表示領域外のコネクタ行に対して資格情報の変更が開かない問題を修正しました。
- ドメインと OU のフィルター処理ページで、以前に選択解除したドメインを展開すると、ウィザードの完了後にドメインを再選択し、ドメイン全体の同期を有効にできる問題を修正しました。
- バンドルされたサード パーティの依存関係と強化されたローカルの一時ファイルのアクセス許可と、コネクタ プロファイルイメージのダウンロードSharePoint複数のセキュリティ脆弱性を修正しました。

#### 既知の問題

- Microsoft Entra Connect ウィザードを使用してパススルー認証を有効にすると、ローカルにインストールされている Microsoft Entra Connect 認証エージェントの登録中に失敗することがあります。 この問題は 、バージョン 2.6.92.0 で修正されています。

### 2.6.84.0

重要

このリリースには、セキュリティ修正プログラムが含まれています。 できるだけ早くこのバージョンにアップグレードすることをお勧めします。

#### リリースの状態

2026 年 7 月 7 日: Microsoft Entra 管理センター経由でダウンロード用にリリースされました。

#### 追加機能

- Microsoft Entra Connect セットアップ ウィザード (プレビュー) でフィッシングに対する耐性のある認証方法のサポートを追加しました。 管理者は、Microsoft Entra Connect を構成するときに、Windows Web アカウント マネージャー (WAM) を介してパスキーと FIDO2 セキュリティ キーを使用してサインインできるようになりました。
- パススルー認証、シームレス シングル サインオン、パスワード ライトバック、Health Agent 監視など、フランスのソブリン クラウド環境のサポートが追加されました。

#### 更新された機能

- 構成ファイルに対する顧客の変更を保持するように自動アップグレード プロセスを改善しました。 以前は、自動アップグレードによって `miiserver.exe.config` ファイルが上書きされ、手動によるカスタマイズは破棄されます。 これで、システムは顧客の変更を新しい構成にマージし、適用する前に結果を検証します。
- トラステッド プラットフォーム モジュール (TPM) でサポートされる証明書を処理するための Application-Based 認証のセットアップ プロセスが改善されました。 これで、システムは証明書の署名機能を事前にテストし、TPM 署名の検証を正しく処理するようになりました。
- Microsoft Entra認証のセットアップが失敗したときに、接続セットアップ ウィザードがレガシ ディレクトリ同期アカウント Application-Based 自動的にフォールバックしなくなりました。 ウィザードがエラーで停止し、基になる問題を解決できるようになりました。"Microsoft Entra Connect では、このサーバーのアプリケーション ベースの認証を構成できませんでした。 セットアップを続行できません。
- Microsoft Entra Connect は、バックグラウンド同期中に既存のサーバーをレガシ ディレクトリ同期アカウントから Application-Based 認証に自動的に切り替えなくなりました。新規インストールでは、セットアップ中も引き続き Application-Based 認証が構成されます。 既存のサーバーを切り替えるには、ウィザードを実行し、[**アプリケーション ベースの認証をMicrosoft Entra IDに構成する**] を選択します。
- クラウド構成 (`Set-ADSyncAADCompanyFeature`、 `Set-ADSyncAADPasswordSyncState`) を変更する PowerShell コマンドレットでは、対話型管理者認証に明示的な `-AADUsername` が必要になりました。 セットアップ ウィザードでは、保存されたサービス資格情報ではなく、クラウド書き込みに対話型Microsoft Authentication Library (MSAL) 認証が使用されます。 アンインストール ウィザードで、クラウド構成をクリーンアップするための管理者資格情報の入力を求めるメッセージが表示されるようになりました。スキップされた場合は、ローカルクリーンアップが続行されます。
- パスワード ハッシュ同期 (PHS) の自己復旧を削除しました。 PHS では、バックグラウンドでクラウド機能フラグが自動的に再有効化されなくなりました。 PHS クラウド機能フラグが無効になっている場合、管理者は明示的に再有効化する必要があります。
- バンドルされた MSAL をバージョン 4.64.1 から 4.83.3 に更新しました。
- バンドルされた SQL LocalDB を SQL Server 2019 から SQL Server 2022 にアップグレードしました。
- Visual C++ 再頒布可能パッケージをバージョン 12 (2013) からバージョン 14.42.34438 (2015-2022) にアップグレードしました。
- Visual C++ 2013 再頒布可能パッケージの依存関係を削除しました。

#### バグ修正

- PowerShell 診断 HTML レポートの表示に関する問題を修正しました。
- 同期Service Managerメタバース検索の問題を修正しました。
- TPM が有効な署名を生成できない場合にソフトウェア ベースの証明書にフォールバックすることで、準拠していない TPM ファームウェアを持つサーバーでの Application-Based 認証のセットアップが改善されました。
- 構成中に必要なプロファイル パラメーターが設定されていないため、汎用 SQL (GSQL) コネクタ プロファイルの作成に失敗する問題を修正しました。
- フランスのクラウド環境で アプリケーション プロキシ クラウド名が正しく解決されず、"EnvironmentName 属性が無効です" エラーでパススルー認証の登録が失敗する問題を修正しました。
- 検出エンドポイント API によって中国のクラウド インスタンス名が正しく解決されず、クラウド インスタンスの検出が失敗する可能性がある問題を修正しました。
- 管理者アクション監査ログで、同期規則の変更に対して実際の管理者がアクションを実行するのではなく、サービス アカウント ID がキャプチャされる問題を修正しました。
- バンドルされたサード パーティの依存関係の複数のセキュリティ脆弱性を修正しました。

#### 既知の問題

- 既存の ADSync データベースを使用して Microsoft Entra Connect バージョン 2.6.84.0 にインストールまたはアップグレードすると、エラー `0xE0474352`で失敗する可能性があります。 既存のデータベースをインストールまたはアップグレードするには、 バージョン 2.6.91.0 以降を使用します。
- **コネクタのプロパティ**を開き、ディレクトリ パーティションを一覧表示すると、同期Service Managerが予期せず閉じることがあります。 この問題は 、バージョン 2.6.91.0 で修正されています。
- Microsoft Entra接続ウィザードを再度開き、[ドメインと OU のフィルター] ページで完全に選択解除されたドメインを展開すると、ウィザードでドメインが再度選択される場合があります。 ウィザードを完了すると、ドメイン全体に対して同期が有効になっている可能性があります。 この問題は 、バージョン 2.6.91.0 で修正されています。

重要

バージョン 2.6.79.0 はダウンロードできなくなりました。 リリース後に問題が特定され、インストーラーが呼び戻されました。 このバージョンをインストールしたお客様は、このバージョンをアンインストールし、Microsoft Entra Connect Sync の利用可能な最新バージョン (2.6.92.0) をインストールする必要があります。

### 2.6.3.0

#### リリースの状態

2026 年 3 月 10 日: Microsoft Entra 管理センターからダウンロード用にリリースされました。 これは修正プログラムのリリースです。 自動アップグレードでは、2026 年 3 月 11 日以降、既存のインストールをこのビルドにアップグレードし、複数のフェーズで完了します。

#### バグの修正

- 自動アップグレードによって Microsoft Entra Connect サーバーが予期せず停止する可能性がある 既知の問題 を修正しました。 自動アップグレードでは、 `miiserver.exe.config` と `miisclient.exe.config` 構成ファイルへの変更が検出され、それらのサーバーでの自動アップグレードがスキップされるようになりました。 これらの構成ファイルを手動でアップグレードして以前に変更した場合、インストールエラーが発生する可能性があります。 この問題を解決するには、 既知の問題に関するセクションを参照してください。

### 2.6.1.0

#### リリースの状態

2026 年 2 月 2 日: Microsoft Entra 管理センター経由でダウンロード用にリリースされました。 既存のインストールは、2026 年 2 月 9 日以降、このビルドに自動アップグレードされ、複数のフェーズで実行されます。

#### バグの修正

- Synchronization Service Manager UI を使用して Microsoft Entra ID Connector の構成を変更すると認証パラメーター Application-Based 削除され、ウィザードと証明書のローテーションエラーが発生する問題を修正しました。 以前のバージョンでは、Synchronization Service Manager UI を使用しないことをお勧めします。
- パスワード ライトバック サービスが無効になっているか、Entra ID テナントから削除されたときにステージング モードの構成が失敗する問題を修正しました。
- Microsoft Entra Connect によって管理される証明書の既定の証明書の有効期間は 90 日になりました。 証明書の更新しきい値は、固定の 30 日間の期間ではなく、パーセンテージベースの有効期間消費量 (70%) を使用するように更新されました。 証明書の更新プロセスは、有効期間の 70% が経過した後、固定の 30 日間隔ではなく更新を試みます。
- Windows イベント ログとトレース ログの Application-Based 認証ログが強化され、認証エラーの診断に役立ちます。
- スクリーン リーダーによってヘルプ アイコンが正しく通知されず、複数行のヘルプ テキスト全体がコントロール名として読み上げられるという、接続ウィザードのアクセシビリティの問題を修正しました。 ヘルプ コントロールで正しい名前とロールが公開され、エクスペリエンスが向上しました。
- キーボード ナビゲーションを使用してヘルプ ポップアップ内のハイパーリンクに到達できないキーボード アクセシビリティの問題を修正しました。 これで、キーボードのみを使用してリンクにアクセスできるようになりました。

#### 既知の問題

参照: miiserver.exe.config が以前に変更された場合、アップグレード後に同期が失敗する

### 2.5.190.0

注

このバージョンでは、Synchronization Service Manager UI を使用しないでください。 これを行うと、Microsoft Entra Connect ウィザードと証明書の自動更新が失敗する可能性があります。 この問題は、バージョン 2.6.1.0 で修正されています。

#### リリースの状態

2025年11月19日:Microsoft Entra管理センターからダウンロード開始。

#### 既知の問題

参照: miiserver.exe.config が以前に変更された場合、アップグレード後に同期が失敗する

#### 追加機能

- **AAD Connector V2 API 強制**:デフォルトのコネクタAPIバージョンは現在V2です。 以前のV1コネクタAPIの使用はもはやサポートされていません。

#### バグ修正

- Trusted Platform Module(TPM)とMicrosoft Authentication Library(MSAL)で Application-Based 認証が失敗した問題を修正しました。 この修正により、MSALのデフォルトの署名方法との互換性が保証されます。
- 設定ウィザードで「このディレクトリのディレクトリ同期が現在同期有効で同期状態が不一致」というエラーが発生し、DirSync Statusが「その他」になっている場合にエラーが発生しました。
- アプリケーションベース認証の証明書更新の閾値は30日に更新されました。 Entraマネージド証明書は、有効期限が30日以内に自動的に更新されます。
- Exchange属性のクラウド管理でエクスポートエラーが発生し `ExchangeManagedAttributesUpdateNotAllowed`エラーが発生しました。

### 2.5.79.0

注

このバージョンでは、Synchronization Service Manager UI を使用しないでください。 これを行うと、Microsoft Entra Connect ウィザードと証明書の自動更新が失敗する可能性があります。 この問題は、バージョン 2.6.1.0 で修正されています。

#### リリースの状態

2025 年 9 月 1 日: Microsoft Entra 管理センター経由でダウンロード用にリリースされました。 既存のインストールは、2025 年 9 月 4 日からこのビルドに自動アップグレードされ、複数のフェーズで実行されます。

#### 追加された機能

- Application-Based 認証のセットアップ プロセスが改善され、TPM でサポートされる証明書が処理されました (トラステッド プラットフォーム モジュールで保護されている証明書については、「 [TPM とは」](https://learn.microsoft.com/ja-jp/windows/security/information-protection/tpm/trusted-platform-module-overview)を参照してください)。 これで、システムは証明書の署名機能を事前にテストし、TPM 署名が失敗した場合は自動的にソフトウェア ベースの証明書にフォールバックします。
- Application-Based 認証構成が証明書の作成後に失敗した場合に、証明書の自動削除を実装しました。 これにより、障害シナリオで未使用の証明書がサーバーに残るのを防ぎ、孤立した証明書の蓄積を回避することでセキュリティが向上します。

#### バグ修正

- セットアップ エラーの原因となっている FIPS 対応サーバーの問題を解決しました。 Application-Based 認証は、FIPS 準拠の暗号化アルゴリズムを使用して FIPS モードが有効になっているサーバーで正しく機能するようになりました。
    ヒント

    FIPS (Federal Information Processing Standards) モードは、機密データに暗号化アルゴリズムの使用を強制する Windows セキュリティ設定です。 FIPS モードが有効になっている場合は、FIPS に準拠したアルゴリズムのみを使用できるため、この修正により、厳密なセキュリティ標準を必要とする環境の互換性が保証されます。
- スケジューラが中断されたときに、証明書の自動ローテーションが誤ってアクティブとして報告される問題を修正しました。 自動ローテーション ロジックは、状態を示す前にスケジューラの状態をチェックし、現在の *構成の表示またはエクスポート ウィザード* に自動回転が有効になっているかどうかを正確に反映するようになりました。
- 証明書の自動操作のためにログに記録されていた不適切な管理者監査イベントを削除しました。 これらのバックグラウンド証明書アクションによって管理監査ログ エントリが生成されなくなり、監査証跡がクリーンになります (Entra Connect 同期監査ログには、管理者が開始した実際の変更のみが表示されます)。

### 2.5.76.0

注

このバージョンでは、Synchronization Service Manager UI を使用しないでください。 これを行うと、Microsoft Entra Connect ウィザードと証明書の自動更新が失敗する可能性があります。 この問題は、バージョン 2.6.1.0 で修正されています。

#### リリースの状態

2025 年 7 月 31 日: Microsoft Entra 管理センター経由でダウンロード用にリリースされました。 既存のインストールは、2025 年 8 月 14 日以降、このビルドに自動アップグレードされ、複数のフェーズで実行されます。

#### 追加された機能

- Microsoft Entra ID に対するアプリケーション ベースの認証が一般公開され、既定のオプションになります。 [「アプリケーション ID を使用した Microsoft Entra ID への認証」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/authenticate-application-id)参照してください。
- 管理者アクションのログ記録が一般公開され、Microsoft Entra Connect で行われたすべての管理変更に対して Windows 監査イベントが提供されるようになりました。 [Microsoft Entra Connect Sync での管理者イベントの監査を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/admin-audit-logging)参照してください。
- 管理者がオンプレミスの Active Directory グループを転送して、Microsoft Entra ID (パブリック プレビュー) を介して管理されるクラウドのみのグループに移行できるようにする、機関のグループ変換機能。 [グループ権限ソースの概要を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/concept-source-of-authority-overview)参照してください。

#### バグ修正

- 接続同期ウィザードでの Active Directory マルチドメイン シナリオに影響する子 OU の選択と選択解除に関する問題が修正されました。
- 更新中にフェデレーション ドメインの設定と MFA フラグのリセットが原因で、オンプレミスの ADFS MFA ではなく Azure MFA の設定を求められた問題が解決されました。
- 一部の Microsoft Entra Connect Sync インスタンスが見つからない場合にエージェント識別子が正しくソース化されていることを確認して、自動アップグレードを妨げる問題を解決しました。
- DirSync の状態が **PendingEnabled** の場合に、**このディレクトリのディレクトリ同期が現在無効になり、同期状態**エラーが発生する構成ウィザードの問題を修正しました

### 2.5.3.0

注

このバージョンでは、Synchronization Service Manager UI を使用しないでください。 これを行うと、Microsoft Entra Connect ウィザードと証明書の自動更新が失敗する可能性があります。 この問題は、バージョン 2.6.1.0 で修正されています。

#### リリースの状態

2025 年 5 月 27 日: Microsoft Entra 管理センター経由でダウンロード用にリリースされました。

#### 追加された機能

- 先進認証を有効にすると、お客様はセキュリティ強化のためにアプリケーション ベースの認証を構成できます (パブリック プレビュー)。 詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/authenticate-application-id)を参照してください

#### 更新された機能

- バンドルされた正常性エージェントをバージョン 4.5.2520.0 にアップグレードしました
- ダウンロード センターから Azure portal に移動してダウンロードする
- SSPR を有効にして PowerShell を使用してステージング モードを切り替えるときに、管理者の資格情報が必要になりました。
- PowerShell を使用して SSPR 構成を有効、無効、または削除するときに、管理者の資格情報が必要になりました。

### 2.4.131.0

#### リリースの状態

2025 年 3 月 27 日: ダウンロードと自動アップグレードのためにリリースされました。

注

自動アップグレードは、リリース日から 2025 年 4 月 15 日まで実行されます。 それまでに環境がアップグレードされていない場合は、自動アップグレードの試行が失敗し、 [手動アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version)を実行する必要があります。 自動アップグレードに失敗した理由については、 [アプリケーション イベント ログ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade#troubleshooting) を確認できます。

#### 更新された機能

- SchUseStrongCrypto レジストリ キーが有効になっているかどうかの前提条件チェックを削除しました。 このバージョンでは、既定で強力な暗号化を使用する .NET 4.7.2 が使用されています。

### 2.4.129.0

#### リリースの状態

2025 年 1 月 15 日: ダウンロードと自動アップグレードのリリース

#### 追加された機能

- Microsoft Entra Connect Sync で行われた管理者の変更をログに記録するために、Microsoft Entra Connect Sync の管理者イベントの監査が有効になりました。これには、UI と PowerShell スクリプトを使用して行われた変更が含まれます。 詳細については、Microsoft Entra Connect Sync (パブリック プレビュー) の監査管理者イベントの を参照してください。

#### バグ修正

- Azure AD コネクタで変更が行われ、Sync Service Manager UI に保存されている場合の SSPR 構成の削除を修正しました
- Entra Connect Sync のインストール中に実行されるグローバル管理者/ハイブリッド ID 管理者ロールと、Privileged Identity Management (PIM) を使用したグローバル管理者/ハイブリッド ID 管理者のユーザーの検証を修正しました。
- AD FS とのフェデレーション シナリオでの "登録されたプロトコル ハンドラーなし" エラーを修正しました。
- AD FS とのフェデレーション シナリオでの "証明書利用者は一意である必要があります (競合エラー)" エラーを修正しました。

### 2.4.27.0

#### リリースの状態

2024 年 11 月 14 日: ダウンロード対象としてリリース済み

#### 更新された機能

- Microsoft Entra Connect に付属する SQL 関連ドライバーが OLE DB [バージョン 18.7.4](https://learn.microsoft.com/ja-jp/sql/connect/oledb/release-notes-for-oledb-driver-for-sql-server#1874) に更新されました

#### バグ修正

- PIM が有効であることと、ユーザーがハイブリッド ID 管理者ロールを有効にしていることを確認するために、Privileged Identity Management (PIM)、Microsoft Entra ロール、および PIM for Groups に関する問題を修正しました。
- ADFS 以外のサーバーに Connect Sync がインストールされている場合に AD FS コマンドが失敗する問題を修正しました。

### 2.4.21.0

#### リリースの状態

2024 年 10 月 9 日: ダウンロード対象としてリリース済み

#### バグ修正

- 非商用クラウドでの認証の問題を修正しました。

### 2.4.18.0

#### リリースの状態

2024 年 10 月 7 日: ダウンロード用にリリース済み

#### 更新された機能

- 接続同期ウィザード **Microsoft Entra ID に接続** 手順では、ログイン ページにリダイレクトする前にパスワードは必要ありません。
- 既定のルールの更新:「onPremisesObjectIdentifier」属性が「**AD から入力 - ユーザー アカウントが有効化**」の同期規則に追加されました。 この規則を追加すると、同期エンジンは、次のようなシナリオで、有効になっているユーザーから **onPremisesObjectIdentifier** 属性を選択できます。
- 同じユーザーが異なるフォレスト間で表され、
- ユーザーがいずれかのフォレストで無効になっている
- 必要に応じて、カスタム規則の優先順位番号を 100 を超える値に設定できるレジストリ キーが導入されました。 最初の標準規則の優先順位は、HLKM:\SOFTWARE\Microsoft\Azure AD Connect\FirstStandardRulePrecedence **キーを使用して設定**、より多くのカスタム 規則を許可できます。 値が設定されていない場合、既定値として 100 が使用されます。
- Microsoft Entra ID と通信する ADSync PowerShell モジュールのコマンドレットで、`Add-ADSyncAADServiceAccount` や `Get-ADSyncExportDeletionThreshold` などの Microsoft Entra ID ログインが必要になりました

#### 使用停止された機能

- スキーマに対してオブジェクトを検証していた機能は非推奨となり、Synchronization Service Manager では利用できなくなりました。
- `/enableldap` コマンド ライン スイッチ (プレビュー機能) は非推奨となり、ウィザードの実行時には使用できなくなります。
- 従来の MSOnline PowerShell モジュールへの参照はすべて削除され、同等の Microsoft Graph API 呼び出しに置き換えられました。

#### その他

- .NET ランタイムの最小要件が 4.7.2 に上がりました。
- Microsoft Entra ID のブランド化に合わせたブランド化の更新プログラム。

#### バグ修正

- ウィザードの次の手順に進む前に、ドメインの検証を完了する必要があることを確認するため、ウィザードのエクスペリエンスが向上しました。
- フォレストのドメインのリストをフェッチするときのエラー メッセージが改善されました
- パスワード ライトバックを有効にした状態で既存のデータベースのインストールに互換性が失われるエラーが修正されました。
- NTLM が deny-all に設定されている場合、ADConnectivityTool モジュールで発生する可能性がある認証情報の問題が修正されました。
- エンタープライズ管理者を要求するときに発生する可能性があるローカライズ文字列に関するエラーが修正されました。
- ウィザードを再実行すると、正しい構成ではなく初期 OU 構成が表示される問題が修正されました。
- 特殊な Unicode 文字が原因で文字列が予想よりも長かった場合、同期中に発生する可能性があるエラーが修正されました。
- AD FS 構成のセットアップで null 証明書が原因でウィザードのインストールのハングを引き起こす可能性があるエラーが修正されました。
- サービス アカウントを取得しようとしたときに自動アップグレードが失敗する可能性がある問題が修正されました。
- 結合規則にハイフンを含む属性名が含まれている場合に発生する可能性があるエラーが修正されました。
- TLS 設定が前提条件を満たしていない場合のウィザードのエラー メッセージが改善されました。
- SMART CARD REQUIRED ビット フラグの変更時にパスワード ハッシュが同期しないバグが修正されました。 この修正では、スマート カードが認証方法として使用されるシナリオで、Microsoft Entra ID と Active Directory のパスワードを同期することはできません。 [詳細情報](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#password-hash-synchronization-and-smart-card-authentication)
- 一部のクラウドに対して自動アップグレード エンドポイントが誤って構成されたバグが修正されました。

### 2.3.20.0

重要

バージョン 2.3.20.0 はセキュリティ更新プログラムです。 この更新プログラムでは、Microsoft Entra Connect に TLS 1.2 が必要です。 このバージョンに更新する前に、TLS 1.2 が有効になっていることを確かめてください。

すべてのバージョンの [Windows Server が TLS 1.2 をサポートしています](https://learn.microsoft.com/ja-jp/windows-server/security/tls/tls-ssl-schannel-ssp-overview)。 サーバーで TLS 1.2 が有効になっていない場合は、Microsoft Entra Connect V2.0 を展開する前に、これを有効にする必要があります。

TLS 1.2 が有効かどうかを確認する PowerShell スクリプトについては、「[TLS を確認する PowerShell スクリプト](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement#powershell-script-to-check-tls-12)」を参照してください

TLS 1.2 について詳しくは、[Microsoft セキュリティ アドバイザリ 2960358](https://learn.microsoft.com/ja-jp/security-updates/SecurityAdvisories/2015/2960358) に関するページを参照してください。 TLS 1.2 の有効化の詳細については、「[TLS 1.2 を有効にする方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement)」を参照してください

#### リリースの状態

2024 年 7 月 15 日: ダウンロード対象としてリリース済み

#### 機能の変更点

- Microsoft Entra Connect には TLS 1.2 以上が必要です。 ガイダンスの前提条件 ([Microsoft Entra Connect: 前提条件とハードウェア - Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#enable-tls-12-for-microsoft-entra-connect) | Microsoft Learn) を参照してください
- TLS 1.3 は Microsoft Entra Connect でサポートされています。 [TLS 1.3 のサポートは、Microsoft Entra ID サービス](https://learn.microsoft.com/ja-jp/troubleshoot/azure/entra/entra-id/ad-dmn-services/enable-support-tls-environment?tabs=azure-monitor#tls-13-support-for-microsoft-entra-services)向けにロールアウトされていますが、これが完了するまで TLS 1.3 を適用することはお勧めしません。

#### その他の変更

- Microsoft Entra Connect に付属する SQL 関連のドライバーが更新されました。 ODBC は 17.10.6 に、OLE DB は 18.7.2 に。
- 同期サイクル中の SQL デッドロックを減らすための SSPR 処理の変更。
- アクセシビリティを向上させるためにナレーターが読み取るウィザード内の要素に対する変更。
- Microsoft Entra Connect アイコンのブランド化

### 2.3.8.0

#### リリースの状態

2024 年 4 月 1 日: ダウンロード向けにリリース

#### バグの修正

- 一部のクラウドで発生する可能性があるエンドポイント検出の問題に対処するために、Microsoft Entra Connect Health が 4.5.2466.0 に更新されました。

### 2.3.6.0

#### リリースの状態

2024 年 2 月 21 日: ダウンロードと自動アップグレード向けにリリース。

#### バグの修正:

- 自動アップグレード検出の改善。 マシンが OS または .NET ランタイムの要件を満たしていないことを検出した場合、自動アップグレードは再試行されなくなりました。

### 2.3.2.0

#### リリースの状態

2023 年 12 月 12 日: ダウンロード用にリリース済み

#### 機能の変更点

- Windows アクセシビリティのフォント サイズ設定を使用したアプリケーションのスケーリングが追加されました。
- 機能が使用停止になっているので、グループの書き戻し V2 を有効にできなくなりました。 [グループの書き戻しについては、この記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-group-writeback-enable)の通知を参照してください。

#### その他の変更

- Microsoft Entra Connect に付属する SQL 関連のドライバーが更新されました。 ODBC は 17.10.5 に、OLE DB は 18.6.7 に。
- Microsoft Entra Connect に付属する Microsoft Entra Connect Health が 4.5.2428.0 に更新されました。
- 中国での Azure の DSSO バグを修正しました

### 2.2.8.0

#### リリースの状態

2023 年 10 月 11 日: ダウンロード用にリリース済み

#### 機能の変更点

- onPremisesObjectIdentifier 属性が既定の同期規則に追加されました。 この属性は、Microsoft Entra クラウド同期の AD へのグループ プロビジョニング機能で必要です。
- .NET ランタイムの最小要件が 4.7.1 に上がりました。

#### バグの修正

- コンポーネントのアップグレードと自動アップグレードが機能強化されました。
- グループと別のドメインに属するメンバーの両方の削除が同じ同期サイクルで処理される場合に、グループのプロビジョニングを解除できない問題が修正されました。

### 2.2.1.0

#### リリースの状態

2023 年 6 月 19 日: ダウンロード対象としてリリース済み。

#### 機能の変更点

- カスタム同期規則を使用するテナントに対して自動アップグレードを有効にしました。 削除された (無効ではない) 既定のルールは、自動アップグレード時に再作成され、有効になります。
- Microsoft Entra Connect エージェント アップデーター サービスがインストールに追加されました。 この新しいサービスは、将来の自動アップグレードに使用されます。
- Synchronization Service WebService コネクタの構成プログラムをインストールから削除しました。
- employeeType 属性をフローするように、既定の同期規則「In from AD – User Common」が更新されました。

#### バグの修正

- アクセシビリティを強化しました。
- Microsoft のプライバシーに関する声明は、より多くの場所でアクセスできるようにしました。

### 2.1.20.0

#### リリースの状態:

11/9/2022: ダウンロード対象としてリリース済み

#### バグ修正

- 新しい employeeLeaveDateTime 属性がバージョン 2.1.19.0 で正しく同期されないバグを修正しました。 正しくない属性が既に規則で使用されていた場合、その規則を新しい属性で更新する必要があり、Microsoft Entra コネクタ スペースに属性が間違っているオブジェクトがある場合、それを "Remove-ADSyncCSObject" コマンドレットで削除し、完全な同期周期を実行する必要があります。

### 2.1.19.0

#### リリースの状態:

11/2/2022: ダウンロード対象としてリリース済み

#### 機能の変更点

- Microsoft Entra ID に同期するための新しい属性 'employeeLeaveDateTime' が追加されました。 この属性を使用してユーザーのライフ サイクルを管理する方法の詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)を参照してください

#### バグ修正

- Microsoft Entra Connect Password の書き戻しが停止し、エラー コード "SSPR\_0029 ERROR\_ACCESS\_DENIED" が表示されるバグが修正されました。

### 2.1.18.0

#### リリースの状態:

2022 年 10 月 5 日: ダウンロード対象としてリリース済み

#### バグ修正

- IsMemberOfLocalGroup 列挙型により、バージョン 1.6 からバージョン 2.1 へのアップグレードがループで停止するバグを修正しました。
- エンタープライズ管理者の検証中に Microsoft Entra Connect 構成ウィザードが正しくない資格情報 (ユーザー名形式) を送信していたバグが修正されました。

### 2.1.16.0

#### リリースの状態

2022 年 8 月 2 日: ダウンロードと自動アップグレード向けにリリース。

#### バグ修正

- サービス アカウントが "UPN" 形式のときに自動アップグレードが失敗するバグを修正しました。

### 2.1.15.0

#### リリースの状態

2022 年 7 月 6 日: ダウンロード用にリリースされました。

重要

Microsoft Entra Connect 管理 エージェントでセキュリティの脆弱性が検出されました。 以前に管理エージェントをインストールしている場合は、脆弱性を軽減するために Microsoft Entra Connect サーバーをこのバージョンに更新することが重要です。

#### 機能の変更点

- Microsoft Entra Connect から、管理エージェントのパブリック プレビュー機能が削除されました。 この機能は今後提供しません。
- 新たに employeeOrgDataCostCenter と employeeOrgDataDivision という 2 つの属性のサポートを追加しました。
- Microsoft Entra Connector の静的スキーマに CertificateUserIds 属性が追加されました。
- イベント ログの書き込みアクセス許可がない場合、Microsoft Entra Connect ウィザードが中止されるようになりました。
- 米国政府クラウドをサポートするように Microsoft Entra Connect の正常性エンドポイントを更新しました。
- "ソース アンカーが変更されました" という大量のエラーを修正するための新しいコマンドレット "Get-ADSyncToolsDuplicateUsersSourceAnchor および Set-ADSyncToolsDuplicateUsersSourceAnchor" を追加しました。 重複するユーザー オブジェクトを含む新しいフォレストが Microsoft Entra Connect に追加された場合、それらのオブジェクトで "ソース アンカーが変更されました" エラーが大量に発生します。 その原因は、msDsConsistencyGuid と ImmutableId との間の不一致です。 このモジュールと新しいコマンドレットの詳細については、[こちらの記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adsynctools)を参照してください。

#### バグ修正

- 一部のロケールで localDB のアップグレードを妨げるバグを修正しました。
- localDB 使用時のデータベースの破損を防ぐために、バグを修正しました。
- タイムアウトとサイズ制限のエラーを接続ログに追加しました。
- 子ドメインに親ドメインのユーザーと同じ名前のユーザーがいて、そのユーザーがたまたまエンタープライズ管理者であった場合にグループのメンバーシップに障害が発生するバグを修正しました。
- "In from Microsoft Entra ID - Group SOAInAAD" 規則に使用されている式が更新され、description 属性を 448 文字に制限されました。
- パスワード リセットの "パスワードの無期限" の拡張権限を設定するように変更しました。
- AD コネクタのアップグレードを変更してスキーマを更新しました。アップグレード中、構築された属性とレプリケートされていない属性は今後ウィザードに表示されません。
- ADSyncConfig 関数 ConvertFQDNtoDN と ConvertDNtoFQDN のバグを修正しました。ユーザーが '$dn' または '$fqdn' という変数を設定することにした場合、これらの変数はスクリプト スコープ内で使用されなくなります。
- 次のアクセシビリティ修正を行いました。
- [ドメインと OU のフィルタリング] ページのキーボード ナビゲーション中にフォーカスが失われるバグを修正しました。
- [実行のクリア] ドロップダウンのアクセス可能な名前を更新しました。
- [ヘルプ] ボタンに方向キーを使用して移動した場合、そのツールヒントにキーボードからアクセスできないバグを修正しました。
- ウィザードの [ようこそ] ページにハイパーリンクの下線が表示されないバグを修正しました。
- Sync Service Manager の [バージョン情報] ダイアログのバグを修正しました。[バージョン情報] ダイアログ ボックスに表示されるデータについての情報が、スクリーン リーダーで読み上げられていませんでした。
- 管理エージェント名の検証中にエラーが発生すると、その名前がログに記載されないバグを修正しました。
- キーボード ナビゲーションとカスタム コントロール タイプの修正に関して、いくつかのアクセシビリティの問題を修正しました。 [ヘルプ] ボタンのツールヒントが、"Esc" キーを押しても非表示になりません。 [ユーザー サインイン] ラジオ ボタンに、理にかなわないキーボード フォーカスがあったほか、ヘルプ ポップアップに無効な種類のコントロールが表示されていました。
- 空のラベルが原因でユーザー補助エラーが発生していたバグを修正しました。

### 2.1.1.0

#### リリースの状態

2022 年 3 月 24 日: ダウンロード専用にリリース。自動アップグレードには使用できません

#### バグ修正

- 一部の同期規則関数でサロゲート ペアが正しく解析されない問題を修正しました。
- 特定の状況下で、model db の破損が原因で同期サービスが開始されない問題を修正しました。 model db 破損の問題の詳細については、[この記事](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/resolve-model-database-corruption-sqllocaldb)をご覧ください

### 2.0.91.0

#### リリースの状態

2022 年 1 月 19 日: ダウンロード用のみにリリース済み。自動アップグレードでは使用できません

#### 機能の変更点

- このリリースの Microsoft Entra Connect Health コンポーネントがバージョン 3.1.110.0 からバージョン 3.2.1823.12 に更新されました。 この新しいバージョンは、Microsoft Entra Connect Health コンポーネントの [Federal Information Processing Standards (FIPS)](https://www.nist.gov/standardsgov/compliance-faqs-federal-information-processing-standards-fips) 要件の準拠を提供します。

### 2.0.89.0

#### リリースの状態

2021 年 12 月 22 日: ダウンロード用のみにリリース済み。自動アップグレードでは使用できません

#### バグ修正

- バージョン 2.0.88.0 のバグ (特定の条件下で、無効になっているユーザーのリンクされたメールボックスと特定のリソース オブジェクトのメールボックスが削除される) を修正しました。
- ADSync の VSA サービス アカウントと共に SQL localdb を使用すると、Microsoft Entra Connect バージョン 2.x へのアップグレードが失敗する問題が修正されました。

### 2.0.88.0

注

このリリースには Windows Server 2016 以降が必要です。 Microsoft Entra Connect のバージョン 2.0 に存在する脆弱性が修正され、その他のいくつかのバグ修正やマイナー機能更新プログラムも含まれます。

#### リリースの状態

2021 年 12 月 15 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### バグ修正

- Microsoft.Data.OData のバージョンを 5.8.1 から 5.8.4 にアップグレードして脆弱性を修正しました。
- アクセシビリティの修正:
- さまざまなズーム レベルと画面解像度を考慮して、Microsoft Entra Connect ウィザードをサイズ変更できるようになりました。
- アクセシビリティ要件を満たすために、要素に名前を付けました。
- null 参照のために miisserver が失敗するバグを修正しました。
- Microsoft Entra Connect を新しいバージョンにアップグレードした後もデスクトップ SSO 値が保持されるようにバグが修正されました。
- アカウントまたはリソース フォレストに関する問題を修正するために、inetorgperson 同期規則を変更しました。
- **[さらなるリンク]** リンクを表示するラジオ ボタン テストを修正しました。

#### 機能の変更点

- グループの書き戻し DN が、同期されたグループの表示名を使用して構成できるように変更しました。
- グループの書き戻しを有効にするときの Exchange スキーマに関するハード要件を削除しました。
- Microsoft Entra Kerberos の変更点:
- 信頼されたオブジェクトを作成するためのカスタムの最上位レベル名をサポートするために PowerShell コマンドを拡張しました。
- Microsoft Entra Kerberos 機能の公式ブランド名を設定するための変更が行われました。

### 1.6.16.0

注

このリリースは Microsoft Entra Connect の更新プログラムのリリースです。 このバージョンは、以前のバージョンの Windows Server を実行していて、この時点でサーバーを Windows Server 2016 以降にアップグレードできないお客様が使用することを目的としています。 このバージョンを使用して Microsoft Entra Connect V2.0 サーバーを更新することはできません。

このリリースは、Windows Server 2016以降にインストールしないでください。 このリリースには SQL Server 2012 コンポーネントが含まれており、2022 年 8 月 31 日に廃止されました。 その日より前に、サーバー OS と Microsoft Entra Connect のバージョンをアップグレードしてください。

この V1.6 ビルドまたは新しいビルドにアップグレードすると、グループ メンバーシップの制限は 50,000 にリセットされます。 サーバーをこのビルドまたは新しい 1.6 ビルドにアップグレードする場合は、サーバーの同期を有効にする前に、最初にグループ メンバーシップの制限を 250,000 に引き上げる時に、適用した規則の変更を再適用します。

#### リリースの状態

2021 年 10 月 13 日: ダウンロードと自動アップグレード向けにリリース

#### バグ修正

- 以前の Windows OS バージョン 2008 または 2008 R2 で実行されている Microsoft Entra Connect サーバーの自動アップグレード プロセスでアップグレードが失敗するバグが修正されました。 これらのバージョンの Windows Server はサポートされなくなりました。 このリリースでは、Windows Server 2012 以降を実行しているコンピューターの自動アップグレードのみが試行されます。
- 特定の条件下で、アクセス違反例外が原因で miisserver が失敗する問題を修正しました。

#### 既知の問題

この V1.6 ビルドまたは新しいビルドにアップグレードすると、グループ メンバーシップの制限は 50,000 にリセットされます。 サーバーをこのビルドまたは新しい 1.6 ビルドにアップグレードする場合は、サーバーの同期を有効にする前に、最初にグループ メンバーシップの制限を 250,000 に引き上げる時に、適用した規則の変更を再適用します。

### 2.0.28.0

注

このリリースは Microsoft Entra Connect のメンテナンス更新プログラムのリリースです。 Windows Server 2016 以降が必要です。

#### リリースの状態

2021 年 9 月 30 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### バグ修正

- ウィザードの **[Group Writeback Permissions](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/グループ書き戻しアクセス許可)** ページで、PowerShell スクリプトのダウンロード ボタンを削除しました。 また、PowerShell スクリプトが掲載されているオンライン記事にリンクされている **[詳細]** リンクを含めるようにウィザード ページのテキストを変更しました。
- サーバー上の .NET バージョンが 4.6 以降の場合に、レジストリ キーがないためにウィザードによって誤ってインストールがブロックされていたバグを修正しました。 これらのレジストリ キーは必須ではなく、意図的に false に設定されている場合にのみインストールがブロックされます。
- 同期手順の初期化中にファントム オブジェクトが見つかった場合に、エラーがスローされるバグを修正しました。 このバグにより、同期手順がブロックされたり、一時的なオブジェクトが削除されました。 ファントム オブジェクトは無視されるようになりました。

ファントム オブジェクトは、そこにない、またはまだ表示されていないオブジェクトのプレースホルダーです。 たとえば、ソース オブジェクトにそこにないターゲット オブジェクトの参照が含まれる場合、ターゲット オブジェクトはファントムとして作成されます。

#### 機能の変更点

使用中であっても、ユーザーが包含リストからオブジェクトと属性の選択を解除できるように変更しました。 このアクションをブロックするのではなく、警告が表示されるようになりました。

### 1.6.14.2

注

このリリースは Microsoft Entra Connect の更新プログラムのリリースです。 このバージョンは、以前のバージョンの Windows Server を実行していて、この時点でサーバーを Windows Server 2016 以降にアップグレードできないお客様が使用することを目的としています。 このバージョンを使用して Microsoft Entra Connect V2.0 サーバーを更新することはできません。

このバージョンをダウンロードできる場合は、対象テナントの自動アップグレードを開始します。 自動アップグレードが完了するまでに数週間かかります。

この V1.6 ビルドまたは新しいビルドにアップグレードすると、グループ メンバーシップの制限は 50,000 にリセットされます。 サーバーをこのビルドまたは新しい 1.6 ビルドにアップグレードする場合は、サーバーの同期を有効にする前に、最初にグループ メンバーシップの制限を 250,000 に引き上げる時に、適用した規則の変更を再適用します。

#### リリースの状態

2021 年 9 月 21 日: ダウンロードと自動アップグレード向けにリリース

#### 機能の変更点

- 最新バージョンの Microsoft Identity Manager (MIM) コネクタ (1.1.1610.0) を追加しました。 詳細については、[MIM コネクタのリリース履歴ページ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-version-history#1116100-september-2021)を参照してください。
- Microsoft Entra Connect であいまい一致機能を無効にするための構成オプションが追加されました。 クラウド専用アカウントを引き継ぐ必要がない限り、ソフト マッチングを無効にすることをお勧めします。 ソフト マッチングを無効にする方法は、[この参照アーティクル](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#hard-match-vs-soft-match)を参照してください。

#### バグ修正

- 以前のバージョンからのアップグレード後にデスクトップ シングル サインオン設定が保持されないバグを修正しました。
- Set-ADSync\*Permission コマンドレットが失敗する原因となるバグを修正しました。

### 2.0.25.1

注

このリリースは Microsoft Entra Connect の修正プログラムのリリースです。 このリリースには Windows Server 2016 以降が必要です。 Microsoft Entra Connect のバージョン 2.0 に存在するセキュリティの問題が修正され、その他のいくつかのバグ修正も含まれています。

#### リリースの状態

2021 年 9 月 14 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### バグ修正

- Microsoft Entra Connect サービスを指し示すために引用符で囲まれていないパスが使用された場合のセキュリティ上の問題が修正されました。 このパスは、現在では引用符で囲まれたパスです。
- 既存の Microsoft Entra Connector アカウントを使用する際に書き戻しが有効になっている場合のインポート構成の問題が修正されました。
- Set-ADSyncExchangeHybridPermissions およびその他の関連コマンドレットの問題を修正しました。これらは無効な継承型のために V1.6 から破損していました。
- TLS バージョンを設定するために、以前のリリースで公開したコマンドレットに関する問題を修正しました。 コマンドレットはキーを上書きし、その中にあったすべての値を破棄しました。 まだ新しいキーが存在していない場合のみ、新しいキーが作成されます。 TLS レジストリの変更が Microsoft Entra Connect 専用ではなく、同じサーバー上の他のアプリケーションにも影響を及ぼす可能性があることをユーザーに知らせる警告が追加されました。
- Windows Server 2016 以降を必須とするために、V2.0 の自動アップグレードを強制するためのチェックを追加しました。
- Set-ADSyncBasicReadPermissions コマンドレットに "ディレクトリの変更のレプリケート" アクセス許可を追加しました。
- UseExistingDatabase とインポート構成には競合する構成設定が含まれる可能性があるため、これらが一緒に使用されることを防ぐよう変更を加えました。
- アプリケーション管理者ロールのユーザーにアプリケーション プロキシ サービスの構成変更を許可するための変更を加えました。
- **Import/Export** 設定のラベルからラベルを削除しました。 この機能は一般公開されています。
- まだ "会社の管理者" と呼ばれるラベルをいくつか変更しました。 現在は、役割の名前として "全体管理者" を使用します。
- 要求変換規則を Microsoft Entra サービス プリンシパルに追加するための新しい Microsoft Entra Kerberos PowerShell コマンドレット (\*-AADKerberosServer) が作成されました。

#### 機能の変更点

- 最新バージョンの MIM コネクタ (1.1.1610.0) を追加しました。 詳細については、[MIM コネクタのリリース履歴ページ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-version-history#1116100-september-2021)を参照してください。
- Microsoft Entra Connect であいまい一致機能を無効にするための構成オプションが追加されました。 クラウド専用アカウントを引き継ぐ必要がない限り、ソフト マッチングを無効にすることをお勧めします。 ソフト マッチングを無効にする方法は、[この参照アーティクル](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#hard-match-vs-soft-match)を参照してください。

### 2.0.10.0

#### リリースの状態

2021 年 8 月 19 日: ダウンロード向けにリリース。自動アップグレードには使用できません

注

これは Microsoft Entra Connect の修正プログラムのリリースです。 このリリースには Windows Server 2016 以降が必要です。 この修正プログラムは、バージョン 2.0 と、Microsoft Entra Connect バージョン 1.6 に存在している問題に対処します。 以前の Windows サーバーで Microsoft Entra Connect を実行している場合は、代わりに 1.6.13.0 ビルドをインストールします。

#### リリースの状態

2021 年 8 月 19 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### 既知の問題

特定の状況で、このバージョンのインストーラーは、TLS 1.2 が有効になっていないと示すエラーを表示し、インストールを停止します。 この問題は、TLS 1.2 のレジストリ設定を検証するコードのエラーが原因となっています。 この問題は今後のリリースで修正する予定です。 この問題が発生した場合は、「[Microsoft Entra Connect に対する TLS 1.2 の適用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement)」に記載されている TLS 1.2 の有効化手順を実行してください。

#### バグ修正

ドメインの名前が変更され、パスワード ハッシュの同期が失敗し、イベント ログに "指定されたキャストが無効" と示されるエラーが発生するバグを修正しました。 このリグレッションは、以前のビルドからのものです。

### 1.6.13.0

注

このリリースは Microsoft Entra Connect の修正プログラムのリリースです。 これは、Windows Server 2012 または 2012 R2 を搭載したサーバーで Microsoft Entra Connect を実行しているお客様が使用することを目的としています。

2021 年 8 月 19 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### バグ修正

ドメインの名前が変更され、パスワード ハッシュの同期が失敗し、イベント ログに "指定されたキャストが無効" と示されるエラーが発生するバグを修正しました。 このリグレッションは、以前のビルドからのものです。

#### 機能の変更点

このリリースでは、機能上の変更はありません。

### 2.0.9.0

#### リリースの状態

2021 年 8 月 17 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### バグ修正

注

このリリースは Microsoft Entra Connect の修正プログラムのリリースです。 このリリースには Windows Server 2016 以降が必要です。 バージョン 2.0.8.0 に存在する問題に対処します。 この問題は、Microsoft Entra Connect バージョン 1.6 には存在しません。

多数のパスワード ハッシュの同期トランザクションを同期し、イベント ログ エントリの長さがパスワード ハッシュの同期イベント エントリで許容される最大長を超える時に発生するバグを修正しました。 長いログ エントリを複数のエントリに分割するようにしました。

### 2.0.8.0

注

このリリースは Microsoft Entra Connect のセキュリティ更新プログラムのリリースです。 このリリースには Windows Server 2016 以降が必要です。 以前のバージョンの Windows Server を使用している場合は、バージョン 1.6.11.3 を使用してください。

このリリースで[こちらの CVE](https://msrc.microsoft.com/update-guide/vulnerability/CVE-2021-36949) に記載されている脆弱性に対処します。 この脆弱性の詳細については、CVE を参照してください。

#### リリースの状態

2021 年 8 月 10 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### 機能の変更点

このリリースでは、機能上の変更はありません。

### 1.6.11.3

注

このリリースは Microsoft Entra Connect のセキュリティ更新プログラムのリリースです。 以前のバージョンの Windows Server を実行していて、この時点でサーバーを Windows Server 2016 以降にアップグレードできないお客様が使用することを目的としています。 このバージョンを使用して Microsoft Entra Connect V2.0 サーバーを更新することはできません。

このリリースで[こちらの CVE](https://msrc.microsoft.com/update-guide/vulnerability/CVE-2021-36949) に記載されている脆弱性に対処します。 この脆弱性の詳細については、CVE を参照してください。

#### リリースの状態

2021 年 8 月 10 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### 機能の変更点

このリリースでは、機能上の変更はありません。

### 2.0.3.0

注

このリリースは Microsoft Entra Connect のメジャー リリースです。 詳細については、「[Microsoft Entra Connect V2.0 の概要](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2)」を参照してください。

#### リリースの状態

2021 年 7 月 20 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### 機能の変更点

- SQL Server の LocalDB コンポーネントを SQL 2019 にアップグレードしました。
- SQL Server 2019 の要件により、このリリースには Windows Server 2016 以降が必要です。 Microsoft Entra Connect サーバーでの Windows Server のインプレース アップグレードはサポートされていません。 このため、[スウィング移行](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version#swing-migration) の使用が必要となる場合があります。
- このリリースでは、TLS 1.2 の使用を適用しています。 ご使用の Windows Server で TLS 1.2 を有効にしている場合は、Microsoft Entra Connect でこのプロトコルが使用されます。 サーバーで TLS 1.2 が有効になっていない場合は、Microsoft Entra Connect をインストールしようとするとエラー メッセージが表示されます。 TLS 1.2 を有効にするまでインストールは続行されません。 お使いのサーバーで新しい Set-ADSyncToolsTls12 コマンドレットを使用して TLS 1.2 を有効にすることができます。
- このリリースでは、Microsoft Entra Connect をインストールするときに、ハイブリッド ID の管理者ロールを使用して認証が行えるように変更されました。 全体管理者の役割を使用する必要はなくなりました。
- SQL Server 2019 の前提条件として、Visual C++ ランタイム ライブラリをバージョン 14 にアップグレードしました
- Microsoft 認証ライブラリを使用して認証を行うために、このリリースを更新しました。 廃止された古い Azure AD 認証ライブラリを削除しました。
- Windows セキュリティ ガイダンスに従って、AdminSDHolders に対するアクセス許可を適用しなくなりました。 ADSyncConfig.psm1 モジュールのパラメーター SkipAdminSdHolders を IncludeAdminSdHolders に変更しました。
- パスワード自体が変更されたかどうかに関係なく、有効期限が切れたパスワードが "有効期限が切れていない" ときにパスワードが再評価されるように変更しました。 ユーザーのパスワードが [次回のログオン時にパスワードを変更する必要があります] に設定されていて、このフラグがオフ (パスワードの "有効期限が切れていない") になってる場合、有効期限が切れていない状態とパスワード ハッシュが Microsoft Entra ID に同期されます。 Microsoft Entra ID で、ユーザーがサインインを試みる際に、有効期限が切れていないパスワードを使用できます。 期限切れのパスワードを Active Directory から Microsoft Entra ID に同期するには、Microsoft Entra Connect の[一時パスワードの同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#synchronizing-temporary-passwords-and-force-password-change-on-next-logon)機能を使用してください。 ユーザーが更新するパスワードが Active Directory に書き戻されるよう、パスワード ライトバックを有効にしてこの機能を使用します。
- WINDOWS Server で TLS 1.2 設定を有効または取得するために、ADSyncTools モジュールに 2 つの新しいコマンドレットを追加しました。
- Get-ADSyncToolsTls12
- Set-ADSyncToolsTls12

これらのコマンドレットを使用して、TLS 1.2 の有効化状態を取得したり、必要に応じて設定したりできます。 インストールまたは Microsoft Entra Connect が成功するには、サーバーで TLS 1.2 を有効にする必要があります。

- いくつかの新しいおよび改善されたコマンドレットにより、ADSyncTools を改良しました。 [ADSyncTools の記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adsynctools)には、これらのコマンドレットの詳細が記載されています。 追加または更新されたコマンドレットは次のとおりです
- Clear-ADSyncToolsMsDsConsistencyGuid
- ConvertFrom-ADSyncToolsAadDistinguishedName
- ConvertFrom-ADSyncToolsImmutableID
- ConvertTo-ADSyncToolsAadDistinguishedName
- ConvertTo-ADSyncToolsCloudAnchor
- ConvertTo-ADSyncToolsImmutableID
- Export-ADSyncToolsAadDisconnectors
- Export-ADSyncToolsObjects
- Export-ADSyncToolsRunHistory
- Get-ADSyncToolsAadObject
- Get-ADSyncToolsMsDsConsistencyGuid
- Import-ADSyncToolsObjects
- Import-ADSyncToolsRunHistory
- Remove-ADSyncToolsAadObject
- Search-ADSyncToolsADobject
- Set-ADSyncToolsMsDsConsistencyGuid
- Trace-ADSyncToolsADImport
- Trace-ADSyncToolsLdapQuery
- 現在インポートとエクスポートに V2 エンドポイントを使用しています。 Get-ADSyncAADConnectorExportApiVersion コマンドレットの問題を修正しました。 V2 エンドポイントの詳細については、「[Microsoft Entra Connect 同期 V2 エンドポイント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-endpoint-api-v2)」を参照してください。
- オンプレミスの Active Directory から Microsoft Entra ID に同期するために、次の新しいユーザー プロパティが追加されました。
- 従業員タイプ
- 従業員採用日

注

Active Directory には、EmployeeHireDate または EmployeeLeaveDateTime に対応する属性はありません。 オンプレミスの AD からインポートする場合は、AD で使用できる属性を確認する必要があります。 この属性は、文字列である必要があります。 詳細については、[ライフサイクル ワークフロー属性の同期](https://learn.microsoft.com/ja-jp/entra/id-governance/how-to-lifecycle-workflow-sync-attributes)に関するページを参照してください。

- このリリースでは、Windows Server に PowerShell バージョン 5.0 以降がインストールされている必要があります。 このバージョンは Windows Server 2016 以降のバージョンの一部です。
- 新しい V2 エンドポイントを使用して、グループ同期メンバーシップの制限を 250,000 に引き上げました。
- Generic LDAP コネクタと Generic SQL コネクタを最新バージョンに更新しました。 これらのコネクタの詳細については、次のリファレンス ドキュメントを参照してください。
- [Generic LDAP コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericldap)
- [Generic SQL コネクタ](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-genericsql)
- Microsoft 365 管理センターでは、Microsoft Entra ID へのエクスポート アクティビティがあるたびに、Microsoft Entra Connect クライアントのバージョンを報告するようになりました。 この報告により、Microsoft 365 管理センターには常に最新の Microsoft Entra Connect クライアント バージョンが確保されるため、期限切れのバージョンを使用している場合は検出できるようになります。

#### バグ修正

- スクリーン リーダーが **[詳細情報]** リンクの間違った役割をアナウンスするアクセシビリティのバグを修正しました。
- 優先順位の値が大きな同期規則 (387163089 など) によってアップグレードが失敗するバグを修正しました。 値をインクリメントする前に、優先順位番号を整数としてキャストするように sproc mms\_UpdateSyncRulePrecedenceを更新しました。
- グループの書き戻しの構成がインポートされた場合に、グループの書き戻しアクセス許可が同期アカウントに設定されないバグを修正しました。 インポートされた構成でグループの書き戻しが有効になっている場合に、グループの書き戻しアクセス許可が設定されるようにしました。
- インストールの失敗を修正するために、Microsoft Entra Connect Health エージェントのバージョンが 3.1.110.0 に更新されました。
- ディレクトリ拡張属性が構成されているエクスポートされた構成からの既定以外の属性で問題が発生しています。 これらの構成を新しいサーバーまたはインストールにインポートする過程で、属性包含リストはディレクトリ拡張機能の構成手順によってオーバーライドされます。 そのため、インポート後は、同期サービス マネージャーで既定の属性とディレクトリ拡張機能属性だけが選択されます。 既定以外の属性はインストールに含まれていないので、インポートした同期規則を機能させるには、ユーザーは同期サービス マネージャーから手動で再び有効にする必要があります。 属性包含リストの既存の属性が保持されるように、ディレクトリ拡張機能を構成する前に Microsoft Entra コネクタが更新されるようになりました。
- ページ ヘッダーのフォントの太さが "細い" に設定されたアクセシビリティの問題を修正しました。 ページ タイトルのフォントの太さが "太字" に設定され、すべてのページのヘッダーに適用されるようになりました。
- ADSyncSingleObjectSync.ps1 の関数 Get-AdObject は、Active Directory コマンドレットとのあいまいさを防ぐために、Get-AdDirectoryObject に名前変更されました。
- 重複する規則の優先順位を許可する条件を削除しました。 SQL 関数 mms\_CheckSynchronizationRuleHasUniquePrecedence では、異なるコネクタの送信同期規則で重複する優先順位が許可されていました。
- 属性フロー データが null の場合に、単一オブジェクト同期コマンドレットが失敗するバグを修正しました。 たとえば、削除操作のエクスポートです。
- ADSync ブートストラップ サービスを開始できないためにインストールが失敗するバグを修正しました。 ブートストラップ サービスを開始する前に、ローカルの Builtin ユーザー グループに同期サービス アカウントを追加しました。
- Microsoft Entra Connect ウィザードのアクティブなタブが、ハイ コントラスト テーマで正しい色で表示されないアクセシビリティの問題が修正されました。 選択した色コードは、通常の色のコード構成で条件が不足していたため上書きされました。
- UI と PowerShell を使用して同期規則で使用されるオブジェクトと属性の選択を解除できる問題に対処しました。 任意の同期規則で使用されている属性またはオブジェクトの選択を解除しようとすると、わかりやすいエラー メッセージが表示されるようになりました。
- 以前のバージョンの Microsoft Entra Connect でスクリプトを実行した場合の下位互換性の問題を確認して修正するために、"設定の移行コード" にいくつかの更新が加えられました。
- PHS が不完全なオブジェクトを参照しようとして発生するバグを修正しました。 最初にパスワードをフェッチするために使用したのと同じアルゴリズムを使用して DC を解決したのではありません。 特に、アフィニティ化された DC 情報が無視されます。 不完全なオブジェクトの検索では、両方のインスタンスで DC を見つけるために同じロジックを使用する必要があります。
- Microsoft Entra Connect で Microsoft Graph を使用してアプリケーション プロキシ項目を読み取ることができないバグが修正されました。これは、Microsoft Entra Connect クライアント識別子に基づいて Microsoft Graph を直接呼び出す際のアクセス許可に関する問題が原因です。 この問題を解決するために、Microsoft Graph への依存関係を削除し、代わりに Microsoft Entra PowerShell を使用してアプリケーション プロキシ アプリケーション オブジェクトを操作しました。
- Out to AD - Group SOAInAAD Exchange 同期規則から書き戻しメンバーの制限を削除しました。
- コネクタ アカウントのアクセス許可を変更した際に発生するバグを修正しました。 最後の差分インポート以降に変更されていないオブジェクトがスコープ内にある場合、差分インポートはそのオブジェクトをインポートしません。 これで、問題を通知する警告が表示されるようになりました。
- スクリーン リーダーでラジオ ボタンの位置が読み取られないアクセシビリティの問題を修正しました。 ラジオ ボタンのアクセシビリティ テキスト フィールドに、位置テキストを追加しました。
- パススルー認証エージェント バンドルを更新しました。 以前のバンドルには、米国政府での HIP のファースト パーティ アプリケーションの正しい応答 URL がありませんでした。
- 既存のデータベースを使用して、既定で DirSyncWebServices API V2 を使用する Microsoft Entra Connect バージョン 1.6.X.X をクリーン インストールすると、Microsoft Entra コネクタの "stopped-extension-dll-exception" エラーがエクスポートされるバグが修正されました。 以前は、V2 へのエクスポート バージョンの設定は、アップグレードでのみ実行されていました。 クリーン インストールに設定されるよう変更しました。
- ADSyncPrep.psm1 モジュールは使用されなくなったため、インストールから削除しました。

#### 既知の問題

- Microsoft Entra Connect ウィザードで、**[同期設定をインポート]** オプションが **[プレビュー]** として表示されますが、この機能は一般提供されています。
- 一部の Active Directory コネクタは、移行設定スクリプトの出力を使用して製品をインストールすると、異なる順序でインストールされる場合があります。
- Microsoft Entra Connect ウィザードの **[ユーザー サインイン オプション]** ページに、"会社の管理者" と記載されています。 この用語は使用されなくなったため、"全体管理者" に置き換える必要があります。
- **サインイン** オプションが PingFederate を使用するように構成されている場合、**[設定のエクスポート]** オプションは破損します。
- Microsoft Entra Connect は、ハイブリッド ID の管理者ロールを使用してデプロイできるようになりましたが、セルフサービス パスワード リセット、パススルー認証またはシングル サインオンを構成するには、引き続きユーザーに全体管理者ロールが必要です。
- 元の Microsoft Entra Connect 構成とは別のテナントに接続するためにデプロイしているときに Microsoft Entra Connect 構成をインポートすると、ディレクトリ拡張属性が正しく構成されません。

### 1.6.4.0

注

こちらの Azure 環境で Microsoft Entra Connect 同期 V2 エンドポイント API を使用できるようになりました。

- Azure コマーシャル
- 21Vianet が運用する Microsoft Azure
- Azure 米国政府向けクラウド

このリリースは、Azure German Cloud では利用できません。

#### リリースの状態

2021 年 3 月 31 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### バグ修正

このリリースでは、バージョン 1.6.2.4 で発生したバグが修正されます。 そのリリースにアップグレードした後、Microsoft Entra Connect Health 機能が正しく登録されず、機能しませんでした。 ビルド 1.6.2.4 をデプロイした場合は、正常性機能を正しく登録するように、このビルドで Microsoft Entra Connect サーバーを更新します。

### 1.6.2.4

重要

2021 年 3 月 30 日の更新: このビルドで問題が検出されました。 このビルドをインストールすると、Health サービスが登録されません。 このビルドをインストールしないことをお勧めします。 修正プログラムが間もなくリリースされる予定です。 このビルドが既にインストールされている場合は、「[Microsoft Entra Connect Health エージェントのインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install#manually-register-azure-ad-connect-health-for-sync)」に示されているように、コマンドレットを使用して手動で Health サービスを登録できます。

- このリリースはダウンロードでのみ使用できます。
- このリリースへのアップグレードでは、同期規則が変更されたため、完全同期が必要です。
- このリリースでは、Microsoft Entra Connect サーバーは既定で、新しい V2 エンド ポイントに設定されます。

#### リリースの状態

2021 年 3 月 19 日: ダウンロード向けにリリース。自動アップグレードには使用できません

#### 機能の変更点

- 書き戻されるグループ内のメンバーシップ数を 5 万メンバーに制限するように、既定の同期規則を更新しました。
- グループ の書き戻しのメンバーシップ数を制限するための新しい既定の同期規則 (AD への送信 - グループ 書き戻しメンバーの制限) とグループ同期を Microsoft Entra ID (Out to Microsoft Entra ID - Group Write up Member Limit) グループに追加しました。
- 書き戻しグループ内のメンバー数を 5 万に制限するため、"Out to AD - Group SOAInAAD - Exchange" 規則にメンバー属性を追加しました。
- グループの書き戻し V2 をサポートするように、同期規則を更新しました。
- In from Microsoft Entra ID - Group SOAInAAD ルールが複製され、Microsoft Entra Connect がアップグレードされた場合:
- 更新されたルールは既定で無効になっているため、targetWritebackType は null です。
- Microsoft Entra Connect は、すべてのクラウド グループ (書き戻しが有効になっている Microsoft Entra セキュリティ グループを含む) を配布グループとして書き戻します。
- AD への送信 - グループ SOAInAAD ルールが複製され、Microsoft Entra Connect がアップグレードされた場合:
- 更新されたルールは、既定で無効になっています。 新しい同期規則 (AD への送信 - グループ SOAInAAD - Exchange) が有効になっています。
- 複製されたカスタム同期規則の優先順位に応じて、Microsoft Entra Connect はメール属性と Exchange 属性をフローします。
- 複製されたカスタム同期規則が一部のメールおよび Exchange 属性をフローしない場合、新しい Exchange 同期規則によってそれらの属性が追加されます。
- [選択的なパスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-selective-password-hash-synchronization)のサポートを追加しました。
- 新しい[単一オブジェクト同期コマンドレット](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-single-object-sync)を追加しました。 このコマンドレットは、Microsoft Entra Connect の同期の構成をトラブルシューティングするために使用します。
- Microsoft Entra Connect では、サービスを構成するための、ハイブリッド ID の管理者の役割をサポートするようになりました。
- Microsoft Entra Connect Health エージェントが 3.1.83.0 に更新されました。
- 新しいバージョンの [ADSyncTools PowerShell モジュール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adsynctools)を導入しました。これには、新しいコマンドレットや改良されたコマンドレットがいくつかあります。
- Clear-ADSyncToolsMsDsConsistencyGuid
- ConvertFrom-ADSyncToolsAadDistinguishedName
- ConvertFrom-ADSyncToolsImmutableID
- ConvertTo-ADSyncToolsAadDistinguishedName
- ConvertTo-ADSyncToolsCloudAnchor
- ConvertTo-ADSyncToolsImmutableID
- Export-ADSyncToolsAadDisconnectors
- Export-ADSyncToolsObjects
- Export-ADSyncToolsRunHistory
- Get-ADSyncToolsAadObject
- Get-ADSyncToolsMsDsConsistencyGuid
- Import-ADSyncToolsObjects
- Import-ADSyncToolsRunHistory
- Remove-ADSyncToolsAadObject
- Search-ADSyncToolsADobject
- Set-ADSyncToolsMsDsConsistencyGuid
- Trace-ADSyncToolsADImport
- Trace-ADSyncToolsLdapQuery
- トークン取得の失敗に関するエラーログを更新しました。
- リンクされている情報の詳細を表示するための、構成ページの **[詳細情報]** リンクを更新しました。
- 以前の同期 UI の **[CS 検索]** ページから、**[明示]** 列が削除されました
- 以前の手順で資格情報がまだ指定されていない場合に、ユーザーに資格情報の入力を求めたり、ADSyncConfig モジュールを使用して独自のアクセス許可を構成したりするため、グループの書き戻しフローに UI を追加しました。
- DC 上の ADSync サービス アカウントのマネージド サービス アカウントを自動作成する機能を追加しました。
- 既存のコマンドレットで Microsoft Entra DirSync 機能のグループの書き戻し V2 を設定および取得する機能が追加されました。
- Set-ADSyncAADCompanyFeature
- Get-ADSyncAADCompanyFeature
- AWS API バージョンを読み取る 2 つのコマンドレットを追加しました。
- Get-ADSyncAADConnectorImportApiVersion: AWS API バージョンのインポートを取得します
- Get-ADSyncAADConnectorExportApiVersion: AWS API のバージョンのエクスポートを取得します
- サービスでの変更のトラブルシューティングに役立つように、同期規則に加えられた変更を追跡できるよう、変更履歴を更新しました。 コマンドレット Get-ADSyncRuleAudit は、追跡された変更を取得します。
- ADSyncAdmin グループのユーザーが Active Directory Domain Services コネクタ アカウントを変更できるように、[ADSyncConfig PowerShell モジュール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account#using-the-adsyncconfig-powershell-module)の Add-ADSyncADDSConnectorAccount コマンドレットを更新しました。

#### バグ修正

- 白い背景での明るさの要件を満たすため、無効にされる前景色を更新しました。 明るさの要件を満たすために無効にされているページが選択された時に、前景のテキストの色を白に設定するよう、ナビゲーション ツリーでその他の条件を追加しました。
- Set-ADSyncPasswordHashSyncPermissions コマンドレットの粒度を向上させました。
- オプションの ADobjectDN パラメーターを含めるために、PHS アクセス許可スクリプト (Set-ADSyncPasswordHashSyncPermissions) を更新しました。
- アクセシビリティのバグ修正を行いました。 スクリーン リーダーでは、フォレストの一覧を保持する UX 要素を、"**フォレストの一覧の一覧**" ではなく "**フォレストの一覧**" として記述するようになりました。
- Microsoft Entra Connect ウィザードで一部の項目のスクリーン リーダーの出力が更新されました。 コントラストの要件を満たすため、ボタンをポイントしたときの色を更新しました。 コントラストの要件を満たすため、Sychronization Service Manager のタイトル色を更新しました。
- カスタム拡張属性を持つエクスポートされた構成からの Microsoft Entra Connect のインストールに関する問題が修正されました。
- 同期規則の適用中に、ターゲット スキーマで拡張属性の確認をスキップするための条件を追加しました。
- グループ書き戻し機能が有効になっている場合のインストール時に、適切なアクセス許可を追加しました。
- インポート時に重複する既定の同期規則の優先順位を修正しました。
- 正常性ポータルで修復されたオブジェクトが競合するとき、V2 API 差分インポート中、ステージング エラーが発生した問題を修正しました。
- コネクタ スペース オブジェクトのリンク状態の一貫性が失われる原因となった同期エンジンの問題を修正しました。
- Get-ADSyncConnectorStatistics 出力にインポート カウンターを追加しました。
- pass2 ウィザード中のいくつかのまれなケースで発生する、ドメインの選択解除 (以前に選択済み) にアクセスできない問題を修正しました。
- カスタム規則の優先順位が重複している場合は、ポリシーのインポートとエクスポートが失敗するように変更しました。
- ドメイン選択ロジックのバグを修正しました。
- ソース アンカーとして mS-DS-ConsistencyGuid を使用し、In from AD - Group Join 規則を複製した場合にビルド 1.5.18.0 で発生する問題を修正しました。
- 新しい Microsoft Entra Connect のインストールでは、クラウドに格納されているエクスポート削除しきい値が使用されます (使用可能なものがあり、別のしきい値が渡されていない場合)。
- Microsoft Entra Connect が、ハイブリッド参加済みデバイスの Active Directory displayName の変更を読み取らない問題が修正されました。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/reference-connect-version-history-archive"} -->
## Microsoft Entra Connect: バージョン リリース履歴アーカイブ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history-archive
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra Connect と Azure AD Sync のすべてのアーカイブされたリリースの一覧を示します

Microsoft Entra チームは、Microsoft Entra Connect を定期的に更新し、新機能を提供しています。 すべての追加機能がすべてのユーザーに適用されるわけではありません。

注

この記事には、Microsoft Entra ID - 1.5.42.0 以前のすべてのアーカイブされたバージョンに関するバージョン参照情報が含まれています。 現在のリリースについては、[Microsoft Entra Connect のバージョン リリース履歴](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)に関する記事を参照してください。

### 1.5.42.0

#### リリースの状態

07/10/2020:ダウンロード対象としてリリース済み

#### 機能の変更点

既存の Microsoft Entra Connect サーバーの構成を .JSON ファイルにエクスポートする機能のパブリック プレビューが含まれています。 新しい Microsoft Entra Connect サーバーをインストールして元のサーバーのコピーを作成するときに、このファイルを使用できます。

この新機能の詳細については、[この記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-import-export-config)を参照してください。

#### 修正された問題

- アップグレード中、ローカライズされたビルドのローカル DB サイズに関する警告が誤って発生するバグを修正しました。
- アカウント名とドメイン名の入れ替えで、アプリ イベントに誤ってエラーが発生するバグを修正しました。
- DC への Microsoft Entra Connect のインストールに失敗し、"メンバーが見つかりませんでした" というエラーが表示されるエラーが修正されました。

### 1.5.30.0

#### リリースの状態

2020 年 5 月 7 日:ダウンロード対象としてリリース済み

#### 修正された問題

この修正プログラムのビルドでは、孫コンテナーのみが選択されている場合に、選択されていないドメインがウィザードの UI から誤って選択されるという問題が修正されます。

注

このバージョンには、新しい Microsoft Entra Connect 同期 V2 エンドポイント API が含まれています。 現在、この新しい V2 エンドポイントはパブリック プレビュー段階にあります。 新しい V2 エンドポイント API を使用するには、このバージョン以降が必要です。 ただし、このバージョンをインストールするだけでは、V2 エンドポイントは有効になりません。 V2 エンドポイントを有効にしない限り、V1 エンドポイントが引き続き使用されます。 有効にしてパブリック プレビューにオプトインするには、[Microsoft Entra Connect 同期 V2 エンドポイント API (パブリック プレビュー)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-endpoint-api-v2) に関する記事の手順に従う必要があります。

### 1.5.29.0

#### リリースの状態

2020 年 4 月 23 日:ダウンロード対象としてリリース済み

#### 修正された問題

この修正プログラム ビルドでは、MFA を使用するテナント管理者が DSSO を有効にできなかった、ビルド 1.5.20.0 で発生した問題を修正しました。

### 1.5.22.0

#### リリースの状態

2020 年 4 月 20 日:ダウンロード対象としてリリース済み

#### 修正された問題

この修正プログラム ビルドでは、**[In from AD - Group Join] (AD からの受信 - グループ結合)** 規則を複製し、**[In from AD - Group Common] (AD からの受信 - グループ共通)** 規則を複製していない場合のビルド 1.5.20.0 の問題を修正します。

### 1.5.20.0

#### リリースの状態

2020 年 4 月 9 日ダウンロード対象としてリリース済み

#### 修正された問題

- この修正プログラム ビルドでは、グループ フィルタリング機能を有効にし、ソース アンカーとして mS-DS-ConsistencyGuid を使用している場合、ビルド 1.5.18.0 の問題が修正されます。
- すべての Set-ADSync\* Permissions コマンドレットで使用される DSACLS コマンドを呼び出すと、次のいずれかのエラーが発生するという ADSyncConfig PowerShell モジュールの問題。
    - `GrantAclsNoInheritance : The parameter is incorrect.  The command failed to complete successfully.`
    - `GrantAcls : No GUID Found for computer …`

重要

**[In from AD - Group Join] (AD からの受信 - グループ結合)** 同期ルールを複製し、**[In from AD - Group Common] (AD からの受信 - グループ共通)** 同期ルールを複製しておらず、アップグレードを計画している場合は、アップグレードの一環として次の手順を実行します。

1. アップグレード中は、 **[構成が完了したら、同期プロセスを開始する]** オプションをオフにします。
2. 複製された結合同期規則を編集し、次の 2 つの変換を追加します。

- ダイレクト フロー `objectGUID` を `sourceAnchorBinary` に設定します。
- 式フロー `ConvertToBase64([objectGUID])` を `sourceAnchor` に設定します。

1. `Set-ADSyncScheduler -SyncCycleEnabled $true` を使用してスケジューラを有効にします。

### 1.5.18.0

#### リリースの状態

2020 年 4 月 2 日ダウンロード対象としてリリース済み

#### 機能変更 ADSyncAutoUpgrade

- グループ オブジェクトの mS-DS-ConsistencyGuid 機能のサポートが追加されました。 グループをフォレスト間で移動したり、AD 内のグループを AD グループの objectID が変更された Microsoft Entra ID に再接続したりすることができます。 詳細については、[フォレスト間でのグループの移動](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-migrate-groups)に関する記事を参照してください。
- mS-DS-ConsistencyGuid 属性は、同期されたすべてのグループに対して自動的に設定されるため、この機能を有効にするために何もする必要はありません。
- Get-ADSyncRunProfile は使用されなくなったため、削除されました。
- AD DS コネクタ アカウントにエンタープライズ管理者またはドメイン管理者アカウントを使用しようとしたときに表示される警告を変更し、より多くのコンテキストを提供します。
- コネクタ スペースからオブジェクトを削除する新しいコマンドレットを追加しました。古い CSDelete.exe ツールは削除され、新しい Remove-ADSyncCSObject コマンドレットに置き換えられています。 Remove-ADSyncCSObject コマンドレットは、入力として CsObject を受け取ります。 このオブジェクトは、Get-ADSyncCSObject コマンドレットを使用して取得できます。

注

以前の CSDelete.exe ツールは削除され、新しい Remove-ADSyncCSObject コマンドレットに置き換えられました。

#### 修正された問題

- 機能を無効にした後に Microsoft Entra Connect ウィザードを再実行するときの、グループ書き戻しのフォレスト/OU セレクターのバグが修正されました。
- 必要な DCOM レジストリ値が見つからない場合に新しいヘルプ リンクと共に表示される、新しいエラーページが導入されました。 情報はログ ファイルにも書き込まれます。
- 使用する前に Microsoft Entra 同期アカウントがすべてのサービス レプリカに伝達されていないために、ディレクトリ拡張または PHS を有効にできないことがあるという、アカウントの作成に関する問題が修正されました。
- 同期エラーの圧縮ユーティリティにおいて、サロゲート文字が正しく処理されないバグを修正しました。
- サーバーがスケジューラの中断状態のままになる自動アップグレードのバグを修正しました。

### 1.4.38.0

#### リリースの状態

2019 年 12 月 9 日:ダウンロード向けリリース。 自動アップグレードでは使用できません。

#### 新機能と機能強化

- Microsoft Entra Domain Services のパスワード ハッシュ同期が更新され、Kerberos ハッシュのパディングが正しく考慮されるようになりました。 Microsoft Entra ID から Microsoft Entra Domain Services へのパスワード同期中のパフォーマンスが向上します。
- 認証エージェントとサービス バスの間の信頼できるセッションのサポートを追加しました。
- 認証エージェントとクラウド サービス間に websocket 接続用の DNS キャッシュを追加しました。
- クラウドから特定のエージェントをターゲットにして、エージェントの接続をテストする機能を追加しました。

#### 修正された問題

- リリース 1.4.18.0 には、DSSO の PowerShell コマンドレットで、PowerShell の実行中に指定される管理者資格情報ではなく、ログインの Windows 資格情報が使用されるバグがありました。 その結果、Microsoft Entra Connect ユーザー インターフェイスを使用して複数のフォレストで DSSO を有効にすることができませんでした。
- Microsoft Entra Connect ユーザー インターフェイスを使用して、すべてのフォレストで同時に DSSO を有効にできるように修正されました。

### 1.4.32.0

#### リリースの状態

11/08/2019:ダウンロード対象としてリリース済み。 自動アップグレードでは使用できません。

重要

このリリースの Microsoft Entra Connect では、内部的なスキーマ変更があるため、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) を使用して AD FS 信頼関係の構成設定を管理する場合は、MSOnline PowerShell モジュールをバージョン 1.1.183.57 以上に更新する必要があります。

#### 修正された問題

このバージョンでは、既存の Microsoft Entra ハイブリッド参加済みデバイスの問題が修正されました。 このリリースには、この問題を修正する新しいデバイス同期規則が含まれています。 この規則の変更により、古いデバイスが Microsoft Entra ID から削除される可能性があります。 これらのデバイス オブジェクトは、条件付きアクセスの認可時に Microsoft Entra ID によって使用されません。 一部のお客様については、この規則の変更によって削除されるデバイスの数が、削除のしきい値を超える場合があります。 Microsoft Entra でのデバイス オブジェクトの削除がエクスポート削除しきい値を超えていることが確認された場合は、[削除のしきい値を超えた場合に削除の実行を許可する方法](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes)に関する記事を参照してください。

### 1.4.25.0

#### リリースの状態

9/28/2019:一部のテナントを対象に自動アップグレード用がリリース。 ダウンロードでは利用できません。

このバージョンでは、前のバージョンから 1.4.18.0 に自動アップグレードされたサーバーの一部で SSPR (セルフサービス パスワード リセット ) とパスワード ライトバックに問題を発生させたバグが修正されました。

#### 修正された問題

特定の状況下では、バージョン 1.4.18.0 に自動アップグレードされたサーバーで、アップグレードの完了後、セルフサービス パスワード リセット とパスワード ライトバックを再度有効にできませんでした。 この自動アップグレードでは、その問題が解決され、セルフサービス パスワード リセット とパスワード ライトバックが再度有効になります。

同期エラーの圧縮ユーティリティにおいて、サロゲート文字が正しく処理されないバグを修正しました。

### 1.4.18.0

警告

このバージョンの Microsoft Entra Connect にアップグレードした後、既存の Microsoft Entra ハイブリッド参加済みデバイスで一部のお客様に問題が発生しているインシデントを調査しています。 Microsoft Entra ハイブリッド参加をデプロイしたお客様は、これらの問題の根本原因が完全に認識され軽減されるまで、このバージョンへのアップグレードを延期することをお勧めします。 詳細については、できるだけ早く提供します。

重要

このバージョンの Microsoft Entra Connect をご利用のお客様に、Windows デバイスの一部または全部が Microsoft Entra ID から消えるという現象が発生することがあります。 これらのデバイス ID が、条件付きアクセスの認可時に Microsoft Entra ID によって使用されることはありません。 詳細については、[Microsoft Entra Connect 1.4.xx.x とデバイスの消失に関する説明](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/reference-connect-device-disappearance)に関する記事を参照してください

#### リリースの状態

9/25/2019:自動アップグレード向けにのみリリース済み。

#### 新機能と機能強化

- 新しいトラブルシューティング ツールは、"ユーザーが同期していない"、"グループが同期していない"、"グループ メンバーが同期していない" という各シナリオのトラブルシューティングに役立ちます。
- Microsoft Entra Connect トラブルシューティング スクリプトに、各国のクラウドのサポートが追加されました。
- MIIS\_Service の非推奨の WMI エンドポイントが削除されたことを顧客に通知する必要があります。 今後、すべての WMI 操作は、PowerShell コマンドレットを使用して実行する必要があります。
- AZUREADSSOACC オブジェクトの制約付き委任のリセットによるセキュリティ強化。
- 同期規則を追加または編集するときに、規則で使用されている属性で、コネクタ スキーマに含まれているがコネクタに追加されていないものがある場合、それらの属性はコネクタに自動的に追加されます。 規則が影響を与えるオブジェクトの種類についても同じことが当てはまります。 コネクタに何かが追加されると、コネクタは次の同期サイクルでフル インポートのマークが付けられます。
- 新しい Microsoft Entra Connect のデプロイでは、エンタープライズ管理者またはドメイン管理者のコネクタ アカウントとしての使用がサポートされなくなりました。 エンタープライズまたはドメイン管理者をコネクタ アカウントとして使用する現在の Microsoft Entra Connect デプロイは、このリリースによる影響を受けません。
- 同期マネージャーでは、規則の作成、編集、削除時に同期が実行されます。 フルインポートまたは完全同期が実行されるときに、ユーザーに通知するすべての規則の変更にポップアップが表示されます。
- [コネクタ] &gt; [プロパティ] &gt; [接続] ページにパスワード エラーの軽減手順が追加されました。
- コネクタのプロパティ ページに、Sync Service Manager の非推奨警告が追加されました。 この警告は、ユーザーに対して、変更を行う際には Microsoft Entra Connect ウィザードを使用する必要があることを通知します。
- ユーザーのパスワード ポリシーに関する問題に対して新しいエラーが追加されました。
- ドメインおよび OU フィルターによるグループ フィルターの構成の誤りを防止します。 入力したグループのドメインまたは OU が既にフィルターで除外されている場合、グループ フィルターでエラーが表示され、その問題が解決されるまでユーザーが先に進まないように抑制されます。
- ユーザーは、Synchronization Service Manager UI で Active Directory Domain Services または Windows Azure Active Directory 用のコネクタを作成できなくなりました。
- Sychronization Service Manager のカスタム UI コントロールのアクセシビリティが修正されました
- Microsoft Entra Connect のすべてのサインイン方法に対して 6 つのフェデレーション管理タスクが有効になりました。 (以前は、すべてのサインインで "AD FS TLS/SSL 証明書の更新" タスクのみ使用できました。)
- フェデレーションから PHS または PTA にサインイン方法を変更したときに表示される、すべての Microsoft Entra ドメインとユーザーがマネージド認証に変換されるという警告が追加されました。
- "Microsoft Entra ID と AD FS 信頼のリセット" タスクからトークン署名証明書が削除され、これらの証明書を更新するための別のサブタスクが追加されました。
- "証明書の管理" という新しいフェデレーション管理タスクが追加されました。これには、AD FS ファームの TLS またはトークン署名証明書を更新するサブタスクが含まれています。
- "プライマリ サーバーの指定" という新しいフェデレーション管理サブタスクが追加されました。これにより、管理者は、AD FS ファームの新しいプライマリ サーバーを指定できます。
- "サーバーの管理" という新しいフェデレーション管理タスクが追加されました。これには、AD FS サーバーのデプロイ、Web アプリケーション プロキシ サーバーのデプロイ、およびプライマリ サーバーの指定を行うサブタスクが含まれています。
- 現在の AD FS 設定を表示する、"フェデレーション構成の表示" という新しいフェデレーション管理タスクが追加されました。 (この追加により、AD FS 設定は [ソリューションのレビュー] ページから削除されました。)

#### 修正された問題

- 対応する連絡先オブジェクトを引き継ぐユーザー オブジェクトが自己参照 (ユーザーが自身のマネージャーであるなど) を持つシナリオでの同期エラーの問題を解決しました。
- ヘルプポップアップがキーボードフォーカスに表示されるようになりました。
- 自動アップグレードでは、競合するアプリが 6 時間実行されている場合は、それを強制終了し、アップグレードを続行します。
- ディレクトリ拡張機能を選択するときに、顧客が選択できる属性の数をオブジェクトあたり 100 個に制限します。 Azure にはオブジェクトあたり最大 100 個の拡張属性があるため、この制限によってエクスポート中のエラーの発生が防止されます。
- AD 接続スクリプトがより堅固になるようにバグを修正しました。
- 既存の名前付きパイプ WCF サービスを使用したマシンへの Microsoft Entra Connect のインストールがより堅牢になるように、バグが修正されました。
- 初期インストール時に ADSync サービスを開始できないグループ ポリシーに関する診断およびトラブルシューティングを機能強化しました。
- Windows コンピューターの表示名が正しく書き込まれなかったバグを修正しました。
- Windows コンピューターの OS の種類が正しく書き込まれなかったバグを修正しました。
- Windows 10 以外のコンピューターが予期せず同期していたバグを修正しました。 この変更の影響として、以前に同期された Windows 10 以外のコンピューターが削除されるようになったことに注意してください。 Windows コンピューターの同期は、Windows 10 デバイスでのみ機能するハイブリッド Microsoft Entra ドメイン参加にのみ使用されるため、これはどの機能にも影響しません。
- ADSync PowerShell モジュールに新しい (内部) コマンドレットをいくつか追加しました。

### 1.3.21.0

重要

Microsoft Entra Connect の以前のバージョンから 1.3.21.0 へのアップグレードに関する既知の問題 (Microsoft Entra Connect が正常にアップグレードされても Microsoft 365 ポータルによって更新されたバージョンが反映されない) があります。

この問題を解決するには、**AdSync** モジュールをインポートしてから、Microsoft Entra Connect サーバー上で `Set-ADSyncDirSyncConfiguration` PowerShell コマンドレットを実行します。 次の手順を使用できます。

1. 管理者モードで PowerShell を開きます。
2. `Import-Module "ADSync"` を実行します。
3. `Set-ADSyncDirSyncConfiguration -AnchorAttribute ""` を実行します。

#### リリースの状態

2019/05/14:ダウンロード対象としてリリース済み

#### 修正された問題

- Microsoft Entra Connect ビルド 1.3.20.0 に存在する、特権の昇格の脆弱性が修正されました。 この脆弱性により、特定の条件下で、攻撃者は特権アカウントのコンテキストで 2 つの PowerShell コマンドレットを実行し、特権の必要なアクションを実行することができる可能性があります。 このセキュリティ更新プログラムは、これらのコマンドレットを無効にすることで問題を修正します。 詳細については、[セキュリティ更新プログラム](https://portal.msrc.microsoft.com/security-guidance/advisory/CVE-2019-1000)に関する記事を参照してください。

### 1.3.20.0

#### リリースの状態

2019/04/24:ダウンロード対象としてリリース済み

#### 新機能と機能強化

- ドメインの更新に対するサポートが追加されました。
- Exchange メールのパブリック フォルダー機能が一般提供されました。
- サービス エラーに対するウィザードのエラー処理が機能強化されました。
- コネクタのプロパティ ページの Sychronization Service Manager UI に警告リンクが追加されました。
- 統合グループの書き戻し機能が一般提供されるようになりました。
- DC で LDAP コントロールがない場合の SSPR エラー メッセージが改善されました。
- インストール時の DCOM レジストリ エラーに対する診断が追加されました。
- PHS RPC エラーのトレースが機能強化されました。
- 子ドメインからの EA 資格情報が許可されるようになりました。
- インストール中のデータベース名の入力が許可されるようになりました (既定名 ADSync)。
- Ping での WS-Trust の修正をピックアップするためと新しい Azure インスタンスに対するサポートを追加するために ADAL 3.19.8 へのアップグレードが行われました。
- samAccountName、DomainNetbios、および DomainFQDN をクラウドに送信するためのグループ同期規則が変更されました (要求で必要)。
- 同期規則の既定の処理が変更されました。詳細については[こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fix-default-rules)を参照してください。
- Windows サービスとして実行する新しいエージェントを追加しました。 "Admin Agent" という名前のこのエージェントでは、Microsoft Entra Connect サーバーの詳細なリモート診断が可能であり、お客様がサポート ケースを開いたときに Microsoft のエンジニアがトラブルシューティングを行う際に役に立ちます。 エージェントはインストールされず、既定で有効になっています。 エージェントをインストールして有効にする方法の詳細については、「[Microsoft Entra Connect 管理エージェントとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-aadc-admin-agent)」を参照してください。
- エンド ユーザー ライセンス契約 (EULA) が更新されました。
- ログインの種類として AD FS を使用するデプロイに対する自動アップグレードのサポートが追加されました。 これにより、アップグレード プロセスの一環として AD FS の Microsoft Entra ID 証明書利用者信頼を更新するという要件が削除されました。
- 信頼の分析/更新および信頼とリセットという 2 つのオプションがある Microsoft Entra ID 信頼管理タスクが追加されました。
- AD FS Microsoft Entra ID 証明書利用者信頼の動作が、常に -SupportMultipleDomain スイッチを使用するように変更されました (信頼と Microsoft Entra ID ドメインの更新が含まれます)。
- インストール済みの証明書を使用するオプションの削除によって、新しい AD FS ファームのインストール動作で .pfx 証明書が必要になるように変更されました。
- 新しい AD FS ファームのワークフローのインストールが、1 台の AD FS と 1 台の WAP サーバーのデプロイのみを許可するように更新されました。 すべてのサーバーの追加は、初期インストールの後で実行されます。

#### 修正された問題

- ADSync サービスの SQL 再接続ロジックが修正されました。
- 空の SQL AOA DB を使用したクリーン インストールを許可するための修正が行われました。
- GWB アクセス許可を絞り込むための PowerShell アクセス許可スクリプトが修正されました
- LocalDB での VSS エラーが修正されました。
- オブジェクトの種類が範囲外の場合の誤解されやすいエラー メッセージが修正されました
- サーバーに Microsoft Graph PowerShell をインストールすると、Microsoft Entra Connect とのアセンブリの競合が発生する可能性があるという問題が修正されました。
- Sychronization Service Manager UI でコネクタの資格情報が更新されるときのステージング サーバーでの PHS のバグが修正されました。
- いくつかのメモリ リークが修正されました。
- 自動更新に関するさまざまな修正が行われました。
- エクスポートと未確認のインポート処理に対するさまざまな修正が行われました。
- ドメインと OU のフィルター処理でのバックスラッシュの処理のバグが修正されました。
- ADSync サービスが停止するまで 2 分以上かかり、アップグレード時間に問題が発生するという問題を修正しました。

### 1.2.70.0

#### リリースの状態

2018/12/18:ダウンロード対象としてリリース済み

#### 修正された問題

このビルドでは、Microsoft Entra Connect に付属の非標準コネクタ (Generic LDAP コネクタや Generic SQL コネクタなど) が更新されます。 適用可能なコネクタの詳細については、「[コネクタ バージョンのリリース履歴](https://learn.microsoft.com/ja-jp/microsoft-identity-manager/reference/microsoft-identity-manager-2016-connector-version-history)」でバージョン 1.1.911.0 を参照してください。

### 1.2.69.0

#### リリースの状態

2018 年 12 月 11 日:ダウンロード対象としてリリース済み

#### 修正された問題

この修正プログラムのビルドでは、デバイス ライトバックを有効にするときに、RegisteredDevices コンテナーに対して、指定されたフォレスト内でターゲット ドメインを選択できます。 新しいデバイス オプション機能が含まれる、以前のバージョン (1.1.819.0 から 1.2.68.0) で、RegisteredDevices コンテナーの場所はフォレストのルートに制限されており、子ドメインが許可されていませんでした。 この制限は、新しいデプロイのみで表面化し、配置済みのアップグレードは影響を受けませんでした。

更新されたデバイスのオプション機能を含む任意のビルドを新しいサーバーにデプロイして、デバイス ライトバックを有効にする場合、フォレスト ルート内に配置しないためには、コンテナーの場所を手動で指定する必要があります。 これを行うには、[ライトバック フォレスト] ページで、デバイス ライトバックを無効にして、コンテナーの場所を指定できるように再度有効にする必要があります。

### 1.2.68.0

#### リリースの状態

2018 年 11 月 30 日: ダウンロード対象としてリリース済み

#### 修正された問題

この修正プログラムのビルドでは、同期サーバー上の Microsoft Graph PowerShel ギャラリー モジュールが独立して存在するため、認証エラーが発生する可能性がある競合が修正されます。

### 1.2.67.0

#### リリースの状態

2018 年 11 月 19 日: ダウンロード対象としてリリース済み

#### 修正された問題

この修正プログラムのビルドでは、Windows Server 2008/R2 で ADDS ドメイン コントローラーを使用するとパスワード ライトバックが失敗する前回のビルドでの回帰が修正されます。

### 1.2.65.0

#### リリースの状態

2018 年 10 月 25 日: ダウンロード用にリリース済み

#### 新機能と機能強化

- ホストされているボイス メールが期待どおりに動作していることを確認するために、属性の書き戻し機能が変更されました。 一部のシナリオでは、Microsoft Entra ID は、null 値での書き戻し中に msExchUcVoicemailSettings 属性を上書きしていました。 現在は、クラウドの値が設定されていない場合は、Microsoft Entra ID でこの属性のオンプレミスの値がクリアされないようになりました。
- Microsoft Entra ID への接続の問題を調査および特定するために、Microsoft Entra Connect ウィザードに診断が追加されました。 これらと同じ診断は、Test- AdSyncAzureServiceConnectivity コマンドレットを使用して PowerShell を介して直接実行することもできます。
- AD への接続の問題を調査および特定するために、Microsoft Entra Connect ウィザードに診断が追加されました。 これらと同じ診断は、ADConnectivityTools PowerShell モジュールの Start-ConnectivityValidation 関数を使用して、PowerShell を介して直接実行することもできます。 詳細については、[ADConnectivityTool PowerShell モジュール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-adconnectivitytools)に関するページを参照してください
- Microsoft Entra ハイブリッド参加とデバイスの書き戻し用の AD スキーマのバージョンの事前チェックが追加されました。
- ディレクトリ拡張ページ属性の検索で大文字と小文字が区別されないように変更されました。
- TLS 1.2 の完全なサポートが追加されました。 このリリースでは、Microsoft Entra Connect がインストールされているマシン上で、他のすべてのプロトコルを無効にし、TLS 1.2 のみを有効にすることがサポートされています。 詳細については、「[Microsoft Entra Connect に対する TLS 1.2 の適用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-tls-enforcement)」を参照してください。

#### 修正された問題

- SQL Always On が使用されている場合に Microsoft Entra Connect のアップグレードが失敗するバグが修正されました。
- スラッシュが含まれている OU 名が正しく解析されるよう、バグを修正しました。
- ステージング モードでのクリーン インストールの場合にパススルー認証が無効になる問題を修正しました。
- トラブルシューティング ツールの実行中に PowerShell モジュールの読み込みを妨げるバグを修正しました
- 顧客がホスト名の最初の文字に数値を使用できないバグを修正しました。
- Microsoft Entra Connect が無効なパーティションとコンテナーの選択を許可するバグが修正されました。
- デスクトップ SSO が有効になっている場合の「無効なパスワードです」というエラー メッセージを修正しました。
- AD FS 信頼管理に関するさまざまなバグの修正
- デバイスの書き戻しの構成時 - msDs-DeviceContainer オブジェクト クラス (WS2012 R2 で導入) を検索するためのスキーマの確認を修正しました

### 1.1.882.0

2018 年 9 月 7 日: ダウンロード用にリリース済み。自動アップグレード用にはリリースされません

#### 修正された問題

SQL Always On 可用性が ADSync DB に対して構成されている場合に Microsoft Entra Connect のアップグレードが失敗します。 この修正プログラムはこの問題に対処し、アップグレードが成功するようにします。

### 1.1.880.0

#### リリースの状態

2018 年 8 月 21 日:ダウンロードと自動アップグレード向けにリリース済み。

#### 新機能と機能強化

- Microsoft Entra Connect の Ping Federate 統合が一般提供されました。 [Microsoft Entra ID と Ping Federate のフェデレーションの詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin#federation-with-pingfederate)。
- Microsoft Entra Connect は、Microsoft Entra ID 信頼のバックアップを更新のたびに AD FS に作成し、さらに、必要に応じて簡単に復元できるよう別個のファイルにそれを格納するようになりました。 [Microsoft Entra Connectの新機能と Microsoft Entra ID 信頼の管理の詳細については、こちらを参照してください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-azure-ad-trust)。
- 通常の電子メール アドレスを変更したり、グローバル アドレス一覧に対してアカウントを非表示にしたりする際に発生した問題のトラブルシューティングを支援するトラブルシューティング ツールが導入されました。
- Microsoft Entra Connect が更新されて、最新の SQL Server 2012 Native Client が追加されました
- [ユーザー サインインの変更] タスクで、ユーザーのサインインをパスワード ハッシュ同期またはパススルー認証に切り替えたとき、[シームレスなシングル サインオン] チェック ボックスが既定で有効になります。
- Windows Server Essentials 2019 のサポートが追加されました
- Microsoft Entra Connect Health エージェントが最新バージョンの 3.1.7.0 に更新されました
- アップグレード中、既定の同期規則に対する変更をインストーラーが検出した場合、変更された規則を上書きする前に、管理者に警告が表示されます。 ユーザーは是正措置を講じたうえで、後から再開することができます。 従来は、標準の規則に変更が加えられていた場合、それらの規則は、ユーザーへの警告なしで手動アップグレードによって上書きされ、同期スケジューラは、ユーザーへの通知なしで無効にされていました。 今後は、標準の同期規則に変更が加えられていた場合、それらを上書きする前にユーザーに警告が表示されます。 ユーザーはアップグレード処理を停止し、是正措置を講じたうえで後から再開することができます。
- FIPS への準拠に関する問題の処理能力が向上しました。FIPS に準拠している環境で MD5 ハッシュ生成のエラー メッセージが表示されるようになったほか、この問題の回避策を記載したドキュメントへのリンクが提供されます。
- ウィザードのフェデレーション関連タスクの改善を図るために UI が更新されています。これらのタスクは、フェデレーション用の独立したサブ グループに表示されます。
- フェデレーション関連の追加タスクはすべて、使いやすいよう単一のサブメニューにグループ化されています。
- 新しく改良された ADSyncConfig Posh Module (AdSyncConfig.psm1) と新しい AD Permissions 機能が、以前の ADSyncPrep.psm1 (間もなく非推奨となる予定) から移動されました

#### 修正された問題

- .NET 4.7.2 へのアップグレード後に Microsoft Entra Connect サーバーで高い CPU 使用率が表示されるバグが修正されました
- 自動的に解決された SQL デッドロックの問題に関して、エラー メッセージが断続的に生成されるバグを修正しました
- Sync Rules Editor と Sync Service Manager のアクセシビリティに関するいくつかの問題を修正しました
- Microsoft Entra Connect がレジストリ設定情報を取得できないバグが修正されました
- ウィザードでユーザーが前後に移動する際の問題を引き起こしていたバグを修正しました
- ウィザード内でのマルチスレッド処理の誤りに起因したエラーの発生を防ぐためにバグを修正しました
- Group Sync Filtering ページでセキュリティ グループを解決する際に LDAP エラーが発生した場合に、詳細な情報を忠実に含んだ例外が Microsoft Entra Connect から返されるようになりました。 紹介例外の根本的な原因はまだ不明であり、別のバグで対処される予定です。
- STK キーと NGC キー (WHfB のユーザー/デバイス オブジェクトの ms-DS-KeyCredentialLink 属性) のアクセス許可が正しく設定されないバグが修正されました。
- "Set-ADSyncRestrictedPermissions" が正しく呼び出されないバグを修正しました
- Microsoft Entra Connect のインストール ウィザードで、グループの書き戻しに対するアクセス許可を付与できるようになりました
- サインイン方法をパスワード ハッシュ同期から AD FS に変更してもパスワード ハッシュ同期が無効になりませんでした。
- AD FS の構成で IPv6 アドレスの検証を追加しました
- 既存の構成が存在することを知らせるための通知メッセージを更新しました。
- デバイス ライトバックで、信頼されていないフォレストのコンテナーを検出することはできません。 この点について、エラー メッセージと適切なドキュメントへのリンクを提供するように更新しました
- OU の選択を解除すると、その OU に対応する同期/書き戻しで一般的な同期エラーが発生します。 この点について、もっと理解しやすいエラー メッセージが表示されるように変更されています。

### 1.1.819.0

#### リリースの状態

2018 年 5 月 14 日:自動アップグレードとダウンロード向けにリリース済み。

#### 新機能と機能強化

新機能と機能強化

- このリリースには、Microsoft Entra Connect での PingFederate の統合のパブリック プレビューが含まれます。 このリリースでは、PingFederate をフェデレーション プロバイダーとして使用するように Microsoft Entra 環境を簡単かつ確実に構成できます。 この新しい機能を使用する方法の詳細については、こちらの[オンライン ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-user-signin#federation-with-pingfederate)を参照してください。
- Microsoft Entra Connect ウィザードのトラブルシューティング ユーティリティが更新され、分析されるエラー シナリオが増えました (リンクされたメールボックスや AD の動的グループなど)。 トラブルシューティング ユーティリティの詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-objectsync)を参照してください。
- デバイスの書き戻しの構成は、Microsoft Entra Connect ウィザード内でのみ管理されるようになりました。
- SQL 接続の問題とその他のさまざまなトラブルシューティング ユーティリティをトラブルシューティングするために使用できる ADSyncTools.psm1 という名前の新しい PowerShell モジュールが追加されました。 ADSyncTools モジュールの詳細については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-tshoot-sql-connectivity)を参照してください。
- 新しいタスクである "デバイス オプションの構成" が追加されました。 このタスクを使用して、次の 2 つの操作を構成できます。
- **Microsoft Entra ハイブリッド参加**: 環境にオンプレミスの AD フットプリントがあるときに、Microsoft Entra ID が提供する機能のメリットを活用したい場合は、Microsoft Entra ハイブリッド参加デバイスを実装できます。 これらのデバイスは、オンプレミスの Active Directory と Microsoft Entra ID の両方に参加しているデバイスです。
- **デバイス ライトバック**:デバイス ライトバックを使うと、AD FS (2012 R2 以降) で保護されているデバイスへの、デバイスに基づく条件付きアクセスを有効にできます。

注

- 同期カスタマイズ オプションからデバイスの書き戻しを有効にするオプションはグレー表示されます。
- このリリースでは、ADPrep の PowerShell モジュールは非推奨になりました。

#### 修正された問題

- このリリースでは、SQL Server 2012 SP4 への SQL Server Express のインストールを更新します。とりわけ、複数のセキュリティの脆弱性の修正プログラムを提供します。 SQL Server 2012 SP4 の詳細については、[こちら](https://support.microsoft.com/help/4018073/sql-server-2012-service-pack-4-release-information)を参照してください。
- 同期ルールの処理: 親同期規則が適用可能でなくなった場合、結合条件のない送信結合同期規則は適用されない
- 複数のアクセシビリティの修正が Synchronization Service Manager の UI と Sync Rules Editor に適用されている
- Microsoft Entra Connect ウィザード: Microsoft Entra Connect がワークグループに含まれている場合に、AD コネクタ アカウントの作成時にエラーが発生します
- Microsoft Entra Connect ウィザード: AD ドメインと Microsoft Entra ID の確認済みドメインが一致しない場合に、常にMicrosoft Entra サインイン ページに検証チェックボックスが表示されます
- 自動アップグレードの試行後、特定の状況で自動アップグレードの状態を正しく設定するように自動アップグレード PowerShell を修正
- Microsoft Entra Connect ウィザード: 前回見逃していた情報をキャプチャするようにテレメトリが更新されました
- Microsoft Entra Connect ウィザード: AD FS からパススルー認証に切り替えるために**ユーザー サインインの変更**タスクを使用する際に、次の変更が加えられました。
    - ドメインをフェデレーションから管理対象に変換する前に、Microsoft Entra Connect サーバーにパススルー認証エージェントがインストールされ、パススルー認証機能が有効になります。
    - ユーザーがフェデレーションから管理対象に変換されることはなくなります。 ドメインのみが変換されます。
- Microsoft Entra Connect ウィザード: ユーザーの UPN に ' 特殊文字が含まれている場合に、AD FS マルチドメイン Regex が正確ではありません。特殊文字をサポートするように Regex が更新されました
- Microsoft Entra Connect ウィザード: 変更がない場合の偽の "ソース アンカー属性を構成します" メッセージが削除されました
- Microsoft Entra Connect ウィザード: AD FS でデュアル フェデレーション シナリオがサポートされるようになりました
- Microsoft Entra Connect ウィザード: マネージド ドメインのフェデレーションへの変換時に追加されたドメインで AD FS 要求が更新されません
- Microsoft Entra Connect ウィザード: インストール済みのパッケージの検出中に古い Dirsync/Azure AD Sync/Azure AD Connect 関連製品が検出されます。 今後、古い製品がアンインストールされるように修正
- Microsoft Entra Connect ウィザード: パススルー認証エージェントのインストールが失敗した場合のエラー メッセージのマッピングが修正されました
- Microsoft Entra Connect ウィザード: ドメイン OU フィルタリング ページから "構成" コンテナーが削除されました
- 同期エンジンのインストール: 失敗することがある不要なレガシ ロジックを同期エンジンのインストール msi から削除
- Microsoft Entra Connect ウィザード: パスワード ハッシュ同期の [オプション機能] ページのポップアップ ヘルプ テキストが修正されました
- 同期エンジン ランタイム:CS オブジェクトに削除がインポートされているときに同期規則がそのオブジェクトの再プロビジョニングを試行するシナリオを修正
- 同期エンジン ランタイム:インポート エラーのイベント ログにオンライン接続トラブルシューティング ガイドへのリンクを追加
- 同期エンジン ランタイム:コネクタの列挙時に同期スケジューラが使用するメモリの量を削減
- Microsoft Entra Connect ウィザード: AD 読み取り特権がないカスタム同期サービス アカウントの解決問題が修正されました
- Microsoft Entra Connect ウィザード: ドメインと OU のフィルター選択のログ記録が改善されました
- Microsoft Entra Connect ウィザード: MFA シナリオ用に作成されたフェデレーションの信頼に AD FS の既定の要求が追加されました
- Microsoft Entra Connect ウィザード: AD FS デプロイ WAP: 新しい証明書を使用するサーバーの追加が失敗します
- Microsoft Entra Connect ウィザード: OnPremCredentials がドメインに対して初期化されていない場合に DSSO 例外が発生します
- アクティブなユーザー オブジェクトから AD の distinguishedName属性を優先的にフロー
- 最初の OOB の同期ルールの優先順位が 100 ではなく 99 に設定される表面的なバグを修正

### 1.1.751.0

状態: 2018 年 4 月 12 日:ダウンロード用のみにリリース済み

注

このリリースは Microsoft Entra Connect  の修正プログラムです

#### Microsoft Entra Connect 同期

##### 修正された問題

中国のテナント用の Azure の自動インスタンス検出が失敗することがある問題が修正されました。

#### AD FS の管理

##### 修正された問題

構成再試行ロジックに問題があり、"同一のキーを含む項目が既に追加されています" を示す ArgumentException が発生していました。 これにより、再試行操作がすべて失敗します。

### 1.1.750.0

状態: 2018 年 3 月 22 日:自動アップグレードとダウンロード向けにリリース済み。

注

この新しいバージョンへのアップグレードが完了すると、Microsoft Entra コネクタの完全同期とフル インポート、および AD コネクタの完全同期が自動的にトリガーされます。 Microsoft Entra Connect 環境のサイズによっては、これには時間がかかる場合があるため、これに対応できるように必要な手順を確実に実施していることを確認してください。また、好都合なタイミングが見つかるまでアップグレードを見合わせるようにしてください。

注

“1.1.524.0 より後にビルドをデプロイしたテナントの一部で、AutoUpgrade 機能が間違って無効化されました。 Microsoft Entra Connect インスタンスで引き続き AutoUpgrade 機能が適用されるように、PowerShell コマンドレット "Set-ADSyncAutoUpgrade -AutoupGradeState Enabled" を実行してください。

#### Microsoft Entra Connect

##### 修正された問題

- 自動アップグレードの状態が一時停止に設定されている場合に、Set-ADSyncAutoUpgrade コマンドレットによって Autoupgrade がブロックされます。 この機能は変更されました。将来のビルドでは AutoUpgrade はブロックされません。
- **ユーザー サインイン** ページの "パスワード同期" オプションが "パスワード ハッシュの同期" オプションに変更されました。 Microsoft Entra Connect ではパスワードではなくパスワード ハッシュが同期されるため、この変更は実際の動作と一致しています。 詳細については、「[Microsoft Entra Connect 同期を使用したパスワード ハッシュ同期の実装](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization)」を参照してください

### 1.1.749.0

状態:一部のお客様にリリース

注

この新しいバージョンへのアップグレードが完了すると、Microsoft Entra コネクタの完全同期とフル インポート、および AD コネクタの完全同期が自動的にトリガーされます。 Microsoft Entra Connect 環境のサイズによっては、これには時間がかかる場合があるため、これに対応できるように必要な手順を確実に実施していることを確認してください。また、好都合なタイミングが見つかるまでアップグレードを見合わせるようにしてください。

#### Microsoft Entra Connect

##### 修正された問題

- 次のページに切り替えるときの、パーティション フィルター処理ページのバック グラウンド タスクにおけるタイミング ウィンドウの修正。
- ConfigDB カスタム アクションの実行中にアクセス違反を引き起こしていたバグを修正しました
- SQL 接続のタイムアウトから復旧できるようにバグを修正しました。
- SAN ワイルドカードを含む証明書で前提条件の確認に失敗するというバグを修正しました。
- Microsoft Entra コネクタのエクスポート中に miiserver.exe がクラッシュするバグが修正されました。
- Microsoft Entra Connect ウィザードを実行しているときに DC に間違ったパスワードの試行が記録されると構成が変更されるバグが修正されました。

##### 新機能と機能強化

- 一般データ保護規則 (GDPR) のプライバシー設定の追加。 詳しくは、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-user-privacy)の記事をご覧ください。

注

この記事は、デバイスまたはサービスから個人データを削除する手順について説明しており、GDPR の下で義務を果たすために使用できます。 GDPR に関する一般情報については、[Microsoft Trust Center の GDPR に関するセクション](https://www.microsoft.com/trust-center/privacy/gdpr-overview)および [Service Trust Portal の GDPR に関するセクション](https://servicetrust.microsoft.com/ViewPage/GDPRGetStarted)をご覧ください。

- アプリケーション テレメトリ - このデータ クラスのオン/オフは、管理者が任意に切り替えることができます
- Microsoft Entra Health データ - 正常性の設定を制御するには、管理者が正常性ポータルにアクセスする必要があります。 サービス ポリシーが変更された場合、エージェントがこれを読み取って強制します。
- デバイスの書き戻し構成アクションと、ページの初期化の進行状況バーを追加しました
- HTML レポートでの一般的な診断と、ZIP テキスト/HTML レポートの完全なデータ コレクションが向上しました
- 自動アップグレードの信頼性が向上しました。また、サーバーの正常性を確認できるように、テレメトリを追加しました
- AD コネクタ アカウントの特権アカウントで使用できるアクセス許可の制限
- 新規インストールの場合、Microsoft Graph PowerShell アカウントの作成後、Microsoft Graph PowerShell アカウントに対して特権アカウントが持つアクセス許可がウィザードによって制限されます。

変更により、次に対応します。

1. 高速インストール
2. 自動作成アカウントでのカスタム インストール
3. Microsoft Entra Connect のクリーン インストールで SA 特権が求められないようにインストーラーが変更されました

- 特定のオブジェクトの同期に関する問題のトラブルシューティングを行う新しいユーティリティを追加しました。 これは、Microsoft Entra Connect ウィザードのトラブルシューティングの追加タスクにある [Troubleshoot Object Synchronization]\(オブジェクトの同期のトラブルシューティング\) オプションで使用できます。 現時点では、このユーティリティは以下を確認します。
- 同期されたユーザー オブジェクトと Microsoft Entra テナントのユーザー アカウントの間で UserPrincipalName の不一致が発生します。
- ドメインのフィルター処理によって、オブジェクトが同期からフィルター処理されているかどうか
- 組織単位 (OU) のフィルター処理によって、オブジェクトが同期からフィルター処理されているかどうか
- 特定のユーザー アカウントを対象に、オンプレミス Active Directory に格納されている現在のパスワード ハッシュを同期する新しいユーティリティを追加しました。

このユーティリティでは、パスワードの変更は必要ありません。 これは、Microsoft Entra Connect ウィザードのトラブルシューティングの追加タスクにある [Troubleshoot Password Hash Synchronization]\(パスワード ハッシュ同期のトラブルシューティング\) オプションで使用できます。

### 1.1.654.0

状態:2017 年 12 月 12 日

注

このリリースは、Microsoft Entra Connect  のセキュリティに関連する修正プログラムです

#### Microsoft Entra Connect

Microsoft Entra Connect バージョン 1.1.654.0 (以降) が強化され、Microsoft Entra Connect が AD DS アカウントを作成するときに「AD DS アカウントへのアクセスのロックダウン」のセクションで説明されている推奨されるアクセス許可の変更が自動的に適用されるようになりました。

- Microsoft Entra Connect をセットアップするときにインストールを実行する管理者は、既存の AD DS アカウントを指定するか、Microsoft Entra Connect でアカウントを自動的に作成できます。 アクセス許可の変更は、セットアップ中に Microsoft Entra Connect によって作成される AD DS アカウントに自動的に適用されます。 インストールを実行する管理者が指定した既存の AD DS アカウントには適用されません。
- 以前のバージョンの Microsoft Entra Connect から 1.1.654.0 (またはそれ以降) にアップグレードしたお客様の場合、アクセス許可の変更は、アップグレードの前に作成された既存の AD DS アカウントにさかのぼって適用されません。 アップグレード後に作成された新しい AD DS アカウントにのみ適用されます。 これは、Microsoft Entra ID に同期する新しい AD フォレストを追加するときに行われます。

注

このリリースでは、サービス アカウントがインストール プロセスによって作成される Microsoft Entra Connect の新規インストールに対してのみ脆弱性が除去されます。 既存のインストールの場合、または自分でアカウントを指定する場合は、この脆弱性が存在しないことを確認する必要があります。

##### AD DS アカウントへのアクセスのロックダウン

オンプレミスの AD で次のアクセス許可の変更を実装して、AD DS アカウントへのアクセスをロックダウンします。

- 指定したオブジェクトの継承を無効にします
- SELF に固有の ACE を除き、特定のオブジェクトのすべての ACE を削除します。 SELF については、既定のアクセス許可を維持します。
- 以下の特定のアクセス許可を割り当てます。

| タイプ | 名前 | アクセス | 適用対象 |
| --- | --- | --- | --- |
| 許可する | 制 | フル コントロール | このオブジェクト |
| 許可する | エンタープライズ管理者 | フル コントロール | このオブジェクト |
| 許可する | ドメイン管理者 | フル コントロール | このオブジェクト |
| 許可する | 管理者 | フル コントロール | このオブジェクト |
| 許可する | エンタープライズ ドメイン コントローラー | コンテンツの一覧 | このオブジェクト |
| 許可する | エンタープライズ ドメイン コントローラー | すべてのプロパティの読み取り | このオブジェクト |
| 許可する | エンタープライズ ドメイン コントローラー | 読み取りのアクセス許可 | このオブジェクト |
| 許可する | 認証済みユーザー | コンテンツの一覧 | このオブジェクト |
| 許可する | 認証済みユーザー | すべてのプロパティの読み取り | このオブジェクト |
| 許可する | 認証済みユーザー | 読み取りのアクセス許可 | このオブジェクト |

##### 既存のサービス アカウントを強化する PowerShell スクリプト

PowerShell スクリプトを使用して、(組織で指定した、または Microsoft Entra Connect の以前のインストールによって作成された) 既存の AD DS アカウントにこれらの設定を適用するには、上記のリンクからスクリプトをダウンロードしてください。

###### 使用法:

```powershell
Set-ADSyncRestrictedPermissions -ObjectDN <$ObjectDN> -Credential <$Credential>
```

どこ

**$ObjectDN** = アクセス許可のセキュリティを強化する必要がある Active Directory アカウント。

**$Credential** = $ObjectDN アカウントに対するアクセス許可を制限するために必要な権限を持つ管理者資格情報。 通常、これらの権限はエンタープライズ管理者またはドメイン管理者が持っています。 アカウント参照の失敗を回避するには、管理者アカウントの完全修飾ドメイン名を使用します。 例: contoso.com\admin。

注

$credential.UserName は FQDN\username 形式である必要があります。 例: contoso.com\admin

###### 例:

```powershell
Set-ADSyncRestrictedPermissions -ObjectDN "CN=TestAccount1,CN=Users,DC=bvtadwbackdc,DC=com" -Credential $credential 
```

#### この脆弱性が未承認のアクセスに使用されたか

この脆弱性が Microsoft Entra Connect 構成の侵害に使用されたかどうかを確認するには、サービス アカウントのパスワードが最後にリセットされた日付を確認する必要があります。 予期しないタイムスタンプがある場合は、イベント ログでそのパスワードのリセット イベントをさらに調査する必要があります。

詳しくは、[マイクロソフト セキュリティ アドバイザリ 4056318](https://learn.microsoft.com/ja-jp/security-updates/securityadvisories/2017/4056318) をご覧ください

### 1.1.649.0

状態:2017 年 10 月 27 日

注

このビルドは、Microsoft Entra Connect の自動アップグレード機能では提供されません。

#### Microsoft Entra Connect

##### 修正された問題

- Microsoft Entra Connect と Microsoft Entra Connect Health エージェント (同期用) の間のバージョンの互換性に関する問題が修正されました。 この問題は、Microsoft Entra Connect のバージョン 1.1.647.0 へのインプレース アップグレードを実行していて、現在の Health エージェントのバージョンが 3.0.127.0 である場合に影響があります。 アップグレード後に、Health エージェントは Microsoft Entra Connect 同期サービスについての正常性データを Microsoft Entra Health サービスに送信できなくなります。 この修正により、Microsoft Entra Connect のインプレース アップグレード中に Health エージェントのバージョン 3.0.129.0 がインストールされます。 Health エージェントのバージョン 3.0.129.0 には、Microsoft Entra Connect バージョン 1.1.649.0 との互換性の問題はありません。

### 1.1.647.0

状態:2017 年 10 月 19 日

重要

Microsoft Entra Connect バージョン 1.1.647.0 と Microsoft Entra Connect Health エージェント (同期用) バージョン 3.0.127.0 間で、互換性に関する既知の問題があります。 この問題によって、Health エージェントは、Microsoft Entra Connect 同期サービスに関する正常性データ (オブジェクト同期エラーと実行履歴データを含む) を Microsoft Entra Health サービスに送信できません。 Microsoft Entra Connect のデプロイをバージョン 1.1.647.0 に手動でアップグレードする前に、Microsoft Entra Connect サーバーにインストールされている Microsoft Entra Connect Health エージェントの現在のバージョンを確認してください。 これを実行するには、*[コントロール パネル] → [プログラムの追加と削除]* の順に選択し、*[Microsoft Entra Connect Health Agent for Sync]\(同期用 Microsoft Entra Connect Health エージェント\)* のアプリケーションを探します。このバージョンが 3.0.127.0 の場合は、Microsoft Entra Connect の次のバージョンが利用可能になってからアップグレードすることをお勧めします。 Health エージェントのバージョンが 3.0.127.0 でない場合は、手動でインプレース アップグレードを続行できます。 この問題は、スウィング アップデートや、Microsoft Entra Connect の新しいインストールを実行しているお客様には影響しません。

#### Microsoft Entra Connect

##### 修正された問題

- Microsoft Entra Connect ウィザードの *[ユーザー サインインの変更]* タスクの問題が修正されました。
- この問題は、Microsoft Entra Connect の既存のデプロイでパスワード同期が**有効**になっており、ユーザーのサインイン方法を "パススルー認証" に設定しようとするときに発生します。 変更が適用される前に、ウィザードに "*パスワード同期を無効にする*" という内容のメッセージが間違って表示されます。 ただし、変更が適用された後もパスワード同期は引き続き有効です。 この修正により、今後はウィザードでこのメッセージが表示されることはありません。
- 仕様上、お客様が *[ユーザー サインインの変更]* タスクを使用してユーザーのサインイン方法を更新するときに、ウィザードによってパスワード同期が無効化されることはありません。 これは、ユーザーの主要なサインイン方法としてパススルー認証またはフェデレーションを有効にする場合でも、パスワード同期を維持する必要があるお客様への中断を避けるためです。
- ユーザーのサインイン方法を更新した後にパスワード同期を無効にする場合は、ウィザードで *[Customize Synchronization Configuration](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/同期構成のカスタマイズ)* タスクを実行する必要があります。 *[Optional features](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/オプション機能)* ページに移動して、 *[パスワード同期]* オプションをオフにします。
- シームレス シングル サインオンを有効または無効にしようとする場合にも、同じ問題が発生することに注意してください。 具体的には、Microsoft Entra Connect の既存のデプロイでパスワード同期が有効になっており、ユーザーのサインイン方法が既に "パススルー認証" に構成されている場合です。 ユーザーのサインイン方法が "パススルー認証" に構成されたままで、*[ユーザー サインインの変更]* タスクを使用して、*[Enable Seamless Single Sign-On]\(シームレス シングル サインオンを有効にする\)* オプションをオンまたはオフにしようとします。 変更が適用される前に、ウィザードに "*パスワード同期を無効にする*" という内容のメッセージが間違って表示されます。 ただし、変更が適用された後もパスワード同期は引き続き有効です。 この修正により、今後はウィザードでこのメッセージが表示されることはありません。
- Microsoft Entra Connect ウィザードの *[ユーザー サインインの変更]* タスクの問題が修正されました。
- この問題は、Microsoft Entra Connect の既存のデプロイでパスワード同期が**無効**になっており、ユーザーのサインイン方法を "パススルー認証" に設定しようとしているときに発生します。 変更が適用されるときに、ウィザードによってパススルー認証とパスワード同期の両方が有効になります。 この修正により、ウィザードでパスワード同期が有効化されなくなりました。
- 以前は、パスワード同期は、パススルー認証を有効にするための前提条件でした。 ユーザーのサインイン方法を*パススルー認証*に設定すると、ウィザードによってパススルー認証とパスワード同期の両方が有効化されていました。 最近、パスワード同期が前提条件から削除されました。 Microsoft Entra Connect バージョン 1.1.557.0 の一環として、ユーザーのサインイン方法が*パススルー認証*に設定される場合にパスワード同期を有効にしない変更が、Microsoft Entra Connect に対して行われました。 ただし、この変更は Microsoft Entra Connect のインストールにのみ適用されていました。 この修正により、同じ変更が *[ユーザー サインインの変更]* タスクにも適用されます。
- シームレス シングル サインオンを有効または無効にしようとする場合にも、同じ問題が発生することに注意してください。 具体的には、Microsoft Entra Connect の既存のデプロイでパスワード同期が無効になっており、ユーザーのサインイン方法が既に "パススルー認証" に構成されている場合です。 ユーザーのサインイン方法が "パススルー認証" に構成されたままで、*[ユーザー サインインの変更]* タスクを使用して、*[Enable Seamless Single Sign-On]\(シームレス シングル サインオンを有効にする\)* オプションをオンまたはオフにしようとします。 変更が適用されるときに、ウィザードによってパスワード同期が有効になります。 この修正により、ウィザードでパスワード同期が有効化されなくなりました。
- Microsoft Entra Connect のアップグレードが失敗して "*Unable to upgrade the Synchronization Service*"\(同期サービスをアップグレードできません\) というエラーが表示される問題が修正されました。 また、今後は同期サービスの起動時にイベント エラー "*The service was unable to start because the version of the database is newer than the version of the binaries installed*"(データベースのバージョンが、インストールされているバイナリのバージョンよりも新しいため、サービスを開始できませんでした) が表示されることはありません。 この問題は、アップグレードを実行している管理者が、Microsoft Entra Connect で使用されている SQL サーバーに対して sysadmin 権限を持っていない場合に発生します。 この修正により、Microsoft Entra Connect では、管理者に対してアップグレード時に ADSync データベースに対する db\_owner 権限を持っていることのみが求められるようになります。
- [シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)を有効にしているお客様に影響を与える、Microsoft Entra Connect のアップグレードの問題が修正されました。 Microsoft Entra Connect をアップグレードした後に、シームレス シングル サインオンが引き続き有効で完全に機能するにもかかわらず、Microsoft Entra Connect ウィザードに "無効" と間違って表示されます。 この修正により、この機能がウィザードで正しく "有効" と表示されるようになりました。
- Microsoft Entra Connect ウィザードで、ソース アンカーに関連する変更が行われていない場合にも、*[構成の準備完了]* ページに "ソース アンカーの構成" メッセージが常に表示される問題が修正されました。
- Microsoft Entra Connect のインプレース アップグレードを手動で実行する際、お客様には、対応する Microsoft Entra テナントのグローバル管理者の資格情報を入力することが求められます。 以前は、入力されたグローバル管理者の資格情報が別の Microsoft Entra テナントに属している場合でも、アップグレードを続行できました。 アップグレードが正常に完了したように見えても、特定の構成はアップグレードで正しく保持されません。 この変更により、入力された資格情報が該当する Microsoft Entra テナントに一致しない場合、ウィザードでアップグレードを続行できなくなります。
- 手動でのアップグレードの開始時に Microsoft Entra Connect Health サービスを不必要に再起動させる冗長なロジックが削除されました。

##### 新機能と機能強化

- Microsoft Germany Cloud で Microsoft Entra Connect を設定するために必要な手順を簡略化するロジックが追加されました。 以前は、この記事で説明しているように、Microsoft Germany Cloud で正しく機能するためには、Microsoft Entra Connect サーバー上の特定のレジストリ キーを更新する必要がありました。 現在は、セットアップ時に入力されたハイブリッド ID 管理者の資格情報に基づいて、Microsoft Germany Cloud 内に存在するテナントかどうかが Microsoft Entra Connect で自動的に検出されるようになりました。

#### Microsoft Entra Connect 同期

注

注:同期サービスには、独自のカスタム スケジューラを作成できる WMI インターフェイスがあります。 このインターフェイスは現在非推奨であり、2018 年 6 月 30 日以降にリリースされる Microsoft Entra Connect の将来のバージョンから削除される予定です。 同期スケジュールをカスタマイズしようとする顧客は、[組み込みスケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler)を使用する必要があります。

##### 修正された問題

- Microsoft Entra Connect ウィザードで、オンプレミス Active Directory からの変更を同期するために必要な AD Connector アカウントを作成するときに、PublicFolder オブジェクトの読み取りに必要なアクセス許可がアカウントに正しく割り当てられません。 この問題は、高速インストールとカスタム インストールの両方に影響します。 今回の変更により、この問題が修正されました。
- Windows Server 2016 から実行している管理者に、Microsoft Entra Connect ウィザードのトラブルシューティング ページが正しく表示されない問題が修正されました。

##### 新機能と機能強化

- Microsoft Entra Connect ウィザードのトラブルシューティング ページを使用してパスワード同期をトラブルシューティングすると、トラブルシューティング ページによって、ドメイン固有の状態が返されるようになりました。
- 以前は、パスワード ハッシュ同期を有効にしようとする際に、オンプレミス AD からのパスワード ハッシュを同期するために必要なアクセス許可が AD Connector アカウントにあるかどうかが、Microsoft Entra Connect で検証されませんでした。 現在は、Microsoft Entra Connect ウィザードが検証を行い、AD Connector アカウントに適切なアクセス許可がない場合は警告メッセージが表示されます。

#### AD FS の管理

##### 修正された問題

- [ソース アンカーとしての ms-DS-ConsistencyGuid](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor) 機能の使用に関連する問題を修正しました。 この問題は、ユーザーのサインイン方法として *AD FS とのフェデレーション*を構成しているお客様に影響します。 ウィザードで *[ソース アンカーの構成]* タスクを実行すると、Microsoft Entra Connect では、immutableId のソース属性として \*ms-DS-ConsistencyGuid を使用するように切り替わります。 この変更の一環として、Microsoft Entra Connect は、AD FS で ImmutableId の要求規則を更新しようとします。 ただし、Microsoft Entra Connect には AD FS の構成に必要な管理者の資格情報がないため、このステップは失敗していました。 この修正により、*[ソース アンカーの構成]* タスクを実行すると、AD FS の管理者の資格情報を入力するように求めるメッセージが Microsoft Entra Connect に表示されるようになります。

### 1.1.614.0

状態:2017 年 9 月 5 日

#### Microsoft Entra Connect

##### 既知の問題

- Microsoft Entra Connect のアップグレードが失敗して "*Unable to upgrade the Synchronization Service* \(同期サービスをアップグレードできません\)" というエラーが表示される既知の問題があります。 また、今後は同期サービスの起動時にイベント エラー "*The service was unable to start because the version of the database is newer than the version of the binaries installed*"(データベースのバージョンが、インストールされているバイナリのバージョンよりも新しいため、サービスを開始できませんでした) が表示されることはありません。 この問題は、アップグレードを実行している管理者が、Microsoft Entra Connect で使用されている SQL サーバーに対して sysadmin 権限を持っていない場合に発生します。 dbo のアクセス許可では不十分です。
- [シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)を有効にしているお客様に影響する、Microsoft Entra Connect のアップグレードに関する既知の問題があります。 Microsoft Entra Connect をアップグレードすると、機能は引き続き有効であるにもかかわらず、ウィザードには無効と表示されます。 この問題は、今後のリリースで修正される予定です。 この表示の問題が気になるお客様は、ウィザードでシームレス シングル サインオンを有効にすることで、問題を手動で修正できます。

##### 修正された問題

- [ソース アンカーとしての ms-DS-ConsistencyGuid](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor) 機能を有効する際に Microsoft Entra Connect でオンプレミスの AD FS の要求規則を更新できない問題が修正されました。 この問題は、サインイン方法として AD FS が構成されている Microsoft Entra Connect の既存のデプロイに対して、この機能を有効にしようとすると発生します。 この問題は、ウィザードで AD FS の要求規則の更新に先立ち ADFS の資格情報の入力を求めるプロンプトを表示していなかったことによるものです。
- オンプレミスの AD フォレストで NTLM が無効になっている場合に Microsoft Entra Connect のインストールが失敗する問題が修正されました。 この問題は、Kerberos 認証に必要なセキュリティ コンテキストを作成するときに、Microsoft Entra Connect ウィザードが完全に修飾された資格情報を提供しないことが原因で発生します。 これにより、Kerberos 認証が失敗し、Microsoft Entra Connect ウィザードは NTLM の使用に戻ります。

#### Microsoft Entra Connect 同期

##### 修正された問題

- Tag 属性が指定されていないと新しい同期ルールを作成できない問題を修正しました。
- Kerberos を使用できる場合でも、Microsoft Entra Connect がオンプレミスの AD に接続して NTLM を使用したパスワード同期を行う問題が修正されました。 この問題は、オンプレミス AD トポロジに、バックアップから復元された 1 つまたは複数のドメイン コントローラーが存在する場合に発生します。
- アップグレード後に完全な同期手順が不必要に発生する問題を修正しました。 一般に、アップグレード後の完全な同期手順は、既定の同期ルールが変更されている場合に実行する必要があります。 この問題は、同期ルール式に改行文字が出現したときに変更が間違って検出される変更検出ロジックのエラーが原因でした。 改行文字は、読みやすさを向上させるために同期ルール式に挿入されます。
- Microsoft Entra Connect サーバーが自動アップグレード後に正常に動作しない可能性がある問題が修正されました。 この問題は、Microsoft Entra Connect サーバーのバージョン 1.1.443.0 (またはそれ以前) に影響を与えます。 この問題の詳細については、[Microsoft Entra Connect が自動アップグレード後に正常に動作しない](https://support.microsoft.com/help/4038479/azure-ad-connect-is-not-working-correctly-after-an-automatic-upgrade)問題に関する記事を参照してください。
- エラーが発生したときに、自動アップグレードが 5 分間隔で再試行される可能性がある問題を修正しました。 修正により、エラー発生時の自動アップグレードの再試行は、指数バックオフで行われます。
- パスワード同期イベント 611 がWindows アプリケーション イベント ログに**エラー**ではなく**情報**と間違って表示される問題を修正しました。 イベント 611 は、パスワード同期で問題が発生するたびに生成されます。
- Microsoft Entra Connect ウィザードで、グループの書き戻し機能が、グループを書き戻すために必要な OU を選択しなくても有効化できる問題が修正されました。

##### 新機能と機能強化

- Microsoft Entra Connect ウィザードの追加タスクに、トラブルシューティング タスクが追加されました。 このタスクを活用して、パスワード同期に関連する問題のトラブルシューティングと一般的な診断の収集を実行できます。 今後、トラブルシューティング タスクは、他のディレクトリの同期に関連する問題を含むように拡張されます。
- Microsoft Entra Connect で、**[既存のデータベースを使用する]** という名前の新しいインストール モードがサポートされるようになりました。 このインストール モードを使用すると、既存の ADSync データベースを指定する Microsoft Entra Connect をインストールできます。 この機能の詳細については、[既存のデータベースの使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-database)に関する記事を参照してください。
- セキュリティを強化するために、Microsoft Entra Connect では、ディレクトリを同期する際に既定で TLS1.2 を使用して Microsoft Entra ID に接続するようになりました。 以前の既定値は TLS1.0 でした。
- Microsoft Entra Connect のパスワード同期エージェントは、起動時に Microsoft Entra の既知のエンドポイントに接続してパスワード同期を試行します。 接続が成功すると、リージョン固有のエンドポイントにリダイレクトされます。 以前は、パスワード同期エージェントは、リージョン固有のエンドポイントが再起動するまで、それをキャッシュしていました。 現在、エージェントは、リージョン固有のエンドポイントで接続問題が発生した場合は、キャッシュをクリアし、既知のエンドポイントで再試行します。 この変更により、キャッシュされたリージョン固有のエンドポイントが使用できなった場合に、パスワード同期を別のリージョン固有のエンドポイントに確実にフェールオーバーできます。
- オンプレミスの AD フォレストの変更を同期するには、AD DS アカウントが必要です。 (i) AD DS アカウントを自分で作成してその資格情報を Microsoft Entra Connect に提供するか、(ii) エンタープライズ管理者の資格情報を指定して Microsoft Entra Connect に AD DS アカウントの作成を任せることができます。 以前は、(i) が Microsoft Entra Connect ウィザードの既定のオプションでした。 現在は、(ii) が既定のオプションです。

#### Microsoft Entra Connect ヘルス

##### 新機能と機能強化

- Microsoft Azure Government Cloud と Microsoft Cloud Germany のサポートを追加しました。

#### AD FS の管理

##### 修正された問題

- AD prep PowerShell モジュールの Initialize-ADSyncNGCKeysWriteBack コマンドレットが、ACL をデバイス登録コンテナーに間違って適用し、そのために既存のアクセス許可のみが継承されていました。 これが、同期サービス アカウントが適切なアクセス許可を持つように更新されました。

##### 新機能と機能強化

- Microsoft Entra Connect の ADFS ログイン検証タスクが、ADFS からのトークンの取得だけではなく、Microsoft Online に対するログインも検証するように更新されました。
- Microsoft Entra Connect を使用した新しい ADFS ファームの設定時に表示される ADFS の資格情報を求めるページが移動され、ユーザーに ADFS サーバーと WAP サーバーの指定を求める前に表示されるようになりました。 これにより、Microsoft Entra Connect は、指定されたアカウントに適切なアクセス許可があることを確認できます。
- Microsoft Entra Connect のアップグレード中に ADFS Microsoft Entra ID 信頼の更新に失敗しても、アップグレードは失敗しなくなりました。 これが発生した場合は、適切な警告メッセージが表示され、ユーザーは、Microsoft Entra Connect の追加タスクを使用して信頼をリセットする操作に進む必要があります。

#### シームレス シングル サインオン

##### 修正された問題

- [シームレス シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)の有効化が試行されたときに Microsoft Entra Connect ウィザードがエラーを返す問題が修正されました。 エラー メッセージは、"*Configuration of Microsoft Entra Connect Authentication Agent failed*"\(Microsoft Entra Connect の認証エージェントを構成できませんでした\) です。この問題は、[パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso)用の認証エージェントのプレビュー バージョンを[こちらの記事](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-upgrade-preview-authentication-agents)に記載されている手順に基づいて手動でアップグレードしたお客様に影響します。

### 1.1.561.0

状態:2017 年 7 月 23 日

#### Microsoft Entra Connect

##### 修正された問題

- 既定の同期規則 “AD への送信 – ユーザー ImmutableId” の削除を招く問題を修正しました。
- この問題は、Microsoft Entra Connect をアップグレードする際か、Microsoft Entra Connect ウィザード内のタスク オプション*[Update Synchronization Configuration] \(同期構成の更新)* を使用して Microsoft Entra Connect 同期構成を更新する際に発生します。
- この同期規則は、[ソース アンカーとしての ms-DS-ConsistencyGuid 機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor)を有効にしているお客様に適用されます。 この機能は、バージョン 1.1.524.0 以降で導入されました。 この同期規則が削除されると、Microsoft Entra Connect でオンプレミスの AD ms-DS-ConsistencyGuid 属性に ObjectGuid 属性値を設定することができなくなります。 これによって新しいユーザーが Microsoft Entra にプロビジョニングされなくなることはありません。
- この修正により、上記の機能が有効になっていれば、アップグレード中や構成の変更中にこの同期規則が削除されなくなります。 この問題による影響を受けている既存のお客様の場合、この修正により、このバージョンの Microsoft Entra Connect へのアップグレード後に、この同期規則がもう一度追加されるようにもなります。
- 既定の同期規則の優先順位値が 100 未満になる原因の問題を修正しました。
- 一般に、カスタム同期規則のために優先順位値 0 から 99 までが予約されています。 アップグレード中に、既定の同期規則の優先順位値は、同期規則の変更に対応して更新されます。 この問題のために、既定の同期規則に 100 未満の優先順位値が割り当てられる可能性があります。
- この修正により、アップグレード中にこの問題が発生しなくなります。 しかし、この問題の影響を受けている既存のお客様の優先順位値は復元されません。 将来、復元に役立つ別個の修正が提供される予定です。
- Microsoft Entra Connect ウィザード内の [\[ドメインと OU のフィルター処理\] 画面](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#domain-and-ou-filtering)で、OU ベースのフィルター処理が有効になっている場合でも、*[すべてのドメインと OU の同期]* オプションが選択済みとして表示される問題が修正されました。
- Synchronization Service Manager の[\[ディレクトリ パーティションの構成\] 画面](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#organizational-unitbased-filtering)で *[更新]* ボタンをクリックするとエラーが返される原因となる問題を修正しました。 エラー メッセージは*“An error was encountered while refreshing domains: Unable to cast object of type ‘System.Collections.ArrayList’ to type ‘Microsoft.DirectoryServices.MetadirectoryServices.UI.PropertySheetBase.MaPropertyPages.PartitionObject.”* (ドメインの更新中にエラーが発生しました。型 ‘System.Collections.ArrayList’ のオブジェクトを型 'Microsoft.DirectoryServices.MetadirectoryServices.UI.PropertySheetBase.MaPropertyPages.PartitionObject' にキャストできません。) です。このエラーは、新しい AD ドメインが既存の AD フォレストに追加されている場合に、[更新] ボタンを使用して Microsoft Entra Connect を更新しようとすると発生します。

##### 新機能と機能強化

- [自動アップグレード機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)が、次のような構成のお客様をサポートするように拡張されています。
- デバイスの書き戻し機能を有効にしました。
- グループの書き戻し機能を有効にしました。
- インストールが簡易設定でも DirSync のアップグレードでもありません。
- メタバース内のオブジェクトが 100,000 を超えています。
- 現在、複数のフォレストに接続しています。 高速セットアップで接続するフォレストは 1 つのみです。
- AD コネクタ アカウントは、既定の Microsoft Graph PowerShell アカウントではなくなりました。
- サーバーがステージング モードに設定されています。
- ユーザーの書き戻し機能を有効にしました。

注

自動アップグレード機能の範囲の拡大は、Microsoft Entra Connect ビルド 1.1.105.0 以降のお客様に影響します。 Microsoft Entra Connect サーバーが自動的にアップグレードされないようにするには、Microsoft Entra Connect サーバーで `Set-ADSyncAutoUpgrade -AutoUpgradeState disabled` コマンドレットを実行する必要があります。 自動アップグレードの有効化/無効化の詳細については、「[Microsoft Entra Connect: 自動アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)」を参照してください。

### 1.1.558.0

状態: リリースされません。 このビルドの変更は、バージョン 1.1.561.0 に組み込まれています。

#### Microsoft Entra Connect

##### 修正された問題

- OU ベースのフィルター処理構成の更新時に、既定の同期規則 “AD への送信 – ユーザー ImmutableId” が削除を招く問題を修正しました。 この同期規則は、[ソース アンカーとしての ms-DS-ConsistencyGuid 機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor)にとって必要です。
- Microsoft Entra Connect ウィザード内の [\[ドメインと OU のフィルター処理\] 画面](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#domain-and-ou-filtering)で、OU ベースのフィルター処理が有効になっている場合でも、*[すべてのドメインと OU の同期]* オプションが選択済みとして表示される問題が修正されました。
- Synchronization Service Manager の[\[ディレクトリ パーティションの構成\] 画面](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#organizational-unitbased-filtering)で *[更新]* ボタンをクリックするとエラーが返される原因となる問題を修正しました。 エラー メッセージは*“An error was encountered while refreshing domains: Unable to cast object of type ‘System.Collections.ArrayList’ to type ‘Microsoft.DirectoryServices.MetadirectoryServices.UI.PropertySheetBase.MaPropertyPages.PartitionObject.”* (ドメインの更新中にエラーが発生しました。型 ‘System.Collections.ArrayList’ のオブジェクトを型 'Microsoft.DirectoryServices.MetadirectoryServices.UI.PropertySheetBase.MaPropertyPages.PartitionObject' にキャストできません。) です。このエラーは、新しい AD ドメインが既存の AD フォレストに追加されている場合に、[更新] ボタンを使用して Microsoft Entra Connect を更新しようとすると発生します。

##### 新機能と機能強化

- [自動アップグレード機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)が、次のような構成のお客様をサポートするように拡張されています。
- デバイスの書き戻し機能を有効にしました。
- グループの書き戻し機能を有効にしました。
- インストールが簡易設定でも DirSync のアップグレードでもありません。
- メタバース内のオブジェクトが 100,000 を超えています。
- 現在、複数のフォレストに接続しています。 高速セットアップで接続するフォレストは 1 つのみです。
- AD コネクタ アカウントは、既定の Microsoft Graph PowerShell アカウントではなくなりました。
- サーバーがステージング モードに設定されています。
- ユーザーの書き戻し機能を有効にしました。

注

自動アップグレード機能の範囲の拡大は、Microsoft Entra Connect ビルド 1.1.105.0 以降のお客様に影響します。 Microsoft Entra Connect サーバーが自動的にアップグレードされないようにするには、Microsoft Entra Connect サーバーで `Set-ADSyncAutoUpgrade -AutoUpgradeState disabled` コマンドレットを実行する必要があります。 自動アップグレードの有効化/無効化の詳細については、「[Microsoft Entra Connect: 自動アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade)」を参照してください。

### 1.1.557.0

状態:2017 年 7 月

注

このビルドは、Microsoft Entra Connect の自動アップグレード機能では提供されません。

#### Microsoft Entra Connect

##### 修正された問題

- Initialize-ADSyncDomainJoinedComputerSync コマンドレットによって、既存のサービス接続ポイント オブジェクトで構成されている確認済みドメインが、有効なドメインであるにもかかわらず、変更される問題を修正しました。 この問題は、サービス接続ポイントの構成に使用できる確認済みドメインが、Microsoft Entra テナントに複数ある場合に発生します。

##### 新機能と機能強化

- Microsoft Azure Government クラウドと Microsoft Cloud Germany のプレビューで、パスワード ライトバックを利用できるようになりました。 さまざまなサービス インスタンスの Microsoft Entra Connect サポートの詳細については、記事「[Microsoft Entra Connect: インスタンスに関する特別な考慮事項](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-instances)」を参照してください。
- Initialize-ADSyncDomainJoinedComputerSync コマンドレットで、AzureADDomain という新しい省略可能なパラメーターを利用できるようになりました。 このパラメーターを使用すると、サービス接続ポイントの構成に使用する確認済みドメインを指定できます。

#### パススルー認証

##### 新機能と機能強化

- パススルー認証に必要なエージェントの名前が、"Microsoft Entra プライベート ネットワーク コネクタ" から "Microsoft Entra Connect 認証エージェント" に変更されました。
- パススルー認証を有効にしても、既定では、パスワード ハッシュ同期は有効化されなくなりました。

### 1.1.553.0

状態:2017 年 6 月

重要

このビルドでは、スキーマと同期規則に変更が加えられています。 アップグレードの後、フル インポートの手順と完全同期の手順が Microsoft Entra Connect 同期サービスによってトリガーされます。 変更の詳細は以下で説明しています。 アップグレード後、フル インポートと完全な同期の手順を一時的に保留にするには、「[How to defer full synchronization after upgrade (アップグレード後に完全な同期を保留にする方法)](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version#how-to-defer-full-synchronization-after-upgrade)」を参照してください。

#### Microsoft Entra Connect 同期

##### 既知の問題

- Microsoft Entra Connect 同期で [OU ベースのフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#organizational-unitbased-filtering)を使用しているお客様に影響する問題があります。Microsoft Entra Connect ウィザードで [\[ドメインと OU のフィルタリング\] ページ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#domain-and-ou-filtering)に移動すると、通常は、次のように動作します。
- OU ベースのフィルター処理が有効になっている場合は、 **[選択したドメインと OU の同期]** オプションが選択されます。
- それ以外の場合は、 **[すべてのドメインと OU の同期]** オプションが選択されます。

問題は、ウィザードを実行したときに、常に **[すべてのドメインと OU の同期]** が選択されることです。 この問題は、OU ベースのフィルター処理が構成されている場合でも発生します。 Microsoft Entra Connect 構成の変更を保存する前に、必ず**[選択したドメインと OU の同期] オプションが選択され**、同期する必要があるすべての OU が有効になっていることをもう一度確認してください。 これを行わないと、OU ベースのフィルター処理は無効になります。

##### 修正された問題

- パスワード ライトバックによって Microsoft Entra 管理者がオンプレミスの AD 特権ユーザー アカウントのパスワードをリセットできる、という問題が修正されました。 この問題は、特権アカウントに対するパスワードのリセット アクセス許可が、Microsoft Entra Connect に付与されている場合に発生します。 この問題は、このバージョンの Microsoft Entra Connect で、オンプレミスの AD 特権ユーザー アカウントの所有者でない Microsoft Entra 管理者が、任意の AD 特権ユーザー アカウントのパスワードをリセットできないようにすることで対処されています。 詳しくは、[セキュリティ アドバイザリ 4033453](https://learn.microsoft.com/ja-jp/security-updates/SecurityAdvisories/2017/4033453) を参照してください。
- Microsoft Entra Connect がオンプレミスの AD ms-DS-ConsistencyGuid 属性への書き戻しを行わないという、[ソース アンカーとしての ms-DS-ConsistencyGuid](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor) 機能に関連する問題が修正されました。 この問題は、オンプレミスの AD フォレストに複数の Microsoft Entra Connect が追加され、"[複数のディレクトリにユーザー ID が存在します] オプション" が選択されているときに発生します。 このような構成が使用されている場合、結果の同期ルールでは、メタバースの sourceAnchorBinary 属性が設定されません。 sourceAnchorBinary 属性は、ms-DS-ConsistencyGuid 属性のソース属性として使用されます。 このため、ms-DSConsistencyGuid 属性へのライトバックが行われません。 この問題を修正するために、メタバースの sourceAnchorBinary 属性が常に設定されるように、次の同期規則が更新されました。
- AD からの受信 - InetOrgPerson AccountEnabled.xml
- AD からの受信 - InetOrgPerson Common.xml
- AD からの受信 - ユーザー AccountEnabled.xml
- AD からの受信 - ユーザー Common.xml
- AD からの受信 - ユーザー結合 SOAInAAD.xml
- [ソース アンカーとしての ms-DS-ConsistencyGuid](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor) 機能が有効になっていなくても、"AD への送信 – ユーザー ImmutableId" 同期規則は引き続き Microsoft Entra Connect に追加されたままです。 これが原因で問題が発生することはなく、ms-DS-ConsistencyGuid 属性のライトバックも行われません。 混乱を避けるために、機能が有効の場合にのみ同期規則が追加されるロジックが追加されました。
- パスワード ハッシュ同期がエラー イベント 611 で失敗する問題を修正しました。 この問題は、1 つ以上のドメイン コントローラーがオンプレミスの AD から削除された後に発生します。 パスワード同期サイクルの終了時、オンプレミスの AD によって発行された同期 Cookie には、削除されたドメイン コントローラーの、USN (Update Sequence Number) 値が 0 の呼び出し ID が含まれています。 パスワード同期マネージャーは、USN 値 0 を含む同期 Cookie を保持できないため、エラー イベント 611 で失敗します。 次の同期サイクル中、パスワード同期マネージャーは、USN 値 0 を含まない、最後に保持された同期 Cookie を再利用します。 これにより、同じパスワードの変更が再同期されます。 この修正により、パスワード同期マネージャーは同期 Cookie を正しく保持します。
- Set-ADSyncAutoUpgrade コマンドレットで自動アップグレードを無効にしても、自動アップグレード プロセスにより、アップグレードは引き続き定期的にチェックされ、アップグレードの無効化には、ダウンロードしたインストーラーを使っていました。 この修正により、自動アップグレード プロセスによって、アップグレードが定期的にチェックされなくなっています。 この修正は、この Microsoft Entra Connect バージョンのアップグレード インストーラーが 1 回実行されたときに、自動的に適用されます。

##### 新機能と機能強化

- 以前は、[ソース アンカーとしての ms-DS-ConsistencyGuid](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor) 機能は、新しいデプロイでのみ使用できました。 現在、この機能は、既存のでデプロイでも使用することができます。 具体的には次のとおりです。
- 機能にアクセスするには、Microsoft Entra Connect ウィザードを開始し、"[Update Source Anchor]\(ソース アンカーの更新\)" オプションを選択します。
- このオプションは、objectGuid を sourceAnchor 属性として使用している既存のデプロイにのみ表示されます。
- オプションを構成するとき、オンプレミス Active Directory の ms-DS-ConsistencyGuid 属性の状態がウィザードによって検証されます。 この属性がディレクトリ内のどのユーザー オブジェクトに対しても構成されていない場合は、ms-DS-ConsistencyGuid が sourceAnchor 属性として使用されます。 ディレクトリ内の 1 つ以上のユーザー オブジェクトに対してこの属性が構成済みであった場合は、この属性が他のアプリケーションによって使用されており、sourceAnchor 属性としては適さないため、ソース アンカーの変更を続行できないと判断されます。 この属性が、既存のアプリケーションで使用されていないことが確実である場合は、サポートに連絡してエラーの抑制方法を入手する必要があります。
- デバイス オブジェクトの **userCertificate** 属性に固有の機能として、Microsoft Entra Connect は、Microsoft Entra ID との同期の前に、[Windows 10 エクスペリエンスのためにドメイン参加済みデバイスを Microsoft Entra ID に接続する](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)ときに必要な証明書の値を探して、それ以外の部分を除外できるようになりました。 この動作を有効にするために、すぐに使用できる同期規則 "Out to Microsoft Entra ID - Device Join SOAInAD" が更新されました。
- Microsoft Entra Connect が、オンプレミス AD **publicDelegates** 属性への Exchange Online **cloudPublicDelegates** 属性の書き戻しをサポートするようになりました。 これにより、オンプレミスの Exchange メールボックスを持つユーザーに送信するための SendOnBehalfTo 権限を、Exchange Online メールボックスに付与できるシナリオが有効になります。 この機能をサポートするために、既定の同期規則 "AD への送信 – ユーザー Exchange ハイブリッド PublicDelegates ライトバック" が新しく追加されました。 この同期規則は、Exchange ハイブリッド機能が有効になっている場合にのみ、Microsoft Entra Connect に追加されます。
- Microsoft Entra Connect が、Microsoft Entra ID からの **altRecipient** 属性の同期をサポートするようになりました。 この変更をサポートするために、次の既定の同期規則が更新され、必須の属性フローが追加されています。
- AD からの受信 - ユーザー Exchange
- Microsoft Entra ID に送信 – ユーザー ExchangeOnline
- メタバースの **cloudSOAExchMailbox** 属性は、特定のユーザーに Exchange Online メールボックスがあるかどうかを示します。 その定義は、追加の Exchange Online RecipientDisplayTypes、つまり備品用メールボックスや会議室のメールボックスを含めるように更新されました。 この変更を有効にするために、すぐに使用できる同期規則 "In from Microsoft Entra ID – User Exchange Hybrid" の下にある次の cloudSOAExchMailbox 属性の定義が更新されました。

    ```
    CBool(IIF(IsNullOrEmpty([cloudMSExchRecipientDisplayType]),NULL,BitAnd([cloudMSExchRecipientDisplayType],&amp;HFF) = 0))
    ```

    更新後の定義は次のとおりです。

    ```
    CBool(
    IIF(IsPresent([cloudMSExchRecipientDisplayType]),(
      IIF([cloudMSExchRecipientDisplayType]=0,True,(
       IIF([cloudMSExchRecipientDisplayType]=2,True,(
        IIF([cloudMSExchRecipientDisplayType]=7,True,(
         IIF([cloudMSExchRecipientDisplayType]=8,True,(
          IIF([cloudMSExchRecipientDisplayType]=10,True,(
           IIF([cloudMSExchRecipientDisplayType]=16,True,(
            IIF([cloudMSExchRecipientDisplayType]=17,True,(
             IIF([cloudMSExchRecipientDisplayType]=18,True,(
              IIF([cloudMSExchRecipientDisplayType]=1073741824,True,(
               IF([cloudMSExchRecipientDisplayType]=1073741840,True,False)))))))))))))))))))),False))
    ```
- 同期規則の式を作成するために、X509Certificate2 対応の次の関数を追加しました。これにより、userCertificate 属性の証明書の値が処理されます。
- CertSubject
- CertIssuer
- CertKeyAlgorithm
- CertSubjectNameDN
- CertIssuerOid
- CertNameInfo
- CertSubjectNameOid
- CertIssuerDN
- IsCert
- CertFriendlyName
- CertThumbprint
- CertExtensionOids
- CertFormat
- CertNotAfter
- CertPublicKeyOid
- 証明書シリアル番号
- CertNotBefore
- CertPublicKeyParametersOid
- CertVersion
- CertSignatureAlgorithmOid
- 選択する
- CertKeyAlgorithmParams
- CertHashString
- どこ
- 次で置換します
- 次のスキーマ変更が行われ、グループ オブジェクトについては sAMAccountName、domainNetBios、および domainFQDN を、ユーザー オブジェクトについては distinguishedName をフローするカスタム同期規則を、顧客が作成できます。
- 次の属性が、MV スキーマに追加されました。
- グループ:AccountName
- グループ: domainNetBios
- グループ: domainFQDN
- ユーザー: distinguishedName
- 次の属性が、Microsoft Entra コネクタ スキーマに追加されました。
- グループ:OnPremisesSamAccountName
- グループ:NetBiosName
- グループ:DnsDomainName
- ユーザー:OnPremisesDistinguishedName
- ADSyncDomainJoinedComputerSync コマンドレット スクリプトで、AzureEnvironment という新しい省略可能なパラメーターを利用できるようになりました。 このパラメーターを使用して、対応する Microsoft Entra テナントがホストされているリージョンを指定します。 有効な値は、次のとおりです。
- AzureCloud (既定)
- AzureChinaCloud
- AzureGermanyCloud
- USGovernment
- 同期規則の作成中、リンクの種類の既定値として、(プロビジョニングではなく) 結合が使用されるように同期規則エディターを更新しました。

#### AD FS の管理

##### 修正された問題

- 次の URL は、認証の障害に対する回復性を向上させるために Microsoft Entra ID によって導入された新しい WS-Federation エンドポイントで、オンプレミスの AD FS 証明書利用者信頼の構成に追加されます。
- https://ests.login.microsoftonline.com/login.srf
- https://stamp2.login.microsoftonline.com/login.srf
- `https://ccs.login.microsoftonline.com/login.srf`
- AD FS によって不正確な要求の値が IssuerID に対して生成される問題を修正しました。 この問題は、Microsoft Entra テナントに複数の確認済みドメインがあり、IssuerID 要求の生成に使用される userPrincipalName 属性のドメイン サフィックスの深さが 3 レベル以上 (たとえば、johndoe@us.contoso.com) の場合に発生します。 問題を解決するには、要求規則によって使用される正規表現を更新します。

##### 新機能と機能強化

- 以前は、Microsoft Entra Connect が提供する ADFS 証明書の管理機能は、Microsoft Entra Connect で管理されている ADFS ファームでのみ使用できました。 現在、この機能は、Microsoft Entra Connect で管理されていない ADFS ファームでも使用できます。

### 1.1.524.0

リリース日:2017 年 5 月

重要

このビルドでは、スキーマと同期規則に変更が加えられています。 アップグレード後は、フル インポートの手順と完全同期の手順が Microsoft Entra Connect 同期サービスによってトリガーされるようになります。 変更の詳細は以下で説明しています。

**修正された問題:**

Microsoft Entra Connect 同期

- ユーザーが Set-ADSyncAutoUpgrade コマンドレットを使用して自動アップグレード機能を無効にしたにもかかわらず、Microsoft Entra Connect サーバーで自動アップグレードが実行される問題が修正されました。 この修正の適用後も、サーバー上の自動アップグレード プロセスで引き続きアップグレードが定期的にチェックされますが、ダウンロードされたインストーラーは、自動アップグレードの構成を忠実に守ります。
- DirSync のインプレース アップグレード中に、Microsoft Entra Connect は、Microsoft Entra コネクタが Microsoft Entra ID と同期するために使用する Microsoft Entra サービス アカウントを作成します。 アカウントが作成されると、Microsoft Entra Connect はそのアカウントを使用して Microsoft Entra ID で認証を行います。 この認証が一時的な問題で失敗することがあり、それが原因で、DirSync のインプレース アップグレードも "An error has occurred executing Configure Azure AD Sync task: AADSTS50034: To sign into this application, the account must be added to the xxx.onmicrosoft.com directory"\(Azure AD 同期タスクの構成の実行中にエラーが発生しました: AADSTS50034: このアプリケーションにサインインするには、xxx.onmicrosoft.com ディレクトリにアカウントを追加する必要があります\) というエラーが発生して失敗することがあります。DirSync アップグレードの回復性を高めるために、Microsoft Entra Connect で認証ステップが再試行されるようになりました。
- ビルド 443 では、DirSync のインプレース アップグレードは成功するものの、ディレクトリの同期に必要な実行プロファイルが作成されない問題がありました。 このビルドの Microsoft Entra Connect には、復旧ロジックが追加されています。 ユーザーがこのビルドにアップグレードするときに、不足している実行プロファイルが Microsoft Entra Connect によって検出されて作成されます。
- パスワード同期処理が、イベント ID 6900 および "*同一のキーを含む項目が既に追加されています*" というエラーで起動に失敗する問題を修正しました。 この問題は、AD 構成パーティションを含めるように OU のフィルタリング構成を更新した場合に発生します。 この問題を修正するため、AD ドメイン パーティションからのパスワード変更のみを同期するようにパスワード同期処理を変更しました。 非ドメイン パーティション (構成パーティションなど) はスキップされます。
- AD コネクタでオンプレミス AD との通信に使用されるオンプレミス AD DS アカウントが、高速インストール中に Microsoft Entra Connect によって作成されます。 以前のバージョンでは、このアカウントが、user-Account-Control 属性の PASSWD\_NOTREQD フラグを設定した状態で作成され、アカウントにはランダムなパスワードが設定されます。 現在は、アカウントのパスワードが設定された後、PASSWD\_NOTREQD フラグは Microsoft Entra Connect によって明示的に削除されます。
- オンプレミス AD スキーマに mailNickname 属性が検出されたものの、AD ユーザー オブジェクト クラスにその属性がバインドされていないと、"*a deadlock occurred in sql server which trying to acquire an application lock* \(アプリケーション ロックの取得を試みるデッドロックが SQL Server で発生しました\)" というエラーが発生して DirSync のアップグレードが失敗する問題を修正しました。
- 管理者が Microsoft Entra Connect ウィザードを使用して Microsoft Entra Connect 同期の構成を更新しているときに、デバイスの書き戻し機能が自動的に無効になる問題が修正されました。 この問題は、デバイスの書き戻し構成が既にオンプレミス AD に存在しているときに、ウィザードによってその前提条件チェックが実行され、失敗することが原因で発生します。 デバイスの書き戻しが既に有効になっている場合は、このチェックをスキップすることで問題を修正しました。
- OU のフィルタリングは、Microsoft Entra Connect ウィザードを使用するか、または Synchronization Service Manager を使用して構成できます。 以前は、Microsoft Entra Connect ウィザードを使用して OU のフィルタリングを構成した場合、後で作成した新しい OU がディレクトリ同期の対象に含まれていました。 新しい OU を含めたくない場合は、Synchronization Service Manager を使って OU のフィルタリングを構成する必要があります。 現在は、同じ動作を Microsoft Entra Connect ウィザードを使用して実現できます。
- Microsoft Entra Connect で必要なストアド プロシージャが、dbo スキーマにではなく、インストールしている管理者のスキーマに作成される問題が修正されました。
- Microsoft Entra ID から返される TrackingId 属性が Microsoft Entra Connect サーバーのイベント ログから抜け落ちる問題が修正されました。 この問題は、Microsoft Entra Connect が Microsoft Entra ID からリダイレクト メッセージを受信し、指定されたエンドポイントに Microsoft Entra Connect が接続できない場合に発生します。 TrackingId は、トラブルシューティング時に、サービス側のログに関連付ける目的でサポート エンジニアが使用します。
- Microsoft Entra Connect は、Microsoft Entra ID から LargeObject エラーを受け取ると、EventID 6941 のイベントと "提供されたオブジェクトが大きすぎます。*このオブジェクト上の属性値の数を調整してください*" というメッセージを生成します。その際、誤解を招くおそれのある EventID 6900 イベントと、"Microsoft.Online.Coexistence.ProvisionRetryException: Windows Azure Active Directory サービスと通信できません" というメッセージも Microsoft Entra Connect から生成されます。混乱を最小限に抑えるために、Microsoft Entra Connect で LargeObject エラーが発生しても、後者のイベントは生成されなくなりました。
- Generic LDAP コネクタの構成を更新しようとしているときに Synchronization Service Manager が無応答になる問題を修正しました。

**新機能/改善点:**

Microsoft Entra Connect 同期

- 同期規則の変更 - 以下の同期規則に変更を加えました。
- **userCertificate** 属性と **userSMIMECertificate** 属性に割り当てられている値が 15 個を超えている場合、これらの属性をエクスポートしないように既定の同期規則セットを更新しました。
- AD 属性 **employeeID** と **msExchBypassModerationLink** は、既定の同期規則セットに追加しました。
- AD 属性 **photo** は、既定の同期規則セットから削除しました。
- メタバースのスキーマと Microsoft Entra コネクタのスキーマに **preferredDataLocation** が追加されました。 Microsoft Entra ID でいずれかの属性を更新する必要のあるユーザーは、カスタム同期規則を実装することで、これを行うことができます。
- メタバースのスキーマと Microsoft Entra ID コネクタのスキーマに **userType** が追加されました。 Microsoft Entra ID でいずれかの属性を更新する必要のあるユーザーは、カスタム同期規則を実装することで、これを行うことができます。
- 現在、Microsoft Entra Connect では、ConsistencyGuid 属性の使用が、オンプレミスの AD オブジェクトのソース アンカー属性として自動的に有効になります。 また、ConsistencyGuid 属性が空の場合、この属性は、Microsoft Entra Connect によって、objectGuid 属性の値で自動的に設定されます。 この機能は新しいデプロイにのみ適用されます。 この機能の詳細については、[Microsoft Entra Connect: 設計概念 - sourceAnchor としての ms-DS-ConsistencyGuid の使用](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts#using-ms-ds-consistencyguid-as-sourceanchor)に関するセクションを参照してください。
- トラブルシューティングのための新しいコマンドレット Invoke-ADSyncDiagnostics を追加しました。パスワード ハッシュ同期に関する問題の診断に役立てることができます。 コマンドレットの使用の詳細については、「[Microsoft Entra Connect 同期を使用したパスワード ハッシュ同期のトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization)」を参照してください。
- Microsoft Entra Connect で新たに、オンプレミスの AD と Microsoft Entra ID との間で "メールが有効なパブリック フォルダ" オブジェクトの同期がサポートされます。 この機能は、Microsoft Entra ID Connect ウィザードのオプション機能から有効にできます。 この機能の詳細については、「[Office 365 Directory Based Edge Blocking support for on-premises Mail Enabled Public Folders (オンプレミスのメールが有効なパブリック フォルダーに対する Office 365 ディレクトリ ベース エッジ ブロック サポート)](https://techcommunity.microsoft.com/t5/exchange/office-365-directory-based-edge-blocking-support-for-on-premises/m-p/74218)」を参照してください。
- Microsoft Entra ID Connect では、オンプレミスの AD から同期するために AD DS アカウントが必要となります。 以前は、簡易モードを使用して Microsoft Entra Connect をインストールした場合、エンタープライズ管理者アカウントの資格情報を指定でき、必要な AD DS アカウントは Microsoft Entra Connect によって作成されました。 しかし、カスタム インストールを行う場合や、既存のデプロイにフォレストを追加する場合は、AD DS アカウントを自分で指定する必要がありました。 現在は、カスタム インストールの際に、エンタープライズ管理者アカウントの資格情報を指定することで、必要な AD DS アカウントを Microsoft Entra Connect で自動的に作成することもできるようになりました。
- Microsoft Entra Connect で SQL AOA がサポートされるようになりました。 Microsoft Entra Connect をインストールする前に SQL AOA を有効にする必要があります。 インストール中、指定された SQL インスタンスで SQL AOA が有効であるかどうかが Microsoft Entra Connect によって検出されます。 SQL AOA が有効である場合、Microsoft Entra Connect はさらに、SQL AOA が、同期レプリケーションまたは非同期レプリケーションを使用するように構成されているかどうかを調べます。 可用性グループ リスナーを設定するときは、RegisterAllProvidersIP プロパティを 0 に設定することをお勧めします。 これが推奨される理由は、Microsoft Entra Connect は現在、SQL Native Client を使用して SQL に接続していますが、SQL Native Client は、MultiSubNetFailover プロパティの使用をサポートしていないためです。
- Microsoft Entra Connect サーバーのデータベースとして LocalDB を使用していて、サイズの上限である 10 GB に達した場合、それ以降、同期サービスは起動しません。 以前のバージョンでは、LocalDB で ShrinkDatabase 操作を実行し、同期サービスを起動できるだけの DB 空き領域を回収する必要があります。 その後は、Synchronization Service Manager を使用して実行履歴を削除し、DB 空き領域をさらに回収することができます。 新しいバージョンでは、Start-ADSyncPurgeRunHistory コマンドレットを使用して実行履歴データを LocalDB から消去し、DB 空き領域を回収することができます。 このコマンドレットは、同期サービスが実行されていないときに使用できるオフライン モードにも対応しています (-offline パラメーターを指定)。 注: オフライン モードは、同期サービスが実行されておらず、なおかつ使用されているデータベースが LocalDB である場合にのみ使用できます。
- 新しいバージョンの Microsoft Entra Connect では、必要な記憶域スペースを小さくするために、同期エラーの詳細情報を圧縮してから LocalDB/SQL データベースに格納します。 以前のバージョンの Microsoft Entra Connect からこのバージョンにアップグレードすると、Microsoft Entra Connect は、既に存在している同期エラー情報に対して一回限りの圧縮を実行します。
- 以前のバージョンでは、OU のフィルタリング構成を更新した後、フル インポートを手動で実行して、ディレクトリ同期の対象に既存のオブジェクトを適切に含めたり、対象から除外したりする必要があります。 現在、Microsoft Entra Connect では、次の同期サイクルの間にフル インポートが自動的にトリガーされるようになりました。 また、フル インポートは、更新の影響を受けた AD コネクタにのみ適用されます。 注: この機能強化が適用されるのは、Microsoft Entra Connect ウィザードを使用して行われた OU のフィルタリングの更新だけです。 Synchronization Service Manager を使用して行われた OU のフィルタリングの更新には適用されません。
- 以前のバージョンでは、グループベースのフィルターが、ユーザー オブジェクト、グループ オブジェクト、連絡先オブジェクトでしかサポートされていません。 新しいバージョンでは、グループベースのフィルターでコンピューター オブジェクトもサポートされます。
- 以前は、Microsoft Entra Connect 同期スケジューラを無効にしなくても、コネクタ スペースのデータを削除することができました。 新しいバージョンでは、スケジューラが有効になっていることを Synchronization Service Manager が検出した場合、コネクタ スペースのデータは、削除できないようブロックされます。 さらに、コネクタ スペースのデータを削除するとデータが失われる可能性があるとして、ユーザーに警告が返されます。
- 以前は、Microsoft Entra Connect ウィザードを正しく動作させるためには、PowerShell 文字起こしを無効にする必要がありました。 この問題は一部解決されています。 Microsoft Entra Connect ウィザードを使用して同期構成を管理する場合は、PowerShell 文字起こしを有効にできます。 Microsoft Entra Connect ウィザードを使用して ADFS の構成を管理する場合は、PowerShell 文字起こしを無効にする必要があります。

### 1.1.486.0

リリース日:2017 年 4 月

**修正された問題:**

- Microsoft Entra Connect がローカライズ版の Windows Server に正常にインストールされない問題が修正されました。

### 1.1.484.0

リリース日:2017 年 4 月

**既知の問題:**

- 次の条件がすべて当てはまる場合、このバージョンの Microsoft Entra Connect は正常にインストールされません。
    1. Microsoft Entra Connect の DirSync インプレース アップグレードまたは新規インストールのどちらかを実行している。
    2. サーバー上の組み込みの管理者グループの名前が "Administrators" でないローカライズ版の Windows Server を使用している。
    3. 独自の完全な SQL を提供するのではなく、Microsoft Entra Connect と共にインストールされた既定の SQL Server 2012 Express LocalDB を使用している。

**修正された問題:**

Microsoft Entra Connect 同期

- 1 つ以上のコネクタに同期手順の実行プロファイルがない場合、同期スケジューラがその同期手順全体をスキップする問題を修正しました。 たとえば、差分インポート実行プロファイルを作成せずに、Synchronization Service Manager を使用してコネクタを手動で追加した場合です。 この解決策により、同期スケジューラが引き続き他のコネクタの差分インポートを実行することが保証されます。
- いずれかの実行手順で問題が発生した場合、同期サービスが直ちに実行プロファイルの処理を停止する問題を修正しました。 この解決策により、同期サービスがその実行手順をスキップし、残りの処理を続行することが保証されます。 たとえば、複数の実行手順 (オンプレミス AD ドメインごとに 1 つ) を含む AD コネクタ用の差分インポート実行プロファイルがあります。 そのいずれかにネットワーク接続に関する問題が発生した場合でも、同期サービスは他の AD ドメインの差分インポートを実行します。
- 自動アップグレード中に Microsoft Entra コネクタの更新がスキップされる問題が修正されました。
- セットアップ中にサーバーがドメイン コントローラであるかどうかを Microsoft Entra Connect が誤って判定し、そのために DirSync アップグレードが失敗する問題が修正されました。
- DirSync インプレース アップグレードで Microsoft Entra Connector の実行プロファイルが作成されない問題が修正されました。
- Generic LDAP コネクタを構成しようとしたときに Synchronization Service Manager ユーザー インターフェイスが無応答になる問題を修正しました。

AD FS の管理

- AD FS プライマリ ノードが別のサーバーに移動されている場合に、Microsoft Entra Connect ウィザードが失敗する問題が修正されました。

デスクトップ SSO

- 新規インストール中に [サインイン] オプションとして [パスワードの同期] を選択した場合に、[サインイン] 画面で [デスクトップ SSO] 機能を有効にできない Microsoft Entra Connect ウィザードの問題が修正されました。

**新機能/改善点:**

Microsoft Entra Connect 同期

- Microsoft Entra Connect 同期は現在、そのサービス アカウントとして仮想サービス アカウント、管理サービス アカウント、およびグループ管理サービス アカウントの使用をサポートしています。 これは、Microsoft Entra Connect の新規インストールにのみ適用されます。 Microsoft Entra Connect のインストール時:
    - 既定では、Microsoft Entra Connect ウィザードは仮想サービス アカウントを作成し、それをそのサービス アカウントとして使用します。
    - ドメイン コントローラ上にインストールしている場合、Microsoft Entra Connect は、ドメイン ユーザー アカウントを作成する前の動作にフォールバックし、代わりにそれをそのサービス アカウントとして使用します。
    - 次のいずれかを指定することによって、既定の動作をオーバーライドできます。
    - グループ管理サービス アカウント
    - 管理サービス アカウント
    - ドメイン ユーザー アカウント
    - ローカル ユーザー アカウント
- 以前は、コネクタの更新または同期規則の変更を含む Microsoft Entra Connect の新しいビルドにアップグレードすると、Microsoft Entra Connect は完全同期サイクルをトリガーしました。 現在、Microsoft Entra Connect は、更新を含むコネクタに対してのみフル インポート手順を、同期規則の変更を含むコネクタに対してのみ完全同期手順を選択的にトリガーします。
- 以前は、エクスポート削除しきい値は、同期スケジューラからトリガーされたエクスポートにのみ適用されました。 現在、この機能は、顧客が Synchronization Service Manager を使用して手動でトリガーしたエクスポートを含むように拡張されています。
- Microsoft Entra テナントには、そのテナントで [パスワードの同期] 機能が有効になっているかどうかを示すサービス構成が存在します。 以前は、アクティブなステージング サーバーがある場合、Microsoft Entra Connect はサービス構成を簡単に誤って構成しました。 現在、Microsoft Entra Connect は、サービス構成をアクティブな Microsoft Entra Connect サーバーだけと整合性のある状態に保持しようとします。
- Microsoft Entra Connect ウィザードは現在、オンプレミス AD で AD のごみ箱が有効になっていないかどうかを検出し、警告を返します。
- 以前は、バッチ内のオブジェクトの合計サイズが特定のしきい値を超えている場合、Microsoft Entra ID へのエクスポートはタイムアウトし、失敗しました。 現在、この問題が発生した場合、同期サービスは個別の、より小さなバッチでのオブジェクトの再送信を再度試みます。
- 同期サービス キー管理アプリケーションが Windows の [スタート] メニューから削除されました。 暗号化キーの管理は、miiskmu.exe を使用してコマンド ライン インターフェイス経由で引き続きサポートされます。 暗号化キーの管理については、[Microsoft Entra Connect 同期の暗号化キーの破棄](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-change-serviceacct-pass#abandoning-the-adsync-service-account-encryption-key)に関する記事を参照してください。
- 以前は、Microsoft Entra Connect 同期サービス アカウントのパスワードを変更すると、暗号化キーを破棄し、Microsoft Entra Connect 同期サービス アカウントのパスワードを再初期化するまで、同期サービスを正常に開始できなくなりました。 現在、このプロセスは必要なくなりました。

デスクトップ SSO

- Microsoft Entra Connect ウィザードでは、パススルー認証およびデスクトップ SSO を構成する場合、ネットワーク上でポート 9090 を開く必要がなくなりました。 ポート 443 のみが必要です。

### 1.1.443.0

リリース日:2017 年 3 月

**修正された問題:**

Microsoft Entra Connect 同期

- Microsoft Entra テナントに割り当てられた初期の onmicrosoft.com ドメインが Microsoft Entra コネクタの表示名に含まれていない場合に Microsoft Entra Connect ウィザードが失敗する原因となる問題が修正されました。
- 同期サービス アカウントのパスワードにアポストロフィ、コロン、スペースなどの特殊文字が含まれている場合に SQL Database への接続時に Microsoft Entra Connect ウィザードが失敗する原因となる問題が修正されました。
- オンプレミスの ADオブジェクトを同期から一時的に除外してからもう一度同期に含めた後で、ステージング モードの Microsoft Entra Connect サーバーで "The image has an anchor that is different than the image \(イメージにイメージとは異なるアンカーがあります\)" エラーが発生する原因となる問題が修正されました。
- オンプレミスの ADオブジェクトを同期から一時的に除外してからもう一度同期に含めた後で、ステージング モードの Microsoft Entra Connect サーバーで "The object located by DN is a phantom \(DN によって特定されたオブジェクトはファントムです\)" エラーが発生する原因となる問題が修正されました。

AD FS の管理

- 代替ログイン ID が構成された後、Microsoft Entra Connect ウィザードが AD FS の構成を更新せず、証明書利用者信頼に対する適切な要求を設定しない問題が修正されました。
- サービス アカウントが sAMAccountName 形式ではなく userPrincipalName 形式を使用して構成されている AD FS サーバーを Microsoft Entra Connect ウィザードが正しく処理できない問題が修正されました。

パススルー認証

- パススルー認証が選択されていてもそのコネクタの登録に失敗する場合に Microsoft Entra Connect ウィザードが失敗する原因となる問題が修正されました。
- デスクトップ SSO 機能を有効にするときに選択されたサインイン方法に対する検証チェックを Microsoft Entra Connect ウィザードがバイパスする原因となる問題が修正されました。

"パスワードのリセット"

- ファイアウォールまたはプロキシにより接続が強制終了した場合に Microsoft Entra Connect サーバーによる再接続が試行されない問題が修正されました。

**新機能/改善点:**

Microsoft Entra Connect 同期

- Get-ADSyncScheduler コマンドレットは、SyncCycleInProgress という名前の新しいブール値プロパティを返すようになりました。 戻り値が true の場合は、進行中のスケジュールされた同期サイクルがあることを意味します。
- Microsoft Entra Connect のインストールおよびセットアップ ログを格納するための保存先フォルダーが `%localappdata%\AADConnect` から `%programdata%\AADConnect` に移動され、ログ ファイルへのアクセシビリティが改善されました。

AD FS の管理

- AD FS ファーム TLS/SSL 証明書の更新のサポートが追加されました。
- AD FS 2016 を管理するためのサポートが追加されました。
- AD FS のインストール中に既存の gMSA (グループ管理サービス アカウント) を指定できるようになりました。
- SHA-256 を Microsoft Entra ID 証明書利用者信頼の署名ハッシュ アルゴリズムとして構成できるようになりました。

"パスワードのリセット"

- より厳格なファイアウォール規則の環境においても製品が機能するように機能強化が導入されました。
- Azure Service Bus への接続信頼性が向上しました。

### 1.1.380.0

リリース日:2016 年 12 月

**修正された問題:**

- このビルドでの Active Directory Federation Services (AD FS) 用の issuerid 要求規則が欠落しているという問題を解決しました。

注

このビルドは、Microsoft Entra Connect の自動アップグレード機能では提供されません。

### 1.1.371.0

リリース日:2016 年 12 月

**既知の問題:**

- このビルドには AD FS 用の issuerid 要求規則が欠落しています。 Microsoft Entra ID で複数のドメインのフェデレーションを行っている場合は issuerid 要求規則が必要です。 Microsoft Entra Connect を使用してオンプレミスの AD FS デプロイを管理している場合は、このビルドにアップグレードすると、既存の issuerid 要求規則が AD FS 構成から削除されます。 この問題は、インストール/アップグレード後に issuerid 要求規則を追加することで回避できます。 issuerid 要求規則の追加の詳細については、「[Microsoft Entra ID とのフェデレーションに使用する複数ドメインのサポート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-multiple-domains)」を参照してください。

**修正された問題:**

- ポート 9090 が送信接続に開かれていない場合、Microsoft Entra Connect のインストールまたはアップグレードは失敗します。

注

このビルドは、Microsoft Entra Connect の自動アップグレード機能では提供されません。

### 1.1.370.0

リリース日:2016 年 12 月

**既知の問題:**

- このビルドには AD FS 用の issuerid 要求規則が欠落しています。 Microsoft Entra ID で複数のドメインのフェデレーションを行っている場合は issuerid 要求規則が必要です。 Microsoft Entra Connect を使用してオンプレミスの AD FS デプロイを管理している場合は、このビルドにアップグレードすると、既存の issuerid 要求規則が AD FS 構成から削除されます。 この問題は、インストール/アップグレード後に issuerid 要求規則を追加することで回避できます。 issuerid 要求規則の追加の詳細については、「[Microsoft Entra ID とのフェデレーションに使用する複数ドメインのサポート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-multiple-domains)」を参照してください。
- インストールを完了するには、ポート 9090 が送信に対して開かれている必要があります。

**新機能:**

- パススルー認証。

注

このビルドは、Microsoft Entra Connect の自動アップグレード機能では提供されません。

### 1.1.343.0

リリース日:2016 年 11 月

**既知の問題:**

- このビルドには AD FS 用の issuerid 要求規則が欠落しています。 Microsoft Entra ID で複数のドメインのフェデレーションを行っている場合は issuerid 要求規則が必要です。 Microsoft Entra Connect を使用してオンプレミスの AD FS デプロイを管理している場合は、このビルドにアップグレードすると、既存の issuerid 要求規則が AD FS 構成から削除されます。 この問題は、インストール/アップグレード後に issuerid 要求規則を追加することで回避できます。 issuerid 要求規則の追加の詳細については、「[Microsoft Entra ID とのフェデレーションに使用する複数ドメインのサポート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-multiple-domains)」を参照してください。

**修正された問題:**

- 組織のパスワード ポリシーで指定された複雑さのレベルを満たしているパスワードを持つローカル サービス アカウントを作成することができないため、Microsoft Entra Connect のインストールが失敗することがあります。
- コネクタ スペースのオブジェクトが、1 つの結合規則ではスコープ外になり、別の結合規則ではスコープ内になった場合に、結合規則が再評価されない問題が修正されました。 この問題は、結合条件が相互に排他的になっている 2 つ以上の結合規則がある場合に発生することがあります。
- 結合規則のない (Microsoft Entra ID からの) 受信同期規則の優先順位の値が、結合規則のある受信同期規則よりも低い場合、結合規則のない受信同期規則が処理されないという問題が修正されました。

**機能強化:**

- Windows Server 2016 Standard 以降に Microsoft Entra Connect をインストールするためのサポートが追加されました。
- Microsoft Entra Connect のリモートのデータベースとしての SQL Server 2016 の使用がサポートされるようになりました。

### 1.1.281.0

リリース日:2016 年 8 月

**修正された問題:**

- 同期間隔の変更が、次の同期サイクルの完了後まで反映されません。
- Microsoft Entra Connect ウィザードで、アンダースコア (\_) で始まるユーザー名を持つ Microsoft Entra アカウントを使用できません。
- Microsoft Entra Connect ウィザードで、アカウントのパスワードに含まれる特殊文字の数が多すぎると、指定した Microsoft Entra アカウントの認証が失敗します。 "資格情報を検証できません。 予期しないエラーが発生しました。" が返されます。
- ステージング サーバーをアンインストールすると、Microsoft Entra テナントでパスワード同期が無効になり、アクティブなサーバーでのパスワード同期が失敗します。
- ユーザーに対してパスワードのハッシュが格納されていない場合、例外的な状況でパスワード同期が失敗します。
- Microsoft Entra Connect サーバーでステージング モードが有効になっている場合、パスワード ライトバックが一時的に無効になりません。
- サーバーがステージング モードの場合、Microsoft Entra Connect ウィザードでパスワード同期とパスワード ライトバックの実際の構成が表示されません。 常に無効として表示されます。
- サーバーがステージング モードの場合、パスワード同期とパスワード ライトバックの構成への変更が Microsoft Entra Connect ウィザードで保持されません。

**機能強化:**

- Start-ADSyncSyncCycle コマンドレットが更新され、新しい同期サイクルを正常に開始できるかどうかを示すようになりました。
- Stop-ADSyncSyncCycle コマンドレットが追加され、現在実行中の同期サイクルと操作を終了できるようになりました。
- Stop-ADSyncScheduler コマンドレットが更新され、現在実行中の同期サイクルと操作を終了できるようになりました。
- Microsoft Entra Connect ウィザードで[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)を構成するときに、"Teletex 文字列" 型の Microsoft Entra 属性を選択できるようになりました。

### 1.1.189.0

リリース日:2016 年 6 月

**修正された問題と機能強化:**

- Microsoft Entra Connect を FIPS に準拠しているサーバーにインストールできるようになりました。
- パスワードの同期については、[パスワード ハッシュの同期と FIPS](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#password-hash-synchronization-and-fips) に関する記事を参照してください。
- Active Directory コネクタで、NetBIOS を FQDN に名前解決できないという問題が修正されました。

### 1.1.180.0

リリース日:2016 年 5 月

**新機能:**

- Microsoft Entra Connect の実行前にドメインの確認が行われなかった場合に、ドメインの確認について警告し、必要なヘルプ情報を提供します。
- [Microsoft Cloud Germany](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-instances#microsoft-cloud-germany)のサポートが追加されました。
- 新しい URL 要件を含む最新の [Microsoft Azure Government クラウド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-instances#microsoft-azure-government) インフラストラクチャのサポートが追加されました。

**修正された問題と機能強化:**

- 同期規則を探しやすくするフィルターが同期規則エディターに追加されました。
- コネクタ スペースを削除するときのパフォーマンスが改善されました。
- 同じオブジェクトに対して削除と追加の両方が同一の実行で行われた場合 (削除/追加) の問題が修正されました。
- 無効にした同期規則で、含まれるオブジェクトや属性が、アップグレードまたはディレクトリ スキーマの更新時に再び有効にされることがなくなりました。

### 1.1.130.0

リリース日:2016 年 4 月

**新機能:**

- [ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)に、複数値の属性のサポートが追加されました。
- アップグレードの対象と見なされる [自動アップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade) の構成バリエーションが増えました。
- [カスタム スケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler#custom-scheduler)にコマンドレットがいくつか追加されました。

### 1.1.119.0

リリース日:2016 年 3 月

**修正された問題:**

- Windows Server 2008 (R2 より前のバージョン) ではパスワード同期がサポートされないため、このオペレーティング システムでは高速インストールが使用できなくなりました。
- カスタム フィルター構成での DirSync からのアップグレードが予期したとおりに動作しません。
- 新しいリリースへのアップグレード時に構成に変更がない場合は、フル インポート/同期をスケジュールすることはできません。

### 1.1.110.0

リリース日:2016 年 2 月

**修正された問題:**

- インストールが既定の C:\Program Files フォルダーにない場合、以前のリリースからのアップグレードが機能しません。
- インストール時に、インストール ウィザードの最後で **[同期処理を開始してください]** をオフにした場合、2 回目にインストール ウィザードを実行したときにスケジューラが有効になりません。
- 日付と時刻の形式が US-en ではない場合、スケジューラはサーバーで予想どおりに機能しません。 また、正しい時刻を返す `Get-ADSyncScheduler` もブロックされます。
- サインイン オプションおよびアップグレードとして AD FS を使用して以前のリリースの Microsoft Entra Connect をインストールした場合、インストール ウィザードを再度実行することはできません。

### 1.1.105.0

リリース日:2016 年 2 月

**新機能:**

- [Automatic upgrade](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-automatic-upgrade) 機能。
- インストール ウィザードで Microsoft Entra 多要素認証および Privileged Identity Management を使用してハイブリッド ID 管理者をサポートします。
- 多要素認証を使用する場合は、`https://secure.aadcdn.microsoftonline-p.com` へのトラフィックも許可するように、プロキシを設定する必要があります。
- 多要素認証を正しく動作させるには、信頼済みサイトの一覧に `https://secure.aadcdn.microsoftonline-p.com` を追加する必要があります。
- 初期インストール後のユーザーのサインイン方法の変更を許可。
- インストール ウィザードでの[ドメインと OU のフィルター処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#domain-and-ou-filtering)を許可。 これによって、一部のドメインは使用できないフォレストへの接続も許可されます。
- 同期エンジンに組み込まれた[スケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler)。

**プレビューから GA に昇格した機能:**

- [デバイスの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-device-writeback)。
- [ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)。

**新しいプレビュー機能:**

- 新しい既定の同期サイクル間隔は 30 分です。 以前のすべてのリリースでは、3 時間でした。 [スケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler) の動作の変更がサポートされるようになりました。

**修正された問題:**

- DNS ドメインの検証ページが、ドメインを認識できない場合がありました。
- AD FS を構成するときに、ドメイン管理者の資格情報を求めるメッセージが表示されます。
- オンプレミス AD アカウントが、ルート ドメインとは異なる DNS ツリーを持つドメイン内にある場合、インストール ウィザードがそのアカウントを認識できません。

### 1.0.9131.0

リリース日:2015 年 12 月

**修正された問題:**

- Active Directory Domain Services (AD DS) でパスワードを変更するときにパスワードの同期が機能しない場合がありますが、パスワードの設定時には機能します。
- プロキシ サーバーがある場合、Microsoft Entra ID に対する認証が、インストール中または構成ページでアップグレードが取り消された場合に失敗することがあります。
- SQL Server のシステム管理者 (SA) でない場合、完全な SQL Server インスタンスで以前のリリースの Microsoft Entra Connect から更新すると失敗します。
- リモートの SQL Server で以前のリリースの Microsoft Entra Connect から更新すると、"ADSync SQL データベースにアクセスできません" というエラーが表示されます。

### 1.0.9125.0

リリース日:2015 年 11 月

**新機能:**

- AD FS を Microsoft Entra ID 信頼に再構成できます。
- Active Directory スキーマを更新し、同期規則を再生成できるようになりました。
- 同期規則を無効にできるようになりました。
- 同期規則の新しいリテラルとして "AuthoritativeNull" を定義できるようになりました。

**新しいプレビュー機能:**

- [同期用の Microsoft Entra Connect Health](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync)。
- [Microsoft Entra Domain Services](https://support.microsoft.com/account-billing/reset-your-work-or-school-password-using-security-info-23dde81f-08bb-4776-ba72-e6b72b9dda9e) のパスワード同期をサポートします。

**新しくサポートされたシナリオ:**

- 複数のオンプレミス Exchange 組織がサポートされました。 詳細については、「[複数の Active Directory フォレストを伴うハイブリッド展開](https://learn.microsoft.com/ja-jp/Exchange/hybrid-deployment/hybrid-with-multiple-forests)」を参照してください。

**修正された問題:**

- パスワード同期の問題:
- スコープ外からスコープ内に移動されたオブジェクトのパスワードは同期されなくなります。 これには、OU と属性フィルターの両方が含まれます。
- 同期に含める新しい OU を選択する際に、完全なパスワード同期は必要ありません。
- 無効なユーザーが有効になっても、パスワードは同期されません。
- パスワード再試行キューは無制限です。5,000 個のオブジェクトを上限として削除されるという以前の制限は削除されました。
- Windows Server 2016 フォレスト機能レベルを使用して Active Directory に接続することはできなくなりました。
- 最初のインストール後にグループ フィルターに使用したグループを変更できなくなりました。
- パスワード ライトバックを有効にしてパスワードを変更した各ユーザーについては、Microsoft Entra Connect の新しいユーザー プロファイルが作成されなくなりました。
- 同期規則スコープに Long Integer 値を使用できなくなりました。
- 到達不能なドメイン コントローラーがある場合、[デバイスの書き戻し] チェック ボックスは無効なままです。

### 1.0.8667.0

リリース日:2015 年 8 月

**新機能:**

- Microsoft Entra Connect インストール ウィザードが、すべての Windows Server 言語にローカライズされました。
- Microsoft Entra パスワード管理を使用する場合のアカウント ロック解除のサポートが追加されました。

**修正された問題:**

- インストールを開始したユーザー以外のユーザーがインストールを続けると、Microsoft Entra Connect インストール ウィザードがクラッシュします。
- 以前の Microsoft Entra Connect のアンインストールで Microsoft Entra Connect 同期を完全にアンインストールできなかった場合、再インストールすることができません。
- ユーザーがフォレストのルート ドメインに属していないか、英語以外のバージョンの Active Directory が使用されている場合、高速インストールを使用して Microsoft Entra Connect をインストールすることはできません。
- Active Directory ユーザー アカウントの FQDN を解決できない場合、スキーマをコミットできなかったという誤ったエラー メッセージが表示されます。
- Active Directory Connector で使用されているアカウントがウィザードの外部で変更された場合、ウィザードのその後の実行が失敗します。
- ドメイン コントローラーで、Microsoft Entra Connect のインストールが失敗することがあります。
- 拡張属性が追加されている場合、"ステージング モード" の有効化や無効化ができません。
- Active Directory Connector での正しくないパスワードのために、一部の構成ではパスワード ライトバックが失敗します。
- 属性フィルターで識別名 (DN) が使用されている場合、DirSync をアップグレードできません。
- パスワード リセットの使用時に CPU 使用量が過剰になります。

**削除されたプレビュー機能:**

- [ユーザーの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-preview#user-writeback) プレビュー機能は、プレビューを利用されているお客様からのフィードバックに基づいて一時的に削除されました。 このプレビュー機能は、提供されたフィードバックに対処した後で、再度追加されます。

### 1.0.8641.0

リリース日:2015 年 6 月

**Microsoft Entra Connect の初期リリースです。**

名前が Azure AD Sync から Microsoft Entra Connect に変更されました。

**新機能:**

- [簡単設定](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express)を使用したインストール
- [AD FS の構成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom#configuring-federation-with-ad-fs)
- [DirSync からのアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-dirsync-upgrade-get-started)
- [誤って削除されないように保護する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-prevent-accidental-deletes)
- [ステージング モード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server)

**新しいプレビュー機能:**

- [ユーザーの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-preview#user-writeback)
- [デバイスの書き戻し](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-device-writeback)
- [ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-preview)

### 1.0.494.0501

リリース日:2015 年 5 月

**新しい要件:**

- Azure AD Sync のインストールに .NET Framework 4.5.1 が必要になりました。

**修正された問題:**

- Microsoft Entra ID からのパスワード ライトバックが、Azure Service Bus 接続のエラーで失敗します。

### 1.0.491.0413

リリース日:2015 年 4 月

**修正された問題と機能強化:**

- ごみ箱が有効になっていて、フォレスト内に複数のドメインがある場合、Active Directory Connector が削除を正しく処理しません。
- Microsoft Entra コネクタで、インポート操作のパフォーマンスが向上しました。
- グループがメンバーシップの制限を超えた場合 (既定では、制限は 50,000 オブジェクトに設定)、Microsoft Entra ID でグループが削除されます。 新しい動作では、グループは削除されません。エラーがスローされ、新しいメンバーシップの変更はエクスポートされません。
- 同じ DN のステージングされた削除がコネクタ スペース内に既に存在する場合、新しいオブジェクトをプロビジョニングすることはできません。
- オブジェクトでステージングされている変更がなくても、差分同期中に一部のオブジェクトが同期のためにマークされます。
- パスワード同期を強制すると、優先 DC リストも削除されます。
- CSExportAnalyzer には、一部のオブジェクトの状態に関する問題があります。

**新機能:**

- 結合で、MV の "任意" のオブジェクト型に接続できるようになりました。

### 1.0.485.0222

リリース日:2015 年 2 月

**機能強化:**

- インポートのパフォーマンスが強化されました。

**修正された問題:**

- パスワード同期が、属性フィルターで使用される cloudFiltered 属性を受け取ります。 フィルター処理されたオブジェクトが、パスワード同期のスコープに含まれなくなります。
- トポロジが多くのドメイン コントローラーを持つまれな状況では、パスワード同期が機能しません。
- Azure AD/Intune でデバイス管理が有効化された後、Microsoft Entra Connector からのインポート時に、"サーバーが停止" します。
- 同じフォレスト内の複数のドメインの外部セキュリティ プリンシパル (FSP) を結合すると、あいまい結合のエラーが発生します。

### 1.0.475.1202

リリース日:2014 年 12 月

**新機能:**

- 属性ベースのフィルターでのパスワード同期がサポートされるようになりました。 詳細については、[フィルターによるパスワード同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering)に関するページを参照してください。
- ms-DS-ExternalDirectoryObjectID 属性が Active Directory に書き戻されます。 この機能により、Microsoft 365 アプリケーションのサポートが追加されます。 OAuth2 を使用して、ハイブリッド Exchange デプロイのオンラインとオンプレミスのメールボックスへのアクセスが行われます。

**修正されたアップグレードの問題:**

- より新しいバージョンのサインイン アシスタントをサーバーで利用できます。
- Azure AD Sync をインストールするために、カスタム インストール パスが使用されていました。
- 無効なカスタム結合条件によって、アップグレードがブロックされます。

**その他の修正:**

- Office Pro Plus 用のテンプレートが修正されました。
- ダッシュで始まるユーザー名によって発生する、インストールの問題が修正されました。
- インストール ウィザードを 2 回目に実行しているときに sourceAnchor 設定が失われる問題を修正しました。
- パスワード同期の ETW トレースの問題が修正されました。

### 1.0.470.1023

リリース日:2014 年 10 月

**新機能:**

- 複数のオンプレミス Active Directory から Microsoft Entra ID へのパスワードの同期。
- すべての Windows Server 言語にローカライズされたインストール UI。

**AADSync 1.0 GA からのアップグレード**

Azure AD Sync が既にインストールされている場合、標準の同期規則を変更したのであれば、追加の手順が 1 つ必要になります。 1.0.470.1023 リリースにアップグレードした後で、変更した同期規則は複製されます。 変更された各同期規則で、次の操作を行ってください。

1. 変更した同期規則を探して、変更内容をメモしておきます。
2. 同期規則を削除します。
3. Azure AD Sync によって作成された新しい同期規則を探して、変更を再適用します。

**Active Directory アカウントのアクセス許可**

Active Directory アカウントには、Active Directory からのパスワード ハッシュを読み取ることができるように、追加のアクセス許可を与える必要があります。 付与するアクセス許可の名前は、[ディレクトリの変更のレプリケート] と [ディレクトリの変更をすべてにレプリケート] です。 パスワード ハッシュを読み取るためには、両方のアクセス許可が必要です。

### 1.0.419.0911

リリース日:2014 年 9 月

**Azure AD Sync の最初のリリースです。**
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/security-updates-pks"} -->
## Microsoft Entra Connect と Microsoft Entra Connect Health の自動アップグレード プロセスに対するセキュリティの強化 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/security-updates-pks
- Service: entra-id / hybrid-connect
- Article date: 2025-09-17
- Summary: この記事では、自動アップグレードを向上するためのセキュリティの強化について説明します。

2023 年 9 月以降、Microsoft では、予防的なセキュリティ関連のサービス変更の一環として、Microsoft Entra Connect 同期と Microsoft Entra Connect Health のお客様を、更新されたビルドに自動アップグレードしています。 自動アップグレードされたユーザーはサービスの変更の影響を受けませんが、自動アップグレードまたは自動アップグレードを無効にした場合は、**2026 年 9 月 30 日**までに[最新バージョン](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history)にアップグレードすることを**強くお勧めします**。

### 予想される影響

次の表に、機能に関する情報と、推奨最小バージョンを使用していない場合に発生する可能性があるサービスへの影響を示します。

| サービス | 影響 |
| --- | --- |
| Microsoft Entra Connect | すべての同期サービスが失敗する |
| Microsoft Entra Connect Health Connect 同期エージェント | [アラート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-alert-catalog#alerts-for-microsoft-entra-connect-sync)のサブセットが影響を受けます。 - 認証エラーが原因で Microsoft Entra ID への接続が失敗しました - CPU 使用率が高いことが検出されました - メモリの消費が多いことが検出されました - パスワード ハッシュ同期が動作を停止しました - Microsoft Entra ID へのエクスポートが停止しました。 誤削除のしきい値に達しました - 過去 120 分間にパスワード ハッシュ同期のハートビートがスキップされました - 無効な暗号化キーが原因で Microsoft Entra Sync サービスを開始できません - Microsoft Entra Sync サービスが実行されていません: Windows サービス アカウントの資格情報の有効期限が切れています |
| Microsoft Entra Connect Health AD DS エージェント | [\[すべてのアラート\]](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-alert-catalog#alerts-for-active-directory-domain-services) |
| Microsoft Entra Connect Heath AD FS エージェント | [\[すべてのアラート\]](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-alert-catalog#alerts-for-active-directory-federation-services) |

### 最小バージョン

最新のセキュリティ強化を活用するために、 **2026 年 9 月 30 日**までに次のビルドにアップグレードすることを強くお勧めします。 サービスへの影響を回避するには、次の最小バージョンを使用する必要があります。

- Microsoft Entra Connect: [2.6.84.0](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-version-history#26840) 以降
- Microsoft Entra Connect Health
    - Connect 同期エージェント: [4.5.2466.0](https://aka.ms/connecthealth-download) 以降
    - AD DS エージェント: バージョン: [4.5.2466.0](https://aka.ms/connecthealth-adds-download) 以降
    - AD FS エージェント: バージョン: [4.5.2466.0](https://aka.ms/connecthealth-adfs-download) 以降

最新バージョンにアップグレードするには、次のようにします。

重要

**必須のアップグレードが必要です。**Connect Sync Microsoft Entraバージョン 2.6.84.0 以降にアップグレードし、2027 年 4 月 7 日までにアプリケーション ベースの認証を構成します。 レガシ認証は廃止され、これらの要件が満たされていない場合、同期サービスはこの日以降動作を停止します。

同期が停止した場合は、最新バージョンにアップグレードし、サービスを復元するようにアプリケーション ベースの認証を構成します。 Microsoft Entra Connect Sync .msi インストール ファイルは、 [Microsoft Entra Admin Center](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted) でのみ使用できます。 .NET Framework 4.7.2 や TLS 1.2 などの最小要件を満たしていることを確認します。

### Microsoft Entra クラウド同期への移行を検討する

資格をお持ちであれば、Microsoft Entra Connect 同期から Microsoft Entra クラウド同期に移行することをお勧めします。Microsoft Entra クラウド同期は、クラウドから動作する新しい同期クライアントであり、同期のユーザー設定をお客様がオンラインで設定および管理できるようにします。 Microsoft ではクラウド同期の使用をお勧めしています。クラウド同期を通じて同期エクスペリエンスを向上する新機能が導入されているためです。クラウド同期がご自分に適した選択肢であれば、それを選択すると、将来移行しなくて済むようになります。 [サポートされている同期シナリオの比較](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/common-scenarios)を使用して、Cloud Sync が適切な同期クライアントであるかどうかを確認します。

クラウド同期がビジネスにもたらす価値を理解するには、下記のビデオをご覧ください。

詳細については、「[クラウド同期とは](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/what-is-cloud-sync)」を参照してください。

### Microsoft Entra Connect 同期をアップグレードする

クラウド同期に移行する資格をまだお持ちでない場合は、こちらの表を利用してアップグレードに関する詳細を確認してください。

| タイトル | 説明 |
| --- | --- |
| [旧バージョンからのアップグレード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version) | Microsoft Entra Connect のバージョン間移行に関する情報 |
| [廃止に関する情報](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/deprecated-azure-ad-connect) | Microsoft Entra Connect の非推奨またはサポート対象外のバージョンの使用に関する情報 (一部の情報はサービス変更の影響を受けるバージョンに適用されます) |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-clear-on-premises-attributes"} -->
## Microsoft Entra Connect: 移行された Microsoft Entra ID ユーザーのオンプレミス属性を削除する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-clear-on-premises-attributes
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra IDで移行されたユーザーからオンプレミスの属性をクリーンアップする方法について説明します。

ユーザーとグループをMicrosoft Entra IDに移行した後、on-premises Active Directoryの使用を停止して同期ツールをアンインストールする準備が整う場合があります。 ディレクトリ同期を無効にした後、Microsoft Entra IDでこれらのオブジェクトを直接管理できます。

ただし、Windows、Intune、Outlookで問題が発生する可能性があります。これは、以前にオンプレミスから同期されていたユーザー属性にレガシ値が残っている場合です。 たとえば、システムがこれらの古い属性からユーザー名とドメインをプルするため、ハイブリッド デバイスの参加が失敗する可能性があります。

これらの問題を回避するには、次のオンプレミス属性をクリアすることをお勧めします。

- onPremisesDistinguishedName
- onPremisesDomainName
- onPremisesImmutableId
- オンプレミスオブジェクト識別子 (onPremisesObjectIdentifier)
- オンプレミスSAMアカウント名
- オンプレミス セキュリティ識別子
- オンプレミスユーザープリンシパル名 (onPremisesUserPrincipalName)

### これらの属性を更新する方法

これらの属性は、[Update User](https://learn.microsoft.com/ja-jp/graph/api/user-update) API 呼び出しを使用して、Microsoft Graph Beta を介して更新できます。 これらの属性は、クラウドのみのユーザーに対してのみMicrosoft Entra IDで更新できます。 これには、以前に同期され、後でテナントの同期が無効になったときにクラウド専用に変換されたユーザーが含まれます。

#### 必要な役割

オンプレミスの属性を更新できるEntra IDロールは次のとおりです。

- [ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)
- [ハイブリッド ID の管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)

#### 必要なアクセス許可

必要なアプリケーションのアクセス許可は **User.ReadWrite.All** と **User-OnPremisesSyncBehavior.ReadWrite.All** です (後者は **onPremisesObjectIdentifier** 属性専用に必要です)。

### ADSyncTools PowerShell モジュールの使用

また、提供されている PowerShell スクリプトを使用して、これらのオンプレミスの属性を表示および更新することもできます。

#### ADSyncTools PowerShell モジュールを使用してオンプレミスの属性を管理するための前提条件:

- [Windows PowerShell 7](https://learn.microsoft.com/ja-jp/powershell/scripting/install/installing-powershell-on-windows)
- [Microsoft Graph SDK PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation)

PowerShell Galleryから [ADSyncTools](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adsynctools) モジュールをインストールします。

```powershell
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12 
Install-Module ADSyncTools # If ADSyncTools isn’t installed, or; 
Update-Module ADSyncTools # If ADSyncTools is already installed 
```

注

Entra IDでオンプレミス属性を管理するために必要な最小バージョンは v2.5.0 です。

ADSyncTools の使用を開始するには、次のコマンドを使用します。

```powershell
Import-Module ADSyncTools 
```

オンプレミスの属性を管理するために使用できるコマンドレットを参照してください。

```powershell
Get-Command *onpremises* -Module ADSyncTools 
```

結果:

```
CommandType   Name                      Version  Source 
-----------   ----                      -------  ------ 
Function    Clear-ADSyncToolsOnPremisesAttribute      2.5.0   ADSyncTools 
Function    Get-ADSyncToolsOnPremisesAttribute       2.5.0   ADSyncTools 
Function    Set-ADSyncToolsOnPremisesAttribute       2.5.0   ADSyncTools 

```

Get-Help &lt; cmdlet&gt; -Full を使用して、コマンドレットのすべての詳細 (構文や例など) を取得します。

`Get-Help Get-ADSyncToolsOnPremisesAttribute -Full `

### Get-ADSyncToolsOnPremisesAttribute

#### 説明

Entra IDでオンプレミスのプロパティを含む特定のユーザーまたはすべてのユーザーを取得します。 オンプレミスの属性が設定されているユーザーのみが返されます。 既定では、すべてのクラウド専用ユーザーが返されますが、オンプレミス AD から同期されたユーザーを含め、すべてのユーザーを返す `-IncludeSyncedUsers` を指定できます。

この操作には、`Connect-MgGraph -Scopes "User.ReadWrite.All, User-OnPremisesSyncBehavior.ReadWrite.All"` で事前認証されたMicrosoft Graph PowerShell SDKが必要です

#### 構文

##### Identity を使用

```powershell
 Get-ADSyncToolsOnPremisesAttribute [-Id] <String> [[-Property] <String[]>] [<CommonParameters>] 
```

##### IncludeSyncedUsers を使用

```powershell
Get-ADSyncToolsOnPremisesAttribute [[-IncludeSyncedUsers]] [[-Property] <String[]>] [<CommonParameters>] 
```

#### 例の数々

##### 例 1

オンプレミス属性が設定されているすべてのクラウド ユーザーのオンプレミス属性を取得します。

```powershell
Get-ADSyncToolsOnPremisesAttribute 
```

#### すべてのユーザーのすべてのオンプレミス属性をクリアする

すべてのユーザーからオンプレミスのすべての属性を一括でクリアするには、get 関数を使用して、オンプレミスの属性を含むすべてのクラウド専用ユーザーの一覧を取得し、結果を Clear コマンドレットにパイプライン処理し、パラメーター -All を追加します。

この操作には、`Connect-MgGraph -Scopes "User.ReadWrite.All, User-OnPremisesSyncBehavior.ReadWrite.All"` で事前認証されたMicrosoft Graph PowerShell SDKが必要です

重要

運用環境のEntra IDユーザーからオンプレミスの属性をクリアする前に、必要に応じて操作をロールバックできるように、ユーザーのオンプレミスのプロパティをバックアップします。

次のコマンドを使用して、現在のすべての値をバックアップできます。

```powershell
Get-ADSyncToolsOnPremisesAttribute | Export-Csv backupOnpremisesAttributes.csv -Delimiter ';' 
```

すべてのユーザーからオンプレミスのすべての属性をクリアするには、次のコマンドを実行します。

```powershell
Get-ADSyncToolsOnPremisesAttribute | Select-Object id | Clear-ADSyncToolsOnPremisesAttribute -All -Verbose 
```

#### 1 人のユーザーのすべてのオンプレミス属性をクリアする

1 人の特定のユーザーのすべてのオンプレミス属性をクリアするには、objectId または UserPrincipalName の後に -All パラメーターを指定します。

この操作には、`Connect-MgGraph -Scopes "User.ReadWrite.All, User-OnPremisesSyncBehavior.ReadWrite.All"` で事前認証されたMicrosoft Graph PowerShell SDKが必要です

```powershell
Clear-ADSyncToolsOnPremisesAttribute 'User1@Contoso.com' -All 
```

`Clear-ADSyncToolsOnPremisesAttribute `を使用して、次のオンプレミス属性を個別にクリアすることもできます。

- onPremisesDistinguishedName
- onPremisesDomainName
- onPremisesImmutableId
- オンプレミスオブジェクト識別子 (onPremisesObjectIdentifier)
- オンプレミスSAMアカウント名
- オンプレミス セキュリティ識別子
- オンプレミスユーザープリンシパル名 (onPremisesUserPrincipalName)

### Clear-ADSyncToolsOnPremisesAttribute

#### 説明

特定の Cloud-Only ユーザーまたはEntra IDのすべての CLoud-Only ユーザーのオンプレミス のプロパティをクリアします。

#### 構文

```powershell
 Clear-ADSyncToolsOnPremisesAttribute [-Id] <String> [[-onPremisesDistinguishedName]] [[-onPremisesDomainName]] [[-onPremisesImmutableId]] 
[[-onPremisesObjectIdentifier]] [[-onPremisesSamAccountName]] [[-onPremisesSecurityIdentifier]] [[-onPremisesUserPrincipalName]] [<CommonParameters>] 
```

##### BodyParameter によって

```powershell
 Clear-ADSyncToolsOnPremisesAttribute [-Id] <String> [-BodyParameter] <String> [<CommonParameters>] 
```

##### すべて

```powershell
 Clear-ADSyncToolsOnPremisesAttribute [-Id] <String> [-All] [<CommonParameters>] 
```

##### 例 1

onPremisesImmutableId 属性のみをクリアする

```powershell
 Clear-ADSyncToolsOnPremisesAttribute -Id '12345678-90ab-cd12-3456-7890abcd1234' -onPremisesImmutableId
```

##### 例 2

json パラメーター本文 (-BodyParameter) に基づいてオンプレミスの属性をクリアする

```powershell
$jsonBody = @'
{ 
  "onPremisesDistinguishedName": null, 
  "onPremisesDomainName": null, 
  "onPremisesImmutableId": null, 
  "onPremisesObjectIdentifier": null, 
  "onPremisesSamAccountName": null, 
  "onPremisesSecurityIdentifier": null, 
  "onPremisesUserPrincipalName": null 
} 
'@ 

Clear-ADSyncToolsOnPremisesAttribute -Id $userId -BodyParameter $jsonBody

```

### Set-ADSyncToolsOnPremisesAttribute

Entra IDの Cloud-Only ユーザーのオンプレミス属性を設定します。

この操作には、`Connect-MgGraph -Scopes "User.ReadWrite.All, User-OnPremisesSyncBehavior.ReadWrite.All"` で事前認証されたMicrosoft Graph PowerShell SDKが必要です

重要

運用環境のEntra IDユーザーのオンプレミス属性を更新する前に、必要に応じて操作をロールバックできるように、ユーザーのオンプレミスのプロパティをバックアップします。

次のコマンドを使用して、現在のすべての値をバックアップできます。

```powershell
Get-ADSyncToolsOnPremisesAttribute | Export-Csv backupOnpremisesAttributes.csv -Delimiter ';' 
```

この関数を使用して、次のいずれかのオンプレミス属性を設定できます。

- onPremisesDistinguishedName
- onPremisesDomainName
- onPremisesImmutableId
- onPremisesObjectIdentifier \*
- sonPremisesSamAccountName
- onPremisesSecurityIdentifier \*\*
- オンプレミスユーザープリンシパル名 (onPremisesUserPrincipalName)

    \* システム生成属性。 クリアはサポートされていますが、サービスの動作によっては、特定の値の設定が失敗する可能性があります。

    \*\* 正しいセキュリティ識別子形式が必要です(例: "S-1-5-21-1234567890-0987654321-1234567890-1111"

#### 構文

```powershell
Set-ADSyncToolsOnPremisesAttribute [-Id] <String> [[-onPremisesDistinguishedName] <String>] [[-onPremisesDomainName] <String>] [[-onPremisesImmutableId] <String>] [[-onPremisesSamAccountName] <String>] [[-onPremisesSecurityIdentifier] <String>] [[-onPremisesUserPrincipalName] <String>] [<CommonParameters>] 
```

##### BodyParameter によって

```powershell
Set-ADSyncToolsOnPremisesAttribute [-Id] <String> [-BodyParameter] <String> [<CommonParameters>] 
```

#### 例の数々

##### 例 1

onPremisesImmutableId のみを設定する (パイプライン処理)

```powershell
'User1@Contoso.com' | Set-ADSyncToolsOnPremisesAttribute -onPremisesImmutableId 'nofCJe0gZk6D8J4gRgrt+A==' 
```

##### 例 2

json パラメーター本文 (-BodyParameter) に基づいてオンプレミスの属性を設定する

```powershell
$jsonBody = @' 
{ 
  "onPremisesDistinguishedName": "User1@Contoso.com", 
  "onPremisesDomainName": 'Contoso.com', 
  "onPremisesImmutableId": 'nofCJe0gZk6D8J4gRgrt+A==', 
  "onPremisesSamAccountName": 'User1', 
  "onPremisesSecurityIdentifier": "S-1-5-21-4097605469-3104078553-1111111111-1111", 
  "onPremisesUserPrincipalName": "User1@Contoso.com" 
}
'@
Set-ADSyncToolsOnPremisesAttribute -Id '11111111-2222-3333-4444-555555555555' -BodyParameter $jsonBody
```

注

`-Verbose`を任意のコマンドと共に使用して、関数の動作に関する追加の詳細を表示できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-attribute-not-syncing"} -->
## Microsoft Entra Connect で同期していない属性のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-attribute-not-syncing
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、トラブルシューティング タスクを使用して、属性同期の問題のトラブルシューティングを行う方法の手順を示します。

### **推奨される手順**

属性同期の問題を調査する前に、**Microsoft Entra Connect** の同期プロセスについて理解しておきましょう。

[Image: Microsoft Entra Connect の同期プロセス]

#### **用語**

- **CS:** コネクタ スペース。データベース内のテーブルです。
- **MV:** メタバース。データベース内のテーブルです。
- **AD：** Active Directory

#### **同期のステップ**

- AD からインポートする:Active Directory オブジェクトが AD CS に取り込まれます。
- Microsoft Entra ID からのインポート: Microsoft Entra オブジェクトが Microsoft Entra CS に取り込まれます。
- 同期:**受信同期規則**と**送信同期規則**は、優先順位の番号の低い方から順に実行されます。 同期規則は、デスクトップ アプリケーションから**同期規則エディター**にアクセスして表示することができます。 **受信同期規則**は、CS から MV にデータを取り込みます。 **送信同期規則**は、MV から CS にデータを移動します。
- AD にエクスポートする:同期の実行後、AD CS から **Active Directory** にオブジェクトがエクスポートされます。
- Microsoft Entra ID へのエクスポート: 同期の実行後、Microsoft Entra CS から **Microsoft Entra ID** にオブジェクトがエクスポートされます。

#### **ステップ バイ ステップの調査**

- まず、**メタバース**から検索し、ソースからターゲットへの属性マッピングを確認します。
- 下図のように、デスクトップ アプリケーションから **Synchronization Service Manager** を起動します。

    [Image: Synchronization Service Manager を起動する]
- **Synchronization Service Manager** で、 **[メタバース検索]** 、 **[Scope by Object Type](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/オブジェクトの種類でスコープ)** の順に選択し、属性を使用してオブジェクトを選び、 **[検索]** ボタンをクリックします。

    [Image: メタバース検索]
- **メタバース**検索で見つかったオブジェクトをダブルクリックし、その属性をすべて表示します。 **[コネクタ]** タブをクリックすることで、すべての **[コネクタ スペース]** の対応するオブジェクトを確認できます。

    [Image: メタバース オブジェクトのコネクタ]
- **[Active Directory コネクタ]** をダブルクリックし、 **[コネクタ スペース]** 属性を表示します。 以下のダイアログで **[プレビュー]** ボタンをクリックし、 **[Generate Preview](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/プレビューの生成)** ボタンをクリックします。

    [Image: [コネクタ スペース オブジェクトのプロパティ] 画面を示すスクリーンショット。[プレビュー] ボタンが強調表示されています。]
- ここで、 **[インポート属性フロー]** をクリックします。これにより、**Active Directory コネクタ スペース**から**メタバース**への属性のフローが表示されます。 **[同期規則]** 列には、その属性に関係する**同期規則**が表示されます。 **[データ ソース]** 列には、**コネクタ スペース**からの属性が表示されます。 **[メタバース属性]** 列には、**メタバース**の属性が表示されます。 ここで同期していない属性を見つけることができます。 ここで属性が見つからない場合、これはマップされておらず、新しいカスタム**同期規則**を作成して、属性をマップする必要があります。

    [Image: コネクタ スペースの属性]
- 左側のウィンドウで **[エクスポート属性フロー]** をクリックし、**アウトバウンド同期規則**を使用して、**メタバース**から **Active Directory コネクタ スペース**に戻る属性フローを表示します。

    [Image: メタバースから Active Directory コネクタ スペースへ戻る [属性フロー] を示すスクリーンショット。ここでは、送信同期規則が使用されています。]
- 同様に、**Microsoft Entra コネクタ スペース** オブジェクトを表示し、**プレビュー**を生成して、**メタバース**から**コネクタ スペース**へ、またその逆で属性フローを表示できます。このようにして、属性が同期していない理由を調査できます。

### **推奨ドキュメント**

- [Microsoft Entra Connect 同期: 技術的概念](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-technical-concepts)
- [Microsoft Entra Conect 同期: アーキテクチャの解釈](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-architecture)
- [Microsoft Entra Connect Sync: 宣言型プロビジョニングについて](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning)
- [Microsoft Entra Connect 同期: 宣言型プロビジョニング式について](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-declarative-provisioning-expressions)
- [AMicrosoft Entra Connect 同期: 既定の構成について](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-default-configuration)
- [Microsoft Entra Connect 同期: ユーザー、グループ、連絡先について](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/concept-azure-ad-connect-sync-user-and-contacts)
- [Microsoft Entra Connect 同期: シャドウ属性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-shadow-attributes)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-connectivity"} -->
## Microsoft Entra Connect: Microsoft Entra の接続に関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-connectivity
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect での接続に関する問題のトラブルシューティング方法について説明します。

この記事では、Microsoft Entra Connect と Microsoft Entra ID の間の接続のしくみと、接続に関する問題のトラブルシューティング方法について説明します。 このような問題は、プロキシ サーバーを使用する環境において発生する可能性が最も高くなります。

### インストール ウィザードにおける接続に関する問題

Microsoft Entra Connect では、認証に Microsoft Authentication Library (MSAL) が使用されます。 インストール ウィザードと同期エンジンは、.NET アプリケーションであるため、machine.config を適切に構成する必要があります。

Note

Azure AD Connect v1.6.xx.x では、Active Directory 認証ライブラリ (ADAL) が使用されます。 ADAL は非推奨となっており、2022 年 6 月にサポートが終了します。 最新バージョンの [Microsoft Entra Connect v2](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect-v2) にアップグレードすることをお勧めします。

この記事では、Fabrikam がプロキシを介して Microsoft Entra ID に接続する方法について説明します。 プロキシ サーバーは `fabrikamproxy` という名前で、ポート 8080 を使用しています。

最初に、[machine.config](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#connectivity) が正しく構成されており、*machine.config* ファイルの更新後に Microsoft Entra ID 同期サービスが再起動されていることを確認します。

[Image: machine.config ファイルの一部を示すスクリーンショット。]

Note

Microsoft 以外の一部のブログでは、*machine.config* ファイルではなく *miiserver.exe.config* に変更を加える必要があると記載されています。 ただし、*miiserver.exe.config* ファイルはアップグレードするたびに上書きされます。 初期インストール中にファイルが動作する場合でも、システムは最初のアップグレード中に動作を停止します。 そのため、この記事の説明に従って *machine.config* を更新することをお勧めします。

プロキシ サーバーでは、必須となる URL を開いておくことも必要です。 公式の URL 一覧は、「[Office 365 URL および IP アドレス範囲](https://support.office.com/article/Office-365-URLs-and-IP-address-ranges-8548a211-3fe7-47cb-abb1-355ea5aa88a2)」に記載されています。

その中でも、次の表にリストされている URL は Microsoft Entra ID への接続に最低限必要な URL です。 この一覧には、パスワード ライトバックや Microsoft Entra Connect Health のようなオプション機能は含まれていません。 初期構成に関するトラブルシューティングに役立つ情報については、こちらを参照してください。

| URL | 港 / ポート | 説明 |
| --- | --- | --- |
| `mscrl.microsoft.com` | HTTP/80 | 証明書失効リスト (CRL) リストをダウンロードするために使用されます。 |
| `*.verisign.com` | HTTP/80 | CRL リストのダウンロードに使用します。 |
| `*.entrust.net` | HTTP/80 | 多要素認証 (MFA) 用の CRL リストをダウンロードするために使用されます。 |
| `*.management.core.windows.net` (Azure Storage)`*.graph.windows.net` (Azure AD Graph) | HTTPS/443 | さまざまな Azure サービスに使用します。 |
| `secure.aadcdn.microsoftonline-p.com` | HTTPS/443 | MFA に使用します。 |
| `graph.microsoft.com` | HTTPS/443 | 2.4.18.0 以降で使用されます。 |
| `*.microsoftonline.com` | HTTPS/443 | Microsoft Entra ディレクトリの構成とデータのインポート/エクスポートに使用します。 |
| `*.crl3.digicert.com` | HTTP/80 | 証明書の確認に使用します。 |
| `*.crl4.digicert.com` | HTTP/80 | 証明書の確認に使用します。 |
| `*.digicert.cn` | HTTP/80 | 証明書の確認に使用します。 |
| `*.ocsp.digicert.com` | HTTP/80 | 証明書の確認に使用します。 |
| `*.www.d-trust.net` | HTTP/80 | 証明書の確認に使用します。 |
| `*.root-c3-ca2-2009.ocsp.d-trust.net` | HTTP/80 | 証明書の確認に使用します。 |
| `*.crl.microsoft.com` | HTTP/80 | 証明書の確認に使用します。 |
| `*.oneocsp.microsoft.com` | HTTP/80 | 証明書の確認に使用します。 |
| `*.ocsp.msocsp.com` | HTTP/80 | 証明書の確認に使用します。 |

### ウィザードでのエラー

インストール ウィザードでは、2 種類のセキュリティ コンテキストを使用します。 **[Microsoft Entra ID への接続]** ページでは、現在サインインしているユーザーが使用されます。 **[構成]** ページでは、使用するセキュリティ コンテキストを[同期エンジンのサービスを実行しているアカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-accounts-permissions#adsync-service-account)に変更します。 問題が発生した場合、プロキシ構成がグローバルであるため、ウィザードの **[Microsoft Entra ID に接続]** ページにエラーが表示される可能性が高くなります。

インストール ウィザードで発生する可能性のある最も一般的な問題を次に示します。

#### インストール ウィザードが正しく構成されていない

このエラーは、ウィザード自体がプロキシに接続できない場合に表示されます。

[Image: スクリーンショットは次のエラーを示しています。資格情報を検証できません。]

このエラーが表示された場合は、[machine.config ファイル](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#connectivity)が正しく構成されていることを確認します。 *machine.config* が正しいようであれば、「プロキシ接続を確認する」の手順を完了して、ウィザード外部でも問題が発生しているかどうかを確認します。

#### Microsoft アカウントが使用されている

*学校または組織*のアカウントではなく *Microsoft アカウント*を使用すると、一般的なエラーが表示されます。

[Image: 汎用資格情報の検証エラーを示すスクリーンショット。]

#### MFA エンドポイントに到達できない

このエラーは、エンドポイント `https://secure.aadcdn.microsoftonline-p.com` に到達できず、ハイブリッド ID 管理者が MFA を有効にしている場合に表示されます。

[Image: MFA エンドポイントに到達できない場合のスクリプト エラーの例を示すスクリーンショット。]

このエラーが表示された場合は、エンドポイント `secure.aadcdn.microsoftonline-p.com` がプロキシに追加されていることを確認します。

#### パスワードを確認できない

インストール ウィザードによる Microsoft Entra ID への接続は成功したものの、パスワード自体を確認できない場合に、このエラーが表示されます。

[Image: パスワードを確認できない場合に発生するエラーを示すスクリーンショット。]

そのパスワードは変更する必要がある一時パスワードではないでしょうか。 また、本当に正しいパスワードでしょうか。 Microsoft Entra Connect サーバーとは別のコンピューターで `https://login.microsoftonline.com` へのサインインを試し、アカウントが使用可能であることを確認してください。

#### プロキシ接続を検証する

Microsoft Entra Connect サーバーがプロキシとインターネットに接続しているかどうかを確認するには、一部の PowerShell コマンドレットを使用して、プロキシが Web 要求を許可しているかどうかを確認します。 PowerShell で、`Invoke-WebRequest -Uri https://adminwebservice.microsoftonline.com/ProvisioningService.svc` を実行します。 (厳密には、最初に呼び出すのは `https://login.microsoftonline.com` です。この URI は同様に機能しますが、応答が速いのは前の URI です。)

PowerShell は、*machine.config* 内の構成を使用してプロキシに接続します。 *winhttp や netsh* 内の設定値がこれらのコマンドレットに影響することはありません。

プロキシが正しく構成されていれば、成功状態が次のように表示されます。

[Image: プロキシが正しく構成されている場合の成功状態を示すスクリーンショット。]

**リモート サーバーに接続できません** というメッセージが表示された場合は、PowerShell がプロキシを使用せずに直接呼び出しを試みているか、DNS が正しく構成されていません。 *machine.config* ファイルが正しく構成されていることを確認します。

[Image: PowerShell がリモート サーバーに接続できない場合のエラー メッセージのスクリーンショット。]

プロキシが正しく構成されていない場合は、403 または 407 のエラー メッセージが表示されます。

[Image: PowerShell での 403 プロキシ エラーのスクリーンショット。]

[Image: PowerShell での 407 プロキシ エラーのスクリーンショット。]

次の表では、403 および 407 プロキシ エラーについて説明します。

| Error | エラー テキスト | Comment |
| --- | --- | --- |
| 4:03 | 許可されていません | 要求された URL に対してプロキシが開かれていません。 プロキシ構成を再検討し、[URL](https://support.office.com/article/Office-365-URLs-and-IP-address-ranges-8548a211-3fe7-47cb-abb1-355ea5aa88a2) が開かれていることを確認してください。 |
| 407 | プロキシの認証が必要です | プロキシ サーバーがサインイン情報を要求しましたが、何も指定されていません。 お使いのプロキシ サーバーで認証が必要な場合は、その設定が *machine.config* 内で構成されているようにしてください。また、ウィザードを実行しているユーザーおよびサービス アカウントにドメイン アカウントを使用していることを確認してください。 |

#### プロキシ アイドル タイムアウトの設定

Microsoft Entra Connect が Microsoft Entra ID にエクスポート要求を送信した場合、Microsoft Entra ID が要求を処理して応答を生成するまでに最大 5 分かかることがあります。 大きなグループ メンバーシップを持つ多数のグループ オブジェクトが同じエクスポート要求に含まれている場合、応答が特に遅れる可能性があります。 プロキシ アイドル タイムアウトを、5 分より長く構成してください。 そうしないと、Microsoft Entra Connect サーバー上の Microsoft Entra ID との接続が断続的という問題が発生する可能性があります。

### Microsoft Entra Connect と Microsoft Entra ID の間の通信パターン

この記事で説明されているすべての手順に従っても接続できない場合は、この時点でネットワーク ログを確認できます。 このセクションでは、通常の成功を示す接続パターンについて記載しています。

ただし、まず、無視できるネットワーク ログ内のデータに関する一般的な懸念事項を次に示します。

- [https://dc.services.visualstudio.com](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/`https:/dc.services.visualstudio.com`) への呼び出しが行われています。 インストールを正常に実行するうえで、この URL をプロキシで開いておくことは必須ではないため、この呼び出しは無視できます。
- DNS 解決では、実際のホストは DNS の名前空間 `nsatc.net` にあり、`microsoftonline.com` の下にない他の名前空間があるとリストされていることがわかります。 ただし、実際のサーバー名には Web サービス要求はありません。 これらの URL をプロキシに追加する必要はありません。
- エンドポイントの `adminwebservice` と `provisioningapi` は検出エンドポイントであり、実際に使用するエンドポイントを見つけるために使用されます。 こうしたエンドポイントは、リージョンによって異なります。

#### 参照用プロキシ ログ

実際のプロキシ ログのダンプと、その取得元のインストール ウィザード ページを次の例に示します (エンドポイントが重複する項目は削除してあります)。 このセクションは、お使いの環境でのプロキシおよびネットワーク ログの参照用としてご利用ください。 実際のエンドポイントは環境によって異なる場合があります (特に*斜体*で示された URL)。

**Microsoft Entra ID との接続**

| 時間 | URL |
| --- | --- |
| 1/11/2016 8:31 | connect:/login.microsoftonline.com:443 |
| 1/11/2016 8:31 | connect://adminwebservice.microsoftonline.com:443 |
| 1/11/2016 8:32 | connect://*bba800-anchor*.microsoftonline.com:443 |
| 1/11/2016 8:32 | connect://login.microsoftonline.com:443 |
| 1/11/2016 8:33 | connect://provisioningapi.microsoftonline.com:443 |
| 1/11/2016 8:33 | connect://*bwsc02-relay*.microsoftonline.com:443 |

**構成**

| 時間 | URL |
| --- | --- |
| 1/11/2016 8:43 | connect://login.microsoftonline.com:443 |
| 1/11/2016 8:43 | connect://*bba800-anchor*.microsoftonline.com:443 |
| 1/11/2016 8:43 | connect://login.microsoftonline.com:443 |
| 1/11/2016 8:44 | connect://adminwebservice.microsoftonline.com:443 |
| 1/11/2016 8:44 | connect://*bba900-anchor*.microsoftonline.com:443 |
| 1/11/2016 8:44 | connect://login.microsoftonline.com:443 |
| 1/11/2016 8:44 | connect://adminwebservice.microsoftonline.com:443 |
| 1/11/2016 8:44 | connect://*bba800-anchor*.microsoftonline.com:443 |
| 1/11/2016 8:44 | connect://login.microsoftonline.com:443 |
| 1/11/2016 8:46 | connect://provisioningapi.microsoftonline.com:443 |
| 1/11/2016 8:46 | connect://*bwsc02-relay*.microsoftonline.com:443 |

**初期同期**

| 時間 | URL |
| --- | --- |
| 1/11/2016 8:48 | connect://login.windows.net:443 |
| 1/11/2016 8:49 | connect://adminwebservice.microsoftonline.com:443 |
| 1/11/2016 8:49 | connect://*bba900-anchor*.microsoftonline.com:443 |
| 1/11/2016 8:49 | connect://*bba800-anchor*.microsoftonline.com:443 |

### 認証エラー

このセクションでは、ADAL および PowerShell から返される可能性があるエラーについて説明します。 エラーの説明は、次に進むステップを特定するうえで役立ちます。

#### 無効な許可

無効なユーザー名またはパスワードを入力しました。 詳細については、「パスワードを確認できない」を参照してください。

#### ユーザーの種類が不明

Microsoft Entra ディレクトリが見つからないか、解決できません。 確認されていないドメインのユーザー名でサインインしようとした可能性があります。

#### ユーザー領域を検出できない

ネットワークまたはプロキシの構成の問題です。 ネットワークに接続できません。 「インストール ウィザードにおける接続に関する問題」を参照してください。

#### ユーザー パスワードの期限切れ

資格情報が有効期限切れです。 パスワードを変更してください。

#### 承認エラー

Microsoft Entra Connect は、ユーザーが Microsoft Entra ID でアクションを実行することを承認できませんでした。

#### 認証が取り消された

MFA チャレンジが取り消されました。

#### MS Online への接続に失敗した

認証は成功しましたが、Microsoft Entra PowerShell に認証の問題があります。

#### Privileged Identity Management が有効になっている

認証は成功しましたが、Privileged Identity Management が有効になっており、ユーザーは現在ハイブリッド ID 管理者ではありません。 詳細については、[Privileged Identity Management](https://learn.microsoft.com/ja-jp/entra/id-governance/privileged-identity-management/pim-getting-started) に関するページをご覧ください。

#### 会社情報が利用できません

認証は成功しましたが、Microsoft Entra ID から会社情報を取得できませんでした。

#### ドメイン情報が利用できません

認証は成功しましたが、Microsoft Entra ID からドメイン情報を取得できませんでした。

#### 不明な認証エラー

インストール ウィザードで*予期しないエラー*として表示されます。 このエラーは、*学校または組織のアカウント*ではなく *Microsoft アカウント*を使用しようとすると発生する可能性があります。

### 以前のリリースでのトラブルシューティング手順

ビルド番号 1.1.105.0 (2016 年 2 月リリース) 以降のリリースでは、サインイン アシスタントが提供されなくなりました。 サインイン アシスタントを構成する必要はなくなりましたが、次のセクションの情報は参照用に含まれています。

シングル サインイン アシスタントを機能させるには、Microsoft Windows HTTP サービス (WinHTTP) を構成する必要があります。 [netsh](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#connectivity) を使用して WinHTTP を構成できます。

[Image: プロキシを設定するための netsh ツールが実行されているコマンド プロンプト ウィンドウを示すスクリーンショット。]

#### サインイン アシスタントが正しく構成されていない

このエラーは、サインイン アシスタントがプロキシに接続できないか、プロキシが要求を許可していない場合に表示されます。

[Image: スクリーンショットは次のエラーを示しています。資格情報を検証できません。ネットワーク接続とファイアウォールまたはプロキシの設定を確認してください。]

このエラーが表示された場合は、[netsh](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#connectivity) でプロキシ構成が正しいことを確認します。

[Image: プロキシ構成を表示するための netsh ツールが実行されているコマンド プロンプト ウィンドウを示すスクリーンショット。]

プロキシ設定が正しいようであれば、「プロキシ接続を確認する」の手順を完了して、ウィザード外部でも問題が発生しているかどうかを確認します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-install-issues"} -->
## Microsoft Entra Connect のインストールに関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-install-issues
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、Microsoft Entra Connect のインストールに関する問題をトラブルシューティングする方法について説明します。

### **推奨される手順**

Microsoft Entra Connect のインストールの種類  が適しているかどうかを確認します。 高速インストールの条件を満たしている場合は、高速インストールを使用することを強くお勧めします。 高速インストールでは、インストールを完了するために必要な最小限のオプションが提供され、問題が発生する可能性は低くなります。

ただし、高速インストールの条件を満たせず、カスタム インストールを実行する必要がある場合は、一般的な問題を回避するために従うことができるベスト プラクティスをいくつか次に示します。 わかりやすくするために、ここでは選択的なオプションのみを説明します。

- Microsoft Entra Connect をインストールするコンピューターの管理者であることを確認します。 同じ管理者資格情報を使用してマシンにサインインします。
- 既存の SQL Server を使用する場合は、[既存の SQL Server を使用する] を除き、次のページですべてのオプションを既定値にします。 カスタム インストール オプション [使用する方法の詳細](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom) を次に示します。

    既存の SQL Server を使用する
- 次のページで、[新しい AD アカウントの作成] オプションを選択して、既存のアカウントに対するアクセス許可の問題を回避します。

    [Image: AD フォレスト アカウント]

#### **一般的な問題**

- [オンプレミスの Active Directory に関する接続問題](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-adconnectivitytools)。
- [オンライン Microsoft Entra ID に関する接続の問題](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-connectivity)。
- [オンプレミスの Active Directory](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-configure-ad-ds-connector-account)に関するアクセス許可の問題。

### **推奨ドキュメント**

- Microsoft Entra Connect の前提条件
- [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-select-installation) に使用するインストールの種類を選択します
- [簡易設定を使用した Microsoft Entra Connect の開始](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-express)
- [Microsoft Entra Connect](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom) のカスタム インストール
- [Microsoft Entra Connect: 以前のバージョンから最新の](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version) にアップグレードする
- [Microsoft Entra Connect: ステージング サーバーとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-topologies#staging-server)
- [`ADConnectivityTool` PowerShell モジュールとは](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-adconnectivitytools)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-largeobjecterror-usercertificate"} -->
## Microsoft Entra Connect - userCertificate 属性によって発生する LargeObject エラー - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-largeobjecterror-usercertificate
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、userCertificate 属性によって発生する LargeObject エラーの修復手順について説明します。

Microsoft Entra ID では、**userCertificate** 属性に **15** 証明書の値の上限が適用されます。 Microsoft Entra Connect が 15 を超える値を持つオブジェクトを Microsoft Entra ID にエクスポートした場合、Microsoft Entra ID は、**LargeObject** エラーと共にメッセージを返します。

>
> *"プロビジョニングされたオブジェクトが大きすぎます。 このオブジェクトの属性値の数をトリミングします。 この操作は、次の同期サイクルで再試行されます。..."*

LargeObject エラーは、他の AD 属性によって発生する可能性があります。 原因が userCertificate 属性であることを確認するには、オンプレミス AD または [Synchronization Service Manager メタバース検索](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvsearch)でオブジェクトに対して検証します。

LargeObject エラーを含むテナント内のオブジェクトの一覧を取得するには、次のいずれかの方法を使用します。

- テナントで Microsoft Entra Connect Health for sync が有効になっている場合は、[同期エラー レポート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync) を参照できます。
- [[Synchronization Service Manager 操作\] タブ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-operations)、最新の Microsoft Entra へのエクスポート操作を選択した場合に LargeObject エラーが発生したオブジェクトの一覧が表示されます。

### 軽減策のオプション

LargeObject エラーが解決されるまで、同じオブジェクトに対する他の属性の変更を Microsoft Entra ID にエクスポートすることはできません。 このエラーを解決するには、次のオプションを検討してください。

- Microsoft Entra Connect をビルド 1.1.524.0 以降にアップグレードします。 Microsoft Entra Connect ビルド 1.1.524.0 では、属性に 15 を超える値がある場合、属性 userCertificate と userSMIMECertificate をエクスポートしないように、既定の同期規則が更新されました。 Microsoft Entra Connect をアップグレードする方法の詳細については、「Microsoft Entra Connect: 以前のバージョンから最新のにアップグレードする」記事を参照してください。
- Microsoft Entra Connect で 15 を超える証明書値を持つオブジェクトの実際の値ではなく、null 値をエクスポートする 送信同期規則を実装します。 このオプションは、15 を超える値を持つオブジェクトの証明書値を Microsoft Entra ID にエクスポートする必要がない場合に適しています。 この同期規則を実装する方法の詳細については、次のセクション userCertificate 属性のエクスポートを制限する同期規則の実装を参照してください。
- 組織で使用されなくなった値を削除して、オンプレミス AD オブジェクトの証明書の値の数を減らします (15 以下)。 これは、有効期限が切れている証明書または未使用の証明書が属性の肥大化の原因になっている場合に適しています。 Remove-ADSyncToolsExpiredCertificatesコマンドレットを使用すると、オンプレミス AD で期限切れの証明書を検索、バックアップ、削除できます。 証明書を削除する前に、組織内のパブリックKey-Infrastructure 管理者に確認することをお勧めします。
- userCertificate 属性が Microsoft Entra ID にエクスポートされないように Microsoft Entra Connect を構成します。 一般に、この属性は特定のシナリオを有効にするために Microsoft Online Services によって使用される可能性があるため、このオプションはお勧めしません。 具体的には次のとおりです。

    - User オブジェクトの userCertificate 属性は、メッセージの署名と暗号化のために Exchange Online および Outlook クライアントによって使用されます。 この機能の詳細については、メッセージ署名と暗号化については、S/MIME 記事を参照してください。
    - Computer オブジェクトの userCertificate 属性は、Windows 10 のオンプレミス ドメイン参加済みデバイスが Microsoft Entra ID に接続できるようにするために、Microsoft Entra ID によって使用されます。 この機能の詳細については、「[Windows 10 エクスペリエンス](https://learn.microsoft.com/ja-jp/entra/identity/devices/hybrid-join-plan)用にドメイン参加済みデバイスを Microsoft Entra ID に接続する」の記事を参照してください。

### userCertificate 属性のエクスポートを制限する同期規則の実装

userCertificate 属性によって発生する LargeObject エラーを解決するには、15 を超える証明書値がオブジェクトの実際の値ではなく、null 値をエクスポートする送信同期規則を Microsoft Entra Connect に実装できます。 このセクションでは、ユーザー オブジェクトの同期規則 実装するために必要な手順について説明します。 この手順は、**Contact** および **Computer** オブジェクトに合わせて調整できます。

重要

null 値をエクスポートすると、以前に Microsoft Entra ID に正常にエクスポートされた証明書の値が削除されます。

手順は次のように要約できます。

1. 同期スケジューラを無効にし、進行中の同期がないことを確認します。
2. userCertificate 属性の既存の送信同期規則を見つけます。
3. 必要な送信同期規則を作成します。
4. LargeObject エラーが発生している既存のオブジェクトに対する新しい同期規則を確認します。
5. LargeObject エラーが発生した残りのオブジェクトに新しい同期規則を適用します。
6. Microsoft Entra ID へのエクスポートを待機している予期しない変更がないことを確認します。
7. 変更を Microsoft Entra ID にエクスポートします。
8. 同期スケジューラを再度有効にします。

#### 手順 1: 同期スケジューラを無効にし、進行中の同期がないことを確認する

意図しない変更が Microsoft Entra ID にエクスポートされないように、新しい同期規則の実装中に同期が行われないようにします。 組み込みの同期スケジューラを無効にするには:

1. Microsoft Entra Connect サーバーで PowerShell セッションを開始します。
2. コマンドレットを実行してスケジュールされた同期を無効にする: `Set-ADSyncScheduler -SyncCycleEnabled $false`

手記

上記の手順は、組み込みのスケジューラを使用する Microsoft Entra Connect の新しいバージョン (1.1.xxx.x) にのみ適用されます。 Windows タスク スケジューラを使用する古いバージョン (1.0.xxx.x) の Microsoft Entra Connect を使用している場合、または独自のカスタム スケジューラ (一般的ではない) を使用して定期的な同期をトリガーする場合は、それに応じて無効にする必要があります。

1. STARTメニューから同期サービスに移動して、**Synchronization Service Manager** を起動します。
2. [**操作**] タブに移動し、ステータスが *「進行中」* の操作がないことを確認します。

#### 手順 2: userCertificate 属性の既存の送信同期規則を見つける

User オブジェクトの userCertificate 属性を Microsoft Entra ID にエクスポートするように有効にされ、構成されている既存の同期規則が存在する必要があります。 この同期規則を見つけて、その 優先順位 やスコープ フィルター の構成 を確認します。

1. 「スタート」メニューから同期規則エディターを開き、「**同期規則エディター**」を起動します。
2. 次の値を使用して検索フィルターを構成します。

    | 属性 | 価値 |
    | --- | --- |
    | 通信方向 | **アウトバウンド** |
    | MV オブジェクトの種類 | **人物** |
    | コネクタ | *Microsoft Entra コネクタの名前* |
    | コネクタ オブジェクトの種類 | **利用者** |
    | MV 属性 | **userCertificate** |
3. Microsoft Entra コネクタで OOB (標準) の同期ルールを使用して、User オブジェクトの userCertificate 属性をエクスポートする場合は、「*Out to Microsoft Entra ID – User ExchangeOnline*」ルールに戻す必要があります。
4. この同期規則の **優先順位** 値をメモしておきます。
5. 同期規則を選択し、[編集] を選択します。
6. [予約ルールの確認の編集] ポップアップ ダイアログ で、[なし] 選択します。 (この同期規則に変更を加えるつもりはありません。
7. 編集画面で、[スコープ フィルターの ] タブを選択します。
8. スコープ フィルターの構成をメモしておきます。 OOB 同期規則を使用している場合、**2 つの句を含む 1 つのスコープ フィルター グループ**が存在する必要があります。これには、以下が含まれます。

    | 属性 | オペレーター | 価値 |
    | --- | --- | --- |
    | ソースオブジェクトタイプ | EQUAL | 利用者 |
    | cloudMastered | NOTEQUAL | 正しい |

#### 手順 3: 必要な送信同期規則を作成する

新しい同期規則には、同じ**スコープ フィルター**と、既存の同期規則よりも**高い優先順位**が設定されている必要があります。 これにより、新しい同期規則が既存の同期規則と同じオブジェクト セットに適用され、userCertificate 属性の既存の同期規則がオーバーライドされます。 同期規則を作成するには:

1. 同期規則エディターで、[新しい規則 の追加] ボタン 選択します。
2. [**説明] タブ**で、次の構成を指定します。

    | 属性 | 価値 | 細部 |
    | --- | --- | --- |
    | 名前 | *名前* を指定する | 例: *"Out to Microsoft Entra ID – Custom override for userCertificate"* |
    | 説明 | *説明を入力* | たとえば、「userCertificate 属性に 15 を超える値がある場合は、NULL をエクスポートします」と |
    | 接続システム | *「Microsoft Entra Connector」を選択します* |  |
    | 接続システム オブジェクトの種類 | **利用者** |  |
    | メタバース オブジェクト型 | **person** |  |
    | リンクの種類 | **接続** |  |
    | 優先順位 | *1 から 99 までの数値を選択* | 選択した数は、既存の同期規則では使用しないでください。また、既存の同期規則よりも値が小さい (したがって、優先順位が高い) 必要があります。 |
3. **スコープ フィルター** タブに移動し、既存の同期規則で使用されているのと同じスコープ フィルターを実装します。
4. **結合規則** タブをスキップします。
5. [**変換**] タブに移動し、次の構成を使用して新しい変換を追加します。

    | 属性 | 価値 |
    | --- | --- |
    | フローの種類 | **式** |
    | ターゲット属性 | **userCertificate** |
    | ソース属性 | *次の式の*を使用します: `IIF(IsNullOrEmpty([userCertificate]), NULL, IIF((Count([userCertificate])> 15),AuthoritativeNull,[userCertificate]))` |
6. **[** の追加] ボタンを選択して同期規則を作成します。

#### 手順 4: LargeObject エラーが発生した既存のオブジェクトに対する新しい同期規則を確認する

これは、他のオブジェクトに適用する前に、LargeObject エラーが発生した既存の AD オブジェクトで作成された同期規則が正しく動作していることを確認するためです。

1. Synchronization Service Manager の [**操作**] タブに移動します。
2. 最新の Microsoft Entra へのエクスポート操作を選択し、LargeObject エラーのあるオブジェクトの 1 つを選択します。
3. [コネクタ スペース オブジェクトのプロパティ] ポップアップ画面で、[**プレビュー**] ボタンを選択します。
4. [プレビュー] ポップアップ画面で、[完全同期]  を選択し、[コミット プレビュー] を選択します。
5. [プレビュー] 画面と [コネクタ スペース オブジェクトのプロパティ] 画面を閉じます。
6. Synchronization Service Manager の [**コネクタ**] タブに移動します。
7. **[Microsoft Entra ID]** コネクタを右クリックし、**[実行...]** を選択します。
8. [コネクタの実行] ポップアップで、**[エクスポート** ステップ] を選択し、**[OK**] を選択します。
9. Microsoft Entra ID へのエクスポートが完了するのを待ち、この特定のオブジェクトに LargeObject エラーが発生していないことを確認します。

#### 手順 5: LargeObject エラーが発生した残りのオブジェクトに新しい同期規則を適用する

同期規則が追加されたら、AD コネクタで完全同期手順を実行する必要があります。

1. Synchronization Service Manager の [**コネクタ**] タブに移動します。
2. **AD** コネクタを右クリックし、「**実行...**」を選択します。
3. [コネクタの実行] ポップアップで、完全同期 **ステップ** を選択し、[OK] を選択します。
4. 完全同期の手順が完了するまで待ちます。
5. 複数の AD コネクタがある場合は、残りの AD コネクタに対して上記の手順を繰り返します。 通常、複数のオンプレミス ディレクトリがある場合は、複数のコネクタが必要です。

#### 手順 6: Microsoft Entra ID へのエクスポートを待機している予期しない変更がないことを確認する

1. Synchronization Service Manager の [**コネクタ**] タブに移動します。
2. **[Microsoft Entra ID]** コネクタを右クリックし、**[コネクタ スペースの検索]** を選択します。
3. コネクタスペース検索のポップアップで、次の手順を実行します。
    1. [スコープ] を **[Pending Export](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/保留中のエクスポート)** に設定します。
    2. 3 つのチェックボックスすべてをオンにしてください。の追加、の変更、の削除を含みます。
    3. [**検索**] ボタンを選択すると、変更が Microsoft Entra ID にエクスポートされるのを待っているすべてのオブジェクトが返されます。
    4. 予期しない変更がないことを確認します。 特定のオブジェクトの変更を調べるには、オブジェクトをダブルクリックします。

#### 手順 7: 変更を Microsoft Entra ID にエクスポートする

変更を Microsoft Entra ID にエクスポートするには:

1. Synchronization Service Manager の [**コネクタ**] タブに移動します。
2. **[Microsoft Entra ID]** コネクタを右クリックし、**[実行...]** を選択します。
3. [コネクタの実行] ポップアップで、**[エクスポート** ステップ] を選択し、**[OK**] を選択します。
4. Microsoft Entra ID へのエクスポートが完了するのを待ち、LargeObject エラーがそれ以上ないことを確認します。

#### 手順 8: 同期スケジューラを再度有効にする

問題が解決したら、組み込みの同期スケジューラを再度有効にします。

1. PowerShell セッションを開始します。
2. コマンドレットを実行してスケジュールされた同期を再度有効にする: `Set-ADSyncScheduler -SyncCycleEnabled $true`

手記

上記の手順は、組み込みのスケジューラを使用する Microsoft Entra Connect の新しいバージョン (1.1.xxx.x) にのみ適用されます。 Windows タスク スケジューラを使用する古いバージョン (1.0.xxx.x) の Microsoft Entra Connect を使用している場合、または独自のカスタム スケジューラ (一般的ではない) を使用して定期的な同期をトリガーする場合は、それに応じて無効にする必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-object-not-syncing"} -->
## Microsoft Entra ID と同期していないオブジェクトのトラブルシューティングを行う - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-object-not-syncing
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra ID と同期していないオブジェクトのトラブルシューティングを行います。

オブジェクトが Microsoft Entra ID と想定どおりに同期していない場合は、いくつかの理由が考えられます。 Microsoft Entra ID からエラーメールを受け取った場合、または Microsoft Entra Connect Health でエラーが表示された場合は、「[同期中のエラーのトラブルシューティング」](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sync-errors) 読んでください。 ただし、オブジェクトが Microsoft Entra ID にない問題のトラブルシューティングを行う場合は、この記事が役立ちます。 ここでは、オンプレミス コンポーネントの Microsoft Entra Connect 同期でエラーを見つける方法について説明します。

重要

バージョン 1.1.749.0 以降の Microsoft Entra Connect の展開では、ウィザードの [トラブルシューティング タスク](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-objectsync) を使用して、オブジェクト同期の問題をトラブルシューティングします。

### 同期プロセス

同期の問題を調査する前に、Microsoft Entra Connect の同期プロセスを理解しましょう。

Microsoft Entra Connect 同期プロセス の 図

#### **用語**

- **CS:** コネクタ スペース。データベース内のテーブル
- **MV:** Metaverse(データベース内のテーブル)

#### **同期の手順**

同期プロセスには、次の手順が含まれます。

1. **AD からのインポート: Active Directory オブジェクト** Active Directory CS に取り込まれます。
2. **Microsoft Entra ID からインポートする:** Microsoft Entra オブジェクトが Microsoft Entra CS に取り込まれます。
3. **同期:** 受信同期規則と送信同期規則は、優先順位の低い順に実行されます。 同期規則を表示するには、デスクトップ アプリケーションから同期規則エディターに移動します。 受信同期規則は、CS から MV にデータを取り込みます。 送信同期規則は、MV から CS にデータを移動します。
4. **AD へのエクスポート:** 同期後、オブジェクトは Active Directory CS から Active Directory にエクスポートされます。
5. **Microsoft Entra ID へのエクスポート:** 同期後、オブジェクトは Microsoft Entra CS から Microsoft Entra ID にエクスポートされます。

### トラブルシューティング

エラーを見つけるには、次の順序で、いくつかの異なる場所を確認します。

1. インポートと同期中に同期エンジンによって特定されたエラーを探す場合は操作ログ。
2. 不足しているオブジェクトと同期エラーを探す場合はコネクタ スペース。
3. メタバースは、データ関連の問題を見つけるための です。

これらの手順を開始する前に、[Synchronization Service Manager](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui) を起動します。

### オペレーションズ

Synchronization Service Manager の [**操作**] タブで、トラブルシューティングを開始する必要があります。 このタブには、最新の操作の結果が表示されます。

[Image: [操作] タブが選択されている Synchronization Service Manager のスクリーンショット]

[**操作**] タブの上半分には、すべての実行が時系列で表示されます。 既定では、操作ログには過去 7 日間の情報が保持されますが、この設定は [スケジューラ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-scheduler)で変更できます。 **success** の状態を示していない実行を探します。 ヘッダーを選択することで、並べ替えを変更できます。

**状態** 列には、最も重要な情報が含まれており、実行に関する最も重大な問題が表示されます。 調査の優先順位の順に最も一般的な状態の簡単な概要を次に示します (\*は、考えられるいくつかのエラー文字列を示します)。

| 地位 | コメント |
| --- | --- |
| stopped-\* | 実行を完了できませんでした。 これは、たとえば、リモート システムがダウンしていて、接続できない場合に発生する可能性があります。 |
| stopped-error-limit | 5,000 を超えるエラーがあります。 多数のエラーが発生したため、実行は自動的に停止されました。 |
| completed-\*-errors | 実行は完了しましたが、調査する必要があるエラー (5,000 未満) があります。 |
| completed-\*-warnings | 実行は完了しましたが、一部のデータが予期した状態ではありません。 エラーが発生した場合、このメッセージは症状にすぎません。 エラーに対処するまで、警告を調査しないでください。 |
| 成功 | 問題はありません。 |

行を選択すると、**操作** タブの下部が更新され、その実行の詳細が表示されます。 この領域の左端には、**ステップ #**というタイトルのリストが表示されることがあります。 この一覧は、フォレスト内に複数のドメインがあり、各ドメインが 1 つのステップで表されている場合にのみ表示されます。 ドメイン名は、パーティション見出しの下にあります。 **同期統計** 見出しの下には、処理された変更の数に関する詳細情報が表示されます。 リンクを選択して、変更されたオブジェクトの一覧を取得します。 エラーのあるオブジェクトがある場合、それらのエラーは **同期エラー** 見出しの下に表示されます。

#### [操作] タブのエラー

エラーがある場合、Synchronization Service Manager では、エラーのオブジェクトとエラー自体の両方が、詳細情報を提供するリンクとして表示されます。

[Image: Synchronization Service Manager] のエラーのスクリーンショット まず、エラー文字列を選択します。 (前の図では、エラー文字列は **sync-rule-error-function-triggered** です)。まず、オブジェクトの概要が表示されます。 実際のエラーを表示するには、スタック トレース選択します。 このトレースは、エラーのデバッグ レベルの情報を提供します。

**[コール スタック情報]** ボックスを右クリックして **[すべて選択]** を選択し、**[コピー]** を選択します。 次に、スタックをコピーし、メモ帳などのお気に入りのエディターでエラーを確認します。

エラーが SyncRulesEngineの場合、呼び出し履歴情報には、最初にオブジェクトのすべての属性が一覧表示されます。 InnerException =見出しが表示されるまで下にスクロールします。

[Image: 同期サービス マネージャーのスクリーンショット。InnerException =&gt;] という見出しの下にエラー情報が表示されています

見出しの後の行にエラーが表示されます。 前の図では、エラーは Fabrikam によって作成されたカスタム同期規則に由来しています。

エラーによって十分な情報が得られない場合は、データ自体を確認します。 オブジェクト識別子を含むリンクを選択し、インポートされた コネクタ スペースのオブジェクトのトラブルシューティングを続行します。

### コネクタ スペース オブジェクトのプロパティ

[**操作**] タブにエラーが表示されない場合は、Active Directory からメタバースから Microsoft Entra ID までのコネクタ スペース オブジェクトに従います。 このパスでは、問題がどこにあるかを見つける必要があります。

#### CS でオブジェクトを検索する

Synchronization Service Manager で、**コネクタ**を選択し、Active Directory コネクタを選択し、**コネクタ スペースを検索**を選択します。

[スコープ] ボックスで、CN 属性で検索する場合 RDN を選択するか、distinguishedName 属性で検索する場合は DN またはアンカー選択します。 値を入力して、**を検索し、**を選択します。

コネクタ スペース検索のスクリーンショット

探しているオブジェクトが見つからない場合は、ドメインベースのフィルター処理 または OU ベースのフィルター処理でフィルター処理されている可能性があります。 フィルター処理が期待どおりに構成されていることを確認するには、「Microsoft Entra Connect Sync: フィルター処理を構成する」参照してください。

Microsoft Entra コネクタを選択すると、別の便利な検索を実行できます。 [**スコープ**] ボックスで、[**保留中のインポート**] を選択し、[**追加**] チェック ボックスをオンにします。 この検索では、オンプレミス オブジェクトに関連付けることができない Microsoft Entra ID 内のすべての同期されたオブジェクトが表示されます。

[Image: コネクタ スペース検索の孤立オブジェクトのスクリーンショット]

これらのオブジェクトは、別の同期エンジンまたは別のフィルター構成の同期エンジンによって作成されました。 これらの孤立したオブジェクトは管理されなくなりました。 この一覧を確認し、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview) コマンドレットを使用して、これらのオブジェクトを削除することを検討してください。

#### CS インポート

CS オブジェクトを開くと、上部にいくつかのタブがあります。 **インポート** タブには、インポート後にステージングされるデータが表示されます。

[Image: [コネクタ スペース オブジェクトのプロパティ] ウィンドウのスクリーンショット。[インポート] タブが選択]

**古い値** 列には、現在 Connect に格納されているものが表示されます。 **[新しい値]** 列には、ソース システムから受信されたものが表示され、まだ適用されていません。 オブジェクトにエラーがある場合、変更は処理されません。

**同期エラー** タブは、オブジェクトに問題がある場合にのみ、**コネクタ スペース オブジェクトのプロパティ** ウィンドウに表示されます。 詳細については、操作 タブで、同期エラーのトラブルシューティング方法を 確認してください。

[コネクタ スペース オブジェクトのプロパティ] ウィンドウの [同期エラー] タブの [Image: スクリーンショット]

#### CS 系列

**[コネクタ スペース オブジェクトのプロパティ]** ウィンドウの **[系列]** タブには、コネクタ スペース オブジェクトとメタバース オブジェクトの関係が示されます。 コネクタが最後に接続されたシステムからの変更をインポートした日時と、メタバース内のデータを設定するために適用されたルールを確認できます。

[Image: コネクタ スペースオブジェクトのプロパティウィンドウにある「系譜」タブを示すスクリーンショット]

前の図で、**[アクション]** 列には、**[プロビジョニング]** アクションの受信同期規則が表示されています。 これは、このコネクタ スペース オブジェクトが存在する限り、メタバース オブジェクトが残っていることを示します。 同期規則の一覧に、**プロビジョニング** アクションを含む送信同期規則が表示されている場合、メタバース オブジェクトが削除されると、このオブジェクトが削除されます。

[Image: [コネクタ スペース オブジェクトのプロパティ] ウィンドウの [系列] タブの系列ウィンドウを示すスクリーンショット]

前の図では、**PasswordSync** 列で、1つの同期規則の値が **True**であるため、受信コネクタスペースからパスワードに変更が加えられることがわかります。 このパスワードは、送信規則を通じて Microsoft Entra ID に送信されます。

系列 タブから、メタバースオブジェクトプロパティ選択してメタバースにアクセスできます。

#### プレビュー

[**コネクタ スペース オブジェクトのプロパティ]** ウィンドウの左下隅には、[**プレビュー**] ボタンがあります。 このボタンを選択すると、**プレビュー** ページが開き、1 つのオブジェクトを同期できます。 このページは、カスタム同期規則のトラブルシューティングを行い、1 つのオブジェクトに対する変更の影響を確認する場合に便利です。 **完全同期** または **差分同期**を選択できます。**[プレビュー**の生成]を選択することもできます。これは、メモリ内の変更のみを保持します。 または **コミット プレビュー**を選択すると、メタバースが更新され、すべての変更がターゲット コネクタ スペースにステージングされます。

[Image: プレビュー ページのスクリーンショットで、[プレビューの開始] が選択されています]

プレビューでは、オブジェクトを検査し、特定の属性フローに適用されたルールを確認できます。

[Image: 属性フローのインポート] を示す [プレビュー] ページのスクリーンショット

#### ログ

[**プレビュー**] ボタンの横にある [**ログ**] ボタンを選択して、[**ログ**] ページを開きます。 ここでは、パスワード同期の状態と履歴を確認できます。 詳細については、「[Microsoft Entra Connect Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization)を使用したパスワード ハッシュ同期のトラブルシューティング」を参照してください。

### メタバース オブジェクトのプロパティ

ソース Active Directory コネクタ スペースから検索を開始することをお勧めします。 ただし、メタバースから検索を開始することもできます。

#### MV でオブジェクトを検索する

次の図に示すように、Synchronization Service Manager **Metaverse Search**を選択します。 ユーザーが見つかることがわかっているクエリを作成します。 accountName (sAMAccountName) や userPrincipalNameなどの一般的な属性を検索します。 詳細については、「[Sync Service Manager メタバース検索](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui-mvsearch)」を参照してください。

[Image: [Metaverse Search] タブが選択されている Synchronization Service Manager のスクリーンショット]

**検索結果** ウィンドウで、オブジェクトを選択します。

オブジェクトが見つからなかった場合は、メタバースに到達していません。 引き続き Active Directory コネクタ スペース内のオブジェクトを検索します。 Active Directory コネクタ スペース内にオブジェクトが見つかると、同期エラーが発生し、オブジェクトがメタバースに送信されるのをブロックしている可能性があります。 または、同期規則のスコープ フィルターが適用される場合があります。

#### MV にオブジェクトが見つかりません

オブジェクトが Active Directory CS にあり、MV には存在しない場合は、スコープ フィルターが適用されます。 スコープ フィルターを確認するには、デスクトップ アプリケーション メニューに移動し、同期規則エディターの選択します。 以下のフィルターを調整して、オブジェクトに適用できるルールをフィルター処理します。

[Image: 受信同期規則の検索] を示す同期規則エディターのスクリーンショット

上から一覧の各ルールを表示し、スコープ フィルターを確認します。 次のスコープ フィルターでは、**isCriticalSystemObject** 値が null または FALSE または空の場合、スコープ内にあります。

[Image: 受信同期規則検索でのスコープ フィルターのスクリーンショット]

CS Import 属性リストに移動し、オブジェクトが MV に移動するのをブロックしているフィルターを確認します。 **コネクタ スペースの** 属性の一覧には、null 以外の属性と空でない属性のみが表示されます。 たとえば、**isCriticalSystemObject** が一覧に表示されない場合、この属性の値は null または空です。

#### Microsoft Entra CS にオブジェクトが見つかりません

オブジェクトが Microsoft Entra ID のコネクタ スペースに存在せず、MV に存在する場合は、対応するコネクタ スペースの送信規則のスコープ フィルターを確認します。 MV 属性 条件を満たしていないために、オブジェクトがフィルターで除外されているかどうかを判断します。

送信スコープ フィルターを確認するには、次のフィルターを調整して、オブジェクトに適用できる規則を選択します。 各ルールを表示し、対応する MV 属性 値を確認します。

[Image: 同期規則エディターでの送信同期規則の検索のスクリーンショット]

#### MV 属性

[**属性**] タブでは、値と、それに寄与したコネクタを確認できます。

[Image: [メタバース オブジェクトのプロパティ] ウィンドウのスクリーンショット。[属性] タブが選択]

オブジェクトが同期していない場合は、メタバースの属性の状態について次の質問をします。

- 属性 **cloudFiltered** が存在しており、**True** に設定されていますか。 その場合は、属性ベースのフィルター処理 の手順に従ってフィルター処理されます。
- 属性 **sourceAnchor** 存在しますか? 存在しない場合、アカウント リソース フォレスト トポロジはありますか。 オブジェクトがリンクされたメールボックスとして識別されている場合 (属性 **msExchRecipientTypeDetails** の値が **2**)、**sourceAnchor** は、有効な Active Directory アカウントを含むフォレストによって提供されます。 マスター アカウントがインポートされ、正しく同期されていることを確認します。 マスター アカウントは、オブジェクトにコネクタの中に一覧表示されている必要があります。

#### MV コネクタ

[**コネクタ**] タブには、オブジェクトの表現を持つすべてのコネクタ スペースが表示されます。

[Image: [コネクタ] タブが選択されている [メタバース オブジェクトのプロパティ] ウィンドウのスクリーンショット]

次のものに対するコネクタが必要です。

- ユーザーが表現される各 Active Directory フォレスト。 この表現には、**foreignSecurityPrincipals** オブジェクトと **Contact** オブジェクトを含めることができます。
- Microsoft Entra ID のコネクタ。

Microsoft Entra ID へのコネクタがない場合は、MV 属性 のセクションを確認して、Microsoft Entra ID へのプロビジョニングの条件を確認してください。

**コネクタ** タブから、コネクタ スペース オブジェクトに移動することもできます。 行を選択し、**プロパティ**を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-objectsync"} -->
## Microsoft Entra Connect: オブジェクト同期のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-objectsync
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: トラブルシューティング タスクを使用してオブジェクト同期の問題のトラブルシューティングを行う手順について説明します。

この条項では、トラブルシューティングタスクを使用し、オブジェクト同期の問題のトラブルシューティングを行う手順を示します。 Microsoft Entra Connect でどのようにトラブルシューティングが行われるか確認するには、[短いビデオ](https://aka.ms/AADCTSVideo)をご覧ください。

### トラブルシューティング タスク

Microsoft Entra Connect のバージョン 1.1.749.0 以降のデプロイについては、ウィザードのトラブルシューティング タスクを使用して、オブジェクトの同期問題をトラブルシューティングしてください。 以前のバージョンの場合は、[手動でトラブルシューティングを行う](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-object-not-syncing)ことができます。

#### ウィザードでトラブルシューティング タスクを実行する

トラブルシューティング タスクを実行するには:

1. [管理者として実行] オプションを指定して、Microsoft Entra Connect サーバーで新しい Windows PowerShell セッションを開きます。
2. `Set-ExecutionPolicy RemoteSigned` または `Set-ExecutionPolicy Unrestricted` を実行します。
3. Microsoft Entra接続ウィザードを起動します。
4. **[追加のタスク]**&gt;**[トラブルシューティング]** に移動し、**[次へ]** を選択します。
5. [ **トラブルシューティング** ] ページで、[ **起動** ] を選択して、PowerShell のトラブルシューティング メニューを開始します。
6. メイン メニューで、**[オブジェクト同期のトラブルシューティング]** を選択します。

[Image: Microsoft Entra Connect で強調表示されている [オブジェクト同期のトラブルシューティング] オプションを示すスクリーンショット。]

#### 入力パラメーターのトラブルシューティング

トラブルシューティング タスクには、次の入力パラメーターが必要です。

- **オブジェクトの識別名**: トラブルシューティングが必要なオブジェクトの識別名です。
- **AD コネクタ名**: オブジェクトが存在する Windows Server Active Directory (Windows Server AD) フォレストの名前。
- Microsoft Entra テナントのハイブリッド ID 管理者の資格情報。

[Image: PowerShell ターミナルの背景の資格情報ダイアログを示すスクリーンショット。]

#### トラブルシューティング タスクの結果を理解する

トラブルシューティング タスクでは、次のチェックが実行されます。

- オブジェクトが Microsoft Entra ID に同期されている場合、ユーザー プリンシパル名 (UPN) の不一致を検出します。
- オブジェクトがドメイン フィルタリングによってフィルター処理されるかどうかを確認します。
- オブジェクトが組織単位 (OU) フィルタリングによってフィルター処理されるかどうかを確認します。
- リンクされたメールボックスによってオブジェクトの同期がブロックされているかどうかを確認します。
- オブジェクトが同期対象ではない動的配布グループにあるかどうかを確認します。

この記事の残りの部分では、トラブルシューティング タスクによって返される具体的な結果について説明します。 各ケースでは、タスクによって分析が示され、その後、問題を解決するための推奨の操作が示されます。

### オブジェクトが Microsoft Entra ID に同期される場合に UPN の不一致を検出する

以降のセクションで説明する UPN の不一致の問題を確認します。

#### UPN サフィックスが Microsoft Entra テナントで検証されない

UPN または代替ログイン ID サフィックスが Microsoft Entra テナントで検証されない場合、Microsoft Entra ID は UPN サフィックスを既定のドメイン名 `onmicrosoft.com` に置き換えます。 この問題を解決するには、テナントの確認済みドメインとして UPN サフィックスを追加します。 詳細については、「 [Microsoft Entra ID でのカスタム ドメイン名の管理」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/users/domains-manage)。

[Image: PowerShell での未検証の UPN サフィックス エラーの例を示すスクリーンショット。]

#### Microsoft Entra テナントの DirSync 機能 SynchronizeUpnForManagedUsers が無効になっている

Microsoft Entra テナントの DirSync 機能 SynchronizeUpnForManagedUsers が無効になっている場合、Microsoft Entra ID では、マネージド認証を使用するライセンス ユーザー アカウントの UPN または代替ログイン ID の同期更新は許可されません。 SynchronizeUpnForManagedUsers 機能を有効にする方法については、 [Microsoft Entra Connect Sync サービスの機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-features)を参照してください。

[Image: PowerShell でのマネージド ユーザーの UPN 同期エラーの例を示すスクリーンショット。]

### オブジェクトがドメイン フィルタリングによってフィルター処理されている

以降のセクションで説明するドメイン フィルターの問題を確認します。

#### ドメインが同期するように構成されていない

ドメインが構成されていないため、オブジェクトはスコープ外となります。 次の図の例では、オブジェクトが属しているドメインが同期対象から除外されているため、オブジェクトはスコープ外となっています。

[Image: 同期スコープに含まれていないドメインによって発生したエラーの例を示すスクリーンショット。]

#### ドメインが同期用に構成されているが、実行プロファイルまたは実行ステップがない

ドメインの実行プロファイルまたは実行ステップがないため、オブジェクトはスコープ外となります。 次の図の例では、オブジェクトの属しているドメインにフル インポート実行プロファイルの実行ステップがないため、オブジェクトが同期スコープ外となっています。

[Image: 実行手順が見つからないことが原因で発生したエラーの例を示すスクリーンショット。]

### オブジェクトが OU フィルタリングによってフィルター処理されている

OU フィルタリング構成のために、オブジェクトは同期スコープ外となります。 次の図の例では、オブジェクトは `OU=NoSync,DC=bvtadwbackdc,DC=com` に属しています。 この OU は同期スコープに含まれません。

[Image: PowerShell での OU フィルター処理エラーの例を示すスクリーンショット。]

### リンクされたメールボックスの問題

リンクされたメールボックスは、別の信頼されたアカウント フォレストにある外部のプライマリ アカウントに関連付けられていると想定されています。 プライマリ アカウントが存在しない場合、Microsoft Entra Connect は、Exchange フォレスト内のリンクされたメールボックスに対応するユーザー アカウントを Microsoft Entra テナントに同期しません。

[Image: PowerShell でのリンクされたメールボックス エラーの例を示すスクリーンショット。]

### 動的配布グループの問題

オンプレミスの Windows Server AD と Microsoft Entra ID にはさまざまな違いがあるため、Microsoft Entra Connect は動的配布グループを Microsoft Entra テナントと同期しません。

[Image: PowerShell での動的配布グループ エラーの例を示すスクリーンショット。]

### HTML レポート

トラブルシューティング タスクでは、オブジェクトを分析するだけでなく、オブジェクトについてわかっているすべての情報を含んだ、HTML レポートを生成します。 この HTML レポートを必要に応じてサポート チームと共有し、さらなるトラブルシューティングを行うこともできます。

[Image: PowerShell での HTML レポートの例を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication"} -->
## Microsoft Entra Connect: パススルー認証のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-pass-through-authentication
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、Microsoft Entra パススルー認証のトラブルシューティングの方法について説明します。

この記事は、Microsoft Entra パススルー認証に関する一般的な問題のトラブルシューティング情報を見つける助けとなります。

重要

パススルー認証でユーザーのサインインの問題が発生している場合、フォールバックするためのクラウド専用ハイブリッド ID 管理者アカウントを用意せずに機能を無効にしたり、パススルー認証エージェントをアンインストールしたりしないでください。

### 一般的な問題

#### 機能と認証エージェントの状態を確認する

テナントでパススルー認証機能が引き続き**有効**になっており、認証エージェントの状態が **[非アクティブ**] ではなく **[アクティブ]** と表示されていることを確認します。 状態を確認するには、Microsoft **Entra 管理センター**の [Microsoft Entra Connect](https://entra.microsoft.com/) ブレードに移動します。

[Image: Microsoft Entra 管理センターの [Microsoft Entra Connect] ブレードを示すスクリーンショット。]

[Image: Microsoft Entra 管理センターの [パススルー認証] ブレードを示すスクリーンショット。]

#### ユーザーに表示されるサインインのエラー メッセージ

ユーザーがパススルー認証を使用してサインインできない場合、Microsoft Entra のサインイン画面に、次のようなユーザー向けエラーの 1 つが表示されることがあります。

| エラー | 説明 | 解決策 |
| --- | --- | --- |
| AADSTS80001 | Unable to connect to Active Directory (Active Directory に接続できません) | エージェント サーバーが、パスワードを検証する必要のあるユーザーと同じ AD フォレストのメンバーであり、Active Directory に接続できることを確認します。 |
| AADSTS80002 | A timeout occurred connecting to Active Directory (Active Directory への接続中にタイムアウトが発生しました) | Active Directory が使用可能で、エージェントからの要求に応答していることを確認します。 |
| AADSTS80004 | The username passed to the agent was not valid (エージェントに渡されたユーザー名が無効です) | サインインしようとしているユーザーのユーザー名が正しいことを確認してください。 |
| AADSTS80005 | Validation encountered unpredictable WebException (検証で予測外の WebException が発生しました) | 一時的なエラーです。 要求をやり直してください。 引き続きエラーが発生する場合は、Microsoft サポートに連絡してください。 |
| AADSTS80007 | An error occurred communicating with Active Directory (Active Directory との通信中にエラーが発生しました) | Check the agent logs for more information and verify that Active Directory is operating as expected. (エージェント ログで詳細を確認し、Active Directory が期待通りに動作していることを確認してください。) |

#### ユーザーが無効なユーザー名またはパスワードのエラーを取得する

これは、ユーザーのオンプレミスの UserPrincipalName (UPN) がユーザーのクラウドの UPN と異なる場合に発生する可能性があります。

これが問題であることを確認するには、まずパススルー認証エージェントが正常に機能していることをテストします。

1. テスト アカウントを作成します。
2. エージェント マシンに PowerShell モジュールをインポートします。

    ```powershell
    Import-Module "C:\Program Files\Microsoft Azure AD Connect Authentication Agent\Modules\PassthroughAuthPSModule\PassthroughAuthPSModule.psd1"
    ```
3. Invoke PowerShell コマンドを実行します。

    ```powershell
    Invoke-PassthroughAuthOnPremLogonTroubleshooter 
    ```
4. 資格情報の入力を求められたら、(`https://login.microsoftonline.com`) へのサインインに使用するものと同じユーザー名とパスワードを入力します。

同じユーザー名またはパスワードのエラーが発生した場合、パススルー認証エージェントは正常に動作していて、オンプレミスの UPN がルーティング不可能であることが問題の可能性があることを意味しています。 詳細については、「 [代替ログイン ID の構成」を](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/configuring-alternate-login-id)参照してください。

重要

Microsoft Entra Connect サーバーがドメインに参加していない場合、 [Microsoft Entra Connect: 前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-prerequisites#installation-prerequisites)に記載されている要件により、無効なユーザー名/パスワードの問題が発生します。

#### [Microsoft Entra 管理センター](https://entra.microsoft.com)でのサインインエラーの理由 (Premium ライセンスが必要)

テナントに Microsoft Entra ID P1 または P2 ライセンスが関連付けられている場合は、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)[のサインイン アクティビティ レポート](https://entra.microsoft.com/)を確認することもできます。

[Image: Microsoft Entra 管理センター - サインイン レポートを示すスクリーンショット。]

[Microsoft **Entra admin center**](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/&gt) で **Microsoft Entra ID**https://portal.azure.com/ に移動し、特定のユーザーのサインイン アクティビティをクリックします。 **SIGN-IN ERROR CODE フィールドを**探します。 次の表を使用して、そのフィールドの値を、失敗の理由と解決策にマップします。

| サインイン エラー コード | サインインが失敗した理由 | 解決策 |
| --- | --- | --- |
| 50144 | ユーザーの Active Directory パスワードの有効期限が切れています。 | オンプレミスの Active Directory でユーザーのパスワードをリセットします。 |
| 80001 | 利用できる認証エージェントがありません。 | 認証エージェントをインストールして登録します。 |
| 80002 | 認証エージェントのパスワード検証要求がタイムアウトしました。 | 認証エージェントから Active Directory に到達可能かどうかを調べます。 |
| 80003 | 認証エージェントが無効な応答を受信しました。 | 複数のユーザーにわたって問題が一貫して再現される場合は、Active Directory の構成を確認します。 |
| 80004 | サインイン要求で使用されたユーザー プリンシパル名 (UPN) が正しくありません。 | 正しいユーザー名でサインインするようユーザーに求めます。 |
| 80005 | 認証エージェント: エラーが発生しました。 | 一時的なエラーです。 後で再試行してください。 |
| 80007 | 認証エージェントが Active Directory に接続できません。 | 認証エージェントから Active Directory に到達可能かどうかを調べます。 |
| 80010 | 認証エージェントはパスワードを復号化できません。 | 一貫して問題を再現できる場合は、新しい認証エージェントをインストールして登録します。 また、現在のものはアンインストールします。 |
| 80011 | 認証エージェントは復号化キーを取得できません。 | 一貫して問題を再現できる場合は、新しい認証エージェントをインストールして登録します。 また、現在のものはアンインストールします。 |
| 80014 | 検証要求への応答が最大経過時間を経過した後になされました。 | 認証エージェントがタイムアウトしました。このエラーの詳細を調べるには、エラー コード、相関 ID、タイムスタンプを添えて、サポート チケットを開いてください |

重要

パススルー認証エージェントは、 [Win32 LogonUser API](https://learn.microsoft.com/ja-jp/windows/win32/api/winbase/nf-winbase-logonusera) を呼び出して Active Directory に対してユーザー名とパスワードを検証することで、Microsoft Entra ユーザーを認証します。 その結果、ワークステーションのログオン アクセスを制限するよう Active Directory の "ログオン先" を設定した場合は、"ログオン先" のサーバーの一覧に、パススルー認証エージェントをホストするサーバーも追加する必要があります。 これに失敗すると、Microsoft Entra ID へのサインインからユーザーがブロックされます。

### 認証エージェントのインストールに関する問題

#### 予期しないエラーが発生する

サーバーからエージェント ログを収集し、問題について Microsoft サポートにお問い合わせください。

### 認証エージェントの登録に関する問題

#### ポートがブロックされていたため認証エージェントの登録に失敗した

認証エージェントがインストールされているサーバーが、 [ここに](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-1-check-the-prerequisites)記載されているサービス URL とポートと通信できることを確認します。

#### トークンまたはアカウント認証エラーのため認証エージェントの登録に失敗した

Microsoft Entra Connect またはスタンドアロンの認証エージェントのインストールおよび登録のすべての操作に、クラウド専用ハイブリッド ID 管理者アカウントを使用していることを確認します。 MFA 対応ハイブリッド ID 管理者アカウントには既知の問題があります。回避策として、MFA を一時的にオフにします (操作を完了するためのみ)。

#### 予期しないエラーが発生する

サーバーからエージェント ログを収集し、問題について Microsoft サポートにお問い合わせください。

### 認証エージェントのアンインストールに関する問題

#### Microsoft Entra Connect のアンインストール時に警告メッセージが表示される

テナントでパススルー認証を有効にしている場合に、Microsoft Entra Connect をアンインストールしようとすると、"Users will not be able to sign-in to Microsoft Entra ID unless you have other pass-through authentication agents installed on other servers. (他のサーバーに他のパススルー認証エージェントがインストールされていない場合、ユーザーは Microsoft Entra ID にサインインできなくなります。)" という警告メッセージが表示されます。

Microsoft Entra Connect をアンインストールする前に、セットアップが [高可用性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-4-ensure-high-availability) であることを確認して、ユーザーのサインインを中断しないようにします。

### 機能の有効化に関する問題

#### 使用できる認証エージェントがないため機能の有効化に失敗した

テナントでパススルー認証を有効にするには、アクティブな認証エージェントが少なくとも 1 つ必要です。 Microsoft Entra Connect またはスタンドアロンの認証エージェントのいずれかをインストールすれば、認証エージェントをインストールできます。

#### ポートがブロックされていたため機能の有効化に失敗した

Microsoft Entra Connect がインストールされているサーバーが、 [ここに](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta-quick-start#step-1-check-the-prerequisites)記載されているサービス URL とポートと通信できることを確認します。

#### トークンまたはアカウント認証エラーのため、機能の有効化に失敗した

機能を有効にする場合は、クラウド専用ハイブリッド ID 管理者アカウントを使用するようにします。 Multi-Factor Authentication (MFA) 対応ハイブリッド ID 管理者アカウントには既知の問題があります。回避策として、MFA を一時的にオフにします (操作を完了するためのみ)。

### パススルー認証エージェントのログの収集

発生する問題の種類に応じて、異なる場所にあるパススルー認証エージェントのログを確認する必要があります。

#### Microsoft Entra Connect ログ

インストールに関するエラーについては、`%ProgramData%\AADConnect\trace-*.log` の Microsoft Entra Connect ログを確認してください。

#### 認証エージェントのイベント ログ

認証エージェントに関連するエラーについては、サーバー上でイベント ビューアー アプリケーションを開き、 **アプリケーションログとサービス ログ\Microsoft\AzureAdConnect\AuthenticationAgent\Admin** を確認します。

詳細な分析を取得するには、"セッション" ログを有効にします (このオプションを表示するにはイベント ビューアー アプリケーション内で右クリックします)。 通常の操作中に、このログを有効にして認証エージェントを実行しないでください。これはトラブルシューティングにのみ使用します。 ログの内容は、ログを再度無効にした後にのみ表示されます。

#### 詳細なトレース ログ

ユーザーのサインイン エラーのトラブルシューティングを行うには、%ProgramData%** \Microsoft\Azure AD Connect Authentication Agent\Trace\** でトレース ログを探します。 これらのログには、パススルー認証機能を使用した特定のユーザー サインインが失敗した原因が記録されています。 これらのエラーは、前の表に示したサインインの失敗の理由にもマップされます。 次にログ エントリの例を示します。

```
    AzureADConnectAuthenticationAgentService.exe Error: 0 : Passthrough Authentication request failed. RequestId: 'df63f4a4-68b9-44ae-8d81-6ad2d844d84e'. Reason: '1328'.
        ThreadId=5
        DateTime=xxxx-xx-xxTxx:xx:xx.xxxxxxZ
```

コマンド プロンプトを開き、次のコマンドを実行することで、エラー (前の例では "1328") の詳細を取得できます (注: "1328" は、ログに表示されている実際のエラー番号に置き換える必要があります)。

`Net helpmsg 1328`

[Image: パススルー認証]

#### パススルー認証によるサインインのログ

監査ログが有効になっている場合は、パススルー認証サーバーのセキュリティ ログで追加情報を確認できます。 サインイン要求のクエリを実行する簡単な方法は、次のクエリを使用してセキュリティ ログをフィルター処理することです。

```
    <QueryList>
    <Query Id="0" Path="Security">
    <Select Path="Security">*[EventData[Data[@Name='ProcessName'] and (Data='C:\Program Files\Microsoft Azure AD Connect Authentication Agent\AzureADConnectAuthenticationAgentService.exe')]]</Select>
    </Query>
    </QueryList>
```

### パフォーマンス監視指標

認証エージェントを監視するもう 1 つの方法は、認証エージェントがインストールされている各サーバーで特定のパフォーマンス モニター カウンターを追跡することです。 次のグローバル カウンター (**# PTA 認証**、 **#PTA 失敗した認証** 、 **成功した認証の #PTA**) とエラー カウンター (**# PTA 認証エラー**) を使用します。

[Image: パススルー認証のパフォーマンス モニター カウンター]

重要

パススルー認証では、負荷分散 *ではなく* 、複数の認証エージェントを使用した高可用性が提供されます。 構成によっては、すべての認証エージェントがほぼ*同じ*数の要求を受け取る*わけではありません*。 特定の認証エージェントがトラフィックを一切受け取らないということもあり得ます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization"} -->
## Microsoft Entra Connect 同期を使用したパスワード ハッシュ同期のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-password-hash-synchronization
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: この記事では、パスワード ハッシュ同期の問題のトラブルシューティングを行う方法に関する情報を提供します。

このトピックでは、パスワード ハッシュ同期の問題のトラブルシューティングを行う手順を示します。 パスワードが想定どおりに同期されていない場合は、ユーザーのサブセットまたはすべてのユーザーに対して同期できます。

Microsoft Entra Connect バージョン 1.1.614.0 以降のデプロイについては、ウィザードのトラブルシューティング タスクを使用して、パスワード ハッシュ同期の問題のトラブルシューティングを行ってください。

- パスワードが同期されない問題がある場合は、「パスワードが同期されない: トラブルシューティング タスクによるトラブルシューティング」を参照してください。
- 個々のオブジェクトに問題がある場合は、「1 つのオブジェクトがパスワードを同期していない: トラブルシューティング タスクによるトラブルシューティング」を参照してください。

バージョン 1.1.524.0 以降のデプロイでは、パスワード ハッシュ同期の問題のトラブルシューティングに使用できる診断コマンドレットがあります。

- パスワードが同期されない問題がある場合は、「パスワードが同期されない: 診断コマンドレットによるトラブルシューティング」を参照してください。
- 個々のオブジェクトに問題がある場合は、「1 つのオブジェクトがパスワードを同期していない: 診断コマンドレットによるトラブルシューティング」を参照してください。

以前のバージョンの Microsoft Entra Connect デプロイの場合:

- パスワードが同期されない問題がある場合は、「パスワードが同期されない: 手動によるトラブルシューティング手順」を参照してください。
- 個々のオブジェクトに問題がある場合は、「1 つのオブジェクトがパスワードを同期していない: 手動によるトラブルシューティング手順」を参照してください。

### パスワードが同期されない: トラブルシューティング タスクによるトラブルシューティング

トラブルシューティング タスクを使用して、パスワードが同期されない理由を確認できます。

注

トラブルシューティング タスクは、Microsoft Entra Connect バージョン 1.1.614.0 以降のみで使用できます。

#### トラブルシューティング タスクを実行する

パスワードが同期されない問題のトラブルシューティングを行うには:

1. **[管理者として実行]** オプションを指定して、Microsoft Entra Connect サーバーで新しい Windows PowerShell セッションを開きます。
2. `Set-ExecutionPolicy RemoteSigned` または `Set-ExecutionPolicy Unrestricted` を実行します。
3. Microsoft Entra Connect ウィザードを起動します。
4. 追加タスク ページに移動し、[トラブルシューティング]選択し、[次へ]選択します。
5. [トラブルシューティング] ページ **[** の起動] を選択して、PowerShell のトラブルシューティング メニューを開始します。
6. メイン メニューで、 **[Troubleshoot password hash synchronization]\(パスワード ハッシュ同期のトラブルシューティング\)** を選択します。
7. サブ メニューで、 **[Password hash synchronization does not work at all]\(パスワード ハッシュ同期がまったく機能しない\)** を選択します。

#### トラブルシューティング タスクの結果を理解する

トラブルシューティング タスクでは、次のチェックが実行されます。

- パスワード ハッシュ同期機能が Microsoft Entra テナントに対して有効になっていることを検証します。
- Microsoft Entra Connect サーバーがステージング モードではないことを検証します。
- 既存のオンプレミスの Active Directory コネクタ (既存の Active Directory フォレストに対応) ごとに次の手順を実行します。

    - パスワード ハッシュ同期機能が有効になっていることを検証します。
    - アプリケーション イベント ログでパスワード ハッシュ同期ハートビート イベントを検索します。
    - オンプレミスの Active Directory コネクタの Active Directory ドメインごとに次の手順を実行します。

        - Microsoft Entra Connect サーバーからドメインにアクセスできることを検証します。
        - オンプレミスの Active Directory コネクタで使用される Active Directory Domain Services (AD DS) アカウントに、パスワード ハッシュ同期に必要な適切なユーザー名、パスワード、およびアクセス許可が付与されていることを検証します。

次の図は、単一ドメインのオンプレミス Active Directory トポロジに対するコマンドレットの結果を示しています。

[Image: パスワード ハッシュ同期の診断出力]

このセクションの残りの部分では、タスクによって返される特定の結果と、それに対応する問題について説明します。

##### パスワード ハッシュ同期機能が有効になっていない

Microsoft Entra Connect ウィザードを使用してパスワード ハッシュ同期が有効になっていない場合は、次のエラーが返されます。

[Image: パスワード ハッシュ同期が有効になっていない]

##### Microsoft Entra Connect サーバーがステージング モードである

Microsoft Entra Connect サーバーがステージング モードである場合、パスワード ハッシュ同期は一時的に無効になり、次のエラーが返されます。

[Image: Microsoft Entra Connect サーバーがステージング モードである]

##### パスワード ハッシュ同期のハートビート イベントがない

各オンプレミス Active Directory コネクタは、独自のパスワード ハッシュ同期チャネルを維持します。 チャネルがアクティブで、パスワードの変更が保留中でない場合は、ハートビート イベント (イベント ID 654) が Windows アプリケーション イベント ログに 30 分ごとに記録されます。

チャネルの正常性を確認するために、コマンドレットは過去 3 時間以内に各コネクタからのハートビート イベントをチェックします。 何も見つからない場合は、次のエラーが返されます。

[Image: パスワード ハッシュ同期のハートビート イベントがない]

##### AD DS アカウントに適切なアクセス許可がない

パスワード ハッシュを同期するためにオンプレミスの Active Directory コネクタによって使用される AD DS アカウントに、適切なアクセス許可がない場合は、次のエラーが返されます。

[Image: AD DS アカウントのユーザー名またはパスワードが正しくない場合に返されるエラーを示すスクリーンショット。]

##### AD DS アカウントのユーザー名またはパスワードが正しくない

パスワード ハッシュを同期するためにオンプレミスの Active Directory コネクタによって使用される AD DS アカウントに、誤ったユーザー名またはパスワードが指定さている場合は、次のエラーが返されます。

[Image: 正しくない資格情報]

### 1 つのオブジェクトがパスワードを同期していない: トラブルシューティング タスクを使用して解決してください

トラブルシューティング タスクを使用して、1 つのオブジェクトがパスワードを同期していない理由を特定できます。

注

トラブルシューティング タスクは、Microsoft Entra Connect バージョン 1.1.614.0 以降のみで使用できます。

#### 診断コマンドレットを実行する

特定のユーザー オブジェクトに関する問題のトラブルシューティングを行うには:

1. **[管理者として実行]** オプションを指定して、Microsoft Entra Connect サーバーで新しい Windows PowerShell セッションを開きます。
2. `Set-ExecutionPolicy RemoteSigned` または `Set-ExecutionPolicy Unrestricted` を実行します。
3. Microsoft Entra Connect ウィザードを起動します。
4. 追加タスク ページに移動し、[トラブルシューティング]選択し、[次へ]選択します。
5. [トラブルシューティング] ページ **[** の起動] を選択して、PowerShell のトラブルシューティング メニューを開始します。
6. メイン メニューで、 **[Troubleshoot password hash synchronization]\(パスワード ハッシュ同期のトラブルシューティング\)** を選択します。
7. サブメニューで、[**パスワードが特定のユーザー アカウントの**に同期されない] を選択します。

#### トラブルシューティング タスクの結果を理解する

トラブルシューティング タスクでは、次のチェックが実行されます。

- Active Directory コネクタ スペース、メタバース、および Microsoft Entra コネクタ スペースで、Active Directory オブジェクトの状態を調べます。
- パスワード ハッシュ同期が有効で、Active Directory オブジェクトに適用されている同期規則があることを検証します。
- オブジェクトに対するパスワード同期の前回の試行結果を取得および表示しようとします。

次の図は、1 つのオブジェクトに対するパスワード ハッシュ同期のトラブルシューティングを行ったときの、コマンドレットの結果を示しています。

[Image: パスワード ハッシュ同期の診断出力 - 1 つのオブジェクト]

このセクションの残りの部分では、コマンドレットによって返される具体的な結果と、それに対応する問題について説明します。

##### Active Directory オブジェクトが Microsoft Entra ID にエクスポートされない

Microsoft Entra テナントに対応するオブジェクトがないため、このオンプレミス Active Directory アカウントのパスワード ハッシュ同期が失敗します。 次のエラーが返されます。

[Image: Microsoft Entra オブジェクトがない]

##### ユーザーに一時パスワードがある

以前のバージョンの Microsoft Entra Connect では、一時パスワードと Microsoft Entra ID の同期はサポートされていませんでした。 オンプレミスの Active Directory ユーザーで **[Change password at next logon]\(次回ログオン時にパスワード変更が必要\)** オプションが設定されている場合、パスワードは一時的なものと見なされます。 これらの以前のバージョンでは、次のエラーが返されます。

一時パスワードがエクスポートされない

一時パスワードの同期を有効にするには、Microsoft Entra Connect バージョン 2.0.3.0 以降がインストールされていて、 [ForcePasswordChangeOnLogon](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-password-hash-synchronization#synchronizing-temporary-passwords-and-force-password-change-on-next-logon) 機能が有効になっている必要があります。

##### パスワード同期の前回の試行結果を使用できない

既定では、Microsoft Entra Connect では、パスワード ハッシュ同期の試行結果が 7 日間保存されます。 選択した Active Directory オブジェクトについて使用できる結果がない場合は、次の警告が返されます。

[Image: 1 つのオブジェクトの診断出力 - パスワード同期履歴がない]

### パスワードが同期されない: 診断コマンドレットによるトラブルシューティング

パスワードが同期されない理由を確認するには、`Invoke-ADSyncDiagnostics` コマンドレットを使用できます。

注

`Invoke-ADSyncDiagnostics` コマンドレットは、Microsoft Entra Connect バージョン 1.1.524.0 以降のみで使用できます。

#### 診断コマンドレットを実行する

パスワードが同期されない問題のトラブルシューティングを行うには:

1. **[管理者として実行]** オプションを指定して、Microsoft Entra Connect サーバーで新しい Windows PowerShell セッションを開きます。
2. `Set-ExecutionPolicy RemoteSigned` または `Set-ExecutionPolicy Unrestricted` を実行します。
3. `Import-Module ADSyncDiagnostics` を実行します。
4. `Invoke-ADSyncDiagnostics -PasswordSync` を実行します。

### 1 つのオブジェクトがパスワードを同期していない: 診断コマンドレットを使用したトラブルシューティング

`Invoke-ADSyncDiagnostics` コマンドレットを使用して、1 つのオブジェクトがパスワードを同期していない理由を特定できます。

注

`Invoke-ADSyncDiagnostics` コマンドレットは、Microsoft Entra Connect バージョン 1.1.524.0 以降のみで使用できます。

#### 診断コマンドレットを実行する

ユーザーのパスワードが同期されない問題のトラブルシューティングを行うには:

1. **[管理者として実行]** オプションを指定して、Microsoft Entra Connect サーバーで新しい Windows PowerShell セッションを開きます。
2. `Set-ExecutionPolicy RemoteSigned` または `Set-ExecutionPolicy Unrestricted` を実行します。
3. `Import-Module ADSyncDiagnostics` を実行します。
4. ユーザーの `DistinguishedName` (DN) を取得し、次のコマンドレットを実行します。

    ```
    Invoke-ADSyncDiagnostics -PasswordSync -ADConnectorName <Name-of-AD-Connector> -DistinguishedName <DistinguishedName-of-AD-object>
    ```

    例えば次が挙げられます。

    ```powershell
    Invoke-ADSyncDiagnostics -PasswordSync -ADConnectorName "contoso.com" -DistinguishedName "CN=TestUserCN=Users,DC=contoso,DC=com"
    ```

### パスワードが同期されない: 手動によるトラブルシューティング手順

パスワードが同期されない理由を確認するには、次の手順に従います。

1. Connect サーバーは[ステージング モード](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-staging-server)ですか。 ステージング モードのサーバーは、パスワードを同期しません。
2. 「パスワード同期設定の状態の取得」セクションにあるスクリプトを実行してください。 これにより、パスワード同期の構成の概要が示されます。

    [Image: パスワード同期設定からの PowerShell スクリプトの出力]
3. この機能が Microsoft Entra ID で有効になっていない場合、または同期チャネルの状態が有効になっていない場合は、接続インストール ウィザードを実行します。 **[同期オプションのカスタマイズ]** を選択し、パスワード同期の選択を解除します。この変更により、一時的に機能が無効になります。 その後、もう一度ウィザードを実行し、パスワード同期を再度有効にします。スクリプトを再実行して、構成が正しいことを確認します。
4. イベント ログでエラーを調べます。 問題を示す次のイベントを探します。

    ソース: "ディレクトリ同期 " ID: 0、611、652、655

    これらのイベントが表示されている場合は、接続に問題があります。 イベント ログ メッセージに、問題のあるフォレストの情報が含まれています。
5. ハートビートが表示されない場合、または他に何も動作していない場合は、「すべてのパスワードの完全同期の開始」を実行します。 スクリプトは 1 回のみ実行してください。
6. 「パスワードを同期していない 1 つのオブジェクトのトラブルシューティング」セクションを参照してください。

#### 接続の問題と AD DS のアクセス許可

Microsoft Entra Connect が Microsoft Entra ID に接続できるかどうかを確認します。

AD DS コネクタ アカウントには、すべてのドメインでパスワード ハッシュを読み取るために必要なアクセス許可が必要です。

Express 設定を使用して Microsoft Entra Connect をインストールした場合、必要なアクセス許可が自動的に構成されました。

カスタム インストールを使用した場合は、次の手順に従ってアクセス許可を手動で割り当てます。

1. Active Directory コネクタで使用されるアカウントを検索するには、**Synchronization Service Manager** を起動します。
2. **[コネクタ]** に移動し、トラブルシューティングを行うオンプレミスの Active Directory フォレストを検索します。
3. コネクタを選択し、[プロパティ]選択します。
4. **[Active Directory フォレストに接続]** を選択します。

    [Image: Active Directory コネクタで使用されるアカウント] ユーザー名と、アカウントがあるドメインをメモしておきます。
5. **Active Directory ユーザーとコンピューター**を起動し、先ほど見つけたアカウントに、フォレスト内のすべてのドメインのルートに次のアクセス許可が設定されていることを確認します。

    - ディレクトリの変更のレプリケート
    - ディレクトリの変更をすべてにレプリケート
6. ドメイン コントローラーは、Microsoft Entra Connect からアクセスできますか? Connect サーバーですべてのドメイン コントローラーに接続できない場合は、 **優先ドメイン コントローラーのみを使用**するように構成します。

    [Image: Active Directory コネクタで使用されるドメイン コントローラー]
7. **Synchronization Service Manager** と **[ディレクトリ パーティションの構成]** に戻ります。
8. [**ディレクトリ パーティション**] でドメインを選択し、[**優先ドメイン コントローラーのみを使用**] チェック ボックスをオンにして、それから [**構成**] を選択します。
9. 一覧で、Connect がパスワード同期に使用するドメイン コントローラーを入力します。同じ一覧がインポートとエクスポートにも使用されます。 すべてのドメインに対してこの手順を実行します。

注

これらの変更を適用するには、**Microsoft Entra ID Sync** (ADSync) サービスを再起動します。

1. スクリプトによってハートビートがないことが示されたら、「すべてのパスワードの完全同期の開始」にあるスクリプトを実行します。

### 1 つのオブジェクトがパスワードを同期していない: 手動によるトラブルシューティング手順

オブジェクトの状態を確認することで、パスワード ハッシュ同期の問題を簡単に解決できます。

1. **[Active Directory ユーザーとコンピューター]** で、ユーザーを検索し、 **[ユーザーは次回ログオン時にパスワードの変更が必要]** チェック ボックスがオフになっていることを確認します。

    [Image: Active Directory の生産性の高いパスワード]

チェック ボックスがオンになっている場合、ユーザーは、サインインしてパスワードを変更するよう求められます。 一時パスワードは Microsoft Entra ID と同期されません。

1. パスワードが Active Directory で正しく表示されたら、同期エンジンのユーザーをフォローします。 オンプレミスの Active Directory から Microsoft Entra ID にユーザーをフォローすると、オブジェクトにわかりやすいエラーがあるかどうかを確認できます。

    ａ. [Synchronization Service Manager](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-service-manager-ui) を起動します。

    b。 **コネクタ**を選択します。

    c. ユーザーが存在する **Active Directory コネクタ**を選択します。

    d. **[Search Connector Space (コネクタ スペースの検索)]** を選択します。

    e. **[スコープ]** ボックスで、 **[DN or Anchor]\(DN またはアンカー\)** を選択し、トラブルシューティングを行うユーザーの完全な DN を入力します。

    [Image: DN でコネクタ スペース内のユーザーを検索]

    f. 探しているユーザーを見つけて、[プロパティ]選択して、すべての属性を表示します。 ユーザーが検索結果に含まれていない場合は、[フィルター規則](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering) を確認し、変更を適用して確認するために [実行し、ユーザーが「接続」に表示されるようにしてください](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-configure-filtering#apply-and-verify-changes)。

    g. 過去 1 週間のオブジェクトのパスワード同期の詳細を表示するには、[ログ選択します。

    [Image: オブジェクト ログの詳細]

    オブジェクト ログが空の場合、Microsoft Entra Connect は Active Directory からパスワード ハッシュを読み取ることができません。 接続エラーのトラブルシューティングに進みます。 **[成功]** 以外の値が表示される場合は、「パスワード同期ログ」の表をご覧ください。

    h. **[系列]** タブを選択し、 **[PasswordSync]** 列の少なくとも 1 つの同期規則が **True** であることを確認します。 既定の構成では、同期規則の名前は **[In from AD - User AccountEnabled]\(AD からの受信 - ユーザー AccountEnabled)** です。

    [Image: ユーザーに関する系列情報]

    一. [メタバース オブジェクト **プロパティ]** を選択して、ユーザー属性の一覧を表示します。

    [Image: [Metaverse Object Properties](メタバース オブジェクトのプロパティ) のユーザー属性の一覧を示すスクリーンショット。]

    **cloudFiltered** 属性が存在しないことを確認します。 ドメイン属性 (domainFQDN と domainNetBios) に必要な値があることを確認します。

    j. [**コネクタ**] タブを選択します。オンプレミスの Active Directory と Microsoft Entra ID の両方へのコネクタが表示されていることを確認します。

    [Image: メタバース情報]

    k. Microsoft Entra ID を表す行を選択し、**[プロパティ]**、**[系列]** タブの順に選択します。コネクタ スペース オブジェクトでは、**[PasswordSync]** 列のアウトバウンド規則が **True** に設定されている必要があります。 既定の構成では、**[Out to Microsoft Entra ID - User Join]** という名前の同期規則です。

    [Image: [コネクタ スペース オブジェクトのプロパティ] ダイアログ ボックス]

#### パスワード ハッシュ同期ログ

状態列には次の値が入ります。

| ステータス | 説明 |
| --- | --- |
| Success | パスワードが正常に同期されました。 |
| ターゲットによるフィルタリング | パスワードは **[ユーザーは次回ログオン時にパスワードの変更が必要]** に設定されています。 パスワードが同期されていません。 |
| NoTargetConnection | メタバースまたは Microsoft Entra コネクタ スペースにオブジェクトがありません。 |
| ソースコネクタが存在しません | オンプレミスの Active Directory コネクタ スペースにオブジェクトがありません。 |
| ディレクトリにエクスポートされないターゲット | Microsoft Entra コネクタ スペース内のオブジェクトはまだエクスポートされていません。 |
| MigratedCheckDetailsForMoreInfo | ログ エントリはビルド 1.0.9125.0 より前に作成されており、従来の状態で表示されます。 |
| Error | サービスから不明なエラーが返されました。 |
| 未知 | パスワード ハッシュのバッチを処理するときにエラーが発生しました。 |
| 属性が欠落しています | Microsoft Entra Domain Services で必要な特定の属性 (Kerberos ハッシュなど) は使用できません。 |
| RetryRequestedByTarget | Microsoft Entra Domain Services で必要な特定の属性 (Kerberos ハッシュなど) は、以前は使用できませんでした。 ユーザーのパスワード ハッシュの再同期が試行されました。 |

### パスワード ハッシュ同期の Windows イベント ビューアー ログ

パスワード ハッシュ同期機能は、Windows イベント ビューアーで包括的なアプリケーション イベントのセットを生成し、その操作アクティビティの大部分をキャプチャします。

効果的なトラブルシューティングを行うには、アプリケーション ログのサイズを大きくすることを検討してください。 この調整を行わないと、パスワード ハッシュ同期イベントが上書きされる可能性があるため、同期状態の追跡や問題の診断が困難になります。

| イベント ID | 説明 |
| --- | --- |
| 601 | パスワード ハッシュ同期マネージャーが起動しています。 |
| 602 | パスワード ハッシュ同期が停止しています。 |
| 603 | パスワード ハッシュ同期の予期しないエラーが発生しました。 |
| 604 | パスワード ハッシュ同期タスク エラーが発生しました。 |
| 605 | パスワード ハッシュ同期項目が再試行キューに追加されます。 |
| 606 | パスワード ハッシュ同期項目は、再試行キューから削除されます。 |
| 6:07 | パスワード ハッシュ同期を開始できません。 |
| 6:09 | パスワード ハッシュ同期が停止しました。 |
| 610 | パスワード ハッシュ同期を停止できません。 |
| 611 | ドメインのパスワード ハッシュ同期中にエラーが発生しました。 |
| 612 | パスワード ハッシュ同期コンテキストの初期化中にエラーが発生しました。 |
| 613 | ディレクトリの完全同期がまだ完了していないため、パスワード ハッシュ同期エージェントが一時停止しました。 |
| 614 | シャットダウンしない場合は、パスワード ハッシュ同期の開始が呼び出されます。 |
| 615 | パスワード ハッシュ同期ワーカー スレッドの例外が発生しました。 |
| 616 | 優先 DC へのパスワード ハッシュ同期接続に失敗しました。 |
| 617 | フォレストの完全なパスワード ハッシュ同期が開始されました。 |
| 618 | ドメインの完全なパスワード ハッシュ同期が開始されました。 |
| 619 | ドメインのパスワード ハッシュ同期の進行状況を示します。 |
| 620 | パスワード ハッシュ同期オブジェクトの再試行なしが報告されます。 |
| 621 | 完全なパスワード ハッシュ同期の試行に失敗しました。 |
| 6:22 | ドメインの完全なパスワード ハッシュ同期が完了しました。 |
| 6:23 | フォレストの完全なパスワード ハッシュ同期が完了しました。 |
| 650 | パスワード ハッシュ同期バッチの開始。 |
| 651 | パスワード ハッシュ同期バッチの終了。 |
| 652 | パスワード ハッシュ同期操作中にエラーが発生しました。 |
| 653 | パスワード ハッシュ同期 ping の開始。 |
| 654 | パスワード ハッシュ同期 ping の終了。 |
| 655 | パスワード ハッシュ同期 ping 中にエラーが発生しました。 |
| 656 | パスワード ハッシュ同期要求メッセージ。 |
| 657 | パスワード ハッシュ同期応答メッセージ。 |
| 658 | DCaaS 同期イベント ログ メッセージ。 |
| 659 | パスワード ポリシー同期イベント ログ メッセージ。 |
| 660 | パスワード ハッシュ同期の会社機能の自己復旧を開始します。 |
| 661 | パスワード ハッシュ同期の会社機能の自己復旧を終了します。 |
| 662 | ping 操作中にパスワード ハッシュ同期の正常性タスクが失敗しました。 |
| 663 | パスワード ハッシュ同期マネージャーが稼働中です。 |
| 664 | 単一オブジェクト同期タスクに失敗しました。 |
| 665 | ドメインのパスワード ハッシュ同期サイクルの状態を格納できませんでした。 |
| 666 | SQL デッドロックが原因でドメインのパスワード ハッシュ同期に失敗しました。 |
| 667 | MD5 復号化キーの生成に失敗しました。 |
| 668 | PwdLastSet のみが変更されたパスワード ハッシュ同期バッチ内のオブジェクトの数。 |

### トラブルシューティングに役立つスクリプト

#### パスワード同期設定の状態の取得

```powershell
Import-Module ADSync
$connectors = Get-ADSyncConnector
$aadConnectors = $connectors | Where-Object {$_.SubType -eq "Windows Azure Active Directory (Microsoft)"}
$adConnectors = $connectors | Where-Object {$_.ConnectorTypeName -eq "AD"}
if ($aadConnectors -ne $null -and $adConnectors -ne $null)
{
    if ($aadConnectors.Count -eq 1)
    {
        $features = Get-ADSyncAADCompanyFeature
        Write-Host
        Write-Host "Password sync feature enabled in your Azure AD directory: "  $features.PasswordHashSync
        foreach ($adConnector in $adConnectors)
        {
            Write-Host
            Write-Host "Password sync channel status BEGIN ------------------------------------------------------- "
            Write-Host
            Get-ADSyncAADPasswordSyncConfiguration -SourceConnector $adConnector.Name
            Write-Host
            $pingEvents =
                Get-EventLog -LogName "Application" -Source "Directory Synchronization" -InstanceId 654  -After (Get-Date).AddHours(-3) |
                    Where-Object { $_.Message.ToUpperInvariant().Contains($adConnector.Identifier.ToString("D").ToUpperInvariant()) } |
                    Sort-Object { $_.Time } -Descending
            if ($pingEvents -ne $null)
            {
                Write-Host "Latest heart beat event (within last 3 hours). Time " $pingEvents[0].TimeWritten
            }
            else
            {
                Write-Warning "No ping event found within last 3 hours."
            }
            Write-Host
            Write-Host "Password sync channel status END ------------------------------------------------------- "
            Write-Host
        }
    }
    else
    {
        Write-Warning "More than one Azure AD Connectors found. Please update the script to use the appropriate Connector."
    }
}
Write-Host
if ($aadConnectors -eq $null)
{
    Write-Warning "No Azure AD Connector was found."
}
if ($adConnectors -eq $null)
{
    Write-Warning "No AD DS Connector was found."
}
Write-Host
```

##### すべてのパスワードの完全同期の開始

注

このスクリプトは 1 回のみ実行してください。 複数回実行する必要がある場合は、別の問題があります。 問題のトラブルシューティングを行う場合は、Microsoft サポートに連絡してください。

次のスクリプトを使用して、すべてのパスワードの完全同期をトリガーできます。

1. ローカル Active Directory **$adConnector** 値を割り当てる

    `$adConnector = "<CASE SENSITIVE AD CONNECTOR NAME>"`
2. AzureAD **$aadConnector** 値を割り当てる

    `$aadConnector = "<CASE SENSITIVE AAD CONNECTOR NAME>"`
3. AzureAD 同期モジュールをインストールする

    `Import-Module adsync`
4. 新しい [完全パスワード同期の強制] 構成パラメーター オブジェクトを作成する

    `$c = Get-ADSyncConnector -Name $adConnector`
5. 既存のコネクタを次の新しい構成で更新します。 各行を個別に実行する

    ａ. `$p = New-Object Microsoft.IdentityManagement.PowerShell.ObjectModel.ConfigurationParameter "Microsoft.Synchronize.ForceFullPasswordSync", String, ConnectorGlobal, $null,   $null, $null`

    b。 `$p.Value = 1`

    c. `$c.GlobalParameters.Remove($p.Name)`

    d. `$c.GlobalParameters.Add($p)`

    e. `$c = Add-ADSyncConnector -Connector $c`
6. Entra ID Connect を無効にする

    `Set-ADSyncAADPasswordSyncConfiguration -SourceConnector $adConnector -TargetConnector $aadConnector -Enable $false`
7. Entra ID Connect を有効にして完全なパスワード同期を強制する

    `Set-ADSyncAADPasswordSyncConfiguration -SourceConnector $adConnector -TargetConnector $aadConnector -Enable $true`
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-recover-from-localdb-10gb-limit"} -->
## Microsoft Entra Connect: LocalDB の 10 GB の制限の問題から回復する方法 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-recover-from-localdb-10gb-limit
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、LocalDB の 10 GB 制限の問題が発生したときに Microsoft Entra Connect Synchronization Service を回復する方法について説明します。

Microsoft Entra Connect には、ID データを格納するための SQL Server データベースが必要です。 Microsoft Entra Connect と共にインストールされる既定の SQL Server 2019 Express LocalDB を使用するか、所有している完全な SQL を使用することができます。 SQL Server Express には、10 GB のサイズ制限があります。 LocalDB を使用していて、この上限に達すると、Microsoft Entra Connect Synchronization Service は正常に開始または同期できなくなります。 この記事では、回復の手順を説明します。

### 症状

一般的には、次の 2 つの症状があります。

- Microsoft Entra Connect Synchronization Service が**実行中**でも、*"stopped-database-disk-full"* エラーで同期が失敗する。
- Microsoft Entra Connect Synchronization Service が**開始できない**。 このサービスを開始しようとすると、イベント 6323 で失敗し、"*SQL Server のディスク領域が不足しているため、サーバーでエラーが発生しました。* " というエラー メッセージが表示されます。

### 短期的な回復手順

このセクションでは、Microsoft Entra Connect Synchronization Service が操作を再開するために必要な DB 空き領域を増やす手順について説明します。 手順は次のとおりです。

1. Synchronization Service の状態を確認する
2. データベースを縮小する
3. 実行履歴データを削除する
4. 実行履歴データの保有期間を短縮する

#### Synchronization Service の状態を確認する

まず、Synchronization Service がまだ実行中かどうかを確認します。

1. 管理者として Microsoft Entra Connect サーバーにログインします。
2. **[サービス コントロール マネージャー]** に移動します。
3. **Microsoft Entra ID Sync** の状態を確認します。
4. 実行されている場合は、サービスを停止または再起動しないでください。 「データベースを縮小する」の手順をスキップし、「実行履歴データを削除する」の手順に進みます。
5. 実行されていない場合は、サービスを開始してみてください。 サービスが正常に開始した場合は、「データベースを縮小する」の手順をスキップし、「実行履歴データを削除する」の手順に進みます。 そうでない場合は、「データベースを縮小する」の手順に進みます。

#### データベースを縮小する

縮小操作を使用して、Synchronization Service を開始するための十分な DB 空き領域を確保します。 データベース内の空白を削除することによって、DB 空き領域が確保されます。 必ず領域を回復できる保証はないため、この手順はベスト エフォートです。 縮小操作の詳細については、「[データベースの圧縮](https://learn.microsoft.com/ja-jp/sql/relational-databases/databases/shrink-a-database)」という記事を参照してください。

Von Bedeutung

Synchronization Service を実行できる場合は、この手順をスキップしてください。 SQL DB の縮小は、断片化の増加によってパフォーマンスが低下する可能性があるため、お勧めしません。

Microsoft Entra Connect 用に作成されるデータベースの名前は、**ADSync** です。 縮小操作を実行するには、sysadmin またはデータベースの DBO としてログインする必要があります。 Microsoft Entra Connect のインストール中に、以下のアカウントに sysadmin 権限が付与されます。

- ローカル管理者
- Microsoft Entra Connect のインストールを実行するために使用されたユーザー アカウント。
- Microsoft Entra Connect Synchronization Service の操作コンテキストとして使用される Sync Service アカウント。
- インストール中に作成されたローカル グループ ADSyncAdmins。

1. にある **ADSync.mdf** ファイルと `%ProgramFiles%\Microsoft Azure AD Sync\Data` ファイルを安全な場所にコピーして、データベースをバックアップします。
2. 新しい PowerShell セッションを開始します。
3. `%ProgramFiles%\Microsoft SQL Server\110\Tools\Binn` フォルダーに移動します。
4. sysadmin またはデータベースの DBO の資格情報を使用してコマンド  を実行することで、`./SQLCMD.EXE -S "(localdb)\.\ADSync" -U <Username> -P <Password>` ユーティリティを起動します。
5. データベースを縮小するには、sqlcmd プロンプト (`1>`) で「`DBCC Shrinkdatabase(ADSync,1);`」と入力し、次の行に「`GO`」と入力します。
6. 操作が成功した場合は、Synchronization Service をもう一度開始してみます。 Synchronization Service を開始できる場合は、「実行履歴データを削除する」の手順に進みます。 そうでない場合は、サポートに問い合わせてください。

#### 実行履歴データを削除する

既定では、Microsoft Entra Connect は最大 7 日間分の実行履歴データを保持します。 この手順では、Microsoft Entra Connect Synchronization Service が再度同期を開始できるように、実行履歴データを削除して DB 空き領域を増やします。

1. [スタート]、[Synchronization Service] の順に移動して、**Synchronization Service Manager** を起動します。
2. **[操作]** タブに移動します。
3. **[アクション]** で **[Clear Runs] (実行のクリア)** を選択します。
4. **[Clear all runs] (すべての実行をクリア)** または [Clear runs before... &lt;date&gt;] (&lt;日付&gt; より前の実行をクリア) のいずれかのオプションを選択できます。 まずは 2 日を経過した実行履歴データをクリアすることをお勧めします。 DB サイズの問題が引き続き発生する場合は、 **[Clear all runs (すべての実行をクリア)]** オプションを選択してください。

#### 実行履歴データの保有期間を短縮する

この手順は、複数の同期サイクル後に 10 GB 制限の問題が発生する可能性を低減するためのものです。

1. 新しい PowerShell セッションを開きます。
2. `Get-ADSyncScheduler` を実行し、現在の保有期間を指定している PurgeRunHistoryInterval プロパティを書き留めておきます。
3. `Set-ADSyncScheduler -PurgeRunHistoryInterval 2.00:00:00` を実行して、保有期間を 2 日間に設定します。 必要に応じて保有期間を調整します。

### 長期的な解決策 – 完全バージョンの SQL への移行

一般に、この問題は、Microsoft Entra Connect がオンプレミスの Active Directory を Microsoft Entra ID に同期するのに 10 GB というデータベース サイズが十分でなくなったことを示しています。 SQL Server の完全バージョンを使用するよう切り替えることをお勧めします。 既存の Microsoft Entra Connect デプロイの LocalDB を、SQL の完全バージョンのデータベースに直接置き換えることはできません。 代わりに、SQL の完全バージョンを搭載した新しい Microsoft Entra Connect サーバーをデプロイする必要があります。 スウィング移行を実行することをお勧めします。スウィング移行では、新しい Microsoft Entra Connect サーバー (SQL DB を含む) がステージング サーバーとして、既存の Microsoft Entra Connect サーバー (LocalDB を含む) の隣にデプロイされます。

- Microsoft Entra Connect でリモート SQL を構成する方法の手順については、「[Microsoft Entra Connect のカスタム インストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-custom)」を参照してください。
- Microsoft Entra Connect アップグレードのスウィング移行の手順については、「[Microsoft Entra Connect: 旧バージョンから最新バージョンにアップグレードする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-upgrade-previous-version#swing-migration)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-source-anchor"} -->
## Microsoft Entra Connect: インストール時のソース アンカーの問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-source-anchor
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: このトピックでは、インストール時にソース アンカーに関する問題をトラブルシューティングする方法について説明します。

この記事では、インストール中に発生する可能性があるさまざまなソース アンカー関連の問題について説明し、これらの問題を解決する方法を提供します。

### Microsoft Entra ID の無効なソース アンカー

#### カスタム インストール

カスタム インストール中、Microsoft Entra Connect は Microsoft Entra ID からソース アンカー ポリシーを読み取ります。 ポリシーが Microsoft Entra ID に存在する場合、お客様が上書きしない限り、Microsoft Entra Connect によって適用されます。 ウィザードによって、読み取られた属性が通知されます。 さらに、ソース アンカー ポリシーをオーバーライドしようとすると、ウィザードによって警告が表示されます。

この読み取り操作中に、Microsoft Entra ID のソース アンカー ポリシーが予期しない可能性があります。 この場合、Microsoft Entra Connect は使用するソース アンカーを認識せず、手動によるオーバーライドが必要です。[Image: ソース アンカーを手動でオーバーライドする場所を示すスクリーンショット。]

この問題を解決するには、特定の属性を選択してソース アンカーを手動でオーバーライドします。 選択する属性が特定の場合にのみ、このオプションに進みます。 不明な場合は、Microsoft サポートにお問い合わせください。 ソース アンカー ポリシーを変更すると、オンプレミスのユーザーとそれに関連付けられている Azure リソースとの関連付けが解除される可能性があります。[Image: ソース アンカーをオーバーライドする指定した属性を示すスクリーンショット。]

#### 高速インストール

高速インストール中、Microsoft Entra Connect は Microsoft Entra ID からソース アンカー ポリシーを読み取ります。 ポリシーが Microsoft Entra ID に存在する場合、Microsoft Entra Connect は同じポリシーを適用します。 手動オーバーライドのオプションはありません。

この読み取り操作中に、Microsoft Entra ID のソース アンカー ポリシーが予期しない可能性があります。 この場合、Microsoft Entra Connect はソース アンカーの内容を認識しません。[Image: Microsoft Entra ID のソース アンカーが予期しない場合の動作を示すスクリーンショット。]

この問題を解決するには、カスタム モードを使用して再インストールし、特定の属性を選択してソース アンカーを手動でオーバーライドする必要があります。 選択する属性が特定の場合にのみ、このオプションに進みます。 不明な場合は、Microsoft サポートにお問い合わせください。 ソース アンカー ポリシーを変更すると、オンプレミスのユーザーとそれに関連付けられている Azure リソースとの関連付けが解除される可能性があります。

#### 同期エンジンのソース アンカーが無効です

インストール中に、Microsoft Entra Connect が無効なソース アンカーを使用して同期エンジンの構成を試みる可能性があります。 この操作は製品の問題である可能性が最も高く、Microsoft Entra Connect のインストールが失敗します。 この問題が発生した場合は、[Microsoft サポート](https://support.microsoft.com/contactus/)にお問い合わせください。[Image: 予期しない]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-sso"} -->
## Microsoft Entra Connect: シームレス シングル サインオンのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sso
- Service: entra-id / hybrid-connect
- Article date: 2026-09-15
- Summary: このトピックでは、Microsoft Entra のシームレス シングル サインオンのトラブルシューティング方法について説明します

この記事は、Microsoft Entra のシームレス シングル サインオン (シームレス SSO) に関する一般的な問題のトラブルシューティング情報を見つけるうえで役立ちます。

### 既知の問題

- 場合によっては、シームレス SSO の有効化に最大 30 分かかることがあります。
- テナントでシームレス SSO を無効にして再度有効にすると、キャッシュされた Kerberos チケットが期限切れになるまで (通常は 10 時間有効)、シングル サインオン機能は利用できません。
- シームレス SSO が成功した場合、ユーザーは [ **サインイン**したままにする] を選択する機会がありません。
- バージョン 16.0.8730.xxxx 以降の Microsoft 365 Win32 クライアント (Outlook、Word、Excel など) は、非対話型フローを使用してサポートされています。 その他のバージョンはサポートされていません。それらのバージョンでは、ユーザーはパスワードではなく、ユーザー名を入力してサインインします。 OneDrive の場合は、サイレント サインオン エクスペリエンスのために [OneDrive サイレント構成機能](https://techcommunity.microsoft.com/t5/Microsoft-OneDrive-Blog/Previews-for-Silent-Sync-Account-Configuration-and-Bandwidth/ba-p/120894) をアクティブ化する必要があります。
- シームレス SSO は、Firefox のプライベート ブラウズ モードでは動作しません。
- シームレス SSO は、拡張保護モードがオンの場合は Internet Explorer で動作しません。
- Microsoft Edge (レガシ) のサポート終了
- シームレス SSO は、iOS および Android 上のモバイル ブラウザーでは動作しません。
- Active Directory でユーザーが属しているグループ数が多すぎる場合、ユーザーの Kerberos チケットが大きすぎて処理できなくなり、シームレス SSO が失敗する可能性があります。 Microsoft Entra HTTPS 要求のヘッダーは最大サイズが 50 KB です。Cookie など、他の Microsoft Entra アーティファクト (通常、2 から 5 KB) に対応するには、Kerberos チケットのサイズを、制限より小さくする必要があります。 ユーザーのグループ メンバーシップを減らし、再試行することをお勧めします。
- 30 以上の Active Directory フォレストを同期している場合は、Microsoft Entra Connect によるシームレス SSO を有効にすることはできません。 回避策として、テナントで機能を 手動で有効 にすることができます。
- ローカル イントラネット ゾーンではなく、信頼済みサイト ゾーンに Microsoft Entra サービス URL (`https://autologon.microsoftazuread-sso.com`) を追加すると、ユーザーが *サインインできなくなります*。
- シームレス SSO では、Kerberos の AES256\_HMAC\_SHA1、AES128\_HMAC\_SHA1、および RC4\_HMAC\_MD5 暗号化の種類がサポートされます。 AzureADSSOAcc$ アカウントの暗号化の種類を AES256\_HMAC\_SHA1 に設定するか、AES タイプまたは RC4 のいずれかに設定してセキュリティを強化することをお勧めします。 暗号化の種類は、Active Directory 内のアカウントの属性の msDS-SupportedEncryptionTypes 属性に格納されます。 AzureADSSOAcc$ アカウントの暗号化の種類が RC4\_HMAC\_MD5 に設定されていて、それを AES 暗号化の種類のいずれかに変更する場合は、関連する質問の [下の FAQ ドキュメント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-faq) で説明されているように、AzureADSSOAcc$ アカウントの Kerberos 復号化キーを最初にロールオーバーしてください。そうしないと、シームレス SSO は発生しません。
- フォレストの信頼関係があるフォレストが複数ある場合、いずれかのフォレストで SSO を有効にすると、すべての信頼されたフォレストで SSO が有効になります。 SSO が既に有効になっているフォレストで SSO を有効にすると、フォレストで SSO が既に有効になっているというエラーが表示されます。
- シームレス SSO を有効にするポリシーには、25,600 文字の制限があります。 この制限は、シームレス SSO を有効にするフォレスト名を含め、ポリシーに含まれるすべてのものに適用されます。 環境内に多数のフォレストがある場合は、文字数の制限に達する可能性があります。 フォレスト間に信頼関係がある場合は、1 つのフォレストでのみシームレス SSO を有効にするだけで十分です。 たとえば、contoso.com と fabrikam.com があり、この 2 つの間に信頼関係がある場合、contoso.com でのみシームレス SSO を有効にすれば、fabrikam.com にも適用されます。 このようにポリシーで有効にするフォレスト数を減らして、ポリシーの上限を超えないようにすることができます。

### 機能の状態の確認

テナントでシームレス SSO 機能が引き続き **有効** になっていることを確認します。 状態を確認するには、[**Microsoft Entra 管理センター**](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/&gt) の [**Entra ID**&gt;**Entra Connect**https://portal.azure.com/] ウィンドウに移動します。

[Image: Microsoft Entra 管理センターの [Microsoft Entra Connect] ウィンドウのスクリーンショット。]

クリックすると、シームレス SSO が有効になっているすべての AD フォレストが表示されます。

[Image: Microsoft Entra 管理センターの [シームレス SSO] ウィンドウのスクリーンショット。]

### [Microsoft Entra 管理センター](https://entra.microsoft.com)でのサインインエラーの理由 (Premium ライセンスが必要)

テナントに Microsoft Entra ID P1 または P2 ライセンスが関連付けられている場合は、[Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)の Microsoft Entra ID 内の[サインイン アクティビティ レポート](https://entra.microsoft.com)を確認することもできます。

[Image: Microsoft Entra 管理センター: サインイン レポートのスクリーンショット。]

[**Microsoft Entra 管理センター**](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/&gt) で **Entra ID**&gt;**Monitoring & health**https://portal.azure.com/ に移動し、特定のユーザーのサインイン アクティビティを選択します。 **SIGN-IN ERROR CODE フィールドを**探します。 次の表を使用して、そのフィールドの値を、失敗の理由と解決策にマップします。

| サインイン エラー コード | サインインが失敗した理由 | 解像度 |
| --- | --- | --- |
| 81001 | ユーザーの Kerberos チケットが大きすぎます。 | ユーザーのグループ メンバーシップを減らしてやり直してください。 |
| 81002 | ユーザーの Kerberos チケットを検証できません。 | トラブルシューティングの チェックリストを参照してください。 |
| 81003 | ユーザーの Kerberos チケットを検証できません。 | トラブルシューティングの チェックリストを参照してください。 |
| 81004 | Kerberos 認証を試みましたが失敗しました。 | トラブルシューティングの チェックリストを参照してください。 |
| 81008 | ユーザーの Kerberos チケットを検証できません。 | トラブルシューティングの チェックリストを参照してください。 |
| 81009 | ユーザーの Kerberos チケットを検証できません。 | トラブルシューティングの チェックリストを参照してください。 |
| 81010 | シームレス SSO に失敗しました。ユーザーの Kerberos チケットが期限切れか無効です。 | ユーザーは、企業ネットワーク内のドメインに参加しているデバイスからサインインする必要があります。 |
| 81011 | ユーザーの Kerberos チケット内の情報では、ユーザー オブジェクトが見つかりません。 | Microsoft Entra Connect を使って、ユーザーの情報を Microsoft Entra ID に同期します。 |
| 81012 | Microsoft Entra ID にサインインしようとしているユーザーは、デバイスにサインインしているユーザーと異なります。 | ユーザーは別のデバイスからサインインする必要があります。 |
| 81013 | ユーザーの Kerberos チケット内の情報では、ユーザー オブジェクトが見つかりません。 | Microsoft Entra Connect を使って、ユーザーの情報を Microsoft Entra ID に同期します。 |

### トラブルシューティングのチェックリスト

シームレス SSO の問題のトラブルシューティングを行うには、次のチェックリストを使用します。

- Microsoft Entra Connect でシームレス SSO 機能が有効になっていることを確認します。 機能を有効にできない場合 (たとえば、ポートがブロックされているため)、すべての [前提条件](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sso-quick-start#step-1-check-the-prerequisites) が満たされていることを確認してください。
- テナントで [Microsoft Entra 参加](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview) とシームレス SSO の両方を有効にしている場合は、問題が Microsoft Entra 参加に含まれていないことを確認します。 デバイスが Microsoft Entra ID に登録されており、かつドメインに参加している場合は、[Microsoft Entra 参加] が [シームレス SSO] よりも優先されます。 Microsoft Entra 参加の SSO を使用している場合、"Windows に接続済み" というサインイン タイルが表示されます。
- Microsoft Entra の URL (`https://autologon.microsoftazuread-sso.com`) が、ユーザーのイントラネット ゾーンの設定に含まれていることを確認します。
- 会社のデバイスが Active Directory ドメインに参加していることを確認します。 シームレス SSO を機能させるために、デバイスを *Microsoft Entra に参加*させる必要[はありません](https://learn.microsoft.com/ja-jp/entra/identity/devices/overview)。
- ユーザーが Active Directory ドメイン アカウントでデバイスにログオンしていることを確認します。
- ユーザーのアカウントが、シームレス SSO が設定されている Active Directory フォレストからのものであることを確認します。
- デバイスが企業ネットワークに接続されていることを確認します。
- デバイスの時刻が、Active Directory とドメイン コントローラーの両方の時刻と同期されており、時刻のずれが 5 分以内であることを確認します。
- シームレス SSO を有効にする各 AD フォレストで `AZUREADSSOACC` コンピューター アカウントが存在し、有効になっていることを確認します。 コンピューター アカウントが削除された場合、または不足している場合は、 PowerShell コマンドレットを 使用して再作成できます。
- コマンド プロンプトから `klist` コマンド使用して、デバイス上の既存の Kerberos チケットを一覧表示します。 `AZUREADSSOACC` コンピューター アカウントに対して発行されたチケットが存在することを確認します。 ユーザーの Kerberos チケットは、通常は 10 時間有効です。 Active Directory で別の設定が行われていることがあります。
- テナントでシームレス SSO を無効にして再度有効にすると、キャッシュされた Kerberos チケットが期限切れになるまでシングル サインオン機能は利用できません。
- `klist purge` コマンドを使用してデバイスから既存の Kerberos チケットを消去し、やり直します。
- JavaScript 関連の問題があるかどうかを判断するには、ブラウザーのコンソール ログ ( **[開発者ツール**] の下) を確認します。
- ドメイン コントローラーのログを確認します。

#### ドメイン コントローラーのログ

ドメイン コントローラーで成功の監査を有効にすると、ユーザーがシームレス SSO でサインインするたびに、セキュリティ エントリがイベント ログに記録されます。 こうしたセキュリティ イベントは、次のクエリを使用して検索できます (コンピューター アカウント **AzureADSSOAcc$** に関連付けられているイベント **4769** を探します)。

```
  <QueryList>
    <Query Id="0" Path="Security">
      <Select Path="Security">*[EventData[Data[@Name='ServiceName'] and (Data='AZUREADSSOACC$')]]</Select>
    </Query>
  </QueryList>
```

### 機能の手動リセット

トラブルシューティングを行っても改善しなかった場合は、テナントでシームレス SSO 機能を手動でリセットできます。 Microsoft Entra Connect が実行されているオンプレミス サーバーで次の手順を実行します。

#### 手順 1: ADSync とシームレス SSO PowerShell モジュールをインポートする

1. Microsoft Entra接続がインストールされていることを確認します。 [Microsoft Entra管理センター](https://entra.microsoft.com/#view/Microsoft_AAD_Connect_Provisioning/AADConnectMenuBlade/%7E/GetStarted)からダウンロードします。
2. ADSync PowerShell モジュールをインポートします。

    ```powershell
    Import-Module "$env:ProgramFiles\Microsoft Azure AD Sync\Bin\ADSync\ADSync.psd1"
    ```
3. `%ProgramFiles%\Microsoft Azure Active Directory Connect` フォルダーを参照します。
4. シームレス SSO PowerShell モジュールをインポートします。

    ```powershell
    Import-Module .\AzureADSSO.psd1
    ```

#### 手順 2:シームレス SSO が有効になっている Active Directory フォレストのリストを取得する

1. PowerShell を管理者として実行します。 PowerShell で、`New-AzureADSSOAuthenticationContext` を呼び出します。 テナントの ハイブリッド ID の管理者資格情報を入力するよう求められたら、入力してください。
2. `Get-AzureADSSOStatus` を呼び出します。 このコマンドでは、この機能が有効になっている Active Directory フォレストの一覧 ("ドメイン" リストを参照) が表示されます。

#### 手順 3:機能を設定した各 Active Directory フォレストのシームレス SSO を無効にする

1. `$creds = Get-Credential` を呼び出します。 求められたら、目的の Active Directory フォレストのドメイン管理者の資格情報を入力します。

    Note

    ドメイン管理者の資格情報ユーザー名は、SAM アカウント名の形式 (contoso\johndoe または contoso.com\johndoe) で入力する必要があります。 Microsoft はユーザー名のドメイン部分を使用して、DNS を使用してドメイン管理者のドメイン コントローラーを検索します。

    Note

    使用するドメイン管理者アカウントは、保護されているユーザー グループのメンバーであってはなりません。 そうである場合、操作は失敗します。
2. `Disable-AzureADSSOForest -OnPremCredentials $creds` を呼び出します。 このコマンドは、この特定の Active Directory フォレスト用のオンプレミスのドメイン コントローラーから `AZUREADSSOACC` コンピューター アカウントを削除します。

    Note

    何らかの理由でオンプレミスの AD にアクセスできない場合は、 **手順 3.1** と **3.2** をスキップして、代わりに `Disable-AzureADSSOForest -DomainFqdn <Domain name from the output list in step 2>`呼び出すことができます。
3. 機能を設定した Active Directory フォレストごとに、前の手順を繰り返します。

#### 手順 4:各 Active Directory フォレストのシームレス SSO を有効にする

1. `Enable-AzureADSSOForest` を呼び出します。 求められたら、目的の Active Directory フォレストのドメイン管理者の資格情報を入力します。

    Note

    ドメイン管理者の資格情報ユーザー名は、SAM アカウント名の形式 (contoso\johndoe または contoso.com\johndoe) で入力する必要があります。 Microsoft はユーザー名のドメイン部分を使用して、DNS を使用してドメイン管理者のドメイン コントローラーを検索します。

    Note

    使用するドメイン管理者アカウントは、保護されているユーザー グループのメンバーであってはなりません。 そうである場合、操作は失敗します。
2. 機能を設定する Active Directory フォレストごとに、前の手順を繰り返します。

#### 手順 5: テナントで機能を有効にする

テナントで機能を有効にするには、`Enable-AzureADSSO -Enable $true`を呼び出します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-sync-errors"} -->
## Microsoft Entra Connect: 同期中のエラーのトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-sync-errors
- Service: entra-id / hybrid-connect
- Article date: 2026-07-01
- Summary: この記事では、Microsoft Entra Connect での同期中に発生したエラーのトラブルシューティング方法について説明します。

Windows Server Active Directory から Microsoft Entra ID に ID データが同期されるとき、エラーが発生する可能性があります。 この記事では、さまざまな種類の同期エラーの概要、これらのエラーの原因となる可能性がある一部のシナリオ、エラーを修正する有効な方法について説明します。 この記事では、一般的な種類のエラーを取り上げます。発生する可能性があるすべてのエラーは取り上げません。

この記事は、読者に [Microsoft Entra ID と Microsoft Entra Connect の設計概念](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-design-concepts)に関する基礎知識があることを前提としています。

重要

この記事では、最も一般的な同期エラーへの対処を試みます。 残念ながら、1 つのドキュメントですべてのシナリオを扱うことはできません。 詳しいトラブルシューティングの手順などについて詳しくは、Microsoft Entra トラブルシューティング ドキュメントの「[Microsoft Entra Connect オブジェクトおよび属性のエンドツーエンド トラブルシューティング](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/troubleshoot-aad-connect-objects-attributes)」および「[ユーザー プロビジョニングと同期](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/welcome-azure-ad)」セクションを参照してください。

Microsoft Entra Connect の最新バージョン (2016 年 8 月以降) では、同期エラー レポートは、[Microsoft Entra 管理センター](https://entra.microsoft.com)で Microsoft Entra Connect Health for Sync の一部として利用できます。

2016 年 9 月 1 日以降、"[新しい](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-duplicate-attribute-resiliency)" すべての Microsoft Entra テナントで *Microsoft Entra ID の重複属性の回復性*が既定で有効になります。 この機能は、既存のテナントに対して自動的に有効になります。

Microsoft Entra Connect は、同期を保つディレクトリに対して 3 種類の操作 (インポート、同期、エクスポート) を実行します。 3 つすべての操作でエラーが発生する可能性があります。 この記事では、主に Microsoft Entra ID へのエクスポート中のエラーについて説明します。

### Microsoft Entra ID へのエクスポート中のエラー

次のセクションでは、Microsoft Entra コネクタを使用して Microsoft Entra ID にエクスポート操作を行うときに発生する可能性のある、さまざまな種類の同期エラーについて説明します。 このコネクタは、contoso.*onmicrosoft.com* という形式の名前により識別できます。 Microsoft Entra ID へのエクスポート中のエラーは、Microsoft Entra Connect (同期エンジン) が Microsoft Entra ID に対して試行した操作 (追加、更新、削除など) が失敗したことを示します。

[Image: エクスポート エラーの概要を示す図。]

### データの不一致エラー

このセクションでは、データの不一致エラーについて説明します。

#### InvalidHardMatch

ターゲット クラウド ユーザーが引き継ぎまたは再関連付けから保護されているため、`InvalidHardMatch`操作をブロックすると、 エラーが発生します。 この保護は、ターゲット アカウントが特権を持っているか、既にオンプレミス オブジェクトにマップされている場合に、オンプレミスの Active Directory オブジェクトがクラウド アカウントを引き継ぐのを防ぐのに役立ちます。

##### Symptoms

エクスポートまたは同期中に、次のいずれかのエラーが表示されることがあります。

| シナリオ | エラーコードまたはファミリ | エラー メッセージ |
| --- | --- | --- |
| 特権クラウド アカウントの引き継ぎ | `InvalidHardMatch` / `103` | `The cloud user with privileged roles is not allowed to be taken over.` |
| 既存のオンプレミス オブジェクト マッピング | `AttributeUpdateNotAllowed` / `96` | `Unable to update this object because the following attributes associated with this object are not allowed to be updated by your on-premises directory: [OnPremisesObjectIdentifier cannot be changed unless its current value is null.]` |
| ハードマッチのセキュリティ強化 | ハードマッチのセキュリティ強化 | `Hard match operation blocked due to security hardening. Review OnPremisesObjectIdentifier mapping.` |

注

古い同期クライアントでは、あまり具体的な表現が表示されませんが、クラウド側の強制の決定は同じです。

##### 原因

2026 年 7 月 1 日より、Microsoft Entra ID はハード マッチ セキュリティ強化を自動的に適用します。 ターゲット クラウド アカウントが次の条件の 1 つ以上を満たしている場合、ハード マッチがブロックされます。

- ターゲット クラウド ユーザーが `onPremisesObjectIdentifier` 設定されている。
- ターゲット クラウド ユーザーには、特権Microsoft Entraロールが割り当てられます。
- ターゲット クラウド ユーザーは、特権Microsoft Entra ロールの対象となります。

また、テナントで `BlockCloudObjectTakeoverThroughHardMatchEnabled` 機能が有効になっていると、ハード マッチがブロックされ、既存のクラウド オブジェクトがハード マッチで引き継がれなくなります。

ライトバックが有効になっているクラウド管理アカウントは、セキュリティ強化の適用から除外されます。 これらの保護により、操作された同期照合属性によってアカウントの引き継ぎのリスクが軽減されます。

##### InvalidHardMatch エラーのシナリオの例

次のシナリオでは、InvalidHardMatch エラーがトリガーされる可能性があります。

- テナントで DirSync が再び有効になり、同じ sourceAnchor を持つオブジェクトが再び同期されますが、 *BlockCloudObjectTakeoverThroughHardMatchEnabled* 機能が有効になり、ハード マッチが発生するのを防ぎます。
- ユーザーが同期スコープから除外され、Microsoft Entra ID のごみ箱から復元されました。 その後、そのユーザーは再び同期スコープに追加され、同じ sourceAnchor 値に基づいて Microsoft Entra ID に既に存在しているオブジェクトの引き継ぎを試みますが、*BlockCloudObjectTakeoverThroughHardMatchEnabled* 機能が有効になっているため、完全一致はブロックされます。
- 特権のロールが割り当てられている、論理削除された同期済み Microsoft Entra ユーザーは、セキュリティリスクを防ぐため、復元操作中のハードマッチの対象から除外されます。
- オンプレミスの Active Directory ユーザーは、管理者ロールが割り当てられている既存の Microsoft Entra ユーザーと、OnPremisesImmutableId 値とのハードマッチを試みます。

##### 事例

次の例は、InvalidHardMatch エラーが発生する方法を示しています。

1. Bob Smith は、オンプレミス Active Directory の contoso.com から同期された、Microsoft Entra ID のユーザーです。
2. 既定では、**"abcdefghijklmnopqrstuv=="** の SourceAnchor 値は、Microsoft Entra Connect によって、オンプレミス Active Directory から Bob Smith の **MsDs-ConsistencyGUID** 属性 (または構成に応じて ObjectGUID) を使用して計算されます。 この属性の値は、Microsoft Entra ID 内の Bob Smith の **immutableId** です。
3. 管理者が Bob Smith を同期スコープから削除し、Microsoft Entra Connect によってオブジェクトの削除がエクスポートされます。
4. Bob Smith のオブジェクトは Microsoft Entra ID で論理的に削除された状態になり、その DirSyncEnabled 属性は False に切り替わります。 ただし、このプロセスによってオブジェクトがクラウド マネージドに変換されるわけではなく、引き続きオンプレミスの Active Directory から同期されたオブジェクトと見なされます。 DirSyncEnabled 値は False であるため、現在は同期スコープ外であり、再び一致可能であることを示しています。
5. 管理者が Bob Smith を同期スコープに再び追加し、Microsoft Entra Connect によってオブジェクトが再び同期されます。
6. 通常、完全一致では、同じ SourceAnchor に基づいて Microsoft Entra ID に存在するオブジェクトを引き継ぎ、DirSyncEnabled 属性を 'True' に戻しますが、*BlockCloudObjectTakeoverThroughHardMatchEnabled* が有効になっているとこの操作は許可されず、InvalidHardMatch がスローされます。

##### 適切な修正プログラムを選択する

ブロックの理由に基づいて回復パスを選択するには、次の表を使用します。

| ブロックの理由が次の場合: | これを実行してください |
| --- | --- |
| 特権ロールが割り当てられました | 特権ロールを一時的に削除し、ハード マッチを完了してから、ロールを再割り当てします。 |
| 特権ロール対象 | ロールの適格性を一時的に削除し、ハード マッチを完了し、その後でロールの適格性を復元します。 |
| ユーザーが論理削除される | 必要に応じて最初にユーザーを復元し、次に特権ロールの回復を適用します。 |
| `onPremisesObjectIdentifier` 既に設定済み | `onPremisesObjectIdentifier``null` をクリアしてから、同期を再実行します。 |
| 適用前に修復することはできません | 修復を完了している間、 `allowOnPremUpdateOfOnPremisesObjectIdentifierEnabled` を一時的に有効にします。 |
| `BlockCloudObjectTakeoverThroughHardMatchEnabled` が有効になっているので、意図的な引き継ぎが必要です | 機能を一時的に無効にしてハード マッチを許可してから、再度有効にします。 「[Microsoft Entra IDでハード マッチをブロックする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#block-hard-match-in-microsoft-entra-id)」を参照してください。 |

Microsoft Graphまたは ADSyncTools で`onPremisesObjectIdentifier`をクリアする、または一時的なバイパス機能フラグを使用する場合は、「[ブロックされたハード マッチからの回復](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#recover-from-a-blocked-hard-match)」を参照してください。

##### 回復の検証

復旧アクションが完了したら、次の手順を実行します。

1. 同期を再実行します。
2. 影響を受けるオブジェクトにエクスポートエラーが表示されなくなったことを確認します。
3. クラウド ユーザーが目的のオンプレミス オブジェクトにリンクされていることを確認します。
4. 特権ロールまたは適格性を一時的に削除した場合は、ハード マッチが成功した後に復元します。
5. `allowOnPremUpdateOfOnPremisesObjectIdentifierEnabled`を有効にした場合は、修復が完了した後でバイパスを無効にします。

##### サポートに連絡する時期

`Contact Technical Support`で終わるエラーが表示された場合は、最初にこの記事の回復手順に従います。 目的のオブジェクト マッピングを検証し、必要に応じて `onPremisesObjectIdentifier` をクリアし、同期を再実行した後でエラーが続く場合にのみ、サポート要求を開きます。

#### InvalidSoftMatch

##### 説明

- InvalidSoftMatch エラーは、ハードマッチで一致するオブジェクトが見つからない一方で、*ソフトマッチ*で一致するオブジェクトが見つかる場合に発生しますが、そのオブジェクトの[immutableId](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#hard-match-vs-soft-match)値が、受け入れ中のオブジェクトの**sourceAnchor**属性値と異なる場合に生じます。 この不一致は、一致するオブジェクトが、オンプレミスの Active Directory の別のオブジェクトから同期されたことを示します。

あいまい一致を機能させるには、あいまい一致の対象となるオブジェクトの **immutableId** 属性に値が設定されていない必要があります。 操作で InvalidSoftMatch 同期エラーが発生するのは、**immutableId** 属性に値が設定されているオブジェクトの完全一致が失敗したが、あいまい一致の条件は満たしていた場合です。

Microsoft Entra スキーマでは、複数のオブジェクトが、次の属性に対して同じ値を持つことは許可されていません。 この一覧はすべてを網羅したものではありません。

- プロキシアドレス
- userPrincipalName
- オンプレミスセキュリティ識別子
- objectId
- 変更不可能ID (immutableId)

[Microsoft Entra 属性の重複属性の回復性](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-syncservice-duplicate-attribute-resiliency)は、Microsoft Entra ID の既定動作としても展開されます。 この機能により、Microsoft Entra Connect およびその他の同期クライアントによって検出される同期エラーの数が減少します。 これにより、Microsoft Entra は、オンプレミス Active Directory 環境に存在する重複した **proxyAddresses** および **userPrincipalName** 属性を処理する点で回復性が向上します。

この機能では重複エラーは修正されません。そのため、データを修正する必要があります。 ただし、新しいオブジェクトのプロビジョニングは許可されます。この機能がない場合には、Microsoft Entra ID に重複値があるとプロビジョニングはブロックされます。 この機能により、同期クライアントに返される同期エラーの数も減少します。

注

テナントに対して Microsoft Entra 属性の重複属性の回復性が有効になっている場合、新しいオブジェクトのプロビジョニング中に検出される InvalidSoftMatch 同期エラーは表示されません。

##### InvalidSoftMatch エラーのシナリオの例

- **proxyAddresses** 属性の値が同じ複数のオブジェクトがオンプレミス Active Directory に存在するとします。 1 つだけが Microsoft Entra ID でプロビジョニングされます。
- **userPrincipalName** 属性の値が同じ複数のオブジェクトがオンプレミス Active Directory に存在するとします。 1 つだけが Microsoft Entra ID でプロビジョニングされます。
- 1 つのオブジェクトがオンプレミス Active Directory に追加され、その **proxyAddresses** 属性の値が、Microsoft Entra ID 内の既存のオブジェクトのものと同じだとします。 オンプレミスに追加されたオブジェクトは、Microsoft Entra ID でプロビジョニングされません。
- 1 つのオブジェクトがオンプレミス Active Directory に追加され、その **userPrincipalName** 属性の値が Microsoft Entra ID 内のアカウントのものと同じだとします。 このオブジェクトは、Microsoft Entra ID でプロビジョニングされません。
- 同期されたアカウントがフォレスト A からフォレスト B に移動されました。Microsoft Entra Connect (同期エンジン) は **objectGUID** 属性を使用して、**sourceAnchor** 属性を計算しました。 フォレストの移動の後、**sourceAnchor** 属性の値は異なります。 フォレスト B の新しいオブジェクトは、Microsoft Entra ID 内の既存のオブジェクトと同期できません。
- 同期対象のオブジェクトがオンプレミス Active Directory から誤って削除されたとします。その後、Microsoft Entra ID でアカウントを削除せずに、同じエンティティ (ユーザーなど) の新しいオブジェクトが Active Directory で作成されたとします。 新しいアカウントは、既存の Microsoft Entra オブジェクトと同期できません。
- Microsoft Entra Connect がアンインストールされ、再インストールされました。 再インストール時に、**sourceAnchor** 属性として別の属性が選択されたとします。 InvalidSoftMatch エラーにより、以前同期されていたすべてのオブジェクトの同期が停止します。

##### 事例

1. Bob Smith は、オンプレミス Active Directory の *contoso.com* から同期された、Microsoft Entra ID のユーザーです。
2. Bob Smith のユーザー プリンシパル名は bobs@contoso.com として設定されています。
3. Microsoft Entra Connect により、オンプレミス Active Directory にある Bob Smith の **objectGUID** 属性を使用して、**sourceAnchor** 属性として **"abcdefghijklmnopqrstuv=="** が計算されます。 この属性は、Microsoft Entra ID 内の Bob Smith の **immutableId** 属性です。
4. また Bob の **proxyAddresses**属性として、次の値があります。
    - SMTP： bobs@contoso.com
    - SMTP： bob.smith@contoso.com
    - SMTP： bob@contoso.com
5. 新しいユーザーである Bob Taylor がオンプレミス Active Directory に追加されます。
6. Bob Taylor のユーザー プリンシパル名は bobt@contoso.com として設定されます。
7. Microsoft Entra Connect により、オンプレミス Active Directory にある Bob Taylor の **objectGUID** 属性を使用して、**sourceAnchor** 属性として **"abcdefghijkl0123456789=="** が計算されます。
8. Bob Taylor の **proxyAddresses**属性として、次の値があります。
    - SMTP： bobt@contoso.com
    - SMTP： bob.taylor@contoso.com
    - SMTP： bob@contoso.com
9. 同期中、Microsoft Entra Connect はオンプレミス Active Directory への Bob Taylor の追加を認識し、同じ変更を行うように Microsoft Entra ID に依頼します。
10. Microsoft Entra は、最初に完全一致を実行します。 つまり、**immutableId** 属性が **"abcdefghijkl0123456789=="** と等しいオブジェクトを検索します。 Microsoft Entra ID 内の他のオブジェクトは、その **immutableId** 属性を持っていないため、完全一致は失敗します。
11. 次に Microsoft Entra ID は、Bob Taylor を見つけるためにあいまい一致を実行します。 つまり、検索を行い、**proxyAddresses** 属性が、smtp: bob@contoso.com など、3 つの値と等しいオブジェクトがあるかどうか確認します。
12. Microsoft Entra ID は、あいまい一致の条件に一致する Bob Smith のオブジェクトを見つけます。 ただし、このオブジェクトには **immutableId = "abcdefghijklmnopqrstuv=="** という値があります。これは、このオブジェクトが、オンプレミス Active Directory 内の別のオブジェクトと同期していることを示します。 Microsoft Entra ID は、これらのオブジェクトをあいまい一致させることができないため、InvalidSoftMatch 同期エラーがスローされます。

##### InvalidSoftMatch エラーを修正する

InvalidSoftMatch エラーの最も一般的な原因は、2 つのオブジェクトで **sourceAnchor** (**immutableId**) 属性が異なっており、一方で **proxyAddresses** 属性または **userPrincipalName** 属性の値が同じであることです。これらの属性は、Microsoft Entra ID であいまい一致プロセス中に使用されます。 InvalidSoftMatch エラーを修正するには:

1. エラーの原因となった、重複する **proxyAddresses** 属性、**userPrincipalName** 属性、または他の属性の値を特定します。 また、この競合に関連している 2 つ以上のオブジェクトを特定します。 [Microsoft Entra Connect Health for Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync) によって生成されるレポートが、2 つのオブジェクトを特定するために役立ちます。
2. 重複した値をそのまま使用するオブジェクトと、使用すべきでないオブジェクトを特定します。
3. 重複する値を、その値を "*持っていてはならない*" オブジェクトから削除します。 そのオブジェクトの供給元のディレクトリで、この変更を行います。 場合によっては、競合しているオブジェクトの 1 つを削除する必要があります。
4. オンプレミス Active Directory で変更を行った場合は、Microsoft Entra Connect で変更を同期します。

Microsoft Entra Connect Health for Sync の同期エラーレポートは 30 分ごとに更新され、最新の同期試行のエラーが含まれます。

注

**ImmutableId** 属性は、定義上、オブジェクトの有効期間中は変更してはなりません。 ただし、前述のリストのシナリオを念頭に置いて Microsoft Entra Connect が構成されていない場合があります。 その場合、Microsoft Entra Connect は、継続して使用したい既存の Microsoft Entra オブジェクトを持つ同じエンティティ（同じユーザー、グループ、または連絡先）を表す Active Directory オブジェクトの **sourceAnchor** 属性に対して、異なる値を計算する可能性があります。

##### 関連記事

[Microsoft 365 でのディレクトリ同期を妨げる重複または無効な属性に関する記事](https://support.microsoft.com/kb/2647098)

#### オブジェクトタイプの不一致

##### 説明

Microsoft Entra ID が 2 つのオブジェクトのあいまい一致を試行するとき、"オブジェクトの種類" (ユーザー、グループ、連絡先など) が異なる 2 つのオブジェクトで、あいまい一致の実行に使用される属性の値が同一である場合があります。 これらの属性の重複は Microsoft Entra ID では許可されないため、この操作では ObjectTypeMismatch 同期エラーが発生します。

##### ObjectTypeMismatch エラーのシナリオ例

メール対応セキュリティ グループが Microsoft 365 で作成されます。 管理者は、**proxyAddresses** 属性の値が Microsoft 365 グループと同じ新しいユーザーまたは連絡先をオンプレミス Active Directory に追加します (まだ Microsoft Entra ID に同期されていません)。

##### 事例

1. 管理者が、税部門のために新しいメール対応セキュリティ グループを Microsoft 365 で作成し、電子メール アドレスを tax@contoso.com と設定します。 このグループには、**proxyAddresses** 属性値として **smtp: tax@contoso.com** が割り当てられます。
2. 新しいユーザーが Contoso.com に加わり、そのユーザーのアカウントがオンプレミスで **proxyAddresses** 属性が **smtp: tax@contoso.com** として作成されます。
3. Microsoft Entra Connect が新しいユーザー アカウントを同期すると、ObjectTypeMismatch エラーが発生します。

##### ObjectTypeMismatch エラーを修正する

ObjectTypeMismatch エラーの最も一般的な原因は、異なる種類 (ユーザー、グループ、連絡先など) の 2 つのオブジェクトの **proxyAddresses** 属性の値が同じであることです。 ObjectTypeMismatch エラーを修正するには:

1. エラーの原因となっている、重複する **proxyAddresses** 属性 (または他の属性) の値を特定します。 また、この競合に関連している 2 つ以上のオブジェクトを特定します。 [Microsoft Entra Connect Health for Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync) によって生成されるレポートが、2 つのオブジェクトを特定するために役立ちます。
2. 重複した値をそのまま使用するオブジェクトと、使用すべきでないオブジェクトを特定します。
3. 重複する値を、その値を "*持っていてはならない*" オブジェクトから削除します。 そのオブジェクトの供給元のディレクトリで、この変更を行います。 場合によっては、競合しているオブジェクトの 1 つを削除する必要があります。
4. オンプレミス AD で変更を行った場合は、Microsoft Entra Connect で変更を同期します。 Microsoft Entra Connect Health for Sync の同期エラー レポートは 30 分ごとに更新されます。 このレポートには、最新の同期試行でのエラーが含まれます。

### 重複する属性

このセクションでは、重複する属性のエラーについて説明します。

#### 属性値はユニークでなければなりません

##### 説明

Microsoft Entra スキーマでは、複数のオブジェクトが、次の属性に対して同じ値を持つことは許可されていません。 Microsoft Entra ID の各オブジェクトは、これらの属性について常に一意の値を持つように強制されます。

- メール
- プロキシアドレス
- サインイン名
- userPrincipalName

Microsoft Entra Connect が新しいオブジェクトの追加または既存のオブジェクトの更新を試行したときに、前述の属性の値が Microsoft Entra ID 内の別のオブジェクトに既に割り当てられている場合、この操作では AttributeValueMustBeUnique 同期エラーが発生します。

##### 考えられるシナリオ

重複する値が、既に同期済みのオブジェクトに割り当てられます。これによって、別の同期オブジェクトとの競合が発生します。

##### 事例

1. Bob Smith は、オンプレミス Active Directory の contoso.com から同期された、Microsoft Entra ID のユーザーです。
2. Bob Smith のオンプレミスでのユーザー プリンシパル名は bobs@contoso.com として設定されています。
3. また Bob の **proxyAddresses**属性として、次の値があります。
    - SMTP： bobs@contoso.com
    - SMTP： bob.smith@contoso.com
    - SMTP： bob@contoso.com
4. 新しいユーザーである Bob Taylor がオンプレミス Active Directory に追加されます。
5. Bob Taylor のユーザー プリンシパル名は bobt@contoso.com として設定されます。
6. Bob Taylor の **proxyAddresses**属性として、次の値があります。
    - SMTP： bobt@contoso.com
    - SMTP： bob.taylor@contoso.com
7. Bob Taylor のオブジェクトは Microsoft Entra ID と正常に同期されます。
8. 管理者が Bob Taylor の **proxyAddresses**属性を次の値で更新することにしました。
    - SMTP： bob@contoso.com
9. Microsoft Entra ID は、Microsoft Entra ID 内の Bob Taylor のオブジェクトを前述の値で更新しようとしますが、この **proxyAddresses** 値は Bob Smith に既に割り当てられているため、この操作は失敗します。 その結果、AttributeValueMustBeUnique エラーが発生します。

##### AttributeValueMustBeUnique エラーを修正する

AttributeValueMustBeUnique エラーの最も一般的な原因は、2 つのオブジェクトで **sourceAnchor** (**immutableId**) 属性が異なっており、一方で **proxyAddresses** 属性または **userPrincipalName** 属性の値が同じであることです。 AttributeValueMustBeUnique エラーを修正するには:

1. エラーの原因となった、重複する **proxyAddresses** 属性、**userPrincipalName** 属性、または他の属性の値を特定します。 また、この競合に関連している 2 つ以上のオブジェクトを特定します。 [Microsoft Entra Connect Health for Sync](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-sync) によって生成されるレポートが、2 つのオブジェクトを特定するために役立ちます。
2. 重複した値をそのまま使用するオブジェクトと、使用すべきでないオブジェクトを特定します。
3. 重複する値を、その値を "*持っていてはならない*" オブジェクトから削除します。 そのオブジェクトの供給元のディレクトリで、この変更を行います。 場合によっては、競合しているオブジェクトの 1 つを削除する必要があります。
4. オンプレミス Active Directory で変更を行った場合は、エラーを修正するために Microsoft Entra Connect で変更を同期します。

##### 関連記事

[Microsoft 365 でのディレクトリ同期を妨げる重複または無効な属性に関する記事](https://support.microsoft.com/kb/2647098)

### データ検証の失敗

このセクションでは、データ検証の失敗について説明します。

#### IdentityDataValidationFailed または DataValidationFailed

##### 説明

Microsoft Entra ID は、データそのものにさまざまな制約を適用した上で、ディレクトリへのデータの書き込みを許可します。 これらの制限は、そのようなデータに依存するアプリケーションをエンド ユーザーが使用する際に、最善のエクスペリエンスを提供するためです。

##### シナリオ

- **userPrincipalName** 属性値に無効な文字またはサポートされていない文字が含まれています。
- **userPrincipalName** 属性が必要な形式ではありません。
- **onPremisesObjectIdentifier** 属性は、ハード マッチ操作の結果として変更されました。

上記のシナリオの結果は、IdentityDataValidationFailed または DataValidationFailed エラーです。

##### IdentityDataValidationFailed エラーと DataValidationFailed エラーを修正する

**userPrincipalName** 属性に、サポートされている文字が含まれていて、かつ必要な形式であることを確認します。 ハード マッチ操作中**に DataValidationFailed** エラーが発生した場合は、「[既存のテナントの Microsoft Entra Connect の構成](https://learn.microsoft.com/entra/identity/hybrid/connect/how-to-connect-install-existing-tenant#hard-match-scenarios-and-recovery-paths)」で説明されている**ハード マッチ シナリオと回復パス**のいずれかを使用します

##### 関連記事

[Microsoft 365 へのディレクトリ同期を通してユーザーをプロビジョニングするための準備](https://support.office.com/article/Prepare-to-provision-users-through-directory-synchronization-to-Office-365-01920974-9e6f-4331-a370-13aea4e82b3e)

### 削除アクセス違反およびパスワード アクセス違反エラー

Microsoft Entra ID は、クラウド専用オブジェクトが Microsoft Entra Connect から更新されないように保護します。 これらのオブジェクトを Microsoft Entra Connect から更新することはできませんが、Microsoft Entra のバックエンドを直接呼び出して、クラウド専用オブジェクトの変更を試みることはできます。 これを行う際は、次のエラーが返されることがあります。

- This synchronization operation, Delete, isn't valid. (この同期操作 (削除) は無効です。) テクニカル サポート (エラーの種類 114) にお問い合わせください。
- Unable to process this update because one or more cloud-only users' credential update is included in the current request. (1 つ以上のクラウド専用ユーザーの資格情報の更新が現在の要求に含まれているため、この更新を処理できません。)
- Deleting a cloud-only object isn't supported. (クラウド専用オブジェクトの削除はサポートされていません。) Microsoft カスタマー サポートに問い合わせてください。
- The password change request can't be executed because it contains changes to one or more cloud-only user objects, which aren't supported. (1 つ以上のクラウド専用ユーザー オブジェクトに対する変更が含まれているため、パスワード変更要求を実行できません。これはサポートされていません。) Microsoft カスタマー サポートに問い合わせてください。

#### CloudOnlyObjectが削除できないエラー（種類 114）を解決する

このセクションでは、エラー DeletingCloudOnlyObjectNotAllowed (エラーの種類 114) の考えられる原因と解決策について説明します。

Microsoft は、組織が[全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロールが永続的に割り当てられる 2 つのクラウド専用緊急アクセス アカウントを作成することを推奨しています。 このようなアカウントは高い特権を持っており、特定のユーザーには割り当てられません。 アカウントは、通常のアカウントが使用できない場合や、他のすべての管理者が誤ってロックアウトされてしまうなどの緊急時、もしくは"break glass"の状況に限定されます。これらのアカウントは、[緊急アクセス アカウントに関する推奨事項](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/security-emergency-access)に従って作成する必要があります。

##### 説明

これは、顧客がハイブリッドからクラウド専用に移行する場合のシナリオです。 管理者は、Microsoft Entra Connect への呼び出しを開始してユーザーをスコープ外に移動しようとしましたが、Microsoft Entra Connect から次のエラー DeletingCloudOnlyObjectNotAllowed (または種類 114 のエラー) が返されました。"This synchronization operation, Delete, isn't valid. (「この同期操作 (削除) は無効です。」) テクニカル サポートに問い合わせてください。

このエラーでは以下の原因が考えられます。

- Microsoft Entra Connect からの呼び出しには、UPN、新規またはユニークな GUID、またはその他の必要な情報がありません。
- Microsoft Entra Connect はデータをエクスポートしようとしましたが、`DirSyncEnabled` が False に設定されています。
- Microsoft Entra Connect が、復元されたユーザーまたはその他のオブジェクトを削除しようとしています。 これは通常、ユーザーまたはその他のオブジェクト参照が、同期スコープ外または Lost & Found コンテナーに移動されているためです。

##### 考えられるシナリオ

Microsoft Entra Connect クライアントは、ハイブリッドからクラウド専用への移行中にユーザーの削除に失敗し、種類 114 のエラーが発生します。

ユーザーが削除されない理由として、以下が考えられます。

- ユーザーをスコープ外に移動するために顧客によって作成されたルールは、`Admin` 属性に基づいています。
- 同期 (Azure AD Sync) 操作中に種類 114 のエラーが返され、ユーザーの削除に失敗します。
- 特定の機能の同期が失敗し、その結果ユーザーが適切に削除されない。

##### エラーの例

エクスポート エラーの例:

```
TimeOccurred (UTC) 2021-10-20 23:51:28
MachineId 321d15e1-4ad6-49c7-918b-40a62a5140bd
Connector Name IDEXX.onmicrosoft.com - AAD
ErrorType 114
ErrorCode 0x8023134a
ErrorLiteral This synchronization operation, Delete, is not valid. Contact Technical Support. Tracking Id: 09fb1e9b-3ff7-4163-9731-581785e347e5
ServerErrorDetail N/A
CsObjectIdentifier {aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb}
Dn CN={783456306961654236304B58786A66746377643748773D3D}
```

##### エラーを修正する

この問題を解決するには、次の手順を実行します。

1. 問題オブジェクト参照を特定します。
2. PowerShell を使って、クラウド アカウントを論理的に削除します。
3. `Start-ADSyncSyncCycle -PolicyType Delta` を実行します。アカウントの削除が正常にインポートされるはずです。
4. 削除が成功したことを確認します。
5. ごみ箱からユーザーを復元します。
6. サーバーで `Start-ADSyncSyncCycle -PolicyType Delta` を実行して、エラーが再び発生しないことを確認します。

警告

ユーザーが同期スコープから除外されると、オブジェクトは Microsoft Entra ID で論理的に削除された状態になり、その DirSyncEnabled 属性は False に切り替わります。 ただし、このプロセスによってオブジェクトがクラウド マネージドに変換されるわけではありません。オンプレミスの Active Directory から同期される、クラウドで管理できない属性と値が引き続き含まれているためです。 DirSyncEnabled 値は False であるため、現在は同期スコープ外であり、再び一致可能であることを示しています。

### LargeObject または ExceededAllowedLength

このセクションでは、LargeObject または ExceededAllowedLength エラーについて説明します。

#### 説明

属性が、Microsoft Entra スキーマで設定されている許容サイズ制限、長さ制限、または個数制限を超過すると、同期操作で LargeObject または ExceededAllowedLength 同期エラーが発生します。 通常、このエラーは次の属性に対して発生します。

- ユーザー証明書
- userSMIMECertificate
- thumbnailPhoto
- プロキシアドレス

Microsoft Entra ID では、属性ごとに制限は適用されません。ただし、**userCertificate** 属性には、証明書が 15 個というハード コード制限があります。また、[ディレクトリ拡張機能](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-feature-directory-extensions)には、属性が最大 100 というハード コード制限があり、各ディレクトリ拡張機能の文字数は最大 250 です。 オブジェクト全体のサイズ制限があります。 Microsoft Entra Connect が、このオブジェクト サイズ制限を超えるオブジェクトを同期しようとすると、エクスポート エラーがスローされます。

すべての属性が、オブジェクトの最終的なサイズに影響します。 一部の属性では、追加の処理オーバーヘッドのために、重みの乗数が異なります。 たとえば、インデックス付きの値などです。 また、さまざまなクラウド サービス、サービス プラン、ライセンスがアカウントに割り当てられる場合があり、それにより、さらに追加の属性が使用され、オブジェクトの全体サイズに影響します。

Microsoft Entra ID で、属性に保持できるエントリの数を正確に特定することはできません (たとえば、**proxyAddresses** 属性に保持できる SMTP アドレスの数など)。 この量は、オブジェクトで設定されているすべての属性のサイズと乗率によって決まります。

#### 考えられるシナリオ

- Bob の **userCertificate** 属性に格納されている、Bob に割り当てられた証明書の数が多すぎます。 これらの証明書には、期限切れの古い証明書が含まれる可能性があります。 ハード制限は、証明書 15 個です。 **userCertificate** 属性が原因の LargeObject エラーを処理する方法について詳しくは、「[userCertificate 属性が原因で発生した LargeObject エラーの処理](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-largeobjecterror-usercertificate)」を参照してください。
- Bob の **userSMIMECertificate** 属性に格納されている、Bob に割り当てられた証明書の数が多すぎます。 これらの証明書には、期限切れの古い証明書が含まれる可能性があります。 ハード制限は、証明書 15 個です。
- Active Directory で設定された Bob の **thumbnailPhoto** 属性が大きすぎて、Microsoft Entra ID で同期できません。
- Active Directory で **proxyAddresses** 属性が自動設定されるときに、オブジェクトに非常に多くの **proxyAddresses** 属性が割り当てられます。

次の例は、**userCertificate** や **proxyAddresses** などの属性のさまざまな重みを示しています。

- 必須の Active Directory 属性と Mail 属性以外の属性が設定されていない同期対象ユーザーは、最大 332 個のプロキシ アドレスを同期できる場合があります。
- 同じ同期対象ユーザーが **mailNickName** 属性と 10 個のユーザー証明書を持つ場合、プロキシ アドレスの最大数は 329 個に減少します。
- 同じ同期対象ユーザーに、10 個のユーザー証明書と、(すべてのサービス プランが有効になった) 4 つのサブスクリプションが割り当てられている場合、プロキシ アドレスの最大数は 311 個に減少します。
- 前述のユーザーが、最大数のプロキシ アドレスを既に保持しており、もう 1 つ SMTP アドレスを追加する必要があるとします。 312 個のプロキシ アドレスを保持するには、少なくとも 3 つのユーザー証明書を削除する必要があります (証明書のサイズによって異なります)。

注

これらの数値は多少異なる場合があります。 経験則として、**proxyAddresses** 属性の SMTP アドレスの制限は、約 300 個にすると安全です。これにより、オブジェクトと設定される属性が将来大きくなっても余裕があります。

#### LargeObject または ExceededAllowedLength エラーを修正する

ユーザー プロパティを確認し、不要になった属性値を削除します。 たとえば、失効した、または期限が切れた証明書や、SMTP、X.400、X.500、MSMail、CcMail など、古いまたは不必要なアドレスなどです。

### 既存の管理者役割の競合

#### 説明

ユーザーオブジェクトに次の要素が含まれる場合、同期中にそのユーザーオブジェクトで既存の管理者ロールの競合が原因の同期エラーが発生します。

- 管理者のアクセス許可。
- 既存の Microsoft Entra オブジェクトと同じ **userPrincipalName** 属性。

Microsoft Entra Connect では、オンプレミス AD のユーザー オブジェクトと、管理ロールが割り当てられている Microsoft Entra ID のユーザー オブジェクトとの、あいまい一致は認められていません。 詳しくは、「[Microsoft Entra UserPrincipalName の作成](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/plan-connect-userprincipalname)」をご覧ください。

#### 既存の管理者ロール衝突エラーを解決する

この問題を解決するには、次の手順を実行します。

1. Microsoft Entra アカウント (所有者) をすべての管理者ロールから削除する。
2. 検疫済みオブジェクトをクラウドから物理的に削除します。
3. クラウド ユーザーはハイブリッド ID 管理者ではなくなったため、次の同期サイクルで、オンプレミス ユーザーとクラウド アカウントのあいまい一致が処理されます。
4. 所有者のロールのメンバーシップを復元します。

注

オンプレミス ユーザー オブジェクトと Microsoft Entra ユーザー オブジェクトの間のあいまい一致が完了した後、既存のユーザー オブジェクトに管理ロールを再び割り当てることができます。

### 関連リンク

- [Active Directory 管理センターで Active Directory オブジェクトを見つける](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2008-R2-and-2008/dd560661%28v=ws.10%29)
- [Microsoft Graph PowerShell を使用して Microsoft Entra ID でオブジェクトを照会する](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/overview)
- [Microsoft Entra Connect オブジェクトおよび属性のエンドツーエンド トラブルシューティング](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/troubleshoot-aad-connect-objects-attributes)
- [Microsoft Entra のトラブルシューティング](https://learn.microsoft.com/ja-jp/troubleshoot/azure/active-directory/welcome-azure-ad)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tshoot-connect-tshoot-sql-connectivity"} -->
## Microsoft Entra Connect: SQL 接続の問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tshoot-connect-tshoot-sql-connectivity
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect で起きる SQL 接続に関する問題のトラブルシューティング方法について説明します。

この記事では、Microsoft Entra Connect と SQL Server の間の接続に関する問題のトラブルシューティング方法について説明します。

次のスクリーンショットは、SQL Server が見つからない場合の一般的なエラーを示しています。

[Image: SQL エラー]

### トラブルシューティングの手順

[管理者として実行] を使用して PowerShell ウィンドウを開き、ADSyncTools のインストールとインポート PowerShell モジュールを開きます。

```powershell
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
Install-PackageProvider -Name NuGet -MinimumVersion 2.8.5.201 -Force
Install-Module ADSyncTools
Import-Module ADSyncTools
```

メモ

Install-Module は [PowerShell 5.0 (WMF 5.0)](https://www.microsoft.com/download/details.aspx?id=50395) またはそれ以降への更新が必要になります。 または [PackageManagement PowerShell モジュール プレビュー - March 2016 for PowerShell 3.0/4.0](https://learn.microsoft.com/ja-jp/powershell/module/packagemanagement/) をインストールしてください

- **すべてのコマンドを表示**: `Get-Command *Sql* -Module ADSyncTools`
- **PowerShell 関数を実行**: 以下のパラメーターを指定した `Connect-ADSyncToolsSqlDatabase`。
    - Server: SQL Server サーバー名。
    - Instance (省略可能): SQL Server インスタンス名と、必要に応じて、使用するポート番号。 既定のインスタンスを使用する場合は、このパラメーターを指定しないでください。
    - Port (省略可能): SQL Server ポート
    - UserName (省略可能): 接続するユーザー アカウント。空白のままにすると、現在ログオンしているアカウントが使用されます。 リモートの SQL Server に接続している場合、この UserName は Microsoft Entra Connect SQL 接続用に作成したカスタム サービス アカウントである必要があります。 Microsoft Entra Connect では、Microsoft Entra Connect 同期サービス アカウントを使用してリモート SQL Server に対する認証を行います。
    - Password (省略可能): 指定した UserName のパスワード。

この PowerShell 関数は、渡された資格情報、または現在のユーザーの資格情報を使用して、指定された SQL Server とインスタンスへのバインドを試みます。 SQL Server が見つからない場合、このスクリプトは SQL Browser サービスに接続を試み、有効になっているプロトコルとポートを決定します。

サーバー名のみを使用する例

```powershell

PS C:\> Connect-ADSyncToolsSqlDatabase -Server SQL1.contoso.com
Resolving server address : SQL1.contoso.com
    InterNetworkV6 : fe80::6c90:a995:3e70:ef74%17
    InterNetworkV6 : 2001:4898:e0:66:6c90:a995:3e70:ef74
    InterNetwork : 10.91.26.143

Attempting to connect to SQL1 using a TCP binding for the default instance.
   Data Source=tcp:SQL1.contoso.com\;Integrated Security=True.ConnectionString
   Successfully connected.

StatisticsEnabled                : False
AccessToken                      : 
ConnectionString                 : Data Source=tcp:SQL1\;Integrated Security=True
ConnectionTimeout                : 15
Database                         : master
DataSource                       : tcp:SQL1.contoso.com\
PacketSize                       : 8000
ClientConnectionId               : 23e06ef2-0a38-4f5f-9291-da931de40375
ServerVersion                    : 13.00.4474
State                            : Open
WorkstationId                    : SQL1
Credential                       : 
FireInfoMessageEventOnUserErrors : False
Site                             : 
Container                        : 

```

サーバー名と SQL 名付きインスタンスの使用例:

```powershell

PS C:\> Connect-ADSyncToolsSqlDatabase -Server SQL1.contoso.com -Instance SQLINSTANCE1
Resolving server address : SQL1.contoso.com
   InterNetwork: 10.0.100.24 

Attempting to connect to SQL1.contoso.com\SQLINSTANCE1 using a TCP binding.
   Data Source=tcp:SQL1.contoso.com\SQLINSTANCE1;Integrated Security=True
   Successfully connected.

StatisticsEnabled                : False
AccessToken                      : 
ConnectionString                 : Data Source=tcp:SQL1.contoso.com\SQLINSTANCE1;Integrated Security=True
ConnectionTimeout                : 15
Database                         : master
DataSource                       : tcp:SQL1.contoso.com\SQLINSTANCE1
PacketSize                       : 8000
ClientConnectionId               : 2b365b7a-4348-45f6-9314-d6b56db36dbd
ServerVersion                    : 13.00.4259
State                            : Open
WorkstationId                    : SQL1
Credential                       : 
FireInfoMessageEventOnUserErrors : False
Site                             : 
Container                        : 

```

到達できない SQL インスタンスの使用例。 これは、SQL Server Browser サービスに対してクエリを試み、使用可能な SQL インスタンスとそれぞれのポートを表示します。

```powershell

PS C:\> Connect-ADSyncToolsSqlDatabase -Server SQL01.Contoso.com -Instance DEFAULT
Resolving server address : SQL01.Contoso.com
   InterNetwork: 10.0.100.24 

Attempting to connect to SQL01.Contoso.com\SQL using a TCP binding.
   Data Source=tcp:SQL01.Contoso.com\SQL;Integrated Security=True
Connect-ADSyncToolsSqlDatabase : Unable to connect using a TCP binding.  A network-related or instance-specific error occurred while establishing a connection to SQL Server. The server was not found or was 
not accessible. Verify that the instance name is correct and that SQL Server is configured to allow remote connections. (provider: SQL Network Interfaces, error: 26 - Error Locating Server/Instance 
Specified) 
At line:1 char:1
+ Connect-ADSyncToolsSqlDatabase -Server SQL01.Contoso.com -Insta ...
+ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    + CategoryInfo          : ConnectionError: (:) [Write-Error], WriteErrorException
    + FullyQualifiedErrorId : Microsoft.PowerShell.Commands.WriteErrorException,Connect-ADSyncToolsSqlDatabase
 
TROUBLESHOOTING: Attempting to query the SQL Server Browser service configuration on SQL01.Contoso.com. 

SQL browser response contained 2 instances.
Verifying protocol bindings and port connectivity...
SQLINSTANCE1    : Enabled - port 49823 is assigned and reachable through the firewall
SQL2019         : Enabled - port 50631 is assigned and reachable through the firewall

WHAT TO TRY NEXT: 

Each SQL instance must be bound to an explicit static TCP port and paired with an inbound firewall rule on SQL01.Contoso.com to allow connection. Review the TcpStatus field for each instance and take cor
rective action. 

InstanceName : SQLINSTANCE1
tcp          : 49823
TcpStatus    : Enabled - port 49823 is assigned and reachable through the firewall

InstanceName : SQL2019
tcp          : 50631
TcpStatus    : Enabled - port 50631 is assigned and reachable through the firewall

```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tutorial-federation"} -->
## チュートリアル: 1 つの Active Directory フォレストでハイブリッド ID にフェデレーションを使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-federation
- Service: entra-id / hybrid-connect
- Article date: 2025-09-29
- Summary: フェデレーションを使用してハイブリッド ID 環境を設定し、Windows Server Active Directory フォレストを Microsoft Entra ID と統合する方法について説明します。

このチュートリアルでは、フェデレーションと Windows Server Active Directory (Windows Server AD) を使用して Azure でハイブリッド ID 環境を作成する方法について説明します。 作成したハイブリッド ID 環境は、テスト目的や、ハイブリッド ID のしくみについて理解を深めるために使用できます。

[Image: フェデレーションを使用して Azure でハイブリッド ID 環境を作成する方法を示す図。]

このチュートリアルでは、以下の内容を学習します。

- 仮想マシンを作成します。
- Windows Server Active Directory 環境を作成する。
- Windows Server Active Directory ユーザーを作成する。
- 証明書を作成する。
- Microsoft Entra のテナントを作成する。
- Azure でハイブリッド ID 管理者アカウントを作成する。
- カスタム ドメインをディレクトリに追加する。
- Microsoft Entra Connect を設定する。
- ユーザーが同期されていることをテストして確認する。

### 前提条件

このチュートリアルを完了するには、以下のものが必要です。

- [Hyper-V](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/hyper-v-technology-overview) がインストールされているコンピューター。 [windows 10](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/about/supported-guest-os) または [Windows Server 2025](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/supported-windows-guest-operating-systems-for-hyper-v-on-windows) コンピューターに Hyper-V をインストールすることをお勧めします。
- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。
- [外部ネットワーク アダプター](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/quick-start/connect-to-network)。仮想マシンがインターネットに接続できるようにします。
- Windows Server 2025 または Windows Server 2022 のコピー。
- 検証できる [カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) 。

注

このチュートリアルでは、PowerShell スクリプトを使用して、チュートリアル環境をすばやく作成します。 各スクリプトでは、そのスクリプトの先頭で宣言された変数が使用されます。 変数は環境に合わせて変更してください。

このチュートリアルのスクリプトでは、Microsoft Entra Connect をインストールする前に、一般的な Windows Server Active Directory (Windows Server AD) 環境を作成します。 スクリプトは、関連するチュートリアルでも使用されます。

このチュートリアルで使用する PowerShell スクリプトは、 [GitHub](https://github.com/billmath/tutorial-phs) で入手できます。

### 仮想マシンの作成

ハイブリッド ID 環境を作成するための最初のタスクは、オンプレミスのWindows Server AD サーバーとして使用する仮想マシンを作成することです。

注

ホスト コンピューターにおいて PowerShell でスクリプトを実行したことがない場合は、スクリプトを実行する前に、管理者として Windows PowerShell ISE 開き、`Set-ExecutionPolicy remotesigned` を実行します。 [ **実行ポリシーの変更** ] ダイアログで、[ **はい**] を選択します。

仮想マシンを作成するには:

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $VMName = 'DC1'
    $Switch = 'External'
    $InstallMedia = 'D:\ISO\en_windows_server_2016_updated_feb_2018_x64_dvd_11636692.iso'
    $Path = 'D:\VM'
    $VHDPath = 'D:\VM\DC1\DC1.vhdx'
    $VHDSize = '64424509440'
    
    #Create a new virtual machine
    New-VM -Name $VMName -MemoryStartupBytes 16GB -BootDevice VHD -Path $Path -NewVHDPath $VHDPath -NewVHDSizeBytes $VHDSize  -Generation 2 -Switch $Switch  
    
    #Set the memory to be non-dynamic
    Set-VMMemory $VMName -DynamicMemoryEnabled $false
    
    #Add a DVD drive to the virtual machine
    Add-VMDvdDrive -VMName $VMName -ControllerNumber 0 -ControllerLocation 1 -Path $InstallMedia
    
    #Mount installation media
    $DVDDrive = Get-VMDvdDrive -VMName $VMName
    
    #Configure the virtual machine to boot from the DVD
    Set-VMFirmware -VMName $VMName -FirstBootDevice $DVDDrive 
    ```

### オペレーティング システムをインストールする

仮想マシンの作成を完了するには、オペレーティング システムをインストールします。

1. Hyper-V マネージャーで、仮想マシンをダブルクリックします。
2. **[スタート] を選択します**。
3. プロンプトで、任意のキーを押して CD または DVD から起動します。
4. Windows Server のスタート ウィンドウで、言語を選択し、[ **次へ**] を選択します。
5. [ **今すぐインストール]** を選択します。
6. ライセンス キーを入力し、[ **次へ**] を選択します。
7. [ **ライセンス条項に同意する** ] チェック ボックスをオンにし、[ **次へ**] を選択します。
8. [ **カスタム: Windows のみをインストールする (詳細)]**を選択します。
9. [ **次へ**] を選択します。
10. インストールが完了したら、仮想マシンを再起動します。 サインインし、[Windows Update] をオンにします。 更新プログラムをインストールして、VM が完全に最新であることを確認します。

### Windows Server AD の前提条件をインストールする

Windows Server ADをインストールする前に、前提条件をインストールするスクリプトを実行します。

1. Windows PowerShell ISE を管理者として開きます。
2. `Set-ExecutionPolicy remotesigned` を実行します。 **[実行ポリシーの変更**] ダイアログで、[**はい] を [すべて] に**選択します。
3. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $ipaddress = "10.0.1.117" 
    $ipprefix = "24" 
    $ipgw = "10.0.1.1" 
    $ipdns = "10.0.1.117"
    $ipdns2 = "8.8.8.8" 
    $ipif = (Get-NetAdapter).ifIndex 
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $newname = "DC1"
    $addsTools = "RSAT-AD-Tools" 
    
    #Set a static IP address
    New-NetIPAddress -IPAddress $ipaddress -PrefixLength $ipprefix -InterfaceIndex $ipif -DefaultGateway $ipgw 
    
    # Set the DNS servers
    Set-DnsClientServerAddress -InterfaceIndex $ipif -ServerAddresses ($ipdns, $ipdns2)
    
    #Rename the computer 
    Rename-Computer -NewName $newname -force 
    
    #Install features 
    New-Item $featureLogPath -ItemType file -Force 
    Add-WindowsFeature $addsTools 
    Get-WindowsFeature | Where installed >>$featureLogPath 
    
    #Restart the computer 
    Restart-Computer
    ```

### Windows Server AD 環境を作成する

次に、環境を作成するために Active Directory Domain Services をインストールして構成します。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $DatabasePath = "c:\windows\NTDS"
    $DomainMode = "WinThreshold"
    $DomainName = "contoso.com"
    $DomainNetBIOSName = "CONTOSO"
    $ForestMode = "WinThreshold"
    $LogPath = "c:\windows\NTDS"
    $SysVolPath = "c:\windows\SYSVOL"
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $Password = ConvertTo-SecureString "Passw0rd" -AsPlainText -Force
    
    #Install Active Directory Domain Services, DNS, and Group Policy Management Console 
    start-job -Name addFeature -ScriptBlock { 
    Add-WindowsFeature -Name "ad-domain-services" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "dns" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "gpmc" -IncludeAllSubFeature -IncludeManagementTools } 
    Wait-Job -Name addFeature 
    Get-WindowsFeature | Where installed >>$featureLogPath
    
    #Create a new Windows Server AD forest
    Install-ADDSForest -CreateDnsDelegation:$false -DatabasePath $DatabasePath -DomainMode $DomainMode -DomainName $DomainName -SafeModeAdministratorPassword $Password -DomainNetbiosName $DomainNetBIOSName -ForestMode $ForestMode -InstallDns:$true -LogPath $LogPath -NoRebootOnCompletion:$false -SysvolPath $SysVolPath -Force:$true
    ```

### Windows Server AD ユーザーを作成する

次に、テスト用のユーザー アカウントを作成します。 オンプレミスの Active Directory 環境でこのアカウントを作成します。 その後、アカウントは Microsoft Entra ID に同期されます。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $Givenname = "Allie"
    $Surname = "McCray"
    $Displayname = "Allie McCray"
    $Name = "amccray"
    $Password = "Pass1w0rd"
    $Identity = "CN=ammccray,CN=Users,DC=contoso,DC=com"
    $SecureString = ConvertTo-SecureString $Password -AsPlainText -Force
    
    #Create the user
    New-ADUser -Name $Name -GivenName $Givenname -Surname $Surname -DisplayName $Displayname -AccountPassword $SecureString
    
    #Set the password to never expire
    Set-ADUser -Identity $Identity -PasswordNeverExpires $true -ChangePasswordAtLogon $false -Enabled $true
    ```

### AD FS の証明書を作成する

Active Directory フェデレーション サービス (AD FS) で使用する TLS または SSL 証明書が必要になります。 証明書は自己署名証明書であり、テストにのみ使用するために作成します。 運用環境では、自己署名証明書を使用しないことをお勧めします。

証明書を作成するには:

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $DNSname = "adfs.contoso.com"
    $Location = "cert:\LocalMachine\My"
    
    #Create a certificate
    New-SelfSignedCertificate -DnsName $DNSname -CertStoreLocation $Location
    ```

### Microsoft Entra テナントを作成する

お持ちでない場合は、「 [Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant) する」の記事の手順に従って、新しいテナントを作成します。

### Microsoft Entra ID でハイブリッド ID の管理者アカウントを作成する

次のタスクは、ハイブリッド ID 管理者アカウントを作成することです。 このアカウントは、Microsoft Entra Connect のインストール中に Microsoft Entra Connector アカウントを作成するために使用されます。 Microsoft Entra Connector アカウントは、Microsoft Entra ID に情報を書き込むために使用されます。

ハイブリッド ID 管理者アカウントを作成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** に移動する
3. [**新しいユーザー**] を選択&gt;**新しいユーザーを作成します**。
4. [ **新しいユーザーの作成** ] ウィンドウで、新しいユーザーの **表示名** と **ユーザー プリンシパル名** を入力します。 テナントのハイブリッド ID 管理者アカウントを作成しているところです。 一時パスワードを表示およびコピーできます。

    1. [ **割り当て]** で [ **ロールの追加]** を選択し、[ **ハイブリッド ID 管理者] を選択します**。
5. 次に、[ **確認と作成**&gt;**作成**] を選択します。
6. 新しい Web ブラウザー ウィンドウで、新しいハイブリッド ID 管理者アカウントと一時パスワードを使用して `myapps.microsoft.com` にサインインします。
7. ハイブリッド ID 管理者アカウントの新しいパスワードを選択し、パスワードを変更します。

### カスタム ドメイン名をディレクトリに追加する

テナントとハイブリッド ID 管理者を作成したので、Azure が検証できるように、カスタム ドメインを追加します。

カスタム ドメイン名をディレクトリに追加するには:

1. [[Microsoft Entra 管理センター](https://entra.microsoft.com)](https://portal.azure.com/#blade/Microsoft_AAD_IAM/ActiveDirectoryMenuBlade/Overview) で、[ **すべてのユーザー** ] ウィンドウを閉じてください。
2. 左側のメニューの [ **管理**] で、[ **カスタム ドメイン名**] を選択します。
3. [ **カスタム ドメインの追加]** を選択します。

    [Image: [カスタム ドメインの追加] ボタンが強調表示されているスクリーンショット。]
4. **[カスタム ドメイン名**] にカスタム ドメインの名前を入力し、[ドメインの**追加]** を選択します。
5. **カスタム ドメイン名**には、TXT または MX の情報が表示されます。 この情報は、ドメインのドメイン レジストラーの DNS 情報に追加する必要があります。 ドメイン レジストラーに移動して、ドメインの DNS 設定で TXT または MX 情報を入力します。

    [Image: TXT または MX 情報を取得する場所を示すスクリーンショット。] この情報をドメイン レジストラーに追加すると、Azure でドメインを確認できます。 ドメインの検証には最大 24 時間かかる場合があります。

    詳細については、 [カスタム ドメインの追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain) に関するドキュメントを参照してください。
6. ドメインが検証されていることを確認するには、[ **確認**] を選択します。

    [Image: [確認] を選択した後の成功メッセージを示すスクリーンショット。]

### Microsoft Entra Connect をダウンロードしてインストールする

ここで、Microsoft Entra Connect をダウンロードしてインストールします。 インストール後、高速インストールを使用します。

1. [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) をダウンロードします。
2. *AzureADConnect.msi* に移動し、ダブルクリックしてインストール ファイルを開きます。
3. [ **ようこそ**] で、ライセンス条項に同意するチェック ボックスをオンにし、[ **続行**] を選択します。
4. **[簡易設定**] で、[カスタマイズ] を選択**します**。
5. [ **必要なコンポーネントのインストール**] で、[インストール] を選択 **します**。
6. **[ユーザー サインイン] で**、[**AD FS とのフェデレーション**] を選択し、[**次へ**] を選択します。

    [Image: AD FS とのフェデレーションを選択する場所を示すスクリーンショット。]
7. [ **Microsoft Entra ID への接続**] で、前に作成したハイブリッド ID 管理者アカウントのユーザー名とパスワードを入力し、[ **次へ**] を選択します。
8. [ **ディレクトリの接続**] で、[ **ディレクトリの追加]** を選択します。 次に、[ **新しい AD アカウントの作成** ] を選択し、contoso\Administrator のユーザー名とパスワードを入力します。 [ **OK] を選択します**。
9. [ **次へ**] を選択します。
10. **Microsoft Entra サインイン構成で**、**すべての UPN サフィックスを検証済みドメインと照合せずに [続行**] を選択します。 [**次へ**] を選択します。
11. **ドメインと OU のフィルター処理**で、[**次へ**] を選択します。
12. [ **ユーザーを一意に識別する**] で、[ **次へ**] を選択します。
13. [ **ユーザーとデバイスのフィルター処理**] で、[ **次へ**] を選択します。
14. **オプション機能**で、[**次へ**] を選択します。
15. **ドメイン管理者の資格情報**で、contoso\Administrator のユーザー名とパスワードを入力し、[**次へ**] を選択します。
16. **AD FS ファーム**で、[**新しい AD FS ファームの構成]** が選択されていることを確認します。
17. **[フェデレーション サーバーにインストールされている証明書を使用する**] を選択し、[参照] を選択**します**。
18. 検索ボックスに「 **DC1** 」と入力し、検索結果で選択します。 [ **OK] を選択します**。
19. [ **証明書ファイル**] で、 **作成した証明書 adfs.contoso.com** 選択します。 [ **次へ**] を選択します。

    [Image: 作成した証明書ファイルを選択する場所を示すスクリーンショット。]
20. **AD FS サーバー**で、[参照] を選択します**。** 検索ボックスに「 **DC1** 」と入力し、検索結果で選択します。 [ **OK] を**選択し、[ **次へ**] を選択します。

    [Image: AD FS サーバーを選択する場所を示すスクリーンショット。]
21. **Web アプリケーション プロキシ サーバー**で、[**次へ**] を選択します。
22. **AD FS サービス アカウント**で、contoso\Administrator のユーザー名とパスワードを入力し、[**次へ**] を選択します。
23. **Microsoft Entra ドメイン**で、確認済みのカスタム ドメインを選択し、[**次へ**] を選択します。
24. [ **構成の準備完了]** で、[インストール] を選択 **します**。
25. インストールが完了したら、[終了] を選択 **します**。
26. 次に、Synchronization Service Manager または同期規則エディターを使用する前に、サインアウトしてから、もう一度サインインします。

### ポータルでユーザーを確認する

次に、オンプレミスの Active Directory テナント内のユーザーが同期され、Microsoft Entra テナントに存在することを確認します。 このセクションが完了するまでに数時間かかることがあります。

ユーザーが同期されていることを確認するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動する
3. テナントに新しいユーザーが表示されていることを確認します。

    [Image: ユーザーが Microsoft Entra ID で同期されたことを確認するスクリーンショット。]

### ユーザー アカウントでサインインして同期をテスト

Windows Server AD テナントのユーザーが Microsoft Entra テナントと同期されていることをテストするには、次のいずれかのユーザーとしてサインインします。

1. 「 https://myapps.microsoft.com 」を参照してください。
2. 新しいテナントで作成されたユーザー アカウントを使用してサインインします。

    ユーザー名には、`user@domain.onmicrosoft.com` という形式を使用します。 ユーザーがオンプレミスの Active Directory へのサインインに使用するのと同じパスワードを使用します。

ハイブリッド ID 環境を正常に設定できました。これは、Azure の機能をテストしたり理解したりするために使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tutorial-passthrough-authentication"} -->
## チュートリアル: 1 つの Active Directory フォレストでハイブリッド ID にパススルー認証を使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-passthrough-authentication
- Service: entra-id / hybrid-connect
- Article date: 2025-09-29
- Summary: パススルー認証を使用してハイブリッド ID 環境を設定し、Windows Server Active Directory フォレストと Microsoft Entra ID を統合する方法について学習します。

このチュートリアルでは、パススルー認証と Windows Server Active Directory (Windows Server AD) を使って Azure でハイブリッド ID 環境を作成する方法について説明します。 作成したハイブリッド ID 環境は、テスト目的や、ハイブリッド ID のしくみについて理解を深めるために使用できます。

[Image: パススルー認証を使って Azure でハイブリッド ID 環境を作成する方法を示す図。]

このチュートリアルでは、以下の内容を学習します。

- 仮想マシンを作成します。
- Windows Server Active Directory 環境を作成する。
- Windows Server Active Directory ユーザーを作成する。
- Microsoft Entra テナントを作成する。
- Azure でハイブリッド ID 管理者アカウントを作成する。
- カスタム ドメインをディレクトリに追加する。
- Microsoft Entra Connect を設定する。
- ユーザーが同期されていることをテストして確認する。

### 前提条件

- [Hyper-V](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/hyper-v-technology-overview) がインストールされたコンピューター。 [windows 10](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/about/supported-guest-os) または [Windows Server 2025](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/supported-windows-guest-operating-systems-for-hyper-v-on-windows) コンピューターに Hyper-V をインストールすることをお勧めします。
- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。
- 仮想マシンがインターネットに接続できるようにするための[外部ネットワーク アダプター](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/quick-start/connect-to-network)。
- Windows Server 2025 または Windows Server 2022 のコピー。
- 確認可能な[カスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)。

注

このチュートリアルでは、PowerShell スクリプトを使用して、チュートリアル環境をすばやく作成します。 各スクリプトでは、そのスクリプトの先頭で宣言された変数が使用されます。 変数は環境に合わせて変更してください。

このチュートリアルのスクリプトでは、Microsoft Entra Connect をインストールする前に、一般的な Windows Server Active Directory (Windows Server AD) 環境を作成します。 スクリプトは、関連するチュートリアルでも使用されます。

このチュートリアルで使用される PowerShell スクリプトは、[GitHub](https://github.com/billmath/tutorial-phs) で入手できます。

### 仮想マシンの作成

ハイブリッド ID 環境を作成するための最初のタスクは、オンプレミスのWindows Server AD サーバーとして使用する仮想マシンを作成することです。

注

ホスト コンピューターにおいて PowerShell でスクリプトを実行したことがない場合は、スクリプトを実行する前に、管理者として Windows PowerShell ISE 開き、`Set-ExecutionPolicy remotesigned` を実行します。 **[実行ポリシーの変更]** ダイアログで、**[はい]** を選択します。

仮想マシンを作成するには:

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $VMName = 'DC1'
    $Switch = 'External'
    $InstallMedia = 'D:\ISO\en_windows_server_2016_updated_feb_2018_x64_dvd_11636692.iso'
    $Path = 'D:\VM'
    $VHDPath = 'D:\VM\DC1\DC1.vhdx'
    $VHDSize = '64424509440'
    
    #Create a new virtual machine
    New-VM -Name $VMName -MemoryStartupBytes 16GB -BootDevice VHD -Path $Path -NewVHDPath $VHDPath -NewVHDSizeBytes $VHDSize  -Generation 2 -Switch $Switch  
    
    #Set the memory to be non-dynamic
    Set-VMMemory $VMName -DynamicMemoryEnabled $false
    
    #Add a DVD drive to the virtual machine
    Add-VMDvdDrive -VMName $VMName -ControllerNumber 0 -ControllerLocation 1 -Path $InstallMedia
    
    #Mount installation media
    $DVDDrive = Get-VMDvdDrive -VMName $VMName
    
    #Configure the virtual machine to boot from the DVD
    Set-VMFirmware -VMName $VMName -FirstBootDevice $DVDDrive 
    ```

### オペレーティング システムをインストールする

仮想マシンの作成を完了するには、オペレーティング システムをインストールします。

1. Hyper-V マネージャーで、仮想マシンをダブルクリックします。
2. **[スタート]** を選択します。
3. プロンプトで、任意のキーを押して CD または DVD から起動します。
4. Windows Server のスタート ウィンドウで、使用する言語を選択し、**[次へ]** を選択します。
5. **[今すぐインストール]** を選択します。
6. ライセンス キーを入力し、**[次へ]** を選択します。
7. **[ライセンス条項に同意します]** チェック ボックスをオンにし、**[次へ]** を選択します。
8. **[カスタム: Windows のみをインストールする (詳細設定)]** を選択します。
9. **[次へ]** を選択します。
10. インストールが完了したら、仮想マシンを再起動します。 サインインし、[Windows Update] をオンにします。 更新プログラムをインストールして、VM が完全に最新であることを確認します。

### Windows Server AD の前提条件をインストールする

Windows Server ADをインストールする前に、前提条件をインストールするスクリプトを実行します。

1. Windows PowerShell ISE を管理者として開きます。
2. `Set-ExecutionPolicy remotesigned` を実行します。 **[実行ポリシーの変更]** ダイアログで、**[すべてはい]** を選択します。
3. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $ipaddress = "10.0.1.117" 
    $ipprefix = "24" 
    $ipgw = "10.0.1.1" 
    $ipdns = "10.0.1.117"
    $ipdns2 = "8.8.8.8" 
    $ipif = (Get-NetAdapter).ifIndex 
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $newname = "DC1"
    $addsTools = "RSAT-AD-Tools" 
    
    #Set a static IP address
    New-NetIPAddress -IPAddress $ipaddress -PrefixLength $ipprefix -InterfaceIndex $ipif -DefaultGateway $ipgw 
    
    # Set the DNS servers
    Set-DnsClientServerAddress -InterfaceIndex $ipif -ServerAddresses ($ipdns, $ipdns2)
    
    #Rename the computer 
    Rename-Computer -NewName $newname -force 
    
    #Install features 
    New-Item $featureLogPath -ItemType file -Force 
    Add-WindowsFeature $addsTools 
    Get-WindowsFeature | Where installed >>$featureLogPath 
    
    #Restart the computer 
    Restart-Computer
    ```

### Windows Server AD 環境を作成する

次に、環境を作成するために Active Directory Domain Services をインストールして構成します。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $DatabasePath = "c:\windows\NTDS"
    $DomainMode = "WinThreshold"
    $DomainName = "contoso.com"
    $DomainNetBIOSName = "CONTOSO"
    $ForestMode = "WinThreshold"
    $LogPath = "c:\windows\NTDS"
    $SysVolPath = "c:\windows\SYSVOL"
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $Password = ConvertTo-SecureString "Passw0rd" -AsPlainText -Force
    
    #Install Active Directory Domain Services, DNS, and Group Policy Management Console 
    start-job -Name addFeature -ScriptBlock { 
    Add-WindowsFeature -Name "ad-domain-services" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "dns" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "gpmc" -IncludeAllSubFeature -IncludeManagementTools } 
    Wait-Job -Name addFeature 
    Get-WindowsFeature | Where installed >>$featureLogPath
    
    #Create a new Windows Server AD forest
    Install-ADDSForest -CreateDnsDelegation:$false -DatabasePath $DatabasePath -DomainMode $DomainMode -DomainName $DomainName -SafeModeAdministratorPassword $Password -DomainNetbiosName $DomainNetBIOSName -ForestMode $ForestMode -InstallDns:$true -LogPath $LogPath -NoRebootOnCompletion:$false -SysvolPath $SysVolPath -Force:$true
    ```

### Windows Server AD ユーザーを作成する

次に、テスト用のユーザー アカウントを作成します。 オンプレミスの Active Directory 環境でこのアカウントを作成します。 その後、アカウントは Microsoft Entra ID に同期されます。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $Givenname = "Allie"
    $Surname = "McCray"
    $Displayname = "Allie McCray"
    $Name = "amccray"
    $Password = "Pass1w0rd"
    $Identity = "CN=ammccray,CN=Users,DC=contoso,DC=com"
    $SecureString = ConvertTo-SecureString $Password -AsPlainText -Force
    
    #Create the user
    New-ADUser -Name $Name -GivenName $Givenname -Surname $Surname -DisplayName $Displayname -AccountPassword $SecureString
    
    #Set the password to never expire
    Set-ADUser -Identity $Identity -PasswordNeverExpires $true -ChangePasswordAtLogon $false -Enabled $true
    ```

### Microsoft Entra テナントを作成する

アカウントをお持ちではない場合は、「[Microsoft Entra ID で新しいテナントを作成する](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)」の記事の手順に従って、新しいテナントを作成してください。

### Microsoft Entra ID でハイブリッド ID 管理者を作成する

次のタスクは、ハイブリッド ID 管理者アカウントを作成することです。 このアカウントは、Microsoft Entra Connect のインストール中に Microsoft Entra Connector アカウントを作成するために使用されます。 Microsoft Entra Connector アカウントは、Microsoft Entra ID に情報を書き込むために使用されます。

ハイブリッド ID 管理者アカウントを作成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** に移動する
3. **[新しいユーザー]**&gt;**[新しいユーザーの作成]** を選択します。
4. **[新しいユーザーの作成]** ペインで、新しいユーザーの **[表示名]** と**[ユーザー プリンシパル名]**を入力します。 テナントのハイブリッド ID 管理者アカウントを作成しているところです。 一時パスワードを表示およびコピーできます。
    1. **[割り当て]** で **[ロールの追加]** を選択し、**[ハイブリッド ID の管理者]** を選びます。
5. 次に、**[確認と作成]**&gt;**[作成]** の順に選択します。
6. 新しい Web ブラウザー ウィンドウで、新しいハイブリッド ID 管理者アカウントと一時パスワードを使用して `myapps.microsoft.com` にサインインします。

### カスタム ドメイン名をディレクトリに追加する

テナントとハイブリッド ID 管理者を作成したので、Azure が検証できるように、カスタム ドメインを追加します。

カスタム ドメイン名をディレクトリに追加するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に移動します。
2. **Entra ID**&gt;** ドメイン名**を参照します。
3. **[カスタム ドメインの追加]** を選択します。

    [Image: [カスタムドメインの追加] ボタンが強調表示されているスクリーンショット。]
4. **[Custom domain names](カスタム ドメイン名)** で、カスタム ドメインの名前を入力し、**[Add domain](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/ドメインの追加)** を選択します。
5. **[カスタム ドメイン名]** に、TXT または MX の情報が表示されます。 この情報は、ドメインのドメイン レジストラーの DNS 情報に追加する必要があります。 ドメイン レジストラーに移動して、ドメインの DNS 設定で TXT または MX 情報を入力します。

    [Image: TXT または MX 情報を取得する場所を示すスクリーンショット。]この情報をドメイン レジストラーに追加すると、Azure がドメインを検証できるようになります。 ドメインの検証には最大 24 時間かかる場合があります。

    詳細については、[カスタム ドメインの追加](https://learn.microsoft.com/ja-jp/entra/fundamentals/add-custom-domain)に関するドキュメントを参照してください。
6. ドメインが検証されていることを確認するには、**[確認する]** を選択します。

    [Image: [確認する] を選択した後の成功メッセージが表示されているスクリーンショット。]

#### Microsoft Entra Connect をダウンロードしてインストールする

ここで、Microsoft Entra Connect をダウンロードしてインストールします。 インストール後、高速インストールを使用します。

1. [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) をダウンロードします。
2. *AzureADConnect.msi* に移動し、ダブルクリックしてインストール ファイルを開きます。
3. **[開始]** で、ライセンス条項に同意するチェック ボックスをオンにし、**[続行]** を選択します。
4. **[Express settings](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/簡単設定)** で、**[カスタマイズする]** を選択 します。
5. **[必要なコンポーネントのインストール]** で、**[インストール]** を選択します。
6. **[ユーザー サインイン]** で、**[パススルー認証]** と **[シングル サインオンを有効にする]** を選んでから、**[次へ]** を選びます。
7. **[Connect to Microsoft Entra ID] (Microsoft Entra ID に接続)** で、先ほど作成したハイブリッド ID の管理者アカウントのユーザー名とパスワードを入力し、**[次へ]** を選択します。
8. **[Connect your directories](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/ディレクトリの接続)** で、**[ディレクトリの追加]** を選択します。 次に **[新しい AD アカウントを作成]** を選択し、contoso\Administrator のユーザー名とパスワードを入力します。 **[OK]** を選択します。
9. **[次へ]** を選択します。
10. **[Microsoft Entra sign-in configuration] (Microsoft Entra サインインの構成)** で、**[一部の UPN サフィックスが確認済みドメインに一致していなくても続行する]** を選択します。 **[次へ]** を選択します。
11. **[ドメインと OU のフィルタリング]** で、**[次へ]** を選択します。
12. **[一意のユーザー識別]** で、**[次へ]** を選択します。
13. **[ユーザーおよびデバイスのフィルタリング]** で、**[次へ]** を選択します。
14. **[Optional features](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/オプション機能)** で、**[次へ]** を選択します。
15. **[Enable single sign-on credentials] (シングル サインオン資格情報を有効にする)** で、ユーザー名とパスワードに contoso\Administrator と入力して、**[次へ]** を選びます。
16. **[構成の準備完了]** で、**[インストール]** を選択します。
17. インストールが完了したら、**[終了]** をクリックします。
18. 次に、Synchronization Service Manager または同期規則エディターを使用する前に、サインアウトしてから、もう一度サインインします。

### ポータルでユーザーを確認する

次に、オンプレミスの Active Directory テナント内のユーザーが同期され、Microsoft Entra テナントに存在することを確認します。 このセクションが完了するまでに数時間かかることがあります。

ユーザーが同期されていることを確認するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** に移動する
3. テナントに新しいユーザーが表示されていることを確認します。

    [Image: Microsoft Entra ID でユーザーが同期されたことを確認している様子を示すスクリーンショット。]

### ユーザー アカウントでサインインして同期をテスト

Windows Server AD テナントのユーザーが Microsoft Entra テナントと同期されていることをテストするには、次のいずれかのユーザーとしてサインインします。

1. 「 https://myapps.microsoft.com 」を参照してください。
2. 新しいテナントで作成されたユーザー アカウントを使用してサインインします。

    ユーザー名には、`user@domain.onmicrosoft.com` という形式を使用します。 ユーザーがオンプレミスの Active Directory へのサインインに使用するのと同じパスワードを使用します。

ハイブリッド ID 環境を正常に設定できました。これは、Azure の機能をテストしたり理解したりするために使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tutorial-password-hash-sync"} -->
## チュートリアル: 1 つの Active Directory フォレストでハイブリッド ID のためにパスワード ハッシュの同期を使用する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-password-hash-sync
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: パスワード ハッシュの同期を使用して Windows Server Active Directory フォレストを Microsoft Entra ID と統合することで、ハイブリッド ID 環境を設定する方法について学習します。

このチュートリアルでは、パスワード ハッシュの同期と Windows Server Active Directory (Windows Server AD) を使用して Azure でハイブリッド ID 環境を作成する方法について説明します。 作成したハイブリッド ID 環境は、テスト目的や、ハイブリッド ID のしくみについて理解を深めるために使用できます。

[Image: パスワード ハッシュ同期を使用して Azure でハイブリッド ID 環境を作成する方法を示す図。]

このチュートリアルでは、以下の内容を学習します。

- 仮想マシンを作成します。
- Windows Server Active Directory 環境を作成する。
- Windows Server Active Directory ユーザーを作成する。
- Microsoft Entra テナントを作成する。
- Azure でハイブリッド ID 管理者アカウントを作成する。
- Microsoft Entra Connect を設定する。
- ユーザーが同期されていることをテストして確認する。

### 前提条件

- [Hyper-V](https://learn.microsoft.com/ja-jp/windows-server/virtualization/hyper-v/hyper-v-technology-overview) がインストールされているコンピューター。 [windows 10 または Windows Server 2016](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/about/supported-guest-os) コンピューターに Hyper-V をインストールすることをお勧めします。
- Azure サブスクリプション。 Azure サブスクリプションをお持ちでない場合は、開始する前に [無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn) を作成してください。
- [外部ネットワーク アダプター](https://learn.microsoft.com/ja-jp/virtualization/hyper-v-on-windows/quick-start/connect-to-network)。仮想マシンがインターネットに接続できるようにします。
- Windows Server 2016 のコピー。

注

このチュートリアルでは、PowerShell スクリプトを使用して、チュートリアル環境をすばやく作成します。 各スクリプトでは、そのスクリプトの先頭で宣言された変数が使用されます。 変数は環境に合わせて変更してください。

このチュートリアルのスクリプトでは、Microsoft Entra Connect をインストールする前に、一般的な Windows Server Active Directory (Windows Server AD) 環境を作成します。 スクリプトは、関連するチュートリアルでも使用されます。

このチュートリアルで使用する PowerShell スクリプトは、 [GitHub](https://github.com/billmath/tutorial-phs) で入手できます。

### 仮想マシンの作成

ハイブリッド ID 環境を作成するための最初のタスクは、オンプレミスのWindows Server AD サーバーとして使用する仮想マシンを作成することです。

注

ホスト コンピューターにおいて PowerShell でスクリプトを実行したことがない場合は、スクリプトを実行する前に、管理者として Windows PowerShell ISE 開き、`Set-ExecutionPolicy remotesigned` を実行します。 [ **実行ポリシーの変更** ] ダイアログで、[ **はい**] を選択します。

仮想マシンを作成するには:

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $VMName = 'DC1'
    $Switch = 'External'
    $InstallMedia = 'D:\ISO\en_windows_server_2016_updated_feb_2018_x64_dvd_11636692.iso'
    $Path = 'D:\VM'
    $VHDPath = 'D:\VM\DC1\DC1.vhdx'
    $VHDSize = '64424509440'
    
    #Create a new virtual machine
    New-VM -Name $VMName -MemoryStartupBytes 16GB -BootDevice VHD -Path $Path -NewVHDPath $VHDPath -NewVHDSizeBytes $VHDSize  -Generation 2 -Switch $Switch  
    
    #Set the memory to be non-dynamic
    Set-VMMemory $VMName -DynamicMemoryEnabled $false
    
    #Add a DVD drive to the virtual machine
    Add-VMDvdDrive -VMName $VMName -ControllerNumber 0 -ControllerLocation 1 -Path $InstallMedia
    
    #Mount installation media
    $DVDDrive = Get-VMDvdDrive -VMName $VMName
    
    #Configure the virtual machine to boot from the DVD
    Set-VMFirmware -VMName $VMName -FirstBootDevice $DVDDrive 
    ```

### オペレーティング システムをインストールする

仮想マシンの作成を完了するには、オペレーティング システムをインストールします。

1. Hyper-V マネージャーで、仮想マシンをダブルクリックします。
2. **[スタート] を選択します**。
3. プロンプトで、任意のキーを押して CD または DVD から起動します。
4. Windows Server のスタート ウィンドウで、言語を選択し、[ **次へ**] を選択します。
5. [ **今すぐインストール]** を選択します。
6. ライセンス キーを入力し、[ **次へ**] を選択します。
7. [ **ライセンス条項に同意する** ] チェック ボックスをオンにし、[ **次へ**] を選択します。
8. [ **カスタム: Windows のみをインストールする (詳細)]**を選択します。
9. [ **次へ**] を選択します。
10. インストールが完了したら、仮想マシンを再起動します。 サインインし、[Windows Update] をオンにします。 更新プログラムをインストールして、VM が完全に最新であることを確認します。

### Windows Server AD の前提条件をインストールする

Windows Server ADをインストールする前に、前提条件をインストールするスクリプトを実行します。

1. Windows PowerShell ISE を管理者として開きます。
2. `Set-ExecutionPolicy remotesigned` を実行します。 **[実行ポリシーの変更**] ダイアログで、[**はい] を [すべて] に**選択します。
3. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $ipaddress = "10.0.1.117" 
    $ipprefix = "24" 
    $ipgw = "10.0.1.1" 
    $ipdns = "10.0.1.117"
    $ipdns2 = "4.2.2.2" 
    $ipif = (Get-NetAdapter).ifIndex 
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $newname = "DC1"
    $addsTools = "RSAT-AD-Tools" 
    
    #Set a static IP address
    New-NetIPAddress -IPAddress $ipaddress -PrefixLength $ipprefix -InterfaceIndex $ipif -DefaultGateway $ipgw 
    
    # Set the DNS servers
    Set-DnsClientServerAddress -InterfaceIndex $ipif -ServerAddresses ($ipdns, $ipdns2)
    
    #Rename the computer 
    Rename-Computer -NewName $newname -force 
    
    #Install features 
    New-Item $featureLogPath -ItemType file -Force 
    Add-WindowsFeature $addsTools 
    Get-WindowsFeature | Where installed >>$featureLogPath 
    
    #Restart the computer 
    Restart-Computer
    ```

### Windows Server AD 環境を作成する

次に、環境を作成するために Active Directory Domain Services をインストールして構成します。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $DatabasePath = "c:\windows\NTDS"
    $DomainMode = "WinThreshold"
    $DomainName = "contoso.com"
    $DomainNetBIOSName = "CONTOSO"
    $ForestMode = "WinThreshold"
    $LogPath = "c:\windows\NTDS"
    $SysVolPath = "c:\windows\SYSVOL"
    $featureLogPath = "c:\poshlog\featurelog.txt" 
    $Password = "Pass1w0rd"
    $SecureString = ConvertTo-SecureString $Password -AsPlainText -Force
    
    #Install Active Directory Domain Services, DNS, and Group Policy Management Console 
    start-job -Name addFeature -ScriptBlock { 
    Add-WindowsFeature -Name "ad-domain-services" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "dns" -IncludeAllSubFeature -IncludeManagementTools 
    Add-WindowsFeature -Name "gpmc" -IncludeAllSubFeature -IncludeManagementTools } 
    Wait-Job -Name addFeature 
    Get-WindowsFeature | Where installed >>$featureLogPath
    
    #Create a new Windows Server AD forest
    Install-ADDSForest -CreateDnsDelegation:$false -DatabasePath $DatabasePath -DomainMode $DomainMode -DomainName $DomainName -SafeModeAdministratorPassword $SecureString -DomainNetbiosName $DomainNetBIOSName -ForestMode $ForestMode -InstallDns:$true -LogPath $LogPath -NoRebootOnCompletion:$false -SysvolPath $SysVolPath -Force:$true
    ```

### Windows Server AD ユーザーを作成する

次に、テスト用のユーザー アカウントを作成します。 オンプレミスの Active Directory 環境でこのアカウントを作成します。 その後、アカウントは Microsoft Entra ID に同期されます。

1. Windows PowerShell ISE を管理者として開きます。
2. 次のスクリプトを実行します。

    ```powershell
    #Declare variables
    $Givenname = "Allie"
    $Surname = "McCray"
    $Displayname = "Allie McCray"
    $Name = "amccray"
    $Password = "Pass1w0rd"
    $Identity = "CN=ammccray,CN=Users,DC=contoso,DC=com"
    $SecureString = ConvertTo-SecureString $Password -AsPlainText -Force
    
    #Create the user
    New-ADUser -Name $Name -GivenName $Givenname -Surname $Surname -DisplayName $Displayname -AccountPassword $SecureString
    
    #Set the password to never expire
    Set-ADUser -Identity $Identity -PasswordNeverExpires $true -ChangePasswordAtLogon $false -Enabled $true
    ```

### Microsoft Entra テナントを作成する

お持ちでない場合は、「 [Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant) する」の記事の手順に従って、新しいテナントを作成します。

### Microsoft Entra ID でハイブリッド ID 管理者を作成する

次のタスクは、ハイブリッド ID 管理者アカウントを作成することです。 このアカウントは、Microsoft Entra Connect のインストール中に Microsoft Entra Connector アカウントを作成するために使用されます。 Microsoft Entra Connector アカウントは、Microsoft Entra ID に情報を書き込むために使用されます。

ハイブリッド ID 管理者アカウントを作成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Users** にアクセスする
3. [**新しいユーザー**] を選択&gt;**新しいユーザーを作成します**。
4. [ **新しいユーザーの作成** ] ウィンドウで、新しいユーザーの **表示名** と **ユーザー プリンシパル名**を入力します。 テナントのハイブリッド ID 管理者アカウントを作成しているところです。 一時パスワードを表示およびコピーできます。
    1. [ **割り当て]** で [ **ロールの追加]** を選択し、[ **ハイブリッド ID 管理者] を選択します**。
5. 次に、[ **確認と作成**&gt;**作成**] を選択します。
6. 新しい Web ブラウザー ウィンドウで、新しいハイブリッド ID 管理者アカウントと一時パスワードを使用して `myapps.microsoft.com` にサインインします。

### Microsoft Entra Connect をダウンロードしてインストールする

ここで、Microsoft Entra Connect をダウンロードしてインストールします。 インストール後、高速インストールを使用します。

1. [Microsoft Entra Connect](https://www.microsoft.com/download/details.aspx?id=47594) をダウンロードします。
2. *AzureADConnect.msi* に移動し、ダブルクリックしてインストール ファイルを開きます。
3. [ **ようこそ**] で、チェックボックスをオンにしてライセンス条項に同意し、[続行] を選択 **します**。
4. **[高速設定]** で、[**高速設定を使用**] を選択します。
5. [ **Microsoft Entra ID への接続] で、Microsoft Entra ID** のハイブリッド ID 管理者アカウントのユーザー名とパスワードを入力します。 [ **次へ**] を選択します。
6. [ **AD DS への接続**] で、エンタープライズ管理者アカウントのユーザー名とパスワードを入力します。 [ **次へ**] を選択します。
7. [ **構成の準備完了]** で、[インストール] を選択 **します**。
8. インストールが完了したら、[終了] を選択 **します**。
9. 次に、Synchronization Service Manager または同期規則エディターを使用する前に、サインアウトしてから、もう一度サインインします。

### ポータルでユーザーを確認する

次に、オンプレミスの Active Directory テナント内のユーザーが同期され、Microsoft Entra テナントに存在することを確認します。 このセクションが完了するまでに数時間かかることがあります。

ユーザーが同期されていることを確認するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ハイブリッド ID 管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#hybrid-identity-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** にアクセスする
3. テナントに新しいユーザーが表示されていることを確認します。

    [Image: ユーザーが Microsoft Entra ID で同期されたことを確認するスクリーンショット。]

### ユーザー アカウントでサインインして同期をテスト

Windows Server AD テナントのユーザーが Microsoft Entra テナントと同期されていることをテストするには、次のいずれかのユーザーとしてサインインします。

1. 「 https://myapps.microsoft.com 」を参照してください。
2. 新しいテナントで作成されたユーザー アカウントを使用してサインインします。

    ユーザー名には、`user@domain.onmicrosoft.com` という形式を使用します。 ユーザーがオンプレミスの Active Directory へのサインインに使用するのと同じパスワードを使用します。

ハイブリッド ID 環境を正常に設定できました。これは、Azure の機能をテストしたり理解したりするために使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/tutorial-phs-backup"} -->
## チュートリアル: Microsoft Entra Connect で AD FS のバックアップとしてパスワード ハッシュ同期を設定する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-phs-backup
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra Connect で Azure Directory フェデレーション サービス (AD FS) のバックアップとして、パスワード ハッシュ同期をオンにする方法について学習します。

このチュートリアルでは、Microsoft Entra Connect で Azure Directory フェデレーション サービス (AD FS) のバックアップおよびフェールオーバーとして、パスワード ハッシュ同期を設定する手順について説明します。 このチュートリアルでは、AD FS で障害が発生した場合や使用できなくなった場合に、パスワード ハッシュ同期をプライマリ認証方法として設定する方法についても説明します。

注記

通常、これらの手順は緊急または障害の状況時に実行されますが、障害が発生する前に、これらの手順をテストして、手順を確認しておくことをお勧めします。

### 前提条件

このチュートリアルは、「[チュートリアル: 1 つの Active Directory フォレストでハイブリッド ID にフェデレーションを使用する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-federation)」に基づいています。 そのチュートリアルを完了することは、このチュートリアルの手順を完了するための前提条件です。

注記

Microsoft Entra Connect サーバーにアクセスできない場合、またはそのサーバーがインターネットにアクセスできない場合は、[Microsoft サポート](https://support.microsoft.com/contactus/)に連絡して、Microsoft Entra ID の変更を支援してもらうことができます。

### Microsoft Entra ID Connect でパスワード ハッシュ同期を有効にする

「[チュートリアル: 1 つの Active Directory フォレストでハイブリッド ID にフェデレーションを使用する](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-federation)」では、フェデレーションを使用する Microsoft Entra Connect 環境を作成しました。

フェデレーションのバックアップを設定する最初の手順は、以下のように、パスワード ハッシュ同期をオンにし、Microsoft Entra Connect を設定してハッシュを同期することです。

1. インストール中にデスクトップに作成された Microsoft Entra Connect アイコンをダブルクリックします。
2. **[構成]** をクリックします。
3. **[追加のタスク]** で **[同期オプションのカスタマイズ]** を選んで、**[次へ]** を選びます。

    [Image: [同期オプションのカスタマイズ] が選択されている [追加のタスク] ペインを示すスクリーンショット。]
4. フェデレーションを設定するためにチュートリアルで[作成したハイブリッド ID 管理者アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-federation#create-a-hybrid-identity-administrator-account-in-azure-ad) のユーザー名とパスワードを入力します。
5. **[ディレクトリの接続]** で、**[次へ]** を選択します。
6. **[ドメインと OU のフィルタリング]** で、**[次へ]** を選択します。
7. **[オプション機能]** で **[パスワード ハッシュ同期]** を選択し、**[次へ]** を選択します。

    [Image: [パスワード ハッシュ同期] が選択されている [オプション機能] ペインのスクリーンショット。]
8. **[構成の準備完了]** で、 **[構成]** を選択します。
9. 構成が完了したら、**[終了]** を選択します。

これで完了です。 以上で完了です。 パスワード ハッシュの同期が行われるようになり、AD FS が使用できなくなった場合のバックアップとして使用できます。

### パスワード ハッシュ同期への切り替え

重要

- パスワード ハッシュ同期に切り替える前に、AD FS 環境のバックアップを作成します。 [AD FS の迅速な復元ツール](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/ad-fs-rapid-restore-tool#how-to-use-the-tool)を使用して、バックアップを作成できます。
- パスワード ハッシュが Microsoft Entra ID に同期されるまでしばらく時間がかかります。 同期が完了するまでに最大 3 時間かかる場合があります。パスワード ハッシュを使用して認証を開始できます。

次に、パスワード ハッシュ同期に切り替えます。 開始する前に、どのような状況で切り替えるべきかを検討してください。 ネットワークの機能停止、軽微な AD FS の問題、ユーザーの一部に影響する問題など、一時的な理由で切り替えることは避けてください。

問題の解消に時間がかかりすぎることから切り替えに踏み切る場合は、以下の手順を完了します。

1. Microsoft Entra Connect で **[構成]** を選択します。
2. **[ユーザー サインインの変更]** を選択し、 **[次へ]** を選択します。
3. フェデレーションを設定するためにチュートリアルで[作成したハイブリッド ID 管理者アカウント](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/tutorial-federation#create-a-hybrid-identity-administrator-account-in-azure-ad) のユーザー名とパスワードを入力します。
4. **[ユーザー サインイン]** で **[パスワード ハッシュ同期]** を選択し、**[ユーザー アカウントを変換しない]** チェック ボックスを選択します。
5. **[シングル サインオンを有効にする]** を選択状態 (既定) のままにし、**[次へ]** を選択します。
6. **[シングル サインオンを有効にする]** で、**[次へ]** を選択します。
7. **[構成の準備完了]** で、 **[構成]** を選択します。
8. 構成が完了したら、**[終了]** を選択します。

これで、ユーザーが自分のパスワードを使用して Azure および Azure サービスにサインインできるようになりました。

### ユーザー アカウントでサインインして同期をテスト

1. 新しい Web ブラウザー ウィンドウで https://myapps.microsoft.com に移動します。
2. 新しいテナントで作成されたユーザー アカウントを使用してサインインします。

    ユーザー名には、`user@domain.onmicrosoft.com` という形式を使用します。 ユーザーがオンプレミスの Active Directory へのサインインに使用するのと同じパスワードを使用します。

    [Image: サインイン テスト時の成功メッセージを示すスクリーンショット。]

### フェデレーションに切り替える

次に、フェデレーションに切り替えます。

1. Microsoft Entra Connect で **[構成]** を選択します。
2. **[ユーザー サインインの変更]** を選択し、 **[次へ]** を選択します。
3. ハイブリッド ID の管理者アカウントのユーザー名とパスワードを入力します。
4. **[ユーザー サインイン]** で **[AD FS とのフェデレーション]** を選択してから、**[次へ]** を選択します。
5. **[ドメイン管理者の資格情報]** で contoso\Administrator のユーザー名とパスワードを入力してから、**[次へ]** をクリックします。
6. **AD FS ファーム**で、**[次へ]** を選択します。
7. **[Microsoft Entra ドメイン]** で、ドメインを選び、**[次へ]** を選択します。
8. **[構成の準備完了]** で、 **[構成]** を選択します。
9. 構成が完了したら、**[次へ]** を選択します。

    [Image: [構成が完了しました] ペインを示すスクリーンショット。]
10. **[フェデレーションの接続性の検証]** で **[確認]** を選択します。 正常に完了するには、DNS レコードを構成 (A および AAAA レコードを追加) する必要がある場合があります。

    [Image: [フェデレーションの接続性の検証] ダイアログと [検証] ボタンを示すスクリーンショット。]
11. **[終了]** を選択します。

### AD FS と Azure の信頼をリセットする

最後のタスクは、AD FS と Azure の間の信頼のリセットです。

1. Microsoft Entra Connect で **[構成]** を選択します。
2. **[フェデレーションの管理]** を選択してから、**[次へ]** を選択します。
3. **[Microsoft Entra ID 信頼のリセット]** を選んでから、**[次へ]** を選択します。

    [Image: Microsoft Entra ID のリセットが選択されている、[フェデレーションの管理] ペインを示すスクリーンショット。]
4. **[Microsoft Entra ID に接続]** で、ハイブリッド ID 管理者アカウントのユーザー名とパスワードを入力します。
5. **[AD FS に接続]** で contoso\Administrator のユーザー名とパスワードを入力してから、**[次へ]** を選択します。
6. **[証明書]** で **[次へ]** を選択します。
7. 「ユーザー アカウントでサインインして同期をテスト」の手順を繰り返します。

ハイブリッド ID 環境を正常に設定できました。これは、Azure で提供されるものをテストしたり習熟したりするために使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/whatis-aadc-admin-agent"} -->
## Microsoft Entra Connect 管理エージェントとは - Microsoft Entra Connect - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-aadc-admin-agent
- Service: entra-id / hybrid-connect
- Article date: 2025-04-09
- Summary: Microsoft Entra ID を使用してオンプレミス環境を同期および監視するために使用されるツールについて説明します。

Microsoft Entra Connect 管理エージェントは、Microsoft Entra Connect サーバーにインストールできる Microsoft Entra Connect のコンポーネントです。 エージェントは、ハイブリッド Active Directory 環境から特定のデータを収集するために使用されます。 収集されたデータは、Microsoft サポート エンジニアがサポート ケースを開くときに問題のトラブルシューティングを行う際に役立ちます。

手記

Microsoft Entra Connect 管理エージェントは Microsoft Entra Connect インストールの一部ではなくなり、Microsoft Entra Connect バージョン 2.1.12.0 以降では使用できません。

Microsoft Entra Connect 管理エージェントは、Microsoft Entra ID からのデータに対する特定の要求を待機します。 エージェントは、要求されたデータを同期環境から取得し、Microsoft Entra ID に送信します。この ID は、Microsoft サポート エンジニアに提示されます。

Microsoft Entra Connect 管理エージェントが環境から取得する情報は保存されません。 この情報は、Microsoft Entra Connect 関連のサポート ケースの調査とトラブルシューティングを支援するために、Microsoft サポート エンジニアにのみ表示されます。

既定では、Microsoft Entra Connect 管理エージェントは Microsoft Entra Connect サーバーにはインストールされません。 サポート ケースを支援するには、エージェントをインストールしてデータを収集する必要があります。

### Microsoft Entra Connect 管理エージェントをインストールする

Microsoft Entra Connect 管理エージェントを Microsoft Entra Connect サーバーにインストールするには、まずいくつかの前提条件を満たしていることを確認してから、エージェントをインストールします。

前提 条件：

- Microsoft Entra Connect がサーバーにインストールされています。
- Microsoft Entra Connect Health がサーバーにインストールされています。

[Image: サーバー上の管理エージェントを示すスクリーンショット。]

Microsoft Entra Connect 管理エージェント のバイナリは、Microsoft Entra Connect サーバーに配置されます。

エージェントをインストールするには:

1. 管理者として PowerShell を開きます。
2. アプリケーションが配置されているディレクトリに移動します:`cd "C:\Program Files\Microsoft Azure Active Directory Connect\Tools"`。
3. `ConfigureAdminAgent.ps1`を実行します。

メッセージが表示されたら、Microsoft Entra ハイブリッド ID 管理者の資格情報を入力します。 これらの資格情報は、Microsoft Entra Connect のインストール時に入力した資格情報と同じである必要があります。

手記

`ConfigureAdminAgent.ps1` スクリプトは、Microsoft Entra Connect バージョン 2.1.12.0 以降には含まれません。 Microsoft Entra Connect 管理エージェント自体は非推奨であり、これらのバージョンにはインストールできません。

エージェントがインストールされると、サーバーのコントロール パネルの [プログラムの追加と削除] **に、次の 2 つの新しいプログラム** 表示されます。

[Image: 追加した新しいプログラムを含む [プログラムの追加と削除] の一覧を示すスクリーンショット。]

### Microsoft サポート エンジニアに表示される同期サービス内のデータは何ですか?

サポート ケースを開くと、Microsoft サポート エンジニアは、特定のユーザーに関するこの情報を確認できます。

- Windows Server Active Directory (Windows Server AD) の関連データ。
- Microsoft Entra Connect サーバー上の Windows Server AD コネクタ スペース。
- Microsoft Entra Connect サーバー上の Microsoft Entra コネクタ スペース。
- Microsoft Entra Connect サーバーのメタバース。

Microsoft サポート エンジニアは、システム内のデータを変更することはできません。また、パスワードを表示することもできません。

### Microsoft サポート エンジニアが自分のデータにアクセスしたくない場合はどうすればよいですか?

エージェントをインストールした後、Microsoft サポート エンジニアがサポート呼び出しのデータにアクセスできないようにする場合は、サービス構成ファイルを変更して機能を無効にすることができます。

1. メモ帳で、*C:\Program Files\Microsoft Azure AD Connect Administration Agent\AzureADConnectAdministrationAgentService.exe.config*を開きます。
2. 次の例に示すように、**UserDataEnabled** 設定を無効にします。 **UserDataEnabled** 設定が存在し、**true**に設定されている場合は、**false**に設定してください。 設定が存在しない場合は、設定を追加します。

    ```xml
    <appSettings>
      <add key="TraceFilename" value="ADAdministrationAgent.log" />
      <add key="UserDataEnabled" value="false" />
    </appSettings>
    ```
3. 構成ファイルを保存します。
4. 次の図に示すように、Microsoft Entra Connect 管理エージェント サービスを再起動します。

    [Image: Microsoft Entra Connect Administrator Agent サービスを再起動する方法を示すスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/hybrid/connect/whatis-azure-ad-connect"} -->
## Microsoft Entra Connect と Connect Health とは - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect
- Service: entra-id / hybrid-connect
- Article date: 2026-09-10
- Summary: Microsoft Entra ID を使用してオンプレミス環境を同期および監視するために使用されるツールについて説明します。

Microsoft Entra Connect は、ハイブリッド ID の目標を達成するために設計されたオンプレミスの Microsoft アプリケーションです。 目標を最も適切に満たす方法を評価する場合は、Microsoft Entra Cloud Syncクラウドマネージド ソリューションも検討する必要があります。

重要

Azure AD Connect V1 は 2022 年 8 月 31 日の時点で廃止され、サポートされなくなりました。 Azure AD Connect V1 のインストールは、**予期せず動作しなくなる**可能性があります。 まだ Azure AD Connect V1 を使用している場合は、すぐに Microsoft Entra Connect V2 にアップグレードする必要があります。

### Microsoft Entra Cloud Sync への移行を検討する

Microsoft Entra Cloud Sync は、Microsoft の同期の未来です。 Microsoft Entra Connect が置き換えられます。

Microsoft Entra Connect V2.0 に移行する前に、クラウド同期への移行を検討する必要があります。クラウド同期が適切かどうかを確認するために、ポータルから [同期の確認ツール](https://aka.ms/M365Wizard) にアクセスするか、提供されたリンクを使用します。

詳細については、「[クラウド同期とは」を参照してください。](https://learn.microsoft.com/ja-jp/azure/active-directory/cloud-sync/what-is-cloud-sync)

### Microsoft Entra Connect の機能

- [パスワード ハッシュ同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-phs) - オンプレミス AD パスワードのユーザーのハッシュを Microsoft Entra ID と同期するサインイン方法。
- [パススルー認証](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-pta) - ユーザーがオンプレミスとクラウドで同じパスワードを使用できるが、フェデレーション環境の追加のインフラストラクチャは必要としないサインイン方法。
- [フェデレーション統合](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-whatis) - フェデレーションは Microsoft Entra Connect のオプションの部分であり、オンプレミスの AD FS インフラストラクチャを使用してハイブリッド環境を構成するために使用できます。 また、証明書の更新や追加の AD FS サーバーの展開などの AD FS 管理機能も提供します。
- [同期](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) - ユーザー、グループ、およびその他のオブジェクトの作成を担当します。 また、オンプレミスのユーザーとグループの ID 情報がクラウドと一致していることを確認します。 この同期には、パスワード ハッシュも含まれます。
- [稼働状況の監視](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/whatis-azure-ad-connect#what-is-azure-ad-connect-health) - Microsoft Entra Connect Health は、堅牢な監視を提供し、[Microsoft Entra 管理センター](https://entra.microsoft.com)でこのアクティビティを表示するための一元的な場所を提供します。

[Image: Microsoft Entra Connect] とは

重要

Microsoft Entra Connect Health for Sync には、Microsoft Entra Connect Sync V2 が必要です。 Azure AD Connect V1 をまだ使用している場合は、最新バージョンにアップグレードする必要があります。 Azure AD Connect V1 は、2022 年 8 月 31 日に廃止されます。 Microsoft Entra Connect Health for Sync は、2022 年 12 月に Azure AD Connect V1 と連携しなくなります。

### Microsoft Entra Connect Health とは

Microsoft Entra Connect Health は、オンプレミスの ID インフラストラクチャの監視を提供します。 これにより、主要な ID コンポーネントの正常性データを 1 か所で使用できるようになるため、Microsoft 365および Microsoft Online Services への信頼性の高い接続を維持できます。

[Microsoft Entra 管理センターで Microsoft Entra Connect Health](https://aka.ms/aadconnecthealth) を開き、アラート、パフォーマンス監視、使用状況分析、同期エラー、サービス情報を表示します。 左側のメニューを使用して、Microsoft Entra Connect Sync、AD FS、AD DS 監視エクスペリエンス間を移動します。

[Image: Microsoft Entra Connect Health] とは

### Microsoft Entra Connect を使用する理由

オンプレミスのディレクトリを Microsoft Entra ID と統合すると、クラウドとオンプレミスの両方のリソースにアクセスするための共通 ID を提供することで、ユーザーの生産性が向上します。 ユーザーと組織は、次の利点を活用できます。

- ユーザーは、1 つの ID を使用して、オンプレミスのアプリケーションや Microsoft 365 などのクラウド サービスにアクセスできます。
- 同期とサインインのための簡単な展開エクスペリエンスを提供する 1 つのツール。
- シナリオの最新の機能を提供します。 Microsoft Entra Connect は、DirSync や Azure AD Sync などの以前のバージョンの ID 統合ツールを置き換えます。詳細については、「ハイブリッド ID ディレクトリ統合ツールの比較を参照してください。

### Microsoft Entra Connect Health を使用する理由

Microsoft Entra ID を使用して認証する場合、クラウドとオンプレミスの両方のリソースにアクセスするための共通 ID があるため、ユーザーの生産性が向上します。 ユーザーがこれらのリソースにアクセスできるように、環境の信頼性を確保することは困難になります。 Microsoft Entra Connect Health は、オンプレミスの ID インフラストラクチャの監視と分析情報の取得に役立ち、この環境の信頼性を確保します。 オンプレミスの各 ID サーバーにエージェントをインストールするのと同じくらい簡単です。

Microsoft Entra Connect Health for AD FS は、Windows Server 2012 R2、Windows Server 2016、Windows Server 2019、Windows Server 2022、および Windows Server 2025 の AD FS をサポートしています。 また、エクストラネット アクセスの認証サポートを提供する Web アプリケーション プロキシ サーバーの監視もサポートしています。 Microsoft Entra Connect Health for AD FS では、ヘルス エージェントを簡単かつ迅速にインストールすることで、一連の主要な機能を利用できます。

主な利点とベスト プラクティス:

| 主な利点 | ベスト プラクティス |
| --- | --- |
| セキュリティの強化 | [エクストラネット ロックアウトのトレンド](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs#usage-analytics-for-ad-fs)[失敗したサインインのレポート](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs-risky-ip)[プライバシー準拠](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/reference-connect-health-user-privacy) |
| [すべての重大な ADFS システムの問題](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-alert-catalog#alerts-for-active-directory-federation-services)に関するアラートを受信する | サーバーの構成と可用性[パフォーマンスと接続](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs#performance-monitoring-for-ad-fs)定期的なメンテナンス |
| デプロイと管理が簡単 | [クイック エージェントインストール](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-agent-install#install-the-agent-for-ad-fs)エージェントがポータルで使用可能な最新のデータに数分で自動アップグレードされます |
| 豊富な[使用状況メトリック](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs#usage-analytics-for-ad-fs) | ネットワークの場所と TCP 接続サーバーごとのトークン要求アプリケーションの使用率の上位 |
| 優れたユーザー エクスペリエンス | Microsoft Entra 管理センター [のダッシュボードデザイン](https://entra.microsoft.com)[アラートは電子メールを通じて提供されます](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-health-adfs#alerts-for-ad-fs) |

### Microsoft Entra Connect を使用するためのライセンス要件

この機能の使用は無料で、Azure サブスクリプションに含まれています。

### Microsoft Entra Connect Health を使用するためのライセンス要件

この機能を使用するには、Microsoft Entra ID P1 ライセンスが必要です。 要件に適したライセンスを見つけるには、「[Microsoft Entra ID](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing)の一般公開機能を比較する」を参照してください。
<!-- /MSL-PAGE -->
