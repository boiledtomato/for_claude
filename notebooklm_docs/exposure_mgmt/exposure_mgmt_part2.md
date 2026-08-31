# Zscaler Help — Risk & Exposure Management (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-31 03:58 UTC
Articles in this file: 153

---

<!-- ZS-ARTICLE {"url":"/risk360/about-financial-risk","lastmod":"2025-09-11T21:26Z","nid":"1453096"} -->
## About Financial Risk

- Source: https://help.zscaler.com/risk360/about-financial-risk
- Product: Risk360
- Path: About Financial Risk
- Last modified: 2025-09-11T21:26Z
- Summary: Information on the financial factors affecting your organization financially in the Risk360 Admin Portal.

The Financial Risk page gives visibility into your organization's potential financial loss exposure for your current risk score. You can also analyze the factors affecting the financial loss exposure on this page.

Viewing your financial exposure provides the following benefits and allows you to:

- Analyze your organization's potential financial loss at hand with respect to your risk score.
- Keep track of the contributing factors aiding to the financial loss exposure.
- View the loss curve over time to take appropriate actions to mitigate your organization's financial risks.

## About the Financial Risk Page

On the Financial Risk page, you can do the following:

1. [Update the financial risk calculation parameters](https://help.zscaler.com/risk360/managing-financial-risk-settings).
2. Download the complete list of factors affecting the financial risk to a CSV file.
3. [View the Monte Carlo Simulation for financial loss](https://help.zscaler.com/risk360/quantifying-loss-monte-carlo-simulation).
4. **Financial Summary**: View our organization's risk score-based financial exposure and the exposure without Zscaler. The section also provides an estimate of how much of this financial risk exposure can be reduced by implementing the recommended actions from the Top 10 Financial Contributing Factors section.
5. **Loss Curve**: The graph shows the loss estimate change over the last 6 months.
6. **Top 10 Financial Contributing Factors**: View the top 10 factors contributing to your organization's financial risk exposure. For each factor, you can see:
  - **Financial Exposure**: The financial loss estimate is in US dollars.
  - **Factor Name**: The name of the contributing factor affecting the risk exposure.
  - **Category**: The category the factor falls under.
  - **Your Score**: The total score assigned to the factor and your score based on the configuration (0 being a healthy score). The total score for a factor depends on its severity.
  - **Licensed?**: Whether you are subscribed to the required feature to implement the recommended action (**Y** for Yes and **N** for No).
  - **Recommended Actions**: The recommended action that needs implementation to reduce your financial risk exposure.

Click on a financial contributing factor; you are redirected to the [Factor](https://help.zscaler.com/risk360/about-factors) page where you can see all the factors and analyze them.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/about-insights-risk360","lastmod":"2024-02-16T06:22Z","nid":"1453061"} -->
## About Insights in Risk360

- Source: https://help.zscaler.com/risk360/about-insights-risk360
- Product: Risk360
- Path: About Insights in Risk360
- Last modified: 2024-02-16T06:22Z
- Summary: Information on Risk360 Insights page, the different types of problems causing higher risk scores and further analyze them with interactive widgets.

The Insights page gives visibility into your organization's current risks and vulnerabilities. The page keeps updating in real time with the latest risks and recommendations discovered within your Zscaler environment.

Insights provides the following benefits and allows you to:

- Analyze your organization's risk exposure against potential cyber attacks or data loss.
- Remediate risks using the recommended actions or follow the documentation links provided to configure stronger policies to attain a healthy risk score.

## About the Insights Page

On the Insights page, you can do the following:

1. View the category-based risk score for your organization.
2. View a list of all problems discovered by the Risk360 service. For each problem, you can see the following information:
  - The problem title, its category, and the day it was generated.
  - **Problem**: The statement explaining the problem.
  - **Recommendation**: The recommendation to remediate the problem.
  - **Trend**: The section shows the problem trend (if applicable).
3. Analyze the problem further using the Explore link. To learn more, see [Exploring Problems](https://help.zscaler.com/risk360/exploring-problems).

[Image: The Insights page in the Risk360 Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/about-role-management-risk360","lastmod":"2026-04-16T10:25Z","nid":"1456991"} -->
## About Role Management

- Source: https://help.zscaler.com/risk360/about-role-management-risk360
- Product: Risk360
- Path: Risk360 Help > Role Management > About Role Management
- Last modified: 2026-04-16T10:25Z
- Summary: Information on managing roles in the Zscaler Admin Console.

[Watch a video about Role Management in Risk360.](https://fast.wistia.net/embed/iframe/zg7mhxhigs)

The roles that are assigned to admins dictate the level of access they have to the Risk360 features in the Experience Center. Zscaler provides a default super admin role that has full access to the Risk360. This role is assigned to the default admin, but you can assign this role to other admins as necessary.

Roles provide the following benefits and enable you to:

- Configure admins for Risk360 features based on their role.
- Assign roles to admins to maintain hierarchy among the admins so that the admin can't modify the settings of an admin with a broader scope.
- View all the configured admins, their access levels, and other information.

## About the Role Management Page

On the Role Management page (Administration > Admin Management > Role Based Access Control > Risk360), you can do the following:

1. [Add an admin role](https://help.zscaler.com/risk360/adding-admin-roles-risk360).
2. Search for a configured admin role.
3. View a list of all admin roles configured for your organization. For each role, you can view the following information:
  - **Name**: The name of the role.
  - **Full Access**: The areas of Risk360 where admins with this role have full access.
  - **View-Only Access**: The areas of Risk360 where admins with this role have view-only access.
  - **Risk360 User Device Name Access**: This shows whether the user names are visible or obfuscated within the Zscaler service
  - **Device Information**: This shows whether the device information is visible or obfuscated within Risk360 features.
  - **Functional Scope**: The features of Risk360 that admins with this role can access.
  - **Type**: The type of role. Currently, there is only one role type.
4. Modify the table and its columns.
5. Edit a configured admin role with lesser scope.
6. View a configured admin role.

[Image: Role Management Page in Risk360 Admin Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/about-seeds-management","lastmod":"2026-04-16T10:27Z","nid":"1504376"} -->
## About Seeds Management

- Source: https://help.zscaler.com/risk360/about-seeds-management
- Product: Risk360
- Path: Risk360 Help > Seed Management > About Seeds Management
- Last modified: 2026-04-16T10:27Z
- Summary: Information on managing domains for external surface attack analysis in the Zscaler Admin Console].

[Watch a video about Seeds Management in Risk360.](https://fast.wistia.net/embed/iframe/el04010hfk)

Seeds Management helps you manage domains that you want to analyze for external surface attacks. After you add the domains (manually or by uploading a CSV file), the Risk360 service performs weekly scans to identify publicly exposed assets within the domains for potential cyberattacks and helps you secure those exposed assets. The Risk360 service also quantifies the risk determined on various parameters for the exposed assets and visualizes them as factors on the [Factors](https://help.zscaler.com/unified/viewing-risk-factors) page. You can add up to 10 domains for scanning. After adding the domains, the Risk360 service takes up to 24–48 hours to scan and analyze the domains.

Seeds Management provides the following benefits and allows you to:

- Monitor multiple organization domains for external surface attacks.
- Identify and report exposed assets to mitigate potential cyberattacks.
- Ensure security compliance and view quantified risk based on your external surface vulnerabilities.

## About the Seeds Management Page

On the Seeds Management Page (Administration > Admin Management > Administrator Management > Seeds Management), you can do the following:

1. View scan frequency that detects external surface vulnerabilities of the domains.
2. [Add a domain](https://help.zscaler.com/risk360/adding-domain-external-attack-surface-analysis).
3. [Add domains by uploading a CSV file](https://help.zscaler.com/risk360/adding-domains-using-csv).
4. View a list of added domains.
5. Delete a domain from the list.

[Image: Seeds Management Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/adding-admin-roles-risk360","lastmod":"2026-03-27T08:48Z","nid":"1456996"} -->
## Adding Admin Roles

- Source: https://help.zscaler.com/risk360/adding-admin-roles-risk360
- Product: Risk360
- Path: Risk360 Help > Role Management > Adding Admin Roles
- Last modified: 2026-03-27T08:48Z
- Summary: How to add administrator roles in the Zscaler Admin Console.

[Watch a video about Role Management in Risk360.](https://fast.wistia.net/embed/iframe/zg7mhxhigs)

Configuring the Risk360 admin role is one of the tasks you must complete when configuring role-based administration.

## Prerequisites

When configuring roles:

- You must have the proper permissions to configure a role.
- You must have organizational scope.

## Adding Admin Roles

To configure admin roles:

1. Go to **Administration**> **Admin Management** > **Role Based Access Control > Risk360**.
2. Click **Add Risk360 Role**.

The **Add Risk360 Role** window appears.

1. In the **Add Risk360 Role** window:
  - **Name**: Enter a namefor the admin role.
  - **Permissions**: Permissions allow you to control an admin's access to the major Risk360 features. For each admin, you must select permissions in the following categories:
    - Dashboard
    - Administrator Management
    - User Management
    - Remote Assistance Management

See image.

1. Click **Save**.

You can edit or delete admin roles at any time.

In **Dashboard**, admins can view the Risk360 dashboard that enables real-time visibility into your organization's risk measurements and metrics in a range of areas.

Choose one of the following permissions:

- **View** **Only**: Allows admins to view the dashboard.
- **None**: Doesn’t allow access to the dashboard.

In **Administration Management**, admins can add other admins, create roles, and view user information.

Choose one of the following permissions:

- **Full**: Allows admins full access and editing privileges for the following pages.
  - **Administrator Management**
    - **Administrators**: Admins can add, edit, and delete admin accounts.
    - **Auditors**: Admins can view the auditors.
    - **Administrator Management**: Admins can configure password expiration and SAML single sign-on for admins.
  - **Role Management**: Admins can only add, edit, and delete roles that have equal or lesser scope.
- **View Only**: Allows admins to view, but not edit, the following pages.
  - Administrator Management
  - Role Management
- **None**: Doesn’t allow admins access to **Administration Management**.

In **User Management**, admins can view users, groups, and department information.

Choose one of the following permissions:

- **View** **Only**: Allows admins to view the User Management page.
- **None**: Doesn’t allow access to the User Management page.

In **Remote Assistance Management**, admins can allow Zscaler Support to securely and remotely log in to your Admin Portal.

Choose one of the following permissions:

- **Full**: Allows admins to access the Remote Assistance.
- **View** **Only**: Allows admins to view the Remote Assistance.
- **None**: Doesn’t allow access to the Remote Assistance.

[Image: Add Risk360 Roles Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/adding-domain-external-attack-surface-analysis","lastmod":"2026-03-11T23:12Z","nid":"1504406"} -->
## Adding a Domain for External Attack Surface Analysis

- Source: https://help.zscaler.com/risk360/adding-domain-external-attack-surface-analysis
- Product: Risk360
- Path: Risk360 Help > Seed Management > Adding a Domain for External Attack Surface Analysis
- Last modified: 2026-03-11T23:12Z
- Summary: Information on how to add domains for external attack surface analysis in the Zscaler Admin Console.

You can add domains on the Seeds Management page to run frequent scans on them to detect vulnerabilities for external attack surfaces. The Risk360 service then quantifies the risk determined on various parameters for potential breach and visualizes the risk as factors on the [Factors](https://help.zscaler.com/unified/viewing-risk-factors) page.

To add a domain:

1. Go to **Administration > Admin Management > Administrator Management > Seeds Management**.
2. In the **Domains** field, enter the domain that you want to scan for external attack surface analysis. You can add up to 10 domains.
3. Click **Add**. See image.

The domain is added successfully. The Risk360 service takes up to 24–48 hours to scan and analyze the domains.

[Image: Adding Domains on Seeds Management Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/adding-domains-using-csv","lastmod":"2026-03-11T23:21Z","nid":"1504411"} -->
## Adding Domains Using a CSV

- Source: https://help.zscaler.com/risk360/adding-domains-using-csv
- Product: Risk360
- Path: Risk360 Help > Seed Management > Adding Domains Using a CSV
- Last modified: 2026-03-11T23:21Z
- Summary: Information on how to add domains for external attack surface analysis in the Zscaler Admin Console.

You can add domains on the Seeds Management page to run frequent scans on them to detect vulnerabilities for external attack surfaces. The Risk360 service then quantifies the risk determined on various parameters on exposed assets for potential breach and visualizes them as factors on the [Factors](https://help.zscaler.com/unified/viewing-risk-factors) page.

To add domains:

1. Go to **Administration > Admin Management > Administrator Management > Seeds Management**.
2. In the **Domains** field, click **Upload File**.
3. Click **Download CSV Template**. A CSV template for uploading the domains is downloaded to your device. See image.
4. Update the file with the list of domains that you want to scan for external attack surface analysis. You can add up to 10 domains. See image.
5. Click **Upload CSV**, go to the file on your device, and select the file.
6. After a successful upload message is displayed, click **Save**. If an error message is displayed, ensure that the domains are added in the correct format in the CSV file and reupload the file. The [Seeds Management](https://help.zscaler.com/risk360/about-seeds-management) page displays all the domains from the CSV file. The Risk360 service takes up to 24–48 hours to scan and analyze the domains.

[Image: Upload CSV window]

[Image: CSV Format]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/analyzing-asset-risk","lastmod":"2025-01-02T00:30Z","nid":"1486861"} -->
## Analyzing an Asset Risk

- Source: https://help.zscaler.com/risk360/analyzing-asset-risk
- Product: Risk360
- Path: Analyzing an Asset Risk
- Last modified: 2025-01-02T00:30Z
- Summary: Information on analyzing risk for an asset in the Risk360 Admin Portal.

When you click an asset on the [Assets](https://help.zscaler.com/risk360/about-asset-level-risk) page, you are redirected to the Asset Details page where you can view the asset's risk score, its risk score trend, location, events that contributed to the risk score change, metadata information, etc. Asset-level risk monitoring is vital in maintaining a healthy risk score and protecting your organization from various security incidents specific to the asset type, location, user, and other parameters that malicious actors can potentially target.

See image.

## Analyzing an Asset Risk

The Asset Details page (Assets > Click an asset from the Risky Asset Inventory table) consists of the following sections:

### Asset Risk Score

This section shows the Zscaler-computed risk score for the asset. The following severities appear for the score ranges:

- Low (1–25)
- Medium (26–50)
- High (51–75)
- Critical (76–100)

See image.

### Asset Risk Score Trend

The graph shows the Zscaler-computed risk score trend. Hover over a point in the graph to view the risk score for that date.

See image.

### Asset Details

This section shows the following metadata for the asset:

- **Asset ID**: The unique ID assigned to the asset by the Zscaler service.
- **Username**: The name of the user with whom the asset is associated.
- **Private IP**: The IP address of the asset.
- **Egress IP**: The gateway IP address that sends the local network traffic to other networks on the internet.
- **Location**: The location ID assigned to the asset's workplace. Displays Road Warrior if the asset isn't tied to a location.
- **Department**: The department to which the asset belongs.
- **Asset Type**: The type of asset (iOS, Android OS, etc.).
- **Last Seen**: The time and date when the last activity was observed on the asset.
- **Authentication Status**: Indicates whether the asset is authenticated or not.
- **Enrolled Device Type Version**: The [Zscaler Client Connector version](https://help.zscaler.com/zscaler-client-connector/viewing-information-about-zscaler-client-connector#app-version) installed on the asset.
- **Device Hostname**: The unique ID assigned to the asset to identify it on the internet.
- **OS Type**: The asset's operating system.
- **OS Version**: The operating system version information.

See image.

### Asset Location

This section shows where the asset is located on the map. Hover over the asset to view the city and country information.

See image.

### Events Contributing to Risk Score for Last 7 Days

This section lists the top events observed within the last 7 days that have contributed to the risk score change of the asset. These events are listed in ascending order with the timestamp. For each event, you can view the:

- Threat Category
- Threat Name
- URL Category
- URL Name

You can click the **Export**icon () to download the risky events to a CSV file and click **View All Events** to view all the risky events in a drawer.

See image.

[Image: Asset Details Page]

[Image: Asset Risk Score]

[Image: Asset Risk Score Trend]

[Image: Asset Details]

[Image: Asset Location]

[Image: Events Contributing to Risk for Last 7 Days Section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/assessing-compliance","lastmod":"2026-03-08T23:27Z","nid":"1532479"} -->
## Assessing Compliance

- Source: https://help.zscaler.com/risk360/assessing-compliance
- Product: Risk360
- Path: Assessing Compliance
- Last modified: 2026-03-08T23:27Z
- Summary: Information about risk frameworks and how Zscaler's Risk360 helps you identify and analyze your security stance in accordance with these frameworks.

The integration of various risk frameworks with Risk360 helps you identify, assess, mitigate, and monitor risk, ensuring informed decision-making and abiding by regulatory compliance mandated or recommended based on your organization's geography and industry.

The Risk360 service supports the following risk frameworks:

- ISO27001
- MITRE ATT&CK
- NIST CSF
- NIST SP 800-53
- DORA
- NIS2
- HIPAA
- PCI DSS
- CIS

## Analyzing the Framework

The framework page (Analytics > Risk360> Frameworks > select a framework) shows all the control IDs, maps these IDs to your current Zscaler protections, and provides you with a holistic overview of your organization's risk, as well as in-depth analysis for each technique. The page also shows any policy misconfigurations that are refraining you from securing techniques that can be reconfigured to strengthen your security stance.

Each tile shows the name of the technique and the ID. When you click a tile, it opens all the sub-techniques under it. The tiles highlighted in blue indicate that they are covered by Zscaler protection, and the green or red color at the right side of these tiles indicates whether the protection is configured correctly or not.

You can search for a technique by its name or the factor mapped to it. Click the **Save**icon () to download the framework as a CSV file.

### Overview

The Legend section provides the following overview:

- The donut chart shows the split between the number of techniques covered by the Zscaler service and those that are not. The center of the donut chart shows the total percentage of technique coverage.
- **Configurations**: This section shows the number of configurations or policies that are misconfigured and configured correctly for your organization.
- You can show or hide this section by using the arrow at the top right of this section.

See image.

### Drawer View

Click a technique or sub-technique to view the following information in a drawer view to the right side of the page:

- Techniques
- Sub-Techniques

The ISO 27001 is a set of standards for establishing, implementing, maintaining, and continually improving an information security management system (ISMS) for any organization. This helps your organization become more resilient to cyber attacks, maintain data integrity, confidentiality, and availability, while also achieving significant cost savings.

MITRE ATT&CK is a cybersecurity framework funded by the US government that is used to detect, identify, and classify various tactics, techniques, and procedures (TTPs) used for cyber attacks by attackers. It helps you assess your organization's security posture and calculate the risk of a cyber attack.

The MITRE ATT&CK framework assumes the attacker's point of view to navigate through your organization's network. This helps in highlighting the attacker's journey from the point of access to a potential data exfiltration, among other harmful acts.

To learn more, refer to the [MITRE ATT&CK website](https://attack.mitre.org/).

The National Institute of Standards and Technology (NIST) cybersecurity framework (CSF) is a set of recommendations and processes that you can implement and follow to strengthen your organization's security posture against malicious attackers that also provides guidance on how to respond and recover from a security breach event.
The NIST CSF is considered a very high-standard risk management tool across the industry as it provides great value at any stage of your cybersecurity journey. The Risk360 service supports both versions of NIST CSF (1.1 and 2.0) in the Zscaler Admin Console. You can use both versions of the framework to manage your organization's risk.

The National Institute of Standards and Technology (NIST) Special Publication (SP) 800-53 is a set of structured security and privacy controls applicable specifically to federal information systems and organizations. It provides guidance on how to implement, assess, and strengthen an organization's security posture against malicious attackers while also making them breach ready with respond and recovery catalogs. NIST SP 800-53 helps federal agencies and organizations comply with the mandatory Federal Information Security Management Act (FISMA) and other applicable laws and regulations for security.

While NIST CSF provides a comprehensive set of best practices for organizations to follow, the NIST SP 800-53 provides specific security controls that must be implemented by federal agencies and organizations.

The Network and Information Security Directive 2 (NIS2) is a European Union (EU) law that aims to improve cybersecurity across member states. It is a set of firm requirements for organizations in essential sectors, including risk management, incident reporting, and supply chain security, to create a common, high level of network and information security throughout the EU.

To learn more, refer to the [NIS2 Directive Document](https://eur-lex.europa.eu/legal-content/EN/TXT/PDF/?uri=CELEX:32022L2555).

The Digital Operational Resilience Act (DORA) is a European Union (EU) regulation for the financial sector that mandates strong cybersecurity resilience and risk management for financial entities and critical Information and Communication Technology (ICT) third-party providers.

To learn more, refer to the [DORA Directive Document](https://eur-lex.europa.eu/legal-content/EN/TXT/PDF/?uri=CELEX:32022R2554&from=EN).

The Health Insurance Portability and Accountability Act (HIPAA) is a mandatory compliance framework for the healthcare industry or entities that interact with the United States' patient health information (PHI).

To learn more, refer to the [Health and Human Services Website](https://www.hhs.gov/hipaa/index.html).

The Payment Card Industry Data Security Standard (PCI DSS) is a mandatory security framework for all entities that store, process, or transmit cardholder's payment data.

To learn more, refer to the [PCI Security Standards Website](https://www.pcisecuritystandards.org/).

The Center for Internet Security (CIS) Critical Security Controls are a prescriptive, tactical, prioritized, and simplified set of best practices designed to strengthen your cybersecurity posture.

To learn more, refer to the [CIS Website](https://www.cisecurity.org/controls).

- The name of the technique, its ID, and the state of the sub-techniques (i.e., whether they are Zscaler protected or not).
- **Details**: A link that opens a PDF file that explains the technique in detail.
- **Description**: A description of the technique. This field name differs depending on the framework you're viewing (e.g., NIST Description and ISO 27001 Description).
- **Zscaler Comment**: A note on how Zscaler can help mitigate the risk from this attack technique by using one of the Zscaler progressive protection portfolios. If the mitigation strategy isn't available, this field shows no information.
- **TTP to Zscaler Product Mapping**: Maps the attack technique to the Zscaler feature responsible for protecting against these attack techniques, whether the features that are responsible for protecting against these tactics, techniques, and procedures (TTPs) are licensed by your organization or not, and the Risk360 category that the TTP falls under.
- **TTP to Risk360 Factor Mapping**: Maps all the attack sub-techniques to the [Risk360 Factors](https://help.zscaler.com/risk360/about-factors) and shows the status of each sub-technique.
- **Notes**: Any notes that you added for the technique. Click the **Edit**or **Delete**icon to modify or delete the note, respectively.

See image.

- The name of the sub-technique, its ID, and its state, whether or not Zscaler protects against it.
- **Details**: A link that opens the PDF file where the technique is explained in detail.
- **Description**: A description of the technique. This field name differs depending on the framework you're viewing (e.g., NIST Description and ISO 27001 Description)
- **Zscaler Comment**: A note on how Zscaler can help mitigate the risk from this attack technique by using one of the Zscaler progressive protection portfolios. This field shows no information if the mitigation strategy isn't available.
- **TTP to 3rd Party Tools Mapping**: Shows whether the sub-technique is securely configured, misconfigured, or not covered by the any third-party security control policies.
- **Notes**: Any notes that you added for the technique. Click the **Edit**or **Delete**icon to modify or delete the note, respectively.

See image.

### Hover-Over View

Hover over a technique or sub-technique tile to view the following information:

- Whether Zscaler protects your organization against this attack technique, and if so, whether the protections against these attack techniques are configured correctly.
- **Zscaler Control**: The Zscaler feature that is responsible for helping protect against these attack techniques.
- **Related Risk360 Factors**: The [Risk360 Factors](https://help.zscaler.com/risk360/about-factors) that are related to the attack.
- **Licensed?**: Whether or not you are subscribed to the Zscaler feature that protects against these attacks.
- **Notes**: Any notes that you added for the technique.

See image.

[Image: Sub-technique Drawer in NIST SP 800-53 framework]

[Image: TTP Drawer in NIST SP 800-53 framework]

[Image: Hover dialog in NIST SP 800-53 framework]

[Image: Legend Section in NIST SP 800-53 framework]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/configuring-alert-rule","lastmod":"2025-02-25T08:00Z","nid":"1483201"} -->
## Configuring an Alert Rule

- Source: https://help.zscaler.com/risk360/configuring-alert-rule
- Product: Risk360
- Path: Configuring an Alert Rule
- Last modified: 2025-02-25T08:00Z
- Summary: How to configure alert rules in the Risk360 Admin Portal.

You can configure alert rules for various criteria (i.e., financial loss or risk score changes at organization, category, factor group, or factor levels) that trigger an email or webhook notification to the recipients.

To configure an alert rule:

1. Go to **Alerts** > **Rules**.
2. Click **Add Alert Rule**.
3. Choose an existing rule template or start configuring from the beginning by clicking **Create New**. See image. The **Add Alert Rule** wizard appears.
4. In the **Add Alert Rule** wizard, complete the following steps: The alert rule is successfully created.
  - a. Define Criteria & Throttling
  - b. Configure Delivery Method

If you've selected a rule template, the service skips to the Configure Delivery Method section. However, you can click **Back**to edit the predefined values in the **Define Criteria &** **Throttling** section. In this section:

- **Criteria**: Set the criteria for the alert rule to be triggered:
  1. Select **All** or **Any** from the drop-down menu.
  2. Select the item for which you want to trigger the alert from **Org**, **Category**, **Factor Group**, or**Factor**.
  3. Based on your selection, the next drop-down menus are auto-populated. Select the required options.
  4. Select the operator for the criteria, i.e., equal to (**=**), greater than (**>**), less than (**<**), greater than or equal to (**≥**), less than or equal to (**≤**), **increases by**, or **decreases by**.
  5. Enter the value for the operator.
  6. Click **Add** to add another criterion to the rule. For example, see the following GIF to understand how to set the criteria for an alert to be triggered when the risk score for the Data Loss category exceeds 55. See image.
- **Expression Preview**: You can view a logical preview for the criteria set in the preceding fields. This field is uneditable.
- **Minimum Alert Throttling Criteria**: Enter the number of days the criteria must persist before triggering the alert notification.
- Click **Next**.

See image.

In the **Configure Delivery Method**section:

- **Rule Name**: Enter a name to identify the rule.
- **Severity**: Select the severity of the rule from **Critical**, **High**, **Medium**, or **Low**.
- **Status**: Select **Enabled** or **Disabled** for the rule.
- **Delivery Method**: Select the alert delivery methods from **Email**and**Webhook.**
- **Webhooks**: Select from the existing configured webhooks or configure a new webhook by clicking **Configure Webhooks** to receive alerts via webhooks. To learn more, see [Configuring Webhooks](https://help.zscaler.com/risk360/configuring-webhooks).
- **Email Recipient**: Enter the email address to which you want the alerts to be sent. To learn more about the information sent, see [Understanding the Alert Email](https://help.zscaler.com/risk360/understanding-alert-email).
- **Custom Message**: Enter a custom message that is displayed within the alert notification when this alert is triggered. This message is applicable for both email and webhooks.
- Click **Add**.

See image.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/configuring-saml-admins-risk360","lastmod":"2024-10-28T21:39Z","nid":"1461806"} -->
## Configuring SAML for Admins in Risk360

- Source: https://help.zscaler.com/risk360/configuring-saml-admins-risk360
- Product: Risk360
- Path: Configuring SAML for Admins in Risk360
- Last modified: 2024-10-28T21:39Z
- Summary: Information about how to configure SAML for admins in the Risk360 Admin Portal.

The Zscaler service supports identity provider (IdP)-initiated SAML to authenticate admins. The admin can log in to the Risk360 Admin Portal directly from a Single Sign-On (SSO) provider's portal by clicking the Zscaler application icon. This feature also enables you to integrate admin authentication with your existing two-factor authentication solution.

Admins are not added through auto-provisioning. Rather, an admin must be added in the Risk360 Admin Portal and can then use SAML authentication to log into it. The Zscaler service does provide a password authentication option for admins, but the Zscaler service recommends that admins use SAML authentication to log in to the Risk360 Admin Portal. However, the service also recommends that you have at least one super admin with password authentication enabled to ensure an admin can still access the Risk360 Admin Portal if SAML servers external to the Zscaler service become unreachable. The Zscaler service supports SAML 2.0 and later.

## Prerequisites

Ensure you do the following before configuring SAML SSO for admins:

- Create admin accounts for your organization's admins. To learn more, see [Adding Admins](https://help.zscaler.com/risk360/adding-admins-risk360).
- Configure an IdP, such as [Okta](https://help.zscaler.com/risk360/saml-configuration-guide-okta), [Microsoft Entra ID](https://help.zscaler.com/risk360/saml-configuration-guide-entra-id), etc.
- Obtain the SAML certificate of the IdP. You upload this certificate to the Risk360 Admin Portal when you configure the service to use SAML.

If you are subscribed to the Authentication Service service, the Administrator Management page shows a link to the Authentication Service Admin Portal. You can manage your admins from the [Authentication Service Admin Portal](https://help.zscaler.com/zidentity/accessing-and-navigating-zidentity-admin-portal). To learn more, see [What Is Authentication Service?](https://help.zscaler.com/zidentity/what-zidentity)
See image.

## Configuring SAML SSO for Admins

To configure SAML SSO for admins in the Risk360 Admin Portal:

1. Go to**Administration**>**Administrator Management**.
2. Click the **Administrator Management** tab.
3. In the **SAML Authentication for Administrators** section:
  - **Enable** **SAML Authentication**: Enable to allow admins to log in to the Risk360 Admin Portal directly from your SSO provider portal. You must have already configured an IdP (e.g., ADFS, Okta, etc.) for your organization and added the admin account in the Risk360 Admin Portal (instead of using auto-provisioning).
  - **IdP SAML Certificate**:Upload the SAML public certificate that is used to verify the digital signature of the IdP. This is the base-64 encoded PEM format that you downloaded from the IdP. The file extension must be .pem or .cer and have no other periods (.) in the file name.

See image.

- Change the Windows Folder Properties to View and Edit Extensions

1. Start Windows 10 Control Panel.
2. Go to **Appearance & Personalization** > **File Explorer Options**.
3. When the **File Explorer Options** window appears, click the **View**tab.
4. In **Advanced settings:**, deselect **Hide extensions for known file types** to view extensions and click **OK**.
5. Rename the certificate to change the extension.

- **Download XML Metadata**:Download the XML metadata of the Zscaler service. The metadata details Zscaler SAML capabilities and is used for auto-configuration. Some IdPs require the metadata to configure service providers.
- **Issuer**: (Optional) Enter the IdP issuer associated with the Zscaler service, and click **Add Items**. You can enter multiple entries. Press `Enter` after each entry. You can add up to custom 25,000 URLs (across all categories). For item lists, you can view up to 500 items on a page; filter the list by searching for a word, phrase, or number contained in an item; and remove all items from the list (**Remove All**) or only items from a specific page (**Remove Page**). If you select **Remove All** or **Remove Page**, a confirmation window appears.

See image.

1. Click **Save** and [activate the change](https://help.zscaler.com/risk360/saving-and-activating-changes-risk360-admin-portal).

[Image: Screenshot highlighting the Upload button in the IdP Public SSL Certificate window.]

[Image: Screenshot of the SAML Authentication for Administrators section on the Administrator Management page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/configuring-webhooks","lastmod":"2025-11-18T23:19Z","nid":"1483191"} -->
## Configuring Webhooks

- Source: https://help.zscaler.com/risk360/configuring-webhooks
- Product: Risk360
- Path: Configuring Webhooks
- Last modified: 2025-11-18T23:19Z
- Summary: Information on configuring webhooks to deliver alerts.

You can configure and use webhooks in an alert rule and assign multiple alert rules to the same webhook from your third-party provider for alert delivery.

To configure a webhook:

1. Go to **Alerts**> **Webhooks** > **Add Webhook**. The **Add New Webhook** drawer appears.
2. In the **Add New Webhook** drawer:
  - **Name**: Enter the name of the webhook.
  - **Status**: Select **Enabled** or **Disabled**for the webhook.
  - **URL**: Enter the URL of the webhook provider. Ensure the URL does not include any spaces.
  - **Authentication Type**: Select the authentication type for the webhook from **Basic**or **Token**. The webhook provider determines the authentication type used. Refer to your provider for details. See image.
    - Basic
    - Token
3. Click **Save** to save the webhook configuration. If the webhook is configured successfully, the [Alert Status](https://help.zscaler.com/risk360/about-alerts) field on the Webhooks page displays **Active**. If not, the field displays **Error**. To resolve the error, check for issues in the **URL** or the **Authentication Type** fields.

Enter a **Username**and**Password**. Password information is hidden by default. You can view it by clicking the **View** icon.

Enter the bearer token. A bearer token is a unique alphanumeric string used for authentication. You can obtain the bearer token from your webhook provider.

[Image: Add Webhook Drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/customizing-your-admin-account-settings","lastmod":"2023-11-07T05:26Z","nid":"1461906"} -->
## Customizing Your Admin Account Settings

- Source: https://help.zscaler.com/risk360/customizing-your-admin-account-settings
- Product: Risk360
- Path: Customizing Your Admin Account Settings
- Last modified: 2023-11-07T05:26Z
- Summary: How to customize your admin account settings in the Risk360 Admin Portal.

The Risk360 service automatically creates a profile for each admin account where you can change your password, admin portal color theme, etc.

- Applying a Color Theme to the Risk360 Admin Portal for Your Profile
- Viewing or Customizing Your Profile

To apply a color them to the Risk360 Admin Portal for your profile:

1. Go to any of the Administration pages. The left-side navigation is updated to show the **User**icon.
2. Hover over the **User** icon to view the **Account** pane.
3. Choose one of the following color themes for your Risk360 Admin Portal.
  - Default
  - Blue
  - Dark blue

The selected color theme is only applicable to your admin profile.

See image.

To view or customize your Risk360 Admin Portal profile:

1. Go to any of the Administration pages. The left-side navigation is updated to show the **User**icon.
2. Click the **User** icon.

The**My Profile** page appears:

1. On the **My Profile** page:
  - **User Name**: Your login ID assigned when your admin account was created.
  - **Password**: Change your password for the Risk360 Admin Portals. The Zscaler service applies the strongest restrictions and complexity. The password must contain at least 8 characters and include one number, one uppercase character, and one special character. Only ASCII characters are allowed.
  - **Language**: By default, the Risk360 Admin Portal is displayed in English. You can change the language to French, German, Japanese, Spanish, or Simplified Chinese.
  - **Auto Refresh Dashboard?**: Enable the dashboards to automatically refresh every 15 minutes.
  - **Policy Information**: Enable to display policy descriptions at the top of each policy window in the Risk360 Admin Portal.
  - **Timezone**: When the Zscaler service saves transactions, it uses UTC (Coordinated Universal Time). The time zone is used only for exporting logs to CSV files. It does not affect the generated logs in the Risk360 Admin Portal because the system time zone determines the time stamp of the generated logs.

See image.

1. Click **Save** and [activate the change](https://help.zscaler.com/risk360/saving-and-activating-changes-risk360-admin-portal.).

[Image: Screenshot of Zscaler admin portal user icon]

[Image: The My Profile page in the ZIA Admin Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/downloading-risk-reports","lastmod":"2025-11-16T06:06Z","nid":"1461841"} -->
## Downloading Risk Reports

- Source: https://help.zscaler.com/risk360/downloading-risk-reports
- Product: Risk360
- Path: Downloading Risk Reports
- Last modified: 2025-11-16T06:06Z
- Summary: Information about how to download various risk reports in the Risk360 Admin Portal.

You can download various risk-related reports from the Reports page. The page contains the following reports and documents:

- **CISO Board Slides**: The slides provide customers with extensive insight into how Risk360 is helping quantify and measure risk in their network. A new slide is generated for a period of 7 days. The reports are securely stored as a PowerPoint file.
- **Attack Surface Report**: A report that provides you with the details of your organization's exposed applications and servers to a public network, such as the internet, and their possible exploitation.
- **Cybersecurity Maturity Assessment**: A report that provides a holistic view of your zero trust journey. It is generated by a custom large language model (LLM) developed by Zscaler.
- **SEC Disclosures**: A document with a sample format and content that can be a helpful starting point for security and legal teams in addressing the Securities and Exchange Commission's (SEC's) new cyber risk reporting regulations.
- **Miscellaneous**: Various materials on risk management, including books published on how board members can manage cyber risk.
- **Risk Management Mitigation Strategy Report**: A report that outlines the mitigation strategy for important risk factors impacting your organization's risk. This report is autogenerated once a week.
- **Operating Entity Risk**: A weekly and on-demand report for portfolio risk assessment by quantifying risk at the operating entity level. You can click the **Settings**icon to change the entity source for the report. To learn more, see [Managing Operating Entity Risk Report Settings](https://help.zscaler.com/risk360/managing-operating-entity-risk-report-settings).
- **Cyber Insurance Addendum**: A comprehensive quarterly and on-demand report of your zero trust security posture for cyber insurance underwriters.

Click **View All** to view all the reports for a specific category.

[Image: Reports page in the Risk360 Admin Portal.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/editing-or-deleting-items-risk360","lastmod":"2024-09-30T06:34Z","nid":"1461851"} -->
## Editing or Deleting Items in Risk360

- Source: https://help.zscaler.com/risk360/editing-or-deleting-items-risk360
- Product: Risk360
- Path: Editing or Deleting Items in Risk360
- Last modified: 2024-09-30T06:34Z
- Summary: How to edit or delete items in the Risk360 Admin Portal.

In the Risk360 Admin Portal, you can edit, or delete items you've configured. For example, you can do so with administrative items such as admins and roles you configure in the Risk360 Admin Portal.

## Editing Items

To edit items:

1. Navigate to the item you want to edit. In this example, it's an admin.
2. Click the **Edit** icon for that item.
3. Make your edits and save the changes.

See image.

## Deleting Items

To delete items:

1. Navigate to the item you want to delete. In this example, it's an admin.
2. Click the ellipsis for that item.
3. Click **Delete**.

See image.

[Image: Delete Button]

1. A confirmation message appears. If you are sure you want to delete the item, click **Delete**.

See image.

[Image: Deleting Item Warning Screenshot]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/exploring-problems","lastmod":"2024-02-16T06:23Z","nid":"1458621"} -->
## Exploring Problems

- Source: https://help.zscaler.com/risk360/exploring-problems
- Product: Risk360
- Path: Exploring Problems
- Last modified: 2024-02-16T06:23Z
- Summary: Information on how to explore a risk problem on the Explore page in the Risk360 Portal.

The Explore page (Insights > click the Explore link on a problem card) shows in-depth analysis of a problem by presenting multiple sections highlighting the affected areas within your organization. The following screenshot illustrates an example problem analyzed on the Explore page. You can scroll down to view all the sections available for that problem.

[Image: The Explore page in the Risk360 Portal]

Click the Investigate link available for each section and you're redirected to the [Investigate](https://help.zscaler.com/risk360/investigating-sections-problem) page where you can view detailed data for the sections in a tabular form.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/integrating-3rd-party-connectors-risk-factors","lastmod":"2025-07-27T07:06Z","nid":"1529546"} -->
## Integrating 3rd-Party Connectors for Risk Factors

- Source: https://help.zscaler.com/risk360/integrating-3rd-party-connectors-risk-factors
- Product: Risk360
- Path: Integrating 3rd-Party Connectors for Risk Factors
- Last modified: 2025-07-27T07:06Z
- Summary: Information about 3rd-party app integration for risk factors in the Risk360 Admin Portal.

The Risk360 service's integration with Zscaler Data Fabric helps in providing better security and a risk assessment that creates a holistic risk quantification environment observed for Zscaler and other third-party technologies. Data Fabric ingests Zscaler and third-party data sources and then harmonizes, deduplicates, correlates, and enriches the ingested data that is used in the Risk360 service for risk quantification. You can configure connectors in the Data Fabric to ingest third-party data sources.

The following out-of-the-box risk factors based on third-party data sources are available in the Risk360 Admin Portal with a connector configuration:

| Factor Name | Connector Name |
| --- | --- |
| CrowdStrike - Zero Trust Score | CrowdStrike Crowdscore |
| CrowdStrike - Endpoint Security CrowdScore | CrowdStrike Crowdscore |
| CrowdStrike - Unsupported Devices | CrowdStrike Environment Assets |
| CrowdStrike - Unmanaged Devices | CrowdStrike Environment Assets |
| CrowdStrike - End-of-Life Operating System | CrowdStrike Environment Assets |
| CrowdStrike - Critical Domain Users Having High Privileges | CrowdStrike Identity Protection - Domain Users |
| CrowdStrike - Identity Protection for Active Directory | CrowdStrike Identity Protection - Security Assessment |
| CrowdStrike - Critical and High Incidents | CrowdStrike Incidents |
| CrowdStrike - High Severity XDR Detections | CrowdStrike Alerts |
| Critical and High Severity Vulnerabilities | CrowdStrike Vulnerabilities Qualys Vulnerabilities Tenable Vulnerability Management Rapid7 InsightVM Wiz Vulnerability Findings Microsoft Defender for Endpoints - Vulnerabilities |
| Highly Exploitable Vulnerabilities | CrowdStrike Vulnerabilities Qualys Vulnerabilities Tenable Vulnerability Management Rapid7 InsightVM Wiz Vulnerability Findings Microsoft Defender for Endpoints - Vulnerabilities |
| Unaddressed Critical and High Severity Vulnerabilities | CrowdStrike Vulnerabilities Qualys Vulnerabilities Tenable Vulnerability Management Rapid7 InsightVM Wiz Vulnerability Findings Microsoft Defender for Endpoints - Vulnerabilities |

Reach out to your Zscaler Account team to define and implement custom risk factors not listed in the preceding table to serve your organization's specific requirements.

Refer to the following deployment guides for configuring connectors in Zscaler Data Fabric to implement the preceding factors:

- Configuration Guides

| Connector | Configuration Guide |
| --- | --- |
| Microsoft Defender for Endpoints - Vulnerabilities | [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide) |
| CrowdStrike Incidents CrowdStrike Alerts CrowdStrike Environment Assets CrowdStrike Vulnerabilities | [Zscaler and CrowdStrike Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-crowdstrike-deployment-guide) |
| Qualys Vulnerabilities | [Zscaler UVM and Qualys Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-qualys-deployment-guide) |
| Tenable Vulnerability Management | [Zscaler UVM and Tenable Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-tenable-deployment-guide) |
| Wiz Vulnerability Findings | [Zscaler UVM and Wiz Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-wiz-deployment-guide) |
| Rapid7 InsightVM | [Zscaler and Rapid7 Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-rapid7-deployment-guide) |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/investigating-sections-problem","lastmod":"2025-06-18T07:19Z","nid":"1461846"} -->
## Investigating Sections of a Problem

- Source: https://help.zscaler.com/risk360/investigating-sections-problem
- Product: Risk360
- Path: Investigating Sections of a Problem
- Last modified: 2025-06-18T07:19Z
- Summary: Information about how to investigate the Explore page sections in the Risk360 Admin Portal.

When you click on the Investigate link for a section on the [Explore](https://help.zscaler.com/risk360/exploring-problems) page, you are redirected to the Investigate page where you can view the data shown in the section in detail for each asset involved in the section (e.g., server, ports, etc.). You can filter the table using the available filter options to narrow your search. The filter options vary based on the section that you investigate.

If available, you can include or exclude an entity in the table from risk score computation by providing an explanation:

1. Enable or disable the **Include** toggle of your choice. See image.
2. Click **Save**. The **Entity Override Note** window appears.
3. In the **Entity Override Note** window, provide an explanation as to why you chose to include or exclude the entity. See image.
4. Click **Save**. You can modify the **Include** toggle for multiple entities at once and provide an explanation in the same **Entity Override Note** window.

The changes made by the admin are captured in the [audit logs](https://help.zscaler.com/risk360/about-audit-logs) with the username and reason provided for the override.

The following screenshot illustrates an example section from a problem analyzed on its Investigate page:
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/managing-financial-risk-settings","lastmod":"2025-09-11T23:02Z","nid":"1504426"} -->
## Managing Financial Risk Settings

- Source: https://help.zscaler.com/risk360/managing-financial-risk-settings
- Product: Risk360
- Path: Managing Financial Risk Settings
- Last modified: 2025-09-11T23:02Z
- Summary: Information on managing financial risk settings in the Risk360 Admin Portal.

You can customize the parameters of the financial model that determines the financial exposure of your organization in Risk360.

To customize the parameters:

1. Go to the **Financial Risk** > **Settings**page. The **Financial Risk Settings** drawer appears.
2. Under the**Default Values**, you can view the default values set for your organization during onboarding. To customize these values, under **Customized Values**: See image.
  - **Industry Vertical**: Select the industry vertical that best represents your organization.
  - **Industry Annual Revenue Range**: Select the annual revenue range for the organization's industry vertical.
  - **Financial Loss Range**: Enter the lower and upper bounds of the financial loss, in case your organization faces a breach. These values are used for simulating financial loss estimations within the set range.
3. Click **Save**. You can click **Reset to Default** if you want the Risk360 service to use the default values for calculation.

The [Financial Risk](https://help.zscaler.com/risk360/about-financial-risk) model inherits the latest values from the preceding settings to perform loss estimations.

[Image: The Financial Risk Settings showing the fields for the Default Values and Customized Values]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/managing-operating-entity-risk-report-settings","lastmod":"2025-10-25T07:06Z","nid":"1529930"} -->
## Managing Operating Entity Risk Report Settings

- Source: https://help.zscaler.com/risk360/managing-operating-entity-risk-report-settings
- Product: Risk360
- Path: Managing Operating Entity Risk Report Settings
- Last modified: 2025-10-25T07:06Z
- Summary: Information on managing the Operating Entity settings in the Risk360 Admin Portal.

You can customize the operating entity settings to set the entity source based on which Operating Entity Risk Report is generated for your organization.

To customize the parameters:

1. Go to the **Account** page.
2. In the **Operating Entity Settings** section: See image.
  1. **User-to-Operating Entity Mapping Source**: Select the operating entity source. Currently, only Zscaler Internet Access (ZIA) is supported as the source.
  2. **Operating Entity Type**: Select the entity type as **Departments** or **Groups**.
3. Click **Save**.

The settings are successfully saved, and the next [Operating Entity Risk Report](https://help.zscaler.com/risk360/downloading-risk-reports) is generated based on the newly saved settings.

[Image: Operating Entity Settings on the Account page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/managing-peer-score-settings","lastmod":"2025-08-15T07:06Z","nid":"1529151"} -->
## Managing Peer Score Settings

- Source: https://help.zscaler.com/risk360/managing-peer-score-settings
- Product: Risk360
- Path: Managing Peer Score Settings
- Last modified: 2025-08-15T07:06Z
- Summary: Information on managing peer score settings in the Risk360 Admin Portal.

The Peer Score Settings drawer (Dashboard > click the Settings icon in the Risk Score Trend section) shows the strategy selected for calculating your industry's peer risk score. The defaultstrategy is Zscaler-defined. You can add, modify, or delete custom strategies for peer score calculation.

## Adding a Strategy

To add a new peer score calculation strategy:

1. Click **Add Custom Strategy**. The **Add Custom Strategy** drawer appears.
2. In the **Add Custom Strategy** drawer, enter a name for the strategy in the **Name** field.
3. Click **Add Attribute** to select from the following attributes in the strategy: See image.
  - **Industry Vertical**: Select your industry vertical from the drop-down menu.
  - **Geographical Region**: Select your organization's geographical region from the drop-down menu.
  - **Revenue ID**: Select your organization's revenue range from the drop-down menu.
4. Click **Save**. The strategy is successfully added to the list of strategies.
5. Select the strategy and click **Save**to use this strategy for peer score calculations.

## Modifying a Strategy

1. Click the **Edit**icon for the strategy you want to modify. The **Edit Custom Strategy** drawer appears. See image.
2. In the **Edit Custom Strategy** drawer, make changes to the strategy. See image.
3. Click **Save**. The strategy is successfully modified.

## Deleting a Strategy

1. Click the **Delete** icon for the strategy you want to remove. See image.
2. Click **Confirm**in the dialog box that appears. See image.
3. Click **Save**. The strategy is successfully deleted. If you delete a strategy that is enabled for peer score calculation, the Zscaler service enables the default strategy for calculation. You cannot delete the default strategy, you can only modify it.

[Image: Add Custom Strategy Drawer for Peer Score Calculation]

[Image: Editing a Strategy in the Peer Score Settings]

[Image: Edit Custom Strategy Drawer]

[Image: Deleting a Strategy in the Peer Score Settings]

[Image: Delete Strategy confirmation dialog box]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/managing-your-admin-account","lastmod":"2025-09-11T20:49Z","nid":"1461836"} -->
## Viewing and Managing Your Admin Account

- Source: https://help.zscaler.com/risk360/managing-your-admin-account
- Product: Risk360
- Path: Viewing and Managing Your Admin Account
- Last modified: 2025-09-11T20:49Z
- Summary: Information on how to view your admin account details and change your password in the Risk360 Admin Portal.

The Account page displays your basic account information (i.e., username, organization ID, cloud name, and current SKU) for your Risk360 Admin Portal account.

See image.

You can also update the following settings on this page.

## Changing Password

To change your password:

1. Go to **My Profile**.
2. Click **Change Password**.

The **Change Password** window appears.

1. In the **Change Password** window:
  - **Old Password**: Enter your current password used for logging in to the Risk360 Admin Portal.
  - **New Password**: Enter a new password.
2. Click **Save**. After you change your password, the current Risk360 Admin Portal session expires and you are prompted to log in using the new password..

## Operating Entity Settings

You can update the operating entity settings to set the entity source based on which Operating Entity Risk Report is generated for your organization. To learn more, see [Managing Operating Entity Risk Report Settings](https://help.zscaler.com/risk360/managing-operating-entity-risk-report-settings).

[Image: My Profile Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/quantifying-loss-monte-carlo-simulation","lastmod":"2025-06-30T22:20Z","nid":"1472081"} -->
## Quantifying Loss With Monte Carlo Simulation

- Source: https://help.zscaler.com/risk360/quantifying-loss-monte-carlo-simulation
- Product: Risk360
- Path: Quantifying Loss With Monte Carlo Simulation
- Last modified: 2025-06-30T22:20Z
- Summary: Information about Monte Carlo Simulation and how it helps you quantify the probability of exceeding loss in millions of dollars.

The Monte Carlo Simulation is a renowned method to determine the probability of an outcome from a range of outcomes with a random set of variables as the source of uncertainty. This simulation helps organizations quantify various risk-related parameters.

The Risk360 service runs the Monte Carlo simulations 1,000 times. In each simulation iteration, the service measures the financial loss based on a randomized cyber breach event and a randomized financial loss within a predefined confidence interval defined by the lower and upper bounds of a loss when a breach occurs. The result of the simulations is used to estimate the yearly average loss and the loss exceedance curve (i.e., the curve that shows the probability that a loss exceeds a certain amount). The process is repeated 4 times to calculate the yearly average loss and the loss exceedance curve under the following 4 distinct scenarios:

- **Inherent Risk**: The current risk score of the organization.
- **Residual Risk**: The risk score of an organization after mitigating the top 10 risk factors.
- **Last 30 Days Average Risk**: The average risk score of an organization in the last 30 days.
- **Industry Peer Risk**: The average risk score of peer organizations.

## Analyzing the Monte Carlo Simulation

The Monte Carlo Simulation page (Financial Risk > Monte Carlo Simulation) consists of the following metrics to help you analyze the simulated results:

### Loss Exceedance

This graph shows the probability of exceeding loss values (in percentage) in millions of dollars. The graph shows the simulation across 4 risk parameters:

- **Inherent Risk (Current Risk Score)**: This simulation is based on your current organization's risk score. This shows the probability of loss based on your overall organization risk score.
- **Residual Risk (Risk score after addressing top 10 factors)**: The residual risk is the risk score obtained after rectifying the 10 [Risk360 Factors](https://help.zscaler.com/risk360/about-factors). This simulation helps you see the financial impact of addressing the top 10 [Risk360 Factors](https://help.zscaler.com/risk360/about-factors).
- **Last 30 Days Average Risk**: This simulation is based on the average risk of the last 30 days. This helps you see the impact of changes in risk score for the last 30 days.
- **Industry Peer Risk**: This simulation is based on the industry peer risk score. This helps you in comparing loss probability to your industry peers.

Hover over a risk parameter curve in the graph to view the probability of exceeding loss for a certain amount (in millions of dollars).

[Image: Monte Carlo Simulation Graph]

### Average Yearly Exposure

This bar graph shows the average yearly loss in millions of dollars for each of the risk parameters.

[Image: Yearly Average Loss Graph in Monte Carlo Simulation Page]

### Breach Probability (%)

This section shows the breach probability percentage calculated based on your organization's inherent risks, last 30 days average risk, residual risk, and industry peer risk. The breach probability is also an input parameter for your organization's financial exposure calculation. The value of Zscaler-computed breach probability is continuously updated and determined based on the industry vertical, annual revenue range, and the organization's risk score.

You can enable the **Simulate**toggle to edit the probability for the desired risk parameter. After you click **Apply**, your organization's financial exposure is reevaluated based on your input. Disable the toggle to restore the Zscaler-computed probabilities.

[Image: Breach Probability in Monte Carlo Simulation]

### Simulation Results

Click **View All Iterations** to open the Simulation Results drawer. The drawer consists of 4 tabs for each risk parameter. Each tab shows the results for all the 1,000 iterations run by the Monte Carlo Simulation.

[Image: Drawer view in Monte Carlo Simulation]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/release-upgrade-summary-2023","lastmod":"2026-01-28T01:32Z","nid":"1461421"} -->
## Release Upgrade Summary (2023)

- Source: https://help.zscaler.com/risk360/release-upgrade-summary-2023
- Product: Risk360
- Path: Risk360 Help > Release Notes > Release Upgrade Summary (2023)
- Last modified: 2026-01-28T01:32Z
- Summary: Risk360 Release Upgrade Summary for service updates deployed in 2023.

This article provides a summary of all new features and enhancements for Risk360. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com/).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/release-upgrade-summary-2024","lastmod":"2025-01-02T16:16Z","nid":"1473536"} -->
## Release Upgrade Summary (2024)

- Source: https://help.zscaler.com/risk360/release-upgrade-summary-2024
- Product: Risk360
- Path: Risk360 Help > Release Notes > Release Upgrade Summary (2024)
- Last modified: 2025-01-02T16:16Z
- Summary: Risk360 Release Upgrade Summary for service updates deployed in 2024.

This article provides a summary of all new features and enhancements for Risk360.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/release-upgrade-summary-2025","lastmod":"2025-08-25T15:09Z","nid":"1516021"} -->
## Release Upgrade Summary (2025)

- Source: https://help.zscaler.com/risk360/release-upgrade-summary-2025
- Product: Risk360
- Path: Risk360 Help > Release Notes > Release Upgrade Summary (2025)
- Last modified: 2025-08-25T15:09Z
- Summary: Risk360 Advanced Release Upgrade Summary for service updates deployed in 2025.

This article provides a summary of all new features and enhancements for Risk360 Advanced.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/release-upgrade-summary-2026","lastmod":"2026-08-13T22:39Z","nid":"1534327"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/risk360/release-upgrade-summary-2026
- Product: Risk360
- Path: Risk360 Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-08-13T22:39Z
- Summary: Risk360 Advanced Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Risk360 Advanced.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/saml-configuration-guide-entra-id","lastmod":"2024-07-02T20:04Z","nid":"1470391"} -->
## SAML Configuration Guide for Microsoft Entra ID

- Source: https://help.zscaler.com/risk360/saml-configuration-guide-entra-id
- Product: Risk360
- Path: SAML Configuration Guide for Microsoft Entra ID
- Last modified: 2024-07-02T20:04Z
- Summary: How to configure SAML SSO for Risk360 with Microsoft Entra ID.

html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd"

The Risk360 service supports identity provider (IdP)-initiated SAML authentication for admins. The admin can log in to the [Risk360 Admin Portal](https://help.zscaler.com/risk360/accessing-and-navigating-risk-360-admin-portal) directly from a single sign-on (SSO) provider's portal by clicking the Risk360 application icon. This guide illustrates how to configure SAML SSO with Microsoft Entra ID for the Risk360 service.

## Prerequisites

Ensure that you have the following before you start configuring Microsoft Entra ID:

- An Microsoft Entra ID account with admin privileges.
- Admins created for Risk360 service in Microsoft Entra ID.

## Configuring SAML SSO with Microsoft Entra ID

To configure Microsoft Entra ID SAML SSO for Risk360:

- 1. Create Risk360 App integration in Microsoft Entra ID.
- 2. Assign admins to Risk360.
- 3. Download the SAML signing certificate from Microsoft Entra ID.
- [4. Configure SAML SSO in the Risk360 Admin Portal.](https://help.zscaler.com/risk360/configuring-saml-admins-risk360)

After you complete the preceding steps, admins can access the Risk360 Admin Portal using their Microsoft Entra ID credentials or the Risk360 tile on their apps dashboard in the [My Apps](https://myapps.microsoft.com/) portal..

See image.

To add the Risk360 application to Microsoft Entra ID:

1. Log in to the [Microsoft Entra portal](https://entra.microsoft.com/) portal.
2. Go to **Identity > Applications > Enterprise applications** from the left-side navigation. See image.
3. Click **New application**, then click **Create your own application**. The **Create your own application** window appears.
4. In the **Create your own application** window: See image. The Microsoft Entra ID service displays a notification that the application is added, and you are redirected to the application's **Overview** page.
  1. Enter an application name for the Risk360 service in the **What's the name of your app?** field. For example, enter `Risk360`.
  2. Select the **Integrate any other application you don't find in the gallery (Non-gallery)** option.
  3. Click **Create**.
5. From the left-side navigation, click **Single sign-on**, then click **SAML**. See image. The **Set up Single Sign-on with SAML** page appears.
6. In the **Basic SAML Configuration**section, click **Edit** and complete the following fields:
  1. **Identifier (Entity ID)**: Enter the entity ID. You can find this information by downloading the XML metadata file from the **Download XML Metadata**field on the Administrator Management page (**Administration**> **Administrator Management**) in the Risk360 Admin Portal. This ID is specific to your IdP. See image.
  2. **Reply URL (Assertion Customer Service URL)**: Enter the Reply URL. You can find this information by downloading the XML metadata file from the **Download XML Metadata**field on the Administrator Management page (**Administration**> **Administrator Management**) in the Risk360 Admin Portal. See image.
  3. **Relay State**: Enter your Risk360 cloud name. You can view the cloud from the My Profile page in the Risk360 Admin Portal. See image.
  4. Click **Save** and exit the window.

See image.

To assign admins to the Risk360 application:

1. From the left-side navigation, go to **Identity > Applications > Enterprise applications**.
2. Search for and open the Risk360 application.
3. From the left-side navigation, click **Users and groups**, then **Add user/group**. See image.

The **Users and groups** window appears.

1. Search for the user or group you want to assign to the Risk360 service.
2. Select the checkbox next to the user or group names you want to assign to the Risk360 service, then click **Select**. See image.
3. In the **Add Assignment** panel, click **Assign**.

See image.

You are redirected to the **Users and groups** page where you can see the users are successfully assigned to Risk360.

See image.

To download the SAML signing certificate:

1. From the left-side navigation, go to **Identity > Applications > Enterprise applications**.
2. Search for and open the Risk360 application.
3. From the left-side navigation, click **Single sign-on**, then **SAML**. See image. The **Set up Single Sign-on with SAML** page appears.
4. In the **SAML Certificates**section, click the download link next to the **Certificate (Base64)**fieldto obtain the Base64 certificate.

See image.

The certificate is downloaded to your system. Upload the certificate as part of Step 4 in the **IdP SAML Certificate**field.

[Image: Screenshot of Create App Integration option in Okta]

[Image: Screenshot of the SAML 2.0 option in OKta]

[Image: Screenshot of the Configure SAML section in Okta]

[Image: Screenshot of the Feedback section in Okta]

[Image: Users and group]

[Image: Screenshot of SAML Signing Certificate section in Okta]

[Image: Screenshot of the Assign to People option in Okta]

[Image: Screenshot of the Assign option in Okta]

[Image: Screenshot of the Assign option in Okta]

[Image: ACS URL]

[Image: Screenshot of the Assign option in Okta]

[Image: Screenshot of the Feedback section in Okta]

[Image: Screenshot of the Feedback section in Okta]

[Image: My Apps Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/saml-configuration-guide-okta","lastmod":"2024-07-02T20:03Z","nid":"1463891"} -->
## SAML Configuration Guide for Okta

- Source: https://help.zscaler.com/risk360/saml-configuration-guide-okta
- Product: Risk360
- Path: SAML Configuration Guide for Okta
- Last modified: 2024-07-02T20:03Z
- Summary: How to configure SAML SSO for Risk360 with Okta.

html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd"

The Risk360 service supports identity provider (IdP)-initiated SAML authentication for admins. The admin can log in to the [Risk360 Admin Portal](https://help.zscaler.com/risk360/accessing-and-navigating-risk-360-admin-portal) directly from a single sign-on (SSO) provider's portal by clicking the Risk360 application icon. This guide illustrates how to configure SAML SSO with Okta for the Risk360 service.

## Prerequisites

Ensure that you have the following before you start configuring Okta:

- An Okta account with admin privileges.
- Admins created for the Risk360 service in the Okta directory.

## Configuring SAML SSO with Okta

To configure Okta SAML SSO for Risk360:

- 1. Create Risk360 App integration in Okta.
- 2. Assign admins to Risk360.
- 3. Download the SAML signing certificate from Okta.
- [4. Configure SAML SSO in the Risk360 Admin Portal.](https://help.zscaler.com/risk360/configuring-saml-admins-risk360)

After you complete the preceding steps, admins can access the Risk360 Admin Portal using their Okta credentials or the Risk360 tile on their Okta homepage.

See image.

To add the Risk360 application to Okta:

1. Log in to your Okta account.
2. In the Admin Console, go to **Applications**> **Applications**.
3. Click **Create App Integration**.

See image.

1. Select **SAML 2.0**as the **Sign**-**in method**.

See image.

1. Click **Next**.
2. In the **General Settings** section, enter the display name for the service in the **App name** field (e.g., `Risk360`).
3. Click **Next**.
4. In the **Configure SAML** section:
  1. **Single sign-on URL**: Enter `https://admin.zscalerrisk.net/idp-auth`
  2. **Audience URI (SP Entity ID)**: Enter `https://admin.zscalerrisk.net/idp-auth`
  3. **Default RelayState**: Enter your Risk360 cloud name. You can view the cloud from the My Profile page in the Risk360 Admin Portal. See image.

See image.

1. Click **Next**.
2. In the **Feedback** section, select **I'm a software vendor. I'd like to integrate my app with Okta**andclick **Finish**.

See image.

The Risk360 integration is created in Okta. You can assign admins to the application.

To assign admins to the Risk360 application:

1. Log in to your Okta account.
2. In the Admin Console, go to **Applications**> **Applications**.
3. Select the Risk360 application from the list.
4. Click **Assign**> **Assign to People**.

See image.

1. Click **Assign**next to the users that you want to assign the application.

See image.

1. Click **Save and Go Back**, then click **Done**.

The admins are assigned to the Risk360 application.

To download the SAML signing certificate:

1. Log in to your Okta account.
2. In the Admin Console, go to **Applications**> **Applications**.
3. Select the Risk360 application from the list.
4. In the**SAML Signing Certificates**section, download the SHA-2 type certificate by clicking **Actions** > **Download certificate**.

See image.

The certificate is downloaded to your system. Upload the certificate as part of Step 4 in the **IdP SAML Certificate**field.

[Image: Screenshot of Create App Integration option in Okta]

[Image: Screenshot of the SAML 2.0 option in OKta]

[Image: My Profile page in the Risk360 Admin Portal]

[Image: Screenshot of the Configure SAML section in Okta]

[Image: Screenshot of the Feedback section in Okta]

[Image: Screenshot of SAML Signing Certificate section in Okta]

[Image: Screenshot of the Assign to People option in Okta]

[Image: Screenshot of the Assign option in Okta]

[Image: Screenshot of Okta Homepage with RIsk360 tile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/saml-configuration-guide-pingfederate","lastmod":"2025-01-19T06:06Z","nid":"1508781"} -->
## SAML Configuration Guide for PingFederate

- Source: https://help.zscaler.com/risk360/saml-configuration-guide-pingfederate
- Product: Risk360
- Path: SAML Configuration Guide for PingFederate
- Last modified: 2025-01-19T06:06Z
- Summary: How to configure SAML SSO for Risk360 with PingFedrate.

This guide illustrates how to configure Ping Identity's PingFederate server as the identity provider (IdP) for Risk360.

Risk360 currently only supports IdP-initiated SSO.

## Prerequisites

Ensure that you have the following before you start configuring PingFederate as your IdP:

- [PingFederate Admin Account](http://docs.pingidentity.com/r/en-us/pingfederate-112/help_administrativeaccountstasklet_administrativeaccountsstate)
- PingFederate Server with the Zscaler Internet Access (ZIA) Connector Add-On
- Export PingFederate Signing Certificate for Zscaler Services
- Zscaler Cloud Name
- Risk360 XML Metadata

To download the Zscaler Internet Access Connector:

1. Go to the [PingFederate Server Add-Ons](https://www.pingidentity.com/en/resources/downloads/pingfederate.html) page.
2. Under **SaaS Connectors**, download **Zscaler Internet Access Connector 1.1**. See image.
3. Unzip the connector folder to extract the `pf-zscaler-zia-quickconnection-1.1.jar` file.
4. Add the `pf-zscaler-zia-quickconnection-1.1.jar` file to the **PingFederate** > **Server** > **default** > **deploy**folder.

When configuring IdPs, the following information might be required for Risk360:

- ACS URL for Risk360 cloud:

```
https://admin.zscalerrisk.net/idp-auth
```

- Download the SAML SSL certificate from the IdP. It must be in Base64-encoded PEM format.
- Entity ID for Risk360 cloud:

```
admin.zscalerrisk.net
```

If you have a domain defined on multiple Zscaler Internet Access (ZIA) clouds, enter the ZIA cloud name that is associated with Risk360 in the **Relay State** field (e.g., `zscalertwo.net`) for each application.

You must also create admin accounts for your organization's admins. To learn more, see [Adding Risk360 Admins](https://help.zscaler.com/risk360/adding-admins-risk360).

To download the XML Metadata from the Risk360 Admin Portal:

1. Sign in to the Risk360 Admin Portal.
2. Go to **Administration** > **Administrator Management** > **Administrator Management**.
3. Click **Download**.

See image.

Remember where you saved the metadata as you will upload it for creating a service provider (SP) connection.

To learn more, see [Configuring SAML for Risk360 Admins](https://help.zscaler.com/risk360/configuring-saml-admins-risk360).

To export your PingFederate signing certificate on the PingFederate admin console:

1. Log in to your PingFederate administrative console.
2. Go to **Security** > **Signing & Decryption Keys & Certificates**. See image.
3. Click **Select Action** on the certificate you want to use.
4. Click **Export**. See image.
5. On the **Export Certificate** tab, click **Next**. See image.
6. On the **Export & Summary** tab, click **Export**. See image.
7. Rename the downloaded certificate's extension to `.pem`.
8. Save this certificate for when you are ready to add PingFederate as an IdP in Risk360.

## Configuring SAML SSO on Zscaler Services

You need to register PingFederate as an IdP in Zscaler services for SAML Single Sign-On (SSO).

To add PingFederate as an IdP in Risk360:

1. If you haven't renamed your certificate from the prerequisites step, rename your certificate's extension to `.pem`.
2. Upload your IdP signing certificate as described in [Configuring SAML SSO for Risk360 Admin.](https://help.zscaler.com/risk360/configuring-saml-admins-risk360)
3. Click **Save**.

## Configuring a Service Provider Connection on PingFederate

To configure a service provider (SP) connection on the PingFederate administrative console:

1. Verify the SAML 2.0 entity ID:
  1. Go to **System** > **Server** > **Protocol Settings** > **Federation Info**.
  2. In the **SAML 2.0 Entity ID** field, enter a name for PingFederate to use when SAML applications need to identify it.
  3. Click **Save**.
2. Create an SP connection:
  1. Use the **SP Connections** shortcut or go to **Applications**>**Integration** > **SP Connections**.
  2. Click **Create Connection**. See image.
  3. In the Create Connection wizard, configure the following sections:
    - Connection Template
    - Connection Type
    - Connection Options
    - General Info
    - Browser SSO
    - Credentials

## Initiate SSO

Risk360 and PingFederate support IdP- and Service Provider-initiated single sign-on. PingFederate's documentation provides information for invoking IdP-initiated SSO. To learn more, refer to the [PingFederate documentation](https://docs.pingidentity.com/r/en-us/pingfederate-112/idp_endpoints).

When using IdP-initiated SSO, Risk360 requires the cloud name (e.g., `zscalerthree.net`) passed through the SAML Relay State if you have a domain defined on multiple ZIA clouds. Zscaler recommends using the SAML Relay State in a single ZIA cloud deployment to avoid any disruption if a second ZIA cloud is added in the future. PingFederate supports this by passing the necessary Relay State value by using the TargetResource query parameter in the `/idp/startSSO.ping` application endpoint.

For example (the green text shows where to insert the ZIA Cloud Name associated with Risk360):

```
https://{PingFederate hostname}/idp/startSSO.ping?PartnerSpId={Risk360 Connection ID}&TargetResource=
zscalerthree.net
```

[Image: Start the Create SP Connection Wizard]

1. Select **Use a template for this connection**.
2. For **Connection Template**, select **Zscaler ZIA Connector**.
3. For **Metadata File**, upload your metadata file.
4. Click **Next**.

[Image: SP Connection Connection Template]

Ensure the **Browser SSO Profiles** checkbox is selected, then click **Next**.

[Image: SP Connection Connection Type]

Ensure the **Browser SSO** checkbox is selected, then click **Next**.

[Image: Connection Options]

- **Partner's Entity ID (Connection ID)**: Verify the partner's entity ID.
- **Connection Name**: Enter a connection name. This might be pre-populated and can be revised to your preference.
- **Base URL**: Verify the base URL. You must append `:443` to the end of your base URL. For example, if your base URL is `https://login.zscaler.net`, then your new base URL is `https://login.zscaler.net``:443`
- Click **Next**.

[Image: Configure General Info]

1. On the **Browser SSO** page, click **Configure Browser SSO.**
2. On the **Assertion Creation**page, click **Configure Assertion Creation**.
3. On the **Authentication Source Mapping** page, click **Map New Authentication Policy**.
4. On the **Authentication Policy Contract** tab, select **subject** for the **Authentication Policy Contract** field. This allows the authentication to be connected to policies. If required, you can configure the contract attribute that is applicable to you.
5. Click **Next**. See image.
6. On the **Mapping Method**page, select **Retrieve Additional Attributes from a data store -- includes options to use alternate data stores and/or a failsafe mapping**.
7. Click **Next**. See image.
8. On the **Attribute Sources & User Lookup** tab, click **Add Attribute Source**.
9. On the **Data Store** page:
  - **Attribute Source Description**: Enter a description for the Attribute Source.
  - **Active Data Store**: Select **PingDirectory**.
10. Click **Next**. See image.
11. On the **LDAP Directory Search** page: See image.
  - **Base DN**: Enter `ou=Zscaler Users,dc=example,dc=com`.
  - **Search Scope**: Select **Subtree** from the drop-down menu.
  - **Attributes to return from the search**:
    - **Root Object Class**: Select **<Show All Attributes>**.
    - **Attribute**: Select **mail**.
    - After **mail** is added, click **Add Attribute** next to it.
12. Click **Next**.
13. On the **LDAP Filter** page, for the **Filter** field, enter `uid=${subject}`.
14. Click **Next**. See image.
15. On the **Attribute Contract Fulfillment** page:
  - **SAML_Subject**: Select **LDAP (pd)**.
  - **Value**: Select **mail**.
16. Click **Next**. See image.
17. On the **Summary** page, click **Done**after you verify your attribute source configuration.
18. On the **Attribute Sources & User Lookup** page, click **Next** after you verify your data store to supply user information in the SAML assertion to the SP. See image.
19. On the**Failsafe Attribute Source** page, select **Abort the SSO Transaction**.\
20. Click **Next**. See image.
21. Click **Done** after reviewing your **Authentication Source Mapping** configuration. See image.
22. Click **Done** after reviewing your **Summary**.
23. On the **Assertion Creation** page, click **Next**. See image.
24. On the **Protocol Settings** page, click **Next**. See image.
25. On the **Summary** page, click **Done** to save your configuration. See image.

1. On the **Credentials** page, click**Configure Credentials**. See image.
2. Select your Signing Certificate. Click **Done**. See image.

[Image: Select ZIA Connector]

[Image: Select Signing & Description Keys & Certificates]

[Image: Select a certificate to export.]

[Image: Click Next to confirm Certificate Options]

[Image: Export your Export Certificate]

[Image: Select Authentication Policy Contract]

[Image: Mapping Method]

[Image: Mapping Method - Data Store]

[Image: Search for mail attribute]

[Image: Added mail attribute]

[Image: Enter the LDAP Filter]

[Image: Attribute Contract Fulfillment - Select your SAML_Subject]

[Image: Attribute Sources & User Lookup]

[Image: Failsafe Attribute Source]

[Image: Authentication Source Mapping]

[Image: Assertion Creation]

[Image: Protocol Settings]

[Image: Summary of Browser SSO]

[Image: Select Configure Credentials]

[Image: Select your credentials]

[Image: Download XML Metadata.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/saving-and-activating-changes-risk360-admin-portal","lastmod":"2024-06-06T22:46Z","nid":"1457136"} -->
## Saving and Activating Changes in the Risk360 Admin Portal

- Source: https://help.zscaler.com/risk360/saving-and-activating-changes-risk360-admin-portal
- Product: Risk360
- Path: Saving and Activating Changes in the Risk360 Admin Portal
- Last modified: 2024-06-06T22:46Z
- Summary: How to save and activate changes in the Risk360 Admin Portal.

html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd"

Propagating configuration changes throughout the Zscaler cloud is a two-step process:

1. Make your configuration changes (e.g., adding, editing, or deleting admins), and save the changes on the local database.

When multiple users edit a configuration setting concurrently, only the last saved configuration persists. This happens when multiple users start editing the same configuration setting before one of them has saved their changes.

1. Queue the changes for activation. Activating the changes effectively pushes the configuration changes to the [Central Authority](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture) (CA), which among other things, serves as the central repository for policies and configuration settings. The ZIA Public Service Edges retrieve the policies from the CA and apply them to your organization's internet traffic. Because all policies are centrally stored on the CA, the latest policies are always applied, no matter to which ZIA Public Service Edge your users connect.

## Activating Changes

Ensure you're making changes to the Risk360 Admin Portal in a single browser session. If you make changes across multiple browser sessions using the same admin account, activation errors can occur.

After you activate your configuration updates, you cannot undo the Activate action. However, you can delete or modify the undesired configuration and activate it again.

To activate changes in the Risk360 Admin Portal:

1. Hover over the **Activation** menu near the bottom left. See image.
2. Click **Activate**. See image.

If there are no other admins editing, the service sends the updates to the CA immediately. If there are multiple admins editing, the service pushes the changes to the CA after all admins activate their changes.

## About the Activation Menu

The Activation menu displays your activation status and the status of other admins who are editing.
See image.

- **My Activation Status**: Displays your activation status.
  - **No Activations Pending**: You haven't saved or activated your changes.
  - **Editing**: You saved your changes but haven't activated them.
  - **Activation Queued**: Your activated changes are pending and haven't been pushed to the CA. This status displays when you activate your changes, and there are other admins still editing. The service pushes the changes to the CA after all admins activate their changes.
- **Currently Editing**: Lists the usernames of admins who saved their changes but haven't activated them.
- **Queued Activations**: Lists the usernames of admins with pending activations. When an admin activates their changes and there are other admins still editing, the changes are queued for activation, and the admin's username is moved to this list. A queued activation cannot be canceled.
- **Force Activate**: Immediately pushes all saved changes to the CA. Only super admins can Force Activate changes.

The Activation menu displays the number of admins who are currently editing.
See image.

The service automatically activates an admin's saved changes if the admin is inactive for 30 minutes or logs out of the admin portal.

[Image: Screenshot of Risk360 Admin Portal Activation menu]

[Image: Screenshot of Risk360 Admin Portal Activate button]

[Image: Screenshot of the Activation menu fields]

[Image: Screenshot of the Activation menu notification number]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/searching-risk360-admin-portal","lastmod":"2023-11-07T05:12Z","nid":"1457141"} -->
## Searching in the Risk360 Admin Portal

- Source: https://help.zscaler.com/risk360/searching-risk360-admin-portal
- Product: Risk360
- Path: Searching in the Risk360 Admin Portal
- Last modified: 2023-11-07T05:12Z
- Summary: How to search in the Risk360 Admin Portal.

To help you navigate faster, you can search for menus in the Risk360 Admin Portal. Search results include menus in the left-hand navigation (except for items in the Help menu) and do not include:

- Tabs
- Field names
- Toggle names
- Tooltips or other text

To search the Admin Portal:

1. Hover over the **Search**icon in the navigation menu. The search panel appears. See image.
2. Enter your search text. The admin portal automatically activates the cursor in the search bar, so you don't need to click before typing.

The search provides auto-complete suggestions as you enter your search.

[Image: Searching in the Risk360 Admin Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/step-step-configuration-guide-risk360","lastmod":"2026-06-03T22:18Z","nid":"1532608"} -->
## Step-by-Step Configuration Guide for Risk360

- Source: https://help.zscaler.com/risk360/step-step-configuration-guide-risk360
- Product: Risk360
- Path: Risk360 Help > Step-by-Step Configuration Guide for Risk360
- Last modified: 2026-06-03T22:18Z
- Summary: This is a recommended chronological process that organizations should follow to be able to use the Risk360 service.

This guide provides the configuration steps needed to begin using Risk360 service in Experience Center for your organization.

To configure Risk360, complete the following steps:

- Step 1: Configure Role-Based Administration
- Step 2: Configure Domains for External Attack Surface Analysis
- Step 3: Manage Peer Score and Financial Risk Settings
- Step 4: Configure Alerting

After configuring the preceding settings, you can start viewing your organization's risk-related [dashboards and insights](https://help.zscaler.com/unified/analytics/unified-dashboards/risk360).

Your organization is enabled with [Authentication Service](https://help.zscaler.com/zidentity/what-zidentity) and you must assign Authentication Service users as Risk360 service admins:

1. Add Risk360 admin role in Experience Center. To learn more, see [Adding Risk360 Admin Roles](https://help.zscaler.com/risk360/adding-admin-roles-risk360).
2. Add users in the Authentication Service Admin Portal. To learn more, see [Adding Users](https://help.zscaler.com/zidentity/adding-users).
3. Assign users a Risk360 admin role. To learn more, see [Assigning Entitlements to Users and User Groups](https://help.zscaler.com/zidentity/assigning-entitlements-users-and-user-groups).

You can add domains to run frequent scans on them to detect vulnerabilities for external attack surfaces. To learn more, see [Adding a Domain for External Attack Surface Analysis](https://help.zscaler.com/risk360/adding-domain-external-attack-surface-analysis).

You can customize the parameters used to calculate your industry's peer risk score and your organization's financial exposure. To learn more, see [Managing Peer Score Settings](https://help.zscaler.com/unified/managing-peer-score-settings) and [Managing Financial Risk Settings](https://help.zscaler.com/unified/managing-financial-risk-settings).

Configure rules to trigger alerts when a preset threshold is reached. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/unified/configuring-alert-rule-risk360).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/understanding-alert-email","lastmod":"2025-02-20T22:04Z","nid":"1486151"} -->
## Understanding Alert Email

- Source: https://help.zscaler.com/risk360/understanding-alert-email
- Product: Risk360
- Path: Understanding Alert Email
- Last modified: 2025-02-20T22:04Z
- Summary: Information about the alert email sent in Risk360.

Alert notifications are sent via email if this option is chosen when configuring the alert rule. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/risk360/configuring-alert-rule).

The alert email shows the following details:

- **Alert Rule Name**: The name of the alert rule. Click the name of the alert rule to view further details in the Risk360 Admin Portal.
- **Alert Severity**: The level of severity for this alert rule. The severity is set when the rule is configured.
- **Alert Criteria**: These are the criteria selected when the alert rule was configured. If one or more alert criteria are met, then it is highlighted.
- **Started On**: This indicates the alert start time.
- **Ended On**: The alert end time. If the alert end time has not ended, then it indicates Ongoing.
- **Alert Cause**: The activity that triggered the criteria in the rule.
- **Customer Message**: The custom message specified when the rule was configured.

Click **View Alerts in Risk360** to view further details in the Risk360 Admin Portal.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/using-tables-risk360","lastmod":"2023-11-07T05:37Z","nid":"1461856"} -->
## Using Tables in Risk360

- Source: https://help.zscaler.com/risk360/using-tables-risk360
- Product: Risk360
- Path: Using Tables in Risk360
- Last modified: 2023-11-07T05:37Z
- Summary: How to use tables in the Risk360 Admin Portal.

In the Risk360 Admin Portal, under the **Administration**pages, the data is organized and displayed in tables. You can modify the default settings for each table. Every time you make a change, the settings are stored in your browser's local storage so that your changes are preserved the next time you log in.

If you clear your cache or use a different browser, your settings are lost.

You can do the following:

- Reorder columns
- Resize columns
- Hide columns
- Sort the column data
- Reset table to default settings

You can reorder columns in one of 2 ways:

- From the Table
- From the Column Menu settings

When you hover over a column header in the table, a **Move** cursor appears. To reorder, drag and drop the column to the desired location. An arrow indicates where the column ends up after the drop.

After you have dragged the column to the desired location, the table refreshes and displays data in the new column order.

The column menu settings also change to reflect the new order.

[Image: Screenshot of reordering columns from a Zscaler table]

When you hover over a listed column name in the settings, a **Move** cursor appears. Drag and drop the column to the desired position. After you drop the column name, the table refreshes and displays the data in the new column order.

[Image: Screenshot of reordering columns from Zscaler table column menu settings]

When the data in a column takes up more space than the viewing area allows, it is truncated. You can view the truncated data in the tooltip. You can also increase the column width.

To increase the column width, hover over the right border of the column. A **Resize** icon appears. Drag and drop to the desired column width.

[Image: Screenshot of resizing columns for Zscaler tables]

When you do not need to view all the columns at the same time, you can hide them so only the columns you want to view are visible.

To hide a column, hover over the **Column Menu** icon on the top right of the table and uncheck the column names you wish to hide. After you click on the checkbox, the table refreshes and displays only the checked columns.

For tables under **Administration**pages, you must have at least one column visible at all times.

[Image: Screenshot of hiding columns for Zscaler tables]

When you hover over different column headers, you can see that some columns have an arrow icon next to them. These arrows indicate that you can sort the data in those columns. You can sort data in ascending or descending order.

To sort a column, click on the arrow in the table column header.

[Image: Sort the coulmn in RIsk360 Admin Portal]

When you make changes to the columns, you can revert back to the default settings by hovering over the **Column Menu** and clicking the **Reset** icon.

This doesn't reset your sort order.

[Image: Screenshot of Reset icon for Zscaler tables]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/using-zscaler-help-browser","lastmod":"2023-11-07T05:16Z","nid":"1461756"} -->
## Using the Zscaler Help Browser

- Source: https://help.zscaler.com/risk360/using-zscaler-help-browser
- Product: Risk360
- Path: Using the Zscaler Help Browser
- Last modified: 2023-11-07T05:16Z
- Summary: Accessing and using the Zscaler Help Browser within the Risk360 Admin Portal.

The Zscaler Help Browser is available for all pages within the Risk360 Admin Portal. Through the Help Browser you can view Zscaler Help Portal articles in context with the page you're on, without leaving the Risk360 Admin Portal.

To open the Help Browser:

1. Go to the page where you want to view an article.
2. Click the **Open Help Browser** icon in the lower-right corner. See image.

The Help Browser displays the article relevant to the page you are viewing. You can then watch videos and read about the page.
 See image.

## About the Help Browser

The Help Browser is also adjustable. There are different features to help you customize the browser and navigate within it.

### Customizing the Help Browser

You can do the following to customize the Help Browser:

- Move the Help Browser
- Resize the Help Browser
- Change the font size

Zscaler saves your customization until you log out or the session expires.

### Navigating within the Help Browser

You can do the following to navigate within the Help Browser:

1. View the article relevant to the page you're viewing in the Risk360 Admin Portal.
2. Navigate back and forth across articles you've viewed.
3. Search for an article.
4. Copy the URL of an article.
5. Close the Help Browser.
6. Open the article in new browser tab.

[Image: Screenshot of the Help Browser and its features]

Closing the Help Browser clears your navigation history. When you reopen the browser, it displays the article relevant to the page you are viewing, and you are unable to navigate back and forth to articles you've viewed in the previous Help Browser session.

**[Image: Screenshot highlighting the Help Browser icon.]**

**[Image: Screenshot showing the Help Browser window with the relevant article and help]**

You can move the Help Browser anywhere within the menu bar and footer. When you hover over the browser header, a **Move** cursor appears. Click and drag the Help Browser to the desired location.

**[Image: Screenshot showing how to move the Help Browser]**

You can resize the Help Browser to expand videos, pictures, and content. When you hover to the top-left corner of the browser, an **Expand** cursor appears. Click and drag the corner to resize the window.

**[Image: Screenshot showing how to resize the Help Browser]**

There are a variety of font sizes for the Help Browser. You can click the **Font** icons to increase or decrease the text size.

**[Image: Screenshot showing the Font icons in the Help Browser]**
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/viewing-risk360-score-change-logs","lastmod":"2026-04-06T14:10Z","nid":"1538852"} -->
## Viewing the Risk360 Score Change Logs

- Source: https://help.zscaler.com/risk360/viewing-risk360-score-change-logs
- Product: Risk360
- Path: Risk360 Help > Logs > Viewing the Risk360 Score Change Logs
- Last modified: 2026-04-06T14:10Z
- Summary: Information regarding risk score change logs within the Risk360 service.

[Watch a video about Score Change Logs in Risk360.](https://fast.wistia.net/embed/iframe/pajjdp57lb)

The Score Change Log page (Logs > Insights > Score Change Log) records the risk score change logs across categories and factors. You can monitor risk score changes and review the action or event that resulted in a risk score change.

## Filtering

- **Filter Data**: Filter logs by Change Event, Category, Factor Group, or Factor.
- **Search**: Search for specific logs.
- **Download**: Download a CSV file. The times mentioned in the CSV file are in PDT.

## Risk Score Change Logs Table

The table shows a list of risk score changes. For each score change, you can see:

- **Timestamp**: The date and time of the user's action.
- **Change Event**: The event that affected the risk score (override, weight, or score).
- **Risk Category**: The Risk360 category where the change occurred (External Attack Surface, Compromise, Lateral Propagation, or Data Loss).
- **Factor Group**: The factor group that is affected.
- **Factor**: The factor that is affected.
- **Notes**: Any additional information about the event.
- **Previous Value**: The score before the event.
- **Current Value**: The current score after the event.

See image.

[Image: Risk Score Change Logs]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/risk360/what-risk360","lastmod":"2026-03-24T10:09Z","nid":"1452526"} -->
## What Is Risk360?

- Source: https://help.zscaler.com/risk360/what-risk360
- Product: Risk360
- Path: Risk360 Help > What Is Risk360?
- Last modified: 2026-03-24T10:09Z
- Summary: Introductory information on Risk360.

[Watch a video about Risk360](https://fast.wistia.net/embed/iframe/hf2l0lww5w).

Zscaler requests you read through the Risk360 service disclaimer before using the service. To learn more, see [Product Output Disclaimer](https://help.zscaler.com/legal/product-output-disclaimer).

Threat actors are constantly exploring new ways to exploit organizations' attack surfaces, move within the environment, and steal data. This makes it critical for security leaders to holistically assess and investigate potential risks to mitigate them. Traditional standalone risk tools and manual processes often make a holistic approach impossible, leading to increased business disruption and brand vulnerability. The risk of long-term financial impact has never been higher. These and other factors have made cyber risk management a board-level priority.

Risk360 analyzes your organization's security data and provides real-time risk metrics to quantify the risk at the organization level and 4 major attack stages:

- **External Attack Surface**: Analyzes external attack surfaces across a range of publicly discoverable variables, such as exposed servers and autonomous system numbers (ASNs) to determine vulnerable internet-facing assets.
- **Compromise**: Analyzes events, security configurations, and traffic flow attributes to compute the likelihood of a compromise.
- **Lateral Propagation**: Analyzes various private access settings and metrics to compute lateral propagation risks.
- **Data Loss**: Analyzes sensitive data attributes to determine potential data leakage risks.

## Key Features and Benefits

The following are the key features and benefits of Risk360:

- Provides a holistic risk analysis and displays factors driving your organization's cyber risk with actionable insights to mitigate the risk.
- Allows you to understand your organization's potential financial exposure using [Monte Carlo simulation](https://help.zscaler.com/unified/quantifying-loss-monte-carlo-simulation).
- Facilitates [risk reporting](https://help.zscaler.com/unified/downloading-risk-reports) for various stakeholders.
- Enables [peer benchmarking](https://help.zscaler.com/unified/managing-peer-score-settings)to understand how your organization is addressing risk compared to industry peers.
- Streamlines compliance efforts against major compliance frameworks.
- Integrates with the Zscaler Zero Trust Exchange (ZTE) for seamless deployment and does not require additional hardware upgrades.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/about-assets-uvm","lastmod":"2026-07-13T21:06Z","nid":"1527976"} -->
## About Assets in UVM

- Source: https://help.zscaler.com/uvm/about-assets-uvm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > About Assets in UVM
- Last modified: 2026-07-13T21:06Z
- Summary: Information on the Assets page in the Unified Vulnerability Management (UVM) application of the SecOps Platform.

The Assets page provides a centralized view of the assets in your organization's Unified Vulnerability Management (UVM) app.Each asset represents a single asset in your environment, unified (i.e., merged) and enriched with information from multiple sources. On this page, you can explore asset details and statuses, view the sources and records from which it was merged, view the findings it contains, and view the tickets it's related to.

The Assets page provides the following benefits and enables you to:

- Access and organize assets using system-saved views, filters, grouping options, and customizable table columns to focus on specific scenarios.
- Explore detailed information about each asset, including associated tickets and findings, risk scores, ownership, and the sources that the assets were detected on.
- Understand how the asset was merged from multiple sources into a unified entity.

## About the Assets Page

On the Assets page (Vulnerabilities > Assets), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/uvm/creating-managing-saved-views).
  - List of System-Saved Views
2. Search for specific assets by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/uvm/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/uvm/using-filters) assets by **Asset Is Crown Jewel, State**, **Owner ID**, or **Source**.
5. Explore the **Overview**charts to gain high-level insights into the assets and their risk level in your environment. The charts are adjusted by the selected view and filters.
  - **Number of Assets by Risk Score**: Displays the number of assets in the different risk score ranges (in increments of 0.5). The X-axis represents the max severity score of active findings related to the asset, and the colors represent the risk category. You can hover over the bars to view the number of assets and the exact score range.
  - **Asset Count by Type**: Presents asset count categorized by asset type, displaying the 5 most frequently occurring types.
  - **Asset Count by Operating System**: Displays the number of assets categorized by operating system, displaying the 5 most frequently occurring types.
6. [Group assets](https://help.zscaler.com/uvm/grouping-operational-views-group) by fields such as **Asset State**, **Asset ID**, or **Asset Owner ID**.
7. Refresh the page to reflect the most current information.
8. Export the list of assets and their associated details as a CSV file.
9. [Modify the columns displayed in the table.](https://help.zscaler.com/uvm/managing-table-columns)
10. Select all assets on the page.
11. Click an asset to open individual [asset drawers](https://help.zscaler.com/uvm/managing-assets-uvm). When the default **Active**saved view is selected, you can see the following details for each asset:
  - **ID**: The asset's ID in the Security Operations Platform.
  - **Type**: The asset type (e.g., **Windows Workstation**, **Web Application**, **Container Image**).
  - **Name**: The asset's name.
  - **Risk Score**: The risk level of findings associated with the asset. The risk score is initially set by the default [reconciliation function](https://help.zscaler.com/uvm/attribute-reconciliation-default-functions), and reflects the highest severity score among the findings. The default can be customized through [Data Unification](https://help.zscaler.com/uvm/what-data-unification).
  - **Risk Mass**: The sum of all severity scores of the active findings associated with the asset.
  - **Owner ID**: The unit in the organization assigned to handling the asset.
  - **Sources**: The sources that the information on the asset is retrieved from.
  - **Is Crown** **Jewel**: A boolean field with values of **TRUE**or **FALSE**indicating whether the asset is defined as a crown jewel asset (i.e., one of your organization's most valuable assets).
  - **Site**: The site that the asset is located on.
  - **First Seen**: The earliest date on which a finding on the asset was first detected.
  - **Last Seen**: The latest date on which a finding on the asset was detected.
  - **Total Findings**: The sum of the active findings per severity.
  - **Tags**: Tags pulled from your sources that include information about the asset that can be [extracted](https://help.zscaler.com/uvm/configuring-field-unification) and used to enrich the asset data.

[Image: The Assets page in UVM]

The Assets page includes system views with predefined filter selections, providing quick access to common data scopes:

- **Active**: All active assets (i.e., all assets ingested into the account from your sources that have not been aged yet). This is the default view.
- **Vulnerable**: All active assets that have at least one active finding.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/about-findings","lastmod":"2026-07-13T21:06Z","nid":"1527991"} -->
## About Findings

- Source: https://help.zscaler.com/uvm/about-findings
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > About Findings
- Last modified: 2026-07-13T21:06Z
- Summary: Information on the Findings page in the Unified Vulnerability Management (UVM) application of the SecOps Platform.

The Findings page provides a centralized view of all findings within your organization's Unified Vulnerability Management (UVM) app. Each finding represents a vulnerability or misconfiguration detected on an asset and contextualized with additional information from multiple sources, such as CVE details, exposed secrets, or misconfigurations. Findings are grouped into tickets based on factors like remediation teams or the affected product. On this page, you can explore finding details, analyze their impact and status, and prioritize remediation using risk-driven insights.

The Findings page provides the following benefits and enables you to:

- Access and filter findings using system-saved views, grouping options, customizable table columns, and advanced filters to focus on specific scenarios.
- Dive into detailed information about each finding, including associated assets, severity scores, and related tickets to prioritize actions effectively.
- Dispatch remediation campaigns by manually grouping findings into a single ticket.

## About the Findings Page

On the Findings page (Vulnerabilities > Findings), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/uvm/creating-managing-saved-views).
  - List of System-Saved Views
2. [Save your view](https://help.zscaler.com/uvm/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns or grouping).
3. [Filter](https://help.zscaler.com/uvm/using-filters) findings by **Severity**, **Source**, **Asset Type**, **First Seen**,or **Last Seen**.
4. Search for specific findings by entering keywords in the search bar.
5. Explore the **Overview**charts to gain high-level insights into the findings and their risk level in your environment. The charts are adjusted by the selected view and filters.
  - **Findings by Severity**: Categorizes findings by severity, comparing their original severity scores (left bar) to recalculated contextualized scores (right bar) for each severity category. This highlights how contextualized scores provide more accurate severity assessments. To learn more, see [Understanding Severity Score](https://help.zscaler.com/uvm/understanding-severity-score).
  - **Findings Count by Source Name**: Displays the number of findings grouped by source, showing the top 5 most frequently occurring sources.
  - **Finding Duplication**: Details the number of findings retrieved from different sources, showcasing how duplicates are reduced and findings are consolidated into actionable tickets for efficient remediation.
6. Apply actions to multiple findings:
  - **Update**: Update finding details (e.g., finding SLA, state).
  - **Create Ticket**: Create a ticket to split the selected findings from their current ticket, and adds them to a new ticket. The new ticket is locked, and the tickets that the findings originate from are listed on the new ticket's [Related Tickets](https://help.zscaler.com/uvm/managing-tickets-uvm) tab.
7. [Group findings](https://help.zscaler.com/uvm/grouping-operational-views-group) by fields such as **Severity Score**, **Finding Type**, or **Source**.
8. Refresh the page to reflect the most current information.
9. Export the list of findings and their details as a CSV file.
10. [Modify the columns displayed in the table.](https://help.zscaler.com/uvm/managing-table-columns)
11. Select all findings on the page.
12. Click a finding to open individual [finding drawers](https://help.zscaler.com/uvm/managing-findings-uvm). When the default **Active Critical and High Findings**saved view is selected, you can see the following details for each finding:
  - **ID**: The unique UVM finding ID.
  - **Severity Score**: The severity score assigned to the finding, calculated based on [severity score settings](https://help.zscaler.com/uvm/configuring-severity-scores).
  - **Title**: The name or description of the finding.
  - **Sources**: The sources from which the finding was ingested.
  - **Asset ID**: The ID of the asset that the finding was detected on.
  - **Asset Type**: The category or classification of the asset related to the finding (e.g., server, application, device).
  - **CVE**: The CVE of the finding, if applicable (i.e., relevant to CVE type findings only).
  - **First Seen**: The date the finding was first detected on your sources.
  - **Last Seen**: The most recent date the finding was detected.

[Image: The Findings page in UVM]

The Findings page includes system views with predefined filter selections, providing quick access to common data scopes:

- **Active Critical and High Findings**: Active findings with critical or high severity. This is the default view.
- **New Findings**: Findings detected within the last 7 days.
- **Active CVE Vulnerabilities**: Active findings classified as CVE-type vulnerabilities.
- **Active Misconfigurations**: Active findings classified as misconfigurations.
- **Known Exploited Active Findings**: Active findings with a high likelihood of being exploited as indicated by the Known Exploited [filter](https://help.zscaler.com/uvm/using-filters) value set to TRUE.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/about-tickets","lastmod":"2026-07-13T21:06Z","nid":"1527801"} -->
## About Tickets

- Source: https://help.zscaler.com/uvm/about-tickets
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > About Tickets
- Last modified: 2026-07-13T21:06Z
- Summary: Information on the Tickets page in the Unified Vulnerability Management (UVM) application of the SecOps Platform.

The Tickets page provides a centralized view of your organization's Unified Vulnerability Management (UVM) tickets. Each ticket in the table aggregates related findings into a single work item that can be fixed as one task. On this page, you can explore ticket details and statuses, assign tickets to remediation owners, and initiate ticket remediation by dispatching tasks to external work management platforms (e.g., Jira, ServiceNow).

The Tickets page provides the following benefits and enables you to:

- Access and organize tickets using system-saved views, filters, grouping options, and customizable table columns to focus on specific scenarios or issues.
- Dive into detailed ticket information to prioritize actions, assign owners, and understand associated findings and assets.
- Dispatch tickets to external platforms (e.g., Jira, ServiceNow) to streamline workflows and remediation processes.

## About the Tickets Page

On the Tickets page (Vulnerabilities > Tickets), you can do the following:

1. Select from system-saved views or views [you previously saved](https://help.zscaler.com/uvm/creating-managing-saved-views).
  - List of System-Saved Views
2. Search for specific tickets by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/uvm/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/uvm/using-filters) tickets by **Severity**, **Status**, **Assignee**, or **Source**.
5. Apply actions to multiple tickets:
  - **Update**: Update the details of a group of selected tickets.
  - **Merge**: Merge multiple selected tickets into one ticket.
  - **Comment**: Add comments to a group of selected tickets.
  - **Create 3rd party Issue**: Dispatch a group of selected tickets to external work management systems.
6. [Group tickets](https://help.zscaler.com/uvm/grouping-operational-views-group) by fields such as **Severity**, **Ticket Type**, or **Assignee**.
7. Refresh the page to reflect the most current information.
8. Export the list of tickets and their associated details as a CSV file.
9. [Modify the columns displayed in the table.](https://help.zscaler.com/uvm/managing-table-columns)
10. Select all policy violations on the page.
11. Click a ticket to open individual [ticket drawers](https://help.zscaler.com/uvm/viewing-uvm-tickets). When the default **Active**saved view is selected, you can see the following details for each ticket:
  - **ID**: The unique UVM ticket ID.
  - **Severity Score**: The [severity score](https://help.zscaler.com/uvm/understanding-severity-scores) assigned to the ticket, calculated based on the severity scores of the findings within the ticket (e.g., using the highest score or the average score).
  - **Risk Mass**: The sum of severity scores of findings in the ticket.
  - **Title**: The ticket title, as configured in ticket [grouping rules](https://help.zscaler.com/uvm/configuring-grouping-rules).
  - **First Seen**: The ticket's first seen date, which reflects the earliest detected finding included in the ticket.
  - **SLA**: The SLA deadline for the ticket.
  - **Assignee**: The remediation owner assigned to remediate the ticket.
  - **Sources**: The sources that the findings in the ticket were ingested by.
  - **Status**: The current status of the ticket.
  - **Remediation**: The percentage of findings resolved within the ticket, based on those that are no longer detected by your sources.

[Image: The Tickets page in UVM]

The Tickets page includes system views with predefined filter selections, providing quick access to common data scopes:

- **Active**: All active tickets (i.e., all tickets with at least one active finding). This is the default view.
- **Closed**: All tickets with a status tagged as closed in status settings (by default, this is the Confirmed status).
- **Missing Assignee**: All tickets with an unpopulated assignee field.
- **No External Ticket Created**: All tickets that are not linked to an external case management issue.
- **Over SLA**: All open tickets with expired service level agreements (SLA).
- **Pending Confirmation**: All tickets are automatically set as inactive when they no longer contain active findings, but have not yet been manually set as closed by the assignee.
- **Tickets With New Findings**: All tickets to which findings were added in the last week.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-apiiro-connector","lastmod":"2026-07-05T07:06Z","nid":"1528251"} -->
## Configuring the Apiiro Connector

- Source: https://help.zscaler.com/uvm/configuring-apiiro-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Apiiro Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Apiiro connector, including setting up authentication and data retrieval filters and specifications.

Apiiro helps security and development teams gain visibility into their software supply chain and application architecture by discovering all application components such as APIs, services, dependencies, and sensitive data, and mapping the application attack surface to remediate critical risks before releasing code or applications to the cloud.

There are three Apiiro streams. Select those that are based on your Apiiro feature plan and use cases:

- Apiiro SCA: Retrieves vulnerabilities related to open-source and third-party components in the application code.
- Apiiro Secrets: Retrieves vulnerabilities that expose access to sensitive application data.
- Apiiro Terraform Misconfiguration: Retrieves vulnerabilities specifically related to misconfigurations in Terraform Infrastructure as Code (IaC) deployments.

To learn more, see [Zscaler UVM and Apiiro Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-apiiro-deployment-guide).

## Prerequisites

The Apiiro source authentication configuration requires an API token.

To generate an Apiiro API token:

1. In the Apiiro platform, go to **Settings**> **General**> **Access Tokens**.
2. Click **Create Token**.
3. Enter a name for the token.
4. Under **Define scopes and permissions**, select either **Read**or **Full Control**.
5. Click **Save**.
6. Copy the generated token to be used in the connector setup process. Save the token securely as you cannot copy or view it later.

## Configuring the Connector

To create the Apiiro data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Apiiro data source, enter the following information in the source setup Retrieval section:

- Authentication
- Risk Level

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Apiiro Authentication** window appears.
2. In the **Apiiro Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Risk Level allows you to filter the retrieved findings based on their severity. The available risk levels are are selected by default and include Low, Medium, High, Critical, Ignored, Accepted, and AutoIgnored. Your selection determines the scope of the ingested data.

The Risk Level drop-down menu is applicable in the following streams:

- Apiiro SCA
- Apiiro Terraform Misconfiguration

[Image: Apiiro Tiles]

[Image: Apiiro Authentication Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-aqua-security-connector","lastmod":"2026-03-04T00:27Z","nid":"1534333"} -->
## Configuring the Aqua Security Connector

- Source: https://help.zscaler.com/uvm/configuring-aqua-security-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Aqua Security Connector
- Last modified: 2026-03-04T00:27Z
- Summary: How to configure the Aqua Security connector, including setting up authentication and data retrieval filters and specifications.

The Aqua Security connector is in limited availability. To learn more, contact Zscaler Support.

Aqua Security offers cybersecurity solutions for containerized applications and cloud-native environments. It supports teams using Docker, Kubernetes, serverless functions, and other cloud-native technologies by providing advanced tools for vulnerability management, runtime protection, and compliance monitoring.

There are 7 Aqua Security streams. Select those that are based on your Aqua Security feature plan and use cases:

- Aqua Security - CWPP Functions Vulnerabilities: Retrieves vulnerability data for functions managed in the Aqua Security platform.
- Aqua Security - CWPP Hosts Vulnerabilities: Retrieves vulnerability data for host entities, including metadata and security-relevant attributes.
- Aqua Security - CWPP Images Vulnerabilities: Retrieves vulnerability data for container images analyzed by Aqua Security.
- Aqua Security Containers: Retrieves information about running containers monitored by Aqua Security.
- Aqua Security CSPM: Retrieves cloud misconfigurations and security alerts related to compliance violations and risks.
- Aqua Security Hosts: Retrieves metadata and relevant details about hosts managed in Aqua Security.
- Aqua Security Images: Retrieves metadata and security insights for container images analyzed by Aqua Security.

## Prerequisites

To configure the Aqua Security connector, you need the source authentication configuration. Choose one of the following authentication types and retrieve the required parameters for it:

- API Key Domain Secret Key
- URL Username Password

If you require a gateway connection for setting up the Aqua Security connector, submit a support ticket to request the configuration of your gateway. To learn more, see [Configuring the Zscaler SecOps Platform Gateway](https://help.zscaler.com/uvm/configuring-zscaler-secops-platform-gateway).

## Configuring the Connector

To create the Aqua Security data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Aqua Security data source, enter the following information in the source setup Retrieval section:

- Authentication
- Fetch data from the past selected days
- (Optional) Include Custom Fields
- Gateway

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

Use this authentication type to connect using an API key.

The following parameters are required:

- API Key and Secret Key
- Domain

Use this authentication type to connect using an Aqua Security username and password.

The following parameters are required:

- Username and Password
- URL

To generate an API key and secret key:

1. In the Aqua Security portal, go to **Account Management**> **Settings**> **API Keys**. See image.
2. Click **Generate Key**. The **Generate API Keys**window appears.
3. In the **Generate API Keys**window: See image. The**API Key generated**window appears and displays the generated API key and secret key. See image.
  1. **Description**: Enter a description for the API key.
  2. **IP Address(es)**: (Optional) Enter the list of the [Zscaler SecOps allowed IP addresses](https://help.zscaler.com/uvm/zscaler-secops-public-ip-addresses) to restrict the use of this key to API requests originating from the specified IP addresses.
  3. **Role(s)**: Select **OOTB API Keys Viewer** and **OOTB Application Scope Viewer**or higher, or custom roles with equivalent access.
  4. **Expiration**: (Optional) Enter an expiration date for the API key. If you do not set an expiration date for the key, the expiration date defaults to one year from the created date. Make sure to refresh the key when it expires.
  5. Click **Generate**.
4. Copy the generated API key and secret key to be used in the connector setup process. Save the secret securely as you cannot copy or view it later.

The base URL for accessing your Aqua Security instance (e.g., `https://``<Your Aqua Security Instance>``.com`, `https://``<Your Aqua Unique ID >``.cloud.aquasec.com`).

The username and password associated with an Aqua Security user carrying the OOTB Application Scope Viewer role or higher, or a custom role with equivalent access.

The base URL for accessing your Aqua Security instance (e.g., `https://``<Your Aqua Security Instance>``.com`, `https://``<Your Aqua Unique ID >``.cloud.aquasec.com`).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Aqua Security Authentication** window appears.
2. In the **Aqua Security Authentication** window, select an authentication type and enter the information from the Prerequisites section.
  - API Key Domain Secret Key See image.
  - URL Username Password See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the number of days that you want to retrieve data for during each run. For example, enter `30` to fetch data from the past 30 days. By default, the connector retrieves data from the last 7 days. Selecting a larger date range might lead to longer processing times or potential timeouts.

This field is available in the following streams:

- Aqua Security - CWPP Functions Vulnerabilities
- Aqua Security - CWPP Hosts Vulnerabilities
- Aqua Security - CWPP Images Vulnerabilities

Enter Aqua Security custom fields to include in the scope of the ingested data. Use the field system names provided by Aqua Security (e.g., `aqua_id`, `aqua_severity`). Press `Enter` after each field name entry to add it to the list.

The added custom fields appear in the drop-down menu, where you can select or deselect them as needed.

This field is available in the following streams:

- Aqua Security - CWPP Functions Vulnerabilities
- Aqua Security - CWPP Hosts Vulnerabilities
- Aqua Security - CWPP Images Vulnerabilities

If a gateway is required for the connection, use the drop-down menu to select the gateway you configured.

The Gateway drop-down menu only displays gateways that have been previously configured.

[Image: Aqua Security Account Management Settings API Keys Page]

[Image: Aqua Security Generate API Keys Window]

[Image: Aqua Security API Key generated window]

[Image: Aqua Security connector tiles]

[Image: Aqua Security Authentication window with API Key Domain Secret Key Authentication Type]

[Image: Aqua Security Authentication window with URL Username Password Authentication Type]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-armis-connector","lastmod":"2026-07-05T07:06Z","nid":"1528391"} -->
## Configuring the Armis Connector

- Source: https://help.zscaler.com/uvm/configuring-armis-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Armis Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Armis connector, including setting up authentication and data retrieval filters and specifications.

Armis provides context about an organization’s assets (i.e., owner, location, dependencies, and vulnerabilities) and continuously analyzes their behavior to identify both operational and cyber risks to protect them against cyberattacks.

There are three Armis streams. Select those that are based on your Armis feature plan and use cases:

- Armis CVE: Retrieves vulnerability data related to devices, including matched device details.
- Armis Devices: Retrieves information about discovered devices, including their attributes and states.
- Armis Reports: Retrieves data from specific reports in Armis using a Report ID.

The Reports stream serves as an additional method for retrieving device and vulnerability data, using preconfigured reports within Armis.

To learn more, see [Zscaler and Armis Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-armis-deployment-guide).

## Prerequisites

To configure the Armis connector, you need the source authentication configuration. The following parameters are required:

- API Key
- Tenant

## Configuring the Connector

To create the Armis data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Armis data source, enter the following information in the source setup Retrieval section:

- Authentication
- Report ID
- Days to Fetch

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Roles and Permissions

The API token must be associated with a user that carries the appropriate permissions as detailed in the following table:

| **Stream** | **Permissions** |
| --- | --- |
| Armis CVE | `Vulnerability > Read` |
| Armis Devices | `Device > Read` |
| Armis Reports | `Report > Read` |

The API token must be associated with a user carrying the appropriate roles and permissions.

To create the API Token:

1. In the Armis portal, go to **Settings** > **API Management**. See image.
2. If an API secret key doesn’t already exist, click **Create**. See image.
3. Click **Show**to view the API secret key. See image.
4. In the**API Secret Key** window, click **Copy**. See image.

**[Image: Armis Settings API Management Page]**

[Image: API Management Create API Secret Key]

[Image: API Management Show API Secret Key]

[Image: API Secret Key Window Copy Secret Key]

In the **Tenant** field, enter your specific Armis instance, which can be found in your management console URL, in the format `https://``<Tenant>``.armis.com`

For example, if your URL is `https://acme.armis.com`, then your tenant is `acme`.

See image.

[Image: Sample URL using Acme as the tenant]

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Armis Authentication** window appears.
2. In the **Armis Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

In the **Report ID** field, enter the ID of the report you want to include in the scope of the ingested data.

This field is applicable in the Armis Reports stream.

In the **Days to Fetch** field, enter the number of days to retrieve devices seen during that time frame. For example, entering 30 returns assets that were seen within the last 30 days.

This field is applicable in the Armis Devices stream.

[Image: Armis Authentication Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-attackforge-connector","lastmod":"2026-07-03T07:06Z","nid":"1528246"} -->
## Configuring the AttackForge Connector

- Source: https://help.zscaler.com/uvm/configuring-attackforge-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the AttackForge Connector
- Last modified: 2026-07-03T07:06Z
- Summary: How to configure the AttackForge connector, including setting up authentication and data retrieval filters and specifications.

AttackForge is a collaborative platform designed to manage penetration testing projects. It provides centralized tools for testers, developers, and stakeholders to track vulnerabilities, share findings, and resolve security issues in real time while improving overall productivity.

The AttackForge connector retrieves vulnerability data accessible to the user associated with the API key.

## Prerequisites

The AttackForge source authentication configuration requires an API key. To enable the API, an administrator must grant the user access to RESTful API methods.

To generate the API key in the AttackForge portal, go to Profile > Settings > Secrets. The API key inherits the access permissions of the user who created it and is restricted to the data they are allowed to access within the AttackForge platform. Verify that the access permissions align with your specific requirements before generating the key. To learn more, refer to the [AttackForge documentation](https://support.attackforge.com/attackforge-enterprise/getting-started/manage-user#user-key).

## Configuring the Connector

To create the AttackForge data source in the Security Operations Platform (SecOps Platform):

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the AttackForge data source, enter the following information in the source setup Retrieval section:

- Authentication
- Host

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **AttackForge Authentication** window appears.
2. In the **AttackForge Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

The host is the domain where your AttackForge instance is hosted, typically represented as the subdomain and domain in your URL (e.g., `acme.attackforge.com`). Enter the complete domain name without including `http://` or `https://`.

[Image: AttackForge connector tile]

[Image: AttackForge Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-aws-connector","lastmod":"2026-07-05T07:06Z","nid":"1531013"} -->
## Configuring the AWS Connector

- Source: https://help.zscaler.com/uvm/configuring-aws-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the AWS Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Amazon Web Services (AWS) connector, including setting up authentication and data retrieval filters and specifications.

The Amazon Web Services (AWS) connector can ingest data relating to your organization's findings and resources collected by and stored on your AWS services.

There are 10 AWS streams. Select those that are based on your AWS feature plan and use cases:

- AWS Accounts: Retrieves metadata about AWS accounts, including ID, ARN, email, name, status, joined method (e.g, created or invited), joined timestamp, and tags.
- AWS EC2: Retrieves metadata about EC2 instances, including ID, type, public IP address, security groups, and configuration details.
- AWS ECR: Retrieves metadata about Elastic Container Registries (ECR), such as registry ID, image tags, and region name.
- AWS ECR Findings: Retrieves metadata about security vulnerabilities and findings related to container images stored in ECR.
- AWS EKS Clusters API: Retrieves metadata about Elastic Kubernetes Service (EKS) clusters, including name, ARN, endpoint, role ARN, status, tags, and region name.
- AWS Inspector Findings: Retrieves metadata about security vulnerabilities and findings identified by AWS Inspector for organizational resources.
- AWS Network: Retrieves metadata about AWS networking components, such as VPCs, subnets, and security configurations.
- AWS RDS: Retrieves metadata about Relational Database Service (RDS) database clusters, including ID, engine, status, endpoint, security groups, availability zone, subnet group, public accessibility, tags, and region name.
- AWS S3 Buckets: Retrieves metadata about S3 buckets, including name, creation date, and region name.
- AWS Security Hub API: Retrieves metadata about security findings and insights from AWS Security Hub across connected organizational resources.

AWS resources are mapped as assets, including accounts.

To learn more, see [Zscaler and AWS Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-aws-deployment-guide).

## Prerequisites

To configure the AWS connector, you need the source authentication configuration. The following parameters are required:

- Secret Key
- Role ARN - Single Account
- Role ARN - Multiple Accounts

## Configuring the Connector

To create the AWS data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the AWS data source, enter the following information in the source setup Retrieval section:

- Authentication
- Include the Following Network Data

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

This authentication method uses an AWS access key and secret key to fetch data from a single AWS account. This method cannot be used for the AWS Accounts stream.

The following parameters are required:

- Access Key and Secret Key
- Region Names

This authentication method uses a single role ARN that allows you to fetch data from a single account or, if you are an admin with the appropriate access, from all organizational accounts.

The role ARN permissions are required for each AWS stream when using a single role ARN account. Determine the required permissions set to be used when generating your role ARN. To learn more, see [Zscaler and AWS Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-aws-deployment-guide).

The following parameters are required:

- Region Names
- Role ARN and External ID

This authentication method uses Role ARNs to allow you to fetch data from multiple accounts.

The role ARN permissions are required for each AWS stream when using multiple role ARN accounts. Determine the required permissions set to be used when generating your role ARNs. To learn more, see [Zscaler and AWS Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-aws-deployment-guide).

The following parameters are required:

- Region Names
- Role ARNs List

Optionally, you can add the External ID parameter.

To securely and efficiently retrieve data from multiple AWS accounts, create an AWS StackSet. This allows you to deploy and manage AWS CloudFormation stacks across multiple AWS accounts and regions from a single account.

Ensure that you are signed in to an organization or management account. To create an AWS StackSet:

- Create a CloudFormation file.
- Create a StackSet.
- Create stack instances.
- List all the accounts in the organization.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **AWS Authentication** window appears.
2. In the **AWS Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Set the Include the Following Network Data drop-down menu filters and specifications. Select the data types to include in the ingestion scope. When selecting network data, add the relevant permissions to your permissions list in the CloudFormation file. To retrieve network data from all accounts, ensure that you attach the permissions to the root/organization account.

This menu is available in the AWS Network stream.

[Image: The AWS Accounts, AWS EC2, AWS ECR, AWS ECR Findings, AWS EKS Clusters API, AWS Inspector Findings, AWS Network, AWS RDS, AWS S3 Buckets, and AWS Security Hub API tiles]

[Image: The AWS Authentication window displaying the Name, Authentication Type, Aws Role ARN, External ID, and Region Name in the SecOps platform]

To create an AWS access key and secret key:

1. Log in to the AWS IAM console.
2. On the **Console Home** page, from the top right, select your username.
3. From the drop-down menu, select **Security credentials**.
4. On the **My security credentials** page, in the **Access keys** section, click **Create access key**. If you already have two access keys, **Create access key** is deactivated. You must delete an access key before you can create a new one.
5. On the **Create access key** page, configure the following:
  1. On the **Access key best practices & alternatives** page, select **Other** and then select **Next**.
  2. On the **Set description tag - optional** page, optionally enter a description tag value for the access key. This adds a tag key-value pair to your IAM user, which can help you identify and update access keys later.
6. Click **Create access key**.
7. On the **Retrieve access keys** page, select either **Show to reveal the value of your user's secret access key** or **Download .csv file**. Save the secret access key. It is not displayed again after you leave this page.
8. Select **Done**.

Enter the region of the AWS service you are retrieving data from.

Enter the region of the AWS service you are retrieving data from.

The external ID is the optional unique identifier generated when creating a role ARN. It is used as an additional security measure when accessing your account. To learn more, see [Zscaler and AWS Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-aws-deployment-guide).

To create a CloudFormation file:

1. Generate a UUID.
2. Copy and save the content of the [CloudFormation JSON file](https://avalor-platform-public-prod.s3.amazonaws.com/static/media/avalor-aws-standard.json) locally as `zscaler-aws-connector.json`.

When connecting the AWS Network stream, you can retrieve any of the data types from the table by adding the relevant permissions to your permissions list in the CloudFormation file. To retrieve network data from all accounts, ensure that you attach the permissions to the root/organization account.

| **Data Type** | **Permission Required** |
| --- | --- |
| Internet Gateway | ec2:DescribeInternetGateways |
| Nat Gateway | ec2:DescribeNatGateways |
| Load Balancer | elasticloadbalancing:DescribeLoadBalancers |
| VPC CIDR | ec2:DescribeVpcs |
| VPC Subnets | ec2:DescribeSubnets |
| Addresses | ec2:DescribeAddresses |
| Network Interfaces | ec2:DescribeNetworkInterfaces |

Before you run the command:

- Ensure that the updated `zscaler-aws-connector.json` [file](https://avalor-platform-public-prod.s3.amazonaws.com/static/media/avalor-aws-standard.json) is located in your present working directory (i.e., the directory you are running the command in).
- Replace `<Generated UUID>` with the UUID you previously created.

To create a StackSet:

1. Run the following command: `aws cloudformation create-stack-set \ --stack-set-name ZscalerStackIntegration \ --template-body file://zscaler-aws-connector.json \ --capabilities CAPABILITY_NAMED_IAM \ --permission-model SERVICE_MANAGED \ --parameters ParameterKey=ExternalId,ParameterValue=<Generated UUID> \ --auto-deployment Enabled=true,RetainStacksOnAccountRemoval=false`
2. To confirm that your StackSet was created, run the `aws cloudformation list-stack-sets` command. Your new StackSet is listed in the results.

Before you run the command:

- Replace `<organization-root-ID>` with your organization root ID to deploy to all accounts in your organization.
- Replace `<region-name>` with one region to specify where the stack instance is created. Specifying a region is required as CloudFormation operates within a regional context. The specified region (e.g., us-east-1) does not limit the scope of IAM resources. They remain global and available in all regions.

To create stack instances, run the following command:

```
aws cloudformation create-stack-instances \
--stack-set-name ZscalerStackIntegration \
--deployment-targets OrganizationalUnitIds='["
<organization-root-ID>
"]' \
--regions '["
<region-name>
"]'
```

Before you run the command:

- Ensure that the updated `zscaler-list-account-cf.json` [file](https://avalor-platform-public-prod.s3.amazonaws.com/static/media/avalor-aws-standard.json) is located in your present working directory (i.e., the directory you are running the command in).
- Replace `<Generated UUID>` with the UUID you previously created.

To list all accounts in the organization:

1. In the organization or management account, copy and save the [CloudFormation JSON file](https://avalor-platform-public-prod.s3.amazonaws.com/static/media/avalor-aws-standard.json) content locally as `zscaler-list-account-cf.json`.
2. To create a new stack, run the following command: `aws cloudformation create-stack \ --stack-name zscaler-list-account-stack \ --template-body file://zscaler-list-account-cf.json \ --capabilities CAPABILITY_NAMED_IAM \ --parameters ParameterKey=ExternalId,ParameterValue=<Generated UUID>`

You can assume the roles in each account within the organization from your code.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-axonius-connector","lastmod":"2026-07-31T07:06Z","nid":"1528216"} -->
## Configuring the Axonius Connector

- Source: https://help.zscaler.com/uvm/configuring-axonius-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Axonius Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Axonius connector, including setting up authentication and data retrieval filters and specifications.

Axonius is a cybersecurity and asset management platform that provides organizations with complete visibility and control over their IT assets, including devices, users, cloud services, and SaaS applications.

The Axonius connector retrieves asset inventory data, including device details, custom fields, and associated metadata.

## Prerequisites

To configure the Axonius connector, you need the source authentication configuration. The following parameters are required:

- API Key and API Secret
- URL

## Configuring the Connector

To create the Axonius data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Axonius data source, enter the following information in the source setup Retrieval section:

- Authentication
- Number of days to fetch (Optional)
- Custom Fields (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

You can retrieve API credentials using a user account or a service account. A service account is used only for API purposes. To learn more, refer to the [Axonius documentation](https://docs.axonius.com/docs/manage-service-accounts).

To retrieve an API key, the user's assigned role must have API access enabled.

### Enabling API Access

To enable API access:

1. In the Axonius portal, go to the **All Pages** page.
2. Click the **Settings** icon in the top-right corner and select **System Settings**.
3. Click the **Manage Roles** tab.
4. Select the role assigned to the user you want to enable access for.
5. In the **API Access**section, select the **API access enabled**checkbox. See image.
6. Click **Save**.

To learn more, refer to the [Axonius documentation](https://docs.axonius.com/docs/manage-roles).

### Retrieving the API Key and Secret

To retrieve the API key and API secret:

1. In the Axonius portal, click the **Profile**menu and select **User Settings**.
2. Click the **API Key** tab.
3. Copy the API key and API secret. See image. Save the API key and API secret securely as you cannot copy or view it later.

In the URL field, enter the Machine URL, which serves as the base URL for the API and follows the format: `https://``<Machine URL>``/api/`. For example, in the URL `https://10.20.0.111/api/`, the expected input for the URL is `10.20.0.111`.

[Image: Axonius API Access Enabled]

[Image: Axonius Reset API Key and API Secret]

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Axonius Authentication** window appears.
2. In the **Axonius Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the number of days that you want to retrieve data for during each run. For example, enter `30` to fetch data from the past 30 days. By default, the connector retrieves all data.

Enter Axonius custom fields to include in the scope of the ingested data. Press `Enter` after each field name entry to add it to the list. The added custom fields appear in the drop-down menu, where you can select or deselect them as needed. To learn more, refer to the [Axonius documentation](https://docs.axonius.com/docs/managing-custom-fields).

[Image: Axonius connector tile]

[Image: Axonius Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-azure-blob-connector","lastmod":"2026-08-18T01:38Z","nid":"1530972"} -->
## Configuring the Azure Blob Connector

- Source: https://help.zscaler.com/uvm/configuring-azure-blob-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Azure Blob Connector
- Last modified: 2026-08-18T01:38Z
- Summary: How to configure the Azure Blob connector, including setting up authentication and data retrieval filters and specifications.

Azure Blob Storage is a cloud-based object storage solution provided by Microsoft Azure, allowing users to store and serve large amounts of unstructured data.

The Azure Blob connector is used for uploading files from your Azure storage to the Zscaler Security Operations (SecOps) platform, such as scan results, compliance reports, or any other security-related data stored in your Azure Blob containers. Files in unsupported formats are not processed by the Azure Blob connector.

## Prerequisites

To configure the Azure Blob connector, you need to configure the appropriate roles and permissions. You also need the source authentication configuration, which is your storage account connection string. The following parameters are required:

- Retrieving the Storage Account Connection String
- Assigning Permissions
- Enabling Network Access

## Configuring the Connector

To create the Azure Blob data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Azure Blob data source, enter the following information in the source setup Retrieval section:

- Authentication
- Requested Containers List
- Requested Tags

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

To retrieve the storage account connection string, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

To assign the required permissions to the user associated with the connection string:

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Storage accounts`. From the drop-down menu, select **Storage accounts**.
3. On the **Storage accounts** page, select the name of your storage account from the table.
4. From the left-side navigation, select **Access Control (IAM)**.
5. Click **Add** > **Add role assignment**.
6. From the drop-down role menu, select a role with the required permissions (i.e., the built-in **Storage Blob Data Reader** role). The **Storage Blob Data Reader** role is a built-in Azure Blob role that covers the necessary permissions to access and read data from a storage account.
7. In the **Assign access to** section, choose the relevant entity type (i.e., **User, group, or service principal**) and select the desired entity.
8. Click **Save**.

The user or service principal associated with the connection string must have the following Azure RBAC permissions:

- Blob service operation - List Containers: `Microsoft.Storage/storageAccounts/blobServices/containers/read`. Ensure that the role is scoped to the storage account or above.To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/rest/api/storageservices/list-containers2?tabs=microsoft-entra-id).
- Blob service operation - List Blobs: `Microsoft.Storage/storageAccounts/blobServices/containers/blobs/read`. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/rest/api/storageservices/list-blobs?tabs=microsoft-entra-id).

To ensure successful connectivity, enable network access to your Azure Blob Storage account. You can allow access from all networks or restrict it to specific IP addresses. To enable network access:

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Storage accounts`. From the drop-down menu, select **Storage accounts**.
3. On the **Storage accounts** page, select the name of your storage account from the table.
4. From the left-side navigation, select **Security + networking** > **Networking**.
5. On the **Firewalls and virtual networks** tab, in the **Public network access** section, configure your preferred access:
  - If **Enabled from all networks** is selected, no further action is required.
  - To restrict access to specific IP addresses, select **Enabled from selected virtual networks and IP addresses**. Add the following IP addresses to the allowed range:
    | **Region** | **IP Addresses** |
    | --- | --- |
    | US | 3.137.47.190/32; 3.15.110.62/32; 3.129.232.141/32 |
    | EU | Refer to the [Zscaler Config](https://config.zscaler.com/zscalertwo.net/hubs). |

These IP addresses must be added to allow the Zscaler service to connect to your Azure Blob Storage account.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Azure Blob Authentication** window appears.
2. In the **Azure Blob Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

The requested containers list is a list of containers in the Azure Blob Storage account. You can specify a list of containers in this account from which you want to retrieve data, which limits the scope of data retrieval. If the containers are specified, only blobs from the mentioned containers are retrieved. If no containers are specified, data is retrieved from all containers in the storage account.

The requested tags are a list of user-defined tags in the format key : value. You can specify a list of user-defined tags to filter the blobs retrieved from Azure Blob Storage account containers. To limit the scope of data to those including specified tags, add tags in the format key : value, where the value can be empty. If the tags are specified, only blobs that include at least one of the listed tags are retrieved.

[Image: The Azure Blob tile]

[Image: The Azure Blob Authentication window displaying the Name and Azure Blob Storage Account Connection fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-azure-cloud-assets-connector","lastmod":"2026-08-18T01:46Z","nid":"1533909"} -->
## Configuring the Azure Cloud Assets Connector

- Source: https://help.zscaler.com/uvm/configuring-azure-cloud-assets-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Azure Cloud Assets Connector
- Last modified: 2026-08-18T01:46Z
- Summary: How to configure the Azure Cloud Assets connector, including setting up authentication and data retrieval filters and specifications.

Azure Cloud is a cloud computing platform that enables organizations to build, deploy, and manage cloud-based applications with scalable infrastructure and integrated services.

The Azure Cloud Assets connector retrieves comprehensive asset information across Azure subscriptions, including metadata, configurations, and properties of resources for centralized insights and management.

This connector retrieves cloud data only.

For Microsoft Defender for Cloud Findings, see [Configuring the Microsoft Defender for Cloud Findings Connector](https://help.zscaler.com/uvm/configuring-microsoft-defender-cloud-connectors). For Microsoft Defender for Endpoints, see [Configuring the Microsoft Defender for Endpoints Connector](https://help.zscaler.com/uvm/configuring-microsoft-defender-endpoints-connectors).

## Prerequisites

To configure the Azure Cloud Assets connector, you need to configure the appropriate roles and permissions. You also need the source authentication configuration. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-create-service-principal-portal).

The following parameters are required:

- Application (client) ID and Directory (tenant) ID
- Client Secret

### Roles and Permissions

On the application API permission tab, assign the permissions to the application.

- View instructions.

The Application (client) ID and the Directory (tenant) ID are located on the Overview tab of an application.

To create a new application:

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, click **New registration**.
  - **Name**: Enter a name for the application.
  - **Support account types**: Select **Accounts in this organizational directory only**.
  - **Redirect URI (optional)**: Optionally configure these settings.
4. Click **Register** to create the application. The application **Overview** section appears.
5. In the **Overview** section, copy the generated **Application (client) ID** and the **Directory (tenant) ID**.

On the application **Certificates & secrets** tab, generate and copy a client secret for the application.

To generate a client secret:

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, select your app registration.
4. From the left-side navigation, from the **Manage** drop-down menu, select **Certificates & secrets**.
5. Click **New client secret**.
  - Optionally, enter a description.
  - Enter an expiration date.
6. Click **Add**.
7. Copy the client secret value. Save the client secret value. The client secret is not displayed again after you leave this page.

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, select your app registration.
4. In your app registration, in the left-side navigation, from the **Manage** drop-down menu, select **API permissions**.
5. In the **Configured permissions** section, click **Add a permission**.
  1. Go to the **Microsoft APIs** tab.
  2. Select **Microsoft Graph**.
  3. Select **Application permissions**.
  4. Add the **PrivilegedAccess.Read.AzureResources** permission.
  5. Click **Add permissions**.
  6. If admin consent is required for these permissions, select **Grant admin consent**.
  7. Click **Yes** to confirm the action.

## Configuring the Connector

To create the Azure Cloud Assets data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Azure Cloud Assets data source, enter the following information in the source setup Retrieval section:

- Authentication
- Specific Subscription IDs
- All Subscription IDs in org

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Microsoft Defender for Cloud Authentication** window appears.
2. In the **Microsoft Defender for Cloud Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

[Image: Azure Cloud - Assets Tile]

Enter the subscription IDs from which you want to retrieve data.

In the subscriptions service, retrieve the subscription ID for the relevant subscriptions and configure the role assignment.

To retrieve your subscription ID:

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Subscriptions`. From the drop-down menu, select **Subscriptions**.
3. On the **Subscriptions** page, select the subscription you want to use.
4. On the **Overview** tab, copy the **Subscription ID**. See image. You can enter multiple subscription IDs in the platform.

To assign the Reader role to a single subscription:

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Subscriptions`. From the drop-down menu, select **Subscriptions**.
3. On the **Subscriptions** page, from the left-side navigation, select **Access control (IAM)**.
4. On the **Access control (IAM)** tab, click **Add** > **Add role assignment** to configure the following: To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/role-based-access-control/role-assignments-portal).
  1. On the **Role** tab, select the **Reader** role.
  2. On the **Members** tab, select **User, group, or service principal**.
  3. Click **+ Select members** and select the application you registered previously.
  4. On the **Assignment type** tab, set the assignment duration.
  5. On the **Review + assign** tab, review the assignment and click **Review + assign**.

Data is retrieved from all subscriptions in your organization. If you have multiple subscriptions, you can organize them into a single management group to set access control in bulk.

To assign the Reader role to multiple subscriptions:

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Management groups`. From the drop-down menu, select **Management groups**.
3. On the **Management groups** page, click **Create** to configure the following:
  - **Management group ID**: The directory unique identifier that is used to submit commands on this management group. You cannot edit this ID after creation.
  - **Management group display name**: (Optional) The name displayed in the Azure portal.
4. Click **Submit**.
5. On the **Management groups** page, click the name of the management group you created.
6. Click **Add subscription**. The **Add subscription** window appears.
7. In the **Add subscription** window, select the subscription you want to add to the management group.
8. Click **Save**.
9. Select the name of the subscription group you added.
10. On the subscription group page, from the left-side navigation, click **Access control (IAM)**.
11. Click **Add**> **Add role assignment** to configure the following:
  - On the **Role** tab, select the **Reader** role.
  - On the **Members** tab, select **User, group, or service principal**.
  - Click **+ Select members** and select the application you registered previously.
  - Click **Select members**, and then select the application you registered previously.
  - On the **Review + assign** tab, review the assignment and click **Review + assign**.

To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/governance/management-groups/overview).

[Image: Copying the Subscription ID on the Subscriptions page of the Microsoft Azure portal]

[Image: Microsoft Defender for Cloud Authentication Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-azure-devops-outegration","lastmod":"2026-08-16T00:47Z","nid":"1532592"} -->
## Configuring Azure DevOps Outegration

- Source: https://help.zscaler.com/uvm/configuring-azure-devops-outegration
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring Azure DevOps Outegration
- Last modified: 2026-08-16T00:47Z
- Summary: How to configure the Azure DevOps outegration to dispatch tickets.

The Azure DevOps outegration is used to dispatch tickets from the Zscaler Security Operations (SecOps) platform applications (e.g., UVM) to your Azure DevOps project, enabling you to track and assign tickets to a specific team for analysis and remediation.

This article is a step-by-step guide to configuring the Azure DevOps work management outegration.

Each Azure DevOps ticket type (e.g., Bug, Task, Feature) requires a separate outegration configuration.

## Prerequisites

Before configuring the outegration, ensure that you complete the authentication and retrieve the Client ID, Client Secret, Tenant ID, organization name, and project name.

- Authentication Workflow

1. Log in to the [Azure portal.](https://portal.azure.com/#home)
2. Go to **App registrations**. See image.
3. Create a new application. The **Overview**page appears when the application is created. If you have already registered an application, then proceed to create the client secret.
  1. On the **Register an application** page, enter a name for the application.
  2. Select **Accounts in any organizational directory (Any Microsoft Entra ID tenant - Multitenant)**.
  3. Click **Register**. See image.
4. Copy and save the **Application (client) ID** and **Directory (tenant) ID**. See image.
5. In the left-side navigation, go to **Manage**> **Authentication**.
6. Click **Add a Platform**and select **Web**.
7. Under **Configure Web**, enter `https://app.avalor.io/oauth` as the application's redirect URI. See image.
8. Click **Configure**.
9. In the left-side navigation, go to **Manage**> **API Permissions**.
10. Click **Add a permission**and add the following permissions:
  - Azure DevOps: vso.project_manage
  - Azure DevOps: vso.work_write
  - Microsoft Graph: offline_access
  - Microsoft Graph: User.Read
11. In the left-side navigation, go to **Manage**> **Certificates & Secrets**.
12. Create a new client secret, select an expiration time, and save the value. See image.
13. Enter all the retrieved credentials (client ID, tenant ID, and client secret) in the **Authentication**section on the Create Azure DevOps Outegration page. A unique**Authentication ID**is generated when the authentication is successful. See image.
14. On the Azure DevOps dashboard, copy your organization's name displayed at the top of the left-side navigation.
15. On the **Projects** tab, copy the project name. See image.

[Image: Details to add a client secret]

[Image: Organization and project names in the Azure DevOps portal]

[Image: The unique authentication ID generated]

[Image: App Registration option in Azure portal]

[Image: Application ID and Directory ID details]

[Image: Redirect URI on Configure Web page]

[Image: The page to register a new application]

## Configuring the Azure DevOps Outegration

To configure the Azure DevOps outegration, complete the following steps:

- Step 1: Authenticate the Azure DevOps Connection (Connect)
- Step 2: Configure the Outegration Visibility and Behavior (Settings)
- Step 3: Map the Outegration Fields (Mapping)
- Step 4: Configure the Azure DevOps Webhook

To establish a secure connection with the Azure DevOps project, you need to authenticate with the client ID, tenant ID, and client secret you previously saved.

1. In the SecOps platform, go to **Configure** > **Outegrations**. See image.
2. Click **Create** and select **Azure DevOps**.
3. In the **Details** section: See image.
  1. **Display Name**: Enter a name for your outegration from the Azure DevOps dashboard.
  2. **Organization**: Enter the name of your organization from the Azure DevOps dashboard.
  3. **Project**: Enter the name of the project.
  4. **Authentication**: Select an existing authentication ID, or click **Create New** to set up a new authentication and enter the required parameters you retrieved earlier into the corresponding fields. See image.
  5. (Optional) **Refresh Token** and **Access Token**: Enter the values if required.
4. Click **Test** in the bottom-right corner of the page to verify the connection. If the credentials are invalid, an error message is displayed along with the remediation steps to resolve the issue.
5. After the connection is verified, click **Next**to proceed to the **Settings**step.

[Image: List of outegrations]

[Image: Azure DevOps outegration setup details]

[Image: Azure DevOps outegration authentication setup]

In this step, you need to set the SecOps entity that triggers the Azure DevOps ticket dispatch, the Azure DevOps ticket type that the SecOps ticket should be dispatched to, and when the Create Azure DevOps Ticket button should appear in the application. The Create Azure DevOps Ticket button allows users with access to SecOps tickets to dispatch these tickets to an Azure DevOps project directly from the SecOps ticket drawer or from the SecOps tickets page.

1. In the **Advanced Settings** section:
  1. **Create Azure DevOps item from**:Select the entity that you want to configure the outegration for. This selection affects the view you configure in the <Entity> **View**step (e.g., selecting Ticket displays the Tickets View setting). Other entity types might be visible depending on the apps enabled in your account. See image.
    - **UVM**: Select **Ticket**.
    - **AEM**: Select **Policy Violation** or **Violation Ticket**.
  2. **Work Item Type**:Select the work item. The schema associated with the selected work item is retrieved from your Azure DevOps project and made available for mapping in the Mapping step. See image.
2. In the <Entity> **View** section, select how the SecOps ticket should display the **Create Azure DevOps**button. This setting can be modified at any time. See image. The **Create****Azure DevOps****Ticket**button appears at two locations:
  - **Always**: Select to display the button on all tickets, allowing users to dispatch all tickets to an Azure DevOps issue.
  - **Never**: Select to hide the button in all tickets. This is useful during the outegrationsetup process to hide the button from users while still keeping the outegration active.
  - **For specific tickets**: Display the button for specific tickets. For example, if your organization uses multiple ticketing systems, you can display the button only to users who work with Azure DevOps, while excluding those using other ticketing systems (e.g., ServiceNow).
  - In the individual entity drawer (e.g., in the [UVM ticket drawer](https://help.zscaler.com/uvm/managing-tickets-uvm), in the [AEM violation ticket drawer](https://help.zscaler.com/uvm/managing-violation-tickets-aem)). See image.
  - On the entity page in the relevant application (e.g., on the [Tickets page](https://help.zscaler.com/uvm/about-tickets-operational-view-uvm) in UVM, on the [Violation Tickets page](https://help.zscaler.com/uvm/about-violation-tickets-operational-view-aem) in AEM). See image.
3. Click **Map**to proceed to the **Mapping** step.

[Image: Advanced Settings options]

[Image: List of work item types]

[Image: Tickets View details]

[Image: Create Azure DevOps Ticket button in a ticket]

[Image: Create Azure DevOps button on the Tickets page]

Map the SecOps and Azure DevOps tickets to enable exchange of data.

The objective of the mapping process is to map SecOps ticket fields (left) to Azure DevOps fields (right). To map values to fields, configure values on the left to populate the fields selected on the right.

See image.

There are three mapping components:

- Tickets initially dispatched to Azure DevOps: Map SecOps ticket fields (left) to Azure DevOps fields (right) for the initial dispatch of a ticket to an Azure DevOps issue. You can also add an attachment to your Azure DevOps issue. Commonly mapped fields include Summary, Description, Assignee, Priority, Due Date, and Status.
- (Optional) Sync from ticket to Azure DevOps: Map SecOps ticket fields (left) to Azure DevOps fields (right) for syncing ticket updates to Azure DevOps, including configuring comments and adding an attachment to your Azure DevOps ticket. Commonly mapped fields include Status and Due Date.
- (Optional) Sync from Azure DevOps to ticket: Map Azure DevOps fields (left) to SecOps ticket fields (right) for syncing Azure DevOps updates to tickets. This step also requires setting up an Azure DevOps webhook. Commonly mapped fields include Ticket Status and Ticket SLA.

The initial Azure DevOps outegration mapping includes preconfigured default mappings for each part, based on common use cases and industry best practices. These defaults can be modified and customized as needed.

### Creating a New Mapping

To create a new mapping from a SecOps ticket to an Azure Devops ticket:

1. Select a field (right): See image.
  1. Click**Mapping**.
  2. Select a field on the right. The field's schema details open on the right of the page. The schema lists the Azure DevOps fields that can be used for mapping. This is the list of fields configured in your Azure DevOps project for the Work Item Type selected in the Settings step (e.g., Bug). See image. The following details are specified for fields, when available:
    - Required
    - Input Type
    - Available Options
2. Configure the field value (left):
  1. Click **Add value** on the left. The **Field Editor** appears.
  2. In the **Field Editor**, select one of the following methods to configure the value of the field:
    - Field (Dictionary)
    - Smart Text
    - Script

Repeatthe mapping process for all required Azure DevOps fields and for any other fields you want to map.

In addition to the mapping of fields on the right to fields on the left, you can perform a set of actions when setting up the Azure DevOps outegration mapping, each relevant to a specific part of the mapping.

- Set a ticket field as mandatory.
- Add attachments to the ticket dispatch.
- Configure comments synchronization.

### Mapping Ticket Title to Summary

To illustrate the mapping process, consider the mapping of the required Azure DevOps Summary field. The final result of the mapping process should show the Summary field on the right, and the Ticket Title field on the left.

[Image: Mapping ticket title to summary]

To map the Ticket Title field to the Summary field:

1. Select **Summary**as the Azure DevOps field on the right. See image. Selecting the Azure DevOps Summary field opens the field's details in the schema. The schema specifies that the field is required and thus must be mapped before the outegration can be saved, and that the field expects a TEXT input type. Therefore, the field for which a value is being configured must also be of TEXT type. See image.
2. Select the **Ticket Title**field on the left: See image.
  1. Click**Add Value**.
  2. Under the **Field** tab, select the **Ticket Title** field, which is the equivalent to the Azure DevOps **Summary**field.

### Previewing the Ticket to Azure DevOps Mapping

After completing the SecOps ticket to Azure DevOps dispatch mapping, preview the mapping to review the configuration. This helps ensure that ticket dispatch is behaving as expected and that the Azure DevOps issue fields are populated correctly.

To preview the mapping, click **Preview**on the bottom right of the ticket initially dispatched to Azure DevOps section. The Mapping Preview window appears. On the left of the Mapping Preview window, there is a sample of the tickets in your account, organized by ticket ID. You can select, filter, or search tickets and preview the mapping to their corresponding Azure DevOps issue. You can also open the actual ticket in a new tab for a more in-depth review.

See image.

### Common Mapping Examples

These mapping examples highlight commonly used field configurations in your outegration. While some might be preconfigured by default, Zscaler recommends reviewing and customizing them to ensure they align with your workflow.

- Ticket to Azure DevOps Description
- Ticket SLA to Azure DevOps Due Date Sync
- Azure DevOps to Ticket Status Sync

[Image: Mapping fields to populate on the right]

[Image: Outegration schema field options]

The Required attribute is TRUE if a field is required by Azure DevOps. If a field is not required, the attribute is not displayed. A required Azure DevOps field is also indicated by a red asterisk (*) on the Azure DevOps field in the first mapping step.

Required Azure DevOps fields must be mapped before saving the outegration.

The Input Type specifies the data type of the Azure DevOps field, such as TEXT (e.g., Summary), DATE (e.g., Due Date), or NUMBER (e.g., Risk Score). This indicates the format that the selected source field must match to successfully map to the Azure DevOps field.

For Azure DevOps fields with fixed values, the Available Options column displays the available values. For example, if the Azure DevOps field Priority is configured to include the following fixed values—High, Low, Medium, Lowest, Highest—the corresponding values in the Ticket Severity field can be mapped to these values.

[Image: Data Mapping details]

[Image: Field details in the schema]

Select a field on the left to populate the field on the right.

The field dictionary allows you to create mappings between specific values from the field on the right and values of the field on the left. To use the dictionary, you must first select a field on the right and a field to populate it with on the left.

For example, if your Azure DevOps Priority field includes the following fixed values—Highest, High, Medium, Low, Lowest—you can use the dictionary to map the corresponding Ticket Severity values to each of the Priority field values.

See image.

[Image: Create mappings between specific values from the field on the right and values from the field on the left]

Configure the field value using free text, or create a template using a combination of free text and selected fields. This allows you to dynamically insert specific field values (e.g., Ticket SLA, Ticket Assignee, or Asset Name) into customized free text sentences or paragraphs.

To add a Smart Text field, enclose it in double curly brackets (e.g., `{{Ticket Assignee}}`). The field's display name automatically translates to its system name.

This option is commonly used to configure the value of fields like Ticket Title and Ticket Description.

See image.

[Image: Adding a Smart Text field]

For use cases that require more advanced configuration than either of the previous two methods, you can use Python scripts to configure the field value to be mapped to the target field.

[Image: Define the Ticket Title field using the Field Editor]

When dispatching tickets to Azure DevOps, map the Azure DevOps Description field with a summary of the Ticket content to provide remediation teams with a brief overview of the ticket.

To configure the Ticket to Azure DevOps Description mapping:

1. Click**Mapping**.
2. Select**Description** as the field on theright.
3. Click **Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Smart Text**.
5. Enter a ticket description, including dynamic fields (e.g., `{{SLA}}`).

Tickets dispatched to Azure DevOps will now include the configured description.

In the Ticket to Azure DevOps sync, map the Azure DevOps Due Date field to keep timelines in sync with Ticket SLA changes.

To configure the Ticket SLA to Azure Devops Due Date mapping:

1. Click**Mapping**.
2. Select**Due Date** as the field on the right.
3. Click**Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Field**, and select **Ticket SLA** as the field on the left.

Ticket SLA changes will now automatically update Azure DevOps issue due dates.

In the Azure DevOps to Ticket sync, map the Ticket Status field to ensure it's updated when remediation teams change the Azure DevOps Issue Status.

To configure the Azure DevOps to Ticket Status mapping:

1. Click**Mapping**.
2. Select**Ticket Status** as the field on the right.
3. Click**Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Field**, and select **Status** as the field on the left. Use the dictionary to map your Azure DevOps **Status** types to **Ticket Status** types.

Azure DevOps issue Status changes will now automatically update the Ticket Status.

[Image: Mapping Preview details]

You can set a SecOps ticket field as mandatory by selecting the **Set as Mandatory**checkbox in the Column Menu to the right of the mapping. Some fields can be set as mandatory by default.

See image.

Setting a field as mandatory guarantees that critical fields (e.g., Ticket Assignee) are always populated before a ticket is dispatched, so Azure DevOps tickets are always actionable for your remediation teams. Attempts to dispatch a ticket without a value in a mandatory field will trigger an error message.

Required fields are defined by your Azure DevOps schema, whereas mandatory fields are for SecOps ticket dispatch.

[Image: Select the Set as Mandatory checkbox to set a SecOps ticket field as mandatory]

You can create a file attachment that summarizes your ticket content and set the trigger to automatically add it to your Azure DevOps issue. Adding an attachment to your Azure DevOps issue simplifies the review and management of findings dispatched from a ticket.

You can configure file attachments in two of the mapping steps:

- Initial ticket dispatch See image.
- Sync from ticket to Azure DevOps See image.

When configured in the ticket to the Azure DevOps sync section, the attachment is included in the Azure DevOps issue alongside existing attachments as a downloadable file in the selected format.

Use the **File Format** drop-down menu on the top left of the attachment page to select from the available formats (**CSV**, **PDF**, **JSONL**, **Excel**).

See image.

To provide your Azure DevOps remediation teams with a comprehensive view of the findings in the ticket, consider including the following fields in your attachment:

- Finding Severity
- Finding Title
- Finding CVE
- Component Name
- Asset Name
- Asset Operating System
- Finding Optimal Fix
- Finding Description
- Finding Sources

[Image: Field details of the ticket dispatched to Azure DevOps]

[Image: Sync update details]

[Image: List of file formats to add an attachment]

In the Ticket to Azure DevOps sync step, you can configure how ticket comments are synchronized with Azure DevOps issue comments. To configure comments, click **Comment Sync**.

See image.

#### Sync Comments

Enable **Sync Comments**to automatically push comments from the ticket's Comments tab to the corresponding Azure DevOps issue.

See image.

#### Trigger Comments

Enable **Sync Trigger Comments**and set conditions to trigger a comment when specific fields are modified. Syncing trigger comments is useful when you want to be notified of important changes to tickets without updating the corresponding Azure DevOps issue. For example, you can configure a trigger to post a comment in Azure DevOps when the Ticket Severity changes from Medium to Critical.

See image.

To add a trigger condition:

1. Select the field you want to monitor (e.g., **Severity**).
2. Set the value change that should trigger the comment:
  - **From**: Select the original value.
  - **To**: Select the updated value.

When the specified change occurs in the ticket, a comment is automatically created and added to the Azure DevOps issue. The following is an example of a trigger comment:

```
Linked UVM ticket updated:
Ticket severity changed from: MEDIUM to: CRITICAL
<URL to ticket>
```

[Image: Sync ticket comments to Azure DevOps issue comments]

[Image: Enable ticket comments to push to Azure DevOps issue]

[Image: Enable to trigger comments when fields are modified]

[Image: Azure DevOps Mapping]

The Azure DevOps outegration webhook enables automatic syncing of Azure DevOps issue updates (e.g., Status or SLA changes) to their corresponding tickets, reducing the need for manual changes. This step is required when configuring the Azure DevOps to Ticket mapping to keep issues and tickets in sync. To learn more, see [Configuring the Azure Outegration Webhook.](https://help.zscaler.com/uvm/configuring-azure-devops-outegration-webhook)

See image.

An Azure DevOps webhook is only needed to sync updates from Azure DevOps to the ticket. It is not required for the initial ticket dispatch or for syncing updates from the ticket to Azure DevOps.

[Image: Sync update details]

When the outegration setup is complete, you can begin dispatching SecOps tickets using the Create Azure DevOps Ticket button that appears in the Create Ticket menu within individual tickets, as well as in the Create Issue menu in the Tickets View. To learn more, see [Creating & Managing Third-Party Tickets](https://help.zscaler.com/uvm/creating-managing-third-party-tickets).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-azure-devops-outegration-webhook","lastmod":"2026-08-16T00:48Z","nid":"1532593"} -->
## Configuring the Azure DevOps Outegration Webhook

- Source: https://help.zscaler.com/uvm/configuring-azure-devops-outegration-webhook
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the Azure DevOps Outegration Webhook
- Last modified: 2026-08-16T00:48Z
- Summary: How to configure the Azure DevOps outegration webhook for automatic syncing of tickets.

The Azure DevOps outegration webhook enables automatic syncing of Azure DevOps ticket updates such as Status or SLA changes to their corresponding Zscaler SecOps tickets, reducing the need for manual changes. This step is required when configuring the Azure DevOps to SecOps ticket mapping to keep the tickets in the two systems in sync. To learn more, see [Configuring the Azure DevOps Outegration.](https://help.zscaler.com/uvm/configuring-azure-devops-outegration)

See image.

## Configuring the Azure DevOps Webhook

To set up your Azure DevOps webhook:

1. Log in to the [Azure Portal.](https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize?redirect_uri=https%3A%2F%2Fportal.azure.com%2Fsignin%2Findex%2F&response_type=code%20id_token&scope=https%3A%2F%2Fmanagement.core.windows.net%2F%2Fuser_impersonation%20openid%20email%20profile&state=OpenIdConnect.AuthenticationProperties%3DZVjiwziwkqMm09ulCtjcFHak_lElmz1-GR3JLeWLxQsF7QjBNKvv9QvLEFH2HcEndSvxqwH8TYIz05Rs-KLkXn5DBfmJfiZ9aE2z3zu7Op006IlpU9ju4tkaKXkLjVtymohcjPOPwTCbV0AO-wSe_IcmX-SWqpR8VrT9TqiH-dKSpSt3Ors_jAkH4T7FhpT-_mDwOdyu6j1x8jas38xjKGG__EjqIp_1RCpWSEvyj4W2WsNVmo4WJHF201lUOCruzvvvkLQWVlJgCNeSPAED_sNLW0UgWO6h18vBbJAT1zaiGvOs98n5J279Fr2jEe3ubIBwpaPdljHfGmjY_3bAHUNZ1-9_Yj1mho1-kuG6Db6arWKhCzvWGeYfiM5_8dBI6io5UiU12LzaXKm8BnkFsfZ5TkLknok2_a0CCDsjB5Io_KV9PkOoCWUwmjg0Glsk-pQc9kTdIdhT_xZE4O4c4NLUlpHVyFfIDBjFOWENYUs&response_mode=form_post&nonce=638943031944613686.MDNjY2Q4MDUtMDU1OC00ZTdkLWI2NjAtMzRkNGEyOTgwMTRjNzU1ZGVkOGEtNzljYy00YWFiLWEwNWYtZWE2ZDQzMDA4MTNm&client_id=c44b4083-3bb0-49c1-b47d-974e53cbdf3c&site_id=501430&instance_aware=true&client-request-id=4632ec44-3590-4f2c-97ca-ccbf3b65c19a&x-client-SKU=ID_NET472&x-client-ver=8.3.0.0)
2. Go to **Organization**>**Projects** and select a project. The project page appears. See image.
3. Click **Project Settings** in the bottom-left corner. The **Project details**page appears. See image.
4. In the left-side navigation, go to **General**> **Service Hooks**.
5. Click **Create subscription**. The **Service**page appears.
6. On the **Service**page, select **Web Hooks**.
7. Click **Next**. The **Trigger**page appears.
8. Select an event to trigger and configure filters. Select the options based on the work item that you are configuring the outegration for. See image.
9. Click **Next**. The **Action**page appears.
10. For **URL,**enter `https://webhook.avalor.io/integration/{avalor-account-id}/azure_DevOps`. See image.
11. Click **Test**.
12. Click **Finish**if the test is successful.

After your webhook is set up, configured triggers for field updates in your Azure DevOps outegration mapping automatically sync changes made to Azure DevOps tickets with their corresponding SecOps tickets.

[Image: Shows the details on sync changes made from Azure DevOps to Zscaler SecOps ticket]

[Image: Shows the projects in the Azure DevOps portal]

[Image: Select the event to trigger and configure the filters]

[Image: Select and configure the action to perform on the Action page]

[Image: Shows the project settings page in the Azure DevOps portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-bitsight-connector","lastmod":"2026-07-05T07:06Z","nid":"1541011"} -->
## Configuring the Bitsight Connector

- Source: https://help.zscaler.com/uvm/configuring-bitsight-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Bitsight Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Bitsight connector, including setting up authentication and data retrieval filters and specifications.

Bitsight helps organizations quantify their cyber risk, measure the impact of their security efforts, and benchmark their performance against peers.

There are two Bitsight streams. Select those that are based on your Bitsight feature plan and use cases:

- Bitsight Findings: Retrieves enriched observation data related to risk types that impact a company's rating, such as compromised systems, diligence, and user behavior. To learn more, refer to the [Bitsight documentation](https://help.bitsighttech.com/hc/en-us/articles/360022913734-GET-Finding-Details).
- Bitsight Observations: Retrieves granular observation-level data, such as risk categories, compromised systems, or user behavior, similar to the forensic view in Bitsight reports. To learn more, refer to the [Bitsight documentation](https://help.bitsighttech.com/hc/en-us/articles/360016546433-GET-Detailed-Company-Observations).

To learn more, see [Zscaler UVM and Bitsight Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-bitsight-deployment-guide).

## Prerequisites

The Bitsight source authentication configuration requires an API token. The token must be associated with a user account that has read access to the relevant Bitsight data you want to ingest. The Bitsight connector can also authenticate with a Company API token. You must be an admin or VRM admin user to generate Company API tokens. To learn more, refer to the [Bitsight documentation](https://help.bitsighttech.com/hc/en-us/articles/115014888388-API-Token-Management).

To generate a Bitsight API token:

1. In the Bitsight portal, go to **Settings**> **Account**.
2. Scroll down to the **API Token**section, and click **Generate New Token**. The generated token inherits the access permissions associated with the user creating it. Ensure that the privileges of the user creating the token align with the streams being configured to enable successful data retrieval.
3. Copy the generated token to be used in the connector setup process. Save the token securely as you cannot copy or view it later.

## Configuring the Connector

To create the Bitsight data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Bitsight data source, enter the following information in the source setup Retrieval section:

- Authentication
- Grades
- Companies
- Number of days to fetch

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Bitsight Authentication** window appears.
2. In the **Bitsight Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select the finding grades to include in the scope of the retrieved data (e.g., good, fair, warn, bad, neutral). By default, all grades are retrieved if none are specified.

Enter the name of each company you want to retrieve data for. Leave this field empty to retrieve data from the parent company. Press `Enter` after each entry to add it to the list.

Enter the number of days that you want to retrieve data for during each run. This value sets the lookback period (in days) from the current date. For example, enter `30` to fetch data from the past 30 days. If left empty, all available data is retrieved.

This field is available in the Bitsight Observations stream.

[Image: Bitsight connector tiles]

[Image: Bitsight Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-black-duck-sca-connector","lastmod":"2026-07-31T07:06Z","nid":"1528221"} -->
## Configuring the Black Duck SCA Connector

- Source: https://help.zscaler.com/uvm/configuring-black-duck-sca-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Black Duck SCA Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Black Duck SCA connector, including setting up authentication and data retrieval filters and specifications.

Black Duck helps security and development teams identify and mitigate open source-related risks across application portfolios.

The Black Duck SCA connector retrieves discovered open-source component details (e.g., name, version, origin and identifier, and package URL), associated license information, and vulnerability findings with remediation metadata (e.g., CVE and BDSA IDs, descriptions, severity and scores, and published or updated timestamps).

## Prerequisites

To configure the Black Duck SCA connector, you need the source authentication configuration. The following parameters are required:

- Token
- URL

## Configuring the Connector

To create the Black Duck SCA data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Black Duck SCA data source, enter the following information in the source setup Retrieval section:

- Authentication
- Required Versions
- Pull Fixes

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

You must be an admin user to generate an access token in the Black Duck portal.

To generate a Black Duck access token:

1. In the Black Duck portal, go to **Admin** > **User Management**.
2. Create or edit the user you want to use for the API source.
  - The user should have the **Global Code Scanner** role and be a member of the relevant project.
  - In the project, the user should have the **Project Code Scanner** project role.
3. Save the user.
4. Log out of the Black Duck platform, and then log in to the Black Duck platform using the new or edited user.
5. From the user menu on the top navigation bar, select **My Access Tokens**.
6. Click **Create New Token**. The **Create Token**window appears.
7. In the **Create Token** window: See image. The API token is displayed.
  1. **Name**: Enter a name for the token.
  2. **Description**: (Optional) Enter a description for the token.
  3. **Scope**: Select an access option (**Read Access Only** is sufficient).
  4. Click **Create**.
8. Copy the generated token to be used in the connector setup process. Save the token securely as you cannot copy or view it later.

Enter the Base URL of your Black Duck instance (e.g., `https://``<Your Black Duck Instance>``.blackduck.synopsys.com`).

[Image: The Create Token page in the Black Duck platform]

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Black Duck Authentication** window appears.
2. In the **Black Duck Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Specify the project versions for data retrieval. Press `Enter` after each entry to add it to the list. Data is fetched only for the versions listed using the `{version_link}/vulnerable-bom-components` endpoint. Projects without these versions are excluded from the scope of data collection.

When no versions are specified, the connector retrieves data from the canonical version of each project, which is the default version defined in Black Duck.

Select this checkbox to include vulnerability fixes in the scope of the ingested data. When selected, data on available fixes or remediation strategies for vulnerabilities found in the specified project versions is retrieved.

[Image: Black Duck SCA tile]

[Image: Black Duck Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-burp-suite-issues-connector","lastmod":"2026-07-05T07:06Z","nid":"1527731"} -->
## Configuring the Burp Suite Issues Connector

- Source: https://help.zscaler.com/uvm/configuring-burp-suite-issues-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Burp Suite Issues Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Burp Suite Issues connector, including setting up authentication and data retrieval filters and specifications.

Burp Suite is a web application security testing toolkit for proxying, scanning, and intrusion testing.

The Burp Suite Issues connector retrieves Burp Suite sites, scans, and issues data.

The Burp Suite Issues connector is in limited availability. To learn more, contact Zscaler Support.

## Prerequisites

To configure the Burp Suite Issues connector, you need the source authentication configuration. The following parameters are required:

- API Key
- URL

## Configuring the Connector

To create the Burp Suite Issues data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Burp Suite Issues data source, enter the authentication information in the source setup Retrieval section.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Burp Suite Authentication** window appears.
2. In the **Burp Suite Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To generate a Burp Suite API key, you need to create an API user in Burp SuiteEnterprise Edition. The API key is automatically generated at the end of the user creation procedure. To create a Burp Suite API user, you must be a Burp Suite Enterprise Edition administrator.

To create an API user:

1. Log in to Burp Suite Enterprise Edition as an administrator.
2. Click **Team** > **Add a new user**.
3. Enter a name and username for the API user (e.g., `Zscaler API User`).
4. Enter an email address. You can enter the email address of the admin user.
5. From the **Choose a login type** drop-down menu, select **API key**.
6. Assign the user to the following groups and roles: Make sure that the assigned groups have the necessary permissions for the user to make API calls.
  - **Scan viewer****Role**: The API user must be a member of the Scan viewer group or a group that uses the Scan viewer role.
  - The API user must be assigned an additional role (custom role or the **Sites maintainer**role) that includes the following permissions:
    - **View sites**
    - **View site details**
7. Click **Save**.
8. When prompted, click **Copy API key**. Save the API key securely as you cannot copy or view it later.

The Burp Suite API URL is generated along with the API key when creating the API user. The expected format is `https://<Server IP>:<Port>`. For example, `https://20.65.782.40:8334/`.

If you click **Copy API link**, remove the URL path after the port number.

[Image: Burp Suite Issues tile]<

[Image: Burp Suite Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-claroty-xdome-healthcare-connector","lastmod":"2026-06-29T07:06Z","nid":"1539976"} -->
## Configuring the Claroty xDome for Healthcare Connector

- Source: https://help.zscaler.com/uvm/configuring-claroty-xdome-healthcare-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Claroty xDome for Healthcare Connector
- Last modified: 2026-06-29T07:06Z
- Summary: How to configure the Claroty xDome for Healthcare connector, including setting up authentication and data retrieval filters and specifications.

Claroty xDome for Healthcare (formerly Medigate) is a modular, SaaS-powered industrial cybersecurity platform that provides real-time visibility, vulnerability management, and threat detection to protect healthcare organizations' medical devices, networks, and systems from cyber threats.

There are two Claroty xDome for Healthcare streams. Select those that are based on your Claroty xDome for Healthcare feature plan and use cases:

- **Claroty xDome Healthcare - Assets**: Retrieves device information, including network and device details, vulnerabilities, and risk scores, as well as visibility and security posture data such as last seen and scan times, end-of-life status, and authentication user lists.
- **Claroty xDome Healthcare - Vulnerabilities:** Retrieves vulnerability data including severity scores, affected products and devices, exploitability data, and recommendations for remediation, as well as vulnerability status and assignment details.

The Claroty xDome for Healthcare connector is in limited availability. To learn more, contact Zscaler Support.

## Prerequisites

The Claroty xDome for Healthcare source authentication configuration requires an API token. To retrieve a token for the Claroty xDome connector, you must first create an API user and then generate a token for that user. Creating an API user requires admin privileges. The API user must carry at least read permissions for the sites that you want the API to access.

### Creating an API User

To create an API user:

1. Log in to the Claroty portal as an admin.
2. In the top navigation bar, click **Settings**, then select **System Settings**.
3. In the left-side navigation, click **User Management**. A list of the users in the account appears.
4. Click**Add User**. See image. The **Create User**window appears.
5. In the **Create User**window: See image.
  1. **User Type**: Select **API User**.
  2. **User Info**: Enter a username and title.
  3. **Permissions**: Make sure to assign at least read permissions for the sites that you want the API to access.
  4. Click **Create User**.

You are redirected to the User Management page, where you can generate a token for the user you created.

### Generating a Token

To generate a token:

1. Hover over the **Status** column, and click **Generate Token.** See image. The **Generate API token**window appears, displaying the API token.
2. Copy the generated token. See image. Save the token securely as you cannot copy or view it later.

## Configuring the Connector

To create the Claroty xDome for Healthcare data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Claroty xDome for Healthcare data source, enter the following information in the source setup Retrieval section:

- Authentication
- URL Region

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Claroty Authentication** window appears.
2. In the **Claroty Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

From the drop-down menu, select the region of your Claroty xDome instance. This is determined by the geographic region where your Claroty xDome platform is hosted (e.g., US, Europe, Canada, or Australia).

[Image: Add User on the User Management page]

[Image: Create User window]

[Image: Generate Token on the User Management page]

[Image: Generate API token dialog]

[Image: Claroty xDome connector tiles]

[Image: Claroty xDome Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-code42-connector","lastmod":"2026-07-31T07:06Z","nid":"1527681"} -->
## Configuring the Code42 Connector

- Source: https://help.zscaler.com/uvm/configuring-code42-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Code42 Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Code42 connector, including setting up authentication and data retrieval filters and specifications.

Code42 is a cloud-native data security platform that provides real-time visibility, detection, and response capabilities to help organizations protect sensitive data from insider threats and data leaks.

The Code42 connector retrieves alerts from configured alert rules, including details on alert destinations and alert actors.

## Prerequisites

The Code42 source authentication configuration requires a client ID, client secret, and URL (i.e., a Code42 API client). To create API clients, you must be assigned the Customer Cloud Admin role. To learn more, refer to the [Mimecast documentation](https://mimecastsupport.zendesk.com/hc/en-us/articles/42665971063059-API-clients#h_01HTZPCPPSPC94S81VPJS71YHM).

To create an API client:

1. In the Code42 console, go to **Administration** > **Integrations** > **API Clients**.
2. Click **Create new API client**. The **Create new API client** window appears.
3. In the **Create new API client** window: See image. The generated client ID, client secret, and base URL are displayed.
  1. **Client name**: Enter a name for the API credentials.
  2. **Description:**(Optional) Enter a description.
  3. **API Permissions**: For **Alerts**, select the **Read**checkbox.
  4. Click**Save**.
4. Copy the client ID, secret, and base URL to be used in the connector setup process. Save the secret securely as you cannot copy or view it later.
5. Click**Done**.

## Configuring the Connector

To create the Code42 data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Code42 data source, enter the following information in the source setup Retrieval section:

- Authentication
- Days to fetch

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

[Image: The Create new API client window in the Code42 console]

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Code42 Authentication** window appears.
2. In the **Code42 Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the number of days that you want to retrieve data for during each run. This value sets the lookback period (in days) from the current date. For example, enter `30` to fetch data from the past 30 days.

[Image: Code42 tile]

[Image: Code42 Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-crowdstrike-connector","lastmod":"2026-08-18T01:47Z","nid":"1530822"} -->
## Configuring the CrowdStrike Connector

- Source: https://help.zscaler.com/uvm/configuring-crowdstrike-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the CrowdStrike Connector
- Last modified: 2026-08-18T01:47Z
- Summary: How to configure the CrowdStrike connector, including setting up authentication and data retrieval filters and specifications.

CrowdStrike is a cybersecurity platform that communicates and shares information with other security tools and platforms, enhancing threat detection and response capabilities.

There are 5 CrowdStrike streams. Select those that are based on your CrowdStrike feature plan and use cases:

- CrowdStrike Vulnerabilities: Retrieves vulnerabilities, which includes IDs, severity levels, affected systems, and descriptions to facilitate risk assessment across the environment.
- CrowdStrike Managed Hosts: Retrieves devices managed by CrowdStrike (i.e., endpoints running the Falcon sensor).
- CrowdStrike Environmental Assets: Retrieves assets in your environment, which includes those unsupported and unmanaged by CrowdStrike.
- CrowdStrike Incidents: Retrieves security incidents, which includes incident IDs, timestamps, status, severity, descriptions, affected resources, and other details related to the incident's lifecycle and context.
- CrowdStrike Alerts: Retrieves security alerts, which include activity IDs, timestamps, alert descriptions, affected resources, source account information, location, and severity.

CrowdStrike Incidents and CrowdStrike Alerts data is used to enrich Vulnerabilities and Assets data.

For the CrowdStrike CSPM connector, see [Configuring the CrowdStrike CSPM Connector](https://help.zscaler.com/uvm/configuring-crowdstrike-cspm-connectors). For the CrowdStrike Identity Protection connector, see [Configuring the CrowdStrike Identity Protection Connector](https://help.zscaler.com/uvm/configuring-crowdstrike-identity-protection-connectors).

To learn more, see [Zscaler and CrowdStrike Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-crowdstrike-deployment-guide).

## Prerequisites

To configure the CrowdStrike connector, you need the source authentication configuration. The following parameters are required:

- Client ID
- Client Secret

To create the API keys, you need to have a Falcon Administrator role. To learn more, see [Zscaler and CrowdStrike Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-crowdstrike-deployment-guide).

## Configuring the Connector

To create the CrowdStrike data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the CrowdStrike data source, enter the following information in the source setup Retrieval section:

- Authentication
- Assets Types
- (Optional) CrowdStrike cloud region
- Number of days to fetch
- Product
- (Optional) Split Finding by Apps
- CVE Severity
- (Optional) Fetch vulnerabilities from the past selected days

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **CrowdStrike Authentication** window appears.
2. In the **CrowdStrike Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

The Assets Types drop-down menu allows you to select the asset types to include in the scope of the ingested data.

- Hosts: A traditional computing device (e.g., desktop computer, laptop, or server).
- IoT: Internet-connected devices that are not traditional computers (e.g., cameras or printers).

By default, both Hosts and IoT devices are retrieved. If you only have Hosts, select Hosts to filter the results.

This menu is available in the CrowdStrike Managed Hosts stream.

The CrowdStrike cloud region drop-down menu allows you to specify your CrowdStrike cloud region and corresponding base URL. The available CrowdStrike cloud regions and their corresponding base URLs are listed in the following table. If left unselected, the default region is US1. `https://api.crowdstrike.com` also supports the US2 and EU1 regions.

| **Region** | **Base URL** |
| --- | --- |
| US1 | `https://api.crowdstrike.com` |
| US2 | `https://api.us-2.crowdstrike.com` |
| EU1 | `https://api.eu-1.crowdstrike.com` |
| USGOV1 | `https://api.laggar.gcw.crowdstrike.com` |
| USGOV2 | `https://api.us-gov-2.crowdstrike.mil` |

If you cannot see all the scopes outlined in the cloud region table when creating the API keys, you might not have access to the relevant CrowdStrike module. The available scopes are determined by the products your organization is subscribed to and the cloud region where your account is hosted. Only scopes corresponding to your subscribed products and cloud region are visible. This discrepancy suggests that the connector you are attempting to set up might not be compatible with your current CrowdStrike configuration.

This menu is available in the CrowdStrike Managed Hosts stream.

The Number of days to fetch field allows you to choose the number of days you want to retrieve on each run.

This field is available in the CrowdStrike Incidents and CrowdStrike Alerts streams.

The Product drop-down menu includes EPP, IDP, Mobile, XDR, Overwatch, and CWPP. The default is All Products.

This menu is available in the CrowdStrike Alerts stream.

The Split Finding by Apps checkbox allows you to separate records containing more than one app into individual entries.

This checkbox is available in the CrowdStrike Vulnerabilities stream.

The CVE Severity drop-down menu allows you to select the severity level of vulnerabilities to include in the scope of the ingested data.

This menu is available in the CrowdStrike Vulnerabilities stream.

The Fetch vulnerabilities from the past selected days field allows you to adjust the number of days for which you want to retrieve vulnerabilities on each run.

This field is available in the CrowdStrike Vulnerabilities stream.

[Image: The CrowdStrike - Incidents, CrowdStrike - Managed Hosts, CrowdStrike - Environment Assets, CrowdStrike Vulnerabilities, and CrowdStrike Alerts tiles]

[Image: The CrowdStrike Authentication window displaying the Name, Client Id, and Client Secret fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-crowdstrike-cspm-connector","lastmod":"2026-07-05T07:06Z","nid":"1530835"} -->
## Configuring the CrowdStrike CSPM Connector

- Source: https://help.zscaler.com/uvm/configuring-crowdstrike-cspm-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the CrowdStrike CSPM Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the CrowdStrike CSPM connector, including setting up authentication and data retrieval filters and specifications.

CrowdStrike is a cybersecurity platform that communicates and shares information with other security tools and platforms, enhancing threat detection and response capabilities. CrowdStrike cloud security posture management (CSPM) provides visibility into your cloud security and strengthens your compliance posture.

There are two available CrowdStrike CSPM streams. Select those that are based on your CrowdStrike CSPM feature plan and use cases:

- CrowdStrike CSPM Indicators of Misconfiguration (IOM): Flags suspicious or unauthorized usage patterns within monitored systems or networks.
- CrowdStrike CSPM Indicators of Attack (IOA): Flags potential signs of active threats or malicious activities detected by CrowdStrike.

For the CrowdStrike connector, see [Configuring the CrowdStrike Connector](https://help.zscaler.com/uvm/configuring-crowdstrike-connectors). For the CrowdStrike Identity Protection connector, see [Configuring the CrowdStrike Identity Protection Connector](https://help.zscaler.com/uvm/configuring-crowdstrike-identity-protection-connectors).

To learn more, see [Zscaler and CrowdStrike Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-crowdstrike-deployment-guide).

## Prerequisites

To configure the CrowdStrike CSPM connector, you need the source authentication configuration. To create the client ID and client secret, you need to have a Falcon Administrator role. The following parameters are required:

- Client ID and Client Secret

To generate the CrowdStrike client ID and secret key:

1. Log in to the CrowdStrike console.
2. Select the **Menu** icon from the top-left navigation.
3. Go to **Support and resources** > **API clients and keys**.
4. On the **OAuth2 API clients** page, click **Create API client**.
5. In the **Create API client** window: See image.
  - **Client Name**: Enter a name.
  - **Description**: Enter a description.
  - In the **Scope** table, select the **Read** checkbox for **CSPM Registration**.
6. Click **Create**.
7. Save the client ID and client secret securely. The client secret is unavailable after you close the window.

## Configuring the Connector

To create the CrowdStrike CSPM data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the CrowdStrike CSPM data source, enter the following information in the source setup Retrieval section:

- Authentication
- Cloud Provider

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **CrowdStrike Authentication** window appears.
2. In the **CrowdStrike Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Set the Cloud Provider drop-down menu filters and specifications. Select the cloud provider (i.e., AWS, Azure, or GCP) that you configured CrowdStrike CSPM to scan and monitor for security posture.

[Image: The CrowdStrike CSPM - IOM and CrowdStrike CSPM - IOA tiles]

[Image: The CrowdStrike Authentication window displaying the Name, Client Id, and Client Secret fields in the SecOps platform]

[Image: The Add new API client window in the CrowdStrike platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-crowdstrike-identity-protection-connector","lastmod":"2026-08-18T01:49Z","nid":"1530837"} -->
## Configuring the CrowdStrike Identity Protection Connector

- Source: https://help.zscaler.com/uvm/configuring-crowdstrike-identity-protection-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the CrowdStrike Identity Protection Connector
- Last modified: 2026-08-18T01:49Z
- Summary: How to configure the CrowdStrike Identity Protection connector, including setting up authentication and data retrieval filters and specifications.

CrowdStrike is a cybersecurity platform that communicates and shares information with other security tools and platforms, enhancing threat detection and response capabilities. CrowdStrike Identity Protection provides identity threat detection and response (ITDR) and endpoint security.

There are two CrowdStrike Identity Protection streams. Select those that are based on your CrowdStrike Identity Protection feature plan and use cases:

- CrowdStrike Identity Protection Security Assessment: Retrieves security risk details, including factors, likelihood, severity, recommendations, and overall assessment scores for risk evaluation.
- CrowdStrike Identity Protection Domain Users: Retrieves user data, including display names, risk scores, admin status, and archival status for identity protection insights.

For the CrowdStrike connector, see [Configuring the CrowdStrike Connector](https://help.zscaler.com/uvm/configuring-crowdstrike-connectors). For the CrowdStrike CSPM connector, see [Configuring the CrowdStrike CSPM Connector](https://help.zscaler.com/uvm/configuring-crowdstrike-cspm-connectors).

To learn more, see [Zscaler and CrowdStrike Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-crowdstrike-deployment-guide).

## Prerequisites

To configure the CrowdStrike Identity Protection connector, you need the source authentication configuration. To create the client ID and client secret, you need to have a Falcon Administrator role. The following parameters are required:

- Client ID and Client Secret

To generate the CrowdStrike client ID and secret key:

1. Log in to the CrowdStrike console.
2. Go to **Menu** > **Support and Resources** > **API clients and keys**.
3. On the **API clients and keys** page, on the **OAuth2 API clients** tab, click **Create API client**.
4. In the **Create API client** window: See image.
  - **Client Name**: Enter a name.
  - **Description**: Enter a description.
  - In the **Scope** table, set the following permissions:
    - **Identity Protection Assessment**: Select the **Read** checkbox.
    - **Identity Protection GraphQL**: Select the **Write** checkbox.
    - **Identity Protection Entities**: Select the **Read** checkbox. This is for domain users.
5. Click **Create**.
6. Save the client ID and client secret securely. The client secret is unavailable after you close the window.

## Configuring the Connector

To create the CrowdStrike Identity Protection data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the CrowdStrike Identity Protection data source, enter the following information in the source setup Retrieval section:

- Authentication
- Fetch Only Active Directory Domains

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **CrowdStrike Authentication** window appears.
2. In the **CrowdStrike Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Set the Fetch Only Active Directory Domains checkbox filters and specifications.

This checkbox is available in the CrowdStrike Identity Protection Security Assessment stream.

[Image: The CrowdStrike Identity Protection - Security Assessment and CrowdStrike Identity Protection - Domain Users tiles]

[Image: The CrowdStrike Authentication window displaying the Name, Client Id, and Client Secret fields in the SecOps platform]

[Image: The Create API client window accessed from the API clients and keys page in the CrowdStrike console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-cycognito-connector","lastmod":"2026-07-05T07:06Z","nid":"1527666"} -->
## Configuring the CyCognito Connector

- Source: https://help.zscaler.com/uvm/configuring-cycognito-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the CyCognito Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the CyCognito connector, including setting up authentication and data retrieval filters and specifications.

CyCognito is an exposure management platform that reduces risk by discovering, testing, and prioritizing security issues.

There are two CyCognito streams. Select those that are based on your CyCognito feature plan and use cases:

- CyCognito Issues: Retrieves a detailed and actionable overview of security issues across your enterprise's digital assets.
- CyCognito Assets: Retrieves a comprehensive view of your enterprise's digital assets.

The CyCognito connector is in limited availability. To learn more, contact Zscaler Support.

## Prerequisites

To configure the CyCognito connector, you need the source authentication configuration. The following parameters are required:

- API Key
- URL

## Configuring the Connector

To create the CyCognito data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the CyCognito data source, enter the following information in the source setup Retrieval section:

- Authentication
- Assets Type
- Asset Status (Optional)
- Issues Status (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

You must be an admin or anIT user to create a CyCognito API key.

To create an API key:

1. In the CyCognito portal, go to **Workflow & Integration**> **API Key Management**. See image.
2. Click the **Add API key**icon. See image. The **Generate new API key**window appears.
3. In the **Generate new API key**window: See image.
  1. **Key name**: Enter name for the API key.
  2. **Key access**: Select **Read only.**
  3. **Set expiration**: (Optional) Enable and set an expiration date for the API key. If an expiration date is set on the API key, make sure to refresh the key when it expires to ensure continuous connectivity.
  4. Click **Create**.
4. Copy the generated API key. Save the API key securely as you cannot copy or view it later.

The base URL of your CyCognito instance prefixed with `api`.

Examples include:

- `https://api.cycognito.com`
- `https://api.platform.cycognito.com`
- `https://api.us-platform.cycognito.com`

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **CyCognito Authentication** window appears.
2. In the **CyCognito Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select the type of assets that you want to include in the scope of the ingested data. Asset types include IP addresses, domains, certificates, web applications, and IP address ranges.

This filter is available in the CyCognito Assets stream.

Select the asset statuses you want to include in the scope of the ingested data. If left empty, the connector defaults to retrieving only assets with a new status. Available statuses are:

- New: Recently discovered by CyCognito and not yet seen in a previous scan.
- Changed: The asset has been modified, or its properties have changed since the last scan.
- Normal: The asset is known, active, and unchanged since the last scan.
- Removed: The asset is no longer detected on your attack surface (inactive or decommissioned).

This filter is available in the CyCognito Assets stream.

Select the issue statuses that you want to include in the scope of the ingested data. If left empty, the connector defaults to retrieving only issues with a new status. Available statuses are New, Normal, Issue-Removed, Asset-Removed, and Asset-Not-Alive.

This drop-down menu is available in the CyCognito Issues stream.

**[Image: API Key Management option in the CyCognito portal]**

[Image: API Key Management page]

**[Image: Generate new API key window]**

[Image: CyCognito connector tiles]

[Image: CyCognito Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-cyera-connector","lastmod":"2026-07-03T07:06Z","nid":"1541015"} -->
## Configuring the Cyera Connector

- Source: https://help.zscaler.com/uvm/configuring-cyera-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Cyera Connector
- Last modified: 2026-07-03T07:06Z
- Summary: How to configure the Cyera connector, including setting up authentication and data retrieval filters and specifications.

Cyera is a Data Security Posture Management (DSPM) tool that uses native APIs to discover, classify, and secure sensitive data across IaaS, PaaS, SaaS, and on-premises environments.

There are two Cyera streams. Select those that are based on your Cyera feature plan and use cases:

- Cyera Assets: Retrieves metadata and security details for assets and data stores, including sensitivity levels, ownership, and security measures.
- Cyera Issues: Retrieves security and compliance issues related to data misconfigurations and vulnerabilities across assets. It includes risk levels, remediation advice, and issue statuses.

## Prerequisites

The Cyera source authentication configuration requires a client ID and client secret. To configure this integration, you must have:

- A Cyera Admin role.
- An active Cyera DSPM license.

To generate a client ID and client secret:

1. Log in to the Cyera portal.
2. In the left-side navigation, click **Integrations**.
3. Select **Zscaler**to view the integration details.
4. In the **API Token Generator**section, review the prepopulated **Token Description**.
5. From the **Token Expiration**drop-down menu, select a duration (e.g., **1 day**, **7 days**, **30 days**, **90 days**, or **365 days**). Make sure to refresh the token when it expires to ensure continuous connectivity.
6. Click **Generate Token**.
7. Copy the generated client ID and client secret, or click **Download CSV**to download both values. Save the client ID and client secret securely as you cannot copy or view them later.

## Configuring the Connector

To create the Cyera data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Cyera data source, select an authentication in the source setup Retrieval section.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Cyera Authentication** window appears.
2. In the **Cyera Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

[Image: Cyera connector tiles]

[Image: Cyera Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-datto-connector","lastmod":"2026-07-03T07:06Z","nid":"1538697"} -->
## Configuring the Datto Connector

- Source: https://help.zscaler.com/uvm/configuring-datto-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Datto Connector
- Last modified: 2026-07-03T07:06Z
- Summary: How to configure the Datto connector, including setting up authentication and data retrieval filters and specifications.

Datto is a secure cloud-based Remote Monitoring and Management (RMM) platform that monitors devices such as servers, VMs, ESXi, PCs, laptops, and network devices in real time, alerting on issues and flagging potential problems.

There are two Datto streams. Select those that are based on your Datto feature plan and use cases:

- Datto Alerts: Retrieves a list of open alerts in your Datto account, including alert ID, alert type, severity, affected device and site, timestamp, and muted status, with an option to include muted open alerts.
- Datto Devices: Retrieves an inventory of devices in your Datto account, including device ID, hostname, site, device type, online or offline status, operating system details, IP address, and last check-in time.

The Datto connector is in limited availability. To learn more, contact Zscaler Support.

## Prerequisites

The Datto source authentication configuration requires an API key and an API secret. Before you begin configuration, make sure you have met the following prerequisites:

- You are an administrator, or you have permissions to access Setup and manage Global Settings and Users.
- API access is enabled for the Datto RMM account.

### Enabling API Access

To enable API access:

1. In the Datto platform, go to **Setup**> **Global Settings**> **Access Control**.
2. Select **Enable API Access**. See image.

### Creating an API Key and Secret

To create an API token for a specific user:

1. In the Datto platform, go to **Setup** > **Users**.
2. Select the user you want the generated API key and API secret to be associated with.
3. Click **Generate API Keys**. The **API key** and **API Secret Key** are displayed.
4. Copy the generated API key and API secret to be used in the connector setup process. See image. Save the secret securely as you cannot copy or view it later.
5. Click **Save User**.

To learn more, refer to the [Datto documentation](https://rmm.datto.com/help/en/Content/2SETUP/APIv2.htm).

## Configuring the Connector

To create the Datto data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Datto data source, enter the following information in the source setup Retrieval section:

- Authentication
- Include Muted Alerts (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Datto Authentication** window appears.
2. In the **Datto Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

By default, only unmuted open alerts are retrieved. Select this checkbox to include muted open alerts in the data retrieved.

This filter is available in the Datto Devices stream.

[Image: Selecting the Enable API access checkbox on the Access Control page of the Datto plaform]

[Image: Copying the Datto API key and API secret]

[Image: Datto connector tiles]

[Image: Datto Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-digital-shadows-connector","lastmod":"2026-07-31T07:06Z","nid":"1528181"} -->
## Configuring the Digital Shadows Connector

- Source: https://help.zscaler.com/uvm/configuring-digital-shadows-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Digital Shadows Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Digital Shadows connector, including setting up authentication and data retrieval filters and specifications.

Digital Shadows SearchLight is a digital risk protection platform that monitors criminal forums, chat platforms, and code-sharing sites across the open, deep, and dark web. It aims to secure an organization's business and reputation by identifying and mitigating threats related to cybersecurity, data exposure, and brand risks.

There are two Digital Shadows streams. Select those that are based on your Digital Shadows feature plan and use cases:

- Digital Shadows: Retrieves triage item events, triage item details (e.g., title, severity, status, and source identifiers), and the associated alert and or incident records with their key metadata and descriptions.
- Digital Shadows Assets: Retrieves asset events and detailed asset records for assets such as domains and IP addresses, including asset type, value, status, created and updated timestamps, and archive state.

## Prerequisites

To configure the Digital Shadows connector, you need the source authentication configuration. The following parameters are required:

- API Key and API Secret
- Account ID

## Configuring the Connector

To create the Digital Shadows data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Digital Shadows data source, enter the following information in the source setup Retrieval section:

- Authentication
- Days to Fetch
- Finding Type

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To enable and retrieve your API credentials and Account ID, contact your Digital Shadows representative. You need a user with the Managing of API Accounts permission to be granted an API key.

When API access is enabled, go to Key words > Account > SearchLight Account ID to find your account ID on the API documentation page.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Digital Shadows Authentication** window appears.
2. In the **Digital Shadows Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the number of days that you want the connector to retrieve data for during each run. For example, enter `7` to fetch data from the past 7 days. By default, the connector retrieves data from the last 30 days. Selecting a larger date range can lead to longer processing times or potential timeouts.

Select the finding types that you want the connector to retrieve during each run (Alerts, Incidents, or both). By default, the connector retrieves Alerts.

This field is available in the Digital Shadows stream.

[Image: Digital Shadow connector tiles]

[Image: Digital Shadow Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-discovery-settings","lastmod":"2026-05-22T07:06Z","nid":"1538686"} -->
## Configuring Discovery Settings

- Source: https://help.zscaler.com/uvm/configuring-discovery-settings
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > External Attack Surface Management for UVM > Configuring Discovery Settings
- Last modified: 2026-05-22T07:06Z
- Summary: How to configure asset discovery settings in Zscaler's AEM and UVM apps to identify and manage your internet-exposed assets using Zscaler's External Attack Surface Management (EASM) capabilities.

Zscaler's External Attack Surface Management (EASM) capabilities require you to create discovery profiles and configure seeds to initiate the discovery process for internet-exposed assets. A seed is a known, legitimate asset belonging to your organization—such as a domain, IP address, or IP address block—that can be used to discover other assets linked with your organization. You can also use an organization's name as a seed to find related domains by performing a reverse domain lookup.

You can create a discovery profile by including or excluding specific seeds across different asset types. This discovery profile is used to scan for asset connections that are associated with the seeds. Discovery profiles add a layer of granularity to tracking and managing your attack surface by grouping relevant seeds. For example, you can create distinct discovery profiles for different business entities (e.g., parent company, subsidiaries) to individually manage the corresponding attack surfaces.

When you configure and activate your discovery profile, the discovery engine starts scanning the internet for asset connections using seeds to map your organization's attack surface and build an inventory of the internet-exposed assets. After the scan is completed, you can view the unified asset inventory in Asset Exposure Management (AEM), and the risks identified for the assets and enriched security insights in Unified Vulnerability Management (UVM). The asset scan is performed weekly, and the asset and findings data in AEM and UVM is updated to reflect the latest information. In addition, when seeds within a discovery profile are modified, a new scan is triggered to update the asset inventory based on the new set of seeds. Outdated or irrelevant assets are identified and marked inactive depending on your [asset aging](https://help.zscaler.com/uvm/aging-assets) configuration.

- To access Zscaler's EASM capabilities, your organization must have either AEM or UVM, or both. EASM functionalities vary between these apps, with AEM focusing on asset inventory and policy coverage to reduce overall risk, and UVM simplifying the management of associated vulnerabilities and remediating risks from a centralized platform.
- To configure and manage EASM Discovery Settings, your assigned role must include Read, Create, Edit, and Delete permissions for the EASM Configuration resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-managing-role-permissions) and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users).

The following section explains how you can configure and manage EASM discovery settings. To learn more about EASM capabilities, see [Understanding Zscaler EASM Capabilities](https://help.zscaler.com/uvm/understanding-zscaler-easm-capabilities) and [Understanding Asset Discovery with Zscaler EASM](https://help.zscaler.com/uvm/understanding-asset-discovery-zscaler-easm).

## Creating a Discovery Profile

To create a discovery profile:

1. In the Zscaler Security Operations (SecOps) platform, go to **Configure**> **EASM Settings**. See image. You can also access this page from **Assets** > **Settings** in AEM, or **Vulnerabilities** > **Settings** in UVM.
2. If you are configuring your first discovery profile, click **Create a profile**. If one or more profiles already exist, click **New Profile**. The **Create Profile** window appears.
3. In the **Create Profile** window:
  1. **Name**: Enter a name for the discovery profile.
  2. **Scan Frequency**: Scanning frequency is automatically set to **Weekly** and cannot be changed.
  3. Select the type of seed asset you want to configure: A discovery profile can have a maximum of 100 seeds across the seed types.
    - Domains
    - IP Addresses
    - IP Blocks
    - Organizations
4. Click **Save**. The discovery profile is saved and displayed on the **EASM Settings** page. On this page, you can view your configured discovery profiles along with their name, individual counts of included and excluded seeds, last scan timestamp, and scan status. The scanning process typically takes a few hours, and you can track the scan status, including progress, successful completion, and failure, under the **Status** column. See image.

After scanning is completed, you can view the ingested assets on the [Assets](https://help.zscaler.com/uvm/about-assets-uvm) page and the associated risk detections on the [Findings](https://help.zscaler.com/uvm/about-findings-operational-view-uvm) page.

1. Select **Included Domains** or **Excluded Domains**to include or exclude specific domains from scanning, respectively. To exclude specific domains, you must first include at least one seed (of any type) to define the target for initiating scanning.
2. You can either upload a CSV file containing the domain names or manually enter them: Domains, subdomains, top-level domains, and punycode extensions are supported. You can use the search option to locate specific domains within include or exclude lists. To remove an entry, click the **Delete** icon displayed for the field. See image.
  - To use a CSV file, click **Upload .CSV File** and select the required file. Ensure that the file contains valid domains, with each entry listed in a separate row. A header row is not required.
  - To manually enter the domain names:
    1. Click **Add Domains**. The **Add Domains** drawer appears.
    2. In the **Add Domains** drawer, enter the domain name or subdomain (e.g., `acme.com` or `support.acme.com`) in the text field. To add multiple entries, click **Add More**.
    3. Click **Done**. See image.
3. Click **Save**.

1. Select **Included IP Addresses** or **Excluded IP Addresses** to include or exclude specific IP addresses from scanning, respectively. To exclude specific IP addresses, you must first include at least one seed (of any type) to define the target for initiating scanning.
2. You can either upload a CSV file containing the IP addresses or manually enter them: The IP address must be a strictly formatted IPv4 address (e.g., `192.0.2.2`). Subnet mask, port number, and CIDR notation (e.g., appending `/24` is invalid) are not supported. IPv6 addresses are also not supported. You can use the search option to locate specific IP addresses within include or exclude lists. To remove an entry, click the **Delete** icon displayed for the field. See image.
  - To use a CSV file, click **Upload .CSV File** and select the required file. Ensure that the file contains valid IP addresses, with each entry listed in a separate row. A header row is not required.
  - To manually enter the IP addresses:
    1. Click **Add IP Addresses**. The **Add IP Addresses** drawer appears.
    2. In the **Add IP Addresses**drawer, enter the IP address (e.g., `192.0.2.24`) in the text field. To add multiple entries, click **Add More**.
    3. Click **Done**. See image.
3. Click **Save**.

1. Select **Included IP Blocks**or **Excluded IP Blocks**to include or exclude specific IP address blocks from scanning, respectively. To exclude specific IP address blocks, you must first include at least one seed (of any type) to define the target for initiating scanning.
2. You can either upload a CSV file containing the IP address blocks or manually enter them: Ensure that the IP address blocks are valid and meet specific, predefined conditions. See validation criteria. You can use the search option to locate specific IP address blocks within include or exclude lists. To remove an entry, click the **Delete** icon displayed for the field. See image.
  - To use a CSV file, click **Upload .CSV File** and select the required file. Ensure that the file contains valid IP address blocks, with each entry listed in a separate row. A header row is not required.
  - To manually enter the IP address blocks:
    1. Click **Add IP Blocks**. The **Add IP Blocks**drawer appears.
    2. In the **Add IP Blocks**drawer, enter the IP address block (e.g., `192.0.2.0/32`) in the text field. To add multiple entries, click **Add More**.
    3. Click **Done**. See image.
3. Click **Save**.

1. Select **Included Organizations**or **Excluded Organizations**to include or exclude specific organizations from scanning, respectively. To exclude specific organizations from scanning, you must first include at least one seed (of any type) to define the target for initiating scanning.
2. Click **Add Organizations**. The **Add Organizations**drawer appears. You can use the search option to locate specific organizations within include or exclude lists. To remove an entry, click the **Delete** icon displayed for the field. See image.
  1. In the **Add Organizations**drawer:
    1. Enter the organization name (e.g., `Acme Inc.`) in the text field. The organization name must have a minimum length of 4 characters. To add multiple entries, click **Add More**.
    2. Click **Done**. See image.
3. Click **Save**.

- The IP address block must be formatted as a valid IP address followed immediately by a forward slash (`/`) and then a routing prefix (i.e., subnet mask).
- The IP address portion must strictly adhere to the formatting rules for IPv4 addresses.
- The subnet mask portion must be a valid, unsigned integer. Explicit positive or negative signs (e.g., `+24` or `-24`) are not allowed.
- The subnet mask must be between `20` and `32`, inclusive of these numbers (e.g., `192.0.2.0/32`). Subnet masks between `0` and `19`, including these numbers, are not allowed.

[Image: Discovery profiles configured in the EASM Settings page]

[Image: Add seed domains to discovery profile]

[Image: Add seed domains to discovery profile]

[Image: Add seed IP addresses to discovery profile]

[Image: Add seed IP addresses to discovery profile]

[Image: Add seed IP address blocks to discovery profile]

[Image: Add seed IP address blocks to discovery profile]

[Image: Add organization names to discovery profile]

[Image: Add organization names to discovery profile]

[Image: Adding new discovery profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-dragos-connector","lastmod":"2026-03-31T07:06Z","nid":"1535061"} -->
## Configuring the Dragos Connector

- Source: https://help.zscaler.com/uvm/configuring-dragos-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Dragos Connector
- Last modified: 2026-03-31T07:06Z
- Summary: How to configure the Dragos connector, including setting up authentication and data retrieval filters and specifications.

The Dragos connector is in limited availability. To learn more, contact Zscaler Support.

Dragos specializes in cybersecurity for Industrial Control Systems (ICS) and Operational Technology (OT), providing advanced threat detection, vulnerability management, and incident response solutions to protect critical infrastructure sectors.

There are two Dragos streams. Select those that are based on your Dragos feature plan and use cases:

- Dragos Assets: Retrieves asset information from the Dragos API, including asset metadata such as ID, name, type, criticality, Purdue level, firmware, OS details, and site information.
- Dragos Vulnerabilities: Retrieves vulnerability data associated with OT assets, including CVEs, CVSS scores, severity levels, mitigations, remediations, operating systems, and impacted software packages. Each vulnerability record is linked with its associated assets for easy mapping.

## Prerequisites

To configure the Dragos connector, you need the source authentication configuration. Choose one of the following authentication types and retrieve the required parameters for it:

- API Key API Secret URL
- URL Username Password

## Configuring the Connector

To create the Dragos data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Dragos data source, enter the following information in the source setup Retrieval section:

- Authentication
- Severity (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

Use this authentication type to connect using an API key.

The following parameters are required:

- API Key and API Secret
- URL

Use this authentication type to connect using a Dragos username and password.

The following parameters are required:

- Username and Password
- URL

You must be an admin user to generate API keys in the Dragos portal.

To generate an API key and API secret:

1. In the Dragos portal, go to **Admin**> **Users**.
2. Select the user you want the generated API key and API secret to be associated with. The selected user must have the **asset:read** and **vulnerability:read** permissions to ensure access to the Dragos data you want to ingest.
3. Select the **Authentication**tab.
4. Under the **API Keys**section, click **Add New API Key**. The **Generate New API Key** section appears.
5. In the **Generate New API Key** section: See image. A dialog window appears and displays the generated API key (i.e., the API key ID) and the API secret.
  1. Enter a name for the API key.
  2. Click **Generate Key**.
6. Copy the generated API key and API secret to be used in the connector setup process. Save the secret securely as you cannot copy or view it later.

The base URL for accessing your Dragos instance (e.g., `https://``<Your Dragos Instance>``.dragos.cloud.com`).

The username and password associated with a Dragos user with the **asset:read**and **vulnerability:read**permissions to ensure access to the Dragos data you want to ingest.

The base URL for accessing your Dragos instance (e.g., `https://``<Your Dragos Instance>``.dragos.cloud.com`).

[Image: Dragos Generate New API Key window]

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Dragos Authentication** window appears.
2. In the **Dragos Authentication** window, select an authentication type and enter the information from the Prerequisites section.
  - API Key API Secret URL See image.
  - URL Username Password See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

By default, the connector retrieves vulnerabilities of all severity levels. Use the Severity drop-down menu to specify which levels to include in the scope of the ingested data (e.g., Critical, High, Medium).

This drop-down menu is available in the Vulnerabilities stream.

[Image: Dragos connector tiles]

[Image: Dragos Authentication window with API Key API Secret URL Authentication Type]

[Image: Dragos Authentication window with URL Username Password Authentication Type]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-endor-labs-connector","lastmod":"2026-03-06T06:06Z","nid":"1534051"} -->
## Configuring the Endor Labs Connector

- Source: https://help.zscaler.com/uvm/configuring-endor-labs-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Endor Labs Connector
- Last modified: 2026-03-06T06:06Z
- Summary: How to configure the Endor Labs connector, including setting up authentication and data retrieval filters and specifications.

The Endor Labs connector is in limited availability. To learn more, contact Zscaler Support.

Endor Labs provides solutions for managing software security challenges in open-source and AI-generated code, and offers tools to identify and resolve vulnerabilities effectively.

There are 5 Endor Labs streams. Select those that are based on your Endor Labs feature plan and use cases:

- Endor Labs - Containers: Retrieves container-related findings, including vulnerabilities in container images, dependency metadata, namespace details, and associated project information.
- Endor Labs - SAST: Retrieves Static Application Security Testing (SAST) findings, including unique IDs, impacted code components, vulnerability metadata, and namespace associations.
- Endor Labs - SCA Findings: Retrieves Software Composition Analysis (SCA) findings, including vulnerable dependencies, their associated projects, and metadata for impacted components.
- Endor Labs - Secrets: Retrieves findings related to exposed secrets, including unique IDs, secret types (e.g., API keys, tokens), metadata for impacted resources, and associated project details.
- Endor Labs - Vulnerabilities: Retrieves all vulnerability types, and includes detailed metadata like unique IDs, updated timestamps, severity levels, impacted components, and remediation suggestions.

The Query API is used for the Containers, SAST, SCA Findings, and Secrets streams, as it is optimized for handling large data volumes and supports namespace traversal, allowing data retrieval from parent namespaces and their child namespaces in a single operation. In contrast, the REST API is used for the Vulnerabilities stream which is optimal for smaller datasets that don't require namespace traversal, making it suitable for direct and efficient retrieval of vulnerability information.

## Prerequisites

The source authentication configuration requires the API key and API secret parameters.

To generate the Endor Labs API key and API secret from the Endor Labs portal:

1. In the left-side navigation, go to **Access Control**.
2. Select **API Keys**.
3. Click **Generate API Key**. By default, API keys are scoped to the current namespace and its child namespaces. You can modify this scope after creation if needed. To learn more, refer to the [Endor Labs documentation](https://docs.endorlabs.com/administration/api-keys/).
4. Enter the following details:
  1. **Name**: Enter a name for the API key, identifying its purpose or user.
  2. **Permission Level**: Select the **Read-only**role or higher. The Read-only role grants view access, without the permissions to modify or create resources. To learn more, refer to the [Endor Labs documentation](https://docs.endorlabs.com/administration/access-endorlabs/authorization-roles/). The API key and API secret must carry the Read-only role or higher.
5. Select an expiration date for the API key, ranging from 30 to 90 days.
6. Click **Generate API Key**.
7. Click **Copy API Key & Secret**. Save the API key and API secret. They are not available again after you leave this page.

You can also generate the API key and API secret through an API. If you don't have permissions to create API credentials, contact your Endor Labs admin. To learn more, refer to the [Endor Labs documentation](https://docs.endorlabs.com/administration/api-keys/).

## Configuring the Connector

To create the Endor Labs data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Endor Labs data source, enter the following information in the source setup Retrieval section:

- Authentication
- Namespace field
- Use Zscaler namespace traversal checkbox

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Endor Labs Authentication** window appears.
2. In the **Endor Labs Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the Endor Labs parent namespaces (e.g., `acme`) or the specific child namespaces (e.g., `acme.dev.app`) to retrieve findings from. You can enter multiple namespaces, separated by commas.

The Use Zscaler namespace traversal checkbox controls whether Zscaler retrieves data from child namespaces of the specified namespaces during data retrieval.

- When selected, data is retrieved from the specified namespaces and all their child namespaces.
- When unselected, data is only retrieved from the specified namespaces, excluding their child namespaces.

To retrieve data from your tenant namespace and all its child namespaces, specify the tenant namespace in the **Namespace**field and select the **Use Zscaler namespace traversal**checkbox.

This checkbox is available in the following streams:

- Endor Labs - Containers
- Endor Labs - SAST
- Endor Labs - SCA Findings
- Endor Labs - Secrets

[Image: Endor Labs connector tiles]

[Image: Endor Labs Authentication Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-flexera-assets-connector","lastmod":"2026-07-31T07:06Z","nid":"1528196"} -->
## Configuring the Flexera Assets Connector

- Source: https://help.zscaler.com/uvm/configuring-flexera-assets-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Flexera Assets Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Flexera Assets connector, including setting up authentication and data retrieval filters and specifications.

Flexera allows enterprises to gain visibility and control of IT assets, which reduces ongoing software costs and maintains continuous license compliance.

The Flexera Assets connector retrieves detailed hardware, lifecycle, and ownership information for each asset in your organization, including unique identifiers (e.g., asset ID, computer ID, and asset tags) and specific hardware details (e.g., manufacturer, model number, and serial number).

## Prerequisites

The Flexera Assets source authentication configuration requires an API token.

To generate a Flexera token:

1. Log in to one of the following URLs based on the server location of your account:
  - **North American**: Log in to `app.flexera.com`.
  - **Europe (EU)**: Log in to `app.flexera.eu`.
  - **APAC**: Log in to `app.flexera.au`.
2. From the top right of the page, click the **Profile**menu.
3. Select **User Settings**. See image.
4. From the left-side navigation, click **API Credentials**, then click **Create API Refresh Token**. See image. The **New API Refresh Token**window appears, displaying the generated refresh token.
5. Click **Copy** to copy the refresh token. See image. Save the refresh token securely as you cannot copy or view it later.

## Configuring the Connector

To create the Flexera Assets data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Flexera Assets data source, enter the following information in the source setup Retrieval section:

- Authentication
- Zone
- Organization ID

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Flexera Authentication** window appears.
2. In the **Flexera Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select one of the three available zones (i.e., US, EU, or APAC) from the drop-down menu according to the zone in which your user and tenant are provisioned. To learn more, refer to the [Flexera documentation](https://developer.flexera.com/#organization-ids).

Enter the unique ID assigned to your Flexera organization.

To retrieve your organization ID:

1. Log in to the Flexera platform based on your server location.
2. Identify and copy the numeric identifier found at the end of the URL. For example, an organization in the NAM zone follows the format `https://app.flexera.com/orgs/<org ID>/`. So if your URL is [`https://app.flexera.com/orgs/30408/`](https://app.flexera.com/orgs/30408/), then the organization ID is 30408.

See image.

[Image: Selecting User Settings in the Flexera platform]

[Image: Selecting API Credentials > Create API Refresh Token in the Flexera platform]

[Image: Copying the refresh token on the New API Refresh Token page of the Flexera platform]

**[Image: The organization ID found in the URL of the Flexera platform]**

[Image: Flexera Assets connector tile]

[Image: Flexera Assets Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-freshservice-connector","lastmod":"2026-07-31T07:06Z","nid":"1528286"} -->
## Configuring the Freshservice Connector

- Source: https://help.zscaler.com/uvm/configuring-freshservice-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Freshservice Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Freshservice connector, including setting up authentication and data retrieval filters and specifications.

Freshservice is a cloud-based IT service management (ITSM) and enterprise service management (ESM) solution designed to help organizations modernize their IT operations and employee service delivery.

The Freshservice connector retrieves ticket ID, details about the individual or team requesting the service, status of the ticket (i.e., open, closed, in progress), priority, assignee, and any other relevant metadata associated with the service request such as tags, labels, or custom fields.

## Prerequisites

The Freshservice source authentication configuration requires an API key.

To generate a Freshservice API key:

1. Log in to your Freshservice Support Portal as an administrator.
2. In the top-right corner of the page, click the **Profile**menu and select **Profile settings**.
3. Copyyour API key.

See image.

To learn more, refer to the [Freshservice documentation](https://support.freshservice.com/support/solutions/articles/50000000306-where-do-i-find-my-api-key-).

## Configuring the Connector

To create the Freshservice data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Freshservice data source, enter the following information in the source setup Retrieval section:

- Authentication
- Domain Name
- Start Date
- Query Parameters (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Freshservice Authentication** window appears.
2. In the **Freshservice Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter your Freshservice domain name (e.g., `yourdomain.freshservice.com`). You can find this in your Freshservice URL when you are logged in to your Freshservice account. Ensure that you include the `.freshservice.com` suffix, but exclude the `https://` prefix and any trailing slashes.

Enter the timestamp in `YYYY-MM-DDTHH:MM:SSZ` format for when the connector should begin ingesting data. Any data created or updated before this timestamp is not ingested.

The query parameters field allows you to apply granular filters to the data retrieved from Freshservice. Enter a comma-separated list of key-value pairs in the format `param1=val1,param2=val2` (e.g., `type=Service Request,include=stats`) to refine the data ingestion scope based on specific attributes or metadata.

[Image: Freshservice Your API Key]

[Image: Freshservice connector tile]

[Image: Freshservice Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-freshservice-outegration","lastmod":"2026-08-16T00:49Z","nid":"1528076"} -->
## Configuring the Freshservice Outegration

- Source: https://help.zscaler.com/uvm/configuring-freshservice-outegration
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the Freshservice Outegration
- Last modified: 2026-08-16T00:49Z
- Summary: How to configure the Freshservice outegration for disptaching SecOps tickets to the Freshservice work management system.

The Freshservice outegration is used to dispatch tickets from Zscaler Security Operations (SecOps) platform applications (e.g., UVM) to your Freshservice tickets, creating a Freshservice ticket that can then be tracked, assigned, and managed by your remediation teams working with Freshservice.

This article is a step-by-step guide to setting up the Freshservice work management outegration. The process involves setting up authentication, outegration visibility in the platform, outegration mapping, and when relevant, configuring a Freshservice webhook to enable bidirectional synchronization.

## Prerequisites

Retrieve the required authentication parameters and enter them in the corresponding fields during the Connect step of the outegration setup wizard.

- API Key
- Domain

## Configuring the Freshservice Outegration

To configure the Freshservice outegration, complete the following steps:

- Step 1: Authenticate the Freshservice Connection (Connect)
- Step 2: Configure the Outegration Visibility and Behavior (Settings)
- Step 3: Map the Outegration Fields (Mapping)
- (Optional) Step 4: Configure the Freshservice Webhook

To authenticate the Freshservice outegration, you need to provide a Freshservice API key.

To retrieve the Freshservice API key:

1. In the Freshservice portal, click the **Profile**menu on the top right of the page.
2. Click **Profile settings**. See image.
3. Copy the API key that appears under the **Delegate Approvals**section. See image.

[Image: Freshservice Profile Settings]

[Image: API key in the Freshservice portal]

Make sure you have the domain of your Freshservice tenant (e.g., `acme.Freshservice.com`).

The first step in setting up your Freshservice outegration is to authenticate using valid credentials to establish a secure connection with your Freshservice instance. With the required parameters retrieved in the prerequisites, you can begin the Freshservice outegration setup in the SecOps platform.

To create the outegration:

1. In the SecOps platform, go to **Configure** > **Outegrations**. See image.
2. Click **Create**, then select **Freshservice**. The **Connect** step appears. See image.
3. In the **Details** section:
  1. **Display Name**: Enter a name for the outegration.
  2. **Active**: Enable to activate the Freshservice outegration.
  3. **Domain**: Enter the domain of your Freshservice tenant.
  4. **Authentication**: Select an existing authentication, or click **Create New** to set up a new authentication, and enter the required parameters you retrieved earlier into the corresponding fields. To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications).
4. Click **Test** in the bottom-right corner of the screen to verify the connection. Invalid credentials trigger error messages to assist with troubleshooting connectivity issues.
5. After the test passes, click **Next**to advance to the **Settings**step.

[Image: The Outegrations page in the Zscaler SecOps platform]

[Image: Edit Freshservice Outegration page]

In the Settings step of the outegration setup wizard, configure your Freshservice outegration's visibility and behavior within the relevant application in the SecOps platform (e.g., UVM, AEM). In this step, you'll set the SecOps entity that triggers the Freshservice ticket dispatch (e.g., ticket, violation ticket) and when the Create Freshservice Ticket button should appear in the application. The Create Freshservice Ticket button allows end users with access to SecOps tickets to dispatch these tickets to Freshservice directly from the SecOps ticket drawer or from the SecOps tickets page.

To configure the outegration's visibility and behavior:

1. In the **Advanced Settings** section, for **Create Freshservice item from**, select the entity that you want to configure the outegration for. This selection affects the view you'll configure in the <Entity> **View**step (e.g., selecting Ticket displays the Tickets View setting). Other entity types might be visible depending on the apps enabled in your account. See image.
  - **UVM**: Select **Ticket**.
  - **AEM**: Select **Violation Ticket**.
2. In the <Entity> **View** section, select how the SecOps ticket should display the **Create Freshservice Ticket**button. This setting can be modified at any time. See image.
  - **Always**: Select to display the button in all tickets, allowing users to dispatch tickets to a Freshservice ticket without exception.
  - **Never**: Select to hide the button in all tickets. This is useful during the outegrationsetup process to hide the button from users while still keeping the outegration active.
  - **For specific tickets**: Define custom conditions to control when the button is displayed, allowing you to target specific tickets. For example, if your organization uses multiple ticketing systems, you can grant access to the button only to users who work with Freshservice, while excluding those who use other ticketing systems (e.g., Jira).
3. Click **Map**to advance to the **Mapping** step.

The **Create Freshservice Ticket**button appears in two locations:

- In the individual entity drawer (e.g., in the [UVM ticket drawer](https://help.zscaler.com/uvm/viewing-managing-tickets-uvm), in the [AEM violation ticket drawer](https://help.zscaler.com/aem/viewing-violation-tickets-aem)). See image.
- On the entity page in the relevant application (e.g., on the [Tickets page](https://help.zscaler.com/uvm/about-tickets-operational-view-uvm) in UVM, on the [Violation Tickets page](https://help.zscaler.com/uvm/about-violation-tickets-operational-view-aem) in AEM). See image.

[Image: Select an entity from the Create Freshservice Item drop-down menu]

[Image: Tickets View setting]

[Image: Create Freshservice Ticket Button in Ticket]

[Image: Create Freshservice Ticket Button on Tickets Page]

The third step in setting up your Freshservice outegration is configuring the field mapping between your SecOps tickets and Freshservice tickets. This defines how data is exchanged and synchronized between the two systems upon initial dispatch and subsequent updates. The SecOps platform's unique mapping capabilities allow for flexible mapping of any custom field or logic to any field in your Freshservice fields, facilitating highly customized workflows that align with your organization's requirements.

The main objective of the mapping process is to map values to fields. To map values to fields, configure values on the left to populate the fields selected on the right.

See image.

There are three mapping components:

- Tickets initially dispatched to Freshservice: Map SecOps ticket fields (left) to Freshservice fields (right) for the initial dispatch of a ticket to a Freshservice ticket. You can also add an attachment to your Freshservice ticket. Commonly mapped fields include priority, email, status, subject, and description.
- (Optional) Sync from ticket to Freshservice: Map SecOps ticket fields (left) to Freshservice fields (right) for syncing ticket updates to Freshservice, including configuring comments and adding an attachment to your Freshservice ticket. Commonly mapped fields include status and due by.
- (Optional) Sync from Freshservice to ticket: Map Freshservice fields (left) to SecOps ticket fields (right) for syncing Freshservice updates to SecOps tickets. This step also requires setting up a [Freshservice webhook](https://help.zscaler.com/uvm/configuring-freshservice-outegration#step4-freshservice-webhook). Commonly mapped fields include Ticket Status and Ticket SLA.

### Creating a New Mapping

In each of the three mapping components, you'll need to select a field on the right, and then configure the corresponding field value on the left.

To create a new mapping:

1. Select a field (right):
  1. Click**Mapping**.
  2. Select a field on the right. See image. The field's schema details open on the right of the page. The schema lists available Freshservice fields to be used during mapping. This is the list of fields configured for tickets in your Freshservice account. See image. The following details are specified for fields, when available:
    - Input Type
    - Available Options
2. Configure the field value (left):
  1. Click **Add Value** on the left. See image. The editor dialog appears.
  2. In the editor dialog, select one of the following methods to configure the value of the field:
    - Field (Dictionary)
    - Smart Text
    - Script

Repeatthe mapping process for all Freshservice fields that you want to map.

In addition to the mapping of fields on the right to fields on the left, you can perform a set of actions when setting up the Freshservice outegration mapping, each relevant to a specific part of the mapping.

- Set a ticket field as mandatory.
- Add attachments to the ticket dispatch.
- Configure comments synchronization.

### Mapping Ticket Title to Subject

To illustrate the mapping process, consider the mapping of the Freshservice subject field. The final result of the mapping process should show the subject field on the right, and the Ticket Title field on the left.

[Image: Ticket Title to Subject Mapping]

To map the Ticket Title field to the subject field:

1. Select **subject**as the Freshservice field on the right. See image. Selecting the Freshservice subject field opens the field's details in the schema. The schema specifies that the field expects a TEXT input type. Therefore, the field for which a value is being configured must also be of TEXT type. See image.
2. Select the **Ticket Title**field on the left: See image.
  1. Click**Add Value**.
  2. Choose the **Field** method.
  3. From the drop-down menu, select the **Ticket Title**field, which corresponds to the Freshservice **subject**field.

### Previewing the Ticket to Freshservice Mapping

After completing the SecOps ticket to Freshservice dispatch mapping, you can preview the mapping to review the configuration. This helps ensure that SecOps ticket dispatch is behaving as expected and that the Freshservice ticket fields are populated correctly.

To preview the mapping, click **Preview**on the bottom right of the ticket initially dispatched to the Freshservice section. The Mapping Preview window appears. In the left pane, the SecOps tickets in your account are listed and organized by ticket ID. You can select, filter, or search tickets and preview the mapping to their corresponding Freshservice ticket. You can also open the actual SecOps ticket in a new tab for a more in-depth review.

See image.

### Common Mapping Examples

These mapping examples highlight commonly used field configurations in your outegration. While some might be preconfigured by default, Zscaler recommends reviewing and customizing them to ensure they align with your workflow.

- Ticket to Freshservice Description
- Ticket SLA to Freshservice Due By Sync

[Image: Freshservice Outegration Mapping]

[Image: Freshservice Priority Field]

[Image: Freshservice Outegration Schema]

[Image: Click Add Value to configure the left field]

The Input Type specifies the data type of the Freshservice field, such as TEXT (e.g., description), DATE (e.g., due by), or NUMBER (e.g., status). This indicates the format that the selected source field must match in order to successfully map to the Freshservice field.

For Freshservice fields with fixed values, the Available Options column displays the available values. For example, if the Freshservice field status is configured to include the following fixed values—Scheduled, Closed, Resolved, Pending, Open—the corresponding values in the Ticket Status field can be mapped to these values.

[Image: Freshservice Outegration Mapping the Subject Field]

[Image: Freshservice Outegration Subject Field Schema]

In the **Field Editor**dialog, select a field from the drop-down menu to populate the field you chose on the right.

#### Dictionary

The field dictionary allows you to create mappings between specific values from the field on the right and values of the field on the left. To enable the dictionary, make sure you have a field selected on the right, and then select a field from the drop-down menu on the left.

For example, if your Freshservice priority field includes the following fixed values—Urgent, High, Medium, Low—you can use the dictionary to map the corresponding Ticket Severity values to each of the Freshservice priority field values.

See image.

[Image: Freshservice Outegration Ticket Severity to Priority Dictionary Mapping]

Configure the field value using free text, or create a template using a combination of free text and selected fields. This allows you to dynamically insert specific field values (e.g., Ticket SLA, Ticket Assignee, or Asset Name) into customized free text sentences or paragraphs.

To add a Smart Text field, enclose it in double curly brackets (e.g., `{{Ticket Assignee}}`). The field's display name automatically translates to its system name.

This option is commonly used to configure the value of fields like Ticket Title and Ticket Description.

See image.

[Image: Freshservice Outegration Description Field Smart Text Mapping]

For use cases that require more advanced configuration than either of the previous two methods, you can use Python scripts to configure the source field value to be mapped to the target field.

[Image: Mapping the Ticket Title field to the Freshservice subject field]

When dispatching tickets to Freshservice, map the Freshservice Description field with a summary of the Ticket content to provide remediation teams with a brief overview of the ticket.

To configure the Ticket to Freshservice Description mapping:

1. Click**Mapping**.
2. Select**Description** as the field on the right.
3. Click **Add Value** on the left. The editor dialog appears.
4. In the editor dialog, select **Smart Text**.
5. Enter a ticket description, including dynamic fields (e.g., `{{SLA}}`).

Tickets dispatched to Freshservice will now include the configured description.

In the Ticket to Freshservice sync, map the Freshservice Due By field to keep timelines in sync with Ticket SLA changes.

To configure the Ticket SLA to Freshservice Due By mapping:

1. Click**Mapping**.
2. Select**Due Date** as the field on the right.
3. Click**Add Value** on the left. The editor dialog appears.
4. In the editor dialog, select **Field**, and select **Ticket SLA** as the field on the left.

Ticket SLA changes will now automatically update Freshservice ticket due dates.

[Image: Freshservice Outegration Mapping Preview]

You can set a SecOps ticket field as mandatory, by selecting the **Set as Mandatory**checkbox in the Column Menu to the right of the mapping.

See image.

Setting a field as mandatory guarantees that critical fields (e.g., Ticket Assignee) are always populated before a ticket is dispatched to Freshservice, so Freshservice tickets are always actionable for your remediation teams. Attempts to dispatch a ticket without a value in a mandatory field will trigger an error message.

[Image: Freshservice Outegration Set As Mandatory]

You can create a file attachment that summarizes your ticket content and set the trigger to automatically add it to your Freshservice ticket. Adding an attachment to your Freshservice ticket simplifies the review and management of findings dispatched from a ticket.

You can configure file attachments in two of the mapping steps:

- Initial ticket dispatch See image.
- Sync from Ticket to Freshservice See image.

When configured in the Ticket to Freshservice sync section, the attachment is included in the Freshservice ticket alongside existing attachments as a downloadable file in the selected format.

Use the **File Format** drop-down menu on the top left of the attachment page to select from the available formats (**CSV**, **PDF**, **JSONL**, **Excel**).

See image.

To provide your Freshservice remediation teams with a comprehensive view of the findings in the ticket, consider including the following fields in your attachment:

- Finding Severity
- Finding Title
- Finding CVE
- Component Name
- Asset Name
- Asset Operating System
- Finding Optimal Fix
- Finding Description
- Finding Sources

[Image: Freshservice Outegration Initial Dispatch Attachment]

[Image: Freshservice Outegration Sync Attachment]

[Image: Select a file format from the File Format drop-down menu]

In the Ticket to Freshservice sync step, you can configure how ticket comments are synchronized with Freshservice ticket notes. To configure comments, click **Comment Sync**.

See image.

#### Sync Comments

Enable **Sync Comments**to automatically push comments from the ticket's Comments tab to the corresponding Freshservice ticket.

See image.

#### Trigger Comments

Enable **Sync Trigger Comments**and set conditions to trigger a comment when specific fields are modified. Syncing trigger comments is useful when you want to be notified of important changes to tickets without updating the corresponding Freshservice ticket. For example, you can configure a trigger to post a comment in Freshservice when the Ticket Severity changes from Medium to Critical.

See image.

To add a trigger condition:

1. Select the field you want to monitor (e.g., **Severity**).
2. Set the value change that should trigger the comment:
  - **From**: Select the original value.
  - **To**: Select the updated value.

When the specified change occurs in the ticket, a comment is automatically created and added to the Freshservice ticket. The following is an example of a trigger comment:

```
Linked UVM ticket updated:
Ticket severity changed from: MEDIUM to: CRITICAL
<URL to ticket>
```

[Image: Freshservice Outegration Comments Sync Button]

[Image: Freshservice Outegration Sync Comments]

[Image: Freshservice Outegration Sync Trigger Comments]

The Freshservice outegration webhook enables automatic syncing of Freshservice ticket updates (e.g., Status or SLA changes) to their corresponding SecOps tickets, reducing the need for manual changes. This step is required when configuring the Freshservice to SecOps ticket mapping to keep the tickets in the two systems in sync. To learn more, see [Configuring the Freshservice Outgeration Webhook](https://help.zscaler.com/uvm/configuring-freshservice-outegration-webhook).

A Freshservice webhook is only needed to sync updates from Freshservice to the ticket. It is not required for the initial ticket dispatch or for syncing updates from the ticket to Freshservice.

When the outegration setup is complete, you can begin dispatching SecOps tickets using the Create Freshservice Ticket button that appears in the Create Ticket menu within individual tickets, as well as in the Create Issue menu in the Tickets View. To learn more, see [Creating & Managing Third Party Tickets](https://help.zscaler.com/uvm/creating-managing-third-party-tickets).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-freshservice-outegration-webhook","lastmod":"2026-08-16T00:50Z","nid":"1533938"} -->
## Configuring the Freshservice Outegration Webhook

- Source: https://help.zscaler.com/uvm/configuring-freshservice-outegration-webhook
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the Freshservice Outegration Webhook
- Last modified: 2026-08-16T00:50Z
- Summary: How to configure the Freshservice outegration webhook for bidirectional sync when setting up the Freshservice outegration.

The Freshservice outegration workflow webhook enables automatic syncing of Freshservice ticket updates such as Status or SLA changes to their corresponding tickets, reducing the need for manual changes. This step is required when configuring the Freshservice to ticket mapping to keep Freshservice tickets and SecOps tickets in sync. To learn more, see [Configuring the Freshservice Outegration](https://help.zscaler.com/uvm/configuring-freshservice-outegration).

See image.

To set up your Freshservice workflow webhook:

1. In the Freshservice portal, go to **Admin** > **Workflow Automator**. See image.
2. From the **New Automator** drop-down menu, select **Ticket**. See image. The**New Ticket Automator**window appears.
3. In the **New Ticket Automator** window: See image.
  1. **Title**: Enter a title for the workflow webhook.
  2. **Description**: (Optional) Enter a description of the workflow webhook.
4. Click **Create**. The **Event**drawer appears.
5. In the **Event**drawer: See image.
  1. Select **Ticket is**,and then select **updated**.
  2. Click **Add New Event**.
  3. Select **Ticket is**,and then select **deleted**.
  4. Update the title to **Ticket is updated/deleted**.
  5. Click **Done**.
6. Drag the **Action**icon into the diagram to add an action to the workflow. See image. The **Action**drawer appears.
7. In the **Action**drawer:
  1. Enter or select **Trigger Webhook**in the field.
  2. **Request Typ**e: Select **POST**.
  3. **Callback URL**: Paste the following URL into the field: `https://webhook.avalor.io/integration/``<Account ID>``/Freshservice`. Replace `<Account ID>` with your account ID.
  4. **Credentials**: Leave the drop-down menu empty.
  5. **Encoding**: Make sure **JSON**and **Simple**are selected.
  6. **Content**: Select the following fields:
    - Ticket ID
    - Ticket ID (numeric)
    - Subject
    - Description
    - Group
    - Priority
    - Urgency
    - Impact
    - Status
  7. Click **Done**. See image.
8. Click **Activate** on the top right of the **Workflow Automator**page. See image.

After your webhook is set up, configured triggers for field updates in your Freshservice outegration mapping automatically sync changes made to Freshservice tickets with their corresponding SecOps tickets.

[Image: Freshservice Webhook Sync]

[Image: Freshservice Webhook Workflow Automator]

[Image: Freshservice Webhook New Automator Menu]

[Image: Freshservice Webhook New Ticket Automator]

[Image: Freshservice Webhook Event Drawer]

[Image: Freshservice Workflow Automator Drag an Action]

[Image: Freshservice Webhook Action Drawer]

[Image: Freshservice Webhook Activate]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-github-advanced-security-connector","lastmod":"2026-04-11T07:06Z","nid":"1532016"} -->
## Configuring the GitHub Advanced Security Connector

- Source: https://help.zscaler.com/uvm/configuring-github-advanced-security-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the GitHub Advanced Security Connector
- Last modified: 2026-04-11T07:06Z
- Summary: How to configure the GitHub Advanced Security connector, including setting up authentication and data retrieval filters and specifications.

The GitHub Advanced Security connector is in limited availability. To learn more, contact Zscaler Support.

GitHub Advanced Security (GHAS) is a set of security features that helps organizations detect and remediate risks in their repositories, including code vulnerabilities and exposed secrets. It provides organization-level visibility into security findings generated by code scanning and secret scanning across GitHub repositories.

There are two available GitHub Advanced Security streams. Select those that are in scope based on your GitHub Advanced Security licenses and use cases:

- GitHub Advanced Security - Code Scanning: Retrieves code scanning data from GitHub repositories, including security vulnerabilities, misconfigurations, and potential exploits in codebases.
- GitHub Advanced Security - Secret Scanning: Retrieves secret scanning data from GitHub repositories, including exposed sensitive information such as API keys, credentials, and tokens in both public and private repositories.

For the GitHub Repositories connector, see [Configuring the GitHub Repositories Connector](https://help.zscaler.com/uvm/configuring-github-repositories-connector).

## Prerequisites

To configure the GitHub Advanced Security connector, you need the source authentication configuration. The following parameters are required:

- Token
- Organization

The authenticated user must be an owner or security manager for the GitHub organization.

To generate a Personal Access Token (PAT) for GitHub Advanced Security APIs, select one of the following options:

### Fine-Grained PAT (Recommended)

To generate a fine-grained PAT:

1. In the GitHub platform, go to **Settings**> **Developer settings**> **Personal access tokens**.
2. In the left-side navigation, click **Fine-grained tokens**.
3. Click **Generate new token**. The**New fine-grained personal access token**page appears.
4. On the **New fine-grained personal access token**page: See image. The **Fine-grained personal access tokens**page appears displaying the access token.
  1. **Token name**: Enter a name for the token.
  2. **Description**: (Optional) Enter a description for the token.
  3. **Resource owner**: Select the organization.
  4. **Expiration**: Set an expiration date for the token. Make sure to refresh the key when it expires.
  5. **Repository access**: Select **All repositories**or specify the required repositories.
  6. **Permissions**: Click **Add permissions**, and select the following permissions: The minimum required access level setting is **Read-only**, which appears automatically for the selected permission. Click the drop-down menu to change the access level setting.
    - **Code scanning alerts**
    - **Secret scanning alerts**
    - **Security events**
  7. Click **Generate token**.
5. Copy the generated token to be used in the connector setup process. Save the token securely. It is not available again after you leave this page.

### Tokens (Classic)

To generate a token:

1. In the GitHub platform, go to **Settings** > **Developer settings** > **Personal access tokens**.
2. In the left-side navigation, click **Tokens (classic)**.
3. Click **Generate new token**.
4. Select **Generate new token (classic)**. The **New personal access token (classic)**page appears.
5. On the **New personal access token (classic)** page: See image. The **Personal access tokens (classic)**page appears displaying the access token.
  1. **Note**: (Optional) Describe the purpose of the access token.
  2. **Expiration**: Set an expiration date for the token. Make sure to refresh the key when it expires.
  3. **Select scopes**: Select the following permissions:
    1. Under **repo**, select the **security_events**checkbox.
    2. Under **admin:org**, select the **read:org**checkbox.
  4. Click **Generate token**.
6. Copy the generated token to be used in the connector setup process. Save the token securely. It is not available again after you leave this page.

An organization is a shared account that allows multiple users to collaborate across repositories and projects.

Enter the GitHub organization name (login) that owns the repositories you want to retrieve GitHub Advanced Security alerts from.

You can find your organization name in one of the following locations:

- In your organization URL (e.g., `https://github.com/``<Your Organization Name>`). For example, if your organization URL is `https://github.com/acme-security`, enter `acme-security`.
- In your GitHub account, click the **Profile**menu and then click **Organizations**.

The connector retrieves alerts at the organization level for the organization you specify. To access organization-level code scanning and secret scanning data, the Personal Access Token (PAT) must belong to a user who is an Organization Owner or assigned as a Security Manager in that organization.

To learn more, refer to the [GitHub documentation](https://docs.github.com/en/organizations/collaborating-with-groups-in-organizations/about-organizations).

[Image: GitHub Advanced Security New fine-grained personal access token page]

[Image: GitHub Advanced Security New personal access token (classic) page]

## Configuring the Connector

To create the GitHub Advanced Security data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the GitHub Advanced Security data source, enter the following information in the source setup Retrieval section:

- Authentication
- State
- Severity
- Resolution
- Secret Types

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **GitHub Advanced Security Authentication** window appears.
2. In the **GitHub Advanced Security Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Use the State filter to limit the scope of the ingested data to specific alert states. If no filter is applied, all data is retrieved.

This filter is available in:

- GitHub Advanced Security - Code Scanning: Available states to filter by include Open, Closed, Dismissed, or Fixed.
- GitHub Advanced Security - Secret Scanning: Available states to filter by include Open or Resolved.

Use the Severity filter to limit the scope of the ingested code scanning alerts by severity. From the drop-down menu, you can select Critical, High, Medium, Low, Warning, Note, or Error. If no filter is applied, alerts of all severities are retrieved.

This filter is available in the GitHub Advanced Security - Code Scanning stream.

Use the Resolution filter to filter secret scanning alerts by resolution. From the drop-down menu, you can select False Positive, Won’t Fix, Revoked, Pattern Edited, Pattern Deleted, or Used In Tests. If no filter is applied, all data is retrieved.

This filter is available in the GitHub Advanced Security - Secret Scanning stream.

Use the Secret Types filter to configure the types of secrets to retrieve in the scope of the ingested data. By default, the connector retrieves alerts for default secret types. You can select one of the following options based on your organization's requirements:

- Default: Includes only the default secret types.
- Generic: Includes only generic secret types (e.g., ec_private_key, mysql_connection_string).
- Both default and generic: Includes both default and generic secret types.

If no option is selected, the connector only retrieves alerts for Default secret types.

To learn more about supported secret types, refer to the [GitHub documentation](https://docs.github.com/en/code-security/reference/secret-security/supported-secret-scanning-patterns#supported-secrets).

This filter is available in the GitHub Advanced Security - Secret Scanning stream.

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

[Image: GitHub Advanced Security connector tiles]

[Image: Github Advanced Security Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-github-repositories-connector","lastmod":"2026-08-29T07:06Z","nid":"1538516"} -->
## Configuring the GitHub Repositories Connector

- Source: https://help.zscaler.com/uvm/configuring-github-repositories-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the GitHub Repositories Connector
- Last modified: 2026-08-29T07:06Z
- Summary: How to configure the GitHub Repositories connector, including setting up authentication and data retrieval filters and specifications.

GitHub Repositories is GitHub's core workspace for hosting and managing code projects. It helps teams collaborate by tracking changes, reviewing work through pull requests, managing issues, and controlling access and workflows.

The GitHub Repositories connector retrieves repository metadata (e.g., name, owner and organization, visibility, URL, and timestamps) for the specified repositories and organizations. It can also enrich each repository record with custom properties when enabled.

The GitHub Repositories connector is in limited availability. To learn more, contact Zscaler Support.

For the GitHub Advanced Security connector, see [Configuring the GitHub Advanced Security Connector](https://help.zscaler.com/uvm/configuring-github-advanced-security-connector).

## Prerequisites

To configure the GitHub Repositories connector, you need the source authentication configuration. Choose one of the following authentication types and retrieve the required parameters for it:

- Personal Token Authentication
- GitHub App Token

Use this option to authenticate the connector using a fine-grained personal access token (PAT) associated with a GitHub user. This is a quick way to get started, but access is tied to the user account and the token must be rotated when it expires.

To generate a fine-grained Personal Access Token (PAT):

1. In the GitHub platform, go to **Settings**> **Developer settings**> **Personal access tokens**.
2. In the left-side navigation, click **Fine-grained tokens**.
3. Click **Generate new token**. The**New fine-grained personal access token**page appears.
4. On the **New fine-grained personal access token**page: See image. The **Fine-grained personal access tokens**page appears displaying the access token.
  1. **Token name**: Enter a name for the token.
  2. **Description**: (Optional) Enter a description for the token.
  3. **Resource owner**: Select the organization.
  4. **Expiration**: Set an expiration date for the token. Make sure to refresh the key when it expires.
  5. **Repository access**: Select **All repositories**or specify the required repositories.
  6. **Permissions**: Click **Add permissions**, and select **Metadata**. The minimum required access level setting is **Read-only**, which appears automatically for the selected permission. Click the drop-down menu to change the access level setting.
  7. Click **Generate token**.
5. Copy the generated token to be used in the connector setup process. Save the token securely. It is not available again after you leave this page.

To learn more, refer to the [GitHub documentation](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-fine-grained-personal-access-token).

[Image: GitHub Advanced Security New fine-grained personal access token page]

Use this option to authenticate the connector using a GitHub App installation access token (recommended for organization-managed access and better control than user PATs).

The App Token authentication method requires creating a GitHub app to retrieve the App ID, Installation ID, private key (PEM), and organization you want to ingest data for.

### GitHub App ID, Installation ID, and PEM

To configure GitHub App authentication:

1. In the GitHub platform, click the **Profile**menu.
2. Go to the app settings in one of the following ways:
  - For an app owned by a personal account, click **Settings**.
  - For an app owned by an organization:
    1. Click **Organizations**.
    2. To the right of the organization, click **Settings**.
3. In the left-side navigation, click **Developer Settings**.
4. In the left-side navigation, click **GitHub Apps**.
5. Click **New GitHub App**. The **Create GitHub App**page appears.
6. On the **Create GitHub App**page: See image.
  1. **GitHub App name**: Enter a name for the app.
  2. **Write**: (Optional) Enter a short description of the app.
  3. **Homepage URL**: Enter any valid URL (required by GitHub; not used by the connector).
  4. **Callback URL**: (Optional) Leave blank. This is only needed for OAuth-based user authorization, which is not supported.
  5. **Expire user authorization tokens**: (Optional) Deselect this option. This is only required for OAuth-based user authorization, which is not supported.
  6. **Request user authorization (OAuth) during installation**: (Optional) Leave as default. This is only required for OAuth-based user authorization, which is not supported.
  7. **Enable Device Flow**: (Optional) Leave as default. This is only required for OAuth-based user authorization, which is not supported.
  8. **Setup URL**: (Optional) Leave as default. This is only required for OAuth-based user authorization, which is not supported.
  9. **Redirect on update**: (Optional) Leave as default. This is only required for OAuth-based user authorization, which is not supported.
  10. **Webhook**: (Optional) Deselect the **Active**checkbox to disable the webhook.
  11. **Permissions**: Select at least **Repository permissions**> **Metadata** > **Read-only**. If you scope the app to specific repositories, ensure access includes the required repositories.
  12. **Where can this GitHub App be installed?**: Select **Only on this account**.
  13. Click **Create GitHub App**.
7. On the app's settings (**General**) page, collect the required parameters:
  1. Copy the **App ID**.
  2. Under **Private keys**, click**Generate a private key**, then download and save the `.pem` file. Save the PEM file securely as you cannot copy or view it later.
8. Install the app and collect the Installation ID:
  1. In the left-side navigation, click **Install App**.
  2. Click **Install**next to the target organization and complete the installation (choose **All repositories**or **Only select repositories**, as needed).
  3. Under **Install App**, click **Configure**for the installation you created, then copy the number from the URL: `.../settings/installations/``<Installation ID>`.

To learn more, refer to the [GitHub documentation](https://docs.github.com/en/apps/creating-github-apps/registering-a-github-app/registering-a-github-app).

### Organization

The GitHub Organization value can be found in the following locations:

- In the GitHub platform, click the **Profile**menu and select **Organizations**, then open the target organization.
- In your browser URL address, copy the organization login/slug from the URL: `https://github.com/``<Organization>`.

Use the App ID, Installation ID, private key (PEM), and organization when creating the GitHub authentication in the connector.

[Image: Create GitHub App page]

## Configuring the Connector

To create the GitHub Repositories data source in the Security Operations Platform (SecOps Platform):

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the GitHub Repositories data source, enter the following information in the source setup Retrieval section:

- Authentication
- Start Date
- Repositories
- Branch (Optional)
- Fetch Custom Properties (Optional)

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **GitHub Authentication** window appears.
2. In the **GitHub Authentication** window, select an authentication type and enter the information from the Prerequisites section.
  - Personal Token Authentication See image.
  - GitHub App Token See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the date and time that the connector starts syncing repository data. Use the format `YYYY-MM-DDT:hh:mm:ssZ` (e.g., `2025-08-15T00:00:00Z`). Only repository records with `updated_at` on or after this time are synced.

Enter a space-delimited list of repository selectors to sync (e.g., `org/repo1 org/repo2`). To sync all repositories in an organization, use `org/*`. You can also combine specific repositories and organizations in the same list (e.g., `org1/repo1 org1/repo2 org2/*`).

Enter the branch name that the connector should use for the specified repositories. You can find the branch name in GitHub by opening the repository and checking the branch selector above the file list (it also shows the default branch).

Select the Fetch Custom Properties checkbox to include repository custom properties configured in your GitHub organization in the scope of the ingested data (in addition to standard repository metadata). Use this option to add business context to repositories, such as data classification (Public/Internal/Confidential) or service owner (team name).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

[Image: GitHub Advanced Security connector tiles]

[Image: GitHub Personal Token Authentication window]

[Image: GitHub App Token Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-google-cloud-platform-connector","lastmod":"2026-07-03T07:06Z","nid":"1541016"} -->
## Configuring the Google Cloud Platform Connector

- Source: https://help.zscaler.com/uvm/configuring-google-cloud-platform-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Google Cloud Platform Connector
- Last modified: 2026-07-03T07:06Z
- Summary: How to configure the Google Cloud Platform connector, including setting up authentication and data retrieval filters and specifications.

Google Cloud Platform (GCP) is Google's cloud services platform for running and managing applications and infrastructure. GCP provides services that maintain a centralized inventory of cloud resource metadata (Cloud Asset Inventory), aggregate and prioritize security findings such as misconfigurations (Security Command Center), and analyze container images and artifacts for metadata and vulnerabilities (Container Analysis/Artifact Analysis).

There are three GCP streams. Select those that are based on your GCP feature plan and use cases:

- Google Cloud Platform Assets: Retrieves an inventory of GCP assets and cloud resource metadata, including projects, compute instances, storage buckets, Kubernetes clusters, resource type, resource name or ID, project, and location. Supported assets include:
  - storage.googleapis.com/Bucket
  - artifactregistry.googleapis.com/Repository
  - artifactregistry.googleapis.com/DockerImage
  - compute.googleapis.com/Instance
  - container.googleapis.com/Cluster
- Google Cloud Platform Misconfigurations: Retrieves findings related to misconfigurations and compliance risks in your GCP environment, including severity, affected resource, finding state, and recommendation when available.
- Google Cloud Platform Vulnerabilities: Retrieves vulnerability occurrences for resources, including vulnerabilities identified in container images and related artifacts, with vulnerability ID such as a CVE, severity, affected package, and image identifier.

The GCP connector is in limited availability. To learn more, contact Zscaler Support.

For the Google Sheets stream, see [Configuring the Google Sheets Connector](https://help.zscaler.com/uvm/google-sheets).

## Prerequisites

To configure the GCP connector, you need the source authentication configuration. The following parameters are required:

- Enable the required APIs.
- Retrieve the service account JSON key.

## Configuring the Connector

To create the GCP data source in the SecOps platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the GCP data source, enter the following information in the source setup Retrieval section:

- Authentication
- Pull data only for service account project (Optional)
- Filter project with sys prefix (Optional)
- Admin Email to Impersonate

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To allow the Zscaler Security Operations (SecOps) platform to access your Google Cloud assets via API, ensure that the Cloud Asset API is enabled.

To enable the API in your project:

1. Log in to the Google Cloud console.
2. From the left-side navigation, go to **APIs & Services**> **Library**. See image.
3. In the **Search for APIs and Services**search box, enter the relevant API, then select it: See image.
  - For Google Cloud Platform Assets: Enable **Cloud Asset API**.
  - For Google Cloud Platform Misconfigurations: Enable **Security Command Center API**.
  - For Google Cloud Platform Vulnerabilities: Enable **Container Analysis API**.

To create the service account and generate the JSON key:

1. Log in to the Google Cloud console.
2. From the project drop-down menu, select your project.
3. From the left-side navigation, select **IAM & Admin**> **Service Accounts**. See image.
4. On the **Service accounts**page, click **Create service account**. See image.
5. On the **Create service account**page, configure the following:
  1. On the **Create service account**tab: See image.
    1. **Service account name**: Enter a name for the service account.
    2. **Service account ID**: An account ID is automatically generated based on the service account name. You can keep the generated ID or edit it.
    3. **Service account description**: Enter a description for the service account.
    4. Click **Create and continue**.
  2. On the**Permissions (optional)**tab, do one of the following: See image.
    - Assign the service account the **Viewer**role from the **Basic**category.
    - Assign the specific roles for the streams you plan to configure:
      - For the Google Cloud Platform Assets stream: Assign the **Cloud Asset Viewer**role, or a custom role that includes **cloudasset.assets.listResource**.
      - For the Google Cloud Platform Misconfigurations stream: Assign a role that includes **securitycenter.findings.list**(e.g., **Security Center Admin Viewer**or **Security Center Findings Viewer**).
      - For the Google Cloud Platform Vulnerabilities stream: Assign the**Container Analysis Occurrences Viewer**role, or a custom role that includes **containeranalysis.occurrences.list**. For the most comprehensive setup, Zscaler recommends assigning either the **Viewer**role, or all three stream-specific roles. This ensures that the connector's internal health checks and connection validation succeed, even if you only plan to enable a subset of the available data streams.
  3. On the **Principals with access (optional)**tab, grant access to users or groups, then click **Done**.
6. On the **Service accounts**page, select the service account you created.
7. On the **Keys**tab, from the **Add Key**drop-down menu, select **Create new key**.
8. Select **JSON**and click **Create**. A JSON file is downloaded. See image.

To learn more, refer to the [Google Cloud documentation](https://docs.cloud.google.com/iam/docs/service-accounts-create).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Google Cloud Platform Authentication** window appears.
2. In the **Google Cloud Platform Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select this checkbox to restrict the connector's scope to the specific Google Cloud project where the service account resides. Use this if your service account has inherited permissions from an organization or folder level, but you want to limit data ingestion and permission validation to this single project only. Leaving this unselected allows the connector to discover and ingest data from all projects across your GCP hierarchy that the service account has access to.

Select this checkbox to exclude Google-managed system projects, which are automatically created for services like cloud functions and typically have IDs starting with `sys-`. This filter ensures that your security findings and asset inventory focus exclusively on your active user-managed infrastructure, which reduces noise and optimizes ingestion performance by ignoring background system resources.

Leave this field empty, as the GCP connector does not require an admin email to impersonate for authentication.

[Image: APIs & Services Library]

[Image: Search for APIs & Services search box]

[Image: GCP IAM & Admin Service Accounts]

[Image: GCP Create service account]

[Image: Create service account tab]

[Image: Permissions (optional) tab]

[Image: Create private key]

[Image: Google Cloud Platform connector tiles]

[Image: Google Cloud Platform Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-google-cloud-storage-outegration","lastmod":"2026-08-16T00:37Z","nid":"1541146"} -->
## Configuring Google Cloud Storage Outegration

- Source: https://help.zscaler.com/uvm/configuring-google-cloud-storage-outegration
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring Google Cloud Storage Outegration
- Last modified: 2026-08-16T00:37Z
- Summary: How to configure Google Cloud Storage outegration in the Zscaler SecOps platform.

The Google Cloud Platform (GCP) Storage outegration is used as a report delivery method to dispatch files from the Zscaler Security Operations (SecOps) platform applications (e.g., UVM) to your Google Cloud Storage bucket.

This article is a step-by-step guide to configuring Google Cloud Storage as a storage outegration.

## Prerequisites

Before you configure the outegration, ensure the following requirements are met:

- Service Account
- Service Account Permissions
- JSON Keys
- Google Cloud Storage Bucket Name
- Google Cloud Storage Bucket's Project ID

To retrieve the Google Cloud Storage bucket's Project ID, use the Google Cloud console, the command line, or the Storage API. To learn more, refer to the [Google Cloud documentation](https://docs.cloud.google.com/storage/docs/bucket-metadata).

To retrieve the Google Cloud Storage's bucket name from the Google Cloud console, go to the Cloud Storage Buckets page and view the list of available buckets for your project. To learn more, refer to the [Google Cloud documentation](https://docs.cloud.google.com/storage/docs/listing-buckets).

Create a new service account or select an existing one dedicated to report delivery in the Google Cloud console. To learn more, refer to the [Google Cloud documentation](https://docs.cloud.google.com/iam/docs/service-accounts-create).

To grant the service account the permission to write objects to a Google Cloud Storage bucket, you need to assign it the Storage Object Creator role and Storage Legacy Bucket Reader role for that specific bucket. To learn more, refer to the [Google Cloud documentation](https://docs.cloud.google.com/storage/docs/access-control/iam-roles).

If the service account requires write access across multiple buckets within a single project, assign the role at the project level.

To generate a JSON key file, go to the Service Accounts page in the Google Cloud console. To learn more, refer to the [Google Cloud documentation](https://docs.cloud.google.com/iam/docs/keys-create-delete#iam-service-account-keys-create-console).

## Creating a Google Cloud Storage Outegration

To configure the Google Cloud Storage outegration:

1. In the SecOps platform, go to **Configure**> **Outegrations**. See image.
2. Click **Create**and select **GCP Storage**.
3. In the **Details**section: See image.
  1. **Display Name**: Enter a name for your outegration.
  2. **Bucket**: Enter the name of the Google Cloud Storage bucket where report files are to be delivered.
  3. **Project ID**: Enter the unique identifier of the GCP project associated with the Google Cloud Storage bucket.
  4. **Path**: (Optional) Enter the folder path to organize and separate the reports within the Google Cloud Storage bucket.
  5. **Authentication**: Select an existing authentication ID, or click **Create New** to set up a new authentication, and enter parameters from the Prerequisites section. See image.
4. Click **Test**in the bottom-right corner of the page to verify the connection. If the credentials are invalid, an error message is displayed along with the remediation steps to resolve the issue.
5. After the connection is verified, click **Finish**.

When the outegration setup is complete, you can begin using the GCP Storage outegration as a delivery method for your reports.

[Image: Create an outegration]

[Image: Create a GCP storage outegration]

[Image: Create a Google Cloud platform authentication]

## Using Google Cloud Storage in Report Export

After you create a Google Cloud Storage outegration, the outegration name appears in each report's Delivery Method drop-down menu. To learn more, see [Scheduling Reports to Export](https://help.zscaler.com/uvm/scheduling-reports-export).

See image.

[Image: Select GCP Storage outegration]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-google-sheets-connector","lastmod":"2026-07-31T07:06Z","nid":"1527656"} -->
## Configuring the Google Sheets Connector

- Source: https://help.zscaler.com/uvm/configuring-google-sheets-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Google Sheets Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Google Sheets connector, including setting up authentication and data retrieval filters and specifications.

The Google Sheets connector allows you to upload files from your Google Sheets to the Zscaler Security Operations (SecOps) platform.

The Google Sheets connector retrieves data from a specified Google Sheets spreadsheet (Spreadsheet ID) and can optionally filter by specific sheets within the spreadsheet.

## Prerequisites

To configure the Google Sheets connector, you need the source authentication configuration. The following parameters are required.

- Enable the API.
- Generate the required parameters.

## Configuring the Connector

To create the Google Sheets data source in the SecOps platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Google Sheets data source, enter the following information in the source setup Retrieval section:

- Authentication
- Spreadsheet ID
- Sheets (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To allow the SecOps platform to access your Google Sheets via API, ensure that your Google Sheets API is enabled.

To enable the API for your project:

1. Log in to the Google Cloud console.
2. From the left-side navigation, click **APIs & Services** > **Library**. See image.
3. Search for and select **Google Sheets API**. See image.
4. Click **Enable**.

To learn more, refer to the [Google Cloud documentation](https://support.google.com/googleapi/answer/6158841?hl=en).

The following parameters are required for authenticating the Google Sheets connector:

- Client ID and Client Secret
- Refresh Token

To generate a client ID and client secret:

1. Log in to the Google Cloud console.
2. From the left-side navigation, click **APIs & Services** > **Credentials**. See image.
3. From the **Create credentials**drop-down menu, select **OAuth client ID**. See image. The **Create OAuth client ID**page appears.
4. On the **Create OAuth client ID** page: See image. The **OAuth client created**window appears, displaying the generated client ID and client secret.
  1. **Application type**: Select **Web application**.
  2. **Name**: Enter a name for the authentication credentials.
  3. In the **Authorized Redirect URIs** section, click **Add URI**and enter `https://developers.google.com/oauthplayground`.
  4. Click **Create**.
5. Copy the client ID and client secret. See image.

To retrieve the refresh token:

1. Go to the [OAuth 2.0 Playground](https://developers.google.com/oauthplayground/).
2. Click the **OAuth 2.0 Configuration**icon. The **OAuth 2.0 configuration**window appears. See image.
3. In the **OAuth 2.0 configuration**window: See image.
  1. Select the **Use your own OAuth credentials** checkbox.
  2. Enter the obtained **Client ID** and **Client Secret** values.
  3. Click **Close**.
4. From the left-side navigation, in the **Step 1** section, select **Google Sheets API v4**, select all the scopes, and click **Authorize APIs**. See image.
5. When prompted, select your Gmail account and click **Allow**. See image.
6. In the **Step 2** section, click **Exchange authorization code for tokens**, enter the authorization code, and click **Exchange authorization code for tokens** to generate and display the access token and refresh token. See image.
7. Copy and save the refresh token. See image.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Google Sheets Authentication** window appears.
2. In the **Google Sheets Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the spreadsheet ID located in the spreadsheet URL. The spreadsheet ID can be found in the URL of the Google Sheets spreadsheet you want to ingest, between `d/` and `/edit`.

For example, if the URL is `https://docs.google.com/spreadsheets/d/8J31VsFnlj_9hSfWHAJKCBN5aF3J0lVh9opFewIUaKJs/edit`, the spreadsheet ID is `8J31VsFnlj_9hSfWHAJKCBN5aF3J0lVh9opFewIUaKJs`.

See image.

You can filter the specific sheets in the spreadsheet that you want to ingest. To retrieve data from specific sheets, enter a comma-separated list of sheet names in the field. If the field is left empty, data is retrieved from all sheets in the spreadsheet.

[Image: The Google Sheets tile]

[Image: Google Sheets Authentication window]

[Image: Google Sheets APIs & Services Library]

[Image: Google Sheets API]

[Image: Google Sheets APIs & Services Credentials]

[Image: Create Credentials OAuth client ID]

[Image: Google Sheets Create OAuth ID page]

[Image: Google Sheets Client created dialog]

[Image: Google Sheets OAuth 2.0 configuration window]

[Image: Google Sheets Use your own OAuth credentials]

[Image: Google Sheets Step 1 Select & authorize APIs]

[Image: Google Sheets Allow]

[Image: Google Sheets Step 2 Exchange authorization code for tokens]

[Image: Google Sheets Refresh Token]

[Image: Google Sheets ID in URL]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-grouping-rules","lastmod":"2025-09-07T02:39Z","nid":"1527901"} -->
## Configuring Grouping Rules

- Source: https://help.zscaler.com/uvm/configuring-grouping-rules
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Configuring Grouping Rules
- Last modified: 2025-09-07T02:39Z
- Summary: How to configure grouping rules and rulesets in UVM.

Ticket grouping rules are customizable rules that aggregate findings with similar attributes into tickets to facilitate a more focused and productive work process. On initial account setup, grouping rules include a default active ruleset that implements grouping best practices. You can adjust the default grouping and align it to your organization's business logic.

You can create rulesets in the following ways:

- Create a new ruleset. After adding rules to your ruleset, activate and run the ruleset to reaggregate findings into tickets using your new ruleset. Activating your new ruleset deactivates the currently active ruleset. To learn more, see [Managing Grouping Rules](https://help.zscaler.com/uvm/managing-grouping-rules).
- Clone and edit the default ruleset to refine it according to your organization's needs. When complete, run the edited ruleset to reaggregate findings into tickets based on the newly configured grouping logic. To learn more, see [Managing Grouping Rules](https://help.zscaler.com/uvm/managing-grouping-rules).

## Configuring a Grouping Ruleset

A grouping ruleset is a collection of individual rules designed to group findings into tickets based on specific conditions.

You can have multiple rulesets in the account, but only one ruleset can be active.

To create a grouping ruleset:

1. In the **Vulnerabilities** app, go to **Settings** > **Grouping Rules**.
2. Click **New Rule Set**. The **Create Rule Set** page appears.
3. Enter a name for the ruleset.
4. Add at least one grouping rule in addition to the default fallback rule. Every ruleset includes a default fallback rule, which provides a default aggregation method for findings that don't match any conditions. The fallback rule can be edited, but can't be removed or deleted.

To aggregate findings into tickets based on your new logic, activate and run the new ruleset. If you only activate but don't run your new ruleset, it will run on the next data ingestion in your account. To learn more, see [Managing Grouping Rules](https://help.zscaler.com/uvm/managing-grouping-rules).

## Configuring Grouping Rules

Within a grouping ruleset, you can create individual rules that define how findings should be aggregated into tickets. For example, you can create a rule to group findings reported on the same vulnerable component (e.g., Adobe Acrobat) to facilitate fixing all findings in a single ticket. Every new ruleset must include at least one rule in addition to the default fallback rule.

A grouping rule consists of three key components:

- If condition: Defines a filter that determines which findings the rule applies to.
- Then action: Specifies that findings should be aggregated into a single ticket if they have matching values in all selected fields.
- Define Ticket Title By: Defines the title format for tickets that are created based on the rule.

To create a grouping rule:

1. In the relevant ruleset, click **New Rule**. The **Create Rule** drawer appears. See image.
2. In the **Create Rule**drawer:
  1. **Name**: Enter a name for the rule.
  2. **If**: Define the rule condition that determines which findings the rule should aggregate into a single ticket.
    1. Select a field on which the condition should be based.
    2. Select an operator (e.g., **Is Not Empty**, **Is Empty**, **Equals**). The available operators vary depending on the field type, indicated to the left of the field name.
    3. Enter the value to which the rule should apply.
    4. (Optional) Use **AND**/**OR**logic to define compound rules.
      - **AND**aggregates findings only if they meet all conditions in the rule.
      - **OR**aggregates findings if they meet any of the conditions in the rule.
  3. **Then**: Select at least one field from the **Group By**drop-down menu according to which the findings that meet your conditions should be aggregated. All findings with the same value are merged into a single ticket. For example, if you group findings by the Asset Type field and there are two findings on Server assets and three findings on Workstation assets, then two tickets are created, one for each asset type. Adding the Asset OS field can lead to the creation of additional tickets, as a ticket is created for each unique combination of Asset Type and Asset OS values.
  4. **Define Ticket Title By**: Select a method to set the ticket title.
    - **Smart Text**: Use a combination of free text and field names as tokens to set the ticket title. For example, you can enter the name of the team based on the business application associated with the ticket. To add fields as tokens, enter the field name within double curly brackets. The field's display name automatically translates to the field's system name (e.g., Application Name → `application.name`).
    - **Expression**: Set a ticket title using an expression to apply custom value transformations and standardize formats. Supported functions, operators, and references, along with examples, are displayed when clicking the Expression text box.
3. Click **Preview**to validate your rule's logic. The preview displays findings that match the rule and their relevant fields according to the fields specified in the rule. This can indicate whether the rule's filters apply to the findings you intend to group into tickets. See image. If **No findings match filter condition** is displayed in the preview, you might need to adjust the rule's configured filters. Alternatively, a previous rule might be applicable to the relevant findings, as grouping is dependent on rule order.
4. Click **Save** to add the rule to the ruleset.

Repeat the process to add as many grouping rules as necessary to the ruleset.

After adding all the necessary rules to the ruleset, save the ruleset to complete the process:

- Click **Save**to save the ruleset. This saves the rules to the ruleset but doesn't reaggregate into tickets based on the ruleset's rules.
  - If the ruleset is active, the new ruleset will take effect either when you click Run or the next time data is processed in the account (e.g., when a data source runs).
  - If the ruleset is inactive, no changes will apply to the grouping of findings in the account until the ruleset is activated and runs.
- In the **Save**drop-down menu, click **Save & Activate**to save the ruleset and immediately apply the rules to the data in your account.

### Order of Rules in a Grouping Ruleset

The rules within a grouping ruleset are executed sequentially by their order of appearance. A finding is aggregated into a ticket according to the first rule that applies to it. If no rule applies to the finding, it's aggregated by the default fallback rule. You can adjust the order of rules by dragging the rules to your desired order. After rearranging the rule order, click Save to apply your changes.

See image.

[Image: create grouping rule drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-halo-assets-connector","lastmod":"2026-07-31T07:06Z","nid":"1528031"} -->
## Configuring the Halo Assets Connector

- Source: https://help.zscaler.com/uvm/configuring-halo-assets-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Halo Assets Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Halo Assets connector, including setting up authentication and data retrieval filters and specifications.

HaloITSM helps organizations track and manage IT assets throughout their lifecycle, integrating with other IT service management processes.

The Halo Assets connector retrieves asset data, including detailed asset records, optional site and asset group information, and Change Advisory Board (CAB) owner names.

The Halo Assets connector is in limited availability. To learn more, contact Zscaler Support.

## Prerequisites

To configure the Halo Assets connector, you need the source authentication configuration. The following parameters are required:

- Roles and Permissions
- Client ID and Client Secret
- Tenant

To obtain the required parameters, you first need to create a Halo application and assign it the appropriate permissions, as outlined in the client ID and client secret section. This process requires HaloITSM administratorprivileges.

When setting up the application, you must associate it with an agent or user. The selected user, whether it is yours or a dedicated user created for this purpose, must have access to all asset fields within their permissions.

You can verify this in the agent's profile. Go to Configuration > Teams & Agents > Agents > Permissions.

See image.

Ensure that you sign in to the HaloITSM console as a HaloITSM administrator.

To generate the client ID and client secret:

1. From the left-side navigation, go to **Configuration** > **Integrations**.
2. Click **HaloITSM API**. See image.
3. In the **Applications** section, click **View Applications**. See image.
4. From the top right, click **New**. See image.
5. On the **Details** tab: See image.
  1. **Application Name**: Enter a name for the application.
  2. **Active**: Ensure that the checkbox is selected.
  3. **Authentication Method**: Select **Client ID and Secret (Services)**. This automatically generates your client ID and client secret.
  4. **Login Type**: Select **Agent**.
  5. **Agent to log in as**: Select your admin user as the agent to which the application is associated. If you select a different user, ensure that the user you select has the relevant access, as this can affect access control.
6. Copy the client ID and client secret. Save the client ID and client secret securely as you cannot copy or view them later. If you save the application before copying the client ID and client secret, you must regenerate the client secret and copy it. The client ID remains unchanged.
7. On the **Permissions**tab, select the **read:assets** checkbox. See image.
8. Click **Save**.

Your tenant is the unique subdomain which can be found in your HaloITSM Console URL (e.g., `https://<tenant>.haloitsm.com/home`)

For example, if your URL is `https://acme.haloitsm.com/home`, your tenant is `acme`.

See image.

Your tenant also be found in your HaloITSM console in **Configuration** > **Integrations** > **HaloITSM API**.

See image.

## Configuring the Connector

To create the Halo Assets data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Halo Assets data source, enter the following information in the source setup Retrieval section:

- Authentication
- Include Site Information (Optional)
- Include Asset Group Information (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Halo Assets Authentication** window appears.
2. In the **Halo Assets Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select this checkbox to include site-specific metadata for each asset, such as the site name, client association, physical address, time zone, and primary contact information.

Select this checkbox to include asset group metadata for each asset, such as the group name, internal notes, active status, and categorization details.

**[Image: Configuration HaloITSM API]**

**[Image: Viewing applications in the Halo console]**

[Image: Creating a new application in the Halo console]

[Image: Add an Application Details tab]

[Image: Selecting the read:assets permission checkbox in the Halo console]

[Image: Your tentant in the URL of the Halo console]

[Image: Your tenant in the Halo console]

[Image: The Halo Assets tile]

[Image: Halo Assets Authentication window]

[Image: Allow use of all Asset fields in the Halo console]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-hcl-bigfix-computers-connector","lastmod":"2026-03-18T07:06Z","nid":"1534400"} -->
## Configuring the HCL BigFix Computers Connector

- Source: https://help.zscaler.com/uvm/configuring-hcl-bigfix-computers-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the HCL BigFix Computers Connector
- Last modified: 2026-03-18T07:06Z
- Summary: How to configure the HCL BigFix - Computers connector, including setting up authentication and data retrieval filters and specifications.

The HCL BigFix - Computers connector is in limited availability. To learn more, contact Zscaler Support.

HCL BigFix is an endpoint management tool that enables organizations to monitor, manage, and secure devices across their environments. It provides capabilities for patch management, software distribution, compliance enforcement, and real-time visibility into endpoint configurations and statuses.

The HCL BigFix - Computers connector retrieves lists of computers along with their core properties, such as computer IDs, names, operating systems, last reported times, system statuses, and IP addresses.

## Prerequisites

The HCL BigFix - Computers source authentication configuration requires basic authentication credentials (i.e., a HCL BigFix username and password), associated with a Console Operator account. The account must have the following permissions configured:

- Console Operator status with Web Reports/REST APIaccess enabled.
- Can Submit Queries permission set to Yes to allow API access to retrieve data from the HCL BigFix server.
- Custom Content permission set to Yes for creating and managing custom content where necessary.
- View All Computers permission to enable visibility and access to all endpoints required for data retrieval.
- Can Read permissions across all relevant HCL BigFix sites and computer properties necessary for the scope of the integration.

These permissions can either be configured directly in the operator's role or assigned as explicit permissions. If the Console Operator is not being managed via WebUI, ensure that the user account is properly configured during creation by assigning the required sites, computer assignments, and roles. To learn more, refer to the [HCL BigFix documentation](https://help.hcl-software.com/bigfix/11.0/platform/Platform/Console/c_adding_local_operators.html).

## Configuring the Connector

To create the HCL BigFix - Computers data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the HCL BigFix - Computers data source, enter the following information in the source setup Retrieval section:

- Authentication
- Server
- Port

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **HCL BigFix Authentication** window appears.
2. In the **HCL BigFix Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the HCL BigFix server hostname or IP address for the system managing the endpoints. For example, the server hostname might be formatted as `bigfix.``<Your Organization Name>``.com`, or you can use an IP address like `192.168.1.100`.

If you are unsure about the correct hostname or IP address, contact your HCL BigFix administrator.

Enter the HCL BigFix REST API port. The default port for REST API communication is `52311`. If your deployment uses a custom port, enter the custom port.

If you are unsure about the correct port, contact your HCL BigFix administrator.

[Image: HCL BigFix Computers connector tile]

[Image: HCL BigFix Authentication Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-horizon3-ai-connector","lastmod":"2026-07-06T07:06Z","nid":"1541018"} -->
## Configuring the Horizon3.ai Connector

- Source: https://help.zscaler.com/uvm/configuring-horizon3-ai-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Horizon3.ai Connector
- Last modified: 2026-07-06T07:06Z
- Summary: How to configure the Horizon3.ai connector, including setting up authentication and data retrieval filters and specifications.

Horizon3.ai provides an autonomous penetration testing solution that helps organizations find, fix, and verify their exploitable attack surfaces.

There are three Horizon3.ai streams. Select those that are based on your Horizon3.ai feature plan and use cases:

- Horizon3 Assets: Retrieves discovered asset (host) details from Horizon3.ai penetration tests, including host identifiers and inventory, and risk context such as IP addresses, hostnames, operating system, score, severity, and weakness counts.
- Horizon3 Flat Impacts: Retrieves flat impact results identified in Horizon3.ai penetration tests (impact edges), including impact type, impact title, score, and severity, and associates impacts per referenced host when applicable.
- Horizon3 Weaknesses Evidence: Retrieves weaknesses and their supporting evidence from Horizon3.ai penetration tests by fetching a pre-signed Weaknesses Evidence CSV URL and ingesting the CSV rows. The Horizon3 Weaknesses Evidence stream is in limited availability. To learn more, contact Zscaler Support.

To learn more, see [Zscaler UVM and Horizon3.ai Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-horizon3-ai-deployment-guide).

## Prerequisites

The Horizon3.ai source authentication configuration requires an API key.

To create an API key:

1. In the Horizon3.ai platform, click the **Profile**menu in the top right, and select **Settings**. See image. The **Account Settings**page appears.
2. Click the **My Settings**tab. See image.
3. From the left-side navigation, select **API Keys**.
4. Click **Generate API Key**. See image. The **Generate API Key**window appears.
5. From the **Permission Level**drop-down menu, select **Read-Only**.
6. Click **Generate**.
7. Copy the generated API key. See image. Save the API key securely as you cannot copy or view it later.

## Configuring the Connector

To create the Horizon3.ai data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Horizon3.ai data source, enter the following information in the source setup Retrieval section:

- Authentication
- Choose Pentest
- Choose Pentest Types

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Horizon3 Authentication** window appears.
2. In the **Horizon3 Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

To learn more, see [Zscaler UVM and Horizon3.ai Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-horizon3-ai-deployment-guide).

Select whether the connector should retrieve data from only the latest completed penetration test, or from all available penetration tests in your Horizon3 account.

Select which penetration test types the connector retrieves data from. By default, all types are selected. Available options include Internal pentest, External asset discovery, External pentest, Network Enumeration, AD Password Audit, Phishing, AWS pentest, and K8s pentest.

[Image: Horizon3 Profile Menu Settings]

[Image: Horizon3 My Settings tab]

[Image: Horizon3 Generate API Key]

[Image: Horizon3 Copy API Key]

[Image: Horizon3 connector tiles]

[Image: Horizon3 Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-invicti-connector","lastmod":"2026-07-31T07:06Z","nid":"1528291"} -->
## Configuring the Invicti Connector

- Source: https://help.zscaler.com/uvm/configuring-invicti-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Invicti Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Invicti connector, including setting up authentication and data retrieval filters and specifications.

Invicti is a Dynamic Application Security Testing (DAST) tool for DevOps and DevSecOps teams to secure the applications that run their organizations.

The Invicti connector retrieves vulnerability data, including severity levels, CVSS scores, remediation guidance, and website group information.

## Prerequisites

The Invicti source authentication configuration requires a user ID and an API token.

To retrieve your user ID and API token:

1. In the Invicti platform, click the **Profile** menu on the top right of the page, and select **API Settings**.
2. Enter the password for the account, and click **Submit**. This does not apply if you use single sign-on (SSO).
3. Your user ID and API token are displayed.
4. Copy your user ID and API token. See image.

## Configuring the Connector

To create the Invicti data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Invicti data source, enter the following information in the source setup Retrieval section:

- Authentication
- Strip HTML from data (Optional)
- Enrich Vulnerability Content (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Invicti Authentication** window appears.
2. In the **Invicti Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

By default, the connector ingests raw HTML content. This is recommended if you require the original formatting for detailed parsing later.

Select this checkbox to remove raw HTML tags from vulnerability data fields (e.g., Details, Description, and Remediation Steps). When selected, the connector parses and cleans HTML tags to provide plain-text responses.

By default, the connector ingests only basic issue details.

Select this checkbox to retrieve further vulnerability data for each issue. When selected, the connector fetches detailed content, including the specific technical request sent by the Invicti scanner and the response received.

[Image: API Settings page in the Invicti platform]

[Image: Invicti connector tile]

[Image: Invicti Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-ionix-connector","lastmod":"2026-03-04T06:06Z","nid":"1534111"} -->
## Configuring the Ionix Connector

- Source: https://help.zscaler.com/uvm/configuring-ionix-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Ionix Connector
- Last modified: 2026-03-04T06:06Z
- Summary: How to configure the Ionix connector, including setting up authentication and data retrieval filters and specifications.

The Ionix connector is in limited availability. To learn more, contact Zscaler Support.

Ionix provides external attack surface visibility, offering insights into digital supply chain risks and actionable remediation data to help teams identify, validate, and resolve vulnerabilities efficiently.

There are two Ionix streams. Select those that are based on your Ionix feature plan and use cases:

- Ionix Assets: Retrieves all organizational assets from Ionix, which represent individual resources or entities being monitored. It includes metadata such as asset ID, asset type, and asset status.
- Ionix Findings: Retrieves detailed remediation action items, including vulnerabilities, assigned assets, and CVE-level details. Each record can be exploded to multiple CVE entries for granular tracking.

## Prerequisites

The Ionix source authentication configuration requires an API key (i.e., an Ionix token).

To generate an Ionix API token:

1. In the [IONIX portal](https://portal.ionix.io/login/?next_path=/pages/dashboard&_gl=1*1sfv0z3*_gcl_au*MTg3OTc5Mzk1NC4xNzY1Njk3NTIz*_ga*MTAyODg2OTExNC4xNzY1Njk3NTI0*_ga_DK5L2QDV0P*czE3NjU3MDU0MjYkbzIkZzEkdDE3NjU3MDU0NTQkajMyJGwwJGgw), go to **Settings**> **API**. See image.
2. At the top right of the page, enter a name for the token and set an expiration date. See image.
3. Click **Create Token**. A dialog window appears displaying the generated token. The generated token inherits the access permissions associated with the user creating it. Ensure that the privileges of the user creating the token align with the streams being configured (Assets or Findings) to enable successful data retrieval.
4. Copy the generated token to be used in the connector setup process. See image. Save the token securely as you cannot copy or view it later.
5. Click **Done**.

## Configuring the Connector

To create the Ionix data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Ionix data source, enter the following information in the source setup Retrieval section:

- Authentication
- Include Closed Vulnerabilities checkbox

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Ionix Authentication** window appears.
2. In the **Ionix Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

By default, only vulnerabilities marked as Open are retrieved. Select the Include Closed Vulnerabilities checkbox to include Closed vulnerabilities in the scope of the ingested data.

This checkbox is available in the Findings stream.

[Image: Ionix Portal Settings API]

[Image: Ionix Portal API Token Name and Expiration]

[Image: Copy Token Dialog Window]

[Image: Ionix connector tiles]

[Image: Ionix Authentication Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-jfrog-xray-connector","lastmod":"2026-07-31T07:06Z","nid":"1528401"} -->
## Configuring the JFrog XRay Connector

- Source: https://help.zscaler.com/uvm/configuring-jfrog-xray-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the JFrog XRay Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the JFrog XRay connector, including setting up authentication and data retrieval filters and specifications.

JFrog Xray is a universal software composition analysis (SCA) solution that integrates with JFrog Artifactory and identifies vulnerabilities on open source and license compliance violations.

The JFrog Xray connector retrieves vulnerability data from Artifactory and Xray, including vulnerability details, affected components, and associated security metadata.

## Prerequisites

To configure the JFrog Xray connector, you need the source authentication configuration. The following parameters are required:

- Username and Password
- URL

## Configuring the Connector

To create the JFrog Xray data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the JFrog XRay data source, enter the authentication information in the source setup Retrieval section:

1. In the **Authentication** section, click **Create New**. The **JFrog Xray Authentication** window appears.
2. In the **JFrog Xray Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

The username and password (or API key) for a JFrog service account (v3.80 or later). The account must be assigned the Manage Watches and Manage Reports roles.

To maintain a least-privileged security posture, you can disable the UI Access and Can Update Profile options for this user.

The base URL of your JFrog Artifactory or Xray instance (e.g., `https://<Instance Name>.jfrog.io`), without any trailing slashes or subpages.

You can retrieve the URL from the browser address bar while logged in to your JFrog deployment.

[Image: JFrog XRay connector tile]

[Image: JFrog XRay Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-jira-issues-connector","lastmod":"2026-08-03T14:41Z","nid":"1541622"} -->
## Configuring the Jira Issues Connector

- Source: https://help.zscaler.com/uvm/configuring-jira-issues-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Jira Issues Connector
- Last modified: 2026-08-03T14:41Z
- Summary: How to configure the Jira Issues connector, including setting up authentication and data retrieval filters and specifications.

Jira enables organizations to handle complex workflows and manage their work.

The Jira Issues stream retrieves standard issue and custom fields.

## Prerequisites

To configure the Jira Issues connector, you need the source authentication configuration. The following parameters are required:

- API Key
- Domain
- Email
- Verify SSL

## Configuring the Connector

To create the Jira data source in the Security Operations Platform:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Jira data source, enter the following information in the source setup Retrieval section:

- Authentication
- Project
- JQL
- Retrieve All Fields (Including Custom)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

The Jira API key field, which is a secret. To learn more about generating an API token for your account, refer to the [Atlassian documentation](https://support.atlassian.com/atlassian-account/docs/manage-api-tokens-for-your-atlassian-account/).

The domain of your Jira account (e.g., `yourcompany.atlassian.net`). It must follow the pattern `^a-zA-Z0-9._-*\.atlassian\.net$.`.

The user email of your Jira account.

Select the Verify SSL checkbox to allow the configuration of a root CA certificate to trust during the SSL negotiation in the CA certificate field.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Jira Issues Authentication** window appears.
2. In the **Jira Issues Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

A comma-separated list of Jira project keys to replicate data from. If left empty, the connector fetches issues from all projects accessible to the user.

The condition to filter the issues by (e.g., `status = complete, priority != highest`). If left empty, the connector retrieves all issues not filtered by a JQL condition.

Select the checkbox to retrieve all fields, including any custom fields configured in your Jira instance. If enabled, data volume might increase. If you do not select the checkbox, the connector retrieves a standard set of issue fields.

[Image: The Jira Issues connector tile]

[Image: The Jira Issues Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-jira-outegration","lastmod":"2026-08-18T02:06Z","nid":"1527961"} -->
## Configuring the Jira Outegration

- Source: https://help.zscaler.com/uvm/configuring-jira-outegration
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the Jira Outegration
- Last modified: 2026-08-18T02:06Z
- Summary: How to configure the Jira outegration for disptaching tickets to the Jira Data Center or Jira Cloud work management systems.

The Jira outegration is used to dispatch tickets from the Zscaler Security Operations (SecOps) platform applications (e.g., UVM) to your Jira project, creating a Jira issue that can then be tracked, assigned, and managed by your remediation teams working with Jira.

This article is a step-by-step guide to setting up the Jira work management outegration. The process involves setting up authentication, outegration visibility in the platform, outegration mapping, and when relevant, configuring a Jira webhook to enable bidirectional synchronization.

Each Jira issue type (e.g., Bug, Task, Feature) requires a separate outegration configuration.

## Prerequisites

Before getting started, identify the Jira platform your organization uses: Jira Cloud or Jira Data Center. While the setup process for both Jira outegrations is mostly similar, Jira Data Center users must first set up a gateway and then proceed to follow the standard Jira outegration setup process. To learn more, see [Configuring the Security Operations Platform Gateway](https://help.zscaler.com/unified/configuring-security-operations-platform-gateway).

Retrieve the required authentication parameters based on your Jira deployment type (i.e., Jira Cloud or Jira Data Center), and enter them in the corresponding fields during the Connect step of the outegration setup wizard.

- Jira Cloud
- Jira Data Center

## Creating the Jira Outegration

To configure the Jira outegration, complete the following steps:

- Step 1: Authenticate the Jira Connection (Connect)
- Step 2: Configure the Outegration Visibility and Behavior (Settings)
- Step 3: Map the Outegration Fields (Mapping)
- Step 4: Configure the Jira Webhook

Obtain the following required parameters for the Jira Cloud outegration:

- Jira Organization Domain
- Project Key
- Choose one of the following authentication methods and retrieve the necessary parameters:
  - Email Password Domain
  - API Key Domain

The Jira organization domain is the unique domain that identifies your organization's Jira instance, in the format `<Domain Name>``.atlassian.net`. For example, if your Jira Cloud URL is `https://``acme``.atlassian.net`, then your Jira organization domain is `acme.atlassian.net`.

See image.

Your Jira project key is the shortened version of the Jira project name that you want the platform to dispatch tickets to.

Your project key can be found in the following locations:

- All Projects list:The Key column displays the project key for each of your projects.
- Jira Issue ID Prefix:The project key is often used as a prefix for issue IDs (e.g., `PROJ-123`).
- Project URL:The project key is included in the URL of your project, after `/projects/`. For example, the project key in the URL `https://acme.atlassian.net/projects/PROJ/summary` is `PROJ`.

For the Email Password Domain authentication method, you’ll need to provide the email and password (API Token) associated with a Jira user account. This account is used to authenticate with Jira and will be displayed as the issue reporter in Jira by default, unless an alternative reporter is specified in the Jira mapping.

For the API Key Domain authentication method, you’ll need to provide the Jira API key that was generated using a Jira user account. To learn more, refer to the [Jira Atlassian documentation](https://support.atlassian.com/statuspage/docs/create-and-manage-api-keys/).

To generate an API key:

1. Log in to your Jira instance using an admin account.
2. Click your avatar in the bottom left of the management interface.
3. Click**API info**.
4. Click **Create key**.
5. Enter a key name that clearly indicates its association with the SecOps platform application (e.g., UVM, AEM).
6. Click**Confirm**.
7. Copy and securely save the key to be used in the Connect step.

Before proceeding, make sure a Zscaler Gateway has been configured. To learn more, see [Configuring the Security Operations Platform Gateway](https://help.zscaler.com/unified/configuring-security-operations-platform-gateway).

Obtain the following required parameters for the Jira Data Center outegration:

- Jira Organization Domain
- Project Key
- Choose one of the following authentication methods and retrieve the necessary parameters:
  - Email Password Domain
  - API Key Domain
- Gateway

The Jira organization domain is the unique domain that identifies your organization's Jira instance, typically the domain name or host name of your Jira server. For example, if your Jira Data Center URL is `https://jira.acme.com`, your Jira organization domain is `jira.acme.com`.

See image.

The domain for Jira Data Center can vary depending on how your Jira instance is configured and hosted.

Your Jira project key is the shortened version of the Jira project name that you want the platform to dispatch tickets to.

Your project key can be found in the following locations:

- All Projects list:The Key column displays the project key for each of your projects.
- Jira Issue ID Prefix:The project key is often used as a prefix for issue IDs (e.g., `PROJ-123`).
- Project URL:The project key is included in the URL of your project, after `/projects/`. For example, the project key in the URL `https://acme.atlassian.net/projects/PROJ/summary` is `PROJ`.

For the Email Password Domain authentication method, you’ll need to provide the email and password (API Token) associated with the Jira admin account. This account is used to authenticate with Jira and will be displayed as the issue reporter in Jira by default, unless an alternative reporter is specified in the Jira mapping.

For the API Key Domain authentication method, you’ll need to provide the Jira API key that was generated using a Jira admin account. To learn more, refer to the [Jira Atlassian documentation](https://support.atlassian.com/statuspage/docs/create-and-manage-api-keys/).

To generate an API key:

1. Log in to your Jira account as an admin.
2. Click your avatar in the bottom left of the management interface.
3. Click**API info**.
4. Click **Create key**.
5. Enter a key name that clearly indicates its association with the SecOps platform application (e.g., UVM, AEM).
6. Click**Confirm**.
7. Copy and securely save the key to be used in the Connect step.

Jira Data Center users must first set up a gateway and then proceed to follow the standard Jira outegration setup process. To learn more, see [Configuring the Zscaler Gateway](https://help.zscaler.com/uvm/configuring-zscaler-gateway).

If you already have a gateway configured, select the gateway from the drop-down menu.

The first step in setting up your Jira outegration is to authenticate using valid credentials to establish a secure connection with your Jira project. With the required parameters retrieved in the prerequisites, you can begin the Jira outegration setup in the SecOps platform.

To create an outegration:

1. In the SecOps platform, go to **Configure** > **Outegrations**. See image.
2. Click **Create**, then select either **Jira Cloud** or **Jira Data Center**, depending on your organization's Jira deployment. The **Connect** step appears. See image.
3. In the **Details** section:
  1. **Display Name**: Enter a name for your outegration.
  2. **Active**: Enable to activate the Jira outegration.
  3. **Project Key**: Enter the key of the Jira project where the tickets should be created.
  4. **Authentication**: Select an existing authentication, or click **Create New** to set up a new authentication and enter the required parameters you retrieved earlier into the corresponding fields. To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications).
4. Click **Test** in the bottom-right corner of the page to verify the connection. Invalid credentials trigger error messages to assist with troubleshooting connectivity issues.
5. After the test passes, click **Next**to advance to the **Settings**step.

[Image: create jira cloud outegration page connect step]

In the Settings step of the outegration setup wizard, configure your Jira outegration's visibility and behavior within the relevant application in the SecOps platform (e.g., UVM, AEM). In this step, you'll set the SecOps entity that triggers the Jira issue dispatch (e.g., ticket, violation ticket), the Jira Issue Type that the SecOps ticket should be dispatched to, and when the Create Jira Ticket button should appear in the application. The Create Jira Ticket button allows end users with access to SecOps tickets to dispatch these tickets to a Jira project directly from the SecOps ticket drawer or from the SecOps tickets page.

To configure the outegration's visibility and behavior:

1. In the **Advanced Settings** section:
  1. **Create Jira item from**:Select the entity that you want to configure the outegration for. This selection affects the view you'll configure in the <Entity> **View**step (e.g., selecting Ticket displays the Tickets View setting). Other entity types might be visible depending on the apps enabled in your account. See image.
    - **UVM**: Select **Ticket**.
    - **AEM**: Select **Violation Ticket**.
  2. **Issue Type**:Select the Jira issue type that the ticket should be dispatched to (e.g., **Task**, **Bug**). The schema associated with the selected issue type will be retrieved from your Jira project and made available for mapping in the Mapping step. See image.
2. In the <Entity> **View** section, select how the SecOps ticket should display the **Create Jira Ticket** button. This setting can be modified at any time. See image.
  - **Always**: Select to display the button in all tickets, allowing users to dispatch tickets to a Jira issue without exception.
  - **Never**: Select to hide the button in all tickets. This is useful during the outegrationsetup process to hide the button from users while still keeping the outegration active.
  - **For specific tickets**: Define custom conditions to control when the button is displayed, allowing you to target specific tickets. For example, if your organization uses multiple ticketing systems, you can grant access to the button only to users who work with Jira, while excluding those who use other ticketing systems (e.g., ServiceNow).
3. Click **Map**to advance to the **Mapping** step.

The **Create Jira Ticket**button appears in two locations:

- In the individual entity drawer (e.g., in the [UVM ticket drawer](https://help.zscaler.com/uvm/managing-tickets-uvm), in the [AEM violation ticket drawer](https://help.zscaler.com/uvm/managing-violation-tickets-aem)). See image.
- On the entity page in the relevant application (e.g., on the [Tickets page](https://help.zscaler.com/uvm/about-tickets-operational-view-uvm) in UVM, on the [Violation Tickets page](https://help.zscaler.com/uvm/about-violation-tickets-operational-view-aem) in AEM). See image.

The third step in setting up your Jira outegration is configuring the field mapping between your SecOps tickets and Jira issues. This defines how data is exchanged and synchronized between the two systems upon initial dispatch and subsequent updates. The SecOps platform's unique mapping capabilities allow for flexible mapping of any custom field or logic to any field in your Jira projects, facilitating highly customized workflows that align with your organization's requirements.

The main objective of the mapping process is to map values to fields. To map values to fields, configure values on the left to populate the fields selected on the right.

See image.

There are three mapping components:

- Tickets initially dispatched to Jira: Map SecOps ticket fields (left) to Jira fields (right) for the initial dispatch of a ticket to a Jira issue. You can also add an attachment to your Jira issue. Commonly mapped fields include Summary, Description, Assignee, Priority, Due Date, and Status.
- (Optional) Sync from ticket to Jira: Map SecOps ticket fields (left) to Jira fields (right) for syncing ticket updates to Jira, including configuring comments and adding an attachment to your Jira issue. Commonly mapped fields include Status and Due Date.
- (Optional) Sync from Jira to ticket: Map Jira fields (left) to SecOps ticket fields (right) for syncing Jira updates to tickets. This step also requires setting up a Jira webhook. Commonly mapped fields include Ticket Status and Ticket SLA.

The initial Jira outegration mapping includes preconfigured default mappings for each part, based on common use cases and industry best practices. These defaults can be modified and customized as needed.

### Creating a New Mapping

In each of the three mapping components, you’ll need to select a field on the right, and then configure the corresponding field value on the left.

To create a new mapping:

1. Select a field (right):
  1. Click**Mapping**.
  2. Select a field on the right. The field's schema details open on the right of the page. The schema lists available Jira fields to be used during mapping. This is the list of fields configured in your Jira project for the Issue Type selected in the Settings step (e.g., Bug). See image. The following details are specified for fields, when available:
    - Required
    - Input Type
    - Available Options
2. Configure the field value (left):
  1. Click **Add value** on the left. The **Field Editor** appears.
  2. In the **Field Editor**, select one of the following methods to configure the value of the field:
    - Field (Dictionary)
    - Smart Text
    - Script

Repeatthe mapping process for all required Jira fields and for any other fields you want to map.

In addition to the mapping of fields on the right to fields on the left, you can perform a set of actions when setting up the Jira outegration mapping, each relevant to a specific part of the mapping.

- Set a ticket field as mandatory.
- Add attachments to the ticket dispatch.
- Configure comments synchronization.

### Mapping Ticket Title to Summary

To illustrate the mapping process, consider the mapping of the required Jira Summary field. The final result of the mapping process should show the Summary field on the right, and the Ticket Title field on the left.

To map the Ticket Title field to the Summary field:

1. Select **Summary**as the Jira field on the right. See image. Selecting the Jira Summary field opens the field's details in the schema. The schema specifies that the field is required and thus must be mapped before the outegration can be saved, and that the field expects a TEXT input type. Therefore, the field for which a value is being configured must also be of TEXT type. See image.
2. Select the **Ticket Title**field on the left: See image.
  1. Click**Add Value**.
  2. Under the **Field** tab, select the **Ticket Title** field, which is the equivalent to the Jira **Summary**field.

### Previewing the Ticket to Jira Mapping

After completing the SecOps ticket to Jira dispatch mapping, preview the mapping to review the configuration. This helps ensure that SecOps ticket dispatch is behaving as expected and that the Jira issue fields are populated correctly.

To preview the mapping, click **Preview**on the bottom right of the ticket initially dispatched to Jira section. The Mapping Preview window appears. On the left of the Mapping Preview window, there is a sample of the SecOps tickets in your account, organized by ticket ID. You can select, filter, or search tickets and preview the mapping to their corresponding Jira issue. You can also open the actual SecOps ticket in a new tab for a more in-depth review.

See image.

### Common Mapping Examples

These mapping examples highlight commonly used field configurations in your outegration. While some might be preconfigured by default, Zscaler recommends reviewing and customizing them to ensure they align with your workflow.

- Ticket to Jira Description
- Ticket SLA to Jira Due Date Sync
- Jira to Ticket Status Sync

The Required attribute is TRUE if a field is required by Jira. If a field is not required, the attribute is not displayed. A required Jira field is also indicated by a red asterisk (*) on the Jira field in the first mapping step.

Required Jira fields must be mapped before saving the outegration.

The Input Type specifies the data type of the Jira field, such as TEXT (e.g., Summary), DATE (e.g., Due Date), or NUMBER (e.g., Risk Score). This indicates the format that the selected source field must match in order to successfully map to the Jira field.

For Jira fields with fixed values, the Available Options column displays the available values. For example, if the Jira field Priority is configured to include the following fixed values—High, Low, Medium, Lowest, Highest—the corresponding values in the Ticket Severity field can be mapped to these values.

Select a field on the left to populate the field on the right.

#### Dictionary

The field dictionary allows you to create mappings between specific values from the field on the right and values of the field on the left. To use the dictionary, you must first select a field on the right and a field to populate it with on the left.

For example, if your JiraPriority field includes the following fixed values—Highest, High, Medium, Low, Lowest—you can use the dictionary to map the corresponding Ticket Severity values to each of the Priority field values.

See image.

Configure the field value using free text, or create a template using a combination of free text and selected fields. This allows you to dynamically insert specific field values (e.g., Ticket SLA, Ticket Assignee, or Asset Name) into customized free text sentences or paragraphs.

To add a Smart Text field, enclose it in double curly brackets (e.g., `{{Ticket Assignee}}`). The field's display name automatically translates to its system name.

This option is commonly used to configure the value of fields like Ticket Title and Ticket Description.

See image.

For use cases that require more advanced configuration than either of the two methods above, you can use Python scripts to configure the field value to be mapped to the target field.

When dispatching tickets to Jira, map the Jira Description field with a summary of the Ticket content to provide remediation teams with a brief overview of the ticket.

To configure the Ticket to Jira Description mapping:

1. Click**Mapping**.
2. Select**Description** as the field on theright.
3. Click **Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Smart Text**.
5. Enter a ticket description, including dynamic fields (e.g., `{{SLA}}`).

Tickets dispatched to Jira will now include the configured description.

In the Ticket to Jira sync, map the Jira Due Date field to keep timelines in sync with Ticket SLA changes.

To configure the Ticket SLA to Jira Due Date mapping:

1. Click**Mapping**.
2. Select**Due Date** as the field on the right.
3. Click**Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Field**, and select **Ticket SLA** as the field on the left.

Ticket SLA changes will now automatically update Jira issue due dates.

In the Jira to Ticket sync, map the Ticket Status field to ensure it's updated when remediation teams change the Jira Issue Status.

To configure the Jira to Ticket Status mapping:

1. Click**Mapping**.
2. Select**Ticket Status** as the field on the right.
3. Click**Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Field**, and select **Status** as the field on the left. Use the **Dictionary** to map your Jira**Status** types to **Ticket Status** types.

Jira issue Status changes will now automatically update the Ticket Status.

You can set a SecOps ticket field as mandatory, by selecting the Set as Mandatorycheckbox in the Column Menu to the right of the mapping. Some fields can be set as mandatory by default.

See image.

Setting a field as mandatory guarantees that critical fields (e.g., Ticket Assignee) are always populated before a ticket is dispatched to Jira, so Jira issues are always actionable for your remediation teams. Attempts to dispatch a ticket without a value in a mandatory field will trigger an error message.

Required fields are defined by your Jira schema, whereas Mandatory fields are mandatory for SecOps ticket dispatch.

You can create a file attachment that summarizes your ticket content and set the trigger to automatically add it to your Jira issue. Adding an attachment to your Jira issue simplifies the review and management of findings dispatched from a ticket.

You can configure file attachments in two of the mapping steps:

- Initial ticket dispatch See image.
- Sync from Ticket to Jira See image.

When configured in the Ticket to Jira sync section, the attachment is included in the Jira issue alongside existing attachments as a downloadable file in the selected format.

Use the **File Format** drop-down menu on the top left of the attachment page to select from the available formats (**CSV**, **PDF**, **JSONL**, **Excel**).

See image.

To provide your Jira remediation teams with a comprehensive view of the findings in the ticket, consider including the following fields in your attachment:

- Recommended Attachment Fields

- Finding Severity
- Finding Title
- Finding CVE
- Component Name
- Asset Name
- Asset Operating System
- Finding Optimal Fix
- Finding Description
- Finding Sources

In the Ticket to Jira sync step, you can configure how ticket comments are synchronized with Jira issue comments. To configure comments, click **Comment Sync**.

See image.

### Sync Comments

Enable **Sync Comments**to automatically push comments from the ticket's Comments tab to the corresponding Jira issue.

See image.

### Trigger Comments

Enable **Sync Trigger Comments**and set conditions to trigger a comment when specific fields are modified. Syncing trigger comments is useful when you want to be notified of important changes to tickets without updating the corresponding Jira issue. For example, you can configure a trigger to post a comment in Jira when the Ticket Severity changes from Medium to Critical.

See image.

To add a trigger condition:

1. Select the field you want to monitor (e.g., **Severity**).
2. Set the value change that should trigger the comment:
  - **From**: Select the original value.
  - **To**: Select the updated value.

When the specified change occurs in the ticket, a comment is automatically created and added to the Jira issue. The following is an example of a trigger comment:

```
Linked UVM ticket updated:
Ticket severity changed from: MEDIUM to: CRITICAL
<URL to ticket>
```

[Image: jira outegration mapping]

The Jira outegration webhook enables automatic syncing of Jira issue updates (e.g., Status or SLA changes) to their corresponding SecOps tickets, reducing the need for manual changes. This step is required when configuring the Jira to Ticket mapping to keep issues and tickets in sync. To learn more, see [Configuring the Jira Outegration Webhook](https://help.zscaler.com/uvm/configuring-jira-outegration-webhook).

See image.

A Jira webhook is only needed to sync updates from Jira to the ticket. It is not required for the initial ticket dispatch or for syncing updates from the ticket to Jira.

When the outegration setup is complete, you can begin dispatching SecOps tickets using the Create Jira Ticket button that appears in the Create Ticket menu within individual tickets, as well as in the Create Issue menu in the Tickets View. To learn more, see [Creating & Managing Third-Party Tickets](https://help.zscaler.com/uvm/creating-managing-third-party-tickets).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-jira-outegration-webhook","lastmod":"2026-08-18T02:07Z","nid":"1527986"} -->
## Configuring the Jira Outegration Webhook

- Source: https://help.zscaler.com/uvm/configuring-jira-outegration-webhook
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the Jira Outegration Webhook
- Last modified: 2026-08-18T02:07Z
- Summary: How to configure the Jira outegration webhook for bidirectional sync when setting up the Jira outegration.

The Jira outegration webhook enables automatic syncing of Jira issue updates such as Status or SLA changes to their corresponding tickets, reducing the need for manual changes. This step is required when configuring the Jira to ticket mapping to keep issues and tickets in sync. To learn more, see [Configuring the Jira Outegration](https://help.zscaler.com/uvm/configuring-jira-outegration).

See image.

This article provides instructions for setting the Jira webhook and applies to both Jira Cloud and Jira Data Center. To learn more, refer to the [Atlassian documentation](https://developer.atlassian.com/server/jira/platform/webhooks/#registering-a-webhook).

To set up your Jira webhook:

1. Log in to your Jira console as a user with the **Administer Jira**global permission. To learn more, refer to the [Atlassian documentation](https://support.atlassian.com/jira-cloud-administration/docs/manage-global-permissions/).
2. Go to **Settings** > **System**. See image.
3. Select the **Webhooks** tab.
4. Click **Create a WebHook**. See image.
5. In the dialog window:
  1. **Name**: Enter a name for the webhook.
  2. **Status**: Select **Enabled**.
  3. **URL**: Paste the following URL into the field: `https://webhook.avalor.io/integration/``<Account ID>``/jira`. Replace `<Account ID>` with your account ID. See image.
  4. In the **Issue related event**s section:
    1. **Filter**: Configure a filter to send updates from the relevant project only. The filter format is `project =``<Your Project Key>`.
    2. **Issue**: Select the **updated** and **deleted** checkboxes. See image.
6. Click **Create**.

After your webhook is set up, configured triggers for field updates in your Jira outegration mapping automatically sync changes made to Jira issues with their corresponding tickets.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-lacework-connector","lastmod":"2026-07-31T07:06Z","nid":"1528256"} -->
## Configuring the Lacework Connector

- Source: https://help.zscaler.com/uvm/configuring-lacework-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Lacework Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Lacework connector, including setting up authentication and data retrieval filters and specifications.

Lacework is a cloud security tool that detects threats, vulnerabilities, misconfigurations, and unusual activity in your cloud environments. Lacework learns how your environment is supposed to run and tells you when it deviates.

The Lacework connector retrieves vulnerability findings, including CVE details (e.g., severity, CVSS scores, and reference links), affected assets (e.g., images, hosts, and packages), risk scores, and fix information.

## Prerequisites

To configure the Lacework connector, you need the source authentication configuration. The following parameters are required:

- API Key and API Secret
- Account ID

## Configuring the Connector

To create the Lacework data source in the SecOps platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Lacework data source, enter the following information in the source setup Retrieval section:

- Authentication
- Number of days to fetch (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

You can generate an API key and secret from either a user with an admin role or a user with write permission for API keys. You can also set up a service user specifically for the Zscaler Security Operations (SecOps) platform source setup.

### Setting Up a Service Account

To create a service user, you need organization administrative privileges.

To set up a service account:

1. Log in to the Lacework console.
2. Go to **Settings** > **Access control** > **Users**.
3. Click **Add New**.
4. In the user type section, select **Service User**.
5. Enter a name for the user, and optionally add a description.
6. Click **Next**, then click **Next** again.
7. In the user group section, click **Account Admin**.
8. Click **Save**.

### Creating an API Key

To create an API key:

1. Log in to the Lacework console.
2. Go to **Settings** > **API Keys**.
3. Select a user option:
  - For a human user, select the **User API Keys** tab.
  - For a service user, select **Service user API Keys**.
4. Click **Add New**.
5. Enter a name for the user, and optionally add a description.
6. For service users, enable **Assign this to a service user** and select the service user from the list.
7. Click **Save**.
8. After the API key is created, click the ellipsis icon and download the JSON file.
9. Open the JSON file and copy the key ID, secret, and account name values. Save the values securely as you cannot copy or view them later.

To learn more, refer to the [Lacework documentation](https://docs-lacework.netlify.app/console/api-access-keys).

In the Account ID field, enter your Lacework account name (subaccount).

You can find your account ID in the following locations:

- In your Lacework console URL `https://``<Account ID>``.lacework.net`, enter only `<Account ID>`.
- In the downloaded API key JSON, copy the value for account.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Lacework Authentication** window appears.
2. In the **Lacework Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

In the Number of days to fetch field, enter the number of days of data you want the connector to retrieve during each run. For example, enter `3` to fetch data from the past 3 days.

[Image: Lacework connector tile]

[Image: Lacework Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-legit-security-connector","lastmod":"2026-07-31T07:06Z","nid":"1528361"} -->
## Configuring the Legit Security Connector

- Source: https://help.zscaler.com/uvm/configuring-legit-security-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Legit Security Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Legit connector, including setting up authentication and data retrieval filters and specifications.

Legit Security protects your software supply chain and oversees application security from code to cloud. It enables secure software releases through automatic detection and resolution of security concerns.

There are two Legit Security streams. Select those that are based on your Legit Security feature plan and use cases:

- Legit: Retrieves all issue types except incidents, including each issue's source, integration, issue metadata, issue type, and full issue payload details.
- Legit Incidents: Retrieves security incidents, including each incident's source, integration, incident metadata, and full payload details.

## Prerequisites

To configure the Legit Security connector, you need the source authentication configuration. The following parameters are required:

- Token
- Tenant ID

## Configuring the Connector

To create the Legit Security data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Legit Security data source, enter the following information in the source setup Retrieval section:

- Authentication
- Exclude Snyk Issues (Optional)
- Sources

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To generate a token:

1. In the Legit Security console, go to **Settings** > **API**.
2. Select **Read** > **Generate Token**.
3. Copy the generated token. Save the token securely as you cannot copy or view it later.

Enter your Legit Security tenant name (the subdomain used to access Legit Security), located in your Legit Security URL: `https://``<Tenant>``.legitsecurity.co`. For example, if your Legit Security URL is `https://acme.legitsecurity.co`, your tenant ID is `acme`.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Legit Authentication** window appears.
2. In the **Legit Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select this checkbox to exclude Snyk-related issues from retrieval. When enabled, the connector filters out issues with the type `SnykIssue` or `ScaIssue` from the scope of the ingested data.

Use this filter to control which Legit Security sources (scanners/integrations) the connector retrieves data from. Select All to retrieve issues or incidents from all available sources, or select Specific to retrieve data only from selected sources. When you select Specific, a drop-down menu appears. By default, the Legit and Checkov sources are selected. To include additional sources, enter the source name and press `Enter` after each entry.

[Image: Legit connector tiles]

[Image: Legit Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-mandiant-asm-connector","lastmod":"2026-03-18T07:06Z","nid":"1535181"} -->
## Configuring the Mandiant ASM Connector

- Source: https://help.zscaler.com/uvm/configuring-mandiant-asm-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Mandiant ASM Connector
- Last modified: 2026-03-18T07:06Z
- Summary: How to configure the Mandiant ASM connector, including setting up authentication and data retrieval filters and specifications.

The Mandiant ASM connector is in limited availability. To learn more, contact Zscaler Support.

Mandiant Attack Surface Management (ASM) is a cloud-based security solution designed to help organizations identify, manage, and secure their external attack surface. It provides visibility into all internet-facing components—including domains, IP addresses, hostnames, certificates, and applications—that could be exposed to attackers.

There are two Mandiant ASM streams. Select those that are based on your Mandiant ASM feature plan and use cases:

- Mandiant ASM Entities: Retrieves all exposed assets within the organization's external attack surface, including domains, subdomains, IP addresses, hostnames, URLs, and associated Secure Sockets Layer/Transport Layer Security (SSL/TLS) certificates.
- Mandiant ASM Issues: Retrieves detailed issues, such as misconfigurations, open ports, vulnerabilities with severity levels, and SSL/TLS-related weaknesses, along with their statuses and remediation recommendations.

## Prerequisites

The Mandiant ASM source authentication configuration requires an API key.

To generate a Mandiant ASM API key:

1. In the Mandiant ASM portal, go to **Account Settings**.
2. In the left-side navigation, click **API Keys**.
3. Click **Generate New Key**to create a new API key. See image.
4. Copy the generated token to be used in the connector setup process. Save the API key securely. It is not available again after you leave this page.

## Configuring the Connector

To create the Mandiant ASM data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Mandiant ASM data source, enter the following information in the source setup Retrieval section:

- Authentication
- Select Retrieval Option
- Issues Status
- Minimum Severity
- Entity Types

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Mandiant ASM Authentication** window appears.
2. In the **Mandiant ASM Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Set the **Select Retrieval Option**to determine which Mandiant ASM projects are included in the scope of the ingested data.

- **Specify Selected Projects**: Select to retrieve data from specific projects only. When this option is selected, the **Project IDs**field appears for input. Enter the comma-separated list of project IDs (e.g., id1, id2, id3) to pull data from.
- **Pull Data from All Projects**: Retrieve data across all available projects in the Mandiant ASM platform.

This configuration is available in the following streams:

- Mandiant ASM Entities
- Mandiant ASM Issues

From the **Issues Status**drop-down menu, select the statuses of issues to include in the scope of the ingested data (e.g., **Open**, **Closed**). The complete list of issue statuses is retrieved via the API and reflects the issue statuses in your Mandiant ASM environment.

This drop-down menu is available in the Mandiant ASM Issues stream.

From the **Minimum Severity**drop-down menu, select the lowest severity level to include in the scope of the ingested data. The connector ingests findings at the selected severity level and all higher levels. Available severity levels to select include **Critical**, **High**, **Medium**, **Low**, and **Informational**. For example, selecting **Medium**retrieves issues classified as **Medium**, **High**, and **Critical**.

This drop-down menu is available in the Mandiant ASM Issues stream.

From the **Entity Types**drop-down menu, select the entity types to include in the scope of ingested data (e.g., **ApiEndpoint**, **AppEndpoint**, **Domain**, **IpAddress**). The complete list of entity types is retrieved via the API and reflects all the supported types in your Mandiant ASM environment.

This drop-down menu is available in the following streams:

- Mandiant ASM Entities
- Mandiant ASM Issues

[Image: Mandiant ASM API Generate New Key]

[Image: Mandiant ASM connector tiles]

[Image: Mandiant ASM Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-microsoft-defender-cloud-findings-connector","lastmod":"2026-08-18T01:50Z","nid":"1531041"} -->
## Configuring the Microsoft Defender for Cloud Findings Connector

- Source: https://help.zscaler.com/uvm/configuring-microsoft-defender-cloud-findings-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Microsoft Defender for Cloud Findings Connector
- Last modified: 2026-08-18T01:50Z
- Summary: How to configure the Microsoft Defender for Cloud connector, including setting up authentication and data retrieval filters and specifications.

Microsoft Defender for Cloud is a cloud-native application protection platform (CNAPP) that safeguards cloud-based applications from cyber threats through unified security management, breach prevention, and workload protection.

The Microsoft Defender for Cloud Findings connector retrieves security findings for cloud assets, providing details on vulnerabilities, compliance issues, and recommended remediations.

This connector retrieves cloud data only.

For Azure Cloud Assets, see [Configuring the Azure Cloud Assets Connector](https://help.zscaler.com/uvm/configuring-azure-cloud-assets-connector). For Microsoft Defender for Endpoints, see [Configuring the Microsoft Defender for Endpoints Connector](https://help.zscaler.com/uvm/configuring-microsoft-defender-endpoints-connectors).

To learn more, see [Zscaler and Microsoft Defender for Cloud Apps Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-defender-cloud-apps-deployment-guide).

## Prerequisites

To configure the Microsoft Defender for Cloud Findings connector, you need to configure the appropriate roles and permissions. You also need the source authentication configuration. The following parameters are required:

- Application (client) ID and Directory (tenant) ID
- Client Secret

To enable Microsoft Defender for Cloud for your Azure subscriptions, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/defender-for-cloud/connect-azure-subscription#enable-defender-for-cloud-on-your-azure-subscription).

### Roles and Permissions

On the application API permission tab, assign the permissions to the application.

- View instructions.

The Application (client) ID and the Directory (tenant) ID are located on the Overview tab of an application.

To create a new application:

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, click **New registration**.
  - **Name**: Enter a name for the application.
  - **Support account types**: Select **Accounts in this organizational directory only**.
  - **Redirect URI (optional)**: Optionally configure these settings.
4. Click **Register** to create the application. The application **Overview** section appears.
5. In the **Overview** section, copy the generated **Application (client) ID** and the **Directory (tenant) ID**.

On the application **Certificates & secrets** tab, generate and copy a client secret for the application.

To generate a client secret:

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, select your app registration.
4. From the left-side navigation, from the **Manage** drop-down menu, select **Certificates & secrets**.
5. Click **New client secret**.
  - Optionally, enter a description.
  - Enter an expiration date.
6. Click **Add**.
7. Copy the client secret value. Save the client secret value. The client secret is not displayed again after you leave this page.

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, select your app registration.
4. In your app registration, in the left-side navigation, from the **Manage** drop-down menu, select **API permissions**.
5. In the **Configured permissions** section, click **Add a permission**.
  1. Go to the **Microsoft APIs** tab.
  2. Select **Microsoft Graph**.
  3. Select **Application permissions**.
  4. Add the **PrivilegedAccess.Read.AzureResources** permission.
  5. Click **Add permissions**.
  6. If admin consent is required for these permissions, select **Grant admin consent**.
  7. Click **Yes** to confirm the action.

## Configuring the Connector

To create the Microsoft Defender for Cloud Findings data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Microsoft Defender for Cloud Findings data source, enter the following information in the source setup Retrieval section:

- Authentication
- Specific Subscription IDs
- All Subscription IDs in org
- Findings Types drop-down menu

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Microsoft Defender for Cloud Authentication** window appears.
2. In the **Microsoft Defender for Cloud Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

[Image: Microsoft Defender for Cloud - Findings Tile]

Enter the subscription IDs from which you want to retrieve data.

In the subscriptions service, retrieve the subscription ID for the relevant subscriptions and configure the role assignment.

To retrieve your subscription ID:

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Subscriptions`. From the drop-down menu, select **Subscriptions**.
3. On the **Subscriptions** page, select the subscription you want to use.
4. On the **Overview** tab, copy the **Subscription ID**. See image. You can enter multiple subscription IDs in the platform.

To assign the Reader role to a single subscription:

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Subscriptions`. From the drop-down menu, select **Subscriptions**.
3. On the **Subscriptions** page, from the left-side navigation, select **Access control (IAM)**.
4. On the **Access control (IAM)** tab, click **Add** > **Add role assignment** to configure the following: To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/role-based-access-control/role-assignments-portal).
  1. On the **Role** tab, select the **Reader** role.
  2. On the **Members** tab, select **User, group, or service principal**.
  3. Click **+ Select members** and select the application you registered previously.
  4. On the **Assignment type** tab, set the assignment duration.
  5. On the **Review + assign** tab, review the assignment and click **Review + assign**.

Data is retrieved from all subscriptions in your organization. If you have multiple subscriptions, you can organize them into a single management group to set access control in bulk.

To assign the Reader role to multiple subscriptions:

1. Log in to the Microsoft Azure Portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `Management groups`. From the drop-down menu, select **Management groups**.
3. On the **Management groups** page, click **Create** to configure the following:
  - **Management group ID**: The directory unique identifier that is used to submit commands on this management group. You cannot edit this ID after creation.
  - **Management group display name**: (Optional) The name displayed in the Azure portal.
4. Click **Submit**.
5. On the **Management groups** page, click the name of the management group you created.
6. Click **Add subscription**. The **Add subscription** window appears.
7. In the **Add subscription** window, select the subscription you want to add to the management group.
8. Click **Save**.
9. Select the name of the subscription group you added.
10. On the subscription group page, from the left-side navigation, click **Access control (IAM)**.
11. Click **Add**> **Add role assignment** to configure the following:
  - On the **Role** tab, select the **Reader** role.
  - On the **Members** tab, select **User, group, or service principal**.
  - Click **+ Select members** and select the application you registered previously.
  - Click **Select members**, and then select the application you registered previously.
  - On the **Review + assign** tab, review the assignment and click **Review + assign**.

To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/governance/management-groups/overview).

Select the finding type that you want to include in the scope of data ingestion (i.e., CVEs, Misconfigurations, or both).

[Image: Copying the Subscription ID on the Subscriptions page of the Microsoft Azure portal]

[Image: Microsoft Defender for Cloud Authentication Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-microsoft-defender-endpoint-connector","lastmod":"2026-08-18T01:52Z","nid":"1530975"} -->
## Configuring the Microsoft Defender for Endpoint Connector

- Source: https://help.zscaler.com/uvm/configuring-microsoft-defender-endpoint-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Microsoft Defender for Endpoint Connector
- Last modified: 2026-08-18T01:52Z
- Summary: How to configure the Microsoft Defender for Endpoint connector, including setting up authentication and data retrieval filters and specifications.

Microsoft Defender for Endpoint is an enterprise-grade endpoint security platform that helps detect, prevent, investigate, and respond to advanced cyber threats. It provides integrated threat protection across devices using behavioral monitoring, automated analysis, and real-time security insights.

There are 5 Microsoft Defender for Endpoint streams. Select those that are based on your Microsoft Defender for Endpoint feature plan and use cases:

- Microsoft Defender for Endpoint Assets: Retrieves device information, including device details such as ID and owner, operating system, and network information.
- Microsoft Defender for Endpoint Vulnerabilities: Retrieves a list of all the vulnerabilities affecting the organization per machine and software.
- Microsoft Defender for Endpoint Software Vulnerabilities by Machine: Retrieves detailed software vulnerabilities for individual machines across all platforms and environments.
- Microsoft Defender for Endpoint Alerts: Retrieves security alerts, including details about the alert, impacted resources, severity, detection source, recommendations, and timestamps for incidents or vulnerabilities.
- Microsoft Defender for Endpoint Incidents: Retrieves security incidents, including incident details, affected resources, severity, status, related alerts, and timelines.

These streams retrieve endpoint data only.

For organizations managing large volumes of data, vulnerabilities can also be retrieved using Microsoft's Export API. This method delivers the same data as the Vulnerabilities stream but in a file-based format designed for efficient bulk retrieval. To request access to the Export API, contact your Zscaler Account team or Zscaler Support. To learn more about the Export API, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/defender-endpoint/api/get-assessment-software-vulnerabilities).

For the Microsoft Defender for Cloud connector, see [Configuring the Microsoft Defender for Cloud Connectors](https://help.zscaler.com/uvm/configuring-microsoft-defender-cloud-connectors).

To learn more, see [Zscaler and Microsoft Defender Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-defender-deployment-guide).

## Prerequisites

To configure the Microsoft Defender for Endpoint connector, you need to configure the appropriate roles and permissions. You also need the source authentication configuration. The following parameters are required:

- Application (client) ID and Directory (tenant) ID
- Client Secret
- URL
- Assign the app permissions

## Configuring the Connector

To create the Microsoft Defender for Endpoint data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Microsoft Defender for Endpoint data source, enter the following information in the source setup Retrieval section:

- Authentication
- Severity
- Ignore Resolved Incidents

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

In the Microsoft Azure portal, complete the app registration process. To learn more, see [Zscaler and Microsoft Defender Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-defender-deployment-guide).

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

On the API permission tab of the app registration process, select the appropriate permissions for the Microsoft Defender for Endpoint stream you want to set up:

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/) field**, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, select your app registration.
4. From the left-side navigation, from the **Manage** drop-down menu, select **API permissions**.
5. On the **API permissions** page, select **Add a permission**.
6. In the panel, select the **APIs my organization uses** tab.
7. Depending on the stream you are connecting to, search for the following relevant API:
  - Incidents stream: Select the Microsoft Threat Protection API.
  - For all other streams (i.e., Vulnerabilities, Alerts, Assets, or Software Vulnerabilities by Machine): Select the WindowsDefenderATP API.
8. Select either **Application** or **Delegated**. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/permissions-consent-overview).
9. Add the permissions per stream:
  - Assets
  - Vulnerabilities
  - Software Vulnerabilities by Machine
  - Alerts
  - Incidents
10. Click **Grant consent** for each permission you add.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Microsoft Defender for Endpoint Authentication** window appears.
2. In the **Microsoft Defender for Endpoint Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

You can filter the ingested data by severity.

This field is available in the Microsoft Defender for Endpoint Vulnerabilities stream.

You can filter out resolved incidents.

This checkbox is available in the Microsoft Defender for Endpoint Incidents stream.

[Image: The Microsoft Defender for Endpoints tiles]

[Image: The Microsoft Defender for Endpoint Authentication window displaying the Name, Client Id, Client Secret, Tenant Id, and Url fields in the SecOps platform]

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

Select the WindowsDefenderATP API and add the following permissions:

| **Permission Type** | **Permission** | **Permission Display Name** |
| --- | --- | --- |
| Application | Machine.Read.All | 'Read all machine profiles' |
| Application | Machine.ReadWrite.All | 'Read and write all machine information' |
| Delegated (work or school account) | Machine.Read | 'Read machine information' |
| Delegated (work or school account) | Machine.ReadWrite | 'Read and write machine information' |

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

Select the Microsoft Threat Protection API and add the following permissions:

| **Permission Type** | **Permission** | **Permission Display Name** |
| --- | --- | --- |
| Application | Incidents.Read.All | 'Read all incidents' |
| Application | Incident.ReadWrite.All | 'Read and write all incidents' |
| Delegated (work or school account) | Incident.Read | 'Read incidents' |
| Delegated (work or school account) | Incident.ReadWrite | 'Read and write incidents' |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-microsoft-entra-id-connector","lastmod":"2026-08-18T01:53Z","nid":"1532685"} -->
## Configuring the Microsoft Entra ID Connector

- Source: https://help.zscaler.com/uvm/configuring-microsoft-entra-id-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Microsoft Entra ID Connector
- Last modified: 2026-08-18T01:53Z
- Summary: How to configure the Microsoft Entra ID connector, including setting up authentication and data retrieval filters and specifications.

The Microsoft Entra ID connector is in limited availability. To learn more, contact Zscaler Support.

The Microsoft Entra ID connector integrates with your Microsoft Entra ID tenant to retrieve crucial identity and device data. This enables you to gain comprehensive visibility into user activities, group memberships, and device states, facilitating enhanced threat detection, compliance monitoring, and incident response within your cloud environments.

There are 4 Entra ID streams. Select those that are based on your Entra ID feature plan and use cases:

- Entra ID - Administrative Units: Retrieves all administrative units (e.g., name and description) and the members assigned to each unit.
- Entra ID - Applications: Retrieves all applications registered in your Entra ID tenant (e.g., display name and app ID) and the owners for each application.
- Entra ID - Devices: Retrieves all registered devices (e.g., device ID, name, OS, compliance and management fields) and the registered owners for each device.
- Entra ID - Users & Groups: Retrieves a list of all Entra ID users (core profile attributes) and the groups each user is a member of.

## Prerequisites

To configure the Entra ID connector, you need the source authentication configuration. The following parameters are required:

- Client ID
- Client Secret
- Tenant ID

To allow the connector to retrieve the data, apply the following Microsoft Graph API permissions to the Entra ID application registration:

| Stream | Permission Type | Permission | Permission Display Name |
| --- | --- | --- | --- |
| Entra ID - Administrative Units | Application | AdministrativeUnit.Read.All | 'Read all administrative units' |
| Entra ID - Applications | Application | Application.Read.All | 'Read all applications' |
| Entra ID - Devices | Application | Device.Read.All | 'Read all devices' |
| Entra ID - Users & Groups | Application | User.Read.All | 'Read all users' full profiles' |
| Application | Group.Read.All | 'Read all groups' |  |

## Configuring the Connector

To create the Entra ID data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Entra ID data source, enter the authentication information in the source setup Retrieval section:

1. In the **Authentication** section, click **Create New**. The **Microsoft Entra ID Authentication** window appears.
2. In the **Microsoft Entra ID Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

You can locate the client ID on the Overview page. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/healthcare-apis/register-application).

You can create a client secret on the App registrations > Certificates & secrets page. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/how-to-add-credentials?tabs=client-secret).

You can locate the tenant ID on the Properties tab of the Overview page. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/fundamentals/how-to-find-tenant).

[Image: Microsoft Entra ID connector tiles]

[Image: The Microsoft Entra ID Authentication window displaying the Name, Client Id, Client Secret, and Tenant Id fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-microsoft-intune-assets-connector","lastmod":"2026-03-26T12:22Z","nid":"1530992"} -->
## Configuring the Microsoft Intune Assets Connector

- Source: https://help.zscaler.com/uvm/configuring-microsoft-intune-assets-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Microsoft Intune Assets Connector
- Last modified: 2026-03-26T12:22Z
- Summary: How to configure the Microsoft Intune Assets connector, including setting up authentication and data retrieval filters and specifications.

Microsoft Intune is a cloud-based endpoint management solution. It manages user access to organizational resources and simplifies app and device management across your devices, including mobile devices, desktop computers, and virtual endpoints.

This stream retrieves Microsoft Intune devices that are mapped to assets in the system.

For the Microsoft Intune Audit Events connector, see [Configuring the Microsoft Intune Audit Events Connector](https://help.zscaler.com/uvm/configuring-microsoft-intune-audit-events-connector).

## Prerequisites

Before getting started, ensure that your tenant has an active Microsoft Intune subscription. If it does not, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/intune/intune-service/fundamentals/licenses-assign) to activate it.

If you already have a SecOps app for API, assign the application roles. This does not apply to the SAML application. To set up the Microsoft Intune Assets connector:

- Register an application
- Declare roles for an application
- Assign app roles

To configure the Microsoft Intune Assets connector, you need the source authentication configuration. The following parameters are required:

- Directory (tenant) ID and Application (client) ID
- Client Secret

## Configuring the Connector

To create the Microsoft Intune Assets data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Microsoft Intune Assets data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Microsoft Intune Authentication** window appears.
2. In the **Microsoft Intune Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Troubleshooting and FAQs

If you are still getting an authorization error after following the steps, assign the role app permissions through the Microsoft APIs tab.

- View instructions.

1. Log in to the Microsoft Azure portal.
2. On the home page, in the **Search resources, services, and docs (G+/)** field, enter `App registrations`. From the drop-down menu, select **App registrations**.
3. On the **App registrations** page, select your app registration.
4. From the left-side navigation, from the **Manage** drop-down menu, select **API permissions**.
5. On the **API permissions** tab, click **Add a permission**.
  - On the **Microsoft APIs** tab, click the **Microsoft Graph** tile.
  - Select **Application permissions**.
  - Select **DeviceManagementManagedDevices.Read.All**.
6. Click **Add permissions**.
7. To grant admin consent, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-add-app-roles-in-apps#grant-admin-consent).

After completing the steps, rerun the connector in the platform. Hover over the Microsoft Intune Assets data source and click **Process Now**.

After the registration process is complete, the Microsoft Azure portal displays the app registration **Overview** page. From the **Overview** page, save the **Application (client) ID** and **Directory (tenant) ID**.

To add a client secret, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/quickstart-register-app#add-a-client-secret). When you are asked to select an expiration date, select **24 months**.

Save the client secret value. The client secret is not displayed again after you leave this page.

[Image: The Microsoft Intune Assets tile]

[Image: The Microsoft Intune Authentication window displaying the Name, Client Id, Client Secret, and Tenant ID fields in the SecOps platform]

Before you create a new app role, ensure that you sign in to the Microsoft Entra admin center as a Cloud Application Administrator or greater admin role. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity/role-based-access-control/permissions-reference#cloud-application-administrator).

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

To declare roles for an application, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-add-app-roles-in-apps#declare-roles-for-an-application).

In the **Create app role** window, configure the following:

- **Display name**: Enter a name.
- **Allowed member types**: Select **Applications**.
- **Value**: Enter `DeviceManagementManagedDevices.Read.All`.
- **Description**: Enter a description.
- **Do you want to enable this app role?**: Select the checkbox.

To assign app roles to applications, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-add-app-roles-in-apps#assign-app-roles-to-applications).

- For step 6, if your application is not found on the **My APIs** tab, it might be located on the **APIs my organization uses** tab.
- For step 7, select the **Avalor - read devices** role.

To grant admin consent, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-add-app-roles-in-apps#grant-admin-consent).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-microsoft-intune-audit-events-connector","lastmod":"2026-03-26T12:22Z","nid":"1531006"} -->
## Configuring the Microsoft Intune Audit Events Connector

- Source: https://help.zscaler.com/uvm/configuring-microsoft-intune-audit-events-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Microsoft Intune Audit Events Connector
- Last modified: 2026-03-26T12:22Z
- Summary: How to configure the Microsoft Intune Audit Events connector, including setting up authentication and data retrieval filters and specifications.

Microsoft Intune is a cloud-based endpoint management solution. It manages user access to organizational resources and simplifies app and device management across your devices, including mobile devices, desktop computers, and virtual endpoints.

For Microsoft Intune Assets, see [Configuring the Microsoft Intune Assets Connector](https://help.zscaler.com/uvm/configuring-microsoft-intune-assets-connector).

## Prerequisites

To set up the Microsoft Intune Audit Events connector, you must register an application in Microsoft Intune. During the setup process, you can retrieve the required parameters. The Microsoft Graph API for Intune requires an active Intune feature plan for the tenant. If you already have a SecOps application for API, you can assign the application roles. This does not apply to the SAML application.

- Register an application
- Declare roles for an application
- Assign application roles

To configure the Microsoft Intune Audit Events connector, you need the source authentication configuration. The following parameters are required:

- Directory (tenant) ID and Application (client) ID
- Client Secret

## Configuring the Connector

To create the Microsoft Intune Audit Events data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Microsoft Intune Audit Events data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Microsoft Intune Authentication** window appears.
2. In the **Microsoft Intune Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

After the registration process is complete, the Microsoft Azure portal displays the application registration **Overview** page. From the **Overview** page, save the **Application (client) ID** and **Directory (tenant) ID**.

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

[Image: The Microsoft Intune Audit Events tile]

[Image: The Microsoft Intune Authentication window displaying the Name, Client Id, Client Secret, and Tenant Id fields]

To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

To declare roles for an application, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-add-app-roles-in-apps#declare-roles-for-an-application). To learn more, see [Zscaler UVM and Microsoft Services Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-microsoft-services-deployment-guide).

To assign app roles to applications, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-add-app-roles-in-apps#assign-app-roles-to-applications). For the Permissions step, select the `intune read` role.

To grant admin consent, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-add-app-roles-in-apps#grant-admin-consent).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-netbox-connector","lastmod":"2026-07-31T07:06Z","nid":"1528006"} -->
## Configuring the NetBox Connector

- Source: https://help.zscaler.com/uvm/configuring-netbox-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the NetBox Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the NetBox connector, including setting up authentication and data retrieval filters and specifications.

NetBox is an infrastructure resource modeling (IRM) platform that supports network automation and management, including IP address management (IPAM), device inventory, and virtual machine and cluster management.

There are three NetBox streams. Select those that are based on your NetBox feature plan and use cases:

- NetBox Devices: Retrieves a detailed inventory of physical and virtual network devices, including hardware types, roles, installation sites, and current operational status.
- NetBox IP Addresses: Retrieves IPAM data, including networks, VRFs, VLANs, assignment status, and associations with specific devices.
- NetBox Virtual Machines: Retrieves detailed virtualized infrastructure data, including virtual machine configurations, cluster associations, and resource allocations like CPU and memory.

## Prerequisites

To configure the NetBox connector, you need the source authentication configuration. The following parameters are required:

- Roles and Permissions
- API Token
- Netbox URL

## Configuring the Connector

To create the NetBox data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the NetBox data source, enter the authentication information in the source setup Retrieval section.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **NetBox Authentication** window appears.
2. In the **NetBox Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

The user account associated with the API token must carry at least the permissions listed in the following table. While any NetBox user with the ability to generate an API token carries these permissions by default, admins can create a dedicated user to manage the token with more granular control.

The following table outlines the minimally required permissions needed for the user with which the API token is associated:

| **Stream** | **Permissions** |
| --- | --- |
| NetBox Devices | `DCIM > Device` |
| NetBox IP Addresses | `IPAM > IP Address` |
| NetBox Virtual Machines | `Virtualization > Virtual Machine` |

You must be a NetBox admin to create an API token.

To create an API token:

1. In the NetBox platform, select one of the following: The **Tokens**page appears.
  - Click the **Profile**menu on the top right, and click **API Tokens**. See image.
  - From the left-side navigation, go to **Admin** > **API Tokens**. See image.
2. On the **Tokens** page, click **Add**. The **Add a new token**page appears.
3. On the**Add a new token**page: See image.
  1. **User**: If you created a dedicated user with restricted permissions, select the user. If you did not create a dedicated user with restricted permissions, leave your user selected. When selecting a user, make sure the selected user carries at least the minimally required permissions for the data streams you plan to ingest.
  2. **Write enabled**: Deselect the checkbox.
  3. **Expires**: (Optional) Enter an expiration date for the token. If an expiration date is set, make sure to refresh the key when it expires to ensure continuous connectivity.
  4. **Description**: (Optional) Enter a description.
  5. **Allowed IPs**: (Optional) Enter the list of the [Zscaler SecOps allowed IP addresses](https://help.zscaler.com/uvm/zscaler-secops-public-ip-addresses) to restrict the use of this key to API requests originating from the specified IP addresses.
4. Click **Create**.

The NetBox URL is your specific NetBox instance which can be found in your management console URL in the format `https://``<Your Instance>``.cloud.netboxapp.com`. For example, `https://acme78.cloud.netboxapp.com`.

See image.

[Image: NetBox tiles]

[Image: NetBox Profile menu API Tokens]

[Image: NetBox left-side navigation API Tokens]

[Image: NetBox Create an API Token]

[Image: The NetBox URL in the NetBox platform]

[Image: NetBox Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-prisma-cloud-connector","lastmod":"2026-03-26T12:25Z","nid":"1531035"} -->
## Configuring the Prisma Cloud Connector

- Source: https://help.zscaler.com/uvm/configuring-prisma-cloud-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Prisma Cloud Connector
- Last modified: 2026-03-26T12:25Z
- Summary: How to configure the Prisma Cloud connector, including setting up authentication and data retrieval filters and specifications.

Prisma Cloud is a cloud-native application protection platform (CNAPP) supplying security and compliance coverage for infrastructure, workloads, and applications across the entire cloud-native technology stack throughout the development lifecycle.

There are two Prisma Cloud streams. Select those that are based on your Prisma Cloud feature plan and use cases:

- Container Issues: Retrieves container scan compliance reports.
- All Findings: Retrieves deployed scan vulnerability reports.

For the Prisma Cloud CSPM connector, see [Configuring the Prisma Cloud CSPM Connector](https://help.zscaler.com/uvm/configuring-prisma-cloud-cspm-connector).

## Prerequisites

To configure the Prisma Cloud connector, you need the source authentication configuration. The following parameters are required:

- Access Key and Secret Key
- Server URL

## Configuring the Connector

To create the Prisma Cloud data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources.' See image.

### Configuring Retrieval Filters and Specifications

After you create the Prisma Cloud data source, enter the following information in the source setup Retrieval section:

- Authentication
- Scan Types

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

By default, only system admins can create access keys and secret keys. However, system admins can add users that have permissions to create keys.

To create a user:

1. Log in to the Prisma Cloud platform as a system admin.
2. Go to **Settings** > **Access Control** > **Roles** > **Add** > **Role**.
3. Create a new reader role with the following configurations:
  - **Permission Group**: Select **Account Group Read Only**.
  - **Account Group**: Select the account groups for scanning.
4. Click**View Permissions** and select **On-prem/Other cloud providers**.
5. In the **Access Control** section, go to **Users** > **Add a new user**.
  - Enter a name for your user.
  - In the **Assign Roles and Default role** section, select the role you created.
6. To enable the user to create access keys and secret keys later, select the **Allow user to create API Access Keys** checkbox.

To create the access key and secret key:

1. Log in to the Prisma Cloud platform.
2. Go to **Settings** > **Manage** > **Access Control** > **Access Keys**.
3. From the top-right menu, go to **Add** > **Access Key**. If the option to add a new key is unavailable, create a user with the proper permissions.
4. Enter a name for your key.
5. Set the key expiration date: If you do not select **Enable Expiration**, the key never expires. If **Enable Expiration** is selected, but no expiration date is specified, the key expires within a month.
  1. Select **Enable Expiration**.
  2. Specify the expiration date and time.
6. Click **Save** to create the key. You can either copy the key or download it as a CSV file. You do not have access to the key after you close the window.

To retrieve the server URL:

1. Log in to the Prisma Cloud platform.
2. Go to **Runtime Security** > **Manage** > **System**.
3. Select the **Utilities** tab.
4. The **Server URL** appears in the **Path to Console** section. When entering the **Server URL** for authentication, use the following format: `https://``<region>``.cloud.twistlock.com/``<customer>`. If authentication with the copied URL fails, append `/api/v1` to the end of the URL and try again.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Prisma Authentication** window appears.
2. In the **Prisma Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Set the Scan Types filters and specifications (i.e., **Images**, **CI Images**, **Registry**, **Host**, and **Serverless Function**).

This menu is available in the Prisma Cloud All Findings stream.

[Image: The Prisma Cloud - Container Issues and Prisma Cloud - All Findings tiles]

[Image: The Prism Authentication window displaying the Name, Access Key, Secret Key, and Server Url fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-prisma-cloud-cspm-connector","lastmod":"2026-03-26T12:25Z","nid":"1531040"} -->
## Configuring the Prisma Cloud CSPM Connector

- Source: https://help.zscaler.com/uvm/configuring-prisma-cloud-cspm-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Prisma Cloud CSPM Connector
- Last modified: 2026-03-26T12:25Z
- Summary: How to configure the Prisma Cloud CSPM connector, including setting up authentication and data retrieval filters and specifications.

Prisma Cloud is a cloud-native application protection platform (CNAPP) supplying security and compliance coverage for infrastructure, workloads, and applications across the entire cloud-native technology stack throughout the development lifecycle.

The Prisma Cloud CSPM connector retrieves vulnerabilities and asset data on cloud resources.

For the Prisma Cloud connector, see [Configuring the Prisma Cloud Connector](https://help.zscaler.com/uvm/configuring-prisma-cloud-connectors).

## Prerequisites

To configure the Prisma Cloud CSPM connector, you need the source authentication configuration. The following parameters are required:

- Access Key and Secret Key
- Server URL

## Configuring the Connector

To create the Prisma Cloud CSPM data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Prisma Cloud CSPM data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Prisma Authentication** window appears.
2. In the **Prisma Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

By default, only system admins can create access keys and secret keys. However, system admins can add users that have permissions to create keys.

To create a user:

1. Log in to the Prisma Cloud platform as a system admin.
2. Go to **Settings** > **Access Control** > **Roles** > **Add** > **Role**.
3. Create a new reader role with the following configurations:
  - **Permission Group**: Select **Account Group Read Only**.
  - **Account Group**: Select the account groups for scanning.
4. Click**View Permissions** and select **On-prem/Other cloud providers**.
5. In the **Access Control** section, go to **Users** > **Add a new user**.
  - Enter a name for your user.
  - In the **Assign Roles and Default role** section, select the role you created.
6. To enable the user to create access keys and secret keys later, select the **Allow user to create API Access Keys** checkbox.

To create the access key and secret key:

1. Log in to the Prisma Cloud platform.
2. Go to **Settings** > **Manage** > **Access Control** > **Access Keys**.
3. From the top-right menu, go to **Add** > **Access Key**. If the option to add a new key is unavailable, create a user with the proper permissions.
4. Enter a name for your key.
5. Set the key expiration date: If you do not select **Enable Expiration**, the key never expires. If **Enable Expiration** is selected, but no expiration date is specified, the key expires within a month.
  1. Select **Enable Expiration**.
  2. Specify the expiration date and time.
6. Click **Save** to create the key. You can either copy the key or download it as a CSV file. You do not have access to the key after you close the window.

Your Prisma Cloud CSPM admin console URL varies according to the cluster in which your tenant is deployed. The correct URL is in your fulfillment email from Prisma Cloud CSPM. After you retrieve your admin console URL, convert it to the matching API URL. To learn more, refer to the [Prisma Cloud documentation](https://pan.dev/prisma-cloud/api/cspm/api-urls/).

[Image: The Prisma Cloud CSPM tile]

[Image: The Prisma Authentication window displaying the Name, Access Key, Secret Key, and Server Url fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-qualys-vmdr-pc-connector","lastmod":"2026-07-05T07:06Z","nid":"1530768"} -->
## Configuring the Qualys VMDR & PC Connector

- Source: https://help.zscaler.com/uvm/configuring-qualys-vmdr-pc-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Qualys VMDR & PC Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Qualys VMDR & PC connector, including setting up authentication and data retrieval filters and specifications.

Qualys Vulnerability Management, Detection, and Response (VMDR) and Policy Compliance (PC) modules are cloud-based solutions that detect vulnerabilities on all networked assets, including servers, network devices, peripherals, and workstations.

There are three Qualys VMDR & PC streams. Select those that are based on your Qualys VMDR & PC feature plan and use cases:

- Qualys Assets: Retrieves hosts data, including IP addresses, DNS names, serial numbers, operating systems, and tags.
- Qualys Vulnerabilities: Retrieves vulnerabilities from the vulnerabilities and potential vulnerabilities categories.
- Qualys Policy Compliance: Retrieves compliance posture info records, which includes the compliance posture ID.

These streams integrate with the Qualys VMDR & PC modules.

For the Qualys WAS connector, see [Configuring the Qualys WAS Connector](https://help.zscaler.com/uvm/configuring-qualys-was-connectors).

To learn more, see [Zscaler UVM and Qualys Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-qualys-deployment-guide).

## Prerequisites

To configure the Qualys VMDR & PC connector, you need to configure the appropriate roles and permissions. You also need the source authentication configuration. The following parameters are required:

- Platform URL
- Username and Password

## Configuring the Connector

To create the Qualys VMDR & PC data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Qualys VMDR & PC data source, enter the following information in the source setup Retrieval section:

- Authentication
- Show ARF Data
- Exclude Kernel Related Vulnerabilities
- Exclude Service Related Vulnerabilities
- Exclude Config Related Vulnerabilities
- Vulnerability Status
- Vulnerability Severity
- Filter Out Superseded QIDs
- Enable port-level granularity
- Retrieve Information Gathered Findings (higher volume)
- Asset tag set to exclude
- Asset tag set to include

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Troubleshooting and FAQs

If you created a user and assigned the specified permissions, but your connector fails, ensure that you have completed the user activation process. This includes logging into the Qualys console using the new user's credentials.

Your username includes your platform identifier. For example, in the username format `quays_ab1`, the underscore is the platform identifier for the US1 platform.

For a list of identifiers and their matching platform URL, refer to the [Qualys documentation](https://www.qualys.com/platform-identification/).

- For Qualys Assets and Qualys Vulnerabilities, use the API server URL.
- For Qualys Policy Compliance, use the API gateway URL.

To learn more, see [Zscaler UVM and Qualys Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-qualys-deployment-guide).

Your Qualys user credentials grant access to your Qualys data. The user credentials you provide must have a set of permissions for the connection to be successful.

Ensure that you grant the following access permissions to the user:

- For Qualys Vulnerabilities and Qualys Assets, select the **Manage VM module** checkbox.
- For Qualys Policy Compliance, select the **Manage PC module** checkbox.

To learn more, refer to the [Qualys documentation](https://docs.qualys.com/en/vm/latest/user_accounts/admin_ui.htm).

Zscaler recommends that you create a new user with the Scanner role for this purpose. Alternatively, you can use an existing user or create a custom role, as long as they have the proper permissions. To learn more about creating a user, refer to the [Qualys documentation](https://docs.qualys.com/en/cloudview/latest/users/create_user.htm).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Qualys Authentication** window appears.
2. In the **Qualys Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select this checkbox to exclude vulnerabilities found on inactive kernels.

This checkbox is available in the Qualys Vulnerabilities stream.

Select this checkbox to exclude vulnerabilities found on inactive ports and services.

This checkbox is available in the Qualys Vulnerabilities stream.

Select this checkbox to exclude vulnerabilities that are not exploitable due to the host configuration.

This checkbox is available in the Qualys Vulnerabilities stream.

Select this checkbox to include Asset Reporting Format (ARF) data in your vulnerability reports or detection results.

This checkbox is available in the Qualys Vulnerabilities stream.

Select the vulnerability status types from the Vulnerability Status drop-down menu to include in the scope of your data retrieval (i.e., New, Active, Re-Opened, or Fixed). To learn more, see [Zscaler UVM and Qualys Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-qualys-deployment-guide).

This menu is available in the Qualys Vulnerabilities stream.

From the drop-down menu, select a severity level. Qualys classifies vulnerability severities from 1 to 5 based on the potential impact of the vulnerability and the difficulty of exploitation.

- 1: The vulnerability is minimal, or very low. This includes information gathering (e.g., service banners or open ports).
- 2: The vulnerability is medium, such as limited sensitive information disclosure. This includes low risk vulnerabilities (e.g., weak SSL ciphers or directory listing).
- 3: The vulnerability is high, such as unauthorized access to specific data or services. This includes serious vulnerabilities (e.g., default passwords or CSRF).
- 4: The vulnerability is critical, such as full system compromise or significant data theft. This includes very serious vulnerabilities (e.g., Remote Code Execution or SQL Injection).
- 5: The vulnerability is urgent, such as immediate total system takeover likely being actively exploited in the wild. This includes the highest risk vulnerabilities (e.g., "Wormable" exploits like EternalBlue).

This drop-down menu is available in the Qualys Vulnerabilities stream.

Select this checkbox to have the latest security updates supersede previous security updates. This is common with Microsoft Windows OS patches.

This checkbox is available in the Qualys Vulnerabilities stream.

If the vulnerability is recorded on more than one port, only the first port with the vulnerability is processed. Select this checkbox to save all ports with the same vulnerability in the data.

This checkbox is available in the Qualys Vulnerabilities stream.

Select this checkbox to allow the retrieval of information-gathered findings.

This checkbox is available in the Qualys Vulnerabilities stream.

You can enter custom asset tag names in the Asset tag set to exclude field to exclude specific groups of assets from the scope of your data retrieval. Separate multiple values with commas.

When using this filter, you must also specify the scope of assets to be retrieved in the include filter.

This field is available in the Qualys Vulnerabilities and Qualys Assets streams.

You can enter custom asset tag names in the Asset tag set to include field to include specific groups of assets from the scope of your data retrieval. Separate multiple values with commas.

When using this filter, you must also specify the scope of assets to be retrieved in the exclude filter.

This field is available in the Qualys Vulnerabilities and Qualys Assets streams.

[Image: The Qualys Assets, Qualys Vulnerabilities, and Qualys Policy Compliance tiles in the SecOps platform]

[Image: The Qualys Authentication window displaying the Name, User Name, Password, and Url fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-qualys-was-connector","lastmod":"2026-03-26T12:25Z","nid":"1530818"} -->
## Configuring the Qualys WAS Connector

- Source: https://help.zscaler.com/uvm/configuring-qualys-was-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Qualys WAS Connector
- Last modified: 2026-03-26T12:25Z
- Summary: How to configure the Qualys WAS connector, including setting up authentication and data retrieval filters and specifications.

Qualys Web Application Scanning (WAS) is an automated web application scanner that uses fault injection tests to identify vulnerabilities by injecting specially crafted inputs and analyzing application responses.

There are two available Qualys WAS streams. Select those that are based on your Qualys WAS feature plan and use cases:

- Qualys Assets: Retrieves the list of web applications within the user's scope.
- Qualys Vulnerabilities: Retrieves a list of findings, including vulnerabilities, sensitive content, and gathered information associated with web applications within the user's scope.

These connectors integrate with the Qualys WAS module.

For Qualys VMDR & PC, see [Configuring the Qualys VMDR & PC Connector](https://help.zscaler.com/uvm/configuring-qualys-vmdr-pc-connectors).

## Prerequisites

To configure the Qualys WAS connector, you need to configure the appropriate roles and permissions. You also need the source authentication configuration. The following parameters are required:

- Username and Password
- Platform URL

## Configuring the Connector

To create the Qualys WAS data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Qualys WAS data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Qualys Authentication** window appears.
2. In the **Qualys Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Troubleshooting and FAQs

If you created a user and assigned the specified permissions, but your connector fails, ensure that you have completed the user activation process. This includes logging into the Qualys console using the new user's credentials.

You need the email and password for a Qualys user with access to the Qualys WAS module.

To create an API reader user:

1. In Qualys WAS, go to **Administration** > **User Management**.
2. From the **Create User** drop-down menu, select **Create Reader User**. The **New Reader User** window appears.
3. In the **New Reader User** window, configure the following:
  - On the **General Information** and **Locale** tabs, enter your information as required.
  - On the **User Role** tab, enter the following information:
    - **User Role**: From the drop-down menu, select **Reader**.
    - **Allow access to**: Select the **API** checkbox.
    - **Business Unit**: Select your information as required.
  - On the **Asset Groups** tab, select the relevant asset groups you want to retrieve. Only assets from the selected asset groups are ingested into the platform.
  - On the **Permissions** tab, select the **Manage VM module** and **Manage web applications** checkboxes.
  - On the **Options** and **Security** tabs, enter your information as required.
4. Click **Save**.

After you create the user, edit the user and grant it full access to the WAS module.

If you created a new user for this connector, activate the user's account by completing the user registration process. This includes checking the email associated with the new user account for a message titled `Registration - Start Now`. Follow the instructions in this email, which guides you through the activation process and provides the login information, including the platform URL and login credentials.

The activation process includes logging in to the Qualys console.

Your username includes your platform identifier. For example, in the username format `quays_ab1`, the underscore is the platform identifier for the US1 platform.

For a list of identifiers and their matching platform URL, refer to the [Qualys documentation](https://www.qualys.com/platform-identification/).

[Image: The Qualys WAS Assets and Qualys WAS Vulnerabilities connector tiles]

[Image: The Qualys Authentication window displaying the Name, User Name, Password, and Url fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-quokka-connector","lastmod":"2026-04-07T07:06Z","nid":"1534082"} -->
## Configuring the Quokka Connector

- Source: https://help.zscaler.com/uvm/configuring-quokka-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Quokka Connector
- Last modified: 2026-04-07T07:06Z
- Summary: How to configure the Quokka connector, including setting up authentication and data retrieval filters and specifications.

Quokka is a mobile security and privacy solution. The Quokka stream retrieves findings data.

## Prerequisites

To configure the Quokka connector, you need the source authentication configuration. The API key parameter is required. To enable and generate the API key:

- Enable the API Key
- Generate the API Key

1. Log in to the Quokka platform as an admin.
2. From the upper right of the page, click the account name and go to the account settings page.
3. Select **Group Admin**.
4. Create a new user, or click the user you want to edit.
5. In the **API Key** section, click **Enable**. See image.
6. Assign the following permissions to the user: See image.
  - View Analyzed Apps
  - View Reports
  - View Shared Apps
  - Change API Key
  - Android Analysis
  - iOS Analysis
  - GDPR Report
  - OWASP Report
  - NIAP Report
  - SBOM Report
7. Save the user.

From the user settings page, you can generate an API key as a group admin. If you are not an admin:

1. Log in to the Quokka platform.
2. From the upper right of the page, click the account name and go to the account settings page.
3. On the **Security** tab, click **Generate New API Key** or use the existing key.

See image.

## Configuring the Connector

To create the Quokka data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Quokka data source, enter the Authentication information:

1. In the **Authentication** section, click **Create New**. The **Quokka Authentication** window appears.
2. In the **Quokka Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

[Image: Quokka connector tile]

[Image: The Quokka Authentication window accessed from the Configure > Sources pages of the SecOps platform]

[Image: Enabling the API key in the Quokka platform]

[Image: Selecting permissions to assign to the user in the Quokka platform]

[Image: Generating a new API key in the Quokka platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-rapid7-insightvm-connector","lastmod":"2026-07-05T07:06Z","nid":"1530820"} -->
## Configuring the Rapid7 InsightVM Connector

- Source: https://help.zscaler.com/uvm/configuring-rapid7-insightvm-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Rapid7 InsightVM Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Rapid7 InsightVM connector, including setting up authentication and data retrieval filters and specifications.

Rapid7 InsightVM is a vulnerability management solution that scans modern environments, aggregates data from multiple sources, and prioritizes remediation to strengthen security programs.

There are three Rapid7 InsightVM streams. Select those that are based on your Rapid7 InsightVM feature plan and use cases:

- Rapid7 InsightVM Vulnerabilities: Retrieves all vulnerabilities found on the assets to which you have access.
- Rapid7 InsightVM Assets: Retrieves all assets to which you have access.
- Rapid7 InsightVM Policy Compliance: Retrieves policy compliance data. The Rapid7 InsightVM Policy Compliance stream is in limited availability. To learn more, contact Zscaler Support.

To learn more, see [Zscaler and Rapid7 Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-rapid7-deployment-guide).

## Prerequisites

To configure the Rapid7 InsightVM connector, you need the source authentication configuration. The following parameters are required:

- API Key
- Region

## Configuring the Connector

To create the Rapid7 InsightVM data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Rapid7 InsightVM data source, enter the following information in the source setup Retrieval section:

- Authentication
- Filter By Severity

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To retrieve vulnerability and asset data from Rapid7 InsightVM, a platform admin with at least View Only (Shared) privileges and View Site Asset Data and View Group Asset Data permissions must generate an API key. To learn more, refer to the [Rapid7 documentation](https://docs.rapid7.com/insightvm/managing-users-and-authentication/).

These permissions should be automatically set for platform admins.

This API key is associated with a user that has the appropriate permissions. Any user can generate a user key. A user key inherits your account permissions, so the API key inherits the access and permissions of the user who created it.

To create a user:

1. Log in to the Rapid7 platform.
2. Go to **Administration** > **User Management** > **Users**. See image.
3. Click **Create New User**. See image. The **Create User** page appears.
4. On the **Create User** page, configure the following:
  1. In the **User Details** section:
    - **Email Address**: Rapid7 requires a valid email address which the admin can access. The admin can use an email alias associated with their existing email address.
    - **First Name**: Enter a first name for the user.
    - **Last Name**: Enter a last name for the user.
    - **Timezone**: This field is automatically set to **UTC**. See image.
  2. In the **Platform Administrator Privileges** section, select the **Create this user as a Platform Administrator** checkbox. See image.
5. Click **Create User**.
6. On the **Users** page, in the **Users** table, click the user name of the user you created. See image.
7. On the user name page, in the **Individual Privileges** section, click **Manage Individual Privileges** to configure the following: See image.
  - **InsightVM**: Select the **InsightVM** checkbox.
  - **Roles**: From the drop-down menu, select the **View Only (Shared)** role.
8. Click **Save Individual Privileges**.

Sign in with this user to generate the API key.

To learn more, see [Zscaler and Rapid7 Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-rapid7-deployment-guide).

This is the region code for the cloud storage region that your organization is provisioned for in Rapid7. You can access your region from the URL of the Rapid7 InsightVM solution, which contains the region as a prefix. For example, the URL `us.idr.insight.rapid7.com` means that your data region is `us`. The region code is typically two letters and sometimes a number (e.g., `us`, `us1`, `eu`).

See image.

To learn more, see [Zscaler and Rapid7 Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-rapid7-deployment-guide).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Rapid7 InsightVM Authentication** window appears.
2. In the **Rapid7 InsightVM Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Set the Filter By Severity drop-down menu filters and specifications. This menu allows you to select the severity level of vulnerabilities to include in the scope of the ingested data (i.e., Critical, Low, Severe, Informational, None, Moderate).

This menu is available in the Rapid7 InsightVM Vulnerabilities stream.

[Image: The Rapid7 InsightVM tiles]

[Image: The Rapid7 InsightVM Authentication window displaying the Name, Api Key, and Region in the SecOps platform]

[Image: The region code located in the URL of the Rapid7 platform home page]

[Image: Selecting Users from the User Management drop-down menu in the Administration section of the Rapid7 platform]

[Image: Selecting Create New User on the Users page of the Rapid7 platform]

[Image: The User Details section of the Create User page in the Rapid7 platform]

[Image: Selecting Create this user as a Platform Administrator in the Platform Administrator Privileges section of the Create User page of the Rapid7 platform]

[Image: Selecting the User Name from the Users table in the Rapid7 platform]

[Image: Assigning the InsightVM and View Only (Shared) role to the user in the Rapid7 platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-runzero-assets-connector","lastmod":"2026-04-07T07:06Z","nid":"1534107"} -->
## Configuring the runZero Assets Connector

- Source: https://help.zscaler.com/uvm/configuring-runzero-assets-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the runZero Assets Connector
- Last modified: 2026-04-07T07:06Z
- Summary: How to configure the runZero Assets connector, including setting up authentication and data retrieval filters and specifications.

runZero is a Cyber Asset Attack Surface Management (CAASM) platform designed to provide security teams with comprehensive, unified visibility to proactively manage risk and exposure.

The runZero Assets stream retrieves the `Get/export/org/assets.jsonl` call and the Asset schema.

For the runZero Vulnerabilities stream, see [Configuring the runZero Vulnerabilities Connector](https://help.zscaler.com/uvm/configuring-runzero-vulnerabilities-connector).

## Prerequisites

To configure the runZero Assets connector, you need the source authentication configuration. The following parameters are required:

- Client ID and Client Secret
- Organization ID

## Configuring the Connector

To create the runZero Assets data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the runzero Assets data source, enter the authentication information in the source setup Retrieval section:

1. In the **Authentication** section, click **Create New**. The **Run Zero Authentication** window appears.
2. In the **Run Zero Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To retrieve the client ID and client secret:

1. Log in to the runZero platform.
2. From the left-side navigation, click the **Account** tab.
3. Go to **API Clients**.
4. Click **Register New API Client**.
5. After the registration process is complete, copy your client ID and client secret.

You can retrieve a list of all organization IDs for your runZero account from the runZero API. To retrieve your organization ID:

1. Log in to the runZero platform.
2. Go to the runZero Swagger API documentation.
3. Click **Authorize** and enter your client ID and secret in the **oauthDefaults (OAuth2, clientCredentials)** section.
4. Click **Authorize** to make the authentication call and receive an API bearer token.
5. In the GET /account/orgs API documentation section, click **Try it out**.
6. Click **Execute**. See image.
7. Identify the response and organization ID to use.

[Image: runZero Assets connector tile]

[Image: The Run Zero Authentication window accessed from Configure > Sources in the SecOps platform]

[Image: Clicking Execute in the runZero Swagger API documentation]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-runzero-vulnerabilities-connector","lastmod":"2026-04-07T07:06Z","nid":"1534108"} -->
## Configuring the runZero Vulnerabilities Connector

- Source: https://help.zscaler.com/uvm/configuring-runzero-vulnerabilities-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the runZero Vulnerabilities Connector
- Last modified: 2026-04-07T07:06Z
- Summary: How to configure the runZero Vulnerabilities connector, including setting up authentication and data retrieval filters and specifications.

runZero is a Cyber Asset Attack Surface Management (CAASM) platform designed to provide security teams with comprehensive, unified visibility to proactively manage risk and exposure.

The runZero Vulnerabilities stream retrieves the `Get/export/org/vulnerabilities.jsonl` call.

For the runZero Assets stream, see [Configuring the runZero Assets Connector](https://help.zscaler.com/uvm/runzero-vulnerabilities).

## Prerequisites

To configure the runZero Vulnerabilities connector, you need the source authentication configuration. The following parameters are required:

- Client ID and Client Secret
- Organization ID

## Configuring the Connector

To create the runZero Vulnerabilities data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the runZero Vulnerabilities data source, enter the authentication information in the source setup Retrieval section:

1. In the **Authentication** section, click **Create New**. The **Run Zero Vulnerabilities Authentication** window appears.
2. In the **Run Zero Vulnerabilities Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To retrieve the client ID and client secret:

1. Log in to the runZero platform.
2. From the left-side navigation, click the **Account** tab.
3. Go to **API Clients**.
4. Click **Register New API Client**.
5. After the registration process is complete, copy your client ID and client secret.

You can retrieve a list of all organization IDs for your runZero account from the runZero API. To retrieve your organization ID:

1. Log in to the runZero platform.
2. Go to the runZero Swagger API documentation.
3. Click **Authorize** and enter your client ID and secret in the **oauthDefaults (OAuth2, clientCredentials)** section.
4. Click **Authorize** to make the authentication call and receive an API bearer token.
5. In the GET /account/orgs API documentation section, click **Try it out**.
6. Click **Execute**. See image.
7. Identify the response and organization ID to use.

[Image: runZero Vulnerabilities connector tile]

[Image: The Run Zero Authentication window accessed from Configure > Sources in the SecOps platform]

[Image: Clicking Execute in the runZero Swagger API documentation]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-securityscorecard-alerts-connector","lastmod":"2026-04-07T07:06Z","nid":"1534149"} -->
## Configuring the SecurityScorecard Alerts Connector

- Source: https://help.zscaler.com/uvm/configuring-securityscorecard-alerts-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the SecurityScorecard Alerts Connector
- Last modified: 2026-04-07T07:06Z
- Summary: How to configure the SecurityScorecard Alerts connector, including setting up authentication and data retrieval filters and specifications.

SecurityScorecard is an information security company that rates cybersecurity postures of companies via scored analysis of cyber threat intelligence signals for third-party management and IT risk management. To learn more, see [Zscaler UVM and SecurityScorecard Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-securityscorecard-deployment-guide).

For the SecurityScorecard stream, see [Configuring the SecurityScorecard Connector](https://help.zscaler.com/uvm/configuring-securityscorecard-connector).

## Prerequisites

To configure the SecurityScorecard Alerts connector, you need the source authentication configuration. To learn more, see [Zscaler UVM and SecurityScorecard Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-securityscorecard-deployment-guide).

## Configuring the Connector

To create the SecurityScorecard Alerts data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the SecurityScorecard Alerts data source, enter the following information in the source setup Retrieval section:

- Authentication
- Username

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Security Scorecard Authentication** window appears.
2. In the **Security Scorecard Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

In the field, enter the username that receives the relevant notifications.

[Image: The Security Scorecard Alerts tile in the SecOps platform]

[Image: The Security Scorecard Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-securityscorecard-connector","lastmod":"2026-07-05T07:06Z","nid":"1534114"} -->
## Configuring the SecurityScorecard Connector

- Source: https://help.zscaler.com/uvm/configuring-securityscorecard-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the SecurityScorecard Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the SecurityScorecard connector, including setting up authentication and data retrieval filters and specifications.

SecurityScorecard is an information security company that rates the cybersecurity postures of companies via scored analysis of cyber threat intelligence signals for third-party management and IT risk management.

For the SecurityScorecard Alerts stream, see [Configuring the SecurityScorecard Alerts Connector](https://help.zscaler.com/uvm/configuring-securityscorecard-alerts-connector).

To learn more, see [Zscaler UVM and SecurityScorecard Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-securityscorecard-deployment-guide).

## Prerequisites

To configure the SecurityScorecard connector, you need the source authentication configuration.

## Configuring the Connector

To create the SecurityScorecard data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the SecurityScorecard data source, enter the following information in the source setup Retrieval section:

- Authentication
- Identifier
- Category

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Security Scorecard Authentication** window appears.
2. In the **Security Scorecard Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

To learn more, see [Zscaler UVM and SecurityScorecard Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-securityscorecard-deployment-guide).

To learn more, see [Zscaler UVM and SecurityScorecard Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-securityscorecard-deployment-guide).

[Image: The Security Scorecard tile in the SecOps platform]

[Image: The Security Scorecard Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-sentinelone-connector","lastmod":"2026-08-18T01:54Z","nid":"1528041"} -->
## Configuring the SentinelOne Connector

- Source: https://help.zscaler.com/uvm/configuring-sentinelone-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the SentinelOne Connector
- Last modified: 2026-08-18T01:54Z
- Summary: How to configure the SentinelOne connector, including setting up authentication and data retrieval filters and specifications.

SentinelOne provides a range of products and services through its Singularity XDR platform, offering comprehensive protection against cyber threats such as malware, ransomware, and advanced persistent threats (APTs).

There are 6 SentinelOne streams. Select those that are based on your SentinelOne feature plan and use cases:

- SentinelOne Threats: Retrieves threat-related data flagged as a threat in the Management Console's Threats section when SentinelOne agents detect suspicious or malicious activity.
- SentinelOne Vulnerabilities: Retrieves vulnerability information from SentinelOne application data.
- SentinelOne Assets: Retrieves different types of assets based on configuration, including endpoints such as managed devices, cloud assets, and unmanaged devices discovered on the network.
- SentinelOne Alerts: Retrieves security alerts from the SentinelOne Singularity platform, allowing for centralized security monitoring, threat analysis, and incident response in the Zscaler Security Operations (SecOps) platform.
- SentinelOne Agents: Retrieves detailed information about the SentinelOne agents installed on endpoints across the organization, providing visibility into agent status, version, network information, and OS details.
- SentinelOne CNS Findings: Retrieves vulnerabilities and cloud/IT misconfigurations configured in the SentinelOne Singularity platform's Extended Security Posture Management (xSPM) module.

The SentinelOne Assets, SentinelOne Alerts, and SentinelOne CNS Findings streams are in limited availability. To learn more, contact Zscaler Support.

To learn more, see [Zscaler and SentinelOne Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-sentinelone-deployment-guide).

## Prerequisites

To configure the SentinelOne connector, you need the source authentication configuration. The following parameters are required:

- Token
- Server URL

## Configuring the Connector

To create the SentinelOne data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the SentinelOne data source, enter the following information in the source setup Retrieval section:

- Authentication
- Alert Type
- Asset Type
- Findings Type
- Severity

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

To learn more, see [Zscaler and SentinelOne Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-sentinelone-deployment-guide).

To learn more, see [Zscaler and SentinelOne Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-sentinelone-deployment-guide).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **SentinelOne Authentication** window appears.
2. In the **SentinelOne Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select the scope of alerts to retrieve. EDR alerts originate from managed endpoints. XDR alerts extend beyond the endpoint, including detections from cloud workloads, identity providers, and other integrated security tools. If neither are selected, both types are ingested.

This menu is available in the SentinelOne Alerts stream.

Select the asset types to retrieve. Endpoint (managed devices) is included by default. Network Discovery (unmanaged network assets) and Cloud require active SentinelOne module subscriptions.

This menu is available in the SentinelOne Assets stream.

Select the types of security findings to retrieve. If none are selected, both vulnerabilities and misconfigurations are ingested.

This menu is available in the SentinelOne CNS Findings stream.

Select the findings to filter by severity level. This information is only applicable to misconfiguration findings. If none are selected, all severities are ingested.

This menu is available in the SentinelOne CNS Findings stream.

[Image: The SentinelOne tiles in the SecOps platform]

[Image: The SentinelOne Authentication window in the SecOps platform]

SentinelOne provides a range of products and services through its Singularity XDR platform, offering comprehensive protection against cyber threats such as malware, ransomware, and advanced persistent threats (APTs).

There are 6 SentinelOne streams. Select those that are based on your SentinelOne feature plan and use cases:

- SentinelOne Threats: Retrieves threat-related data flagged as a threat in the Management Console's Threats section when SentinelOne agents detect suspicious or malicious activity.
- SentinelOne Vulnerabilities: Retrieves vulnerability information from SentinelOne application data.
- SentinelOne Assets: Retrieves different types of assets based on configuration, including endpoints such as managed devices, cloud assets, and unmanaged devices discovered on the network.
- SentinelOne Alerts: Retrieves security alerts from the SentinelOne Singularity platform, allowing for centralized security monitoring, threat analysis, and incident response in the Zscaler Security Operations (SecOps) platform.
- SentinelOne Agents: Retrieves detailed information about the SentinelOne agents installed on endpoints across the organization, providing visibility into agent status, version, network information, and OS details.
- SentinelOne CNS Findings: Retrieves vulnerabilities and cloud/IT misconfigurations configured in the SentinelOne Singularity platform's Extended Security Posture Management (xSPM) module.

The SentinelOne Assets, SentinelOne Alerts, and SentinelOne CNS Findings streams are in Limited Availability (LA). To learn more, contact Zscaler Support.

## Prerequisites

To configure the SentinelOne connector, you need the source authentication configuration. The following parameters are required:

- Token
- Server URL

## Configuring the Connector

To create the SentinelOne data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the SentinelOne data source, enter the following information in the source setup Retrieval section:

- Authentication
- Alert Type
- Asset Type
- Findings Type
- Severity

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To learn more, see [Zscaler and SentinelOne Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-sentinelone-deployment-guide).

To learn more, see [Zscaler and SentinelOne Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-sentinelone-deployment-guide).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **SentinelOne Authentication** window appears.
2. In the **SentinelOne Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select the scope of alerts to retrieve. EDR alerts originate from managed endpoints. XDR alerts extend beyond the endpoint, including detections from cloud workloads, identity providers, and other integrated security tools. If neither are selected, both types are ingested.

This menu is available in the SentinelOne Alerts stream.

Select the asset types to retrieve. Endpoint (managed devices) is included by default. Network Discovery (unmanaged network assets) and Cloud require active SentinelOne module subscriptions.

This menu is available in the SentinelOne Assets stream.

Select the types of security findings to retrieve. If none are selected, both vulnerabilities and misconfigurations are ingested.

This menu is available in the SentinelOne CNS Findings stream.

Select the findings to filter by severity level. This information is only applicable to misconfiguration findings. If none are selected, all severities are ingested.

This menu is available in the SentinelOne CNS Findings stream.

[Image: The SentinelOne tiles in the SecOps platform]

[Image: The SentinelOne Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-service-level-agreement-settings","lastmod":"2026-04-23T03:27Z","nid":"1534129"} -->
## Configuring Service Level Agreement Settings

- Source: https://help.zscaler.com/uvm/configuring-service-level-agreement-settings
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Configuring Service Level Agreement Settings
- Last modified: 2026-04-23T03:27Z
- Summary: How to configure service level agreement (SLA) settings in the Zscaler SecOps UVM application.

Service level agreement (SLA) settings in Zscaler Unified Vulnerability Management (UVM) allow you to define and enforce resolution timelines for findings or tickets, helping your organization meet internal policies and compliance requirements. With customizable SLA rules, you can set timelines based on factors such as severity, asset criticality, vulnerability type, and other criteria. Additionally, you can enable dynamic SLA adjustments when key attributes change (e.g., severity or asset ownership), ensuring timelines stay accurate as conditions evolve.

SLA settings rely on severity categories, which are mapped to severity scores on the Severity Settings page. These severity categories feed into your SLA rules, allowing you to set resolution timelines that align with the risk levels of findings or tickets in your environment. To learn more, see [Configuring Severity Scores](https://help.zscaler.com/uvm/configuring-severity-scores) and [Configuring Severity Categories](https://help.zscaler.com/uvm/configuring-severity-categories).

For access to severity settings, your assigned role must include the Read, Create, and Edit permissions under the Vulnerabilities App - Ticket Settings resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles). 
See image.

To configure SLA settings:

1. In the **Vulnerabilities**app, go to **Settings**>**Severity & SLA**. See image.
2. Click **SLA Settings**. The **SLA Settings** page appears. If SLA is configured in the Data Model, you'll be prompted to unlink and override to configure the settings on the SLA Settings page.
3. On the **SLA Settings** page, configure the setup in the following sections:
  - Configuration
  - Default SLA Rules
  - Custom SLA Rules
4. Save the SLA settings in one of the following ways:
  - Click **Save**. The SLA settings apply the next time the finding or the ticket entity is processed.
  - In the **Save**drop-down menu, click **Save & Run**to save the settings and immediately apply them in your account.

You can reset the SLA settings to the system defaults, if needed. Click **Reset**at the bottom of the **SLA Settings**page to restore all SLA configurations to their original system-defined values.

[Image: Vulnerabilities App Ticket Settings Resource Permissions]

[Image: Vulnerabilities Settings Severity & SLA Page]

To configure the SLA granularity and start date:

1. Select the entity to apply the SLA rules: The subsequent SLA settings adjust based on your selection, including the available configurations and fields (e.g., those used as the SLA countdown trigger).
  - **Findings**: SLAs are applied to individual findings. Tickets SLA is calculated based on the SLA of the findings it contains (e.g., the earliest finding SLA date).
  - **Tickets**: SLAs are applied at the ticket level, and individual findings are not assigned SLAs.
2. **Default SLA start date**: Depending on your selection in the previous step, set a date to define when the SLA countdown begins. Triggers set in individual SLA rules override this global start date for findings or tickets that meet those rule conditions.
  - For **Findings**, select a finding-related date field from the drop-down menu (e.g., **Finding First Seen**).
  - For **Tickets**, choose one of the following:
    - Select a ticket-related date field from the drop-down menu (e.g., **Ticket Last Seen**).
    - Select **Third-Party Issue Created** (i.e., when the ticket is dispatched to an external system). This option triggers the SLA countdown when the ticket is dispatched to an external system.
3. (Optional) Select **Enable Dynamic SLA Updates**to allow SLA deadlines to automatically recalculate when key attributes change (e.g., severity or asset owner). Dynamic SLA updates are not available when **Third-Party Issue Created**is selected as the start date.

See image.

This section contains a list of fallback rules for each severity category configured in [Severity Settings](https://help.zscaler.com/uvm/configuring-severity-categories). These rules ensure that a default SLA duration is always applied, preventing potential data conflicts or loss.

See image.

While the conditions of the default rules are fixed, you can adjust the SLA start dates and durations to align with your organization's remediation requirements.

To adjust a default SLA rule:

1. In the**Default SLA Rules**section, select the rule you want to adjust. The rule configuration drawer appears.
2. In the rule configuration drawer: See image.
  1. **SLA Start Date**: From the drop-down menu, select the field to define when the SLA countdown begins.
  2. **Set SLA to**<**#**>**day(s)**: Enter the number of days for the SLA duration. The SLA countdown starts when the rule conditions are met and the SLA start date is triggered. For example, if you select **Ticket First Seen**and the ticket is first seen on October 1 with an SLA duration of 10 days, the SLA will be due on October 11. If the SLA duration is set to 0 days, SLA is not applied, and the SLA field for the affected findings or tickets is populated with the value "No SLA". This option is useful for severity categories where an SLA is not required.
  3. Click **Save**to apply changes to the rule.

Default SLA rules act as fallback timelines based on severity, but are overridden by custom rules when their conditions are met.

In the **Custom SLA Rules**section, you can define SLAs for customized use cases. For example, you can create a rule to enforce a strict SLA for critical tickets associated with findings on Crown Jewel assets, or set extended SLA timelines for specific asset types like development servers.

To create custom SLA rules:

1. In the **Custom SLA Rules**section, click **New Rule**. The **New SLA Rule** drawer appears.
2. In the **New SLA Rule**drawer: See image.
  1. **Name**: Enter a name for the rule.
  2. **IF**: Define the rule conditions that determine which findings or tickets the rule should apply to.
    - (Optional) Select the severity checkbox and choose the severity category that the rule should apply to (e.g., **Critical**, **High**). The severity checkbox corresponds to the selection in the **Configuration**section (i.e., **Finding Severity**for **Findings**,or **Ticket Severity**for **Tickets**).
    - Create custom rules:
      1. Select a field that the condition should be based on. Available fields depend on the selection in the **Configuration**section.
      2. Select an operator (e.g., **Equals**, **Contains**). Available operators vary depending on the field type, indicated to the left of the field name.
      3. Enter the value that the rule should apply to. SLA rule conditions are not case sensitive.
      4. (Optional) Use **AND**/**OR**logic to define compound rules.
        - **AND**:The SLA applies only if all specified conditions are met.
        - **OR**: The SLAapplies if any of the specified conditions are met.
  3. **THEN**: Define the SLA actions that apply when conditions are met.
    1. **SLA Start Date**: From the drop-down menu, select the event that triggers the SLA countdown (e.g., **Ticket Created Date**).
    2. **Set SLA to <#> day(s):** Enter the number of days you want to allow for resolution. The SLA countdown starts when the rule conditions are met and the SLA start date is triggered. For example, if you select **Ticket First Seen**and the ticket is first seen on October 1 with an SLA duration of 7 days, the SLA will be due on October 8. If the SLA duration is set to 0 days, no SLA applies, and the SLA field is populated with the value "No SLA" for affected findings or tickets.
  4. Click **Save**to create the rule.
3. (Optional) Adjust the order of SLA rules by dragging and dropping them to the order you want them to apply.

The new rule appears in the Custom SLA Rules list and takes precedence over Default SLA Rules when its conditions are met.

[Image: SLA Settings Configuration Section]

[Image: SLA Settings Default SLA Rules Section]

[Image: SLA Settings Critical Default Rule Drawer]

[Image: SLA Settings Custom Rule Crown Jewel Assets Example]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-servicenow-cmdb-outegration","lastmod":"2026-08-16T01:01Z","nid":"1534007"} -->
## Configuring ServiceNow CMDB Outegration

- Source: https://help.zscaler.com/uvm/configuring-servicenow-cmdb-outegration
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring ServiceNow CMDB Outegration
- Last modified: 2026-08-16T01:01Z
- Summary: How to configure ServiceNow CMDB outegration.

The ServiceNow CMDB outegration enables updates to CMDB records, such as adding missing assets, and maintaining an accurate and up-to-date CMDB. This is critical for improving operational efficiency, and strengthening policy compliance and audit readiness.

This article is a step-by-step guide to configuring the ServiceNow CMDB outegration.

## Prerequisites

Before configuring the outegration, make sure you have met the following prerequisites:

- Configure the ServiceNow Assets Connector. To learn more, see [Configuring the ServiceNow Connectors.](https://help.zscaler.com/uvm/configuring-servicenow-connectors)
- Ensure that you retrieve the authentication parameters based on the selected authentication method to enter them in the corresponding fields during the outegration setup:
  - OAuth 2.0
  - User Name Password Client ID Client Secret
  - Basic Authentication (Username and Password)
  - JWT OAuth2

## Configuring the ServiceNow CMDB Outegration

To configure the ServiceNow CMDB outegration, complete the following steps:

- Step 1: Authenticate the ServiceNow CMDB Connection (Connect)
- Step 2: Configure the Outegration Visibility and Behavior (Settings)
- Step 3: Map the Outegration Fields (Mapping)

Obtain the following parameters:

- Instance Name
- Client ID and Client Secret
- Refresh Token and Access Token

The ServiceNow CMDB instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

If the ServiceNow CMDB instance is not hosted on the ServiceNow domain, the instance name is the full domain name.

To obtain the client ID and client secret:

1. Log in to ServiceNow and access the relevant instance as an admin user.
2. Go to **System OAuth**> **Application Registry**.
3. Click **New**to create a new application registry.
4. On the interceptor page, click **Create an OAuth API endpoint for external clients**. The **Application Registries** window appears.
5. In the **Application Registries** window: See image.
  1. **Name**: Enter a name for the platform using the OAuth endpoint.
  2. **Client ID**:The ID is automatically generated by the instance. Copy this value and save it.
  3. **Client Secret**:The client secret is generated by the instance after you submit the form.
  4. **Refresh Token Lifespan**: Enter8,640,000 seconds (100 days). The value can be increased.
  5. **Access Token Lifespan**: Enter1,800 seconds (30 minutes). The value can be increased.
6. Click **Submit**. The client secret is generated and displayed within the created record.

To generate the refresh token for the ServiceNow CMDB outregation, make a curl request to the ServiceNow OAuth token endpoint.

To make the curl request, prepare the OAuth 2.0 credentials by inserting the following details:

- `<Instance Name>`: Enter the retrieved instance name.
- `grant_type`: Set to `password`.
- `<Client ID>`: The client ID of your OAuth application.
- `<Client Secret>`: The client secret of your OAuth application.
- `<Username>`: Your ServiceNow user account name that authorizes the access token request.
- `<Password>`: The password for the ServiceNow user account that authorizes the access token request.

To generate the refresh token, launch your terminal or an API platform (e.g., Postman) and run the following command:

```
curl --location 'https://
<Instance Name>
.service-now.com/oauth_token.do' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=
password
' \
--data-urlencode 'client_id=
<Client ID>
' \
--data-urlencode 'client_secret=
<Client Secret>
' \
--data-urlencode 'username=
<Username>
' \
--data-urlencode 'password=
<Password>
'
```

The curl request returns an access token and a refresh token.

Enter the refresh token in the mandatory **Refresh Token** field in the outegration authentication step. The access token is optional, and is automatically generated if left blank.

[Image: Generate the client ID and client secret]

Obtain the following parameters for the **Username Password Client ID Client Secret**authentication method:

- Instance Name
- Username and Password
- Client ID and Client Secret

The ServiceNow CMDB instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

The email and password associated with a ServiceNow`Security Admin`user and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

For the username password client credentials authentication method, you need to provide ServiceNow client credentials that were generated using a ServiceNow admin account.

1. Log in to ServiceNow and access the required instance as an admin user.
2. Go to **System OAuth**> **Application Registry**.
3. Click **New**.
4. On the interceptor page, click **Create an OAuth API endpoint for external clients**. The **Application Registries** window appears.
5. In the **Application Registries** window: See image.
  1. **Name**: Enter a name for the platform using the OAuth endpoint.
  2. **Client ID**: The ID is automatically generated by the instance. Copy this value and save it.
  3. **Client Secret**: The client secret is generated by the instance after you submit the form.
  4. **Refresh Token Lifespan**: Enter 8,640,000 seconds (100 days). The value can be increased.
  5. **Access Token Lifespan**: Enter 1,800 seconds (30 minutes). The value can be increased.
6. Click **Submit**.

[Image: Generate the client ID and client secret]

- Instance Name
- Username and Password

The ServiceNow CMDB instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

The email and password associated with a ServiceNow user with the `ITIL` role or higher and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

- Instance Name
- Client ID and Client Secret
- Key ID
- Subject

To learn more, refer to the [ServiceNow documentation](https://www.servicenow.com/docs/bundle/yokohama-platform-security/page/administer/security/task/create-jwt-endpoint.html).

### Adding a Certificate in JWT OAuth2

When authenticating using the JWT OAuth2 method, a dialog window appears containing a certificate after entering the parameters and successfully creating the authentication.

See image.

To complete the authentication process:

1. Copy the certificate from the dialog window that appears.
2. In the ServiceNow portal, go to **System Definition**> **Certificates**.
3. Click **New**.
4. Enter a name, and enter the certificate in the **PEM Certificate** field.
5. Click **Submit**.
6. Link the certificate in the **Application Registry**by updating the **Verifier Map**field to reference the new entry in `Sys_certificate`.
7. Click **Test**in the bottom-right corner of the page to verify the connection. Invalid credentials trigger error messages to assist with troubleshooting connectivity issues.
8. After the test passes, click **Next**to advance to the **Settings**step.

The ServiceNow CMDB instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

For the JWT OAuth2 authentication method, you'll need to provide ServiceNow client credentials that were generated using a ServiceNow admin account.

1. Log in to ServiceNow and access the required instance as an admin user.
2. Go to **System OAuth**> **Application Registry**.
3. Click **New**.
4. On the interceptor page, click **Create an OAuth JWT API endpoint for external clients**. The **New Record** window appears.
5. In the **New Record** window: See image.
  1. **Name:** Enter a name for the platform using the OAuth JWT API endpoint.
  2. **Client ID**: The ID is automatically generated by the instance. Copy this value and save it.
  3. **Client Secret**:The ID is automatically generated by the instance after you submit the form.
  4. **User Field**: The field in the User (`sys_user`) table that the system uses to match the value of the subject claim in the JWT is set to **Email**by default.
  5. **Access Token Lifespan**: The value is set to1,800 seconds (30 minutes) by default. The value can be increased.
  6. **Clock skew**: The value is set to 300 seconds (5 minutes) by default. The value can be increased.
6. Click **Submit**.

[Image: Client credentials that were generated using a ServiceNow admin account]

For the JWT auth method, you'll need to provide a Key ID (Kid), which is generated when creating a new verifier map.

See image.

[Image: Key ID generated when creating a verifier map]

The JWT OAuth2 authentication method requires the subject (`sub`) field for authenticating with ServiceNow. This value is used to identify the user in the `sys_user` table. If the User field in the JWT authentication profile is left as the default (i.e., **Email**), the Subject must match the user's email address. If the User field was changed (e.g., to `user_name`), the Subject must instead match the value in that specified field.

To establish a secure connection with the ServiceNow CMDB instance, you need to authenticate with the parameters you previously saved.

1. In the Zscaler Security Operations (SecOps), go to **Configure** > **Outegrations**. See image.
2. Click **Create** and select **ServiceNow CMDB**.
3. In the **Details** section:
  1. **Display Name**: Enter a name for your outegration.
  2. **Active**: Enable to activate the ServiceNow outegration.
  3. **Instance Name**: Enter the name of the hosted ServiceNow CMDB instance where the asset details should be added. If the ServiceNow CMDB instance is not hosted on the ServiceNow domain, the instance name is the full domain name.
  4. **Authentication**: Select an existing authentication ID, or click **Create New** to set up a new authentication, and enter the required parameters you retrieved earlier into the corresponding fields. See image.
4. Click **Test**in the bottom-right corner of the page to verify the connection. If the credentials are invalid, an error message is displayed along with the remediation steps to resolve the issue. See image.
5. After the connection is verified, click **Next**to proceed to the **Settings**step.

[Image: Outegrations page in the SecOps platform]

[Image: Authentication details for ServiceNow outegration setup]

[Image: ServiceNow CMDB outegration details]

[Image: JWT OAuth2 certificate created]

In this step, you need to set the CMDB entity for which you want to configure the outegration, the ServiceNow CMDB Table where the asset details will be dispatched to, and when the Create ServiceNow Ticket button should appear in the violation ticket.

1. In the **Advanced Settings** section:
  1. **Create ServiceNow CMDB item from**: By default,**Violation Ticket** is selected.
  2. **Table**:Select the ServiceNow CMDB table that the asset should be dispatched to. The schema associated with the selected table will be retrieved from your ServiceNow CMDB table and made available for mapping in the Mapping step. Each ServiceNow CMDB Table Type (e.g., CMDB CI Server) requires a separate outegration configuration. See image.
2. In the **Violation Ticket View**section, select how the violation ticket should display the **ServiceNow CMDB**button. Select **For specific violation ticket**to define the custom conditions that control when the button is displayed, allowing you to target specific violation tickets. See image.
  - **Field Name**: Select**Violation Ticket** Type.
  - **Field Value**: Select **CMDB Hygiene**.
3. Click **Map**to advance to the **Mapping** step.

The **ServiceNow CMDB**button appears in the relevant violation ticket.

See image.

[Image: ServiceNow CMDB button in the violation ticket]

[Image: ServiceNow CMDB table that the assets data should be dispatched to]

[Image: Violation Ticket View selection]

The objective of the mapping process is to map SecOps fields (left) to ServiceNow CMDB fields (right). To map values to fields, configure values on the left to populate the fields selected on the right.

See image.

### Creating a New Mapping

To create a new mapping from SecOps to ServiceNow CMDB:

1. Select a field (right): See image.
  1. Click**Mapping**.
  2. Select a field on the right. The field's schema details open on the right of the page. The schema lists the ServiceNow CMDB fields that can be used for mapping. See image. The following details are specified for fields, when available:
    - Required
    - Input Type
    - Available Options
2. Configure the field value (left):
  1. Click **Add value** on the left. The **Field Editor** appears.
  2. In the **Field Editor**, select one of the following methods to configure the value of the field:
    - Field (Dictionary)
    - Expression

Repeatthe mapping process for all required ServiceNow CMDB fields and for any other fields you want to map. Ensure that fields that you want to add are up to date.

To preview the mapping, click **Preview**on the bottom right of the ticket initially dispatched to the ServiceNow CMDB section. The Mapping Preview window appears. On the left of the Mapping Preview window, there is a sample of the assets in your account, organized by asset ID. You can select, filter, or search the assets and preview the mapping. You can also open the asset details in a new tab for a more in-depth review.

[Image: Mapping fields to populate on the right]

[Image: Outegration schema field options]

The Required attribute is TRUE if a field is required by ServiceNow CMDB. If a field is not required, the attribute is not displayed. A required ServiceNow CMDB field is also indicated by a red asterisk (*) on the ServiceNow CMDB field in the first mapping step.

Required ServiceNow CMDB fields must be mapped before saving the outegration.

The Input Type specifies the data type of the ServiceNow CMDB field, such as TEXT (e.g., Attested), DATE (e.g., Start Date), or NUMBER (e.g., Attestation Score). This indicates the format that the selected source field must match to successfully map to the ServiceNow CMDB field.

For ServiceNow CMDB fields with fixed values, the Available Options column displays the available values. For example, if the ServiceNow CMDB field Attestation Status is configured to include the following fixed values—Attested, Not Yet Reviewed, Rejected—the corresponding values in the field can be mapped to these values.

Select a field on the left to populate the field on the right.

The field dictionary allows you to create mappings between specific values from the field on the right and values of the field on the left. To use the dictionary, you must first select a field on the right and a field to populate it with on the left.

See image.

[Image: Create mappings between specific values from the field on the right and values from the field on the left]

For use cases that require more advanced configuration, you can use the Expression Editor to configure the field value to be mapped to the target field.

[Image: Expression Editor]

[Image: ServiceNow CMDB data mapping]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-servicenow-connector","lastmod":"2026-08-03T14:33Z","nid":"1530840"} -->
## Configuring the ServiceNow Connector

- Source: https://help.zscaler.com/uvm/configuring-servicenow-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the ServiceNow Connector
- Last modified: 2026-08-03T14:33Z
- Summary: How to configure the ServiceNow connector, including setting up authentication and data retrieval filters and specifications.

ServiceNow is used to set up systems that define, manage, automate, and structure IT services for companies.

There are three ServiceNow connector streams. To learn more, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

This article covers the ServiceNow Assets and the ServiceNow Users streams. For the ServiceNow Generic stream, see [Configuring ServiceNow Generic Connectors](https://help.zscaler.com/uvm/configuring-servicenow-generic-connector).

## Prerequisites

To configure the ServiceNow connector, you need the source authentication configuration. Select an authentication method and retrieve the required parameters:

- OAuth 2.0
- User Name Password Client Id Client Secret
- Basic Authentication (Username and Password)
- Jwt OAuth2

Obtain the following required parameters for the OAuth 2.0 authentication method:

- Instance Name
- Client ID and Client Secret
- Refresh Access Token
- Verify SSL

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

Provide the ServiceNow client credentials that were generated using a ServiceNow admin account.

To learn more about creating a client ID and client secret, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

To learn more about generating the refresh token for your ServiceNow source, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

Obtain the following required parameters for the Username Password Client Id Client Secret authentication method:

- Instance Name
- Username and Password
- Client ID and Client Secret
- Verify SSL

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

The email and password associated with a ServiceNowSecurity Adminuser and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

Provide ServiceNow client credentials that were generated using a ServiceNow admin account.

To learn more about creating a client ID and client secret, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

Obtain the following required parameters for the Basic Authentication method:

- Instance Name
- Username and Password
- Verify SSL

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

The email and password associated with a ServiceNow user with the ITIL role or higher and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

Obtain the following required parameters for the Jwt OAuth2 authentication method:

- Instance Name
- Client ID and Client Secret
- Key ID
- Subject
- Verify SSL

To learn more, refer to the [ServiceNow documentation](https://www.servicenow.com/docs/bundle/yokohama-platform-security/page/administer/security/task/create-jwt-endpoint.html).

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

Provide ServiceNow client credentials that were generated using a ServiceNow admin account.

To learn more about creating a client ID and client secret, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

Provide a Key ID, or Kid, which is generated when you create a new verifier map.

See image.

[Image: snow jwt key id]

The Jwt OAuth2 authentication method requires the subject (`sub`) field for authenticating with ServiceNow. This value is used to identify the user in the `sys_user` table. If the User field in the JWT authentication profile is left as the default (i.e., email), the Subject must match the user's email address. If the User field was changed (e.g., to `user_name`), the Subject must instead match the value in that specified field.

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

## Configuring the Connector

To create the ServiceNow data source in the Security Operations Platform:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the ServiceNow data source, enter the following information in the source setup Retrieval section:

- Authentication
- Instance Name
- Application data
- Include User's Related Group

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Troubleshooting and FAQs

The following table provides troubleshooting guidance and answers to frequently asked questions to help address common issues:

| **Potential Cause** | **Troubleshooting Action** |
| --- | --- |
| I selected the Business Application Data checkbox, but I am not seeing my business applications. | Check whether ServiceNow Service Mapping is enabled in your organization. If it is, you might need to select the Business Services (Discovered) Data checkbox in addition to the Business Application Data checkbox and rerun the source. To learn more about Service Mapping, refer to the [ServiceNow documentation](https://www.servicenow.com/docs/bundle/xanadu-it-operations-management/page/product/service-mapping/task/view-unified-map-sm-workspace.html). |
| The Business Application Data checkbox retrieved my business applications, but some of the expected applications are missing. | Consider selecting the Applications Data checkbox. |

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **ServiceNow Authentication** window appears.
2. In the **ServiceNow Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

[Image: The ServiceNow - Assets and ServiceNow - Users tiles]

[Image: The Service Now Authentication window displaying the Name, Authentication Type, Client Id, Client Secret, Refresh Token, Access Token, and Tenant Id in the SecOps platform]

The Instance Name field is the name of the hosted ServiceNow instance. It is found in the URL in the format (e.g., `https://[instance-name].service-now.com`). For example, if your URL is `https://acme.service-now.com/`, then the instance name is `acme`.

Application data checkboxes retrieve related application data for each asset. There are 4 checkboxes and fields for each selected asset, and they are prefixed with their corresponding table name (e.g., `cmdb_ci_appl.sys_id`). The available checkboxes are:

- Include Related Applications Data
- Include Related Application Services (Discovered) Data
- Include Related Business Applications Data
- Include Related IT Services Data

The application data checkboxes are available in the ServiceNow Assets stream.

To effectively retrieve application data, determine whether your organization uses ServiceNow Service Mapping. If Service Mapping is not configured, you can select any of the 4 checkboxes to retrieve the available data types. If Service Mapping is configured, you can only retrieve one of the following data types:

- Related Applications data
- Related Application Services data

When Service Mapping is configured, you can retrieve Related Business Applications data only in conjunction with Related Application Services data. In this case, the Application Services and Business Applications data are dependent. If your configuration is not supported, contact Zscaler Support to discuss potential solutions.

The Include User's Related Group checkbox retrieves the related group for each of the users retrieved.

This checkbox is available in the ServiceNow Users stream.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-servicenow-generic-connector","lastmod":"2026-08-03T14:39Z","nid":"1534356"} -->
## Configuring the ServiceNow Generic Connector

- Source: https://help.zscaler.com/uvm/configuring-servicenow-generic-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the ServiceNow Generic Connector
- Last modified: 2026-08-03T14:39Z
- Summary: How to configure the ServiceNow Generic connector, including setting up authentication and data retrieval filters and specifications.

ServiceNow is used to set up systems that define, manage, automate, and structure IT services for companies.

There are three ServiceNow connector streams. To learn more, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

This article covers the ServiceNow Generic stream. For the ServiceNow Assets and the ServiceNow Users streams, see [Configuring the ServiceNow Connector](https://help.zscaler.com/uvm/configuring-servicenow-connectors).

## Prerequisites

To configure the ServiceNow Generic connector, you need the source authentication configuration. Select an authentication method and retrieve the required parameters:

- OAuth 2.0
- User Name Password Client Id Client Secret
- Basic Authentication (Username and Password)
- Jwt OAuth2

Obtain the following required parameters for the OAuth 2.0 authentication method:

- Client ID and Client Secret
- Refresh Token and Access Token
- Tenant ID
- Verify SSL

Provide the ServiceNow client credentials that were generated using a ServiceNow admin account.

To learn more about creating a client ID and client secret, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

To learn more about generating the refresh token and access token for your ServiceNow source, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

The ServiceNow tenant ID.

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

Obtain the following required parameters for the Username Password Client Id Client Secret authentication method:

- Username and Password
- Client ID and Client Secret
- Verify SSL

The email and password associated with a ServiceNowSecurity Adminuser and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

Provide ServiceNow client credentials that were generated using a ServiceNow admin account.

To learn more about creating a client ID and client secret, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

Obtain the following required parameters for the Basic Authentication method:

- Username and Password
- Verify SSL

The email and password associated with a ServiceNow user with the ITIL role or higher and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

Obtain the following required parameters for the Jwt OAuth2 authentication method:

- Client ID and Client Secret
- Key ID
- Subject
- Verify SSL

To learn more, refer to the [ServiceNow documentation](https://www.servicenow.com/docs/bundle/yokohama-platform-security/page/administer/security/task/create-jwt-endpoint.html).

Provide ServiceNow client credentials that were generated using a ServiceNow admin account.

To learn more about creating a client ID and client secret, see [Zscaler and ServiceNow Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-servicenow-deployment-guide).

Provide a Key ID, or Kid, which is generated when you create a new verifier map.

See image.

[Image: snow jwt key id]

This value is used to identify the user in the `sys_user` table. If the User field in the JWT authentication profile is left as the default, the subject must match the user's email address. If the User field has changed, the Subject must instead match the value in that specified field.

Select the Verify SSL checkbox to allow the configuration of a root certificate authority (CA) certificate to trust during the SSL negotiation in the CA certificate field.

## Configuring the Connector

To create the ServiceNow Generic data source in the Security Operations Platform:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the ServiceNow Generic data source, enter the following information in the source setup Retrieval section:

- Authentication
- Instance Name
- Table Name
- Query
- Fields Params

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Service Now Authentication** window appears.
2. In the **Service Now Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

The Instance Name field is the name of the hosted ServiceNow instance. It is found in the URL in the format (e.g., `https://[instance-name].service-now.com`). For example, if your URL is `https://acme.service-now.com/`, then the instance name is `acme`.

The name of the table you want to retrieve from ServiceNow.

The query determines whether the search is limited to domains the user is configured to access. Enter `true` to include the record even if it is in a domain that the ServiceNow user is not configured to access. Enter `false` to exclude the record if it is in a domain that the ServiceNow user is not configured to access.

The list of fields retrieved from the table.

[Image: The Service Now Generic tile in the SecOps platform]

[Image: The ServiceNow Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-servicenow-outegration","lastmod":"2026-08-18T02:08Z","nid":"1528026"} -->
## Configuring the ServiceNow Outegration

- Source: https://help.zscaler.com/uvm/configuring-servicenow-outegration
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the ServiceNow Outegration
- Last modified: 2026-08-18T02:08Z
- Summary: How to configure the ServiceNow outegration for disptaching SecOps tickets to the ServiceNow work management system.

The ServiceNow outegration is used to dispatch tickets from the Zscaler Security Operations (SecOps) platform applications (e.g., UVM) to your ServiceNow tables, creating a ServiceNow ticket that can then be tracked, assigned, and managed by your remediation teams working with ServiceNow.

This article is a step-by-step guide to setting up the ServiceNow work management outegration. The process involves setting up authentication, outegration visibility in the platform, outegration mapping, and when relevant, configuring a ServiceNow webhook to enable bidirectional synchronization.

Each ServiceNow Table Type (e.g., Remediation Task, Incident) requires a separate outegration configuration.

## Prerequisites

Retrieve the required authentication parameters based on your selected authentication method (e.g., Username and Password, OAuth 2.0), and enter them in the corresponding fields during the Connect step of the outegration setup wizard.

- OAuth 2.0
- User Name Password Client ID Client Secret
- Basic Authentication (Username and Password)
- Jwt OAuth2

## Creating the ServiceNow Outegration

To configure the ServiceNow outegration, complete the following steps:

- Step 1: Authenticate the ServiceNow Connection (Connect)
- Step 2: Configure the Outegration Visibility and Behavior (Settings)
- Step 3: Map the Outegration Fields (Mapping)
- (Optional) Step 4: Configure the ServiceNow Webhook

Obtain the following required parameters for the OAuth 2.0 authentication:

- Instance Name
- Client ID and Client Secret
- Refresh Token and Access Token

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

For the OAuth 2.0 authentication method, you'll need to provide ServiceNow client credentials that were generated using a ServiceNow admin account.

To create a client ID and client secret:

1. Log in to ServiceNow as an admin user.
2. In the relevant instance, go to **System OAuth**> **Application Registry**.
3. Click **New**.
4. On the interceptor page, click **Create an OAuth API endpoint for external clients**. The **Application Registries** window appears.
5. In the **Application Registries** window: See image.
  1. **Name**: Enter a name that identifies the platform.
  2. **Client ID**:Automatically generated by the instance.
  3. **Client Secret**:Automatically generated by the instance after you submit the form.
  4. **Refresh Token Lifespan**: Enter8,640,000 seconds (100 days). This can be increased.
  5. **Access Token Lifespan**: Enter1,800 seconds (30 minutes). This can be increased.
6. Click **Submit**.

Generating the refresh token for your ServiceNow outegration involves making a curl request to the ServiceNow OAuth token endpoint. To make the curl request, insert the following OAuth 2.0 credentials into the command:

- `<Instance Name>`: Enter the instance name previously retrieved (e.g., `acme`).
- `grant_type`: Set to `password`.
- `<Client ID>`: The client ID of your OAuth application previously generated.
- `<Client Secret>`: The client secret of your OAuth application previously generated.
- `<Username>`: Your ServiceNow user account name that authorizes the access token request.
- `<Password>`: The password for the ServiceNow user account that authorizes the access token request.

To generate the refresh token, launch your terminal or an API platform (e.g., Postman) and run the following command:

```
curl --location 'https://
<Instance Name>
.service-now.com/oauth_token.do' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=
password
' \
--data-urlencode 'client_id=
<Client ID>
' \
--data-urlencode 'client_secret=
<Client Secret>
' \
--data-urlencode 'username=
<Username>
' \
--data-urlencode 'password=
<Password>
'
```

The curl request returns an access token and a refresh token. Make sure to enter the refresh token in the mandatory Refresh Token field in the outegration authentication. The access token is optional, and is automatically generated if left blank.

Obtain the following required parameters for the username password client ID client secret authentication method:

- Instance Name
- Username and Password
- Client ID and Client Secret

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

The email and password associated with a ServiceNow`Security Admin`user and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

For the username password client credentials authentication method, you'll need to provide ServiceNow client credentials that were generated using a ServiceNow admin account.

To create client ID and client secret:

1. Log in to ServiceNow with an admin user.
2. In the relevant instance, go to **System OAuth**> **Application Registry**.
3. Click **New**.
4. On the interceptor page, click **Create an OAuth API endpoint for external clients**. The **Application Registries** window appears.
5. In the **Application Registries** window: See image.
  1. **Name:** Enter a name that identifies the platform.
  2. **Client ID**:Automatically generated by the instance.
  3. **Client Secret**:Automatically generated by the instance after you submit the form.
  4. **Refresh Token Lifespan**: Enter8,640,000 seconds (100 days). This can be increased.
  5. **Access Token Lifespan**: Enter1,800 seconds (30 minutes). This can be increased.
6. Click **Submit**.

Obtain the following required parameters for the basic authentication method (i.e., username and password):

- Instance Name
- Username and Password

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

The email and password associated with a ServiceNow user with the ITIL role or higher and permissions to access the relevant table. To learn more, refer to the [ServiceNow documentation](https://docs.servicenow.com/bundle/utah-platform-administration/page/administer/roles/reference/r_BaseSystemRoles.html).

Obtain the following required parameters for the Jwt OAuth 2 authentication method:

- Instance Name
- Client ID and Client Secret
- Key ID
- Subject

To learn more, refer to the [ServiceNow documentation](https://www.servicenow.com/docs/bundle/yokohama-platform-security/page/administer/security/task/create-jwt-endpoint.html).

The ServiceNow instance name is the name of the hosted ServiceNow instance found in the URL in the format `https://``<Instance Name>``.service-now.com`. For example, if your URL is `https://``acme``.service-now.com/`, then the instance name is `acme`.

For the Jwt OAuth2 authentication method, you'll need to provide ServiceNow client credentials that were generated using a ServiceNow admin account.

To create client ID and client secret:

1. Log in to ServiceNow with an admin user.
2. In the relevant instance, go to **System OAuth**> **Application Registry**.
3. Click **New**.
4. On the interceptor page, click **Create an OAuth JWT API endpoint for external clients**. The **New Record** window appears.
5. In the **New Record** window: See image.
  1. **Name:** Enter a name that identifies the platform.
  2. **Client ID**:Automatically generated by the instance.
  3. **Client Secret**:Automatically generated by the instance after you submit the form.
  4. **User field**: The field in the User (sys_user) table that the system uses to match the value of the subject claim in the JWT. By default, set to **Email**.
  5. **Access Token Lifespan**: By default, set to1,800 seconds (30 minutes). This can be increased.
  6. **Clock skew**: By default, set to 300 seconds (5 minutes). This can be increased.
6. Click **Submit**.

[Image: creating the snow jwt oauth2 client credentials form]

For the JWT auth method, you'll need to provide a Key ID (or KID), which is generated when creating a new verifier map.

See image.

[Image: snow jwt key id]

The Jwt OAuth2 authentication method requires the subject (`sub`) field for authenticating with ServiceNow. This value is used to identify the user in the `sys_user` table. If the User field in the JWT authentication profile is left as the default (i.e., email), the Subject must match the user's email address. If the User field was changed (e.g., to `user_name`), the Subject must instead match the value in that specified field.

The first step in setting up your ServiceNow outegration is to authenticate using valid credentials to establish a secure connection with your ServiceNow instance. With the required parameters retrieved in the prerequisites, you can begin the ServiceNow outegration setup in the SecOps platform.

To create an outegration:

1. In the SecOps platform, go to **Configure** > **Outegrations**. See image.
2. Click **Create**, then select **ServiceNow**. The **Connect** step appears. See image.
3. In the **Details** section:
  1. **Display Name**: Enter a name for your outegration.
  2. **Active**: Enable to activate the ServiceNow outegration.
  3. **Instance Name**: Enter the name of the hosted ServiceNow instance where the tickets should be created.
  4. **Authentication**: Select an existing authentication, or click **Create New** to set up a new authentication, and enter the required parameters you retrieved earlier into the corresponding fields. To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications).
4. Click **Test** in the bottom-right corner of the screen to verify the connection. Invalid credentials trigger error messages to assist with troubleshooting connectivity issues.
5. After the test passes, click **Next**to advance to the **Settings**step.

### Adding a Certificate in Jwt OAuth2

When authenticating using the Jwt OAuth2 method, a dialog window appears containing a certificate after entering the parameters and successfully creating the authentication.

See image.

To complete the authentication process:

1. Copy the certificate from the window.
2. In the ServiceNow portal, go to **System Definition**> **Certificates**.
3. Click **New**.
4. Fill in the entry details, and paste the certificate in the **PEM Certificate**field.
5. Click **Submit**.
6. Link the certificate in the **Application Registry**by updating the **Verifier Map**field to reference the new entry in `Sys_certificate`.
7. Click **Test**in the bottom-right corner of the page to verify the connection. Invalid credentials trigger error messages to assist with troubleshooting connectivity issues.
8. After the test passes, click **Next**to advance to the **Settings**step.

[Image: snow outegration connect step]

[Image: jwt oauth 2 certificate]

In the Settings step of the outegration setup wizard, configure your ServiceNow outegration's visibility and behavior within the relevant application in the SecOps platform (e.g., UVM, AEM). In this step, you'll set the SecOps entity that triggers the ServiceNow ticket dispatch (e.g., ticket, violation ticket), the ServiceNow table that the SecOps ticket should be dispatched to, and when the Create ServiceNow Ticket button should appear in the application. The Create ServiceNow Ticket button allows end users with access to SecOps tickets to dispatch these tickets to a ServiceNow table directly from the SecOps ticket drawer or from the SecOps tickets page.

To configure the outegration's visibility and behavior:

1. In the **Advanced Settings** section:
  1. **Create ServiceNow item from**:Select the entity that you want to configure the outegration for. This selection affects the view you'll configure in the <Entity> **View**step (e.g., selecting Ticket displays the Tickets View setting). Other entity types might be visible depending on the apps enabled in your account. See image.
    - **UVM**: Select **Ticket**.
    - **AEM**: Select **Violation Ticket**.
  2. **Table**:Select the ServiceNow table that the SecOps ticket should be dispatched to. The schema associated with the selected table will be retrieved from your ServiceNow table and made available for mapping in the Mapping step. See image.
2. In the <Entity> **View** section, select how the SecOps ticket should display the **Create ServiceNow Ticket**button. This setting can be modified at any time. See image.
  - **Always**: Select to display the button in all tickets, allowing users to dispatch tickets to a ServiceNow ticket without exception.
  - **Never**: Select to hide the button in all tickets. This is useful during the outegrationsetup process to hide the button from users while still keeping the outegration active.
  - **For specific tickets**: Define custom conditions to control when the button is displayed, allowing you to target specific tickets. For example, if your organization uses multiple ticketing systems, you can grant access to the button only to users who work with ServiceNow, while excluding those who use other ticketing systems (e.g., Jira).
3. Click **Map**to advance to the **Mapping** step.

The **Create ServiceNow Ticket**button appears in two locations:

- In the individual entity drawer (e.g., in the [UVM ticket drawer](https://help.zscaler.com/uvm/managing-tickets-uvm), in the [AEM violation ticket drawer](https://help.zscaler.com/uvm/managing-violation-tickets-aem)). See image.
- On the entity page in the relevant application (e.g., on the [Tickets page](https://help.zscaler.com/uvm/about-tickets-operational-view-uvm) in UVM, on the [Violation Tickets page](https://help.zscaler.com/uvm/about-violation-tickets-operational-view-aem) in AEM). See image.

The third step in setting up your ServiceNow outegration is configuring the field mapping between your SecOps tickets and ServiceNow tickets. This defines how data is exchanged and synchronized between the two systems upon initial dispatch and subsequent updates. The SecOps platform's unique mapping capabilities allow for flexible mapping of any custom field or logic to any field in your ServiceNow tables, facilitating highly customized workflows that align with your organization's requirements.

The main objective of the mapping process is to map values to fields. To map values to fields, configure values on the left to populate the fields selected on the right.

See image.

There are three mapping components:

- Tickets initially dispatched to ServiceNow: Map SecOps ticket fields (left) to ServiceNow fields (right) for the initial dispatch of a ticket to a ServiceNow ticket. You can also add an attachment to your ServiceNow ticket. Commonly mapped fields include Short Description, Description, Assignment Group, Priority, Due Date, and Status.
- (Optional) Sync from ticket to ServiceNow: Map SecOps ticket fields (left) to ServiceNow fields (right) for syncing ticket updates to ServiceNow, including configuring comments and adding an attachment to your ServiceNow ticket. Commonly mapped fields include Status and Due Date.
- (Optional) Sync from ServiceNow to ticket: Map ServiceNow fields (left) to SecOps ticket fields (right) for syncing ServiceNow updates to SecOps tickets. This step also requires setting up a [ServiceNow webhook](https://help.zscaler.com/uvm/configuring-servicenow-outegration-webhook). Commonly mapped fields include Ticket Status and Ticket SLA.

The initial ServiceNow outegration mapping includes preconfigured default mappings for each part, based on common use cases and industry best practices. These defaults can be modified and customized as needed.

### Creating a New Mapping

In each of the three mapping components, you'll need to select a field on the right, and then configure the corresponding field value on the left.

To create a new mapping:

1. Select a field (right):
  1. Click**Mapping**.
  2. Select a field on the right. The field's schema details open on the right of the page. The schema lists available ServiceNow fields to be used during mapping. This is the list of fields configured in your ServiceNow table for the Table selected in the Settings step (e.g., Task). See image. The following details are specified for fields, when available:
    - Required
    - Input Type
    - Available Options
2. Configure the field value (left):
  1. Click **Add value** on the left. The **Field Editor** appears.
  2. In the **Field Editor**, select one of the following methods to configure the value of the field:
    - Field (Dictionary)
    - Smart Text
    - Script

Repeatthe mapping process for all required ServiceNow fields and for any other fields you want to map.

In addition to the mapping of fields on the right to fields on the left, you can perform a set of actions when setting up the ServiceNow outegration mapping, each relevant to a specific part of the mapping.

- Set a ticket field as mandatory.
- Add attachments to the ticket dispatch.
- Configure comments synchronization.

### Mapping Ticket Title to Short Description

To illustrate the mapping process, consider the mapping of the required ServiceNow Short Description field. The final result of the mapping process should show the Short Description field on the right, and the Ticket Title field on the left.

To map the Ticket Title field to the Short Description field:

1. Select **Short Description**as the ServiceNow field on the right. See image. Selecting the ServiceNow Short Description field opens the field's details in the schema. The schema specifies that the field is required and thus must be mapped before the outegration can be saved, and that the field expects a TEXT input type. Therefore, the field for which a value is being configured must also be of TEXT type. See image.
2. Select the **Ticket Title**field on the left: See image.
  1. Click**Add Value**.
  2. Under the **Field** tab, select the **Ticket Title** field, which is the equivalent to the ServiceNow **Short Description**field.

### Previewing the Ticket to ServiceNow Mapping

After completing the SecOps ticket to ServiceNow dispatch mapping, preview the mapping to review the configuration. This helps ensure that SecOps ticket dispatch is behaving as expected and that the ServiceNow ticket fields are populated correctly.

To preview the mapping, click **Preview**on the bottom right of the ticket initially dispatched to ServiceNow section. The Mapping Preview window appears. On the left of the Mapping Preview window, there is a sample of the SecOps tickets in your account, organized by ticket ID. You can select, filter, or search tickets and preview the mapping to their corresponding ServiceNow ticket. You can also open the actual SecOps ticket in a new tab for a more in-depth review.

See image.

### Common Mapping Examples

These mapping examples highlight commonly used field configurations in your outegration. While some might be preconfigured by default, Zscaler recommends reviewing and customizing them to ensure they align with your workflow.

- Ticket to ServiceNow Description
- Ticket SLA to ServiceNow Due Date Sync

[Image: snow outegration mapping right left]

The Required attribute is TRUE if a field is required by ServiceNow. If a field is not required, the attribute is not displayed. A required ServiceNow field is also indicated by a red asterisk (*) on the ServiceNow field in the first mapping step.

Required ServiceNow fields must be mapped before saving the outegration.

The Input Type specifies the data type of the ServiceNow field, such as TEXT (e.g., Short Description), DATE (e.g., Due Date), or NUMBER (e.g., Reassignment Count). This indicates the format that the selected source field must match in order to successfully map to the ServiceNow field.

For ServiceNow fields with fixed values, the Available Options column displays the available values. For example, if the ServiceNow field Priority is configured to include the following fixed values—1 - Critical, 2 - High, 3 - Moderate, 4 - Low, None—the corresponding values in the Ticket Severity field can be mapped to these values.

Select a field on the left to populate the field on the right.

#### Dictionary

The field dictionary allows you to create mappings between specific values from the field on the right and values of the field on the left. To use the dictionary, you must first select a field on the right and then a field to populate it with on the left.

For example, if your ServiceNowPriority field includes the following fixed values—1 - Critical, 2 - High, 3 - Moderate, 4 - Low, None—you can use the dictionary to map the corresponding Ticket Severity values to each of the Priority field values.

See image.

Configure the field value using free text, or create a template using a combination of free text and selected fields. This allows you to dynamically insert specific field values (e.g., Ticket SLA, Ticket Assignee, or Asset Name) into customized free text sentences or paragraphs.

To add a Smart Text field, enclose it in double curly brackets (e.g., `{{Ticket Assignee}}`.) The field's display name automatically translates to its system name.

This option is commonly used to configure the value of fields like Ticket Title and Ticket Description.

See image.

For use cases that require more advanced configuration than either of the two methods above, you can use Python scripts to configure the source field value to be mapped to the target field.

When dispatching tickets to ServiceNow, map the ServiceNow Description field with a summary of the Ticket content to provide remediation teams with a brief overview of the ticket.

To configure the Ticket to ServiceNow Description mapping:

1. Click**Mapping**.
2. Select**Description** as the field on the right.
3. Click **Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Smart Text**.
5. Enter a ticket description, including dynamic fields (e.g., `{{SLA}}`).

Tickets dispatched to ServiceNow will now include the configured description.

In the Ticket to ServiceNow sync, map the ServiceNow Due Date field to keep timelines in sync with Ticket SLA changes.

To configure the Ticket SLA to ServiceNow Due Date mapping:

1. Click**Mapping**.
2. Select**Due Date** as the field on the right.
3. Click**Add Value** on the left. The **Field Editor** appears.
4. In the **Field Editor**, select **Field**, and select **Ticket SLA** as the field on the left.

Ticket SLA changes will now automatically update ServiceNow ticket due dates.

You can set a SecOps ticket field as mandatory, by selecting the Set as Mandatorycheckbox in the Column Menu to the right of the mapping. Some fields can be set as mandatory by default.

See image.

Setting a field as mandatory guarantees that critical fields (e.g., Ticket Assignee) are always populated before a ticket is dispatched to ServiceNow, so ServiceNow tickets are always actionable for your remediation teams. Attempts to dispatch a ticket without a value in a mandatory field will trigger an error message.

Required fields are defined by your ServiceNow schema, whereas Mandatory fields are mandatory for SecOps ticket dispatch.

You can create a file attachment that summarizes your ticket content and set the trigger to automatically add it to your ServiceNow ticket. Adding an attachment to your ServiceNow ticket simplifies the review and management of findings dispatched from a ticket.

You can configure file attachments in two of the mapping steps:

- Initial ticket dispatch See image.
- Sync from Ticket to ServiceNow See image.

When configured in the Ticket to ServiceNow sync section, the attachment is included in the ServiceNow ticket alongside existing attachments as a downloadable file in the selected format.

Use the **File Format** drop-down menu on the top left of the attachment page to select from the available formats (**CSV**, **PDF**, **JSONL**, **Excel**).

See image.

To provide your ServiceNow remediation teams with a comprehensive view of the findings in the ticket, consider including the following fields in your attachment:

- Recommended Attachment Fields

- Finding Severity
- Finding Title
- Finding CVE
- Component Name
- Asset Name
- Asset Operating System
- Finding Optimal Fix
- Finding Description
- Finding Sources

In the Ticket to ServiceNow sync step, you can configure how ticket comments are synchronized with ServiceNow ticket comments. To configure comments, click **Comment Sync**.

See image.

### Sync Comments

Enable **Sync Comments**to automatically push comments from the ticket's Comments tab to the corresponding ServiceNow ticket.

See image.

### Trigger Comments

Enable **Sync Trigger Comments**and set conditions to trigger a comment when specific fields are modified. Syncing trigger comments is useful when you want to be notified of important changes to tickets without updating the corresponding ServiceNow ticket. For example, you can configure a trigger to post a comment in ServiceNow when the Ticket Severity changes from Medium to Critical.

See image.

To add a trigger condition:

1. Select the field you want to monitor (e.g., **Severity**).
2. Set the value change that should trigger the comment:
  1. **From**: Select the original value.
  2. **To**: Select the updated value.

When the specified change occurs in the ticket, a comment is automatically created and added to the ServiceNow ticket. The following is an example of a trigger comment:

```
Linked UVM ticket updated:
Ticket severity changed from: MEDIUM to: CRITICAL
<URL to ticket>
```

The ServiceNow outegration webhook enables automatic syncing of ServiceNow ticket updates (e.g., Status or SLA changes) to their corresponding SecOps tickets, reducing the need for manual changes. This step is required when configuring the ServiceNow to SecOps ticket mapping to keep the tickets in the two systems in sync. To learn more, see [Configuring the ServiceNow Outgeration Webhook](https://help.zscaler.com/uvm/configuring-servicenow-outegration-webhook).

A ServiceNow webhook is only needed to sync updates from ServiceNow to the ticket. It is not required for the initial ticket dispatch or for syncing updates from the ticket to ServiceNow.

When the outegration setup is complete, you can begin dispatching SecOps tickets using the Create ServiceNow Ticket button that appears in the Create Ticket menu within individual tickets, as well as in the Create Issue menu in the Tickets View. To learn more, see [Creating & Managing Third Party Tickets](https://help.zscaler.com/uvm/creating-managing-third-party-tickets).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-servicenow-outegration-webhook","lastmod":"2026-08-18T02:09Z","nid":"1528051"} -->
## Configuring the ServiceNow Outegration Webhook

- Source: https://help.zscaler.com/uvm/configuring-servicenow-outegration-webhook
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the ServiceNow Outegration Webhook
- Last modified: 2026-08-18T02:09Z
- Summary: How to configure the ServiceNow outegration webhook for bidirectional sync when setting up the ServiceNow outegration.

The ServiceNow outegration webhook enables automatic syncing of ServiceNow ticket updates such as Status or SLA changes to their corresponding SecOps tickets, reducing the need for manual changes. This step is required when configuring the ServiceNow to SecOps ticket mapping to keep the tickets in the two systems in sync. To learn more, see [Configuring the ServiceNow Outegration](https://help.zscaler.com/uvm/configuring-servicenow-outegration).

See image.

## Prerequisite

To set up the ServiceNow webhook, you need Zscaler API token credentials (i.e., Client ID and Client Secret) and your account ID. To obtain these, submit a support ticket in the SecOps platform requesting the necessary credentials.

## Configuring the ServiceNow Webhook

To set up your ServiceNow webhook:

1. In the ServiceNow console, go to **Activity Subscription**>**Business Rules**. See image.
2. Click **New**. The **Business Rule** page appears.
3. On the **Business Rule** page:
  1. **Name**: Enter a namefor the business rule.
  2. **Table**: From the drop-down menu, select one of the following ServiceNow tables to trigger the platform on update:
    - For the **Incidents** table, select **Incident [incident]**.
    - For the **Requests** table, select **Requested Item [sc_req_item]**.
    - For the **Exceptions** table, select **Policy Exception [sn_compliance_policy_exception]**.
    - For any other table, select the relevant table name based on your use case.
  3. Select the **Advanced** checkbox. See image.
  4. On the **When to run**tab: See image.
    - From the **When** drop-down menu, select **after**.
    - Select the **Update** and **Delete** checkboxes.
  5. On the **Advanced**tab, copy and paste the following script: Make the following changes to the script:
    - See script.
    - In the `getAvalorJWT()` function, replace the `<Client ID>`and`<Client Secret>` variables that appear in red.
    - In the `sendWebhookRequest()` function, replace the `<Account ID>` variable that appears in red. You can find your Account ID in your platform URL, or contact Zscaler Support for assistance.
4. Click **Submit**.

After your webhook is set up, configured triggers for field updates in your ServiceNow outegration mapping automatically sync changes made to ServiceNow tickets with their corresponding SecOps tickets.

```
function getAvalorJWT(){
   var url='
https://auth.us01.app.avalor.io/oauth2/token
';
var restMessage = new sn_ws.RESTMessageV2();
   restMessage.setEndpoint(url);
   restMessage.setHttpMethod('POST');
   restMessage.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
   restMessage.setRequestHeader('Accept', 'application/json');
   var payload = "grant_type=
client_credentials
&client_id=
<Client ID>
&client_secret=
<Client Secret>
";
   restMessage.setRequestBody(payload);
   var response = restMessage.execute();
   var responseBody = response.getBody();
   var statusCode = response.getStatusCode();
   if (statusCode !== 200) {
       gs.error('Failed to get access. Status code: ' + statusCode + ', Response body: ' + responseBody);
   }
   var jsonResponse = JSON.parse(responseBody);
   return jsonResponse.access_token;
}
function sendWebhookRequest(sysId, displayKey, changedFields, isDeleted) {
   var jwt = getAvalorJWT();
   var url = 'https://webhook.avalor.io/integration/inbound/
<Account ID>
/servicenow';
   var restMessage = new sn_ws.RESTMessageV2();
   var payload = {};
   payload.sysId = sysId;
   payload.displayKey = displayKey;
   payload.changedFields = changedFields;
   payload.isDeleted = isDeleted;
   restMessage.setEndpoint(url);
   restMessage.setHttpMethod('POST');
   restMessage.setRequestHeader('Content-Type', 'application/json');
   restMessage.setRequestHeader('Authorization', 'Bearer ' + jwt);
   restMessage.setRequestBody(JSON.stringify(payload));
   var response = restMessage.execute();
   var responseBody = response.getBody();
   var statusCode = response.getStatusCode();
   if (statusCode !== 200) {
       gs.error('Failed to send webhook request. Status code: ' + statusCode + ', Response body: ' + responseBody);
   }
}
(function executeRule(current, previous /*null when async*/ ) {
   if (current.operation() === 'delete') {
       sendWebhookRequest(current.sys_id.getDisplayValue(), current.number.getDisplayValue(), {}, true);
   }
   if (current.operation() === 'update') {
       var changedFields = {};
       var fieldNames = current.getFields().toArray().map(function(field) {
           return field.getName();
       });
       fieldNames.forEach(function(fieldName) {
           if (current.getValue(fieldName) != previous.getValue(fieldName)) {
               changedFields[fieldName] = current.getDisplayValue(fieldName);
           }
       });
       sendWebhookRequest(current.getValue('sys_id'), current.getValue('number'), changedFields, false);
   }
})(current, previous);
```
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-severity-categories","lastmod":"2026-08-09T05:59Z","nid":"1534128"} -->
## Configuring Severity Categories

- Source: https://help.zscaler.com/uvm/configuring-severity-categories
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Configuring Severity Categories
- Last modified: 2026-08-09T05:59Z
- Summary: How to assign severity score to severity categories for findings in the Zscaler SecOps UVM application.

Severity levels are categorical values assigned to findings based on their severity score. In Zscaler Unified Vulnerability Management (UVM), you can customize severity categories by mapping severity score ranges to the categories. The configured severity categories can then be used in dashboards (e.g., [Remediation History dashboard](https://help.zscaler.com/uvm/viewing-remediation-history-dashboard), [Risk dashboard](https://help.zscaler.com/uvm/viewing-risk-dashboard), and [custom dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards)), [prioritization workflows](https://help.zscaler.com/uvm/updating-ticket-workflows), and [setting up service level agreement (SLA) settings](https://help.zscaler.com/uvm/configuring-service-level-agreement-settings). Proper severity settings ensure that remediation teams can efficiently focus on high-risk vulnerabilities while aligning their processes with organizational or compliance requirements.

The scores mapped to severity categories are configured on the Score Settings page. These numerical values are derived from external sources and organizational risk factors. Each finding is assigned a score, which determines its corresponding severity category based on the configuration in Severity Settings. To learn more, see [Understanding Severity Score](https://help.zscaler.com/uvm/understanding-severity-scores) and [Configuring Severity Scores](https://help.zscaler.com/uvm/configuring-severity-scores).

For access to severity settings, your assigned role must include the Read, Create, and Edit permissions under the Vulnerabilities App - Ticket Settings resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles).
See image.

To configure severity categories:

1. In the **Vulnerabilities**app, go to **Settings**> **Severity & SLA**. See image.
2. Click **Severity Settings**. The **Severity Settings**page appears. If severity is configured in the Data Model, you'll be prompted to unlink and override to configure severity settings on the Severity Settings page.
3. For each severity category (i.e., **Critical**, **High**, **Medium**, **Low**, **Info**): See image.
  1. **Score starts at**: Input the minimum score where the severity category starts. The minimum score is included in the category.
  2. **Score is less than**: Define the maximum score as an optional cutoff. The maximum score is excluded from the category. Every score must be included in a severity category to ensure complete coverage. The settings cannot be saved if there are gaps between ranges that are not assigned to a category. Additionally, severity ranges must be unique (non-overlapping) and ordered in ascending numerical value.
4. Use the **Severity Range Preview**slider to review your score mapping visually and ensure the entire score range (e.g., 0 to 10) is fully accounted for.
5. Save the settings in one of the following ways: See image.
  - Click **Save**. The severity settings apply the next time the finding or the ticket entity is processed.
  - In the **Save**drop-down menu, click **Save & Run** to save the settings and immediately apply them in your account.

While severity categories are assigned to findings, they also impact related entities. For example, a ticket's severity is derived from the severity categories of the findings it contains (e.g., if a ticket includes a Critical finding, it's also categorized as Critical).

You can use these severity categories in setting up SLA rules to correlate remediation expectations with the defined severity levels. To learn more, see [Configuring Service Level Agreement Settings](https://help.zscaler.com/uvm/configuring-service-level-agreement-settings).

[Image: Vulnerabilities App Ticket Settings Resource Permissions]

[Image: Vulnerabilities Settings Severity & SLA Page]

[Image: Mapping Score Ranges to Severity Categories]

[Image: Severity Settings Save Drop-down Menu]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-severity-scores","lastmod":"2026-08-09T05:59Z","nid":"1527921"} -->
## Configuring Severity Scores

- Source: https://help.zscaler.com/uvm/configuring-severity-scores
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Configuring Severity Scores
- Last modified: 2026-08-09T05:59Z
- Summary: How to configure severity score settings for UVM findings.

Severity scores are numerical values assigned to security findings that are used to evaluate their associated risk. These scores help security teams effectively allocate resources and make informed decisions, ensuring accurate risk prioritization, streamlined remediation, and immediate attention to critical vulnerabilities.

Within Zscaler's Unified Vulnerability Management (UVM) app, you can customize how severity scores are calculated for findings. This customization uses external scores and configurable risk and mitigating factors, which provide additional context (e.g., information about the asset associated with the finding). To learn more about severity scores, see [Understanding Severity Scores](https://help.zscaler.com/uvm/understanding-severity-score).

Severity scores can also be mapped to severity categories, which can in turn be used in SLA Settings to classify and prioritize findings and tickets based on risk. To learn more, see [Configuring Severity Categories](https://help.zscaler.com/uvm/configuring-severity-categories)and[Configuring Service Level Agreement Settings](https://help.zscaler.com/uvm/configuring-service-level-agreement-settings).

Admins can configure UVM score settings. If you do not have admin access but require access to score settings, contact your Zscaler Account team or Zscaler Support.

## Configuring the Severity Score

Setting up severity scores involves defining and customizing the base score and risk and mitigating factors to accurately assess the risk level of findings.

Zscaler recommends keeping a balance of 60% for the base score and 40% for the mitigating score.

### Setting the Base Score

The base score is an initial value typically calculated from the external scores of a finding. It serves as the primary reference point for the overall severity calculation and carries the most weight in determining the final score. You can select the metrics to include in the base score and configure their weights.

The base score is most commonly configured to include a combination of the finding's CVSS and EPSS scores, which are typically a decimal value between zero and one. To standardize these scores, they are converted to a value on a scale from 1 to 10 by multiplying the decimal value by 10.

To set the base score:

1. In the **Vulnerabilities**app, go to **Settings**> **Score**. If score settings is configured in the Data Model, you'll be prompted to Unlink & Override to configure score settings on the Score page.
2. In the **Base Score** section:
  1. Select the relevant fields to be used for the base score. The default factors are **CVSS**, **EPSS**, and **Original Severity Score** received from a scanner.
  2. Assign a percentage to each base score factor to define its contribution to the total score.

To add a factor to the base score:

1. Click **Add Factor**. The**Add New Factor**drawer appears.
2. In the **Add New Factor**drawer:
  1. **Factor Name**: Enter a name for the factor.
  2. **Field**: Select a field from the drop-down menu. Available fields are related to assets, vulnerabilities, or findings. Zscaler recommends using fields with numerical values.
  3. **Share of total score**: Set the percentage of this new factor's weight from the total score.
3. Click **Apply**.

If all base score factors are null, the severity score is also null. The weighted base score is calculated as the sum of each base factor multiplied by its dynamically adjusted weight, producing a standardized score on a 0 to 10 scale. This weighted base score serves as the anchor score, to which risk and mitigating factors add or subtract points subject to capping constraints.

### Setting the Risk and Mitigating Factors

Risk and mitigating factors provide additional organizational context that can adjust a finding's score based on their assigned weights. Configuring risk and mitigating factors involves:

1. Identifying the key risk and mitigating factors relevant to your organization. You can add as many risk or mitigating factors as needed.
  - Risk Factors: These increase the severity score by accounting for additional vulnerability context. For example, if an asset contains Personally Identifiable Information (PII), the likelihood of it being targeted is higher, raising its severity.
  - Mitigating Factors: These decrease the severity score by accounting for protective measures in place. For example, an asset with a firewall is less exposed to threats, reducing its severity score.
2. Assigning a weight to each factor based on its significance to your organization. Factor weights can be adjusted to reflect the specific context and priorities of your environment. For example, if PII exposure is critical to your risk model, you can assign a higher weight to that risk factor.

To add risk and mitigating factors to the score:

1. In the **Risk & Mitigating Factors** section, click **Add Factor**. The**Add New Factor**drawer appears.
2. In the **Add New Factor**drawer:
  1. **Factor Type**: Select **Risk Factors** or **Mitigating Factors**.
  2. **Factor Name**: Enter a name for the factor.
  3. **Field**: Select a field from the drop-down menu. Available fields are related to assets, vulnerabilities, or findings. Zscaler recommends using fields with numerical values.
  4. **When**<Field Name>**Equals**: Configure the numerical value of each factor depending on the factor field type.
    - For Boolean fields, enter the percentage weights for **True**, **False**, and **Else**.
    - For string fields, enter the expected field values and enter a percentage weight for each.
  5. Click **Apply**.

The weight assigned to risk and mitigating factors is capped based on the percentage remaining after the base score's weight is set. For example, if the base score is assigned 60% of the total score, the combined weight of all risk and mitigating factors is capped at the remaining 40%. In the following image, the 170% represents the total contribution of risk and mitigating factors before capping, while the 40% reflects their adjusted share within the total score. This ensures the combined weight of the base score and the factors always adds up to 100%.

See image.

#### Using the Original Severity Score as Fallback

The Original Severity Score is a base score factor derived from external vendors, with each vendor employing its own unique method of calculation. These vendor-specific methods are not detailed or incorporated into the UVM score calculation.

- If the Original Severity Score is included in the base score as a factor and assigned a weight of 0%, it receives the full weight as a fallback when all other base score factors are null.
- If the Original Severity Score is not included in the base score and all other factors are null, no weights are reassigned to it.

#### Factor Field Type

Factors can be configured with either Boolean fields or string fields. The factor's field type dictates how a factor is configured and how it contributes to the overall risk calculation.

- Boolean Factors
- String Factors

Boolean factors represent entity attributes that are either true or false (e.g., Asset Has PII, Is Crown Jewel).

- Boolean risk factors increase the finding's score if true and reduce or leave the score unchanged otherwise (if false or null).
- Boolean mitigating factors reduce the finding's score if true and increase or leave the score unchanged otherwise (if false or null).

String factors represent entity attributes that can have a gradual effect (e.g., the User Prone to Phishing field with the values Low, Medium, High, Severe). For string factors, you can set the effect that each value has on the score (e.g., Low - don't change, Medium - increase by 5%, High - increase by 10%, Severe - increase by 20%).

When a string factor's returned value meets the Else condition, the weight of the factor is proportionally redistributed among the remaining factors. If no other factors are present, the weight of the factor is proportionally distributed to the base factors.

## Using the Score Simulator

You can use the score simulator to validate severity scores by modeling the impact of different factors on the scoring system.

See image.

To use the score simulator:

1. In the **Vulnerabilities**app, go to **Settings**> **Score**.
2. In the **Score Simulator** section on the left:
  1. **Base Score Factors**: Enter a numerical value for all factors.
  2. **Risk Factors**: Select a value for each drop-down menu (e.g., **True**, **False**, **Else**). If no value is selected, the factor is counted according to the Else condition.
3. Click **Calculate Score**.

[Image: risk and mitigating factors 170 to 40]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-sftp-connector","lastmod":"2026-04-07T07:06Z","nid":"1534172"} -->
## Configuring the SFTP Connector

- Source: https://help.zscaler.com/uvm/configuring-sftp-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the SFTP Connector
- Last modified: 2026-04-07T07:06Z
- Summary: How to configure the SFTP connector, including setting up authentication and data retrieval filters and specifications.

The SFTP stream allows you to retrieve your files from any remote server that supports SFTP.

## Prerequisites

To configure the SFTP connector, you need the source authentication configuration. The following parameters are required:

- Username
- Password
- Host

## Configuring the Connector

To create the SFTP data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the SFTP data source, enter the following information in the source setup Retrieval section:

- Authentication
- Files Type
- Folder Path
- Start Date
- Files Pattern (Regex)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

A valid username associated with the host server.

A valid password associated with the user account on the host server.

The hostname or IP address associated with the host server.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Sftp Authentication** window appears.
2. In the **Sftp Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

From the drop-down menu, select CSV or JSON format.

In the field, enter the path to the specific folder you want to retrieve. If left empty, all relevant files are retrieved. For example, entering `reports/2023` only retrieves files from that specific folder. Files from the `reports/2024` or personnel folders are not retrieved.

Enter a start date for the data retrieval in the `YYYY-MM-DDT00:00:00Z` format.

Enter the files that the connector filters to monitor or ingest data.

[Image: The SFTP tile in the SecOps platform]

[Image: The SFTP Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-snyk-connector","lastmod":"2026-08-18T01:55Z","nid":"1530957"} -->
## Configuring the Snyk Connector

- Source: https://help.zscaler.com/uvm/configuring-snyk-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Snyk Connector
- Last modified: 2026-08-18T01:55Z
- Summary: How to configure the Snyk connector, including setting up authentication and data retrieval filters and specifications.

Snyk is a platform that helps developers find and fix vulnerabilities in open-source libraries and containers integrated into their workflows.

There are two available Snyk streams. To learn more, see [Zscaler UVM and Snyk Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-snyk-deployment-guide).

## Prerequisites

To configure the Snyk connector, you need the source authentication configuration. The following parameters are required:

- API Token
- Org ID

## Configuring the Connector

To create the Snyk data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Snyk data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Snyk Authentication** window appears.
2. In the **Snyk Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

To learn more, see [Zscaler UVM and Snyk Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-snyk-deployment-guide).

To learn more, see [Zscaler UVM and Snyk Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-snyk-deployment-guide).

[Image: The Snyk SAST and Snyk tiles]

[Image: The Snyk Authentication window displaying the Name, Token, and Org Id fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-tanium-connector","lastmod":"2026-04-07T07:06Z","nid":"1534182"} -->
## Configuring the Tanium Connector

- Source: https://help.zscaler.com/uvm/configuring-tanium-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Tanium Connector
- Last modified: 2026-04-07T07:06Z
- Summary: How to configure the Tanium connector, including setting up authentication and data retrieval filters and specifications.

Tanium helps you secure your endpoint devices with the Converged Endpoint Management (XEM) platform.

To learn more, see [Zscaler and Tanium Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-tanium-deployment-guide).

## Prerequisites

To configure the Tanium connector, you need the source authentication configuration. The following parameters are required:

- API Key
- Domain
- Roles and Permissions

## Configuring the Connector

To create the Tanium data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Tanium data source, enter the following information in the source setup Retrieval section:

- Authentication
- Sensors

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To learn more, see [Zscaler and Tanium Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-tanium-deployment-guide).

To learn more, see [Zscaler and Tanium Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-tanium-deployment-guide).

To learn more, see [Zscaler and Tanium Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-uvm-and-tanium-deployment-guide).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Tanium Authentication** window appears.
2. In the **Tanium Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

In the field, optionally enter the sensor names. A sensor is a script that runs on endpoints to gather specific data in response to a Tanium question. To add multiple sensors, press `Enter` after each entry. To learn more, refer to the [Tanium documentation](https://help.tanium.com/bundle/ug_console_onprem/page/platform_user/authoring_sensors.html).

[Image: The Tanium tiles in the SecOps platform]

[Image: The Tanium Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-tenable-nessus-connector","lastmod":"2026-07-31T07:06Z","nid":"1528381"} -->
## Configuring the Tenable Nessus Connector

- Source: https://help.zscaler.com/uvm/configuring-tenable-nessus-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Tenable Nessus Connector
- Last modified: 2026-07-31T07:06Z
- Summary: How to configure the Tenable Nessus connector, including setting up authentication and data retrieval filters and specifications.

Tenable Nessus scans for security vulnerabilities in devices, applications, operating systems, cloud services, and other network resources.

The Tenable Nessus connector retrieves vulnerability findings, including CVE identifiers, severity, vulnerability details, plugin information, and affected asset information. Each record represents a vulnerability finding on a specific asset. If a finding includes multiple CVEs, the connector creates a separate record for each CVE.

## Prerequisites

The Tenable Nessus source authentication configuration requires an API key. An API key consists of an access key and a secret key. The user account associated with the API key must have the following roles and permissions to retrieve vulnerability data:

- User Role: Basic [16] or higher.
- Access Control: Can View for the specific asset objects being exported. Administrator [64] users can export data without explicit Can View access control permissions.

To learn more, see the [Tenable documentation](https://developer.tenable.com/docs/access-control).

To create an API key:

1. Log in to Tenable Nessus.
2. From the top navigation bar, click **Settings**. You are redirected to the **About**page.
3. From the left-side navigation, click**My Account.**
4. Select the **API Keys** tab.
5. Click **Generate**. A window appears, confirming your selection to generate a new API key.
6. Click **Generate** again.
7. Copy the generated API key. Save the API key securely as you cannot copy or view it later.

## Configuring the Connector

To create the Tenable Nessus data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Tenable Nessus data source, enter the following information in the source setup Retrieval section:

- Authentication
- Number of days to fetch (Optional)

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Nessus Authentication** window appears.
2. In the **Nessus Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Enter the number of days to retrieve data for during each run. For example, enter `7` to fetch data from the past 7 days. By default, the connector retrieves data from the last day if no value is specified.

[Image: Nessus connector tile]

[Image: Nessus Authentication window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-tenable-outegration","lastmod":"2026-04-11T07:06Z","nid":"1535317"} -->
## Configuring the Tenable Outegration

- Source: https://help.zscaler.com/uvm/configuring-tenable-outegration
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Outegration Configuration Guides > Configuring the Tenable Outegration
- Last modified: 2026-04-11T07:06Z
- Summary: How to configure the Tenable Vulnerability Management outegration for triggering Tenable Vulnerability Management scans on SecOps assets to update their vulnerability data.

The Tenable scanner outegration allows you to [trigger on-demand scans](https://help.zscaler.com/uvm/triggering-asset-scans-uvm) for specific assets directly from the Zscaler Security Operations (SecOps) platform. This enables you to refresh vulnerability findings immediately after a remediation is applied, ensuring your asset data reflects the most up-to-date status without waiting for the next scheduled ingestion.

Both Tenable Vulnerability Management (formerly Tenable.io) for cloud-based scanning and Tenable Security Center for on-premises scanning are supported.

This article explains how to set up the Tenable Vulnerability Management scanner outegration and the Tenable Security Center scanner outegration. The process involves setting up authentication, outegration visibility in the platform, and outegration mapping.

The Tenable scanner outegration is currently supported only for the Zscaler Unified Vulnerability Management (UVM) app.

## Prerequisites

Before getting started, identify your Tenable deployment type (Tenable Vulnerability Management or Tenable Security Center) and ensure that the corresponding data source is configured.

For access to the Tenable outegration, your assigned role must include the Create and Edit permissions under the Vulnerabilities App - Asset Outegrations resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles).
See image.

### Configuring the Data Source

The scanner outegration can only trigger scans on assets that are already managed in the platform. You must have the corresponding data source configured and actively ingesting assets.

- For Tenable Vulnerability Management: Ensure the Tenable Vulnerability Management connector is configured and active. To learn more, see [Configuring the Tenable Vulnerability Management Connector](https://help.zscaler.com/uvm/configuring-tenable-vulnerability-management-connector).
- For Tenable Security Center: Ensure the Tenable Security Center connector is configured and active. To learn more, see [Configuring the Tenable Security Center Connector](https://help.zscaler.com/uvm/configuring-tenable-security-center-connector).

### Retrieving the Authentication Parameters

Retrieve the required authentication parameters based on your Tenable deployment type, and enter them in the corresponding fields during the Connect step of the outegration setup wizard.

You can reuse the authentication credentials from your Tenable source connector for this outegration. However, while the source connector requires only View permissions, the scanner outegration requires Scan permissions. Ensure the associated Tenable user has the necessary access to create and launch scans.

- Tenable Vulnerability Management
- Tenable Security Center

Obtain an access key and secret key associated with a Tenable Vulnerability Management user account. This user must have permissions to create and launch scans on the target assets. To learn more, see [Configuring the Tenable Vulnerability Management Connector](https://help.zscaler.com/uvm/configuring-tenable-vulnerability-management-connector).

Obtain the following required parameters for the Tenable Security Center outegration:

- URL
- Access Key and Secret Key
- Gateway

The base URL of your Tenable Security Center instance (e.g., `https://tenable.acme.com`).

The API keys associated with a Tenable Security Center user account. This user must have permissions to create and launch scans. To learn more, see [Configuring the Tenable Security Center Connector](https://help.zscaler.com/uvm/configuring-tenable-security-center-connector).

If you require a gateway connection for setting up the Tenable Security Center outegration, submit a support ticket to request the configuration of your gateway. To learn more, see [Configuring the Zscaler SecOps Platform Gateway](https://help.zscaler.com/uvm/configuring-zscaler-secops-platform-gateway).

## Creating the Tenable Outegration

To configure the Tenable outegration, complete the following steps:

- Step 1: Authenticate the Connection (Connect)
- Step 2: Configure Scan Behavior (Settings)
- Step 3: Map the Outegration Fields (Mapping)

The first step in setting up your Tenable outegration is to authenticate using valid credentials to establish a secure connection with your Tenable instance. With the required parameters retrieved in the prerequisites, you can begin the outegration setup in the SecOps platform.

To create an outegration:

1. In the SecOps platform, go to **Configure**> **Outegrations**.
2. Click **Create**, then search for and select the **Tenable Vulnerability Management**or **Tenable Security Center**tile, depending on your organization's deployment. See image. The **Connect**step appears.
3. In the **Details**section: See image.
  1. **Display Name**: Enter a name for your outegration.
  2. **Active**: Enable to activate the outegration.
  3. **Authentication**: Select an existing authentication profile, or click **Create New**to set up a new authentication and enter the required parameters you retrieved earlier into the corresponding fields.
  4. **Skip SSL**: (Tenable Security Center only) Select to skip SSL certificate validation. This is generally recommended only for testing environments.
  5. **Gateway**:(Tenable Security Center only) If you have a Zscaler gateway configured for this connection, select it from the drop-down menu.
4. Click **Test**in the bottom-right corner of the page to verify the connection. Invalid credentials trigger error messages to assist with troubleshooting connectivity issues.
5. After the test passes, click **Next**to advance to the **Settings**step.

In the Settings step of the outegration setup wizard, configure how the scanner behaves when an on-demand scan is triggered. The available fields vary based on your Tenable deployment type.

- Tenable Vulnerability Management
- Tenable Security Center

Click **Map**to advance to the Mapping step.

For the Tenable Vulnerability Management settings, configure the following:

- **Scan Name**: (Optional) Enter a custom name for scans triggered by this outegration. If left blank, a system default name is used.
- **Max Number of Parallel Scans**: Select the maximum number of scans allowed to run concurrently (the default is set to 1). Limiting parallel scans helps prevent API rate limiting or performance impacts on your scanner.
- **Scan Template**: Select the Tenable scan template to use (e.g., **Basic Network Scan**). This list is dynamically populated from your connected Tenable instance.
- **Folder Name**: (Optional) Enter the name of the folder in Tenable where the scan will be listed. If left blank, the scan is placed in the main folder.

See image.

For the Tenable Security Center settings, configure the following:

- **Scan Name**: (Optional) Enter a custom name for scans triggered by this outegration.
- **Max Number of Parallel Scans**: Select the maximum number of scans allowed to run concurrently (the default is set to 1). Limiting parallel scans helps prevent API rate limiting or performance impacts on your scanner.
- **Scan Policy**: Select the scan policy to use. This list is dynamically populated from your connected Tenable instance.
- **Repository**: Select the repository where scan results should be stored.
- **Proxy DNS**: (Optional) Enable if a DNS proxy is required for the connection.

The third step in setting up your Tenable outegration is verifying the field mapping between the SecOps platform and the Tenable scanner. This defines how asset data from the SecOps platform is translated into a scan request for Tenable. Specifically, it determines which identifier (e.g., IP address, Hostname) the platform sends to Tenable to ensure the correct assets are targeted for the scan. The main objective of the mapping process is to ensure Tenable receives the correct address format to locate and scan the selected assets.

The Tenable outegration includes a default mapping designed to work for most network environments. The default mapping assumes that IP addresses are the most reliable way to target assets for vulnerability scanning. When you trigger a scan on a set of assets in the SecOps platform, the platform extracts the IP addresses of those assets and passes them to Tenable as a list of targets.

Review the default mapping configuration and ensure the asset identifiers (e.g., IP Address) are correctly mapped to the target field in Tenable.

While the default mapping uses IP addresses, you can customize this if your environment requires scanning by a different field (e.g., you can configure the mapping to use tags for organizing and targeting assets instead of relying solely on IP addresses).

When the mapping is verified, click **Finish**to complete the setup.

See image.

When the outegration is configured, you can launch targeted scans directly from assets in the SecOps platform. This allows you to immediately update specific assets after applying patches or remediation. To learn more, see [Triggering Asset Scans in UVM](https://help.zscaler.com/uvm/triggering-asset-scans-uvm).

[Image: Asset Outegration Create and Edit permissions in the Vulnerabilities App]

[Image: Create button on the Outegrations page]

[Image: Details section on the Connect step]

[Image: Tenable outegration Settings page]

[Image: Tenable outegration default mapping]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-tenable-security-center-connector","lastmod":"2026-07-05T07:06Z","nid":"1528201"} -->
## Configuring the Tenable Security Center Connector

- Source: https://help.zscaler.com/uvm/configuring-tenable-security-center-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Tenable Security Center Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Tenable Security Center connector, including setting up authentication and data retrieval filters and specifications.

Tenable Security Center provides a comprehensive and integrated view of enterprise security posture and allows you to accurately identify, investigate, and prioritize vulnerabilities.

There are two Tenable Security Center streams. Select those that are based on your Tenable Security Center feature plan and use cases:

- Tenable Security Center Vulnerabilities: Retrieves vulnerabilities across the scanned assets broken down by CVE.
- Tenable Security Center Assets: Retrieves data related to assets scanned by Tenable (e.g., endpoints, servers, network devices, and web app)

For Tenable Vulnerability Management, see [Configuring the Tenable Vulnerability Management Connector](https://help.zscaler.com/uvm/configuring-tenable-vulnerability-management-connectors).

To learn more, see [Zscaler UVM and Tenable Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-tenable-deployment-guide).

## Prerequisites

To configure the Tenable Security Center connector, you need the source authentication configuration. The following parameters are required:

- URL
- Access Key
- Secret Key

## Configuring the Connector

To create the Tenable Security Center data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Tenable Security Center data source, enter the following information in the source setup Retrieval section:

- Authentication
- Severity Category

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

The URL is the Tenable Security Center server URL and is required as part of the authentication.

The access key is an API key associated with a user account with the required permissions. To learn more, refer to the [Tenable documentation](https://docs.tenable.com/security-center/Content/EnableAPIKeys.htm).

After enabling API key authentication, you can generate the API access key and secret key. To learn more, see [Zscaler UVM and Tenable Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-tenable-deployment-guide).

You cannot view API secret keys after the initial generation. If you lose your existing secret key, you must generate new API keys. To learn more, refer to the [Tenable documentation](https://docs.tenable.com/security-center/Content/GenerateAPIKey.htm).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Tenable Security Center Authentication** window appears.
2. In the **Tenable Security Center Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Set the Severity Category drop-down menu filters and specifications. This menu allows the user to select the severity level of vulnerabilities to include in the scope of the ingested data.

This menu is available in the Tenable Security Center Vulnerabilities stream.

[Image: The Tenable Security Center Assets and Tenable Security Center Vulnerabilities tiles]

[Image: The Tenable Security Center Authentication window displaying the Name, Access Key, Secret Key, and Url in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-tenable-vulnerability-management-connector","lastmod":"2026-07-05T07:06Z","nid":"1528341"} -->
## Configuring the Tenable Vulnerability Management Connector

- Source: https://help.zscaler.com/uvm/configuring-tenable-vulnerability-management-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Tenable Vulnerability Management Connector
- Last modified: 2026-07-05T07:06Z
- Summary: How to configure the Tenable VM connector, including setting up authentication and data retrieval filters and specifications.

Tenable Vulnerability Management automatically discovers and assesses a user's environment for vulnerabilities, misconfigurations, and other cybersecurity issues.

There are three Tenable Vulnerability Management streams. Select those that are based on your Tenable Vulnerability Management feature plan and use cases:

- Tenable Vulnerability Management - Vulnerabilities: Retrieves vulnerabilities across the scanned assets broken down by CVE.
- Tenable Vulnerability Management - Issues: Retrieves vulnerabilities with no associated CVEs.
- Tenable Vulnerability Management - Assets: Retrieves data related to assets scanned by Tenable (e.g., endpoints, servers, network devices, or web apps).

The Issues and Vulnerabilities streams are identical, but the Issues stream does not include CVE information.

For the Tenable Security Center connector, see [Configuring the Tenable Security Center Connector](https://help.zscaler.com/uvm/configuring-tenable-security-center-connector).

To learn more, see [Zscaler UVM and Tenable Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-tenable-deployment-guide).

## Prerequisites

To configure the Tenable Vulnerability Management connector, you need the source authentication configuration. The access key and secret key parameters are required.

To generate an access key and secret key, create a user with the necessary permissions and generate the keys within that user account:

- Create a user.
- Generate the keys.

To create a user with the necessary permissions:

1. Log in to the Tenable platform.
2. Go to **Settings**>**Access Control**.
3. On the **Users** tab, click **Create User**. The **Create User** window appears.
4. In the **Create User** window:
  - **General**: Enter the primary user details.
  - **User Groups**: This field can be left empty.
  - **Permissions**:This field can be left empty. The user is assigned at least **Can View** privileges for **All Assets**.
    - **Role**: Select **Basic User** from the drop-down menu. To learn more, refer to the [Tenable documentation](https://docs.tenable.com/vulnerability-management/Content/Settings/access-control/TenableRolePrivileges.htm?_gl=1*1a7sb4v*_gcl_au*MTA5NTE4OTIzNC4xNzI2MzkwNTk2*_ga*NzYzOTc1NjYuMTcyNjM4OTc5NQ..*_ga_HSJ1XWV6ND*MTcyODM5OTc0NC4yLjEuMTcyODQwMDc4OS4zMC4wLjQzODIxNjY1Ng..). See image.
    - **API Key**: Enable to allow the user to authenticate through an API key.
    - **Username/Password**:Enable to allow the user to authenticate with a username and password. See image.
5. Click **Save**.
6. On the **Permissions**tab, click**Create Permission**. The **Create Permission** window appears.
7. In the **Create Permission** window: See image.
  - **Permission Name**:Enter a name for the permission.
  - **Users**: This field can be left empty.
  - **Groups**: This field can be left empty.
  - **Permissions**: Select **Can View**.
  - **Objects**: Select **All Assets**.
8. Click **Save**.

To learn more, see [Zscaler UVM and Tenable Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-tenable-deployment-guide).

## Configuring the Connector

To create the Tenable Vulnerability Management data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Tenable Vulnerability Management data source, enter the following information in the source setup Retrieval section:

- Authentication
- Split findings by Output
- Include Info-Level Severity Data
- Fetch vulnerabilities from the past selected days

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Troubleshooting and FAQs

The number of findings in Tenable does not match the data displayed in the SecOps platform because the discrepancy in vulnerability counts between Tenable and the SecOps platform stems from differences in vulnerability processing and aggregation. Tenable reports vulnerabilities at the plugin level, which includes a group of related CVEs. The SecOps platform decomposes each Tenable plugin into its constituent CVEs.

For example, consider this [Tenable plugin](https://www.tenable.com/plugins/nessus/141937). In Tenable, this plugin is treated as a single vulnerability. In the SecOps platform, it splits the plugin into the 48 distinct CVEs that comprise it. These 48 findings are then aggregated into a single ticket.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Tenable Vulnerability Management Authentication** window appears.
2. In the **Tenable Vulnerability Management Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

The Split findings by Output checkbox splits raw data records based on the output field, so that each resulting record contains a unique output.

This checkbox is available in the Tenable Vulnerability Management - Vulnerabilities and Tenable Vulnerability Management - Issues streams.

The Include Info-Level Severity Data checkbox allows you to include vulnerabilities with info-level severity in your data, which are otherwise automatically filtered out.

This checkbox is available in the Tenable Vulnerability Management - Vulnerabilities and Tenable Vulnerability Management - Issues streams.

The Fetch vulnerabilities from the past selected days field allows you to retrieve vulnerabilities that were last found by the vendor in the past input number of days. By default, data is retrieved from the past 30 days.

This field is available in the Tenable Vulnerability Management - Vulnerabilities and Tenable Vulnerability Management - Issues streams.

[Image: The Tenable Vulnerability Management tiles in the SecOps platform]

[Image: The Tenable Vulnerability Management Authentication window in the SecOps platform]

[Image: Selecting Basic User in the Tenable platform]

[Image: Enabling the API Key and Username/Password permissions in the Tenable platform]

[Image: The Create Permission window in the Tenable platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-ticket-workflows","lastmod":"2026-07-09T00:51Z","nid":"1534318"} -->
## Configuring Ticket Workflows

- Source: https://help.zscaler.com/uvm/configuring-ticket-workflows
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Configuring Ticket Workflows
- Last modified: 2026-07-09T00:51Z
- Summary: How to configure ticket workflows to automate ticket status transitions, lock ticket scopes, and dispatch tickets to external work management systems.

Ticket workflows in Zscaler Unified Vulnerability Management (UVM) allow you to automate and standardize the ticket lifecycle according to your organization's operational requirements. By defining rule sets for status transitions, locking ticket scopes, and dispatching tickets to external systems, you can ensure that ticket states remain synchronized with the real-time status of underlying findings and third-party work management tools. These workflows reduce manual administrative effort and maintain data integrity throughout the remediation process.

You can create and manage the list of available ticket statuses on the Ticket Statuses page. To learn more, see [Managing Ticket Statuses in UVM](https://help.zscaler.com/uvm/managing-ticket-status).

On initial setup, the Ticket Workflows page displays default system logic. You can customize these rules to align with your organization's remediation policies across three primary categories:

- **Ticket Status Management**: Automate transitions based on finding activity, such as auto-closing tickets when findings are no longer detected or reopening them if they resurface.
- **Lock Ticket Scope Triggers**: Define conditions to automatically lock a ticket's scope, preventing automated system logic from adding or removing findings after a ticket has been manually modified.
- **Auto-Dispatch to External Systems**: Synchronize workflows by automatically creating work items or alerts in third-party systems (e.g., Jira or ServiceNow) when specific criteria are met.

To learn more, see [About Tickets](https://help.zscaler.com/uvm/about-tickets) and [Viewing & Managing Tickets in UVM](https://help.zscaler.com/uvm/viewing-managing-tickets-uvm).

## Configuring Ticket Workflow Rule Sets

For access to ticket workflows, your assigned role must include the **Read**, **Create**, **Edit**, and **Delete**permissions under the **Vulnerabilities App**- **Ticket Settings**resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles).

To configure ticket workflow rule sets:

1. Go to the UVM app (**Vulnerabilities**).
2. In the left-side navigation, click **Settings**> **Ticket Lifecycle**. The **Ticket Lifecycle** page appears. See image.
3. Click **Ticket Workflows**. The **Ticket Workflows**page appears, displaying one of the following states: See image.
  - If no custom rules have been defined, the page displays the default system workflow logic.
  - If workflows are currently managed through [data unification](https://help.zscaler.com/uvm/what-data-unification), click **Unlink & Override**to enable local rule configuration.
  - If rules have already been configured and saved, your existing workflow settings and rules are displayed.
4. On the **Ticket Workflows**page, you can configure rules for the following workflow categories: For each of the**Ticket Status Management**and **Lock Ticket Scope Triggers**categories, rules are processed sequentially. When a ticket meets the criteria for multiple rules within the same category, the system applies only the first matching rule based on the list order. For the **Auto-Dispatch to External Systems**category, the system evaluates all rules. Every rule that matches the ticket's criteria is triggered, allowing a single ticket to trigger dispatches to multiple destinations if it meets the conditions for more than one rule.
  - Ticket Status Management
  - Lock Ticket Scope Triggers
  - Auto-Dispatch to External Systems
5. Click **Done.**

You can view the activity log for all these actions on the Activity tab of a ticket. To learn more, see [Viewing & Managing Tickets in UVM](https://help.zscaler.com/uvm/viewing-managing-tickets-uvm#ticket-drawer-activity-tab).

[Image: Ticket Lifecycle page]

[Image: Ticket Workflows with Workflow Category, Condition, and Action]

Automate ticket status transitions based on the lifecycle of the underlying findings or external workflow triggers. You can configure rules to auto-close tickets when findings are no longer detected, open a new ticket or reopen an existing ticket if findings resurface, update the ticket's status during a third-party handoff, or define custom logic for status updates based on your specific organizational needs.

Automated ticket status workflows take precedence over manual status updates to ensure tickets remain aligned with your organization's remediation policies. If a ticket meets the criteria for an automated transition (e.g., an auto-closure triggered by an inactive finding), the system updates the status to reflect the workflow-defined value, even if the status was previously modified by a user.

To configure automated rules for tickets:

1. Click **Ticket Status Management**. The **Ticket Status Management**drawer opens. See image. If the **Ticket Status Management**setting is configured through [data unification](https://help.zscaler.com/uvm/what-data-unification), you'll be prompted to unlink and override to configure ticket workflows on the Ticket Workflows page. If no rules have been configured yet, click **Create New Rule**.
2. In the **Ticket Status Management** drawer, enter the following details: See image. You can't use the same ticket status as the input condition for both a **Resurface Finding - Create New Ticket**rule and any rule that performs a status transition (i.e., **Automatic Ticket Closure**, **Resurface Finding - Reopen Ticket**, or **Custom Status Transition**). This restriction ensures deterministic behavior, guaranteeing that a single trigger event results in one unambiguous outcome (e.g., when a finding resurfaces, the system either creates a new ticket or changes the status of the original one, but not both).
  - **NAME**: Enter a name for the rule.
  - **ACTIVE**: Enable to activate the rule. Keeping a rule inactive is useful when you want to save the rule without applying its logic during initial setup or while testing your workflow configuration.
  - **CATEGORY**: Select one of the following options:
    - Automatic Ticket Closure
    - Resurface Finding - Create New Ticket
    - Resurface Finding - Reopen Ticket
    - Custom Status Transition
3. (Optional) Click **New Rule** to add a new rule.
4. (Optional) Click**Ticket Default Status** and select a status from the drop-down menu. New tickets that don't meet any of the configured rules default to this status. Only statuses from non-closed and non-remediated buckets are available for selection.
5. Save the rule set in one of the following ways: See image. If the save options are disabled, ensure you have completed the required configuration, including entering a name for every rule.
  - Click **Save** for rules take effect when new data is ingested into your account.
  - Click **Save & Run** to save the rule set and immediately apply the rules on the tickets.

Configure rules to define the conditions that automatically close a ticket to help reduce manual effort and ensure your remediation statuses remain accurate. For example, you can create a rule to close a ticket when its associated findings are no longer detected by your scanners for a specified number of days.

Set the criteria:

- **IF**:
  - **Ticket status is not**: Select the ticket statuses for which this rule should be applied. Available statuses are those configured in the Remediated or Closed buckets under [Ticket Statuses settings](https://help.zscaler.com/uvm/managing-ticket-status). This ensures the rule applies only to active tickets, so the automation does not attempt to process or close tickets that have already reached the specified closed statuses.
  - **All findings are undetected for**: (Optional) Select the checkbox and enter the number of days that findings must remain undetected (not seen in a recent scan) before the ticket closes. If set to 0, the ticket is closed immediately when all findings are undetected. Findings are marked as Undetected based on the finding aging logic configured in the [Remediation Detection settings](https://help.zscaler.com/uvm/creating-data-sources#remediation-detection-settings) of a source and, if enabled, in the [Asset Aging](https://help.zscaler.com/uvm/aging-assets) settings. Make sure the specified number of days aligns with the expected aging cycles.
  - (Optional) You can use the AND/OR operators to include additional criteria and create custom rules (e.g., specific ticket severities or assignment groups).
- **THEN**: From the **Update ticket status to** drop-down menu, select the status that tickets that meet the IF conditions should move to (e.g., **Auto Closed**).

See image.

Configure rules to define when a resurfaced finding in a closed ticket should trigger creation of a new ticket. If your scanners detect the finding again (i.e., it transitions from Undetected to Active) and it remains active for the specified number of days, the system creates a new ticket that includes the resurfaced finding(s) so the recurrence can be tracked separately. This starts a new remediation cycle ensuring historical and MTTX measurements are tracked separately. If an [aged asset](https://help.zscaler.com/uvm/aging-assets) resurfaces, its associated findings also resurface.

Set the criteria:

- **IF**:
  - **Ticket status is**: Select the ticket statuses for which this rule should be applied. Available statuses are those configured in the **Closed**bucket under [Ticket Statuses](https://help.zscaler.com/uvm/managing-ticket-status) settings. This ensures the rule applies only to closed tickets, so resurfaced findings trigger new ticket creation only if the original ticket has been closed.
  - **At least one finding is active for**: Enter the minimum number of days that a resurfaced finding must remain active before the rule is triggered. The rule is triggered even if one finding is active for the specified period.
  - **A third-party work management item is attached**: (Optional) Select the checkbox to apply the rule only to tickets linked to a third-party tool (e.g., a [Jira](https://help.zscaler.com/uvm/configuring-jira-outegration) issue or a [ServiceNow](https://help.zscaler.com/uvm/configuring-servicenow-outegration) ticket).
  - (Optional) You can use the AND/OR operators to include additional criteria and create custom rules.
- **THEN**: The platform is set to open a new ticket with the resurfaced finding by default. This ensures the resurfaced findings are isolated for tracking and assignment.

See image.

Configure rules to define when a resurfaced finding in a closed ticket should trigger reopening the existing ticket. If your scanners detect the finding again (i.e., it transitions from Undetected to Active) and it remains active for the specified number of days, the system reopens the ticket (i.e., moves it to the selected status) so remediation can be tracked in the same ticket. If an [aged asset](https://help.zscaler.com/uvm/aging-assets) resurfaces, its associated findings also resurface.

Set the criteria:

- **IF**:
  - **Ticket status is**: Select the ticket statuses for which this rule should be applied. Available statuses are those configured in the **Closed**bucket under [Ticket Statuses](https://help.zscaler.com/uvm/managing-ticket-status) settings. This ensures the rule applies only to closed tickets, so resurfaced findings trigger reopening the ticket only if it's currently closed.
  - **At least one finding is active for**: Enter the minimum number of days that a resurfaced finding must remain active before the rule is triggered. The rule is triggered even if one finding is active for the specified period.
  - **A third-party work management item is attached**: (Optional) Select the checkbox to apply the rule only to tickets linked to a third-party tool (e.g., a [Jira](https://help.zscaler.com/uvm/configuring-jira-outegration) issue or a [ServiceNow](https://help.zscaler.com/uvm/configuring-servicenow-outegration) ticket).
  - (Optional) You can use the AND/OR operators to include additional criteria and create custom rules.
- **THEN**: From the **Move ticket to status** drop-down menu, select the status that the ticket should move to when reopened. The platform reopens the ticket with the resurfaced finding, ensuring the resurfaced findings are tracked on their original ticket with the original assignment.

See image.

Configure rules to define when a ticket automatically transitions from one status to a different status based on custom conditions. For example, you can create a rule to move a ticket to the In Progress status when the ticket is assigned or when a third-party item is dispatched.

Set the criteria:

- **IF**:
  - **Ticket status is**: Select the ticket status for which this rule should be applied. Available statuses to select from are those configured in [Ticket Statuses](https://help.zscaler.com/uvm/managing-ticket-status) settings.
  - **A third-party work management item is attached**: (Optional) Select the checkbox to apply the rule only to tickets linked to a third-party tool (e.g., a [Jira](https://help.zscaler.com/uvm/configuring-jira-outegration) issue or a [ServiceNow](https://help.zscaler.com/uvm/configuring-servicenow-outegration) ticket).
  - (Optional) You can use the AND/OR operators to include additional criteria and create custom rules.
- **THEN**: From the **Move ticket to status** drop-down menu, select the status that tickets that meet the IF conditions should move to.

See image.

Configure rules to define when to automatically lock a ticket's scope, which prevents the system from adding or removing findings when specific conditions are met. For example, after a user merges two tickets into a single ticket, the ticket's scope is locked by default to preserve the combined set of findings. This ensures that the findings in the merged ticket are not modified by subsequent automated system scans or grouping logic.

To configure automated rules to lock a ticket:

1. Click **Lock Ticket Scope Triggers**. The **Lock Ticket Scope Triggers**drawer opens. If the **Lock Ticket Scope Triggers**setting is configured through [data unification](https://help.zscaler.com/uvm/what-data-unification), you'll be prompted to unlink and override to configure ticket workflows on the Ticket Workflows page. If no rules have been configured yet, click **Create New Rule**.
2. In the**Lock Ticket Scope Triggers** drawer, enter the following details: See image.
  - **NAME**: Enter a name for the rule.
  - **ACTIVE**: Enable to activate the rule. Keeping a rule inactive is useful when you want to save the rule without applying its logic during initial setup or while testing your workflow configuration.
  - Set the criteria:
    - **IF**:
      - **Ticket is manually split or merged**: This checkbox is selected by default and cannot be changed. This ensures that when a user [splits or merges tickets](https://help.zscaler.com/uvm/managing-manual-ticket-grouping), the platform locks the scope of the resulting tickets so subsequent automated [grouping](https://help.zscaler.com/uvm/configuring-grouping-rules) or [data unification](https://help.zscaler.com/uvm/what-data-unification) logic doesn't adjust the findings in the ticket.
      - **Ticket status is**: Select the ticket statuses for which this rule should be applied. Available statuses to select from are those configured in [Ticket Statuses](https://help.zscaler.com/uvm/managing-ticket-status) settings.
      - **A third-party work management item is attached**: (Optional) Select the checkbox to apply the rule only to tickets linked to a third-party tool (e.g., a [Jira](https://help.zscaler.com/uvm/configuring-jira-outegration) issue or a [ServiceNow](https://help.zscaler.com/uvm/configuring-servicenow-outegration) ticket).
      - (Optional) You can use the AND/OR operators to include additional criteria and create custom rules.
    - **THEN**: The ticket is locked. This setting is displayed by default and cannot be changed.
3. (Optional) Click **New Rule** to add a new rule.
4. Save the rule set in one of the following ways: See image. If the save options are disabled, ensure you have completed the required configuration, including entering a name for every rule.
  - Click **Save** for rules to take effect when new data is ingested into your account.
  - Click **Save & Run** to save the rule set and immediately apply the rules on the tickets.

Configure rules to automatically dispatch tickets to external work management systems or trigger alerts when specific conditions are met. This allows you to synchronize remediation workflows with third-party tools and ensure that relevant teams are notified immediately when a ticket matches the configured criteria.

To configure automated rules for creating third-party work management items and alerts:

1. Click **Auto-Dispatch to External Systems**. The **Auto-Dispatch to External Systems**drawer opens.
2. In the **Auto-Dispatch to External Systems** drawer, enter the following details: See image.
  - **NAME**: Enter a name for the rule.
  - **ACTIVE**: Enable to activate the rule. Keeping a rule inactive is useful when you want to save the rule without applying its logic during initial setup or while testing your workflow configuration.
  - Set the criteria:
    - **IF**:
      - **Ticket status is**: Select the ticket statuses for which this rule should be applied. Available statuses to select from are those configured in [Ticket Statuses](https://help.zscaler.com/uvm/managing-ticket-status) settings.
      - **Ticket severity is**: (Optional) Select this checkbox to add the severity levels that trigger this rule. Only tickets with the selected severities are dispatched.
      - **Ticket assignee is**: (Optional) Select this checkbox and enter the assignees that the ticket must be assigned to for this rule to apply, so only tickets owned by specific users or teams are dispatched to external systems.
      - (Optional) You can use the AND/OR operators to include additional criteria and create custom rules.
    - **THEN**:
      - **Create a third-party item on**: Select the outegration for the system you want to trigger. The drop-down menu lists the outegrations already configured in your account.
      - **Create New Outegration**: If no outegration is configured for the destination you want to dispatch to, click to open the **Connect a New Outegration**page and [create one](https://help.zscaler.com/uvm/creating-outegrations). After you create an outegration, it appears in the **Create a third-party item on**drop-down menu.
3. (Optional) Click **New Rule** to add a new rule.
4. Save the rule set in one of the following ways: See image. If the save options are disabled, ensure you have completed the required configuration, including entering a name for every rule and configuring the THEN conditions.
  - Click **Save** for rules take effect when new data is ingested into your account.
  - Click **Save & Run** to save the rule set and immediately apply the rules on the tickets.

[Image: Ticket Status Management drawer]

[Image: Ticket Status Management Automatic Ticket Closure]

[Image: Ticket Status Management Category drop-down menu]

[Image: Save options]

[Image: Ticket Status Management Resurface Findings - Create New Ticket category]

[Image: Ticket Status Management Resurface Findings - Reopen Ticket category]

[Image: Ticket Status Management Custom Status Transition]

[Image: Lock Ticket Scope Triggers drawer]

[Image: Save options]

[Image: Auto-Dispatch to External Systems drawer]

[Image: Save options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-veracode-connector","lastmod":"2026-04-25T07:06Z","nid":"1534185"} -->
## Configuring the Veracode Connector

- Source: https://help.zscaler.com/uvm/configuring-veracode-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Veracode Connector
- Last modified: 2026-04-25T07:06Z
- Summary: How to configure the Veracode connector, including setting up authentication and data retrieval filters and specifications.

Veracode provides visibility into application threats by continuously finding flaws and vulnerabilities at every stage of the software development lifecycle.

The Veracode connector gathers information about open and mitigated findings associated with applications and development sandboxes.

There are three Veracode streams. Select those that are based on your Veracode feature plan and use cases:

- Veracode: Retrieves Static Analysis, Dynamic Analysis, Manual Penetration Testing, and Software Composition Analysis (SCA) findings.
- Veracode SCA: Retrieves SCA findings. This stream does not combine SCA findings with other finding types.
- Veracode Applications: Retrieves a paginated list of applications from Veracode. The Veracode Applications stream is in limited availability. To learn more, contact Zscaler Support.

## Prerequisites

To configure the Veracode connector, you need the source authentication configuration. The API key and API secret parameters are required. The API key allows you to improve security and session management. The API credentials consist of the API key and API secret.

- API Credentials
- API Service Account

To generate API credentials:

1. Log in to the Veracode platform.
2. From the user account drop-down menu, select **API Credentials**.
3. Click **Generate API Credentials**. Save the ID and secret key securely. They are not displayed again after you exit the window.

To learn more, refer to the [Veracode documentation](https://docs.veracode.com/r/c_api_credentials3).

Veracode also requires HMAC authentication, which is performed in the Zscaler Security Operations (SecOps) platform. It does not require any actions in the Veracode platform. To learn more, refer to the [Veracode documentation](https://docs.veracode.com/r/c_enabling_hmac).

To set up the Veracode API, you must have an API service account with the Results API role or a user account with the Reviewer or Security Lead role. To learn more, refer to the [Veracode documentation](https://docs.veracode.com/r/c_API_roles_details) and the [Veracode documentation](https://docs.veracode.com/r/c_role_permissions).

You must create a new user to create an API service account. You cannot change an existing user account to an API service account. To create a new user:

1. Log in to the Veracode platform.
2. From the top right, click the gear icon and select **Admin**.
3. On the **Users** tab, click **Add New User**.
4. Enter a first and last name.
5. Select the **Non-Human User** checkbox.
6. Provide a valid email address. Veracode sends notifications about error messages, password expiration, and other automated success and error messages to this email.
7. Optionally, define the IP address range restrictions for the user. To learn more, refer to the [Veracode documentation](https://docs.veracode.com/r/admin_ip).
8. In the **User Roles** section, select the APIs you want the API service account to access. You must select the **Results API** role.
9. Click **Save** to create and enable the user. You receive an activation email.

To learn more, refer to the [Veracode documentation](https://docs.veracode.com/r/c_video_create_and_manage_api_users).

## Configuring the Connector

To create the Veracode data source in the SecOps platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Veracode data source, enter the following information in the source setup Retrieval section:

- Authentication
- Severities
- Statuses
- Scan Types

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Veracode Authentication** window appears.
2. In the **Veracode Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

Select one or more severity levels to choose which vulnerabilities you want to ingest.

This menu is available in the Veracode stream.

Select the lifecycle status of the vulnerabilities you want to retrieve from Veracode (i.e., Open or Closed).

This menu is available in the Veracode stream.

Select one or more security scan methods to retrieve findings:

- Static Analysis (SAST): The findings identified by analyzing application source code for security vulnerabilities without running the code.
- Dynamic Analysis (DAST): The findings identified by analyzing an application to detect vulnerabilities that appear while it is running.
- Manual Analysis: The findings identified through penetration testing or other manual security assessments.
- Software Composition Analysis (SCA): The findings related to vulnerabilities found in third-party and open-source components used within your applications.

This menu is available in the Veracode stream.

[Image: The Veracode tiles in the SecOps platform]

[Image: The Veracode Authentication window in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-wiz-connector","lastmod":"2026-08-24T10:31Z","nid":"1530953"} -->
## Configuring the Wiz Connector

- Source: https://help.zscaler.com/uvm/configuring-wiz-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Wiz Connector
- Last modified: 2026-08-24T10:31Z
- Summary: How to configure the Wiz connector, including setting up authentication and data retrieval filters and specifications.

Wiz analyzes all layers of the cloud stack to identify high-risk attack vectors to be prioritized and fixed.

There are three Wiz streams. Select those that are based on your Wiz feature plan and use cases. The following is one of the available streams:

- Wiz Assets: Retrieves detailed information about cloud resources, with optional enrichment for exposed resources data.
- Wiz Issues: Retrieves details on active security threats (e.g., vulnerabilities, misconfigurations, or exposed secrets) including severity, affected resources, and remediation recommendations for each identified threat.
- Wiz Configuration Findings: Retrieves details on configurations by identifying misconfigurations, compliance status, and potential vulnerabilities across various cloud resources.

To learn more, see [Zscaler UVM and Wiz Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-wiz-deployment-guide).

## Prerequisites

Before you configure the Wiz connector, make sure the following prerequisites are met:

- Retrieve the source authentication configuration parameters.
- Ensure the proper roles and permissions are set.

For the Wiz Assets stream, the value supplied in the Client ID field must carry at least the following permissions:

- `read:resources`
- `read:network_exposure` (if the Network Exposure Info checkbox is selected)

To learn more about permissions for the other Wiz streams, see [Zscaler UVM and Wiz Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-wiz-deployment-guide).

## Configuring the Connector

To create the Wiz data source in the Security Operations Platform (SecOps Platform):

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Wiz data source, enter the following information in the source setup Retrieval section:

- Authentication
- Project ID
- Asset Type
- Fetch configuration findings from the past selected days
- Finding Status
- Finding Severity
- Issue Type
- Issue Severity
- Issue Status
- Retrieve detailed data (larger volume)
- (Optional) Include Network Exposure Info

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Troubleshooting and FAQs

The Wiz Issues stream might return fewer results than expected, or miss data due to a 50,000 row limit on full report runs enforced by Wiz. If your dataset exceeds this threshold, only the first 50,000 rows are included in the export. The export is the data exported from Wiz to the SecOps Platform. The remainder is omitted. For larger datasets, use incremental exports, which support up to 500,000 rows to ensure full data coverage.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Wiz Authentication** window appears.
2. In the **Wiz Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

To retrieve all projects, you must have global permissions granted in Wiz; otherwise, the report generation fails.

To retrieve the project ID, see [Zscaler UVM and Wiz Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-wiz-deployment-guide).

Use the Asset Type drop-down menu filter to limit vulnerability findings to specific asset categories.

This field is available in the following streams:

- Wiz Vulnerability Findings: Filters findings by asset type (e.g., Virtual Machine, Container, Container Image, Serverless). Only findings related to the selected asset type are retrieved.
- Wiz Assets: Filters cloud resources based on Entity Types normalized by Wiz (e.g., Virtual Machine, Database, Container, Load Balancer).

If no filter is applied, all available asset types are included.

Enter the number of days to retrieve configuration findings that were first analyzed within that timeframe. For example, enter `30` to return configuration findings that were first analyzed within the last 30 days.

This field is available in the Wiz Configuration Findings stream.

Select the statuses to include in the scope of the ingested data (i.e., Open, In Progress, Resolved, Rejected).

This menu is available in the Wiz Configuration Findings stream.

Select the severity level of vulnerabilities to include in the scope of the ingested data (i.e., Critical, Low, Severe, Informational, None, Moderate).

This menu is available in the Wiz Configuration Findings stream.

Select the types of issues to include in the scope of the ingested data (i.e., Toxic Combination, Threat Detection, Cloud Configuration).

This menu is available in the Wiz Issues stream.

Select the issue severity levels to include in the scope of the ingested data (i.e., Critical, High, Medium, Low, Informational).

This menu is available in the Wiz Issues stream.

Select the status you want to include in the scope of the ingested data (i.e., Open, In Progress, Resolved, Rejected, or All Statuses).

This menu is available in the Wiz Issues stream.

Select the checkbox to retrieve additional metadata for each Wiz issue, resulting in large data ingestion volumes.

This checkbox is available in the Wiz Issues stream.

Select thecheckbox to add enrichment for exposed resources data. This data provides insights into which resources are exposed, how they are exposed (e.g., to the public internet, cross-account access, custom IP ranges), and details like IP ranges, ports, and protocols.

This checkbox is available in the Wiz Assets stream.

[Image: The Wiz - Vulnerability Findings, Wiz - Issues, and Wiz - Configuration Findings tiles]

[Image: The Wiz Authentication window displaying the Name, Client Id, Client Secret, Url, and Token Url fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-zia-devices-and-users-connector","lastmod":"2026-08-18T02:00Z","nid":"1530878"} -->
## Configuring the ZIA Devices and Users Connector

- Source: https://help.zscaler.com/uvm/configuring-zia-devices-and-users-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the ZIA Devices and Users Connector
- Last modified: 2026-08-18T02:00Z
- Summary: How to configure the ZIA Devices and Users connector, including setting up authentication and data retrieval filters and specifications.

Zscaler Internet Access (ZIA) is a cloud-based security platform that provides secure internet access for users, protecting organizations from various online threats by enforcing security policies, filtering content, and ensuring secure connections to public applications and services on the internet.

The ZIA Devices and Users connector retrieves information about devices and their owners from ZIA.

In the ZIA Admin Portal, you can view the lists of devices and users, along with their associated group and department details:

- To view devices, go to Administration > Device Management > Devices. To learn more, see [About Devices](https://help.zscaler.com/zia/about-devices).
- To view users, go to Administration > User Management > Users. To learn more, see [About Users](https://help.zscaler.com/zia/about-users).

## Prerequisites

To configure the ZIA Devices and Users connector, you need the source authentication configuration. The following parameters are required:

- Username
- Password
- API Key
- Company ID
- Cloud Name

## Configuring the Connector

To create the ZIA Devices and Users data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the ZIA Devices and Users data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1. In the **Authentication** section, click **Create New**. The **ZIA Authentication** window appears.
2. In the **ZIA Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

## Troubleshooting and FAQs

If you entered all the required parameters with the appropriate permissions, but are still encountering an error when attempting to save the connector, you might need to enable **Password Based Login** for the user whose credentials you are entering into the system. To enable password-based login:

1. Log in to the ZIA Admin Portal as a super admin.
2. Go to **Administration** > **Administration Management**.
3. On the **Administrators** tab, click the **Edit** icon next to the user that you want to configure the credentials for to authenticate the connector.
4. In the **Edit Administrator** window, select the **Password Based Login** checkbox.

The username associated with a ZIA admin account.

The password associated with a ZIA admin account.

Before getting started, ensure that your organization has an API subscription and an API key enabled. If you do not have a valid subscription, contact Zscaler Support.

To locate your API key:

1. Log in to the ZIA Admin Portal.
2. Go to **Administration** > **Cloud Service API Security**. See image.
3. On the **Cloud Service API Key** tab, view the key. See image.

An organization can only have one API key. If you already have an API key, creating a new API key is not possible. To create a new API key, delete the existing API key.

### Roles and Permissions

To successfully use the ZIA Devices and Users connector and ensure that all the relevant data is retrieved successfully, the admin who configures the connector must have the proper permissions and scope. To learn more, see [Adding Admin Roles](https://help.zscaler.com/zia/adding-admin-roles).

Configure the following permissions for the admin who creates the API key:

- All permissions: When possible, set to **View Only**.
- **User Names**: Set to **Visible**.
- **Device Information**: Set to **Visible**.
- **Workflow Access**: Set to **None**.

The user providing the credentials (i.e., username and password) for the configuration must be an admin with access to all functional scopes.

To retrieve the company ID:

1. Log in to the ZIA Admin Portal.
2. Go to **Administration** > **Company Profile**. See image.
3. On the **Organization** tab, in the **General Information** section, view the company ID. See image.

Your cloud name is located in the URL that the admin uses to log in to the Zscaler service. For example, if your organization logs into admin.zscalerbeta.net, then your organization's cloud name is zscalerbeta.net. To learn more, see [What Is My Cloud Name for ZIA?](https://help.zscaler.com/zia/what-my-cloud-name-zia)

[Image: The Zscaler cloud name located in the URL of the portal]

[Image: The ZIA Devices and Users tile]

[Image: The ZIA Authentication window displaying the Name, User Name, Password, Api Key, Company Id, and Cloud Name fields in the SecOps platform]

[Image: Navigating to the Company Profile page in the ZIA Admin Portal]

[Image: Viewing the Company ID on the Organization tab of the Company Profile page of the ZIA Admin Portal]

[Image: Navigating to the Cloud Service API Security page in the ZIA Admin Portal]

[Image: Viewing the API Key under Key in the table on the Cloud Service API Key tab of the Cloud Service API Security page of the ZIA Admin Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-zscaler-advanced-detection-connector","lastmod":"2026-08-26T09:06Z","nid":"1543059"} -->
## Configuring the Zscaler Advanced Detection Connector

- Source: https://help.zscaler.com/uvm/configuring-zscaler-advanced-detection-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Zscaler Advanced Detection Connector
- Last modified: 2026-08-26T09:06Z
- Summary: How to configure the Zscaler Advanced Detection connector.

Zscaler Advanced Detections uses expert-driven Internet & SaaS (ZIA) detections to find behavioral patterns that match adversarial techniques within your Zscaler-specific threats. When an attacker infiltrates your security environment, Zscaler Advanced Detection analyzes the raw telemetry — not just alerts — using two primary classes of analytics:

- **Indicators of compromise**: Every piece of telemetry is tested to determine if it matches a compromise indicator that we've seen or heard adversaries use. These are brittle and often short-lived analytics, but if an adversary is foolish enough to reuse infrastructure or tools, they are easy to catch.
- **Behavioral detectors**: These analytics identify sequences of system activity that match techniques used by adversaries. These could be as simple as command and control, exfiltration, or a highly complex chain of behavior over a long period of time. We map every detector to MITRE ATT&CK® techniques so you can quantify your detection coverage.

Zscaler's focused approach to security data has a direct influence on how and why we create our Zscaler Advanced Detections' security analytics, known as detectors. These detectors surface high-confidence atomic data points from Internet & SaaS telemetry that correlate into high-fidelity incidents. This helps detect the following Zscaler-specific threats earlier and stop them faster:

- Unmanaged devices
- Insider threats
- Encrypted threats
- "Living off the land" attacks
- AI-driven and multi-stage attacks

When a threat is detected, Zscaler Advanced Detections publishes an incident on the **Incidents** page with one source labeled `Zscaler Advanced Detections`. To learn more, see [About Incidents](https://help.zscaler.com/soc-workbench/about-incidents).

See image.

[Image: Zscaler Advanced Detections incident example]

## Prerequisites

To configure the Zscaler Advanced Detections connector, ensure you have active Internet & SaaS and SOC Workbench subscriptions. To learn more, see [Viewing Subscriptions](https://help.zscaler.com/unified/viewing-subscriptions).

## Configuring the Connector

To create the Zscaler Advanced Detections data source in the Security Operations Platform (SecOps Platform):

1. Go to **Configure** > **Sources**.
2. Click **Create**. The **Create New Data Source**page appears.
3. On the **Create New Data Source**page, search for and select the Zscaler Advanced Detections connector tile from the available data sources. See image. [Image: Zscaler Advanced Detections tile]

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

Your Zscaler Account team sets up the Zscaler Advanced Detections connector on your behalf. You do not need to configure or adjust your user roles for SOC Workbench.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/configuring-zscaler-client-connector-devices-connector","lastmod":"2026-08-18T01:58Z","nid":"1530862"} -->
## Configuring the Zscaler Client Connector Devices Connector

- Source: https://help.zscaler.com/uvm/configuring-zscaler-client-connector-devices-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Zscaler Client Connector Devices Connector
- Last modified: 2026-08-18T01:58Z
- Summary: How to configure the Zscaler Client Connector Devices connector, including setting up authentication and data retrieval filters and specifications.

Zscaler Client Connector is an application deployed on an end user device that automatically forwards all user traffic through the Zscaler Zero Trust Exchange (ZTE) to enforce policy and access controls while improving performance.

The Zscaler Client Connector Devices connector retrieves data on assets in your organization, including which Zscaler products are installed on which assets.

## Prerequisites

To configure the Zscaler Client Connector Devices connector, you need the source authentication configuration. The following parameters are required:

- Client ID and Secret Key
- Cloud Name

## Configuring the Connector

To create the Zscaler Client Connector Devices data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Zscaler Client Connector Devices data source, enter the Authentication information in the source setup Retrieval section. To configure authentication:

1. In the **Authentication** section, click **Create New**. The **ZCC Authentication** window appears.
2. In the **ZCC Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

Before getting started, you must enable the API for your organization to obtain access to the Zscaler Client Connector API by contacting Zscaler Support. To create an API token, the admin must be assigned a role with full access to the public API resource in the Zscaler Client Connector Portal (Administration > Administration Management > Role Management).

See image.

To retrieve the API key and secret key:

1. Log in to the Zscaler Client Connector Portal.
2. Go to **Administration** > **Public API**.
3. Click **Add API Key**.
4. In the **Add API Key** window:
  - **Name**: Enter a name for the API key. The name must be alphanumeric, cannot contain spaces, and has a maximum of 50 characters.
  - **Status**: By default, **Enabled** is selected. If disabled, the key is unavailable to use.
  - **Role**: Select **Read** access for the key.
  - **Session Validity Interval (In Seconds)**: Enter the amount of time the key is available to use. The recommended session length is at least 43,200 seconds, which is equal to 12 hours. See image.
5. Click **Save** to generate the client ID and client secret.
6. Copy the client secret and close the **Add API Key** window. See image. Store the client secret securely because it is only available when creating an API key. It is not available after you close the window.
7. On the **Public API** page, copy the **Client ID**. See image.

Your cloud name is located in the URL that the admin uses to log in to the Zscaler service. For example, if your organization logs into admin.zscalerbeta.net, then your organization's cloud name is zscalerbeta.net. To learn more, see [What Is My Cloud Name for ZIA?](https://help.zscaler.com/zia/what-my-cloud-name-zia)

[Image: The Zscaler cloud name in the URL of your Zscaler portal]

[Image: The ZCC Devices tile]

[Image: The ZCC Authentication window displaying the Name, Api Key, Domain, and Secret Key fields in the SecOps platform]

[Image: The Add API Key window in the Zscaler Client Connector Portal]

[Image: Selecting Full for Public API access in the Zscaler Client Connector Portal]

[Image: Copying the client secret in the Add API Key window of the Zscaler Client Connector Portal]

[Image: Copying the client ID on the Public API page of the Zscaler Client Connector Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/connecting-anysource","lastmod":"2026-08-16T01:14Z","nid":"1528226"} -->
## Connecting AnySource

- Source: https://help.zscaler.com/uvm/connecting-anysource
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Connecting AnySource
- Last modified: 2026-08-16T01:14Z
- Summary: How to connect and set up an AnySource data connector, including uploading files manually, or using AWS S3, GCP, webhook, and an uploading file API.

When [creating a data source](https://help.zscaler.com/unified/creating-data-sources) to ingest data into the platform, you can either use a dedicated vendor connector, or you can use the AnySource connector. The AnySource connector allows you to upload files directly to the platform. Uploaded files are stored in their original format and can be mapped to the platform's data model. Each source maintains a consistent field mapping, so all files uploaded to a given source should match the structure of the original file (e.g., headers, file type).

To set up the AnySource connector:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select **AnySource** from the tiles.
4. On the **Create AnySource Source** page, in the **Retrieval** section, select a file upload method:

- Upload File
- AWS S3
- GCP
- Webhook
- Upload File API

To complete the AnySource connector setup, including Remediation Detection Settings and Suppression Rules, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

Manually upload a file by dragging and dropping it into the designated area, or selecting it from your local folders. The following file formats are supported for upload: JSON, JSONL, CSV, XLSX, ZIP, XML, ZST, ZSTD.

After your file is uploaded, the **Parser Type**drop-down menu appears.

See image.

In most cases, Zscaler recommends selecting **Auto**to automatically detect the file type and parse it accordingly.

If you require more granular control over how the file is parsed, or if the Auto parser does not correctly identify the file type, you can manually select the appropriate Parser Type based on your file's format.

The following parser types are available:

- JSONL
- JSON
- CSV
- Excel
- XML
- PYTHON_STREAM

## File Size Limit

Uploaded files must not exceed 50 MB. If your file is larger than this limit, consider the following options:

- Compress the file using one of the supported formats (i.e., ZIP, GZIP, or ZSTD) to reduce its size to within the 50 MB limit.
- Use a file storage service (e.g., AWS S3) to upload the file. This method is recommended for files that cannot be compressed to meet the 50 MB size requirement.

[Image: anysource upload file method parser type dropdown]

No additional configuration is required.

You can enter a Root Json Path to access specific data elements. The Root Json Path uses JSONPath expressions. To learn more, refer to the [Overview of JSONPath Expressions](https://www.rfc-editor.org/rfc/rfc9535#name-overview-of-jsonpath-expres).

To configure the CSV parser, you must enter the **Delimiter**(e.g., comma, period, pipe) used in the file.

You can also enter a **Quote Character**, which can be any character. The double quote (") is the standard and most commonly used in CSV files.

To configure the Excel parser, enter the **Sheet Name**from which the data should be extracted. You can enter the exact name of the sheet, or you can use a wildcard (i.e., an asterisk `*`) to dynamically select the sheet name.

Using only the wildcard extracts the first sheet from the Excel file. You can combine the wildcard with a pattern to extract sheets with a recurring name format. For example, entering `pen_test*` retrieves the first sheet that begins with `pen_test`, regardless of any characters that follow.

Select the **Use first sheet as default**checkbox to return the first sheet if the specified sheet name is not found. If this option is left unchecked and the specified sheet does not exist, an error occurs and no data is retrieved.

You can enter the Root XPath to access specific elements within the XML structure. To learn more, refer to the [XPath Syntax guide](https://www.w3schools.com/xml/xpath_syntax.asp).

The PYTHON_STREAM parser allows you to configure custom data manipulations and parsing logic necessary for accurately and optimally mapping data.

A common use case is exploding a single row in the ingested data into multiple rows in the transformed data. For example, you can explode a single row that contains multiple CVEs in a column into separate rows of CVEs that can then be mapped to individual findings.

The following functions are available:

- `parse_to_json()`: The primary function where you must implement your custom parsing logic. It is mandatory to define all parsing and output logic within this function for the parser to function correctly.
- `AvalorInputStream()`: Provides access to the uploaded file via the PYTHON_STREAM parser. This function supports two key methods for reading file content:
  - `read()`: Reads the entire content of the uploaded file in a single operation and returns it as a single string.
  - `readline()`: Reads a single line from the uploaded file and returns it as a string.
- `output_writer.write(json_formatted_string)`: A method used to write rows of data into the platform using the PYTHON_STREAM parser. Accepts a JSON-formatted string as its input.

The following is an example script for parsing files using the PYTHON_STREAM parser:

```
import json
def parse_to_json():
   # Open input stream for reading
   with AvalorInputStream() as inputstream:
       line = inputstream.readline()  # Read the first line
       while line:
           # Assume each line contains a JSON array of CVEs
           cve_list = json.loads(line.strip())  # Parse JSON array
           
           # Write each CVE back to output, one at a time, as a JSON string
           for cve in cve_list:
               output_writer.write(json.dumps(cve))  # Write as JSON without 
newline
           
           line = inputstream.readline()  # Read the next line
```

The platform supports integration with your AWS S3 buckets to automatically extract data as new files are added. When a file is uploaded to a designated bucket, the platform is notified and retrieves the file for processing. To learn more, see [Connecting AnySource Using AWS S3](https://help.zscaler.com/uvm/connecting-anysource-using-aws-s3).

The platform supports integration with your GCP Cloud Storage buckets to automatically extract data as new files are added. When a file is uploaded to a designated bucket, the platform is notified and retrieves the file for processing.

To use a webhook for retrieving data, select the **Push**option from the **Method**drop-down menu. This is a REST API call that receives the data as part of the body. Each source has its own unique webhook ID that is part of the URL.

See image.

The following is an example API call, including the payload:

```
curl --location 'https://webhook.avalor.io/inlinedata/
<Account ID>
/
<Unique Webhook ID>
' \
--header 'Content-Type: text/plain' \
--data-raw '[{
"field1": false,
"field2": "text",
"field3": [
"item1",
"item2"
]
}]'
```

## File Size Limit

Uploaded files must not exceed 6 MB. If your file is larger than this limit, consider the following options:

- Compress the file using one of the supported formats (i.e., ZIP, GZIP, or ZSTD) to reduce its size to within the 6 MB limit.
- Use a file storage service (e.g., AWS S3) to upload the file. This method is recommended for files that cannot be compressed to meet the 6 MB size requirement.

The Upload File API allows you to programmatically upload a file to an AnySource data source instance via API.

## Prerequisites

The Upload File API method uses token-based authentication using client credentials. To authenticate API requests, you must first obtain client credentials (client ID and client secret) from your platform representative or the SecOps platform support team.

## File Size Limit

Uploaded files must not exceed 50 MB. If your file is larger than this limit, consider the following options:

- Compress the file using one of the supported formats (i.e., ZIP, GZIP, or ZSTD) to reduce its size to within the 50 MB limit.
- Use a file storage service (e.g., AWS S3) to upload the file. This method is recommended for files that cannot be compressed to meet the 50 MB size requirement.

## Using the API

To make an API call, you must first generate a token, which is then used to authenticate the request.

### Generating a Token

Using your client ID and client secret, access the `oauth2/token` endpoint to generate a token. The token is valid for one hour only.

Replace the following details in the command below:

- `<URL>`
  - For **US**: `https://auth.us01.app.avalor.io/oauth2/token`
  - For **EU**: `https://auth.eu.app.avalor.io/oauth2/token`
- `<Client ID>`: Your client ID
- `<Client Secret>`: Your client secret

```
curl --location '
<URL>
' \
--header 'accept: application/json' \
--header 'content-type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=
Client Credentials
' \
--data-urlencode 'client_id=
<Client ID>
' \
--data-urlencode 'client_secret=
<Client Secret>
'
```

### Making the API Request

The following is an example API call, including the payload:

```
curl --request POST \
--url 'https://api.region.app.avalor.io/api/webserver/data-source-instance/upload?processDsiId=
<Data Source Instance ID>
' \
--header 'accountid:
<Account ID>
' \
--header 'authorization:
<Token>
' \
--header 'content-type: multipart/form-data' \
--form 'multipartFile=@
<File Name>
'
```

## Fields to Include in the AnySource File

When ingesting data through the AnySource connector, it's important to include specific fields to enable effective data unification (i.e., entity resolution and data normalization) within the platform's unified data model. To learn more, see [What Is Data Unification?](https://help.zscaler.com/unified/what-data-unification)

Unlike vendor-specific API connectors (e.g., CrowdStrike Managed Hosts, ServiceNow Assets), which automatically extract the necessary fields based on predefined integration logic, the AnySource connector requires you to explicitly determine which fields to include. For example, when uploading a data file manually, you can choose to include the appropriate fields directly in the file. In other cases, such as using the Upload File API, field selection is typically handled through the parsing logic you define.

### Best Practices for Field Selection

To ensure ingested data is usable in downstream processes (e.g., normalization, enrichment, correlation, and analytics), follow these best practices when selecting fields:

- Include core identifiers: Always export key attributes for each entity to support correlation across data sources (e.g., ID or unique key, name or title, and timestamps like first seen and last seen).
- Add contextual data: Supplement core fields with additional attributes that provide business, operational, or technical context (e.g., asset type, operating system, business unit, owner, location, or tags).

When possible, export a detailed version of the data to facilitate more accurate mapping and merging across different sources.

The ingestion layer is designed to support a wide variety of file formats and structural variations, so preprocessing (e.g., data cleaning, reformatting) is generally not required, as the system can interpret and normalize diverse input structures during parsing.

### Recommended Fields (by Entity Type)

Each entity type (e.g., assets, vulnerabilities) has its own set of core recommended fields. The following examples are the key fields typically associated with assets and vulnerabilities.

#### Assets

| **Attribute** | **Description** |
| --- | --- |
| Asset Name/Hostname | The name or hostname of the asset |
| Asset Type | The type of asset (e.g., server, workstation) |
| External or Internal IP Address | The IP addresses associated with the asset |
| Operating System | The operating system running on the asset |
| Asset Owner | The individual or department responsible for the asset |
| Location | The physical or logical location of the asset |
| Asset Status | The status of the asset (e.g., active, decommissioned) |
| Asset Tags | The asset tags, if available. Tags can contain valuable business information. |
| Software Installed | A list of installed software and software versions |
| Asset Criticality | The importance of the asset to the organization (e.g., critical, non-critical) |

#### Findings

| **Attribute** | **Description** |
| --- | --- |
| Vulnerability Name/ID | The name or unique identifier for the vulnerability |
| Severity Score/CVSS/Scanner Score | The severity score assigned to the vulnerability, including the numerical score (8.1) and the category (High) |
| Description | A detailed description of the vulnerability and impact |
| Affected Asset | Information about the asset or system with the vulnerability |
| CVE | The CVE identifier, if available |
| Threat Intel Information | Threat intel parameters provided by the vendor |
| Tags | Vulnerability tags, if available. Tags can contain valuable business information. |
| Timestamps | The First Seen and Last Seen fields that state when the vulnerability was first and last discovered or reported |
| Recommendations/Fix | Recommended actions for vulnerability remediation |
| Affected Component | Information about the affected component (i.e. Java, Windows), if available |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/connecting-anysource-using-aws-s3","lastmod":"2026-08-16T01:12Z","nid":"1528386"} -->
## Connecting AnySource Using AWS S3

- Source: https://help.zscaler.com/uvm/connecting-anysource-using-aws-s3
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Connecting AnySource Using AWS S3
- Last modified: 2026-08-16T01:12Z
- Summary: How to connect and set up an AnySource connector using the AWS S3 method.

The Zscaler Security Operations (SecOps) platform can integrate with your AWS S3 buckets to automatically extract data. When a new file is added to the bucket, an alert is triggered and the file is retrieved.

## Setting Up an S3 Integration

Configuring an integration with your S3 data is a three-step process.

- 1. Create a role that Zscaler can use to pull data.
- 2. Create an SQS queue for the SecOps platform to subscribe to.
- 3. Configure your bucket to send new object notifications to the SQS queue.

1. Generate a unique identifier that will be used by Zscaler's service to assume the role. You can use this [UUID Generator](https://www.uuidgenerator.net).
2. Run the SecOps platform's Cloud Formation Role Stack. You can either:
  - [Follow the AWS instructions](https://console.aws.amazon.com/cloudformation/home?region=us-east-1#/stacks/create/review?stackName=AvalorStackIntegration&templateURL=https%3A%2F%2Favalor-platform-prod.s3.amazonaws.com/static/media/avalor-aws-standard.json).
  - Use the following command: `aws cloudformation create-stack \ --region <Region> \ --stack-name AvalorStackIntegration \ --capabilities CAPABILITY_NAMED_IAM \ --template-url https://avalor-platform-public-prod.s3.amazonaws.com/static/media/avalor-aws-standard.json \ --parameters ParameterKey=ExternalId, ParameterValue=<Generated UUID>`
    - Replace `<Region>` with the region of the AWS service you're retrieving data from.
    - Replace `<Generated UUID>` with the UUID you created.
    - The `template-url` script provides permissions for AWS. If you're connecting to AnySource only, you can edit the script to modify or remove unnecessary permissions.
      - Permissions

After running the command, the RoleARNID value appears under the stack's Output tab.

{
"Action": [
"ec2:DescribeInstances", 
"ecr:ListImages", 
"ecr:DescribeImages",
"ecr:DescribeRepositories",
"ecr:DescribeImageScanFindings",
"rds:DescribeDBInstances",
"eks:ListClusters",
"eks:DescribeCluster",
"s3:ListAllMyBuckets",
"inspector2:ListFindings",
"securityhub:GetFindings"
],
"Effect": "Allow",
"Resource": "*"
},

1. Create an SQS queue with the access policy as described in the [AWS documentation](https://docs.aws.amazon.com/AmazonS3/latest/userguide/ways-to-add-notification-config-to-bucket.html#step1-create-sqs-queue-for-notification).
2. Name the queue `avalor-s3-event-forwarder`.
3. Copy and save the Queue ARN, to be used in the next step.

Make sure to send the Queue URL to your Zscaler Account team representative to complete the configuration process.

### KMS Encryption (Optional)

If your organization uses KMS encryption, add the following to the key policy:

```
- Sid: Allow Role to use the key
              Effect: Allow
              Principal:
                AWS: !GetAtt AvalorRole.Arn
              Action:
             - "kms:Decrypt"
             - "kms:GenerateDataKey*"
              Resource: "*"
```

The final step is configuring an event notification from the S3 buckets where the data is generated for the SQS Queue. To complete the process, refer to the [AWS documentation](https://docs.aws.amazon.com/AmazonS3/latest/userguide/enable-event-notifications.html).

When configuring the bucket:

1. In step 6, select **All object create events**under **Event Type**.
2. In step 7, select **SQS Queue** as the Destination, then enter **SQS queue ARN**. Paste the ARN obtained from the previous step.
3. Click **Save Changes**.

Repeat these steps for every bucket that you want to ingest data from.

## Data Source Setup

After you finish setting up your S3 integration, send the following details to your Zscaler Account team representative to use in setting up the data source.

- AWS account ID
- Region
- Bucket
- Role ARN
- (Optional) Path
- (Optional) External ID: The external ID is the unique identifier you create when creating a role for Zscaler. It is an additional security measure that can be used when accessing your account. While using an External ID is considered best practice, it is not mandatory.
- (Optional) File pattern (regex)

### Path and File Pattern Fields

You can use the Path and File pattern fields to manage which files are retrieved to the source. This is useful if you have a main bucket for all Zscaler files but want to divide the files into different sources. The Path and File pattern fields can be used separately or together to fit your use case.

#### Path

The Path field is useful when you have subfolders in your main bucket, and each folder contains files that should be associated with a different source in the platform. For example, if one folder holds logs from the production environment and another from the test environment, you can enter the specific path when setting up the source to ensure that only files from that path are uploaded to the source.

#### File

The File pattern field also allows better control over the files uploaded to a specific source. For example, by entering a `*.jsonl` pattern, the platform will only retrieve files with a .jsonl extension in that particular source. The file pattern should be entered in a regex format.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/exporting-reports-discovery-profiles","lastmod":"2026-05-17T07:06Z","nid":"1538692"} -->
## Exporting Reports for Discovery Profiles

- Source: https://help.zscaler.com/uvm/exporting-reports-discovery-profiles
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > External Attack Surface Management for UVM > Exporting Reports for Discovery Profiles
- Last modified: 2026-05-17T07:06Z
- Summary: How to generate and export reports for EASM asset discovery profiles, which present key aggregated data and insights into the organization's external security posture.

You can generate and export reports for the [discovery profiles](https://help.zscaler.com/uvm/configuring-discovery-settings) configured for Zscaler's External Attack Surface Management (EASM) that aggregate key analytic data and security insights into an organization's exposure to cyber risk. The reports are generated in PPTX format, translating real-time technical security data from automated discovery findings into actionable strategic insights for diverse stakeholders.

These reports consolidate critical asset findings along with their mitigation strategies, which help organizations assess the resulting business and financial impact and focus on high-impact risk remediation. The report data varies across discovery profiles depending on the asset and risk information identified. Some key metrics included are known vulnerabilities, exposed servers and public cloud instances, outdated SSL/TLS, domain expiration, and registered lookalike domains highlighting phishing domains for virtual takedown.

These reports can be used in executive- and board-level communication to report on the organization's external posture across dimensions—such as vulnerabilities, exposures, and compliance—aiding in key business decision-making and strategic management and governance. They can also be used in mergers and acquisitions to summarize the inherited risks of a target company, providing visibility into vulnerabilities and misconfigurations without requiring invasive internal scans. These reports also serve auditing purposes to demonstrate continuous monitoring and adherence to regulatory frameworks.

To export reports, your assigned role must include Read permission under the EASM Configuration resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-managing-role-permissions) and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users).

To export a report:

1. In the Zscaler Security Operations (SecOps) platform, go to **Configure**> **EASM Settings**.
2. Locate the required discovery profile, and click the **Export Report**icon. See image. You can also click the **Edit** icon for the discovery profile, then click **Export Report** in the top-right corner of the Edit [Discovery Profile] window. See image. The report is generated and downloaded to your system as a PPTX file.

[Image: Exporting report for discovery profile from the EASM Settings page]

[Image: Exporting report for EASM discovery profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-discovery-profiles","lastmod":"2026-05-22T07:06Z","nid":"1538688"} -->
## Managing Discovery Profiles

- Source: https://help.zscaler.com/uvm/managing-discovery-profiles
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > External Attack Surface Management for UVM > Managing Discovery Profiles
- Last modified: 2026-05-22T07:06Z
- Summary: How to edit and delete discovery profiles created for Zscaler's External Attack Surface Management (EASM) capabilities.

You can manage the [discovery profiles](https://help.zscaler.com/uvm/configuring-discovery-settings) configured for Zscaler's External Attack Surface Management (EASM) capabilities, including editing and deleting them. When you add or remove seeds in a discovery profile, a new discovery scan is triggered to update the asset inventory based on the modified profile.

To manage EASM Discovery Settings, your assigned role must include Read, Edit, and Delete permissions for the EASM Configuration resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-managing-role-permissions) and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users).

## Editing a Discovery Profile

To edit a discovery profile:

1. In the Zscaler Security Operations (SecOps) platform, go to **Configure**> **EASM Settings**. You can also access this page from **Assets** > **Settings** in AEM, or **Vulnerabilities** > **Settings** in UVM.
2. Locate the required discovery profile, and click the **Edit**icon. You can also click the discovery profile entry in the table to edit details. The **Edit [Discovery Profile]** window appears.
3. In the **Edit [Discovery Profile]** window, you can edit the discovery profile name and make changes to the seed configuration by adding or removing seeds. You cannot edit seeds that are already present in the discovery profile. You can remove them and add new seeds to make changes. To learn more, see [Configuring Discovery Settings](https://help.zscaler.com/uvm/configuring-discovery-settings). See image.
4. Click **Save**.

## Deleting a Discovery Profile

Deleting a discovery profile removes all references to the scan data, including discovered assets and associated risk findings, and can no longer be accessed on the platform.

To delete a discovery profile:

1. Go to **Configure**> **EASM Settings**. You can also access this page from **Assets** > **Settings** in AEM, or **Vulnerabilities** > **Settings** in UVM.
2. Locate the required discovery profile, and click the **Delete**icon.
3. In the confirmation window that appears, click **Continue**.

To delete multiple discovery profiles:

1. Go to **Configure**> **EASM Settings**.
2. Select the discovery profiles you want to delete, and click the **Delete** icon displayed in the top-left of the page.
3. In the confirmation window that appears, click **Continue**.

[Image: Editing EASM discovery profile]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-exception-settings","lastmod":"2026-07-09T00:27Z","nid":"1527626"} -->
## Managing Exception Settings

- Source: https://help.zscaler.com/uvm/managing-exception-settings
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Managing Exception Settings
- Last modified: 2026-07-09T00:27Z
- Summary: How to manage exception setting in the Unified Vulnerability Management (UVM) app of the SecOps Platform.

You can set up and manage exception requests within your Unified Vulnerability Management (UVM) application. Users with access to exception settings can enable and customize the exception request process in the account. To learn more about exceptions, see [Understanding Exceptions Requests](https://help.zscaler.com/uvm/understanding-exception-requests).

For access to exceptions settings, your assigned role must include the Read and Edit permissions under the Vulnerabilities App > Exception Settings resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles). To learn more about assigning exception reviewers, see [Reviewing Exception Requests](https://help.zscaler.com/uvm/reviewing-exception-requests).

The exception settings process involves the following steps:

- Configuring the Exception Request Form
- Assigning Reviewers
- Configuring Email Notifications

After customizing the settings, select **Enable to allow exception request submissions for UVM tickets**at the top of the page to activate the **Request Exception**button in the UVM tickets drawer.

See image.

Configure the Exception Request form to capture the necessary information from requesters when they submit an exception request for an extended service level agreement (SLA). This form is displayed when the requester clicks the Exception Request button in the relevant ticket drawer.

The following fields are mandatory and cannot be removed from the form:

- **Requested SLA**: The requested SLA date extension for the ticket.
- **Reason**: The reason the requester is submitting the request. The Reason field is populated by the values configured in the field's content validation on the Data Model page. To customize the reason values:
  1. Go to **Configure**> **Data Model**.
  2. Under **Exception**, select the **Reason**field. The **Reason**drawer appears.
  3. In the **Reason**drawer, click the **Manual Updates**tab.
  4. In the **Content Validation**section, adjust the values as needed. See image.
  5. Click **Save**.

You can add fields to the exception form to capture relevant information for your organization's exception request process.

If you integrate with a third-party exception management system, Zscaler recommends mirroring the required fields in the exception form to maintain consistency and avoid potential errors or misalignments.

To configure the exceptions form:

1. In the **Vulnerabilities**app, go to **Settings**> **Exceptions**.
2. Click **Exceptions Settings**. The **Exceptions**page appears.
3. In the **Request Form Fields**section, click **Add Field**. A drop-down menu appears.
4. Select a field from the drop-down menu. If the field you need is not listed in the drop-down menu, you can create a custom field on the Data Model page. If you don't have access to the Data Model page, contact your admin.
5. (Optional) Hover over the field and select the **Required Field**checkbox. This ensures the field is populated during form submission.
6. (Optional) Drag and drop fields using the **Drag**icon to reorder their appearance in the form.
7. Save the settings in one of the following ways:
  - Click **Save**. The exception settings will apply the next time data is ingested into your account.
  - In the **Save**drop-down menu, click **Save & Run**to save the settings and immediately apply them in your account.

[Image: Content Validation on the Data Model Management page]

Configure assignment rules to automatically assign new exception request submissions to designated reviewers based on shared attributes.

An assignment rule consists of two parts:

- IF Condition: Defines a filter that determines which requests the rule applies to. The IF condition can be configured as structured Conditions or as an Expression.
- Set Reviewer as: Specifies the value to populate the Reviewer field with when the request meets the IF conditions.

The reviewer assignment ruleset includes a default fallback rule with no conditional logic that applies when no other rule conditions are met. This ensures that there is always a default method for populating the Reviewer field. By default, no value is set in the default rule, which leaves the Reviewer field empty. You can edit this rule to adjust the default value, but it cannot be removed or deleted.

To create an assignment rule:

1. In the **Vulnerabilities**app, go to **Settings**> **Exceptions**.
2. Click **Exceptions Settings**. The **Exceptions**page appears.
3. In the **Reviewer Assignment Rules**section, click **Add Rule**. The **Create Unification Rule** drawer appears.
4. In the **Create Unification Rule**drawer:
  1. **Name**: Enter a name for the rule.
  2. **IF**: Define the rule condition that determines which request the rule should apply to. For advanced filtering, click **Expression**in the top-right corner. Supported functions, operators, and references, along with examples, are displayed when clicking the Expression text box.
    1. Select a field that the condition should be based on. Available fields include Exception fields and all fields with a relation to Exceptions.
    2. Select an operator (e.g., **Equals**, **Contains**). Available operators vary depending on the field type, indicated to the left of the field name.
    3. Enter the value that the rule should apply to. Exception Reviewer conditions are not case sensitive.
    4. (Optional) Use **AND**/**OR** logic to define compound rules:
      - **AND**populates the field only if the field meets all conditions in the rule.
      - **OR**populates the field if the field meets any of the conditions in the rule.
  3. **Set Reviewer as**: Select a method to set the value to populate the Reviewer field if the request meets the rule's conditions.
    - **By User**: Select a user from the drop-down menu. The available users in the drop-down menu that you can set as exception reviewers are users assigned the **Audit**permission under the **Vulnerabilities**> **Exceptions Operational View** resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles).
    - **Empty**: Select this method to prevent automatic reviewer assignment for requests that meet the rule's conditions, effectively leaving the **Reviewer**field unassigned.
5. Click **Add**to save the rule.
6. Save the settings in one of the following ways:
  - Click **Save**. The exception settings will apply the next time data is ingested into your account.
  - In the **Save**drop-down menu, click **Save & Run**to save the settings and immediately apply them in your account.

Configure email notifications to alert reviewers when new exception requests are submitted. This helps reviewers stay informed, enabling them to review and process exception requests efficiently and in a timely manner. Configuring email notifications ensures that exception requests are properly routed and processed through your organization's workflow.

To configure email notifications for new exception requests:

1. In the **Vulnerabilities**app, go to **Settings**> **Exceptions**.
2. Click **Exceptions Settings**. The **Exceptions**page appears.
3. Enable **Email Notifications**.
4. Select an option:
  - **Notify assigned reviewer of new exception requests**: Select when you have assignment rules configured and you want to ensure that the designated reviewer is notified directly. An email is sent only to the reviewer assigned to review the request. If no reviewer is assigned to the exception, no email is sent.
  - **Notify all reviewers of new exception requests**: Select if all reviewers are equally responsible for reviewing requests. An email is sent to all reviewers in the account.
5. Save the settings in one of the following ways:
  - Click **Save**. The exception settings will apply the next time data is ingested into your account.
  - In the **Save**drop-down menu, click **Save & Run**to save the settings and immediately apply them in your account.

When the notification is sent for a single request submission, the email includes:

- **Ticket ID**: The unique identifier for the ticket associated with the exception request.
- **Ticket Title**: The title of the ticket associated with the exception request.
- **Requester**: The user who submitted the exception request.
- **Reason**: The reason for the exception request, as specified by the requester.
- **Current SLA**: The current SLA for the ticket.
- **Requested SLA**: The requested SLA extension for the ticket.

When the notification is sent for multiple request submissions, the email includes a link to the Exceptions drawer filtered by the new exception requests.

[Image: Enable exceptions from the Exceptions page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-grouping-rules","lastmod":"2025-09-07T03:05Z","nid":"1530814"} -->
## Managing Grouping Rules

- Source: https://help.zscaler.com/uvm/managing-grouping-rules
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Managing Grouping Rules
- Last modified: 2025-09-07T03:05Z
- Summary: How to manage UVM grouping rulesets and rules.

After [creating grouping rules](https://help.zscaler.com/uvm/configuring-grouping-rules), you can manage the rulesets and the rules they contain to refine how findings are grouped into tickets. To apply changes to the grouping in your account, activate the updated ruleset.

Only one grouping ruleset can be active at any given time.

## Managing Grouping Rulesets

When managing grouping rulesets, you can perform the following actions:

- Activate a Grouping Ruleset
- Clone a Grouping Ruleset
- Edit a Grouping Ruleset
- Delete a Grouping Ruleset

To manage and organize your rules effectively, you can create multiple grouping rulesets. This flexibility allows you to develop new rulesets without altering your existing ones, ensuring your current setup remains intact. However, keep in mind that only one ruleset can be active at a time. The active ruleset is clearly identified to help you track which configuration is currently in use.

See image.

To activate an inactive ruleset:

1. Go to **Vulnerabilities** > **Settings** > **Grouping Rules**.
2. Click the **Column Menu**icon on the ruleset tile.
3. Click **Set as active**. See image. The **Activate Rule Set**window appears.
4. In the **Activate Rule Set** window, enter the text displayed above the text box into the text box, then click **Continue**. After manually activating a ruleset, the previously active ruleset becomes inactive.

To immediately apply changes to the grouping in your account, run the newly activated ruleset to reaggregate findings into tickets. To run the ruleset, click **Run**on the ruleset tile. This reprocesses all data in the account, and applies the new grouping logic to your findings.

See image.

You can clone an existing ruleset to create a new ruleset based on the structure of an existing one. This saves time and allows you to modify the cloned ruleset to fit your specific needs.

To clone a rule set:

1. Go to **Vulnerabilities** > **Settings** > **Grouping Rules**.
2. Click the **Column Menu**icon on the ruleset tile.
3. Click **Clone**. See image. A copy of the ruleset is created with the same name as the original, preceded by the word clone.
4. Click the cloned ruleset to edit it.
5. Enter a name for the ruleset and update the rules as needed.
6. Save the ruleset in one of the following ways:
  1. Click **Save**to save the ruleset. This saves the rules to the ruleset but doesn't reaggregate into tickets based on the ruleset's rules. If the ruleset is left inactive, no changes will apply to the grouping of findings in the account until the ruleset is activated and runs.
  2. In the **Save**drop-down menu, click **Save & Activate**to save and set the ruleset as active. To apply changes immediately, run the activated ruleset to reaggregate findings into tickets; otherwise, changes will take effect during the next data ingestion.

You can edit a ruleset to update its name or adjust its logic as needed.

To edit a ruleset:

1. Go to **Vulnerabilities** > **Settings** > **Grouping Rules**.
2. Click the **Edit** icon on the ruleset. See image.
3. Make the necessary changes.
4. Save your changes.
  - For the active ruleset: Click **Save**to apply the changes during the next data ingestion, or select **Save & Run**from the drop-down menu to immediately apply the changes to the grouping in your account.
  - For inactive rulesets: Click **Save**to update the ruleset, or select **Save & Activate**from the drop-down menu to enable the ruleset and apply its grouping logic in your account. To apply changes immediately, make sure the ruleset is active and then run it.

If a ruleset is no longer needed, you can delete it to remove unnecessary configurations from your account. Deleting the active ruleset can impact data processing, so ensure another ruleset is activated before proceeding.

To delete a rule set:

1. Go to **Vulnerabilities** > **Settings** > **Grouping Rules**.
2. Click the **Column Menu**icon on the ruleset tile.
3. Click **Delete rule set**. See image. The **Confirm Deletion**dialog window appears.
4. In the **Confirm Deletion**dialog window:
  - For the active ruleset, enter the text displayed above the text box into the text box, then click **Delete**.
  - For inactive rulesets, click **Delete**.

## Managing Grouping Rules

Manage grouping rules to control how findings are grouped into tickets within a ruleset. You can clone or edit rules to adjust their logic, or delete rules you no longer need. Each ruleset must include at least one rule in addition to the default fallback rule, which provides a standard grouping method for unmatched findings and cannot be removed.

When managing grouping rules, you can perform the following actions:

- Clone Grouping Rules
- Edit Grouping Rules
- Delete Grouping Rules

Cloning a rule is useful when you need to create multiple rules with similar logic or structure. Instead of building each rule from scratch, you can copy an existing rule and modify only the parts that differ, such as field values, conditions, or grouping criteria.

To clone a grouping rule:

1. Go to **Vulnerabilities** > **Settings** > **Grouping Rules**.
2. Select the ruleset that you want to modify.
3. Hover over the rule you want to clone, and click the **Clone**icon ([Image: clone icon]). A copy of the rule is created with the same name as the original, followed by the word clone.
4. Click the cloned rule to edit it.
5. Enter a name for the rule and update the rule's logic as needed.
6. (Optional) Click **Preview**to verify your rule's logic.
7. Click **Save.**

To update an existing rule's grouping logic, you can edit the rule directly rather than deleting and recreating it.

To edit a rule:

1. Go to **Vulnerabilities** > **Settings** > **Grouping Rules**.
2. Select the ruleset that you want to modify.
3. Hover over the rule you want to edit, and click the **Edit**icon ([Image: edit icon]). The **Edit Rule**drawer appears.
4. Make the necessary changes.
5. Click**Save**.

If your rule is part of the active ruleset, it will apply either when the ruleset is manually run or during the next data ingestion.

You can remove rules that are outdated or no longer relevant due to changes in data structure or grouping logic.

Deleting a rule doesn't trigger a warning message and deletes the rule immediately.

To delete a rule:

1. Go to **Vulnerabilities** > **Settings** > **Grouping Rules**.
2. Select the ruleset that you want to modify.
3. Hover over the rule you want to delete, and click the **Delete**icon ([Image: delete icon]).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-manual-ticket-grouping","lastmod":"2025-11-20T01:08Z","nid":"1533826"} -->
## Managing Manual Ticket Grouping

- Source: https://help.zscaler.com/uvm/managing-manual-ticket-grouping
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Managing Manual Ticket Grouping
- Last modified: 2025-11-20T01:08Z
- Summary: How to manage ticket grouping in the Vulnerabilities app.

An account's grouping rules automatically aggregate findings with similar attributes into tickets, facilitating a productive work process. In addition to automatic grouping rules, the system also offers manual grouping management, allowing isolated grouping adjustments when necessary.

For access to manual ticket grouping, your assigned role must include the Read, Create, and Edit permissions under the Vulnerabilities App - Ticket Operational View resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users).
See image.

[Image: List of roles and their permissions]

There are two general use cases where manual grouping can be used:

- Tickets that need minor restructuring, which can be grouped by:
  - Splitting findings from a single ticket into a new ticket
  - Merging tickets into a single ticket
- To launch a one-time remediation campaign, where manually grouping findings based on meaningful shared attributes into a single work unit can be assigned and tracked. For example, searching for instances of a particular CVE vulnerability located on multiple assets can be grouped into a single ticket for convenient remediation.

Ensure that automatic grouping rules in your account accurately group findings into tickets reflecting your business logic and vulnerability management efforts. Frequent use of manual ticket grouping might require you to revise your account's automatic grouping rules.

## Splitting Findings

You can split findings from a ticket into one or more new tickets. For example, when a specific asset in the ticket cannot be remediated and is blocking full ticket remediation, you can split out the findings on that asset into a separate ticket to allow the ticket to progress toward resolution.

To split findings from an existing ticket into a new ticket:

1. In the SecOps platform, go to **Vulnerabilities**> **Tickets**. The **Tickets**page appears. See image.
2. Click the ticket you want to split. The ticket details drawer appears.
3. Click the **Findings**tab.
4. Select the findings you want to split, using filters if needed. To select all filtered findings, select the checkbox at the top of the list. To select more than 20 findings, click **Select all**<#>**rows**. See image. The **Split Into a New Ticket** drop-down menu appears.
5. From the drop-down menu, select **Split Into a New Ticket** to split all findings into one ticket, or select **Split Into**<#>**New Tickets** to create separate tickets for each finding. See image.
6. In the dialog window, under **New Ticket Title**, enter a name for the tickets. Titles for multiple tickets are numbered sequentially (e.g., Apache vulnerabilities 1, Apache vulnerabilities 2, etc.). See image.
7. Click **Split**. A dialog window appears in the bottom-right corner, where you can click the split ticket when processing is complete.

Splitting a ticket triggers a notification at the bottom right of the page, indicating the split progress. Once the process is complete, click **View Ticket** to review your newly split ticket. You can view the original ticket from which your findings were split in the **Related Tickets**tab of your newly created ticket.

[Image: The Tickets page in the Vulnerabilities app]

[Image: List of findings]

[Image: Split all findings into one ticket]

[Image: Split the selected findings into multiple tickets]

## Merging Tickets

Ticket merging is useful when you want to merge tickets with related findings into one single ticket. A common use case for merging tickets is when multiple tickets contain similar findings and can be resolved using the same fix. You can merge these tickets to create a single work item to assign, track, and remediate.

To merge multiple tickets into one single ticket:

1. In the SecOps platform, go to **Vulnerabilities**> **Tickets**. The **Tickets**page appears.
2. Select the ticket(s) you want to merge, using filters if needed. You can't merge tickets if more than one of them is linked to an external case management system (e.g., Jira issue) to ensure the dispatched task scope remains unchanged. To proceed with the action despite this restriction, unlink the ticket from the external issue.
3. Click **Merge**. See image.
4. Enter a name for the new ticket. See image.
5. Click **Merge**.

Merging tickets triggers a notification at the bottom right of the page, indicating the merge progress. When the process is complete, click **View Ticket** to review your newly merged ticket.

[Image: Select tickets to merge]

**[Image: Merge the selected tickets into a single ticket]**

## Manual Grouping

Manual grouping allows you to manually curate findings into a single ticket. A common use case for manual grouping is when you want to launch a remediation campaign for a particular CVE vulnerability across different assets, and you want to create a single ticket for findings related to this CVE that is dispersed across several tickets.

To manually group a set of related findings into one single ticket:

1. In the SecOps platform, go to **Vulnerabilities**> **Findings**. The **Findings**page appears. See image.
2. Select the finding(s) you want to group, search for the CVE or keyword, or use filters if needed.
3. Click **Create Ticket**. See image.
4. Review the selected findings before proceeding, as this action can potentially affect multiple tickets and deselect irrelevant findings. See image. Removing all findings from a ticket is generally not recommended, and isn't permitted when a ticket is linked to an external case management system. To proceed with the action despite this restriction, unlink the ticket from the external issue.
5. Enter a name for the ticket.
6. Click **Create.**

Manually grouping tickets triggers a notification at the bottom right of the page, indicating the progress. When the process is complete, click **View Ticket** to review your newly created ticket.

[Image: The Findings page in the Vulnerabilities app]

[Image: Select findings to create a new ticket]

[Image: Create a new ticket from the selected findings]

## Analyzing the Impact of Manual Adjustments

You can analyze how manual adjustments can impact critical ticket attributes.

### Activity Log

All manual adjustments made to tickets are saved in the ticket's activity log on the **Activity**tab. This allows you to keep track of all changes made to tickets in the account.

### Locked Scope

Your newly created ticket is locked and won't be affected by automatic grouping rules. This means that the new ticket won't be updated with new findings, and the findings it contains won't be automatically grouped with other tickets.

Manual ticket grouping actions can be performed on locked tickets.

Locked tickets display the Lock icon at the top of the ticket pane.

[Image: Locked ticket displays a lock icon]

### New Ticket Assignment, Scoring, SLA, and Other Attributes

The new ticket's assignment, scoring, service level agreement (SLA), and all other attributes are determined according to the same logic that applies to automatically grouped tickets in your account. For example, if the severity score of your automatically grouped tickets is set according to the finding of the highest severity score in the ticket, then the severity score of your manually adjusted tickets follows the same setting.

### External Case Management Issues

Manual ticket grouping updates can be configured to trigger external issue refresh and update. Contact Zscaler Support to configure this in your account.

#### When Merging Tickets

Ticket merging is only available if no more than one of the merged tickets is linked to an external case management issue. When this is the case, all other selected tickets are merged to the linked ticket. If configured in your account, the external issue is updated with the merged tickets' details, including all new findings.

#### When Splitting Tickets

When splitting findings from a ticket linked to an external case management issue, the original ticket's link is not affected, but the new ticket isn't linked to the external issue. If configured in your account, the external issue is updated with your changes.

#### Manually Grouping Findings

Manually grouping findings into a new ticket can involve splitting findings from tickets that are linked to external case management issues. Splitting findings from such tickets is only possible if at least one finding remains in the linked ticket, or if the ticket link is detached from the external issue. If configured in your account, the external issue is updated to remove the split findings.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-ticket-status","lastmod":"2026-03-16T02:55Z","nid":"1527826"} -->
## Managing Ticket Statuses in UVM

- Source: https://help.zscaler.com/uvm/managing-ticket-status
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Managing Ticket Statuses in UVM
- Last modified: 2026-03-16T02:55Z
- Summary: How to manage ticket status.

You can manage the status of UVM tickets by creating status buckets. A status bucket is a high-level container that logically groups tickets with similar states, allowing you to track the progress and manage workflows efficiently.

[Image: View the statuses of a ticket]

## Creating a Status Bucket

To create a status bucket:

1. Go to the UVM app (**Vulnerabilities**).
2. In the left-side navigation, go to **Settings > Ticket Lifecycle.** The **Ticket Lifecycle** page appears. See image.
3. Click **Ticket Statuses**. The **Ticket Statuses** page appears, displaying the default configuration for statuses and buckets. See image.
4. Click **Add Bucket**. See image.
5. Enter a name for the bucket.
6. (Optional) Select the following checkboxes to add a label to the status bucket: These labels provide insights for metrics and calculations and allow you to view all closed tickets.
  - **Closed**: Indicates that the ticket is closed.
  - **Remediated**: Indicates that the ticket is resolved.
7. Click **Save**. The new status bucket appears on the **Ticket Statuses** page.

## Adding a Status to a Status Bucket

To add a status to a status bucket:

1. Click**Add Status** under the relevant status bucket. See image.
2. Enter a status name.
3. Click **Save**.

[Image: Ticket Lifecycle page]

[Image: The default configuration for buckets and statuses]

[Image: Create a new bucket]

## Editing, Rearranging, and Deleting Status Buckets

To edit a status bucket:

1. Hover over the bucket, and click the **Edit**icon. See image.
2. Edit the bucket name.
3. Add or remove the labels (e.g., **Closed**).
4. Click **Save**.

[Image: Edit a bucket]

Torearrange the order of buckets, hover over the bucket and use the grid icon to drag it to the desired position.

See image.

[Image: Rearrange the order of a bucket]

To delete a bucket, hover over the bucket and click the **Delete Bucket**icon.

See image.

[Image: Delete a bucket]

You can delete only the most recently added bucket.

[Image: Add a status name for the bucket]

## Editing, Rearranging, and Deleting Ticket Statuses

You can only edit a ticket status that is not currently being used for a ticket.

To edit a ticket status:

1. Hover over the ticket status in the status bucket, and click the **Edit**icon. See image.
2. Edit the status name.
3. Click **Save**.

[Image: Edit a ticket status]

Torearrange the order of ticket statuses in a status bucket, hover over the status and use the grid icon to drag it to the desired position.

See image.

[Image: Rearrange the order of a ticket status in a bucket]

To delete a ticket status, hover over the status and click the **Delete Status** icon.

See image.

[Image: Delete a status]

You can delete only the most recently added ticket status.

## Updating Ticket Status

A status timeline is displayed on the top of each ticket, providing a visual cue of the ticket's current status. You can manually change the status of the ticket by selecting a new one from the drop-down menu. To learn more, see [Updating Tickets in UVM](https://help.zscaler.com/uvm/updating-tickets).

See image.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-tickets","lastmod":"2026-02-17T06:06Z","nid":"1528066"} -->
## Managing Tickets in UVM

- Source: https://help.zscaler.com/uvm/managing-tickets
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Managing Tickets in UVM
- Last modified: 2026-02-17T06:06Z
- Summary: How to manage a ticket in the Vulnerabilities app.

You can update details of tickets in the Zscaler Security Operations (SecOps) platform to ensure a clear and efficient remediation workflow.

## Updating the Ticket Owner

To update the owner of a ticket:

1. In the SecOps platform, go to **Vulnerabilities**> **Tickets**. The **Tickets**page appears. See image.
2. Click a ticket. The ticket details drawer appears. See image.
3. On the **Details**tab, click the **Owner**drop-down menu and select an option.
4. Click **Apply Changes**.

[Image: Tickets in the Vulnerabilities app]

[Image: Details of the selected ticket]

## Adding a Comment

You can use comments to update your team on changes made to a ticket, details on splitting findings from tickets, change of assignee, change of severity score, etc.

To add a comment to a ticket:

1. In the SecOps platform, go to **Vulnerabilities**> **Tickets**. The **Tickets**page appears.
2. Click a ticket. The ticket details drawer appears.
3. Click the **Comments**tab and add a comment. See image.
4. Click **Apply Changes**.

[Image: Add ticket comments]

## Updating Ticket Status

A ticket's status reflects the current progress or state of the issue. This is essential for tracking workflows, communicating progress, and ensuring issues are addressed efficiently. To learn more, see [Managing Ticket Statuses in UVM](https://help.zscaler.com/uvm/managing-ticket-status).

To update the status of a ticket:

1. In the SecOps platform, go to **Vulnerabilities**> **Tickets**. The **Tickets**page appears.
2. Click a ticket. The ticket details drawer appears.
3. Click the arrow next to the status progress bar at the top of the drawer.
4. Select the desired status. See image.
5. Click **Apply Changes**.

[Image: Status progress bar shows status of the selected ticket]

## Updating Tickets in Bulk

To apply changes to multiple tickets at the same time:

1. In the SecOps platform, go to **Vulnerabilities**> **Tickets**. The **Tickets**page appears.
2. Select the checkboxes of the tickets you want to update.
3. Click **Update**. See image.
4. Select the options to update (e.g., **Assignee**, **Severity**, etc.).
5. Click **Save**.

[Image: Apply changes to multiple tickets]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/release-upgrade-summary-2025","lastmod":"2026-07-22T10:45Z","nid":"1532348"} -->
## Release Upgrade Summary (2025)

- Source: https://help.zscaler.com/uvm/release-upgrade-summary-2025
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Release Notes > Release Upgrade Summary (2025)
- Last modified: 2026-07-22T10:45Z
- Summary: Unified Vulnerability Management (UVM) Release Upgrade Summary for service updates deployed in 2025.

This article provides a summary of all new features and enhancements for Unified Vulnerability Management (UVM).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/release-upgrade-summary-2026","lastmod":"2026-08-19T07:40Z","nid":"1534299"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/uvm/release-upgrade-summary-2026
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-08-19T07:40Z
- Summary: Unified Vulnerability Management (UVM) Release Upgrade Summary for commercial service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Unified Vulnerability Management (UVM).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/requesting-exceptions","lastmod":"2025-11-20T01:20Z","nid":"1527636"} -->
## Requesting Exceptions

- Source: https://help.zscaler.com/uvm/requesting-exceptions
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Requesting Exceptions
- Last modified: 2025-11-20T01:20Z
- Summary: How to submit exception requests to extend the SLA date of UVM tickets.

When a ticket cannot be remediated within its designated service level agreement (SLA) due to technical limitations, an unavailable fix, or unacceptable business impact, you can submit an exception request to temporarily exempt the ticket from published security policies. When initiating the exception request, you'll need to provide justification and submit necessary supporting evidence for review. The request is then assessed by a reviewer, who either approves or denies it. To learn more, see [Understanding Exception Requests](https://help.zscaler.com/uvm/understanding-exception-requests).

If Exceptions is enabled in your account, you can submit exception requests.

## Submitting Exception Requests

Before submitting an exception request, make sure the ticket contains only the findings you want the exception to cover. An exception applies to the entire ticket, not individual findings within it.

- To request an exception for a subset of a ticket's findings, split the relevant findings into a new, separate ticket. To learn more, see [Managing Manual Ticket Grouping](https://help.zscaler.com/uvm/managing-manual-ticket-grouping).
- To request an exception for findings across multiple tickets, merge the relevant tickets or findings into a single, consolidated ticket. To learn more, see [About Tickets](https://help.zscaler.com/uvm/about-tickets).

When your findings are grouped into a single ticket, you can request an exception.

To create an exception request:

1. Go to **Vulnerabilities** > **Tickets**.
2. In the table, click the ticket for which you want to request an exception. The **Ticket**drawer appears.
3. On the **Details** tab, click **Request Exception**. See image. The **Request Exception**dialog window appears.
4. In the **Request Exception**dialog window:
  1. **Reason**: Select a reason for the exception from the drop-down menu.
  2. **Requested SLA**: Select the date for the ticket's SLA extension. Additional fields might appear depending on your organization's settings.
5. (Optional) Click **Add Attachment**to upload documents that can support justification for the request.
6. Click **Submit**.

After submitting, your request is routed for review. You can track your submission directly within the ticket it was created for, or from the Exceptions page. On the Exceptions page, filter by Requester Name to see all your requests and click Save As View to [create a saved view](https://help.zscaler.com/uvm/creating-managing-saved-views) for quick access to your requests. To manage a specific request, select the exception and add comments, upload attachments, or review the request's activity.

If no designated reviewers are assigned in your account, the Reviewer field remains empty by default. This typically means all users with reviewer permissions are responsible for reviewing exceptions.

By default, when submitting an exception request, the approved SLA field in the exception is auto-populated with the requested SLA. The ticket's SLA field updates with the Requested SLA if the request is approved.

### Understanding Exception Statuses

The status of your request reflects the reviewer's decision and is shown in the top-right corner of the exception drawer.

- Approved: The request was accepted. The ticket's SLA date automatically updates to the approved date, and the ticket is locked to prevent new findings from being added.
- Denied: The request was rejected, and the ticket's original SLA date remains unchanged.
- Cancelled: The request was automatically cancelled because the ticket's SLA was updated to a later date than the one you requested. This can happen if: You cannot manually cancel a request. If it's no longer needed, add a comment notifying the reviewer.
  - The ticket's SLA date was manually changed to a later date.
  - A new finding with lower severity was added to the ticket, consequently pushing the ticket's SLA date.
  - Findings with critical or high severity were split from the ticket to a new ticket.

## Syncing Exceptions with External Systems

If your organization uses an outegration (e.g., ServiceNow IRM) to track exceptions, you can sync your request. To learn more, see [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations).

To sync an exception request with an external system:

1. Go to **Vulnerabilities**> **Exceptions**.
2. In the table, click the exception request you want to sync.
3. Click **Create**<Outegration> **Ticket**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/reviewing-exception-requests","lastmod":"2025-09-09T00:19Z","nid":"1527631"} -->
## Reviewing Exception Requests

- Source: https://help.zscaler.com/uvm/reviewing-exception-requests
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Reviewing Exception Requests
- Last modified: 2025-09-09T00:19Z
- Summary: How to review exception requests submitted for UVM tickets.

If a ticket cannot be remediated by its service level agreement (SLA) date, remediation owners can submit an exception request for an extension. Following submission, a reviewer assesses the request and either approves or denies it based on organizational policies. To learn more, see [Understanding Exception Requests](https://help.zscaler.com/uvm/understanding-exception-requests).

For access to review exceptions, your assigned role must include the Read, Create, Edit, Delete, and Audit permissions under the Vulnerabilities App > Exception Operational View resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users). 
See image.

To review requests, go to Vulnerabilities > Exceptions. The Exceptions page is your central hub for managing all submitted requests, featuring charts for high-level insights and a detailed exceptions table for managing individual submissions. To view the requests assigned to you, filter by Reviewer Name. You can also add or remove columns, sort data, and apply additional filters to adjust the view to your needs. To learn more, see [Filtering Operational Views](https://help.zscaler.com/uvm/filtering-operational-views) and [Creating & Managing Saved Views](https://help.zscaler.com/uvm/creating-managing-saved-views).

The Exceptions page features charts that represent key exception insights, helping you to identify trends and patterns and optimize the exception review process.

- Exceptions by Ticket Severity
- Active Tickets by Exception Status Buckets
- Exceptions by Requester

The exceptions table, located below the charts, lists all exception requests for your account. Use this table to track and manage submissions throughout the review process. By default, the table displays key information about each request, including its ID, Status, Reason, and Requested SLA. It also shows details from the original ticket, such as Ticket SLA (the ticket's original SLA), Ticket Severity, Ticket Title, and Ticket ID.

If reviewer assignment rules are configured in your account, the assigned reviewers are displayed in the Reviewer column. You can filter this column to display the requests assigned to you.

## Auditing Exception Requests

To view the request submission's details, click an exception request. The exception drawer appears, displaying a comprehensive view of the request details.

See image.

In the top right of the exception drawer, you can view and modify the status of the request so the requester can keep track of where the request is in the review process.

To audit an exception request:

1. Go to **Vulnerabilities**> **Exceptions**.
2. Click the exception request you want to audit. You can also drill down to the exception request from the **Exception Requests**section on the **Details**tab of the ticket the request was created for.
3. Review the request details:
  - **Requested SLA**: The SLA date that the requester is asking for.
  - **Approved SLA**: This field defaults to the requested SLA but you can modify it to grant a different SLA extension date.
  - **Reason and Attachments**: The justification provided by the requester.
  - **Original SLA**: The first SLA date assigned to the ticket for which the request was submitted.
  - **Current SLA**: The SLA date currently assigned to the ticket, which can be different from the original due to new findings that were added to the ticket or to previously approved exception requests for the ticket.
4. Choose an audit decision:
  - **Approve**: If the request is valid, click **Approve**. The request status updates to **Approved**, the ticket's SLA is automatically extended, and the ticket is locked to prevent new findings from being added to it.
  - **Approve with a Modified Date**: If you agree with the request but want to grant a different extension date, first change the **Approved SLA**field value, click **Apply Changes**, and then click **Approve**. The exception request is automatically cancelled if the ticket's SLA is extended to a date later than the requested SLA. See image.
  - **Deny**: If the request is unjustified, click **Deny**. The status updates to **Denied**, and the ticket's original SLA remains in effect.

## Syncing Exceptions with External Systems

If your organization uses an integration (e.g., ServiceNow IRM) to track exceptions, you can sync your request.

To sync an exception request with an external system:

1. Go to **Vulnerabilities**> **Exceptions**.
2. In the table, click the exception request you want to sync.
3. Click **Create**<Outegration> **Ticket**.

This chart displays the distribution of exceptions by ticket severity, enabling you to visualize the severity landscape and focus on high-priority exceptions that require immediate attention.

See image.

This chart categorizes active tickets by their exception status. The exception statuses reflect the progression of the review process, giving you a high-level understanding of the overall status of exceptions within your organization.

See image.

The displayed statuses vary depending on the configured exception statuses in your account. The default exception statuses include:

- Requested: Exceptions that have been submitted for approval but are pending acknowledgment, indicating that the review process has not yet begun.
- Under Review: Exceptions that have been acknowledged and are actively being evaluated for exception approval.
- Denied: Exceptions that have been rejected, resulting in no change to the original SLA of the associated ticket.
- Cancelled: Exceptions that were automatically cancelled as a result of the ticket's SLA date updating to a later date than the requested extension, effectively ending the exception request process.
- Approved: Exceptions that have been approved, granting an extended SLA for the associated ticket.

This chart displays the distribution of exception requests by individual requester, providing insight into the frequency and volume of requests submitted by each user.

See image.

[Image: exceptions by requester overview chart]

[Image: exception request drawer details tab]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/triggering-asset-scans-uvm","lastmod":"2026-08-13T05:20Z","nid":"1535394"} -->
## Triggering Asset Scans in UVM

- Source: https://help.zscaler.com/uvm/triggering-asset-scans-uvm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Triggering Asset Scans in UVM
- Last modified: 2026-08-13T05:20Z
- Summary: How to trigger asset scans on a UVM asset.

This feature is in limited availability. To learn more, contact Zscaler Support.

Zscaler Unified Vulnerability Management (UVM) enables you to trigger on-demand asset scans directly from the Zscaler Security Operations (SecOps) platform. By initiating scans immediately rather than waiting for scheduled cycles, you can accelerate validation workflows and instantly verify that patches have been successfully applied to your assets and findings are no longer being detected.

## Prerequisites

Before you can trigger a scan, ensure the following requirements are met:

- The assets you intend to scan must be ingested and managed by a configured data source connector. To learn more, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).
- You must have a configured and active scanner outegration to perform the scan. To learn more, see [Creating Outegrations](https://help.zscaler.com/unified/creating-outegrations).
- Your assigned role must include the Read and Edit permissions under the Vulnerabilities App - Asset Operational View resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/unified/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/unified/managing-user-roles).

The SecOps platform currently supports the Tenable Vulnerability Management and the Tenable Security Center scanner outegrations. To learn more, see [Configuring the Tenable Outegration](https://help.zscaler.com/uvm/configuring-tenable-outegration).

## Triggering a Scan

You can trigger scans on assets directly from UVM. When initiated, the SecOps platform sends the scan request to your scanner (e.g., Tenable). The scanner queues and executes the job according to its own scheduling and capacity. When the scan is completed, updated asset data is made available in the SecOps platform. Results might be subject to a short delay while queued Extract, Transform, Load (ETL) operations are processed.

Scans can be triggered only on assets ingested from a source that has a corresponding outegration configured. 
Scans cannot be stopped in UVM after they are triggered. To cancel an ongoing scan, you must stop it directly through the scanner interface.

You can trigger asset scans from the following locations:

- Assets page
- Asset drawer
- Ticket drawer

The maximum number of scans that can run concurrently is determined by the Max Number of Parallel Scans setting in your scanner outegration configuration (e.g., the [Tenable scanner outegration](https://help.zscaler.com/uvm/configuring-tenable-outegration)). This value defines how many scans the SecOps platform can initiate and process at the same time for a given scanner outegration.

### Tracking Scan Status

After you trigger a scan, you can monitor its progress and completion status through the activity log for your scanner outegration. The activity log provides real-time updates so you can track each scan from initiation through completion. To learn more, see [Managing Outegrations](https://help.zscaler.com/unified/managing-outegrations#outegration-activity-log).

The time required to complete a scan depends on several factors, including the number of selected assets, scanner capacity limits, and current workload. You might also see multiple scans in progress if your outegration is configured to allow parallel execution. When the scan is complete, the outegration automatically polls for final results and updates the corresponding asset data in the SecOps platform. Results can be subject to a brief delay while queued ETL operations are processed within the platform.

You can trigger scans for one or more assets directly from the Assets page.

To trigger a scan from the Assets page:

1. Click **Vulnerabilities**(the UVM app) in the top navigation bar.
2. In the left-side navigation, click **Assets**.
3. Select the asset(s) you want to scan. A maximum of 10 assets can be selected per scan.
4. Click **Trigger Scan**.
5. Select the scanner you want to trigger from the list (e.g., **Trigger Tenable Vulnerability Management**). See image. The **Trigger <external scanner> Scan**dialog window appears.
6. Click **Continue**.

You can trigger a scan while investigating the details of a specific asset.

To trigger a scan from an asset drawer:

1. Click **Vulnerabilities**(the UVM app) in the top navigation bar.
2. In the left-side navigation, click **Assets**.
3. Click the asset you want to scan. The asset drawer appears.
4. In the asset drawer, click **Act**in the bottom-right corner.
5. Select the scanner you want to trigger from the list (e.g., **Tenable Vulnerability Management**). See image. The **Trigger <external scanner> Scan**dialog window appears.
6. Click **Continue**.

You can trigger scans for assets associated with a specific remediation ticket to verify they have been patched.

To trigger a scan from a ticket drawer:

1. Click **Vulnerabilities**(the UVM app) in the top navigation bar.
2. In the left-side navigation, click **Tickets**.
3. Click the ticket whose related assets you want to scan. The ticket drawer appears.
4. Select the **Assets**tab.
5. Select the asset(s) you want to scan. A maximum of 10 assets can be selected per scan.
6. Click **Trigger Scan**.
7. Select the scanner you want to trigger from the list (e.g., **Trigger Tenable Vulnerability Management**). See image. The **Trigger <external scanner> Scan**dialog window appears.
8. Click **Continue**.

[Image: Trigger Scan drop-down menu on the Assets page]

[Image: Act drop-down menu in asset drawer]

[Image: Trigger Scan drop-down menu on the Assets tab in the ticket drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-asset-discovery-zscaler-easm","lastmod":"2026-05-22T07:06Z","nid":"1538683"} -->
## Understanding Asset Discovery with Zscaler EASM

- Source: https://help.zscaler.com/uvm/understanding-asset-discovery-zscaler-easm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > External Attack Surface Management for UVM > Understanding Asset Discovery with Zscaler EASM
- Last modified: 2026-05-22T07:06Z
- Summary: Information on EASM asset discovery capabilities and benefits offered within AEM and UVM apps.

Asset discovery, a core function of External Attack Surface Management (EASM), allows organizations to identify, inventory, and continuously monitor their internet-facing assets. It involves discovering all internet-facing assets an organization owns or manages—such as domains, hosts, web pages, certificates, and IP addresses—that are exposed to the public internet. The discovery process ensures that even unknown, unmanaged, or forgotten assets (often referred to as "shadow IT") are identified and brought under security management. As part of the discovery process, assets are investigated for risk exposures, such as vulnerabilities and misconfigurations, to provide deeper insights into an organization's external attack surface and help identify and remediate security risks.

Effective asset discovery enables organizations to:

- **Gain Visibility**: Establish a complete, unified inventory of all internet-facing assets, which is critical for managing external threats.
- **Identify Risks**: Proactively uncover risk exposures from both known and unknown assets.
- **Prioritize Remediation**: Prioritize mitigation of critical vulnerabilities and risks associated with discovered assets.
- **Monitor Changes**: Continuously track changes in the asset inventory and risk exposures, allowing security teams to respond quickly to evolving threats.

To learn more, see [Understanding Zscaler EASM Capabilities](https://help.zscaler.com/uvm/understanding-zscaler-easm-capabilities).

## How Asset Discovery Works

Zscaler's EASM capabilities employ a multi-faceted discovery approach to continuously map internet-exposed assets. The discovery process is initiated using one or more seed assets, which are known, legitimate assets (e.g., domain, IP address, or IP block) belonging to the organization that you can configure. The discovery engine starts scanning the seed asset and discovers assets that are directly linked to the seed. Then, it scans the first layer of connected assets identified from the seed to map their related assets, and further expands the discovery net to subsequent layers of connections, ultimately mapping all assets connected to the organization as a web of nodes.

EASM capabilities use both passive and active scanning methods to continuously discover and inventory all internet-facing assets, including domains, subdomains, IP addresses, cloud provider instances, web applications, certificates, and more. This includes not only known assets but also forgotten or unknown assets in your digital infrastructure that might have been overlooked. It provides comprehensive asset coverage by utilizing a diverse array of discovery methods and data sources, including WHOIS reverse lookup, ICANN regex analysis, DNS record lookup, web content analysis, certificate transparency logs (CTLogs), GitHub repository scanning, Nuclei patterns, SSL/TLS certificate analysis, LLM subnet and IP inferencing, and more. It automatically validates ownership of the discovered assets by using a proprietary LLM. It analyzes WHOIS data of seed domains and associated assets, comparing fields, such as "Registrant Organization" and "Registrant Email" against seed domains to verify asset attribution to the organization.

In addition to discovery, EASM capabilities provide deeper visibility into asset risk by identifying critical exposures such as CVEs, misconfigurations, open ports, expired certificates or domains, phishing and lookalike domains, exposed database services, outdated TLS versions, and more. It provides out-of-the-box risk scoring to help security teams prioritize high-impact risks and vulnerabilities for remediation. This risk score is further integrated with other risk contexts uncovered by the Zscaler Security Operations (SecOps) platform to provide a more accurate, sophisticated risk score, which is customized to align with specific organizational needs and priorities. By combining EASM insights with Zscaler's extensive connector ecosystem, organizations can establish a unified, risk-prioritized inventory to strengthen their security posture.

The following key steps outline the asset discovery and risk identification processes with EASM capabilities:

1. **Seed-Based Scanning**: The discovery process starts by scanning a seed asset and mapping its direct connections and discovering related assets.
2. **Multi-Layered Discovery**: When the first level of assets connected to the seed is found, the subsequent levels of connections are recursively scanned, ultimately building a comprehensive map of the organization's attack surface.
3. **Asset Attribution**: Asset ownership is automatically validated by analyzing relevant scan data (e.g., WHOIS data) of seed assets and associated assets, and a discovery chain is presented for source traceability and attestation of the asset's connection to the organization.
4. **Risk Assessment**: Each discovered asset is evaluated against a set of risk parameters to identify known vulnerabilities, misconfigurations, exposed sensitive services, etc.
5. **Asset Exposure and Vulnerability Management**: Built natively into the Zscaler Exposure Management platform, EASM integration with Zscaler Asset Exposure Management (AEM) and Unified Vulnerability Management (UVM), allows you to manage asset and risk exposure end-to-end by leveraging AEM's policy framework, UVM's automated ticket workflows, and other integrated capabilities. To learn more, see [What Is Zscaler Security Operations?](https://help.zscaler.com/uvm/what-zscaler-security-operations)
6. **Automated Monitoring**: The organization's external attack surface is continuously monitored through weekly scans to provide accurate visibility into the organization's current risk exposure.

Seed assets are configured using [discovery profiles](https://help.zscaler.com/uvm/configuring-discovery-settings). You can create distinct discovery profiles for different business entities (e.g., parent companies, subsidiaries), allowing organizations to have granular control over how they manage their external attack surface.

To learn how to set up asset discovery for EASM, see [Configuring Discovery Settings](https://help.zscaler.com/uvm/configuring-discovery-settings).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-exception-requests","lastmod":"2026-07-14T21:06Z","nid":"1527696"} -->
## Understanding Exception Requests

- Source: https://help.zscaler.com/uvm/understanding-exception-requests
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Understanding Exception Requests
- Last modified: 2026-07-14T21:06Z
- Summary: Information about exceptions in the Unified Vulnerability Management (UVM) app, including exception usage and the different roles involved in the exception lifecycle.

Exception requests are a critical component of vulnerability management. They provide a structured process for organizations to temporarily exempt vulnerabilities or remediation tasks from published security policies, standards, or guidelines when remediation cannot be completed within designated timeframes.

## Common Use Cases for Requesting Exceptions

Common use cases for extending vulnerability remediation timelines include:

- Technical or operational limitations: The vulnerability resides in legacy systems, custom applications, or critical infrastructure where applying a fix would introduce significant instability, is technically unfeasible without substantial re-engineering, or requires extensive testing cycles that exceed standard remediation windows.
- Unavailable fix: A patch, fix, or viable workaround is currently unavailable from the vendor or internal development teams.
- Unacceptable business impact: Remediation efforts (e.g., system downtime, major reconfigurations, extensive testing) would severely disrupt critical business operations, impact revenue, or compromise essential services beyond an acceptable level.

## Key Roles in the Exception Request Process

The following primary personas are involved in the exception request process in vulnerability management:

- Exceptions Manager: Configures exceptions settings, defines user permissions for requesting exceptions, and sets rules to assign exception reviewers. To learn more, see [Managing Exception Settings](https://help.zscaler.com/uvm/managing-exception-settings).
- Requester: Typically a remediation owner who is assigned remediation tasks that might require a timeline extension. They are responsible for initiating the exception request, providing justification, and submitting necessary supporting evidence for review. To learn more, see [Requesting Exceptions](https://help.zscaler.com/uvm/requesting-exceptions).
- Reviewer: Designated reviewers who assess exception requests. They evaluate the associated risks, verify the justification and evidence, and make a decision to approve or deny the request based on organizational policies and risk acceptance criteria. To learn more, see [Reviewing Exception Requests](https://help.zscaler.com/uvm/reviewing-exception-requests).

## Exception Request Lifecycle

The Unified Vulnerability Management (UVM) exception lifecycle starts with the submission of an exception request, which is routed for review using the configured review process in the account's exception settings. The review process results in either approval or denial. Approved exceptions are managed and monitored on the Exceptions page.

### Requesting an Exception

The exception lifecycle begins when a remediation owner determines they cannot meet the deadline for an assigned remediation ticket. They submit an exception request through the Exception Request form in the ticket that requires an extension, providing a detailed justification, supporting evidence, and a proposed new remediation date.

A new exception is created in the Exceptions View with the Requested status. This exception is automatically linked to the source remediation ticket that is awaiting assessment.

### Reviewing an Exception Request

The system routes the requested exception to the appropriate reviewer based on configured assignment rules. The reviewer assesses the request against organizational policies and risk acceptance criteria, evaluating the business justification, technical feasibility, and potential risk exposure before making a final decision to approve or deny the request. The decision immediately updates the exception's status and dictates the next steps:

- If Approved: The exception's status changes to Approved, and the due date on the associated remediation ticket is automatically deferred to the new date.
- If Denied: The exception's status changes to Denied. The request is rejected, and the original remediation timeline for the ticket remains in effect.

### Tracking an Exception Request

The status of the exception request can be tracked in two locations to ensure ongoing visibility for all parties:

- Exceptions view: A centralized dashboard to view and manage all exception requests across the organization.
- Ticket's details tab: All exception requests linked to the ticket appear on the ticket's Details tab, displaying the exception status and the relevant exception updates.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-severity-scores","lastmod":"2026-08-09T05:59Z","nid":"1527926"} -->
## Understanding Severity Scores

- Source: https://help.zscaler.com/uvm/understanding-severity-scores
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Understanding Severity Scores
- Last modified: 2026-08-09T05:59Z
- Summary: Information on how Unified Vulnerability Management (UVM) severity scores are calculated.

In vulnerability management, vulnerabilities are given severity scores that are used to prioritize remediation efforts. Severity scores typically range from 0 to 10, where a higher score indicates greater criticality requiring immediate attention and resolution, and thus takes precedence over vulnerabilities with lower scores.

Standard severity scoring frameworks like CVSS and EPSS, as well as scanner-provided scores, often fall short in addressing an organization's unique context.

Unified Vulnerability Management (UVM) calculates severity scores based on context and incorporates data from various industry tools and sources, providing more effective and accurate risk assessment. To learn more, see [Configuring Severity Score Formulas](https://help.zscaler.com/uvm/configuring-severity-score-formulas).

The score context is divided into two categories:

- Base score: The initial numerical value derived from processing all score inputs gathered from a particular finding that serves as a foundational reference point. The base score is the main component that carries most of the weight in determining the overall score. It grounds the evaluation in the numerical values obtained from the input data.
- Risk and mitigating factors: Additional factors tailored to the unique context of the organization that can shift the score depending on their weight. Assigning more weight to risk and mitigating factors implies that they have a substantial impact on the final score, while less weight indicates that the base score remains the dominant one. For example, risks such as public access raise the severity score, while mitigating factors such as firewalls lower the severity score.

By taking into account specific risks and mitigating factors within an organization, UVM adjusts the initial score and provides an updated severity score. This allows for a more accurate assessment of the actual level of criticality associated with each finding.

When reviewing your tickets on the [Tickets](https://help.zscaler.com/uvm/tickets-view) page, each ticket displays both the original and updated severity scores for comparison purposes.

See image.

You can drill down into the details of the severity score by clicking a finding. Here you can see the initial score, score calculations, and adjustments made based on the configured risk and mitigating factors.

See image.

[Image: Severity scores for a ticket]

[Image: Severity score details]

## How the Severity Score Is Calculated

The total severity score is composed of the base score and the risk and mitigating factors, and is calculated per finding. The total score must be 100% or higher to save the score setting. The score is calculated for each component (i.e., base score factors and risk and mitigating factors) separately. The two separate calculations are then summed to make up the final finding severity score.

The following example assumes a balance of 60% for the base score and 40% for the risk and mitigating factors score.

- Calculating Each Component Separately
- Summing the Total Score

First, the base score is calculated and the risk and mitigating score is calculated.

### Calculating the Base Score

Suppose you assign the base score a total weight of 60%. You then distribute this percentage between the CVSS score (40%) and the EPSS score (20%).

Base Score = Finding's CVSS score * 0.4 + Finding's EPSS score * 0.2

You can also add the Original Severity Score as part of the base factor. If you do, the original severity score serves as the fallback score when the CVSS and EPSS scores are missing. To learn more, see [Configuring Severity Score Formulas](https://help.zscaler.com/uvm/configuring-severity-score-formulas).

### Calculating Risk and Mitigating Factors

Since the base score's contribution is 60% of the total score, the risk and mitigating factors receive a weight of 40% of the total score.

- Risk factors increase a finding's severity score, so the result is added to the finding's score.
- Mitigating factors decrease a finding's score, so the result is subtracted from the finding's score.

The formula for calculating a factor's contribution is Risk and Mitigating Factor = 10 (highest possible score) * weight given to the factor.

For example, you can configure risk factors that increase a finding's severity score if the asset it's found on is a crown jewel, if the asset it's found on has PII, or if the finding is a CISA known exploit.

| Risk Factor | Weight | Contribution |
| --- | --- | --- |
| Asset Is Crown Jewel | 20% | + 10 * 0.2 |
| Asset Has PII | 12% | + 10 * 0.12 |
| CISA Known Exploited | 10% | + 10 * 0.1 |
| Total | 42% | + 4.2 |

Additionally, you can add mitigating factors that decrease the finding's severity score if the asset it's found on is behind a firewall or if it has EDR installed on it.

| Mitigating Factor | Weight | Contribution |
| --- | --- | --- |
| Asset Is Behind Firewall | 8% | - 10 * 0.08 |
| Asset Has EDR | 10% | - 10 * 0.1 |
| Total | 18% | - 1.8 |

The total contribution of the risk and mitigating factors is 4.2 - 1.8 = 2.4. The 2.4 score is then given a weighted contribution depending on the weight given to the base factor (in our example, 40% or 0.4).

For this calculation, the base score and the risk and mitigating factors are summed with their assigned relative weights. In our example, 0.6 * Base Score + 0.4 (completing the score to 100%) * 2.4 (factor contribution).

### When the Total Score Exceeds 100%

If the total score exceeds 100%, the calculation caps the risk and mitigating share of the total score to bring it back to 100%. This process is always according to the base score percentage. For example, if the base score and risk and mitigating factors are set equally at 50% and 50%, and we change the base score to 70%, the percentage of the factors is reduced to 30%. To allow the risk and mitigating factors to have more influence, reduce the weight of the base score.

### When the Total Score Is Below 100%

To ensure every finding's score always sums to a complete 100%, the score uses a dynamic weight allocation mechanism. If the risk and mitigating factors don't use their full allocated weight (e.g., if a factor is null or not applicable to a specific finding), the unused portion of their allocated weight is proportionally reallocated to the individual factors of the base score.

For example, consider the following score allocation:

Base score (60% of total score):

- CVSS 40%
- EPSS 20%

Risk and Mitigating Factors (60% > capped at 40%):

- Asset Is Crown Jewel - 20%
- Asset Has PII - 12%
- CISA Known Exploited - 10%
- Asset Is Behind Firewall - 8%
- Asset Has EDR - 10%

If the risk and mitigating factors contribute only 30% to a finding's score, leaving 10% unused from their 40% allocation, the unused 10% is added to the base score. This 10% increases the base score's total contribution from 60% to 70%, and is proportionally distributed to CVSS and EPSS based on their original shares within the base score (CVSS gains 6.67%, EPSS gains 3.33%).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-uvm-measurement-terminology","lastmod":"2026-08-16T01:06Z","nid":"1533637"} -->
## Understanding UVM Measurement Terminology

- Source: https://help.zscaler.com/uvm/understanding-uvm-measurement-terminology
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analyze for UVM > Understanding UVM Measurement Terminology
- Last modified: 2026-08-16T01:06Z
- Summary: Describes built-in measurements used in UVM's dashboards and reports.

This article describes built-in measurements used in Unified Vulnerability Management (UVM)'s dashboards and reports. These measurements depend on the states and statuses of tickets. To learn more, see [About Tickets](https://help.zscaler.com/uvm/about-tickets).

Additional measurements can be added upon request, and default measurements' calculations can be overridden according to specific needs. Contact your Zscaler Account team for more details.

## Status Buckets

A ticket's status indicates its current stage in the workflow at any given time. Ticket statuses are arranged in buckets, which ensures that the ticket workflow remains logical and coherent. You can add a new status under a bucket, allowing you to add a descriptive label to a ticket. You can change the name of a status and status bucket.

To access the Ticket Statuses page:

1. In the SecOps platform, go to **Vulnerabilities**> **Settings**> **Ticket Lifecycle**. The **Ticket Lifecycle** page appears.
2. Click **Ticket Statuses**. [Image: Descriptive labels for ticket statuses]
3. To add a new bucket, click **Add Bucket.**
4. To add a new status, click **Add Status**under a bucket.
5. Click **Done**to exit the page.

## Ticket States

Tickets can have the following states:

- **Active**: The ticket has at least one active finding.
- **Inactive**: All findings under the ticket are undetected. The ticket is waiting to be confirmed as closed.
- **Archived**: A ticket no longer contains any findings (e.g., due to a change in grouping settings or the merging of tickets).

## Ticket Measurements

The following table shows information about ticket measurements:

| **Name** | **Description** |
| --- | --- |
| New Tickets | The number of tickets created in the set date range. |
| Total Open Tickets | The number of tickets set as Active or Inactive that are not in the Closed bucket. |
| Opened Tickets | The number of tickets that were set for the Open bucket during the set date range. This includes new tickets that were set to a status from the Open bucket and tickets that were reopened in the set date range. |
| Active Tickets (Open) | The number of tickets set as Active that are not in the Closed bucket. This measurement does not include inactive tickets. |
| Total Active Tickets | The number of tickets set as active, no matter their status. |
| Total Tickets Over SLA | The number of tickets that went over their service level agreement (SLA) date. For tickets not in the Closed bucket, over SLA is considered when the set date range is after the set SLA for the ticket. For tickets in the Closed bucket, over SLA is considered when the date the ticket was moved to the Closed bucket was after the set SLA date. |
| Total Tickets In SLA | The number of tickets that are within their SLA date or don't have an SLA date. For tickets not in the Closed bucket, in SLA is considered when the set SLA for the ticket is after the end of the set date range. For tickets in the Closed bucket, in SLA is considered when the date the ticket was moved to the Closed bucket was before the set SLA date. |
| Open Tickets Over SLA | The number of tickets not in the Closed bucket that went over their SLA date. Over SLA is considered when the set date range is over the set SLA for the ticket. |
| Open Ticket In SLA | The number of tickets not in the Closed bucket that are either within their SLA date or without an SLA date. In SLA is considered when the set SLA for the ticket is after the end of the set date range. |
| Total Closed Tickets | The number of tickets in the Closed bucket in the set date range. This includes tickets closed before the set date range. |
| Closed Tickets | The number of tickets that were set to the Closed bucket during the set date range. This measurement only counts tickets that were set to the Closed bucket within the set date range; tickets closed prior to the set date range are not counted. |
| Total Tickets | The number of tickets created up to the end of the set date range. |
| Critical Open Tickets | The number of tickets set as Active or Inactive with critical severity that are not in the Closed bucket. |
| High Open Tickets | The number of tickets set as Active or Inactive with high severity that are not in the Closed bucket. |
| Medium Open Tickets | The number of tickets set as Active or Inactive with medium severity that are not in the Closed bucket. |
| Low Open Tickets | The number of tickets set as Active or Inactive that are not in the Closed bucket with low severity. |
| Info Open Tickets | The number of tickets set as Active or Inactive with info severity that are not in the Closed bucket. |
| New Tickets Last 7 Days | The number of tickets created in the last 7 days. |
| Closed Tickets Last 7 Days | The number of tickets that were set to the Closed bucket in the last 7 days. |
| Undetected Tickets Last 7 Days | The number of tickets that were set as Inactive in the last 7 days. |
| Total Remediated Tickets | The number of tickets with a status from the Remediated bucket. |
| Remediated Tickets | The number of tickets that were set to a status from the Remediated bucket during the set date range. This measurement only counts tickets that were set to the Remediated bucket within the set date range; tickets set as Remediated prior to the set date range are not counted. |
| Undetected Tickets | The number of tickets that were set to an Inactive state (meaning all their findings were set as undetected) during the set date range. This measurement only counts tickets that turned inactive in the set date range. |
| Total Undetected Tickets | The number of tickets with an Inactive state in the set date range. |

### Percentage Measurements

The following table shows information about percentage measurements in tickets:

| **Name** | **Expression** |
| --- | --- |
| % Total Tickets Over SLA | Tickets Over SLA / Total Tickets |
| % Total Tickets In SLA | Tickets In SLA / Total Tickets |
| % Remediation | Inactive findings / Total findings |
| % Open Tickets Over SLA | Open Tickets Over SLA / Total Open Tickets |
| % Open Tickets In SLA | Open Tickets In SLA / Total Open Tickets |

### Time to Measurements

The following table shows information about ticket time measurements:

| **Name** | **Description** | **Start Condition** | **End Condition** | **Date Granularity** |
| --- | --- | --- | --- | --- |
| Ticket Mean Time to Remediate | The average number of days it takes for a ticket to turn inactive. This measurement counts the days passed from the ticket's creation date up to the date the ticket's state turned Inactive. | Ticket Create Date | Ticket State last turned to Inactive | Day |
| Ticket Mean Time to Assign | The average number of days it takes for a ticket to be assigned. This measurement counts the days passed from the ticket's creation date up to the date the ticket's assignee is changed from Null for the first time. | Ticket Create Date | Ticket Assignee first turned to Not null | Day |
| Ticket Mean Time to Close | The average number of days it takes for a ticket to be closed. This measurement counts the days passed from the ticket's creation date up to the date the ticket's state turned Closed. | Ticket Create Date | Ticket's status bucket is Closed | Day |

## Finding Measurements

The following table shows information about finding measurements:

| **Name** | **Description** |
| --- | --- |
| Active Findings | The number of active findings on the last day of the set date range. This is a unique count by finding key. |
| Active Findings (Open Tickets) | The number of active findings on tickets that are not in the Closed bucket on the last day of the set date range. This measurement only considers tickets not in the Closed bucket on the last day of the set date range, and then only counts active findings on those tickets. |
| New Findings | The number of findings first seen in the set date range. This measurement does not include reopened findings. |
| Detected Findings | The number of findings that turned Active during the set date range. This measurement only counts findings that were set as Active within the set date range; findings set as Active prior to the set date range are not counted. |
| Undetected Findings | The number of findings that turned inactive during the set date range. This measurement only counts findings that were set as Undetected within the set date range; findings set as Undetected prior to the set date range are not counted. |
| Total Undetected Findings | The number of findings with an Undetected state in the set date range. |
| Total Findings | The number of Active findings up to the set date range. This measurement counts findings that were set as Active at some point up to the end of the set date range. |
| Unique CVEs | This measurement looks at the Active findings on the last day of the set date range, and then counts the unique CVE IDs. |
| AVG Finding Severity Score | The average severity score of Active findings on the last day of the set date range. The averaging of severity scores is only made on findings set as Active on the last day of the set date range. |
| MAX Finding Severity Score | The highest severity score of Active findings on the last day of the set date range. The calculation of severity scores is made only on findings set as Active on the last day of the set date range. |
| Critical Active Findings | The number of Active findings with critical severity on the last day of the set date range. |
| High Active Findings | The number of Active findings with high severity on the last day of the set date range. |
| Medium Active Findings | The number of Active findings with medium severity on the last day of the set date range. |
| Low Active Findings | The number of Active findings with low severity on the last day of the set date range. |
| Info Active Findings | The number of Active findings with info severity on the last day of the set date range. |
| Risk Mass | The sum of all severity scores of the Active findings associated with an entity (ticket or asset). |

## Asset Measurements

The following table shows information about asset measurements:

| **Name** | **Description** |
| --- | --- |
| Total Assets | The total number of assets in the set date range. |
| Total Active Assets | The number of Active assets in the set date range. This measurement only counts assets that were active at some point in the set date range, not prior. |
| Vulnerable Assets | The number of assets linked to Active findings on the last day of the set date range. This measurement looks at the Active findings on the last day of the set date range, and then counts the assets linked to them. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-zscaler-easm-capabilities","lastmod":"2026-05-22T07:06Z","nid":"1538682"} -->
## Understanding Zscaler EASM Capabilities

- Source: https://help.zscaler.com/uvm/understanding-zscaler-easm-capabilities
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > External Attack Surface Management for UVM > Understanding Zscaler EASM Capabilities
- Last modified: 2026-05-22T07:06Z
- Summary: Information on Zscaler's External Attack Surface Management (EASM) capabilities—features, benefits, and use cases for securing internet-facing assets—built into the Zscaler SecOps platform within AEM and UVM apps.

In the modern digital landscape, organizations deal with increasingly complex external attack surfaces due to rapid cloud adoption, the proliferation of shadow IT, and the continuous evolution of cyber threats. In addition, as organizations grow through cloud migration and acquisitions, they often inherit invisible risk in the form of shadow IT and forgotten legacy systems. These unknown or unmonitored assets often lack critical security updates, leaving organizations with security gaps that are frequently exploited.

Without a unified perspective of their attack surface, security teams are forced to deal with fragmented visibility and critical blind spots across internet-facing applications, legacy services, remote access systems, shadow IT, and more. Zscaler eliminates this fragmentation by natively integrating External Attack Surface Management (EASM) into the Asset Exposure Management (AEM) and Unified Vulnerability Management (UVM) solutions offered on the Zscaler Security Operations (SecOps) platform. This unification provides security teams with a single "golden record" of all internet-facing assets, ensuring that every vulnerability is identified, prioritized, and remediated within a unified inventory.

External Attack Surface Management refers to the continuous discovery, inventory, classification, and monitoring of an organization's internet-exposed assets to identify, evaluate, and remediate risk exposures. Zscaler's EASM capabilities offer automated discovery and scanning to map previously unknown assets, analyze their internet exposure, and detect associated vulnerabilities and misconfigurations. It employs a multi-layered discovery approach to comprehensively map an organization's internet-exposed assets using various discovery methods and data sources, including WHOIS reverse lookup, ICANN regex analysis, DNS record lookup, web content analysis, certificate transparency logs (CTLogs), GitHub repository scanning, Nuclei patterns, certificate analysis, LLM subnet and IP inferencing, and more.

When assets are identified, the SecOps platform scans for a wide range of security risks associated with the assets, including unpatched vulnerabilities, misconfigurations, critical exposures, lookalike and phishing domains, outdated certificates, etc. By integrating EASM capabilities with AEM and UVM, the SecOps platform offers a comprehensive visibility of scanned assets and findings, management of discovered assets using AEM's policy engine to cover security and business policies, and end-to-end capabilities to minimize risk exposure using UVM, helping organizations identify and mitigate vulnerabilities before they can be exploited by malicious actors.

Built into the SecOps platform, EASM capabilities leverage and benefit from Zscaler's data fabric for security that centralizes and transforms disparate security data. EASM asset data undergoes the data fabric's processing, such as harmonizing, deduplication, and unification, to lead to more accurate, contextualized, and enriched security insights. To learn more, see [What Is Zscaler Security Operations?](https://help.zscaler.com/uvm/what-zscaler-security-operations)

## Key Features and Benefits

The key features and benefits offered by EASM capabilities for managing and securing your external attack surface include:

- **Unified Asset Intelligence**: Unlike standalone tools, EASM capabilities are natively integrated into the Zscaler Exposure Management platform. This allows organizations to build a unified, centralized inventory with maximum coverage by correlating data from subsidiaries, M&A activities, and shadow IT with their existing security stack for continuous, silo-free visibility.
- **Enhanced CMDB**: Leverage the AEM policy framework to identify and close security gaps with discovered assets, including the ability to add missing assets to your Configuration Management Database (CMDB).
- **Enhanced Discovery and Accuracy**: While traditional scanners rely on point-in-time internet snapshots and noisy banner-grabbing, Zscaler employs a multi-layered discovery engine. By feeding continuously updated exposure data into a customizable prioritization engine, security teams can filter through extensive CVE lists and focus on remediating the most critical, high-context risks.
- **Contextualized Risk Prioritization**: Leverage the platform's context-rich data to prioritize risks based on your business context and unique environment, enhancing the accuracy of risk assessment and helping plan remediation efforts.
- **Integrated Risk Remediation**: Manage vulnerabilities and misconfigurations from a centralized platform and remediate security risks by leveraging customizable severity scoring systems, automated ticket workflows, and more offered by UVM.
- **Continuous Monitoring**: The discovered assets are monitored for changes and new exposures, providing security teams visibility into their current external attack surface. This proactive approach ensures that organizations can respond swiftly to emerging threats.

## Use Cases

The following use cases highlight how EASM capabilities empower organizations in different scenarios to enhance their security posture:

- **Security Posture Management**: Zscaler Exposure Management with integrated EASM capabilities provides a unified, comprehensive view of an organization's overall attack surface with enriched data, helping security teams effectively manage their security posture and ensure that all internet-facing assets are secure and compliant.
- **M&A Diligence**: During mergers and acquisitions, organizations can assess the security posture of the target company's digital footprint, identifying any potential risks that need to be addressed.
- **Proactive Brand Protection**: Minimize financial and reputational damage by proactively identifying lookalike domains and phishing sites. With integrated registration data, the platform accelerates your ability to flag and take down phishing and impersonation websites before they impact customers.
- **Continuous Compliance Monitoring**: EASM capabilities support compliance efforts by continuously monitoring and reporting on the organization's external assets, helping meet regulatory requirements.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/using-remediation-copilot-uvm","lastmod":"2026-04-12T07:06Z","nid":"1533873"} -->
## Using Remediation Copilot in UVM

- Source: https://help.zscaler.com/uvm/using-remediation-copilot-uvm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Using Remediation Copilot in UVM
- Last modified: 2026-04-12T07:06Z
- Summary: How to use Remediation Copilot in UVM to assist in finding fix paths for the vulnerabilities in the ticket.

Remediation Copilot is an AI-powered assistant integrated directly into your Zscaler Unified Vulnerability Management (UVM) tickets, designed to support remediation teams with intelligent, context-aware remediation recommendations and mitigation instructions. It closes the gap between identifying a finding and successfully fixing it, providing remediation guidance based on the specific findings in the ticket, the affected assets, and your environment within your platform, even when external sources don't provide clear remediation guidance.

Remediation Copilot assists by analyzing the ticket's context to identify possible remediation paths for various types of issues, including:

- CVEs: Remediation Copilot can help you identify patch or upgrade options to address vulnerabilities, or suggest mitigation measures if fixes are unavailable. It can also highlight operational considerations (e.g., reboots for OS-level fixes) and distinguish direct vs. transitive dependencies.
- Misconfigurations: Remediation Copilot can provide recommendations on secure settings, guidance for applying configuration changes, and compensating measures if a secure setup isn't feasible immediately. Additionally, it can relate findings to relevant compliance standards (e.g., SOC 2, PCI DSS).
- Code-level Issues: Remediation Copilot can share best practices for secure coding or runtime mitigations, and suggest compensating controls for flaws like SQL injection or XSS when immediate remediation isn't viable.

To learn more, see [What Is Remediation Copilot?](https://help.zscaler.com/uvm/what-remediation-copilot), [Viewing & Managing Tickets in UVM](https://help.zscaler.com/uvm/viewing-managing-tickets-uvm), and [About Tickets](https://help.zscaler.com/uvm/about-tickets).

Account admins can remove access to Remediation Copilot by turning off AI capabilities in Account Settings. To learn more, see [Managing AI Features in the SecOps Platform](https://help.zscaler.com/uvm/managing-ai-features-secops-platform).

To use Remediation Copilot:

1. In the Zscaler Security Operations (SecOps) platform, go to **Vulnerabilities**> **Tickets**. The **Tickets**page appears.
2. Click the ticket you want to remediate.
3. Click the **Remediation Copilot**icon in the top panel of the ticket drawer. See image. The **Remediation Copilot**chat appears.
4. Select a system prompt or enter text describing what you want to remediate and any constraints (e.g., "prefer closest patch," "avoid reboot," or "maintenance window is weekends"). See image. To subsequently access the system prompts, click the **Default Prompts**icon ([Image: Default Prompts icon]) at the bottom left of the chat panel.
5. Ask follow-up questions, compare alternatives (patch vs. configuration change, upgrade vs. pin), and refine the plan until it fits your environment. If the session times out during long-running responses, click **Retry**to proceed.

After finalizing a satisfactory remediation plan, you can use the output in the following ways:

- Click the **Copy**icon in the Remediation Copilot panel to copy the remediation steps.
- Click **Summarize Insights**to generate a summary of the remediation steps discussed in the chat. See image.
- Click **Save to Fixes** to save a summarized version of the remediation plan to the ticket's **Fixes**tab. The summary is saved to the ticket's **Fixes**tab under the **AI** tab for **Fix Type**. See image. Multiple summaries can be saved to a single ticket, allowing you to capture any updates or alternative approaches. After saving your remediation summary to the ticket's **Fixes**tab, you can reference it during the remediation process, or provide a clear remediation path for others if the ticket is reassigned.

## Limited Context Mode

When a ticket includes many findings, Remediation Copilot might run in limited-context mode and not take all findings in the ticket into account. If you need full-context recommendations for a specific subset of findings, split those findings into a new ticket and run Remediation Copilot there to generate detailed fix paths. To learn more, see [Managing Manual Ticket Grouping](https://help.zscaler.com/uvm/managing-manual-ticket-grouping).

[Image: Remediation Copilot Icon in Ticket Drawer]

[Image: Remediation Copilot Chat]

[Image: Remediation Copilot Summarize Chat Insights]

[Image: Remediation Copilot Saved AI-Type Fixes on Ticket's Fixes Tab]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/viewing-findings-uvm","lastmod":"2026-07-13T21:06Z","nid":"1531067"} -->
## Viewing Findings in UVM

- Source: https://help.zscaler.com/uvm/viewing-findings-uvm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Viewing Findings in UVM
- Last modified: 2026-07-13T21:06Z
- Summary: How to view findings details in the Unified Vulnerability Management (UVM) app in the SecOps Platform.

Unified Vulnerability Management (UVM) findings represent vulnerabilities or misconfigurations detected on assets and linked to specific sources. Selecting a finding on the Findings page opens its drawer, where you can view detailed information. To learn more, see [About Findings](https://help.zscaler.com/uvm/about-findings-operational-view-uvm). The actions you can perform in the finding drawer depend on your user role in the UVM app. To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles) and [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles).

The finding drawer can be configured by admins and might look different in your account. The information in this article refers to the default finding drawer settings. To learn more, see [Configuring Entity Drawers in UVM](https://help.zscaler.com/uvm/configuring-uvm-entity-drawers-ui-config).

To view the finding drawer:

1. Go to **Vulnerabilities**> **Findings**. See image.
2. Optionally, you can [apply filters](https://help.zscaler.com/uvm/using-filters) to refine the data and display the findings you want to view. To view the findings discovered by Zscaler's [External Attack Surface Management (EASM) capabilities](https://help.zscaler.com/uvm/understanding-zscaler-easm-capabilities), click the **Sources** filter and select **EASM Findings**. Available Finding Types
3. Click the finding you want to view. See image. A drawer appears with the following details and tabs:
  - Top Panel
  - Details
  - Vulnerability
  - Finding Evidence
4. Click **Apply Changes** after updating the finding's details, or close the drawer.

[Image: The Findings page in UVM]

[Image: The Finding drawer with details]

In the top panel of the finding drawer, you can view:

- **Title**: The finding's title as assigned by the source.
- **First Seen**: The date the finding was first detected.
- **Severity Score**: Both the original severity score and the contextualized severity score for the finding. You can manually override the score.

Additionally, you can perform the following actions:

- Copy a shareable link to the finding.
- View the finding's ID.
- Expand the finding drawer to full screen.
- Close the finding drawer.

On the **Details**tab, you can view:

- **Asset**: The related asset affected by the finding.
- **Ticket**: The related ticket that aggregated the finding.
- **Sources**: The source that reported the finding.
- **CVE ID**: If applicable, the CVE ID linked to the issue, with a direct link to the National Vulnerability Database (NVD) for further information.
- **First Seen**: The date the finding was first detected.
- **Last Seen**: The most recent date the finding was detected.
- **Description**: The description of the finding as provided by the source.
- **Score Explanation**: A detailed breakdown of the factors that contributed to the finding's severity score and how the score was calculated, including risk and mitigation criteria. To learn more, see [Understanding Severity Score](https://help.zscaler.com/uvm/understanding-severity-score).

On the **Vulnerability**tab, you can explore additional information about CVE findings, including vulnerability insights provided by related sources.

This information is only available for findings ingested using [Zscaler's EASM capabilities](https://help.zscaler.com/uvm/understanding-zscaler-easm-capabilities).

On the **Finding Evidence** tab, you can view:

- **Scan Output**: The complete output of the scan that was used to identify the finding. This section is labeled differently for assets depending on the type of scan performed (e.g., **HTTP Response**, **Nmap Response**, and **Certificate Response**).
- **Match Evidence**: The specific portions of the scan results that attest to the finding. This information provides attestation of detected findings and enables organizations to self-validate their findings.

- **CVE**: Includes vulnerabilities detected on assets, such as CVEs, commonly exploited CVEs, and vulnerabilities supported by [Threat Intelligence (TI) decoys in Zscaler Deception](https://help.zscaler.com/deception/about-threat-intelligence-decoys).
- **Misconfig**: Includes various misconfigurations and exposures detected on assets, including:
  - Exposed VPN appliances
  - Exposed sensitive services
  - Revealing hostnames
  - Non-default or unknown open ports
  - Expired domain registrations
  - Domains with registration expiring within 30 days
  - Expired SSL/TLS certificates
  - Outdated SSL/TLS versions
  - Self-signed certificates
  - SSL/TLS certificates expiring within 30 days
  - HTTP using insecure header
  - HTTP missing common security header
  - Dangling DNS records
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/viewing-managing-assets-uvm","lastmod":"2026-07-15T21:06Z","nid":"1531065"} -->
## Viewing & Managing Assets in UVM

- Source: https://help.zscaler.com/uvm/viewing-managing-assets-uvm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Viewing & Managing Assets in UVM
- Last modified: 2026-07-15T21:06Z
- Summary: How to view and manage asset details in the Unified Vulnerability Management (UVM) application in the SecOps Platform.

A Unified Vulnerability Management (UVM) asset represents a single asset in your environment, unified (i.e., merged) and enriched with information from multiple sources. Selecting an asset on the Assets page opens its drawer, where you can view detailed information and perform multiple actions for the asset. To learn more, see [About Assets in UVM](https://help.zscaler.com/uvm/about-assets-operational-view-uvm).

The actions you can perform in the asset drawer depend on your user role in the UVM app. To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles) and [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles).

The asset drawer can be configured by admins and might look different in your account. The information provided in this article refers to the default asset drawer settings. To learn more, see [Configuring Entity Drawers in UVM](https://help.zscaler.com/uvm/configuring-ticket-ui-vulnerabilities-app).

To view the asset drawer:

1. Go to **Vulnerabilities**> **Assets**. See image.
2. Optionally, you can [apply filters](https://help.zscaler.com/uvm/using-filters) to refine the data and display the assets you want to view. To view the assets discovered by Zscaler's [External Attack Surface Management (EASM) capabilities](https://help.zscaler.com/uvm/understanding-zscaler-easm-capabilities), click the **Sources** filter and select **EASM Assets**. Available Asset Types
3. Click the asset you want to view. See image. A drawer appears with the following details and tabs:
  - Top Panel
  - Details
  - Asset Merging
  - Findings
  - Tickets
4. Click **Apply Changes** after making changes to the asset, or close the drawer.

In the top panel of the asset drawer, you can view:

- **Name**: The name of the asset.
- **First Seen**: The asset's first seen date.

Additionally, you can perform the following actions:

- Copy a shareable link to the asset you're viewing.
- View the asset ID.
- Expand the asset's drawer to full screen.
- Close the asset drawer.
- View the asset's severity level and severity score. By default, it reflects the highest severity score among the findings detected on the asset.

On the **Details**tab, you can view:

- **Asset Type**: The classification or category that the asset belongs to, such as server, workstation, or application.
- **Sources**: The sources that the findings on the asset were detected on.
- **Assignee**: The agent or team responsible for handling the asset.
- **Risk Mass**: The asset's cumulative risk exposure, calculated by summing the severity scores of active findings for each severity level (i.e., Critical, High, Medium, Low), and rounding the result. This indicator can be used to prioritize assets with similar risk profiles.
- **Has PII Data**: Indicates whether the asset contains Personally Identifiable Information (PII), highlighting its sensitivity and compliance requirements.
- **Discovery Chain**: The asset's full discovery path, featuring the seed asset, intermediate nodes, and the current asset in a sequence, along with the services and attributes used to identify assets in each discovery hop as applicable. It enables source traceability and provides attestation of auto-attributed assets based on a seed, allowing you to self-validate your assets using the investigative trail provided. The discovery chain is only available for assets ingested using [Zscaler's EASM capabilities](https://help.zscaler.com/uvm/understanding-zscaler-easm-capabilities). When an asset is discovered in more than one way by EASM, the discovery path with the highest confidence is shown.

On the **Asset Merging**tab, you can view the original source records that the asset was merged from.

Additionally, you can perform the following actions:

- Apply filters to adjust the displayed assets by relevant attributes (e.g., filtering the original source records owner ID or first seen date).
- Adjust the displayed columns and their sorting settings. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).
- Click the **Export as CSV**icon to export the list of assets that the current asset was merged from as a CSV file.

On the **Findings**tab, you can explore the findings that were detected on the asset.

To view the finding's details (e.g., descriptions and [score calculation logic](https://help.zscaler.com/uvm/configuring-severity-score-formulas)), you can either expand the finding or drill down to the finding drawer.

Additionally, you can perform the following actions:

- Apply filters to adjust the displayed findings by relevant attributes and to explore the asset's risk portfolio (e.g., filtering the findings by a particular state, title, or severity score).
- To update key finding details, select the findings and click **Update**. The fields available to update include fields that were enabled for manual override in Configure > Data Model.
- Click the**Export as CSV**icon to export findings on the asset to a CSV file.
- Adjust the displayed columns and their sorting settings. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).

On the **Tickets**tab, you can view all tickets related to the asset and a summary of their details, sources, status, and remediation percentage.

Additionally, you can perform the following actions:

- Apply filters to adjust the displayed tickets by relevant attributes (e.g., filtering the tickets by type, title, or severity score).
- Adjust the displayed columns and their sorting settings. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).

Clicking a ticket opens its drawer. To return to the asset drawer, click the asset name in the top-left corner of the ticket drawer.

- **Certificate**: Includes SSL/TLS certificates.
- **Domain**: Includes domains, subdomains, and hosts.
- **Edge Server**: Includes IP addresses.
- **Git Repository**: Includes GitHub repositories.

[Image: The Assets page in UVM]

[Image: The Assets drawer showing details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/viewing-managing-tickets-uvm","lastmod":"2026-07-15T21:06Z","nid":"1531052"} -->
## Viewing & Managing Tickets in UVM

- Source: https://help.zscaler.com/uvm/viewing-managing-tickets-uvm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Viewing & Managing Tickets in UVM
- Last modified: 2026-07-15T21:06Z
- Summary: How to view and manage ticket details in the Unified Vulnerability Management (UVM) application in the SecOps Platform.

Unified Vulnerability Management (UVM) tickets aggregate related findings into a single work item that can be fixed as one task. Selecting a ticket on the Tickets page opens its drawer, where you can view detailed information and perform multiple actions for the ticket. To learn more, see [About Tickets](https://help.zscaler.com/uvm/about-tickets-operational-view).

The actions you can perform in the ticket drawer depend on your user role in the UVM app. To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles) and [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles).

The ticket drawer can be configured by admins and might look different in your account. The information provided in this article refers to the default ticket drawer settings. To learn more, see [Configuring Entity Drawers in UVM](https://help.zscaler.com/uvm/configuring-ticket-ui-vulnerabilities-app).

To view the ticket drawer:

1. Go to **Vulnerabilities**> **Tickets**. See image.
2. Click the ticket you want to view. See image. A drawer appears with the following details and tabs:
  - Top Panel
  - Details
  - Findings
  - Assets
  - Fixes
  - Related Tickets
  - Comments
  - Activity
3. Dispatch the ticket to the external work management systems (e.g., Jira) configured in your account's outegrations. To learn more, see [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations) and [Creating & Managing Third Party Tickets](https://help.zscaler.com/uvm/creating-managing-third-party-tickets).
4. Click **Apply Changes** after making changes to the ticket.

[Image: The Tickets page in UVM]

[Image: Ticket drawer with details]

In the top panel of the ticket drawer, you can view:

- **Title**: The ticket title is set by your account's grouping rules.
- **First Seen**: The ticket's first seen date reflects the earliest detected finding included in the ticket. The ticket creation date can differ from its first seen date due to ticket merges or changes to grouping rules.

Additionally, you can perform the following actions:

- Copy a shareable link to the ticket you're viewing.
- View the ticket ID.
- Expand the ticket's drawer to full screen.
- Close the ticket drawer.
- Check if the ticket is in Locked Scope, which means new findings cannot be added to it. Tickets are automatically locked when created through the findings split or ticket merge actions. A ticket might also be locked if it's linked to an external system or if an exception request was submitted for it. You can customize the conditions for locking tickets in the Ticket Workflows settings.
- Access Remediation Copilot to open an AI-powered chat for generating contextual remediation guidance and fix recommendations. To learn more, see [What Is Remediation Copilot?](https://help.zscaler.com/uvm/what-remediation-copilot) and [Using Remediation Copilot](https://help.zscaler.com/uvm/using-remediation-copilot-uvm).
- View and manually update the ticket's severity level and severity score. The score on the right is the ticket's original severity score, while the score on the left is the UVM score, which is calculated based on the configurable score settings. To learn more, see [Configuring Severity Score Formulas](https://help.zscaler.com/uvm/configuring-severity-score-formulas).
- View and manually update the ticket's status to track and manage its progress in the workflow. To learn more, see [Managing Ticket Statuses in UVM](https://help.zscaler.com/uvm/managing-ticket-status). When remediating tickets, consider the distinction between the ticket's status and state. Ticket status is either set manually by the ticket assignee or synced from an external work management system, indicating the ticket's current step in your workflow. Ticket state reflects whether the ticket contains active findings that were detected in a recent scan. The ticket state can remain active as long as it contains active findings, regardless of the ticket status.

On the **Details**tab, you can view:

- **Sources**: The sources that the findings in the ticket were detected on.
- **Assignee**: The agent or team responsible for handling the ticket.
- **SLA**: The service level agreement (SLA) date that the ticket needs to be remediated by.
- **Risk Mass**: The ticket's cumulative risk exposure, calculated by summing the severity scores of active findings for each severity level (i.e., Critical, High, Medium, Low), and rounding the result. This indicator can be used to prioritize tickets with similar risk profiles.
- **Grouping Details**: Specifies the grouping rule and the ruleset that grouped the findings into the ticket. This can be used when investigating whether your account's grouping logic needs to be adjusted. To learn more, see [Configuring Grouping Rules](https://help.zscaler.com/uvm/configuring-grouping-rules).
- **Exceptions**: The details of all exception requests to extend the ticket's SLA date that were submitted for the ticket. To learn more, see [Understanding Exception Requests](https://help.zscaler.com/uvm/understanding-exception-requests).

Additionally, you can perform the following actions:

- If the ticket isn't assigned, or if the ticket is incorrectly assigned, you can manually update the ticket assignee.
- To add attachments to your ticket, click the **Attachment**icon, and select the files you want to attach. Supported formats include CSV and XSLX.
- To request an exception for the ticket to extend its SLA date, click **Request Exception**. To learn more, see [Requesting Exceptions](https://help.zscaler.com/uvm/requesting-exceptions).

On the **Findings**tab, you can explore the findings that the ticket contains, aggregated based on the grouping rules configured in the account. For example, you can view and filter findings by the asset that they were discovered on. To learn more, see [Configuring Grouping Rules](https://help.zscaler.com/uvm/configuring-grouping-rules).

To view the finding's details (e.g., descriptions and [score calculation logic](https://help.zscaler.com/uvm/configuring-severity-score-formulas)), you can either expand the finding or drill down to the finding drawer.

Additionally, you can perform the following actions:

- Apply filters to adjust the displayed findings by relevant attributes (e.g., filtering the findings in the ticket by a particular asset).
- Select the findings that you want to split into a new ticket, and click **Split into a New Ticket**. Splitting findings from a ticket into a new ticket can be used when you need to focus on a specific subset of findings, such as launching a targeted remediation campaign, requesting an SLA extension, assigning findings to different teams, or resolving cases where automatic grouping has incorrectly combined unrelated findings. To learn more, see [Manually Splitting Findings into New Tickets](https://help.zscaler.com/uvm/manually-splitting-findings-new-ticket).
- To update key finding details, select the findings and click **Update**. The fields available to update include fields that were enabled for manual override in Configure > Data Model.
- To export the findings in the ticket as a CSV file, click the **Export as CSV**icon.
- Adjust the displayed columns and their sorting settings. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).

On the **Assets**tab, you can explore the list of assets on which the findings in the ticket were detected. For example, you can analyze the number of findings on each of the assets in the ticket and the percentage that are remediated.

To view the asset's details in the asset drawer, hover over the asset and click **Drill to Asset Page**on the right.

Additionally, you can perform the following actions:

- Apply filters to adjust the displayed assets to focus on those relevant to your current task.
- To update asset details, select the assets and click **Update**. The fields available to update include fields that were enabled for manual override in Configure > Data Model.
- To export the assets in the ticket as a CSV file, click the **Export as CSV**icon.
- Adjust the displayed columns and their sorting settings. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).

On the **Fixes**tab, you can view the recommended fixes for the findings in the ticket. This tab is displayed depending on factors such as ticket type or available remediation insights in data sources.

The system extracts the following fix types from source data:

- KB: Microsoft KB fixes
- Version: Version updates
- Text: Fixes descriptions

You can group the fixes to optimize potential remediation strategies, to help you determine what fixes can be applied to resolve as many findings as possible.

To group fixes:

1. Click the **Settings**icon. The **Select your preferred table view** dialog window appears.
2. Select a **Group By**option (e.g., **Component**, **Fix**, **Asset**).
3. Click **Apply**.

On the **Related Tickets**tab, you can view a list of tickets that are related to the ticket you're currently viewing.

Related tickets include:

- Tickets that were aggregated based on the same grouping rule as the locked ticket.
- Tickets that were split from the current ticket.
- Tickets that the current ticket were split from.

On the **Comments**tab, you can add, delete, or edit comments to collaborate, inquire, or share information with other users.

On the **Activity**tab, you can view the ticket's activity feed, which is the chronological record of actions performed on the ticket.

You can filter the feed by:

- **Manual**: Manual changes that were made to the ticket (e.g., manually assigning the ticket, or [splitting findings from the ticket](https://help.zscaler.com/uvm/manually-splitting-findings-new-ticket)).
- **Outegrations Actions**: Changes performed on the ticket as a result of a two-way sync with a third-party ticket (e.g., creating a Jira issue, updating the ticket's status based on a linked ServiceNow ticket).
- **System Actions**: Automatic actions performed by the system (e.g., newly discovered findings added to the ticket).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/viewing-overview-dashboard","lastmod":"2026-07-19T06:00Z","nid":"1540139"} -->
## Viewing the Overview Dashboard

- Source: https://help.zscaler.com/uvm/viewing-overview-dashboard
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analyze for UVM > Viewing the Overview Dashboard
- Last modified: 2026-07-19T06:00Z
- Summary: How to view and interpret the Overview dashboard in UVM.

The Overview dashboard provides a comprehensive view of your Zscaler Unified Vulnerability Management (UVM) findings from ingestion to remediation. The dashboard demonstrates the data deduplication, normalization, and prioritization capabilities in the Zscaler Security Operations (SecOps) platform, allowing you to view how fragmented data points are transformed into a streamlined set of prioritized work items.

To access the dashboard, go to **Vulnerabilities** > **Overview**.

See image.

You can adjust the data displayed in the dashboard by using the filters at the top of the page. To learn more, see [Using Filters](https://help.zscaler.com/uvm/using-filters). You can save the adjusted view to return to without reapplying the filters each time. To learn more, see [Creating & Managing Saved Views](https://help.zscaler.com/uvm/creating-managing-saved-views).

## Tracking Discovery to Prioritization

The primary view of the dashboard demonstrates the transition of high-volume findings from their raw source state to their final, prioritized, grouped state in tickets. Tracking from left to right, this visualization illustrates how the platform refines fragmented data into an actionable workload based on your defined business context.

See image.

### Source Findings and Assets

On the left, the dashboard displays source findings and source assets for each of the [data sources configured in your account](https://help.zscaler.com/uvm/creating-data-sources) (e.g., Qualys, CrowdStrike, ServiceNow), after mapping but before unification.

- Findings: The total number of findings reported by a specific source.
- Assets: The number of assets that the source is reporting on.

See image.

### Unified Findings

As you trace the flow toward the right, the source data is aggregated into the Findings node. The number of findings displayed in this node reflects the findings after they have been processed by the platform's [unification](https://help.zscaler.com/uvm/what-data-unification). UVM identifies when multiple different sources report the same vulnerability on the same machine and merges them into a single, unified record. Thus, you might see a lower number in the central Findings node than the sum of all raw source findings on the left. This provides you with an accurate count of unique findings in your environment without the duplication of overlapping scanner reports.

The trend arrow indicates the percentage of increased or decreased deduplicated findings in the past 30 days.

See image.

### Findings Grouping to Tickets

The flow continues from the Findings node to the Tickets node, including the findings to tickets ratio. The ratio represents the [grouping](https://help.zscaler.com/uvm/configuring-grouping-rules) of findings into actionable work items, illustrating the platform's ability to group related findings into a single ticket. The ratio between findings and tickets reflects the efficiency of your grouping logic. For example, a single ticket might group 50 different findings found on a single software component into one ticket, allowing a developer to address all 50 findings in a single work item.

See image.

### Tickets by Severity

On the right, the tickets are broken down by severity (Critical, High, Medium, Low). This distribution reflects the ticket [severity](https://help.zscaler.com/uvm/configuring-severity-categories) distribution in your account. UVM takes the base severity provided by the scanner and adjusts it based on your specific business context, such as whether the asset is a Crown Jewel or if mitigating controls are present, to ensure that your team focuses on the highest true risk first.

The Mean Time to Remediate (MTTR) measurement shows the average number of days it takes for a ticket to move from opened to resolved.

See image.

## Tracking Operational Health

The tiles at the bottom of the dashboard provide key performance indicators (KPIs) that allow you to monitor the effectiveness of your remediation program and identify process bottlenecks.

See image.

### Vulnerable Assets

The Vulnerable Assets tile displays the total number of unique, unified assets in your environment that have at least one active finding. This represents the actual footprint of your exposure and helps you understand how many physical or virtual machines require attention in your security landscape.

### Total Unique CVE

The Total Unique CVE tile counts the number of distinct Common Vulnerabilities and Exposures (CVE) identifiers currently active across your environment. This helps understand the diversity of your risk. A high number of unique CVEs suggests a wide range of different software issues, whereas a low number of CVEs paired with a high number of findings suggests a few specific vulnerabilities are widespread across your entire organization.

### % Assigned Tickets

The % Assigned Tickets tile tracks how many currently active tickets have been assigned to a specific owner or remediation team. A low assignment percentage indicates that tickets are being created but are sitting in an unassigned queue. You can automate assignment with [data unification](https://help.zscaler.com/uvm/what-data-unification) rules.

### % Third-Party Tickets

The % Third-Party Tickets tile represents the percentage of tickets that have been dispatched to an external work management system using [outegrations](https://help.zscaler.com/uvm/creating-outegrations) (e.g., Jira or ServiceNow). A high percentage indicates a healthy, automated integration where security insights are being pushed directly into the existing workflows of your IT and development teams.

[Image: Vulnerabilities Overview dashboard]

[Image: Overview dashboard central view]

[Image: Raw source Findings and Assets]

[Image: Overview dashboard Findings node]

[Image: Finding to Tickets ratio and Total Tickets]

[Image: Tickets broken down by severity]

[Image: Overview dashboard tiles]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/viewing-remediation-history-dashboard","lastmod":"2025-11-20T21:04Z","nid":"1527771"} -->
## Viewing the Remediation History Dashboard

- Source: https://help.zscaler.com/uvm/viewing-remediation-history-dashboard
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analyze for UVM > Viewing the Remediation History Dashboard
- Last modified: 2025-11-20T21:04Z
- Summary: Viewing data on the Remediation History dashboard in the Vulnerabilities app.

The Vulnerabilities application in the Zscaler Security Operations (SecOps) platform features a Remediation History dashboard that provides insights into remediation efficiency, service level agreement (SLA) compliance, and the overall count and trend of tickets across statuses, teams, and severity levels. The historical data provided by the dashboard helps identify patterns in your remediation efforts, such as trends in fixing vulnerabilities, defects, or other issues.

To view the Remediation History dashboard, go to Vulnerabilities > Remediation History.

The data displayed on the dashboard is based on the selected view and the set filters.

- Dashboard filter: Refine and customize the dashboard data to focus on the remediation details that are most relevant for analysis.
- Date filter: Filter the chart by date and time to view data within the specified time frame and at the chosen level of detail, enabling you to identify trends, anomalies, and changes over time.
- Saved views: Save the dashboard with frequently used filters as a saved view to switch between different data scopes. Saved views help to track and compare changes over time, allowing you to revisit specific remediation inquiries and share insights with team members.

You can also Export the dashboard as a PDF by clicking the Export icon at the top of the dashboard.

[Image: Export the dashboard data as a PDF]

## Understanding Ticket Status and Ticket State

To understand the impact of historical data, it is important to know the critical difference between ticket status and ticket state.

- Ticket status: The status of a ticket is configured during account setup to reflect your organization's ticket remediation workflow. A ticket's status is set either manually by the ticket assignee or synced from an external case management system, indicating the ticket's current status in your workflow.
- Ticket state: The state of a ticket is an attribute that reflects whether the ticket contains active findings detected in a recent scan. The ticket state can remain active as long as it has active findings that need to be addressed, regardless of its actively set status.

This nuance enables you to monitor and differentiate a ticket's remediation progress based on its stage in your designed workflow, while still maintaining visibility into whether the findings it contains continue to be detected by your scanners (useful for identifying tickets that are Closed yet remain Active).

In the dashboard charts explained in the following sections, ticket measurement titles that include Open refer to ticket status, while those that include Active refer to ticket state.

## Top Panel Tiles

The tiles in the top panel provide an overview of open tickets categorized by severity score. This data does not reflect any historical data, and is not affected by the time filter. Each tile shows the current number of open tickets in your account based on severity level (e.g., Critical, High, Medium, Low, etc.) along with the sum of active findings associated with these tickets (active or inactive).

[Image: Open tickets categorized by severity score]

## Key Metrics Over Time

This chart offers insights into performance trends, allowing you to monitor improvements in metrics like MTTR and open tickets exceeding SLA over time. The chart data helps identify specific issues within the remediation process, enabling you to pinpoint areas for improvement and ensure a more efficient workflow that meets SLA targets.

[Image: Insights on performance trends]

The following metric measurements are displayed on the Key Metrics Over Time chart. Open tickets here refer to tickets that were in a status bucket but not tagged as Closed.

- Total Open Tickets: The number of tickets that were open at some point during the specified time period (regardless of their state).
- Active Tickets (Open): The number of open tickets that were set as Active at some point during the specified time period.
- Total Tickets Over SLA: The number of open tickets that are past their SLA date at some point during the specified time period (can be Active or Inactive).
  - For open tickets (i.e., not in the Closed status bucket), over SLA is considered when the set date range is after the set SLA for the ticket.
  - For Closed tickets (i.e., in the Closed status bucket), over SLA is considered when the date on which the ticket was closed was after the set SLA date.
- % Open Tickets Over SLA: The percentage of open tickets that were past their SLA date at some point during the specified time period, relative to the total number of tickets.

To add more metrics, click the **+**icon at the top of the chart.

The selection of measurements is limited to 8 metrics.

## Active Tickets Progress Over Time

This chart can reveal trends of discovered versus remediated findings across various severity levels. This is helpful for evaluating the effectiveness of your remediation efforts, highlighting how well your organization addresses emerging vulnerabilities over time.

The measurement displayed in this chart is Total Active Tickets, and represents the sum of Active tickets of any status (including Closed) based on severity level (e.g., Critical) over the time set in the time filter.

[Image: Active tickets of any status based on severity level]

## Open Tickets by Current Status

This chart shows the sum of open tickets (Active and Inactive) based on status buckets. You can hover over each status bar to view additional details based on sub-statuses in each bucket.

As this chart displays only open tickets, status buckets tagged as Closed in your ticket status settings aren't displayed. Status buckets without any associated tickets aren't displayed in this chart.

[Image: Open tickets based on status buckets]

## Analyze Remediation Work

This chart offers a comprehensive comparison of teams based on their efficiency and SLA compliance in vulnerability management efforts. It helps analyze each team's performance, and identify strengths and specific areas for improvement in their workflow processes.

The chart displays the following details:

- % Total Tickets Over SLA: The percentage of tickets that were over SLA at some point during the specified time period, relative to the total number of tickets, regardless of their status (Open or Closed).
- Total Tickets Over SLA: The number of active or inactive tickets that are over their SLA date at some point during the specified time period.
- Total Open Tickets: The sum of active and inactive tickets that were not in the Closed status bucket at some point during the specified time period.

[Image: Compare teams on efficiency and SLA compliance]

You can click the **By**drop-down menu and select an option based on how you want the data to be displayed. By default, the chart displays data based on the assignee.

[Image: Select an option from the drop-down menu to filter the data]

To add measurements to this chart, click the **+**icon. In addition to the option to export the entire dashboard as a PDF, you can also export this chart as a CSV by clicking the Export icon.

[Image: Additional measures to analyze]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/viewing-risk-dashboard","lastmod":"2025-11-20T21:02Z","nid":"1527776"} -->
## Viewing the Risk Dashboard

- Source: https://help.zscaler.com/uvm/viewing-risk-dashboard
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analyze for UVM > Viewing the Risk Dashboard
- Last modified: 2025-11-20T21:02Z
- Summary: Understanding components of the Risk dashboard in the Vulnerabilities app.

The Vulnerabilities application in the Zscaler Security Operations (SecOps) platform features a Risk dashboard that displays charts providing high-level insights into your organization's vulnerability-related risk. Risk is measured by analyzing the severity scores of your organization's vulnerabilities. The Risk dashboard leverages the historical data of your account and helps identify trends, assess the effectiveness of remediation efforts, and prioritize risk management strategies.

To view the Risk dashboard, go to Vulnerabilities > Risk.

The data displayed on the dashboard is based on the selected view and the set filters.

- Dashboard filter: Refine and customize the dashboard data to view the most relevant vulnerability-related risk details.
- Saved views: Save the dashboard with frequently used filters as a saved view to switch between different data scopes. This enables you to revisit specific historical data and share insights with team members.

You can export the dashboard as a PDF by clicking the Export icon at the top right of the dashboard.

[Image: Export the dashboard as a PDF]

## Risk Scoring Method

Risk score is determined by the severity scores of active findings typically associated with an entity (e.g., [Assets](https://help.zscaler.com/uvm/about-assets-operational-view-uvm)) in your account.

There are three methods for risk score calculation:

- AVG Risk: Calculates the average severity score of findings associated with the entity. This is useful for analytical comparisons and trend measurement to reveal risk fluctuations and assess the effectiveness of remediation efforts.
- Max Risk: Returns the highest severity score related to the entity. This helps in viewing a stable measure of maximum risk exposure, ensuring that significant vulnerabilities are prioritized and addressed efficiently.
- Risk Mass: Calculates the sum of all severity scores of the active findings associated with the entity. This helps in gaining further insights into cumulative risk exposure. This facilitates effective prioritization of entities with similar risk profiles and enhances overall risk management strategies.

The risk scoring methods complement one another, and toggling between them provides a comprehensive view of risk from multiple perspectives. You canselect your preferred method from the Risk Scoring Method drop-down menu at the top right of the dashboard.

[Image: Methods used for risk score calculation]

The following example charts in this article show the AVG Risk method.

## Overall Risk

This tile shows the average severity score of active findings in your account. This is useful for evaluating the trend in your organization's overall security posture. For example, if the average severity score is currently 5.58, up from 5.57 last month, your risk is indicated as trending up by 0.1%.

[Image: Average severity score of active findings]

## Key Metrics Over Time

This chart shows the historical data over the last 6 months. The default measurement shown in the following chart is Active Findings, which displays the total number of all active findings at the end of each month over the past 6 months. This chart can be used to pinpoint specific months with a significant increase or decrease in the number of active findings, indicating the effectiveness of remediation efforts or the impact of new findings introduced during specific periods.

[Image: Historical data over the last 6 months]

You can add up to two measurements to the chart at a time by clicking the **+** icon.

## Findings by Source Names

This chart shows the number of active findings and their risk score based on sources with the highest count of active findings, and all other sources grouped as others.

[Image: Active findings and Risk Score based on source names]

You can click any of the slices on the chart (except Others) to filter all other charts in the dashboard by this value. This action adds a Slicer filter in the top-right corner of the selected chart and all other charts on the dashboard. Deselecting the selected slice returns the dashboard to the default view.

[Image: Slicer filter to filter all other charts in the dashboard by this value]

## Findings by Asset Type

This chart shows the number of active findings and their risk score based on assets with the highest count of active findings, and all other sources grouped as others.

[Image: Active Findings and Risk Score based on assets]

You can click any of the slices on the chart (except Others) to filter all other charts in the dashboard by this value. This action adds a Slicer filter in the top-right corner of the selected chart and all other charts on the dashboard. Deselecting the selected slice returns the dashboard to the default view.

[Image: Filter the pie chart based on a value]

[Image: Filter tag displayed on all the charts in the dashboard]

## Tile Widgets

The Risk dashboard includes the following widgets.

### Active Findings

This widget displays the number of active findings in your account.

[Image: Number of active findings in your account]

### Vulnerable Assets

The number in this widget represents the count of active assets that contain active findings. An increasing number of vulnerable assets can signal a growing vulnerability exposure.

[Image: Number of vulnerable assets with active findings]

### Findings - Last Week

This widget helps track the number of discovered and undetected findings.

#### Discovered

The number of findings thatbecame active in the last 7 days and are active currently. These findings were either redetected by the scanners or discovered for the first time.

#### Undetected

The number of findings that were active and undetected in the last 7 days.

[Image: Number of active findings that were discovered and those that were undetected]

### Unique CVEs

The number in this widget represents the count of unique CVEs linked to your active findings. It helps indicate the expected complexity of remediation efforts. Fewer unique CVEs suggest simpler and more efficient remediation.

[Image: Number of unique CVEs linked to active findings]

## Number of Assets by Risk Score

This chart displays the number of assets across your account based on risk score ranges.

[Image: Number of assets in your account based on their associated risk score]

## Risk Severity By

The charts in this section represent risk scattered across your organization's assets over time.

### Overview Chart

This chart displays the number of assets and the associated risk score based on the value you select from the Risk Severity drop-down menu. For example, selecting Application Source ID displays the number of assets linked to each application source and the associated risk score. If you click a specific application source ID in the left pane, it filters the chart accordingly.

[Image: Number of assets and the associated Risk Score based on the selected value]

### Risk Severity Over Time

This chart provides insights into the findings' severity category, which make up each of the data points displayed as a scatter plot in the Overview chart over the last 6 months. For example, by selecting a specific application source ID in the scatter plot, you can examine the number of assets and findings linked to it over time. This helps visualize any significant changes and trends in risk exposure.

[Image: Severity category of the findings over the last 6 months]

Hovering over a specific bar shows a breakdown of the risk score by findings' severity categories.

[Image: Breakdown of the risk severity for the selected data point]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/what-remediation-copilot","lastmod":"2026-04-12T07:06Z","nid":"1533882"} -->
## What Is Remediation Copilot?

- Source: https://help.zscaler.com/uvm/what-remediation-copilot
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > What Is Remediation Copilot?
- Last modified: 2026-04-12T07:06Z
- Summary: Information about Remediation Copilot in the UVM app.

Remediation Copilot is an AI-powered assistant integrated into Zscaler Unified Vulnerability Management (UVM) tickets. It provides intelligent, context-aware mitigation recommendations and ready-to-use instructions based on the specific finding, affected assets, and your environment. Remediation Copilot helps close the gap between detection and resolution, including cases where external sources do not provide clear remediation paths.

Remediation Copilot is accessed from the ticket drawer. Previously saved chats appear in the ticket's Fixes tab.

See image.

To learn more, see [Using Remediation Copilot in UVM](https://help.zscaler.com/uvm/using-remediation-copilot-uvm) and[Viewing & Managing Tickets in UVM](https://help.zscaler.com/uvm/viewing-managing-tickets-uvm).

## Key Features and Benefits

Remediation Copilot provides the following features and benefits:

- Contextual Remediation: Guidance is tailored to asset type and deployment (e.g., container images, Kubernetes workloads, virtual machines, or serverless functions). Instructions include commands, configuration changes, and validation checks where applicable.
- Interactive Recommendations: Users can ask questions and iteratively refine fix paths directly in the ticket via an AI chat.
- Gap Coverage When Fix Data Is Missing: When vendor advisories or connected sources do not provide a fix, Remediation Copilot suggests mitigations or workarounds and notes assumptions or risks.
- Fix Preservation: Users can save a summary of the agreed remediation steps to the ticket's Fixes tab for traceability, handoffs, and audits.

## How Remediation Copilot Works

Remediation Copilot uses the ticket's context (grouped findings, asset details, environment data) to generate remediation paths and instructions. Recommendations adapt to constraints users share in the chat (e.g., minimal disruption, closest patch version, vendor-recommended targets).

Example use cases that Remediation Copilot can assist with include:

- Remediating CVEs: Remediation Copilot can produce patch or upgrade targets tailored to the asset and runtime. When fixes are unavailable, Remediation Copilot can provide mitigation recommendations.
- Addressing Misconfigurations: Remediation Copilot can identify current vs. recommended secure settings, provide exact configuration changes, and offer compensating controls if secure configuration cannot be applied.
- Addressing Code-Level Issues: Remediation Copilot can share secure coding practices and compensating measures when source code is not available, or suggest runtime or dependency-level actions where relevant.

Remediation Copilot only provides guidance; it does not apply changes. You must review and test instructions in your environment before implementing them. Recommendations depend on the data available in the ticket and your environment, so more context yields more precise instructions.

## Privacy and Consent

Remediation Copilot operates within Zscaler services, and data shared with it is not transmitted to external third parties. Some ticket fields might contain sensitive information, so users should review content before submitting it. By using the assistant, users consent to sharing the information they enter for generating remediation guidance and remain responsible for the data they choose to provide. Where possible, the system strips personally identifiable information (PII) before processing. To learn more about Zscaler's privacy practices and AI data handling, see [Acceptable Use Policy](https://help.zscaler.com/legal/acceptable-use-policy).

Use of AI-powered features in the Zscaler Security Operations (SecOps) platform is optional. Availability is controlled via admin opt-in in your account. To learn more, see [Managing AI Capabilities in the SecOps Platform](https://help.zscaler.com/uvm/managing-ai-capabilities-secops-platform).

[Image: Remediation Copilot Chat and Fixes Tab]
<!-- /ZS-ARTICLE -->
