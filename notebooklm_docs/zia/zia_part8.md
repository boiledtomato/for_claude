# Zscaler Help — ZIA — Internet & SaaS (part 8)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-10-05 09:38 UTC
Articles in this file: 1

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-traffic-bypasses","lastmod":"2026-06-02T23:36Z","nid":"1440656"} -->
## Zscaler Traffic Bypasses

- Source: https://help.zscaler.com/zia/zscaler-traffic-bypasses
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Traffic Forwarding > Zscaler Traffic Bypasses
- Last modified: 2026-06-02T23:36Z
- Summary: Information on traffic bypasses that are available in the Zscaler cloud.

This article provides detailed information about the types of traffic bypasses available in the Zscaler cloud. The following sections illustrate how you can bypass certain application traffic or web traffic in the Zscaler cloud.

- Zscaler Client Connector
- SSL/TLS Inspection
- Firewall Control Policy

Zscaler Client Connector has an Application Bypass feature that allows you to automatically bypass specific applications from being tunneled through Zscaler Tunnel (Z-Tunnel) 2.0. You need to add the applications that you want to bypass in the **Application Bypass** field while configuring the app profiles in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).

This application bypass is only applicable for [Windows](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles?referer=mobileadmin.zscalerbeta.net#windows) and [macOS](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles?referer=mobileadmin.zscalerbeta.net#macOS) app profiles and if you use Z-Tunnel 2.0 as your forwarding profile in Zscaler Client Connector. You can also view the details of the bypassed applications on the [Application Bypass](https://help.zscaler.com/zscaler-client-connector/viewing-information-bypassed-applications-z-tunnel-2.0-configuration) page.

Full visibility of the traffic bypassed by Zscaler Client Connector, such as bypassed session, bypassed session event time, and flow type is logged and displayed in the [Web](https://help.zscaler.com/zia/web-insights-logs-columns) and [Firewall](https://help.zscaler.com/zia/firewall-insights-logs-columns) Insights Logs in the Zscaler Admin Console.

See sample Web Logs.

See sample Firewall Logs.

## Application-Based Bypass in Android

Zscaler recommends bypassing Android application traffic. While configuring the [Android app profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles?referer=mobileadmin.zscalerbeta.net#android), you can automatically bypass standard messaging applications on Android using the following steps:

1. Enable the **Bypass Traffic for MMS Applications** field to ensure that Android does not interfere with MMS, which also uses mobile data.
2. Add the app's identifier to the **Bypass Traffic for Specific Applications** field to bypass specific Android application traffic. You can find the app's identifier after the ID parameter in the URL of the app's Play Store details. For custom applications (i.e., applications not available in the Play Store), add the package name instead of the app’s identifier in the field.

If you are not aware of the package name of the application, use PAC file-based exclusion to bypass specific hostnames and IP addresses. To do that, configure a [custom PAC file](https://help.zscaler.com/zia/using-custom-pac-file-forward-traffic-internet-saas) in the Zscaler Admin Console to bypass the Google FQDNs, and add that PAC URL to the **Custom PAC URL** field while adding an Android app profile.

The Zscaler exception list for SSL/TLS inspection includes a few dozen known domains or destinations, such as Zscaler service IP addresses for Zscaler best practices, `contactservice.zoom.us` for UCaaS bypass, and `self.events.data.microsoft.com` for Microsoft 365 bypass events, which cannot be SSL/TLS inspected for various reasons. These domains are not exposed as they are subject to change.

The predefined **Zscaler Recommended Exemptions** rule under **Policies** >**Common Configuration**>**SSL/TLS Inspection**>**SSL/TLS Inspection Policy**automatically exempts known destinations that cannot be SSL/TLS inspected when it is enabled. If you want to SSL/TLS inspect certain domains from the exempted list, create an inspection rule with a higher rule order. Disable the predefined rule for SSL/TLS inspecting all domains. To learn more, see [About SSL/TLS Inspection Policy](https://help.zscaler.com/zia/about-ssltls-inspection-policy).

You can find transactions that are not SSL/TLS inspected or blocked after an inspection under the **SSL/TLS Policy Reason** filter in [Web Insights Logs](https://help.zscaler.com/zia/web-insights-logs-columns). The percentage of traffic automatically SSL/TLS bypassed is relatively small (less than 1%).

See sample Web Logs.

SSL/TLS inspection also does not work on applications that use [certificate pinning](https://help.zscaler.com/zia/certificate-pinning-and-ssltls-inspection) because the client application is hardcoded to accept only one specific client certificate. They should be included in the list of URL categories for which SSL/TLS transactions are not decrypted. To learn how to manage certificate pinning exempted applications, see [Certificate Pinning and SSL/TLS Inspection](https://help.zscaler.com/zia/certificate-pinning-and-ssltls-inspection).

The following are some of the Firewall Control policy rules applied to traffic that is bypassing the Zscaler cloud:

- Zscaler Bypass Traffic
- Blocked by Web Proxy Policy
- Other Implicit Rules for Traffic Bypasses

The Firewall Control policy has a predefined implicit **Zscaler Bypass Traffic**rulewhich applies to traffic that matches the following conditions. Transactions that match this rule are logged and displayed along with the rule name in [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-columns) and [DNS Insights Logs](https://help.zscaler.com/zia/dns-insights-logs-columns).

- Firewall Logs
- DNS Logs

Zscaler recommends keeping the predefined **Zscaler Proxy Traffic** rule enabled. When the recommended predefined rule is disabled, the **Zscaler Bypass Traffic** rule is triggered.

The **Zscaler Bypass Traffic** rule matches with traffic that meets the following criteria and populates in the Firewall Logs:

- The web module does not accept control for the traffic evaluation from the firewall module because the handshake abruptly terminates during flow establishment.
- The traffic is forwarded from a firewall-enabled sublocation but not enabled to the parent location or another sublocation under the same parent location. This happens due to the latency in identifying these location differences, such as in the X-Forwarded-For (XFF) configuration.
- Traffic is destined to a Zscaler-owned IP address. For HTTP CONNECT requests destined to a virtual IP (VIP) address, this rule triggers the first policy match unless the inner traffic is destined elsewhere.
- Traffic forwarded using GRE tunnels matches this rule.
- Zscaler web proxy instructs the firewall that a connection should not pass through firewall policy enforcement.
- A sublocation has firewall disabled while other sublocations within the same parent location have it enabled, and there are policies applied to the sublocation (e.g., [Source IP Anchoring](https://help.zscaler.com/zia/configuring-source-ip-anchoring) or [Forwarding rules](https://help.zscaler.com/zia/configuring-forwarding-control-policy)). In this scenario, the Public Service Edge for Internet & SaaS (ZIA) holds the session temporarily to evaluate all the sublocation's policies and apply the most suitable rule, logging the traffic with the Zscaler Bypass Traffic rule until evaluation is complete.

See sample Firewall Logs.

The Zscaler Bypass Traffic rule populates in the DNS logs when:

- The domain name in the DNS request query matches a Zscaler cloud domain.
- The DNS request query matches a Microsoft 365 endpoint listed in the [Microsoft 365 One Click predefined Firewall Filtering rules](https://help.zscaler.com/zia/about-predefined-firewall-filtering-rules#office-one-click) if enabled.
- The DNS response does not contain a resolved IP address or CNAME.
- The DNS response is not completely analyzed because of its resource record type. DNS Control performs a detailed analysis of responses for A, AAAA, CNAME, and PTR record types.

See sample DNS Logs.

This implicit rule is applied when a web policy blocks traffic while it is being evaluated by deep packet inspection to identify the network application to which the traffic belongs. You can view the transactions that match this rule in the [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-columns).

The traffic flow from users might bypass firewall or DNS modules when the following scenarios occur:

- When the Service Edge fails to establish a connection with the Zscaler Central Authority (CA), it results in the traffic flow passing through the firewall or DNS without a policy application. This might occur when traffic flow from a specific user or location arrives at the Service Edge for the first time and a connection to the CA is required to apply policies. Firewall logs this transaction, and you can view this in [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-filters) by applying the **Bypassed due to missing config**filter. See sample Firewall Insights Logs.
- If the Service Edge has established a connection with the CA, but the requested configuration does not arrive from the CA within the expected time period (typically 5 seconds), it results in the traffic flow passing through the firewall without a policy application. This might occur when traffic flow from a specific user or location arrives at the Service Edge for the first time and a connection to the CA is established, but there is no response from the CA within the expected timeframe. Firewall logs this transaction, and you can view this in [Firewall Insights Logs](https://help.zscaler.com/zia/firewall-insights-logs-filters) by applying the **Timed out while waiting for a config** filter. <p> <a class="image-icon" href="#timed-out-while-waiting-for-config-filter-logs">See sample Firewall Logs.</a> </p>

[Image: Web Logs traffic bypass columns]

[Image: Firewall Logs traffic bypass columns]

[Image: Web Logs page for SSL bypassed traffic]

[Image: Zscaler Bypass Traffic in the DNS Logs]

[Image: Firewall Logs page for Zscaler Bypass Traffic rule]

[Image: Bypassed due to missing config filter in Firewall Logs]

<div class="subc"> <p> <a class="ck-anchor" id="timed-out-while-waiting-for-config-filter-logs"></a><img src="/downloads/zia/traffic-forwarding/zscaler-traffic-bypasses/firewall-logs-timed-out-while-waiting-for-config.png" data-entity-uuid="0" data-entity-type="image" alt=" Timed out while waiting for config filter in Firewall Logs" title="Firewall logs filtered to show transactions matching Timed out while waiting for config action " width="1425" height="234"> </p> </div>
<!-- /ZS-ARTICLE -->
