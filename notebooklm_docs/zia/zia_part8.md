# Zscaler Help — ZIA — Internet & SaaS (part 8)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-10-12 09:45 UTC
Articles in this file: 12

---

<!-- ZS-ARTICLE {"url":"/zia/web-insights-logs-filters","lastmod":"2026-10-09T07:06Z","nid":"1401016"} -->
## Web Insights Logs: Filters

- Source: https://help.zscaler.com/zia/web-insights-logs-filters
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Dashboard & Analytics > Insights > Logs > Web Insights Logs: Filters
- Last modified: 2026-10-09T07:06Z
- Summary: Information on the different filters in the Web Insights Logs page in the Zscaler Admin Console.

Filters define the traffic information that you view in your Web Insights Logs. To learn more about logs, see [About Insights Logs](https://help.zscaler.com/zia/about-insights-logs).

Certain filters, like**Users**, **Departments**, **Locations**, and others, support the selection of multiple values. For these, you can select up to 200 values in a single filter. You can also choose to include or exclude the selected values. Also, certain filters support additional operators (i.e., Does Not Contain, Does Not Start With, Does Not End With, Is Null, Is Not Null) for filters that perform string match, like **Advanced** **Threat Category** and others.

- There are certain filter combinations that appear together in Insights Logs when applied, but don't appear together in Insights. For example, the **Department** and **Location** filters appear together in Insights Logs when applied, but don't appear together in Insights.
- There are certain filter values where control is handed over to the firewall. For example, when the filter values are set to Tunnel or Tunnel SSL/TLS, control is handed to the firewall. The firewall completes the remaining inspection and applies a policy according to the rules. While these filter values indicate that control is handed over to the firewall, it is recommended that you always check the firewall logs to confirm the same.

Following are the web log filters you can select:

- Adaptive Access Profile
- Advanced Threat Category
- AI/ML Detection Source
- ALPN Protocol
- Allowed File Type Rule
- App Instance Level 1
- App Instance Level 2
- App Instance Level 3
- Application Activity
- Application Segment
- Application Status
- Bandwidth Class
- Bandwidth Rule
- Blocked Policy Type and Rule Name
- Bypassed Transaction
- Bypassed Transaction Event Time
- Certificate Chain Validity
- Certificate Expiry
- Client Connection Ciphers
- Client Connection TLS Version
- Client External IP
- Client IP
- Capture
- Client Digital Signature Proposal
- Client Key Exchange Proposal
- Client-Side Key Exchange Algorithm
- Client-Side Digital Signature Algorithm
- Client Source Port
- Client SSL/TLS Handshake Failure Aggregate Count
- Client SSL/TLS Handshake Failure Reason
- Cloud Application
- Cloud Application Class
- Cloud Application Policy Type and Name
- Configuration
- Data Center
- Department
- Destination IP Countries
- Device Hostname
- Device Model
- Device Name
- Device OS Type
- Device OS Version
- Device Owner
- DLP Confirmation Message
- DLP Dictionary
- DLP Engine
- DLP Identifier
- DLP MD5
- DLP Policy Action
- DLP Severity
- Document Type
- Domain Fronted Host Header
- Domain Fronted SNI
- Download File Name
- Download File Type
- Download File Type Super Category
- Download User-Defined File Type
- Enrolled Device appversion
- Exempted DLP Identifier
- External Device ID
- Extranet Resource
- File Share Activity
- Flow Type
- Forwarding Method
- Forwarding Rule
- Gateway IP
- Gateway Name
- HTTP Header Insertion Profile
- HTTP Header Profile
- IM Activity
- Intermediate CA Protection Type
- Is Destination IP Country Risky?
- Is Source IP Country Risky?
- JA4 Fingerprint
- JA4 Fingerprint A
- JA4 Fingerprint B
- JA4 Fingerprint C
- Location
- Location Group
- Location Type
- OCSP Result
- Other DLP Rules
- Prompt
- Prompt Classification
- Protocol
- Received Bytes
- Referrer Search
- Request Latency Due to Bandwidth Control (ms)
- Response Latency Due to Bandwidth Control (ms)
- Request Method
- Request Version
- Response HTTP Version
- Sandbox
- Sandbox Action
- Sandbox API Key
- Sandbox MD5
- Sent Bytes
- Server Certificate Self Signed
- Server Certificate Validation Type
- Server Certificate Validity Period
- Server Connection Ciphers
- Server Connection TLS Version
- Server Destination Port
- Server IP
- Server-Side Key Exchange Algorithm
- Server-Side Digital Signature Algorithm
- SHA-256
- Show Delayed Logs
- Social Networking Activity
- Source IP Countries
- SSL/TLS Inspected
- SSL/TLS Policy Name
- SSL/TLS Policy Reason
- Streaming Activity
- Subdocument Type
- Suspicious Content
- Threat Category
- Threat Class
- Threat Name
- Threat Severity
- Threat Super Category
- Throttled request bytes
- Throttled response bytes
- Total Bytes
- Total Latency Due to Bandwidth Control (ms)
- Traffic Forwarding
- Unscannable Type
- Upload File Name
- Upload File Type
- Upload File Type Super Category
- Upload User-Defined File Type
- URL Category
- URL Class
- URL Filtering Policy Name
- URL Search
- URL Super Category
- User
- User Group
- User Agent
- Policy Action
- Webmail Activity
- Zscaler Client Connector Tunnel Version

Use this filter to view transactions associated with a specific user group. You can choose to include or exclude the selected groups. The default option for this filter is **None**. You can search for specific user groups.

Use this filter to view transactions associated with an Adaptive Access profile. The default filter for this is **Any**. You can also choose to include or exclude the selected values.

Use this filter to view transactions in which advanced threats were detected. These advanced threats are detected by **Advanced Threat Protection**. The default option for this filter is **Any**. You can search for specific categories. You can also use the toggle to turn on **Threat Name Search** to enter all or part of the threat name in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**. The following categories appear under this filter:

- **Adware/Spyware Sites**: This category refers to any detections of websites known to contain adware or spyware based on the URL/IP reputation. The URLs/IPs added in this category would be associated with distributing adware or spyware, which can collect information related to the user’s browsing activities and display unwanted advertisements without the user’s consent.
- **Any**
- **Botnet Callback**: This is the most important threat category because the Sandbox detects post-infection activity, which requires you to follow up and remediate. There are two types of detections: destination URL reputation and content IPS signature, which are both part of Advanced Threat Protection. URL reputation-based detections, especially those that match only on a domain or even an IP address, are lower fidelity and have a lower chance of the affected endpoint actually being infected by a botnet agent. This is because some indicators detect when a user happens to visit a web destination that is known to serve or be associated with malicious botnet payloads or communication, so the user is protected preinfection. The IPS signature-based detections generally match on the botnet communication pattern/protocol, so they are more likely to signal that the affected endpoint is actually infected by a botnet agent.
- **Browser Exploit**: This category refers to any detections of known exploits against web browsers. These detections are often IPS signature-based detections, so they are high fidelity.
- **Cross-site Scripting**: This category refers to any detections that try to abuse an end user via a type of injection, in which malicious scripts are injected into benign and trusted websites. Cross-site scripting (XSS) attacks occur when an attacker uses a web application to send malicious code, typically through browser-side scripting, to a different end user. These detections are often IPS signature-based detections, so they are high fidelity.
- **Crypto Mining & Blockchain**: This category refers to any detections of crypto mining or crypto jacking activity or sites associated with malicious cryptocurrency activity. General cryptocurrency websites and content are not blocked by this category, but those that are designed to abuse user's devices without their consent via browser-based scripts that mine cryptocurrency. These detections are often IPS signature-based detections with the actual scripts hiding on or behind web pages, so they are high fidelity.
- **Domain Generation Algorithm (DGA) Domains**: This category refers to the domains that are suspected to be generated using domain generation algorithms (DGA). These algorithms are used in various malware families to periodically generate a large number of domain names that can be used by malware-infected devices to connect with command and control servers in order to circumvent the identification and shutting down of malicious domains.
- **Malicious Content**: This category refers to any detections of malware or websites known to host malware and other malicious content that isn't attributed to a specific threat type or category. The detection capabilities in this category are often based upon various signature types and patterns, such as URL reputation, IPS signatures, etc.
- **Other Threat**: This is a catchall category for any detections that might not have an appropriate mapping to a specific category.
- **Peer-to-Peer**: This category refers to any detections of peer-to-peer traffic via applications such as BitTorrent, Tor, and other anonymizer or file sharing applications. These applications can be across any port. You also must have Firewall for the Zscaler service to detect and block these applications.
- **Phishing**: This category is one of the most powerful detection capabilities in the Advanced Threat Protection policy. It refers to any detections that are both URL reputation and IPS content signature-based. The ThreatLabZ operations team focuses on writing new phishing IPS signatures based on the patterns discovered and extracted from the phishing attacks observed by the Zscaler service and outside threat intelligence sources and partners. ThreatLabZ proactively scans and reviews all newly registered domains to discover new phishing and credential stealing URLs and ensure phishing IPS signature coverage for any new phishing patterns.
- **Spyware Callback**: This category refers to any detections of communication and callback traffic associated with spyware agents and data transmission. The Zscaler service detects this content using high fidelity IPS signatures that match content patterns in web traffic.
- **Suspicious Content**: This category refers to any detections from the Suspicious Content Protection (PageRisk) engine. You can configure the Zscaler service to block users from accessing web pages with a high [Page Risk Index (PRI) score](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy#pagerisk). The Zscaler service analyzes malicious content on a web page (e.g, injected scripts, vulnerable ActiveX, zero-pixel iFrames, etc.) and creates a Page Risk Index. The service also analyzes data from the domain (e.g., hosting country, domain age, past results, links to high-risk top-level domains, etc.) and creates a Domain Risk Index. The Page Risk and Domain Risk Index are combined to produce a single PRI score. This score is then evaluated against the value you set.
- **Suspicious Destination**: This category refers to any detections of internet activity destined to specific countries where the website IP address geographically is located and hosted from. This detection is based on the countries you decide to block access to in your policies.
- **Unauthorized Communication**: This category refers to any detections of unauthorized, tunneling, or anonymizer traffic such as IRC traffic, SSH tunneling, Tor anonymizer traffic, etc. The Zscaler service detects this content via specialized IPS content signatures that match the traffic patterns associated with this kind of communication.
- **Web Spam**: This category refers to any URL detections from web-based email spam scams and specific phishing attacks within web email content. The Zscaler service detects this content using high fidelity IPS signatures that match content patterns in web traffic.

Use this filter to view transactions associated with a specific ALPN protocol. The default option for this filter is **Any**. You can search for specific ALPN protocols.

Use this filter to view transactions associated with an allowed File Type Control Rule policy. The default option for this filter is **None**.

Use this filter to view transactions associated with a specific AI/ML detection source. The default option for this filter is **Any**. You can choose to include or exclude certain detection sources and search for specific sources. The following detection sources appear for the filter:

- AI/ML - ATP - Domain Generation Algorithm
- AI/ML - ATP - Known Command & Control
- AI/ML - ATP - Page Risk
- AI/ML - ATP - Phishing
- AI/ML - ATP - Unknown Command & Control
- AI/ML - Sandbox - AI Instant Verdict
- AI/ML - URL Filtering - Content Categorization
- Unknown

Use this filter to view discovered instances associated with an organization, domain, or tenant. Enter all or part of the instance level ID in the text field and choose an operator such as: **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view discovered instances associated with a project, workspace, or site. Enter all or part of the instance ID in the text field and choose an operator such as: **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view discovered instances associated with a resource type. Enter all or part of the resource name in the text field and choose an operator such as: **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to limit the data to traffic associated with a specific country, as determined by the destination IP address. The default option for this filter is **None**. You can search for a specific country.

Use this filter to limit the data to check if the country associated with the destination IP address is risky or not.

Use this filter to limit data to check if the country associated with the source IP address is risky or not.

Use this filter to limit the data to traffic associated with a specific country, as determined by the source IP address. The default option for this filter is **None**. You can search for a specific country.

Use this filter to view transactions associated with an application’s activity. The default option for this filter is **Any**. You can search for a specific action on an application, such as:

- Comment
- Create
- Delete
- Download
- Edit
- Embedded AI (For Atlassian applications only)
- File Share
- Invite
- MCP
- Post
- Reaction
- Rename
- Send Mail
- Share
- Upload
- Upload/Attachments
- View

Use this filter to view transactions associated with a specific [application segment](https://help.zscaler.com/zpa/about-applications). The default option for this filter is **None**. You can search for specific application segments.

Use this filter to view if the cloud application is sanctioned or unsanctioned. The status is displayed as N/A if the URL is unknown and no cloud application is tagged in the transaction.

Use this filter to view transactions associated with a specific bandwidth class. The default option for this filter is **All**. You can search for specific classes.

Use this filter to view transactions associated with a specific bandwidth rule. The default option for this filter is **All**. You can search for specific rules.

Use this filter to see if the server certificate is signed by a Zscaler-trusted certificate authority or not. This filter applies to SSL/TLS inspected traffic. The default option for this filter is **None**. You can search for specific validations. The following validations appear under this filter:

- Fail
- Pass

Use this filter to see if the certificate presented by the server is valid or expired. This filter applies to SSL/TLS inspected traffic. The default option for this filter is **None**. You can search for specific answers. The following answers appear under this filter:

- No
- None
- Yes

Use this filter to view the cipher suite agreed upon during the SSL/TLS handshake between the client and the Public Service Edge for Internet & SaaS (ZIA). This filter applies to SSL/TLS inspected traffic. The default option for this filter is **None**. You can search for specific cipher suites.

The cipher suite names are listed in OpenSSL/TLS format. To view them in RFC format, use this [link](https://testssl.sh/openssl-iana.mapping.html) to map between the two notations.

Use this filter to see the version of TLS used for communication between the client and the Public Service Edge. This filter applies to SSL/TLS inspected traffic. The default option for this filter is **None**. You can search for specific TLS versions.

Use this filter to view transactions associated with the internet gateway location IP address. Choose **Match**and enter an IP address, a range of IP addresses, or an IP address and netmask, as shown in the examples below the text box.

Use this filter to view transactions associated with a source IP address. Enter either the internet gateway location IP address or the IP address of the client device. Choose **Match**and enter an IP address, a range of IP addresses, or an IP address and netmask, as shown in the examples below the text box.

Use this filter to view transactions associated with the source port number from where the traffic originated. When you select this filter, enter the **From**and **To**fields to view the logs within that range. This filter is disabled by default. To enable it, contact Zscaler Support.

Use this filter to view the number of times the client's SSL/TLS handshake attempts have failed. You can view transactions for a certain range of aggregate counts by entering a minimum and maximum value.

Use this filter to view the digital signature proposed by the client in signature_algorithms extension of ClientHello message during the SSL/TLS handshake process in a transaction. The default option for this filter is **None**. The following categories appear under this filter:

- **Classical**: Use this filter to view the classical key exchange algorithm proposed by the client during the SSL/TLS handshake process.
- **Hybrid**: Use this filter to view the hybrid key exchange algorithm proposed by the client during the SSL/TLS handshake process in a transaction.
- **PQC**: Use this filter to view the pure post-quantum cryptography (PQC) key exchange algorithm proposed by the client during the SSL/TLS handshake process in a transaction.
- **None**: Use this filter to view all non-TLS related transactions.
- **Unknown**: Use this filter to view the TLS-related transaction whose value could not be determined.

Use this filter to view key exchange algorithms proposed by the client in supported_groups extension of ClientHello message during the SSL/TLS handshake process in a transaction. The default option for this filter is **None**. The following categories appear under this filter:

- **Classical**: Use this filter to view transactions where a client proposed a classical key exchange algorithm during the SSL/TLS handshake process.
- **Hybrid**: Use this filter to view transactions where a client proposed a hybrid key exchange algorithm during the SSL/TLS handshake process in a transaction.
- **None**: Use this filter to view all non-TLS related transactions.
- **PQC**: Use this filter to view transactions where a client proposed a pure post-quantum cryptography (PQC) key exchange algorithm during the SSL/TLS handshake process.
- **Unknown**: Use this filter to view transactions with a non-standard key exchange algorithm proposed by a client during the SSL/TLS handshake process.

Use this filter to view either the key exchange algorithm agreed upon during the SSL/TLS handshake between the client and the Service Edge in the case of SSL/TLS inspected traffic, or the key exchange algorithm agreed upon during the TLS handshake between the client and the server in the case of bypassed traffic. The default option for this filter is **None**.

Use this filter to view either the digital signature algorithm agreed upon during the SSL/TLS handshake between the client and the Service Edge in the case of SSL/TLS inspected traffic, or the digital signature algorithm agreed upon during the SSL/TLS handshake between the client and the server in the case of bypassed traffic. The default option for this filter is **None**.

Use this filter to view either the key exchange algorithm agreed upon during the SSL/TLS handshake between the server and the Service Edge in the case of SSL/TLS inspected traffic, or the key exchange algorithm agreed upon during the SSL/TLS handshake between the server and the client in the case of bypassed traffic. The default option for this filter is **None**.

Use this filter to view either the digital signature algorithm agreed upon during the SSL/TLS handshake between the Service Edge and the client in the case of SSL/TLS inspected traffic, or the digital signature algorithm agreed upon during the SSL/TLS handshake between the server and the client in the case of bypassed traffic. The default option for this filter is **None**.

Use this filter to view client SSL/TLS handshake failure transactions. The default option for this filter is **None**. The following SSL/TLS failure reasons appear under this filter:

- Access Denied
- Bad Cert Status Resp or Hash Value
- Bad or Unsupported Certificate
- Bad Record Mac
- Certificate Revoked or Expired
- Certificate Unknown
- Certificate Unobtainable
- Close Notify or Client TCP Reset
- Decode Error
- Decrypt Error
- Handshake Failure
- Illegal Parameter
- Insufficient Security
- Internal Error
- N/A
- No Application Protocol
- No Renegotiation
- Other Handshake Error
- Protocol Version
- Record Overflow
- Unexpected Message
- Unknown CA
- Unknown PSK Identity
- Unrecognized Name
- Unsupported Extension
- User Canceled

To learn more, refer to [RFC 2246 The TLS Protocol](https://www.ietf.org/rfc/rfc2246.html#section-7.2.2).

Use this filter to view transactions associated with a specific cloud app. The default option for this filter is **All**. You can search for specific apps.

- To view all the applications that appear under this filter, see [Cloud App Categories](https://help.zscaler.com/zia/cloud-app-categories).

The Miscellaneous <Cloud Application Category> Apps (e.g., Miscellaneous Finance Apps) option represents all the newly added lesser-known predefined applications for the category (e.g., Finance). The log displays results for each application grouped inside this option individually.

Use this filter to view transactions associated with a specific cloud app class. The default option for this filter is **All**. You can choose to include or exclude certain locations. You can search for specific apps. The following categories appear under this filter:

- Administration
- Collaboration and Online Meetings
- Consumer
- DNS Over HTTPS Services
- File Sharing
- General Browsing
- Hosting Providers
- Human Resources
- Instant Messaging
- IT Services
- Peer-to-Peer
- Productivity and CRM Tools
- Sales & Marketing
- Social Networking
- Streaming Media
- System & Development
- Web Mail
- Web Search

Use this filter to view the cloud application policy type and name that took action during the transaction. Following are the policy types that appear in this field:

- **Collaboration & Online Meetings App Control**: This refers to the [Collaboration & Online Meetings rule](https://help.zscaler.com/zia/adding-collaboration-online-meetings-rule-for-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Consumer**: This refers to the [Consumer rule](https://help.zscaler.com/zia/adding-consumer-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **DNS Over HTTPS Services**: This refers to the [DNS Over HTTPS Services Rule](https://help.zscaler.com/zia/adding-dns-over-https-services-rule-cloud-app-control) for the [Could App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **File Sharing**: This refers to the [File Sharing rule](https://help.zscaler.com/zia/adding-file-sharing-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Finance**
- **Health Care**
- **Hosting Providers**: This refers to the [Hosting Providers rule](https://help.zscaler.com/zia/adding-hosting-providers-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Human Resources**: This refers to the [Human Resources rule](https://help.zscaler.com/zia/adding-human-resources-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Instant Messaging**: This refers to the [Instant Messaging rule](https://help.zscaler.com/zia/adding-instant-messaging-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **IT Services**: This refers to the [IT Services rule](https://help.zscaler.com/zia/adding-it-services-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Legal**
- **Productivity and CRM Tools**: This refers to the [Productivity & CRM tools rule](https://help.zscaler.com/zia/adding-productivity-crm-tools-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Sales & Marketing**: This refers to the [Sales & Marketing rule](https://help.zscaler.com/zia/adding-sales-marketing-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Social Networking**: This refers to the [Social Networking & Blogging rule](https://help.zscaler.com/zia/adding-social-networking-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **System & Development**: This refers to the [System & Development rule](https://help.zscaler.com/zia/adding-system-development-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Video Streaming**
- **Webmail**: This refers to the [Webmail rule](https://help.zscaler.com/zia/adding-webmail-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).

Use this filter to limit the data to traffic associated with a specific data center.

Use this filter to view transactions associated with a specific department. The default option for this filter is **All**. Select **Hide Deleted**if you want to remove deleted departments from the list. Click **Select All** to select all the configured departments. You can search for specific departments.

Use this filter to view transactions associated with a specific hostname from support devices. Enter all or part of the device model in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**. This filter is not available for admins with [device information obfuscation](https://help.zscaler.com/zia/obfuscating-device-information-admins) enabled.

Use this filter to view transactions associated with a specific device model. Enter all or part of the device model in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with a specific device name. Enter all or part of the device name in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**. This filter is not available for admins with [device information obfuscation](https://help.zscaler.com/zia/obfuscating-device-information-admins) enabled.

Use this filter to view transactions associated with a specific device OS type. Enter all or part of the device OS type in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with a specific device OS version. Enter all or part of the device OS version in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with a specific device owner. Enter all or part of the device owner in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**. This filter is not available for admins with [device information obfuscation](https://help.zscaler.com/zia/obfuscating-device-information-admins) enabled.

Use this filter to view the message entered by a user to justify a transaction that triggers a DLP rule with the **Confirm**action. Enter all or part of the confirmation message and choose **Contains**, **Starts With**, **Ends With**, **Exact** **Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to see which transactions contain this dictionary as a trigger. If a dictionary was triggered, the name of the dictionary is displayed along with a match count indicating the search score or match count for this dictionary. The default option for this filter is **All**. You can search for specific DLP dictionaries.

Use this filter to view transactions in which data leakage was detected. The default option for this filter is **Any**. You can search for specific DLP engines.

Use this filter to identify transactions where a DLP rule triggered Auditor or Incident Receiver notifications as a part of the policy action. The default option for this filter is **Any**.

Use this filter to search for the transactions using this DLP identifier. Whenever a DLP rule is hit, and the appropriate alert is configured, an email containing this ID is sent to your auditors. Use it as a filter to locate the exact transaction. You can search for specific DLP identifiers.

Use this filter to view transactions associated with a specific enrolled device app version. Enter all or part of the enrolled device app version in the text field and choose an operator such as: **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with an extranet resource. You can also choose to include or exclude the selected values. The default option for this filter is **Any**.

Use this filter to search for the unique DLP identifier of transactions exempted from policy enforcement because the DLP scan timed out. Use it as a filter to locate the exact transaction. You can search for specific DLP identifiers.

Use this filter to view transactions associated with a specific external device ID. Enter all or part of the device model in the text field and choose an operator such as: **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to see the MD5 hash for the file that triggered the DLP rule. Whenever a DLP rule is hit, and the appropriate alert is configured, an email containing the MD5 hash of the file is sent to your auditors. Use it as a filter to locate the exact transaction. You can search for specific DLP MD5 hashes.

Use this filter to view transactions associated with a specific triggered DLP rule's severity. You can choose to include or exclude certain severities. The following severities appear under this filter:

- Information
- Low
- Medium
- High

Use this filter to see the HTTP/S transactions that indicate domain fronting due to an FQDN mismatch: between the request URL and the request's host header, or between the SNI (Server Name Indication) and the inner request's host header.

Use this filter for the SSL/TLS/TLS connection's Server Name Indication (SNI) in cases that the HTTPS request host header doesn't match the SNI. SSL/TLS Inspection must be enabled for this field to be populated.

Use this filter to limit the data to traffic associated with a specific upload or download document type. The following types appear under this filter:

- Corporate Finance
- Corporate Legal
- Court Form
- DMV
- Immigration
- Insurance
- Invoice
- Legal
- Medical Information
- None
- Real Estate
- Resume
- Tax
- Technical
- Unknown

Use this filter to limit the data to traffic associated with a specific downloaded file name. Enter all or part of the URL in the text field and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to limit the data to traffic associated with a specific downloaded file type.

Use this filter to limit the data to traffic associated with a specific user-defined downloaded file type. The default option for this filter is **None**.

Use this filter to view transactions in which a specific file type was downloaded. The default option for this filter is **Any**. You can search for specific categories. The following categories appear under this filter:

- Any
- Archive
- Audio
- Database
- Executable
- Image
- Microsoft Office
- Mobile
- Online Gaming
- Open Office Files
- Other
- Other Documents
- Other Microsoft
- Source Code
- Streaming Media
- Video
- Web Content

Use this filter to view transactions associated with file sharing activities. When you select this filter, the dialog automatically adds the **Cloud Application Class** filter set to **File Sharing**. From the **File Sharing Activity** filter, the default option is **All**. You can search for specific activities. The following activities appear under the **Streaming** **Activity**filter:

- All
- Upload
- View

Use this filter to view transactions by their flow type. Choose one from the following:

- Direct
- Intranet
- Loopback
- Unclassified
- VPN
- VPN Tunnel
- ZIA
- ZPA

Use this filter to view transactions for a specific forwarding method. You can select from the ones listed under this filter.

Use this filter to view transactions for a specific forwarding rule. You can select from the ones listed under this filter.

Use this filter to view transactions for a specific gateway IP. You can search for specific gateway IPs.

Use this filter to view transactions for a specific gateway name. You can select from the ones listed under this filter.

Use this filter to view transactions associated with a HTTP header insertion profile. The default option for this filter is **None**.

Use this filter to view transactions associated with a HTTP header profile. The default option for this filter is **Any**.

Use this filter to view transactions associated with instant messaging applications. When you select this filter, the dialog automatically adds the **Cloud Application Class** filter set to **Instant Messaging**. For the**IM Activity** filter, the default option is **Any**. You can search for specific activities. The following activities appear under the**IM Activity** filter:

- All
- Receive File
- Receive Message
- Send File
- Send Message

Use this filter to view the transactions associated with intermediate CA certificates. The default option for this filter is **None**. The following intermediate CA protection types appear under this filter:

- HSM Protection
- N/A
- Software Protection

Use this filter to view the JA4 fingerprint calculated for TLS-based transactions for SaaS applications. JA4 fingerprint is a TLS client fingerprint generated during the TLS handshake. It analyzes the TLS Client Hello packet to create a unique, easily shareable identifier for clients in encrypted communications. A JA4 fingerprint is a sequence of three fragments (i.e., JA4A, JA4B, JA4C).

For example, for the Chrome application, the JA4 fingerprint is JA4=t13d1516h2_8daaf6152771_e5627efa2ab1 comprising:

- **JA4 A**: t13d1516h2
- **JA4 B**: 8daaf6152771
- **JA4 C**: e5627efa2ab1

See image.

Enter all or part of the JA4 fingerprint information in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Does Not Start With**, **Not Null**, or **Is** **Null**.

[Image: The JA4 fingerprint contains three fragments, each with a different meaning, separated by underscores]

Use this filter to view the JA4 fingerprint A, which consists of the protocol type, TLS version requested by the client, Sever Name Indication (SNI)or no SNI, the number of supported cipher suites, number of extensions, and the first ALPN value. Enter all or part of the JA4 fingerprint A info in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Does Not Start With**, **Not** **Null**, or **Is** **Null**.

Use this filter to view the truncated SHA256 hash of sorted cipher suites. Enter all or part of the JA4 fingerprint B information in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not** **Contain**, **Does Not End With**, **Does Not Start With**, **Not Null**, or **Is Null**.

Use this filter to view the truncated SHA256 hash of sorted extensions and signature algorithms. Enter all or part of the JA4 fingerprint C information in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not** **Contain**, **Does Not End With**, **Does Not Start With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with a specific location. The default option for this filter is **All**. Select **Hide Deleted**if you want to remove deleted locations from the list. Click **Select All** to select all the configured locations. You can search for specific locations. You can choose to include or exclude certain locations.

Use this filter to view transactions associated with a specific location group. The default option for this filter is **None**. You can search for specific location groups.

Use this filter to view transactions associated with a specific location type. The default option for this filter is **None**. The following location types appear under this filter:

- Corporate User Traffic Group
- Guest Wifi Group
- IoT Traffic Group
- Server Traffic Group
- Unassigned Locations
- Workload Traffic Group

Use this filter to see if the certificate status verification using OCSP failed or passed. This filter applies to SSL/TLS inspected traffic. The default option for this filter is **None**. You can search for specific results. The following results appear under this filter:

- Good
- None
- Revoked
- Unknown

Use this filter to view the blocked policy and rule that took action during the transaction. Following are the policy types that appear in this field:

- **Advanced Threat Protection**: This refers to the [Advanced Threat Protection policy](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy).
- **Collaboration & Online Meetings App Control**: This refers to the [Collaboration & Online Meetings rule](https://help.zscaler.com/zia/adding-collaboration-online-meetings-rule-for-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Consumer**: This refers to the [Consumer rule](https://help.zscaler.com/zia/adding-consumer-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Data Loss Prevention**: This refers to the [Data Loss Prevention policy](https://help.zscaler.com/zia/about-data-loss-prevention).
- **DNS**: This refers to the [DNS Control policy](https://help.zscaler.com/zia/about-dns-control).
- **DNS Over HTTPS Services**: This refers to the [DNS Over HTTPS Services Rule](https://help.zscaler.com/zia/adding-dns-over-https-services-rule-cloud-app-control) for the [Could App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **File Sharing**: This refers to the [File Sharing rule](https://help.zscaler.com/zia/adding-file-sharing-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **File Type Control**: This refers to the [File Type Control policy](https://help.zscaler.com/zia/about-file-type-control).
- **Firewall Filtering**: This refers to the [Firewall Control policy](https://help.zscaler.com/zia/about-firewall-control).
- **Hosting Providers**: This refers to the [Hosting Providers rule](https://help.zscaler.com/zia/adding-hosting-providers-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Human Resources**: This refers to the [Human Resources rule](https://help.zscaler.com/zia/adding-human-resources-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Instant Messaging**: This refers to the [Instant Messaging rule](https://help.zscaler.com/zia/adding-instant-messaging-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Intrusion Prevention**
- **IT Services**: This refers to the [IT Services rule](https://help.zscaler.com/zia/adding-it-services-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Malware Protection**: This refers to the [Malware Protection policy](https://help.zscaler.com/zia/configuring-malware-protection-policy).
- **Mobile App Store Control**: This refers to the [Mobile App Store Control policy](https://help.zscaler.com/zia/about-mobile-app-store-control).
- **NAT Control**: This refers to the [NAT Control policy](https://help.zscaler.com/zia/about-nat-control).
- **Productivity and CRM Tools**: This refers to the [Productivity & CRM tools rule](https://help.zscaler.com/zia/adding-productivity-crm-tools-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Sales & Marketing**: This refers to the [Sales & Marketing rule](https://help.zscaler.com/zia/adding-sales-marketing-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **Sandbox**: This refers to the [Sandbox policy](https://help.zscaler.com/zia/about-sandbox).
- **Social Networking**: This refers to the [Social Networking & Blogging rule](https://help.zscaler.com/zia/adding-social-networking-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **System & Development**: This refers to the [System & Development rule](https://help.zscaler.com/zia/adding-system-development-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).
- **URL Filtering**: This refers to the [URL filtering policy](https://help.zscaler.com/zia/about-url-filtering).
- **Video Streaming**
- **Webmail**: This refers to the [Webmail rule](https://help.zscaler.com/zia/adding-webmail-rule-cloud-app-control) for the [Cloud App Control policy](https://help.zscaler.com/zia/about-cloud-app-control).

Use this filter to view transactions that bypassed the Zscaler Client Connector.

Use this filter to view transactions that bypassed the Zscaler Client Connector within the specified time period.

Capture:Use this filter to limit the data to view transactions that were captured into a PCAP file.

Improve the visibility of protocols that traverse within Zscaler’s cloud. The following information is shown:

- **DNS over HTTPS**: Transactions from sites that are used for DNS resolution over an encrypted and secure connection with [DNS Over HTTPS Services](https://help.zscaler.com/zia/adding-dns-over-https-services-rule-cloud-app-control).
- **FTP**: Transactions from native FTP servers.
- **FTP over HTTP**: Transactions from FTP over HTTP websites.
- **HTTP**: Transactions from HTTP websites.
- **HTTP Proxy**: Transactions from HTTP CONNECT requests destined to a Public Service Edge IP address.
- **HTTPS**: HTTPS transactions that have been inspected.
- **SSL/TLS**: Transactions from SSL/TLS connections that have not been inspected. For example, hosts you've [exempted from SSL/TLS inspection](https://help.zscaler.com/zia/about-ssl-inspection#configure-ssl-inspection-policy).
- **Tunnel**: Transactions from unidentified encrypted traffic. For example, tunneling applications (e.g., Telnet or SSH) that are encapsulated in HTTP or HTTPS.
- **Tunnel SSL/TLS**: Undecodable protocol within an SSL/TLS connection.
- **WebSocket**: Transactions from WebSocket websites.
- **WebSocket SSL/TLS**: Transactions from WebSocket websites encrypted by SSL/TLS.

Zscaler inspects bidirectional WebSocket traffic carrying supported text-based content across applications. Contact Zscaler Support to enable this feature.

Use this filter to view transactions based on the number of bytes a destination web server returned for an HTTP request. The default option for this filter is **All Sizes**. You can search for specific sizes. The following sizes appear under this filter:

- All Sizes
- 0–1KB
- 1KB–100KB
- 100KB–1MB
- 1MB–5MB
- 5MB–10MB
- 10MB–50MB
- 50MB–100MB
- Above 100MB
- Custom

Use this filter to find transactions associated with a referrer URL, which is the URL from which an HTTP request originated. Enter all or part of the URL in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with the web traffic of an HTTP request method. Choose **GET** to see transactions only for HTTP requests to retrieve data, or choose **POST** to see transactions only for HTTP requests to submit data to be processed. Post requests include email that was sent through webmail or posting on a social networking site or blog.

- Baselinecontrol
- Bcopy
- Bdelete
- Bmove
- Bpropfind
- Bproppatch
- Checkin
- Checkout
- Connect
- Copy
- Delete
- Get
- Head
- Invalid
- Label
- Lock
- Mailpost
- Merge
- Mkactivity
- Mkcol
- Mkworkspace
- Move
- Notify
- Options
- Poll
- Post
- Propfind
- Proppatch
- Put
- Report
- Reqmod
- Respmod
- Search
- Subscribe
- Trace
- Uncheckout
- Unlock
- Unsubscribe
- Update
- Versioncontrol

Use this filter to see the request version of HTTP used for communication between the client and the server. The default option for this filter is **None**. You can search for specific versions. The following versions appear under this filter:

- 1.0
- 1.1
- 2.0
- None

Use this filter to see the response version of HTTP used for communication between the client and the server. The default option for this filter is **None**. You can search for specific versions. The following versions appear under this filter:

- 1.0
- 1.1
- 2.0
- None

Use this filter to view transactions based on the latency (in milliseconds) experienced by HTTP requests due to bandwidth control. The minimum value is 0 and maximum value is 3,600,000 milliseconds.

Use this filter to view transactions based on the latency (in milliseconds) experienced by HTTP response due to bandwidth control. The minimum value is 0 and maximum value is 3,600,000 milliseconds.

Use this filter to view transactions based on the total latency (in milliseconds) due to bandwidth control, i.e., sum of latencies experienced by HTTP request and response due to bandwidth control. The minimum value is 0 and maximum value is 3,600,000 milliseconds.

Use this filter to view file downloads based on the Sandbox result. The default option for this filter is **Any**. You can search for specific results The following results appear under this filter:

- **Sandbox Adware**:View known malicious results. This category refers to any malicious file sample installing persistent components to push advertising content to the user’s device. Often, these advertisements are unwanted and can lead to spyware or other grayware-oriented privacy violations.
- **Sandbox Anonymizer**:View known malicious results. This category refers to any malicious file sample exhibiting behavior consistent with anonymizer programs, such as Tor Browser or other VPN services, that essentially make a user’s internet activity untraceable.
- **Sandbox Benign**:View known non-malicious results. This is a catchall category for any non-malicious file sample with a Sandbox Threat Score equal to or less than 70. The Zscaler service refers to file samples that have a score between 40 and 70 as “suspicious” because they might need additional review.
- **Sandbox Malware**:View known malicious results. This is a catchall category for any malicious file sample that doesn't fall under the other Sandbox categories. Most Sandbox-classified file samples aren't clearly known to be a specific threat or malware family-oriented because there aren't specific signatures or indicators to categorize the file. Instead the Zscaler service categorizes the file based on an aggregation of the file’s OS and application behaviors and network activity.
- **Sandbox Suspicious**: This category refers to files that exhibit some malicious behaviors but are not fully classified as malware.
- **Sent for Analysis**: View unknown results that have been sent to the Sandbox for behavioral analysis.

Use this filter to view file downloads based on the Sandbox action. The default option for this filter is **Blocked**. You can search for specific results The following results appear under this filter:

- Blocked
- Quarantined
- Sent for Analysis

Use this filter to view sandbox API file submissions based on the specified Sandbox API Token. The default option for this filter is **Any**. You can filter by any combination of created token names.

Use this filter to view file download transactions based on the file hash value (MD5). Enter all or part of the MD5 in the text field and **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions based on the size, in bytes, of the HTTP request that was sent to the destination web server. The default option for this filter is **All Sizes**. You can search for specific sizes. The following sizes appear under this filter:

- All Sizes
- 0–1KB
- 1KB–100KB
- 100KB–1MB
- 1MB–5MB
- 5MB–10MB
- 10MB–50MB
- 50MB–100MB
- Above 100MB
- Custom

Use this filter to see if the certificate presented by the Origin Content Server (web server) to the Public Service Edge was self-signed. The default option for this filter is **None**. The following options appear under this filter:

- No
- None
- Yes

Use this filter to view the validation type for the certificate presented by the server to the Public Service Edge. The default option for this filter is **None**. The following options appear under this filter:

- Domain Validation
- Extended Validation
- None
- Organization Validation

Use this filter to view the validity duration of the certificate presented by the server to the Public Service Edge (i.e. How long is the certificate valid for?). The default option for this filter is **None**. The following options appear under this filter:

- 0–3 months
- 3–12 months
- More than 12 months
- None

Use this filter to view the cipher suite agreed upon during the SSL/TLS handshake between the Public Service Edge and the server. The default option for this filter is **None**. You can search for specific cipher suites.

The cipher suite names are listed in OpenSSL format. To view them in RFC format, use this [link](https://testssl.sh/openssl-iana.mapping.html) to map between the two notations.

Use this filter to see the version of TLS used for communication between the Public Service Edge and the server. The default option for this filter is **None**. You can search for specific versions. The following versions appear under this filter:

- TLS 2.0
- TLS 3.0
- TLS 1.0
- TLS 1.1
- TLS 1.2
- TLS 1.3

Use this filter to view transactions associated with a destination server. Choose **Match**and enter an IP address, a range of IP addresses, or an IP address and netmask, as shown in the examples below the text box.

Use this filter to view any delayed logs.

Use this filter to display the hash of identical files.

Use this filter to view transactions associated with social networking sites. When you select this filter, the dialog automatically adds the **Cloud Application Class** filter set to **Social Networking**. From the **Social Networking Activity** filter, the default option is **All**. You can search for specific activities. The following activities appear under the**Social Networking Activity** filter:

- All
- Publish
- View

Use this filter to view SSL/TLS inspected transactions. Select to view decrypted SSL/TLS transactions. Deselect to view SSL/TLS transactions that weren't decrypted.

Use this filter to view the [SSL/TLS Inspection Policy](https://help.zscaler.com/zia/about-ssltls-inspection-policy) rule that was applied to the transaction. The default option for this filter is **Any**.

Use this filter to view SSL/TLS policy reason transactions. The default option for this filter is **None**. The following SSL/TLS Inspection policy actions appear under this filter:

- Blocked
- Inspected
- N/A
- Not inspected because of failed client SSL/TLS handshake
- Not inspected because of HSM error
- Not inspected because of mutual TLS authentication
- Not inspected because of O365 bypass
- Not inspected because of SSL/TLS policy
- Not inspected because of UCaaS bypass
- Not inspected because of undecryptable traffic
- Not inspected because of Zscaler best practices
- Not inspected because of Zscaler service domains

Use this filter to view transactions associated with streaming media. When you select this filter, the dialog automatically adds the **Cloud Application Class** filter set to **Streaming Media**. From the **Streaming** filter, the default option is **All**. You can search for specific activities. The following activities appear under the **Streaming** **Activity**filter:

- All
- Listen
- Upload

Use this filter to limit the data to traffic associated with a specific upload or download subdocument type. The default option for this filter is **Any**.

Use this filter to view transactions based on the “raw” Page Risk Index score of a URL; it filters based on the Suspicious Content [column](https://help.zscaler.com/zia/web-insights-logs-columns). You can view transactions for a certain range of Page Risk Index scores by entering a minimum and maximum value. The value ranges are from 0 to 100. To learn more about the Suspicious Content Protection (Page RiskTM), see [About Advanced Threat Protection](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy).

Use this filter to view transactions associated with a specific threat category. These threats are detected by **Malware Protection**. The default option for this filter is **Any**. You can search for specific categories. You can also use the toggle to turn on **Threat Name Search** to enter all or part of the threat name in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**. The following categories appear under this filter:

- **Any**
- **Adware**: This category refers to any detections of adware-related installers or content through file reputation or signature matching (e.g., antivirus or YARA). Adware is software that persistently serves ads to the user and increases the risk of installing spyware or other unwanted software.
- **Archive Bomb**: This category refers to any detections of archive bomb-related content through file reputation or signature matching (e.g., antivirus). An archive bomb is a ZIP or other archive file that was small when compressed or recursively compressed/archived several times; however, when expanded, the file can become extremely large. It can overwhelm antivirus scanning engines or the user’s device by completely filling the hard disk or memory.
- **Backdoor**: This category refers to any detections of backdoor-related installer payloads or content through file reputation or signature matching (e.g., antivirus or YARA). Backdoors are software that allow the attackers to gain remote network access to devices for future exploitation.
- **Benign**: This category refers to any clean file.
- **Boot Virus**: This category refers to any detections of viruses that embed themselves into the boot sectors of users' devices. The Zscaler service detects boot virus-related content through file reputation or signature matching (e.g., antivirus).
- **Dialer**: This category refers to any detections of dialer software that infects users' devices and enables outbound dialing for malicious purposes. The Zscaler service detects dialer-related content through file reputation or signature matching (e.g., antivirus).
- **Downloader**: This category refers to any detections of malware that downloads additional botnets or other malicious payloads on users' devices. The Zscaler service detects downloader-related content through file reputation or signature matching (e.g., antivirus or YARA).
- **Exploit**: This category refers to any detections of various exploits and exploit-related content through file reputation or signature matching (e.g., antivirus or YARA).
- **Macro Virus**: This category refers to any detections of macro viruses and other related content through file reputation or signature matching (e.g., antivirus or YARA).
- **MalwareTool**: This category refers to any detections of malware tools used to generate viruses, exploits, or denial-of-service (DoS) attacks through file reputation or signature matching (e.g., antivirus).
- **Misdisinfection**: This category refers to any file detections that another security service tried to disinfect but failed to do so completely because there are traces of malware in the file. The Zscaler service detects this content through file reputation or signature matching (e.g., antivirus).
- **Other Malware**: This category refers to any malware detections that don't fit into the more specific malware categories. The Zscaler service detects other malware content through file reputation or signature matching (e.g., antivirus).
- **Other Spyware**: This category refers to any spyware detections that don't fit into the more specific malware or spyware categories. The Zscaler service detects other spyware content through file reputation or signature matching (e.g., antivirus).
- **Other Virus**: This category refers to any viruses that don't fit into the more specific malware categories. The Zscaler service detects other viruses through file reputation or signature matching (e.g., antivirus).
- **Password Stealer**: This category refers to any detections of password stealing payloads, installers, or related content through file reputation or signature matching (e.g., antivirus).
- **Privacy Risk**: This category refers to any detections of content, installers, or programs that are related to data exfiltration or attempt to access sensitive data. The Zscaler service detects privacy risks through file reputation or signature matching (e.g., antivirus).
- **Proxy**: This category refers to any malware detections that allow unauthorized connections to occur with the infected device. This type of malware allows a person to use the infected device to attack other devices, send spam, or impersonate your device. The Zscaler service detects proxy-related content through file reputation or signature matching (e.g., antivirus).
- **Ransomware (Virus)**: This category refers to any detections of ransomware installers, agents, or related content through file reputation, signature matching (e.g., antivirus or YARA), or machine learning model techniques.
- **Sandbox Adware**: This category refers to any known malicious Sandbox file detections that install persistent components to push advertising content to users' devices. Often, these advertisements are unwanted and can lead to spyware or other grayware-oriented privacy violations.
- **Sandbox Anonymizer**: This category refers to any known malicious Sandbox file detections that exhibit behavior consistent with anonymizer programs, such as Tor Browser or other VPN services, that essentially make a user’s internet activity untraceable.
- **Sandbox Malware**: This is a catchall category for any known malicious Sandbox file detections that don't fall under the other Sandbox categories. Most Sandbox-classified files aren't clearly known to be a specific threat or malware family-oriented because there aren't specific signatures or indicators to categorize the file. Instead, the Zscaler service categorizes the file based on an aggregation of the file’s OS and application behaviors and network activity.
- **Sandbox Offensive Security** **Tools**: This category refers to the threat actors that can use offensive security tools for malicious reasons. They can also be used by cyber security professionals.
- **Sandbox Ransomware**: This category refers to the type of malware that prevents or limits users from accessing their system, either by locking the system or by locking the users' files, until a ransom is paid.
- **Sent for Analysis**: This category refers to any unknown file detections that have been sent to the Sandbox for behavioral analysis.
- **Trojan**: This category refers to any detections of trojan installers or related content through file reputation, signature matching (e.g., antivirus or YARA), or machine learning model techniques.
- **Suspicious**: This category refers to files that exhibit some malicious behaviors but are not fully classified as malware.
- **Unrecognized Virus**: This category refers to any suspected viruses that don't fall under a specific virus family. The Zscaler service detects this content through signature matching (e.g., antivirus).
- **Unwanted Application**: This category refers to any detections of applications that are potentially unwanted, such as password crackers or other grayware software applications. The Zscaler service detects unwanted applications through file reputation or signature matching (e.g., antivirus or YARA).
- **Worm**: This category refers to any detections of worms, which are stand-alone malware files that replicate themselves in order to spread to other devices. They often use a computer network to propagate themselves. The Zscaler service detects worms through file reputation or signature matching (e.g., antivirus or YARA).

Use this filter to view transactions associated with a specific threat class. The default option for this filter is **Advanced Threats**. You can search for specific classes. You can also use the toggle to turn on **Threat Name Search** to enter all or part of the threat name in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**. The following classes appear under this filter:

- Advanced Threats
- Sandbox
- Viruses & Spyware

Use this filter to enter all or part of the threat name in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with a specific threat severity. The default option for this filter is **None**. The following severities appear under this filter:

- Critical
- High
- Medium
- Low

Use this filter to view transactions associated with a specific threat super category. The default option for this filter is **None**. You can search for specific categories. The following categories appear under this filter:

- Malware
- Sandbox
- Spyware
- Virus

Use this filter to view transactions based on the total size of the throttled requests. The default option for this filter is **All Sizes**. You can search for specific sizes. The following sizes appear under this filter:

- All Sizes
- 0–1KB
- 1KB–100KB
- 100KB–1MB
- 1MB–5MB
- 5MB–10MB
- 10MB– 50MB
- 50MB–100MB
- Above 100MB
- Custom

Use this filter to view transactions based on the total size of the throttled response. The default option for this filter is **All Sizes**. You can search for specific sizes. The following sizes appear under this filter:

- All Sizes
- 0–1KB
- 1KB–100KB
- 100KB–1MB
- 1MB–5MB
- 5MB–10MB
- 10MB–50MB
- 50MB–100MB
- Above 100MB
- Custom

Use this filter to view transactions based on the total size of the HTTP request and response. The default option for this filter is **All Sizes**. You can search for specific sizes. The following sizes appear under this filter:

- All Sizes
- 0–1KB
- 1KB–100KB
- 100KB–1MB
- 1MB–5MB
- 5MB–10MB
- 10MB–50MB
- 50MB–100MB
- Above 100MB
- Custom

Use this filter to limit the data to traffic associated with a specific traffic forwarding mechanism. The following appear under this filter:

- GRE
- IPSec Tunnel
- NAT Control
- PAC File
- PAC File over GRE Tunnel
- PAC File over IPSec Tunnel
- Policy Based Forwarding
- Zscaler Client Connector
- Zscaler Client Connector over GRE Tunnel
- Zscaler Client Connector over IPSEC Tunnel
- ZPA Microtunnels (M-Tunnels)

Use this filter to limit data to traffic associated with a specific unscannable file type. The following appear under this filter:

- **Encrypted File**: Encrypted or password-protected (e.g., GZIP, PDF)
- **Undetectable File**: Unable to determine the file type, based on multiple methods.
- **Unscannable File**: Unscannable (e.g., corrupt archive)

Use this filter to limit the data to traffic associated with a specific uploaded file name. Enter all or part of the URL in the text field and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to limit the data to traffic associated with a specific uploaded file type.

Use this filter to limit the data to traffic associated with a specific user-defined uploaded file type. The default option for this filter is **None**.

Use this filter to view transactions in which a specific file type was uploaded. The default option for this filter is **Any**. You can search for specific categories. The following categories appear under this filter:

- Any
- Archive
- Audio
- Database
- Executable
- Image
- Microsoft Office
- Mobile
- Online Gaming
- Open Office Files
- Other
- Other Documents
- Other Microsoft
- Source Code
- Streaming Media
- Video
- Web Content

Use this filter to view transactions associated with a specific URL category. The default option for this filter is **Any**. You can search for specific categories. You can choose to include or exclude certain categories.

Use this filter to limit the data to a specific URL class. The default option for this filter is **All**. You can search for specific classes. The following classes appear under this filter:

- All
- Bandwidth Loss
- Business Use
- General Surfing
- Legal Liability
- Privacy Risk
- Productivity Loss

Use this filter to view transactions for specific URL filtering policy names. You can select from the ones listed under this filter.

Use this filter to find transactions associated with specific URLs.

To use this filter:

1. Do one of the following:
  - Click **URL** and enter all or part of the URL (e.g., `http://www.zscaler.com/products-user-security.php`).
  - Click **Path** and enter all or part of the path information (e.g., `products-user-security.php`).
  - Click **Host** and enter all or part of the host name (e.g., `www.zscaler.com`).
  - Click **Domain** and enter all or part of the domain name (e.g., `zscaler).`
2. Choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.
3. Click **Download (.csv)**or **Display**.
4. Click **Apply Filter**. For optimized and accelerated results, the Zscaler Admin Console might show recommendations to switch between **URL**, **Path**, **Host**, and **Domain**based on your seach inputs. You can choose to **Apply Recommentation**, **Ignore**it,or **Cancel**the search. For example, if you select **URL** and enter `www.zscaler.com` in the Search field, the Internet & SaaS service shows a recommendation pop-up window to search it as **Host** to minimize the processing time in generating the logs. See image. The search results are displayed in the portal or downloaded as a CSV file.

Use this filter to view transactions associated with a specific URL super category. The default option for this filter is **All**. You can search for specific categories. The following categories appear under this filter:

- All
- Adult Material
- Business and Economy
- Custom
- Drugs
- Education
- Entertainment/Recreation
- Gambling
- Games
- Government and Politics
- Health
- Illegal or Questionable
- Information Technology
- Internet Communication
- Job/Employment Search
- Militancy/Hate and Extremism
- Miscellaneous
- News and Media
- Religion
- Security
- Shopping and Auctions
- Social and Family Issues
- Society and Lifestyle
- Special Interests/Social Organizations
- Sports
- Tasteless
- Travel
- User-Defined
- Vehicles
- Violence
- Weapons/Bombs

Use this filter to view the transactions of a specific user. The default option for this filter is **All**. Select **Hide Deleted**if you want to remove deleted users from the list. Click **Select All** to select all the configured users. You can search for specific users. You can choose to include or exclude certain users. By default, user-related traffic data includes locations and users.

Use this filter to find transactions associated with the user-agent string that the browser included in its GET request. The user-agent string contains browser and system information that the destination server can use to provide appropriate content.

- The service lists the commonly-used user agents in the **Known** category. To find a user-agent string, click the **Known**tab and scroll or use the Search function to find the user-agent string in this category.
- If the user-agent string is not found, click **Unknown** and enter all or part of the user-agent string in the text field and choose**Contains**, **Exact**, **Ends With**, or **Starts With**. (Example: **Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:18.0) Gecko/20100101 Firefox/18.0**)

Use this filter to view transactions based on the service's action. The default option for this filter is **All**. You can search for specific actions. The following categories appear under this filter:

- **All**: View all transactions. This is the default option.
- **Allow**: View allowed transactions.
- **Allowed - No Active Content**: View download transactions of unknown PDF or Microsoft Office files that are allowed because they are classified as benign in [Sandbox static analysis](https://help.zscaler.com/zia/configuring-sandbox-policy#first-time-action).
- **Allowed and No Scan**: View download transactions of unknown files that were allowed but not scanned due to your [configured Sandbox policy](https://help.zscaler.com/zia/configuring-sandbox-policy#first-time-action).
- **Allowed with caution**: View allowed transactions where the service displayed a caution notification.
- **Block**: View blocked transactions.
- **Blocked - Tenant Restricted**: View blocked transactions because of your configured [Tenant Restriction](https://help.zscaler.com/zia/about-tenant-profiles)policies.
- **Blocked with caution**: View blocked transactions where the service displayed a caution notification.
- **Isolate**: View transactions sent for isolation by [Zero Trust Browser](https://help.zscaler.com/zero-trust-browser/what-is-zero-trust-browser) (formerly Isolation).

Use this filter to view transactions associated with webmail applications. When you select this filter, the dialog automatically adds the **Cloud Application Class** filter set to **Web Mail**. From the **Webmail Activity** filter, the default option is **All**. You can search for specific activities. The following activities appear under the **Webmail** **Activity**filter:

- All
- Send
- Send Attachment
- View

Use this filter to view transactions associated with a specific Zscaler Client Connector tunnel version, listed under this filter.

Use this filter to limit the data to traffic associated with a specific server destination port. You can specify individual ports and a range of ports, from 0 to 65535.

This filter is disabled by default. To enable the filter, contact Zscaler SupportZscaler Support.

Use this filter to view the data to activities associated with DLP rules that are evaluated, but no action was taken based on the rule. To populate this field, you must enable the **Evaluate All Rules** mode for inline DLP rule evaluation in the [DLP Advanced Settings](https://help.zscaler.com/zia/configuring-dlp-advanced-settings#RuleEvaluation) page (Administration > DLP Advanced Settings). To learn more, see [Configuring DLP Policy Rules with Evaluate All Rules Mode Enabled](https://help.zscaler.com/zia/configuring-dlp-policy-rules-evaluate-all-rules-mode-enabled#EvaluateAllRules).

Use this filter to view prompts entered by users across the various generative AI applications. You can also use the toggle to turn on prompt search to enter all or part of the prompt in the text field, and choose **Contains**, **Starts With**, **Ends With**, **Exact Match**, **Does Not Contain**, **Does Not End With**, **Not Null**, or **Is Null**.

Use this filter to view transactions associated with a specific category of prompts entered by users across the various Gen AI applications. You can choose to include or exclude the selected values. The default option for this filter is **Any**. The following categories appear under this filter:

- Art and Culture
- Blogs and Social Media
- Code Generation
- Corporate Finance
- Corporate Legal
- Customer Service Success
- Education
- Government and Politics
- Jobs Employment HR
- Marketing
- News and Media
- PII
- Religion
- Sales/Support Apps
- Shopping and Retail
- Source Code
- Tax
- Technology
- Threat
- Travel and Vehicles
- Unknown

This filter allows you to view transactions based on whether policies were applied using full tenant configurations during normal operating conditions or using only a partial configuration. A partial configuration is an extremely rare occurrence caused by transient network disruptions, which can result in a Service Edge operating with incomplete or cached tenant, user, or location configurations.

When this toggle is enabled, transactions that used partial configurations for policy application are shown. When this toggle is disabled, transactions that used full configurations for evaluation are shown.

This distinction applies only when the **Best Effort Policies** option is used for the **Behavior When Partial Configuration Available** setting in [Advanced Settings](https://help.zscaler.com/zia/configuring-advanced-settings#partial-configuration-policy-handling).

[Image: Recommendation window for URL Search Filter]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/what-3rd-party-app-governance","lastmod":"2026-09-30T03:24Z","nid":"1540013"} -->
## What Is 3rd-Party App Governance?

- Source: https://help.zscaler.com/zia/what-3rd-party-app-governance
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Policies > SaaS Security > 3rd-Party App Governance > Getting Started > What Is 3rd-Party App Governance?
- Last modified: 2026-09-30T03:24Z
- Summary: Information on third-party app security using Zscaler 3rd-Party App Governance.

Zscaler 3rd-Party App Governance allows you to discover, manage, reduce, and control the attack surface introduced by third-party apps, extensions, and add-ons to corporate SaaS applications.

With 3rd-Party App Governance, you can uncover and maintain an up-to-date app inventory, gaining full visibility into what is connected, the risks posed, and how each app is used in your environment.

You can effectively govern your environment by classifying apps as sanctioned or unsanctioned; monitoring and receiving alerts on drift in app usage or security posture; and receiving notifications when apps are introduced in violation of corporate policy or compliance.

You can manually or automatically streamline remediation actions such as banning or revoking an app token, and involve end users in the review process. 3rd-Party App Governance lets you reduce your vetting or pre-vetting procedures from hours to minutes with a centralized console and extensive app catalog that unifies key attributes and automates the majority of the process.

To learn more about navigating the 3rd-Party App Governance Admin Portal, see[Accessing and Navigating the 3rd-Party App Governance Admin Portal](https://help.zscaler.com/zia/accessing-and-navigating-3rd-party-app-governance-admin-portal).

To learn more about configuring 3rd-Party App Governance, see the [Step-by-Step Configuration Guide for 3rd-Party App Governance](https://help.zscaler.com/zia/step-step-configuration-guide-3rd-party-app-governance).

## Key Benefits

3rd-Party App Governance provides the following benefits and enables you to:

- Quickly vet third-party apps before connecting them to your environment.
- Gain full visibility over first-, second-, and third-party API integrations across your business so you can uncover dangerous SaaS platform connections.
- Reduce your integrations and third-party attack surface and stop data exfiltration and breaches.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/what-advanced-posture-management","lastmod":"2026-10-07T04:58Z","nid":"1508606"} -->
## What Is Advanced Posture Management?

- Source: https://help.zscaler.com/zia/what-advanced-posture-management
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Policies > SaaS Security > Posture Management > Posture Management - Advanced > What Is Advanced Posture Management?
- Last modified: 2026-10-07T04:58Z
- Summary: Information on SaaS security using Zscaler Advanced SSPM.

Zscaler Advanced SaaS Security Posture Management (SSPM) is a comprehensive and unified solution that delivers complete security across Software as a Service (SaaS) apps and platforms, from data visibility to posture and governance. It helps you to quickly identify and mitigate risky misconfigurations, control SaaS sprawl and reduce third-party access, and identify users at risk.

With Advanced SSPM, you can continuously monitor and correct SaaS security posture to ensure regulatory compliance is maintained across the organization. Advanced SSPM lets you reduce your attack surface by vetting third-party integrations and revoking risky connections. You can also ensure least-privileged SaaS access and revoke over privileged identities and permissions.

If you subscribe to the Advanced SSPM service, you can access Advanced SSPM from the Zscaler Admin Console using single sign-on (SSO). To access Advanced SSPM, go to **Analytics**> **Reports** > **SaaS Security Report** >**Posture Management.**

See image.

The Posture page displays a list of all the managed posture controls. To learn more, see [About Posture](https://help.zscaler.com/zia/about-posture).

Advanced SSPM supports the following API-based platforms. These platforms are onboarded from the Zscaler Admin Console or the 3rd-Party App Governance Admin Portal. To learn more, see [Adding SaaS Application Tenants](https://help.zscaler.com/zia/adding-saas-application-tenants) and [Connecting Your Platforms to Advanced SSPM](https://help.zscaler.com/zia/connecting-your-platforms-advanced-sspm).

- Airtable
- Bitbucket
- Bitwarden
- Box
- Confluence
- Databricks
- Docusign
- Dropbox
- GitHub
- GitLab
- Google Workspace
- Jira
- JumpCloud
- Lucid
- Microsoft 365
- Microsoft Copilot
- Okta
- OneLogin
- OpenAI
- Oracle Financials Cloud
- Salesforce
- Sentry
- ServiceNow
- ShareFile
- Slack
- Smartsheet
- Snowflake
- Trello
- Twilio
- Webex Teams
- Workday
- Zendesk
- Zoom

## Key Benefits

Advanced SSPM provides the following benefits and enables you to:

- Continuously monitor SaaS platforms for dangerous misconfigurations, and fix risky configuration drift.
- Leverage in-depth SaaS shadow IT and first-, second-, and third-party API integrations across your business to gain in-depth visibility, and identify, govern, and audit risks.
- Quickly and effectively vet sanctioned and unsanctioned third-party apps before connecting them to your environment.
- Continuously monitor compliance, ensuring that SaaS applications adhere to relevant regulations and standards such as GDPR, HIPAA, and SOC 2.
- Enforce granular policies to prevent exfiltration from your SaaS platform to unknown locations and apps.
- Leverage a Zero Trust approach to revoke excessive privileges and restrict risky user profiles from accessing SaaS apps and data.

[Image: SaaS Security Report showing Posture Management Tab]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/what-are-alternatives-caching-web-traffic","lastmod":"2026-05-21T21:06Z","nid":"1400226"} -->
## Alternative Options to Caching Web Traffic

- Source: https://help.zscaler.com/zia/what-are-alternatives-caching-web-traffic
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Traffic Forwarding > Alternative Options to Caching Web Traffic
- Last modified: 2026-05-21T21:06Z
- Summary: Information on alternate methods to caching web traffic for optimizing the bandwidth usage.

Proxies have been employing caching techniques to minimize bandwidth usage and improve latency on web browsing traffic in companies for over a decade. In the Web 1.0 days, caching was a useful technique because most web pages were relatively static. Today’s websites have evolved to provide much more dynamic content, session-based connections, streaming media, and the utilization of HTTPS to encrypt a large segment of traffic. In addition, there are now alternative techniques you can use to realize some of the same goals as caching, but in a more effective manner.

To learn more about some of the changing trends that affect web proxy, see:

- Dynamic Content
- HTTPS
- Browser Caching
- File Size Limits
- Streaming Media

## Alternatives to Caching

The primary goal of caching is to reduce bandwidth usage and improve user experience. Below are some alternative solutions that will effectively reduce bandwidth usage, leaving more bandwidth for business applications and improving user experience. The number one category responsible for bandwidth usage is Streaming Media.

- Prioritize Traffic. Set a policy in place that will prioritize business-related traffic over Streaming Media, Large File downloads, or other high-bandwidth/less-critical sites. Having a product that can do traffic shaping can effectively limit any impact on high-bandwidth sites/users.
- Enforce network quotas on Streaming Media sites per user. For example, each user gets 50 MB of Streaming Media per day. This allows the average user to access streaming media sites, while putting a cap on the high-bandwidth usage.
- Use the [URL Filtering policy](https://help.zscaler.com/zia/about-url-filtering) to block access to Streaming Media or specific popular non-business streaming media sites like YouTube or Vimeo.
- Direct to Net. By using local Internet connections and not backhauling traffic over a WAN, latency is considerably reduced, and user experience is improved. This helps the user experience from both a performance perspective and a localized content perspective.

Most web traffic today includes dynamic content. From news to searches and web applications, content is created on the fly and delivered to specific users. Caching provides no benefit for such content. The HTTP header, Cache-Control, was introduced to allow web developers to determine if and for how long a page should be cached. You will see many sites that use *Cache-Control: no cache* or *Cache-Control: no-store*, indicating that these resources should not be cached.

If you are not decrypting HTTPS connections, you are not caching content from HTTPS connections because proxies cannot cache encrypted data.

If you are decrypting HTTPS on your proxy, then it is possible in some products to cache that data. But from a security standpoint, this can open up a potential risk that you are now storing sensitive data on a multiuser machine (with virtually no control or visibility to who receives that potentially sensitive data).

All modern browsers provide caching at the client level, both in memory and on disk. A caching proxy server would only provide a secondary level of caching.

Due to limited memory and disk space, proxies are configured to only cache files up to a certain size. If the max file size is too small (for example, 1 MB), the gains from caching will be minimal since most files will not be cached. If the max file size is too large, then the cache will be limited to the number of files it can cache, which will reduce the cache hits. Even with all the time and expertise at your disposal to tune the max file size, the optimal setting could change from day to day, or even hour to hour, based on browsing patterns.

With the prevalence of Streaming Media and Content Delivery Networks (CDNs), many sites store the same content at multiple URLs or hosts. This makes caching less effective because a single video can be cached multiple times, filling your cache and reducing cache hits. Additionally, changing resolution (which often happens dynamically) can cause a video to be cached at multiple resolutions, which will also fill your cache and reduce cache hits.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/what-internet-saas","lastmod":"2026-04-07T16:57Z","nid":"1538661"} -->
## What is Zscaler Internet & SaaS?

- Source: https://help.zscaler.com/zia/what-internet-saas
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > What is Zscaler Internet & SaaS?
- Last modified: 2026-04-07T16:57Z
- Summary: Introductory information about Zscaler Internet & SaaS.

[Zscaler Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture) (ZIA) is a cloud-native Security Service Edge (SSE) platform that gives users secure, fast, and reliable access to the internet and SaaS applications. Part of the Zscaler Zero Trust Exchange (ZTE), Internet & SaaS is designed to replace legacy, hardware-centric security point solutions with a scalable, AI-powered security architecture. Rather than route traffic to a centralized data center, Internet & SaaS delivers security directly at the edge, enforcing policy-based security protocols to ensure a consistent user experience regardless of location, whether in a branch office, at home, or traveling.

## How Internet & SaaS Works

Internet & SaaS acts as an inline, secure gateway between users, workloads, and the internet. When a user or workload requests access to an external resource, the traffic is routed to the nearest [Public Service Edge](https://help.zscaler.com/zia/about-public-service-edges) for Internet & SaaS.

The platform then performs the following actions:

- **Full**[SSL/TLS Inspection](https://help.zscaler.com/zia/understanding-ssltls-inspection): Internet & SaaS decrypts and inspects encrypted web and related (e.g., FTP) traffic at scale, ensuring that threats hidden within encrypted streams are detected without degrading performance.
- **Policy Enforcement**: Every request is checked against your organization’s granular security and data protection policies.
- **Identity-Based Access**: Access is granted based on the identity of the user, the health of the device, and the context of the request, rather than the user's network location.
- **Cyberthreat Protection**: Traffic is analyzed in real time by multiple security engines, including AI-powered sandboxing, antivirus, and advanced threat protection (ATP). After the traffic is verified as "clean" and compliant with policy, it is forwarded directly to its destination.

## Key Benefits

Internet & SaaS offers several advantages over traditional security appliances and VPN-based architectures:

- **Superior User Experience**: By eliminating the need for routing traffic to a central location, Internet & SaaS reduces latency and provides faster access to company-critical cloud applications, like Microsoft 365 and Salesforce.
- **Reduced Attack Surface**: Internet & SaaS makes your network invisible to the internet, preventing discovery and stopping lateral movement within it.
- **Simplified Management**: As a 100% cloud-delivered service, Internet & SaaS eliminates the need to buy, deploy, and manage hardware appliances, like firewalls and web gateways.
- **Global Scalability**: The Zscaler ZTE is the world’s largest security cloud, with over 150 data centers globally, providing high availability and localized peering.
- **Unified Data Protection**: Internet & SaaS provides consistent [Data Loss Prevention (DLP)](https://help.zscaler.com/zia/about-data-loss-prevention) and SaaS Security capabilities across all users and locations.

## Core Features

Internet & SaaS provides a comprehensive suite of security and compliance tools:

- **Cloud Secure Web Gateway (SWG)**: Provides URL filtering, reputation-based blocking, and advanced web security.
- **Firewall**: A full-featured, user- and application-aware firewall with DNS security and Intrusion Prevention Systems (IPS) that protects all ports and protocols.
- **SaaS Security**: Secures data at rest and in transit within SaaS applications, preventing unauthorized data sharing.
- **DLP**: Identifies and protects sensitive data (e.g., PII, PCI, intellectual property) across all internet and SaaS traffic.
- **AI-Powered Sandboxing**: Instantly detects and blocks patient 0 attacks by analyzing suspicious files in a virtual environment.
- [Zero Trust Browser](https://help.zscaler.com/zero-trust-browser/what-is-zero-trust-browser): Executes active web content in a remote, isolated container to prevent malware from reaching the user's device.

## Deployment Options

Organizations can easily connect their traffic to Internet & SaaS using several methods:

- [Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector): A lightweight app installed on user devices that automatically tunnels traffic to the nearest Zscaler node.
- **Cloud Tunnels**: Secure GRE or IPSec tunnels from branch office routers or SD-WAN gateways.
- **Proxy Auto-Configuration (PAC) Files**: Lightweight files that instruct browsers to route traffic to the Zscaler service.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/what-zscaler-outbound-email-dlp","lastmod":"2026-09-22T07:06Z","nid":"1492656"} -->
## What Is Zscaler Outbound Email DLP?

- Source: https://help.zscaler.com/zia/what-zscaler-outbound-email-dlp
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Policies > Outbound Email Data Loss Prevention > What Is Zscaler Outbound Email DLP?
- Last modified: 2026-09-22T07:06Z
- Summary: A high-level overview of what Zscaler Outbound Email Data Loss Prevention (DLP) is and how it uses outbound email policy to prevent the exfiltration of sensitive data in email content.

Zscaler Outbound Email Data Loss Prevention (DLP) stops the exfiltration of sensitive data by enforcing policy rules on outbound email content sent to external domains, including content in subject lines, body text, and attachments. Using connectors and rules, your email server sends email to, and receives email from, the Zscaler smart host. The Zscaler smart host receives the email and sends it to the Zscaler DLP service for inspection. The Zscaler DLP service then inspects the email content for sensitive data, adding headers that define DLP actions to emails that trigger outbound email policy. When your email server receives inspected email from the Zscaler smart host, it uses those headers to determine enforcement actions.

As a part of your larger DLP strategy, Zscaler Outbound Email DLP lets you use the same DLP policy tools that you already use across other channels (i.e., Inline Web DLP, Endpoint DLP, and SaaS Security API DLP), eliminating the need for different DLP point solutions and the operational overhead involved in maintaining those solutions. Additionally, Zscaler Outbound Email DLP lets you enforce your DLP policy at a granular level, so that a single email containing sensitive information can be blocked for one recipient and allowed for another.

To learn more about how the Zscaler service enforces outbound email policy rules, see [Understanding Outbound Email Policy Enforcement](https://help.zscaler.com/zia/understanding-outbound-email-policy-enforcement).

The following diagram shows a high-level overview of how Zscaler Outbound Email DLP enforces outbound email policy:

1. A user sends an email containing sensitive information to an external domain.
2. The rules configured on your email server instruct Gmail, Microsoft Exchange, or your secure email gateway (SEG) to send the email to the Zscaler smart host via Simple Mail Transfer Protocol (SMTP) for DLP inspection.
3. The Zscaler DLP service performs DLP inspection and inserts email headers based on the verdict of the policy evaluation.
4. Inspected email is sent back to your email server.
5. Your email server enforces the DLP action based on the mail flow/transport rules and inserted headers in the email.
6. If the email is allowed to be sent, it is delivered to the next hop MTA or to the intended recipient.

To learn more about configuring Zscaler Outbound Email DLP, see [Step-by-Step Configuration Guide for Zscaler Outbound Email DLP](https://help.zscaler.com/zia/step-step-configuration-guide-zscaler-outbound-email-dlp).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/writing-pac-file","lastmod":"2026-09-02T17:17Z","nid":"1399396"} -->
## Writing a PAC File

- Source: https://help.zscaler.com/zia/writing-pac-file
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Traffic Forwarding > PAC Files > Using PAC Files > Writing a PAC File
- Last modified: 2026-09-02T17:17Z
- Summary: How to write a PAC file and include Zscaler-specific variables in the argument.

This article describes how to write a new [PAC file](https://help.zscaler.com/zia/understanding-pac-file).

1. Copy and paste any 1 of the 4 default PAC files, `recommended.pac`, `proxy.pac`, `mobile_proxy.pac`, or `kerberos.pac` from the Zscaler Admin Console based on your requirements. You can customize these default PAC files as necessary. See image.
2. Build your PAC file one element at a time.
3. Save the file and test it after each addition.

## Prerequisites

Before you begin, ensure that you review [Best Practices for Writing PAC Files](https://help.zscaler.com/zia/best-practices-writing-pac-files).

By default, each PAC file that you upload to the Zscaler service can be up to 256 KB in size. Contact Zscaler Support to increase the PAC file size limit to a maximum of 2 MB.

## PAC File Components

Be aware of the following components of a PAC file while writing it:

- FindProxyForURL Function
- Return Statements
- Arguments
- Zscaler-Specific Variables

## Manual Failover to Secondary Data Center

When the primary data center is down, the failover to the secondary data center is automatic. However, if the primary data center is unavailable for any other reason, such as ISP issues, latency issues, etc., modify the PAC file syntax as follows to manual failover to the secondary data center:

```
return "PROXY ${SECONDARY_GATEWAY_FX}:80; PROXY ${GATEWAY_FX}:80; DIRECT";
```

If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler Cloud>``.net}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler Cloud>``.net}`. For example:

```
return "PROXY ${SECONDARY.GATEWAY.
<Subcloud>.<
Zscaler
Cloud>
.net_FX}:80; PROXY ${GATEWAY.
<Subcloud>.<
Zscaler
Cloud>
.net_FX}:80; DIRECT";
```

- See a sample PAC file.

A PAC file must start with the opening function `FindProxyForURL(url, host)` on the first line. This function identifies which URLs, hostnames, or IP addresses are redirected to a proxy. The URL variable must contain the full URL of a destination (e.g., http://www.zscaler.com), and the host variable must contain a domain name or its IP address (e.g., zscaler.com or 72.249.144.174). Generally speaking, the host variable is preferable to the URL variable. If no URL or host is specified, all web requests use the return argument.

Enter a return statement in curly brackets after each argument. The return statement directs requests to a proxy or the specified destination. For example:

```
function FindProxyForURL(url, host)
                { return "PROXY ${GATEWAY}:9400"; return "DIRECT"; }
```

A return statement accepts one of two values:

- `DIRECT` tells the browser to bypass the proxy and go directly to the destination server.
- `PROXY` tells the browser to send the request to a proxy.

The `DIRECT` or `PROXY` values must be in uppercase characters and enclosed in quotation marks. Add a semicolon immediately after the closing quotation marks. For example:

```
return "DIRECT";
                return "PROXY ${GATEWAY}:9400";
```

Each PROXY statement must specify the fully qualified hostname or the IP address of the proxy and the port. IP addresses are generally discouraged because they can change at any time. Zscaler recommends that you use the variables `${GATEWAY}` and `${SECONDARY_GATEWAY}` instead. If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}` and`${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}`.

The Zscaler service uses its geolocation technology to automatically find the Public Service Edge for Internet & SaaS (ZIA) that is closest to you and with the quickest response time. Naming a primary and secondary gateway provides failover when one of the Public Service Edges is unavailable for any reason.

Public Service Edges accept web requests on ports 80, 443, 9400, 9443, and 9480.

- Port 80 is the standard port used by almost all web servers.
- Port 443 is the standard port used for encrypted (HTTPS) traffic. Port 9400 can be used instead if another host between the end user and Public Service Edge attempts to redirect the user’s traffic before it can reach the Public Service Edge. Ensure to allow port 9400 for outbound traffic destined for Public Service Edge from the user's end before adding the proxy entry to the PAC file. If port 9400 is blocked at the user's end for outbound traffic, do not add the proxy entry for the port to the PAC file. This ensures that there is no latency due to the browser's multiple attempts to connect to the blocked port until it fails over to a reachable port.
- Port 9443 can be used for remote users who need the service to proxy and inspect HTTPS transactions.
- Port 9480 can be used to exempt authentication for traffic that is destined for Public Service Edges from a known location. Remote users can use this port to forward traffic to Zscaler. However, they are forced to authenticate because Zscaler does not accept unauthenticated traffic from unregistered IP addresses or unknown locations.

Write the opening function `FindProxyForURL(url,host)` { } followed by a return statement on the next line. For example:

```
function FindProxyForURL(url, host)
                { return "PROXY ${GATEWAY}:9400"; return "DIRECT"; }
```

Save the text file and test it. Verify that the PAC file sends your browser to the Zscaler service with no other arguments, IF statements, or other elements. Confirm by going to [ip.zscaler.com](http://ip.zscaler.com/), which indicates if you reached the web server directly or through a Public Service Edge.

You can add various arguments that identify which internal or external hosts must be proxied to. Add one nested argument at a time, and then test and confirm that the PAC file works properly after each addition.

The following is a sample PAC file that contains arguments, each of which is preceded by a comment.

```
function FindProxyForURL(url, host)
                {
                //
                //Exclude FTP from proxy
                //
                if (url.substring(0, 4) == "ftp:")
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for internal hosts
                //
                if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                isInNet(host, "10.0.0.0", "255.0.0.0") ||
                isInNet(host, "127.0.0.0", "255.0.0.0") ||
                isInNet(host, "169.254.0.0", "255.255.0.0") ||
                isInNet(host, "172.16.0.0", "255.240.0.0") ||
                isInNet(host, "192.0.2.0", "255.255.255.0")||
                isInNet(host, "64.206.157.136", "255.255.255.255"))
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for this server
                //
                if (dnsDomainIs(host, "mail.domain.com"))
                {
                return "DIRECT";
                }
                return "PROXY ${GATEWAY}:9400; PROXY ${SECONDARY_GATEWAY}:9400; DIRECT";
                }
```

For the arguments in the preceding sample PAC file, specify the following:

- Excluding FTP Traffic
- Excluding Requests for Internal Hosts
- Excluding Internal Networks
- Excluding Servers

The following are the different Zscaler-specific variables you can use in your PAC file argument:

- Gateway Variable
- Gateway Host Variable
- Source IP Variable
- Country Variable
- Country Gateway Variable
- Country Gateway Host Variable
- Custom Port Variable
- Zscaler Client Connector Trusted Network Variable
- Zscaler Client Connector Z-Tunnel 2.0 Variable

The Zscaler-specific variables are applicable only if you host your PAC files on the Zscaler cloud.

The following lines exclude FTP traffic from being redirected to the proxy (since the service does not support native FTP):

```
//
                   //Exclude FTP from proxy
                   //
                if (url.substring(0, 4) == "ftp:") 
                    {
                     return "DIRECT";
                    }
```

The argument begins with a simple IF statement using the built-in function called `url.substring`. IF arguments are always followed by opening and closing parentheses—they describe the conditions under which the argument must be evaluated and for which a result is required. In the example, the argument specifies that if the URL contains the string `ftp:` in the first four characters of the URL (from 0 to 4), return DIRECT—bypass the proxy named in the following line.

The argument’s result must be enclosed within its own set of open and closed curly brackets.

Save this change to the PAC file and upload it to the Zscaler Admin Console. Reload the PAC file in your browser and go to an FTP download site such as ftp://ftp.hp.com/. After loading this page, log in to the specific Insights log (for example, Web Insights) to determine if this transaction was logged. The transaction should not appear in the logs.

The following lines in the PAC file example exclude requests for internal hosts from being redirected to a proxy:

```
//
                 //Do not send traffic to the following network to Zscaler
                 //
                if (isInNet(host), "192.168.0.0", "255.255.0.0")
                {
                 return "DIRECT";
                }
```

This argument uses the JavaScript function `IsInNet()`, which is typically used to identify either of the following:

- Client IP address (if the request comes from this IP address, use this proxy). Be aware that this argument returns the first IP address on your device, based on its OS. The first IP address, shown when you use the `ipconfig` command, might be the IPv6 address of the device or the IP address of virtual adapters and this can cause conflicts.
- Host server IP address (if the request is going to this address, use this proxy). Be aware that this argument results in a DNS lookup. It can impact performance if the DNS server is not available. Instead, you can use the following to constrain the `IsInNet()` function based on the host domains being accessed:

```
if dnsDomainIs(host, "internal.net") {
                if (isInNet(host, "10.0.0.0”, "255.0.0.0"))
                Return "DIRECT";
                }
```

Save the PAC file and test again. Browse to an internal host and ensure that you can reach it. If you were proxied through a Public Service Edge, your request would be denied. The request first goes outside your network to the Zscaler proxy but is then blocked as it tries to access an internal host as it comes back in from outside the network.

The following lines in the PAC file example exclude requests for multiple internal hosts from being redirected to a proxy:

```
//
                  //Bypass proxy for internal hosts
                  //
                 if(isInNet(host, "0.0.0.0", "255.0.0.0")||
                 isInNet(host, "10.0.0.0", "255.0.0.0") ||
                 isInNet(host, "127.0.0.0", "255.0.0.0") ||
                 isInNet(host, "169.254.0.0", "255.255.0.0") ||
                 isInNet(host, "172.16.0.0", "255.240.0.0") ||
                 isInNet(host, "192.0.2.0", "255.255.255.0")||
                 isInNet(host, "64.206.157.136", "255.255.255.255"))
                 {
                  return "DIRECT";
                 }
```

As shown in the example, before closing the IF argument with a final parenthesis, the Boolean `or` (||) operator is used to concatenate the internal networks. The last `isInNet` statement does not require the Boolean `or` (||) operator. Instead, it uses an additional parenthesis to close the opening parenthesis.

The last statement is a specific IP address, not a network. To bypass specific hosts, enter the exact IP address with a subnet mask of 255.255.255.255.

The use of `isInNet()` is extremely effective when the host you are trying to reach is an actual IP address. If you try to reach a host by a domain name, such as https://zscaler.com, your browser must perform a DNS lookup for it. If the hostname is not resolvable, the client needs to wait for DNS to time out before moving on. To avoid this and to avoid placing an undue strain on the name server, insert a variable that uses a regular expression immediately preceding the `isInNet()` argument to restrict the result just to IP addresses.

Therefore, instead of entering:

```
if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                isInNet(host, "10.0.0.0", "255.0.0.0") ||
                isInNet(host, "127.0.0.0", "255.0.0.0") ||
                ...)
                {
                  return "DIRECT";
                }
```

Enter this:

```
reip = /^\d+\.\d+\.\d+\.\d+$/g;
                if (reip.test(host))
                {
                 if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                 isInNet(host, "10.0.0.0", "255.0.0.0") ||
                 isInNet(host, "127.0.0.0", "255.0.0.0") ||
                    ...)
                 {
                  return "DIRECT";
                 }      
                }
```

Here, the variable `reip = /^\d+\.\d+\.\d+\.\d+$/g;` is used in the argument `if(reip.test(host))`, and the result is only used if the host is an IP address in one of the networks specified in the nested IF argument.

This can only be used for URLs that include IP addresses, such as http://192.0.2.3/example.

In some versions of Internet Explorer, the use of the preceding variable might not work. An alternative to this is to use the JavaScript shell expression match function:

```
if (shExpMatch(host, "/^\d+\.\d+\.\d+\.\d+$/g")) 
                {
                 if (isInNet(host, "10.0.0.0", "255.0.0.0") ||
                 isInNet(host, "192.168.0.0", "255.255.0.0")) 
                 {
                  return "DIRECT";
                 }      
                }
```

Save this change, reload the PAC file in your browser, and then try browsing to an internal web server in the internal network. If you can reach the server, you have bypassed the Public Service Edge.

The following lines in the PAC file example exclude e specific servers, such as mail.domain.com, from being redirected to a proxy. In the example, a separate `if isInNet()` argument lists internal hostnames:

```
//Bypass proxy for this server //
                if (dnsDomainIs(host, "mail.domain.com"))
                { return "DIRECT"; }
                //To bypass all hosts in a domain, use
                dnsDomainIs(host, "host.com")
                 
                You can also use the following to bypass specific internal hosts:
                var bypassHosts = /(remote\.mydomain\.com|mail\.mydomain\.com)/; 
                if (bypassHosts.test(host)) 
                {
                 
                  return "DIRECT";
                }
```

The preceding argument includes a variable that contains two hosts: remote.mydomain.com and mail.mydomain.com. Using the `JavaScript test (host)` function, any host you enter here returns `DIRECT` and does not require a DNS lookup. `var` is the JavaScript function to set a variable. `bypassHosts` is a JavaScript function. You must use this specific name or function. Forward slashes mark the beginning and ending boundaries of the variable. Open and close parentheses in the variable match the parentheses in the argument (test(host)). The periods in the hostnames must be “escaped” with a backslash. The variable itself requires a semicolon to close the variable argument.

You can use the `${GATEWAY}` and `${SECONDARY_GATEWAY}`variables to determine the Public Service Edge closest to the client. For example:

```
return "PROXY ${GATEWAY}:80; PROXY ${SECONDARY_GATEWAY}:80; DIRECT";
```

The Zscaler service uses its geolocation technology to find the closest Public Service Edge with the quickest response time. These variables provide the optimal user experience.

If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}`. For example:

```
return "PROXY ${GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net}:80; PROXY ${SECONDARY.GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net}:80; DIRECT";
```

If your organization has many users behind a single egress IP address, you can configure the PAC file to choose from multiple gateway IP addresses to distribute the load. To do this, use the following variables:

- The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.
- Regional surcharge data centers are not included in the gateway variable.

- Gateway Index Tokens
- Dynamic Gateway Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway IP addresses in a data center for load-balancing purposes.

Add the suffixes `_F0` through `_F7` to the `${GATEWAY}` variable in the PAC file for the PAC server to return the healthy gateway IP addresses through the 8 variables. For example, the `${GATEWAY_F0}` variable corresponds to the first healthy IP address that is available, the `${GATEWAY_F1}` variable corresponds to the second healthy IP address, and so on.

Use the following syntax to include the `${GATEWAY_F0}` variable in your PAC file:

```
return "PROXY ${GATEWAY_F0}:80; PROXY ${SECONDARY_GATEWAY_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateway IP addresses, then the PAC server allocates the available healthy VIP addresses to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the gateway index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}`.

Use the suffix `_FX` to the `${GATEWAY}` variable in the PAC file for the PAC server to dynamically issue gateway IP addresses based on the client fingerprints (all users coming from a single egress IP address are given an IP address from a pool of healthy gateway IP addresses). The fingerprint is used to ensure that a single device continues its session to the same gateway IP address.

Use the following syntax to include the `${GATEWAY_FX}`variable in your PAC file:

```
return "PROXY ${GATEWAY_FX}:80; PROXY ${SECONDARY_GATEWAY_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}`.

The `_FX` suffix provides load balancing across multiple VIP addresses depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Gateway Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${Gateway_Host}` variable to resolve to a hostname instead of an IP address. For Kerberos authentication and IPv6 traffic, this is a mandatory configuration.

Zscaler mandates using this variable and resolving your hostname to forward IPv6 traffic to Internet & SaaS. You must also ensure that [IPv6 support](https://help.zscaler.com/zia/configuring-ipv6-settings) is enabled for your organization and locations in the Zscaler Admin Console.

Use the following syntax to include the `${GATEWAY_HOST}` variable in your PAC file:

```
return "PROXY ${GATEWAY_HOST}:80; PROXY ${SECONDARY_GATEWAY_HOST}:80; DIRECT";
```

If your organization uses a subcloud, use the variables `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}`. For example:

```
return "PROXY ${GATEWAY.
<Subcloud>.<Zscaler cloud>
.net_HOST}:80; PROXY ${SECONDARY.GATEWAY.
<Subcloud>.<Zscaler cloud>
.net_HOST}:80; DIRECT";
```

If your organization has many users behind a single-egress IP address, you can configure the PAC file to choose from multiple gateway hosts to distribute the load. To do this, use the following variables:

The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.

- Gateway Host Index Tokens
- Dynamic Gateway Host Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway hosts in a data center for load-balancing purposes.

Add the suffixes `_F0` through `_F7` to the `${GATEWAY_HOST}` variable in the PAC file for the PAC server to return the healthy gateway host through the 8 variables. For example, `${GATEWAY_HOST_F0}` corresponds to the first healthy host that is available, `${GATEWAY_HOST_F1}` corresponds to the second healthy host, and so on.

Use the following syntax to include the `${GATEWAY_HOST_F0}` variable in your PAC file:

```
return "PROXY ${GATEWAY_HOST_F0}:80; PROXY ${SECONDARY_GATEWAY_HOST_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateways, then the PAC server allocates the available healthy gateway hosts to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the gateway host index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}`.

Use the suffix `_FX` to the `${GATEWAY_HOST`} variable in the PAC file for the PAC server to dynamically issue gateway hosts based on the client fingerprint (all users coming from a single egress IP address are given an IP address from a pool of healthy gateway IP addresses). The fingerprint is used to ensure that a single device continues its session on the same gateway host.

Use the following syntax to include the `${GATEWAY_HOST_FX`} variable in your PAC file:

```
return "PROXY ${GATEWAY_HOST_FX}:80; PROXY ${SECONDARY_GATEWAY_HOST_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}` and `${SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}`.

The `_FX` suffix provides load balancing for multiple gateway hosts depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Gateway Host Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${SRCIP}` variable to determine the client's public IP address. For example:

```
var egressip = "${SRCIP}";
                if (shExpMatch(egressip,"203.0.113.10")) {
                		/* User is in the office */
                		return "PROXY 10.84.0.188:80;DIRECT";
                }
```

You can use the `${COUNTRY}` variable to determine the client's country as shown in the following sample:

```
var country = "${COUNTRY}";
                if (shExpMatch(country,"Canada")) { 
                        /* User is in Canada */ 
                        return "PROXY ${COUNTRY_GATEWAY}:80;DIRECT"; 
                }
```

The `${COUNTRY}` variable supports the countries listed on [GeoNames](https://www.geonames.org/countries/). Your IP addresses might not fall into those categories.

You can also set exceptions for specific URLs when accessed from a specific country using the `${COUNTRY}`variable as shown in the following sample:

```
var country = "${COUNTRY}";
                if (shExpMatch(country,"Canada") && 
                ((dnsDomainIs(host,"*.oracleindustry.com")) || 
                (dnsDomainIs(host,"cegbuger-p6.oracleindustry.com")))) {
                return "PROXY ${COUNTRY_GATEWAY}:80;DIRECT";
                }
```

You can use the `${COUNTRY_GATEWAY}` and `${COUNTRY_SECONDARY_GATEWAY}` variables to determine the closest Public Service Edge in the client's country. For example:

```
return "PROXY ${COUNTRY_GATEWAY}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY}:80";
```

When there is no Public Service Edge configured in your country, the ${COUNTRY_GATEWAY} variable behaves just like the ${GATEWAY} variable.

If your organization uses a subcloud, use the variables `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net}`. For example:

```
return "PROXY ${COUNTRY_GATEWAY.
<Subcloud>.<Zscaler cloud>
.net}:80; PROXY ${COUNTRY_SECONDARY.GATEWAY.
<Subcloud>.<Zscaler cloud>
.net}:80; DIRECT";
```

This prevents clients that are near multiple Public Service Edges or in countries that are close to one another from having their traffic forwarded to the wrong Public Service Edges.

If your organization has many users behind a single egress IP address, you can configure the PAC file to choose from multiple gateway IP addresses within a country to distribute the load. To do this, use the following variables:

The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.

- Country Gateway Index Tokens
- Dynamic Country Gateway Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway IP addresses in a data center for load-balancing purposes.

Add the suffixes `_F0` through `_F7` to the `${COUNTRY_GATEWAY}` variable in the PAC file for the PAC server to return the healthy gateway IP addresses within the country through the 8 variables. For example, the `${COUNTRY_GATEWAY_F0}` variable corresponds to the first healthy IP address available, the `${COUNTRY_GATEWAY_F1}` variable corresponds to the second healthy IP address, and so on.

Use the following syntax to include the `${COUNTRY_GATEWAY_F0}` variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_F0}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateways, then the PAC server allocates the available healthy gateway IP addresses to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the country gateway host index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_F1}`.

Use the suffix `_FX` to the `${COUNTRY_GATEWAY}` variable in the PAC file for the PAC server to dynamically issue the gateway IP addresses within a country based on the client fingerprints (all users coming from a single egress IP address are given an IP address from a pool of healthy gateway IP addresses). The fingerprint is used to ensure that a single device continues its session to the same gateway IP address.

Use the following syntax to include the `${COUNTRY_GATEWAY_FX}`variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_FX}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_FX}`.

The `_FX` suffix provides load balancing for multiple gateway IP addresses depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Country Gateway Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${COUNTRY_GATEWAY_HOST}`variable to determine the closest Public Service Edge in the client's country using the hostname instead of an IP address.

Zscaler recommends this setting only for Kerberos authentication.

Use the following syntax to include the `${COUNTRY_GATEWAY_HOST}`variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_HOST}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_HOST}:80; DIRECT";
```

When there is no Public Service Edge configured in your country, the ${COUNTRY_GATEWAY_HOST} variable behaves just like the ${GATEWAY_HOST} variable.

If your organization uses a subcloud, use the variables `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST}`. For example:

```
return "PROXY ${COUNTRY_GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net_HOST}:80; PROXY ${COUNTRY_SECONDARY.GATEWAY.
<Subcloud>
.
<Zscaler cloud>
.net_HOST}:80; DIRECT";
```

If your organization has many users behind a single-egress IP address, you can configure the PAC file to choose from multiple gateway hosts within a country to distribute the load. To do this, use the following variables:

The load-balancing PAC variables are designed for non-tunnel traffic. You do not require these variables for load-balancing your tunnel (GRE/IPSec) traffic.

- Country Gateway Host Index Tokens
- Dynamic Country Gateway Host Tokens

Zscaler recommends this method if you want the users to be distributed across multiple gateway hosts in a data center for load-balancing purposes.

Add the suffixes`_F0` through `_F7` to the `${COUNTRY_GATEWAY_HOST}` variable in the PAC file for the PAC server to return the healthy gateway hosts within the country through the 8 variables. For example, the `${COUNTRY_GATEWAY_HOST_F0}` variable corresponds to the first healthy host that is available, the `${COUNTRY_GATEWAY_HOST_F1}` variable corresponds to the second healthy host, and so on.

Use the following syntax to include the `${COUNTRY_GATEWAY_HOST_F0}` variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_HOST_F0}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_HOST_F0}:80; DIRECT";
```

If the data center has fewer than 8 healthy gateways, then the PAC server allocates the available healthy gateway hosts to all 8 variables in a round-robin fashion.

If your organization uses a subcloud, you can use the country gateway host index tokens (`_F0`through`_F7`suffix) with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_F1}`.

Use the suffix `_FX` to the `${COUNTRY_GATEWAY_HOST}` variable in the PAC file for the PAC server to dynamically issue the gateway hosts within a country based on the client fingerprints, i.e., all users from a single egress IP address are given a gateway host from a pool of healthy gateway hosts. The fingerprint is used to ensure that a single device continues its session on the same gateway host.

Use the following syntax to include the `${COUNTRY_GATEWAY_HOST_FX}`variable in your PAC file:

```
return "PROXY ${COUNTRY_GATEWAY_HOST_FX}:80; PROXY ${COUNTRY_SECONDARY_GATEWAY_HOST_FX}:80; DIRECT";
```

You can also use the `_FX` suffix with the subcloud variables. For example, `${COUNTRY_GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}` and `${COUNTRY_SECONDARY.GATEWAY.``<Subcloud>``.``<Zscaler cloud>``.net_HOST_FX}`.

The `_FX` suffix provides load balancing for multiple gateway hosts depending on the HTTP headers (useragent, x-forwarded-for, and z-client). This variable is effective only for Zscaler Client Connector clients because the z-client ID is different for each user. To implement load balancing for non-Zscaler Client Connector clients, you can use the Country Gateway Host Index Tokens. To learn more, see [Load Balancing for PAC Forwarded Traffic](https://help.zscaler.com/zia/load-balancing-pac-forwarded-traffic).

You can use the `${TRUSTED_NETWORK}` variable to determine Zscaler Client Connector's current network type. The following values correspond to the supported network types: `ON_TRUSTED`, `OFF_TRUSTED`, `VPN_TRUSTED`, and `SPLIT_VPN_TRUSTED`. These values are taken only if the variable is used in an APP Profile PAC file.

If there is no network type available or if the variable is used in a forwarding profile or Proxy PAC file, then the variable takes the value `NOT_AVAILABLE`.

For example:

```
var network = "${TRUSTED_NETWORK}";
                if (network == "ON_TRUSTED")
                    return "PROXY ${GATEWAY_FX}:12345; PROXY ${SECONDARY_GATEWAY_FX}:12345; DIRECT";
                else if (network == "OFF_TRUSTED")
                    return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
```

This variable is applicable only if you use Zscaler Client Connector version 4.2.0 or later.

To use this variable, enable the **Use Preferred Port from PAC for Z-Tunnel 1.0** or the **Use Preferred Port from PAC for Z-Tunnel 2.0** option in the [Zscaler Client Connector Windows profile](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles) policy rule.

You can use the`${ZS_CUSTOM_PORT}` variable to return an appropriate custom port based on your country and the Z-Tunnel version. For example, if you are in China, then the variable is replaced with port 9401 for Z-Tunnel 2.0 requests and 9400 for Z-Tunnel 1.0 requests. The ports are subject to change.

However, if you are in a country other than China, then the variable is replaced with port 443 for Z-Tunnel 2.0 and Z-Tunnel 1.0 requests.

Use the following syntax to include the `${ZS_CUSTOM_PORT}` variable in your PAC file:

```
return "PROXY ${GATEWAY_FX}:${ZS_CUSTOM_PORT}; PROXY ${SECONDARY_GATEWAY_FX}:${ZS_CUSTOM_PORT}; DIRECT";
```

This variable is applicable only if you use Zscaler Client Connector version 4.2.0 or later.

You can use the `${ZT2_REQUEST}`variable to determine the Z-Tunnel version for which the PAC file is requested. This variable takes the value `true` if the request is for Z-Tunnel 2.0 or the value `false` if the request is for Z-Tunnel 1.0.

In this example, port 9981 is used for Z-Tunnel 2.0 requests and port 9980 is used for Z-Tunnel 1.0 requests.

```
var tunnel2 = "${ZT2_REQUEST}";
                if (tunnel2 == "true")
                    return "PROXY ${GATEWAY_FX}:9981; PROXY ${SECONDARY_GATEWAY_FX}:9981; DIRECT";
                else
                    return "PROXY ${GATEWAY_FX}:9980; PROXY ${SECONDARY_GATEWAY_FX}:9980; DIRECT";
```

```
function FindProxyForURL(url, host)
                {
                //
                //Exclude FTP from proxy
                //
                if (url.substring(0, 4) == "ftp:")
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for internal hosts
                //
                if (isInNet(host, "0.0.0.0", "255.0.0.0")||
                isInNet(host, "10.0.0.0", "255.0.0.0") ||
                isInNet(host, "127.0.0.0", "255.0.0.0") ||
                isInNet(host, "169.254.0.0", "255.255.0.0") ||
                isInNet(host, "172.16.0.0", "255.240.0.0") ||
                isInNet(host, "192.0.2.0", "255.255.255.0")||
                isInNet(host, "64.206.157.136", "255.255.255.255"))
                {
                return "DIRECT";
                }
                //
                //Bypass proxy for this server
                //
                if (dnsDomainIs(host, "mail.domain.com"))
                {
                return "DIRECT";
                }
return "PROXY ${SECONDARY_GATEWAY_FX}:9400; PROXY ${GATEWAY_FX}:9400; DIRECT";
}
```

[Image: Default PAC Files menu in the Zscaler Admin Console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zia-error-codes-kerberos-authentication","lastmod":"2026-06-11T12:27Z","nid":"1399596"} -->
## Internet & SaaS Error Codes for Kerberos Authentication

- Source: https://help.zscaler.com/zia/zia-error-codes-kerberos-authentication
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Authentication & Administration > User Management & Authentication Settings > Kerberos Authentication > Internet & SaaS Error Codes for Kerberos Authentication
- Last modified: 2026-06-11T12:27Z
- Summary: Information on the cause and solutions to several Public Service Edge for Internet & SaaS error codes for Kerberos authentication.

If Kerberos authentication fails, the Zscaler service displays a page with an error code. The following table lists the information on the Kerberos authentication error codes. For instructions on how to troubleshoot Kerberos, see [Troubleshooting Kerberos Authentication](https://help.zscaler.com/zia/troubleshooting-kerberos-authentication).

| Error Code | Description | When It Occurs | What to Do |
| --- | --- | --- | --- |
| 391000 | The page cannot load. | Generic error code. | Contact Zscaler Support. |
| 441000, 461000 | The user cannot be found. | The user was deleted or cannot be found in the registered domain. | Add the user. |
| 451000 | The domain does not exist. | The realm is not a registered domain on the Zscaler service. | Contact Zscaler Support to add the realm as a registered domain. |
| 471000 (Occurrence 1) | The proxy authorization header does not contain the encoded Kerberos ticket. In almost all cases, this means that NTLM was used. | The traffic was sent to the Public Service Edge for Internet & SaaS (ZIA) IP address. The default Zscaler PAC file was used instead of the Kerberos PAC file. **Symptoms**: klist does not show either the Public Service Edge or KDC tickets. | Change the PAC file. Ensure that you use the Kerberos PAC file. |
| 471000 (Occurrence 2) |  | The user's computer does not have the GPO updates, the GPO updates are not on the domain controller, or the user is not logged in to the domain. **Symptoms**: A traffic capture from the user's computer does not show any query to the domain controller for the Zscaler ticket. | Verify the registry settings to ensure the user's computer has the Zscaler Kerberos settings. If they are not on the computer, verify that the domain controller has the GPO configurations. Verify that the user is logged in to the domain. |
| 471000 (Occurrence 3) |  | AES encryption is not enabled in the realm trust on the domain controller. **Symptoms**: klist does not show either the Public Service Edge or KDC tickets. A traffic capture shows the domain controller returning ETYPE_NOSUPP errors. | Modify the realm trust relationship and ensure that the AES encryption option is selected. |
| 471000 (Occurrence 4) |  | The realm trust relationship is not configured on the domain controller or the realm trust was configured with an incorrect password. **Symptoms**: klist does not show the Zscaler KDC or Public Service Edge tickets. A traffic capture shows the domain controller returning PRINCIPAL_UNKNOWN error. | Verify the realm trust configuration. Ensure that the realm name is in upper case and that the Zscaler cloud name is correct. To learn how you can find your cloud name, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names). If they are both correct, then the passwords in the organization's realm and the Zscaler realm might not match. Log in to the Zscaler Admin Console, regenerate the trust password, and copy it. On the domain controller, delete the trust configuration and create a new one as described in [Kerberos Trust Relationship Configuration Guide for Windows Server and GPO Push](https://help.zscaler.com/zia/kerberos-configuration-example-trust-relationship-windows-server-2012-and-gpo-push#create-new-trust). Ensure that you use the newly generated password that you copied from the Zscaler Admin Console. |
| 471000 (Occurrence 5) |  | The user's computer cannot connect to the domain controller. **Symptoms**: When you run klist purge to clear the tickets, refresh the browser, and then run klist again, Kerberos tickets are not displayed, including those of the domain controller. | Verify that the computer can contact the domain controller. If the user is using DirectAccess, run netsh name show effectivepolicy to determine if the DirectAccess client considers the workstation as being in the intranet or outside. |
| 48100 | Invalid encoding of Kerberos credentials. | Possible header corruption. Ensure that there is no intermediate proxy or L7 device that might be corrupting the header. | Contact Zscaler Support. |
| 491000, 501000 (Occurrence 1) | Invalid Kerberos token or username. | The computer time is incorrect. | Kerberos is very sensitive to clocks being in sync. Ensure that computer time is correct and it synchronizes with an NTP server. |
| 491000, 501000 (Occurrence 2) |  | Other issues. | Contact Zscaler Support. |
| 510000 | The user name is too long. | The user's login name exceeds the maximum allowed characters. | Contact Zscaler Support. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-authentication-bridge-server-certificate-renewal-process","lastmod":"2026-05-07T21:06Z","nid":"1402261"} -->
## Zscaler Authentication Bridge Server Certificate Renewal Process

- Source: https://help.zscaler.com/zia/zscaler-authentication-bridge-server-certificate-renewal-process
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Authentication & Administration > User Management & Authentication Settings > Zscaler Authentication Bridge > Zscaler Authentication Bridge Server Certificate Renewal Process
- Last modified: 2026-05-07T21:06Z
- Summary: Information on how to renew the Zscaler Authentication Bridge server certificate when it expires.

A server certificate that is uploaded onto the [Zscaler Authentication Bridge (ZAB)](https://help.zscaler.com/zia/about-zscaler-authentication-bridge) will eventually expire. When the certificate expires, it can be renewed using the following method:

- Step 1: Create a Directory on the ZAB
- Step 2: Verify that the Files are on the ZAB
- Step 3: Perform a Test and Validate the New ZAB Server Certificate

1. Create a directory on the ZAB to host the certificate files.
2. SCP into the ZAB and copy the certificate files to `var/cacert`.

- Server Certificate - `example.pem`
- Certificate Key - `example.key`
- Root Chain - `example.pem`

1. Validate that the files are copied onto the ZAB.
2. During the maintenance window, install the certificate files from the directory. To learn more, see [Deploying a Zscaler Authentication Bridge](https://help.zscaler.com/zia/deploying-zscaler-authentication-bridge#cert-signed-trusted-cert-auth).
3. Restart the ZAB service and validate the ZAB status.

1. After the new certificate files are uploaded to the ZAB and services are started, establish the SSL connection to the ZAB by using OpenSSL. Validate that the new certificate is being returned by the ZAB and look for the validity period of the certificate returned. Then, get redirected to the ZAB.
2. Test the browser and ensure that there are no certificate errors on the ZAB authentication page.
3. Configure the browser proxy settings to `gateway.<Zscaler cloud name>.net:9443`and add the ZAB hostname to proxy bypass. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names). With proxy settings in place, access any internet page to get redirected for authentication. On the Zscaler authentication page, use a dummy `user@domain.com` account to get redirected to ZAB.
4. Sign in and validate the new certificate. After clicking **Sign In**, you will be redirected to the ZAB for authentication. Ensure the internet page loads without any certificate errors.
5. Validate that the new certificate is being presented on the ZAB authentication page.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-dns-security-and-control-0","lastmod":"2025-09-04T09:51Z","nid":"1451391"} -->
## Zscaler DNS Security and Control

- Source: https://help.zscaler.com/zia/zscaler-dns-security-and-control-0
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Reference Architecture > Zscaler DNS Security and Control
- Last modified: 2025-09-04T09:51Z
- Summary: The Zscaler DNS Security and Control reference architecture guide steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler DNS Security and Control services offer mechanisms to take control of your DNS architecture and response. By proxying the DNS request, you can enforce your organization’s DNS policies in the Zscaler Zero Trust Exchange (ZTE). When the DNS request reaches the ZTE, the request is open and inspected. No DNS requests can bypass inspection unless you authorize it, as you can restrict your users to only using DNS servers you specify.

[Zscaler DNS Security and Control: Reference Architecture Guide](https://help.zscaler.com/downloads/zia/reference-architecture/zscaler-dns-security-and-control-0/zscaler-dns-security-and-control-reference-architecture.pdf) [Download PDF](https://help.zscaler.com/downloads/zia/reference-architecture/zscaler-dns-security-and-control-0/zscaler-dns-security-and-control-reference-architecture.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zia/zscaler-endpoint-dlp-release-summary-2026","lastmod":"2026-10-08T18:39Z","nid":"1535357"} -->
## Zscaler Endpoint DLP Release Summary (2026)

- Source: https://help.zscaler.com/zia/zscaler-endpoint-dlp-release-summary-2026
- Product: Internet & SaaS (ZIA)
- Path: Internet & SaaS (ZIA) Help > Release Notes > Zscaler Endpoint DLP Release Notes > Zscaler Endpoint DLP Release Summary (2026)
- Last modified: 2026-10-08T18:39Z
- Summary: Zscaler Endpoint DLP release summary for updates deployed, per OS and version, in 2026.

This article provides a summary of all new features and enhancements released per operating system (OS) for Zscaler Endpoint DLP.
<!-- /ZS-ARTICLE -->

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
