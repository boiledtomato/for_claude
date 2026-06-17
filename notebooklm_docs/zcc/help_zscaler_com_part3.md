# ZCC — help.zscaler.com (続き 3)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2023 -->

Title: Release Upgrade Summary (2023) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2023

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Release Upgrade Summary (2023)

This article provides a summary of all new features and enhancements per Zscaler cloud for the Zscaler Client Connector Portal. Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com/).

**Select your Zscaler cloud:**

**Select a deployment date:**

* * *

The following service updates were deployed to mobile.zscaler.net on the following dates.

**December 01, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.0.1**

        *   Enables Path MTU Discovery by default for all new forwarding profiles.
        *   Adds an option to stop and resume a slow rollout of an app update. To learn more, see [Configuring an App Update in the Zscaler Client Connector App Store](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store).
        *   Fixes an issue where the auto-update didn’t update Zscaler Client Connector on all devices.
        *   Fixes an issue where Zscaler Client Connector would not use the packet filter driver if the client was installed with a policy token for an app profile that had been deleted.

**November 03, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.0**

        *   Displays a Latest Updates window after logging in to the Zscaler Client Connector Portal that lists the latest release information.
        *   Supports cloning Forwarding Profiles in the Zscaler Client Connector Portal. To learn more, see [Copying a Forwarding Profile](https://help.zscaler.com/zscaler-client-connector/copying-forwarding-profile).
        *   Supports a slow rollout of Zscaler Client Connector upgrades. To learn more, see [Configuring an App Update in the Zscaler Client Connector App Store](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store).
        *   Supports passwords for upgrade, uninstall, and revert in the unattended mode. To learn more, see [Configuring Passwords for Access in Unattended Mode](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode).
        *   Adds additional end-user notification controls such as custom timer, enable persistent notifications, and ZIA notification persistent in the Zscaler Client Connector Portal for macOS.
        *   Adds the ability to remove the default VPN names from VPN Trusted Network Adapter Criteria. To learn more, see [Adding a VPN Trusted Network Adapter Name](https://help.zscaler.com/zscaler-client-connector/configuring-vpn-trusted-network-adapter-criteria).
        *   Displays the Anti-Tampering status on the Device Details page in Enrolled Devices. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Adds an option to install the Zscaler App Inbound Rule to the Windows Firewall in App Profiles. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Adds a field on the Device Details page in Enrolled Devices to identify if an Imprivata user is logged in to Zscaler Client Connector.
        *   Adds the following trusted network conditions in Trusted Network and Forwarding Profile: Network Range, Default Gateway, and DHCP Server. To learn more, see [Configuring Trusted Networks for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector).
        *   Adds a configuration to manage the removal of existing exempted app containers from the firewall configuration. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Adds a configuration to block the domain profile detection for all network types. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Adds the option to use the SAML-based login_hint parameter to populate the username field automatically. To learn more, see [Configuring Automatic Username Population for IdP Authentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-username-population-idp-authentication).
        *   Replaces HTTP-based probing with ICMP-based probing for improved performance.
        *   Adds 64-bit support for Zscaler Digital Experience (ZDX).
        *   Adds the Azure AD Domain-Joined posture type for Zscaler Client Connector to identify the tenant ID. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Supports real-time posture checks for the following posture types: Process Check, Detect Carbon Black, Detect CrowdStrike, Detect SentinelOne, and Detect Microsoft Defender. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Provides a similar Zscaler Client Connector experience using the CLI to perform various operations. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Allows admins to see the active tunnel SDK version to track the devices switching between multiple tunnel SDK versions. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).

**September 08, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.24.1**

        *   Removes the certificate download function from a cloud-based content delivery network (CDN). To learn more, see [End-of-Support for Certificate Downloads](https://help.zscaler.com/eos-eol/end-support-certificate-downloads).
        *   Allows admins to configure the Threshold Rank for the Dynamic ZIA Service Edge Assignment and the Dynamic ZPA Service Edge Assignment in Forwarding Profile by selecting a value from 1 to 5. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Increases the entire character limit to 5,000 (comma-separated by VPN names) when adding VPN Trusted Network Adapter names.
        *   Supports bypassing applications using the [transparent proxy system extension](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) based on the defined process name for macOS.
        *   Fixes an issue where some users were able to uninstall Zscaler Client Connector without using the password configured in the Zscaler Client Connector Portal.

**August 04, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.24**

        *   Supports integrating multiple Zscaler Private Access (ZPA) clouds with the Zscaler Client Connector Portal.
        *   Moves Zscaler Client Connector app store configurations for app updates into one place on the Update Settings tab. To learn more, see [Configuring Update Settings for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-client-connector).
        *   Updates the API to fetch ZPA cloud information.
        *   Adds support for predefined application bypasses for Linux.
        *   Integrates ZIdentity with Zscaler Client Connector and the Zscaler Client Connector Portal to manage both user and device information in one place. To learn more, see [Accessing and Navigating the Zscaler Client Connector Portal](https://help.zscaler.com/zscaler-client-connector/accessing-and-navigating-zscaler-client-connector-portal).
        *   Gives admins the flexibility to configure how often Zscaler Client Connector reevaluates posture checks. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Supports configurable posture check intervals. To learn more, see [About Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/about-device-posture-profiles), and [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).

**July 07, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.23.1**

        *   Adds a warning message instructing users to set an uninstall password for Zscaler Client Connector when enabling anti-tampering in App Profiles.
        *   Renames Web Based to IP-Based in Application Bypass and enables admins to add IP-based applications and process-based applications to bypass traffic.
        *   Provides support for ZDX Autosense probes by dynamically detecting and probing the endpoints. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Fixes an issue where users weren't able to access the Zscaler Client Connector Portal after creating a custom admin role in Administration Management.
        *   Fixes an issue where users could not see one-time passwords (OTPs) for some devices on the Device Details page in Enrolled Devices.
        *   Fixes an issue where synching admins from ZIA and ZPA in Administration Management remained stuck in progress because the same database record was updated at the same time.

**June 02, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.23**

        *   Displays the Debian download link for Linux on the New Releases tab of the Client Connector App Store in the Zscaler Client Connector Portal.
        *   Adds the Forwarding Profile column to the App Profile page. To learn more, see [About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-profiles).
        *   Supports configuring the location and UTC time of Zscaler Client Connector auto-updates. To learn more, see [Configuring Update Settings for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-client-connector#group-based).
        *   Supports searching by the hardware fingerprint on the Machine Tunnel page. To learn more, see [About Machine Tunnels](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels).
        *   Adds configuring the Block Unreachable Domain Traffic for Z-Tunnel 2.0. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports Endpoint Data Loss Prevention integration with Zscaler Client Connector. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Adds configuration to allow non-administrator users access to Zscaler Client Connector log files. To learn more, see [Allowing Non-Administrator Users Access to Client Connector Log Files](https://help.zscaler.com/zscaler-client-connector/allowing-non-administrators-access-client-connector-log-files).
        *   Adds machine tunnel support for the following device postures: File Path, Registry Key, Firewall, Full Disk Encryption, Domain Joined, and OS Version. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Adds a configuration that restricts remote packet capture for administrators only. To learn more, see [Restricting Remote Packet Capture](https://help.zscaler.com/zscaler-client-connector/restricting-remote-packet-capture).
        *   Adds support for latency-based server configuration for ZPA. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Provides support for retrieving the updated policy based on Device Groups within the App Profiles section of the Zscaler Client Connector Portal. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Fixes an issue where the audit log shows incorrect information after a change was made in the forwarding profile.
        *   Fixes an issue where the forwarding profile in App Profiles was replaced with the default after the forwarding profile was deleted in Forwarding Profile without validation.
        *   Fixes an issue where some users weren’t able to delete the Trusted Network.
        *   Fixes an issue where the device posture ID displayed in the Audit Log instead of the device posture name after adding or removing a device posture.

**March 31, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.22.1**

        *   Provides the ability to use the Zscaler Client Connector synthetic IP range on the LAN address space, based on Zscaler Client Connector Portal configuration. To learn more, see [Configuring the Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range).
        *   Allows Z-Tunnel 1.0 to drop QUIC (UDP 443) packets for websites that use QUIC, so Zscaler Client Connector for Android and iOS can fall back to TCP 443. To learn more, [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).

**February 24, 2023**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.22**

        *   Syncs department information from Zscaler Internet Access (ZIA) and displays it on the Device Details page in Enrolled Devices.
        *   Supports IPv6 IPs for Zoom and Microsoft Teams in application bypass.
        *   Supports configuring a default global log mode for all new Zscaler Client Connector profiles that customers can override in App Profiles. To learn more, see [Configuring a Default Global Log Mode](https://help.zscaler.com/zscaler-client-connector/configuring-global-default-log-level).
        *   Enables customers to search within Forwarding Profile, App Profiles, Device Posture Profile, ZIA Device Posture Profile, Device Groups, and Trusted Networks in the Zscaler Client Connector Portal.
        *   Prevents the entire machine tunnel token from displaying on the Zscaler Client Connector Registered Machine Tunnel Details page for users that have read-only access.
        *   Supports cloning Zscaler Client Connector Profiles in the Zscaler Client Connector Portal. To learn more, see [Copying a Zscaler Client Connector Profile](https://help.zscaler.com/zscaler-client-connector/copying-app-profile).
        *   Adds the ability to export Partner Login Details in Enrolled Devices.
        *   Adds a public API to download the service status for devices as a CSV file.
        *   Adds a confirmation dialog box when deleting profiles.
        *   Changes the default Tunnel Driver Type for the Windows Driver Selection in Forwarding Profile from a Route-Based driver to a Lightweight Filter (LWF) driver for new customers.
        *   Provides the ability to use the Zscaler Client Connector synthetic IP range on the LAN address space, based on Zscaler Client Connector Portal configuration.
        *   Provides support for iOS to include IPv6 modes configuration. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Fixes an issue where service entitlements were disabled after enabling the Device Groups feature.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-client-certificate-posture-check-linux -->

Title: Configuring the Client Certificate Posture Check for Linux | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-client-certificate-posture-check-linux

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Configuring the Client Certificate Posture Check for Linux

This article describes how to configure the Client Certificate for Linux. You must perform these steps to successfully use the Client Certificate posture check for Linux in the Zscaler Admin Console.

The Client Certificate posture check verifies the following conditions:

*   The certificate authority (CA) certificate uploaded to the Zscaler Admin Console can be trusted by the Linux client system.
*   The Client Certificate on the Linux client system is either issued by the CA certificate or is on the certificate chain of trust, with the uploaded CA certificate being a root CA.
*   The private key associated with the Client Certificate is on the Linux client system.

Follow these steps to configure the Client Certificate for Linux:

1.   On the Linux client system, install Zscaler Client Connector.
2.   In the Zscaler Admin Console, upload a CA certificate issued by an internal root CA trusted by the organization’s users. You can upload one of the following certificates:

*   A root CA certificate (a self-signed certificate). This root CA certificate and all intermediate CAs must be installed in the trusted certificate store on the Linux client system [following these sample steps.](https://help.zscaler.com/zscaler-client-connector/configuring-client-certificate-posture-check-linux#self-signed-certificate)

    1.   Create the CA key: `openssl genrsa -des3 -out ca.key 4096`
    2.   Create the self-signed root certificate for signing client certificates: `openssl req -new -x509 -days 365 -key ca.key -out ca.crt`
    3.   Create the private key: `openssl genrsa -out client.key 1024`
    4.   Create the Certificate Signing Request (CSR) using the client key: `openssl req -new -key client.key -out client.csr`
    5.   Sign the client certificate with the root certificate: `openssl x509 -req -days 365 -in client.csr -CA ca.crt -CAkey ca.key -set_serial 01 -out client_cert.pem`
    6.   Upload the root certificate `ca.crt` to the Zscaler Admin Console.
    7.   Install the root certificate `ca.crt` to the client machine using the following commands:

    *   On Ubuntu systems:

sudo cp ca.crt /usr/local/share/ca-certificates/
sudo /usr/sbin/update-ca-certificates

    *   On CentOS/Fedora systems:

sudo update-ca-trust force-enable
sudo cp ca.crt /etc/pki/ca-trust/source/anchors/
sudo update-ca-trust extract
    1.   Copy the Client Certificate file `client_cert.pem` to the following locations on the Linux client:

The certificate file must be Base64-encoded and the file name must end with the extension .pem.

        *   If **Non-Exportable Private Key** is disabled, copy to `~/.Zscaler/certificates/`. The Client Certificate file has user access permission.
        *   If **Non-Exportable Private Key** is enabled, copy to `/opt/zscaler/client_cert/`. The Client Certificate file has root access only.

    2.   Confirm that certificates are properly installed by running the following commands:
        *   On Ubuntu systems: `openssl verify -show_chain -CApath /etc/ssl/certs/ <client_cert_file>`
        *   On CentOS/Fedora systems: `openssl verify -show_chain -CApath /etc/pki/ca-trust/extracted/pem/ <client_cert_file>`

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-client-certificate-posture-check-linux#self-signed-certificate)

*   An intermediate certificate. If there are intermediate CAs on the certificate chain of trust, they must be installed either in the system store or the directory `/opt/zscaler/intermediate_ca/`.

1.   Copy the Client Certificate file `client_cert.pem` to the following locations on the Linux client:

The certificate file must be Base64-encoded and the file name must end with the extension .pem.

*   If **Non-Exportable Private Key** is disabled, copy to `~/.Zscaler/certificates/`. The Client Certificate file has user access permission.
*   If **Non-Exportable Private Key** is enabled, copy to `/opt/zscaler/client_cert/`. The Client Certificate file has root access only.

Confirm that certificates are properly installed by running the following commands:

*   On Ubuntu systems: `openssl verify -show_chain -CApath/etc/ssl/certs/ <client_cert_file>`
*   On CentOS/Fedora systems: `openssl verify -show_chain -CApath /etc/pki/ca-trust/extracted/pem/ <client_cert_file>`

1.   Copy the private key that is associated with the Client Certificate to the Linux client system. The private key store location depends on whether or not **Non-Exportable Private Key** is enabled or disabled:

*   If **Non-Exportable Private Key** is disabled, copy the private key in the user's home directory: `~/.Zscaler/certificates/private/`
*   If **Non-Exportable Private Key** is enabled, copy the private key to the directory: `/opt/zscaler/private_key/`. This directory is created when you install Zscaler Client Connector. This directory is owned by the root and has a permission setting of 755. Zscaler Client Connector checks the private key file's permission. Posture validation fails if the file can be accessed by non-root users.

The private key file should be Base64-encoded and the file name must end with the extension `.`key.

1.   Log in to Zscaler Client Connector.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/searching-app-profile -->

Title: Searching for a Zscaler Client Connector App Profile | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/searching-app-profile

Markdown Content:
# Searching for a Zscaler Client Connector App Profile | Zscaler

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
*    Searching for a Zscaler Client Connector App Profile 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Searching for a Zscaler Client Connector App Profile

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

You can quickly find a specific [Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) using the Search field by selecting different criteria to narrow down your search results.

To find a Zscaler Client Connector Profile using the Search field:

1.   Go to **Infrastructure**>**Connectors**>**Client.**
2.   Under Platform Settings, select the OS.
3.   On the **App Profiles**tab, you can select a search option from the drop-down menu.
4.   Select the **Search** field. You can search using the following criteria:

*   Policy Name
*   Group Name
*   Policy Token

1.   Enter search words and press `Enter` to display your search results.

To clear the **Search**field, delete the search term you typed and press `Enter`.

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

<!-- source: https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi -->

Title: Step-by-Step Configuration Guide for Zscaler Client Connector for VDI | Zscaler

URL Source: https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
![Image 1: icon-cloud-connector.svg](blob:http://localhost/6e416d15bcb50c1001e80e392c70c45c)

## Cloud & Branch Connector

### Step-by-Step Configuration Guide for Zscaler Client Connector for VDI

This guide takes you step-by-step through the configuration tasks you must complete to begin using Zscaler Client Connector for Virtual Desktop Infrastructure (VDI) for your organization.

To enable this feature, contact Zscaler Support.

Before you begin configuring Zscaler Client Connector for VDI, Zscaler recommends reading the following articles:

*   [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-vdi-agent "What is Zscaler VDI Agent? ")
*   [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names)
*   [What Is Zscaler Cloud Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-cloud-connector)
*   [What Is Zscaler Branch Connector?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-branch-connector)
*   [Understanding the Zscaler Cloud Architecture for Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture)
*   [Understanding the Private Access Architecture](https://help.zscaler.com/zpa/understanding-private-access-architecture)

## Configuring Zscaler Client Connector for VDI

To configure Zscaler Client Connector for VDI, complete the following steps:

*   [Step 1: Review the System Requirements](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-1)[](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi)Zscaler Client Connector for VDI currently supports only modern Windows operating systems. You must be running Windows 10 or Windows Server 2016 or later. Additionally, only Azure Virtual Desktop and Citrix, on-premises and cloud-hosted, have been tested. Other vendors might work, but have not been tested.

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-1) 
*   [Step 2: Review the Zscaler Firewall Requirements](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-2)[](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi)The Cloud Connector and Branch Connector instances require only outbound connections to the Zscaler cloud. They do not require any inbound connections to your network from the Zscaler cloud. For user authentication, Zscaler Client Connector for VDI communicates with the Zscaler cloud using TCP/443. It also requires UDP/7443 to be open between the application software and the Cloud Connector or Branch Connector. This is how Zscaler Client Connector for VDI tunnels traffic to the Cloud Connector or Branch Connector. UDP/7443 to the Cloud Connector or Branch Connector and TCP/443 to the Zscaler cloud are required. Additionally, Zscaler Client Connector for VDI connects on TCP 9090 to the Cloud Connector service endpoint for policy updates. To view the outbound access requirements for your specific account, go to the following URL: https://config.zscaler.com/<Zscaler Cloud Name>/cloud-branch-connector.

You can find the <Zscaler Cloud Name> in the URL you use to log in to the Zscaler Admin Console. For example, if you log in to connector.zscaler.net, then go to https://config.zscaler.com/zscaler.net/cloud-branch-connector. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-2) 
*   [Step 3: Deploy Zscaler Cloud Connector or Zscaler Branch Connector](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-3)
*   [Step 4: Configure the VDI Templates](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-4)

[](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi)The VDI templates allow you to create a set of required configurations, including the provisioning URL, authentication token, and end user authentication method. These configurations are then applied to Zscaler Client Connector for VDI.

To configure VDI templates with Internet & SaaS, assign an identity provider (IdP) and System User to the template. Zscaler Client Connector for VDI uses the IdP configured in the Internet & SaaS tenant.

To configure VDI templates with Private Access, ensure that SCIM and [SCIM Attributes for Policy](https://help.zscaler.com/zpa/enabling-scim-identity-management) are enabled for Private Access user attribution to function. Zscaler Client Connector for VDI uses the IdP configured in the Internet & SaaS tenant for both Internet & SaaS and Private Access. For Private Access, the Internet & SaaS IdP provides the authentication and passes a token to Private Access, authenticating the user to the Private Access platform. For Private Access and wildcard Fully Qualified Domain Name (FQDN) forwarding to work, send all DNS requests through Cloud Connectors.

To learn more, see the following articles:

    *   [About VDI Templates](https://help.zscaler.com/cloud-branch-connector/about-vdi-agent-templates)
    *   [Configuring VDI Templates](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-templates)

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-4)

*   [Step 5: Configure the VDI Forwarding Profile](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-5)

[](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi)The VDI forwarding profiles allow you to create forwarding rules to include or exclude destinations from being encapsulated in the tunnels that Zscaler Client Connector for VDI creates.

To configure VDI forwarding profiles with Internet & SaaS, Zscaler recommends configuring a single 0.0.0.0/0 inclusion rule. The application automatically excludes RFC 1918 traffic (i.e., 10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/24, and 224.0.0.0/4). You can adjust the exclusion list to add additional private ranges. For example, Microsoft Azure users might choose to include the Automatic Private IP Addressing (APIPA) address range of 169.254.0.0/16 and Microsoft's dedicated 168.63.129.16/32 IP. If you are implementing Conditional Access policies in Azure, consider the URL-based bypasses that Microsoft recommends for Microsoft 365 and Azure Virtual Desktop to function correctly. Execute the bypasses in the Zscaler Admin Console. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/microsoft-365/enterprise/urls-and-ip-address-ranges?view=o365-worldwide) and [Azure documentation](https://learn.microsoft.com/en-us/azure/virtual-desktop/required-fqdn-endpoint?tabs=azure).

To configure VDI forwarding profiles with Private Access, you must make additional changes to the synthetic IP pool used for Private Access. This synthetic IP pool is configured in the Zscaler Admin Console. Zscaler recommends adding your synthetic IP ranges, set to 10.254.0.0/19 by default, to the inclusion list of your Zscaler VDI Agent forwarding profile to ensure that the traffic is properly tunneled to the Cloud Connector or Branch Connector. If your VDI forwarding profile excludes Private Access synthetic IP pools, then some Private Access functionalities might be unavailable.

To learn more, see the following articles:

    *   [About VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/about-vdi-agent-forwarding-profiles)
    *   [Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-agent-forwarding-profiles)

Zscaler Client Connector for VDI does not support Private Access features such as server-to-client connectivity for remote access, client forwarding policy for client types as Zscaler Client Connector for VDI, and double encryption. However, if the [client forwarding policy](https://help.zscaler.com/zpa/about-client-forwarding-policy) is required, the client type of the Cloud Connector or Branch Connector is supported, and you can use it in the same way.

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-5)

*   [Step 6: Configure Dynamic VDI Groups](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-6)
*   [Step 7: Customize Zscaler Client Connector for VDI with Installer Options](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-7)
*   [Step 8: Configure DNS for Private Access](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-8)

To enable Zscaler Client Connector for VDI to connect to Private Access access, route DNS queries through Cloud Connector. In Azure, Zscaler Client Connector for VDI uses the Azure DNS server with the IP address `168.63.129.16` by default. If this IP address is included in the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Include list, DNS queries are handled by Cloud Connector. If it is in the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Exclude list, ensure that the DNS request is sent toward Cloud Connector.

    *   If `168.63.129.16` is the primary DNS server for the Azure virtual network (VNet), you can use the private Azure outbound DNS resolver to route specific domains, such as Private Access applications, to the Cloud Connector for resolution.
    *   If the Azure VNet uses a custom DNS server, you can add the DNS server to the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Include list, and DNS requests are forwarded to Cloud Connector by default. If you add a custom DNS server to the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles) Exclude list, ensure that a route toward the Cloud Connector is configured on the Azure Virtual Desktop (AVD) subnet. If the AVD subnet is not configured to send the default route to the Cloud Connector, ensure that DNS traffic is directed toward the Cloud Connector.
    *   A default rule is configured to send all traffic to Internet & SaaS. If you do not want the DNS traffic to be sent to Internet & SaaS, add a direct traffic forwarding rule to the Cloud Connector cluster so that this traffic bypasses Internet & SaaS.

For Private Access use cases, include the Private Access synthetic IP pool in the [VDI forwarding profile](https://help.zscaler.com/cloud-branch-connector/about-vdi-forwarding-profiles).

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-8)

*   [Step 9: (Optional) Review Best Practices for FSLogix, Citrix, and AVD](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-9)

Zscaler recommends the following best practices when configuring FSLogix, Citrix, or AVD for Zscaler Client Connector for VDI:

    *   [FSLogix](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#FSLogix)

The following best practices apply to FSLogix:

        *   Storage Account Bypass: You can host the Azure storage account on a public IP address or on an Azure Private Link. Zscaler recommends using an Azure Private Link. Bypass the Azure storage account IP address in the VDI forwarding profile so the traffic can go directly from the Windows session host to the FSLogix storage account.
        *   Azure Storage Account Firewall Policies: Azure storage accounts are protected by firewall policies, and everything from outside the Azure VNet is dropped by default. Depending on the designated traffic flow, you can decide whether the firewall policy allows the Cloud Connector service interface IP address, the NAT gateway public IP address, or the Zscaler Public Service Edge IP address.
        *   Azure resources (Microsoft Learn): Azure has a unique, global IP address, `168.63.129.16`. It is the default DNS server for all Azure instances and the gateway to other Azure resources such as the Azure Windows VM Agent. Traffic to the IP address must be bypassed latest on the Cloud Connector traffic forwarding profile for the following ports:

            *   UDP/53
            *   TCP/53
            *   TCP/80
            *   TCP/443
            *   TCP/32526

Microsoft owns the IP address `168.63.129.16`. If Microsoft changes this IP address, update it to match.

        *   DNS Resolution: To prepare for enrollment, bypass the DNS server IP address, `168.63.129.16`, in Cloud Connector traffic forwarding rules, and not in Zscaler Client Connector for VDI itself.
            *   When the FSLogix storage account is a default account, the FQDN of the account is always resolved as a public IP address.
            *   When the FSLogix storage account is using a private link connection, DNS requests for the FQDN from Azure networks are resolved into the private IP address assigned to the private link. External requests, such as requests from the Zscaler Public Service Edge, are resolved with the storage accounts' public IP address.

        *   Azure Instance Metadata Service: Azure instances communicate with the hypervisor host through `169.254.0.0/19`, which should be bypassed in Zscaler Client Connector for VDI.
        *   Cloud Connector Network Security Group: The default network security group deployed within the Azure Marketplace only allows TCP/443, UDP/443, and TCP/80. For storage accounts, enable the account IP address with TCP/443, UDP/443, TCP/80, UDP/53, TCP/53, TCP/445, and UDP/445.

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#FSLogix)

    *   [Citrix](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#Citrix)

The following best practices apply to Citrix:

        *   To prevent bootup during MCS creation, ensure that the Zscaler service and ZCCVDIService are not running.
        *   If you are using XenApp, Zscaler recommends always using an embedded browser and SSO.
        *   Bypass Citrix Profile Management and Citrix Rendezvous protocols for them to function correctly. To learn more about bypasses, see [Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles#CitrixIPFQDN).
        *   When installing Zscaler on a Citrix image VM, ensure that you are using the onboarding parameter 0. To learn more about command line arguments when creating a MCS golden image, see [Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi).

To learn more, refer to the [Citrix documentation](https://docs.citrix.com/en-us/citrix-cloud/overview/requirements/internet-connectivity-requirements.html).

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#Citrix)

    *   [AVD](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#AVD)

[Close](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi#step-9)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2024 -->

Title: ZDX Module Release Summary (2024) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2024

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### ZDX Module Release Summary (2024)

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Digital Experience (ZDX) Module in the Zscaler Client Connector app.

**Select an OS:**

**Select a ZDX Module version:**

**Select a deployment date:**

* * *

The ZDX Module app versions for Windows listed below were deployed on the following dates.

**December 10, 2024**

*   Release Available: ZDX Module 4.4 for Windows

    *   ### **ZDX Module 4.4 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.6, 4.5, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Updates third-party libraries to the latest versions.
        *   Adds support for Health Metrics collection on devices to allow for diagnostics and to proactively identify issues.
        *   Adds handling of Personally Identifiable Information (PII) for new features.
        *   Fixes an issue regarding a Zscaler Client Connector call to the ZDX Cloud Path in Zscaler Tunnel (Z-Tunnel) 1.0.
        *   Fixes a display issue where the Cloud Path response for a bandwidth test showed a misleading hop IP address.
        *   Fixes an issue where a bandwidth test was unsuccessful when passing through a GRE tunnel.
        *   Addresses an issue where a traceroute is unsuccessful when the Zscaler Client Connector Portal is configured to drop IPv6 packets.
        *   Fixes an issue where the Cloud Path to the target on the private network did not pass through the third-party internal proxy.

**November 18, 2024**

*   Release Available: ZDX Module 4.3.0.18 for Windows

    *   ### **ZDX Module 4.3.0.18 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.5, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Fixes an issue where a Zscaler Client Connector call to ZDX Cloud Path service timed out.
        *   Fixes an issue where a bandwidth test showed a 0.0.0.0 IP address with a 100% packet loss.
        *   Fixes an issue where the Zscaler Digital Experience (ZDX) Self Service Dashboard sent notifications although the Enable Self Service and Send Push Notifications via Zscaler Client Connector settings in ZDX were disabled.

**October 01, 2024**

*   Release Available: ZDX Module 4.3.0.15 for Windows

    *   ### **ZDX Module 4.3.0.15 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.5, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Fixes an issue where the internal Cloud Path did not pass traffic to the internal proxy.

**September 13, 2024**

*   Release Available: ZDX Module 4.3 for Windows

    *   ### **ZDX Module 4.3 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.5, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Supports DNAT topology in Zscaler Internet Access (ZIA) Tunnel 1.0 mode.
        *   Adds checks to escape any non-UTF-8 characters from system-collected information.
        *   Fixes a database issue related to a Zscaler Client Connector version.
        *   Fixes an issue where Software Inventory data collection was affected by the addition of Software Patch Inventory.
        *   Fixes an issue where ZDX sometimes remained in a disconnected state.
        *   Fixes an issue where Device Inventory data was not always cleared after the threshold date.
        *   Fixes an issue where traceroute did not succeed in certain scenarios.
        *   Fixes an issue where ZDX encountered an error while running a bandwidth test in some scenarios.
        *   Fixes an issue to show the correct message when the App Connector is unavailable.
        *   Fixes an issue where the chosen port affected the traceroute in the Cloud Path.
        *   Fixes an issue in the Cloud Path where an incorrect name appeared for Zscaler Private Access (ZPA) Private Service Edge.
        *   Fixes an issue where ZDX could not stop the uploader.
        *   Fixes an issue when running remote packet capture in a Diagnostics session.
        *   Resolves an issue where ZDX experienced an error during shutdown in specific scenarios.
        *   Fixes an issue where a bandwidth test failed when started in conjunction with remote packet capture.

**July 15, 2024**

*   Release Available: ZDX Module 4.2.0.69 for Windows

    *   ### **ZDX Module 4.2.0.69 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.5, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Fixes an issue where the packet count displays incorrectly in the Cloud Path.
        *   Fixes a URL encoding issue where the Web probes failed and indicated no data was available on ZDX.

**June 03, 2024**

*   Release Available: ZDX Module 4.2 for Windows

    *   ### **ZDX Module 4.2 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.5, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Fixes an issue where the Cloud Path to an application with a Private IP address within a VPN environment does not go through the External Proxy.
        *   Improves CPU utilization of the ZDX service.
        *   Enhances ZDX database security with encryption to sensitive data.
        *   Updates to the latest Nmap Project's packet capture (NPCAP), which addresses issues in relation to the blue screen occurrences when utilizing Bluetooth tethering.
        *   Improves remote Packet Capture (PCAP) to incorporate LightWeight Filter (LWF) capture capability.

**April 05, 2024**

*   Release Available: ZDX Module 4.1 for Windows

    *   ### **ZDX Module 4.1 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.4.0.276, 4.4, 4.3.500, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Enhances Zscaler Private Access (ZPA) Cloud Path to support TCP and UDP protocol for probing from the client to the Zscaler Private Service Edge.
        *   Improves the code for better memory allocation in certain scenarios.
        *   Improves the logic to reduce the CPU consumption for Windows Management Instrumentation (WMI) queries used for network interface detection.
        *   Fixes an issue where an incorrect error was reported for DNS failure.
        *   Fixes an issue where the targeted hop appeared after the egress when there were unknown hops present.
        *   Fixes an issue where ZDX is not operational during modern standby in certain scenarios.
        *   Fixes an issue with Cloud Path in No Default Route (NDR) environment.
        *   Fixes an issue with the Web probe in an external proxy environment.

**February 01, 2024**

*   Release Available: ZDX Module 4.0.1.42 for Windows

    *   ### **ZDX Module 4.0.1.42 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.3.500, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2, 4.1.0.98, 4.1.0.89, 4.1.0.85, 4.1.0.82 , 4.1.0.104, 4.1.0.102, 4.1, 4.0.0.89, 4.0.0.80, 4.0, 3.9.0.191, 3.9.0.189, 3.9.0.183, 3.9.0.175, 3.9.0.160, 3.9.**

        *   Fixes an issue where ZDX fails to restart in some scenarios following a machine reboot.
        *   Fixes an issue where ZDX Location API fails with older versions of browsers.
        *   Enhances Patch Inventory feature performance by reducing the number of queries generated in the AD environment.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication -->

Title: Using WebView2 Authentication | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Using WebView2 Authentication

If your organization uses advanced multi-factor authentication (MFA) for SAML or FIDO2 (Fast Identity Online 2), your users can authenticate using WebView2 in their embedded browser. Zscaler Client Connector still manages traffic for Internet & SaaS and provides access to applications through Private Access.

This article describes how to configure and enable WebView2.

Users can also authenticate using their browser. To learn more, see [Enabling Browser-Based Authentication](https://help.zscaler.com/zscaler-client-connector/enabling-browser-based-authentication).

## Prerequisites

To use WebView2 authentication, your system must meet the following minimum versions:

*   Zscaler Client Connector version 4.2 or later for Windows
*   .Net Framework 4.5+
*   WebView2 103.0.1264.42+

If an existing version of WebView2 is earlier than the minimum version, Zscaler Client Connector pulls the latest version from Microsoft's content delivery network (CDN), using the bootstrap installer included in the Zscaler Client Connector install package.

## Configuring WebView2

To avoid encountering issues with the automated download and install of WebView2 by the Evergreen WebView2 bootstrap installer included in the Zscaler Client Connector package, do the following:

*   Bypass the following Microsoft CDN domains in strict enforcement mode or for any other security policies, so that the bootstrapper can download and install WebView2.
    *   .delivery.mp.microsoft.com
    *   .cdp.microsoft.com

The WebView2 installation is shared by other apps which use the WebView2 runtime.

*   Allowlist the file `MicrosoftEdgeWebview2Setup.exe` in antivirus and other security policies on client machines. To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).

WebView2 launches its own set of processes, so Zscaler Client Connector must bypass them in the strict enforcement mode. The full path of the exe is: `%ProgramFiles%\Zscaler\ThirdParty\WebView2\MicrosoftEdgeWebview2Setup.exe`.

## Enabling WebView2 Authentication

To enable WebView2 authentication in the Zscaler Admin Console:

1.   Go to **Infrastructure**>**Connectors**>**Client**.
2.   Under Platform Settings, select **Windows** and click the **Platform Settings** tab.
3.   Under **Authentication Settings**:

    1.   **WebView2**: Select this option to enable WebView2 authentication.
    2.   **SSO using Windows Primary Account**: (Optional) Select this option to automatically log in users in an Azure AD environment using Windows as an IdP. If this option is disabled, users must enter their credentials separately to log in to Zscaler Client Connector. This option is available only for Zscaler Client Connector version 4.4 and later for Windows.
    3.   **Ignore Client Cert errors for Webview2**: (Optional) Select this option to have Zscaler Client Connector continue authenticating if the IdP requires a client certificate for mutual authentication but the client device does not have a client certificate or if the client certificate is invalid. This option is available only for Zscaler Client Connector version 4.4 and later for Windows.
    4.   **Allow WebView2 to follow System Proxy**: (Optional) Deselect this option to connect directly to the internet. This option is available only for Zscaler Client Connector version 4.5 and later for Windows.
    5.   **Additional IdP Domains**: (Optional) Enter additional domains if the user login domain is different from the IdP domain. These domains are added to the Auth Server allowlist in the embedded WebView2 browser along with the user login domain added by default. This option is available only for Zscaler Client Connector version 4.6 and later for Windows and you must [integrate with Imprivata](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration) to use it with a version earlier than 4.8.
    6.   **Display certificate selection popup on desktop**: (Optional) Select this option to display a list of certificates for the user to select from in a pop-up window if multiple authentication certificates for the IdP are available. If disabled, the selection window displays only when users are viewing the app. Available only with Zscaler Client Connector version 4.7 and later for Windows.

If you select this option, you can enable [app profile options](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#auth) to control what displays when users reauthenticate to Private Access.

![Image 2: Enabling WebView2 authentication](https://help.zscaler.com/downloads/zscaler-client-connector/platform-and-authentication-management/using-webview2-authentication/zclient-connector-platform-settings-webview2_2.png)

4.   Click **Save**.

---

<!-- source: https://help.zscaler.com/product-usage-terms/zscaler-client-connector-long-term-support -->

Title: Zscaler Client Connector Long-Term Support | Zscaler

URL Source: https://help.zscaler.com/product-usage-terms/zscaler-client-connector-long-term-support

Markdown Content:
![Image 1: icon-cust.svg](blob:http://localhost/f013ee77204e039806a4c635526bd9c0)

## Product Usage Terms & Policies

### Zscaler Client Connector Long-Term Support

This policy is effective as of January 31, 2025.

## What is Long-Term Support?

In addition to supporting the current and previous two versions of Zscaler Client Connector for each of the desktop OS platforms, Zscaler identifies one of those [specific versions](https://help.zscaler.com/eos-eol/supported-versions) per desktop OS platform as the Long-Term Support (LTS) version. LTS means that the selected version of Zscaler Client Connector is supported for up to 12 months from the date of the LTS announcement, regardless of how the set of supported versions continues to change.

The LTS version is not updated with new features and functionality during those 12 months. However, there could be new releases based on that LTS version which are made available to customers to fix bugs and security issues.

Each LTS version is chosen based on widespread customer adoption.

## Which platforms does LTS apply to?

Currently, LTS only applies to Windows, macOS, and Linux platforms. As Zscaler Client Connector for iOS and Zscaler Client Connector for Android on ChromeOS are typically deployed from their respective app stores, it is not possible to maintain multiple versions.

## Why is the LTS version needed?

As Zscaler Client Connector is a widely adopted security solution, it is critical that newer versions are adopted. Zscaler recognizes that the frequency of updates might not apply to all deployments. Such environments face the challenge of complying with these change controls while running software versions that are supported by their vendors. Zscaler provides customers with the choice of deploying LTS versions that continue to be updated with security and bug fixes, but not with new functionality so that their environments can remain protected with minimal software churn.

## Is this the recommended version?

In general, no. LTS is intended to give Zscaler customers a stable version to run for an extended period of time. The recommended version is typically the latest version that has reached General Availability (GA). If your environment has a requirement for longer change cycles that prevent more frequent updates to GA versions, then Zscaler recommends the LTS version for you.

## How will changes to future LTS versions happen?

Two months prior to the next LTS version, the projected next version is announced, giving time for customers to migrate to the latest LTS version.

As LTS is chosen from existing versions, the next LTS is a version already available for tenants.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades -->

Title: Viewing and Configuring ZDX Module Upgrades | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Viewing and Configuring ZDX Module Upgrades

If you use Zscaler Digital Experience (ZDX), Zscaler regularly releases new versions of the base ZDX Module that are compatible with Zscaler Client Connector for Windows and Zscaler Client Connector for macOS. Zscaler Client Connector automatically rolls out the latest version. You can configure a delayed rollout to apply a version to selected user groups for testing purposes for up to 180 days.

Contact Zscaler Support to enable this feature.

You can download an upgrade package and manually deploy with the Mobile Device Management (MDM) used by your organization or with the CLI. This feature is available only with Zscaler Client Connector version 4.7 and later for Windows.

The following options are available:

*   [View Auto Rollout Information](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#view-auto-rollout)

    1.   [](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades)Go to **Infrastructure**>**Common Resources**>**Deployment**>**ZDX Releases**.
    2.   Select the platform (**Windows** or **macOS**) and view the fields:

        *   **ZDX Module Version**: The ZDX Module version that has been rolled out.
        *   **Zscaler Client Connector Min-Max Version**: The earliest and latest versions of Zscaler Client Connector that the ZDX Module version is compatible with.
        *   **Devices**: The number of devices using the ZDX Module version. Click the**Information** icon to display the number.

![Image 2: View the ZDX Module tab](https://help.zscaler.com/downloads/zscaler-client-connector/administration/zscaler-client-connector-store-settings/viewing-and-configuring-zdx-module-upgrades/zclient-connector-zdx-module-auto-rollout.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#view-auto-rollout)

*   [Configure a Delayed Rollout](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#configure-delayed-rollout)

    1.   [](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades)Go to **Infrastructure**>**Common Resources**>**Deployment**>**ZDX Releases**.
    2.   Select **Delay Rollout** and click **Save**.

[See image.](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#delay-rollout)

    3.   Select the platform (**Windows** or **macOS**) and disable the **Rollout** option for the ZDX Module version you want to delay the rollout for.
    4.   Click **Submit** to confirm you want to delay the rollout.

The **Edit** icon displays in the Action column.

    5.   Click **Edit** to display the **Rollout Version** window.

[See image.](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#rollout-version-window)

    6.   In the **Rollout Version** window, select the **User Groups** that will receive the rollout, and enter the date and time to apply the rollout, then click **Rollout**.

If you want to delay the rollout for all users, you can select all user groups and enter a date up to 180 days from the day the ZDX Module version was released. Zscaler Client Connector automatically rolls out the ZDX Module version to all users when the 180-day maximum is reached unless you freeze the rollout.

The ZDX Module version **Status** is changed to **Pending** and the **Auto Rollout In** value is changed to the number of days before Zscaler Client Connector automatically rolls out the version.

[See image.](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#pending-status)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#configure-delayed-rollout)

*   [Freeze a Delayed Rollout](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#freeze-delayed-rollout)

[](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades)If you want to delay a rollout beyond the 180-day maximum (e.g., you encountered issues in testing and need additional time to address them), you can freeze a delayed rollout:

    1.   Go to **Infrastructure**>**Common Resources**>**Deployment**>**ZDX Releases**.
    2.   Click the **Lock** icon beside the ZDX Module version.

The **Request to Freeze Rollout** window appears.

[See image.](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#freeze-rollout-window)

    3.   Enter the case number (e.g., the Salesforce case number) if you know it and the reason you are freezing the rollout.
    4.   Click **Submit**.

The **Status** changes to **Frozen** and the **Auto Rollout In** field is cleared.

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#freeze-delayed-rollout)

*   [Unfreeze a Frozen Rollout](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#unfreeze-frozen-rollout)

    1.   [](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades)Go to **Infrastructure**>**Common Resources**>**Deployment**>**ZDX Releases**.
    2.   Click the **Lock** icon beside the frozen ZDX Module version, and click **Unfreeze** in the confirmation window.

The rollout returns to a **Status** of **Pending** (if you unfreeze it fewer than 180 days after it was released) or is immediately rolled out (if you unfreeze it 180 or more days after it was released).

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#unfreeze-frozen-rollout)

*   [Download an Upgrade Package](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#download-upgrade-package)

    1.   [](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades)Go to **Infrastructure**>**Common Resources**>**Deployment**>**ZDX Releases**.
    2.   Select **Windows** and select the **ZIP URL (32 bit)** or **ZIP URL (64 bit)** download link for the ZDX Module Version package.

![Image 3: View the ZDX Module tab](https://help.zscaler.com/downloads/zscaler-client-connector/administration/zscaler-client-connector-store-settings/viewing-and-configuring-zdx-module-upgrades/zclient-connector-zdx-module-auto-rollout.png)

You can use the downloaded package with your MDM or Group Policy Object (GPO) to upgrade the ZDX Module based on your organization’s procedures. You can also [upgrade from the CLI](https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely).

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades#download-upgrade-package)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/parameters-guide-zscaler-client-connector-android-and-android-chromeos -->

Title: Supported Parameters for Zscaler Client Connector for Android and Android on ChromeOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/parameters-guide-zscaler-client-connector-android-and-android-chromeos

Markdown Content:
userDomain Your organization's domain name. If your instance has multiple domains associated with it, enter the primary domain for your instance.

The primary domain is only valid if you are using a single IdP with multiple domains. The primary domain won’t work if you have multiple domains across multiple IdPs.Example: `safemarch.com`

Specify the exact domain that is present in the SAML NameID field. For example, if your user’s NameID is john.doe@corp.company.com, then enter `corp.company.com`.This install option allows users to skip the app enrollment page. If single sign-on (SSO) is enabled for your organization, users are taken directly to your organization's SSO login page.
cloudName The name of the cloud on which your organization is provisioned. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).

If your organization is provisioned on more than one cloud, your users are asked to select the cloud where their traffic is sent during the enrollment process. With this install option, you can specify the cloud where the app sends user traffic, so your users don't have to make the selection during enrollment.If your cloud name is zscalertwo.net, enter `zscalertwo`.Do not use this option if your organization is provisioned on one cloud. The app automatically sends traffic to the proper cloud and your users don't need to make a selection during enrollment.
deviceToken The appropriate device token from the Zscaler Admin Console if you want to use [Zscaler Admin Console as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider). The Zscaler service silently provisions and authenticates users even if you don't have an authentication mechanism in place.Example: `123456677754`This option applies only to Internet & SaaS. It is not supported by Private Access.

Before adding this option, you must generate a device token in the Zscaler Admin Console and complete the full configuration in [Using Zscaler Admin Console as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider).
userName The username of the user.

You can also use an MDM macro to auto-populate this value. Refer to your MDM's documentation.A maximum of 255 alphanumeric and special characters.

For example, if the username is j.doe@zscaler.com, enter `j.doe`If you use this option, the userDomain option must not be empty.

For Microsoft Intune only: To use the same username used for enrolling in Intune, you can use the {{partialupn}} token. To view a complete list of available Intune tokens, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/intune/intune-service/apps/app-configuration-policies-use-android).
enableFips Indicates whether Zscaler Client Connector uses FIPS-compliant libraries for communication with Zscaler infrastructure.`1` = Enable

`0`= Disable (default)Enable this option only if you require FIPS-level security within your organization.
Ownership If you use the Ownership Variable device posture type, add the key Ownership. You can enter up to 32 alphanumeric characters in the **Configuration value**field. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).Example: `acmecorp`
autoEnrollWithMDM Use this parameter to configure auto-enrollment without user interaction when using [Client Connector IdP](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-idp).*   Enter `0` to disable auto-enrollment.
*   Enter `1` to always auto-enroll, even after log out.
*   Enter `2` for one-time auto-enrollment.When this parameter is set to either `1` or `2`, you must specify the parameters deviceToken, cloudName, and userDomain before enabling the autoEnrollWithMDM option.

If you don’t specify the userName parameter or you don’t explicitly allow the contacts permission from the MDM admin console, users are prompted to allow Zscaler to access their contacts when [enrolling in Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-service-zscaler-client-connector#android-enroll) so that the auto-enrollment process can retrieve the Google account username for enrollment.
customDNS By default, Zscaler Client Connector uses the device's DNS server. You can change the value to another DNS server using this setting. Enter the DNS IP address.Example: `4.2.2.2`
allowRunningOnRootedDevice Restricts users from running Zscaler Client Connector on a rooted device. Enter `1` to allow users to run Zscaler Client Connector on a rooted device.`1` = Enable

`0`= Disable (default)Applies to Zscaler Client Connector version 3.7 and later for Android, and Zscaler Client Connector version 3.7 and later for Android on ChromeOS.

This parameter was formerly allowZccOnRootedDevice.
allowRunningOnEmulator Restricts users from running Zscaler Client Connector on an emulator. Enter `1` to allow users to run Zscaler Client Connector on an emulator.`1` = Enable

`0`= Disable (default)This parameter was formerly allowZccOnEmulator.
externalDeviceId The identifier that associates devices in a mobile device management (MDM) solution with devices in the Zscaler Admin Console. You can use an MDM macro to auto-populate this value. Refer to your MDM's documentation.`0` = Disable (default)

Enter a custom value to identify the device (e.g., `123456677754`).Applies to Zscaler Client Connector version 3.7 and later for Android, and Zscaler Client Connector version 3.7 and later for Android on ChromeOS.
httpAuthAllowedHosts Use this parameter to specify a comma-separated list of hosts allowed for HTTP Basic authentication in the SAML WebView and for client certificate requests during SAML authentication. The system saves the specified hosts and allows these requests only from those hosts and cancels all others.Comma-separated list of hostnames. Examples: `proxy.corp.net, auth-gateway.acme.internal,idp.example.com`Mandatory parameter for HTTP Basic authentication and clientCert request. Not case-sensitive. FQDN without scheme/path must be provided (e.g., proxy.corpnet but not https://proxy.corpnet). Wildcard (*) character not accepted. Will match supplied top-layer domain against any related sub-domains (e.g., admin.zscaler.com will be a match for supplied parameter of zscaler.com). Requires Zscaler Client Connector version 4.2 or later for Android.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-macos -->

Title: Customizing Zscaler Client Connector with Install Options for macOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-macos

Markdown Content:
# Customizing Zscaler Client Connector with Install Options for macOS | Zscaler

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
*   [Downloading & Deployment](https://help.zscaler.com/zscaler-client-connector/downloading-deployment)
*   [Customizing Zscaler Client Connector with Install Options](https://help.zscaler.com/zscaler-client-connector/downloading-deployment/customizing-zscaler-client-connector-install-options)
*    Customizing Zscaler Client Connector with Install Options for macOS 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Customizing Zscaler Client Connector with Install Options for macOS

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

You can customize and deploy Zscaler Client Connector to your users' devices using a .pkg file installation process with property list (PLIST) configuration settings deployed from a mobile device management (MDM) solution. The steps for downloading and deploying the app vary by your MDM.

*   [Deploying Zscaler Client Connector with Microsoft Intune for macOS](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-macos)
*   [Deploying Zscaler Client Connector with Jamf Pro for macOS](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-jamf-pro-macos)

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Customizing Zscaler Client Connector with Install Options for MSI](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi)[Customizing Zscaler Client Connector with Install Options for EXE](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe)[Customizing Zscaler Client Connector with Install Options for macOS](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-macos)[Customizing Zscaler Client Connector with Install Options for Linux](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)[Customizing Zscaler Client Connector with Install Options for iOS](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-ios)[Customizing Zscaler Client Connector with Install Options for Android](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-android)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist -->

Title: Zscaler Client Connector Processes to Allowlist | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Zscaler Client Connector Processes to Allowlist

Zscaler recommends that your users' devices have inbound rules that allow the Zscaler Client Connector binaries and processes.

For some endpoint protection products like antivirus and personal firewalls, you might need to update additional allowlists for full Zscaler Client Connector functionality. This article covers processes and firewall rules that should be allowed.

Because Zscaler Client Connector modifies the networking component of the operating system, a Symantec Tamper Protection can trigger a false positive for the Zscaler service. While you can ignore this warning, you must update the allowlist for Symantec Tamper Protection.

## Allowlist Processes

This section lists the file paths to allowlist for Zscaler Client Connector.

*   [Windows](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows-64-32-bit)

[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)The location of files to allowlist depends on your version of Zscaler Client Connector.

If your organization uses [Group Policy Object (GPO)](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-active-directory-windows#Create-Group-Policy) to push policies on both Windows 32-bit and Windows 64-bit systems, you must add the file paths of both versions to your GPO policy.

    *   [Zscaler Client Connector for Windows: 32-bit version](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows32bit-allowlist-rules)

Allowlist the entire path of the following files:

These files reside in the `%ProgramFiles(x86)` folder even if you are running the 32-bit version of Zscaler Client Connector on a 64-bit system.

        *   [](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)`%ProgramFiles(x86)%\Zscaler\ZSAHelper\ZSAHelper.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ZSATray\ZSATray.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ZSATrayManager\ZSATrayManager.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ZSATunnel\ZSATunnel.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ZSAService\ZSAService.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ZSAZDP`
        *   `%ProgramFiles(x86)%\Zscaler\ZSAUpdater\ZSAUpdater.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ZSAUpm\ZSAUpm.exe`
        *   `%ProgramFiles(x86)%\Zscaler\Updater\zscalerappupdater.exe`
        *   `%ProgramFiles(x86)%\Zscaler\Updater\zscalerchecksumverifier.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\x86\Zscaler-Network-Adapter-1.0.1.0.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\x86\Zscaler-Network-Adapter-1.0.2.0.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\x86\Zscaler-Network-Adapter-Win10-1.0.2.0.exe`
        *   `%ProgramData%\Zscaler`
        *   `%WINDIR%\system32\`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPService.exe`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPClassifier.exe`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPApp.exe`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPResources.exe`

If you are on a 64-bit device and install a 32-bit Zscaler Client Connector, the ZDPInstaller is installed in the 64-bit folder. If you are on a 32-bit device and install a 32-bit Zscaler Client Connector, the ZDPInstaller is not downloaded and installed.

        *   `%ProgramFiles%\Zscaler\ZDP`
        *   `%ProgramFiles%\Zscaler\ZEPInstaller`
        *   `%ProgramFiles(x86)%\Microsoft\EdgeWebView\Application\<``version number``>\msedgewebview2.exe`

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows32bit-allowlist-rules)

    *   [Zscaler Client Connector for Windows: 64-bit version](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows64bit-allowlist-rules)

[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)Allowlist the entire path of the following files:

        *   `%ProgramFiles%\Zscaler\ZSAHelper\ZSAHelper.exe`
        *   `%ProgramFiles%\Zscaler\ZSATray\ZSATray.exe`
        *   `%ProgramFiles%\Zscaler\ZSATrayManager\ZSATrayManager.exe`
        *   `%ProgramFiles%\Zscaler\ZSATunnel\ZSATunnel.exe`
        *   `%ProgramFiles%\Zscaler\ZSAService\ZSAService.exe`
        *   `%ProgramFiles%\Zscaler\ZSAUpdater\ZSAUpdater.exe`
        *   `%ProgramFiles%\Zscaler\ZSAUpm\ZSAUpm.exe`
        *   `%ProgramFiles%\Zscaler\Updater\zscalerappupdater.exe`
        *   `%ProgramFiles%\Zscaler\Updater\zscalerchecksumverifier.exe`
        *   `%ProgramFiles%\Zscaler\ThirdParty\TAPDriver\x64\Zscaler-Network-Adapter-1.0.1.0.exe`
        *   `%ProgramFiles%\Zscaler\ThirdParty\TAPDriver\x64\Zscaler-Network-Adapter-1.0.2.0.exe`
        *   `%ProgramFiles%\Zscaler\ThirdParty\TAPDriver\x64\Zscaler-Network-Adapter-Win10-1.0.2.0.exe`
        *   `%ProgramFiles%\Zscaler\ThirdParty\ZSFFutil\x64\zsffutil.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\CertUtil\certutil.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\Filechecksum\fciv.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\Zscaler-Network-Adapter-1.0.1.0.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\Zscaler-Network-Adapter-1.0.2.0.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\TAPDriver\Zscaler-Network-Adapter-Win10-1.0.2.0.exe`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\ZSFFutil\zsffutil.exe`
        *   `%ProgramFiles%\Zscaler\ThirdParty\WebView2\MicrosoftEdgeWebview2Setup.exe`
        *   `%ProgramFiles%\Zscaler\ThirdParty\CertUtil\certutil.exe`
        *   `%ProgramFiles%\Zscaler\ThirdParty\Filechecksum\fciv.exe`
        *   `%ProgramData%\Zscaler`
        *   `%ProgramFiles%\Zscaler\ZDP`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPService.exe`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPClassifier.exe`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPApp.exe`
        *   `%ProgramFiles%\Zscaler\ZDP\ZDPResources.exe`
        *   `%ProgramFiles%\Zscaler\ZSAZDP`
        *   `%ProgramFiles%\Zscaler\ZEPInstaller`
        *   `%ProgramFiles(x86)%\Zscaler\ThirdParty\ZSFFutil\x86\zsffutil.exe`
        *   `%ProgramFiles(x86)%\Microsoft\EdgeWebView\Application\<``version number``>\msedgewebview2.exe`

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows64bit-allowlist-rules)

`%ProgramFiles(x86)%` and `%ProgramFiles%` are environmental variables that represent the drive where the Windows program files are located. Typically, program files are located on the C drive. However, there are exceptions. For example, on Amazon WorkSpaces, program files are on the D drive.

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows-64-32-bit)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#macos-allowlist-rules)

Allowlist the entire path of the following files:

    *   [](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)`/Applications/Zscaler/Zscaler.app/`
    *   `/Applications/Zscaler/.Updater/autoupdate-osx.app/Contents/MacOS/ZscalerUpdater`

This file is required only for Zscaler Client Connector version 3.7 and earlier for macOS.

    *   `/Library/Application Support/Zscaler/UPM/UPMServiceController`
    *   `com.zscaler.zscaler`

`com.zscaler.zscaler` is the Zscaler Client Connector identifier.

    *   `/Library/Application Support/Zscaler/ZDP`

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#macos-allowlist-rules)

*   [Linux](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#linux)

[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)Allowlist the entire path of the following files:

    *   `/opt/zscaler/bin/zsaservice`
    *   `/opt/zscaler/bin/zstunnel`
    *   `/opt/zscaler/bin/ZSTray`
    *   `/opt/zscaler/bin/zsupdater`
    *   `/opt/zscaler/ZSAUpm/bin/ZSAUpm`

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#linux)

## Bypasses for Firewall

If you have a GPO-managed or AV-managed host firewall, you can configure firewall rules on your endpoint protection product for `ZSATunnel.exe` processes for all ports, protocols, network interfaces, and network addresses (e.g., 0.0.0.0/0).

Zscaler Client Connector also uses carrier-grade NAT range 100.64.0.0/16 as part of internal health checking and for the Private Access service.

Zscaler can check IP addresses to avoid IP address conflict. For example, if you are using 100.64.0.0/16 and Zscaler sees a conflicting IP address, Zscaler changes it to 100.65.0.0/16. This change in the IP addresses can range from 100.64.0.0/16 to 100.83.0.0/16.

You can bypass the processes listed under the following platforms in your firewall rules:

*   [Windows](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows-bypasses-firewall)

    *   [](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)`ZSATunnel.exe: Inbound`
    *   `ZSATunnel.exe: Outbound`
    *   `ZSATray.exe: Outbound`
    *   `ZSATrayManager.exe: Outbound`
    *   `ZSAUpdater.exe: Outbound`
    *   `ZSAService.exe: Outbound`
    *   `Zscalerappupdater.exe: Outbound`
    *   `ZDPService.exe: Outbound`
    *   `ZEPInstaller.exe: Outbound`

Zscaler Client Connector automatically adds required firewall rules to the Windows Defender Firewall. However, if the Local Policy Merge GPO setting is enabled, the rules are ignored and you must set up the firewall rules via the GPO. If you use Microsoft Defender Antivirus, you must configure any excluded paths and excluded processes from the allowlist as custom exclusions. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/defender-endpoint/configure-exclusions-microsoft-defender-antivirus).

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#windows-bypasses-firewall)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#macos-bypasses-firewall)

    *   [](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)`Zscaler: Inbound`
    *   `Zscaler: Outbound`
    *   `ZscalerService: Inbound`
    *   `ZscalerService: Outbound`
    *   `ZscalerTunnel: Inbound`
    *   `ZscalerTunnel: Outbound`
    *   `ZscalerUpdater: Outbound`
    *   `UPMServiceController: Inbound`
    *   `UPMServiceController: Outbound`
    *   `/Applications/Zscaler/.Updater/autoupdate-osx.app/Contents/MacOS/ZscalerUpdater: Inbound`

`/Applications/Zscaler/.Updater/autoupdate-osx.app/Contents/MacOS/ZscalerUpdater: Outbound`
    *   `/Library/Application Support/Zscaler/ZDP/bin/zdpd.app/Contents/MacOS/zdpd: Outbound`

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#macos-bypasses-firewall)

*   [Linux](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#linux-bypasses-firewall)

[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)Linux provides several firewall frameworks that operate primarily at the network level (IP, port, interface), each offering varying levels of abstraction and functionality. All these frameworks leverage the underlying Netfilter kernel infrastructure.

Here are the common firewall tools used across Linux distributions:

    *   **iptables**: A widely used tool for packet filtering and firewall management.
    *   **nftables**: A modern replacement for iptables, offering improved performance and flexibility.
    *   **firewalld**: A daemon that uses nftables or iptables as its backend, commonly used in RPM-based distributions.
    *   **ufw**: The Uncomplicated Firewall, a user-friendly tool commonly used in Ubuntu.

The Zscaler Client Connector for Linux relies on the iptables command-line tool to configure the necessary rules within Netfilter.

## Firewall Hardening Guidelines for Linux Endpoints

The following example shows you how to harden your firewall for non-Zscaler traffic without affecting Zscaler Client Connector functionality. You must enter the commands in the terminal window in the specified order:

    1.   Delete all rules: `sudo iptables -F`

Although optional, performing step 1 ensures the following steps work properly.

    1.   Allow all outgoing connections: `sudo iptables -I OUTPUT -j ACCEPT`
    2.   Allow loopback connections: `sudo iptables -A INPUT -i lo -j ACCEPT`
    3.   Allow Zscaler Client Connector internal connections to the Zscaler Client Connector adapter: `sudo iptables -I INPUT -d 100.64.0.1 -j ACCEPT`
    4.   Allow DHCP traffic:
        *   `sudo iptables -I INPUT -p udp --dport 68`
        *   `sudo iptables -I OUTPUT -p udp --dport 67`

    5.   Allow SSH traffic (optional, comment out if not needed):
        *   `sudo iptables -A INPUT -p tcp --dport ssh -j ACCEPT`
        *   `sudo iptables -A OUTPUT -p tcp --sport ssh -j ACCEPT`

    6.   Allow established and related incoming connections: `sudo iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT`
    7.   Deny everything else incoming: `sudo iptables -A INPUT -j DROP`

[Close](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist#linux-bypasses-firewall)

If you are receiving a local FW/AV error after entering the rules, examine the `%windir%\system32\logfiles\firewall\pfirewall.log` to verify that there are no _drop_ entries for 100.64 addresses. If you see _drop_ entries, the Windows Firewall rules are not applied correctly to allow Zscaler to communicate. You must revise the rules based on the processes listed earlier.

## Processes Usage

The following list describes what each process is used for:

*   **ZSAHelper**: An internal process used by other Zscaler Client Connector processes.
*   **ZSATray**: The UI of the application.
*   **ZSATrayManager**: Manages the tray process and checks signatures for communication between tray and system services.
*   **ZSATunnel**: Handles traffic tunneling.
*   **ZSAService**: The main service and the watchdog that looks after all other services.
*   **ZSAUpdater**:The process that looks after automatic updates for the app.
*   **ZSAUpm**: The Zscaler Digital Experience (ZDX) service.
*   **zscalerappupdater**: The executable that initiates updates if found by the updater service.
*   **zscalerchecksumverifier**: Ensures the update being launched is legitimate.
*   **zsffutil**: Replaces certutil and file checksum used to validate crypto functions.
*   **NetworkAdapter**: Various versions of the network adapter, used by Zscaler Client Connector when in Tunnel (Route-Based).
*   **ProgramData\Zscaler**: Directory storing logs, PCAPs, and configuration for Zscaler Client Connector.
*   **LogonUI**: Used for pre-Windows login in Private Access.
*   **ZDPService.exe**: The data protection process that evaluates Data Loss Prevention (DLP) policy rules and determines the protection action to perform.
*   **ZDPClassifier.exe**: The text extraction and classification process that identifies the true file type of a file and extracts the text from supported file types and then performs content classification.
*   **ZDPApp.exe**: This process interacts with the end user to get user confirmation for specific activities.
*   **ZEPInstaller.exe**: Prevents end users from stopping, modifying, and deleting Zscaler products and services.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors -->

Title: Zscaler Client Connector: Private Access Authentication Errors | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors

Markdown Content:
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)2008 Authentication failed due to an invalid redirection URL. Please try again.This error occurs when a user delays Private Access authentication.Authenticate again. If the error persists, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42000 Inconsistency in user credentials is detected. Log out of the client and retry.When the user attempts to reauthenticate to Private Access, this error occurs if:

*   The user enters a different username instead of the one provided during initial enrollment.
*   The IdP SAML response has a different NameID instead of the one sent during initial enrollment.Verify that the user has entered the username provided during initial enrollment, and have the user retry authentication.

If the error persists, verify that the IdP SAML response has the NameID that Private Access received during initial enrollment.

You can also have the user log out from Zscaler Client Connector and attempt to re-enroll into Private Access.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42001 Internal Error: Contact Administrator This error occurs when a user attempts to log in to Zscaler Client Connector without a domain name. In this case, Private Access cannot identify the user's organization and as a result cannot proceed further. This is not applicable if you have specifically configured the App installer so that the domain name is not required in the username field.Verify that the user has entered a valid domain as part of the username (for example, joe@safemarch.com). This is not applicable if you have specifically configured the App installer so that the domain name is not required in the username field (in this case, the user would only enter joe).
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42002 Zscaler Private Access is not configured for your company.This error occurs when Private Access is not configured correctly and is unable to identify the IdP that must be used for enrolling the user.Verify that an IdP is configured for Private Access and that the IdP can communicate with Private Access. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign)
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42004 Internal Error: Contact Administrator This error occurs if Zscaler Client Connector is not sending the expected information to Private Access during the user's enrollment process.Verify that single sign-on (SSO) for Private Access has been configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign)
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42005 Internal Error: Contact Administrator This error occurs if Private Access cannot correctly interpret the information sent by Zscaler Client Connector during the user's enrollment process.Verify that SSO for Private Access has been configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign)
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42006 Internal Error: Contact Administrator This error occurs when the SAML response validation fails. The failure could be due to issues like the system clock being out of sync, an expired IdP certificate, a failure in the SAML response signature validation, or issues around IdP lookup by IdP entity ID.Verify that SSO for Private Access has been configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign)
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42007 Internal Error: Contact Administrator This error occurs when the certificate signing request in Private Access fails during the user enrollment process.Verify that the [signing certificate](https://help.zscaler.com/zpa/about-signingcerts) chosen for enrolling the user device to Private Access is valid. Private Access provides a default signing certificate that can be used when enrolling user devices to Private Access. Check the Zscaler Admin Console to ensure this certificate is still available, or if a new, valid certificate has been added in its place. If the error persists, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42010 Internal Error: Contact Administrator This error occurs if Private Access does not receive the expected information during the user enrollment process.This is an internal error. Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42013 The message is not of the SAML response object type.This error occurs if the IdP SAML response doesn’t match the expected SAML response object type.Update the IdP configuration to send the expected object type in the SAML response.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42014 The SAML response status is unsuccessful.This error occurs if the status in the SAML response is unsuccessful.Review the user’s information in the IdP and have the user retry logging in.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42015 Failed to validate the SAML response signature.This error occurs if:

*   This error occurs if the IdP certificates aren't configured correctly, and the Private Access authentication service needs to validate the SAML response signature.
*   The public certificate used by Private Access to validate the SAML response from the IdP has expired.Verify that the [IdP certificates are configured](https://help.zscaler.com/zpa/configuring-idp-single-sign) correctly in Private Access.

Check the expiration date of the IdP certificate, and upload a valid certificate if the current certificate has expired.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42016 The response issue time is either too old or with date in the future.

**IdP Issue Time**: [Timestamp]s

**Accepted Range**: [Timestamp]s to [Timestamp]s This error occurs if the IdP and the Private Access authentication service clocks have a large skew. The maximum accepted skew time is 120 seconds.Ensure the value for the response issue time is in the accepted range.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42017 The IdP originated SSO is not supported.This error occurs if the IdP sends Private Access a SAML response without the Private Access authentication service initiating it.Only the service provider (SP) initiated SSO is supported with Private Access.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42018 Failed to look up the SAML request corresponding to the SAML response received.This error occurs if the Private Access authentication service failed to look up the SAML request corresponding to the SAML response from its database.Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42019 The intended destination doesn't match any of the configured endpoints.This error occurs if the assertion consumer endpoint of the Private Access authentication service isn't properly configured in the IdP.Review the SP configuration in your IdP.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42020 Failed to validate the issuer in the SAML response.This error occurs if the IdP entity ID isn't properly configured in the Zscaler Admin Console. The entity ID is case sensitive.In the Zscaler Admin Console, review the entity ID of the IdP configuration.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42021 Possible error messages:

*   The assertion is too old to be used.
*   The assertion failed the validation because of the set notBefore condition.
*   The assertion failed the validation because of the set notOnOrAfter condition.This error occurs if the Private Access authentication service failed to validate the assertions in the SAML response. The assertion validation may fail due to timing issues, unsupported assertion conditions (e.g., OneTimeUse condition), etc.Ensure the value for the response issue time is in the valid range.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42022 Missing NameID in the SAML response.This error occurs if the SAML response doesn't have NameID in it.In the IdP configuration, ensure NameID is part of the subject in the SAML response message.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42023 The CA certificate (signing certificate) for Zscaler Client Connector has expired.This error occurs if the Central Authority (CA) certificate for Zscaler Client Connector has expired.Provision a valid CA certificate for Zscaler Client Connector. To learn more, see [Understanding Preloaded Enrollment (CA) Certificates](https://help.zscaler.com/zpa/understanding-preloaded-enrollment-ca-certificates).
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42024 The CA certificate (signing certificate) for Zscaler Client Connector is missing.This error occurs if the CA certificate for Zscaler Client Connector is missing.Provision a valid CA certificate for Zscaler Client Connector. To learn more, see [Understanding Preloaded Enrollment (CA) Certificates](https://help.zscaler.com/zpa/understanding-preloaded-enrollment-ca-certificates).
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42025 The private key for the Zscaler Client Connector CA certificate (signing certificate) is missing.This error occurs if the private key for the Zscaler Client Connector CA certificate is missing.Provision a valid CA certificate for Zscaler Client Connector. To learn more, see [Understanding Preloaded Enrollment (CA) Certificates](https://help.zscaler.com/zpa/understanding-preloaded-enrollment-ca-certificates).
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42026 Unable to secure a valid certificate for this user.This error occurs if Zscaler Client Connector fails to get a valid certificate.Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42027 Your organization has reached the limit for the maximum number of allowed users.This error occurs if your organization has provisioned more users than the number allowed by its subscription.Verify that the existing Private Access subscription meets the needs of your organization.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42028 Unexpected or missing information when enrolling or unenrolling Zscaler Client Connector.This error occurs if the Private Access authentication service receives a request from Zscaler Client Connector with missing or unexpected information.Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42029 Unable to identify the user by domain from the provided username.This error occurs if the user's username doesn't have a domain that is associated with the organization.Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42030 Unable to look up the user’s organization information.This error occurs due to missing information in the account associated with the Private Access service.Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42031 Unable to authorize Zscaler Client Connector enrollment request.This error occurs due to missing information in the account associated with the Private Access service.Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42032 The Private Access authentication service doesn’t support the OneTimeUse condition in the SAML assertion.This error occurs if the IdP issues a SAML assertion with the OneTimeUse condition.Update the IdP configuration to not issue OneTimeUse SAML assertion.
[42033](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)Private Access SP was not able to validate the SAML response. Please check the IdP configuration on both Private Access and your IdP.This error occurs when the Private Access service cannot validate the SAML response for the Private Access admin.Verify that an IdP is configured for Private Access administrator SSO and that the IdP can communicate with Private Access.

Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO](https://help.zscaler.com/zpa/configuring-idp-single-sign).
[42034](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)Private Access SP was not able to validate the SAML response. Please check the IdP configuration on both Private Access and your IDP.This error occurs when the Private Access service cannot validate the SAML response for the Private Access user.Verify that an IdP is configured for Private Access user SSO and that the IdP can communicate with Private Access.

Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign)
[42035](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)User not authorized because of domain mismatch. Please check configuration on both Private Access and your IdP.This error occurs if the user’s username domain doesn’t match any domains associated with the organization.Contact Zscaler Support.
[42036](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)Unable to verify the IdP configuration for the IdP entity ID.This error occurs when the Private Access service cannot verify the entity ID for the IdP configuration.In the Zscaler Admin Console, review the entity ID of the IdP configuration.
[42037](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)IdP is not enabled for admin SSO.Please check the IdP for user admin SSO in your IDP configuration.This error occurs when the IdP isn’t enabled for the admin SSO.Verify that SSO for Private Access is configured correctly.

Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for admin SSO](https://help.zscaler.com/zpa/configuring-idp-single-sign).
[42038](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)Failed to insert into Object Store.This is an internal error.Contact Zscaler Support.
[42039](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)Unable to verify the SP configuration for this domain.This error occurs when the Private Access service cannot verify the service provider (SP) configuration for the domain.Verify that the SP for IdP has been configured correctly.

Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign)
[42040](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)Failed to encrypt.This is an internal error.Contact Zscaler Support.
[42042](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)Configured IdP is disabled for SSO.This error occurs when IdP is disabled on Private Access.Enable the IdP on Private Access UI.
[42043](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)IdP configuration is incomplete.This error occurs when the IdP is misconfigured.Verify configuration.

To learn more, see [Using Zscaler Client Connector Portal as an Identity Provider](https://help.zscaler.com/zscaler-client-connector/using-zscaler-app-portal-identity-provider).
[42044](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)IdP configuration has mismatched SSO type/usage.This error occurs when the SSO type and usage for the IdP configuration do not match.Verify that SSO for Private Access is configured correctly. Prior to enrolling users with Zscaler Client Connector, verify that your [IdP Configuration is correctly set up for SSO.](https://help.zscaler.com/zpa/configuring-idp-single-sign)
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42045 Zscaler Private Access: SAML Assertion input too large.This error occurs if the IdP issues a SAML assertion that is larger than expected.Contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42046 All the signing certificates associated with the IdP are expired.This error occurs when a user tries to log in to Zscaler Client Connector and the IdP’s signing certificates in Private Access have expired.Update the [IdP configuration](https://help.zscaler.com/zpa/configuring-idp-single-sign) to upload a valid signing certificate from the IdP.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42047 The SAML request signing certificate has expired.This error occurs if the SAML request signing certificate configured in Zscaler Admin Console has expired.Edit the IdP configuration to change the certificate used by the IdP to another certificate, and then update the IdP. To learn more, see [Managing a Service Provider Certificate Rotation](https://help.zscaler.com/zpa/managing-service-provider-certificate-rotation).
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-zpa-authentication-errors)42048 The SAML request signing certificate is invalid.This error occurs if the SAML request signing certificate configured in Zscaler Admin Console is not valid.Edit the IdP configuration to change the certificate used by the IdP to another certificate, and then update the IdP. To learn more, see [Managing a Service Provider Certificate Rotation](https://help.zscaler.com/zpa/managing-service-provider-certificate-rotation).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device -->

Title: Viewing Device Fingerprint for an Enrolled Device | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Viewing Device Fingerprint for an Enrolled Device

From the Zscaler Admin Console, you can view device fingerprint information for [enrolled devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices).

To view the device fingerprint:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Device Overview**. The Device Management page appears.
2.   Click the **Device Details** icon to view the device fingerprint from the enrolled device.

![Image 2: Device Management page](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/viewing-device-fingerprint-enrolled-device/zclient-connector-device-mgmt-view.png)

The **Zscaler Client Connector Registered Device Details** window appears.

1.   In the **Zscaler Client Connector Registered Device Details** window, you can view:
    *   Under **Registration Details**:

        *   **User ID**: The username used for Zscaler Client Connector during enrollment.
        *   **Department**: Department information synced from Internet & SaaS.

Contact Zscaler Support to enable this feature.

        *   **Policy Name**: The Zscaler Client Connector profile assigned to the device. To learn more, see [About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles).
        *   **Device ID**: An internal Zscaler identifier for the device.
        *   **External Device ID**: The identifier that associates an external Mobile Device Management (MDM) device ID with devices in the Zscaler Admin Console. For Android only, the External Device ID displays in the Unique-ID field in the Device Details section of the Zscaler Client Connector Registered Device Details page.
        *   **Last Registration Time**: The last time the user logged in to Zscaler Client Connector on the device.
        *   **Last Deregistration Time**: The last time the user logged out of Zscaler Client Connector on the device.
        *   **Zscaler Client Connector Version**: The Zscaler Client Connector version on the device.
        *   **Tunnel Version**: The last Zscaler Tunnel (Z-Tunnel) version the device connected with.
        *   **Zscaler Digital Experience Version**: The Zscaler Digital Experience (ZDX) version, if enabled on the device.
        *   **Active Tunnel SDK Version**: The current tunnel SDK version to allow admins to track the devices switching between multiple tunnel SDK versions.
        *   **Installation Type**:
            *   **Strict Enforcement**: All internet traffic is blocked until the end user logs in to Zscaler Client Connector.
            *   **General Deployment**: The end user can access the internet before they log in to Zscaler Client Connector.

*   Under **Device Details**:
    *   **Owner**: If [Collect Device Owner Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-collect-device-owner-information) is enabled, this field displays the device owner information. For Windows and macOS, this is the locally logged in user. For Android, Android on ChromeOS, and iOS, this is the Zscaler Client Connector username. When disabled, this field does not display device owner information.
    *   **Machine Hostname**: If [Collect Machine Hostname Information](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-collect-hostnames) is enabled, this field displays the machine hostname. When disabled, this field does not display the machine hostname.
    *   **Unique-ID**: The device's unique identifier.
    *   **OS**: The device's operating system.
    *   **Model**: The device's model.
    *   **Manufacturer**: The device's manufacturer.
    *   **MAC Address**: The device's media access control address.
    *   **Device Locale**: The device's locale.
    *   **Hardware Fingerprint**: The device fingerprint.
    *   **Serial Number**: The device’s serial number. Applies only to Zscaler Client Connector version 4.7 and later for Windows, Zscaler Client Connector version 4.3 and later for macOS, Zscaler Client Connector version 4.4 and later for iOS, and Zscaler Client Connector version 4.2.1 and later for Linux.
    *   **Imprivata User**: **Yes**indicates an Imprivata user is logged in to Zscaler Client Connector.

*   Under **Service Status**:
    *   **ZIA Enabled**: Displays **True**if the user is entitled for the Internet & SaaS service in Zscaler Client Connector. Displays **False**if the user is not entitled for the Internet & SaaS service in Zscaler Client Connector.
    *   **ZIA Health**:Displays **Active**if Zscaler Client Connector is connected to Internet & SaaS. Displays **Inactive**if Zscaler Client Connector is not connected to Internet & SaaS.
    *   **Last Seen Connected to ZIA**:The last known date and time of connection to Internet & SaaS.
    *   **ZPA Enabled**: Displays **True**if the user is entitled for the Private Access service in Zscaler Client Connector. Displays **False**if the user is not entitled for the Private Access service in Zscaler Client Connector.
    *   **ZPA Health**: Displays **Active**if Zscaler Client Connector is connected to Private Access. Displays **Inactive**if Zscaler Client Connector is not connected to Private Access.
    *   **Last Seen Connected to ZPA**: The last known date and time of connection to Private Access.
    *   **ZDX Enabled**: Displays **True**if the user is entitled for the ZDX service in Zscaler Client Connector. Displays **False**if the user is not entitled for the ZDX service in Zscaler Client Connector.
    *   **ZDX Health**: Displays **Active**if Zscaler Client Connector is connected to ZDX. Displays **Inactive**if Zscaler Client Connector is not connected to ZDX.
    *   **Last Seen Connected to ZDX**: The last known date and time of connection to ZDX.
    *   **Zscaler Deception Enabled**: Displays **True**if the user is entitled for the Deception service in Zscaler Client Connector. Displays **False**if the user is not entitled for the Deception service in Zscaler Client Connector.
    *   **Zscaler Deception Health**: Displays **Active**if Zscaler Client Connector is connected to Deception. Displays **Inactive**if Zscaler Client Connector is not connected to Deception.
    *   **Last Seen Connected to Zscaler Deception**: The last known date and time of connection to Deception.
    *   **DC Location Method**: Displays **Source IP** if MaxMind’s GeoIP was used to find the nearest data center. Displays **Device Geolocation** if [Use Endpoint Location for Zscaler DC Selection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#advanced) is enabled and the user’s location services were used to find the nearest data center. Applies to Zscaler Client Connector version 4.8 and later for Windows only.

*   Under **Compliance Status**:
    *   **Refresh Status**: Click this option to refresh the passwords manually to ensure you have the latest password to provide to an end user.

Passwords refresh automatically every 60 minutes while the window is open.

    *   **Device State**: The device’s policy status. To learn more, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/policy-statuses-enrolled-devices).
    *   **Last Seen with Zscaler Client Connector Active**: The last time that Zscaler Client Connector was active on the device.
    *   **Last Configuration Download Time**: The last time the Zscaler Client Connector profile was updated. To learn more, see [Zscaler Client Connector Update Intervals](https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals).
    *   **Configuration Download Count**: The total number of times the app profile was updated since enrollment.
    *   **One-Time Password**: Displays a temporary password for login.
    *   **Logout, Disable, Uninstall Password**: The password associated with the device’s app profile. Applies to Zscaler Client Connector version 4.0 for Windows and Zscaler Client Connector version 4.1 for macOS. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
    *   For Zscaler Client Connector version 4.0 and later for Windows and Zscaler Client Connector version 4.1 and later for macOS, the following optional one-time passwords (OTPs) listed are associated with the device’s app profile and are configured in [app profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles):

Click the **Copy** icon to copy the password to your clipboard.

        *   **Logout OTP**: The password users must enter to log out of Zscaler Client Connector.
        *   **Revert OTP**: The password users must enter to revert to the previous Zscaler Client Connector version.
        *   **Uninstall OTP**: The password users must enter to uninstall Zscaler Client Connector.
        *   **Exit OTP**: The password that users can enter to exit the app from the system tray without disabling Internet & SaaS.
        *   **Disable ZIA OTP**: The password users must enter to disable the Internet & SaaS service.
        *   **Disable ZPA OTP**: The password users must enter to disable the Private Access service.
        *   **Disable ZDX OTP**: The password users must enter to disable the ZDX service.
        *   **Disable Endpoint DLP OTP**: The password users must enter to disable data protection in Zscaler Client Connector.

    *   **Device Trust Level**: Displays the device trust level based on the configured levels in [Internet & SaaS posture profiles](https://help.zscaler.com/zscaler-client-connector/adding-internet-saas-posture-profiles). Trust levels are **Low**, **Medium**, **High**, or **Unknown**.
    *   **Disable Anti-Tampering OTP**: The password used to disable anti-tampering protection. This option is available for Zscaler Client Connector version 4.1 and later for Windows.
    *   **Anti-Tampering Status**: Displays whether anti-tampering is enabled or disabled.

*   Under **Fetch Logs**and **Fetch Lightweight Logs**:

To enable **Fetch Logs** or **Fetch Lightweight Logs**, you must first enable the option Client Connector App Logs and then configure an email address on the [App Supportability tab in Client Connector Support](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-options-zscaler-client-connector).

When you click **Fetch Logs**or**Fetch Lightweight Logs**, Zscaler Client Connector uploads logs from the device to the web server during the next keepalive, which is usually once every hour while the device is active. Alternatively, you can click **Update Policy** in Zscaler Client Connector to immediately send a keepalive signal. To learn more, see [Viewing Information About Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector).

This table lists the differences between Fetch Logs and Fetch Lightweight Logs:

**Fetch Logs****Fetch Lightweight Logs**
Applies to all OSs for all supported versions.Applies to Windows 4.7 and later.
Captures logs from the previous 14 days.Captures logs from the previous 24 hours.
Includes PCAP files and crash files.Doesn't include PCAP files. Includes crash files.

These fields describe the process for remotely fetching logs from the device:

*   **Fetch Log Status**: The following messages display to keep you informed of the fetch log status:
    *   **Waiting for KeepAlive**: Displays after the fetch log request is initiated.
    *   **Extraction in progress**: Displays after the KeepAlive message is displayed.
    *   **Completion**: Displays if the fetch log request is successful.
    *   **Error while fetching logs**: Displays if the fetch log request fails.

*   **Log Timestamp**: Displays the date and time you clicked **Fetch Logs** and Zscaler Client Connector began uploading the logs to the web server.
*   **Log Acknowledge Timestamp**: Displays the date and time the log fetch successfully completed.
*   **Log URL**: Displays the URL provided after Zscaler Client Connector completes uploading logs. Click the **Copy** icon to copy the **Log URL**.

*   Under **Revert Client Connector**: Displays the status of reverting to a previous Zscaler Client Connector version. Possible values are **Unknown**, **InProgress**, **PreviousBuildNotAvailable**, **RevertFailed**, and **RevertSuccess**.

*   Under **Service Disable Reason**: Displays the service that was disabled, the time that the service was disabled, and the reason for disabling a service.

*   Under **ZPA Partner Logins**: Displays partner logins that logged in to a device’s tenant. To learn more, see [Enabling Private Access Partner Logins](https://help.zscaler.com/zscaler-client-connector/enabling-private-access-partner-logins).

*   Under **Device Upgrade Status**: Displays the status of an upgrade that is in progress.
    *   **Upgrade Status**: Possible values are:
        *   **In Progress**: Waiting for upgrade status from Zscaler Client Connector.
        *   **Complete**: Device upgraded successfully.
        *   **Failure**: Device failed to upgrade.

    *   **Client Connector Target Version**: The Zscaler Client Connector version provided by the Zscaler Admin Console in the last auto-update call for Zscaler Client Connector.
    *   **Client Connector Target Version Timestamp**: The date and time when the targeted Zscaler Client Connector version was sent to the client, regardless of whether the upgrade was successful or not.

*   Under **Force Remove**: When viewing device fingerprint information for an enrolled device, you can also force remove the device from the Zscaler Admin Console. To force remove a device, click **Force Remove**.

You can only force remove devices with the device state of **Removal Pending**. To learn more about device states, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).

*   Under **Quarantine**: You can quarantine a device by clicking **Quarantine**. This prevents the device from re-enrolling. If a device is in quarantine, click **Unquarantine**. To learn more, see [Quarantining a Device in the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/quarantining-device-zscaler-admin-console).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/uploading-custom-ssl-certificate-zscaler-client-connector -->

Title: Uploading a Custom SSL Certificate for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/uploading-custom-ssl-certificate-zscaler-client-connector

Markdown Content:
# Uploading a Custom SSL Certificate for Zscaler Client Connector | Zscaler

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
*   [Advanced Configuration](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings/advanced-configuration)
*    Uploading a Custom SSL Certificate for Zscaler Client Connector 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Uploading a Custom SSL Certificate for Zscaler Client Connector

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

Uploading your organization's custom SSL certificate is an optional step you can complete when [configuring SSL inspection for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-app).

When you upload your organization’s custom SSL certificate in the Zscaler Admin Console, and [turn on the install Zscaler SSL Certificate option](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-app), the custom certificate is automatically installed on users’ devices.

The **Install Zscaler SSL Certificate** feature is not supported on devices running macOS Big Sur (11) and later.

To upload the custom certificate:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Directory Sync and Custom Root Cert**.
2.   On the **Advanced Configuration** tab, under **Custom Certificate**, click **Upload**.

![Image 6: Upload the custom certificate](https://help.zscaler.com/downloads/zclient-connector-advanced-configuration-custom-cert.png)

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Advanced Configuration for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/advanced-configuration-zscaler-client-connector)[Configuring SSL Inspection for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector)[Uploading a Custom SSL Certificate for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/uploading-custom-ssl-certificate-zscaler-client-connector)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals -->

Title: Zscaler Client Connector Update Intervals | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals

Markdown Content:
# Zscaler Client Connector Update Intervals | Zscaler

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
*    Zscaler Client Connector Update Intervals 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Zscaler Client Connector Update Intervals

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

Zscaler Client Connector automatically updates at the set intervals:

*   Every 15 minutes, Zscaler Client Connector downloads the PAC files of the app profiles and forwarding profiles.
*   Every 80 minutes, Zscaler Client Connector connects for policy updates from the app profiles, forwarding profiles, and administration settings.

If the PAC file URLs are changed, it automatically updates every 80 minutes because this counts as a profile change.

*   Every 2 hours, Zscaler Client Connector checks for software updates.

You cannot configure these intervals. If the user manually updates the policy from their device, Zscaler Client Connector updates immediately.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[What Is Zscaler Client Connector?](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)[Why Is Zscaler Client Connector on My Device?](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device)[Step-by-Step Configuration Guide for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)[Zscaler Client Connector Update Intervals](https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals)[Zscaler Client Connector Resource Usage](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-resource-usage)[Domains to Add to SSL Bypass List](https://help.zscaler.com/zscaler-client-connector/domains-add-ssl-bypass-list)[Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector -->

Title: Enabling Packet Capture for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Enabling Packet Capture for Zscaler Client Connector

The packet capture feature allows users to capture traffic specific to the Zscaler Client Connector app. When enabled, users can run a packet capture to generate packet capture files that are stored with Zscaler Client Connector logs.

The packet capture feature captures packets at the adapter level and captures all packets on all network adapters.

If you use Zscaler Client Connector for Windows with the [Packet Filter-Based driver](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector#windows-driver-selection), the feature also captures packets that pass through the filter driver. With the Packet Filter-Based driver, the Zscaler service handles traffic on the system at a low level while traditional packet capture tools (e.g., Wireshark) might not see all traffic for troubleshooting purposes.

Enabling packet capture automatically installs Npcap if it is not already installed.

## Enabling the Start Packet Capture Option

To enable packet capture for Zscaler Client Connector:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**User Privacy**.
2.   Select **Enable Local Packet Capture in Zscaler Client Connector**if it's not already enabled.

[See image.](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector#enable-local-packet-capture)

After enabling access to local packet capture, it can take up to two hours for this feature to appear in Zscaler Client Connector. Click **Update Policy**from the **More** window to manually refresh your policies.

3.   To confirm that the packet capture feature is enabled, click **More** in the app and verify that the **Start Packet Capture**option appears in the Troubleshoot section.

[See image.](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector#start-packet-capture)

If you use Zscaler Client Connector version 4.8 and later for Windows, you can configure access to local packet capture on the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#notification). Packet capture access that is enabled on the app profile overrides this setting.

## Using the Start Packet Capture Option

When reproducing an issue that requires packet capture:

1.   In Zscaler Client Connector, click **More**.
2.   In the **Troubleshoot** section, click **Start Packet Capture**and modify the following settings:
    *   **Run Session For**: Select the time limit you want to run the packet captures for. The default setting is 5 mins.
    *   **Disk Space Limit**: Select the storage space for the PCAP file. Each PCAP file is stored at half the size of the selected disk space limit and the oldest PCAP file is deleted. For example, if a limit of 500 MB is chosen, two PCAP files are created in sequence, each with a size of 250 MB. If the packet capture space limit exceeds 500 MB, the first (oldest) file is deleted. The default setting is 200MB.
    *   **Frame Size Limit**: Select the packet payload length. The default setting is 1514.
    *   **Filters**: Enter [filter text](https://www.tcpdump.org/manpages/pcap-filter.7.html) to filter the packets. If left empty, all packets are captured. If invalid text is entered, an error message is displayed. The filter options are based on the OS:
        *   **Packet Capture Filter**: Filter all packets. Applies to Zscaler Client Connector for macOS, Linux, and version 4.7 and earlier for Windows.
        *   **Adapter Packet Capture Filter**: Filter the packets that go through the device adapter. Applies to Zscaler Client Connector version 4.8 and later for Windows.
        *   **LWF Packet Capture Filter**: Filter the packets that go through the Lightweight Filter (LWF) driver. Applies to Zscaler Client Connector version 4.8 and later for Windows.

3.   Click **Start** to run the packet capture.

1.   Reproduce the issue.
2.   Click **Stop Packet Capture** after you resolve the issue.

If the user forgets to stop the packet capture, it automatically stops after the time limit set in **Run Session For** has expired.

Duplicate packets are created in a packet capture to use for troubleshooting. The duplicate packets are not sent to the destination.

## Packet Capture Files

Packet capture files are stored with Zscaler Client Connector logs in the following locations:

*   **Windows**: `%ProgramData%\Zscaler\log-``<random numbers>`

`%ProgramFiles(x86)%` and `%ProgramFiles%` are macros that represent the drive where the Windows program files are located. Typically, program files are located on the C drive. However, there are exceptions (e.g., on Amazon WorkSpaces, program files are on the D drive).

*   **macOS**: `/Library/Application Support/com.zscaler.Zscaler`

For ARM processor-based macOS devices, the path is `/Library/Application Support/Zscaler`.

*   **Linux**: /var/log/zscaler/.Zscaler/Logs

Packet capture files have the prefix `CaptureAdapters` (Windows can also have the prefix `CaptureLWF`), followed by a timestamp and the file extension `.pcap`.

You can export packet capture files to a secondary location by browsing to the desired location when you export logs.

Examples of packet capture files:

CaptureAdapters_2020-07-28-17-13-49.822349.pcap
CaptureLWF_2020-07-28-17-13-49.822349.pcap
You might need to select the option to display hidden files to view the packet capture file.

Packet capture files are exported to the archive that is generated when using the **Export Logs** option from Zscaler Client Connector. To learn more, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

However, these files are not exported to the archive that is generated when using the **Report an Issue** option from Zscaler Client Connector. You must send the files to Zscaler Support for assessment.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-and-repair-options-zscaler-client-connector -->

Title: Configuring User Access to the Restart and Repair Options for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-and-repair-options-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring User Access to the Restart and Repair Options for Zscaler Client Connector

Users can troubleshoot the app by clicking **Restart Service** or **Repair App f**rom the **More** window of Zscaler Client Connector.

The **Repair App** feature is only available for Windows.

![Image 2: The Restart Service and Repair App options on the Zscaler Client Connector](https://help.zscaler.com/downloads/tech-pubs-drafts/configuring-user-access-restart-repair-options-zscaler-client-connector/Repair_Restart-750.png)

Restarting Zscaler Client Connector doesn't impact security enforcement. However, you can click **Restart Service**only once within a 30-second period for Zscaler Client Connector version 3.8 and later for Android and Zscaler Client Connector version 3.8 and later for Android on ChromeOS. When users select **Repair App**, the app attempts to repair itself by reinstalling app drives and services.

Using **Restart Service** or **Repair App** causes Zscaler Client Connector to fail-open briefly. Disable access if this is not ideal.

To configure user access to these options:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**App Supportability**.
2.   On the **App Supportability** tab, click **Enable End User to Restart Services and Repair App**.
3.   Click **Save**.

![Image 3: The Enable End User to Restart Services and Repair App switch](https://help.zscaler.com/downloads/tech-pubs-drafts/configuring-user-access-restart-repair-options-zscaler-client-connector/Portal-Enable_Repair-Restart.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector -->

Title: Viewing Information About Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Viewing Information About Zscaler Client Connector

In the **About**section of the **More** window in Zscaler Client Connector, you can view information about the app and also update the app or internet security policies on your device using the following fields:

*   [](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector)**App Version**: Displays the app version number.
*   **Update App**: You can click or tap to update the app to the latest version approved by your organization if an update has not already occurred. For Android devices, this option is not available. If you think your app needs to be updated, contact your IT administrator.
*   **App Policy**: Displays the name of the app profile assigned to the device.
*   **Update Policy**: You can click or tap to manually refresh your internet security policies and PAC file.
*   **License Agreement**: Applies to macOS. You can click to view the **License Agreement for Zscaler Software**.
*   **License and Credits**: Applies to Android and Linux. You can click to view the **License Agreement for Zscaler Software**and **Third-Party Software Notice**.
*   **License Agreement and Third Party Software Notice**: Applies to Windows, macOS and iOS. You can click to view the **License Agreement and Third-Party Software Notice**for Zscaler.

The following is a sample screenshot from the Windows platform. The screen might slightly differ based on the OS of your device:

![Image 2: About Menu for Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/end-user-guides/viewing-information-about-zscaler-client-connector/zscaler-client-connector-about-windows.png)

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2022 -->

Title: Release Upgrade Summary (2022) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2022

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Release Upgrade Summary (2022)

This article provides a summary of all new features and enhancements per Zscaler cloud for the Zscaler Client Connector Portal. Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com/).

**Select your Zscaler cloud:**

**Select a deployment date:**

* * *

The following service updates were deployed to mobile.zscaler.net on the following dates.

**November 11, 2022**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.21.2**

        *   Improves how long the DNS cache manages time-to-live (TTL) for all OS types.
        *   Provides the option to have Zscaler Client Connector collect logs per device.
        *   Provides the ability to use the Web 2.0 web browser control in Zscaler Client Connector.
        *   Includes the option to intercept Zscaler Internet Access (ZIA) traffic from any network adapter in the Zscaler Client Connector profile.
        *   Updates Swagger to version 3.0.
        *   Includes public API information in the Zscaler Client Connector Portal audit logs.
        *   Provides the option to disable the Zscaler firewall for macOS if ZIA is active. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Includes the ability to enable or disable the exclusion DNS servers in the routing table for macOS. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Adds a link to the Zscaler Client Connector APIs from the Public API page in the Zscaler Client Connector Portal.
        *   Renames the Personal Computers tab in the Zscaler Client Connector Store page to Registered Devices, and moves the Revert Zscaler Client Connector option from the Zscaler Client Connector Registered Device Details window to the Registered Devices tab.
        *   Improves the table pagination on the Enrolled Devices page in the Zscaler Client Connector Portal.
        *   Provides an anti-tampering solution for processes, services, files, and registry keys. To learn more, see [Anti-Tampering for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector).
        *   Supports configuring a preferred port to enable Zscaler Client Connector to SME server communication. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Fixes an issue where an invalid request for a device list using the Public APIs did not return a bad request response.

**September 23, 2022**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.21.1**

        *   Supports using the keyboard to select the Launch button on the landing page when using browser-based authentication.
        *   Supports downloading the browser-based configuration in the keepalive policy download.
        *   Adds a public API to download enrolled devices information as a CSV file.
        *   Allows administrators to configure an option to notify users that per-app VPN tunnel establishment is in progress for iOS devices. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles#iOS).
        *   Supports a test mode for disaster recovery for selected app profiles. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Supports authentication using the default browser for iOS. To learn more, see [About Authentication Settings](https://help.zscaler.com/zscaler-client-connector/about-authentication-settings).
        *   Supports disabling and enabling Zscaler Client Connector proxies on VPN adapters. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles#macOS).
        *   Updates the range of the value you can enter for MTU for Zscaler Adapter to between 800 and 1400. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports adding Zoom and Microsoft Teams bypass IPs to VPN Gateways.
        *   Supports prohibiting the installation or upgrade of Zscaler Client Connector for macOS versions 3.7 or later on macOS Mojave 10.14.
        *   Updates the minimum value for the CrowdStrike ZTA posture type Minimum Score from zero to one. To learn more, see [Configuring Device Posture Profiles for ZPA](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles-zpa).
        *   Supports multiple one-time passwords (OTP) for different functions.
        *   Supports the Detect Microsoft Defender posture type for Linux.
        *   Supports the OS Version posture type for Linux.
        *   Supports the Full Disk Encryption posture type for Linux.
        *   Fixes an issue where users lost access to Zscaler Private Access (ZPA) and were forced to log out after some devices were removed through an API request.

**July 15, 2022**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.21**

        *   Adds ID fields for Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) to audit logs streaming to SIEM. This only applies to customers with either ZIA or ZIA and ZPA.
        *   Provides visibility into traffic bypassed from ZIA.
        *   Adds the Fallback to Gateway Domain option to use when PAC proxies cannot be reached for Z-Tunnel 1.0. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles#macOS).
        *   Supports using the read and read/write roles in the token for API CRUD (create, read, update, and delete) operations.
        *   Supports using role-based access (RBA) to provide Zscaler Support access to the Zscaler Client Connector Portal when using Remote Assistance.
        *   Supports assigning specific entitlements to devices based on posture. To learn more, see [About Device Groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups-0) and [About Zscaler Entitlement.](https://help.zscaler.com/zscaler-client-connector/about-zscaler-service-entitlement)
        *   Supports the ability to revert Zscaler Client Connector to the previously installed version. To learn more, see [Reverting Zscaler Client Connector to the Previous Version](https://help.zscaler.com/zscaler-client-connector/reverting-zscaler-client-connector).
        *   Supports Zscaler Deception integration with Zscaler Client Connector. To learn more, see [Enabling Deception for a Group of Users](https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-deception-group-users).

**April 22, 2022**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.20.0.231**

        *   Supports the posture type Process Check for Linux. For more information, see [Configuring Device Posture Profiles for ZPA](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles-zpa). 

**March 04, 2022**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.20.1**

The following updates are for release 3.20 refresh 1 (a.k.a., 3.20.1):

        *   Supports giving administrators the ability to delete devices using the public API for the Zscaler Client Connector Portal (formerly Zscaler App Portal or Z App Portal).
        *   Supports increasing the device token limit from 8 to 16. For more information, see [Creating a Device Token](https://help.zscaler.com/zscaler-client-connector/creating-device-token).
        *   Supports enabling the Zscaler Notification Framework as an alternative to the default Windows notification system. To learn more, see [About the Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework).
        *   Supports authentication using the default browser for Linux. To learn more, see [About Authentication Settings](https://help.zscaler.com/zscaler-client-connector/about-authentication-settings).
        *   Supports skipping trusted network criteria evaluation for Split VPN Trusted Network detection. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports a customizable list of VPN Trusted Network names to accurately detect VPN Trusted Networks. To learn more, see [About the Endpoint Integration Page](https://help.zscaler.com/zscaler-client-connector/about-endpoint-integration-page).
        *   Supports authentication using the default browser for Android. To learn more, see [About Authentication Settings](https://help.zscaler.com/zscaler-client-connector/about-authentication-settings).

**February 04, 2022**

*   Release Available

    *   ### **Zscaler Client Connector Portal 3.20**

        *   Supports selecting specific devices and registration details to create a filtered list for enrolled devices. To learn more, see [About Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices).
        *   Supports new status details when remotely fetching logs from the device: Waiting for KeepAlive, Extraction in progress, Completed, Error while fetching logs. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Supports authentication using the default browser for macOS. To learn more, see [About Authentication Settings](https://help.zscaler.com/zscaler-client-connector/about-authentication-settings).
        *   Supports read-only access to the Zscaler Client Connector Portal during an upgrade.
        *   Supports the ability to filter data on the Enrolled Devices page without first exporting it. To learn more, see [About Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices).
        *   Improves Z-Tunnel 2.0 protocol bypass support by enabling Zscaler Client Connector to forward and tunnel traffic using two new options in the Forwarding Profile configuration setting: Redirect Web Traffic to Zscaler Client Connector Listening Proxy and Use Z-Tunnel 2.0 for proxied Web Traffic. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#trusted-network-criteria).
        *   Supports using terms allowlist and denylist to describe managing access.
        *   Provides administrators the control to configure an automatic update of 64-bit images of Zscaler Client Connector for specific groups. To learn more, see [Adding a Zscaler Client Connector Store Group Policy](https://help.zscaler.com/zscaler-client-connector/adding-zscaler-client-connector-store-group-policy) and [Configuring Update Settings for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-update-settings-zscaler-client-connector#group-based).
        *   Improves error messages when configuring trusted networks or device postures.
        *   Supports overriding the Z-Tunnel 2.0 protocol settings by adding a new option on the User Privacy page. To learn more, see [About User Privacy](https://help.zscaler.com/zscaler-client-connector/about-user-privacy).
        *   Supports the ability for Zscaler Client Connector to detect if a device is running a split-tunnel VPN in addition to the currently supported full tunnel VPN Trusted Network detection. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Allows Zscaler Client Connector to evaluate trusted network criteria against all default route adapters.
        *   Enables customers to configure fallback options on Z-Tunnel 2.0 establishment failure. To learn more, [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports the Certificate Trust and Client Certificate posture types for the pre-Windows login machine tunnel. To learn more, see [Configuring Device Posture Profiles for ZPA](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles-zpa).
        *   Adds posture-based risk profiles for Zscaler Internet Access (ZIA). To learn more, see [About ZIA Posture Profiles](https://help.zscaler.com/zscaler-client-connector/about-zia-posture-profiles).
        *   Supports configuring role-based administration within the Zscaler Client Connector Portal. To learn more, see [Administration Management](https://help.zscaler.com/zscaler-client-connector/about-administration-management).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2022 -->

Title: Client Connector App Release Summary (2022) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2022

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Client Connector App Release Summary (2022)

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector app.

**Select an OS:**

**Select an app version:**

**Select a deployment date:**

* * *

The Client Connector app versions for Windows listed below were deployed on the following dates.

**December 16, 2022**

*   Release in Limited Availability: Client Connector 4.1 for Windows

    *   ### **Zscaler Client Connector 4.1 Enhancements and Fixes**

        *   Allows admins to update the system proxy settings on any network change detection.
        *   Adds enhancement to resume Packet Capture after a tunnel restart if the packet capture was not completed before the tunnel restart.
        *   Adds IPv6 inclusions and exclusions for Zscaler Internet Access (ZIA). To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles#windows-z-tunnel-2-configuration).
        *   Supports Z-Tunnel 2.0 IPv6 traffic on IPv6-enabled Windows devices.
        *   Provides anti-tampering solution for processes, services, files, and registry keys. To learn more, see [Anti-Tampering for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector).
        *   Supports Partner Zscaler Private Access (ZPA) Tenant function to allow or remove access to partner ZPA resources from Zscaler Client Connector. To learn more, see [About Partner Devices](https://help.zscaler.com/zscaler-client-connector/about-partner-devices), [Enabling ZPA Partner Logins](https://help.zscaler.com/zscaler-client-connector/enabling-zpa-partner-logins), and [Viewing Device Fingerprint Information for a Partner Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-partner-device).

Contact Zscaler Support to enable this feature for your organization.

        *   Supports Zscaler Client Connector discovering the optimal path maximum transmission unit (MTU) for Z-Tunnel 2.0. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Adds verification using proof key for code exchange (PKCE) with ZIA for user enrollment (CVE-2023-28799) and an associated XSS vulnerability (CVE-2023-28800).
        *   Fixes an issue related to missing cloud path data due to the NPCAP installation failure.
        *   Fixes an issue where DNS packets were incorrectly tunneled when ZIA is disabled.
        *   Fixes an issue where the internet fails when the DNS request is routed to the company's DNS server instead of Zscaler's.
        *   Fixes an issue where Tray Manager Service starts twice when a user session exits.
        *   Fixes an issue where Zscaler Client Connector fails to connect in a timely fashion after the machine wakes up from sleep.
        *   Fixes an issue with application loopback exemption where Zscaler Client Connector was using an incorrect utility path on a 64-bit device.
        *   Fixes an issue with Zscaler Client Connector compatibility mode during browser-based authentication.
        *   Fixes the error prompting incorrect autopilot login credentials on Zscaler Client Connector's strict enforcement mode.
        *   Fixes Zscaler Client Connector's detection where it sets the VPN as the default interface route.
        *   Fixes an issue where a user could delete a file on a device in an elevated context. (CVE-2023-28797).
        *   Fixes an issue where a code execution vulnerability occurred with the repair app (CVE-2023-41970).

**December 13, 2022**

*   Release Available: Client Connector 4.0.0.80 for Windows

    *   ### **Zscaler Client Connector 4.0.0.80 Enhancements and Fixes**

        *   Fixes an issue where the internet fails when the DNS request is routed to the company's DNS server instead of Zscaler’s.
        *   Fixes reauthentication to Microsoft 365 applications after a password change.
        *   Fixes the DNS resolution when Zscaler Internet Access (ZIA) is disabled.
        *   Fixes the error prompting incorrect autopilot login credentials on Zscaler Client Connector’s strict enforcement mode.
        *   Fixes the incorrect Windows version in Google Chrome’s detection.
        *   Fixes the Tray Manager starting twice when a current session exists.
        *   Fixes Zscaler Client Connector’s detection where it sets the VPN as the default interface route.
        *   Fixes Zscaler Client Connector’s persistence in connecting mode when the machine is in sleep mode.

**December 07, 2022**

*   Release Available: Client Connector 3.9.0.183 for Windows

    *   ### **Zscaler Client Connector 3.9.0.183 Enhancements and Fixes**

        *   Fixes reauthentication to Microsoft 365 applications after a password change.
        *   Fixes the DNS resolution when Zscaler Internet Access (ZIA) is disabled.
        *   Fixes the error prompting incorrect autopilot login credentials on Zscaler Client Connector’s strict enforcement mode.
        *   Fixes the incorrect Windows version in Google Chrome’s detection.
        *   Fixes the Tray Manager starting twice when a current session exists.
        *   Fixes Zscaler Client Connector’s detection where it sets the VPN as the default interface route.
        *   Fixes Zscaler Client Connector’s persistence in connecting mode when the machine is in sleep mode.

**October 20, 2022**

*   Release in Limited Availability: Client Connector 4.0 for Windows

    *   ### **Zscaler Client Connector 4.0 Enhancements and Fixes**

        *   Supports Data Loss Prevention notifications.
        *   Zscaler Client Connector supports ARM processor-based Windows machines with operating systems running Windows 10 or 11.
        *   Provides separate one-time passwords (OTPs) for the logout and uninstall functions.
        *   Adds External Device ID value in Device Details.
        *   Adds support for posture-based risk profiles for Zscaler Internet Access (ZIA).
        *   Provides Disaster Recovery options when ZIA is not reachable.
        *   Provides Disaster Recovery options when ZPA is not reachable.
        *   Automatically updates application bypass for common applications on Z-Tunnel 2.0.
        *   Upgrades Npcap to version 1.71 for Intel and Advanced RISC Machines (ARM).
        *   Updates the Lightweight Filter (LWF) driver to improve performance and packet handling.
        *   Supports dropping inbound access to Zscaler Client Connector listening ports when in packet filter mode.
        *   Supports a fallback option that validates the Detect Microsoft Defender posture type when the certificate matching thumbprint check fails.
        *   Supports a network mounted user profile folder in a non-persistent VDI environment.
        *   Provides visibility into traffic bypassed from ZIA.
        *   Adds verification using proof key for code exchange (PKCE) with ZIA for user enrollment (CVE-2023-28799) and an associated XSS vulnerability (CVE-2023-28800).
        *   Fixes an issue where users were unable to connect to Wi-Fi on airplanes due to captive portal detection not working.
        *   Fixes an issue where the computer name in the ZPA IdP Metadata field was different from the name in the Machine Hostname field in the Zscaler Client Connector Portal.
        *   Fixes an issue where a deleted application segment blocked traffic.
        *   Fixes an issue where cloud path data appeared only intermittently.
        *   Fixes an issue where a quarantined device received a Connection Error but still connected.
        *   Fixes an issue where Zscaler Client Connector stopped tunneling traffic.
        *   Fixes an issue where PAC files published through Zscaler Private Access (ZPA) couldn’t be downloaded after upgrading.

**October 11, 2022**

*   Release Available: Client Connector 3.9.0.175 for Windows

    *   ### **Zscaler Client Connector 3.9.0.175 Enhancements and Fixes**

        *   Supports a new configurable endpoint for captive detection that won’t be bypassed from some captive portals.
        *   Improves the handling of duplicate DNS suffixes when configured via Zscaler Private Access (ZPA).
        *   Fixes an issue where Zscaler Client Connector did not detect the captive portal in some scenarios.
        *   Fixes an issue where the RDP session was disconnected when a user ran the session through the client-to-client ZPA. For more information, see [About Client-Based Remote Assistance](https://help.zscaler.com/zpa/about-client-based-remote-assistance).
        *   Fixes an issue where the service discovery did not correctly detect the proxy with enforce proxy configuration on trusted networks.
        *   Fixes an issue where the registry key Computer\HKEY_LOCAL_MACHINE\SOFTWARE\Zscaler Inc. was missing on Windows 32-bit OS.
        *   Fixes the network connectivity issue in the Drop IPv6 feature.
        *   Fixes an issue where Zscaler Client Connector authentication was not successful when using browser-based authentication.

**August 01, 2022**

*   Release in Limited Availability: Client Connector 3.9.0.160 for Windows

    *   ### **Zscaler Client Connector 3.9.0.160 Enhancements and Fixes**

        *   Fixes an issue where Z-Tunnel 2.0 connections for Zscaler Internet Access (ZIA) were unstable in dual stack networks.
        *   Fixes issues with IPv6 DNS packet handling.

**July 19, 2022**

*   Release in Limited Availability: Client Connector 3.9 for Windows

    *   ### **Zscaler Client Connector 3.9 Enhancements and Fixes**

        *   Enables customers to configure fallback options on Z-Tunnel 2.0 establishment failure. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports non-persistent VDI support changes. To learn more, see [Customizing Zscaler Client Connector with Install Options for MSI](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi).
        *   Supports a customizable list of VPN Trusted Network names to accurately detect VPN Trusted Networks. To learn more, see [About the Endpoint Integration Page](https://help.zscaler.com/zscaler-client-connector/about-endpoint-integration-page).
        *   Supports skipping trusted network criteria evaluation for Split VPN Trusted Network detection. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports running Zscaler Client Connector installs on Amazon Workspaces.
        *   Supports assigning specific entitlements to devices based on posture. To learn more, see [About Device Groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups-0) and [About Zscaler Entitlement](https://help.zscaler.com/zscaler-client-connector/about-zscaler-service-entitlement).
        *   Supports Zscaler Deception integration with Zscaler Client Connector. To learn more, see [About Zscaler Client Connector Integration with Deception](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-integration-deception).
        *   Supports the ability to revert Zscaler Client Connector to the previously installed version. To learn more, see [Reverting Zscaler Client Connector to the Previous Version](https://help.zscaler.com/zscaler-client-connector/reverting-zscaler-client-connector).
        *   Supports pausing notifications in Zscaler Client Connector. To learn more, see[Viewing Notifications on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector).
        *   Supports the Certificate Trust and Client Certificate posture types for the pre-Windows login machine tunnel. To learn more, see [Configuring Device Posture Profiles for ZPA](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles-zpa).
        *   Adds the option Fallback to Gateway Domain to use when PAC proxies cannot be reached for Z-Tunnel 1.0. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Improves Z-Tunnel 2.0 performance by optimizing the decision-making process for packets.
        *   Adds verification using proof key for code exchange (PKCE) with Zscaler Internet Access (ZIA) for user enrollment (CVE-2023-28799) and an associated XSS vulnerability (CVE-2023-28800).
        *   Fixes an issue where users remained authenticated and the machine tunnel remained on after rebooting if they did not log in to the workstation.
        *   Fixes an issue where some traffic was sent directly from the user’s machine in some scenarios.
        *   Fixes an issue where Zscaler Client Connector didn’t recognize the trusted network connection after a connection with the Checkpoint VPN client was established.
        *   Fixes an issue where the machine tunnel remained active even after it was removed from the app profile.
        *   Fixes an issue where Zscaler Client Connector bypassed traffic because it selected a single physical network adapter as the default and didn’t correctly select other active physical network adapters.

**June 23, 2022**

*   Release Available: Client Connector 3.8.0.102 for Windows

    *   ### **Zscaler Client Connector 3.8.0.102 Enhancements and Fixes**

        *   Fixes an issue where Windows bypassed Zscaler Internet Access (ZIA) when switching to a different NIC.
        *   Fixes an issue where users could change proxy settings when Enable Firefox Integration was enabled in the Zscaler Client Connector Portal.
        *   Fixes an issue where Zscaler Client Connector failed to build Z-Tunnel 2.0 with versions 3.7 and 3.8 and fell back to Z-Tunnel 1.0.
        *   Fixes an issue where users working from home experienced intermittent outages.
        *   Fixes an issue where users weren’t connecting to the closest data center because the PAC file download request was tunneled through ZIA.
        *   Fixes an issue where Zscaler Client Connector reconnected every 30 seconds.

**May 04, 2022**

*   Release in Limited Availability: Client Connector 3.8.0.100 for Windows

    *   ### **Zscaler Client Connector 3.8.0.100 Enhancements and Fixes**

        *   Improves the Z-Tunnel 2.0 teardown and deadlock detection functionality to avoid and recover from deadlocks.
        *   Fixes an issue where users remained authenticated and the machine tunnel remained on after rebooting if they did not log in to the workstation.
        *   Fixes an issue with Crowdstrike Zscaler Private Access (ZPA) integration.
        *   Fixes an issue where users were able to access the internet directly even though Zscaler Client Connector was on and enabled.
        *   Fixes an issue where Zscaler Client Connector did not recognize the trusted network connection after a connection with the Checkpoint VPN client was established.

**March 30, 2022**

*   Release Available: Client Connector 3.8 for Windows

    *   ### **Zscaler Client Connector 3.8 Enhancements and Fixes**

        *   Enhances the strict enforcement feature to block all ports and protocols.
        *   Supports using the Zscaler Client Connector Notification Framework. To learn more, see [About the Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework).
        *   Supports protecting sensitive and personal information in logs by restricting access to the Zscaler Program Data folder to only System Administrators.
        *   Adds two new options for the Z-Tunnel 2.0 protocol bypass feature: Redirect Web Traffic to ZCC Listening Proxy and Use Z-Tunnel 2.0 for Proxied Web Traffic. To learn more, see [Configuring Forwarding Profiles for Zscaler Client](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports overriding the Z-Tunnel 2.0 protocol settings by adding a new option on the User Privacy page. To learn more, see [About User Privacy](https://help.zscaler.com/zscaler-client-connector/about-user-privacy).
        *   Supports the ability for Zscaler Client Connector to detect if a device is running a split-tunnel VPN in addition to the currently supported full tunnel VPN Trusted Network detection. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Allows Zscaler Client Connector to evaluate trusted network criteria against all default route adapters. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Supports rate-limiting in tray notifications to suppress frequent and duplicate notifications. To learn more, see [About the Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework).
        *   Supports enabling the Zscaler Notification Framework as an alternative to the default Windows notification system. To learn more, see [About the Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework).
        *   Supports Npcap version 1.6 for ARM architecture-based machines.
        *   Adds verification using proof key for code exchange (PKCE) with Zscaler Internet Access (ZIA) for user enrollment (CVE-2023-28799) and an associated XSS vulnerability (CVE-2023-28800).
        *   Fixes an issue where Zscaler Client Connector processed machines as being Off Trusted Network when using VPNs in some scenarios.
        *   Fixes an issue where users can’t reopen the Zscaler Client Connector window and are instead prompted to log in using their browser.
        *   Fixes an issue where, in some scenarios, device posture evaluation for the firewall posture type fails.
        *   Fixes an issue where the client’s certificate is not checked by the Certificate Revocation List (CRL), producing inconsistent device posture evaluations.
        *   Fixes an issue where automatic reauthentication fails, and users have to click Authenticate to access Zscaler Private Access (ZPA) applications.
        *   Fixes an issue where the client-side certificate posture check was skipped after starting Zscaler Client Connector.
        *   Fixes an issue where a ZDX password was used to disable ZIA.
        *   Fixes an issue that handles non-standard ASCII characters in the user configuration, which requires users to log in again after rebooting.
        *   Fixes an issue where if Tunnel Internal Client Connector Traffic is selected, all DNS queries go inside the tunnel even if an asterisk (*) is entered in the Domain Inclusion for DNS Requests field.
        *   Fixes an inconsistent Apply On Network Change behavior while executing the proxy configuration.
        *   Fixes an issue by adding retry logic when a Z-Tunnel 1.0 request failed with an authentication required error (407).
        *   Fixes an issue where the machine tunnel service could not be restarted.
        *   Fixes a compatibility issue with the AnyConnect client where ZDX web probes on applications failed through ZIA.

**March 15, 2022**

*   Release Available: Client Connector 3.7.2.18 for Windows

    *   ### **Zscaler Client Connector 3.7.2.18 Enhancements and Fixes**

        *   Fixes an issue where the install parameters, like the cloud name, were not carried forward when upgrading Zscaler Client Connector from Windows 3.4. or Windows 3.4.1.

Release Available: Client Connector 3.7.1.56 for Windows

    *   ### **Zscaler Client Connector 3.7.1.56 Enhancements and Fixes**

        *   Fixes an issue where the install parameters, like the cloud name, were not carried forward when upgrading Zscaler Client Connector from Windows 3.4. or Windows 3.4.1.

**February 25, 2022**

*   Release Available: Client Connector 3.7.2 for Windows

    *   ### **Zscaler Client Connector 3.7.2 Enhancements and Fixes**

        *   Provides the ability to specify a unique username for each machine by adding the username parameter to the command-line interface.
        *   Fixes an issue where automatic re-authentication fails, and users have to click Authenticate to access internal Zscaler Private Access (ZPA) applications.
        *   Fixes an issue where the client’s certificate was not checked by the Certificate Revocation List (CRL), producing inconsistent device posture evaluations.
        *   Fixes an issue where device posture evaluation for the firewall posture type fails and users cannot access ZPA applications.
        *   Fixes an issue where users can’t reopen the Zscaler Client Connector window to log in if, after installation, users closed the window without logging in.
        *   Fixes an issue where the Z-Tunnel 2.0 include and exclude rules were not working if the port and protocols were specified in the rules.

Release Available: Client Connector 3.7.1.54 for Windows

    *   ### **Zscaler Client Connector 3.7.1.54 Enhancements and Fixes**

        *   Fixes an issue where the Z-Tunnel 2.0 include and exclude rules were not working if the port and protocols were specified in the rules.

**February 03, 2022**

*   Release Available: Client Connector 3.7.0.92 for Windows

    *   ### **Zscaler Client Connector 3.7.0.92 Enhancements and Fixes**

        *   Improves the performance of ZPA applications by implementing a socket option in Zscaler Client Connector that has no delays.
        *   Fixes an issue where Zscaler Client Connector experienced a connection error after the machine was locked or in sleep mode in some scenarios.
        *   Fixes an issue where VPN bypasses were not followed for DNS traffic containing IPv6 addresses, which prevented the Zscaler Digital Experience (ZDX) Cloud Path from reporting information for some end users.
        *   Fixes an issue where a hostname with multiple IPs caused connection issues that resulted in high CPU usage.
        *   Fixes a memory leak issue that caused posture policy evaluations to fail for CrowdStrike.
        *   Fixes an issue where the PAC file bypass failed when using a VPN on 3.6.
        *   Fixes an issue where ZOOM traffic was not bypassed from Zscaler Client Connector as configured.
        *   Fixes an issue where Zscaler Client Connector processed machines as being Off Trusted Network when using VPNs in some scenarios.
        *   Fixes an issue where Zscaler Client Connector did not recognize the Checkpoint VPN in 3.5.0.108.
        *   Fixes an issue where DNS requests were bypassing Z-Tunnel 2.0 on machines when the Dynamic Host Configuration Protocol (DHCP) assigned the IP address.

*   Release in Limited Availability: Client Connector 3.7.1.53 for Windows

    *   ### **Zscaler Client Connector 3.7.1.53 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector experienced a connection error after the machine was locked or in sleep mode in some scenarios.
        *   Fixes an issue where Zscaler Client Connector processed machines as being Off Trusted Network when using VPNs in some scenarios.
        *   Fixes an issue where DNS requests were bypassing Z-Tunnel 2.0 on machines when the Dynamic Host Configuration Protocol (DHCP) assigned the IP address.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/soft-removing-device-zscaler-admin-console -->

Title: Soft Removing a Device from the Zscaler Admin Console | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/soft-removing-device-zscaler-admin-console

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Soft Removing a Device from the Zscaler Admin Console

This article provides instructions on how to soft remove a device from the Zscaler Admin Console as an admin. Soft removing a device does not immediately remove it from the Zscaler Admin Console. To force remove a device, see[Force Removing a Device from the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/force-removing-device-zscaler-admin-console). When you remove a device, Zscaler Client Connector logs out the user but does not uninstall the app from the device. To uninstall the app, see [Uninstalling Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/uninstalling-zscaler-client-connector).

After soft removal, the following circumstances might require you to [force remove](https://help.zscaler.com/zscaler-client-connector/force-removing-device-zscaler-admin-console) a device:

*   The device state of **Device Removal Pending** stays on the **Device Overview** page for more than a day. This means that the user is inactive. If the user becomes active, Zscaler Client Connector sends a keepalive and logs out the user automatically.

A device can be in Device Removal Pending if the admin soft removed the device from the Zscaler Admin Console or removed the user from User Management in Internet & SaaS.

*   Zscaler Client Connector fails to send the keepalive or Zscaler Client Connector is uninstalled before the keepalive is sent.

To soft remove a device from the Zscaler Admin Console:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Device Overview**.
2.   Search for the **Device ID**or the**User ID** you want to remove.
3.   Select the checkbox on the right and click **Remove Checked Devices**. The device state changes to **Removal Pending**.

After the update is complete, typically in one hour, the **Removal Pending** device state changes to **Removed**, and the device is removed from the Zscaler Admin Console.

Device removal can take up to an hour. The end user can immediately remove the device from Zscaler Client Connector. To learn more, see [Deprovisioning Your Device from Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/deprovisioning-zscaler-client-connector?check_logged_in=1).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-vpn-trusted-network-adapter-criteria -->

Title: Adding a VPN-Trusted Network Adapter Name | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-vpn-trusted-network-adapter-criteria

Markdown Content:
# Adding a VPN-Trusted Network Adapter Name | Zscaler

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
*   [Endpoint Integration](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings/endpoint-integration)
*    Adding a VPN-Trusted Network Adapter Name 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Adding a VPN-Trusted Network Adapter Name

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

If your VPN isn't from a common vendor and the NIC adapter text doesn't match what is usually detected, you can add the name of your VPN so Zscaler Client Connector can accurately detect a VPN-Trusted Network.

To add VPN adapter names in the Zscaler Admin Console:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Endpoint Configuration**.
2.   On the **Endpoint Integration** tab, enter the VPN adapter vendor name in the **VPN-Trusted Network Adapter Criteria**field. Press `Enter` to add another VPN.
3.   When you're finished adding VPNs, click **Save**.

You can remove hard-coded VPN adapter names by clicking the **Delete**icon next to the VPN name.

![Image 6: Use the VPN-Trusted Network Adapter Criteria to add a VPN adapter strings](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-support-settings/endpoint-integration/configuring-vpn-trusted-network-adapter-criteria/zclient-connector-endpoint-integration-vpn-trusted-network-adapter.png)

When policies are updated on devices, new VPN adapter names are sent to Zscaler Client Connector. On the next network change or when services start, Zscaler Client Connector detects the new VPN adapter as a trusted network.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About the Endpoint Integration Page](https://help.zscaler.com/zscaler-client-connector/about-endpoint-integration-page)[Configuring Firefox Integration for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-firefox-integration-zscaler-client-connector)[Configuring the Port for Zscaler Client Connector to Listen On](https://help.zscaler.com/zscaler-client-connector/configuring-port-zscaler-client-connector-listen)[Adding a VPN-Trusted Network Adapter Name](https://help.zscaler.com/zscaler-client-connector/configuring-vpn-trusted-network-adapter-criteria)[Adding a VPN Service Name](https://help.zscaler.com/zscaler-client-connector/adding-vpn-service-name)[Configuring the Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-roles -->

Title: Adding Roles | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-roles

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding Roles

With Role Management, you can manage access to Zscaler Admin Console settings.

Admin roles must be assigned in Authentication Service. To learn more, see [Assigning Entitlements to Users and User Groups](https://help.zscaler.com/authentication-service/assigning-entitlements-users-and-user-groups).

The following three permissions are available when you add a role. You can also create a custom access level based on these permissions:

*   **Full**: View and configure settings.
*   **View Only**: View settings.
*   **None**: Setting is hidden.

To add a role:

1.   Go to **Administration**>**Admin Management**>**Role Based Access Control**>**Connectors**.
2.   Click **Add Role**.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-roles#RM)
3.   In the **Add Admin Role** window, provide information for the following fields:

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-roles#RM_Full)

    *   [Role Info](https://help.zscaler.com/zscaler-client-connector/adding-roles#roleinfo)[](https://help.zscaler.com/zscaler-client-connector/adding-roles)**Name**: Enter a name for the role. The name you enter cannot contain special characters, except periods (.), hyphens (-), and underscores ( _ ).

[Close](https://help.zscaler.com/zscaler-client-connector/adding-roles#roleinfo) 
    *   [Permissions](https://help.zscaler.com/zscaler-client-connector/adding-roles#permissions)

[](https://help.zscaler.com/zscaler-client-connector/adding-roles)Permissions allow you to control an admin’s access to Zscaler Admin Console administration settings. You can choose one of the following permissions: **Full**, **View Only**, **None**, or **Customize**.

For some settings, only **View Only** or **None**is available.

Expand each section to display the settings under that section and choose the permission for the role you’re creating. When you choose **Full**, **View Only**, or **None**, that permission applies to the setting's entire section. When you choose **Customize**, you can select a mix of permissions for each setting.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-roles#ARE)

        *   [Dashboard](https://help.zscaler.com/zscaler-client-connector/adding-roles#dashboard)[](https://help.zscaler.com/zscaler-client-connector/adding-roles)On the [Dashboard](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-dashboard), admins can view data for all or specific users, all or specific device states, and all or specific operating systems. **View Only** is the default permission for the Dashboard and cannot be changed.

[Close](https://help.zscaler.com/zscaler-client-connector/adding-roles#dashboard) 
        *   [Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/adding-roles#enrolled)

The Enrolled Devices menu includes the following settings:

            *   [**Device Overview**](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices): View, sort, filter, and export data for enrolled devices and removed devices.
            *   [**Machine Tunnel**](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels):View a list of machine tunnels, details about each machine tunnel, and remove machine tunnels.
            *   [**Partner Devices**](https://help.zscaler.com/zscaler-client-connector/about-partner-devices): View, sort, filter, and export data for Partner Devices.

Choose one of the following permissions:

            *   **Full**: Allows access to all settings on the Device Overview page and the Machine Tunnel page. Admins must have full access to remove devices and machine tunnels.
            *   **View Only**: Allows access to view, filter, sort, export, and search data on the Device Overview page and the Machine Tunnel page.
            *   **None**: Does not allow access to the Device Overview page and the Machine Tunnel page.
            *   **Customize**: Allows you to choose a permission level for each setting.

[Close](https://help.zscaler.com/zscaler-client-connector/adding-roles#enrolled)

        *   [App Profiles](https://help.zscaler.com/zscaler-client-connector/adding-roles#app_profiles)

[](https://help.zscaler.com/zscaler-client-connector/adding-roles)Admins can view [app profile](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-profiles) rules for a specific platform, a list of all configured app rules, the policy token for an app profile rule, and the default policy. Admins can also configure, edit, or delete an app profile rule.

For each platform listed, choose one of the following permissions:

            *   **Full**: Allows access to all settings for all platforms on the App Profiles page. Admins must have full access to configure, edit, and delete app profile rules, except the default policy.
            *   **View Only**: Allows access to only view data on the App Profiles page.
            *   **None**: Does not allow access to the App Profiles page.
            *   **Customize**: Allows you to choose a permission level for each platform.

[Close](https://help.zscaler.com/zscaler-client-connector/adding-roles#app_profiles)

        *   [Administration](https://help.zscaler.com/zscaler-client-connector/adding-roles#Admin)
        *   [Sensitive Data](https://help.zscaler.com/zscaler-client-connector/adding-roles#sensitive-data)The Sensitive Data section includes the option to obfuscate passwords and tokens in the Zscaler Admin Console.

**Obfuscate Passwords and Tokens**: When enabled, all passwords, OTP, Policy Tokens, Device Tokens, and Machine Tokens in the Zscaler Admin Console are obfuscated. This feature is applicable to admins who have read-only access.

[Close](https://help.zscaler.com/zscaler-client-connector/adding-roles#sensitive-data) 

[Close](https://help.zscaler.com/zscaler-client-connector/adding-roles#permissions)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/copying-app-profile -->

Title: Copying a Zscaler Client Connector App Profile | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/copying-app-profile

Markdown Content:
# Copying a Zscaler Client Connector App Profile | Zscaler

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
*    Copying a Zscaler Client Connector App Profile 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Copying a Zscaler Client Connector App Profile

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

You can copy an existing [Zscaler Client Connector App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) and change the settings to customize the profile to fit your needs instead of having to configure each setting in a profile. By duplicating an existing profile, you can test a profile before deploying it to reduce mistakes.

To copy a Zscaler Client Connector App Profile:

1.   Go to **Infrastructure**>**Connectors**>**Client.**
2.   Under Platform Settings, select the OS.
3.   Click the **Copy**icon next to the profile you want to copy.

![Image 6: Copy App Profile](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/copying-app-profile/ZClient-Connector-CopyAppProfilePage.png)

You cannot copy the default profile.

The **Copy <Platform> Policy** window is displayed.

1.   (Optional) Enter a new name in the **Name**field.
2.   Make changes to settings as desired.
3.   Click **Save**.

Your copied profile appears under the profile you copied.

You can copy a maximum of 10 profiles at the same time.

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

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-automatic-crash-reporting-zscaler-client-connector -->

Title: Configuring Automatic Crash Reporting for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-automatic-crash-reporting-zscaler-client-connector

Markdown Content:
# Configuring Automatic Crash Reporting for Zscaler Client Connector | Zscaler

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
*    Configuring Automatic Crash Reporting for Zscaler Client Connector 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Configuring Automatic Crash Reporting for Zscaler Client Connector

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

For the Android version of Zscaler Client Connector, the app automatically uploads crash reports, which might contain user-identifiable information. You can enable or disable the app from uploading crash reports, allowing for compliance with General Data Protection Regulation (GDPR).

Zscaler recommends that you enable this feature, because the crash reports provide crucial information for resolving unexpected issues.

To configure whether the app uploads or does not upload crash reports:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**User Privacy**.
2.   Select the **Disable Automatic Crash Reporting**option to stop Zscaler Client Connector from uploading crash reports. Disable the option to allow Zscaler Client Connector to upload crash reports.

![Image 6: Disable Automatic Crash Reporting option](https://help.zscaler.com/downloads/zclient-connector-user-privacy-disable-auto-crash-rptg.png)

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

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-trusted-networks -->

Title: About Trusted Networks | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-trusted-networks

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Trusted Networks

[Watch a video about Trusted Networks.](https://fast.wistia.net/embed/iframe/uccu8mi4d4)

To have Zscaler Client Connector identify one of your organization's trusted networks, you must first define conditions for that network as criteria that Zscaler Client Connector uses for verification.

Predefining the Trusted Network Criteria for your networks on the Trusted Networks page allows you to select multiple trusted networks in a [forwarding profile](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles). This prevents you from having to define the same network repeatedly for your forwarding profiles.

Trusted Networks provide the following benefits and allow you to:

*   Define criteria for your networks so Zscaler Client Connector can easily identify and verify them as trusted networks.
*   Designate multiple trusted networks in a forwarding profile.
*   Quickly locate a trusted network using the Search feature.
*   Easily view, edit, and delete trusted networks.

## About the Trusted Networks Page

On the Trusted Networks page (Infrastructure > Locations > Trusted Networks), you can do the following:

1.   [Configure trusted network criteria for one of your networks.](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-app)
2.   [Search for a trusted network](https://help.zscaler.com/zscaler-client-connector/searching-trusted-network).
3.   View a list of all configured trusted networks.
4.   [Edit a trusted network.](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-app-portal)
5.   [Delete a trusted network.](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-app-portal)

![Image 2: About Trusted Network](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/about-trusted-networks/Trusted_Network_0.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-zdx-group-users -->

Title: Enabling ZDX for a Group of Users | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-zdx-group-users

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling ZDX for a Group of Users

You can use Zscaler Service Entitlement to select which users can enroll into ZDX. To enable ZDX for only a select group of users, you must deploy Zscaler Client Connector 2.2.1 or later.

If you’re using device groups, the user must belong to both the device group and user group to avoid disconnecting ZPA services.

## Enabling ZDX at a Group Level

To enable ZDX for a group of users:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   From the left menu, select **Zscaler Service Entitlement**.
3.   Click the **Zscaler Digital Experience**tab.
4.   To enable ZDX for only a group of users, ensure that **ZDX Enabled by Default** is disabled. If this setting is enabled, then ZDX is available for all users and you cannot assign ZDX to a group.

1.   Select a group of users from the drop-down menu and click **Done**. The default setting is **None**. This option means no groups have access to ZDX. This allows users to keep their current settings, allowing you to choose when to configure it for them.

These groups are defined in the ZIA Admin Portal. If you do not see your groups, ensure that groups were synced to the Zscaler Client Connector Portal. To learn more, see [Syncing Directory Groups between the ZIA Admin Portal and App Portal](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-zscaler-admin-portal-and-app-portal).

1.   To enable ZDX for [device groups](https://help.zscaler.com/zscaler-client-connector/about-device-groups-0), select one or more groups from the **Device Groups** drop-down menu.
2.   Select **Logout Zscaler Client Connector when ZDX Entitlement is Enabled** to automatically log users out of Zscaler Client Connector when ZDX is enabled for a device group. Users can then log in again to enable the ZDX service. This applies to customers using ZPA only or ZPA and Zscaler Deception. When disabled, Zscaler Client Connector runs without the ZDX service until the next Zscaler Client Connector login.

![Image 2: Configure ZDX user groups for Zscaler Service Entitlement](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/enabling-zdx-group-users/ZDX_Entitlement.png)

1.   Click **Save**.

This updates your users' devices the next time they connect. If they are already connected, the devices automatically update in 60 minutes. To manually update their devices, users can go into Zscaler Client Connector and click **Update Policy** from the **More** window. After manually refreshing, they must reauthenticate on the **Digital Experience** window.

## Possible Configurations for ZDX

The following table provides possible configurations for the Zscaler Service Entitlement feature and the resulting behavior of the ZDX service:

**ZDX Enabled by Default****Groups Specified****Behavior**
Enabled N/A ZDX service is enabled for all users
Disabled No ZDX service is not enabled for any users
Disabled Yes ZDX service is enabled only for the specified group of users

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector -->

Title: Viewing Information About Private Access on Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Viewing Information About Private Access on Zscaler Client Connector

This article provides an overview of the Private Access window of Zscaler Client Connector for different OSs. This window features connectivity information and traffic statistics for [Private Access](https://help.zscaler.com/zpa/what-zscaler-private-access).

*   [Windows](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#win)

[](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)This window includes the following features on Windows devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

    *   **Username**: Displays the username with which you are logged into the app.
    *   **Service Status**: Displays the app connection status. Click **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you click**Turn On**.
    *   **Network Type:** Displays the type of network you are connected to (**Trusted Network**, **VPN-Trusted Network**, **Split VPN-Trusted Network**, or **Off-Trusted Network**).
    *   **Authentication Status**: Displays the current authentication state (i.e., whether you are currently authenticated or not).
    *   **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
    *   **Broker:** Displays the IP address of the server to which your traffic is being forwarded.
    *   **Client:**Displays the local IP address of your device.
    *   **Time Connected**: Displays your initial connection time for the present session.
    *   **Protocol:** Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

    *   **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the Z-Tunnel.
    *   **Total Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the Z-Tunnel.

![Image 2: Private Access Window](https://help.zscaler.com/downloads/zscaler-client-connector/end-user-guide/viewing-information-about-private-access-zscaler-client-connector/zscaler-client-connector-private-access-windows_2.png)

## VPN Tunnel

This section displays only if you are connected to the Zscaler cloud using a VPN connection. To learn more, see [About VPN Service Edges](https://help.zscaler.com/zpa/about-vpn-service-edges).

This section displays the following connectivity information:

    *   **VPN Service Edge**: Displays the IP address of the VPN Service Edge to which your traffic is being forwarded.

This field displays as **Network Service Edge** in previous versions of Zscaler Client Connector.

    *   **Client IP**: Displays the IP address assigned to your device by the VPN Service Edge for the session.
    *   **Time Connected**: Displays your initial connection time for the present session.
    *   **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the VPN Tunnel.
    *   **Total Bytes Received**: Displays, in real time, bytes of traffic received from your computer through the VPN Tunnel.

![Image 3: VPN Tunnel section](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-information-about-private-access-zscaler-client-connector-draft-doc-53331/zclient-connector-vpn-tunnel-windows.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#win)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#macos)

[](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)This window includes the following features on macOS devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

    *   **Username**: Displays the username with which you are logged into the app.
    *   **Service Status**: Displays the app connection status. Click **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you click**Turn On**.
    *   **Network Type:** Displays the type of network you are connected to (Trusted Network, VPN-Trusted Network, Split VPN-Trusted Network, or Off-Trusted Network).
    *   **Authentication Status**: Displays the current authentication state (i.e., whether you are currently authenticated or not).
    *   **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
    *   **Broker:** Displays the IP address of the server to which your traffic is being forwarded.
    *   **Client:**Displays the local IP address of your device.
    *   **Time Connected**: Displays your initial connection time for the present session.
    *   **Tunnel Protocol:** Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

    *   **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the Z-Tunnel.
    *   **Total Bytes Received**: Displays, in real time, bytes of traffic received by your computer through the Z-Tunnel.

![Image 4: Private Access Window](https://help.zscaler.com/downloads/zscaler-client-connector/end-user-guide/viewing-information-about-private-access-zscaler-client-connector/viewing-zpa-macos.png)

## VPN Tunnel

This section displays only if you are connected to the Zscaler cloud using a VPN connection. To learn more, see [About VPN Service Edges](https://help.zscaler.com/zpa/about-vpn-service-edges).

This section displays the following connectivity information:

    *   **VPN Service Edge**: Displays the IP address of the VPN Service Edge to which your traffic is being forwarded.

This field displays as **Network Service Edge** in previous versions of Zscaler Client Connector.

    *   **Client IP**: Displays the IP address assigned to your device by the VPN Service Edge for the session.
    *   **Time Connected**: Displays your initial connection time for the present session.
    *   **Total Bytes Sent**: Displays, in real time, bytes of traffic sent from your computer through the VPN Tunnel.
    *   **Total Bytes Received**: Displays, in real time, bytes of traffic received from your computer through the VPN Tunnel.

![Image 5: VPN Tunnel section](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-information-about-private-access-zscaler-client-connector-draft-doc-53331/zclient-connector-vpn-tunnel-windows.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#macos)

*   [Linux](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#linux)

[](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)This window includes the following features on Linux devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

    *   **Username**: Displays the username with which you are logged into the app.
    *   **Service Status**: Displays the app connection status. Click **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you click**Turn On**.
    *   **Network Type**: Displays the type of network you are connected to (Trusted Network, VPN-Trusted Network, Split VPN-Trusted Network, or Off-Trusted Network).
    *   **Authentication Status**: Displays the current authentication state (i.e., whether you are currently authenticated or not).
    *   **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
    *   **Broker**: Displays the IP address of the server to which your traffic is being forwarded.
    *   **Client**: Displays the local IP address of your device.
    *   **Time Connected**: Displays your initial connection time for the present session.

## Statistics

This section displays the following traffic statistics:

    *   **Total Bytes Sent:** Displays, in real time, bytes of traffic that your mobile device has sent through the Z-Tunnel.
    *   **Total Bytes Received**: Displays, in real time, bytes of traffic that your mobile device has received through the Z-Tunnel.

![Image 6: Private Access Window](https://help.zscaler.com/downloads/zscaler-client-connector/end-user-guide/viewing-information-about-private-access-zscaler-client-connector/Client-Connector-Viewing_ZPA_Linux_ZCC.png)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#linux)

*   [Android](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#android)

[](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)This window includes the following features on Android devices:

## Zero Trust Connectivity

This section displays the following connectivity information:

    *   **Username**: Displays the username with which you are logged in to the app.
    *   **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you tap **Turn On**.
    *   **Network Type**: Displays the type of network you're connected to (Trusted Network or Off-Trusted Network).
    *   **Authentication Status**: Displays whether the user is authenticated to the Private Access service.
    *   **Authenticate Early**: Click to reauthenticate Private Access before your authentication expires.
    *   **Broker**: Displays the IP address of the server to which your traffic is being forwarded.
    *   **Time Connected**: Displays your initial connection time for the present session.
    *   **Protocol**: Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

    *   **Total Bytes Sent**: Displays, in real time, bytes of traffic that your mobile device has sent through the Z-Tunnel.
    *   **Total Bytes Received**: Displays, in real time, bytes of traffic that your mobile device has received through the Z-Tunnel.

![Image 7: Private Access Screen](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-information-about-private-access-zscaler-client-connector/Client_Connector_Android_ZPA.jpg)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#android)

*   [Android on ChromeOS](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#chrome)

[](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)This window includes the following features of Android on ChromeOS devices:

## Connectivity

This section displays the following connectivity information:

    *   **Username**: Displays the username with which you are logged into the app.
    *   **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. The Private Access service is disabled until you tap **Turn On**.
    *   **Network Type**: Displays the type of network you're connected to (Trusted Network or Off-Trusted Network).
    *   **Authentication Status**: Displays whether the user is authenticated to the Private Access service.
    *   **Authenticate Early**: Click to reauthenticate Private Access before your authentication expires.
    *   **Broker**: Displays the IP address of the server to which your traffic is being forwarded.
    *   **Client**: Displays the local IP address of your device.
    *   **Time Connected**: Displays your initial connection time for the present session.
    *   **Tunnel Protocol**: Displays the protocol used for the tunnel (TLS or DTLS).

## Statistics

This section displays the following traffic statistics:

    *   **Total Bytes Sent**: Displays, in real time, bytes of traffic that your mobile device has sent through the Z-Tunnel.
    *   **Total Bytes Received**: Displays, in real time, bytes of traffic that your mobile device has received through the Z-Tunnel.

![Image 8: Private Access Window](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/viewing-information-about-private-access-zscaler-client-connector/ZPA_Authenticate_Early.jpg)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#chrome)

*   [iOS](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#ios)

[](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)This screen includes the following features on iOS devices.

## Zero Trust Connectivity

This section displays the following connectivity information:

    *   **Username**: Displays the username with which you are logged into the app.
    *   **Service Status**: Displays the app connection status. Tap **Turn Off** if you want to turn off the Zscaler Tunnel (Z-Tunnel) and disable the Private Access service while remaining logged in to the app. Depending on your organization’s policies, you might be required to enter a password. The Private Access service is disabled until you tap **Turn On**.
    *   **Network Status**: Displays the type of network you are connected to (Trusted Network, VPN Trusted Network, Split VPN Trusted Network, or Off-Trusted Network).
    *   **Authentication Status**: Displays whether or not the user is authenticated to the Private Access service.
    *   **Authenticate Early**: Click **Authenticate Early** to reauthenticate Private Access before your authentication expires.
    *   **Broker**: Displays the broker Zscaler Client Connector is connected to.
    *   **Client**: Displays your client IP address.
    *   **Time Connected**: Displays your initial connection time for the present session.
    *   **Tunnel Version**: Displays the version of the Zscaler Tunnel (Z-Tunnel) that Zscaler Client Connector forwards your traffic through.
    *   **Tunnel SDK Version**: The current tunnel SDK version.

## Statistics

This section displays the following traffic statistics:

    *   **Data Sent**: Displays, in real time, bytes of traffic sent from your mobile device through the Z-Tunnel.
    *   **Data Received**: Displays, in real time, bytes of traffic received by your mobile device through the Z-Tunnel.

![Image 9: Private Access Screen](https://help.zscaler.com/downloads/zscaler-client-connector/end-user-guide/viewing-information-about-private-access-zscaler-client-connector/viewing-zpa-ios.PNG)

[Close](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector#ios)

For information about other Zscaler Client Connector features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux -->

Title: Customizing Zscaler Client Connector with Install Options for Linux | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Customizing Zscaler Client Connector with Install Options for Linux

You can use the application package, the Debian package, or the Red Hat Package Manager (RPM) to manually install Zscaler Client Connector on a device. You can also use these packages if you're deploying the app to users through a device management method that supports Linux devices.

After downloading the Zscaler Client Connector package, you can deploy the file with your device management method or add install options to customize the app package using various command-line options. You can edit the parameter configuration in both the Debian package and the RPM package using a Zscaler script.

Zscaler Client Connector relies on the Linux systemd-resolved service to manage DNS configurations.

Because /etc/resolv.conf is a symbolic link to /run/systemd/resolve/stub-resolv.conf, you should not modify the /etc/resolv.conf file when deploying Zscaler Client Connector for Linux.

Zscaler Client Connector requires NetworkManager and its CLI, nmcli, which is a default system program that provides detection and configuration for systems to automatically connect to networks.

This article covers the following topics:

*   [Installing Runtime Dependencies](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-package-dependencies)

As long as your device is connected to the network, the application installer automatically installs the necessary package dependencies for you. The individual package dependencies are listed here for reference:

    *   [Debian-Based Operating System](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#DB)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)Install the following dependencies depending on the distribution you're using:

        *   [Debian 11](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Debian11)

            *   libglib2.0-0
            *   net-tools
            *   dbus
            *   libqt5core5a
            *   libqt5webengine5
            *   libqt5webenginewidgets5
            *   libqt5sql5
            *   libqt5webkit5
            *   libdbus-glib-1-2
            *   libnss3-tools
            *   libnss-resolve
            *   libpcap0.8
            *   curl
            *   jq
            *   systemd-coredump
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Debian11)

        *   [Debian 12](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Debian12)

            *   libglib2.0-0
            *   net-tools
            *   dbus
            *   libqt5core5a
            *   libqt5webengine5
            *   libqt5webenginewidgets5
            *   libqt5sql5
            *   libqt5webkit5
            *   libdbus-glib-1-2
            *   libnss3-tools
            *   libnss-resolve
            *   libpcap0.8
            *   curl
            *   jq
            *   systemd-coredump
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Debian12)

        *   [Ubuntu 20](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ubuntu20)

            *   libglib2.0-0
            *   net-tools
            *   libqt5dbus5
            *   libqt5core5a
            *   libqt5gui5
            *   libqt5opengl5
            *   libqt5qml5
            *   libqt5quick5
            *   libqt5quickcontrols2-5
            *   libqt5quickparticles5
            *   libqt5quickwidgets5
            *   libqt5sql5
            *   libqt5sql5-sqlite
            *   libqt5webchannel5
            *   libqt5webengine5
            *   libqt5webenginecore5
            *   libqt5webenginewidgets5
            *   libqt5webkit5
            *   libqt5webview5
            *   libqt5widgets5
            *   dbus
            *   libdbus-glib-1-2
            *   libnss3-tools
            *   libnss-resolve
            *   libpcap0.8 curl
            *   jq
            *   systemd-coredump

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ubuntu20)

        *   [Ubuntu 22.04.5 LTS](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ubuntu22)

            *   libglib2.0-0
            *   net-tools
            *   libqt5dbus5
            *   libqt5core5a
            *   libqt5gui5
            *   libqt5opengl5
            *   libqt5qml5
            *   libqt5quick5
            *   libqt5quickcontrols2-5
            *   libqt5quickparticles5
            *   libqt5quickwidgets5
            *   libqt5sql5
            *   libqt5sql5-sqlite
            *   libqt5webchannel5
            *   libqt5webengine5
            *   libqt5webenginecore5
            *   libqt5webenginewidgets5
            *   libqt5webkit5
            *   libqt5webview5
            *   libqt5widgets5
            *   dbus
            *   libdbus-glib-1-2
            *   libnss3-tools
            *   libnss-resolve
            *   libpcap0.8 curl
            *   jq
            *   systemd-coredump
            *   qt6-qpa-plugins
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ubuntu22)

        *   [Ubuntu 24 LTS](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ubuntu24)

            *   libglib2.0-0t64
            *   libqt5dbus5t64
            *   libqt5core5t64
            *   libqt5gui5t64
            *   libqt5opengl5t64
            *   libqt5qml5
            *   libqt5quick5
            *   libqt5quickcontrols2-5
            *   libqt5quickparticles5
            *   libqt5quickwidgets5
            *   libqt5sql5
            *   libqt5sql5-sqlite
            *   libqt5webchannel5
            *   libqt5webengine5
            *   libqt5webenginecore5
            *   libqt5webenginewidgets5
            *   libqt5webkit5
            *   libqt5webview5
            *   libqt5widgets5t64
            *   dbus
            *   libdbus-glib-1-2
            *   libnss3-tools
            *   libnss-resolve
            *   libpcap0.8t64
            *   curl
            *   jq
            *   systemd-coredump
            *   qt6-qpa-plugins
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ubuntu24)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#DB)

    *   [RPM-Based Operating System](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RPM)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)Install the following dependencies depending on the distribution you're using:

        *   [Fedora 41 and 42](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#fed41-42)

            *   dbus-devel
            *   dbus-glib-devel
            *   qt6-qtwebengine
            *   qt6-qtbase-devel
            *   net-tools
            *   dbus
            *   dbus-glib
            *   nss-util
            *   ca-certificates
            *   libpcap
            *   systemd
            *   curl
            *   jq
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#fed41-42)

        *   [CentOS 8 Stream](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#centos8)

            *   dbus-devel
            *   dbus-glib-devel
            *   qt5-qtwebengine
            *   qt5-qtbase-devel
            *   net-tools
            *   dbus
            *   dbus-glib
            *   nss-util
            *   ca-certificates
            *   libpcap
            *   systemd
            *   curl
            *   jq

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#centos8)

        *   [CentOS 9 Stream](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#centos9)

            *   dbus-devel
            *   dbus-glib-devel
            *   qt5-qtwebengine
            *   qt5-qtbase-devel
            *   net-tools
            *   dbus
            *   dbus-glib
            *   nss-util
            *   ca-certificates
            *   libpcap
            *   systemd
            *   curl
            *   jq
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

CentOS 9 Stream has an additional package, systemd-resolved, that is part of the Extra Packages for Enterprise Linux (EPEL) repository which must be installed separately for CentOS. The CentOS OS is required to enable the EPEL repository for Qt packages. Use one of the following commands to enable the EPEL repository:

            *   `sudo yum -y install epel-release`
            *   `sudo yum -y --disablerepo="*" --enablerepo="epel" update`

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#centos9)

        *   [CentOS 10](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#centos10)

            *   dbus-devel
            *   dbus-glib-devel
            *   net-tools
            *   dbus
            *   dbus-glib
            *   nss-util
            *   ca-certificates
            *   libpcap
            *   systemd
            *   curl
            *   jq
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6webenginecore6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools
            *   qt6-qtwebengine
            *   qt6-qtbase

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#centos10)

        *   [Rocky Linux 9.7, 10.01](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#rocky9-10)

            *   qt5-qtbase
            *   qt5-qtwebengine

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#rocky9-10)

        *   [Red Hat Enterprise Linux (RHEL)](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL-main)

            *   [Red Hat Enterprise Linux (RHEL) 8.x, 9.1, 9.2](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL8)

                *   dbus-devel
                *   dbus-glib-devel
                *   qt5-qtbase
                *   qt5-qtwebengine
                *   net-tools
                *   dbus
                *   dbus-glib
                *   ca-certificates
                *   nss-util
                *   nss
                *   libpcap
                *   curl
                *   jq

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL8)

            *   [Red Hat Enterprise Linux (RHEL) 9.3, 9.4, 9.5](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL9)

                *   dbus-devel
                *   dbus-glib-devel
                *   qt5-qtbase
                *   qt5-qtwebengine
                *   net-tools
                *   dbus
                *   dbus-glib
                *   ca-certificates
                *   nss-util
                *   nss
                *   libpcap
                *   curl
                *   jq
                *   libqt6dbus6
                *   libqt6core6
                *   libqt6gui6
                *   libqt6qml6
                *   libqt6quick6
                *   ibqt6quickcontrols2-6
                *   libqt6quickparticles6
                *   libqt6quickwidgets6
                *   libqt6sql6
                *   libqt6sql6-sqlite
                *   libqt6webchannel6
                *   libqt6webenginecore6
                *   libqt6webenginewidgets6
                *   libqt6webview6
                *   libqt6widgets6
                *   libqt6webenginecore6-bin
                *   qt6-base-dev
                *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL9)

            *   [Red Hat Enterprise Linux (RHEL) 10](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL10)

                *   dbus-devel
                *   dbus-glib-devel
                *   qt6-qtbase
                *   qt6-qtwebengine
                *   net-tools
                *   dbus
                *   dbus-glib
                *   ca-certificates
                *   nss-util
                *   nss
                *   libpcap
                *   curl
                *   jq
                *   libqt6dbus6
                *   libqt6core6
                *   libqt6gui6
                *   libqt6qml6
                *   libqt6quick6
                *   ibqt6quickcontrols2-6
                *   libqt6quickparticles6
                *   libqt6quickwidgets6
                *   libqt6sql6
                *   libqt6sql6-sqlite
                *   libqt6webchannel6
                *   libqt6webenginecore6
                *   libqt6webenginewidgets6
                *   libqt6webview6
                *   libqt6widgets6
                *   libqt6webenginecore6-bin
                *   qt6-base-dev
                *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL10)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RHEL-main)

        *   [Arch Linux](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#archlinux)

            *   glib2
            *   net-tools
            *   inetutils
            *   qt5-base
            *   qt5-tools
            *   qt5-webengine
            *   dbus-glib
            *   ca-certificates
            *   nss
            *   libpcap
            *   wget
            *   curl
            *   jq
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#archlinux)

        *   [OpenSUSE Leap](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#opensuse)

            *   dbus-1
            *   dbus-1-devel
            *   dbus-1-glib
            *   dbus-1-glib-devel
            *   ca-certificates
            *   nss-systemd
            *   systemd-network
            *   libpcap1
            *   libpcap-devel
            *   libQt5Core5
            *   libQt5WebKit5
            *   libQt5WebView5
            *   systemd
            *   curl
            *   jq
            *   systemd-coredump
            *   mozilla-nss-tools
            *   libqt6dbus6
            *   libqt6core6
            *   libqt6gui6
            *   libqt6qml6
            *   libqt6quick6
            *   ibqt6quickcontrols2-6
            *   libqt6quickparticles6
            *   libqt6quickwidgets6
            *   libqt6sql6
            *   libqt6sql6-sqlite
            *   libqt6webchannel6
            *   libqt6webenginecore6
            *   libqt6webenginewidgets6
            *   libqt6webview6
            *   libqt6widgets6
            *   libqt6webenginecore6-bin
            *   qt6-base-dev
            *   qt6-base-dev-tools

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#opensuse)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#RPM)

If dependencies don't automatically install, you can install them manually by entering all dependencies in a command line on one line without line breaks, as in the following examples:

Example for Debian:

`sudo apt install libglib2.0-0 net-tools dbus libqt5core5a libqt5webengine5 libqt5webenginewidgets5 libqt5sql5 libqt5webkit5 libdbus-glib-1-2 libnss3-tools libnss-resolve libpcap0.8 curl jq systemd-coredump -y`
Example for RPM:

`sudo yum install dbus-devel dbus-glib-devel qt5-qtwebengine qt5-qtbase-devel net-tools dbus dbus-glib nss-util ca-certificates libpcap system curl jq -y`

*   [Installing the Application Using the Zscaler Run File](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-application-run)

You must have administrator permission to do the installation.

To download and install the application:

    1.   From a terminal window, open a command prompt as administrator.
    2.   Download the Zscaler run file from the Zscaler Client Connector App Store on the Zscaler Client Connector Portal. To learn more, see [Understanding Zscaler Client Connector App Downloads](https://help.zscaler.com/client-connector/understanding-zscaler-client-connector-app-downloads).
    3.   Navigate to the folder with the installation file:

 cd ~/Downloads
    
    1.   Make the file executable using the following command:

sudo chmod a+x Zscaler-linux-<version number>-installer.run
    1.   Install the application using the following command:

 sudo ./Zscaler-linux-<version number>-installer.run
    
    1.   Leave the token form empty. You'll see the progress of your installation.

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-application-run)

*   [Installing the Application Using the Debian Package](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-application-deb)

You must have administrator permission to do the installation. Contact Zscaler Support to obtain the Debian package.

Installing Zscaler Client Connector with the Debian package requires the following two-step process to ensure you can provide install parameters during the installation:

    1.   Install the Debian package.
    2.   [Configure installation parameters using either interactive or silent mode](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#configure).

The Debian package is a silent installer and does not allow any arguments to pass next to it. For example, this command would not work with the Debian package:

 sudo .deb --strictEnforcement 0 --enableFips 1. 
The configuration script `zscaler-config` resolves this issue. This script edits the parameters in this file:

/opt/zscaler/.config.ini.
The Zscaler Debian package name follows this convention:

<packagename>_<version number>-<Debianrevisionnumber>_<Debianarchitecture>".deb
    
Zscaler's package name is:

zscaler-client_<version number>_amd64.deb
                                                                                                                                                                                    zscaler-client-debug_<version number>_amd64.deb
Examples:

zscaler-client_1.3.0.11-0_amd64.deb 
                                                                                                                                                                                    zscaler-client-debug_1.3.0.11-0_amd64.deb 
    
    *   [Installing the Debian Package](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-deb)

Zscaler recommends installing `systemd-coredump` before installing the Zscaler Client Connector Debian package.

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)Enter one of the following commands to install the Debian package on the system:

        *   `dpkg -i``<absolutePathToThePackage>`
        *   `sudo apt-get install``<absolutePathToThePackage>`

Examples:

        *   `sudo dpkg -i /home/zuser/Downloads/zscaler-client_1.3.0.11-0_amd64.deb`
        *   `sudo dpkg -i ~/Downloads/zscaler-client_1.3.0.11-0_amd64.deb`
        *   `sudo apt-get install /home/zuser/Downloads/zscaler-client_1.3.0.11-0_amd64.deb`
        *   `sudo apt-get install ~/Downloads/zscaler-client_1.3.0.11-0_amd64.deb`

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-deb)

    *   [Removing the Debian Package](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#remove-deb)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)Enter one of the following commands to remove the Debian package from the system:

        *   `sudo apt-get remove``<PackageName>`
        *   `sudo apt-get purge``<PackageName>`

Examples:

        *   `sudo apt-get remove zscaler-client`
        *   `sudo apt-get purge zscaler-client`

If the Zscaler Client Connector was already installed on the system with the Zscaler run file and a user wants to install the Zscaler Client Connector Debian package, first uninstall the Zscaler run file before installing the Debian package.

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#remove-deb)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-application-deb)

*   [Downloading and Installing with RPM](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-RPM)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)RPM is a package management system that runs on Red Hat Enterprise Linux (RHEL), CentOS, and Fedora. You can use RPM to distribute, manage, and update software that you create for any of these operating systems. RPM simplifies the distribution of software because RPM packages are standalone binary files, similar to compressed archives.

Zscaler Client Connector for Linux supports the following RPM distributions: RHEL, Fedora, CentOS, and openSUSE. The Zscaler Client Connector Linux RPM package is a `.tar` file, i.e., `zscaler-client–``<release version>``-rpm.tar`, and contains two files that perform the following tasks:

    *   `Zscaler_package_installation.sh`: Zscaler Client Connector installation requires different dependencies based on the respective distribution. This script identifies the distribution and installs the corresponding dependencies.
    *   `Zscaler-client-``<release version>``-1.x86_64.rpm`: This file installs Zscaler Client Connector binaries in each system.

To download and install the CLI version of the RPM Package for Zscaler Client Connector version 3.7.1 and later for Linux, follow these steps:

    1.   Obtain the Zscaler Client Connector RPM package from your IT administrator.
    2.   Copy the `.tar` file to the Linux endpoint device and unzip the package using the following command:

`tar -xvf zscaler-client-3.7.1.51-rpm.tar`

![Image 2: CLI to unzip the RPM package](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/customizing-zscaler-client-connector-install-options-linux/step1_unzip.png)

    1.   Run the following installation script to install all dependencies for Zscaler Client Connector:

`sudo ./zscaler_package_installation.sh install_dependency_libs`

![Image 3: Client-Connector-install-dependencies](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/customizing-zscaler-client-connector-install-options-linux/figure%202%20example.png)

    1.   Install the RPM package using one of the following commands, depending on the distribution you’re using:
        *   For Fedora, RHEL, and CentOS, use one of the following commands:
            *   `sudo dnf install zscaler-client-3.7.1.51-1.x86_64.rpm`
            *   `sudo yum install zscaler-client-3.7.1.51-1.x86_64.rpm`

        *   For openSUSE, enter `sudo zypper install zscaler-client-3.7.1.51-1.x86_64.rpm`.

![Image 4: Client Connector Linux Command for installing the RPM package](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/customizing-zscaler-client-connector-install-options-linux/figure%203%20example.png)

While the Zscaler Client Connector package installation script displays all missing dependencies on the system and installs the missing packages for Zscaler Client Connector, it doesn’t remove Zscaler Client Connector dependencies in the current release.

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-RPM)

*   [Installing with Command-Line Options Using the Zscaler Run File](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-package-command-line)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)You can install the package for Zscaler Client Connector from a terminal using specific Linux command-line options. For example:

sudo ./Zscaler-linux-1.0.0.108-installer.run <install options>
                            
Replace <install options> with one or more of the following options:

    *   [--cloudName](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#cn)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)With this install option, you can specify the cloud to which the app must send user traffic so your users won't make the selection during enrollment. Do not use this option if your organization is provisioned on one cloud. The app automatically sends traffic to the proper cloud.

This install option is required if you enable the`--strictEnforcement` option.

To add this option using the CLI, enter --cloudName <organization's cloud name in lowercase>. For example, if your cloud name is zscalertwo.net, you would enter zscalertwo. To learn more, see [What Is My Cloud Name for ZIA?](https://help.zscaler.com/zia/what-my-cloud-name-zia)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#cn) 
    *   [--deviceToken](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#dt)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)The --deviceToken install option only applies to Zscaler Internet Access (ZIA). It is not supported by Zscaler Private Access (ZPA).

This install option allows you to use Zscaler Client Connector Portal as an IdP. The Zscaler service silently provisions and authenticates users even if you don't have an authentication mechanism in place. Before adding this option, you must generate a device token in the Zscaler Client Connector Portal and complete the full configuration detailed in [Using Zscaler Client Connector Portal as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-portal-identity-provider).

To add this option using the CLI, enter --deviceToken<device token from the Zscaler Client Connector Portal>.

![Image 5: Device Token](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/customizing-zscaler-client-connector-install-options-linux/client-connector-IdP-Device-Token.png)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#dt) 
    *   [--hideAppUIOnLaunch](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#hide)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)This install option forces the app window to stay hidden before users enroll. Users can always open the window by clicking the app icon in the system tray.

To enable this option using the CLI, enter --hideAppUIOnLaunch 1. By default, the value is 0 (i.e., disabled).

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#hide) 
    *   [--policyToken](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#pt)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)This install option allows you to specify which app profile policy you want to enforce for the app before the user enrolls. All relevant settings associated with the policy apply, including the bypass of the IdP login page. After the user enrolls, this policy is replaced with the app profile policy that matches the user based on group affiliation.

Prerequisites:

        *   This install option is only applicable, and required, if you enable the --strictEnforcement option and want users to enroll with the app before accessing the internet.
        *   In the Zscaler Client Connector Portal, you must [configure the app profile policy](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) that you want to enforce and ensure that the custom PAC file associated with that policy includes a bypass for your IdP login page. This allows the user to access the IdP page to log in as necessary before enrolling with the app.

To add this option using the CLI, enter --policyToken <policy token from the Zscaler Client Connector Portal>.

![Image 6: Policy Token](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/customizing-zscaler-client-connector-install-options-linux/client-connector-app-profiles-token.png)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#pt)

    *   [--strictEnforcement](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#se)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)This install option only works when the forwarding profile action for Zscaler Client Connector is **Tunnel** or **Tunnel with Local Proxy**. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).

This install option allows you to require users to enroll with the app before accessing the internet and blocks traffic in the following situations:

        *   The user has not yet logged in after a new install.
        *   A user logs in and logs out.
        *   An administrator removes a device.

This install option does not affect users that remain logged in and disable the ZIA service.

If you enable this install option, the `--cloudName` and `--policyToken` options are required.

To enable this option using the CLI, enter `--strictEnforcement 1`. By default, the value is 0 (i.e., disabled).

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#se)

    *   [--userDomain](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ud)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)This install option allows users to skip the app enrollment page. If SSO is enabled for your organization, users are taken directly to your organization's SSO login page. If you've integrated SSO with the app, users can also skip the SSO login page and are automatically enrolled in the Zscaler service and logged in.

[See image.](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#udimage2)

To add the install option using the CLI, enter --userDomain <your organization's domain>. If your instance has multiple domains associated with it, enter the primary domain for your instance.

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ud) 
    *   [--enableFips](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ef)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)This install option allows you to enable the Federal Information Processing Standard (FIPS) mode for Zscaler Client Connector. By default, the value is 0 (i.e., disabled).

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#ef) 

For example:

sudo ./Zscaler-linux-1.0.0.108-installer.run --hideAppUIOnLaunch 1 --cloudName zscalertwo
                            
The following image is an example of a command that uses all the available installation options, where:

    *   The cloud on which the organization is provisioned is zscalertwo.
    *   The device token value is 123456789.
    *   The policy token value is 123456789.
    *   The organization's domain name is safemarch.com.

![Image 7: Installation example showing all parameter options on command line](https://help.zscaler.com/downloads/z-app/downloading-deployment/customizing-zscaler-client-connector-install-options-linux/client-connector-all-options.png)

*   [Providing Command-Line Options with the Debian Package](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-package-command-line-deb)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)Only administrators can use `zscaler-config` to edit the parameter configuration after the Debian package is installed on the system and requires root permission. Use the `zscaler-config` script to edit the following parameters:

    *   cloudName
    *   userDomain
    *   enableFips
    *   Help
    *   hideAppUIOnLaunch
    *   User password
    *   externalRedirect
    *   strictEnforcement
    *   policyToken
    *   deviceToken
    *   userName
    *   waitForInput

The `zscaler-config` script resides in the `/usr/bin` directory. You can use this script in the following two ways:

    *   [Interactive Mode](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Debconf)[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)Run `zscaler-config` without parameters (e.g., `sudo zscaler-config`). You are prompted with a series of questions to set the parameters, as shown in the following image:

![Image 8: Debian package install](https://help.zscaler.com/downloads/zscaler-tech-pubs-style-guide/draft-articles/customizing-zscaler-client-connector-install-options-linux-draft-0/MO-11001_1.png)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Debconf) 
    *   [Silent Mode](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Silent)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)You can change the order of parameters without any UI prompting. For example, you can use the following commands in the following combinations to edit parameters:

        *   **command**: `sudo zscaler-config -h`
        *   **When to use**: To see parameter help.
        *   **Output**:Usage

zscaler-config - [ -c Cloud Name ] [-d User Domain ] [ -f Enable Fips ]
                        [ -h Help] [ -l hideAppUIOnLaunch ] [ -p User password ] [ -r externalRedirect ]
                        [ -s strictEnforcement ] [ -t policyToken ] [ -T deviceToken ] [ -u username ]
                        [ -w waitForInput ] 
                        zscaler-config - [ No Argument ] For UI based interaction 
                        zscaler-config - [ -c Cloud Name ][ String Argument ] 
                        zscaler-config - [ -d User Domain ] [ String Argument ] 
                        zscaler-config - [-f Enable Fips ] [ 0/1 ] 
                        zscaler-config - [ -h Help] 
                        zscaler-config - [ -l Hide App UI on Launch ] [ 0/1 ] 
                        zscaler-config - [ -p User Password ] [ Sring Argument ] 
                        zscaler-config - [ -r External Redirect ] [ 0/1 ] 
                        zscaler-config - [ -s Strict Enforcement] [ 0/1 ] 
                        zscaler-config - [ -S Disable sudo keyword inside the scripts] [ 0/1 ] 
                        zscaler-config - [ -t Policy Token ] [ String Argument ] 
                        zscaler-config - [ -T Device Token ] [ String Argument ] 
                        zscaler-config - [ -u User Name ] [ String Argument ] 
                        zscaler-config - [ -w Wait for Input ] [ 0/1 ] 
        *   **command**:`sudo zscaler-config -s 1 -t``<Policy Token>``-T``<Device Token>``-u``<User Name>``-u`
        *   **When to Use**: To set the value for `strictEnforcement`, `policyToken`, `deviceToken`, and `username`.
        *   **Output**:None. The command writes data in the `/opt/zscaler/.config.ini` file.

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#Silent)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-package-command-line-deb)

*   [Providing Command-Line Options with the RPM Package](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-package-command-line-RPM)

Only administrators can use `zscaler-config` to edit the configuration after the RPM package is installed on the system. It also requires admin privileges. Use the `zscaler-config` script to edit the following parameters:

    *   cloudName
    *   userDomain
    *   enableFips
    *   Help
    *   hideAppUIOnLaunch
    *   externalRedirect
    *   strictEnforcement
    *   policyToken
    *   deviceToken
    *   userName
    *   MAPubKey

The `zscaler-config` script resides in the `/usr/bin` directory. You can use this script in the following combinations to edit these parameters:

    *   **command**: `sudo zscaler-config -h`
    *   **When to use**: To see parameter help.
    *   **Output**: Usage

    *   **command**: `sudo zscaler-config -s 1 -t``<Policy Token``> -T``<Device Token>``-u``<User Name>`
    *   **When to Use**: To set the value for `strictEnforcement`, `policyToken`, `deviceToken`, and `username`.
    *   **Output**: None. The command writes data in the `/opt/zscaler/.config.ini` file.

![Image 9: client connector edit parameters using zscaler-config](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/customizing-zscaler-client-connector-install-options-linux/figure%204%20example.png)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-package-command-line-RPM)

*   [Locating Logs](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-log-locations)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux)The following are locations where you can find related log information:

    *   Tray logs: <user home>/.Zscaler/Logs
    *   Zscaler system logs: /var/log/zscaler/.Zscaler/Logs
    *   Installer errors: /var/log/zscaler/zscaler-installation.log

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-linux#install-log-locations)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-internet-saas-posture-profiles -->

Title: About Internet & SaaS Posture Profiles | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-internet-saas-posture-profiles

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Internet & SaaS Posture Profiles

[Watch a video about Internet & SaaS Posture Profiles.](https://fast.wistia.net/embed/iframe/wrcft32xt4)

A posture profile is a set of criteria evaluated on users’ devices that admins can use for configuring access policies in the Zscaler Admin Console. Posture profiles enable admins to apply different policies to users’ personal devices versus their corporate devices. For example, admins might need to grant access to social media sites for users’ personal devices but not for their corporate devices.

Admins can create new device postures or use the existing ones [to determine Internet & SaaS (ZIA) risk levels](https://help.zscaler.com/zscaler-client-connector/adding-internet-saas-posture-profiles). When you add a posture profile, the devices are given a trust level (High, Medium, Low), which is selected when configuring a [Firewall](https://help.zscaler.com/zia/configuring-firewall-policies), [Firewall Filtering](https://help.zscaler.com/zia/configuring-firewall-filtering-policy), [URL Filtering](https://help.zscaler.com/zia/configuring-url-filtering-policy), [File Type Control](https://help.zscaler.com/zia/configuring-file-type-control-policy), [SSL Inspection](https://help.zscaler.com/zia/configuring-ssl-inspection-policy), or [Cloud App Control](https://help.zscaler.com/zia/adding-rules-cloud-app-control-policy) policy in the Zscaler Admin Console.

Internet & SaaS device posture profiles provide the following benefits and allow you to:

*   Determine access to corporate resources and public applications based on the device posture you set.
*   Set up and align posture profiles with trust levels for use in Internet & SaaS policies.

On the Internet & SaaS Posture Profiles page (Policies > Common Configuration > Resources > Internet & SaaS Posture Profiles), you can do the following:

1.   [Add posture profile rules for a specific platform.](https://help.zscaler.com/zscaler-client-connector/adding-internet-saas-posture-profiles)
2.   Search for an [Internet & SaaS posture profile](https://help.zscaler.com/zscaler-client-connector/searching-internet-saas-posture-profile).
3.   View a list of all configured posture profile rules for a specific platform.
4.   Create a custom view of the Internet & SaaS Posture Profiles page.
5.   [Edit a posture profile rule.](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-client-connector-portal)
6.   [Delete a posture profile rule.](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-client-connector-portal)

![Image 2: Client Connector Manage Internet & SaaS Posture](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/configuring-zscaler-client-connector-app-profiles/Client_Connector-ZIA-Posture-Profiles-Page.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-vpn-service-name -->

Title: Adding a VPN Service Name | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-vpn-service-name

Markdown Content:
# Adding a VPN Service Name | Zscaler

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
*   [Endpoint Integration](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings/endpoint-integration)
*    Adding a VPN Service Name 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Adding a VPN Service Name

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

By default, traffic that is bypassed from Zscaler Client Connector is not logged. When you enable and configure flow logging in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) and select **VPN**to capture traffic logs for bypassed data, you must add a VPN service name to classify traffic flow into a VPN tunnel if the VPN service is not already listed.

Contact Zscaler Support to enable this feature.

You can view existing VPN service names in the **VPN Services for Flow Logging** list.

To add VPN services in the Zscaler Admin Console:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Endpoint Configuration**.
2.   On the **Endpoint Integration** tab, enter the VPN service name in the **VPN Services for Flow Logging** field. The name can be up to 65 characters.

![Image 6: VPN Services for Flow Logging option](https://help.zscaler.com/downloads/zclient-connector-endpoint-integration-vpn-services-flow-logging.png)

3.   Press `Enter` to add another VPN service.
4.   When you're finished adding VPN services, click **Save**.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About the Endpoint Integration Page](https://help.zscaler.com/zscaler-client-connector/about-endpoint-integration-page)[Configuring Firefox Integration for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-firefox-integration-zscaler-client-connector)[Configuring the Port for Zscaler Client Connector to Listen On](https://help.zscaler.com/zscaler-client-connector/configuring-port-zscaler-client-connector-listen)[Adding a VPN-Trusted Network Adapter Name](https://help.zscaler.com/zscaler-client-connector/configuring-vpn-trusted-network-adapter-criteria)[Adding a VPN Service Name](https://help.zscaler.com/zscaler-client-connector/adding-vpn-service-name)[Configuring the Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zpa/about-client-connector-ip-assignment -->

Title: About Client Connector IP Assignment | Zscaler

URL Source: https://help.zscaler.com/zpa/about-client-connector-ip-assignment

Markdown Content:
![Image 1: icon-zpa.svg](blob:http://localhost/f387fb0b7fab0d5b509efd7f530296e1)

## Private Access (ZPA)

### About Client Connector IP Assignment

Client Connector IP Assignment controls the Zscaler virtual IP addresses assigned to clients during connectivity to the Zero Trust Exchange (ZTE). These IP addresses do not appear in the Zscaler Client Connector interface list, but are unique IP addresses within each Zscaler Client Connector. These virtual IP addresses are used for [server-to-client connectivity](https://help.zscaler.com/zpa/understanding-server-client-connectivity) and client-to-client connectivity where applications cannot use FQDNs to connect. For server-to-client connectivity, these IP address ranges must be non-overlapping and routed to the Branch Connector or Cloud Connector as the next hop router.

Zscaler Client Connector IP assignment provides the following benefits and enables you to:

*   Assign Zscaler virtual IP addresses to Zscaler Client Connector.
*   Complete the first step to configure a server-to-client connection.

To configure server-to-client connectivity, you must configure an IP address range and create application segments with the Zscaler virtual IP address of the client that is creating the IP-based connection. To learn more, see [Configuring Server-to-Client Connectivity](https://help.zscaler.com/zpa/configuring-server-client-connectivity).

## About the IP Ranges Page

On the IP Ranges page (Infrastructure> Private Access > Client Connector Policies > Client Connector IP Assignment), you can do the following:

1.   View a list of applied filters available from the current and previous user sessions. Applied filters must be saved to the user session first before they can be viewed. Use the drop-down menu to select the applied filters to view. To learn more, see [Using Tables](https://help.zscaler.com/unified/using-tables#filter).
2.   Hide the filters on the page by clicking **Hide Filters**. Click **Show Filters** to display the filters.
3.   Refresh the IP Ranges page to reflect the most current information.
4.   Filter the IP address range information that appears in the table. By default, no filters are applied. You can also save applied filters to your preferences so that they're visible in future user sessions. To learn more, see [Using Tables](https://help.zscaler.com/unified/using-tables#filter).
5.   [Add an IP address range](https://help.zscaler.com/zpa/adding-ip-ranges).
6.   Expand all of the rows in the table to see more information about each IP address range.
7.   View a list of all IP address ranges that are configured for your organization. For each IP address range, you can see:
    *   **Name**: The unique name of the IP address range. You can also click the **Sort**icon (![Image 2: Reorder icon in the ZPA Admin Portal](https://help.zscaler.com/downloads/tech-pubs-drafts/zpa-draft-articles/about-client-connector-ip-assignment/zpa-reorder-icon-in-table-columns.png)) to sort the **Name** column in ascending order. Click the icon again to sort the column in descending order.
    *   **IP Range or IP Subnet**: The IP address ranges or IP address subnets of the client or destination device.
    *   **Location Details**: The location that is bound to the IP range in the following format: City, State, Country.
    *   **Total IPs**: The total number of IP addresses in the range.
    *   **Used IPs**: The total number of IP addresses currently used.
    *   **Status**: The status of the IP range (**Enabled**or **Disabled**). You can also click the **Sort** icon to sort the **Status**column in ascending order. Click the icon again to sort the column in alphabetical order.

8.   [Modify the columns displayed in the table.](https://help.zscaler.com/zpa/using-tables)
9.   Copy an existing IP address range. Copying an existing IP address range opens the **Add IP Range** window with duplicate details of the selected IP range.
10.   [Edit an existing IP address range](https://help.zscaler.com/zpa/editing-ip-ranges).
11.   Delete an IP address range.
12.   Enable an existing disabled IP address range. Enabling a disabled IP address range opens the **Enable IP Range**window. Click **Enable** to proceed.
13.   Display more rows or a different page of the table.
14.   Go to the [IP Bindings](https://help.zscaler.com/zpa/about-ip-bindings) page to view and download the IP addresses bound to Zscaler Client Connector.

![Image 3: Viewing the IP Ranges page](https://help.zscaler.com/downloads/zpa/administration/peer-peer-connectivity/about-client-connector-ip-assignment/xc-ip-ranges-page2.png)

IP address ranges must be non-overlapping. Zscaler recommends that IP address ranges are broad enough to cover your endpoints based on geolocation criteria. Otherwise, the IP address from another available IP address range is assigned.

---

<!-- source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-processes-allowlist -->

Title: Zscaler Client Connector for VDI Processes to Allowlist | Zscaler

URL Source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-processes-allowlist

Markdown Content:
# Zscaler Client Connector for VDI Processes to Allowlist | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Cloud & Branch Connector

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

*   [Zscaler Cloud & Branch Connector Help](https://help.zscaler.com/cloud-branch-connector)
*   [Zscaler Client Connector for VDI Management](https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-management)
*    Zscaler Client Connector for VDI Processes to Allowlist 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-cloud-connector.svg](blob:http://localhost/6e416d15bcb50c1001e80e392c70c45c)

# Cloud & Branch Connector

### Zscaler Client Connector for VDI Processes to Allowlist

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

Zscaler recommends that you allowlist Zscaler Client Connector for VDI processes that permit Virtual Desktop Infrastructure (VDI) binaries and processes. You can only allowlist in Windows. To learn more about Zscaler Client Connector for VDI, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)

# Allowlist Processes

The file paths to allowlist for Zscaler Client Connector for VDI are:

*   `%ProgramFiles%\ZCCVDI\ZCCVDIHelper.exe`
*   `%ProgramFiles%\ZCCVDI\ZCCVDIService.exe`
*   `%ProgramFiles%\ZCCVDI\ZCCVDIUI\ZCCVDIUI.exe`
*   `%ProgramFiles%\ZCCVDI\ThirdParty\WebView2\MicrosoftEdgeWebview2Setup.exe`
*   `%ProgramData%\ZCCVDI`

# Bypasses for Firewall

If you have a firewall managed by group policy object (GPO), you can configure firewall rules on your endpoint protection product for `ZCCVDI.exe` processes for all ports, protocols, network interfaces, and network addresses (e.g., `0.0.0.0/0`).

*   `ZCCVDIUI.exe: Outbound`
*   `ZCCVDIService.exe: Outbound`

# Processes Usage

The following list describes what each process is used for:

*   **ZCCVDIHelper**: An internal process used by other Zscaler Client Connector for VDI processes.
*   **ZCCVDIUI**: The user interface of the application.
*   **ZCCVDIService**: The main service that manages all other internal services.
*   **ProgramData\ZCCVDI**: The directory that stores logs and configuration for Zscaler Client Connector for VDI.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi)[Downloading Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/downloading-zscaler-client-connector-vdi)[Step-by-Step Configuration Guide for Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/step-step-configuration-guide-zscaler-client-connector-vdi)[Customizing Zscaler Client Connector for VDI with Install Options for MSI](https://help.zscaler.com/cloud-branch-connector/customizing-zscaler-client-connector-vdi-install-options-msi)[Zscaler Client Connector for VDI Processes to Allowlist](https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-processes-allowlist)[About VDI Devices](https://help.zscaler.com/cloud-branch-connector/about-vdi-devices)[About VDI Groups](https://help.zscaler.com/cloud-branch-connector/about-vdi-groups)[Configuring VDI Groups](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-groups)[About VDI Templates](https://help.zscaler.com/cloud-branch-connector/about-vdi-templates)[Configuring VDI Templates](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-templates)[About VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/about-vdi-forwarding-profiles)[Configuring VDI Forwarding Profiles](https://help.zscaler.com/cloud-branch-connector/configuring-vdi-forwarding-profiles)[Troubleshooting Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/troubleshooting-zscaler-client-connector-vdi)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-zdx-module-upgrades-cli -->

Title: Enabling ZDX Module Upgrades via the CLI | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-zdx-module-upgrades-cli

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling ZDX Module Upgrades via the CLI

You can upgrade the Zscaler Digital Experience (ZDX) Module for Windows via the CLI if you want to deploy an upgrade separately from a Zscaler Client Connector upgrade. Before you can use a [downloaded package](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades) to [upgrade via the CLI](https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely), you must enable this feature.

This feature is available only for Zscaler Client Connector version 4.7 and later for Windows.

To enable ZDX Module upgrades via the CLI:

1.   Go to **Infrastructure**>**Connectors**>**Client**.
2.   Under Platform Settings, select **Windows** and click the **Platform Settings** tab.
3.   Enable **Allow ZDX Client Upgrade via CLI**.

![Image 2: Enable Allow ZDX Client Upgrade Via CLI](https://help.zscaler.com/downloads/zscaler-client-connector/platform-and-authentication-management/enabling-zdx-module-upgrades-cli/zclient-connector-platform-settings-enable-zdx-cli.png)

4.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-client-connector-telemetry -->

Title: Enabling Zscaler Client Connector Telemetry | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-client-connector-telemetry

Markdown Content:
# Enabling Zscaler Client Connector Telemetry | Zscaler

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
*   [App Supportability](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-support-settings/app-supportability)
*    Enabling Zscaler Client Connector Telemetry 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Enabling Zscaler Client Connector Telemetry

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

Zscaler Client Connector Telemetry gets telemetry and device event information from devices. It collects a subset of the information already collected by Zscaler Digital Experience (ZDX). It also enables device event collection, which provides valuable reporting such as the [Device Events](https://help.zscaler.com/zscaler-client-connector/understanding-zscaler-client-connector-portal-dashboard#device-events) page and the [Failed Posture Devices](https://help.zscaler.com/zscaler-client-connector/about-failed-posture-devices) page.

You can use this setting to enable or disable Zscaler Client Connector Telemetry for all users. The default value is enabled.

When the option is enabled, the Zscaler Client Connector Telemetry option on all existing and new app profiles for [Windows](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#telemetry_options), [macOS](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#mac_telemetry), and [Linux](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#lin-telemetry) is enabled, but if required, you can disable the option on individual app profiles.

If you disable the option, the Zscaler Client Connector Telemetry option on all existing app profiles is also disabled, and you cannot enable it.

To enable Zscaler Client Connector Telemetry for all users:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**App Supportability**.
2.   On the **App Supportability** tab, select **Enable Zscaler Client Connector Telemetry**.

![Image 6: Enable Zscaler Client Connector Telemetry](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-profile-management/configuring-zscaler-client-connector-app-profiles/zclient-connector-app-supportability-enable-telemetry.png)

3.   Click **Save**.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[About App Supportability](https://help.zscaler.com/zscaler-client-connector/about-app-supportability)[Configuring User Access to Logging Controls for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector)[Configuring User Access to Support Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-support-options-zscaler-client-connector)[Configuring User Access to the Restart and Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-and-repair-options-zscaler-client-connector)[Enabling Zscaler Client Connector Telemetry](https://help.zscaler.com/zscaler-client-connector/enabling-zscaler-client-connector-telemetry)[Configuring Automatic Username Population for IdP Authentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-username-population-idp-authentication)[Configuring Automatic ZPA Reauthentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication)[Registering Devices with the ZPA IdP Username](https://help.zscaler.com/zscaler-client-connector/registering-devices-zpa-idp-username)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/domains-add-ssl-bypass-list -->

Title: Domains to Add to SSL Bypass List | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/domains-add-ssl-bypass-list

Markdown Content:
# Domains to Add to SSL Bypass List | Zscaler

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
*    Domains to Add to SSL Bypass List 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Domains to Add to SSL Bypass List

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

To ensure Zscaler Client Connector properly processes traffic for Private Access, all domains for Zscaler Client Connector enrollment and authentication that are listed on [config.zscaler.com/private.zscaler.com/zpa](http://config.zscaler.com/private.zscaler.com/zpa) must be in your SSL bypass list. Also, be sure to include any domains used by your organization's identity provider (IdP).

If you are using Okta as your organization's IdP, you must also include Okta-specific domains in your SSL bypass list. To learn more, see the [Okta documentation](https://help.okta.com/en-us/content/topics/security/ip-address-allow-listing.htm%20).

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[What Is Zscaler Client Connector?](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)[Why Is Zscaler Client Connector on My Device?](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device)[Step-by-Step Configuration Guide for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)[Zscaler Client Connector Update Intervals](https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals)[Zscaler Client Connector Resource Usage](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-resource-usage)[Domains to Add to SSL Bypass List](https://help.zscaler.com/zscaler-client-connector/domains-add-ssl-bypass-list)[Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-ios -->

Title: Customizing Zscaler Client Connector with Install Options for iOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-ios

Markdown Content:
. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2020 -->

Title: Client Connector App Release Summary (2020) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2020

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Client Connector App Release Summary (2020)

**Select an OS:**

**Select an app version:**

**Select a deployment date:**

* * *

The Client Connector app versions for Windows listed below were deployed on the following dates.

**December 18, 2020**

*   Release Available: Client Connector 3.1.0.103 for Windows

    *   ### **Zscaler Client Connector 3.1.0.103 Enhancements and Fixes**

Contact the Zscaler Account team if you don't see this release enabled for your organization.

        *   Fixes a timing issue that resulted in connection errors while accessing Zscaler Private Access (ZPA) applications when Posture Checks were in use.
        *   Fixes an issue where process-based Posture Checks were not working correctly.
        *   Fixes an issue that prevented Zscaler Client Connector upgrade in a rare instance where the termination process had not completed.
        *   Fixes an issue where a user could not log in to ZScaler Client Connector when multiple proxies were configured.
        *   Fixes an issue where a user login would fail with error 10110 when the autofill username option was used in some instances.
        *   Fixes an issue where the Registry Key-based Posture Checks were not working with ZPA device Posture Check policies.
        *   Fixes an issue where the tray icon was greyed out after Client Connector was running for a few days.

Version 3.1.0.103 includes all fixes and enhancements from version 3.1 and version 3.1.0.96. This includes the following issues fixed in version 3.1. To learn more, see [Zscaler Client Connector 3.1 Enhancements and Fixes](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2020?applicable_category=Windows&applicable_version=3.1) and [Zscaler Client Connector 3.1.0.96 Enhancements and Fixes](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2020?applicable_category=Windows&applicable_version=3.1.0.96).

        *   Fixes an issue where in some instances an environment variable could be abused on Windows operating systems. (CVSS - 5.6)
        *   Hardens some components of Zscaler Client Connector which could be susceptible to exploitation on Windows operating systems. (CVE-2020-11635)
        *   Hardens security for the inter process communication to prevent exploitation on Windows operating systems. (CVSS - 7.3)

**December 10, 2020**

*   Release Available: Client Connector 3.1.0.96 for Windows

    *   ### **Zscaler Client Connector 3.1.0.96 Enhancements and Fixes**

Contact the Zscaler Account team if you don't see this release enabled for your organization.

        *   Fixes an issue where Zscaler Client Connector displayed incorrect AUP messages.
        *   Restores certificate-based posture functionality when the posture is validated against a root certificate.
        *   Fixes an issue where Zscaler Client Connector did not launch after upgrade.
        *   Fixes an issue where Zscaler Client Connector displayed a connection error after Zscaler Private Access (ZPA) reauthentication.
        *   Fixes an issue where the One Time Password for uninstall was not working after upgrading Zscaler Client Connector.
        *   Restores custom certificate support when using certificates with the .CER format.

All fixes and enhancements within version 3.1 were included in version 3.1.0.96. This includes the following issues fixed in version 3.1. To learn more, see [Zscaler Client Connector 3.1 Enhancements and Fixes](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2020?applicable_category=Windows&applicable_version=3.1).

        *   Fixes an issue where in some instances an environment variable could be abused on Windows operating systems. (CVSS - 5.6)
        *   Hardens some components of Zscaler Client Connector which could be susceptible to exploitation on Windows operating systems. (CVE-2020-11635)
        *   Hardens security for the inter process communication to prevent exploitation on Windows operating systems. (CVSS - 7.3)

**December 02, 2020**

*   Release Available: Client Connector 3.1 for Windows

    *   ### **Zscaler Client Connector 3.1 Enhancements and Fixes**

Contact the Zscaler Account team if you don't see this release enabled for your organization.

        *   Introduces an architecture change to allow for improved security posture. This change adds a new service ZSATrayManager that must be . To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).
        *   Hardens some components of Zscaler Client Connector which could be susceptible to exploitation on Windows operating systems. (CVE-2020-11635)
        *   Hardens security for the inter process communication to prevent exploitation on Windows operating systems. (CVSS - 7.3)
        *   Fixes an issue where in some instances an environment variable could be abused on Windows operating systems. (CVSS - 5.6)
        *   Fixes an issue where the Zscaler Client Connector update failed when it coincided with user enrollment.
        *   Fixes an issue where Zscaler Client Connector incorrectly indicated a ZPA reauthentication warning in the System Tray even though the user was in a trusted network.
        *   Fixes an issue where Zscaler Client Connector sometimes failed to delete the filters corresponding to ZPA applications when ZPA was turned off or in fail-open state.
        *   Fixes an issue where Symantec AV version 14.2 was blocking the ZSATray process from coming up.
        *   Fixes an issue where Zscaler Client Connector logged out due to a local configuration decryption failure when the CPU ID on the system changed.
        *   Zscaler Client Connector now evaluates the posture as non-compliant if the intermediate CA or the root CA is not installed when the certificate chain installed on the system is incomplete.
        *   Fixes an issue where Zscaler Client Connector logged out due to local configuration decryption failure when the battery was removed or changed on the system.

**November 16, 2020**

*   Release in Limited Availability: Client Connector 3.0.2 for Windows

    *   ### **Zscaler Client Connector 3.0.2 Enhancements and Fixes**

Contact the Zscaler Account team to enable this release for your organization.

        *   Zscaler Client Connector now evaluates postures on a periodic basis.
        *   Zscaler Client Connector now supports posture checks for client certificates in the system certificate store.
        *   Improves interoperability for AD users.
        *   Improves performance for ZPA applications that open a high number of sockets.
        *   Zscaler Client Connector now supports restoring the previous system proxy settings upon exiting based on the app profile settings.
        *   Fixes an issue where some ZPA users faced connection errors when using double quotes in a posture profile.
        *   Fixes an issue where Zscaler Client Connector requests to maintain connections resulted in authentication issues in certain situations.
        *   Fixes an issue where Zscaler Client Connector encountered an error condition during application exit.
        *   Fixes an issue where Microsoft applications were not able to operate when loopback exemptions were enabled on systems shared by multiple users.
        *   Fixes an AD interoperability issue in cases where the systems of newly synced users could not reach AD during authentication.
        *   Improves the ZPA re-authentication notifications by periodically prompting the user to re-authenticate.
        *   Fixes an issue where auto update failed on Windows 7 clients running Zscaler Client Connector 1.5.0.337.
        *   Fixes an issue where Zscaler Client Connector displayed incorrect AUP messages.
        *   Fixes an issue where a system crash was observed while Zscaler Client Connector was retrieving interface information.
        *   PCAP file size is now increased to 500 MB from 100 MB.

**November 04, 2020**

*   Release in Limited Availability: Client Connector 3.0.0.303 for Windows

    *   ### **Zscaler Client Connector 3.0.0.303 Enhancements and Fixes**

Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Zscaler Client Connector was consuming more resources than normal, for some exception conditions when using Z-Tunnel 2.0.

**October 07, 2020**

*   Release Available: Client Connector 2.2.1.77 for Windows

    *   ### **Zscaler Client Connector 2.2.1.77 Enhancements and Fixes**

This is a service update for Zscaler Client Connector (formerly Zscaler App or Z App) 2.2.1. Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Zscaler Client Connector was not automatically upgraded to a new version when the network resulted in HTTP fragmentation.
        *   Fixes an issue where the ZPA application traffic based on IP address was not properly routed through the tunnel.

Release Available: Client Connector 2.1.2.113 for Windows

    *   ### **Zscaler Client Connector 2.1.2.113 Enhancements and Fixes**

This is a service update for Zscaler Client Connector (formerly Zscaler App or Z App) 2.1.2. Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Zscaler Client Connector was not automatically upgraded to a new version when the network resulted in HTTP fragmentation.

**September 30, 2020**

*   Release in Limited Availability: Client Connector 3.0 for Windows

    *   ### **Zscaler Client Connector 3.0 Enhancements and Fixes**

Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Z-Tunnel 2.0 failed to recover from an error condition in a certain scenario.
        *   Fixes an issue where the Z-Tunnel 2.0 connection retries failed in certain scenarios.
        *   Includes support for segmented HTTP requests in Tunnel, TWLP, and auto upgrade scenarios. 
        *   Zscaler Client Connector now supports DNS traffic for ZPA domains over TCP.
        *   Zscaler Client Connector now allows you to select the Zscaler cloud that you want to connect to.
        *   Zscaler Client Connector now displays the username either as the logged in user name or as the authenticated user ID based on the Zscaler Client Connector Portal settings.
        *   Zscaler Client Connector now supports DTLS 1.2 protocol.
        *   Zscaler Client Connector enhances secure communications between application components.
        *   Zscaler Client Connector now supports automatic ZPA reauthentication for SSO clients. 
        *   Includes improvements to support dedicated Z-Tunnel 2.0 Public Service Edge hardware.
        *   Fixes an issue where file download from Zscaler Client Connector failed in certain scenarios.
        *   Fixes an issue where JDBC connection failed over ZPA with a socket error on Zscaler Client Connector.
        *   Fixes an issue where Zscaler Client Connector logs displayed incorrect hostnames.
        *   Zscaler Client Connector now uses the cached app profile PAC when an intermittent PAC file download fails.
        *   Fixes an issue where Zscaler Client Connector failed to load after a device reboot in certain scenarios.
        *   Fixes an issue where some connections were failing through Z-Tunnel 1.0 due to segmented HTTP requests.
        *   Fixes an issue where traceroutes failed between Zscaler Client Connector and ZDX in certain scenarios.
        *   Fixes an issue where third-party VPN clients randomly disconnected with Zscaler Client Connector due to simultaneous access to resources.
        *   Fixes an issue where the username field was pre-populated with the user’s email address during ZPA SSO reauthentication in Zscaler Client Connector.
        *   Fixes an issue with file transfers through ZPA when using double encryption for the FTP application.
        *   Zscaler Client Connector no longer performs DNS resolution for mobilesupport.zscaler.com when support access is disabled
        *   Fixes an issue where the users were unable to start the packet capture option from Zscaler Client Connector when ZDX service was enabled.
        *   Fixes an issue where Zscaler Client Connector stopped forwarding traffic when the device was in sleep mode.
        *   Fixes an issue where Zscaler Client Connector did not reflect the updated group ID information.
        *   Fixes an issue where remote uninstallation of Zscaler Client Connector was incomplete.
        *   Improves the interoperability with VPN clients when the 100.64.x.x IP subnet is overlapping between VPN and Zscaler Client Connector.
        *   Zscaler Client connector now includes the end user email address with the Report an Issue option.
        *   Zscaler Client Connector now binds to the loopback address to serve PAC files and to intercept the loopback traffic in local proxy mode. 
        *   Includes updates to third party libraries and components to enhance security.
        *   Improves the ZPA re-authentication flow after the user enters credentials in the IDP page. 
        *   Zscaler Client Connector now includes dump files when the users export logs for troubleshooting purposes.
        *   Zscaler Client Connector improves the connection status change notifications when using the accessibility feature.
        *   Zscaler Client Connector now supports ZPA applications that are defined using a wildcard (e.g., *). To learn more, see [Configuring Application Segments](https://help.zscaler.com/zpa/configuring-application-segments).

This configuration method requires approval from Zscaler prior to implementation.

**September 18, 2020**

*   Release in Limited Availability: Client Connector 2.2.1.63 for Windows

    *   ### **Zscaler Client Connector 2.2.1.63 Enhancements and Fixes**

Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue during a fresh install where the wizard showed incorrect installer licensing information.

**September 11, 2020**

*   Release in Limited Availability: Client Connector 2.2.1.55 for Windows

    *   ### **Zscaler Client Connector 2.2.1.55 Enhancements and Fixes**

Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Zscaler Client Connector login failed in certain scenarios with AD authentication.
        *   Fixes an issue where ADFS authentication failed on Zscaler Client Connector.

**September 10, 2020**

*   Release Available: Client Connector 2.1.2.112 for Windows

    *   ### **Zscaler Client Connector 2.1.2.112 Enhancements and Fixes**

This is a service update for Zscaler Client Connector (formerly Zscaler App or Z App) 2.1.2. Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Zscaler Client Connector login failed in certain scenarios with AD authentication.

**August 21, 2020**

*   Release in Limited Availability: Client Connector 2.2.1 for Windows

    *   ### **Zscaler Client Connector 2.2.1 Enhancements and Fixes**

Contact the Zscaler Account team to enable this release for your organization.

        *   Zscaler Client Connector now includes support for the Zscaler Digital Experience (ZDX) service. This module will not be installed unless it is enabled, and specific destination domains must be in your SSL bypass list. To learn more, contact the Zscaler Account team.
        *   Zscaler App (Z App) was renamed to Zscaler Client Connector within the app.
        *   Fixes an issue with the driver installation on Zscaler Client Connector while using the Repair App option.
        *   Fixes an issue where installing Zscaler Client Connector triggers too many processes on users' devices.
        *   Fixes an issue where the Process Check device posture failed when the process was launched using command line arguments.
        *   Zscaler Client Connector is now upgraded to the NPCAP version 0.9995 and Install builder version 20.4.
        *   Fixes an issue where a script error popped up while logging in to the Zscaler Client Connector.
        *   Fixes an issue where Zscaler Client Connector did not clear the DNS suffix list when ZPA was disabled.
        *   Fixes an issue where domain validation for service record lookup failed in Zscaler Client Connector for ZPA users.
        *   Fixes an issue where Zscaler Client Connector failed to load after a device restart.
        *   Fixes an issue where the Zscaler Client Connector re-authentication alert was not displayed on the system tray.
        *   Fixes an issue where the users randomly lost connectivity while switching networks.
        *   Zscaler Client Connector now includes additional validation for remote procedure calls (RPC).
        *   ZDX module of the Zscaler Client Connector now provides detailed information for all the traceroute hops.

**August 03, 2020**

*   Release in Limited Availability: Client Connector 2.1.3.25 for Windows

    *   ### **Zscaler Client Connector 2.1.3.25 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector failed to install the SSL certificate in some scenarios.
        *   Fixes an issue where Zscaler Client Connector could not establish connections on operating systems using a locale other than English.

**July 07, 2020**

*   Release in Limited Availability: Client Connector 2.1.3 for Windows

    *   ### **Zscaler Client Connector 2.1.3 Enhancements and Fixes**

        *   Zscaler Client Connector (formerly Zscaler App or Z App) now includes additional validation for remote procedure calls (RPC) and enhancements for server-based integrity checks.
        *   Fixes an issue with the ZIA service where an internal application didn’t load on the first request.
        *   Fixes an issue where an error occurred with the ZSATunnel process when using packet capture.
        *   Fixes an issue where after canceling a machine reboot, Zscaler Client Connector sent traffic directly for approximately one minute.
        *   Fixes an issue where the ZPA service was disabled, but Zscaler Client Connector displayed a reauthentication warning for ZPA.
        *   Fixes an issue where, in some scenarios, the ZIA service was stuck in a connecting state after installing Zscaler Client Connector.

**June 16, 2020**

*   Release Available: Client Connector 2.1.2.105 for Windows

    *   ### **Zscaler Client Connector 2.1.2.105 Enhancements and Fixes**

This is a service update for Zscaler Client Connector (formerly Zscaler App or Z App) 2.1.2. Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an unquoted service path vulnerability. (Zscaler acknowledges Ryan Schachtschneider and the Lockheed Martin Red Team for independently disclosing this vulnerability.) (CVE-2020-11632)
        *   Fixes a DLL hijacking vulnerability due to the configuration of OpenSSL. (Zscaler acknowledges Xavier Danest for responsibly disclosing this vulnerability.) (CVE-2020-11634)
        *   Fixes an issue where, when using a VPN, domain bypasses didn’t work after switching to Z-Tunnel 2.0.
        *   Fixes an issue where sometimes Zscaler Client Connector disconnected when using VPN gateway bypasses.
        *   Upgrades the OpenSSL version that Zscaler Client Connector uses.
        *   Fixes an OpenSSL configuration issue with DLL loading.
        *   Zscaler Client Connector is now compiled with additional binary protection flags.
        *   Fixes an issue that could cause Zscaler Client Connector to load an incorrect DLL.
        *   Fixes an issue by including additional validation for registry key values.

**May 22, 2020**

*   Release Available: Client Connector 2.1.2.92 for Windows

    *   ### **Zscaler Client Connector 2.1.2.92 Enhancements and Fixes**

This is a service update for Zscaler Client Connector (formerly Zscaler App or Z App) 2.1.2. Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Zscaler Client Connector displayed the Logout Password prompt instead of the Disable Password prompt for users exiting Zscaler Client Connector.
        *   Fixes an issue where users experienced an internal Zscaler Client Connector error with Zscaler Internet Security when using Zoom.
        *   Fixes an issue where, in a certain scenario, Zscaler Client Connector was configured to use Z-Tunnel 2.0 for DTLS but switched to Z-Tunnel 1.0 instead.
        *   Improves upload and download speeds for Z-Tunnel 2.0 in different bandwidth networks for both TLS and DTLS.
        *   Fixes an issue where users experienced a firewall error with Zscaler Client Connector in Tunnel mode when using Check Point VPN. 
        *   Improves UDP application performance for the ZPA service.

**April 29, 2020**

*   Release Available: Client Connector 2.1.2.84 for Windows

    *   ### **Zscaler Client Connector 2.1.2.84 Enhancements and Fixes**

This is a service update for Zscaler Client Connector (formerly Zscaler App or Z App) 2.1.2. Contact the Zscaler Account team to enable this release for your organization.

        *   Fixes an issue where Zscaler Client Connector accidentally intercepted and tunneled sessions established from a server to a client application.
        *   Improves network performance for using the ZPA service with the packet filter driver and a high number of defined IP-based applications segments with ports and protocols.
        *   Fixes an issue with using keyboard navigation on the Private Access window within the Zscaler Client Connector user interface. You can now access all the UI elements for Zscaler Client Connector with the Tab key. 

**April 18, 2020**

*   Release Available: Client Connector 2.1.2.81 for Windows

    *   ### **Zscaler Client Connector 2.1.2.81 Enhancements and Fixes**

        *   Fixes a potential buffer overflow vulnerability when connecting to misconfigured TLS servers. (Zscaler acknowledges the Siemens Red Team for responsibly disclosing this vulnerability.) (CVE-2020-11633)
        *   Adds resilience for when a user's device switches from Enforced Proxy mode to Z-Tunnel 2.0 mode.
        *   Fixes an issue where, in some scenarios, Zscaler Client Connector did not download an updated forwarding profile PAC file after a network change.
        *   Fixes an issue where a matching subdomain did not pass the Domain Joined device posture profile.
        *   Fixes an issue where sometimes the Client Certificate device posture profile did not work.
        *   Fixes an issue where, in some scenarios, Zscaler Client Connector could not handle TLS hostname identification.

**March 26, 2020**

*   Release in Limited Availability: Client Connector 2.1.2 for Windows

    *   ### **Zscaler Client Connector 2.1.2 Enhancements and Fixes**

        *   Zscaler Client Connector exported logs are no longer encrypted. However, logs included in the Report an Issue form are still encrypted.
        *   Exported logs now include crash dumps and pcaps files.
        *   Includes improvements for Z-Tunnel 2.0 gateway selection and DTLS to TLS fallback.
        *   Adds support for ZPA Private Service Edges.
        *   Zscaler Client Connector now displays service maintenance notifications during upgrades.
        *   Adds support for Zscaler Client Connector to tunnel internal traffic through Zscaler. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles).
        *   Adds support for the Process Check, Carbon Black, Crowdstrike, and SentinelOne options for device posture profiles. To learn more, see [Configuring Device Posture Profiles for ZPA](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles-zpa).
        *   Adds support for Zscaler Client Connector to automatically lower MTU based on the forwarding profile configuration to prevent IP fragmentation. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app).
        *   For the Windows version of Zscaler Client Connector, adds support for accessibility features, including screen readers (e.g., Narrator), keyboard navigators, application and text size changes, and high contrast themes.
        *   Fixes an issue where Zscaler Client Connector could launch applications in the system context. (CVSS v3.1 Base Score: 8.2 - High)
        *   Fixes an issue where Zscaler Client Connector in Tunnel with Local Proxy mode did not change the URI to a relative path.
        *   Fixes an issue where Z-Tunnel 2.0 did not work when using Windows Hello on Kerberos.
        *   Fixes an issue where Zscaler Client Connector switched between gateway.cloudname.net and the default PROXY statement when the PAC file was invalid or contained syntax errors.
        *   Fixes an issue where Zscaler Client Connector did not give users, who passed the device posture profile check for certificates, access to ZPA applications.
        *   Fixes an issue where, in some cases, Zscaler Client Connector sent ZIA traffic or ZPA traffic direct instead of tunneling the traffic.
        *   Fixes an issue where the ZPA service did not resolve DNS for SRV records.
        *   Fixes an issue for the macOS version of Zscaler Client Connector where Zscaler Client Connector did not uninstall when a shell script was used.
        *   Fixes an issue for the macOS version of Zscaler Client Connector where Zscaler Client Connector still opened after installing the app in silent mode.

**February 14, 2020**

*   Release Available: Client Connector 2.1 for Windows

    *   ### **Zscaler Client Connector 2.1 Enhancements and Fixes**

        *   Now displays the name of the broker that Zscaler Client Connector (Z App) is connected to in the Private Access window.
        *   Allows you to configure multiple trusted networks for use with ZPA applications. To learn more, see [About Trusted Networks](https://help.zscaler.com/zscaler-client-connector/about-trusted-networks).
        *   Allows you to configure Zscaler Client Connector to automatically populate the username field during IdP authentication. To learn more, see [Configure Automatic Username Population for IdP Authentication](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-username-population-idp-authentication).
        *   The Disable Loopback Restriction option now works independently of the forwarding profile action. Before this release, this setting only worked with Tunnel with Local Proxy mode.
        *   Fixes an issue where Zscaler Client Connector couldn’t detect the captive portal due to some airlines' Wi-Fi settings. 
        *   Fixes an issue where, in some cases, Zscaler Client Connector was stuck in a connecting state when using Z-Tunnel 2.0.
        *   Fixes an issue where sometimes Zscaler Client Connector didn't resolve internal DNS domains when ZPA was in a fail-open state.
        *   Fixes an issue where Zscaler Client Connector didn’t immediately enforce a new PAC file after the device reboots.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-deception-group-users -->

Title: Enabling Deception for a Group of Users | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-deception-group-users

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling Deception for a Group of Users

You can use Zscaler Service Entitlement to select which users can enroll into Zscaler Deception. To enable Deception for only a select group of users, you must deploy Zscaler Client Connector for Windows 3.9 or later.

## Enabling Deception for User Groups

To enable Deception for a group of users:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left menu, select **Zscaler Service Entitlement**.
3.   Click the **Zscaler Deception (Deception)** tab.
4.   To enable Deception for only a group of users, ensure that **Zscaler Deception Enabled by Default**is disabled. If this setting is enabled, Deception is available for all users and you cannot assign Deception to a group.
5.   Select a group of users from the **User Groups** drop-down menu and click **Done**. The default setting is **None**. This option means no groups have access to Deception and allows users to keep their current settings.

Groups are defined in the ZIA Admin Portal. If you do not see your groups, ensure that groups were synced to the Zscaler Client Connector Portal. To learn more, see [Syncing Directory Groups between the ZIA Admin Portal and App Portal](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-zia-admin-portal-and-zscaler-client-connector-portal).

1.   Select **Logout Zscaler Client Connector when Zscaler Deception Entitlement is Enabled**to automatically log users out of Zscaler Client Connector when Deception is enabled for a device group. Users can then log in again to enable the Deception service. This applies to customers using ZPA only or ZPA and Zscaler Deception. When disabled, Zscaler Client Connector runs without the ZDX service until the next Zscaler Client Connector login.

![Image 2: Zscaler Deception tab in Zscaler Service Entitlement ](https://help.zscaler.com/downloads/z-app/policy-administration-settings/zscaler-service-entitlement/selective-entitlement-enabling-deception-group-users/Deception_usergrps_0.png)

1.   Click **Save**.

Your users' devices are updated the next time they connect. If users are already connected, devices automatically update in 60 minutes. To manually update devices, users can go into Zscaler Client Connector and click **Update Policy** from the **More**window. After manually refreshing, users must reauthenticate in the Zscaler Deception Service Status window.

## Possible Configurations for Deception

The following table provides possible configurations for the Zscaler Service Entitlement feature and the resulting behavior of the Zscaler Deception service:

**Zscaler Deception Enabled by Default****Groups Specified****Behavior**
Enabled N/A Deception service is enabled for all users
Disabled No Deception service disabled for users
Disabled Yes Deception service is enabled only for the specified group of users

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range -->

Title: Configuring the Zscaler Client Connector Synthetic IP Range | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring the Zscaler Client Connector Synthetic IP Range

You can configure your organization to use a Zscaler Client Connector IP range beyond the default range of 100.64.0.0/16 for your Private Access applications.

To configure a synthetic IP range:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Endpoint Configuration**.
2.   On the **Endpoint Integration**tab, enter a range between `100.64.0.0/16`–`100.127.0.0./16` in the**Zscaler Client Connector Synthetic IP Range** field.
3.   If your LAN network operates on the same synthetic IP range that Zscaler Client Connector uses for Private Access, enable **Drop Non-Zscaler Packets in Synthetic IP Range** to have Zscaler Client Connector block non-Zscaler packets destined for the synthetic IP range.

This option is disabled by default. When disabled, non-Zscaler packets are bypassed.

![Image 2: Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/downloads/zclient-connector-endpoint-integration-synthetic-ip-range_0.png)

4.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector -->

Title: Configuring Notification Templates for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring Notification Templates for Zscaler Client Connector

You can configure notification templates for various settings for user notifications in Zscaler Client Connector and assign a template to specific groups of users in the app profile. Some of these settings are enabled after a user’s device is enrolled in the Zscaler service and can be changed by a user in Zscaler Client Connector.

This feature is available only for Zscaler Client Connector version 4.8 and later for Windows and you must have the [Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/using-zscaler-notification-framework) enabled. When you enable this feature, the [End User Notifications](https://help.zscaler.com/zscaler-client-connector/configuring-end-user-notifications-zscaler-client-connector) tab no longer displays and the existing settings from that tab are copied to a Legacy Notification Settings template. Contact Zscaler Support to enable this feature.

To configure a notification template:

1.   Go to **Policies**>**Common Configuration**>**Resources**>**Client Connector Notifications**.
2.   On the **Notification Template** tab, click **Add** to create a new template or click **Edit** beside an existing template.

![Image 2: Notification Template tab](https://help.zscaler.com/downloads/zscaler-client-connector/administration/zscaler-client-connector-notifications/configuring-notification-templates-zscaler-client-connector/zclient-connector-notification-template-tab_0.png)

3.   On the Notification Template window, configure the following settings:
    *   **Name**: Enter a name for the template. You cannot change the default Legacy Notification Settings template name.
    *   **Default**: If you are configuring a template that is not the Legacy Notification Settings template, select whether this notification template is the default template for new app profiles.
    *   **Show Zscaler Client Connector Notification Popups by Default**: Select this option to have users receive [pop-up notifications](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-pop-notifications) from Zscaler Client Connector. This setting is enabled after a user is enrolled. Users can turn this option off from Zscaler Client Connector in the [More window](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector) in the app.
    *   **Enable App Updates Notifications**: Select this option to have users receive app upgrade notifications.
    *   **Enable Service Status Notifications**: Select this option to have users receive status notifications for Zscaler services, such as when a service is in Disaster Relief (DR) mode.
    *   **Enable ZIA Notifications**: Select this option to have users receive notifications from Internet & SaaS (ZIA), such as data loss prevention (DLP) notifications. To learn more, see [About Zscaler Client Connector-Based End User Notifications](https://help.zscaler.com/zia/about-zscaler-client-connector-based-end-user-notifications).
    *   **Enable Notifications for ZPA reauthentication**: Select this option to have users prompted for authentication. This option is enabled after a user is enrolled. Users can turn this option off from Zscaler Client Connector in the [More window](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector) in the app.
    *   **Show ZPA Reauthentication Notifications Every (In Minutes)**: Select this option to show Private Access (ZPA) reauthentication notifications at a specific time interval. This setting is enabled by default. Enter a value from `2` to `1440` to set the interval in minutes.
    *   **Custom Timer (In Seconds)**: Set the amount of time that the notification displays for the user. Enter a time between `5` and `60` seconds.
    *   **Enable Persistent Notifications**: When enabled, overrides the Custom Timer. This setting displays critical notifications until the user dismisses them. Critical notifications include Private Access reauthentication, captive portal detection, and request for a system reboot.
    *   **ZIA Notification Persistent**: When enabled, overrides the Custom Timer and makes [Endpoint Data Loss Prevention (DLP) notifications](https://help.zscaler.com/zia/configuring-euns-endpoint-dlp) persistent.
    *   **Delay Posture Failure Notifications after Tunnel Establishment (In Seconds)**: When enabled, Zscaler Client Connector delays pop-up notifications for device posture failure for the number of entered seconds after a system reboot. The default value is `0`. You can enter a value up to `300`.

This option helps prevent user confusion and unnecessary resolution attempts. For example, after a device restart, Zscaler Client Connector evaluates the [Detect CrowdStrike](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-CrowdStrike) device posture but because CrowdStrike is still starting, the device posture check fails temporarily and Private Access blocks access based on the access policy. You can use this option to delay the notification and allow time for the device posture to pass.

This option delays only the pop-up notification (not the message that displays in the Notifications window in the app). Zscaler Client Connector automatically suppresses duplicate messages that display within a two-minute interval, so subsequent posture failure notifications won’t display for an additional 120 seconds.

For example, if this option is set to 60 seconds and a posture failure notification is received within 15 seconds of a reboot, the pop-up notification is suppressed. If an additional failure notification is received 90 seconds after reboot, the pop-up notification is still suppressed because it is within two minutes of the first notification. 
    *   **Do not disturb**: When enabled, Zscaler Client Connector pop-up notifications include a **Do not Disturb** button. The default value is enabled, but you can clear this option to prevent the button from displaying.
    *   **ZIA Firewall**: Select this option to show firewall notifications from Internet & SaaS in the Notifications window in the app. If enabled, you can select **Firewall Popup Notifications** to also display them as pop-up notifications. To learn more, see [Configuring EUNs for Firewall Filtering](https://help.zscaler.com/zia/configuring-euns-firewall-filtering).
    *   **DNS**: Select this option to show DNS notifications from Internet & SaaS in the Notifications window in the app. If enabled, you can select **DNS Popup Notifications** to also display them as pop-up notifications. To learn more, see [Configuring EUNs for DNS Control](https://help.zscaler.com/zia/configuring-euns-dns-control).
    *   **IPS**: Select this option to show IPS notifications from Internet & SaaS in the Notifications window in the app. If enabled, you can select **IPS Popup Notifications** to also display them as pop-up notifications. To learn more, see [Configuring EUNs for IPS Control](https://help.zscaler.com/zia/configuring-euns-ips-control).

You must also select**Enable ZIA Notifications** to display the ZIA Firewall, DNS, and IPS notifications for users.

After you create a template, you can assign it to an [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#general) to apply the notification settings to a group of users.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-idp -->

Title: About Zscaler Client Connector IdP | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-idp

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
# About Zscaler Client Connector IdP | Zscaler

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
*    About Zscaler Client Connector IdP 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### About Zscaler Client Connector IdP

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

The Zscaler Admin Console can function as an identity provider (IdP) for the Zscaler service. With this feature, users do not need to be tied to your organization’s standard identity provider (IdP) in order to authenticate to the Zscaler service. Instead, if your organization uses SAML-based single sign-on (SSO), the app can use a device token to auto-provision and silently authenticate users and devices for the Zscaler service.

To learn more, see [Using Zscaler Admin Console as an Identity Provider (IdP)](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider).

# About the Client Connector IdP Page

On the Client Connector IdP page (Infrastructure > Connectors > Client > Client Connector IdP), you can do the following:

1.   [Create a new device token](https://help.zscaler.com/zscaler-client-connector/creating-device-token).
2.   View a list of created device tokens.
3.   [Delete a device token](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-app-portal).

![Image 6: Client Connector IdP Page](https://help.zscaler.com/downloads/zscaler-client-connector/platform-and-authentication-management/about-zscaler-app-idp/zclient-connector-client-connector-idp-device-token.png)

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

<!-- source: https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector -->

Title: Using Fiddler with Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Using Fiddler with Zscaler Client Connector

To use the Telerik Fiddler application with Zscaler Client Connector, you must configure Zscaler Client Connector to use a PAC file to point to a specific port. Then, you must ensure that Fiddler is listening to that port.

At a high level, the flow of traffic should be: Browser > Fiddler > Zscaler Client Connector > Destination Page.

To learn more, see [Best Practices for Using PAC Files with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-app).

## Configuring Zscaler Client Connector and Fiddler

For more information on Fiddler, refer to the [Telerik documentation](https://www.telerik.com/support). The version used for the following steps is Fiddler 5.0.20182.28034.

To use Fiddler with Zscaler Client Connector:

*   [1. Create a custom PAC file.](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-1)[](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector)To learn more about creating a custom PAC file, see [Writing a PAC File](https://help.zscaler.com/zia/writing-pac-file).

Create a custom PAC file with the following return statement:

return "PROXY 127.0.0.1:<Port for Fiddler>; PROXY ${ZAPP_LOCAL_PROXY};";
You must add the port to the return statement. For example, if you choose port 8888, then the return statement is as follows:

return "PROXY 127.0.0.1:8888; PROXY ${ZAPP_LOCAL_PROXY};";
You can host the PAC file in the Zscaler Admin Console. To learn more, see [Using Custom PAC Files to Forward Traffic to ZIA](https://help.zscaler.com/zia/how-do-i-use-custom-pac-file-forward-traffic-zia#AddCustomPAC).

If you want to debug on a local web server, the browser’s PAC file may ignore the request for 127.0.0.1. As a workaround, create a host file entry pointing to 127.0.0.1 (e.g., 127.0.0.1 server.local).

[Close](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-1) 
*   [2. Add the PAC file to a forwarding profile.](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-2)

This step is not required if you are using Fiddler to automatically configure the system proxy, as Fiddler manages the system proxy settings.

[](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector)[Create a new forwarding profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app) or update an existing one, and then add the custom PAC file.

You must configure the forwarding profile with the following settings:

    *   Select **Tunnel with Local Proxy**or **Tunnel** mode for any application network (i.e., On-Trusted Network, VPN-Trusted Network, Off-Trusted Network).

If you have configured Fiddler to automatically populate system proxy settings, you must use **Tunnel** mode and not **Tunnel With Local Proxy** mode. This is because the Zscaler Client Connector enforces its own system proxy in **Tunnel With Local Proxy** mode.

    *   For the **Use Automatic Configuration Script** field, select the checkbox and enter the custom PAC URL.

[Close](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-2)

*   [3. Add the forwarding profile to an app profile.](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-3)

[](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector)[Create a Zscaler Client Connector app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles) or update an existing one, and then add the configured forwarding profile.

The forwarding profile in this example is Fiddler.

You must configure the app profile with the following settings:

    *   The **Rule Order** must be **1**.
    *   The app profile must be enabled.

![Image 2: Enter the rule order and enable the rule](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/using-fiddler-zscaler-client-connector/zclient-connector-fiddler-app-profile-1.png)

    *   The **Disabled Loopback Restriction** switch must be enabled.

![Image 3: Select the Disable Loopback Restriction option](https://help.zscaler.com/downloads/zscaler-client-connector/interoperability/using-fiddler-zscaler-client-connector/zclient-connector-fiddler-app-profile-2.png)

[Close](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-3)

*   [4. Configure the proxy and port for Fiddler.](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-4)

[](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector)To configure Fiddler:

    1.   Open the Fiddler application.
    2.   Go to **Tools**>**Options…**

![Image 4: Navigating to the Fiddler options menu to configure settings for Zscaler Client Connector](https://help.zscaler.com/downloads/z-app/knowledge-base/using-fiddler-zscaler-app/fiddler-2.png)

    1.   In the **Options** window, click the **Connections** tab and do the following:
        *   **Fiddler listens on port**: Enter the port that you configured in the forwarding profile PAC file.

The port in this example is 8888.

![Image 5: Configuring Fiddler to listen to the port from the Zscaler Client Connector forwarding profile PAC file](https://help.zscaler.com/downloads/z-app/knowledge-base/using-fiddler-zscaler-app/fiddler-3.png)

    1.   Click the**Gateway** tab and do the following:
        *   **Manual Proxy Configuration**: Select this option. In the first field, enter the following proxy string.

http=127.0.0.1:<Zscaler Client Connector Port>;https=127.0.0.1:<Zscaler Client Connector Port>;ftp=127.0.0.1:<Zscaler Client Connector Port>;
You must add [the port that Zscaler Client Connector listens on](https://help.zscaler.com/zscaler-client-connector/configuring-port-zscaler-app-listen) to the proxy string. For example, if Zscaler Client Connector listens on port 9000, then the proxy string is the following:

http=127.0.0.1:9000;https=127.0.0.1:9000;ftp=127.0.0.1:9000;
Optionally, to debug on a local web server, enter Bypass list: <local>; in the second **Manual Proxy Configuration** field.

![Image 6: Configuring Fiddler manual proxy configuration settings with the port Zscaler Client Connector listens on](https://help.zscaler.com/downloads/z-app/knowledge-base/using-fiddler-zscaler-app/fiddler-4.png)

If you are using the Fiddler AutoResponder feature, the **Accept all CONNECTs** option must be disabled.

    1.   Click **OK**.

[Close](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector#Step-4)

After you finish the configuration for both Zscaler Client Connector and Fiddler:

1.   Enroll in Zscaler Client Connector if you have not already.
2.   When**Internet Security** for Zscaler Client Connector is **ON**, you can open and use Fiddler.

Ignore any errors that Fiddler displays during startup.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication -->

Title: Configuring Automatic ZPA Reauthentication | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Configuring Automatic ZPA Reauthentication

You can enable Zscaler Client Connector to automatically attempt reauthentication for users with Private Access (ZPA). This article describes how to configure automatic Private Access reauthentication.

This feature is only available for Zscaler Client Connector version 3.0 and later for Windows and macOS.

Prior to configuring automatic Private Access reauthentication, you must:

1.   [Configure your IdP for single sign-on (SSO)](https://help.zscaler.com/zpa/documentation-knowledgebase/single-sign/idp-configuration-guides).
2.   Enable Integrated Windows Authentication (IWA).

## Enabling IWA

After you [configure your organization's IdP](https://help.zscaler.com/zpa/documentation-knowledgebase/single-sign/idp-configuration-guides), you can enable IWA on the following browsers. Select a browser to learn more.

While IWA works with most browsers, it does not work over some HTTP proxy servers.

*   [Mozilla Firefox](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Mozilla)

[](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication)Enabling IWA on Mozilla Firefox depends on your OS.

To enable IWA on Mozilla Firefox for Windows:

    1.   Start `about:config`:
        1.   In the URL field, enter `about:config` and press `Enter`.
        2.   Click **Accept the Risk and Continue**.

    2.   Configure `network.negotiate-auth.trusted-uris`:
        1.   In the **Search preference name**field, enter `negotiate`.
        2.   Click the **Edit**icon for `network.negotiate-auth.trusted-uris`.
        3.   Add the SSO domain.
        4.   Press `Enter` or click the check mark.

    1.   Configure `network.automatic-ntlm-auth.trusted-uris`:
        1.   In the **Search preference name** field, enter `automatic`.
        2.   Click the **Edit**icon for `network.automatic-ntlm-auth.trusted-uris`.
        3.   Add the SSO domain.
        4.   Press `Enter` or click the check mark.

To enable IWA on Mozilla Firefox for macOS:

    1.   Start `about:config`:
        1.   In the URL field, enter `about:config` and press `Enter`.
        2.   Click **Accept the Risk and Continue**.

    2.   Configure `network.negotiate-auth.delegation-uris`:
        1.   In the **Search preference name** field, enter `negotiate`.
        2.   Click the **Edit**icon for `network.negotiate-auth.delegation-uris`.
        3.   Add the SSO domain.
        4.   Press `Enter` or click the check mark.

    1.   Configure `network.automatic-ntlm-auth.trusted-uris`:
        1.   In the **Search preference name** field, enter `automatic`.
        2.   Click the **Edit**icon for `network.automatic-ntlm-auth.trusted-uris`.
        3.   Add the SSO domain.
        4.   Press `Enter` or click the check mark.

    1.   Configure `network.automatic-ntlm-auth.allow-proxies`:
        1.   In the **Search preference name**field, enter `automatic`.
        2.   Click the gray toggle for `network.automatic-ntlm-auth.allow-proxies` to set this value to **true**.

    1.   Configure `network.negotiate-auth.allow-proxies`
        1.   In the **Search preference name** field, enter `negotiate`.
        2.   Click the gray toggle for `network.negotiate-auth.allow-proxies` to set this value to **true**.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Mozilla)

*   [Google Chrome version 8.0 and later](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Google)

[](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication)For Windows and macOS, IWA is automatically enabled on Google Chrome and this function is allowlist-driven. The only way to change the policy is through the command prompt (Windows) or terminal window (macOS).

To change the policy in Windows:

    1.   Enter `cmd` into the search field on your taskbar to start the command prompt.
    2.   Configure the allowlist using the following command-line parameter:

`--auth-server-whitelist="https://www.example.com"`.

Use a comma to separate multiple domains.

To change the policy in macOS:

    1.   Start the terminal application.
    2.   Create a Kerberos ticket for the account using the following command: `kinit username@example.com`.
    3.   Replace `username@example.com` with your username and domain. When prompted, enter your password.
    4.   Configure the allowlist using the following command-line parameter: 

`$ defaults write com.google.Chrome AuthServerWhitelist "httpsL//www.example.com, https://www.example2.net, https://www.example3.org"`.

Use a comma to separate multiple domains.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Google)

*   [Safari, after you obtain a Kerberos ticket](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Safari)[](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication)For macOS devices running OS X, IWA is enabled automatically for Safari.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Safari) 
*   [Microsoft Edge version 77 and later](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Microsoft)

[](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication)To enable IWA on Microsoft Edge:

    1.   In the Windows Control Panel, select **Network and Internet**>**Internet Options**.

    2.   Click the **Security**tab and click **Local Intranet > Sites**.

    3.   Click **Advanced**.

    4.   In the **Add this website to the zone**field, enter the SSO domain.
    5.   Click **Add**.
    6.   Click **Close**.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-zpa-reauthentication#Microsoft)

Admins use their organization's preferred method to enable IWA for all users. For example, an admin might use Microsoft Group Policy Object (GPO) to enable IWA for all their users. To learn more, see [Kerberos Trust Relationship Configuration Guide for Windows Server 2012 & GPO Push](https://help.zscaler.com/zia/kerberos-trust-relationship-configuration-guide-windows-server-2012-gpo-push).

## Configuring Automatic Private Access Reauthentication

Zscaler Client Connector enforces system proxy settings, and all applications on the device must comply with these settings. Administrators review any custom proxy settings within applications, then restrict these settings.

After you've configured your IdP for SSO and enabled IWA, you can configure automatic Private Access reauthentication:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**App Supportability**.
2.   On the **App Supportability** tab:
    1.   Select **Automatically Attempt ZPA Reauthentication**to allow users to continue to access Private Access. When enabled, Zscaler Client Connector attempts to log in silently when users must reauthenticate to Private Access. Successful attempts allow users to continue accessing Private Access. If unsuccessful, users must reauthenticate manually.

If you use Zscaler Client Connector version 4.8 and later for Windows, you can enable this feature per app profile and by network type. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zpa-reauth).

    2.   From the **Timeout for Automatic ZPA Reauthentication (in seconds)**drop-down menu, select the time it takes for the browser to automatically reauthenticate. The default is 30 seconds. You can also select 60, 90, or 120 seconds.

3.   Click **Save**.

If automatic reauthentication is unsuccessful, users are prompted to reauthenticate with their credentials using Zscaler Client Connector.

![Image 2: Automatic Reauthentication configuration in the Zscaler Admin Console](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-automatic-zpa-reauthentication-doc-62854/Automatically_Attempt_ZPA_Reauth.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-device-groups -->

Title: About Device Groups | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-device-groups

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Device Groups

[Watch a video about Device Groups.](https://fast.wistia.net/embed/iframe/rwfvkb1k6o)

Configuring Zscaler services for device groups allows you to assign entitlements and configure policy settings based on device posture profiles. For example, one user can have two devices, one personal and one employer-provided. The personal device can be enrolled in one service, such as Zscaler Private Access (ZPA), and the employer-provided device can be enrolled in ZPA and Zscaler Digital Experience (ZDX). To learn more, see [Creating Device Groups](https://help.zscaler.com/zscaler-client-connector/add-device-groups-zscaler-private-access-zpa).

Device Groups provide the following benefits and allow you to:

*   Assign a user with multiple devices to different entitlements and forwarding policies.
*   Assign multiple device posture types to a device group by building expressions.
*   Quickly locate a device group using the Search feature.
*   Easily view, edit, or delete a device group.

On the Device Groups page, you can do the following:

1.   [Add a Device Group](https://help.zscaler.com/zscaler-client-connector/add-device-groups-zscaler-private-access-zpa).
2.   [Search for a Device Group](https://help.zscaler.com/zscaler-client-connector/searching-device-groups).
3.   View a list of configured device groups.
4.   [Edit a device group](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-client-connector-portal).
5.   [Delete a device group](https://help.zscaler.com/zscaler-client-connector/editing-or-deleting-items-zscaler-client-connector-portal).

![Image 2: Assign different policy settings based on device posture profiles](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/about-device-groups/Device_Groups_0.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/allowing-users-override-z-tunnel-2-0-or-private-access-protocol-settings -->

Title: Allowing Users to Override Z-Tunnel 2.0 or Private Access Protocol Settings | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/allowing-users-override-z-tunnel-2-0-or-private-access-protocol-settings

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Allowing Users to Override Z-Tunnel 2.0 or Private Access Protocol Settings

The Allow end user to override Z-Tunnel 2.0 or ZPA protocol settings option is disabled by default, to prevent end users from changing the default parameters for Zscaler Tunnel (Z-Tunnel) 2.0 or Private Access (ZPA) protocol settings. You can enable this option to allow end users to modify these settings.

Override settings are applied per session and continue until the next keepalive is sent, or the user logs out and then logs back in again.

Overriding Z-Tunnel 2.0 or Private Access protocol settings allows you to configure additional settings by browsing to a local configuration website. To override Z-Tunnel 2.0 or Private Access protocol settings:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**User Privacy**.
2.   Enable **Allow end user to override Z-Tunnel 2.0 or ZPA protocol settings**.

[See image.](https://help.zscaler.com/zscaler-client-connector/allowing-users-override-z-tunnel-2-0-or-private-access-protocol-settings#console-toggle)

1.   In your browser, enter `http://localhost:9000/zconfig` and browse to the website.
2.   On the website, in the **Zscaler User Configuration** window, configure the following settings:
    *   **PAC**: Enter a link.
    *   **PROTOCOL**: Select **Default**, **DTLS**, or **TLS**.
    *   **MTU**: Select **1200**, **1300**, **1350**, or **1400** for the Maximum Transmission Unit (MTU) value. The default value is 1500 bytes.
    *   **DEBUG**: Select **Enabled** or **Disabled**. This setting determines the status of the log mode in Zscaler Client Connector.
    *   **LONG RUNNING PACKET CAPTURE**: Select **Enabled** or **Disabled**. The default running time is 5 minutes.
    *   **IGNORE KEEPALIVE FAILURE**: Select **Enabled** or **Disabled**.

[See image.](https://help.zscaler.com/zscaler-client-connector/allowing-users-override-z-tunnel-2-0-or-private-access-protocol-settings#overridingtunnelzpa)

1.   Click **Save**.

