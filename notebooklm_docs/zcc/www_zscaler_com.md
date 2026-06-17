# ZCC (Zscaler Client Connector) ドキュメント — www.zscaler.com

---

<!-- source: https://www.zscaler.com/blogs/product-insights/communicating-security-notifications-users-zscaler-client-connector-eun -->

# Communicating Security Notifications to Users with Zscaler Client Connector EUN Notifications

Zscaler Blog

Get the latest Zscaler blog updates in your inbox

# Communicating Security Notifications to Users with Zscaler Client Connector EUN Notifications

Contents

- Communicating Security Notifications to Users with Zscaler Client Connector EUN Notifications

- More blogs

In the networking world, there is a widely known adage: "It's always the network" . This phrase refers to the tendency of users to blame network connectivity whenever access to a resource fails, even if the true reason lies elsewhere—such as being blocked by a corporate security policy.

## The Need for Better User Communication

When end-users receive no clear notification of why access to an application or network has been denied or other action taken, it is natural for them to assume the failure stems from a "networking issue." Left in the dark, users often retry accessing the resource, wasting valuable time and, eventually, filing help desk tickets.

This pattern creates multiple challenges:

- Increased workload for IT support teams , draining resources that could be allocated elsewhere.

- Frustration across the business , as employees feel hindered by network inefficiencies.

- Potential security risks , as users may attempt to bypass corporate security restrictions by leveraging unsanctioned third-party solutions.

In most instances, employees adopting workarounds are driven by necessity, not malice—they simply want to complete tasks without engaging with technical barriers they don’t fully understand.

The solution? Providing clear, timely end-user notifications (EUNs) that inform users when access to a specific resource is blocked, along with the reason for the restriction.

Such transparency not only reduces the volume of unnecessary tickets but also cultivates better-informed, security-aware employees. Over time, this strengthens the organization’s overall security posture.

## A Unique Challenge: Non-Web Traffic EUNs

For web traffic, user notifications are relatively straightforward: organizations can display a web-based End-User Notification (EUN) page explaining the block. This page might include customized corporate branding, a message specific to the policy violation, and instructions for contacting IT support if needed.

But not all traffic is web-based. What happens, for example, when a user tries to access a resource via SSH in a public cloud, only to have the attempt blocked by a security policy? Since there’s no browser-based interaction, traditional EUN pages can’t be displayed in such cases. This can leave users confused, wasting time trying to troubleshoot what they perceive as “networking” or application-related issues.

## Enter Zscaler Client Connector EUN Notifications

This is where Zscaler Client Connector EUN Notifications step in to fill the gap. Starting with Zscaler Client Connector version 4.8 (used in conjunction with Z-Tunnel 2.0), notifications can now be surfaced directly to the user for ZIA policies, clearly explaining that access to a site or resource has been blocked by a corporate security policy.

### Expanded Policy Support

Previously, ZCC-based notifications were available for policies such as Inline Web Data Loss Prevention (DLP), Endpoint DLP, and Cloud App Control. Recently, Zscaler has enhanced these capabilities to include:

- Firewall Filtering

- DNS Control

- Intrusion Prevention System (IPS) Control

This expanded support is particularly valuable for non-web traffic, where no web-based EUN page can be presented.

## Key Use Cases for EUN Notifications

Here are some common scenarios in which Zscaler Client Connector EUN Notifications offer clarity:

- DNS Control Actions:

- When a DNS request is blocked due to a classification (e.g., a domain falls under a restricted category). When DNS Control redirects a request (e.g., A-record response redirected to a specified IP), but no subsequent web flow occurs, leaving the user without context for the block. Firewall or IPS Control Actions:

When a DNS request is blocked due to a classification (e.g., a domain falls under a restricted category).

- When DNS Control redirects a request (e.g., A-record response redirected to a specified IP), but no subsequent web flow occurs, leaving the user without context for the block.

- Firewall or IPS Control Actions:

- When attempts to use protocols such as SSH are blocked. When an IPS signature match triggers a block, users are left wondering why their application or connection isn't functioning as expected.

- When an IPS signature match triggers a block, users are left wondering why their application or connection isn't functioning as expected.

EUN notifications eliminate this ambiguity by clearly communicating the reason behind the restriction, for example, by communicating:

- Block actions on non-web traffic to the user.

- Warnings to the user when they go to a suspicious domain or use a protocol or application that is not banned but dangerous.

- Remediation steps to the user (opening a ticket, not running an app etc.).

## Key Capabilities of Zscaler Client Connector EUN Notifications

Customizable Messaging:

- A default EUN message is available, but you can tailor messages by policy type (e.g., Firewall, DNS, IPS Control) to better suit your organization's requirements. This can include details such as the remediation steps such as contact information for opening a ticket.

- Administrators can control the specific data displayed in the EUN message. For example, when users are blocked from going to a suspicious domain by a DNS Control policy, the EUN notification can include additional details such as the domain category, thereby providing clarity to the user.

Policy-Specific Enablement:

- Organizations can activate Client Connector EUN notifications on a per-policy basis for Firewall, DNS Control, and IPS Control actions.

Severity-Based Color Coding:

- Visual indicators allow users to quickly understand the severity of the block: Red: Severe enforcement, such as "Block" actions for DNS, Firewall, or IPS policies. Amber: Less severe actions, such as "Redirect Response" for DNS or "Allow" for IPS.

- Red: Severe enforcement, such as "Block" actions for DNS, Firewall, or IPS policies.

- Amber: Less severe actions, such as "Redirect Response" for DNS or "Allow" for IPS.

Supported Actions:

- DNS Control: Block (Red) Block with Response Code (Red) Redirect Response (Amber)

DNS Control:

Block (Red)

Block with Response Code (Red)

Redirect Response (Amber)

- Firewall Policies: Block/Drop (Red) Block/ICMP (Red) Block/Reset (Red)

Firewall Policies:

Block/Drop (Red)

Block/ICMP (Red)

Block/Reset (Red)

- IPS Control: Allow (Amber) Block/Drop (Red) Block/Reset (Red)

IPS Control:

Allow (Amber)

## Summary

The Zscaler Client Connector EUN Notification is a game-changing feature that enhances end-user visibility across both web and non-web traffic. It eliminates confusion by notifying users when their access is denied due to corporate security policies, reducing unnecessary IT support tickets and reclaiming employee productivity.

Beyond operational efficiency, these notifications also foster a culture of security awareness across your organization, ensuring employees understand and respect corporate policies, consequently improving the organization's security posture.

With this feature, Zscaler continues to empower businesses by prioritizing both security and user experience. No longer will users believe "it's always the network." Instead, they’ll know exactly what’s happening—and why.

## Was this post useful?

Disclaimer: This blog post has been created by Zscaler for informational purposes only and is provided "as is" without any guarantees of accuracy, completeness or reliability. Zscaler assumes no responsibility for any errors or omissions or for any actions taken based on the information provided. Any third-party websites or resources linked in this blog post are provided for convenience only, and Zscaler is not responsible for their content or practices. All content is subject to change without notice. By accessing this blog, you agree to these terms and acknowledge your sole responsibility to verify and use the information as appropriate for your needs.

## Explore more Zscaler blogs

Zscaler Internet Access (ZIA) and CrowdStrike: Zero Trust Access Control Based on Device Security Posture

Five Must-Haves for Data Security Posture Management (DSPM)

Zero Trust is Shaking up VPN Strategies

## Get the latest Zscaler blog updates in your inbox

By submitting the form, you are agreeing to our privacy policy .

---

<!-- source: https://www.zscaler.com/blogs/product-insights/enhancing-security-flow-logging-exploring-zscaler-client-connector-s-key -->

# Enhancing Security with Flow Logging: Exploring Zscaler Client Connector's Key Feature

Zscaler Blog

Get the latest Zscaler blog updates in your inbox

# Enhancing Security with Flow Logging: Exploring Zscaler Client Connector's Key Feature

Contents

- Article

- More blogs

Organizations face increasing cyberthreats in today’s digital landscape, making robust security measures a top priority. As a leader in cloud security, we offer a powerful feature in Zscaler Client Connector called Flow Logging, which enhances visibility, threat detection, and incident response capabilities. Let’s dive into the details of Flow Logging and how it contributes to a secure and efficient network environment.

“Let’s try excluding the traffic.” Does that sound familiar? If you’re in IT and have been in troubleshooting sessions or tried to get an application to work, you’ve probably tried it. With the massive increase in remote users and the rate at which IT needs to scale, you sometimes have to move corporate policies around to address top priorities.

Zscaler Client Connector is a software agent that provides secure access to the Zscaler Zero Trust Exchange platform. Flow Logging, available from Client Connector 4.0 onward, provides critical visibility into traffic not sent through Zscaler Internet Access or Zscaler Private Access (i.e., excluded traffic). For instance, if you need to exclude Microsoft Teams traffic from ZIA, Flow Logging ensures all Teams session connectivity is artificially logged to ZIA.

### 3 Key Benefits of Flow Logging

#### 1. Enhanced visibility

Many customers are looking for ways to send only desired traffic through ZIA, but excluding traffic comes with a key downside: losing visibility of traffic and user activity. Flow Logging enables tracking of excluded traffic, giving security teams comprehensive visibility and deep insights into user activity, application usage, and data flows.

#### 2. Threat detection and incident response

Security analysts can proactively monitor and detect malicious activities by leveraging the captured flow logs. With Flow Logging enabled, you can proactively track and correlate any malicious activity across endpoints and users, even for excluded traffic.

#### 3. Troubleshooting and reporting

Flow logs are easy to filter and navigate in ZIA Analytics to assist with security investigations and network troubleshooting. By analyzing flow logs, IT teams can identify and resolve any events related to bypassed traffic. Reports are embedded in the Insights section and spread across all the Insights sections in the ZIA dashboard.

### Easily, Flexible Configuration

Configuring Flow Logging takes just a couple of clicks. In the app profile, we’ve introduced a new section for enabling Flow Logging. Flow tracking is enabled per app profile, and an administrator can start or stop a flow based on your organization's needs and policies.

Admin can select specific flows:

- VPN: VPN connection (outer tunnel toward VPN server)

- VPN tunnel: Inner tunnel traffic (actual traffic flows within the VPN tunnel)

- Direct: Direct internet traffic

- Intranet: RFC 1918, IPv6 Intranet

- Loopback: Loopback IPv4 and IPv6

- Block traffic: T2 fallback option, drop IPv6 configuration, block due to strict enforcement mode, block due to disaster recovery configuration

Flow Logging, available through Zscaler Client Connector, offers your organization a powerful tool that enhances visibility, threat detection, and incident response capabilities for bypassed traffic. By capturing and analyzing detailed flow logs, you can gain key insights to proactively identify and mitigate security risks while facilitating audit, troubleshooting, and operational efficiency.

Incorporating Flow Logging into your security strategy can significantly strengthen your organization's defenses and ensure a secure, efficient network environment.

## Was this post useful?

Disclaimer: This blog post has been created by Zscaler for informational purposes only and is provided "as is" without any guarantees of accuracy, completeness or reliability. Zscaler assumes no responsibility for any errors or omissions or for any actions taken based on the information provided. Any third-party websites or resources linked in this blog post are provided for convenience only, and Zscaler is not responsible for their content or practices. All content is subject to change without notice. By accessing this blog, you agree to these terms and acknowledge your sole responsibility to verify and use the information as appropriate for your needs.

## Explore more Zscaler blogs

Why should CISOs take a managed security service approach to cybersecurity?

Introduction: OTZ Use Cases | Zscaler Zero Trust Exchange

Dynamic Latency-based ZIA Service Edge Assignment

Introducing Zscaler EASM

01
                /
                02

## Get the latest Zscaler blog updates in your inbox

By submitting the form, you are agreeing to our privacy policy .

---

<!-- source: https://www.zscaler.com/blogs/product-insights/introducing-location-based-policy-client-connector -->

# Introducing Location-based Policy with Client Connector

Zscaler Blog

Get the latest Zscaler blog updates in your inbox

# Introducing Location-based Policy with Client Connector

Contents

- Key benefits

- Location-based Policy

- Protecting the endpoint at any location

- Wrap-up

- More blogs

According to a recent Gallup report*, 52% of all workers have a hybrid work arrangement, and 25% now exclusively work remotely. In other words, there are a lot of user devices that no longer sit in an office behind traditional firewalls. So, how do you prevent these devices from leaking sensitive data and ensure that they have the smallest attack surface possible when they are used in coffee shops and home offices?

Thousands of organizations with remote and hybrid workers already use Zscaler’s lightweight agent, Client Connector, to provide their workforce with secure, zero trust connectivity to the internet, SaaS, and private applications. And now, Zscaler is introducing a new Client Connector feature called Location-Based Policy, which enables the ability to steer traffic and apply inbound and outbound endpoint firewall rules based on the trust status of the location of the endpoint, while reducing the effort required to manage policies across groups of users,devices and types of trusted locations.

## Key benefits:

- DNS and user traffic steering based on trusted location type

- Inbound and outbound endpoint firewall management based on type of trusted location

- A new policy framework with support for reusable objects and bulk changes

## Location-based Policy

## Protecting the endpoint in any location

When the Location-based Policy feature is enabled and a hybrid worker is at the office (which is considered a trusted location) Client Connector detects the location type and applies the Policy Ruleset for "On-Trusted Network". Since the organization has deployed Zero Trust for private application access with Zscaler Private Access (ZPA) externally only at this time, the laptop should disable ZPA forwarding in the office, access network resources and query internal domain names using the network DNS servers directly. However, when the laptop accesses SaaS applications or any external resources,  traffic should be forwarded to the Zscaler Internet Access (ZIA) service to ensure that user-specific policies for access, security, data protection, DNS security, IPS and firewall are enforced. Windows Defender Firewall on the laptop should  allow all inbound and outbound traffic with the Domain Profile.

The Policy Ruleset for "On-Trusted Network" can be configured with a traffic steering policy to forward all DNS requests for internal domains and all traffic to internal networks directly on the network. The traffic steering policy can be configured to tunnel all other traffic with Z-Tunnel 2.0 to ensure that traffic is protected by Zscaler Internet Access (ZIA). The Policy Ruleset for "On-Trusted Network" can be configured to disable endpoint firewall management so Client Connector doesn't make any changes to the endpoint firewall and Windows Defender Firewall continues to operate as it did previously.

When the hybrid worker, leaves the office after lunch for an offsite customer meeting (which is considered an untrusted location), Client Connector detects the change immediately when the laptop wakes up from sleep and applies the Policy Ruleset for "Off-Trusted Network". As per the organization's security policy, all DNS requests should be protected using DNS security with Zscaler Zero Trust Firewall and all other traffic should be tunneled to the Zero Trust Exchange with Z-Tunnel 2.0 except requests to private applications, which should be tunneled using Zscaler Private Access (ZPA). Lastly, all inbound and outbound traffic to/from the local network and any RFC-1918 networks should be blocked by the Windows firewall with the exception of inbound ZPA Client-to-Client traffic which is used by the IT Servicedesk for remote support.

The Policy Ruleset for "Off-Trusted Network" can be configured with a traffic steering policy, which forwards all DNS requests and application traffic to Zscaler Internet Access (ZIA) while enabling access to the organization's private applications via Zscaler Private Access (ZPA). Endpoint firewall management can be enabled in the App Profile and a firewall policy defined in the Policy Ruleset to enforce Windows firewall rules. The firewall rules can be configured to block all inbound traffic with the exception of ZPA Client-to-Client traffic and block outbound access to RFC-1918 and local networks while allowing all traffic to the Zscaler Zero Trust Exchange.

With the "Off-Trusted Network" Policy Ruleset now active, Client Connector steers traffic accordingly and in this case, enables endpoint firewall management. With endpoint firewall management active, Client Connector creates Windows firewall rules in separate WFP sublayer which override Windows Defender Firewall rules. As a result, the active Windows Defender Firewall configuration and rules are left intact and not modified in any way. Since Zscaler Private Access (ZPA) is active, Windows Defender Firewall switches to the Domain Profile (as per default behavior) but Client Connector ensures that the Windows firewall enforces the Client Connector firewall rules defined in the Policy Ruleset instead of the rules defined in Windows Defender Firewall's Domain Profile.

To further secure the device, the App Profile assigned to Client Connector can be configured with the Trigger Domain Profile Detection feature (introduced in previous releases) which ensures that Windows Defender Firewall continues to function using its Public Profile when Client Connector is in an "Off-Trusted Network" location and Zscaler Private Access (ZPA) is active. With the above feature enabled and Windows Defender Firewall Public Profile configured with a default Block policy for all inbound and outbound traffic, if the user were to disable the Zscaler Internet Access (ZIA) service in Client Connector, the Windows Defender Firewall Public Profile configuration would take effect and ensure that the Windows laptop doesn't fail-open and become a target for attackers and the only way the user would be able to access SaaS, internet or private applications would be by re-enabling Client Connector forwarding.

## Wrap-up

## Was this post useful?

Disclaimer: This blog post has been created by Zscaler for informational purposes only and is provided "as is" without any guarantees of accuracy, completeness or reliability. Zscaler assumes no responsibility for any errors or omissions or for any actions taken based on the information provided. Any third-party websites or resources linked in this blog post are provided for convenience only, and Zscaler is not responsible for their content or practices. All content is subject to change without notice. By accessing this blog, you agree to these terms and acknowledge your sole responsibility to verify and use the information as appropriate for your needs.

## Explore more Zscaler blogs

Key Benefits of Zscaler’s Do-It-All Agent, Client Connector

Zero Trust: Begin Your Journey with These First Steps

Empowering Critical Infrastructure with Zscaler Cellular

## Get the latest Zscaler blog updates in your inbox

By submitting the form, you are agreeing to our privacy policy .

---

<!-- source: https://www.zscaler.com/blogs/product-insights/key-benefits-zscaler-s-do-it-all-agent-client-connector -->

# Key Benefits of Zscaler’s Do-It-All Agent, Client Connector

Zscaler Blog

Get the latest Zscaler blog updates in your inbox

# Key Benefits of Zscaler’s Do-It-All Agent, Client Connector

Contents

- Key Benefits of Zscaler’s Do-It-All Agent, Client Connector

- More blogs

Client Connector is one of the core Zscaler technologies that enable organizations to embrace zero trust architecture . It is a lightweight yet multi-faceted software agent that is installed on end users’ devices. As a result, their traffic can be routed through the Zero Trust Exchange , Zscaler’s global security cloud, which serves as an intelligent switchboard and provides secure any-to-any connectivity—without extending the network to anyone or anything.

While there are other mechanisms for routing traffic through Zscaler and giving users least-privileged access to IT resources (like Zero Trust Browser ), Client Connector is the primary technology Zscaler customers use to secure their workforces. In light of its importance for countless organizations around the globe, let’s discuss a few of its key benefits.

## Zero trust connectivity to any destination

To secure user access to different types of destinations, organizations often rely on multiple tools from multiple vendors, each with their own endpoint agent. For example, they might use a secure web gateway (SWG) agent to secure web access, a cloud access security broker (CASB) agent to secure the use of SaaS apps, and a zero trust network access (ZTNA) agent to secure access to private apps.

Since its inception, Zscaler has always aspired to provide direct, any-to-any connectivity without providing network connectivity. Naturally, that includes providing secure user access to any destination. As such, Client Connector is designed to facilitate that overarching goal. Whatever users may be accessing, they only need this one agent for secure zero trust connectivity (which also checks the boxes for security service edge (SSE) ).

## Zero trust connectivity for any device

In today’s technology landscape, employees can use countless different devices to do their work. So, just as users need zero trust access to any destination, they also need zero trust access from any device. Fortunately, Client Connector can secure any user device, including mobile phones, laptops, and tablets, and supports a breadth of operating systems, including Windows, macOS, iOS, Android, Linux, and ChromeOS.

## Protection for data on endpoints

As a part of securing any-to-any connectivity with zero trust, Zscaler helps customers enforce least-privileged access to data and secures any potential leakage path. To address the challenge of data leakage via user devices, organizations typically deploy a dedicated data loss prevention (DLP) agent onto said devices.

Client Connector circumvents that need with its own endpoint DLP functionality. It identifies sensitive information and prevents it from leaking via removable storage, printing, personal cloud storage, and network shares. This both simplifies and enhances data protection—particularly when combined with the other components of Zscaler’s comprehensive data protection offering .

## Deception and detection of hidden adversaries

To further enhance organizations’ security postures, Zscaler’s deception technology takes a proactive approach to stopping stealthy, advanced cyberthreats. Stated simply, it places realistic decoys throughout the IT ecosystem to lure hidden attackers and generate high-fidelity alerts. Client Connector plays a key role in this capability: Zscaler Deception uses the agent to deploy decoy app bookmarks, cookies, sessions, and passwords on users’ devices. Then, if malicious entities search for potential targets on an endpoint, they will encounter decoys and be caught red-handed.

## Superior user experience and productivity

Security is often seen as the enemy of productivity, as something that merely adds latency and slows users down. But the opposite is true with Zscaler—and not just because of its high-performance cloud , resilience functionality , intelligent traffic routing, and “ single scan, multi-action ” approach to security. It is also, in large part, due to Zscaler Digital Experience (ZDX) , a digital experience monitoring (DEM) solution built directly into Client Connector.

While conventional monitoring efforts entail siloed point products and blind spots, ZDX has full hop-by-hop visibility across the entire user connection. Because of Client Connector, that includes visibility into the performance of users’ devices. As such, IT teams can see device health metrics associated with CPU, memory, active processes, disk I/O, and more, helping them to identify and resolve user experience issues quickly.

## Wrap-up

By providing all of the above functionality and serving as a do-it-all agent that consolidates point products, Client Connector offers other benefits, as well:

- It cuts down on endpoint agent bloat, freeing up endpoint resources so employees can have a better user experience and work more productively.

- It reduces the management burden for IT teams, who no longer have to oversee myriad point product agents—streamlining deployments, troubleshooting, and software updates.

- It optimizes technology costs, reduces overhead costs, and saves money in multiple other ways for organizations.

In summary, Client Connector plays a key role in securing, simplifying, and transforming Zscaler customers. If you would like to learn more about it, click here .

Or, if you would like to learn more about zero trust, sign up for our webinar series, Zero Trust, from Theory to Practice , which is designed to guide you throughout your entire zero trust journey.

## Was this post useful?

Disclaimer: This blog post has been created by Zscaler for informational purposes only and is provided "as is" without any guarantees of accuracy, completeness or reliability. Zscaler assumes no responsibility for any errors or omissions or for any actions taken based on the information provided. Any third-party websites or resources linked in this blog post are provided for convenience only, and Zscaler is not responsible for their content or practices. All content is subject to change without notice. By accessing this blog, you agree to these terms and acknowledge your sole responsibility to verify and use the information as appropriate for your needs.

## Explore more Zscaler blogs

What Did Plato Have to Say About Zero Trust Security?

Zero Trust: Begin Your Journey with These First Steps

Four Steps for a Successful Zero Trust Journey

How Zscaler Provides an Unrivaled Zero Trust Architecture

01
                /
                02

## Get the latest Zscaler blog updates in your inbox

By submitting the form, you are agreeing to our privacy policy .

---

<!-- source: https://www.zscaler.com/blogs/product-insights/long-term-support-zscaler-client-connector -->

# Long-Term Support for Zscaler Client Connector

Zscaler Blog

Get the latest Zscaler blog updates in your inbox

# Long-Term Support for Zscaler Client Connector

Contents

- Long-Term Support for Zscaler Client Connector

- More blogs

The Long-Term Support (LTS) initiative for Zscaler Client Connector provides a solution for customers who require stable, minimal-update software versions that still maintain both proper security and support. Under this program, Zscaler guarantees support for designated LTS versions for the 12 months following their announcement dates. During that time, Zscaler’s focus will be placed solely upon addressing critical bugs and security issues—customers should note that no new features are introduced for these versions.

Beginning on January 31st of 2026 and lasting for 12 months, the LTS versions of Client Connector that will be supported across operating systems are:

- Windows: Client Connector Version 4.7

- macOS: Client Connector Version 4.5.2

- Linux: Client Connector Version 3.7.2

These LTS versions are intended for customers with strict control policies on version upgrades. They will allow customers to remain secure and supported, but without any feature changes.

Using the latest Client Connector version is still advised

Despite this Long-Term Support initiative, it is important to note that Zscaler still encourages customers to adopt the latest versions of Client Connector whenever possible. That’s because the latest versions typically include additional vulnerability patches, bug fixes, features, and improvements that enhance both overall security and user experiences. So, for customers using outdated Client Connector versions, there is significant cyber risk—not to mention, they are ineligible for support.

As a result of the above, Zscaler has implemented a phased plan to deprecate and block connections from older, unsupported versions. Ultimately, the goal is to require compliance with supported versions only. But in the near term, the most pertinent information can be seen below.

Phase 1 (Effective February 1, 2026): Zscaler will not allow new device enrollment for users running Client Connector 1.x and 2.x on Windows, Mac, and mobile platforms. For Linux devices, versions prior to 1.5 will be blocked from enrollment. Users already enrolled will not be immediately impacted, but will be unable to re-enroll after logging out of Client Connector.

For additional details on this plan, refer to the Zscaler trust portal .

## Was this post useful?

Disclaimer: This blog post has been created by Zscaler for informational purposes only and is provided "as is" without any guarantees of accuracy, completeness or reliability. Zscaler assumes no responsibility for any errors or omissions or for any actions taken based on the information provided. Any third-party websites or resources linked in this blog post are provided for convenience only, and Zscaler is not responsible for their content or practices. All content is subject to change without notice. By accessing this blog, you agree to these terms and acknowledge your sole responsibility to verify and use the information as appropriate for your needs.

## Explore more Zscaler blogs

Key Benefits of Zscaler’s Do-It-All Agent, Client Connector

Introducing Location-based Policy with Client Connector

What Did Plato Have to Say About Zero Trust Security?

## Get the latest Zscaler blog updates in your inbox

By submitting the form, you are agreeing to our privacy policy .

---

<!-- source: https://www.zscaler.com/products-and-solutions/zscaler-client-connector -->

# Zscaler Client Connector

# Zscaler Client Connector

Securely connect users to any destination from any device or location.

- Overview

- Why It’s Important

- Architecture Matters

- The Zscaler Difference

- The Zscaler Platform

- Benefits

- Resources

- Request a demo

## Zscaler Client Connector™ is a lightweight agent for any user endpoint, enabling hybrid work through secure, fast, and reliable access to any destination, over any network.

Why It’s Important

## A mobile world needs secure remote access

Users now work from anywhere, and require seamless access to websites, SaaS apps, and private apps, whether in the cloud or a data center. To help workforces be productive and secure, organizations need to retire network-centric solutions in favor of simpler, safer, zero trust communications.

Architecture Matters

Firewall-and-VPN architectures connect users to the network for security and connectivity—even remote users accessing SaaS. This slows productivity, enables lateral threat movement, and entails other risks .

Client Connector, a lightweight agent, forwards user traffic to the Zscaler Zero Trust Exchange , the world’s largest cloud security platform, which acts as an intelligent switchboard to securely connect users directly to their destinations.

The Zscaler Difference

## What sets Client Connector apart?

- 01 Security for any access

- 02 Security for any user device

- 03 Context-aware security

- 04 Protection for endpoint data

- 05 User experience monitoring

- 06 Cybercriminal deception

- 07 Operational excellence

Client Connector facilitates seamless zero trust communications to the internet, SaaS, and private apps in the cloud and data center.

Client Connector supports laptops, smartphones, and tablets, and runs on Windows, macOS, Linux, ChromeOS, iOS, and Android.

Client Connector provides granular insights into device security posture, enabling Zscaler to deliver context-based adaptive access control.

Zscaler Endpoint DLP uses Client Connector to stop data leakage via channels like removable storage, printing, and personal cloud storage.

Zscaler Digital Experience (ZDX) uses Client Connector to gain valuable insights into device events and health, accelerating issue resolution.

Zscaler Deception uses Client Connector to deploy decoy passwords, cookies, sessions, and app bookmarks on endpoints, luring attackers.

Zscaler’s unified UI offers comprehensive, cloud-based management (and automation via OneAPI ) that streamlines administration.

## The Zscaler Platform

The cybersecurity platform for the AI Age - built on Zero Trust to protect users, workloads, branches and devices through the world’s largest inline security cloud.

#### Data Security

Secure data everywhere, with comprehensive visibility and controls across all channels.

#### AI Security

Embrace AI with confidence using Zscaler AI Protect, a unified solution to secure AI at scale.

#### Agentic SecOps

Draw on insights from the world’s largest inline security cloud and third-party sources to assess risk and detect and contain breaches.

Benefits

## The benefits of using Client Connector

#### Reduced risk

Leverage zero trust communications, decoys that lure and detect sophisticated threats, and endpoint data loss prevention (DLP).

#### Less cost and complexity

Eliminate disjointed tools and agents for VPN, NAC, and endpoint DLP, as well as standalone SWG , CASB , and ZTNA .

#### Enhanced productivity

Improve user experiences and productivity with direct-to-app connectivity, intelligent traffic routing, and digital experience monitoring.

#### Rapid, frictionless deployment

Quickly deploy the agent via Microsoft Intune or any other MDM; silently auto-install client and TLS/SSL certificates onto devices.

#### Resilient lifecycle management

Gain cloud-managed release updates and rollback support for security and traffic forwarding, ensuring resilient lifecycle management.

#### Strict enforcement

Protect user endpoints before they have connectivity to the Zero Trust Exchange as well as during any events where they might be disconnected.

#### EDR, MDM, and IdP integrations

Inform adaptive access policy with additional device context from CrowdStrike, VMware Carbon Black, Intune, Okta, JAMF, and more.

## Explore more resources

What’s New in Client Connector: Location-Based Policies

Long-Term Support for Zscaler Client Connector

Key Benefits of Zscaler’s Do-It-All Agent, Client Connector

Zscaler Client Connector

How Client Connector and Zscaler Internet Access Secure the Web

How Client Connector and Zscaler Private Access Secure Private Apps

01
                /
                04

