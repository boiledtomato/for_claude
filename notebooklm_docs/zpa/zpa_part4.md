# Zscaler Help — ZPA — Private Access (part 4)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-09-21 22:17 UTC
Articles in this file: 56

---

<!-- ZS-ARTICLE {"url":"/zpa/uploading-enrollment-ca-certificates-and-certificate-chain","lastmod":"2026-09-17T07:54Z","nid":"1484076"} -->
## Uploading Enrollment (CA) Certificates and the Certificate Chain

- Source: https://help.zscaler.com/zpa/uploading-enrollment-ca-certificates-and-certificate-chain
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Certificate Management > Enrollment Certificates > Uploading Enrollment (CA) Certificates and the Certificate Chain
- Last modified: 2026-09-17T07:54Z
- Summary: How to create and upload a CA certificate chain file as well as how to upload signed CA certificates that are used for App Connector and Zscaler Client Connector enrollment for Private Access.

A CA certificate is required for enrolling Zscaler Client Connector and when [configuring an App Connector](https://help.zscaler.com/zpa/configuring-connectors) for enrollment. You can upload up to 1,000 enrollment (CA) certificates. For a complete list of ranges and limits per feature, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#private-applications).

The uploaded signed certificate must include the private key.

To upload the certificate chain and CA certificates for enrolling Zscaler Client Connector and App Connectors:

- Step 1: Create CA Certificate Chain File
- Step 2: Upload CA Certificate Chain File
- Step 3: Upload Signed CA Certificates

You only need to upload the certificate chain once for your CA certificates. If you have already uploaded the certificate chain, skip to [Step 3: Upload Signed CA Certificates](https://help.zscaler.com/zpa/uploading-enrollment-ca-certificates-and-certificate-chain#UploadNotPending) to upload your CA certificates.

1. Download all of your intermediate certificates and the root certificate as Base64-encoded ASCII PEM-formatted files.
2. Using a text editor, create a new certificate file (e.g., certificate_chain.pem).
3. Within the new file, include all of the certificate information up to and including the root certificate. Also, make sure that the certificate order within the file starts from the intermediate certificate.
  - Intermediate certificate 1
  - Intermediate certificate 2 above that, etc.
  - Root certificate

For example, your certificate chain file should appear as follows:

```
-----BEGIN CERTIFICATE-----
MIICujCCAaICAQAwdTEQMA4GA1UEChMHWnNjYWxlcjEXMBUGA1UECxMOUHJpdmF0
ZSBBY2Nlc3MxSDBGBgNVBAMTP21vY2tjb21wYW55LmNvbS9Nb2NrIENvbXBhbnkg
wMFowgYcxCzAJBgNVBAYTAlVTMREwDwYDVQQIEwhNYXJ5bGFuZDESMBAwMFowgYc
MDEyMDAwMFowgYcxCzAJBgNVBAYTAlVTMREwDwYDVQQIEwhNYXJ5bGFuZDESMBAG
v+PMGxmcJcqnBrJT3yOyzxIZow==
-----END CERTIFICATE-----
-----BEGIN CERTIFICATE-----
MIIClDCCAXwCAQAwTzEQMA4GA1UEChMHWnNjYWxlcjEXMBUGA1UECxMOUHJpdmF0
ZSBBY2Nlc3MxIjAgBgNVBAMTGW15LW1vY2tjb21wYW55LmNvbS9hZHNkc2QwggEi
MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC2meeeh24wzQQ48o2lcbhBFYhi
slXkLGtB8L5cRspKKaBIXiDSRf8F3jSvcEuBOeLKB1d8tjHcISnivpcOd5AUUUDh
v+PMGxmcJcqnBrJT3yOyzxIZow==
-----END CERTIFICATE-----
-----BEGIN CERTIFICATE-----
MIICtzCCAZ8CAQAwcjEQMA4GA1UEChMHWnNjYWxlcjEXMBUGA1UECxMOUHJpdmF0
ZSBBY2Nlc3MxRTBDBgNVBAMTPG1vY2tjb21wYW55LmNvbS9Nb2NrIENvbXBhbnkg
Q2xpZW50IFByb3Zpc2lvbmluZyBDZXJ0aWZpY2F0ZTCCASIwDQYJKoZIhvcNAQEB
BQADggEPADCCAQoCggEBAK0vUQx3UYZ1Krlxk2uPfntu8HSDnn+Jwnj7WLkanyvJ
YHIHKHFYNs9mHRL2JsMgV3FxOuVMde7y0cdEXOovDsIVF9y/DHNh4cDVN4fKqfcy
CAUw7C29C79Fv1C5qfPrmAESrciIxpg0X40KPMbp1ZWVbd4=
-----END CERTIFICATE-----
```

1. Make sure that you have completed Step 1.
2. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Private Access** > **Private Infrastructure** > **Enrollment Certificates**.
3. On the **Enrollment Certificates** page, upload the root certificate:
  1. Click **Upload Certificate Chain**. The **Upload Certificate Chain** window appears.
  2. In the **Upload Certificate Chain** window:
    1. **Name**: Enter a name for the root certificate. The name cannot contain special characters, with the exception of periods (.), hyphens (-), and underscores ( _ ).
    2. **Description**: (Optional) Enter a description.
    3. **Certificate**: Click **Select File** and navigate to the root certificate.
  3. Click **Upload**

[Image: Viewing the Upload Certificate Chain window]

1. On the **Enrollment Certificates** page, upload the PEM-formatted file:

Prior to uploading the PEM file, you must upload the root file. If the root file is not uploaded first, then the PEM file will not upload.

1. Click **Upload Certificate Chain**. The **Upload Certificate Chain** window appears.
2. In the **Upload Certificate Chain** window:
  1. **Name**: Enter a name for the certificate chain. The name cannot contain special characters, with the exception of periods (.), hyphens (-), and underscores ( _ ).
  2. **Description**: (Optional) Enter a description.
  3. **Certificate**: Click **Select File** and navigate to a Base64-encoded ASCII PEM-formatted file that includes the certificate chain of trust for your signed CA certificates.
3. Click **Upload**.

Make sure that you have uploaded the certificate chain associated to your CA certificates. If you have not done so, complete [Steps 1 and 2](https://help.zscaler.com/zpa/about-uploadingEnrollmentCertificate#UploadPending).

1. Within the table, locate the **Certificate Pending**icon ([Image: Certificate Pending icon]) next to the certificate name and click the **Edit** icon.

The **Upload Signed Certificate** window appears.

1. In the **Upload Signed Certificate** window:
  - **Name**: Enter a name for the signed certificate. The name cannot contain special characters, with the exception of periods (.), hyphens (-), and underscores ( _ ).
  - **Description**: (Optional) Enter a description.
  - **Certificate Signing Request**: The CSR text is displayed here.
  - **Certificate**: Click **Select File** and navigate to the signed CA certificate (i.e., the .pem file).

[Image: Viewing the Upload Signed Certificate window]

1. Click **Upload**.

After uploading the signed CA certificate, click the **Edit**icon within the table again and make sure that the **Client Certificate Type**option is set correctly. This option specifies whether the signed CA certificate is used to enroll Zscaler Client Connector, App Connectors and Private Service Edges for Private Access, or Zero Trust Browser clients:

- If you are using the enrollment (CA) certificate for Zscaler Client Connector, select **Client Connector**.
- If you are using the enrollment (CA) certificate for Zero Trust Browser clients, select **Isolation Client**.
- If you are using the enrollment (CA) certificate for App Connectors and Private Service Edges, select **None**.

[Image: Viewing the Edit Certificate window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/uploading-web-server-certificates","lastmod":"2026-09-15T15:28Z","nid":"1484061"} -->
## Uploading (Web Server) Certificates

- Source: https://help.zscaler.com/zpa/uploading-web-server-certificates
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Certificate Management > Certificates > Uploading (Web Server) Certificates
- Last modified: 2026-09-15T15:28Z
- Summary: How to upload a Browser Access (web server) certificate within the Zscaler Admin Console.

After a certificate used for web servers is uploaded, it can then be selected when [defining an application](https://help.zscaler.com/zpa/configuring-defined-application-segments#tab1) within an application segment. For defined applications with Browser Access enabled, if the certificate is signed by a public certificate authority (CA), then HTTPS should be selected as the protocol. If the certificate is self-signed, or Zscaler is unable to verify the chain of trust to the public CA, select HTTP. Private Access (ZPA) supports HTTP and HTTPS protocols, and inserts a Via header in HTTP requests (e.g., Via: 1.1 Zscaler-p.zpa-auth.net). To learn more, see [Configuring Application Segments](https://help.zscaler.com/zpa/configuring-defined-application-segments#tab1).

The uploaded signed certificate must include the unencrypted private key.

You can upload up to 1,000 certificates. For a complete list of ranges and limits per feature, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#private-applications).

To upload a certificate:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Private Access** > **Resources** > **Clientless Certificates**.
2. On the **Certificates** page: You must upload a single Base64-encoded ASCII .pem file, which includes the certificate and unencrypted private key. If your PKI provided you with separate files or a file in the wrong format, you can use the following OpenSSL commands to create a single .pem file that includes the certificate and key. See instructions.
  - Upload a web server certificate.
  - Upload a web server certificate for a pending CSR.
3. Click **Upload**.

The following command creates a combined certificate and unencrypted private key PKCS#12 file (.pfx):

```
openssl pkcs12 -export -in
<certificate>
.pem -inkey
<unencrypted private key>
.key -out
<combined cert and key>
.pfx
```

For example:

```
openssl pkcs12 -export -in cert.pem -inkey unencrypted.key -out combined.pfx
```

The following command converts the PKCS#12 file (.pfx) containing the combined certificate and unencrypted private key file to PEM format (.pem):

```
openssl pkcs12 -in
<combined cert and key>
.pfx -out
<combined cert and key>
.pem -nodes
```

For example:

```
openssl pkcs12 -in combined.pfx -out combined.pem -nodes
```

1. Click **Upload Server Certificate**.

The **Upload Server Certificate** drawer appears.

1. In the **Upload Server Certificate** drawer: [Image: Certificates page with Upload Server Certificate drawer within Zscaler Admin Console]
  1. **Name**: Enter a name for the web server certificate. The name cannot contain special characters, with the exception of periods (.), hyphens (-), and underscores ( _ ).
  2. **Description**: (Optional) Enter a description.
  3. **Certificate**: Click **Select File** and navigate to the web server certificate that was signed using your PKI (i.e., a Base64-encoded ASCII PEM-formatted file). Make sure that the .pem file also includes the unencrypted private key.

1. Within the table, locate the **Certificate Pending icon** ([Image: Certificate Pending icon]) next to the certificate name and click the **Edit** icon.

The **Upload Server Certificate** drawer appears.

1. In the **Upload Server Certificate** drawer:
  1. **Name**: Make sure that the correct name was entered.
  2. **Description**: (Optional) Enter a description.
  3. **Certificate Signing Request**: The CSR text is displayed here.
    1. Click **Download .CSR File**.
    2. Sign the downloaded .csr file using your public key infrastructure (PKI) to create a valid signed web server certificate.
    3. Save the certificate in Base64-encoded ASCII PEM format (.pem).
  4. **Certificate**: Click **Select File** and navigate to the signed web server certificate you created (i.e., the .pem file).

[Image: Browser Access Certificates page with Upload Server Certificate window for pending CSR]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/using-app-segment-multimatch","lastmod":"2026-09-10T10:52Z","nid":"1485951"} -->
## Using Application Segment Multimatch

- Source: https://help.zscaler.com/zpa/using-app-segment-multimatch
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Application Management > Application Segments > Using Application Segment Multimatch
- Last modified: 2026-09-10T10:52Z
- Summary: Information about using Application Segment Multimatch in Private Access.

This feature is in limited availability. To learn more, contact Zscaler Support.

Multimatch allows an application request to match multiple application segments. When a user tries to access a private application without Multimatch, a request is mapped to an application segment. After the application is mapped to an application segment, the [policy](https://help.zscaler.com/zpa/about-access-policy) search is performed, and the request is either allowed or blocked based on the policy configuration.

Private Access (ZPA) evaluates Multimatch across all application segments that include the same applications. When an administrator enables or disables Multimatch for an application segment, Private Access checks all other application segments that contains any overlapping domains to determine whether the change is allowed. If a domain is found in multiple application segments with different Multimatch settings, there is a conflict and the application segment cannot be updated.

To learn more, see [About Access Policy](https://help.zscaler.com/zpa/about-access-policy) and [Configuring Access Policies.](https://help.zscaler.com/zpa/configuring-access-policies)

## Prerequisites

To use application segment Multimatch, ensure the following:

- App Connectors or Private Service Edges for Private Access are upgraded to version 24.298.1 or later.
- Zscaler Client Connector is upgraded to the following versions per OS:
  - Windows versions:
    - 4.7.0.88 or later
    - 4.6.0.282 or later
  - macOS versions: 4.5.2.98 or later
  - iOS version 4.4.1 or later To use the Private Access Multimatch feature for iOS applications, you must enable the [Use Tunnel SDK Version 4.3 or above setting](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) in the Zscaler Admin Console. See image.
  - Android version: 3.10 or later
  - Linux version: 4.2 or later

## Using Multimatch

By default, after a specific FQDN is configured in an application segment, the destination is removed from the wildcard application segment. The default behavior can cause unwanted access failure due to the destination matching a more specific application segment that does not have the UDP or TCP ports defined. Multimatch allows admins to create new application segments without the risk of unwanted access failure if a user attempts to access a FQDN with undefined ports. After Multimatch is enabled, the wildcard application segment catches all UDP or TCP ports that are not configured in the more specific application segment. When you have decided what application segments you want matched, you can enable Multimatch for that application segment. To learn more, see [Configuring Defined Application Segments](https://help.zscaler.com/zpa/configuring-application-segments).

Multimatch must be disabled if the configuration contains applications using Double Encryption and Source IP Anchor. To learn more, see [Configuring Defined Application Segments](https://help.zscaler.com/zpa/configuring-application-segments).

### Multimatch Validation for Application Segments

When Multimatch is enabled, a Multimatch Validation window appears for you to review any impacted application segments and conflicting features. If there is a conflicting feature, a warning appears.

See image.

Depending on the application segments selected, you can resolve the issue by doing one of the following:

- Edit an application segment and disable unsupported features.
- Review conflicting features in a local Microtenant and select the checkboxes that appear.
- Contact the default administrator when Multimatch includes the default application segment.
- Cancel and disable Multimatch if an application segment belongs to another local tenant.

## App Connector Selection

If both wildcard and specific FQDN application segments have Multimatch enabled, the specific FQDN application segment's server group is used for App Connector selection, even if the traffic to the FQDN matches the wildcard or the specific FQDN rule.

The following table shows example application segment configurations:

| **Application Segment Name** | **FQDN** | **Protocol** | **Port** | **Server Group** |
| --- | --- | --- | --- | --- |
| Wildcard_AS | *.example.com | TCP, UDP | 1 to 52, 54 to 65535 | Server Group - All |
| Server1_AS | server1.example.com | TCP | 443, 3389 | Server Group - Server1 |

When traffic is destined for a specific FQDN and port (e.g., server1.example.com on TCP/22), the App Connectors belonging to the more specific Server Group - Server1 will proxy the traffic, even if a less specific wildcard application segment (Wildcard_AS) also matches.

The App Connectors associated with the specific FQDN's application segment are typically the most appropriate for handling that traffic.

The selection of the App Connector that proxies traffic is determined by the application segment's Server Group configuration, with critical differences based on whether Multimatch is enabled.

- If Multimatch is disabled, the App Connector selection is primarily based on the application segment's server group configuration. For example, traffic matching Wildcard_AS would be proxied by the App Connector in Server Group - All. This selection can be overridden in an access policy.
- If Multimatch is enabled, the App Connector linked to the specific FQDN application segment must be capable of accessing all the ports defined for that FQDN. If multiple Multimatch application segments exist for the same FQDN but with different ports, they must all use the same server group. If different server groups are used, any of them can be randomly selected to proxy the traffic to that FQDN, leading to unpredictable behavior.

## Exact Match vs. Multimatch

There are two application match behaviors available in Private Access: Exact Match and Multimatch. Exact match is the default behavior.

### About Exact Match

The default behavior of Private Access is to perform an exact match of applications. If two or more application segments cover the same destination address, Zscaler Client Connector attempts to match traffic to the more granular application segment. Consider the following example of two application segments with the overlapping domain name example.com:

- Example: Exact Match

If a user attempts to access server1.example.com on TCP port 3389, the destination address maps to the more specific application segment, which is Server1_AS. However, the Server1_AS application segment does not have TCP port 3389 configured, resulting in failed access.

| Application Segment Name | Application | Protocol | Ports |
| --- | --- | --- | --- |
| Wildcard_AS | *.example.com | TCP, UDP | 1 to 52, 54 to 65535 |
| Server1_AS | server1.example.com | TCP | 443 |

In this scenario, the attempt to access server1.example.com on TCP port 3389 is dropped at the client level, traffic is not sent to the Private Access cloud, and logs aren't visible in the diagnostics. After a specific FQDN (Fully Qualified Domain Name) has been configured in an application segment, it is removed from the wildcard application segment by default. Even though server1.example.com is part of the Wildcard_AS application segment for the *example.com application on the TCP port range of 54 to 65535, this access does not match the Wildcard_AS application segment. To fix this, TCP port 3389 must be configured for the Server1_AS application segment, or Multimatch can be enabled. Enabling Multimatch changes the behavior of the wildcard application segment, and catches access to ports that have not been defined in the more specific FQDN application segment.

### About Multimatch

The following examples illustrate how applications are mapped to application segments, as well as how policy execution works when Multimatch is enabled or not enabled on application segments.

- Multiple application segment matches
- Behavior after inserting a block rule

The primary use for Multimatch is when there are multiple possible application segment matches. The following tables show application segment configuration, the policy configuration, and how they match up.

| Application Segment Configuration |  |  |  |  |
| --- | --- | --- | --- | --- |
| Application Segment Name | Application | Protocol | Ports | Multimatch |
| Wildcard_AS | *.example.com | TCP, UDP | 1 to 52, 54 to 65535 | Enabled |
| Server1_AS | server1.example.com | TCP | 443, 3389 | Enabled |

| Access Policy Configuration |  |  |  |  |
| --- | --- | --- | --- | --- |
| # | Access Policy Name | Application Segment Name | Policy Action | User Group |
| 1 | Allow Server1_AS for Admin | Server1_AS | Allow | Admin_Grp |
| 2 | Allow Wildcard_AS for All | Wildcard_AS | Allow | All |

| Application Match and Policy Search Results |  |  |  |  |  |  |
| --- | --- | --- | --- | --- | --- | --- |
| User | Group | FQDN: Protocol & Port | Matched Application Segment with Multimatch | Matched Application Segment without Multimatch | Matched Policy with Multimatch | Matched Policy Number without Multimatch |
| user1 | Admin_Grp | server1.example.com:TCP+3389 | Wildcard_AS Server1_AS | Server1_AS | Allow Server_AS for All (#1) | Allow Server1_AS for Admin (#1) |
| user2 | IT_Grp | server1.example.com:TCP+3389 | Wildcard_AS Server1_AS | Server1_AS | Allow Wildcard_AS for All (#2) | No policy match. Blocked by implicit default block policy. |
| user1 | Admin_Grp | server1.example.com:TCP+22 | Wildcard_AS | N/A | Allow Wildcard_AS for All (#2) | Traffic is dropped at the client level |
| user2 | IT_Grp | server1.example.com:TCP+22 | Wildcard_AS | N/A | Allow Wildcard_AS for All (#2) | Traffic is dropped at the client level |
| user3 | IT_Grp | server1.example.com:TCP+80 | Wildcard_AS | N/A | Allow Wildcard_AS for All (#2) | Traffic is dropped at the client level |
| user3 | IT_Grp | server1.example.com:TCP+443 | Wildcard_AS Server1_AS | Server1_AS | Allow Wildcard_AS for All (#2) | No policy match. Blocked by implicit default block policy. |

Traffic that is dropped at the client level means that traffic matches the hostname, but it does not match the protocol and port. In this condition, traffic is not sent to the cloud for further processing. This means that policy evaluation does not occur, and the user is not able to access the application segment. In this case, traffic is not visible in the Private Access diagnostics.

The following table shows how the policy search results change by inserting a block policy in the rule set.

| Application Segment Configuration |  |  |  |  |
| --- | --- | --- | --- | --- |
| Application Segment Name | Application | Protocol | Ports | Multimatch |
| Wildcard_AS | *.example.com | TCP, UDP | 1 to 52, 54 to 65535 | Enabled |
| Server1_AS | server1.example.com | TCP | 443, 3389 | Enabled |

| Access Policy Configuration |  |  |  |  |
| --- | --- | --- | --- | --- |
| # | Access Policy Name | Application Segment Name | Policy Action | User Group |
| 1 | Allow Server1_AS for Admin | Server1_AS | Allow | Admin_Grp |
| 2 | Block Server1_AS for All | Server1_AS | Block | All |
| 3 | Allow Wildcard_AS for All | Wildcard_AS | Allow | All |

| Application Match and Access Policy Search Results |  |  |  |  |  |  |
| --- | --- | --- | --- | --- | --- | --- |
| User | Group | FQDN: Protocol & Port | Matched Application Segment with Multimatch | Matched Application Segment without Multimatch | Matched Policy Number with Multimatch | Matched Policy Number without Multimatch |
| user1 | Admin_Grp | server1.example.com:TCP+3389 | Wildcard_AS Server1_AS | Server1_AS | Allow Server1_AS for Admin (#1) | Allow Server1_AS for Admin (#1) |
| user1 | Admin_Grp | server1.example.com:TCP+443 | Wildcard_AS Server1_AS | Server1_AS | Allow Server1_AS for Admin (#1) | Allow Server1_AS for Admin (#1) |
| user1 | Admin_Grp | server1.example.com:TCP+22 | Wildcard_AS | N/A | Allow Wildcard_AS for All (#3) | Traffic is dropped at the client level |
| user2 | IT_Grp | server1.example.com:TCP+22 | Wildcard_AS | N/A | Allow Wildcard_AS for All (#3) | Traffic is dropped at the client level |
| user3 | IT_Grp | server1.example.com:TCP+80 | Wildcard_AS | N/A | Allow Wildcard_AS for All (#3) | Traffic is dropped at the client level |
| user3 | IT_Grp | server1.example.com:TCP+443 | Wildcard_AS Server1_AS | Server1_AS | Block Server1_AS for All (#2) | Block1_AS for All (#2) |

Traffic that is dropped at the client level means that traffic matches the hostname, but it does not match the protocol and port. In this condition, traffic is not sent to the cloud for further processing. This means that policy evaluation does not occur, and the user is not able to access the application segment. In this case, traffic is not visible in the Private Access diagnostics.

### Matched vs. Not Matched

Multiple matches apply to applications from most specific to least specific. As soon as an application is encountered that does not support Multimatch, the multimatching stops. The following examples show each possible outcome for matched and not matched results:

- Example 1
- Example 2
- Example 3
- Example 4

| Application Match |  |  |  |
| --- | --- | --- | --- |
| **Application** | **Multimatch** | **Requested Domain** |  |
| server1.db.hr.company.com | server2.ui.hr.company.com |  |  |
| *.db.hr.company.com | Enabled | Matched | Not matched |
| *.ui.hr.company.com | Disabled | Not matched | Matched |
| *.hr.company.com | Enabled | Matched | Not matched |
| *.company.com | Disabled | Not matched | Not matched |

| Application Match |  |  |
| --- | --- | --- |
| **Application** | **Multimatch** | **Requested Domain** |
| server1.db.hr.company.com |  |  |
| server1.db.hr.company.com | Enabled | Matched |
| *.db.hr.company.com | Enabled | Matched |
| *.hr.company.com | Enabled | Matched |
| *.company.com | Disabled | Not matched |
| *.com | Enabled | Not matched |

| Application Match |  |  |  |
| --- | --- | --- | --- |
| **Application** | **Multimatch** | **Requested Domain** |  |
| server1.db.hr.company.com | server2.db.hr.company.com |  |  |
| server1.db.hr.company.com | Disabled | Matched | Not matched |
| *.db.hr.company.com | Enabled | Not matched | Matched |
| *.hr.company.com | Enabled | Not matched | Matched |
| *.company.com | Disabled | Not matched | Not matched |

| Application Match |  |  |
| --- | --- | --- |
| **Application** | **Multimatch** | **Requested Domain** |
| server2.ui.hr.company.com |  |  |
| server2.ui.hr.company.com | Enabled | Matched |
| *.ui.hr.company.com | Disabled | Not matched |
| *.hr.company.com | Enabled | Not matched |
| *.company.com | Disabled | Not matched |

### About IP Address- and IP Subnet-Based Multimatch

Even with an IP address, the default behavior of Private Access is to perform an exact match of applications. If two or more application segments cover the same destination address, Zscaler Client Connector attempts to match traffic to the more granular application segment. Consider the following examples of two application segments with overlapping IP addresses:

- Example: IP-Based Exact Match
- Example: IP-Based Multimatch

| Application | Applications | Protocol | Port |
| --- | --- | --- | --- |
| Subnet_AS | 10.0.0.0/24 | TCP, UDP | 1 to 52, 54 to 65535 |
| IPaddress_AS | 10.0.0.1/32 | TCP | 443 |

| Rule Number | Access Policy Name | Application Segment | User Group | Action |
| --- | --- | --- | --- | --- |
| 1 | Allow User A to access IPaddress_AS | IPaddress_AS | User A | Allow |
| 2 | Allow Everyone to access Subnet_AS | Subnet_AS | All | Allow |

In this scenario, the host at IP address 10.0.0.1 is contained within the IPaddress_AS application segment. If user B tries to access 10.0.0.1 on TCP port 443, they match the implicit block rule. User B does not match with rule number 1 because it is restricted to only User A. User B also does not match with rule number 2 even though it is for everyone, because the moment the IP address is created, the 10.0.0.1/32 host is carved out of Subnet_AS 10.0.0.0/24.

The primary use for Multimatch is when there are multiple possible application segment matches. The following tables show application segment configuration, the policy configuration, and how they match up.

| Application Segment Configuration |  |  |  |  |
| --- | --- | --- | --- | --- |
| Application Segment Name | Application | Protocol | Port | Multimatch |
| Subnet_AS | 10.0.0.0/24 | TCP, UDP | 1 to 52, 54 to 65535 | Enabled |
| IPAddress_AS | 10.0.0.1/32 | TCP | 443, 3389 | Enabled |

| Application Match and Policy Search Results |  |  |  |  |
| --- | --- | --- | --- | --- |
| # | Access Policy Name | Application Segment Name | Policy Action | User Group |
| 1 | Allow IPAddress_AS for Admin | IPAddress_AS | Allow | Admin_Grp |
| 2 | Allow Subnet_AS for All | Subnet_AS | Allow | All |

| Application Match and Policy Search Results |  |  |  |  |  |  |
| --- | --- | --- | --- | --- | --- | --- |
| User | Group | IP Address: Protocol & Port | Matched Application Segment with Multimatch | Matched Application Segment without Multimatch | Matched Policy with Multimatch | Matched Policy without Multimatch |
| user1 | Admin_Grp | 10.0.0.1:TCP+3389 | Subnet_AS IPAddress_AS | IPAddress_AS | Allow IPAddress_AS for Admin (#1) | Allow IPAddress_AS for Admin (#1) |
| user2 | IT_Grp | 10.0.0.1:TCP+3389 | Subnet_AS IPAddress_AS | IPAddress_AS | Allow Subnet_AS for All (#2) | No policy match. Blocked by implicit default block policy. |
| user1 | Admin_Grp | 10.0.0.1:TCP+22 | Subnet_AS | N/A | Allow Subnet_AS for All (#2) | Traffic is dropped at the client level |
| user2 | IT_Grp | 10.0.0.1:TCP+22 | Subnet_AS | N/A | Allow Subnet_AS for All (#2) | Traffic is dropped at the client level |
| user3 | IT_Grp | 10.0.0.1:TCP+80 | Subnet_AS | N/A | Allow Subnet_AS for All (#2) | Traffic is dropped at the client level |
| user3 | IT_Grp | 10.0.0.1:TCP+443 | Subnet_AS IPAddress_AS | IPAddress_AS | Allow Subnet_AS for All (#2) | No policy match. Blocked by implicit default block policy. |

Traffic that is dropped at the client level means that traffic matches the IP address, but it does not match the protocol and port. In this condition, traffic is not sent to the cloud for further processing. This means that policy evaluation does not occur, and the user is not able to access the application segment. In this case, traffic is not visible in the Private Access diagnostics.

[Image: Multimatch Validation page]

[Image: Advanced settings in the iOS app profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/using-application-scaling","lastmod":"2026-09-15T10:34Z","nid":"1542240"} -->
## Using Application Scaling

- Source: https://help.zscaler.com/zpa/using-application-scaling
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Application Management > Application Segments > Using Application Scaling
- Last modified: 2026-09-15T10:34Z
- Summary: Information on using application scaling for application segments for Private Access (ZPA).

This feature is in limited availability. To learn more, contact Zscaler Support.

Application Scaling enables organizations with large private application environments to scale beyond the standard [application limits](https://help.zscaler.com/unified/ranges-limitations#application-management). With Application Scaling enabled, tenants can scale to approximately 100,000 applications. It optimizes how application definitions are delivered and evaluated between Private Access (ZPA) and Zscaler Client Connector, reducing the amount of application data delivered to endpoints.

Instead of distributing the complete application inventory to every endpoint, Private Access delivers aggregated domain information, reducing the client-side configuration footprint and improving scalability.

If you are using [Source IP Anchoring](https://help.zscaler.com/zpa/configuring-source-ip-anchoring-direct) or [Zero Trust Branch](https://help.zscaler.com/zero-trust-branch/what-zero-trust-branch), continue to use the standard Private Access [application access](https://help.zscaler.com/zpa/understanding-application-access) as it is designed for those deployment scenarios.

## Prerequisites

To use Application Scaling, ensure the following prerequisites are met:

- App Connectors or Private Service Edges for Private Access are upgraded to version 24.298.1 or later. To learn more, see [About App Connectors](https://help.zscaler.com/zpa/about-connectors) and [About Private Service Edges](https://help.zscaler.com/zpa/about-private-service-edges).
- Zscaler Client Connector is upgraded to the following versions per OS:
  - Windows versions:
    - 4.7.0.88 or later
    - 4.6.0.282 or later
  - macOS versions: 4.5.2.98 or later
  - iOS version 4.4.1 or later To use the App Scaling feature for iOS applications, you must enable the [Use Tunnel SDK Version 4.3 or above setting](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#iOS-advanced) in the Zscaler Admin Console. See image.
  - Android version: 3.10 or later
  - Linux version: 4.2 or later

## Enabling App Scaling

Application Scaling is enabled at the tenant level. Contact Zscaler Support to request access.

[Image: Traffic Steering Advanced settings in the Zscaler Client Connector iOS App Profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/using-application-segment-import","lastmod":"2026-05-13T14:32Z","nid":"1540203"} -->
## Using Application Segment Import

- Source: https://help.zscaler.com/zpa/using-application-segment-import
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Application Management > Application Segments > Using Application Segment Import
- Last modified: 2026-05-13T14:32Z
- Summary: How to use the Application Segment Import feature in the Zscaler Admin Console.

Private Access (ZPA) allows you to bulk import application information to ease the process of application segment configuration. This speeds up the data entry process and eliminates the need to re-enter data if it changes over time. To learn more, see [About Application Segment Import](https://help.zscaler.com/zpa/about-application-segment-import).

To bulk import application segment data:

1. Go to **Policies** > **Access Control** > **Private Applications** > **Application Segment Import**.
2. Click **Download Sample CSV**. The sample template downloads to your system. See image. If you already have a CSV file containing your data, you can import the file.
3. Open the template file and add information for the following fields:
  - Application Name
  - Application FQDN/IP
  - Server IP
  - TCP Ports
  - UDP Ports
  - App Owner Contact
  - Application Importance
  - Hosting Location
  - Environment
4. Save the template.
5. Click **Import CSV**. See image.
6. Click **Browse File**to select your CSV file.
7. Click **Import**. See image. The data can take some time to import. You can leave the page and return to it later. If you import a file with entries that were included in a previous import, the new entries are automatically added as additional information to the existing entries of the same name.

After the data from the file is imported, the Application Segment Import page refreshes to show a table of all the application information categorized alphabetically by the Application Segment Name. A message appears confirming the number of successfully imported entries, as well as the number of failed downloads. You can click the link in the message to download a new file containing the failed entries and the reasons for their import failure.

The limit of application segments that can be imported is 50,000. If you attempt to import a file with more than 50,000 entries of application segment data, the import fails. Depending on how many application segments you import, the import time can increase.

After you import the data for the application segments, you can merge the application segments together. To learn more, see [Merging Imported Application Segments](https://help.zscaler.com/zpa/merging-imported-application-segments).

[Image: Download Sample CSV action in the Application Segment Import page]

[Image: The Import window with a CSV file selected.]

[Image: Click Import CSV.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/using-pattern-matching-application-segments","lastmod":"2026-09-10T10:53Z","nid":"1485996"} -->
## Using Pattern Matching for Application Segments

- Source: https://help.zscaler.com/zpa/using-pattern-matching-application-segments
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Application Management > Application Segments > Using Pattern Matching for Application Segments
- Last modified: 2026-09-10T10:53Z
- Summary: Information on using pattern matching for application segments for Private Access (ZPA).

This feature is in limited availability. To learn more, contact Zscaler Support.

Private Access (ZPA) supports patterns within FQDN-based application segments. Configuring applications with pattern definition helps to build optimal configurations. It also future proofs the configuration in the case where new servers are brought online and match the existing patterns for applications. This means no configuration changes are required in the application segment definition. To learn more, see [Configuring Defined Application Segments](https://help.zscaler.com/zpa/configuring-application-segments).

## Prerequisites

To configure pattern matching for application segments, ensure the following prerequisites are met:

- Multimatch must be enabled on the tenant and the specific application segment. To learn more, see [Using Application Segment Multimatch.](https://help.zscaler.com/zpa/using-app-segment-multimatch)
- App Connectors are upgraded to version 24.298.1 or later. To learn more, see [About App Connector Groups](https://help.zscaler.com/zpa/about-connector-groups).
- Zscaler Client Connector is upgraded to the following versions per OS:
  - Windows versions:
    - 4.7.0.88 or later
    - 4.6.0.282 or later
  - macOS version: 4.5.2.98 or later
  - iOS version: 4.4.1 or later To use the Private Access pattern matching feature for iOS applications, you must enable the [Use Tunnel SDK Version 4.3 or above setting](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) in the Zscaler Admin Console. See image.
  - Android version: 3.10 or later
  - Linux version: 4.2 or later

## Supported Patterns of Applications in Application Segments

The asterisk (*) and question mark (?) symbols are the supported characters for pattern configuration in application segments.

| **Pattern Type** | **Example** |
| --- | --- |
| Pattern matching in the initial part of the FQDN | *.acme.com *nonprod.acme.com |
| Pattern matching in the middle part of the application | psl*.ms.acme.com www.jira*.acme.com *w*.acme.com |
| Matching one character with “?” | w?w.jira.acme.com w??.jira.acme.com |

The maximum number of patterns an admin can configure is 500. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations).

### App Connector Selection

Pattern matching does not select the most specific application for App Connector selection. Therefore, ensure you have at least one App Connector group available for similar applications. To learn more, see [About App Connector Groups](https://help.zscaler.com/zpa/about-connector-groups).

When application access matches multiple application segments for pattern matching, the App Connector selection is based on the most specific application segments that are pattern matching, regardless of the application segment that matches the access policy rule.

The following table shows example application segment configurations:

| Application Segment Name | Domain or Pattern Match | Protocol & Port | Multimatch | Match | Server Group Name |
| --- | --- | --- | --- | --- | --- |
| AS1 | important-server*.eu.example.com | TCP/22 | Yes | Yes | SG1 |
| AS2 | important-server?.eu.example.com | TCP/22 | Yes | Yes | SG2 |
| AS3 | important-*.eu.example.com | TCP/22 | Yes | Yes | SG3 |
| AS4 | *.eu.example.com | All | Yes | Yes | SG4 |
| AS5 | *.example.com | All | No | No | SG5 |

When a user accesses important-server1.eu.example.com on the TCP protocol for port 22, it matches AS1, AS2, AS3, and AS4 because these are the most specific application segments with pattern matching. However, since AS4 is the wildcard application segment and AS5 does not have Multimatch enabled, the App Connector selection only applies to SG1, SG2, and SG3.

[Image: Advanced settings in the iOS app profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/using-wildcard-certificates-browser-access-applications","lastmod":"2026-09-18T02:53Z","nid":"1484096"} -->
## Using Wildcard Certificates for Browser Access Applications

- Source: https://help.zscaler.com/zpa/using-wildcard-certificates-browser-access-applications
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Browser Access > Using Wildcard Certificates for Browser Access Applications
- Last modified: 2026-09-18T02:53Z
- Summary: Information about wildcard certificates and how they can be used when defining Browser Access-enabled web applications for Private Access.

Private Access (ZPA) supports using wildcard certificates when defining Browser Access applications within an application segment. You can use a wildcard certificate for multiple fully qualified domain names (FQDNs) within a single application segment or within multiple application segments.

For example, you can create two application segments:

- App1 that contains app1.example.com
- App2 that contains app2.example.com

For both application segments, you use the same wildcard certificate: *.example.com.

You can also use a wildcard certificate with a wildcard application. However, while the wildcard application includes subdomains, the wildcard certificate only matches against one level. For example:

- A `*.example.com` wildcard application matches against `app1.example.com`, `app2.example.com`, and `app1.local.example.com`.
- A `*.example.com` wildcard certificate matches against `app1.example.com` and `app2.example.com`, but will not match against `app1.local.example.com`. In this example, Private Access does not consider the wildcard certificate to be valid for `app1.local.example.com`.

If you need to enable Browser Access for a particular subdomain, you must define a separate wildcard application (i.e., *.local.example.com) within an application segment that includes the equivalent wildcard certificate.

To learn more, see [About (Web Server) Certificates](https://help.zscaler.com/zpa/about-web-server-certificates), [About Browser Access](https://help.zscaler.com/zpa/about-browser-access), and [Configuring Defined Application Segments](https://help.zscaler.com/zpa/configuring-defined-application-segments).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/validating-client-hostname","lastmod":"2026-06-16T11:51Z","nid":"1485046"} -->
## Validating a Client Hostname

- Source: https://help.zscaler.com/zpa/validating-client-hostname
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Application Management > Application Segments > Validating a Client Hostname
- Last modified: 2026-06-16T11:51Z
- Summary: How to validate a client hostname in the Zscaler Admin Console.

Validating a client hostname allows you to enroll endpoints for [peer-to-peer connectivity](https://help.zscaler.com/zpa/administration/peer-peer-connectivity) so that you can accept incoming connections through Private Access (ZPA) from other clients. To enroll the endpoints, a regular expression of allowed hostnames is configured per tenant. This regular expression controls the endpoints to which Zscaler Client Connector allows the peer-to-peer connectivity. Endpoints whose FQDNs match this regular expression are enrolled. To learn more, see [Understanding Client-to-Client Connectivity](https://help.zscaler.com/zpa/understanding-client-client-connectivity) and [Understanding Server-to-Client Connectivity](https://help.zscaler.com/zpa/understanding-server-client-connectivity).

If an application configured for Privileged Remote Access (PRA) matches a valid client hostname configured for peer-to-peer connectivity, and the user's device is also configured for peer-to-peer connectivity, then PRA is not supported.

Prior to enabling peer-to-peer connectivity, the following prerequisites must be met:

- Ensure that the remote user has installed Zscaler Client Connector version 3.9.0.169 or later on the destination machine that they want to establish connectivity to. Zscaler Client Connector on the destination machine must have a machine tunnel deployed. To learn more, see [Understanding Zscaler Client Connector App Downloads](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-app-downloads).
- Ensure that endpoints or devices have valid, complete, and unique FQDNs.

To validate a client hostname:

1. Go to **Policies** > **Access Control** > **Private Applications** > **Defined Application Segments**.
2. Click the **Column** **Menu**icon ([Image: Column Menu icon]), and select **Client Hostname Validation**.

See image.

The **Edit Regular Expression**window appears.

1. In the **Regular Expression**field, enter a valid regular expression. See image. For example, enter the regular expression `.*.example.com` to establish a peer-to-peer connection for devices joined with domains matching this regular expression (e.g., `test.example.com`). If you have the same namespace for both your applications and workstations, Zscaler recommends creating different application segments for peer-to-peer connectivity and for application access. To successfully enable remote assistance, the application segment designated for peer-to-peer connectivity must match the regular expression entered.
2. Click **Save**.

[Image: Viewing the Column Menu in the Zscaler Admin Console]

[Image: Configure a regex in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-active-directory-protection-dashboard","lastmod":"2026-06-29T10:17Z","nid":"1515526"} -->
## Viewing the Active Directory Protection Dashboard

- Source: https://help.zscaler.com/zpa/viewing-active-directory-protection-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > AppProtection and Browser Protection Monitoring > Viewing the Active Directory Protection Dashboard
- Last modified: 2026-06-29T10:17Z
- Summary: Information on the Active Directory Protection dashboard and widgets accessible in the Zscaler Admin Console.

The Active Directory Protection dashboard provides information about the Active Directory policy activity in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Security > Active Directory Protection.

## Dashboard Tools

The Active Directory Protection dashboard displays the following information and functionality:

- **Time Range Filter**: View Active Directory Protection data over a period between **1 Hour** to **14 Days**, or you can select **Custom Range**. If you use **Custom Range**, the start date must be within the last 14 days. The end date automatically sets to the system's current time. By default, the dashboard displays information for events that occurred in the last hour. This filter applies to all widgets on the dashboard.

Log information in the dashboard is limited to 14 days. For longer access to the logs, use the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming-service).

- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **AppProtection Dashboard**: View the [AppProtection Dashboard](https://help.zscaler.com/zpa/viewing-appprotection-dashboard) for more information about AppProtection policy activity.
- **Browser Protection Dashboard**: View the [Browser Protection Dashboard](https://help.zscaler.com/zpa/viewing-browser-protection-dashboard) for information about browser sessions in your organization.
- **API Protection Dashboard**: View the [API Protection Dashboard](https://help.zscaler.com/zpa/viewing-api-protection-dashboard) for information about API traffic in API protection controls. This tab is only visible if the feature is enabled in your account.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 4 charts and a maximum of 8 charts can be selected.

## Widgets

The Active Directory Protection dashboard provides the following widgets:

- Top Users by KRB TGT Suspicious Activity
- Top Users by KRB TGT Errors
- Top Users by KRB TGS Errors
- Top Users by KRB TGS Suspicious Activity
- Top Users by LDAP Suspicious Queries
- Top Users by SMB Suspicious Activity
- Top Profile Violations
- Top Control Violations
- Top Users by Control Violations

The widget displays the top 10 Kerberos TGT suspicious activity within the selected time frame and lists them by user.

[Image: Kerberos TGT Suspicious Activity widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Click on a tab to switch between a chart of unique Active Directory logins and a chart of account harvesting indications.
- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users - KRB TGT Suspicious Activity**: Depending on the chart you've selected:
    - **Unique Active Directory Logins**: The number of unique Active Directory logins seen for the selected user. The percentage is the total unique Active Directory logins seen by that user within the top users for that category.
    - **Account Harvesting Indications**: The number of Active Directory login failures seen for the selected user. The percentage is the Active Directory login failures seen by that user within the top users for that category.
- Click a user and then click **View Logs** to be directed to log information matching that user in Active Directory Protection Diagnostics.

The widget displays the top 10 Kerberos TGT errors within the selected time frame and lists them by user. The error counts displayed do not include these common errors:

- KDC_ERR_PREAUTH_REQUIRED (25)
- KRB_AP_ERR_TKT_EXPIRED (32)
- KRB_ERR_RESPONSE_TOO_BIG (52)
- KDC_ERR_BADOPTION (13)
- KDC_ERR_NEVER_VALID (11)
- KRB_AP_ERR_SKEW (37)
- KDC_ERR_ETYPE_NOSUPP (14)
- KDC_ERR_S_PRINCIPAL_UNKNOWN (7)
- KRB_AP_ERR_MODIFIED (41)

[Image: Top Users by Kerberos TGT Errors widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users by KRB TGT Error(s)**: The number of errors for the selected user. The percentage is the errors held by that user within the top users for that category.
- Click a user and then click **View Logs** to be directed to log information matching that user in Active Directory Protection Diagnostics.

The widget displays the top 10 Kerberos TGS errors within the selected time frame and lists them by user. The error counts displayed do not include these common errors:

- KDC_ERR_PREAUTH_REQUIRED (25)
- KRB_AP_ERR_TKT_EXPIRED (32)
- KRB_ERR_RESPONSE_TOO_BIG (52)
- KDC_ERR_BADOPTION (13)
- KDC_ERR_NEVER_VALID (11)
- KRB_AP_ERR_SKEW (37)
- KDC_ERR_ETYPE_NOSUPP (14)
- KDC_ERR_S_PRINCIPAL_UNKNOWN (7)
- KRB_AP_ERR_MODIFIED (41)

[Image: Top 10 Kerberos TGS Errors widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users by KRB TGS Errors**: The number of errors for the selected user. The percentage is the errors held by that user within the top users for that category.
- Click a user and then click **View Logs** to be directed to log information matching that user in Active Directory Protection Diagnostics.

The widget displays the top 10 Kerberos TGS suspicious activity within the selected time frame and lists them by user.

[Image: Top 10 Kerberos TGS Suspicious Activity widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Click a tab to switch between a chart of Kerberoasting requests and a chart of Kerberos TGS activity.
- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users by KRB TGS Suspicious Activity**: Depending on the chart you've selected:
    - **Kerberoasting Requests**: The number of instances the user requested a ticket with a weak encryption (e.g., RC4 and DES encryption). The percentage is the total Kerberoasting requests held by that user within the top users for that category.
    - **KRB TGS Activity**: The number of instances where the user sends a TGS request for various services in Active Directory domain. The percentage is the total Kerberos TGS activity held by that user within the top users for that category.
- Click a section of the chart and then click **View Logs** to be directed to log information matching the control category in Active Directory Protection Diagnostics.

The widget displays the top 10 LDAP suspicious queries within the selected time frame and lists them by user.

[Image: Top 10 LDAP Suspicious Queries widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users** **by LDAP Suspicious Activity**: The number of suspicious queries for the selected user. The percentage is suspicious queries held by that user within the top users for that category.
- Click a user and then click **View Logs** to be directed to log information matching that user in Active Directory Protection Diagnostics.

The widget displays the top 10 SMB suspicious activity within the selected time frame and lists them by user. This activity data consists of session and user enumeration.

[Image: Top Users by SMB Suspicious Activity widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users** **by SMB Suspicious Activity**: The number of suspicious activity for the selected user. The percentage is suspicious activity held by that user within the top users for that category.
- Click a section of the chart and then click **View Logs** to be directed to log information matching the control category in Active Directory Protection Diagnostics.

The widget displays the top 10 profile violations within the selected time frame and lists them by name.

[Image: Top Profile Violations widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Hover over a violation to view the following:
  - **Name**: The profile violation name.
  - **Number of Top Profile Violation(s)**: The number of profile violations for the selected violation. The percentage is violations held within the category.
- Click a profile violation and then click **View Logs** to be directed to log information matching that violation in Active Directory Protection Diagnostics.

This widget displays the top 10 control violations within the selected time frame and categorizes them by name. The top control violations are categorized by control number and name, in the ControlNumber:ControlName format.

[Image: Top Control Violations widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

Hover over a section of the chart to view the name of the control violation and the percentage of control violations held within the Top Control Violations category.

The widget displays the top 10 users by control violations within the selected time frame and lists them by user.

[Image: Top Users by Control Violations widget on the Active Directory Protection Dashboard in the Zscaler Admin Console]

- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users by Control Violation(s)**: The top users with their number of control violations. The percentage is control violations held by that user within the top users for that category.
- Click a user and then click **View Logs** to be directed to log information matching that user in Active Directory Protection Diagnostics.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-agent-dashboard","lastmod":"2026-09-11T07:14Z","nid":"1498186"} -->
## Viewing the Agent Dashboard

- Source: https://help.zscaler.com/zpa/viewing-agent-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Microsegmentation > Dashboard > Viewing the Agent Dashboard
- Last modified: 2026-09-11T07:14Z
- Summary: How to view the Agent dashboard for Microsegmentation in the Zscaler Admin Console.

The Agent dashboard provides information about agent data in your organization.

To view the Agent dashboard, from the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Dashboard** > **Microsegmentation** **Agent**.

## Dashboard Tools

The Agent dashboard displays the following information and functionality:

- **Time Range Filter**: View agent data for a specified time period, from 1 Hour to 14 Days.
- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **Agent Type**: Select the type of agent: Virtual Machines, Kubernetes Node, or Kube Connector.
- **Agent Name**: Select an agent to view graphs of the CPU usage of the agent, system, agent memory, and system memory. You can select multiple agents.

[Image: The Agent dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-and-managing-events-diagnostics","lastmod":"2026-05-15T07:06Z","nid":"1485451"} -->
## Viewing and Managing Events Diagnostics

- Source: https://help.zscaler.com/zpa/viewing-and-managing-events-diagnostics
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Viewing and Managing Events Diagnostics
- Last modified: 2026-05-15T07:06Z
- Summary: Information about Events diagnostics and data related to the events, accessible within the Zscaler Admin Console.

This article describes how to view and filter event logs for notifications. Admins subscribe to events to receive email notifications from the Zscaler Admin Console. After an event is triggered, admins receive alerts of these notifications via email. To learn more, see [About Notifications](https://help.zscaler.com/zpa/about-notifications). The Events Diagnostics page displays these notifications.

## Accessing Events Diagnostics

To access diagnostics for events:

1. Go to **Logs**>**Insights**>**Diagnostics**.
2. From the **Log Type**drop-down menu, select **Events**.

By default, the information for all events is displayed for notifications that occurred in the last 24 hours. You can do the following:

- Click the **Calendar** drop-down menu to change the time range. In the **Calendar** drop-down menu, you can select a preset range or specify a custom start and end date. If you use **Custom Range**, the start date must be within the last 14 days.
- Click the **Time Zone** icon to change the time zone.
- Click the **Refresh** icon to refresh the diagnostics page.
- Click the **Settings**icon to configure the diagnostics settings.

Zscaler retains logs for rolling periods of up to 6 months depending on your subscription. To learn more, see [ZPA Customer Logs and Data](https://help.zscaler.com/customer-logs-fair-use/zpa-customer-logs-and-data). You can also view your logs or stream logs in real time using the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming). Data in the [dashboard](https://help.zscaler.com/zpa/dashboard-diagnostics) might be more recent than the data presented within Diagnostics for the same time range.

When you use **Custom Range**, you can search for data within the past 6 months of the current date. The data retrieved begins from the moment of feature enablement.

When the date range exceeds 14 days:

- Not all the log fields are populated.
- Metrics including aggregates and trends are not available.

This feature and its procedures are in limited availability. To learn more, contact Zscaler Support.

See image.

[Image: Events Diagnostics tools]

By default, the table displays the **Total**number of events. To change this, select one of the following filters:

- **High Priority**: The number of high priority events.
- **Medium Priority**: The number of medium priority events.
- **Low Priority**: The number of low priority events.

See image.

[Image: Diagnostics widgets]

## Configuring Settings

To configure settings for diagnostics pages in the Zscaler Admin Console:

1. Go to **Logs**> **Insights**> **Diagnostics**.
2. Click the **Settings** icon ([Image: Settings icon within the diagnostics pages]). The **Settings** drawer appears.
3. In the **Settings** drawer, select the default filter operator (i.e., **Equals** or **Contains**) from thedrop-down menu. The **Default Filter Operator** is set to **Equals** by default. See image.
4. Click **Save** to apply your changes. The selected filter operator is saved for future sessions.

[Image: Viewing the Settings drawer in the diagnostics pages]

## Filtering Event Diagnostics

On the Events page, you can apply filters or drill down further into the log data. By default, no filters are applied.

To configure filters using the query builder:

1. Click **Add Filters** and select a filter from the drop-down menu.
  - View query builder filters.
2. Select a Boolean operator from the drop-down menu (e.g., **Equals**, **Not Equals**).
3. Select the fields from the drop-down menu or enter the values required for the filter. The field or value required is determined by the filter you are configuring.

See image.

[Image: Events Diagnostics filtering]

1. Click **Apply**.

You can also perform the following actions in the query builder:

- Click **Add Filters**to apply more filters.
- Click the **Delete**icon ([Image: Delete icon in the Zscaler Admin Console]) to remove an added filter, and then click **Apply**.
- Click **Clear All**to remove all filters.
- Click the **Copy** icon to save the filter query details. If you or another Private Access (ZPA) admin accesses **Diagnostics**, you can paste the query into the field by clicking the **Clipboard** icon.

See image.

[Image: Filtering tools]

- **App Connector: Name**: See event logs by the App Connector.
- **Backup Name**: See event logs by the [backup](https://help.zscaler.com/zpa/about-backup-and-restore).
- **Cloud Connectors**: See event logs by the Cloud Connector.
- **Event Category:**See event logs by category(i.e.,**Authentication**,**Backup Configuration**, **Connectivity and Upgrade**, **Enrollment**, **System Resource**, **Usage Metrics**).To learn more, see the Category section.
- **Event Component:**See event logs by component (i.e., **App Connectors**, **Backup and Restore**, **Cloud Connectors**, **Private Service Edges**,and **Zscaler Client Connector**).To learn more, see the Component section.
- **Event Name:**See event logs by the type of the event. To learn more, see the Events table.
- **Event Priority:**See event logs by the priority of the event (i.e.,**High**, **Medium**, **Low**).
- **Private Service Edge: Name**: See event logs by the Private Service Edge for Private Access.

The table displays the following data about events:

- Timestamp
- Event
- Category
- Component
- Priority
- Action

You can expand each row to see more details, or click **Expand** **All**or **Collapse** **All** to expand or collapse all rows within the table. By default, the table displays 20 transactions. You can scroll to load more transactions. Within the table, you can click the **Filter**icon ([Image: Filter icon within the Zscaler Admin Console]) next to certain field names to drill down further into the data. The filter query section within the page updates automatically to include the proper filter for the field name you selected, along with the applicable Boolean operator configuration for that field.

The data and time when the event log was generated. The column sorts requests by the date and start time in descending order. You can click the **Arrow**icon ([Image: Arrow icon]) to sort the requests in ascending order.

The time displayed is based on the time the event was triggered.

Expanding an event provides the following:

- Event Log: View, download, and copy the raw JSON for the event:
  - Click the **View Log** icon ([Image: View Log icon]) to display the Raw JSON for the event within the Zscaler Admin Console.
  - Click the **Download**icon ([Image: Download icon]) to download the raw JSON for the event to a text (.txt) file.
  - Click the **Copy** icon ([Image: Copy icon]) to copy the raw JSON text for the event to your clipboard.

The type of event that triggered the notification.

Expanding an event provides information regarding the status and outcome of the event.

The following table provides a list of events, as well as the category and component the event belongs to:

| Event | Category | Component | Description |
| --- | --- | --- | --- |
| Application Exceeded Count Limit | Usage Metrics | App Connectors | An event indicating when the component's application count has exceeded the limit. The Application Exceeded Count Limit value must be an integer between 4,500 and 6,000. The default value is set to 4,500. |
| Backup Completed | Backup Configuration | Backup and Restore | An event indicating when the backup is completed. To learn more, see [About Backup and Restore](https://help.zscaler.com/zpa/about-backup-and-restore) and [Restoring Policies and Configurations from a Backup](https://help.zscaler.com/zpa/restoring-policies-and-configurations-backup). |
| Backup Failed | Backup Configuration | Backup and Restore | An event indicating when the backup has failed. To learn more, see [About Backup and Restore](https://help.zscaler.com/zpa/about-backup-and-restore) and [Restoring Policies and Configurations from a Backup](https://help.zscaler.com/zpa/restoring-policies-and-configurations-backup). |
| Bandwidth Utilization Exceeded Limit | Usage Metrics | App Connectors, Network Connectors, Private Cloud Controllers, Private Service Edges | An event indicating when the component's bandwidth utilization has exceeded the limit. The Bandwidth Utilization Exceeded Limit value must be an integer greater than or equal to 250 Mbps. The default value is set to 250. |
| Certificate Signing Request Invalid | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating Certificate Signing Request (CSR) is invalid for the selected components. |
| Certificate Signing Request Not Found | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the CSR is not found for the selected components. |
| Certificate Signing Request Not Found for Issued Certificate | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the CSR for the selected components is not found for the issued certificate. |
| Control Connection Disconnected | Connectivity and Upgrade | App Connectors, Private Service Edges | An event indicating when the control connection for App Connectors or Private Service Edges disconnects. This event cannot be configured and is disabled by default. To learn more, contact Zscaler Support. |
| CPU Exceeded Limit | Usage Metrics | App Connectors, Network Connectors, Private Cloud Controllers, Private Service Edges | An event indicating when the component's CPU utilization has exceeded the limit. The CPU Exceeded Limit value must be an integer between 75% and 99%. The default value is set to 75. To learn more, see [Monitoring App Connector Performance](https://help.zscaler.com/zpa/monitoring-connector-performance#cpu). |
| CPU Starvation | System Resource | App Connectors | An event indicating when the App Connector's CPU is missing resources needed for operations. This event cannot be configured and is disabled by default. To learn more, contact Zscaler Support. |
| Disk Space Exceeded Limit | Usage Metrics | App Connectors, Network Connectors, Private Cloud Controllers, Private Service Edges | An event indicating when the component's available disk space is less than the indicated threshold. The Disk Space Exceeded Limit value must be an integer between 0 and 2,048 MB. The default value is set to 2,048. To learn more, see [Monitoring App Connector Performance](https://help.zscaler.com/zpa/monitoring-connector-performance#diskspace). |
| Duplicate Certificate Signing Request | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating that there is a duplicate CSR for the selected components. |
| Duplicate Serial Number | Enrollment | Zscaler Client Connector | An event indicating that there is a duplicate serial number during Zscaler Client Connector enrollment. |
| Enrollment Certificate Expired | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when an enrollment certificate has expired for the selected component. To learn more, see [About Enrollment (CA) Certificates](https://help.zscaler.com/zpa/about-enrollment-ca-certificates). |
| Enrollment Certificate Invalid | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating an invalid enrollment certificate for the selected components. |
| Enrollment Completed | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the enrollment of the component is completed. |
| Enrollment Failed | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the enrollment for the selected component has failed. |
| Entity Limit Exceeded | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when an entity limit is exceeded for a component. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations). |
| File Descriptors Exhausted | Usage Metrics | App Connectors, Network Connectors, Private Cloud Controllers, Private Service Edges | An event indicating when the component's file descriptors are exhausted. The File Descriptors Exhausted value must be an integer between 75% and 99%. The default value is set to 75. To learn more, see [Monitoring App Connector Performance](https://help.zscaler.com/zpa/monitoring-connector-performance#filedscrpt). |
| Invalid Fingerprint | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when a fingerprint for the selected components is invalid. |
| Invalid Signature | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when a signature for the selected components is invalid. |
| Invalid System Listen IP Configuration | System Resource | Private Service Edges | An event indicating when the listen IP address configuration for a Private Service Edge is invalid. |
| Issued Certificate Missing | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the issued certificate is missing for the selected components. |
| Issued Certificate Revoked | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the issued certificate is revoked for the selected components. |
| Last Component Disconnected | Connectivity and Upgrade | App Connectors, Private Service Edges | An event indicating when the component last disconnects for the selected component. |
| Missing Signature | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the signature for the selected components is missing during enrollment. |
| Outdated Component Manager Version | Connectivity and Upgrade | App Connectors, Private Service Edges | An event indicating when the Manager version is outdated for the selected components. |
| Provisioning Key Disabled | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the provisioning key is disabled for the selected components. |
| Provisioning Key Expired | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the provisioning key is expired for the selected components. |
| Provisioning Key Mismatched | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the provisioning key is mismatched for the selected components. |
| Provisioning Key Not Found | Enrollment | App Connectors, Cloud Connectors, Private Service Edges, Network Connectors | An event indicating when the provisioning key is not found for the selected components. Raw logs for this event include component name, when the component was updated, and who updated the component. This event cannot be configured. To learn more, contact Zscaler Support. |
| Provisioning Key Utilization Exceeded Limit | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the provisioning key has exceeded the limit for the selected components. |
| Public Key in Certificate Signing Request and Issued Certificate Mismatched | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the public key in the CSR and the issued certificate are mismatched for the selected components. |
| Restore Completed | Backup Configuration | Backup and Restore | An event indicating when the restore is completed. To learn more, see [About Backup and Restore](https://help.zscaler.com/zpa/about-backup-and-restore) and [Restoring Policies and Configurations from a Backup](https://help.zscaler.com/zpa/restoring-policies-and-configurations-backup). |
| Restore Failed | Backup Configuration | Backup and Restore | An event indicating when the restore has failed. To learn more, see [About Backup and Restore](https://help.zscaler.com/zpa/about-backup-and-restore) and [Restoring Policies and Configurations from a Backup](https://help.zscaler.com/zpa/restoring-policies-and-configurations-backup). |
| SCIM Users Successfully Deleted | Enrollment | Zscaler Client Connector | An event indicating when SCIM users are successfully deleted. |
| Source Port Consumption Exhausted | Usage Metrics | App Connectors, Network Connectors, Private Cloud Controllers, Private Service Edges | An event indicating when the component's source TCP or UDP ports are exhausted. The exhausted values must be an integer between 75% and 99%. The default value is set to 75. |
| System Memory Exceeded Limit | Usage Metrics | App Connectors, Network Connectors, Private Cloud Controllers, Private Service Edges | An event indicating when the component's system memory has exceeded the limit. The System Memory Exceeded Limit value must be an integer between 75% and 99%. The default value is set to 75. To learn more, see [Monitoring App Connector Performance](https://help.zscaler.com/zpa/monitoring-connector-performance#memory). |
| Timestamp Expired | Enrollment | App Connectors, Private Service Edges, Cloud Connectors | An event indicating when the timestamp is expired for the selected components (e.g., an App Connector sends an old timestamp due to a Network Time Protocol error). |
| Upgrade Complete | Connectivity and Upgrade | App Connectors, Private Service Edges | An event indicating when an upgrade is complete for the selected components. |
| Upgrade Failed | Connectivity and Upgrade | App Connectors, Private Service Edges | An event indicating when an upgrade fails for the selected components. |

The category of the event. Events are grouped by the component issues related to the following categories:

- **Authentication**: Issues related to authentication.
- **Backup Configuration**: Issues related to configuration backups and backups that are created manually or automatically.
- **Connectivity and Upgrade**: Issues related to component connectivity and upgrades.
- **Enrollment**: Issues related to enrollment.
- **System Resource**: Issues related to component system resources.
- **Usage Metrics**: Issues related to usage metrics.

The component associated with the event. The following components are applicable to the events:

- **App Connectors**: To learn more, see [About App Connectors](https://help.zscaler.com/zpa/about-connectors).
- **Backup and Restore**: To learn more, see [About Backup and Restore](https://help.zscaler.com/zpa/about-backup-and-restore).
- **Private Service Edges**: To learn more, see [About Private Service Edges](https://help.zscaler.com/zpa/about-private-service-edges).
- **Cloud Connectors**: To learn more, see [About Cloud Connectors](https://help.zscaler.com/zpa/about-cloud-connectors).
- **Zscaler Client Connector**: To learn more, see [What Is Zscaler Client Connector?](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)

Expanding an event provides the following:

- **App Connector Name**: The name of the App Connector.
- **App Connector ID**: The ID of the App Connector. Click the **Copy** icon to copy the ID to your clipboard.
- **Backup Name**: The name of the backup.
- **Backup ID**: The ID of the backup. Click the **Copy**icon to copy the ID to your clipboard.
- **Cloud Connector Name**: The name of the Cloud Connector.
- **Cloud Connector ID**: The ID of the Cloud Connector. Click the **Copy**icon to copy the ID to your clipboard.
- **Private Service Edge Name**: The name of the Private Service Edge.
- **Private Service Edge ID**: The ID of the Private Service Edge. Click the **Copy**icon to copy the ID to your clipboard.

Indicates the priority of the notification (i.e., **Low**, **Medium**, and **High**).

Click the **Add Notification** icon ([Image: Add Notification icon]) to open the **Add Notification** page.

On the **Add Notification** page:

- Step 1: General Information
- Step 2: Events
- Step 3: Action
- Step 4: Review

1. On the **General Information** tab, provide the necessary details for the following sections:

- **Name**: The name of the notification.
- **Status**: Indicates the status of the notification (i.e., **Enabled**or **Disabled**). By default, the status is set to **Enabled**.

- Select a Component
- Select a Category
- Select a Priority

1. Click **Next**.

[Image: Add Notification page]

On the **Events**tab:

1. Click **Add Events**.
2. Select the desired event.

For events that require text input, enter an integer value within the supported range. To learn more, see the Events table.

See image.

1. Click **Next**.

On the **Action**tab:

- Configure the Throttling
- Configure the Recipients

1. Review your notification settings.
2. Click **Save**.

[Image: Step 2 of the Add Notification window]

Select a component from the drop-down menu. The following is a list of available components. To learn more, see the Component section.

Click the **Delete**icon ([Image: Delete icon]) to remove a selected component. After a component is selected, a drop-down menu appears for the **Categories**, **Priorities**, and selection of the components.

After a component is selected, select the desired App Connectors, Cloud Connectors, or Private Service Edges from the list of available components:

A drop-down menu is not available for the **Zscaler Client Connector**component.

- **App Connectors**: Select an App Connector from the drop-down menu. You can search for a specific App Connector, select an individual App Connector, click **Select All Displayed** to select all App Connectors displayed in the drop-down menu, click **Clear All** to remove all selections, or click the **Delete**icon next to the selected App Connector to remove it. All App Connectors are selected by default.
- **Cloud Connectors**: Select a Cloud Connector from the drop-down menu. You can search for a specific Cloud Connector, select an individual Cloud Connector, click **Select All Displayed** to select all Cloud Connectors displayed in the drop-down menu, click **Clear All** to remove all selections, or click the **Delete**icon next to the selected Cloud Connector to remove it. All Cloud Connectors are selected by default.
- **Private Service Edges**: Select a Private Service Edge from the drop-down menu. You can search for a specific Private Service Edge, select an individual Private Service Edge, click **Select All Displayed** to select all Private Service Edges displayed in the drop-down menu, click **Clear All** to remove all selections, or click the **Delete**icon next to the selected Private Service Edge to remove it. All Private Service Edges are selected by default.

Select a category from the drop-down menu. You can select an individual category, click **Select All**to select all categories displayed in the drop-down menu, or click **Clear All** to remove all selections. All categories are selected by default. To learn more, see the Categorysection.

Select the priority of the notification (i.e., **Low**, **Medium**, and **High**). You can select an individual priority, click **Select All**to select all priorities displayed in the drop-down menu, or click **Clear All** to remove all selections. All priorities are selected by default.

**Throttling**enforces limits and timeout durations on the notification. Throttling is set to **Disabled**by default. When **Throttling**is **Enabled**, the **Throttling Limit** and **Throttling Timeout** fields appear.

- **Throttling Limit**: Enter an integer value to indicate the throttling limit.
- **Throttling Timeout**: Enter an integer value in hours to indicate the throttling timeout.

For example, the **Throttling Limit** value is 3, and the **Throttling Timeout**value is 1 hour. If the notification is set to trigger when the App Connector CPU is greater than 80%, the recipients receive only three email notifications within the last hour if the CPU of the App Connector exceeds the 80% threshold.

See image.

[Image: Throttling in the Add Notification page]

Under the Recipients section:

1. Select the recipients from the drop-down menu. You can search for a specific recipient, select an individual recipient, click **Select All Displayed** to select all recipients displayed in the drop-down menu, click **Clear All** to remove all selections, or click the **Delete** icon next to the selected recipient to remove it.
2. In the **Distribution List** field, enter the desired distribution list alias in the following format: `example@test.com`.

A maximum of 5 recipients combined (this includes both **Recipients** and **Distribution Lists**) is allowed.

1. Click **Add Items** so that the distribution list receives notifications after the notification is configured.

See image.

1. Click **Next**.

[Image: Step 3 of the Add Notification window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-and-managing-zscaler-client-connector-download-links","lastmod":"2026-04-27T15:41Z","nid":"1484326"} -->
## Viewing and Managing Zscaler Client Connector Download Links

- Source: https://help.zscaler.com/zpa/viewing-and-managing-zscaler-client-connector-download-links
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > User Portal > Viewing and Managing Zscaler Client Connector Download Links
- Last modified: 2026-04-27T15:41Z
- Summary: How to add or update Zscaler Client Connector download links in the Zscaler Admin Console.

After you [create a user portal](https://help.zscaler.com/zpa/configuring-user-portals) for your organization, you can [add links to any applications](https://help.zscaler.com/zpa/configuring-user-portal-links) that your employees and partners are authorized to access. If the application requires that Zscaler Client Connector is installed on the user's device, you can provide links within the user portal to allow them to download the proper Zscaler Client Connector installer for their OS.

You can add a download link from the Client Connector Download Links page to the proper installer for the following PC and mobile operating systems:

- Apple iOS
- Apple macOS
- Google Android
- Linux
- Microsoft Windows

You must determine which versions of the Zscaler Client Connector installer are approved for your organization. You are also responsible for maintaining these Zscaler Client Connector download links on the portal as newer versions become available.

To add or update a Zscaler Client Connector download link:

1. Go to **Policies** > **Access Control** > **Clientless** > **Client Connector Download Links**.
2. Enter the URL of the Zscaler Client Connector installer for each OS you support. You can copy or paste the **Download Link** from the [Zscaler Client Connector App Store](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store), or use any other valid download URL for your organization. [Image: Viewing and managing Zscaler Client Connector download links]
3. Click **Save**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-api-protection-dashboard","lastmod":"2026-06-29T10:13Z","nid":"1499836"} -->
## Viewing the API Protection Dashboard

- Source: https://help.zscaler.com/zpa/viewing-api-protection-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > AppProtection and Browser Protection Monitoring > Viewing the API Protection Dashboard
- Last modified: 2026-06-29T10:13Z
- Summary: Information on the API Protection dashboard and widgets accessible within the Zscaler Admin Console.

The API Protection dashboard provides information about the API Protection activity in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Security > API Protection.

See image.

[Image: Viewing the API Protection dashboard]

## Dashboard Tools

The API Protection dashboard displays the following information and functionality:

- **Time Range Filter**: View AppProtection data over a period between **1 Hour** to **14 Days**, or you can select **Custom Range**. If you use a **Custom Range**, the start date must be within the last 14 days. The end date automatically sets to the system's current time. By default, the dashboard displays information for events that occurred in the last hour. This filter applies to all widgets on the dashboard.
- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 4 charts and a maximum of 7 charts can be selected.

See image.

[Image: About the API Protection Dashboard tools]

## Widgets

The API Protection dashboard provides the following widgets:

- Recent API Activities in Last 14 Days
- Recent Blocked APIs
- Recent API Activity with Sensitive Information
- Recent API Traffic Violations
- Recent API Methods
- API Error Responses
- Recent Users with Violations

The widget displays the top 10 most recent activities for the API in the last 14 days. They are listed from the most recent API activity at the top to the least recent API activity at the bottom.

[Image: Top APIs Discovered in 14 days widget]

Hover over an API name to view the following:

- **Name**: The name of the API.
- **Number of Recent API Activities in last 14 Days**: The amount of times the API appeared in the past 14 days and the percentage of times the API appeared within the Top APIs Discovered in 14 Days category.

Click an API name and then click **View Logs** to be directed to log information matching that API in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the top 10 most recent blocked APIs within the selected time frame. They are listed from the most frequent blocked APIs at the top to the least frequent APIs at the bottom.

[Image: Top Blocked APIs widget]

Hover over an API name to view the following:

- **Name**: The name of the API.
- **Number of Recent Blocked APIs**: The amount of times the API appeared within the selected time frame and the percentage of times the API appeared within the Number of Top Blocked APIs category.

Click an API name and then click **View Logs** to be directed to log information matching that blocked API in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the amount of recent API activity with sensitive information categories (US Social Security numbers, Brazilian CPF numbers, and credit card numbers) in the selected time frame. Zscaler does not store the details or specific information; only the sensitive information category and how frequently it appeared in the time frame are stored.

[Image: Sensitive Information Disclosure widget]

Hover over an area of the chart to view the following:

- Displays the percentage of sensitive information transactions that occurred within the selected time frame. Zscaler does not store the specific data for sensitive information, only the category type itself is stored.
- **Click for more information**: Click this option to show the **View Logs** option. Click **View Logs** to be directed to log data matching that disclosed sensitive information transaction in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics). Zscaler only displays the amount of transactions and does not store any details related to sensitive information.

The widget displays the top 10 most recent API traffic violations and their respective violations within the selected time frame. They are listed from the most frequent API traffic violations at the top to the least frequent violations at the bottom.

[Image: Top API Control Violations widget]

Hover over an area of the chart to view the following:

- **Name**: The name of the API control and the associated violation.
- **Number of Recent API Traffic Violations**: The amount of times the violation with its related API traffic violation appeared within the selected time frame and the percentage of times the API control violation appeared within the Top API Traffic Violations category.

Click an API control name and then click **View Logs** to be directed to log information matching that API control in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the top 10 API methods (i.e., GET, POST, PUT, etc.) for APIs within the selected time frame. They are listed from the most frequent method used at the top to the least frequent method used at the bottom.

[Image: Top Methods widget]

Hover over an area of the chart to view the following:

- **Name**: The name of the API method.
- **Number of Recent API Methods**: The amount of times the method was used within the selected time frame and the percentage of times the method appeared within the Recent API Methods category.

Click a method and then click **View Logs** to be directed to log information matching that API method in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the top 10 most recent API error responses within the selected time frame. They are listed from the most frequent API error responses at the top to the least frequent API error responses at the bottom.

[Image: Top Errors widget]

Hover over an area of the chart to view the following:

- **Name**: The name of the error.
- **Total Transactions**: The complete number of transactions for the selected error within the selected time frame.
- **Errors for this selection**: The total amount of that error type within the selected time frame and the percentage of times that error type appeared within the Top Methods category.
- **Analyze by**: Click **Top URLs** to gain deeper insight into the top URLs for a specific error type. This widget displays the top 10 URLs for a selected error type within the selected time frame. They are listed from the most frequent URLs at the top to the least frequent URLs at the bottom.
  - **Name**: The name of the URL.
  - **Total Transactions**: The complete number of error transactions for the selected URL within the selected time frame.
  - **Errors for this selection**: The total amount of errors for the selected URL in the selected time frame and the percentage of times that the error for that URL appeared with the selected error type.

See image.

Click an error and then click **Show in Logs** to be directed to log information matching that API error in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the most recent users with violations within the selected time frame. They are listed from the most frequent users with violations at the top to the least frequent users with violations at the bottom.

[Image: Top Users widget]

Hover over an area of the chart to view the following:

- **Name**: The IdP user name for the API user.
- **Total Transactions**: The complete number of transactions for that IdP user.
- **Analyze by**: Click **Top URLs** to gain deeper insight into the top URLs for a specific user. This widget displays the top 10 URLs for a selected user within the selected time frame. They are listed from the most frequent URLs at the top to the least frequent URLs at the bottom.
  - **Name**: The name of the URL.
  - **Total Transactions**: The complete number of transactions for the selected URL within the selected time frame.

See image.

Click an error and then click **Show in Logs** to be directed to log information matching that API error in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

[Image: Top URLs widget within the Top Errors widget]

[Image: Top URLs widget within the Top Users widget]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-app-connectors-dashboard","lastmod":"2026-06-29T10:06Z","nid":"1484596"} -->
## Viewing the App Connectors Dashboard

- Source: https://help.zscaler.com/zpa/viewing-app-connectors-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > App Connector Monitoring > Viewing the App Connectors Dashboard
- Last modified: 2026-06-29T10:06Z
- Summary: Information on the App Connectors dashboard and widgets accessible within the Zscaler Admin Console.

The App Connectors dashboard provides information about the App Connectors for your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > App Connectors.

See image.

## Dashboard Tools

The App Connectors dashboard displays the following information and functionality:

- **Time Range Filter**: View user data over a period between **30 Mins** to **14 Days**, or select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 14 days. This filter applies to all widgets on the dashboard. By default, the dashboard displays information for events that occurred in the last 30 minutes.

Due to the way data is aggregated for different time period filters, the same point in time in an [Activity Monitor widget](https://help.zscaler.com/zpa/viewing-app-connectors-dashboard#activitywidgets) may show slightly different values depending on the time chosen. For example, the data with the **30 mins** time period filter at 3:00 PM may not match the data with the **14 Days** time period filter for the same date at 3:00 PM.

- **Refresh Icon**: Refresh the dashboard to reflect the most current information. The dashboard displays the most recent information from the last five minutes.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 4 charts and a maximum of 8 charts can be selected.

See image.

## Top App Connector Widgets

The Top App Connector widgets provide an overview of the peak or top metrics for the relevant App Connectors in the selected time range.

Four widgets are selected automatically when you access the dashboard. At least four widgets must be selected for the widgets to display, and no more than 8 widgets are available to view at one time.

View the widgets.

Clicking on any of the App Connectors in the widgets takes you to the [User Activity Diagnostic logs page](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) filtered for this App Connector.

## Activity Monitor Widgets

The Activity Monitor widgets provide trend information about selected App Connectors in the selected time range. If no App Connectors are selected, the top App Connectors from the Peak Memory Utilization widget are selected by default.

Three widgets are selected automatically when you access the dashboard. At least three widgets must be selected for the widgets to display, and no more than 6 widgets are available to view at one time.

View the widgets.

Widgets may show solid lines or dashed lines. Solid lines represent actual data for the time period. Dashed lines indicate the expected trajectory of the data, but it isn't actual data. You can select a point on the lines in a widget to see the exact date, time, and relevant numbers for the App Connectors as related to the particular widget. You can also click **View Logs** to see the [User Activity Diagnostic logs page](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) filtered for the App Connectors in the widget.

See image.

Select a part of the chart to zoom in for greater detail. A blue box shows the chosen portion of the widget, and the widget shows this selected smaller time period.

See image.

Click **Zoom Out** to view the original widget.

See image.

For each widget, you can deselect the listed App Connectors to change what items are tracked within the widget. You can also search within the widget to reduce the listed App Connectors that appear in the widget. To search, enter part or all of an App Connector name or use the following search query options with >, <, or = operators:

- name: The name of the App Connector (e.g., name = MyAppConnector).
- value: A numerical value specific to the widget (e.g., value < 40).

See image.

### Filtering App Connectors

You can filter the Activity Monitor charts and App Connector Details table by selecting the App Connectors you want to review. The available filters are **App Connectors** and **App Connector Groups**.

See image.

When filtering by App Connectors and App Connector groups, the selected App Connectors and App Connectors within the selected App Connectors groups are shown.

- Click here to view an example.

The filters between App Connectors and App Connector groups use the OR operator, instead of AND, to help compare App Connectors in App Connector groups.

There is a limit of 25 App Connectors you can select at one time. If you haven't selected any App Connectors and select an App Connector group that contains more than 25 App Connectors, then the first 25 App Connectors for the selected App Connector group are used in the filter. If you select App Connectors and then select App Connector groups in a way that exceeds the limit, you see an error message and need to adjust your selection.

Within the **App Connector Group** filter, you can see the number of App Connectors associated with the App Connector group.

See image.

If no App Connectors are selected, the top App Connectors from the **Peak Memory Utilization** chart are selected by default.

## App Connector Details

The App Connector Details table provides information about the App Connectors selected in the Activity Monitor section. If no App Connectors are selected, the top App Connectors from the **Peak Memory Utilization** widget are selected by default.

The table covers:

- **App Connector**: The name of the App Connector.
- **App Connector Group**: The name of the group the App Connector is included in.
- **Location**: The city and country that the App Connector is connecting from.
- **Actions**:
  - **View**: View the App Connector Details and App Connector Latency pages.
  - **Edit**: Edit the App Connector.
  - **Logs**: View the [User Activity Diagnostic logs page](https://help.zscaler.com/zpa/about-user-activity-diagnostics) filtered for this App Connector.

See image.

### Evaluating Individual App Connector Details and Latency

For each App Connector, you can click the View icon for an App Connector in the App Connector Details table. For each App Connector, you can view:

- Details
- Latency

The Details tab is automatically selected.

See image.

- **Top Errors**: Displays up to the top 100 App Connectors that had the most errors in the selected time range. This widget displays automatically when first accessing the dashboard.
- **Peak Active Application Tunnel Count**: Displays up to the top 10 App Connectors that have the highest active connections to applications in the selected time range. This widget displays automatically when first accessing the dashboard.
- **Peak Active Connections to Private Service Edges**: Displays up to the top 10 App Connectors with the highest active connections to Private Service Edges for Private Access (ZPA) in the selected time range.
- **Peak Active Connections to Public Service Edges**: Displays up to the top 10 App Connectors with the highest active connections to Public Service Edges for Private Access in the selected time range.
- **Minimum Available Disk Space**: Displays up to the top 10 App Connectors has the least disk space available in the selected time frame. This is not the average disk space used over the time frame.
- **Peak Application Reachability**: Displays up to the top 10 App Connectors monitoring the most application targets in the selected time range. This widget displays automatically when first accessing the dashboard.
- **Peak CPU Utilization**: Displays up to the top 10 App Connectors using the most CPU in the selected time frame. This is not the average CPU used over the time frame.
- **Peak Memory Utilization**: Displays up to the top 10 App Connectors using the most memory in the selected time frame. This is not the average memory used over the time frame. This widget displays automatically when first accessing the dashboard.
- **Peak TCP Port Utilization**: Displays up to the top 10 App Connectors using the most TCP ports for IPv4 in the selected time frame. This is not the average TCP ports used over the time frame, and it does not show App Connectors using TCP ports for IPv6.
- **Peak UDP Port Utilization**: Displays up to the top 10 App Connectors using the most UDP ports for IPv4 in the selected time frame. This is not the average UDP ports used over the time frame, and it does not show App Connectors using UDP ports for IPv6.
- **Peak File Descriptor Utilization**: Displays up to the top 10 App Connectors using the most file descriptors in the selected time frame. This is not the average file descriptors used over the time frame.
- **Total Application Tunnel Count**: Displays up to the top 10 App Connectors that have the highest cumulative connections to applications in the selected time range.
- **Total Bytes Received from Public Service Edges**: Displays up to the top 10 App Connectors that have the highest cumulative received bytes from Public Service Edges in the selected time range.
- **Total Bytes Transmitted to Public Service Edges**: Displays up to the top 10 App Connectors that have the highest cumulative transmitted bytes to Public Service Edges in the selected time range.
- **Peak Inspection Tunnel Count in Past 14 Days**: Displays up to the top 10 App Connectors that have the highest connections to AppProtection in the past 14 days.

- **Active Application Tunnel Count**: The number of active connections the App Connector had to applications for the point in time listed. This widget displays automatically when first accessing the dashboard.
- **Active Connections to Private Service Edges**: The number of active connections the App Connector had to Private Service Edges in the selected time range.
- **Active Connections to Public Service Edges**: The number of active connections the App Connector had to Public Service Edges in the selected time range.
- **Available Disk Space**: Displays the amount of disk space that is available to an App Connector at different points during the selected time range.
- **Rate of Bits Received from Public Service Edge**: The number of bits received per second by the App Connector from Public Service Edges in the selected time range. The rate is measured by taking the total bytes (b) for two points in time counted in seconds (t): rate= (b2-b1) */(t2-t1). For example, there are 100 total bytes at 7:00, and 1000 total bytes at 7:05. The rate is 24 bits per second ((1000-100)*8 / 300 = 24 bits/second).
- **Application Reachability**: Displays the number of application targets for an App Connector at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **CPU Utilization**: Displays the amount of CPU used by an App Connector at different points during the selected time range.
- **Memory Utilization**: Displays the amount of memory used by an App Connector at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **TCP Port Utilization**: Displays the number of TCP ports for IPv4 used by an App Connector at different points during the selected time range. It does not show App Connectors using TCP ports for IPv6.
- **UDP Port Utilization**: Displays the number of UDP ports for IPv4 used by an App Connector at different points during the selected time range. It does not show App Connectors using UDP ports for IPv6.
- **File Descriptor Utilization**: Displays the number of file descriptors used by an App Connector at different points during the selected time range.
- **Rate of Bits Transmitted to Public Service Edges**: The number of bits transmitted per second by the App Connector to Public Service Edges during the selected time range. The rate is measured by taking the total bytes (b) for two points in time counted in seconds (t): rate= (b2-b1) */(t2-t1). For example, there are 100 total bytes at 7:00, and 1000 total bytes at 7:05. The rate is 24 bits per second ((1000-100)*8 / 300 = 24 bits/second).
- **Application Tunnel Creation Rate**: Compare the rate of application tunnels interacting with App Connectors for application connections in the selected time range. The rate is measured by taking the total application tunnels (a) for two points in time counted in seconds (t): rate= (a2-a1) / (t2-t1). For example, there are 100 total application tunnels at 7:00, and 400 total application tunnels at 7:05. The rate is one tunnel per second ((400-100) / 300 = 1 tunnel/second).

[Image: App Connector dashboard tools in the Zscaler Admin Console]

[Image: App Connectors dashboard tools]

[Image: Select a point or view logs for part of a chart on the App Connector Dashboard in the Zscaler Admin Console]

[Image: Select a part of a chart on the Zscaler Admin Console Dashboard in the Zscaler Admin Console]

[Image: Zoom Out of a chart on the App Connector in the Zscaler Admin Console]

[Image: Search and Deselect App Connectors in Widgets on the App Connector Dashboard in the Zscaler Admin Console]

[Image: Actions for the App Connector Details table on the App Connector Dashboard in the Zscaler Admin Console]

[Image: Filter App Connectors on the App Connector Dashboard in the Zscaler Admin Console]

[Image: Number of App Connectors in an App Connector group in the App Connector Groups filter on the App Connector Dashboard in the Zscaler Admin Console]

The Details page provides information about the selected App Connector. It is divided into three sections:

- General Information
- App Connector Information
- Activity Monitor Widgets

The general information available about the App Connector:

- **App Connector Group**: The name of the group the App Connector is included in.
- **Location**: The city and country that the App Connector is connecting from.
- **Enabled**: Identifies if the App Connector is enabled or disabled.
- **Session Status**: The status of the App Connector session during the time range. The potential session statuses are:
  - **Authenticated**: The App Connector successfully authenticated.
  - **Authentication Failed**: The App Connector was unable to authenticate to the Zscaler cloud.
  - **Disconnected**: The App Connector successfully disconnected.
- **Periodic Software Update On**: The date and time of the next periodic software update for the App Connector.
- **Last Software Update On**: The date and time of the last software update for the App Connector.
- **Scheduled Software Version**: The next App Connector software version that the App Connector upgrades to.
- **Current Software Version**: The current App Connector software version during the time range.
- **Connection Status**: The connection status of the App Connector during the time range. The potential session statuses are:
  - **Connected**: The App Connector is up during the time range.
  - **Disconnected**: The App Connector is down during the time range.

[Image: General information for an App Connector on the App Connector Dashboard in the Zscaler Admin Console.]

The values and percentages for each item in this section are initially based on the most recent data available as noted by the point in time listed on the right side of this section. The values and percentages change based on selections made in the Activity Monitor widgets below this section. The time also adjusts.

[Image: Time Selection for Individual App Connector Details on the App Connector Dashboard in the Zscaler Admin Console]

The information available about the App Connector includes:

- **Active Application Tunnel Count**: The number of active connections the App Connector had to applications for the point in time listed.
- **Active Connections to Private Service Edges**: The number of active connections the App Connector had to Private Service Edges for the point in time listed.
- **Active Connections to Public Service Edges**: The active connection the App Connector had to Public Service Edges for the point in time listed.
- **Available Disk Space**: The number of bytes available to the App Connector for the point in time listed.
- **Rate of Bits Received from Public Service Edge**: The number of bits received by the App Connector from Public Service Edges for the point in time listed.
- **Application Reachability**: The number of application targets the App Connector is monitoring for the point in time listed.
- **CPU Utilization**: The highest CPU used by the App Connector for the past 5 minutes.
- **Memory Utilization**: The highest memory used by the App Connector for the past 5 minutes.
- **TCP Port Utilization**: The number of TCP ports for IPv4 used by the App Connector for the point in time listed. It does not show App Connectors using TCP ports for IPv6.
- **UDP Port Utilization**: The number of UDP ports for IPv4 used by the App Connector for the point in time listed. It does not show App Connectors using UDP ports for IPv6.
- **File Descriptor Utilization**: The file descriptors used by the App Connector for the point in time listed.
- **Rate of Bits Transmitted to Public Service Edges**: The number of bits transmitted per second by the App Connector to Public Service Edges for the point in time listed.

The Activity Monitor section displays the same widgets with the same functionality as seen in the [Activity Monitor section](https://help.zscaler.com/zpa/about-app-connector-dashboard#activitywidgets) above. Three widgets are selected automatically. They are:

- Active Application Tunnel Count
- Application Reachability
- Memory Utilization

The latency monitor widgets in the Latency tab show the latency trend from an App Connector to Public Service Edges in the selected time range. The time range for the Latency tab is different from the rest of the App Connector Dashboard. It is limited to data over a period between **30 Mins** to **24 Hours**, or you can select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 24 hours. This filter applies to all widgets in the Latency tab, and the default time is 30 minutes.

The widgets available are:

- App Connector To Service Edge TCP Latency
- App Connector To Service Edge Latency

Each widget shows the Public Service Edges that interacted with the selected App Connector during the time range. The widgets have the same functionality as seen in the [Activity Monitor section](https://help.zscaler.com/zpa/about-app-connector-dashboard#widgetfunctions).

For each widget, you can search by entering part or all of a Public Service Edge name or by using the following search query options with >, <, or = operators:

- location: The location of the Public Service Edge (e.g., location < San Jose).
- name: The name of the Public Service Edge (e.g., name = sj_serviceedge).
- value: A numerical value specific to the widget (e.g., value < 40).

[Image: Latency Tab on the App Connector Dashboard in the Zscaler Admin Console]

[Image: Details and Latency tabs for App Connectors on the App Connector Dashboard in the Zscaler Admin Console]

- Let's say there is App Connector group 1, which contains App Connectors A and B, and then App Connector group 2, which contains App Connectors C and D.
- If App Connector group 1 and App Connector C are selected, the dashboard shows App Connectors A, B, and C, rather than showing no App Connectors because C is not part of App Connector group 1.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-application-and-user-group-relationships","lastmod":"2026-04-17T07:06Z","nid":"1530860"} -->
## Viewing Application and User Group Relationships

- Source: https://help.zscaler.com/zpa/viewing-application-and-user-group-relationships
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Application and User Group Relationships
- Last modified: 2026-04-17T07:06Z
- Summary: Information on the Application and User Group Relationships Report and its granular details.

Application and User Group Relationships provides you with interactive and actionable insights on the usage of application segments and segment groups by user groups. To help you design better policy, you can gain insights into the relationships of the application and user groups to ensure [least privileged access](https://www.zscaler.com/resources/security-terms-glossary/what-is-least-privilege-access), a central tenet of Zscaler's Zero Trust Network Access (ZTNA).

The Application and User Group Relationship insights are provided for the following items:

- Application Segments
  - The application segments accessed by user groups.
  - The user groups accessing the application segments.
- Segment Groups
  - The segment groups accessed by user groups.
  - The user groups accessing the segment groups.

Items include application segments, segment groups, and user groups.

See image.

## Application and User Group Relationships Report

Use the Application and User Group Relationships page (Logs > Insights > Usage > Application and User Group Relationships) to view the usage between applications and user groups.

The report consists of the following information:

- **Top Items**: Displays the top items as segment groups, application segments, or user groups with the total number. This automatically updates based on the selection filter and exclusion criteria from the Settings drawer.
- **Current Report Information**: Displays the current report information based on when it was last updated. See image.
- **Launch Tour**: Displays a series of guided steps to interact with the Application and User Group Relationships page.
- **Run Report**: Generate the report to include data from the selected time range. In the time range drop-down menu, you can select a preset range (e.g., **14 Days** or **30 Days**). By default, the time range of the report is set to **14 Days**. The report automatically expires after 90 days. You cannot run the report if there is no more data to add when the report was recently updated. Run Report is disabled for 72 hours before and after 12:00 AM on the first Saturday of every month due to scheduled automatic reports. You cannot manually generate a report during this time. Additionally, customers with the Segmentation Add-On feature can generate one report per day, and customers without the feature can generate one report every 90 days. See image.
- **Settings**: Customize your page view between Application Segments or Segment Groups. The default is set to Application Segments. You can opt to exclude items.
- **Download**: Download a CSV file for your list of items. Click **Download** and select one of the following reports:
  - **Full Report**: Click **Full Report** to open the **Download CSV File** window. In the **Download CSV File** window, you can access the following report views:
    - **Detailed**: The detailed report includes details about segment groups, application segments, applications (including FQDN, port, protocol, application type, and discovered applications), access policy rule, and user groups and users who have accessed the applications and application segments. Only customers with the Segmentation Add-On can generate detailed report views for the last 6 reports (i.e., 180 days). See image.
    - **Summary**: The summary report includes application types, application segments, segment groups, and the count of users and user groups. See image.
  - **Filtered Report**: The filtered report includes details about segment groups, application segments, applications (including FQDN, port, protocol, and application type), access policy rules, and user groups and users who have accessed the applications and application segments for the filtered application segments.
- **View Filter**: Alternate the view of your page between Application Segments or Segment Groups and User Groups.

The report displays the following graphical representations of the relationship between applications and user groups:

- **Sunburst Chart**: Displays a hierarchical graphical representation of the top items (up to 10) based on selection and view. You can select parts of the sunburst chart to drill down for granular details.
- **List of Items**: Displays the top items based on selection and shows the total count between applications and user groups. The list shows: Your list varies and updates accordingly based on your Settings, View Filter, and Selection Filter. For example, if you select Application Segments in your Settings and User Groups in your View Filter, then your list shows User Groups, Application Segments, and Users Accessed.
  - **Application Segments** or **Segment Groups**: The names of the application segments or segment groups.
  - **User Groups**: The names of the user groups.
  - **Users Accessed**: The number of users accessing the application (i.e., application segment or segment group).
  - **Application Types**: The type of application (e.g., **RDP**, **SSH**, **Microsoft SQL**) based on standard ports and protocols. If ports for an application can't be classified, then the application type appears as **Unknown**. Applications can be classified as **Unknown**for up to 14 days until a proper classification occurs.

See image.

## Customizing the Application and User Group Relationships Page

Zscaler recommends starting with Customize Your View from the Settings drawer to decide what information you want to analyze: Application Segments or Segment Groups.

There are multiple ways to customize the Application and User Group Relationships page:

- Settings
- Selection with the Sunburst Chart
- Alternate Your View
- Select
- Exclude
- Reset

## Viewing Item Details

The Application and User Group Relationships page allows you to display item details in a granular view.

You can click a specific row to open a drawer containing its details.

See image.

You can customize your view to display or exclude application segments or segment groups. To change your view settings:

1. On the **Application and User Group Relationships** page, click **Settings**(). The **Settings** drawer appears. See image.
2. In the **Settings**drawer, under **Customize Your View**, select **Application Segments** or **Segment Groups**. The default is **Application Segments**.
3. In the **Exclude**section, you can exclude specific items depending on what you selected: Excluding multiple items uses the OR logic.
  - Application Segments or Segment Groups
  - User Groups
  - Branch and Cloud Connector access policy rules The option to exclude Branch and Cloud Connector access policy rules is supported only for Application Segments.
4. For **Maximum number of reports to retain**, select the maximum number of reports to retain (e.g., **4**, **5**, or **6**). Older reports are deleted. The option to retain the maximum number of reports is supported only for Application Segments.
5. In the Include section, you can include specific items depending on what you selected:
  - **SCIM Attribute**: Select the SCIM attribute value from the drop-down menu.
  - **SAML Attribute**: Select the SAML attribute value from the drop-down menu.
6. Click **Save**.

Your page is updated based on your selections to view Application Segments or Segment Groups and to exclude the specified items.

You can interact with the sunburst chart to view multiple levels of the relationship between the application and user group. The levels are displayed as follows:

- 1st Level: Displays the relationship between the top items and what each individual item is. Up to 10 items are displayed. For example, the top 7 application segments are displayed as a continuous, inner ring and the outer ring displays what those 7 application segments are. Click the inner ring to display the next hierarchy level. See image.
  - Inner Ring: Displays the top items as a continuous ring.
  - Outer Ring: Displays top items currently associated with the inner ring (e.g., application segments, segment groups, user groups).
- 2nd Level: Displays the relationship between the subsequent items and their associated items. Click one of the parts on the inner ring to select the item to display on the next hierarchy level. See image.
  - Inner Ring: Displays the subsequent items from the previous level.
  - Outer Ring: Displays the related items to the subsequent items.
  - You can also:
    - Hover over an item part and click to exclude it.
    - Click the **Back**icon () to return to the previous level.
- 3rd Level: Displays the last level of the hierarchical data between the selected item and who accessed it. By selecting an item in the 3rd level of hierarchical data, the display switches your view to that item type (e.g., Application Segments > User Groups, User Groups > Application Segments). See image.

If there are more than 10 items for the top inner ring or if you exclude items, then the remaining items are displayed as Others in the sunburst chart.

See image.

You can alternate between viewing Application Segments or Segment Groups (based on your Settings) and User Groups.

See image.

You can reset from the following sections:

- Settings: Clear all exclusions and then click **Save** to reset all items. See image.
- Sunburst chart: Click the **Back**icon () to return to the previous level of the chart.
- Reset: Next to the selection filter, click the **Reset**icon () to return to the default view.

Select your items to include what you want displayed.

A maximum of 10 items can be selected.

See image.

You can exclude items from:

- Settings: Select items to exclude. See image.
- Sunburst chart: If you click an individual item in the inner ring, you can exclude it from display. See image.

If you exclude items, then the sunburst chart, selection filter, and list adjust accordingly.

[Image: Top Items - 1st Level]

[Image: Application Segments and User Groups]

[Image: Selected Application Segment and User Group]

[Image: Application Segment Settings in the Application and User Group Relationships Page]

[Image: Settings for Segment Groups]

[Image: Clear All]

[Image: Selection Filter]

[Image: Exclude Items in Settings Drawer]

[Image: Exclude Item from Sunburst Chart]

[Image: Sunburst Chart]

[Image: Specific Item Details]

[Image: Using the Application and User Group Relationships Insights]

[Image: Application and User Group Relationship]

[Image: Report Information]

[Image: Run Report]

[Image: Selected Application Segments View]

[Image: Selected Segment Groups View]

[Image: Detailed Report View within the Download CSV File window in the Zscaler Admin Console]

[Image: Summary Report View within the Download CSV File window in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-application-map","lastmod":"2026-09-18T10:51Z","nid":"1534383"} -->
## Viewing the Application Map

- Source: https://help.zscaler.com/zpa/viewing-application-map
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Microsegmentation > Dashboard > Viewing the Application Map
- Last modified: 2026-09-18T10:51Z
- Summary: How to view the Application Map for Microsegmentation in the Zscaler Admin Console.

The Application Map visualizes network data in your organization. It provides a high-level view of how resource groups and their connected resources are performing, as well as options to view each group in detail.

To view the Application Map, from the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Dashboard** > **Microsegmentation Application Map**.

On the Application Map, you can use your mouse to zoom in or out of the map and rearrange the groups. You can use filters to view data about your organization’s resource groups or VPCs/VNETs within a specific timeframe and AppZone.

[Image: Filter and adjust the Application Map]

The Application Map provides information about the following:

- Managed Resource Groups
- Unmanaged Resource Groups
- Unmapped Resource Groups
- Public Internet
- Private Infrastructure
- VPC/VNET

You can click a resource group to open a drawer containing its details. Additionally, when you click a resource group, the map displays information about the group’s managed and unmanaged flows, and if applicable, provides the option to review policy recommendations for the resource group based on those flows.

[Image: Resource group details on the Application Map]

To learn more about reviewing policy recommendations for resource groups, see [About Policy Recommendations for Resource Groups](https://help.zscaler.com/zpa/about-policy-recommendations-resource-groups) and [Reviewing Policy Recommendations for Resource Groups](https://help.zscaler.com/zpa/reviewing-policy-recommendations-resource-groups).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-application-segments-usage","lastmod":"2026-09-15T11:41Z","nid":"1531128"} -->
## Viewing Application Segments Usage

- Source: https://help.zscaler.com/zpa/viewing-application-segments-usage
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Application Segments Usage
- Last modified: 2026-09-15T11:41Z
- Summary: Viewing and understanding the application usage by users and groups in the Application Segments Usage page.

Application Segments Usage insights provide visibility into which application segments are being actively used or not. These insights can help you assess and determine unused application segments for improvement consideration. The Application Segments Usage insights provide:

- A distribution of users and their associated application segments.
- Most used application segments.
- Least used application segments.

See image.

[Image: Application Segments Usage page]

## Application Segments Usage Report

The Application Segments Usage Report contains the following information:

- The number of application segments applied to a percentage distribution of users. This includes: The distribution of users is calculated based on the number of unique users (must be more than zero users) against each application segment. Then the distribution categorizes the users into 1 of 10 percentage bars based on their usage of the application segment. Each bar indicates a tenth percentage of application segments based on usage. You can click a bar to view application segment usage data based on that user group. The percentage is rounded to the nearest whole number.
  - **Most Used Application Segments**: Displays the number of most used application segments.
  - **Unused Application Segments**: Displays the number of unused application segments.
- **Current Report Information**: Displays the current report information based on when it was last updated. See image.
- **Launch Tour**: Launches a series of guided steps on how to interact with the Application Segments Usage page.
- **Run Report**: Generates the report to include data from the selected time range. In the time range drop-down menu, you can select a preset range (e.g., **14 Days** or **30 Days**). By default, the time range of the report is set to **14 Days**. The report automatically expires after 90 days. You cannot run the report if there is no more data to add when the report was recently updated. Run Report is disabled for 72 hours before and after 12:00 AM on the first Saturday of every month due to scheduled automatic reports. You cannot manually generate a report during this time. Additionally, customers with the Segmentation Add-On feature can generate one report per day, and customers without the feature can generate one report every 90 days. See image.

[Image: Viewing the Current Report Information in the Application Segment Usage Insights page]

See image.

[Image: Run a report on the Application Segments Usage insights page]

[Image: Application Segment Usage report information]

## Application Segments Usage Chart and Table

The Application Segments Usage page consists of two charts:

- Usage
- Discovered Host Count

The Application Segments Usage chart and table provide the following information:

- **Application Segment Usage Bar Chart**: Displays the distribution of application segment usage from access policy rules. Click a bar to specify the application segments displayed in the table. The default is set to **Unused Application Segments**.
- See image.
- **Application Segment Filter**: Select specific application segments to display in the chart and table. See image.
- **View Application Segments**: Allows you to access the [Defined Application Segments](https://help.zscaler.com/zpa/about-applications) page to manage the defined application segments.
- **Application Segment Table**: Displays the following information for each application segment:
  - **Application Segment**: The name of the application segment.
  - **Segment Group**: The name of the segment group.
  - **Applications**: The number of applications accessed. If the same application is accessed through different ports and/or protocols (e.g., TCP, UDP), then each application is counted separately.
  - **Access Policy Rules**: The number of access policy rules that allowed access to the application segment.
  - **Unique Users**: The number of unique users impacted by the application segment.
  - **Transactions**: The number of Private Access (ZPA) transactions going through the application segment.
  - **Actions**: The available actions for the application segment. This includes:
    - **Edit**: Edit the application segment in the Edit Application Segment window. To learn more, see [Editing Defined Application Segments](https://help.zscaler.com/zpa/editing-application-segments).
    - **Application Segments and User Details**: View the application segment's usage details. To learn more, see [Viewing Application Segment Usage Details](https://help.zscaler.com/zpa/viewing-application-segment-usage-details).

[Image: Bar Chart Selection within the Application Segment Usage Insights page]

[Image: Application Segment Filter within the Application Segment Usage Insights Table]

See image.

[Image: Application Segment Usage Page within the ZPA Admin Portal]

The Discovered Host Count chart and table provide the following information:

- **Discovered Host Count Bar Chart**: Displays the top 10 application segments by discovered host count. See image.
- **Application Segment Filter**: Select specific application segments to display in the chart and table.
- **Application Segment Table**: Displays the following information for each application segment:
  - **Application Segment**: The name of the application segment.
  - **Segment Group**: The name of the segment group.
  - **Discovered Host Count**: The number of discovered hosts.

See image.

[Image: Discovered Host Count Bar Chart]

[Image: Viewing the Discovered Host Count Chart and Table]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-application-segments-usage-details","lastmod":"2026-09-15T11:38Z","nid":"1531129"} -->
## Viewing Application Segments Usage Details

- Source: https://help.zscaler.com/zpa/viewing-application-segments-usage-details
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Application Segments Usage Details
- Last modified: 2026-09-15T11:38Z
- Summary: Application segments usage details include granular information for a specific application segment from the Application Segments Usage page.

Application segment usage details are accessible from the following pages:

- [Application Segments Usage](https://help.zscaler.com/zpa/viewing-application-segments-usage)
- [Defined Application Segments](https://help.zscaler.com/zpa/about-applications)

When you click the **Graph** icon (), you can view the following application segment usage details:

- **Time Range**: Select to filter details based on a time range.
- **Refresh**: Click the **Refresh** icon to include the most recent updates. The report automatically refreshes every 30 days.
- Depending on what view you selected, you see the following widgets: When you click **View Logs** on a widget, you are taken to the Diagnostics page. To learn more, see [Accessing User Activity Diagnostics](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics).
  - **Users** view(default):
    - **Users Over Time**: Displays the number of users over time for the application segment.
    - **Top Access Policies**: Displays the most impacted access policies.
    - **Top Applications**: Displays the most impacted applications across users. You can hover over and then select ports to switch the view between applications and ports. To switch back, select the application name.
    - **Top Ports**: Displays the most used ports.
    - **Top Client Types**: Displays the most impacted client types for the application segment.
  - **Transactions** view:
    - **Transactions Over Time**: Displays the number of transactions for the application segment.
    - **Top Access Policies**: Displays the most impacted access policies.
    - **Top Applications**: Displays the most impacted applications across users. You can hover over and then select a port to switch the view between applications and ports. To switch back, select the application name.
    - **Top Ports**: Displays the most used ports.
    - **Top Users**: Displays the users creating the most transactions for the application segment.
    - **Top Client Types**: Displays the most impacted client types for the application segment.

See image.

No data is shown if the application segment has no usage within the time range (e.g., unused application segment).

See image.

[Image: Application Segment Usage Details]

[Image: No Data Available]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-applications-dashboard","lastmod":"2026-09-20T07:06Z","nid":"1483451"} -->
## Viewing the Applications Dashboard

- Source: https://help.zscaler.com/zpa/viewing-applications-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Viewing the Applications Dashboard
- Last modified: 2026-09-20T07:06Z
- Summary: Information on the Applications dashboard and widgets accessible within the Zscaler Admin Console.

The Applications dashboard provides information about applications in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Applications.

See image.

[Image: pplications dashboard in the Zscaler Admin Console]

## Dashboard Tools

The Applications dashboard displays the following information and functionality:

- **Time Range Filter**: View application data over a period between **1 Hour** to **14 Days**, or you can select **Custom Range**. If you use a **Custom Range**, the start and end date must be within the last 14 days. The end can be configured to the selected time in hours and minutes. This filter applies to all widgets on the dashboard except **Discovered Applications**and **Recommended Application Segments by Confidence %**. By default, the dashboard displays information for events that occurred in the last hour.

Log information in the dashboard is limited to 14 days. For longer access to the logs, use the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming).

- **Federated By**: Filter data in the dashboard by applications that are federated by your tenant (i.e., **My Tenant**), or by applications that are federated by your partner (i.e., **Partner**). If the **Federated By** filter is set to **Partner**, then the **Partner**filter and a valid value must be applied in addition to the Federated By filter. To learn more, see [Understanding Business-to-Business (B2B) Federation](https://help.zscaler.com/zpa/understanding-business-business-b2b-federation).
- **Partner**: Filter data in the dashboard by the federated partner. The **Partner**filter must be applied with the **Federated By** filter, and is only visible when the **Federated By** filter is set to **Partner**. To learn more, see [About Federated Partners](https://help.zscaler.com/zpa/about-federated-partners).
- **Refresh icon**: Refresh the dashboard to reflect the most current information.
- **Recent Applications Accessed**: View the total number for this category at the top of the page, and then view its details in the widget below. This number is based on the applications accessed by users, and it includes all successful and unsuccessful transactions.
- **Discovered Applications**: View the total number for this category at the top of the page, and then view its details in the widget below. This number is based only on successful transactions.
- **Access Policy Blocks** and **Successful Transactions**: View the total numbers for these categories at the top of the page, and then view their details in the widgets below.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 4 charts and a maximum of 8 charts can be selected.

See image.

[Image: Applications Dashboard tools]

## Widgets

The Applications dashboard provides the following widgets:

- AI-Powered Recommendations by Attack Surface Reduction %
- Recent Applications Accessed
- Top Applications by Bandwidth
- Top Errors
- Top Application Segments by Bandwidth
- Top Disaster Recovery App Segments by Bandwidth
- Top Policy Blocks
- Top Applications by Users
- Applications Discovered in the Past 14 Days
- App Configuration in Past 3 Months
- Top Applications by Tunnel Count

If you are using the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming), the Users dashboard includes information for a ZPA LSS Client user. This user represents the LSS service, not an actual user. Also, each log receiver is displayed as an application to reflect the data coming in from the service. To learn more, including how to stop the LSS service from streaming ZPA LSS Client logs, see [Configuring a Log Receiver](https://help.zscaler.com/zpa/configuring-log-receiver#Step2).

This widget displays the total number of recommended application segments, grouped by their percentage of attack surface reduction. The attack surface reduction groups are broken up into 25% increments to show how many recommended application segments had higher attack surface reduction versus those that were lower. If you hover over the chart and click a specific section, a tooltip appears that specifies the attack surface reduction group for the percentage increment, the total number of AI-powered recommendations, and the percentage total. Clicking **View All** takes you to the [AI-Powered Recommendations](https://help.zscaler.com/zpa/about-ai-powered-recommendations-application-segments) page.

AI-Powered Recommendations must be activated to display the AI-Powered Recommendations by Attack Surface Reduction % widget and populate it with data. To activate this feature, click **Activate Recommendations** on the AI-Powered Recommendations page (Policies > Access Control > Private Apps > AI-Powered Recommendations).

[Image: AI-Powered Recommendations by Attack Surface Reduction % Widget in the Zscaler Admin Console]

This widget displays real-time information about the total number of applications requested by users that were accessed in the selected time frame. The widget uses the current time for its end time unless you select a custom time range with a different end time.

[Image: Applications Dashboard with Applications Accessed widget]

- Click on an application to view more details in **Diagnostics**.
- Click the **Download** icon ([Image: Download icon within the ZPA Admin Portal]) to export a CSV file containing information on the applications accessed for the selected time frame (i.e., Timestamp (in UTC), application (domain name or IP address), port number, and protocol).

This widget displays the top 10 applications that used the most bandwidth for your organization in the selected time frame. The percentage of total transactions for the top 10 applications by bandwidth appears in the top-right corner.

[Image: Top Applications by Bandwidth widget]

- Hover over an application to view:
  - **Name**:The application name.
  - **Bandwidth**: The amount of bandwidth used by the application in the selected time frame, and the percentage of that amount represents the total bandwidth used by the 10 applications shown in the widget.
- Click on an application to view more details in **Diagnostics**.

This widget displays the top 10 application segments that used the most bandwidth for your organization in the selected time frame. The percentage of total transactions for the top application segments by bandwidth appears in the top-right corner.

[Image: Top Application Segments by Bandwidth widget]

This widget displays the top 10 disaster recovery-enabled application segments that used the most bandwidth for your organization in the selected time frame. If there was a disaster recovery-related incident during this time frame, it accounts for both the disaster recovery-related transactions and the regular transactions. The percentage of total transactions for the top disaster recovery-enabled application segments by bandwidth appears in the top-right corner.

[Image: Top Disaster Recovery App Segments by Bandwidth widget]

This widget displays the top errors experienced by users per connection status code, application, App Connector, and Private Service Edges for Private Access over the selected time frame.

- Connection Status Codes
- Applications
- App Connectors
- Service Edges

You can hover over the connection status codes to:

- View the total number of transactions that occurred with this connection status code.
- View the percentage of errors where this connection status code occurred for the drilldown data displayed.
- Analyze by:
  - **Applications**: This drills down and displays data on the applications impacted by the connection status code.
  - **Connectors**: This drills down and displays data on the App Connectors impacted by the connection status code.
  - **Show in Logs**: To view more details in **Diagnostics** for the drilldown data that is displayed.

[Image: Top Errors widget]

You can hover over the applications to:

- View the total number of transactions where the application error occurred.
- View the percentage of errors where this application error occurred for the drilldown data displayed.
- Analyze by:
  - **Connectors**: This drills down and displays data on the App Connectors impacted by the application error.
  - **Connection Status Codes**: This drills down and displays data on the connection status code applicable to the impacted application.
  - **Show in Logs**: To view more details in **Diagnostics** for the drilldown data that is displayed.

[Image: Top Errors widget]

You can hover over the App Connectors to:

- View the total number of transactions where the App Connector error occurred.
- View the percentage of errors for the drilldown data displayed for the App Connector.
- Analyze by:
  - **Applications**: This drills down and displays data on the applications impacted by the App Connector error.
  - **Connection Status Codes**: This drills down and displays data on the connection status code applicable to the impacted App Connector.
  - **Show in Logs**: To view more details in **Diagnostics** for the drilldown data that is displayed.

[Image: Top Errors widget]

You can hover over the Private Service Edges to:

- View the total number of transactions for the error that occurred on the Private Service Edge.
- View the percentage of errors for the drilldown data displayed for the Private Service Edge.
- Analyze by:
  - **Connectors**: This drills down and displays data on the App Connectors impacted by the Private Service Edge error.
  - **Connection Status Codes**: This drills down and displays data on the connection status code applicable to the impacted Private Service Edge.
  - **Show in Logs**: To view more details in **Diagnostics** for the drilldown data that is displayed.

[Image: Top Errors widget]

This widget displays the top access policy and timeout policy blocks experienced by users over the selected time frame.

- Access Policy Blocks
- Timeout Policy Blocks

You can hover over the access policy block to:

- View the total number of transactions where the access policy block occurred.
- View the percentage of errors where this access policy block occurred for the drilldown data displayed.
- Analyze by:
  - **Applications**: This drills down and displays data on the applications impacted by the access policy block.
  - **Show in Logs**: To view more details in **Diagnostics** for the drilldown data that is displayed.

[Image: Access Policy Blocks widget]

You can hover over the timeout policy block to:

- View the total number of transactions where the timeout policy block occurred.
- View the percentage of errors where this timeout policy block occurred for the drilldown data displayed.
- Analyze by:
  - **Applications**: This drills down and displays data on the applications impacted by the timeout policy block.
  - **Show in Logs**: To view more details in **Diagnostics** for the drilldown data that is displayed.

[Image: Timeout Policy Blocks widget within Dashboard]

This widget displays the top 10 applications accessed by your organization's users in the selected time frame. The percentage of total transactions for the top applications by users appears in the top-right corner.

[Image: Top Applications by User widget]

- Hover over an application to view:
  - **Name**: The application name.
  - **Number of User(s)**:The top 10 applications accessed by your organization's users in the selected time frame, and the percentage that number represents of the 10 applications shown in the widget.
- Click on an application to view more details in **Diagnostics**.

This widget displays the applications that Zscaler has [discovered](https://help.zscaler.com/zpa/understanding-application-discovery) for your organization in the past 14 days, with the most recently discovered application listed first.

[Image: Applications Dashboard with the Applications Discovered in the Past 14 Days widget]

- Click on an application to view more details in **Diagnostics**. If the application has not been accessed in the last 14 days, you will not see data for the application in **Diagnostics**.
- Click the **Download** icon ([Image: Download icon within the ZPA Admin Portal]) to export a CSV file containing information on the applications discovered in the last 14 days (i.e., application (domain name), port number, protocol, and internal application ID).
- To define a user access policy or change settings for an application (e.g., enable health monitoring or configure bypass settings), you can explicitly define an application by clicking on **Add Application Segment**, selecting the applications, and then clicking **Define Selected Applications**. To learn more, see [Defining a Dynamically Discovered Application](https://help.zscaler.com/zpa/defining-dynamically-discovered-application).

To learn more about application discovery, see [Understanding Application Discovery](https://help.zscaler.com/zpa/understanding-application-discovery).

This widget displays the number of Defined Application Segments (orange) compared to the number of Discovered Applications (blue) in two-week increments over the past 3 months. Ideally for Zero Trust, the number of Defined Application Segments should increase while the number of Discovered Applications should decrease.

Hover over a graph bar to get detailed information for each two-week increment. Click a graph bar and then click **View Recommended Apps** to view the Recommended Application Segments page.

AI-Powered Recommendations must be activated to display the App Configuration in Past 3 Months widget and populate it with data. To activate this feature, click **Activate Recommendations** on the AI-Powered Recommendations page (Policies > Access Control > Private Applications > AI-Powered Recommendations).

[Image: App Configuration in Past 3 Months widget]

This widget displays the top applications by tunnel count in the selected time frame.

[Image: Top Applications by Tunnel Count widget on the Applications dashboard]

- Hover over an application to view:
  - **Name**: The application name.
  - **Number of MTunnel(s)**:The number of tunnel counts accessed by the application in the selected time frame, and the percentage that number represents of the applications shown in the widget.
- Click an application to view more details in **Diagnostics**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-appprotection-dashboard","lastmod":"2026-06-29T10:11Z","nid":"1484966"} -->
## Viewing the AppProtection Dashboard

- Source: https://help.zscaler.com/zpa/viewing-appprotection-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > AppProtection and Browser Protection Monitoring > Viewing the AppProtection Dashboard
- Last modified: 2026-06-29T10:11Z
- Summary: Information on the AppProtection dashboard and widgets accessible within the Zscaler Admin Console.

The AppProtection dashboard provides information about the AppProtection policy activity in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Security > AppProtection.

See image.

[Image: Viewing the AppProtection dashboard in the Zscaler Admin Console]

## Dashboard Tools

The AppProtection dashboard displays the following information and functionality:

- **Time Range Filter**: View AppProtection data over a period between **1 Hour** to **14 Days**, or you can select **Custom Range**. If you use a **Custom Range**, the start date must be within the last 14 days. The end date automatically sets to the system's current time. By default, the dashboard displays information for events that occurred in the last hour. This filter applies to all widgets on the dashboard.

Log information in the dashboard is limited to 14 days. For longer access to the logs, use the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming).

- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 4 charts and a maximum of 8 charts can be selected.

See image.

[Image: AppProtection Dashboard Tools]

## Widgets

The AppProtection dashboard provides the following widgets:

- Violations by Control Category
- Profile Violations by Application
- Violations by Control Severity
- Transaction Distribution
- Top User Agents with Profile Violations
- Top Users with Profile Violations
- Top Control Violations
- Top Profile Violations

The widget displays security violations within the selected time frame and categorizes them by the ThreatLabZ, WebSocket, and OWASP predefined top 10 control categories. The control categories are based on the ThreatLabZ Predefined Controls, WebSocket Predefined Controls, OWASP Predefined Controls, WebSocket Custom Controls, and HTTP Custom Controls, and are found in AppProtection Controls (**Policies**> **Cyber Security**> **Inline Security** > **Protection Controls**).

[Image: Violations by control category widget]

- Hover over a section of the chart to view its control category.
- Click on a section of the chart and then click **View Logs** to be directed to log information matching the control category in[AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays profile violations within the selected time frame and categorizes them by application.

[Image: Profile violations by applications widget]

- Click on the upper navigation tabs to switch between a chart of profile violations by applications or application segments.
- Hover over a section of the chart to view the name of the application or application segment.
- Click on a section of the chart and then click**View Logs** to be directed to log information matching that application or application segment in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays security violations within the selected time frame and categorizes them by severity rating (i.e., Critical, High, Medium, Low).

[Image: Profile violations by control severity widget]

- Hover over a section of the chart to view the severity rating.
- Click on a section of the chart and then click **View Logs** to be directed to log information matching that severity rating in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the transaction distribution of traffic with no violations and traffic with violations across an organization within the selected time frame.

[Image: Transaction Distribution widget]

- Hover over a section of the chart to view the violation type by No Violations or by Security Profile Violations.
- Click on a section of the chart and then click **View Logs** to be directed to log information matching that violation status in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the top 10 profile violations within the selected time frame and lists them by user agent.

[Image: Top user agents by profile violations widget]

- Hover over a user agent to view the following:
  - **Name**: The details about the user agent such as browser, machine, and software version.
  - **Number of Top User Agents by Profile Violation(s)**: The number of profile violations for the selected user agent and the percentage of violations held by that user agent within the top users category.
- Click on a user agent and then click **View Logs** to be directed to log information matching that user agent in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the top 10 profile violations within the selected time frame and lists them by user.

[Image: Top users by profile violations widget]

- Hover over a user to view the following:
  - **Name**: The name of the user.
  - **Number of Top Users by Profile Violation(s)**: The top users with their number of profile violations and the percentage of violations held by that user within the top users category.
- Click on a user and then click **View Logs** to be directed to log information matching that user in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

This widget displays the top 10 control violations within the selected time frame and categorizes them by name. The top control violations are categorized by control number and name, in the ControlNumber:ControlName format.

[Image: Top control violations widget]

- Hover over a section of the chart to view the name of the control violation.
- Click on a section of the chart and then click **View Logs** to be directed to log information matching that control violation in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).

The widget displays the top 10 profile violations within the selected time frame and lists them by name.

[Image: Top profile violations widget]

- Hover over a profile violation to view the following:
  - **Name**: The profile violation name.
  - **Number of Top Profile Violation(s)**: The number of profile violations for the selected violation and the percentage of violations held within the top profile violations category.
- Click on a profile violation and then click **View Logs** to be directed to log information matching that profile violation in [AppProtection Diagnostics](https://help.zscaler.com/zpa/accessing-approtection-diagnostics).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-bgp-peers-dashboard","lastmod":"2026-09-10T12:19Z","nid":"1534094"} -->
## Viewing the BGP Peers Dashboard

- Source: https://help.zscaler.com/zpa/viewing-bgp-peers-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > VPN (for Legacy Apps) > Dashboard & Diagnostics > Viewing the BGP Peers Dashboard
- Last modified: 2026-09-10T12:19Z
- Summary: Information about the BGP Peers dashboard in the Zscaler Admin Console.

The BGP Peers dashboard provides information about the Border Gateway Protocol (BGP) peers for your organization. To view the dashboard in the Zscaler Admin Console, go to Private Access > VPN (for Legacy Apps) > Dashboard > VPN BGP Peers.

## Dashboard Tools

The BGP Peers dashboard displays the following information and functionality:

- **Time Period Filter**: View BGP peers data over a period between 1 hour to 14 days, or you can select **Custom Range**. If you use a custom range, the start and end dates must be within the last 14 days. The end date can be configured to the selected time in hours and minutes. This filter applies to all widgets on the dashboard. By default, the dashboard displays information about events that occurred in the last hour.
- **Refresh icon**: Refresh the dashboard to adjust the time period filter to reflect the most current information.
- **Hide Filters** or **Show Filters**: Hide the filters on the page by clicking **Hide Filters**. Click **Show Filters** to display the filters.
- **VPN Service Edges**: Filter the information that appears in the table for a specific VPN Service Edge. By default, the first option is applied.

## VPN Service Edge Information

This section shows the following information about the VPN Service Edge:

- **Name**: The name of the VPN Service Edge.
- **Router ID**: The router ID of the VPN Service Edge.
- **Local AS Number**: The autonomous system number (ASN) assigned to the VPN Service Edge.
- **BGP Service Status**: The service status of the BGP:
  - **Active**: The BGP service is running.
  - **Inactive**: The BGP service is not running.
  - **Failed**: The BGP service failed or crashed.

## Network Connectors Table

The table shows the following information for each Network Connector associated with the VPN Service Edge:

- **Name**: The name of the Network Connector.
- **Router ID**: The route ID assigned to the Network Connector.
- **Peer AS Number**: The ASN assigned to the Network Connector.
- **BGP Session Status**: The status of the BGP for the Network Connector:
  - **Idle**: The initial state of the BGP before it initiates a connection or listens for a connection request from a neighboring BGP.
  - **Connect**: The BGP has successfully connected to a neighboring BGP peer.
  - **Active**: The BGP is actively trying to reopen the TCP connection to the peer after an earlier failure. If successful, it moves to the **OpenSent** status. If the attempt fails, the status moves back to **Connect**.
  - **OpenSent**: Open messages are exchanged between BGP peers. The router sends its BGP parameters to its peer that include ASN, BGP identifier, hold time, etc. If the parameters match and are accepted, BGP transitions to the **OpenConfirm** status.
  - **OpenConfirm**: The BGP has received a valid open message and is waiting for a keepalive message from the neighboring BGP.
  - **Established**: The BGP session is fully established, and peers can exchange updates about routes. Routes are advertised and learned. This is the operational state of a successful BGP session.
- **Prefixes Sent**: The number of network prefixes sent to BGP peers informing them on how to reach them.
- **Prefixes Received**: The number of network prefixes received by a router. The prefix information is used for making traffic forwarding decisions to reach destination networks.
- **Session Uptime (Seconds)**: The duration for which the Network Connector remained active and functional.
- **Settings icon**: [Modify the columns](https://help.zscaler.com/unified/using-tables)displayed in the table.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-browser-protection-dashboard","lastmod":"2026-06-29T10:12Z","nid":"1485611"} -->
## Viewing the Browser Protection Dashboard

- Source: https://help.zscaler.com/zpa/viewing-browser-protection-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > AppProtection and Browser Protection Monitoring > Viewing the Browser Protection Dashboard
- Last modified: 2026-06-29T10:12Z
- Summary: Information on the Browser Protection dashboard and widgets accessible within the Zscaler Admin Console.

The Browser Protection dashboard provides information about browser sessions in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Security > Browser Protection.

See image.

[Image: About the Browser Protection Dashboard tools]

## Dashboard Tools

The Browser Protection dashboard displays the following information and functionality:

- **Time Range Filter**: View Browser Protection data over a period between 1 Hour to 14 Days, or you can select **Custom Range**to specify a custom start and end date. If you use **Custom Range**, the start date must be within the last 14 days. The end date automatically sets to the system's current time. By default, the dashboard displays information for events that occurred in the last hour. This filter applies to all widgets on the dashboard.

Log information in the dashboard is limited to 14 days. For longer access to the logs, use the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming).

- **Refresh Icon**: Refresh the dashboard to reflect the most current information.

## Widgets

The Browser Protection dashboard provides the following widgets:

- Browser Based Access Users
- Unique Fingerprints for Monitored Users
- Monitored vs. Unmonitored Requests
- Monitored Users Details

The widget displays real-time users that are affiliated with browser-based access. The categories are Monitored and Unmonitored, representing the monitored and unmonitored users and shows them based on their percentages within the selected time frame.

[Image: Browser Based Access Users widget]

The widget displays the browser sessions that have the fingerprint option enabled for monitored users. The unique fingerprints for monitored users are grouped by low and high frequency percentages within the selected time frame by the number of monitored users. If frequent changes exist on a fingerprint, there is a high possibility of malicious activity. This widget allows you to keep a closer eye on monitored users' activity.

[Image: Unique Fingerprints for Monitored Users widget]

The widget displays the amount of browser session requests from monitored and unmonitored users. The categories are Monitored and Unmonitored, representing the monitored and unmonitored users based on their percentages within the selected time frame.

[Image: Monitored vs Unmonitored Requests widget]

The Monitored Users Details table provides information on monitored users within the specified time frame. You can filter the information that appears in the table. By default, no filters are applied.

The table covers:

- **Email**: The email address of the monitored user.
- **Number of Unique Fingerprints**: The number of browser sessions with **Fingerprint** enabled that the monitored user has accessed.
- **Actions**: Click the **Diagnostics** icon [Image: Clientless Access Diagnostics icon in the Monitored Users Details table on the Browser Protection Dashboard page] to go to the [Clientless Access Diagnostics](https://help.zscaler.com/zpa/accessing-clientless-access-diagnostics) page.

[Image: Monitored Users Details widget]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-configuration-graphs","lastmod":"2026-09-10T12:13Z","nid":"1516601"} -->
## Viewing Configuration Graphs

- Source: https://help.zscaler.com/zpa/viewing-configuration-graphs
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Private Access Software Components > Viewing Configuration Graphs
- Last modified: 2026-09-10T12:13Z
- Summary: Information on how to view the configuration graphs in the Zscaler Admin Console.

Configuration graphs are graphical representations of how configuration objects are connected to each other (e.g., how an application segment is connected to a server group and server groups). They are helpful in determining when and where you might need to fix configuration requisites to get them working together again.

A configuration graph consists of a segment group, application segment, server group, App Connector group, and App Connector. If you find an **Incomplete Configuration** icon ([Image: Caution icon]) next to one of them, you can click the **Configuration Graph** icon ([Image: Configuration Graph icon]) to access the graph and edit each connected object as required.

Configuration graphs can be accessed from the following pages:

- [App Connectors](https://help.zscaler.com/zpa/about-connectors)
- [App Connector Groups](https://help.zscaler.com/zpa/about-connector-groups)
- [Defined Application Segments](https://help.zscaler.com/zpa/about-applications)
- [Segment Groups](https://help.zscaler.com/zpa/about-segment-groups)
- [Server Groups](https://help.zscaler.com/zpa/about-server-groups)

In the following example, the configuration graph was accessed from the App Connectors page.

[Image: Viewing the Configuration Graph]

## Hiding Connections

You can hide connections by clicking the arrow icon.

[Image: Hiding connections in a configuration graph]

## Editing Configuration Objects

You can edit any configuration objects to ensure they meet the configuration requirements.

[Image: Edit Configuration Objects]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-extranet-dashboard","lastmod":"2026-06-29T10:03Z","nid":"1510081"} -->
## Viewing the Extranet Dashboard

- Source: https://help.zscaler.com/zpa/viewing-extranet-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Viewing the Extranet Dashboard
- Last modified: 2026-06-29T10:03Z
- Summary: Information about the Extranet Dashboard and widgets available within the Zscaler Admin Console.

The Extranet dashboard uses information gathered from Internet & SaaS (ZIA) to display extranet resources and locations with the lowest health score for overall performance and reliability in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Extranet.

The health score represents the health of an IPSec connection between the extranet resource's (partner) data center and the Zscaler cloud. It is a cumulative score of all the components and functions for that connection. You can have multiple connections between an extranet resource and the Zscaler cloud. Each extranet resource can have multiple locations, and each location can have multiple tunnels to the Zscaler cloud.

To learn more, see [About Extranet](https://help.zscaler.com/zia/about-extranet).

[Image: Extranet dashboard and widgets]

## Dashboard Tools

The Extranet dashboard displays the following information and functionality:

- **Time Range Filter**: View Source IP Anchoring data over a period between **30 Minutes** to **14 Days**, or you can select **Custom Range**. This filter applies to all widgets on the dashboard. By default, the dashboard displays information about events that occurred in the last hour.
- **Refresh icon**: Refresh the dashboard to reflect the most current information.

## Widgets

The Extranet dashboard provides the following widgets:

- Health Score for Extranet Resources
- Health Score for Extranet Locations
- Health Score for Tunnels

This score is the minimum of all the tunnels between the extranet resource and the Zscaler cloud via all possible locations. The score for both the VPN and IPSec tunnel ranges from 0 (unhealthy) to 100 (optimal health) for a maximum possible combined score of 200. The 5 lowest health scores appear in this widget.

- Hover over a value to view the individual VPN and tunnel scores for that resource.
- Click **View Analytics** to open the Diagnostics page with a filter for the extranet resource applied to the table.

This score is the minimum of all the tunnels from the location to the extranet resource. The score for both the VPN and IPSec tunnel ranges from 0 (unhealthy) to 100 (optimal health) for a maximum possible combined score of 200. The 5 lowest health scores appear in this widget.

[Image: Extranet Location Health Score Widget]

- Hover over a value to view the individual VPN and tunnel scores for that location.
- Click **View Analytics** to open the Diagnostics page with a filter for the extranet location applied to the table.

This widget displays the IPSec tunnels connected to extranet resources with the lowest health scores. The score for the IPSec tunnel ranges from 0 (unhealthy) to 100 (optimal health). The 5 lowest health scores appear in this widget.

[Image: Tunnel Health Score Widget]

- Hover over a value to view the tunnel score for that location.
- Click **View Analytics** to open the Diagnostics page with a filter for the extranet location applied to the table.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-flow-dashboard","lastmod":"2026-09-11T07:12Z","nid":"1498176"} -->
## Viewing the Flow Dashboard

- Source: https://help.zscaler.com/zpa/viewing-flow-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Microsegmentation > Dashboard > Viewing the Flow Dashboard
- Last modified: 2026-09-11T07:12Z
- Summary: How to view the Flow dashboard for Microsegmentation in the Zscaler Admin Console.

The Flow dashboard provides information about agent flows and connections in your organization. You can see a maximum of 10 top metrics for each category.

To view the Flow dashboard, from the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Dashboard** > **Microsegmentation** **Flow**.

## Dashboard Tools

The Flow dashboard displays the following information and functionality:

- **Time Range Filter**: View flow data from 1 Hour to 14 Days.
- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **Top Permitted Talkers**: The allowed resources and IP addresses sending out the number of flows in the network (Source IP and Protocol). These resources send the most outbound flows and initiate the connection to a given protocol.
- **Top Blocked Talkers**: The blocked resources and IP addresses sending out the number of flows in the network (Source IP and Protocol). These resources send the most outbound flows and initiate the connection to a given protocol.
- **Top Permitted Listeners**: The allowed resources and port combinations receiving the number of flows in the network (IP, Protocol, and Listening Port).
- **Top Blocked Listeners**: The blocked resources and port combinations receiving the number of flows in the network (IP, Protocol, and Listening Port).
- **Top Permitted Agent-to-Agent Flows**: The flows between multiple agents.
- **Top Permitted Flows Between Agent and Non-Agent**: The flows between agents and resources not connected to any agents.
- **Source Information**: Hover over one of the port data lines to display its Resource Name, Resource ID, IP Address, Protocol, and Count. See image.

[Image: The Flow dashboard]

[Image: Source Information]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-health-dashboard","lastmod":"2026-06-29T10:06Z","nid":"1483736"} -->
## Viewing the Health Dashboard

- Source: https://help.zscaler.com/zpa/viewing-health-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Viewing the Health Dashboard
- Last modified: 2026-06-29T10:06Z
- Summary: Information on the Health dashboard and widgets, accessible within the Zscaler Admin Console.

The Health dashboard provides widgets that display the health of your organization's application segments, App Connectors, and Private Service Edges for Private Access. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Health.

See image.

## Dashboard Tools

The Health dashboard displays the following information and functionality:

- **Search**: Search the dashboard for a specific application, App Connector, or Private Service Edge.
- **Refresh**: Refresh the dashboard to reflect the most current information for all widgets.

See image.

## Widgets

The Health dashboard provides the following widgets:

- Applications
- App Connectors
- Private Service Edges

Application health is checked and reported by the App Connectors your organization has deployed. This widget displays the health status of your applications depending on the Health Reporting setting you've configured for them. To learn more, see [Understanding Health Reporting](https://help.zscaler.com/zpa/understanding-health-reporting).

- Possible Application Health States
- Widget Filters
- Viewing Application Details

The widget displays the following health states for applications defined within application segments:

- **Up**: The applications are up and functioning as expected.

[Image: Application health is up]

- **Down**: The applications are down and not accessible to users. This is most likely because a server that hosts the application is down or unhealthy.

[Image: Application health is down]

- **Unhealthy**: The applications are unhealthy but still accessible to users. An application can have multiple servers that host it, and at least one of those servers is unhealthy or down. But because there's at least one server for the application that is up, users can access the application.

[Image: Application is unhealthy]

- **Unknown**: The application health is unknown. This status is shown only for applications associated with application segments configured with [Health Reporting set to On Access](https://help.zscaler.com/zpa/configuring-defined-application-segments#define-cmnconfig). It indicates that Private Access has stopped reporting the health of this application because it has been more than 30 minutes since a user accessed it. Private Access reports the health status as soon as a user accesses the application again.

[Image: Application health is unknown]

If an application has not been accessed by a user or if an application segment was configured with [Health Reporting set to On Access](https://help.zscaler.com/zpa/configuring-defined-application-segments#define-cmnconfig), then no state is displayed. Additionally, if an application has Client Hostname Validation enabled to facilitate client-to-client remote assistance, then the application is not shown on the Health Dashboard. To learn more, see [Validating a Client Hostname](https://help.zscaler.com/zpa/validating-client-hostname).

You can view or hide applications based on their health status with the **Health Status Filters**. You must select at least one filter option.

[Image: Application filters]

If there are more than 2,000 applications listed, the filters are not displayed.

For each application, you can view more information about the application in a variety of ways.

- Hover over the application icon to view the following details:
  - **Name**: The name of the application.
  - **Port**: The portused by the application.
  - **Protocol Type**: The protocol typeused by the application.
  - **Last Updated**: The timestamp showing the last time the App Connector reported the application's health status.

[Image: Viewing the application details]

- Hover over the application icon and click on the graph icon at the top-right. The graphical view that appears visually depicts the servers that host that application and the App Connectors that provide access to those servers. If an application is down or unhealthy, the graphical view enables you to pinpoint the problem.

[Image: Graphical view of Private Access objects from the Health Dashboard]

- Click on an application to see servers that host that application. You can then drill down further by clicking the arrow for a server to see the App Connectors that provide access to that server.

[Image: Connected Applications to App Connectors on the Health Dashboard]

App Connector health is checked and reported by the Private Access cloud.

- Possible App Connector Health States
- Widget Filters
- Viewing App Connector Details

The widget displays the following health states for App Connectors:

- **Up**: The App Connector is up and functioning as expected.

[Image: App Connector health status as up]

For App Connectors that are disabled but not yet processed as Down, a Disabled label appears under the App Connector.

See image.

- **Down**: The App Connector is down and not functional.

[Image: App Connector health status as down]

[Image: Disabled App Connector in the Health Dashboard]

You can view or hide App Connectors based on their health status with the **Health Status Filters**. You must select at least one filter option.

If there are more than 2,000 App Connectors listed, the filters are not displayed.

[Image: App Connector filters]

For each App Connector, you can view more information about it by hovering over the App Connector icon to view the following details:

- **Name**: The name of the App Connector.
- **Last Updated**: The timestamp showing the last time the Private Access cloud checked the health status.
- **Public IP**: The public IP address of the App Connector.
- **Private IP**: The private IP address of the App Connector.
- **Version**:The software versionnumber of the App Connector.
- **CPU Utilization**: The CPU usage of the App Connector.
- **Memory Utilization**: The memory usage of the App Connector.
- **Up Time**:How long the App Connector has been enrolled and running.
- **Active Apps:** The number of applications currently active for this App Connector.

[Image: App Connector details]

Private Service Edge health is checked and reported by the Private Access cloud.

- Possible Private Service Edge Health States
- Widget Filters
- Viewing Private Service Edge Details

The widget displays the following health states for Private Service Edges:

- **Up**: The Private Service Edge is up and functioning as expected.

[Image: Private Service Edge health status is up]

For Private Service Edges that have been stopped but not yet processed as Down, a Disconnected label appears under the Private Service Edge.

- **Down**: The Private Service Edge is down and not functional.

[Image: Private Service Edge health status is down]

You can view or hide Private Service Edges based on their health status with the **Health Status Filters**. You must select at least one filter option.

If there are more than 2,000 Private Service Edges listed, the filters are not displayed.

[Image: Health Status Filters for Private Service Edges]

For each Private Service Edge, you can view more information about it by hovering over the Private Service Edge icon to view the following details:

- **Name**: The name of the Private Service Edge.
- **Last Updated**: The timestamp showing the last time the Private Access cloud checked the health status.
- **Public IP**: The public IP address of the Private Service Edge.
- **Private IP**: The private IP address of the Private Service Edge.
- **CPU Utilization**: The CPU usage of the Private Service Edge.
- **Memory Utilization**: The memory usage of the Private Service Edge.
- **Up Time**: How long the Private Service Edge has been enrolled and running.

[Image: Private Service Edge details on the Health dashboard]

[Image: The Health dashboard within the Zscaler Admin Console]

[Image: Health dashboard tools]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-network-connectors-dashboard","lastmod":"2026-09-10T12:16Z","nid":"1525456"} -->
## Viewing the Network Connectors Dashboard

- Source: https://help.zscaler.com/zpa/viewing-network-connectors-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > VPN (for Legacy Apps) > Dashboard & Diagnostics > Viewing the Network Connectors Dashboard
- Last modified: 2026-09-10T12:16Z
- Summary: Information about the Network Connectors dashboard and widgets available in the Zscaler Admin Console.

The Network Connectors Dashboard provides information about the Network Connectors for your organization. To view the dashboard in the Zscaler Admin Console, go to Private Access > VPN (for Legacy Apps)> Dashboard > VPN Dashboard.

[Image: View of the Network Connectors dashboard in the Zscaler Admin Console]

## Dashboard Tools

The Network Connectors Dashboard displays the following information and functionality:

- **Time Period Filter**: View Network Connector data over a period between 1 hour to 14 days, or you can select **Custom Range**. If you use a custom range, the start and end dates must be within the last 14 days. The end date can be configured to the selected time in hours and minutes. This filter applies to all widgets on the dashboard. By default, the dashboard displays information about events that occurred in the last hour. Due to how data is aggregated for different time period filters, the same point in time in an Activity Monitor widget might show slightly different values depending on the selected time. For example, the data with the 1-hour time period filter at 3:00 PM might not match the data with the 14 Days time period filter for the same date at 3:00 PM.
- **Refresh icon**: Refresh the dashboard to adjust the time period filter to reflect the most current information.

## Top Network Connectors Widgets

The Top Network Connectors widgets provide an overview of the peak or top metrics for the relevant Network Connectors in the selected time range.

Four widgets are selected automatically when you access the dashboard. At least 4 widgets must be selected for the widgets to display, and no more than 8 widgets are available to view at one time.

View the widgets.

## Activity Monitor Widgets

The Activity Monitor widgets provide trend information about selected Network Connectors in the selected time range. If no Network Connectors are selected, the top Network Connectors from the Minimum Available Disk Space widget are selected by default.

Four widgets are selected automatically when you access the dashboard. At least 4 widgets must be selected for the widgets to display, and no more than 6 widgets are available to view at one time.

View the widgets.

Widgets might show solid lines or dashed lines. Solid lines represent actual data for the time period. Dashed lines indicate the expected trajectory of the data, but it isn't actual data. You can select a point on the lines in a widget to see the exact date, time, and relevant numbers for the Network Connectors as related to the particular widget.

See image.

A plus icon (+) appears as you move over the widgets. Use this icon to select the time period to zoom in for greater detail. A blue box shows the chosen portion of the widget, and the widget shows this selected smaller time period.

See image.

Click **Zoom Out** to view the original widget.

See image.

For each widget, you can deselect the listed Network Connectors to change what items are tracked within the widget. You can also search within the widget to reduce the listed Network Connectors that appear on the widget. To search, enter part or all of a Network Connector name, or use the following search query options with >, <, or = operators:

- **name**: The name of the Network Connector (e.g., `name = MyConnector`).
- **value**: A numerical value specific to the widget (e.g., `value < 40`).

See image.

### Filtering Network Connectors

You can filter the Activity Monitor widgets and the Network Connector Details table by selecting the Network Connectors you want to review. The filters available are **Network Connectors** and **Network Connector Groups**.

See image.

When filtering, if you select a Network Connector group, the Network Connector associated with the Network Connector group is also shown.

The filters between Network Connectors and Network Connector groups use the OR operator, instead of AND, to help compare Network Connectors in Network Connector groups.

There is a limit of 25 Network Connectors you can select at one time. If you select Network Connectors and then select Network Connector groups in a way that exceeds the limit, you see an error message and need to adjust your selection.

See image.

If no Network Connectors are selected, the top Network Connectors from the Minimum Available Disk Space widgets are selected by default.

## Network Connector Details

The Network Connector Details table provides information about the Network Connectors selected in the Activity Monitor section. If no Network Connectors are selected, the top Network Connectors from the Minimum Available Disk Space widget are selected by default.

The table includes:

- **Network Connectors**: The name of the Network Connector.
- **Network Connector Group**: The name of the group the Network Connector is included in.
- **Location**: The city and country that the Network Connector is connecting from.
- **Actions**: Click the **Edit**icon to edit the Network Connector.

[Image: Actions for the VPN Connector Details table]

- **Minimum Available Disk Space**: Displays up to the top 10 Network Connectors that have the least disk space available in the selected time frame. This is not the average disk space used over the time frame.
- **Peak CPU Utilization**: Displays up to the top 10 Network Connectors using the most CPU in the selected time frame. This is not the average CPU used over the time frame.
- **Peak Memory Utilization**: Displays up to the top 10 Network Connectors using the most memory in the selected time frame. This is not the average memory used over the time frame.
- **Peak TCP Port Utilization**: Displays up to the top 10 Network Connectors using the most TCP ports for IPv4 in the selected time frame. This is not the average TCP ports used over the time frame.
- **Peak UDP Port Utilization**: Displays up to the top 10 Network Connectors using the most UDP ports for IPv4 in the selected time frame. This is not the average UDP ports used over the time frame.
- **Peak System File Descriptor Utilization**: Displays up to the top 10 Network Connectors using the most system file descriptors in the selected time frame. This is not the average file descriptors used over the time frame.
- **Peak Network Connector Throughput**: Displays up to the top 10 Network Connectors' throughput (in percentage) and bandwidth (in bytes) in the selected time frame.

- **Available Disk Space**: Displays the amount of disk space that is available to a Network Connector at different points during the selected time range.
- **CPU Utilization**: Displays the amount of CPU used by a Network Connector at different points during the selected time range.
- **Memory Utilization**: Displays the amount of memory used by a Network Connector at different points during the selected time range.
- **TCP Port Utilization**: Displays the number of TCP ports for IPv4 used by a Network Connector at different points during the selected time range.
- **UDP Port Utilization**: Displays the number of UDP ports for IPv4 used by a Network Connector at different points during the selected time range.
- **System File Descriptor Utilization**: Displays the number of file descriptors used by a Network Connector at different points during the selected time range.
- **Network Connector Throughput**: Displays the Network Connectors' throughput (in percentage) and bandwidth (in bytes) at different points during the selected time range.

[Image: Select a point or view logs for part of a chart on the Network Connector Dashboard in the Zscaler Admin Console]

[Image: Select a part of a chart on the Network Connector Dashboard in the Zscaler Admin Console]

[Image: Zoom Out of a chart on the Network Connector Dashboard in the Zscaler Admin Console]

[Image: Search and Deselect Network Connectors in Widgets on the Network Connector Dashboard in the Zscaler Admin Console]

[Image: Filter Network Connectors on the Network Connector Dashboard in the Zscaler Admin Console]

[Image: Number of Network Connectors in an Network Connector Group in the Network Connector Group filter on the Network Connector Dashboard in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-overview-dashboard","lastmod":"2026-09-11T07:16Z","nid":"1531939"} -->
## Viewing the Overview Dashboard

- Source: https://help.zscaler.com/zpa/viewing-overview-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Microsegmentation > Dashboard > Viewing the Overview Dashboard
- Last modified: 2026-09-11T07:16Z
- Summary: How to view the Overview dashboard for Microsegmentation in the Zscaler Admin Console.

The Overview dashboard provides information about the status of agents and resource protection in your organization.

To view the Overview dashboard, from the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Dashboard** > **Microsegmentation** **Overview**.

## Dashboard Tools

The Overview dashboard displays the following information and functionality:

- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **Agent Licensing**: Shows how many agents are licensed and actively connected.
- **ML Resource Group Recommendations**: Shows how many machine learning (ML) recommendations for resource groups have been found and allows you to review them.
- **ML Tag Recommendations**: Shows how many ML tag recommendations for resource groups have been found and allows you to review them.
- **Host Agent Status**: Shows how many agents are **Connected** or **Disconnected**, or in **Error** state.
- **Host Agent Version**: Shows how many agents are using which agent version.
- **Kubernetes Agent Status**: Shows how many agents are **Connected** or **Disconnected**,
- **Kubernetes Agent Version**: Shows how many agents are using which agent version.
- **Resource Protection Status**: Shows how many resources are **Protected**or **Unprotected**. You can also download a CSV file of this information.
- **Resource Group Protection Status**: Shows how many resource groups are **Protected**or **Unprotected**. You can also download a CSV file of this information.

[Image: The Overview dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-policy-usage","lastmod":"2026-04-20T16:13Z","nid":"1530858"} -->
## Viewing Policy Usage

- Source: https://help.zscaler.com/zpa/viewing-policy-usage
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Policy Usage
- Last modified: 2026-04-20T16:13Z
- Summary: Viewing and understanding the usage between the policy rules and users in the Policy Usage Report.

Policy Usage insights provides you with a deeper understanding and details about the distribution of access policy rules used by users. These insights can help you assess and determine unused access policy rules for improvement consideration. The Policy Usage insights provide:

- A distribution of users and their associated access policy rules.
- Most used access policy rules.
- Least used access policy rules.

See image.

## Policy Usage Report

The Policy Usage Report contains the following information:

- The number of Access Policy Rules applied to the distribution of users. The distribution of users is calculated based on the number of unique users (must be more than zero users) against each access policy rule. Then the distribution categorizes the users into 1 of 10 percentage bars based on their usage of the access policy rule. Each bar indicates a tenth percentage of access policy rules based on usage. You can click on a bar to view policy usage data based on that user group. The percentage is rounded to the nearest whole number.
  - **Most Used Access Policy Rules**: Displays the number of most used access policy rules.
  - **Unused Access Policy Rules**: Displays the number of the least used access policy rules. These include unused access policies.
- **Current Report Information**: Displays the current report's information. See image.
- **Launch Tour**: Launches a series of guided steps on how to interact with the Policy Usage page.
- **Run Report**: Generate the report to include data from the selected time range. The report automatically expires after 90 days. You cannot run the report if there is no more data to add when the report was recently updated. Run Report is disabled for 72 hours before and after 12:00 AM on the first Saturday of every month due to scheduled automatic reports. You cannot manually generate a report during this time. Additionally, customers with the Segmentation Add-On feature can generate one report per day, and customers without the feature can generate one report every 90 days. See image.

See image.

## Policy Usage Chart and Table

The Policy Usage chart and table provides the following information:

- **Policy Usage Bar Chart**: Displays the distribution of policy usage from access policy rules. Select a bar to specify the access policy rules. The default is set to Unused Access Policy Rules. See image.
- **Access Policy Rule Filter**: Select specific access policy rules for display. See image.
- **View Access Policy**: Allows you to access the [Access Policy](https://help.zscaler.com/zpa/about-access-policy) page to manage the access policies.
- **Access Policy Rules Table**: Displays the following information for each access policy:
  - **Access Policy Rule**: The name of the access policy rule.
  - **Unique Users**: The number of unique users impacted by the access policy rule.
  - **Transactions**: The number of Private Access (ZPA) transactions going through the access policy.
  - **Actions**: The available actions for the access policy rule.
    - **Edit**: Edit the access policy rule in the **Edit Access Policy** window. To learn more, see [Editing Access Policies](https://help.zscaler.com/zpa/editing-access-policies).
    - **Policy Hit and User Details**: View the access policy rule's policy usage details. To learn more, see [Viewing Policy Usage Details](https://help.zscaler.com/zpa/viewing-policy-usage-details).

See image.

[Image: Current Report Information]

[Image: Policy Usage Report Information]

[Image: Run a Report]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-policy-usage-details","lastmod":"2025-09-26T07:06Z","nid":"1530857"} -->
## Viewing Policy Usage Details

- Source: https://help.zscaler.com/zpa/viewing-policy-usage-details
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Policy Usage Details
- Last modified: 2025-09-26T07:06Z
- Summary: Police Usage details include granular information for a specific access policy rule from the Policy Usage page.

Policy usage details are accessible from the following pages:

- [Access Policy](https://help.zscaler.com/zpa/about-access-policy)
- [Defined Application Segments](https://help.zscaler.com/zpa/about-applications)
- [Policy Usage](https://help.zscaler.com/zpa/viewing-policy-usage)

When you click the **Graph** icon (), you can view the following policy usage details:

- **Time Range**: Select to filter details based on a time range.
- **Refresh**: Click the **Refresh** icon to include the most recent updates. The report automatically refreshes every 30 days.
- Depending on what view you have selected, you see the following widgets: When you click **View Logs** on a widget, you are taken to the Diagnostics page. To learn more, see [Accessing User Activity Diagnostics](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics).
  - **Users** view(default):
    - **Users Over Time**: Displays the number of users over time for the policy.
    - **Top Application Segments**: Displays the most impacted application segments. You can hover over and then select an application to switch the view between application segments and applications. To switch back, select the application segment name.
    - **Top Applications**: Displays the most impacted applications across users. You can hover over and then select ports to switch the view between applications and ports. To switch back, select the application name.
    - **Top Ports**: Displays the most used ports.
    - **Top Client Types**: Displays the most impacted client types for the policy.
  - **Transactions** view:
    - **Transactions Over Time**: Displays the number of transactions for the policy.
    - **Top Application Segments**: Displays the most impacted application segments. You can hover over and then select an application to switch the view between application segments and applications. To switch back, select the application segment name.
    - **Top Applications**: Displays the most impacted applications across users. You can hover over and then select a port to switch the view between applications and ports. To switch back, select the application name.
    - **Top Ports**: Displays the most used ports.
    - **Top Users**: Displays the users creating the most transactions for the policy.
    - **Top Client Types**: Displays the most impacted client types for the policy.

See image.

No data is shown if the access policy has not seen usage within the time range (e.g., unused access policy).

See image.

[Image: No Data Available]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-private-cloud-controllers-dashboard","lastmod":"2026-06-29T10:10Z","nid":"1506341"} -->
## Viewing the Private Cloud Controllers Dashboard

- Source: https://help.zscaler.com/zpa/viewing-private-cloud-controllers-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Private Cloud Controller Monitoring > Viewing the Private Cloud Controllers Dashboard
- Last modified: 2026-06-29T10:10Z
- Summary: Information on the Private Cloud Controllers dashboard and widgets accessible within the Zscaler Admin Console.

The Private Cloud Controllers dashboard provides information about the Private Cloud Controllers for your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Private Cloud Controllers.

See image.

## Dashboard Tools

The Private Cloud Controllers dashboard displays the following information and functionality:

- **Time Range Filter**: View user data over a period between **30 Mins** to **14 Days**, or you can select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 14 days. This filter applies to all widgets on the dashboard. By default, the dashboard displays information for events that occurred in the past 30 minutes.

Due to the way data is aggregated for different time period filters, the same point in time in an Activity Monitor widget might show slightly different values depending on the time chosen. For example, the data with the **30 Mins** time period filter at 3:00 PM might not match the data with the **14 Days** time period filter for the same date at 3:00 PM.

- **Refresh Icon**: Refresh the dashboard to reflect the most current information. The page displays the most recent information from the last 5 minutes.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 3 charts and a maximum of 6 charts can be selected.

See image.

## Top Private Cloud Controller Widgets

The Top Private Cloud Controller widgets provide an overview of the peak or top metrics for the relevant Private Cloud Controllers in the selected time range.

Four widgets are selected automatically when you access the dashboard. At least 4 widgets must be selected for the widgets to display, and no more than 8 widgets are available to view at one time.

- View the widgets.

Click on any of the Private Cloud Controllers in the widgets to view the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Cloud Controller.

- **Minimum Available Disk Space**: Displays up to the top 10 Private Cloud Controllers that have the least disk space available in the selected time range. This is not the average disk space used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak CPU Utilization**: Displays up to the top 10 Private Cloud Controllers using the most CPU in the selected time range. This is not the average CPU used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak File Descriptor Utilization**: Displays up to the top 10 Private Cloud Controllers using the most file descriptors in relation to their maximum number of file descriptors.
- **Peak Memory Utilization**: Displays up to the top 10 Private Cloud Controllers using the most memory in the selected time range. This is not the average memory used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak TCP IPv6 Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most TCP ports for IPv6 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Cloud Controllers using TCP ports for IPv4.
- **Peak TCP Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most TCP ports for IPv4 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Cloud Controllers using TCP ports for IPv6.
- **Peak UDP IPv6 Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most UDP ports for IPv6 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Cloud Controllers using UDP ports for IPv4.
- **Peak UDP Port Utilization**: Displays up to the top 10 Private Cloud Controllers using the most UDP ports for IPv4 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Cloud Controllers using UDP ports for IPv6.
- **Total Bytes Received from Public Service Edges**: Displays up to the top 10 Private Cloud Controllers and the number of bytes received by each from the Public Service Edges for Private Access (ZPA).
- **Total Bytes Transmitted to Public Service Edges**: Displays up to the top 10 Private Cloud Controllers and the number of bytes transmitted by each to the Public Service Edges.
- **Total App Connectors Connected to the Private Cloud Controller**: Displays up to the top 10 Private Cloud Controllers and the total number of App Connectors connected to each.
- **Total Private Service Edges Connected to the Private Cloud Controller**: Displays up to the top 10 Private Cloud Controllers and the total number of Private Service Edges for Private Access connected to each.
- **Peak Zscaler Client Connector Redirections to Private Service Edges**: Displays up to the top 10 Zscaler Client Connector redirects to the Private Service Edges.
- **Logs Successfully Transmitted to SIEM**: The total number of successful logs transmitted to the SIEM. This is a cumulative count of all diagnostic log types (i.e., User Status, User Activity, App Connector Status, Private Service Edge Status, etc.).
- **Logs Failed to be Transmitted to SIEM**: The total number of logs that failed to be transmitted to the SIEM. This is a cumulative count of all diagnostic log types (i.e., User Status, User Activity, App Connector Status, Private Service Edge Status, etc.).

## Activity Monitor Widgets

The Activity Monitor widgets provide trend information about selected Private Cloud Controllers in the selected time range. If no Private Cloud Controllers are selected, the top Private Cloud Controllers from the Peak Memory Utilization widget are selected by default.

Three widgets are selected automatically when you access the dashboard. At least three widgets must be selected for the widgets to display, and no more than 6 widgets are available to view at one time.

- View the widgets.

Widgets show solid lines that represent actual data for the time period. You can select a point on the lines in a widget to see the exact date, time, and relevant numbers for the Private Cloud Controllers as related to the particular widget. You can also click **Show All Names**to show or hide the particular labels for the Private Cloud Controllers in the widget.

See image.

Select a part of the chart to zoom in for greater detail. A blue box shows the chosen portion of the widget, and the widget shows this selected smaller time period.

See image.

Click **Zoom Out** to view the original widget.

See image.

For each widget, you can deselect the listed Private Cloud Controllers to change what items are tracked within the widget. You can also search within the widget to reduce the listed Private Cloud Controllers that appear in the widget. To search, enter part or all of a Private Cloud Controller name, or use the following search query options with >, <, or = operators:

- Name: The name of the Private Cloud Controller (e.g., name = MyPrivateCloudController).
- Value: A numerical value specific to the widget (e.g., value < 40).

See image.

- **Available Disk Space**: Displays the amount of disk space that is available to a Private Cloud Controller at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **CPU Utilization**: Displays the amount of CPU used by a Private Cloud Controller at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **File Descriptor Utilization**: Displays the number of file descriptors used by a Private Cloud Controller at different points during the selected time range.
- **Memory Utilization**: Displays the amount of memory used by a Private Cloud Controller at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **TCP IPv6 Port Utilization**: Displays the number of TCP ports for IPv6 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using TCP ports for IPv4.
- **TCP Port Utilization**: Displays the number of TCP ports for IPv4 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using TCP ports for IPv6.
- **UDP IPv6 Port Utilization**: Displays the number of UDP ports for IPv6 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using UDP ports for IPv4.
- **UDP Port Utilization**: Displays the number of UDP ports for IPv4 used by a Private Cloud Controller at different points during the selected time range. It does not show Private Cloud Controllers using UDP ports for IPv6.
- **Bytes Received from Public Service Edges**: Displays the number of bytes received by the Private Cloud Controller from the Public Service Edges.
- **Bytes Transmitted to Public Service Edges**: Displays the number of bytes transmitted by the Private Cloud Controller from the Public Service Edges.
- **App Connectors Connected to the Private Cloud Controller**: Displays the number of App Connectors connected to the Private Cloud Controller.
- **Private Service Edges Connected to the Private Cloud Controller**: Displays the number of Private Service Edges connected to the Private Cloud Controller.
- **Zscaler Client Connector Redirections to Private Service Edges**: Displays the number of Zscaler Client Connector redirects to the Private Service Edges.
- **Logs Successfully Transmitted to SIEM**: Displays the number of successful logs that are transmitted to the SIEM.
- **Logs Failed to be Transmitted to SIEM**: Displays the number of logs that failed to be transmitted to the SIEM.

### Filtering Private Cloud Controllers

You can filter the Activity Monitor charts and Private Cloud Controller Details table by selecting the Private Cloud Controllers you want to review. The filters available are **Private Cloud Controllers** and **Private Cloud Controller Groups**.

See image.

When filtering by Private Cloud Controllers and Private Cloud Controller groups, the selected Private Cloud Controllers and Private Cloud Controllers within the selected Private Cloud Controller groups are shown.

The filters between Private Cloud Controllers and Private Cloud Controller groups use the OR operator, instead of AND, to help compare Private Cloud Controllers in Private Cloud Controller groups.

There is a limit of 25 Private Cloud Controllers you can select at one time. If you haven't selected any Private Cloud Controllers and have selected a Private Cloud Controller group that contains more than 25 Private Cloud Controllers, then the first 25 Private Cloud Controllers for the selected Private Cloud Controller group are used in the filter. If you select Private Cloud Controllers and then select Private Cloud Controller groups in a way that exceeds the limit, an error message appears and you must adjust your selection.

Within the **Private Cloud Controller Groups** filter, you can see the number of Private Cloud Controllers associated with the Private Cloud Controller group.

See image.

If no Private Cloud Controllers are selected, the top Private Cloud Controllers from the **Peak Memory Utilization chart** are selected by default.

## Private Cloud Controller Details

The Private Cloud Controller Details table provides information about the Private Cloud Controller selected in the Activity Monitor section. If no Private Cloud Controllers are selected, the top Private Cloud Controllers from the **Peak Memory Utilization** widget are selected by default.

The table covers:

- **Private Cloud Controllers**: The name of the Private Cloud Controllers.
- **Private Cloud Controller Group**: The name of the group the Private Cloud Controller is included in.
- **Location**: The city and country that the Private Cloud Controller is connecting from.
- **Actions**: The following actions are available:
  - **View**: View the Private Cloud Controller Details and Private Cloud Controller Latency pages.
  - **Edit**: Edit the Private Cloud Controller.
  - **Logs**: View the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Cloud Controller.

See image.

### Evaluating Individual Private Cloud Controller Details and Latency

For each Private Cloud Controller, you can click the **View**icon for a Private Cloud Controller in the Private Cloud Controller Details table. For each Private Cloud Controller, you can view:

- Details
- Latency

The **Details** tab is automatically selected.

See image.

- General Information
- Private Cloud Controller Information
- Activity Monitor Widgets

The general information available about the Private Cloud Controller:

- **Private Cloud Controller Group**: The name of the group the Private Cloud Controller is included in.
- **Location**: The city and country that the Private Cloud Controller is connecting from.
- **Enabled**: Identifies if the Private Cloud Controller is enabled or disabled.
- **Session Status**: The status of the Private Cloud Controller session during the time range. The potential session statuses are:
  - **Authenticated**: The Private Cloud Controller successfully authenticated.
  - **Authentication Failed**: The Private Cloud Controller was unable to authenticate to the Zscaler cloud.
  - **Disconnected**: The Private Cloud Controller successfully disconnected.
- **Periodic Software Update On**: The date and time of the next periodic software update for the Private Cloud Controller.
- **Last Software Update On**: The date and time of the last software update for the Private Cloud Controller.
- **Scheduled Software Version**: The next Private Cloud Controller software version that the Private Cloud Controller upgrades to.
- **Current** **Software** **Version**: The current Private Cloud Controller software version during the time range.
- **Connection Status**: The connection status of the Private Cloud Controller during the time range. The potential session statuses are:
  - **Connected**: The Private Cloud Controller is up during the time range.
  - **Disconnected**: The Private Cloud Controller is down during the time range.

[Image: General information for a Private Cloud Controller on the Private Cloud Controllers dashboard]

The values and percentages for each item in this section are initially based on the most recent data available as noted by the point in time listed on the right side of this section.

[Image: Time Selection for Individual Private Cloud Controller Details]

The information available about the Private Cloud Controller includes:

- **Active Connections to App Connectors**: The number of active connections the Private Cloud Controller had to App Connectors for the point in time listed.
- **Active Connections to Public Service Edges**: The active connection the Private Cloud Controller had to Public Service Edges for the point in time listed.
- **Available Disk Space**: The size of available disk space for the Private Cloud Controller at the point in time listed.
- **Bytes Received from the Public Service Edge**: The number of received bytes from Public Service Edges to the Private Cloud Controller in the selected time range.
- **Bytes Transmitted to the Public Service Edge**: The number of transmitted bytes to Public Service Edges from the Private Cloud Controller in the selected time range.
- **CPU Utilization**: The highest CPU used by the Public Service Edge for the time range selected.
- **Logs Failed to be Transmitted to SIEM**: The total number of logs that failed to be transmitted to the SIEM.
- **File Descriptor Utilization**: The file descriptors used by the Public Service Edge for the point in time listed.
- **Memory Utilization**: The highest memory used by the Private Cloud Controller for the selected time range.
- **Logs Successfully Transmitted to SIEM**: The total number of logs successfully transmitted to the SIEM.
- **TCP IPv6 Port Utilization:**The number of TCP ports for IPv6 used by the Private Cloud Controller for the point in time listed. It does not show Private Cloud Controllers using TCP ports for IPv4.
- **TCP Port Utilization**: The number of TCP ports for IPv4 used by the Private Cloud Controller for the point in time listed. It does not show Private Cloud Controllers using TCP ports for IPv6.
- **UDP IPv6 Port Utilization:**The number of UDP ports for IPv6 used by the Private Cloud Controller for the point in time listed. It does not show Private Cloud Controllers using TCP ports for IPv4.
- **UDP Port Utilization**: The number of UDP ports for IPv4 used by the Private Cloud Controllers for the point in time listed. It does not show Private Cloud Controllers using UDP ports for IPv6.
- **Zscaler Client Connector Redirections to Private Service Edges**: The number of Zscaler Client Connector redirects to the Private Service Edges.

The Activity Monitor section displays the same widgets with the same functionality as seen in the Activity Monitor section. Three widgets are selected automatically:

- Available Disk Space
- CPU Utilization
- Memory Utilization

The latency monitor widgets on the Latency tab show the latency trend from a Public Service Edge to a Private Cloud Controller in the selected time range. The time range for the Latency tab is different from the rest of the Private Cloud Controllers dashboard. It is limited to data over a period between **30 Mins** to **24 Hours**, or you can select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 24 hours. This filter applies to all widgets in the Latency tab, and the default time is 30 minutes.

The widgets available are:

- Private Cloud Controller to Default Gateway TCP Latency
- Private Cloud Controller to Public Service Edge Latency

Each widget shows the Public Service Edges that interact with the selected Private Cloud Controller during the time range. The widgets have the same functionality as seen in the Activity Monitor section.

For each widget, you can search by entering part or all of a Private Cloud Controller name, or by using the following search query options with >, <, or = operators:

- Location: The location of the Private Cloud Controller (e.g., location = San Jose).
- Name: The name of the Private Cloud Controller (e.g., name = sj_serviceedge).
- Value: A numerical value specific to the widget (e.g., value < 40).

[Image: Latency Tab on the Private Cloud Controllers Dashboard]

[Image: Viewing the labels for each data point within the Activity Monitor widgets]

[Image: Select a part of a chart]

[Image: Zoom Out of a chart on the Private Cloud Controllers Dashboard]

[Image: Search and Deselect Private Cloud Controllers in Widgets]

[Image: Filter Private Cloud Controllers on the Private Cloud Controllers Dashboard in the Zscaler Admin Console]

[Image: Number of Private Cloud Controller Groups in the Private Cloud Controller Group filter]

[Image: Actions for the Private Cloud Controller Details table]

[Image: Details and Latency Tabs for Private Cloud Controllers in the Private Cloud Controllers Dashboard]

[Image: Private Cloud Controller Dashboard]

[Image: Private Cloud Controllers Dashboard Tools]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-private-service-edges-dashboard","lastmod":"2026-07-30T14:22Z","nid":"1485151"} -->
## Viewing the Private Service Edges Dashboard

- Source: https://help.zscaler.com/zpa/viewing-private-service-edges-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Private Service Edge Monitoring > Viewing the Private Service Edges Dashboard
- Last modified: 2026-07-30T14:22Z
- Summary: Information on the Private Service Edges dashboard and widgets accessible within the Zscaler Admin Console.

The Private Service Edges dashboard provides information about the Private Service Edges for Private Access for your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Private Service Edges.

See image.

## Dashboard Tools

The Private Service Edges dashboard displays the following information and functionality:

- **Time Range Filter**: View user data over a period between **30 Mins** to **14 Days**, or select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 14 days. This filter applies to all widgets on the dashboard. By default, the dashboard displays information for events that occurred in the past 30 minutes.

Due to the way data is aggregated for different time period filters, the same point in time in an Activity Monitor widget might show slightly different values depending on the time chosen. For example, the data with the **30 Mins** time period filter at 3:00 PM might not match the data with the **14 Days** time period filter for the same date at 3:00 PM.

- **Refresh Icon**: Refresh the dashboard to reflect the most current information. The page displays the most recent information from the last 5 minutes.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 4 charts and a maximum of 8 charts can be selected.

See image.

## Top Private Service Edges Widgets

The Top Private Service Edges widgets provide an overview of the peak or top metrics for the relevant Private Service Edges in the selected time range.

Four widgets are selected automatically when you access the dashboard. At least 4 widgets must be selected for the widgets to display, and no more than 8 widgets are available to view at one time.

- View the widgets.

Click any of the Private Service Edges in the widgets to view the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Service Edge.

- **Top Errors**: Displays up to the top 100 Private Service Edges that had the most errors in the selected time range. This widget displays automatically when first accessing the dashboard.
- **Minimum Available Disk Space**: Displays up to the top 10 Private Service Edges that have the least disk space available in the selected time range. This is not the average disk space used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak CPU Utilization**: Displays up to the top 10 Private Service Edges using the most CPU in the selected time range. This is not the average CPU used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak File Descriptor Utilization**: Displays up to the top 10 Private Service Edges using the most file descriptors in relation to their maximum number of file descriptors.
- **Peak Memory Utilization**: Displays up to the top 10 Private Service Edges using the most memory in the selected time range. This is not the average memory used over the time range. This widget displays automatically when first accessing the dashboard.
- **Peak System File Descriptor Utilization**: Displays up to the top 10 Private Service Edges in the entire system using the most file descriptors in the selected time range.
- **Peak TCP IPv6 Port Utilization**: Displays up to the top 10 Private Service Edges using the most TCP ports for IPv6 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Service Edges using TCP ports for IPv4.
- **Peak TCP Port Utilization**: Displays up to the top 10 Private Service Edges using the most TCP ports for IPv4 in the selected time range. This is not the average number of TCP ports used over the time range, and it does not show Private Service Edges using TCP ports for IPv6.
- **Peak UDP IPv6 Port Utilization**: Displays up to the top 10 Private Service Edges using the most UDP ports for IPv6 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Service Edges using UDP ports for IPv4.
- **Peak UDP Port Utilization**: Displays up to the top 10 Private Service Edges using the most UDP ports for IPv4 in the selected time range. This is not the average number of UDP ports used over the time range, and it does not show Private Service Edges using UDP ports for IPv6.

## Activity Monitor Widgets

The Activity Monitor widgets provide trend information about selected Private Service Edges in the selected time range. If no Private Service Edges are selected, the top Private Service Edges from the Peak Memory Utilization widget are selected by default.

Three widgets are selected automatically when you access the dashboard. At least three widgets must be selected for the widgets to display, and no more than 6 widgets are available to view at one time.

- View the widgets.

Widgets can show solid lines or dashed lines. Solid lines represent actual data for the time period. Dashed lines indicate the expected trajectory of the data, but it isn't actual data. You can select a point on the lines in a widget to see the exact date, time, and relevant numbers for the Private Service Edges as related to the particular widget. You can also click **View Logs** to see the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for the Private Service Edges in the widget.

See image.

Select a part of the chart to zoom in for greater detail. A blue box shows the chosen portion of the widget, and the widget shows this selected smaller time period.

See image.

Click **Zoom Out** to view the original widget.

See image.

For each widget, you can deselect the listed Private Service Edges to change what items are tracked within the widget. You can also search within the widget to reduce the listed Private Service Edges that appear in the widget. To search, enter part or all of a Private Service Edge name, or use the following search query options with >, <, or = operators:

- name: The name of the Private Service Edge (e.g., name = MyPrivateServiceEdge).
- value: A numerical value specific to the widget (e.g., value < 40).

See image.

- **Available Disk Space**: Displays the amount of disk space that is available to a Private Service Edge at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **CPU Utilization**: Displays the amount of CPU used by a Private Service Edge at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **File Descriptor Utilization**: Displays the number of file descriptors used by a Private Service Edge at different points during the selected time range.
- **Memory Utilization**: Displays the amount of memory used by a Private Service Edge at different points during the selected time range. This widget displays automatically when first accessing the dashboard.
- **System File Descriptor Utilization**: Displays the number of file descriptors used by the entire system running the Private Service Edges in the selected time range.
- **TCP IPv6 Port Utilization**: Displays the number of TCP ports for IPv6 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using TCP ports for IPv4.
- **TCP Port Utilization**: Displays the number of TCP ports for IPv4 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using TCP ports for IPv6.
- **UDP IPv6 Port Utilization**: Displays the number of UDP ports for IPv6 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using UDP ports for IPv4.
- **UDP Port Utilization**: Displays the number of UDP ports for IPv4 used by a Private Service Edge at different points during the selected time range. It does not show Private Service Edges using UDP ports for IPv6.

### Filtering Private Service Edges

You can filter the Activity Monitor charts and Private Service Edge Details table by selecting the Private Service Edges you want to review. The filters available are Private Service Edge and Private Service Edge Groups.

See image.

When filtering by Private Service Edges and Private Service Edge groups, the selected Private Service Edges and Private Service Edges within the selected Private Service Edge groups are shown.

The filters between Private Service Edges and Private Service Edge groups use the OR operator, instead of AND, to help compare Private Service Edges in Private Service Edge groups.

There is a limit of 25 Private Service Edges you can select at one time. If you haven't selected any Private Service Edges and have selected a Private Service Edge group that contains more than 25 Private Service Edges, then the first 25 Private Service Edges for the selected Private Service Edge group are used in the filter. If you select Private Service Edges and then select Private Service Edge groups in a way that exceeds the limit, an error message appears and you must adjust your selection.

Within the Private Service Edge group filter, you can see the number of Private Service Edges associated with the Private Service Edge group.

See image.

If no Private Service Edges are selected, the top Private Service Edges from the Peak Memory Utilization chart are selected by default.

## Private Service Edge Details

The Private Service Edge Details table provides information about the Private Service Edge selected in the Activity Monitor section. If no Private Service Edges are selected, the top Private Service Edges from the Peak Memory Utilization widget are selected by default.

The table covers:

- **Private Service Edges**: The name of the Private Service Edges.
- **Private Service Edge Group**: The name of the group the Private Service Edge is included in.
- **Location**: The city and country that the Private Service Edge is connecting from.
- **Actions**:
  - **View**: View the Private Service Edge Details and Private Service Edge Latency pages.
  - **Edit**: Edit the Private Service Edge.
  - **Logs**: View the [User Activity Diagnostic logs](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics) page filtered for this Private Service Edge.

See image.

### Evaluating Individual Private Service Edge Details and Latency

For each Private Service Edge, you can click the **View**icon for a Private Service Edge in the Private Service Edge Details table. For each Private Service Edge, you can view:

- Details
- Latency

The **Details** tab is automatically selected.

See image.

- General Information
- Private Service Edge Information
- Activity Monitor Widgets

The general information available about the Private Service Edge:

- **Private Service Edge Group**: The name of the group the Private Service Edge is included in.
- **Location**: The city and country that the Private Service Edge is connecting from.
- **Enabled**: Identifies if the Private Service Edge is enabled or disabled.
- **Session Status**: The status of the Private Service Edge session during the time range. The potential session statuses are:
  - **Authenticated**: The Private Service Edge successfully authenticated.
  - **Authentication Failed**: The Private Service Edge was unable to authenticate to the Zscaler cloud.
  - **Disconnected**: The Private Service Edge successfully disconnected.
- **Periodic Software Update On**: The date and time of the next periodic software update for the Private Service Edge.
- **Last Software Update On**: The date and time of the last software update for the Private Service Edge.
- **Scheduled Software Version**: The next Private Service Edge software version that the Private Service Edge upgrades to.
- **Current** **Software** **Version**: The current Private Service Edge software version during the time range.
- **Connection Status**: The connection status of the Private Service Edge during the time range. The potential session statuses are:
  - **Connected**: The Private Service Edge is up during the time range.
  - **Disconnected**: The Private Service Edge is down during the time range.

[Image: General information for a Private Service Edge on the Private Service Edge dashboard]

The values and percentages for each item in this section are initially based on the most recent data available as noted by the point in time listed on the right side of this section.

[Image: Time Selection for Individual Private Service Edge Details]

The information available about the Private Service Edge is:

- **Active Application Tunnel Count**: The number of active connections the Private Service Edge had to applications for the point in time listed.
- **Active Connections to App Connectors**: The number of active connections the Private Service Edge had to App Connectors for the point in time listed.
- **Active Connections to Public Service Edges**: The active connection the Private Service Edge had to Public Service Edges for Private Access for the point in time listed.
- **Available Disk Space**: The number of available disk space for the Private Service Edge at the point in time listed.
- **Total Bytes Received**: The number of received bytes from App Connectors to the Private Service Edge in the selected time range.
- **Total Bytes Transferred**: The number of transfered bytes to App Connectors from the Public Service Edges in the selected time range.
- **Configured Application Count**: The number of applications configured for access via the Private Service Edge at different points during the selected time range.
- **CPU Utilization**: The highest CPU used by the Public Service Edge for the time range selected.
- **Disconnected Connections to App Connectors**: The number of disconnected connections to App Connectors in the selected time range for a Private Service Edge.
- **Disconnected Connections to Public Service Edges**: The number of disconnected connections to Public Service Edges in the selected time range for a Private Service Edge.
- **File Descriptor Maximum Limit**: The maximum limit of file descriptors used by a Private Service Edge at different points during the selected time range.
- **File Descriptor Utilization**: The file descriptors used by the Public Service Edge for the point in time listed.
- **Number of Usable Ports**: The number of usable ports for a Private Service Edge at different points during the selected time range.
- **Memory Utilization**: The highest memory used by the Private Service Edge for the selected time range.
- **Rate of Bits Received from Public Service Edge**: The number of bits received per second by the Private Service Edge from the Public Service Edge for the point in time listed.
- **Rate of Bits Transmitted to Public Service Edges**: The number of bits transmitted per second by the Private Service Edge to Public Service Edges for the point in time listed.
- **TCP IPv6 Port Utilization:**The number of TCP ports for IPv6 used by the Private Service Edge for the point in time listed. It does not show Private Service Edges using TCP ports for IPv4.
- **TCP Port Utilization**: The number of TCP ports for IPv4 used by the Private Service Edge for the point in time listed. It does not show Private Service Edges using TCP ports for IPv6.
- **Total Transactions Completed**: The total number of completed connections to applications on a Private Service Edge.
- **UDP IPv6 Port Utilization:**The number of UDP ports for IPv6 used by the Private Service Edge for the point in time listed. It does not show Private Service Edges using TCP ports for IPv4.
- **UDP Port Utilization**: The number of UDP ports for IPv4 used by the Private Service Edges for the point in time listed. It does not show Private Service Edges using UDP ports for IPv6.

The Activity Monitor section displays the same widgets with the same functionality as seen in the Activity Monitor section. Three widgets are selected automatically. They are:

- Active Application Tunnel Count
- CPU Utilization
- Memory Utilization

The latency monitor widgets on the Latency tab show the latency trend from a Public Service Edge to a Private Service Edge in the selected time range. The time range for the Latency tab is different from the rest of the App Connector Dashboard. It is limited to data over a period between **30 Mins** to **24 Hours**, or you can select **Custom Range** to specify a custom start and end date. If you use **Custom Range**, the start date can be within the last 24 hours. This filter applies to all widgets in the Latency tab, and the default time is 30 minutes.

The widgets available are:

- Private Service Edge to Default Gateway TCP Latency
- Private Service Edge to Public Service Edge Latency

Each widget shows the Public Service Edges that interact with the selected Private Service Edge during the time range. The widgets have the same functionality as seen in the Activity Monitor section.

For each widget, you can search by entering part or all of a Private Service Edge name, or by using the following search query options with >, <, or = operators:

- location: The location of the Private Service Edge (e.g., location = San Jose).
- name: The name of the Private Service Edge (e.g., name = sj_serviceedge).
- value: A numerical value specific to the widget (e.g., value < 40).

[Image: Latency Tab on the Private Service Edge Dashboard]

[Image: Select a point in time or view the logs]

[Image: Select a part of the chart]

[Image: Zoom Out of a chart on the Private Service Edge Dashboard]

[Image: Search and Deselect Private Service Edges in Widgets]

[Image: Filter Private Service Edges on the Private Service Edge Dashboard in the Zscaler Admin Console]

[Image: Number of Private Service Edge Groups in the Private Service Edge Group filter]

[Image: Actions for the Private Service Edge Details table]

[Image: Details and Latency Tabs for Private Service Edges in the Private Service Edge Dashboard]

[Image: Viewing the Private Service Edge dashboard tools]

[Image: Private Service Edges dashboard tools]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-protocol-discovery-dashboard","lastmod":"2026-06-29T10:14Z","nid":"1486011"} -->
## Viewing the Protocol Discovery Dashboard

- Source: https://help.zscaler.com/zpa/viewing-protocol-discovery-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > AppProtection and Browser Protection Monitoring > Viewing the Protocol Discovery Dashboard
- Last modified: 2026-06-29T10:14Z
- Summary: Information on the Protocol Discovery dashboard accessible within the Zscaler Admin Console.

The Protocol Discovery dashboard displays protocols (KRB, LDAP, SMB, HTTP, and TLS) detected for domains with application segments that have AppProtection disabled. [You can enable AppProtection for the application segment](https://help.zscaler.com/zpa/configuring-defined-application-segments#ADProtection) when you click the application segment, the listed domains, and the ports and protocols mapped to it. The Protocol Discovery dashboard displays up to 100 of a domain's most recent transactions and 6,000 application segments for the time range selected. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Security > Protocol Discovery.

## Dashboard Tools

The AppProtection dashboard displays the following information and functionality:

- **Time Range Filter**: View Protocol Discovery data over a period between 1 hour to 7 days, or you can select **Custom Range**. If you use **Custom Range**, the start date must be within the last 7 days. The end date automatically sets to the system's current time. By default, the dashboard displays information for events that occurred in the last hour.
- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **AppProtection Dashboard**: View the [AppProtection page](https://help.zscaler.com/zpa/about-appprotection-dashboard) for information about AppProtection policy activity in your organization.
- **Browser Protection Dashboard**: View the [Browser Protection](https://help.zscaler.com/zpa/about-browser-protection-dashboard) page for information about browser sessions in your organization.

## Protocol Discovery Widget

You can select the application segment that you want displayed from the drop-down menu. You can enter a keyword to search by domain within the displayed application segment.

[Image: Selecting an application segment for the Protocol Discovery widget]

### Enabling AppProtection for an Application Segment

If you haven't [enabled AppProtection](https://help.zscaler.com/zpa/configuring-defined-application-segments#ADProtection)for the displayed application segment and want to inspect it, follow these steps:

1. Click the displayed application segment or any of its related items (domain, port, or protocol).
2. When you click one of the displayed items, the **Enable AppProtection** window appears. If the application segment doesn't include domains that have AppProtection enabled, and an AppProtection profile and policy don't already exist, you can adjust the AppProtection Profile Type and select the **Auto create an AppProtection policy** checkbox.

See image.

1. When you are finished, click **Save**. A confirmation message appears notifying you that you have successfully updated the application segment.

Make sure you also [generate the AppProtection enrollment (CA) certificate](https://help.zscaler.com/zpa/generating-zscaler-issued-enrollment-ca-certificates) to use the AppProtection feature. If you haven't already generated the certificate, then another pop-up window appears to generate the certificate.

You can view the adjusted application segments on the[Defined Application Segments page](https://help.zscaler.com/zpa/about-applications).

### Inspect Application Segments

To inspect at the application segment level:

1. Click the displayed application segment.
2. After you have clicked on the application segment, a pop-up message appears to confirm if you want to enable inspection for the application segment.
  - If you want to continue with inspection of the entire application segment, click **Yes**. The application segment is then inspected. A confirmation message appears notifying you that you have successfully updated the application segment.
  - If you don't want to inspect all of the application segments, click **Cancel**. You can go to the Defined Application page and [edit the application segment](https://help.zscaler.com/zpa/editing-application-segments) to adjust the domain and related ports.

See image.

### Inspect Domains and Ports

To inspect at the domain or port level:

1. Click one of the displayed domains or ports.
2. After you have clicked a domain or port, a pop-up message appears to confirm if you want to enable inspection for all of the UDP and TCP ports assigned to the application segment associated with the selected domain.
  - If you want to continue with inspection of all the ports, click**Yes**. The domain and its related ports for the related application segment are then inspected. A confirmation message appears notifying you that you have successfully updated the application segment.
  - If you don't want to inspect all of the current ports for the application segment that the domain is associated with, click **Cancel**. You can go to the Defined Application page and [edit the application segment](https://help.zscaler.com/zpa/editing-application-segments) to adjust the domain and related ports. Protocol Discovery allows AppProtection to be enabled for a domain with a single port or for all of the TCP or UDP ports configured with the application segment. If you have successfully enabled inspection for only one domain, when you refresh the page, if you click the same domain but with a different port than the one that you inspected, you are prompted to enable inspection on all the ports for that domain.

See image.

### Inspect Protocols

To inspect at the protocol level:

1. Click one of the listed protocols.
2. When you have clicked a protocol, a pop-up message appears to confirm if you want to enable inspection for that specific protocol of the application segment displayed. If you want to continue, click **Yes**. A confirmation message appears notifying you that you have successfully updated the application segment.

See image.

1. After inspection has been enabled, the items that have been selected for inspection are removed from the widget.

[Image: The Enable AppProtection window on the Protocol Discovery Dashboard page]

[Image: Info window to inspect all the domains in the application segment selected]

[Image: Info window to inspect the domain selected]

[Image: Info window to inspect all the domains in the protocol selected]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-quarterly-business-review-reports","lastmod":"2026-04-21T16:15Z","nid":"1519176"} -->
## Viewing Quarterly Business Review Reports

- Source: https://help.zscaler.com/zpa/viewing-quarterly-business-review-reports
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Quarterly Business Review Reports
- Last modified: 2026-04-21T16:15Z
- Summary: Descriptions of individual sections within a Private Access (ZPA) Quarterly Business Review report.

Quarterly Business Review (QBR) reports are available for download to all users. The reports provide extensive insight into emerging traffic trends based on private application usage and the types of threats Zscaler blocks to protect your network.

A new QBR report is generated on the first weekend of every month. However, if the first weekend falls on the first or second day of the month, then the report is generated on the following weekend. The QBR reports are securely stored as a PowerPoint file in the Zscaler cloud. The QBR reports are displayed for the last 3 years, and up to 12 reports are stored.

Go to **Logs** > **Insights** > **Usage** > **Quarterly Business Reports** to view a list of downloadable quarterly business reports based on your organization's private application usage.

Each report provides the following information:

- **Highlights of Zscaler in Your Environment**: An architectural overview of your internet, SaaS, and private app traffic. Additionally, it provides an overview of your end users' digital experience data.
- **Global Traffic Distribution**: A geospatial distribution of your users around the world shows the number of users and the traffic percentage originating from each location.
- **Quarterly Snapshot**: The growth in your organization's applications (FQDNs), configured app segments, app segments for browser-related access, and transaction count between the last and current quarter.
- **ZPA Secures User Access to Private Applications**: Displays the quarterly overview of the most used application segments across users, the number of application segments with traffic usage, and the percentage growth.
- **Most Used Application Segments**: Displays the quarterly overview of the top 10 application segments and data transfer (in TBytes) across your organization.
- **Where are my apps hosted?**: A geospatial distribution of your users around the world by showing the number of users, number of App Connectors, and traffic percentage originating from each location. If hosting provider details are not determined, **Unknown** appears next to the name of the private applications in the Bar and Map views. For example, if the provider and region can't be found, but the app's country is Australia, you see **Unknown, Unknown, AU**. For accurate location tracking regarding data center environments, edit the App Connector group, set Hosting Detection to Manual, and then select or enter host data center information. To learn more, see [Configuring App Connectors](https://help.zscaler.com/zpa/configuring-connectors) and [Editing App Connector Groups](https://help.zscaler.com/zpa/editing-connector-groups).
- **Reduce Lateral Threat Movement with User-to-App-Segmentation**: The growth of your private applications, configured application segments, and configured access policies to build granular privileged access.

## Downloading QBR Reports

To download and view the reports:

1. Go to **Logs** > **Insights** > **Usage** > **Quarterly Business Reports**.
2. Click the **Download** icon to download a specific report.

[Image: Download QBR]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-restore-activities-and-restore-reports","lastmod":"2026-09-17T13:52Z","nid":"1485871"} -->
## Viewing Restore Activities and Restore Reports

- Source: https://help.zscaler.com/zpa/viewing-restore-activities-and-restore-reports
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Administration > Backup & Restore > Viewing Restore Activities and Restore Reports
- Last modified: 2026-09-17T13:52Z
- Summary: How to view restore activities and restore reports for the backup and restore feature in the Zscaler Admin Console.

After a [backup](https://help.zscaler.com/zpa/about-backup-and-restore) is created [manually](https://help.zscaler.com/zpa/adding-backups-manually) or [automatically](https://help.zscaler.com/zpa/creating-scheduled-backup-configurations), you can view a list of restore activities, or you can view the post-restore reports for the particular backup.

## Accessing a List of Restore Activities for a Backup

To access a list of restore activities for a particular backup:

1. Go to **Administration**>**Back Up and Restore**>**Private Access**.
2. In the table, locate the backup you want to view the restore activities and restore reports for, and click on the individual backup name in the **Backup Name** column. The **Restore Activities**drawer appears. See image.
3. In the **Restore Activities** drawer, you can:
  - Click the **Restore**icon () to open the **View Report and Restore** drawer. In the **View Report and Restore** drawer, you can [view the inconsistency reports before restoring a backup](https://help.zscaler.com/zpa/restoring-policies-and-configurations-backup).
  - View the list of restore activities. Filter the data in the table with your selections, and then click **Apply**. For each restore activity, you can see:
    - **Admin ID**: The admin ID of the admin who restored the backup.
    - **Initiated At**: The time when the backup is restored.
    - **Status**: The status of the restore (**Completed**, **Failure**, **In Progress**).
  - Click the **View Restore Report** icon () to view the post-restore reports of the selected backup. The **Restore Report** drawer appears.

[Image: Viewing the Restore Activities drawer]

## Viewing the Post-Restore Reports for a Backup

In the **Restore Report** drawer, you can view the post-restore report view of the backup if the configurations were modified. You can click **Collapse All** to collapse all configurations, click on an individual configuration to see more information, or click the **Download**icon to download the full report as a CSV file. In the **Restore Report** table, you can see:

- **Configuration**: The list of configurations for the backup. Any unchanged configurations are not listed in the table.
- **Status**: The status of the configurations after the backup is restored.

See image.

[Image: Viewing the Restore Reports drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-source-ip-anchoring-dashboard","lastmod":"2026-06-29T10:04Z","nid":"1485891"} -->
## Viewing the Source IP Anchoring Dashboard

- Source: https://help.zscaler.com/zpa/viewing-source-ip-anchoring-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Viewing the Source IP Anchoring Dashboard
- Last modified: 2026-06-29T10:04Z
- Summary: Information on the Source IP Anchoring dashboard and widgets accessible within the Zscaler Admin Console.

The Source IP Anchoring dashboard provides information about your organization's Source IP Anchoring connections. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Source IP Anchoring.

See image.

[Image: Source IP Anchoring dashboard within the Zscaler Admin Console]

## Dashboard Tools

The Source IP Anchoring dashboard displays the following information and functionality:

- **Time Range Filter**: View Source IP Anchoring data over a period between **7 Days** to **6 Months**, or you can select **Custom Range**. This filter applies to all widgets on the dashboard. By default, the dashboard displays information for events that occurred in the last 7 days.
- **Refresh icon**: Refresh the dashboard to reflect the most current information.
- **Average Number of Users**: View real-time information about the number of users per day or month for the selected time period.
- **Average Total Bandwidth Consumption**: View real-time information about the average bandwidth consumed per day or month for the selected time period.

[Image: Source IP Anchoring dashboard tools]

## Widgets

The Source IP Anchoring dashboard provides the following widgets:

- Bandwidth Consumption
- Active Users
- Top Application Segments by Bandwidth

The widget displays total and average per user bandwidth consumption for the selected time interval.

[Image: Bandwidth Consumption widget]

- Hover over a date to view:
  - **Total**: The total bandwidth consumed for Source IP Anchoring applications.
  - **Average per user**: The average bandwidth consumed per user.
- Click the **Download**icon () to export a file (.csv) containing information on the total bandwidth consumed, and average bandwidth consumed per user for the time interval.

The widget displays the total unique Source IP Anchoring users for the selected time interval.

[Image: Active Users widget]

- Hover over a date to view the number of active Source IP Anchoring users by date.
- Click the **Download**icon () to export a file (.csv) containing information on the number of active Source IP Anchoring users for the time interval.

The widget displays the top application segments by bandwidth for the selected time interval.

[Image: Top Application Segments by Bandwidth widget]

- Hover over a section of the pie or horizontal bar chart to see the applications. Click **View Applications** to view the top five applications for the selected application segment.
- Click the **Pie**or **Bar**icon () to display data in pie or bar format.
- Click the **Download**icon () to export a file (.csv) containing information on top application segments by bandwidth.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-users-dashboard","lastmod":"2026-09-20T07:06Z","nid":"1483761"} -->
## Viewing the Users Dashboard

- Source: https://help.zscaler.com/zpa/viewing-users-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Viewing the Users Dashboard
- Last modified: 2026-09-20T07:06Z
- Summary: Information on the Users dashboard and widgets accessible within the Zscaler Admin Console.

The Users dashboard provides information about user activity in your organization. To view the dashboard in the Zscaler Admin Console, go to Analytics > Switch to Existing Reports > Private Applications > Users.

See image.

[Image: Users Dashboard within the Zscaler Admin Console]

## Dashboard Tools

The Users dashboard displays the following information and functionality:

- **Time Range Filter**: View user data over a period between **1 Hour** to **14 Days**, or you can select **Custom Range**. If you use a **Custom Range**, the start and end date must be within the last 14 days. The end time can be configured to the selected time in hours and minutes. By default, the dashboard displays information for events that occurred in the last hour. This filter applies to all widgets on the dashboard except **Current Connected Users**, which displays users for up to the last 30 minutes.

Log information in the dashboard is limited to 14 days. For longer access to the logs, use the [Log Streaming Service (LSS)](https://help.zscaler.com/zpa/about-log-streaming-service).

- **Federated By**: Filter data in the dashboard by users that are federated by your tenant (i.e., **My Tenant**), or by users that are federated by your partner (i.e., **Partner**). If the **Federated By** filter is set to **Partner**, then the **Partner**filter and a valid value must be applied in addition to the **Federated By** filter. To learn more, see [Understanding Business-to-Business (B2B) Federation](https://help.zscaler.com/zpa/understanding-business-business-b2b-federation).
- **Partner**: Filter data in the dashboard by the federated partner. The **Partner** filter must be applied with the**Federated By** filter, and is only visible when the **Federated By** filter is set to **Partner**. To learn more, see [About Federated Partners](https://help.zscaler.com/zpa/about-federated-partners).
- **Refresh Icon**: Refresh the dashboard to reflect the most current information.
- **Recent Users**: View the total number for this category at the top of the page, and then view its details in the widget below. This number is based on the users that accessed applications, and it includes all successful and unsuccessful transactions.
- **Current Connected**: View the total number for this category at the top of the page, and then view its details in the widget below. This number is based on the users who have logged into the Zscaler Client Connector and who are authenticated and connected to Private Access.
- **Top Policy Blocks**: View the total numbers for this category at the top of the page, and then view its details in the widget below.
- **Chart Selection**: Select the charts you want to display or hide on the dashboard. A minimum of 3 charts and a maximum of 6 charts can be selected.

See image.

[Image: Accessing the Time Period filter, Refresh icon, and totals for Recent Users, Connected Users, and Policy Blocks within the Zscaler Admin Console]

## Widgets

The Users dashboard provides the following widgets:

- Recent Users
- Current Connected Users
- Users Blocked by Policies
- Top Users by Applications
- Top Users by Bandwidth
- Top Policies by Blocked Users
- Top Users by Tunnel Count

If you are using the [Log Streaming Service](https://help.zscaler.com/zpa/about-log-streaming-service), the Users dashboard includes information for a ZPA LSS Client user. This user represents the LSS service, not an actual user. Also, each log receiver is displayed as an application, to reflect the data coming in from the service. To learn more, including how to stop the LSS service from streaming ZPA LSS Client logs, see [Configuring a Log Receiver](https://help.zscaler.com/zpa/configuring-log-receiver#Step2).

This widget displays real-time information about the total number of users that requested applications and successful machine tunnel connections over the selected time frame. The widget always uses the current time for its end time even if a custom time range with a different end time is selected.

For users accessing applications using Browser Access through an unauthenticated HTTP preflight OPTIONS request, the username will appear as **ZPA BA Unauthenticated**. To learn more, see [Configuring Application Segments](https://help.zscaler.com/zpa/configuring-defined-application-segments#BrowserAccess_NewAppSeg).

[Image: Users Dashboard with Recent Users widget]

- Click the navigation tabs to view a list of users or machine tunnels.
- Click on a user or machine tunnel to view more details in **Diagnostics**.
- Click the **Download** icon ([Image: Download icon within the ZPA Admin Portal]) to export a file (.csv) containing information on the users that accessed applications or successful machine tunnel connections with Private Access for the selected time frame (i.e., timestamp (in UTC) and username).

This widget displays real-time information of users and machine tunnels currently connected to Private Access.

[Image: Users Dashboard with Current Connected Users widget]

- Click on the navigation tabs to view a list of users or machine tunnels.
- Hover over a user or machine tunnel to view the following:
  - **Logged In Time**:The time the user logged in.
  - **IP Address**:The IP address of the user's device.
  - **User Location**:The location the user is connecting from.
- Click on a user or machine tunnel to view more details in **Diagnostics**.
- Click the **Download** icon ([Image: Download icon within the ZPA Admin Portal]) to export a file (.csv) containing information on the users or machine tunnels that are currently accessing applications with Private Access for up to the last 30 minutes.

This widget displays users that have been blocked by your organization's configured policies in the selected time frame.

[Image: Users Blocked by Policies widget]

- Hover over a user to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP and not the username entered in Zscaler Client Connector.
  - **# of Policy Blocks**: The number of times the user was blocked by a policy in the selected time frame and the percentage that number represents of the total number of times users were blocked by policies, as shown in the widget.
- Click on a user to view more details in **Diagnostics**.

This widget displays the top 10 users of applications in the selected time frame. The percentage of total transactions for the top users by applications appears in the top-right corner.

[Image: Top Users by Application widget]

- Hover over a user to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP and not the username entered in Zscaler Client Connector.
  - **# of App(s)**: The number of applications accessed by the user in the selected time frame and the percentage that amount represents of the total number of applications the top 10 users accessed, as shown in the widget.
- Click on a user to view more details in **Diagnostics**.

This widget displays the top 10 consumers of bandwidth in the selected time frame. The percentage of total transactions for the top users by bandwidth appears in the top-right corner.

[Image: Top Users by Bandwidth widget]

- Hover over a user to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP and not the username entered in Zscaler Client Connector.
  - **Bandwidth**: The amount of bandwidth consumed by the user in the selected time frame and the percentage that number represents of the total bandwidth consumed by the ten users shown in the widget.
- Click on a user to view more details in **Diagnostics**.

This widget displays the top 10 policies that blocked users in the selected time frame.

[Image: Top Policies By Blocked Users widget]

- Hover over a policy to view the following:
  - **Name**: The policy name.
  - **# of Policy Blocks**: The number of users the policy blocked in the selected time frame and the percentage that number represents of the total number of users blocked by the ten policies shown in the widget.
- Click on a policy to view more details in **Diagnostics**.

This widget displays the top users by tunnel count in the selected time frame. The percentage of total transactions for the top users by tunnel count appears in the top-right corner.

[Image: Top Users by Tunnel Count widget on the Users dashboard]

- Hover over a policy to view the following:
  - **Name**: The username. This is the NameID in the SAML assertion from the IdP, and not the username entered in Zscaler Client Connector.
  - **Number of MTunnel(s)**: The number of tunnel counts accessed by the user in the selected time frame, and the percentage of that amount that represents the total number of tunnel counts accessed by the users shown in the widget.
- Click a policy to view more details in **Diagnostics**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-vpn-service-edges-dashboard","lastmod":"2026-09-03T12:13Z","nid":"1542720"} -->
## Viewing the VPN Service Edges Dashboard

- Source: https://help.zscaler.com/zpa/viewing-vpn-service-edges-dashboard
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > VPN (for Legacy Apps) > Dashboard & Diagnostics > Viewing the VPN Service Edges Dashboard
- Last modified: 2026-09-03T12:13Z
- Summary: Information about the VPN Service Edge dashboard and widgets available in the Zscaler Admin Console.

The VPN Service Edges Dashboard provides information about the VPN Service Edges for your organization. To view the dashboard in the Zscaler Admin Console, go to Private Access >VPN (for Legacy Apps) > Dashboard > VPN Service Edge.

The dashboard displays a series of interactive timeseries charts. These charts show how data changes over a selected period and help you identify trends, patterns, and anomalies in performance and usage. You can hover over any point in a chart to see the specific value at that time.

[Image: Learning about the widgets on the VPN service edges dashboard]

## Dashboard Tools

The VPN Service Edges Dashboard displays the following information and functionality:

- **VPN Service Edge selection**: Select the VPN Service Edge that you want to view data for from the drop-down menu.
- **Time Period Filter**: View VPN Service Edge data over a period between 1 hour to 14 days, or you can select **Custom**. If you use a custom range, the start and end dates must be within the last 14 days. You can adjust the time in hours and minutes for the start and end dates. This filter applies to all widgets on the dashboard. By default, the dashboard displays information about events that occurred in the last hour. Due to how data is aggregated for different time period filters, the same point in time in an Activity Monitor widget might show slightly different values depending on the selected time. For example, the data with the 1 Hour time period filter at 3:00 PM might not match the data with the 14 Days time period filter for the same date at 3:00 PM.
- **Refresh icon**: Refresh the dashboard to adjust the time period filter to reflect the most current information.

## Client Connectors Widgets

The Client Connectors widgets provide a real-time overview of user device connectivity and data traffic.

The following widgets are available:

- **Total Number of Client Connectors**: Displays the total count of individual Zscaler Client Connectors that are currently connected to the VPN Service Edge.
- **Bytes Received from Client Connectors**: Displays the volume of data traffic received from Zscaler Client Connectors to monitor inbound traffic from users.
- **Bytes Sent to Client Connectors**: Displays the volume of data traffic sent to Zscaler Client Connectors to monitor outbound traffic to users.

## Network Connectors Widgets

The Network Connectors widgets show traffic from your private network locations. You can use these widgets to monitor traffic loads and plan ahead for potential network issues. This information helps you decide when to add Network Connectors for proper scaling and ensures service reliability.

The following widgets are available:

- **Total Number of Network Connectors**: Displays the total count of Network Connectors currently connected to the VPN Service Edge.
- **Bytes Received from Network Connectors**: Displays the throughput of data received from Network Connectors, showing traffic from your internal networks.
- **Bytes Sent to Network Connectors**: Displays the throughput of data sent to Network Connectors, showing traffic directed towards your internal networks.

## VPN Service Edges Widgets

The VPN Service Edges widgets provide key performance indicators and overall health metrics for VPN Service Edges.

The following widgets are available:

- **Total Number of VPN Service Edges**: Displays the total count of VPN Service Edges deployed within your organization.
- **Bytes Received from VPN Service Edges**: Displays the total volume of inbound data processed by the VPN Service Edges from all sources.
- **Bytes Sent to VPN Service Edges**: Displays the total volume of outbound data sent from the VPN Service Edges to all destinations.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/viewing-where-your-apps-are-served","lastmod":"2026-04-21T16:21Z","nid":"1529636"} -->
## Viewing Where Your Apps Are Served

- Source: https://help.zscaler.com/zpa/viewing-where-your-apps-are-served
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Dashboard & Diagnostics > Applications & Users Monitoring > Applications & Users Insights > Viewing Where Your Apps Are Served
- Last modified: 2026-04-21T16:21Z
- Summary: How to view the Where are my apps being served from? insights.

The Where are my apps being served from? page provides a view of hosting providers and analytical details of the App Connectors that have been serving your applications for the last quarter. You can view and understand where your applications are hosted because App Connectors act as a good proxy for your private applications in certain cases. While observing and understanding trends, you can filter and sort to customize your view to focus on details based on your selection.

The report is generated on the same schedule as the [quarterly business reports](https://help.zscaler.com/zpa/viewing-quarterly-business-review-reports).

If hosting provider details are not determined, **Unknown** appears next to the name of the private applications in the Bar and Map views. For example, if the provider and region can't be found, but the app's country is Australia, you see **Unknown, Unknown, AU**.
For accurate location tracking regarding data center environments, edit the App Connector group, set Hosting Detection to Manual, and then select or enter host data center information. To learn more, see [Configuring App Connectors](https://help.zscaler.com/zpa/configuring-connectors) and [Editing App Connector Groups](https://help.zscaler.com/zpa/editing-connector-groups).

## Bar View

The Bar view provides a visual and analytical representation of the number of FQDNs, application segments, users, and transactions using horizontal bar graphs.

[Image: Bar View displays bar graphs of private applications]

In the Bar view, you can:

- Sort by **FQDNs**, **App Segments**, **Users**, or **Transactions** in ascending or descending order to view private applications. See image.
- Filter private applications based upon selection (**AWS**, **Azure**, **Google Cloud**, **Data Center**). At least one private application must be selected to display data. See image.
- Switch between the Map and Bar view.
- Hover over a bar to view the number of FQDNs, App Segments, Users, Transactions, and Location details. See image.

If hosting provider details are not determined, **Unknown** appears next to the name of the private applications in Bar view. For accurate location tracking regarding data center environments, edit the App Connector group, set **Hosting Detection** to **Manual**, and then select or enter host data center information. To learn more, see [Configuring App Connectors](https://help.zscaler.com/zpa/configuring-connectors) and [Editing App Connector Groups](https://help.zscaler.com/zpa/editing-connector-groups).

## Map View

The Map view displays the geographic locations of all cloud providers based upon your selection. Zscaler uses the user device's location service to determine the general vicinity of the private application's location.

[Image: View insights on the Where are my apps being served from? page]

In the Map view, you can:

- Sort by **FQDNs**, **App Segments**, **Users**, or **Transactions** in ascending or descending order to view a list of private applications. Click **Prev** or **Next** to navigate between items. The map automatically adjusts its location based on the items selected. See image.
- Filter private applications based upon selection (**AWS**, **Azure**, **Google Cloud**, **Data Center**). See image.
- Switch between the Map and Bar view.
- Zoom in, zoom out, center your position, or reset your view to review areas of interest. You can zoom in and out with your mouse's scroll wheel. See image.
- Hover over a marked location to view the number of FQDNs, application segments, users, transactions, and location details. See image.

[Image: Sort the list of private applications in ascending or descending order or navigate through the list.]

[Image: Filter based on private applications]

[Image: Use the Zoom menu to interact with map]

[Image: Hover over a location to view more details]

[Image: Use Sort By to select items in ascending or descending order]

[Image: Select filters to specify private applications to view]

[Image: Hover over a bar to view details of the private application]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/vpn-redundancy-deployment-guide","lastmod":"2026-08-07T07:38Z","nid":"1535291"} -->
## VPN Redundancy Deployment Guide

- Source: https://help.zscaler.com/zpa/vpn-redundancy-deployment-guide
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > VPN (for Legacy Apps) > VPN Redundancy Deployment Guide
- Last modified: 2026-08-07T07:38Z
- Summary: Information on how to enable VPN redundancy for your Network Connectors.

This guide explains how to configure VPN redundancy for your organization's traffic using Network Connectors that support redundancy within a Network Connector group. Network Connectors that support redundancy solve the following failure scenarios:

- A Network Connector link failure
- An internal link failure towards a customer router
- An external link failure towards the VPN Service Edge
- A complete Network Connector node failure
- A data center failure with traffic being rerouted towards another data center

With the help of Border Gateway Protocol (BGP), you can scale your network with Equal-Cost Multi-Path (ECMP) to load balance traffic to the Network Connectors from the VPN Service Edges, and the traffic from the routers to the Network Connectors.

If you want to upgrade your existing VPN (for Legacy Apps) configuration with redundancy capability, see [Replacing or Migrating Existing Network Connectors with Network Connectors that Support Redundancy](https://help.zscaler.com/zpa/replacing-or-migrating-network-connectors-support-redundancy).

With a BGP router, you can configure redundancy using the following steps:

- Step 1: Add VPN Service Edges
- Step 2: Configure BGP for VPN Service Edges
- Step 3: Add External Routers
- Step 4: Add Network Connectors
- Step 5: Deploy Network Connectors by Platform
- Step 6: Configure BGP for Network Connectors
- Step 7: Add Network Segments
- Step 8: View the BGP Dashboard
- Step 9: Add Access Policies

To add a VPN Service Edge, see the following articles:

- [About VPN Service Edges](https://help.zscaler.com/zpa/about-vpn-service-edges)
- [Configuring VPN Service Edges](https://help.zscaler.com/zpa/configuring-vpn-service-edges)

To configure BGP for VPN Service Edges, see [Configuring Border Gateway Protocols for VPN Service Edges](https://help.zscaler.com/zpa/configuring-border-gateway-protocol-vpn-service-edges).

To add external routers, see the following articles:

- [About External Routers](https://help.zscaler.com/zpa/about-external-routers)
- [Configuring External Routers](https://help.zscaler.com/zpa/configuring-external-routers)

To add Network Connectors, see the following articles:

- [About Network Connectors](https://help.zscaler.com/zpa/about-network-connectors)
- [Configuring Network Connectors](https://help.zscaler.com/zpa/configuring-network-connectors)

To create Network Connectors using a provisioning key, see [About Network Connector Provisioning Keys](https://help.zscaler.com/zpa/about-network-connector-provisioning-keys).

Ensure the following settings for the Network Connector group when configuring Network Connectors:

- **Advertise LAN Segments**: Select **Disabled** to use BGP.
- **External Routers**: Select the routers you want to associate with the Network Connector group.
- **Keepalive**: Select the frequency and wait time for the keepalive message (in seconds) for BGP before a session times out.
- **Enable Multi**-**Hop**: Enable this option to allow BGP sessions to be established between Network Connectors and the external routers that are not directly connected on the same network subnet.
- **Interface**: Enter the interface name that has connectivity to the BGP router.

Ensure that the Manager software version for the new Network Connectors is 25.46.2 or later.

To deploy Network Connectors based on your platform, see [Network Connector Software by Platform](https://help.zscaler.com/zpa/network-connector-software-platform).

Ensure the following based on the platform:

- VMware
- AWS
- Azure

If you are installing Network Connectors without using the prebuilt virtual image from Zscaler, make sure you configure the Zscaler repository so the Network Connector can download and install the FRR package, which is essential for BGP to work. To learn more, see [Network Connector Deployment Guide for Linux](https://help.zscaler.com/zpa/network-connector-deployment-guide-linux).

- Assign a static private IP address.
- Use only one network interface.
- Use version VMware ESXi 7.0 Update 2 or later.

For the deployed Network Connector instance, ensure that the **Source / destination checking** option is disabled (**Actions**> **Networking**>**Change source / destination check**).

For the deployed Network Connector VMs, ensure that the **IP forwarding** option is enabled (**Settings > IP configurations**).

To configure BGP for Network Connectors, see [Configuring Border Gateway Protocols for Network Connectors](https://help.zscaler.com/zpa/configuring-border-gateway-protocol-network-connectors).

To configure network segments, see the following articles:

- [About Network Segments](https://help.zscaler.com/zpa/about-network-segments)
- [Configuring Network Segments](https://help.zscaler.com/zpa/configuring-network-segments)

The routes advertised by the BGP routers must match the IP subnets defined in the network segments for the BGP to work.

You can check the BGP states and statuses on the BGP Peers Dashboard. To learn more, see [Viewing the BGP Peers Dashboard](https://help.zscaler.com/zpa/viewing-bgp-peers-dashboard).

To add access polices, see the following articles:

- [About VPN Access Policy](https://help.zscaler.com/zpa/about-vpn-access-policy)
- [Configuring VPN Access Policies](https://help.zscaler.com/zpa/configuring-vpn-access-policies)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/what-is-microsegmentation","lastmod":"2026-09-04T12:18Z","nid":"1531937"} -->
## What Is Microsegmentation?

- Source: https://help.zscaler.com/zpa/what-is-microsegmentation
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Microsegmentation > What Is Microsegmentation?
- Last modified: 2026-09-04T12:18Z
- Summary: Information about Microsegmentation in the Zscaler Admin Console.

Zscaler Microsegmentation is a platform within the Zscaler Admin Console that provides the capability to visualize traffic flows within private applications and segment them on a fractional level, reducing the attack surface and preventing lateral movement of any threats on your network. It is a multi-tenant Software as a Service solution optimized for security, reliability, and scale, using a system divided into the Zscaler cloud and deployed agents. The Zscaler cloud and agents work together to collect and analyze application flow and telemetry data and also monitor the health of all managed systems.

Microsegmentation agents and Zscaler Client Connector are not compatible and should not be installed on the same host.

The deployed agents sit on Windows or Linux hosts in your environment and collect information about application activity. Agents are deployed to your servers, whether they are virtual or physical, cloud based, or in your data center. The agents are responsible for downloading the latest access policies from the Zscaler cloud and translating them to rules that are specific to local OS enforcement points (e.g., Windows Filtering Platform and Linux nftables). You can create [Agent Groups](https://help.zscaler.com/zpa/configuring-agent-groups), [Resource Groups](https://help.zscaler.com/zpa/configuring-resource-groups), and [AppZones](https://help.zscaler.com/zpa/configuring-appzones) that group together specific machines and their applications that you want to monitor for certain data flows.

The backend application is hosted and managed by the Zscaler cloud and is available across the US region. The data collection is localized to the region of choice of the administrator, and the data retention cycle is a 14-day rolling period. You can deploy the agent to resources in any region. The installed agents operate in continuous mode.

If you want Microsegmentation provisioned for your organization, contact your Zscaler Account team.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/what-zscaler-private-access","lastmod":"2026-06-01T08:36Z","nid":"1483511"} -->
## What Is Zscaler Private Access?

- Source: https://help.zscaler.com/zpa/what-zscaler-private-access
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > What Is Zscaler Private Access?
- Last modified: 2026-06-01T08:36Z
- Summary: Introductory information about Private Access and its cloud-based infrastructure.

The Private Access (ZPA) service enables organizations to provide access to internal applications and services while ensuring the security of their networks. Private Access is an easier-to-deploy, more cost-effective, and more secure alternative to VPNs. Unlike VPNs, which require users to connect to your network to access your enterprise applications, Private Access allows you to give users policy-based secure access only to the internal apps they need to get their work done. With Private Access, application access does not require network access.

While Private Access is for connecting users to an enterprise's internal applications, Internet & SaaS (ZIA) is for connecting users to public applications on the internet. To learn more about Internet & SaaS architecture, see [Understanding the Zscaler Cloud Architecture for Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture).

Additionally, Private Access decouples applications from the physical network so you can provide seamless connectivity to private internal applications and assets, whether they are in the cloud, the data center, or both. It also adjusts dynamically to network changes, so you can move your resources without impacting user access.

You can configure settings and policies in a central [Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console), which also features dashboards where you can see your [users](https://help.zscaler.com/zpa/about-users-dashboard) and the [apps](https://help.zscaler.com/zpa/viewing-applications-dashboard) they access, and [monitor the health of your servers and resources](https://help.zscaler.com/zpa/understanding-health-reporting). You can configure Private Access to automatically discover servers and applications when users request them, or you can configure them manually as well. You then define [policies](https://help.zscaler.com/zpa/policies) that specify which apps users or groups can use, and Private Access allows them to connect to those apps only. Private Access renders your applications invisible to all but the authorized users and unroutable to anyone.

Like all Zscaler offerings, the Private Access service is based on the Zero Trust Exchange (ZTE), so there is no requirement for additional hardware or upgrades to existing hardware.

To learn more, see the [Private Access Overview](https://www.zscaler.com/products/zscaler-private-access).

## Key Features and Benefits

Private Access offers the following key features and benefits:

- Seamless User Experience: Policy-driven connectivity that dynamically adjusts to network changes.
- Enhanced Security: Application-specific connectivity without ever bringing users on-net.
- Ease of Deployment: Does not require hardware or hardware upgrades.
- Instant Deployment and Discovery: Can automatically discover applications so you can easily build policies around them.
- Single Sign-On (SSO): Private Access is tied directly to your existing authentication infrastructure, leveraging SSO to further reduce complexity.
- Real-Time Visibility: Dashboards provide unparalleled visibility into your users and applications, and the health of your organization's applications and servers

## How Private Access Works

Private Access uses Zscaler's cloud-based, elastically scalable infrastructure to deliver seamless connectivity to your private internal applications and assets. Its key components are as follows:

- Zscaler Client Connector: Installed on your users' devices, Zscaler Client Connector connects to the Private Access cloud to enable granular, policy-based access to your organization's internal resource.

Zscaler Client Connector can also forward your users' traffic to the ZTE to secure their internet traffic as well. To learn more, see [What Is Zscaler Client Connector?](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)

- App Connectors: Lightweight virtual machines (VM) that are installed in the data centers that host your servers and applications. They connect to Public Service Edges or Private Service Edges only to provide users access to applications in your data center, and do not accept inbound connections. To learn more, see [About App Connectors](https://help.zscaler.com/zpa/about-connectors).
- Global Zscaler cloud: Stitches all components together. The Central Authority (CA) provides a central location for software updates as well as policy and configuration settings. The Public Service Edges or Private Service Edges enforce user policies and provide secure transport to the App Connectors.

Users install Zscaler Client Connector on their devices and can then log into an application using SAML 2.0-based SSO credentials. When users request access to an internal application, Zscaler Client Connector uses geolocation technology to locate the Public Service Edge or Private Service Edge closest to them. Zscaler Client Connector presents its certificate to the Public Service Edge or Private Service Edge to confirm its identity, and then establishes a secure tunnel to the Public Service Edge or Private Service Edge. The Public Service Edge or Private Service Edge then retrieves the user's policies from the CA, and depending on the internal application requested by the user, the Public Service Edge or Private Service Edge contacts the appropriate App Connector. The App Connector presents its certificate to the Public Service Edge or Private Service Edge, and when the Public Service Edge or Private Service Edge confirms the App Connector's identity, it allows the App Connector to connect to it.

When the connection is established between the user's device and the application, the traffic traversing the solution remains completely isolated.

Because Private Access is built on the premise of Zero Trust for your private applications, the traffic is isolated from Zscaler as well.

Private Access admins can view [dashboards](https://help.zscaler.com/zpa/dashboard-diagnostics) and [diagnostics](https://help.zscaler.com/zpa/dashboard-diagnostics) to filter for detailed information about users and applications, as well as monitor the health of their organization's applications, servers, and App Connectors.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zero-trust-access-private-apps-aws-zscaler-private-access","lastmod":"2026-09-02T08:00Z","nid":"1485076"} -->
## Zero Trust Access to Private Apps in AWS with Zscaler Private Access

- Source: https://help.zscaler.com/zpa/zero-trust-access-private-apps-aws-zscaler-private-access
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Reference Architecture > Zero Trust Access to Private Apps in AWS with Zscaler Private Access
- Last modified: 2026-09-02T08:00Z
- Summary: The Zero Trust Access to Private Apps in Amazon Web Services (AWS) with Zscaler Private Access (ZPA) reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Private Access (ZPA) is a part of the Zscaler Zero Trust Exchange platform. ZPA provides secure access, based on a zero-trust framework, to private applications—including remote access to internal applications running on Amazon Web Services (AWS). With ZPA, applications are never exposed to the internet, making them completely invisible to unauthorized users.

[Download English PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-aws-zscaler-private-access/zero-trust-access-private-apps-aws-zpa.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-aws-zscaler-private-access/zscaler-architecture-guide-ZPA-R9-15dec21_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access","lastmod":"2022-11-04T13:48Z","nid":"1485486"} -->
## Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access

- Source: https://help.zscaler.com/zpa/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Reference Architecture > Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access
- Last modified: 2022-11-04T13:48Z
- Summary: The Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access (ZPA) reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Private Access (ZPA) is a part of the Zscaler Zero Trust Exchange platform. ZPA provides secure access, based on a zero-trust framework, to private applications—including remote access to internal applications running on Microsoft Azure. With ZPA, applications are never exposed to the internet, making them completely invisible to unauthorized users.

[Zero Trust Access to Private Apps in Microsoft Azure with Zscaler Private Access: Reference Architecture Guide](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access/Zero-Trust-Access-to-Private-Apps-in-Azure-with-Zscaler-Private-Access.pdf) [Download PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-access-private-apps-microsoft-azure-zscaler-private-access/Zero-Trust-Access-to-Private-Apps-in-Azure-with-Zscaler-Private-Access.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zero-trust-user-to-app-segmentation-zpa","lastmod":"2026-09-02T08:01Z","nid":"1485376"} -->
## Zero Trust User-to-App Segmentation with ZPA

- Source: https://help.zscaler.com/zpa/zero-trust-user-to-app-segmentation-zpa
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Reference Architecture > Zero Trust User-to-App Segmentation with ZPA
- Last modified: 2026-09-02T08:01Z
- Summary: The Zero Trust User-to-App Segmentation with Zscaler Private Access (ZPA) reference architecture guide that steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zero trust has become a popular model for secure user access to applications and resources. Moving away from the traditional VPN style of network access, zero trust is an approach that focuses on granular user-to-app segmentation. Zscaler Private Access (ZPA) gives users access to applications without requiring users to share a network context with the applications. Where VPNs assign a user an IP address and place the user on the network, ZPA enables users to connect only to allowed applications, with no access to adjacent applications or systems. The user can be anywhere, and the application can be hosted in any location. Granular context-based policy can control application visibility and access by end users.

[Download English PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-user-to-app-segmentation-zpa/zero-trust-user-to-app-segmentation-with-zpa.pdf)

[Download Japanese PDF](https://help.zscaler.com/downloads/zpa/reference-architecture/zero-trust-user-to-app-segmentation-zpa/zero-trust-user-to-app-segmentation-zpa_ja-JP.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-app-connector-software-by-platform","lastmod":"2026-09-11T08:00Z","nid":"1485956"} -->
## App Connector Software by Platform

- Source: https://help.zscaler.com/zpa/zpa-app-connector-software-by-platform
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > App Connector Management > App Connector Deployment Guides for Supported Platforms > App Connector Software by Platform
- Last modified: 2026-09-11T08:00Z
- Summary: The current App Connector software downloads by platform.

App Connectors are supported on [many different platforms](https://help.zscaler.com/zpa/app-connector-management/app-connector-deployment-guides-supported-platforms). Each supported platform has an App Connector image you can use to deploy App Connectors on that platform. To learn more, see [App Connector Deployment Guides for Supported Platforms](https://help.zscaler.com/zpa/app-connector-management/app-connector-deployment-guides-supported-platforms) for detailed deployment instructions.

The following platforms support App Connector software packages. Where applicable, see the download links for images, public keys, and checksums.

| Platform | Software |
| --- | --- |
| Colocation / Data Centers |  |
| [Nutanix AHV](https://help.zscaler.com/zpa/app-connector-deployment-guide-nutanix-ahv#deploy) | [STIG OVA](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova) [STIG OVA checksum](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova.sha256sum) |
| [VMware](https://help.zscaler.com/zpa/connector-deployment-vmware-appliance-vmware-vcenter) | [STIG OVA](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova) [STIG OVA checksum](https://dist.private.zscaler.com/vms/VMware/2026.07/zpa-connector-el9-2026.07.ova.sha256sum) |
| Clouds |  |
| [Amazon Web Services (AWS)](https://help.zscaler.com/zpa/connector-deployment-guide-amazon-web-services#deploy) | [Private Access - AWS Marketplace (STIG Image)](https://aws.amazon.com/marketplace/pp/prodview-cvvqe5hxw2bku?sr=0-1&ref_=beagle&applicationId=AWSMPContessa) |
| [Google Cloud Platform (GCP)](https://help.zscaler.com/zpa/app-connector-deployment-guide-google-cloud-platform) | [Private Access - GCP Marketplace (STIG Image)](https://console.cloud.google.com/marketplace/product/zpa-gcp-marketplace/zscaler-private-access-connector) |
| [Microsoft Azure](https://help.zscaler.com/zpa/connector-deployment-guide-microsoft-azure#Deployment) | [Private Access - Azure Marketplace (STIG Image)](https://azuremarketplace.microsoft.com/en-us/marketplace/apps/zscaler.zscaler-private-access?tab=overview) |
| Containers |  |
| [Docker](https://help.zscaler.com/zpa/app-connector-deployment-guide-docker) | [Docker Hub](https://hub.docker.com/r/zscaler/zpa-connector/tags) |
| [Kubernetes](https://help.zscaler.com/zpa/app-connector-deployment-guide-kubernetes) | To learn more, see the [App Connector Deployment Guide for Kubernetes](https://help.zscaler.com/zpa/app-connector-deployment-guide-kubernetes). |
| [OpenShift](https://help.zscaler.com/zpa/app-connector-deployment-guide-openshift#deploy) | A Helm Chart must be installed in order to deploy an App Connector on OpenShift. To learn more, see the [App Connector Deployment Guide for OpenShift](https://help.zscaler.com/zpa/app-connector-deployment-guide-openshift#get-helm-chart). |
| Linux Operating Systems |  |
| [App Connector Deployment Guide for Linux](https://help.zscaler.com/zpa/connector-deployment-guide-centos-oracle-and-redhat#Deployment) | The following RPM packages are supported for RHEL App Connector deployments: RHEL 8-based: [RPM package](https://yum.private.zscaler.com/yum/el8/zpa-connector-26.56.9-1.el8.x86_64.rpm); [GPG public key](https://yum.private.zscaler.com/yum/el8/gpg)RHEL 9-based: [RPM package](https://yum.private.zscaler.com/yum/el9/zpa-connector-26.56.9-1.el9.x86_64.rpm); [GPG public key](https://yum.private.zscaler.com/yum/el9/gpg)You must have the RHEL operating system deployed and running. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-appliance-physical-port-mapping","lastmod":"2026-09-03T10:55Z","nid":"1540962"} -->
## ZPA Appliance Physical Port Mapping

- Source: https://help.zscaler.com/zpa/zpa-appliance-physical-port-mapping
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > ZPA Appliance > ZPA Appliance Physical Port Mapping
- Last modified: 2026-09-03T10:55Z
- Summary: A description of the physical ports on the ZPA hardware appliance, and its interface, port types, and roles.

This article depicts the physical ports on the ZPA hardware appliance and identifies its interface names, port types, and roles. You can configure the ports using Linux commands from the command-line interface.

The following image and table description describe the physical port mapping on the ZS-ZPA-APPL-8010 and ZS-ZPA-PSE-8010 appliances.

[Image: ZS-ZPA-APPL-8010 Appliance physical port diagram that identifies the console port and 18 interface ports]

| Port | Interface | Port Type | Speed | Role |
| --- | --- | --- | --- | --- |
| C | Console | RJ45 | 1GbE | Serial console |
| 1 | GE1/eno1 | RJ45 | 1GbE | LAN |
| 2 | GE2/enp1s0 | RJ45 | 1GbE | LAN |
| 3 | GE3/ens84f3 | RJ45 | 1GbE | LAN |
| 4 | GE4/ens84f2 | RJ45 | 1GbE | LAN |
| 5 | GE5/ens84f1 | RJ45 | 1GbE | LAN |
| 6 | GE6/ens84f0 | RJ45 | 1GbE | LAN |
| 7 | XE7/eno4 | SFP+ | 10G | LAN (default traffic port) |
| 8 | XE8/eno5 | SFP+ | 10G | LAN |
| 9 | XE9/eno2 | SFP+ | 10G | LAN |
| 10 | XE10/eno3 | SFP+ | 10G | LAN |
| 11 | GE11/ens83f0 | RJ45 | 1GbE | LAN |
| 12 | GE12/ens83f1 | RJ45 | 1GbE | LAN |
| 13 | GE13/ens83f2 | RJ45 | 1GbE | LAN |
| 14 | GE14/ens83f3 | RJ45 | 1GbE | LAN |
| 15 | XE15/ens5f3 | Optional SFP+ | 10G | LAN |
| 16 | XE16/ens5f2 | Optional SFP+ | 10G | LAN |
| 17 | XE17/ens5f1 | Optional SFP+ | 10G | LAN |
| 18 | XE18/ens5f0 | Optional SFP+ | 10G | LAN |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-appliance-wall-and-rack-mount-instruction-manual","lastmod":"2026-06-29T07:06Z","nid":"1540961"} -->
## ZPA Appliance Wall and Rack Mount Instruction Manual

- Source: https://help.zscaler.com/zpa/zpa-appliance-wall-and-rack-mount-instruction-manual
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > ZPA Appliance > ZPA Appliance Wall and Rack Mount Instruction Manual
- Last modified: 2026-06-29T07:06Z
- Summary: Instructions for rack mounting a ZPA hardware appliance.

This article includes instructions on mounting your ZPA hardware appliance.

## Rack Mount

Mounting your ZPA appliance on a rack or wall optimizes your workspace and makes the appliance more accessible for installation and deployment. It also protects the appliance from any external damage and helps streamline the organization of cables and power adapters. Additionally, rack or wall mounting helps ensure proper airflow, preventing overheating and dust accumulation caused by restricted ventilation. Proper airflow also maintains a cleaner environment, prolonging the lifespan of the appliance.

## ZPA Appliance

The ZPA appliance ships in two form factors: one hosting the App Connector and another hosting the Private Service Edge for Private Access (ZPA).

- ZS-ZPA-APPL-8010-APPC - App Connector appliance
- ZS-ZPA-APPL-8010-PSE - Private Service Edge appliance

## Instruction Manual

The ZPA appliance is a 1U, 19-inch rack-mountable form factor. This article serves as a comprehensive hardware installation manual for the ZS-ZPA-APPL-8010-APPC / ZS-ZPA-APPL-8010-PSE. These instructions are structured to assist IT personnel with the physical mounting, cabling, and initial power on of the appliance. This guide solely focuses on the hardware installation process and doesn’t cover software configuration. For software configuration, see [Deploying App Connector and Private Service Edge ZPA Appliances](https://help.zscaler.com/zpa/deploying-app-connector-private-service-edge-zpa-appliances).

- ZS-ZPA-APPL-8010 Specifications
- Package Contents
- Rack Mount the Appliance

The ZPA appliance has a weight of 18.96lbs (8.6kg), and its total packaged weight is 33lbs (15kg).

The following table provides environmental and power specifications for ZS-ZPA-APPL-8010:

| ZS-ZPA-APPL-8010 Specifications |  |
| --- | --- |
| **Environmental Specifications** |  |
| Operating Temperature | 32 to 104°F (0 to 40°C) |
| Non-Operating Temperature | -4 to 150°F (-20 to 65°C) |
| Relative Humidity | 5 to 90% (operating); 5 to 95% (non-operating) |
| Altitude | 5,000 feet (1,524 m) operating; 50,000 feet (15,240 m) non-operating |
| **Power Specifications** |  |
| Power Supply | Internal AC |
| Redundant Power Supply | 1 + 1 |
| Typical Power Consumption | Idle Mode: 107.8W Full Load Mode: 225.5W |
| Maximum Power Consumption | Redundant PSU: 300W Single PSU: 350W |
| Power Cord Rating | C14 10A |
| Input Range | Redundant PSU: AC 100–240V @ 50–60 Hz Single PSU: AC 100–240V @ 47–63Hz |
| Input Current | 5A (max) |
| Output Rating | 11.4 to 12.6V |

Before beginning the installation, verify that all necessary components are present in the packaging. The package includes the following items:

- 1x ZS-ZPA-APPL-8010-XX(APPC/PSE) unit
- 2x PSUs
- 2x RJ45 cable
- 1x USB serial console cable
- 4 x SFP+ FTLX8574D3BCL
- 6x A screws for attaching the brackets to the appliance See image.
- 6x B screws for mounting the appliance to the rack See image.
- 2x ear brackets See image.

To rack mount the appliance:

1. Align one of the ear brackets to screw holes on the side of the chassis, and secure the brackets using 3x A screws. See image.
2. Secure the other ear bracket to the other side of the appliance.
3. Insert the appliance into the rack with the ear brackets fixed on the posts using the B screws. See image. The method and parts required for mounting depend on whether a shelf or rail kit is used.
4. Connect the AC power cords to the appliance power supply unit. The appliance supports redundant power supply units (PSUs). Connect both cords to their respective power sources. A continuous beep indicates that the second power source is unplugged or faulty. To silence the beep, press the round button located near or between the power supplies, or connect the second power source.
5. Connect the WAN cable to the designated port of the appliance. The appliance has several ports, including 10x 1GbE RJ45 ports and 8x 10G SFP+ ports. To learn more, see [ZPA Appliance Physical Port Mapping](https://help.zscaler.com/zpa/zpa-appliance-physical-port-mapping). You can deploy 2 or more appliances in a network location and pair them into groups for load balancing. See image.

[Image: Set of A screws for ZS-ZPA-APPL-8010]

[Image: Set of B screws for ZS-ZPA-APPL-8010]

[Image: Ear brackets for ZS-ZPA-APPL-8010]

[Image: Ear bracket mounted to ZS-ZPA-APPL-8010]

[Image: ZS-ZPA-APPL-8010 mounted to a rack]

[Image: Illustration of ports in ZS-ZPA-APPL-8010]

## Troubleshooting Installation

This section helps to identify and resolve common installation issues.

- No Power
- Loud Beep
- No Link on Port

If the ZPA appliance does not boot:

1. Verify that the power outlet is active and functioning. A power LED light should be on. If not, it indicates a lack of power to the unit.
2. Check that the power supply is securely plugged into the unit and the power source.
3. Verify that the power LED light is on.

A continuous beeping sound indicates that only one power supply is active or that the power supply is defective. To stop the beeping, press the round button located near or between the power supplies. If the power supply is faulty, contact Zscaler Support to request a Return Merchandise Authorization (RMA).

If a network cable is connected, but no link light appears, there could be an issue with the cable, the port on the ZPA appliance, or the port on the connected switch.

1. Replace the cable with a new one to see if that resolves the issue.
2. If the problem persists, try connecting to a different port on the ZPA appliance to determine if the port is faulty.
3. Power cycle the device and confirm if the LED returns to a normal state.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-red-hat-enterprise-linux-9-migration","lastmod":"2026-09-17T17:34Z","nid":"1487746"} -->
## Red Hat Enterprise Linux 9 Migration for Private Service Edges

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-red-hat-enterprise-linux-9-migration
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Private Service Edge Management > Private Service Edge Deployment Guides for Supported Platforms > Red Hat Enterprise Linux 9 Migration for Private Service Edges
- Last modified: 2026-09-17T17:34Z
- Summary: Private Service Edge for Private Access (ZPA) migration steps for Red Hat Enterprise Linux 9.x.

This article provides migration instructions to replace CentOS 7 instances with Red Hat Enterprise Linux 9.x (RHEL 9.x). The enrollment and provisioning of new Private Service Edges for Private Access (ZPA) can be automated in a few steps using Terraform (IaC) or Container Orchestration to further simplify deployment.

To learn more about support for CentOS 7.x, see [End-of-Support for CentOS 7.x, RHEL 7.x, and Oracle Linux 7.x](https://help.zscaler.com/eos-eol/end-support-centos-7.x-rhel-7.x-and-oracle-linux-7.x).

Note the following requirements for successfully migrating from CentOS 7 to RHEL 9.x:

- Use a fresh install for all deployments.
- The EL9 repository must be used with RHEL 9 base OS. Older platform binaries (EL7/EL8) are not supported.
- Ensure that the`/opt/zscaler/var` folder is empty before install.
- Yum upgrades from EL7/EL8 to RHEL9 are not supported.
- Requires ESXi version 7.0 Update 2 or newer, including ESXi 8.x. To learn more about ESXi support, see [End-of-Support for VMware vSphere Hypervisor (ESXi) Version 5.5](https://help.zscaler.com/eos-eol/end-support-vmware-vsphere-hypervisor-esxi-version-5.5).

Use the following steps to migrate from CentOS 7 to RHEL 9.x:

1. Use a current [provisioning key](https://help.zscaler.com/zpa/about-zpa-service-edge-provisioning-keys) or create a new [Private Service Edge group](https://help.zscaler.com/zpa/about-zpa-private-service-edge-groups) with a provisioning key for each location.
2. Verify that the [version profile](https://help.zscaler.com/zpa/configuring-version-profile) is set to **Default**, **Previous Default**, or **New Release**. See image.

1. Follow the [step-by-step guide](https://help.zscaler.com/zpa/service-edge-deployment-guide-vmware) to deploy new VMs using the RHEL 9.x images and newly created provisioning keys. Ensure the yum repository is pointing to the new RHEL 9 link: `https://yum.zpagov.net/yum/el9`

Only RHEL 9.x repositories and RPMs are supported on RHEL 9.x.

1. Add trusted networks and enable **Publicly Accessible** (if applicable) on the new Private Service Edge groups. [Image: Edit Private Service Edges group]
2. (Optional) [Disable the Private Service Edge groups](https://help.zscaler.com/zpa/editing-service-edge-groups) 15 minutes prior to the regional off-hours maintenance window to allow connections to gradually drain down.
3. During regional off hours, remove trusted networks and disable public access (if applicable) on CentOS 7 Private Service Edge groups. [Image: Edit Private Service Edges group]

[Image: Set version profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2020","lastmod":"2020-10-23T14:03Z","nid":"1484716"} -->
## ZPA Private Service Edge Release Summary (2020)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2020
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2020)
- Last modified: 2020-10-23T14:03Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2020.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2021","lastmod":"2022-03-24T16:30Z","nid":"1484741"} -->
## ZPA Private Service Edge Release Summary (2021)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2021
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2021)
- Last modified: 2022-03-24T16:30Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2021.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2022","lastmod":"2022-02-27T06:00Z","nid":"1485091"} -->
## ZPA Private Service Edge Release Summary (2022)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2022
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2022)
- Last modified: 2022-02-27T06:00Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2022.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2023","lastmod":"2022-12-19T18:47Z","nid":"1485501"} -->
## ZPA Private Service Edge Release Summary (2023)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2023
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2023)
- Last modified: 2022-12-19T18:47Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2023.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2024","lastmod":"2024-03-03T06:06Z","nid":"1485906"} -->
## ZPA Private Service Edge Release Summary (2024)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2024
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2024)
- Last modified: 2024-03-03T06:06Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2024.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2025","lastmod":"2026-08-19T14:50Z","nid":"1516231"} -->
## ZPA Private Service Edge Release Summary (2025)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2025
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2025)
- Last modified: 2026-08-19T14:50Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2025.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zpa/zpa-private-service-edge-release-summary-2026","lastmod":"2026-09-11T08:53Z","nid":"1534307"} -->
## ZPA Private Service Edge Release Summary (2026)

- Source: https://help.zscaler.com/zpa/zpa-private-service-edge-release-summary-2026
- Product: Private Access (ZPA)
- Path: Private Access (ZPA) Help > Release Notes > ZPA Private Service Edge Release Notes > ZPA Private Service Edge Release Summary (2026)
- Last modified: 2026-09-11T08:53Z
- Summary: Zscaler Private Access (ZPA) Private Service Edge release summary for updates deployed, per version, in 2026.

This article provides a summary of all new features and enhancements released per Zscaler Private Access (ZPA) Private Service Edge version.
<!-- /ZS-ARTICLE -->
