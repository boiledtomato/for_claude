# Zscaler Help — Zscaler Deception (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-31 03:58 UTC
Articles in this file: 25

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-gen-ai-decoys","lastmod":"2026-08-03T00:19Z","nid":"1540649"} -->
## Understanding Gen AI Decoys

- Source: https://help.zscaler.com/deception/understanding-gen-ai-decoys
- Product: Deception
- Path: Deception Help > Deceive  > Gen AI Decoys > Understanding Gen AI Decoys
- Last modified: 2026-08-03T00:19Z
- Summary: Information about Gen AI decoys.

Generative AI (Gen AI) is an emerging attack vector. Organizations are increasingly adopting Gen AI technologies, such as large language models (LLMs) in their products, services, and internal operations. Adversaries target Gen AI infrastructure for data exfiltration, asset compromise, organizational intelligence gathering, etc. Attackers target datasets for data poisoning and data exfiltration. They also use interactive attacks such as prompt injection to target AI systems and extract valuable data.

To address these emerging risks, Zscaler Deception provides high-interaction Gen AI decoys that mimic infrastructure like chatbots, LLM servers, open-source platforms, APIs, etc., to swiftly detect attacks and deflect the attacker from compromising the real assets. By simulating realistic interactions, Gen AI decoys collect critical intelligence on attack patterns and methodologies, and provide valuable insights into malicious tactics, thereby enhancing the organization’s threat intelligence.

Deception supports the following types of Gen AI decoys:

- **Interactive Gen AI Decoys**: Mimics common interactive Gen AI infrastructure such as chatbots, LLM servers, open-source platforms, etc. You can deploy these decoys by configuring the Gen AI service using out-of-the-box AI-based high-interaction or static applications datasets. You can configure these decoys according to your organization’s use case to generate responses based on the attacker’s intent. You can deploy interactive Gen AI decoys on: To learn more, see [Deploying Interactive Gen AI Decoys](https://help.zscaler.com/deception/deploying-interactive-generative-ai-decoys).
  - [Internal Networks via Internal Decoy](https://help.zscaler.com/deception/deploying-interactive-generative-ai-decoys#deception-gen-ai-deploy-internal-network)
  - [Zero Trust Exchange via Zero Trust Network (ZPA) Decoys](https://help.zscaler.com/deception/deploying-interactive-generative-ai-decoys#deception-gen-ai-zpa)
  - [Internet via Threat Intelligence (TI) Decoys](https://help.zscaler.com/deception/deploying-interactive-generative-ai-decoys#deception-gen-ai-deploy-ti)
- **File-Based Gen AI Decoys**: Mimics resources that are used to set up local LLMs. You can create these decoys by configuring the Gen AI file datasets provided by Deception using one of the following assets: To learn more, see [Deploying File-Based Gen AI Decoys](https://help.zscaler.com/deception/deploying-file-based-generative-ai-decoys).
  - Endpoints via [landmine policies](https://help.zscaler.com/deception/about-policies)
  - Azure Cloud via [storage account container decoy](https://help.zscaler.com/deception/creating-storage-account-container-decoy-azure) or [storage account file share decoy](https://help.zscaler.com/deception/creating-storage-account-file-share-decoy-azure)
  - Amazon Web Services (AWS) Cloud via [S3 decoys](https://help.zscaler.com/deception/creating-s3-decoy-aws)
  - [Network decoys](https://help.zscaler.com/deception/about-network-decoys) via [FTP](https://help.zscaler.com/deception/configuring-services-network-decoy#configuring-ftp-service) or [Share](https://help.zscaler.com/deception/configuring-services-network-decoy#configuring-shares-service) service

## Gen AI Network Personality

Deception provides a [Gen AI decoy personality](https://help.zscaler.com/deception/about-network-decoy-personalities) that serves as templates to deploy Gen AI decoys via network decoys. You can use this personality when configuring [network decoys manually](https://help.zscaler.com/deception/about-network-decoys), or use them in a [deception strategy](https://help.zscaler.com/deception/about-deploy-strategy) to create decoys. To learn more, see [About Network Decoy Personalities](https://help.zscaler.com/deception/about-network-decoy-personalities).

See image.

The **GenAI** icon is used across the Zscaler Deception Admin Portal to indicate Gen AI use cases and capabilities.

## ThreatParse Details and Event Logs

When an adversary compromises an endpoint and attempts a data exfiltration technique to find credentials for an internally hosted LLM application (which is a Gen AI decoy), Deception detects the attack and generates event logs. It also captures the prompt and automatically categorizes it as malicious, etc. for further investigation using the Gen AI Malicious Input Prompt ThreatParse rule. In the Gen AI Malicious Input Prompt ThreatParse rule, the classification of whether the prompt is malicious and the categorization of the prompt are determined by enriching data using Deception AI. Hence, the values for the **gen ai malicious** and **gen ai malicious category** fields might be inaccurate. Verify the details for accuracy or completeness before making any decisions.

See image.

After the threat is detected, the configured [orchestration rules](https://help.zscaler.com/deception/about-orchestration-rules) automatically contain the attack and block the compromised user from accessing any private applications in the environment.

To learn more, see [Viewing ThreatParse Details](https://help.zscaler.com/deception/viewing-threatparse-details) and [About Event Logs](https://help.zscaler.com/deception/about-event-logs).

The responses or content generated by the Deception AI is for informational purposes only. The content is prone to inaccuracy and AI hallucinations. Verify the details for accuracy or completeness before making any decisions.

[Image: View Gen AI network decoy personality]

[Image: View ThreatParse details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-keyword-datasets","lastmod":"2026-08-03T00:19Z","nid":"1540576"} -->
## Understanding Keyword Datasets

- Source: https://help.zscaler.com/deception/understanding-keyword-datasets
- Product: Deception
- Path: Deception Help > Miragemaker > Keyword Datasets > Understanding Keyword Datasets
- Last modified: 2026-08-03T00:19Z
- Summary: Understanding the keywords datasets

Zscaler Deception allows you to configure a set of keywords that can be used to autogenerate recommendations for decoy parameters, such as hostnames, file and folder names, network decoy names, etc. Zscaler uses a proprietary algorithm to randomly generate recommendations for decoy parameters.

When keywords are configured, Zscaler generates recommendations that include the keywords in addition to randomly generated recommendations. When generating keyword-based recommendations, the keyword is part of the generated texts and is not exclusively used to avoid conflicts. For example, if "admin" is a configured keyword, keyword-based recommendations can be admin-pc, adminlog, etc., and cannot be "admin" alone. Also, when multiple keywords are configured, keywords are chosen randomly to generate a recommendation.

Only one keyword is used for each keyword-based recommendation.

To learn how to configure keyword datasets, see [Configuring Keyword Datasets](https://help.zscaler.com/deception/configuring-keyword-datasets).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-landmine-decoys","lastmod":"2026-08-10T19:52Z","nid":"1540401"} -->
## Understanding Landmine Decoys

- Source: https://help.zscaler.com/deception/understanding-landmine-decoys
- Product: Deception
- Path: Deception Help > Deceive  > Landmine Decoys > Understanding Landmine Decoys
- Last modified: 2026-08-10T19:52Z
- Summary: Information about the Landmine Decoys page in the Zscaler Deception Admin Portal.

[Watch a video on Creating Landmine Decoys and Policies](https://fast.wistia.net/embed/iframe/c3w5mose6l).

Landmine decoys look like valuable assets ripe for exfiltration. They can be decoy files, credentials, and application lures on endpoints. When adversaries access these decoys, Zscaler Deception sends alerts about the adversary's presence. You can use the [Deception dashboard](https://help.zscaler.com/deception/viewing-investigate-dashboard) to analyze an adversary's behavior, hunt for threats across the network, or block access.

Landmine decoys can detect the following types of attacks:

- Defense Evasion
  - T1562.001: Impair defenses – Disable or modify tools
- Impact
  - T1489: Service stop
  - T1485: Data destruction
- Credential Access
  - T1552.001: Unsecured credential – Credentials in files
  - T1557.001: Man-in-the-Middle – Link-local multicast name resolution (LLMNR) poisoning and server message block (SMB) relay
- Collection
  - T1005: Data from local system

Landmine decoys provide the following benefits and enable you to:

- Protect endpoints against ransomware attacks such as attempts to encrypt files and credentials stealing at every stage of the kill chain.
- Intercept adversaries who have bypassed traditional perimeter-based defenses and limit their ability to find targets or move laterally.

You can create landmine decoys based on policies. A landmine agent fetches these policies and verifies if they apply to an endpoint, and then deploys the decoys. You can create a base policy that enables a simple detection mechanism or an advanced base policy that enables advanced detection mechanisms to detect adversarial techniques.

To learn more, see the following articles:

- [About Landmine Policies](https://help.zscaler.com/deception/about-policies)
- [About Landmine Agent and Agentless](https://help.zscaler.com/deception/about-landmine-agent-agentless)
- [About Agent Configuration](https://help.zscaler.com/deception/about-landmine-settings)
- [About Agent Update Groups](https://help.zscaler.com/deception/about-agent-update-groups)
- [About Safe Processes](https://help.zscaler.com/deception/about-safe-processes)
- [Deploying Endpoint Deception with Zscaler Client Connector for Windows](https://help.zscaler.com/deception/deploying-endpoint-deception-zscaler-client-connector-windows)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-miragemaker-module","lastmod":"2026-08-03T00:19Z","nid":"1540620"} -->
## Understanding the Miragemaker Module

- Source: https://help.zscaler.com/deception/understanding-miragemaker-module
- Product: Deception
- Path: Deception Help > Miragemaker > Understanding the Miragemaker Module
- Last modified: 2026-08-03T00:19Z
- Summary: Understanding the capabilities of the Miragemaker module.

The Miragemaker module in Zscaler Deception allows you to configure and manage various ready-to-use resources that are typically used to build and deploy different types of decoys. By default, the Miragemaker module includes preconfigured resources such as datasets, ThreatParse rules, templates, strategies, decoy personalities, banners, and tags. You can also create custom resources based on your business requirements. The various resources that you can configure and manage in the Miragemaker module are as follows:

- Application Datasets
- SCADA/IoT Datasets
- Keyword Datasets
- Custom Service Datasets
- ThreatParse Rules
- File Datasets and Templates
- Strategy Builder
- Banners
- Tags

Application datasets are resources used to build and deploy decoy web applications. You can create static, dynamic, or high-interaction web application decoys by deploying the datasets using [Threat Intelligence (TI) decoys](https://help.zscaler.com/deception/about-threat-intelligence-decoys) or [web services on network decoys](https://help.zscaler.com/deception/configuring-services-network-decoy#configuring-web-service). The following list of application datasets in Miragemaker allows you to create different types of application decoys:

- **Static Application Datasets**: Used to create the front ends of decoy web applications.
- **Vulnerable Application Datasets**: Used to create specific dynamic interactions for decoy web applications.
- **Dynamic Application Datasets**: Used to create dynamic applications by combining appropriate front ends (static application datasets) and dynamic interactions (vulnerable application datasets) for decoy web applications.
- **High-Interaction Container Datasets**: Used to create fully interactive web application decoys based on docker images of real applications.

To learn more about application decoys, see [Understanding Application Datasets](https://help.zscaler.com/deception/understanding-application-datasets).

SCADA/IoT datasets are resources used to build [network decoys](https://help.zscaler.com/deception/configuring-services-network-decoy#configuring-scada-iot-service) that mimic Supervisory Control and Data Acquisition (SCADA) and Internet of Things (IoT) devices used in industrial processes, such as sensors, actuators, and other logical controllers. To learn more, see [About SCADA/IoT Datasets](https://help.zscaler.com/deception/about-scada-iot-datasets).

Keyword datasets are a set of keywords that can be used to autogenerate recommendations for decoy parameters, such as hostnames, file and folder names, network decoy names, etc. Zscaler uses a proprietary algorithm to randomly generate recommendations for decoy parameters. To learn more, see [Understanding Keyword Datasets](https://help.zscaler.com/deception/understanding-keyword-datasets).

Custom service datasets are resources that are used to create [custom network services](https://help.zscaler.com/deception/configuring-services-network-decoy#configuring-custom-service) that include requests and responses. The custom services are deployed using [network decoys](https://help.zscaler.com/deception/about-network-decoys). To learn more, see [About Custom Service Datasets](https://help.zscaler.com/deception/about-custom-service-datasets).

ThreatParse rules are conditions that are used to parse details of an attack event and translate them into plain English using natural language reconstruction. Deception uses the rules to provide information about an attack event on the [Deception Dashboard](https://help.zscaler.com/deception/viewing-and-managing-zscaler-deception-dashboard). To learn more, see [About ThreatParse Rules](https://help.zscaler.com/deception/about-threatparse-rules).

File datasets and templates are resources that allow you to create decoy files and folders that can be deployed using [network decoy](https://help.zscaler.com/deception/about-network-decoys)s or landmine decoys. To learn more, see [About File Datasets](https://help.zscaler.com/deception/about-file-datasets) and [About File Templates](https://help.zscaler.com/deception/about-file-templates).

Strategy Builder allows you to [deploy](https://help.zscaler.com/deception/about-deploy-strategy) different types of decoys in your environment with a single click using strategies. The strategies are built using decoy personalities that serve as templates for different types of decoys.

- **Deception Strategy**: A mechanism that combines different decoy personalities to configure and deploy different types of decoys with a single click.
- **Network Decoy Personalities**: A template used to create [network decoys](https://help.zscaler.com/deception/about-network-decoys) either manually or via deception strategies.
- **Threat Intelligence Decoy Personalities**: A template used to create [TI decoys](https://help.zscaler.com/deception/about-threat-intelligence-decoys) via deception strategies.
- **Landmine Decoy Personalities**: A template used to create [landmine decoys](https://help.zscaler.com/deception/about-landmine-decoys) via deception strategies.
- **Active Directory Decoy Personalities**: A template used to create [AD decoys](https://help.zscaler.com/deception/configuring-active-directory-decoy-personality) via deception strategies.

To learn more, see [Understanding Strategy Builder](https://help.zscaler.com/deception/understanding-strategy-builder).

Banners are metadata about services running on a server. These banners are used in [network decoys](https://help.zscaler.com/deception/about-network-decoys) configured with [FTP services](https://help.zscaler.com/deception/configuring-services-network-decoy#configuring-ftp-service) or [web services](https://help.zscaler.com/deception/configuring-services-network-decoy#configuring-web-service) to mimic legitimate services. To learn more, see [Configuring and Managing FTP Banners](https://help.zscaler.com/deception/configuring-and-managing-ftp-banners) and[Configuring and Managing Web Server Banners](https://help.zscaler.com/deception/configuring-and-managing-web-server-banners).

Tags are used to group various resources across Deception. For example, you can associate tags with personalities and reference them using their tags in [deception strategies](https://help.zscaler.com/deception/about-deception-strategy). To learn more, see [Configuring and Managing Tags](https://help.zscaler.com/deception/configuring-and-managing-tags).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-mitm-detection","lastmod":"2026-08-03T00:19Z","nid":"1540623"} -->
## Understanding MITM Detection

- Source: https://help.zscaler.com/deception/understanding-mitm-detection
- Product: Deception
- Path: Deception Help > Deceive  > MITM Detection > Understanding MITM Detection
- Last modified: 2026-08-03T00:19Z
- Summary: Understanding MITM detection.

Zscaler Deception can detect and alert certain types of Man-in-the-Middle (MITM) attacks, such as Link-Local Multicast Name Resolution (LLMNR) poisoning, NetBIOS Name Service (NBT-NS) poisoning, and multicast DNS (mDNS) poisoning. With these types of MITM attacks, the adversaries listen to name resolution requests from devices and send a malicious response. To detect these MITM attacks, Deception enables you to configure your [internal network decoys](https://help.zscaler.com/deception/creating-internal-network-decoy) to broadcast decoy name resolution requests across the subnet at specified intervals. If an adversary responds to the request using tools like Responder or Inveigh, then the activity is logged as an MITM attack. You can view and analyze the attack details from the [Deception Dashboard](https://help.zscaler.com/deception/viewing-investigate-dashboard).

To learn how to configure MITM detection, see [Configuring MITM Attack Detection](https://help.zscaler.com/deception/configuring-mitm-attack-detection).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-quality-service-qos-events","lastmod":"2025-12-22T06:06Z","nid":"1539455"} -->
## Understanding Quality of Service Events

- Source: https://help.zscaler.com/deception/understanding-quality-service-qos-events
- Product: Deception
- Path: Deception Help > Getting Started > Admin Portal > Understanding Quality of Service Events
- Last modified: 2025-12-22T06:06Z
- Summary: Information on QoS events in the Zscaler Deception Admin Portal.

Zscaler Deception generates Quality of Service (QoS) events to maintain platform integrity and prevent malicious actors from overwhelming resources. This mechanism helps preserve resource availability for legitimate detections and analytics while throttling abusive or automated tools that exceed reasonable event generation limits.

## QoS Events for Threat Intelligence (TI) Decoys

The QoS mechanism functions as a lightweight, automated DDoS protection layer for TI decoys. It prevents malicious or misconfigured sources from saturating the logging capabilities with repetitive probes, connection attempts, or payloads. By capping the event rate per attacker, Deception ensures:

- Legitimate threat telemetry remains accurate and actionable.
- TI decoy integrity and uptime are maintained.
- Resources are fairly distributed across multiple attackers and decoys.
- Logs capture only high-value events, improving signal-to-noise ratio and analytical efficiency.

When an attacker or automated tool repeatedly triggers a TI decoy, Deception monitors the event rate and automatically applies QoS controls if the threshold is exceeded. The QoS event is generated when the threshold of 10,000 events exceeds within 60 minutes. This is treated as potential DDoS-like behavior, and Deception applies a cool-down period of two hours. During the cool-down period, no new QoS event is generated for the same attacker. Normal detection and logging resume automatically after the attacker's event rate falls below the threshold.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-radius-decoys","lastmod":"2026-08-03T00:19Z","nid":"1540663"} -->
## Understanding RADIUS Decoys

- Source: https://help.zscaler.com/deception/understanding-radius-decoys
- Product: Deception
- Path: Deception Help > Deceive  > RADIUS Decoys > Understanding RADIUS Decoys
- Last modified: 2026-08-03T00:19Z
- Summary: Information about RADIUS decoys.

Organizations use the Remote Authentication Dial-in User Service (RADIUS) protocol for centralized authentication, authorization, and accounting (AAA) to securely manage users who access a remote network.

The RADIUS protocol encrypts only the password within authentication requests, leaving the rest of the payload unencrypted. This limitation makes RADIUS servers vulnerable to packet forgery and man-in-the-middle (MITM) attacks if additional security layers, such as TLS or IPSec, are not integrated. Adversaries use the Network Mapper (Nmap) tool to scan the network and enumerate the server username, password, and secret.

To address these risks, Zscaler Deception provides high-interaction RADIUS decoys that mimic RADIUS servers. These decoys respond to authentication attempts, lure the attackers, and divert any threat targeting your RADIUS network.

You can [deploy an interactive RADIUS decoy](https://help.zscaler.com/deception/deploying-radius-decoy) on an internal network via Internal decoys. After deploying, you can [test and verify that the decoy](https://help.zscaler.com/deception/testing-radius-decoys) is working properly.

## ThreatParse Details and Event Logs

When an adversary attempts to authenticate the RADIUS decoy, Deception generates event logs and enriches them using the RADIUS Authentication ThreatParse rule. The ThreatParse rule classifies the interaction and assigns a score based on the status of the authentication attempt. A successful authentication attempt is assigned a higher score than a failed or unknown attempt.

See image.

[Image: ThreatParse rules score]

Event logs are generated for each authentication attempt, and the associated details such as attacker ID, port, username, etc. are extracted. These details are displayed for further investigation.

See image.

After the threat is detected, the configured [orchestration rules](https://help.zscaler.com/deception/about-orchestration-rules) automatically contain the attack and block the compromised user from accessing any private applications in the environment.

To learn more, see [Viewing ThreatParse Details](https://help.zscaler.com/deception/viewing-threatparse-details) and [Viewing and Managing](https://help.zscaler.com/deception/viewing-managing-event-logs)[Event Logs](https://help.zscaler.com/deception/viewing-managing-event-logs).

[Image: Radius decoy event logs]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-strategy-builder","lastmod":"2026-08-03T00:19Z","nid":"1540622"} -->
## Understanding Strategy Builder

- Source: https://help.zscaler.com/deception/understanding-strategy-builder
- Product: Deception
- Path: Deception Help > Miragemaker > Strategy Builder > Understanding Strategy Builder
- Last modified: 2026-08-03T00:19Z
- Summary: Understanding different features in strategy builder

Strategy Builder allows you to deploy different types of decoys in your environment with a single click using strategies. The Strategy Builder includes the following components:

- **Deception Strategy**: A mechanism that combines different decoy personalities to configure and deploy different types of decoys with a single click. The strategies are built using decoy personalities that serve as templates for different types of decoys, and are deployed using [deploy strategies](https://help.zscaler.com/deception/about-deploy-strategy). To learn more, see [About Deception Strategy](https://help.zscaler.com/deception/about-deception-strategy).
- **Network Decoy Personalities**: Templates that can be used to create [network decoys](https://help.zscaler.com/deception/about-network-decoys). You can use these personalities when configuring [network decoys manually](https://help.zscaler.com/deception/about-network-decoys), or use them in a [deception strategy](https://help.zscaler.com/deception/about-deploy-strategy) to create decoys. To learn more, see [About Network Decoy Personalities](https://help.zscaler.com/deception/about-network-decoy-personalities).
- **Threat Intelligence Decoy Personalities**: Templates that can be used to create [Threat Intelligence (TI) decoys](https://help.zscaler.com/deception/about-threat-intelligence-decoys) via [deception strategies](https://help.zscaler.com/deception/about-deception-strategy). To learn more, see [About Threat Intelligence (TI)](https://help.zscaler.com/deception/about-threat-intelligence-decoy-personalities).
- **Landmine Decoy Personalities**: Templates that can be used to create [landmine policies](https://help.zscaler.com/deception/about-landmine-decoys) via [deception strategies](https://help.zscaler.com/deception/about-deploy-strategy). To learn more, see [About Landmine Decoy Personalities](https://help.zscaler.com/deception/about-landmine-decoy-personalities).
- **Active Directory Decoy Personalities**: Templates that can be used to create [AD decoys](https://help.zscaler.com/deception/about-active-directory-decoys) via [deception strategies](https://help.zscaler.com/deception/about-deception-strategy). To learn more, see [About Active Directory Decoy Personalities](https://help.zscaler.com/deception/about-active-directory-decoy-personalities).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/understanding-zscaler-deception-architecture","lastmod":"2026-08-19T22:40Z","nid":"1540336"} -->
## Understanding the Zscaler Deception Architecture

- Source: https://help.zscaler.com/deception/understanding-zscaler-deception-architecture
- Product: Deception
- Path: Deception Help > Getting Started > Understanding the Zscaler Deception Architecture
- Last modified: 2026-08-19T22:40Z
- Summary: Information on the Zscaler Deception architecture and key components.

Zscaler Deception is a threat-detection solution built on the Zero Trust architecture, which is designed for seamless integration with the Zscaler service and other parts of your security environment. Deception is cloud-delivered and scalable, and requires minimal on-premises computing.

## Deception Standalone

The Deception standalone architecture includes the following key components:

[Image: Zscaler Deception standalone architecture]

- Zscaler Deception Admin Portal: A cloud-hosted user interface (UI) hosted by Zscaler. It serves as the central point of management and analysis for the Deception service. Decoy Connector and software agents connect to it. You can use the portal to create and deploy decoys across assets in your environment. The portal also provides a powerful dashboard for deep analysis and orchestration of events.
- Decoy Connector: Lightweight virtual appliances that are hosted in your environment and can create decoy applications within multiple trunked virtual local area networks (VLANs) of your network. Decoy Connectors can be used as a secure relay for integration between the Deception Admin Portal and applications, such as Active Directory (AD), security information and event management (SIEM), firewalls, etc. in your network.
- Landmine: Software agent installed on endpoints, such as desktops or laptops on your network. These agents deploy decoy credentials, files, processes, and lures to other decoys at your endpoints.
- Cloud Decoys: Configure and deploy decoys designed to resemble legitimate resources and assets within public cloud platforms. Depending on the type and configuration of the decoy, adversaries can attempt to access, interact with, or carry out malicious operations against these cloud decoys. Such actions are classified as attacks and automatically logged. You can view and analyze the details of these attacks through the Deception dashboard.

When an adversary probes for vulnerabilities and breaks into your network, the decoys look like real assets. The moment the adversary interacts with a decoy, the decoy detects the threat and relays all the information to the Deception Admin Portal.

The Deception Admin Portal uses the ThreatParse engine, which is a proprietary technology built into the Deception dashboard. ThreatParse conducts natural language reconstruction of attacks, summarizes log information, and translates attacks into plain English that is easy to understand. ThreatParse also links this information to the MITRE ATT&CK framework and includes a risk score assigned to adversaries that enables security teams to prioritize the most pressing threats first.

Based on the type and severity of the actions that the adversary performs, the Deception Admin Portal performs the following actions:

- Automates actions using built-in integrations.
- Sends high-fidelity alerts to specific personnel via phone, text messages, and emails.
- Forwards the events to other security tools, such as a firewall or an EDR to automatically isolate the affected asset.

## Deception Integrated with ZPA

Deception integrated with Zscaler Private Access (ZPA) leverages the Zero Trust Network Access (ZTNA) solution that allows you to create decoy applications that look like they exist within your organization's environment, but leverage ZPA's technology to redirect adversaries to the Deception cloud.

Decoys that make use of ZPA provide better visibility, intelligence, and containment capabilities than their non-ZPA counterparts.

[Image: Zscaler Deception integrated with ZPA architecture]

Deception uses the ZPA service to deploy decoys in a dedicated Deception cloud. The Landmine agent deploys decoy credentials, files, processes, and lures to other decoys at your endpoints. These lures direct adversaries away from your legitimate assets and towards decoy applications.

You don't have to install any additional Decoy Connectors or make any changes to your network configurations to add deception to your environment.

When an adversary breaks into your network and interacts with a decoy, Deception leverages Zero Trust access policies to block traffic from the adversary to the legitimate applications and contains the adversary to one system. Deception builds a complete picture of the adversary using the user and device identity information from the ZPA service.

To learn more about ZPA architecture, see [Understanding the ZPA Cloud Architecture](https://help.zscaler.com/zpa/understanding-zpa-cloud-architecture).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/uninstalling-or-deleting-landmine-agents","lastmod":"2024-03-15T07:06Z","nid":"1539178"} -->
## Uninstalling or Deleting Landmine Agents

- Source: https://help.zscaler.com/deception/uninstalling-or-deleting-landmine-agents
- Product: Deception
- Path: Deception Help > Settings > Endpoint Settings > Agents > Agent Management > Uninstalling or Deleting Landmine Agents
- Last modified: 2024-03-15T07:06Z
- Summary: How to uninstall or delete landmine agents in the Zscaler Deception Admin Portal.

This article provides instructions for uninstalling or deleting landmine agents.

## Uninstalling Landmine Agents from the Zscaler Deception Admin Portal

You can uninstall a single agent, multiple agents, or all agents from the Deception Admin Portal.

To uninstall agents:

1. Go to **Settings**> **Endpoint Settings**> **Agents**.
2. In the **Agents** table, choose one of the following options:
  - Select the agents that you want to uninstall, and then click **Actions** > **Uninstall Selected** to uninstall them. See image.
  - Click **Actions** > **Uninstall All Agents** to uninstall all agents. See image.
  - Click the **Uninstall**icon under the **Actions**column to uninstall standalone landmine agents individually. See image.
3. In the **Uninstall Agent** window, choose an option:
  - **Uninstall**: The agent removes the policies from the endpoint, and then it uninstalls itself. The agent tries to remove the policies for three days before uninstalling itself.
  - **Force Uninstall**: The agent uninstalls the policies from the endpoint on a best-effort basis, and then it uninstalls itself. See image. A confirmation message appears indicating that the uninstallation request is successful. After the agents are uninstalled on the requested system, the agent's entry is deleted from the **Agents** table.

## Uninstalling Agents on Windows

Occasionally, uninstallation of agents from the Deception Admin Portal can fail when you try to remotely uninstall agents. It can also fail due to network connectivity issues. In such scenarios, you can uninstall the agents using CLI.

To uninstall agents on Windows:

1. Click **Start**.
2. In the Start Search box, enter `cmd`, and then press `CTRL+SHIFT+ENTER`.
3. When the **User Account Control (UAC)** dialog box appears, click **Yes**.
4. In the command prompt, enter the following command to uninstall the agent: msiexec /qn /x {94e4ac3b-2519-46eb-97bd-d6be9b6c8f55}The landmine agent is uninstalled. All the services and program files related to the agent are removed from the system. You can verify if the files have been removed from the `C:\ProgramData` folder. The uninstaller attempts to remove the system entry from the Deception Admin Portal. If the agent fails to remove the system entry for 5 consecutive times, it continues with the uninstallation. If this is unsuccessful, the entry must be deleted manually.

## Deleting Landmine Agents

To delete a landmine agent:

1. Go to **Settings**> **Endpoint Settings**> **Agents**.
2. In the **Agents** table, choose one of the following options:
  - Select the agents that you want to delete, and then click **Actions** > **Delete Selected** to delete them. See image.
  - Click the **Delete**icon under the **Actions**column to delete standalone landmine agents individually. See image.
3. In the confirmation window, click **OK**. See image.

- When you delete an agent, its entry is removed from the Deception Admin Portal only. The agent is not uninstalled from the system.
- Landmine agents cannot be deleted if their **Last Seen** value is under 7 days.

[Image: Uninstall multiple landmine agents]

[Image: Uninstall all landmine agents]

[Image: Uninstall or force uninstall a landmine agent]

[Image: Delete landmine agents]

[Image: Delete landmine agents]

[Image: A screenshot capturing the option to delete each standalone landmine agent]

[Image: A screenshot capturing the option to uninstall standalone landmine agents]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/using-network-decoy-personalities-and-services","lastmod":"2026-08-03T00:19Z","nid":"1540350"} -->
## Using Network Decoy Personalities and Services

- Source: https://help.zscaler.com/deception/using-network-decoy-personalities-and-services
- Product: Deception
- Path: Deception Help > Deceive  > Network Decoys > Using Network Decoy Personalities and Services
- Last modified: 2026-08-03T00:19Z
- Summary: Information about using Zscaler Deception network decoy personalities and services.

Zscaler Deception provides a set of ready-to-use templates to create network decoys (Internal or Zero Trust Network). These templates are known as personalities and are built based on different types of servers, applications, etc. You can use these personalities when configuring [network decoys manually](https://help.zscaler.com/deception/about-network-decoys), or deploying [deception strategy](https://help.zscaler.com/deception/about-deploy-strategy) to create decoys. Zscaler Deception provides a list of preconfigured network decoy personalities for various business use cases. You can also create custom personalities based on your business requirements. To learn more, see [About Network Decoy Personalities](https://help.zscaler.com/deception/about-network-decoy-personalities).

When you select a personality while creating a network decoy, the configurations, such as hostname or FQDN and MAC address, are automatically configured along with the default services that are tagged to the personality.

See image.

By default, services are enabled based on the network decoy personality that you select when creating a network decoy. The services can be tailored to specific environments and use cases. You can enable or disable services or change the content they serve per your requirements. To learn more, see [Configuring Services for a Network Decoy.](https://help.zscaler.com/deception/configuring-services-network-decoy)

See image.

[Image: Configure network personality in network decoys]

[Image: Configure services for network decoys]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/validating-strategy","lastmod":"2025-05-21T20:32Z","nid":"1539319"} -->
## Validating a Strategy

- Source: https://help.zscaler.com/deception/validating-strategy
- Product: Deception
- Path: Deception Help > Miragemaker > Strategy Builder > Deception Strategy > Validating a Strategy
- Last modified: 2025-05-21T20:32Z
- Summary: How to validate a Deception strategy.

You can [create a strategy](https://help.zscaler.com/deception/creating-deception-strategy) with landmine policies and personalities. These landmine personalities have browser, session, and memory credential lures that place decoys such as Network, Active Directory (AD), Threat Intelligence (TI) as breadcrumbs on the endpoint to lure adversaries. You can validate the strategy to ensure that the corresponding Network, AD, and TI decoys are available for the configured landmine lures.

To validate a Deception Strategy:

1. Go to **Miragemaker**>**Strategy Builder**>**Deception Strategy**.
2. Click the **Validate** icon for the strategy that you want to validate.

See image.

1. The strategy is validated. You can see the total percentage of enabled landmine lures with available Network, AD, and TI decoys.

See image.

1. Click **Close**.

[Image: The Deception Strategy page with an annotation around the Validate button]

[Image: Decoy strategies sucessfully validated on the Strategy Validation page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-active-directory-decoy-computers","lastmod":"2026-08-03T00:19Z","nid":"1540656"} -->
## Viewing Active Directory Decoy Computers

- Source: https://help.zscaler.com/deception/viewing-active-directory-decoy-computers
- Product: Deception
- Path: Deception Help > Deceive  > Active Directory Decoys > Viewing Active Directory Decoy Computers
- Last modified: 2026-08-03T00:19Z
- Summary: How to view the AD decoy computer details.

You can [add an Internal network decoy to an Active Directory (AD) and AD DNS as a decoy computer object](https://help.zscaler.com/deception/adding-network-decoy-active-directory) to make it look like a legitimate domain-joined system. AD decoy computers detect AD enumeration activities and AD-related exploits.

After you add AD decoy computers, you can view them on the Decoy Computers page. Additionally, you can view the decoy deployment status and the last updated timestamp of the decoy.

To view AD decoy computers:

1. Go to **Deceive** > **Active Directory Decoys** > **Decoy Computers**.
2. Select an AD domain from the **Domain** drop-down menu. The AD decoy computers are displayed. For each deployed decoy computer, you can view:
  - **Name**: The name of the AD decoy computer. The following icons indicate the deployment status:
    - : Decoy successfully deployed.
    - : Decoy updated, but deployment is pending.
    - : Decoy deployment is pending.
    - : Decoy deployment failed.
  - **OU**: The organization unit (OU) path.
  - **Operating System**: The operating system (OS) name. The OS details appear in the properties of the decoy computer in the AD domain.
  - **Description**: The description of the AD decoy computer.
  - **IP Address**: The IP address of the AD decoy computer.
  - **Last Updated**: The timestamp when the AD decoy computer was last updated (password resets, login actions, etc.).

[Image: View AD decoy computer details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-and-managing-decoy-deployment","lastmod":"2025-12-30T20:48Z","nid":"1539463"} -->
## Viewing and Managing Decoy Deployment

- Source: https://help.zscaler.com/deception/viewing-and-managing-decoy-deployment
- Product: Deception
- Path: Deception Help > Deceive  > Viewing and Managing Decoy Deployment
- Last modified: 2025-12-30T20:48Z
- Summary: How to view and manage decoy deployment.

The Deceive Summary page (Deceive > Summary) allows you to view and manage decoy deployment. On the Summary page, you can do the following:

- View the deployment health status.
- Start or stop decoys.
- View and analyze deployment logs.

You can view the deployment health status of all the Decoy Connectors and service back ends.

To view the deployment health status:

1. Go to **Deceive** > **Summary**.
2. Click a deployment health status: See image. The deployment health status of the Decoy Connectors and service back ends appears. See image.
  - **Ready for deployment**: All the Decoy Connectors and service back ends are up and running.
  - **Action Required**: Some or all Decoy Connectors and service back ends are down and need your attention.
3. Click **Done**.

Before you configure a network (Internal and Zero Trust Network) or Threat Intelligence (TI) decoy, you must stop all active decoys, make the required changes, and then restart the decoys for the configurations to take effect.

Starting and stopping decoys impacts network and TI decoys only. Other decoys such as landmine, AD, etc. are not impacted.

## Stopping Decoys

To stop a decoy:

1. Go to **Deceive** > **Summary**.
2. If there are any decoys that are currently active, click **Stop Decoys**. See image.
3. In the confirmation window, click **OK**.

## Starting Decoys

To start a decoy:

1. Go to **Deceive** > **Summary**.
2. Click **Start Decoys**. See image.
3. In the confirmation window, click **OK**.

You can view and analyze decoy deployment logs to troubleshoot deployment issues. You can use the logs to debug the components that caused an error during the decoy deployment.

To view the decoy deployment logs:

1. Go to **Deceive** > **Summary**.
2. Click **View Deployment Logs**. See image. The **Deployment Logs** window appears. The red text indicates deployment issues. See image.

Click **Show Only Failed**to view only the failed deployment log details.

[Image: View deployment health status]

[Image: Ready for deployment status]

[Image: Stop decoys]

[Image: Start decoys]

[Image: View Deployment Logs button]

[Image: View deployment logs]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-attack-chronology-details","lastmod":"2026-08-03T00:19Z","nid":"1540438"} -->
## Viewing Attack Chronology Details

- Source: https://help.zscaler.com/deception/viewing-attack-chronology-details
- Product: Deception
- Path: Deception Help > Investigate  > Extended Details > Viewing Attack Chronology Details
- Last modified: 2026-08-03T00:19Z
- Summary: How to view Chronology details in the Zscaler Deception Admin Portal

The Chronology page provides a temporal overview and heatmap of the attacker's activities. This information helps you to identify periods of activity with unusual volumes of alerts. The Chronology page provides graphical representations of the number of events generated in Zscaler Deception over time. This helps you gain insights into attackers' activity using the weekly heatmap generated based on the volume of activity during the time of day.

To learn more, see [Viewing Extended Details](https://help.zscaler.com/deception/viewing-extended-details).

See image.

The Chronology page includes:

- Events over time graph: When an attacker infiltrates your network and interacts with the decoys, Deception generates events. This graph shows these events over a specified period of time.
- Activity over a week graph: This graph shows a heatmap that helps you to analyze the attacker's activity pattern for a weekly duration. A brighter spot on the graph represents a high volume of activity.

See image.

[Image: Accessing chronology details]

[Image: Chronology details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-blocked-identities","lastmod":"2026-08-03T00:19Z","nid":"1540472"} -->
## Viewing the Blocked Identities

- Source: https://help.zscaler.com/deception/viewing-blocked-identities
- Product: Deception
- Path: Deception Help > Orchestrate  > Containment Integrations > Viewing the Blocked Identities
- Last modified: 2026-08-03T00:19Z
- Summary: How to view and manage the contained attacker IP address details.

You can view the following details of contained attackers or endpoints depending on the containment integration:

- The details of blocked attackers or endpoints, such as IP address, hostname, or username.
- The timestamp when the attacker's identity was added to the containment list.
- The expiration date of the containment.
- Additional details such as the device ID and machine ID, depending on the containment integration.

With CrowdStrike integration, you can also view IOC hashes, IOC IP addresses, and IOA process trees that are shared by Zscaler Deception. To learn more about CrowdStrike integration, see [Containment Configuration Guide for CrowdStrike](https://help.zscaler.com/deception/zscaler-deception-and-crowdstrike-deployment-guide).

To view and delete the details of the containment:

1. Go to **Orchestrate** > **Containment**. See image.
2. Locate the containment integration and follow these steps based on the third-party application:
  - For third-party integrations other than CrowdStrike:
    - To view containment details, click the number under the **Blocked Identities** column. A window appears listing the contained attacker's IP details. See image.
    - To remove an entry from the list, click the **Delete**icon in the **Actions**column, and confirm your action. See image.
  - For CrowdStrike integration:
    - To view containment or shared intelligence details, click one of the following options: See image.
      - **Contained IP**:Shows the list of contained IP addresses.
      - **IOC Hash**: Shows the list of file hashes that are shared with CrowdStrike as indicators of compromise.
      - **IOC IP**: Shows the list of IP addresses that are shared with CrowdStrike as indicators of compromise.
      - **IOA Process Tree**: Shows the list of process trees that are shared with CrowdStrike as indicators of attack.
    - To lift containment and remove the entry from one of the lists, click the **Delete**icon in the **Actions**column, and confirm your action. See image. When the containment is lifted directly from the CrowdStrike Falcon console, the corresponding entry in the Zscaler Deception Admin Portal is also removed automatically via a scheduled job that typically runs every hour.

[Image: Attacker IP count on the Containment page]

[Image: View attacker IP details]

[Image: Delete an attacker IP]

[Image: View CrowdStrike Containment Details]

[Image: Delete a CrowdStrike IOC IP Entry]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-extended-details","lastmod":"2026-08-03T00:19Z","nid":"1540442"} -->
## Viewing Extended Details

- Source: https://help.zscaler.com/deception/viewing-extended-details
- Product: Deception
- Path: Deception Help > Investigate  > Extended Details > Viewing Extended Details
- Last modified: 2026-08-03T00:19Z
- Summary: How to view additional details of attacker events in the Zscaler Deception Admin Portal.

Attacker activities are recorded as events and displayed as a graph on the Investigate page. You can evaluate the attacker's actions and how they interact with Zscaler Deception elements (decoys, decoy connectors, etc.). You can also see additional details of these activities from ThreatParse along with timelines and network maps to know when and how the attack happened, view the event logs, and download evidence like PCAP files, RDP session recordings, and IoCs for a detailed investigation.

To view the extended details:

1. In the left-side navigation, click **Investigate**.
2. On the **Investigate** page, click an element on the graph. For example, click an attacker icon. See image.
3. In the details pane, click **View Extended Details.** See image.
4. The following tabs are displayed: See image.
  - **ThreatParse**: Attack details based on the MITRE ATT&CK framework along with a risk score in plain English. To learn more, see [Viewing ThreatParse Details](https://help.zscaler.com/deception/viewing-threatparse-details).
  - **Chronology**: A temporal overview and heatmap of the attacker's activities. To learn more, see [Viewing Attack Chronology Details](https://help.zscaler.com/deception/viewing-attack-chronology-details).
  - **Network**: Details of the attacker's activity in your network based on the telemetry generated by network decoys. To learn more, see [Viewing Network Details](https://help.zscaler.com/deception/viewing-network-details).
  - **Event Logs**: Event logs associated with an attacker activity. To learn more, see [Viewing and Managing Event Logs](https://help.zscaler.com/deception/viewing-managing-event-logs).
  - **Evidence**: Evidence of an attack on the PCAP, RDP, or IoC files. To learn more, see [Viewing and Managing Evidence](https://help.zscaler.com/deception/viewing-managing-evidence).

[Image: Attacker's extended details]

[Image: Extended Details Page]

[Image: Click an attacker icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-investigate-dashboard","lastmod":"2026-08-03T00:19Z","nid":"1540667"} -->
## Viewing the Investigate Dashboard

- Source: https://help.zscaler.com/deception/viewing-investigate-dashboard
- Product: Deception
- Path: Deception Help > Investigate  > Viewing the Investigate Dashboard
- Last modified: 2026-08-03T00:19Z
- Summary: How to view the Investigate dashboard in the Zscaler Deception Admin Portal.

The Zscaler Deception Investigate dashboard is an interactive graphical representation of real-time alerts that indicate attacker activities. When an attacker interacts with the decoys, Deception detects threats, collects information on the attacker's actions and intentions, and generates high-fidelity and real-time alerts that are displayed on the dashboard.

[Image: Investigate dashboard]

## Viewing Events

Deception identifies attacker activities and presents them in an interactive graph, where icons represent both elements and attackers. You can click the icons to view the details pane that shows all the attacker's activities.

1. Zscaler Deception Admin Portal: The Deception configuration and management platform.
2. Decoy Connector: Lightweight virtual machines (VMs) that host network decoys. They also act as a network broker to connect the Deception service to on-premises systems and Active Directory (AD) decoys in your environment.
3. Decoy Group: Logically organized networks and Threat Intelligence (TI) decoys based on function, location, system type, etc.
4. Decoy: Decoys look like real assets and are a trap to divert attackers. When an attacker interacts with a decoy, Deception detects the threat and relays all the information to the Deception Admin Portal. Each type of decoy is represented by a unique icon.
5. Attacker: A unique [identicon](https://en.wikipedia.org/wiki/Identicon) depicts the attacker's interaction with decoys, and the country's flag (if available) is displayed in the case of a TI activity. Icons help distinguish and visually identify attackers without the need to remember complex IP addresses or hostnames.

See image.

## Filtering Events

You can filter events using time and queries.

- By Time
- Using Queries

You can filter events between the last 10 minutes and all time. By default, the dashboard displays information for events that occurred in the last 7 days.

To filter events by time:

- Click the time selector drop-down menu, and select a time period (e.g., **Last 30 days**). See image.
- To select a custom time frame, provide a **Start**and **End**date in years, months, days, hours, and minutes. See image.

You can use built-in queries or the query builder to create filters and focus on specific events.

- Use Saved Queries
- Use the Query Builder

Deception provides a list of saved queries to quickly filter certain types of events. Select a saved query from the **Select Query** drop-down menu. For example, select **Cloud Threat Detection**to filter for threat detections related to AWS or Azure.

See image.

You can select the built-in syntax to create simple and advanced query expressions and save them for future use.

- Create a Simple Query
- Create an Advanced Query
- Save a Query

Simple queries have just one parameter. To build a query to filter attacks from a particular IP address:

1. Click the query builder and enter `attacker.ip`. As you continue to type, the query builder lists the built-in syntax that matches your query. You can use various supported fields, parameters, operators, and regular expressions to build queries depending on your requirements. To learn more, see [Understanding and Building Queries](https://help.zscaler.com/deception/understanding-and-building-queries). See image.
2. Select **attacker.ip** from the drop-down menu and add `"192.0.2.0"` to the query. Events from the specified attacker's IP address are filtered and displayed. See image.

When using regular expressions to build queries, make sure that appropriate letter casing is used in the expressions as the filter does not support case-agnostic expressions nor [the case-insensitivity flag](https://help.zscaler.com/deception/understanding-and-building-queries#zd-query-language-regex) (`\I`).

Advanced queries are complex queries with Boolean logic statements. To filter events that match one or more criteria:

1. Click the query builder and enter `(type)`. As you type, the query builder lists the built-in syntax that matches your query syntax. See image.
2. Select the required built-in syntax from the list and create the advanced query. `(type is "windows") or (type is "network" and sub_type is "file")` Events that match one of the criteria are filtered and displayed. You can use various supported fields, parameters, operators, and regular expressions to build queries depending on your requirements. To learn more, see [Understanding and Building Queries](https://help.zscaler.com/deception/understanding-and-building-queries). See image.

When using regular expressions to build queries, make sure that appropriate letter casing is used in the expressions as the filter does not support case-agnostic expressions nor [the case-insensitivity flag](https://help.zscaler.com/deception/understanding-and-building-queries#zd-query-language-regex) (`\I`).

You can create queries and save them for future use.

To save a query:

1. Create a simple query or an advanced query.
2. (Optional) Select a time frame from the time selector drop-down menu (e.g., **Last 30 days**).
3. Click **Save Query**. See image.
4. In the **Save Query** window: See image.
  - **Name**: Enter the name of the query.
  - **Include time period**: (Optional) Enable if you want the query to record the selected time period and restore the time period settings when the query is selected in the future.
5. Click **Save Query**. The query is saved and listed in the **Select Query** drop-down menu. See image. To delete a saved query, click the **Delete** icon next to the query.

## Using the Chronology Slider

The chronology slider shows a graph of attack volume by time. After filtering events by timeline, you can drag the controls at both ends of the chronology slider to select a subset of time from your main filter. For example, if you have filtered events for the last 30 days, you can drag the controls at both ends of the chronology slider to trim the timeline to the first 10 days. Click the **Apply Current Range** ([Image: Apply current time range icon]) icon to apply the time range and view the graph for the subset of time. Click the **Start Replay** ([Image: Start replay icon]) icon to replay the recorded events in the sequence they occurred. This helps you to analyze the chronology of attacks.

See image.

## Using Dashboard Controls

You can use the following controls to customize the dashboard:

1. Zoom slider: Zooms in and out of the elements on the dashboard.
2. Fit to screen: Fits the elements on the dashboard to the screen dimensions.
3. Rearrange elements: Rearranges the elements on the dashboard to their original position if they were moved.
4. Lock elements: Locks all the elements on the dashboard.
5. Full screen: Toggles to full-screen mode.

See image.

## Using Additional Filters

You can use additional filters to select a layout, view specific details, filter attack types, etc.

To apply additional filters:

1. Click the filter icon in the bottom-right corner of the page. See image.
2. In the **Filters** window: See image.
  - **Level of Detail**: Reduce or increase the number of elements or details on the dashboard. The first level is the Deception Admin Portal, followed by Decoy Connectors, Decoy Groups, and Decoys.
    - Click the minus (-) icon to reduce the number of elements and view basic details of the attack.
    - Click the plus (+) icon to increase the number of elements and view in-depth details of the attack. The number on the filter icon shows the current level of detail.
  - **Layout**: Select a layout from the drop-down menu:
    - **Default**
    - **Hierarchy** (tree layout)
    - **Radial** (circular layout)
  - **Threat Level**: Select a threat level from the drop-down menu to filter events based on threat severity:
    - **All**
    - **Medium and Higher**
    - **Only High**
  - **Decoy Type**: Select a decoy type from the drop-down menu to view events for that decoy type:
    - **Active Directory**
    - **Endpoint**
    - **ITDR**
    - **Network**
    - **Threat Intelligence**
  - **Attack Types**: Select an attack type from the drop-down menu to view events for that attack type:
    - List of attack types
  - **Demographics**: Select a demographic level to view events based on the interaction between the attacker and the Deception elements:
    - **All**
    - **One to Many**
    - **Many to One**
    - **One to One**
  - **Show Only Attacks**: Enable to show only those elements that are attacked.
  - **Interactive Only**: Enable to show attacks only when there is an interaction between the attackers and decoys. For example, if **Interactive Only** is enabled, network reconnaissance attacks are not shown.
  - **Attackers as Identicons**: Enable to show attacker icons as identicons. Disable to show a generic attacker icon instead of a unique identicon for each attacker.
  - **Attackers as Flags**: Enable to show attack icons as country flags, if available.
  - **Attackers as Tags**: Enable to show attacker icons as tagged names. You can tag an attacker icon with an alternate text for your reference. If enabled, attacker icons are shown with tagged names.

- AMQP
- Cloud
- Credential Theft
- Custom Service
- Endpoint
- FTP
- File Theft
- GenAI
- ITDR Threat Detection
- MITM
- MQTT
- MongoDB
- MySQL
- Network
- PostgreSQL
- SKADA
- SSH
- Telnet
- Threat Intelligence
- Web
- Windows

[Image: Alerts area on the Investigate page]

[Image: Select a time period to filter alerts]

[Image: Select custom time frame]

[Image: Select a built-in query to filter alerts]

[Image: Create a simple query using the query builder]

[Image: Alerts are filtered based on the simple query]

[Image: Create an advanced query using the query builder]

[Image: Alerts are filtered based on the advanced query]

[Image: Save Query option on the dashboard]

[Image: Save a query]

[Image: Saved query]

[Image: Chronology slider on the dashboard]

[Image: Display icons]

[Image: Additional filter icon]

[Image: Configure additional filters on the dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-managing-event-logs","lastmod":"2026-08-03T00:19Z","nid":"1540440"} -->
## Viewing and Managing Event Logs

- Source: https://help.zscaler.com/deception/viewing-managing-event-logs
- Product: Deception
- Path: Deception Help > Investigate  > Extended Details > Viewing and Managing Event Logs
- Last modified: 2026-08-03T00:19Z
- Summary: How to view and manage event logs in the Zscaler Deception Admin Portal.

When an attacker engages with a decoy, Zscaler Deception immediately triggers an alert and records the activity in the event logs. Event logs provide detailed insights into attempted attacks by malicious actors who have infiltrated the network and interacted with deployed decoys. Security teams can investigate these incidents, analyze attacker behavior and intent, and use the findings to strengthen threat response and containment strategies. For deeper analysis and proactive defense planning, event logs can be downloaded in multiple formats.

## Viewing Event Logs

To view event logs:

1. In the left-side navigation, click **Investigate.**
2. On the **Investigate** page, click any icon to view the event details. See image.
3. In the details pane, click **View Extended Details.** See image.
4. Select the **Event Logs** tab. See image. For each event, you can see basic event information, such as: For in-depth details of the event, click any event to open its **Event Details**window. See image.
  - **Timestamp**: The time and date when the attack happened.
  - **Attack Type**: The attack type (e.g., network, web, etc.).
  - **Attacker ID**: The attacker's IP address.
  - **Decoy Name**: The name of the decoy with which the attacker interacted.
  - **Decoy Group**: The category of decoy on which the attacker attacks.
  - **Decoy Port**: The port number of the decoy.
  - **Decoy Connector**: The name of the Decoy Connector.
  - **Kill Chain Phase**: The kill chain attack phase (e.g., lateral movement, exploitation, etc.).

### Adding or Removing Columns

You can add or remove columns to show additional information about the events. You can add a maximum of 50 columns.

1. Click the **Expand** icon. See image.
2. From the side panel, select or deselect columns that you want to appear in the table. See image.

## Managing Event Logs

You can perform the following actions with event logs:

- Export event logs.
- Delete events.
- Mark events as safe.
- Test a rule with events.

[Image: Expand icon on the Event Logs page]

[Image: Add columns to the Event Logs table]

[Image: Click an attacker icon]

You can delete events when they are no longer needed. The option to delete specific events works well when the number of events is less than 100.

To delete a specific event:

1. On the**Event Logs** page, select the checkboxes next to the event that you want to delete.
2. From the **Actions**drop-down menu, select **Delete.** See image. The selected events are deleted permanently.

To delete all events:

1. From the **Actions**drop-down menu, select **Delete All**. See image. All events are removed. The **Delete All** option removes a maximum of 100K events at a time. If you have more than 100K events associated with an entity, repeat these steps.

[Image: Delete selected events]

[Image: Delete all events]

You can mark events as safe if they are legitimate events. For example, you can mark an event as safe if it was generated due to testing activity.

1. On the**Event Logs** page, select the checkboxes next to the event that you want to mark as safe.
2. From the **Actions**drop-down menu, select **Mark as safe.** See image.

[Image: Mark event as safe]

You can use the listed events and test them if they match custom rules created using [query language](https://help.zscaler.com/deception/understanding-and-building-queries). You can combine multiple events and test custom rules on them. Typically, you can test rules on events to analyze why an existing [orchestration rule](https://help.zscaler.com/deception/about-orchestration-rules) did not trigger, or to create new rules that would match similar events.

To test a rule:

1. On the**Event Logs** page, select the checkboxes next to the event that you want to test. You can select a maximum of 10 events.
2. From the **Actions**drop-down menu, select **Test Rule**.
3. In the **Test Rules**window, enter custom rules in the **Rule**field. To learn more, see [Understanding and Building Queries](https://help.zscaler.com/deception/understanding-and-building-queries). See image. The test results are displayed immediately, showing the number of events that passed the rule. The list of events that failed is shown separately.

[Image: Use an event to test a rule]

You can export the attacker summary and events to analyze and orchestrate actions to contain the threat.

To export event logs:

1. On the **Event Logs** page, click the **Export** drop-down menu and select an option: See image.
  - **Attacker Summary in JSON**: Export a JSON file that includes the attacker's hostname and IP address, details of the decoys the attacker interacted with, first and last seen timestamps, etc.
  - **Attacker Summary in CSV**: Export a CSV file that includes the attacker's hostname and IP address, details of the decoys the attacker interacted with, first and last seen timestamps, etc.
  - **Events as JSON**: Export a JSON file that includes all fields for event logs.
  - **Event Fields as JSON**: Export a JSON file that includes all fields for event fields.
  - **Events as CSV**: Export a CSV file that includes the event ID, timestamp, decoy details, etc. You can add additional fields, if required.
  - **STIX v1.2**: Export a file in STIX format that includes event logs (supported for STIX version 1.2).
  - **STIX v2.1**: Export a file in STIX format that includes event logs (supported for STIX version 2.1).
  - **Snort Rules**: Export a file that includes the snort rules for the event logs.
2. The event logs in the selected format are exported to your system. See image.

[Image: Export event logs]

[Image: Event Logs exported to CSV format]

[Image: Select an event]

[Image: Click view extended details]

[Image: Event Logs page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-managing-evidence","lastmod":"2026-08-03T00:19Z","nid":"1540441"} -->
## Viewing and Managing Evidence

- Source: https://help.zscaler.com/deception/viewing-managing-evidence
- Product: Deception
- Path: Deception Help > Investigate  > Extended Details > Viewing and Managing Evidence
- Last modified: 2026-08-03T00:19Z
- Summary: How to view and manage evidence files in the Zscaler Deception Admin Portal.

When an attacker interacts with a decoy, Zscaler Deception generates evidence files that capture details of the attack. These files provide valuable information to investigate the incident and understand the attacker's tactics, techniques, and procedures (TTPs). They can be analyzed through integration with Zscaler Sandbox or third-party sandbox solutions, with reports available directly from the Zscaler Deception Admin Portal. Evidence files can also be downloaded in their original format for external analysis, and records of files generated for individual attack events can be exported for further review.

## Viewing Evidence Files

To view the evidence files:

1. In the left-side navigation, click**Investigate.**
2. On the **Investigate** page, click the decoy element to view the evidence details. See image.
3. In the details pane, click **View Extended Details**. See image.
4. Select the **Evidence** tab. You can view all available evidence files. See image. For each evidence file, you can see:
  - **Timestamp**: The time and date when the event happened.
  - **Decoy Connector ID**: The ID of the Decoy Connector.
  - **Type**: Depending on the decoy interaction, the evidence files are available in the following formats:
    - Packet Capture (PCAP)
    - Remote Desktop Protocol (RDP) recording files
    - Indicators of Compromise (IOC)
  - **Metadata**: The attribute associated with each log file.

## Managing Evidence Files

You can perform the following actions with evidence files:

- Send an evidence file to a sandbox.
- Download a sandbox report.
- Analyze the sandbox report.
- Download evidence files.
- Export evidence details.
- Delete evidence files.

[Image: Click decoy element]

[Image: Click View Extended Details]

[Image: Evidence details]

Deception is integrated with the Zscaler Sandbox. This allows you to send evidence files to the sandbox, and download an analysis report to enrich incident investigations and response. To learn more, see [About Sandbox](https://help.zscaler.com/zia/about-sandbox).

Only IOC files can be sent to the sandbox.

In addition to Zscaler Sandbox integration, Deception also supports the following third-party sandboxes:

- Palo Alto Networks WildFire
- Hybrid Analysis
  - Linux (Ubuntu 20.04, 64-bit)
  - Windows 7 64-bit
  - Windows 7 32-bit (HWP Support)
  - Windows 7 32-bit
- Joe Sandbox
  - Windows 10 64-bit
  - Windows 10 64-bit (native)
  - Windows 7 64-bit
  - Linux (Ubuntu 16.04, 64-bit)
  - Linux (Ubuntu 20.04, 64-bit)
  - Mac (Mojave)
- CrowdStrike Falcon X
  - Linux (Ubuntu 16.04, 64-bit)
  - Windows 7 64-bit
  - Windows 7 32-bit
  - Windows 10 64-bit
- VirusTotal

Before sending an evidence file to a third-party sandbox, you must configure the sandboxes for enrichment integration. To learn more, see [About Enrichment Integrations](https://help.zscaler.com/deception/about-enrichment-integration).

To send an evidence file to a sandbox:

1. On the **Evidence** page, select **IOC** from the **Type**drop-down menu. See image.
2. Locate the IOC evidence file that you want to analyze, and click the **Send to Sandbox** icon. See image.
3. In the **Send to Sandbox** window, select a sandbox option. See image.
4. Click **Send**.
5. Click **OK** in the confirmation window. The evidence file is submitted to the sandbox. Submitting an evidence file can take a few minutes, depending on the file type and size. After you send the evidence file to a sandbox, you can download the report for analysis.

[Image: Sandbox Report]

[Image: Select IOC evidence files from the Type drop-down menu]

[Image: Select a Sandbox option]

After the evidence file is sent, you can download the analysis report.

1. On the **Evidence** page, locate the IOC evidence file, and click the**Download** icon under the **Report**column. See image.
2. In the **Download Report** window, select **Zscaler Sandbox**. See image.
3. Click **Done**. The **Zscaler Sandbox Report** page appears. See image.
4. Click **Download Report**. The report is downloaded to your system in the JSON format.

To download a third-party sandbox report:

1. On the **Evidence** page, locate the IOC evidence file, and click the **Download** icon under the **Report** column. See image.
2. In the **Download Report** window, select a third-party sandbox. See image.
3. Click **Done**. The report is downloaded to your system in either a PDF or JSON format.

[Image: Sandbox Report]

[Image: Download a Zscaler Sandbox Report]

[Image: Zscaler Sandbox Report]

[Image: Sandbox Report]

[Image: Download a third-party sandbox report]

1. Locate an evidence file based on the reported timestamp. You can filter evidence files based on the file type using the drop-down menu under the **Type** column. For example, you can select **ioc** to view IOC files only.
2. Under the **Actions** column, click the **Download** icon. See image. The evidence file is downloaded to your system. PCAP files contain one minute or 5 MB of PCAP data, whichever is less. You can download a unified PCAP file for the selected time duration from the [Network](https://help.zscaler.com/deception/about-network) page.

[Image: Download the evidence file]

You can delete evidence files when they are no longer needed.

To delete specific files:

1. On the **Evidence** page, select the checkboxes next to the evidence files that you want to delete.
2. From the **Actions** drop-down menu, select **Delete**. See image.
3. In the confirmation window, click **OK**. The selected evidence files are deleted.

To delete all evidence files:

1. From the **Actions** drop-down menu, select **Delete All**. See image. All evidence files for the specified time period on the[Deception Investigate Dashboard](https://help.zscaler.com/deception/viewing-investigate-dashboard) are permanently deleted from the Decoy Connectors.
2. In the confirmation window, click **OK**. All the evidence files are deleted.

[Image: Delete selected evidence files]

[Image: Delete all evidence files]

The Zscaler Sandbox Report displays the attack lifecycle, event killchain, malware behavior, and payload intent. It maps the information to the MITRE ATT&CK framework. You can leverage this report to enrich security operation workflows and strengthen your defenses throughout the security stack.

[Image: The Zscaler Sandbox Report]

The Zscaler Sandbox Report displays the following information:

- **Classification**: The threat type of the analyzed file, such as Benign, Malicious, and Suspicious with the associated threat score. The file is further categorized as follows:
  - **Benign**: A known non-malicious file.
  - **Adware**: A known malicious file that automatically renders advertisements and installs adware.
  - **Anonymizer**: A known malicious file that contains anonymizers and P2P clients.
  - **Malware**: A known malicious file that behaves like APTs, exploits, botnets, trojans, keyloggers, spyware, and other malware.
- **File Properties**: File properties such as the file size, digital certificates used to sign in to the file, MD5, SHA1, and SSDEEP hash details.
- **Exploits**: List of exploits detected, if any.
- **Network**: Information on which network connections were initiated and the network objects of the evidence file, if any.
- **Persistence**: Information on the persistence mechanism created by the evidence file.
- **Security Bypass**: The techniques used to bypass traditional security scanners and sandboxes.
- **Spyware**: The techniques spyware processes use to learn about the system and spy on the user.
- **Stealth**: The techniques used to hide the process from the user and systems trying to identify the malware.
- **System Summary**: Information related to the system and process.

Click **Download Report** to download the report to your local system in the JSON format.

You can export the list of evidence details to a JSON or CSV file.

To export an evidence file:

1. On the **Evidence** page, click the **Export**drop-down menu. See image.
2. Select **Evidence as JSON** or **Evidence as CSV**. The file is exported to your system.

[Image: The Export menu options on the Evidence page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-network-details","lastmod":"2026-08-03T00:19Z","nid":"1540439"} -->
## Viewing Network Details

- Source: https://help.zscaler.com/deception/viewing-network-details
- Product: Deception
- Path: Deception Help > Investigate  > Extended Details > Viewing Network Details
- Last modified: 2026-08-03T00:19Z
- Summary: How to view network details in the Zscaler Deception Admin Portal.

The Network page shows information about the attacker's activity on your network based on the telemetry generated by network decoys. It displays a graph that shows the path that an attacker used to interact with the network decoys. It lists event logs that include the attacker's IP address, decoy IP address and port, network protocol, network service, and network connection duration.

The attack network graph helps you to understand the attacker's behavior. Analyze the attack pattern and the attacker's behavior using the graph that shows the network path used by the attacker to interact with the decoy. You can filter the event logs as necessary and combine the information in the graph and logs to identify how and when the attack happened. In addition, you can leverage the log information recorded for the event to correlate this data with the graph to gain better insights into the attack.

To learn more, see [Viewing Extended Details](https://help.zscaler.com/deception/viewing-extended-details).

See image.

The Network page includes:

- Network graph: Shows the attack path from the initial node to the target node that describes the attacker's specific attack behaviors. You can also drag the cursor and select an area on the graph to filter events.
- Filtered event logs: Shows logs filtered per your selection on the graph. Click the log entry to see all the details available for the event. See image.
- PCAP: Provides options to build and download a consolidated packet capture file (PCAP) that has evidence of attack for the specified time period. To download a PCAP file, select **Build PCAP** from the **PCAP**drop-down menu. After the file is built, click **Download Available PCAP.**

See image.

[Image: Accessing network details]

[Image: Network details]

[Image: Building and Downloading PCAP File]

[Image: Network Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-policy-details","lastmod":"2026-02-04T04:28Z","nid":"1539273"} -->
## Viewing Policy Details

- Source: https://help.zscaler.com/deception/viewing-policy-details
- Product: Deception
- Path: Deception Help > Deceive  > Landmine Decoys > Policies > Policy Management > Viewing Policy Details
- Last modified: 2026-02-04T04:28Z
- Summary: How to view the details of landmine deception modules in a policy that is deployed on a system.

You can view the details of the configured landmine deception modules in a policy that is deployed on a system. On the policy details page, you can view:

- **System Details**: The system name, username, policy name, operating system name and version, IP addresses of the system, the type of agent installed on the system (agent or agentless), date and time when the agent was installed, and date and time when the agent was last connected to the Zscaler Deception Admin Portal.
- **Fake Security Processes Details**: The name of the fake process, policy name, and folder path to the fake process.
- **Privilege Escalation Details**: The name of the detection (such as man-in-the-middle (MITM), brute force, kerberoast, and memory credential); status (enabled or disabled); and other configuration details.
- **Cloud Lures**: The policy name, usernames, Amazon Web Services (AWS) access key, and AWS secret keys.
- **Browser Lures**: For the supported browsers (Google Chrome, Mozilla Firefox, etc.), you can view:
  - Cookie lure details such as username, cookie name, host, value, etc.
  - Bookmark lure details, username, bookmark name, URL, folder name, etc.
  - Browser lure credentials, username, perimeter application decoy, URL, etc.
- **Session Lures**: The decoy credentials, hostname, and port number of supported software such as FileZilla, DbVisualizer, PuTTY, Remote Desktop Protocol (RDP), SQuirreL SQL, WinSCP, etc.
- **File Decoys**: The name, path, and hidden attributes of decoy files such as custom files, credential files, and preconfigured file datasets.
- **Advanced Deception Capabilities**: The status (enabled or disabled) of advanced deception capabilities such as PsExec, Ransomware, and Triage.

To view the details of the configured deception modules applied to a system using the landmine agent:

1. Go to **Settings**> **Endpoint Settings**> **Agents**.
2. Locate the system (using the **System Name** field) on which policies are applied by an agent, and click the **View** icon under the **Actions**column. See image. The policy details page appears. See image.

[Image: View policy details icon]

[Image: View policy details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-private-access-app-connector-update-logs","lastmod":"2026-06-24T21:06Z","nid":"1540630"} -->
## Viewing Private Access App Connector Update Logs

- Source: https://help.zscaler.com/deception/viewing-private-access-app-connector-update-logs
- Product: Deception
- Path: Deception Help > Settings > Topology > ZPA App Connectors > Viewing Private Access App Connector Update Logs
- Last modified: 2026-06-24T21:06Z
- Summary: How to view Private Access App Connector update logs.

Update logs provide valuable information that you can use to troubleshoot Private Access (ZPA) App Connector update issues.

To view the update logs:

1. Go to **Settings** > **Topology** > **ZPA App Connectors**.
2. Click **Logs**for an App Connector to view the update logs.
3. Select **View update logs** from the drop-down menu. See image.
4. When the **Update Logs** window appears, view the logs, and then click **Close**. See image.

[Image: View ZPA App Connector update logs]

[Image: View App Connector update logs]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/viewing-threatparse-details","lastmod":"2026-08-03T00:19Z","nid":"1540437"} -->
## Viewing ThreatParse Details

- Source: https://help.zscaler.com/deception/viewing-threatparse-details
- Product: Deception
- Path: Deception Help > Investigate  > Extended Details > Viewing ThreatParse Details
- Last modified: 2026-08-03T00:19Z
- Summary: How to view ThreatParse details in the Zscaler Deception Admin Portal.

ThreatParse is a technology that conducts natural language reconstruction of attacks by summarizing and translating log information into plain English. It also links this information to the MITRE ATT&CK framework and includes the risk scores assigned to attackers. The information on the ThreatParse details page helps your analysts to understand what the attacker is trying to accomplish. They can prioritize the most pressing threats first and take necessary actions to stop lateral movement.

To learn more, see [Viewing Extended Details](https://help.zscaler.com/deception/viewing-extended-details).

See image.

For each threat, you can view information such as:

- A detailed description of each threat
- Attack examples
- Attack mitigations
- The MITRE ATT&CK ID
- The MITRE ATT&CK tactic
- The event occurrence summary
- Important information about the attacker (e.g., the username and the password the attacker has submitted)

See image.

[Image: Accessing ThreatParse Details]

[Image: ThreatParse details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/deception/what-zscaler-deception","lastmod":"2026-08-03T00:19Z","nid":"1540335"} -->
## What Is Zscaler Deception?

- Source: https://help.zscaler.com/deception/what-zscaler-deception
- Product: Deception
- Path: Deception Help > Getting Started > What Is Zscaler Deception?
- Last modified: 2026-08-03T00:19Z
- Summary: Introductory information about Zscaler  Deception and its cloud-based infrastructure.

Zscaler Deception is a simple, faster, and more effective targeted threat detection solution built on the Zscaler Zero Trust architecture. Deception uses advanced lures and decoys to detect and disrupt sophisticated threats that consistently bypass traditional defenses, such as advanced persistent threats (APT), exploits, reconnaissance, lateral movement, active directory, supply chain, human-operated ransomware, supervisory control and data acquisition (SCADA), and industrial control system (ICS) attacks.

As an integral part of the Zscaler Zero Trust Exchange, Deception integrates with Zero Trust, tracking the full attack sequence and initiating automated response actions across the Zscaler platform. To learn more, see [Zscaler Deception Overview](https://www.zscaler.com/products/deception-technology).

## Why Deception?

Attackers are becoming exceptional at exploiting organizations' growing attack surfaces. Advanced attacks bypass existing defenses. Detecting and containing them is challenging because:

- Advanced attacks are stealthy and 91% of attacks do not generate a security alert.
- Advanced attacks are human-operated and 68% of attacks do not use malware.
- Security teams have too many events to investigate because 45% of alerts are false positives.

Deception uses active defense techniques to make your network a hostile environment for attackers. It blankets your environment with decoys for all your IT artifacts. Decoys make your environment unpredictable and disrupt attackers' playbooks. Decoys lure attackers and detect advanced attacks without operational overhead or false positives. Deception is a robust addition to your organization's threat detection and Zero Trust strategy. It's the easiest way to add a powerful layer of high-fidelity threat detection to your entire enterprise.

### Key Features and Benefits

Deception's key features and benefits include:

- Ease of deployment: Integration with the Zscaler Zero Trust Exchange allows for seamless deployment. Deception is cloud-delivered and scalable, and requires minimal on-premises computing. It does not require additional hardware upgrades.
- Comprehensive coverage: Provides threat detection for your perimeter, applications, endpoints, active directories, cloud, and operational technology (OT) or internet of things (IoT) environments.
- Extensive built-in decoys: Easy to customize and manage decoys that engage savvy adversaries.
- Disrupt advanced threats: Detects and stops attackers across your security infrastructure, including low-visibility paths like DC-to-DC and internal-traffic-to-DC.
- Low false positives: There is no legitimate business traffic to decoys, so any interaction with them is an immediate high-confidence signal of an ongoing breach, alerting your security team to threats like ransomware, supply chain attacks, and APTs.
- Business risk awareness: Aligns security controls tightly to areas with current business risks.
- Orchestrated response: Orchestrates complex scenarios with high-fidelity alerts. Takes precise action to shut down active attacks, driven by high-confidence alerts.
- Active defense with the MITRE ATT&CK framework: Delivers 99% of the capabilities covered in MITRE Engage.

## How Does Deception Work?

Deception's workflow is categorized as follows:

### Administration

The Administration's key components include:

- Licenses: Deception issues a license to you when you subscribe.
- User Roles: Deception allows you to create users with the following roles at the license and account level:
  - Administrator: Administrators can configure Decoy Connector (virtual machines), deploy decoys, view and analyze events, and orchestrate actions.
  - Analyst: Analysts can investigate events, block the attackers, and export logs.
  - Responder: Responders can analyze events and orchestrate actions.
  - Super admin: Super admins can view and manage all features and configurations of Deception, such as configuring user roles, APIs, decoys, audit logs, etc. You can create a custom role with specific read and write permissions based on the requirements.

### Configure Network Components

To place decoys on your network, Deception allows you to configure Decoy Connectors and connect them to the Zscaler Deception Admin Portal. You can configure virtual LANs (VLANs) in your environment to deploy network decoys. If you have deployed Private Access (ZPA) for Zero Trust Network Access, you can integrate Deception with Private Access to deploy Zero Trust Network decoys without installing any additional network components or making any changes to your network configurations.

### Configure and Deploy Decoys

Deception allows you to configure and deploy decoys to disrupt active attacks, create fake attack paths, and gain high-fidelity threat intelligence. Deception provides extensive built-in decoys across the kill chain that mimic production assets. You can configure and deploy [network decoys](https://help.zscaler.com/deception/about-network-decoys), [Threat Intelligence (TI) decoys](https://help.zscaler.com/deception/about-threat-intelligence-decoys), [Active Directory (AD) decoys](https://help.zscaler.com/deception/about-active-directory-decoys), [endpoint decoys](https://help.zscaler.com/deception/about-landmine-decoys), and [cloud decoys](https://help.zscaler.com/deception/understanding-cloud-deception). In addition, you can use the extensive datasets in [Miragemaker](https://help.zscaler.com/deception/understanding-miragemaker-module) to make decoys more realistic-looking and scalable.

### Detect Threats

When an attacker infiltrates your network and interacts with the decoys, Deception detects threats, collects information on the attackers' actions and intentions, and generates high-fidelity and real-time alerts.

### Investigate

When alerts appear on the Deception Admin Portal, Deception captures threat intelligence data and reconnaissance activity in real time. ThreatParse conducts natural language reconstruction of attacks, summarizes log information, and translates it into plain English to help the security operation center (SOC) and analysts understand what the attacker is trying to accomplish. SOCs and analysts can correlate this data to generate threat intelligence and take necessary actions to stop lateral movement.

### Orchestrate

Based on high-fidelity data, Deception lets you orchestrate (automate) and build rules to take immediate actions when events are discovered to contain threats.

### Remediate

When the attacker's intended targets are uncovered, Deception deploys additional decoys to validate remediation.
<!-- /ZS-ARTICLE -->
