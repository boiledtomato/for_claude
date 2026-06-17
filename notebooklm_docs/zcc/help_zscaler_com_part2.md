# ZCC — help.zscaler.com (続き 2)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability -->

Title: Best Practices for Zscaler Client Connector and VPN Client Interoperability | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
# Best Practices for Zscaler Client Connector and VPN Client Interoperability | Zscaler

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
*   [Interoperability](https://help.zscaler.com/zscaler-client-connector/interoperability)
*    Best Practices for Zscaler Client Connector and VPN Client Interoperability 

![Image 2: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 3: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Best Practices for Zscaler Client Connector and VPN Client Interoperability

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

If your users are running Zscaler Client Connector in conjunction with a corporate VPN client, follow these best practices:

*   [Step 1: Select Forwarding Profile Action](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#forwarding-profile-action)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)To ensure maximum interoperability between the VPN client and Zscaler Client Connector, you must select the appropriate [forwarding profile action](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#forwarding-profile-action-zia) that determines if Zscaler Client Connector works at the network (IP) layer or the application layer to tunnel traffic (or whether Zscaler Client Connector tunnels traffic at all).

**Forwarding Profile Action****How Zscaler Client Connector Traffic**
Tunnel (Route-Based) as the [Tunnel Driver Type](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#windows-driver-selection)Tunnels traffic at the network (IP) layer and captures user traffic by setting IP routes on users’ devices.
Tunnel (Packet Filter-Based) as the [Tunnel Driver Type](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#windows-driver-selection)Uses Windows packet filtering to send traffic directly to Zscaler Client Connector to process.
Tunnel with Local Proxy Tunnels traffic to the application layer and captures traffic by applying proxy settings on users' devices.

This option has advantages over the Enforce Proxy option because the app transparently handles authentication for users. Users don't have to reauthenticate for applications when they open new browsers and rarely run into issues accessing applications that aren't browser-based.
Enforce Proxy Does not tunnel any traffic. Only sets system proxy settings on the users' devices and prohibits users from changing those proxy settings.
None Does not tunnel any traffic. Performs no actions on users' devices. 
To prevent interoperability issues, Zscaler recommends the following forwarding profile actions for the different platforms:

    *   [Windows and macOS](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommended-forwarding-action-windows-macOS)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)For Windows and macOS, Zscaler recommends you use the following settings:

        *   Windows:
            *   Full-Tunnel Mode: Select only **Tunnel with Local Proxy**.
            *   Split-Tunnel Mode: Select either **Tunnel with Local Proxy** or **Tunnel**(**Packet Filter-Based**).

        *   macOS: Select only **Tunnel with Local Proxy**.

Since Zscaler Client Connector works at the application layer for Tunnel with Local Proxy and Tunnel (Packet Filter-Based) and doesn’t compete with the VPN client at the IP layer, users don’t experience interoperability issues. Instead, the app allows the VPN to take traffic as needed but sets proxy settings or packet filters to ensure that all user traffic is still protected by Zscaler.

Zscaler highly recommends that you don't select Tunnel (Route-Based) as the forwarding profile for VPN-Trusted Network. Because Zscaler Client Connector works at the same IP layer as VPN clients for Tunnel mode, it might cause interoperability problems. To learn more, see [Recommendation for Using Tunnel (Route-Based) Forwarding Profile Action](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommendation-tunnel(route-based)-forwarding-profile-action).

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommended-forwarding-action-windows-macOS)

    *   [iOS](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommended-forwarding-action-iOS)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)For iOS, Zscaler recommends you use Tunnel for the forwarding profile action. The iOS platform allows multiple VPNs to run simultaneously, as long as each VPN is of a different type; personal, per-app, or enterprise. For example, Zscaler Client Connector runs as an enterprise VPN. So you can simultaneously run another personal or per-app VPN, but not an enterprise VPN.

Zscaler Client Connector for iOS does not support Tunnel with Local Proxy.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommended-forwarding-action-iOS) 
    *   [Linux](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommended-forwarding-action-linux)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)For Linux, Zscaler recommends using the following settings if your VPN runs in full-tunnel mode:

        *   [Forwarding Profile Action for ZIA](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#forward-action-zia)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)Choose the following options based on the trusted network type:

            *   **On-Trusted Network**: Select **Tunnel with Local Proxy**.
            *   **VPN-Trusted Network:**Select **Same as On-Trusted Network**.
            *   **Off-Trusted Network**: Select **Same as On-Trusted Network**.

If Internet & SaaS (ZIA) experiences connection loss upon VPN startup, the VPN client might have failed to install the VPN bypass route for the Zscaler Client Connector administration site (e.g., the Public Service Edge for Internet & SaaS IP). If this occurs, add the Public Service Edge for Internet & SaaS IP to the VPN Gateway Bypass field in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#forward-action-zia)

        *   [Forwarding Profile Action for ZPA](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#forward-action-zpa)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)Choose the following options based on the trusted network type:

            *   **On-Trusted Network**: Select **Tunnel**.
            *   **VPN-Trusted Network:**Select **None**.
            *   **Off-Trusted Network**: Select **Same as On-Trusted Network**.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#forward-action-zpa)

Disable the VPN client's route monitoring option (e.g., Cisco AnyConnect VPN).

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommended-forwarding-action-linux)

For Android, you cannot run Zscaler Client Connector and any third-party VPN simultaneously because the Android OS only allows one concurrent VPN at a time.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#forwarding-profile-action)

*   [Step 2: Configure Proxy Settings for Users' Devices](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#proxy-settings)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)You must ensure that the VPN client is not configured to change proxy settings on users’ devices (even if you select Tunnel with Local Proxy as the forwarding profile action for VPN-Trusted Network). If VPN clients tamper with proxy settings in any way, Zscaler Client Connector does not forward traffic properly.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#proxy-settings) 
*   [Step 3: Configure Zscaler Client Connector for Different Tunnel Modes](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-mode)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)Your VPN can run in full-tunnel mode or split-tunnel mode, but each mode requires different Zscaler Client Connector configurations:

    *   [Full-Tunnel Mode](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#full-tunnel-mode-config)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)When your VPN runs full-tunnel mode, all of your users’ traffic is routed to the VPN client. Zscaler Client Connector treats the network as a VPN-Trusted Network and applies the forwarding profile action you chose for that network. However, the following important caveats apply to this configuration:

        *   Zscaler strongly recommends against using the forwarding profile Tunnel (Route-Based) for VPN-Trusted Network. It can cause interoperability issues because Zscaler Client Connector in Tunnel mode works on the IP layer (the same layer as the VPN client). Zscaler recommends selecting Tunnel with Local Proxy in this scenario for Windows and macOS.
        *   If your VPN doesn't set a default route, select Tunnel with Local Proxy. Zscaler Client Connector detects a full tunnel VPN by looking for a default route in the routing table. If the VPN doesn’t set a default route and uses a different mechanism to capture all traffic, Zscaler Client Connector won't consider the VPN a full-tunnel VPN. It won't treat the user as connected to a VPN Trusted Network. Instead, the app treats the user as Off-Trusted Network and applies the corresponding forwarding profile action.
        *   Zscaler Client Connector looks for the following words in the default interface description to detect a VPN-Trusted Network: Cisco, Juniper, Fortinet, PanGP, Check Point, and VPN. If these words are missing, the app treats the user as Off-Trusted Network.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#full-tunnel-mode-config)

    *   [Split-Tunnel Mode](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#split-tunnel-mode-config)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)When your VPN runs split-tunnel mode, only some of your users’ traffic is routed to the VPN client. For example, the VPN can set routes only for specific subnets, such as 10/8 or 192.168/16. Additionally, the VPN can set the DNS on the device to capture traffic for internal hosts. Zscaler Client Connector treats the device as Off-Trusted Network and applies the forwarding profile action you chose for that network. For a simpler configuration, Zscaler recommends selecting Tunnel with Local Proxy or Tunnel (Packet Filter Based)in this scenario.

If you choose to run Zscaler Client Connector in Tunnel mode and your VPN runs split-tunnel mode, you must take steps to ensure that the app interoperates with your VPN client. To learn more, see [Recommendation for Using Tunnel (Route-Based) Forwarding Profile Action](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#recommendation-tunnel(route-based)-forwarding-profile-action).

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#split-tunnel-mode-config) 

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-mode)

# Recommendation for Using Tunnel (Route-Based) Forwarding Profile Action

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)If your organization decides to use Tunnel (Route-Based) as the forwarding profile action, follow these recommendations for different deployment modes to ensure users don't experience connectivity issues.

*   [Full-Tunnel Mode](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-full-tunnel-mode)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)If your VPN runs in full-tunnel mode, Zscaler strongly recommends against selecting Tunnel (Route-Based) for the forwarding profile action.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-full-tunnel-mode) 
*   [Split-Tunnel Mode](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-split-tunnel-mode)

[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)If your VPN runs in split-tunnel mode:

    *   [Allow traffic destined for the VPN gateway to bypass Zscaler Client Connector.](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#allow-traffic-bypass-Z-App)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)When you select Tunnel (Route-Based) and your VPN runs split-tunnel mode, Zscaler Client Connector can only forward traffic properly if you allow traffic destined for the VPN gateway to bypass Zscaler Client Connector.

To configure this, you must specify the hostnames and IP addresses for all your VPN gateways in the Hostname or IP Address Bypass for VPN Gateway field when you configure the [app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles). Zscaler Client Connector sets the routing table to exclude all traffic destined for the VPN gateway, ensuring that this traffic is allowed to bypass the app tunnel and properly go to the VPN.

To ensure against connectivity issues, it's critical that you include all VPN hostnames or all IP addresses to which these hostnames can be resolved.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#allow-traffic-bypass-Z-App) 
    *   [Ensure that any DNS used by the VPN can resolve both internal and external domains.](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#ensure-DNS-resolves-domains)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)On Windows devices, when Zscaler Client Connector runs in Tunnel (Route-Based) mode, the app sets the DNS on the interface to point to 100.64.0.3, 100.64.0.4, and 100.64.0.5. When a user launches a VPN connection, the app detects this network change and responds accordingly.

If the VPN client runs in split-tunnel mode, Zscaler Client Connector applies the forwarding profile action for Off-Trusted Network. While this is the expected behavior, sometimes the following scenario can occur even when you configure the DNS to resolve only internal domains: Upon launch, the VPN client sets its own DNS on the interface to make it the prioritized DNS to resolve internal domains. Zscaler Client Connector detects this change and reverts it, so that 100.64.0.3 (Zscaler's DNS) is again the prioritized DNS for the user device. However, the app redirects any DNS query that comes to 100.64.0.3 to the original prioritized DNS (i.e., the VPN client DNS).

At this point, if the DNS query is for an external domain, and the original VPN DNS is configured only to resolve internal domains, the DNS query should continue to be redirected to the next DNS in the priority list, until it finds a server that can resolve the requested external domain.

However, some Windows programs do not redirect DNS queries to the next DNS in the priority list. In this scenario, the DNS query can't find a server to resolve the requested external domain, and the user can't reach the external domain. To prevent this, ensure that all VPN clients set the DNS so that they can resolve both internal and external domains.

If a third-party VPN operates in split-tunnel mode, you must configure DNS search domains in the VPN connection profile. Otherwise, Zscaler Client Connector intercepts the VPN connection's DNS requests and forwards them to the system DNS server, which might be unable to resolve the VPN domains.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#ensure-DNS-resolves-domains) 

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-split-tunnel-mode)

*   [Firewall Functionality](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-firewall)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)If your VPN has any firewall functionality, you must disable it or allowlist Zscaler Client Connector in the firewall. Otherwise, the VPN firewall can interfere with app tunnel processes.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-firewall) 
*   [Trusted Network Criteria](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-trusted-network-criteria)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)When configuring the Trusted Network Criteria for your forwarding profile, Zscaler recommends that you select DNS Server and DNS Search Domain. To learn more, see [Trusted Network Criteria](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app#trusted-network-criteria).

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-trusted-network-criteria) 
*   [Troubleshooting Temporary Network Instability](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-troubleshooting)[](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)Keep in mind that users can see some momentary network instability when a VPN client initially launches while Zscaler Client Connector is running.

On Windows devices, when Zscaler Client Connector runs in Tunnel (Route-Based), the app sets the DNS on the interface to point to 100.64.0.3, 100.64.0.4, and 100.64.0.5. When a user launches a VPN connection, the app detects this network change and responds accordingly. If the VPN client runs in full-tunnel mode, the app removes all of its own DNS settings to allow all traffic to properly go to the VPN. If the VPN client runs in split-tunnel mode, the app reconfigures DNS settings so it can properly apply the forwarding profile action for Off-Trusted Network.

While that is the ultimate expected outcome, the user can experience some temporary network instability. When the app removes its own DNS settings, this is detected as a network change by the VPN client. In response, the VPN client can disconnect and attempt to reestablish the connection. The user can experience some connectivity issues until this process is complete and the app and VPN client reach equilibrium.

[Close](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability#tunnel-route-based-troubleshooting) 

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Domain Validation in Zscaler Client Connector for Private Access Applications](https://help.zscaler.com/zscaler-client-connector/domain-validation-zscaler-client-connector-private-access-applications)[Best Practices for Zscaler Client Connector and VPN Client Interoperability](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)[Zscaler Client Connector and Charles Proxy Interoperability](https://help.zscaler.com/zscaler-client-connector/zscaler-app-charles-proxy-interoperability)[Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)[Allowing Traffic to the ID Federation URL by Bypassing Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/allowing-traffic-id-federation-url-bypassing-zscaler-client-connector)[Enrolling Zscaler Client Connector Users When Using a Proxy](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-client-connector-users-when-using-proxy)[Using Fiddler with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector)[Best Practices for Using PAC Files with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)[Implementing Zscaler Client Connector in No-Default Route Environments](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)[Understanding Alternative Cloud Domains](https://help.zscaler.com/zscaler-client-connector/understanding-alternative-cloud-domains)[Zscaler Client Connector Interoperability with Apple iCloud Private Relay](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-interoperability-apple-icloud-private-relay)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-end-user-notifications-zscaler-client-connector -->

Title: Configuring End User Notifications for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-end-user-notifications-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring End User Notifications for Zscaler Client Connector

[Watch a video about Zscaler Client Connector Notifications](https://fast.wistia.net/embed/iframe/nqirtih60a) (shows legacy UI).

In the Zscaler Admin Console, you can configure various settings for [user notifications in Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-pop-notifications). Some of these settings are enabled after a user’s device is enrolled in the Zscaler service and then a user can change these settings in Zscaler Client Connector.

If you use notification templates, this tab does not display, and you must configure the end user notification settings on the Notification Templates tab. To learn more, see [Configuring Notification Templates for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector).

Configure user notifications as follows:

1.   Go to **Policies > Common Configuration > Resources > Client Connector Notifications**.
2.   On the **End User Notifications** tab, select from the following options:

    *   **Enable Notifications by Default**: This setting is enabled when a user is enrolled. Users can turn this option off from Zscaler Client Connector.
    *   **Enable App Updates Notifications**: Select this option to have users receive app upgrade notifications.
    *   **Enable Service Status Notifications**: Select this option to have users receive status notifications for Zscaler services, such as when a service is in Disaster Recovery (DR) mode.
    *   **Enable ZIA Notifications**: Select this option to have users receive notifications from Internet & SaaS (ZIA), such as data loss prevention (DLP) notifications.
    *   **Enable Notifications for ZPA reauthentication**: Select this option to have users prompted for Private Access (ZPA) authentication. This option is enabled after a user is enrolled. Users can turn off this option from Zscaler Client Connector.
    *   **Show ZPA Reauthentication Notifications Every (In Minutes)**: Select this option to show Private Access reauthentication notifications at a specific time interval. This setting is enabled by default. Enter a value from `2` to `1440` to set the interval in minutes.

For Windows and macOS only, you can configure the following settings for the Zscaler Notification Framework.

You must have Zscaler Client Connector version 4.2 and later for Windows and Zscaler Client Connector version 4.2 and later for macOS to[enable the Zscaler Notification Framework](https://help.zscaler.com/zscaler-client-connector/about-zscaler-notification-framework) in App Profiles.

    *   **Custom Timer (In Seconds)**: Set the amount of time that the notification displays for the user. Enter a time between 5 and 60 seconds.
    *   **Enable Persistent Notifications**: When enabled, overrides the **Custom Timer**. This setting displays critical notifications until the user dismisses them. Critical notifications include Private Access reauthentication, captive portal detection, and request for a system reboot.
    *   **ZIA Notification Persistent**: When enabled, overrides the **Custom Timer** and makes certain Internet & SaaS notifications persistent. Currently, the ZIA Notification Persistent option only applies to DLP notifications.

3.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles -->

Title: Configuring Device Posture Profiles | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles

Markdown Content:
**Posture Type**: Complete the following steps, depending on the platforms you selected.

*   [Certificate Trust](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Certificate-Trust)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows, macOS, Linux, Android, or iOS. Select **Certificate Trust** from the drop-down menu and upload a certificate issued by an internal root Certificate Authority (CA) trusted by your organization's users. The user's device must trust the certificate to pass the posture validation check.

For iOS, the certificate must be trusted directly by the device. An intermediate certificate doesn’t work.

Zscaler accepts Base64-encoded .pem and .cer files, and you can upload any one of the following:

    *   A root CA certificate
    *   An intermediate certificate
    *   A client certificate

![Image 1: Certificate Trust Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-certificate-trust.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Certificate-Trust)

*   [File Path](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#FilePath)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows or macOS. Select **File Path** from the drop-down menu and enter a file path that can be found on your users' systems. For example, you can enter: C:\Program Files(x86)\Example\AV.txt.

The user's device must have the specified file path to pass the posture validation check.

![Image 2: File Path Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-file-path.png)

If you choose more than one platform, the File Path option is not available. Instead, you must use the Certificate Trust posture type.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#FilePath) 
*   [Registry Key](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Registry-Key)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows only. Select **Registry Key** from the **Posture Type** drop-down menu. Enter the registry key in the **Registry Key** field.

From the **Match Type**drop-down menu, select either **Path**or **Value**:

    *   If you select **Path**, ensure that the registry key begins with HKEY. For example, `HKEY_CURRENT_USER\Software\Zscaler\App`.
    *   If you select **Value**, you must also complete the **Name**and **Data**fields. For example, if you enter the registry key `HKEY_CURRENT_USER\Software\Zscaler\App`, you would enter `ZNW_State` in the **Name** field and `CONNECTING` in the **Data** field to describe that the service is connecting. To learn more, see [Zscaler Client Connector: Windows Registry Keys](https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys).

The app checks against the registry key **Path**or the **Value**. The user's device must have the specified registry key to pass the posture validation check. The registry key check runs in the user context. If your permissions prevent users from accessing the system keys (e.g., HKLM), you must create the registry key where users can access it.

![Image 3: Registry Key Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-registry-key.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Registry-Key)

*   [Client Certificate](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Client-Certificate)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows, macOS, Linux, and Android. For details on how to configure Linux for the Client Certificate, see [Configuring the Client Certificate Posture Check for Linux](https://help.zscaler.com/zscaler-client-connector/configuring-client-certificate-posture-check-linux).

As of Zscaler Client Connector version 1.14 for Android, Zscaler ended support for the Knox Admin and the Client Certificate posture type for Samsung Android devices. Zscaler recommends you remove the Client Certificate posture check for Samsung Android devices in the Zscaler Admin Console. To learn more, see [End-of-Support for the Client Certificate Posture Check and Device Admin for Samsung Android Devices](https://help.zscaler.com/eos-eol/end-support-client-certificate-posture-check-and-device-admin-samsung-android-devices).

Select **Client Certificate** from the drop-down menu and upload a certificate issued by an internal root Certificate Authority (CA) trusted by your organization's users. The certificate must be in the user store at the endpoint, issued by the CA that matches the uploaded certificate in the posture profile. The user must have a private and public key pair issued by the specified CA to pass this posture validation check.

Zscaler accepts Base64-encoded .pem and .cer files, and you can upload any one of the following:

    *   A root CA certificate
    *   An intermediate CA certificate

After uploading a certificate, enable or disable the **Non-Exportable Private Key**. If enabled, Zscaler Client Connector must check if the certificate's private key can or cannot be exported. Posture validation fails if **Non-Exportable Private Key**is enabled and the key is exportable.

Optionally, enable **Perform CRL Check**. If enabled, Zscaler Client Connector can help detect whether the certificate was revoked. The Certificate Revocation List (CRL) contains digital certificates that were revoked by the issuing CA before their scheduled expiration date, and those certificates should no longer be trusted. If a certificate was revoked, the posture check fails.

Zscaler Client Connector for Windows checks the CRL that was downloaded from the CA server and cached by a Microsoft API. If you use Zscaler Client Connector version 4.8 and later for Windows, you can have Zscaler Client Connector download, validate, and cache the CRL locally based on a configurable cache interval (0 to 30 minutes). Zscaler recommends using this option only in these scenarios:

    *   The CRL server experiences instability due to fluctuating between reachable and unreachable states.
    *   The CRL server is hosted as a Private Access application which could lead to ambiguous caching behavior due to Private Access access expiration.
    *   You require frequent CRL downloads or granular control over caching intervals for near real-time validation.

Contact Zscaler Support to enable this feature.

For Windows and macOS only, enter a value in the **Client Certificate Template Information** field that matches the certificate issued by the uploaded root certificate.

![Image 4: Client Certificate Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-client-certificate_0.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Client-Certificate)

*   [Firewall](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Firewall)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows or macOS. Select **Firewall** from the drop-down menu. Zscaler Client Connector checks the firewall status for all three firewall profiles: public, private, and domain. At least one of these profiles must be active to pass the posture validation check.

Zscaler Client Connector doesn't check for the presence of third-party firewalls. Third-party firewalls don't fulfill the posture check requirement.

![Image 5: Firewall Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-firewall.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Firewall) 
*   [Full Disk Encryption](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Full-Disk-Encryption)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows, macOS, and Android. Also applies to Zscaler Client Connector version 1.4 and later for Linux. Select **Full Disk Encryption** from the drop-down menu. Zscaler Client Connector checks if the device has full disk encryption enabled or disabled. The user must have full disk encryption enabled to pass the posture validation check.

![Image 6: Full Disk Encryption Posture TYpe](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-full-disk-encryption.png)

For Linux only, provide a value in the **File Path** field to check whether or not the disk or the disk partition that contains the File Path is encrypted.

![Image 7: Disk Encryption Posture Type for Linux](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-full-disk-encryption-linux.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Full-Disk-Encryption) 
*   [Domain Joined](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Domain-Joined)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows or macOS. Select **Domain Joined** from the drop-down menu and enter the domain or workgroup to match. Zscaler Client Connector checks if the device is joined to the domain or workgroup. The user's device must have a matching domain to pass the posture validation check.

![Image 8: Domain Joined Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-domain-joined.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Domain-Joined) 
*   [Process Check](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Process-Check)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Zscaler Client Connector version 2.1.2 and later for Windows or macOS or Zscaler Client Connector version 1.3 and later for Linux. Select **Process Check** from the drop-down menu. For **Process Path**, enter the name and absolute path for the process (e.g., `c:\folder\process.exe`). Do not use double quotes. For **Signer Certificate Thumbprint,**enter the unique identifier of the process signer certificate. You can add multiple thumbprints for a single Process Check by separating each thumbprint with a comma.

    *   [Locating the Signer Certificate Thumbprint for Windows](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#win)

In File Explorer:

        1.   Go to **Program Files**>**Zscaler**>**ZSATunnel**.
        2.   Right-click the **ZSATunnel** file and select **Properties**.
        3.   Click the **Digital Signatures** tab.
        4.   Select the signature from the list and click **Details**.
        5.   Click **View Certificate**.
        6.   Click the **Details**tab. Scroll down and click **Thumbprint**. The thumbprint displays in a window from which you can copy and paste it into the **Signer Certificate Thumbprint** field.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#win)

    *   [Locating the Signer Certificate Thumbprint for macOS](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#macos)

In the terminal window:

        1.   Extract the certificates associated with binary codesign -d --extract-certificates binarypath: (e.g., `codesign -d --extract-certificates /Library/CS/CSDaemon <binary path>`)
        2.   Parse the certificate: `for ((certnumber=0 ; certnumber<=2; certnumber++)) ; do openssl asn1parse -in codesign${certnumber} -inform der -out codesign${certnumber}.der ; done`
        3.   Convert to a PEM file: `for ((certnumber=0 ; certnumber<=2; certnumber++)) ; do openssl x509 -inform der -in codesign${certnumber}.der -out codesign${certnumber}.pem ; done`
        4.   Extract the certificates using the following command: `openssl x509 -noout -sha1 -fingerprint -inform pem -in codesign0.pem`

You can change `-sha1` to `-sha256`.

        5.   Remove the colons from the output.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#macos)

If you use Zscaler Client Connector version 4.9 and later for Windows, you can specify one or more software versions that the device must have to pass the check. Enter up to three versions in the **Version** field in a minimum two-version level to maximum 4-version level format. If you select **Exact Match**, the exact version number is required to pass the check. Contact Zscaler Support to enable this feature.

The following table shows example results for different values:

| Values in Version Field | Exact Match Checkbox | Passing Versions |
| --- | --- | --- |
| 1.8 | Selected | Only 1.8.X.X versions |
| Cleared | All versions 1.8.X.X and later |
| 1.8.0.456, 1.8.0.789 | Selected | Only versions 1.8.0.456 and 1.8.0.789 |
| Cleared | All versions 1.8.0.456 and later |
| 1.8.0.456, 1.9.0.789 | Selected | Only versions 1.8.0.456 and 1.9.0.789 |
| Cleared | All versions 1.8.0.456 and later (including 1.9.X.X) |

![Image 9: Process Check Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-process-check.png)[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Process-Check)

*   [Detect Carbon Black](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-Carbon-Black)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)This is only applicable if you’re using Zscaler Client Connector version 2.1.2 and later for Windows or macOS. Select **Detect Carbon Black**. The user must have Carbon Black running on the device to pass the posture validation check.

![Image 10: Carbon Black Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-detect-carbon-black.png)

If you use Zscaler Client Connector version 4.9 and later for Windows, you can specify one or more software versions that the device must have to pass the check. Enter up to three versions in the **Version** field in a minimum two-version level to maximum 4-version level format. If you select **Exact Match**, the exact version number is required to pass the check. Contact Zscaler Support to enable this feature.

The following table shows example results for different values:

| Values in Version Field | Exact Match Checkbox | Passing Versions |
| --- | --- | --- |
| 1.8 | Selected | Only 1.8.X.X versions |
| Cleared | All versions 1.8.X.X and later |
| 1.8.0.456, 1.8.0.789 | Selected | Only versions 1.8.0.456 and 1.8.0.789 |
| Cleared | All versions 1.8.0.456 and later |
| 1.8.0.456, 1.9.0.789 | Selected | Only versions 1.8.0.456 and 1.9.0.789 |
| Cleared | All versions 1.8.0.456 and later (including 1.9.X.X) | 
The following signer certificate matching thumbprints are supported for the Carbon Black **RepMgr.exe** executable for Windows:

| Zscaler Client Connector version 3.5 and later for Windows |
| --- |
| 4d66d506976bde36ae01ab3335d501bec9fb9837 f855a4a29ecefdd9ad04384ae3a099aff61d717f 9033309926659b4346c496d44407d39e0487868c d9665dc3abce52eaf263dad3412c7cedb9e79b9d fce3566368d917acf28779b98d996f416fef1f2b | 
For Zscaler Client Connector version 4.5 and later for Windows, if there are no matching thumbprints, the posture check still passes if Carbon Black is installed and turned on in Windows Security.

The next table displays the signer certificate matching thumbprints supported for the following Carbon Black binaries for macOS:

    *   `/Applications/Confer.app/Contents/MacOS/repmgr`
    *   `/Applications/VMware Carbon Black Cloud/repmgr.bundle/Contents/MacOS/repmgr`
    *   `/Library/SystemExtensions/<UUID>/com.vmware.carbonblack.cloud.se-agent.extension.systemextension/Contents/MacOS/se_agent`

The **UUID** varies depending on your system settings.

If you are using an older version of Zscaler Client Connector, look at the thumbprints of the Carbon Black release to ensure that the thumbprints are supported by the posture.

**Zscaler Client Connector version 4.3 and later for macOS**
8F262B63E0DA232A95111C9E1428BBCE76E609B7

B19EE7CB9B1FEA04F201BBF12F71EE54DC38EDDBD2C80F662EC15090DD442266

DAE2AE5FF662A39BC591E0FF2F3BB7BD2E00C23D

For Zscaler Client Connector version 4.3 and later for macOS, if there are no matching thumbprints, the posture check still passes if Carbon Black is installed and the process is running.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-Carbon-Black)

*   [Detect CrowdStrike](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-CrowdStrike)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)This is only applicable if you’re using Zscaler Client Connector version 2.1.2 and later for Windows or macOS. Select **Detect CrowdStrike**. The user must have CrowdStrike running on the device to pass the posture validation check.

![Image 11: Detect CrowdStrike Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-detect-crowdstrike.png)

If you use Zscaler Client Connector version 4.9 and later for Windows, you can specify one or more software versions that the device must have to pass the check. Enter up to three versions in the **Version** field in a minimum two-version level to maximum 4-version level format. If you select **Exact Match**, the exact version number is required to pass the check. Contact Zscaler Support to enable this feature.

The following table shows example results for different values:

| Values in Version Field | Exact Match Checkbox | Passing Versions |
| --- | --- | --- |
| 1.8 | Selected | Only 1.8.X.X versions |
| Cleared | All versions 1.8.X.X and later |
| 1.8.0.456, 1.8.0.789 | Selected | Only versions 1.8.0.456 and 1.8.0.789 |
| Cleared | All versions 1.8.0.456 and later |
| 1.8.0.456, 1.9.0.789 | Selected | Only versions 1.8.0.456 and 1.9.0.789 |
| Cleared | All versions 1.8.0.456 and later (including 1.9.X.X) | 
The following signer certificate matching thumbprints are supported for the CrowdStrike **CSFalconService.exe** executable for Windows:

| Zscaler Client Connector version 3.5 and later for Windows |
| --- |
| cd485eaadb584dcf911bb9dae6ecae1253f05308 93324968ecd73a9570c347cf0c8ee4bc390d3948 38b7c74e37392713e436e19a2be053100115da88 0e2f50955c71c9a9b5e6f9c0ecb1c1b198249f4f 9c4a4665abbcd3d4a8bffcd9251b21bb2ec6e341 426510225a68b9260814230b37e289bfe0566f28 ca5296e9d87044679d0b405104b8db2c8d56eb03 b878d8eb696cf3d4505e2f6641c57af9062ec51a 1935420a805a0cefebecdbe59a391a69db32eab3 16adfe3b79a93d4b1fa7b8e6b2c972c99bfdc849 62c16976bfbbf57b8f8b588a043b4fb0bc294a6d | 
For Zscaler Client Connector version 4.5 and later for Windows, if there are no matching thumbprints, the posture check still passes if CrowdStrike is installed and turned on in Windows Security.

The next table displays the signer certificate matching thumbprints supported for the following CrowdStrike binaries for macOS:

    *   `/Library/CS/falcond`
    *   `/Applications/Falcon.app/Contents/Resources/falcond`
    *   `/Library/SystemExtensions/<UUID>/com.crowdstrike.falcon.Agent.systemextension/Contents/MacOS/com.crowdstrike.falcon.Agent`

The `UUID` is different for every installation. For example: `/Library/SystemExtensions/7F3C861E-7137-4797-AA86-CFD764D1796F/com.crowdstrike.falcon.Agent.systemextension/Contents/MacOS/com.crowdstrike.falcon.Agent`

If you are using an older version of Zscaler Client Connector, look at the thumbprints of the CrowdStrike release to ensure that the thumbprints are supported by the posture.

**Zscaler Client Connector 4.3 and later for macOS**
475B2989BAE075A1F444B76ADAABE8EDF33A0ADF

B7447A2DAE7B0C3016274ACBB55F7AA8A7349ACF

5B45F61068B29FCC8FFFF1A7E99B78DA9E9C4635

611E5B662C593A08FF58D14AE22452D198DF6C60

For Zscaler Client Connector version 4.3 and later for macOS, if there are no matching thumbprints, the posture check still passes if CrowdStrike is installed and the process is running.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-CrowdStrike)

*   [CrowdStrike ZTA Score](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#CrowdStrike-ZTA)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)This is only applicable if you’re using Zscaler Client Connector version 3.4 and later for Windows or macOS. Select **CrowdStrike ZTA Score**. The user must have CrowdStrike running on the device to pass the posture validation check.

Users must enable the CrowdStrike ZTA feature in CrowdStrike. Contact CrowdStrike Support to enable this feature.

Enter a score between `1` and `100`. This number represents a risk score that helps determine the security level of the device. The higher the number you designate, the more secure you consider the device. Zscaler Client Connector compares this number against an overall score (a combined score based on the device OS settings and sensor settings) assigned by CrowdStrike’s user agent. If that overall score is greater than or equal to the score you’ve entered, the posture check passes. If the overall score is less than the score you entered, the posture check fails. To learn more about the ZTA score, refer to the CrowdStrike documentation.

![Image 12: CrowdStrike ZTA Score Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-crowdstrike-zta.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#CrowdStrike-ZTA) 
*   [Detect SentinelOne](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-SentinelOne)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)This is only applicable if you’re using Zscaler Client Connector version 2.1.2 and later for Windows or macOS. Select **Detect SentinelOne**. The user must have SentinelOne running on the device to pass the posture validation check.

![Image 13: Sentinel One Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-detect-sentinelone.png)

If you use Zscaler Client Connector version 4.9 and later for Windows, you can specify one or more software versions that the device must have to pass the check. Enter up to three versions in the **Version** field in a minimum two-version level to maximum 4-version level format. If you select **Exact Match**, the exact version number is required to pass the check. Contact Zscaler Support to enable this feature.

The following table shows example results for different values:

| Values in Version Field | Exact Match Checkbox | Passing Versions |
| --- | --- | --- |
| 1.8 | Selected | Only 1.8.X.X versions |
| Cleared | All versions 1.8.X.X and later |
| 1.8.0.456, 1.8.0.789 | Selected | Only versions 1.8.0.456 and 1.8.0.789 |
| Cleared | All versions 1.8.0.456 and later |
| 1.8.0.456, 1.9.0.789 | Selected | Only versions 1.8.0.456 and 1.9.0.789 |
| Cleared | All versions 1.8.0.456 and later (including 1.9.X.X) | 
The following signer certificate matching thumbprints are supported for the SentinelOne **SentinelAgent.exe** executable for Windows:

| Zscaler Client Connector version 3.6 and later for Windows | Zscaler Client Connector version 3.5 and later for Windows |
| --- | --- |
| f1654692e31c02872d73507de97617fd6dc14c8c 910b496dc984d3818001dbfe1a75cf75e46aef0e 2b90394e9b094f067f84d9a0b15009d51fda20dd 22C492673C2651B397AC13750978220A87E03CD5 69640f124bafc9a891fc4b51fcfd0ddf8785a6e5 5dfc737f10a570899baf8209e9e5cd4cfdfd6aed 403e7374894c743e3ebd2ecc15f8a46091ff6a4d 2349d742850cafb6f691b5d2b79b3d47576ec52a | f1654692e31c02872d73507de97617fd6dc14c8c 910b496dc984d3818001dbfe1a75cf75e46aef0e 2b90394e9b094f067f84d9a0b15009d51fda20dd | 
For Zscaler Client Connector version 4.5 and later for Windows, if there are no matching thumbprints, the posture check still passes if SentinelOne is installed and turned on in Windows Security.

The next table displays the signer certificate matching thumbprints supported for the following SentinelOne binaries for macOS:

`/Library/Sentinel/sentinel-agent.bundle/Contents/MacOS/sentineld`

If you are using an older version of Zscaler Client Connector, look at the thumbprints of the SentinelOne release to ensure that the thumbprints are supported by the posture.

**Zscaler Client Connector 3.7 and later for macOS**
CAF5A626546E84A2036913B07FCB835EB60D4F95

c885b2ee5abf5815455f274990215f1b29514b49 
For Zscaler Client Connector version 4.3 and later for macOS, if there are no matching thumbprints, the posture check still passes if SentinelOne is installed and the process is running.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-SentinelOne) 
*   [Ownership Variable](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Ownership-Variable)
*   [](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Android or iOS. Select **Unauthorized Modification** from the drop-down menu. Zscaler Client Connector checks for unauthorized modifications on the device, such as jailbreaking or rooting. The user's device must not have unauthorized modifications to pass the posture validation check.

![Image 14: Unauthorized Modification Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-unauthorized-modification.png)

If you are using Zscaler Client Connector version 1.5.2 for iOS or Zscaler Client Connector version 1.5.0 for Android, see [Using the Unauthorized Modification Device Posture Profile](https://help.zscaler.com/zscaler-client-connector/known-issues-device-posture-profiles) for known issues.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Unauthorized-Modification) 
*   [Detect Microsoft Defender](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-MS-Defender)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Zscaler Client Connector version 2.1.1 and later for Windows or macOS, or Zscaler Client Connector version 1.4 and later for Linux. Select **Detect Microsoft Defender**. The user must have Microsoft Defender Advanced Threat Protection running on the device to pass the posture validation check.

![Image 15: Microsoft Defender Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-detect-ms-defender.png)

If you use Zscaler Client Connector version 4.9 and later for Windows, you can specify one or more software versions that the device must have to pass the check. Enter up to three versions in the **Version** field in a minimum two-version level to maximum 4-version level format. If you select **Exact Match**, the exact version number is required to pass the check. Contact Zscaler Support to enable this feature.

The following table shows example results for different values:

| Values in Version Field | Exact Match Checkbox | Passing Versions |
| --- | --- | --- |
| 1.8 | Selected | Only 1.8.X.X versions |
| Cleared | All versions 1.8.X.X and later |
| 1.8.0.456, 1.8.0.789 | Selected | Only versions 1.8.0.456 and 1.8.0.789 |
| Cleared | All versions 1.8.0.456 and later |
| 1.8.0.456, 1.9.0.789 | Selected | Only versions 1.8.0.456 and 1.9.0.789 |
| Cleared | All versions 1.8.0.456 and later (including 1.9.X.X) | 
For Linux only, enter the executable path for the Microsoft Defender endpoint on Linux.

![Image 16: Microsoft Defender Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-detect-ms-defender-linux.png)

The next table displays the signer certificate matching thumbprints supported for the following Detect Microsoft Defender binaries for macOS:

`/Library/SystemExtensions/<UUID>/com.microsoft.wdav.epsext.systemextension/Contents/MacOS/epsext`

**Zscaler Client Connector version 3.7 and later for macOS**
2ED71A71E4BFC746CBB0C0B1C80AADCE899A67F2

7D4DE827C9FA532C2C65684AD5DDB003231DDF9F 
[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-MS-Defender) 
*   [Detect Antivirus](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-Antivirus)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)This is only applicable if you’re using Zscaler Client Connector version 3.6 and later for Windows or macOS. Select **Detect Antivirus** from the drop-down menu.

For Windows, you can enter the antivirus name in the **AV Name**field and enable **Check if AV Definitions Are Up-to-Date**. When enabled, Zscaler Client Connector detects if an antivirus is running on the system. If you specify an antivirus name, the antivirus must be running on the system for the posture check to pass. If you leave the **AV Name** field blank, Zscaler Client Connector detects any antivirus running on the system.

For macOS, you must specify the antivirus name in the **AV Name** field. Include the system extension name as part of the antivirus name. You can use the command line tool `systemextensionsctl` to identify the complete name.

![Image 17: Detect Antivirus for Device Posture Configuration on the Zscaler Client Connector portal](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-detect-antivirus.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Detect-Antivirus) 
*   [OS Version](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#OS-Version)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Zscaler Client Connector version 3.6 and later for Windows or macOS, or Zscaler Client Connector 1.4 and later for Linux, or Zscaler Client Connector 3.7 and later for Android.Select **OS Version** from the drop-down menu.

For Windows only, select an edition from the **OS Editions** drop-down menu, click **Add OS**to add it, and then select a build from the **OS Build** drop-down menu. If the machine is running this version or later, then the posture check passes. Click **Add OS** to add more OS Editions.

![Image 18: OS Version Posture Type for Windows](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-os-version-windows.png)

For macOS only, select a version from the**OS Versions**drop-down menu, click **Add OS** to add it, and then enter the OS version. Click **Add OS** to add more OS Versions.

![Image 19: OS Version Posture Type for macOS](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-os-version-macos.png)

For Linux only, select a distribution from the **Linux Distributions** drop-down menu, click **Add OS**to add it, and then enter the OS version. Click **Add OS** to add more Linux Distributions.

![Image 20: OS Version Posture Type for Linux](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-os-version-linux.png)

For Android only, select an edition from the **OS Editions** drop-down menu, click **Add OS**to add it, and then click the **Calendar** icon to select a month for the patch date. Click **Add OS** to add more OS Editions.

![Image 21: OS Version Posture Type for Android](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-os-version-android.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#OS-Version) 
*   [Jamf Detection](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#JAMF-detection)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Zscaler Client Connector version 4.3 and later for macOS.

    *   **Jamf Detection**: Zscaler Client Connector checks if Jamf is running on the device.

![Image 22: Jamf Detection Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-jamf-detection.png)

    *   **Jamf Risk Level**: Zscaler Client Connector checks for Jamf-provided risk levels. Choose from **Secure**, **Low**, **Medium**, or **High**.

![Image 23: Jamf Risk Level Device Posture Type](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-jamf-risk-level.png)

If you are using Jamf Detection posture check, when removing an MDM profile from a device, the Jamf daemon might still be running. To ensure it is not running, on unenrollment, admins must add the command `sudo jamf -removeFramework` to Jamf Pro to completely remove the Jamf daemon from running on the system.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#JAMF-detection)

*   [AzureAD Domain Joined](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#azuread)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Windows only. Select **AzureAD Domain Joined** from the drop-down menu and then enter a globally unique identifier in the **Tenant Id** field. Zscaler Client Connector checks if the device is AzureAD Domain Joined to the specified Tenant Id.

A device can be only Domain Joined, only AzureAD Domain Joined, or both AzureAD Domain Joined and Domain Joined (i.e., hybrid join).

![Image 24: AzureAD Domain-Joined](https://help.zscaler.com/downloads/zscaler-client-connector/device-posture-profiles/configuring-device-posture-profiles/zclient-connector-device-posture-azuread-domain-joined.png)[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#azuread) 
*   [Server Validated Client Certificate](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Server-Validated-Client-Certificate)

[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies only to Zscaler Client Connector version 4.4 and later for Windows.

Select **Server Validated Client Certificate** from the drop-down menu and upload a CA certificate trusted by your organization's users. The client certificate must be either in the current user personal store or local computer personal store at the endpoint. The client certificate must be issued by the CA certificate uploaded in the posture profile.

For this posture validation check, the server sends a challenge to the client. After locating the private key and the root CA on the certificate chain, the client sends the signed challenge back to the server to pass the validation.

Zscaler accepts Base64-encoded .pem and .cer files, and you can upload any one of the following:

    *   A root CA certificate
    *   An intermediate certificate

After uploading a certificate, enable or disable the **Non-Exportable Private Key**. If enabled, Zscaler Client Connector must check if the certificate's private key can or cannot be exported. Posture validation fails if **Non-Exportable Private Key**is enabled and the key is exportable.

If you upload a large number of certificates (more than 50), the response from the server might take some time (around 10 seconds).

Enable the**Perform CRL Check**option to allow Zscaler Client Connector to help detect whether the certificate was revoked. The Certificate Revocation List (CRL) contains digital certificates that were revoked by the issuing CA before their scheduled expiration date, and those certificates should no longer be trusted. If a certificate was revoked, the posture check fails.

![Image 25: Server Certificate Device Posture Type](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-device-posture-profiles-draft-doc-60178/zclient-connector-device-posture-server-validated.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#Server-Validated-Client-Certificate)

*   [CrowdStrike ZTA Device OS Score](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#CrowdStrike-ZTA-Device-OS-Score)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)This is only applicable if you’re using Zscaler Client Connector version 4.6 and later for Windows or Zscaler Client Connector version 4.8 and later for macOS. Select **CrowdStrike ZTA Device OS Score**. The user must have CrowdStrike running on the device to pass the posture validation check.

Users must enable the CrowdStrike ZTA feature in CrowdStrike. Contact CrowdStrike Support to enable this feature.

Enter a score between `1` and `100`. This number represents a risk score that helps determine the security level of the device. The higher the number you designate, the more secure you consider the device. Zscaler Client Connector compares this number against the OS score assigned by CrowdStrike’s user agent. If that OS score is greater than or equal to the score you’ve entered, the posture check passes. If the OS score is less than the score you entered, the posture check fails. To learn more about the OS score, refer to the CrowdStrike documentation.

![Image 26: CrowdStrike ZTA Score Device Posture Type](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-device-posture-profiles-draft-doc-60178/zclient-connector-device-posture-crowdstrike-os.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#CrowdStrike-ZTA-Device-OS-Score) 
*   [CrowdStrike ZTA Sensor Setting Score](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#CrowdStrike-ZTA-Sensor-Setting-Score)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)This is only applicable if you’re using Zscaler Client Connector version 4.6 and later for Windows or Zscaler Client Connector version 4.8 and later for macOS. Select **CrowdStrike ZTA Sensor Setting Score**. The user must have CrowdStrike running on the device to pass the posture validation check.

Users must enable the CrowdStrike ZTA feature in CrowdStrike. Contact CrowdStrike Support to enable this feature.

Enter a score between `1` and `100`. This number represents a risk score that helps determine the security level of the device. The higher the number you designate, the more secure you consider the device. Zscaler Client Connector compares this number against the sensor setting score assigned by CrowdStrike’s user agent. If that sensor setting score is greater than or equal to the score you’ve entered, the posture check passes. If the sensor setting score is less than the score you entered, the posture check fails. To learn more about the sensor setting score, refer to the CrowdStrike documentation.

![Image 27: CrowdStrike ZTA Score Device Posture Type](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-device-posture-profiles-draft-doc-60178/zclient-connector-device-posture-crowdstrike-sensor.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#CrowdStrike-ZTA-Sensor-Setting-Score) 
*   [Zscaler Client Connector Version](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#zscaler-client-connector-version)[](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)Applies to Zscaler Client Connector version 4.8 and later for Windows or Zscaler Client Connector version 4.8 and later for macOS. Select**Zscaler Client Connector Version** from the drop-down menu. Enter up to five versions in the **Version** field in a minimum two-version level to maximum 4-version level format. If you select **Exact Match**, the exact version number is required to pass the check.

You can enter only values 4.8 and higher (e.g., 4.8, 4.8.0.456, 4.9, 4.9.0.789). The following table shows example results for different values:

| Values in Version Field | Exact Match Checkbox | Passing Versions |
| --- | --- | --- |
| 4.8 | Selected | Only 4.8.X.X versions |
| Cleared | All versions 4.8.X.X and later |
| 4.8.0.456, 4.9 | Selected | All versions 4.9.X.X and only version 4.8.0.456 |
| Cleared | All versions 4.8.0.456 and later (including 4.9.X.X) |
| 4.8.0.456, 4.9.0.789 | Selected | Only versions 4.8.0.456 and 4.9.0.789 |
| Cleared | All versions 4.8.0.456 and later (including 4.9.X.X) | 
![Image 28: Zscaler Client Connector Version device posture](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/configuring-device-posture-profiles-draft-doc-60178/zclient-connector-device-posture-zcc-version.png)

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#zscaler-client-connector-version) 

Not all device posture types work for all platforms. If you select a device that doesn't support a specific posture type, the posture type is unavailable.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device -->

Title: Why Is Zscaler Client Connector on My Device? | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Why Is Zscaler Client Connector on My Device?

[Watch a video about Zscaler Client Connector.](https://fast.wistia.net/embed/iframe/sq0jjgyieg)

Zscaler is a SaaS security platform that provides fast, secure connections between you and your applications, regardless of device, location, or network. Zscaler Client Connector is an application that allows you to receive all of the benefits of the Zscaler service from your device, even when you are off your corporate network. Zscaler Client Connector forwards your traffic to the Internet & SaaS service. It also allows you to use the Private Access, Zscaler Digital Experience (ZDX), and Zscaler Endpoint Data Loss Prevention (DLP) services.

*   With the Internet & SaaS service, you can protect your internet traffic and allow your users to securely access the internet. This service scans all traffic in real time to ensure compliance with corporate policies and protection from threats. These threats include viruses, advanced threats, malware, phishing attempts, malicious sites, and more.
*   With the Private Access service, you can securely access internal applications and services from any location.
*   With the Zscaler Digital Experience (ZDX) service, Zscaler Client Connector performs synthetic probing to a desired Software as a Service (SaaS) application or internet-based service (e.g., OneDrive, Gmail, etc.) to triage and pinpoint the source of performance issues.
*   With the Endpoint DLP service, you can protect your organization from data loss on endpoints (i.e., printing, saving to removable storage, saving to network shares, or uploading to personal cloud storage accounts.

## Using Zscaler Client Connector

You must log in to Zscaler Client Connector with your user ID and complete the one-step device enrollment process. Then you can safely connect to the web and your organization's internal resources.

Zscaler Client Connector automatically recognizes when you are connected to a trusted network (i.e., your corporate office network), and depending on your organization's configuration, can disable its Internet & SaaS, Private Access, Zscaler Digital Experience (ZDX), or Endpoint DLP service accordingly. It can also recognize when you connect to Wi-Fi hotspots (i.e., airports, hotels, cafés) where you must pay or accept a use policy before connecting. The app disables its services for a period of time and re-enables itself after you've had a chance to complete the steps necessary to connect.

To learn more about using Zscaler Client Connector and its features, see [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector).

You can view the connection status of the Internet & SaaS, Private Access, ZDX, and Endpoint DLP services on Zscaler Client Connector:

*   On the **Internet Security** page, if the **Status** is **ON**, your internet traffic is protected.

[See image.](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#z-app-on-zia)

*   On the **Private Access** page, if the **Service Status** is **ON**, you can securely access your internal resources.

[See image.](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#z-app-on-zpa)

*   On the **Digital Experience** page, if the **Service Status** is **ON**, your network, application, and device performance are being measured.

[See image.](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#z-app-on-zdx)

*   On the **Data Protection** page, if the **Service Status** is **ON**, endpoint user activity is monitored.

[See image.](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#endpoint-dlp-service)

[![Image 2: The service status of Endpoint DLP on Zscaler Client Connector](https://help.zscaler.com/downloads/tech-pubs-drafts/client-connector-draft-articles/why-zscaler-client-connector-my-device-draft-doc-48428/endpoint-dlp-service-status.jpg)](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device)

## When does Zscaler protect me?

The Zscaler service protects your internet traffic when you connect to your corporate network or to a public internet connection, depending on your organization's configuration. For example, your organization's configuration might mean that you are protected when connected to the corporate network, but you are not protected when connected from home. Your organization can tell you when your connection is protected by Zscaler .

## What about privacy?

The Zscaler service does not record or store personal data when you browse the internet. The service only inspects your internet traffic for threats when you are connected to your corporate network or when Zscaler Client Connector is enabled. Your company might also choose to exempt non-corporate applications from inspection.

Depending on your organization’s corporate policy, your organization might track your internet browsing activity. Follow your organization’s terms of service (TOS) when browsing the internet.

## Why does my organization need to install a certificate on my device?

If your organization requires it, you might be prompted to install a certificate. This certificate is used to inspect your applications' traffic to protect against security threats. This certificate is typically not used when you access the internet from outside of your organization's network (e.g., home Wi-Fi, cafés, public hotspots) and when you disable Zscaler Client Connector.

## What should I be aware of when I browse the internet?

Follow your organization's TOS when browsing the internet. Your organization has configured policies to protect you from harmful websites, and to restrict access to sites that do not conform with its internal internet usage guidelines. Your organization might also limit bandwidth to streaming websites, in order to ensure access to business-related sites is undisrupted.

## How do I troubleshoot Zscaler Client Connector?

Zscaler Client Connector displays error messages in the **Status**. You can either:

*   [Click**Retry** to resolve the error.](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#retry)[](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device)The **Retry** option appears next to the **Status**.

![Image 3: The Retry option for Zscaler Client Connector](https://help.zscaler.com/downloads/retry-z-app.png)

[Close](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#retry) 
*   [Click **Repair App** to resolve the error.](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#repair-app)[](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device)The**Repair App** option appears on the **More** page in the **Troubleshoot** menu.

![Image 4: The Repair option for Zscaler Client Connector](https://help.zscaler.com/downloads/zia/repair-app-z-app.png)

[Close](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#repair-app) 

To learn more about connection status errors, see [Zscaler Client Connector: Connection Status Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors).

If you encounter an error that is not in the**Status** and has an error code, contact your organization's support team.

[See image.](https://help.zscaler.com/zscaler-client-connector/why-is-zscaler-client-connector-my-device#Z-App-Error)

You must contact your organization's support team if you experience general issues not specific to Zscaler Client Connector.

## Who should I contact if I experience any issues?

If you experience any issues with your internet connection, contact your organization's support team.

For issues with Zscaler Client Connector (if your organization enables this feature), you can send support request emails directly from the app to your organization's support team, or you can submit tickets directly from the app to Zscaler Support. If this feature is unavailable, contact your organization's support team.

To learn how to report issues from Zscaler Client Connector, see [Reporting an issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-device-owner-information -->

Title: Configuring Zscaler Client Connector to Collect Device Owner Information | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-device-owner-information

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring Zscaler Client Connector to Collect Device Owner Information

Zscaler Client Connector collects the device owner name from supported devices and stores it in the Zscaler Admin Console database. You can enable or disable the app from collecting device owner information, allowing for compliance with General Data Protection Regulation (GDPR).

This setting is enabled by default.

To configure whether the app collects or does not collect information:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**User Privacy**.
2.   Select the **Collect Device Owner Information** option.

![Image 2: Collect Device Owner Information option](https://help.zscaler.com/downloads/zclient-connector-user-privacy-collect-device-owner.png)

3.   Click **Save**.

To see the collected device owner information, go to the **Enrolled Devices** page. For any enrolled device, view the device fingerprint and locate the **Owner** field. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-firefox-integration-zscaler-client-connector -->

Title: Configuring Firefox Integration for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-firefox-integration-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring Firefox Integration for Zscaler Client Connector

You can enable or disable Firefox integration for Zscaler Client Connector. If enabled, Zscaler Client Connector attempts to configure Firefox automatically to follow Zscaler settings for macOS and Windows devices by enabling the "Use system proxy settings" feature in Firefox. If disabled, Zscaler Client Connector ignores Firefox and does not overwrite or create any configurations.

When enabled, Zscaler Client Connector overrides the Firefox proxy settings and prevents the user from changing them. Firefox integration does not support Mozilla Developer Preview or Firefox downloaded from the Microsoft Store.

To configure Firefox integration:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Endpoint Configuration**.
2.   On the **Endpoint Integration**tab, select **Enable Firefox Integration**.

![Image 2: Enable Firefox Integration option](https://help.zscaler.com/downloads/zclient-connector-endpoint-integration-enable-firefox-integration.png)

If you disable **Enable Firefox Integration**, ensure to add Zscaler proxy settings to your Firefox configuration.

3.   Click **Save**.

If you choose not to use Firefox integration for Zscaler Client Connector, then you must manually install the appropriate signing certificates from Firefox.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-zpa-group-users -->

Title: Enabling ZPA for a Group of Users | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/selective-entitlement-enabling-zpa-group-users

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling ZPA for a Group of Users

You can use Zscaler Service Entitlement to select which users can enroll into Zscaler Private Access (ZPA). Configuring ZPA using a small subset of users allows for testing before rolling out the service to all users.

To enable ZPA for only a select group of users, you must deploy Zscaler Client Connector 1.2.4 or later. However, earlier versions of Zscaler Client Connector always have ZPA enabled, regardless of the Zscaler Service Entitlement setting.

*   For ZPA instances created before July 2017, ZPA is enabled for all users by default. This preserves the existing behavior of ZPA enrollment prior to the addition of the selective entitlement feature.
*   For ZPA instances created after July 2017, ZPA is disabled by default. This allows you to determine when and how to provision ZPA for your users.

If you’re using device groups, the user must belong to both the device group and user group to avoid disconnecting ZPA services.

## Enabling ZPA for User Groups

To enable ZPA for a group of users:

1.   In the Zscaler Client Connector Portal, go to **Administration**.
2.   In the left menu, select **Zscaler Service Entitlement**.
3.   To enable ZPA for only a group of users, ensure that **ZPA Enabled by Default for User Tunnel**is disabled. If this setting is enabled, ZPA is available for all users and you cannot assign ZPA to a group.

**![Image 2: Configure setting for ZPA Enabled by Default for User Tunnel](https://help.zscaler.com/downloads/z-app/policy-administration-settings/zscaler-service-entitlement/selective-entitlement-enabling-zpa-group-users/client-connector-zpa-enabled-by-default.png)**

1.   Select a group of users from the drop-down menu and click **Done**. The default setting is **None**. This option means no groups have access to ZPA. This allows users to keep their current settings.

These groups are defined in the ZIA Admin Portal. If you do not see your groups, ensure that groups were synced to the Zscaler Client Connector Portal. To learn more, see [Syncing Directory Groups between the ZIA Admin Portal and App Portal](https://help.zscaler.com/zscaler-client-connector/syncing-directory-groups-between-zscaler-admin-portal-and-app-portal).

1.   Click **Save**.

This updates your users' devices the next time they connect. If they are already connected, the devices automatically update in 60 minutes. To manually update their devices, users can go into Zscaler Client Connector and click **Update Policy** from the **More** window. After manually refreshing the device, they must [reauthenticate on the **Private Access** page](https://help.zscaler.com/zpa/about-reauthPolicy#understandReauth).

## Possible Configurations for ZPA

The following table provides possible configurations for the Zscaler Service Entitlement feature and the resulting behavior of the ZPA service:

**ZPA Enabled by Default****Groups Specified****Behavior**
Enabled N/A ZPA service is enabled for all users
Disabled No ZPA service is not enabled for any users
Disabled Yes ZPA service is enabled only for the specified group of users

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys -->

Title: Zscaler Client Connector: Windows Registry Keys | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-registry-keys

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Zscaler Client Connector: Windows Registry Keys

You can navigate to Zscaler Client Connector registry keys by using the following path: `\HKEY_CURRENT_USER\Software\Zscaler\App`.

This table provides a list of possible values and their explanation for the registry key, `ZNW_State`**,**which represents Zscaler Client Connector's network state.

| Registry Key Values | Explanation |
| --- | --- |
| TRUSTED_VPN | The machine is connected to a trusted full tunnel VPN. |
| NON_TRUSTED | The machine is connected to a non-trusted network. |
| TRUSTED | The machine is connected to a trusted network. |

This table provides a list of possible values and their explanation for the registry keys, `ZPA_State`and`ZWS_State`which represents Private Access service state and Internet & SaaS service state, respectively.

| Registry Key Values | Explanation |
| --- | --- |
| OFF | Service is turned off. |
| ON | Service is turned on. |
| CONNECTING | Service is connecting. |
| NONE_FORWARDING | Service is in None Forwarding state. The traffic Interception is turned off. |
| TUNNEL_FORWARDING | Service is in Tunnel Forwarding state. Network drivers are being used to intercept traffic. |
| LOCAL_PROXY_FORWARDING | Service is in Local Proxy Forwarding state. PAC files are being used to intercept traffic. |
| ENFORCE_PROXY_FORWARDING | Service is in Enforce Proxy Forwarding state. A PAC file is being enforced on the system. |
| ADAPTER_DOWN_ERROR | Service is in Adapter Down Error state. Zscaler Client Connector is not able to find an adapter with a default route. This error may appear due to DHCP renewal. Run the "ipconfig /all" command and check for the lease obtained timestamp. Verify if the lease obtained timestamp coincides with the problem time. For example: Lease Obtained. . . . . . . . . . : Wednesday, 9 February 2022 2:59:42 |
| SERVICE_DOWN_ERROR | Service is in Service Down Error state. One of the microservices is not operational. |
| CAPTIVE_PORTAL_ERROR | Service is in Captive Down Error state. Captive portal has been detected on the system and the open timeout has expired. |
| SERVER_DOWN_ERROR | Service is in Server Down Error state. Zscaler Client Connector is not able to reach the Public Service Edge for Internet & SaaS or Public Service Edge for Private Access. |
| INTERNET_UNREACHABLE_ERROR | Service is in Internet Unreachable Error state. Network appears to be connected but Private Access is not able to resolve the broker name. This registry key value is applicable only for Private Access. |
| FIREWALL_BLOCK_ERROR | Service is in Firewall Block Error state. Zscaler Client Connector's attempt to create an outbound and/or inbound connection to itself failed. |
| SYSTEM_SOCKETS_EXHAUSTED_ERROR | Service is in Sockets Exhausted Error state. System's limit for maximum sockets has reached. |
| DRIVER_ERROR | Service is in Driver Error state. Zscaler Client Connector is not able to load the network driver (TAP/TUN/LWF). |
| CAPTIVE_PORTAL_FAILOPEN | Service is in Captive Portal FailOpen state. Zscaler Client Connector has detected a captive portal on the network and it has stopped traffic interception for some time to allow captive authentication. |
| PRE_ENROLMENT_PROXY_ENFORCEMENT | Service is in Local Captive Mode state. Strict enforcement has been configured and Zscaler Client Connector has blocked network access till the user logs in to Zscaler Client Connector. |
| SERVER_AUTH_ERROR | Service is in Server Auth Error state. Public Service Edge for Internet & SaaS or Public Service Edge for Private Access is not accepting the auth credentials sent by the client. |
| SERVER_AUTH_TERMINATED_AT_UNKNOWN | Service is in Chaining Authentication Error state. The realm of Public Service Edge for Internet & SaaS or Public Service Edge for Private Access and the logged-in user do not match. |
| ZPA_UNTRUSTED_SERVER_CERT_ERROR | Service is in Private Access Untrusted Server Error state. Private Access connection got an SSL exception while connecting. This registry key value is applicable only for Private Access. |
| ZPAAuth_STATE | Service is in disabled state. Private Access entitlement is removed. |

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed -->

Title: Locating Where Zscaler Client Connector is Installed on Your Device | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed

Markdown Content:
# Locating Where Zscaler Client Connector is Installed on Your Device | Zscaler

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
*   [End User Guide](https://help.zscaler.com/zscaler-client-connector/end-user-guide)
*    Locating Where Zscaler Client Connector is Installed on Your Device 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Locating Where Zscaler Client Connector is Installed on Your Device

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

When Zscaler Client Connector is installed on users' devices, Zscaler creates specific folders on the devices based on the OS running on the system:

*   [Windows](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#locate-win)

[](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed)When Zscaler Client Connector is installed on users' Windows devices, the following folders are installed.

    *   [Zscaler](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#zscaler)[](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed)![Image 6: The Zscaler folder on a Windows device](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/zscaler-app-user-guide-windows/where-zscaler-app-installed-windows-devices/zscaler_folder_for_windows_screenshot.png)

[Close](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#zscaler) 
    *   [Zscaler-Network-Adapter](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#z-network-adaptor)[](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed)![Image 7: The Zscaler-Network-Adapter folder on the Windows device](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/zscaler-app-user-guide-windows/where-zscaler-app-installed-windows-devices/zscaler-network-adapter_folder_screenshot.png)

[Close](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#z-network-adaptor) 

The following Windows services also appear in **Computer Management (Local)**>**Services and Applications**>**Services**.

    *   ZSAService: This is a management service for Zscaler Client Connector.
    *   ZSATunnel: This is a packet handling service responsible for tunneling traffic to the Zscaler service.
    *   ZSAUpdater: This is the Zscaler Client Connector Update service.

[See image.](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#z-windows-services)

[](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed)![Image 8: The Windows services for the Zscaler Client Connector](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/zscaler-app-user-guide-windows/where-zscaler-app-installed-windows-devices/windows_services_screenshot.png)

[Close](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#z-windows-services)

[Close](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#locate-win)

*   [macOS](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#locate-macos)[](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed)When Zscaler Client Connector is installed on users' macOS devices, you can find the Zscaler folder in their list of applications.

![Image 9: The Zscaler folder on a macOS device](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/zscaler-app-user-guide-macos/where-zscaler-app-installed-macos-devices/zscaler_folder_for_macos_screenshot.png)

[Close](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#locate-macos) 
*   [Linux](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#locate-linux)[](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed)When Zscaler Client Connector is installed on users' Linux devices, the /opt/zscaler directory is created.

[Close](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed#locate-linux) 

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector)[Locating Where Zscaler Client Connector is Installed on Your Device](https://help.zscaler.com/zscaler-client-connector/locating-where-zscaler-client-connector-installed)[Enrolling in the Zscaler Service on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-service-zscaler-client-connector)[Viewing Information About Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector)[Viewing Information About Private Access on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector)[Viewing Information About Internet Security on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-internet-security-zscaler-client-connector)[Viewing Information About Digital Experience on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-digital-experience-zscaler-client-connector)[Viewing Information About Zscaler Endpoint DLP on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-endpoint-dlp-zscaler-client-connector)[Viewing Notifications on Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector)[Zscaler Client Connector: Pop-Up Notifications](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-pop-notifications)[Self Service Notifications in Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/self-service-notifications-zscaler-client-connector)[Reporting an Issue with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector)[Using Zscaler Diagnostics](https://help.zscaler.com/zscaler-client-connector/using-zscaler-diagnostics)[Troubleshooting Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/troubleshooting-zscaler-client-connector)[Changing the Display Language for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/changing-display-language-zscaler-client-connector)[Adding a Partner Tenant to Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/adding-partner-tenant-zscaler-client-connector)[Verifying Access to Applications](https://help.zscaler.com/zscaler-client-connector/verifying-access-applications)[Deprovisioning Your Device from Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/deprovisioning-zscaler-client-connector)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels -->

Title: About Machine Tunnels | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Machine Tunnels

A machine tunnel allows a user's Windows or macOS device to establish a connection to a service before the user is logged in to Zscaler Client Connector. Zscaler Client Connector doesn't support machine tunnels for iOS, Linux, Android, and Android on ChromeOS.

*   Admins can enable **ZPA Machine Authentication** in [App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) to require users to authenticate against their IdP before the machine tunnel starts.
*   WebView2 authentication is not supported for Machine Tunnels.
*   Contact Zscaler Support to enable this feature for Zscaler Client Connector for macOS.

To use a machine tunnel, you must [configure Machines groups and Machine Provisioning keys](https://help.zscaler.com/zpa/configuring-machine-provisioning-keys) in the Zscaler Admin Console, add keys to Zscaler Client Connector app profile [rules for Windows](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles#windows) or [macOS](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#mac-auth), and [enable the Private Access machine tunnel for all](https://help.zscaler.com/zscaler-client-connector/selective-entitlement-configuring-zpa-machine-tunnel-all).

The Machine Tunnel page provides the following benefits and enables you to:

*   View a list of machines and details for your organization.
*   Search for a machine tunnel by hostname, filter by operating system and status, and export machine tunnel details as a CSV file.
*   View details for a machine tunnel and copy the token for each machine.
*   Remove machine tunnels from the Zscaler Admin Console.

## About the Machine Tunnel Page

On the Machine Tunnel page (Infrastructure > Connectors > Client > Machine Tunnel), you can do the following:

1.   Filter the list of enrolled devices with the following options:
    *   View machines for a specific operating system.
    *   View machines by status:
        *   **Active**:The default status. The machine tunnel is connected to Private Access before user login.
        *   **Inactive**: The machine tunnel is not connected to Private Access for various reasons, including the following:
            *   The machine tunnel was turned off by the admin and cannot establish a connection.
            *   The device was turned off or disconnected from the network.
            *   The machine tunnel failed to establish a connection because of connection issues or failed authentication.
            *   The machine tunnel was not enabled for the device in [Zscaler Service Entitlement](https://help.zscaler.com/zscaler-client-connector/about-zscaler-service-entitlement).

        *   **Removed:**The machine tunnel configuration was deleted or deactivated from the Zscaler Admin Console.
        *   **Unregistered:**The machine tunnel is no longer connected to Zscaler services or policies.

2.   Remove selected machine tunnels from the Zscaler Admin Console by either clicking **Delete** from the **Actions** drop-down menu or clicking the **Delete** icon.
3.   Search for a machine tunnel by **Hostname**, **Machine Tunnel Token**, **Hardware Fingerprint**, or **Zscaler Client Connector Version**.
4.   View a list of machines for your organization. For each machine tunnel, you can see:
    *   **Hostname**: The name of the machine.
    *   **OS Type**: The operating system of the machine.
    *   **Device Model**: The model of the machine.
    *   **Zscaler Client Connector Version**: The Zscaler Client Connector version installed on the machine.
    *   **Status**: The status of the machine tunnel.

5.   View the details for a machine tunnel. You can view and copy the token for each machine.
6.   Select machine tunnels for removal from the Zscaler Admin Console.
7.   Export the machine tunnel details as a CSV file for all items listed in the table, as determined by the filters.

![Image 2: Machine Tunnel](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-machine-tunnels/client-connector-about-machine-tunnels.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/enabling-browser-based-authentication -->

Title: Enabling Browser-Based Authentication | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/enabling-browser-based-authentication

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Enabling Browser-Based Authentication

If your organization uses advanced multi-factor authentication (MFA) for SAML, your users can authenticate using their browser instead of authenticating in Zscaler Client Connector. Zscaler Client Connector still manages traffic for Internet & SaaS and provides access to applications through Private Access.

Zscaler must enable this feature.

1.   Go to **Infrastructure**>**Connectors**>**Client**.
2.   Under Platform Settings, select the operating system and click the **Platform Settings** tab.
3.   Select an option based on the OS:
    *   **Windows, Linux, iOS, and Android**: Select **Browser-Based Authentication**.

![Image 2: Enable Browser Based Authentication](https://help.zscaler.com/downloads/zscaler-client-connector/platform-and-authentication-management/enabling-browser-based-authentication/zclient-connector-browser-based-auth.png)

    *   **macOS**: In the **Authentication Browser Type** drop-down menu, select one of the following options:

        *   **Default**: Authenticate with the default system-embedded browser that Zscaler Client Connector uses.
        *   **Browser Based Authentication**: Authenticate with a web browser. When selected, the **Use Default Browser** field appears. If enabled, Zscaler Client Connector uses the default web browser, and if disabled, Zscaler Client Connector uses Safari. Use this option when authenticating with a client certificate and a FIDO2 compliant key.
        *   **WebAuthn Popup Browser**: Authenticate with a restricted pop-up browser. When selected, the **Use Default Browser** field appears and is automatically enabled, so Zscaler Client Connector uses the default web browser. Use this option when authenticating with client certificates and FIDO2 compliant keys. Applies to Zscaler Client Connector version 4.7 and later for macOS and applies to Authentication Service only.

![Image 3: Enable Browser-Based Authentication macOS](https://help.zscaler.com/downloads/zscaler-client-connector/platform-and-authentication-management/enabling-browser-based-authentication/zclient-connector-browser-based-auth-macos-comm_0.png)

4.   Click **Save**.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/adding-api-key -->

Title: Adding an API Key | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/adding-api-key

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Adding an API Key

[Watch a video about API Key Management, including adding a new API key.](https://fast.wistia.net/embed/iframe/ohezo0fe9l)

An [API key](https://help.zscaler.com/zscaler-client-connector/about-public-api-management) is required for authenticating with Zscaler back-end servers to make API calls.

Using an API key is available for Zscaler Client Connector version 3.6 and later for Windows.

If you must obtain API keys or secrets to access [Zscaler OneAPI](https://help.zscaler.com/oneapi)endpoints, see [About API Clients](https://help.zscaler.com/zidentity/about-api-clients) in Authentication Service.

To add a new API key:

1.   Go to **Administration**>**Legacy API**>**Client Connector API**.
2.   Click **Add API Key**.

The **Add API Key** window appears.

3.   In the **Add API Key** window:

    *   **Name**: Enter a name for the API key. The name must be alphanumeric, cannot contain spaces, and has a maximum of 50 characters.
    *   **Status**: Make sure **Enabled** is selected. If **Disabled** is selected, the key is unavailable to use. By default, this option is enabled.
    *   **Role**: Select **Read** or **Write** access for the key.
    *   **Session Validity Interval (In Seconds)**: Enter the amount of time the key is available to use. Enter a range between 30 and 999,999,999 seconds.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-api-key#add1)

4.   Click **Save**. The client secret for the key is displayed.
5.   Copy the client secret to your clipboard. You need it for authentication.

[See image.](https://help.zscaler.com/zscaler-client-connector/adding-api-key#add2)

The client secret is only available to copy when creating an API key. It is not available to access in the Zscaler Admin Console after you close the window, so store it in a secure location.

6.   When finished, click **Cancel**to close the window.

---

<!-- source: https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia -->

Title: Deploying and Managing Zscaler Client Connector for Internet & SaaS | Zscaler

URL Source: https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia

Markdown Content:
# Deploying and Managing Zscaler Client Connector for Internet & SaaS | Zscaler

[![Image 2: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

Internet & SaaS (ZIA)

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

*   [Internet & SaaS (ZIA) Help](https://help.zscaler.com/zia)
*   [Policies](https://help.zscaler.com/zia/policies)
*   [Mobile Security](https://help.zscaler.com/zia/policies/mobile-security)
*   [Zscaler Client Connector Deployment](https://help.zscaler.com/zia/policies/mobile-security/zscaler-client-connector-deployment)
*    Deploying and Managing Zscaler Client Connector for Internet & SaaS 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zia.svg](https://help.zscaler.com/assets/images/icon-zia-hNrDwZ7t.svg)

# Internet & SaaS (ZIA)

### Deploying and Managing Zscaler Client Connector for Internet & SaaS

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

To properly deploy and manage Zscaler Client Connector for the Internet & SaaS (ZIA) service, you must complete the following tasks in the Zscaler Admin Console:

*   [Step 1: Manage Admin Access to the Zscaler Client Connector Portal](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#manage-admin-access)[](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia)Manage admin access to the Zscaler Client Connector Portal using role-based administration. To learn more, see [Adding Admin Roles](https://help.zscaler.com/zia/adding-admin-roles).

[Close](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#manage-admin-access) 
*   [Step 2: Provision and Authenticate Users](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#provision-authenticate-users)[](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia)To use the Zscaler service, you must have an authentication mechanism configured and users provisioned on the Internet & SaaS service. To learn more, see [Understanding User Provisioning and Authentication](https://help.zscaler.com/zia/understanding-provisioning-authenticating-users).

If you do not have an authentication mechanism installed, you must use the Zscaler Client Connector Portal as your [identity provider (IdP)](https://help.zscaler.com/zia/about-identity-providers) to provision and authenticate users. To learn more, see [Using the Zscaler Admin Console as an Identity Provider](https://help.zscaler.com/zscaler-client-connector/using-zscaler-admin-console-identity-provider).

[Close](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#provision-authenticate-users) 

Optionally, you can complete the following tasks when deploying or managing Zscaler Client Connector for Internet & SaaS:

*   [Configure SSL/TLS Inspection](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#configure-SSL)[](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia)In order to enable [SSL/TLS Inspection](https://help.zscaler.com/zia/understanding-ssltls-inspection) for Zscaler Client Connector, you must first enable the feature in the Zscaler Admin Console for each relevant platform. To learn more, see [Configuring SSL Inspection for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector).

[Close](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#configure-SSL) 
*   [Create Groups](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#create-groups)[](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia)For Zscaler Client Connector, you can apply certain policies or settings to specific user groups. To learn more, see [About User Groups](https://help.zscaler.com/authentication-service/about-user-groups).

[Close](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#create-groups) 
*   [Create and Host PAC Files](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#create-host-PAC-files)[](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia)You can use PAC files in the [Zscaler Client Connector profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles) and [forwarding profiles](https://help.zscaler.com/zscaler-client-connector/about-forwarding-profiles) to determine how to direct your organization’s traffic.

To create custom PAC files for these profiles, Zscaler highly recommends that you use the Zscaler Admin Console default PAC file and customize it as necessary. After you create a custom PAC file, you can [host it in the Zscaler Admin Console](https://help.zscaler.com/zia/about-hosted-pac-files).

To learn more, see [Best Practices for Using PAC Files with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector) and [Writing a PAC File](https://help.zscaler.com/zia/writing-pac-file).

[Close](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia#create-host-PAC-files) 

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Article

[Deploying and Managing Zscaler Client Connector for Internet & SaaS](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2024 -->

Title: Release Upgrade Summary (2024) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/release-upgrade-summary-2024

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Release Upgrade Summary (2024)

This article provides a summary of all new features and enhancements per Zscaler cloud for the Zscaler Client Connector Portal. Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com/).

**Select your Zscaler cloud:**

**Select a deployment date:**

* * *

The following service updates were deployed to mobile.zscaler.net on the following dates.

**December 06, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.3.1**

        *   Enhances a more user-friendly selection for users and user groups in app profiles. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Fixes an issue where customers experienced an error when modifying a forwarding profile in Zscaler Client Connector Portal.

**October 18, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.3**

        *   Includes the Last Seen Connected to ZPA service status option for ZPA-only customers. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Fixes an issue where customers could not upload more than a 10KB client certificate.
        *   Fixes an issue where the search box on the Add Windows Policy page refreshed after each letter input, causing the user to repeatedly click the text box to continue typing.
        *   Fixes an issue where the remote host contained a Spring Framework version that was affected by an open redirect vulnerability (CVE-2024-22243).
        *   Fixes an issue where some of the default filter settings on the Zscaler Client Connector Portal dashboard appeared inconsistent in the updated UI.
        *   Fixes an issue where the Enable Notifications by Default notification showed incorrect behavior.
        *   Fixes an issue where a request for the list of downloaded devices using public APIs returned an invalid response.
        *   Fixes an issue where users received the "MobileAuthentication token is not valid" error in some scenarios.
        *   Fixes an issue where switching from T2 DTLS to TLS transport mode or vice versa in the forwarding profile resets all previously selected options.
        *   Fixes an issue where the Tunnel Internal Traffic option displayed for iOS and Android users, although not currently supported in Zscaler Client Connector.
        *   Fixes an issue where the Zscaler Digital Experience (ZDX) service was enabled on Zscaler Client Connector for some users although ZDX entitlement was disabled in the Zscaler Client Connector Portal.
        *   Fixes an issue where admins couldn’t see domain bypasses in the app profiles under the audit logs section of the Zscaler Client Connector Portal.

**August 30, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.2.1**

        *   Adds the ability in Zscaler Client Connector Portal for ZPA-only customers to remove older devices after user enrollment from the ZPA Admin Portal. To learn more, see [Configuring Automated Device Cleanup](https://help.zscaler.com/zscaler-client-connector/configuring-automated-device-cleanup).
        *   Includes support for the new reskinned Zscaler Client Connector Portal UI.
        *   Includes a device limit per user for ZPA-only customers for the removal of older devices from the ZPA Admin Portal. To learn more, see [Configuring Automated Device Cleanup](https://help.zscaler.com/zscaler-client-connector/configuring-automated-device-cleanup).
        *   Fixes an issue where the Enable Deception option was disabled for customers.
        *   Fixes an issue where some of the default filter settings on the Zscaler Client Connector Portal dashboard appeared inconsistent in the new UI.
        *   Fixes an issue where responses for keepalive requests were delayed in some scenarios.
        *   Fixes an issue where the lastActivatedTimestamp was not set when a build was enabled from the Zscaler Client Connector Portal.
        *   Fixes an issue where customers weren’t able to view the Active Tunnel SDK version in the Zscaler Client Connector Portal.
        *   Fixes an issue where customers were unable to modify an app profile policy in some scenarios.
        *   Fixes an issue where admins were unable to modify an app profile in some scenarios.
        *   Fixes an issue where the .CSV file export from Enrolled Devices didn’t contain all the device information.
        *   Fixes an issue where notifications did not display in Zscaler Client Connector version 4.4.0.300.
        *   Fixes an issue where the complete users list did not display in the app profile after customers migrated their identity provider (IdP) from Microsoft Active Directory (AD) to Azure Active Directory (Azure AD).
        *   Fixes an issue where Zscaler Client Connector Portal displayed an incorrect last configuration download time.
        *   Removes the OneID Admins tab in Zscaler Client Connector Portal.
        *   Fixes an issue where the ZDX service was enabled on Zscaler Client Connector for some users even when ZDX entitlement was disabled in the Zscaler Client Connector Portal.
        *   Fixes an issue where machine tunnel users weren’t displayed in the Zscaler Client Connector Portal under Enrolled Devices.
        *   Fixes an issue where a non-user friendly error displayed on the Device Posture page instead of HTTP status code 503.
        *   Fixes an issue where all other versions were disabled when Zscaler Client Connector version 4.4.0.309 was enabled.
        *   Fixes an issue where users ran into the 'MobileAuthentication token is not valid' error in some scenarios.
        *   Fixes an issue where switching from T2 DTLS to TLS transport mode or vice versa in the forwarding profile reset all previously selected options.
        *   Fixes an issue where admins couldn’t see domain bypasses in the app profiles under the audit logs section of the Zscaler Client Connector Portal.

**July 19, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.2**

        *   Adds the installation type to the enrolled devices that indicates if the user’s device is deployed through strict enforcement or general deployment. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Allows admins to upload CSV files in the VPN Gateway Bypass section of App Profiles. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Adds the ability to provision ZDX License for an existing ZPA-only customer without service interruption or the need to enable device groups in the Zscaler Client Connector Portal. To learn more, see [Enabling ZDX for all Users](https://help.zscaler.com/zscaler-client-connector/enabling-zdx-all-users).
        *   Adds the Perform CRL Check toggle to the Server Validated Client Certificate Posture. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Fixes an issue where the ZDX service was enabled on Zscaler Client Connector for some users even when ZDX entitlement was disabled in the Zscaler Client Connector Portal.
        *   Fixes an issue where admins were unable to remove the users from the Enrolled Device.
        *   Fixes an issue where the version distribution section of the Zscaler Client Connector Portal dashboard displayed no data for the unsupported version.
        *   Fixes an issue where a customer experienced an error when modifying a forwarding profile in the Zscaler Client Connector Portal.
        *   Adds an Ignore Client Cert errors for Webview2 option that allows Zscaler Client Connector to ignore client certificate errors and continue authentication using WebView2 with invalid or no certificates, when enabled. [To learn more, see Using WebView2 Authentication](https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication).

**June 14, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.1.2**

        *   Fixes an issue when adding device groups to App Profiles, Zscaler Client Connector version 4.3 and earlier for Windows were also included in the device groups.

Previously added Windows device groups in App Profiles are available only for Zscaler Client Connector version 4.3 and later for Windows devices. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles-windows#groups).

**May 03, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.1.1**

        *   Allows admins to access and authenticate Zscaler Client Connector Portal via ZIdentity. To learn more, see [Accessing and Navigating the Zscaler Client Connector Portal](https://help.zscaler.com/zscaler-client-connector/accessing-and-navigating-zscaler-client-connector-portal#SubscribedtoZSLogin).
        *   Fixes an issue when adding device groups to App Profiles, Zscaler Client Connector version 4.3 and earlier for Windows were also included in the device groups.

Previously added Windows device groups in App Profiles are available only for Zscaler Client Connector version 4.3 and later for Windows devices. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles-windows#groups).

        *   Fixes an issue where devices using various Zscaler Client Connector versions for Android (e.g., 1.13, 1.14, 1.15) didn’t appear in the Registered Devices list in the Zscaler Client Connector Portal. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Fixes a partner login failure error that indicated that a partner existed with the same domain.

Unregistered partner devices are removed if inactive in the previous 24 hours, allowing the same user or a new user to log in. To learn more, see [Viewing Device Fingerprint Information for a Partner Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-partner-device).

        *   Fixes an issue where Zscaler Client Connector didn't detect Cisco AnyConnect as a VPN interface.
        *   Fixes an issue where device posture thumbprint didn’t get updated without the user manually updating the policy on Zscaler Client Connector.
        *   Fixes an issue where admins couldn’t filter a device by hardware fingerprint on the enrolled device section of the Zscaler Client Connector Portal. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Fixes an issue where customers upgrading to Zscaler Client Connector version 3.7 for iOS experienced an error 3068 indicating that the Zscaler service (ZIA, ZPA, ZDX, Deception) was disabled. To learn more, see [Zscaler Client Connector Errors](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-errors#error_3068).

**April 05, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.1**

Includes a new user interface of the Zscaler Client Connector Portal. Contact Zscaler Support to enable this new UI for your organization. To view the new user interface, on the upper-right of the Zscaler Client Connector Portal page, click the **Try New UI** icon (![Image 2](https://help.zscaler.com/downloads/client-connector/zscaler-client-connector-profile-management/configuring-zscaler-client-connector-profiles/Try-new-UI-icon-50px.png)).

        *   Supports the Custom IP-Based App Bypass feature's configuration to be enabled per OS platform.

The Custom IP-Based App Bypass feature in App Profiles is currently enabled for the Windows platform only.

        *   Supports Disable and Logout passwords through public API. To learn more, see [API Developer & Reference Guide](https://help.zscaler.com/zscaler-client-connector/public-api-controller).
        *   Supports read-only access to the Zscaler Client Connector Portal during an upgrade. To learn more, see [Accessing and Navigating the Zscaler Client Connector Portal](https://help.zscaler.com/zscaler-client-connector/accessing-and-navigating-zscaler-client-connector-portal).
        *   Fixes an authentication error when logging via SSO into the Zscaler Client Connector Portal.
        *   Fixes an issue where when updating a forwarding profile in the Zscaler Client Connector Portal with Pre-defined Trusted Networks, the change configuration in the audit logs didn’t show the existing configuration. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Fixes an issue where customers accessing partner tenants experienced an error stating that the partner name already existed with the same domain.
        *   Fixes an issue where, in the Enrolled Devices, the Last Seen Connected to ZIA column was visible to ZPA-only customers.
        *   Fixes an issue where the Zscaler Client Connector Portal stays in a Sync in Progress status when synchronizing ZPA admins under the Administration section.
        *   Includes Authentication Service integration for the following features: Strict Enforcement mode, Endpoint Data Loss Prevention (DLP), machine tunnel authentication, support for device groups and service entitlement, dynamic service enrollment, and using Authentication Service to authenticate device tokens created in Zscaler Client Connector. To learn more, see [What is Authentication Service?](https://help.zscaler.com/zidentity/what-zidentity)
        *   Supports using the Enable Anti-tampering setting from the app profile regardless of how the anti-tampering option was originally set during installation. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Updates Zscaler Client Connector to require users to reauthenticate ZPA if they log in to Windows with fast startup enabled. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Allows Zscaler Client Connector to block all inbound traffic for various network types with an option to exclude one or more ports. To learn more, see Configuring Zscaler Client Connector App Profiles.
        *   Adds an option to have WebView2 not follow the system proxy and instead connect directly to the internet. To learn more, see [Enabling WebView2 Authentication](https://help.zscaler.com/zscaler-client-connector/enabling-webview-2.0-authentication).
        *   Updates Zscaler Client Connector for macOS devices for the Detect Carbon Black, Detect CrowdStrike, and Detect SentinelOne [device posture types](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles) so that the posture check passes if the security product is installed and the process is running even if a matching thumbprint is not detected.
        *   Adds the ability to view macOS device serial numbers in the Registration Details section of the Zscaler Client Connector Portal.

**February 09, 2024**

*   Release Available

    *   ### **Zscaler Client Connector Portal 4.0.2**

        *   Allows admins to enable password and token obfuscation in the Zscaler Client Connector Portal. To learn more, see [Adding Roles](https://help.zscaler.com/zscaler-client-connector/adding-roles).
        *   Includes support for the verbose logging feature for Android that enables extra debug logs in the Zscaler Client Connector logs. To learn more, see [Configuring User Access to Logging Controls for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-logging-controls-zscaler-client-connector).
        *   Includes support for enabling SSO for WebView 2.0 for Windows customers. To learn more see, [Enabling WebView 2.0 Authentication](https://help.zscaler.com/zscaler-client-connector/enabling-webview-2.0-authentication).
        *   Removes the Last Seen Connected to ZIA service status option for ZPA-only customers. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Fixes an issue where older and unused versions of Zscaler Client Connector appeared in the Registered Device Details section after they were removed. To learn more, see [Viewing Device Fingerprint for an Enrolled Device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device).
        *   Fixes an issue where the Admin ID was missing in the audit logs in the Zscaler Client Connector Portal. To learn more, see [About Audit Logs](https://help.zscaler.com/zscaler-client-connector/about-audit-logs).
        *   Adds a new posture type, Server Validated Client Certificate, that checks the client certificate against the cloud rather than only checking locally. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Fixes an issue where the user experienced an invalid request error when adding a domain name or an IP address in the Hostname or IP Address Bypass for VPN Gateway setting in the app profiles. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Fixes an issue where admins couldn’t see a full list of the groups in the User Groups drop-down menu when configuring user groups. Zscaler Client Connector Portal shows a list of 100 groups. To see a complete list of groups, click load more. To learn more about user groups, see [Configuring an App Update in the Zscaler Client Connector App Store](https://help.zscaler.com/zscaler-client-connector/configuring-app-update-zscaler-client-connector-app-store#configure-auto-update).
        *   Fixes an issue where ZPA super admins’ access was restricted to read-only for ZPA-only accounts.
        *   Fixes an issue where customers experienced an internal error when enrolling a new device in Zscaler Client Connector.
        *   Fixes an issue where admins couldn’t add a PAC server IP address to the Custom PAC URL section in the app profiles.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2024 -->

Title: Client Connector App Release Summary (2024) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2024

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Client Connector App Release Summary (2024)

**Select an OS:**

**Select an app version:**

**Select a deployment date:**

* * *

The Client Connector app versions for Windows listed below were deployed on the following dates.

**December 20, 2024**

*   Release Available: Client Connector 4.5.0.352 for Windows

    *   ### **Zscaler Client Connector 4.5.0.352 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434.

        *   Fixes an issue where Zscaler Client Connector did not always clear the DNS suffixes when moving from a machine tunnel to a user tunnel.
        *   Fixes an issue where the ZSATray process could block another application, such as PowerBuilder, from starting.
        *   Fixes a driver error that occurred when upgrading Zscaler Client Connector for customers with anti-tampering already or previously enabled.
        *   Fixes an issue where Zscaler Client Connector did not switch to the correct Network Type on a device using a USB Ethernet adapter.
        *   Fixes an issue where a Detect Antivirus device posture check did not pass, even though the antivirus specified in the antivirus name (AV Name) was running on the system.
        *   Fixes an issue where Zscaler Client Connector prevented removing a device from isolation through the Microsoft Defender portal with Microsoft Intune.
        *   Fixes an issue where network transfer data could be duplicated (e.g., print jobs, file transfers) after upgrading to Zscaler Client Connector version 4.5.0.337 for Windows.
        *   Fixes an issue where Zscaler Client Connector did not fall back to the redirect broker (any.broker) when switching from an Off Trusted Network to a Trusted Network.
        *   Fixes an issue where Zscaler Client Connector did not correctly close an existing Zscaler Private Access (ZPA) app session when in the ZPA forced reauthentication state.

**December 19, 2024**

*   Release Available: Client Connector 4.4.0.389 for Windows

    *   ### **Zscaler Client Connector 4.4.0.389 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector did not always clear the DNS suffixes when moving from a machine tunnel to a user tunnel.
        *   Fixes an issue where the ZSATray process could block another application, such as PowerBuilder, from starting.
        *   Fixes a driver error that occurred when upgrading Zscaler Client Connector for customers with anti-tampering already or previously enabled.
        *   Fixes an issue where Zscaler Client Connector did not switch to the correct Network Type on a device using a USB Ethernet adapter.
        *   Fixes an issue where Zscaler Client Connector prevented removing a device from isolation through the Microsoft Defender portal with Microsoft Intune.
        *   Fixes an issue where Zscaler Client Connector did not fall back to the redirect broker (any.broker) when switching from an Off Trusted Network to a Trusted Network.
        *   Fixes an issue where Zscaler Client Connector did not correctly close an existing Zscaler Private Access (ZPA) app session when in the ZPA forced reauthentication state.

**December 18, 2024**

*   Release Available: Client Connector 4.3.0.269 for Windows

    *   ### **Zscaler Client Connector 4.3.0.269 Enhancements and Fixes**

        *   Fixes a driver error that occurred when upgrading Zscaler Client Connector for customers with anti-tampering already or previously enabled.
        *   Fixes an issue where Zscaler Client Connector prevented removing a device from isolation through the Microsoft Defender portal with Microsoft Intune.
        *   Fixes an issue where Zscaler Client Connector did not fall back to the redirect broker (any.broker) when switching from an Off Trusted Network to a Trusted Network.

**December 11, 2024**

*   Release in Limited Availability: Client Connector 4.6 for Windows

    *   ### **Zscaler Client Connector 4.6 Enhancements and Fixes**

Zscaler Client Connector version 4.6.0.123 has a known issue where users in a dual stack network or an IPv6-only network intermittently might be unable to access Zscaler Private Access (ZPA) applications after the device wakes up from sleep mode. Users can resolve the issue by clicking Restart Service on the More window. The issue will be corrected in an upcoming refresh release.

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.6.0.216. 
        *   Adds support for VPN (for Legacy Apps) that provides secure access if you are using call centers, network-connected applications, or server-to-client applications. This service uses [Network Connectors](https://help.zscaler.com/zpa/about-network-connectors) and [VPN Service Edges](https://help.zscaler.com/zpa/about-vpn-service-edges) to create a secure connection with Zscaler Client Connector. The Network Connectors and VPN Service Edges are [monitored and managed by Zscaler](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-private-access-zscaler-client-connector).
        *   Supports [Business Continuity](https://help.zscaler.com/zscaler-client-connector/about-business-continuity) in Zscaler Private Access (ZPA) which ensures continued access to applications for users in events where the reachability or availability of the ZPA cloud is affected.
        *   Adds [fail-open settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) to the app profile that can override the global fail-open settings.
        *   Supports using a [fail-close policy](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) when Zscaler Client Connector is in Strict Enforcement mode and is in a fail-close state.
        *   Supports [partner login settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) on the app profile that can override the global ZPA Partner Logins settings (including entering specific partner domains that you can select on the app) and apply a [device posture to partner tenants](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Adds support for the collection of [Health Metrics](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) on devices to allow for diagnostics and proactively identify issues.
        *   Adds the ability to [automatically resize the Zscaler Client Connector window](https://help.zscaler.com/zscaler-client-connector/about-platform-settings) during authentication to fit the IdP page.
        *   Adds the ability to display a [custom notification](https://help.zscaler.com/zscaler-client-connector/configuring-fail-open-settings-zscaler-client-connector) to users who attempt to access the internet when in Strict Enforcement mode.
        *   Enhances the CrowdStrike ZTA posture by introducing two new [device posture checks](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles): CrowdStrike ZTA Device OS Score and CrowdStrike ZTA Sensor Setting Score.
        *   Adds the ability to [create a custom banner](https://help.zscaler.com/zscaler-client-connector/configuring-custom-banner) with information about your organization and display it on the app.
        *   Adds enhancements to the stored configuration files used when [reverting Zscaler Client Connector to a previous version](https://help.zscaler.com/zscaler-client-connector/reverting-zscaler-client-connector).
        *   Supports the ability to notify end users of cloud DLP notifications on the endpoint if [Endpoint Data Loss Prevention (DLP)](https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-prevention-dlp-integration-zscaler-client-connector) notifications are enabled.
        *   Supports Egress IP as a [condition for trusted network criteria](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Adds enhancements to the Report an Issue form by supporting specific file types (image, document, and text files) and increasing the attachment size cap from 1 MB to 5 MB.
        *   Supports step-up authentication for conditional access to ZPA based on dynamic requests for stronger authentication for Authentication Service-enabled tenants.
        *   Updates the [forwarding profile settings](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector) to drop IPv6 packets from IPv6-only networks, and renames the Drop IPv6 Packets option that drops IPv6 packets in dual stack networks.
        *   Improves Zscaler Tunnel (Z-Tunnel) 2.0 session handling and reconnection logic after the device wakes up from sleep mode.
        *   Supports the [Dynamic ZIA Service Edge Assignment feature](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector) with ZIA Private Service Edges and ZIA Virtual Service Edges in addition to ZIA Public Service Edges.
        *   Updates third-party libraries for security fixes: upgrades the V8 engine used for the PAC parser to version 13.0, Npcap to version 1.80, and OpenSSL to version 1.0.2zk.
        *   Improves DNS request handling to reduce requests, including adding the ability to [prioritize domain exclusions over IP inclusions](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) when determining whether a DNS request should bypass Z-Tunnel 2.0.
        *   Supports [forcing ZPA authentication to expire](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) after a Windows user has been locked on a device for a specified number of minutes.
        *   Improves the startup sequence to reduce the time before Zscaler Client Connector begins traffic interception.
        *   Adds the ability to [specify a registry key to locate the PAC URL on forwarding profiles](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector) using the Enforce and None modes.
        *   Supports using [additional IdP domains if you use WebView2 authentication](https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication) with an Imprivata integration and the user login domain is different from the IdP domain.
        *   Updates the [Show all notifications toggle on the More window](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector) in the app to be Show notification pop-ups.
        *   Updates Zscaler Client Connector to try only ports 443 and 80 and not try 8080 if it can’t connect to either a [configured dedicated proxy port](https://help.zscaler.com/zscaler-client-connector/configuring-dedicated-proxy-ports) or the [app profile custom PAC file port](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#pac-proxy) used when the Use Preferred Port from PAC for Z-Tunnel 1.0 option or Use Preferred Port from PAC for Z-Tunnel 2.0 option is enabled.
        *   Fixes an issue where Zscaler Client Connector failed to launch when the user skipped completion of Autopilot setup during Microsoft Autopilot deployment.
        *   Fixes an issue where Zscaler Client Connector pushed a proxy PAC file even though proxy settings were set to Never.
        *   Fixes an issue where users could not connect to a remote desktop protocol (RDP) related to the Windows domain network profile detection.
        *   Fixes an issue where Zscaler Client Connector intermittently did not follow IP-based Z-Tunnel 2.0 inclusion and exclusion rules.
        *   Fixes an issue where connections made while Zscaler Client Connector was in a fail-open state due to captive portal detection were still available even after the Zscaler Client Connector reenabled its services.
        *   Fixes an issue where a persistent pop-up notification lost focus when a new window was opened, resulting in new notifications not displaying.
        *   Fixes an issue where an update to the Show ZPA Reauthentication Notification in Every (In Minutes) value in the Zscaler Client Connector Portal did not immediately take effect after updating the policy manually in the app.
        *   Fixes a decryption issue with machine tunnels when Zscaler Client Connector was installed using Microsoft AutoPilot.
        *   Fixes an issue where ZPA did not reauthenticate automatically due to a WebView2 initialization issue resulting from resuming a Windows device from Modern Standby.
        *   Fixes an issue where Zscaler Client Connector did not bypass mobilesupport.zscaler.com when fetching logs from the Zscaler Client Connector Portal even though support access was not enabled for users.
        *   Fixes an issue where the internet usage policy page for a captive portal did not load properly after clicking Open Browser in Zscaler Client Connector.
        *   Fixes an issue where Zscaler Client Connector did not detect a Trusted Network when sending the DNS request through the default adapter.
        *   Fixes an issue where Zscaler Client Connector did not always clear the DNS suffixes when moving from a machine tunnel to a user tunnel.
        *   Fixes an issue where service status pop-up notifications indicating that Internet Security or Private Access is enabled or disabled displayed even though service status notifications were disabled.
        *   Fixes an issue where Zscaler Client Connector resolved a valid fully qualified domain name (FQDN) DNS search domain in a ZPA application segment as a non-existent domain (NXDOMAIN).
        *   Fixes an issue where Zscaler Client Connector did not recognize the Windows proxy settings during authentication.
        *   Fixes an issue where customers experienced DNS resolution errors on applications after Multimatch and App Scaling were enabled.
        *   Fixes an issue where users could not log in to Zscaler Client Connector if they were subscribed to a Authentication Service-enabled tenant and Posture Based App Profile was enabled but there were no device postures configured.
        *   Fixes an issue where Strict Enforcement was not enabled after installing Zscaler Client Connector for a Authentication Service-enabled tenant.
        *   Fixes an issue where the ZSATray process could block another application, such as PowerBuilder, from starting.
        *   Fixes an authentication issue in Zscaler Client Connector caused by a hardware fingerprint change after a reboot of the client machine.
        *   Fixes an issue where the Ethernet adapter was disabled after a device was rebooted or powered up if Zscaler Client Connector was configured to block domain profile detection.
        *   Fixes an issue where Zscaler Client Connector did not clear the Windows Firewall Protection filter after Trigger Domain Profile Detection was disabled.
        *   Fixes an issue where Zscaler Client Connector did not switch to a Split VPN Trusted Network after a VPN connection was established.
        *   Fixes an internal socket problem error that occurred with certain malformed UDP datagrams after starting applications such as Microsoft PowerPoint using the empower add-in.
        *   Fixes an issue where Zscaler Client Connector did not switch to the correct Network Type on a device using a USB Ethernet adapter.
        *   Fixes an issue where users with non-English OS devices were prompted to log in to Zscaler Client Connector after a device restart.
        *   Fixes an issue where Zscaler Client Connector connected to the default proxy port instead of the custom port specified in the PAC file.
        *   Fixes an issue where a Detect Antivirus device posture check did not pass, even though the antivirus specified in the antivirus name (AV Name) was running on the system.
        *   Fixes an issue where printing resulted in duplicate print jobs.
        *   Fixes an issue where Zscaler Client Connector did not fall back to the redirect broker (any.broker) when switching from an Off Trusted Network to a Trusted Network.
        *   Fixes an issue where Windows did not set the domain profile correctly after switching from a domain-joined network to a VPN Trusted Network when using a ZPA forwarding profile for VPN Trusted Network set to None.
        *   Fixes an issue where Zscaler Client Connector prevented removing a device from isolation through the Microsoft Defender portal with Microsoft Intune.
        *   Fixes a driver error that occurred when upgrading Zscaler Client Connector for customers with anti-tampering already or previously enabled.

**November 26, 2024**

*   Release Available: Client Connector 4.3.0.264 for Windows

    *   ### **Zscaler Client Connector 4.3.0.264 Enhancements and Fixes**

Fixes an issue where Zscaler Client Connector used the default IP range for the machine tunnel even though the Zscaler Client Connector Synthetic IP Range was set.

**November 20, 2024**

*   Release Available: Client Connector 4.4.0.383 for Windows

    *   ### **Zscaler Client Connector 4.4.0.383 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector used the default IP range for the machine tunnel even though the Zscaler Client Connector Synthetic IP Range was set.
        *   Fixes an issue where Zscaler Client Connector did not switch to a Split VPN Trusted Network after a VPN connection was established.
        *   Fixes an issue where users with non-English OS devices were prompted to log in to Zscaler Client Connector after a device restart.

**November 18, 2024**

*   Release Available: Client Connector 4.5.0.344 for Windows

    *   ### **Zscaler Client Connector 4.5.0.344 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434.

        *   Corrected errors in the translated text included with French localization.
        *   Fixes an issue where Zscaler Client Connector did not recognize the Windows proxy settings during authentication.
        *   Fixes an issue where Zscaler Client Connector used the default IP range for the machine tunnel even though the Zscaler Client Connector Synthetic IP Range was set.
        *   Fixes an issue where the Ethernet adapter was disabled after a device was rebooted or powered up if Zscaler Client Connector was configured to block domain profile detection.
        *   Fixes an issue where Zscaler Client Connector did not switch to a Split VPN Trusted Network after a VPN connection was established.
        *   Fixes an internal socket problem error that occurred with certain malformed UDP datagrams after starting applications such as Microsoft PowerPoint using the Empower add-in.
        *   Fixes an issue where users with non-English OS devices were prompted to log in to Zscaler Client Connector after a device restart.

**October 28, 2024**

*   Release Available: Client Connector 4.5.0.337 for Windows

    *   ### **Zscaler Client Connector 4.5.0.337 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434.

        *   Fixes an issue where anti-tampering protection could be bypassed by tampering with a specific action performed by a trusted Windows process.
        *   Fixes an issue where Zscaler Client Connector did not load in a non-persistent VDI (virtual desktop infrastructure) where the user's roaming profile was mapped to an internal network share, causing network delays in certain file operations.
        *   Fixes an FW/AV error that could occur when using Microsoft eDiscovery.
        *   Fixes an issue where Zscaler Client Connector did not detect a Trusted Network when sending the DNS request through the default adapter.
        *   Fixes an issue where service status pop-up notifications indicating that Internet Security or Private Access is enabled or disabled displayed even though service status notifications were disabled.
        *   Fixes an internal error (302) that occurred if a Zscaler Private Access (ZPA) reauthentication session was interrupted by another reauthentication session initiated by the user through the Authenticate button from the system tray icon.
        *   Fixes an issue where customers experienced DNS resolution errors on applications after Multimatch and App Scaling were enabled.
        *   Fixes an issue where the ZSATunnel crashed and did not recover after switching from an Off Trusted Network to a VPN Trusted Network with a forwarding profile of None.
        *   Fixes a Machine Tunnel authentication error that occurred after the signing certificate expired for devices that were cloned with Zscaler Client Connector already installed.
        *   Fixes an issue where strict enforcement was not enabled after installing Zscaler Client Connector for a Authentication Service-enabled tenant.
        *   Fixes an issue where ZSATrayManager crashed after updating Zscaler Client Connector when there were a large number of environment variables.

Release Available: Client Connector 4.4.0.379 for Windows

    *   ### **Zscaler Client Connector 4.4.0.379 Enhancements and Fixes**

        *   Fixes an issue where anti-tampering protection could be bypassed by tampering with a specific action performed by a trusted Windows process.
        *   Fixes an issue where Zscaler Client Connector did not load in a non-persistent VDI (virtual desktop infrastructure) where the user's roaming profile was mapped to an internal network share, causing network delays in certain file operations.
        *   Fixes an FW/AV error that could occur when using Microsoft eDiscovery.
        *   Fixes an issue where Zscaler Client Connector did not detect a Trusted Network when sending the DNS request through the default adapter.
        *   Fixes an issue where service status pop-up notifications indicating that Internet Security or Private Access is enabled or disabled displayed even though service status notifications were disabled.
        *   Fixes an internal error (302) that occurred if a Zscaler Private Access (ZPA) reauthentication session was interrupted by another reauthentication session initiated by the user through the Authenticate button from the system tray icon.
        *   Fixes an issue where customers experienced DNS resolution errors on applications after Multimatch and App Scaling were enabled.
        *   Fixes an issue where the ZSATunnel crashed and did not recover after switching from an Off Trusted Network to a VPN Trusted Network with a forwarding profile of None.
        *   Fixes a Machine Tunnel authentication error that occurred after the signing certificate expired for devices that were cloned with Zscaler Client Connector already installed.
        *   Fixes an issue where ZSATrayManager crashed after updating Zscaler Client Connector when there were a large number of environment variables.
        *   Fixes an issue where the posture-based app profile was not correctly applied after upgrading to Zscaler Client Connector version 4.4.0.368 for Windows.

Release Available: Client Connector 4.3.0.261 for Windows

    *   ### **Zscaler Client Connector 4.3.0.261 Enhancements and Fixes**

        *   Fixes an issue where anti-tampering protection could be bypassed by tampering with a specific action performed by a trusted Windows process.
        *   Fixes an FW/AV error that could occur when using Microsoft eDiscovery.
        *   Fixes an issue where service status pop-up notifications indicating that Internet Security or Private Access is enabled or disabled displayed even though service status notifications were disabled.
        *   Fixes an issue where customers experienced DNS resolution errors on applications after Multimatch and App Scaling were enabled.
        *   Fixes a Machine Tunnel authentication error that occurred after the signing certificate expired for devices that were cloned with Zscaler Client Connector already installed.

**October 01, 2024**

*   Release Available: Client Connector 4.5.0.325 for Windows

    *   ### **Zscaler Client Connector 4.5.0.325 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434.

        *   Fixes an issue where ZSATunnel did not exit when it aborted execution or crashed if you have Process-Based Application Bypass configured and enabled.
        *   Fixes a decryption issue with machine tunnels when Zscaler Client Connector was installed using AutoPilot.
        *   Fixes an internal server error that occurred when connecting to Zscaler Private Access (ZPA) if an IPv6 IP address was on the DNS priority list and you use the Route Based option for Tunnel Driver Type.
        *   Fixes an issue where ZPA did not reauthenticate automatically due to a WebView2 initialization issue resulting from resuming a Windows device from Modern Standby.
        *   Fixes an issue where the internet usage policy page for a captive portal did not load properly after clicking Open Browser in Zscaler Client Connector.
        *   Fixes high CPU usage by the Anti-Tampering feature (ZEP Service).
        *   Fixes an issue where a machine tunnel was not established after the initial Zscaler Client Connector deployment on a new device.
        *   Fixes an issue where Zscaler Client Connector did not display in the system tray after a system reboot.
        *   Fixes an issue where uploads from a client device to ZPA apps could fail when a client app server sent more than 64k bytes of data directly after a TCP handshake without a server acknowledgement.
        *   Fixes an issue where users could not log in to Zscaler Client Connector if they were subscribed to a Authentication Service-enabled tenant and Posture Based App Profile was enabled but there were no device postures configured.

**September 26, 2024**

*   Release Available: Client Connector 4.4.0.368 for Windows

    *   ### **Zscaler Client Connector 4.4.0.368 Enhancements and Fixes**

        *   Fixes an issue where ZSATunnel did not exit when it aborted execution or crashed if you have Process-Based Application Bypass configured and enabled.
        *   Fixes a decryption issue with machine tunnels when Zscaler Client Connector was installed using AutoPilot.
        *   Fixes an internal server error that occurred when connecting to Zscaler Private Access (ZPA) if an IPv6 IP address was on the DNS priority list and you use the Route Based option for Tunnel Driver Type.
        *   Fixes an issue where ZPA did not reauthenticate automatically due to a WebView2 initialization issue resulting from resuming a Windows device from Modern Standby.
        *   Fixes an issue where the internet usage policy page for a captive portal did not load properly after clicking Open Browser in Zscaler Client Connector.
        *   Fixes high CPU usage by the Anti-Tampering feature (ZEP Service).
        *   Fixes an issue where a machine tunnel was not established after the initial Zscaler Client Connector deployment on a new device.
        *   Fixes an issue where Zscaler Client Connector did not display in the system tray after a system reboot.

Release Available: Client Connector 4.3.0.255 for Windows

    *   ### **Zscaler Client Connector 4.3.0.255 Enhancements and Fixes**

        *   Fixes an issue where ZSATunnel did not exit when it aborted execution or crashed if you have Process-Based Application Bypass configured and enabled.
        *   Fixes an issue where a blank web page appeared during Zscaler Internet Access (ZIA) authentication or Zscaler Private Access (ZPA) reauthentication when using WebView2 web control.
        *   Fixes an internal server error that occurred when connecting to ZPA if an IPv6 IP address was on the DNS priority list and you use the Route Based option for Tunnel Driver Type.
        *   Fixes an issue where ZPA did not reauthenticate automatically due to a WebView2 initialization issue resulting from resuming a Windows device from Modern Standby.
        *   Fixes high CPU usage by the Anti-Tampering feature (ZEP Service).
        *   Fixes an issue where a machine tunnel was not established after the initial Zscaler Client Connector deployment on a new device.
        *   Fixes an issue where the Export Logs option did not display when the Report an issue option was enabled in the Troubleshoot section on the More window of Zscaler Client Connector.

**September 12, 2024**

*   Release Available: Client Connector 4.5.0.296 for Windows

    *   ### **Zscaler Client Connector 4.5.0.296 Enhancements and Fixes**

Zscaler Client Connector version 4.5.0 for Windows has a known issue where uploads from a client device to Zscaler Private Access (ZPA) apps can fail when a client app server sends more than 64k bytes of data directly after a TCP handshake without a server acknowledgement. The issue has been corrected on Zscaler Client Connector version 4.5.0.325 for Windows. Prior to upgrading, you can temporarily resolve the issue by using an HTTPS server.

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434. 
        *   Fixes an issue where a blank web page appeared during Zscaler Internet Access (ZIA) authentication or Zscaler Private Access (ZPA) reauthentication when using WebView2 web control.
        *   Fixes an issue where antivirus detection failed after upgrading to Zscaler Client Connector version 4.5.0.286 for Detect Antivirus posture types with a blank AV Name.

**September 06, 2024**

*   Release Available: Client Connector 4.4.0.346 for Windows

    *   ### **Zscaler Client Connector 4.4.0.346 Enhancements and Fixes**

Fixes an issue where a blank web page appeared during Zscaler Internet Access (ZIA) authentication or Zscaler Private Access (ZPA) reauthentication when using WebView2 web control.

**August 27, 2024**

*   Release Available: Client Connector 4.5.0.286 for Windows

    *   ### **Zscaler Client Connector 4.5.0.286 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

Zscaler Client Connector version 4.5.0 for Windows has a known issue where uploads from a client device to Zscaler Private Access (ZPA) apps can fail when a client app server sends more than 64k bytes of data directly after a TCP handshake without a server acknowledgement. The issue has been corrected on Zscaler Client Connector version 4.5.0.325 for Windows. Prior to upgrading, you can temporarily resolve the issue by using an HTTPS server.

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434. 
        *   Updates Zscaler Client Connector to ignore the "Run this program as an administrator" compatibility setting and always launch the system tray as the standard user.
        *   Fixes an issue where Zscaler Client Connector disconnected temporarily when deploying a larger number of Zscaler Private Access (ZPA) applications via an API.
        *   Fixes an issue where flow logs did not display the URL for a custom IP-based application bypass.
        *   Supports the Ignore Client Cert errors for WebView2 option that continues the authentication workflow if the embedded WebView2 in Zscaler Client Connector encounters client certificate errors. To learn more, see [Using WebView2 Authentication](https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication).
        *   Fixes an issue where Zscaler Client Connector did not update the PAC file for the forwarding profile when the IP address remained the same even after a network type change.
        *   Fixes an issue where Zscaler Client Connector did not bypass mobilesupport.zscaler.com when fetching logs from the Zscaler Client Connector Portal even though support access was not enabled for users.
        *   Fixes an issue where the User Devices section of the Zscaler Digital Experience (ZDX) Admin Portal did not display the correct tunnel status for Zscaler Client Connector.

**August 23, 2024**

*   Release Available: Client Connector 4.4.0.335 for Windows

    *   ### **Zscaler Client Connector 4.4.0.335 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Updates Zscaler Client Connector to ignore the "Run this program as an administrator" compatibility setting and always launch the system tray as the standard user.
        *   Fixes an issue where the Wi-Fi adapter was disabled after rebooting when Zscaler Private Access (ZPA) was enabled and Block Domain Profile Detection was not enabled for any network types.
        *   Fixes an issue where Zscaler Client Connector disconnected temporarily when deploying a larger number of ZPA applications via an API.
        *   Fixes an issue where flow logs did not display the URL for a custom IP-based application bypass.
        *   Supports the Ignore Client Cert errors for WebView2 option that continues the authentication workflow if the embedded WebView2 in Zscaler Client Connector encounters client certificate errors. To learn more, see [Using WebView2 Authentication](https://help.zscaler.com/zscaler-client-connector/using-webview2-authentication).
        *   Fixes a false positive FW/AV Error detection.
        *   Fixes an issue where the initial DNS query timed out for a ZPA bypassed application when App Scaling was enabled.
        *   Fixes an issue where Zscaler Client Connector did not update the PAC file for the forwarding profile when the IP address remained the same even after a network type change.
        *   Fixes an issue where Zscaler Client Connector disappeared and did not reappear in the system tray until the system was rebooted.
        *   Fixes an issue where customers using a process-based application bypass could experience a Blue Screen error.
        *   Fixes an issue where Zscaler Client Connector did not bypass mobilesupport.zscaler.com when fetching logs from the Zscaler Client Connector Portal even though support access was not enabled for users.
        *   Fixes an issue where the User Devices section of the Zscaler Digital Experience (ZDX) Admin Portal did not display the correct tunnel status for Zscaler Client Connector.

**August 22, 2024**

*   Release Available: Client Connector 4.3.0.243 for Windows

    *   ### **Zscaler Client Connector 4.3.0.243 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where Zscaler Client Connector disconnected temporarily when deploying a larger number of Zscaler Private Access (ZPA) applications via an API.
        *   Fixes a false positive FW/AV Error detection.
        *   Fixes an issue where the initial DNS query timed out for a Zscaler Private Access (ZPA) bypassed application when App Scaling was enabled.
        *   Fixes an issue where Zscaler Client Connector did not update the PAC file for the forwarding profile when the IP address remained the same even after a network type change.
        *   Fixes an issue where Zscaler Client Connector did not bypass mobilesupport.zscaler.com when fetching logs from the Zscaler Client Connector Portal even though support access was not enabled for users.

Release Available: Client Connector 4.2.1.237 for Windows

    *   ### **Zscaler Client Connector 4.2.1.237 Enhancements and Fixes**

        *   Fixes a false positive FW/AV Error detection.
        *   Fixes an issue where Zscaler Client Connector did not bypass mobilesupport.zscaler.com when fetching logs from the Zscaler Client Connector Portal even though support access was not enabled for users.

**August 09, 2024**

*   Release in Limited Availability: Client Connector 4.5.0.278 for Windows

    *   ### **Zscaler Client Connector 4.5.0.278 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

Zscaler Client Connector version 4.5.0 for Windows has a known issue where uploads from a client device to Zscaler Private Access (ZPA) apps can fail when a client app server sends more than 64k bytes of data directly after a TCP handshake without a server acknowledgement. The issue has been corrected on Zscaler Client Connector version 4.5.0.325 for Windows. Prior to upgrading, you can temporarily resolve the issue by using an HTTPS server.

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434. 
        *   Fixes an error where the ZSATrayManager process became stuck on the Windows Management Instrumentation (WMI) API invocation during reauthentication.
        *   Fixes an issue where Zscaler Client Connector was preventing the removal of a device from isolation through the Intune Microsoft Defender portal.
        *   Fixes high memory consumption on Zscaler Client Connector.
        *   Fixes an issue where users could not connect to an RDP related to the Windows domain network profile detection.
        *   Fixes an issue where the Wi-Fi adapter was disabled after rebooting when Zscaler Private Access (ZPA) was enabled and Block Domain Profile Detection was not enabled for any network types.
        *   Fixes an issue where Zscaler Client Connector users displayed in the Endpoint Settings in the Zscaler Deception Admin Portal even though Zscaler Deception was not enabled.
        *   Fixes an issue where excessive keepalive requests were received from devices with posture-based app profiles.
        *   Fixes an issue where Zscaler Client Connector did not evaluate trusted network criteria against a non-primary default adapter even though Evaluate Trusted NW against All Default Route Adapters was enabled.
        *   Fixes an issue where a "The Zscaler Client Connector is not running Tunnel" message displayed in User Devices in the Zscaler Digital Experience (ZDX) Admin Portal even though Zscaler Client Connector was connected to the tunnel.
        *   Fixes a false positive FW/AV Error detection.
        *   Fixes an issue where Zscaler Client Connector did not match the wildcard in partner login mode, although it matched without partner login.
        *   Fixes an issue where the initial DNS query timed out for a ZPA bypassed application when App Scaling was enabled.
        *   Fixes an issue where Zscaler Client Connector disappeared and did not reappear in the system tray until the system was rebooted.

**July 22, 2024**

*   Release Available: Client Connector 4.4.0.324 for Windows

    *   ### **Zscaler Client Connector 4.4.0.324 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where users ran into connection delays in some scenarios when a device woke up from sleep.
        *   Fixes an issue where Zscaler Client Connector was preventing the removal of a device from isolation through the Intune Microsoft Defender portal.
        *   Fixes high memory consumption on Zscaler Client Connector.
        *   Fixes an issue where Zscaler Client Connector pushed a proxy PAC file even though proxy settings were set to Never.
        *   Fixes an issue where users could not connect to a remote Windows desktop (RDP) related to the Windows domain network profile detection.
        *   Fixes an issue where Zscaler Client Connector users displayed in the Endpoint Settings in the Zscaler Deception Admin Portal even though Zscaler Deception was not enabled.
        *   Fixes an issue where excessive keepalive requests were received from devices with posture-based app profiles.
        *   Fixes an issue where Zscaler Client Connector did not evaluate trusted network criteria against a non-primary default adapter even though Evaluate Trusted NW against All Default Route Adapters was enabled.
        *   Fixes an issue where the Zscaler Internet Access (ZIA) disaster recovery hostname DNS query failed when VPN was enabled.
        *   Fixes an issue where a "The Zscaler Client Connector is not running Tunnel" message displayed in the User Devices section of the Zscaler Digital Experience (ZDX) Admin Portal even though Zscaler Client Connector was connected to the tunnel.
        *   Fixes an issue where Zscaler Client Connector did not match the wildcard in partner login mode, although it matched without partner login.

**July 18, 2024**

*   Release Available: Client Connector 4.3.0.238 for Windows

    *   ### **Zscaler Client Connector 4.3.0.238 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an error where the ZSATrayManager process became stuck on the Windows Management Instrumentation (WMI) API invocation during reauthentication.
        *   Fixes an issue where Zscaler Client Connector was preventing the removal of a device from isolation through the Intune Microsoft Defender portal.
        *   Fixes high memory consumption on Zscaler Client Connector.
        *   Fixes an issue where Zscaler Client Connector pushed a proxy PAC file even though proxy settings were set to Never.
        *   Fixes an issue where Zscaler Client Connector users displayed in the Endpoint Settings in the Zscaler Deception Admin Portal even though Zscaler Deception was not enabled.
        *   Fixes an issue where Zscaler Private Access (ZPA) remained in a Connecting state when the WMI service was paused.
        *   Fixes an issue where excessive keepalive requests were received from devices with posture-based app profiles.
        *   Fixes an issue where the Zscaler Internet Access (ZIA) disaster recovery hostname DNS query failed when VPN was enabled.
        *   Fixes an issue where Zscaler Client Connector did not match the wildcard in partner login mode, although it matched without partner login.

**July 16, 2024**

*   Release Available: Client Connector 4.2.1.234 for Windows

    *   ### **Zscaler Client Connector 4.2.1.234 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector pushed a proxy PAC file even though proxy settings were set to Never.
        *   Fixes an issue where Zscaler Client Connector users displayed in the Endpoint Settings in the Zscaler Deception Admin Portal even though Zscaler Deception was not enabled.
        *   Fixes an issue where excessive keepalive requests were received from devices with posture-based app profiles.
        *   Fixes an issue where Zscaler Client Connector did not match the wildcard in partner login mode, although it matched without partner login.

**June 27, 2024**

*   Release in Limited Availability: Client Connector 4.5 for Windows

    *   ### **Zscaler Client Connector 4.5 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

Zscaler Client Connector version 4.5.0 for Windows has a known issue where uploads from a client device to Zscaler Private Access (ZPA) apps can fail when a client app server sends more than 64k bytes of data directly after a TCP handshake without a server acknowledgement. The issue has been corrected on Zscaler Client Connector version 4.5.0.325 for Windows. Prior to upgrading, you can temporarily resolve the issue by using an HTTPS server.

This version of Zscaler Client Connector has a known issue where the cached ports for source port-based bypasses were not being cleared, leading to high memory consumption and new connections being blocked or unintentionally bypassed. The issue has been corrected on Zscaler Client Connector version 4.5.0.434. 
        *   Supports ZIdentity integration for the following features: Strict Enforcement mode, Endpoint Data Loss Prevention (DLP), machine tunnel authentication, support for device groups and service entitlement, dynamic service enrollment, and using ZIdentity to authenticate device tokens created in Zscaler Client Connector. To learn more, see [What Is ZIdentity](https://help.zscaler.com/zslogin/what-zslogin)?
        *   Supports Zscaler Digital Experience (ZDX) database security with encryption to sensitive data.
        *   Updates the DNS TXT record query in Disaster Recovery Mode to send the query to all DNS servers.
        *   Updates Zscaler Client Connector to disable traffic forwarding when users start Windows in safe mode.
        *   Adds support to use a PROXY return statement in the PAC file used with Zscaler Internet Access (ZIA) Disaster Recovery Configuration so that internet traffic is sent to a proxy server. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Updates Zscaler Client Connector to allow traffic to the loopback proxy for app containers that are added while the app is running if Disable Loopback Restriction is enabled.
        *   Supports clearing the list of Kerberos domain controllers in the cache when Zscaler Client Connector connects to and disconnects from Zscaler Private Access (ZPA). To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Adds support to automatically start packet captures when Zscaler Client Connector detects a captive portal. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Improves the installation and uninstallation processes for anti-tampering, including adding an option to enable anti-tampering using an MSI installer. To learn more, see [Customizing Zscaler Client Connector with Install Options for MSI](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-msi).
        *   Supports using the Enable Anti-tampering setting from the app profile regardless of how the anti-tampering option was originally set during installation. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Updates Zscaler Client Connector to require users to reauthenticate ZPA if they log in to Windows with fast startup enabled. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Updates the process used with Firefox integration. Firefox integration no longer supports Mozilla Developer Preview or Firefox downloaded from the Microsoft Store.
        *   Adds support for captive portal detection that moves captive portal settings from the global settings on the Client Connector Support page to the app profile and adds additional configuration settings, including using an embedded browser when users authenticate to a captive portal. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Acknowledges Zscaler’s use of third-party software and adds a link to view the third-party software notice in the About section of the More window in Zscaler Client Connector. To learn more, see [Viewing Information About Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector).
        *   Adds localization support to change the language of the app user interface based on the system language. Zscaler Client Connector version 4.5 and later for Windows supports changing the English language to French and vice versa.

If you use Zscaler Diagnostics, users must copy the Current user settings to the Welcome screen settings in Windows to display the Zscaler Diagnostics user interface in the system language.

        *   Supports ZDX improvements to remote Packet Capture (PCAP) functionality to incorporate Lightweight Filter (LWF) capture capability.
        *   Updates the Detect Carbon Black, Detect CrowdStrike, and Detect SentinelOne device posture types so that the posture check passes if the security product is installed and turned on in Windows Security Center even if a matching thumbprint is not detected. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Allows Zscaler Client Connector to block all inbound traffic for various network types with an option to exclude one or more ports. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Supports forcing a location sync between the SCCM client and the management point after connecting to or disconnecting from ZPA. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
        *   Fixes an issue where Zscaler Client Connector displayed an Authentication Error for a device that was removed in the Zscaler Client Connector Portal in a No Default Route (NDR) environment with Tunnel Internal Traffic enabled. If the user clicked Retry, Zscaler Client Connector returned a -13 error.
        *   Adds an option to have WebView 2.0 not follow the system proxy and instead connect directly to the internet. To learn more, see [Enabling WebView 2.0 Authentication](https://help.zscaler.com/zscaler-client-connector/enabling-webview-2.0-authentication).
        *   Fixes an issue where Zscaler Client Connector did not upgrade correctly to a higher version on Windows 10 Long-Term Servicing Branch (LTSB) devices.
        *   Fixes an issue where users ran into connection delays in some scenarios when a device woke up from sleep.
        *   Fixes an issue where Zscaler Client Connector incorrectly reported traffic as bypassed when it had been blocked by the Windows firewall in some scenarios.
        *   Fixes an issue where Zscaler Client Connector failed to launch when the user skipped completion of Autopilot setup during Microsoft Autopilot deployment.
        *   Fixes an issue where the Zscaler Client Connector Portal displayed the wrong service status if the user was connected to ZPA as a partner tenant.
        *   Fixes an issue where Zscaler Client Connector did not upgrade correctly to a higher version in some scenarios.
        *   Fixes an issue where Zscaler Client Connector continued to use the service edge IP address from the previous PAC file after the PAC file was updated in the app profile in the None forwarding mode.
        *   Fixes an issue where users were required to reauthenticate to ZPA multiple times per day if Zscaler Client Connector incorrectly detected a network adapter change.
        *   Updates third-party libraries for security fixes: upgrades the V8 engine used for the PAC parser (to version 12.5), Npcap (to version 1.79), NewtonSoft Json Parser (to version 13.0.3), and fixes ZSFFUtil and Mozilla libraries.
        *   Resolves an issue where Zscaler Client Connector displayed "Partner Already Exists with the same domain" when attempting to log in to a partner tenant after upgrading the app.
        *   Fixes an issue with ZSATray High CPU usage when the device posture check was set to Detect Windows Defender.
        *   Fixes an issue where Zscaler Client Connector did not apply the system PAC file after a network type change due to a Remote Procedure Call (RPC) failure.
        *   Fixes an issue where the ZPA reauthentication failed intermittently during a network change.
        *   Fixes an issue where a ZPA IP-based application was bypassed in some scenarios when an entry in the VPN gateway bypass section was not resolvable.
        *   Fixes an issue where Zscaler Client Connector dropped an initial DNS request via TCP in some scenarios.
        *   Fixes connectivity issues between Zscaler Active Defense (ZAD) service and the Zscaler Deception Admin Portal.
        *   Fixes an issue where users could not access a private application after force reauthentication was triggered when the timeout policy was set to Never.
        *   Fixes an issue where a change to a Device Posture profile in the Zscaler Client Connector Portal was not sent to the ZPA Service Edge.
        *   Fixes an issue where VPN gateway bypass domains were not accessible after upgrading Zscaler Client Connector.
        *   Fixes an issue where Zscaler Client Connector disconnected intermittently when a large number of smaller chunk downloads were happening simultaneously.
        *   Fixes an issue where the Zscaler Client Connector login page displayed although the HIDEAPPUIONLAUNCH command line parameter was enabled.
        *   Fixes an issue where the Windows Explorer and other application windows were refreshed and repainted while Zscaler Client Connector updated the system proxy configuration.
        *   Fixes an issue where Zscaler Client Connector could not connect to an application using a non-web port when traffic forwarding was set to use Zscaler Tunnel (Z-Tunnel) 1.0 and the Route-based driver type.
        *   Resolves an issue where domain profile detection was not correctly blocked when the Block Domain Profile Detection feature was enabled while using the Machine Tunnel feature.
        *   Fixes an issue where Zscaler Client Connector did not bypass all ZPA applications set up for domain- and IP-based application bypass when using App Scaling.
        *   Fixes an issue where ZPA remained in a Connecting state for over an hour due to an issue with the Disaster Recovery file.

**June 20, 2024**

*   Release Available: Client Connector 4.4.0.309 for Windows

    *   ### **Zscaler Client Connector 4.4.0.309 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where Zscaler Private Access (ZPA) remained in a Connecting state for over an hour due to an issue with the Disaster Recovery file.
        *   Fixes an issue where Zscaler Client Connector switched from Zscaler Tunnel (Z-Tunnel) 2.0 to Z-Tunnel 1.0 when connecting to the VPN after an upgrade.
        *   Fixes an issue where users could not access a private application after force reauthentication was triggered when the timeout policy was set to Never.

Release Available: Client Connector 4.3.0.226 for Windows

    *   ### **Zscaler Client Connector 4.3.0.226 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where Zscaler Private Access (ZPA) remained in a Connecting state for over an hour due to an issue with the Disaster Recovery file.
        *   Fixes an issue where Zscaler Client Connector fell back to Zscaler Tunnel (Z-Tunnel) 1.0 after the user upgraded to Zscaler Client Connector version 4.3.0.x and later.
        *   Fixes an issue where users could not access a private application after force reauthentication was triggered when the timeout policy was set to Never.
        *   Fixes an issue where Zscaler Client Connector was stuck in a Connecting state in some scenarios.
        *   Fixes an issue where the Zscaler Client Connector login page displayed although the HIDEAPPUIONLAUNCH command line parameter was enabled.
        *   Fixes an issue where the ZSATunnel process did not stop in a timely manner during upgrades and other scenarios.
        *   Fixes an issue where Zscaler Client Connector remained in a Connecting state for a few minutes after the user's computer goes into sleep or hibernation.
        *   Fixes an issue where users ran into connection delays in some scenarios when a device woke up from sleep.

**June 18, 2024**

*   Release Available: Client Connector 4.2.1.229 for Windows

    *   ### **Zscaler Client Connector 4.2.1.229 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector fell back to Zscaler Tunnel (Z-Tunnel) 1.0 after the user upgraded to Zscaler Client Connector version 4.3.0.x and later.
        *   Fixes an issue where the Zscaler Client Connector login page displayed although the HIDEAPPUIONLAUNCH command line parameter was enabled.

**June 04, 2024**

*   Release Available: Client Connector 4.4.0.300 for Windows

    *   ### **Zscaler Client Connector 4.4.0.300 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where Zscaler Client Connector failed to launch when the user skipped completion of Autopilot setup during Microsoft Autopilot deployment.
        *   Resolves an issue where Zscaler Client Connector displayed "Partner Already Exists with the same domain" when attempting to log in to a partner tenant after upgrading the app.
        *   Fixes an issue where VPN gateway bypass domains were not accessible after upgrading Zscaler Client Connector.
        *   Fixes an issue where Zscaler Client Connector disconnected intermittently when a large number of smaller chunk downloads were happening simultaneously.
        *   Fixes an issue where users were not auto-enrolled in Zscaler Client Connector after a network switch.
        *   Resolves an issue where domain profile detection was not correctly blocked when the Block Domain Profile Detection feature was enabled while using the Machine Tunnel feature.
        *   Fixes an issue where users faced intermittent connectivity issues to the ZPA service during network change events.

**May 23, 2024**

*   Release Available: Client Connector 4.3.0.218 for Windows

    *   ### **Zscaler Client Connector 4.3.0.218 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where Zscaler Client Connector did not upgrade correctly to a higher version on Windows 10 LTSB devices.
        *   Fixes an issue where Zscaler Client Connector incorrectly reported traffic as bypassed when it had been blocked by the Windows firewall in some scenarios.
        *   Fixes an issue where Zscaler Client Connector did not upgrade correctly to a higher version in some scenarios.
        *   Fixes an issue where the Firewall device posture check passed even though Windows Defender Firewall was disabled.
        *   Fixes an issue where the Firewall device posture check failed even though the firewall was enabled on the user device.
        *   Fixes an issue where a Zscaler Private Access (ZPA) IP-based application was bypassed in some scenarios when an entry in the VPN gateway bypass section was not resolvable.
        *   Fixes connectivity issues between the Zscaler Active Defense (ZAD) and the Zscaler Deception Admin Portal.
        *   Fixes an issue where users could not access a private application after force reauthentication was triggered when the timeout policy was set to Never.
        *   Fixes an issue where VPN gateway bypass domains were not accessible after upgrading Zscaler Client Connector to version 4.3.0.190.
        *   Fixes an issue where Zscaler Client Connector disconnected intermittently when a large number of smaller chunk downloads were happening simultaneously.
        *   Fixes an issue where users were not auto-enrolled in Zscaler Client Connector after a network switch.
        *   Fixes an issue where Zscaler Client Connector could not connect to an application using a non-web port when traffic forwarding was set to use Zscaler Tunnel (Z-Tunnel) 1.0 and the Route-based driver type.

Release Available: Client Connector 4.2.1.223 for Windows

    *   ### **Zscaler Client Connector 4.2.1.223 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector did not upgrade correctly to a higher version on Windows 10 LTSB devices.
        *   Fixes an issue where the Firewall device posture check passed even though Windows Defender Firewall was disabled.
        *   Fixes an issue where the Firewall device posture check failed even though the firewall was enabled on the user device.
        *   Fixes an issue where a Zscaler Private Access (ZPA) IP-based application was bypassed in some scenarios when an entry in the VPN gateway bypass section was not resolvable.
        *   Adds support for downloading the default PAC file using HTTPS.
        *   Fixes connectivity issues between the Zscaler Active Defense (ZAD) and the Zscaler Deception Admin Portal.
        *   Fixes an issue where users could not access a private application after force reauthentication was triggered when the timeout policy was set to Never.
        *   Fixes an issue where VPN gateway bypass domains were not accessible after upgrading Zscaler Client Connector.
        *   Fixes an issue where users were not auto-enrolled in Zscaler Client Connector after a network switch.

**May 15, 2024**

*   Release Available: Client Connector 4.4.0.294 for Windows

    *   ### **Zscaler Client Connector 4.4.0.294 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where Zscaler Client Connector did not upgrade correctly to a higher version on Windows 10 LTSB devices.
        *   Fixes an issue where Zscaler Client Connector incorrectly reported traffic as bypassed when it had been blocked by the Windows firewall in some scenarios.
        *   Fixes an issue where Zscaler Client Connector did not upgrade correctly to a higher version in some scenarios.
        *   Fixes an issue where the Firewall device posture check passed even though Windows Defender Firewall was disabled.
        *   Fixes an issue with ZSATray High CPU usage when the device posture check was set to Detect Windows Defender.
        *   Fixes an issue where the Firewall device posture check failed even though the firewall was enabled on the user device.
        *   Fixes an issue where a Zscaler Private Access (ZPA) IP-based application was bypassed in some scenarios when an entry in the VPN gateway bypass section was not resolvable.
        *   Fixes connectivity issues between the Zscaler Active Defense (ZAD) and the Zscaler Deception Admin Portal.
        *   Fixes an issue where users could not access a private application after force reauthentication was triggered when the timeout policy was set to Never.
        *   Fixes an issue where Zscaler Client Connector did not connect to the correct ZIA Public Service Edge after a trusted network switch in some scenarios.
        *   Fixes an issue where Zscaler Client Connector could not connect to an application using a non-web port when traffic forwarding was set to use Zscaler Tunnel (Z-Tunnel) 1.0 and the Route-based driver type.

**April 24, 2024**

*   Release in Limited Availability: Client Connector 4.4.0.285 for Windows

    *   ### **Zscaler Client Connector 4.4.0.285 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where the Detect Antivirus posture check failed intermittently in some scenarios.
        *   Fixes an issue where users faced intermittent connectivity issues to Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) services.
        *   Fixes an issue where Zscaler Client Connector did not switch to a different user when the user logged in on Imprivata.
        *   Fixes an issue where Firefox and Safari did not enforce the default proxy configuration in TWLP mode.
        *   Fixes an issue where the PAC file was not set after returning to the trusted network after switching from a trusted network to a captive portal.
        *   Fixes an issue where the ZPA reauthentication timed out if the system went to sleep during the reauthentication process.
        *   Fixes a problem where help.zscaler.com traffic did not route through the ZIA tunnel in some scenarios.
        *   Fixes an issue where users could not connect to the internet because Zscaler Client Connector remained in a Connecting state.
        *   Fixes an issue where the ZPA reauthentication failed intermittently during a network change.
        *   Fixes an issue where Zscaler Client Connector did not validate the certificate correctly for a downloaded PAC file (CVSS 6.4).
        *   Fixes an issue where Zscaler Client Connector dropped an initial DNS request via TCP in some scenarios.

**April 19, 2024**

*   Release Available: Client Connector 4.3.0.202 for Windows

    *   ### **Zscaler Client Connector 4.3.0.202 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where the username field did not display when logging in to Zscaler Client Connector when the system locale was set to Japanese.
        *   Fixes an issue where Zscaler Client Connector entered a loop trying to connect to a Zscaler Private Access (ZPA) IP address after detecting a captive portal.
        *   Fixes an issue where Zscaler Client Connector continued to use the service edge IP address from the previous PAC file after the PAC file was updated in the app profile in the None forwarding mode.
        *   Fixes an issue where users faced intermittent connectivity issues to Zscaler Internet Access (ZIA) and ZPA services.
        *   Fixes an issue where the posture-based app profile was not correctly applied in a specific case.
        *   Fixes an issue where Firefox and Safari did not enforce the default proxy configuration in TWLP mode.
        *   Fixes an issue where the PAC file was not set after returning to the trusted network after switching from a trusted network to a captive portal.
        *   Fixes an issue where the ZPA reauthentication timed out if the system went to sleep during the reauthentication process.
        *   Fixes a problem where help.zscaler.com traffic did not route through the ZIA tunnel in some scenarios.
        *   Fixes an issue where users could not connect to the internet because Zscaler Client Connector remained in a Connecting state.
        *   Fixes an issue where Zscaler Client Connector remained in a Connecting state after system hibernation or tunnel restart.
        *   Fixes an issue where the ZPA reauthentication failed intermittently during a network change.
        *   Fixes an issue where Zscaler Client Connector did not validate the certificate correctly for a downloaded PAC file (CVSS 6.4).

Release Available: Client Connector 4.2.1.212 for Windows

    *   ### **Zscaler Client Connector 4.2.1.212 Enhancements and Fixes**

        *   Fixes an issue where the username field did not display when logging in to Zscaler Client Connector when the system locale was set to Japanese.
        *   Fixes an issue where users could not enter the @ symbol in the Password field of Zscaler Client Connector during user login if using a non-English keyboard layout.
        *   Fixes an issue where Zscaler Client Connector entered a loop trying to connect to a Zscaler Private Access (ZPA) IP address after detecting a captive portal.
        *   Fixes an issue where Zscaler Client Connector continued to use the service edge IP address from the previous PAC file after the PAC file was updated in the app profile in the None forwarding mode.
        *   Fixes an issue where Zscaler Client Connector refreshed all VPN hostname bypasses even though no network change was detected.
        *   Fixes an internal error that occurred when connecting to a partner tenant while ZPA was disabled on a VPN trusted network.
        *   Fixes an issue where the ZPA reauthentication timed out if the system went to sleep during the reauthentication process.
        *   Fixes an issue where the ZPA reauthentication failed intermittently during a network change.
        *   Fixes an issue where Zscaler Client Connector did not validate the certificate correctly for a downloaded PAC file (CVSS 6.4).

**March 21, 2024**

*   Release Available: Client Connector 4.3.0.190 for Windows

    *   ### **Zscaler Client Connector 4.3.0.190 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes an issue where users were unable to authenticate with Zscaler Client Connector when browser-based authentication was set up and the application was installed with strict enforcement.
        *   Fixes an issue with the V8-based PAC parser that caused the ZSATunnel process to encounter an error condition.
        *   Fixes an issue where Zscaler Client Connector failed to stop the previous version of the tunnel service during an upgrade, causing the upgrade process to fail for certain users.
        *   Fixes an issue where Zscaler Client Connector did not load the captive portal page even though captive portal detection was enabled.
        *   Fixes an issue where Zscaler Client Connector users experienced a connection error in Zscaler Private Access (ZPA) when an alternative cloud was activated.
        *   Fixes an issue where Zscaler Client Connector did not connect via a captive portal if the IP address overlapped with a ZPA application segment.

**March 18, 2024**

*   Release in Limited Availability: Client Connector 4.4.0.276 for Windows

    *   ### **Zscaler Client Connector 4.4.0.276 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Fixes a UI issue where the PIN dialog for smart card authentication hid behind the Zscaler tray in some scenarios.
        *   Fixes an issue where Zscaler Client Connector consumed a high amount of resources when it was running for a prolonged period.
        *   Fixes an issue where the PAC file was not set after returning to a trusted network from a captive portal.
        *   Fixes an issue where the Zscaler Client Connector Portal displayed the wrong service status if the user was connected to Zscaler Private Access (ZPA) as a partner tenant.
        *   Adds the ability to use the EXE file with a CLI option to disable anti-tampering after it has been enabled.
        *   Fixes an issue where Zscaler Client Connector failed to connect to the private broker in a non-default route environment.
        *   Fixes an issue where Zscaler Client Connector continued to use the service edge IP address from the previous PAC file after the PAC file was updated in the app profile when the forwarding mode was also set to "None."
        *   Fixes an issue where Zscaler Client Connector didn't fall back to WebBrowser Control if there was a failure to start WebView 2.0.
        *   Fixes an issue where Zscaler Client Connector refreshed all VPN hostname bypasses even though no network change was detected.
        *   Fixes an issue with the V8-based PAC parser that caused the ZSATunnel process to encounter an error condition.
        *   Fixes an issue where some configuration files (including machine tunnel configurations) were not retained and strict enforcement was not reapplied after reverting Zscaler Client Connector to a previous version.
        *   Fixes an internal error that occurred when connecting to a partner tenant while ZPA was disabled on a VPN trusted network.
        *   Fixes an issue where Zscaler Client Connector entered a loop when running the Export Logs process for a large file.
        *   Fixes an issue where the posture-based app profile was not correctly applied in a specific case.
        *   Fixes an issue where Zscaler Client Connector failed to stop the previous version of the tunnel service during an upgrade, resulting in the upgrade failing for some users.
        *   Fixes an issue where Zscaler Client Connector did not load the captive portal page even though captive portal detection was enabled.
        *   Fixes a network error that occurred when Zscaler Client Connector tried to connect to Zscaler Internet Access (ZIA) if ZIA was configured to block domain fronting.

**March 05, 2024**

*   Release Available: Client Connector 4.3.0.188 for Windows

    *   ### **Zscaler Client Connector 4.3.0.188 Enhancements and Fixes**

Zscaler Client Connector version 4.3.0.x has a known issue with alternative cloud domains. If you have an alternative cloud domain activated for Zscaler Private Access (ZPA), do _not_ update to this release. To learn more, see [Configuring ZPA Private Service Edges](https://help.zscaler.com/zpa/configuring-service-edges).

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page). 
        *   Fixes an issue where Zscaler Client Connector consumed a high amount of resources when it was running for a prolonged period.
        *   Fixes an error where traffic could bypass the tunnel after a Zscaler Private Access (ZPA) connection error in some scenarios.
        *   Fixes an issue where the Zscaler Client Connector Portal displayed the wrong service status if the user was connected to ZPA as a partner tenant.
        *   Adds the ability to use the EXE file with a CLI option to disable anti-tampering after it has been enabled.
        *   Fixes an issue where Zscaler Client Connector failed to connect to the private broker in a non-default route environment.
        *   Fixes an issue where Zscaler Client Connector refreshed all VPN hostname bypasses even though no network change was detected.
        *   Fixes an issue where some configuration files (including machine tunnel configurations) were not retained and strict enforcement was not reapplied after reverting Zscaler Client Connector to a previous version.
        *   Fixes an internal error that occurred when connecting to a partner tenant while ZPA was disabled on a VPN trusted network.

**February 26, 2024**

*   Release Available: Client Connector 4.2.1.199 for Windows

    *   ### **Zscaler Client Connector 4.2.1.199 Enhancements and Fixes**

        *   Fixes an issue where Zscaler Client Connector consumed a high amount of resources when it was running for a prolonged period.
        *   Fixes an error where traffic could bypass the tunnel after a Zscaler Private Access (ZPA) connection error in some scenarios.
        *   Fixes an issue where the Zscaler Client Connector Portal displayed the wrong service status if the user was connected to ZPA as a partner tenant.
        *   Adds the ability to use the EXE file with a CLI option to disable anti-tampering after it has been enabled.

**February 09, 2024**

*   Release in Limited Availability: Client Connector 4.4 for Windows

    *   ### **Zscaler Client Connector 4.4 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Supports flow logging for partner tenants using Zscaler Private Access (ZPA) when the main tenant uses only Zscaler Internet Access (ZIA).
        *   Updates notification message text to indicate when the message applies to a partner tenant.
        *   Adds support to retain an active partner tunnel connection after a Zscaler Client Connector policy update for the main tenant.
        *   Adds [Machine Tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels) support for the following device postures: File Path, Registry Key, Firewall, Full Disk Encryption, Domain Joined, and OS Version. To learn more, see [Configuring Device Posture Profiles.](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles)
        *   Supports the [AzureAD Domain Joined](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles#azuread) posture type for devices that are joined to an Azure AD domain.
        *   Adds support for latency-based server configuration for ZPA. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Adds support for DNS TXT records for ZPA domains.
        *   Adds enhancements to the [packet capture functionality](https://help.zscaler.com/zscaler-client-connector/enabling-packet-capture-zscaler-client-connector) in Zscaler Client Connector.
        *   Adds a configuration in the Zscaler Client Connector Portal that [restricts remote packet capture](https://help.zscaler.com/zscaler-client-connector/restricting-remote-packet-capture) for administrators only.
        *   Adds the [Server Certificate](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles) posture type that validates the client certificate against the cloud rather than only checking locally for managed devices.
        *   Adds the following [trusted network conditions](https://help.zscaler.com/zscaler-client-connector/configuring-trusted-networks-zscaler-client-connector) in the Zscaler Client Connector Portal: Network Range, Default Gateway IP Addresses, and DHCP Server.
        *   Supports [Installing Windows Firewall Inbound Rule](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) in the app profiles.
        *   Provides enhancements to the Repair App feature.
        *   Updates Npcap to version 1.78.
        *   Supports [seamless integration with Imprivata](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-and-imprivata-integration) to differentiate individual users sessions on shared clinical workstations.
        *   Adds support for login_hint SAML attributes to improve the [Pre-populate the Zscaler Client Connector Username](https://help.zscaler.com/zscaler-client-connector/configuring-automatic-username-population-idp-authentication) feature.
        *   Adds alternate cloud name support for both ZIA and ZPA.
        *   Adds enhancements to the [Zscaler Cloud Performance Test tool](https://help.zscaler.com/zia/using-zscaler-cloud-performance-test-tool).
        *   Supports an option to [remove existing exempted containers](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) in the app profiles.
        *   Adds a reauthenticate button to Zscaler Client Connector on the taskbar icon menu for authenticating ZPA in advance.
        *   Allows Zscaler Client Connector to [block domain profile detection](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) for various network types.
        *   Replaces HTTP-based probing with ICMP-based probing for improved performance.
        *   Provides Command Line Interface Access on Zscaler Client Connector and allows admins to interact with Zscaler Client Connector via CLI. To learn more, see [Interacting with Zscaler Client Connector Remotely](https://help.zscaler.com/zscaler-client-connector/interacting-zscaler-client-connector-remotely).
        *   Updates the V8 engine used for the PAC parser to version 11.9.
        *   Adds [real-time posture checks](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles) for the following posture types: Process Check, Detect Carbon Black, Detect CrowdStrike, Detect SentinelOne, and Detect Microsoft Defender.
        *   Includes support for the Zscaler Digital Experience (ZDX) [Self Service](https://help.zscaler.com/zdx/configuring-self-service-settings) feature that suggests steps to resolve potential device issues without the need to contact customer support. To learn more, see [Self Service Notifications in Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/self-service-notifications-zscaler-client-connector).
        *   Zscaler Client Connector supports the integration of the ZIdentity platform that allows you to use single sign-on (SSO) for the portals of all Zscaler services in the ZIdentity Admin Portal. To learn more, see [Accessing and Navigating the Zscaler Client Connector Portal](https://help.zscaler.com/zscaler-client-connector/accessing-and-navigating-zscaler-client-connector-portal).
        *   Supports traffic forwarding to ZPA through the ZIA Inspected ZPA Apps forwarding rule.
        *   Adds functionality to defer the enrollment process when deploying Zscaler Client Connector using Microsoft Autopilot so that the Autopilot setup can complete before users are prompted to authenticate. Users must allow the setup to complete for the Zscaler Client Connector system tray to launch correctly.
        *   Supports the ability to prompt users to renew their ZPA certificate a specific number of days before the certificate expires.
        *   Updates Zscaler Client Connector to detect external proxies for all proxy action types to support application monitoring by ZDX.
        *   Supports [enabling SSO for WebView 2.0](https://help.zscaler.com/zscaler-client-connector/enabling-webview-2.0-authentication) customers.
        *   Fixes a ZDX issue where customers using the ZSAUPM process encountered an error state.
        *   Fixes an issue where Zscaler Client Connector couldn’t parse larger DNS TXT records. At this time, Zscaler Client Connector doesn’t support DNS TXT query responses that are larger than 16,384 bytes.
        *   Fixes frequent disconnections on Zscaler Client Connector when using the routing-based app profile with ZPA on a domain-joined Windows machine.
        *   Fixes a delay in loading bypassed sites when the connection is reset by the server.
        *   Updates Zscaler Client Connector to refresh the proxy list and retry any failed proxies after a network change instead of waiting for the next standard PAC file download (every 15 minutes).
        *   Enhances security in the machine tunnel using Zscaler Diagnostics.
        *   Fixes an issue where Zscaler Client Connector generated too many DNS queries when detecting captive portals for customers using a No Default Route environment.
        *   Removes functionality that automatically excludes "F5 Networks VPN Adapter" when evaluating VPN trusted networks.
        *   Fixes an issue where the tunnel processes repeatedly encountered an error condition, causing ZPA to disconnect and reconnect.
        *   Fixes an error that could occur when ZPA/ZIA authentication was forwarded through an external proxy.
        *   Fixes an issue where some Zscaler Private Access (ZPA) apps were not accessible after upgrading to Zscaler Client Connector version 4.2.0.209 if users had IPv6 enabled on their devices but IPv6 did not have internet access.
        *   Fixes an issue where Zscaler Client Connector connected temporarily to a different ZIA Public Service Edge than the closest one after a network type change.
        *   Fixes an issue where Zscaler Client Connector entered a loop trying to connect to a ZPA IP address after detecting a captive portal.
        *   Updates the Certificate Revocation List (CRL) check for the Client Certificate device posture type to add a retrial mechanism in case of failure scenarios.
        *   Fixes an error where the ZSATray manager process became stuck on the Windows WMI API invocation during reauthentication.
        *   Fixes an issue where Zscaler Client Connector did not prompt for reauthentication after a system restart even though Force ZPA Authentication To Expire was set to On System Restart in the app profile.
        *   Fixes an issue where Zscaler Client Connector did not correctly detect a captive portal until users restarted the service if the captive portal shared an IP address with a ZPA app.
        *   Fixes a delay in Zscaler Client Connector connection after a system reboot when a large number of destination exclusions were configured.
        *   Fixes an issue where Zscaler Client Connector did not detect a captive portal even after getting a 307 response code.
        *   Fixes an internal error that occurred when users tried to reauthenticate ZPA after upgrading Zscaler Client Connector.
        *   Fixes an issue where the ZSATunnel.exe file encountered an error that caused Zscaler Client Connector to crash.
        *   Fixes an issue where Zscaler Client Connector bypassed some DNS requests for ZPA domains that are also public domains.
        *   Fixes an error where traffic could bypass the tunnel after a ZPA connection error in some scenarios.
        *   Fixes an issue with the hardware ID for ARM devices.

After upgrading to this release, users with an ARM device must re-register ZPA in Zscaler Client Connector.

**February 07, 2024**

*   Release Available: Client Connector 4.2.1.194 for Windows

    *   ### **Zscaler Client Connector 4.2.1.194 Enhancements and Fixes**

Updates the Certificate Revocation List (CRL) check for the Client Certificate device posture type to add a retrial mechanism in case of failure scenarios.

**February 06, 2024**

*   Release Available: Client Connector 4.3.0.181 for Windows

    *   ### **Zscaler Client Connector 4.3.0.181 Enhancements and Fixes**

Zscaler Client Connector version 4.3.0.x has a known issue with alternative cloud domains. If you have an alternative cloud domain activated for Zscaler Private Access (ZPA), do _not_ update to this release. To learn more, see [Configuring ZPA Private Service Edges](https://help.zscaler.com/zpa/configuring-service-edges).

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page). 
        *   Fixes an issue where some Zscaler Private Access (ZPA) apps were not accessible after upgrading to Zscaler Client Connector version 4.2.0.209 if users had IPv6 enabled on their devices but IPv6 did not have internet access.
        *   Fixes an issue where Zscaler Client Connector connected temporarily to a different ZIA Public Service Edge than the closest one after a network type change.
        *   Fixes a delay in opening files with Japanese characters in the file name for customers using anti-tampering protection.
        *   Updates the Certificate Revocation List (CRL) check for the Client Certificate device posture type to add a retrial mechanism in case of failure scenarios.
        *   Fixes an issue where Zscaler Client Connector did not prompt for reauthentication after a system restart even though Force ZPA Authentication To Expire was set to On System Restart in the app profile.
        *   Fixes an issue where Zscaler Client Connector did not correctly detect a captive portal until users restarted the service if the captive portal shared an IP address with a ZPA app.
        *   Fixes an issue where Zscaler Client Connector did not detect a captive portal even after getting a 307 response code.
        *   Fixes an internal error that occurred when users tried to reauthenticate ZPA after upgrading Zscaler Client Connector.
        *   Fixes an issue where the ZSATunnel.exe file encountered an error that caused Zscaler Client Connector to crash.

**January 26, 2024**

*   Release Available: Client Connector 4.2.1.193 for Windows

    *   ### **Zscaler Client Connector 4.2.1.193 Enhancements and Fixes**

        *   Fixes an issue where some Zscaler Private Access (ZPA) apps were not accessible after upgrading to Zscaler Client Connector version 4.2.0.209 if users had IPv6 enabled on their devices but IPv6 did not have internet access.
        *   Fixes an issue where Zscaler Client Connector connected temporarily to a different ZIA Public Service Edge than the closest one after a network type change.
        *   Fixes an issue where Zscaler Client Connector did not prompt for reauthentication after a system restart even though Force ZPA Authentication To Expire was set to On System Restart in the app profile.
        *   Fixes an issue where Zscaler Client Connector did not correctly detect a captive portal until users restarted the service if the captive portal shared an IP address with a ZPA app.
        *   Fixes an issue where Zscaler Client Connector did not detect a captive portal even after getting a 307 response code.
        *   Fixes an internal error that occurred when users tried to reauthenticate ZPA after upgrading Zscaler Client Connector.
        *   Fixes an issue where the ZSATunnel.exe file encountered an error that caused Zscaler Client Connector to crash.

**January 23, 2024**

*   Release Available: Client Connector 4.3.0.161 for Windows

    *   ### **Zscaler Client Connector 4.3.0.161 Enhancements and Fixes**

Zscaler Client Connector version 4.3.0.x has a known issue with alternative cloud domains. If you have an alternative cloud domain activated for Zscaler Private Access (ZPA), do _not_ update to this release. To learn more, see [Configuring ZPA Private Service Edges](https://help.zscaler.com/zpa/configuring-service-edges).

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page). 
Fixes an issue with anti-tampering where users encountered network disconnections in some specific scenarios after upgrading to Zscaler Client Connector version 4.3.0.151 for Windows. 

Release Available: Client Connector 4.2.1.181 for Windows

    *   ### **Zscaler Client Connector 4.2.1.181 Enhancements and Fixes**

Fixes an issue with anti-tampering where users encountered network disconnections in some specific scenarios after upgrading to Zscaler Client Connector version 4.2.1 for Windows.

---

<!-- source: https://help.zscaler.com/cloud-branch-connector/downloading-zscaler-client-connector-vdi -->

Title: Downloading Zscaler Client Connector for VDI | Zscaler

URL Source: https://help.zscaler.com/cloud-branch-connector/downloading-zscaler-client-connector-vdi

Markdown Content:
# Downloading Zscaler Client Connector for VDI | Zscaler

[![Image 3: Zscaler logo](https://help.zscaler.com/assets/images/zscaler_logo-C5p-IrEj.svg)](https://help.zscaler.com/)

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
*    Downloading Zscaler Client Connector for VDI 

![Image 4: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 5: icon-cloud-connector.svg](blob:http://localhost/6e416d15bcb50c1001e80e392c70c45c)

# Cloud & Branch Connector

### Downloading Zscaler Client Connector for VDI

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

In the Zscaler Admin Console, on the VDI App Store page, you can download Zscaler Client Connector for Virtual Desktop Infrastructure (VDI) for Windows. You can download the latest version or one of the older versions still available for download. To learn more about Zscaler Client Connector for VDI, see [What Is Zscaler Client Connector for VDI?](https://help.zscaler.com/cloud-branch-connector/what-zscaler-vdi-agent)

To download Zscaler Client Connector for VDI:

1.   In the Zscaler Admin Console, go to **Infrastructure**>**Common Resources**>**Deployment**>**Client Connector Deployment**>**App Store for VDI**.
2.   In the **General Availability** tab, view the latest released versions. In the **Limited Availability** tab, view versions that are not yet fully qualified.
3.   Click the **Download** icon for the version you want in the**MSI URL (64 bit)** column.

![Image 6: VDI App Store in the Zscaler Admin Console](https://help.zscaler.com/downloads/cloud-branch-connector/zscaler-client-connector-vdi-management/downloading-zscaler-client-connector-vdi/vdi-app-store.png)

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

<!-- source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2024 -->

Title: Zscaler Client Connector for VDI Release Summary (2024) | Zscaler

URL Source: https://help.zscaler.com/cloud-branch-connector/zscaler-client-connector-vdi-release-summary-2024

Markdown Content:
![Image 1: icon-cloud-connector.svg](blob:http://localhost/6e416d15bcb50c1001e80e392c70c45c)

## Cloud & Branch Connector

### Zscaler Client Connector for VDI Release Summary (2024)

This article provides a summary of all new features and enhancements released per operating system (OS) for the Zscaler Client Connector for VDI on Zscaler Cloud & Branch Connector.

**Select an OS:**

**Select an app version:**

**Select a deployment date:**

* * *

The Zscaler Client Connector for VDI versions for Windows listed below were deployed on the following dates.

**October 09, 2024**

*   Feature Available: Zscaler Client Connector for VDI 1.4.0.5 for Windows

    *   ### **Zscaler Client Connector for VDI 1.4.0.5 Enhancements and Fixes**

        *   Adds support for port and protocol forwarding options.
        *   Fixes an issue where diagnostic options were incorrectly displayed.

**August 08, 2024**

*   Feature Available: Zscaler Client Connector for VDI 1.3.0.16 for Windows

    *   ### **Zscaler Client Connector for VDI 1.3.0.16 Enhancements and Fixes**

Fixes an issue where a user logged in to two separate devices, which caused an invalid reauthentication loop.

**June 10, 2024**

*   Feature Available: Zscaler Client Connector for VDI 1.3.0.14 for Windows

    *   ### **Zscaler Client Connector for VDI 1.3.0.14 Enhancements and Fixes**

        *   Contains a new installer parameter, `USEEMBEDDEDBROWSER`, which enables embedded browser support.

**May 24, 2024**

*   Feature Available: Zscaler Client Connector for VDI 1.2.0.19 for Windows

    *   ### **Zscaler Client Connector for VDI 1.2.0.19 Enhancements and Fixes**

        *   Changes the name of the application from Zscaler VDI Agent to Zscaler Client Connector for VDI.
        *   Contains a new installer parameter, `HIDEAPPONUILAUNCH`, which minimizes the application when launching.

**April 19, 2024**

*   Feature Available: Zscaler Client Connector for VDI 1.1.0.12 for Windows

    *   ### **Zscaler Client Connector for VDI 1.1.0.12 Enhancements and Fixes**

        *   Improves rate limit reauthentication prompts for users.
        *   Fixes an issue where the policy did not update after restarting Zscaler Client Connector for VDI.
        *   Fixes an issue where the driver did not install due to a race condition.

**March 15, 2024**

*   Feature Available: Zscaler Client Connector for VDI 1.1.0.8 for Windows

    *   ### **Zscaler Client Connector for VDI 1.1.0.8 Enhancements and Fixes**

        *   Moves Zscaler Client Connector for VDI's user configuration file to the user's roaming profile folder, which allows you to restore the user-specific configurations using tools such as Citrix UPM or FSLogix.
        *   Fixes an issue where Zscaler Client Connector for VDI caused packet fragmentation, resulting in failed connections to internal Microsoft Azure endpoints.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2025 -->

Title: ZDX Module Release Summary (2025) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2025

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### ZDX Module Release Summary (2025)

**Select an OS:**

**Select a ZDX Module version:**

**Select a deployment date:**

* * *

The ZDX Module app versions for Windows listed below were deployed on the following dates.

**October 30, 2025**

*   Release Available: ZDX Module 4.6.2.7 for Windows

    *   ### **ZDX Module 4.6.2.7 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.8, 4.7.0.88, 4.7.0.61, 4.7.0.47, 4.7.0.113, 4.7, 4.6.0.310, 4.6.0.284, 4.6.0.282, 4.6.0.240, 4.6.0.216, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.498, 4.5.0.495, 4.5.0.484, 4.5.0.478, 4.5.0.471, 4.5.0.459, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.468, 4.4.0.465, 4.4.0.464, 4.4.0.460, 4.4.0.456, 4.4.0.432, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

This package includes a new executable, ZUpmApplication.exe. This binary is present to support a future cloud-dependent capability and remains inactive in this release. It has no functional impact and requires no admin action.

        *   Adds improved detection of failed updates that might leave devices vulnerable or unstable.
        *   Adds monitoring for disk errors that can cause performance degradation or data loss.
        *   Adds monitoring for Group Policy errors that prevent user login or correct policy application.
        *   Adds monitoring for memory allocation failures caused by resource exhaustion or memory leaks.
        *   Adds monitoring for OS login delays and failed login attempts that impact user experience.
        *   Adds monitoring for overheat-related shutdowns caused by hardware or cooling issues.
        *   Adds monitoring for power issues that lead to unexpected shutdowns or restarts.
        *   Adds monitoring for rendering issues that affect user interface responsiveness.
        *   Adds monitoring for slow boot and shutdown performance that impacts startup times.
        *   Adds monitoring for system restarts following updates, improving visibility into post-update behavior and downtime.
        *   Adds monitoring for Windows Update failures caused by missing dependencies, poor connectivity, or insufficient permissions.
        *   Expands HTTP_CONNECT_TIMING metrics in Zscaler Tunnel (Z-Tunnel) 1.0 mode for Web probes, improving visibility into TCP handshake and HTTP connection times between Tunnels and Service Edges.
        *   Upgrades SQLite to 3.50.4 to address security vulnerabilities.
        *   Upgrades the V8 engine to 14.0.365.10 to address security vulnerabilities.
        *   Fixes a discrepancy issue between web load times and trace route hops in configurations involving ZIA Private Service Edges.
        *   Fixes an issue in IPSec tunneling cases by routing REST API calls through Peer IP and using the Destination NAT (DNAT) IP and Port as a proxy where necessary.
        *   Fixes an issue where ICMP probes for Zscaler Private Access (ZPA) Cloud Path were sent over TCP port 443, ensuring correct protocol use for Cloud Path configurations.
        *   Fixes an issue with egress detection in environments using GRE/IPSec tunnels when public IP addresses are applied to private networks, ensuring proper topology detection.

**October 09, 2025**

*   Release Available: ZDX Module 4.5.0.34 for Windows

    *   ### **ZDX Module 4.5.0.34 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.7, 4.6.0.284, 4.6.0.282, 4.6.0.240, 4.6.0.216, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.495, 4.5.0.484, 4.5.0.478, 4.5.0.471, 4.5.0.459, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.465, 4.4.0.464, 4.4.0.460, 4.4.0.456, 4.4.0.432, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

The following updates from [ZDX Module 4.5.0.33 for Windows](https://help.zscaler.com/zscaler-client-connector/zdx-module-release-summary-2025?applicable_category=Windows&applicable_version=4.5.0.33) have been temporarily deferred to allow for additional quality enhancements in the Source IP Anchoring (SIPA) Cloud Path. ZDX Module 4.5.0.33 contains a bug affecting SIPA functionality and is in the process of being rolled back, while SIPA functionality has been removed in ZDX Module 4.5.0.34, which is the recommended build for customers. SIPA features will be re-released in a future update after the bug has been resolved:

        *   Adds SIPA Cloud Path support for ZDX.
        *   Fixes an issue where public IP address and geolocation reporting was incorrect for users in Zscaler locations.
        *   Fixes an issue where the API call to Zscaler Internet Access (ZIA) encounters a Secure Sockets Layer (SSL) error in the Zscaler Tunnel (Z-Tunnel) 1.0 Destination NAT (DNAT) environment.
        *   Fixes an issue where Tunnel Proxy Gateway (TPG) IPv4 addresses were not translated to IPv6 in NAT64 environments.

**September 10, 2025**

*   Release Available: ZDX Module 4.5.0.33 for Windows

    *   ### **ZDX Module 4.5.0.33 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.7, 4.6.0.284, 4.6.0.282, 4.6.0.240, 4.6.0.216, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.484, 4.5.0.478, 4.5.0.471, 4.5.0.459, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.464, 4.4.0.460, 4.4.0.456, 4.4.0.432, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Adds Source IP Anchoring (SIPA) Cloud Path support for ZDX.
        *   Fixes an issue where public IP address and geolocation reporting was incorrect for users in Zscaler locations.
        *   Fixes an issue where the API call to Zscaler Internet Access (ZIA) encounters a Secure Sockets Layer (SSL) error in the Tunnel 1.0 Destination NAT (DNAT) environment.
        *   Fixes an issue where Tunnel Proxy Gateway (TPG) IPv4 addresses were not translated to IPv6 in NAT64 environments.

**June 23, 2025**

*   Release Available: ZDX Module 4.5.0.16 for Windows

    *   ### **ZDX Module 4.5.0.16 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.7.0.47, 4.7, 4.6.0.216, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.459, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.432, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Mitigates an issue where certain Intel NICs receive a stalled ring that led to a drop in network connectivity when ZDX was enabled.
        *   Updates policy logic for Windows Error Reporting (WER) events.
        *   Fixes an issue where the ZDX installation encountered an error for specific virtual desktop infrastructures (VDIs).
        *   Fixes ZDX’s Device Profile logic to capture correct data.

**April 30, 2025**

*   Release Available: ZDX Module 4.5 for Windows

    *   ### **ZDX Module 4.5 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.7, 4.6.0.200, 4.6.0.168, 4.6.0.146, 4.6, 4.5.0.434, 4.5.0.381, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.428, 4.4.0.406, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.277, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Adds support to install Nmap Packet Capture (Npcap), if it is not already installed, when Cloud Path starts.
        *   Enhances Wi-Fi metrics for telemetry data.
        *   Extends the packet capture (PCAP) duration selection to 60 minutes.
        *   Supports Hi-Fi Cloud Path to Zscaler Internet Access (ZIA) Public Service Edge through the Generic Routing Encapsulation (GRE) and Internet Protocol Security (IPSec) tunnel.
        *   Updates the userAgent to the latest version for Web probes.
        *   Fixes an issue where the Cloud Path probe failed in a Destination Network Address Translation (DNAT) environment with Zscaler Internet Access (ZIA) in the Tunnel with Local Proxy (TWLP) mode.
        *   Fixes an issue where the firewall detected errors due to TCP Cloud Path sending a TCP reset after receiving TCP SYN-ACK (Synchronize-Acknowledge).
        *   Fixes an issue where Zscaler Client Connector disconnects intermittently when a laptop uses a cellular connection.

**March 04, 2025**

*   Release Available: ZDX Module 4.4.0.15 for Windows

    *   ### **ZDX Module 4.4.0.15 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.6.0.146, 4.6, 4.5.0.366, 4.5.0.352, 4.5.0.344, 4.5.0.337, 4.5.0.325, 4.5.0.296, 4.5.0.286, 4.5.0.278, 4.5, 4.4.0.395, 4.4.0.389, 4.4.0.383, 4.4.0.379, 4.4.0.368, 4.4.0.346, 4.4.0.335, 4.4.0.324, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.500, 4.3.0.272, 4.3.0.269, 4.3.0.264, 4.3.0.261, 4.3.0.255, 4.3.0.243, 4.3.0.238, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.237, 4.2.1.234, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.209, 4.2.0.198, 4.2.0.190, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Adds tunnel support in Cloud Path for application bypass.
        *   Fixes an issue where Cloud Path failed when the secure sockets layer virtual private network (SSL VPN) was configured with a DNS name.
        *   Fixes an issue where data was missing from the Cloud Path due to a DNS resolution failure.

**February 03, 2025**

*   Release Available: ZDX Module 4.4.0.11 for Windows

    *   ### **ZDX Module 4.4.0.11 Enhancements and Fixes**

###### **Compatible with the following Zscaler Client Connector versions for Windows: 4.6, 4.5, 4.4.0.309, 4.4.0.300, 4.4.0.294, 4.4.0.285, 4.4.0.276, 4.4, 4.3.0.226, 4.3.0.218, 4.3.0.202, 4.3.0.190, 4.3.0.188, 4.3.0.181, 4.3.0.161, 4.3.0.151, 4.3.0.131, 4.3.0.121, 4.3, 4.2.1.229, 4.2.1.223, 4.2.1.212, 4.2.1.199, 4.2.1.194, 4.2.1.193, 4.2.1.181, 4.2.1, 4.2.0.217, 4.2.0.178, 4.2.0.173, 4.2.**

        *   Fixes an issue where the SYSID was missing when an external proxy was chained to the Zscaler Service Edge.
        *   Fixes an issue that prevented NAT64 discovery due to an older version of Zscaler Client Connector.
        *   Fixes an issue where Self Service did not provide a notification for high CPU usage.
        *   Fixes an issue where a policy block was not sent to ADX.
        *   Fixes an issue where ZDX encountered an error condition when running a Windows version of Zscaler Client Connector.
        *   Fixes an issue where ZDX plugins were not restarted to reflect the updated policy.
        *   Fixes an issue where an Npcap upgrade caused packet captures to stop.

---

<!-- source: https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns -->

Title: Step-by-Step Configuration Guide for Zscaler Client Connector-Based EUNs | Zscaler

URL Source: https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns

Published Time: Wed, 17 Jun 2026 06:32:05 GMT

Markdown Content:
![Image 1: icon-zia.svg](https://help.zscaler.com/assets/images/icon-zia-hNrDwZ7t.svg)

## Internet & SaaS (ZIA)

### Step-by-Step Configuration Guide for Zscaler Client Connector-Based EUNs

This guide describes the configuration steps for displaying notifications to users through Zscaler Client Connector when the user activity triggers Internet & SaaS (ZIA) policy actions. You can customize these notifications and associate them with individual policy rules as explained in the following steps. To learn more, see [About Zscaler Client Connector-Based End User Notifications](https://help.zscaler.com/zia/about-zscaler-client-connector-based-end-user-notifications).

## Prerequisites

Before configuring Zscaler Client Connector EUNs, ensure that you meet the necessary requirements based on the policy type:

*   [Inline Web Data Loss Prevention (DLP)](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#web-dlp-eun-requirement)

| [](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Requirement | Details |
| --- | --- |
| In Zscaler Client Connector | Endpoint DLP (includes EUN) or EUN as a standalone feature must be enabled. Standalone EUN feature requires: * For Windows: 4.6.0.123 or later * For macOS: 4.3.1.56 or later |
| In Internet & SaaS | Contact Zscaler Support to enable EUN for Inline Web DLP. |

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#web-dlp-eun-requirement)

*   [Endpoint DLP](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#endpoint-dlp-eun-requirement)| [](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Requirement | Details |
| --- | --- |
| In Zscaler Client Connector | Endpoint DLP must be enabled. |
| In Internet & SaaS | Endpoint DLP policy must be enabled. | 
[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#endpoint-dlp-eun-requirement) 
*   [Cloud App Control](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#cloud-app-control-eun-requirement)

| [](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Requirement | Details |
| --- | --- |
| In Zscaler Client Connector | Endpoint DLP (includes EUN) or EUN as a standalone feature must be enabled. Standalone EUN feature requires: * For Windows: 4.6.0.123 or later * For macOS: 4.3.1.56 or later |
| In Internet & SaaS | Contact Zscaler Support to enable EUN for Cloud App Control. |

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#cloud-app-control-eun-requirement)

*   [Firewall Filtering](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#firewall-filtering-eun-requirement)| [](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Requirement | Details |
| --- | --- |
| In Zscaler Client Connector | Supported on Windows devices running Zscaler Client Connector version 4.8 or later over Z-Tunnel 2.0. |
| In Internet & SaaS | Firewall Filtering policy must be enabled with Advanced Firewall. | 
[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#firewall-filtering-eun-requirement) 
*   [DNS Control](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#dns-control-eun-requirement)| [](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Requirement | Details |
| --- | --- |
| In Zscaler Client Connector | Supported on Windows devices running Zscaler Client Connector version 4.8 or later over Z-Tunnel 2.0. |
| In Internet & SaaS | DNS Control Policy must be enabled with Advanced DNS, which is provided by the Advanced Firewall. | 
[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#dns-control-eun-requirement) 
*   [IPS Control](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#ips-control-eun-requirement)| [](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Requirement | Details |
| --- | --- |
| In Zscaler Client Connector | Supported on Windows devices running Zscaler Client Connector version 4.8 or later over Z-Tunnel 2.0. |
| In Internet & SaaS | IPS Control policy must be enabled through Advanced Firewall. | 
[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#ips-control-eun-requirement) 

To configure Zscaler Client Connector-based EUNs:

*   [Step 1: Enable EUNs in Zscaler Client Connector](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#enable-eun)

[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Ensure that you have EUN customization enabled for your organization and complete the necessary configurations depending on the policy type:

    *   [Inline Web Data Loss Prevention (DLP)](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-web-dlp-eun)[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Configure an [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) for your devices (Windows or macOS) and enable the **Install Endpoint ZDP** option.

EUNs for Inline Web DLP and Cloud App Control are supported as a standalone feature, without requiring Endpoint DLP. Depending on your organization's subscription, the Endpoint ZDP agent activates either Endpoint DLP (includes EUN) or standalone EUN.

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-web-dlp-eun) 
    *   [Endpoint DLP](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-endpoint-dlp-eun)[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Configure an [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) for your devices (Windows or macOS) and enable the **Install Endpoint ZDP** option.

You must have Endpoint DLP in Zscaler Client Connector to use these EUNs.

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-endpoint-dlp-eun) 
    *   [Cloud App Control](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-cloud-app-control-eun)[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Configure an [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) for your devices (Windows or macOS) and enable the **Install Endpoint ZDP** option.

EUNs for Inline Web DLP and Cloud App Control are supported as a standalone feature, without requiring Endpoint DLP. Depending on your organization's subscription, the Endpoint ZDP agent activates either Endpoint DLP (includes EUN) or standalone EUN.

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-cloud-app-control-eun) 
    *   [Firewall Filtering](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-firewall-filtering-eun)[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Configure an [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) for your devices (Windows only) and enable the **Zscaler Notification Framework** option. Additionally, you must select a [Notification Template](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector) for your App Profile that has **ZIA Firewall** and **Firewall Popup Notifications** options enabled.

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-firewall-filtering-eun) 
    *   [DNS Control](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-dns-eun)[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Configure an [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) for your devices (Windows only) and enable the **Zscaler Notification Framework** option. Additionally, you must select a [Notification Template](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector) for your App Profile that has **DNS** and **DNS Popup Notifications** options enabled.

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-dns-eun) 
    *   [IPS Control](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-ips-eun)[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)Configure an [App Profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) for your devices (Windows only) and enable the **Zscaler Notification Framework** Framework option. Additionally, you must select a [Notification Template](https://help.zscaler.com/zscaler-client-connector/configuring-notification-templates-zscaler-client-connector) for your App Profile that has **IPS** and **IPS Popup Notifications** options enabled.

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#zscaler-client-connector-ips-eun) 

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#enable-eun)

*   [Step 2: (Optional) Customize Notification Messages](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#customize-notification-message)
*   [Step 3: (Optional) Customize Notification Settings](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#customize-notification-settings)[](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns)In addition to customizing the notification message, you can modify general settings, such as organization name, logo, support details, notification duration, and more. These attributes are shared by all Zscaler Client Connector-based notifications across policies.

To learn more, see [Configuring Settings for Zscaler Client Connector-Based EUNs](https://help.zscaler.com/zia/configuring-settings-zscaler-client-connector-based-euns).

[Close](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#customize-notification-settings) 
*   [Step 4: Use Notifications in Policy Rules](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-client-connector-based-euns#associate-eun-policy-rule)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-macos -->

Title: Supported Parameters for Zscaler Client Connector for macOS | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-macos

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Supported Parameters for Zscaler Client Connector for macOS

This table lists the available parameters for devices running Zscaler Client Connector for macOS. You can preconfigure these parameters when deploying Zscaler Client Connector using a pkg installation process with property list (PLIST) configuration settings deployed from a mobile device management (MDM) solution.

To learn more, see [Deploying Zscaler Client Connector with Microsoft Intune for macOS](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-microsoft-intune-macos) and [Deploying Zscaler Client Connector with Jamf Pro for macOS](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-client-connector-jamf-pro-macos).

| Parameter | Description | Value | Notes |
| --- | --- | --- | --- |
| `userDomain` | Your organization's domain name. If your instance has multiple domains associated with it, enter the primary domain for your instance. The primary domain is only valid if you are using a single IdP with multiple domains. The primary domain won’t work if you have multiple domains across multiple IdPs. | Example: `safemarch.com` Specify the exact domain that is present in the SAML NameID field. For example, if your user’s NameID is john.doe@corp.company.com, then enter `corp.company.com`. | This install option allows users to skip the app enrollment page. If single sign-on (SSO) is enabled for your organization, users are taken directly to your organization's SSO login page. If you've integrated SSO with the app (i.e., using a mechanism like Microsoft Enterprise SSO Plug-In), users can also skip the SSO login page and are automatically enrolled with the Zscaler service and logged in. |
| `cloudName` | The name of the cloud on which your organization is provisioned. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names). If your organization is provisioned on more than one cloud, your users are asked to select the cloud where their traffic is sent during the enrollment process. With this install option, you can specify the cloud where the app sends user traffic, so your users don't have to make the selection during enrollment. | If your cloud name is zscalertwo.net, enter `zscalertwo` or `zscalertwo.net` | Do not use this option if your organization is provisioned on one cloud. The app automatically sends traffic to the proper cloud and your users don't need to make a selection during enrollment. This option is required if you use the `strictEnforcement` option. |
| `deviceToken` | The appropriate device token from the Zscaler Admin Console if you want to use the [Zscaler Admin Console as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-portal-identity-provider). The Zscaler service silently provisions and authenticates users even if you don't have an authentication mechanism in place. | Example: `123456677754` | This option applies only to Internet & SaaS It is not supported by Private Access unless you also use Authentication Service. Before adding this option, you must generate a device token in the Zscaler Admin Console and complete the full configuration in [Using Zscaler Admin Console as an IdP](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector-portal-identity-provider). |
| `userName` | The username of the user. You can also use an MDM macro to auto-populate this value. Refer to your MDM's documentation. | A maximum of 255 alphanumeric and special characters. For example, if the username is j.doe@zscaler.com, enter `j.doe` | If you use this option, the `userDomain` option must not be empty. |
| `enableFips` | Indicates whether Zscaler Client Connector uses FIPS-compliant libraries for communication with the Zscaler infrastructure. | `1` = Enable `0`= Disable (default) | Enable this option only if you require FIPS-level security within your organization. |
| `externalDeviceId` | The identifier that associates devices in an MDM solution with devices in the Zscaler Admin Console. You can use an MDM macro to auto-populate this value. Refer to your MDM's documentation. | `0` = Disable (default) Enter a custom value to identify the device (e.g., `123456677754`). | Not supported on Zscaler Client Connector version 4.1 and earlier for macOS. |
| `hideAppUIOnLaunch` | Forces the app window to stay hidden before users enroll. Users can always open the window by clicking the app icon in the system tray. | `1` = Enable `0`= Disable (default) |  |
| `policyToken` | Allows you to specify which app profile policy you want to enforce for the app before the user enrolls. All relevant settings associated with the policy apply, including the bypass of the IdP login page. After the user enrolls, this policy is replaced with an app profile policy that matches the user based on group affiliation. | Example: `123456677754` | Applies only, and is required, in the following situations: * You use the `strictEnforcement` option. * You configure the machine tunnel and want the users to access Private Access applications before logging in to the device. In the Zscaler Admin Console, you must configure the app profile policy that you want to enforce and ensure that the custom PAC file associated with that policy includes a bypass for your IdP login page. This allows the user to access the IdP page to log in as necessary before enrolling with the app. |
| `strictEnforcement` | Allows you to require users to enroll with the app before accessing the internet and blocks traffic in the following situations: * The user has not yet logged in after a new install. * A user logs in and logs out. * An administrator removes a device. | `1` = Enable `0`= Disable (default) | If you enable this option, the following options are required: * `cloudName` * `policyToken` This option only works when the forwarding profile action for Zscaler Client Connector is Tunnel or Tunnel with Local Proxy. This option does not affect users who remain logged in and disable the Internet & SaaS service. |
| `launchTray` | By default, Zscaler Client Connector starts its services and user interface after installation. To change this, you can disable this install option to prevent Zscaler Client Connector from automatically starting after installation. If you disable the option, users must open Zscaler Client Connector manually to start the app, or Zscaler Client Connector automatically runs after the next reboot. | `1` = Enable `0`= Disable (default) |  |
| `externalRedirect` | Redirects authentication to your organization’s SAML IdP through the default browser and Safari. When redirected to the browser for the first time, your users must select Remember Me on their IdP log-in screen. For any subsequent authentications, the browser remembers the user and automatically logs them in. | `1` = Enable `0`= Disable (default) |  |

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector -->

Title: Step-by-Step Configuration Guide for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Step-by-Step Configuration Guide for Zscaler Client Connector

This guide takes you step-by-step through the configuration tasks you must complete to begin using Zscaler Client Connector for your organization.

Before you begin configuring Zscaler Client Connector, Zscaler recommends reading the following articles:

*   [What Is Zscaler Client Connector?](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector)
*   [About the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-portal)
*   [Using Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-zscaler-client-connector)
*   [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)

## Configuring Zscaler Client Connector

To configure Zscaler Client Connector, complete the following steps:

*   [Step 1: Complete System Requirements and Prerequisite Tasks](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-1)

[](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Before you begin configuring Zscaler Client Connector, complete the following system requirements and prerequisite tasks:

    *   [Windows Requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#windows-requirements)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Supported versions: For a list of supported Zscaler Client Connector versions and supported OS versions for Windows, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions).
        *   Disk usage: 200 MB
        *   Memory usage: 150 MB
        *   Processor capable of running operating systems supported by Zscaler Client Connector.
        *   Microsoft .NET Framework 4 and later.
        *   Supported languages: For a list of supported languages for localization, see [Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support).
        *   Allowlist Zscaler Client Connector processes and configure firewall bypasses.

While Zscaler has allowlist agreements for Zscaler Client Connector in place with specific endpoint protection vendors such as Trend Micro and Kaspersky Labs, for some endpoint protection products like anti-virus and personal firewall, you might need to perform additional allowlist to ensure full Zscaler Client Connector functionality. To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).

        *   [Limitations and dependencies](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#Windows-limitations)

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#windows-requirements)

    *   [macOS Requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#macOS-requirements)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Supported versions: For a list of supported Zscaler Client Connector versions and supported OS versions for macOS, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions).
        *   Disk usage: 200 MB
        *   Memory usage: 150 MB
        *   Processor capable of running operating systems supported by Zscaler Client Connector.
        *   If you are using Tunnel mode in your [forwarding profile](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-app), ensure that you disabled the system firewall.
        *   Supported languages: For a list of supported languages for localization, see [Localization Support](https://help.zscaler.com/zscaler-client-connector/localization-support).
        *   Allowlist Zscaler Client Connector processes and configure firewall bypasses.

While Zscaler has allowlist agreements for Zscaler Client Connector in place with specific endpoint protection vendors such as Trend Micro and Kaspersky Labs, for some endpoint protection products like anti-virus and personal firewall, you might need to perform additional allowlist to ensure full Zscaler Client Connector functionality. To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).

        *   [Limitations and dependencies](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#macOS-limitations)

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#macOS-requirements)

    *   [Linux Requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#linux-requirements)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Supported versions: For a list of supported Zscaler Client Connector versions and supported OS versions for Linux, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions).
        *   Disk usage: 200 MB
        *   Memory usage: 150 MB
        *   x86-64 architecture processor capable of running operating systems supported by Zscaler Client Connector.

        *   [Limitations and dependencies](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#Linux-limitations)

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#linux-requirements)

    *   [iOS Requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#iOS-requirements)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Supported versions: For a list of supported Zscaler Client Connector versions and supported OS versions for iOS, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions).
        *   Compatible with iPhone, iPad, and iPod touch.
        *   20 MB required for installation and additional space for logs.

        *   [Limitations and dependencies](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#iOS-limitations)

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#iOS-requirements)

    *   [Android Requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#android-requirements)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Supported versions: For a list of supported Zscaler Client Connector versions and supported OS versions for Android, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions).
        *   21 MB required for installation and additional space for logs.

        *   [Limitations and dependencies](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#Android-limitations)

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#android-requirements)

    *   [Android on ChromeOS Requirements](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#chrome-requirements)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Supported versions: For a list of supported Zscaler Client Connector versions and supported OS versions for Android on ChromeOS, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions).
        *   21 MB required for installation and additional space for logs.

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#chrome-requirements)

    *   [Internet & SaaS Prerequisite Tasks](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#zia-prereq-tasks-windows-macOS)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Configure appropriate security and access settings in the ZIA Admin Portal.
        *   You must have one of the following for authentication:
            *   An authentication mechanism configured and users provisioned on the ZIA service.
            *   If you do not have an authentication mechanism installed, you must [use the Zscaler Client Connector Portal as your Identity Provider (IdP)](https://help.zscaler.com/zscaler-client-connector/using-zscaler-app-portal-identity-provider) to provision and authenticate users.

        *   Configure your organization's firewall to allow the necessary connections. For detailed information about the traffic your firewall must allow, go to config.zscaler.com/<Zscaler Cloud Name>/zscaler-app. For example, if your cloud name is zscalertwo.net, you would go to config.zscaler.com/zscalertwo.net/zscaler-app. To learn more, see [What Is my cloud name for ZIA?](https://help.zscaler.com/zia/what-my-cloud-name-zia)
        *   (Optional) Enable SSL inspection for users running Zscaler Client Connector. To learn more, see [Configuring SSL Inspection for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-app).

To learn more, see [Deploying and Managing Zscaler Client Connector for ZIA](https://help.zscaler.com/zia/deploying-managing-zscaler-client-connector-zia).

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#zia-prereq-tasks-windows-macOS)

    *   [Private Access Prerequisite Tasks](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#zpa-prereq-tasks-windows-macOS)

        *   [](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)Configure appropriate security and access settings in the Zscaler Admin Console.
        *   SAML-based authentication must be configured and users provisioned. You cannot use the Zscaler Client Connector Portal as an IdP for the ZPA service.
        *   Ensure that Zscaler Client Connector properly processes traffic for ZPA. To learn more, see [Domains to Add to SSL Bypass List](https://help.zscaler.com/zscaler-client-connector/domains-add-ssl-bypass-list).

If you use a PAC file for Zscaler Client Connector, you must add the URLs to the SSL exemptions list on the proxy as well.

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#zpa-prereq-tasks-windows-macOS)

    *   [Zscaler Digital Experience (ZDX) Prerequisite Tasks (Windows and macOS)](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#zdx-prereq-tasks-windows-macOS)[](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector)To ensure that Zscaler Client Connector properly monitors your users’ digital experience, ensure all destination domains are placed on the allowlist in your SSL bypass list. To learn more, see [Allowlist Domains for ZDX](https://help.zscaler.com/zdx/allowlist-domains-zdx).

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#zdx-prereq-tasks-windows-macOS) 

[Close](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-1)

*   [Step 2: Configure Your Administration Settings](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-2)
*   [Step 3: Configure Your Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-3)
*   [Step 4: Download Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-4)
*   [Step 5: Customize Zscaler Client Connector with Installer Options](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-5)
*   [Step 6: Deploy Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector#step-6)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-acceptable-use-policy-zscaler-app -->

Title: Configuring an Acceptable Use Policy for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-acceptable-use-policy-zscaler-app

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring an Acceptable Use Policy for Zscaler Client Connector

You can create an Acceptable Use Policy (AUP) that your users must accept before they can connect to the internet or access your organization's internal resources from devices protected by Zscaler Client Connector.

To configure an AUP:

1.   Go to **Policies**>**Common Configuration**>**Resources**>**Client Connector Notifications**.
2.   On the **Acceptable Use Policy (AUP) Settings** tab:

    *   **Configure AUP Frequency**: Choose how often Zscaler Client Connector displays the AUP.
        *   Never
        *   After User Enrollment
        *   Daily
        *   Weekly
        *   Custom

    *   **Custom Days**: This field only appears if you select Custom as the AUP frequency. Enter any value from `1` to `180` days.
    *   **Configure AUP Message**: Enter or copy and paste your organization's AUP into the field. You can enter static HTML tags as well as images, as long as the image files are accessible from the internet.
    *   **Preview AUP Message**: Click to view the AUP as your users will see it.

![Image 2: The Acceptable Usage Policy (AUP) Settings tab](https://help.zscaler.com/downloads/zscaler-client-connector/administration/zscaler-client-connector-notifications/configuring-acceptable-use-policy-zscaler-app/zclient-connector-notifications-aup.png)

3.   Click **Save**.

To learn more about other Zscaler Client Connector Notifications features, see [About Zscaler Client Connector Notifications](https://help.zscaler.com/zscaler-client-connector/about-zscaler-app-notifications).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe -->

Title: Customizing Zscaler Client Connector with Install Options for EXE | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Customizing Zscaler Client Connector with Install Options for EXE

You can use the EXE file to manually install Zscaler Client Connector on a device, or if you're deploying the app to your users through device management methods that do not support MSI files. After downloading the Zscaler Client Connector EXE installer file, you can deploy the file as is with your device management method.

You can also add install options to customize the app for your organization. This article covers the following options:

*   [Running the EXE File with CLI Options](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#RunZAppEXECmdLine)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe)To run the EXE file using CLI options:

    1.   Start a command prompt as an administrator.
        1.   Click **Start**.
        2.   In the **Start Search** box, enter cmd, then press `CTRL+SHIFT+ENTER`.
        3.   If the User Account Control (UAC) window appears, confirm that you want to continue.

    2.   Enter the absolute path to the EXE file using one or more of the [Zscaler configuration parameters](https://help.zscaler.com/zscaler-client-connector/supported-parameters-zscaler-client-connector-windows) in the `--<install option>` format.

To modify any of these parameters post-installation, app reinstallation with the new values is required.

The following image is an example of a CLI where:

    *   The absolute path to the EXE file is `C:\Users\User\Downloads\Zscaler-windows-1.2.0.000311-installer.exe`
    *   The cloud on which the organization is provisioned is `zscalertwo`
    *   The device token value is `123456789`
    *   The policy token value is `987654321`
    *   The organization's domain name is `safemarch.com`
    *   The userName is `test`
    *   The externalDeviceId is `TestDevice`.

![Image 2: EXE install options](https://help.zscaler.com/downloads/zscaler-client-connector/downloading-deployment/customizing-zscaler-app-install-options-exe/ZClientConnector-Install-Options-EXE-Command-Prompt.png)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#RunZAppEXECmdLine)

*   [Allowing Users to Log into Zscaler Client Connector Without Entering Domains](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#ZAppLoginNoDomain)

[](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe)In addition to the custom features enabled by the install options, you can also modify the EXE file to allow users to log in to the app without entering a domain name.

You can use this configuration only if your organization's domain is registered on a single cloud. If your organization's domain is registered on multiple clouds, use the CLI install options described earlier.

This configuration achieves the same function as the --userDomain install option. The following guidelines apply:

    *   SSO must be enabled for your organization.
    *   If you've integrated your SSO with Zscaler Client Connector using a mechanism like Integrated Windows Authentication (IWA), users can also skip the SSO login page and are automatically enrolled in the Zscaler service and logged in.

To allow users to log into the app without entering domains:

    1.   Locate the EXE file.
    2.   Prefix the file name with your organization's domain name. For example, if the file name is Zscaler-windows-1.1.0.000213-installer and your organization's domain is safemarch.com, you would rename the file to safemarch.com-Zscaler-windows-1.1.0.000213-installer.

![Image 3: A configured Zscaler Client Connector EXE file that allows users to log in without entering domains](https://help.zscaler.com/downloads/zia/documentation-knowledgebase/forwarding-your-traffic/zscaler-app/downloading-and-deploying-zscaler-app/customizing-zscaler-app-install-options-exe/de0904c5-b98e-4e08-8e17-e734842e7ba2_display.png)

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#ZAppLoginNoDomain)

*   [Deploying Zscaler Client Connector with Non-Persistent Citrix VDIs](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#InstallVDIexe)

Zscaler Client Connector only supports a dedicated, single-user virtual desktop infrastructure (VDI) model. For multi-session VDIs, [Zscaler Client Connector for VDI](https://help.zscaler.com/cloud-branch-connector/what-zscaler-client-connector-vdi) is required.

Follow these best practices when using Zscaler Client Connector in a VDI:

    *   Zscaler recommends that you don't log in to Zscaler Client Connector on the master VM.
    *   To use the --strictEnforcement install option, you must have the --hideAppUIOnLaunch install option disabled. This allows Zscaler Client Connector to remind users to enroll with Zscaler Client Connector before accessing the internet.
    *   To use the --userDomain install option, you must use Integrated Windows Authentication (IWA).

## Behavior of Install Parameters for VDI

The install parameters for VDI behave in the following manner:

    *   `--vdi``1` : Configures the Zscaler Client Connector to store configuration data in the `%USERPROFILE%\AppData\Roaming\Zscaler` user profile store so it is roamed using a profile management tool instead of the default `%ProgramData%\Zscaler`. This allows admins to restore Zscaler's configuration as part of users' profiles using tools such as Citrix UPM as described in the [Installing Zscaler Client Connector on the Master VM](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#install-vm) section.
    *   `--configTimeout``300` : If the Zscaler Client Connector configuration data is not found, Zscaler Client Connector waits for the specified interval for configuration data to be restored. In environments with roaming profiles such as non-persistent VDIs, it might take additional time for the profile management solution to restore the Zscaler Client Connector configuration data to the user profile.
    *   `--installLWFDriver``1` : Install the lightweight filter (LWF) driver used for traffic interception during installation of the Zscaler Client Connector. Without this parameter, Zscaler Client Connector doesn't install the LWF driver until configured to do so by a forwarding profile during policy download, which might cause network connection disruption and VDI disconnection.
    *   `--LWFBootStart``1` : Configures the LWF driver to load at system boot instead of the default at system startup. This prevents a blue screen during OS boot in environments where the OS is booted over the network, such as in Citrix Provisioning Services.

## [](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe)Installing Zscaler Client Connector on the Master VM

Install Zscaler Client Connector on the master VM using the following parameters:

    1.   Configure Citrix UPM to backup and restore to the following folder: `{UserProfileFolder}\AppData\Roaming\Zscaler`
    2.   Run your installer and run the installer executable file: `--vdi 1 --configTimeout 300 --installLWFDriver 1 --LWFBootStart 1`

The parameters listed earlier are samples for a VDI installation Zscaler Client Connector. Consult the installation parameters for Zscaler Client Connector to remove/add any options as necessary for the deployment requirements. For example, to support seamless SSO authentication with IWA, adding `--userDomain` and `--cloudName` parameters might be necessary. If the VDI deployment does not use a profile management solution, it's not necessary to apply the `--configTimeout` parameter. If you are using Citrix Provisioning Server, you must use the `--LWFBootStart` option.

[Close](https://help.zscaler.com/zscaler-client-connector/customizing-zscaler-client-connector-install-options-exe#InstallVDIexe)

If you're deploying the app in an Active Directory (AD) environment, you can add install options as parameters when assigning a system start-up script to install the app. To learn more, see [Deploying Zscaler Client Connector for Windows with Active Directory](https://help.zscaler.com/zscaler-client-connector/deploying-zscaler-app-active-directory-windows#EXE).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-charles-proxy-interoperability -->

Title: Zscaler Client Connector and Charles Proxy Interoperability | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-app-charles-proxy-interoperability

Markdown Content:
# Zscaler Client Connector and Charles Proxy Interoperability | Zscaler

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
*   [Interoperability](https://help.zscaler.com/zscaler-client-connector/interoperability)
*    Zscaler Client Connector and Charles Proxy Interoperability 

![Image 3: file-pdf-icon](blob:http://localhost/32f1bfd91f45da96af2fe0ba8f56d8b9)

![Image 4: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

# Client Connector

### Zscaler Client Connector and Charles Proxy Interoperability

Ask Zscaler

Ask Zscaler Preview

Hello, what can I help you with?

On macOS devices, Zscaler Client Connector is interoperable with the Charles Web Debugging Proxy application. If Charles Proxy is detected, Zscaler Client Connector creates a proxy chain. The app automatically configures external proxy settings in the Charles application so that the user's system routes all traffic through the Charles Proxy first, then sends the traffic on to the app.

Keep in mind that if you install the Charles Proxy after installing Zscaler Client Connector, users must restart the Charles Proxy twice after installation, so that Zscaler Client Connector can automatically modify the Charles Proxy configuration for proxy chaining.

If the you installed Charles Proxy on a user's device before installing Zscaler Client Connector, Zscaler Client Connector works seamlessly and users do not need to restart the Charles Proxy.

###### Was this article helpful? Click an icon below to submit feedback.

##### Related Articles

[Domain Validation in Zscaler Client Connector for Private Access Applications](https://help.zscaler.com/zscaler-client-connector/domain-validation-zscaler-client-connector-private-access-applications)[Best Practices for Zscaler Client Connector and VPN Client Interoperability](https://help.zscaler.com/zscaler-client-connector/best-practices-zscaler-client-connector-and-vpn-client-interoperability)[Zscaler Client Connector and Charles Proxy Interoperability](https://help.zscaler.com/zscaler-client-connector/zscaler-app-charles-proxy-interoperability)[Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist)[Allowing Traffic to the ID Federation URL by Bypassing Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/allowing-traffic-id-federation-url-bypassing-zscaler-client-connector)[Enrolling Zscaler Client Connector Users When Using a Proxy](https://help.zscaler.com/zscaler-client-connector/enrolling-zscaler-client-connector-users-when-using-proxy)[Using Fiddler with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/using-fiddler-zscaler-client-connector)[Best Practices for Using PAC Files with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector)[Implementing Zscaler Client Connector in No-Default Route Environments](https://help.zscaler.com/client-connector/implementing-zscaler-client-connector-no-default-route-environments)[Understanding Alternative Cloud Domains](https://help.zscaler.com/zscaler-client-connector/understanding-alternative-cloud-domains)[Zscaler Client Connector Interoperability with Apple iCloud Private Relay](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-interoperability-apple-icloud-private-relay)

Copyright ©2007 - 2026 Zscaler Inc. All rights reserved.

. Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our Terms of Service. For more information about our data processing practices, please see our Privacy Statement.

Zscaler uses cookies, pixels, and other tools to collect information you provide to us and to capture and record your interaction with our site. We use this information to enhance site navigation, personalize content, analyze your use of our website, and assist in our marketing efforts and customer service. To deliver the best experience and to assist with our efforts, Zscaler social media, advertising, analytics, and hosting service providers may have access to the information that you provide to us. By clicking "Accept All," you consent to our collection, use, and disclosure of such information and to our [Terms of Service](https://www.zscaler.com/privacy-compliance/cookie-policy). For more information about our data processing practices, please see our [Privacy Statement](https://help.zscaler.com/company/cookie-policy).

Manage Cookie Preferences

Accept All

Reject All

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors -->

Title: Zscaler Client Connector: Connection Status Errors | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors

Markdown Content:
Zscaler Client Connector displays error messages in the **Service Status**field.

The following table provides a list of possible error messages, an explanation of the error, and the action users can take to resolve it.

**Error Message****Explanation****Required Action**
Intermediate Authentication Error A tunnel authentication error has occurred because an intermediate proxy service has intercepted the app authentication request.No action required.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Authenticating...A tunnel authentication error has occurred because the Public Service Edge for Internet & SaaS is waiting for user configuration.No action required.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Authentication Error A tunnel authentication error has occurred.For Internet Security:

Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

For Private Access:

Click **Authenticate** to resolve the error. The **Authentication** option appears next to the **Authentication Status**.

If the error persists, click **More** and click **Restart****Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, log out of Zscaler Client Connector and log in again.

If the error persists, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Captive Portal Detected Zscaler Client Connector is in a fail-open state because Zscaler Client Connector detected a captive portal.Click the **Open Browser**button to access the internet. The **Open Browser** button appears next to the **Status**. If you don't resolve the captive portal in time, click **Retry** to try again.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Captive Portal Error The user has not resolved the captive portal within the time configured in the Zscaler Admin Console. The error message disappears when the user reconnects.Click **Retry** and then resolve the captive portal. The **Retry** option appears next to the **Status**. If you don't resolve the captive portal in time, click **Retry** to try again.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Chaining Authentication Error A tunnel authentication error has occurred due to proxy chaining.For Internet Security:

Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

For Private Access:

Click **Authenticate** to resolve the error. The **Authentication** option appears next to the **Authentication Status**.

If the error persists, click **More** and click **Restart****Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, log out of Zscaler Client Connector and log in again.

If the error persists, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Connection Error The Public Service Edge for Internet & SaaS cannot be reached.Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Driver Error A Windows driver installation issue has been detected, and the tunnel interface cannot be started.

Zscaler Client Connector is in a fail-open state unless you have a [fail-close app profile option](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) enabled.In the **More** window, click **Repair App**. This option is available in the **Troubleshoot**section.

If the error persists, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Endpoint FW/AV Error The device has a firewall or antivirus program blocking Zscaler Client Connector traffic.

Zscaler Client Connector is in a fail-open state unless you have a [fail-close app profile option](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#zcc-fail-close-settings) enabled.Contact your administrator for any required configuration changes on the device.

To learn more, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Fail Open Zscaler Client Connector is in a fail-open state because Zscaler Client Connector detected Windows safe mode activation.Restart Windows without safe mode.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Fail Close <reason>Zscaler Client Connector is in a fail-close state because the tunnel interface cannot be started (e.g., a driver error or an endpoint FW/AV error).Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Internal Error Internal socket problem has been detected.Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Installation Error Zscaler Client Connector experienced a network error while trying to connect to the Zscaler Digital Experience (ZDX) server.Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Network Error No network interface is detected.Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Safe Mode The Zscaler service is down. You'll only have access to critical resources determined by your organization.No action required. To learn more, see [Configuring Disaster Recovery.](https://help.zscaler.com/zia/configuring-disaster-recovery)
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Server Error Zscaler Client Connector is unable to connect to the ZDX cloud.Check network connectivity.

Click **Retry** to resolve the error. The **Retry** option appears next to the **Status**.

If the error persists, click **More** and click **Restart Service**. To learn more, see [Configuring User Access to the Restart & Repair Options for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-user-access-restart-repair-options-zscaler-app).

If the error continues, contact Zscaler Support.
[](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors)Untrusted Root Cert Zscaler Client Connector is unable to validate the Private Service Edge for Private Access root certificate.Contact Zscaler Support.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/removing-device-if-number-devices-limit-reached -->

Title: Removing a Device if the Number of Devices Limit is Reached | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/removing-device-if-number-devices-limit-reached

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### Removing a Device if the Number of Devices Limit is Reached

You can only enroll up to 16 devices under one username. If you want to enroll another device but have reached the maximum number of devices, you must remove at least one device from the Zscaler Admin Console. You can also configure the Zscaler Admin Console to automatically remove devices. To learn how to configure the number of devices threshold, see [Configuring Automated Device Removal](https://help.zscaler.com/zscaler-client-connector/configuring-automated-device-removal).

You can enroll a removed device again if you have fewer than 16 enrolled devices. If you have fewer than 16 active devices but see an error for exceeding the number of devices limit, contact Zscaler Support.

## What Counts as a Device?

In the Zscaler Admin Console, a removed device has a device state of **Unregistered**or **Removed**.Devices with a device state of **Unregistered**or **Removed** do not count against the number of devices limit. These are displayed in the Zscaler Admin Console for statistical purposes only. Devices with a device state of **Removal Pending** count against the number of devices limit. To learn more about device states, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).

To remove users' devices in the Zscaler Admin Console, you can use either of the following methods:

**Soft Remove****Force Remove**
The soft removal takes effect when Zscaler Client Connector sends its next keepalive.The force removal takes effect immediately.
The user does not experience service interruption or authentication errors.The user might experience service interruption or authentication errors.

To learn how to soft remove a device, see [Soft Removing a Device from the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/soft-removing-device-zscaler-admin-console).

To learn how to force remove a device, see [Force Removing a Device from the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/force-removing-device-zscaler-admin-console).

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-port-zscaler-client-connector-listen -->

Title: Configuring the Port for Zscaler Client Connector to Listen On | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-port-zscaler-client-connector-listen

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring the Port for Zscaler Client Connector to Listen On

By default, Zscaler Client Connector listens on port 9000 for Internet & SaaS and port 9010 for Private Access via a TCP protocol. Zscaler Client Connector automatically listens to the next port if another application is listening to the port. However, if Zscaler Client Connector starts before the other application and begins listening on port 9000 for Internet & SaaS or port 9010 for Private Access, the other application can fail.

In order to avoid port conflicts with other applications, you can configure the port for Zscaler Client Connector to listen on.

You must configure any local proxy PAC file to point to the configured port.

To configure the port:

1.   Go to **Infrastructure**>**Connectors**>**Client**>**Endpoint Configuration**.
2.   On the **Endpoint Integration** tab, enter the number for any port ranging from `1024` to `65534` in **Zscaler Client Connector Listening Port**.

![Image 2: Zscaler Client Connector Listening Port option](https://help.zscaler.com/downloads/zscaler-client-connector/zscaler-client-connector-support-settings/endpoint-integration/configuring-port-zscaler-app-listen/zclient-connector-endpoint-integration-zcc-listening-port.png)

3.   Click **Save**.

When a device enrolls and downloads the policy, the listening port is downloaded. Zscaler Client Connector listens to this port the next time Zscaler Client Connector, the Zscaler service, or the device itself restarts. Zscaler Client Connector does not switch ports while it is running.

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices -->

Title: About Enrolled Devices | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

## Client Connector

### About Enrolled Devices

[Watch a video about Enrolled Devices](https://fast.wistia.net/embed/iframe/g3zfrh3wzo) (shows legacy UI).

From the Zscaler Admin Console, you can view a list of enrolled devices, view device fingerprint information, and soft remove devices.

The Device Management page provides the following benefits:

*   Create a custom view by filtering the list of enrolled devices and selecting the columns that display on the page.
*   Find information quickly by performing an exact match search.
*   Sort and capture data by exporting device details as a CSV file.
*   Easily manage devices by soft removing devices.

## About the Device Management Page

On the Device Management page (Infrastructure > Connectors > Client > Device Overview), you can do the following:

1.   Filter the list of enrolled devices with the following options:
    *   **States**: View devices that are identified as **Registered**, **Unregistered**, **Removal Pending**, **Removed**, or **Quarantined**. By default, all states except **Removed** are shown in the table. To learn more, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).
    *   **OS**: View devices for all operating systems or a specific operating system. By default,all operating systems are shown in the table.
    *   **Active From**: View devices active from **7 Days**, **30 Days**, **60 Days**, **90 Days**, **120 Days**, **150 Days**, **180 Days**, or **Older than 180 Days**.

2.   [Soft remove selected devices from the Zscaler Admin Console](https://help.zscaler.com/zscaler-client-connector/soft-removing-device-zscaler-admin-console) by either clicking **Delete**from the **Actions**drop-down menu or clicking the **Delete**icon.
3.   Perform an exact match search of enrolled devices by:
    1.   **Users**: View devices for all users or a specific user. Use the search function to find a specific user.
    2.   **Device ID**: The unique ID for each device in the Zscaler Admin Console database. The unique ID is generated for each new enrollment in the Zscaler Client Connector and continues for subsequent enrollments.
    3.   **UDID**: The Unique Device Identifier that Zscaler Client Connector generates for every new installation. The same UDID continues during uninstalling, reinstalling, and rebooting of the devices. The UDID is unique to each user and device and changes from user to user for the same device.
    4.   **Machine Hostname**: A name assigned to a device on a network. The end user or the operating system can customize the value.
    5.   **Hardware Fingerprint**: The unique ID created from the device’s hardware, such as a serial number, BIOS ID, battery ID, etc.
    6.   **Zscaler Client Connector Version**: The Zscaler Client Connector version installed on the devices.
    7.   **App Profile Name**: A custom name that an administrator configures when creating or modifying app profiles for each OS (Windows, macOS, Linux, etc.).

4.   View a list of enrolled devices for your organization.

[For each enrolled device, you can view these fields.](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices#Item1)

    *   **User ID**: The enrolled user for the device.
    *   **OS Type**: The device operating system.
    *   **Device Model**: The device model.
    *   **Zscaler Client Connector Version**: The Zscaler Client Connector version installed on the device.
    *   **Device State**: The status of the device. To learn more, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).
    *   **Zscaler Digital Experience Version**: The Zscaler Digital Experience (ZDX) version installed on the device.
    *   **Zscaler Deception Version**: The Deception version installed on the device.
    *   **Unique-ID**: The Zscaler-provided device's unique identifier.
    *   **Hardware Fingerprint**: The unique ID created from the device’s hardware, such as a serial number, BIOS ID, battery ID, etc.
    *   **Configuration Download Count**: The total number of times the app profile was updated on the device since enrollment.
    *   **Tunnel Version**: The last Zscaler Tunnel (Z-Tunnel) version the device connected with.
    *   **Policy Name**: The Zscaler Client Connector profile assigned to the device. To learn more, see [About Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-app-profiles "About ").
    *   **OS Version**: The version of the operating system for the device.
    *   **Machine Hostname**: If **Collect Machine Hostname Information** is enabled, this field displays the machine hostname. When disabled, this field does not display the machine hostname.
    *   **MAC Address**: The device's media access control address.
    *   **Manufacturer**: The device's manufacturer.
    *   **Owner**: If **Collect Device Owner Information** is enabled, this field displays the device owner information. For Windows and macOS, this is the locally logged in user. For Android and iOS, this is the Zscaler Client Connector username. When disabled, this field does not display device owner information.
    *   **Last Registration Time**: The last time the user logged in to Zscaler Client Connector on the device.
    *   **Keep Alive Time**: The keepalive occurs every 80 minutes.
    *   **Last Deregistration Time**: The last time the user logged out of Zscaler Client Connector on the device.
    *   **Last Configuration Download Time**: The last time the Zscaler Client Connector profile was updated. To learn more, see [Zscaler Client Connector Update Intervals](https://help.zscaler.com/zscaler-client-connector/zscaler-app-update-intervals).
    *   **Last Seen with Client Connector Active**: The last time that Zscaler Client Connector was active on the device.
    *   **Last Seen Connected to ZIA**: The last known date and time of connection to Internet & SaaS (ZIA).
    *   **Zscaler Client Connector Revert Status**: Statuses include **Unknown**, **InProgress**, **PreviousBuildNotAvailable**, **RevertFailed**, and **RevertSuccess**.
    *   **Department**: Department information synced from Internet & SaaS.
    *   **Active Tunnel SDK Version**: The current tunnel SDK version to allow admins to track the devices switching between multiple tunnel SDK versions.
    *   **Installation Type**: The type of installation (Strict Enforcement or General Deployment).
    *   **Serial Number**: The device’s serial number.
    *   **DC Location Method**: The method used to locate the nearest data center (Source IP or Device Geolocation).

[Close](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices#Item1)

You can customize what you see on the screen. For details, refer to step 7.

5.   [View the device fingerprint for each enrolled device](https://help.zscaler.com/zscaler-client-connector/viewing-device-fingerprint-enrolled-device). You can also view the [one-time password](https://help.zscaler.com/zscaler-client-connector/about-one-time-passwords-enrolled-devices) for each device.
6.   Select devices for soft removal from the Zscaler Admin Console.
7.   Create a custom view of the Device Management page by modifying the displayed columns and the number of rows.
    1.   To modify the displayed columns:
        1.   Click the **Table Options** icon. Verify that **Columns**is selected. Select the checkboxes of the fields you want to view on the page. Clear the checkboxes of fields you don't want to view on the page.
        2.   To reorder columns, drag and drop the column name to the desired position.

    2.   To modify the number of rows that appear, click the **Rows per page** drop-down menu and select either **50**or **100**.

8.   Export the device fingerprint as a CSV file for all enrolled devices.
    *   **Device Details (All Fields)**: Includes fields such as manufacturer, model, username, machine hostname, UDID, etc.
    *   **Device Details (Custom Fields)**: Includes fields such as **User**, **OS Type**, **Device Model**, **OS Version**, and **VPN State**.
    *   **Disable Reasons**: Displays the reasons a service was disabled up to the 5 most recent times the service was disabled.
    *   **Service Status**: The status of Internet & SaaS, Private Access, and Zscaler Digital Experience (ZDX). Can be in an **On**, **Off**, or **Error** state. This only applies if you have those services enabled for your organization.
    *   **Partner Login Details**: Includes partner device fields such as UDID, Platform, Device ID, login name, and cloud.

![Image 2: About Device Management](https://help.zscaler.com/downloads/zscaler-client-connector/monitoring-usage/about-enrolled-devices/zclient-connector-enrolled-devices.png)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector -->

Title: Configuring SSL Inspection for Zscaler Client Connector | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Configuring SSL Inspection for Zscaler Client Connector

When you enable [SSL inspection](https://help.zscaler.com/zia/about-ssl-inspection) for your Zscaler Client Connector devices, this allows the Zscaler service to decrypt and inspect HTTPS traffic to and from the browser to a device, and to and from the destination server. You can also exempt URLs from SSL inspection.

Depending on the platform, Zscaler Client Connector or your Mobile Device Management (MDM) solution installs the Zscaler SSL certificate required for SSL inspection.

Select from the following options for a description of each task:

*   [Configuring SSL Inspection for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#configure-SSL-Zscaler-App)[](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector)You must enable the service to perform SSL inspection for Zscaler Client Connector users on each relevant platform. To learn more, see [Configuring SSL/TLS Inspection Policy](https://help.zscaler.com/zia/configuring-ssltls-inspection-policy).

Location-specific SSL inspection settings are separate from Zscaler Client Connector SSL inspection settings. To learn more, see [Configuring Locations](https://help.zscaler.com/zia/configuring-locations#EnableSSLScanning).

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#configure-SSL-Zscaler-App) 
*   [Exempting URLs from SSL Inspection](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#exempting-URLs-SSL)

To exempt URLs from SSL inspection, you must first create a custom category for the URLs and then add the custom category to **URL Categories**. If you already have a custom category for bypassed URLs, edit the category and add the URLs.

    *   Apple recommends bypassing specific iOS URLs from SSL inspection. Refer to the[Apple Support page](https://support.apple.com/en-au/101555).
    *   Google recommends bypassing specific URLs from SSL inspection. Refer to [Chrome Enterprise and Education Help](https://support.google.com/chrome/a/answer/6334001?hl=en&ref_topic=3504941#allowlist&zippy=%2Chostname-allowlist-for-chromeos-devices-using-android-apps-google-play-store).

## Creating a Custom URL Category

To create a custom URL category in the Zscaler Admin Console:

    1.   Go to **Policies**>**Access Control**>**Internet & SaaS**>**URL Categories**.
    2.   Click **Add URL Category**.
    3.   Enter a name for the category.
    4.   Add the URLs to the **Custom URLs** field.
    5.   Click **Save** and[activate the change](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

To learn more about creating a custom URL category, see [Configuring Custom URL Categories](https://help.zscaler.com/zia/adding-custom-url-categories).

## Adding the Custom Category to URL Categories

To add the custom category in the Zscaler Admin Console:

    1.   Go to **Policies**>**Common Configuration**>**SSL/TLS Inspection**>**SSL/TLS Inspection Policy**.
    2.   Select the SSL inspection policy you created.
    3.   Under **Criteria**, select the URL categories you want to exempt from decryption from the **URL Categories** drop-down menu.
    4.   Under **Action**, click **Do Not Inspect**.
    5.   Click**Save** and [activate the change](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

To learn more about adding the custom category to a URL category, see [Configuring SSL/TLS Inspection Policy](https://help.zscaler.com/zia/configuring-ssltls-inspection-policy).

To learn more about deploying SSL Inspection, see [Deploying SSL/TLS Inspection](https://help.zscaler.com/zia/deploying-ssltls-inspection).

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#exempting-URLs-SSL)

*   [Configuring Zscaler Client Connector to Install the Zscaler SSL Certificate](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#configure-Zscaler-App-SSL-cert)

[](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector)The SSL certificate allows the Zscaler service to perform SSL inspection on user traffic forwarded by the app. Any SSL exemptions you configure in the Zscaler Admin Console also apply.

(Optional) If you [upload your organization’s custom SSL certificate](https://help.zscaler.com/zscaler-client-connector/uploading-custom-ssl-certificate-zscaler-app), you can install the custom certificate instead.

## Installing the Zscaler SSL Certificate on Device Platforms

**Install Zscaler SSL Certificate** is not supported on devices running macOS Big Sur (11) and later.

To install the Zscaler SSL certificate by device:

    *   [Windows, Linux, and macOS](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#WindowsLinuxmacOS)

[](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector)In the Zscaler Admin Console, enable Zscaler Client Connector to automatically install the Zscaler SSL certificate on your users’ devices for Windows, Linux, and macOS.

        1.   Go to **Infrastructure**>**Connectors**>**Client**>**<**_**OS Version**_**>**.
        2.   On the **App Profiles** tab, click [Add <_OS Version_> Policy](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-app-profiles) or select an existing policy to edit.
        3.   Enable **Install Zscaler SSL Certificate**.
        4.   Click **Save**.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#WindowsLinuxmacOS)

    *   [Android](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#android)[](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector)For Android, Zscaler Client Connector does not automatically install the certificate. Zscaler recommends installing the root certificate using your MDM.

Zscaler Client Connector only installs certificates for Samsung devices that have administrator privileges. To install the certificate, go to the **More** window in Zscaler Client Connector and click **Install Certificates**.

For Android version 7.0 and later, Google prevents non-default certificate authorities from being trusted. Unless the third-party application developer explicitly allows it, the Zscaler certificate or any third-party certificate is not trusted.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#android) 
    *   [iOS](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#iOS)[](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector)For iOS, Zscaler Client Connector does not automatically install the certificate because Apple requires application-installed certificates to be untrusted by default. Zscaler recommends installing the root certificate using your MDM.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#iOS) 

## Events That Prompt Zscaler Client Connector to Install the Certificate

If a certificate is already deployed and you upload a new certificate, Zscaler Client Connector uses a native Windows method to install the certificate. The app automatically replaces the certificate if it’s new or different, but won’t change it if the same certificate already exists.

The Internet & SaaS service imports the certificate in the following situations:

    *   Upon enrollment
    *   If the admin modifies the app profile and the device checks in to download the new profile
    *   When Zscaler Client Connector updates
    *   When the user clicks **Update Policy** for Zscaler Client Connector

When these events occur, Zscaler Client Connector receives the certificate link from the Zscaler Admin Console and attempts to install the certificate.

[Close](https://help.zscaler.com/zscaler-client-connector/configuring-ssl-inspection-zscaler-client-connector#configure-Zscaler-App-SSL-cert)

---

<!-- source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2023 -->

Title: Client Connector App Release Summary (2023) | Zscaler

URL Source: https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2023

Markdown Content:
![Image 1: icon-zapp.svg](blob:http://localhost/405ec260e41141a9cf3bea7000d8dcc8)

### Client Connector App Release Summary (2023)

**Select an OS:**

**Select an app version:**

**Select a deployment date:**

* * *

The Client Connector app versions for Windows listed below were deployed on the following dates.

**December 06, 2023**

*   Release in Limited Availability: Client Connector 4.3.0.151 for Windows

    *   ### **Zscaler Client Connector 4.3.0.151 Enhancements and Fixes**

Zscaler Client Connector version 4.3.0.151 has a known issue with anti-tampering. If you use or are planning to use anti-tampering, do _not_ update to this release.

Zscaler Client Connector version 4.3.0.x has a known issue with alternative cloud domains. If you have an alternative cloud domain activated for Zscaler Private Access (ZPA), do _not_ update to this release. To learn more, see [Configuring ZPA Private Service Edges](https://help.zscaler.com/zpa/configuring-service-edges).

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page). 
This version includes all the enhancements and fixes from Zscaler Client Connector version 4.2.1 for Windows. To learn more, see the release notes for [Zscaler Client Connector version 4.2.1 for Windows](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2023?applicable_category=Windows&applicable_version=4.2.1&deployment_date=2023-12-01).

        *   Fixes an issue where a user encountered a login name mismatch error when logging into a partner tenant.
        *   Fixes a Bad URI Syntax error that occurred when handling Zscaler Internet Access (ZIA) URL with non-English characters.
        *   Fixes an issue where traffic was not going through the tunnel in hybrid tunnel mode if the health check to the proxy was failing.
        *   Fixes an issue where Zscaler Client Connector opened an external browser window during authentication, causing the authentication to fail when using WebView2.
        *   Adds retry logic for CrowdStrike ZTA score posture evaluation after an initial reboot of Zscaler Client Connector.
        *   Fixes an issue where traffic from a custom IP-based application was not bypassed even though it was added in the custom app bypasses.
        *   Fixes an issue where Zscaler Digital Experience (ZDX) web probes failed after upgrading Zscaler Client Connector in some scenarios.
        *   Fixes an issue where Zscaler Client Connector did not honor the Bind Trusted Criteria DNS Request to Default Adapter option when evaluating DNS-based trusted network criteria.
        *   Fixes an issue where the tunnel service could be stopped by initiating a repair app and quickly modifying the Tunnel executable path (CVE-2023-41969).

To successfully update to the latest version of Zscaler Client Connector, see [Best Practices for Updating Latest Versions of Zscaler Client Connector Application](https://help.zscaler.com/zscaler-client-connector/best-practices-updating-latest-version-zscaler-client-connector-application).

**December 01, 2023**

*   Release in Limited Availability: Client Connector 4.2.1 for Windows

    *   ### **Zscaler Client Connector 4.2.1 Enhancements and Fixes**

Zscaler Client Connector version 4.2.1 has a known issue with anti-tampering. If you use or are planning to use anti-tampering, do _not_ update to this release.

        *   Zscaler Client Connector supports [passwords for upgrade, uninstall, and revert](https://help.zscaler.com/zscaler-client-connector/configuring-passwords-access-unattended-mode) in the unattended mode.
        *   Adds enhancements for [Zscaler anti-tampering](https://help.zscaler.com/zscaler-client-connector/anti-tampering-zscaler-client-connector) service to provide additional protection for Zscaler Client Connector processes (CVE-2024-23463).
        *   Adds Alternate Cloud Domain support for both Zscaler Internet Access (ZIA) and Zscaler Private Access (ZPA) to support better services in China.
        *   Fixes an issue where the PAC file on [Tunnel with Local Proxy](https://help.zscaler.com/zscaler-client-connector/best-practices-using-pac-files-zscaler-client-connector) mode didn’t apply properly on Windows 11 devices when Zscaler Client Connector switched to an on-trusted network.
        *   Fixes an issue where Zscaler Private Access (ZPA) automatic reauthentication feature was not working in a few scenarios.
        *   Improves the method of DLL inclusions into Zscaler Client Connector for better resiliency.
        *   Fixes an issue to improve the resiliency of [Report an Issue](https://help.zscaler.com/zscaler-client-connector/reporting-issue-zscaler-client-connector) feature in Zscaler Client Connector (CVE-2023-41969).
        *   Fixes an issue where Zscaler Client Connector didn’t adopt the client-to-client FQDN when in the [Machine Tunnel](https://help.zscaler.com/zscaler-client-connector/about-machine-tunnels) mode.
        *   Fixes an issue where Zscaler Client Connector didn’t enable the captive portal authentication in some scenarios.
        *   Fixes a compatibility issue with a third-party VPN that caused Zscaler Client Connector to experience a network error.
        *   Fixes an issue where the Zscaler Client Connector tunnel ran into an error condition due to a timing scenario.
        *   Fixes an issue where Zscaler Client Connector displayed a blank screen during reauthentication.
        *   Fixes an issue where Zscaler Client Connector was unable to detect the trusted network after waking up from sleep.
        *   Fixes an issue where domains required to bypass ZPA didn't work intermittently when mode was set to Always Bypass.
        *   Fixes an issue where Zscaler Client Connector directed the partner login reauthentication to the primary tenant's IdP.
        *   Fixes a Zscaler Client Connector installation issue with the HIDEAPPUIONLAUNCH command line parameter.
        *   Fixes an issue where the tunnel service could be stopped by initiating a repair app and quickly modifying the Tunnel executable path (CVE-2023-41969).
        *   Fixes an issue where, in certain cases, Zscaler Internet Access (ZIA) can be disabled by a PowerShell command with administrator rights (CVSS Base Score: 7.2 - High).

To successfully update to the latest version of Zscaler Client Connector, see [Best Practices for Updating Latest Versions of Zscaler Client Connector Application](https://help.zscaler.com/zscaler-client-connector/best-practices-updating-latest-version-zscaler-client-connector-application).

**November 29, 2023**

*   Release Available: Client Connector 4.1.0.104 for Windows

    *   ### **Zscaler Client Connector 4.1.0.104 Enhancements and Fixes**

Fixes an issue where a user could disable Zscaler Internet Access (ZIA) or Zscaler Private Access (ZPA) from the Machine Tunnel diagnostics page without a password (CVE-2023-28802).

**October 13, 2023**

*   Release in Limited Availability: Client Connector 4.3.0.131 for Windows

    *   ### **Zscaler Client Connector 4.3.0.131 Enhancements and Fixes**

Zscaler Client Connector version 4.3.0.x has a known issue with alternative cloud domains. If you have an alternative cloud domain activated for Zscaler Private Access (ZPA), do _not_ update to this release. To learn more, see [Configuring ZPA Private Service Edges](https://help.zscaler.com/zpa/configuring-service-edges).

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page). 
        *   Adds optimizations to reduce the frequency of policy download requests on the Zscaler Client Connector Portal server.
        *   Fixes an issue with Zscaler Client Connector policy update when Zscaler Client Connector is reinstalled with a new policy token.

**September 25, 2023**

*   Release Available: Client Connector 4.2.0.217 for Windows

    *   ### **Zscaler Client Connector 4.2.0.217 Enhancements and Fixes**

        *   Adds optimizations to reduce the frequency of policy download requests on the Zscaler Client Connector Portal server.
        *   Fixes an issue with Zscaler Client Connector policy update when Zscaler Client Connector is reinstalled with a new policy token.

**September 01, 2023**

*   Release Available: Client Connector 4.2.0.209 for Windows

    *   ### **Zscaler Client Connector 4.2.0.209 Enhancements and Fixes**

        *   Fixes an issue where the Windows Narrator app announced the Zscaler Client Connector window as a pop-up.
        *   Fixes a performance issue related to Zscaler Client Connector interprocess communication.
        *   Fixes an issue where ZPA resources were not accessible after adding a partner tenant.
        *   Fixes an issue where Zscaler Client Connector detected a captive portal when the captive portal detection value was set to 0 in the Zscaler Client Connector Portal.
        *   Fixes an issue where user devices didn’t automatically enroll after upgrading Zscaler Client Connector to a new version.
        *   Fixes an issue where devices running Microsoft Edge WebView2 experienced an authentication error when connecting to Zscaler Client Connector.
        *   Fixes an issue where customers experienced crashes when upgrading to Zscaler Client Connector version 4.2.0.190.
        *   Fixes an issue with Zscaler anti-tampering API validation.
        *   Fixes upgrade issues for Zscaler Client Connector version 4.2.0.190 on devices using Unified Write Filter (UWF).
        *   Fixes an issue with Zscaler Client Connector revert API validation (CVE-2023-41972).
        *   Fixes an issue where the revert logic would append the previous installer name to a system path, which could be used to launch a different file (CVE-2023-41973).
        *   Fixes an issue where anti-tampering can be disabled under certain conditions when the uninstall password is enforced (CVE-2024-23457).

*   Release in Limited Availability: Client Connector 4.3.0.121 for Windows

    *   ### **Zscaler Client Connector 4.3.0.121 Enhancements and Fixes**

Zscaler Client Connector version 4.3.0.x has a known issue with alternative cloud domains. If you have an alternative cloud domain activated for Zscaler Private Access (ZPA), do _not_ update to this release. To learn more, see [Configuring ZPA Private Service Edges](https://help.zscaler.com/zpa/configuring-service-edges).

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page). 
        *   Fixes an issue where using Zscaler Client Connector caused high CPU utilization issues in some devices.
        *   Fixes an issue where the DNS server sent the request back to the host server instead of the internal server.
        *   Fixes an issue where a customer encountered a login name mismatch error when logging into a partner tenant.
        *   Fixes frequent disconnections on Zscaler Client Connector when using the routing-based app profile with ZPA on a domain-joined Windows machine.
        *   Fixes an issue with Zscaler Client Connector revert API validation (CVE-2023-41972).
        *   Fixes an issue where the revert logic would append the previous installer name to a system path, which could be used to launch a different file (CVE-2023-41973).

**August 31, 2023**

*   Release Available: Client Connector 4.3.500 for Windows

    *   ### **Zscaler Client Connector 4.3.500 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Adds support for latency-based server configuration for ZPA. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Includes support for the [Self Service](https://help.zscaler.com/zdx/release-upgrade-summary-2023?applicable_category=zdxcloud.net&deployment_date=2023-08-29&id=1462826) feature that allows users to resolve potential device issues without the need to contact customer support.
        *   Supports configurable posture check intervals. To learn more, see [About Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/about-device-posture-profiles), and [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).

**August 04, 2023**

*   Release Available: Client Connector 4.2.0.198 for Windows

    *   ### **Zscaler Client Connector 4.2.0.198 Enhancements and Fixes**

        *   Fixes a user access control issue by enforcing more validations when handling log files.
        *   Fixes a UI issue where the PIN dialog for smart card authentication hid behind the Zscaler tray in some scenarios.
        *   Fixes a latency issue with web traffic by enhancing DNS handling capability in Zscaler Client Connector.
        *   Fixes an issue with Microsoft Defender Antivirus device posture in a localized environment.
        *   Resolves an issue where PAC download redirections were added to the captive portal to bypass.
        *   Fixes an issue where the CrowdStrike posture evaluation failed in some scenarios after the user upgraded to Zscaler Client Connector version 4.2.0.190 for Windows.

**July 27, 2023**

*   Release in Limited Availability: Client Connector 4.3 for Windows

    *   ### **Zscaler Client Connector 4.3 Enhancements and Fixes**

This version of Zscaler Client Connector has a known issue with some embedded web pages appearing blank if you use WebView2. To learn more, see [Zscaler Client Connector Displays Blank Page](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-displays-blank-page).

        *   Provides support for ZDX Autosense probes by dynamically detecting and probing the endpoints. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Supports excluding traffic from applications based on Application Identity attributes. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) and [About Application Bypass](https://help.zscaler.com/zscaler-client-connector/about-application-bypass-info).
        *   Restricts access to Zscaler's registry to privileged processes.
        *   Adds support for downloading the default PAC file using HTTPS.
        *   Allows administrators to bypass the IdP traffic during Zscaler Client Connector authentication. To learn more, see [Configuring Defined Application Segments](https://help.zscaler.com/zpa/configuring-application-segments#define-clientconnector).
        *   Adds a default bypass for partner tenants when the partner being added is on a different cloud.
        *   Provides the ability to configure the Zscaler Client Connector synthetic IP range used by ZPA.
        *   Adds enhancements to improve DNS traffic processing for ZIA and ZPA services.
        *   Provides support for retrieving the updated policy based on Device Groups within the App Profiles section of the Zscaler Client Connector Portal. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles#groups).
        *   Admins can allow non-admins access to the Zscaler Client Connector logs folder. To learn more, see [Allowing Non-Administrators to Access Client Connector Log Files](https://help.zscaler.com/zscaler-client-connector/allowing-non-administrators-access-client-connector-log-files).
        *   Upgrades Npcap version to 1.75.
        *   Supports integration of Endpoint Data Loss Prevention service with Zscaler Client Connector. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles) and [Zscaler Endpoint Data Loss Protection (DLP) Integration with Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/zscaler-endpoint-data-loss-protection-dlp-integration-zscaler-client-connector).
        *   Allows Gov customers to add partner tenants if the tenants are on the same cloud.
        *   Supports creation of [custom app bypass rules](https://help.zscaler.com/zscaler-client-connector/adding-ip-based-applications-bypass-traffic) for packet tunnel exclude filters.
        *   Zscaler Client Connector notifies users when a [captive portal is detected](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-connection-status-errors) and allows them to launch their default browser to show the captive portal.
        *   Fixes a Zscaler Client Connector crash issue during the registration process.
        *   Fixes an intermittent network disconnect issue on select ports when working with the DLP solution.
        *   Fixes an issue where the ZIA Disaster Recovery feature did not work on Palo Alto Network’s GlobalProtect remote access VPN.
        *   Supports rate-limiting in disaster recovery [notifications](https://help.zscaler.com/zscaler-client-connector/viewing-notifications-zscaler-client-connector) to prevent frequent and duplicate notifications.
        *   Fixes an issue where users couldn’t log into Zscaler Client Connector when enrolled via the Strict Enforcement mode.
        *   Fixes an issue with username validation when logging into Zscaler Client Connector.
        *   Fixes an issue with OS device posture detection for Windows 11 virtual machines (VMs) in Azure.
        *   Fixes an issue where the Zscaler Client Connector app's drop-down menu opened on a different monitor for users connected to multiple displays.
        *   Fixes an issue with Microsoft Defender Antivirus device posture in a localized environment.

**July 10, 2023**

*   Release Available: Client Connector 4.2.0.190 for Windows

    *   ### **Zscaler Client Connector 4.2.0.190 Enhancements and Fixes**

        *   Fixes an issue with Zscaler Client Connector where users are not immediately redirected to the captive portal when using Z-Tunnel 2.0 in some scenarios.
        *   Fixes an issue where the Zscaler Private Access (ZPA) client certificate posture check failed for devices using Zscaler Client Connector version 4.0.0.89 for Windows.
        *   Fixes an issue where ZPA was stuck in a connecting state in some scenarios.
        *   Fixes an issue where Zscaler Client Connector stayed in a connecting state for a few minutes when connected to ZPA.
        *   Fixes an issue where CrowdStrike users experienced connectivity issues in certain conditions when they upgraded the CrowdStrike app to a later version.
        *   Fixes a user access control issue by enforcing more validations when handling log files.
        *   Fixes an issue where Z-Tunnel 2.0 ran into a deadlock in a specific condition for users using Domain Name for ZIA Public Service Edges in PAC.
        *   Fixes an issue where DNS requests were being sent directly when a device woke up from sleep.
        *   Fixes an issue where, in certain cases, anti-tampering can be disabled (CVSS Base Score: 7.8 - High).
        *   Fixes an issue with local privilege escalation in Zscaler Client Connector for Windows devices (CVSS Base Score: 7.3 - High).

**June 12, 2023**

*   Release in Limited Availability: Client Connector 4.2.0.178 for Windows

    *   ### **Zscaler Client Connector 4.2.0.178 Enhancements and Fixes**

        *   Fixes an issue where the user experienced a Zscaler Client Connector application crash after restarting the device in some scenarios.
        *   Fixes a Zscaler Client Connector authentication flow issue with WebView2.
        *   Fixes a ZIA posture trust evaluation issue that appeared in some scenarios.

**May 26, 2023**

*   Release in Limited Availability: Client Connector 4.2.0.173 for Windows

    *   ### **Zscaler Client Connector 4.2.0.173 Enhancements and Fixes**

Issues resolved as part of version 4.1.0.102 for Windows were also included in this release. Refer to the [Windows 4.1.0.102 release notes](https://help.zscaler.com/zscaler-client-connector/client-connector-app-release-summary-2023?applicable_category=Windows&applicable_version=4.1.0.102&deployment_date=2023-05-23) for details.

        *   Fixes an issue where machine tunnel enrollment failed because of an issue with the policy download.
        *   Fixes an issue with anti-tampering by adding additional configurations for registry protection.
        *   Fixes an issue where the Force ZPA Reauthentication feature did not work as expected in some conditions.
        *   Fixes an issue where adding partner tenants failed due to an issue in domain name validation.
        *   Fixes an intermittent issue where bypassed traffic didn’t get excluded due to a race condition in some scenarios.

**May 23, 2023**

*   Release Available: Client Connector 4.1.0.102 for Windows

    *   ### **Zscaler Client Connector 4.1.0.102 Enhancements and Fixes**

        *   Fixes an issue where some users received a Driver Error for ZPA when installing a lightweight filter driver with GlobalProtect VPN enabled.
        *   Fixes an issue where Zscaler Client Connector was uninstalled after an auto-upgrade from Windows Zscaler Client Connector version 3.7.1.53 to Windows Zscaler Client Connector version 4.0.0.89.
        *   Fixes an issue where Zscaler Client Connector app profile bypasses didn’t work in scenarios where the default interface had more than one IP address.
        *   Fixes an issue where the Zscaler Deception service failed to update in some scenarios.

**April 21, 2023**

*   Release Available: Client Connector 4.1.0.98 for Windows

    *   ### **Zscaler Client Connector 4.1.0.98 Enhancements and Fixes**

        *   Fixes an issue where upgrading to Zscaler Client Connector version 4.0.0.70 caused the ZSATrayManager service to go to 25% CPU usage in a VDI pool.
        *   Fixes an issue where Zscaler Private Access (ZPA) didn’t connect and displayed the Connection Error message on Zscaler Client Connector version 3.9.0.175 for Windows.
        *   Fixes an issue where some users experienced PAC configuration issues when connected to a VPN Trusted Network.
        *   Fixes an issue where the nslookup command, which targets a DNS request to a specific DNS server by domain, failed on devices running Zscaler Client Connector with only an IPv4 address if the DNS domain is a Zscaler Private Access (ZPA) application. To learn more, see [DNS Request Failure in Zscaler Client Connector version 4.1.0.89](https://help.zscaler.com/zscaler-client-connector/dns-request-failure-zscaler-client-connector-version-4.1.0.89).
        *   Fixes an issue where users lost access to internal resources after upgrading to Zscaler Client Connector version 4.1.0.82.
        *   Fixes an issue where the Zscaler Client Connector system tray icon displayed the Service is Disabled message, although no services were affected.
        *   Fixes an issue where disabling Zscaler Internet Access (ZIA) did not always prompt users to enter a reason for disabling the service.
        *   Fixes an issue where Microsoft Outlook and Microsoft Teams applications were not accessible in Tunnel with Local Proxy (TWLP). To learn more, see [Microsoft Outlook and Microsoft Teams Not Accessible](https://help.zscaler.com/zscaler-client-connector/microsoft-outlook-and-microsoft-teams-not-accessible).
        *   Fixes an issue where Zscaler Client Connector didn't evaluate the Detect Antivirus posture type within the usual time frame after a reboot because of a race condition with the Windows Security Center Service.

**April 12, 2023**

*   Release in Limited Availability: Client Connector 4.2 for Windows

    *   ### **Zscaler Client Connector 4.2 Enhancements and Fixes**

        *   Supports configuring dynamic Zscaler Internet Access (ZIA) Public Service Edge use based on latency settings for the probe interval, frequency, and threshold limit. To learn more, see [Configuring Forwarding Profiles for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-forwarding-profiles-zscaler-client-connector).
        *   Assigns a virtual IP address to Zscaler Client Connector to support some Server-to-Client applications such as remote troubleshooting and software distribution.
        *   Adds the ability to configure the VPN name for flow logging.
        *   Adds configurations to display critical notifications until users dismiss them for Zscaler Private Access (ZPA) reauthentication, system reboot requests, captive portal, and packet capture. To learn more, see [Configuring End User Notifications for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-end-user-notifications-zscaler-client-connector).
        *   Enhances the built-in browser for IdP authentication by using WebView2 based on configuration. Supports multi-factor authentication (MFA) and Fast Identity Online (FIDO) authentication methods for YubiKey.
        *   Supports configuring a one-time password (OTP) to disable anti-tampering and an option to reactivate anti-tampering after a user disables it, and enables the Zscaler Endpoint Protection (ZEP) driver to accept root CA certificate validation for trusted processes in the ZEP policy. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Enhances the Learn More link with new error codes reported by Zscaler Client Connector.
        *   Supports tracking the Zscaler Client Connector upgrade history (i.e., version) and date and time the version was installed.
        *   Adds additional end user notification controls configured in the Zscaler Client Connector Portal.
        *   Supports force reverting to a previous version in the Zscaler Client Connector App Store. To learn more, see [About the Zscaler Client Connector Store](https://help.zscaler.com/zscaler-client-connector/about-zscaler-client-connector-store).
        *   Supports blocking access to keyboard shortcuts and the drag-and-drop procedure in Zscaler Client Connector WebBrowser Control (WBC).
        *   Supports configuring advance authentication notification alerts for ZPA applications. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Supports including browser-based configurations in keepalive policy downloads.
        *   Supports the PcapNG file format for packet capture.
        *   Adds a configurable option to force ZPA authentication if a user’s computer goes into sleep or hibernation, on system restart, or because of a network IP change. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Supports configuring a preferred port to enable Zscaler Client Connector to SME server communication. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Improves accessibility with keyboard navigation.
        *   Ensures that new Zscaler Digital Experience (ZDX) versions remain compatible with older Zscaler Client Connector versions.
        *   Allows configuration of the default RDP 3389 bypass.
        *   Supports the Windows 365 platform with some additional configuration. To learn more, see [Configuring Zscaler Client Connector for Microsoft 365 Cloud PCs](https://help.zscaler.com/client-connector/configuring-zscaler-client-connector-microsoft-365-cloud-pcs).
        *   Improves the process of updating a large number of GPO rules during the Windows logon process.
        *   Supports Advanced RISC Machines (ARM) architecture in 64-bit with AMD64 builds in compatibility mode. ARM64 is not natively supported.
        *   Supports ZPA client-to-client (C2C) remote assistance for Azure Active Directory (AD) domain-joined and hybrid-joined devices, while still supporting enterprise local domain-joined devices.
        *   Removes support of legacy cipher suites from Zscaler Client Connector TLS connections.
        *   Supports adding certificate template information of the issued certificate for the Client Certificate posture type to confirm that the certificate contains the correct value. To learn more, see [Configuring Device Posture Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-device-posture-profiles).
        *   Provides the ability to use the Zscaler Client Connector synthetic IP range on the LAN address space, based on Zscaler Client Connector Portal configuration. To learn more, see [Configuring the Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range). (CVE-2023-28803).
        *   Supports the SCCM VPN Boundary method with ZPA to support client-initiated SCCM use cases for content delivery.
        *   Fixes an issue where reauthentication failed after users' systems woke up from hibernation.
        *   Fixes an issue where a logon script lost connectivity when Zscaler Client Connector transitioned from machine tunnel to user tunnel.
        *   Fixes an issue where, in some configurations, users received a blank IdP page after logging out and then logging back in when not connected to a VPN.
        *   Fixes an issue where a client-to-client (C2C) request timed out before forwarding the ICMP request over ZPA.
        *   Fixes an issue where traffic received an SSL handshake error if the maximum transmission unit (MTU) was incorrectly set.
        *   Improves DNS inclusions and exclusions for Z-Tunnel 2.0 by comparing the DNS Domains and matching against the longest domain name suffix. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-profiles).
        *   Fixes an issue where Zscaler Client Connector wasn’t using the configured cipher suite in the TLS connection to SVPN.
        *   Fixes an issue where users were disconnected from ZPA and the connection on port 443 couldn't be established because of a configuration change in the ZPA Admin Portal.
        *   Fixes an issue where users were not able to bypass the 100.64.1.0 -100.64.255.255 subnet traffic when added to a VPN bypass or the Destination Exclusions list for ZIA customers only.
        *   Fixes an issue where users received an internal error in Zscaler Client Connector when connecting to the network on a docking station because the local DNS proxy did not restart.
        *   Fixes an issue where Zscaler Client Connector did not tunnel DNS traffic over TCP for Z-Tunnel 2.0.
        *   Fixes an issue where Zscaler Client Connector fell back to IP address 8.8.8.8/8.8.4.4 on port 53 when no system DNS server was detected.
        *   Fixes an issue where a user could disable ZIA or ZPA from the Machine Tunnel diagnostics page without a password. (CVE-2023-28802).

**March 27, 2023**

*   Release Available: Client Connector 4.1.0.89 for Windows

    *   ### **Zscaler Client Connector 4.1.0.89 Enhancements and Fixes**

Zscaler Client Connector version 4.1 has a known issue with DNS request failures when using the nslookup command for IPv4-only devices. For more information, see [DNS Issues with Zscaler Client Connector version 4.1](https://help.zscaler.com/zscaler-client-connector/dns-request-failure-zscaler-client-connector-version-4.1.0.89).

        *   Fixes an issue where a logon script lost connectivity when Zscaler Client Connector transitioned from machine tunnel to user tunnel.
        *   Fixes an issue where users were not able to bypass the 100.64.1.0 - 100.64.255.255 subnet traffic when added to a VPN bypass or the Destination Exclusions list for Zscaler Internet Access (ZIA) customers only.
        *   Fixes an issue where Zscaler Client Connector sent IPv6 packets to non-IPv6 capable SME servers, causing Z-Tunnel 2.0 disconnections.
        *   Fixes an issue where users received an internal error in Zscaler Client Connector when connecting to the network on a docking station because the local DNS proxy did not restart.

**March 20, 2023**

*   Release Available: Client Connector 3.9.0.191 for Windows

    *   ### **Zscaler Client Connector 3.9.0.191 Enhancements and Fixes**

        *   Fixes an issue where a logon script lost connectivity when Zscaler Client Connector transitioned from machine tunnel to user tunnel.
        *   Fixes an issue where users were not able to bypass the 100.64.1.0 -100.64.255.255 subnet traffic when added to a VPN bypass or the Destination Exclusions list for Zscaler Internet Access (ZIA) customers only.

**March 07, 2023**

*   Release in Limited Availability: Client Connector 4.1.0.85 for Windows

    *   ### **Zscaler Client Connector 4.1.0.85 Enhancements and Fixes**

        *   Provides the ability to use the Zscaler Client Connector synthetic IP range on the LAN address space, based on a Zscaler Client Connector Portal configuration. To learn more, see [Configuring the Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range). (CVE-2023-28803).
        *   Fixes an issue where Zscaler Client Connector forwarded traffic directly because of a PAC parser issue when an invalid domain was accessed.
        *   Fixes an issue where users were disconnected from Zscaler Private Access (ZPA) and the connection on port 443 couldn't be established because of a configuration change in the ZPA Admin Portal.

**March 02, 2023**

*   Release Available: Client Connector 4.0.0.89 for Windows

    *   ### **Zscaler Client Connector 4.0.0.89 Enhancements and Fixes**

        *   Provides the ability to use the Zscaler Client Connector synthetic IP range on the LAN address space, based on a Zscaler Client Connector Portal configuration. To learn more, see [Configuring the Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range). (CVE-2023-28803).
        *   Fixes an issue where users ran into connection errors in some scenarios when multiple active network interfaces were present on the system.
        *   Fixes an issue where the SCCM CMG registry key was incorrectly set after upgrading to Zscaler Client Connector version 4.0.0.70.
        *   Fixes an issue where Zscaler Client Connector removed routes for Zscaler Private Access (ZPA) applications.
        *   Fixes an issue where Zscaler Client Connector forwarded traffic directly because of a PAC parser issue when an invalid domain was accessed.
        *   Fixes an issue where users were disconnected from ZPA and the connection on port 443 couldn't be established because of a configuration change in the ZPA Admin Portal.
        *   Fixes an issue where the Zscaler Client Connector MSI installer file was not created in the Program Files folder during the repair app process.

**February 22, 2023**

*   Release Available: Client Connector 3.9.0.189 for Windows

    *   ### **Zscaler Client Connector 3.9.0.189 Enhancements and Fixes**

        *   Provides the ability to use the Zscaler Client Connector synthetic IP range on the LAN address space, based on a Zscaler Client Connector Portal configuration. To learn more, see [Configuring the Zscaler Client Connector Synthetic IP Range](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-synthetic-ip-range). (CVE-2023-28803).
        *   Fixes an issue where DNS traffic was bypassed from Z-Tunnel 2.0 in some scenarios.
        *   Fixes an issue where users ran into connection errors in some scenarios when multiple active network interfaces were present on the system.

**February 14, 2023**

*   Release in Limited Availability: Client Connector 4.1.0.82 for Windows

    *   ### **Zscaler Client Connector 4.1.0.82 Enhancements and Fixes**

Zscaler Client Connector version 4.1.0.82 and later has a known issue where Microsoft Outlook and Microsoft Teams applications are not accessible in Tunnel with Local Proxy (TWLP) in some scenarios. To learn more, see [Microsoft Outlook and Microsoft Teams not accessible](https://help.zscaler.com/zscaler-client-connector/microsoft-outlook-and-microsoft-teams-not-accessible).

        *   Fixes an issue where client-to-client (C2C) timed out before forwarding the ICMP request over Zscaler Private Access (ZPA).
        *   Fixes an issue where VPN trusted network tunneling might send traffic directly after upgrading to Zscaler Client Connector version 3.9.0.175.
        *   Fixes an issue with Autopilot and strict enforcement.
        *   Fixes an issue where users received an Internet Security Safe Mode Failure notification because of a timeout error.
        *   Fixes an issue where the SCCM CMG registry key was incorrectly set after upgrading to Zscaler Client Connector version 4.0.0.70.
        *   Fixes an issue where traffic could receive an SSL handshake error if the maximum transmission unit (MTU) was incorrectly set.
        *   Fixes an issue where users received an error when trying to log in to the partner tenant because the partner tenant did not belong to the same cloud as the main tenant.
        *   Fixes an issue where users were not able to access file drive shares and the Group Policy Update command (gpupdate) failed with a connectivity error to the Active Directory (AD).
        *   Fixes an issue where Zscaler Client Connector removed routes for ZPA applications.
        *   Fixes an issue where a PAC file didn’t remain in the system proxy after moving from a trusted network to an off trusted network and then back to a trusted network.

