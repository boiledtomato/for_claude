# Zscaler Help — SOC Workbench (part 1)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-31 03:58 UTC
Articles in this file: 18

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-agentic-soc","lastmod":"2026-08-28T15:45Z","nid":"1540781"} -->
## About Agentic SOC

- Source: https://help.zscaler.com/soc-workbench/about-agentic-soc
- Product: SOC Workbench
- Path: SOC Workbench Help > About Agentic SOC
- Last modified: 2026-08-28T15:45Z
- Summary: Information on the Agentic SOC page in the SOC Workbench application of the Security Operations Platform.

The Agentic SOC page is designed as a starting point in the SecOps Platform Admin Portal, providing a quick visual representation of your organization's overall security posture. You can instantly see which sources are responsible for alerts, the total number of alerts, the number of incidents created from alerts, and the triaged incidents.

Additionally, you quickly see one of the central value propositions of SOC Workbench: alert unification and reduction via agentic AI. Alerts are analyzed and intelligently grouped into incidents, representing the full attack story rather than isolated data points. SOC Workbench uses multiple AI agents (e.g., enrichment agents, correlation agents, triage agents) to ingest vast amounts of data to enrich alerts with extra context, correlate and aggregate alerts to create incidents, and then triage the incidents to assign a verdict and criticality.

The Agentic SOC page provides the following benefits and enables you to:

- See a high-level overview of your organization's current risk of a data breach.
- View information about alerts vs. incidents, as well as the verdict and criticality for each incident, across your organization.
- Use an interactive Sankey chart to understand how incidents directly affect your organization.

## About the Agentic SOC Page

On the Agentic SOC page (SOC Workbench > Agentic SOC), you can do the following:

1. See alert sources for your organization.
2. See the incidents that have been triaged by the SOC Workbench triage agents, which provide verdicts, reasoning, contradicting evidence, and analysis of discrepancies. You can click the different areas of the Sankey chart to see specific information about each finding: See image.
3. Set the date range for the data on the page.
4. See the total number of alerts affecting your organization from all alert sources, enriched with extra context from the SOC Workbench enrichment agents.
5. See the total number of incidents affecting your organization, correlated and aggregated from alerts across multiple factors (e.g., users, assets, artifacts, Indicators of Compromise, etc.) by the SOC Workbench correlation agents.
6. See the total amount of alert reduction (i.e., alerts that became actual incidents).

[Image: About Agentic SOC operational view]

[Image: Navigating the SOC Workbench Agentic SOC page in the Zscaler SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-alerts","lastmod":"2026-08-28T15:23Z","nid":"1534212"} -->
## About Alerts

- Source: https://help.zscaler.com/soc-workbench/about-alerts
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > About Alerts
- Last modified: 2026-08-28T15:23Z
- Summary: Information on how the Zscaler SOC Workbench Alerts page helps your team monitor alert activity across your organization.

Zscaler SOC Workbench alerts are designed to aggregate data from many different sources to help your Security Operations Center (SOC) team see a larger context and not be pressured into acting quickly based on an accumulation of noise. Instead of using traditional detection techniques to focus on individual alerts, you can instead focus on proactively anticipating and cutting off your adversaries’ next moves. With SOC Workbench alerts, you can take pressure off of your SOC teams and your organization as a whole by taking a proactive (instead of reactive) approach to the alerts being generated across applications.

SOC Workbench alerts provide the following benefits and enable you to:

- Aggregate alerts from many different sources to provide a larger context about threats across your organization.
- Stop focusing on individual alerts and avoid alert fatigue.
- Help your SOC team take a proactive approach to accumulated alerts from many sources.

## About the Alerts Page

On the Alerts page (SOC Workbench Portal > Alerts), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/soc-workbench/creating-managing-saved-views).
2. Search for specific alerts by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/soc-workbench/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/soc-workbench/using-filters) the incident information on the page, and add new filters to control the information you see.
5. See the number of alerts received from each integrated product.
6. View the spread of severity scores across products.
7. See the number of alerts received from integrated products vs. the number of identified incidents.
8. Update information for one or more alerts selected in the table.
9. [Create an incident](https://help.zscaler.com/soc-workbench/creating-custom-incident) based on one or more alerts selected in the table.
10. [Specify grouping](https://help.zscaler.com/soc-workbench/grouping-data-entity-pages) for the alerts in the table (e.g., alert first seen, alert last seen, alert tags).
11. Refresh table data, export table data to a CSV file, and specify the columns available in the table.
12. View a list of all alerts affecting your organization. For each alert, you can see the following by default:
  - **ID**: The ID number of the alert.
  - **Severity**: The severity of the alert (e.g., **Critical**, **High**, **Medium**).
  - **Severity Score**: The severity score of the alert.
  - **Title**: The title of the alert.
  - **Created**: The date the alert was created.
  - **Sources**: The source of the alert (e.g., alerts sent by Zscaler use the Zscaler logo).
  - **Tactic**: The MITRE ATT&CK tactic for the alert.
  - **Technique**: The MITRE ATT&CK technique for the alert.
  - **Verdict**: The verdict for the alert (e.g., **Malicious**, **Suspicious**, **Benign**)
  - **Original Severity**: The severity as determined by the source application.

For each alert in the list, you can click to see a detailed view of data about that alert. You can also customize the detailed view for alerts. To learn more, see [Customizing Alert and Incident Detail Pages](https://help.zscaler.com/soc-workbench/customizing-alert-and-incident-detail-pages).

[Image: The Alerts page in Zscaler SOC Workbench]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-assets","lastmod":"2026-08-28T15:42Z","nid":"1539869"} -->
## About Assets

- Source: https://help.zscaler.com/soc-workbench/about-assets
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Assets > About Assets
- Last modified: 2026-08-28T15:42Z
- Summary: Information on the Assets page in the SOC Workbench application of the SecOps platform.

The Assets page provides a centralized view of the assets in SOC Workbench.Each asset represents a single entity in your environment, unified (i.e., merged) and enriched with information from multiple sources. On this page, you can explore asset details and statuses, view the sources and records from which it was merged, view the findings it contains, and view the tickets it's related to.

The Assets page provides the following benefits and enables you to:

- Access and organize assets using system-saved views, filters, grouping options, and customizable table columns to focus on specific scenarios.
- Explore detailed information about each asset, including associated tickets and findings, risk scores, ownership, and the sources that the assets were detected on.
- Understand how each asset was merged from multiple sources into a unified entity.

## About the Assets Page

On the Assets page (SOC Workbench > Assets), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/unified/creating-managing-saved-views).
  - List of System-Saved Views
2. Search for specific assets by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/unified/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/unified/using-filters) assets by **Sources, Type, Site,**or**Operating System**.
5. Explore the **Overview**charts to gain high-level insights into the assets and their risk level in your environment. The charts are adjusted by the selected view and filters.
  - **Number of Assets by Risk Score**: Displays the number of assets in the different risk score ranges (in increments of 0.5). The X-axis represents the max severity score of active findings related to the asset, and the colors represent the risk category. You can hover over the bars to view the number of assets and the exact score range.
  - **Asset Count by Type**: Presents asset count categorized by asset type, displaying the 5 most frequently occurring types.
  - **Asset Count by Operating System**: Displays the number of assets categorized by OS, displaying the 5 most frequently occurring types.
6. [Group assets](https://help.zscaler.com/unified/grouping-data-entity-pages) by fields such as **Asset State**, **Asset ID**, or **Asset Owner ID**.
7. Refresh the page to reflect the most current information.
8. Export the list of assets and their associated details as a CSV file.
9. [Modify the columns displayed in the table.](https://help.zscaler.com/unified/managing-table-columns)
10. Select some or all assets on the page.
11. When the default **Active**saved view is selected, you can see the following details for each asset (or you can click an asset to open individual asset drawers):
  - **ID**: The asset's ID on the SecOps platform.
  - **Type**: The asset type (e.g., **Windows Workstation**, **Web Application**, **Container Image**).
  - **Name**: The asset's name.
  - **Risk Score**: The risk level of findings associated with the asset. The risk score is initially set by the default [reconciliation function](https://help.zscaler.com/unified/attribute-reconciliation-default-functions) and reflects the highest severity score among the findings. The default can be customized through [data unification](https://help.zscaler.com/unified/what-data-unification).
  - **Risk Mass**: The sum of all severity scores of the active findings associated with the asset.
  - **Owner ID**: The unit within the organization assigned to handling the asset.
  - **Sources**: The sources that the information on the asset is retrieved from.
  - **Is Crown** **Jewel**: A Boolean field with values of **TRUE**or **FALSE**indicating whether the asset is defined as a crown jewel asset (i.e., one of your organization's most valuable assets).
  - **Site**: The site that the asset is located on.
  - **First Seen**: The earliest date on which a finding on the asset was first detected.
  - **Last Seen**: The latest date on which a finding on the asset was detected.
  - **Total Findings**: The sum of the active findings per severity.
  - **Tags**: Tags pulled from your sources that include information about the asset that can be [extracted](https://help.zscaler.com/unified/configuring-field-unification) and used to enrich the asset data.

[Image: About Assets operational view]

The **Assets** page includes system views with predefined filter selections, providing quick access to common data scopes:

- **Active**: All active assets (i.e., all assets ingested into the account from your sources that have not been aged yet). This is the default view.
- **Vulnerable**: All active assets that have at least one active finding.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-incidents","lastmod":"2026-08-24T16:18Z","nid":"1534211"} -->
## About Incidents

- Source: https://help.zscaler.com/soc-workbench/about-incidents
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > About Incidents
- Last modified: 2026-08-24T16:18Z
- Summary: Information on how the Zscaler SOC Workbench Incidents page helps your team monitor incident activity across your organization.

Zscaler SOC Workbench incidents are designed to quickly provide complete context for all incidents and to let you pivot to specific asset information for deeper insights, enabling your Security Operations Center (SOC) team to make faster, more effective decisions. When you open the Incidents page, it provides high-level information at a glance about the incidents affecting your organization, and you can merge, sort, update, and add comments to the incidents listed in the table. Additionally, you can drill down to get granular information about each incident, including specific recommended actions from the AI Response Agent.

SOC Workbench incidents provide the following benefits and enable you to:

- See a high-level overview of all incidents affecting your organization.
- Use charts and graphs to quickly learn about incidents and the affected users.
- See specific recommended actions to respond to each incident.

## About the Incidents Page

On the Incidents page (SOC Workbench Portal > Incidents), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/soc-workbench/creating-managing-saved-views).
2. Search for specific incidents by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/soc-workbench/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/soc-workbench/using-filters) the incident information on the page, and add new filters to control the information you see.
5. See the number of incidents by status.
6. View the cumulative impact of risk to your organization, based on incident risk score.
7. See the number of incidents per user.
8. Update information for one or more incidents selected in the table.
9. Merge one or more incidents selected in the table.
10. Add comments to incidents selected in the table.
11. [Specify grouping](https://help.zscaler.com/soc-workbench/grouping-data-entity-pages) for the incidents in the table (e.g., incident first seen, incident last seen, incident tags).
12. Refresh table data, export table data to a CSV file, and specify the columns available in the table.
13. View a list of all incidents affecting your organization. For each incident, you can see the following by default:
  - **ID**: The ID number of the incident.
  - **Severity**: The severity of the incident (e.g., **Critical**, **High**, **Medium**).
  - **Risk Mass**: The risk mass of the incident.
  - **Type**: The type of incident (i.e., **AI-generated** or **Custom**).
  - **Title**: The title of the incident.
  - **Created**: The date the incident was created.
  - **Total Alerts**: The number of alerts associated with the incident.
  - **Sources**: The sources of the threat data (e.g., threats identified from Zscaler logs are identified by the Zscaler logo, threats identified from third-party apps are identified by app logos).
  - **Assignee**: The user assigned to the incident.
  - **Status**: The status of the incident (e.g., **Discovered**, **Confirmed**).
  - **Verdict**: The verdict for the incident (e.g., **Malicious**, **Suspicious**, **Benign**).

For each incident in the list, you can click to see a detailed view of data about that incident. You can also customize the detailed view for incidents. To learn more, see [Customizing Alert and Incident Detail Pages](https://help.zscaler.com/soc-workbench/customizing-alert-and-incident-detail-pages).

[Image: The Incidents page in Zscaler SOC Workbench]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-soc-workbench-dashboard","lastmod":"2026-08-28T15:30Z","nid":"1534209"} -->
## About the SOC Workbench Dashboard

- Source: https://help.zscaler.com/soc-workbench/about-soc-workbench-dashboard
- Product: SOC Workbench
- Path: SOC Workbench Help > Analytics > About the SOC Workbench Dashboard
- Last modified: 2026-08-28T15:30Z
- Summary: Information on how the Zscaler SOC Workbench dashboard helps your organization monitor threat activity across your organization.

The Zscaler SOC Workbench dashboard is designed to provide high-level information about your organization's current risk of a data breach. The dashboard is divided into a series of tables and interactive charts and graphs. You can simply click chart data to see more detailed information. You can also use default views and custom filtering to better understand how alerts relate to actual incidents, as well as your organization’s placement within the MITRE ATT&CK framework during a specified period. Most importantly, the dashboard lets you focus on incidents instead of alerts, showing you the statuses of incidents across your organization, including which users and hosts have been affected.

The SOC Workbench dashboard provides the following benefits and enables you to:

- See a high-level overview of your organization's current risk of a data breach.
- View information about alerts vs. incidents across your organization.
- Use interactive charts and graphs to understand how alerts from different sources are affecting your organization.

## About the SOC Workbench Dashboard Page

On the SOC Workbench Dashboard page (SOC Workbench Portal > Dashboard), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/soc-workbench/creating-managing-saved-views).
2. Set the date range for the data on the dashboard.
3. [Filter](https://help.zscaler.com/soc-workbench/using-filters) the alert and incident information on the page, and add new filters to control the information you see.
4. View alert and incident information, along with trends for the previous 30 days.
5. Compare the number of alerts vs. the number of incidents across your organization.
6. See the statuses of identified incidents.
7. View the mean time to respond for incidents across your organization.
8. See a list of the top 5 devices affected by incidents in your organization.
9. View a list of the top 5 high-risk incidents in your organization.
10. See the top 5 alerts generated by applications vs. the number of actual incidents.
11. View where SOC Workbench places the alerts in your organization on the list of MITRE ATT&CK tactics.

[Image: Dashboard page in Zscaler SOC Workbench]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-users-soc-workbench","lastmod":"2026-08-28T15:33Z","nid":"1539870"} -->
## About Users in SOC Workbench 

- Source: https://help.zscaler.com/soc-workbench/about-users-soc-workbench
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > About Users in SOC Workbench 
- Last modified: 2026-08-28T15:33Z
- Summary: Information on the Users page in the SOC Workbench application of the Security Operations Platform.

The Users page provides a centralized view of user accounts and their identity types in SOC Workbench. You can also review the identity findings for each user and the total number of critical or high-severity findings so that you can take the necessary action. Only identities in an active state display on the Users page.

The Users page provides the following benefits and enables you to:

- Aggregate users from different sources for a centralized view.
- View details and identity findings for each user.
- Monitor internal and external users.

## About the Users Page

On the Users page (SOC Workbench > Users), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/soc-workbench/creating-managing-saved-views).
2. Search for specific assets by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/soc-workbench/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/soc-workbench/using-filters) assets by **Name, Type**, **Identity Type**, or **Source**.
5. View the distribution of identities based on the type (**User**, **Service Principa**l, or **Special Identities**).
6. View the distribution of users based on the type (**Internal**or **External**).
7. [Group users](https://help.zscaler.com/soc-workbench/grouping-data-entity-pages) by category such as user first seen, user last seen, user tags, etc.
8. Refresh the user table to reflect the most current information.
9. Export the list of users and their associated details as a CSV file.
10. [Modify the columns displayed in the table.](https://help.zscaler.com/soc-workbench/managing-table-columns)
11. View the list of users. Click a user to [view detailed information](https://help.zscaler.com/unified/viewing-user-details). For each user in the list, you can see:
  - **ID**: The unique identifier of the user.
  - **Name**: The name of the user.
  - **Email**: The email address of the user.
  - **Is Admin**: The user's admin role status. If the user is an admin, this field shows `true`.
  - **Total Identity Findings**: The total number of **Critical**, **High**, **Medium**, and **Low**identity findings for the user.
  - **Sources**: The source of the user (e.g., **Active Directory**, **Microsoft Entra ID**, etc.).
  - **Identity Type**: The type of identity (**User**, **Service Principa**l, or **Special Identities**).
  - **Last Activity Date**: The date of most recent activity.
  - **State**: The state of the user's account (**Active**or **Inactive**).
  - **Type**: The type of user (**Internal**or **External**).

[Image: Users page in SOC Workbench with callouts]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-alert-scores","lastmod":"2026-08-28T15:20Z","nid":"1534213"} -->
## Configuring Alert Scores

- Source: https://help.zscaler.com/soc-workbench/configuring-alert-scores
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Configuring Alert Scores
- Last modified: 2026-08-28T15:20Z
- Summary: Information on how to configure Zscaler SOC Workbench alert scores to help your team monitor alert activity across your organization.

As you use SOC Workbench, you can customize alert scoring by creating new factors or editing existing factors for alerts, MITRE ATT&CK classifications, users, and assets.

To configure alert scores:

1. [Access the SecOps platform](https://help.zscaler.com/uvm/accessing-and-navigating-security-operations-secops-platform).
2. In the Zscaler Security Operations (SecOps) platform, click **Threats**.

See image.

1. In the left-side navigation, go to **Settings** > **Scoring**. The **Alert Score** page opens.
2. On the **Alert Score**page, do one of the following:
  - Update an Existing Factor
  - Add a New Factor
3. Click **Save** to save your changes, or click **Save & Process**to save your changes and process alert data with the updates.

[Image: Clicking the Threats app in the Zscaler SecOps platform]

1. In the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section, select an existing factor from the list.

See image.

The detail panel for the alert factor opens.

1. In the detail panel: See image.
  - **Factor Type**: View the factor type, based on whether you are updating a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section.
  - **Field**: Select a field from the drop-down menu. The options in the **Field** change based on whether you are adding a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section. Based on your selection, use the operators and fields to add parameters for the factor.
  - **Factor Name**: Specify a unique name for the factor. By default, SOC Workbench uses the value from the **Field** drop-down menu to populate the **Factor Name** field.
2. Click **Apply**.

The detail panel for the alert factor closes.

1. Click **Apply**. The updated alert factor appears on the **Alert Score** page.

[Image: Selecting an existing alert factor in the SOC Workbench Portal]

[Image: Applying an update to an existing alert factor in the SOC Workbench Portal]

1. In the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section, click **Add Factor**.

See image.

The detail panel for the alert factor opens.

1. In the detail panel: See image.
  - **Factor Type**: View the factor type, based on whether you are adding a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section.
  - **Field**: Select a field from the drop-down menu. The options in the **Field** change based on whether you are adding a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section. Based on your selection, use the operators and fields to add parameters for the factor.
  - **Factor Name**: Specify a unique name for the factor. By default, SOC Workbench uses the value from the **Field** drop-down menu to populate the **Factor Name** field.
2. Click **Add**.

The detail panel for the alert factor closes.

1. Click **Apply**. The new alert factor appears on the **Alert Score** page.

[Image: Adding a new alert factor in the SOC Workbench Portal]

[Image: Applying settings to a new alert factor in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-incident-rules","lastmod":"2026-08-28T15:19Z","nid":"1534214"} -->
## Configuring Incident Rules

- Source: https://help.zscaler.com/soc-workbench/configuring-incident-rules
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Configuring Incident Rules
- Last modified: 2026-08-28T15:19Z
- Summary: Information on how to configure Zscaler SOC Workbench incident rules to help your team monitor incidents across your organization.

Incident rules determine the data you see on the [Incidents page](https://help.zscaler.com/soc-workbench/about-incidents) and are a key element of SOC Workbench's focus on incidents over alerts. Incidents allow you to see connections and bring together multiple disparate alerts from different products into a single location. For example, suppose 50 people in your organization receive phishing emails. Without SOC Workbench, you'd receive numerous alerts from different products about the same phishing attempt. SOC Workbench, on the other hand, consolidates all alerts into a single incident, which significantly reduces the number of alerts. Moreover, SOC Workbench incidents help you differentiate between users who received but didn't open the phishing email vs. those who opened the email, leading to data exfiltration.

By default, SOC Workbench provides a number of incident rules to cover multiple use cases (e.g., phishing scams that lead to data exfiltration, data exfiltration via cloud storage). This out-of-the-box functionality saves your Security Operations Center (SOC) team from having to create high-value incident rules manually. Additionally, you can either use the AI Incident Generation Agent or you can manually create and customize incident rules to suit your business needs.

To configure incident rules:

1. [Access the Zscaler Security Operations (SecOps) platform](https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform).
2. In the SecOps platform, click **SOC Workbench**.

See image.

1. In the left-side navigation, go to **Settings** > **Incident Rules**. The **Incident Rules** page opens.
2. On the **Incident Rules**page, do one of the following:
  - Use the AI Incident Generation Agent to Create an Incident Rule
  - Manually Create an Incident Rule
  - Edit an Existing Incident Rule

[Image: Clicking the SOC Workbench app in the Zscaler SecOps platform]

1. Click **Create**.

See image.

The **Create Incident Rule** window opens.

See image.

1. In the **Create Incident Rule** window, in the **Describe Incident Rule** field, enter a basic description for the rule you want to create.
2. (Optional) Click the description prompts provided in the **Sample Incident Rules** section as a starting point for the new incident rule. The prompt appears in the **Describe Incident Rule** field for you to edit.

See image.

1. Click the **Submit** icon to submit the incident rule prompt.

See image.

The **Incident Rules Copilot** panel opens.

See image.

1. In the **Incident Rules Copilot** panel, follow the prompts provided by the AI Incident Generation Agent to clarify the incident rule, then click the **Submit** icon.

See image.

With the details clarified, the Incident Rules Copilot creates the incident rule.

See image.

1. Clarify and edit the rule as needed, then click **Save** to save your changes, or click **Save & Process** to save your changes and process alert data with the updates. The new incident rule appears on the **Incident Rules** page.

[Image: The Create Incident Rule window in the SOC Workbench Portal]

[Image: The Sample Incident Rules in the Create Incident Rule window in the SOC Workbench Portal]

[Image: The Submit icon in the Create Incident Rule window in the SOC Workbench Portal]

[Image: The Incident Rules Copilot panel in the SOC Workbench Portal]

[Image: A prompt for the Incident Rules Copilot in the SOC Workbench Portal]

[Image: An incident rule created by the Incident Rules Copilot panel in the SOC Workbench Portal]

1. Click **Create**.

See image.

The **Create Incident Rule** window opens.

1. In the **Create Incident Rule** window, click **I would like to create a manual incident rule**. The **Create New Incident Rule** page opens. See image.
2. On the **Create New Incident Rule** page:
  1. Enter the following details:
    - **Rule Name**: Specify a unique name for the incident rule. The **Active** toggle for the rule is enabled by default. You can toggle the rule on or off as needed.
    - **Incident Name**: Specify a unique name for the incident itself.
    - **Description**: (Optional) Specify a description for the incident rule.
  2. In the **Incident Logic** section, in the **Which alerts can create this incident** section, specify an alert parameter in the **Select Field** drop-down menu, then specify an operator and value for the alert parameter. If needed, click **AND** or **OR** to add additional parameters, and repeat as needed.
  3. In the **Grouping & Context**section:
    1. Select whether to **Create an incident for each alert** to create separate incidents for each alert that triggers the rule.
    2. Select **Group alerts into 1 incident**to reduce noise around alerts and let your SOC team see common alerts as part of a single incident.
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts SOC Workbench attaches to the incident.
      2. Select **Group alerts only when they have the same entities** to specify that SOC Workbench group the alerts by entity (e.g., assets, users, etc.).
        - In the **Group by** drop-down menu, specify the entities that SOC Workbench uses to group alerts.
        - Select whether to **Group alerts if any of the entities are matched** or to **Group alerts if all of the entities are matched** to specify how SOC Workbench filters alerts by entity for the incident rule.
    3. Select **Attach alerts to incident** to specify the alerts that SOC Workbench attaches to the incident.
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts SOC Workbench attaches to the incident.
      2. In the **Select Field** drop-down menu, specify any parameters for the alerts to attach to the incident, then specify an operator and value for the alert parameters. If needed, click **AND** or **OR** to add additional parameters, and repeat as needed.
      3. Select **Attach alerts only when they have the same entities** to specify that SOC Workbench group the alerts by entity (e.g., assets, users, etc.).
        - In the **Group by** drop-down menu, specify the entities that SOC Workbench uses to group alerts.
        - Select whether to **Attach alerts if any of the entities are matched** or to **Attach alerts if all of the entities are matched** to specify how SOC Workbench filters alerts by entity for the incident rule.

See image.

1. Click **Save** to save your changes, or click **Save & Process** to save your changes and process alert data with the updates. The new incident rule appears on the **Incident Rules** page.

[Image: Creating a manual incident rule in the SOC Workbench Portal]

[Image: Creating a manual incident rule in the SOC Workbench Portal]

[Image: Creating an incident rule in the SOC Workbench Portal]

[Image: Creating an incident rule in the SOC Workbench Portal]

1. Select an existing incident rule from the list, then click **Edit**.

See image.

The **Edit Incident Rule** page opens.

1. On the **Edit Incident Rule** page:
  1. Enter the following details:
    - **Rule Name**: Specify a unique name for the incident rule. The **Active** toggle for the rule is enabled by default. You can toggle the rule on or off as needed.
    - **Incident Name**: Specify a unique name for the incident itself.
    - **Description**: (Optional) Specify a description for the incident rule.
  2. In the **Incident Logic** section, in the **Which alerts can create this incident** section, specify an alert parameter in the **Select Field** drop-down menu, then specify an operator and value for the alert parameter. If needed, click **AND** or **OR** to add additional parameters, and repeat as needed.
  3. In the **Grouping & Context**section:
    1. Select whether to **Create an incident for each alert** to create separate incidents for each alert that triggers the rule.
    2. Select **Group alerts into 1 incident**to reduce noise around alerts and let your SOC team see common alerts as part of a single incident.
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts SOC Workbench attaches to the incident.
      2. Select **Group alerts only when they have the same entities** to specify that SOC Workbench group the alerts by entity (e.g., assets, users, etc.).
        - In the **Group by** drop-down menu, specify the entities that SOC Workbench uses to group alerts.
        - Select whether to **Group alerts if any of the entities are matched** or to **Group alerts if all of the entities are matched** to specify how SOC Workbench filters alerts by entity for the incident rule.
    3. Select **Attach alerts to incident** to specify the alerts that SOC Workbench attaches to the incident.
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts SOC Workbench attaches to the incident.
      2. In the **Select Field** drop-down menu, specify any parameters for the alerts to attach to the incident, then specify an operator and value for the alert parameters. If needed, click **AND** or **OR** to add additional parameters, and repeat as needed.
      3. Select **Attach alerts only when they have the same entities** to specify that SOC Workbench group the alerts by entity (e.g., assets, users, etc.).
        - In the **Group by** drop-down menu, specify the entities that SOC Workbench uses to group alerts.
        - Select whether to **Attach alerts if any of the entities are matched** or to **Attach alerts if all of the entities are matched** to specify how SOC Workbench filters alerts by entity for the incident rule.

See image.

1. Click **Save** to save your changes, or click **Save & Process** to save your changes and process alert data with the updates. The updated incident rule appears on the **Incident Rules** page.

[Image: Editing an incident rule in the SOC Workbench Portal]

[Image: Editing an incident rule in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-severity-categories","lastmod":"2026-08-28T15:21Z","nid":"1541100"} -->
## Configuring Severity Categories

- Source: https://help.zscaler.com/soc-workbench/configuring-severity-categories
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Configuring Severity Categories
- Last modified: 2026-08-28T15:21Z
- Summary: How to assign severity score to severity categories for findings in SOC Workbench.

Severity levels are categorical values assigned to findings based on their severity score. In SOC Workbench, you can customize severity categories by mapping severity score ranges to those categories. The configured severity categories can then be used in dashboards and charts throughout the SOC Workbench UI. Proper severity settings ensure that remediation teams can efficiently focus on high-risk vulnerabilities while aligning their processes with organizational or compliance requirements.

The scores mapped to severity categories are configured on the Severity Settings page. These numerical values are derived from external sources and organizational risk factors. Each finding is assigned a score, which determines its corresponding severity category based on the configuration in Severity Settings. To learn more, see [Understanding Severity Scores](https://help.zscaler.com/soc-workbench/understanding-severity-scores).

To configure severity categories:

1. [Access the Zscaler Security Operations (SecOps) platform](https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform).
2. In the SecOps platform, click **SOC Workbench**.

See image.

1. In the left-side navigation, go to **Settings** > **Severity**. The **Severity Settings**page opens.
2. For each severity category (i.e., **Critical**, **High**, **Medium**, **Low**, **Info**): See image.
  1. **Score starts at**: Enter the minimum score where the severity category starts. The minimum score is included in the category.
  2. **Score is less than**: Enter the maximum score as an optional cutoff. Every score must be included in a severity category to ensure complete coverage. The settings cannot be saved if there are gaps between ranges that are not assigned to a category. Additionally, severity ranges must be unique (non-overlapping) and ordered in ascending numerical value.
3. Use the **Severity Range Preview**slider to review your score mapping visually and ensure the entire score range (i.e., 0 to 100) is fully accounted for.
4. Save the settings in one of the following ways: See image.
  - Click **Save**. The severity settings apply the next time the finding or the ticket entity is processed.
  - In the **Save**drop-down menu, select **Save & Run** to save the settings and immediately apply them in your account.

[Image: Clicking the SOC Workbench app in the Zscaler SecOps platform]

[Image: Mapping Score Ranges to Severity Categories in SOC Workbench]

[Image: Severity Settings Save Drop-down Menu in SOC Workbench]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/creating-custom-incident","lastmod":"2026-08-28T15:20Z","nid":"1540861"} -->
## Creating a Custom Incident

- Source: https://help.zscaler.com/soc-workbench/creating-custom-incident
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Creating a Custom Incident
- Last modified: 2026-08-28T15:20Z
- Summary: Information on how to create a custom incident in Zscaler SOC Workbench.

Although one of SOC Workbench’s biggest benefits is its ability to use multiple AI agents to ingest vast amounts of alert data to create specific and actionable incidents, you might also want to create custom incidents based on your organization’s business needs. You can create a custom incident based on one or more alerts from the collated list available on the [Alerts page](https://help.zscaler.com/soc-workbench/about-alerts) in SOC Workbench.

To create a custom incident:

1. [Access the Zscaler Security Operations (SecOps) platform](https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform).
2. In the SecOps platform, click **SOC Workbench**.

See image.

1. In the left-side navigation, go to **Alerts**. The **Alerts** page opens.
2. Select one or more alerts from the list, then click **Create Incident**. The **Create Incident from Alerts** drawer opens. See image.
3. In the **Create Incident from Alerts** drawer:
  - **Severity**: Select a severity level (e.g., **High**, **Medium**, **Low**) from the drop-down menu.
  - **Status**: Select a status (e.g., **Discovered**, **Remediated**, **Confirmed**) from the drop-down menu.
  - **Title**: Enter a title for the incident, or use the title automatically generated by SOC Workbench.
  - **Verdict**: Select a verdict (e.g., **Accepted**, **Executed**, **Partial**) from the drop-down menu.
4. Click **Create Incident**. See image. You receive a confirmation message and the incident appears on the [Incidents page](https://help.zscaler.com/soc-workbench/about-incidents).

[Image: Clicking the SOC Workbench app in the Zscaler SecOps platform]

[Image: The Create Incident from Alerts window in the SOC Workbench Portal]

[Image: Completed fields in the Create Incident from Alerts window in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/customizing-alert-and-incident-detail-pages","lastmod":"2026-08-28T15:20Z","nid":"1534217"} -->
## Customizing Alert and Incident Detail Pages

- Source: https://help.zscaler.com/soc-workbench/customizing-alert-and-incident-detail-pages
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Customizing Alert and Incident Detail Pages
- Last modified: 2026-08-28T15:20Z
- Summary: How to customize the incident and alert detail pages in Zscaler SOC Workbench.

To help make SOC Workbench incident and alert data easier to parse, you can customize the detail pages you see when you click an incident on the [Incidents page](https://help.zscaler.com/soc-workbench/about-incidents) or an alert on the [Alerts page](https://help.zscaler.com/soc-workbench/about-alerts).

See image.

SOC Workbench provides a default view with important alert and incident details, but you can customize the default configuration or create new configurations. The tiles on the page (e.g., Details, Entities) appear as tabs on the detail pages for both incidents and alerts.

See image.

The following steps explain how to customize the Alert Detail page, but the process is the same for customizing both alert details and incident details.

To customize the **Incident Detail** page:

1. [Access the Zscaler Security Operations (SecOps) platform](https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform).
2. In the SecOps platform, click **SOC Workbench**.

See image.

1. In the left-side navigation, go to **Settings** > **UI Config**. The **Alert UI Configuration** page opens. To switch to the **Incident UI Configuration** page, select **Incident**from the **Entity** drop-down menu.

See image.

1. (Optional) To remove the **Severity** and **Status Timeline** data from the incident detail, deselect the checkboxes for those settings.
2. Click **Add Field** to add a new field or table to a tab:
3. Do one of the following:
  - Add a Field
  - Add a Table
4. Hover over the tiles and fields to see options for editing, moving, or deleting each element, and do one or more of the following:
  - Click **Edit** to update information for elements:
    - Fields
    - Tables
  - Click and drag tiles to reorder them on the page.
  - Click the **Delete**icon to delete an element.
5. To add a tab to the selected page, click **Add Tab** > **Add Custom Tab**, then populate the following fields: See image.
  - **Tab Name**: Enter a name for the tab on the **Alert Detail** page.
  - **Tab main projection**: (Optional) Select an option from the drop-down menu for what users will see when they drill down on the data on the tab.
  - **Should show the tab by field**: (Optional) Select an option from the drop-down menu to specify the data field that appears first on the tab.
  - **Type**: Select whether to display data on the tab as **Fields**, **2 Columns**, **Text**, or **Table**.
6. Click **Save** to save the existing configuration, or click **Save as New Type** to save a new configuration.

[Image: Clicking the SOC Workbench app in the Zscaler SecOps platform]

[Image: Incident and alert detail pages in the SOC Workbench Portal]

[Image: The default incident detail page layout example in the SOC Workbench Portal]

[Image: The Entity drop-down menu on the incident detail page in the SOC Workbench Portal]

1. Populate the following fields: See image.
  - **Display Name**: Enter the name for the field on the **Alert Detail** page.
  - **Field Name**: Select the data used to populate the field from the drop-down menu.
  - **Link Field**: (Optional) Select linked data from the drop-down menu. Instead of data, you see a **Details** hyperlink on the **Alert Detail** page.
  - **AI Icon**: Select to add a **Based on AI** icon and tooltip to the field.
  - **Entity Field**: Select to include an icon link to entities associated with the field data.
  - **Display When Empty**: Select to include the field even if it contains no data.
2. Click **Apply**.

1. Populate the following fields: See image.
  - **Display Name**: Enter the name for the field on the **Alert Detail** page.
  - **Field Name**: Select the data used to populate the field from the drop-down menu.
  - **Link Field**: (Optional) Select linked data from the drop-down menu. Instead of data, you see a **Details** hyperlink on the **Alert Detail** page.
  - **Display When Empty**: Select to include the field even if it contains no data.
2. Click **Apply**.

1. Update the information as needed: See image.
  - **Display Name**: Enter the name for the field on the **Alert Detail** page.
  - **Field Name**: Select the data used to populate the field from the drop-down menu.
  - **Link Field**: (Optional) Select linked data from the drop-down menu. Instead of data, you see a **Details** hyperlink on the **Alert Detail** page.
  - **AI Icon**: Select to add a **Based on AI** icon and tooltip to the field.
  - **Entity Field**: Select to include an icon link to entities associated with the field data.
  - **Display When Empty**: Select to include the field even if it contains no data.
2. Click **Apply**.

1. Update the information as needed: See image.
  - **Display Name**: Enter the name for the field on the **Alert Detail** page.
  - **Field Name**: Select the data used to populate the field from the drop-down menu.
  - **Link Field**: (Optional) Select linked data from the drop-down menu. Instead of data, you see a **Details** hyperlink on the **Alert Detail** page.
  - **Display When Empty**: Select to include the field even if it contains no data.
2. Click **Apply**.

[Image: Adding a tab on the Alert UI Configuration page in the SOC Workbench Portal]

[Image: Adding a field on the Alert UI Configuration page in the SOC Workbench Portal]

[Image: Adding a table on the Alert UI Configuration page in the SOC Workbench Portal]

[Image: Editing a field on the Alert UI Configuration page in the SOC Workbench Portal]

[Image: Editing a table on the Alert UI Configuration page in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/customizing-incident-and-alert-statuses","lastmod":"2026-08-28T15:21Z","nid":"1541098"} -->
## Customizing Incident and Alert Statuses

- Source: https://help.zscaler.com/soc-workbench/customizing-incident-and-alert-statuses
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Customizing Incident and Alert Statuses
- Last modified: 2026-08-28T15:21Z
- Summary: How to customize incident and alert statuses in Zscaler SOC Workbench.

To help support your Security Operations Center (SOC) team's internal workflows, SOC Workbench allows you to customize the statuses that are available for [incidents](https://help.zscaler.com/soc-workbench/about-incidents) and [alerts](https://help.zscaler.com/soc-workbench/about-alerts). SOC Workbench provides a default set of statuses for incidents and alerts, but you can configure existing statuses (e.g., reorder and rename) and create new statuses. Statuses appear in various incident and alert views in the SOC Workbench UI.

See image.

The following steps explain how to customize alert statuses, but the process is the same for customizing incident statuses.

To customize alert statuses:

1. [Access the Zscaler Security Operations (SecOps) platform](https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform).
2. In the SecOps platform, click **SOC Workbench**.

See image.

1. In the left-side navigation, go to **Settings** > **Statuses**. The **Statuses** page opens. Select **Alert** from the **Entity Type** drop-down menu.

See image.

1. Click **Add Status**to add a new status within one of the existing buckets, then enter a name for the status and click **Save**. See image. The new status appears in the bucket. See image.
2. Hover over the tiles and fields to see options for editing, moving, or deleting each element, and do one or more of the following:
  - Click the **Edit** icon to update information for elements.
  - Click and drag tiles to reorder them on the page.
  - Click the **Delete**icon to delete an element.
3. (Optional) Click **Add Bucket**to add a status bucket, then configure the new bucket: See image.
  1. **Bucket Name**: Enter a unique name for the bucket.
  2. **Closed**: (Optional) Select whether the bucket automatically includes this status.
  3. **Remediated**: (Optional) Select whether the bucket automatically includes this status.
  4. Click **Save**.

[Image: Clicking the SOC Workbench app in the Zscaler SecOps platform]

[Image: Alert status configuration and the alert detail page in the SOC Workbench Portal]

[Image: The Entity Type drop-down menu on the Statuses page in the SOC Workbench Portal]

[Image: Saving a new status on the Statuses page in the SOC Workbench Portal]

[Image: New alert status on the Statuses page in the SOC Workbench Portal]

[Image: Adding a new bucket on the Statuses page in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/step-by-step-config-guide-soc-workbench","lastmod":"2026-08-28T15:37Z","nid":"1534195"} -->
## Step-by-Step Configuration Guide for SOC Workbench

- Source: https://help.zscaler.com/soc-workbench/step-by-step-config-guide-soc-workbench
- Product: SOC Workbench
- Path: SOC Workbench Help > Step-by-Step Configuration Guide for SOC Workbench
- Last modified: 2026-08-28T15:37Z
- Summary: How to use Zscaler SOC Workbench to track and prevent data breaches in your organization.

This guide explains the basic high-level steps for how to use Zscaler SOC Workbench and provides links to more information. SOC Workbench ingests data from other Zscaler tools, as well as many third-party applications, so you should familiarize yourself with various product integrations to better understand the data that SOC Workbench uses to identify threats to your organization. In addition, SOC Workbench uses the MITRE ATT&CK framework to give you a clear and instant picture of how threats are progressing in your organization. Zscaler recommends reading the following articles before you begin using SOC Workbench:

- [Understanding the Zscaler Cloud Architecture for Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture)
- [What Is Zscaler Deception?](https://help.zscaler.com/deception/what-zscaler-deception)
- [About Data Loss Prevention](https://help.zscaler.com/zia/about-data-loss-prevention)
- [MITRE ATT&CK Overview](https://attack.mitre.org/)

## Configuring SOC Workbench

To configure SOC Workbench:

- Step 1: Ensure Completion of Prerequisite Tasks
- Step 2: Assess the Security Threats to Your Organization
- Step 3: Use SOC Workbench Dashboards and Reports to Further Analyze Data
- Step 4: Configure the Zscaler SecOps Platform

1. License SOC Workbench: SOC Workbench is a standalone product that is licensed separately from other parts of the Zscaler ecosystem. To learn more about provisioning SOC Workbench for your organization, contact your Zscaler Account team.
2. [Configure Internet & SaaS (ZIA)](https://help.zscaler.com/zia/step-step-configuration-guide-internet-saas): To ensure that Internet & SaaS logs are available for SOC Workbench, Internet & SaaS must be fully configured for your organization. If your organization already uses Internet & SaaS, no extra configuration is required for SOC Workbench to access Internet & SaaS logs.
3. [Configure Deception](https://help.zscaler.com/deception/step-step-configuration-guide-zscaler-deception): To ensure that Deception logs are available for SOC Workbench, Deception must be fully configured for your organization. If your organization already uses Deception, no extra configuration is required for SOC Workbench to access Deception logs.
4. Integrate with third-party applications: SOC Workbench also ingests log data from third-party sources like CrowdStrike and Gmail. To learn more about third-party integrations, see the [SOC Workbench help](https://help.zscaler.com/soc-workbench/administration/security-operations-platform/connectors).

SOC Workbench uses easy-to-understand charts, tables, and graphs to give you visibility into vast amounts of threat data across your organization. As you navigate the SOC Workbench Portal, you can use the interconnected data points to easily switch from macro to micro views of the data (e.g., clicking incident information on the **Agentic SOC** dashboard opens the list of incidents associated with the verdict or criticality level). Throughout the SOC Workbench UI, you can drill down to get granular detail, including AI summaries, entity information, threat data, etc. You can use the following basic workflow to assess your threat risk:

- a. Evaluate your overall threat landscape on the Agentic SOC dashboard.
- b. Examine the incidents affecting your organization.
- c. Look at the alerts associated with incidents.

After you've examined the SOC Workbench data for your organization, you might need to move beyond the dashboards and reports available by default. You can set up custom [dashboards](https://help.zscaler.com/soc-workbench/analytics/dashboards) and [reports](https://help.zscaler.com/soc-workbench/analytics/reports) to help your SOC team better analyze threat data across your organization.

From the Zscaler Security Operations (SecOps) Platform, you can [customize and manage](https://help.zscaler.com/soc-workbench/administration) the data in the platform, including data sources, integrations, and outbound integrations (i.e., outegrations).

On the [Agentic SOC dashboard](https://help.zscaler.com/soc-workbench/about-agentic-soc), you can not only see an overview of the alerts in your environment, but also how many alerts have actually become incidents. Additionally, you can see high-level triage information about each incident, including its verdict (e.g., Malicious, Benign, etc.) and criticality (e.g., Critical, High, Medium, etc.). The **Agentic SOC** dashboard is the starting point each time you access the SOC Workbench Portal.

On the [Incidents](https://help.zscaler.com/soc-workbench/about-incidents) page, you can see an overview of risk metrics across incidents, incidents categorized by assignees, and a list of specific incidents. Clicking an incident lets you drill down to see more granular data, including an AI-based summary that explains what's happening, affected users and assets, specific recommended actions, and additional details about how the incident is affecting your organization. The **Incidents** page lets you focus on real threats instead of being distracted by alert overload.

The [Alerts](https://help.zscaler.com/soc-workbench/about-alerts) page brings all alerts from all source applications into a single place. When you click an alert, you can see its source, how often it has occurred, its MITRE ATT&CK tactic and technique, the entities associated with it, and its risk score. The **Alerts** page is designed to help your SOC team make sense of all of the alerts coming from different sources across your organization.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/taking-action-based-incidents","lastmod":"2026-08-24T16:37Z","nid":"1540857"} -->
## Taking Action Based on Incidents

- Source: https://help.zscaler.com/soc-workbench/taking-action-based-incidents
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Taking Action Based on Incidents
- Last modified: 2026-08-24T16:37Z
- Summary: Information on how to take action based on incidents in Zscaler SOC Workbench.

The primary goal of SOC Workbench is to take pressure off of your Security Operations Center (SOC) analysts by cutting out noise and demystifying the threats that are affecting your organization. SOC Workbench uses its multi-agentic AI approach to do most of the heavy lifting, aggregating countless alerts from multiple products to produce a manageable number of prioritized incidents, complete with data around the triage process and actionable recommendations.

The following steps provide general guidance on what to do when SOC Workbench identifies incidents in your organization:

1. [Access the Zscaler Security Operations (SecOps) platform](https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform).
2. In the SecOps platform, click **SOC Workbench**.

See image.

1. In the left-side navigation, go to **Agentic SOC**. The **Agentic SOC**page opens.
2. On the **Agentic SOC**page, observe the incidents identified by the SOC Workbench triage agents as most dangerous. Pay close attention to verdicts and criticality. See image.
3. Start with the most urgent threats by examining the details for the incidents that SOC Workbench identifies as **Malicious** and **Critical**. See image.
4. Look at the details for each prioritized incident, focusing on response recommendations from the SOC Workbench Response Agent. There, you can see which source identified a threat, as well as specific steps you can take to remediate that threat. See image.
5. With the most serious threats to the organization remediated, examine the [extensive data](https://help.zscaler.com/soc-workbench/viewing-incident-details) around each incident to look for problem areas, such as [users](https://help.zscaler.com/soc-workbench/about-users) or [assets](https://help.zscaler.com/soc-workbench/about-assets) that are particularly vulnerable to incidents.

[Image: Clicking the SOC Workbench app in the Zscaler SecOps platform]

[Image: Incidents identified by SOC Workbench Triage Agents on the Agentic SOC page]

[Image: Incidents identified as Malicious and Critical on the SOC Workbench Agentic SOC page]

[Image: Clicking incident details to determine remediation steps in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/understanding-severity-scores","lastmod":"2026-08-28T15:21Z","nid":"1541099"} -->
## Understanding Severity Scores

- Source: https://help.zscaler.com/soc-workbench/understanding-severity-scores
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Understanding Severity Scores
- Last modified: 2026-08-28T15:21Z
- Summary: Information on how SOC Workbench severity scores are calculated.

In alert management, multiple factors within categories (e.g., the product that produced an alert, whether a user is an admin, etc.) are given severity scores that are used to prioritize remediation efforts. Individual severity scores typically range from 0 to 10, where a higher score indicates greater criticality requiring immediate attention and resolution, and thus takes precedence over vulnerabilities with lower scores. SOC Workbench then adds the total score from each factor to produce the overall severity score.

Standard severity scoring frameworks often fall short in addressing an organization's unique context. SOC Workbench calculates severity scores based on context and incorporates data from various sources and analyses, providing more effective and accurate risk assessment. To learn more, see [Configuring Alert Scores](https://help.zscaler.com/soc-workbench/configuring-alert-scores).

You can drill down into the details for an alert to see how SOC Workbench calculated the severity score.

See image.

[Image: The Alert Detail page with score calculation in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/understanding-zscaler-soc-workbench","lastmod":"2026-08-28T15:37Z","nid":"1534207"} -->
## Understanding Zscaler SOC Workbench

- Source: https://help.zscaler.com/soc-workbench/understanding-zscaler-soc-workbench
- Product: SOC Workbench
- Path: SOC Workbench Help > Understanding Zscaler SOC Workbench
- Last modified: 2026-08-28T15:37Z
- Summary: A high-level explanation of how Zscaler SOC Workbench works.

Zscaler SOC Workbench is specifically built to eliminate gaps that traditional Security Operations Center (SOC) tools ignore. Powered by the Zscaler Data Fabric for Security, SOC Workbench transforms isolated alerts from across your security stack into a prioritized, holistic view of threats. Seamless integration with the Zero Trust Exchange (ZTE) platform provides additional threat context and initiates inline controls to automatically mitigate risk.

As part of the Zscaler Security Operations (SecOps) portfolio, SOC Workbench also connects exposure insights with threat prioritization, ensuring that vulnerable assets and identities are factored into weighted results. It also enables the real-world threat activity that your SOC team sees to inform which vulnerabilities they should address first.

SOC Workbench revolutionizes security operations by enabling analysts to overcome alert fatigue and focus on the most critical issues. By consolidating and deduplicating alerts, enhancing them with rich context, and enabling smart prioritization of risks, SOC Workbench empowers your team to efficiently detect and respond to the threats that pose the highest risk to your organization.

At a high level, SOC Workbench provides three main value propositions:

- **It unifies all alerts**: SOC Workbench lets you track not only Zscaler alerts, but also alerts from third-party applications (e.g., Gmail, CrowdStrike) in a single console, unifying alerts from disparate sources with rich context. As alerts come in, SOC Workbench provides visibility across assets, identities, and exposures in a unified application. This enables your analysts to eliminate the multiple-system inefficiency that comes from receiving countless alerts from multiple tools.
- **It focuses on threats, not alerts**: SOC Workbench's AI-driven threat analysis transforms raw alerts into contextualized, actionable threats. SOC Workbench focuses on providing the right business context, understanding the threats associated with alerts, and leveraging historical attack patterns to identify the first 5 things you must address in your environment.
- **It helps stop the biggest threats in your environment**: With the ability to accelerate investigations by providing all threat details on a single page, as well as the ability to search logs from one unified location, understand the asset impact within the same screen, streamline investigations, and enable faster, better, and proportional responses, SOC Workbench was designed from the ground up to take pressure off of your SOC teams and your organization as a whole.

See image.

More specifically, SOC Workbench simplifies day-to-day activities for your SOC teams by:

- Ingesting Vast Amounts of Data
- Providing Enriched Alerts
- Correlating Alerts
- Prioritizing Actionable Threats
- Facilitating Investigation
- Driving Resolution

To learn more about how to navigate SOC Workbench, see [Step-by-Step Configuration Guide for SOC Workbench](https://help.zscaler.com/soc-workbench/step-by-step-config-guide-soc-workbench).

SOC Workbench pulls in first-party data from Zscaler products and integrates seamlessly with third-party solutions.

Each alert is enhanced with contextual metadata (like identity, asset, location, and threat intelligence) at the fabric layer.

Alerts are analyzed and intelligently grouped into incidents, representing the full attack story rather than isolated data points.

Risk-based scoring identifies high-priority incidents requiring immediate action, making it easy to focus on critical threats.

You can explore correlated incidents interactively, pivot through entities, and collaborate with team members to get to the root cause.

With specific recommended actions from the SOC Workbench Response Agent, you can remediate risks proactively and efficiently. For example, suppose there were multiple failed login attempts by a user, followed by successful login activity from an unusual geographic location, privilege escalation, and access to sensitive files. SOC Workbench correlates these activities into a single incident, assigns the incident a risk score, and clearly outlines a recommended response, enabling you to pivot into action within minutes.

[Image: An illustration of the main SOC Workbench value proposition]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/viewing-incident-details","lastmod":"2026-08-24T16:20Z","nid":"1540782"} -->
## Viewing Incident Details

- Source: https://help.zscaler.com/soc-workbench/viewing-incident-details
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Incidents & Alerts > Viewing Incident Details
- Last modified: 2026-08-24T16:20Z
- Summary: Information on how to view incident details in Zscaler SOC Workbench.

As incidents are the most important and actionable element in SOC Workbench, you can find information about specific incidents in several ways. Starting with the [Agentic SOC dashboard](https://help.zscaler.com/soc-workbench/about-agentic-soc) and extending to nearly every other page in the SOC Workbench UI, you can easily access specific and actionable incident information.

This article explains how to use the Incidents page to access extensive information about each incident affecting your organization.

To view incident details:

1. [Access the Zscaler Security Operations (SecOps) platform](https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform).
2. In the SecOps platform, click **SOC Workbench**.

See image.

1. In the left-side navigation, go to **Settings** > **Incidents**. The **Incidents**page opens.
2. On the **Incidents**page, click an incident in the table to view the detail page for that incident. See image.

To learn more about the layout of the **Incidents** page, see [About Incidents](https://help.zscaler.com/soc-workbench/about-incidents).

1. On the **Details** tab, you can see basic details, risk mass, an AI incident summary created by the SOC Workbench Summary Agent, the [MITRE ATT&CK](https://attack.mitre.org/tactics/enterprise/) path for the incident, and the AI response recommendation created by the SOC Workbench Response Agent. See image.

You can click different elements on the **Details** tab to see additional information that provides more context about the current incident. For example, if you click a response recommendation, you'll see expanded information that includes specific configuration steps.

See image.

1. Click the **Affected Entities** tab to see the affected assets and users. You can click the arrows on an entity to open a panel that contains expanded information. See image.
2. Click the **Triage** tab to see the information that the SOC Workbench Triage Agent uses to determine the incident's verdict and criticality (e.g., date, time, source, affected entity). See image.
3. Click the **Attack Path** tab to see how the incident has moved across the [MITRE ATT&CK matrix](https://attack.mitre.org/). As a threat moves further to the right in the matrix, your organization is at a higher risk of a data breach. You can easily determine whether a technique has actually been observed, or whether SOC Workbench is predicting its advancement. Some events are identified as **Possible**, meaning that they might have happened but were not captured as part of the SOC Workbench threat detection. See image.
4. Click the **Alerts** tab to see which alerts and sources SOC Workbench's Enrichment and Correlation Agents used to create the incident. You can click any of the alerts in the list to see more advanced information. See image.
5. Click the **Activity** tab to see the activities (e.g., user action, sync from third party, system) that led to the incident. See image.
6. Click the **Comments** tab to add comments or see comments from other admins. See image.

[Image: Clicking the SOC Workbench app in the Zscaler SecOps platform]

[Image: The detail page for an incident in the SOC Workbench Portal]

[Image: The Details tab for an incident in the SOC Workbench Portal]

[Image: The response recommendation for an incident in the SOC Workbench Portal]

[Image: The Affected Entities tab for an incident in the SOC Workbench Portal]

[Image: The Triage tab for an incident in the SOC Workbench Portal]

[Image: The Attack Path tab for an incident in the SOC Workbench Portal]

[Image: The Alerts tab for an incident in the SOC Workbench Portal]

[Image: The Activity tab for an incident in the SOC Workbench Portal]

[Image: The Comments tab for an incident in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/what-zscaler-soc-workbench","lastmod":"2026-08-28T15:36Z","nid":"1534206"} -->
## What Is Zscaler SOC Workbench?

- Source: https://help.zscaler.com/soc-workbench/what-zscaler-soc-workbench
- Product: SOC Workbench
- Path: SOC Workbench Help > What Is Zscaler SOC Workbench?
- Last modified: 2026-08-28T15:36Z
- Summary: How Zscaler SOC Workbench works and fits in with other products in the Zscaler ecosystem.

Security Operations Center (SOC) teams face a daily battle, wading through endless alerts coming from disparate tools—networks, endpoints, cloud environments, email security applications, identity platforms, and more. Bringing together all of these isolated alerts to understand the full picture of an attack is challenging, tedious, and often error prone. As a result, SOC analysts are overwhelmed with alert fatigue, wasting precious time chasing false positives instead of remediating real threats.

See image.

SOC Workbench uses a multi-agentic AI approach to provide rich, context-driven data that helps your SOC teams achieve faster, more efficient, and more confident threat detection and response. SOC Workbench is a unified platform to prioritize and triage threats so that you can rapidly resolve business-critical incidents. Unlike other tools that simply provide a list of incidents, SOC Workbench provides risk-based prioritization that focuses not only on a threat but also its business context. Additionally, SOC Workbench uses advanced AI-driven capabilities to seamlessly narrow gaps between proactive threat detection and reactive incident response.

You can use SOC Workbench to transform security operations and help your analysts cut through alert fatigue to stay laser-focused on what matters most. By unifying relevant alerts, enriching alerts with deep context, and powering smart prioritization of risks, SOC Workbench enables you to quickly and accurately identify and contain the threats that pose the greatest risk to your business.

## Key Benefits

SOC Workbench protects your organization by providing the following key features and benefits:

- **Risk-Based Threat Prioritization**: SOC Workbench consolidates business intelligence and threat context to help you understand all critical threats in your environment.
- **Automated Correlation**: SOC Workbench ties multiple alerts into threats by correlating and aggregating entities to drive historical attack patterns and contextual data.
- **Built-In Context Enrichment**: SOC Workbench provides deeper insights into each alert with information such as geographic locations, user roles, asset criticality, vulnerability data, and threat intelligence.
- **Efficiency Gains with Actionable Incidents**: SOC Workbench focuses analyst time on business-critical incidents, reducing noise around alerts to drive quicker remediation.
- **Proactive Attack Prediction**: SOC Workbench surfaces attack paths with related Indicators of Compromise and possible attack progressions, allowing analysts to stop threats before they lead to a data breach.
- **Specific Response Recommendations**: SOC Workbench's AI Response Agent takes triaged incidents and provides targeted remediation recommendations that save your analysts countless hours of manual work.

In short, SOC Workbench is designed to give you comprehensive visibility across your security landscape. Using an intelligence-first, incident-driven workflow to automate context, SOC Workbench takes pressure off of your SOC teams and your organization as a whole by enabling your analysts to rapidly identify and remediate threats.

See image.

To learn more, see [Understanding Zscaler SOC Workbench](https://help.zscaler.com/soc-workbench/understanding-zscaler-soc-workbench).

[Image: An illustration of the biggest SOC team struggles]

[Image: An illustration of the main benefits that SOC Workbench provides]
<!-- /ZS-ARTICLE -->
