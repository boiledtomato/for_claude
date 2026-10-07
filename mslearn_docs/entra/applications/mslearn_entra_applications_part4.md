# Microsoft Learn — Microsoft Entra / エンタープライズアプリ・プロビジョニング・アプリプロキシ (part 4)

Source: https://learn.microsoft.com/ja-jp/entra/
Pages in this file: 70

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-basic"} -->
## PowerShell サンプル - アプリケーション プロキシ アプリの基本情報を一覧表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-basic
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: アプリケーション ID (AppId)、名前 (DisplayName)、オブジェクト ID (ObjId) と共に Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、アプリケーション ID (AppId)、名前 (DisplayName)、オブジェクト ID (ObjId) など、すべての Microsoft Entra アプリケーション プロキシ アプリケーションに関する情報を一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra application proxy applications (AppId, Name of the app, ObjID).
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

$numberofAadapApps = 0

Write-Host "List of the configured Microsoft Entra application proxy applications"
Write-Host

foreach ($item in $allApps) {

 $aadapApp = $null
 
 $aadapAppId =  Get-MgBetaApplication -Top 100000 | where-object {$_.AppId -eq $item.AppId}
 $aadapApp = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing | Format-List -Property InternalUrl, ExternalUrl, AlternateUrl

  if ($aadapApp -ne $null) {
   
  Write-Host $item.DisplayName"(AppId: " $item.AppId ", ObjId:" $item.Id")"
  Write-Host

  $numberofAadapApps = $numberofAadapApps + 1      

  }
}

Write-Host "Number of the Microsoft Entra application proxy applications: " $numberofAadapApps

Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."

```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-by-connector-group"} -->
## アプリ用の Microsoft Entra プライベート ネットワーク コネクタ グループを一覧表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-by-connector-group
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: 割り当てられたアプリケーションを含むすべての Microsoft Entra プライベート ネットワーク コネクタ グループを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、割り当てられたアプリケーションを含むすべての Microsoft Entra プライベート ネットワーク コネクタ グループに関する情報を一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra private network connector groups with the assigned applications.
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green" 

$aadapServPrinc = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

Write-Host "Reading Microsoft Entra applications. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaApplication -Top 100000

Write-Host "Reading application. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$aadapApp = $aadapServPrinc | ForEach-Object {$allApps.AppId -match $_.AppId}
 
Write-Host "Reading connector groups. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$aadapConnectorGroups= Get-MgBetaOnPremisePublishingProfileConnectorGroup -OnPremisesPublishingProfileId "applicationProxy" -Top 100000 

Write-Host "Displaying connector groups and assigned applications..." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

foreach ($item in $aadapConnectorGroups)
 {
  
   If ($item.ConnectorGroupType -eq "applicationProxy")
    {  
        "Connector group: " + $item.Name + " (Id: " + $item.Id+ ") - Region: " + $item.Region;
          
        $assignedApps= Get-MgBetaOnPremisePublishingProfileConnectorGroupApplication -ConnectorGroupId $item.Id -OnPremisesPublishingProfileId "applicationProxy";
    
        " "; 

        foreach ($item2 in $assignedApps)
         {
           
           $Item2.DisplayName + " (AppId: " + $item2.AppId+ ")"
         } 
    
    " ";
       
    }
           
 }   

Write-Host ("")
Write-Host ("Finished.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet." 
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
| [Get-MgBetaOnPremisePublishingProfileConnectorGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaonpremisepublishingprofileconnectorgroup) | コネクタ グループを取得します |
| [Get-MgBetaOnPremisePublishingProfileConnectorGroupApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaonpremisepublishingprofileconnectorgroupapplication) | コネクタ グループに割り当てられたアプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-extended"} -->
## PowerShell サンプル - Microsoft Entra アプリケーション プロキシ アプリの拡張情報の一覧表示 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-extended
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: アプリケーション ID (AppId)、名前 (DisplayName)、外部 URL (ExternalUrl)、内部 URL (InternalUrl)、認証の種類 (ExternalAuthenticationType) と共にすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、アプリケーション ID (AppId)、名前 (DisplayName)、外部 URL (ExternalUrl)、内部 URL (InternalUrl)、認証の種類 (ExternalAuthenticationType)、シングル サインオン (SSO) モード、その他の設定など、すべての Microsoft Entra アプリケーション プロキシ アプリケーションに関する情報を一覧表示します。

`$ssoMode`変数の値を変更すると、SSO モードでフィルター処理された出力が有効になります。 さらに詳しい情報は、スクリプト内に記載されています。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script enumerates all Microsoft Entra application proxy applications with configuration details
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer

$ssoMode = "All"

# Change $ssoMode to filter the output based on the configured SSO type
# All                           - all Microsoft Entra application proxy apps (no filter)
# none                          - Microsoft Entra application proxy apps configured with no SSO, SAML, Linked, Password
# OnPremisesKerberos            - Microsoft Entra application proxy apps configured with Windows Integrated SSO (Kerberos Constrained Delegation)
# aadHeaderBased                - Microsoft Entra Native Header-based authentication
# pingHeaderBased               - Microsoft Entra Ping Header-based authentication
# oAuthToken                    - Microsoft Entra OAuth-based SSO

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green" 

$aadapServPrinc = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

Write-Host "Reading Microsoft Entra applications. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaApplication -Top 100000

Write-Host "Filtering Microsoft Entra application proxy applications. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$aadapApp = $null

foreach ($item in $aadapServPrinc) {
   foreach ($item2 in $allApps) {
    
     if ($item.AppId -eq $item2.AppId) {[array]$aadapApp += $item2}

    }
}

$numberofAadapApps, $numberofFilteredAadapApps = 0, 0

Write-Host "Displaying all Microsoft Entra application proxy applications with configuration details..." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "SSO mode filter: " $ssoMode -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

foreach ($item in $aadapApp) {
 
 $aadapAppConf, $aadapAppConf1, $aadapAppConf2, $aadapAppConf3, $aadapAppConf4 = $null, $null, $null, $null, $null

 $aadapAppConf = Get-MgBetaApplication -ApplicationId $item.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing 
 $aadapAppConf1 = Get-MgBetaApplication -ApplicationId $item.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing `
  | select singleSignOnSettings -expand SingleSignOnSettings 
 $aadapAppConf2 = Get-MgBetaApplication -ApplicationId $item.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing `
  | select verifiedCustomDomainCertificatesMetadata -expand verifiedCustomDomainCertificatesMetadata 
 $aadapAppConf3 = Get-MgBetaApplication -ApplicationId $item.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing | select OnPremisesApplicationSegments -expand OnPremisesApplicationSegments
 $aadapAppConf4 = Get-MgBetaApplication -ApplicationId $item.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing `
  | select singleSignOnSettings -expand SingleSignOnSettings | select KerberosSignOnSettings -expand KerberosSignOnSettings 

    if ($aadapAppConf -ne $null) {
   
      if ($ssoMode -eq "All" -Or $aadapAppConf1.SingleSignOnSettings.SingleSignOnMode -eq $ssoMode) {
    
        Write-Host $Item.DisplayName " (AppId: " $item.AppId  " / ObjectId: " $item.Id ")" -BackgroundColor "Black" -ForegroundColor "White"    

        Write-Host " "

        Write-Host "External Url: " $aadapAppConf.ExternalUrl
        Write-Host "Internal Url: " $aadapAppConf.InternalUrl
        Write-Host "Pre authentication type: " $aadapAppConf.ExternalAuthenticationType
        Write-Host " "
        Write-Host "SSO mode: " $aadapAppConf1.SingleSignOnSettings.SingleSignOnMode

      If ($aadapAppConf1.SingleSignOnMode -eq "OnPremisesKerberos") {

        Write-Host "Service Principal Name (SPN): " $aadapAppConf4.KerberosServicePrincipalName
        Write-Host "Username Mapping Attribute: " $aadapAppConf4.KerberosSignOnMappingAttributeType
      
        }
      
        Write-Host " "
        Write-Host "Backend Application Timeout: " $aadapAppConf.ApplicationServerTimeout
        Write-Host "Translate URLs in Headers: " $aadapAppConf.IsTranslateHostHeaderEnabled
        Write-Host "Translate URLs in Application Body: " $aadapAppConf.IsTranslateLinksInBodyEnabled
        Write-Host "Use HTTP-Only Cookie: " $aadapAppConf.IsHttpOnlyCookieEnabled
        Write-Host "Use Secure Cookie: " $aadapAppConf.IsSecureCookieEnabled
        Write-Host "Use Persistent Cookie: " $aadapAppConf.IsPersistentCookieEnabled
        Write-Host "Backend Certification Validation: " $aadapAppConf.IsBackendCertificateValidationEnabled

      If ($aadapAppConf3.Count -gt 0) { Write-Host "Complex App."}
      
      If ($aadapAppConf2.VerifiedCustomDomainCertificatesMetadata.Thumbprint.Length -ne 0) {
       
        Write-Host " "
        Write-Host "SSL Certificate details:"
        Write-Host "Certificate SubjectName: " $aadapAppConf2.VerifiedCustomDomainCertificatesMetadata.SubjectName
        Write-Host "Certificate Issuer: " $aadapAppConf2.VerifiedCustomDomainCertificatesMetadata.Issuer
        Write-Host "Certificate Thumbprint: " $aadapAppConf2.VerifiedCustomDomainCertificatesMetadata.Thumbprint
        Write-Host "Valid from: " $aadapAppConf2.VerifiedCustomDomainCertificatesMetadata.IssueDate
        Write-Host "Valid to: " $aadapAppConf2.VerifiedCustomDomainCertificatesMetadata.ExpiryDate
       
       } 

      $numberofFilteredAadapApps = $numberofFilteredAadapApps + 1
      
        Write-Host
      }

      $numberofAadapApps = $numberofAadapApps + 1          

     }
}

Write-Host "Number of the Microsoft Entra application proxy Applications: " $numberofAadapApps
Write-Host "Number of the filtered Microsoft Entra application proxy Applications: " $numberofFilteredAadapApps
Write-Host
Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet." 
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-with-policy"} -->
## PowerShell サンプル - ポリシーを使用して、すべての Microsoft Entra アプリケーション プロキシ アプリを一覧表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-app-proxy-apps-with-policy
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: ディレクトリ内の有効期間トークン ポリシーを持つすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、トークンの有効期間ポリシーを持つディレクトリ内のすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示し、ポリシーに関する詳細を一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra proxy applications that have assigned an Azure AD policy (token lifetime) with policy details.
# Reference:
# Configurable token lifetimes in Microsoft Entra ID
# https://learn.microsoft.com/entra/identity-platform/configurable-token-lifetimes
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green" 

$aadapServPrinc = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

Write-Host "Reading Microsoft Entra applications. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaApplication -Top 100000

Write-Host "Reading application. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$aadapApp = $null

foreach ($item in $aadapServPrinc) {
   foreach ($item2 in $allApps) {
    
     if ($item.AppId -eq $item2.AppId) {[array]$aadapApp += $item2}

    }
}

foreach ($item in $aadapApp)
 {
  
  $Policies = $Null
  $Policies = Get-MgBetaApplicationTokenLifetimePolicy -ApplicationId $item.Id 
  
  if ($Policies -ne $Null) {

  Write-Host ("")        
 
  Write-Host $item.DisplayName + " (AppId: " + $item.AppId + ")"  -BackgroundColor "Black" -ForegroundColor "White" 
 
  Write-Host ("") 
  Write-Host ("Assigned policy:") 
  Write-Host ("") 

  Write-Host ("Policy Id:    " + $Policies.Id)
  Write-Host ("DisplayName:  " + $Policies.DisplayName)
  Write-Host ("Definition:   " + $Policies.Definition)
  Write-Host ("Org. default: " + $Policies.IsOrganizationDefault)
  Write-Host ("") 

  }
          
 }   

Write-Host ("")
Write-Host ("Finished.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得します。 |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
| [Get-MgBetaApplicationTokenLifetimePolicy](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplicationtokenlifetimepolicy) | アプリケーションまたはサービス プリンシパルに割り当てられたポリシーを一覧表示します |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-connectors"} -->
## PowerShell サンプル - すべての Microsoft Entra プライベート ネットワーク コネクタ グループを一覧表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-connectors
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: ディレクトリ内のすべての Microsoft Entra プライベート ネットワーク コネクタ グループおよびコネクタを一覧表示する PowerShell の例。

### 概要

ディレクトリ内のすべての Microsoft Entra プライベート ネットワーク コネクタ グループおよびコネクタを一覧表示する PowerShell スクリプトの例。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

Note

Azure を操作するには、Azure Az PowerShell モジュールを使用することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「[AzureRM から Az への Azure PowerShell の移行](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)」を参照してください。

このサンプルには、[Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra private network connector groups with the included connectors.
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading Microsoft Entra private network connector groups. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$aadapConnectorGroups= Get-MgBetaOnPremisePublishingProfileConnectorGroup -OnPremisesPublishingProfileId "applicationProxy" -Top 100000 

$countAssignedApps, $CountOfConnectorGroups = 0

foreach ($item in $aadapConnectorGroups) {
   
     If ($item.ConnectorGroupType -eq "applicationProxy") {

     Write-Host "Connector group: " $item.Name, "(Id:" $item.Id ")" -BackgroundColor "Black" -ForegroundColor "White" 
     Write-Host "Region: " $item.Region
     
     Write-Host " "

     $connectors = Get-MgBetaOnPremisePublishingProfileConnectorGroupMember -ConnectorGroupId $item.Id -OnPremisesPublishingProfileId "applicationProxy" 

     $connectors | ft

     " ";

     $CountOfConnectorGroups = $CountOfConnectorGroups + 1

     }
}  

Write-Host ("")
Write-Host ("Number of Microsoft Entra private network connector Groups: $CountOfConnectorGroups")
Write-Host ("")
Write-Host ("Finished.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続します |
| [Get-MgBetaOnPremisePublishingProfileConnectorGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaonpremisepublishingprofileconnectorgroup) | コネクタ グループを取得します |
| [Get-MgBetaOnPremisePublishingProfileConnectorGroupMember](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaonpremisepublishingprofileconnectorgroupmember) | コネクタ グループのメンバーを取得します |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-custom-domain-no-cert"} -->
## PowerShell サンプル - 証明書のない Microsoft Entra アプリケーション プロキシ アプリ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-custom-domain-no-cert
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: カスタム ドメインを使用しているが、有効な TLS/SSL 証明書がアップロードされていないすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、有効な TLS/SSL 証明書がアップロードされていないカスタム ドメインを使用しているすべての Microsoft Entra アプリケーション プロキシ アプリを一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra application proxy applications using custom domain with no uploaded certificate.
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) and one of the following modules:
#
# Microsoft.Graph ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

$numberofAadapApps = 0

Write-Host " "
Write-Host "Displaying custom domain Microsoft Entra application proxy applications with no uploaded certificates..." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

foreach ($item in $allApps) {

 $aadapApp, $aadapAppConf, $aadapAppConf1 = $null, $null, $null
 
 $aadapAppId =  Get-MgBetaApplication -Top 100000 | where-object {$_.AppId -eq $item.AppId}
 $aadapAppConf = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing 
 $aadapAppConf1 = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing `
  | select verifiedCustomDomainCertificatesMetadata -expand verifiedCustomDomainCertificatesMetadata 

  if (($aadapAppConf -ne $null) -and ($aadapAppConf.ExternalUrl -notmatch ".msappproxy.net")) {
   
   if ($aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.Thumbprint.Length -eq 0) {
  
     Write-Host $item.DisplayName"(AppId: " $item.AppId ", ObjId:" $item.Id")" -BackgroundColor "Black" -ForegroundColor "White"
     Write-Host
     Write-Host "External Url: " $aadapAppConf.ExternalUrl
     Write-Host "Internal Url: " $aadapAppConf.InternalUrl
     Write-Host "Pre-authentication: " $aadapAppConf.ExternalAuthenticationType
     Write-Host
  
     $numberofAadapApps = $numberofAadapApps + 1              
    }
  
   }
  
}

Write-Host
Write-Host "Number of the custom domain Microsoft Entra application proxy applications with no uploaded certificate: " $numberofAadapApps -BackgroundColor "Black" -ForegroundColor "White"
Write-Host ("")

Write-Host
Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-custom-domains-and-certs"} -->
## PowerShell サンプル - カスタム ドメインを使用した Microsoft Entra アプリケーション プロキシ アプリ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-custom-domains-and-certs
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: カスタム ドメインを使用しているすべての Microsoft Entra アプリケーション プロキシ アプリケーションと証明書情報を一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、カスタム ドメインを使用しているすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示し、カスタム ドメインに関連付けられている証明書情報を一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra application proxy application custom domain applications & uploaded certificates.
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) and one of the following modules:
#
# Microsoft.Graph ver 2.10
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

$numberofAadapApps, $certsNumber = 0, 0

[string[]]$certs = $null

Write-Host "Displaying all custom domain Microsoft Entra application proxy applications and the uploaded certificates..." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

foreach ($item in $allApps) {

 $aadapApp, $aadapAppConf, $aadapAppConf1 = $null, $null, $null
 
 $aadapAppId =  Get-MgBetaApplication -Top 100000 | where-object {$_.AppId -eq $item.AppId}
 $aadapAppConf = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing 
 $aadapAppConf1 = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing `
  | select verifiedCustomDomainCertificatesMetadata -expand verifiedCustomDomainCertificatesMetadata 

  if (($aadapAppConf -ne $null) -and ($aadapAppConf.ExternalUrl -notmatch ".msappproxy.net")) {
   
  Write-Host $item.DisplayName"(AppId: " $item.AppId ", ObjId:" $item.Id")" -BackgroundColor "Black" -ForegroundColor "White"
  Write-Host
  Write-Host "External Url: " $aadapAppConf.ExternalUrl
  Write-Host "Internal Url: " $aadapAppConf.InternalUrl
  Write-Host "Pre-authentication: " $aadapAppConf.ExternalAuthenticationType
  Write-Host

  If ($aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.Thumbprint.Length -ne 0) {
       
        Write-Host " "
        Write-Host "SSL Certificate details:"
        Write-Host "Certificate SubjectName: " $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.SubjectName
        Write-Host "Certificate Issuer: " $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.IssuerName
        Write-Host "Certificate Thumbprint: " $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.Thumbprint
        Write-Host "Valid from: " $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.IssueDate
        Write-Host "Valid to: " $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.ExpiryDate
        Write-Host " "

        if ($null -eq ($aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.Thumbprint | ? { $certs -match $_ })) {

          $certs += " `r`nSSL Certificate details:`r`nCertificate SubjectName: " + $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.SubjectName
          $certs += "Certificate Issuer: " + $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.IssuerName
          $certs += "Certificate Thumbprint: " + $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.Thumbprint
          $certs += "Valid from: " + $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.IssueDate
          $certs += "Valid to: " + $aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.ExpiryDate + "`r`n"

          $certsNumber = $certsNumber + 1
          
        }

  $numberofAadapApps = $numberofAadapApps + 1      
     }
  }
}

Write-Host
Write-Host "Number of the Microsoft Entra application proxy applications with custom domain: " $numberofAadapApps -BackgroundColor "Black" -ForegroundColor "White"
Write-Host ("")
Write-Host ("Number of uploaded certificates: " + $certsNumber) -BackgroundColor "Black" -ForegroundColor "White"
Write-Host ("")
Write-Host ("Used certificates:") -BackgroundColor "Black" -ForegroundColor "White"
Write-Host ("")

$certs 

Write-Host
Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-default-domain-apps"} -->
## PowerShell サンプル - 既定のドメインを使った Microsoft Entra アプリケーション プロキシ アプリ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-default-domain-apps
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: 既定のドメイン (.msappproxy.net) を使うすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、既定のドメインを使用するすべての Microsoft Entra アプリケーション プロキシ アプリケーションが一覧表示されます。 既定のドメインは `.msappproxy.net`で終わる。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra application proxy application "non-custom domain" apps (.msappproxy.net).
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) and one of the following modules:
#
# Microsoft.Graph.Beta 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

$numberofAadapApps = 0

Write-Host "Displaying all non-custom domain apps (.msappproxy) applications..." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

foreach ($item in $allApps) {

 $aadapApp = $null
 
 $aadapAppId =  Get-MgBetaApplication -Top 100000 | where-object {$_.AppId -eq $item.AppId}
 $aadapApp = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing 

  if (($aadapApp -ne $null) -and ($aadapApp.ExternalUrl -match ".msappproxy.net")) {
   
  Write-Host $item.DisplayName"(AppId: " $item.AppId ", ObjId:" $item.Id")"
  Write-Host
  Write-Host "External Url: " $aadapApp.ExternalUrl
  Write-Host "Internal Url: " $aadapApp.InternalUrl
  Write-Host

  $numberofAadapApps = $numberofAadapApps + 1      

  }
}

Write-Host
Write-Host "Number of the Microsoft Entra application proxy applications: " $numberofAadapApps
Write-Host
Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得します。 |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-all-wildcard-apps"} -->
## PowerShell サンプル - ワイルドカードを使用している Microsoft Entra アプリケーション プロキシ アプリの一覧を表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-all-wildcard-apps
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: ワイルドカードを使用しているすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、ワイルドカード発行を使用して、すべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra application proxy application wildcard published apps.
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) and one of the following modules:
#
# Microsoft.Graph ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

$numberofAadapApps = 0

Write-Host "Displaying wildcard Microsoft Entra application proxy applications..." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

foreach ($item in $allApps) {

 $aadapApp = $null
 
 $aadapAppId =  Get-MgBetaApplication -Top 100000 | where-object {$_.AppId -eq $item.AppId}
 $aadapApp = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing 

  if (($aadapApp -ne $null) -and ($aadapApp.ExternalUrl -match "\*.")) {
   
  Write-Host $item.DisplayName"(AppId: " $item.AppId ", ObjId:" $item.Id")"
  Write-Host
  Write-Host "External Url: " $aadapApp.ExternalUrl
  Write-Host "Internal Url: " $aadapApp.InternalUrl
  Write-Host

  $numberofAadapApps = $numberofAadapApps + 1      

  }
}

Write-Host
Write-Host "Number of the Microsoft Entra application proxy applications: " $numberofAadapApps
Write-Host
Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-custom-domain-identical-cert"} -->
## PowerShell サンプル - 同じ証明書のない Microsoft Entra アプリケーション プロキシ アプリ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-custom-domain-identical-cert
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: 同じ証明書を使用して公開されたすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示する PowerShell の例。

### 概要

PowerShell スクリプトの例では、同じ証明書で発行されたすべての Microsoft Entra アプリケーション プロキシ アプリケーションを一覧表示します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra application proxy applications published with the identical certificate.
#
# .\replace_with_the_script_name.ps1 -Thumbprint <thumbprint of the certificate to filter on> 
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) and one of the following modules:
#
# Microsoft.Graph ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

param(
[parameter(Mandatory=$true)]
[string] $Thumbprint = "null"
)

$certThumbprint = $Thumbprint 

If ($certThumbprint -eq "null") {

    Write-Host "Parameter is missing." -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host ".\get-custom-domain-identical-cert.ps1 -Thumbprint <thumbprint of the certificate>" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "

    Exit
}

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.Read.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

$numberofAadapApps = 0

Write-Host " "
Write-Host "Displaying all Microsoft Entra application proxy applications published with the identical certificate (", $certThumbprint,")" -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

foreach ($item in $allApps) {

 $aadapApp, $aadapAppConf, $aadapAppConf1 = $null, $null, $null
 
 $aadapAppId =  Get-MgBetaApplication -Top 100000 | where-object {$_.AppId -eq $item.AppId}
 $aadapAppConf = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing 
 $aadapAppConf1 = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing `
  | select verifiedCustomDomainCertificatesMetadata -expand verifiedCustomDomainCertificatesMetadata 

  if ($aadapAppConf -ne $null) {
   
   if ($aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.Thumbprint -match $certThumbprint) {
  
     Write-Host $item.DisplayName"(AppId: " $item.AppId ", ObjId:" $item.Id")" -BackgroundColor "Black" -ForegroundColor "White"
     Write-Host
     Write-Host "External Url: " $aadapAppConf.ExternalUrl
     Write-Host "Internal Url: " $aadapAppConf.InternalUrl
     Write-Host "Pre-authentication: " $aadapAppConf.ExternalAuthenticationType
     Write-Host
  
     $numberofAadapApps = $numberofAadapApps + 1              
    }
  
   }
  
}

Write-Host
Write-Host "Number of the displayed Microsoft Entra application proxy applications: " $numberofAadapApps -BackgroundColor "Black" -ForegroundColor "White"
Write-Host ("")

Write-Host
Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet." 
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-get-custom-domain-replace-cert"} -->
## PowerShell サンプル - Microsoft Entra アプリケーション プロキシ アプリの証明書の置換 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-custom-domain-replace-cert
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシ アプリケーション間で証明書を一括置換する PowerShell の例。

### 概要

PowerShell スクリプトの例では、公開されているすべての Microsoft Entra アプリケーション プロキシ アプリケーションの証明書を同じ証明書に一括で置き換えます。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script gets all Microsoft Entra application proxy applications published with the identical certificate.
#
# .\replace_with_the_script_name.ps1 -CurrentThumbprint <thumbprint of the current certificate> -PFXFilePath <full path with PFX filename>
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) and one of the following modules:
#
# Microsoft.Graph ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator or Application Developer 
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

param(
[parameter(Mandatory=$true)]
[string] $CurrentThumbprint = "null",
[parameter(Mandatory=$true)]
[string] $PFXFilePath = "null"
)

$certThumbprint = $CurrentThumbprint
$certPfxFilePath = $PFXFilePath

If (($certThumbprint -eq "null") -or ($certPfxFilePath -eq "null")) {

    Write-Host "Parameter is missing." -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host ".\get-custom-domain-replace-cert.ps1 -CurrentThumbprint <thumbprint of the current certificate> -PFXFilePath <full path with PFX filename>" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "

    Exit
}

If ((Test-Path -Path $certPfxFilePath) -eq $False) {

    Write-Host "The pfx file does not exist." -BackgroundColor "Black" -ForegroundColor "Red"
    Write-Host " "

    Exit
}

$securePassword = Read-Host -AsSecureString // please provide the password of the pfx file

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.ReadWrite.All -NoWelcome

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green"

$allApps = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

$numberofAadapApps = 0

Write-Host ("")
Write-Host ("SSL certificate change for the Microsoft Entra application proxy apps below:")
Write-Host ("")

foreach ($item in $allApps) {

  $aadapApp, $aadapAppConf, $aadapAppConf1 = $null, $null, $null

  $aadapAppId =  Get-MgBetaApplication -Filter "AppId eq '$($item.AppID)'"

  $aadapAppConf = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing 
  $aadapAppConf1 = Get-MgBetaApplication -ApplicationId $aadapAppId.Id -ErrorAction SilentlyContinue -select OnPremisesPublishing | select OnPremisesPublishing -expand OnPremisesPublishing `
    | select verifiedCustomDomainCertificatesMetadata -expand verifiedCustomDomainCertificatesMetadata 

  if ($aadapAppConf -ne $null) {

    if ($aadapAppConf1.VerifiedCustomDomainCertificatesMetadata.Thumbprint -match $certThumbprint) {

      Write-Host $item.DisplayName"(AppId: " $item.AppId ", ObjId:" $item.Id")" -BackgroundColor "Black" -ForegroundColor "White"
      Write-Host
      Write-Host "External Url: " $aadapAppConf.ExternalUrl
      Write-Host "Internal Url: " $aadapAppConf.InternalUrl
      Write-Host "Pre-authentication: " $aadapAppConf.ExternalAuthenticationType
      Write-Host

      $params = @{
         onPremisesPublishing = @{
            verifiedCustomDomainKeyCredential = @{
                type="X509CertAndPassword";
                value = [convert]::ToBase64String([System.IO.File]::ReadAllBytes($certPfxFilePath));
            };
            verifiedCustomDomainPasswordCredential = @{
                value = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto([System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($securePassword)) };
         }
      }

      Update-MgBetaApplication -ApplicationId $aadapAppId.Id -BodyParameter $params
  
      $numberofAadapApps = $numberofAadapApps + 1
    }
  }
}

Write-Host
Write-Host "Number of the updated Microsoft Entra application proxy applications: " $numberofAadapApps -BackgroundColor "Black" -ForegroundColor "White"
Write-Host ("")

Write-Host
Write-Host "Finished." -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
| [Update-MgBetaApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/update-mgbetaapplication) | アプリケーションを更新する |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/app-proxy/scripts/powershell-move-all-apps-to-connector-group"} -->
## PowerShell サンプル - Microsoft Entra アプリケーション プロキシ アプリを別のグループに移動する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-move-all-apps-to-connector-group
- Service: entra-id / app-proxy
- Article date: 2026-03-11
- Summary: Microsoft Entra アプリケーション プロキシの PowerShell の例は、現在コネクタ グループに割り当てられているすべてのアプリケーションを別のコネクタ グループに移動するために使用されます。

### 概要

PowerShell スクリプトの例では、現在コネクタ グループに割り当てられているすべての Microsoft Entra アプリケーション プロキシ アプリケーションを別のコネクタ グループに移動します。

[Azure サブスクリプション](https://learn.microsoft.com/ja-jp/azure/guides/developer/azure-developer-guide#understanding-accounts-subscriptions-and-billing)をお持ちでない場合は、開始する前に [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を作成してください。

注

Azure Az PowerShell モジュールを使用して Azure と対話することをお勧めします。 作業を開始するには、[Azure PowerShell のインストール](https://learn.microsoft.com/ja-jp/powershell/azure/install-azure-powershell)に関する記事を参照してください。 Az PowerShell モジュールに移行する方法については、「 [Azure PowerShell を AzureRM から Az に移行する」](https://learn.microsoft.com/ja-jp/powershell/azure/migrate-from-azurerm-to-az)を参照してください。

このサンプルには、 [Microsoft Graph Beta PowerShell モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) 2.10 以降が必要です。

### サンプル スクリプト

```powershell
# This sample script moves all Microsoft Entra application proxy applications assigned to a specific connector group to another connector group.
#
# .\move-all-apps-to-a-connector-group.ps1 -CurrentConnectorGroupId <ObjectId of the current connector group> -NewConnectorGroupId <ObjectId of the new connector group>
#
# Version 1.0
#
# This script requires PowerShell 5.1 (x64) or beyond and one of the following modules:
#
# Microsoft.Graph.Beta ver 2.10 or newer
#
# Before you begin:
#    
#    Required Microsoft Entra role at least Application Administrator
#    or appropriate custom permissions as documented https://learn.microsoft.com/azure/active-directory/roles/custom-enterprise-app-permissions
#
# 

param(
[parameter(Mandatory=$true)]
[string] $CurrentConnectorGroupId = "null", 
[parameter(Mandatory=$true)]
[string] $NewConnectorGroupId = "null"
)

$currentGroupId = $CurrentConnectorGroupId
$newGroupId = $NewConnectorGroupId
$connectorAssignedApp = $null

If (($currentGroupId -eq "null") -or ($newGroupId -eq "null")) {

    Write-Host "Parameter is missing." -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "
    Write-Host ".\move-all-apps-to-a-connector-group.ps1 -CurrentConnectorGroupId <ObjectId of the current connector group> -NewConnectorGroupId <ObjectId of the new connector group>" -BackgroundColor "Black" -ForegroundColor "Green"
    Write-Host " "

    Exit
}

Import-Module Microsoft.Graph.Beta.Applications

Connect-MgGraph -Scope Directory.ReadWrite.All -NoWelcome

Try {
$temp = Get-MgBetaOnPremisePublishingProfileConnectorGroup -OnPremisesPublishingProfileId "applicationProxy" -ConnectorGroupId $currentGroupId
$temp = Get-MgBetaOnPremisePublishingProfileConnectorGroup -OnPremisesPublishingProfileId "applicationProxy" -ConnectorGroupId $newGroupId
}

Catch {
    Write-Host "Possibly, one of the parameters is incorrect." -BackgroundColor "Black" -ForegroundColor "Red"
    Write-Host " "

    Exit
}

Write-Host "Reading service principals. This operation might take longer..." -BackgroundColor "Black" -ForegroundColor "Green" 

$aadapServPrinc = Get-MgBetaServicePrincipal -Top 100000 | where-object {$_.Tags -Contains "WindowsAzureActiveDirectoryOnPremApp"}

Write-Host "Displaying Microsoft Entra application proxy applications moved from the connector Id :",$currentGroupId," to: ",$newGroupId -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host " "

$connectorAssignedApp = Get-MgBetaOnPremisePublishingProfileConnectorGroupApplication -OnPremisesPublishingProfileId "applicationProxy" -ConnectorGroupId $CurrentConnectorGroupId;
$movedApps, $notmovedApps = 0, 0

 foreach ($item in $connectorAssignedApp) {

    if ($item.AppId -in ($aadapServPrinc.AppId)) {
               
     $item.DisplayName + " (AppId: " + $item.AppId + ")"
     
     $params = @{
      "@odata.id" = "https://graph.microsoft.com/beta/onPremisesPublishingProfiles/applicationProxy/connectorGroups/$NewConnectorGroupId"
       }
      
        Set-MgBetaApplicationConnectorGroupByRef -ApplicationId $item.Id -BodyParameter $params 

        $movedApps = $movedApps + 1
     }
     else
     {
      $notmovedApps = $notmovedApps + 1
     }      
} 

Write-Host ("")
Write-Host ("$movedApps apps has been moved to the new connector.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host ("$notmovedApps apps could not be moved to the new connector. Finished.") -BackgroundColor "Black" -ForegroundColor "Green"
Write-Host ("")
Write-Host "To disconnect from Microsoft Graph, please use the Disconnect-MgGraph cmdlet."
Write-Host ("")
```

### スクリプトの説明

| コマンド | 注記 |
| --- | --- |
| [Connect-MgGraph](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.authentication/connect-mggraph) | Microsoft Graph に接続する |
| [Get-MgBetaServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/get-mgserviceprincipal) | サービス プリンシパルを取得する |
| [Get-MgBetaOnPremisePublishingProfileConnectorGroup](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaapplication) | エンタープライズ アプリケーションを取得します。 |
| [Get-MgBetaOnPremisePublishingProfileConnectorGroupApplication](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/get-mgbetaonpremisepublishingprofileconnectorgroupapplication) | コネクタ グループに割り当てられているアプリケーションを一覧表示します |
| [Set-MgBetaApplicationConnectorGroupByRef](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.applications/set-mgbetaapplicationconnectorgroupbyref) | コネクタ グループにアプリケーションを割り当てます |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps"} -->
## アプリケーション管理のドキュメント - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps
- Service: entra-id / enterprise-apps
- Article date: 2025-03-31
- Summary: Microsoft Entra ID は Identity and Access Management (IAM) システムの 1 つです。 デジタル ID に関する情報を格納するための 1 つの場所を提供します。 ユーザー情報が格納される場所として Microsoft Entra ID を使用するようにソフトウェア アプリケーションを構成できます。

Microsoft Entra ID は Identity and Access Management (IAM) システムの 1 つです。 デジタル ID に関する情報を格納するための 1 つの場所を提供します。 ユーザー情報が格納される場所として Microsoft Entra ID を使用するようにソフトウェア アプリケーションを構成できます。

### 基本要素

#### 概要

- [アプリケーション管理とは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management)

#### 概念

- [Microsoft Entra はどのような種類のアプリをサポートしていますか?](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-app-types)
- [シングル サインオンとは](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)

### アプリを開発する

#### 概念

- [認証と承認の概念](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-vs-authorization)

#### 攻略ガイド

- [Microsoft ID またはソーシャル アカウントを使用してアプリを構築する](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-overview)

### アプリをテナントと統合する

#### 攻略ガイド

- [アプリ統合の概要](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/plan-an-application-integration)
- [事前に設定されたクラウド アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)
- [アプリを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)
- [AD FS アプリを Microsoft Entra ID に移行する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/migrate-adfs-apps-stages)

### アプリを構成する

#### 攻略ガイド

- [アプリのプロパティを構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-configure)
- [ユーザーとグループを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)
- [アプリをプロビジョニングする](https://learn.microsoft.com/ja-jp/entra/id-governance/what-is-provisioning)
- [マイ アプリの構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/myapps-overview)

#### リファレンス

- [Microsoft Graph API を使用してアプリケーションを管理する](https://learn.microsoft.com/graph/api/resources/applications-api-overview?view=graph-rest-1.0)

### アプリをセキュリティで保護する

#### 攻略ガイド

- [条件付きアクセスを設定する](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-cloud-apps)
- [多要素認証を設定する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/howto-mfa-getstarted)
- [証明書の管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)
- [テナント制限を設定する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tenant-restrictions)
- [トークン暗号化を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)
- [Defender for Cloud Apps](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/cloud-app-security)
- [過剰な特権が与えられているアプリや疑わしいアプリに対応する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)

### アプリのアクセスを管理する

#### 概要

- [ID ガバナンス](https://learn.microsoft.com/ja-jp/entra/id-governance/identity-governance-overview)
- [ユーザーと管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview)

#### 攻略ガイド

- [ロールを割り当てる](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)
- [ユーザーの同意の構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent)
- [管理者の同意の構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)
- [アクセス許可の分類を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-permission-classifications)
- [エンタイトルメントを管理する](https://learn.microsoft.com/ja-jp/entra/id-governance/entitlement-management-scenarios)

### アプリを維持する

#### 攻略ガイド

- [テナント内のアプリの一覧を表示、検索、並べ替え、フィルター処理する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal)
- [サインイン エラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-troubleshoot-sign-in-errors)
- [アプリに対するユーザーのサインインを無効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal)
- [アプリへのユーザー アクセスを削除する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/methods-for-removing-user-access)
- [アプリを削除する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)

### アプリを監視する

#### 概念

- [サインイン ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-sign-ins)
- [使用状況と分析情報のレポート](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-usage-insights-report)
- [監査ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-audit-logs)
- [プロビジョニング ログ](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/concept-provisioning-logs)

#### 攻略ガイド

- [アクティビティログへのアクセス](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-access-activity-logs)
- [ログのダウンロード](https://learn.microsoft.com/ja-jp/entra/identity/monitoring-health/howto-download-logs)
- [アクセス レビューを設定する](https://learn.microsoft.com/ja-jp/entra/id-governance/deploy-access-reviews)
- [所有者を割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners)

### オンプレミス アプリへのリモート アクセス

#### 概念

- [アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)

#### 攻略ガイド

- [アプリケーション プロキシのデプロイを計画する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/conceptual-deployment-plan)
- [コネクタを設定する](https://learn.microsoft.com/ja-jp/entra/global-secure-access/concept-connectors)
- [シングル サインオンの設定](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-sso)
- [ネイティブ クライアント アプリケーションを公開する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-native-client-application)
- [要求に対応するアプリケーションを公開する](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-configure-for-claims-aware-applications)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/access-panel-collections"} -->
## マイ アプリ ポータルでコレクションを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/access-panel-collections
- Service: entra-id / enterprise-apps
- Article date: 2024-09-30
- Summary: マイ アプリ コレクションを使用してマイ アプリ ページをカスタマイズし、ユーザーのマイ アプリ エクスペリエンスをシンプルにします。 別個のタブを使用してアプリケーションをグループに整理します。

ユーザーは、マイ アプリポータルを使用して、自分がアクセス権を持つクラウドベースのアプリケーションを表示および起動できます。 既定では、ユーザーがアクセスできるすべてのアプリケーションが 1 つのページにまとめて表示されます。 Microsoft Entra ID P1 または P2 ライセンスをお持ちの場合は、このページをご自分のユーザー向けに整理するために、コレクションを設定できます。 コレクションを使用すると、関連するアプリケーションをグループ化できます。 たとえば、職務、タスク、またはプロジェクトごとにグループ化し、別々のタブに表示できます。コレクションは基本的に、ユーザーが既にアクセスできるアプリケーションにフィルターを適用するものであり、ユーザーは自分に割り当てられたコレクション内のアプリケーションのみを見ることができます。

Note

この記事では、管理者がコレクションを有効にして作成する方法について説明します。 マイ アプリ ポータルとコレクションの使用方法に関するエンド ユーザーの詳細については、「 [コレクションへのアクセスと使用](https://support.microsoft.com/account-billing/organize-apps-using-collections-in-the-my-apps-portal-2dae6b8a-d8b0-4a16-9a5d-71ed4d6a6c1d)」を参照してください。

### 前提条件

マイ アプリ ポータルでコレクションを作成するには、以下が必要です。

- アクティブなサブスクリプションが含まれる Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者、またはサービス プリンシパルの所有者。

### コレクションの作成

コレクションを作成するには、Microsoft Entra ID P1 または P2 ライセンスが必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. [ **管理**] で、[ **アプリ起動ツール**] を選択します。
4. [ **新しいコレクション]** を選択します。 [ **新しいコレクション** ] ページで、コレクションの名前を入力 **します** (名前に "collection" を使用しないことをお勧めします)。 次に **、説明**を入力します。
5. [アプリケーション] タブ **を** 選択します。[ **+ アプリケーションの追加]** を選択し、[ **アプリケーションの追加** ] ページで、コレクションに追加するすべてのアプリケーションを選択するか、[ **検索** ] ボックスを使用してアプリケーションを検索します。

    [Image: アプリケーションをコレクションに追加する]
6. アプリケーションの追加が完了したら、[ **追加**] を選択します。 選択したアプリケーションの一覧が表示されます。 矢印を使用して、一覧内のアプリケーションの順序を変更できます。
7. [ **所有者** ] タブを選択します。[ **+ ユーザーとグループの追加]** を選択し、[ **ユーザーとグループの追加** ] ページで、所有権を割り当てるユーザーまたはグループを選択します。 ユーザーとグループの選択が完了したら、[選択] を **選択します**。
8. [ **ユーザーとグループ** ] タブを選択します。[ **+ ユーザーとグループの追加]** を選択し、[ **ユーザーとグループの追加** ] ページで、コレクションを割り当てるユーザーまたはグループを選択します。 または、[ **検索** ] ボックスを使用してユーザーまたはグループを検索します。 ユーザーとグループの選択が完了したら、[選択] を **選択します**。
9. [ **確認と作成]** を選択します。 新しいコレクションのプロパティが表示されます。

Note

管理コレクションは、[マイ アプリ ポータル](https://entra.microsoft.com)からではなく、[Microsoft Entra 管理センター](https://myapps.microsoft.com)を使用して管理されます。 たとえば、ユーザーまたはグループを所有者として割り当てた場合、その所有者は Microsoft Entra 管理センター経由でのみコレクションを管理できます。

Note

コレクション内の Office アプリに関する既知の問題があります。 コレクションに既に少なくとも1つの Office アプリがある場合、さらに追加するには、以下の手順に従います。

1. 管理するコレクションを選択し、[ **アプリケーション** ] タブを選択します。
2. コレクションからすべての Office アプリを削除しますが、変更は保存しません。
3. [ **+ アプリケーションの追加] を**選択します。
4. [ **アプリケーションの追加** ] ページで、コレクションに追加するすべての Office アプリ (手順 2 で削除したアプリを含む) を選択します。
5. アプリケーションの追加が完了したら、[ **追加**] を選択します。 選択したアプリケーションの一覧が表示されます。 矢印を使用して、一覧内のアプリケーションの順序を変更できます。
6. [ **保存] を** 選択して変更を適用します。

### 監査ログの表示

監査ログには、コレクション作成エンド ユーザー アクションなど、マイ アプリ コレクションの操作が記録されます。 マイ アプリから次のイベントが生成されます。

- 管理者コレクションの作成
- 管理者コレクションの編集
- 管理者コレクションの削除
- セルフサービス アプリケーションの追加 (エンド ユーザー)
- セルフサービス アプリケーションの削除 (エンド ユーザー)

[Microsoft Entra 管理センター](https://entra.microsoft.com)で監査ログにアクセスするには、[アクティビティ] セクションで **[Entra ID**&gt;**Enterprise apps**&gt;**Audit logs** を選択します。 **[サービス**] で、[**マイ アプリ**] を選択します。

### マイ アカウント ページのサポートを受ける

[マイ アプリ] ページで、ユーザーは [ **マイ アカウント**&gt;**アカウントの表示** ] を選択して、自分のアカウント設定を開くことができます。 Microsoft Entra ID **の [マイ アカウント]** ページでは、ユーザーは自分のセキュリティ情報、デバイス、パスワードなどを管理できます。 また、Office アカウントの設定にアクセスすることもできます。

Microsoft Entra アカウントのページや Office アカウントのページの問題についてサポート リクエストを送信する必要がある場合は、以下の手順に従ってリクエストが適切にルーティングされるようにしてください。

- **Microsoft Entra ID の [マイ アカウント]** ページに関する問題については、Microsoft Entra 管理センター内からサポート リクエストを開きます。 **Microsoft Entra 管理センター**&gt;**Learn > サポート**&gt;**新しいサポート リクエスト**に移動します。
- **Office の [マイ アカウント]** ページで問題が発生した場合は、Microsoft 365 管理センター内からサポート リクエストを開きます。 **Microsoft 365 管理センター**&gt;**Support** に移動します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/add-application-portal"} -->
## クイックスタート: エンタープライズ アプリケーションを追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal
- Service: entra-id / enterprise-apps
- Article date: 2025-03-31
- Summary: Microsoft Entra テナントに事前統合アプリを追加する方法について説明します。詳細な手順について説明します。

このクイックスタートでは、Microsoft Entra 管理センターを使用し、Microsoft Entra テナントにエンタープライズ アプリケーションを追加します。 Microsoft Entra ID には既にあらかじめ統合された何千ものエンタープライズ アプリケーションが含まれるギャラリーがあります。 組織で使用しているアプリケーションの多くは、おそらく既にギャラリーにあります。 このクイック スタートでは、 **Microsoft Entra SAML Toolkit** という名前のアプリケーションを例として使用しますが、概念は [ギャラリー内のほとんどのエンタープライズ アプリケーションに](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)適用されます。

このクイックスタートの手順をテストするには、非運用環境を使うことをお勧めします。

### 前提条件

Microsoft Entra テナントにエンタープライズ アプリケーションを追加するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者。

### エンタープライズ アプリケーションの追加

テナントにエンタープライズ アプリケーションを追加するには、次が必要です。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**にアクセスします。
3. [ **新しいアプリケーション]** を選択します。
4. [ **Microsoft Entra ギャラリーの参照** ] ウィンドウが開き、クラウド プラットフォーム、オンプレミス アプリケーション、およびおすすめのアプリケーションのタイルが表示されます。 [ **おすすめアプリケーション** ] セクションに一覧表示されているアプリケーションには、フェデレーション シングル サインオン (SSO) とプロビジョニングがサポートされているかどうかを示すアイコンが表示されます。 アプリケーションを検索して選択します。 このクイック スタートでは、 **Microsoft Entra SAML Toolkit** が使用されています。

    [Image: エンタープライズ アプリケーション ギャラリーで、追加するアプリケーションを参照します。]
5. アプリケーションのインスタンスを識別するために使用する名前を入力します。 たとえば、`Microsoft Entra SAML Toolkit 1` のようにします。
6. [ **作成]** を選択すると、登録したアプリケーションが表示されます。
7. この時点で、ベスト プラクティスとして [所有者をアプリケーションに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners#assign-an-owner) 必要があります。

OpenID Connect ベースの SSO を使用するアプリケーションをインストールする場合は、[ **作成** ] ボタンが表示されるのではなく、アカウントが既にあるかどうかに応じて、アプリケーションのサインインまたはサインアップ ページにリダイレクトされるボタンが表示されます。 詳細については、「 [OpenID Connect ベースのシングル サインオン アプリケーションを追加](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso)する」を参照してください。 サインインすると、アプリケーションがテナントに追加されます。

### リソースをクリーンアップする

次のクイックスタートを行う予定の場合は、作成したエンタープライズ アプリケーションをそのままにします。 そうでない場合は、削除してテナントをクリーンアップしてもかまいません。 詳細については、「 [アプリケーションの削除」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal)参照してください。

### Microsoft Graph API

プログラムによって Microsoft Entra ギャラリーからアプリケーションを追加するには、 [applicationTemplate:](https://learn.microsoft.com/ja-jp/graph/api/applicationtemplate-instantiate) を使用して Microsoft Graph で API をインスタンス化します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/add-application-portal-assign-users"} -->
## クイックスタート: ユーザー アカウントを作成して割り当てる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users
- Service: entra-id / enterprise-apps
- Article date: 2025-03-21
- Summary: ユーザー アカウントを作成し、Microsoft Entra テナントのエンタープライズ アプリケーションに割り当てます。 今すぐ効率的にアクセスの管理を開始する

このクイックスタートでは、Microsoft Entra 管理センターを使用して、Microsoft Entra テナントにユーザー アカウントを作成します。 作成したアカウントは、テナントに追加したエンタープライズ アプリケーションに割り当てることができます。

このクイックスタートの手順をテストするには、非運用環境を使用することをお勧めします。

### 前提条件

ユーザー アカウントを作成してエンタープライズ アプリケーションに割り当てるには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- クラウド アプリケーション管理者、サービス プリンシパルの所有者のいずれかのロール。 ユーザーを管理するには、ユーザー管理者ロールが必要です。
- [「クイック スタート: エンタープライズ アプリケーションを追加する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)」の手順の完了。

### ユーザー アカウントの作成

Microsoft Entra テナントにユーザー アカウントを作成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動する
3. ウィンドウの上部にある [ **新しいユーザー** ] を選択し、[ **新しいユーザーの作成**] を選択します。
4. [ **ユーザー プリンシパル名** ] フィールドに、ユーザー アカウントのユーザー名を入力します。 たとえば、「 `b.simon@contoso.com` 」のように入力します。 必ず `contoso.com` を自分のテナントのドメインの名前に変更してください。
5. [ **表示名** ] フィールドに、アカウントのユーザーの名前を入力します。 たとえば、「 `B.Simon` 」のように入力します。
6. [**グループとロール** **]、[設定]**、[**ジョブ情報**] セクションで、ユーザーに必要な詳細を入力します。
7. **作成**を選択します。

### エンタープライズ アプリケーションにユーザー アカウントを割り当てる

エンタープライズ アプリケーションにユーザー アカウントを割り当てるには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリケーション**に移動します。 たとえば、 **Microsoft Entra SAML Toolkit 1** という名前の前のクイック スタートで作成したアプリケーションを選択します。
3. 左側のウィンドウで、[ **ユーザーとグループ**] を選択し、[ **ユーザー/グループの追加]** を選択します。

    [Image: Microsoft Entra テナントのアプリケーションにユーザー アカウントを割り当てます。]
4. [**割り当ての追加**] ウィンドウで、[**ユーザーとグループ**] で [**選択なし**] を選択します。
5. アプリケーションに割り当てるユーザーを見つけて選択します。 たとえば、「 `b.simon@contoso.com` 」のように入力します。
6. **[選択]** を選択します。
7. [**ロールの選択**] で [**選択なし**] を選択し、ユーザーに割り当てるロールを選択します。 たとえば、 **Standard User など**です。
8. **[選択]** を選択します。
9. ウィンドウの下部にある [ **割り当て** ] を選択して、アプリケーションにユーザーを割り当てます。

ユーザーをアプリケーションに割り当てた後、そのアプリケーションがユーザーに表示されるように設定されていることを確認します。 割り当てられたユーザーに表示するには、左側のウィンドウで **[プロパティ** ] を選択し、[ **ユーザーに表示]** を [ **はい**] に設定します。

### リソースをクリーンアップする

次のクイックスタートを行う予定の場合は、作成したアプリケーションをそのままにします。 そうでない場合は、削除してテナントをクリーンアップしてもかまいません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/add-application-portal-configure"} -->
## エンタープライズ アプリケーション*のプロパティを構成する* - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-configure
- Service: entra-id / enterprise-apps
- Article date: 2025-06-10
- Summary: ユーザーがアプリケーションにアクセスして操作する方法に合わせて企業のプロパティを構成する方法について説明します。

この記事では、Microsoft Entra テナントでエンタープライズ アプリケーションのプロパティを構成する場合について説明します。 構成可能なプロパティの詳細については、[「エンタープライズ アプリケーション*の プロパティ」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-properties)を参照してください。

### 前提条件

エンタープライズ アプリケーションのプロパティを構成するには、次が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者、またはサービス プリンシパルの所有者。

### アプリケーションのプロパティを構成する

アプリケーションのプロパティは、アプリケーションの表現方法とアプリケーションへのアクセス方法を制御します。

::: zone pivot="portal"

アプリケーション\*のプロパティを構成する\*には：

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. 使用するアプリケーションを検索して選択します。
4. **[管理]** セクションで、 **[プロパティ]** を選択して編集用の **[プロパティ]** ペインを開きます。
5. **[プロパティ]**ペインでは、アプリケーションの次のプロパティを構成できます。
    - ロゴ
    - ユーザーのサインイン オプション
    - ユーザーに対するアプリの可視性
    - 使用可能な URL オプションの設定
    - アプリの割り当てが必要とするかどうかの選択
6. アプリのニーズに応じてプロパティを構成したら、**[**保存] を選択します。

::: zone-end

::: zone pivot="ms-powershell"

次の Microsoft Graph PowerShell スクリプトを使用して、アプリケーションの基本プロパティを構成します。

[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上の特権でサインインし、`Application.ReadWrite.All` アクセス許可に同意する必要があります。

```powershell

Import-Module Microsoft.Graph.Applications

$params = @{
    Tags = @(
        "HR"
        "Payroll"
        "HideApp"
    )
    Info = @{
        LogoUrl = "https://cdn.pixabay.com/photo/2016/03/21/23/25/link-1271843_1280.png"
        MarketingUrl = "https://www.contoso.com/app/marketing"
        PrivacyStatementUrl = "https://www.contoso.com/app/privacy"
        SupportUrl = "https://www.contoso.com/app/support"
        TermsOfServiceUrl = "https://www.contoso.com/app/termsofservice"
    }
    Web = @{
        HomePageUrl = "https://www.contoso.com/"
        LogoutUrl = "https://www.contoso.com/frontchannel_logout"
        RedirectUris = @(
            "https://localhost"
        )
    }
    ServiceManagementReference = "Owners aliases: Finance @ contosofinance@contoso.com; The Phone Company HR consulting @ hronsite@thephone-company.com;"
}

Update-MgApplication -ApplicationId $applicationId -BodyParameter $params
```

::: zone-end

::: zone pivot="ms-graph"

アプリケーションの基本プロパティを構成にするには、少なくとも[クラウド アプリケーション管理者](https://developer.microsoft.com/graph/graph-explorer)として [Graph Explorer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。

`Application.ReadWrite.All` アクセス許可に同意する必要があります。

次の Microsoft Graph クエリを実行して、アプリケーションの基本プロパティを構成します。

```http
PATCH https://graph.microsoft.com/v1.0/applications/00001111-aaaa-2222-bbbb-3333cccc4444/
Content-type: application/json

{
    "tags": [
        "HR",
        "Payroll",
        "HideApp"
    ],
    "info": {
        "logoUrl": "https://cdn.pixabay.com/photo/2016/03/21/23/25/link-1271843_1280.png",
        "marketingUrl": "https://www.contoso.com/app/marketing",
        "privacyStatementUrl": "https://www.contoso.com/app/privacy",
        "supportUrl": "https://www.contoso.com/app/support",
        "termsOfServiceUrl": "https://www.contoso.com/app/termsofservice"
    },
    "web": {
        "homePageUrl": "https://www.contoso.com/",
        "logoutUrl": "https://www.contoso.com/frontchannel_logout",
        "redirectUris": [
            "https://localhost"
        ]
    },
    "serviceManagementReference": "Owners aliases: Finance @ contosofinance@contoso.com; The Phone Company HR consulting @ hronsite@thephone-company.com;"
}
```

::: zone-end

エンタープライズ アプリケーション (サービス プリンシパル) は、関連付けられているアプリの登録から特定のプロパティを継承します。 これらのプロパティはアプリ登録から同期されますが、同期は即時または継続的ではありません。 エンタープライズ アプリケーションを更新すると、アプリの登録からプロパティを更新するようにディレクトリに求められる場合があり、元の要求に含まれなかった更新が発生することがあります。

注

マネージド ID は、Microsoft Entra アプリの登録とは異なります。 マネージド ID にはサービス プリンシパル オブジェクトのみが含まれ、アプリケーション オブジェクトは保持されません。これは通常、アプリのアクセス許可を付与するために使用されます。 その結果、セキュリティ境界はリソース自体であるため、グローバル管理者はマネージド ID の設定を変更できません。

### Microsoft Graph を使用して高度なアプリのプロパティを構成する

Microsoft Graph を使用して、アプリ登録とエンタープライズ アプリケーション (サービス プリンシパル) の両方のその他の詳細プロパティを構成することもできます。 これらのプロパティには、アクセス許可とロールの割り当てが含まれます。 詳細は、「[Microsoft Graph を使用した Microsoft Entra アプリケーションの作成と管理](https://learn.microsoft.com/ja-jp/graph/tutorial-applications-basics#configure-other-basic-properties-for-your-app)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso"} -->
## ギャラリーとカスタム アプリケーションの OIDC SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-oidc-sso
- Service: entra-id / enterprise-apps
- Article date: 2025-07-22
- Summary: ギャラリー アプリケーションと独自のカスタム (ギャラリー以外) アプリケーションの両方に対して、Microsoft Entra ID で OpenID Connect ベースのシングル サインオン (SSO) を構成する方法について説明します。

この記事では、ギャラリー アプリケーションとカスタム (ギャラリー以外) アプリケーションの両方に対して、Microsoft Entra ID で OpenID Connect (OIDC) シングル サインオン (SSO) を構成する方法について説明します。 OIDC SSO を使用すると、ユーザーは Microsoft Entra 資格情報を使用してアプリケーションにサインインし、シームレスな認証エクスペリエンスを提供できます。

OIDC は、セキュリティで保護されたユーザー認証とシングル サインオンを可能にする OAuth 2.0 上に構築された認証プロトコルです。 OIDC プロトコルの詳細については、 [Microsoft ID プラットフォームでの OIDC 認証に関する記事を](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-protocols-oidc)参照してください。

非運用環境を使用して、この記事の手順をテストすることをお勧めします。

OIDC SSO を構成する前に、次の主要な概念を理解しておくことが役立ちます。

- **アプリの登録とエンタープライズ アプリケーション**: アプリの登録によってアプリケーションの ID と構成が定義され、エンタープライズ アプリケーションはテナント内のアプリのインスタンスを表します。 詳細については、「 [Microsoft Entra ID のアプリケーション オブジェクトとサービス プリンシパル オブジェクト」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/app-objects-and-service-principals)参照してください。
- **アクセス許可と同意**: アプリケーションはリソースにアクセスするためのアクセス許可を要求し、ユーザーまたは管理者は同意を付与します。 同意フレームワークの詳細については、 [Microsoft ID プラットフォームでのアクセス許可と同意に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。
- **マルチテナント アプリケーション**: 複数の組織で使用できるアプリケーション。 マルチテナントのガイダンスについては、「 [方法: アプリをマルチテナントに変換する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)を参照してください。
- **認証フロー**: シングルページ アプリケーションの PKCE を使用した承認コード フローなど、ユーザーを認証するためのさまざまな方法。 詳細については、「 [Microsoft ID プラットフォームの認証フロー」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows)参照してください。
- **OIDC SSO: OIDC** プロトコルを使用してアプリケーション間でユーザーを認証するシングル サインオンの方法。 これにより、ユーザーは 1 回サインインし、資格情報を再入力しなくても複数のアプリケーションにアクセスできます。

### 前提条件

OIDC ベースの SSO を構成するには、次が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - サービス プリンシパルの所有者
- カスタム アプリケーションの場合: リダイレクト URI や認証要件など、アプリケーションに関する詳細

### Microsoft Entra アプリ ギャラリー アプリの OIDC SSO を構成する

Microsoft Entra ID のギャラリー アプリケーションには OIDC サポートが事前に構成されており、同意ベースのプロセスを通じてセットアップが簡単になります。

SSO に OIDC 標準を使用するエンタープライズ アプリケーションを追加する場合は、[ **サインアップ** ] ボタンを選択します。 アプリ ギャラリーからアプリを選択すると、右側のウィンドウにボタンが表示されます。 このボタンを選択すると、アプリケーションのサインアップ プロセスが完了します。

ギャラリー アプリケーションの OIDC ベースの SSO を構成するには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com) 以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. **すべてのアプリケーション** メニューで、**新しいアプリケーション** を選択します。
4. [ **Microsoft Entra ギャラリーの参照** ] ウィンドウが開きます。 この例では、 **SmartSheet** を使用します。
5. **SmartSheet のサインアップ**を選択します。 Microsoft Entra ID からユーザー アカウントの資格情報を使用してサインインします。 アプリケーションのサブスクリプションを既に持っている場合は、ユーザーの詳細およびテナント情報が検証されます。 アプリケーションがユーザーを確認できない場合は、アプリケーション サービスへのサインアップにリダイレクトされます。

    [Image: アプリケーションの同意画面を完了します。]

    サインイン資格情報を入力すると、同意画面が表示されます。 同意画面には、アプリケーションと必要なアクセス許可に関する情報が表示されます。
6. **[組織の代理として同意する]** を選択して、 **[同意する]** を選択します。 アプリケーションがテナントに追加され、アプリケーションのホーム ページが表示されます。

アプリケーションに必要な追加の構成手順については、アプリケーション ベンダーにお問い合わせください。

注

ギャラリー アプリケーションのインスタンスは 1 つだけ追加できます。 アプリケーションを追加してもう一度同意しようとすると、もう一度テナントに追加することはできません。

ユーザーと管理者の同意の詳細については、「 [ユーザーと管理者の同意について](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant#understand-user-and-admin-consent-and-make-appropriate-code-changes)」を参照してください。 同意フレームワークの包括的な情報については、 [Microsoft ID プラットフォームでのアクセス許可と同意に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。

### カスタム (ギャラリー以外) アプリケーションの OIDC SSO を構成する

Microsoft Entra ギャラリーで使用できないアプリケーションの場合は、アプリケーションを手動で登録して構成する必要があります。 このセクションでは、カスタム アプリケーションで OIDC SSO を設定する手順について説明します。

#### 手順1: アプリケーションを登録する

1. [クラウド アプリケーション管理者](https://entra.microsoft.com) 以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**アプリの登録**&gt;**New registration** に移動します。
3. アプリケーションの **名前** ("My Custom Web App" など) を入力します。
4. [ **サポートされているアカウントの種類**] で、適切なオプションを選択します。
    - **この組織のディレクトリ内のアカウント** は、シングルテナント アプリケーションの場合のみ
    - マルチテナント アプリケーションの詳細については、**任意の組織ディレクトリのアカウント**、マルチ[テナント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)に関するページを参照してください。
    - 職場**/学校アカウントと個人アカウントの**両方をサポートする場合は、組織のディレクトリ内のアカウントと個人の Microsoft アカウント
5. **[リダイレクト URI**] で、プラットフォームの種類を選択し、アプリケーションのリダイレクト URI を入力します。
    - **Web**: サーバー側の Web アプリケーションの場合 (たとえば、 `https://contoso.com/auth/callback`)
    - **シングルページ アプリケーション (SPA):**最新の認証フローを使用するクライアント側アプリケーションの場合 (たとえば、開発用の `https://contoso.com` または `http://localhost:3000` )
    - **パブリック クライアント/ネイティブ**: モバイル アプリケーションとデスクトップ アプリケーション用
6. **登録** を選択します。

#### 手順 2: 認証設定を構成する

1. アプリの登録で、[ **認証**] に移動します。
2. リダイレクト URI がプラットフォームの種類に対して正しく構成されていることを確認します。
3. **認証フローを構成する** (セキュリティにとって重要):

    **Single-Page アプリケーション (SPA) の場合:**

    - **シングルページ アプリケーション** プラットフォームの下にリダイレクト URI が表示されていることを確認します。 このオプションでは、PKCE を使用してセキュリティで保護された **承認コード フロー**が自動的に構成されます。これは、SPA に推奨される方法です。
    - レガシ アプリケーションで必要な場合を除き、暗黙的な許可オプションを**有効にしないでください**

    **Web アプリケーションの場合:**

    - リダイレクト URI が **Web** プラットフォームの下に一覧表示されていることを確認します。 このオプションは、標準の **承認コード フロー**を構成します。

    Warnung

    ブラウザー履歴のトークン漏洩など、セキュリティの脆弱性により、新しいアプリケーションには暗黙的な許可フロー **は推奨されません** 。 代わりに、シングルページ アプリケーションに対して **PKCE で承認コード フロー** を使用することを強くお勧めします。 これらのオプションは、より安全なフローをサポートするために更新できないレガシ アプリケーションがあり、関連するセキュリティ リスクを理解している場合にのみ有効にします。

    認証フローの詳細については、「 [Microsoft ID プラットフォームの認証フロー」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/msal-authentication-flows)参照してください。

#### 手順 3: クライアント資格情報を構成する (Web アプリケーション用)

アプリケーションが機密クライアント (シークレットを安全に格納できるサーバー側 Web アプリケーション) の場合:

1. **[証明書とシークレット]** に移動します。
2. **新しいクライアント シークレット**を選択します。
3. 説明を追加し、有効期限を選択します。
4. [ **追加]** を選択し、シークレット値をすぐにコピーします (再度表示することはできません)。
5. クライアント シークレットをアプリケーション構成に安全に格納します。

ヒント

運用アプリケーションの場合は、セキュリティ強化のためにクライアント シークレットの代わりに証明書を使用することを検討してください。 「 [証明書の資格情報」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/certificate-credentials)参照してください。

#### 手順 4: API のアクセス許可を構成する

1. **API のアクセス許可**に移動します。
2. 既定では、Microsoft Graph の `User.Read` アクセス許可が追加されます。
3. OIDC 認証では、通常、次の委任されたアクセス許可が必要です。
    - **openid**: OIDC 認証に必要
    - **profile**: ユーザーのプロファイル情報にアクセスするには
    - **電子メール**: ユーザーのメール アドレスにアクセスするには
4. アクセス許可を追加するには:
    - [ **アクセス許可の追加] を選択する**
    - **Microsoft Graph を選択する**
    - **委任されたアクセス許可**を選択します
    - 必要なアクセス許可 (openid、プロファイル、電子メールなど) を検索して選択します
    - **[アクセス許可の追加]** を選択する
5. アプリケーションに管理者の同意が必要なアクセス許可が必要な場合は、[ **テナント] に管理者の同意を付与**するを選択します。

アクセス許可と同意の概要については、 [Microsoft ID プラットフォームでのアクセス許可と同意に関するページを](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview)参照してください。

#### 手順 5: 省略可能な要求を構成する (必要な場合)

1. **[トークンの構成]** に移動します。
2. [ **省略可能な要求の追加]** を選択します。
3. 追加する省略可能な要求 ( `email`、 `given_name`、 `family_name`など) を選択します。
4. [ **追加]** を選択して変更を適用します。

#### 手順 6: アプリケーションの詳細を収集する

登録と構成が完了したら、アプリケーションに必要な次の情報を収集します。

1. **[概要**] ページで、次の点に注意してください。
    - **アプリケーション (クライアント) ID**: アプリの一意識別子
    - **ディレクトリ (テナント) ID**: テナントの一意識別子
2. **OIDC**メタデータとエンドポイントを表示するには、[エンドポイント] を選択します。
    - **OIDC メタデータ ドキュメント**: `https://login.microsoftonline.com/{tenant}/v2.0/.well-known/openid_configuration`
    - **承認エンドポイント**: サインイン フローを開始する場合
    - **トークン エンドポイント: トークン**の承認コードを交換する場合
    - **JWKS URI**: トークン署名の検証用

これらの詳細は、アプリケーションの OIDC ライブラリ構成で使用されます。

#### 手順 7: アプリケーション コードを構成する

収集した情報を使用して、次の方法でアプリケーションの OIDC ライブラリを構成します。

- **クライアント ID**: 手順 5 のアプリケーション (クライアント) ID
- **クライアント シークレット**: 該当する場合 (Web アプリケーションの場合)
- **リダイレクト URI**: 手順 1 で構成したのと同じ URI
- **機関/発行者**: `https://login.microsoftonline.com/{tenant}/v2.0/` ({tenant} をテナント ID に置き換えます)
- **スコープ**: 通常、基本的なOIDC認証に使用される`openid profile email`

具体的な実装ガイダンスについては、Web アプリケーションの [PKCE を使用した承認コード フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow) を参照してください。

#### 手順 8: OIDC SSO 構成をテストする

1. **オンライン ツールの使用**: https://jwt.msを使用して基本認証フローをテストできます。

    - サインイン URL を作成します。 `https://login.microsoftonline.com/{tenant}/oauth2/v2.0/authorize?client_id={client_id}&response_type=id_token&redirect_uri=https://jwt.ms&scope=openid&nonce={random_value}`
    - `{tenant}`と`{client_id}`を実際の値に置き換える
    - ブラウザーでこの URL に移動して、認証フローをテストします
2. **アプリケーション内**: OIDC ライブラリをアプリケーションに統合し、完全なサインイン エクスペリエンスをテストします。
3. **ユーザーの割り当て**: **エンタープライズ アプリケーション**に移動し、アプリを見つけて、[ユーザーとグループ] でユーザーまたは **グループを**割り当てます。

### マルチテナント アプリに関する考慮事項

アプリケーションで複数の組織のユーザーをサポートする必要がある場合:

- **任意の組織のディレクトリ内のアカウントでアプリの登録を**構成する
- 共通エンドポイントを使用します。 `https://login.microsoftonline.com/common/`
- アプリケーション ロジックに適切なテナント検証を実装する

詳細なガイダンスについては、「 [方法: アプリをマルチテナントに変換する」](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)を参照してください。

### 一般的な問題のトラブルシューティング

- **無効なリダイレクト URI**: アプリ登録のリダイレクト URI が、アプリケーションから送信されたものと正確に一致していることを確認します
- **同意の問題**: 要求されたアクセス許可に対して管理者の同意が必要かどうかを確認する
- **トークン検証エラー**: 正しい JWKS URI を使用していることを確認し、トークン署名を検証します
- **マルチテナントの問題**: 正しいエンドポイント (一般的なエンドポイントとテナント固有のエンドポイント) を使用していることを確認する

包括的なトラブルシューティング ガイダンスについては、 [Microsoft ID プラットフォームのエラー コードを](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/add-application-portal-setup-sso"} -->
## エンタープライズ アプリケーションの SAML シングル サインオンを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso
- Service: entra-id / enterprise-apps
- Article date: 2025-07-10
- Summary: Microsoft Entra ID でエンタープライズ アプリケーションのシングル サインオンを有効にします。

この記事では、Microsoft Entra 管理センターを使用して、Microsoft Entra テナントに追加したエンタープライズ アプリケーションのシングル サインオン (SSO) を有効にします。 SSO を構成すると、ユーザーは自分の Microsoft Entra 資格情報を使用してサインインできるようになります。

Microsoft Entra ID には、SSO を使用する、あらかじめ統合された何千ものアプリケーションが含まれるギャラリーがあります。 この記事では、 **Microsoft Entra SAML Toolkit 1** という名前のエンタープライズ アプリケーションを例として使用しますが、概念は Microsoft Entra アプリケーション ギャラリーのほとんどの構成済みエンタープライズ アプリケーションに適用されます。

アプリケーションがシングル サインオンのために Microsoft Entra ID と直接統合されておらず、代わりに証明書利用者セキュリティ トークン サービス (STS) によってトークンがアプリケーションに提供される場合は、証明書利用者セキュリティ トークン サービスを使用して [エンタープライズ アプリケーションのシングル サインオンを有効にする方法に関する記事を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso-rpsts)参照してください。

この記事の手順をテストするには、非運用環境を使用することをお勧めします。

### 前提条件

SSO を構成するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者、またはサービス プリンシパルの所有者。
- [「クイック スタート: ユーザー アカウントを作成して割り当てる」の手順を完了します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)。

注

SAML SSO は、シングル テナント アプリケーションまたはギャラリー アプリケーションでのみ構成できます。 マルチテナント アプリケーションでは、SAML SSO 構成が淡色表示されます。

### シングル サインオンの有効化

アプリケーション用に SSO を有効にするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** に移動します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。 たとえば、 **Microsoft Entra SAML Toolkit 1** などです。
4. 左側のメニューの [ **管理** ] セクションで、[ **シングル サインオン** ] を選択して、編集用の **[シングル サインオン** ] ウィンドウを開きます。
5. **SAML** を選択して SSO 構成ページを開きます。 アプリケーションの構成が済むと、ユーザーは Microsoft Entra テナントから自分の資格情報を使用してそれにサインインできるようになります。
6. SAML ベースの SSO に Microsoft Entra ID を使用するようにアプリケーションを構成するプロセスは、アプリケーションによって異なります。 ギャラリー内のエンタープライズ アプリケーションの場合は、 **構成ガイド** のリンクを使用して、アプリケーションの構成に必要な手順に関する情報を見つけます。 この記事では、 **Microsoft Entra SAML Toolkit 1** の手順を示します。

    [Image: エンタープライズ アプリケーションのシングル サインオンを構成する方法を示すスクリーンショット。]
7. [ **Microsoft Entra SAML Toolkit 1 のセットアップ** ] セクションで、後で使用する **ログイン URL**、 **Microsoft Entra 識別子**、および **ログアウト URL** プロパティの値を記録します。

### テナントでシングル サインオンを構成する

サインインと応答 URL の値を追加し、証明書をダウンロードして Microsoft Entra ID で SSO の構成を開始します。

Microsoft Entra ID で SSO を構成するには:

1. Microsoft Entra 管理センターで、[SAML を使用**したシングル Sign-On の設定**] ウィンドウの [**基本的な SAML 構成**] セクションで **[編集]** を選択します。
2. **応答 URL (Assertion Consumer Service URL)** に「`https://samltoolkit.azurewebsites.net/SAML/Consume`」と入力します。
3. [ **サインオン URL] に**「 `https://samltoolkit.azurewebsites.net/`」と入力します。 **識別子 (エンティティ ID)** は、通常、統合するアプリケーションに固有の URL です。 この例の **Microsoft Entra SAML Toolkit 1** アプリケーションでは、サインオン URL と**応答** URL の値を入力すると、値が自動的**に**生成されます。 統合対象のアプリケーションの構成ガイドに従って正しい値を確認してください。
4. **[保存] を選択します**。
5. [**SAML 証明書**] セクションで、[**証明書のダウンロード (未加工)]** を選択して SAML 署名証明書をダウンロードし、後で使用するために保存します。

### アプリケーションでシングル サインオンを構成する

アプリケーションでシングル サインオンを使用するには、ユーザー アカウントをアプリケーションに登録し、前に記録した SAML 構成の値を追加する必要があります。

#### ユーザー アカウントを登録する

ユーザー アカウントをアプリケーションに登録するには:

1. 新しいブラウザー ウィンドウを開き、アプリケーションのサインイン URL を参照します。 **Microsoft Entra SAML Toolkit** アプリケーションの場合、アドレスは`https://samltoolkit.azurewebsites.net`。
2. ページの右上隅にある [ **登録** ] を選択します。
3. [ **電子メール]** に、アプリケーションにアクセスできるユーザーのメール アドレスを入力します。 ユーザー アカウントがアプリケーションに既に割り当てられていることを確認します。
4. **パスワード**を入力して確認します。
5. [ **登録**] を選択します。

#### SAML の設定を構成する

アプリケーションの SAML 設定を構成するには:

1. アプリケーションのサインイン ページで、アプリケーションに既に割り当てたユーザー アカウントの資格情報でサインインし、ページの左上隅にある **[SAML 構成]** を選択します。
2. ページの中央にある [ **作成** ] を選択します。
3. **[ログイン URL]**、[**Microsoft Entra Identifier]**、[**ログアウト URL]** に、前に記録した値を入力します。
4. [ **ファイルの選択] を選択** して、以前にダウンロードした証明書をアップロードします。
5. **作成** を選択します。
6. 後で使用する **SP 開始ログイン URL** と **アサーション コンシューマー サービス (ACS) URL の** 値をコピーします。

### シングル サインオンの値を更新する

**SP Initiated Login URL** と **Assertion Consumer Service (ACS) URL** に記録した値を使用して、テナントのシングル サインオン値を更新します。

シングル サインオンの値を更新するには:

1. Microsoft Entra 管理センターで、[**シングル サインオンの設定**] ウィンドウの [**基本的な SAML 構成**] セクションで **[編集**] を選択します。
2. **応答 URL (Assertion Consumer Service URL)** には、前に記録した**アサーション コンシューマー サービス (ACS) の URL** 値を入力します。
3. **[サインオン URL] に**、前に記録**した SP 開始ログイン URL** の値を入力します。
4. **[保存] を選択します**。

### シングル サインオンのテスト

シングル サインオンの構成は、[シングル サインオンの **設定** ] ウィンドウからテストできます。

SSO をテストするには:

1. **[Microsoft Entra SAML Toolkit 1 でのシングル サインオンのテスト**] セクションの [**SAML でのシングル サインオンの設定**] ウィンドウで、[**テスト**] を選択します。
2. アプリケーションに割り当てたユーザー アカウントの Microsoft Entra 資格情報を使用して、アプリケーションにサインインします。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/add-application-portal-setup-sso-rpsts"} -->
## 証明書利用者セキュリティ トークン サービスを使用してエンタープライズ アプリケーションのシングル サインオンを有効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso-rpsts
- Service: entra-id / enterprise-apps
- Article date: 2025-02-01
- Summary: Microsoft Entra ID で証明書利用者セキュリティ トークン サービスを持つエンタープライズ アプリケーションのシングル サインオンを有効にします。

この記事では、Microsoft Entra 管理センターを使用して、証明書利用者セキュリティ トークン サービス (STS) に依存するエンタープライズ アプリケーションに対してシングル サインオン (SSO) を有効にします。 証明書利用者 STS は、セキュリティ アサーション マークアップ言語 (SAML) をサポートしており、エンタープライズ アプリケーションとして Microsoft Entra と統合できます。 SSO を構成した後、ユーザーは Microsoft Entra 資格情報を使用してアプリケーションにサインインできます。

[Image: アプリケーション、証明書利用者 STS、および ID プロバイダーとしての Microsoft Entra ID の間の信頼関係を示す図。]

アプリケーションがシングル サインオンのために Microsoft Entra と直接統合され、証明書利用者セキュリティ トークン サービス (STS) を必要としない場合は、「 [エンタープライズ アプリケーションのシングル サインオンを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)」の記事を参照してください。

運用テナントでアプリケーションを構成する前に、非運用環境を使用してこの記事の手順をテストすることをお勧めします。

### Prerequisites

SSO を構成するには、次のものが必要です。

- HTTPS エンドポイントを持つ Active Directory フェデレーション サービス (AD FS) や PingFederate などの証明書利用者サービス STS
    1. 証明書利用者 STS のエンティティ識別子 (エンティティ ID) が必要です。 これは、Microsoft Entra テナントで構成されているすべての証明書利用者 STS およびアプリケーションで一意である必要があります。 同じエンティティ識別子を持つ 1 つの Microsoft Entra テナントに 2 つのアプリケーションを含めることはできません。 たとえば、Active Directory フェデレーション サービス (AD FS) が証明書利用者 STS である場合、識別子は `http://{hostname.domain}/adfs/services/trust`形式の URL である可能性があります。
    2. 依存パーティ STS のアサーション コンシューマー サービス URL (または応答 URL) も必要です。 この URL は、アプリケーションへのシングル サインオンの一環として、Microsoft Entra から証明書利用者 STS に SAML トークンを安全に転送するための `HTTPS` URL である必要があります。 たとえば、AD FS が証明書利用者 STS の場合、URL は `https://{hostname.domain}/adfs/ls/`形式である可能性があります。
- 既にその依存先用の STS と統合されているアプリケーション
- Microsoft Entra の次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者
- アプリケーションにサインインできる Microsoft Entra のテスト ユーザー

Note

このチュートリアルでは、1 つの Microsoft Entra テナント、1 つの証明書利用者 STS、および証明書利用者 STS に接続された 1 つのアプリケーションがあることを前提としています。 このチュートリアルでは、証明書利用者 STS によって提供されるエンティティ識別子を使用して適切なエンタープライズ アプリケーションを決定し、応答で SAML トークンを送信するように Microsoft Entra を構成する方法について説明します。 1 つの証明書利用者 STS に複数のアプリケーションが接続されている場合、Microsoft Entra は SAML トークンを発行するときにこれら 2 つのアプリケーションを区別できません。 異なるエンティティ識別子の構成は、このチュートリアルの範囲外です。

### Microsoft Entra でアプリケーションを作成する

まず、Microsoft Entra でエンタープライズ アプリケーションを作成します。これにより、Microsoft Entra は証明書利用者 STS がアプリケーションに提供する SAML トークンを生成できます。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. Browse to **Entra ID**&gt;**Enterprise apps**&gt;**All applications**.
3. 証明書利用者 STS を表すアプリケーションを既に構成している場合は、検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択して、次のセクションに進みます。
4. Select **New application**.
5. [独自のアプリケーション 作成] を選択します。
6. 入力名ボックスに新しいアプリケーションの名前を入力し、[ **ギャラリーに見つからない他のアプリケーションを統合する (ギャラリー以外)]**、[ **作成**] の順に選択します。

### アプリケーションでシングル サインオンを構成する

1. In the **Manage** section of the left menu, select **Single sign-on** to open the **Single sign-on** pane for editing.
2. Select **SAML** to open the SSO configuration page.
3. [ **基本的な SAML 構成** ] ボックスで、[編集] を選択 **します**。 さらに SAML 構成を変更する前に、識別子と応答 URL を設定する必要があります。 [Image: エンタープライズ アプリケーションのシングル サインオンに必要な基本的な SAML 構成を示すスクリーンショット。]
4. [基本的な SAML 構成] ページの [ **識別子 (エンティティ ID)]** で、識別子が一覧に表示されていない場合は、[ **識別子の追加]** を選択します。 証明書利用者 STS によって提供されるアプリケーションの識別子を入力します。 たとえば、識別子は、フォーム `http://{hostname.domain}/adfs/services/trust`の URL である場合があります。
5. [基本的な SAML 構成] ページの [ **応答 URL (Assertion Consumer Service URL)]** で、[ **応答 URL の追加]** を選択します。 証明書利用者 STS アサーション コンシューマー サービスの HTTPS URL を入力します。 たとえば、URL は `https://{hostname.domain}/adfs/ls/`形式の場合があります。
6. Optionally, configure the **sign on**, **relay state**, or **logout** URLs, if required by the relying party STS.
7. Select **Save**.

### Microsoft Entra からメタデータと証明書をダウンロードする

証明書利用者 STS では、構成を完了するために、ID プロバイダーとして Microsoft Entra からのフェデレーション メタデータが必要になる場合があります。 The federation metadata and associated certificates are provided in the **SAML Certificates** section of the **Basic SAML configuration** page. For more information, see [federation metadata](https://learn.microsoft.com/ja-jp/entra/identity-platform/federation-metadata).

[Image: エンタープライズ アプリケーションの SAML 署名証明書とフェデレーション メタデータのダウンロード オプションを示すスクリーンショット。]

- 証明書利用者 STS がインターネット エンドポイントからフェデレーション メタデータをダウンロードできる場合は、 **アプリのフェデレーション メタデータ URL** の横にある値をコピーします。
- If your relying party STS requires a local XML file containing the federation metadata, then select **Download** next to **Federation Metadata XML**.
- If your relying party STS requires the certificate of the identity provider, then select **Download** next to either the **Certificate (Base64)** or **Certificate (Raw)**.
- If your relying party STS does not support federation metadata, then copy the **Login URL** and **MIcrosoft Entra Identifier** to configure your relying party STS. [Image: エンタープライズ アプリケーションの Microsoft Entra ログイン URL と識別子を示すスクリーンショット。]

### Microsoft Entra によって発行された要求を設定する

既定では、Microsoft Entra ユーザーからのいくつかの属性のみが、Microsoft Entra が証明書利用者 STS に送信する SAML トークンに含まれます。 アプリケーションに必要な要求を追加し、SAML 名識別子で指定された属性を変更できます。 標準要求の詳細については、「 [SAML トークン要求リファレンス」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-saml-tokens)。

1. [ **属性と要求** ] ボックスで、[編集] を選択 **します**。
2. 名前識別子の値として送信される Entra ID 属性を変更するには、[ **一意のユーザー識別子 (名前 ID)]** 行を選択します。 ソース属性を別の Microsoft Entra 組み込み属性または拡張属性に変更できます。 Then select **Save**.
3. To change which Entra ID attribute is sent as the value of a claim already configured, select the row in the **Additional claims** section.
4. 新しい要求を追加するには、[ **新しい要求の追加]** を選択します。
5. When complete, select **SAML-based Sign-on** to close this screen.

### アプリケーションにサインインできるユーザーを構成する

構成をテストするときは、指定されたテスト ユーザーを Microsoft Entra のアプリケーションに割り当てて、ユーザーが Microsoft Entra と証明書利用者 STS を介してアプリケーションにサインオンできることを検証する必要があります。

1. In the **Manage** section of the left menu, select **Properties**.
2. **ユーザーがサインインできるように [有効]** の値が [**はい**] に設定されていることを確認します。
3. Ensure that the value of **Assignment required** is set to **Yes**.
4. If you made any changes, select **Save**.
5. In the **Manage** section of the left menu, select **Users and groups**.
6. Select **Add user/group**.
7. Select **None selected**.
8. In the search box, type the name of the test user, then pick the user and select **Select**.
9. Select **Assign** to assign the user to the default **User** role of the application.
10. In the **Security** section of the left menu, select **Conditional Access**.
11. Select **What if**.
12. [ **ユーザーまたはサービス プリンシパルが選択されていません**] を選択し、[ **ユーザーが選択されていません**] を選択し、以前にアプリケーションに割り当てられたユーザーを選択します。
13. **任意のクラウド アプリ**を選択し、エンタープライズ アプリケーションを選択します。
14. Select **What if**. 適用されるすべてのポリシーで、ユーザーがアプリケーションにサインインすることを許可していることを検証します。

### 証明書利用者 STS で ID プロバイダーとして Microsoft Entra を構成する

次に、フェデレーション メタデータを証明書利用者 STS にインポートします。 AD FS を使用して次の手順を示しますが、代わりに別の証明書利用者 STS を使用できます。

1. 証明書利用者 STS のクレーム プロバイダー信頼の一覧で、**[クレームプロバイダー信頼の追加]** を選択し、**[開始]** を選択します。
2. Microsoft Entra からフェデレーション メタデータをダウンロードしたかどうかに応じて、[ **オンラインまたはローカル ネットワークで発行されたクレーム プロバイダーに関するデータをインポート**する] を選択するか、 **または要求プロバイダーに関するデータをファイルからインポート**します。
3. 証明書利用者 STS に Microsoft Entra の証明書を提供する必要がある場合もあります。
4. ID プロバイダーとしての Microsoft Entra の構成が完了したら、次の点を確認します。
    - クレーム プロバイダー識別子は、フォーム `https://sts.windows.net/{tenantid}`の URI です。
    - Microsoft Entra ID グローバル サービスを使用している場合、SAML シングル サインオンのエンドポイントは、 `https://login.microsoftonline.com/{tenantid}/saml2`形式の URI です。 各国のクラウドについては、 [Microsoft Entra 認証と各国のクラウド](https://learn.microsoft.com/ja-jp/entra/identity-platform/authentication-national-cloud)に関する説明を参照してください。
    - 証明書利用者 STS によって Microsoft Entra の証明書が認識されます。
    - 暗号化は構成されていません。
    - Microsoft Entra で構成された要求は、証明書利用者 STS の要求規則マッピングで使用可能として一覧表示されます。 後で要求を追加した場合は、証明書利用者 STS の ID プロバイダーの構成に要求を追加する必要がある場合もあります。

### 信頼する当事者 STS でクレーム規則を構成する

Microsoft Entra が ID プロバイダーとして送信する要求が証明書利用者 STS に認識されたら、それらの要求をアプリケーションで必要な要求にマップまたは変換する必要があります。 AD FS を使用して次の手順を示しますが、代わりに別の証明書利用者 STS を使用できます。

1. 証明書利用者 STS のクレーム プロバイダー信頼リストで、Microsoft Entra のクレーム プロバイダー信頼を選択し、[ **要求規則の編集]** を選択します。
2. For each claim provided by Microsoft Entra and required by your application, select **Add Rule**. 各規則で、アプリケーションの要件に基づいて、[ [**パススルー\] または \[受信要求のフィルター処理**](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/create-a-rule-to-pass-through-or-filter-an-incoming-claim)] または [**\[受信要求の変換**](https://learn.microsoft.com/ja-jp/windows-server/identity/ad-fs/operations/create-a-rule-to-transform-an-incoming-claim)] を選択します。

### アプリケーションへのシングル サインオンをテストする

アプリケーションが Microsoft Entra と証明書利用者 STS で構成された後、ユーザーは Microsoft Entra に認証し、Microsoft Entra によって提供されたトークンを証明書利用者 STS によってアプリケーションに必要なフォームと要求に変換することでサインインできます。

このチュートリアルでは、証明書利用者が開始するシングル サインオン パターンを実装する Web ベースのアプリケーションを使用してサインイン フローをテストする方法について説明します。 詳細については、「 [シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。

[Image: アプリケーション、証明書利用者 STS、および ID プロバイダーとしての Microsoft Entra ID の間の Web ブラウザー リダイレクトを示す図。]

1. Web ブラウザーのプライベート ブラウズ セッションで、アプリケーションに接続し、ログイン プロセスを開始します。 アプリケーションによって Web ブラウザーが証明書利用者 STS にリダイレクトされ、証明書利用者 STS によって、適切な要求を提供できる ID プロバイダーが決定されます。
2. 証明書利用者 STS で、メッセージが表示されたら、Microsoft Entra ID プロバイダーを選択します。 証明書利用者 STS は、Microsoft Entra ID グローバル サービスを使用している場合に `https://login.microsoftonline.com` 、Web ブラウザーを Microsoft Entra ログイン エンドポイントにリダイレクトします。
3. テスト ユーザーの ID を使用して Microsoft Entra にサインインします。この手順では、 アプリケーションにサインインできるユーザーを構成します。 その後、Microsoft Entra はエンティティ識別子に基づいてエンタープライズ アプリケーションを検索し、Web ブラウザーを証明書利用者 STS 応答 URL エンドポイントにリダイレクトし、Web ブラウザーで SAML トークンを転送します。
4. 証明書利用者 STS は、Microsoft Entra によって発行された SAML トークンを検証し、SAML トークンから要求を抽出して変換し、Web ブラウザーをアプリケーションにリダイレクトします。 このプロセスを介して、アプリケーションが Microsoft Entra から必要な要求を受け取っていることを確認します。

### Complete configuration

1. 初期サインオン構成をテストした後、新しい証明書が Microsoft Entra に追加されると、証明書利用者 STS が最新の状態に保たれるようにする必要があります。 証明書利用者 STS によっては、ID プロバイダーのフェデレーション メタデータを監視するための組み込みプロセスがある場合があります。
2. このチュートリアルでは、シングル サインインの構成について説明しました。 証明書利用者 STS が SAML シングル サインアウトをサポートしている場合もあります。この機能の詳細については、「 [単一 Sign-Out SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-out-saml-protocol)」を参照してください。
3. アプリケーションへのテスト ユーザーの割り当てを削除できます。 動的グループやエンタイトルメント管理などの他の機能を使用して、アプリケーションにユーザーを割り当てることができます。 詳細については、「 [クイック スタート: ユーザー アカウントを作成して割り当てる」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users)参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/admin-consent-workflow-faq"} -->
## 管理者の同意ワークフローに関してよく寄せられる質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/admin-consent-workflow-faq
- Service: entra-id / enterprise-apps
- Article date: 2022-05-27
- Summary: 管理者の同意ワークフローに関してよく寄せられる質問 (FAQ) に対する回答を確認します。

### ワークフローを有効にしましたが、機能をテストするときに、アクセスを要求できる新しい "承認が必要" というプロンプトが表示されないのはなぜですか?

この機能を有効にすると、ユーザーに更新プログラムが表示されるまでに最大 60 分かかる場合がありますが、通常は数分以内にすべてのユーザーが利用できます。

### レビュー担当者として、保留中のすべてのリクエストが表示されないのはなぜですか?

レビュー担当者は、レビュー担当者として指定された後に作成された管理者要求のみを表示できます。 最近レビュー担当者として追加された場合、割り当て前に作成された要求は表示されません。

### レビュー担当者として、同じアプリケーションに対して複数の要求が表示される理由

静的同意と動的同意を使用してユーザーのデータへのアクセスを要求するようにアプリケーションが構成されている場合は、2 つの管理者の同意要求が表示されます。 1 つの要求は静的アクセス許可を表し、もう 1 つは動的アクセス許可を表します。

### 要求者として、要求の状態を確認できますか?

いいえ。要求者は、電子メール通知を使用してのみ更新プログラムを受信できます。

### レビュー担当者は、アプリケーションを承認することはできますが、すべてのユーザーに対しては承認できませんか?

管理者の同意を付与し、テナント内のすべてのユーザーにアプリケーションの使用を許可することを懸念している場合は、要求を拒否する必要があります。 その後、アプリケーションへのアクセスを制限することで、管理者の同意を手動で付与できます。 ユーザーの割り当てを要求するようにアプリケーションを構成し、アクセスを制限するためにユーザーまたはグループをアプリケーションに割り当てます。 詳細については、[ユーザーとグループの割り当て方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)に関するページを参照してください。

### ユーザーの割り当てを必要とするアプリケーションがあります。 アプリケーションに割り当てたユーザーは、自分で同意するのではなく、管理者の同意を要求するように求められます。 なぜでしょうか。

"ユーザー割り当てが必要" 設定を使用してアプリケーションへのアクセスが制限されている場合、管理者はアプリケーションによって要求されたすべてのアクセス許可に同意する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/admin-consent-workflow-overview"} -->
## 管理者の同意ワークフローの概要 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/admin-consent-workflow-overview
- Service: entra-id / enterprise-apps
- Article date: 2024-11-29
- Summary: 同意要求に関連する管理者の同意ワークフロー、電子メール通知、監査ログを管理する方法について説明します。

場合によっては、ユーザーが自分の職場アカウントで作成または使用しているアプリケーションのアクセス許可に同意する必要がある場合があります。 ただし、管理者以外のユーザーは、管理者の同意を必要とするアクセス許可に同意することはできません。 また、ユーザーのテナントで [ユーザーの同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent) がオフになっている場合、ユーザーはアプリケーションに同意できません。

ユーザーの同意がオフになっている場合、管理者は、管理者の同意ワークフローを有効にすることで、アプリケーションへのアクセスを要求する権限をユーザーに付与できます。 この記事では、管理者の同意ワークフローがオンのときとオフの場合のユーザーと管理者のエクスペリエンスについて説明します。

ユーザーがサインインしようとすると、次のスクリーンショットのような同意プロンプトが表示されることがあります。

[Image: 管理者の同意ワークフローがオフになっている場合の同意プロンプトのスクリーンショット。]

ユーザーにアクセス権を付与できるユーザーがわからない場合は、アプリケーションを使用できない可能性があります。 この状況では、管理者がアプリケーションの受信を開いている場合に、アプリケーションの要求を追跡する別のワークフローを作成する必要もあります。

管理者は、次のオプションを使用して、ユーザーがアプリケーションに同意する方法を決定できます。

- ユーザーの同意をオフにします。 たとえば、高校では、学校の IT 管理者がテナント内のすべてのアプリケーションを完全に制御できるように、ユーザーの同意をオフにすることができます。
- ユーザーが必要なアクセス許可に同意できるようにします。 テナントに機密データがある場合は、ユーザーの同意を開いたままにすることをお勧めします。
- 特定のアクセス許可に対して管理者専用の同意を保持したいが、ユーザーがアプリケーションのオンボードを支援したい場合は、管理者の同意ワークフローを使用して、管理者の同意要求を評価して応答できます。 これにより、テナントの管理者の同意を求めるすべての要求のキューを作成できます。 これらの要求は、Microsoft Entra 管理センターを通じて直接追跡して応答できます。

    管理者の同意ワークフローを構成する方法については、「[管理者の同意ワークフロー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)を構成する」を参照してください。

### 管理者の同意ワークフローのしくみ

管理者の同意ワークフローを構成すると、ユーザーはプロンプトを通じて直接同意を要求できます。 次のスクリーンショットのような同意プロンプトがユーザーに表示される場合があります。

[Image: 管理者の同意ワークフローが有効になっている場合の同意プロンプトのスクリーンショット。]

管理者が要求に応答すると、ユーザーは要求が処理されたことを示す電子メール アラートを受け取ります。

ユーザーが同意要求を送信すると、Microsoft Entra 管理センターの管理者の同意要求のウィンドウに要求が表示されます。 管理者と、指定されたレビュー担当者はサインインして、[新しい要求を表示し、操作します](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/review-admin-consent-requests)。

レビュー担当者は、テナント内のすべての保留中の管理者の同意要求 (レビュー担当者として指定される前に作成された要求を含む) を表示して操作できます。 保留中の要求は、新しく割り当てられたレビュー担当者に引き続き表示されるため、割り当てられたアクセス許可に基づいて既存の要求を確認してアクションを実行できます。

要求は、管理者の同意要求のウィンドウの次の 2 つのタブに表示されます。

- **保留中**: このタブには、サインインしているユーザーがレビュー担当者として指定されているアクティブな要求が表示されます。 レビュー担当者は要求をブロックまたは拒否できますが、要求されたアクセス許可に同意できる適切なロールベースのアクセス制御 (RBAC) アクセス許可を持つユーザーのみがこれを行うことができます。
- **[すべて (プレビュー)]**: このタブには、テナントに存在するすべての要求 (アクティブまたは期限切れ) が表示されます。 各要求には、アプリケーションに関する情報と、アプリケーションを要求したユーザーが含まれます。

### 電子メール通知

電子メール通知が構成されている場合、すべての校閲者は次の場合に通知を受け取ります。

- 新しい要求が作成されます。
- 要求の有効期限が切れます。
- 要求が有効期限に近づきます。

要求者は、次の場合に電子メール通知を受信します。

- 新しいアクセス要求を送信します。
- 要求の有効期限が切れます。
- 要求が拒否またはブロックされます。
- 要求が承認されます。

### 監査ログ

次の表は、管理者の同意ワークフローで使用できるシナリオと監査値の概要を示しています。

| シナリオ | 監査サービス | 監査カテゴリ | 監査活動 | 監査アクター | 監査ログの制限事項 |
| --- | --- | --- | --- | --- | --- |
| 同意要求ワークフローを有効にする管理者 | アクセス レビュー | ユーザー管理 | ガバナンス ポリシー テンプレートを作成する | アプリ コンテキスト | 現在、ユーザー コンテキストが見つかりません |
| 同意要求ワークフローを無効にする管理者 | アクセス レビュー | ユーザー管理 | ガバナンス ポリシー テンプレートを削除する | アプリ コンテキスト | 現在、ユーザー コンテキストが見つかりません |
| 同意ワークフロー構成を更新する管理者 | アクセス レビュー | ユーザー管理 | ガバナンス ポリシー テンプレートを更新する | アプリ コンテキスト | 現在、ユーザー コンテキストが見つかりません |
| アプリの管理者の同意要求を作成するユーザー | アクセス レビュー | Policy | 要求の作成 | アプリ コンテキスト | 現在、ユーザー コンテキストが見つかりません |
| 管理者の同意要求を承認するレビュー担当者 | アクセス レビュー | ユーザー管理 | ビジネス フロー内のすべての要求を承認する | アプリ コンテキスト | 現在、管理者の同意が付与されたユーザー コンテキストまたはアプリ ID が見つかりません |
| 管理者の同意要求を拒否するレビュー担当者 | アクセス レビュー | ユーザー管理 | ビジネス フロー内のすべての要求を承認する | アプリ コンテキスト | 現在、管理者の同意要求を拒否したアクターのユーザー コンテキストが見つかりません |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/app-gallery-sso-requirements"} -->
## Microsoft Entra アプリ ギャラリーの SSO 要件 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-sso-requirements
- Service: entra-id / enterprise-apps
- Article date: 2026-08-26
- Summary: Microsoft Entra アプリ ギャラリーでアプリケーションを発行するための、SAML と OpenID Connect の要件を確認してください。

Microsoft Entra App Gallery でシングル サインオン (SSO) をサポートするアプリケーションを検証して発行する前に、これらの要件を確認してください。 すべての申請に適用される要件については、「 [アプリを検証して発行するための前提条件」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)。

アプリケーションでユーザー プロビジョニングもサポートされている場合は、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニング要件](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements)」を参照してください。

### SAML SSO の要件

これらの要件は、SSO に Security Assertion Markup Language (SAML) 2.0 を使用するアプリケーションに適用されます。

アプリケーションは、次の認証要件を満たしている必要があります。

- サービス プロバイダー開始モード、ID プロバイダー開始モード、またはその両方で SAML 2.0 プロトコルをサポートします。 (必須)
- SAML トークン証明書キー、証明書の有効性、発行者、対象ユーザー、およびその他の必要なユーザー要求を検証します。 (必須)
- ギャラリー以外のアプリケーションを使用して、SAML とMicrosoft Entra IDの統合をテストします。 (必須)
- [SAML シングル ログアウト](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-out-saml-protocol)をサポートします。 (おすすめ)
- MICROSOFT ENTRA IDが提供する URL から ID プロバイダーの SAML フェデレーション メタデータを取得します。 この方法により、顧客の構成が減り、証明書のローテーションがサポートされます。 詳細については、「 [証明書のローテーションガイダンス」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on#guidance-and-best-practices-for-isvs-on-rotating-certificates)を参照してください。 (おすすめ)
- お客様がアプリケーション インスタンスの SSO の構成に使用できるユーザー インターフェイスと API を提供します。 (おすすめ)
- テナント全体に SSO を適用する方法を提供します。 管理者や緊急アクセスのシナリオでは、他の認証オプションやバイパス メカニズムをサポートできます。 (おすすめ)

独立系ソフトウェア ベンダー (ISV) は、次の要件も満たす必要があります。

- クラウドでアプリケーションをサービスとしてのソフトウェア (SaaS) として発行するか、お客様が所有して構成できるように、インストールのために顧客に配布します。 (必須)
- App Gallery のオンボードとオンボード後のサポートに関するエンジニアリングとサポートの連絡先を確立します。 (必須)
- SAML SSO を構成するためのドキュメントを公開します。 (必須)
- Azure Governmentや 21Vianet によって運用されるMicrosoft Azureなど、アプリケーションを一覧表示する予定の各クラウドのコンプライアンス要件を満たします。 (必須)

### マルチテナント OIDC SSO の要件

これらの要件は、SSO に OpenID Connect (OIDC) を使用するアプリケーションに適用されます。

アプリケーションは、次の認証要件を満たしている必要があります。

- OpenID Connect をサポートします。 [OAuth 2.0 承認コード フローを使用します](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-auth-code-flow)。 [リソース所有者のパスワード資格情報フローは](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth-ropc)使用しないでください。 [デバイス承認付与フロー](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-device-code)は、シナリオで必要な場合にのみ使用します。 (必須)
- クラウド SaaS [アプリケーションにはマルチテナント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant) を使用します。 サービスとしてのインフラストラクチャ (IaaS) またはサービスとしてのプラットフォーム (PaaS) アーキテクチャを使用して、顧客ごとに個別のアプリケーション インスタンスをデプロイする場合は、 [シングルテナント アプリケーション](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-and-multi-tenant-apps) を使用できます。 (必須)
- 認証には Microsoft ID プラットフォーム v2.0 エンドポイントを使用します。 (必須)
- シナリオに必要な[最小特権の Microsoft Graph アクセス許可](https://learn.microsoft.com/ja-jp/graph/permissions-overview?tabs=http#best-practices-for-using-microsoft-graph-permissions)を要求します。 (Microsoft Graphを使用する場合は必須)
- ユーザーまたは管理者が同意を付与できるように、 [委任されたアクセス許可](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/developer-strategy-delegated-permission) を使用します。 シナリオで必要な場合を除き、 [アプリケーションのアクセス許可](https://learn.microsoft.com/ja-jp/security/zero-trust/develop/developer-strategy-application-permissions) は避けてください。 (Microsoft Graphを使用する場合は必須)
- アプリケーションがクライアント資格情報フローを使用する場合は、クライアント シークレットの代わりに証明書を使用します。 (必須)
- シングルページ アプリケーションの場合は、OAuth 2.0 の暗黙的な許可フローではなく、承認コード フローを使用します。 詳細については、 [暗黙的な許可フローに関するセキュリティの問題を](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-implicit-grant-flow#security-concerns-with-implicit-grant-flow)参照してください。 (おすすめ)

ISV として、次の要件も満たす必要があります。

- クラウドで SaaS としてアプリケーションを発行するか、お客様が所有して構成できるように、インストールのためにアプリケーションを顧客に配布します。 (必須)
- サインイン ページ**に [Microsoft を使用**してサインイン] ボタンを追加し、[アプリケーションのブランド化ガイドライン](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-branding-in-apps)に従います。 (おすすめ)
- Microsoft AI Cloud Partner Program ID を使用して発行元の確認を完了します。 (必須)
- オンボード後のサポートのために、エンジニアリングとサポートの連絡先を確立します。 (必須)
- OIDC と OAuth SSO を構成するためのドキュメントを公開します。 (必須)
- Azure Governmentや 21Vianet によって運用されるMicrosoft Azureなど、アプリケーションを一覧表示する予定の各クラウドのコンプライアンス要件を満たします。 (必須)
- 機密クライアント アプリケーションを使用します。 Microsoft Entra アプリ ギャラリーでは、パブリック クライアント アプリケーションはオンボードされません。

### 顧客のドキュメントを準備する

少なくとも次の情報を含むドキュメントを発行します。

- サポートされているプロトコル、バージョン、SKU、ID プロバイダーなど、SSO 機能の概要。
- ライセンス要件。
- SSO を構成するために必要なロール。
- SAML 構成手順 (予想される値とサービス プロバイダー情報を含む)。
- ビジネス上の正当な理由を持つ OIDC と OAuth のアクセス許可。
- パイロット ユーザーのテスト手順。
- エラー コードやメッセージなどのトラブルシューティング情報。
- サポート オプション。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements"} -->
## Microsoft Entra アプリ ギャラリーのユーザー プロビジョニング要件 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-user-provisioning-requirements
- Service: entra-id / enterprise-apps
- Article date: 2026-08-26
- Summary: Microsoft Entra アプリ ギャラリーでユーザー プロビジョニング統合を発行するための SCIM 要件を確認します。

Microsoft Entra アプリ ギャラリーでユーザー プロビジョニングをサポートするアプリケーションを検証して発行する前に、これらの要件を確認してください。 すべての申請に適用される要件については、「 [アプリを検証して発行するための前提条件」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/v2-howto-app-gallery-listing)。

アプリケーションで SSO もサポートされている場合は、[Microsoft Entra App Gallery の SSO 要件](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-gallery-sso-requirements)を参照してください。

### SCIM API の要件

クロスドメイン ID 管理 (SCIM) API のシステムは、次の要件を満たしている必要があります。

- SCIM 2.0 ユーザーおよびグループ エンドポイントをサポートします。 ユーザー プロビジョニングが必要です。 グループ のプロビジョニングをお勧めします。 (必須)
- ユーザーとグループを遅延なくプロビジョニングおよびプロビジョニング解除できるように、テナントごとに 1 秒あたり少なくとも 25 個の要求をサポートします。 (必須)
- [ギャラリー以外のアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#getting-started)を使用して、ユーザーとグループのプロビジョニングを検証してテストします。 (必須)
- [ギャラリー以外のアプリケーション](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#getting-started)を使用して、クライアント資格情報認証またはサポートされている別の認証方法を検証します。 (必須)
- ユーザーのソフト削除またはハード削除に対応します。 両方の方法をサポートできます。 (必須)
- クエリがユーザーと一致しない場合は、結果が 0 の成功応答を返します。 不適切な要求を返さないでください。 (必須)
- SCIM エンドポイントでのスキーマ検出をサポートします。 (必須)
- 1 つの `PATCH` 要求で複数のグループ メンバーシップの更新をサポートします。 (おすすめ)
- コネクタのパフォーマンスを向上させるために SCIM 一括 API をサポートします。 (おすすめ)

### SCIM 認証の要件

OAuth 2.0 クライアント資格情報またはワークロード ID フェデレーションを使用して、SCIM エンドポイントに対するMicrosoft Entra プロビジョニング サービスを認証します。 Microsoftでは、基本認証、有効期間の長いベアラー トークン、または承認コード付与フローを使用する SCIM プロビジョニング アプリケーションはオンボードされません。

#### OAuth 2.0 クライアント資格情報

OAuth 2.0 クライアント資格情報を使用する場合は、次の要件を満たします。

- クライアント ID、クライアント シークレット、トークン エンドポイント、SCIM エンドポイントを顧客に提供します。 (必須)
- クライアント シークレットを 1 年から 3 年後に期限切れに設定します。 資格情報の有効期限が切れた場合は、アクセス トークンを発行しないでください。 (必須)
- 複数のアクティブなシークレットと古いシークレットの削除を許可することで、シークレットのスムーズなローテーションを有効にします。 または、顧客が新しいクライアント ID とクライアント シークレットを作成できるようにします。 (必須)
- 発行後 60 分から 6 時間の間に有効期限が切れるアクセス トークンを設定します。 (必須)

実装ガイダンスについては、 [OAuth 2.0 クライアント資格情報付与フローを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/use-scim-to-provision-users-and-groups#oauth-20-client-credentials-grant-flow)参照してください。

#### ワークロード アイデンティティ フェデレーション

ワークロード ID フェデレーションを使用すると、Microsoft Entra プロビジョニング サービスは、有効期間の長いシークレットを格納せずに SCIM エンドポイントに対して認証を行うことができます。 Microsoft Entra IDは、[RFC 7523](https://datatracker.ietf.org/doc/html/rfc7523) の OAuth 2.0 JWT ベアラー プロファイルを使用して、トークン エンドポイントに署名された JSON Web トークン (JWT) アサーションを提示します。 トークン エンドポイントはアサーションを検証し、SCIM エンドポイントの有効期間が短いアクセス トークンを返します。

ワークロード ID フェデレーションをサポートするには、Microsoftの発行された JSON Web キー セットに対してMicrosoft Entra発行された JWT を検証し、SCIM エンドポイントをスコープとするアクセス トークンを発行します。 構成フロー、トークン要求、実装の要件については、 [SCIM プロビジョニングのワークロード ID フェデレーションを](https://github.com/AzureAD/SCIMReferenceCode/blob/master/Workload-Identity-Federation-for-SCIM-Provisioning.md)参照してください。 (おすすめ)

### ISV の要件

独立系ソフトウェア ベンダー (ISV) は、次の要件を満たす必要があります。

- App Gallery のオンボード、オンボード後のサポート、Microsoftからの将来の通信に関するエンジニアリングとサポートの連絡先を確立します。 (必須)
- SCIM エンドポイントのドキュメントを公開します。 (必須)
- ギャラリー以外のMicrosoft Entraアプローチを使用して、SCIM プロビジョニング統合を少なくとも 100 人の相互顧客にデプロイします。
- Azure Governmentや 21Vianet によって運用されるMicrosoft Azureなど、アプリケーションを一覧表示する予定の各クラウドのコンプライアンス要件を満たします。 (必須)

### 既知の制限

統合 [を送信する前に、アプリケーション のプロビジョニングに関する既知の問題](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/known-issues?pivots=app-provisioning) を確認してください。

### 統合を検証する

SCIM エンドポイントがこれらの要件を満たしたら、Microsoft Entra プロビジョニング サービスに対して検証し、ギャラリーの申請で結果を送信します。 手順については、「[Microsoft Entra アプリ ギャラリーのユーザー プロビジョニングの検証](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/validate-user-provisioning-app-gallery)」を参照してください。

### 顧客のドキュメントを準備する

少なくとも次の情報を含むドキュメントを発行します。

- プロビジョニング機能の概要。
- ライセンス要件。
- プロビジョニングを構成するために必要なロール。
- SCIM エンドポイントとサポートされているリソースと属性。
- 認証のセットアップと資格情報のローテーションの手順。
- パイロット ユーザーのテスト手順。
- エラー コードやメッセージなどのトラブルシューティング情報。
- サポート オプション。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/app-management-powershell-samples"} -->
## アプリケーション管理の PowerShell サンプル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-management-powershell-samples
- Service: entra-id / enterprise-apps
- Article date: 2025-01-23
- Summary: これらの PowerShell サンプルは、Microsoft Entra テナントで管理するアプリに使用されます。 これらのサンプル スクリプトを使用して、シークレットと証明書に関する有効期限情報を見つけることができます。

次の表に、Microsoft Entra Application Management の PowerShell スクリプトの例へのリンクを示します。

これらのサンプルには、[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) SDK モジュールが必要です。

| リンク | 説明 |
| --- | --- |
| **アプリケーション管理スクリプト** |  |
| [シークレットと証明書 (アプリの登録)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-all-app-registrations-secrets-and-certs) をエクスポートする | Microsoft Entra テナントでのアプリ登録用のシークレットと証明書をエクスポートします。 |
| [シークレットと証明書 (エンタープライズ アプリ)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-all-enterprise-apps-secrets-and-certs) をエクスポートする | Microsoft Entra テナントでエンタープライズ アプリのシークレットと証明書をエクスポートします。 |
| [期限切れのシークレットと証明書 (アプリの登録)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-apps-with-expiring-secrets) をエクスポートする | 期限切れのシークレットと証明書、および Microsoft Entra テナントの所有者を含むアプリの登録をエクスポートします。 |
| [期限切れのシークレットと証明書 (エンタープライズ アプリ)](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-enterprise-apps-with-expiring-secrets) をエクスポートする | 期限切れのシークレットと証明書、および Microsoft Entra テナントの所有者を含むエンタープライズ アプリをエクスポートします。 |
| [必要な日付を超えて期限切れのシークレットと証明書をエクスポート](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/scripts/powershell-export-apps-with-secrets-beyond-required) | Microsoft Entra テナントで必要な日付を超えて期限切れのシークレットと証明書を含むアプリ登録をエクスポートします。 このシナリオでは、非インターアクティブ Client\_Credentials Oauth フローを使用します。 |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/app-perms-audit-logs"} -->
## アプリケーションのアクセス許可のアクティビティ ログを表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-perms-audit-logs
- Service: entra-id / enterprise-apps
- Article date: 2025-04-28
- Summary: ディレクトリ内のアプリケーションに対して付与および取り消されているアクセス許可のアクティビティ ログを表示する方法について説明します。

Microsoft Entra は、組織のアプリケーションを作成および管理できるプラットフォームです。 データへのアクセスやアクションの実行など、さまざまなアクセス許可をアプリケーションに付与できます。 これらのアクセス許可を定期的に確認して、適切で安全な状態を維持することが重要です。

アプリに付与されたアクセス許可を確認する 1 つの方法は、アクティビティ ログを使用して、Microsoft Entra アプリケーションで発生するアクティビティとイベントを記録することです。 アクティビティ ログは、アプリケーションの使用状況とパフォーマンスを監視し、潜在的な問題やリスクを特定するのに役立ちます。 アクティビティ ログを確認することで、アプリケーションに与えるアクセス許可と、アプリケーションがポリシーと期待に準拠しているかどうかを確認できます。

この記事では、次のことを行います：

- アクティビティ ログを表示して、特定のアプリケーションのアクティビティを許可および削除する API アクセス許可を確認します。
- アクティビティ ログを表示して、すべてのアプリケーションのアクティビティを許可および削除する API アクセス許可を確認します。
- アプリからアプリへの API アクセス許可の付与と削除を追跡するために使用される監査ログについて説明します。

### [前提条件]

アプリケーションのアクティビティ ログを表示するには、次のものが必要です。

- ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: レポート閲覧者、セキュリティ閲覧者、セキュリティ管理者、グローバル閲覧者

### ディレクトリ内のすべてのアプリケーションのアクセス許可監査ログを表示する方法

アプリケーションのアクセス許可アクティビティを表示するには、アクティビティ ログに記録された特定のイベントのみが必要です。 Microsoft Entra 管理センターを使用してすべてのイベントを表示するには、次の手順を実行します。

1. 少なくとも[レポート閲覧者](https://entra.microsoft.com)ロールを使用して [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)にサインインする
2. **Entra ID**&gt;**Enterprise アプリ**をブラウズします。
3. **[アクティビティ**] の下の左側のナビゲーションで、[**監査ログ**] を参照します。
4. [監査ログ] セクションに含まれる情報を使用して 監査ログ をフィルター処理し、アプリケーションのアクセス許可アクティビティを表示するために必要なログのみを選択します。
5. 上部のコマンド バーの **[管理] ビュー** を使用して、表示される列を編集します。 [ **日付]** 列を選択すると、監査ログごとの詳細情報が表示されます。

### 特定のリソース アプリケーションのアクセス許可監査ログを表示する方法

リソース アプリケーションのアクティビティを詳しく調べ、API アクセス許可を使用して既にアクセス権を持っているアプリケーションを確認すると役立ちます。 たとえば、Microsoft Graph アプリケーションのアクティビティ ログを監視して、保護するリソースに対してアクセス許可が付与されるタイミングを確認できます。

リソース アプリケーションのアクティビティ ログを表示するには:

1. 少なくとも[レポート閲覧者](https://entra.microsoft.com)ロールを使用して [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#reports-reader)にサインインする
2. **Entra ID**&gt;**Enterprise アプリ**をブラウズします。
3. アクセス許可を所有するリソース アプリケーションを検索します。 たとえば、過去 30 日間に Microsoft Graph `Mail.Read` アクセス許可が付与されたアプリケーションを表示する場合は、 *Microsoft Graph* を検索します。
4. **[アクティビティ**] の下の左側のナビゲーションで、[**監査ログ**] を参照します。
5. [監査ログ] セクションに含まれる情報を使用して 監査ログ をフィルター処理し、アプリケーションのアクセス許可アクティビティを表示するために必要なログのみを選択します。
6. 上部のコマンド バーの **[管理] ビュー** を使用して、表示される列を編集します。 [ **日付]** 列を選択すると、監査ログごとの詳細情報が表示されます。

### 監査ログ

次の表は、アプリに付与されたアクセス許可の付与と取り消しに使用できるシナリオと監査値の概要を示しています。

| シナリオ | 監査サービス | 監査のカテゴリ | 監査アクティビティ | 監査アクター | 監査ログの制限事項 |
| --- | --- | --- | --- | --- | --- |
| アプリ専用アクセス権をアプリに付与する | Core Directory (コア ディレクトリ) | アプリケーション管理 | サービス プリンシパルにアプリ ロールの割り当てを追加する | ユーザー コンテキスト |  |
| アプリへのアプリ専用アクセスの取り消し | Core Directory (コア ディレクトリ) | アプリケーション管理 | サービス プリンシパルからアプリ ロールの割り当てを削除する | ユーザー コンテキスト |  |
| 委任されたアクセス権をアプリに付与する | Core Directory (コア ディレクトリ) | アプリケーション管理 | 委任されたアクセス許可の付与の追加 | ユーザー コンテキスト |  |
| ユーザーがアプリケーションに同意を付与する | Core Directory (コア ディレクトリ) | アプリケーション管理 | アプリケーションへの同意 | ユーザー コンテキスト |  |
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-list"} -->
## ID 管理用のテナントを使用してアプリを表示する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-list
- Service: entra-id / enterprise-apps
- Article date: 2023-07-14
- Summary: ID 管理用の Microsoft Entra テナントを使用してすべてのアプリを表示する方法について説明します。

「[アプリケーション管理のクイックスタート シリーズ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal)」では、基本情報を紹介します。 ID 管理用の Microsoft Entra テナントを使用して、すべてのアプリを表示する方法について説明します。 この記事では、表示されるアプリの種類についてもう少し詳しく説明します。

### 特定のアプリケーションがすべてのアプリケーション リストに表示される理由

**[すべてのアプリケーション]** にフィルターすると、**[すべてのアプリケーションの** **一覧]** に、テナントのすべてのサービス プリンシパル オブジェクトが表示されます。 サービス プリンシパル オブジェクトは、さまざまな方法でこのリストに表示できます。

- アプリケーション ギャラリーから、次のようなアプリケーションを追加する場合:

    - **Microsoft Entra ID - エンタープライズ アプリケーション** – Microsoft Entra 管理センターで **[エンタープライズ アプリケーション]** オプションを使用してテナントに追加されたアプリ。 通常、SAML 標準を使用して統合されたアプリです。
    - **Microsoft Entra ID - アプリの登録** – Microsoft Entra 管理センターで **[アプリの登録]** オプションを使用してテナントに追加されたアプリ。 通常、OpenID Connect と OAuth 標準を使用してカスタム開発されたアプリです。
    - **アプリケーション プロキシ アプリケーション** - 外部へのセキュリティで保護されたシングル サインオンを提供するオンプレミスの環境で実行されるアプリケーション
- Microsoft Entra ID と統合されたサードパーティー製アプリケーションにサインアップまたはサインインする場合。 一例として、[Smartsheet](https://app.smartsheet.com/b/home) または [DocuSign](https://www.docusign.net/member/MemberLogin.aspx) があります。
- Microsoft 365 などの Microsoft アプリ。
- Azure リソースのマネージド ID を使用する場合。 詳細については、[マネージド ID の種類](https://learn.microsoft.com/ja-jp/entra/identity/managed-identities-azure-resources/overview#managed-identity-types)に関するページを参照してください。
- [アプリケーション レジストリ](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)でカスタム開発アプリケーションを作成して、新しいアプリケーション登録を追加する場合
- [V2.0 アプリケーション登録ポータル](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)でカスタム開発アプリケーションを作成して、新しいアプリケーション登録を追加する場合
- Visual Studio の [ASP.NET の認証方法](https://learn.microsoft.com/ja-jp/aspnet/core/security/authentication/identity?tabs=visual-studio)または [接続済みサービス](https://devblogs.microsoft.com/visualstudio/connecting-to-cloud-services/)を使用して開発しているアプリケーションを追加する場合
- [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) モジュールを使用してサービス プリンシパル オブジェクトを作成する場合。
- 管理者としてテナントのデータを[アプリケーションが使用することに同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)する場合
- テナントのデータを[アプリケーションが使用することにユーザーが同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-convert-app-to-be-multi-tenant)する場合
- 特定のサービスがテナントにデータを格納できるようにする場合。 一例として、パスワード リセット ポリシーを安全に格納するためにサービス プリンシパルとしてモデル化されるパスワード リセットがあります。

アプリがディレクトリに追加される方法および理由について詳しくは、「[アプリケーションを Microsoft Entra ID に追加する方法](https://learn.microsoft.com/ja-jp/entra/identity-platform/how-applications-are-added)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-management-certs-faq"} -->
## アプリケーションの管理証明書に関してよく寄せられる質問 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-management-certs-faq
- Service: entra-id / enterprise-apps
- Article date: 2025-05-21
- Summary: Microsoft Entra ID を ID プロバイダー (IdP) として使用するアプリの証明書の管理についてよく寄せられる質問 (FAQ) とその回答について説明します。

このページでは、Microsoft Entra ID を ID プロバイダー (IdP) として使用するアプリの証明書の管理についてよく寄せられる質問について回答します。

### 間もなく期限が切れる SAML 署名証明書の一覧を生成する方法はありますか?

[PowerShell スクリプト](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-management-powershell-samples)を使用して、ご自分のディレクトリのアプリを指定して、間もなく期限が切れるシークレット、証明書、およびそれらの所有者を含め、すべてのアプリ登録を CSV ファイルにエクスポートします。

### 間もなく期限切れになる証明書の更新手順に関する情報はどこで確認できますか?

手順については、[こちら](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on#renew-a-certificate-that-is-set-to-expire-soon)を参照してください。

### Microsoft Entra ID によって発行された証明書の有効期限をカスタマイズするにはどうすればよいですか?

既定では、Microsoft Entra ID は、SAML シングル サインオン構成中に自動的に作成されてから 3 年後に有効期限が切れる証明書を構成します。 証明書の日付は保存した後は変更できないので、新しい証明書を作成する必要があります。 その方法の手順については、「[フェデレーション証明書の有効期限のカスタマイズと、新しい証明書へのロールオーバー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on#customize-the-expiration-date-for-your-federation-certificate-and-roll-it-over-to-a-new-certificate)」を参照してください。

注

SAML アプリケーションを作成するには、Microsoft Entra アプリケーション ギャラリーを使用することをお勧めします。このギャラリーでは、3 年間の有効な X509 証明書が自動的に作成されます。

### 証明書の有効期限通知を自動化するにはどうすればよいですか?

Microsoft Entra ID では、SAML 証明書の有効期限が切れる 60 日前、30 日前、7 日前に、通知がメールで送信されます。 通知を受信するメール アドレスを複数追加できます。

注

通知一覧に最大 5 つのメールアドレスを追加できます (アプリケーションを追加した管理者のメール アドレスを含む)。 もっと多くのユーザーに通知する必要がある場合は、配布リストのメール アドレスを使用します。

通知送信先のメール アドレスを指定するには、「[証明書の有効期限のメール通知アドレスの追加](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on#add-email-notification-addresses-for-certificate-expiration)」を参照してください。

`aadnotification@microsoft.com`から受信したこれらの電子メール通知を編集またはカスタマイズするオプションは存在しません。 ただし、[PowerShell スクリプト](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/app-management-powershell-samples)を使用して、間もなく期限が切れるシークレットと証明書を含め、アプリ登録をエクスポートできます。

### 誰が証明書を更新できますか?

アプリケーションの所有者またはアプリケーション管理者は、Microsoft Entra 管理センター UI、PowerShell、または Microsoft Graph を使用して証明書を更新できます。

### 証明書署名オプションの詳細について教えてください

Microsoft Entra ID では、証明書署名オプションと証明書署名アルゴリズムを設定できます。 詳細については、[Microsoft Entra アプリ用の詳細な SAML トークンの証明書署名オプション](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/certificate-signing-options)に関するページを参照してください。

### シングル サインオン用の SAML 証明書の構成に使用できる証明書の種類は何ですか?

SAML シングル サインオン証明書で推奨されることは、組織のセキュリティ要件とポリシーによって異なります。 組織に内部証明機関 (PKI) がある場合、内部 PKI の証明書を使用すると、より高いレベルのセキュリティと信頼を提供できます。 内部 PKI がある場合は、その証明書を制御して監視するため、セキュリティと信頼が向上します。

組織が独自の証明機関を実行していない場合は、DigiCert などのパブリック証明機関から証明書を取得します。 組織はこれらの証明機関を信頼し、厳格なセキュリティと検証規則に従ってアプリをセキュリティで保護します。

### Microsoft Entra アプリケーション プロキシ アプリケーションの証明書を置き換える必要があるので、手順について教えてください

Microsoft Entra アプリケーション プロキシ アプリケーションの証明書を置き換えるには、[PowerShell のサンプル - アプリケーション プロキシ アプリケーションでの証明書の置換](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/scripts/powershell-get-custom-domain-replace-cert)に関するページを参照してください。

### Microsoft Entra アプリケーション プロキシでカスタム ドメインの証明書を管理するにはどうすればよいですか?

カスタム ドメインを使うようにオンプレミス アプリを構成するには、検証済みの Microsoft Entra カスタム ドメイン、カスタム ドメインの PFX 証明書、および構成するオンプレミス アプリが必要です。 詳細については、[Microsoft Entra アプリケーション プロキシでのカスタム ドメイン](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/how-to-configure-custom-domain)に関する記事を参照してください。

### アプリケーション側でトークン署名証明書を更新する必要があります。 Microsoft Entra ID 側ではどこで入手できますか?

SAML x.509 証明書を更新できます。「[SAML 署名証明書](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。

### Microsoft Entra ID 署名キーのロールオーバーとは何ですか?

詳細については、 [こちら](https://learn.microsoft.com/ja-jp/entra/identity-platform/signing-key-rollover)を参照してください。

### アプリケーション トークン暗号化証明書を更新するにはどうすればよいですか?

アプリケーション トークン暗号化証明書を更新するには、[エンタープライズ アプリケーションのトークン暗号化証明書を更新する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/howto-saml-token-encryption)方法に関するページを参照してください。

### アプリケーション トークン署名証明書を更新するにはどうすればよいですか?

アプリケーション トークン署名証明書を更新するには、[エンタープライズ アプリケーションのトークン署名証明書を更新する方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)に関するページを参照してください。

### フェデレーション証明書を変更した後に Microsoft Entra ID を更新するにはどうすればよいですか?

フェデレーション証明書を変更した後に Microsoft Entra ID を更新するには、「[Microsoft 365 および Microsoft Entra ID 用のフェデレーション証明書の更新](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-o365-certs)」を参照してください。

### 同じ SAML 証明書を異なるアプリで使用できますか?

エンタープライズ アプリ用に SSO を初めて構成するときは、Microsoft Entra ID 全体で使用される既定の SAML 証明書が提供されます。 ただし、既定の Microsoft Entra ID ではない複数のアプリで同じ証明書を使用する必要がある場合は、外部証明機関を使用して PFX ファイルをアップロードしてください。 その理由は、Microsoft Entra ID が内部で発行された証明書から秘密キーへのアクセスを提供しないためです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-properties"} -->
## エンタープライズ アプリケーションのプロパティ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-properties
- Service: entra-id / enterprise-apps
- Article date: 2025-01-31
- Summary: Microsoft Entra ID でのエンタープライズ アプリケーションのプロパティについて説明します。

この記事では、Microsoft Entra テナントでエンタープライズ アプリケーション用に構成できるプロパティについて説明します。 プロパティを構成するには、「[エンタープライズ アプリケーションのプロパティを構成する」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-configure)を参照してください。

### ユーザーのサインインが有効になっていますか?

このオプションを [はい] に設定すると、割り当てられたユーザーはマイ アプリ ポータル、ユーザー アクセス URL、またはアプリケーション URL に直接移動して、アプリケーションにサインインできます。 割り当てが必要な場合は、アプリケーションに割り当てられているユーザーのみがサインインできます。 割り当てが必要な場合は、トークンを取得するためにアプリケーションを割り当てる必要があります。

このオプションを [なし] に設定すると、アプリケーションに割り当てられている場合でも、ユーザーはアプリケーションにサインインできません。 トークンはアプリケーションに対して発行されません。 この設定は、ユーザーがサインインできないようにするだけでなく、サービス プリンシパルがアプリケーションのアクセス許可を使用してアプリケーションにアクセスできないようにします。

### 名前

このプロパティは、ユーザーがマイ アプリ ポータルに表示するアプリケーションの名前です。 管理者は、アプリケーションへのアクセスを管理するときに名前を表示します。 他のテナントでは、アプリケーションをディレクトリに統合するときに名前が表示されます。

ユーザーが理解できる名前を選択することをお勧めします。 この名前は、マイ アプリや Microsoft 365 Launcher などのさまざまなポータルに表示されるため、重要です。

### ホームページ URL

アプリケーションがカスタム開発されている場合、ホーム ページの URL は、ユーザーがアプリケーションへのサインインに使用できる URL です。 たとえば、マイ アプリ ポータルでアプリケーションが選択されたときに起動される URL です。 このアプリケーションが Microsoft Entra ギャラリーの場合、この URL を使用して、アプリケーションまたはそのベンダーの詳細を確認できます。

エンタープライズ アプリケーション内でホームページ URL を編集することはできません。 ホーム ページの URL は、アプリケーション オブジェクトで編集する必要があります。

### ロゴ

このプロパティは、ユーザーがマイ アプリ ポータルと Office 365 アプリケーション起動ツールに表示するアプリケーション ロゴです。 管理者は、Microsoft Entra ギャラリーにもロゴが表示されます。

カスタム ロゴは、サイズが 215 x 215 ピクセルで、PNG 形式である必要があります。 アプリケーション のロゴに透明度のない単色の背景を使用する必要があります。 ロゴ ファイルのサイズは 100 KB を超えることはできません。

### アプリケーションID

ディレクトリ内のこのプロパティは、アプリケーションのユニークな識別子です。 Microsoft サポートのサポートが必要な場合は、このアプリケーション ID を使用できます。 識別子を使用して、Microsoft Graph API または Microsoft Graph PowerShell SDK を使用して操作を実行することもできます。

### オブジェクト ID

この ID は、アプリケーションに関連付けられているサービス プリンシパル オブジェクトの一意の識別子です。 この識別子は、PowerShell またはその他のプログラム インターフェイスを使用して、このアプリケーションに対して管理操作を実行する場合に役立ちます。 この識別子は、アプリケーション オブジェクトの識別子とは異なります。

この識別子は、アプリケーションへのユーザーとグループの割り当てなど、アプリケーションのローカル インスタンスの情報を更新するために使用されます。 この識別子は、エンタープライズ アプリケーションのプロパティを更新したり、シングル サインオンを構成したりするためにも使用できます。

### 割り当てが必要です

この設定では、アプリケーションのアクセス トークンを取得できるユーザーまたはディレクトリ内の内容を制御します。 この設定を使用して、アプリケーションへのアクセスをさらにロックダウンし、指定されたユーザーとアプリケーションのみがアクセス トークンを取得できるようにします。

このオプションは、アプリケーションがマイ アプリ ポータルに表示されるかどうかを決定します。 そこでアプリケーションを表示するには、適切なユーザーまたはグループをアプリケーションに割り当てます。 このオプションは、他のシングル サインオン モードに対してアプリケーションを構成する場合、ユーザーによるアプリケーションへのアクセスには影響しません。

このオプション **[はい]**に設定されている場合は、ユーザーとその他のアプリケーションまたはサービスにアクセスする前に、まずこのアプリケーションを割り当てる必要があります。

このオプションを **[**なし] に設定すると、すべてのユーザーがサインインでき、他のアプリケーションとサービスはアプリケーションへのアクセス トークンを取得できます。 このオプションでは、組織に招待できる外部ユーザーもサインインできます。

このオプションは、次の種類のアプリケーションとサービスにのみ適用されます。

- セキュリティ アサーション マークアップ言語 (SAML) を使用するアプリケーション
- OpenIDコネクト
- OAuth 2.0 (英語)
- ユーザーサインインに WS-Federation
- Microsoft Entra 事前認証が有効になっているアプリケーション プロキシ アプリケーション
- 他のアプリケーションまたはサービスがアクセス トークンを要求しているアプリケーションまたはサービス

注

グローバル管理者ロールを持つユーザーは、割り当てに必要な設定に関係なく、アプリケーションにサインインできます。

### ユーザーに表示

マイ アプリと Microsoft 365 ランチャーでアプリケーションを表示する

このオプションが [はい] に設定されている場合、割り当てられたユーザーはマイ アプリ ポータルと Microsoft 365 アプリ起動ツールでアプリケーションを表示します。

このオプションが [なし] に設定されている場合、ユーザーはマイ アプリ ポータルと Microsoft 365 起動ツールでこのアプリケーションを表示しません。

ホーム ページの URL が含まれていることを確認するか、マイ アプリ ポータルからアプリケーションを起動できないことを確認します。

割り当てが必要かどうかに関係なく、割り当てられたユーザーのみがマイ アプリ ポータルでこのアプリケーションを表示できます。 特定のユーザーがマイ アプリ ポータルでアプリケーションを表示し、すべてのユーザーがアプリケーションにアクセスできるようにする場合は、[**ユーザーとグループ]** タブでユーザーを割り当て、割り当てを [**なし]**に設定します。

### メモ

このフィールドを使用して、アプリケーションの管理に関連する情報を追加できます。 このフィールドは、最大サイズが 1,024 文字のフリー テキスト フィールドです。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-sign-in-other-problem-access-panel"} -->
## マイ アプリ ポータルからのアプリケーションへのサインインに関する問題のトラブルシューティング - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-other-problem-access-panel
- Service: entra-id / enterprise-apps
- Article date: 2023-09-05
- Summary: Microsoft Entra マイ アプリ からのアプリケーションへのサインインに関する問題のトラブルシューティング

Web ベースのポータルであるマイ アプリを使用すると、Microsoft Entra ID の職場または学校アカウントを持つユーザーは、Microsoft Entra 管理者によってアクセスを許可されたクラウドベースのアプリケーションを表示および起動することができます。 マイ アプリは、https://myapps.microsoft.com で Web ブラウザーの使用によりアクセスされます。

アプリの ID プロバイダーとして Microsoft Entra ID を使用する方法の詳細については、「 [Microsoft Entra ID でのアプリケーション管理とは」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-application-management)参照してください。 迅速に作業を開始するには、 [アプリケーション管理に関するクイック スタート シリーズをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/view-applications-portal)。

これらのアプリケーションは、Microsoft Entra 管理センターでユーザーに代わって構成されます。 マイ アプリでアプリケーションを表示するには、アプリケーションが正しく構成され、ユーザーまたはユーザーがメンバーであるグループに割り当てられている必要があります。

ユーザーに表示されるアプリの種類は、次のカテゴリに分類されます。

- Microsoft 365 アプリケーション
- フェデレーション ベースの SSO で構成されたマイクロソフトとサード パーティのアプリケーション
- パスワードベースの SSO アプリケーション
- 既存の SSO ソリューションのアプリケーション

ここでは、アプリが表示されるかどうかを確認する方法について説明します。

- アプリが Microsoft Entra ID に追加されていることを確認し、ユーザーが割り当てられていることを確認します。 詳細については、 [アプリケーション管理に関するクイック スタート シリーズを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)参照してください。
- アプリが最近追加された場合は、ユーザーにサインアウトしてから再度サインインしてもらいます。
- Office のように、アプリにライセンスが必要な場合は、ユーザーに適切なライセンスが割り当てられていることを確認します。
- ライセンスの変更にかかる時間は、グループの大きさと複雑さによって異なります。

### 最初にチェックすべき一般的な問題

- Web ブラウザーが要件を満たしていることを確認します。 [「マイ アプリでサポートされているブラウザー」](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)を参照してください。
- ユーザーのブラウザーで、アプリケーションの URL が **信頼済みサイト**に追加されていることを確認します。
- アプリケーションが正しく **構成** されていることを確認してください。
- ユーザーのアカウントでサインインが **有効** になっていることを確認します。
- ユーザーのアカウントが**ロックアウトされていないこと**を確認します。
- ユーザーの**パスワードの有効期限が切れていないか、忘れていない**ことを確認します。
- **Multi-Factor Authentication** がユーザー アクセスをブロックしていないことを確認します。
- **条件付きアクセス ポリシー**または**レガシ Identity Protection** ポリシーがユーザー アクセスをブロックしていないことを確認します。
- Multi-Factor Authentication ポリシーまたは条件付きアクセス ポリシーを適用できるように、ユーザーの認証 **連絡先情報** が最新であることを確認します。
- ブラウザーの Cookie を削除してから、再度サインインを試行して、サインインできることを確認します。

### ユーザーのアカウントに関する問題

ユーザーのアカウントに問題があるために、マイ アプリへのアクセスがブロックされる可能性があります。 ユーザーとそのアカウントの設定に関する問題をトラブルシューティングして解決するための方法を次に示します。

- Microsoft Entra ID にユーザー アカウントが存在するかどうかを確認する
- ユーザーのアカウントの状態を確認する
- ユーザーのパスワードをリセットする
- セルフサービス パスワード リセットを有効にする
- ユーザーの多要素認証の状態を確認する
- ユーザーの認証連絡先情報を確認する
- ユーザーのグループ メンバーシップを確認する
- ユーザーが 999 を超えるアプリ ロールの割り当てを持っているかどうかを確認する
- ユーザーの割り当てられたライセンスを確認する
- ユーザーにライセンスを割り当てる

#### Microsoft Entra ID にユーザー アカウントが存在するかどうかを確認する

ユーザーのアカウントが存在するかどうかを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを検索し、行を選んでユーザーの詳細を表示します。
4. ユーザー オブジェクトのプロパティを調べて、予想した通りに表示されていることと、紛失したデータがないことを確認します。

#### ユーザーのアカウントの状態を確認する

ユーザーのアカウントの状態を確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを検索し、そのユーザーの詳細を含む行を選択します。
4. **[プロファイル] を選択します**。
5. [ **設定]** で、[ **サインインのブロック** ] が **[いいえ**] に設定されていることを確認します。

#### ユーザーのパスワードをリセットする

ユーザーのパスワードをリセットするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを検索し、そのユーザーの詳細を含む行を選択します。
4. ユーザー ウィンドウの上部にある [ **パスワードのリセット** ] ボタンを選択します。
5. 表示される [ **パスワードのリセット** ] ウィンドウの [ **パスワードのリセット** ] ボタンを選択します。
6. **一時パスワード**をコピーするか**、ユーザーの新しいパスワードを入力**します。
7. この新しいパスワードをユーザーに伝えます。 ユーザーは、次に Microsoft Entra ID にサインインするときに、このパスワードを変更することが必要な場合があります。

#### セルフサービス パスワード リセットを有効にする

セルフサービス パスワード リセットを有効にするには、次のデプロイ手順に従います。

- [ユーザーが Microsoft Entra パスワードをリセットできるようにする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)
- [ユーザーが Active Directory のオンプレミス パスワードをリセットまたは変更できるようにする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)

#### ユーザーの多要素認証の状態を確認する

ユーザーの多要素認証の状態を確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. ウィンドウの上部にある **[ユーザーごとの MFA** ] ボタンを選択します。
4. **Multi-Factor Authentication** 管理ポータルが読み込まれたら、[**ユーザー**] タブが表示されていることを確認します。
5. ユーザーの一覧で、検索、フィルター処理、または並べ替えによってユーザーを見つけます。
6. ユーザーの一覧からユーザーを選択し、必要に応じて多要素認証を **有効**、 **無効**、または **適用** します。
    注

    ユーザーが **強制** 状態の場合は、一時的に **[無効]** に設定してアカウントに戻すようにすることができます。 ユーザーが再びサインインしたら、その状態をもう一度 **[有効]** に変更して、次回のサインイン時に連絡先情報の再登録を要求できます。 または、「 ユーザーの認証連絡先情報を確認 する」の手順に従って、ユーザーのこのデータを確認または設定することもできます。

#### ユーザーの認証の連絡先情報をチェックする

多要素認証、条件付きアクセス、およびパスワード リセットで使用されるユーザーの認証用連絡先情報をチェックするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを検索し、そのユーザーの詳細を含む行を選択します。
4. [管理] で [ **認証方法** ] を選択 **します**。
5. ユーザーに登録されているデータを**確認**し、必要に応じて更新します。

#### ユーザーのグループ メンバーシップを確認する

ユーザーのグループ メンバーシップを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを検索し、そのユーザーの詳細を含む行を選択します。
4. [ **グループ]** を選択して、ユーザーがメンバーになっているグループを確認します。

#### ユーザーに 999 個を超えるアプリ ロールの割り当てがあるかどうかを確認する

ユーザーに 999 個を超えるアプリ ロールの割り当てがある場合、それらのアプリの一部がマイ アプリに表示されないことがあります。

これは、ユーザーが割り当てられているアプリを特定するために、マイ アプリでは現在、最大 999 個のアプリ ロールの割り当てが読み取られるためです。 ユーザーが 999 個を超えるアプリに割り当てられている場合、マイ アプリ ポータルに表示されるアプリを制御することはできません。

ユーザーに 999 個を超えるアプリ ロールの割り当てがあるかどうかを確認するには、以下の手順に従います。

1. [**Microsoft.Graph**](https://github.com/microsoftgraph/msgraph-sdk-powershell) PowerShell モジュールをインストールします。
2. `Connect-MgGraph -Scopes "User.ReadBasic.All Application.Read.All"`実行し、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
3. `(Get-MgUserAppRoleAssignment -UserId "<user-id>" -PageSize 999).Count` を実行して、ユーザーが現在付与されているアプリ ロールの割り当て数を確認します。
4. 結果が 999 の場合、そのユーザーには 999 個を超えるアプリ ロールの割り当てがある可能性があります。

#### ユーザーに割り当てられているライセンスを確認する

ユーザーに割り当てられているライセンスを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを検索し、そのユーザーの詳細を含む行を選択します。
4. [ **ライセンス]** を選択して、ユーザーが現在割り当てられているライセンスを確認します。

#### ユーザーにライセンスを割り当てる

ユーザーにライセンスを割り当てるには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを検索し、そのユーザーの詳細を含む行を選択します。
4. [ **ライセンス]** を選択して、ユーザーが現在割り当てられているライセンスを確認します。
5. **割り当て**ボタンを選択します。
6. 使用できる製品一覧から 1 つ以上のライセンスを選びます。
7. オプション: 製品を細かく割り当てるには **、[ライセンスの確認] オプション** を選択します。
8. **[保存] を選択します**。

### ディープ リンクのトラブルシューティング

ディープ リンクまたはユーザー アクセス URL は、ユーザーがブラウザーの URL バーから直接自分のパスワード SSO アプリケーションにアクセスするために使用する可能性のあるリンクです。 このリンクに移動すると、ユーザーは最初にマイ アプリに移動しなくても、アプリケーションに自動的にサインインされます。 このリンクは、ユーザーが Microsoft 365 アプリケーション起動プログラムからこれらのアプリケーションにアクセスするために使用するものと同じです。

#### ディープ リンクの確認

正しいディープ リンクが存在するかどうかを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**を参照します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. **ユーザー アクセス URL** というラベルを見つけます。 ディープ リンクはこの URL に一致している必要があります。

### サポートにお問い合せください

次の情報 (取得可能な場合) を含むサポート チケットを開きます。

- 関連エラー ID
- UPN (ユーザーの電子メール アドレス)
- TenantId
- ブラウザーの種類
- タイム ゾーンとエラーが発生した時刻/タイムフレーム
- Fiddler のトレース
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-sign-in-problem-application-error"} -->
## アプリ ページに、ユーザーがサインインした後にエラー メッセージが表示される - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-problem-application-error
- Service: entra-id / enterprise-apps
- Article date: 2025-04-29
- Summary: アプリがエラー メッセージを返したときに Microsoft Entra サインインに関する問題を解決する方法。

このシナリオでは、Microsoft Entra ID によってユーザーがサインインします。 ただし、アプリケーションではエラー メッセージが表示され、ユーザーはサインイン フローを完了できません。 問題は、アプリが Microsoft Entra ID によって発行された応答を受け入れられなかったということです。

アプリが Microsoft Entra ID からの応答を受け入れなかった理由はいくつかあります。 エラー メッセージまたはコードが表示されている場合は、次のリソースを使用してエラーを診断します。

- [Microsoft Entra の認証と承認のエラー コード](https://learn.microsoft.com/ja-jp/entra/identity-platform/reference-error-codes)
- [同意プロンプト エラーのトラブルシューティング](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-unexpected-user-consent-error)

エラー メッセージで応答に不足しているものが明確に識別されない場合は、次の手順を試してください。

- アプリが Microsoft Entra ギャラリーにある場合は、「 [Microsoft Entra ID でアプリケーションに SAML ベースのシングル サインオンをデバッグする方法](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/debug-saml-sso-issues)」の手順に従っていることを確認します。
- [Fiddler](https://www.telerik.com/fiddler) などのツールを使用して、SAML 要求、応答、トークンをキャプチャします。
- SAML 応答をアプリ ベンダーに送信し、不足している内容を確認します。

### SAML 応答に属性がありません

Microsoft Entra 応答で送信される属性を Microsoft Entra 構成に追加するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**を参照します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、シングル サインオン用に構成するアプリケーションを選択します。
4. アプリが読み込まれたら、ナビゲーション ウィンドウで [ **シングル サインオン** ] を選択します。
5. [ **ユーザー属性** ] セクションで、[表示] を選択 **し、他のすべてのユーザー属性を編集します**。 ここでは、ユーザーがサインインするときに SAML トークンでアプリに送信する属性を変更できます。

    属性を追加するには:

    1. [ **属性の追加] を選択します**。 **[名前]** を入力し、ドロップダウン リストから **[値**] を選択します。
    2. **[保存] を選択します**。 テーブルに新しい属性が表示されます。
6. 構成を保存します。

    ユーザーが次回アプリにサインインすると、Microsoft Entra ID は SAML 応答で新しい属性を送信します。

### アプリでユーザーを識別できない

SAML 応答にロールなどの属性がないため、アプリへのサインインは失敗します。 または、アプリが **NameID** (ユーザー識別子) 属性の別の形式または値を想定しているために失敗します。

[Microsoft Entra ID 自動ユーザー プロビジョニングを](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/user-provisioning)使用してアプリ内のユーザーを作成、維持、削除する場合は、ユーザーが SaaS アプリにプロビジョニングされていることを確認します。 詳細については、「 [Microsoft Entra Gallery アプリケーションにユーザーがプロビジョニングされていない](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/application-provisioning-config-problem-no-users-provisioned)」を参照してください。

#### Microsoft Entra アプリ構成に属性を追加する

ユーザー識別子の値を変更するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**を参照します。
3. SSO 用に構成するアプリを選択します。
4. アプリが読み込まれたら、ナビゲーション ウィンドウで [ **シングル サインオン** ] を選択します。
5. [ **ユーザー属性**] で、[ユーザー識別子] ドロップダウン リストからユーザーの一意 **の識別子を** 選択します。

#### NameID 形式を変更する

アプリケーションで **NameID** (ユーザー識別子) 属性に別の形式が必要な場合は、「 [nameID の編集」](https://learn.microsoft.com/ja-jp/entra/identity-platform/saml-claims-customization#edit-nameid) セクションを参照して NameID 形式を変更してください。

Microsoft Entra ID は、選択した値または SAML AuthRequest でアプリによって要求された形式に基づいて **、NameID** 属性 (ユーザー識別子) の形式を選択します。 詳細については、 [シングル サインオン SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol#nameidpolicy)の「NameIDPolicy」セクションを参照してください。

### アプリでは、SAML 応答に対して異なる署名方法が必要です

Microsoft Entra ID によってデジタル署名される SAML トークンのどの部分を変更するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**を参照します。
3. シングル サインオン用に構成するアプリケーションを選択します。
4. アプリケーションが読み込まれたら、ナビゲーション ウィンドウで [ **シングル サインオン** ] を選択します。
5. [ **SAML 署名証明書**] で、[ **証明書の署名の詳細設定を表示**する] を選択します。
6. 次の **オプション** の中から、アプリで想定される署名オプションを選択します。

    - **SAML 応答に署名する**
    - **SAML 応答とアサーションに署名します**。
    - **SAML アサーションに署名します**。

    ユーザーが次回アプリにサインインすると、Microsoft Entra ID は選択した SAML 応答の一部に署名します。

### アプリは SHA-1 署名アルゴリズムを想定しています

既定では、Microsoft Entra ID は、最も安全なアルゴリズムを使用して SAML トークンに署名します。 アプリで SHA-1 が必要な場合を除き、署名アルゴリズムを SHA-1  に変更しないことをお勧めします。

署名アルゴリズムを変更するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**を参照します。
3. シングル サインオン用に構成するアプリを選択します。
4. アプリが読み込まれたら、アプリの左側にあるナビゲーション ウィンドウから [ **シングル サインオン** ] を選択します。
5. [ **SAML 署名証明書**] で、[ **証明書の署名の詳細設定を表示**する] を選択します。
6. **署名アルゴリズム**として **SHA-1** を選択します。

    ユーザーが次回アプリにサインインすると、Microsoft Entra ID は SHA-1 アルゴリズムを使用して SAML トークンに署名します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-sign-in-problem-first-party-microsoft"} -->
## Microsoft アプリケーションへのサインインに関する問題 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-problem-first-party-microsoft
- Service: entra-id / enterprise-apps
- Article date: 2023-09-07
- Summary: Microsoft Entra ID を使用したファースト パーティの Microsoft アプリケーション (Microsoft 365 など) にサインインする際に直面する一般的な問題をトラブルシューティングする

Microsoft アプリケーション (Exchange、SharePoint、Yammer など) の割り当てと管理は、サード パーティの SaaS アプリケ―ションや、シングル サインオンで Microsoft Entra ID と統合する他のアプリケーションとは少し異なる方法で行います。

Microsoft が公開したアプリケーションにユーザーがアクセスする方法は、主に 3 つあります。

- Microsoft 365 またはその他の有料スイートのアプリケーションの場合、ユーザーは、ユーザー アカウントに直接 **ライセンスを割り当て** 、またはグループ ベースのライセンス割り当て機能を使用してグループを通じてアクセス権を付与されます。
- Microsoft またはサード パーティが誰でも自由に公開して使用できるアプリケーションの場合、ユーザーは **ユーザーの同意**を得てアクセス権を付与できます。 つまり、ユーザーは自分の Microsoft Entra の職場または学校アカウントでアプリケーションにサインインし、そのアカウントの限定されたデータ セットにアクセスすることが許可されます。
- Microsoft またはサード パーティが誰でも自由に公開して使用できるアプリケーションの場合、ユーザーは **管理者の同意**を得てアクセス権を付与することもできます。 つまり、管理者は組織の全員がそのアプリケーションを使用する可能性があると判断しているため、特権ロール管理者アカウントを使用してアプリケーションにサインインし、組織の全員にアクセス権を付与します。

問題のトラブルシューティングを行うには、 アプリケーション アクセスに関する一般的な問題領域から始めて検討 し、「チュートリアル: Microsoft アプリケーション のアクセスをトラブルシューティングして詳細を確認する手順」を参照してください。

### アプリケーション アクセスに関して考慮すべき一般的問題

一般的な問題領域の一覧を次に示します。どこから始めるべきかわかっている場合は、そこから詳細をご覧ください。ただし、お急ぎの場合はチュートリアル:「Microsoft アプリケーション アクセスをトラブルシューティングする手順」を参照してください。

- ユーザーのアカウントに関する問題
- グループに関する問題
- 条件付きアクセス ポリシーに関する問題
- アプリケーションの同意に関する問題

### Microsoft アプリケーション アクセスをトラブルシューティングする手順

ユーザーが Microsoft アプリケーションにサインインできないときによくある問題を次に示します。

- 最初にチェックすべき一般的な問題

    - ユーザーがローカル アプリケーション **の URL ではなく、正しい URL** にサインインしていることを確認します。
    - ユーザーのアカウントが**ロックアウトされていないこと**を確認します。
    - **ユーザーのアカウントが** Microsoft Entra ID に存在することを確認します。 Microsoft Entra ID にユーザー アカウントが存在するかどうかを確認する
    - ユーザーのアカウントでサインインが **有効** になっていることを確認します。 ユーザーのアカウントの状態を確認する
    - ユーザーの**パスワードの有効期限が切れていないか、忘れていない**ことを確認します。ユーザーのパスワードをリセットするか、[セルフサービス パスワード リセットを有効にする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)
    - **多要素認証**によってユーザー アクセスがブロックされていないことを確認します。 ユーザーの多要素認証の状態を確認 するか 、ユーザーの認証の連絡先情報を確認する
    - **条件付きアクセス ポリシー**または**レガシ Identity Protection** ポリシーがユーザー アクセスをブロックしていないことを確認します。 特定の条件付きアクセス ポリシーを確認 するか、 特定のアプリケーションの条件付きアクセス ポリシーを確認 するか 、特定の条件付きアクセス ポリシーを無効にする
    - 多要素認証または条件付きアクセス ポリシーを適用できるように、ユーザーの認証 **連絡先情報** が最新であることを確認します。 ユーザーの多要素認証の状態を確認 するか 、ユーザーの認証の連絡先情報を確認する
- **ライセンスが必要な** **Microsoft** アプリケーション (Office365 など) の場合、上記の一般的な問題を除外した後に確認する必要がある特定の問題を次に示します。

    - ユーザーまたは**ライセンスが割り当てられている**ことを確認します。ユーザーの割り当て済みライセンスを確認するか、グループの割り当て済みライセンスを確認する
    - ライセンスが**静的グループ** **に割り当てられている**場合は、ユーザーがそのグループの**メンバー**であることを確認します。 ユーザーのグループ メンバーシップを確認する
    - ライセンスが**動的グループ** **に割り当てられている**場合は、**動的グループ規則が正しく設定**されていることを確認します。 動的グループのメンバーシップ条件を確認する
    - ライセンスが**動的グループ** **に割り当てられている**場合は、動的グループがそのメンバーシップの**処理を完了**していること、および**ユーザーがメンバー**であることを確認します (これには時間がかかる場合があります)。 ユーザーのグループ メンバーシップを確認する
    - ライセンスが割り当てられていることを確認したら、ライセンスの **有効期限が切れていない**ことを確認します。
    - ライセンスがアクセス **しているアプリケーション用** であることを確認します。
- **ライセンスを必要としない** **Microsoft** アプリケーションの場合、確認すべきその他の点を次に示します。

    - アプリケーションが **ユーザー レベルのアクセス許可** ("このユーザーのメールボックスにアクセスする" など) を要求している場合は、ユーザーがアプリケーションにサインインし、ユーザー **レベルの同意操作** を実行してアプリケーションがデータにアクセスできることを確認します。
    - アプリケーションが **管理者レベルのアクセス許可** ("すべてのユーザーのメールボックスへのアクセス" など) を要求している場合は、特権ロール管理者が組織内 **のすべてのユーザーに代わって管理者レベルの同意操作** を実行していることを確認します。

### ユーザーのアカウントに関する問題

アプリケーションへのアクセスが、そのアプリケーションに割り当てられているユーザーの問題によりブロックされる場合があります。 ユーザーとそのアカウントの設定に関する問題をトラブルシューティングして解決するための方法を次に示します。

- Microsoft Entra ID にユーザー アカウントが存在するかどうかを確認する
- ユーザーのアカウントの状態を確認する
- ユーザーのパスワードをリセットする
- セルフサービス パスワード リセットを有効にする
- ユーザーの多要素認証の状態を確認する
- ユーザーの認証連絡先情報を確認する
- ユーザーのグループ メンバーシップを確認する
- ユーザーの割り当てられたライセンスを確認する
- ユーザーにライセンスを割り当てる

#### ユーザー アカウントが Microsoft Entra ID に存在するかどうかを調べる

ユーザーのアカウントが存在するかどうかを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを**検索**し、ユーザーの詳細を含む行を選択します。
4. ユーザー オブジェクトのプロパティを調べて、予想した通りに表示されていることと、紛失したデータがないことを確認します。

#### ユーザーのアカウントの状態を確認する

ユーザーのアカウントの状態を確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを**検索**し、ユーザーの詳細を含む行を選択します。
4. **[プロファイル] を選択します**。
5. [ **設定]** で、[ **サインインのブロック** ] が **[いいえ**] に設定されていることを確認します。

#### ユーザーのパスワードをリセットする

ユーザーのパスワードをリセットするには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを**検索**し、ユーザーの詳細を含む行を選択します。
4. ユーザー ウィンドウの上部にある [ **パスワードのリセット** ] ボタンを選択します。
5. 表示される [ **パスワードのリセット** ] ウィンドウの [ **パスワードのリセット** ] ボタンを選択します。
6. **一時パスワード**をコピーするか**、ユーザーの新しいパスワードを入力**します。
7. この新しいパスワードをユーザーに伝えます。 ユーザーは、次回 Microsoft Entra ID にサインインするときに、このパスワードの変更を求められることがあります。

#### セルフサービス パスワード リセットを有効にする

セルフサービス パスワード リセットを有効にするには、次のセクションのデプロイ手順を実行します。

- [ユーザーが Microsoft Entra パスワードをリセットできるようにする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)
- [ユーザーが Active Directory のオンプレミス パスワードをリセットまたは変更できるようにする](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-sspr)

#### ユーザーの多要素認証の状態を確認する

ユーザーの多要素認証の状態を確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. ウィンドウの上部にある **多要素認証** ボタンを選択します。
4. **多要素認証管理ポータル**が読み込まれたら、[**ユーザー**] タブが表示されていることを確認します。
5. ユーザーの一覧で、検索、フィルター処理、または並べ替えによってユーザーを見つけます。
6. ユーザーの一覧からユーザーを選択し、必要に応じて多要素認証を **有効**、 **無効**、または **適用** します。

    - **注**: ユーザーが **強制** 状態の場合は、一時的に **[無効]** に設定してアカウントに戻すようにすることができます。 ユーザーが再びサインインしたら、その状態をもう一度 **有効** に変更して、次回のサインイン時に連絡先情報の再登録を要求できます。 または、「 ユーザーの認証連絡先情報を確認 する」の手順に従って、ユーザーのこのデータを確認または設定することもできます。

#### ユーザーの認証の連絡先情報を確認する

多要素認証、条件付きアクセス、およびパスワード リセットで使用されるユーザーの認証の連絡先情報を確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを**検索**し、ユーザーの詳細を含む行を選択します。
4. **[プロファイル] を選択します**。
5. [ **認証の連絡先情報**] まで下にスクロールします。
6. ユーザーに登録されているデータを**確認**し、必要に応じて更新します。

#### ユーザーのグループ メンバーシップを確認する

ユーザーのグループ メンバーシップを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを**検索**し、ユーザーの詳細を含む行を選択します。
4. [ **グループ]** を選択して、ユーザーがメンバーになっているグループを確認します。

#### ユーザーに割り当てられているライセンスを確認する

ユーザーに割り当てられているライセンスを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを**検索**し、ユーザーの詳細を含む行を選択します。
4. [ **ライセンス]** を選択して、ユーザーが現在割り当てられているライセンスを確認します。

#### ユーザーにライセンスを割り当てる

ユーザーにライセンスを割り当てるには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)としてサインインします。
2. **Entra ID**&gt;**Users** に移動します。
3. 関心のあるユーザーを**検索**し、ユーザーの詳細を含む行を選択します。
4. [ **ライセンス]** を選択して、ユーザーが現在割り当てられているライセンスを確認します。
5. [ **割り当て** ] ボタンを選択します。
6. 利用可能な製品の一覧から **1 つ以上** の製品を選択します。
7. **オプション** で、製品を細かく **割り当てる割り当てオプション** 項目を選択します。 完了したら **、[OK] を選択します** 。
8. [ **割り当て** ] ボタンを選択して、これらのライセンスをこのユーザーに割り当てます。

### グループに関する問題

アプリケーションへのアクセスが、そのアプリケーションに割り当てられているグループの問題によりブロックされる場合があります。 グループおよびグループ メンバーシップに関する問題をトラブルシューティングして解決するための方法を次に示します。

- グループのメンバーシップを確認する
- 動的グループのメンバーシップ条件を確認する
- グループの割り当てられたライセンスを確認する
- グループのライセンスを再処理する
- グループにライセンスを割り当てる

#### グループのメンバーシップを確認する

グループのメンバーシップを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)または[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All Groups** に移動します。
3. 対象のグループを検索し、そのグループの詳細が記載されている行を選択します。
4. [ **メンバー]** を選択して、このグループに割り当てられているユーザーの一覧を確認します。

#### 動的グループのメンバーシップ条件を確認する

動的グループのメンバーシップ条件を確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)または[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All Groups** に移動します。
3. 対象のグループを検索し、そのグループの詳細が記載されている行を選択します。
4. **[動的メンバーシップ ルール**] を選択します。
5. このグループに対して定義されている **単純** または **高度な** ルールを確認し、このグループのメンバーにするユーザーがこれらの条件を満たしていることを確認します。

#### グループに割り当てられているライセンスを確認する

グループに割り当てられているライセンスを確認するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)または[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All Groups** に移動します。
3. 対象のグループを検索し、そのグループの詳細が記載されている行を選択します。
4. [ **ライセンス]** を選択して、グループに現在割り当てられているライセンスを確認します。

#### グループのライセンスを再処理する

グループに割り当てられているライセンスを再処理するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)または[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All Groups** に移動します。
3. 対象のグループを検索し、そのグループの詳細が記載されている行を選択します。
4. [ **ライセンス]** を選択して、グループに現在割り当てられているライセンスを確認します。
5. [ **再処理** ] ボタンを選択して、このグループのメンバーに割り当てられているライセンスが -date up-toされていることを確認します。 このとき、グループの大きさと複雑さによっては、時間がかかる場合があります。

    Note

    もっと速くするには、一時的に直接ユーザーにライセンスを割り当てることを検討してください。 ユーザーにライセンスを割り当てます。

#### グループにライセンスを割り当てる

グループにライセンスを割り当てるには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[ユーザー管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#user-administrator)または[グループ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#groups-administrator)としてサインインします。
2. **Entra ID**&gt;**Groups**&gt;**All Groups** に移動します。
3. 対象のグループを検索し、そのグループの詳細が記載されている行を選択します。
4. [ **ライセンス]** を選択して、グループに現在割り当てられているライセンスを確認します。
5. [ **割り当て** ] ボタンを選択します。
6. 利用可能な製品の一覧から **1 つ以上** の製品を選択します。
7. **オプション** で、製品を細かく **割り当てる割り当てオプション** 項目を選択します。 完了したら **、[OK] を選択します** 。
8. [ **割り当て** ] ボタンを選択して、これらのライセンスをこのグループに割り当てます。 このとき、グループの大きさと複雑さによっては、時間がかかる場合があります。

    Note

    もっと速くするには、一時的に直接ユーザーにライセンスを割り当てることを検討してください。 ユーザーにライセンスを割り当てます。

### 条件付きアクセス ポリシーに関する問題

#### 特定の条件付きアクセス ポリシーを確認する

1 つの条件付きアクセス ポリシーを確認または検証する方法

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. **[条件付きアクセス**] ナビゲーション項目を選択します。
4. 検査の対象とするポリシーを選択します。
5. 特定の条件、割り当て、またはユーザーのアクセスをブロックしてしまう他の設定がないことを確認します。

    Note

    このポリシーがサインインに影響しないように、このポリシーを一時的に無効にすることもできます。これを行うには、[ポリシーの **有効化** ] トグルを **[いいえ** ] に設定し、[ **保存** ] ボタンをクリックします。

#### 特定のアプリケーションの条件付きアクセス ポリシーを確認する

1 つのアプリケーションで現在構成されている条件付きアクセス ポリシーを確認または検証するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**にアクセスします。
3. 対象のアプリケーション、またはユーザーがサインインしようとしているアプリケーションを、アプリケーション表示名またはアプリケーション ID で検索します。
4. **[条件付きアクセス**] ナビゲーション項目を選択します。
5. 検査の対象とするポリシーを選択します。
6. 特定の条件、割り当て、またはユーザーのアクセスをブロックしてしまう他の設定がないことを確認します。

    Note

    このポリシーがサインインに影響しないように、このポリシーを一時的に無効にすることもできます。これを行うには、[ポリシーの **有効化** ] トグルを **[いいえ** ] に設定し、[ **保存** ] ボタンをクリックします。

#### 特定の条件付きアクセス ポリシーを無効にする

1 つの条件付きアクセス ポリシーを確認または検証する方法

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[条件付きアクセス管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#conditional-access-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]** に移動します。
3. **[条件付きアクセス**] ナビゲーション項目を選択します。
4. 検査の対象とするポリシーを選択します。
5. [ポリシーを **有効にする** ] トグルを **[いいえ** ] に設定し、[ **保存** ] ボタンを選択して、ポリシーを無効にします。

### アプリケーションの承認に関する問題

適切なアクセス許可の承認操作が行われていないために、アプリケーションへのアクセスがブロックされる場合があります。 アプリケーションの承認の問題をトラブルシューティングして解決するための方法を次に示します。

- ユーザー レベルの同意操作を実行する
- 任意のアプリケーションに対して管理者レベルの同意操作を実行する
- シングルテナント アプリケーションに対して管理者レベルの同意を実行する
- マルチテナント アプリケーションに対して管理者レベルの同意を実行する

#### ユーザー レベルの同意操作を実行する

- アクセス許可を要求する任意の OpenID 接続対応アプリケーションで、アプリケーションのサインイン画面に移動して、サインインしているユーザーのアプリケーションに対するユーザー レベルの同意を実行します。
- これをプログラムで行う場合は、「個々の [ユーザーの同意を要求する」を](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#user-consent)参照してください。

#### 任意のアプリケーションで管理者レベルの同意操作を実行する

- **V1 アプリケーション モデルを使用して開発されたアプリケーションの場合のみ**、アプリケーションのサインイン URL の末尾に "**?prompt=admin\_consent**" を追加することで、この管理者レベルの同意を強制的に行うことができます。
- **V2 アプリケーション モデルを使用して開発されたアプリケーション**の場合は、「管理者の同意エンドポイントを使用する」の**「ディレクトリ管理者からのアクセス許可の要求**」セクションの手順に従って、この管理者レベルの[同意を](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#administrator-consent)適用できます。

#### シングルテナント アプリケーションで管理者レベルの同意を実行する

- アクセス許可を要求する**シングルテナント アプリケーション** (組織内で開発しているアプリケーションや所有しているアプリケーションなど) の場合は、特権ロール管理者としてサインインし、アプリケーション レジストリの上部にある [**アクセス許可の付与**] ボタンをクリックして、すべてのユーザーに代わって**管理レベルの同意**操作を実行できます**。&gt;すべてのアプリケーション -&gt; アプリの選択 -&gt;必要なアクセス許可**ウィンドウ。
- **V1 または V2 アプリケーション モデルを使用して開発されたアプリケーション**の場合は、「管理者の同意エンドポイントを使用する」の**「ディレクトリ管理者からのアクセス許可の要求**」セクションの手順に従って、この管理者レベルの[同意を](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#administrator-consent)適用できます。

#### マルチテナント アプリケーションで管理者レベルの同意を実行する

- アクセス許可を要求する **マルチテナント アプリケーション** (サード パーティや Microsoft が開発するアプリケーションなど) の場合は、 **管理レベルの同意** 操作を実行できます。 特権ロール管理者としてサインインし、[] ウィンドウの下にある [アクセス許可の&gt;] ボタンを選択します (間もなく利用可能)。
- また、「管理者の同意エンドポイントを使用する」の「 **ディレクトリ管理者からのアクセス許可の要求** 」セクションの手順に従って、この管理者レベルの [同意を](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#administrator-consent)適用することもできます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-sign-in-unexpected-user-consent-error"} -->
## アプリケーションへの同意を実行するときに予期しないエラーが発生する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-unexpected-user-consent-error
- Service: entra-id / enterprise-apps
- Article date: 2025-02-27
- Summary: この記事では、アプリケーションへの同意プロセス中に発生する可能性があるエラーと、そのエラーに対して実行できる操作について説明します。

この記事では、アプリケーションに同意する処理の最中に発生する可能性があるエラーについて説明します。 エラー メッセージが含まれていない予期しない同意プロンプトのトラブルシューティングを行う場合は、「Microsoft Entra ID の認証シナリオ」を参照してください。

Microsoft Entra IDと統合される多くのアプリケーションでは、機能するために他のリソースにアクセスするためのアクセス許可が必要です。 これらのリソースもMicrosoft Entra IDと統合されている場合、それらにアクセスするためのアクセス許可は、多くの場合、共通の同意フレームワークを通じて要求されます。 同意プロンプトは、通常、アプリケーションが初めて使用されるときに表示されます。 また、アプリケーションの後続の使用時にも発生する可能性があります。

ユーザーがアプリケーションに必要なアクセス許可に同意するには、特定の条件が満たされている必要があります。 これらの条件が満たされていない場合は、以下のエラーが発生する可能性があります。

### 承認されていないアクセス許可の要求

- **AADSTS90093**: `clientAppDisplayName` は、付与する権限がない 1 つ以上のアクセス許可を要求しています。 あなたの代わりにこのアプリケーションに同意できる管理者に問い合わせてください。
- **AADSTS90094**: `clientAppDisplayName` には、管理者のみが付与できる組織内のリソースにアクセスするためのアクセス許可が必要です。 アプリケーションを使用するには、まず管理者に依頼してこのアプリにアクセス許可を付与してください。

このエラーは、管理者ではないユーザーが、管理者のみが付与できるアクセス許可を要求しているアプリケーションを使用しようとした場合に発生します。 このエラーを解決するには、管理者が組織に代わってアプリケーションへのアクセス権を付与する必要があります。

このエラーは、アクセス許可要求が危険であると検出Microsoftが原因で、ユーザーがアプリケーションに同意できない場合にも発生する可能性があります。 この場合、監査イベントは、 **ApplicationManagement** のカテゴリ、アプリケーション **に対する同意**のアクティビティの種類、および **危険なアプリケーションが検出された**状態の理由でログに記録されます。

このエラーが発生する可能性があるもう 1 つのシナリオは、アプリケーションでユーザー割り当てが必要だが、管理者の同意が提供されていない場合です。 この場合、管理者はまず、アプリケーションに対してテナント全体の管理者の同意を提供する必要があります。

### ポリシーによってアクセス許可が付与されないようにする

- **AADSTS90093**: `tenantDisplayName` の管理者は、要求しているアクセス許可 `name of app` 付与できないようにするポリシーを設定します。 ユーザーに代わってこのアプリにアクセス許可を付与できる `tenantDisplayName`の管理者に問い合わせてください。

このエラーは、管理者がユーザーがアプリケーションに同意する機能をオフにした場合に発生します。 その後、管理者以外のユーザーが、同意を必要とするアプリケーションを使用しようとします。 このエラーを解決するには、管理者が組織に代わってアプリケーションへのアクセス権を付与する必要があります。

### 断続的な問題

- **AADSTS90090**: サインイン プロセスで、 `clientAppDisplayName`に付与しようとしたアクセス許可を記録する断続的な問題が発生しました。 後でもう一度試してみてください。

このエラーは、断続的なサービス側の問題が発生したことを示します。 解決策は、アプリケーションにもう一度同意を試みすることです。

### テナントでリソースを使用できない

- **AADSTS65005**: `clientAppDisplayName`は、組織の`resourceAppDisplayName`で使用できないリソース `tenantDisplayName`へのアクセスを要求しています。

要求されたアクセス許可を提供するリソースがテナントで使用可能であることを確認するか、 `tenantDisplayName`の管理者に問い合わせてください。 それ以外の場合は、アプリケーションがリソースを要求する方法に誤った構成があり、アプリケーション開発者に問い合わせる必要があります。

### アクセス許可の不一致

- **AADSTS65005**: アプリがリソース `resourceAppDisplayName`にアクセスするための同意を要求しました。 この要求は、アプリの登録時のアプリの事前構成方法と一致しないため、失敗しました。 アプリ ベンダーに問い合わせてください。

このエラーは、ユーザーが同意しようとしているアプリが、組織のディレクトリ (テナント) に見つからないリソース アプリケーションにアクセスするためのアクセス許可を要求した場合に発生します。 この状況は、次の理由で発生する可能性があります。

- クライアント アプリケーション開発者がアプリケーションを誤って構成し、無効なリソースへのアクセスを要求しました。 この場合、アプリケーション開発者は、この問題を解決するためにクライアント アプリケーションの構成を更新する必要があります。
- ターゲット リソース アプリケーションを表すサービス プリンシパルが組織に存在しないか、過去に存在していたが削除されます。 この問題を解決するには、クライアント アプリケーションがアクセス許可を要求できるように、組織内のリソース アプリケーションのサービス プリンシパルをプロビジョニングする必要があります。 サービス プリンシパルのプロビジョニングに使用する方法は、アプリケーションの種類によって異なります。

    - リソース アプリケーション (Microsoft発行済みアプリケーション) のサブスクリプションの取得
    - リソース アプリケーションへの同意
    - Microsoft Entra 管理センターを使用してアプリケーションのアクセス許可を付与する
    - Microsoft Entra アプリケーション ギャラリーからのアプリケーションの追加

### 危険なアプリ

- **AADSTS900941**: 管理者の同意が必要です。 アプリは危険であると考えられます。 (リスキーなアプリのため管理者の同意が必要)

    このアプリは危険な場合があります。 このアプリを信頼する場合は、アクセス権を付与するように管理者に依頼してください。
- **AADSTS900981**: 危険なアプリに対する管理者の同意要求が受信されました。 (AdminConsentRequestRiskyAppWarning)

    このアプリは危険な場合があります。 このアプリを信頼している場合のみ続行してください。

Microsoftが、同意要求にリスクがあると判断する場合にこれらのメッセージが表示されます。 他の多くの要因の中で、 [検証済みの発行元が](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview) アプリの登録に追加されていない場合、これらのエラーが発生する可能性があります。 [管理者の同意ワークフロー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)がオフの場合、最初のエラー コードとメッセージがユーザーに表示されます。 2 番目のコードとメッセージは、管理者の同意ワークフローが有効になっているときに、ユーザーと管理者に表示されます。

ユーザーは、危険であると検出Microsoftアプリに同意を与えることはできません。 管理者は同意を付与できますが、アプリを慎重に評価し、慎重に進める必要があります。 アプリが詳細なレビューで疑わしいと思われる場合は、管理者は同意ダイアログからMicrosoftに報告できます。

### ユーザーがアプリへのアクセスに同意しなかった

- **AADSTS65004**: ユーザーがアプリへのアクセスに同意することを拒否しました。

このエラーは、管理者の同意ワークフロー中に発生します。 ユーザーがアプリケーションの管理者の同意の承認を送信すると、[ **アプリに戻る** ] ボタンが表示された別のダイアログが表示されます。 ユーザーがボタンを選択すると、Microsoft Entra ID元の認証要求で指定されたリダイレクト URI にAADSTS65004 エラーが送信されます。 このエラーは、管理者の同意ワークフローで想定される部分です。

管理者が確認し、送信された管理者の同意をユーザーが承認した後、ユーザーはアプリケーションで新しい認証プロセスを開始する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/application-sign-in-unexpected-user-consent-prompt"} -->
## アプリケーションにサインインするときに予期しない同意プロンプトが表示される - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-unexpected-user-consent-prompt
- Service: entra-id / enterprise-apps
- Article date: 2022-09-07
- Summary: 予期していなかった Microsoft Entra ID と統合したアプリケーションの同意プロンプトがユーザーに表示されたときにトラブルシューティングを行う方法

Microsoft Entra ID と統合される多くのアプリケーションでは、実行するためにさまざまなリソースへのアクセス許可が必要です。 これらのリソースが Microsoft Entra ID とも統合されている場合、それらにアクセスするためのアクセス許可は、Microsoft Entra 同意フレームワークを使用して要求されます。 これらの要求により、アプリケーションが初めて使用されるときに同意プロンプトが表示されます。これは多くの場合、1 回限りの操作です。

特定のシナリオでは、ユーザーがサインインしようとしたときに追加の同意プロンプトが表示されることがあります。 この記事では、予期しない同意プロンプトが表示される理由とトラブルシューティング方法を診断します。

### ユーザーに同意プロンプトが表示されるシナリオ

さまざまなシナリオで、さらにプロンプトが表示される場合があります。

- 割り当てが必要なアプリケーションが構成されています。 個々のユーザーの同意は、現在、割り当てを必要とするアプリではサポートされていません。そのため、ディレクトリ全体に対して管理者がアクセス許可を付与する必要があります。 割り当てが必要になるようにアプリケーションを構成する場合は、割り当てられたユーザーがサインインできるように、テナント全体の管理者の同意も必ず付与してください。
- アプリケーションに必要なアクセス許可のセットが開発者によって変更されたため、もう一度付与する必要があります。
- アプリケーションに最初に同意したユーザーは管理者ではなく、別の (管理者以外の) ユーザーが初めてアプリケーションを使用するようになりました。
- 最初にアプリケーションに同意したユーザーは管理者でしたが、組織全体に代わって同意しませんでした。
- アプリケーションでは、同意が最初に付与された後、 [増分および動的同意](https://learn.microsoft.com/ja-jp/entra/identity-platform/permissions-consent-overview#consent) を使用して、さらにアクセス許可を要求しています。 増分同意と動的同意は、多くの場合、アプリケーションのオプション機能がベースライン機能に必要なアクセス許可を超えるアクセス許可を必要とする場合に使用されます。
- 最初に許可された後、同意は取り消されました。
- 開発者は、アプリケーションが使用されるたびに同意プロンプトを要求するようにアプリケーションを構成しました (注: この動作はベスト プラクティスではありません)。

    注

    Microsoft の推奨事項とベスト プラクティスに従って、多くの組織では、アプリに同意を付与するためのユーザーのアクセス許可を無効または制限しています。 アプリケーションがサインインするたびにユーザーに同意を強制する場合、管理者がテナント全体の管理者の同意を付与した場合でも、ほとんどのユーザーはこれらのアプリケーションの使用がブロックされます。 管理者の同意が付与された後でもユーザーの同意が必要なアプリケーションが発生した場合は、アプリの発行元に問い合わせて、すべてのサインインでユーザーの同意を強制することを停止する設定またはオプションがあるかどうかを確認してください。

### トラブルシューティングの手順

#### アプリケーションに対して要求および付与されたアクセス許可を比較する

アプリケーションに付与されたアクセス許可が -date up-toであることを確認するには、アプリケーションによって要求されているアクセス許可と、テナントで既に付与されているアクセス許可を比較できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. 左側のナビゲーションの [セキュリティ] で、[**アクセス許可**] を選択します。
5. [アクセス許可] ページの表から、既に付与されているアクセス許可の一覧を表示する
6. 要求されたアクセス許可を表示するには、[ **管理者の同意の付与** ] ボタンを選択します。 これにより、要求されたすべてのアクセス許可を一覧表示する同意プロンプトが開きます。 テナント全体の管理者の同意を許可する必要がある場合を除き、同意プロンプトで **[同意** する] を選択しないでください。
7. 同意プロンプト内で、一覧表示されているアクセス許可を展開し、アクセス許可ページのテーブルと比較します。 同意プロンプトに存在するが、アクセス許可ページに存在しない場合、そのアクセス許可はまだ同意されていません。 アプリケーションに対して予期しない同意プロンプトが表示される原因として、未同意のアクセス許可が考えられます。

#### ユーザー割り当ての設定を表示する

アプリケーションで割り当てが必要な場合、個々のユーザーは自分で同意できません。 アプリケーションに割り当てが必要かどうかを確認するには、次の操作を行います。

1. アプリケーションのページで、[管理] の [ **プロパティ** ] を選択 **します**。
2. **割り当てが必要**かどうかを確認するには、[**はい**] に設定します。
3. [はい] に設定した場合、管理者は組織全体の代わりにアクセス許可に同意する必要があります。

#### テナント全体のユーザー同意設定を確認する

個々のユーザーがアプリケーションに同意できるかどうかを判断することは、すべての組織で構成でき、ディレクトリごとに異なる場合があります。 すべてのアクセス許可が既定で管理者の同意を必要としない場合でも、組織はユーザーの同意を完全に無効にして、個々のユーザーがアプリケーションに対して自分自身の同意を得られない可能性があります。 組織のユーザーの同意設定を表示するには、次の操作を行います。

1. Microsoft Entra 管理センターの **[エンタープライズ アプリケーション** ] ページに移動します。
2. [ **セキュリティ**] で、[ **同意とアクセス許可**] を選択します。
3. ユーザーの同意設定を表示します。 [ユーザーの **同意を許可しない**] に設定されている場合、ユーザーはアプリケーションに対して自分の代わりに同意することはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/assign-agent-identities-to-applications"} -->
## アプリケーションへのエージェント ID の割り当てを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-agent-identities-to-applications
- Service: entra-id / enterprise-apps
- Article date: 2025-11-19
- Summary: Microsoft Entra ID でエンタープライズ アプリケーションのエージェント ID を構成する方法について説明します。 ロールの割り当て、アクセス許可の管理、アプリの操作の効率化。

この記事では、アプリケーションと対話する予定のエージェントに対して、Microsoft Entra ID でエンタープライズ アプリケーションにエージェント ID を割り当てる方法について説明します。 Microsoft Entra の構成手順は、エージェントとエンタープライズ アプリケーションの機能によって異なります。

組織は、Microsoft Entra テナント内のどの従業員およびビジネス ゲスト ユーザーが組織のエンタープライズ アプリケーションと対話できるかを制御できます。各アプリケーションを構成して、シングル サインオンまたはプロビジョニングのために Microsoft Entra に依存するように構成し、それらのユーザーをアプリケーションに割り当てる必要があります。 アプリケーションが複数のアプリ ロールを公開している場合は、各ユーザーに特定のアプリ ロールを割り当てることもできます。 詳細については、「 [アプリケーションへのユーザーとグループの割り当てを管理する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)参照してください。

AI エージェントに、テナント内のエンタープライズ アプリケーションと通信するためのツール (またはそのプラットフォームでスキルまたはコネクタと呼ばれる場合もあります) がある場合は、そのツールの ID をアプリケーションに割り当てることができます。 このツールでは、認証にサービス プリンシパル、エージェント ID、またはエージェント ユーザーを使用できます。 これらの ID をエンタープライズ アプリケーションのロールとアクセス許可に割り当てることで、エージェントをサポートするアプリケーションがエージェント ID を認識し、エージェントがアプリケーション アクセス用の Microsoft Entra ID から適切なトークンを取得できるようにします。

この記事では、アプリケーションの機能に基づいて 3 つのオプションを選択する方法について説明します。

| アプリケーション機能 | セクション |
| --- | --- |
| アプリケーションが OAuth2 アクセス許可スコープを持つ API を公開する | エージェント ID またはサービス プリンシパルをアプリケーションのアクセス許可スコープに同意する |
| アプリケーションがサービス プリンシパルのロール要求を認識する | エージェント ID またはサービス プリンシパルをアプリケーション ロールに割り当てる |
| アプリケーションでは、ユーザーの SAML のみがサポートされます | SCIM で使用するアプリケーション ロールにエージェント ユーザーを割り当てる |

### アプリケーションのアクセス許可スコープへの同意

Microsoft Entra ID プラットフォームを使用するアプリケーションでは、 [他のクライアント アプリケーションが呼び出す API を公開](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-configure-app-expose-web-apis#register-the-web-api)できます。 API を使用するアプリケーションは、これらの API 呼び出しの OAuth スコープを公開できます。 ツールのサービス プリンシパルは、これらのスコープに対するアクセス許可に同意でき、API を呼び出すことができます。

[Image: 右側にスコープが公開されている Web API と左側にクライアント アプリがあり、それらのスコープがアクセス許可として選択されていることを示す線図。]

エージェント ID またはサービス プリンシパルへの同意の詳細については、 [アプリケーションのアクセス許可に対する管理者の同意に関するページを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent#grant-admin-consent-for-application-permissions-using-microsoft-graph-api)参照してください。

### アプリケーション ロールへのエージェント ID の割り当て

サービス プリンシパルのロール要求を認識するアプリケーションの場合は、エージェントのサービス プリンシパルまたはエージェント ID をアプリケーションのアプリ ロールに割り当てることができます。 アプリ ロールに割り当てると、アプリケーションのアクセス許可が得られます。 通常、アプリケーションのアクセス許可は、デーモン アプリ、バックエンド サービス、または自律エージェントによって使用されます。このエージェントは、ユーザーの操作なしで、認証を行い、承認された API 呼び出しを自身で行う必要があります。 詳細については、「 [アプリケーションにアプリ ロールを追加し、トークンで受け取る」を参照してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps)。

注

Microsoft Entra SCIM 送信プロビジョニングでは、ユーザーとグループのみがサポートされます。アプリ ロールに直接またはグループのメンバーとして割り当てられたサービス プリンシパルとエージェント ID は、アプリケーションにプロビジョニングされません。

まず、アプリケーションのマニフェスト内のアプリ ロールに`allowedMemberTypes`の`Application`があることを確認します。これは、ロールが他のアプリケーションで使用可能であることを示します。 ロールがユーザーのみを許可する場合、エージェント ID とサービス プリンシパルは、そのロールのアプリケーションではサポートされません。 その後、Microsoft Entra 管理センター、Microsoft Graph、または Microsoft Graph PowerShell を使用して、エージェント ID またはサービス プリンシパルにアプリ ロールを割り当てることができます。

- Microsoft Entra 管理センターを使用している場合は、 [アプリケーション アプリロールに割り当てる](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-add-app-roles-in-apps#assign-app-roles-to-applications)方法の手順に従ってください。
- Microsoft Graph または Microsoft Graph PowerShell を使用している場合、エージェント ID またはサービス プリンシパルをアプリケーション ロールに割り当てるのは、アプリケーションへの [ユーザーとグループの割り当てと](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal#assign-users-and-groups-to-an-application-using-microsoft-graph-api)似ています。 [リスト アプリ ロールの割り当てを](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-list-approleassignedto)使用して、エージェント ID またはサービス プリンシパルにロールの割り当てが既にあるかどうかを確認し、[アプリ ロールの割り当ての追加](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-post-approleassignedto)を使用して新しいアプリ ロールの割り当てを作成できます。 たとえば、PowerShell を使用してエージェント ID のアプリケーション ロールの割り当てを作成するには、エージェント ID の`$PrincipalId`に`id`を設定し、ターゲット アプリケーション サービス プリンシパルの`$ResourceId`に`id`し、ターゲット アプリケーション アプリ ロールのアプリ ロールの ID に`$AppRoleId`します。 次に、アプリケーション ロールの割り当てを追加するためのペイロードを構築します。

    ```powershell
    
    $Body = @{
      principalId = $PrincipalId
      resourceId = $ResourceId
      appRoleId = $AppRoleId
    } | ConvertTo-Json
    
    Invoke-MgGraphRequest -Method POST -Uri "https://graph.microsoft.com/v1.0/servicePrincipals/$ResourceId/appRoleAssignedTo" -Body $Body
    ```

### SAML ベースのアプリケーションへの割り当て

ユーザーがトークンを取得するための SAML のみをサポートするアプリケーションがあり、エージェントがそのアプリケーションとエージェント ID を自律的に対話できるようにする場合は、エージェントの ID をエージェント ユーザーと拡張できます。 その後、SAML およびエージェント ユーザーのサポートをエージェントに追加すると、エージェントはアプリケーションのトークンを取得できます。 エージェント ID とは異なり、アプリケーションで必要に応じて、エージェント ユーザーを SCIM 経由でそれらのアプリケーションにプロビジョニングすることもできます。

注

アプリケーションがエージェントの相互作用パターンをサポートしているかどうかをアプリケーション開発者に確認します。

エージェントが SAML アサーションを受け入れるアプリケーションと対話できるようにするには、Microsoft Entra ID のフローに参加して、代理フローに [応答して SAML アサーションを提供するための追加の](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow#saml-assertions-obtained-with-an-oauth20-obo-flow)アプリケーション登録が必要です。 エージェント自体とアプリケーションに加えて、テナント内の任意の SAML ベースのアプリケーションに対して SAML ヘルパー アプリケーション登録を作成する必要があります。 社内でエージェントを開発している場合は、このヘルパーがエージェントの一部になる可能性があります。

Microsoft Entra テナントには、次の成果物が存在する必要があります。

- エージェント ID ブループリントとエージェント ID ブループリント プリンシパル
- SAML ヘルパー アプリケーションの登録
- ターゲット リソースとしてのエンタープライズ アプリケーション
- `OAuth2PermissionGrant`と
    - クライアントとしての SAML ヘルパー アプリケーションの登録
    - リソースとしてのエンタープライズ アプリケーション
    - エンタープライズ アプリケーションのエンティティ ID に `/.default` を追加して連結したスコープ値

次に、各エージェント ID に対して、次を作成します。

- エージェントユーザー
- エンタープライズ アプリケーションのいずれかのロールに対するアプリケーション ロールの割り当て (エージェント ユーザー向け)
- `OAuth2PermissionGrant`(エージェント識別 ID の場合)
    - クライアントとしてのエージェントの識別子
    - リソースとしての SAML ヘルパー アプリケーションの登録
    - `api://'`の連結のスコープ値、SAML ヘルパー アプリケーションのアプリケーション ID、および`/.default`

[Image: SAML トークンの発行に必要な Microsoft Entra アーティファクト間の関係の図。]

エージェントに複数のエージェント ID がある場合は、アクセス許可の継承を使用して、エージェント ID ブループリントで同意を 1 回付与し、エージェント ID に対してそれを継承できます。

これらのアプリケーション、ユーザー、ロールの割り当て、および付与がテナントに設定されると、エージェントがエンタープライズ アプリケーションに対する認証に必要な SAML アサーションを取得するために以下の操作を実行できます。

- エージェント ID ブループリントとしてトークンを取得します。
- そのトークンを使用してエンドポイント `https://login.microsoftonline.com/<tenantid>/oauth2/v2.0/token` トークン要求を行い、エージェント ID としてフェデレーション ID 資格情報 (FIC) トークンを取得します。

    その要求では、 `client_id` はエージェント ID、 `scope` は `api://AzureADTokenExchange/.default`、 `grant_type` は `client_credentials`、 `client_assertion_type` は `urn:ietf:params:oauth:client-assertion-type:jwt-bearer`、 `client_assertion` は手順 1 のエージェント ID ブループリント トークンです。
- 2つのトークンを使用して、ヘルパーアプリケーションのスコープを持つエージェントユーザーとして、トークンに対するトークン要求を行います。

    この要求では、 `client_id` はエージェント ID、 `scope` は `api://'`の連結、SAML ヘルパー アプリケーションのアプリケーション ID、 `/.default`、 `grant_type` は `user_fic`、 `client_assertion_type` は `urn:ietf:params:oauth:client-assertion-type:jwt-bearer`、 `client_assertion` はエージェント ID ブループリント トークン、 `user_id` はエージェント ユーザー オブジェクト ID、 `user_federated_identity_credential` はエージェント ID トークンです。
- 代理呼び出し用のトークン要求を`https://login.microsoftonline.com/<tenantid>/oauth2/v2.0/token`、[SAML トークンを取得してください](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow#obtain-a-saml-token-by-using-an-obo-request-with-a-shared-secret)。

    この要求では、SAML ヘルパー アプリケーション登録の資格情報、アサーションとして`user_fic`によって返されるトークン、許可の種類`urn:ietf:params:oauth:grant-type:jwt-bearer`、`/.default`が追加されたエンタープライズ アプリケーションのアプリ ID のスコープ、`requested_token_use`の`on_behalf_of`、および`requested_token_type`の`urn:ietf:params:oauth:token-type:saml2`を指定します。 [応答](https://learn.microsoft.com/ja-jp/entra/identity-platform/v2-oauth2-on-behalf-of-flow#response-with-saml-assertion)には、Base64URL でエンコードされた SAML アサーションが含まれています。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/assign-app-owners"} -->
## エンタープライズ アプリケーション所有者を割り当てる - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-app-owners
- Service: entra-id / enterprise-apps
- Article date: 2024-12-06
- Summary: Microsoft Entra ID でエンタープライズ アプリケーションに所有者を割り当て、構成を管理し、ユーザー アクセスを効率的に合理化する方法について説明します。

Microsoft Entra ID の [エンタープライズ アプリケーションの所有者](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/overview-assign-app-owners) は、シングル サインオン、プロビジョニング、ユーザー割り当てなど、アプリケーションの組織固有の構成を管理できます。 所有者は、他の所有者を追加または削除することもできます。 他のアプリケーション管理者とは異なり、所有者は自分が所有するエンタープライズ アプリケーションのみを管理できます。 この記事では、アプリケーションの所有者を割り当てる方法について説明します。

### 前提条件

Microsoft Entra テナントにエンタープライズ アプリケーションを追加するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者。

### 所有者を割り当てる

::: zone pivot="portal"

エンタープライズ アプリケーションに所有者を割り当てるには

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**すべてのアプリケーション**&gt;**エンタープライズ アプリケーション**を参照します。
3. 所有者を追加するアプリケーションを選択します。
4. [ **所有者**] を選択し、[ **追加]** を選択して、所有者を選択できるユーザー アカウントの一覧を取得します。
5. アプリケーションの所有者にしたいユーザー アカウントを検索して選択します。
6. [ **選択** ] を選択して、アプリケーションの所有者として選択したユーザー アカウントを追加します。

::: zone-end

::: zone pivot="ms-powershell"

Microsoft Graph PowerShell を使用してエンタープライズ アプリケーションに所有者を追加するには、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) としてサインインし、 `Application.ReadWrite.All` アクセス許可に同意する必要があります。

次の例では、ユーザーのオブジェクト ID は aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb で、applicationId は 00001111-aaaa-2222-bbbb-3333cccc4444 です。

```powershell
1. Connect-MgGraph -Scopes 'Application.ReadWrite.All'

1. Import-Module Microsoft.Graph.Applications

$params = @{
    "@odata.id" = "https://graph.microsoft.com/v1.0/directoryObjects/aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"
}

New-MgServicePrincipalOwnerByRef -ServicePrincipalId 'aaaaaaaa-bbbb-cccc-1111-222222222222' -BodyParameter $params
```

::: zone-end

::: zone pivot="ms-graph"

Microsoft Graph API を使用してアプリケーションに所有者を割り当てるには、少なくとも[クラウド アプリケーション管理者](https://developer.microsoft.com/graph/graph-explorer)として [Graph Explorer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。

`Application.ReadWrite.All` アクセス許可に同意する必要があります。

次の Microsoft Graph クエリを実行して、アプリケーションに所有者を割り当てます。 アプリケーションを割り当てるユーザーのオブジェクト ID が必要です。 次の例では、ユーザーのオブジェクト ID は aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb で、appId は 00001111-aaaa-2222-bbbb-3333cccc4444 です。

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals(appId='00001111-aaaa-2222-bbbb-3333cccc4444')/owners/$ref
Content-Type: application/json

{
    "@odata.id": "https://graph.microsoft.com/v1.0/directoryObjects/aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb"
}
```

::: zone-end

注

ユーザー設定 [ **Microsoft Entra 管理ポータルへのアクセスを制限する** ] が [ `Yes`] に設定されている場合、管理者以外のユーザーは、Microsoft Entra 管理センターを使用して自分が所有するアプリケーションを管理することはできません。 所有しているエンタープライズ アプリケーションで実行できるアクションの詳細については、「 [所有エンタープライズ アプリケーション](https://learn.microsoft.com/ja-jp/entra/fundamentals/users-default-permissions#owned-enterprise-applications)」を参照してください。

注

現在、バックグラウンド アプリケーションとサービス プリンシパル オブジェクトの設定の依存関係のため、Entra 管理センター以外で追加されたアプリケーション所有者 (Graph API、PowerShell) では、属性や要求などの一部のエンタープライズ アプリケーション設定を管理したり、構成された SAML 証明書のプロパティやトークン暗号化設定を変更したりすることはできません。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/assign-user-or-group-access-portal"} -->
## アプリケーションへのユーザーとグループの割り当てを管理する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal
- Service: entra-id / enterprise-apps
- Article date: 2026-04-01
- Summary: ID 管理にMicrosoft Entra IDを使用して、アプリのユーザーとグループの割り当てと割り当て解除を行う方法について説明します。

この記事では、Microsoft Entra IDでエンタープライズ アプリケーションにユーザーとグループを割り当てる方法について説明します。 アプリケーションにユーザーを割り当てると、アプリケーションはユーザーの [My Apps](https://myapps.microsoft.com/) ポータルに表示され、簡単にアクセスできます。 アプリケーションでアプリ ロールが公開されている場合は、ユーザーに特定のアプリ ロールを割り当てることもできます。

グループをアプリケーションに割り当てると、そのグループ内のユーザーのみがアクセスできます。 割り当ては、入れ子になったグループにはカスケードされません。

グループベースの割り当てには、P1 または P2 エディションMicrosoft Entra ID必要があります。 入れ子になったグループ メンバーシップは現在サポートされていません。 この記事で説明する機能のライセンス要件の詳細については、[Microsoft Entra 価格に関するページ](https://azure.microsoft.com/pricing/details/active-directory)を参照してください。

制御を強化するために、ユーザー割り当てを必要とするように、特定の種類のエンタープライズ アプリケーションを構成できます。 アプリにユーザー割り当てを要求する方法の詳細については、「[アプリケーションへのアクセスの管理](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-access-management#requiring-user-assignment-for-an-app)」を参照してください。 ユーザーをアプリケーションに割り当てる必要があるアプリケーションでは、ディレクトリのユーザー同意ポリシーでユーザーが自分の代わりに同意できる場合でも、管理者がアクセス許可を付与する必要があります。

Microsoft Entraとの統合の前に、アプリケーションに既に 1 人以上のユーザーが存在する可能性があります。 アカウント検出機能を使用すると、アプリケーション内のすべてのユーザーのレポートを生成し、Entra で一致するアカウントを持っているユーザーと、アプリケーションに対してローカルなユーザーを 1 回のクリックで識別できます。 アカウント検出機能の詳細については [、こちらをご覧ください](https://learn.microsoft.com/ja-jp/entra/identity/app-provisioning/how-to-account-discovery)。 これにより、Entra へのオンボードを簡素化しながら、承認されていないアクセスを段階的に監視することもできます。

注

アプリケーション アクセス ポリシー グループなど、ポータルを使用してグループを管理する際に制限が発生する場合は、PowerShell や Microsoft Graph API などの代替方法を使用することを検討してください。

### 前提条件

エンタープライズ アプリケーションにユーザーを割り当てるには、次のものが必要です。

- アクティブなサブスクリプションを持つMicrosoft Entra アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - ユーザー管理者
    - サービス プリンシパルの所有者。
- グループベースの割り当てには、Microsoft Entra ID P1 または P2 を使用します。 この記事で説明する機能のライセンス要件の詳細については、[Microsoft Entra 価格に関するページ](https://azure.microsoft.com/pricing/details/active-directory)を参照してください。

::: zone pivot="portal"

### Microsoft Entra admin centerを使用してアプリケーションにユーザーとグループを割り当てる

エンタープライズ アプリケーションにユーザーまたはグループ アカウントを割り当てるには:

1. [Microsoft Entra admin center](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**All applications** に移動します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. **[ユーザーとグループ]**、**[ユーザーまたはグループの追加]** の順に選択します。

    Microsoft Entra テナント内のアプリケーションにユーザー アカウントを割り当てます。
5. **[割り当ての追加]** ペインで **[ユーザーとグループ]** の **[選択されていません]** を選択します。
6. アプリケーションに割り当てるユーザーまたはグループを見つけて選択します。 たとえば、`contosouser1@contoso.com` または `contosoteam1@contoso.com` です。
7. **[選択]** を選択します。
8. **[ロールの選択]** の下で、ユーザーまたはグループに割り当てるロールを選択します。 ロールをまだ定義していない場合、既定のロールは**既定のアクセス**です。
9. **[割り当ての追加]** ウィンドウで、**[割り当て]** を選択してアプリケーションにユーザーまたはグループを割り当てます。

### アプリケーションからユーザーとグループの割り当てを解除する

1. 「アプリケーションにユーザーとグループを割り当てる」セクションの手順に従って、**[ユーザーとグループ]** ウィンドウに移動します。
2. アプリケーションから割り当てを解除するユーザーまたはグループを見つけて選択します。
3. **[削除]** を選択して、アプリケーションからユーザーまたはグループの割り当てを解除します。

::: zone-end

::: zone pivot="entra-powershell"

### Microsoft Entra PowerShell を使用してアプリケーションにユーザーとグループを割り当てる

1. [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上としてサインインします。
2. 次のスクリプトを使用して、アプリケーションにユーザーを割り当てます。

    ```powershell
    connect-entra -scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"
    # Assign the values to the variables
    $username = "<Your user's UPN>"
    $app_name = "<Your App's display name>"
    $app_role_name = "<App role display name>"
    
    # Get the user to assign, and the service principal for the app to assign to
    $user = Get-EntraUser -ObjectId "$username"
    $sp = Get-EntraServicePrincipal -Filter "displayName eq '$app_name'"
    $appRole = $sp.AppRoles | Where-Object { $_.DisplayName -eq $app_role_name }
    
    # Assign the user to the app role
    New-EntraUserAppRoleAssignment -ObjectId $user.ObjectId -PrincipalId $user.ObjectId -ResourceId $sp.ObjectId -Id $appRole.Id
    ```

#### 例

次の使用例は、PowerShell を使用してユーザー Britta Simon を Microsoft Workplace Analytics アプリケーションに割り当てます。

1. PowerShell で、対応する値を変数 `$username`、`$app_name`、`$app_role_name` に割り当てます。

    ```powershell
    connect-entra -scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"
    $username = "britta.simon@contoso.com"
    $app_name = "Workplace Analytics"
    ```
2. この例では、Britta Simon に割り当てるアプリケーション ロールの正確な名前はわかりません。 次のコマンドを実行し、ユーザーの UPN とサービス プリンシパル表示名を使用してユーザー (`$user`) と サービス プリンシパル (`$sp`) を取得します。

    ```powershell
    $user = Get-EntraUser -ObjectId "$username"
    $sp = Get-EntraServicePrincipal -Filter "displayName eq '$app_name'"
    ```
3. 次のコマンドを実行して、サービス プリンシパルによって公開されるアプリ ロールを見つける

    ```powershell
    $appRoles = $sp.AppRoles
    # Display the app roles
    $appRoles | ForEach-Object {
        Write-Output "AppRole: $($_.DisplayName) - ID: $($_.Id)"
    }
    ```

    注

    既定の AppRole ID は `00000000-0000-0000-0000-000000000000` です。 このロールは、サービス プリンシパルに対して特定の AppRole が定義されていない場合に割り当てられます。
4. `$app_role_name` 変数に AppRole 名を割り当てます。 この例では、Britta Simon にアナリスト (制限付きアクセス) のロールを割り当てます。

    ```powershell
    $app_role_name = "Analyst (Limited access)"
    $appRole = $sp.AppRoles | Where-Object { $_.DisplayName -eq $app_role_name }
    ```
5. 次のコマンドを実行して、アプリのロールにユーザーを割り当てます。

    ```powershell
    New-EntraUserAppRoleAssignment -ObjectId $user.ObjectId -PrincipalId $user.ObjectId -ResourceId $sp.ObjectId -Id $appRole.Id
    ```

グループをエンタープライズ アプリに割り当てるには、`Get-EntraUser` を `Get-EntraGroup` に置き換え、`New-EntraUserAppRoleAssignment` を `New-EntraGroupAppRoleAssignment` に置き換えます。

### Microsoft Entra PowerShell を使用してアプリケーションからユーザーとグループの割り当てを解除する

1. 管理者特権で Windows PowerShell コマンド プロンプトを開きます。
2. [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上としてサインインします。
3. 次のスクリプトを使用して、アプリケーションからユーザーとロールを削除します。

    ```powershell
    connect-entra -scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"
    # Store the proper parameters
    $user = Get-Entrauser -ObjectId "<objectId>"
    $spo = Get-EntraServicePrincipal -ObjectId "<objectId>"
    
    #Get the ID of role assignment 
    $assignments = Get-EntraServicePrincipalAppRoleAssignedTo -ObjectId $spo.ObjectId | Where {$_.PrincipalDisplayName -eq $user.DisplayName}
    
    #if you run the following, it will show you what is assigned what
    $assignments | Select *
    
    #To remove the App role assignment run the following command.
    Remove-EntraServicePrincipalAppRoleAssignment -ObjectId $spo.ObjectId -AppRoleAssignmentId $assignments.ObjectId
    ```

### Microsoft Entra PowerShell を使用してアプリケーションに割り当てられているすべてのユーザーを削除する

1. 管理者特権で Windows PowerShell コマンド プロンプトを開きます。

次のスクリプトを使って、アプリケーションに割り当てられているすべてのユーザーとグループを削除します。

```powershell
connect-entra -scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"
#Retrieve the service principal object ID.
$app_name = "<Your App's display name>"
$sp = Get-EntraServicePrincipal -Filter "displayName eq '$app_name'"

# Get Microsoft Entra App role assignments using objectId of the Service Principal
$assignments = Get-EntraServicePrincipalAppRoleAssignedTo -ObjectId $sp.ObjectId -All

# Remove all users and groups assigned to the application
$assignments | ForEach-Object {
    if ($_.PrincipalType -eq "User") {
        Remove-EntraUserAppRoleAssignment -ObjectId $_.PrincipalId -AppRoleAssignmentId $_.ObjectId
    } elseif ($_.PrincipalType -eq "Group") {
        Remove-EntraGroupAppRoleAssignment -ObjectId $_.PrincipalId -AppRoleAssignmentId $_.ObjectId
    }
}
```

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してアプリケーションにユーザーとグループを割り当てる

1. 管理者特権で Windows PowerShell コマンド プロンプトを開きます。
2. `Connect-MgGraph -Scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"` を実行し、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上としてサインインします。
3. 次のスクリプトを使用して、アプリケーションにユーザーを割り当てます。

    ```powershell
    #Assign the values to the variables
    $userId = "<Your user's ID>"
    $app_name = "<Your App's display name>"
    $app_role_name = "<App role display name>"
    $sp = Get-MgServicePrincipal -Filter "displayName eq '$app_name'"
    
    #Get the user, the service principal and appRole.
    $params = @{
    "PrincipalId" =$userId
    "ResourceId" =$sp.Id
    "AppRoleId" =($sp.AppRoles | Where-Object { $_.DisplayName -eq $app_role_name }).Id
    }
    #Assign the user to the AppRole
    New-MgUserAppRoleAssignment -UserId $userId -BodyParameter $params |
        Format-List Id, AppRoleId, CreationTime, PrincipalDisplayName,
        PrincipalId, PrincipalType, ResourceDisplayName, ResourceId
    ```

#### 例

次の使用例は、Microsoft Graph PowerShell を使用して、ユーザー Britta Simon を Microsoft Workplace Analytics アプリケーションに割り当てます。

1. PowerShell で、対応する値を変数 `$userId`、`$app_name`、`$app_role_name` に割り当てます。

    ```powershell
    # Assign the values to the variables  
    $userId = "<Britta Simon's user ID>"  
    $app_name = "Workplace Analytics"  
    ```
2. この例では、Britta Simon に割り当てるアプリケーション ロールの正確な名前はわかりません。 次のコマンドを実行し、サービス プリンシパルの表示名を使用してサービス プリンシパル ($sp) を取得します。

    ```powershell
    # Get the service principal for the app  
    $sp = Get-MgServicePrincipal -Filter "displayName eq '$app_name'"  
    ```
3. 次のコマンドを実行して、サービス プリンシパルによって公開されるアプリ ロールを見つけます。

    ```powershell
    # Get the app roles exposed by the service principal  
    $appRoles = $sp.AppRoles  
    # Display the app roles  
    $appRoles | ForEach-Object {  
        Write-Output "AppRole: $($_.DisplayName) - ID: $($_.Id)"  
    }  
    ```

    注

    既定の AppRole ID は `00000000-0000-0000-0000-000000000000` です。 このロールは、サービス プリンシパルに対して特定の AppRole が定義されていない場合に割り当てられます。
4. `$app_role_name` 変数にロール名を割り当てます。 この例では、Britta Simon にアナリスト (制限付きアクセス) のロールを割り当てます。

    ```powershell
    # Assign the values to the variables  
    $app_role_name = "Analyst (Limited access)"  
    $appRoleId = ($sp.AppRoles | Where-Object { $_.DisplayName -eq $app_role_name }).Id  
    ```
5. パラメーターを準備し、次のコマンドを実行して、ユーザーをアプリ ロールに割り当てます。

    ```powershell
    # Prepare parameters for the role assignment  
    $params = @{  
        "PrincipalId" = $userId  
        "ResourceId" = $sp.Id  
        "AppRoleId" = $appRoleId  
    }  
    
    # Assign the user to the app role  
    New-MgUserAppRoleAssignment -UserId $userId -BodyParameter $params |   
        Format-List Id, AppRoleId, CreationTime, PrincipalDisplayName,   
        PrincipalId, PrincipalType, ResourceDisplayName, ResourceId  
    ```

グループをエンタープライズ アプリに割り当てるには、`Get-MgUser` を `Get-MgGroup` に置き換え、`New-MgUserAppRoleAssignment` を `New-MgGroupAppRoleAssignment` に置き換えます。

アプリケーション ロールにグループを割り当てる方法の詳細については、[New-MgGroupAppRoleAssignment](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/new-mggroupapproleassignment) のドキュメントをご覧ください。

### Microsoft Graph PowerShell を使用してアプリケーションからユーザーとグループの割り当てを解除する

1. 管理者特権で Windows PowerShell コマンド プロンプトを開きます。
2. `Connect-MgGraph -Scopes "Application.ReadWrite.All", "AppRoleAssignment.ReadWrite.All"` を実行し、[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)以上としてサインインします。
3. ユーザーとサービス プリンシパルを取得する

    ```powershell
    $user = Get-MgUser -UserId <userid>
    $sp = Get-MgServicePrincipal -ServicePrincipalId <ServicePrincipalId>
    ```
4. ロールの割り当ての ID を取得する

    ```powershell
    $assignments = Get-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $sp.Id | Where {$_.PrincipalDisplayName -eq $user.DisplayName}
    ```
5. 次のコマンドを実行し、アプリケーションに割り当てられているユーザーの一覧を表示する

    ```powershell
    $assignments | Select *
    ```
6. AppRole の割り当てを削除するには、次のコマンドを実行します。

    ```powershell
    Remove-MgServicePrincipalAppRoleAssignedTo -AppRoleAssignmentId  '<AppRoleAssignment-id>' -ServicePrincipalId $sp.Id
    ```

### Microsoft Graph PowerShell を使用して、アプリケーションに割り当てられているすべてのユーザーとグループを削除する

次のコマンドを実行し、アプリケーションに割り当てられているすべてのユーザーとグループを削除します。

```powershell
$assignments | ForEach-Object {
    if ($_.PrincipalType -in ("user", "Group")) {
        Remove-MgServicePrincipalAppRoleAssignedTo -ServicePrincipalId $sp.Id -AppRoleAssignmentId $_.Id  }
}
```

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph API を使用してアプリケーションにユーザーとグループを割り当てる

1. ユーザーとグループをアプリケーションに割り当てるには、[クラウド アプリケーション管理者](https://developer.microsoft.com/graph/graph-explorer)以上として [Graph Explorer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。

    次のアクセス許可に同意する必要があります。

    `Application.ReadWrite.All` および `AppRoleAssignment.ReadWrite.All`。

    アプリのロールの割り当てを許可するには、以下の 3 つの識別子が必要です。

    - `principalId`: アプリのロールを割り当てるユーザーまたはグループの ID。
    - `resourceId`: アプリのロールを定義するリソース servicePrincipal の ID。
    - `appRoleId`: ユーザーまたはグループに割り当てる appRole (リソース サービス プリンシパルで定義される) の ID。
2. エンタープライズ アプリケーションを取得します。 `DisplayName` でフィルター処理します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals?$filter=displayName eq '{appDisplayName}'
    ```

    応答本文から以下の値を記録します。

    - エンタープライズ アプリケーションのオブジェクト ID
    - ユーザーに割り当てる AppRole ID。 アプリケーションでロールが公開されていない場合、ユーザーには既定のアクセス ロールが割り当てられます。

    注

    既定の AppRole ID は `00000000-0000-0000-0000-000000000000` です。 このロールは、サービス プリンシパルに対して特定の AppRole が定義されていない場合に割り当てられます。
3. ユーザーのプリンシパル名でフィルター処理して、ユーザーを取得します。 ユーザーのオブジェクト ID を記録します。

    ```http
    GET https://graph.microsoft.com/v1.0/users/{userPrincipalName}
    ```
4. アプリケーションにユーザーを割り当ててください。

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals/{resource-servicePrincipal-id}/appRoleAssignedTo
    
    {
    "principalId": "aaaaaaaa-bbbb-cccc-1111-222222222222",
    "resourceId": "a0a0a0a0-bbbb-cccc-dddd-e1e1e1e1e1e1",
    "appRoleId": "00000000-0000-0000-0000-000000000000"
    }
    ```

    この例では、`resource-servicePrincipal-id` と `resourceId` の両方がエンタープライズ アプリケーションを表しています。

### Microsoft Graph API を使用してアプリケーションからユーザーとグループの割り当てを解除する

アプリケーションからすべてのユーザーとグループの割り当てを解除するには、次のクエリを実行します。

1. エンタープライズ アプリケーションを取得します。 `displayName` でフィルター処理します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals?$filter=displayName eq '{appDisplayName}'
    ```
2. アプリケーションの `appRoleAssignments` の一覧を取得します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals/{id}/appRoleAssignedTo
    ```
3. `appRoleAssignments` ID を指定して `appRoleAssignment` を削除します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/servicePrincipals/{resource-servicePrincipal-id}/appRoleAssignedTo/{appRoleAssignment-id}
    ```

Microsoft Graph Explorer では、アプリ ロールの割り当てのバッチ削除は直接サポートされていません。 各割り当てを個別に削除する必要があります。 ただし、Microsoft Graph PowerShell を使用して、各割り当てを反復処理して削除することで、このプロセスを自動化できます。

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/certificate-signing-options"} -->
## SAML トークンの証明書署名オプション - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/certificate-signing-options
- Service: entra-id / enterprise-apps
- Article date: 2025-07-10
- Summary: Microsoft Entra ID の事前統合済みアプリ用 SAML トークンに詳細な証明書署名オプションを使用する方法について説明します

現在、Microsoft Entra ID では、Microsoft Entra アプリ ギャラリーの何千もの事前統合済みアプリケーションをサポートしています 500 を超えるアプリケーションでは、[NetSuite](https://wikipedia.org/wiki/Security_Assertion_Markup_Language) アプリケーションなどの[セキュリティ アサーション マークアップ言語 (SAML)](https://azuremarketplace.microsoft.com/marketplace/apps/aad.netsuite) 2.0 プロトコルを使用したシングル サインオンがサポートされています。 お客様が SAML を使って Microsoft Entra ID からアプリケーションに対して認証されると、Microsoft Entra ID はアプリケーションにトークンを送信します (HTTP POST 経由)。 その後、アプリケーションがトークンを検証し、ユーザー名とパスワードの入力を求める代わりに、検証済みのトークンを使用してお客様をサインインします。 これらの SAML トークンは、Microsoft Entra ID で、特定の標準的なアルゴリズムで生成された一意の証明書で署名されます。

Microsoft Entra ID では、ギャラリー アプリケーションにいくつかの既定の設定が使用されます。 これらの既定値はアプリケーションの要件に応じて設定されます。

Microsoft Entra ID では、証明書署名オプションと証明書署名アルゴリズムを設定できます。

### 証明書署名オプション

Microsoft Entra ID は次の 3 つの証明書の署名オプションをサポートします

- **SAML アサーションへの署名**. この既定のオプションは、ギャラリーのアプリケーションのほとんどに設定されます。 このオプションを選択すると、Id プロバイダーとしての Microsoft Entra ID (IdP) は、アプリケーションの [X.509](https://wikipedia.org/wiki/X.509) 証明書を使用して SAML アサーションと証明書に署名します。
- **SAML 応答に署名します**。 このオプションを選択した場合、Microsoft Entra ID が IdP として、アプリケーションの X.509 証明書を使って SAML 応答に署名します。
- **SAML 応答とアサーションへの署名**. このオプションを選択した場合、Microsoft Entra ID が IdP として、アプリケーションの X.509 証明書を使って SAML トークン全体に署名します。

### 証明書署名アルゴリズム

Microsoft Entra ID では、SAML 応答に署名するための次の 2 つの署名アルゴリズム (セキュアハッシュ アルゴリズム (SHA)) がサポートされています。

- **SHA-256**。 Microsoft Entra ID では、この既定のアルゴリズムを使用して、SAML 応答に署名します。 これは最新のアルゴリズムであり、SHA-1 よりも安全です。 ほとんどのアプリケーションで、SHA-256 アルゴリズムをサポートしています。 アプリケーションが署名アルゴリズムとして SHA-1 しかサポートしていない場合は、設定を変更することができます。 それ以外の場合、SAML 応答の署名には SHA-256 アルゴリズムの使用をお勧めします。
- **SHA-1**。 このアルゴリズムは古く、SHA-256 より安全性が低くなります。 アプリケーションでこの署名アルゴリズムのみがサポートされている場合は、[ **署名** アルゴリズム] ドロップダウン リストでこのオプションを選択できます。 これで、Microsoft Entra ID は SHA-1 アルゴリズムを使用して SAML 応答に署名します。

### 前提条件

アプリケーションの SAML 証明書署名オプションと証明書署名アルゴリズムを変更するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか。

### 証明書署名オプションと署名アルゴリズムを変更する

アプリケーションの SAML 証明書署名オプションと証明書署名アルゴリズムを変更するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリケーション**&gt;**すべてのアプリケーション**をブラウズします。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。

次に、そのアプリケーションの SAML トークンの証明書署名オプションを変更します。

1. アプリケーションの概要ページの左側のウィンドウで、[ **シングル サインオン**] を選択します。
2. **[SAML を使用した単一 Sign-On の設定**] ページが表示されたら、手順 5 に進みます。
3. [ **SAML を使用したシングル Sign-On の設定** ] ページが表示されない場合は、[ **シングル サインオン モードの変更**] を選択します。
4. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。 **SAML** が使用できない場合、アプリケーションは SAML をサポートしていないため、この手順と記事の残りの部分は無視する可能性があります。
5. [ **SAML を使用した単一 Sign-On のセットアップ** ] ページで、[ **SAML 署名証明書** ] 見出しを見つけて、[ **編集** ] アイコン (鉛筆) を選択します。 **[SAML 署名証明書]** ページが表示されます。
6. [ **署名オプション** ] ドロップダウン リストで、[ **SAML 応答の署名**]、[ **SAML アサーションに署名**]、または [ **SAML 応答とアサーションに署名**] を選択します。 これらのオプションの説明は、この記事の前半の 「証明書署名オプション」に記載されています。
7. 署名 **アルゴリズム** ドロップダウン リストで、 **SHA-1** または **SHA-256** を選択します。 これらのオプションの説明は、この記事の「 証明書署名アルゴリズム 」セクションの前半に記載されています。
8. 選択内容に問題がなければ、[ **保存]** を選択して新しい SAML 署名証明書の設定を適用します。 それ以外の場合は、 **X** を選択して変更を破棄します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/cloud-app-security"} -->
## Microsoft Defender for Cloud Apps でアプリを表示し、制御する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/cloud-app-security
- Service: entra-id / enterprise-apps
- Article date: 2021-07-29
- Summary: アプリのリスク レベルを識別し、侵害と漏洩をリアルタイムで阻止し、アプリ コネクタを使用して、可視性とガバナンスのためのプロバイダー API を活用する方法について説明します。

クラウド アプリとサービスの利点を最大限に活用するには、IT チームは、重要なデータを保護するためのコントロールを維持しながら、アクセスをサポートするための適切なバランスを見つける必要があります。 Microsoft Defender for Cloud Apps では、あらゆる Microsoft サービスとサードパーティ製クラウド サービスに対するネット上の脅威を特定し、対処するために、豊富な検出機能、データ送受信の制御、高度な分析を備えています。

### ネットワーク内のシャドウ IT の検出と管理

IT 管理者が従業員が使用していると思うクラウド アプリの数を尋ねられると、実際には平均で 1,000 を超えるアプリがorganizationで従業員によって使用されていると言われます。 Shadow IT は、使用されているアプリとそのリスク レベルを把握し、特定するのに役立ちます。 誰も確認しておらず、セキュリティとコンプライアンス ポリシーに準拠していない未承認のアプリを従業員の 80% が使用しています。 また、従業員は企業ネットワークの外部からリソースやアプリにアクセスできるので、ファイアウォールに関するルールやポリシーだけでは不十分になっています。

Microsoft Cloud App Discovery (Microsoft Entra ID P1 機能) を使用して、使用されているアプリを検出し、それらのアプリのリスクを調査し、リスクの高い新しいアプリを特定するためのポリシーを構成し、これらのアプリをプロキシまたはファイアウォール アプライアンスを使用してネイティブにブロックするためにアプリを未承認にします。

- シャドウ IT の検出と識別
- 評価と分析
- 自分のアプリを管理する
- 高度なシャドウ IT の検出レポート
- 承認済みのアプリの制御

#### 詳細情報

- [ネットワーク内のシャドウ IT を検出して管理する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/tutorial-shadow-it)
- [Defender for Cloud Apps で検出されたアプリ](https://learn.microsoft.com/ja-jp/defender-cloud-apps/discovered-apps)

### ユーザー セッションの可視性と制御

今日の職場では、多くの場合、クラウド環境で何が起こっているかを事後的に知るだけでは十分ではありません。 従業員が意図的に、あるいは不注意でデータと組織を危険にさらす前に、侵害と漏洩をリアルタイムで阻止する必要があります。 Microsoft Defender for Cloud Apps は Microsoft Entra ID と共に、アプリの条件付きアクセス制御を使用し、以上のような機能をまとめて提供します。

セッション制御はリバース プロキシ アーキテクチャを使用し、Microsoft Entra 条件付きアクセスと一意に統合されます。 Microsoft Entra 条件付きアクセスによって、特定の条件に基づいて組織のアプリにアクセス制御を適用できます。 この条件では、条件付きアクセス ポリシーの適用先が、誰であるか (ユーザーまたはユーザーのグループ)、何であるか (どのクラウド アプリか)、どこであるか (どの場所およびネットワークか) が定義されます。 条件を決定したら、データをリアルタイムで保護できる Defender for Cloud Apps にユーザーをルーティングできます。

このコントロールを使用すると、次のことができます。

- ファイルのダウンロードを制御する
- B2B シナリオを監視する
- ファイルへのアクセスを制御する
- ダウンロード時にドキュメントを保護する

#### 詳細情報

- [Defender for Cloud Apps のセッション制御でアプリを保護する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/proxy-intro-aad)

### 高度なアプリの可視性と制御

アプリ コネクタは、アプリ プロバイダーの API を使用し、Microsoft Defender for Cloud Apps に接続したアプリの可視性と制御を強化するものです。 Defender for Cloud Apps では、クラウド プロバイダーが提供する API が活用されます。 各サービスには、調整、API 制限、動的時間シフト API ウィンドウなどの独自のフレームワークと API の制限があります。 Defender for Cloud Apps の製品チームは、API の使用を最適化し、最高のパフォーマンスを提供すべく、以上のサービスに取り組みました。 サービスによって API に適用されるさまざまな制限事項を考慮しながら、Defender for Cloud Apps エンジンでは許容される最大容量が使用されます。 テナント内のすべてのファイルをスキャンするなど、一部の操作では多数の API 呼び出しが必要になるため、長期間に及びます。 ポリシーによっては数時間あるいは数日にわたって実行されることを想定してください。

#### 詳細情報

- [Defender for Cloud Apps でアプリに接続する](https://learn.microsoft.com/ja-jp/defender-cloud-apps/enable-instant-visibility-protection-and-governance-actions-for-your-apps)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/cloudflare-conditional-access-policies"} -->
## Cloudflare Access で条件付きアクセス ポリシーを構成するチュートリアル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/cloudflare-conditional-access-policies
- Service: entra-id / enterprise-apps
- Article date: 2024-04-18
- Summary: Cloudflare Access でアプリケーション ポリシーとユーザー ポリシーを適用するように条件付きアクセスを構成します。

条件付きアクセスを使用すると、管理者は Microsoft Entra ID のアプリケーション ポリシーとユーザー ポリシーにポリシーを適用できるようになります。 条件付きアクセスは、決定のために ID ドリブンなシグナルをまとめ、組織のポリシーを適用します。 Cloudflare Access は、セルフホステッド、サービスとしてのソフトウェア (SaaS)、または Web 以外のアプリケーションへの接続を実現します。

詳細情報: [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

### 前提条件

- Microsoft Entra サブスクリプション
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を入手してください
- Microsoft Entra サブスクリプションにリンクされた Microsoft Entra テナント
    - 「[クイック スタート: Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)する」を参照してください
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者。
- Microsoft Entra サブスクリプションで構成されたユーザー
- Cloudflare アカウント
    - `dash.cloudflare.com`に移動して [Cloudflare の使用を開始](https://dash.cloudflare.com/sign-up)する

### シナリオのアーキテクチャ

- **Microsoft Entra ID** - ユーザー資格情報と条件付きアクセスを検証する ID プロバイダー (IdP)
- **アプリケーション** - IdP 統合用に作成した
- **Cloudflare Access** - アプリケーションへのアクセスを提供します

### ID プロバイダーを設定する

developers.cloudflare.com に移動して、 [Microsoft Entra ID を IdP として設定](https://developers.cloudflare.com/cloudflare-one/identity/idp-integration/entra-id/#set-up-entra-id-as-an-identity-provider)します。

注

IdP 統合の名前は、ターゲット アプリケーションに関連したものにすることをお勧めします。 たとえば、 **Microsoft Entra ID - 顧客管理ポータルなどです**。

### 条件付きアクセスを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**アプリ登録**&gt;**すべてのアプリケーション**を参照する
3. 作成したアプリケーションを選択します。
4. **[ブランド化とプロパティ]** に移動します。
5. **[ホーム ページの URL]** に、アプリケーションのホスト名を入力します。

    [Image: ブランドとプロパティのオプションとエントリのスクリーンショット。]
6. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**すべてのアプリケーション**を参照します。
7. アプリケーションを選択します。
8. **[プロパティ] を選択します**。
9. [ **ユーザーに表示]** で、[ **はい**] を選択します。 このアクションにより、アプリをアプリ起動ツールと [マイ アプリ](https://myapplications.microsoft.com/)に表示できます。
10. [ **セキュリティ**] で、[ **条件付きアクセス**] を選択します。
11. [「条件付きアクセス ポリシーの構築」を](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies)参照してください。
12. アプリケーションの他のポリシーを作成して有効にします。

### Cloudflare Access アプリケーションを作成する

Cloudflare Access アプリケーションに条件付きアクセス ポリシーを適用します。

1. `dash.cloudflare.com`に移動して [Cloudflare にサインイン](https://dash.cloudflare.com/login)します。
2. **ゼロ トラスト**で、**Access** に移動します。
3. [ **アプリケーション] を選択します**。
4. [セルフホステッド アプリケーションの追加を](https://developers.cloudflare.com/cloudflare-one/applications/configure-apps/self-hosted-apps/)参照してください。
5. **アプリケーション ドメイン**で、保護されたアプリケーション ターゲット URL を入力します。
6. **ID プロバイダーの場合は**、IdP 統合を選択します。
7. アクセス ポリシーを作成します。 [アクセス ポリシー](https://developers.cloudflare.com/cloudflare-one/policies/access/)と次の例を参照してください。

    注

    他のアプリケーションで同じ条件付きアクセス ポリシーが必要な場合は、IdP 統合を再利用します。 たとえば、多要素認証と最新の認証クライアントを必要とする条件付きアクセス ポリシーとのベースライン IdP 統合などが該当します。 特定の条件付きアクセス ポリシーを必要とするアプリケーションがある場合は、そのアプリケーション専用の IdP インスタンスを設定します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/cloudflare-integration"} -->
## Cloudflare と Microsoft Entra ID の統合を構成して安全なハイブリッド アクセスを実現する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/cloudflare-integration
- Service: entra-id / enterprise-apps
- Article date: 2025-05-21
- Summary: このチュートリアルでは、Cloudflare を Microsoft Entra ID と連携させて、安全にハイブリッド アクセスを行う方法を説明します

このチュートリアルでは、Microsoft Entra ID と Cloudflare ゼロ トラストを統合する方法について説明します。 ユーザー ID とグループ メンバーシップに基づいてルールを作成します。 ユーザーは、Microsoft Entra 資格情報を使用して認証し、ゼロ トラストで保護されたアプリケーションに接続します。

### 前提条件

- Microsoft Entra サブスクリプション
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を入手してください
- Microsoft Entra サブスクリプションにリンクされている Microsoft Entra テナント
    - 「[クイック スタート: Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)する」を参照してください
- Cloudflare ゼロ トラスト アカウント
    - お持ちでない場合は、「[Cloudflare のゼロ トラスト プラットフォームの使用を開始](https://dash.cloudflare.com/sign-up/teams)する」に進みます
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか。

### 組織の ID プロバイダーと Cloudflare Access を統合する

Cloudflare ゼロ トラスト アクセスは、企業アプリケーション、プライベート IP スペース、およびホスト名へのアクセスを制限する既定の拒否、ゼロ トラスト ルールを適用するのに役立ちます。 この機能では、仮想プライベート ネットワーク (VPN) よりも高速かつ安全にユーザーを接続します。 組織で複数の ID プロバイダー (IdP) を使用できるため、パートナーや請負業者と連携するときの煩わしさを軽減できます。

サインイン方法として IdP を追加するには、Cloudflare サインイン ページと Microsoft Entra ID で [Cloudflare にサインイン](https://dash.teams.cloudflare.com/) します。

次のアーキテクチャの図は統合を示しています。

[Image: Cloudflare と Microsoft Entra 統合アーキテクチャの図。]

### Cloudflare ゼロ トラスト アカウントと Microsoft Entra ID の統合

Cloudflare Zero Trust アカウントを Microsoft Entra ID のインスタンスと統合します。

1. [Cloudflare サインイン ページの Cloudflare Zero Trust ダッシュボードにサインインします](https://dash.teams.cloudflare.com/)。
2. **[設定]** に移動します。
3. [ **認証] を選択します**。
4. **Login メソッドの場合は**、[**新規追加]** を選択します。

    [Image: [認証] の [ログイン方法] オプションのスクリーンショット。]
5. [ **ID プロバイダーの選択**] で、[ **Microsoft Entra ID] を**選択します。
6. [ **Azure ID の追加]** ダイアログが表示されます。
7. Microsoft Entra インスタンスの資格情報を入力して、必要に応じて選択を行います。
8. **[保存] を選択します**。

### Cloudflare を Microsoft Entra ID に登録する

Cloudflare を Microsoft Entra ID に登録するには、次の 3 つのセクションの手順を使用します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**App 登録**に移動します。
3. [ **新しい登録**] を選択します。
4. アプリケーション名を入力 **します**。
5. パスの末尾に **コールバック** があるチーム名を入力します。 たとえば、「`https://<your-team-name>.cloudflareaccess.com/cdn-cgi/access/callback`」のように入力します。
6. [ **登録**] を選択します。

Cloudflare 用語集の [チーム ドメイン](https://developers.cloudflare.com/cloudflare-one/glossary#team-domain) 定義を参照してください。

[Image: [アプリケーションの登録] のオプションと選択のスクリーンショット。]

#### 証明書とシークレット

1. **Cloudflare Access** 画面の **[要点**] で、アプリケーション (クライアント) ID とディレクトリ (テナント) ID をコピーして保存します。

    [Image: Cloudflare Access 画面のスクリーンショット。]
2. 左側のメニューの [ **管理**] で、[ **証明書とシークレット**] を選択します。

    [Image: [証明書とシークレット] 画面のスクリーンショット。]
3. [ **クライアント シークレット**] で、[ **+ 新しいクライアント シークレット**] を選択します。
4. [ **説明]** に、クライアント シークレットを入力します。
5. [ **有効期限]** で、有効期限を選択します。
6. **追加** を選択します。
7. [ **クライアント シークレット] の** [ **値** ] フィールドから値をコピーします。 アプリケーションのパスワードの値を考えてください。 この例の値が表示され、Azure の値は [Cloudflare Access の構成] に表示されます。

#### アクセス許可

1. 左側のメニューで、[ **API のアクセス許可**] を選択します。
2. [ **+ アクセス許可の追加] を選択します**。
3. [ **API の選択**] で、 **Microsoft Graph** を選択します。

    [Image: [API アクセス許可の要求] の [Microsoft Graph] オプションのスクリーンショット。]
4. **委任されたアクセス許可** を次のアクセス許可に対して選択します。

    - Email
    - openid（オープンID認証プロトコル）
    - プロファイル
    - オフラインアクセス
    - user.read
    - directory.read.all
    - group.read.all
5. [ **管理**] で、[ **+ アクセス許可の追加]** を選択します。

    [Image: 要求 API のアクセス許可のオプションと選択のスクリーンショット。]
6. **管理者の同意を付与する...** を選択します。

    [Image: [API のアクセス許可] で構成されたアクセス許可のスクリーンショット。]
7. Cloudflare ゼロ トラスト ダッシュボードで、[ **設定] &gt; [認証]** に移動します。
8. [ **Login methods]\(ログインメソッド\) で**、[ **Add new]\(新規追加\**) を選択します。
9. **Microsoft Entra ID を選択します**。
10. **アプリケーション ID**、**アプリケーション シークレット**、**およびディレクトリ ID** の値を入力します。
11. **[保存] を選択します**。

注

Microsoft Entra グループの場合は、[ **Microsoft Entra ID プロバイダーの編集]** の **[サポート グループ** ] **で [オン**] を選択します。

### 統合をテストする

1. Cloudflare Zero Trust ダッシュボードで、 **設定**&gt;**Authentication** に移動します。
2. **[ログイン方法] で**、[Microsoft Entra ID] で [**テスト**] を選択します。
3. Microsoft Entra の資格情報を入力します。
4. **[接続が機能します]** メッセージが表示されます。

    [Image: [接続が機能します] メッセージのスクリーンショット。]
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-admin-consent-workflow"} -->
## 管理者の同意ワークフローの構成 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow
- Service: entra-id / enterprise-apps
- Article date: 2024-12-29
- Summary: 管理者の同意を必要とするアプリケーションへのアクセスをエンド ユーザーが要求できるように構成する方法について説明します。

この記事では、管理者の同意ワークフローを構成して、管理者の同意が必要なアプリケーションへのアクセスをユーザーが要求できるようにする方法について説明します。 管理者の同意ワークフローを使用して、要求を作成する機能を有効にします。 アプリケーションへの同意の詳細については、[ユーザーと管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/user-admin-consent-overview)に関する説明を参照してください。

管理者の同意ワークフローによって、管理者は、管理者の承認を必要とするアプリケーションへのアクセス権を安全に付与することができます。 ユーザーがアプリケーションにアクセスしようとしているものの同意ができない場合には、ユーザーは管理者の承認の要求を送信できます。 要求は、レビュー担当者として指定された管理者にメールで送信されます。 レビュー担当者が要求に対してアクションを実行すると、ユーザーにそのアクションが通知されます。

レビュー担当者が要求を承認するには、要求されたアプリケーションに管理者の同意を付与するための[アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent#prerequisites)を持っている必要があります。 レビュー担当者として指定するだけでは、特権は昇格されません。

### 前提条件

管理者の同意ワークフローを構成するには、以下が必要です。

- Azure アカウント。 [アカウントは無料で作成できます](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 管理者の同意ワークフローを有効にするには、グローバル管理者である必要があります。

重要

Microsoft は、アクセス許可が最も少ないロールを使用することを推奨しています。 このプラクティスは、組織のセキュリティを強化するのに役立ちます。 グローバル管理者は、緊急シナリオに限定する必要がある、または既存のロールを使用できない場合に、高い特権を持つロールです。

### 管理者の同意ワークフローの有効化

管理者の同意ワークフローを有効にし、レビュー担当者を選択するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[Enterprise アプリ]**&gt;**[同意とアクセス許可]**&gt;**[管理者の同意設定]** を参照します。
3. **[管理者の同意要求]** で、**[ユーザーは、自分が同意できないアプリに対して管理者の同意を要求できます]** に対して **[はい]** を選択します。

    [Image: 管理者の同意ワークフロー設定のスクリーンショット。]
4. 次の設定を構成します。

    - **[管理者の同意要求を確認できるユーザー]** - 管理者の同意要求のレビュー担当者として指定されているユーザー、グループ、またはロールを選択します。 レビュー担当者は、管理者の同意要求を表示、ブロック、または拒否できますが、Microsoft Graph アプリ ロール (アプリケーションのアクセス許可) を要求するアプリについて管理者の同意要求を承認できるのはグローバル管理者だけです。 レビュー担当者として指定されたユーザーは、レビュー担当者として設定されると **[自分の保留]** タブに受信要求を表示できます。 新しいレビュー担当者は、既存または期限切れの管理者の同意要求に対して操作を行うことはできません。
    - **選択したユーザーは、要求に関するメール通知を受け取ります。**要求が行われたときのレビュー担当者へのメール通知を有効または無効にします。 このオプションを無効にすると、要求が行われ、レビューされたときに要求者に電子メール通知も無効になります。
    - **選択したユーザーは、要求の有効期限の通知を受け取ります。**要求の有効期限が近づいたときの、レビュー担当者へのリマインダー メール通知を有効または無効にします。 最初の期限切れ間近のリマインダー メールは、構成された [同意要求の有効期限 (日数)] の途中で送信される可能性があります。たとえば、同意要求が 3 日で期限切れするように構成した場合、最初のアラーム メールは 2 日目に送信され、同意要求の有効期限が切れるとほぼすぐに、最後の有効期限のメールが送信されます。
    - **同意要求の有効期限 (日数)** -要求の有効期間を指定します。
5. **保存**を選択します。 ワークフローが有効になるまでに、最大で 1 時間かかることがあります。

Note

このワークフローのレビュー担当者を追加または削除するには、**[Who can review admin consent requests] (管理者の同意要求を確認できるユーザー)** の一覧を変更します。 現在この機能には制限事項があり、レビュー担当者は、自身がレビュー担当者として指定されていた間に行われた要求をレビューする能力を保持し、レビュー担当者の一覧から削除された後で、これらの要求の有効期限通知メールを受信します。 また、レビュー担当者として指定される前に作成された要求には、新しいレビュー担当者が割り当てられません。

### Microsoft Graph を使用して管理者の同意ワークフローを構成する

管理者の同意ワークフローをプログラムで構成するには、Microsoft Graph で [Update adminConsentRequestPolicy](https://learn.microsoft.com/ja-jp/graph/api/adminconsentrequestpolicy-update) API を使用します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-app-management-policies"} -->
## アプリケーションの構成方法に関する制限を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-app-management-policies
- Service: entra-id / enterprise-apps
- Article date: 2025-07-23
- Summary: Microsoft Entra IDでアプリ管理ポリシーを構成して、テナント内のアプリとサービス プリンシパルを構成する方法に関する制限を設定します。 詳細なガイダンスを使用して、環境をセキュリティで保護します。

この記事では、アプリの所有者と管理者が組織内のアプリケーションとサービス プリンシパルを構成する方法を制御するために、Microsoft Entra IDでアプリ管理ポリシーを構成する方法について説明します。 このガイダンスは、管理者が安全でない構成に起因するセキュリティ リスクを軽減するのに役立ちます。

構成に使用できる一連の制限事項には、次のものが含まれます。

| 制限名 | Description | セキュリティ値 | 可用性 |
| --- | --- | --- | --- |
| 非対称鍵の有効期間 | 非対称キー (証明書) の最大有効期間範囲を適用します。 | 有効期間の長い資格情報からセキュリティ リスクを軽減します | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで `Restrict max certificate lifetime` と呼ばれます。 |
| audiences | signInAudience 値に基づいてアプリの作成または昇格を制限します。 | 承認されていないマルチテナント アプリケーションまたはコンシューマー向けアプリケーションを防止します。 | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで `Block multitenant applications` および `Block consumer account applications` と呼ばれます。 |
| カスタムパスワード追加 | アプリケーションまたはサービス プリンシパルのカスタム パスワード シークレットを制限します。 | システムによって生成されたパスワードよりも簡単に侵害される、ユーザーが指定した新しいアプリ パスワードを防止します。 | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで `Block custom passwords` と呼ばれます。 |
| nonDefaultUriAddition | `api://{appId}`または`api://{tenantId}/{appId}`の既定の形式の 1 つである場合を除き、アプリの新しい識別子 URI をブロックします。 | 不適切な対象ユーザー検証によるセキュリティ リスクの軽減 | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで `Block custom identifier URIs` と呼ばれます。 |
| uriAdditionWithoutUniqueTenantIdentifier | セキュリティ [で保護された形式](https://aka.ms/identifier-uri-policy)の 1 つである場合を除き、アプリの新しい識別子 URI をブロックします。 | 対象ユーザーの重複によるセキュリティ リスクを軽減 | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで `Block identifier URIs without unique tenant identifier` と呼ばれます。 |
| パスワードの追加 | アプリケーションへの新しいパスワード (シークレットとも呼ばれます) の追加を完全にブロックします。 | 資格情報の最も簡単に侵害された形式である新しいパスワードを防止します。 | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで、`symmetricKeyAddition` 設定の下の `Block password addition` 制限と組み合わせて使用します。 |
| パスワード有効期間 | パスワード シークレットの最大有効期間範囲を適用します。 | 有効期間の長い資格情報からセキュリティ リスクを軽減します | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで、`symmetricKeyLifetime` 設定の下の `Restrict max password lifetime` 制限と組み合わせて使用します。 |
| 対称鍵追加 | アプリケーションで対称キーを制限する。 | 新しい対称キー（すなわち、効果的に最も簡単に侵害され得る形式のパスワードと同様の資格情報）の作成を防止します。 | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで、`passwordAddition` 設定の下の `Block password addition` 制限と組み合わせて使用します。 |
| シンメトリックキー有効期間 | 対称キーの最大有効期間範囲を適用します。 | 有効期間の長い資格情報からセキュリティ リスクを軽減します | [app 管理ポリシー API](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true) および [Microsoft Entra 管理センター](https://aka.ms/app-mgmt-policy-ux) を使用して構成できます。 Microsoft Entra 管理センターで、`passwordLifetime` 設定の下の `Restrict max password lifetime` 制限と組み合わせて使用します。 |
| 信頼された認証局 | 発行者が信頼された証明機関の一覧に表示されていない場合は、新しい証明書資格情報をブロックします。 | テナント内のアプリで信頼された CA のみが使用されるようにする | [アプリ管理ポリシー API を](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true)使用して構成できます。 |

アプリ管理ポリシー API のしくみの詳細については、 [API のドキュメントを参照してください](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true)。

### [前提条件]

アプリ管理ポリシーを構成するには、次のものが必要です。

- ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- [セキュリティ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#security-administrator)ロール、および[クラウド アプリ管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)または[アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。 または、 [全体管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) ロールのみ。

### 制限を構成する

Microsoft Entra 管理センターまたは Microsoft Graph APIを使用して、Microsoft Entra IDでアプリ管理ポリシーを構成できます。

#### すべてのアプリケーションの制限を有効にする

この例では、組織内のすべてのアプリケーションとサービス プリンシパルに対する新しいパスワードの追加をブロックします。 同様のプロセスを使用して、他の制限を有効にすることができます。

## [Microsoft Entra 管理センター](#tab/portal)
Microsoft Entra 管理センターを使用して新しいパスワードをブロックするには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Application policies** に移動します。
3. [ **パスワードの追加をブロックする] を選択します**。
4. 状態を **[オン]** に設定します。 [適用対象] フィールドが **[すべてのアプリケーション**] に設定されていることを確認します。
5. **保存**を選んで設定を保存します。

[Image: [パスワードの追加] 制限のスクリーンショット。]

## [Microsoft Graph](#tab/graph)
Microsoft Graphを使用してアプリケーションとサービス プリンシパルに対する新しいパスワードの追加をブロックするには:

1. 既存のテナント全体のアプリ管理ポリシーを取得します。

    ```http
    GET https://graph.microsoft.com/beta/policies/defaultAppManagementPolicy
    ```
2. `passwordCredentials`プロパティおよび`applicationRestrictions`プロパティの下の`servicePrincipalsRestrictions`コレクションを見つけます。 両方のコレクションで、 `passwordAddition` 制限の状態と `symmetricKeyAddition` の制限を `enabled`に設定します。 コレクションに他の制限が既に存在する場合は、誤って無効にしないように要求に含めます。

    ```http
    PATCH https://graph.microsoft.com/beta/policies/defaultAppManagementPolicy
    
    {
        "applicationRestrictions": {
            "passwordCredentials": [
                {
                    "restrictionType": "passwordAddition",
                    "state": "enabled"
                },
                {
                    "restrictionType": "symmetricKeyAddition",
                    "state": "enabled"
                }
            ]
        },
          "servicePrincipalRestrictions": {
            "passwordCredentials": [
                {
                    "restrictionType": "passwordAddition",
                    "state": "enabled"
                },
                {
                    "restrictionType": "symmetricKeyAddition",
                    "state": "enabled"
                }
            ]
        }      
    }
    ```

---

#### アプリケーションに例外を付与する

テナント全体のルールに例外が必要な場合があります。 この例では、カスタム識別子 URI をブロックする制限に対してアプリに例外を付与するため、カスタム URI を追加できます。 他の制限については、同様のプロセスに従うことができます。

## [Microsoft Entra 管理センター](#tab/portal)
Microsoft Entra 管理センターを使用してカスタム識別子 URI をブロックする制限に対してアプリに例外を付与するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Application policies** に移動します。
3. [ **カスタム識別子 URI をブロックする] を選択します**。
4. 状態が **[オン]** になっていることを確認します。 [適用対象] フィールドを除外対象 **のすべてのアプリケーションに設定します**。
5. [ **除外されたアプリ**] で、[ **アプリケーションの追加]** を選択します。
6. 制限から除外するアプリケーションを選択します。
7. **保存**を選んで設定を保存します。

## [Microsoft Graph](#tab/graph)
Microsoft Graphを使用してカスタム識別子 URI をブロックする制限に対してアプリに例外を付与するには:

1. 新しいカスタム アプリ管理ポリシーを作成します。 作成時に、`nonDefaultUriAddition`の`restrictions.applicationRestrictions`制限を `disabled` に設定します。

    ```http
    POST https://graph.microsoft.com/beta/policies/appManagementPolicies
    
    {
        "displayName": "Identifier URI exemption policy",
        "description": "Policy granting an exemption to the nonDefaultUriAddition restriction",
        "isEnabled": true,
        "restrictions": {
            "applicationRestrictions": {
                "identifierUris": {
                    "nonDefaultUriAddition": {
                        "state": "disabled"
                    }
                }
            }
        }
    }
    ```
2. 応答から新しいポリシーの ID を記録します。
3. 除外するアプリケーションに新しいカスタム ポリシーを割り当てます。

    ```http
    POST https://graph.microsoft.com/beta/applications/{objectIdOfTheApplication}/appManagementPolicies/$ref
    
    {
        "@odata.id":"https://graph.microsoft.com/v1.0/policies/appManagementPolicies/{idOfTheCustomPolicy}"
    }
    ```
4. このアプリケーションにカスタム ポリシーが既に割り当てられていることを示すエラーが発生した場合は、代わりにこの新しい除外を含むようにそのポリシーを変更します。 既存のポリシーが他のアプリケーションに割り当てられていると、それらのアプリケーションも除外対象になりますのでご注意ください。

---

#### ユーザーまたはサービスに例外を付与する

場合によっては、アプリケーションを作成または変更するユーザーまたはサービスに例外を付与する必要があります。 たとえば、組織内の自動化されたプロセスが定期的にアプリケーションを作成し、それらにパスワードを設定しているとします。 組織内の新しいパスワードをブロックしたいが、更新中にこの自動化されたプロセスを中断したくない。 この場合、アプリケーションの例外は機能しません。これは、作成または更新されるアプリがまだ存在しないためです。 代わりに、プロセス自体に例外を適用できます。

この種の例外 ("アクター" または "呼び出し元" 例外のラベルが付けられることもあります) は、 [カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)を使用して構成されます。 このため、前提条件のロールに加えて、このシナリオには 2 つの追加ロールが必要 です。

- [属性定義管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-definition-administrator)
- [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)

注

呼び出し元ベースの除外をユーザーに割り当てた場合でも、そのユーザーは、[attribute 割り当て閲覧者ロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)を持っていない限り、Entra 管理センターまたはAzure ポータルを使用して、ポリシーに違反する方法でアプリケーションを変更できない可能性があります。 これは、Microsoft Graphや PowerShell などの他のアプリ管理インターフェイスでは必要ありません。

この例では、他のアプリケーションとサービス プリンシパルに追加する新しい証明書に対して最長有効期間を適用する制限に例外をサービスに付与します。 サービスは、そのサービス プリンシパルによって表されます。 [エンタープライズ アプリケーション](https://entra.microsoft.com/#view/Microsoft_AAD_IAM/StartboardApplicationsMenuBlade/%7E/AppAppsPreview)でサービスを検索して、そのサービスのサービス プリンシパルを見つけます。

## [Microsoft Entra 管理センター](#tab/portal)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Application policies** に移動します。
3. [ **証明書の最大有効期間を制限する**] を選択します。
4. 状態が **[オン]** になっていることを確認します。 [適用対象] フィールドを除外対象 **のすべてのアプリケーションに設定します**。
5. [ **除外された呼び出し元] で**、[ **除外された呼び出し元の追加]** を選択します。
6. 制限から除外するアプリの作成/更新を呼び出すユーザーまたはサービス プリンシパルを選択します。
7. **保存**を選んで設定を保存します。

## [Microsoft Graph](#tab/graph)
###### カスタム セキュリティ属性定義を作成する

この呼び出し元ベースの除外は、 [カスタム セキュリティ属性を](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)使用して行われます。 カスタム セキュリティ属性はキーと値のペアです。テナントでキーと値のペアを作成するための定義が必要です。その後、キーと値のペアのインスタンスを特定のユーザーまたはサービス プリンシパルに追加できます。

カスタム セキュリティ属性の定義は [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add) を使用して作成できますが、Microsoft Graphを使用して作成することもできます。 まず、 [属性セットを作成](https://learn.microsoft.com/ja-jp/graph/api/directory-post-attributesets) します (まだ作成していない場合)。 属性セットは、カスタム セキュリティ属性定義のコンテナーです。

```http
POST https://graph.microsoft.com/v1.0/directory/attributeSets 
{
    "id":"PolicyExemptions",
    "description":"Attributes for granting exemptions to policy",
    "maxAttributesPerSet":25
}
```

それから[定義を作成します](https://learn.microsoft.com/ja-jp/graph/api/directory-post-customsecurityattributedefinitions)。

```http
POST https://graph.microsoft.com/v1.0/directory/customSecurityAttributeDefinitions
{
    "attributeSet": "PolicyExemptions",
    "description": "App mgmt policy exemption attributes",
    "isCollection": false,
    "isSearchable": true,
    "name": "AppManagementExemption",
    "status": "Available",
    "type": "String",
    "usePreDefinedValuesOnly": true,
    "allowedValues": [
        {
            "id": "ExemptFromCertificateLifetimeRestriction",
            "isActive": true
        }
    ]
}
```

これらは単なる値の例です。カスタム セキュリティ属性には任意の名前を付けることができます。 ただし、作成するカスタム セキュリティ属性の定義が `String` 型であり、 `isCollection` が `false` に設定されていることを確認します。 現在、単一値の文字列型は、アプリ管理ポリシーで除外インジケーターとしてサポートされている唯一のカスタム セキュリティ属性です。

###### カスタム セキュリティ属性を除外インジケーターとして追加する

`excludeActors`と`asymmetricKeyLifetime`の`applicationRestrictions`制限の下で、`servicePrincipalRestrictions` プロパティを更新します。

```http
PATCH https://graph.microsoft.com/beta/policies/defaultAppManagementPolicy

 {  
    "applicationRestrictions": {
        "keyCredentials": [
            {
                "restrictionType": "asymmetricKeyLifetime",
                "state": "enabled",
                "maxLifetime": "P180D",
                "excludeActors": {
                    "customSecurityAttributes": [
                        {
                            "@odata.type": "#microsoft.graph.customSecurityAttributeStringValueExemption",
                            "id": "PolicyExemptions_AppManagementExemption",  //This `id` value is the concatenation of "AttributeSet_AttributeName"
                            "operator": "equals",
                            "value": "ExemptFromCertificateLifetimeRestriction"
                        }
                    ]
                }
            }
        ]
    },
    "servicePrincipalRestrictions": {
        "keyCredentials": [
            {
                "restrictionType": "asymmetricKeyLifetime",
                "state": "enabled",
                "maxLifetime": "P180D",
                "excludeActors": {
                    "customSecurityAttributes": [
                        {
                            "@odata.type": "#microsoft.graph.customSecurityAttributeStringValueExemption",
                            "id": "PolicyExemptions_AppManagementExemption",  //This `id` value is the concatenation of "AttributeSet_AttributeName"
                            "operator": "equals",
                            "value": "ExemptFromCertificateLifetimeRestriction"
                        }
                    ]
                }
            }
        ]
    }
 }
```

これは、特定のカスタム セキュリティ属性値が割り当てられているユーザーまたはサービス プリンシパルをポリシーから除外したいことをMicrosoft Entraに示します。

###### カスタム セキュリティ属性をサービス プリンシパルに割り当てる

カスタム セキュリティ属性は、Microsoft Entra 管理センターを通じて [users](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes) と [service principals](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/custom-security-attributes-apps) の両方に割り当てることができますが、Microsoft Graphを使用して割り当てることもできます。

サービス プリンシパルに割り当てるには:

```http
PATCH https://graph.microsoft.com/v1.0/servicePrincipals/{id}
{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "AppManagementExemption":"ExemptFromCertificateLifetimeRestriction"
        }
    }
}
```

{id} をサービス プリンシパルのオブジェクト ID に置き換えます。

これが完了すると、カスタム セキュリティ属性が割り当てられたサービス プリンシパルは、変更可能な任意のアプリに有効期間の長い証明書を追加できるようになります。

---

#### 特定のアプリケーションに制限を適用する

テナント全体に制限を適用する準備ができなくても、セキュリティに依存するアプリケーションの選択したセットにルールを適用したい場合があります。 この例では、制限をブロックするカスタム パスワードを 1 つのアプリケーションに適用します。 他の制限については、同様のプロセスに従うことができます。

## [Microsoft Entra 管理センター](#tab/portal)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **Entra ID**&gt;**Enterprise apps**&gt;**Application policies** に移動します。
3. [ **カスタム パスワードをブロックする] を選択します**。
4. 状態が **[オン]** になっていることを確認します。 [適用対象] フィールドを **[アプリケーションの選択**] に設定します。
5. [ **アプリケーションの追加] を選択します**。
6. 制限を適用するアプリケーションを選択します。
7. **保存**を選んで設定を保存します。

[Image: [カスタム パスワードのブロック] 制限のスクリーンショット。]

## [Microsoft Graph](#tab/graph)
1. 新しいカスタム アプリ管理ポリシーを作成します。 作成時に、`customPasswordAddition`の`restrictions.passwordCredentials`制限を `enabled` に設定します。

    ```http
    POST https://graph.microsoft.com/beta/policies/appManagementPolicies
    
    {
        "displayName": "Custom password policy",
        "description": "Policy that enforces the custom password restriction",
        "isEnabled": true,
        "restrictions": {
            "passwordCredentials": [
                {
                    "restrictionType": "customPasswordAddition",
                    "state": "enabled"
                }
            ]
        }
    }
    ```
2. 応答から新しいポリシーの ID を記録します。
3. 制限を適用するアプリケーションに新しいカスタム ポリシーを割り当てます。

    ```http
    POST https://graph.microsoft.com/beta/applications/{objectIdOfTheApplication}/appManagementPolicies/$ref
    
    {
        "@odata.id":"https://graph.microsoft.com/v1.0/policies/appManagementPolicies/{idOfTheCustomPolicy}"
    }
    ```
4. このアプリケーションにカスタム ポリシーが既に割り当てられていることを示すエラーが表示される場合は、代わりにこの新しい制限を含むようにそのポリシーを変更します。 既存のポリシーが他のアプリケーションに割り当てられていないことを確認してください。割り当てられていると、他のアプリケーションにもそのポリシーが強制適用されます。

---

#### カスタム ポリシーを表示する

[カスタム ポリシー](https://learn.microsoft.com/ja-jp/graph/api/resources/appmanagementpolicy?view=graph-rest-beta&preserve-view=true) は、特定のアプリケーションとサービス プリンシパルに適用されます。 特定のアプリのテナント全体の構成をオーバーライドするために使用されます。 詳細については、 [こちらをご覧ください](https://learn.microsoft.com/ja-jp/graph/api/resources/applicationauthenticationmethodpolicy?view=graph-rest-beta&preserve-view=true)。

Microsoft Entra 管理センターでは、意図に基づいてカスタム ポリシーが自動的に構成されます。 たとえば、特定のアプリの制限の除外を許可する場合、Microsoft Entra 管理センターはバックグラウンドでその動作を持つカスタム ポリシーを作成し、アプリケーションに割り当てます。

このため、カスタム ポリシーの一覧をMicrosoft Entra 管理センターで直接表示することはできません。 ただし、Microsoft Graphで表示できます。

## [Microsoft Entra 管理センター](#tab/portal)
カスタム ポリシーの一覧は、Microsoft Entra 管理センターで直接表示することはできません。 [Microsoft Graph] タブに切り替えます。

## [Microsoft Graph](#tab/graph)
```http
GET https://graph.microsoft.com/beta/policies/appManagementPolicies
```

---

#### Microsoft Entra 管理センターで使用するポリシーの状態を修正する

Microsoft Entra 管理センターの外部でアプリ管理ポリシーを構成したことがある場合は、ポータルが想定しない方法でアプリ管理ポリシーを構成している可能性があります。 その場合、制限を読み込むと、次のようなエラー メッセージが表示されます。

`The restriction have been modified outside of this interface. To prevent data loss, editing is disabled until restrictions are synchronized.`

Microsoft Entra 管理センターが期待する状態に制限を戻すには、Microsoft Graphを使用してそれらを更新する必要があります。

## [Microsoft Entra 管理センター](#tab/portal)
これは、Microsoft Entra 管理センターを使用して行うことはできません。 [Microsoft Graph] タブに切り替えます。

## [Microsoft Graph](#tab/graph)
まず、テナント全体のアプリ管理ポリシーを取得します。

```http
GET https://graph.microsoft.com/beta/policies/defaultAppManagementPolicy
```

Microsoft Entra 管理センターで、自分がブロックされているいずれかの制限に移動してください。

###### パスワードの追加

**[パスワードの追加をブロックする**] 制限では、次の 4 つの制限がすべて同じ状態であることが想定されています。

- `passwordAddition` コレクションの`applicationRestrictions.passwordCredentials`制限
- `passwordAddition` コレクションの`servicePrincipalRestrictions.passwordCredentials`制限
- `symmetricKeyAddition` コレクションの`applicationRestrictions.passwordCredentials`制限
- `symmetricKeyAddition` コレクションの`servicePrincipalRestrictions.passwordCredentials`制限

これは、4 つのすべての制限のプロパティが一致する必要があることを意味します。 または、4 つの制限がすべてポリシーに存在しないようにする必要があります。

###### パスワードの有効期間

**[パスワードの最大有効期間の制限]** 制限では、次の 4 つの制限がすべて同じ状態であることが想定されています。

- `passwordLifetime` コレクションの`applicationRestrictions.passwordCredentials`制限
- `passwordLifetime` コレクションの`servicePrincipalRestrictions.passwordCredentials`制限
- `symmetricKeyLifetime` コレクションの`applicationRestrictions.passwordCredentials`制限
- `symmetricKeyLifetime` コレクションの`servicePrincipalRestrictions.passwordCredentials`制限

これは、4 つのすべての制限のプロパティが一致する必要があることを意味します。 または、4 つの制限がすべてポリシーに存在しないようにする必要があります。

###### 証明書の有効期間

**[証明書の最大有効期間の制限] 制限**では、次の両方の制限がすべて同じ状態であることが想定されます。

- `asymmetricKeyLifetime` コレクションの`applicationRestrictions.keyCredentials`制限
- `asymmetricKeyLifetime` コレクションの`servicePrincipalRestrictions.keyCredentials`制限

これは、両方の制限のプロパティが一致する必要があることを意味します。 または、両方の制限がポリシーに存在しないようにする必要があります。

---
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal"} -->
## サインインの自動高速化を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-authentication-for-federated-users-portal
- Service: entra-id / enterprise-apps
- Article date: 2024-11-29
- Summary: ホーム領域検出ポリシーを使用して、アプリケーションのフェデレーション IdP 自動高速化を強制する方法について説明します。

この記事では、ホーム領域検出 (HRD) ポリシーを使用してフェデレーション ユーザーのために Microsoft Entra 認証の動作を構成する方法についての概要を示します。 自動高速化サインインを使用して、ユーザー名入力画面をスキップし、自動的にフェデレーション サインイン エンドポイントにユーザーを転送する方法を取り上げます。 HRD ポリシーの詳細については、 [ホーム領域検出](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy) に関する記事を参照してください。

### 前提条件

Microsoft Entra ID でアプリケーションのために HRD ポリシーを構成するには、次が必要です。

- アクティブなサブスクリプションが含まれる Azure アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- アプリケーション管理者ロール

### 自動高速化サインイン

一部の組織では、Microsoft Entra テナント内のドメインを、ユーザー認証用の Active Directory フェデレーション サービス (ADFS) など、別の ID プロバイダー (IDP) とフェデレーションするように構成します。 ユーザーがアプリケーションにサインインすると、最初に Microsoft Entra のサインイン ページが表示されます。 ユーザー プリンシパル名 (UPN) を入力した後、フェデレーション ドメイン内にいると、そのドメインにサービスを提供している IDP のサインイン ページが表示されます。 状況によっては、管理者は特定のアプリケーションにサインインしたユーザーに対してサインイン ページを表示したい場合があります。 その結果、ユーザーは最初の Microsoft Entra ID ページをスキップできます。 このプロセスは、"サインイン自動高速化" と呼ばれます。

ショート メッセージ サービス (SMS) サインインや FIDO キーなど、クラウドが有効な資格情報を持つフェデレーション ユーザーの場合は、サインインの自動高速化を防ぐ必要があります。 HRD でドメイン ヒントを禁止する方法については、「 [自動高速化サインインを無効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/prevent-domain-hints-with-home-realm-discovery) 」を参照してください。

重要

2023 年 4 月以降、自動高速化またはスマートリンクを使用する組織では、サインイン UI に追加された新しい画面が表示される場合 があります。 [ドメインの確認] ダイアログと呼ばれるこの画面は、セキュリティ強化に対する Microsoft の一般的なコミットメントの一部であり、ユーザーはサインイン先のテナントのドメインを確認する必要があります。 [ドメインの確認] ダイアログが表示されたが、一覧にテナント ドメインが認識されない場合は、認証フローをキャンセルし、IT 管理者にお問い合わせください。

詳細については、「 [ドメインの確認ダイアログ」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/home-realm-discovery-policy#domain-confirmation-dialog)を参照してください。

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用して HRD ポリシーを設定する

Microsoft Graph PowerShell コマンドレットを使用して、次のようないくつかのシナリオについて説明します。

- 1 つのフェデレーション ドメインを持つテナント内でアプリケーションの自動高速化を実行するように HRD ポリシーを設定する。
- テナント用に確認された複数のドメインのいずれかに対してアプリケーションの自動高速化を実行するように HRD ポリシーを設定する。
- フェデレーション ユーザー用の Microsoft Entra ID に、レガシ アプリケーションがユーザー名とパスワードの認証を直接実行できるように HRD ポリシーを設定する。
- ポリシーが構成されているアプリケーションを一覧表示する。

以下の例では、Microsoft Entra ID 内のアプリケーション サービス プリンシパルを対象に、HRD ポリシーを作成、更新、リンク、および削除します。

1. 開始する前に、Connect コマンドを実行して、少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを使用して Microsoft Entra ID にサインインします。

    ```powershell
    connect-MgGraph -scopes "Policy.Read.All"
    ```
2. 次のコマンドを実行して、組織内のすべてのポリシーを表示します。

    ```powershell
    Get-MgPolicyHomeRealmDiscoveryPolicy -Property Id, displayName
    ```

何も返されない場合は、テナント内にポリシーが作成されていないことを意味します。

#### Microsoft Graph PowerShell を使用して HRD ポリシーを作成する

この例では、アプリケーションに割り当てたときに、次のいずれかを実行するポリシーを作成します。

- テナント内のドメインが 1 つのみのときに、ユーザーがアプリケーションにサインインするときに、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。
- テナント内にフェデレーション ドメインが複数ある場合に、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。
- ポリシーを割り当てた先のアプリケーションのフェデレーション ユーザーが Microsoft Entra ID に対して、ユーザー名とパスワードによる非対話型のサインインを直接行えるようにします。

[!注] ポリシーで `"AccelerateToFederatedDomain": true` を有効にすると、ポリシーがサービス プリンシパルに明示的に適用されていない場合でも、ゲスト ユーザーがサインインできなくなる可能性があります。 意図しないアクセスの問題を回避するには、ポリシーを適用する前に、この設定を慎重に確認してください。

次のポリシーは、テナント内のドメインが 1 つのみのときに、ユーザーがアプリケーションにサインインするときに、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。

1. Connect コマンドを実行して、少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールを持つ Microsoft Entra ID にサインインします。

    ```powershell
    connect-MgGraph -scopes "Policy.ReadWrite.ApplicationConfiguration"
    
    ```
2. 次のコマンドを実行して、新しい HRD ポリシーを作成します。

    ```powershell
    # Define the parameters for the policy 
    $params = @{
        definition = @(
        '{"HomeRealmDiscoveryPolicy":{
        "AccelerateToFederatedDomain":true,
        }
    }'
    )
    displayName = "BasicAutoAccelerationPolicy"
    isOrganizationDefault = $true
    } 
    # Create a new Home Realm Discovery Policy
    New-MgPolicyHomeRealmDiscoveryPolicy -BodyParameter $params 
    ```

次のポリシーは、テナント内にフェデレーション ドメインが複数あるときに、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。 アプリケーションのユーザーを認証するフェデレーション ドメインが複数ある場合は、自動高速化するドメインを指定する必要があります。

```powershell
connect-MgGraph -scopes "Policy.ReadWrite.ApplicationConfiguration"

# Define the parameters for the New-MgPolicyHomeRealmDiscoveryPolicy cmdlet
$params = @{definition = @('{"HomeRealmDiscoveryPolicy":{"AccelerateToFederatedDomain":true,"PreferredDomain":"federated.example.edu"}}'
)
displayName = "MultiDomainAutoAccelerationPolicy"
isOrganizationDefault = $true

}

# Create the new policy
New-MgPolicyHomeRealmDiscoveryPolicy -BodyParameter $params
```

次のポリシーは、特定のアプリケーションで Microsoft Entra ID に対し、ユーザー名とパスワードを使用してフェデレーション ユーザーが直接認証できるようにします。

```powershell

connect-MgGraph -scopes "Policy.ReadWrite.ApplicationConfiguration"

# Define the parameters for the New-MgPolicyHomeRealmDiscoveryPolicy cmdlet  
$params = @{definition = @('{"HomeRealmDiscoveryPolicy":{ "AllowCloudPasswordValidation":true
     }
   }'
)
displayName = "EnableDirectAuthPolicy"
}

New-MgPolicyHomeRealmDiscoveryPolicy -BodyParameter $params  
```

新しいポリシーを表示し、その **ObjectID を**取得するには、次のコマンドを実行します。

```powershell
    Get-MgPolicyHomeRealmDiscoveryPolicy -Property Id, displayName
```

HRD ポリシーを作成した後に適用するには、複数のサービス プリンシパルに割り当てることができます。

#### Microsoft Graph PowerShell を使用してポリシーを割り当てるサービス プリンシパルを見つける

ポリシーを割り当てるサービス プリンシパルの **ObjectID** が必要です。 サービス プリンシパルの **ObjectID** を検索するには、いくつかの方法があります。

Microsoft Entra 管理センターを使用できます。 このオプションの使用:

1. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
2. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。 アプリケーションのオブジェクト ID をコピーします。

Microsoft Graph PowerShell を使用しているため、次のコマンドレットを実行して、サービス プリンシパルとその ID を一覧表示します。

```powershell
connect-MgGraph -scopes "Application.Read.All"
Get-MgServicePrincipal
```

#### Microsoft Graph PowerShell を使用してサービス プリンシパルにポリシーを割り当てる

自動高速化を構成するアプリケーションのサービス プリンシパルの **ObjectID** を取得したら、次のコマンドを実行します。 このコマンドは、前のセクションで見たサービス プリンシパルに作成した HRD ポリシーを関連付けます。

```powershell
    connect-MgGraph -scopes "Policy.ReadWrite.ApplicationConfiguration", "Application.ReadWrite.All"

# Define the parameters for the New-MgServicePrincipalHomeRealmDiscoveryPolicy cmdlet  
$assignParams = @{"@odata.id" = "https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/<policyId>"
}

New-MgServicePrincipalHomeRealmDiscoveryPolicyByRef -ServicePrincipalId $servicePrincipalId -BodyParameter $assignParams
```

このコマンドは、ポリシーを追加する各サービス プリンシパルに対して繰り返し実行できます。

アプリケーションにホーム領域検出ポリシーが既に割り当てられている場合、2 つ目のポリシーを追加することはできません。 その場合は、アプリケーションに割り当てられている HRD ポリシーの定義を変更して、別のパラメーターを追加します。

#### Microsoft Graph PowerShell を使用して HRD ポリシーが割り当てられているサービス プリンシパルを確認する

次のコマンドを実行して、ポリシーが割り当てられているサービス プリンシパルを一覧表示します。

```powershell
Get-MgPolicyHomeRealmDiscoveryPolicyApplyTo -HomeRealmDiscoveryPolicyId "<ObjectId of the Policy>"
 # Replace with the actual ObjectId of the Policy 
```

アプリケーションのサインイン エクスペリエンスをテストして、新しいポリシーが動作していることを確認します。

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph を使用して HRD ポリシーを設定する

Microsoft Graph API 呼び出しを使用して、次のようないくつかのシナリオについて説明します。

- 1 つのフェデレーション ドメインを持つテナント内でアプリケーションの自動高速化を実行するように HRD ポリシーを設定する。
- テナント用に確認された複数のドメインのいずれかに対してアプリケーションの自動高速化を実行するように HRD ポリシーを設定する。
- フェデレーション ユーザー用の Microsoft Entra ID に、レガシ アプリケーションがユーザー名とパスワードの認証を直接実行できるように HRD ポリシーを設定する。
- ポリシーが構成されているアプリケーションを一覧表示する。

以下の例では、Microsoft Entra ID 内のアプリケーション サービス プリンシパルを対象に、HRD ポリシーを作成、更新、リンク、および削除します。

1. 開始する前に、Microsoft Graph エクスプローラー ウィンドウにアクセスします。
2. 少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールでサインインします。
3. `Policy.Read.All` アクセス許可への同意を付与します。
4. 次の API 呼び出しを実行して、組織内のすべてのポリシーを表示します。

    ```http
    GET https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies
    ```

何も返されない場合は、テナント内にポリシーが作成されていないことを意味します。

#### Microsoft Graph を使用して HRD ポリシーを作成する

この例では、アプリケーションに割り当てたときに、次のいずれかを実行するポリシーを作成します。

- テナント内のドメインが 1 つのみのときに、ユーザーがアプリケーションにサインインするときに、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。
- テナント内にフェデレーション ドメインが複数ある場合に、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。
- ポリシーを割り当てた先のアプリケーションのフェデレーション ユーザーが Microsoft Entra ID に対して、ユーザー名とパスワードによる非対話型のサインインを直接行えるようにします。

次のポリシーは、テナント内のドメインが 1 つのみのときに、ユーザーがアプリケーションにサインインするときに、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。

Microsoft Graph エクスプローラーに移動します。

1. 少なくとも [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator) ロールでサインインします。
2. `Policy.ReadWrite.ApplicationConfiguration` アクセス許可への同意を付与します。
3. 新しいポリシーを POST するか、既存のポリシーを更新する場合は PATCH します。

    ```http
    POST https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies  
    
    {  
        "definition": [  
            "{\"HomeRealmDiscoveryPolicy\":{\"AccelerateToFederatedDomain\":true}}"  
        ],  
        "displayName": "BasicAutoAccelerationPolicy",
        "isOrganizationDefault": true 
    } 
    ```

次のポリシーは、テナント内にフェデレーション ドメインが複数あるときに、フェデレーション ID プロバイダーのサインイン画面への移動を自動高速化します。 アプリケーションのユーザーを認証するフェデレーション ドメインが複数ある場合は、自動高速化するドメインを指定する必要があります。

```http
POST https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies  

{  
    "definition": [  
        "{\"HomeRealmDiscoveryPolicy\":{\"AccelerateToFederatedDomain\":true,\"PreferredDomain\":\"federated.example.edu\"}}"  
    ],  
    "displayName": "MultiDomainAutoAccelerationPolicy",
    "isOrganizationDefault": true 

}
```

次のポリシーは、特定のアプリケーションで Microsoft Entra ID に対し、ユーザー名とパスワードを使用してフェデレーション ユーザーが直接認証できるようにします。

```http
POST https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies  

{  
    "definition": [  
        "{\"HomeRealmDiscoveryPolicy\":{\"AllowCloudPasswordValidation\":true}}"  
    ],  
    "displayName": "EnableDirectAuthPolicy"  
}  
```

新しいポリシーを表示し、その **ObjectID を**取得するには、次の API 呼び出しを実行します。

```http
    GET https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies
```

HRD ポリシーを作成した後に適用するには、複数のサービス プリンシパルに割り当てることができます。

#### Microsoft Graph を使用してポリシーを割り当てるサービス プリンシパルを見つける

ポリシーを割り当てるサービス プリンシパルの **ObjectID** が必要です。 サービス プリンシパルの **ObjectID** を検索するには、いくつかの方法があります。

Microsoft Entra 管理センターを使用できます。 このオプションの使用:

1. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
2. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。 アプリケーションのオブジェクト ID をコピーします。

    Microsoft Graph エクスプローラーを使用しているため、次の要求を実行して、サービス プリンシパルとその ID を一覧表示します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals  
    ```

#### Microsoft Graph を使用してサービス プリンシパルにポリシーを割り当てる

自動高速化を構成するアプリケーションのサービス プリンシパルの **ObjectID** を取得したら、次の API cal を実行します。この API 呼び出しは、作成した HRD ポリシーを、前のセクションで見たサービス プリンシパルに関連付けます。

`Application.ReadWrite.All` アクセス許可に同意していることを確認します。

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/homeRealmDiscoveryPolicies/$ref  

{  
    "@odata.id": "https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/{policyId}"  
}  
```

ポリシーを追加するサービス プリンシパルごとに、この API 呼び出しを繰り返すことができます。

アプリケーションにホーム領域検出ポリシーが既に割り当てられている場合、2 つ目のポリシーを追加することはできません。 その場合は、アプリケーションに割り当てられている HRD ポリシーの定義を変更して、別のパラメーターを追加します。

#### Microsoft Graph を使用して HRD ポリシーが割り当てられているサービス プリンシパルを確認する

次の API 呼び出しを実行して、ポリシーが割り当てられているサービス プリンシパルを一覧表示します。

```http
GET https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/{policyId}/appliesTo  
```

アプリケーションのサインイン エクスペリエンスをテストして、新しいポリシーが動作していることを確認します。

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してアプリケーションから HRD ポリシーを削除する

1. ポリシーの ObjectID を取得します。

    前の例を使用して、ポリシーの ObjectID と、それを削除するアプリケーション サービス プリンシパルの **ObjectID** を取得します。
2. アプリケーション サービス プリンシパルからポリシー割り当てを削除します。

    ```powershell
    Remove-MgServicePrincipalHomeRealmDiscoveryPolicyHomeRealmDiscoveryPolicyByRef -ServicePrincipalId $servicePrincipalId -HomeRealmDiscoveryPolicyId $homeRealmDiscoveryPolicyId
    ```
3. ポリシーが割り当てられているサービス プリンシパルを一覧表示して、削除を確認します。

    ```powershell
    Get-MgPolicyHomeRealmDiscoveryPolicyApplyTo -HomeRealmDiscoveryPolicyId "<ObjectId of the Policy>"
    # Replace with the actual ObjectId of the Policy 
    ```

#### Microsoft Graph PowerShell を使用して HRD ポリシーを削除する

作成した HRD ポリシーを削除するには、次のコマンドを実行します。

```powershell
    Remove-MgPolicyHomeRealmDiscoveryPolicy -HomeRealmDiscoveryPolicyId "<ObjectId of the Policy>" # Replace with the actual ObjectId of the Policy
```

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph を使用してアプリケーションから HRD ポリシーを削除する

1. ポリシーの ObjectID を取得します。

    前の例を使用して、ポリシーの ObjectID と、それを削除するアプリケーション サービス プリンシパルの **ObjectID** を取得します。
2. アプリケーション サービス プリンシパルからポリシー割り当てを削除します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipalId}/homeRealmDiscoveryPolicies/{policyId}/$ref
    ```
3. ポリシーが割り当てられているサービス プリンシパルを一覧表示して、削除を確認します。

    ```http
    GET https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/<policyId>/appliesTo  
    ```

#### Microsoft Graph を使用して HRD ポリシーを削除する

作成した HRD ポリシーを削除するには、次の API 呼び出しを実行します。

```http
DELETE https://graph.microsoft.com/v1.0/policies/homeRealmDiscoveryPolicies/{id}
```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-linked-sign-on"} -->
## リンクされたシングル サインオンをアプリケーションに追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-linked-sign-on
- Service: entra-id / enterprise-apps
- Article date: 2025-06-20
- Summary: Microsoft Entra ID でリンクされたシングル サインオンをアプリケーションに追加します。

この記事では、Microsoft Entra ID でアプリケーションに対するリンクベースのシングル サインオン (SSO) を構成する方法について説明します。 Microsoft Entra ID では、リンクベースの SSO を使用して、既に別のサービスの SSO が構成されているアプリケーションに SSO を提供できます。 [リンク] オプションを使用すると、ユーザーが組織のマイ アプリ、または Microsoft 365 ポータルでアプリケーションを選択するときにターゲットとなる場所を構成できます。

"別のサービス" という用語は、アプリケーションの SSO が既に構成されている外部 ID プロバイダーまたはサービスを指します。 Microsoft Entra ID はファシリテーターとして機能し、サインオン プロセス自体を管理せずにユーザーをアプリケーションにリンクします。

リンクベースの SSO では、Microsoft Entra ID を使用したサインオン機能は提供されません。 このオプションでは、マイ アプリ、または Microsoft 365 ポータルでアプリケーションを選択したときにユーザーが送信される場所が設定されるだけです。

リンクベースの SSO が役立つ一般的なシナリオには、次のようなものがあります。

- Active Directory フェデレーション サービス (ADFS) など、現在フェデレーションを使用しているカスタム Web アプリケーションにリンクを追加する。
- ユーザーのアクセス ページに表示するだけの特定の Web ページにディープ リンクを追加する。
- 認証を必要としないアプリケーションにリンクを追加する。 リンクされたオプションでは、Microsoft Entra 資格情報によるサインオン機能は提供されませんが、エンタープライズ アプリケーションの他の機能の一部は引き続き使用できます。 たとえば、監査ログを使用したり、カスタム ロゴとアプリケーション名を追加したりできます。

### 前提条件

Microsoft Entra テナントでリンクベース SSO を構成するには、次のものが必要です。

- Microsoft Entra ユーザー アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者、またはサービス プリンシパルの所有者。
- リンクベースの SSO をサポートするアプリケーション。

### リンクベースのシングル サインオンを構成する

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. リンクされた SSO を追加するアプリケーションを検索して選択します。
4. [ **シングル サインオン** ] を選択し、[ **リンク]** を選択します。
5. アプリケーションのサインイン ページの URL を入力します。
6. **[保存] を選択します**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications"} -->
## パスワードベースのシングル サインオンをアプリケーションに追加する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-password-single-sign-on-non-gallery-applications
- Service: entra-id / enterprise-apps
- Article date: 2025-06-20
- Summary: Microsoft Entra ID でアプリケーションにパスワードベースのシングル サインオンを追加します。

この記事では、Microsoft Entra ID でパスワードベースのシングル サインオン (SSO) を設定する方法について説明します。 パスワードベースの SSO では、ユーザーは、アプリケーションに初めてサインインするときに、ユーザー名とパスワードを使用してサインインします。 最初のサインイン後は、Microsoft Entra ID によりユーザー名とパスワードがアプリケーションに送信されます。

パスワード ベースの SSO では、アプリケーションによって提供される既存の認証プロセスが使用されます。 アプリケーションでパスワードベースの SSO を有効にすると、Microsoft Entra ID がそのアプリケーション用のユーザー名とパスワードを収集し、安全に保存します。 ユーザーの資格情報は、暗号化された状態でディレクトリ内に保存されます。 パスワードベースの SSO は、HTML ベースのサインイン ページを持つどのクラウド ベース アプリケーションでもサポートされます。

次の場合にパスワードベースの SSO を選択します。

- アプリケーションで、Security Assertion Markup Language (SAML) SSO プロトコルがサポートされていない。
- アプリケーションは、アクセス トークンとヘッダーではなく、ユーザー名とパスワードを使用して認証する。

パスワード ベースの SSO の構成ページは単純です。 アプリケーションで使用されているサインオン ページの URL のみが表示されます。 この文字列は、ユーザー名入力フィールドを含んだページである必要があります。

### 前提条件

Azure AD テナントでパスワードベース SSO を構成するには、次のものが必要です:

- アクティブなサブスクリプションが含まれる Azure アカウント。 アカウントをまだお持ちでない場合は、[無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます
- アプリケーション管理者、クラウド アプリケーション管理者、アプリケーション管理者、サービス プリンシパルの所有者。
- パスワードベースの SSO をサポートするアプリケーション。

### パスワードベースのシングル サインオンの構成

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。
4. [ **シングル サインオン** ] を選択し、[ **パスワードベース**] を選択します。
5. アプリケーションのサインイン ページの URL を入力します。
6. **[保存] を選択します**。

Microsoft Entra ID は、サインイン ページのユーザー名とパスワードの入力フィールドの HTML を解析します。 試行が成功すると、サインインしたことになります。 次の手順では、 [アプリケーションにユーザーまたはグループを割り当てます](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-assign-users) 。

ユーザーとグループを割り当てた後、ユーザーがアプリケーションにサインインするときに使用する資格情報を提供できます。

1. [ **ユーザーとグループ**] を選択し、ユーザーまたはグループの行のチェック ボックスをオンにして、[ **資格情報の更新**] を選択します。
2. そのユーザーまたはグループのために使用するユーザー名とパスワードを入力します。 そうしない場合、ユーザーは起動時に自分で資格情報を入力するように求められます。

### 手動構成

Microsoft Entra ID による解析試行が失敗した場合は、手動でサインオンを構成できます。

1. **[{アプリケーション名} パスワードのシングル サインオン設定の構成]** を選択して、[**サインオンの構成**] ページを表示します。
2. [ **サインイン フィールドを手動で検出する]** を選択します。 サインイン フィールドの手動検出について説明したその他の手順が表示されます。
3. [ **サインイン フィールドのキャプチャ**] を選択します。 キャプチャ ステータス ページが新しいタブで開き、「メタデータ キャプチャは現在進行中です」というメッセージが示されます。
4. [ **マイ アプリ拡張機能が必要]** ボックスが新しいタブに表示された場合は、[ **今すぐインストール** ] を選択して、[マイ アプリのセキュリティで保護されたサインイン拡張機能] ブラウザー拡張機能をインストールします。 (ブラウザー拡張機能には、Microsoft Edge か Chrome が必要です)。続いて拡張機能をインストールし、起動し、有効にして、キャプチャ ステータス ページを更新します。 ブラウザー拡張機能は次に、入力した URL を表示する別のタブを開きます。
5. 入力した URL のタブで、サインイン プロセスを実行します。 ユーザー名とパスワードのフィールドを入力し、サインインを試みます。 (正しいパスワードを指定する必要はありません)。キャプチャされたサインイン フィールドを保存するように求めるメッセージが表示されます。
6. [ **OK] を選択します**。 ブラウザー拡張機能は、 **アプリケーションのメタデータが更新された**というメッセージでキャプチャ状態ページを更新します。 ブラウザー タブが閉じます。
7. Microsoft Entra ID の [サインオンの構成] ページで、[ **OK] を選択します。アプリに正常にサインインできました**。
8. [ **OK] を選択します**。

### 制限事項

パスワードベースの SSO の場合、エンド ユーザーのブラウザーには次のいずれかを使用できます。

- Internet Explorer 8、9、10、11 -- Windows 7 以降 (制限付きサポート)
- Microsoft Edge - Windows 10 Anniversary Edition 以降
- Chrome -- Windows 7 以降および macOS X 以降

ユーザーは、パスワードベースのシングル サインオンを使用するアプリケーション用に構成された資格情報を最大 [48](https://learn.microsoft.com/ja-jp/entra/identity/users/directory-service-limits-restrictions) 個まで使用できます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-permission-classifications"} -->
## アクセス許可の分類を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-permission-classifications
- Service: entra-id / enterprise-apps
- Article date: 2025-03-03
- Summary: 委任されたアクセス許可の分類を管理する方法について説明します。

この記事では、Microsoft Entra ID でアクセス許可の分類を構成する方法について説明します。 アクセス許可の分類を使用すると、組織のポリシーとリスク評価に基づいて、さまざまなアクセス許可の影響を特定できます。 たとえば、同意ポリシーでアクセス許可の分類を使用して、ユーザーが同意を許可された一連のアクセス許可を識別できます。

"低"、"中" (プレビュー)、"高" (プレビュー) の 3 つのアクセス許可の分類がサポートされています。 現時点では、管理者の同意を必要としない委任されたアクセス許可のみを分類できます。

基本的なサインインに必要な最小限のアクセス許可は `openid`、`profile`、`email`、`offline_access` です。これらはすべて、Microsoft Graph で委任されたアクセス許可です。 これらのアクセス許可を使用すると、アプリはサインインしているユーザーのプロファイルの詳細を読み取ることができ、ユーザーがアプリを使用しなくなった場合でもこのアクセスを維持できます。

### 前提条件

アクセス許可の分類を構成するには、次が必要です。

- アクティブなサブスクリプションが含まれる Azure アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- 次のいずれかのロール: アプリケーション管理者、クラウド アプリケーション管理者

### アクセス許可の分類を管理する

::: zone pivot="portal"

Microsoft Entra 管理センターを使用してアクセス許可を分類するには、次の手順に従います。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**&gt;**同意と許可**&gt;**許可の分類**に移動します。
3. 更新するアクセス許可の分類のタブを選択します。
4. [ **アクセス許可の追加]** を選択して、別のアクセス許可を分類します。
5. API を選択し、委任されたアクセス許可を 1 つ以上選択します。

この例では、シングル サインオンに必要な最小限のアクセス許可セットを分類します。

[Image: アクセス許可の分類]

::: zone-end

::: zone pivot="entra-powershell"

最新の [Microsoft Entra PowerShell](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/?preserve-view=true&view=entra-powershell) を使用してアクセス許可を分類できます。 アクセス許可の分類は、アクセス許可を発行する API の **ServicePrincipal** オブジェクトに対して構成されます。

次のコマンドを実行して、Microsoft Entra PowerShell に接続します。 必要なスコープに同意するには、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。

```powershell
Connect-Entra -scopes "Policy.ReadWrite.PermissionGrant", "Application.Read.All"
```

#### Microsoft Entra PowerShell を使用して現在のアクセス許可の分類を一覧表示する

1. API の **ServicePrincipal** オブジェクトを取得します。 ここでは、Microsoft Graph API の ServicePrincipal オブジェクトを取得します。

    ```powershell
    $serviceprincipal = Get-EntraServicePrincipal `
        -Filter "servicePrincipalNames/any(n:n eq 'https://graph.microsoft.com')"
    ```
2. API の委任されたアクセス許可の分類を読み取ります。

    ```powershell
    Get-EntraServicePrincipalDelegatedPermissionClassification `
        -ServicePrincipalId $serviceprincipal.ObjectId | Format-Table Id, PermissionName, Classification
    ```

#### Microsoft Entra PowerShell を使用してアクセス許可を "低影響" として分類する

1. API の **ServicePrincipal** オブジェクトを取得します。 ここでは、Microsoft Graph API の ServicePrincipal オブジェクトを取得します。

    ```powershell
    $serviceprincipal = Get-EntraServicePrincipal `
        -Filter "servicePrincipalNames/any(n:n eq 'https://graph.microsoft.com')"
    ```
2. 分類対象となる委任されたアクセス許可を検索します。

    ```powershell
    $delegatedPermission = $serviceprincipal.oauth2PermissionScopes | Where-Object { $_.Value -eq "User.ReadBasic.All" }
    ```
3. アクセス許可の名前と ID を使用して、アクセス許可の分類を設定します。

    ```powershell
    Add-EntraServicePrincipalDelegatedPermissionClassification `
       -ServicePrincipalId $serviceprincipal.ObjectId `
       -PermissionId $delegatedPermission.Id `
       -PermissionName $delegatedPermission.Value `
       -Classification "low"
    ```

#### Microsoft Entra PowerShell を使用して委任されたアクセス許可の分類を削除する

1. API の **ServicePrincipal** オブジェクトを取得します。 ここでは、Microsoft Graph API の ServicePrincipal オブジェクトを取得します。

    ```powershell
    $serviceprincipal = Get-EntraServicePrincipal `
        -Filter "servicePrincipalNames/any(n:n eq 'https://graph.microsoft.com')"
    ```
2. 削除対象となる委任されたアクセス許可の分類を検索します。

    ```powershell
    $classifications = Get-EntraServicePrincipalDelegatedPermissionClassification `
        -ServicePrincipalId $serviceprincipal.ObjectId
    $classificationToRemove = $classifications | Where-Object {$_.PermissionName -eq "User.ReadBasic.All"}
    ```
3. アクセス許可の分類を削除します。

    ```powershell
    Remove-EntraServicePrincipalDelegatedPermissionClassification `
        -ServicePrincipalId $serviceprincipal.ObjectId `
        -Id $classificationToRemove.Id
    ```

::: zone-end

::: zone pivot="ms-powershell"

[Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started?preserve-view=true&view=graph-powershell-1.0) を使用して、アクセス許可を分類できます。 アクセス許可の分類は、アクセス許可を発行する API の **ServicePrincipal** オブジェクトに対して構成されます。

次のコマンドを実行して、Microsoft Graph PowerShell に接続します。 必要なスコープに同意するには、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。

```powershell
Connect-MgGraph -Scopes "Policy.ReadWrite.PermissionGrant", "Application.Read.All"
```

#### Azure AD PowerShell を使用して API の現在のアクセス許可の分類を一覧表示する

1. API の servicePrincipal オブジェクトを取得します。

    ```powershell
    $serviceprincipal = Get-MgServicePrincipal -Filter "displayName eq 'Microsoft Graph'" 
    ```
2. API の委任されたアクセス許可の分類を読み取ります。

    ```powershell
    Get-MgServicePrincipalDelegatedPermissionClassification -ServicePrincipalId $serviceprincipal.Id 
    ```

#### Microsoft Graph PowerShell を使用してアクセス許可を "低影響" として分類する

1. API の servicePrincipal オブジェクトを取得します。

    ```powershell
    $serviceprincipal = Get-MgServicePrincipal -Filter "displayName eq 'Microsoft Graph'" 
    ```
2. 分類対象となる委任されたアクセス許可を検索します。

    ```powershell
    $delegatedPermission = $serviceprincipal.Oauth2PermissionScopes | Where-Object {$_.Value -eq "openid"} 
    ```
3. アクセス許可の分類を設定します。

    ```powershell
    $params = @{ 
       PermissionId = $delegatedPermission.Id 
       PermissionName = $delegatedPermission.Value 
       Classification = "Low"
    } 
    
    New-MgServicePrincipalDelegatedPermissionClassification -ServicePrincipalId $serviceprincipal.Id -BodyParameter $params 
    ```

#### Microsoft Graph PowerShell を使用して委任されたアクセス許可の分類を削除する

1. API の servicePrincipal オブジェクトを取得します。

    ```powershell
    $serviceprincipal = Get-MgServicePrincipal -Filter "displayName eq 'Microsoft Graph'" 
    ```
2. 削除対象となる委任されたアクセス許可の分類を検索します。

    ```powershell
    $classifications = Get-MgServicePrincipalDelegatedPermissionClassification -ServicePrincipalId $serviceprincipal.Id 
    
    $classificationToRemove = $classifications | Where-Object {$_.PermissionName -eq "openid"}
    ```
3. アクセス許可の分類を削除します。

```powershell
Remove-MgServicePrincipalDelegatedPermissionClassification -DelegatedPermissionClassificationId $classificationToRemove.Id   -ServicePrincipalId $serviceprincipal.id 
```

::: zone-end

::: zone pivot="ms-graph"

エンタープライズ アプリケーションのアクセス許可の分類を構成するには、少なくとも[クラウド アプリケーション管理者](https://developer.microsoft.com/graph/graph-explorer)として [Graph Explorer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) にサインインします。

`Policy.ReadWrite.PermissionGrant` と `Application.Read.All` のアクセス許可に同意する必要があります。

Microsoft Graph エクスプローラーで次のクエリを実行して、アプリケーションの委任されたアクセス許可の分類を追加します。

#### Microsoft Graph API を使用して API の現在のアクセス許可の分類を一覧表示する

次の Microsoft Graph API 呼び出しを使用して、API の現在のアクセス許可の分類を一覧表示します。

```http
GET https://graph.microsoft.com/v1.0/servicePrincipals(appId='00000003-0000-0000-c000-000000000000')/delegatedPermissionClassifications
```

#### Microsoft Graph API を使用してアクセス許可を "低影響" として分類する

次の例では、アクセス許可を "低影響" として分類します。

次の Microsoft Graph API 呼び出しを使用して、API の委任されたアクセス許可の分類を追加します。

```http
POST https://graph.microsoft.com/v1.0/servicePrincipals(appId='00000003-0000-0000-c000-000000000000')/delegatedPermissionClassifications
Content-type: application/json

{
   "permissionId": "b4e74841-8e56-480b-be8b-910348b18b4c",
   "classification": "low"
}
```

#### Microsoft Graph API を使用して委任されたアクセス許可の分類を削除する

Microsoft Graph エクスプローラーで次のクエリを実行して、API の委任されたアクセス許可の分類を削除します。

```http
DELETE https://graph.microsoft.com/v1.0/servicePrincipals(appId='00000003-0000-0000-c000-000000000000')/delegatedPermissionClassifications/QUjntFaOC0i-i5EDSLGLTAE
```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-risk-based-step-up-consent"} -->
## リスクに基づくステップアップ同意を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-risk-based-step-up-consent
- Service: entra-id / enterprise-apps
- Article date: 2025-05-21
- Summary: リスクに基づくステップアップ同意を無効または有効にして、違法な同意要求を行う悪質なアプリへのユーザーの露出を減らす方法について説明します。

この記事では、Microsoft Entra ID でリスクベースのステップアップ同意を構成する方法について説明します。 リスクに基づくステップアップ同意を使用すると、[違法な同意要求](https://learn.microsoft.com/ja-jp/microsoft-365/security/office-365-security/detect-and-remediate-illicit-consent-grants)を行う悪質なアプリへのユーザーの露出を減らすことができます。

たとえば、 [発行元が検証](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview) されておらず、非基本アクセス許可を必要とする、新しく登録されたマルチテナント アプリに対する同意要求は危険と見なされます。 危険なユーザーの同意要求が検出された場合、代わりに管理者の同意を求める "ステップアップ" が必要になります。 このステップアップ機能は既定で有効ですが、ユーザーの同意が有効化されている場合にのみ、動作変更につながります。

危険な同意要求が検出されると、管理者の承認が必要であることを示すメッセージが同意プロンプトに表示されます。 [管理者の同意要求ワークフロー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)が有効になっている場合、ユーザーは同意プロンプトから直接その要求を管理者に送信してさらなる確認を求めることができます。 管理者の同意要求のワークフローが有効になっていない場合は、次のメッセージが表示されます。

**AADSTS90094:**: &lt;clientAppDisplayName&gt; には、組織内のリソースへのアクセス許可が必要です。これを付与できるのは管理者のみです。 使用するには、まず、このアプリへのアクセス許可を付与するように管理者に依頼してください。

この場合、"ApplicationManagement" というカテゴリ、"Consent to application" (アプリケーションへの同意) というアクティビティの種類、"Risky application detected" (危険なアプリケーションの検出) という状態の理由で、監査イベントもログに記録されます。

### 前提条件

リスクに基づくステップアップ同意を構成するには、以下のものが必要です。

- ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)。

### リスクに基づくステップアップ同意を無効にする、または再度有効にする

リスクが検出されたときに管理者のステップアップを無効または有効にするには、 [Microsoft Graph PowerShell ベータ モジュール](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/installation) を使用します。

重要

Microsoft Graph PowerShell ベータ コマンドレット モジュールを使用していることを確認します。

1. 次のコマンドを実行します。

    ```powershell
    Install-Module Microsoft.Graph.Beta
    ```
2. Microsoft Graph PowerShell に接続します。

    ```powershell
    Connect-MgGraph -Scopes "Directory.ReadWrite.All"
    ```
3. テナントの**同意ポリシー設定**ディレクトリ設定の現在の値を取得します。 そのためには、この機能のディレクトリ設定が作成されているかどうかを確認する必要があります。 作成されていない場合は、対応するディレクトリ設定テンプレートの値を使用します。

    ```powershell
    $consentSettingsTemplateId = "dffd5d46-495d-40a9-8e21-954ff55e198a" # Consent Policy Settings
    $settings = Get-MgBetaDirectorySetting -All | Where-Object { $_.TemplateId -eq $consentSettingsTemplateId }
    if (-not $settings) {
        $params = @{
            TemplateId = $consentSettingsTemplateId
            Values = @(
                @{ 
                    Name = "BlockUserConsentForRiskyApps"
                    Value = "True"
                }
                @{ 
                    Name = "ConstrainGroupSpecificConsentToMembersOfGroupId"
                    Value = "<groupId>"
                }
                @{ 
                    Name = "EnableAdminConsentRequests"
                    Value = "True"
                }
                @{ 
                    Name = "EnableGroupSpecificConsent"
                    Value = "True"
                }
            )
        }
        $settings = New-MgBetaDirectorySetting -BodyParameter $params
    }
    $riskBasedConsentEnabledValue = $settings.Values | ? { $_.Name -eq "BlockUserConsentForRiskyApps" }
    ```
4. 値を確認します。

    ```powershell
    $riskBasedConsentEnabledValue
    ```

    設定の値を理解します。

    | 設定 | タイプ | 説明 |
    | --- | --- | --- |
    | リスキーなアプリに対するユーザー同意をブロックする | ブール値 | 危険な要求が検出されたときにユーザーの同意がブロックされるかどうかを示すフラグ。 |
5. `BlockUserConsentForRiskyApps` の値を変更するには、[Update-MgBetaDirectorySetting](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.beta.identity.directorymanagement/update-mgbetadirectorysetting) コマンドレットを使用します。

    ```powershell
    $params = @{
        TemplateId = $consentSettingsTemplateId
        Values = @(
            @{ 
                Name = "BlockUserConsentForRiskyApps"
                Value = "False"
            }
            @{ 
                Name = "ConstrainGroupSpecificConsentToMembersOfGroupId"
                Value = "<groupId>"
            }
            @{ 
                Name = "EnableAdminConsentRequests"
                Value = "True"
            }
            @{ 
                Name = "EnableGroupSpecificConsent"
                Value = "True"
            }
        )
    }
    Update-MgBetaDirectorySetting -DirectorySettingId $settings.Id -BodyParameter $params
    ```
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/configure-user-consent"} -->
## ユーザーがアプリケーションに同意する方法を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-user-consent
- Service: entra-id / enterprise-apps
- Article date: 2025-06-15
- Summary: Microsoft Entra ID でユーザーの同意設定を構成して、ユーザーが組織のデータにアクセス許可を付与するタイミングと方法を制御します。 詳細なガイダンスを使用して、環境をセキュリティで保護します。

この記事では、Microsoft Entra ID でユーザーの同意設定を構成して、ユーザーがアプリケーションにアクセス許可を付与するタイミングと方法を制御する方法について説明します。 このガイダンスは、IT 管理者がユーザーの同意を制限または無効にすることで、セキュリティ リスクを軽減するのに役立ちます。

アプリケーションが組織のデータにアクセスできるようにするには、そのためのアプリケーションのアクセス許可をユーザーに付与する必要があります。 異なるアクセス許可によって、さまざまなレベルのアクセスが可能になります。 既定では、すべてのユーザーが、管理者の同意を必要としないアクセス許可のアプリケーションに同意することが許可されています。 たとえば、既定では、ユーザーはアプリが自分のメールボックスにアクセスすることを許可することができますが、組織内のすべてのファイルを自由に読み取りと書き込みできるアクセスをアプリに許可することに同意することはできません。

悪意のあるアプリケーションがユーザーをだまして組織のデータへのアクセスを許可しようとするリスクを軽減するには、 [検証済みの発行元](https://learn.microsoft.com/ja-jp/entra/identity-platform/publisher-verification-overview)によって発行されたアプリケーションに対してのみユーザーの同意を許可することをお勧めします。

メモ

ユーザーをアプリケーションに割り当てる必要があるアプリケーションでは、ディレクトリのユーザー同意ポリシーでユーザーが自分の代わりに同意できる場合でも、管理者がアクセス許可を付与する必要があります。

### 前提条件

ユーザーの同意を構成するには、次のものが必要です。

- ユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- A [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)ロール。
- [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)ロールは、Microsoft Entra 管理センターを使用する場合にのみ必要です。

### ユーザーの同意設定を構成する

Microsoft Entra 管理センター、Microsoft Graph PowerShell、または Microsoft Graph API を使用して、Microsoft Entra ID でユーザーの同意設定を構成できます。 構成する設定は、組織内のすべてのユーザーに適用されます。

::: zone pivot="portal"

#### Microsoft Entra 管理センターでユーザーの同意を構成する

Microsoft Entra 管理センターを使用してユーザーの同意設定を構成するには:

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に[グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)としてサインインします。
2. **Identity**&gt;**Applications**&gt;**エンタープライズ アプリケーション**&gt;**同意とアクセス許可**&gt;**ユーザーの同意設定**に移動します。
3. [ **アプリケーションのユーザーの同意**] で、すべてのユーザーに対して構成する同意設定を選択します。
4. [ **保存] を** 選択して設定を保存します。

    [Image: [ユーザーの同意設定] ウィンドウのスクリーンショット。]

::: zone-end

::: zone pivot="ms-powershell"

#### Microsoft Graph PowerShell での承認とアクセス許可の付与ポリシーについて

Microsoft Graph PowerShell を使用してプログラムでユーザーの同意設定を構成するには、テナント全体の **承認ポリシー** と個々の **アクセス許可付与ポリシー**の違いを理解することが重要です。 `authorizationPolicy` を使用して取得されたは、ユーザーがアプリに同意できるかどうか、既定のユーザー ロールに割り当てられるアクセス許可付与ポリシーなど、グローバル設定を管理します。 たとえば、`ManagePermissionGrantsForOwnedResource.DeveloperConsent` コレクション内の`permissionGrantPoliciesAssigned`のみを割り当てることで、開発者が自分が所有するアプリのアクセス許可を管理できるようにしながら、ユーザーの同意を無効にすることができます。

一方、 [permissionGrantPolicies](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.identity.signins/get-mgpolicypermissiongrantpolicy) エンドポイントには、現在のアクセス許可付与ポリシーが一覧表示されます。 これらのポリシーは、アプリケーションに付与できるアクセス許可と、どのような状況で付与できるかを決定します。 各ポリシーには特定の条件が含まれますが、他の条件は "除外" されます。 ユーザーがアプリケーションに同意しようとすると、システムはアクセス許可付与ポリシーをチェックして、ユーザーの要求に該当するかどうかを確認します。 たとえば、リスクの低いポリシーを使用すると、ユーザーは "低リスク" として構成されたアクセス許可に同意できます。 これには、(GUID として) これらのリスクの低いポリシーが含まれています。 別のシナリオでは、ユーザーが "AdminOnly" ポリシーに一致するコンテキストで同意しようとすると、ユーザーは同意できません。

メモ

`Update-MgPolicyPermissionGrantPolicy` コマンドを使用して同意設定を更新する前に、常に現在の`authorizationPolicy`を取得して、既に割り当てられているアクセス許可付与ポリシーを特定します。 これにより、開発者が所有するアプリの同意を管理できるようにするなど、必要なアクセス許可を確実に保持し、既存の機能を意図せずに削除しないようにすることができます。

アプリケーションのユーザーの同意を管理するアプリの同意ポリシーを選択するには、 [Microsoft Graph PowerShell](https://learn.microsoft.com/ja-jp/powershell/microsoftgraph/get-started?view=graph-powershell-1.0&preserve-view=true) モジュールを使用します。 ここで使用するコマンドレットは、 [Microsoft.Graph.Identity.SignIns](https://www.powershellgallery.com/packages/Microsoft.Graph.Identity.SignIns) モジュールに含まれています。

必要な最小限の特権を使用して、Microsoft Graph PowerShell に接続します。 現在のユーザーの同意設定を読み取る場合は、 *Policy.Read.All を使用します*。 ユーザーの同意設定を読み取って変更するには、 *Policy.ReadWrite.Authorization* を使用します。 [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインする必要があります。

```powershell
Connect-MgGraph -Scopes "Policy.ReadWrite.Authorization"
```

#### Microsoft Graph PowerShell を使用してユーザーの同意を無効にする

ユーザーの同意を無効にするには、コレクションの更新中に同意ポリシー (`PermissionGrantPoliciesAssigned`) に他の現在の `ManagePermissionGrantsForOwnedResource.*` ポリシー (存在する場合) が含まれることを確認します。 これにより、ユーザーの同意設定と他のリソースの同意設定の現在の構成を維持できます。

```powershell
# only exclude user consent policy
$body = @{
    "permissionGrantPolicyIdsAssignedToDefaultUserRole" = @(
        "managePermissionGrantsForOwnedResource.{other-current-policies}" 
    )
}
Update-MgPolicyAuthorizationPolicy -BodyParameter $body

```

#### PowerShell を使ってアプリの同意ポリシーに従ってユーザーの同意を許可する

ユーザーの同意を許可するには、アプリに同意を付与するユーザーの認可を管理するアプリの同意ポリシーを選択します。 コレクションの更新中に同意ポリシー (`PermissionGrantPoliciesAssigned`) に他の現在の `ManagePermissionGrantsForOwnedResource.*` ポリシー (存在する場合) が含まれることを確認します。 これにより、ユーザーの同意設定と他のリソースの同意設定の現在の構成を維持できます。

```powershell
$body = @{
    "permissionGrantPolicyIdsAssignedToDefaultUserRole" = @(
        "managePermissionGrantsForSelf.{consent-policy-id}",
        "managePermissionGrantsForOwnedResource.{other-current-policies}"
    )
}
Update-MgPolicyAuthorizationPolicy -BodyParameter $body
```

`{consent-policy-id}` を、適用するポリシーの ID に置き換えます。 作成した [カスタム アプリの同意ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-app-consent-policies#create-a-custom-app-consent-policy-using-powershell) を選択することも、次の組み込みポリシーから選択することもできます。

| ID | 説明 |
| --- | --- |
| microsoft-user-default-low | **選択したアクセス許可に対して、検証済みの発行元からのアプリに対するユーザーの同意を許可する** 制限付きユーザーの同意は、検証済みの発行元のアプリとテナントに登録されているアプリに対してのみ許可し、 *影響の少ない*アクセス許可として分類する場合にのみ許可します。 (ユーザーが同意できる [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-permission-classifications) を選択するには、必ずアクセス許可を分類してください)。 |
| マイクロソフト・ユーザー・デフォルト・レガシー | **アプリのユーザーの同意を許可する** このオプションを使用すると、すべてのユーザーが、すべてのアプリケーションに対し、管理者の同意を必要としないすべてのアクセス許可に同意することができます |

たとえば、組み込みのポリシー `microsoft-user-default-low` に従ってユーザーの同意を有効にするには、次のコマンドを実行します。

```powershell
$body = @{
    "permissionGrantPolicyIdsAssignedToDefaultUserRole" = @(
        "managePermissionGrantsForSelf.managePermissionGrantsForSelf.microsoft-user-default-low",
        "managePermissionGrantsForOwnedResource.{other-current-policies}"
    )
}
```

::: zone-end

::: zone pivot="ms-graph"

#### Microsoft Graph の承認ポリシーとアクセス許可付与ポリシーについて

Microsoft Graph を使用してプログラムでユーザーの同意設定を構成するには、テナント全体の **承認ポリシー** と個々の **アクセス許可付与ポリシー**の違いを理解することが重要です。 `authorizationPolicy` (`GET https://graph.microsoft.com/v1.0/policies/authorizationPolicy/authorizationPolicy` を使用して取得) は、ユーザーがアプリに同意できるかどうか、既定のユーザー ロールに割り当てられるアクセス許可付与ポリシーなど、グローバル設定を制御します。 たとえば、`ManagePermissionGrantsForOwnedResource.DeveloperConsent` コレクション内の`permissionGrantPoliciesAssigned`のみを割り当てることで、開発者が自分が所有するアプリのアクセス許可を管理できるようにしながら、ユーザーの同意を無効にすることができます。

一方、 `permissionGrantPolicies` エンドポイント (`GET https://graph.microsoft.com/v1.0/policies/permissionGrantPolicies`) には、現在のアクセス許可付与ポリシーが一覧表示されます。 これらのポリシーは、アプリケーションに付与できるアクセス許可と、どのような状況で付与できるかを決定します。 各ポリシーには特定の条件が含まれますが、他の条件は "除外" されます。 ユーザーがアプリケーションに同意しようとすると、システムはアクセス許可付与ポリシーをチェックして、ユーザーの要求に該当するかどうかを確認します。 たとえば、リスクの低いポリシーを使用すると、ユーザーは "低リスク" として構成されたアクセス許可に同意できます。 これには、(GUID として) これらのリスクの低いポリシーが含まれています。 別のシナリオでは、ユーザーが "AdminOnly" ポリシーに一致するコンテキストで同意しようとすると、ユーザーは同意できません。

メモ

同意設定を `PATCH` 要求で更新する前に、常に現在の `authorizationPolicy` を取得して、既に割り当てられているアクセス許可付与ポリシーを特定します。 これにより、開発者が所有するアプリの同意を管理できるようにするなど、必要なアクセス許可を確実に保持し、既存の機能を意図せずに削除しないようにすることができます。

[Graph エクスプローラー](https://developer.microsoft.com/graph/graph-explorer)を使用して、アプリケーションのユーザーの同意を管理するアプリの同意ポリシーを選択します。 [特権ロール管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#privileged-role-administrator)としてサインインする必要があります。

#### Microsoft Graph を使用してユーザーの同意を無効にする

ユーザーの同意を無効にするには、コレクションの更新中に同意ポリシー (`PermissionGrantPoliciesAssigned`) に他の現在の `ManagePermissionGrantsForOwnedResource.*` ポリシー (存在する場合) が含まれることを確認します。 これにより、ユーザーの同意設定と他のリソースの同意設定の現在の構成を維持できます。

```http
PATCH https://graph.microsoft.com/v1.0/policies/authorizationPolicy
{
   "defaultUserRolePermissions": {
       "permissionGrantPoliciesAssigned": [
           "managePermissionGrantsForOwnedResource.{other-current-policies}"
        ]
    }
}
```

#### Microsoft Graph を使ってアプリの同意ポリシーに従ってユーザーの同意を許可する

ユーザーの同意を許可するには、アプリに同意を付与するユーザーの認可を管理するアプリの同意ポリシーを選択します。 コレクションの更新中に同意ポリシー (`PermissionGrantPoliciesAssigned`) に他の現在の `ManagePermissionGrantsForOwnedResource.*` ポリシー (存在する場合) が含まれることを確認します。 これにより、ユーザーの同意設定と他のリソースの同意設定の現在の構成を維持できます。

```http
PATCH https://graph.microsoft.com/v1.0/policies/authorizationPolicy

{
    "defaultUserRolePermissions": {
        "managePermissionGrantsForSelf.{consent-policy-id}",
        "managePermissionGrantsForOwnedResource.{other-current-policies}"
   }
}
```

`{consent-policy-id}` を、適用するポリシーの ID に置き換えます。 作成した [カスタム アプリの同意ポリシー](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-app-consent-policies#create-a-custom-app-consent-policy-using-microsoft-graph) を選択することも、次の組み込みポリシーから選択することもできます。

| ID | 説明 |
| --- | --- |
| microsoft-user-default-low | **選択したアクセス許可に対して、検証済みの発行元からのアプリに対するユーザーの同意を許可する** 制限付きユーザーの同意は、検証済みの発行元のアプリとテナントに登録されているアプリに対してのみ許可し、 *影響の少ない*アクセス許可として分類する場合にのみ許可します。 (ユーザーが同意できる [アクセス許可](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-permission-classifications) を選択するには、必ずアクセス許可を分類してください)。 |
| マイクロソフト・ユーザー・デフォルト・レガシー | **アプリのユーザーの同意を許可する** このオプションを使用すると、すべてのユーザーが、すべてのアプリケーションに対し、管理者の同意を必要としないすべてのアクセス許可に同意することができます |

たとえば、組み込みのポリシー `microsoft-user-default-low` に従ってユーザーの同意を有効にするには、次の PATCH コマンドを使用します。

```http
PATCH https://graph.microsoft.com/v1.0/policies/authorizationPolicy

{
    "defaultUserRolePermissions": {
        "permissionGrantPoliciesAssigned": [
            "managePermissionGrantsForSelf.microsoft-user-default-low",
            "managePermissionGrantsForOwnedResource.{other-current-policies}"
        ]
    }
}
```

::: zone-end

ユーザーの同意設定の更新は、アプリケーションの今後の同意操作にのみ影響します。 既存の同意付与は変更されず、ユーザーは以前に付与されたアクセス許可に基づいて引き続きアクセス権を持ちます。 既存の同意付与を取り消す方法については、「 [エンタープライズ アプリケーションに付与されたアクセス許可を確認する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/manage-application-permissions)参照してください。

ヒント

ユーザーが同意を許可されていないアプリケーションの管理者のレビューと承認をユーザーが要求できるようにするには、 [管理者の同意ワークフローを有効にします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/configure-admin-consent-workflow)。 たとえば、ユーザーの同意が無効になっている場合や、ユーザーによる付与が許可されていないアクセス許可をアプリケーションが要求している場合に、このような操作を行うことができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/create-service-principal-cross-tenant"} -->
## マルチテナント アプリケーションからエンタープライズ アプリケーションを作成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/create-service-principal-cross-tenant
- Service: entra-id / enterprise-apps
- Article date: 2025-07-10
- Summary: マルチテナント アプリケーションのクライアント ID を使用して、エンタープライズ アプリケーションを作成します。

この記事では、マルチテナント アプリケーションのクライアント ID を使用してテナントにエンタープライズ アプリケーションを作成する方法について説明します。 エンタープライズ アプリケーションでは、テナント内のサービス プリンシパルを参照します。 この記事で説明するサービス プリンシパルは、単一のテナントまたはディレクトリ内のグローバル アプリケーション オブジェクトのローカル表現、つまりアプリケーション インスタンスです。

これらのオプションのいずれかを使用してアプリケーションを追加する前に、アプリケーションへのサインインを試みることで、エンタープライズ アプリケーションが既にテナントに存在するかどうかを確認します。 サインインが成功した場合は、エンタープライズ アプリケーションがテナントに既に存在しています。

アプリケーションがテナント内にないことを確認する場合は、次のいずれかの方法でエンタープライズ アプリケーションをテナントに追加します。

### 前提条件

Microsoft Entra テナントにエンタープライズ アプリケーションを追加するには、以下が必要です。

- Microsoft Entra ユーザー アカウント。 まだアカウントがない場合は、[無料でアカウントを作成する](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)ことができます。
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか。
- マルチテナント アプリケーションのクライアント ID (Microsoft Graph では appId とも呼ばれる)。

### エンタープライズ アプリケーションの作成

::: zone pivot="admin-consent-url"

管理者の同意 URL が提供されている場合は、Web ブラウザーから URL に移動して、 [テナント全体の管理者の同意](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/grant-admin-consent) をアプリケーションに付与します。 テナント全体の管理者の同意をアプリケーションに付与すると、テナントに追加されます。 テナント全体の管理者の同意の URL は、次のような形式です。

```http
https://login.microsoftonline.com/common/oauth2/authorize?response_type=code&client_id=248e869f-0e5c-484d-b5ea1fba9563df41&redirect_uri=https://www.your-app-url.com
```

各値の説明:

- `{client-id}` は、アプリケーションのクライアント ID (appID とも呼ばれる) です。

注

エンタープライズ アプリケーションを使用しようとしても、サービス プリンシパルがまだテナントに作成されていない場合、Microsoft Entra は "クライアント アプリケーション {appId} にテナント {tenantId} のサービス プリンシパルがありません" という (401) 未承認のエラーで応答します。これを解決するには、前述のように管理者の同意 URL で同意を実行すると、テナント内のサービス プリンシパルがインスタンス化され、問題が解決されます。

::: zone-end

::: zone pivot="msgraph-powershell"

1. `connect-MgGraph -Scopes "Application.ReadWrite.All"` を実行して、クラウド アプリケーション管理者以上のロールでサインインします。
2. 次のコマンドを実行してエンタープライズ アプリケーションを作成します。

    ```powershell
    New-MgServicePrincipal -AppId 00001111-aaaa-2222-bbbb-3333cccc4444
    ```
3. 作成したエンタープライズ アプリケーションを削除するには、次のコマンドを実行します。

    ```powershell
    Remove-MgServicePrincipal
       -ServicePrincipalId aaaaaaaa-bbbb-cccc-1111-222222222222
    
    ```

::: zone-end

::: zone pivot="ms-graph"

Microsoft Graph は、[Graph エクスプローラー](https://aka.ms/ge)などの API クライアントを使用して操作できます。

1. クライアント アプリに `Application.ReadWrite.All` アクセス許可を付与します。
2. エンタープライズ アプリケーションを作成するには、次のクエリを実行します。 appId はアプリケーションのクライアント ID です。

    ```http
    POST https://graph.microsoft.com/v1.0/servicePrincipals
    Content-type: application/json
    
    {
      "appId": "00001111-aaaa-2222-bbbb-3333cccc4444"
    }
    
    ```
3. 作成したエンタープライズ アプリケーションを削除するには、クエリを実行します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/servicePrincipals(appId='00001111-aaaa-2222-bbbb-3333cccc4444')
    ```

::: zone-end

::: zone pivot="azure-cli"

1. エンタープライズ アプリケーションを作成するには、次のコマンドを実行します。

    ```azurecli
    az ad sp create --id 00001111-aaaa-2222-bbbb-3333cccc4444
    ```
2. 作成したエンタープライズ アプリケーションを削除するには、次のコマンドを実行します。

    ```azurecli
    az ad sp delete --id bbbbbbbb-1111-2222-3333-cccccccccccc
    
    ```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/custom-security-attributes-apps"} -->
## アプリケーションのカスタム セキュリティ属性の管理 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/custom-security-attributes-apps
- Service: entra-id / enterprise-apps
- Article date: 2025-03-05
- Summary: Microsoft Entra テナントに登録されているアプリケーションのカスタム セキュリティ属性を割り当て、更新、一覧表示、または削除します。

Microsoft Entra ID の[カスタム セキュリティ属性](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-overview)は、Microsoft Entra オブジェクトを定義して割り当てることができるビジネス固有の属性 (キーと値のペア) です。 たとえば、カスタム セキュリティ属性を割り当ててアプリケーションをフィルター処理したり、アクセスできるユーザーを決定したりすることができます。 この記事では、Microsoft Entra のエンタープライズ アプリケーションのカスタム セキュリティ属性の割り当て、更新、一覧表示、削除の方法について説明します。

### 前提条件

Microsoft Entra テナント内のアプリケーションのカスタム セキュリティ属性の割り当てや削除には、次のものが必要です。

- アクティブなサブスクリプションを持つ Microsoft Entra アカウント。 [アカウントを無料で作成します](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)。
- [属性割り当て管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator) ロール。
- 既存のカスタム セキュリティ属性があることを確認します。 セキュリティ属性を作成する方法については、「 [Microsoft Entra ID でカスタム セキュリティ属性を追加または非アクティブ化する」を](https://learn.microsoft.com/ja-jp/entra/fundamentals/custom-security-attributes-add)参照してください。

重要

既定では、 [グローバル管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator) とその他の管理者ロールには、カスタム セキュリティ属性の読み取り、定義、または割り当てに対するアクセス許可がありません。

### アプリケーションのカスタム属性の割り当て、更新、一覧表示、または削除

Microsoft Entra ID でアプリケーションのカスタム属性を操作する方法について学習します。

#### カスタム セキュリティ属性のアプリケーションへの割り当て

::: zone pivot="portal"

Microsoft Entra 管理センターを使用してカスタム セキュリティ属性を割り当てるには、次の手順を実行します。

1. [属性割り当て管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照します。
3. カスタムセキュリティ属性を追加するアプリケーションを検索して選択します。
4. [管理] セクションで、[ **カスタム セキュリティ属性**] を選択します。
5. [ **割り当ての追加] を選択します**。
6. [ **属性セット]** で、一覧から属性セットを選択します。
7. [ **属性名**] で、一覧からカスタム セキュリティ属性を選択します。
8. 選択したカスタム セキュリティ属性のプロパティに応じて、1 つの値を入力したり、定義済みの一覧から値を選択したり、複数の値を追加したりできます。

    - フリーフォームの単一値のカスタム セキュリティ属性の場合は、[ **割り当てられた値** ] ボックスに値を入力します。
    - 定義済みのカスタム セキュリティ属性値の場合は、[ **割り当てられた** 値] ボックスの一覧から値を選択します。
    - 複数値のカスタム セキュリティ属性の場合は、[ **値の追加** ] を選択して **[属性値** ] ウィンドウを開き、値を追加します。 値の追加が完了したら、[ **完了]** を選択します。

    [Image: アプリケーションにカスタム セキュリティ属性を割り当てる方法を示すスクリーンショット。]
9. 完了したら、[ **保存]** を選択して、カスタム セキュリティ属性をアプリケーションに割り当てます。

#### アプリケーションのカスタム セキュリティ属性の割り当て値を更新する

1. [属性割り当て管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照します。
3. 更新するカスタム セキュリティ属性の割り当て値を持つアプリケーションを検索して選択します。
4. [管理] セクションで、[ **カスタム セキュリティ属性**] を選択します。
5. 更新するカスタム セキュリティ属性の割り当て値を見つけます。

    カスタム セキュリティ属性をアプリケーションに割り当てた後は、カスタム セキュリティ属性の値のみを変更できます。 属性セットやカスタム セキュリティ属性名など、カスタム セキュリティ属性のその他のプロパティを変更することはできません。
6. 選択したカスタム セキュリティ属性のプロパティに応じて、1 つの値を更新したり、定義済みの一覧から値を選択したり、複数の値を更新したりできます。
7. 完了したら、[ **保存]** を選択します。

#### カスタム セキュリティ属性に基づいてアプリケーションをフィルター処理する

[ **すべてのアプリケーション** ] ページで、アプリケーションに割り当てられているカスタム セキュリティ属性の一覧をフィルター処理できます。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[属性割り当て閲覧者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-reader)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照します。
3. [ **フィルターの追加]** を選択して、[フィールドの選択] ウィンドウを開きます。

    [ **フィルターの追加]** が表示されない場合は、バナーを選択してエンタープライズ アプリケーションの検索プレビューを有効にします。
4. **[フィルター]** で、[**カスタム セキュリティ属性**] を選択します。
5. 属性セットと属性名を選択します。
6. **演算子**の場合は、等しい (**==**)、等しくない (**!=**)、または**開始値を**選択できます。
7. [ **値]** に値を入力または選択します。
8. フィルターを適用するには、[ **適用**] を選択します。

#### アプリケーションからカスタム セキュリティ属性の割り当てを削除する

1. [属性割り当て管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#attribute-assignment-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ**を参照します。
3. 削除するカスタム セキュリティ属性の割り当て値を持つアプリケーションを検索して選択します。
4. [ **管理** ] セクションで、[ **カスタム セキュリティ属性 (プレビュー)]** を選択します。
5. 削除するすべてのカスタム セキュリティ属性の割り当ての横にチェックマークを追加します。
6. [ **割り当ての削除] を選択します**。

::: zone-end

::: zone pivot="ms-powershell"

#### Microsoft Graph PowerShell

Microsoft Entra 組織のアプリケーションへのカスタム セキュリティ属性の割り当ての管理には、Microsoft Graph PowerShell を使用できます。 割り当ての管理には、次のコマンドを使用できます。

#### Microsoft Graph PowerShell を使用して複数の文字列値を持つカスタムセキュリティ属性をアプリケーション (サービス プリンシパル) に割り当てる

[Update-MgServicePrincipal](https://learn.microsoft.com/ja-jp/powershell/module/microsoft.graph.applications/update-mgserviceprincipal) コマンドを使用して、複数文字列値を持つカスタム セキュリティ属性をアプリケーション (サービス プリンシパル) に割り当てます。

指定された値

- 属性セット: `Engineering`
- 属性: `ProjectDate`
- 属性のデータ型: 文字列
- 属性値: `"2024-11-15"`

```powershell
#Retrieve the servicePrincipal

$ServicePrincipal = (Get-MgServicePrincipal -Filter "displayName eq 'TestApp'").Id

$customSecurityAttributes = @{
    Engineering = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "ProjectDate" ="2024-11-15"
    }
}
Update-MgServicePrincipal -ServicePrincipalId $ServicePrincipal -CustomSecurityAttributes $customSecurityAttributes
```

#### Microsoft Graph PowerShell を使用してアプリケーション (サービス プリンシパル) の複数文字列値を持つカスタム セキュリティ属性を更新する

アプリケーションに反映する新しい属性値のセットを指定します。 この例では、プロジェクト属性にもう 1 つの値を追加しています。

指定された値

- 属性セット: `Engineering`
- 属性: `Project`
- 属性のデータ型: 文字列のコレクション
- 属性値: `["Baker","Cascade"]`

```powershell
$customSecurityAttributes = @{
    Engineering = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "Project@odata.type" = "#Collection(String)"
        "Project" = @(
            "Baker"
            "Cascade"
        )
    }
}
Update-MgServicePrincipal -ServicePrincipalId $ServicePrincipal -CustomSecurityAttributes $customSecurityAttributes
```

#### Microsoft Graph PowerShell を使用してカスタム セキュリティ属性に基づいてアプリケーションをフィルター処理する

この例は、指定した値と同じカスタム セキュリティ属性の割り当てを使用して、アプリケーションの一覧をフィルター処理します。

```powershell
$appAttributes = Get-MgServicePrincipal -CountVariable CountVar -Property "id,displayName,customSecurityAttributes" -Filter "customSecurityAttributes/Engineering/Project eq 'Baker'" -ConsistencyLevel eventual
$appAttributes | select Id,DisplayName,CustomSecurityAttributes  | Format-List
$appAttributes.CustomSecurityAttributes.AdditionalProperties | Format-List
```

```Output
Id                       : aaaaaaaa-bbbb-cccc-1111-222222222222
DisplayName              : TestApp
CustomSecurityAttributes : Microsoft.Graph.PowerShell.Models.MicrosoftGraphCustomSecurityAttributeValue

Key   : Engineering
Value : {[@odata.type, #microsoft.graph.customSecurityAttributeValue], [ProjectDate, 2024-11-15], [Project@odata.type, #Collection(String)], [Project, System.Object[]]}
```

#### Microsoft Graph PowerShell を使用してアプリケーションからカスタム セキュリティ属性の割り当てを削除する

この例では、単一の値をサポートするカスタム セキュリティ属性の割り当てを削除します。

```powershell

$params = @{
    "customSecurityAttributes" = @{
        "Engineering" = @{
            "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
            "ProjectDate" = $null
        }
    }
}
Invoke-MgGraphRequest -Method PATCH -Uri "https://graph.microsoft.com/v1.0/servicePrincipals/$ServicePrincipal" -Body $params
```

この例では、複数の値をサポートするカスタム セキュリティ属性の割り当てを削除します。

```powershell
$customSecurityAttributes = @{
    Engineering = @{
        "@odata.type" = "#Microsoft.DirectoryServices.CustomSecurityAttributeValue"
        "Project" = @()
    }
}
Update-MgServicePrincipal -ServicePrincipalId $ServicePrincipal -CustomSecurityAttributes $customSecurityAttributes
```

::: zone-end

::: zone pivot="ms-graph"

#### Microsoft Graph API

Microsoft Entra 組織のアプリケーションへのカスタム セキュリティ属性の割り当ての管理には、Microsoft Graph API を使用できます。 割り当てを管理するために、次の API 呼び出します。

ユーザーに対するその他の同様の Microsoft Graph API の例については、「 [ユーザーのカスタム セキュリティ属性の割り当て、更新、一覧表示、または削除](https://learn.microsoft.com/ja-jp/entra/identity/users/users-custom-security-attributes#powershell-or-microsoft-graph-api) 」および [「例: Microsoft Graph API を使用したカスタム セキュリティ属性の割り当ての割り当て、更新、一覧表示、または削除](https://learn.microsoft.com/ja-jp/graph/custom-security-attributes-examples)」を参照してください。

#### Microsoft Graph API を使用して複数の文字列値を持つカスタムセキュリティ属性をアプリケーション (サービス プリンシパル) に割り当てる

[Update servicePrincipal](https://learn.microsoft.com/ja-jp/graph/api/serviceprincipal-update) API を使用して、文字列値を持つカスタム セキュリティ属性をアプリケーションに割り当てます。

指定された値

- 属性セット: `Engineering`
- 属性: `Project`
- 属性のデータ型: 文字列
- 属性値: `"Baker"`

```http
PATCH https://graph.microsoft.com/v1.0/servicePrincipals/{id}
Content-type: application/json

{
    "customSecurityAttributes":
    {
        "Engineering":
        {
            "@odata.type":"#Microsoft.DirectoryServices.CustomSecurityAttributeValue",
            "Project@odata.type":"#Collection(String)",
            "Project": "Baker"
        }
    }
}
```

#### Microsoft Graph API を使用してアプリケーション (サービス プリンシパル) の複数文字列値を持つカスタム セキュリティ属性を更新する

アプリケーションに反映する新しい属性値のセットを指定します。 この例では、プロジェクト属性にもう 1 つの値を追加しています。

```http
PATCH https://graph.microsoft.com/v1.0/servicePrincipals/{id}
Content-type: application/json

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

#### Microsoft Graph API を使用してカスタム セキュリティ属性に基づいてアプリケーションをフィルター処理する

この例は、指定した値と同じカスタム セキュリティ属性の割り当てを使用して、アプリケーションの一覧をフィルター処理します。 フィルター値は大文字と小文字が区別されます。 要求またはヘッダーに `ConsistencyLevel=eventual` を追加する必要があります。 要求が正しくルーティングされるようにするには、 `$count=true` も含める必要があります。

```http
GET https://graph.microsoft.com/v1.0/servicePrincipals?$count=true&$select=id,displayName,customSecurityAttributes&$filter=customSecurityAttributes/Engineering/Project eq 'Baker'
ConsistencyLevel: eventual
```

#### Microsoft Graph API を使用してアプリケーションからカスタム セキュリティ属性の割り当てを削除する

この例では、複数の値をサポートするカスタム セキュリティ属性の割り当てを削除します。

```http
PATCH https://graph.microsoft.com/v1.0/servicePrincipals/{id}
Content-type: application/json

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

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/datawiza-configure-sha"} -->
## Microsoft Entra ID および Datawiza で安全なハイブリッド アクセスを構成するためのチュートリアル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-configure-sha
- Service: entra-id / enterprise-apps
- Article date: 2024-01-30
- Summary: Datawiza と Microsoft Entra ID を使ってユーザーを認証し、オンプレミスとクラウドのアプリへのアクセス権を付与する方法を説明します。

このチュートリアルでは、Microsoft Entra ID と [Datawiza](https://www.datawiza.com/) を統合して[ハイブリッド アクセス](https://learn.microsoft.com/ja-jp/entra/identity/devices/concept-hybrid-join)を実現する方法について説明します。 [Datawiza Access Proxy (DAP)](https://www.datawiza.com) は、Microsoft Entra ID を拡張して、シングル サインオン (SSO) を可能にし、オンプレミスとクラウドでホストされる Oracle E-Business Suite、Microsoft IIS、SAP などのアプリケーションを保護するためのアクセス制御を実現します。 このソリューションを使用することで企業は、Symantec SiteMinder、NetIQ、Oracle、IBM といった従来の Web アクセス マネージャー (WAM) から Microsoft Entra ID に移行でき、アプリケーションを書き換える必要はありません。 企業は、ノーコードまたはローコードのソリューションとして Datawiza を使って、新しいアプリケーションを Microsoft Entra ID に統合できます。 このアプローチによって企業はエンジニアリングにかかる時間を短縮し、コストを削減しながらゼロ トラスト戦略を遂行できます。

詳細情報: [ゼロ トラスト セキュリティ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/zero-trust)

### Datawiza と Microsoft Entra 認証アーキテクチャ

Datawiza 統合には、次のコンポーネントが含まれています。

- **[Microsoft Entra ID](https://learn.microsoft.com/ja-jp/entra/fundamentals/what-is-entra)** - ユーザーが外部と内部のリソースにサインインしてアクセスするのを助ける、ID およびアクセス管理サービス
- **Datawiza Access Proxy (DAP)** - このサービスは、HTTP ヘッダーを介して ID 情報をアプリケーションに透過的に渡します
- **Datawiza Cloud Management Console (DCMC)** - 管理者が DAP の構成とアクセス制御ポリシーを管理するための UI と RESTful API

次の図は、ハイブリッド環境での Datawiza を使用した認証アーキテクチャを表しています。

[Image: オンプレミス アプリケーションへのユーザー アクセスのための認証プロセスのアーキテクチャ図。]

1. ユーザーが、オンプレミスまたはクラウドでホストされているアプリケーションへのアクセスを要求します。 DAP によって、要求がアプリケーションにプロキシされます。
2. DAP によって、ユーザーの認証状態がチェックされます。 セッション トークンがないか、セッション トークンが無効な場合、ユーザー要求は DAP によって認証のために Microsoft Entra ID に送られます。
3. ユーザー要求は、Microsoft Entra ID によって、Microsoft Entra テナントでの DAP の登録時に指定されたエンドポイントに送信されます。
4. DAP によって、ポリシーと、アプリケーションに転送される HTTP ヘッダーに含まれる属性値が評価されます。 ヘッダー値を正しく設定するための情報を取得するため、DAP によって ID プロバイダーが呼び出されることがあります。 DAP によって、ヘッダー値が設定され、要求がアプリケーションに送信されます。
5. ユーザーは認証され、アクセスを許可されます。

### 前提条件

開始するには、以下が必要です。

- Azure サブスクリプション
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます
- Azure サブスクリプションにリンクされた [Microsoft Entra テナント](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)
- DAP を実行するには、[Docker](https://docs.docker.com/get-docker/) と [docker-compose](https://docs.docker.com/compose/install/)が必要です
    - アプリケーションは、仮想マシン (VM) やベアメタルなどのプラットフォームで実行できます
- レガシ ID システムから Microsoft Entra ID に移行するオンプレミスのアプリケーションまたはクラウドでホストされるアプリケーション
    - この例では、DAP はアプリケーションと同じサーバーにデプロイされます
    - アプリケーションは localhost: 3001 で実行されます。 DAP は localhost: 9772 を通してアプリケーションにトラフィックをプロキシします
    - アプリケーションへのトラフィックは DAP に到達してから、アプリケーションにプロキシされます

### Datawiza Cloud Management Console を構成する

1. [Datawiza Cloud Management Console](https://console.datawiza.com/) (DCMC) にサインインします。
2. DCMC でアプリケーションを作成し、アプリのキーの組を生成します: `PROVISIONING_KEY` と `PROVISIONING_SECRET`。
3. アプリを作成し、キーの組を生成するには、[Datawiza Cloud Management Console](https://docs.datawiza.com/step-by-step/step2.html) の指示に従います。
4. [Microsoft Entra ID とのワンクリック統合](https://docs.datawiza.com/tutorial/web-app-azure-one-click.html)を使って、アプリケーションを Microsoft Entra ID に登録します。

    [Image: [Configure IdP] (IdP の構成) ダイアログの [Automatic Generator] (自動ジェネレーター) 機能のスクリーンショット。]
5. Web アプリケーションを使うには、**[Tenant ID] (テナント ID)**、**[Client ID] (クライアント ID)**、**[Client Secret] (クライアント シークレット)** の各フォーム フィールドを手動で設定します。

    詳細情報: Web アプリケーションを作成して値を取得するには、docs.datawiza.com の [Microsoft Entra ID](https://docs.datawiza.com/idp/azure.html) に関するドキュメントをご覧ください。

    [Image: [Automatic Generator] (自動ジェネレーター) がオフになっている [Configure IdP] (IdP の構成) ダイアログのスクリーンショット。]
6. Docker または Kubernetes を使って DAP を実行します。 サンプル ヘッダーベースのアプリケーションを作成するには、Docker イメージが必要です。

- Kubernetes については、「[Kubernetes を使用して Web アプリで Datawiza Access Proxy をデプロイする](https://docs.datawiza.com/tutorial/web-app-AKS.html)」をご覧ください
- Docker については、「[アプリで Datawiza Access Proxy をデプロイする](https://docs.datawiza.com/step-by-step/step3.html)」をご覧ください
    - 次のサンプルの Docker イメージの docker-compose.yml ファイルを使用できます。

```yaml
services:
   datawiza-access-broker:
   image: registry.gitlab.com/datawiza/access-broker
   container_name: datawiza-access-broker
   restart: always
   ports:
   - "9772:9772"
   environment:
   PROVISIONING_KEY: #############################################
   PROVISIONING_SECRET: ##############################################
   
   header-based-app:
   image: registry.gitlab.com/datawiza/header-based-app
   restart: always
ports:
- "3001:3001"
```

1. コンテナー レジストリにサインインします。
2. この「[重要なステップ](https://docs.datawiza.com/step-by-step/step3.html#important-step)」で、DAP イメージとヘッダー ベースのアプリケーションをダウンロードします。
3. コマンド `docker-compose -f docker-compose.yml up` を実行します。
4. ヘッダー ベースのアプリケーションで Microsoft Entra ID による SSO が有効になります。
5. ブラウザーで `http://localhost:9772/` に移動します。
6. Microsoft Entra のサインイン ページが表示されます。
7. ヘッダーベースのアプリケーションにユーザー属性を渡す DAP は、Microsoft Entra ID からユーザー属性を取得し、ヘッダーまたは Cookie を介してアプリケーションに属性を渡します。
8. メール アドレス、名、姓などのユーザー属性をヘッダー ベースのアプリケーションに渡す方法については、「[ユーザー属性を渡す](https://docs.datawiza.com/step-by-step/step4.html)」をご覧ください。
9. 構成されたユーザー属性を確認するには、各属性の横にある緑色のチェック マークを調べます。

[Image: ホスト、メール、名、姓の各属性を含むホーム ページのスクリーンショット。]

### フローをテストする

1. アプリケーション URL に移動します。
2. ユーザーは、DAP によって、Microsoft Entra サインイン ページにリダイレクトされます。
3. 認証後、ユーザーは DAP にリダイレクトされます。
4. DAP によって、ポリシーが評価され、ヘッダーが計算されて、アプリケーションに送信されます。
5. 要求されたアプリケーションが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/datawiza-mfa-sso-oracle-hyperion-epm"} -->
## Datawiza を構成して Microsoft Entra 多要素認証 (MFA) を有効にし、Oracle Hyperion EPM にシングル サインオンするためのチュートリアル - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-mfa-sso-oracle-hyperion-epm
- Service: entra-id / enterprise-apps
- Article date: 2023-11-01
- Summary: Datawiza Access Proxy (DAP) を使用して Oracle Hyperion EPM の Microsoft Entra 多要素認証とシングル サインオンを有効にする

このチュートリアルを使用して、Datawiza アクセス プロキシ (DAP) を使用して Oracle Hyperion Enterprise Performance Management (EPM) に対して Microsoft Entra 多要素認証とシングル サインオン (SSO) を有効にします。

詳細については、[datawiza.com](https://www.datawiza.com/) を参照してください。

DAP を使用してアプリケーションを Microsoft Entra ID と統合する利点:

- [ゼロ トラストを使用してプロアクティブなセキュリティを採用する](https://www.microsoft.com/security/business/zero-trust) - 最新の環境に適応し、ハイブリッド ワークプレースを採用しつつ、ユーザー、デバイス、アプリ、データを保護するセキュリティ モデル
- [Microsoft Entra ID シングル サインオン](https://azure.microsoft.com/solutions/active-directory-sso/#overview) - 任意の場所からの、デバイスを使用した、ユーザーとアプリにとって安全かつシームレスなアクセス
- [仕組み: Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) - ユーザーは、サインイン時に携帯電話上のコードや指紋スキャンなどの識別形式を求められます
- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) - ポリシーは if-then ステートメントで、ユーザーがリソースにアクセスするにはアクションを完了する必要がある
- [コードなしの Datawiza を使用した Microsoft Entra ID での簡単な認証と認可](https://www.microsoft.com/security/blog/2022/05/17/easy-authentication-and-authorization-in-azure-active-directory-with-no-code-datawiza/) - [Oracle JDE](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-sso-oracle-jde)、[Oracle E-Business Suite](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-sso-oracle-peoplesoft)、[Oracle Sibel](https://www.datawiza.com/enable-sso-mfa-for-oracle-siebel-crm-in-minutes/)、自社製アプリなどの Web アプリケーションを使用します
- [Datawiza Cloud 管理コンソール](https://console.datawiza.com) (DCMC) を使用する - パブリック クラウドとオンプレミスのアプリケーションへのアクセスを管理する

### シナリオの説明

このシナリオでは、保護されたコンテンツへのアクセスを管理するために HTTP Authorization ヘッダーを使用する Oracle Hyperion EPM 統合に重点を置いています。

レガシ アプリケーションでは最新のプロトコルがサポートされていないため、Microsoft Entra SSO との直接的な統合は困難です。 Datawiza Access Proxy (DAP) では、プロトコル遷移によって従来のアプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しします。 DAP を使用すると、統合のオーバーヘッドが軽減され、エンジニアリングにかかる時間が短縮され、アプリケーションのセキュリティが向上します。

### シナリオのアーキテクチャ

このソリューションには、次のコンポーネントがあります。

- **Microsoft Entra ID** - ユーザーが外部と内部のリソースにサインインしてアクセスするのを助ける、ID とアクセスの管理サービス
- **Datawiza アクセス プロキシ (DAP)** - ユーザー サインイン フロー用に OpenID Connect (OIDC)、OAuth、または Security Assertion Markup Language (SAML) を実装するコンテナー ベースのリバース プロキシ。 HTTP ヘッダーを介して ID をアプリケーションに透過的に渡します。
- **Datawiza Cloud Management Console (DCMC)** - 管理者は、DAP とアクセス制御ポリシーを構成するために UI API と RESTful API を使用して DAP を管理します。
- **Oracle Hyperion EPM**: Microsoft Entra ID と DAP によって保護されるレガシ アプリケーション

[Microsoft Entra 認証アーキテクチャを使用した Datawiza](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-configure-sha#datawiza-with-microsoft-entra-authentication-architecture) でサービス プロバイダーによって開始されるフローについてご確認ください。

### 前提条件

次の前提条件が満たされていることを確認してください。

- Azure サブスクリプション
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます
- Azure サブスクリプションにリンクされた Microsoft Entra テナント
    - 「[クイックスタート: Microsoft Entra ID で新しいテナントを作成する](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)」をご覧ください
- Docker と Docker Compose
    - docs.docker.com に移動して [Docker を取得](https://docs.docker.com/get-docker)し、[Docker Compose をインストール](https://docs.docker.com/compose/install)します
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミス ディレクトリに戻されたユーザー ID
    - 「[Microsoft Entra Connect 同期: 同期を理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)」を参照してください
- Microsoft Entra ID アプリケーション管理者の役割を持つアカウント
    - [Microsoft Entra ビルトイン ロール、すべてのロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)に関する記事をご覧ください。
- Oracle Hyperion EMP 環境
    - (省略可能) HTTPS 経由でサービスを公開するための SSL Web 証明書。 テストには、既定の Datawiza 自己署名証明書を使用できます。

### DAP の使用を開始する

Oracle Hyperion EPM を Microsoft Entra ID と統合するには:

1. [Datawiza Cloud Management Console](https://console.datawiza.com/) (DCMC) にサインインします。
2. [ようこそ] ページが表示されます。
3. オレンジ色の **[作業の開始]** ボタンを選択します。

    [Image: スクリーンショットは [使用を開始する] ボタンを示しています。]
4. **[デプロイ名]** の **[名前]** フィールドと **[説明]** フィールドに情報を入力します。

    [Image: スクリーンショットは [名前] および [説明] フィールド、さらに [次へ] ボタンを示しています。]
5. [**次へ**] を選択します。
6. **[アプリケーションの追加]** ダイアログが表示されます。
7. **[プラットフォーム]** で、**[Web]** を選択します。
8. **[アプリ名]** に一意のアプリケーション名を入力します。
9. **[パブリック ドメイン]** に、たとえば `https://hyperion.example.com` を使用します。 テストでは、localhost DNS を使用できます。 ロード バランサーの内側に DAP をデプロイしていない場合は、パブリック ドメイン ポートを使用します。
10. **[リッスン ポート]** で、DAP がリッスンするポートを選択します。
11. **[アップストリーム サーバー]** で、保護する Oracle Hyperion 実装の URL とポートを選択します。
12. [**次へ**] を選択します。
13. **[アプリケーションの追加]** で、情報を入力します。 **[パブリック ドメイン]**、**[リッスン ポート]**、**[アップストリーム サーバー]** のエントリ例を確認してください。
14. [**次へ**] を選択します。
15. **[IdP の構成]** ダイアログで、関連情報を入力します。

注

Datawiza Cloud Management Console (DCMC) の [\[ワンクリック統合\]](https://docs.datawiza.com/tutorial/web-app-azure-one-click.html) を使用して、構成を完了します。 DCMC が、Microsoft Graph API を呼び出して、Microsoft Entra テナントでお客様に代わってアプリケーション登録を作成します。

1. **［作成］** を選択します
2. DAP デプロイ ページが表示されます。
3. デプロイ用の Docker Compose ファイルを書き留めておきます。 このファイルには DAP イメージが含まれています。また、プロビジョニング キーとプロビジョニング シークレットも含まれていて、DCMC から最新の構成とポリシーをプルします。
4. **完了** を選択します。

### SSO ヘッダーと HTTP ヘッダー

DAP では ID プロバイダー (IdP) からユーザー属性が取得され、ヘッダーまたは Cookie を使用してアップストリーム アプリケーションに渡されます。

次の手順では、Oracle Hyperion EPM アプリケーションがユーザーを認識できるようにします。 名前を使用して、HTTP ヘッダーを介して IdP からアプリケーションに値を渡すように DAP に指示します。

1. 左側のナビゲーションから、**[アプリケーション]** を選択します。
2. 作成したアプリケーションを特定します。
3. **[属性パス]** サブタブを選択します。
4. **[フィールド]** で、**[Email]** を選択します。
5. **[予想]** で、**[HYPLOGIN]** を選択します。
6. **[種類]** で、**[ヘッダー]** を選択します。

    [Image: スクリーンショットは [Attribute Pass] (属性パス) タブを示しています。]

    注

    この構成では、Oracle Hyperion によって使用されるサインイン ユーザー名として Microsoft Entra ユーザー プリンシパル名を使用します。 別のユーザー ID については、**[マッピング]** タブに移動します。

    [Image: スクリーンショットはユーザー プリンシパル名を示しています。]

### SSL の構成

次の手順を使用して SSL を構成します。

1. **[詳細]** タブを選択します。

    [Image: スクリーンショットは [詳細] タブを示しています。]
2. **[SSL]** タブで、**[SSL を有効にする]** を選択します。
3. **[証明書の種類]** ドロップダウンで、種類を選択します。 テスト用として、自己署名証明書があります。

    [Image: スクリーンショットは [Cert Type] (証明書の種類) ドロップダウンを示しています。]

    注

    ファイルから証明書をアップロードできます。

    [Image: スクリーンショットは [詳細設定] にある [オプションの選択] の [ファイル ベース] エントリを示しています。]
4. **[保存]** を選択します。

### ログイン リダイレクト URI とログアウト リダイレクト URI

ログイン リダイレクト URI とログアウト リダイレクト URI を示すには、次の手順に従います。

1. **[詳細オプション]** タブを選択します。
2. **[ログイン リダイレクト URI]** と **[ログアウト リダイレクト URI]** については、「`/workspace/index.jsp`」と入力します。

    [Image: スクリーンショットは [Login Redirect URI (ログイン リダイレクト URI) および [Logout Redirect URI] (ログアウト リダイレクト URI) フィールドを示しています。]
3. **[保存]** を選択します。

### Microsoft Entra 多要素認証を有効にする

サインインのセキュリティを強化するために、Microsoft Entra MFA を適用することができます。

詳細については、「[チュートリアル: Microsoft Entra MFA を使用してユーザー サインイン イベントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)」をご確認ください。

1. [アプリケーション管理者ロール](https://portal.azure.com)として、[Azure portal](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference) にサインインします。
2. **[Microsoft Entra ID]**&gt;**[管理]**&gt;**[プロパティ]** を選択します。
3. **[プロパティ]** で、**[セキュリティの既定値の管理]** を選択します。
4. **[セキュリティの既定値群を有効にする]** で、**[はい]** を選択します。
5. **[保存]** を選択します。

### Oracle Hyperion Shared Services コンソールで SSO を有効にする

Oracle Hyperion 環境で SSO を有効にするには、次の手順に従います。

1. 管理者のアクセス許可を使用して Hyperion Shared Service コンソールにサインインします。 たとえば、`http://{your-hyperion-fqdn}:19000/workspace/index.jsp` のようにします。
2. **[ナビゲート]**を選択し、**[Shared Services コンソール]**を選択します。

    [Image: スクリーンショットは [Shared Service Console] (共有サービス コンソール) オプションを示しています。]
3. **[管理]**、**[ユーザー ディレクトリの構成]** の順に選択します。
4. **[セキュリティ オプション]** タブを選択します。
5. **[シングル サインオン構成]** で、**[SSO を有効にする]** チェック ボックスをオンにします。
6. **[SSO プロバイダーまたはエージェント]** ドロップダウンから、**[その他]** を選択します。
7. **[SSO メカニズム]** ドロップダウンから、**[カスタム HTTP ヘッダー]** を選択します。
8. 次のフィールドに、セキュリティ エージェントが EMP に渡すヘッダー名である「**HYPLOGIN**」を入力します。
9. **[OK]** を選択します。

    [Image: スクリーンショットは [Configure User Directories] (ユーザー ディレクトリの構成) オプションと [セキュリティ オプション] タブを示しています。]

### EMP ワークスペースでログオフ後に URL 設定を更新する

1. **[移動]** を選択します。
2. **[管理]** で、**[ワークスペースの設定]**、**[サーバー設定]** の順に選択します。

    [Image: スクリーンショットは [ワークスペース設定] および [サーバー設定] オプションを示しています。]
3. **[ワークスペース サーバーの設定]** ダイアログで、**[ログオフ後の URL]** について、ユーザーが EPM からサインアウトするときに表示される URL `/datawiza/ab-logout` を選択します。
4. **[OK]** を選択します。

    [Image: スクリーンショットは [Post Logoff URL] (ログオフ後の URL) および [OK] ボタンを示しています。]

### Oracle Hyperion EMP アプリケーションをテストする

Oracle Hyperion アプリケーションのアクセスを確認するために、サインインに Microsoft Entra アカウントを使用するためのプロンプトが表示されます。 資格情報がチェックされ、Oracle Hyperion EPM のホーム ページが表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/datawiza-sso-mfa-oracle-ebs"} -->
## Oracle EBS に対する Microsoft Entra 多要素認証とシングル サインオンのために Datawiza を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-sso-mfa-oracle-ebs
- Service: entra-id / enterprise-apps
- Article date: 2024-01-31
- Summary: Datawiza を使用して Oracle E-Business Suite アプリケーションに対して Microsoft Entra 多要素認証と SSO を有効にする方法について説明します。

この記事では、Datawiza を使用して Oracle E-Business Suite (Oracle EBS) アプリケーションに対して Microsoft Entra 多要素認証とシングル サインオン (SSO) を有効にする方法について説明します。

Datawiza を使用してアプリケーションを Microsoft Entra ID と統合することには、次のような利点があります。

- [ゼロ トラスト](https://www.microsoft.com/security/business/zero-trust)セキュリティ モデルが最新の環境に適応し、ハイブリッド ワークプレースを採用しながら、ユーザー、デバイス、アプリ、データを保護するのに役立ちます。
- [シングル サインオン](https://azure.microsoft.com/solutions/active-directory-sso/#overview)により、デバイス ユーザーとアプリに、任意の場所からの安全かつシームレスなアクセスを提供できます。
- [多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks)では、ユーザーがサインインする際に、デバイス上のコードや指紋スキャンなどの識別情報をユーザーに求めます。
- [条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)では、if/then ステートメントとしてポリシーが提供されます。 (if) ユーザーがあるリソースへのアクセスを求める場合、(then) あるアクションを完了する必要があります。
- [Datawiza](https://www.microsoft.com/security/blog/2022/05/17/easy-authentication-and-authorization-in-azure-active-directory-with-no-code-datawiza/) は、コードなしで Microsoft Entra ID での認証と認可を提供します。 Oracle JDE、Oracle EBS、Oracle Sibel、自社製のアプリなどの Web アプリケーションを使用します。
- [Datawiza Cloud 管理コンソール](https://console.datawiza.com) (DCMC) を使用して、パブリック クラウドとオンプレミスのアプリケーションへのアクセスを管理する。

この記事では、従来の Oracle EBS アプリケーションと統合される最新の ID プロバイダー (IdP) に焦点を当てています。 アプリケーションには、Oracle EBS サービス アカウント資格情報のセットと Oracle EBS データベース コンテナー (DBC) ファイルが必要です。

### アーキテクチャ

このソリューションには、次のコンポーネントがあります。

- **Microsoft Entra ID**: Microsoft のクラウドベースの ID およびアクセス管理サービスです。ユーザーはこのサービスを利用して外部リソースおよび内部リソースにサインインしてアクセスすることができます。
- **Oracle EBS**: Microsoft Entra ID で保護するレガシ アプリケーション。
- **Datawiza アクセス プロキシ (DAP)**: ユーザー のサインオン フローに OIDC/OAuth または SAML を実装する軽量のコンテナー ベースのリバース プロキシ。 HTTP ヘッダーを介して ID をアプリケーションに透過的に渡します。
- **DCMC**: DAP を管理する一元管理コンソール。 このコンソールには、管理者が DAP の構成とそのアクセス制御ポリシーを細かく管理するための UI および RESTful API が用意されています。

### 前提条件

この記事の手順を完了するには、次のものが必要です。

- Azure サブスクリプション。 お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます。
- Azure サブスクリプションにリンクされた Microsoft Entra テナント。
- [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)ロール。
- DAP を実行するための Docker と Docker Compose。 詳細については、「[Docker の入手](https://docs.docker.com/get-docker/)」と「[Docker Compose の概要](https://docs.docker.com/compose/install/)」を参照してください。
- オンプレミスのディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミスのディレクトリに戻されたユーザー ID。 詳細については、「[Microsoft Entra Connect Sync: 同期を理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)」を参照してください。
- Oracle EBS 環境。

### SSO 用に Oracle EBS 環境を構成し、DBC ファイルを作成する

Oracle EBS 環境で SSO を有効にするには、次の手順に従います。

1. Oracle EBS 管理コンソールに管理者としてサインインします。
2. ナビゲーション ウィンドウを下にスクロールし、**[ユーザー管理]** を展開して、**[ユーザー]** を選択します。

    [Image: Oracle EBS 管理コンソールのナビゲーション ウィンドウのスクリーンショット。]
3. ユーザー アカウントを追加します。 **[ユーザーの作成]**&gt;**[ユーザー アカウント]** を選択します。

    [Image: ユーザー アカウントを作成するための選択項目のスクリーンショット。]
4. **[User Name]** に、「**DWSSOUSER**」と入力します。
5. **[パスワード]** に、パスワードを入力します。
6. **[Description]** に、「**DW User account for SSO**」と入力します。
7. **[Password Expiration]** で、**[None]** を選択します。
8. ユーザーに **[Apps Schema Connect Role]** を割り当てます。

    [Image: 検索結果で [Apps Schema Connect Role] を割り当てる選択のスクリーンショット。]

### ORACLE EBS に DAP を登録する

Oracle EBS Linux 環境で、DAP 用の新しい DBC ファイルを生成します。 アプリのユーザー資格情報と、アプリケーション層で使用される既定の DBC ファイル (`$FND_SECURE` の下) が必要です。

1. 次のようなコマンドを使用して Oracle EBS の環境を構成します。`./u01/install/APPS/EBSapps.env run`
2. AdminDesktop ユーティリティを使用して、新しい DBC ファイルを生成します。 この DBC ファイルの新しいデスクトップ ノードの名前を指定します。

    `java oracle.apps.fnd.security.AdminDesktop apps/apps CREATE NODE_NAME=\<ebs domain name> DBC=/u01/install/APPS/fs1/inst/apps/EBSDB_apps/appl/fnd/12.0.0/secure/EBSDB.dbc`

    このアクションにより、コマンドを実行した場所に `ebsdb_\<ebs domain name>.dbc` というファイルが生成されます。
3. DBC ファイルのコンテンツをノートブックにコピーします。 このコンテンツは後で使用します。

### SSO 用の ORACLE EBS を有効にする

1. JDE を Microsoft Entra ID に統合するために、[Datawiza Cloud Management Console](https://console.datawiza.com/) にサインインします。

    ウェルカム ページが表示されます。
2. オレンジ色の **[作業の開始]** ボタンを選択します。

    [Image: Datawiza Cloud Management Console でのアクセス プロキシの使用を開始するためのボタンのスクリーンショット。]
3. **[名前]** に、デプロイの名前を入力します。

    [Image: デプロイ名のテキスト ボックスのスクリーンショット。]
4. **[説明]** に、デプロイの説明を入力します。
5. **[次へ]** を選択します。
6. **[Add Application] (アプリケーションの追加)** で、**[Platform] (プラットフォーム)** に **[Oracle E-Business Suite]** を選択します。
7. **[App Name]** に、アプリ名を入力します。
8. **[Public Domain] (パブリック ドメイン)** に、アプリケーションの外部向け URL を入力します。 たとえば、「`https://ebs-external.example.com`」と入力します。 テストには localhost DNS を使用できます。
9. **[Listen Port]** で、DAP でリッスンされるポートを選択します。 DAP をロード バランサーの背後にデプロイしない場合は、**パブリック ドメイン**のポートを使用できます。
10. **[Upstream Servers] (アップストリーム サーバー)** に、保護される Oracle EBS 実装の URL とポートの組み合わせを入力します。
11. **[EBS Service Account] (EBS サービス アカウント)** に、サービス アカウントのユーザー名 (**DWSSOUSER**) を入力します。
12. **[EBS Account Password] (EBS アカウント パスワード)** に、サービス アカウントのパスワードを入力します。
13. **[EBS User Mapping] (ユーザー マッピング)** では、認証のために Oracle EBS ユーザー名にマップされる属性が製品によって決まります。
14. **[EBS DBC Content] (EBS DBC コンテンツ)** に、コピーしたコンテンツを使用します。
15. **[次へ]** を選択します。

#### IdP の構成

DCMC のワンクリック統合を使用して、Microsoft Entra の構成を完了します。 この機能を使用すると、管理コストと、構成エラーが発生する可能性を削減できます。

[Image: IdP を構成するための入力と選択のスクリーンショット。]

#### Docker Compose ファイル

管理コンソールの構成が完了しました。 DAP をアプリケーションと共にデプロイするように求められます。 デプロイ用の Docker Compose ファイルを書き留めておきます。 ファイルには、DAP イメージ `PROVISIONING_KEY` および `PROVISIONING_SECRET` が含まれています。 DAP ではこの情報を使用して、DCMC から最新の構成とポリシーをプルします。

#### SSL の構成

1. 証明書の構成については、アプリケーション ページの **[Advanced]** タブを選択します。 次に、**[SSL]**&gt;**[編集]** を選択します。

    [Image: 詳細設定のタブのスクリーンショット。]
2. **[Enable SSL] (SSL を有効にする)** トグルをオンにします。
3. **[Cert Type] (証明書の種類)** で、証明書の種類を選択します。

    [Image: SSL を有効にし、証明書の種類を選択するためのオプションのスクリーンショット。]

    localhost 用の自己署名証明書があります。 その証明書をテストに使用するには、**[Self Signed] (自己署名)** を選択します。

    [Image: 自己署名証明書を使用するオプションのスクリーンショット。]

    必要に応じて、ファイルから証明書をアップロードできます。 **[Cert Type] (証明書の種類)** で、**[アップロード]** を選択します。 次に、**[Select Option] (オプションの選択)** で **[File Based] (ファイル ベース)** を選択します。

    [Image: ファイル ベースの証明書をアップロードするオプションのスクリーンショット。]
4. **[保存]** を選択します。

#### 省略可能: Microsoft Entra ID で多要素認証を有効にする

サインインのセキュリティを強化するために、Microsoft Entra 管理センターで多要素認証を有効にできます。

1. [アプリケーション管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#global-administrator)にサインインします。
2. **[Entra ID**&gt;**Overview**&gt;**Properties**] タブに移動します。
3. **セキュリティの既定値** で、**セキュリティの既定値の管理** を選択します。
4. **セキュリティの既定値** ウィンドウで、ドロップダウン メニューを切り替えて **有効** を選択します。
5. **[保存]** を選択します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/datawiza-sso-mfa-to-owa"} -->
## Outlook Web Access の Microsoft Entra SSO と MFA 用に Datawiza Access Proxy を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-sso-mfa-to-owa
- Service: entra-id / enterprise-apps
- Article date: 2024-07-02
- Summary: Outlook Web Access の Microsoft Entra SSO と MFA 用に Datawiza Access Proxy を構成する方法について説明します

このチュートリアルでは、Datawiza Access Proxy (DAP) を構成して、Outlook Web Access (OWA) の Microsoft Entra シングル サインオン (SSO) と Microsoft Entra 多要素認証 を有効にする方法について説明します。 最新の ID プロバイダー (IdP) がレガシ OWA と統合されている場合の問題の解決に役立ちます。これは、Kerberos トークン認証によるユーザー識別をサポートしています。

最新のプロトコルのサポートがないため、レガシ アプリと最新の SSO の統合が課題になることがよくあります。 Datawiza Access Proxy を使用すると、プロトコルのサポートに関するギャップが解消され、統合オーバーヘッドが軽減され、アプリケーションのセキュリティが向上します。

統合の利点:

- SSO、MFA、条件付きアクセスによる、ゼロ トラストのセキュリティ強化:
    - [ゼロ トラストを使用したプロアクティブ セキュリティの採用に関](https://www.microsoft.com/security/business/zero-trust)する説明を参照してください
    - 「[条件付きアクセスとは」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を参照してください。
- Microsoft Entra ID および Web アプリとのコードなしでの統合:
    - オワ
    - オラクルJDエドワーズ
    - Oracle E-business Suite
    - オラクル・シーベル
    - オラクルPeopleSoft
    - ご使用のアプリ
    - コード[なしの Datawiza を使用した Microsoft Entra ID での簡単な認証と承認](https://www.microsoft.com/security/blog/2022/05/17/easy-authentication-and-authorization-in-azure-active-directory-with-no-code-datawiza/)を参照してください
- Datawiza Cloud 管理コンソール (DCMC) を使用した、クラウドとオンプレミスのアプリへのアクセス管理:
    - [login.datawiza.com](https://login.datawiza.com/df3f213b-68db-4966-bee4-c826eea4a310/b2c_1a_linkage/oauth2/v2.0/authorize?client_id=4f011d0f-44d4-4c42-ad4c-88c7bbcd1ac8&amp;scope=https%3A%2F%2Fdatawizab2cprod.onmicrosoft.com%2F4f011d0f-44d4-4c42-ad4c-88c7bbcd1ac8%2FReadWrite.All%20openid%20profile%20offline_access&amp;redirect_uri=https%3A%2F%2Fconsole.datawiza.com%2Fhome&amp;client-request-id=3c20ca19-1dc7-4226-b2cf-fab4d7af3929&amp;response_mode=fragment&amp;response_type=code&amp;x-client-SKU=msal.js.browser&amp;x-client-VER=2.14.2&amp;x-client-OS=&amp;x-client-CPU=&amp;client_info=1&amp;code_challenge=hz6u_I8Z04mD8zz-olLBSXJ_OI1T2-Evy699ff0O8Ik&amp;code_challenge_method=S256&amp;nonce=80f15c2b-ff10-40a8-a48c-a2533fb2b8d9&amp;state=eyJpZCI6ImY1NzEyZTcyLTBiZTItNGJjMC1hMmExLTYzNjE3NzYyMGU1OSIsIm1ldGEiOnsiaW50ZXJhY3Rpb25UeXBlIjoicmVkaXJlY3QifX0%3D) に移動してサインインまたはアカウントにサインアップする

### アーキテクチャ

DAP 統合アーキテクチャには次のコンポーネントがあります。

- **Microsoft Entra ID** - ユーザーがサインインして外部リソースと内部リソースにアクセスするのに役立つ ID とアクセス管理サービス
- **OWA** - Microsoft Entra ID によって保護される従来の Exchange Server コンポーネント
- **ドメイン コントローラー** - Windows ベースのネットワーク内のネットワーク リソースへのユーザー認証とアクセスを管理するサーバー
- **キー配布センター (KDC)** - Kerberos 認証システムで秘密鍵とチケットを配布および管理します
- **DAP**- ユーザー サインイン用に OpenID Connect (OIDC)、OAuth、または Security Assertion Markup Language (SAML) を実装するリバース プロキシ。 DAP は、次を使用して保護されたアプリケーションと統合します。
    - HTTP ヘッダー
    - Kerberos
    - JSON Web トークン (JWT)
    - その他のプロトコル
- **DCMC** - 構成とアクセス制御ポリシーを管理するための UI および RESTful API を備えた DAP 管理コンソール

次の図は、顧客ネットワーク内の DAP を使用したユーザー フローを示しています。

[Image: 顧客ネットワーク内の DAP を使用したユーザー フローのスクリーンショット。]

次の図は、ユーザー ブラウザーから OWA へのユーザー フローを示しています。

[Image: ユーザー ブラウザーから owa へのユーザー フローのスクリーンショット。]

1. ユーザー ブラウザーは、DAP で保護された OWA へのアクセスを要求します。
2. ユーザー ブラウザーは、Microsoft Entra ID に移動されます。
3. Microsoft Entra のサインイン ページが表示されます。
4. ユーザーが資格情報を入力します。
5. 認証時に、ユーザー ブラウザーが DAP に送信されます。
6. DAP と Microsoft Entra ID 交換トークン。
7. Microsoft Entra ID が、ユーザー名と関連情報を DAP に発行します。
8. DAP は、資格情報を使用してキー配布センター (KDC) にアクセスします。 DAP が Kerberos チケットを要求します。
9. KDC が Kerberos チケットを返します。
10. DAP が、ユーザー ブラウザーを OWA にリダイレクトします。
11. OWA リソースが表示されます。|

注

後続のユーザー ブラウザー要求には Kerberos トークンが含まれています。これにより、DAP 経由で OWA にアクセスできます。

### 必須コンポーネント

次のコンポーネントが必要になります。 以前の DAP エクスペリエンスは必要ありません。

- Azure アカウント
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を入手してください
- Azure アカウントにリンクされた Microsoft Entra テナント
    - 「[クイック スタート: Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)する」を参照してください
- DAP を実行するには Docker と Docker Compose が必要
    - 参照、 [Docker の取得](https://docs.docker.com/get-docker/)
    - Docker Compose のインストール、[概要](https://docs.docker.com/compose/install/)に関するページを参照してください
- オンプレミスのディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミスのディレクトリに戻されたユーザー ID
    - [Microsoft Entra Connect Sync を参照: 同期を理解してカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)する
- Microsoft Entra アプリケーション管理者のアクセス許可を持つアカウント
    - [Microsoft Entra 組み込みロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference)で、アプリケーション管理者やその他のロールを参照してください。
- Exchange Server 環境。 サポート対象のバージョン:
    - Microsoft インターネット インフォメーション サービス (IIS) 統合 Windows 認証 (IWA) - IIS 7 以降
    - Microsoft OWA IWA - IIS 7 以降
- ドメイン コントローラー (DC) として実行され、Kerberos (IWA) SSO を実装する IIS と Microsoft Entra サービスが構成された Windows Server インスタンス
    - 大規模な運用環境では、アプリケーション サーバー (IIS) が DC としても機能するのは一般的ではありません。
- **省略可能**: HTTPS 経由でサービスを発行する SSL Web 証明書、またはテスト用の DAP 自己署名証明書。

### OWA の Kerberos 認証を有効にする

1. [Exchange 管理センター](https://admin.exchange.microsoft.com/)にサインインします。
2. Exchange 管理センターの左側のナビゲーションで、サーバーを選択 **します**。
3. **[仮想ディレクトリ**] タブを選択します。

    [Image: 仮想ディレクトリを示すスクリーンショット。]
4. [ **サーバーの選択** ] ドロップダウンから、サーバーを選択します。
5. **owa (既定の Web サイト)** をダブルクリックします。
6. **仮想ディレクトリ**で、**認証**タブを選択します。
7. [認証] タブで、[ **1 つ以上の標準認証方法を使用**する] を選択し、[ **統合 Windows 認証**] を選択します。
8. **[保存] を選択する**

    [Image: [internet-explorer] タブを示すスクリーンショット。]
9. コマンド プロンプトを開きます。
10. **iisreset** コマンドを実行します。

    [Image: IIS リセット コマンドのスクリーンショット。]

### DAP サービス アカウントを作成する

DAP では、Kerberos サービスを構成するために、インスタンスが使用する既知の Windows 資格情報が必要になります。 ユーザーは DAP サービス アカウントを使用します。

1. Windows Server インスタンスにサインインします。
2. [ **ユーザーとコンピューター] を選択します**。
3. DAP インスタンスの下矢印を選択します。 例は **datawizatest.com** です。
4. 一覧で、[ **ユーザー**] を右クリックします。
5. メニューから [ **新規**] を選択し、[ **ユーザー**] を選択します。
6. **[新しいオブジェクト] - [ユーザー**] に**名**と**姓**を入力します。
7. [ **ユーザー ログオン名]** に **「dap」**と入力します。
8. [ **次へ**] を選択します。

    [Image: ユーザー ログオンのスクリーンショット。]
9. [ **パスワード**] にパスワードを入力します。
10. [確認] にもう一度入力 **します**。
11. [ **ユーザーがパスワードを変更できない** ]と[ **パスワードの有効期限が切れない**]のチェック ボックスをオンにします。

    [Image: パスワード メニューのスクリーンショット。]
12. [ **次へ**] を選択します。
13. 新しいユーザーを右クリックして、構成されたプロパティを表示します。

### サービス アカウントのサービス プリンシパル名を作成する

サービス プリンシパル名 (SPN) を作成する前に、SPN を一覧表示し、http SPN がそれらの中に含まれているかどうかを確認できます。

1. SPN を一覧表示するには、Windows コマンド ラインで次の構文を使用します。

    `setspn -Q \*/\<**domain.com**`
2. http SPN がそれらの中に含まれていることを確認します。
3. アカウントのホスト SPN を登録するには、Windows コマンド ラインで次の構文を使用します。

    `setspn -A host/dap.datawizatest.com dap`

注

`host/dap.datawizatest.com` は一意の SPN で、dap は作成したサービス アカウントです。

### 制約付き委任用に Windows Server IIS を構成する

1. ドメイン コントローラー (DC) にサインインします。
2. [ **ユーザーとコンピューター] を選択します。**
3. 組織で、 **Users** オブジェクトを見つけて選択します。
4. 作成したサービス アカウントを見つけます。
5. そのアカウントを右クリックします。
6. 一覧から [ **プロパティ**] を選択します。
7. [ **委任** ] タブを選択します。
8. **指定したサービスへの委任に対してのみ[このユーザーを信頼する**]を選択します。
9. [ **任意の認証プロトコルを使用する**] を選択します。
10. **追加**を選択します。

    [Image: 認証プロトコルを示すスクリーンショット。]
11. [ **サービスの追加]** で、[ **ユーザー] または [コンピューター] を選択します。**

    [Image: [サービスの追加] ウィンドウを示すスクリーンショット。]
12. 選択 **するオブジェクト名を入力し**、マシン名を入力します。
13. [ **OK] を選択する**

    [Image: [オブジェクト名の選択] フィールドを示すスクリーンショット。]
14. [ **サービスの追加]** で、[利用可能なサービス] の [サービスの種類] で [http] を選択 **します。**
15. [ **OK] を選択する**

    [Image: Http サービスの追加フィールドを示すスクリーンショット。]

### OWA と Microsoft Entra ID を統合する

OWA と Microsoft Entra ID を統合するには、次の手順に従います。

1. [Datawiza Cloud Management Console (DCMC)](https://console.datawiza.com/) にサインインします。
2. [ようこそ] ページが表示されます。
3. オレンジ色の [ **作業の開始** ] ボタンを選択します。

    [Image: アクセス プロキシ画面を示すスクリーンショット。]

#### 展開名

1. [ **展開名]** に、[ **名前]** と **[説明**] を入力します。
2. [ **次へ**] を選択します。

    [Image: [デプロイ名] 画面を示すスクリーンショット。]

#### アプリケーションの追加

1. [ **アプリケーションの追加]** **で、[プラットフォーム**] で [Web] を選択 **します**。
2. [ **アプリ名]** に、アプリ名を入力します。 わかりやすい名前付け規則をお勧めします。
3. **[パブリック ドメイン] には**、アプリの外部向け URL を入力します。 たとえば、`https://external.example.com` のようにします。 テストには localhost ドメイン ネーム サーバー (DNS) を使用します。
4. **[リッスン ポート]** に、DAP がリッスンするポートを入力します。 ロード バランサーの背後に DAP がデプロイされていない場合は、パブリック ドメインに示されているポートを使用できます。
5. **アップストリーム サーバー**の場合は、OWA 実装の URL とポートの組み合わせを入力します。
6. [ **次へ**] を選択します。

    [Image: [アプリケーションの追加] 画面を示すスクリーンショット。]

#### IdP を構成する

DCMC 統合機能は、Microsoft Entra 構成を完了するのに役立ちます。 代わりに、DCMC は Microsoft Graph API を呼び出してタスクを実行します。 この機能により、時間、労力、エラーが削減されます。

1. **「IdP の構成」**で**名前**を入力します。
2. [ **プロトコル**] で、[ **OIDC**] を選択します。
3. **ID プロバイダーの**場合は、**Microsoft Entra ID を**選択します。
4. **自動ジェネレーターを**有効にします。
5. **[サポートされているアカウントの種類**] で、[**この組織のディレクトリ内のアカウントのみ (シングル テナント)]** を選択します。
6. **作成** を選択します。
7. DAP とアプリケーションのデプロイ手順を示すページが表示されます。
8. デプロイの Docker Compose ファイルを参照してください。そこには DAP のイメージ、また **PROVISIONING\_KEY** と **PROVISIONING\_SECRET** が含まれています。DAP はこれらのキーを使用して、最新の DCMC 構成とポリシーを取得します。

#### Kerberos の構成

1. アプリケーション ページで、[アプリケーションの **詳細**] を選択します。
2. [ **詳細設定** ] タブを選択します。
3. **[Kerberos**] サブ タブで、**Kerberos** を有効にします。
4. **Kerberos 領域**の場合は、Kerberos データベースが格納されている場所またはドメインを入力します。
5. **SPN** の場合は、OWA アプリケーションのサービス プリンシパル名を入力します。 これは、作成した SPN とは異なります。
6. **[委任されたログイン ID] に**、アプリケーションの外部向け URL を入力します。 テストには localhost DNS を使用します。
7. **KDC** の場合は、ドメイン コントローラー IP を入力します。 DNS が構成されている場合は、完全修飾ドメイン名 (FQDN) を入力します。
8. **[サービス アカウント] に**、作成したサービス アカウントを入力します。
9. [ **認証の種類] で**、[パスワード] を選択 **します**。
10. サービス アカウントのパスワードを入力 **します**。
11. **[保存] を選択します**。

    [Image: Kerberos の構成を示すスクリーンショット。]

#### SSL の構成

1. アプリケーション ページで、[ **詳細設定** ] タブを選択します。
2. **SSL** サブタブを選択します。
3. [ **編集] を選択します**。

    [Image: datawiza の詳細ウィンドウを示すスクリーンショット。]
4. [ **SSL を有効にする] オプションを選択します**。
5. [ **証明書の種類] から**、証明書の種類を選択します。 テストには提供された自己署名 localhost 証明書を使用できます。

    [Image: 証明書の種類を示すスクリーンショット。]
6. **[保存] を選択します**。

### Optional: Microsoft Entra 多要素認証を有効にする

サインインのセキュリティを高めるために、Microsoft Entra 多要素認証を適用できます。 プロセスが Microsoft Entra 管理センターで開始されます。

1. [アプリケーション管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **[Entra ID**&gt;**Overview**&gt;**Properties**] タブに移動します。
3. [ **セキュリティの既定値]** で、[ **セキュリティの既定値の管理**] を選択します。
4. [ **セキュリティの既定値** ] ウィンドウで、ドロップダウン メニューを切り替えて **[有効]** を選択します。
5. **[保存] を選択します**。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/datawiza-sso-oracle-jde"} -->
## Datawiza Access Proxy を使用して Oracle JD Edwards アプリケーションの Microsoft Entra 多要素認証と SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-sso-oracle-jde
- Service: entra-id / enterprise-apps
- Article date: 2024-01-30
- Summary: Datawiza Access Proxy を使用して Oracle JD Edwards アプリケーションの Microsoft Entra 多要素認証と SSO を有効にする

このチュートリアルでは、Datawiza Access Proxy (DAP) を使用して Oracle JD Edwards (JDE) アプリケーションの Microsoft Entra シングル サインオン (SSO) と Microsoft Entra 多要素認証を有効にする方法について説明します。

詳細については、[Datawiza アクセス プロキシ](https://www.datawiza.com/)に関するページを参照してください

DAP を使用してアプリケーションを Microsoft Entra ID と統合する利点:

- [ゼロ トラストを使用してプロアクティブなセキュリティを採用する](https://www.microsoft.com/security/business/zero-trust) - 最新の環境に適応し、ハイブリッド ワークプレースを採用しつつ、ユーザー、デバイス、アプリ、データを保護するセキュリティ モデル
- [Microsoft Entra ID シングル サインオン](https://azure.microsoft.com/solutions/active-directory-sso/#overview) - 任意の場所からの、デバイスを使用した、ユーザーとアプリにとって安全かつシームレスなアクセス
- [仕組み: Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) - ユーザーは、サインイン時に携帯電話上のコードや指紋スキャンなどの識別形式を求められます
- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) - ポリシーは if-then ステートメントで、ユーザーがリソースにアクセスするにはアクションを完了する必要がある
- [コードなしの Datawiza を使用した Microsoft Entra ID での簡単な認証と承認](https://www.microsoft.com/security/blog/2022/05/17/easy-authentication-and-authorization-in-azure-active-directory-with-no-code-datawiza/) - Oracle JDE、Oracle E-Business Suite、Oracle Siebel、自宅で成長したアプリなどの Web アプリケーションを使用する
- [Datawiza Cloud 管理コンソール](https://console.datawiza.com) (DCMC) を使用する - パブリック クラウドとオンプレミスのアプリケーションへのアクセスを管理する

### シナリオの説明

このシナリオでは、保護されたコンテンツへのアクセスを管理するために HTTP Authorization ヘッダーを使用する Oracle JDE アプリケーション統合に重点を置いています。

レガシ アプリケーションでは、最新のプロトコルがサポートされていないため、Microsoft Entra SSO との直接的な統合は困難です。 DAP では、プロトコル遷移によって従来のアプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しすることができます。 DAP を使用すると、統合のオーバーヘッドが軽減され、エンジニアリングにかかる時間が短縮され、アプリケーションのセキュリティが向上します。

### シナリオのアーキテクチャ

このシナリオのソリューションには、次のコンポーネントがあります。

- **Microsoft Entra ID** - ユーザーが外部と内部のリソースにサインインしてアクセスするのを助ける、ID とアクセスの管理サービス
- **Oracle JDE アプリケーション** - Microsoft Entra ID によって保護されるレガシ アプリケーション
- **Datawiza アクセス プロキシ (DAP)** - ユーザー サインイン フロー用に OpenID Connect (OIDC)、OAuth、または Security Assertion Markup Language (SAML) を実装するコンテナー ベースのリバース プロキシ。 HTTP ヘッダーを介して ID をアプリケーションに透過的に渡します。
- **Datawiza Cloud Management Console (DCMC)** - DAP を管理するコンソール。 管理者は UI と RESTful API を使用して、DAP とアクセス制御ポリシーを構成します。

詳細情報: 「[Datawiza と Microsoft Entra 認証アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-configure-sha#datawiza-with-azure-ad-authentication-architecture)」

### 前提条件

次の前提条件が満たされていることを確認してください。

- Azure サブスクリプション。
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます
- Azure サブスクリプションにリンクされた Microsoft Entra テナント
    - 「[クイックスタート: Microsoft Entra ID で新しいテナントを作成する](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)」を参照してください。
- Docker と Docker Compose
    - docs.docker.com に移動して [Docker を取得](https://docs.docker.com/get-docker)し、[Docker Compose をインストール](https://docs.docker.com/compose/install)します
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミス ディレクトリに戻されたユーザー ID
    - 「[Microsoft Entra Connect 同期: 同期を理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)」を参照してください
- Microsoft Entra ID でのアカウントとアプリケーション管理者ロール。 「[Microsoft Entra 組み込みロールの一覧](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#all-roles)」を参照してください
- Oracle JDE 環境
- (省略可能) HTTPS 経由でサービスを公開するための SSL Web 証明書。 テストには、既定の Datawiza 自己署名証明書を使用することもできます

### DAB の使用を開始する

Oracle JDE を Microsoft Entra ID と統合するには:

1. [Datawiza Cloud Management Console](https://console.datawiza.com/) にサインインします。
2. [ようこそ] ページが表示されます。
3. オレンジ色の **[作業の開始]** ボタンを選択します。

    [Image: [作業の開始] ボタンのスクリーンショット。]
4. **[名前]** フィールドと **[説明]** フィールドに情報を入力します。
5. **[次へ]** を選択します。

    [Image: [デプロイ名] の下の [名前] フィールドと [次へ] ボタンのスクリーンショット。]
6. **[アプリケーションの追加]** ダイアログで、**[プラットフォーム]** に **[Web]** を選択します。
7. **[アプリ名]** に一意のアプリケーション名を入力します。
8. **[パブリック ドメイン]** に、たとえば、「`https://jde-external.example.com`」と入力します。 構成のテストには、localhost DNS を使用できます。 ロード バランサーの内側に DAP をデプロイしていない場合は、**パブリック ドメイン** ポートを使用します。
9. **[リッスン ポート]**には、DAPがリッスンするポートを選択します。
10. **[アップストリーム サーバー]** で、保護する Oracle JDE 実装の URL とポートを選択します。
11. **[次へ]** を選択します。

[Image: [パブリック ドメイン]、[リッスン ポート]、[アップストリーム サーバー]のエントリのスクリーンショット。]

1. **[IdP の構成]** ダイアログで、情報を入力します。

注

Microsoft Entra 構成を完了するのに役立つ DCMC ワンクリック統合を使用します。 DCMC は、Graph API を呼び出して、Microsoft Entra テナントでユーザーの代わりにアプリケーション登録を作成します。 docs.datawiza.com の「[Microsoft Entra ID とのワンクリック統合](https://docs.datawiza.com/tutorial/web-app-azure-one-click.html)」にアクセスしてください。

1. **［作成］** を選択します

[Image: [プロトコル]、[ID プロバイダー]、[サポートされているアカウントの種類] の各エントリ、および [作成] ボタンのスクリーンショット。]

1. DAP デプロイ ページが表示されます。
2. デプロイ用の Docker Compose ファイルを書き留めておきます。 このファイルには DAP イメージ、プロビジョニング キーとプロビジョニング シークレットも含まれていて、DCMC から最新の構成とポリシーをプルします。

    [Image: Docker エントリのスクリーンショット。]

### SSO および HTTP ヘッダー

DAP では IdP からユーザー属性を取得し、ヘッダーまたは Cookie を使用してアップストリーム アプリケーションに渡します。

Oracle JDE アプリケーションはユーザーを認識する必要があります。名前を使用すると、アプリケーションは IdP から HTTP ヘッダーを介してアプリケーションに値を渡すように DAP に指示します。

1. Oracle JDE で、左側のナビゲーションから **[アプリケーション]** を選択します。
2. **[属性パス]** サブタブを選択します。
3. **[フィールド]** で **[Email]** を選択します。
4. **[予想]** で、**[JDE\_SSO\_UID]** を選択します。
5. **[種類]** で、**[ヘッダー]** を選択します。

    [Image: [属性パス] タブの情報のスクリーンショット。]

    注

    この構成では、Oracle JDE によって使用されるサインイン ユーザー名として Microsoft Entra ユーザー プリンシパル名を使用します。 別のユーザー ID を使用するには、**[マッピング]** タブに移動します。

    [Image: userPrincipalName エントリのスクリーンショット。]
6. **[詳細]** タブを選択します。

    [Image: [詳細設定] タブの情報のスクリーンショット。]

    [Image: [属性パス] タブの情報のスクリーンショット。]
7. **[SSL を有効にする]** を選択します。
8. **[証明書の種類]** ドロップダウンで、種類を選択します。

    [Image: [証明書の種類] ドロップダウンを示すスクリーンショット。]
9. テスト目的で、自己署名証明書を提供する予定です。

    [Image: [SSL を有効にする] メニューを示すスクリーンショット。]

    注

    ファイルから証明書をアップロードするオプションがあります。

    [Image: ファイル オプションからの証明書のアップロードを示すスクリーンショット。]
10. **[保存]** を選択します。

### Microsoft Entra 多要素認証を有効にする

サインインのセキュリティを強化するために、ユーザー サインインに MFA を適用できます。

「[チュートリアル: Microsoft Entra 多要素認証を使用してユーザー サインイン イベントをセキュリティで保護する](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)」を参照してください。

1. [アプリケーション管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **[Entra ID**&gt;**Overview**&gt;**Properties**] タブに移動します。
3. **セキュリティの既定値** で、**セキュリティの既定値の管理** を選択します。
4. **セキュリティの既定値** ウィンドウで、ドロップダウン メニューを切り替えて **有効** を選択します。
5. **[保存]** を選択します。

### Oracle JDE EnterpriseOne コンソールで SSO を有効にする

Oracle JDE 環境で SSO を有効にするには、

1. Oracle JDE EnterpriseOne サーバー マネージャー管理コンソールに管理者としてサインインします。
2. **[インスタンスの選択]** で、**EnterpriseOne HTML Server** の上にあるオプションを選択します。
3. **[構成]** タイルで、**[詳細として表示]** を選択します。
4. **[セキュリティ]** を選択します。
5. **[Oracle Access Manager を有効にする]** チェック ボックスをオンにします。
6. **[Oracle Access Manager Sign-Off URL]** フィールドに、「**datawiza/ab-logout**」と入力します。
7. **[セキュリティ サーバー構成]** セクションで、**[適用]** を選択します。
8. **[停止]** を選択します。

    注

    Web サーバーの構成 (jas.ini) が最新ではないというメッセージが表示されている場合は、**[構成の同期]** を選択します。
9. **[スタート]** を選択します。

### Oracle JDE ベースのアプリケーションをテストする

Oracle JDE アプリケーションをテストするには、アプリケーション ヘッダー、ポリシー、および全体的なテストを検証します。 必要に応じて、ヘッダーとポリシーのシミュレーションを使用して、ヘッダー フィールドとポリシーの実行を検証します。

Oracle JDE アプリケーションのアクセスが行われることを確認するために、サインインに Microsoft Entra アカウントを使用するためのプロンプトが表示されます。 資格情報がチェックされ、Oracle JDE が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/datawiza-sso-oracle-peoplesoft"} -->
## Datawiza Access Proxy を使用して Oracle PeopleSoft アプリケーションに Microsoft Entra multifactor authentication と SSO を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-sso-oracle-peoplesoft
- Service: entra-id / enterprise-apps
- Article date: 2024-01-30
- Summary: Datawiza Access Proxy を使用して、Oracle PeopleSoft アプリケーションに Microsoft Entra Multi-Factor Authentication と SSO を構成する

このチュートリアルでは、Datawiza アクセス プロキシ (DAP) を使用して Oracle PeopleSoft アプリケーションに Microsoft Entra SSO と Microsoft Entra MFA を有効にする方法について説明します。

詳細情報: [Datawiza アクセス プロキシ](https://www.datawiza.com/)

DAP を使用してアプリケーションを Microsoft Entra ID と統合する利点:

- [ゼロ トラストを使用してプロアクティブ セキュリティを採用](https://www.microsoft.com/security/business/zero-trust) する - 最新の環境に適応し、ハイブリッド ワークプレースを採用するセキュリティ モデルであり、人、デバイス、アプリ、データを保護します
- [Microsoft Entra シングル サインオン](https://azure.microsoft.com/solutions/active-directory-sso/#overview) - デバイスを使用して、任意の場所からユーザーとアプリに安全かつシームレスにアクセス
- [しくみ: Microsoft Entra 多要素認証](https://learn.microsoft.com/ja-jp/entra/identity/authentication/concept-mfa-howitworks) - サインイン中に、携帯電話のコードや指紋スキャンなどの ID の形式を求めるメッセージが表示される
- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview) - ポリシーは if-then ステートメントで、ユーザーがリソースにアクセスするにはアクションを完了する必要がある
- [コードなしの Datawiza を使用した Microsoft Entra ID での簡単な認証と承認](https://www.microsoft.com/security/blog/2022/05/17/easy-authentication-and-authorization-in-azure-active-directory-with-no-code-datawiza/) - Oracle JDE、Oracle E-Business Suite、Oracle Siebel、自宅で成長したアプリなどの Web アプリケーションを使用する
- [Datawiza Cloud Management Console (DCMC)](https://console.datawiza.com) を使用する - パブリック クラウドとオンプレミスのアプリケーションへのアクセスを管理する

### シナリオの説明

このシナリオでは、保護されたコンテンツへのアクセスを管理するために HTTP Authorization ヘッダーを使用する Oracle PeopleSoft アプリケーション統合に重点を置いています。

レガシ アプリケーションでは、最新のプロトコルがサポートされていないため、Microsoft Entra SSO との直接的な統合は困難です。 Datawiza Access Proxy (DAP) では、プロトコル遷移によって従来のアプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しします。 DAP を使用すると、統合のオーバーヘッドが軽減され、エンジニアリングにかかる時間が短縮され、アプリケーションのセキュリティが向上します。

### シナリオのアーキテクチャ

このシナリオのソリューションには、次のコンポーネントがあります。

- **Microsoft Entra ID** - ユーザーがサインインして外部リソースと内部リソースにアクセスするのに役立つ ID とアクセス管理サービス
- **Datawiza アクセス プロキシ (DAP)** - ユーザー サインイン フロー用に OpenID Connect (OIDC)、OAuth、または Security Assertion Markup Language (SAML) を実装するコンテナーベースのリバース プロキシ。 HTTP ヘッダーを介して ID をアプリケーションに透過的に渡します。
- **Datawiza Cloud Management Console (DCMC)** - 管理者は、DAP とアクセス制御ポリシーを構成するための UI API と RESTful API を使用して DAP を管理します
- **Oracle PeopleSoft アプリケーション** - Microsoft Entra ID と DAP によって保護されるレガシ アプリケーション

詳細情報: [Datawiza と Microsoft Entra 認証アーキテクチャ](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/datawiza-configure-sha#datawiza-with-azure-ad-authentication-architecture)

### 前提条件

次の前提条件が満たされていることを確認してください。

- Azure サブスクリプションとして
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得できます
- Azure サブスクリプションにリンクされた Microsoft Entra テナント
    - 「[クイック スタート: Microsoft Entra ID で新しいテナントを作成](https://learn.microsoft.com/ja-jp/entra/fundamentals/create-new-tenant)する」を参照してください
- Docker と Docker Compose
    - docs.docker.com に移動して Docker を[取得](https://docs.docker.com/get-docker)し、[Docker Compose をインストール](https://docs.docker.com/compose/install)する
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミス ディレクトリに戻されたユーザー ID
    - [Microsoft Entra Connect Sync を参照: 同期を理解してカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)する
- Microsoft Entra ID アプリケーション管理者の役割を持つアカウント
    - [Microsoft Entra ビルトイン ロール、すべてのロール](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)に関する記事をご覧ください。
- Oracle PeopleSoft 環境
- (省略可能) HTTPS 経由でサービスを公開するための SSL Web 証明書。 テストには、既定の Datawiza 自己署名証明書を使用できます。

### DAP の使用を開始する

Oracle PeopleSoft と Microsoft Entra ID を統合するには:

1. [Datawiza Cloud Management Console (DCMC)](https://console.datawiza.com/) にサインインします。
2. [ようこそ] ページが表示されます。
3. オレンジ色の [ **作業の開始** ] ボタンを選択します。

    [Image: [作業の開始] ボタンのスクリーンショット。]
4. [ **名前** ] フィールドと [ **説明]** フィールドに情報を入力します。

    [Image: [デプロイ名] の [名前] フィールドのスクリーンショット。]
5. [ **次へ**] を選択します。
6. [アプリケーションの追加] ダイアログが表示されます。
7. [ **プラットフォーム**] で、[Web] を選択 **します**。
8. [ **アプリ名]** に、一意のアプリケーション名を入力します。
9. **パブリック ドメイン**の場合は、たとえば `https://ps-external.example.com` を使用します。 テストでは、localhost DNS を使用できます。 ロード バランサーの内側に DAP をデプロイしていない場合は、パブリック ドメイン ポートを使用します。
10. **[リッスン ポート**] で、DAP がリッスンするポートを選択します。
11. **アップストリーム サーバー**の場合は、保護する Oracle PeopleSoft 実装 URL とポートを選択します。
12. [ **次へ**] を選択します。
13. [ **IdP の構成** ] ダイアログで、情報を入力します。

注

DCMC には、Microsoft Entra 構成を完了するのに役立つワンクリック統合があります。 DCMC では、Microsoft Graph API を呼び出して、Microsoft Entra テナントに代わってアプリケーション登録を作成します。 詳細については、[ワンクリック統合と Microsoft Entra ID](https://docs.datawiza.com/tutorial/web-app-azure-one-click.html#preview) の docs.datawiza.com に関するページを参照してください

1. **作成** を選択します。

[Image: [IDP の構成] の下のエントリのスクリーンショット。]

1. DAP デプロイ ページが表示されます。
2. デプロイ用の Docker Compose ファイルを書き留めておきます。 このファイルには DAP イメージ、プロビジョニング キーとプロビジョニング シークレットも含まれていて、これにより、DCMC から最新の構成とポリシーがプルされます。

### SSO ヘッダーと HTTP ヘッダー

DAP では ID プロバイダー (IdP) からユーザー属性が取得され、ヘッダーまたは Cookie を使用してアップストリーム アプリケーションに渡されます。

Oracle PeopleSoft アプリケーションは、ユーザーを認識する必要があります。 アプリケーションにより、名前を使用して、HTTP ヘッダーを介して IdP からアプリケーションに値を渡すように DAP に指示されます。

1. Oracle PeopleSoft の左側のナビゲーションで、[アプリケーション] を選択 **します**。
2. **[属性パス**] サブタブを選択します。
3. **[フィールド] で**、[**電子メール**] を選択します。
4. **予期される** で **PS\_SSO\_UID** を選択します。
5. **[種類]** で、**[ヘッダー]** を選択します。

    [Image: フィールド、予期される、および型のエントリを含む属性パス機能のスクリーンショット。]

    注

    この構成では、Oracle PeopleSoft のサインイン ユーザー名として Microsoft Entra ユーザー プリンシパル名が使用されます。 別のユーザー ID を使用するには、[ **マッピング** ] タブに移動します。

    [Image: ユーザー プリンシパル名のスクリーンショット。]

### SSL の構成

1. [ **詳細設定] タブ**を選択します。

    [Image: [アプリケーションの詳細] の下の [詳細設定] タブのスクリーンショット。]
2. [ **SSL を有効にする] を選択します**。
3. [ **証明書の種類]** ドロップダウンから、種類を選択します。

    [Image: [証明書の種類] ドロップダウンのスクリーンショット。[自己署名] と [アップロード] のオプションが表示されています。]
4. 構成をテストするために、自己署名証明書があります。

    [Image: [自己署名] が選択されている [証明書の種類] オプションのスクリーンショット。]

    注

    ファイルから証明書をアップロードできます。

    [Image: [詳細設定] の [オプションの選択] の [ファイル ベース] エントリのスクリーンショット。]
5. **[保存] を選択します**。

### Microsoft Entra の多要素認証を有効にする

Microsoft Entra MFA を適用して、サインインのセキュリティを強化できます。

詳細情報: [チュートリアル: Microsoft Entra 多要素認証を使用してユーザー サインイン イベントをセキュリティで保護](https://learn.microsoft.com/ja-jp/entra/identity/authentication/tutorial-enable-azure-mfa)する

1. [アプリケーション管理者](https://entra.microsoft.com)として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)にサインインします。
2. **[Entra ID**&gt;**Overview**&gt;**Properties**] タブに移動します。
3. [ **セキュリティの既定値]** で、[ **セキュリティの既定値の管理**] を選択します。
4. [ **セキュリティの既定値** ] ウィンドウで、ドロップダウン メニューを切り替えて **[有効]** を選択します。
5. **[保存] を選択します**。

### Oracle PeopleSoft コンソールで SSO を有効にする

Oracle PeopleSoft 環境で SSO を有効にするには、次の手順に従います。

1. 管理資格情報 (PS/PS など) を使用して、PeopleSoft Console `http://{your-peoplesoft-fqdn}:8000/psp/ps/?cmd=start` にサインインします。
2. PeopleSoft に既定のパブリック アクセス ユーザーを追加する
3. メイン メニューから、 **PeopleTools &gt; Security &gt; ユーザー プロファイル &gt; ユーザー プロファイルに移動 &gt; 新しい値を追加します**。
4. [ **新しい値の追加] を選択します**。
5. ユーザー **PSPUBUSER を**作成します。
6. パスワードを入力します。

    [Image: PS PUBUSER ユーザー ID とパスワード変更オプションのスクリーンショット。]
7. **[ID**] タブを選択します。
8. **[ID の種類] で** [なし] を選択**します**。

    [Image: [ID] タブの [ID の種類] の [なし] オプションのスクリーンショット。]
9. **PeopleTools &gt; Web プロファイル &gt; Web プロファイル構成 &gt; Search &gt; PROD &gt; Security** に移動します。
10. [ **パブリック ユーザー**] で、[ **パブリック アクセスを許可** する] ボックスを選択します。
11. **[ユーザー ID**] に「**PSPUBUSER**」と入力します。
12. パスワードを入力します。

    [Image: [パブリック アクセスの許可]、[ユーザー ID]、[パスワード] オプションのスクリーンショット。]
13. **[保存] を選択します**。
14. SSO を有効にするには、**Signon PeopleCode &gt; PeopleTools &gt; Security &gt; Security Objects** に移動します。
15. [ **PeopleCode のサインオン** ] ページを選択します。
16. **OAMSSO\_AUTHENTICATION**を有効にします。
17. **[保存] を選択します**。
18. PeopleTools アプリケーション デザイナーを使用して PeopleCode を構成するには、[ **ファイル] &gt; [Open &gt; Definition: Record &gt; Name: `FUNCLIB_LDAP`**] に移動します。
19. **FUNCLIB\_LDAP**を開きます。

    [Image: [定義を開く] ダイアログのスクリーンショット。]
20. レコードを選択します。
21. **LDAPAUTH &gt; PeopleCode の表示**を選択します。
22. `getWWWAuthConfig()` 関数 `Change &defaultUserId = ""; to &defaultUserId = PSPUBUSER` を探します。
23. `PS_SSO_UID` 関数のユーザー ヘッダーが `OAMSSO_AUTHENTICATION` であることを確認します。
24. レコード定義を保存します。

    [Image: レコード定義のスクリーンショット。]

### Oracle PeopleSoft アプリケーションをテストする

Oracle PeopleSoft アプリケーションをテストするには、アプリケーション ヘッダー、ポリシー、および全体的なテストを検証します。 必要に応じて、ヘッダーとポリシーのシミュレーションを使用して、ヘッダー フィールドとポリシーの実行を検証します。

Oracle PeopleSoft アプリケーションへのアクセスが正しく行われることを確認するために、サインインに Microsoft Entra アカウントを使用するように求めるメッセージが表示されます。 資格情報がチェックされ、Oracle PeopleSoft が表示されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/deactivate-app-registration"} -->
## アプリの登録を非アクティブ化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/deactivate-app-registration
- Service: entra-id / enterprise-apps
- Article date: 2026-03-16
- Summary: Microsoft Entra ID でアプリの登録を非アクティブ化して、アプリケーションの構成を維持しながらトークンの発行を防ぐ方法について説明します。

アプリの登録を非アクティブ化すると、アプリケーションが保護されたリソースにアクセスするのを防ぐために、テナントから完全に削除することなく、元に戻すことができます。 アプリケーションを非アクティブ化すると、新しいアクセス トークンの受信は直ちに停止されますが、既存のトークンは有効期限が切れるまで有効なままです。 この方法は、セキュリティ調査、疑わしいアプリケーションの一時的な中断、またはアプリケーション構成データを維持する必要がある場合に役立ちます。

アプリケーションを完全に削除する場合とは異なり、非アクティブ化ではすべてのアプリケーション メタデータ、アクセス許可、構成設定が保持されるため、必要に応じてアプリケーションを簡単に再アクティブ化できます。 アプリケーションはテナントのエンタープライズ アプリケーションの一覧に表示されたままですが、ユーザーはサインインできないため、新しいトークンは発行されません。

この記事では、エンタープライズ アプリケーションを非アクティブ化し、非アクティブ化されたアプリケーションを表示し、必要に応じて再アクティブ化する方法について説明します。

### [前提条件]

アプリケーションを非アクティブ化する前に、次の要件を満たしていることを確認してください。

- 次のいずれかの Microsoft Entra ロール:
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
- または、次のアクションを持つカスタム ロール。
    - `microsoft.directory/applications/disablement/update`
- Microsoft Graph を使用する場合は、次の API アクセス許可を使用します。
    - `Application.ReadWrite.All` (委任またはアプリケーション)
    - `Application.ReadWrite.OwnedBy` （アプリケーション、所有するアプリのみの場合）

### アプリケーションの非アクティブ化について

アプリケーションが非アクティブ化されると、次の動作が発生します。

- 即時効果:

    - 新しいアクセス トークン要求が拒否される
    - ユーザーがアプリケーションにサインインできない
    - アプリケーションが新しいトークンを使用して保護されたリソースにアクセスできない
- 保持される要素:

    - 既存のアクセス トークンは、構成された有効期間の有効期限が切れるまで有効なままです
    - アプリケーションの構成、アクセス許可、およびメタデータは保持されます
    - **アプリケーションはエンタープライズ アプリケーション**の一覧に表示されたまま
    - サービス プリンシパル オブジェクトはテナントで保持されます

ユーザーが非アクティブ化されたアプリケーションにサインインしようとすると、そのアプリケーションが所有者によって無効にされたことを示すエラー メッセージが表示されます。 これは、無効な資格情報やアクセス拒否などの他のエラー メッセージとは異なります。

#### 他のオプションとの比較

Microsoft Entra アプリとサービス プリンシパルは、次の 4 つの方法で使用できないようにすることができます。

- **isDisabled** (非アクティブ化) プロパティは、アプリ所有者または管理者によってグローバルに無効にされたアプリに設定されます。
- **disabledByMicrosoftStatus** (Microsoft によって無効) プロパティは、Microsoft によってグローバルに無効にされたアプリに設定されます。
- **accountEnabled** (サインインを無効にする) プロパティは、アプリの所有者または管理者によってテナントで無効になっているサービス プリンシパルに設定されます。
- **DELETE** (削除) 操作は、アプリ所有者または管理者がアプリまたはサービス プリンシパルに対する操作として完了します。

次の表では、さまざまなアプローチについて詳しく説明します。

| アクション | トークンの発行 | 設定が保持されました | リバーシブル | Scope |
| --- | --- | --- | --- | --- |
| 非アクティブ化 | Blocked | イエス | イエス | グローバル (すべてのテナント) |
| Microsoft によって無効になっている | Blocked | イエス | イエス | グローバル (すべてのテナント) |
| サインインを無効にする | テナント内でブロック | イエス | イエス | シングル テナントのみ |
| 削除​​ | Blocked | いいえ (30日間のリサイクルビン) | はい (30 日) | グローバル |

### アプリケーションを非アクティブ化する

Microsoft Graph API または Microsoft Entra 管理センターを使用してアプリケーションを非アクティブ化するには、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) ロールまたは `microsoft.directory/applications/disablement/update` アクションを持つカスタム ロールが必要です。

## [Microsoft Entra 管理センター](#tab/admin-center)
1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[アプリの登録]** を参照します。
3. 登録されているアプリの一覧から、非アクティブ化が必要なアプリを見つけます。
4. 非アクティブ化するアプリを特定したら、そのアプリ登録ページで **[非アクティブ化** ] ボタンを選択します。
5. 2 つ目の **[非アクティブ化** ] ボタンを選択する前に、[アプリの登録の **非アクティブ化** ] ウィンドウに表示される情報を確認します。

    - アプリは保護されたリソースにアクセスできません。
    - 新しいアクセス トークンを取得することはできませんが、既存のアクセス トークンは引き続き有効です。
    - インスタンスを持つテナントの **エンタープライズ アプリケーション** の一覧には引き続き表示されますが、ユーザーはサインインできません。
    - 以前に発行されたアクセス トークンは、有効期間に基づいて無効になります。 アクセス トークンの有効期限または無効化は、既定の有効期限やトークンの有効期間ポリシーなど、さまざまな要因によって異なります。
6. アプリを非アクティブ化することを確認したら、[ **非アクティブ化** ] ボタンを選択すると、直ちに非アクティブ化が行われ、このアプリケーションの `isDisabled` プロパティが `true`に設定されます。 アプリの状態に変更が反映されていることを確認するには、[アプリ**の登録**] ページで非アクティブ化された**状態**の変更を確認します。

    [Image: Microsoft Entra 管理センターの [アプリ登録の非アクティブ化] ウィンドウのスクリーンショット。]

Important

アプリに所有者が割り当てられている場合、この情報は [アプリ登録の **非アクティブ化** ] ウィンドウに表示されます。 非アクティブ化する前に、所有者の一覧を確認し、所有者のいずれかを削除するかどうかを決定します。 他のユーザーがアプリを再アクティブ化できないようにするには、他のすべての所有者を削除します。

[Image: 承認されていない再アクティブ化を防ぐためにアプリの登録を非アクティブ化する前にアプリ所有者を削除するオプションを示すスクリーンショット。]

## [マイクロソフト グラフ API](#tab/graph-api)
1. アプリケーションを非アクティブ化する

    ```http
    PATCH https://graph.microsoft.com/beta/applications/{applicationObjectId}
    Content-Type: application/json
    
    {
        "isDisabled": true
    }
    ```

    ```http
    PATCH https://graph.microsoft.com/beta/applications(appId='{appId}')
    Content-Type: application/json
    
    {
        "isDisabled": true
    }
    ```
2. 非アクティブ化の確認

    ```http
    GET https://graph.microsoft.com/beta/applications/{applicationObjectId}
    ```

    応答には `"isDisabled": true`が含まれます。

    ```http
     GET https://graph.microsoft.com/beta/applications(appId='{appId}')
    ```

    応答には `"isDisabled": true`が含まれます。

---

### 非アクティブ化されたアプリケーションを表示する

非アクティブ化されたアプリケーションを表示して、その状態を監視し、テナントで一時的に無効にされたアプリケーションを追跡できます。

## [Microsoft Entra 管理センター](#tab/admin-center)
1. [Microsoft Entra 管理センター](https://entra.microsoft.com)にサインインします。
2. **[アプリの登録]** を参照します。
3. [ **非アクティブ化されたアプリケーション** ] タブを選択します。
4. または、[ **エンタープライズ アプリ** ] ウィンドウに移動し、[ **管理** ] -&gt;**Properties** -&gt;**Activation 状態**で特定のエンタープライズ アプリを確認します。

    [Image: アプリケーションがアクティブか非アクティブかを示す [エンタープライズ アプリのプロパティ] ページの [アクティブ化の状態] フィールドを示すスクリーンショット。]

## [マイクロソフト グラフ API](#tab/graph-api)
1. 非アクティブ化されたすべてのアプリケーションを一覧表示する

    ```http
    GET https://graph.microsoft.com/beta/applications?$filter=isDisabled eq true
    ```
2. 特定のアプリケーションの状態を取得する

    ```http
    GET https://graph.microsoft.com/beta/applications/{applicationObjectId}?$select=displayName,isDisabled,appId
    ```

---

Important

非アクティブ化は、アプリの登録 (アプリケーション オブジェクト) で実行する必要があります。 非アクティブ化された状態は、エンタープライズ アプリ (サービス プリンシパル オブジェクト) に反映されます。 サービス プリンシパルを直接非アクティブ化することはできません。 サービス プリンシパルでのサインインは、set `accountEnabled = false`を使用してのみ無効にできます。

### 非アクティブ化されたアプリケーションを調査する

非アクティブ化されたアプリケーションを処理する場合は、API のアクセス許可、認証設定、証明書、サインイン ログなど、アプリケーションの構成を調べることで、徹底的な調査を行います。 非アクティブ化の理由、疑わしいアクティビティやセキュリティに関する懸念、影響を受けるユーザー、組織に影響を与える可能性がある依存関係に注意して、調査結果を慎重に文書化します。

調査に基づいて、侵害が疑われる場合はセキュリティ チームにエスカレートする、再アクティブ化する前に不要なアクセス許可を削除する、特定されたセキュリティの問題に対処するためにアプリケーション構成を更新するなど、適切なアクションを実行します。 アプリケーションが不要になったり、継続的なセキュリティ リスクが発生したりする場合は、再アクティブ化ではなく、完全な削除を検討してください。

### アプリケーションを再アクティブ化する

Microsoft Graph API または Microsoft Entra 管理センターを使用してアプリケーションを再アクティブ化するには、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) ロールまたは `microsoft.directory/applications/disablement/update` アクションを持つカスタム ロールが必要です。

## [Microsoft Entra 管理センター](#tab/admin-center)
1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **[アプリの登録]** を参照します。
3. [ **非アクティブ化されたアプリケーション** ] タブを選択して、再アクティブ化する非アクティブ化されたアプリを見つけます。
4. 一覧から非アクティブ化されたアプリケーションを選択します。
5. アプリの登録ページで、[ **再アクティブ化** ] ボタンを選択します。
6. 2 つ目の [再アクティブ化] ボタンを選択する前に、[**再アクティブ化アプリの登録**] ウィンドウに表示される情報を確認します。

    - アプリは、保護されたリソースに再びアクセスできるようになります。
    - 新しいアクセス トークンを取得できます。
    - ユーザーはアプリケーションにサインインできます。
7. アプリを再アクティブ化することを確認したら、[ **再アクティブ化** ] ボタンを選択します。 再アクティブ化はすぐに行われ、このアプリケーションの `isDisabled` プロパティは `false` に設定されます。 アプリの状態が変更を反映していることを確認するには、[**アプリの登録**] ページで**状態**の変更を確認します。

    [Image: アプリケーションを再アクティブ化するオプションを示すスクリーンショット。]

## [マイクロソフト グラフ API](#tab/graph-api)
1. アプリケーションを再アクティブ化する

    ```http
    PATCH https://graph.microsoft.com/v1.0/applications(appId='{appId}')
    Content-Type: application/json
    
    {
        "isDisabled": false
    }
    ```
2. 再アクティブ化の確認

    ```http
    GET https://graph.microsoft.com/v1.0/applications(appId='{appId}')?$select=displayName,isDisabled
    ```

    応答には `"isDisabled": false`が表示されます。

---

### 管理者以外による再アクティブ化の防止

アプリケーションを非アクティブ化する前に、アプリケーションからすべての所有者を削除します。 これにより、少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator) ロールを持つユーザー、または `microsoft.directory/applications/disablement/update` アクションを持つカスタム ロールを持つユーザーのみが、アプリケーションを再アクティブ化できるようになります。

### 無効化と再有効化の監査

アプリケーションが非アクティブ化または再アクティブ化されるたびに、Microsoft Entra 監査ログ イベントが発生し、次のイベントが発生します。

- **サービス**: コア ディレクトリ
- **カテゴリ**: ApplicationManagement
- **Activity** (activityDisplayName): 「アプリケーションを更新」

Microsoft Entra 管理センターでは、[ **監視] と [正常性] &gt; [監査ログ**] でこれらのイベントを確認できます。 **Update アプリケーション** イベントを選択したら、[**監査ログの詳細**] ウィンドウの [**変更されたプロパティ**] タブに移動します。

[Image: 'isDisabled' プロパティの変更によるアプリケーションの非アクティブ化の監査ログの詳細を示すスクリーンショット。]

古い値と新しい値を持つプロパティ名 `isDisabled` が表示されます。ここで、"true" が非アクティブ化され、"false" または null がアクティブ化または再アクティブ化されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/deactivate-application-portal"} -->
## エンタープライズ アプリケーションを非アクティブ化する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/deactivate-application-portal
- Service: entra-id / enterprise-apps
- Article date: 2025-11-25
- Summary: Microsoft Entra ID でエンタープライズ アプリケーションを非アクティブ化して、アプリケーション構成を維持しながらトークンの発行を防ぐ方法について説明します。

エンタープライズ アプリケーションを非アクティブ化すると、アプリケーションが保護されたリソースにアクセスするのを防ぐために、アプリケーションをテナントから完全に削除することなく、元に戻すことができます。 アプリケーションを非アクティブ化すると、新しいアクセス トークンの受信は直ちに停止されますが、既存のトークンは有効期限が切れるまで有効なままです。 この方法は、セキュリティ調査、疑わしいアプリケーションの一時的な中断、またはアプリケーション構成データを維持する必要がある場合に役立ちます。

アプリケーションを完全に削除する場合とは異なり、非アクティブ化ではすべてのアプリケーション メタデータ、アクセス許可、構成設定が保持されるため、必要に応じてアプリケーションを簡単に再アクティブ化できます。 アプリケーションはテナントのエンタープライズ アプリケーションの一覧に表示されたままですが、ユーザーはサインインできないため、新しいトークンは発行されません。

この記事では、エンタープライズ アプリケーションを非アクティブ化し、非アクティブ化されたアプリケーションを表示し、必要に応じて再アクティブ化する方法について説明します。

### [前提条件]

アプリケーションを非アクティブ化する前に、次の要件を満たしていることを確認してください。

- 次のいずれかの Microsoft Entra ロール:
    - [クラウドのアプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)
    - [アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)
- Microsoft Graph を使用する場合は、次の API アクセス許可を使用します。
    - `Application.ReadWrite.All` (委任またはアプリケーション)
    - `Application.ReadWrite.OwnedBy` （アプリケーション、所有するアプリのみの場合）

### アプリケーションの非アクティブ化について

アプリケーションが非アクティブ化されると、次の動作が発生します。

- 即時効果:

    - 新しいアクセス トークン要求が拒否される
    - ユーザーがアプリケーションにサインインできない
    - アプリケーションが新しいトークンを使用して保護されたリソースにアクセスできない
- 保持される要素:

    - 既存のアクセス トークンは、構成された有効期間の有効期限が切れるまで有効なままです
    - アプリケーションの構成、アクセス許可、およびメタデータは保持されます
    - アプリケーションはエンタープライズ アプリケーションの一覧に表示されたまま
    - サービス プリンシパル オブジェクトはテナントで保持されます

ユーザーが非アクティブ化されたアプリケーションにサインインしようとすると、そのアプリケーションが所有者によって無効にされたことを示すエラー メッセージが表示されます。 これは、無効な資格情報やアクセス拒否などの他のエラー メッセージとは異なります。

#### 他のオプションとの比較

Microsoft Entra アプリとサービス プリンシパルは、次の 4 つの方法で使用できないようにすることができます。

- **isDisabled** (非アクティブ化) プロパティは、アプリ所有者または管理者によってグローバルに無効にされたアプリに設定されます。
- **disabledByMicrosoftStatus** (Microsoft によって無効) プロパティは、Microsoft によってグローバルに無効にされたアプリに設定されます。
- **accountEnabled** (サインインを無効にする) プロパティは、アプリの所有者または管理者によってテナントで無効になっているサービス プリンシパルに設定されます。
- **DELETE** (削除) 操作は、アプリ所有者または管理者がアプリまたはサービス プリンシパルに対する操作として完了します。

次の表では、さまざまなアプローチについて詳しく説明します。

| アクション | トークンの発行 | 設定が保持されました | リバーシブル | Scope |
| --- | --- | --- | --- | --- |
| 非アクティブ化 | Blocked | イエス | イエス | グローバル (すべてのテナント) |
| Microsoft によって無効になっている | Blocked | イエス | イエス | グローバル (すべてのテナント) |
| サインインを無効にする | テナント内でブロック | イエス | イエス | シングル テナントのみ |
| 削除​​ | Blocked | いいえ (30日間のリサイクルビン) | はい (30 日) | グローバル |

### アプリケーションを非アクティブ化する

Microsoft Graph API を使用してアプリケーションを非アクティブ化するには、少なくとも **[クラウド アプリケーション管理者ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)** 必要です。

1. アプリケーションを非アクティブ化する

    ```http
    PATCH https://graph.microsoft.com/beta/applications/{applicationObjectId}
    Content-Type: application/json
    
    {
        "isDisabled": true
    }
    ```
2. 非アクティブ化の確認

    ```http
    GET https://graph.microsoft.com/beta/applications/{applicationObjectId}
    ```

    応答には `"isDisabled": true`が含まれます。

### 非アクティブ化されたアプリケーションを表示する

1. 非アクティブ化されたすべてのアプリケーションを一覧表示する

    ```http
    GET https://graph.microsoft.com/beta/applications?$filter=isDisabled eq true
    ```
2. 特定のアプリケーションの状態を取得する

    ```http
    GET https://graph.microsoft.com/beta/applications/{applicationObjectId}?$select=displayName,isDisabled,appId
    ```

### 非アクティブ化されたアプリケーションを調査する

非アクティブ化されたアプリケーションを処理する場合は、API のアクセス許可、認証設定、証明書、サインイン ログなど、アプリケーションの構成を調べることで、徹底的な調査を行います。 非アクティブ化の理由、疑わしいアクティビティやセキュリティに関する懸念、影響を受けるユーザー、組織に影響を与える可能性がある依存関係に注意して、調査結果を慎重に文書化します。

調査に基づいて、侵害が疑われる場合はセキュリティ チームにエスカレートする、再アクティブ化する前に不要なアクセス許可を削除する、特定されたセキュリティの問題に対処するためにアプリケーション構成を更新するなど、適切なアクションを実行します。 アプリケーションが不要になったり、継続的なセキュリティ リスクが発生したりする場合は、再アクティブ化ではなく、完全な削除を検討してください。

### アプリケーションを再アクティブ化する

Microsoft Graph API を使用してアプリケーションを再アクティブ化するには、少なくとも **[アプリケーション管理者ロールが](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#application-administrator)** 必要です。

1. アプリケーションを再アクティブ化する

    ```http
    PATCH https://graph.microsoft.com/v1.0/applications/{application-id}
    Content-Type: application/json
    
    {
        "isDisabled": false
    }
    ```
2. 再アクティブ化の確認

    ```http
    GET https://graph.microsoft.com/v1.0/applications/{application-id}?$select=displayName,isDisabled
    ```

    応答には `"isDisabled": false`が表示されます。

### 管理者以外による再アクティブ化の防止

アプリケーションを非アクティブ化する前に、アプリケーションからすべての所有者を削除します。 これにより、テナント全体の `microsoft.directory/applications/enable` スコープを持つユーザーのみがアプリケーションを再アクティブ化できます。 このスコープは、管理ロールに制限されます。 このスコープは、管理ロールに制限されます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/debug-saml-sso-issues"} -->
## アプリケーションに対する SAML に基づいたシングル サインオンをデバッグする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/debug-saml-sso-issues
- Service: entra-id / enterprise-apps
- Article date: 2025-06-20
- Summary: Microsoft Entra ID でアプリケーションへの SAML ベースのシングル サインオンをデバッグします。

この記事では、SAML ベースのシングル サインオンを使用する Microsoft Entra ID のアプリケーションについて、[シングル サインオン](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/what-is-single-sign-on)の問題を見つけて修正する方法を説明します。

### 開始する前に

[マイ アプリによるセキュリティで保護されたサインイン拡張機能](https://support.microsoft.com/account-billing/troubleshoot-problems-with-the-my-apps-portal-d228da80-fcb7-479c-b960-a1e2535cbdff#im-having-trouble-installing-the-my-apps-secure-sign-in-extension)をインストールすることをお勧めします。 このブラウザー拡張機能により、シングル サインオンに関する問題の解決に必要な SAML 要求および SAML 応答の情報を収集しやすくなります。 拡張機能をインストールできない場合でも、この記事では、拡張機能がインストールされている場合とされていない場合の両方について、問題を解決する方法を示します。

マイ アプリによるセキュリティで保護されたサインイン拡張機能ををダウンロードしてインストールするには、次のいずれかのリンクを使用します。

- [クロム](https://go.microsoft.com/fwlink/?linkid=866367)
- [Microsoft Edge](https://microsoftedge.microsoft.com/addons/detail/my-apps-secure-signin-ex/gaaceiggkkiffbfdpmfapegoiohkiipl)

### SAML に基づいたシングル サインオンをテストする

Microsoft Entra ID と対象アプリケーションの間の、SAML に基づいたシングル サインオンをテストするには:

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. エンタープライズ アプリケーションのリストから、シングル サインオンをテストするアプリケーションを選択し、左側のオプションの **[シングル サインオン]** を選択します。
4. **[シングル サインオン方式の選択]** ペインで、 **[SAML]** を選択します。
5. SAML ベースのシングル サインオン テスト エクスペリエンスを開くには、 **[シングル サインオンのテスト]** (手順 5) に進みます。 **[テスト]** ボタンが淡色表示される場合は、まず、 **[基本的な SAML 構成]** セクションで必須の属性を入力して保存する必要があります。
6. **[シングル サインオンのテスト]** ぺージで、会社の資格情報を使用して対象のアプリケーションにサインインします。 現在のユーザーまたは別のユーザーとしてサインインできます。 別のユーザーとしてサインインすると、認証するよう求めるプロンプトが表示されます。

    [Image: SAML SSO ページのテストを示すスクリーンショット]

サインインできる場合、テストは成功です。 この場合、Microsoft Entra ID がアプリケーションに、SAML 応答トークンを発行しました。 アプリケーションはこの SAML トークンを使用して、正常にユーザーをサインインさせました。

会社のサインイン ページまたはアプリケーションのページでエラーが発生する場合は、以降のセクションのいずれかに従ってエラーを解決します。

### 会社のサインイン ページでのサインイン エラーを解決する

サインインしようとすると、会社のサインイン ページに次の例のようなエラーが表示されることがあります。

[Image: 会社のサインイン ページのエラーを示す例]

このエラーをデバッグするには、エラー メッセージと SAML 要求が必要です。 マイ アプリによるセキュリティで保護されたサインイン拡張機能は、この情報を自動的に収集し、Microsoft Entra ID に解決ガイダンスを表示します。

#### インストールしたマイ アプリによるセキュリティで保護されたサインイン拡張機能を使用してサインインのエラーを解決するには

1. エラーが発生すると、拡張機能によって、Microsoft Entra ID の **[シングル サインオンのテスト]** ページにリダイレクトして戻されます。
2. **[シングル サインオンのテスト]** ページで、**[SAML 要求をダウンロードします]** を選択します。
3. エラーと SAML 要求内の値に基づいて、具体的な解決ガイダンスが表示されます。
4. Microsoft Entra ID の構成を自動的に更新して問題を解決する **[修正する]** ボタンが表示されます。 このボタンが表示されない場合、サインインの問題は Microsoft Entra ID の不適切な構成が原因ではありません。

サインイン エラーの解決策が表示されない場合は、フィードバック ボックスを使用して Microsoft に問い合わせることをお勧めします。

#### MyApps Secure Sign-in 拡張機能をインストールせずにエラーを解決するには

1. ページの右下隅のエラー メッセージをコピーします。 エラー メッセージには以下が含まれています。
    - CorrelationID とタイムスタンプ。 これらの値は、Microsoft のエンジニアが問題を識別して、実際の問題に対する正確な解決策を提供する助けとなるため、Microsoft とのサポート ケースを作成するときに重要です。
    - 問題の根本原因を明らかにしている文章。
2. Microsoft Entra ID に戻り、**[シングル サインオンのテスト]** ページを見つけます。
3. **[Get resolution guidance]** (解決ガイダンスの取得) の上にあるテキスト ボックスに、エラー メッセージを貼り付けます。
4. **[解決ガイダンスを入手する]** をクリックし、問題を解決するための手順を表示します。 ガイダンスには、SAML 要求または SAML 応答からの情報が必要な場合があります。 マイ アプリによるセキュリティで保護されたサインイン拡張機能を使用していない場合は、SAML の要求や応答を取得するために [Fiddler](https://www.telerik.com/fiddler) などのツールが必要なことがあります。
5. SAML 要求に含まれる送信先が、Microsoft Entra ID から取得した SAML シングル サインオン サービス URL に対応していることを確認します。
6. SAML 要求に含まれる発行者が、Microsoft Entra ID のアプリケーションのために構成した識別子と同じであることを確認します。 Microsoft Entra ID は、発行者を使用してディレクトリ内のアプリケーションを検索します。
7. AssertionConsumerServiceURL は、アプリケーションが Microsoft Entra ID から SAML トークンを受け取ることになっている場所であることを確認します。 この値は Microsoft Entra ID 内で構成できますが、SAML 要求の一部としては必須の値ではありません。

### アプリケーションのページでサインイン エラーを解決する

正常にサインインしてから、アプリケーションのページにエラーが表示される場合があります。 このエラーは、Microsoft Entra ID からアプリケーションにトークンが発行されたが、アプリケーションが応答を受け付けないときに発生します。

このエラーを解決するには、次の手順に従うか、こちらの [Microsoft Entra ID を使用して SAML SSO のトラブルシューティングを行う方法についての短いビデオ](https://www.youtube.com/watch?v=poQCJK0WPUk&amp;list=PLLasX02E8BPBm1xNMRdvP6GtA6otQUqp0&amp;index=8)をご覧ください:

1. アプリケーションが Microsoft Entra ギャラリーにある場合は、アプリケーションと Microsoft Entra ID を統合するためのすべての手順に従っていることを確認します。 アプリケーションの統合手順については、[SaaS アプリケーションの統合に関するチュートリアルの一覧](https://learn.microsoft.com/ja-jp/entra/identity/saas-apps/tutorial-list)を参照してください。
2. SAML 応答を取得します。

    - マイ アプリによるセキュリティで保護されたサインイン拡張機能がインストールされている場合、**[シングル サインオンのテスト]** ページで、**[SAML 応答をダウンロードします]** を選びます。
    - この拡張機能がインストールされていない場合は、[Fiddler](https://www.telerik.com/fiddler) などのツールを使用して SAML 応答を取得します。
3. SAML 応答トークン内の以下の要素に注目します。

    - ユーザーの一意の識別子である NameID の値形式
    - そのトークンで発行された要求
    - トークンの署名に使用された証明書。

        SAML 応答の詳細については、「[シングル サインオンの SAML プロトコル](https://learn.microsoft.com/ja-jp/entra/identity-platform/single-sign-on-saml-protocol)」を参照してください。
4. SAML 応答の内容を確認したので、問題の解決方法のガイダンスについて、「[サインイン後、アプリケーションのページでエラーが発生する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/application-sign-in-problem-application-error)」を参照してください。
5. まだ正常にサインインできない場合は、SAML 応答には何が不足しているか、アプリケーション ベンダーに問い合わせることができます。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/delete-application-portal"} -->
## エンタープライズ アプリケーションの削除 - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-application-portal
- Service: entra-id / enterprise-apps
- Article date: 2025-03-06
- Summary: Microsoft Entra IDでエンタープライズ アプリケーションを削除します。

この記事では、Microsoft Entra テナントに追加されたエンタープライズ アプリケーションを削除する方法について説明します。

エンタープライズ アプリケーションを削除すると、30 日間ごみ箱に中断状態で保持されます。 30 日間は、 [アプリケーションを復元](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-application)できます。 削除された項目は、30 日の期間が過ぎると自動的に物理的に削除されます。 アプリケーションの削除と回復に関してよく寄せられる質問の詳細については、「アプリケーションの [削除と回復に関する FAQ」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq)を参照してください。

Important

エンタープライズ アプリケーションを削除する前に、非 [アクティブ化がニーズを](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/deactivate-application-portal) 満たしているかどうかを検討してください。 非アクティブ化により、アプリケーション構成を保持しながらトークンの発行とユーザーのサインインが防止されるため、調査、セキュリティ インシデント、一時的な中断に最適です。

### 前提条件

エンタープライズ アプリケーションを削除するには、以下が必要です。

- Microsoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
    - 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - サービス プリンシパルの所有者
- [テナントにエンタープライズ アプリケーションが追加されました](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal)。

::: zone pivot="portal"

### 管理センターを使用してエンタープライズ アプリケーションMicrosoft Entra削除する

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズ アプリ | すべてのアプリケーション**
3. 検索ボックスに既存のアプリケーションの名前を入力し、検索結果からアプリケーションを選択します。 この記事では、**Microsoft Graph コマンド ライン ツール**を例として使用します。
4. 左側のメニューの [ **管理** ] セクションで、[ **プロパティ**] を選択します。
5. **Properties** ペインの上部にある **Delete** を選択し、 **Yes** を選択して、Microsoft Entra テナントからアプリケーションを削除することを確認します。

    [Image: エンタープライズ アプリケーションを削除する方法のスクリーンショット。]

::: zone-end

::: zone pivot="entra-powershell"

### Microsoft Entra PowerShell を使用してエンタープライズ アプリケーションを削除する

[Microsoft Entra PowerShell](https://learn.microsoft.com/ja-jp/powershell/entra-powershell/?preserve-view=true&view=entra-powershell) モジュールを使用していることを確認します。

1. Microsoft Entra PowerShell に接続し、少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. アプリケーション名でフィルター処理して削除するアプリケーションを取得し、アプリケーションを削除します。

    ```powershell
    Connect-Entra -Scopes 'Application.ReadWrite.All'
    Get-EntraServicePrincipal -Filter "displayName eq 'Test-app1'" | Remove-EntraServicePrincipal
    ```

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してエンタープライズ アプリケーションを削除する

1. Microsoft Graph PowerShell に接続し、少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。

    ```powershell
    Connect-MgGraph -Scopes 'Application.ReadWrite.All'
    ```
2. テナント内のエンタープライズ アプリケーションの一覧を取得します。

    ```powershell
    Get-MgServicePrincipal
    ```
3. 削除するエンタープライズ アプリのオブジェクト ID を記録します。
4. エンタープライズ アプリケーションを削除します。

    ```powershell
    Remove-MgServicePrincipal -ServicePrincipalId 'aaaaaaaa-bbbb-cccc-1111-222222222222'
    ```

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph API を使用してエンタープライズ アプリケーションを削除する

[Graph Explorer](https://developer.microsoft.com/graph/graph-explorer) を使用してエンタープライズ アプリケーションを削除するには、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

1. テナント内のサービス プリンシパルの一覧を取得するには、次のクエリを実行します。

    ```http
    GET https://graph.microsoft.com/v1.0/servicePrincipals
    ```
2. 削除するエンタープライズ アプリの ID を記録します。
3. エンタープライズ アプリケーションを削除します。

    ```http
    DELETE https://graph.microsoft.com/v1.0/servicePrincipals/{servicePrincipal-id}
    ```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/delete-recover-faq"} -->
## アプリケーションの削除と回復に関する FAQ - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/delete-recover-faq
- Service: entra-id / enterprise-apps
- Article date: 2024-02-21
- Summary: 削除されたアプリとサービス プリンシパルの回復に関してよく寄せられる質問 (FAQ) に対する回答を確認します。

アプリケーションの削除と回復に関してよく寄せられる質問 (FAQ) を次に示します。

### アプリケーションを作成すると、Directory\_QuotaExceeded エラーが発生します。この問題を回避するにはどうすればよいですか?

>
> 管理者以外のユーザーは、アプリケーションとサービス プリンシパルを含む 250 個以下の Microsoft Entra リソースを作成できます。 アクティブ リソースと復元可能な削除済みリソースの両方が、このクォータに加算されます。 不要なアプリケーションをさらに削除しても、引き続きクォータに加算されます。 クォータを解放するには、削除済みアイテム コンテナー内のオブジェクトを[完全に削除する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-application)必要があります。
>
> サービスの制限の詳細については、[Azure リソース管理](https://learn.microsoft.com/ja-jp/azure/azure-resource-manager/management/azure-subscription-service-limits?msclkid=6cb6cc54c68711ec93eb9539fce3cc28#azure-active-directory-limits)に関するページを参照してください。

### 削除されたすべてのアプリケーションとサービス プリンシパルはどこで確認できますか?

>
> 論理的に削除されたアプリケーション オブジェクトとサービス プリンシパル オブジェクトは、削除済みアイテム コンテナーに移動し、最大 30 日間復元できます。 30 日後に完全に削除され、クォータが解放されます。
>
> 削除されたアプリケーション オブジェクトを Microsoft Entra 管理センターで表示する方法については、[復元可能なアプリケーションの表示](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restore-app#view-your-deleted-applications)に関するページを参照してください。
>
> 削除されたサービス プリンシパルは、Microsoft Entra 管理センターでは表示できません。 PowerShell または Microsoft Graph API を使用して復元可能なサービス プリンシパルを表示する方法については、「[復元可能なサービス プリンシパルを表示する」](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-application)を参照してください。

### 削除されたアプリケーションまたはサービス プリンシパルを復元するにはどうすればよいですか?

>
> 最近削除したアプリケーションの登録を Microsoft Entra 管理センターで復元する方法については、[アプリケーションの登録の復元](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restore-app)に関する記事を参照してください。 アプリケーション登録と、対応するサービス プリンシパルが削除された場合、サービス プリンシパルも復元されます。
>
> 最近削除されたサービス プリンシパルを復元する方法については、[サービス プリンシパルの復元](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-application)に関するページを参照してください。 この方法は、PowerShell または Microsoft Graph API を使用して最近削除されたアプリケーションの登録を復元する場合にも適用できます。

### 論理的に削除されたアプリケーションまたはサービス プリンシパルを完全に削除するにはどうすればよいですか?

>
> アプリケーションの登録を Microsoft Entra 管理センターで完全に削除するには、「[アプリケーションを完全に削除する](https://learn.microsoft.com/ja-jp/entra/identity-platform/howto-restore-app#permanently-delete-an-application)」を参照してください。
>
> サービス プリンシパルを完全に削除するには、[サービス プリンシパル を完全の削除](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-application)に関するページを参照してください。 この方法は、PowerShell または Microsoft Graph API を使用してアプリケーションの登録を完全に削除する場合にも適用できます。

### Microsoft Entra ID によってアプリケーションとサービス プリンシパルが完全に削除される間隔を設定できますか?

>
> いいえ。 物理的な削除の周期性を構成することはできません。

### マネージド ID は論理的に削除されますか?

>
> はい。マネージド ID は論理的に削除されます。 論理的に削除されたマネージド ID サービス プリンシパルは、削除後 30 日以内にごみ箱から表示できますが、復元または完全に削除することはできません。 マネージド ID サービス プリンシパルは、30 日後に完全に削除されます。 論理的に削除されたマネージド ID サービス プリンシパルを表示する方法の詳細については、「 [削除されたサービス プリンシパルの表示」を参照](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/restore-application)してください。

### 回復したサービス プリンシパルからのプロビジョニング データを表示できません。 回復するにはどうすればよいですか。

>
> サービス プリンシパル を回復すると、最初に次のスクリーンショットにエラーが表示されることがあります。 この問題は、40 分から 1 日の間で自然に解決されます。 プロビジョニング ジョブをすぐに開始したい場合は、[再開] をクリックして、プロビジョニング サービスを強制的に再実行することができます。 [Restart] (再開) をクリックすると、初期サイクルがトリガーされます。これは、100,000 人以上のユーザーまたはグループ メンバーシップを持つお客様には時間がかかる場合があります。
>
> [Image: ユーザー プロビジョニング データを回復するスクリーンショット。]

### アプリケーション プロキシ用に構成されたアプリケーションを回復しました。 回復後、アプリ プロキシの構成が表示されません。 回復するにはどうすればよいですか?

>
> ポータル UI を使用してアプリ プロキシの構成を回復することはできません。 アプリ プロキシの設定を回復するには、API を使用します。 アプリ プロキシ データの同期が戻るには、最大 24 時間の遅延が予想されます。

### 回復後にサービス プリンシパル オブジェクトに設定したポリシーを表示できません。 回復するにはどうすればよいですか?

>
> 現在、ポリシーを回復できません。 サービス プリンシパルを復元するときに、ポリシーをもう一度構成する必要があります。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/disable-user-sign-in-portal"} -->
## アプリケーションへのユーザーのサインインを無効にする - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/disable-user-sign-in-portal
- Service: entra-id / enterprise-apps
- Article date: 2025-03-03
- Summary: ユーザーがMicrosoft Entra IDでアプリケーションにサインインするのを防ぎ、トークンが発行されないようにする方法について説明します。

アプリケーションの構成または管理中に、アプリケーションへのトークンの発行をi一時的に停止する場合があります。 または、従業員によるアクセスが好ましくないアプリケーションをブロックする場合もあります。 ユーザーによるアプリケーションへのアクセスをブロックするには、アプリケーションへのユーザー サインインを無効にできます。その結果、対象のアプリケーションに対してすべてのトークンが発行されなくなります。

この記事では、Microsoft Entra管理センターと PowerShell の両方を使用して、ユーザーが Microsoft Entra ID でアプリケーションにサインインできないようにする方法について説明します。 特定のユーザーがアプリケーションにアクセスできないようにする方法を探している場合は、[ユーザーまたはグループの割り当て](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/assign-user-or-group-access-portal)を使用してください。

注

この記事の手順では、1 つのテナントのユーザー サインインを無効にします。 (マルチテナント アプリの場合) すべてのテナントでアプリケーションをグローバルに無効にする必要がある場合は、代わりに [アプリケーションを非アクティブ化](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/deactivate-application-portal) することを検討してください。これにより、すべてのトークン発行がグローバルに禁止されます。

### 前提条件

ユーザーのサインインを無効にするには、以下が必要です。

- Microsoft Entraユーザー アカウント。 アカウントをまだお持ちでない場合は、 [無料でアカウントを作成](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)できます。
- 次のいずれかのロール:
    - クラウド アプリケーション管理者
    - アプリケーション管理者
    - サービス プリンシパルの所有者

::: zone pivot="portal"

### Microsoft Entra管理センターを使用してユーザー サインインを無効にする

1. [Microsoft Entra管理センター](https://entra.microsoft.com)に少なくとも [Cloud アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. ユーザーがサインインすることを無効にするアプリケーションを検索し、そのアプリケーションを選択します。
4. **プロパティ**を選択します。
5. **[ユーザーのサインインが有効になっていますか?]** では **[いいえ]** を選択します。
6. **保存**を選びます。

::: zone-end

::: zone pivot="entra-powershell"

### Microsoft Entra PowerShell を使用してユーザー サインインを無効にする

エンタープライズ アプリの一覧に表示されていなくても、アプリの AppId が既知の場合があります。 たとえば、アプリを削除した場合や、サービス プリンシパルはまだ作成されていないが、Microsoft が事前認証している場合です。 アプリのサービス プリンシパルを手動で作成し、次の Microsoft Entra PowerShell コマンドレットを使用して無効にすることができます。

Microsoft Entra PowerShell モジュールをインストールしてください。 NuGet モジュールまたは PowerShell V2 モジュールの新しいMicrosoft Entraをインストールするように求められた場合は、「Y」と入力して Enter キーを押します。 少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

```PowerShell
# Connect to Microsoft Entra PowerShell
Connect-Entra -scopes "Application.ReadWrite.All"

# The AppId of the service principal to be disabled
$appId = "{AppId}"

# Disable the service principal
$servicePrincipal = Get-EntraServicePrincipal -Filter "appId eq '$appId'"
Set-EntraServicePrincipal -ObjectId $servicePrincipal.ObjectId -AccountEnabled $false
```

::: zone-end

::: zone pivot="ms-powershell"

### Microsoft Graph PowerShell を使用してユーザー サインインを無効にする

エンタープライズ アプリの一覧に表示されていなくても、アプリの AppId が既知の場合があります。 たとえば、アプリを削除した場合や、サービス プリンシパルはまだ作成されていないが、Microsoft がそのアプリを事前認証している場合です。 アプリのサービス プリンシパルを手動で作成し、次の Microsoft Graph PowerShell コマンドレットを使用して無効にすることができます。

Microsoft Graph モジュールをインストールしていることを確認します (コマンド `Install-Module Microsoft.Graph` を使用します)。 少なくとも [クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインする必要があります。

```powershell
# Connect to Microsoft Graph PowerShell
Connect-MgGraph -Scopes "Application.ReadWrite.All"

# Get the AppId of the service principal to be disabled  
$appId = "{AppId}"  
$servicePrincipal = Get-MgServicePrincipal -Filter "appId eq '$appId'"  

# Disable the service principal
Update-MgServicePrincipal -ServicePrincipalId $servicePrincipal.Id -AccountEnabled:$false 
```

::: zone-end

::: zone pivot="ms-graph"

### Microsoft Graph API を使用してユーザー サインインを無効にする

エンタープライズ アプリの一覧に表示されていなくても、アプリの AppId が既知の場合があります。 たとえば、アプリを削除した場合や、サービス プリンシパルはまだ作成されていないが、Microsoft がそのアプリを事前認証している場合です。 アプリのサービス プリンシパルを手動で作成し、次のMicrosoft Graph呼び出しを使用して無効にすることができます。

アプリケーションへのサインインを無効にするには、[クラウド アプリケーション管理者](https://developer.microsoft.com/graph/graph-explorer)以上として [Graph Explorer](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。

`Application.ReadWrite.All` アクセス許可に同意する必要があります。

次のクエリを実行して、アプリケーションへのユーザー サインインを無効にします。

```http
PATCH https://graph.microsoft.com/v1.0/servicePrincipals/00001111-aaaa-2222-bbbb-3333cccc4444

Content-type: application/json

{
    "accountEnabled": false
}
```

::: zone-end
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/end-user-experiences"} -->
## アプリケーションのエンドユーザー エクスペリエンス - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/end-user-experiences
- Service: entra-id / enterprise-apps
- Article date: 2026-09-04
- Summary: Microsoft Entra ID を使用して組織内のエンド ユーザーにアプリケーションを展開するカスタマイズ可能な方法について説明します。

Microsoft Entra ID には、組織内のエンド ユーザーにアプリケーションをデプロイするためのカスタマイズ可能な方法が複数用意されています。

- Microsoft Entra マイ アプリ
- Microsoft 365 アプリケーション起動プログラム
- フェデレーション アプリへの直接サインオン
- フェデレーション アプリ、パスワードベースのアプリ、または既存のアプリへのディープ リンク

組織で展開する方法は、ユーザーの判断です。

### Microsoft Entra マイ アプリ

マイ アプリは Web ベースのポータルであり、Microsoft Entra ID の組織ユーザーは、管理者によってアクセス権が付与されているアプリを表示および起動できます。 [Microsoft Entra ID P1 または P2](https://www.microsoft.com/security/business/identity-access-management/azure-ad-pricing) を使用しているエンド ユーザーの場合は、マイ アプリを通じてセルフサービス グループ管理機能を利用することもできます。

既定では、すべてのアプリケーションが 1 つのページにまとめて表示されます。 しかし、コレクションを使用して関連するアプリケーションをグループ化し、別々のタブで表示すれば、アプリケーションが見つけやすくなります。 たとえば、コレクションを使用して、特定の担当業務、タスク、プロジェクトなどに関連したアプリケーションの論理グループを作成することができます。 詳細については、「 [マイ アプリ ポータルでコレクションを作成する」を](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/access-panel-collections)参照してください。

[マイ アプリ](https://myapps.microsoft.com) は Microsoft Entra 管理センターとは別のものであり、ユーザーが Azure サブスクリプションまたは Microsoft 365 サブスクリプションを持っている必要はありません。

Microsoft Entra マイ アプリ の詳細については、 [マイ アプリの概要を](https://support.microsoft.com/account-billing/sign-in-and-start-apps-from-the-my-apps-portal-2f3b1bae-0e5a-4a86-a33e-876fbd2a4510)参照してください。

### Microsoft 365 アプリケーション起動プログラム

Microsoft 365 アプリケーション起動ツールは、Microsoft 365 を使用する組織に推奨されるアプリ起動ソリューションです。

Office 365 アプリケーション起動ツールの詳細については、「Office [365 アプリ起動ツールにアプリを表示](https://learn.microsoft.com/ja-jp/previous-versions/office/office-365-api/)する」を参照してください。

### フェデレーション アプリへの直接サインオン

SAML 2.0、WS-Federation、または OpenID Connect をサポートするほとんどのフェデレーション アプリでは、ユーザーがアプリケーションから開始する機能もサポートしています。 その後、ユーザーは Microsoft Entra ID 経由で、自動リダイレクトまたはサインイン用リンクの選択によりサインインします。 直接サインオンはサービス プロバイダーによって開始されるサインオンであり、Microsoft Entra アプリケーション ギャラリーのほとんどのフェデレーション アプリケーションでサポートされています。 詳細については、Microsoft Entra 管理センターにあるアプリのシングル サインオン構成ウィザードからリンクされているドキュメントを参照してください。

### 直接サインオンのリンク

Microsoft Entra ID では、パスワードベースのシングル サインオン、リンクされたシングル サインオン、任意の形式のフェデレーション シングル サインオンをサポートする個々のアプリケーションへの直接シングル サインオン リンクもサポートされます。

直接サインオンのリンクは、特定のアプリケーションの Microsoft Entra サインイン プロセスを通じてユーザーを送信するように作成された URL です。 ユーザーは、マイ アプリまたは Microsoft 365 からアプリケーションを起動する必要はありません。 これらの **ユーザー アクセス URL は** 、使用可能なエンタープライズ アプリケーションのプロパティの下にあります。 Microsoft Entra 管理センターで、 **Entra ID**&gt;**Enterprise アプリ**を選択します。 アプリケーションを選択し、[ **プロパティ**] を選択します。

[Image: X プロパティのユーザー アクセス URL の例]

直接サインオンのリンクをコピーし、選んだアプリケーションへのサインイン リンクを提供する必要がある任意の場所に貼り付けることができます。 これらは、電子メール、またはユーザー アプリケーション アクセス用に設定した任意のカスタム Web ベースのポータルに配置できます。 次の URL は、X に対する Microsoft Entra ID の直接シングル サインオン URL の例です。

`https://myapps.microsoft.com/signin/X/230848d52c8745d4b05a60d29a40fced`

マイ アプリの組織固有の URL と同様に、myapps.microsoft.com ドメインの後にディレクトリのアクティブまたは検証済みドメインのいずれかを追加することで、直接サインオン URL をさらにカスタマイズできます。 直接サインオン URL をカスタマイズすると、ユーザーが最初にユーザー ID を入力しなくても、必ずサインイン ページで組織のブランド設定が即座に読み込まれます。

`https://myapps.microsoft.com/contosobuild.com/signin/X/230848d52c8745d4b05a60d29a40fced`

許可されているユーザーがこれらのアプリケーションに固有のリンクのいずれかを選ぶと、(まだサインインされていないと想定して) 最初に組織のサインイン ページが表示されます。 サインイン後、最初にマイ アプリで停止することなく、アプリにリダイレクトされます。 パスワードベースのシングル サインオン用ブラウザー拡張機能など、ユーザーがアプリケーションにアクセスするための前提条件を満たしていない場合、リンクをクリックすると、ユーザーは不足している拡張機能をインストールするよう求められます。 アプリケーションのシングル サインオン構成が変更された場合でもリンク URL は変わりません。

これらのリンクでは、マイ アプリおよび Microsoft 365 と同じアクセス制御メカニズムが使用されます。 Microsoft Entra 管理センターでアプリケーションに割り当てられているユーザーまたはグループのみが正常に認証できます。 ただし、承認されていないユーザーには、アクセス権が付与されていないことを説明するメッセージが表示されます。 許可されていないユーザーには、本人がアクセス権を持つ使用可能なアプリケーションを表示するためのマイ アプリを読み込むためのリンクが示されます。

### レガシ マイ アプリ エクスペリエンスの設定

ユーザーがマイ アプリの**プレビュー機能を使用できる**などの従来のマイ アプリプレビュー設定は、マイ アプリ ポータルやその他のアプリ起動ツールでは使用されなくなりました。 これらの設定は、ユーザーに表示される内容やアプリ起動ツールの動作には影響しません。

Note

従来のマイ アプリとマイ スタッフエクスペリエンスの設定は、サービスによって使用されなくなり、ユーザーの動作には影響しません。 これらの設定は、Microsoft Entra 管理センターから削除されます。 管理者によるアクションは必要ありません。

マイ アプリでユーザーに表示される内容を制御するには、アプリケーションにユーザーとグループを割り当て、コレクションを使用してアプリケーションを整理します。 詳細については、「[マイ アプリ ポータルでのコレクションの作成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/access-panel-collections)」を参照してください。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-forms-advanced"} -->
## フォームベースの SSO 用に F5 BIG-IP Access Policy Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-forms-advanced
- Service: entra-id / enterprise-apps
- Article date: 2024-06-28
- Summary: F5 BIG-IP Access Policy Manager と Microsoft Entra ID を構成して、フォームベースのアプリケーションへのセキュア ハイブリッド アクセス (SHA) を実現する方法について説明します。

F5 BIG-IP Access Policy Manager (APM) と Microsoft Entra ID を構成して、フォームベースのアプリケーションへのセキュア ハイブリッド アクセス (SHA) を実現する方法について説明します。 Microsoft Entra シングル サインオン (SSO) 用の BIG-IP 公開サービスには、次のような利点があります。

- Microsoft Entra の事前認証および条件付きアクセスによるゼロ トラスト ガバナンスの強化
    - [「条件付きアクセスとは」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を参照してください。
    - [ゼロ トラスト セキュリティ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/zero-trust)を参照してください
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- マネージド ID と 1 つのコントロール プレーンからのアクセス
    - [Microsoft Entra 管理センター](https://entra.microsoft.com)を参照してください

詳細情報:

- [F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [エンタープライズ アプリケーションの SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)

### シナリオの説明

このシナリオでは、フォームベース認証 (FBA) 用に構成された内部レガシ アプリケーションがあります。 レガシには最新の認証プロトコルがないため、Microsoft Entra ID でアプリケーション アクセスを管理するのが理想的です。 最新化には時間と労力がかかり、ダウンタイムのリスクが生じます。 代わりに、パブリック インターネットと内部アプリケーションの間に BIG-IP をデプロイします。 この構成により、アプリケーションへの受信アクセスが制限されます。

アプリケーションの前に BIG-IP を配置することにより、Microsoft Entra の事前認証とヘッダーベース SSO でサービスをオーバーレイできます。 このオーバーレイにより、アプリケーションのセキュリティ体制が向上します。

### シナリオのアーキテクチャ

SHA ソリューションには、次のコンポーネントがあります。

- **アプリケーション**- SHA によって保護されたサービス BIG-IP を公開。
    - アプリケーションによってユーザー資格情報を検証する
    - 任意のディレクトリやオープンソースなどを使用する
- **Microsoft Entra ID**- BIG-IP に対するユーザー資格情報、条件付きアクセス、SSO を検証するセキュリティ アサーション マークアップ言語 (SAML) ID プロバイダー (IdP)。
    - SSO では、Microsoft Entra ID から BIG-IP にユーザー識別子を含む属性が提供される
- **BIG-IP**- アプリケーションへのリバース プロキシと SAML サービス プロバイダー (SP)。
    - SAML IdP に認証を委任する BIG-IP では、バックエンド アプリケーションに対するヘッダーベースの SSO を実行します。
    - SSO では、他のフォーム ベースの認証アプリケーションに対して、キャッシュされたユーザー資格情報が使用されます

SHA では、SP と IdP によって開始されるフローがサポートされます。 次の図は、SP によって開始されるフローを示しています。

[Image: サービス プロバイダーによって開始されるフローの図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続します。
2. BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトします。
3. Microsoft Entra によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われます。
4. ユーザーは BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行されます。
5. BIG-IP は、ユーザーにアプリケーション パスワードの入力を求め、キャッシュに格納します。
6. BIG-IP はアプリケーションに要求を送信し、サインオン フォームを受信します。
7. APM スクリプトによってユーザー名とパスワードが入力され、フォームが送信されます。
8. Web サーバーによってアプリケーション ペイロードが提供され、クライアントに送信されます。

### 前提条件

次のコンポーネントが必要です。

- Azure サブスクリプションとして
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を入手してください
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか
- BIG-IP、または Azure に BIG-IP Virtual Edition (VE) をデプロイします
    - [Azure での F5 BIG-IP Virtual Edition 仮想マシンのデプロイに](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)関するページを参照してください
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90 日間の BIG-IP 全機能試用版。 [無料試用版](https://www.f5.com/trial/big-ip-trial.php)を見る
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID
    - [「Microsoft Entra Connect Sync: 同期の理解とカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)」を参照してください
- HTTPS でサービスを公開するための SSL 証明書。または、テスト中は既定の証明書を使用します
    - [SSL プロファイル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile)を参照する
- フォームベースの認証アプリケーション、またはテスト用にインターネット インフォメーション サービス (IIS) フォームベース認証 (FBA) アプリを設定する
    - [フォーム ベースの認証を](https://learn.microsoft.com/ja-jp/troubleshoot/developer/webapps/aspnet/development/forms-based-authentication)参照してください

### BIG-IP の構成

この記事の構成は、柔軟な SHA 実装: BIG-IP 構成オブジェクトの手動作成です。 この方法は、ガイド付き構成テンプレートで対応できないシナリオに使用します。

Note

サンプルの文字列または値は、実際の環境のものに置き換えてください。

### Microsoft Entra ID に F5 BIG-IP を登録する

BIG-IP 登録は、エンティティ間の SSO の最初の手順です。 F5 BIG-IP ギャラリー テンプレートから作成するアプリは証明書利用者であり、BIG-IP で公開されているアプリケーションの SAML SP を表します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**エンタープライズアプリ**&gt;**すべてのアプリケーション**に移動します。
3. [ **すべてのアプリケーション** ] ウィンドウで、[ **新しいアプリケーション**] を選択します。
4. [ **Microsoft Entra ギャラリーの参照** ] ウィンドウが開きます。
5. クラウド プラットフォーム、オンプレミス アプリケーション、おすすめのアプリケーション用のタイルが表示されます。 **注目のアプリケーション アイコンは、** フェデレーション SSO とプロビジョニングのサポートを示します。
6. Azure ギャラリーで **、F5** を検索します。
7. **F5 BIG-IP APM Microsoft Entra ID 統合を**選択します。
8. 新しいアプリケーションがアプリケーション インスタンスを認識するために使用する **名前** を入力します。
9. **追加を選択します**。
10. **[作成]** を選択します。

#### F5 BIG-IP への SSO を有効にする

BIG-IP APM から要求された SAML トークンを実行するように BIG-IP の登録を構成します。

1. 左側のメニューの [ **管理** ] セクションで、[ **シングル サインオン**] を選択します。
2. [ **シングル サインオン** ] ウィンドウが表示されます。
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. [ **いいえ] を選択します。後で保存します**。
5. [ **SAML でシングル サインオンを設定** する] ウィンドウで、 **ペン** アイコンを選択します。
6. **[識別子]** では、値を BIG-IP 発行されたアプリケーション URL に置き換えます。
7. **応答 URL の**場合は、値を置き換えますが、アプリケーション SAML SP エンドポイントのパスは保持します。 この構成により、SAML フローは IdP-Initiated モードで動作します。
8. Microsoft Entra ID によって SAML アサーションが発行され、ユーザーが BIG-IP エンドポイントにリダイレクトされます。
9. SP 開始モードの場合は、[ **サインオン URL] にアプリケーション URL** を入力します。
10. **[ログアウト URL]** に、サービス ホスト ヘッダーの前に付加された BIG-IP APM シングル ログアウト (SLO) エンドポイントを入力します。
11. この結果、BIG-IP APM ユーザー セッションは、Microsoft Entra ID からユーザーがサインアウトすると終了します。
12. **[保存] を選択します**。
13. [SAML 構成] ペインを閉じます。
14. SSO テスト プロンプトをスキップします。
15. **[ユーザー属性] および [要求**] セクションのプロパティを書き留めます。 Microsoft Entra ID によって BIG-IP APM 認証とバックエンド アプリケーションへの SSO のためのプロパティが発行されます。
16. **[SAML 署名証明書**] ウィンドウで、[**ダウンロード**] を選択します。
17. **フェデレーション メタデータ XML** ファイルがコンピューターに保存されます。

Note

Traffic Management Operating System (TMOS) v16 以降、SAML SLO エンドポイントは `/saml/sp/profile/redirect/slo` です。

[Image: SAML 構成の URL のスクリーンショット。]

Note

Microsoft Entra SAML 署名証明書の有効期間は 3 年です。

詳細情報: [チュートリアル: フェデレーション シングル サインオンの証明書を管理する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)

#### ユーザーとグループの割り当て

Microsoft Entra ID により、アプリケーションへのアクセスが付与されているユーザーにトークンが発行されます。 特定のユーザーとグループにアプリケーションへのアクセスを付与するには、次の手順に従います。

1. **F5 BIG-IP アプリケーションの概要**ウィンドウで、[**ユーザーとグループの割り当て**] を選択します。
2. [ **+ ユーザー/グループの追加]** を選択します。
3. 目的のユーザーとグループを選択します。
4. **割り当て**を選択します。

### BIG-IP の高度な構成

次の手順を使用して BIG-IP を構成します。

#### SAML サービス プロバイダーの設定を構成する

SAML SP の設定は、APM がレガシ アプリケーションを SAML 事前認証でオーバーレイするために使用する SAML SP プロパティを定義します。 それらを構成するには、次の手順に従います。

1. **Access**&gt;**Federation**&gt;**SAML Service Provider** を選択します。
2. **[ローカル SP サービス]** を選択します。
3. **[作成]** を選択します。

    [Image: [SAML サービス プロバイダー] タブの [作成] オプションのスクリーンショット。]
4. [ **Create New SAML SP Service]\(新しい SAML SP サービスの作成**\) で、[ **名前** ] と **[エンティティ ID**] に、定義された名前とエンティティ ID を入力します。

    [Image: [Create New SAML SP Service](新しい SAML SP サービスの作成) の下の [名前] フィールドと [エンティティ ID] フィールドのスクリーンショット。]

    Note

    エンティティ ID が公開 URL のホスト名部分と一致しない場合は、**SP 名の設定**の値が必要です。 または、エンティティ ID が通常のホスト名ベースの URL 形式でない場合は、値が必要です。
5. エンティティ ID が `urn:myvacation:contosoonline` の場合は、アプリケーションの外部スキームとホスト名を入力します。

#### 外部 IdP コネクタを構成する

SAML IdP コネクタは、BIG-IP APM が SAML IdP として Microsoft Entra ID を信頼するための設定を定義します。 これらの設定によって、SAML サービスプロバイダーが SAML IdP に接続されます。これにより、APM と Microsoft Entra ID の間にフェデレーションの信頼が確立されます。

コネクタを構成するには、次の手順に従います。

1. 新しい SAML サービス プロバイダー オブジェクトを選択します。
2. **バインド/アンバインド IdP コネクタ** を選択します。

    [Image: [SAML サービス プロバイダー] タブの [バインド解除 IdP コネクタ] オプションのスクリーンショット。]
3. **新しい IdP コネクタの作成** リストで、**メタデータから**を選択します。

    [Image: [Create New IdP Connector](新しい IdP コネクタの作成) ドロップダウン リストの [From Metadata](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/メタデータから) オプションのスクリーンショット。]
4. [ **Create New SAML IdP Connector]\(新しい SAML IdP コネクタの作成** \) ウィンドウで、ダウンロードしたフェデレーション メタデータ XML ファイルを参照します。
5. 外部 SAML IdP を表す APM オブジェクトの **ID プロバイダー名** を入力します。 たとえば、MyVacation\_EntraID があります。

    [Image: [Create New SAML IdP Connector](新しい SAML IdP コネクタの作成) の [Select File and Identity Provider name](ファイルと ID プロバイダー名の選択) フィールドのスクリーンショット。]
6. [ **新しい行の追加] を選択します**。
7. 新しい **SAML IdP コネクタを選択します**。
8. [ **更新] を**選択します。

    [Image: [更新] オプションのスクリーンショット。]
9. [ **OK] を選択します**。

    [Image: [この SP を使用する SAML IdP の編集] ダイアログのスクリーンショット。]

#### フォームベースの SSO を構成する

バックエンド アプリケーションへの FBA SSO を実行するための APM SSO オブジェクトを作成します。

Client-Initiated モードまたは BIG-IP-Initiated モードで FBA SSO を実行します。 どちらの方法でも、ユーザー名とパスワードのタグに資格情報を挿入することで、ユーザー サインオンがエミュレートされます。 フォームが送信されます。 ユーザーは、FBA アプリケーションにアクセスするためのパスワードを指定します。 パスワードはキャッシュされ、他の FBA アプリケーションに再使用されます。

1. **アクセス**&gt;**シングルサインオン**を選択します。
2. **[フォームベース]** を選択します。
3. **[作成]** を選択します。
4. **[名前]** にわかりやすい名前を入力します。 たとえば、Contoso\FBA\sso など。
5. [ **SSO テンプレートの使用**] で、[なし] を選択 **します**。
6. [ **Username Source]\(ユーザー名ソース**\) に、パスワード 収集フォームを事前入力するユーザー名ソースを入力します。 既定の `session.sso.token.last.username` は、サインインしているユーザーの Microsoft Entra ユーザー プリンシパル名 (UPN) が含まれているため、適切に機能します。
7. **[パスワード ソース]** では、ユーザー パスワードのキャッシュに使用 BIG-IP APM 変数`session.sso.token.last.password`既定値のままにします。

    [Image: [新しい SSO 構成] の [SSO テンプレートの名前と使用] オプションのスクリーンショット。]
8. **[開始 URI**] に、FBA アプリケーションのログオン URI を入力します。 要求 URI がこの URI 値と一致すると、APM のフォームベース認証で SSO が実行されます。
9. **フォーム アクション**の場合は、空白のままにします。 この結果、元の要求 URL が SSO に使用されます。
10. [ **ユーザー名のフォーム パラメーター]** に、サインイン フォームのユーザー名フィールド要素を入力します。 ブラウザー開発ツールを使用して、要素を特定します。
11. [ **パスワードのフォーム パラメーター]** に、サインイン フォームのパスワード フィールド要素を入力します。 ブラウザー開発ツールを使用して、要素を特定します。

[Image: [開始 URI]、[ユーザー名のフォーム パラメーター]、[パスワード] フィールドのフォーム パラメーターのスクリーンショット。]

[Image: ユーザー名フィールドとパスワード フィールドの吹き出しが表示されたサインイン ページのスクリーンショット。]

詳細については、「 [手動の章: シングル サインオン方法](https://techdocs.f5.com/en-us/bigip-14-1-0/big-ip-access-policy-manager-single-sign-on-concepts-configuration-14-1-0/single-sign-on-methods.html#GUID-F8588DF4-F395-4E44-881B-8D16EED91449)の techdocs.f5.com」を参照してください。

#### アクセス プロファイルを構成する

アクセス プロファイルは、アクセス ポリシー、SSO 構成、UI 設定など、BIG-IP 仮想サーバーへのアクセスを管理する APM 要素をバインドします。

1. **[アクセス**&gt;**プロファイル/ポリシー]** を選択します。
2. **アクセス プロファイル (Per-Session ポリシー)** を選択します。
3. **[作成]** を選択します。
4. **名前**を入力します。
5. [ **プロファイルの種類] で**[ **すべて**]を選択します。
6. **[SSO 構成]** で、作成した FBA SSO 構成オブジェクトを選択します。
7. [ **承認済み言語] で**、少なくとも 1 つの言語を選択します。

    [Image: [セッション ポリシーごとのアクセス プロファイル]、[新しいプロファイル] のオプションと選択のスクリーンショット。]
8. [ **Per-Session ポリシー** ] 列で、プロファイルの **[編集**] を選択します。
9. APM ビジュアル ポリシー エディターが起動します。

    [Image: [ポリシーの Per-Session] 列の [編集] オプションのスクリーンショット。]
10. **フォールバック**で、**+**記号を選択します。

[Image: フォールバックオプションの下にある APM ビジュアルポリシーエディターのプラス記号オプションのスクリーンショット。]

1. ポップアップで、[ **認証**] を選択します。
2. **[SAML 認証] を**選択します。
3. [ **項目の追加] を選択します**。

[Image: SAML 認証オプションのスクリーンショット。]

1. **SAML 認証 SP** で、**名前**を **Microsoft Entra 認証**に変更します。
2. **AAA サーバ**ドロップダウンで、作成した SAML サービス プロバイダ オブジェクトを入力します。

[Image: Microsoft Entra 認証サーバーの設定を示すスクリーンショット。]

1. **成功**したブランチで、**+**記号を選択します。
2. ポップアップで、[ **認証**] を選択します。
3. **[ログオン] ページ**を選択します。
4. [ **項目の追加] を選択します**。

[Image: [ログオン] タブの [ログオン ページ] オプションのスクリーンショット。]

1. **ユーザー名**の場合、[**読み取り専用**] 列で [**はい**] を選択します。

[Image: [プロパティ] タブの [ユーザー名] 行の [はい] オプションのスクリーンショット。]

1. サインイン ページのフォールバックで **+** 記号を選択します。 このアクションにより、SSO 資格情報マッピング オブジェクトが追加されます。
2. ポップアップで、[ **割り当て** ] タブを選択します。
3. **[SSO 資格情報マッピング] を選択します**。
4. [ **項目の追加] を選択します**。

    [Image: [割り当て] タブの [SSO 資格情報マッピング] オプションのスクリーンショット。]
5. **変数割り当て時: SSO 資格情報マッピング**では、既定の設定をそのまま使用します。
6. **[保存] を選択します**。

    [Image: [プロパティ] タブの [保存] オプションのスクリーンショット。]
7. 上部の **[拒否** ] ボックスで、リンクを選択します。
8. **成功した**ブランチが **許可** に変わります。
9. **[保存] を選択します**。

##### (省略可能) 属性マッピングを構成する

LogonID\_Mapping 構成を追加できます。 この結果、BIG-IP アクティブ セッション一覧には、セッション番号ではなく、サインインしているユーザーの UPN が含まれます。 この情報は、ログの分析やトラブルシューティングに使用します。

1. **SAML 認証成功**ブランチで、**+**記号を選択します。
2. ポップアップで、[ **割り当て]** を選択します。
3. [ **変数の割り当て] を選択します**。
4. [ **項目の追加] を選択します**。

    [Image: [割り当て] タブの [変数の割り当て] オプションのスクリーンショット。]
5. [ **プロパティ** ] タブで、[ **名前]** を入力します。 たとえば、「LogonID\_Mapping」と入力します。
6. [ **変数の割り当て]** で、[ **新しいエントリの追加]** を選択します。
7. **[変更**] を選択します。

    [Image: [新しいエントリの追加] オプションと [変更] オプションのスクリーンショット。]
8. **カスタム変数**の場合は、`session.logon.last.username`を使用します。
9. セッション変数には、`session.saml.last.identity` を使用します。
10. [ **完了] を選択します**。
11. **[保存] を選択します**。
12. [ **アクセス ポリシーの適用] を選択します**。
13. ビジュアル ポリシー エディターを閉じます。

[Image: アクセス ポリシー適用画面のスクリーンショット。]

#### バックエンド プールを構成する

BIG-IP でクライアント トラフィックを正しく転送できるようにするには、アプリケーションをホストするバックエンド サーバーを表す BIG-IP ノード オブジェクトを作成します。 その後、そのノードを BIG-IP サーバー プールに配置します。

1. [**ローカル トラフィック**&gt;**プールを選択します**]。
2. **[プールの一覧] を選択します**。
3. **[作成]** を選択します。
4. サーバー プール オブジェクトの **名前** を入力します。 たとえば、「MyApps\_VMs」と入力します。

    [Image: [新しいプール] の [名前] フィールドのスクリーンショット。]
5. **[ノード名]** に、サーバーの表示名を入力します。 このサーバーは、バックエンド Web アプリケーションをホストします。
6. [ **アドレス]** に、アプリケーション サーバーのホスト IP アドレスを入力します。
7. **サービス ポート**の場合は、アプリケーションがリッスンしている HTTP/S ポートを入力します。

    [Image: [ノード名]、[アドレス]、[サービス ポート] フィールド、[追加] オプションのスクリーンショット。]

    Note

    正常性モニターには、この記事で説明されていない構成が必要です。 support.f5.com にアクセスし、BIG-IP DNS システム用の HTTP 正常性モニター要求の書式設定の概要である K13397 を参照してください。

#### 仮想サーバーを構成する

仮想サーバーは、仮想 IP アドレスで表される BIG-IP データ プレーン オブジェクトです。 サーバーは、アプリケーションへのクライアント要求をリッスンします。 受信したトラフィックはすべて処理され、仮想サーバーに関連付けられている APM アクセス プロファイルに照らして評価されます。 トラフィックはポリシーに従って送信されます。

仮想サーバーを構成するには、次の手順に従います。

1. [ **ローカル トラフィック**&gt;**仮想サーバー] を選択します**。
2. **[仮想サーバーの一覧] を選択します**。
3. **[作成]** を選択します。
4. **名前**を入力します。
5. **[宛先アドレス/マスク]** で、[**ホスト**] を選択し、IPv4 または IPv6 アドレスを入力します。 このアドレスは、公開されたバックエンド アプリケーションのクライアント トラフィックを受信します。
6. **[サービス ポート]** で、[**ポート**] を選択し、「**443**」と入力して、[**HTTPS**] を選択します。

    [Image: [名前]、[宛先アドレス]、[サービス ポート] の各フィールドとオプションのスクリーンショット。]
7. **[HTTP プロファイル (クライアント)]**で、[**http**] を選択します。
8. **SSL プロファイル (クライアント) の**場合は、作成したプロファイルを選択するか、テスト用の既定値のままにします。 このオプションを使用すると、トランスポート層セキュリティ (TLS) 用の仮想サーバーで HTTPS 経由でサービスを公開できます。

    [Image: HTTP プロファイル クライアントと SSL プロファイル クライアントのオプションのスクリーンショット。]
9. [ **送信元アドレス変換]** で、[ **自動マップ**] を選択します。

    [Image: [ソース アドレス変換] の [自動マップ] 選択のスクリーンショット。]
10. [ **アクセス ポリシー**] の [ **アクセス プロファイル** ] ボックスに、作成した名前を入力します。 このアクションにより、Microsoft Entra SAML 事前認証プロファイルと FBA SSO ポリシーが仮想サーバーにバインドされます。

[Image: [アクセス ポリシー] の [アクセス プロファイル] エントリのスクリーンショット。]

1. [ **リソース**] の **[既定のプール**] で、作成したバックエンド プール オブジェクトを選択します。
2. [ **完了] を選択します**。

[Image: [リソース] の [既定のプール] オプションのスクリーンショット。]

#### セッション管理の設定を構成する

BIG-IP セッション管理設定では、セッションの終了と継続の条件を定義します。 この領域にポリシーを作成します。

1. **アクセス ポリシー**に移動します。
2. **[アクセス プロファイル] を選択します**。
3. **[アクセス プロファイル] を選択します**。
4. 一覧から、アプリケーションを選択します。

Microsoft Entra にシングル ログアウト URI 値を定義した場合は、MyApps からの IdP-Initiated サインアウトにより、クライアントと BIG-IP APM のセッションが終了します。 インポートしたアプリケーションのフェデレーション メタデータ XML ファイルは、SP-Initiated サインアウトのための Microsoft Entra SAML エンドポイントを APM に提供します。APM がユーザー サインアウトに正しく応答することを確認してください。

BIG-IP Web ポータルがない場合、ユーザーはサインアウトするよう APM に指示できません。ユーザーがアプリケーションからサインアウトした場合、BIG-IP では認識されません。 アプリケーション セッションは、SSO を使用して復帰できます。 SP-Initiated サインアウトでは、セッションが安全に終了することを確認してください。

SLO 関数をアプリケーションの **サインアウト** ボタンに追加できます。 この機能を使用すると、クライアントは Microsoft Entra SAML サインアウト エンドポイントにリダイレクトされます。 SAML サインアウト エンドポイントを見つけるには、[ **アプリの登録] &gt; [エンドポイント]** に移動します。

アプリを変更できない場合は、BIG-IP でアプリのサインアウト呼び出しをリッスンして SLO をトリガーしてください。

詳細情報:

- [K42052145: URI で参照されるファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)
- [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)

### 公開アプリケーション

アプリケーションは公開され、アプリの URL または Microsoft ポータルにより SHA を使用してアクセスできます。

アプリケーションは、条件付きアクセスのターゲット リソースとして表示されます。 詳細情報: [条件付きアクセス ポリシーの構築](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies)。

セキュリティを強化するには、BIG-IP 経由のパスを適用して、アプリケーションへの直接アクセスをブロックします。

### テスト

1. ユーザーは、ブラウザーを使用してアプリケーションの外部 URL に接続するか、マイ アプリでアプリケーションのアイコンを選択します。
2. ユーザーは、Microsoft Entra ID を認証します。
3. ユーザーは、アプリケーションの BIG-IP エンドポイントにリダイレクトされます。
4. パスワードのプロンプトが表示されます。
5. APM により、Microsoft Entra ID からの UPN でユーザー名が入力されます。 ユーザー名は、セッションの一貫性のために読み取り専用です。 このフィールドは、必要に応じて非表示にしてください。
6. 情報が送信されます。
7. ユーザーは、アプリケーションにサインインしました。

### トラブルシューティング

トラブルシューティングの際は次の情報を考慮してください。

- BIG-IP は、URI でサインイン フォームを解析するときに FBA SSO を実行します

    - BIG-IP は、構成からユーザー名とパスワードの要素タグをシークします
- 要素タグの一貫性を確認してください。そうしないと、SSO は失敗します。
- 動的に生成される複雑なフォームでは、サインイン フォームを理解するために開発ツールの分析が必要になる場合があります
- クライアント Initiation は、複数のフォームがあるサインイン ページに適しています

    - フォーム名を選択し、JavaScript フォーム ハンドラー ロジックをカスタマイズできます
- FBA SSO のどちらの方法でも、フォーム操作を非表示にすれば、ユーザー エクスペリエンスとセキュリティが最適化されます。

    - 資格情報が挿入されているかどうかを検証できます
    - Client-Initiated モードでは、SSO プロファイルでフォームの自動送信を無効にします
    - 開発ツールを使用して、サインイン ページの表示を妨げる 2 つのスタイル プロパティを無効にします

    [Image: [プロパティ] ページのスクリーンショット。]

#### ログの詳細度を上げる

BIG-IP ログには、認証と SSO の問題を分離するための情報が含まれています。 次の手順を実行して、ログの詳細レベルを上げます。

1. **アクセス ポリシー**&gt;**Overview** に移動します。
2. **[イベント ログ]** を選択します。
3. [ **設定] を選択します**。
4. 発行されたアプリケーションの行を選択します。
5. [ **編集] を選択します**。
6. [ **アクセス システム ログ] を選択します**。
7. SSO の一覧で [ **デバッグ**] を選択します。
8. [ **OK] を選択します**。
9. 問題を再現します。
10. ログを確認します。

設定を元に戻します。そうしないと、過剰なデータが提供されます。

#### BIG-IP のエラー メッセージ

Microsoft Entra 事前認証後に BIG-IP のエラーが表示される場合、Microsoft Entra ID と BIG-IP の SSO に関する問題が発生している可能性があります。

1. **Access**&gt;**Overview** に移動します。
2. [ **レポートへのアクセス**] を選択します。
3. 直近 1 時間のレポートを実行します。
4. ログに手がかりがないか確認します。

セッションの **[セッション変数の表示]** リンクを使用して、APM が予想される Microsoft Entra 要求を受け取るかどうかを判断します。

#### BIG-IP エラー メッセージなし

BIG-IP エラー メッセージが表示されない場合、問題はバックエンド要求、または BIG-IP からアプリケーションへの SSO に関連している可能性があります。

1. アクセス **ポリシー**&gt;**概要** を選択します。
2. **[アクティブなセッション] を選択します**。
3. アクティブなセッション リンクを選びます。

この場所の **[変数の表示]** リンクを使用すると、APM が正しいユーザー ID とパスワードを取得できない場合に、根本原因を特定できます。

詳細については、「 [手動章: セッション変数](https://techdocs.f5.com/en-us/bigip-16-1-0/big-ip-access-policy-manager-visual-policy-editor/session-variables.html)」の techdocs.f5.com を参照してください。

### リソース

- [「手動の章: 認証](https://techdocs.f5.com/kb/en-us/products/big-ip_apm/manuals/product/apm-authentication-single-sign-on-12-1-0/2.html)」の techdocs.f5.com に移動する
- [パスワードレス認証](https://www.microsoft.com/security/business/identity/passwordless)
- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [リモート作業を有効にするゼロ トラスト フレームワーク](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-header-advanced"} -->
## ヘッダーベースのシングル サインオン用に F5 BIG-IP Access Policy Manager を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-header-advanced
- Service: entra-id / enterprise-apps
- Article date: 2024-04-18
- Summary: F5 BIG-IP Access Policy Manager (APM) と Microsoft Entra SSO を構成してヘッダーベースの認証を行う方法について説明します

F5 BIG-IP 詳細構成を使用して、ヘッダーベースのアプリケーションへのシングル サインオン (SSO) によるセキュア ハイブリッド アクセス (SHA) を実装する方法について学習します。 BIG-IP 公開アプリケーションと Microsoft Entra 構成には、次のようなメリットがあります。

- Microsoft Entra の事前認証および条件付きアクセスによるゼロ トラスト ガバナンスの強化
    - 「[条件付きアクセスとは」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を参照してください。
    - ゼロトラスト[セキュリティ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/zero-trust)を参照
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- マネージド ID と 1 つのコントロール プレーンからのアクセス
    - [Microsoft Entra 管理センター](https://entra.microsoft.com)を参照してください

詳細情報:

- [F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [エンタープライズ アプリケーションの SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)

### シナリオの説明

このシナリオでは、HTTP 認可ヘッダーを使用して保護されたコンテンツへのアクセスを制御するレガシ アプリケーションがあるとします。 可能であれば、Microsoft Entra ID がアプリケーション アクセスを管理します。 ただし、レガシ アプリケーションには最新の認証プロトコルがありません。 最新化には労力と時間がかかりますが、ダウンタイムのコストとリスクが発生します。 代わりに、パブリック インターネットと内部アプリケーションの間に BIG-IP をデプロイして、アプリケーションへの受信アクセスをゲートします。

アプリケーションの手前に BIG-IP を置くことにより、Microsoft Entra の事前認証およびヘッダーベース SSO をサービスにオーバーレイできます。 この構成により、アプリケーションのセキュリティ態勢が向上します。

### シナリオのアーキテクチャ

このシナリオのためのセキュア ハイブリッド アクセス ソリューションは、次のもので構成されます。

- **アプリケーション** - BIG-IP 公開サービスは Microsoft Entra SHA によって保護される予定です。
- **Microsoft Entra ID**- ユーザー資格情報、条件付きアクセス、および BIG-IP への SSO を検証するセキュリティ アサーション マークアップ言語 (SAML) ID プロバイダー (IdP)
    - SSO を使用すると、Microsoft Entra ID により、ユーザー識別子を含む必要なセッション属性が BIG-IP に提供されます
- **BIG-IP** - バックエンド アプリケーションへのヘッダー ベースの SSO の前に、アプリケーションへのリバース プロキシと SAML サービス プロバイダー (SP) による SAML IdP への認証の委任

次の図は、Microsoft Entra ID、BIG-IP、APM、アプリケーションを使用したユーザー フローを示しています。

[Image: Microsoft Entra ID、BIG-IP、APM、およびアプリケーションを使用したユーザー フローの図]

1. ユーザーがアプリケーションの SAML SP エンドポイント (BIG-IP) に接続します。
2. BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトします。
3. Microsoft Entra がユーザーを事前認証し、ConditionalAccess ポリシーを適用します。
4. ユーザーは BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行されます。
5. BIG-IP によって、Microsoft Entra 属性がアプリケーションへの要求のヘッダーとして挿入されます。
6. アプリケーションが要求を承認し、ペイロードを返します。

### 前提条件

このシナリオでは、以下が必要です。

- Azure サブスクリプションとして
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を入手してください
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか
- BIG-IP、または Azure に BIG-IP Virtual Edition (VE) をデプロイします
    - [Azure での F5 BIG-IP Virtual Edition 仮想マシンのデプロイに](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)関するページを参照してください
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90 日間の BIG-IP 全機能試用版。 無料試用版をご覧 [ください](https://www.f5.com/trial/big-ip-trial.php)。
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID
    - [Microsoft Entra Connect Sync: 同期の理解とカスタマイズ](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)
- HTTPS でサービスを公開するための SSL 証明書。または、テスト中は既定の証明書を使用します
    - SSL [プロファイル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile)を参照してください
- ヘッダーベースのアプリケーション、またはテスト用の IIS ヘッダー アプリ

### BIG-IP の構成方法

次の手順は、SHA を実装するための柔軟な方法である高度な構成方法です。 BIG-IP 構成オブジェクトを手動で作成します。 ガイド付き構成テンプレートに含まれていないシナリオでは、この方法を使用します。

Note

サンプルの文字列または値は、実際の環境のものに置き換えてください。

### Microsoft Entra ギャラリーから F5 BIG-IP を追加する

SHA を実装するには、最初の手順として、BIG-IP APM と Microsoft Entra ID の間に SAML フェデレーション信頼を設定します。 この信頼により、公開サービスへのアクセスを許可する BIG-IP が、あらかじめ事前認証と条件付きアクセスを Microsoft Entra ID に引き継ぐための統合が確立されます。

詳細情報: [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **[Entra ID]**&gt;**[エンタープライズ アプリ]**&gt;**[すべてのアプリケーション]** を参照します。
3. 上部のリボンで、[ **+ 新しいアプリケーション**] を選択します。
4. ギャラリーで **F5** キーを検索します。
5. **F5 BIG-IP APM Microsoft Entra ID 統合を**選択します。
6. アプリケーション名を入力 **します**。
7. [ **追加/作成] を選択します**。
8. 名前にはサービスが反映されます。

### Microsoft Entra SSO を構成する

1. 新しい **F5** アプリケーション プロパティが表示されます
2. [**管理**&gt;**シングルサインオン**] を選択する
3. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。
4. シングル サインオンの設定を保存するかどうかを確認するメッセージをスキップします。
5. [ **いいえ] を選択します。後で保存します**。
6. [ **SAML でシングル サインオンを設定**する] で、[ **基本的な SAML 構成]** で **ペン** アイコンを選択します。
7. **識別子** URL を、BIG-IP 発行されたサービス URL に置き換えます。 たとえば、`https://mytravel.contoso.com` のように指定します。
8. **応答 URL を**繰り返し、APM SAML エンドポイント パスを含めます。 たとえば、`https://mytravel.contoso.com/saml/sp/profile/post/acs` のように指定します。

    Note

    この構成では、SAML フローは IdP モードで動作します。アプリケーションの BIG-IP サービス エンドポイントへのリダイレクトの前に、Microsoft Entra ID がユーザーに SAML アサーションを発行します。 BIG-IP APM では、IdP モードと SP モードがサポートされています。
9. **ログアウト URI** の場合は、サービス ホスト ヘッダーの先頭に APM シングル ログアウト (SLO) エンドポイント BIG-IP を入力します。 SLO URI を使用すると、Microsoft Entra のサインアウト後にユーザー BIG-IP APM セッションが終了します (例: `https://mytravel.contoso.com/saml/sp/profile/redirect/slr`)。

    [Image: 識別子、応答 URL、サインオン URL などの基本的な SAML 構成入力のスクリーンショット。]

    Note

    Traffic Management Operating System (TMOS) v16 以降、SAML SLO エンドポイントは `/saml/sp/profile/redirect/slo` に変更されました。
10. **[保存] を選択します**。
11. SAML 構成を終了します。
12. SSO テスト プロンプトをスキップします。
13. **[ユーザー属性] & [要求] &gt; + [新しい要求の追加]** を編集するには、**ペン** アイコンを選択します。
14. **[名前]** で [**Employeeid**] を選択します。
15. **[ソース] 属性**で **user.employeeid** を選択します。
16. **[保存] を選択する**

[Image: [要求の管理] ダイアログの [名前] 属性と [ソース] 属性の入力のスクリーンショット。]

1. [ **+ グループ要求の追加] を選択する**
2. **アプリケーションに割り当てられたグループ**&gt;**Source Attribute**&gt;**sAMAccountName** を選択します。

[Image: [グループ要求] ダイアログの [ソース属性] の入力のスクリーンショット。]

1. [構成の **保存]** を選択します。
2. ビューを閉じます。
3. **[ユーザー属性] および [要求**] セクションのプロパティを確認します。 Microsoft Entra ID によって BIG-IP APM 認証とバックエンド アプリケーションへの SSO のためのユーザー プロパティが発行されます。

[Image: ユーザー属性とクレーム情報 (姓、電子メール アドレス、ID など) のスクリーンショット。]

Note

BIG-IP 公開アプリケーションがヘッダーとして想定するその他の要求を追加します。 Microsoft Entra ID では、より明確に定義された要求が発行されます。 要求を発行する前に、Microsoft Entra ID でディレクトリ メンバーシップとユーザー オブジェクトを定義します。 「 [Microsoft Entra ID を使用してアプリケーションのグループ要求を構成する」を](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-fed-group-claims)参照してください。

1. [ **SAML 署名証明書** ] セクションで、[ **ダウンロード**] を選択します。
2. **フェデレーション メタデータ XML** ファイルは、コンピューターに保存されます。

Microsoft Entra ID によって作成された SAML 署名証明書の有効期間は 3 年です。

#### Microsoft Entra 承認

既定では、Microsoft Entra ID はアプリケーションへのアクセスが付与されているユーザーにトークンを発行します。

1. アプリケーションの構成ビューで、[ **ユーザーとグループ**] を選択します。
2. [ **+ ユーザーの追加]** を選択し、[ **割り当ての追加]** で [ **ユーザーとグループ**] を選択します。
3. [ **ユーザーとグループ** ] ダイアログで、ヘッダー ベースのアプリケーションにアクセスする権限を持つユーザー グループを追加します。
4. **[選択]** を選択します。
5. **割り当て**を選択します。

Microsoft Entra SAML フェデレーション信頼が完了しました。 次に、BIG-IP APM を設定して Web アプリケーションを発行し、プロパティを構成して SAML 事前認証の信頼を完了します。

### 詳細な構成

SAML、ヘッダー SSO、アクセス プロファイルなどを構成するには、次のセクションを使用します。

#### [SAML 構成]

公開アプリケーションの Microsoft Entra ID とのフェデレーションを実行するには、BIG-IP SAML サービス プロバイダーと、対応する SAML IdP オブジェクトを作成します。

1. **「アクセス**&gt;**フェデレーション**&gt;**SAML サービスプロバイダー**&gt;**ローカルSPサービス**&gt;**の作成**」を選択します。

    [Image: [SAML サービス プロバイダー] タブの [作成] オプションをスクリーンショット。]
2. **名前**を入力してください。
3. Microsoft Entra ID で定義されている **エンティティ ID** を入力します。

    [Image: [新しい SAML SP サービスの作成] ダイアログの [名前] と [エンティティ ID] 入力のスクリーンショット。]
4. **[SP 名の設定]** では、エンティティ ID が発行された URL のホスト名と一致しない場合は選択するか、通常のホスト名ベースの URL 形式でない場合は選択します。 エンティティ ID が `urn:mytravel:contosoonline` の場合は、外部スキームとアプリケーションのホスト名を指定します。
5. 下にスクロールして、新しい SAML SP オブジェクトを選択します。
6. [ **IdP コネクタのバインド/バインド解除**] を選択します。

    [Image: [SAML サービス プロバイダー] タブの [バインド解除 IdP コネクタ] オプションのスクリーンショット。]
7. [ **Create New IdP Connector]\(新しい IdP コネクタの作成**\) を選択します。
8. ドロップダウンから[ **メタデータから**]を選択します。

    [Image: [Create New IdP Connection](新しい IdP 接続の作成) ドロップダウン メニューの [From Metadata](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/メタデータから) オプションのスクリーンショット。]
9. ダウンロードしたフェデレーション メタデータ XML ファイルを参照します。
10. 外部 SAML IdP の APM オブジェクトの **ID プロバイダー名** を入力します。 たとえば、`MyTravel_EntraID` のように指定します。

[Image: [Create New SAML IdP Connector](新しい SAML IdP コネクタの作成) の [Select File and Identity Provider Name](ファイルと ID プロバイダー名の選択) 入力のスクリーンショット。]

1. [ **新しい行の追加] を選択します**。
2. 新しい **SAML IdP コネクタを選択します**。
3. [ **更新] を**選択します。

[Image: [SAML IdP コネクタ] の [更新] オプションのスクリーンショット。]

1. [ **OK] を選択します**。

[Image: 保存された設定のスクリーンショット]

#### ヘッダー SSO の構成

APM SSO オブジェクトを作成します。

1. **アクセス**&gt;**プロファイル/ポリシー**&gt;**Per-Request ポリシー**&gt;**作成** を選択します。
2. **名前**を入力してください。
3. 少なくとも 1 つの **承認済み言語を追加します**。
4. [ **完了] を選択します。**

    [Image: [名前] と [承認済み言語] の入力のスクリーンショット。]
5. 新しい要求ごとのポリシーの場合は、[ **編集]** を選択します。

    [Image: [要求ごとのポリシー] 列の [編集] オプションのスクリーンショット。]
6. ビジュアル ポリシー エディターが起動します。
7. **フォールバック**で、**+**記号を選択します。

    [Image: フォールバックのプラス オプションのスクリーンショット。]
8. [**汎用**] タブで、[**HTTP ヘッダー**] &gt;**[項目の追加]** を選択します。

    [Image: [HTTP ヘッダー] オプションのスクリーンショット。]
9. [ **新しいエントリの追加] を選択します**。
10. 3 つの HTTP およびヘッダー変更エントリを作成します。
11. **[ヘッダー名]** に「**upn**」と入力します。
12. **[ヘッダー値]** ** に、「{session.saml.last.identity}」%** 入力します。
13. **[ヘッダー名]** に「**employeeid**」と入力します。
14. **[ヘッダー値]** ** に、「{session.saml.last.attr.name.employeeid}」%** 入力します。
15. **[ヘッダー名]** に「group\_authz」と入力**します**。
16. **[ヘッダー値]** ** に「{session.saml.last.attr.name.`http://schemas.microsoft.com/ws/2008/06/identity/claims/groups`}」と**%入力します。

Note

中かっこ内の APM セッション変数では、大文字と小文字が区別されます。 属性は小文字で定義することをお勧めします。

[Image: [プロパティ] タブの [HTTP ヘッダーの変更] の下にあるヘッダー入力のスクリーンショット。]

1. **[保存] を選択します**。
2. ビジュアル ポリシー エディターを閉じます。

[Image: ビジュアル ポリシー エディターのスクリーンショット。]

#### アクセス プロファイル構成

アクセス プロファイルは、アクセス ポリシー、SSO 構成、UI 設定など、BIG-IP 仮想サーバーへのアクセスを管理する多くの APM 要素をバインドします。

1. **Access**&gt;**Profiles / Policies**&gt;**Access Profiles (Per-Session Policies)**&gt;**Create** を選択します。
2. **[名前]** に「**MyTravel」**と入力します。
3. [ **プロファイルの種類] で**[ **すべて**]を選択します。
4. [ **承認済み言語] で**、少なくとも 1 つの言語を選択します。
5. **[完了]** を選択します。

    [Image: [名前]、[プロファイルの種類]、[承認済み言語] のエントリのスクリーンショット。]
6. 作成したセッションごとのプロファイルで、[ **編集]** を選択します。

    [Image: [ポリシーの Per-Session] 列の [編集] オプションのスクリーンショット。]
7. ビジュアル ポリシー エディターが起動します。
8. [フォールバック] の下で **+** 記号を選択します。

    [Image: プラスオプションのスクリーンショット。]
9. [ **認証**&gt;**SAML 認証**&gt;**項目の追加を選択します**。

    [Image: [認証] タブの [SAML 認証] オプションのスクリーンショット。]
10. **SAML 認証 SP** 構成の場合は、**AAA サーバー**のドロップダウンから、作成した SAML SP オブジェクトを選択します。
11. **[保存] を選択します**。

[Image: AAA サーバーの選択のスクリーンショット。]

#### 属性マッピング

以下の手順は省略可能です。 LogonID\_Mapping 構成では、BIG-IP アクティブ セッションの一覧に、セッション番号ではなく、サインインしているユーザー プリンシパル名 (UPN) が含まれます。 このデータは、ログの分析やトラブルシューティングを行うときに使用します。

1. SAML 認証 **成功** ブランチで、 **+** 記号を選択します。

    [Image: SAML 認証成功ブランチのプラス記号のスクリーンショット。]
2. ポップアップで、[ **割り当て**&gt;**可変割り当て**&gt;**項目の追加**を選択します。

    [Image: [割り当て] タブの [変数の割り当て] オプションのスクリーンショット。]
3. **名前**を入力する
4. [ **変数の割り当て** ] セクションで、[ **新しいエントリの追加**&gt;**change**] を選択します。 たとえば、「LogonID\_Mapping」と入力します。

    [Image: [新しいエントリの追加と変更] オプションのスクリーンショット]
5. **カスタム変数**の場合は、**session.saml.last.identity を**設定します。
6. **セッション変数**の場合は、**session.logon.last.username を設定します**。
7. [ **完了] を選択します**。
8. **[保存] を選択します**。
9. アクセス **ポリシー成功** ブランチで、 **拒否** ターミナルを選択します。
10. [許可] を選択します。
11. **[保存] を選択します**。
12. [ **アクセス ポリシーの適用] を選択します**。
13. ビジュアル ポリシー エディターを閉じます。

#### バックエンドプール構成

BIG-IP がクライアント トラフィックを正しく転送できるようにするには、アプリケーションをホストしているバックエンド サーバーを表す APM ノード オブジェクトを作成します。 ノードを APM プールに配置します。

1. **ローカル トラフィック &gt; プール &gt; プールリスト &gt; 作成**を選択します。
2. サーバー プール オブジェクトの場合は、名前を入力 **します**。 たとえば、「MyApps\_VMs」と入力します。

    [Image: [アクセス ポリシーの適用] のスクリーンショット。]
3. プール メンバー オブジェクトを追加します。
4. **[ノード名]** に、バックエンド Web アプリケーションをホストするサーバーの名前を入力します。
5. [ **アドレス]** に、アプリケーションをホストしているサーバーの IP アドレスを入力します。
6. **サービス ポート**の場合は、アプリケーションがリッスンしている HTTP/S ポートを入力します。
7. **追加**を選択します。

    [Image: [ノード名]、[アドレス]、[サービス ポート]、[追加] オプションの入力のスクリーンショット。]

    Note

    詳細については、「 [K13397 の my.f5.com: BIG-IP DNS システムの HTTP 正常性モニター要求の書式設定の概要](https://support.f5.com/csp/article/K13397)」を参照してください。

### 仮想サーバーの構成

仮想サーバーは、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表される BIG-IP データ プレーン オブジェクトです。 受信したトラフィックが処理され、仮想サーバーに関連付けられている APM アクセス プロファイルに照らして評価されます。 トラフィックはポリシーに従って送信されます。

1. **ローカル トラフィック**&gt;**仮想サーバー**&gt;**仮想サーバーの一覧**&gt;**作成**を選択します。
2. 仮想サーバー名を入力 **します**。
3. **宛先アドレス/マスク**の場合は、[**ホスト**] を選択します。
4. クライアント トラフィックを受信するために BIG-IP に割り当てられる未使用の IP (IPv4 または IPv6) を入力します。
5. **[サービス ポート**] で、[**ポート**]、[**443**]、[**HTTPS**] の順に選択します。

    [Image: [名前]、[宛先アドレス マスク]、[サービス ポート] のエントリのスクリーンショット。]
6. **[HTTP プロファイル (クライアント)]**で、[**http**] を選択します。
7. **SSL プロファイル (クライアント) の**場合は、作成したクライアント SSL プロファイルを選択するか、テスト用の既定値のままにします。

    [Image: HTTP プロファイル クライアントと SSL プロファイル クライアントのエントリのスクリーンショット。]
8. [ **送信元アドレス変換]** で、[ **自動マップ**] を選択します。

    [Image: [送信元アドレス変換] オプションのスクリーンショット。]
9. **[アクセス ポリシー]** で、先ほど作成した**アクセス プロファイル**を選択します。 このアクションにより、Microsoft Entra SAML 事前認証プロファイルとヘッダー SSO ポリシーが仮想サーバーにバインドされます。
10. **Per-Requestポリシー**で、**SSO\_Headers**を選択します。

[Image: アクセス プロファイルと要求前ポリシーのエントリのスクリーンショット。]

1. **[既定のプール]** で、作成したバックエンド プール オブジェクトを選択します。
2. [ **完了] を選択します**。

[Image: [リソース] の [既定のプール] オプションのスクリーンショット。]

### セッションの管理

BIG-IP セッション管理設定を使用して、ユーザー セッションの終了または継続の条件を定義します。 **アクセス ポリシー**&gt;**Access プロファイル**を使用してポリシーを作成します。 一覧からアプリケーションを選択します。

SLO 機能に関して、Microsoft Entra ID で SLO URI を定義すると、MyApps ポータルからの IdP によって開始されたサインアウトによって、クライアントと BIG-IP APM の間のセッションが終了することが保証されます。 インポートされたアプリケーション フェデレーションの metadata.xml は、SP によって開始されるサインアウトの Microsoft Entra SAML サインアウト エンドポイントを APM に提供します。そのため、APM はユーザーがいつサインアウトしたかを把握できます。

BIG-IP Web ポータルがない場合、ユーザーはサインアウトするよう APM に指示できません。ユーザーがアプリケーションからサインアウトした場合、BIG-IP ではアクションが認識されません。 アプリケーション セッションは、SSO を使用して復帰できます。 そのため、SP によって開始されるサインアウトは慎重に検討する必要があります。

セッションが安全に終了するようにするには、SLO 関数をアプリケーションの **[サインアウト** ] ボタンに追加します。 そこからクライアントを Microsoft Entra SAML のサインアウト エンドポイントにリダイレクトできるようにします。 テナントの SAML サインアウト エンドポイントについては、「 **アプリの登録**&gt;**Endpoints**」を参照してください。

アプリを変更できない場合は、BIG-IP でアプリのサインアウト呼び出しをリッスンして SLO をトリガーするようにできます。 詳細については、以下を参照してください。

- support.f5.comのK42052145: [URI参照ファイル名に基づく自動セッション終了（ログアウト）の構成](https://support.f5.com/csp/article/K42052145)にアクセスしてください
- my.f5.com にアクセスして、[K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056) をご覧ください。

### 展開

1. [ **デプロイ]** を選択して設定をコミットします。
2. アプリケーションがテナントに表示されていることを確認します。
3. アプリケーションが発行され、その URL、または Microsoft ポータルで、SHA を介してアクセスできるようになります。

### テスト

ユーザーとして以下のテストを実行します。

1. アプリケーションの外部 URL を選択するか、MyApps ポータルでアプリケーション アイコンを選択します。
2. Microsoft Entra ID に対して認証します。
3. アプリの BIG-IP 仮想サーバーにリダイレクトされ、SSO を使用してサインインされます。
4. 挿入されたヘッダー出力は、ヘッダーベースのアプリケーションによって表示されます。

セキュリティを強化するには、BIG-IP 経由のパスを適用して、アプリケーションへの直接アクセスをブロックします。

### トラブルシューティング

トラブルシューティングには、次のガイダンスを使用します。

#### ログの冗長性

BIG-IP ログには、認証や SSO の問題の切り分けに役立つ情報があります。 次の手順を実行して、ログの詳細レベルを上げます。

1. **アクセス ポリシー**&gt;**Overview**&gt;**Event ログ**に移動します。
2. [ **設定] を選択します**。
3. 発行されたアプリケーションの行を選択します。
4. [ **編集]**&gt;**[システム ログにアクセス]**を選択します。
5. SSO の一覧から [ **デバッグ**] を選択します。
6. [ **OK] を選択します**。
7. 問題を再現します。
8. ログを確認します。
9. 完了したら、設定を元に戻します。

#### BIG-IP のエラー メッセージ

リダイレクト後に BIG-IP のエラーが表示される場合は、Microsoft Entra ID から BIG-IP への SSO に関連する問題が発生している可能性があります。

1. **アクセス ポリシー**&gt;**Overview** に移動します。
2. [ **レポートへのアクセス**] を選択します。
3. 直近 1 時間のレポートを実行します。
4. ログに手がかりがないか確認します。
5. セッションの場合は、[ **セッション変数の表示** ] リンクを選択します。
6. APM が Microsoft Entra ID から期待される要求を受け取ることを確認します。

#### BIG-IP エラー メッセージなし

BIG-IP のエラー メッセージが表示されない場合、問題は、BIG-IP からバックエンド アプリケーションへの SSO に関係している可能性が高くなります。

1. **アクセス ポリシー**&gt;**Overview** に移動します。
2. **[アクティブなセッション] を選択します**。
3. アクティブなセッションのリンクを選択します。
4. [ **変数の表示** ] リンクを選択して、SSO の問題を特定します。
5. BIG-IP APM が失敗するか成功するかを確認して、正しいユーザーとドメインの識別子を取得します。

詳細情報:

- [APM 変数の割り当て例](https://devcentral.f5.com/s/articles/apm-variable-assign-examples-1107)の devcentral.f5.com に移動する
- BIG-IP [アクセス ポリシー マネージャー: ビジュアル ポリシー エディターの techdocs.f5.com](https://techdocs.f5.com/en-us/bigip-16-1-0/big-ip-access-policy-manager-visual-policy-editor.html) に移動する

### リソース

- [パスワードレス認証](https://www.microsoft.com/security/business/identity/passwordless)
- [条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)
- [リモート作業を有効にするゼロ トラスト フレームワーク](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-headers-easy-button"} -->
## ヘッダーベース SSO 用に F5 BIG-IP Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-headers-easy-button
- Service: entra-id / enterprise-apps
- Article date: 2024-04-18
- Summary: F5 BIG-IP Easy Button Guided Configuration を使用して、ヘッダーベースのアプリケーションへのシングルサインオン (SSO) によるセキュリティで保護されたハイブリッド アクセス (SHA) を実装する方法について説明します。

ヘッダー ベースのアプリケーションを、Microsoft Entra ID と F5 BIG-IP Easy Button Guided Configuration v16.1 で保護する方法について説明します。

BIG-IP と Microsoft Entra ID の統合には、以下のように数多くの利点があります。

- Microsoft Entra の事前認証および条件付きアクセスによるゼロ トラスト ガバナンスの強化
    - 「[条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)」を参照してください。
    - 「[ゼロ トラスト セキュリティ](https://learn.microsoft.com/ja-jp/azure/security/fundamentals/zero-trust)」を参照してください
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- マネージド ID と 1 つのコントロール プレーンからのアクセス
    - 「[Microsoft Entra 管理センター](https://entra.microsoft.com)」を参照

詳細情報:

- [F5 BIG-IP を Microsoft Entra ID と統合する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)
- [エンタープライズ アプリケーションの SSO を有効にする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/add-application-portal-setup-sso)

### シナリオの説明

このシナリオでは、保護されたコンテンツへのアクセスを管理するために HTTP Authorization ヘッダーを使用するレガシ アプリケーションを対象にしています。 レガシ アプリケーションは、Microsoft Entra ID との直接統合をサポートする最新のプロトコルに対応していません。 最新化にはコストも時間もかかり、ダウンタイムのリスクが生じます。 代わりに、プロトコルの切り換えと共に、F5 BIG IP Application Delivery Controller (ADC) を使用して、レガシ アプリケーションと最新の ID コントロール プレーンとの間のギャップを埋めます。

アプリケーションの手前に BIG-IP を置くことにより、Microsoft Entra の事前認証およびヘッダーベース SSO をサービスにオーバーレイできます。 この構成により、アプリケーションのセキュリティ態勢全体が向上します。

注

組織は、Microsoft Entra アプリケーション プロキシを使用して、この種類のアプリケーションにリモート アクセスできます。 詳細情報: [Microsoft Entra アプリケーション プロキシからのオンプレミス アプリケーションへのリモート アクセス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy)

### シナリオのアーキテクチャ

SHA ソリューションには次のものが含まれます。

- **アプリケーション** - Microsoft Entra SHA で保護された BIG-IP 公開サービス。
- **Microsoft Entra ID** - Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。これにより、ユーザーの資格情報、条件付きアクセス、BIG-IP への SAML ベースの SSO が検証されます。 SSO により、Microsoft Entra ID から BIG-IP にセッション属性が提供されます。
- **BIG-IP** - アプリケーションに対するリバース プロキシおよび SAML サービス プロバイダー (SP)。バックエンド アプリケーションへのヘッダーベースの SSO を実行する前に認証を SAML IdP に委任します。

このシナリオの場合、SHA では、SP と IdP によって開始される各フローがサポートされます。 次の図は、SP によって開始されるフローを示しています。

[Image: SP によって開始されるフローを使用した構成の図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続します。
2. BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトします。
3. Microsoft Entra がユーザーを事前認証し、条件付きアクセス ポリシーを適用します。
4. ユーザーは BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行されます。
5. BIG-IP によって、Microsoft Entra 属性がアプリケーション要求のヘッダーとして挿入されます。
6. アプリケーションが要求を承認し、ペイロードを返します。

### 前提条件

このシナリオでは、以下が必要です。

- Azure サブスクリプションとして
    - お持ちでない場合は、[Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)を取得してください。
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか
- BIG-IP、または Azure に BIG-IP Virtual Edition (VE) をデプロイします
    - 「[F5 BIG-IP Virtual Edition 仮想マシンを Azure にデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)」を参照
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - 90 日間の BIG-IP 全機能試用版。 [無料試用版](https://www.f5.com/trial/big-ip-trial.php)に関するページを参照してください
- オンプレミス ディレクトリから Microsoft Entra ID に同期されたユーザー ID
    - 「[Microsoft Entra Connect Sync: 同期について理解してカスタマイズする](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)」を参照
- HTTPS を介してサービスを発行するための SSL Web 証明書。または、テストの場合には既定の BIG-IP 証明書を使用します
    - 「[SSL プロファイル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile)」を参照してください
- ヘッダーベースのアプリケーション。または、テストの場合には IIS ヘッダー アプリを設定します
    - [IIS ヘッダー アプリの設定](https://learn.microsoft.com/ja-jp/previous-versions/iis/6.0-sdk/ms525396%28v=vs.90%29)に関するページを参照してください

### BIG-IP の構成

このチュートリアルでは、Easy Button テンプレートを備えた Guided Configuration v16.1 を使用します。 Easy Button を使用すると、管理者は SHA サービスを有効にするために行き来する必要がなくなります。 [Guided Configuration] (ガイド付き構成) ウィザードと Microsoft Graph でデプロイとポリシー管理を処理します。 BIG-IP APM と Microsoft Entra の統合により、アプリケーションで ID フェデレーション、SSO、条件付きアクセスが確実にサポートされます。

注

サンプルの文字列や値は、お使いの環境のものに置き換えてください。

### Easy Button を登録する

クライアントまたはサービスは、Microsoft Graph にアクセスする前に、Microsoft ID プラットフォームによって信頼される必要があります。

詳細情報: [クイック スタート: Microsoft ID プラットフォームにアプリケーションを登録する](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)。

Graph への Easy Button アクセスを認可するために、テナント アプリの登録を作成します。 これらのアクセス許可を使用して、BIG-IP は、発行済みアプリケーションの SAML SP インスタンスと、SAML IdP となる Microsoft Entra ID の間に信頼を確立するための構成をプッシュします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**アプリ登録**&gt;に移動し、**新規登録**を選択します。
3. **[管理]** で、**[アプリの登録] &gt; [新規登録]** を選択します。
4. アプリケーションの**名前**を入力します。
5. アプリケーションを使用するユーザーを指定します。
6. **[この組織のディレクトリ内のアカウントのみ]** を選択します。
7. **[登録]** を選択します。
8. **[API permissions] (API のアクセス許可)** に移動します。
9. 次の Microsoft Graph **アプリケーションのアクセス許可**を認可します。

    - Application.Read.All
    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - Group.Read.All
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - ポリシー.読み取り書き込み.アプリケーション設定
    - Policy.ReadWrite.ConditionalAccess
    - User.Read.All
10. 組織に管理者の同意を付与します。
11. **[証明書 & シークレット]** で、新しい**クライアント シークレット**を生成します。 クライアント シークレットをメモします。
12. **[概要]** で、クライアント ID とテナント ID をメモします。

### Easy Button を構成する

1. APM Guided Configuration を開始します。
2. **Easy Button** テンプレートを開始します。
3. **[アクセス] &gt; [ガイド付き構成]** に移動します。
4. **[Microsoft Integration] (Microsoft 統合)** を選びます
5. **[Microsoft Entra アプリケーション]** を選択します。
6. 構成の手順を確認します。
7. **[次へ]** を選択します。
8. アプリケーションを発行するには、図に示されている手順の順序を使用します。

    [Image: 発行の順序の図。]

#### 構成プロパティ

**[Configuration Properties] (構成のプロパティ)** タブを使用して、BIG-IP アプリケーション構成と SSO オブジェクトを作成します。 [Azure Service Account Details] (Azure サービス アカウントの詳細) で Microsoft Entra テナントに登録したクライアントを示します。 BIG-IP OAuth クライアントの設定を使用して、SSO プロパティと共に SAML SP をテナントに登録します。 Easy Button により、発行されて SHA が有効になっている BIG-IP サービスに対してこのアクションが実行されます。

設定を再利用して、さらにアプリケーションを発行できます。

1. **構成名**を入力します。
2. **[Single Sign-On (SSO) & HTTP Headers] (シングル サインオン (SSO) と HTTP ヘッダー)** で、**[On] (オン)** を選びます。
3. **[Tenant ID] (テナント ID)**, **[Client ID] (クライアント ID)**、**[Client Secret] (クライアント シークレット)** に、メモした内容を入力します。
4. BIG-IP がテナントに接続されたことを確認します。
5. **[次へ]** を選択します

#### サービス プロバイダー

Service Provider］(サービス プロバイダー) の設定では、SHA で保護されたアプリケーションの SAML SP インスタンス設定を定義します。

1. **ホスト** (アプリケーションパブリック FQDN) を入力します。
2. **エンティティ ID** を入力します。これは、トークンを要求する SAML SP を識別するために Microsoft Entra によって使用される識別子です。
3. (省略可能) [Security Settings] (セキュリティの設定) で、**[Enable Encryption Assertion] (暗号化アサーションを有効にする)** を選んで、発行された SAML アサーションを Microsoft Entra ID で暗号化できるようにします。 Microsoft Entra ID と BIG-IP APM の暗号化アサーションは、コンテンツ トークンが傍受されたり、個人または企業のデータが侵害されたりしないことを保証するのに役立ちます。
4. **[Security Settings] (セキュリティの設定)** で、**[Assertion Decryption Private Key] (アサーション解読の秘密キー)** の一覧から、**[Create New] (新規作成)** を選びます。

    [Image: [Assertion Decryption Private Key] (アサーション解読の秘密キー) の一覧にある [Create New] (新規作成) オプションのスクリーンショット。]
5. **OK** を選択します。
6. **[SSL 証明書とキーのインポート]** ダイアログが表示されます。
7. **[Import Type] (インポートの種類)** で、**[PKCS 12 (IIS)]** を選びます。 この操作により、証明書と秘密キーがインポートされます。
8. **[Certificate and Key Name] (証明書とキー名)** で **[New] (新規)** を選び、入力します。
9. **パスワード**を入力します。
10. **インポート**を選択します。
11. ブラウザー タブを閉じて、メイン タブに戻ります。

[Image: SSL 証明書キー ソースの選択とエントリのスクリーンショット。]

1. **[Enable Encrypted Assertion] (暗号化されたアサーションを有効にする)** チェック ボックスをオンにします。
2. 暗号化を有効にした場合は、**[Assertion Decryption Private Key] (アサーション解読の秘密キー)** の一覧から証明書を選びます。 BIG-IP APM では、この証明書の秘密キーを使用して Microsoft Entra アサーションの暗号化を解除します。
3. 暗号化を有効にした場合は、**[Assertion Decryption Certificate] (アサーション解読の証明書)** の一覧から証明書を選びます。 BIG-IP は、発行された SAML アサーションを暗号化するためにこの証明書を Microsoft Entra ID にアップロードします。

[Image: [Security Settings] (セキュリティの設定) の 2 つのエントリと 1 つのオプションのスクリーンショット。]

#### Microsoft Entra ID

Microsoft Entra テナントで新しい BIG-IP SAML アプリケーションを構成するには、次の手順を使用します。 Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP 用のアプリケーション テンプレートと、汎用の SHA テンプレートが用意されています。

1. **[Azure Configuration] (Azure の構成)** の **[Configuration Properties] (構成のプロパティ)** で、**[F5 BIG-IP APM Microsoft Entra ID Integration] (F5 BIG-IP APM Microsoft Entra ID 統合)** を選びます。
2. **追加**を選択します。

##### Azure の構成

1. BIG-IP が Microsoft Entra テナントに作成するアプリの**表示名**を入力します。 ユーザーには、この名前がアイコンと共に Microsoft [\[マイ アプリ\]](https://myapplications.microsoft.com/) 上に表示されます。
2. **[Sign On URL] (サインオン URL)** (省略可能) は、スキップします。
3. **[Signing Key] (署名キー)** と **[Signing Certificate] (署名証明書)** の横にある**更新**ボタンを押して、インポートした証明書を検索します。
4. **[署名キーのパスフレーズ]** に証明書のパスワードを入力します。
5. (省略可能) **[署名オプション]** を有効にして、BIG-IP が Microsoft Entra ID によって署名されたトークンと要求を受け入れるようにします。

    [Image: [Azure Configuration](Azure の構成) のスクリーンショット - 署名証明書情報を追加する]
6. **[User And User Groups] (ユーザーとユーザー グループ)** の入力は動的に照会されます。

    重要

    テストに使うユーザーまたはグループを追加します。そうしないと、すべてのアクセスは拒否されます。 **[User And User Groups] (ユーザーとユーザー グループ)** で、**[+ Add] (+ 追加)** を選びます。

    [Image: [User And User Groups] (ユーザーとユーザー グループ) の [Add] (追加) オプションのスクリーンショット。]

##### ユーザー属性と要求

ユーザーが認証されると、Microsoft Entra ID により、ユーザーを識別するクレームと属性を含む SAML トークンが発行されます。 **[User Attributes & Claims] (ユーザー属性とクレーム)** タブには、アプリケーションの既定のクレームがあります。 さらにクレームを構成するには、このタブを使用します。

次のようにして、もう 1 つの属性を含めます。

1. **ヘッダー名**として「**employeeid**」と入力します。
2. **ソース属性**として「**user.employeeid**」と入力します。

    [Image: [Additional Claims] (追加のクレーム) の値のスクリーンショット。]

##### 追加のユーザー属性

**[Additional User Attributes] (追加のユーザー属性)** タブでは、セッションの拡張を有効にします。 この機能は、Oracle、SAP、他のディレクトリに属性を格納する必要がある他の JAVA 実装などの分散システムに使用します。 ライトウェイト ディレクトリ アクセス プロトコル (LDAP) ソースからフェッチされた属性は、追加の SSO ヘッダーとして挿入されます。 このアクションは、ロール、パートナー ID などに基づいてアクセスを制御するのに役立ちます。

注

この機能には、Microsoft Entra ID との関連性はありません。 これは属性ソースです。

##### 条件付きアクセス ポリシー

条件付きアクセスのポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御します。

- **[Available Policies] (使用可能なポリシー)** では、ユーザー アクションのない条件付きアクセス ポリシーが検索されます
- **[Selected Policies] (選択されたポリシー)**では、クラウド アプリ ポリシーが検索されます
    - これらのポリシーは、テナント レベルで適用されるため、選択解除することも、[Available Policies] (使用可能なポリシー) に移動することもできません

公開されているアプリケーションに適用するポリシーを選択するには:

1. **[Conditional Access Policy] (条件付きアクセス ポリシー)** タブの **[Available Policies] (使用可能なポリシー)** の一覧で、ポリシーを選びます。
2. **右矢印**を選択して、これを **[選択されたポリシー]** リストに移動します。

注

ポリシーに対して **[Include] (含める)** または **[Exclude] (除外する)** オプションを選ぶことができます。 両方のオプションが選択されている場合、ポリシーは強制されません。

[Image: [Selected Policies] (選択されたポリシー) でポリシーに対して [Exclude] (除外する) オプションが選択されているスクリーンショット。]

注

**[Conditional Access Policy] (条件付きアクセス ポリシー)** タブを選ぶと、ポリシーの一覧が表示されます。**更新**を選ぶと、ウィザードによってテナントが照会されます。 アプリケーションがデプロイされると、更新内容が表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは、仮想 IP アドレスで表される BIG-IP データ プレーン オブジェクトです。 サーバーは、アプリケーションに対するクライアント要求をリッスンします。 受信されたトラフィックは処理され、仮想サーバーに関連する APM プロファイルに対して評価されます。 トラフィックはポリシーに従って送信されます。

1. **[Destination Address] (宛先アドレス)** に、BIG-IP でクライアント トラフィックの受信に使用する IPv4 または IPv6 アドレスを入力します。 クライアントが BIG-IP で発行済みのアプリケーションの外部 URL をこの IP に解決できるようにする、ドメイン ネーム サーバー (DNS) 内の対応するレコードを確保してください。 テストの場合には、コンピューターの localhost DNS を使用できます。
2. **[Service Port] (サービス ポート)** には「**443**」と入力し、**[HTTPS]** を選びます。
3. **[Enable Redirect Port] (リダイレクト ポートを有効にする)** チェック ボックスをオンにします。
4. **[Enable Redirect] (リダイレクト ポート)** に値を入力します。 このオプションにより、受信 HTTP クライアント トラフィックが HTTPS にリダイレクトされます。
5. 作成した**クライアント SSL プロファイル**を選びます。または、テストの場合には既定値のままにします。 [クライアント SSL プロファイル] では、クライアント接続が TLS で暗号化されるように、HTTPS 用の仮想サーバーを有効にできます。

    [Image: [Virtual Server Properties] (仮想サーバーのプロパティ) の宛先アドレス、サービス ポート、選択済みプロファイルのスクリーンショット。]

#### プールのプロパティ

**[Application Pool] (アプリケーション プール)** タブには、プールとして表される、BIG-IP の背後にあるサービスを設定します。これには、1 つ以上のアプリケーション サーバーが含まれます。

1. **[Select a Pool] (プールの選択)** で、**[Create New] (新規作成)** を選ぶか、別のものを選びます。
2. **[Load Balancing Method] (負荷分散方法)** では、**[Round Robin] (ラウンド ロビン)** を選びます。
3. **[Pool Servers] (プール サーバー)** では、ノードを選ぶか、ヘッダーベースのアプリケーションをホストするサーバーの IP アドレスおよびポートを選びます。

    [Image: [Pool Properties] (プールのプロパティ) での IP アドレスまたはノード名、およびポートの入力のスクリーンショット。]

    注

    Microsoft バックエンド アプリケーションは HTTP ポート 80 にあります。 [HTTPS] を選んだ場合は、**443** を使用してください。

##### シングル サインオン & HTTP ヘッダー

SSO を使用すると、ユーザーは資格情報を入力することなく、BIG-IP で発行済みのサービスにアクセスします。 Easy Button ウィザードでは、SSO 用に Kerberos、OAuth Bearer、HTTP 承認ヘッダーがサポートされています。

1. **[Single Sign-On & HTTP Headers] (シングル サインオンと HTTP ヘッダー)** の **[SSO Headers] (SSO ヘッダー)** にある **[Header Operation] (ヘッダー操作)** で、**insert (挿入)** を選びます
2. **ヘッダー名**に **upn** を使用します。
3. **ヘッダーの値**に **%{session.saml.last.identity}** を使用します。
4. **[Header Operation] (ヘッダー操作)** で、**[insert] (挿入)** を選びます。
5. **ヘッダー名**に **employeeid** を使用します。
6. **ヘッダーの値**に **%{session.saml.last.attr.name.employeeid}** を使用します。

    [Image: [SSO Headers] (SSO ヘッダー) のエントリと選択のスクリーンショット。]

    注

    中かっこ内の APM セッション変数では、大文字と小文字が区別されます。 不整合があると、属性マッピングが失敗します。

#### セッションの管理

BIG-IP セッション管理設定を使用して、ユーザー セッションの終了または継続の条件を定義します。

詳細については、support.f5.com にアクセスして、「[K18390492: セキュリティ | BIG-IP APM 操作ガイド](https://support.f5.com/csp/article/K18390492)」を参照してください

シングル ログアウト (SLO) により、ユーザーがサインアウトするときに、IdP、BIG-IP、ユーザー エージェントの各セッションが終了するようなります。Easy Button によって Microsoft Entra テナントに SAML アプリケーションがインスタンス化されると、サインアウト URL に APM SLO エンドポイントが設定されます。 マイ アプリからの IdP によって開始されたサインアウトにより、BIG-IP とクライアントのセッションが終了します。

詳細情報: [\[マイ アプリ\]](https://myapplications.microsoft.com/) を確認してください

発行済みアプリケーションの SAML フェデレーション メタデータがテナントからインポートされます。 Microsoft Entra ID の SAML サインアウト エンドポイントは、このインポートによって APM に伝えられます。 このアクションにより、SP によって開始されるサインアウトでクライアントおよび Microsoft Entra のセッションが確実に終了します。 ユーザーのサインアウトが発生した場合に APM が認識するようにしてください。

BIG-IP Web トップ ポータルから発行済みアプリケーションにアクセスする場合は、eAPM によってサインアウトが処理され、Microsoft Entra サインアウト エンドポイントが呼び出されます。 BIG-IP Web ポータルが使用されない場合、ユーザーはサインアウトするよう APM に指示できません。ユーザーがアプリケーションからサインアウトした場合、BIG-IP で認識されません。 したがって、SP によって開始されるサインアウトによってセッションが安全に終了されるようにしてください。 SLO 関数をアプリケーションの **[サインアウト]** ボタンに追加できます。そうすると、クライアントは Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトされます。 テナントの SAML サインアウト エンドポイント URL を見つけるには、**[アプリの登録] &gt; [エンドポイント]** に移動します。

アプリを変更できない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンして SLO をトリガーできるようにします。

詳細情報:

- [PeopleSoft シングル ログアウト](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout)
- support.f5.com にアクセスして、以下を参照してください。
    - [K42052145: URI 参照ファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)
    - [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)

### 展開

デプロイでは、構成の概要を提供します。

1. 設定をコミットするには、**[Deploy] (デプロイ)** を選びます。
2. エンタープライズ アプリケーションのテナント一覧でアプリケーションを確認します。
3. アプリケーションが発行され、その URL、または Microsoft のアプリケーション ポータルで、SHA を介してアクセスできるようになります。

### テスト

1. ブラウザーで、アプリケーションの外部 URL に接続するか、[\[マイ アプリ\]](https://myapplications.microsoft.com/) でアプリケーションのアイコンを選びます。
2. Microsoft Entra ID に対して認証します。
3. アプリケーションの BIG-IP 仮想サーバーにリダイレクトされ、SSO を使用してサインインされます。

次のスクリーンショットでは、ヘッダー ベースのアプリケーションからのヘッダー出力が挿入されています。

注

アプリケーションへの直接アクセスをブロックできます。これにより BIG-IP を介したパスが強制されます。

### 詳細なデプロイ

一部のシナリオでは、Guided Configuration テンプレートは柔軟性が不足します。

詳細情報: [チュートリアル: ヘッダーベースの SSO 用に F5 BIG-IP Access Policy Manager を構成する](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-header-advanced)。

BIG-IP では、ガイド付き構成の厳格な管理モードを無効にできます。 その後、構成を手動で変更しますが、ほとんどの構成はウィザード テンプレートを使用して自動化されます。

1. 厳格モードを無効にするには、**[Access] (アクセス) &gt; [Guided Configuration] (ガイド付き構成)** に移動します。
2. アプリケーション構成の行で、**南京錠**アイコンを選びます。
3. アプリケーションの発行済みインスタンスに関連する BIG-IP オブジェクトは、管理のためにロック解除されます。 ウィザードでの変更はできなくなります。

    [Image: 南京錠アイコンのスクリーンショット。]

    注

    厳格モードを再度有効にして構成をデプロイすると、このアクションによって、ガイド付き構成に含まれていない設定が上書きされます。 運用サービスには高度な構成をお勧めします。

### トラブルシューティング

トラブルシューティングを行う場合は、次のガイダンスを使用します。

#### ログの冗長性

BIG-IP のログは、接続、SSO、ポリシー、正しく構成されていない変数マッピングなどに関する問題の切り分けに役立ちます。 トラブルシューティングを行うには、ログの詳細を増やします。

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要)** に移動します。
2. **[Event Logs] (イベント ログ)** を選びます。
3. **設定**を選択します。
4. 発行したアプリケーションの行を選びます
5. **[編集]** を選択します。
6. **[Access System Logs] (システム ログへのアクセス)** を選びます。
7. SSO の一覧で、**[デバッグ]** を選択します。
8. **OK** を選択します。
9. 問題を再現します。
10. ログを調べます。

注

完了したら、この機能を元に戻してください。 詳細モードでは、データが過剰に生成されます。

#### BIG-IP のエラー メッセージ

Microsoft Entra 事前認証後に BIG-IP のエラー メッセージが表示される場合、Microsoft Entra ID から BIG-IP への SSO に関する問題が発生している可能性があります。

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要)** に移動します。
2. **[Access reports] (レポートへのアクセス)** を選びます。
3. 直近 1 時間のレポートを実行します。
4. ログに手がかりがないか確認します。

セッションの **[View Session Variables] (セッション変数の表示)** リンクを使用して、予想される Microsoft Entra クレームを APM が受け取っているかどうかを把握するのに役立てます。

#### BIG-IP エラー メッセージなし

BIG-IP エラー メッセージが表示されない場合、問題はバックエンド要求、または BIG-IP からアプリケーションへの SSO に関連している可能性があります。

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要)** に移動します。
2. **[アクティブ セッション]** を選びます。
3. アクティブなセッション リンクを選びます。

**変数の表示**リンクを使用すると、特に BIG-IP APM で正しい属性が取得されない場合の SSO の問題を特定できます。

詳細情報:

- [Active Directory 向けの LDAP リモート認証の構成](https://support.f5.com/csp/article/K11072)
- techdocs.f5.com にアクセスして、「[手動チャプター: LDAP クエリ](https://techdocs.f5.com/kb/en-us/products/big-ip_apm/manuals/product/apm-authentication-single-sign-on-12-1-0/5.html)」を参照してください
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced"} -->
## F5 BIG-IP Access Policy Manager の Kerberos 認証を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced
- Service: entra-id / enterprise-apps
- Article date: 2024-04-18
- Summary: F5 BIG-IP の高度な構成を使用して、Kerberos アプリケーションへのシングル サインオン (SSO) によるセキュア ハイブリッド アクセス (SHA) を実装する方法について説明します。

このチュートリアルでは、F5 BIG-IP の高度な構成を使用して、Kerberos アプリケーションへのシングル サインオン (SSO) によるセキュア ハイブリッド アクセス (SHA) を実装する方法について説明します。 Microsoft Entra SSO に BIG-IP の公開済みサービスを有効にすると、以下のような多くの利点があります。

- Microsoft Entra 事前認証による [ゼロ トラスト](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)ガバナンスの改善と、条件付きアクセス セキュリティ ポリシー適用ソリューションの使用。
    - 「[条件付きアクセスとは」](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)を参照してください。
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- 1 つのコントロール プレーン ([Microsoft Entra 管理センター](https://entra.microsoft.com)) からの ID 管理とアクセス

特典の詳細については、 [F5 BIG-IP と Microsoft Entra ID の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)に関するページを参照してください。

### シナリオの説明

このシナリオでは、重要な基幹業務アプリケーションに対し、"統合 Windows 認証" としても知られる "Kerberos 認証" を使用するための構成を行います。

Microsoft Entra ID にアプリケーションを統合するには、Security Assertion Markup Language (SAML) など、フェデレーションベースのプロトコルからのサポートが必要になります。 しかし、アプリケーションの最新化にはダウンタイムのリスクが伴うことから、他の選択肢もあります。

SSO に Kerberos Constrained Delegation (KCD) を使用している間は、 [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy) を使用してアプリケーションにリモートでアクセスできます。 レガシ アプリケーションを最新の ID コントロール プレーンに連携するプロトコル移行を実現できます。

また、F5 BIG-IP Application Delivery Controller を使用する方法もあります。 この方法によって Microsoft Entra の事前認証や KCD SSO でアプリケーションをオーバーレイできます。 これにより、アプリケーションの全体的なゼロ トラスト体制が向上します。

### シナリオのアーキテクチャ

このシナリオの SHA ソリューションには、次の要素があります。

- **アプリケーション**: BIG-IP によって外部に公開され、SHA によって保護されたバックエンド Kerberos ベースのサービス
- **BIG-IP**: バックエンド アプリケーションを発行するためのリバース プロキシ機能。 公開されたアプリケーションは、Access Policy Manager (APM) によって SAML サービス プロバイダー (SP) と SSO 機能でオーバーレイされます。
- **Microsoft Entra ID**: SAML を介してユーザー資格情報、Microsoft Entra 条件付きアクセス、および BIG-IP APM への SSO を検証する ID プロバイダー (IdP)
- **KDC**: Kerberos チケットを発行するドメイン コントローラー (DC) でのキー配布センターの役割

次の画像は、SAML SP を起点としたこのシナリオのフローを表していますが、IdP を起点としたフローもサポートされます。

[Image: シナリオ アーキテクチャの図。]

### ユーザー フロー

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続する
2. BIG-IP アクセス ポリシーにより、ユーザーが Microsoft Entra ID (SAML IdP) にリダイレクトされる
3. Microsoft Entra ID によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われる
4. ユーザーが BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行される
5. BIG-IP がユーザーを認証し、KDC に Kerberos チケットを要求する
6. BIG-IP がバックエンド アプリケーションに対し SSO 用の Kerberos チケットとともに要求を送信する
7. アプリケーションが要求を承認し、ペイロードを返す

### 前提条件

以前の BIG-IP エクスペリエンスは必要ありません。 必要なもの:

- [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn/)、または上位レベルのサブスクリプション。
- BIG-IPを使用するか、[Azure で BIG-IP Virtual Edition をデプロイする](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)。
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP Best バンドル
    - F5 BIG-IP APM スタンドアロン ライセンス
    - BIG-IP Local Traffic Manager (LTM) に対する F5 BIG-IP APM アドオン ライセンス
    - 90 日間の BIG-IP [無料試用版](https://www.f5.com/trial/big-ip-trial.php) ライセンス
- オンプレミスディレクトリから Microsoft Entra ID に [同期された](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis) ユーザー ID、または Microsoft Entra ID で作成され、オンプレミスのディレクトリにフローバックされたユーザー ID。
- Microsoft Entra テナントの次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者。
- HTTPS 経由でサービスを発行するための Web サーバー [証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide) 。テスト中は既定の BIG-IP 証明書を使用します。
- Kerberos アプリケーション、または [windows 上の IIS に対する SSO](https://active-directory-wp.com/docs/Networking/Single_Sign_On/SSO_with_IIS_on_Windows.html) の構成については、active-directory-wp.com を参照してください。

### BIG-IP の構成方法

この記事では、BIG-IP 構成オブジェクトを作成する柔軟な SHA 実装である高度な構成について説明します。 この方法は、ガイド付き構成テンプレートが対応できないシナリオにも使用できます。

注

この記事のすべての文字列または値の例は、実際の環境の文字列または値に置き換える必要があります。

### Microsoft Entra ID に F5 BIG-IP を登録する

BIG-IP から Microsoft Entra ID に事前認証を引き継ぐ前に、テナントを登録します。 このプロセスにより、両方のエンティティ間で SSO が開始されます。 F5 BIG-IP ギャラリー テンプレートから作成するアプリが証明書利用者であり、BIG-IP によって公開されるアプリケーションの SAML SP を表します。

1. [Microsoft Entra 管理センター](https://entra.microsoft.com)に、少なくとも[クラウド アプリケーション管理者](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)としてサインインします。
2. **Entra ID**&gt;**Enterprise アプリ**&gt;**すべてのアプリケーション**を参照し、[**新しいアプリケーション**] を選択します。
3. [ **Microsoft Entra ギャラリーの参照** ] ウィンドウが表示され、クラウド プラットフォーム、オンプレミス アプリケーション、およびおすすめのアプリケーションのタイルが表示されます。 [ **おすすめアプリケーション** ] セクションのアプリケーションには、フェデレーション SSO とプロビジョニングがサポートされているかどうかを示すアイコンがあります。
4. Azure ギャラリーで **F5** を検索し、 **F5 BIG-IP APM Microsoft Entra ID 統合**を選択します。
5. 新しいアプリケーションの名前を入力して、アプリケーションのインスタンスを認識します。
6. [ **追加/作成]** を選択してテナントに追加します。

### F5 BIG-IP への SSO を有効にする

BIG-IP APM から要求された SAML トークンを実行できるように BIG-IP の登録を構成します。

1. 左側のメニューの [ **管理** ] セクションで、[ **シングル サインオン**] を選択します。 [ **シングル サインオン** ] ウィンドウが表示されます。
2. [ **シングル サインオン方法の選択** ] ページで、[SAML] を選択 **します**。 [ **いいえ] を選択します。後で保存** してプロンプトをスキップします。
3. [ **SAML でのシングル サインオンの設定** ] ウィンドウで **、ペン** アイコンを選択して **基本的な SAML 構成**を編集します。
4. 定義済みの **識別子** の値を、BIG-IP 発行されたアプリケーションの完全な URL に置き換えます。
5. **応答 URL** の値を置き換えますが、アプリケーションの SAML SP エンドポイントのパスは保持します。

注

この構成では、SAML フローは IdP によって開始されるモードで動作します。 ユーザーがアプリケーションの BIG-IP エンドポイントにリダイレクトされる前に、Microsoft Entra ID によって SAML アサーションが発行されます。

1. SP 開始モードを使用するには、[サインオン URL] にアプリケーション URL **を入力します**。
2. **[ログアウト URL]** には、発行するサービスのホストヘッダーで始まる APM 単一ログアウト (SLO) エンドポイント BIG-IP を入力します。 このアクションにより、ユーザーが Microsoft Entra ID からサインアウトした後に、ユーザーの BIG-IP APM セッションが終了します。

    [Image: 基本的な SAML 構成の URL エントリのスクリーンショット。]

注

BIG-IP トラフィック管理オペレーティング システム (TMOS) v16 から、SAML SLO エンドポイントが **/saml/sp/profile/redirect/slo** に変更されました。

1. SAML 構成を閉じる前に、[ **保存]** を選択します。
2. SSO テスト プロンプトをスキップします。
3. **[ユーザー属性と要求**] セクションのプロパティに注意してください。 Microsoft Entra ID によって BIG-IP APM 認証とバックエンド アプリケーションへの SSO のために、ユーザーにプロパティが発行されます。
4. フェデレーション メタデータ XML ファイルをコンピューターに保存するには、[ **SAML 署名証明書** ] ウィンドウで [ **ダウンロード**] を選択します。

注

Microsoft Entra ID が作成した SAML 署名証明書の有効期間は 3 年です。 詳細については、「 [フェデレーション シングル サインオンのマネージド証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/tutorial-manage-certificates-for-federated-single-sign-on)」を参照してください。

### ユーザーとグループへのアクセスの付与

既定では、Microsoft Entra ID はアプリケーションへのアクセスが付与されているユーザーに対しトークンを発行します。 ユーザーおよびグループにアプリケーションへのアクセスを許可するには、次の手順に従います。

1. **F5 BIG-IP アプリケーションの概要**ウィンドウで、[**ユーザーとグループの割り当て**] を選択します。
2. [ **+ ユーザー/グループの追加]** を選択します。

    [Image: [ユーザーとグループ] の [ユーザーまたはグループの追加] オプションのスクリーンショット。]
3. ユーザーとグループを選択し、[ **割り当て]** を選択します。

### Active Directory による Kerberos の制約付き委任の構成

BIG-IP APM がユーザーに代わってバックエンド アプリケーションへの SSO を実行するように、ターゲット Active Directory (AD) ドメインで KCD を構成します。 認証を委任するには、BIG-IP APM にドメイン サービス アカウントをプロビジョニングする必要があります。

このシナリオでは、アプリケーションはサーバー APP-VM-01 にホストされ、コンピューターの ID ではなく、web\_svc\_account という名前のサービス アカウントのコンテキストで実行されています。 APM に割り当てられた、委任する側のサービス アカウントの名称は F5-BIG-IP です。

#### BIG-IP APM 委任アカウントを作成する

BIG-IP ではグループ管理サービス アカウント (gMSA) がサポートされていないため、APM のサービス アカウントとして使用する標準ユーザー アカウントを作成します。

1. 次の PowerShell コマンドを入力します。 **UserPrincipalName** と **SamAccountName の**値を実際の環境の値に置き換えます。 セキュリティを強化するため、アプリケーションのホスト ヘッダーに一致する専用のサービス プリンシパル名 SPN を使用します。

    `New-ADUser -Name "F5 BIG-IP Delegation Account" UserPrincipalName $HOST_SPN SamAccountName "f5-big-ip" -PasswordNeverExpires $true Enabled $true -AccountPassword (Read-Host -AsSecureString "Account Password")`

    HOST\_SPN = host/f5-big-ip.contoso.com@contoso.com

    注

    ホストを使用すると、ホストで実行中のアプリケーションはアカウントを委任します。一方で、HTTPS を使用すると、HTTP プロトコルに関連する操作のみが許可されます。
2. Web アプリケーション サービス アカウントへの委任中に使用する APM サービス アカウントのサービス **プリンシパル名 (SPN)** を作成します。

    `Set-AdUser -Identity f5-big-ip -ServicePrincipalNames @Add="host/f5-big-ip.contoso.com"}`

    注

    host/part には、必ず UserPrincipleName (host/name.domain@domain) または ServicePrincipleName (host/name.domain) の形式で含める必要があります。
3. ターゲット SPN を指定する前に、その SPN 構成を表示します。 SPN が APM サービス アカウントに対して表示されていることを確認します。 APM サービス アカウントが Web アプリケーションの委任を行います。

    - Web アプリケーションがコンピューターのコンテキストまたは専用サービス アカウントで実行されていることを確認します。
    - コンピューターのコンテキストの場合、次のコマンドを使用して、Active Directory のアカウント オブジェクトに対してクエリを実行し、定義されている SPN を確認します。 &lt;name\_of\_account&gt; は、実際の環境のアカウントに置き換えてください。

        `Get-ADComputer -identity <name_of_account> -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames`

        例: Get-ADUser -identity f5-big-ip -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames
    - 専用のサービス アカウントの場合、次のコマンドを使用して、Active Directory のアカウント オブジェクトに対してクエリを実行し、定義されている SPN を確認します。 &lt;name\_of\_account&gt; は、実際の環境のアカウントに置き換えてください。

        `Get-ADUser -identity <name_of_account> -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames`

        例: Get-ADComputer -identity f5-big-ip -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames
4. アプリケーションをマシン コンテキストで実行した場合は、Active Directory のコンピューター アカウントのオブジェクトに SPN を追加します。

    `Set-ADComputer -Identity APP-VM-01 -ServicePrincipalNames @{Add="http/myexpenses.contoso.com"}`

SPN の定義後、APM サービス アカウントがそのサービスに対して委任を行うための信頼を確立します。 その構成は、BIG-IP インスタンスとアプリケーション サーバーのトポロジによって異なります。

#### BIG-IP とターゲット アプリケーションを同じドメインで構成する

1. APM サービス アカウントが認証を委任するための信頼を設定します。

    `Get-ADUser -Identity f5-big-ip | Set-ADAccountControl -TrustedToAuthForDelegation $true`
2. APM サービス アカウントは、委任先として信頼されたターゲット SPN を把握する必要があります。 ターゲット SPN は、Web アプリケーションを実行しているサービス アカウントに設定します。

    `Set-ADUser -Identity f5-big-ip -Add @{'msDS-AllowedToDelegateTo'=@('HTTP/myexpenses.contoso.com')}`

    注

    これらのタスクは、ドメイン コントローラー上の Active Directory ユーザーとコンピューターおよび Microsoft 管理コンソール (MMC) スナップインを使用して完了できます。

#### BIG-IP とターゲット アプリケーションを異なるドメインで構成する

Windows Server 2012 以降のバージョンでは、クロス ドメインの KCD にリソースベースの制約付き委任 (RBCD) が使用されます。 サービスの制約は、ドメイン管理者からサービス管理者に転送されます。 この委任により、バックエンドのサービス管理者が SSO を許可または拒否できます。 この状況では、構成の委任時に異なるアプローチが適用されます。このアプローチは、PowerShell または Active Directory Service Interfaces Editor (ADSI Edit) を使用する場合に適用できます。

アプリケーション サービス アカウント (コンピューターまたは専用サービス アカウント) の PrincipalsAllowedToDelegateToAccount プロパティを使って、BIG-IP から委任できます。 このシナリオでは、アプリケーションと同じドメインのドメイン コントローラー (Windows Server 2012 R2 以降) で以下の PowerShell コマンドを使用します。

Web アプリケーション サービス アカウントに対して定義された SPN を使用します。 セキュリティを強化するため、アプリケーションのホスト ヘッダーに一致する専用の SPN を使用します。 たとえば、この例の Web アプリケーションのホスト ヘッダーは myexpenses.contoso.com なので、Active Directory (AD) でアプリケーションのサービス アカウント オブジェクトに HTTP/myexpenses.contoso.com を追加します。

`Set-AdUser -Identity web_svc_account -ServicePrincipalNames @{Add="http/myexpenses.contoso.com"}`

次のコマンドでは、コンテキストに注意してください。

web\_svc\_account サービスがユーザー アカウントのコンテキストで実行されている場合は、以下のコマンドを使用します。

`$big-ip= Get-ADComputer -Identity f5-big-ip -server dc.contoso.com``Set-ADUser -Identity web_svc_account -PrincipalsAllowedToDelegateToAccount``$big-ip Get-ADUser web_svc_account -Properties PrincipalsAllowedToDelegateToAccount`

web\_svc\_account サービスがコンピューター アカウントのコンテキストで実行されている場合は、以下のコマンドを使用します。

`$big-ip= Get-ADComputer -Identity f5-big-ip -server dc.contoso.com``Set-ADComputer -Identity web_svc_account -PrincipalsAllowedToDelegateToAccount``$big-ip Get-ADComputer web_svc_account -Properties PrincipalsAllowedToDelegateToAccount`

詳細については、「 [ドメイン間での Kerberos の制約付き委任](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/hh831477%28v=ws.11%29)」を参照してください。

### BIG-IP の高度な構成

次のセクションを使用して、BIG-IP 構成の設定を続行します。

#### SAML サービス プロバイダーの設定を構成する

SAML サービス プロバイダーの設定では、SAML 事前認証でレガシ アプリケーションをオーバーレイするために APM が使用する SAML SP のプロパティを定義します。 それらを構成するには、次の手順に従います。

1. ブラウザーから F5 BIG-IP 管理コンソールにサインインします。
2. **[アクセス]**&gt;**[フェデレーション]**&gt;**[SAML サービス プロバイダー]**&gt;**[ローカル SP サービス]**&gt;**[作成]** の順に選択します。

    [Image: [ローカル SP サービスの SAML サービス プロバイダー] の [作成] オプションのスクリーンショット。]
3. Microsoft Entra ID の SSO を構成したときに保存した **名前** と **エンティティ** ID の値を指定します。

    [Image: [Create New SAML SP Service](新しい SAML SP サービスの作成) の [名前] エントリと [エンティティ ID] エントリのスクリーンショット。]
4. SAML エンティティ ID が発行されたアプリケーションの URL と完全に一致する場合は、 **SP 名の設定**をスキップできます。 たとえば、エンティティ ID が urn:myexpenses:contosoonline の場合、 **Scheme** 値は **https です**。 **ホスト** 値が **myexpenses.contoso.com**。 エンティティ ID が https://myexpenses.contoso.com&quot の場合は、この情報を指定する必要はありません。

#### 外部 IdP コネクタを構成する

SAML IdP コネクタは、BIG-IP APM が SAML IdP として Microsoft Entra ID を信頼するための設定を定義します。 これらの設定により、SAML SP が SAML IdP にマップされ、APM と Microsoft Entra ID 間のフェデレーションの信頼が確立されます。 コネクタを構成するには、次の手順に従います。

1. 下にスクロールして新しい SAML SP オブジェクトを選択し、[ **IdP コネクタのバインド/バインド解除**] を選択します。

    [Image: ローカル SP サービスの SAML サービス プロバイダーの [バインド解除 IdP コネクタ] オプションのスクリーンショット。]
2. [**Create New IdP Connector]\(新しい IdP コネクタの作成**\)&gt;**[From Metadata]\(メタデータから**\) を選択します。

    [Image: [SAML IdP の編集] の [Create New IdP Connector](新しい IdP コネクタの作成) の [From Metadata](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/メタデータから) オプションのスクリーンショット]
3. ダウンロードしたフェデレーション メタデータ XML ファイルを参照し、外部 SAML IdP を表す APM オブジェクトの **ID プロバイダー名** を指定します。 次の例は **、MyExpenses\_AzureAD**を示しています。

    [Image: [Create New SAML IdP Connector](新しい SAML IdP コネクタの作成) の [Select File](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/ファイルの選択) の下の [Select File and Identity Provider name](ファイルと ID プロバイダー名の選択) エントリのスクリーンショット。]
4. [ **新しい行の追加]** を選択して新しい **SAML IdP コネクタの** 値を選択し、[ **更新**] を選択します。

    [Image: SAML IdP コネクタ エントリの [更新] オプションのスクリーンショット。]
5. [ **OK] を選択します**。

#### Kerberos SSO を構成する

バックエンド アプリケーションに対する KCD SSO を実行するための APM SSO オブジェクトを作成します。 作成した APM 委任アカウントを使用します。

1. **[Access**&gt;**Single Sign-on**&gt;**Kerberos**&gt;**Create** を選択し、次の情報を指定します。

- **名前**: 作成した後、他の発行済みアプリケーションで Kerberos SSO APM オブジェクトを使用できます。 たとえば、Contoso\_KCD\_sso は、Contoso ドメインで公開された複数のアプリケーションに使用します。 1 つのアプリケーションに MyExpenses\_KCD\_sso を使用します。
- **ユーザー名のソース**: ユーザー ID ソースを指定します。 ソースとして APM セッション変数を使用します。 **session.saml.last.identity** の使用は、Microsoft Entra 要求のログイン ユーザー ID が含まれているためお勧めします。
- **ユーザー領域ソース: ユーザー** ドメインが KCD の Kerberos 領域と異なる場合に必要です。 信頼された別のドメインにユーザーが存在している場合、ログイン ユーザーのドメインが格納されている APM セッション変数を指定することによって APM に認識させます。 たとえば、「session.saml.last.attr.name.domain」です。 ユーザー プリンシパル名 (UPN) が代替サフィックスに基づいているシナリオで、このアクションを実施します。
- **Kerberos 領域**: ユーザー ドメイン サフィックス (大文字)
- **KDC**: ドメイン コントローラーの IP アドレス。 または、DNS が構成されていて効率的である場合は、完全修飾ドメイン名を入力します。
- **UPN サポート**: ユーザー名のソースが UPN 形式 (session.saml.last.identity 変数など) の場合は、このチェック ボックスをオンにします。
- **アカウント名** と **アカウント パスワード**: KCD を実行するための APM サービス アカウントの資格情報
- **SPN パターン**: HTTP/%hを使用する場合、APM はクライアント要求のホスト ヘッダーを使用して、Kerberos トークンを要求する SPN を構築します。
- **承認の送信**: 最初の要求 (Tomcat など) で Kerberos トークンを受信するのではなく、認証のネゴシエートを優先するアプリケーションでは、このオプションを無効にします。

    [Image: [全般プロパティ] の [名前]、[ユーザー名ソース]、[SSO メソッドの構成] エントリのスクリーンショット。]

ユーザーの領域がバックエンド サーバーの領域と異なる場合、[KDC] は未定義のままでかまいません。 この規則は、複数ドメイン領域のシナリオに適用されます。 KDC を未定義のままにすると、BIG-IP は、バック エンド サーバー ドメインの SRV レコードの DNS 参照を介して Kerberos 領域の検出を試行します。 ドメイン名は領域名と同じと見なされます。 ドメイン名が異なる場合は、 [/etc/krb5.conf](https://support.f5.com/csp/article/K17976428) ファイルで指定します。

IP アドレスに KDC を指定している場合、Kerberos SSO の処理が速くなります。 ホスト名に KDC を指定していると、Kerberos SSO の処理が遅くなります。 DNS クエリが増えるため、KDC が未定義の場合、処理はさらに遅くなります。 概念実証を運用環境に移行する前に、DNS のパフォーマンスが最適であることを確認します。

注

バックエンド サーバーが複数の領域に存在する場合は、それぞれの領域用に別個の SSO 構成オブジェクトを作成します。

バックエンド アプリケーションに対する SSO 要求の一部としてヘッダーを挿入できます。 [ **全般プロパティ** ] 設定を [ **基本** ] から **[詳細設定]** に変更します。

APM for KCD SSO の構成の詳細については、F5 の記事 [「K17976428: Kerberos の制約付き委任の概要](https://support.f5.com/csp/article/K17976428)」を参照してください。

#### アクセス プロファイルを構成する

アクセス プロファイルは、BIG-IP 仮想サーバーへのアクセスを管理する APM 要素をバインドします。 これらの要素には、アクセス ポリシー、SSO 構成、UI 設定が含まれます。

1. [**Access**&gt;**Profiles/Policies**&gt;**Access Profiles (Per-Session Policies)**) &gt;**Create** を選択し、次のプロパティを入力します。

    - **名前**: たとえば、「MyExpenses」と入力します。
    - **プロファイルの種類**: **すべて**選択
    - **SSO 構成**: 作成した KCD SSO 構成オブジェクトを選択します
    - **受け入れ可能な言語**: 少なくとも 1 つの言語を追加する

    [Image: [全般プロパティ]、[認証ドメイン間の SSO]、[言語設定] のエントリのスクリーンショット。]
2. 作成したセッションごとのプロファイルで、[ **編集]** を選択します。

    [Image: [セッションごとのポリシー] の [編集] オプションのスクリーンショット。]
3. ビジュアル ポリシー エディターが開きます。 フォールバックの横にある **プラス記号** を選択します。

    [Image: [アクセス ポリシーの適用] の [プラッシュ サイン] ボタンのスクリーンショット。]
4. ダイアログで、 **認証**&gt;**SAML 認証**&gt;**項目の追加**を選択します。

    [Image: [認証] タブの [SAML 認証] オプションのスクリーンショット。]
5. **SAML 認証 SP** 構成で、作成した SAML SP オブジェクトを使用するように **AAA サーバー** オプションを設定します。

    [Image: [プロパティ] タブの AAA サーバー エントリのスクリーンショット。]
6. **成功**した分岐を**許可**に変更するには、上部の **[拒否**] ボックスでリンクを選択します。
7. **[保存] を選択します**。

    [Image: アクセス ポリシーの [拒否] オプションのスクリーンショット。]

#### 属性マッピングの構成

オプションですが、 **LogonID\_Mapping** 構成を追加して、BIG-IP アクティブなセッションの一覧に、セッション番号ではなくログイン ユーザーの UPN を表示することができます。 この情報は、ログを分析する場合やトラブルシューティングを行うのに便利です。

1. **SAML 認証成功**ブランチで、**プラス記号**を選択します。
2. ダイアログで、 **割り当て**&gt;**バリアント割り当て**&gt;**項目の追加**を選択します。

    [Image: [割り当て] タブの [変数の割り当て] オプションのスクリーンショット。]
3. 名前を入力してください。
4. [ **変数の割り当て** ] ウィンドウで、[ **新しいエントリの追加**&gt;**change**] を選択します。 次の例は、[Name](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/名前) ボックスでの LogonID\_Mapping を示したものです。

    [Image: [新しいエントリの追加と変更] オプションのスクリーンショット。]
5. 次の両方の変数を設定します。

    - **カスタム変数**: session.logon.last.username を入力します
    - **セッション変数**: session.saml.last.identity を入力します
6. **[完了]**&gt;**[保存]を選択します**。
7. アクセス ポリシーの **[成功**] ブランチの **[拒否**] ターミナルを選択します。 これを **[許可]** に変更します。
8. **[保存] を選択します**。
9. [ **アクセス ポリシーの適用]** を選択し、エディターを閉じます。

    [Image: [アクセス ポリシーの適用] オプションのスクリーンショット。]

#### バックエンド プールを構成する

BIG-IP でクライアント トラフィックを正確に転送するには、アプリケーションをホストするバックエンド サーバーを表す BIG-IP ノード オブジェクトを作成します。 その後、そのノードを BIG-IP サーバー プールに配置します。

1. **[Local Traffic**&gt;**Pools**&gt;**Pool List**&gt;**Create** を選択し、サーバー プール オブジェクトの名前を指定します。 たとえば、「MyApps\_VMs」と入力します。

    [Image: [新しいプールの構成] の [名前] エントリのスクリーンショット。]
2. 以下のリソースの詳細を使用してプール メンバー オブジェクトを追加します。

    - **ノード名**: バックエンド Web アプリケーションをホストしているサーバーの表示名
    - **アドレス**: アプリケーションをホストしているサーバーの IP アドレス
    - **サービス ポート**: アプリケーションがリッスンしている HTTP/S ポート

    [Image: ノード名、アドレス、サービス ポートのエントリと [追加] オプションのスクリーンショット。]

注

こ記事では、正常性モニターで必要となる追加の構成については説明しません。 「 [K13397: BIG-IP DNS システムの HTTP 正常性モニター要求の書式設定の概要](https://support.f5.com/csp/article/K13397)」を参照してください。

#### 仮想ネットワークを構成する

仮想サーバーは BIG-IP データ プレーン オブジェクトであり、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表されます。 受信したトラフィックは、ポリシーに従って送信される前に、仮想サーバーに関連付けられている APM アクセス プロファイルに対して処理および評価されます。

仮想サーバーを構成するには、次の手順に従います。

1. **ローカル トラフィック**&gt;**仮想サーバー**&gt;**仮想サーバーの一覧**&gt;**作成**を選択します。
2. 接続されたネットワーク上の BIG-IP オブジェクトまたはデバイスに割り当てられない **名前** と IPv4/IPv6 アドレスを入力します。 この IP アドレスは、公開されたバックエンド アプリケーションのクライアント トラフィックの受信専用です。
3. **サービス ポート**を **443** に設定します。

    [Image: [全般プロパティ] の [名前]、[宛先アドレス/マスク]、および [サービス ポート] エントリのスクリーンショット。]
4. **HTTP プロファイル (クライアント)** を **http** に設定します。
5. トランスポート層セキュリティ (TLS) の仮想サーバーを有効にして、HTTPS でサービスを公開できるようにします。
6. **SSL プロファイル (クライアント) の**場合は、前提条件として作成したプロファイルを選択します。 もしくは、テストする場合は既定値を使用します。

    [Image: クライアントの HTTP プロファイルエントリと SSL プロファイル エントリのスクリーンショット。]
7. **ソース アドレス変換**を**自動マップ**に変更します。

    [Image: [送信元アドレス変換] エントリのスクリーンショット。]
8. [ **アクセス ポリシー]** で、作成 **したプロファイル** に基づいてアクセス プロファイルを設定します。 このセクションでは、Microsoft Entra SAML 事前認証プロファイルと KCD SSO ポリシーが仮想サーバーにバインドされます。

    [Image: [アクセス ポリシー] の [アクセス プロファイル] エントリのスクリーンショット。]
9. 前のセクションで作成したバックエンド プール オブジェクトを使用するように **既定** のプールを設定します。
10. [ **完了] を選択します**。

    [Image: リソースの既定のプール エントリのスクリーンショット。]

#### セッション管理の設定を構成する

BIG-IP のセッション管理設定では、ユーザー セッションが終了する条件や続行が許可される条件、ユーザーと IP アドレスの制限、およびエラー ページを定義します。 ここではポリシーを作成できます。

**アクセス ポリシー**&gt;**アクセス プロファイル**&gt;**アクセス プロファイル**に移動し、一覧からアプリケーションを選択します。

Microsoft Entra ID でシングル ログアウト URI 値を定義すると、MyApps ポータルから IdP によって開始されるサインアウトにより、クライアントと BIG-IP APM の間のセッションが確実に終了します。 インポートされたアプリケーション フェデレーション メタデータ XML ファイルは、SP によって開始されるサインアウトの Microsoft Entra SAML サインアウト エンドポイントを APM に提供します。効果的な結果を得るには、ユーザーがいつサインアウトしたかを APM が把握する必要があります。

BIG-IP Web ポータルを使用しないシナリオを考えてみましょう。 ユーザーはサインアウトするよう APM に指示できません。ユーザーがアプリケーションからサインアウトしても、BIG-IP では認識されないため、SSO を使用してアプリケーション セッションを再開できます。 SP によって開始されるサインアウトでは、セッションが安全に終了するように配慮する必要があります。

注

アプリケーションのサインアウト ボタンに SLO 関数を追加できます。 この機能を使用すると、クライアントは Microsoft Entra SAML サインアウト エンドポイントにリダイレクトされます。 SAML サインアウト エンドポイントは、 **App Registrations**&gt;**Endpoints** で見つけます。

アプリを変更できない場合は、BIG-IP でアプリのサインアウト呼び出しをリッスンすることを検討してください。 要求が検出されたら、SLO をトリガーします。

詳細については、F5 の記事を参照してください。

- [K42052145: URI で参照されるファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)
- [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)。

### まとめ

アプリケーションが公開され、その URL または Microsoft のアプリケーション ポータルを介して、SHA によりアクセスできるようになります。 アプリケーションは、 [Microsoft Entra 条件付きアクセス](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/concept-conditional-access-policies)のターゲット リソースとして表示されます。

このパターンを使用する組織では、セキュリティを強化するため、アプリケーションへの直接アクセスをブロックできます。そうすることで、BIG-IP 経由の厳密なパスを強制します。
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-kerberos-easy-button"} -->
## Kerberos シングル サインオン用に F5 BIG-IP Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-easy-button
- Service: entra-id / enterprise-apps
- Article date: 2024-06-28
- Summary: F5 の BIG-IP Easy Button ガイド付き構成を使用して、Kerberos アプリケーションへのシングル サインオンによるセキュア ハイブリッド アクセス (SHA) を実装する方法について説明します。

F5 の BIG-IP Easy Button ガイド付き構成 16.1 を使って、Microsoft Entra ID で Kerberos ベースのアプリケーションをセキュリティで保護する方法について説明します。

BIG-IP と Microsoft Entra ID の統合には、以下のように数多くの利点があります。

- ガバナンスの強化: [リモート作業を可能にするゼロトラスト フレームワーク](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)に関するページを参照し、Microsoft Entra 事前認証の詳細を確認してください。
- 組織ポリシーを適用します。 「[条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)」を参照してください。
- Microsoft Entra ID と BIG-IP 公開サービスとの間の完全な SSO
- 単一のコントロール プレーンである [Microsoft Entra 管理センター](https://entra.microsoft.com)から ID とアクセスを管理します。

メリットの詳細については、[F5 BIG-IP と Microsoft Entra の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)に関する記事を参照してください。

### シナリオの説明

このシナリオは、統合 Windows 認証 (IWA) としても知られる Kerberos 認証を使用して、保護されたコンテンツへのアクセスを制限するクラシック レガシ アプリケーションに関するものです。

レガシであるため、アプリケーションには Microsoft Entra ID との直接的な統合をサポートする最新のプロトコルがありません。 アプリケーションを最新化することは可能ですが、コストがかかりすぎ、計画が必要であり、潜在的なダウンタイムのリスクが生じます。 そうする代わりに、F5 BIG IP Application Delivery Controller (ADC) が、プロトコル遷移によってレガシ アプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しします。

アプリケーションの前に BIG-IP を配置することによって、Microsoft Entra の事前認証とヘッダー ベースの SSO によるサービスをオーバーレイできるようになるため、アプリケーションのセキュリティ体制が強化されます。

注

組織では、この種類のアプリケーションに対して [Microsoft Entra アプリケーション プロキシ](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy) を使用してリモート アクセスを取得することもできます

### シナリオのアーキテクチャ

このシナリオのためのセキュア ハイブリッド アクセス (SHA) ソリューションは、次のコンポーネントで構成されています。

- **アプリケーション:** Microsoft Entra SHA によって保護される BIG-IP 公開サービス。 このアプリケーション ホストはドメインに参加しています。
- **Microsoft Entra ID:** - ユーザー資格情報、条件付きアクセス、BIG-IP への SAML ベースの SSO を検証する Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。 SSO を使用して、Microsoft Entra ID により、必要なセッション属性が BIG-IP に提供されます。
- **KDC:** ドメイン コントローラー (DC) のキー配布センター (KDC) ロール。Kerberos チケットを発行します。
- **BIG-IP**: アプリケーションに対するリバース プロキシおよび SAML サービス プロバイダー (SP)。バックエンド アプリケーションへの Kerberos ベースの SSO を実行する前に認証を SAML IdP に委任します。

このシナリオの SHA では、SP と IdP によって開始されるフローがサポートされます。 次の図は SP のフローを示したものです。

[Image: シナリオ サービス プロバイダー フローの図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続する
2. BIG-IP Access Policy Manager (APM) アクセス ポリシーにより、ユーザーが Microsoft Entra ID (SAML IdP) にリダイレクトされます
3. Microsoft Entra ID によって、ユーザーの事前認証と、強制された条件付きアクセス ポリシーが適用される
4. ユーザーが BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行される
5. BIG-IP が KDC に Kerberos チケットを要求する
6. BIG-IP がバックエンド アプリケーションに対し、SSO 用の Kerberos チケット一緒に要求を送信する
7. アプリケーションが要求を承認し、ペイロードを返す

### 前提条件

以前の BIG-IP エクスペリエンスは必要ありませんが、以下が必要です。

- [Azure 無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn)以上
- BIG-IP、または [Azure に BIG-IP Virtual Edition (VE) をデプロイします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP APM スタンドアロン
    - F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP APM アドオン ライセンス
    - 90 日間の BIG-IP [無料試用版](https://www.f5.com/trial/big-ip-trial.php)ライセンス
- オンプレミスのディレクトリから Microsoft Entra ID に[同期された](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)ユーザー ID、または Microsoft Entra ID 内で作成されてオンプレミスのディレクトリに戻されたユーザー ID
- 次のいずれかのロール: クラウド アプリケーション管理者、アプリケーション管理者。
- HTTPS でサービスを公開するための [SSL Web 証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)。またはテスト時に既定の BIG-IP 証明書を使います
- Kerberos アプリケーション。または [Windows で インターネット インフォメーション サービス (IIS) を使用する SSO](https://active-directory-wp.com/docs/Networking/Single_Sign_On/SSO_with_IIS_on_Windows.html) を構成する方法について説明します。

### BIG-IP の構成方法

このチュートリアルでは、Easy Button テンプレートを使用する最新のガイド付き構成 16.1 について説明します。 Easy Button を使用すると、管理者は Microsoft Entra ID と BIG-IP の間を行き来して SHA のためにサービスを有効にする必要がなくなります。 APM のガイド付き構成ウィザードと Microsoft Graph によって、デプロイとポリシー管理が処理されます。 BIG-IP APM と Microsoft Entra ID の統合により、アプリケーションでは確実に ID フェデレーション、SSO、Microsoft Entra 条件付きアクセスをサポートできるため、管理オーバーヘッドが軽減されます。

注

この記事の文字列または値の例は、実際の環境のものに置き換えてください。

### Easy Button を登録する

[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app) がサービスまたはクライアントを信頼すると、どちらかが Microsoft Graph にアクセスできます。 このアクションでは、Graph への Easy Button アクセスを承認するために使用されるテナント アプリの登録を作成します。 これらのアクセス許可を使用して、BIG-IP は、発行済みアプリケーションの SAML SP インスタンスと、SAML IdP となる Microsoft Entra ID の間に信頼を確立するための構成をプッシュします。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**アプリ登録**&gt;に移動し、**新規登録**を選択します。
3. アプリケーションの表示名を入力します。 たとえば、F5 BIG-IP Easy Button です
4. アプリケーションを使用できるユーザー &gt;**[Accounts in this organizational directory only] (この組織ディレクトリのアカウントのみ)** を指定します。
5. **[登録]** を選択します。
6. **[API\* のアクセス許可\*]** に移動し、次の Microsoft Graph\* の**アプリケーション\*のアクセス許可\***を承認します：

    - Application.Read.All (アプリケーションの全ての読み取り権限)
    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - グループ.リード.オール
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - ポリシー.読み取り書き込み.アプリケーション設定
    - Policy.ReadWrite.ConditionalAccess（ポリシーの読み書き条件付きアクセス）
    - User.Read.All（ユーザー全体の読み取り）
7. 組織に管理者の同意を付与します。
8. **[証明書とシークレット]** で、新しいクライアント シークレットを生成します。 このシークレットを書き留めておきます。
9. **[概要]** でクライアント ID とテナント ID をメモします。

### Easy Button を構成する

APM の [ガイド付き構成] を開始して、Easy Button テンプレートを起動します。

1. **[アクセス] &gt; [ガイド付き構成] &gt; [Microsoft 統合]** と移動して、**[Microsoft Entra アプリケーション]** を選択します。
2. 構成手順を確認し、**[次へ]** を選択します。
3. アプリケーションを公開するには、次の手順に従います。

    [Image: ガイド付き構成の構成フローのスクリーンショット。]

#### 構成プロパティ

**[構成のプロパティ]** タブでは、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 **[Azure サービス アカウントの詳細]** セクションは、アプリケーションとして、以前に Microsoft Entra テナントに登録したクライアントを表すものとします。 これらの設定により、BIG-IP の OAuth クライアントでは、手動で構成する SSO プロパティと共に、SAML SP をテナントに登録できるようになります。 Easy Button により、公開されて SHA が有効になっているすべての BIG-IP サービスに対してこのアクションが行われます。

一部の設定はグローバルであるため、より多くのアプリケーションを公開するために再利用でき、デプロイの時間と労力を削減するのに役立ちます。

1. 一意の**構成名**を指定します。
2. **[シングル サインオン (SSO) と HTTP ヘッダー]** を有効にします
3. テナントに Easy Button クライアントを登録するときに記録した**テナント ID**、**クライアント ID**、**クライアント シークレット**を入力します。
4. BIG-IP がテナントに接続されたことを確認します。
5. **[次へ]** を選択します。

#### サービス プロバイダー

サービス プロバイダー設定は、SHA によって保護されるアプリケーションの SAML SP インスタンスのプロパティになります。

1. **[ホスト]** に、セキュリティで保護されるアプリケーションのパブリック完全修飾ドメイン名 (FQDN) を入力します。
2. **[エンティティ ID]** には、トークンを要求する SAML SP を識別するために Microsoft Entra ID が使用する識別子を入力します。

    [Image: サービス プロバイダーの [ホスト] および [エンティティ ID] の入力のスクリーンショット。]

オプションの **[セキュリティ設定]** を使用して、発行済みの SAML アサーションを Microsoft Entra ID で暗号化するかどうかを指定します。 Microsoft Entra ID と BIG-IP APM の間でアサーションを暗号化すると、コンテンツ トークンが傍受されないこと、および個人や会社のデータが侵害されないことの保証が提供されます。

1. **[Assertion Decryption Private Key] (アサーション解読秘密キー)** の一覧から、**[新規作成]** を選択します。

[Image: [セキュリティ設定] の [新規作成] オプションのスクリーンショット。]

1. **[OK]** を選択します。 **[SSL 証明書とキーのインポート]** ダイアログが表示されます。
2. **PKCS 12 (IIS)** を選択して、証明書と秘密キーをインポートします。
3. プロビジョニングが完了したら、ブラウザーのタブを閉じて、メイン タブに戻ります。

[Image: [インポートの種類]、[証明書とキー名]、[証明書キーとソース]、[パスワード] の入力のスクリーンショット]

1. **[暗号化アサーションを有効にする]** をオンにします。
2. 暗号化を有効にした場合は、**[アサーション解読の秘密キー]** リストから証明書を選択します。 この秘密キーは証明書のためのもので、Microsoft Entra アサーションを解読するために BIG-IP APM で使用されます。
3. 暗号化を有効にした場合は、**[アサーション解読の証明書]** リストから証明書を選択します。 BIG-IP は、発行された SAML アサーションを暗号化するために、この証明書を Microsoft Entra ID にアップロードします。

[Image: [アサーション解読の秘密キー] と [Assertion Decryption Certificate] (アサーション解読の証明書) エントリのスクリーンショット。]

#### Microsoft Entra ID

このセクションには、Microsoft Entra テナント内で新しい BIG-IP SAML アプリケーションを手動で構成するためのプロパティが定義されています。 Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP Enterprise Resource Planning (ERP) 用のアプリケーション テンプレートと、その他のアプリ用の SHA テンプレートが用意されています。

このシナリオでは、**[F5 BIG-IP APM Microsoft Entra ID 統合] &gt; [追加]** を選びます。

##### Azure 構成

1. BIG-IP によって Microsoft Entra テナント内に作成されるアプリと、**MyApps ポータル**のアイコンの [表示名](https://myapplications.microsoft.com/) を入力します。
2. IdP Initiated サインオンを有効にするには、**[サインオン URL]** (省略可能) に何も入力しないでください。
3. **[署名キー]** と **[署名証明書]** の横にある**更新**アイコンを選択して、先ほどインポートした証明書を見つけます。
4. **[署名キーのパスフレーズ]** に証明書のパスワードを入力します。
5. **[署名オプション]** (省略可能) を有効にして、BIG-IP が、Microsoft Entra ID によって署名されたトークンと要求を受け入れるようにします。

    [Image: [SAML 署名証明書] の [署名キー]、[署名証明書]、[Signing Key Passphrase] (署名キーのパスフレーズ) のスクリーンショット。]
6. **ユーザーとユーザー グループ**は、Microsoft Entra テナントから動的に照会され、アプリケーションへのアクセスを承認します。 テストに使うユーザーまたはグループを追加します。そうしないと、すべてのアクセスは拒否されます。

    [Image: [ユーザーとユーザー グループ] の [追加] オプションのスクリーンショット。]

##### ユーザー属性とクレーム

ユーザーが認証されると、Microsoft Entra ID は、ユーザーを識別する要求と属性の既定のセットを使用して SAML トークンを発行します。 **[ユーザー属性と要求]** タブには、新しいアプリケーションのために発行する既定の要求が表示されます。 それを使用して、より多くの要求を構成します。

インフラストラクチャは、内部および外部で使用される .com ドメイン サフィックスに基づいています。 機能する Kerberos の制約付き委任シングル サインオン (KCD SSO) の実装を実現するために、他の属性は必要ありません。 別のサフィックスを使用する複数のドメインまたはユーザーのサインインについては、[詳細なチュートリアル](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced)を参照してください。

[Image: ユーザー属性と要求のスクリーンショット。]

##### 追加のユーザー属性

**[追加のユーザー属性]** タブでは、セッション拡張のために、他のディレクトリに格納されている属性を必要とする、さまざまな分散システムがサポートされます。 次に、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) ソースからフェッチされた属性を SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスを制御できます。

注

この機能は Microsoft Entra ID と相関関係はありませんが、属性のもう 1 つのソースです。

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証後に適用されます。

**[使用可能なポリシー]** ビューには、ユーザーベースのアクションのない条件付きアクセス ポリシーが示されます。

**[選択されたポリシー]** ビューには、クラウド アプリをターゲットとするポリシーが表示されます。 テナント レベルで適用されるポリシーは選択解除することも、[使用可能なポリシー] リストに移動することもできません。

公開されているアプリケーションに適用するポリシーを選択するには、以下の手順を実行します:

1. **[使用可能なポリシー]** リストからポリシーを選びます。
2. **右矢印**を選択して、これを **[選択されたポリシー]** リストに移動します。

選択したポリシーでは、**[含める]** または **[除外する]** オプションにチェックを入れる必要があります。 両方のオプションをオンにした場合、選択したポリシーは適用されません。

[Image: [条件付きアクセス ポリシー] の [選択されたポリシー] の下に表示される除外された条件付きアクセス ポリシーのスクリーンショット。]

注

このタブに切り替えると、ポリシー リストが 1 回表示されます。**[更新]** ボタンを使用することで、ウィザードでテナントに対してクエリを実行するように手動で強制できますが、このボタンはアプリケーションをデプロイした後に表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは BIG-IP データ プレーン オブジェクトであり、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表されます。 受信されたトラフィックは処理され、仮想サーバーに関連する APM プロファイルに対して評価されます。 トラフィックはポリシーに従って送信されます。

1. **[宛先アドレス]** を入力します。これは、クライアント トラフィックを受信するために BIG-IP で使用できる IPv4 または IPv6 アドレスです。 対応するレコードが ドメイン ネーム サーバー (DNS) にも存在します。それによってクライアントでは、BIG-IP の公開済みアプリケーションの外部 URL を、アプリケーションではなく、この IP に解決できるようになります。 テストでは、テスト PC の localhost DNS を使用しても問題ありません。
2. **[サービス ポート]** で、HTTPS に対して「443」と入力します。
3. **[リダイレクト ポートを有効にする]** をオンにし、**[リダイレクト ポート]** に入力します。これは、受信 HTTP クライアント トラフィックを HTTPS にリダイレクトします。
4. [クライアント SSL プロファイル] では、クライアント接続が トランスポート層セキュリティ (TLS) で暗号化されるように、HTTPS 用の仮想サーバーを有効にできます。 前提条件として作成した**クライアント SSL プロファイル**を選択するか、テストする場合は既定値のままにします。

    [Image: [仮想サーバーのプロパティ] の [コピー先アドレス]、[サービス ポート]、[共通] エントリのスクリーンショット。]

#### プールのプロパティ

**[アプリケーション プール]** タブには、アプリケーション サーバーを含むプールとして表される、BIG-IP の背後にあるサービスが表示されます。

1. **[プールの選択]** で、新しいプールを作成するか、既存のものを選びます。
2. **[負荷分散方法]** で、[ラウンド ロビン] などを選びます。
3. **[プール サーバー]** では、サーバー ノードを選択するか、ヘッダーベースのアプリケーションをホストするバックエンド ノードの IP とポートを指定します。

    [Image: [プールのプロパティ] の [IP Address/Node Name] (IP アドレスとノード名)、[ポート] エントリのスクリーンショット。]

バックエンド アプリケーションは HTTP ポート 80 で実行されます。 アプリケーションが HTTPS で実行される場合は、ポートを 443 に切り替えることができます。

##### シングル サインオンと HTTP ヘッダー

SSO を有効にすると、ユーザーは資格情報を入力しなくても、BIG-IP で公開されているサービスにアクセスできるようになります。 Easy Button ウィザードでは、SSO 用に Kerberos、OAuth Bearer、HTTP 承認ヘッダーがサポートされています。 これらの手順には、作成した Kerberos 委任アカウントを使ってください。

**[Kerberos]** と **[詳細設定の表示]** を有効にして、次の情報を入力します。

- **[ユーザー名ソース]:** SSO のためにキャッシュする優先ユーザー名。 ユーザー ID のソースとしてセッション変数を指定できますが、*session.saml.last.identity* は、ログインしたユーザー ID を含む Microsoft Entra 要求が保持されるため、より効果的に機能します。
- **[ユーザー領域のソース]:** ユーザー ドメインが BIG-IP の Kerberos 領域と異なる場合に必要です。 その場合、APM セッション変数には、ログインしているユーザー ドメインが含まれます。 たとえば、*session.saml.last.attr.name.domain*など

    [Image: [Sign On and HTTP Headers] (シングル サインオンと HTTP ヘッダー) の [Username Source] (ユーザー名ソース) エントリのスクリーンショット。]
- **[KDC]:** ドメイン コントローラーの IP、または DNS が構成されていて効率的である場合は FQDN
- **[UPN サポート]:** APM で Kerberos チケット発行にユーザー プリンシパル名 (UPN) を使用する場合に、このオプションを有効にします
- **[SPN パターン]:** HTTP/%h を使用して、クライアント要求のホスト ヘッダーを使用し、Kerberos トークンの要求対象となるサービスプリンシパル名 (SPN) を構築するように APM に通知します
- **[承認の送信]:** 1 回目の要求で Kerberos トークンを受信せずに、認証をネゴシエートするアプリケーションでは無効にします。 たとえば、Tomcat が該当します。

    [Image: SSO 方法の構成に関するエントリのスクリーンショット]

#### セッションの管理

BIG-IP のセッション管理設定では、ユーザー セッションが終了する、または続行する条件、ユーザーと IP アドレスの制限、および対応するユーザー情報を定義します。 これらの設定の詳細については、AskF5 記事「[K18390492: セキュリティ | BIG-IP APM 操作ガイド](https://support.f5.com/csp/article/K18390492)」を参照してください。

IdP、BIG-IP、およびユーザー エージェント間のセッションをユーザーのサインアウト時に終了するシングル ログアウト (SLO) 機能は対象外です。Easy Button によって Microsoft Entra テナントに SAML アプリケーションがインスタンス化されると、サインアウト URL に APM SLO エンドポイントが設定されます。 Microsoft Entra のマイ アプリ ポータルからの IdP によって開始されるサインアウトでも、BIG-IP とクライアント間のセッションが終了します。

公開済みアプリケーションの SAML フェデレーション メタデータがテナントからインポートされて、APM に Microsoft Entra ID の SAML サインアウト エンドポイントが提供されます。 このアクションにより、SP によって開始されるサインアウトによって、クライアントと Microsoft Entra ID の間のセッションが終了します。 APM は、ユーザーがアプリケーションからサインアウトするタイミングを把握する必要があります。

BIG-IP Web トップ ポータルが発行済みアプリケーションにアクセスする場合、APM によってサインアウトが処理され、Microsoft Entra サインアウト エンドポイントが呼び出されます。 しかし、BIG-IP Web トップ ポータルが使用されていない場合に、ユーザーが APM にサインアウトを指示できないシナリオについて考えてみます。ユーザーがアプリケーションからサインアウトした場合でも、BIG-IP では認識されません。 そのため、SP によって開始されるサインアウトでは、セッションを安全に終了するよう配慮してください。 SLO 関数をアプリケーションの [サインアウト] ボタンに追加すると、クライアントは Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトされます。

テナントの SAML サインアウト エンドポイントの URL は、**[アプリの登録] &gt; [エンドポイント]** にあります。

アプリを変更できない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンし、要求を検出したら SLO をトリガーすることを検討してください。 BIG-IP iRules の詳細については、[Oracle PeopleSoft SLO ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout)を参照してください。 BIG-IP iRules の使用に関する詳細については、次を参照してください。

- [K42052145: URI 参照ファイル名に基づく自動セッション終了 (ログアウト) の構成](https://support.f5.com/csp/article/K42052145)
- [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)

### まとめ

このセクションは、構成の内訳になります。

**[デプロイ]** を選択して設定をコミットし、エンタープライズ アプリケーションのテナント リストにアプリケーションが存在することを検証します。

### KCD 構成

BIG-IP APM がユーザーに代わってバックエンド アプリケーションへの SSO を実行する場合は、ターゲット AD ドメインでキー配布センター (KCD) を構成します。 認証を委任するには、ドメイン サービス アカウントで BIG-IP APM をプロビジョニングする必要があります。

APM サービス アカウントと委任が設定されている場合は、このセクションをスキップしてください。 それ以外の場合は、管理者アカウントでドメイン コントローラーにサインインします。

このシナリオでは、アプリケーションはサーバー APP-VM-01 にホストされ、コンピューターの ID ではなく、web\_svc\_account という名前のサービス アカウントのコンテキストで実行されています。 APM に割り当てられた、委任する側のサービス アカウントの名称は F5-BIG-IP です。

#### BIG-IP APM 委任アカウントを作成する

BIG-IP ではグループ管理サービス アカウント (gMSA) がサポートされていないため、APM のサービス アカウントとして使用する標準ユーザー アカウントを作成します。

1. 次の PowerShell コマンドを入力します。 **UserPrincipalName** と **SamAccountName** の値を、お使いの環境値に置き換えます。 セキュリティを強化するため、アプリケーションのホスト ヘッダーに一致する専用の SPN を使用します。

    `New-ADUser -Name "F5 BIG-IP Delegation Account" UserPrincipalName $HOST_SPN SamAccountName "f5-big-ip" -PasswordNeverExpires $true Enabled $true -AccountPassword (Read-Host -AsSecureString "Account Password")`

    HOST\_SPN = host/f5-big-ip.contoso.com@contoso.com

    注

    ホストを使用すると、ホストで実行されているアプリケーションはアカウントを委任しますが、HTTPS を使用すると、HTTP プロトコル関連の操作のみが許可されます。
2. Web アプリケーションのサービス アカウントに委任している間に使用する APM サービス アカウントの**サービス プリンシパル名 (SPN)** を作成します。

    `Set-AdUser -Identity f5-big-ip -ServicePrincipalNames @{ Add="host/f5-big-ip.contoso.com" }`

    注

    ホストまたはパーツを UserPrincipleName (host/name.domain@domain) または ServicePrincipleName (host/name.domain) の形式で含める必要があります。
3. ターゲット SPN を指定する前に、その SPN 構成を表示します。 SPN が APM サービス アカウントに対して表示されていることを確認します。 APM サービス アカウントが Web アプリケーションの委任を行います。

    - Web アプリケーションがコンピューターのコンテキストまたは専用サービス アカウントで実行されていることを確認します。
    - コンピュータ コンテキストの場合、次のコマンドを使用して、アカウント オブジェクトに対してクエリを実行し、定義されている SPN を確認します。 &lt;name\_of\_account&gt; は、実際の環境のアカウントに置き換えてください。

        `Get-ADComputer -identity <name_of_account> -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames`

        例: Get-User -identity f5-big-ip -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames
    - 専用サービス アカウントの場合、次のコマンドを使用して、アカウント オブジェクトに対してクエリを実行し、定義されている SPN を確認します。 &lt;name\_of\_account&gt; は、実際の環境のアカウントに置き換えてください。

        `Get-User -identity <name_of_account> -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames`

        次に例を示します。

        `Get-Computer -identity f5-big-ip -properties ServicePrincipalNames | Select-Object -ExpandProperty ServicePrincipalNames`
4. アプリをマシン コンテキストで実行した場合は、コンピューター アカウントのオブジェクトに SPN を追加します:

    `Set-Computer -Identity APP-VM-01 -ServicePrincipalNames @{ Add="http/myexpenses.contoso.com" }`

SPN の定義後、APM サービス アカウントがそのサービスに対して委任を行うための信頼を確立します。 その構成は、BIG-IP インスタンスとアプリケーション サーバーのトポロジによって異なります。

#### BIG-IP とターゲット アプリケーションを同じドメインで構成する

1. APM サービス アカウントが認証を委任するための信頼を設定します:

    `Get-User -Identity f5-big-ip | Set-AccountControl -TrustedToAuthForDelegation $true`
2. APM サービス アカウントは、委任先としてターゲット SPN を把握する必要があります。 ターゲット SPN は、Web アプリケーションを実行しているサービス アカウントに設定します。

    `Set-User -Identity f5-big-ip -Add @{ 'msDS-AllowedToDelegateTo'=@('HTTP/myexpenses.contoso.com') }`

    注

    これらのタスクは、ドメイン コントローラー上で、ユーザーとコンピュータおよび Microsoft 管理コンソール (MMC) スナップインを使用して完了できます。

#### 異なるドメインにある BIG-IP とアプリケーション

Windows Server 2012 以降のバージョンでは、クロス ドメインの KCD にリソースベースの制約付き委任 (RBCD) が使用されます。 サービスの制約は、ドメイン管理者からサービス管理者に転送されます。 この委任により、バックエンドのサービス管理者が SSO を許可または拒否できます。 この状況により、構成の委任時に異なるアプローチが作成されます。これは、PowerShell でのみ可能です。

アプリケーション サービス アカウント (コンピュータまたは専用サービス アカウント) の PrincipalsAllowedToDelegateToAccount プロパティを使って、BIG-IP からの委任を行えます。 このシナリオでは、アプリケーションと同じドメインのドメイン コントローラー (Windows Server 2012 R2 以降) で以下の PowerShell コマンドを使用します。

Web アプリケーション サービス アカウントに対して定義された SPN を使用します。 セキュリティを強化するため、アプリケーションのホスト ヘッダーに一致する専用の SPN を使用します。 たとえば、この例の Web アプリケーションのホスト ヘッダーは `myexpenses.contoso.com` なので、アプリケーション サービスのアカウント オブジェクトに `HTTP/myexpenses.contoso.com` を追加します:

`Set-User -Identity web_svc_account -ServicePrincipalNames @{ Add="http/myexpenses.contoso.com" }`

次のコマンドの場合は、コンテキストに注意してください。

web\_svc\_account サービスがユーザー アカウントのコンテキストで実行されている場合は、以下のコマンドを使用します:

`$big-ip= Get-Computer -Identity f5-big-ip -server dc.contoso.com`

''Set-User -Identity web\_svc\_account -PrincipalsAllowedToDelegateToAccount'

`$big-ip Get-User web_svc_account -Properties PrincipalsAllowedToDelegateToAccount`

web\_svc\_account サービスがコンピューター アカウントのコンテキストで実行されている場合は、以下のコマンドを使用します:

`$big-ip= Get-Computer -Identity f5-big-ip -server dc.contoso.com`

`Set-Computer -Identity web_svc_account -PrincipalsAllowedToDelegateToAccount`

`$big-ip Get-Computer web_svc_account -Properties PrincipalsAllowedToDelegateToAccount`

詳細については、「[ドメイン間の Kerberos の制約付き委任](https://learn.microsoft.com/ja-jp/previous-versions/windows/it-pro/windows-server-2012-R2-and-2012/hh831477%28v=ws.11%29)」を参照してください。

### アプリ ビュー

ブラウザーから、アプリケーションの外部 URL に接続するか、**[Microsoft MyApps ポータル]** の [\[アプリケーション\]](https://myapps.microsoft.com/) のアイコンを選択します。 Microsoft Entra ID に対して認証を行うと、リダイレクトによりアプリケーションの BIG-IP の仮想サーバーに移動し、SSO を通じてサインイン処理が行われます。

[Image: アプリケーションの外部 URL のスクリーンショット]

このパターンを使用する組織では、セキュリティを強化するため、アプリケーションへの直接アクセスをブロックできます。そうすることで、BIG-IP 経由の厳密なパスを強制します。

#### Microsoft Entra B2B ゲスト アクセス

このシナリオでは [Microsoft Entra B2B ゲスト アクセス](https://learn.microsoft.com/ja-jp/entra/external-id/hybrid-cloud-to-on-premises)がサポートされています。これは、Microsoft Entra テナントから、アプリケーションによって承認のために使用されるディレクトリにゲスト ID を送ることによって行われます。 AD のゲスト オブジェクトをローカルで表現しないと、BIG-IP ではバックエンド アプリケーションへの KCD SSO の Kerberos チケットの受信に失敗します。

### 詳細なデプロイ

ガイド付き構成テンプレートでは、一部の要件を達成するための柔軟性が欠けている可能性があります。 そのようなシナリオについては、[kerberos ベースの SSO の詳細構成](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-kerberos-advanced)に関するページを参照してください。

また、BIG-IP では、ガイド付き構成の厳格な管理モードを無効にすることもできます。 構成を手動で変更できますが、構成の大部分はウィザードベースのテンプレートを使用して自動化されます。

**[アクセス] &gt; [ガイド付き構成]** の順に移動して、アプリケーションの構成の行の右端にある、**小さな南京錠アイコン**を選択します。

[Image: 南京錠オプションのスクリーンショット。]

この時点で、ウィザード UI を介した変更は行えません。ただし、アプリケーションの公開されたインスタンスに関連付けられているすべての BIG-IP オブジェクトのロックが解除され、管理できるようになります。

注

厳格モードを再度有効にして構成をデプロイすると、ガイド付き構成 UI の外部で実行された設定が上書きされます。 そのため、運用サービスに対しては詳細構成の方式をお勧めします。

### トラブルシューティング

Kerberos SSO の問題のトラブルシューティングを行う場合は、次の概念に注意してください。

- Kerberos は時刻に依存するため、サーバーとクライアントが正しい時刻に設定されている必要があります。可能であれば信頼性の高いタイム ソースと同期されるようにします。
- ドメイン コントローラーと Web アプリケーションのホスト名が DNS で解決可能であることを確認します
- ドメイン PC のコマンド ラインから setspn -q HTTP/my\_target\_SPN というクエリを実行し、重複する SPN が AD 環境に存在しないことを確かめます

IIS アプリケーションが KCD のために構成されていることを検証するには、[アプリケーション プロキシのガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/app-proxy/application-proxy-back-end-kerberos-constrained-delegation-how-to)に関する記事を参照してください。 AskF5 の記事「[Kerberos のシングル サインオン メソッド](https://techdocs.f5.com/en-us/bigip-17-1-0/big-ip-access-policy-manager-single-sign-on-concepts-configuration/kerberos-single-sign-on-method.html)」も参照してください。

#### ログ分析: 詳細度を上げる

BIG-IP のログを使用して、接続、SSO、ポリシー違反、正しく構成されていない変数マッピングなどの問題を分離します。 ログの詳細レベルを上げることでトラブルシューティングを開始します。

1. **[Access Policy] (アクセス ポリシー) &gt; [Overview] (概要) &gt; [Event Logs] (イベント ログ) &gt; [Settings] (設定)** に移動します。
2. 公開されたアプリケーションの行を選択し、**[編集] &gt; [システム ログへのアクセス]** を選択します。
3. SSO の一覧から **[Debug]** を選択して **[OK]** を選択します。

問題を再現し、ログを検査します。 完了したら、詳細モードでは多くのデータが生成されるため、機能を元に戻します。

#### BIG-IP エラー ページ

Microsoft Entra の事前認証後に BIG-IP のエラーが表示される場合は、この問題が Microsoft Entra ID から BIG-IP への SSO に関連している可能性があります。

1. **[Access] (アクセス) &gt; [Overview] (概要) &gt; [Access reports] (レポートへのアクセス)** に移動します。
2. 過去 1 時間のレポートを実行して、ログで手掛かりを確認します。
3. **[セッション変数の表示]** リンクを使用して、APM が Microsoft Entra ID から予想される要求を受信しているかどうかを把握します。

#### バックエンド要求

エラー ページが表示されない場合は、問題は、バックエンド要求や、BIG-IP からアプリケーションへの SSO に関係していることが考えられます。

1. **[アクセス ポリシー] &gt; [概要] &gt; [アクティブなセッション]** に移動します。
2. アクティブなセッションのリンクを選択します。 この場所にある **[変数の表示]** リンクは、KCD のイシュー、特に BIG-IP APM で適切なユーザー識別子やドメイン識別子をセッション変数から取得できない場合の根本原因を特定するのに役立つ場合があります

詳細については、次を参照してください。

- dev/central: [APM 変数代入の例](https://community.f5.com/t5/codeshare/apm-variable-assign-examples/ta-p/287962)
- MyF5: [セッション変数](https://techdocs.f5.com/en-us/bigip-16-1-0/big-ip-access-policy-manager-visual-policy-editor/session-variables.html)
<!-- /MSL-PAGE -->

---

<!-- MSL-PAGE {"url":"entra/identity/enterprise-apps/f5-big-ip-ldap-header-easybutton"} -->
## ヘッダー ベースと LDAP SSO 用に F5 BIG-IP の Easy Button を構成する - Microsoft Entra ID

- Source: https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-ldap-header-easybutton
- Service: entra-id / enterprise-apps
- Article date: 2024-04-19
- Summary: F5 の BIG-IP Access Policy Manager (APM) と Microsoft Entra ID を構成して、ヘッダー ベースのアプリケーションへのセキュア ハイブリッド アクセスを実現する方法を学習します。これには、ライトウェイト ディレクトリ アクセス プロトコル (LDAP) をソースとする属性を介したセッション拡張も必要になります。

この記事では、F5 BIG-IP Easy Button ガイド付き構成 16.1 で、Microsoft Entra ID を使用して、ヘッダーと LDAP ベースのアプリケーションをセキュリティで保護する方法について学習できます。 BIG-IP と Microsoft Entra ID の統合には、数多くの利点があります。

- ガバナンスの強化: [リモート作業を可能にするゼロトラスト フレームワーク](https://www.microsoft.com/security/blog/2020/04/02/announcing-microsoft-zero-trust-assessment-tool/)に関するページを参照し、Microsoft Entra 事前認証の詳細を確認してください
    - 組織のポリシーを適用する方法については、「[条件付きアクセスとは](https://learn.microsoft.com/ja-jp/entra/identity/conditional-access/overview)」も参照してください。
- Microsoft Entra ID と BIG-IP 公開済みサービス間のフル シングル サインオン (SSO)
- ID とアクセスを、[Microsoft Entra 管理センター](https://entra.microsoft.com)という 1 つのコントロール プレーンから管理できる

その他の利点については、[F5 BIG-IP と Microsoft Entra の統合](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-integration)に関する記事を参照してください。

### シナリオの説明

このシナリオでは、LDAP ディレクトリ属性から供給される **HTTP 承認ヘッダー**を使用して、保護されたコンテンツへのアクセスを管理するクラシック レガシ アプリケーションに焦点を当てています。

アプリケーションは長年使用されてきたものであるため、Microsoft Entra との直接的な統合をサポートする最新のプロトコルがありません。 アプリを最新化することは可能ですが、コストがかかり、計画が必要であり、潜在的なダウンタイムのリスクが生じます。 代わりに、プロトコル遷移によってレガシ アプリケーションと最新の ID コントロール プレーンとの間のギャップを橋渡しするために、F5 BIG IP Application Delivery Controller (ADC) を使用します。

アプリの前に BIG-IP があると、Microsoft Entra の事前認証とヘッダー ベースの SSO によってサービスをオーバーレイできるようになるため、アプリケーションの全体的なセキュリティ体制が強化されます。

### シナリオのアーキテクチャ

このシナリオのためのセキュア ハイブリッド アクセス ソリューションには、次のものが用意されています。

- **アプリケーション** - Microsoft Entra ID のセキュア ハイブリッド アクセス (SHA) によって保護される BIG-IP の公開済みサービス。
- **Microsoft Entra ID** - Security Assertion Markup Language (SAML) ID プロバイダー (IdP)。これにより、ユーザーの資格情報、条件付きアクセス、BIG-IP への SAML ベースの SSO が検証されます。 SSO により、Microsoft Entra ID から BIG-IP に必須のセッション属性が提供されます。
- **HR システム** - アプリケーションのアクセス許可の真の情報源として機能する LDAP ベースの従業員データベース
- **BIG-IP** - アプリケーションに対するリバース プロキシおよび SAML サービス プロバイダー (SP)。バックエンド アプリケーションへのヘッダーベースの SSO を実行する前に認証を SAML IdP に委任します。

このシナリオの SHA では、SP と IdP によって開始されるフローがサポートされます。 次の図は、SP Initiated フローを示しています。

[Image: セキュア ハイブリッド アクセス SP Initiated フローの図。]

1. ユーザーがアプリケーション エンドポイント (BIG-IP) に接続する
2. BIG-IP APM アクセス ポリシーは、ユーザーを Microsoft Entra ID (SAML IdP) にリダイレクトする
3. Microsoft Entra ID によって、ユーザーの事前認証と、条件付きアクセス ポリシーの適用が行われる
4. ユーザーが BIG-IP (SAML SP) にリダイレクトされ、発行された SAML トークンを使用して SSO が実行される
5. BIG IP によって、LDAP ベースの HR システムに追加の属性が要求される
6. BIG-IP によって、Microsoft Entra ID および HR システムの属性がアプリケーションへの要求のヘッダーとして挿入される
7. アプリケーションが、エンリッチされたセッション アクセス許可を使用してアクセスを承認する

### 前提条件

以前の BIG-IP エクスペリエンスは必要ありませんが、以下が必要です。

- [Azure の無料アカウント](https://azure.microsoft.com/pricing/purchase-options/azure-account?cid=msft_learn/)、またはより上位のサブスクリプション
- BIG-IP、または [Azure に BIG-IP Virtual Edition (VE) をデプロイします](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide)
- 次のいずれかの F5 BIG-IP ライセンス:
    - F5 BIG-IP® Best バンドル
    - F5 BIG-IP Access Policy Manager™ (APM) スタンドアロン ライセンス
    - BIG-IP F5 BIG-IP® Local Traffic Manager™ (LTM) に対する F5 BIG-IP Access Policy Manager™ (APM) アドオン ライセンス
    - BIG-IP 製品の 90 日間の[無料試用版](https://www.f5.com/trial/big-ip-trial.php)
- オンプレミス ディレクトリから Microsoft Entra ID に[同期された](https://learn.microsoft.com/ja-jp/entra/identity/hybrid/connect/how-to-connect-sync-whatis)ユーザー ID
- クラウド アプリケーション管理者ロール、アプリケーション管理者ロールのいずれか。
- HTTPS でサービスを公開するための [SSL Web 証明書](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-bigip-deployment-guide#ssl-profile)。またはテスト時に既定の BIG-IP 証明書を使用します
- ヘッダーベースのアプリケーション、またはテスト用に[簡単な IIS ヘッダー アプリをセットアップ](https://learn.microsoft.com/ja-jp/previous-versions/iis/6.0-sdk/ms525396%28v=vs.90%29)します
- LDAP をサポートするユーザー ディレクトリ (Windows Active Directory ライトウェイト ディレクトリ サービス (AD LDS)、OpenLDAP など)。

### BIG-IP の構成

このチュートリアルでは、Easy Button テンプレートを備えたガイド付き構成 16.1 を使用します。 Easy Button を使用すると、管理者は、Microsoft Entra ID と BIG-IP の間を行き来して SHA のためにサービスを有効にする必要がありません。 デプロイとポリシー管理は、APM のガイド付き構成ウィザードと Microsoft Graph との間で処理されます。 BIG-IP APM と Microsoft Entra ID のこの統合により、アプリケーションでは確実に ID フェデレーション、SSO、Microsoft Entra 条件付きアクセスをサポートでき、管理オーバーヘッドが軽減されます。

注

このガイドの文字列または値の例は、実際の環境のものに置き換えてください。

### Easy Button を登録する

クライアントまたはサービスから Microsoft Graph にアクセスするには、[Microsoft ID プラットフォーム](https://learn.microsoft.com/ja-jp/entra/identity-platform/quickstart-register-app)によって信頼されている必要があります。

この最初の手順では、Graph への **Easy Button** アクセスを承認するために使用されるテナント アプリの登録を作成します。 これらのアクセス許可を使用して、BIG-IP は、発行済みアプリケーションの SAML SP インスタンスと、SAML IdP となる Microsoft Entra ID の間に信頼を確立するための構成をプッシュできます。

1. [クラウド アプリケーション管理者](https://entra.microsoft.com)以上として [Microsoft Entra 管理センター](https://learn.microsoft.com/ja-jp/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator)にサインインします。
2. **Entra ID**&gt;の**アプリ登録**&gt;に移動し、**新規登録**を選択します。
3. アプリケーションの表示名を入力します。 たとえば、F5 BIG-IP Easy Button です
4. アプリケーションを使用できるユーザー &gt;**[Accounts in this organizational directory only] (この組織ディレクトリのアカウントのみ)** を指定します。
5. **[登録]** を選択します。
6. **[API\* のアクセス許可\*]** に移動し、次の Microsoft Graph\* の**アプリケーション\*のアクセス許可\***を承認します：

    - Application.Read.All
    - Application.ReadWrite.All
    - Application.ReadWrite.OwnedBy
    - Directory.Read.All (ディレクトリのすべてを読む)
    - Group.Read.All
    - IdentityRiskyUser.Read.All（アイデンティティリスキーユーザー.リード.オール）
    - Policy.Read.All
    - ポリシー.読み取り書き込み.アプリケーション設定
    - Policy.ReadWrite.ConditionalAccess
    - User.Read.All
7. 組織に管理者の同意を付与します。
8. **[証明書 & シークレット]** で、新しい**クライアント シークレット**を生成します。 このシークレットを書き留めておきます。
9. **[概要]** で**クライアント ID** と**テナント ID** をメモします。

### Easy Button を構成する

APM の **[ガイド付き構成]** を開始して、**Easy Button** テンプレートを起動します。

1. **[アクセス] &gt; [ガイド付き構成] &gt; [Microsoft 統合]** に移動し、**[Microsoft Entra アプリケーション]** を選択します。
2. 手順のリストを確認し、**[次へ]** を選択します。
3. アプリケーションを公開するには、次の手順に従います。

    [Image: ガイド付き構成の構成フローのスクリーンショット。]

#### 構成プロパティ

**[構成のプロパティ]** タブでは、BIG-IP アプリケーション構成と SSO オブジェクトが作成されます。 **[Azure サービス アカウントの詳細]** セクションは、アプリケーションとして、以前に Microsoft Entra テナントに登録したクライアントを表すものとします。 これらの設定により、BIG-IP の OAuth クライアントでは、手動で構成する SSO プロパティと共に、SAML SP をテナントに登録できるようになります。 Easy Button により、公開されて SHA が有効になっているすべての BIG-IP サービスに対してこのアクションが行われます。

これらの設定の一部はグローバルであるため、より多くのアプリケーションを公開するために再利用でき、デプロイの時間と労力を削減するのに役立ちます。

1. 一意の**構成名**を入力して、管理者が各 Easy Button 構成を区別できるようにします。
2. **[Single Sign-On (SSO) & HTTP Headers] (シングル サインオン (SSO) と HTTP ヘッダー)** を有効にします。
3. テナントに Easy Button クライアントを登録するときに記録した**テナント ID**、**クライアント ID**、**クライアント シークレット**を入力します。
4. BIG-IP がテナントに接続されたことを確認します。
5. **[次へ]** を選択します。

#### サービス プロバイダー

サービス\* プロバイダー\*設定\*では、SHA によって保護されるアプリケーション\*の SAML SP インスタンス\*のプロパティを定義します。

1. **[ホスト]** に、セキュリティで保護されるアプリケーションのパブリック完全修飾ドメイン名 (FQDN) を入力します。
2. **エンティティ ID** を入力します。これは、トークンを要求する SAML SP を識別するために Microsoft Entra によって使用される識別子です。 省略可能な **[セキュリティ設定]** で、発行された SAML アサーションを Microsoft Entra ID で暗号化するかどうかを指定します。 Microsoft Entra ID と BIG-IP APM の間でアサーションを暗号化すると、コンテンツ トークンが傍受されないこと、および個人や会社のデータが侵害されないことが保証されます。
3. **[アサーション解読秘密キー]** の一覧から、**[新規作成]** を選択します

    [Image: [セキュリティ設定] の [アサーション解読の秘密キー] の [新規作成] オプションのスクリーンショット。]
4. **OK** を選択します。 新しいタブで **[SSL 証明書とキーのインポート]** ダイアログが開きます。
5. **PKCS 12 (IIS)** を選択して、証明書と秘密キーをインポートします。 プロビジョニングが完了したら、ブラウザー タブを閉じて、メイン タブに戻ります。

    [Image: [インポートの種類]、[Certificate and Key Name] (証明書とキー名)、[Certificate Key Source] (証明書キー ソース)、[パスワード] のエントリのスクリーンショット]
6. **[Enable Encrypted Assertion](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/暗号化アサーションを有効にする)** をオンにします。
7. 暗号化を有効にした場合は、**[アサーション解読の秘密キー]** リストから証明書を選択します。 BIG-IP APM では、この証明書の秘密キーを使用して Microsoft Entra アサーションの暗号化を解除します。
8. 暗号化を有効にした場合は、**[アサーション解読の証明書]** リストから証明書を選択します。 BIG-IP は、発行された SAML アサーションを暗号化するためにこの証明書を Microsoft Entra ID にアップロードします。

    [Image: [セキュリティ設定] の [アサーション解読の秘密キー] および [Assertion Decryption Certificate] (アサーション解読の証明書) エントリのスクリーンショット。]

#### Microsoft Entra ID

このセクションには、Microsoft Entra テナント内で新しい BIG-IP SAML アプリケーションを手動で構成するためのプロパティが含まれます。 Easy Button には、Oracle PeopleSoft、Oracle E-business Suite、Oracle JD Edwards、SAP ERP 用のアプリケーション テンプレートと、その他のアプリ用の SHA テンプレートが用意されています。

このシナリオでは、**[F5 BIG-IP APM Microsoft Entra ID 統合] &gt; [追加]** を選びます。

##### Azure の構成

1. BIG-IP が Microsoft Entra テナントに作成するアプリの **[表示名]** と、[MyApps ポータル](https://myapplications.microsoft.com/)でユーザーに表示されるアイコンを入力します。
2. **[サインオン URL]** (省略可能) には何も入力しません。
3. 先ほどインポートした証明書を見つけるには、**[署名キー]** と **[署名証明書]** の横にある**更新**アイコンを選択します。
4. **[署名キーのパスフレーズ]** に証明書のパスワードを入力します。
5. **[署名オプション]** (省略可能) を有効にして、BIG-IP が Microsoft Entra ID によって署名されたトークンと要求を受け入れるようにします。

    [Image: [SAML 署名証明書] の [署名キー]、[署名証明書]、[Signing Key Passphrase] (署名キーのパスフレーズ) のエントリのスクリーンショット。]
6. **ユーザーとユーザー グループ**は、Microsoft Entra テナントから動的に照会され、アプリケーションへのアクセスを承認するために使用されます。 テストに使用するユーザーまたはグループを追加します。そうしないと、アクセスが拒否されます。

    [Image: [ユーザーとユーザー グループ] の [追加] オプションのスクリーンショット。]

##### ユーザー属性と要求

ユーザーが認証されると、Microsoft Entra ID は、ユーザーを一意に識別する要求と属性の既定のセットを使用して SAML トークンを発行します。 **[ユーザー属性と要求] タブ**には、新しいアプリケーションに対して発行する既定の要求が表示されます。 また、さらに多くの要求を構成することもできます。

この例では、属性をもう 1 つ含めます。

1. **[要求名]** に「**employeeid**」と入力します。
2. **[ソース属性]** に「**user.employeeid**」と入力します。

    [Image: [ユーザー属性と要求] の [追加の要求] の employeeid 値のスクリーンショット。]

##### 追加のユーザー属性

**[追加のユーザー属性]** タブでは、Oracle、SAP、および他のディレクトリに格納された属性を必要とする他の JAVA ベースの実装など、分散システムに必要なセッション拡張を有効にすることができます。 次に、LDAP ソースからフェッチされた属性を追加の SSO ヘッダーとして挿入して、ロール、パートナー ID などに基づいてアクセスを制御できます。

1. **[詳細設定]** オプションを有効にします。
2. **[LDAP 属性]** チェック ボックスをオンにします。
3. [認証サーバーの選択] で **[新規作成]** を選択します。
4. 実際の設定に応じて、**[Use pool](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/プールを使用)** または **[Direct](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/直接)** サーバー接続モードのいずれかを選択して、ターゲット LDAP サービスの**サーバー アドレス**を指定します。 単一の LDAP サーバーを使用する場合は、**[Direct](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/直接)** を選択します。
5. **[サービス ポート]** に、389、636 (セキュア)、または LDAP サービスが使用するその他のポートを入力します。
6. **[基本検索 DN]** に、LDAP サービス クエリに対して APM が認証を行うアカウントが含まれている場所の識別名を入力します。

    [Image: [追加のユーザー属性] の [LDAP サーバーのプロパティ] エントリのスクリーンショット。]
7. **[Search DN] (検索 DN)** に、APM が LDAP を介して照会するユーザー アカウント オブジェクトが含まれている場所の識別名を設定します。
8. 両方のメンバーシップ オプションを **[なし]** に設定し、LDAP ディレクトリから返されるユーザー オブジェクト属性の名前を追加します。 このシナリオの場合: **eventroles**。

    [Image: [LDAP Query Properties] (LDAP クエリ プロパティ) のエントリのスクリーンショット]

##### 条件付きアクセス ポリシー

条件付きアクセス ポリシーは、デバイス、アプリケーション、場所、リスクの兆候に基づいてアクセスを制御するために、Microsoft Entra の事前認証の後に適用されます。

**[使用可能なポリシー]** ビューには、ユーザー アクションを含まない条件付きアクセス ポリシーが一覧表示されます。

**[選択されたポリシー]** ビューには、すべてのリソースをターゲットとするポリシーが表示されます。 これらのポリシーは、テナント レベルで適用されるため、選択解除することも、[使用可能なポリシー] リストに移動することもできません。

公開されているアプリケーションに適用するポリシーを選択するには:

1. **[使用可能なポリシー]** リストでポリシーを選択します。
2. 右矢印を選択して、これを **[Selected Policies](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/選択されたポリシー)** リストに移動します。

    注

    選択したポリシーでは、**[含める]** または **[除外する]** オプションにチェックを入れます。 両方のオプションをオンにした場合、選択したポリシーは適用されません。

    [Image: [条件付きアクセス ポリシー] の[選択されたポリシー] の下の除外されたポリシーのスクリーンショット。]

    注

    このタブを最初に選択すると、ポリシー リストが 1 回列挙されます。**[更新]** ボタンを使用して、ウィザードでテナントに対してクエリを実行するように手動で強制します。 このボタンは、アプリケーションをデプロイすると表示されます。

#### 仮想サーバーのプロパティ

仮想サーバーは BIG-IP データ プレーン オブジェクトであり、アプリケーションに対するクライアント要求をリッスンする仮想 IP アドレスで表されます。 受信したトラフィックは、ポリシーに従って送信される前に、仮想サーバーに関連付けられている APM プロファイルに対して処理および評価されます。

1. **[宛先アドレス]** を入力します。これは、クライアント トラフィックを受信するために BIG-IP で使用できる IPv4 または IPv6 アドレスです。 対応するレコードがドメイン ネーム サーバー (DNS) にも存在する必要があり、それによってクライアントでは、BIG-IP の公開済みアプリケーションの外部 URL を、アプリケーションではなく、この IP に解決できるようになります。 テストでは、テスト PC の localhost DNS を使用しても問題ありません。
2. **[サービス ポート]** に、「443」と「HTTPS」を入力します。
3. **[リダイレクト ポートを有効にする]** をオンにし、**[リダイレクト ポート]** に入力して、受信 HTTP クライアント トラフィックを HTTPS にリダイレクトします。
4. クライアント SSL プロファイルを使用すると、HTTPS\* 用の仮想サーバー\*が有効になり、クライアント\*接続がトランスポート層セキュリティ (TLS) で暗号化されるようになります。 作成してある**クライアント SSL プロファイル**を選択するか、テスト中は既定値のままにします。

    [Image: 「仮想サーバーのプロパティ」の「全般プロパティ」の下の「送信先アドレス」、「サービス ポート」、「共通」のエントリのスクリーンショット。]

#### プールのプロパティ

**[アプリケーション プール]** タブには、1 つ以上のアプリケーション サーバーを含むプールとして表される、BIG-IP の背後にあるサービスが表示されます。

1. **[Select a Pool](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/プールの選択)** から選択します。 新しいプールを作成するか、既存のプールを選択します。
2. **[負荷分散方法]** で、[ラウンド ロビン] などを選択します。
3. **[プール サーバー]** では、ノードを選択するか、ヘッダーベースのアプリケーションをホストするサーバーの IP とポートを指定します。

    [Image: 「プールのプロパティ」の「アプリケーション プール」の下にある「IP アドレス/ノード名」と「ポート」のエントリのスクリーンショット。]

注

バックエンド アプリケーションは HTTP ポート 80 に設定されます。 HTTPS の場合は、443 に切り替えます。

#### シングル サインオンと HTTP ヘッダー

SSO を有効にすると、ユーザーは資格情報を入力しなくても、BIG-IP で公開されているサービスにアクセスできるようになります。 **Easy Button ウィザード**では、SSO 用に Kerberos、OAuth Bearer、HTTP 承認ヘッダーがサポートされています。

次の一覧を使ってオプションを構成します。

- **ヘッダー操作:** 挿入
- **ヘッダー名:** upn
- **ヘッダー値:** %{session.saml.last.identity}
- **ヘッダー操作:** 挿入
- **ヘッダー名:** employeeid
- **ヘッダー値:** %{session.saml.last.attr.name.employeeid}
- **ヘッダー操作:** 挿入
- **ヘッダー名:** eventroles
- **ヘッダー値:** %{session.ldap.last.attr.eventroles}

    [Image: [SSO Headers on SSO and HTTP Headers] (SSO と HTTP ヘッダー) の [SSO Headers] (SSO ヘッダー) のエントリのスクリーンショット。]

注

中かっこ内の APM セッション変数では、大文字と小文字が区別されます。 たとえば、Microsoft Entra の属性名が orclguid である場合に、「OrclGUID」と入力すると、属性マッピング エラーが発生します。

#### セッション管理設定

BIG-IP のセッション管理設定では、ユーザー セッションが終了されるか続行が許可される条件、ユーザーと IP アドレスの制限、および対応するユーザー情報を定義します。 これらの設定の詳細については、F5 の記事「[K18390492: セキュリティ | BIG-IP APM 操作ガイド](https://support.f5.com/csp/article/K18390492)」を参照してください。

IdP、BIG-IP、ユーザー エージェント間のセッションがユーザーのサインアウト時に終了するようにする、シングル ログアウト (SLO) 機能は、対象となりません。Easy Button によって Microsoft Entra テナントに SAML アプリケーションがインスタンス化されると、サインアウト URL に APM SLO エンドポイントが設定されます。 そのため、Microsoft Entra マイ アプリ ポータルからの IdP によって開始されるサインアウトでも、BIG-IP とクライアント間のセッションが終了します。

テナントから公開済みアプリケーションの SAML フェデレーション メタデータがインポートされて、APM に Microsoft Entra ID の SAML サインアウト エンドポイントが提供されます。 このアクションにより、SP によって開始されるサインアウトによって、クライアントと Microsoft Entra ID の間のセッションが終了します。 APM は、ユーザーがアプリケーションからサインアウトするタイミングを把握する必要があります。

BIG-IP Web トップ ポータルを使用して公開済みアプリケーションにアクセスする場合は、サインアウトが APM によって処理され、Microsoft Entra サインアウト エンドポイントが呼び出されます。 しかし、BIG-IP Web トップ ポータルが使用されないシナリオについて考えてみましょう。 ユーザーは、サインアウトするように APM に指示できません。ユーザーがアプリケーションからサインアウトした場合でも、BIG-IP は認識しません。 そのため、SP によって開始されるサインアウトでは、セッションを安全に終了するよう配慮してください。 SLO 関数をアプリケーションの [サインアウト] ボタンに追加すると、クライアントを Microsoft Entra SAML または BIG-IP サインアウト エンドポイントにリダイレクトできます。 テナントの SAML サインアウト エンドポイントの URL については、**[アプリの登録] &gt; [エンドポイント]** にあります。

アプリに変更を加えることができない場合は、BIG-IP でアプリケーションのサインアウト呼び出しをリッスンし、要求を検出したら SLO をトリガーすることを検討してください。 BIG-IP iRules の詳細については、[Oracle PeopleSoft SLO ガイダンス](https://learn.microsoft.com/ja-jp/entra/identity/enterprise-apps/f5-big-ip-oracle-peoplesoft-easy-button#peoplesoft-single-logout)を参照してください。 BIG-IP iRules の使用に関する詳細については、次を参照してください。

- [K42052145: URI 参照ファイル名に基づく自動セッション終了の構成](https://support.f5.com/csp/article/K42052145)
- [K12056: ログアウト URI インクルード オプションの概要](https://support.f5.com/csp/article/K12056)

### まとめ

この最後の手順では、構成の概要を示します。

**[デプロイ]** を選択して設定をコミットし、エンタープライズ アプリケーションのテナント リストにアプリケーションが存在することを確認します。

アプリケーションが公開され、その URL または Microsoft のアプリケーション ポータルを使用して、SHA によりアクセスできるようになります。 このパターンを使用するとは、アプリケーションへの直接アクセスをブロックできるため、セキュリティが強化されます。 これにより、BIG-IP を経由する厳密なパスが適用されます。
<!-- /MSL-PAGE -->
