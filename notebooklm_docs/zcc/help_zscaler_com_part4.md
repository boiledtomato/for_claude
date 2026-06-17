# ZCC — help.zscaler.com (続き 4)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector -->

Title: Anti-Tampering for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector

Markdown Content:
# Anti-Tampering for Zscaler Client Connector | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Zscaler Client Connector Profile Management](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-profile-management)
*    Anti-Tampering for Zscaler Client Connector 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Anti-Tampering for Zscaler Client Connector

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

Anti-tampering protection prevents non-admin end-users from stopping, modifying, and deleting Zscaler products and services, including Zscaler files and new registry keys. Admins can enable or disable anti-tampering in [Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles). To disable anti-tampering as an end user, see [Troubleshooting Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector).

Anti-tampering is supported only on Windows desktop operating systems. Anti-tampering is not supported on Windows Server operating systems.

Zscaler Anti-Tampering does not run in safe mode by design. Zscaler recommends disabling safe mode for all end-users and allowing safe mode access only to the administrator.

# ZEP Installer

The anti-tampering framework is part of the ZEP module and is installed by the ZEPInstaller.exe. The ZEPInstaller.exe is a 32-bit program on a 32-bit OS and a 64-bit program on a 64-bit OS that is embedded as a resource inside the Zscaler Client Connector installer. For example, Zscaler-windows-4.0.60.9-installer.exe.

The Zscaler Client Connector installer unpacks the ZEPInstaller.exe to `/program files/zscaler`on the local hard drive.

To enable anti-tampering using an install option, see [Customizing Zscaler Client Connector with Install Options for EXE](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#antitampering) or [Customizing Zscaler Client Connector with Install Options for MSI](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi).

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles)[Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles)[Searching for a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/searching-app-profile)[Copying a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/copying-app-profile)[Zscaler Client Connector Profile Rule Example](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-profile-rule-example)[Viewing the Policy Token for a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/viewing-policy-token-zscaler-client-connector-app-profile)[Anti-Tampering for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector)[Configuring a Default Global Log Mode](https://help.zscaler.com/zscaler-client-connector/configuring-global-default-log-level)[Configuring a Cellular Quota with Zscaler Client Connector for Android](https://help.zscaler.com/zscaler-client-connector/configuring-cellular-quota-zscaler-client-connector-android)[Syncing Directory Groups between Internet & SaaS and Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector)[Best Practices for Adding Bypasses for Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0)[About Application Bypass](https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info)[Adding IP-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic)[Adding Process-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic)[Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-prevention-dlp-integration-zscaler-client-connector)[About Business Continuity](https://help.zscaler.com/zscaler-client-connector/about-business-continuity)[Configuring Business Continuity in Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-business-continuity-zscaler-client-connector)[Customizing the Zscaler Client Connector Theme](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-theme)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/searching-trusted-network -->

Title: Searching for a Trusted Network | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/searching-trusted-network

Markdown Content:
# Searching for a Trusted Network | Zscaler

[![Image 3: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Forwarding Traffic Management](https://help.zscaler.com/zscaler-client-connector/forwarding-traffic-management)
*    Searching for a Trusted Network 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Searching for a Trusted Network

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

You can quickly find a specific trusted network using the **Search** field by selecting different criteria to narrow your search results.

To find a Trusted Network using the **Search**field:

1.   Go to **Infrastructure**>**Locations**>**Trusted Networks**.
2.   In the **Search**field, select a search option from the drop-down menu. You can search using the following criteria:

*   Network Name
*   DNS Servers
*   DNS Search Domains
*   Hostname or IP
*   Network Range
*   Default Gateway
*   DHCP Server
*   Egress IP

1.   Enter search words and press `Enter` to display your search results.

![Image 6: Search for a Trusted Network](https://help.zscaler.com/downloads/zscaler-client-connector/forwarding-traffic-management/searching-trusted-network/zscaler-client-connector-trusted-network-search.png)

To clear the **Search**field, delete the search term you typed and press `Enter`.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Forwarding Profiles](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles)[Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)[Searching for a Forwarding Profile](https://help.zscaler.com/zscaler-client-connector/searching-forwarding-profile)[About Trusted Networks](https://help.zscaler.com/zscaler-client-connector/about-trusted-networks)[Configuring Trusted Networks for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector)[Searching for a Trusted Network](https://help.zscaler.com/zscaler-client-connector/searching-trusted-network)[About Z-Tunnel 1.0 & Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/about-z-tunnel-1.0-z-tunnel-2.0)[Best Practices for Deploying Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)[Migrating from Z-Tunnel 1.0 to Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/migrating-z-tunnel-1.0-z-tunnel-2.0)[Configuring Dedicated Proxy Ports](https://help.zscaler.com/zscaler-client-connector/configuring-dedicated-proxy-ports)[Copying a Forwarding Profile](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/searching-internet-saas-posture-profile -->

Title: Searching for an Internet & SaaS Posture Profile | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/searching-internet-saas-posture-profile

Markdown Content:
# Searching for an Internet & SaaS Posture Profile | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/device-posture-profiles)
*    Searching for an Internet & SaaS Posture Profile 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Searching for an Internet & SaaS Posture Profile

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

You can quickly locate a specific Internet & SaaS posture profile using the Search field.

To find an Internet & SaaS Posture Profile using the Search field:

1.   Go to **Policies**>**Common Configuration**>**Resources**>**Internet & SaaS Posture Profiles**.
2.   In the **Search**field, enter search words and press `Enter` to display your search results.

To clear the **Search**field, delete the search term you typed and press `Enter`.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/about-device-posture-profiles)[Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)[Configuring the Client Certificate Posture Check for Linux](https://help.zscaler.com/zscaler-client-connector/configuring-client-certificate-posture-check-linux)[Searching for a Device Posture Profile](https://help.zscaler.com/zscaler-client-connector/searching-device-posture-profile)[About Internet & SaaS Posture Profiles](https://help.zscaler.com/zscaler-client-connector/about-internet-saas-posture-profiles)[Adding Internet & SaaS Posture Profiles](https://help.zscaler.com/zscaler-client-connector/adding-internet-saas-posture-profiles)[Searching for an Internet & SaaS Posture Profile](https://help.zscaler.com/zscaler-client-connector/searching-internet-saas-posture-profile)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zpa/viewing-and-managing-zscaler-client-connector-download-links -->

Title: Viewing and Managing Zscaler Client Connector Download Links | Zscaler

URL Source: https://help.zscaler.com/zpa/viewing-and-managing-zscaler-client-connector-download-links

Markdown Content:
![Image 1: icon-zpa.svg](blob:http://localhost/f387fb0b7fab0d5b509efd7f530296e1)

## Private Access (ZPA)

### Viewing and Managing Zscaler Client Connector Download Links

After you [create a user portal](https://help.zscaler.com/zpa/configuring-user-portals) for your organization, you can [add links to any applications](https://help.zscaler.com/zpa/configuring-user-portal-links) that your employees and partners are authorized to access. If the application requires that Zscaler Client Connector is installed on the user's device, you can provide links within the user portal to allow them to download the proper Zscaler Client Connector installer for their OS.

You can add a download link from the Client Connector Download Links page to the proper installer for the following PC and mobile operating systems:

*   Apple iOS
*   Apple macOS
*   Google Android
*   Linux
*   Microsoft Windows

You must determine which versions of the Zscaler Client Connector installer are approved for your organization. You are also responsible for maintaining these Zscaler Client Connector download links on the portal as newer versions become available.

To add or update a Zscaler Client Connector download link:

1.   Go to **Policies**>**Access Control**>**Clientless**>**Client Connector Download Links**.
2.   Enter the URL of the Zscaler Client Connector installer for each OS you support. You can copy or paste the **Download Link** from the [Zscaler Client Connector App Store](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-store), or use any other valid download URL for your organization.

![Image 2: Viewing and managing Zscaler Client Connector download links](https://help.zscaler.com/downloads/zpa/user-portal/viewing-and-managing-zscaler-client-connector-download-links/xc-client-connector-download-links-page.png)

3.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-microsoft-365-cloud-pcs -->

Title: Configuring Zscaler Client Connector for Windows 365 Cloud PCs | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-microsoft-365-cloud-pcs

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring Zscaler Client Connector for Windows 365 Cloud PCs

Microsoft Windows 365 is a cloud-delivered, Desktop-as-a-Service (DaaS) solution that provides a virtualized Windows desktop, also referred to as a Cloud PC, that is accessible from any device without needing to manage the underlying infrastructure to support it. These Cloud PCs sit within a Microsoft-managed subscription. You can choose between a self-managed, underlying network infrastructure or a Microsoft-hosted network.

This article describes how to use Zscaler Client Connector on Microsoft Windows 365.

1.   Deploy a Windows 365 provisioning policy. Refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/windows-365/enterprise/deployment-options).
2.   Install Zscaler Client Connector. There are two ways to deploy Zscaler Client Connector to a Windows 365 Cloud PC:
    *   Use a [custom image](https://learn.microsoft.com/en-us/windows-365/enterprise/add-device-images). Install Zscaler Client Connector onto the image before provisioning a Cloud PC.
    *   Use [Intune to deploy the application](https://learn.microsoft.com/en-us/windows-365/enterprise/app-overview) to the Cloud PC after it’s been provisioned. You can do this with either a custom image or a gallery image, and is Microsoft’s recommended approach to app deployment in Windows 365.

3.   Configure Zscaler Client Connector for Windows 365. Bypass the RDP traffic used to connect to a Cloud PC from Zscaler Client Connector and given a direct path. To do this, select **Windows 365 & Azure Visual Desktop** in the Predefined IP-Based Application Bypass field in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#IP).

The predefined application bypass provided by Zscaler simplifies deployment and includes IP addresses used for Cloud PC health monitoring, the Azure Instance Metadata Service (IMDS) endpoint, and gateway addresses with the WindowsVirtualDesktop service tag from Public Azure.

If the connecting client is using Zscaler Client Connector to optimize RDP traffic, Zscaler and Microsoft recommend configuring the bypasses mentioned earlier on both Cloud PCs running Zscaler Client Connector and the connecting client.

If IP address bypasses are not added, the following scenario can occur when a user is connected to the Cloud PC using RDP: After a user logs in to Zscaler Client Connector, the user experiences a freeze on the RDP session because the connection drops and is re-established. This happens every time the user logs in or logs out, turns Internet & SaaS off or on, or restarts the Internet & SaaS service in Zscaler Client Connector. However, after the session is re-established, users can use the Cloud PC without issues because as soon as the tunnel is established, all inbound traffic starts flowing through the tunnel, transferring the RDP traffic to it.

When installing Zscaler Client Connector in strict enforcement mode, use the app policy that has the bypasses mentioned earlier in the `POLICYTOKEN` tag to prevent users from losing access to the Cloud PC. After access is lost, the only way to regain access is to restore to a previous snapshot.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/localization-support -->

Title: Localization Support | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/localization-support

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Localization Support

Zscaler Client Connector supports changing the language of the app user interface based on the system language. The following languages are supported:

| Endpoint Application | Language | Supported As Of |
| --- | --- | --- |
| Zscaler Client Connector for Windows | Chinese Taiwan Traditional (zh-TW) | ![Image 2](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |
| French (fr-FR) | ![Image 3](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) Version 4.5 |
| German (de-DE) | ![Image 4](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |
| Japanese (ja-JP) | ![Image 5](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |
| Spanish (es-ES) | ![Image 6](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |
| Zscaler Client Connector for macOS | Chinese Taiwan Traditional (zh-TW) | ![Image 7](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |
| French (fr-FR) | ![Image 8](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_Yes.png) Version 4.3 |
| German (de-DE) | ![Image 9](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |
| Japanese (ja-JP) | ![Image 10](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |
| Spanish (es-ES) | ![Image 11](https://help.zscaler.com/downloads/client-connector/getting-started/localization-support/Badge_No.png) Not currently supported |

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/captive-portal-sign-fails-chromebook-users -->

Title: Captive Portal Sign-In Fails for Chromebook Users | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/captive-portal-sign-fails-chromebook-users

Markdown Content:
# Captive Portal Sign-In Fails for Chromebook Users | Zscaler

[![Image 4: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Troubleshooting](https://help.zscaler.com/zscaler-client-connector/troubleshooting)
*    Captive Portal Sign-In Fails for Chromebook Users 

![Image 5: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 6: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Captive Portal Sign-In Fails for Chromebook Users

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

After clicking the **Sign in** button in Settings, Chromebook users running Zscaler Client Connector for Android on ChromeOS are not redirected to the captive portal when a captive portal is detected because of an issue with Google ChromeOS.

![Image 7: Chromebook Settings page](https://help.zscaler.com/downloads/client-connector/troubleshooting/chrome-os-troubleshooting/captive-portal-sign-fails-chromebook-users/Client-Connector-Captive-Portal-Sign-in_0.png)
Instead, users can click **Open Captive Portal** in Zscaler Client Connector for either Internet & SaaS or [Private Access until this issue is resolved.

![Image 8: Zscaler-Client-Connector-captive-portal-button](https://help.zscaler.com/downloads/client-connector/troubleshooting/chrome-os-troubleshooting/captive-portal-sign-fails-chromebook-users/Client-Connector-ZPA-Open-Captive-Portal_2.png)

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Zscaler Client Connector Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)[Zscaler Client Connector: Windows Registry Keys](https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys)[Zscaler Client Connector: Connection Status Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)[Zscaler Client Connector: Private Access Authentication Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)[Zscaler Services Inaccessible After Upgrading to Zscaler Client Connector versions 4.4 and 4.4.1 for iOS](https://help.zscaler.com/zscaler-client-connector/zscaler-services-inaccessible-after-upgrading-zscaler-client-connector-versions-4-4-and-4-4-1-ios)[DNS Resolution Fails through the ZIA Public Service Edge](https://help.zscaler.com/zscaler-client-connector/dns-resolution-fails-through-zia-public-service-edge)[Captive Portal Sign-In Fails for Chromebook Users](https://help.zscaler.com/zscaler-client-connector/captive-portal-sign-fails-chromebook-users)[Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page)[AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail](https://help.zscaler.com/zscaler-client-connector/apparmor-causes-auto-upgrade-zscaler-client-connector-version-3-7-1-linux-fail)[Firewall Posture Check Failure on macOS Sequoia](https://help.zscaler.com/zscaler-client-connector/firewall-posture-check-failure-macos-sequoia)[Microsoft Outlook and Microsoft Teams Not Accessible](https://help.zscaler.com/zscaler-client-connector/microsoft-outlook-and-microsoft-teams-not-accessible)[Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/log-failure-zscaler-client-connector-android-chromeos--version-1.12)[Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows](https://help.zscaler.com/zscaler-client-connector/browser-based-authentication-fails-when-using-certain-browsers-launch-zscaler-client-connector-linux)[DNS Request Failure in Zscaler Client Connector version 4.1.0.89](https://help.zscaler.com/zscaler-client-connector/dns-request-failure-zscaler-client-connector-version-4.1.0.89)[DNS Resolution Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/dns-resolution-failure-zscaler-client-connector-android-chromeos-version-1.12)[Upgrading to Zscaler Client Connector 3.7 for Windows](https://help.zscaler.com/zscaler-client-connector/upgrading-zscaler-client-connector-3.7-windows)[Missing Zscaler Client Connector GNOME Tray icon for Linux](https://help.zscaler.com/zscaler-client-connector/missing-zscaler-client-connector-gnome-tray-icon)[Upgrading to Windows 10, Version 2004](https://help.zscaler.com/zscaler-client-connector/upgrading-windows-10-version-2004)[Supporting M1 Processors](https://help.zscaler.com/zscaler-client-connector/supporting-m1-processors)[Upgrading to macOS Big Sur](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-big-sur)[Upgrading to macOS Catalina](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-catalina)[Upgrading to Android 10](https://help.zscaler.com/zscaler-client-connector/upgrading-android-10)[Using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-cisco-anyconnect-macos-catalina)[Using the Unauthorized Modification Device Posture Profile](https://help.zscaler.com/zscaler-client-connector/using-unauthorized-modification-device-posture-profile)[Resolving Update Issues to Zscaler Client Connector 1.4.3](https://help.zscaler.com/zscaler-client-connector/resolving-update-issues-zscaler-app-1.4.3)[Login Failures with Zscaler Client Connector Using Active Directory](https://help.zscaler.com/zscaler-client-connector/login-failures-zscaler-client-connector-using-active-directory)[Resolving Zscaler Client Connector Linux 1.2 DNS Configuration Issues for VPNs](https://help.zscaler.com/zscaler-client-connector/resolving-zscaler-client-connector-linux-1.2-dns-configuration-issues-vpns)[Resolving Auto-Update Issues for Zscaler Client Connector Linux 1.2](https://help.zscaler.com/zscaler-client-connector/resolving-auto-update-issues-zscaler-client-connector-linux-1.2)[Upgrading to Zscaler Client Connector version 1.9 for Android](https://help.zscaler.com/zscaler-client-connector/upgrading-android-1.9)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider -->

Title: Using the Zscaler Admin Console as an Identity Provider | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Using the Zscaler Admin Console as an Identity Provider

If you are a Authentication Service user, see [Using Authentication Service](https://help.zscaler.com/zidentity/using-zslogin-identity-provider)[with Zscaler Client Connector Device Tokens](https://help.zscaler.com/zidentity/using-zidentity-with-zscaler-client-connector-device-tokens).

This information applies to Internet & SaaS only. The Zscaler Admin Console can function as an identity provider (IdP) for the Zscaler service. With this feature, users do not need to be tied to your organization’s standard IdP in order to authenticate to the Zscaler service. Instead, if your organization uses SAML-based single sign-on (SSO), Zscaler Client Connector can use a device token to auto-provision and silently authenticate users and devices for the Zscaler service.

You can generate the device token in the Zscaler Admin Console and pass the token to Zscaler Client Connector in an installer option. In addition, in the Zscaler Admin Console, you must select the Zscaler Client Connector Portal as your authentication method. The app is then able to gather user ID and other relevant parameters from devices and send the information to the Zscaler cloud in SAML requests. The Zscaler Admin Console parses and verifies the SAML requests, enabling the Zscaler cloud to provision and silently authenticate users.

## Configuring the Zscaler Admin Console to function as an IdP

To configure the Zscaler Admin Console to function as an IdP:

1.   [Create a device token](https://help.zscaler.com/zscaler-client-connector/creating-device-token).
2.   [Add the Zscaler Client Connector as an IdP](https://help.zscaler.com/zia/adding-zscaler-client-connector-portal-idp).
3.   [Pass the device token, user domain, and cloud name](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider#PassToken).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector -->

Title: Best Practices for Using PAC Files with Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Best Practices for Using PAC Files with Zscaler Client Connector

[Watch a video on using PAC files with Zscaler Client Connector.](https://fast.wistia.net/embed/iframe/fofxzpyxrv)

To prevent outages, Zscaler strongly recommends that you roll out all PAC file changes to a small set of users before deploying changes to all users.

A PAC file is a text file used for directing traffic to a proxy server. A PAC file can also be used to bypass traffic or direct traffic to a specific proxy (i.e., traffic splitting). To learn more about PAC files, see [Understanding PAC Files](https://help.zscaler.com/zia/understanding-pac-file).

Zscaler Client Connector uses PAC files in the [Forwarding Profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app) and in the [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles). The Forwarding Profile PAC file is used to direct system, browser, and application traffic to Zscaler Client Connector. When Zscaler Client Connector receives traffic, the App Profile is used to direct that traffic to the Zscaler cloud. Zscaler Client Connector directs traffic to the closest data center according to the App Profile PAC file by changing the proxy return statement to the IP address of the geographically closest data center.

If the primary and secondary data center from the PAC file both fail, Zscaler Client Connector attempts to connect to gateway.<cloudname>.net, unless the **Fallback to gateway domain** setting is disabled.

This article provides best practices for using PAC files with Zscaler Client Connector.

*   If you experience slowness when using Zscaler Client Connector:
    *   Check if the data center has any issues.
    *   Run traceroute to check for possible ISP issues.
    *   From the App Profile PAC file, check for and remove the functions `dnsResolve()`, `isResolvable()`, and`isInNet()`.
    *   Check if any network firewalls or network scanning is interfering with traffic.
    *   Check for possible server issues by browsing to a website without a proxy, with the PAC file only, and with Zscaler Client Connector.
    *   Check if another process, such as Group Policy Object (GPO), is also applying proxy settings. This can conflict with Zscaler Client Connector and cause performance issues on the device.
    *   Ensure that the return statement in your PAC file has double quotes. For example, `return "DIRECT";`. Missing double quotes can cause errors.

*   Bypass domains by using the shExpMatch function on the host variable. For example:

Bypassing a domain:

if (shExpMatch(host, "safemarch.com")) return "DIRECT";
Bypassing a domain and all of its subdomains:

if (shExpMatch(host, "safemarch.com") || shExpMatch(host, "*.safemarch.com")) return "DIRECT";
*   Apply bypasses in the Zscaler Admin Console by configuring bypasses in the SSL bypass list and in the Authentication Bypass list. If you have traffic that you do not want to reach the Zscaler cloud, and it must be bypassed on the client itself, you must configure bypasses in the PAC files used for Zscaler Client Connector.
*   If you are using Zscaler Client Connector version 1.4 or later, you can configure multiple Public Service Edge for Internet & SaaS gateway destinations based on the destination the user is trying to access. For example, if you want traffic for internal hosts to go to a specific data center, but all other traffic to go to the geographically closest data center, define two return statements with the respective Public Service Edge for Internet & SaaS gateway IP addresses. For example:

var InternalHosts = /(remote\.mydomain\.com|mail\.mydomain\.com)/; 
if (InternalHosts.test(host)) 
{
return "PROXY 104.129.192.43:80; PROXY 104.129.198.34:80; DIRECT";
}
return "PROXY ${GATEWAY}; PROXY ${SECONDARY_GATEWAY}:9400; DIRECT";
} 
For optimal performance, do not define more than two return statements that contain Zscaler Client Connector gateway IP addresses.

The following are the best practices for using PAC files with different forwarding methods:

*   [Tunnel](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)To configure the following PAC files with Tunnel mode:

    *   [Forwarding Profile PAC File](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel-forwarding-profile)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)In Tunnel mode, you must only use the Forwarding Profile PAC file to bypass traffic away from Zscaler Client Connector or to tunnel traffic to Zscaler Client Connector. Do not use it to tunnel traffic to the Zscaler cloud.

In **Tunnel** mode, a Forwarding Profile PAC file is commonly used to get web traffic on nonstandard ports. For example:

if ( shExpMatch(url,"*:8080") ||
shExpMatch(url,"*:8081"))
return "PROXY ${ZAPP_LOCAL_PROXY}";
else
return "DIRECT";
If you choose Tunnel mode when configuring the Forwarding Profile, select Apply on Network Change or Never for the Proxy Action Type.

        *   When you select Apply on Network Change, Zscaler Client Connector applies proxy settings when the network changes once and does not enforce them.
        *   When you select Never, Zscaler Client Connector does not apply any PAC file configurations to the system.

In an IPv6 environment, IPv6 bypasses are required for Private Access apps in the App Profile PAC file when the Forwarding PAC is configured to send traffic to the Zscaler Client Connector listener.

Routes and filters are used to direct browser, system, or application traffic to Zscaler Client Connector. Zscaler Client Connector intercepts any traffic heading to port 80 and port 443.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel-forwarding-profile)

    *   [App Profile PAC File](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel-app-profile)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)In Tunnel mode, the App Profile PAC file directs traffic that Zscaler Client Connector received to the Zscaler cloud. Using the PAC file, Zscaler Client Connector chooses the data center and tunnels the traffic to it with the Connect Tunnel method. If the PAC file contains exceptions for traffic, Zscaler Client Connector sends that traffic directly to the destination server.

When using the App Profile PAC file for Tunnel mode, you must:

        *   Configure exceptions in the PAC file for traffic that must be bypassed.
        *   Keep the PAC file as simple as possible.

The following is an example of App Profile PAC file for Tunnel mode:

```
function FindProxyForURL(url, host) {
    var privateIP = /^(0|10|127|192\.168|172\.1[6789]|172\.2[0-9]|172\.3[01]|169\.254|192\.88\.99)\.[0-9.]+$/;
    
    /* Non-FQDN or private IP goes direct */
    if (isPlainHostName(host) || privateIP.test(host)) {
        return "DIRECT";
    }
    
    /* FTP goes directly */
    if (url.substring(0,4) == "ftp:") {
        return "DIRECT";
    }
    
    /* Private domains or ZPA apps goes direct */
    if (shExpMatch(host, "corp.zscaler.com") || shExpMatch(host, "*.corp.zscaler.com")) {
        return "DIRECT";
    }
    
    /* Any other bypass */
    if (shExpMatch(host, "zscaler.com") || shExpMatch(host, "*.zscaler.com")) {
        return "DIRECT";
    }
    
    /* Updates are directly accessible */
    if ((localHostOrDomainIs(host, "trust.zscaler.com") ||
        localHostOrDomainIs(host, "trust.zscaler.net") ||
        localHostOrDomainIs(host, "trust.zscloud.net") ||
        localHostOrDomainIs(host, "trust.zscalerone.net") ||
        localHostOrDomainIs(host, "trust.zscalertwo.net")) &&
        (url.substring(0,5) == "http:" || url.substring(0,6) == "https:")) {
        return "DIRECT";
    }
    
    /* Default Traffic Forwarding. Forwarding to Zen */
    return "PROXY ${GATEWAY}:80; PROXY ${SECONDARY_GATEWAY}:80; DIRECT";
}
```

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel-app-profile)

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel)

*   [Tunnel with Local Proxy](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel-with-local-proxy)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)To configure the following PAC files with Tunnel with Local Proxy mode:

    *   [Forwarding Profile PAC File](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#twlp-forwarding-profile)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)In Tunnel with Local Proxy mode, the Forwarding Profile PAC file is responsible for directing system, browser, and application traffic to Zscaler Client Connector.

When using the Forwarding Profile PAC file for Tunnel with Local Proxy mode, you must:

        *   Use this PAC file for configuring exceptions. Configuring an exception in the PAC file allows you to bypass traffic so that it does not reach Zscaler Client Connector.
        *   Ensure that the proxy return statement has the following macro:

return "PROXY ${ZAPP_LOCAL_PROXY}";

When Zscaler Client Connector downloads this PAC file, it replaces the macro with a loopback IP address and the port that Zscaler Client Connector is listening to. Then Zscaler Client Connector applies this PAC file to the system, browser, and applications.

If traffic does not follow the Forwarding Profile PAC file, it does not reach Zscaler Client Connector and the Zscaler cloud. Instead, the traffic is sent directly.

In an IPv6 environment, IPv6 bypasses are required for Private Access apps in the App Profile PAC file when the Forwarding PAC is configured to send traffic to the Zscaler Client Connector listener.

The following is an example of a Forwarding Profile PAC file for Tunnel with Local Proxy mode:

```
function FindProxyForURL(url, host) {
    var privateIP = /^(0|10|127|192\.168|172\.1[6789]|172\.2[0-9]|172\.3[01]|169\.254|192\.88\.99)\.[0-9.]+$/;
    var resolved_ip = dnsResolve(host);
 /* Non-FQDN or private IP goes direct */
    if (isPlainHostName(host) || isInNet(resolved_ip, "192.0.2.0","255.255.255.0") || privateIP.test(resolved_ip)) {
        return "DIRECT";
    }
 /* FTP goes directly */
    if (url.substring(0,4) == "ftp:") {
        return "DIRECT";
    }
  /* ZPA goes direct */
    if (isInNet(resolved_ip, "100.64.0.0","255.255.0.0")) {
        return "DIRECT";
    }
    
    /* Any other bypass */
    if (shExpMatch(host, "zscaler.com") || shExpMatch(host, "*.zscaler.com")) {
        return "DIRECT";
    }
    
    /* Updates are directly accessible */
    if ((localHostOrDomainIs(host, "trust.zscaler.com") ||
        localHostOrDomainIs(host, "trust.zscaler.net") ||
        localHostOrDomainIs(host, "trust.zscloud.net") ||
        localHostOrDomainIs(host, "trust.zscalerone.net") ||
        localHostOrDomainIs(host, "trust.zscalertwo.net")) &&
        (url.substring(0,5) == "http:" || url.substring(0,6) == "https:")) {
        return "DIRECT";
    }
  /* Default Traffic Forwarding: All traffic goes to Z-App */
    return "PROXY ${ZAPP_LOCAL_PROXY}";
   }
  /* Non-FQDN or private IP goes direct */
    if (isPlainHostName(host) || isInNet(resolved_ip, "192.0.2.0","255.255.255.0") || privateIP.test(resolved_ip)) {
        return "DIRECT";
    }
  /* Default Traffic Forwarding: All traffic goes to Z-App */
    return "PROXY ${ZAPP_LOCAL_PROXY}";
  }
```

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#twlp-forwarding-profile)

    *   [App Profile PAC File](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#twlp-app-profile)[](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)In Tunnel with Local Proxy mode, the App Profile directs traffic (that Zscaler Client Connector received) to the Zscaler cloud. Zscaler Client Connector directs traffic to the closest data center according to the App Profile PAC file by changing the proxy return statement to the geographically closest data center IP address.

When using the App Profile PAC for Tunnel with Local Proxy mode, you must keep the PAC file as simple as possible.

The following is an example of App Profile PAC file for Tunnel with Local Proxy mode:

```
function FindProxyForURL(url, host) {
    /* Default Traffic Forwarding. Forwarding to Zen */
    return "PROXY ${GATEWAY}:80; PROXY ${SECONDARY_GATEWAY}:80; DIRECT";
}
```

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#twlp-app-profile) 

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#tunnel-with-local-proxy)

*   [Enforce Proxy](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#enforce-proxy)[](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)In Enforce Proxy mode, Zscaler Client Connector acts similarly to a GPO pushing a PAC file on the system. Zscaler Client Connector enforces the Forwarding Profile PAC file on your machine.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector#enforce-proxy)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-android -->

Title: Customizing Zscaler Client Connector with Install Options for Android | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-android

Markdown Content:
. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/registering-devices-zpa-idp-username -->

Title: Registering Devices with the ZPA IdP Username | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/registering-devices-zpa-idp-username

Markdown Content:
. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/create-device-groups -->

Title: Creating Device Groups | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/create-device-groups

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Creating Device Groups

[Watch a video about Creating Device Groups.](https://fast.wistia.net/embed/iframe/rwfvkb1k6o)

You can create device groups based on device posture profiles. Assign these groups to policies and [service entitlements](https://help.zscaler.com/zscaler-client-connector/about-zscaler-service-entitlement), such as enrolling device groups in Zscaler Private Access (ZPA), Zscaler Internet Access (ZIA), Zscaler Digital Experience (ZDX), and Zscaler Deception. You can add up to 4 device groups. To learn more, see [About Device Groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups-0).

To create device groups for Android or iOS, you must first enable[Use Device Groups in Service Entitlement](https://help.zscaler.com/zscaler-client-connector/enabling-device-groups-android) in [Platform Settings](https://help.zscaler.com/zscaler-client-connector/about-platform-settings).

To create a device group:

1.   In the Zscaler Client Connector Portal, go to **Administration**>**Device Groups**.
2.   Click **Add Device Group**.

The **Add Device Group** window appears.

3.   In the **Add Device Group** window:
    1.   **Define Device Group:**Enter a name for the device group.
    2.   **Platform**: Select the applicable platforms.
    3.   **Device Group Configuration**: Select one or more device postures from the **Select Device Posture** drop-down menu.
        *   When selecting multiple device postures, the `AND` operator is added after each device posture. Click out of the drop-down menu when finished selecting device postures.
        *   (Optional) Click **Add**to use the `OR` operator and then select a device posture from the drop-down menu. Click out of the drop-down menu when finished selecting a device posture. You can continue to add up to 5 device postures.
        *   Review your expression in **Expression Preview**. Within a group, the logic is `AND`. Between groups, the logic is `OR`.

    4.   (Optional) **Device Group Description**: Add a description of the device group.

![Image 2: Add Device Group in the Zscaler Client Connector Portal](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-service-entitlement/create-device-groups/client-connector-add-device-grp2.png)

1.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/allowing-non-administrators-access-client-connector-log-files -->

Title: Allowing Non-Administrator Users Access to Zscaler Client Connector Log Files | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/allowing-non-administrators-access-client-connector-log-files

Markdown Content:
# Allowing Non-Administrator Users Access to Zscaler Client Connector Log Files | Zscaler

[![Image 3: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Zscaler Client Connector Support Settings](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings)
*   [User Privacy](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings/user-privacy)
*    Allowing Non-Administrator Users Access to Zscaler Client Connector Log Files 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Allowing Non-Administrator Users Access to Zscaler Client Connector Log Files

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

This feature enables Zscaler Admin Console administrators to grant Zscaler Client Connector log file access to users who don’t have administrator privileges. This allows non-administrator users to send specific log files to admins instead of having to send the entire log folder in a ZIP file, making the troubleshooting process more efficient.

To allow non-admin users access to the Zscaler program data folder to access log files:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**User Privacy**.
2.   Select**Allow access to Client Connector logs folder for non administrators**.

![Image 6: Allow access to Client Connector logs folder for non administrators option](https://help.zscaler.com/downloads/zclient-connector-user-privacy-allow-access-cc-logs.png)

3.   Click **Save**.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About User Privacy](https://help.zscaler.com/zscaler-client-connector/about-user-privacy)[Configuring Zscaler Client Connector to Collect Device Owner Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-device-owner-information)[Configuring Zscaler Client Connector to Collect Hostnames](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-hostnames)[Enabling Packet Capture for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector)[Configuring Automatic Crash Reporting for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-crash-reporting-zscaler-client-connector)[Configuring Zscaler Client Connector to Collect ZDX Location Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-zdx-location-information)[Allowing Users to Override Z-Tunnel 2.0 or Private Access Protocol Settings](https://help.zscaler.com/zscaler-client-connector/allowing-users-override-z-tunnel-2-0-or-private-access-protocol-settings)[Allowing Non-Administrator Users Access to Zscaler Client Connector Log Files](https://help.zscaler.com/zscaler-client-connector/allowing-non-administrators-access-client-connector-log-files)[Restricting Remote Packet Capture](https://help.zscaler.com/zscaler-client-connector/restricting-remote-packet-capture)[Enabling Auto System Info and Log Fetch](https://help.zscaler.com/zscaler-client-connector/enabling-auto-system-info-and-log-fetch)[Enforcing Secure PAC URLs](https://help.zscaler.com/zscaler-client-connector/enforcing-secure-pac-urls)[Bypassing FQDN Subdomains for VPN Gateway Bypass](https://help.zscaler.com/zscaler-client-connector/bypassing-fqdn-subdomains-vpn-gateway-bypass)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector -->

Title: Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector

This article provides an overview of the Data Protection window on Zscaler Client Connector. This window features connectivity information and troubleshooting steps for the Data Protection feature on Windows and macOS devices.

![Image 2: Data Protection window](https://help.zscaler.com/downloads/client-connector/end-user-guide/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector/data-protection-on-zcc.jpg)

## Connectivity

*   **Service Status**: Displays the app connection status. Click **Turn Off** if you want to disable the Data Protection service while remaining logged in to the app. The Data Protection service is disabled until you click **Turn On**. You can disable the service using the [Password to Disable Endpoint DLP](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles#windows) in App Profiles or by using a One-Time Password (OTP).

Admins can access an OTP code from the Zscaler Admin Console:

    1.   Go to **Infrastructure > Connectors > Client > Device Overview**.
    2.   Click the View icon to view the device fingerprint for the enrolled device.
    3.   Under **Compliance Status**, copy the password listed under **Disable Endpoint DLP OTP**.

[See image.](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector#disable-otp)

*   **Product Version:**Displays the current version of the Data Protection feature.

## Troubleshoot

*   **Request exemption**: Users that are blocked by the Endpoint DLP due to a rule violation can request to be exempted from the block action, and instead be monitored. Clicking this option prompts you to enter a password.

Admins can access an OTP code from the Zscaler Admin Console:

    1.   Go to **Infrastructure > Connectors > Client > Device Overview**.
    2.   Click the View icon to view the device fingerprint for the enrolled device.
    3.   Under **Compliance Status**, copy the password listed under **Disable Endpoint DLP OTP**.

The OTP also works if you are in offline mode. Exemptions are granted for 12 hours.

*   **Update DLP Policy**: Click to manually refresh your endpoint DLP policy. The DLP policy refreshes every 15 minutes. If you want to update immediately, click **Update DLP Policy**.
*   **Open Quarantine Folder**: Contains the files that moved to this folder as a result of a block action for the personal cloud storage rules (e.g., Dropbox) or by other channels where the file source information is missing (e.g., copying an extract ZIP file to a removable storage device).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page -->

Title: Zscaler Client Connector Displays Blank Page | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page

Markdown Content:
# Zscaler Client Connector Displays Blank Page | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Troubleshooting](https://help.zscaler.com/zscaler-client-connector/troubleshooting)
*    Zscaler Client Connector Displays Blank Page 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Zscaler Client Connector Displays Blank Page

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

Zscaler Client Connector versions 4.3, 4.4, and 4.5 for Windows have a known issue if WebView2 is enabled where embedded web pages can appear blank if display settings have changed or if displays are attached or removed from the system. The issue affects SAML authentication during login, reauthentication, acceptable use policy, license agreement, data loss protection notifications, and self-service notifications.

You can temporarily resolve the issue by restarting the application (terminate the ZSATray.exe process via Task Manager) or restarting the system. The issue is fixed on Zscaler Client Connector version 4.3.0.255, version 4.4.0.346, and version 4.5.0.296 for Windows.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Zscaler Client Connector Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)[Zscaler Client Connector: Windows Registry Keys](https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys)[Zscaler Client Connector: Connection Status Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)[Zscaler Client Connector: Private Access Authentication Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)[Zscaler Services Inaccessible After Upgrading to Zscaler Client Connector versions 4.4 and 4.4.1 for iOS](https://help.zscaler.com/zscaler-client-connector/zscaler-services-inaccessible-after-upgrading-zscaler-client-connector-versions-4-4-and-4-4-1-ios)[DNS Resolution Fails through the ZIA Public Service Edge](https://help.zscaler.com/zscaler-client-connector/dns-resolution-fails-through-zia-public-service-edge)[Captive Portal Sign-In Fails for Chromebook Users](https://help.zscaler.com/zscaler-client-connector/captive-portal-sign-fails-chromebook-users)[Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page)[AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail](https://help.zscaler.com/zscaler-client-connector/apparmor-causes-auto-upgrade-zscaler-client-connector-version-3-7-1-linux-fail)[Firewall Posture Check Failure on macOS Sequoia](https://help.zscaler.com/zscaler-client-connector/firewall-posture-check-failure-macos-sequoia)[Microsoft Outlook and Microsoft Teams Not Accessible](https://help.zscaler.com/zscaler-client-connector/microsoft-outlook-and-microsoft-teams-not-accessible)[Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/log-failure-zscaler-client-connector-android-chromeos--version-1.12)[Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows](https://help.zscaler.com/zscaler-client-connector/browser-based-authentication-fails-when-using-certain-browsers-launch-zscaler-client-connector-linux)[DNS Request Failure in Zscaler Client Connector version 4.1.0.89](https://help.zscaler.com/zscaler-client-connector/dns-request-failure-zscaler-client-connector-version-4.1.0.89)[DNS Resolution Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/dns-resolution-failure-zscaler-client-connector-android-chromeos-version-1.12)[Upgrading to Zscaler Client Connector 3.7 for Windows](https://help.zscaler.com/zscaler-client-connector/upgrading-zscaler-client-connector-3.7-windows)[Missing Zscaler Client Connector GNOME Tray icon for Linux](https://help.zscaler.com/zscaler-client-connector/missing-zscaler-client-connector-gnome-tray-icon)[Upgrading to Windows 10, Version 2004](https://help.zscaler.com/zscaler-client-connector/upgrading-windows-10-version-2004)[Supporting M1 Processors](https://help.zscaler.com/zscaler-client-connector/supporting-m1-processors)[Upgrading to macOS Big Sur](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-big-sur)[Upgrading to macOS Catalina](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-catalina)[Upgrading to Android 10](https://help.zscaler.com/zscaler-client-connector/upgrading-android-10)[Using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-cisco-anyconnect-macos-catalina)[Using the Unauthorized Modification Device Posture Profile](https://help.zscaler.com/zscaler-client-connector/using-unauthorized-modification-device-posture-profile)[Resolving Update Issues to Zscaler Client Connector 1.4.3](https://help.zscaler.com/zscaler-client-connector/resolving-update-issues-zscaler-app-1.4.3)[Login Failures with Zscaler Client Connector Using Active Directory](https://help.zscaler.com/zscaler-client-connector/login-failures-zscaler-client-connector-using-active-directory)[Resolving Zscaler Client Connector Linux 1.2 DNS Configuration Issues for VPNs](https://help.zscaler.com/zscaler-client-connector/resolving-zscaler-client-connector-linux-1.2-dns-configuration-issues-vpns)[Resolving Auto-Update Issues for Zscaler Client Connector Linux 1.2](https://help.zscaler.com/zscaler-client-connector/resolving-auto-update-issues-zscaler-client-connector-linux-1.2)[Upgrading to Zscaler Client Connector version 1.9 for Android](https://help.zscaler.com/zscaler-client-connector/upgrading-android-1.9)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/creating-device-token -->

Title: Creating a Device Token | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/creating-device-token

Markdown Content:
# Creating a Device Token | Zscaler

[![Image 3: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Platform and Authentication Management](https://help.zscaler.com/zscaler-client-connector/platform-and-authentication-management)
*    Creating a Device Token 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Creating a Device Token

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

If you configure the Zscaler Admin Console as your identity provider (IdP), users automatically enroll with Zscaler Client Connector. Users and their devices authenticate using a device token generated in the Zscaler Admin Console.

Zscaler must enable this feature.

To generate the device token:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Client Connector IdP**.
2.   Click **Create Device Token**.
3.   In the **Create Device Token** window, do the following:

    1.   **Enter****Password**: Enter a password that is at least six characters and includes at least one alphabetic character and a number. This password is only needed to generate the token and is not needed after that.
    2.   **Token****Description**: Enter a description that helps you track the device token.
    3.   Click**Create Token**.

![Image 6: Create Device Token window](https://help.zscaler.com/downloads/zscaler-client-connector/platform-and-authentication-management/creating-device-token/zclient-connector-create-device-token.png)

The token you generate appears in the table under **Manage Device Tokens**. You can create up to 16 tokens.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Platform Settings](https://help.zscaler.com/zscaler-client-connector/about-platform-settings)[Enabling Browser-Based Authentication](https://help.zscaler.com/zscaler-client-connector/enabling-browser-based-authentication)[Using WebView2 Authentication](https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication)[Enabling Resizing of the Zscaler Client Connector Authentication Window](https://help.zscaler.com/zscaler-client-connector/enabling-resizing-zscaler-client-connector-authentication-window)[Enabling ZDX Module Upgrades via the CLI](https://help.zscaler.com/zscaler-client-connector/enabling-zdx-module-upgrades-cli)[Enabling IPv6 Resolution for Zscaler Domains](https://help.zscaler.com/zscaler-client-connector/enabling-ipv6-resolution-zscaler-domains)[About Zscaler Client Connector IdP](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-idp)[Using the Zscaler Admin Console as an Identity Provider](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider)[Creating a Device Token](https://help.zscaler.com/zscaler-client-connector/creating-device-token)[Customizing the Zscaler Client Connector User Agent](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-user-agent)[Configuring Passwords for Access in Unattended Mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode)[Zscaler Client Connector and Imprivata Integration](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0 -->

Title: Best Practices for Deploying Z-Tunnel 2.0 | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Best Practices for Deploying Z-Tunnel 2.0

Following the best practices in this article can ensure successful deployment of Zscaler Tunnel (Z-Tunnel) 2.0 for your organization.

*   You must have a [supported version](https://help.zscaler.com/eos-eol/supported-versions) of Zscaler Client Connector to use Z-Tunnel 2.0.
*   Based on your organization’s needs, your settings for the phase 5 rollout to all users might be different than the recommended test settings described in phases 1 through 4.

*   [Phase 1: Identify a Group of Users and Configure Z-Tunnel 2.0 Settings](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase1)

For initial testing, begin by identifying a group of users and configuring Zscaler Tunnel (Z-Tunnel) 2.0 settings.

    *   [Step 1: Create a Group](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#step1)[](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)In the Zscaler Admin Console, identify and create a small group of users for testing Z-Tunnel 2.0. Groups you configure in User Management in Internet & SaaS are automatically available for selection in the Advanced Settings tab of Directory Sync & Custom Root Cert in Client Connector. You can also manually sync the groups between Internet & SaaS and Client Connector. To learn more, see [Syncing Directory Groups in the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-zia-admin-portal-and-zscaler-client-connector-portal).

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#step1) 
    *   [Step 2: Create a Forwarding Profile Policy](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#step2)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)To prevent confusion, Zscaler recommends creating a new forwarding profile for your Z-Tunnel 2.0 test. The forwarding profile policy determines what traffic Zscaler Client Connector captures from users’ devices. You enable the forwarding mechanism for Z-Tunnel 2.0 in this policy.

To enable Z-Tunnel 2.0, you must configure the following settings in the forwarding profile:

        *   **Tunnel Driver Type**: Select **Packet Filter-Based**.
        *   **On-Trusted Network:** Select **Tunnel**.
        *   **Tunnel version selection:** Select**Z-Tunnel 2.0**.
        *   **VPN-Trusted Network** and **Off-Trusted Network:** Select **Same as "On-Trusted Network"**.

For testing purposes, Zscaler recommends that you don’t configure the **VPN-Trusted Network** and**Off-Trusted Network** forwarding profile actions. After initial testing, you can change the behavior for all network environments.

For all other settings, keep the default. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).

Don’t route Z-Tunnel 2.0 traffic through GRE tunnels. For on-premises environments with existing GRE infrastructure, implement one of the following options to prevent performance issues:

        *   Configure the Zscaler Client Connector forwarding profile to [fall back to Z-Tunnel 1.0](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector) when Trusted Network Criteria are met.
        *   Configure a policy-based route to exclude Z-Tunnel 2.0 traffic from the GRE tunnel.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#step2)

    *   [Step 3: Create an App Profile Policy](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#step3)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)[Create a new app profile policy](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) and associate your test users and the Z-Tunnel 2.0 forwarding profile to it.

To configure Z-Tunnel 2.0, you must configure the following in the app profile:

        *   Under **General**, select the following:
            *   **Rule Order**: Select **1** to ensure that your users receive this app profile before other profiles.
            *   **Status**: Select **Enabled**.
            *   **Forwarding Profile**:Select the forwarding profile you created for testing from the drop-down menu.
            *   Enable **Install Zscaler SSL Certificate**.

        *   Under **Groups**, select your test users group from the **User Groups**drop-down menu.
        *   Under **Traffic Steering**, on the **PAC and Proxy** tab, leave the **Custom PAC URL**field empty.
        *   Under **Passwords**, leave the **Logout Password**and **Disable Password ZIA** fields empty.

For all other settings, keep the default. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#step3)

    *   [Step 4: Assign a Supported Zscaler Client Connector Version to the Group](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#step4)

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase1)

*   [Phase 2: Block ICMP Traffic to Perform Initial Testing](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase2)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)To perform initial testing, begin by ensuring that ICMP traffic is blocked.

To block ICMP traffic:

    1.   Ping google.com.
    2.   Verify that ICMP traffic is seen on file for all logs.
    3.   Create a firewall rule to block ICMP traffic.
    4.   Ping google.com to test this rule.
    5.   Verify that ICMP traffic is blocked on all logs.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase2)

*   [Phase 3: Exclude Internal Network Ranges from Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase3)
*   [Phase 4: Continue to Test the Group for One to Two Weeks](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase4)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)Continue to test this group of test users for one to two weeks.

To test your Z-Tunnel 2.0 configuration:

    1.   Identify the top business applications that your organization uses.
    2.   Test access to these applications with the test user group.
    3.   Get user feedback on any issues they experience.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase4)

*   [Phase 5: Roll Out a Supported Version of Zscaler Client Connector and Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase5)[](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)Roll out a [supported version](https://help.zscaler.com/eos-eol/supported-versions) of Zscaler Client Connector and Z-Tunnel 2.0 to the rest of your employees in batches of 100 to 200 users. Each time you roll out Zscaler Client Connector and Z-Tunnel 2.0 to a group, you must ensure that your business applications are unaffected.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0#phase5)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android -->

Title: Deploying Zscaler Client Connector with Microsoft Intune for Android | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Deploying Zscaler Client Connector with Microsoft Intune for Android

This guide is for admins only. If you are an end user, contact your organization’s administrator for deployment-related details.

With Microsoft Intune, you can deploy Zscaler Client Connector for your Android and Android on ChromeOS devices. The Microsoft Intune Service release version 2006 is shown in the following steps.

If you deploy Zscaler Client Connector from Google Play _with_ Android Enterprise enabled, you can preconfigure Zscaler Client Connector with [parameters](https://help.zscaler.com/zscaler-client-connector/parameters-guide-zscaler-client-connector-android-and-android-chromeos). This allows you to simplify the Zscaler Client Connector [enrollment process](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-service-zscaler-client-connector) for your users. But, if you deploy Zscaler Client Connector from Google Play _without_ Android Enterprise enabled or as an APK file, you cannot preconfigure Zscaler Client Connector.

*   [From Google Play _with_ Android Enterprise enabled](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#google-play-with-android-enterprise-enabled)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)To deploy Zscaler Client Connector to Microsoft Intune for enterprise-enabled Android devices:

    *   [Configure Managed Google Play app](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#managed-google-play-app)

        1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)In the Microsoft Intune admin center, click **Apps** in the left-side navigation.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apps1)

        1.   Click **All apps**, and then click **Add**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#all_apps1)

        1.   Select **Managed Google Play app** from the **Select app type** drop-down menu, and then click **Select**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#managed)

        1.   In the **Managed Google Play**app store, search for and select **Zscaler Client Connector**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#search)

        1.   Click **Approve** to accept Zscaler Client Connector permissions in the **Managed Google Play** page.
        2.   Select **Keep approved when app requests new permissions** in the **Approval Settings** tab, and then click **Done**.
        3.   Click **Select**, and then click **Sync**to add Zscaler Client Connector.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#sync)

        1.   To configure the app for Android devices:
            1.   Navigate to **Apps**>**App configuration policies**.
            2.   On the **Basics** tab, configure the following parameters, and then click **Next**.

        *   **Name**:Enter`Zscaler Client Connector`.
        *   **Description**: (Optional) Enter a relevant description for Zscaler Client Connector.
        *   **Platform**: Select **Android Enterprise**.
        *   **Profile Type**: Select a relevant profile type based on your requirements. In this example, it's **Work Profile Only**.
        *   **Targeted app**: Click **Select app**, select**Zscaler Client Connector** from the **Associated app** window, and then click **OK**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#basic)

The **Device enrollment type**field is automatically set to **Managed devices**and is not editable.

        1.   On the **Settings**tab, select **Use configuration designer**as the **Configurations settings**format.
        2.   Click **Add**.
        3.   Add [Zscaler configuration parameters](https://help.zscaler.com/zscaler-client-connector/parameters-guide-zscaler-client-connector-android-and-android-chromeos) and their corresponding values per your organization's needs.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#parameters)

        1.   After you enter the appropriate values for the configuration keys that you selected, click **Next**.

        1.   On the **Assignments** tab, select the group assignments for which you want to assign the app configuration policy, and then click **Next**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#assignments)

        1.   On the **Review + create**tab, review the values and settings entered, and then click **Create**. Zscaler Client Connector is pushed to the devices in the group that you selected.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#review-create)

After Zscaler Client Connector is installed on users' devices, they must launch the app and log in to [enroll](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-service-zscaler-client-connector) in the Zscaler service.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#managed-google-play-app)

    *   [Configure Managed Google Play private app](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#managed-google-play-private-app)

        1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)In the Microsoft Intune admin center, click **Apps** in the left-side navigation.
        2.   Click **Add**.
        3.   Select **Managed Google Play app** from the **Select app type** drop-down menu, and then click **Select**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#private-app-add)

        1.   In the **Managed Google Play app** section, click the **Lock** icon in the left-side navigation.
        2.   Click the + icon located at the bottom-right of the screen.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#plus-icon)

        1.   In the **Private app** section:
            1.   **Title**: Enter a title for your file.
            2.   **APK file**: Upload the APK file. Contact Zscaler Support for a private APK file.
            3.   Click **Create**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#private-app)

The app can take up to 10 minutes to publish and appear in private apps.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#click-create)

        1.   Select the app you have created from the Android apps list.
        2.   (Optional) Click **Edit** next to **App information** and **Assignments** to make any changes to these sections.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#app-info)

        1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)On the **Review + create**tab, review the values and settings entered and save your settings.

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)To configure Always on VPN device restrictions for private apps, see [Configure Always On VPN](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#always-on-vpn).

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#managed-google-play-private-app)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#google-play-with-android-enterprise-enabled)

*   [From Google Play _without_ Android Enterprise enabled](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#google-play-without-android-enterprise-enabled)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)To deploy Zscaler Client Connector to Microsoft Intune from the Google Play Store for Android devices that are not Enterprise enabled:

    1.   In the Microsoft Intune admin center, click **Apps** in the left-side navigation.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#wo-enterprise)

    1.   Click **All apps**, and then click **Add**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apps2)

    1.   Select **Android store app** from the **Select app type** drop-down menu, and then click **Select**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#store-app)

    1.   To add the app from the Android store app:[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)
        1.   On the **App information** tab, provide the following Zscaler Client Connector details, and then click **Next**.

            *   **Name**: Enter `Zscaler Client Connector`.
            *   **Description**: Enter a relevant description for Zscaler Client Connector.
            *   **Publisher**: Enter `Zscaler Inc`.
            *   **Appstore URL**: Enter the following Google Play Store URL:

[https://play.google.com/store/apps/details?id=zscaler.com.zscaler](https://play.google.com/store/apps/details?id=zscaler.com.zscaler&hl=en_IN).
            *   **Minimum operating system**: Select **Android 8.0**.

The minimum operating system for Android on ChromeOS is **Android 6.0**.

            *   **Show this as a featured app in the Company Portal**: Select **Yes**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#add-app-not-enabled)

    1.   On the **Assignments**tab, select the group assignments for which you want to deploy Zscaler Client Connector, and then click **Next**. For more information on adding groups, refer to [Microsoft technical documentation](https://docs.microsoft.com/en-us/mem/intune/fundamentals/groups-add).

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#select-group-assignments)

    1.   On the **Review + create** tab, review the values and settings entered, and then click **Create**. Zscaler Client Connector is pushed to the devices in the group that you selected.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#review-create-tab2)

After Zscaler Client Connector is installed on users' devices, they must launch the app and log in to [enroll](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-service-zscaler-client-connector) in the Zscaler service.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#google-play-without-android-enterprise-enabled)

*   [As an APK file](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apk-file)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)To deploy Zscaler Client Connector to Microsoft Intune for Android devices as an APK file:

    *   [Configure using an App Package File](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#app-package-file)

        1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)In the Zscaler Admin Console, go to **Infrastructure**>**Common Resources**>**Deployment**>**Registered Devices**, and download the Zscaler Client Connector APK file.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#registered-devices)

Contact Zscaler Support to enable the APK file link.

        1.   In the Microsoft Intune admin center, click **Apps** in the left-side navigation.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apps4)

        1.   Click **All apps**, and then click **Add**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apk-all-apps)

        1.   Select **Line-of-business app** from the **Select app type** drop-down menu, and then click **Select**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apk-line-bus-app)

        1.   To add the app as an APK file:
            1.   On the **App information** tab, click **Select app package file**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apk-app-info)

        1.   Upload the Zscaler Client Connector APK file, and then click **OK**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apk-upload-file)

        1.   Provide the following Zscaler Client Connector details, and then click **Next**.

            *   **Name**: Enter `Zscaler Client Connector`.
            *   **Description**: Enter a relevant description for Zscaler Client Connector.
            *   **Publisher**: Enter `Zscaler Inc`.
            *   **Minimum operating system**: Select **Android 8.0**.
            *   **Show this as a featured app in the Company Portal**: Select **Yes**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#enter-details)

        1.   On the **Assignments**tab, select the group assignments for which you want to deploy Zscaler Client Connector, and then click **Next**. For more information on adding groups, see [Microsoft technical documentation](https://docs.microsoft.com/en-us/mem/intune/fundamentals/groups-add).

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apk-assign-tab)

        1.   On the **Review + create** tab, review the values and settings entered, and then click **Create**. Zscaler Client Connector is pushed to the devices in the group that you selected.

After Zscaler Client Connector is installed on users' devices, they must launch the app and log in to [enroll](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-service-zscaler-client-connector) in the Zscaler service.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#app-package-file)

    *   [Configure Always On VPN (Optional)](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#always-on-vpn)

[](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android)Zscaler Client Connector can restrict the traffic and secure the device before enrollment if **Always ON VPN** is enabled on your organization's MDM for Zscaler app. After the app is enrolled, it intercepts the traffic and forwards it according to the policies in the Zscaler Admin Console. To learn more, refer to [Android documentation](https://developer.android.com/work/dpc/network-telephony#about_always-on_vpn_connections).

        1.   In the Microsoft Intune admin center, go to **Devices**>**Android**.
        2.   Under **Android policies**, click **Configuration profiles**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#vpn1)

        1.   On the **Android Configuration profiles** page:
            1.   Click **Create profile**.
            2.   In the **Create a profile** section:
                1.   **Platform**: Enter `Android Enterprise`.
                2.   **Profile type**: Select **Device Restriction** from either the **Fully Managed, Dedicated, and Corporate-Owned Work Profile** or the **Personally-Owned Work Profiles**sections.

            3.   Click **Create**.

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#vpn2)

        1.   In the **Basics** section:

        *   **Name**: Enter a name.
        *   **Description**: (Optional) Enter a description.

        1.   Click **Next**.
        2.   In the **Configuration settings** section, expand the **Connectivity** section to complete the following steps and then click **Next**:

        *   **Always-on VPN**: Enable to allow Zscaler Client Connector to restrict the traffic and secure the device without enrollment.
        *   **VPN client**: Select **Custom**.
        *   **Package ID**: Enter your package ID (e.g., `zscaler.com.zscaler`).

[See image.](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#vpn3)

        1.   In the **Assignments** section, choose the users, groups, and devices for the profile.
        2.   Click **Next**.
        3.   In the **Review + create** section, review the summary, and click **Create**.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#always-on-vpn)

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-android#apk-file)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector -->

Title: Viewing Notifications on Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Viewing Notifications on Zscaler Client Connector

This article provides an overview of the **Notifications**window of Zscaler Client Connector.

*   [Windows](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)This section covers the following topics:

    *   [Viewing Notifications on Windows](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win-notification)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)In the Notifications window, you can view the following information:

        *   **Application**: Displays the name of the application relevant to the action that triggered the notification. In the following example, **Zscaler Client Connector** is listed in the **Application** column when the notification is relevant only to Zscaler Client Connector. For example, you received a notification after the app required you to reauthenticate into the Private Access service, in order to access internal applications. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization's cloud app control policies in the Internet & SaaS service, you would receive a notification and see Dropbox.com in the **Application** column.
        *   **Time**: Displays the time when the notification was received.
        *   **Message**:Displays the notification message.

Click **Clear All** to clear all notifications from this page.

![Image 2: Notifications Window](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-notifications-zscaler-client-connector-windows-0/zscaler-app-shown-notifications-windows_0.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win-notification)

    *   [Enabling Notifications in the System Tray](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#show-notifications-system-tray)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)To display notifications from the system tray icon:

        1.   Click **More**.
        2.   In the **Settings**section, you can:

        *   Manually enable or disable [pop-up notifications](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-pop-notifications). The default setting is set by the admin. If notifications are enabled, notifications appear in the tray icon. This setting doesn’t affect Private Access reauthentication notifications.
        *   Override the default setting that was configured in the Zscaler Admin Console to show Private Access reauthentication notifications.
        *   Pause notifications by enabling **Do Not Disturb (DND) Mode**. You can pause notifications in 30-minute, 1-hour, or 2-hour increments. If you use Zscaler Client Connector version 4.9, you can select **Enabled** to pause notifications indefinitely until you select **Disabled**.

![Image 3: Show notifications in Zscaler tray icon](https://help.zscaler.com/downloads/zscaler-client-connector/end-user-guide/viewing-notifications-zscaler-client-connector/zscaler-client-connector-more-window.png)

For Zscaler Client Connector version 3.8 and later for Windows, you can instead use Zscaler's Notification Framework. You cannot disable notifications in Windows settings. To learn more, see [About the Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework).

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#show-notifications-system-tray)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#win)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#macos)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)This section covers the following topics:

    *   [Viewing Notifications on macOS](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#macos-notification)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)In the Notifications window, you can view the following information:

        *   **Application**: Displays the name of the application relevant to the action that triggered the notification.

In the following example, **Zscaler Client Connector**is listed in the **Application**column because the notification was relevant only to Zscaler Client Connector. For example, you received the notification after disabling the app's Internet & SaaS service. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization's cloud app control policies, you would receive a notification and see Dropbox.com in the **Application** column.

        *   **Time**: Displays the time when the notification was received.
        *   **Message**:Displays the notification message.

Click **Clear All** to clear all notifications from this page.

![Image 4: Notifications Window](https://help.zscaler.com/downloads/z-app/end-user-guides/viewing-notifications-zscaler-client-connector/ZClient-Conn-notification-macos.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#macos-notification)

    *   [Enabling Notifications on the Menu Bar](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#show-notifications-menu-bar)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#macos)

*   [Linux](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#linux)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)This section covers the following topics:

    *   [Viewing Notifications on Linux](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#linux-notification)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)In the Notifications window, you can view the following information:

        *   **Application**: Displays the name of the application relevant to the action that triggered the notification.

**Zscaler**is listed in the **Application**column because the notification was relevant only to Zscaler Client Connector. For example, you received the notification after disabling the app's Internet & SaaS service. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization's cloud app control policies, you would receive a notification and see Dropbox.com in the **Application** column.

        *   **Time**: Displays the time when the notification was received.
        *   **Message**:Displays the notification message.

Click **Clear All** to clear all notifications from this page.

![Image 5: Notifications Window](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-notifications-zscaler-client-connector-draft-doc-50360/zscaler-client-connector-notifications-linux.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#linux-notification)

    *   [Enabling Notifications in the System Tray](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#show-notifications-linux-menu-bar)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#linux)

*   [Android](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#android)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)This section covers the following topics:

    *   [Viewing Notifications on Android](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#android-notification)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)In the Notifications screen, you can view the following information:

        *   The name of the application and the action that triggered it. For example, the following image shows a notification you’d receive after the app’s Private Access service started. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization’s cloud app control policies, you'd receive a notification with Dropbox.com listed.
        *   The notification message and the time when it was received.

Tap the **Delete** icon in the top-right corner to clear all notifications from this screen.

Use the drop-down menu to filter the notifications by the predefined time frames. You can view notifications from **Today**, the **Last 7 Days**, the **Last 10 Days**, or view **All** notifications.

![Image 6: The Notifications screen of Zscaler Client Connector for Android on ChromeOS](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-notifications-zscaler-client-connector-doc-55419/client-connector-notifications-android.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#android-notification)

    *   [Enabling Notifications on the App Icon](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#enable-notification-android)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)To display notifications on the app icon:

        1.   Tap **More**.
        2.   In the **Settings** section, you can:
            *   Manually enable or disable pop-up notifications. The default setting is set by the admin. If notifications are enabled, notifications appear on the app icon. This setting doesn’t affect Private Access reauthentication notifications.
            *   Override the default setting that was configured in the Zscaler Admin Console to enable or disable Private Access reauthentication notifications.

![Image 7: Enabling notifications for Android devices](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-notifications-zscaler-client-connector-doc-55419/Settings_Android.jpg)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#enable-notification-android)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#android)

*   [Android on ChromeOS](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#chrome)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)This section covers the following topics:

    *   [Viewing Notifications on Android on ChromeOS](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#chrome-notification)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)In the Notifications screen, you can view the following information:

        *   The name of the application and the action that triggered it. For example, the following image shows a notification you’d receive after the app’s Private Access service started. As another example, if you attempted to upload content to Dropbox.com in violation of one of your organization’s cloud app control policies, you'd receive a notification with Dropbox.com listed.
        *   The notification message and the time when it was received.

Tap the **Delete** icon in the top-right corner to clear all notifications from this screen.

Use the drop-down menu to filter the notifications by the predefined time frames. You can view notifications from **Today**, the **Last 7 Days**, the **Last 10 Days**, or view **All** notifications.

![Image 8: The Notifications screen of Zscaler Client Connector for Android on ChromeOS](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-notifications-zscaler-client-connector-doc-55419/client-connector-notifications-android.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#chrome-notification)

    *   [Enabling Notifications on the App Icon](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#enable-notification-chrome)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)To display notifications on the app icon:

        1.   Tap **More**.
        2.   In the **Settings** section, you can:
            *   Manually enable or disable menu bar notifications. The default setting is set by the admin. If notifications are enabled, you see notifications on the app icon.
            *   Override the default setting that was configured in the Zscaler Admin Console to enable or disable Private Access reauthentication notifications.

![Image 9: Enabling notifications for Android on ChromeOS devices](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-notifications-zscaler-client-connector-doc-55419/ChromeOS_More_Settings_Notifications.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#enable-notification-chrome)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#chrome)

*   [iOS](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#ios)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)This section covers the following topics:

    *   [Viewing Notifications on iOS](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#ios-notification)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)In the Notifications screen, you can view the following information:

        *   The name of the application relevant to the action that triggered it. In the following example, **Zscaler Client Connector Private Access** is listed because the notification was relevant only to Zscaler Client Connector. For example, you received the notification after the app’s Internet & SaaS service was turned on. For example, if you attempted to upload content to Dropbox.com in violation of one of your organization’s cloud app control policies, you would receive a notification and see Dropbox.com listed.
        *   The notification message and the time when it was received.

Tap the **Delete** icon in the top-right corner to clear all notifications from this screen.

![Image 10: Notifications Screen for iOS](https://help.zscaler.com/downloads/client-connector/end-user-guide/viewing-notifications-zscaler-client-connector/zscaler-client-connector-notifications-ios.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#ios-notification)

    *   [Enabling Notifications on the App Icon](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#enable-notification-ios)

[](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)To display notifications on the app icon:

        1.   Tap **More**.
        2.   In the **Settings** section, you can enable or disable app icon notifications.

![Image 11: Enabling notifications for iOS devices](https://help.zscaler.com/downloads/client-connector/end-user-guide/viewing-notifications-zscaler-client-connector/zscaler-client-connector-notificationsettings-ios.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#enable-notification-ios)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector#ios)

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

---

<!-- source: https://help.zscaler.com/zia/configuring-settings-zscaler-client-connector-based-euns -->

Title: Configuring Settings for Zscaler Client Connector-Based EUNs | Zscaler

URL Source: https://help.zscaler.com/zia/configuring-settings-zscaler-client-connector-based-euns

Markdown Content:
![Image 1: icon-zia.svg](https://help.zscaler.com/assets/images/icon-zia-hNrDwZ7t.svg)

## Internet & SaaS (ZIA)

### Configuring Settings for Zscaler Client Connector-Based EUNs

You can configure the settings available for [Zscaler Client Connector-based end user notifications](https://help.zscaler.com/zia/about-zscaler-client-connector-based-end-user-notifications) (EUNs), including company name, logo, support information, corporate policy page, support site, etc. In addition, you can enable user actions, such as notification snooze and exemption request to display them in the notifications.

To configure the EUN settings:

1.   Go to **Policies**>**Common Configurations**>**Resources**>**End User Notifications**>**Client Connector**.
2.   Click **Settings** in the top-right corner.

The **Settings** window appears.

3.   In the **Settings** window:

    1.   Under **Notification Actions**, select the actions that must appear in the notification displayed for the end users:
        *   **Learn More**: Opens the **Notifications** tab in Zscaler Client Connector. This option is enabled by default and is non-editable.
        *   **Do Not Disturb**: Opens the **More** tab in Zscaler Client Connector where the user can control the notification settings. To learn more, see [Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector](https://help.zscaler.com/client-connector/zscaler-endpoint-data-loss-prevention-dlp-integration-zscaler-client-connector).
        *   **Open Corporate Policy**: Opens the URL specified in the text field.
        *   **Open Containing Folder**: Opens the folder where files that triggered the block rule are quarantined. To learn more, see [Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector](https://help.zscaler.com/client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector).

This field is applicable only for Endpoint DLP policies that quarantine the original file.

        *   **Request Exemption**: Opens the **Data Protection** tab in Zscaler Client Connector where users can request to be exempted from the block rule enforcement. To learn more, see [Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector](https://help.zscaler.com/client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector).

This field is applicable only for Endpoint DLP rules with the Block action.

    2.   Under **General Details**, provide the following information about your organization:

        *   **Company Name**: Enter the company name that appears at the top of the notification.
        *   **Company Logo**: Upload a company logo that appears at the top of the notification. The logo must be a PNG file with a size limit of 20x20 pixels. The **Reset**option allows you to restore the default company Logo.

Any changes made within this section are mirrored in the [User Confirmation notification settings](https://help.zscaler.com/zia/configuring-settings-user-confirmation-notification-templates) as these configurations are shared by Zscaler Client Connector EUNs and User Confirmation notifications.

    3.   Under **Duration**, specify whether the notification must be closed automatically or manually by the user. If you choose the **Auto Close** option, you need to specify the duration after which the notification must be closed using the drop-down menu.
    4.   Under **Support Info**, provide your organization's contact information:
        *   **Email Address**: Enter your organization's email address where end users can reach out for technical support.
        *   **Phone Number**: Enter your organization's phone number where end users can reach out for technical support.
        *   **URL**: Enter the URL where end users can open a support request with your organization. The URL can contain a query string that uses one or more variables supported by Zscaler. These variables allow you to transmit values and populate them in the respective fields on the support request form opened by end users using the URL. For example, `https://support.example.com/path/to/CreateTicket?name=$zcc_user&url_accessed=$url` can take users to a web page for raising a ticket that's prepopulated with the user's name and the URL that triggered the policy.

[See the list of variables supported.](https://help.zscaler.com/zia/configuring-settings-zscaler-client-connector-based-euns#query-string-variables)

            *   [](https://help.zscaler.com/zia/configuring-settings-zscaler-client-connector-based-euns)$filename
            *   $destination
            *   $triggered_rule
            *   $action
            *   $url
            *   $url_category
            *   $app_activity
            *   $app_category
            *   $zcc_user

[Close](https://help.zscaler.com/zia/configuring-settings-zscaler-client-connector-based-euns#query-string-variables)

        *   **Link Text**: Enter the text for the hyperlink where end users can open a support request with your organization.

[See image.](https://help.zscaler.com/zia/configuring-settings-zscaler-client-connector-based-euns#eun-settings)

4.   Click **Save** and [activate the change](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

To learn how to customize the fields specific to individual Zscaler Client Connector-based EUNs, see the respective configuration articles.

---

<!-- source: https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments -->

Title: Implementing Zscaler Client Connector in No-Default Route Environments | Zscaler

URL Source: https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Implementing Zscaler Client Connector in No-Default Route Environments

You can deploy Zscaler Client Connector in a no-default route environment to provide a greater level of protection for end users connecting to the internet with minimal changes to the environment. In a typical network, the default route determines how traffic is forwarded to a specific destination when a route for that destination isn't available. The default route usually points to the edge router, and eventually out to the internet.

Some companies have deployed an alternate architecture, known as a no-default route environment, in their network. In such an environment, a default route isn't propagated through the network and the only way to egress is via an edge proxy server. Proxy servers are configured with a default route and placed in a neutral zone between an organization's internal network and the internet, which allows internet egress.

In a no-default route environment, browser traffic is explicitly sent to a proxy using a PAC file that is either served through a local web server, through the proxy itself, or through the use of Web Proxy Automatic Discovery. DNS resolution of public DNS records is restricted for all devices except the proxy server. The following image shows traffic flow in an explicit proxy environment.

![Image 2: Traffic flow in explicit proxy environment](https://help.zscaler.com/downloads/client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/explicit-proxy-network-flow.png)

In these explicit proxy environments, the web browser downloads the PAC file and sends the request to access the website explicitly to the on-premises proxy. The proxy server challenges the web browser for authentication. The browser resends the request to the proxy and includes the required credentials. The proxy validates the credentials with the IdP and queries the DNS server to resolve the hostname for the website. Upon successful DNS resolution, the proxy server sends a new request to the website on the internet via the edge firewall/router. The website responds back to the proxy with the requested content and the proxy server inspects the content and sends the website content to the web browser. Internal hosts are neither allowed to resolve external DNS records nor access the internet directly without sending the request via the proxy server.

![Image 3: Explicit proxy environment](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/explicit-proxy-network-flow_1.png)

## Zscaler Client Connector Traffic Flow in a Typical Environment

Prior to deploying Zscaler Client Connector in a no-default route environment, it is important to first understand how the client operates in a typical environment. The following image shows traffic flow for Zscaler Client Connector in a typical environment. It doesn't show all operations (e.g., SAML and PKI) as those operations have no bearing on this article.

![Image 4: Traffic flow in a typical environment](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/zscaler-client-connector-traffic-flow-in-typical-environments.png)

In a typical environment, Zscaler Client Connector communicates with the following entities:

**Operation****Entity****Communication Details**
Captive Portal Detection Zscaler Admin Console Probe to gateway.zscaler.net

Probe to gateway.[cloudname].net
Login Start Zscaler Admin Console API call to login.[cloudname].net
Name Resolution Internal DNS Servers DNS resolution requests
Policy Download Zscaler Admin Console

Zscaler PAC Server API call to mobile.[cloudname].net

Download App Profile PAC from pac.[cloudname].net
Service Discovery & Enrollment Zscaler Admin Console API call to gateway.[cloudname].net

API call to mobile.[cloudname].net
Tenant Cloud Discovery Zscaler Admin Console Probe to mobile.zscaler.net
Internet & SaaS Authentication Customer IdP

Internet & SaaS SAML SP SAML call to customer IdP

SAML call to Internet & SaaS (SP)
Internet & SaaS Traffic Forwarding Public/Private Service Edge for Internet & SaaS Forwarding using Tunnel with Local Proxy, Z-Tunnel 1.0 & 2.0
Private Access Authentication Customer IdP

Private Access SAML SP SAML call to customer IdP

SAML call to Private Access (SP)
Private Access Traffic Forwarding Broker/Public/Private Service Edge for Private Access Forwarding using Microtunnel (M-Tunnel)

## Configuration Requirements for Implementing Zscaler Client Connector in a No-Default Route Environment

You can deploy Zscaler Client Connector in no-default route environments to support different kinds of traffic forwarding requirements. Zscaler Client Connector supports operation in a no-default environment on the following platforms:

*   Windows
*   macOS
*   Linux
*   iOS
*   Android
*   Android on ChromeOS

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)At a high level, you must configure the following requirements to enable Zscaler Client Connector to communicate with Zscaler cloud in a no-default route environment:

**Requirement****Description**
System PAC For initial Service Discovery, Enrollment, Authentication and Policy and PAC Download using an Explicit Proxy IP address
Internal DNS Server Records Spoofed Zscaler records pointing to Zscaler global virtual IP (GVIP) or an internal NAT address so Zscaler Client Connector can resolve Zscaler cloud addresses
GRE/IPSec Tunnel To provide connectivity to the Zscaler cloud during initial configuration of Zscaler Client Connector and traffic forwarding for Internet & SaaS and Private Access. Tunnel isn't used for traffic forwarding if a Private Service Edge is present
Private Service Edge for Internet & SaaS (optional)You can deploy a Private Service Edge for Internet & SaaS to forward traffic directly to the internet from the customer location. If Z-Tunnel 2.0 forwarding is required from on-premises, then a Private Service Edge for Internet & SaaS is highly recommended
App and Forwarding Profile Settings You must configure App and Forwarding Profiles to support the environment with the use of **Tunnel Internal Client Connector Traffic** and other features
Third-Party Proxy (optional)You must configure explicit proxies to bypass authentication to the IdP and Zscaler cloud, must not attempt to re-resolve DNS based on Server Name Indication (SNI) and disable SSL inspection

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Zscaler Client Connector supports forwarding of different types of traffic in no-default environments. The following table includes the requirements for each type of traffic forwarded by Zscaler Client Connector. The configuration details for each scenario are covered in this article.

**Scenario****Internet & SaaS Traffic Type****Zscaler Client Connector Forwarding Method****System PAC File****DNS Records****GRE/IPSec Tunnel****Third-Party Proxy****Zscaler Private/**

**Virtual Service Edge****Private Access**
1 HTTP/

HTTPS Tunnel w/ Local Proxy Required Required Required Supported Supported Supported
2 TCP 80, 443 Z-Tunnel 1.0 Required Required Required Supported Supported Supported
3 All ports/

protocols Z-Tunnel 2.0 Required Required Required*N/A Required Supported
4 All ports/

protocols Z-Tunnel 2.0 Required Required Required**Required N/A Supported

* GRE/IPSec tunnels used for all traffic except for Internet & SaaS traffic forwarding

** GRE/IPSec tunnels used for Service Discovery, Enrollment, and initial authentication only

## Common Configuration Steps for All No-Default Route Environments

The following image shows the Zscaler Client Connector traffic flow during Service Discovery, Enrollment, and Initialization in no-default route environments.

![Image 5: Traffic flow during service discovery](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/client-connector-traffic-flow-no-default.png)

The following image shows Zscaler Client Connector using the Zscaler GVIP as an Explicit Proxy for Service Discovery, Enrollment, and Initialization in no-default route environments.

![Image 6: Zscaler Client Connector using the Zscaler GVIP as an Explicit Proxy](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/client-connector-zscaler-gvip.png)

The following image shows that Zscaler Client Connector can use an internal IP as an Explicit Proxy for Service Discovery, Enrollment, and Initialization in no-default route environments. An edge device performs Destination NAT to the Zscaler GVIP.

![Image 7: Zscaler Client Connector can use an internal IP as an Explicit Proxy](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/client-connector-internal-ip.png)

No External Route Networks: Certain networks don't allow injection of public IP prefixes in internal routing tables. In these networks, an internal IP address can be used instead of the Zscaler GVIP with an upstream router/firewall performing Destination NAT and translating the internal address to the Zscaler GVIP. In these environments, the spoofed Zscaler DNS records must reference the internal IP instead of the Zscaler GVIP.

You must configure the following high-level steps to support Zscaler Client Connector in all no-default environments, regardless of traffic type and forwarding method. These steps allow Zscaler Client Connector to perform service discovery and enrollment when using Zscaler GVIPs.

*   [1. Create GRE/IPSec tunnel configurations in Internet & SaaS and set up the tunnels from a router or firewall to the closest Zscaler data centers.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#create-gre-ipsec)
*   [2. Create a new location in Internet & SaaS.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#new-location)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Follow the steps in [Configuring Locations](https://help.zscaler.com/zia/configuring-locations) to configure a new location in Internet & SaaS. The **Enforce Authentication**option must be **Disabled**for the newly created location.

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#new-location) 
*   [3. Determine the Zscaler GVIP address to use as the Explicit Proxy IP address and set up routing.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#determine-gvip)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Determine the Zscaler GVIP address Zscaler Client Connector connects to by selecting one from the bottom of the page at [https://config.zscaler.com/[cloudname].net/cenr](https://config.zscaler.com/%5Bcloudname%5D.net/cenr.). For example, if your tenant is hosted in the zscalertwo cloud, use the configuration information from [https://config.zscaler.com/zscalertwo.net/cenr.](https://config.zscaler.com/zscalertwo.net/cenr.) The Zscaler GVIP address is located at the bottom of the page. The following image shows a list of GVIPs that you can use for forwarding traffic across the GRE/IPSec tunnel. This article uses 185.46.212.88 for all configurations except when forwarding traffic to a Private Service Edge for Internet & SaaS.

![Image 8: GVIPs used for forwarding traffic](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/zscaler-GVIP.png)

Create a route to forward traffic to the Zscaler GVIP via the GRE/IPSec tunnels.

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#determine-gvip) 
*   [4. Create required spoofed Zscaler DNS records in the internal DNS server.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#create-spoofed)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Using 185.46.212.88 as the Zscaler VIP, create the following DNS Type A records in the internal DNS server.

**DNS Record****Record Type****IP Address****Example Records for ZS2**
gateway.[cloudname].net A 185.46.212.88 gateway.zscalertwo.net
gateway.zscaler.net A 185.46.212.88 gateway.zscaler.net
login.[cloudname].net A 185.46.212.88 login.zscalertwo.net
mobile.[cloudname].net A 185.46.212.88 mobile.zscalertwo.net
mobile.zscaler.net A 185.46.212.88 mobile.zscaler.net
pac.[cloudname].net A 185.46.212.88 pac.zscalertwo.net 
Certain networks don't allow injection of public IP prefixes in internal routing tables. In those scenarios, you can use an internal IP address for DNS records instead of the Zscaler GVIP with an upstream router/firewall performing Destination NAT and translating the internal address to the Zscaler GVIP.

The following table shows the DNS records when an internal IP address is used instead of a Zscaler GVIP address.

**DNS Record****Record Type****IP Address (No External Route Environments)****Example Records for ZS2**
gateway.zscaler.net A 10.10.10.10 gateway.zscaler.net
mobile.zscaler.net A 10.10.10.10 mobile.zscaler.net
gateway.[cloudname].net A 10.10.10.10 gateway.zscalertwo.net
mobile.[cloudname].net A 10.10.10.10 mobile.zscalertwo.net
login.[cloudname].net A 10.10.10.10 login.zscalertwo.net
pac.[cloudname].net A 10.10.10.10 pac.zscalertwo.net 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#create-spoofed) 
*   [5. Create a System PAC file in Zscaler Admin Console and assign the PAC URL to internal devices using GPO or WPAD.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#create-system-pac-file)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)A System PAC file is required for initial discovery, enrollment, authentication, app and forwarding profile policy and PAC file downloads. The System PAC enables Zscaler Client Connector to communicate with the Zscaler cloud in an explicit proxy mode. You can use a Zscaler GVIP address as the explicit proxy address or you can use a third-party proxy, as long as it meets the [configuration requirements](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#config-requirements). After enrollment, the System PAC is no longer used by Zscaler Client Connector. Zscaler Client Connector restores the System PAC upon exit.

To create a hosted PAC file in Internet & SaaS, see [About Hosted PAC Files](https://help.zscaler.com/zia/about-hosted-pac-files).

The following hosted System PAC file references the Zscaler GVIP [previously mentioned](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#determine-gvip)and is specific to the Zscaler Two cloud.

[See PAC file.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#pac-file-1)

```
function FindProxyForURL(url, host) {
   var egressip = "${SRCIP}";
   var privateIP = /^(0|10|127|192\.168|172\.1[6789]|172\.2[0-9]|172\.3[01]|169\.254|192\.88\.99)\.[0-9.]+$/;
   var resolved_ip = dnsResolve(host);
   // Bypass RFC-1918 and internal domain
   if ((shExpMatch(host, "*.internaldomain.com")) || (privateIP.test(resolved_ip)))
       return "DIRECT";
    
   // Zscaler public egress subnets
   if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
       (isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
       (isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
       (isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
       (isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
       (isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
       (isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
       (isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
       (isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
       (isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
       (isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
       (isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
       (isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
       (isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
       (isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
       (isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
       (isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
       (isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
       (isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
       {
           if ((dnsDomainIs(host, "1.2.3.4")) || (shExpMatch(host, "*.zscaler.net")) ||
               (shExpMatch(host, "*.zscalertwo.net")) || //Service Discovery, Enrollment, Login
               (shExpMatch(host, "mobilesupport.zscaler.com")) || //Support
               (shExpMatch(host, "*.zdxcloud.net")) || //ZDX
               (shExpMatch(host, "*.digicert.com")) || //Cert validation
               // Required for ZPA
               (shExpMatch(host, "*.prod.zpath.net")) || (shExpMatch(host, "*.private.zscaler.com")) ||
               // Zscaler Updates
               (localHostOrDomainIs(host, "d32a6ru7mhaq0c.cloudfront.net")) || (localHostOrDomainIs(host, "d3l44rcogcb7iv.cloudfront.net")) ||
               (localHostOrDomainIs(host, "dwv281inkfqg3.cloudfront.net")) ||
               // Required for Entra IdP
               (shExpMatch(host, "*.msauth.net")) || (shExpMatch(host, "*.msauthimages.net")) ||
               (shExpMatch(host, "*.msftauthimages.net")) || (localHostOrDomainIs(host, "login.microsoftonline.com")) ||
               (localHostOrDomainIs(host, "autologon.microsoftazuread-sso.com")) || (localHostOrDomainIs(host, "login.windows.net")) ||
               // Required for OKTA
               (shExpMatch(host, "*.okta.com")) || (shExpMatch(host, "*.oktacdn.com")) || (shExpMatch(host, "*.oktapreview.com")))
           {
               return "PROXY 185.46.212.88:80";
           }
       }
 return "DIRECT";
}
```

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#pac-file-1) 
You can use the System PAC file for devices that are on On-Trusted Networks (with a GRE/IPSec tunnel) or Off-Trusted Networks (remote users). The PAC file logic detects the device location using the `${SRCIP}` macro. If the device is at a location with an existing GRE/IPSec tunnel, it uses the Zscaler GVIP as the Explicit Proxy IP address in order to connect to Zscaler and if the device is on an Off-Trusted Network, it communicates directly with the closest Zscaler data center.

The following table shows the System PAC file code details:

**PAC File Code Details****Notes**
`var egressip = "${SRCIP}";`Determines the client's public IP address using the ${SRCIP} macro which is only available when the PAC file is hosted in the Zscaler cloud.
```
if ((shExpMatch(host, "*.internaldomain.com")) || (privateIP.test(resolved_ip)))
return "DIRECT";
```Don't use proxy for internal domains and RFC-1918 addresses. Replace `internaldomain.com` with your internal domains.
```
// Zscaler public egress subnets
if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
(isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
(isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
(isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
(isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
(isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
(isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
(isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
(isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
(isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
(isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
(isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
(isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
(isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
(isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
(isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
(isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
(isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
(isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
```List of Zscaler public IP addresses to detect whether Zscaler Client Connector is accessing the Zscaler cloud using a GRE/IPSec tunnel. These addresses are documented in the Zscaler Aggregate IP Address Ranges section: [https://config.zscaler.com/[cloudname].net/cenr](https://config.zscaler.com/%5Bcloudname%5D.net/cenr)

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#zscaler-ip-addresses)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)![Image 9: Zscaler aggregate IP address ranges](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/Common-config-PAC-file-code-details-public-egress.png)[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#zscaler-ip-addresses)
`if ((dnsDomainIs(host, "1.2.3.4")) || (shExpMatch(host, "*.zscaler.net")) ||`Special URL checked by Zscaler Client Connector to enroll using an explicit proxy, in this case the Zscaler GVIP (185.46.212.88).
`(shExpMatch(host, "*.zscalertwo.net")) || //Service Discovery, Enrollment, Login`Replace the wildcard domain with the one that matches the customer cloud.
```
(shExpMatch(host, "mobilesupport.zscaler.com")) || //Support
(shExpMatch(host, "*.zdxcloud.net")) || //ZDX
(shExpMatch(host, "*.digicert.com")) || //Cert validation
```Required for support access, Zscaler Digital Experience (ZDX) and certificate validation during discovery and enrollment.
`(shExpMatch(host, "*.prod.zpath.net")) || (shExpMatch(host, "*.private.zscaler.com")) ||`Required for Private Access discovery and enrollment.
```
(localHostOrDomainIs(host, "d32a6ru7mhaq0c.cloudfront.net")) || (localHostOrDomainIs(host, "d3l44rcogcb7iv.cloudfront.net")) ||
(localHostOrDomainIs(host, "dwv281inkfqg3.cloudfront.net")) ||
```Required for Zscaler Client Connector software and module updates during discovery and enrollment.
```
(shExpMatch(host, "*.msauth.net")) || (shExpMatch(host, "*.msauthimages.net")) ||
(shExpMatch(host, "*.msftauthimages.net")) || (localHostOrDomainIs(host, "login.microsoftonline.com")) ||
(localHostOrDomainIs(host, "autologon.microsoftazuread-sso.com")) || (localHostOrDomainIs(host, "login.windows.net")) ||
```Required for authentication during enrollment. The example shows domains required by Entra. Use domains required for your IdP.
```
{
return "PROXY 185.46.212.88:80";
}
```If Zscaler Client Connector is at a no-default route location, it uses the Zscaler GVIP 185.46.212.88 as the explicit proxy address for service discovery, enrollment, authentication, and policy and pac downloads.

Instead of using the Zscaler GVIP, use the internal IP address, e.g., 10.10.10.10 for No External Route Networks.
```
return "DIRECT";
}
```All other traffic is sent directly to the local network. 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#create-system-pac-file) 
*   [6. Exempt IdP URLs from authentication in Internet & SaaS.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#exempt-idp-urls)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Zscaler Client Connectormust be able to authenticate with the IdP in order for enrollment and policy downloads to succeed. To ensure that Zscaler Client Connector is able to authenticate, you must add the IdP URLs to the Authentication Exemptions list in Internet & SaaS.

    1.   Follow the steps in [Exempting URLs and Cloud Apps from Authentication](https://help.zscaler.com/zia/exempting-urls-cloud-apps-authentication) to configure the authentication exemptions list.

The IdP URLs depend on the type of IdP in use. Consult your IdP documentation for details. The following example shows IdP URLs for Microsoft Entra ID and Okta.

    2.   Add the IdP and other required URLs to the Exempted URLs list.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#exempted-urls)

The following are example URLs for Entra ID and Okta.

**IdP/Service****URL**
Entra ID login.microsoftonline.com
autologon.microsoftazuread-sso.com
*.windowsazure.com
device.login.microsoftonline.com
login.microsoft.com
login.windows.net
Okta*.okta.com
*.oktacdn.com
*.oktapreview.com
Private Access*.zpath.net
*.private.zscaler.com

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#exempt-idp-urls)

## Scenarios: Types of Traffic Forwarded by Zscaler Client Connector

*   [Scenario 1 - Forwarding Internet & SaaS HTTP/HTTPS Traffic Using Tunnel with Local Proxy and Private Access Traffic](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)The following image shows Zscaler Client Connector forwarding Internet & SaaS and Private Access traffic via the Zscaler GVIP IP.

![Image 10: Zscaler Client Connector forwarding Internet & SaaS and Private Access traffic via the Zscaler GVIP](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/scenario-1-client-connector-forwards-traffic_0.png)

In this scenario, after enrollment has completed, Zscaler Client Connector forwards Internet & SaaS HTTP/HTTPS using TWLP/Z-Tunnel 1.0 and Private Access traffic using M-Tunnels to the Zscaler GVIP. The Zscaler GVIP in turn forwards the traffic to Internet & SaaS and Private Access services in the Zscaler cloud. You can optionally use a third-party proxy in this scenario as long as it meets the [configuration requirements](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#config-requirements) and forwards the traffic through the GRE/IPSec tunnel. The steps in this scenario do not cover configuration using a third-party proxy as third-party proxies aren't required.

You must configure the following high-level steps to forward HTTP/HTTPS traffic by Zscaler Client Connector using Tunnel with Local Proxy (TWLP) in No-Default Environments.

    *   [1. Complete Common Configuration Requirements for All No-Default Route Environments](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-common-config-for-no-default)
    *   [2. Create a Hosted Forwarding Profile PAC File for Tunnel with Local Proxy (TWLP)](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-create-forwarding-profile-pac-file)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Follow the steps for [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia) to add a custom PAC file. Use the following PAC file.

[See PAC file.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-pac-file)

```
function FindProxyForURL(url, host) {
   if ((dnsDomainIs(host, "1.2.3.4")) ||
       (shExpMatch(host, "*.prod.zpath.net")))
     return "PROXY 185.46.212.88:80";
   return "PROXY ${ZAPP_LOCAL_PROXY}";
}
```

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-pac-file) 
See the following table for forwarding PAC file details.

**PAC File Code Details****Notes**
`if ((dnsDomainIs(host, "1.2.3.4")) ||`Special URL checked by Zscaler Client Connector to enroll using an Explicit proxy, in this case the Zscaler GVIP (185.46.212.88).
`(shExpMatch(host, "*.prod.zpath.net")))`Required for Private Access discovery and enrollment.
`return "PROXY 185.46.212.88:80";`If Zscaler Client Connector is at a no-default route location, it uses the Zscaler GVIP 185.46.212.88 as the explicit proxy address to complete discovery and enrollment.

Instead of using the Zscaler GVIP, use the internal IP address, e.g., 10.10.10.10 for No External Route Networks.
`return "PROXY ${ZAPP_LOCAL_PROXY}";`Send all other traffic to Zscaler Client Connector. 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-create-forwarding-profile-pac-file) 
    *   [3. Create a Forwarding Profile to Use Tunnel with Local Proxy for On-Trusted Network and Use Z-Tunnel 2.0 for Off-Trusted Network](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-profile-on-off-trusted)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)To create a forwarding profile for On-Trusted and Off-Trusted networks:

        1.   Go to **Infrastructure**>**Connectors**>**Client**>**Forwarding Profile for Platforms**.
        2.   Click **Add Forwarding Profile**.

The **Add Forwarding Profile** window appears.
        3.   Enter a name for the profile.
        4.   For **Predefined Trusted Networks**, choose the **Selected** option. This allows Zscaler Client Connector to detect when it is in a no-default-route environment. To learn more about trusted networks, see [Configuring Trusted Networks for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector).

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-edit-forwarding-profile)

        1.   For **On-Trusted Network**, select **Tunnel with Local Proxy**.
        2.   For **Proxy Action Type**, select **Enforce**.
        3.   Select the **PAC URL Location** and choose **PAC URL**.
        4.   In the **Custom PAC URL** section, enter the PAC URL for the forwarding profile.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-edit-forwarding-profile-cont)

        1.   For **Off-Trusted Network**, select the forwarding mechanisms as per your organization's requirements. Zscaler supports all forwarding mechanism for off-trusted network users. The following example uses Z-Tunnel 2.0.
        2.   For **Off-Trusted Network**, choose **Tunnel**.
        3.   For **Tunnel version selection**, select **Z-Tunnel 2.0**.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-profile-off-trusted)

        1.   Enable **Redirect Web Traffic to Zscaler Client Connector Listening Proxy**.

A PAC URL is not required for an Off-Trusted Network.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-profile-off-trusted-1)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-profile-on-off-trusted)

    *   [4. Create a Hosted App Profile PAC File for Tunnel with Local Proxy (TWLP)](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-create-hostel-tunnel-local-proxy-app-profile-pac-file)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Follow the steps for [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia) to add a custom PAC file. Use the following PAC file:

[See PAC file.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-pac-file)

```
function FindProxyForURL(url, host) {
   var customPort = "${ZS_CUSTOM_PORT}"; // Assigned port for local region - ZS_CUSTOM_PORT
   var privateIP = /^(0|10|127|192\.168|172\.1[6789]|172\.2[0-9]|172\.3[01]|169\.254|192\.88\.99)\.[0-9.]+$/;
   var resolved_ip = dnsResolve(host);
   var trust = /^(trust|ips).(zscaler|zscalerone|zscalertwo|zscalerthree|zsdemo|zscalergov|zscloud|zsfalcon|zdxcloud|zdxpreview).(com|net)$/;
   var iam = /^.*.(zslogin|zsloginbeta|zslogindemo).net$/;
   var egressip = "${SRCIP}";
    // Don't send non-FQDN or private IP auths to us
   if (isInNet(resolved_ip, "192.0.2.0", "255.255.255.0") || privateIP.test(resolved_ip))
       return "DIRECT";
   // Zscaler public egress subnets
   if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
       (isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
       (isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
       (isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
       (isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
       (isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
       (isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
       (isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
       (isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
       (isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
       (isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
       (isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
       (isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
       (isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
       (isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
       (isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
       (isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
       (isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
       (isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
       return "PROXY 185.46.212.88:80";
   if (trust.test(host) || iam.test(host) || /^trust.zscaler.us$/.test(host) || /^config.zscaler.com$/.test(host))
       return "DIRECT";
   // Bypasses for ZPA
   if ((isInNet(resolved_ip, "100.64.0.0", "255.255.0.0")) || (shExpMatch(host, "*.private.zscaler.com")) || (shExpMatch(host, "*.zpath.net")))
       return "DIRECT";
   return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
}
```

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-pac-file) 
Use the following PAC file code details:

**PAC File Code Details****Notes**
`var egressip = "${SRCIP}";`Determines the client's public IP address using the ${SRCIP} macro which is only available when the PAC file is hosted in the Zscaler cloud.
```
if ((shExpMatch(host, "*.internaldomain.com")) || (privateIP.test(resolved_ip)))
return "DIRECT";
```Don't use proxy for internal domains and RFC-1918 addresses. Replace `internaldomain.com` with your internal domains.
```
// Zscaler public egress subnets
if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
	(isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
	(isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
	(isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
	(isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
	(isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
	(isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
	(isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
	(isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
	(isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
	(isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
	(isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
	(isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
	(isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
	(isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
	(isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
	(isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
	(isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
	(isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
        return "PROXY 185.46.212.88:80";
```List of Zscaler public IP addresses to detect whether Zscaler Client Connector is accessing the Zscaler cloud using a GRE/IPSec tunnel. These addresses are documented in the Zscaler Aggregate IP Address Ranges section: [https://config.zscaler.com/[cloudname].net/cenr](https://config.zscaler.com/%5Bcloudname%5D.net/cenr)

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-zscaler-ip-addresses)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)![Image 11: Zscaler aggregate IP address ranges](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/Scenario-1-Common-config-PAC-file-code-details-public-egress.png)[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-zscaler-ip-addresses)
```
{
return "PROXY 185.46.212.88:80";
}
```If Zscaler Client Connector is at a no-default route location, it uses the Zscaler GVIP 185.46.212.88 as the explicit proxy address for service discovery, enrollment, authentication, and policy and pac downloads.

Instead of using the Zscaler GVIP, use the internal IP address, e.g., 10.10.10.10 for No External Route Networks.
```
if (trust.test(host) || iam.test(host) || /^trust.zscaler.us$/.test(host) || /^config.zscaler.com$/.test(host))
        return "DIRECT";
    // Bypasses for ZPA
    if ((isInNet(resolved_ip, "100.64.0.0", "255.255.0.0")) || (shExpMatch(host, "*.private.zscaler.com")) || (shExpMatch(host, "*.zpath.net")))
        return "DIRECT";
```When Zscaler Client Connector is on an Off-Trusted Network use this criteria to bypass Zscaler.
```
return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
}
```Use auto-assigned data centers to connect via Z-Tunnel 2.0 when on an Off-Trusted Network. 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-create-hostel-tunnel-local-proxy-app-profile-pac-file) 
    *   [5. Create an App Profile](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-create-app-profile)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Create an App Profile and associate it to the [previously created Forwarding Profile](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-profile-on-off-trusted):

        1.   Go to **Infrastructure**>**Connectors**>**Client**.
        2.   Under Platform Settings, select the OS platform you want to add the policy to. The following example uses Windows.
        3.   On the **App Profiles** tab, click **Add Windows Policy**. The **Add Windows Policy** window appears.
        4.   For**Forwarding Profile**, choose the forwarding profile [previously created](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-forwarding-profile-on-off-trusted).

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-app-profile-general)

        5.   In the **PAC and Proxy** section, enter the URL in the **Custom PAC URL** section.
        6.   In **Proxy Configuration**, enable **Disable Loopback Restriction**.
        7.   Enable the **Cache System Proxy**. When enabled, this option restores the System PAC after the user exits Zscaler Client Connector.
        8.   **Override WPAD**:(Optional) Enable this option if WPAD is in use.
        9.   **Restart WinHTTP Service**:(Optional) Enable this option if WPAD is in use.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-app-profile-pac-proxy)

        10.   In the **Advanced** section, enable **Tunnel Internal Client Connector Traffic**.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-app-profile-advanced)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1-create-app-profile)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-1)

*   [Scenario 2 - Forwarding Internet & SaaS TCP 80/443 Traffic Using Z-Tunnel 1.0 and Private Access Traffic](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)In this scenario, Zscaler Client Connector forwards Internet & SaaS TCP 80/443 traffic using Z-Tunnel 1.0 and Private Access traffic using M-Tunnels to the Zscaler GVIP. The Zscaler GVIP, in turn, forwards the traffic to Internet & SaaS and Private Access services in the Zscaler cloud. In this scenario, you can optionally use a third-party proxy as long as it meets the [requirements](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-table) and forwards the traffic through the GRE/IPSec tunnel. The following directions don't cover configuration using a third-party proxy because third-party proxies aren't required and can be deprecated in this scenario.

![Image 12: Zscaler Client Connector forwards Internet & SaaS TCP 80/443 traffic using Z-Tunnel 1.0 and Private Access traffic using M-Tunnels](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/Scenario-2-Zscaler-Fwd-Traffic_0.png)

The following high-level steps must be configured to forward TCP 80/443 traffic by Zscaler Client Connector using Z-Tunnel 1.0 in no-default Environments.

    *   [1. Complete common configuration requirements for all no-default route environments](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-common-config-requirements)
    *   [2. Create a hosted Z-Tunnel 1.0 Forwarding Profile PAC file](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-forwarding-profile-pac-file)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Follow the steps for [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia) to add a custom PAC file. Use the following PAC file.

[See PAC file.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-pac-file)

```
function FindProxyForURL(url, host) {
   if ((dnsDomainIs(host, "1.2.3.4")) ||
       (shExpMatch(host, "*.prod.zpath.net")))
     return "PROXY 185.46.212.88:80";
   return "PROXY ${ZAPP_LOCAL_PROXY}";
}
```

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-pac-file) 
Refer to the following table for forwarding PAC file details.

**PAC File Code Details****Notes**
`if ((dnsDomainIs(host, "1.2.3.4")) ||`Special URL checked by Zscaler Client Connector to enroll using an Explicit proxy, in this case the Zscaler GVIP (185.46.212.88).
`(shExpMatch(host, "*.prod.zpath.net")))`Required for Private Access discovery and enrollment.
`return "PROXY 185.46.212.88:80";`If Zscaler Client Connector is at a no-default route location, it uses the Zscaler GVIP 185.46.212.88 as the explicit proxy address to complete discovery and enrollment.

Instead of using the Zscaler GVIP, use the internal IP address, e.g., 10.10.10.10 for No External Route Networks.
`return "PROXY ${ZAPP_LOCAL_PROXY}";`Send all other traffic to Zscaler Client Connector. 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-forwarding-profile-pac-file) 
    *   [3. Create a Forwarding Profile to use Z-Tunnel 1.0 when On-Trusted Network and use Z-Tunnel 2.0 when Off-Trusted Network](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-forwarding-profile-off-on-trusted)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Create a [Trusted Network Criteria](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)so Zscaler Client Connector can detect when it is on an On-Trusted Network (no-default route environment). Follow these steps to create forwarding profile:

        1.   Go to **Infrastructure**>**Connectors**>**Client**>**Forwarding Profile for Platforms**.
        2.   Click **Add Forwarding Profile**.

The **Add Forwarding Profile** window appears.
        3.   Enter a name for the profile.
        4.   For **Predefined Trusted Networks**, choose the **Selected** option. This allows Zscaler Client Connector to detect when it is in a no-default-route environment. To learn more about trusted networks, see [Configuring Trusted Networks for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector).

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-edit-forwarding-profile)

        1.   For **On-Trusted Network**, select **Tunnel**.
        2.   For Tunnel version selection, select **Z-Tunnel 1.0**.
        3.   For **Proxy Action Type**, select **Enforce**.
        4.   Select the **PAC URL Location** and choose **PAC URL**.
        5.   In the **Custom PAC URL** section, enter the PAC URL for the forwarding profile.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-edit-forwarding-profile-cont)

        1.   For **Off-Trusted Network**, select the forwarding mechanisms as per your organization's requirements. Zscaler supports all forwarding mechanism for Off-Trusted network users. The following example uses Z-Tunnel 2.0.
        2.   For **Tunnel version selection**, select **Z-Tunnel 2.0**.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-edit-forwarding-profile-off-trusted-network)

        1.   Enable **Redirect Web Traffic to Zscaler Client Connector Listening Proxy**.

A PAC URL is not required for an Off-Trusted Network.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-edit-forwarding-profile-cont-2)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-forwarding-profile-off-on-trusted)

    *   [4. Create a hosted App Profile PAC file](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-hosted-app-profile-pac-file)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Follow the steps for [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia) to add a custom PAC file. Use the following PAC file:

[See PAC file.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-use-app-pac-file)

```
function FindProxyForURL(url, host) {
 
    var customPort = "${ZS_CUSTOM_PORT}"; // Assigned port for local region - ZS_CUSTOM_PORT
    var privateIP = /^(0|10|127|192\.168|172\.1[6789]|172\.2[0-9]|172\.3[01]|169\.254|192\.88\.99)\.[0-9.]+$/;
    var resolved_ip = dnsResolve(host);
    var trust = /^(trust|ips).(zscaler|zscalerone|zscalertwo|zscalerthree|zsdemo|zscalergov|zscloud|zsfalcon|zdxcloud|zdxpreview).(com|net)$/;
    var iam = /^.*.(zslogin|zsloginbeta|zslogindemo).net$/;
    var egressip = "${SRCIP}";
 
     // Don't send non-FQDN or private IP auths to us
    if (isInNet(resolved_ip, "192.0.2.0", "255.255.255.0") || privateIP.test(resolved_ip))
        return "DIRECT";
 
    // Zscaler public egress subnets
    if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
        (isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
        (isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
        (isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
        (isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
        (isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
        (isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
        (isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
        (isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
        (isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
        (isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
        (isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
        (isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
        (isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
        (isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
        (isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
        (isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
        (isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
        (isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
        return "PROXY 185.46.212.88:80";
 
    if (trust.test(host) || iam.test(host) || /^trust.zscaler.us$/.test(host) || /^config.zscaler.com$/.test(host))
        return "DIRECT";
 
    // Bypasses for ZPA
    if ((isInNet(resolved_ip, "100.64.0.0", "255.255.0.0")) || (shExpMatch(host, "*.private.zscaler.com")) || (shExpMatch(host, "*.zpath.net")))
        return "DIRECT";
 
    return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
}
```

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-use-app-pac-file) 
Refer to the following table for forwarding PAC file details.

**PAC File Code Details****Notes**
`var egressip = "${SRCIP}";`Determines the client's public IP address using the ${SRCIP} macro which is only available when the PAC file is hosted in the Zscaler cloud.
```
if ((shExpMatch(host, "*.internaldomain.com")) || (privateIP.test(resolved_ip)))
return "DIRECT";
```Don't use proxy for internal domains and RFC-1918 addresses. Replace `internaldomain.com` with your internal domains.
```
// Zscaler public egress subnets
if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
	(isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
	(isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
	(isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
	(isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
	(isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
	(isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
	(isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
	(isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
	(isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
	(isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
	(isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
	(isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
	(isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
	(isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
	(isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
	(isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
	(isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
	(isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
        return "PROXY 185.46.212.88:80";
```List of Zscaler public IP addresses to detect whether Zscaler Client Connector is accessing the Zscaler cloud using a GRE/IPSec tunnel. These addresses are documented in the Zscaler Aggregate IP Address Ranges section: https://config.zscaler.com/[cloudname].net/cenr

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-zscaler-ip-addresses)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)![Image 13: Zscaler aggregate IP address ranges](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/Scenario-2-Common-config-PAC-file-code-details-public-egress%20-%20Copy.png)[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-zscaler-ip-addresses)
```
{
return "PROXY 185.46.212.88:80";
}
```If Zscaler Client Connector is at a no-default route location, it uses the Zscaler GVIP 185.46.212.88 as the explicit proxy address for service discovery, enrollment, authentication, and policy and pac downloads.

Instead of using the Zscaler GVIP, use the internal IP address, e.g, . 10.10.10.10 for No External Route Networks.
```
if (trust.test(host) || iam.test(host) || /^trust.zscaler.us$/.test(host) || /^config.zscaler.com$/.test(host))
        return "DIRECT";
    // Bypasses for ZPA
    if ((isInNet(resolved_ip, "100.64.0.0", "255.255.0.0")) || (shExpMatch(host, "*.private.zscaler.com")) || (shExpMatch(host, "*.zpath.net")))
        return "DIRECT";
```When Zscaler Client Connector is on an Off-Trusted Network, use this criteria to bypass Zscaler.
```
return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
}
```Use auto-assigned data centers to connect via Z-Tunnel 2.0 when on an Off-Trusted Network. 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-hosted-app-profile-pac-file) 
    *   [5. Create an App Profile](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-create-app-profile)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Create an App Profile and associate it to the [previously created Forwarding Profile](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments):

        1.   Go to **Infrastructure**>**Connectors**>**Client**.
        2.   Under Platform Settings, select the OS platform you want to add the policy to. The following example uses Windows.
        3.   On the **App Profiles** tab, click **Add Windows Policy**. The **Add Windows Policy** window appears.
        4.   For**Forwarding Profile**, choose the forwarding profile created [previously created](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments).

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-create-app-profile-1)

        5.   In the **PAC and Proxy** section, enter the URL in the **Custom PAC URL** section.
        6.   In **Proxy Configuration**, enable **Disable Loopback Restriction**.
        7.   Enable the **Cache System Proxy**option. When enabled, this option restores the System PAC after the user exits Zscaler Client Connector.
        8.   **Override WPAD**: (Optional) Enable this option if WPAD is in use.
        9.   **Restart WinHTTP Service**:(Optional) Enable this option if WPAD is in use.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-create-app-profile-2)

        10.   In the **Advanced** section, enable **Tunnel Internal Client Connector Traffic**.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-create-app-profile-3)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2-create-app-profile)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-2)

*   [Scenario 3 - Forwarding Internet & SaaS Traffic on All Ports and Protocols Using Z-Tunnel 2.0 via On-Premises Private Service Edge for Internet & SaaS and Private Access Traffic](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)In this scenario, Zscaler Client Connector forwards Internet & SaaS traffic across all ports and protocols using Z-Tunnel 2.0 via the Private Service Edge for Internet & SaaS. Private Access M-Tunnels and authentication traffic are forwarded using the Zscaler GVIP. The Zscaler GVIP, in turn, forwards the traffic to Private Access services in the Zscaler cloud. In this scenario, the Internet & SaaS location associated with the Private Service Edge for Internet & SaaS cluster has Force Authentication enabled to ensure that the Private Service Edge doesn't operate as an open proxy.

![Image 14: Zscaler Client Connector forwards Internet & SaaS traffic across all ports and protocols using Z-Tunnel 2.0](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/Scenario-3-Forwarding-ZIA-Traffic_0.png)

You must configure the following high-level steps to forward traffic on all ports/protocols by Zscaler Client Connector using Z-Tunnel 2.0 in no-default Environments.

    *   [1. Complete Common Configuration Requirements for All no-default Route Environments](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-complete-common-config-req)
    *   [2. Create a Hosted Forwarding Profile PAC File](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-create-hosted-forwarding-profile)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Follow the steps for [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia) to add a custom PAC file. Use the following PAC file:

[See PAC file.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-use-forwarding-profile-pac-file)

```
function FindProxyForURL(url, host) {
   var resolved_ip = dnsResolve(host);
   if ((dnsDomainIs(host, "1.2.3.4")) || (shExpMatch(host, "*.zscaler.net")) ||
       (shExpMatch(host, "*.zscalertwo.net")) || //Service Discovery, Enrollment, Login
       (shExpMatch(host, "mobilesupport.zscaler.com")) || //Support
       (shExpMatch(host, "*.zdxcloud.net")) || //ZDX
       (shExpMatch(host, "*.digicert.com")) || //Cert validation
        
       // Zscaler Updates
       (localHostOrDomainIs(host, "d32a6ru7mhaq0c.cloudfront.net")) || (localHostOrDomainIs(host, "d3l44rcogcb7iv.cloudfront.net")) ||
       (localHostOrDomainIs(host, "dwv281inkfqg3.cloudfront.net")) ||
        
       // Required for Entra IdP
       (shExpMatch(host, "*.msauth.net")) || (shExpMatch(host, "*.msauthimages.net")) ||
       (shExpMatch(host, "*.msftauthimages.net")) || (localHostOrDomainIs(host, "login.microsoftonline.com")) ||
       (localHostOrDomainIs(host, "autologon.microsoftazuread-sso.com")) || (localHostOrDomainIs(host, "login.windows.net")))
   {
       return "PROXY 185.46.212.88:80";
   }
   /* Forward to Client Connector */
   return "DIRECT";
}
```

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-use-forwarding-profile-pac-file) 
Refer to the following table for forwarding PAC file details.

**PAC File Code Details****Notes**
`if ((dnsDomainIs(host, "1.2.3.4")) || (shExpMatch(host, "*.zscaler.net")) ||`Special URL checked by Zscaler Client Connector to enroll using an Explicit proxy, in this case the Zscaler GVIP (185.46.212.88).
`(shExpMatch(host, "*``.zscalertwo.net``")) || //Service Discovery, Enrollment, Login`Replace the wildcard domain with the one that matches the cloud.
`(shExpMatch(host, "mobilesupport.zscaler.com")) || //Support`

`(shExpMatch(host, "*.zdxcloud.net")) || //ZDX`

`(shExpMatch(host, "*.digicert.com")) || //Cert validation`Required for Support access, Zscaler Digital Experience (ZDX), and certificate validation during discovery and enrollment.
`(localHostOrDomainIs(host, "d32a6ru7mhaq0c.cloudfront.net")) || (localHostOrDomainIs(host, "d3l44rcogcb7iv.cloudfront.net")) ||`

`(localHostOrDomainIs(host, "dwv281inkfqg3.cloudfront.net")) ||`Required for Zscaler Client Connector software and module updates during discovery and enrollment.
`(shExpMatch(host, "*``.msauth.net``")) || (shExpMatch(host, "*``.msauthimages.net``")) ||`

`(shExpMatch(host, "*``.msftauthimages.net``")) || (localHostOrDomainIs(host, "``login.microsoftonline.com``")) ||`

`(localHostOrDomainIs(host, "``autologon.microsoftazuread-sso.com``")) || (localHostOrDomainIs(host, "``login.windows.net``")) ||`Required for authentication during enrollment. The example shows domains required by Entra. Use domains required for your IdP.
`return "PROXY 185.46.212.88:80";`If Zscaler Client Connector is at a no-default route location, it uses the Zscaler GVIP 185.46.212.88 as the explicit proxy address to complete discovery and enrollment.

Instead of using the Zscaler GVIP, use the internal IP address, e.g., 10.10.10.10 for No External Route Networks.
`return "DIRECT";`

`}`All other traffic is sent to Zscaler Client Connector. 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-create-hosted-forwarding-profile) 
    *   [3. Create a Forwarding Profile to Use Z-Tunnel 2.0 Forwarding for Both On-Trusted Network (no-default Route Network) and Off-Trusted Network](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-on-off-trusted)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Create a [Trusted Network Criteria](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector) so Zscaler Client Connector can detect when it is on an On-Trusted Network (no-default route environment). Follow these steps to create a forwarding profile:

        1.   Go to **Infrastructure**>**Connectors**>**Client**>**Forwarding Profile for Platforms**.
        2.   Click **Add Forwarding Profile**.

The **Add Forwarding Profile** window appears.
        3.   Enter a name for the profile.
        4.   For **Predefined Trusted Networks**, choose the **Selected** option. This allows Zscaler Client Connector to detect when it is in a no-default route environment. To learn more about trusted networks, see [Configuring Trusted Networks for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector).

        1.   For **On-Trusted Network**, select **Tunnel**.
        2.   For Tunnel version selection, select **Z-Tunnel 2.0**.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-2)

        1.   Disable **Redirect Web Traffic to Zscaler Client Connector Listening Proxy** and **Use Z-Tunnel 2.0 for Proxied Web Traffic**.
        2.   For **Proxy Action Type**, select **Apply on Trusted Network Type Change**.
        3.   Select the **PAC URL Location** and choose **PAC URL**.
        4.   In the **Custom PAC URL** section, enter the PAC URL for the forwarding profile.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-3)

        1.   For **Off-Trusted Network**, select the forwarding mechanisms as per your organization's requirements. Zscaler supports all forwarding mechanism for Off-Trusted network users. The following example uses **Tunnel**.
        2.   For **Tunnel version selection**, select **Z-Tunnel 2.0**.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-4)

        1.   Enable **Redirect Web Traffic to Zscaler Client Connector Listening Proxy**.

A PAC URL is not required for an Off-Trusted Network.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-5)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-on-off-trusted)

    *   [4. Create a Hosted App Profile PAC File](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-created-hosted-app-profile-pac-file)[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Follow the steps for [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-zia) to add a custom PAC file. Use the following PAC file:

```
function FindProxyForURL(url, host) {
 
    var customPort = "${ZS_CUSTOM_PORT}"; // Assigned port for local region - ZS_CUSTOM_PORT
    var egressip = "${SRCIP}";
 
    // Zscaler public egress subnets
    if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
        (isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
        (isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
        (isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
        (isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
        (isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
        (isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
        (isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
        (isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
        (isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
        (isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
        (isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
        (isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
        (isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
        (isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
        (isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
        (isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
        (isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
        (isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
        return "PROXY 10.10.10.50";
 
 
    return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
}
```
**PAC File Code Details****Notes**
`var egressip = "${SRCIP}";`Determines the client's public IP address using the ${SRCIP} macro which is only available when the PAC file is hosted in the Zscaler cloud.
```
// Zscaler public egress subnets
if ((isInNet(egressip, "58.220.95.0", "255.255.255.0")) || (isInNet(egressip, "64.215.22.0", "255.255.255.0")) ||
	(isInNet(egressip, "87.58.64.0", "255.255.192.0")) || (isInNet(egressip, "94.188.131.0", "255.255.255.128")) ||
	(isInNet(egressip, "94.188.139.64", "255.255.255.192")) || (isInNet(egressip, "94.188.248.64", "255.255.255.192")) ||
	(isInNet(egressip, "98.98.26.0", "255.255.255.0")) || (isInNet(egressip, "98.98.27.0", "255.255.255.0")) ||
	(isInNet(egressip, "98.98.28.0", "255.255.255.0")) || (isInNet(egressip, "101.2.192.0", "255.255.192.0")) ||
	(isInNet(egressip, "104.129.192.0", "255.255.240.0")) || (isInNet(egressip, "112.137.170.0", "255.255.255.0")) ||
	(isInNet(egressip, "124.248.141.0", "255.255.255.0")) || (isInNet(egressip, "128.177.125.0", "255.255.255.0")) ||
	(isInNet(egressip, "136.226.0.0", "255.255.0.0")) || (isInNet(egressip, "137.83.128.0", "255.255.192.0")) ||
	(isInNet(egressip, "140.210.152.0", "255.255.254.0")) || (isInNet(egressip, "147.161.128.0", "255.255.128.0")) ||
	(isInNet(egressip, "154.113.23.0", "255.255.255.0")) || (isInNet(egressip, "165.225.0.0", "255.255.128.0")) ||
	(isInNet(egressip, "165.225.192.0", "255.255.192.0")) || (isInNet(egressip, "167.103.0.0", "255.255.0.0")) ||
	(isInNet(egressip, "170.85.0.0", "255.255.0.0")) || (isInNet(egressip, "185.46.212.0", "255.255.252.0")) ||
	(isInNet(egressip, "194.9.96.0", "255.255.240.0")) || (isInNet(egressip, "194.9.112.0", "255.255.252.0")) ||
	(isInNet(egressip, "194.9.116.0", "255.255.255.0")) || (isInNet(egressip, "196.23.154.64", "255.255.255.224")) ||
	(isInNet(egressip, "196.23.154.96", "255.255.255.224")) || (isInNet(egressip, "197.98.201.0", "255.255.255.0")) ||
	(isInNet(egressip, "197.156.241.224", "255.255.255.224")) || (isInNet(egressip, "198.14.64.0", "255.255.192.0")) ||
	(isInNet(egressip, "199.168.148.0", "255.255.254.0")) || (isInNet(egressip, "209.55.128.0", "255.255.192.0")) ||
	(isInNet(egressip, "209.55.192.0", "255.255.224.0")) || (isInNet(egressip, "211.144.19.0", "255.255.255.0")) ||
	(isInNet(egressip, "220.243.154.0", "255.255.254.0")) || (isInNet(egressip, "221.122.91.0", "255.255.255.0")))
        return "PROXY 10.10.10.50:80";
```List of Zscaler public IP addresses to detect whether Zscaler Client Connector is accessing the Zscaler cloud using a GRE/IPSec tunnel. These addresses are documented in the Zscaler Aggregate IP Address Ranges section: [https://config.zscaler.com/[cloudname].net/cenr](https://config.zscaler.com/%5Bcloudname%5D.net/cenr)

If Zscaler Client Connector is at a no-default route location with a GRE/IPSec tunnel, then connect to the Private Service Edge for Internet & SaaS VIP address at `10.10.10.50`.
```
return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
}
```Use auto-assigned data centers to connect via Z-Tunnel 2.0 when Off-Trusted Network. 
[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-created-hosted-app-profile-pac-file) 
    *   [5. Create an App Profile](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)Create an App Profile and associate the [previously created Forwarding Profile](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-on-off-trusted) with it.

        1.   Go to **Infrastructure**>**Connectors**>**Client**.
        2.   Under Platform Settings, select the OS platform you want to add the policy to. The following example uses Windows.
        3.   On the **App Profiles** tab, click **Add Windows Policy**. The **Add Windows Policy** window appears.
        4.   For**Forwarding Profile**, choose the [forwarding profile created previously](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-forwarding-profile-on-off-trusted).

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile-1)

        5.   In the **PAC and Proxy** section, enter the URL in the **Custom PAC URL** section.
        6.   In **Proxy Configuration**, enable **Disable Loopback Restriction**.
        7.   Enable the **Cache System Proxy**option. When enabled, this option restores the System PAC when the user exits Zscaler Client Connector.
        8.   **Override WPAD**:(Optional) Enable this option if WPAD is in use.
        9.   **Restart WinHTTP Service**:(Optional) Enable this option if WPAD is in use.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile-2)

        10.   In the **Advanced** section, enable the **Tunnel Internal Client Connector Traffic**.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile-3)

        11.   In the **App and IP Bypass** section, under **IPv4 Exclusion**, ensure that all internal IP subnets are added so the internal traffic is not tunneled to Internet & SaaS.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile-4)

        12.   Ensure that `0.0.0.0/0` is present in the IPv4 Inclusion section.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile-5)

        13.   Add a list of all internal domains in the Domain Exclusion field for which DNS requests are sent to internal DNS servers and not Internet & SaaS.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile-6)

        14.   Ensure `*` is added to the Domain Inclusion field, so all external DNS requests are tunneled via Internet & SaaS.

[See image.](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile-7)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-app-profile)

    *   [6. Deploy a Private Service Edge for Internet & SaaS Cluster and Associate with a New Internet & SaaS Location with Force Authentication Enabled](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3-deploy-zia-private-service-edge)

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-3)

*   [Scenario 4 - Forwarding Internet & SaaS Traffic on All Ports and Protocols Using Z-Tunnel 2.0 via On-Premises Third-Party Proxy And Private Access Traffic](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-4)

[](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)In this scenario, Zscaler Client Connector forwards Internet & SaaS traffic across all ports and protocols using Z-Tunnel 2.0 via a third-party proxy. Private Access M-Tunnels and authentication traffic are forwarded using the Zscaler GVIP. The Zscaler GVIP, in turn, forwards the traffic to Private Access services in the Zscaler cloud.

![Image 15: Zscaler Client Connector forwards Internet & SaaS traffic across all ports and protocols ](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/implementing-zscaler-client-connector-no-default-route-environments/Scenario-4-Forwarding-ZIA-traffic_1.png)

The configuration for this scenario is the same as [Scenario 3](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments) with the following exceptions regarding the third-party proxy:

    *   The Forwarding Profile for Z-Tunnel 2.0, when on-premises should only use TLS (instead of DTLS).
    *   The proxy must not attempt to authenticate requests destined for Zscaler IP addresses.
    *   The proxy must not attempt to authenticate requests destined for the IdP.
    *   The proxy must not attempt to re-resolve DNS based on SNI.
    *   The proxy must have TLS inspection disabled and tunnel all Zscaler and IdP bound requests.
    *   A separate Internet & SaaS Location (used for the Private Service Edge for Internet & SaaS cluster in the previous step) isn't required.
    *   To account for situations where Zscaler Client Connector falls back to Z-Tunnel 1.0 at locations with a large number of users, consider using a Source-NAT IP address pool with multiple IP addresses and ensure that the load-balancing used with the pool uses sticky connections based on client IP addresses.

[Close](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments#scenario-4)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/apparmor-causes-auto-upgrade-zscaler-client-connector-version-3-7-1-linux-fail -->

Title: AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/apparmor-causes-auto-upgrade-zscaler-client-connector-version-3-7-1-linux-fail

Markdown Content:
# AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Troubleshooting](https://help.zscaler.com/zscaler-client-connector/troubleshooting)
*    AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

Some endpoint devices cannot upgrade to Zscaler Client Connector version 3.7.1 for Linux because of an AppArmor conflict when accessing `openssl.cnf` by the OpenSSL service.

To resolve this issue, run the following script to update the ZSAUpdater AppArmor profile by adding the read permission to `openssl.cnf`:

This issue will be fixed in a future release.

1.   Open the ZSAUpdater AppArmor policy file `sudo vi /etc/apparmor.d/opt.zscaler.bin.zsupdater`.
2.   Insert the policy into the file `/etc/ssl/openssl.cnf r,`.
3.   Save and close the file.
4.   Refresh the AppArmor policies for the ZSAUpdater by using the command `sudo apparmor_parser -r /etc/apparmor.d/opt.zscaler.bin.zsupdater`.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Zscaler Client Connector Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)[Zscaler Client Connector: Windows Registry Keys](https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys)[Zscaler Client Connector: Connection Status Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)[Zscaler Client Connector: Private Access Authentication Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)[Zscaler Services Inaccessible After Upgrading to Zscaler Client Connector versions 4.4 and 4.4.1 for iOS](https://help.zscaler.com/zscaler-client-connector/zscaler-services-inaccessible-after-upgrading-zscaler-client-connector-versions-4-4-and-4-4-1-ios)[DNS Resolution Fails through the ZIA Public Service Edge](https://help.zscaler.com/zscaler-client-connector/dns-resolution-fails-through-zia-public-service-edge)[Captive Portal Sign-In Fails for Chromebook Users](https://help.zscaler.com/zscaler-client-connector/captive-portal-sign-fails-chromebook-users)[Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page)[AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail](https://help.zscaler.com/zscaler-client-connector/apparmor-causes-auto-upgrade-zscaler-client-connector-version-3-7-1-linux-fail)[Firewall Posture Check Failure on macOS Sequoia](https://help.zscaler.com/zscaler-client-connector/firewall-posture-check-failure-macos-sequoia)[Microsoft Outlook and Microsoft Teams Not Accessible](https://help.zscaler.com/zscaler-client-connector/microsoft-outlook-and-microsoft-teams-not-accessible)[Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/log-failure-zscaler-client-connector-android-chromeos--version-1.12)[Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows](https://help.zscaler.com/zscaler-client-connector/browser-based-authentication-fails-when-using-certain-browsers-launch-zscaler-client-connector-linux)[DNS Request Failure in Zscaler Client Connector version 4.1.0.89](https://help.zscaler.com/zscaler-client-connector/dns-request-failure-zscaler-client-connector-version-4.1.0.89)[DNS Resolution Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/dns-resolution-failure-zscaler-client-connector-android-chromeos-version-1.12)[Upgrading to Zscaler Client Connector 3.7 for Windows](https://help.zscaler.com/zscaler-client-connector/upgrading-zscaler-client-connector-3.7-windows)[Missing Zscaler Client Connector GNOME Tray icon for Linux](https://help.zscaler.com/zscaler-client-connector/missing-zscaler-client-connector-gnome-tray-icon)[Upgrading to Windows 10, Version 2004](https://help.zscaler.com/zscaler-client-connector/upgrading-windows-10-version-2004)[Supporting M1 Processors](https://help.zscaler.com/zscaler-client-connector/supporting-m1-processors)[Upgrading to macOS Big Sur](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-big-sur)[Upgrading to macOS Catalina](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-catalina)[Upgrading to Android 10](https://help.zscaler.com/zscaler-client-connector/upgrading-android-10)[Using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-cisco-anyconnect-macos-catalina)[Using the Unauthorized Modification Device Posture Profile](https://help.zscaler.com/zscaler-client-connector/using-unauthorized-modification-device-posture-profile)[Resolving Update Issues to Zscaler Client Connector 1.4.3](https://help.zscaler.com/zscaler-client-connector/resolving-update-issues-zscaler-app-1.4.3)[Login Failures with Zscaler Client Connector Using Active Directory](https://help.zscaler.com/zscaler-client-connector/login-failures-zscaler-client-connector-using-active-directory)[Resolving Zscaler Client Connector Linux 1.2 DNS Configuration Issues for VPNs](https://help.zscaler.com/zscaler-client-connector/resolving-zscaler-client-connector-linux-1.2-dns-configuration-issues-vpns)[Resolving Auto-Update Issues for Zscaler Client Connector Linux 1.2](https://help.zscaler.com/zscaler-client-connector/resolving-auto-update-issues-zscaler-client-connector-linux-1.2)[Upgrading to Zscaler Client Connector version 1.9 for Android](https://help.zscaler.com/zscaler-client-connector/upgrading-android-1.9)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector -->

Title: Syncing Directory Groups between Internet & SaaS and Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector

Markdown Content:
# Syncing Directory Groups between Internet & SaaS and Zscaler Client Connector | Zscaler

[![Image 4: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Zscaler Client Connector Profile Management](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-profile-management)
*    Syncing Directory Groups between Internet & SaaS and Zscaler Client Connector 

![Image 5: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 6: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Syncing Directory Groups between Internet & SaaS and Zscaler Client Connector

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

The directory [groups](https://help.zscaler.com/zia/about-groups) you configured in Internet & SaaS are automatically available for selection within the Zscaler Client Connector when you [configure an App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles). This allows you to create different profiles for the various directory groups in your organization.

![Image 7: Selecting a group for a Zscaler Client Connector profile policy](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/syncing-directory-groups-between-zia-admin-portal-and-zscaler-client-connector-portal/zclient-connector-directory-groups.png)

The Zscaler Client Connector groups sync with the Internet & SaaS groups every three hours. In the Zscaler Admin Console, you can check when the next sync will occur. You can also manually sync groups between the portals.

# Checking Next Sync Time and Performing Manual Sync

1.   Go to **Infrastructure**>**Connectors**>**Directory Sync & Custom Root Cert**.
2.   On the **Advanced Configuration**tab, you can:
    *   View the date and timestamp under **Next Directory Group Sync Time**.
    *   Manually sync directory groups between the Internet & SaaS and Zscaler Client Connector, by clicking **Sync Groups**.

![Image 8: Check the next group sync time and perform a manual group sync](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/syncing-directory-groups-between-zia-admin-portal-and-zscaler-client-connector-portal/zclient-connector-directory-sync-status.png)

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles)[Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles)[Searching for a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/searching-app-profile)[Copying a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/copying-app-profile)[Zscaler Client Connector Profile Rule Example](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-profile-rule-example)[Viewing the Policy Token for a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/viewing-policy-token-zscaler-client-connector-app-profile)[Anti-Tampering for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector)[Configuring a Default Global Log Mode](https://help.zscaler.com/zscaler-client-connector/configuring-global-default-log-level)[Configuring a Cellular Quota with Zscaler Client Connector for Android](https://help.zscaler.com/zscaler-client-connector/configuring-cellular-quota-zscaler-client-connector-android)[Syncing Directory Groups between Internet & SaaS and Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector)[Best Practices for Adding Bypasses for Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0)[About Application Bypass](https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info)[Adding IP-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic)[Adding Process-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic)[Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-prevention-dlp-integration-zscaler-client-connector)[About Business Continuity](https://help.zscaler.com/zscaler-client-connector/about-business-continuity)[Configuring Business Continuity in Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-business-continuity-zscaler-client-connector)[Customizing the Zscaler Client Connector Theme](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-theme)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zia/multiple-language-support-zscaler-client-connector-based-euns -->

Title: Multiple Language Support for Zscaler Client Connector-Based EUNs | Zscaler

URL Source: https://help.zscaler.com/zia/multiple-language-support-zscaler-client-connector-based-euns

Markdown Content:
![Image 1: icon-zia.svg](https://help.zscaler.com/assets/images/icon-zia-hNrDwZ7t.svg)

## Internet & SaaS (ZIA)

### Multiple Language Support for Zscaler Client Connector-Based EUNs

Zscaler Client Connector supports the following languages for [end user notifications](https://help.zscaler.com/zia/about-zscaler-client-connector-based-end-user-notifications) (EUNs):

*   English
*   Chinese (Traditional)
*   French
*   German
*   Japanese
*   Spanish

The EUN language is based on the OS language setting in your system. The Zscaler Client Connector automatically detects your system's OS language and displays EUNs in that language. If your system's OS language is different from the EUN supported languages, Zscaler Client Connector displays EUNs in English, by default.

Only the default messages are translated to all the languages that the Zscaler Client Connector supports. You can modify the default messages per language. Modifying the message in a specific language does not impact the respective message in other languages. Zscaler Client Connector does not automatically translate customized messages.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-auto-system-info-and-log-fetch -->

Title: Enabling Auto System Info and Log Fetch | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-auto-system-info-and-log-fetch

Markdown Content:
# Enabling Auto System Info and Log Fetch | Zscaler

[![Image 3: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Zscaler Client Connector Support Settings](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings)
*   [User Privacy](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings/user-privacy)
*    Enabling Auto System Info and Log Fetch 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Enabling Auto System Info and Log Fetch

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

This feature allows Zscaler to retrieve and analyze Zscaler Client Connector log bundles to expedite resolution of customer-reported issues and to proactively resolve internal issues. Zscaler recommends enabling this feature. When enabled, Zscaler Client Connector collects the information and holds it in secure cloud storage where only Zscaler Support can access it. This feature is disabled by default.

The information collected includes personally identifiable information (PII). To learn more about how Zscaler handles PII, see the [Zscaler Privacy FAQ](https://www.zscaler.com/privacy/faq).

To enable this feature:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**User Privacy**.
2.   Select **Enable Auto System Info and Log Fetch**.

![Image 6: Enable Auto System Info and Log Fetch option](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-support-settings/user-privacy/enabling-auto-system-info-and-log-fetch/zclient-connector-user-privacy-auto-system-info-log-fetch.png)

3.   Click **Save**.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About User Privacy](https://help.zscaler.com/zscaler-client-connector/about-user-privacy)[Configuring Zscaler Client Connector to Collect Device Owner Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-device-owner-information)[Configuring Zscaler Client Connector to Collect Hostnames](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-hostnames)[Enabling Packet Capture for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector)[Configuring Automatic Crash Reporting for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-crash-reporting-zscaler-client-connector)[Configuring Zscaler Client Connector to Collect ZDX Location Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-zdx-location-information)[Allowing Users to Override Z-Tunnel 2.0 or Private Access Protocol Settings](https://help.zscaler.com/zscaler-client-connector/allowing-users-override-z-tunnel-2-0-or-private-access-protocol-settings)[Allowing Non-Administrator Users Access to Zscaler Client Connector Log Files](https://help.zscaler.com/zscaler-client-connector/allowing-non-administrators-access-client-connector-log-files)[Restricting Remote Packet Capture](https://help.zscaler.com/zscaler-client-connector/restricting-remote-packet-capture)[Enabling Auto System Info and Log Fetch](https://help.zscaler.com/zscaler-client-connector/enabling-auto-system-info-and-log-fetch)[Enforcing Secure PAC URLs](https://help.zscaler.com/zscaler-client-connector/enforcing-secure-pac-urls)[Bypassing FQDN Subdomains for VPN Gateway Bypass](https://help.zscaler.com/zscaler-client-connector/bypassing-fqdn-subdomains-vpn-gateway-bypass)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook -->

Title: Zscaler Client Connector Performance Troubleshooting Runbook | Zscaler

URL Source: https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook

Markdown Content:
![Image 1: icon-zscaler-troubleshooting-runbooks.svg](blob:http://localhost/c81c0e12c3727d61ee80ff6c9b403a3b)

## Zscaler Support Troubleshooting Runbooks

### Zscaler Client Connector Performance Troubleshooting Runbook

The Zscaler Client Connector Performance Troubleshooting Runbook is a comprehensive guide designed to address and resolve reported slowness issues when using Zscaler Client Connector for traffic forwarding to the Zscaler cloud. As Zscaler Client Connector is a recommended forwarding method, maintaining its optimal performance is crucial for a seamless user experience. This runbook outlines essential steps for diagnosing performance bottlenecks, starting with client-side and intermediate network checks, before escalating to deeper Zscaler troubleshooting. Furthermore, it details various Zscaler Client Connector configuration modes and adjustments that can be leveraged to significantly improve Zscaler Internet Access (ZIA) performance in Zscaler Client Connector deployments.

## Check UDP Throttling and MTU (Only Z-Tunnel 2.0)

The following troubleshooting information addresses the major types of symptoms commonly reported by users facing performance issues using Z-Tunnel 2.0:

*   Slowness due to internet service provider (ISP) throttling UDP packets.
*   Slowness due to maximum transmission unit (MTU) mismatch.

A My Traceroute (MTR) trace alone is not helpful in identifying these issues.

[ISP Throttling UDP Packets](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#isp-throtting-udp-packets)

Reports and data analysis reveal that certain ISPs deprioritize UDP-based traffic, meaning the ISPs deliberately apply policies to slow down UDP packets. DTLS is the primary transport protocol for Z-Tunnel 2.0, and DTLS uses UDP 443. Deprioritization can result in network traffic performance issues such as overall internet sluggishness.

## Option 1: Switching Users to Z-Tunnel 2.0 or TLS

The following steps describe switching user to Z-Tunnel 2.0 / TLS:

1.   On Zscaler Client Connector Portal, go to **Administration**>**Client Connector Support**>**User Privacy** and enable the **Allow end user to override Z-Tunnel 2.0 or ZPA protocol settings** option.

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#zscaler-client-connector-portal)

1.   With Zscaler Client Connector turned on, browse to `http://127.0.0.1:9000/zconfig?q=@``<CustomerDomain>` (e.g., http://127.0.0.1:9000/zconfig?q=@zscaler.com) and select **TLS**as the **Protocol**in the drop-down menu:

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#zscaler-user-configuration)

1.   To enable these changes, click **Restart Service** in the **More**section of Zscaler Client Connector.

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#restart-service)

## Option 2: Switching Users to Z-Tunnel 2.0 or TLS

You must create a new app profile and a new forwarding profile.

1.   In the new forwarding profile, select the **Primary Transport Selection** as **TLS**.

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#new-forwarding-profile)

1.   Map this new forwarding profile to the newly created app profile.
2.   Map the affected user to this new app profile.
3.   Users must click **Update Policy** in Zscaler Client Connector to be assigned to the new profile.

If the performance improves, it indicates that the ISP is throttling UDP packets. Contact the ISP and work towards resolution.

Until the ISP resolves the issue, you have two other options to work around it:

*   Continue using TLS: Move users to an app profile that is configured with an app profile having Z-Tunnel 2.0 (TLS).

Z-Tunnel 2.0 with TLS can still intercept all ports and protocols, just like Z-Tunnel 2.0 with DTLS.

*   Consider using a Zscaler Client Connector Hybrid configuration: Zscaler Client Connector 3.8 introduced a feature that you can use to tailor a new app profile for specific users affected by slow performance issues. The feature turns on **Redirect Web Traffic to Zscaler Client Connector Listening Proxy** and turns off **Use Z-Tunnel 2.0 for Proxied Web Traffic** in the **Advanced Z-Tunnel Configuration** menu while configuring the forwarding profile.

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#advanced-tunnel-configuration)

The results are as follows:

*   Web traffic using Z-Tunnel 1.0.
*   Non-web traffic using Z-Tunnel 2.0.
*   Not using the forwarding profile Proxy Auto-Configuration (PAC) file.

[Close](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#isp-throtting-udp-packets)

[Issues With Path MTU](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#issues-path-mtu)

MTU Discovery is only applicable for DTLS mode. In the case of TLS mode, TCP negotiates the Maximum Segment Size (MSS) for the outer connection automatically.

Skip this section if you have enabled the Path MTU Discovery option for DTLS mode in the forwarding profile.

The MTU is used for identifying the maximum size of any data packet. The MSS is used for specifying the maximum size of packets that can be sent over a network. The MSS measures the non-header portion of a packet, which is called the payload.

*   DTLS: In the case of DTLS Tunnel 2.0 mode, the default MTU value is 1400. Consequently, the MSS value is MTU - 40 = 1360.
*   TLS: In the case of TLS, TCP negotiates the MSS for the outer connection automatically. Zscaler Client Connector is not involved in making it work.

## Option 1: How to Check and Modify MTU Size

The following steps describe checking and modifying MTU size:

1.   On the Zscaler Client Connector Portal, go to **Administration**>**Client Connector Support**>**User Privacy** and enable **Allow end user to override Z-Tunnel 2.0 or ZPA protocol settings**.
2.   With the Zscaler Client Connector turned on, direct the user to browse to `http://127.0.0.1:9000/zconfig?q=@``<CustomerDomain>` (e.g., http://127.0.0.1:9000/zconfig?q=@zscaler.com) and select one of the available values under **MTU** as shown in the following image:

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#zscaler-user-configuration-2)

1.   To enable these changes, click **Restart Service**in the **More**section of Zscaler Client Connector.

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#restart-service-2)

## Option 2: How to Check and Modify MTU Size

You must create a new app profile and a new forwarding profile.

1.   In the new forwarding profile, under the**MTU for Zscaler Adapter**, configure an MTU size less than 1400 (preferably in increments of 50).
2.   Map this new forwarding profile with the newly created app profile.

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#configure-MTU-size)

1.   Map the affected user to this new forwarding profile.
2.   Users must then click **Update Policy**in Zscaler Client Connector to get assigned to the new profile.

If the performance improves, this means that one of the hops in the path has less MTU size, thereby fragmenting the packets originating from Zscaler Client Connector and leading to performance degradation. You can either report this problem to the ISP and get this issue rectified or continue using this value which is determined to be optimal.

Alternatively, you can enable the Path MTU Discovery option in the forwarding profile, which enables Zscaler Client Connector to automatically determine the path MTU.

If there is no improvement in performance, switch to Z-Tunnel 1.0 to perform some tests and data gathering.

The workaround is _not_ recommended as a permanent solution. Modifying the MTU size is only for testing purposes. Switching to Z-Tunnel 1.0 means Zscaler Client Connector is incapable of intercepting non-web traffic.

### How to Switch to Z-Tunnel 1.0

Complete the following steps to temporarily switch to Z-Tunnel 1.0:

1.   In the new forwarding profile, under **Forwarding Profile Action for ZIA**, select the **Z-Tunnel 1.0** option.
2.   Map this new forwarding profile with the newly created app profile.

[See image.](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#forwarding-action-profile)

1.   Map the affected user to this new forwarding profile.
2.   Click **Update Policy** on Zscaler Client Connector to be assigned to the new profile.

[Close](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#issues-path-mtu)

## Analyzing and Isolating the Pattern

This section covers the most common patterns to help decide the next troubleshooting direction.

[Slowness Observed With a Particular OS Type](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slowness-particular-os)

Slowness might be observed with a particular OS type, at office or at home, or when connected to any Zscaler data center.

Review the following events to understand the pattern. Understand when the issue started and try to note all the changes that happened during that time.

| Events | Steps to Try |
| --- | --- |
| Did an OS upgrade occur? | Check the behavior on a device with the previous OS or revert the OS on one of the impacted machines. |
| Did a third-party security or VPN app get newly installed or updated? | Disable the services of the app and monitor the performance. |
| Have the processes required for Zscaler Client Connector to function been allowlisted? | Revisit [this allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)to validate it. |
| High CPU utilization with Zscaler Client Connector enabled. | High CPU usage on the host machine could lead to performance issues. |

[Close](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slowness-particular-os)

[Performance Issues Observed by All Users of an Organization](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slowness-all-day)

There are instances wherein all users of an organization can face slowness, regardless of where they are working from. A few reasons for such behavior could be:

| Events | Next Steps |
| --- | --- |
| Slow performance after a recent Zscaler Client Connector version upgrade. | * Revert to the previously known working version on a few workstations and monitor performance. * If performance improvement is seen on all the workstations where Zscaler Client Connector was reverted, the Zscaler Client Connector version could be the issue. * Collect the Zscaler Client Connector logs from a few workstations with old and new versions and escalate to [Zscaler Support](https://help.zscaler.com/contact-support). |
| Slowness performance after a recent security patch or new software push. | * Revert the security patch or software push on a few workstations. * If performance improvement is seen on all the workstations where the security patch or software push was reverted, and is most likely caused by the recent change. * Collect the Zscaler Client Connector logs from a few workstations with old and new versions and escalate to [Zscaler Support](https://help.zscaler.com/contact-support). |

[Close](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slowness-all-day)

[Performance Issues Observed During a Particular Time of the Day or Day of the Week](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slowness-time-day)

Although rare, this issue might have been reported previously. An intermittent issue could be caused by the following events:

*   Scheduled big data upload or download, which restricts the firewall or internet bandwidth.
*   Scheduled antivirus scans on the workstation, which restricts the CPU or memory utilization of the workstation.

[Close](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slowness-time-day)

[Performance Issues Observed at a Particular Office Location](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slow-office-location)

There are instances where users at a particular office location are facing performance issues. When the users connect from a non-office location or connect to their mobile hotspot from the office, the performance is normal.

Here are some potential issues and next steps:

| Events | Next Steps |
| --- | --- |
| Ongoing issue or maintenance at the Zscaler data center. | Go to section [Zscaler Public Data Center](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#scope-3-zia) to continue with the troubleshooting. |
| Path congestion between office location and the Zscaler data center. | Go to section [Zscaler Public Data Center](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#scope-3-zia) to continue with the troubleshooting. |
| Issues with office networking components such as router, switch, or firewall. | Check the general health parameters (e.g., CPU, memory, throttling policies, etc.), and check any network monitoring tools. |

[Close](https://help.zscaler.com/troubleshooting-runbooks/zscaler-client-connector-performance-troubleshooting-runbook#slow-office-location)

## Further Steps

Compile all the relevant collected data and open a support case with [Zscaler Support](https://help.zscaler.com/contact-support).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-deception-device-groups -->

Title: Enabling Zscaler Deception for Device Groups | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-deception-device-groups

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling Zscaler Deception for Device Groups

You can use Zscaler Service Entitlement to enroll Device Groups in Zscaler Deception. Configuring Deception for device groups allows you to assign entitlements and policy settings based on ownership through device posture profiles. For example, one user can have two devices, one personal and one employer-provided. The personal device can be enrolled in Deception and the employer-provided device can be enrolled in Deception and Zscaler Private Access (ZPA).

To enable Zscaler Deception for device groups, you must install Zscaler Client Connector for Windows 3.9 or later.

To enable Zscaler Deception for device groups:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left menu, select **Zscaler Service Entitlement**.
3.   Click the **Zscaler Deception****(Deception)** tab.
4.   To enable Deception for device groups, ensure that **Zscaler Deception Enabled by Default**is disabled. If this setting is enabled, Deception is available for all users and you cannot assign Deception to a device group.
5.   Select one or more groups from the **Device Groups**drop-down menu.

Groups are defined in the Device Groups section in the Zscaler Client Connector Portal under Administration. For more information, see [About Device Groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups-0).

1.   Select **Logout Zscaler Client Connector when Zscaler Deception Entitlement is Enabled**to automatically log users out of Zscaler Client Connector when Deception is enabled for a device group. Users can then log in again to enable the Deception service. This applies to customers using ZPA only. When not enabled, Zscaler Client Connector runs without the Deception service until the next Zscaler Client Connector login.

![Image 2: Enable ZDX for Device Groups](https://help.zscaler.com/downloads/z-app/policy-administration-settings/zscaler-service-entitlement/enabling-zscaler-deception-device-groups/Deception_device_grps.png)

1.   Click **Save**.

Your users’ devices are updated the next time they connect. If users are already connected, devices automatically update in 60 minutes. Users can manually update their devices in Zscaler Client Connector. On the **More**page, click **Update Policy**. After manually refreshing the device, they must reauthenticate on the **Private Access** page.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/self-service-notifications-zscaler-client-connector -->

Title: Self Service Notifications in Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/self-service-notifications-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Self Service Notifications in Zscaler Client Connector

After the admins have [configured the Self Service settings](https://help.zscaler.com/zdx/configuring-self-service-settings), users receive notifications when issues are detected and need attention. Each notification contains a brief diagnosis and recommendation that might resolve the CPU or Wi-Fi issue.

For the minimum required version compatibility for Self Service, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).

## Enabling and Disabling Notifications in Zscaler Client Connector

In Zscaler Client Connector, you can enable or disable the Self Service notifications:

1.   From the left-side navigation, click **More**.
2.   Under **Settings**, enable the toggle for **Show Self Service notification popups**.

![Image 2: Self Service notification settings on Zscaler Client Connector](https://help.zscaler.com/downloads/client-connector/end-user-guide/self-service-notifications-zscaler-client-connector/self-service-ZClientconnector.png)

Self Service notifications appear as a dialog window on a user’s screen. For example:

![Image 3: Self Service pop-up notification example](https://help.zscaler.com/downloads/z-app/end-user-guides/self-service-notifications-zscaler-client-connector/self-service-notification-pop-up.png)

The notifications are also recorded on the **Notifications** page.

![Image 4: Self Service notifications example](https://help.zscaler.com/downloads/z-app/end-user-guides/self-service-notifications-zscaler-client-connector/self-service-%20notifications.png)

Click **Learn more** for more information on the notification. For example:

![Image 5: Learn more about the Self Service notification](https://help.zscaler.com/downloads/z-app/end-user-guides/self-service-notifications-zscaler-client-connector/self-service-learn-more.png)

You can also provide feedback if you found the recommendation helpful.

![Image 6: Feedback for Self Service notification recommendations](https://help.zscaler.com/downloads/z-app/end-user-guides/self-service-notifications-zscaler-client-connector/self-service-feedback-%20learn-more.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-interoperability-apple-icloud-private-relay -->

Title: Zscaler Client Connector Interoperability with Apple iCloud Private Relay | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-interoperability-apple-icloud-private-relay

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Zscaler Client Connector Interoperability with Apple iCloud Private Relay

iCloud Private Relay is a security feature available on Apple iOS and macOS devices. It routes internet traffic through Apple’s servers and encrypts client DNS traffic to obfuscate the end user's identity. iCloud Private Relay encrypts client DNS traffic before it is intercepted by Zscaler Client Connector. This interferes with Zscaler Client Connector’s ability to process DNS requests and can disrupt both Internet & SaaS and Private Access traffic flows.

Zscaler recommends disabling iCloud Private Relay when deploying Zscaler Client Connector to iOS and macOS devices to avoid connectivity issues.

## How do I disable iCloud Private Relay?

## End Users

End users can check whether iCloud Private Relay is enabled and then disable iCloud Private Relay on their individual devices. To learn more, refer to the following documentation based on the OS:

*   [iOS](https://support.apple.com/en-sg/guide/iphone/iph499d287c2/ios)
*   [macOS](https://support.apple.com/guide/mac-help/use-icloud-private-relay-mchlecadabe0/mac)

## Administrators

You can Disable iCloud Private Relay in App Profiles. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#iOS-advanced). Applies to Zscaler Client Connector version 4.4 and later for iOS.

Administrators can return an NXDOMAIN response for the hostnames used by iCloud Private Relay traffic to alert end users that they must disable iCloud Private Relay. To learn more, refer to the _Allow for network traffic audits_ section in the [Apple documentation](https://developer.apple.com/icloud/prepare-your-network-for-icloud-private-relay/).

If you use Jamf Pro or another MDM to deploy Zscaler Client Connector, you can restrict iCloud Private Relay by distributing a configuration profile. To learn more, refer to the [Jamf documentation](https://learn.jamf.com/en-US/bundle/jamf-security-cloud-setup-guide/page/Enabling_the_HTTPS_Block_Page_for_Supervised_Apple_Devices.html).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-mobileiron-android -->

Title: Deploying Zscaler Client Connector with MobileIron for Android | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-mobileiron-android

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Deploying Zscaler Client Connector with MobileIron for Android

This guide is for admins only. If you are an end user, contact your organization’s administrator for deployment-related details.

With MobileIron Unified Endpoint Management (UEM), you can configure and deploy Zscaler Client Connector for Android devices. Before deploying Zscaler Client Connector, you must first enable the Android Enterprise feature for MobileIron UEM. For more information, refer to the [MobileIron documentation](https://help.ivanti.com/mi/help/en_us/CORE/11.1.0.0/dmga/Content/DMGfiles/Android_enterprise_Overv.htm).

The following deployment procedure is based on MobileIron Core 10.1.

To configure and deploy Zscaler Client Connector to MobileIron for Android devices:

1.   In the MobileIron Admin Portal, click **Apps** from the top menu.

![Image 2: Navigating to the apps page to import the Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/mobileiron-1.png)

1.   Click **Add** to import Zscaler Client Connector to the **App Catalog**.

![Image 3](https://help.zscaler.com/downloads/z-app/mobileiron-20.png)

1.   Click **Google Play**, and then search for `Zscaler Client Connector`.

![Image 4: Searching for the Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/mobileiron-3.png)

1.   Select **Zscaler Client Connector** from the list, and then click **Next**.

![Image 5: Selecting the Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/mobileiron-4.png)

1.   (Optional) Modify the **Description** and select a **Category**. Click **Next**.

![Image 6: Details for the Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/mobileiron-5.png)

1.   In the **Android Enterprise (All Modes)** section, enable the **Install this app for Android enterprise** option.

![Image 7: Enabling Zscaler Client Connector for Android Enterprise](https://help.zscaler.com/downloads/z-app/mobileiron-6.png)

The **Configuration Choices** section appears.

This section appears only if you have already enabled Android Enterprise for MobileIron Core.

![Image 8: Configuration choices for the Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/mobileiron-7.png)

1.   Under **Default Configuration for Zscaler Client Connector**, you can use [parameters](https://help.zscaler.com/zscaler-client-connector/parameters-guide-zscaler-client-connector-android-and-android-chromeos) to preconfigure Zscaler Client Connector. Preconfiguring Zscaler Client Connector allows you to remove steps from the user enrollment process (e.g., allowing users to skip the enrollment page or the cloud selection prompt on Zscaler Client Connector).
2.   Click **Finish**.

![Image 9: The Default Configuration for Zscaler Client Connector options](https://help.zscaler.com/downloads/z-app/downloading-deployment/deploying-zscaler-app-mobileiron-android/CustomDNS%20MobileIron.png)

You can now register the device (that has a work profile or is in Device Owner mode) and apply the Android Enterprise configuration. Zscaler Client Connector is automatically installed and configured.

Users must open Zscaler Client Connector once for the configurations to be applied for the first time.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector -->

Title: Reporting an Issue with Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Reporting an Issue with Zscaler Client Connector

This article covers how to access the **Report an Issue** form and complete the form on different OSs. When you submit the form, depending on your organization's setup, Zscaler Client Connector might send an email to your organization's support admin or submit a ticket directly to Zscaler Support (your support admin receives a copy of this ticket as well). After you submit the form, you receive an email acknowledging the support request.

The **Report an Issue**option appears in the app user interface only if your IT admin has [enabled it in your Zscaler Client Connector support configuration](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-options-zscaler-client-connector).

*   [Windows](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)This section covers the following topics:

    *   [Access the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win-access)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To open the **Report an Issue** form, use one of the following options:

        *   Open Zscaler Client Connector and click **More**. In the **Troubleshoot** section, click **Report an Issue**.

![Image 2: The Report an Issue option on Zscaler Client Connector for Windows](https://help.zscaler.com/downloads/client-connector/end-user-guide/reporting-issue-zscaler-client-connector/zscaler-client-connector-reporting-windows.png)

        *   Right-click the Zscaler Client Connector system tray icon, then select **Report An Issue**.

![Image 3: The Report an Issue option from the Zscaler Client Connector tray icon](https://help.zscaler.com/downloads/z-app/end-user-guides/windows/reporting-issue-zscaler-app-windows/zscaler-app-tray-icon-report-issue.png)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win-access)

    *   [Complete the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win-complete)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To complete the **Report an Issue**form, specify the following information:

![Image 4: The Report an Issue Form for Zscaler Client Connector for Windows](https://help.zscaler.com/downloads/z-app/end-user-guides/windows/reporting-issue-zscaler-app-windows/zscaler-app-report-issue-form.png)

        *   **Name:**Enter your name. The field to the right of the **Name** field is prepopulated with the email addresses of your organization's designated support admins.
        *   **CC:**If you want to copy anyone else on this support request, enter the email address here. If entering multiple addresses, separate them with commas.

Your organization's support admins are always copied on support requests, even if you leave this field blank.

        *   **Problem:**From the drop-down menu, select a category that describes the problem. **Other** is selected by default.
            *   **Authentication:**Select if you are having problems logging into the app.
            *   **User Interface:**Select if app features are not responding as expected.
            *   **Network Connectivity:**Select if the app is showing connectivity issues.
            *   **Private Access:** Select if you are having problems with the Private Access service.
            *   **Other:**Select if you are unsure of the problem, or if the problem does not relate to the categories above.

        *   **Priority:**From the drop-down menu, select a severity level for the problem. **Normal** is selected by default.
            *   **Urgent:**App cannot be used at all.
            *   **High:** App can be partially used but critical functions are not working.
            *   **Normal:**App can be used but some functions are not working.
            *   **Low:**App can be used but minor functions are not working.

        *   **Comments:**Enter additional comments to describe the issue.
        *   **Attach File:**Attach an applicable file, e.g., a screenshot showing an error message.
        *   **Show/Hide logs:**You can show or hide logs in the **Report an Issue**window. You can also see where encrypted logs are stored on your device

Logs will always be automatically attached when you report an issue.

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win-complete)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#macos)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)This section covers the following topics:

    *   [Access the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#mac-access)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To open the **Report an Issue**form, use one of the following options.

        *   Open Zscaler Client Connector and click **More**. In the **Troubleshoot**section, click **Report An Issue**.

![Image 5: The Report an Issue option on Zscaler Client Connector for macOS](https://help.zscaler.com/downloads/client-connector/end-user-guide/reporting-issue-zscaler-client-connector/zscaler-client-connector-reporting-macos.png)

        *   Right-click the Zscaler Client Connector menu bar icon, then select **Report An Issue**.

![Image 6: The Report an Issue option from the Zscaler Client Connector menu bar icon](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/zscaler-app-user-guide-macos/how-do-i-report-issue-macos-version-zscaler-app/menu_bar_icon_report_an_issue_option_screenshot.png)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#mac-access)

    *   [Complete the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#mac-complete)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To complete the **Report an Issue** form, specify the following information:

![Image 7: The Report an Issue Form for Zscaler Client Connector for macOS](https://help.zscaler.com/downloads/client-connector/end-user-guide/reporting-issue-zscaler-client-connector/zscaler-client-connector-report-an-issue-macos.png)

        *   **Name**:Enter your name. The field below the **Name** field is prepopulated with the email addresses of your organization's designated support admins.
        *   **CC**:If you want to copy anyone else on this support request, enter the email address here. If entering multiple addresses, separate them with commas.

Your organization's support admins are always copied on support requests, even if you leave this field blank.

        *   **Problem**:From the drop-down menu, select a category that describes the problem. **Other** is selected by default.
            *   **Authentication**:Select if you are having problems logging into the app.
            *   **User Interface**:Select if app features are not responding as expected.
            *   **Network Connectivity**:Select if the app is showing connectivity issues.
            *   **Private Access**: Select if you are having problems with the Private Access service.
            *   **Other**:Select if you are unsure of the problem, or if the problem does not relate to the categories above.

        *   **Priority**:From the drop-down menu, select a severity level for the problem. **Normal** is selected by default.
            *   **Urgent**:App cannot be used at all.
            *   **High**: App can be partially used but critical functions are not working.
            *   **Normal**:App can be used but some functions are not working.
            *   **Low**:App can be used but minor functions are not working.

        *   **Comments**:Enter additional comments to describe the issue.
        *   **Attach File**:Attach an applicable file, e.g, a screenshot showing an error message.

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#mac-complete)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#macos)

*   [Linux](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#linux)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)This section covers the following topics:

    *   [Access the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#linux-access)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To open the **Report an Issue**form, use one of the following options.

        *   Open Zscaler Client Connector and click **More**. In the **Troubleshoot**section, click **Report An Issue**.

![Image 8: Report an issue from the app More window](https://help.zscaler.com/downloads/z-app/end-user-guide/reporting-issue-zscaler-client-connector/client-connector-linux-report-an-issue-app.png)

        *   Right-click the Zscaler Client Connector menu bar icon, then select **Report An Issue**.

![Image 9: Report an issue from the tray icon](https://help.zscaler.com/downloads/z-app/end-user-guide/reporting-issue-zscaler-client-connector/client-connector-linux-report-an-issue-tray.png)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#linux-access)

    *   [Complete the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#linux-complete)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To complete the **Report An Issue** form, specify the following information:

![Image 10: Report an issue form](https://help.zscaler.com/downloads/z-app/end-user-guide/reporting-issue-zscaler-client-connector/client-connector-linux-report-an-issue-form.png)

        *   **Name**:Enter your name. The field below the **Name** field is prepopulated with the email addresses of your organization's designated support admins.
        *   **CC**:If you want to copy anyone else on this support request, enter the email address here. If entering multiple addresses, separate them with commas.

Your organization's support admins are always copied on support requests, even if you leave this field blank.

        *   **Problem**:From the drop-down menu, select a category that describes the problem. **Other** is selected by default.
            *   **Authentication**:Select if you are having problems logging into the app.
            *   **User Interface**:Select if app features are not responding as expected.
            *   **Network Connectivity**:Select if the app is showing connectivity issues.
            *   **Private Access**: Select if you are having problems with the Private Access service.
            *   **Other**:Select if you are unsure of the problem, or if the problem does not relate to the categories above.

        *   **Priority**:From the drop-down menu, select a severity level for the problem. **Normal** is selected by default.
            *   **Urgent**:App cannot be used at all.
            *   **High**: App can be partially used but critical functions are not working.
            *   **Normal**:App can be used but some functions are not working.
            *   **Low**:App can be used but minor functions are not working.

        *   **Comments**:Enter additional comments to describe the issue.
        *   **Attach File**:Attach an applicable file, e.g, a screenshot showing an error message.

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#linux-complete)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#linux)

*   [Android](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#android)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)This section covers the following topics:

    *   [Access the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#android-access)[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To open the **Report an Issue** form, tap **More** from the Zscaler Client Connector app. In the **Troubleshoot**section, tap **Report An Issue**.

![Image 11: The Report an Issue option on Zscaler Client Connector for Android](https://help.zscaler.com/downloads/client-connector/end-user-guide/reporting-issue-zscaler-client-connector/zscaler-client-connector-reporting-android.png)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#android-access) 
    *   [Complete the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#android-complete)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To complete the **Report an Issue** form, specify the following information:

![Image 12: The Report an Issue Form on Zscaler Client Connector for Android on ChromeOS](https://help.zscaler.com/downloads/z-app/end-user-guides/reporting-issue-zscaler-client-connector/ZClient-Conn-complete-report-issue-form-android.png)

        *   **Name**: Enter your name. The field below the **Name** field is prepopulated with the email address of your organization’s designated support admins.
        *   **CC**: If you want to copy anyone else on this support request, you can enter the email address here (separate multiple addresses by commas.)

Your organization’s support admins are always copied on support requests, even if you leave this field blank.

        *   **Priority**: From the drop-down menu, select a severity level for the problem.
            *   **Urgent**: App cannot be used at all.
            *   **High**: App can be partially used but critical functions are not working.
            *   **Normal**: App can be used but some functions are not working.
            *   **Low**: App can be used but minor functions are not working.

        *   **Problem**: From the drop-down menu, select a category that describes the problem.
            *   **Authentication**: Select if you are having problems logging into the app.
            *   **User Interface**: Select if app features are not responding as expected.
            *   **Network Connectivity**: Select if the app is showing connectivity issues.
            *   **Others**: Select if you are unsure of the problem, or if the problem does not relate to the categories above.

        *   **Comments**: Enter additional comments to describe the issue. The comments cannot exceed 250 characters.
        *   **Attach File(<1MB)**:Attach an applicable file, e.g, a screenshot showing an error message. The size of the file should not exceed1 MB.

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#android-complete)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#android)

*   [Android on ChromeOS](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#chrome)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)This section covers the following topics:

    *   [Access the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#chrome-access)[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To open the **Report an Issue** form, tap **More** from the Zscaler Client Connector app. In the **Troubleshoot**section, tap **Report an Issue**.

![Image 13: The Report an Issue option on Zscaler Client Connector for Android on ChromeOS](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/reporting-issue-zscaler-client-connector-draft-doc-50360-round-2/zscaler-client-connector-reporting-android.png)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#chrome-access) 
    *   [Complete the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#chrome-complete)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To complete the **Report an Issue** form, specify the following information:

![Image 14: The Report an Issue Form on Zscaler Client Connector for Android on ChromeOS](https://help.zscaler.com/downloads/z-app/end-user-guides/reporting-issue-zscaler-client-connector/ZClient-Conn-complete-report-issue-form-android.png)

        *   **Name**: Enter your name. The field below the **Name** field is prepopulated with the email address of your organization’s designated support admins.
        *   **CC**: If you want to copy anyone else on this support request, you can enter the email address here. If entering multiple addresses, separate them with commas.

Your organization’s support admins are always copied on support requests, even if you leave this field blank.

        *   **Priority**: From the drop-down menu, select a severity level for the problem.
            *   **Urgent**: App cannot be used at all.
            *   **High**: App can be partially used but critical functions are not working.
            *   **Normal**: App can be used but some functions are not working.
            *   **Low**: App can be used but minor functions are not working.

        *   **Problem**: From the drop-down menu, select a category that describes the problem.
            *   **Authentication**: Select if you are having problems logging into the app.
            *   **User Interface**: Select if app features are not responding as expected.
            *   **Network Connectivity**: Select if the app is showing connectivity issues.
            *   **Others**: Select if you are unsure of the problem, or if the problem does not relate to the categories above.

        *   **Comments**: Enter additional comments to describe the issue. The comments cannot exceed 250 characters.
        *   **Attach File(<1MB)**:Attach an applicable file, e.g, a screenshot showing an error message. The size of the file should not exceed 1 MB.

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#chrome-complete)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#chrome)

*   [iOS](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#ios)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)This section covers the following topics:

    *   [Access the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#ios-access)[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To open the **Report an Issue** form, tap **More** from the Zscaler Client Connector app. In the **Troubleshoot**section, tap **Report An Issue**.

![Image 15: The Report an Issue Option on Zscaler Client Connector for iOS](https://help.zscaler.com/downloads/client-connector/end-user-guide/reporting-issue-zscaler-client-connector/zscaler-client-connector-reporting-ios.png)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#ios-access) 
    *   [Complete the Report an Issue Form](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#ios-complete)

[](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)To complete the **Report an Issue** form, specify the following information:

![Image 16: The Report an Issue Form on Zscaler Client Connector for iOS](https://help.zscaler.com/downloads/client-connector/end-user-guide/reporting-issue-zscaler-client-connector/zscaler-client-connector-report-an-issue.ios_.png)

        *   **Name**: Enter your name containing alphabetic characters only. The field below the **Name** field is prepopulated with the email addresses of your organization’s designated support admins.
        *   **CC**: If you want to copy anyone else on this support request, you can enter the email address here (separate multiple addresses by commas).

Your organization’s support admins are always copied on support requests, even if you leave this field blank.

        *   **Problem**: Enter a category that describes the problem.
            *   **Authentication**: Select if you are having problems logging into the app.
            *   **User Interface**: Select if app features are not responding as expected.
            *   **Network Connectivity**: Select if the app is showing connectivity issues.
            *   **Others**: Select if you are unsure of the problem, or if the problem does not relate to the categories above.

        *   **Priority**: Enter a severity level for the problem.
            *   **Urgent**: App cannot be used at all.
            *   **High**: App can be partially used but critical functions are not working.
            *   **Normal**: App can be used but some functions are not working.
            *   **Low**: App can be used but minor functions are not working.

        *   **Enter comments here**: Enter additional comments to describe the issue. The comments cannot exceed 1024 characters.

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#ios-complete)

[Close](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#ios)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info -->

Title: About Application Bypass | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info

Markdown Content:
# About Application Bypass | Zscaler

[![Image 3: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Zscaler Client Connector Profile Management](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-profile-management)
*    About Application Bypass 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### About Application Bypass

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

[Watch a video about Application Bypass](https://fast.wistia.net/embed/iframe/vslmsh229d) (shows legacy UI).

In Application Bypass, you can view the available predefined IP-based applications and add IP-based applications to bypass traffic. You can also add process-based applications to bypass traffic. Select applications to bypass traffic in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) under Traffic Steering.

You cannot configure domain-based bypasses on this page. If you want to bypass a specific domain, you can add it to a custom PAC file. To learn more, see [Best Practices for Using PAC Files with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector).

Application bypass provides the following benefits and enables you to:

*   View, add, and search for IP-based or process-based applications to bypass traffic.
*   Explore details of a bypassed application.

# About the Application Bypass Page

On the Application Bypass page (Infrastructure > Common Resources > Application > IP Based), you can do the following:

1.   View and add [process-based applications](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic).
2.   [Add an IP-based application](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic).
3.   Search for existing applications added to Application Bypass.
4.   View a list of IP-based applications that Zscaler Client Connector bypasses, along with the **Last Edited Timestamp** details.
5.   Click the **View**icon to view the **Protocol**, **Port**, and **IP Address** of a bypassed IP-based application.

![Image 6: Application Bypass page](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/about-application-bypass-info/zclient-connector-about-application-bypass.png)

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles)[Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles)[Searching for a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/searching-app-profile)[Copying a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/copying-app-profile)[Zscaler Client Connector Profile Rule Example](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-profile-rule-example)[Viewing the Policy Token for a Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/viewing-policy-token-zscaler-client-connector-app-profile)[Anti-Tampering for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector)[Configuring a Default Global Log Mode](https://help.zscaler.com/zscaler-client-connector/configuring-global-default-log-level)[Configuring a Cellular Quota with Zscaler Client Connector for Android](https://help.zscaler.com/zscaler-client-connector/configuring-cellular-quota-zscaler-client-connector-android)[Syncing Directory Groups between Internet & SaaS and Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-internet-saas-and-zscaler-client-connector)[Best Practices for Adding Bypasses for Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-adding-bypasses-z-tunnel-2.0)[About Application Bypass](https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info)[Adding IP-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic)[Adding Process-Based Applications to Bypass Traffic](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic)[Zscaler Endpoint Data Loss Prevention (DLP) Integration with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-prevention-dlp-integration-zscaler-client-connector)[About Business Continuity](https://help.zscaler.com/zscaler-client-connector/about-business-continuity)[Configuring Business Continuity in Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-business-continuity-zscaler-client-connector)[Customizing the Zscaler Client Connector Theme](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-theme)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-zdx-device-groups -->

Title: Enabling ZDX for Device Groups | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-zdx-device-groups

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling ZDX for Device Groups

You can use Zscaler Service Entitlement to enroll Device Groups in Zscaler Digital Experience (ZDX). Configuring ZDX for device groups allows you to assign entitlements and policy settings based on ownership through device posture profiles. For example, one user can have two devices, one personal and one employer-provided. The personal device can be enrolled in ZDX, and the employer-provided device can be enrolled in ZDX and Zscaler Private Access (ZPA).

To enable ZDX for device groups, you must deploy Zscaler Client Connector 3.9 or later.

To enable ZDX for device groups:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left menu, select **Zscaler Service Entitlement**.
3.   Click the **Zscaler Digital Experience****(ZDX)**tab.
4.   To enable ZDX for device groups, ensure that **ZDX Enabled by Default** is disabled. If this setting is enabled, ZDX is available for all users and you cannot assign ZDX to a device group.
5.   To configure user groups, see [Enabling ZDX for a Group of Users](https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-zdx-group-users).
6.   Select one or more groups from the **Device Groups**drop-down menu.

Groups are defined in the Device Groups section in the Zscaler Client Connector Portal under Administration. For more information, see [About Device Groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups-0).

1.   Select **Logout ZCC when ZDX Entitlement is Enabled**to automatically log users out of Zscaler Client Connector when ZDX is enabled for a device group. Users can then log in again to enable the ZDX service. This applies to customers using ZPA only or ZPA and Zscaler Deception. When disabled, Zscaler Client Connector runs without the ZDX service until the next Zscaler Client Connector login.​​​​​​

![Image 2: Enable ZDX for Device Groups](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/enabling-zdx-device-groups/Client_Connector_ZDX_Device_Grps_5.png)

1.   Click **Save**.

Your users' devices are updated the next time they connect. If users are already connected, devices automatically update in 60 minutes. Users can manually update their devices in Zscaler Client Connector. On the **More**page, click **Update Policy**. After manually refreshing the device, they must reauthenticate on the **Private Access** page.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/log-failure-zscaler-client-connector-android-chromeos--version-1.12 -->

Title: Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12 | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/log-failure-zscaler-client-connector-android-chromeos--version-1.12

Markdown Content:
# Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12 | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Troubleshooting](https://help.zscaler.com/zscaler-client-connector/troubleshooting)
*    Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

In Zscaler Client Connector for Android on ChromeOS version 1.12, users couldn't log in to Zscaler Client Connector when connected to an IPv6-only cellular network. For a workaround, request a dual-stack mobile data network from your cellular provider.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Zscaler Client Connector Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)[Zscaler Client Connector: Windows Registry Keys](https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys)[Zscaler Client Connector: Connection Status Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)[Zscaler Client Connector: Private Access Authentication Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)[Zscaler Services Inaccessible After Upgrading to Zscaler Client Connector versions 4.4 and 4.4.1 for iOS](https://help.zscaler.com/zscaler-client-connector/zscaler-services-inaccessible-after-upgrading-zscaler-client-connector-versions-4-4-and-4-4-1-ios)[DNS Resolution Fails through the ZIA Public Service Edge](https://help.zscaler.com/zscaler-client-connector/dns-resolution-fails-through-zia-public-service-edge)[Captive Portal Sign-In Fails for Chromebook Users](https://help.zscaler.com/zscaler-client-connector/captive-portal-sign-fails-chromebook-users)[Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page)[AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail](https://help.zscaler.com/zscaler-client-connector/apparmor-causes-auto-upgrade-zscaler-client-connector-version-3-7-1-linux-fail)[Firewall Posture Check Failure on macOS Sequoia](https://help.zscaler.com/zscaler-client-connector/firewall-posture-check-failure-macos-sequoia)[Microsoft Outlook and Microsoft Teams Not Accessible](https://help.zscaler.com/zscaler-client-connector/microsoft-outlook-and-microsoft-teams-not-accessible)[Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/log-failure-zscaler-client-connector-android-chromeos--version-1.12)[Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows](https://help.zscaler.com/zscaler-client-connector/browser-based-authentication-fails-when-using-certain-browsers-launch-zscaler-client-connector-linux)[DNS Request Failure in Zscaler Client Connector version 4.1.0.89](https://help.zscaler.com/zscaler-client-connector/dns-request-failure-zscaler-client-connector-version-4.1.0.89)[DNS Resolution Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/dns-resolution-failure-zscaler-client-connector-android-chromeos-version-1.12)[Upgrading to Zscaler Client Connector 3.7 for Windows](https://help.zscaler.com/zscaler-client-connector/upgrading-zscaler-client-connector-3.7-windows)[Missing Zscaler Client Connector GNOME Tray icon for Linux](https://help.zscaler.com/zscaler-client-connector/missing-zscaler-client-connector-gnome-tray-icon)[Upgrading to Windows 10, Version 2004](https://help.zscaler.com/zscaler-client-connector/upgrading-windows-10-version-2004)[Supporting M1 Processors](https://help.zscaler.com/zscaler-client-connector/supporting-m1-processors)[Upgrading to macOS Big Sur](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-big-sur)[Upgrading to macOS Catalina](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-catalina)[Upgrading to Android 10](https://help.zscaler.com/zscaler-client-connector/upgrading-android-10)[Using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-cisco-anyconnect-macos-catalina)[Using the Unauthorized Modification Device Posture Profile](https://help.zscaler.com/zscaler-client-connector/using-unauthorized-modification-device-posture-profile)[Resolving Update Issues to Zscaler Client Connector 1.4.3](https://help.zscaler.com/zscaler-client-connector/resolving-update-issues-zscaler-app-1.4.3)[Login Failures with Zscaler Client Connector Using Active Directory](https://help.zscaler.com/zscaler-client-connector/login-failures-zscaler-client-connector-using-active-directory)[Resolving Zscaler Client Connector Linux 1.2 DNS Configuration Issues for VPNs](https://help.zscaler.com/zscaler-client-connector/resolving-zscaler-client-connector-linux-1.2-dns-configuration-issues-vpns)[Resolving Auto-Update Issues for Zscaler Client Connector Linux 1.2](https://help.zscaler.com/zscaler-client-connector/resolving-auto-update-issues-zscaler-client-connector-linux-1.2)[Upgrading to Zscaler Client Connector version 1.9 for Android](https://help.zscaler.com/zscaler-client-connector/upgrading-android-1.9)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile -->

Title: Copying a Forwarding Profile | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile

Markdown Content:
# Copying a Forwarding Profile | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Forwarding Traffic Management](https://help.zscaler.com/zscaler-client-connector/forwarding-traffic-management)
*    Copying a Forwarding Profile 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Copying a Forwarding Profile

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

You can copy an existing Forwarding Profile and customize the profile to fit your needs, instead of having to configure each setting in a new profile.

Follow these steps to copy a Zscaler Client Connector Forwarding Profile:

You cannot copy the default profile.

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Forwarding Profile for Platforms**.
2.   Click **Add Forwarding Profile**.
3.   Click the **Copy**icon next to the profile you want to copy.

[See Image](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile#copy)

[](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile)![Image 5: Copy a Forwarding Profile to customize](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/copying-forwarding-profile-doc-62977/forwarding_copy_icon.png)[Close](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile#copy)

1.   In the **Copy Forwarding Profile**window:
    1.   Enter a new name in the **Profile Name**field.
    2.   Change the settings as desired.
    3.   Click **Save**.

[See Image](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile#copy-window)

[](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile)![Image 6: Copy Forwarding Profile window](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/copying-forwarding-profile-doc-62977/copy-forwarding-profile.png)[Close](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile#copy-window)

Your copied profile appears at the bottom of the Forwarding Profile table.

You can copy a maximum of 10 profiles at the same time.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Forwarding Profiles](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles)[Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector)[Searching for a Forwarding Profile](https://help.zscaler.com/zscaler-client-connector/searching-forwarding-profile)[About Trusted Networks](https://help.zscaler.com/zscaler-client-connector/about-trusted-networks)[Configuring Trusted Networks for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector)[Searching for a Trusted Network](https://help.zscaler.com/zscaler-client-connector/searching-trusted-network)[About Z-Tunnel 1.0 & Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/about-z-tunnel-1.0-z-tunnel-2.0)[Best Practices for Deploying Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/best-practices-deploying-z-tunnel-2.0)[Migrating from Z-Tunnel 1.0 to Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/migrating-z-tunnel-1.0-z-tunnel-2.0)[Configuring Dedicated Proxy Ports](https://help.zscaler.com/zscaler-client-connector/configuring-dedicated-proxy-ports)[Copying a Forwarding Profile](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration -->

Title: Zscaler Client Connector and Imprivata Integration | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Zscaler Client Connector and Imprivata Integration

This feature is available only for Zscaler Client Connector version 4.4 and later for Windows.

Zscaler Client Connector supports seamless integration with Imprivata OneSign. This integration is useful if your users authenticate on shared devices and allows you to:

*   Silently log Imprivata users in to and out of Zscaler Client Connector.
*   Apply different security policies on the same device based on the user.
*   Log activity for each user.

Zscaler and Imprivata are technology partners. To learn more about integrating Zscaler and Imprivata, see the [Zscaler and Imprivata Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-imprivata-deployment-guide).

In the following example, a nurse taps an Imprivata badge to access the shared workstation in Room 1. Zscaler Client Connector silently logs them in, applies the appropriate security policy, and begins tracking end user activity. When the doctor accesses the shared workstation, Zscaler Client Connector logs out the nurse and logs in the doctor. The nurse and doctor can later access the workstation in Room 2, and Zscaler Client Connector applies the same security profiles and logging as in Room 1.

![Image 2: An example of Imprivata integration](https://help.zscaler.com/downloads/client-connector/platform-and-authentication-management/zscaler-client-connector-and-imprivata-integration/Imprivata-Integration-Example.png)

The Zscaler Client Connector system tray icon does not display when Imprivata users are logged in. Imprivata users can access Zscaler Client Connector from the Start menu. Updates to Zscaler Client Connector or changes in the Zscaler Admin Console that result in temporary downtime of the app do not affect the Imprivata login.

The following recommendations are best practices for integrating Zscaler Client Connector with Imprivata:

*   Disable the [Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/using-zscaler-notification-framework) to prevent pop-up messages for users.
*   Set your Private Access policies to expire at the same time.

To use Zscaler Client Connector integration with Imprivata:

1.   Deploy Zscaler Client Connector with a customized install option. To learn more, see [Customizing Zscaler Client Connector with Install Options for MSI](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi) and [Customizing Zscaler Client Connector with Install Options for EXE](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe).
2.   Enable Integrated Windows Authentication (IWA). To learn more about enabling IWA, see [Configuring Automatic Private Access Reauthentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication).
3.   (Optional) If you [use WebView2 authentication](https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication), enter additional domains if the user login domain is different from the IdP domain.
4.   View the type of user accessing a device (Windows or Imprivata) on the [Zscaler Client Connector Registered Device Details page](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
5.   View user activity on the Zscaler Admin Console.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-failed-posture-devices -->

Title: About Failed Posture Devices | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-failed-posture-devices

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Failed Posture Devices

Failed Posture Devices is available only if you have Zscaler Digital Experience (ZDX) or Zscaler Client Connector Telemetry enabled.

From the Zscaler Admin Console, you can view a list of devices that failed posture checks. To view a trend of devices failing posture checks and what posture profiles are involved, see [Understanding the Zscaler Client Connector Dashboard](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-portal-dashboard).

Failed posture devices provide the following benefits and allow you to:

*   Review the list of devices that failed their posture check.
*   Filter the list by time, OS, or posture profile to immediately identify devices.
*   Quickly identify the users and attributes of those devices to accelerate remediation.

On the Failed Posture Devices page (Infrastructure > Connectors > Client > Failed Posture Devices), you can do the following:

1.   Filter the list of failed devices with the following options:
    *   **Time**: View devices for failed postures ranging from three-hour intervals up to 24 hours.
    *   **Failed Device Posture**: View failed devices for that specific device posture profile.
    *   **OS**: View devices for either Windows or macOS or for both of the operating systems. By default, both operating systems are shown.

2.   Export the list of devices as a CSV file. Select **Export All** to export the information for all devices and then select a time interval to export the information for a specific time frame up to 24 hours within the past 14 days. Select **Export View** to export the information currently visible on the page.
3.   View a list of all failed devices for a specific failed device posture. For each device, you can view:
    *   **User ID**: The enrolled user for the device.
    *   **OS Type**: The device operating system.
    *   **Device Model**: The device model.
    *   **First Failure Time**: The time and date the first posture failure occurred.
    *   **Last Failure Time**: The time and date the most recent posture failure occurred.
    *   **Count**: The number of times the device posture failed for the device.

![Image 2: Failed Posture Devices page](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-failed-posture-devices/zclient-connector-failed-posture-devices-page.png)

By default, each page shows up to 50 devices. View more devices by using the table pagination feature located at the bottom of each page.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/bypassing-fqdn-subdomains-vpn-gateway-bypass -->

Title: Bypassing FQDN Subdomains for VPN Gateway Bypass | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/bypassing-fqdn-subdomains-vpn-gateway-bypass

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Bypassing FQDN Subdomains for VPN Gateway Bypass

You can control whether Zscaler Client Connector bypasses the subdomains for FQDNs that are entered in [VPN Gateway Bypass](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#global). Zscaler Client Connector bypasses the subdomains for Zscaler Tunnel (Z-Tunnel) 1.0 and Tunnel with Local Proxy by default unless you enable this option.

This feature applies only to Zscaler Client Connector for Windows:

*   Version 4.5.0.508 and later
*   Version 4.6.0.334 and later
*   Version 4.7.0.141 and later

To enable the option:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**User Privacy**.
2.   Enable **Do not Explicitly Bypass Subdomains of the FQDNs listed in VPN Gateway Bypass**.

![Image 2: Enable the Do not Explicitly Bypass the Subdomains of the FQDNs option](https://help.zscaler.com/downloads/zclient-connector-user-privacy-bypass-subdomain-fqdn.png)

3.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics -->

Title: Using Zscaler Diagnostics | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Using Zscaler Diagnostics

This article details how to access Zscaler Diagnostics features that are available for use after IdP authentication. This is the same information as provided in [Troubleshooting Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector), except users can access this information without being logged in to Zscaler Client Connector on a Windows or macOS device.

*   [Windows](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#windows)

[](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics)This feature is available with Zscaler Client Connector version 3.2 or later for Windows.

The Windows Policy setting for **ZPA Machine Authentication** must be enabled so users can authenticate and start the machine tunnel.

To authenticate and start the machine tunnel from the Windows login screen:

    1.   In the bottom-left corner of the screen, click **Zscaler Diagnostics**.
    2.   Click **Zscaler Options**.

[See image.](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#windows-login)

    3.   Based on your organization’s authentication mechanism, you are prompted to complete one of the following steps:
        1.   Enter the IdP credentials for Username and Password.

[See image.](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#idp-credentials)

        2.   On a separate device (e.g., a mobile phone), enter the link that displays in a browser and then enter the passcode. You can scan the QR code to automatically go to the verification site.

[See image.](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#oauth2.0-credentials)

After successful authentication, the **Tunnel Status** window displays current information about the machine tunnel, including the number of packets sent or received, and whether the tunnel is running on a trusted network. Users have the option to stop the machine tunnel by clicking **Turn Off**.

[See image.](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#tunnel-status)

The following options are available in the **Diagnostics**menu:

    *   **Start Packet Capture**: If your organization's admin enabled packet captures, you can use this feature when reproducing an issue. To learn more, see [Using the Start Packet Capture Option](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture).
    *   **Restart Service**: You can restart the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels). Restarting does not impact security enforcement.
    *   **Report an Issue**: If your organization's admin enabled support access, you can use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support (your support admin also receives a copy of this ticket). To learn more, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector).
    *   **Include Packet Captures**: Select this option to include [packet capture](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture) files when using the Report an Issue feature.
    *   **Clear Logs**: You can clear stored logs.
    *   **Update Policy**: You can manually refresh the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels) policy.

[See image.](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#tunnel-diagnostics)

[Close](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#windows)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#macOS)

[](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics)To start the machine tunnel from the macOS login screen, in the bottom-left corner of the screen, click the Zscaler icon. This machine tunnel icon is only visible when a device logs out or reboots. The icon is not visible when a user locks the screen.

The **Tunnel Status** window displays current information about the machine tunnel, including the number of packets sent or received, and whether the tunnel is running on a trusted network.

The following options are available in the **Diagnostics**menu:

    *   **Restart Service**: You can restart the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels). Restarting does not impact security enforcement.
    *   **Report an Issue**: If your organization's admin enabled support access, you can use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support (your support admin also receives a copy of this ticket). To learn more, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector).
    *   **Clear Logs**: You can clear stored logs.
    *   **Update Policy**: You can manually refresh the [machine tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels) policy.

[Close](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics#macOS)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-zia-device-groups -->

Title: Enabling ZIA for Device Groups | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-zia-device-groups

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling ZIA for Device Groups

You can use Zscaler Service Entitlement to enroll Device Groups in Zscaler Internet Access (ZIA). Configuring ZIA using device groups allows you to assign entitlements and policy settings based on ownership through device posture profiles. For example, one user can have two devices, one personal and one employer-provided. The personal device can be enrolled in ZIA, and the employer-provided device can be enrolled in ZIA and Zscaler Private Access (ZPA).

To enable ZIA for device groups, you must deploy Zscaler Client Connector 3.9 or later. Contact Zscaler Support to enable this feature.

To enable ZIA for device groups:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left menu, select **Zscaler Service Entitlement**.
3.   Click the **Zscaler Internet Access (ZIA)**tab.
4.   To enable ZIA for device groups, ensure that **ZIA Enabled by Default**is disabled. If this setting is enabled, ZIA is available for all users and you cannot assign ZIA to a device group.
5.   Select one or more groups from the **Device Groups** drop-down menu.

Groups are defined in the Device Groups section in the Zscaler Client Connector Portal under Administration. For more information, see [About Device Groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups-0).

1.   Select **Logout ZCC when ZIA Entitlement is Enabled**to automatically log users out of Zscaler Client Connector when ZIA is enabled for a device group. Users can then log in again to enable the ZIA service. This applies to customers using ZPA only or ZPA and Zscaler Deception. When disabled, Zscaler Client Connector runs without the ZIA service until the next Zscaler Client Connector login.

![Image 2: Enable ZIA for Device Groups](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/enabling-zia-device-groups/Client_Connector_ZIA_Entitlement_1.png)

1.   Click **Save**.

Your users' devices are updated the next time they connect. If they're already connected, devices automatically update in 60 minutes. Users can manually update their devices in Zscaler Client Connector. On the **More**page, click **Update Policy**. After manually refreshing the device, they must reauthenticate on the**Private Access** page.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic -->

Title: Adding IP-Based Applications to Bypass Traffic | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding IP-Based Applications to Bypass Traffic

[Watch a video about Application Bypass](https://fast.wistia.net/embed/iframe/vslmsh229d) (shows legacy UI).

In addition to using predefined applications (e.g., Microsoft Teams and Zoom), you can add IP-based applications to bypass traffic. Select the applications to bypass in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

To add an IP-based application to bypass traffic:

1.   Go to **Infrastructure**>**Common Resources**>**Application**>**IP Based**.
2.   On the **IP-Based**tab, click **Custom**.
3.   Click **Add Application**.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic#add)

4.   In the **Add Application** window, complete the following fields:

Android only supports IP-based bypasses and does not support ports and protocols. All traffic that is associated with the specified IP is bypassed.

    *   For **General**: Enter the application’s name in the **Name**field.
    *   For **IPv4**or **IPv6****Addresses**:
        *   **Port**: Enter valid port numbers (1–65535) or a range of port numbers (e.g., 80–100) or an asterisk (*), separated by commas.
        *   **Protocol**: Select one of the following protocols for your application:
            *   **UDP**
            *   **TCP**
            *   Asterisk (*****) to represent any protocol

        *   **IPv4 Addresses** or **IPv6 Addresses**: Enter the IPv4/IPv6 addresses or subnet for the application, separated by commas.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic#add_IP)

5.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/browser-based-authentication-fails-when-using-certain-browsers-launch-zscaler-client-connector-linux -->

Title: Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/browser-based-authentication-fails-when-using-certain-browsers-launch-zscaler-client-connector-linux

Markdown Content:
# Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows | Zscaler

[![Image 1: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Troubleshooting](https://help.zscaler.com/zscaler-client-connector/troubleshooting)
*    Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows 

![Image 2: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 3: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

When using the Google Chrome browser version 116 or Mozilla Firefox browser version 117 for browser-based authentication, Zscaler Client Connector for both Windows and Linux doesn’t automatically launch. You must click **Launch**on your browser to complete authentication.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Zscaler Client Connector Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors)[Zscaler Client Connector: Windows Registry Keys](https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys)[Zscaler Client Connector: Connection Status Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)[Zscaler Client Connector: Private Access Authentication Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)[Zscaler Services Inaccessible After Upgrading to Zscaler Client Connector versions 4.4 and 4.4.1 for iOS](https://help.zscaler.com/zscaler-client-connector/zscaler-services-inaccessible-after-upgrading-zscaler-client-connector-versions-4-4-and-4-4-1-ios)[DNS Resolution Fails through the ZIA Public Service Edge](https://help.zscaler.com/zscaler-client-connector/dns-resolution-fails-through-zia-public-service-edge)[Captive Portal Sign-In Fails for Chromebook Users](https://help.zscaler.com/zscaler-client-connector/captive-portal-sign-fails-chromebook-users)[Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page)[AppArmor Causes Auto-Upgrade to Zscaler Client Connector version 3.7.1 for Linux to Fail](https://help.zscaler.com/zscaler-client-connector/apparmor-causes-auto-upgrade-zscaler-client-connector-version-3-7-1-linux-fail)[Firewall Posture Check Failure on macOS Sequoia](https://help.zscaler.com/zscaler-client-connector/firewall-posture-check-failure-macos-sequoia)[Microsoft Outlook and Microsoft Teams Not Accessible](https://help.zscaler.com/zscaler-client-connector/microsoft-outlook-and-microsoft-teams-not-accessible)[Login Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/log-failure-zscaler-client-connector-android-chromeos--version-1.12)[Browser-Based Authentication Fails When Using Certain Browsers to Launch Zscaler Client Connector for Linux and Windows](https://help.zscaler.com/zscaler-client-connector/browser-based-authentication-fails-when-using-certain-browsers-launch-zscaler-client-connector-linux)[DNS Request Failure in Zscaler Client Connector version 4.1.0.89](https://help.zscaler.com/zscaler-client-connector/dns-request-failure-zscaler-client-connector-version-4.1.0.89)[DNS Resolution Failure in Zscaler Client Connector for Android on ChromeOS version 1.12](https://help.zscaler.com/zscaler-client-connector/dns-resolution-failure-zscaler-client-connector-android-chromeos-version-1.12)[Upgrading to Zscaler Client Connector 3.7 for Windows](https://help.zscaler.com/zscaler-client-connector/upgrading-zscaler-client-connector-3.7-windows)[Missing Zscaler Client Connector GNOME Tray icon for Linux](https://help.zscaler.com/zscaler-client-connector/missing-zscaler-client-connector-gnome-tray-icon)[Upgrading to Windows 10, Version 2004](https://help.zscaler.com/zscaler-client-connector/upgrading-windows-10-version-2004)[Supporting M1 Processors](https://help.zscaler.com/zscaler-client-connector/supporting-m1-processors)[Upgrading to macOS Big Sur](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-big-sur)[Upgrading to macOS Catalina](https://help.zscaler.com/zscaler-client-connector/upgrading-macos-catalina)[Upgrading to Android 10](https://help.zscaler.com/zscaler-client-connector/upgrading-android-10)[Using Zscaler Client Connector with Cisco AnyConnect on macOS Catalina](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-cisco-anyconnect-macos-catalina)[Using the Unauthorized Modification Device Posture Profile](https://help.zscaler.com/zscaler-client-connector/using-unauthorized-modification-device-posture-profile)[Resolving Update Issues to Zscaler Client Connector 1.4.3](https://help.zscaler.com/zscaler-client-connector/resolving-update-issues-zscaler-app-1.4.3)[Login Failures with Zscaler Client Connector Using Active Directory](https://help.zscaler.com/zscaler-client-connector/login-failures-zscaler-client-connector-using-active-directory)[Resolving Zscaler Client Connector Linux 1.2 DNS Configuration Issues for VPNs](https://help.zscaler.com/zscaler-client-connector/resolving-zscaler-client-connector-linux-1.2-dns-configuration-issues-vpns)[Resolving Auto-Update Issues for Zscaler Client Connector Linux 1.2](https://help.zscaler.com/zscaler-client-connector/resolving-auto-update-issues-zscaler-client-connector-linux-1.2)[Upgrading to Zscaler Client Connector version 1.9 for Android](https://help.zscaler.com/zscaler-client-connector/upgrading-android-1.9)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi -->

Title: Troubleshooting Zscaler Client Connector for VDI | Zscaler

URL Source: https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi

Markdown Content:
![Image 1: icon-cloud-connector.svg](blob:http://localhost/6e416d15bcb50c1001e80e392c70c45c)

## Cloud & Branch Connector

### Troubleshooting Zscaler Client Connector for VDI

This article provides troubleshooting information and guidelines for Zscaler Client Connector for Virtual Desktop Infrastructure (VDI) deployment. You can review issues and corresponding solutions for Zscaler Client Connector for VDI. If you cannot reach a solution, contact Zscaler Support. Additionally, ensure that you bypass services you want to keep local to Microsoft Azure or Citrix. To learn more about bypasses, see [Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles).

*   [Registration](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#Registration)The following registration troubleshooting flow chart leads you through various Zscaler Client Connector for VDI situations and potential solutions.

The following table lists Zscaler Client Connector for VDI registration issues and their corresponding troubleshooting actions.

**Potential Causes****Troubleshooting Action**
Zscaler Client Connector for VDI is experiencing location fetch errors.Location fetch errors can occur for multiple reasons. Collect Zscaler Client Connector for VDI's logs and contact Zscaler Support.
The Zscaler Cloud & Branch Connector service interface is unreachable or Cloud & Branch Connector is displayed inactive.Test whether the Cloud & Branch Connector service interface is reachable with and without Zscaler Client Connector for VDI. To learn more, see the [Cloud Connector troubleshooting documentation](https://help.zscaler.com/cloud-branch-connector/troubleshooting).
The Zscaler Client Connector for VDI onboard parameter is not set.Ensure that the onboarding behavior is set to either `1` or `0`. The default is `1`, which allows Zscaler Client Connector for VDI to onboard itself to the Workload Communications (WLC) tenant. Setting the behavior to `0` disables this automatic behavior. To learn more, see [Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi).
Routing is not configured for `185.46.212.80`.By default, Zscaler Client Connector for VDI connects to `185.46.212.80`. Ensure that a route is configured for this destination pointing toward the Cloud Connector, Branch Connector, or a load balancer. 
[Close](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#Registration) 
*   [Authentication](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#Authentication)

The following authentication troubleshooting flow chart guides you through various Zscaler Client Connector for VDI situations and potential solutions.

The following table lists Zscaler Client Connector for VDI authentication issues and their corresponding troubleshooting actions.

**Potential Causes****Troubleshooting Action**
User authentication is failing.User authentication can fail for multiple reasons:

    *   When the VDI environment completes a backup and restoration of the roaming profile, Zscaler Client Connector for VDI stores and reads the user configuraton file in the roaming profile. This restoration process can take time. Configure the `CONFIGREADTIMEOUT` parameter so that Zscaler Client Connector for VDI waits a minimum of 0 and a maximum of 300 seconds for profiles to restore.
    *   If the virtual machine (VM) loads more slowly than Zscaler Client Connector for VDI, a timeout issue might occur. Configure the timeout parameter to wait for the window to load completely before showing the authentication page. To learn more, see [Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi).
Integrated Windows Authentication (IWA) is failing.IWA relies on browser and/or Internet settings, so review your configured settings and refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity/hybrid/connect/how-to-connect-sso-quick-start).
Okta ADSSO authentication is failing.Refer to the [Okta documentation](https://help.okta.com/en-us/content/topics/directory/configuring_agentless_sso.htm).
Profile backup and restore is not working.Ensure that the backup and restore IP addresses are bypassed.
FSLogix is not able to restore the user profile.Ensure that all FSLogix IP addresses are bypassed.
Zscaler Client Connector for VDI is not able to reach the identity provider (IdP).Ensure that there is a bypass from the Zscaler Admin Console and FQDN bypasses from the Cloud Connector.
Zscaler Client Connector for VDI is not able to reach Zscaler authentication domains.Ensure that all Zscaler authentication domains are bypassed.
You do not see the correct user when navigating to `ip.zscaler.com`.Ensure that all bypasses and authentications are functioning. Collect the packet captures and ensure that traffic is tunneled properly.

[Close](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#Authentication)

*   [Data Path](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#DataPath)

    *   [Internet & SaaS](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#ZIADataPath)The following Internet & SaaS data path troubleshooting flow chart guides you through various Zscaler Client Connector for VDI situations and potential solutions.

To learn more about routing traffic through `185.46.212.80`, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)

The following table lists Zscaler Client Connector for VDI Internet & SaaS data path issues and their corresponding troubleshooting actions.

**Potential Causes****Troubleshooting Action**
The incorrect tunnel mode is configured for Zscaler Client Connector for VDI.You can configure Point-to-Point (P2P) or Any-to-Any (A2A) tunneling mode for Zscaler Client Connector for VDI. To determine which tunneling mode is appropriate, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)

A2A Tunneling Mode is not recommended and is being deprecated.
Traffic is not reaching Internet & SaaS.Review the Cloud Connector session insights to verify whether the traffic is forwarded to Internet & SaaS. To learn more, refer to the [Cloud Connector troubleshooting documentation](https://help.zscaler.com/cloud-branch-connector/troubleshooting).
Traffic is not reaching Cloud Connector or Branch Connector.Ensure that workload routes are properly configured to send the traffic to Cloud Connector and the traffic forwarding rule is configured to send traffic to Internet & SaaS.
The incorrect policy is applied in Internet & SaaS.In the Zscaler Admin Console, check the Internet & SaaS insights logs and match them with the rule preceding the correct rule. 
[Close](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#ZIADataPath) 
    *   [Private Access](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#ZPADataPath)The following Private Access data path troubleshooting flow chart guides you through various Zscaler Client Connector for VDI situations and potential solutions.

To learn more about P2P and A2A tunneling modes, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)

The following table lists Zscaler Client Connector for VDI Private Access data path issues and their corresponding troubleshooting actions.

**Potential Causes****Troubleshooting Action**
Server Message Block (SMB), Kerberos, Windows network, DNS, ICMP, or network file system (NFS) traffic are not able to reach the destination application.SMB, Kerberos, Windows network, DNS, ICMP, and NFS traffic are treated as system user traffic by Zscaler Client Connector for VDI. Ensure that Private Access has a correct access policy configured for the system user.

This is a Windows limitation, not a Zscaler limitation.
Traffic is not reaching Cloud Connector or Branch Connector.Ensure that workload routes are properly configured to send the traffic to Cloud Connector and the traffic forwarding rule is configured to send traffic to Private Access.
The incorrect access policy is applied in Private Access.Ensure that the access policy is configured with the client type as Zscaler Client Connector for VDI. If the access policy contains user attributes, enable System for Cross-domain Identity Management (SCIM). SAML is not supported.
The incorrect application segment is selected in Private Access.Check the application segment configuration in Private Access to verify that it is correct.
Synthetic IP pool is not included in the VDI forwarding profile.Ensure that the VDI forwarding policy includes synthetic IP pools in the Include list.
DNS requests are not being received.Ensure that Cloud Connector is seeing DNS requests and able to match them to the Private Access application or FQDN. To learn more, see [Step-by-Step Configuration Guide for Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi). 
[Close](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#ZPADataPath) 

[Close](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi#DataPath)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos -->

Title: Deploying Zscaler Client Connector with Google Workspace for ChromeOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Deploying Zscaler Client Connector with Google Workspace for ChromeOS

This guide is for admins only. If you are an end user, contact your organization’s administrator for deployment-related details.

With Google Workspace, you can distribute App Store apps, including apps purchased in volume, to mobile devices. After an app is distributed, you can use Google Workspace to manage future updates to Zscaler Client Connector. You must have a required license for managing ChromeOS devices before deployment.

To deploy Zscaler Client Connector with Google Workspace:

*   [Step 1: Add and Manage Public Apps with Google Workspace](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos#apps-with-google-workspace)

    1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos)Log in to the Google Admin Console.
    2.   In the left-side navigation, go to **Devices**>**Chrome**>**Apps & Extensions**.

    1.   On the right side of the screen, select the **Users & Browsers** tab.
    2.   Click the **+** button located in the bottom-right corner of the screen and select the **Add from Google Play** icon.

    1.   From the Google Play Store, select the **Client Connector - Chromebook**app for Chrome devices.
    2.   In the expanded section on the right, under the **Installation policy** drop-down menu, select **Force install** to install the application at the endpoint.

    1.   Under **Managed configuration,**enter the following JSON format:

```
{
"userDomain":"<org’s_domain>",
"cloudName":"zscalerone",
"autoEnrollWithMDM":"1",
"deviceToken":"<token_value>",
"externalDeviceId":"${DEVICE_SERIAL_NUMBER}"
}
```

    1.   You can add [Zscaler parameters](https://help.zscaler.com/zscaler-client-connector/parameters-guide-zscaler-client-connector-android-and-android-chromeos) to the JSON format based on your organization's needs and click **Save**.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos#apps-with-google-workspace)

*   [Step 2: Configure Always-On VPN](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos#config-always-on-vpn)

    1.   [](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos)From the left-side navigation, go to **Devices**>**Settings**.
    2.   In the **Search or add a filter**section, search for **Always on VPN**.
    3.   Click **Edit in legacy view**. A new tab opens for configuring the Always on VPN app.

    1.   Select the app. Apps with the **Installation Policy** configured as **Force install** appear in the search. Apps with any other configurations do not appear here. Ensure that your app's **Installation Policy** is **Force install**.

    1.   Click **OK**.

Users might be required to restart Chromebooks for the changes to be applied.

[Close](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-google-workspace-chromeos#config-always-on-vpn)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector -->

Title: Troubleshooting Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Troubleshooting Zscaler Client Connector

This article details the **Troubleshoot** section features of Zscaler Client Connector on different OSs:

*   [Windows](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#win)

[](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)The **Troubleshoot** section includes the following features on Windows devices:

![Image 2: The Troubleshoot menu features of Zscaler Client Connector for Windows](https://help.zscaler.com/downloads/zscaler-client-connector/end-user-guide/troubleshooting-zscaler-client-connector/Export_Logs_Zapp_Windows.png)

    *   **Start Packet Capture:**If your organization's admin enabled packet captures, use this feature when reproducing an issue. To modify the settings for Start Packet Capture, see [Enabling Packet Capture for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector).
    *   **Report an Issue:**If your organization's admin enabled in-app support access, use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support. Your support admin receives a copy of this ticket. After you submit the form, you receive an email acknowledging the support request. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win).
    *   **Export Logs**: Click this option to export logs to a ZIP file, then email that folder to your organization’s support admin.
    *   **Restart Service:**Click to restart the app. Restarting does not impact security enforcement.
    *   **Repair App:**If you select this option, the app attempts to repair itself by reinstalling app drivers and services. Zscaler recommends trying this option before reporting an issue.
    *   **Revert App:** If your organization’s admin enabled the revert option, click this option to revert to the previous version of Zscaler Client Connector. Depending on your organization’s policies, you might be required to enter a password.
    *   **Disable Tamper Protection**: Click this option to disable anti-tampering protection. When prompted, provide the [Disable Anti-Tampering OTP](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device). To learn more, see [Anti-Tampering for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector).
    *   **Clear Logs:**Click this option to clear stored logs.
    *   **Log Mode:**Change the mode in which Zscaler Client Connector generates logs. The change is effective for that connection session only. At the start of the next connection session, the app returns to the default log mode set by your organization. The following is a description of each log mode.

        *   **Error:**Logs only when the app encounters an error and functionality is affected.
        *   **Warn:**Logs when the app is functioning but is encountering potential issues, or logs when conditions for the Error log mode are met.
        *   **Info:**Logs general app activity, or logs when conditions for the Warn log mode are met.
        *   **Debug:** Logs all app activity that could assist Zscaler Support in debugging issues, or logs when conditions for the Info log mode are met.

To see where the logs are stored, click **Report an Issue** and then click **Show/Hide logs**. To learn more, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win).

[Close](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#win)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#macos)

[](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)The **Troubleshoot** section includes the following features on macOS devices:

![Image 3: The Troubleshoot menu features of Zscaler Client Connector for macOS](https://help.zscaler.com/downloads/zscaler-client-connector/end-user-guide/troubleshooting-zscaler-client-connector/troubleshoot-macos_0.png)

    *   **Start Packet Capture:**If your organization's admin enabled packet captures, use this feature when reproducing an issue. For instructions on using this option, see [Using the Start Packet Capture Option](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture).
    *   **Report an Issue**:If your organization's admin enabled in-app support access, use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support. Your support admin receives a copy of this ticket. After you submit the form, you receive an email acknowledging the support request. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win).
    *   **Restart Service**:Click to restart the app. Restarting does not impact security enforcement.
    *   **Revert App:** If your organization’s admin enabled the revert option, click this option to revert to the previous version of Zscaler Client Connector. Depending on your organization’s policies, you might be required to enter a password.
    *   **Clear Logs**:Click this option to clear stored logs.
    *   **Log Mode**:Change the mode in which Zscaler Client Connector generates logs. The change is effective for that connection session only. At the start of the next connection session, the app returns to the default log mode set by your organization. The following is a description of each log mode.
        *   **Error**:Logs only when the app encounters an error and functionality is affected.
        *   **Warn**:Logs when the app is functioning but is encountering potential issues, or logs when conditions for the Error log mode are met.
        *   **Info**:Logs general app activity, or logs when conditions for the Warn log mode are met.
        *   **Debug**: Logs all app activity that could assist Zscaler Support in debugging issues, or logs when conditions for the Info log mode are met.

    *   **Run Zscaler Diagnostics**: Provides a one-time snapshot of the system's network state by performing various diagnostic tests. To run diagnostics using CLI, see [Interacting with Zscaler Client Connector Remotely](https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely).
    *   **Export Logs**: Click this option to export logs to a ZIP file, then email that folder to your organization’s support admin.

[Close](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#macos)

*   [Linux](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#Linux)

[](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)The **Troubleshoot** section includes the following features on Linux devices:

![Image 4: Zscaler Client Connector More page in Linux highlighting the Troubleshoot section](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/troubleshooting-zscaler-client-connector-draft-doc-41077/Client%20Connector%20Linux.png)

    *   **Start Packet Capture:**If your organization's admin enabled packet captures, use this feature when reproducing an issue. For instructions on using this option, see [Using the Start Packet Capture Option](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture).
    *   **Export Logs**: Click this option to export logs to a ZIP file, then email that folder to your organization’s support admin.
    *   **Restart Service**: Click this option to restart the app. Restarting does not impact security enforcement.
    *   **Clear Logs**: Click this option to clear stored logs.
    *   **Log Mode:**Change the mode in which Zscaler Client Connector generates logs. The change is effective for that connection session only. At the start of the next connection session, the app returns to the default log mode set by your organization. The following is a description of each log mode:
        *   **Error:**Logs only when the app encounters an error and functionality is affected.
        *   **Warn:**Logs when the app is functioning but is encountering potential issues, or logs when conditions for the Error log mode are met.
        *   **Info:**Logs general app activity, or logs when conditions for the Warn log mode are met.
        *   **Debug:** Logs all app activity that could assist Zscaler Support in debugging issues, or logs when conditions for the Info log mode are met.

[Close](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#Linux)

*   [Android](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#android)

[](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)The **Troubleshoot** section includes the following features on Android devices:

![Image 5: Troubleshooting Options on Zscaler Client Connector for Android](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/troubleshooting-zscaler-client-connector-doc-55419/Troubleshooting_Android.jpg)

    *   **Packet Capture:**If your organization's admin enabled packet captures, use this feature when reproducing an issue. For instructions on using this option, see [Using the Start Packet Capture Option](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture).
    *   **Report an Issue**: If your organization’s admin enabled in-app support access, use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support. Your support admin receives a copy of this ticket. After you submit the form, you receive an email acknowledging the support request. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win).
    *   **Restart Service**: Tap this option to restart the app. Restarting does not impact security enforcement. However, you can click **Restart Service** only once within a 30-second period.
    *   **Clear Logs**: Tap this option to clear stored logs.
    *   **Export Logs**: Tap this option to export logs to a ZIP file, then email that file to your organization’s support admin.
    *   **Log Mode**: Change the mode in which the app generates logs. The change is effective for that connection session only. At the start of the next connection session, the app returns to the default log mode set by your organization. The following is a description of each log mode.
        *   **Error**: Logs only when the app encounters an error and functionality is affected.
        *   **Warn**: Logs when the app is functioning but is encountering potential issues or when conditions for the Error log mode are met.
        *   **Info**: Logs general app activity or when conditions for the Warn log mode are met.
        *   **Debug**: Logs all app activity that could assist Zscaler Support in debugging issues or when conditions for the info log mode are met.
        *   **Verbose**: Logs when invoked by some external events (e.g., Firebase Push Notification or Mobile Manager flags).

[Close](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#android)

*   [Android on ChromeOS](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#chrome)

[](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)The **Troubleshoot** section includes the following features on Android on ChromeOS devices:

![Image 6: Troubleshooting Options on Zscaler Client Connector for Chrome OS](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/troubleshooting-zscaler-client-connector-doc-55419/ChromeOS_Troubleshooting.png)

    *   **Packet Capture:**If your organization's admin enabled packet captures, use this feature when reproducing an issue. For instructions on using this option, see [Using the Start Packet Capture Option](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture).
    *   **Report an Issue**: If your organization’s admin enabled in-app support access, use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support. Your support admin receives a copy of this ticket. After you submit the form, you receive an email acknowledging the support request. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win).
    *   **Restart Service**: Tap this option to restart the app. Restarting does not impact security enforcement. However, you can click **Restart Service** only once within a 30-second period.
    *   **Clear Logs**: Tap this option to clear stored logs.
    *   **Export Logs**: Tap this option to export logs to a ZIP file, then email that file to your organization’s support admin.
    *   **Log Mode**: Change the mode in which the app generates logs. The change is effective for that connection session only. At the start of the next connection session, the app returns to the default log mode set by your organization. The following is a description of each log mode.
        *   **Error**: Logs only when the app encounters an error and functionality is affected.
        *   **Warn**: Logs when the app is functioning but is encountering potential issues or when conditions for the Error log mode are met.
        *   **Info**: Logs general app activity or when conditions for the Warn log mode are met.
        *   **Debug**: Logs all app activity that could assist Zscaler Support in debugging issues or when conditions for the info log mode are met.

[Close](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#chrome)

*   [iOS](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#ios)

[](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)The **Troubleshoot** section includes the following features on iOS devices:

![Image 7: The Troubleshoot Option on Zscaler Client Connector for iOS](https://help.zscaler.com/downloads/client-connector/end-user-guide/troubleshooting-zscaler-client-connector/zscaler-client-connector-troubleshooting-ios.png)

    *   **Start Packet Capture:**If your organization's admin enabled packet captures, use this feature when reproducing an issue. For instructions on using this option, see [Using the Start Packet Capture Option](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-app#using-start-packet-capture).
    *   **Report an Issue**: If your organization’s admin enabled in-app support access, use this feature to report an issue. When you submit the form, depending on your organization's setup, Zscaler Client Connector can send an email to your organization's support admin or submit a ticket directly to Zscaler Support. Your support admin receives a copy of this ticket. After you submit the form, you receive an email acknowledging the support request. For instructions on completing the form, see [Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector#win).
    *   **Restart Service**: Tap this option to restart the app. Restarting does not impact security enforcement.
    *   **Export Logs**: Tap this option to export logs to a ZIP file, then email that file to your organization’s support admin.
    *   **Clear Logs**: Tap this option to clear stored logs.
    *   **Log Level**: Change the mode in which the app generates logs. The change is effective for that connection session only. At the start of the next connection session, the app returns to the default log mode set by your organization. The following is a description of each log mode.
        *   **Error**: Logs only when the app encounters an error and functionality is affected.
        *   **Warn**: Logs when the app is functioning but is encountering potential issues or when conditions for the Error log mode are met.
        *   **Info**: Logs general app activity or when conditions for the Warn log mode are met.
        *   **Debug**: Logs all app activity that could assist Zscaler Support in debugging issues or when conditions for the info log mode are met.

[Close](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector#ios)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/searching-device-groups -->

Title: Searching for Device Groups | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/searching-device-groups

Markdown Content:
# Searching for Device Groups | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Client Connector

- [x] All

- [x] Getting Started with Zscaler

- [x] Internet & SaaS (ZIA)

- [x] Private Access (ZPA)

- [x] Digital Experience Monitoring (ZDX)

- [x] Workflow Automation

- [x] Data Security Posture Management (DSPM)

- [x] Client Connector

- [x] Cloud & Branch Connector

- [x] Zscaler Cellular

- [x] Zero Trust Branch

- [x] Unified Vulnerability Management (UVM)

- [x] Asset Exposure Management

- [x] External Attack Surface Management

- [x] SOC Workbench

- [x] Deception

- [x] Identity Protection

- [x] Breach Predictor

- [x] Zero Trust Browser

- [x] Risk360

- [x] Business Insights

- [x] Authentication Service

- [x] Zscaler SDK for Mobile Apps

- [x] AI Asset Management

- [x] Secure Access to AI Apps

- [x] Secure AI Apps & Infrastructure

- [x] Legacy Zscaler APIs

- [x] Logs & Fair Use

- [x] Zscaler Support Troubleshooting Runbooks

- [x] Zscaler Deployments & Operations

- [x] Zscaler Technology Partners

- [x] Multi-Tenant Portal

- [x] Product Usage Terms & Policies

EN

English 日本語

*   [Zscaler Client Connector Help](https://help.zscaler.com/zscaler-client-connector)
*   [Zscaler Service Entitlement](https://help.zscaler.com/zscaler-client-connector/zscaler-service-entitlement)
*    Searching for Device Groups 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Searching for Device Groups

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

You can quickly locate a specific device group using the Search field.

To find a Device Group using the Search field:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left-side navigation, select **Device Groups**.
3.   In the **Search**field, select a search option from the drop-down menu. You can search by Group Name and Device Posture.

When searching by Device Posture, you must enter the exact Device Posture name.

1.   Enter search words and press `Enter` to display your search results.

To clear the **Search**field, delete the search term you typed and press `Enter`.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About Zscaler Service Entitlement](https://help.zscaler.com/zscaler-client-connector/about-zscaler-service-entitlement)[Enabling ZPA for a Group of Users](https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-zpa-group-users)[Configuring ZPA Machine Tunnel for All](https://help.zscaler.com/zscaler-client-connector/configuring-zpa-machine-tunnel-all)[Enabling ZDX for All Users](https://help.zscaler.com/zscaler-client-connector/enabling-zdx-all-users)[Enabling ZDX for a Group of Users](https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-zdx-group-users)[Enabling Deception for a Group of Users](https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-deception-group-users)[About Device Groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups)[Creating Device Groups](https://help.zscaler.com/zscaler-client-connector/create-device-groups)[Enabling Device Groups](https://help.zscaler.com/zscaler-client-connector/enabling-device-groups)[Enabling ZPA for Device Groups](https://help.zscaler.com/zscaler-client-connector/enabling-zpa-device-groups)[Enabling Zscaler Deception for Device Groups](https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-deception-device-groups)[Enabling ZDX for Device Groups](https://help.zscaler.com/zscaler-client-connector/enabling-zdx-device-groups)[Enabling ZIA for Device Groups](https://help.zscaler.com/zscaler-client-connector/enabling-zia-device-groups)[Searching for Device Groups](https://help.zscaler.com/zscaler-client-connector/searching-device-groups)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic -->

Title: Adding Process-Based Applications to Bypass Traffic | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding Process-Based Applications to Bypass Traffic

[Watch a video about Application Bypass](https://fast.wistia.net/embed/iframe/vslmsh229d) (shows legacy UI).

You can add process-based applications in the Zscaler Admin Console to bypass traffic. Add information such as paths, app identifiers, executable signatures, and certificate attributes to help identify process-based applications that can frequently change. You can then select these applications in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) to bypass traffic for both Zscaler Tunnel (Z-Tunnel) 1.0 and Z-Tunnel 2.0.

After you add the process-based application to the app profile, you must restart the application before traffic is bypassed.

You can add process-based applications for each of the following platforms:

*   [Windows](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic#windows)

[](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic)To add a process-based application to bypass traffic:

    1.   Go to **Infrastructure**>**Common Resources**>**Application >****Process-Based**.
    2.   On the **Process-Based** tab, click **Add Application**.

![Image 2: Process-Based Application Bypass](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/searching-app-profile/ZClient-Connector-AddingProBased.png)

    3.   In the **Add Application**window, complete the following fields:

        1.   **Name**: Enter the name of the process-based application.
        2.   **Path**: Enter the executable file names for the application. You can use wildcards or environment variables as follows:
            *   Wildcards:
                *   Use a single asterisk (*) to match any full single path component. For example: `*\Program\Apps\MSApps\ms-teams.exe`.

You can’t use a wildcard for partial components.

                *   Use two asterisks (**) to match any number of consecutive path components. For example: `**\ms-teams.exe`.

            *   Environment variables: The following example shows how to use environment variables in a path: `%APPDATA%\Zoom\bin`.

Press `Enter` or click the **Add**icon after each entry. You can add multiple items at the same time by separating each item with a comma and then pressing `Enter` or clicking the **Add**icon when finished.

    4.   **Matching Criteria**: Select one or more options from the drop-down menu and then complete the fields that display.

        *   **SHA2 Signature**
            *   **Application Name**: Enter a unique name of the executable signature that distinguishes it from other signatures.
            *   **Application Signature**: Enter the hash of the executable file.

        *   **Certificate Signer**
            *   **Certificate Name**: Enter a unique name of the code signing certificate to distinguish it from other signatures.
            *   **Certificate Thumbprint**: Enter the Certificate Thumbprint. You can use File Explorer to locate the certificate thumbprint.
                1.   Navigate to the Program Files folder where Zscaler is installed.
                2.   Go to **Zscaler**,then double-click **ZSATunnel**.
                3.   Right-click the **ZSATunnel** file and select **Properties**.
                4.   Click the **Digital Signatures** tab.
                5.   Select the signature from the **Signature list** window and click **Details**.
                6.   Click **View Certificate**.
                7.   Click the **Details**tab.
                8.   Scroll down and select **Thumbprint**.
                9.   Copy the thumbprint and paste it into the **Certificate Thumbprint** field.

        *   **Certificate Subject**
            *   **Certificate Name**: Enter a unique name of the certificate subject to distinguish it from other certificate subjects.
            *   **Certificate Subject**: Enter a name that matches the subject of the certificate.

![Image 3: Add Application Windows Process-Based Applications](https://help.zscaler.com/downloads/zclient-connector-app-bypass-add-application-windows.png)

    5.   Click **Save**.

After you enter a process-based application, you can select it in App Profiles. You must enable**Install WFP Driver** in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) to use Process-Based Application Bypass.

[Close](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic#windows)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic#macos)

[](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic)To use this feature, you must enable [Transparent Proxy-based Traffic Interception](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#mac-advanced). Applies to Zscaler Client Connector version 4.8 for macOS and later. Contact Zscaler Support to enable this feature.

To add a process-based application to bypass traffic:

    1.   Go to **Infrastructure**>**Common Resources**>**Application >****Process-Based**.
    2.   On the **Process-Based** tab, click **Add Application**.

![Image 4: Process-Based Application Bypass](https://help.zscaler.com/downloads/zclient-connector-app-bypass-process-based-macos_0.png)

    3.   In the **Add Application**window, complete the following fields:

        1.   **Name**: Enter the name of the process-based application.
        2.   **App Identifier**: Enter one of the following:
            *   The app identifier using one of the following formats:

                *   `TeamIdentifier.BundleIdentifier` (e.g., `UBF8T346G9.com.microsoft.teams2`).
                *   `BundleIdentifier` (e.g., `com.apple.Safari`).

If a single application requires multiple bundle identifiers to bypass, you can add all the bundle identifiers separated by commas (e.g., `BJ4HAAB9B3.zoom.us.ZoomAudioDevice, BJ4HAAB9B3.us.zoom.xos`). You can define a maximum of five bundle identifiers as part of a single application.

            *   The full path to the app if the app doesn't have a bundle identifier (e.g., `/opt/homebrew/Cellar/podman/5.7.0/libexec/podman/gvproxy`). Wildcards are not allowed.

Press `Enter` or click the **Add**icon after each entry. You can add multiple items at the same time by separating each item with a comma and then pressing `Enter` or clicking the **Add**icon when finished.

![Image 5: Add Application macOS Process-Based Applications](https://help.zscaler.com/downloads/zclient-connector-app-bypass-add-application-macos.png)

    4.   Click **Save**.

After you enter a process-based application, you can select it in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#mac-global).

[Close](https://help.zscaler.com/zscaler-client-connector/adding-process-based-applications-bypass-traffic#macos)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-digital-experience-zdx-workspace-one-uem-ios -->

Title: Deploying ZDX With Workspace ONE UEM for iOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-digital-experience-zdx-workspace-one-uem-ios

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Deploying ZDX With Workspace ONE UEM for iOS

This guide is for admins only. If you are an end user, contact your organization’s administrator for deployment-related details.

With Workspace ONE Unified Endpoint Management (UEM), you can configure and deploy Zscaler Digital Experience (ZDX) for iOS devices. Before following this guide, ensure that Zscaler Client Connector is installed on your device. To learn more, see [Deploying Zscaler Client Connector with Workspace ONE UEM for iOS](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-workspace-one-uem-ios).

This deployment guide applies to devices running iOS version 16 and later. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).

To deploy ZDX with Workspace ONE UEM for iOS devices:

1.   In the **Resources** section, go to **Profiles & Baselines**.

1.   Click **Add**>**Add Profile**.
2.   On the **Add Profile** page, click **iOS** and then **Device Profile**.
3.   Enter a name for your profile.
4.   Go to the **Custom Settings**section.
5.   Click **Add**.

1.   In the **Custom Settings** box, copy and paste the following XML text. You can customize the `ContentFilterUUID` and `PayloadIdentifier` strings.

```
<dict>
   <key>ContentFilterUUID</key>
   <string>6A53E317-8C38-42D6-9A49-BD2970F3A42A</string>
   <key>FilterSockets</key>
   <true />
   <key>FilterType</key>
   <string>Plugin</string>
   <key>Organization</key>
   <string>Zscaler</string>
   <key>PayloadDescription</key>
   <string>Zscaler ZDX Configuration</string>
   <key>PayloadDisplayName</key>
   <string>Zscaler ZDX Configuration</string>
   <key>PayloadIdentifier</key>
   <string>com.apple.webcontent-filter.E889893A-0185-422A-AC8F-797D4AA2766F</string>
   <key>PayloadOrganization</key>
   <string>Zscaler</string>
   <key>PayloadType</key>
   <string>com.apple.webcontent-filter</string>
   <key>PayloadUUID</key>
   <string>E889893A-0185-422A-AC8F-797D4AA2766F</string>
   <key>PayloadVersion</key>
   <integer>1</integer>
   <key>PluginBundleID</key>
   <string>com.zscaler.zscaler</string>
   <key>UserDefinedName</key>
   <string>Zscaler ZDX Configuration</string>
</dict>
```

1.   Click **Next**.
2.   Assign groups.
3.   Click **Save & Publish**.

To map a third-party app with the ZDX Configuration Profile created in the previous section:

1.   In the Resources section, go to **Apps**>**Native**>**Public**>**Add Application**.

1.   In the **Add Application** section:
    *   **Managed By**: Enter the entity that manages the application. For example, `Zscaler`.
    *   **Platform**: Select **Apple iOS**.
    *   **Source**: Select **Search App Store**.
    *   **Name**: Enter the name of the application. For example, `Firefox`. You can choose any third-party application that you want.

1.   Click **Next**.
2.   **Select** the application.

1.   Click **Save & Assign**.

1.   In the **Distribution** section, provide a name and assign groups.

1.   In the **Restrictions** section, enable the**Managed Access**and **Make App MDM Managed if User Installed** options.

1.   In the **Tunnel & Other Attributes**section, configure `ContentFilterUUID` with the value `6A53E317-8C38-42D6-9A49-BD2970F3A42A`. The ContentFilterUUID value must match with the value in the XML text in the previous section.

1.   Click **Save**.
2.   Review the assignment and click **Save**.

1.   In the next window, click **Publish**.

To verify that the profile is pushed to the device correctly, go to device **Settings**>**General**>**VPN & Device Management**>**Content Filter**. The Zscaler Content Filter shows as running. Clicking on the filter shows the apps associated with the filter.

Open the Zscaler Client Connector app on your iOS device to see the ZDX service running.

