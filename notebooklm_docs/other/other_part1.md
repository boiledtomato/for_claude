# Zscaler Help — Other (part 1)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-11-02 09:57 UTC
Articles in this file: 79

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/about-agentic-soc","lastmod":"2026-09-17T11:45Z","nid":"1540781"} -->
## About Agentic SOC

- Source: https://help.zscaler.com/agentic-soc/about-agentic-soc
- Product: Agentic SOC
- Path: Agentic SOC Help > About Agentic SOC
- Last modified: 2026-09-17T11:45Z
- Summary: Information on the Agentic SOC page in the Agentic SOC application of the Agentic Security Operations Platform.

The Agentic SOC page is designed as a starting point in the Agentic SecOps Platform Admin Portal, providing a quick visual representation of your organization's overall security posture. You can instantly see which sources are responsible for alerts, the total number of alerts, the number of incidents created from alerts, and the triaged incidents.

Additionally, you quickly see one of the central value propositions of Agentic SOC: alert unification and reduction via agentic AI. Alerts are analyzed and intelligently grouped into incidents, representing the full attack story rather than isolated data points. Agentic SOC uses multiple AI agents (e.g., enrichment agents, correlation agents, triage agents) to ingest vast amounts of data to enrich alerts with extra context, correlate and aggregate alerts to create incidents, and then triage the incidents to assign a verdict and criticality.

The Agentic SOC page provides the following benefits and enables you to:

- See a high-level overview of your organization's current risk of a data breach.
- View information about alerts vs. incidents, as well as the verdict and criticality for each incident, across your organization.
- Use an interactive Sankey chart to understand how incidents directly affect your organization.

## About the Agentic SOC Page

On the Agentic SOC page, you can do the following:

1. See alert sources for your organization.
2. See the incidents that have been triaged by the Agentic SOC triage agents, which provide verdicts, reasoning, contradicting evidence, and analysis of discrepancies. You can click the different areas of the Sankey chart to see specific information about each finding: See image.
3. Set the date range for the data on the page.
4. See the total number of alerts affecting your organization from all alert sources, enriched with extra context from the Agentic SOC enrichment agents.
5. See the total number of incidents affecting your organization, correlated and aggregated from alerts across multiple factors (e.g., users, assets, artifacts, Indicators of Compromise, etc.) by the Agentic SOC correlation agents.
6. See the total amount of alert reduction (i.e., alerts that became actual incidents).

[Image: About Agentic SOC operational view]

[Image: Navigating the Agentic SOC Agentic SOC page in the Zscaler SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/about-agents","lastmod":"2026-09-09T13:14Z","nid":"1545377"} -->
## About Agents

- Source: https://help.zscaler.com/agentic-soc/about-agents
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Agents > About Agents
- Last modified: 2026-09-09T13:14Z
- Summary: Information on what the Agents page is in Zscaler Agentic SOC.

The Agents page is a dashboard that tracks the activity and performance of your AI agents in Agentic SOC. It shows key operational metrics — including alerts investigated, time saved, and resolution rates — and provides a per-agent breakdown of investigation counts and trends over time.

The Agents page provides the following benefits and enables you to:

- Measure the time and manual labor your AI agents save your SOC team.
- Identify which agents are driving the most impact across your investigations.
- Share and demonstrate AI performance data with stakeholders.

## About the Agents Page

On the Agents page (**Agentic SOC** > **Agents**), you can do the following:

1. Use the time range filter to scope all metrics and trend data. This filter applies to the performance metrics and per-agent investigation counts displayed on the page. The default time range is 7 days.
2. Use the filters to sort and view metrics by alert type, agent name, and the following agent categories:
  - **Correlation**: Agents that link related alerts and events into unified incidents, reducing noise and providing a complete picture of an attack.
  - **Data Processing**: Agents that apply models and scoring logic to raw event data to support prioritization and downstream analysis.
  - **Email Security**: Agents that evaluate email-based threats and return verdicts with supporting analysis.
  - **Enrichment:** Agents that augment alerts with additional context — such as threat intelligence, framework mappings, or indicator metadata — to support deeper investigation.
  - **Investigation**: Agents that analyze threat indicators and evidence to assess risk and produce insights that analysts can act on.
  - **Remediation**: Agents that generate and execute response actions across integrated platforms, produce insights that inform remediation decisions.
  - **Response**: Agents that translate detected threats into clear explanations and actionable guidance tailored to the specific threat type.
  - **Security**: Agents that produce structured summaries and reports to give analysts a clear starting point for review.
  - **Triage**: Agents that perform end-to-end pre-investigation of incoming alerts, delivering a risk-scored verdict so analysts can skip the initial legwork.
  - **User Behavioral Analysis**: Agents that profile user and identity activity to detect anomalous behavior and surface suspicious patterns.
  - **Utility**: Agents that handle data extraction, parsing, and normalization tasks that support the work of other agents.
3. View metrics to gauge your agents' performance:
  - **Alerts Investigated**: Number of alerts processed within your selected time range.
  - **Actions Performed**: Total actions taken by all agents. Each alert can trigger multiple agents, and each agent can perform multiple actions, so this number is significantly higher than the alerts processed.
  - **FTE Multiplier**: Time saved divided by the total hours in the selected time range (number of days × 8 hours). This represents the equivalent number of full-time analysts the agents contribute to your team during the selected period.
  - **Time Saved**: Hours saved on manual work.
  - **Sub-5 Min Resolution**: Percentage of alerts resolved under 5 minutes.
4. Search for a specific agent by name to quickly locate it without scrolling.
5. Click an agent card to view the agent's job description and a filtered list of all alerts the agent has impacted. From the agent details view, you can: See image. [Image: Agent details page containing a job description, work details, and impacted alerts]
  - View a detailed job description to understand the agent's functionality.
  - Customize, download, and share the Alerts table.
  - Open the list of alerts on the **Alerts** page.

[Image: Overview of Agents page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/about-alerts","lastmod":"2026-09-17T11:45Z","nid":"1534212"} -->
## About Alerts

- Source: https://help.zscaler.com/agentic-soc/about-alerts
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > About Alerts
- Last modified: 2026-09-17T11:45Z
- Summary: Information on how the Zscaler Agentic SOC Alerts page helps your team monitor alert activity across your organization.

Zscaler Agentic SOC alerts are designed to aggregate data from many different sources to help your Security Operations Center (SOC) team see a larger context and not be pressured into acting quickly based on an accumulation of noise. Instead of using traditional detection techniques to focus on individual alerts, you can instead focus on proactively anticipating and cutting off your adversaries’ next moves. With Agentic SOC alerts, you can take pressure off of your SOC teams and your organization as a whole by taking a proactive (instead of reactive) approach to the alerts being generated across applications.

Agentic SOC alerts provide the following benefits and enable you to:

- Aggregate alerts from many different sources to provide a larger context about threats across your organization.
- Stop focusing on individual alerts and avoid alert fatigue.
- Help your SOC team take a proactive approach to accumulated alerts from many sources.

## About the Alerts Page

On the Alerts page (Agentic SOC Portal > Alerts), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/unified/creating-managing-saved-views).
2. Search for specific alerts by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/unified/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/unified/using-filters) the incident information on the page, and add new filters to control the information you see.
5. See the number of alerts received from each integrated product.
6. View the spread of severity scores across products.
7. See the number of alerts received from integrated products vs. the number of identified incidents.
8. Update information for one or more alerts selected in the table.
9. [Create an incident](https://help.zscaler.com/agentic-soc/creating-custom-incident) based on one or more alerts selected in the table.
10. [Specify grouping](https://help.zscaler.com/unified/grouping-data-entity-pages) for the alerts in the table (e.g., alert first seen, alert last seen, alert tags).
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

For each alert in the list, you can click to see a detailed view of data about that alert. You can also customize the detailed view for alerts. To learn more, see [Customizing Alert and Incident Detail Pages](https://help.zscaler.com/agentic-soc/customizing-alert-and-incident-detail-pages).

[Image: The Alerts page in Zscaler Agentic SOC]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/about-assets-agentic-soc","lastmod":"2026-09-02T11:46Z","nid":"1539869"} -->
## About Assets in Agentic SOC

- Source: https://help.zscaler.com/agentic-soc/about-assets-agentic-soc
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Assets > About Assets in Agentic SOC
- Last modified: 2026-09-02T11:46Z
- Summary: Information on the Assets page in the Agentic SOC application of the Agentic Security Operations Platform.

The Assets page provides a centralized view of the assets in Agentic SOC.Each asset represents a single entity in your environment, unified (i.e., merged) and enriched with information from multiple sources. On this page, you can explore asset details and statuses, view the sources and records from which it was merged, view the findings it contains, and view the tickets it's related to.

The Assets page provides the following benefits and enables you to:

- Access and organize assets using system-saved views, filters, grouping options, and customizable table columns to focus on specific scenarios.
- Explore detailed information about each asset, including associated tickets and findings, risk scores, ownership, and the sources that the assets were detected on.
- Understand how each asset was merged from multiple sources into a unified entity.

## About the Assets Page

On the Assets page (Agentic SOC > Assets), you can do the following:

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

<!-- ZS-ARTICLE {"url":"/agentic-soc/about-incidents","lastmod":"2026-10-08T13:28Z","nid":"1534211"} -->
## About Incidents

- Source: https://help.zscaler.com/agentic-soc/about-incidents
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > About Incidents
- Last modified: 2026-10-08T13:28Z
- Summary: Information on how the Zscaler Agentic SOC Incidents page helps your team monitor incident activity across your organization.

Agentic SOC incidents are designed to quickly provide complete context for all incidents and to let you pivot to specific asset information for deeper insights, enabling your Security Operations Center (SOC) team to make faster, more effective decisions. When you open the Incidents page, it provides high-level information at a glance about the incidents affecting your organization, and you can merge, sort, update, and add comments to the incidents listed in the table. To learn how to investigate and remediate an incident, see [Viewing Incident Details](https://help.zscaler.com/agentic-soc/viewing-incident-details).

The Incidents page provides the following benefits and enables you to:

- View high-level data measuring the impact of all incidents.
- Use charts and graphs to quickly learn about the impact of all incidents.
- Filter and sort incidents based on severity, data source, time range, and more.
- Make bulk edits and exports.
- Save filters (custom views) of incidents most important to you.

## About the Incidents Page

On the Incidents page (Agentic SOC > Incidents), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/unified/creating-managing-saved-views).
2. Search for specific incidents by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/unified/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, grouping).
4. [Filter](https://help.zscaler.com/unified/using-filters) the incident information on the page, and add new filters to control the information you see.
5. See the number of incidents by status, severity, and verdict, displayed as charts.
6. Modify multiple incidents at once, such as updating the status, merging incidents together, and adding comments.
7. [Specify grouping](https://help.zscaler.com/unified/grouping-data-entity-pages) for the incidents in the table (e.g., incident first seen, incident last seen, incident tags).
8. Refresh table data, export table data to a CSV file, and configure the columns displayed in the table.
9. View a list of all incidents affecting your organization. The table displays the following columns by default:
  - **ID**: The ID number of the incident.
  - **Severity**: The severity of the incident (e.g., **Critical**, **High**, **Medium**).
  - **Type**: The type of incident (e.g., **AI**, **Platform**, **Custom**).
  - **Title**: The title of the incident.
  - **Created**: The date the incident was created.
  - **Alerts Count**: The number of alerts associated with the incident.
  - **Sources**: The sources of the threat data (e.g., threats identified from Zscaler logs are identified by the Zscaler logo, threats identified from third-party apps are identified by app logos).
  - **Assignee**: The user assigned to the incident.
  - **Status**: The status of the incident (e.g., **Discovered**, **Confirmed, Closed**).
  - **Verdict**: The verdict for the incident (e.g., **Malicious**, **Suspicious**, **Benign**).

For each incident in the list, you can click to see a detailed view of data about that incident. You can also customize the detailed view for incidents. To learn more, see [Customizing Alert and Incident Detail Pages](https://help.zscaler.com/agentic-soc/customizing-alert-and-incident-detail-pages).

[Image: An annotated screenshot of the Incidents landing page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/about-soc-workbench-dashboard","lastmod":"2026-09-17T11:44Z","nid":"1534209"} -->
## About the SOC Workbench Dashboard

- Source: https://help.zscaler.com/agentic-soc/about-soc-workbench-dashboard
- Product: Agentic SOC
- Path: Agentic SOC Help > Analytics > About the SOC Workbench Dashboard
- Last modified: 2026-09-17T11:44Z
- Summary: Information on how the SOC Workbench dashboard for Agentic SOC helps your organization monitor threat activity across your organization.

The SOC Workbench Dashboard is designed to provide high-level information about your organization's current risk of a data breach. The dashboard is divided into a series of tables and interactive charts and graphs. You can simply click chart data to see more detailed information. You can also use default views and custom filtering to better understand how alerts relate to actual incidents, as well as your organization’s placement within the MITRE ATT&CK framework during a specified period. Most importantly, the dashboard lets you focus on incidents instead of alerts, showing you the statuses of incidents across your organization, including which users and hosts have been affected.

The SOC Workbench Dashboard provides the following benefits and enables you to:

- See a high-level overview of your organization's current risk of a data breach.
- View information about alerts vs. incidents across your organization.
- Use interactive charts and graphs to understand how alerts from different sources are affecting your organization.

## About the SOC Workbench Dashboard Page

On the SOC Workbench Dashboard page (Agentic SOC > Dashboard), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/unified/creating-managing-saved-views).
2. Set the date range for the data on the dashboard.
3. [Filter](https://help.zscaler.com/unified/using-filters) the alert and incident information on the page, and add new filters to control the information you see.
4. View alert and incident information, along with trends for the previous 30 days.
5. Compare the number of alerts vs. the number of incidents across your organization.
6. See the statuses of identified incidents.
7. View the mean time to respond for incidents across your organization.
8. See a list of the top 5 devices affected by incidents in your organization.
9. View a list of the top 5 high-risk incidents in your organization.
10. See the top 5 alerts generated by applications vs. the number of actual incidents.
11. View where Agentic SOC places the alerts in your organization on the list of MITRE ATT&CK tactics.

[Image: Dashboard page in Zscaler Agentic SOC]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/configuring-alert-scores","lastmod":"2026-10-02T12:45Z","nid":"1534213"} -->
## Configuring Alert Scores

- Source: https://help.zscaler.com/agentic-soc/configuring-alert-scores
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Configuring Alert Scores
- Last modified: 2026-10-02T12:45Z
- Summary: Information on how to configure Zscaler Agentic SOC alert scores to help your team monitor alert activity across your organization.

As you use Agentic SOC, you can customize alert scoring by creating new factors or editing existing factors for alerts, MITRE ATT&CK classifications, users, and assets.

To configure alert scores:

1. [Access the Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal).
2. From the top navigation, click **Agentic SOC**.
3. In the left-side navigation, go to **Settings** > **Scoring**. The **Alert Score** page opens.
4. On the **Alert Score**page, do one of the following:
  - Update an Existing Factor
  - Add a New Factor
5. Click **Save** to save your changes, or click **Save & Process**to save your changes and process alert data with the updates.

1. In the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section, select an existing factor from the list.

See image.

The detail panel for the alert factor opens.

1. In the detail panel: See image.
  - **Factor Type**: View the factor type, based on whether you are updating a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section.
  - **Field**: Select a field from the drop-down menu. The options in the **Field** change based on whether you are adding a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section. Based on your selection, use the operators and fields to add parameters for the factor.
  - **Factor Name**: Specify a unique name for the factor. By default, Agentic SOC uses the value from the **Field** drop-down menu to populate the **Factor Name** field.
2. Click **Apply**.

The detail panel for the alert factor closes.

1. Click **Apply**. The updated alert factor appears on the **Alert Score** page.

[Image: Selecting an existing alert factor in the Agentic SOC Portal]

[Image: Applying an update to an existing alert factor in the Agentic SOC Portal]

1. In the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section, click **Add Factor**.

See image.

The detail panel for the alert factor opens.

1. In the detail panel: See image.
  - **Factor Type**: View the factor type, based on whether you are adding a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section.
  - **Field**: Select a field from the drop-down menu. The options in the **Field** change based on whether you are adding a factor for the **Alert** section, the **MITRE ATT&CK** section, the **User** section, or the **Asset** section. Based on your selection, use the operators and fields to add parameters for the factor.
  - **Factor Name**: Specify a unique name for the factor. By default, Agentic SOC uses the value from the **Field** drop-down menu to populate the **Factor Name** field.
2. Click **Add**.

The detail panel for the alert factor closes.

1. Click **Apply**. The new alert factor appears on the **Alert Score** page.

[Image: Adding a new alert factor in the Agentic SOC Portal]

[Image: Applying settings to a new alert factor in the Agentic SOC Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/configuring-incident-rules","lastmod":"2026-10-02T12:43Z","nid":"1534214"} -->
## Configuring Incident Rules

- Source: https://help.zscaler.com/agentic-soc/configuring-incident-rules
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Configuring Incident Rules
- Last modified: 2026-10-02T12:43Z
- Summary: Information on how to configure Zscaler Agentic SOC incident rules to help your team monitor incidents across your organization.

Incident rules determine the data you see on the [Incidents page](https://help.zscaler.com/soc-workbench/about-incidents) and are a key element of Agentic SOC's focus on incidents over alerts. Incidents allow you to see connections and bring together multiple disparate alerts from different products into a single location. For example, suppose 50 people in your organization receive phishing emails. Without Agentic SOC, you'd receive numerous alerts from different products about the same phishing attempt. Agentic SOC, on the other hand, consolidates all alerts into a single incident, which significantly reduces the number of alerts. Moreover, Agentic SOC incidents help you differentiate between users who received but didn't open the phishing email vs. those who opened the email, leading to data exfiltration.

By default, Agentic SOC provides a number of incident rules to cover multiple use cases (e.g., phishing scams that lead to data exfiltration, data exfiltration via cloud storage). This out-of-the-box functionality saves your Security Operations Center (SOC) team from having to create high-value incident rules manually. Additionally, you can either use Incident Rules Copilot or you can manually create and customize incident rules to suit your business needs.

To configure incident rules:

1. [Access the Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal).
2. From the top navigation, click **Agentic SOC**.
3. In the left-side navigation, go to **Settings** > **Incident Rules**. The **Incident Rules** page opens.
4. On the **Incident Rules**page, do one of the following:
  - Use Incident Rules Copilot to create an incident rule.
  - Manually create an incident rule.
  - Edit an existing incident rule.

1. Click **Create**.

See image.

The **Create Incident Rule** window appears.

See image.

1. In the **Create Incident Rule** window, in the **Describe Incident Rule** field, enter a basic description for the rule you want to create.
2. (Optional) Click the description prompts provided in the **Sample Incident Rules** section as a starting point for the new incident rule. The prompt appears in the **Describe Incident Rule** field for you to edit.

See image.

1. Click the **Submit** icon to submit the incident rule prompt.

See image.

The **Incident Rules Copilot** panel opens.

See image.

1. In the **Incident Rules Copilot** panel, follow the prompts provided by the **Incident Rules Copilot** to clarify the incident rule, then click the **Submit** icon.

See image.

With the details clarified, the **Incident Rules Copilot** creates the incident rule.

See image.

1. Clarify and edit the rule as needed, then click **Save** to save your changes, or click **Save & Process** to save your changes and process alert data with the updates. The new incident rule appears on the **Incident Rules** page.

[Image: The Create Incident Rule window in the Agentic SOC Portal]

[Image: The Sample Incident Rules in the Create Incident Rule window in the Agentic SOC Portal]

[Image: The Submit icon in the Create Incident Rule window in the Agentic SOC Portal]

[Image: The Incident Rules Copilot panel in the Agentic SOC Portal]

[Image: A prompt for the Incident Rules Copilot in the Agentic SOC Portal]

[Image: An incident rule created by the Incident Rules Copilot panel in the Agentic SOC Portal]

1. Click **Create**.

See image.

The **Create Incident Rule** window opens.

1. In the **Create Incident Rule** window, click **I would like to create a manual incident rule**. The **Create New Incident Rule** page opens.
2. On the **Create New Incident Rule** page:
  1. Enter the following details:
    - **Rule Name**: Specify a unique name for the incident rule. The **Active** toggle for the rule is enabled by default. You can toggle the rule on or off as needed.
    - **Incident Name**: Specify a unique name for the incident itself.
    - **Description**: (Optional) Specify a description for the incident rule.
  2. In the **Incident Logic** section, in the **Which alerts can create this incident** section, specify an alert parameter in the **Select Field** drop-down menu, then specify an operator and value for the alert parameter. If needed, click **AND** or **OR** to add additional parameters, and repeat as needed.
  3. In the **Grouping & Context**section:
    1. Select whether to **Create an incident for each alert** to create separate incidents for each alert that triggers the rule.
    2. Select **Group alerts into 1 incident**to reduce noise around alerts and let your SOC team see common alerts as part of a single incident.
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts Agentic SOC are attached to the incident.
      2. Select **Group alerts only when they have the same entities** to specify that Agentic SOC group the alerts by entity (e.g., assets, users).
        - In the **Group by** drop-down menu, specify the entities that Agentic SOC uses to group alerts.
        - Select whether to **Group alerts if any of the entities are matched** or to **Group alerts if all of the entities are matched** to specify how Agentic SOC filters alerts by entity for the incident rule.
    3. Select **Attach alerts to incident** to specify the alerts that Agentic SOC attaches to the incident.
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts Agentic SOC are attached to the incident.
      2. In the **Select Field** drop-down menu, specify any parameters for the alerts to attach to the incident, then specify an operator and value for the alert parameters. If needed, click **AND** or **OR** to add additional parameters, and repeat as needed.
      3. Select **Attach alerts only when they have the same entities** to specify that Agentic SOC group the alerts by entity (e.g., assets, users).
        - In the **Group by** drop-down menu, specify the entities that Agentic SOC uses to group alerts.
        - Select whether to **Attach alerts if any of the entities are matched** or to **Attach alerts if all of the entities are matched** to specify how Agentic SOC filters alerts by entity for the incident rule.

See image.

1. Click **Save** to save your changes, or click **Save & Process** to save your changes and process alert data with the updates. The new incident rule appears on the **Incident Rules** page.

[Image: Creating a manual incident rule in the Agentic SOC Portal]

[Image: Creating an incident rule in the Agentic SOC Portal]

[Image: Creating an incident rule in the Agentic SOC Portal]

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
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts Agentic SOC attaches to the incident.
      2. Select **Group alerts only when they have the same entities** to specify that Agentic SOC group the alerts by entity (e.g., assets, users).
        - In the **Group by** drop-down menu, specify the entities that Agentic SOC uses to group alerts.
        - Select whether to **Group alerts if any of the entities are matched** or to **Group alerts if all of the entities are matched** to specify how Agentic SOC filters alerts by entity for the incident rule.
    3. Select **Attach alerts to incident** to specify the alerts that Agentic SOC attaches to the incident.
      1. On the **Alerts within the following time window** timeline, specify a time period for which alerts Agentic SOC attaches to the incident.
      2. In the **Select Field** drop-down menu, specify any parameters for the alerts to attach to the incident, then specify an operator and value for the alert parameters. If needed, click **AND** or **OR** to add additional parameters, and repeat as needed.
      3. Select **Attach alerts only when they have the same entities** to specify that Agentic SOC group the alerts by entity (e.g., assets, users).
        - In the **Group by** drop-down menu, specify the entities that Agentic SOC uses to group alerts.
        - Select whether to **Attach alerts if any of the entities are matched** or to **Attach alerts if all of the entities are matched** to specify how Agentic SOC filters alerts by entity for the incident rule.

See image.

1. Click **Save** to save your changes, or click **Save & Process** to save your changes and process alert data with the updates. The updated incident rule appears on the **Incident Rules** page.

[Image: Editing an incident rule in the Agentic SOC Portal]

[Image: Editing an incident rule in the Agentic SOC Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/configuring-incident-workflows","lastmod":"2026-10-30T09:26Z","nid":"1546616"} -->
## Configuring Incident Workflows

- Source: https://help.zscaler.com/agentic-soc/configuring-incident-workflows
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Configuring Incident Workflows
- Last modified: 2026-10-30T09:26Z
- Summary: How to configure Agentic SOC incident workflows and dispatch incidents to third-party systems.

Incident workflows in Agentic SOC allow you to automate and standardize the incident lifecycle according to your organization's operational requirements. By defining rule sets for dispatching incidents to external systems, you can reduce manual administrative effort and maintain data integrity throughout the remediation process.

You can create and manage the list of available incident severities. To learn more, see [Configuring Severity Categories](https://help.zscaler.com/agentic-soc/configuring-severity-categories).

The Incident Workflows page displays the Auto-Dispatch to External Systems category. It allows you to automatically create items or alerts in [Splunk](https://help.zscaler.com/uvm/configuring-splunk-outegration), [AWS S3](https://help.zscaler.com/uvm/configuring-aws-s3-outegration), and CrowdStrike when specific criteria are met.

See image.

[Image: Incident Workflows page showing Workflow Category, Condition, and Action sections]

To learn more, see [About Incidents](https://help.zscaler.com/agentic-soc/about-incidents).

## Configuring Incident Workflow Rule Sets

For access to incident workflows, your assigned role must include the **Read**, **Create**, **Edit**, and **Delete** permissions under the **Threats App - Incident Settings** resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/unified/creating-custom-roles) and [Creating & Managing Users](https://help.zscaler.com/unified/creating-managing-users).

To configure incident workflow rule sets:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, go to **Settings** > **Incident Lifecycle**.
3. Click **Incident Workflows**. See image. [Image: Incident Workflows card highlighted on Incident Lifecycles page] The **Incident Workflows** page appears.
4. On the **Incident Workflows** page, in the **Auto-Dispatch to External Systems** category, click the **Add New Rule** icon. See image. [Image: Add New Rule icon] The **Auto-Dispatch to External System** drawer opens.
5. In the **Auto-Dispatch to External System** drawer, enter the following details:
  1. **Name**: Enter a name for the new rule.
  2. **Active**: Enable to activate the rule.
  3. Set the criteria: See image. [Image: Auto-Dispatch to External Systems drawer showing an example rule]
    1. **IF**: Define the rule condition that determines which incidents the rule should apply to:
      1. **Incident status is:** Select the checkbox to add incident statuses that trigger this rule.
      2. **Incident severity is**: Select the checkbox to add incident severity levels that trigger this rule.
      3. (Optional) Create custom rules for specific fields:
        1. Select a field that the condition should be based on.
        2. Select an operator (e.g., **Equals**, **Contains**). Available operators vary depending on the field type, indicated to the left of the field name.
        3. Enter the value that the rule should apply to.
        4. (Optional) Use AND/OR logic to define compound rules.
          - AND populates the field only if the field meets all conditions in the rule.
          - OR populates the field if the field meets any of the conditions in the rule.
    2. **THEN**:
      1. **Create a third-party item:**Select the outegration for the system you want to trigger.
      2. **Create New Outegration:** If no outegration is configured for the destination you want to dispatch to, click to open the **Connect a New Outegration**page and create one. After you create an outegration, it appears in the **Create a third-party item** in the drop-down menu.
6. (Optional) Click **New Rule**to add a new rule.
7. Save the rule set to complete the process in one of the following ways:
  - Click **Save** to save the rule. Rules evaluate against each new incident created in the system.
  - From the **Save** drop-down menu, click **Save & Run** to save the rule and immediately run the rules against all existing incidents in Agentic SOC.
8. Click **Done**.

## Managing Incident Workflow Rule Sets

When managing incident workflow rule sets, you can perform the following actions:

- Delete a Rule
- Edit a Rule
- View Audit Logs

To delete a rule:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, go to **Settings** > **Incident Lifecycle**.
3. Click **Incident Workflows**. The **Incident Workflows** page appears.
4. Delete a rule by performing one of the following actions:
  - To delete a single rule:
    1. In the **Conditions** section, click the rule you want to delete. The **Auto-Dispatch to External System** drawer opens.
    2. In the **Auto-Dispatch to External System** drawer on the rule you want to delete, click **Delete Rule**. See image. The **Delete Rule** window appears. See image.
    3. Click **Delete**.
  - To delete all rules:
    1. In the **Auto-Dispatch to External Systems** category, click the **Delete All Rules** icon. See image. The **Delete All Rules** window appears. See image.
    2. Click **Delete**.

[Image: Delete All Rules confirmation window]

[Image: Delete All Rules icon]

[Image: Delete Rule button]

[Image: Delete Rule confirmation window]

To edit a rule:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, go to **Settings** > **Incident Lifecycle**.
3. Click **Incident Workflows**. The **Incident Workflows** page appears.
4. In the **Auto-Dispatch to External Systems** category, click the **Edit** icon. See image. The **Auto-Dispatch to External System** drawer opens.
5. In the **Auto-Dispatch to External Systems** drawer, make the necessary changes.
6. Save the rule to complete the process in one of the following ways:
  1. Click **Save** to save the rule. Rules apply to each new incident created in the system.
  2. From the **Save** drop-down menu, click **Save & Run** to save the rule and immediately run the rules against all existing incidents in Agentic SOC.

[Image: Edit icon]

To view incident workflow audit logs:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, go to **Settings** > **Incident Lifecycle**.
3. Click **Incident Workflows**. The **Incident Workflows** page appears.
4. On the **Incident Workflows** page, in the **Auto-Dispatch to External Systems** category, click the **Edit**icon. See image. The **Auto-Dispatch to External System** drawer opens.
5. On the **Incident Workflows** page, in the **Auto-Dispatch to External Systems** drawer, click **More** > **Audit Logs**. The rule's **Audit Logs** page appears.
6. On the **Audit Logs** page, click to expand a log, and drill down into detailed information about the changes made. See image.

[Image: Audit Logs page for incident workflows]

[Image: Edit icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/configuring-severity-categories","lastmod":"2026-10-02T12:49Z","nid":"1541100"} -->
## Configuring Severity Categories

- Source: https://help.zscaler.com/agentic-soc/configuring-severity-categories
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Configuring Severity Categories
- Last modified: 2026-10-02T12:49Z
- Summary: How to assign severity score to severity categories for findings in Zscaler Agentic SOC.

Severity levels are categorical values assigned to findings based on their severity score. In Agentic SOC, you can customize severity categories by mapping severity score ranges to those categories. The configured severity categories can then be used in dashboards and charts throughout the Agentic SecOps Platform Admin Portal. Proper severity settings ensure that remediation teams can efficiently focus on high-risk vulnerabilities while aligning their processes with organizational or compliance requirements.

The scores mapped to severity categories are configured on the Severity Settings page. These numerical values are derived from external sources and organizational risk factors. Each finding is assigned a score, which determines its corresponding severity category based on the configuration in Severity Settings. To learn more, see [Understanding Severity Scores](https://help.zscaler.com/agentic-soc/understanding-severity-scores).

To configure severity categories:

1. [Access the Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal).
2. From the top navigation, click **Agentic SOC**.
3. In the left-side navigation, go to **Settings** > **Severity**. The **Severity Settings**page opens.
4. For each severity category (i.e., **Critical**, **High**, **Medium**, **Low**, **Info**): See image.
  1. **Score starts at**: Enter the minimum score where the severity category starts. The minimum score is included in the category.
  2. **Score is less than**: Enter the maximum score as an optional cutoff. Every score must be included in a severity category to ensure complete coverage. The settings cannot be saved if there are gaps between ranges that are not assigned to a category. Additionally, severity ranges must be unique (non-overlapping) and ordered in ascending numerical value.
5. Use the **Severity Range Preview**slider to review your score mapping visually and ensure the entire score range (i.e., 0 to 100) is fully accounted for.
6. Save the settings in one of the following ways: See image.
  - Click **Save**. The severity settings apply the next time the finding or the ticket entity is processed.
  - In the **Save**drop-down menu, select **Save & Run** to save the settings and immediately apply them to your account.

[Image: Mapping Score Ranges to Severity Categories in Agentic SOC]

[Image: Severity Settings Save Drop-down Menu in Agentic SOC]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/creating-custom-incident","lastmod":"2026-10-02T12:51Z","nid":"1540861"} -->
## Creating a Custom Incident

- Source: https://help.zscaler.com/agentic-soc/creating-custom-incident
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Creating a Custom Incident
- Last modified: 2026-10-02T12:51Z
- Summary: Information on how to create a custom incident in Zscaler Agentic SOC.

Although one of Agentic SOC’s biggest benefits is its ability to use multiple AI agents to ingest vast amounts of alert data to create specific and actionable incidents, you might also want to create custom incidents based on your organization’s business needs. You can create a custom incident based on one or more alerts from the collated list available on the [Alerts page](https://help.zscaler.com/agentic-soc/about-alerts) in Agentic SOC.

To create a custom incident:

1. [Access the Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal).
2. From the top navigation, click **Agentic SOC**.
3. In the left-side navigation, go to **Alerts**. The **Alerts** page opens.
4. Select one or more alerts from the list, then click **Create Incident**. The **Create Incident from Alerts** drawer opens. See image.
5. In the **Create Incident from Alerts** drawer:
  - **Severity**: Select a severity level (e.g., **High**, **Medium**, **Low**) from the drop-down menu.
  - **Status**: Select a status (e.g., **Discovered**, **Remediated**, **Confirmed**) from the drop-down menu.
  - **Title**: Enter a title for the incident, or use the title automatically generated by Agentic SOC.
  - **Verdict**: Select a verdict (e.g., **Accepted**, **Executed**, **Partial**) from the drop-down menu.
6. Click **Create Incident**. See image. You receive a confirmation message and the incident appears on the [Incidents page](https://help.zscaler.com/agentic-soc/about-incidents).

[Image: The Create Incident from Alerts window in the Agentic SOC Portal]

[Image: Completed fields in the Create Incident from Alerts window in the Agentic SOC Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/customizing-alert-and-incident-detail-pages","lastmod":"2026-10-02T12:50Z","nid":"1534217"} -->
## Customizing Alert and Incident Detail Pages

- Source: https://help.zscaler.com/agentic-soc/customizing-alert-and-incident-detail-pages
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Customizing Alert and Incident Detail Pages
- Last modified: 2026-10-02T12:50Z
- Summary: How to customize the incident and alert detail pages in Zscaler Agentic SOC.

To help make Agentic SOC incident and alert data easier to parse, you can customize the detail pages you see when you click an incident on the [Incidents page](https://help.zscaler.com/agentic-soc/about-incidents) or an alert on the [Alerts page](https://help.zscaler.com/agentic-soc/about-alerts).

See image.

Agentic SOC provides a default view with important alert and incident details, but you can customize the default configuration or create new configurations. The tiles on the page (e.g., Details, Entities) appear as tabs on the detail pages for both incidents and alerts.

See image.

The following steps explain how to customize the Alert Detail page, but the process is the same for customizing both alert details and incident details.

To customize the **Incident Detail** page:

1. [Access the Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal).
2. From the top navigation, click **Agentic SOC**.
3. In the left-side navigation, go to **Settings** > **UI Config**. The **Alert UI Configuration** page opens. To switch to the **Incident UI Configuration** page, select **Incident**from the **Entity** drop-down menu.

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

[Image: Incident and alert detail pages in the Agentic SOC Portal]

[Image: The default incident detail page layout example in the Agentic SOC Portal]

[Image: The Entity drop-down menu on the incident detail page in the Agentic SOC Portal]

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

[Image: Adding a tab on the Alert UI Configuration page in the Agentic SOC Portal]

[Image: Adding a field on the Alert UI Configuration page in the Agentic SOC Portal]

[Image: Adding a table on the Alert UI Configuration page in the Agentic SOC Portal]

[Image: Editing a field on the Alert UI Configuration page in the Agentic SOC Portal]

[Image: Editing a table on the Alert UI Configuration page in the Agentic SOC Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/customizing-incident-and-alert-statuses","lastmod":"2026-10-02T12:49Z","nid":"1541098"} -->
## Customizing Incident and Alert Statuses

- Source: https://help.zscaler.com/agentic-soc/customizing-incident-and-alert-statuses
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Customizing Incident and Alert Statuses
- Last modified: 2026-10-02T12:49Z
- Summary: How to customize incident and alert statuses in Zscaler Agentic SOC

To help support your Security Operations Center (SOC) team's internal workflows, Agentic SOC allows you to customize the statuses that are available for [incidents](https://help.zscaler.com/agentic-soc/about-incidents) and [alerts](https://help.zscaler.com/agentic-soc/about-alerts). Agentic SOC provides a default set of statuses for incidents and alerts, but you can configure existing statuses (e.g., reorder and rename) and create new statuses. Statuses appear in various incident and alert views in the Agentic SecOps Platform Admin Portal.

See image.

The following steps explain how to customize alert statuses, but the process is the same for customizing incident statuses.

To customize alert statuses:

1. [Access the Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal).
2. From the top navigation, click **Agentic SOC**.
3. In the left-side navigation, go to **Settings** > **Statuses**. The **Statuses** page opens. Select **Alert** from the **Entity Type** drop-down menu.

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

[Image: Alert status configuration and the alert detail page in the Agentic SOC Portal]

[Image: The Entity Type drop-down menu on the Statuses page in the Agentic SOC Portal]

[Image: Saving a new status on the Statuses page in the Agentic SOC Portal]

[Image: New alert status on the Statuses page in the Agentic SOC Portal]

[Image: Adding a new bucket on the Statuses page in the Agentic SOC Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/release-upgrade-summary-2026","lastmod":"2026-10-10T07:06Z","nid":"1534331"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/agentic-soc/release-upgrade-summary-2026
- Product: Agentic SOC
- Path: Agentic SOC Help > 	Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-10-10T07:06Z
- Summary: Agentic SOC Release Upgrade Summary for service updates deployed in 2026.

This article provides a summary of all new features and enhancements for Agentic SOC.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/step-by-step-config-guide-agentic-soc","lastmod":"2026-10-13T15:58Z","nid":"1534195"} -->
## Step-by-Step Configuration Guide for Agentic SOC

- Source: https://help.zscaler.com/agentic-soc/step-by-step-config-guide-agentic-soc
- Product: Agentic SOC
- Path: Agentic SOC Help > Step-by-Step Configuration Guide for Agentic SOC
- Last modified: 2026-10-13T15:58Z
- Summary: How to use Zscaler Agentic SOC to track and prevent data breaches in your organization.

This guide outlines key steps for getting started with Zscaler Agentic SOC, the core threat operations layer of the Agentic Security Operations Platform. Agentic SOC transforms fragmented alerts into prioritized threat stories by unifying zero trust telemetry from Zscaler controls and third-party applications with business context, asset records, and user identity. Powered by AI agents and structured around the MITRE ATT&CK framework, it accelerates threat triage, investigation, and response across your organization.

Before using Agentic SOC, we recommend reviewing the following resources:

- [About Data Loss Prevention](https://help.zscaler.com/zia/about-data-loss-prevention)
- [MITRE ATT&CK Overview](https://attack.mitre.org/)
- [Understanding the Zscaler Cloud Architecture for Internet & SaaS](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture)
- [Understanding the Zscaler Deception Architecture](https://help.zscaler.com/deception/understanding-zscaler-deception-architecture)
- [What is Zscaler Internet & SaaS?](https://help.zscaler.com/zia/what-internet-saas)
- [What is Zscaler Deception?](https://help.zscaler.com/deception/understanding-zscaler-deception-architecture)

## Configuring Agentic SOC

To configure Agentic SOC, complete the following steps:

- Step 1: Complete Prerequisite Tasks
- Step 2: Assess the Security Threats to Your Organization
- Step 3: Use Agentic SOC Dashboards and Reports to Further Analyze Data
- Step 4: Customize the Zscaler Agentic Security Operations Platform

1. **License Agentic SOC**: Agentic SOC is a standalone product that is licensed separately from other parts of the Zscaler ecosystem. To learn more about provisioning Agentic SOC for your organization, contact your Zscaler Account team.
2. **Configure Internet & SaaS (ZIA)**: To ensure that Internet & SaaS logs are available for Agentic SOC, Internet & SaaS must be fully configured for your organization. If your organization already uses Internet & SaaS, no extra configuration is required for Agentic SOC to access Internet & SaaS logs. To learn more, see [Step-by-Step Configuration Guide for Internet & SaaS](https://help.zscaler.com/zia/step-step-configuration-guide-internet-saas).
3. **Configure Deception**: To ensure that Deception logs are available for Agentic SOC, Deception must be fully configured for your organization. If your organization already uses Deception, no extra configuration is required for Agentic SOC to access Deception logs. To learn more, see [Step-by-Step Configuration Guide for Zscaler Deception](https://help.zscaler.com/deception/v9.0/step-step-configuration-guide-zscaler-deception).
4. **Integrate with third-party applications**: Agentic SOC also ingests log data from third-party sources like CrowdStrike and Gmail. To learn more, see [Data Sources & Outegrations Integration Guides](https://help.zscaler.com/uvm/data-sources-outegration-configuration-guides).

Agentic SOC uses easy-to-understand charts, tables, and graphs to give you visibility into vast amounts of threat data across your organization. As you navigate the Agentic SecOps Platform Admin Portal, you can use the interconnected data points to easily switch from macro to micro views of the data (e.g., clicking incident information on the Agentic SOC dashboard opens the list of incidents associated with the verdict or criticality level). Throughout the admin portal, you can drill down to get granular detail, including AI summaries, entity information, threat data, etc. You can use the following basic workflow to assess your threat risk:

- a. Evaluate your overall threat landscape on the Agentic SOC dashboard.
- b. Examine the incidents affecting your organization.
- c. Look at the alerts associated with incidents.
- d. Review assets associated with incidents.
- e. Use response playbooks to respond to incidents.

After reviewing the threat data available in Agentic SOC, use the dashboards and reports that best fit your analysis needs:

- **Measure agent activity and impact**: On the Agents page, review metrics such as alerts investigated, time saved, and resolution rates to understand agent performance and demonstrate its impact to stakeholders. To learn more, see [About Agents](https://help.zscaler.com/agentic-soc/about-agents).
- **Create custom dashboards and reports**: If the default dashboards and reports don’t provide the views you need, set up custom dashboards and reports to analyze threat data across your organization. To learn more, see [About Dashboards](https://help.zscaler.com/unified/about-dashboards) and [About Reports](https://help.zscaler.com/unified/about-reports).

You can use the Agentic SecOps Platform Admin Portal to customize and manage the data in the platform, including data sources, integrations, and outbound integrations (i.e., outegrations). To learn more, see [Data Sources, Outegrations, & Data Management](https://help.zscaler.com/unified/getting-started-security-operations-platform/data-sources-outegrations-data-management).

On the [Agentic SOC dashboard](https://help.zscaler.com/agentic-soc/about-agentic-soc), you can see an overview of the alerts in your environment as well as how many alerts have become incidents. Additionally, you can see high-level triage information about each incident, including its verdict (e.g., Malicious, Benign) and criticality (e.g., Critical, Low). The Agentic SOC dashboard is the starting point each time you access the Agentic SecOps Platform Admin Portal.

On the [Incidents](https://help.zscaler.com/agentic-soc/about-incidents) page, you can see an overview of risk metrics across incidents, incidents categorized by assignees, and a list of specific incidents. Clicking an incident lets you drill down to see more granular data, including an AI-based summary that explains what's happening, affected users and assets, specific recommended actions, and additional details about how the incident is affecting your organization. The Incidents page lets you focus on real threats instead of being distracted by alert overload.

The [Alerts](https://help.zscaler.com/agentic-soc/about-alerts) page brings all alerts from all source applications into a single place. When you click an alert, you can see its source, how often it has occurred, its MITRE ATT&CK tactic and technique, the entities associated with it, and its risk score. The Alerts page is designed to help your SOC team make sense of all the alerts coming from different sources across your organization.

The [Assets](https://help.zscaler.com/agentic-soc/about-assets-agentic-soc) page provides a centralized view of assets in your environment, with each asset unified and enriched using information from multiple sources. Review an asset’s risk score, ownership, findings, source records, and related tickets to understand its context and potential impact.

When viewing an incident or alert, you can run a [playbook](https://help.zscaler.com/unified/about-playbooks) manually or configure an automation rule to trigger it automatically. A playbook groups one or more response actions to help standardize incident response, automate repetitive tasks, and reduce manual effort and response time. Depending on your configured integrations, actions might include blocking an IP address, isolating a host, or resetting a password.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/taking-action-incidents","lastmod":"2026-10-30T10:05Z","nid":"1546684"} -->
## Taking Action on Incidents

- Source: https://help.zscaler.com/agentic-soc/taking-action-incidents
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Taking Action on Incidents
- Last modified: 2026-10-30T10:05Z
- Summary: How to take action on Agentic SOC incidents.

Within the MITRE ATT&CK framework, taking action on an incident is the key operational transition from investigation to resolution. After an agentic investigation or triage completes, security analysts and AI agents respond by setting investigation verdicts, updating the status, and configuring automated response actions.

## Setting the Verdict

The verdict reflects the final conclusion of an incident's investigation.

To view or set the verdict:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, click **Incidents**.
3. Click an incident. The **Incident Detail**page appears. The verdict is located in the top-left region of the page. See image.

Analysts and agents can set the following verdicts:

- **Benign**: The incident is confirmed to pose no security risk or was successfully blocked inline by existing controls (e.g., ZIA inline policy block).
- **False Positive**: The incident is determined to be a benign event or authorized activity incorrectly flagged as a security threat.
- **Inconclusive**: The incident is unable to be assigned a definitive conclusion automatically due to insufficient telemetry or data access constraints, requiring human analyst follow-up.
- **Malicious**: The incident is a confirmed security threat requiring active remediation or containment workflows.
- **Suspicious**: The incident is flagged with potential threat indicators requiring specialized human expertise to validate due to domain-specific complexity.
- **True Positive**: The incident is a verified security event where the flagged malicious or unauthorized activity accurately represents a real security risk.

## Updating the Status

The incident status is used to track updates to the investigation.

To update the status:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, click **Incidents**.
3. Click an incident. The **Incident Detail** page appears. The status is located in the top-right region of the page. See image.

Analysts and AI agents can set the following statuses:

- **Discovered**: The incident was first identified via a series of alerts before any investigation has begun.
- **Opened**: An analyst has begun actively investigating the incident.
- **Acknowledged**: The incident has been reviewed and confirmed to be a legitimate issue requiring further action.
- **Not Remediated**:
  - **Testing**: The activity was performed as part of explicit security testing, such as penetration testing.
  - **False Positive**: The incident was investigated and determined not to be a security threat.
  - **Authorized Activity**: The activity under investigation was necessary for a valid job function.
- **Confirmed**: The incident is closed and the assigned verdict is confirmed to be correct.
- **Closed**: The incident is closed without passing judgment on the assigned verdict.

## Manually Triggering a Playbook

Playbooks are typically executed when an incident requires remediation, such as when the verdict is set to **Malicious** or the status is updated to **Ready to Remediate**. To simplify this process, the Threat Response Recommendation Agent suggests relevant playbooks in the AI Response Actions section of the incident details view.

To manually trigger a playbook from a recommended response action, perform the following steps:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, click **Incidents**.
3. Click an incident. The **Incident Detail** page appears.
4. On the **Details** tab, locate the **AI Response Actions** section. See image.
5. Review the recommended playbook for the target entity or incident, taking note of recommendation labels such as **Success**, **Proposed**, or **Failed**.
6. Click**Select Playbook** next to the desired action.
7. If a playbook execution succeeds partially or fails across listed entities, click **View** to inspect the specific execution status for each individual entity associated with the recommended action.

To learn more about configuring and evaluating playbook execution results, see [Managing Playbooks](https://help.zscaler.com/unified/managing-playbooks).

[Image: Verdict drop-down list]

[Image: Status drop-down list]

[Image: AI Response Actions section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/understanding-severity-scores","lastmod":"2026-09-17T11:46Z","nid":"1541099"} -->
## Understanding Severity Scores

- Source: https://help.zscaler.com/agentic-soc/understanding-severity-scores
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Understanding Severity Scores
- Last modified: 2026-09-17T11:46Z
- Summary: Information on how Agentic SOC severity scores are calculated.

In alert management, multiple factors within categories (e.g., the product that produced an alert, whether a user is an admin, etc.) are given severity scores that are used to prioritize remediation efforts. Individual severity scores typically range from 0 to 10, where a higher score indicates greater criticality requiring immediate attention and resolution, and thus takes precedence over vulnerabilities with lower scores. Agentic SOC then adds the total score from each factor to produce the overall severity score.

Standard severity scoring frameworks often fall short in addressing an organization's unique context. Agentic SOC calculates severity scores based on context and incorporates data from various sources and analyses, providing more effective and accurate risk assessment. To learn more, see [Configuring Alert Scores](https://help.zscaler.com/agentic-soc/configuring-alert-scores).

You can drill down into the details for an alert to see how Agentic SOC calculated the severity score.

See image.

[Image: The Alert Detail page with score calculation in the Agentic SOC Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/understanding-zscaler-agentic-soc","lastmod":"2026-10-01T13:30Z","nid":"1534207"} -->
## Understanding Zscaler Agentic SOC

- Source: https://help.zscaler.com/agentic-soc/understanding-zscaler-agentic-soc
- Product: Agentic SOC
- Path: Agentic SOC Help > Understanding Zscaler Agentic SOC
- Last modified: 2026-10-01T13:30Z
- Summary: A high-level explanation of how Zscaler Agentic SOC works.

Zscaler Agentic SOC is specifically built to eliminate gaps that traditional Security Operations Center (SOC) tools ignore. Powered by the Zscaler Data Fabric for Security, Agentic SOC transforms isolated alerts from across your security stack into a prioritized, holistic view of threats. Seamless integration with the Zero Trust Exchange (ZTE) platform provides additional threat context and initiates inline controls to automatically mitigate risk.

As part of the Agentic Security Operations Platform (Agentic SecOps Platform) portfolio, Agentic SOC also connects exposure insights with threat prioritization, ensuring that vulnerable assets and identities are factored into weighted results. It also enables the real-world threat activity that your SOC team sees to inform which vulnerabilities they should address first.

Agentic SOC revolutionizes security operations by enabling analysts to overcome alert fatigue and focus on the most critical issues. By consolidating and deduplicating alerts, enhancing them with rich context, and enabling smart prioritization of risks, Agentic SOC empowers your team to efficiently detect and respond to the threats that pose the highest risk to your organization.

At a high level, Agentic SOC provides three main value propositions:

- **It unifies all alerts**: Agentic SOC lets you track not only Zscaler alerts, but also alerts from third-party applications (e.g., Gmail, CrowdStrike) in a single console, unifying alerts from disparate sources with rich context. As alerts come in, Agentic SOC provides visibility across assets, identities, and exposures in a unified application. This enables your analysts to eliminate the multiple-system inefficiency that comes from receiving countless alerts from multiple tools.
- **It focuses on threats, not alerts**: Agentic SOC's AI-driven threat analysis transforms raw alerts into contextualized, actionable threats. Agentic SOC focuses on providing the right business context, understanding the threats associated with alerts, and leveraging historical attack patterns to identify the first 5 things you must address in your environment.
- **It helps stop the biggest threats in your environment**: With the ability to accelerate investigations by providing all threat details on a single page, as well as the ability to search logs from one unified location, understand the asset impact on the same page, streamline investigations, and enable faster, better, and proportional responses, Agentic SOC was designed from the ground up to take pressure off of your SOC teams and your organization as a whole.

See image.

More specifically, Agentic SOC simplifies day-to-day activities for your SOC teams by:

- Ingesting Vast Amounts of Data
- Providing Enriched Alerts
- Correlating Alerts
- Prioritizing Actionable Threats
- Facilitating Investigation
- Driving Resolution

To learn more about how to navigate Agentic SOC, see [Step-by-Step Configuration Guide for Agentic SOC](https://help.zscaler.com/agentic-soc/step-by-step-config-guide-agentic-soc).

Agentic SOC pulls in first-party data from Zscaler products and integrates seamlessly with third-party solutions.

Each alert is enhanced with contextual metadata (like identity, asset, location, and threat intelligence) at the fabric layer.

Alerts are analyzed and intelligently grouped into incidents, representing the full attack story rather than isolated data points.

Risk-based scoring identifies high-priority incidents requiring immediate action, making it easy to focus on critical threats.

You can explore correlated incidents interactively, pivot through entities, and collaborate with team members to get to the root cause.

With specific recommended actions from the Agentic SOC Response Agent, you can remediate risks proactively and efficiently. For example, suppose there were multiple failed login attempts by a user, followed by successful login activity from an unusual geographic location, privilege escalation, and access to sensitive files. Agentic SOC correlates these activities into a single incident, assigns the incident a risk score, and clearly outlines a recommended response, enabling you to pivot into action within minutes.

[Image: An illustration of the main Agentic SOC value proposition]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/viewing-incident-details","lastmod":"2026-10-08T12:08Z","nid":"1540782"} -->
## Viewing Incident Details

- Source: https://help.zscaler.com/agentic-soc/viewing-incident-details
- Product: Agentic SOC
- Path: Agentic SOC Help > Administration > Incidents & Alerts > Viewing Incident Details
- Last modified: 2026-10-08T12:08Z
- Summary: Information on how to view incident details in Zscaler Agentic SOC.

The Incidents page is your starting point for investigating and remediating threats. From this page, you can review a list of all incidents and select one to see a detailed breakdown.

On an incident's detail page, you can:

- Review the current verdict and findings and get suggestions for next steps.
- See which assets and users are impacted.
- Visualize the threat's progression chronologically and across the [MITRE ATT&CK matrix](https://attack.mitre.org/tactics/enterprise/).
- Examine individual investigative leads and review how they confirm or eliminate potential threat paths.
- Coordinate with other analysts by adding comments.

To view incident details:

1. In the [Agentic SecOps Platform Admin Portal](https://help.zscaler.com/unified/signing-security-operations-platform-admin-portal#navigating-secops-platform), click **Agentic SOC**.
2. In the left-side navigation, click **Incidents**.
3. On the **Incidents**page, click an incident in the table. See image.

On the incident's detail page, you can view the following tabs:

1. **Details**: Summarizes the activity included in the incident, including the current status of the investigation, any verdict reached so far, and AI-recommended response actions to remediate the incident (if applicable).
2. **Affected Entities**: Lists the assets and users involved in the incident.
3. **Timeline**: Displays the chronological order of events included in the incident.
4. **Attack Path**: Overlays events on the [MITRE ATT&CK matrix](https://attack.mitre.org/tactics/enterprise/), helping you visualize how an adversary might be progressing through an attack chain.
5. **Analysis**: Summarizes the investigative leads explored by the Investigation Agent.
6. **Alerts**: Shows which alerts and sources Agentic SOC's Enrichment and Correlation Agents used to create the incident.
7. **Activity**:Shows an audit log of all activities related to the incident (e.g., user action, sync from third-party systems).
8. **Comments:** Shows all comments added by analysts.

[Image: Tabs on the incident's details page]

[Image: Select an incident from the Incidents table to see more details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/agentic-soc/what-zscaler-agentic-soc","lastmod":"2026-10-01T13:32Z","nid":"1534206"} -->
## What Is Zscaler Agentic SOC?

- Source: https://help.zscaler.com/agentic-soc/what-zscaler-agentic-soc
- Product: Agentic SOC
- Path: Agentic SOC Help > What Is Zscaler Agentic SOC?
- Last modified: 2026-10-01T13:32Z
- Summary: How Zscaler Agentic SOC works and fits in with other products in the Zscaler ecosystem.

Security Operations Center (SOC) teams face a daily battle, wading through endless alerts coming from disparate tools—networks, endpoints, cloud environments, email security applications, identity platforms, and more. Bringing together all of these isolated alerts to understand the full picture of an attack is challenging, tedious, and often error prone. As a result, SOC analysts are overwhelmed with alert fatigue, wasting precious time chasing false positives instead of remediating real threats.

See image.

Agentic SOC uses a multi-agentic AI approach to provide rich, context-driven data that helps your SOC teams achieve faster, more efficient, and more confident threat detection and response. Agentic SOC is a unified platform to prioritize and triage threats so that you can rapidly resolve business-critical incidents. Unlike other tools that simply provide a list of incidents, Agentic SOC provides risk-based prioritization that focuses not only on a threat but also its business context. Additionally, Agentic SOC uses advanced AI-driven capabilities to seamlessly narrow gaps between proactive threat detection and reactive incident response.

You can use Agentic SOC to transform security operations and help your analysts cut through alert fatigue to stay laser-focused on what matters most. By unifying relevant alerts, enriching alerts with deep context, and powering smart prioritization of risks, Agentic SOC enables you to quickly and accurately identify and contain the threats that pose the greatest risk to your business.

## Key Benefits

Agentic SOC protects your organization by providing the following key features and benefits:

- **Risk-Based Threat Prioritization**: Agentic SOC consolidates business intelligence and threat context to help you understand all critical threats in your environment.
- **Automated Correlation**: Agentic SOC ties multiple alerts into threats by correlating and aggregating entities to drive historical attack patterns and contextual data.
- **Built-In Context Enrichment**: Agentic SOC provides deeper insights into each alert with information such as geographic locations, user roles, asset criticality, vulnerability data, and threat intelligence.
- **Efficiency Gains with Actionable Incidents**: Agentic SOC focuses analyst time on business-critical incidents, reducing noise around alerts to drive quicker remediation.
- **Proactive Attack Prediction**: Agentic SOC surfaces attack paths with related Indicators of Compromise and possible attack progressions, allowing analysts to stop threats before they lead to a data breach.
- **Specific Response Recommendations**: Agentic SOC's AI Response Agent takes triaged incidents and provides targeted remediation recommendations that save your analysts countless hours of manual work.

In short, Agentic SOC is designed to give you comprehensive visibility across your security landscape. Using an intelligence-first, incident-driven workflow to automate context, Agentic SOC takes pressure off of your SOC teams and your organization as a whole by enabling your analysts to rapidly identify and remediate threats.

See image.

To learn more, see [Understanding Zscaler Agentic SOC](https://help.zscaler.com/agentic-soc/understanding-zscaler-agentic-soc).

[Image: An illustration of the biggest SOC team struggles]

[Image: An illustration of the main benefits that Agentic SOC provides]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-agent-connection-status-logs","lastmod":"2026-11-01T23:33Z","nid":"1498236"} -->
## About Agent Connection Status Logs

- Source: https://help.zscaler.com/microsegmentation/about-agent-connection-status-logs
- Product: Microsegmentation
- Path: Microsegmentation Help > Analytics > About Agent Connection Status Logs
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the Agent Connection Status Logs page for Microsegmentation in the Zscaler Admin Console.

The Agent Connection Status Logs page provides agent connection logs to the Zscaler cloud. The logs displayed on this page can help you troubleshoot connection issues.

The Agent Connection Status Logs page provides the following benefits and enables you to:

- View a summary of agent connection data in your organization.
- View, monitor, and analyze agent connection data from a single workload.
- Troubleshoot agent connection issues.
- Debug the count of logs occurring.
- Discover insights about different types of agent connection errors.
- Take appropriate actions based on the agent connection data displayed.

## About the Agent Connection Status Logs Page

On the Agent Connection Status Logs page (Data Explorer > Microsegmentation > Agent Connection Status Log), you can do the following:

1. Filter the time range up to the last 14 days.
2. Refresh the Agent Connection Status Logs page to reflect the most current information.
3. Show or hide available columns.
4. Show or hide available filters.
5. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. See image.
6. View a list of all agent connection status logs that are configured for your organization. For each log, you can see:
  - **Date**: The date the log occurred.
  - **Status**: The authentication status of the log.
  - **Authentication/Disconnect Time**: The time when the agent connection was authenticated or disconnected.
  - **Agent Name**: The name of the agent.
  - **Agent Controller Name**: The name of the agent controller.
  - **Agent Public IP**: The IP address for the agent.
7. Expand an individual status log to view its complete information. See image.

[Image: The Agent Connection Status Logs]

[Image: Apply configured filters button]

[Image: Detailed information of an agent connection status log]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-agent-groups","lastmod":"2026-11-01T23:34Z","nid":"1531955"} -->
## About Agent Groups

- Source: https://help.zscaler.com/microsegmentation/about-agent-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Groups > About Agent Groups
- Last modified: 2026-11-01T23:34Z
- Summary: Information about the Agent Groups page for Microsegmentation in the Zscaler Admin Console.

The Agent Groups page gives insight into your organization's agent group data. It allows you to monitor and analyze the data for all configured agent groups in your organization.

The Agent Groups page provides the following benefits and enables you to:

- View a summary of agent group data in your organization.
- View, monitor, and analyze agent group data for a single workload.
- Take appropriate actions based on the agent group data displayed.
- Discover insights about collective agent group data.

## About the Agent Groups Page

On the Agent Groups page (Zero Trust Cloud > Microsegmentation > Agent Groups), you can do the following:

1. Refresh the Agent Groups page to reflect the most current information.
2. [Add an agent group](https://help.zscaler.com/zpa/configuring-agent-groups).
3. Show or hide available columns.
4. Show or hide available filters.
5. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. See image.
6. View a list of all agent groups that are configured for your organization. For each agent group, you can see:
  - **Name**: The name of the agent group.
  - **Admin Status**: The admin status of the agent group. If disabled, the agents within the agent group receive configuration updates from the backend but do not send any logs. Additionally, if the admin status is disabled, policies are not enforced.
  - **Policy Status**: The policy status of the agent group. If enabled, you can use the agent group in policy configuration.
  - **Type**: The type of agent group: **VM** or **Kubernetes Cluster**.
  - **Count**: The number of agents in the agent group.
  - **Platform**: The platform of the agent group (i.e., cloud or on-premises).
  - **Upgrade Status**: The upgrade status of the agent group. Depending on the status, you can halt an upgrade that is in progress, initiate an on-demand upgrade, or view details about a failed upgrade.
7. Click an individual agent group to view its complete information, including its agents and provisioning keys. See image.
8. [Edit an agent group](https://help.zscaler.com/zpa/editing-agent-groups).
9. View instructions to download and install the agent manager for the VM agent group.
10. View options to [manage](https://help.zscaler.com/zpa/managing-agent-groups) and [delete the VM agent group](https://help.zscaler.com/zpa/deleting-agent-groups).
11. View instructions to download and install the Helm chart for the Kubernetes Cluster agent group.
12. [Delete the Kubernetes Cluster agent group](https://help.zscaler.com/zpa/deleting-agent-groups).
13. Display more rows or a different page of the table.

[Image: The Agent Groups page]

[Image: Button to apply configured filters]

[Image: Details of an agent group]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-agent-provisioning-keys","lastmod":"2026-11-01T23:34Z","nid":"1531959"} -->
## About Agent Provisioning Keys

- Source: https://help.zscaler.com/microsegmentation/about-agent-provisioning-keys
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Provisioning Keys > About Agent Provisioning Keys
- Last modified: 2026-11-01T23:34Z
- Summary: Information about provisioning keys for Microsegmentation in the Zscaler Admin Console.

The Provisioning Keys page gives insight into your organization's agent provisioning key data. It allows you to monitor and analyze the data for all configured agent provisioning keys in your organization.

The Provisioning Keys page provides the following benefits and enables you to:

- View a summary of agent provisioning keys data in your organization.
- View, monitor, and analyze agent provisioning keys data for a single workload.
- Take appropriate actions based on the agent provisioning keys data displayed.
- Discover insights about collective agent provisioning keys data.

## About the Provisioning Keys Page

On the Provisioning Keys page (Zero Trust Cloud > Microsegmentation > Agent Groups), you can do the following by selecting an agent group and clicking Provisioning Keys:

1. Refresh the page to reflect the most current information.
2. Add an agent provisioning key. To learn more, see [Configuring Agent Groups](https://help.zscaler.com/zpa/configuring-agent-groups). See image.
3. Show or hide the available columns.
4. Show or hide the available filters.
5. Filter the information by **Name**. After configuring the filter, click the button to apply the filter (e.g., Apply 1 filter) and view the results. See image.
6. View a list of all agent provisioning keys that are configured for your agent group. For each agent provisioning key, you can see:
  - **Name**: The name of the provisioning key.
  - **Maximum no. of Agents**: The maximum number of agents that can use the provisioning key.
  - **Provisioning Key Utilization Count**: The number of times the provisioning key has been used.
  - **Agent Group**: The agent group associated with the provisioning key.
  - **Signing Certificate**: The name of the signing certificate.
  - **Provisioning Key**: The provisioning key that you can copy.
7. View the signing certificate for the agent provisioning key. See image.
8. Copy an agent provisioning key.
9. [Edit an agent provisioning key](https://help.zscaler.com/zpa/editing-agent-provisioning-keys).
10. [Delete an agent provisioning key](https://help.zscaler.com/zpa/deleting-agent-provisioning-keys).
11. Display more rows or a different page of the table.
12. [Manage the agent group](https://help.zscaler.com/zpa/managing-agent-groups).
13. View details about the agent group.
14. View more details about the agent group. See image.
15. View the agents in the agent group.
16. View instructions for downloading and installing the agent manager for VM agent groups or the Helm chart for Kubernetes Cluster agent groups.

[Image: A view of the provisioning keys for an agent group]

[Image: Add provisioning key]

[Image: Button to apply the configured filter]

[Image: A view of the certificate details.]

[Image: Agent group details drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-agent-telemetry","lastmod":"2026-11-01T23:33Z","nid":"1531940"} -->
## About Agent Telemetry

- Source: https://help.zscaler.com/microsegmentation/about-agent-telemetry
- Product: Microsegmentation
- Path: Microsegmentation Help > Analytics > About Agent Telemetry
- Last modified: 2026-11-01T23:33Z
- Summary: Information on the Agent Telemetry page for Microsegmentation in the Zscaler Admin Console.

The Agent Telemetry page gives insight into your organization's agent data. It allows you to monitor and analyze the agent data for all installed agents in your organization.

The Agent Telemetry page provides the following benefits and enables you to:

- View a summary of agent data in your organization.
- View, monitor, and analyze agent data from a single workload.
- Take appropriate actions based on the data displayed on the page about the agents.
- Discover insights about any agent errors.

## About the Agent Telemetry Page

On the Agent Telemetry page (Data Explorer > Microsegmentation > Agent Telemetry), you can do the following:

1. Filter the time range up to the last 14 days.
2. Refresh the Agent Telemetry page to reflect the most current information.
3. Show or hide available columns.
4. Show or hide available filters.
5. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. See image.
6. View a list of all agent data for your organization. For each agent, you can see:
  - **Timestamp**: The date and time of the agent's last usage.
  - **Agent CPU Util (%)**: The percentage of CPU in megabytes that the agent used.
  - **Agent Memory Util (MB)** The total memory in megabytes that the agent used.
  - **Agent ID**: The agent ID number.
7. Expand an individual agent to view its complete information. See image.

[Image: The Agent Telemetry page]

[Image: Apply configured filters button]

[Image: Agent telemetry detail]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-agents","lastmod":"2026-11-01T23:33Z","nid":"1531950"} -->
## About Agents

- Source: https://help.zscaler.com/microsegmentation/about-agents
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agents > About Agents
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the Agents page for Microsegmentation in the Zscaler Admin Console.

The Agents page gives insight into your organization's agent data. It allows you to monitor and analyze the data for all configured agents in your organization.

The Agents page provides the following benefits and enables you to:

- View a summary of agent data in your organization.
- View, monitor, and analyze agent data for a single workload.
- Take appropriate actions based on the agent data displayed.
- Discover insights about collective agent data.

## About the Agents Page

On the Agents page (Zero Trust Cloud > Microsegmentation > Agents), you can do the following:

1. Refresh the Agents page to reflect the most current information.
2. Show or hide available columns.
3. Show or hide available filters.
4. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. By default, no filters are applied. See image.
5. View a list of all agents that are configured for your organization. For each agent, you can see:
  - **Name**: The name of the agent.
  - **Type**: The type of agent.
  - **Current Software Version**: The software version of the agent.
  - **Connection Status**: The connection status of the agent.
  - **Admin Status**: The admin status of the agent.
6. Click an individual agent to view its complete information. See image.
7. [Edit an agent.](https://help.zscaler.com/zpa/editing-agents)
8. Manually upgrade an agent.
9. [Delete an agent.](https://help.zscaler.com/zpa/deleting-agents)

[Image: A view of the Agents page and all its actions.]

[Image: Button to apply configured filters]

[Image: The Agents page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-application-catalog-microsegmentation","lastmod":"2026-11-01T23:34Z","nid":"1533998"} -->
## About the Application Catalog for Microsegmentation

- Source: https://help.zscaler.com/microsegmentation/about-application-catalog-microsegmentation
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > About the Application Catalog for Microsegmentation
- Last modified: 2026-11-01T23:34Z
- Summary: Information about applications found running on virtual machines (VMs) and how they are used to generate the machine-learning (ML) tags that you can apply to resource groups.

The Application Catalog gives admins insight into the applications used to generate the machine-learning (ML) tags that you can apply to resource groups.

The Application Catalog page provides the following benefits and enables you to:

- Review the list of applications and application categories used by ML to recommend resource tags.
- Compare similar application information.
- Discover insights about collective application data.

## About the Application Catalog Page

On the Application Catalog page (Zero Trust Cloud > Microsegmentation > Application Catalog), you can do the following:

1. Refresh the Application Catalog page to reflect the most current information.
2. Show or hide available columns.
3. Show or hide available filters.
4. Filter the information by **Application Name**, **Application Category,** **Process**, **Protocol**, and **Port**. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. See image.
5. View a list of all applications found. For each application, you can see:
  - **Application Name**: The name of the application.
  - **Application Category**: The type of application.
  - **Process Name**: The name of the process file.
  - **Protocol**: The protocol type (UDP, TCP, etc.).
  - **Port Start**: The port starting number.
  - **Port End**: The port ending number.
6. Display more rows or a different page of the table.

[Image: A view of the Application Catalog page and its filters]

[Image: Button to apply configured filters]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-appzones","lastmod":"2026-11-01T23:34Z","nid":"1498246"} -->
## About AppZones

- Source: https://help.zscaler.com/microsegmentation/about-appzones
- Product: Microsegmentation
- Path: Microsegmentation Help > AppZone Management > About AppZones
- Last modified: 2026-11-01T23:34Z
- Summary: Information about the AppZone page for Microsegmentation in the Zscaler Admin Console.

AppZones in Microsegmentation are applications grouped together into `zones` based on the applications' topology and their underlying network connectivity with each other. Admins can use AppZones to segment different resources that use these applications to track usage. The AppZones page gives insight into your organization's complete list of AppZone data. It allows you to monitor and analyze the data for all configured AppZones in your organization.

The AppZones page provides the following benefits and enables you to:

- View a summary of AppZone data in your organization.
- View, monitor, and analyze AppZone data for a single workload.
- Take appropriate actions based on the AppZone data displayed.
- Discover insights about collective AppZone data.

## About the AppZones Page

On the AppZones page (Zero Trust Cloud > Microsegmentation > App Zones), you can do the following:

1. Filter the information by **Name**, **Description**, and **Member Count**. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. By default, no filters are applied. See image.
2. Refresh the AppZones page to reflect the most current information.
3. [Add an AppZone.](https://help.zscaler.com/zpa/configuring-appzones)
4. Show or hide available columns.
5. View a list of all AppZones that are configured for your organization. For each AppZone, you can see:
  - **Name**: The name of the AppZone.
  - **Member Count**: The number of resources connected to the AppZone.
  - **Description**: Contextual information about the AppZone.
6. Click an individual AppZone to view its complete information. See image.
7. [Edit an AppZone.](https://help.zscaler.com/zpa/editing-appzones)
8. [Delete an AppZone.](https://help.zscaler.com/zpa/deleting-appzones)
9. Display more rows or a different page of the table.

[Image: A view of the AppZone page and all its filters: Name, Description, and Member Count.]

[Image: Button to apply configured filters]

[Image: A view of an AppZone's complete information.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-event-logs","lastmod":"2026-11-01T23:33Z","nid":"1531941"} -->
## About Event Logs

- Source: https://help.zscaler.com/microsegmentation/about-event-logs
- Product: Microsegmentation
- Path: Microsegmentation Help > Analytics > About Event Logs
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the Event Logs page for Microsegmentation in the Zscaler Admin Console.

The Event Logs page provides logs of resource and agent diagnostics data. The logs displayed on this page can help you troubleshoot resource and agent issues depending on the priority.

The Event Logs page provides the following benefits and enables you to:

- View a summary of resource and agent data in your organization.
- View, monitor, and analyze agent diagnostics from a single workload.
- Troubleshoot resource and agent issues.
- Take appropriate actions based on the event log data displayed.

## About the Event Logs Page

On the Event Logs page (Data Explorer > Microsegmentation > Event Logs), you can do the following:

1. View the number of **Total**, **High Priority**, **Medium Priority**, and **Low Priority** event logs.
2. Filter the time range up to the last 14 days.
3. Refresh the Event Logs page to reflect the most current information.
4. Show or hide available columns.
5. Show or hide available filters.
6. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. See image.
7. View a list of all event logs that are configured for your organization. For each log, you can see:
  - **Timestamp**: The date and time the log occurred.
  - **Event Name**: The title of the log.
  - **Category**: The action type of the log.
  - **Component Name**: The area of Microsegmentation where the log came from.
  - **Priority**: The ranked importance of the log shown in Low, Medium, and High.
8. Expand an individual event log to view its complete information. See image.

[Image: The Event Logs page]

[Image: Apply configured filters button]

[Image: Event log detail]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-flow-log-aggregations","lastmod":"2026-11-01T23:33Z","nid":"1545453"} -->
## About Flow Log Aggregations

- Source: https://help.zscaler.com/microsegmentation/about-flow-log-aggregations
- Product: Microsegmentation
- Path: Microsegmentation Help > Analytics > About Flow Log Aggregations
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the Flow Log Aggregations page for Microsegmentation in the Zscaler Admin Console.

The Flow Log Aggregations page for Microsegmentation in the Zscaler Admin Console provides insight into your organization's aggregated network flows. You can view flows within a specific time period (up to the last 14 days) and filter the flows by AppZone and further by the source and destination resource groups within that AppZone.

The Flow Log Aggregations page provides the following benefits and enables you to:

- View a summary of network flows that have occurred in your organization.
- View, monitor, and analyze flows between resource groups.
- Evaluate the frequency of similar network flows.
- Take appropriate actions based on the data displayed on the page about the flows.

## About the Flow Log Aggregations Page

On the Flow Log Aggregations page (Data Explorer > Microsegmentation > Flow Log Aggregations), you can do the following:

1. Filter the flows by AppZone.
2. Filter the time range up to the last 14 days.
3. Refresh the page to reflect the most current information.
4. Show or hide available columns.
5. Show or hide available filters.
6. Filter the flows by **Source Resource Groups** and **Destination Resource Groups**. After configuring your preferred filters, click the button to apply the filters (e.g., **Apply 1 filter**, **Apply 2 filters**) and view the results. By default, no filters are applied. See image.
7. View a list of all flows that are configured for your organization. For each admin, you can see:
  - **Source**: The hostname or IP address of the source resource.
  - **Source Resource Groups**: The resource groups to which the source resource belongs.
  - **Destination**: The hostname or IP address of the destination resource.
  - **Destination Resource Groups**: The resource groups to which the destination resource belongs.
  - **Destination Port**: The destination port.
  - **Protocol**: The protocol, either TCP or UDP.
  - **Action**: The policy action applied to the traffic (e.g., Allow, Block, Sim Block).
  - **Policy**: The name of the policy.
  - **Count**: The number of flows observed.

[Image: The Flow Log Aggregations page]

[Image: Apply configured filters button]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-flow-logs","lastmod":"2026-11-01T23:33Z","nid":"1498211"} -->
## About Flow Logs

- Source: https://help.zscaler.com/microsegmentation/about-flow-logs
- Product: Microsegmentation
- Path: Microsegmentation Help > Analytics > About Flow Logs
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the Flow Logs page for Microsegmentation in the Zscaler Admin Console.

Flow Logs give insight into your organization's network and agent flows. They allow you to monitor and analyze the flow data at an organizational level.

The Flow Logs page provides the following benefits and enables you to:

- View a summary of network flows that have occurred in your organization.
- View, monitor, and analyze flows from a single location.
- Take appropriate actions based on the data displayed on the page about the flows.
- Discover insights about the processing of the flows.

## About the Flow Logs Page

On the Flow Logs page (Data Explorer > Microsegmentation > Flow Logs), you can do the following:

1. Filter the time range up to the last 14 days.
2. Refresh the Flow Logs page to reflect the most current information.
3. Show or hide available columns.
4. Show or hide available filters.
5. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. By default, no filters are applied. See image.
6. View a list of all flow logs that are configured for your organization. For each admin, you can see:
  - **Timestamp**: The date and time of the flow log.
  - **Source IP**: The IP address that initiated the flow log.
  - **Destination IP**: The IP address that received the flow log.
  - **Destination Port**: The port number that received the flow log.
  - **Protocol**: The type of communication of the flow log.
  - **Action**: The policy action: Allow, Block, or Sim Block.
  - **AppZone Name**: The name of the AppZone connected to the flow log.
  - **Agent ID**: The agent ID.
7. Expand a row in the table to see more information about a flow log. See image.
8. Copy the agent ID.

If two or more matching events occur within the flow log aggregation interval, the system combines these events into a single log entry and shows a count greater than one.

[Image: The Flow Logs page]

[Image: Apply configured filters button]

[Image: Detailed information for a flow in Microsegmentation]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-kubernetes-clusters-microsegmentation","lastmod":"2026-11-01T23:33Z","nid":"1538445"} -->
## About Kubernetes Clusters for Microsegmentation

- Source: https://help.zscaler.com/microsegmentation/about-kubernetes-clusters-microsegmentation
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > About Kubernetes Clusters for Microsegmentation
- Last modified: 2026-11-01T23:33Z
- Summary: How to access and understand Kubernetes Clusters for Microsegmentation.

You can use Kubernetes Clusters as an alternative option to virtual machines when configuring agent groups for Microsegmentation. It supports Google Kubernetes Engine (GKE), Azure Kubernetes Solution (AKS), and Amazon Elastic Kubernetes Service (Amazon EKS). To learn more, see [About Agent Groups](https://help.zscaler.com/zpa/about-agent-groups) and [Configuring Agent Groups](https://help.zscaler.com/zpa/configuring-agent-groups).

The Clusters Resources page provides the following benefits and enables you to:

- View a summary of cluster data in your organization.
- Take appropriate actions based on the cluster data displayed.
- Discover insights about collective cluster data.

## About the Cluster Resources Page

On the Cluster Resources page (Zero Trust Cloud > Microsegmentation > Resources K8s), you can do the following:

1. Refresh the Cluster Resources page to reflect the most current information.
2. Show or hide available columns.
3. Show or hide available filters.
4. Filter the information by **Name**, **AppZone**, **Status**, and **Resource ID**. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. By default, no filters are applied. See image.
5. View a list of all Kubernetes Clusters that are configured for your organization. For each resource group, you can see:
  - **Name**: The name of the cluster resource.
  - **AppZone**: The name of the AppZone it is attached to.
  - **Type**: The type of cluster resource it is (Workload, Node, or Service).
  - **Cloud**: The name of the cloud the cluster resource is on.
  - **Status**: The status of the cluster resource (Active or Inactive).
6. Click an individual cluster resource to view its complete information. See image.
7. Display more rows or a different page of the table.

[Image: A view of the Cluster Resources page and its filters]

[Image: Button to apply configured filters]

[Image: The information details for an individual cluster resource]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-microsegmentation-policies","lastmod":"2026-11-01T23:34Z","nid":"1531962"} -->
## About Microsegmentation Policies

- Source: https://help.zscaler.com/microsegmentation/about-microsegmentation-policies
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Resource Policies > About Microsegmentation Policies
- Last modified: 2026-11-01T23:34Z
- Summary: Information about Microsegmentation policies.

Admins can create Layer 3 and Layer 4 Microsegmentation enforcement policies to protect east-west traffic in both cloud and data center environments. By anchoring the policy with a [resource group](https://help.zscaler.com/zpa/about-resource-groups), a rule is built around that with other dynamic criteria to enforce either allowing or blocking certain traffic.

The Resource Policies page provides the following benefits and enables you to:

- View the list of configured policies for your organization.
- View policy hit status.
- Configure new policy rules.
- Filter policies based on rule data.

After you've configured Microsegmentation policies, they appear on the Resource Policies page. To learn more, see [Configuring Microsegmentation Policies](https://help.zscaler.com/zpa/configuring-microsegmentation-policies).

## About the Resource Policies Page

On the Resource Policies page (Zero Trust Cloud > Microsegmentation > Policy Rules), you can do the following:

1. View the [policy settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings) statuses.
2. Refresh the Resource Policies page to reflect the most current information.
3. [Add a policy rule](https://help.zscaler.com/zpa/configuring-microsegmentation-policies).
4. Show or hide available columns.
5. Show or hide available filters.
6. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. By default, no filters are applied. See image.
7. View a list of all policies that are configured for your organization. For each policy rule, you can see:
  - **Order**: The ranking of which policy rule should be applied first.
  - **Name**: The name of each policy rule.
  - **Source**: The source location for the policy rule.
  - **Destination**: The destination location for the policy rule.
  - **Applications**: The applications associated with the policy rule, if any.
  - **Protocol**: The protocols added to the policy rule.
  - **Scope**: The AppZone the policy rule should affect.
  - **Rule Action**: The command for the policy rule to apply.
  - **Last Hit**: The last time the policy rule was triggered.
8. Click a policy name to open a drawer of its details. See image.
9. [Edit a policy rule](https://help.zscaler.com/zpa/editing-microsegmentation-policy).
10. Reorder the policy rule.
11. Insert a policy rule in the rule order.
12. [Delete a policy rule](https://help.zscaler.com/zpa/deleting-microsegmentation-policy).

[Image: The Resource Policies page for Microsegmentation]

[Image: Button to apply configured filters]

[Image: The drawer with policy rule details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-ml-recommendations-resource-groups","lastmod":"2026-11-01T23:33Z","nid":"1531949"} -->
## About ML Recommendations for Resource Groups

- Source: https://help.zscaler.com/microsegmentation/about-ml-recommendations-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > About ML Recommendations for Resource Groups
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the ML Recommendations for Resource Groups page for Microsegmentation in the Zscaler Admin Console.

When [configuring resource groups](https://help.zscaler.com/zpa/configuring-resource-groups) for [Microsegmentation policies](https://help.zscaler.com/zpa/configuring-microsegmentation-policies), admins can eliminate manual time and effort spent on reviewing [resources](https://help.zscaler.com/zpa/about-resources). To help admins easily find resources with enough similarities to group together, admins can generate machine-learning (ML) recommendations to provide suggestions for configuration. To learn more, see [About Resources](https://help.zscaler.com/zpa/about-resources).

ML recommendations automatically find and generate suggestions for resource groups based on user-defined cloud tags, cloud environment metadata, and host data. Admins can review an ML recommendation and accept and approve it, edit and approve it, or ignore it for resource group configuration. Selecting a recommendation for approval means it is automatically configured as a resource group. Selecting a recommendation to be ignored means that its specific data won't be regenerated in a future recommendation.

Admins must first accept the terms agreement that appears on the ML Recommendations for Resource Groups page in order to begin seeing recommendations. To disable it, admins must contact Zscaler Support.

The ML Recommendations for Resource Groups page provides the following benefits and enables you to:

- View ML recommendations for configuring resource groups.
- Compare similar resource information.
- Discover insights about collective resource data.

## About the ML Recommendations for Resource Groups Page

On the ML Recommendations for Resource Groups page (Zero Trust Cloud > Microsegmentation > Resource Groups > ML Recommendations), you can do the following:

1. Show or hide the list of ignored ML recommendations.
2. Refresh the page to reflect the most current information.
3. Show or hide available columns.
4. View a list of all ML recommendations for your organization. For each recommendation, you can see:
  - **Name**: The name of the recommendation. Names are generated based on Dynamic Membership criteria, which you can view by clicking an individual recommendation.
  - **Resource Type**: The type of resource (**Managed** or **Unmanaged**).
  - **Member Count**: The number of resources in the resource group.
  - **Confidence Score**: The likelihood from 1 to 100 of how optimal the recommendation is for configuration. A score of 1 is considered the lowest confidence, and a score of 100 is considered the highest confidence.
5. Click an individual ML recommendation to view its complete information and accept or ignore it for configuration. The criteria in the **Dynamic Membership** section are generated based on the priority of tags automated by the Zscaler Microsegmentation (ZMS) engine. See image. By default, resource groups can include up to 10 different dynamic criteria tags. When more than 10 tags are available for the recommendation, they are ranked by priority, and the top 10 ranking tags are selected for the recommendation. Custom tags are the highest ranked, followed by cloud tags, ML tags, then network identifiers and environment fields, with agent group IDs as the lowest ranked.
6. Review a recommendation.
7. Ignore a recommendation. See image.
8. Display more rows or a different page of the table.
9. View the last time a recommendation test was run, and when the next run is scheduled for.
10. View instructions to have Zscaler Support enable or disable ML recommendations for your organization.
11. Go to the [User-Defined Resource Groups](https://help.zscaler.com/zpa/about-resource-groups) page.
12. Go to the [System Resource Groups](https://help.zscaler.com/zpa/about-system-resource-groups) page.

[Image: The ML Recommendations for Resource Groups page]

[Image: The details of an individual ML recommendation]

[Image: Confirm that you want to ignore a recommendation]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-ml-tag-recommendations","lastmod":"2026-11-01T23:34Z","nid":"1533997"} -->
## About ML Tag Recommendations 

- Source: https://help.zscaler.com/microsegmentation/about-ml-tag-recommendations
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > About ML Tag Recommendations 
- Last modified: 2026-11-01T23:34Z
- Summary: Information about the machine learning (ML) Tag Recommendations page.

The machine learning (ML) Tag Recommendations page shows which applications from the Applications Catalog have been detected running on managed resources in the environment. It allows you to review tag recommendations in depth, and accept or ignore them depending on if you want to keep the application tag information accessible for later configuration.

The ML Tag Recommendations page provides the following benefits and enables you to:

- View machine learning recommendations for matching application tags.
- Compare similar application information.
- Discover insights about collective application data.

## About the ML Tag Recommendations Page

On the ML Tag Recommendations page (Zero Trust Cloud > Microsegmentation > Tag Recommendations), you can do the following:

1. If applicable, view a message regarding the availability of flow data for tag recommendations.
2. Show or hide the ignored list of ML tag recommendations.
3. Refresh the ML Tag Recommendations page to reflect the most current information.
4. Show or hide available columns.
5. Show or hide the available filters.
6. Filter the information by **Application Name** and **Application Category**. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. See image.
7. View a list of all ML tag recommendations for your organization. For each recommendation, you can see:
  - **Application Name**: The name of the application.
  - **Application Category**: The category type of the application.
  - **Member Count**: How many resources are in the recommendation.
8. Review a recommendation to view its complete information and accept, edit, or ignore it. Any resources that are not selected while accepting the recommendation are considered `ignored`. If the user wants to review the previously ignored recommendations, they must check that list. See image.
9. Delete a recommendation.
10. Display more rows or a different page of the table.
11. View the last time a recommendations check was run, and when the next check is scheduled.
12. View instructions to have Zscaler Support enable or disable ML tag recommendations for your organization.

[Image: A view of the ML Tag Recommendations page and its filters]

[Image: Button to apply configured filters]

[Image: The information details for an individual ML tag recommendation.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-perimeter-policies","lastmod":"2026-11-01T23:34Z","nid":"1543027"} -->
## About Perimeter Policies

- Source: https://help.zscaler.com/microsegmentation/about-perimeter-policies
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Perimeter Policies > About Perimeter Policies
- Last modified: 2026-11-01T23:34Z
- Summary: Information on the Perimeter Policies page for Microsegmentation in the Zscaler Admin Console.

Perimeter policies allow you to easily define how Microsegmentation handles inbound, outbound, and internal traffic for managed resource groups. You can create perimeter policies on the Perimeter Policies page in the Zscaler Admin Console.

The Perimeter Policies page provides the following benefits and enables you to:

- View high-level and in-depth information about the perimeter policies configured for your resource groups.
- Create, edit, and delete perimeter policies to meet your organization's requirements.

## About the Perimeter Policies Page

On the Perimeter Policies page (Zero Trust Cloud > Microsegmentation > Perimeter Policies), you can do the following:

1. View the [policy settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings) statuses.
2. [Add a perimeter policy.](https://help.zscaler.com/zpa/adding-perimeter-policy)
3. Refresh the page to reflect the most current information.
4. Show or hide available columns.
5. Show or hide available filters.
6. Filter the information in the table. After configuring your preferred filters, click the button to apply the filters (e.g., **Apply 1 filter**, **Apply 2 filters**) and view the results. By default, no filters are applied. See image.
7. View a list of all perimeter policies that are configured for your organization. For each perimeter policy, you can see:
  - **Name**: The name of the perimeter policy.
  - **Resource** **Group**: The resource group associated with the perimeter policy.
  - **App Zone**: The AppZones associated with the perimeter policy.
  - **Rule Action**: The actions the perimeter policy takes on inbound, outbound, and internal traffic.
8. Click an individual perimeter policy to view its complete information.
9. Click an individual resource group to view its complete information.
10. [Edit a perimeter policy.](https://help.zscaler.com/zpa/editing-perimeter-policy)
11. [Delete a perimeter policy.](https://help.zscaler.com/zpa/deleting-perimeter-policy)
12. Display more rows or a different page of the table.

[Image: The Perimeter Policies page for Microsegmentation]

[Image: Apply configured filters button]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-policy-recommendations-resource-groups","lastmod":"2026-11-01T23:34Z","nid":"1542953"} -->
## About Policy Recommendations for Resource Groups

- Source: https://help.zscaler.com/microsegmentation/about-policy-recommendations-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Resource Policies > About Policy Recommendations for Resource Groups
- Last modified: 2026-11-01T23:34Z
- Summary: Information about policy recommendations for resource groups in the Zscaler Admin Console.

Microsegmentation provides policy recommendations for resource groups based on historical network flows. Recommendations are listed on a page within the [Application Map](https://help.zscaler.com/zpa/viewing-application-map) and organized by managed and unmanaged flows. You can filter managed and unmanaged flows by direction (i.e., inbound and outbound).

To view policy recommendations for a resource group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Dashboard** > **Microsegmentation** **Application Map**.
2. On the Application Map, configure the filters as desired, then select a resource group. The drawer for the resource group opens.
3. In the drawer, click **Review Recommended Policies**. See image. The Policy Recommendation page for the resource group appears.

The Policy Recommendation page for a resource group provides the following benefits and enables you to:

- Review policy recommendations for a resource group based on flows from the last 14 days.
- Accept and apply recommendations without conflicts for quick and targeted policy creation.
- Resolve conflicts in recommendations, streamlining your organization's ports and policies.

## About Policy Recommendations for Resource Groups

On the Policy Recommendation page for a resource group, you can do the following:

1. Show or hide available columns.
2. Refresh the page to reflect the most current information.
3. Filter the table information. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. By default, no filters are applied. See image.
4. View a list of policy recommendations for your resource group.
5. [Resolve policy conflicts.](https://help.zscaler.com/zpa/reviewing-policy-recommendations-resource-groups)
6. View if the recommendation is accepted. This means the recommendation can be reviewed and applied as a policy.
7. View flows protected by the recommended policy.
8. Edit a policy recommendation.
9. Revert a policy recommendation.
10. Display more rows or a different page of the table.
11. View aggregated information about your policy recommendations.
12. Review accepted recommendations and apply them as policies. To learn more, see [Reviewing Policy Recommendations for Resource Groups](https://help.zscaler.com/zpa/reviewing-policy-recommendations-resource-groups).

[Image: The policy recommendations page for a resource group]

[Image: Review Recommended Policies for a resource group via the Application Map]

[Image: Button to apply configured filters]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-resource-groups","lastmod":"2026-11-01T23:33Z","nid":"1531945"} -->
## About Resource Groups

- Source: https://help.zscaler.com/microsegmentation/about-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > About Resource Groups
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the User-Defined Resource Groups page for Microsegmentation in the Zscaler Admin Console.

Resource groups are the key factor in [configuring Microsegmentation policies](https://help.zscaler.com/zpa/configuring-microsegmentation-policies). Admins can create resource groups based on a [combination of the resources](https://help.zscaler.com/zpa/about-resources) added to them and a mix of dynamic criteria, including hosts, environments, and cloud tag data. To learn more, see [About Resources](https://help.zscaler.com/zpa/about-resources).

The User-Defined Resource Groups page provides the following benefits and enables you to:

- View a summary of resource group data in your organization.
- Configure, edit, and delete resource groups.
- Take appropriate actions based on the resource group data displayed.
- Discover insights about collective resource group data.

## About the User-Defined Resource Groups Page

On the User-Defined Resource Groups page (Zero Trust Cloud > Microsegmentation > Resource Groups), you can do the following:

1. Refresh the page to reflect the most current information.
2. [Add a resource group](https://help.zscaler.com/zpa/configuring-resource-groups).
3. Show or hide available columns.
4. Show or hide available filters.
5. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., **Apply 1 filter**, **Apply 2 filters**) and view the results. By default, no filters are applied. See image.
6. View a list of all resource groups that are configured for your organization. For each resource group, you can see:
  - **Name**: The name of the resource group.
  - **Resource Type**: The type of resource group (**Managed** or **Unmanaged**).
  - **Member Count**: The number of resources in the resource group.
7. Click an individual resource group to view its complete information. See image.
8. [Edit a resource group.](https://help.zscaler.com/zpa/editing-resource-groups)
9. [Delete a resource group.](https://help.zscaler.com/zpa/deleting-resource-groups)
10. Display more rows or a different page of the table.
11. Go to the [System Resource Groups](https://help.zscaler.com/zpa/about-system-resource-groups) page.
12. Go to the [ML Recommendations for Resource Groups](https://help.zscaler.com/zpa/about-ml-recommendations-resource-groups) page.

[Image: The User-Defined Resource Groups page]

[Image: Button to apply configured filters]

[Image: The information details for an individual resource group]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-resources","lastmod":"2026-11-01T23:33Z","nid":"1498241"} -->
## About Resources

- Source: https://help.zscaler.com/microsegmentation/about-resources
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > About Resources
- Last modified: 2026-11-01T23:33Z
- Summary: Information about the Resources page for Microsegmentation in the Zscaler Admin Console.

The Resources page gives insight into your organization's configured object data. It allows you to monitor and analyze the data for all configured agents and AppZones in your organization.

The Resources page provides the following benefits and enables you to:

- View a summary of resource data in your organization.
- View, monitor, and analyze resource data for a single workload.
- Take appropriate actions based on the resource data displayed.
- Discover insights about collective resource data.

## About the Resources Page

On the Resources page (Zero Trust Cloud > Microsegmentation > Resources VM), you can do the following:

1. Refresh the Resources page to reflect the most current information.
2. Show or hide available columns.
3. Show or hide available filters.
4. Filter the information. Click the **+** icon to view additional filters. After configuring your preferred filters, click the button to apply the filters (e.g., Apply 1 filter, Apply 2 filters) and view the results. See image.
5. View a list of all resources that are configured for your organization. For each resource, you can see:
  - **Name**: The name of the resource.
  - **AppZone**: The name of the AppZone connected to the resource.
  - **Type**: The type of resource.
  - **Cloud**: The cloud name where the resource resides.
  - **Status**: The status of the resource.
  - **Platform**: The operating system where the resource resides.
  - **Last Seen**: The last time the resource connected.
6. Click an individual resource to view its complete information:
  - Click **Resource Details** to see **General Information** and **Environment** data. See image.
  - Click **Tags** to configure and manage tag data. To learn more, see [Configuring Resource Tags](https://help.zscaler.com/zpa/configuring-resource-tags). See image.
7. Display more rows or a different page of the table.

[Image: A view of the Resources page and its filters]

[Image: Button to apply configured filters]

[Image: The information details for an individual resource.]

[Image: A view of the Tags tab  of a resource window.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-system-resource-groups","lastmod":"2026-11-01T23:33Z","nid":"1545488"} -->
## About System Resource Groups

- Source: https://help.zscaler.com/microsegmentation/about-system-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > About System Resource Groups
- Last modified: 2026-11-01T23:33Z
- Summary: Information on the System Resource Groups page for Microsegmentation in the Zscaler Admin Console.

The System Resource Groups page lists unmanaged system-level resource groups. You can use these view-only resource groups to define policies. To learn more, see [Configuring Microsegmentation Policies](https://help.zscaler.com/zpa/configuring-microsegmentation-policies).

The System Resource Groups page provides the following benefits and enables you to:

- View a summary of resource group data.
- Take appropriate actions based on the resource group data displayed.
- Discover insights about collective resource group data.

## About the System Resource Groups Page

On the System Resource Groups page (Zero Trust Cloud > Microsegmentation > Resource Groups > System), you can do the following:

1. Refresh the page to reflect the most current information.
2. Show or hide available columns.
3. View a list of all resource groups. For each resource group, you can see:
  - **Name**: The name of the resource group.
  - **Resource Type**: The type of resource group (**Unmanaged**).
  - **Member Count**: The number of resources in the resource group.
4. Click an individual resource group to view its complete information. See image.
5. Display more rows or a different page of the table.
6. Go to the [User-Defined Resource Groups](https://help.zscaler.com/zpa/about-resource-groups) page.
7. Go to the [ML Recommendations for Resource Groups](https://help.zscaler.com/zpa/about-ml-recommendations-resource-groups) page.

[Image: A view of the Resources page and its filters]

[Image: The information details for an individual resource group]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/about-tags","lastmod":"2026-11-01T23:34Z","nid":"1538641"} -->
## About Tags

- Source: https://help.zscaler.com/microsegmentation/about-tags
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > About Tags
- Last modified: 2026-11-01T23:34Z
- Summary: Information about custom tags in Microsegmentation and their namespaces, keys, and values.

The Tag Management page allows you to configure tag data, such as namespaces, keys, and values. After a tag is fully configured with those data components, you can assign the tag to resource groups. To learn more, see [About Resource Groups](https://help.zscaler.com/zpa/about-resource-groups).

The Tag Management page provides the following benefits and enables you to:

- Configure, edit, and delete tag configuration data.
- View a summary of tag configuration data in your organization.
- Discover insights about collective resource tag data.

## About the Tag Management Page

On the Tag Management page (Zero Trust Cloud > Microsegmentation > Tag Management), you can do the following:

1. [Add a namespace.](https://help.zscaler.com/zpa/configuring-namespaces)
2. Click a namespace to display its tag data.
3. Search for a tag key or value from the configured namespace.
4. [Add a tag](https://help.zscaler.com/zpa/configuring-tags).
5. Show or hide available columns.
6. View a list of all tags configured for your namespace.
7. Click an individual tag key to view its **Tag Details**. See image.
8. [Edit a tag.](https://help.zscaler.com/zpa/editing-tags)
9. [Delete a tag.](https://help.zscaler.com/zpa/deleting-tags)
10. Display more rows or a different page of the table.

[Image: A view of the Tags page.]

[Image: A view of the Tag Details window.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/adding-perimeter-policy","lastmod":"2026-11-01T23:34Z","nid":"1543028"} -->
## Adding a Perimeter Policy

- Source: https://help.zscaler.com/microsegmentation/adding-perimeter-policy
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Perimeter Policies > Adding a Perimeter Policy
- Last modified: 2026-11-01T23:34Z
- Summary: How to add a perimeter policy for Microsegmentation in the Zscaler Admin Console.

You can create perimeter policies to easily define how Microsegmentation handles inbound, outbound, and internal traffic (e.g., Block, Sim Block, Allow) for managed resource groups.

You can create a perimeter policy from the Resource Groups page when creating a resource group. To learn more, see [Configuring Resource Groups](https://help.zscaler.com/zpa/configuring-resource-groups).

The following steps describe how to add a perimeter policy from the Perimeter Policies page in the Zscaler Admin Console.

To add a perimeter policy:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to Zero Trust Cloud > Microsegmentation > Perimeter Policies.
2. Click **Add Perimeter Policy**. See image. The **Add Perimeter Policy** drawer opens.
3. In the **Add Perimeter Policy** drawer:
  - **Resource Group**: Select a resource group.
  - **Description**: (Optional) Add a description.
  - **Order**: Select the order of the perimeter policy. The policy is ordered last by default.
  - **App Zones**: Click the checkbox to **Include all AppZones** or select your preferred AppZones.
  - **Inbound Traffic Enforcement**: Select **Sim Block**, **Block**, or **Disable**.
  - **Outbound Traffic Enforcement**: Select **Sim Block**, **Block**, or **Disable**.
  - **Internal Traffic** **Enforcement**: Select **Allow** or **Disable**.
4. Click **Create**. See image.

The perimeter policy is added to the Perimeter Policies page.

[Image: The Add Perimeter Policy button]

[Image: The Add Perimeter Policy drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/configuring-agent-groups","lastmod":"2026-11-01T23:34Z","nid":"1531956"} -->
## Configuring Agent Groups

- Source: https://help.zscaler.com/microsegmentation/configuring-agent-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Groups > Configuring Agent Groups
- Last modified: 2026-11-01T23:34Z
- Summary: How to configure agent groups for Microsegmentation in the Zscaler Admin Console.

Agents in Microsegmentation are installed on servers' workloads, such as virtual machines, bare metal servers, or Kubernetes clusters. You can install agents using most configuration management tools that accommodate Windows `.msi` files or Linux `.deb` and `.rpm` files. Agent groups allow admins to group together different agents to organize them depending on different local machines they are deployed to.

Use the following procedures to configure an agent group based on type:

- Configuring VM Agent Groups
- Configuring Kubernetes Cluster Agent Groups

Admins can [edit](https://help.zscaler.com/zpa/editing-agent-groups) and [delete](https://help.zscaler.com/zpa/deleting-agent-groups) agent groups as needed.

You must place agents in the same group based on the upgrade plan and location. For the upgrade plan, agents in the same agent group inherit the same upgrade plan, including version profile, upgrade schedule, upgrade order (serial or parallel), and upgrade failure behavior (halt or skip). For agents deployed in on-premises data center environments, you must provide the admin-supplied region, virtual private cloud (VPC), and Subnet IDs. These attributes are inherited by all agents in the group.

To configure a VM agent group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**> **Microsegmentation** >**Agent Groups**.
2. Click **Add Agent Group**. See image. The **Add Agent Group** wizard appears.
3. In the **General Information** section, configure the following, then click **Next**:
  1. **Type**: Select **Virtual Machines**.
  2. **Name**: Enter a name for the agent group.
  3. **Admin Status**: Enable or disable this setting. If disabled, the agents within the agent group receive configuration updates from the backend but do not send any logs. Additionally, if the admin status is disabled, policies are not enforced.
  4. **Policy Status**: Enable or disable this setting. If enabled, the agent group can be used in policy configuration. To enable this feature for your organization, contact Zscaler Support.
  5. **Description**: (Optional) Enter a description. The limit is 2,500 characters.
  6. **Cloud**: Select the cloud for the agent group: **AWS**, **Azure**, **GCP**, or **On Premises**.
    - If you select **Azure**, you must provide the **VNET ID** and **Subnet ID**.
    - If you select **On Premises**, you must provide the **VPC ID**, **Subnet ID**, and **Region**.
  7. **Tamper Protection**: (Optional) Enable this setting to prevent or detect unauthorized attempts at agent management. To learn more, see [Understanding Anti-Tamper Protection](https://help.zscaler.com/zpa/understanding-anti-tamper-protection-microsegmentation). To enable this feature for your organization, contact Zscaler Support.
  8. **Agent Age Out**: (Optional) Select **Automatically remove inactive agents**, then enter the amount of time before the agents time out: **Days**, **Hours**, or **Minutes**. Zscaler recommends an age out time of at least 30 days. If the age out time is less than 30 days, you are prompted to confirm this setting before creating the agent group. See image.
  9. Expand **Agent software updates** and configure the following: See image.
    1. **Version Profile**: The **Default** version profile is selected by default. Optionally, select **Latest** or **Custom**.
      - If you select **Latest**, the latest version profile is selected.
      - If you select **Custom**, select the **Custom Version Profile**.
    2. **Auto Update**: Select **Enabled**or **Disabled**. If you select **Enabled**, configure the upgrade schedule for agents in the agent group:
      1. **Upgrade schedule**: Select the day of the week, time zone, start time of the upgrade, and the upgrade window. For example, if you start the upgrade at 8:00 AM and set the upgrade window to 12 hours, then the final agent upgrade required for the group starts no later than 8:00 PM.
      2. **Update Sequence**: Select **Serial** or **Parallel**.
        - If you select **Serial**, agents are upgraded one at a time.
        - If you select **Parallel**, the system automatically upgrades agents in one or more batches. The batch size is automatically calculated depending on the total agent count in the group. For smaller agent groups, the first batch could include all agents, so users might notice all agents are being upgraded.
      3. **In case of upgrade failure**: Select one of the following:
        - If you select **Halt next agent upgrade**, two potential behaviors can happen:
          - If selected with the **Serial** upgrade sequence, then the upgrade process is immediately paused and the agent group is marked as Failed.
          - If selected with the **Parallel** upgrade sequence, then the current batch of agents continues upgrading until they return to their respective status. However, the next agent batch is not started, and the agent groups are marked as Failed.
        - If you select **Skip to next agent**, the system continues to upgrade the next agent, and the agent group's status is marked as Incomplete.
4. In the **Provisioning Key**section, configure the following, then click**Next**: See image.
  - **Name**: Enter the provisioning key name.
  - **Maximum Reuse of Key**: Enter a number from 1 to 1000 for how many times the provisioning key can be reused.
  - **Signing Certificate**: Select a signing certificate.
5. In the **Review**section, verify your agent group configuration, then click **Save**. The agent group is created successfully.
6. In the **Review Documentation**section, review documentation for downloading and installing the agent manager, then click **Done**.

The agent group appears in the list of agent groups.

To configure a Kubernetes Cluster agent group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Agent Groups**.
2. Click **Add Agent Group**. See image. The **Add Agent Group** wizard appears.
3. In the **General Information** section, configure the following, then click **Next**:
  1. **Type**: Select **Kubernetes Cluster**.
  2. **Name**: Enter a name for the agent group.
  3. **Admin Status**: Enable or disable this setting. If disabled, the agents within the agent group receive configuration updates from the backend but do not send any logs. Additionally, if the admin status is disabled, policies are not enforced.
  4. **Description**: (Optional) Enter a description. The limit is 2,500 characters.
  5. **Agent Age Out**: Enter the amount of time before the agents time out: **Days**, **Hours**, or **Minutes**. Zscaler recommends an age out time of at least 7 days. If the age out time is less than 7 days, you are prompted to confirm this setting before creating the agent group.
  6. **Kubernetes platform**: (Optional) Select **EKS**, **GKE**, or **AKS**. If you select **AKS**, you must provide the **VNET ID** and **Subnet ID**. See image.
4. In the **Provisioning Key**section, configure the following, then click**Next**: See image.
  - **Name**: Enter the provisioning key name.
  - **Maximum Reuse of Key**: Enter a number from 1 to 1000 for how many times the provisioning key can be reused.
  - **Signing Certificate**: Select a signing certificate.
5. In the **Review**section, verify your agent group configuration, then click **Save**. The agent group is created successfully.
6. In the **Review Documentation**section, review documentation for downloading and installing the Helm chart, then click **Done**.

The agent group appears in the list of agent groups.

[Image: Add Agent Group button]

[Image: Add a VM agent group in the Agent Group wizard]

[Image: Configure the version profile for the VM agent group]

[Image: Add provisioning key for a VM agent group]

[Image: Add Agent Group button]

[Image: Add a Kubernetes Cluster agent group]

[Image: Add a provisioning key for Kubernetes Cluster agent group]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/configuring-appzones","lastmod":"2026-11-01T23:34Z","nid":"1531942"} -->
## Configuring AppZones

- Source: https://help.zscaler.com/microsegmentation/configuring-appzones
- Product: Microsegmentation
- Path: Microsegmentation Help > AppZone Management > Configuring AppZones
- Last modified: 2026-11-01T23:34Z
- Summary: How to configure AppZones for Microsegmentation in the Zscaler Admin Console.

AppZones in Microsegmentation are applications grouped together into `zones` based on the applications' topology and their underlying network connectivity with each other. Admins can use AppZones to segment resources that use these applications to track usage. The AppZone page gives insight into your organization's complete list of AppZone data. It allows you to monitor and analyze the data for all configured AppZones in your organization.

The following factors impact AppZone definition:

- Network Address Translation (NAT) boundary: The presence of NAT gateways or internal load balancers between applications. All application workloads within an AppZone are assumed to be within a common NAT boundary.
- Policy blast radius: Applications that interact with each other and inherit common policies must be in the same AppZone.

Admins can configure, [edit](https://help.zscaler.com/zpa/editing-appzones), and [delete](https://help.zscaler.com/zpa/deleting-appzones) AppZones as needed.

To configure an AppZone:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**> **Microsegmentation** > **App Zones**.
2. Click **Add AppZone**. The **Add AppZone** dialog appears.
3. In the **Add AppZone** dialog: See image.
  1. **Name**: Enter a name for the new AppZone.
  2. **Description**: (Optional) Enter a description.
  3. **Region**: Select at least one region from the drop-down menu to which the AppZone applies. All resources that belong to the selected regions become members of the AppZone.
  4. (Optional) Select or deselect the **Include all VPCs/VNETs for selected regions** checkbox. If selected, all virtual private clouds or virtual networks in the region apply to this AppZone. It is selected by default. If you deselect it, a new drop-down menu appears to select specific VPCs or VNETs from the regions. The **Include existing and all new subnets added to selected VPCs/VNETs** checkbox also appears. If selected, all resources that are deployed on selected subnets are members of the AppZone. A given resource must belong to only one AppZone. If the AppZone definition has overlapping regions either for VPCs or VNETs or subnets, this results in the mapping of resources to all related AppZones and all related data is highlighted on the Resources page.
4. Click **Save**.

Your new AppZone appears in the list of AppZones.

[Image: The view of a new AppZone configuration.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/configuring-microsegmentation-policies","lastmod":"2026-11-01T23:34Z","nid":"1531966"} -->
## Configuring Microsegmentation Policies

- Source: https://help.zscaler.com/microsegmentation/configuring-microsegmentation-policies
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Resource Policies > Configuring Microsegmentation Policies
- Last modified: 2026-11-01T23:34Z
- Summary: How to configure Microsegmentation policies.

Admins can create Layer 3 and Layer 4 Microsegmentation enforcement policies to protect east-west traffic in both cloud and data center environments. Policies are applied between resource groups or between a resource group and any other [resource](https://help.zscaler.com/zpa/about-resources). Policies are evaluated from highest priority (lowest integer) to lowest priority (highest integer). No two policies can have the same priority.

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Configuring a Microsegmentation Policy

To configure a Microsegmentation policy:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Policy** **Rules**.
2. Click **Add Rule**. See image. The **Add Rule**wizard appears.
3. In the **Add Rule** wizard:
  1. In the **General Information** section: See image.
    1. **Name**: Enter a name for the new policy.
    2. **Description**: (Optional) Enter a description.
    3. Click **Next**.
  2. In the **Rule Order** section, enter a number to order the new rule in the list. The maximum number possible depends on how many existing rules there are in the list. See image.
  3. In the **Rule Configuration** section:
    1. **Action**: Select **Allow**, **Sim Block**, or **Block**.
    2. **Source**: Select sources that apply to the traffic found. You can select up to 10 sources total.
    3. **Destination**: Select destinations that apply to the traffic found. You can select up to 10 destinations total.
    4. **Destination application port template**: Select from the drop-down menu applications that are in the Application Catalog. When you select an application, it appears in a table that displays the ports associated with it. To learn more, see [About the Application Catalog for Microsegmentation](https://help.zscaler.com/zpa/about-application-catalog-microsegmentation).
    5. **Additional TCP Port Ranges**: Enter TCP port range information in the **From** and **To** fields. Click **Add TCP Port Range** to add up to 10 ranges, or enable the toggle **Include all TCP ports** to add all available TCP port ranges.
    6. **Additional UDP Port Ranges**: Enter UDP port range information in the **From** and **To** fields. Click **Add UDP Port Range** to add up to 10 ranges, or enable the toggle **Include all UDP ports** to add all available TCP port ranges.
    7. **Scope**: Select or deselect the checkbox to **Include all AppZones**. By default, it is already selected. If you deselect this option, a drop-down menu appears for you to choose which AppZones to include.
    8. Click **Next**. See image.
  4. In the **Review** section, review your configurations. Click the **Edit** icon to edit any of the fields, then click **Save**. See image.

Your new policy rule appears in the list of rules on the [Resource Policies](https://help.zscaler.com/zpa/about-microsegmentation-policies) page.

[Image: The Add Rule button on the Resource Policies page]

[Image: The General Information section of the Add Rule wizard]

[Image: The Rule Order section of the Add Rule wizard]

[Image: The Rule Configuration section of the Add Rule wizard]

[Image: The Review section of the Add Rule wizard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/configuring-namespaces","lastmod":"2026-11-01T23:34Z","nid":"1538743"} -->
## Configuring Namespaces

- Source: https://help.zscaler.com/microsegmentation/configuring-namespaces
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > Configuring Namespaces
- Last modified: 2026-11-01T23:34Z
- Summary: How to create a namespace for Microsegmentation.

Namespaces are the areas in which tags for Microsegmentation are created. You can create your own custom namespaces or allow machine learning (ML) and cloud namespaces to be generated from your organization's data. You can [edit](https://help.zscaler.com/zpa/editing-namespaces) or [delete](https://help.zscaler.com/zpa/deleting-namespaces) custom namespaces at any time.

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Configuring Custom Namespaces

To configure a custom namespace for a tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Tag Management**.
2. Click the **+** icon to add a custom namespace. See image. The **Add Namespace** drawer opens.
3. In the **Add Namespace** drawer:
  1. **Name**: Enter a name for the custom namespace.
  2. **Description**: (Optional) Add information to describe the namespace. See image.
  3. Click **Save**.

Your custom namespace appears in the list.

[Image: A view of the Tags page.]

[Image: A view of the Add Namespace window.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/configuring-resource-groups","lastmod":"2026-11-01T23:33Z","nid":"1531946"} -->
## Configuring Resource Groups

- Source: https://help.zscaler.com/microsegmentation/configuring-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > Configuring Resource Groups
- Last modified: 2026-11-01T23:33Z
- Summary: How to configure resource groups for Microsegmentation in the Zscaler Admin Console.

Resource groups are collections of resources. Policies are applied to traffic between two resource groups. To learn more, see [About Resource Groups](https://help.zscaler.com/zpa/about-resource-groups), [About System Resource Groups](https://help.zscaler.com/zpa/about-system-resource-groups), and [About Microsegmentation Policies](https://help.zscaler.com/zpa/about-microsegmentation-policies).

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Configuring a Resource Group

To configure a resource group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Resource Groups**.
2. Click **Add Resource Group**. See image. The **Add Resource Group**wizard appears.
3. In the **Add Resource Group**wizard:
  1. In the **General Information** section:
    1. **Name**: Enter a name for the new resource group.
    2. **Description**: (Optional) Enter a description. See image.
    3. Click **Next**.
  2. In the **Criteria** section:
    1. Select one of the following options:
      - **Managed**: The resources have agents and are actively protected.
      - **Unmanaged**: The resources are not actively protected by agents. Unmanaged resource groups are defined as Classless Inter-Domain Routing (CIDR) blocks or IP ranges.
    2. **Static Membership**: Select any or all resources.
    3. **Dynamic Membership**: Click **+ Add/Edit Criteria**. See image. The **Dynamic Membership Criteria** drawer appears.
    4. In the **Dynamic Membership Criteria** drawer:
      1. Select one of the following tag scopes: See image. The Static Membership and Dynamic Membership options are only for Managed resource groups.
        - **Host**: Allows users to define criteria for **Host Name**, **Platform**, **Platform Distro**, **Platform Version**, and **CPU Architecture**.
        - **Environment**: Allows users to define criteria for **AMI ID**, **Account/Subscription ID**, **Cloud Provider**, **Cloud Region**, **VPC/VNET ID**, **Subnet ID**, **Security Group ID**, Classless Inter-Domain Routing**(CIDR)**, and **IP Range**.
        - **ZMS**: Allows the users to select different Zscaler Microsegmentation (ZMS) agent groups.
        - **Cloud**: Allows the users to select different clouds.
        - **ML**: Allows users to select machine learning (ML) recommended tags. To learn more, see [About ML Tag Recommendations Page](https://help.zscaler.com/zpa/about-ml-tag-recommendations-page).
      2. Click **Done**.
    5. **Create Perimeter**: The option to create a perimeter policy for the resource group is selected by default.
  3. In the **Review** section:
    1. In the **General Information** and **Criteria** sections, edit the fields as needed.
    2. In the **Create Perimeter** section: See image.
      - **Description**: (Optional) Add a description.
      - **AppZones**: Click the checkbox to **Include all AppZones** or select your preferred AppZones.
      - **Inbound Traffic Enforcement**: Select **Sim Block**, **Block**, or **Disable**.
      - **Outbound Traffic Enforcement**: Select **Sim Block**, **Block**, or **Disable**.
      - **Internal Traffic Enforcement**: Select **Allow** or **Disable**.
    3. Click **Save**.

Your new resource group appears in the list. If you added a perimeter policy to your resource group, the policy appears at the bottom of the [Perimeter Policies](https://help.zscaler.com/zpa/about-perimeter-policies) page.

[Image: The Resource Groups page]

[Image: The General Information section of the Add Resource Group wizard]

[Image: The Criteria section of the Add Resource Group wizard]

[Image: Tag scopes in Dynamic Membership Criteria]

[Image: The Review section of the Add Resource Group wizard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/configuring-resource-tags","lastmod":"2026-11-01T23:33Z","nid":"1538475"} -->
## Configuring Resource Tags

- Source: https://help.zscaler.com/microsegmentation/configuring-resource-tags
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > Configuring Resource Tags
- Last modified: 2026-11-01T23:33Z
- Summary: How to configure tags for resources in Microsegmentation in the Zscaler Admin Console.

Tags are resource components used when configuring criteria for [resource groups](https://help.zscaler.com/zpa/configuring-resource-groups). You can create and delete tags individually. To learn more, see [About Resource Groups](https://help.zscaler.com/zpa/about-resource-groups) and [About Resources](https://help.zscaler.com/zpa/about-resources).

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Configuring Resource Tags

To create a new resource tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Resources VM**.
2. Click a resource. The resource drawer opens.
3. In the resource drawer, click **Tags**. See image. The **Tags** section appears.
4. In the **Tags** section, click **Add Tag**. See image.
5. Add a tag: See image.
  - **Namespace**: Select a namespace for the tag.
  - **Key**: Select a key name to title the tag.
  - **Value**: Select value components for which the tag qualifies.
6. Click **Save**.

Your new resource tag appears in the list.

[Image: The Resources page]

[Image: The Tags section of a resource]

[Image: The Add Tag configuration options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/configuring-tags","lastmod":"2026-11-01T23:34Z","nid":"1538746"} -->
## Configuring Tags

- Source: https://help.zscaler.com/microsegmentation/configuring-tags
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > Configuring Tags
- Last modified: 2026-11-01T23:34Z
- Summary: How to configure tags for Microsegmentation.

Admins can create tags for [custom namespaces](https://help.zscaler.com/zpa/configuring-namespaces) in Microsegmentation that can then be assigned to resource groups. Cloud or machine learning (ML) tags are autogenerated from your organization's data, and you cannot manually configure them. You can [edit](https://help.zscaler.com/zpa/editing-tags) or [delete](https://help.zscaler.com/zpa/deleting-tags) custom tags at any time.

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Configuring Custom Tags

To configure a custom tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Tag Management**.
2. Select the namespace in which you want to add a tag.
3. Click **Add Tag**. See image. The **Add Tag** drawer opens.
4. In the **Add Tag** drawer:
  1. **Key**: Enter a key name for the custom tag.
  2. **Description**: (Optional) Add information to describe the tag.
  3. **Values**: Add individual values, separated by commas, for the tag. To add many values to the tag, click the **Add** button next to the **Values** field, and it displays a table of all the added values. You can also delete any added values by clicking the **Delete** icon. See image.
  4. Click **Save**.

Your new custom tag appears in the list.

[Image: The Tags page]

[Image: The Add Tag drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-agent-groups","lastmod":"2026-11-01T23:34Z","nid":"1531958"} -->
## Deleting Agent Groups

- Source: https://help.zscaler.com/microsegmentation/deleting-agent-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Groups > Deleting Agent Groups
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete agent groups for Microsegmentation in the Zscaler Admin Console.

Admins can [configure](https://help.zscaler.com/zpa/configuring-agent-groups), [edit](https://help.zscaler.com/zpa/editing-agent-groups), and delete agent groups as needed.

To delete an agent group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Agent Groups**.
2. Delete an agent group: The**Agent Group** window appears.
  1. For agent groups with the Kubernetes Cluster type, click the **Delete**icon. See image.
  2. For agent groups with the VM type, click the **More** icon, then click **Delete** in the menu. See image.
3. In the **Agent Group** window: See image.
  1. Enter `CONFIRM`.
  2. Click **Delete**.

The page refreshes and removes the agent group from the list.

[Image: Delete Kubernetes Cluster agent group]

[Image: Delete VM agent group]

[Image: A confirmation window appears when deleting an Agent Group]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-agent-provisioning-keys","lastmod":"2026-11-01T23:34Z","nid":"1531961"} -->
## Deleting Agent Provisioning Keys

- Source: https://help.zscaler.com/microsegmentation/deleting-agent-provisioning-keys
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Provisioning Keys > Deleting Agent Provisioning Keys
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete agent provisioning keys for Microsegmentation in the Zscaler Admin Console.

Agent provisioning keys are created during agent group configuration. You can [edit](https://help.zscaler.com/zpa/editing-agent-provisioning-keys) or delete provisioning keys as needed outside agent group configuration.

To delete an agent provisioning key:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Agent Groups** and select an agent group.
2. On the **Provisioning Keys** tab, click the **Delete** icon for the provisioning key you want to delete. See image. The **Delete Provisioning Key** window appears.
3. Click **Delete**. See image.

The page refreshes and removes the agent provisioning key from the list.

[Image: A view of the Provisioning Keys page and all its actions]

[Image: A confirmation appears when you delete an agent provisioning key]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-agents","lastmod":"2026-11-01T23:34Z","nid":"1531954"} -->
## Deleting Agents

- Source: https://help.zscaler.com/microsegmentation/deleting-agents
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agents > Deleting Agents
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete agents for Microsegmentation in the Zscaler Admin Console.

Admins can [edit](https://help.zscaler.com/zpa/editing-agents) and delete agents as needed.

To delete an agent:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Agents**.
2. Click the **Delete**icon next to the agent you want to delete. See image. The **Delete Agent** window appears.
3. In the **Delete Agent** window, click **Delete**. See image.

Deleting an agent does not remove it from the Agent list, because it still exists in the workload services. Deleted agents are only unregistered in the system. The agent must be manually uninstalled from the workload, otherwise the resource reported by the agent is marked as Deleted. Deleted resources might appear in the Application map when viewing historical flows until the logs roll over every 14 days.

[Image: The Delete icon for the agent]

[Image: A confirmation box appears when deleting an agent]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-appzones","lastmod":"2026-11-01T23:34Z","nid":"1531944"} -->
## Deleting AppZones

- Source: https://help.zscaler.com/microsegmentation/deleting-appzones
- Product: Microsegmentation
- Path: Microsegmentation Help > AppZone Management > Deleting AppZones
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete AppZones for Microsegmentation in the Zscaler Admin Console.

Admins can [configure](https://help.zscaler.com/zpa/configuring-appzones), [edit](https://help.zscaler.com/zpa/editing-appzones), and delete AppZones as needed.

When you delete an AppZone, its matched flow doesn't appear in Flow Logs and any related resources become unmapped.

To delete an AppZone:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**App Zones**.
2. Click the **Delete** icon for the AppZone you want to delete. The **Delete AppZone** window appears.
3. In the **Delete AppZone** window:
  1. Enter `CONFIRM`. See image.
  2. Click **Delete**.

The page refreshes and removes the AppZone from the list.

[Image: Delete AppZone confirmation window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-microsegmentation-policy","lastmod":"2026-11-01T23:34Z","nid":"1531969"} -->
## Deleting a Microsegmentation Policy

- Source: https://help.zscaler.com/microsegmentation/deleting-microsegmentation-policy
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Resource Policies > Deleting a Microsegmentation Policy
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete a Microsegmentation policy.

You can [configure](https://help.zscaler.com/zpa/configuring-microsegmentation-policies), [edit](https://help.zscaler.com/zpa/editing-microsegmentation-policy), and delete policy rules as necessary. Any configurations, edits, or deletions of policy rules take effect immediately.

To delete a Microsegmentation policy rule:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Policy** **Rules**.
2. Click the **Delete**icon for the policy rule you want to delete. See image. The **Delete Policy** window appears.
3. In the **Delete Policy** window: See image.
  1. Enter `CONFIRM`.
  2. Click **Delete**.

[Image: The Delete icon on the Resource Policies page]

[Image: The Delete Policy confirmation window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-namespaces","lastmod":"2026-11-01T23:34Z","nid":"1538745"} -->
## Deleting Namespaces

- Source: https://help.zscaler.com/microsegmentation/deleting-namespaces
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > Deleting Namespaces
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete namespaces for Microsegmentation.

Microsegmentation allows admins to [configure](https://help.zscaler.com/zpa/configuring-namespaces), [edit](https://help.zscaler.com/zpa/editing-namespaces), and delete namespaces at any time, as long as they are custom-type namespaces. You cannot delete cloud and machine learning (ML) namespaces.

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Deleting Custom Namespaces

To delete a custom namespace for a tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Tag Management**.
2. Click the **More**icon (vertical ellipsis) on the chosen namespace.
3. Click **Delete**. See image. The **Delete Namespace** window appears.
4. In the **Delete Namespace** window:
  1. Enter `CONFIRM`. See image.
  2. Click **Delete**.

The page reloads and your namespace is removed from the list.

[Image: The Tags page]

[Image: The Delete Namespace confirmation window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-perimeter-policy","lastmod":"2026-11-01T23:34Z","nid":"1545487"} -->
## Deleting a Perimeter Policy

- Source: https://help.zscaler.com/microsegmentation/deleting-perimeter-policy
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Perimeter Policies > Deleting a Perimeter Policy
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete a perimeter policy.

To delete a perimeter policy:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Perimeter Policies**.
2. Click the **Delete**icon for the perimeter policy you want to delete. See image. The **Delete Perimeter Policy Rule** window appears.
3. In the **Delete Perimeter Policy Rule** window: See image.
  1. Enter `CONFIRM`.
  2. Click **Delete**.

[Image: The Delete icon for a perimeter policy]

[Image: The Delete Perimeter Policy Rule window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-resource-groups","lastmod":"2026-11-01T23:33Z","nid":"1531948"} -->
## Deleting Resource Groups

- Source: https://help.zscaler.com/microsegmentation/deleting-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > Deleting Resource Groups
- Last modified: 2026-11-01T23:33Z
- Summary: How to delete resource groups for Microsegmentation.

You can [configure](https://help.zscaler.com/zpa/configuring-resource-groups), [edit](https://help.zscaler.com/zpa/editing-resource-groups), and delete resource groups at any time to provide flexibility in managing traffic that can change over time. To learn more, see [About Resource Groups](https://help.zscaler.com/zpa/about-resource-groups).

To delete a resource group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** >**Microsegmentation**>**Resource Groups**.
2. Click the **Delete** icon next to the resource group you want to delete. See image. The **Delete Resource Group** window appears.
3. In the **Delete Resource Group** window:
  1. Enter `CONFIRM`. See image.
  2. Click **Delete**.

[Image: The Delete icon for the resource group]

[Image: A view of the Delete Resource Group confirmation window.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-resource-tags","lastmod":"2026-11-01T23:33Z","nid":"1538476"} -->
## Deleting Resource Tags

- Source: https://help.zscaler.com/microsegmentation/deleting-resource-tags
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > Deleting Resource Tags
- Last modified: 2026-11-01T23:33Z
- Summary: How to delete resource tags in Microsegmentation.

[Resource tags](https://help.zscaler.com/zpa/configuring-resource-tags) are used to define criteria when creating a resource group. You can manually delete individual tags from the list at any time.

To delete a resource tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Resources VM**.
2. Click a resource. The resource drawer opens.
3. In the resource drawer, click **Tags**. See image. The **Tags** section appears.
4. In the **Tags** section:
  1. Click the **Delete** icon next to the tag you want to delete. The **Delete Tag**window appears.
  2. In the **Delete Tag** window:
    1. Enter `CONFIRM`. See image.
    2. Click **Delete**.

The page refreshes and removes the deleted tag from the list.

[Image: The Tags section of a resource]

[Image: The Delete Tag confirmation window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/deleting-tags","lastmod":"2026-11-01T23:34Z","nid":"1538748"} -->
## Deleting Tags

- Source: https://help.zscaler.com/microsegmentation/deleting-tags
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > Deleting Tags
- Last modified: 2026-11-01T23:34Z
- Summary: How to delete tags for Microsegmentation.

Microsegmentation allows admins to [configure](https://help.zscaler.com/zpa/configuring-tags), [edit](https://help.zscaler.com/zpa/editing-tags), and delete tags at any time, as long as they are custom-type tags. Admins cannot delete tags within cloud and machine learning (ML) namespaces.

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Deleting Custom Tags

To delete a custom tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Tag Management**.
2. Select the namespace that contains the tag you want to delete.
3. Click the **Delete**icon on the chosen tag. See image. The **Delete Tag Key**window appears.
4. In the **Delete Tag Key** window:
  1. Enter `CONFIRM`. See image.
  2. Click **Delete**.

The page reloads and your tag is removed from the list.

[Image: The Tags page]

[Image: The Delete Tag Key confirmation window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-agent-groups","lastmod":"2026-11-01T23:34Z","nid":"1531957"} -->
## Editing Agent Groups

- Source: https://help.zscaler.com/microsegmentation/editing-agent-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Groups > Editing Agent Groups
- Last modified: 2026-11-01T23:34Z
- Summary: How to edit agent groups for Microsegmentation in the Zscaler Admin Console.

Admins can [configure](https://help.zscaler.com/zpa/configuring-agent-groups), edit, and [delete](https://help.zscaler.com/zpa/deleting-agent-groups) agent groups as needed.

To edit an agent group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** >**Microsegmentation**>**Agent Groups**.
2. Click the **Edit** icon next to the agent group you want to edit. See image. The **Edit Agent Group** window appears. If you are editing an agent group with the Kubernetes Cluster type, the **Edit Cluster** window appears.
3. In the **Edit Agent Group** window, modify the information as necessary. To learn more, see [Configuring Agent Groups](https://help.zscaler.com/zpa/configuring-agent-groups). See image. If you change the Admin Status, you are asked if you also want to change the Admin Status for all agents in that group. To learn more, see [Editing Agents](https://help.zscaler.com/zpa/editing-agents).
4. Click **Save**.

Your updated agent group reflects the changes in the list of agent groups.

[Image: A view of the Agents Groups page and all its actions.]

[Image: Edit Agent Group window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-agent-provisioning-keys","lastmod":"2026-11-01T23:34Z","nid":"1531960"} -->
## Editing Agent Provisioning Keys

- Source: https://help.zscaler.com/microsegmentation/editing-agent-provisioning-keys
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Provisioning Keys > Editing Agent Provisioning Keys
- Last modified: 2026-11-01T23:34Z
- Summary: How to edit agent provisioning keys for Microsegmentation in the Zscaler Admin Console.

Agent provisioning keys are created during agent group configuration. You can edit or [delete](https://help.zscaler.com/zpa/deleting-agent-provisioning-keys) provisioning keys as needed outside agent group configuration.

To edit an agent provisioning key:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Agent Groups** and select an agent group.
2. On the **Provisioning Keys** tab, click the **Edit**icon for the provisioning key you want to edit. See image. The **Edit Provisioning Key** drawer opens.
3. In the **Edit Provisioning Key** drawer:
  1. **Name:**Edit the provisioning key name.
  2. **Maximum Reuse of Key**: The number of times the key can be reused. Enter a number from 1 to 1000.
  3. **Signing Certificate**: Select a signing certificate from the drop-down menu. See image.
  4. Click **Save**.

Your updated provisioning key appears in the list of agent provisioning keys.

[Image: A view of the Agent Provisioning Keys page and all its actions.]

[Image: A view of the Edit Provisioning Key window details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-agents","lastmod":"2026-11-01T23:33Z","nid":"1531953"} -->
## Editing Agents

- Source: https://help.zscaler.com/microsegmentation/editing-agents
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agents > Editing Agents
- Last modified: 2026-11-01T23:33Z
- Summary: How to edit agents for Microsegmentation in the Zscaler Admin Console.

Admins can edit and [delete](https://help.zscaler.com/zpa/deleting-agents) agents as needed.

To edit an agent:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**> **Agents**.
2. Click the **Edit**icon next to the agent you want to edit. See image. The **Edit <Agent Name>** drawer opens.
3. In the **Edit <Agent Name>** drawer: You cannot edit the additional information. Review it as necessary. See image.
  1. **Name**: Edit the name of the agent.
  2. **Admin Status**: (Optional) Select **Enabled**,**Disabled**, or**Inherit From Group**.
  3. **Tamper Protection Status**: (Optional) Select **Disabled** or **Inherit From Group**.
  4. **Policy Status**: (Optional) Enable or disable the policy status.
  5. **Description**: (Optional) Edit the description.
4. Click **Save**.

Your updated agent appears in the list of Agents.

[Image: Edit icon]

[Image: The Edit Agent window details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-appzones","lastmod":"2026-11-01T23:34Z","nid":"1531943"} -->
## Editing AppZones

- Source: https://help.zscaler.com/microsegmentation/editing-appzones
- Product: Microsegmentation
- Path: Microsegmentation Help > AppZone Management > Editing AppZones
- Last modified: 2026-11-01T23:34Z
- Summary: How to edit AppZones for Microsegmentation in the Zscaler Admin Console.

Admins can [configure](https://help.zscaler.com/zpa/configuring-appzones), edit, and [delete](https://help.zscaler.com/zpa/deleting-appzones) AppZones as needed.

To edit an AppZone:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**App Zones**.
2. Click the **Edit** icon for the AppZone you want to edit. The **Edit AppZone** wizard appears.
3. In the **Edit AppZone**wizard, modify the information as necessary. To learn more, see [Configuring AppZones](https://help.zscaler.com/zpa/configuring-appzones). See image.
4. Click **Save**.

Your updated AppZone appears in the list of AppZones.

[Image: Edit the AppZone]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-microsegmentation-policy","lastmod":"2026-11-01T23:34Z","nid":"1531967"} -->
## Editing a Microsegmentation Policy

- Source: https://help.zscaler.com/microsegmentation/editing-microsegmentation-policy
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Resource Policies > Editing a Microsegmentation Policy
- Last modified: 2026-11-01T23:34Z
- Summary: How to edit a Microsegmentation policy.

You can edit Microsegmentation policies to allow flexibility in managing traffic that might change over time. You can [configure](https://help.zscaler.com/zpa/configuring-microsegmentation-policies), edit, and [delete](https://help.zscaler.com/zpa/deleting-microsegmentation-policy) policy rules as necessary.

To edit a Microsegmentation policy rule:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Policy** **Rules**.
2. Click the **Edit**icon for the policy rule you want to edit. See image. The **Edit Rule** wizard appears.
3. In the **Edit Rule** wizard, edit the fields as necessary and click **Save**. To learn more, see [Configuring Microsegmentation Policies](https://help.zscaler.com/zpa/configuring-microsegmentation-policies).

[Image: The Edit icon on the Resource Policies page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-namespaces","lastmod":"2026-11-01T23:34Z","nid":"1538744"} -->
## Editing Namespaces

- Source: https://help.zscaler.com/microsegmentation/editing-namespaces
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > Editing Namespaces
- Last modified: 2026-11-01T23:34Z
- Summary: How to edit custom namespaces for Microsegmentation.

Custom namespaces allow admins to specify the keys and values they want for certain tags. While you cannot edit or delete cloud and machine learning (ML) namespaces, you can edit and delete custom namespaces.

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Editing Custom Namespaces

To edit a custom namespace for a tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Tag Management**.
2. Click the **More**icon (vertical ellipsis)on the chosen namespace.
3. Click **Edit**. See image. The **Edit Namespace** drawer opens.
4. In the **Edit Namespace** drawer window:
  1. **Name**: Edit the text to change the name of the custom namespace.
  2. **Description**: (Optional) Add or remove information that describes the namespace. See image.
  3. Click **Save**.

The page reloads and the changes to your custom namespace appear in the list.

[Image: A view of the Tags page.]

[Image: A view of the Edit Namespace window.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-perimeter-policy","lastmod":"2026-11-01T23:34Z","nid":"1545486"} -->
## Editing a Perimeter Policy

- Source: https://help.zscaler.com/microsegmentation/editing-perimeter-policy
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Perimeter Policies > Editing a Perimeter Policy
- Last modified: 2026-11-01T23:34Z
- Summary: How to edit a perimeter policy.

To edit a perimeter policy:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Perimeter Policies**.
2. Click the **Edit**icon for the perimeter policy you want to edit. See image. The **Edit Perimeter Policy**drawer opens.
3. In the **Edit Perimeter Policy** drawer, edit the fields as necessary, then click **Save**. You cannot edit the **Resource Group** field. To learn more, see [Adding a Perimeter Policy](https://help.zscaler.com/zpa/configuring-microsegmentation-policies). See image.

[Image: The Edit icon for a perimeter policy]

[Image: The Edit Perimeter Policy drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-resource-groups","lastmod":"2026-11-01T23:33Z","nid":"1531947"} -->
## Editing Resource Groups

- Source: https://help.zscaler.com/microsegmentation/editing-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Resource Management > Editing Resource Groups
- Last modified: 2026-11-01T23:33Z
- Summary: How to edit resource groups for Microsegmentation in the Zscaler Admin Console.

You can [configure](https://help.zscaler.com/zpa/configuring-resource-groups), edit, and [delete](https://help.zscaler.com/zpa/deleting-resource-groups) resource groups at any time to provide flexibility in managing security posture.

To edit a resource group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**>**Resource Groups**.
2. Click the **Edit** icon next to the resource group you want to edit. See image. The **Edit Resource Group**window appears.
3. In the **Edit Resource Group**window, edit the options as necessary. To learn more, see [Configuring Resource Groups](https://help.zscaler.com/zpa/configuring-resource-groups). See image.
4. Click **Save**.

[Image: The edit icon next to the resource group]

[Image: Edit Resource Group]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/editing-tags","lastmod":"2026-11-01T23:34Z","nid":"1538747"} -->
## Editing Tags

- Source: https://help.zscaler.com/microsegmentation/editing-tags
- Product: Microsegmentation
- Path: Microsegmentation Help > Tag Management > Editing Tags
- Last modified: 2026-11-01T23:34Z
- Summary: How to edit tags for Microsegmentation.

Tags allow admins to manage the data that is assigned to resource groups with custom criteria. Admins can manually edit custom tags at any time.

## Prerequisites

Enable policy enforcement for your organization. To learn more, see [Enabling Microsegmentation Policy Settings](https://help.zscaler.com/zpa/enabling-microsegmentation-policy-settings).

## Editing Custom Tags

To edit a custom tag:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Tag Management**.
2. Select the namespace that contains the tag you want to edit.
3. Click the **Edit**icon on the chosen tag. See image. The **Edit Tag** drawer opens.
4. In the **Edit Tag** drawer:
  1. **Key**: Edit the key name for the custom tag.
  2. **Description**: (Optional) Add or remove information that describes the tag.
  3. **Values**: Add or remove individual values, separated by commas, for the tag. To add many values to the tag, click the **Add** button next to the **Values** field, and it displays a table of all the added values. You can also delete any added values by clicking the **Delete** icon. See image.
  4. Click **Save**.

The page reloads and the changes to your custom tag appear in the list.

[Image: The Tags page]

[Image: The Edit Tag drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/enabling-anti-tamper-protection-microsegmentation","lastmod":"2026-11-01T23:33Z","nid":"1539804"} -->
## Enabling Anti-Tamper Protection for Microsegmentation

- Source: https://help.zscaler.com/microsegmentation/enabling-anti-tamper-protection-microsegmentation
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Enabling Anti-Tamper Protection for Microsegmentation
- Last modified: 2026-11-01T23:33Z
- Summary: Instructions for how to enable anti-tamper protection for Microsegmentation.

Agent version 1.12.2 is the minimum-supported version for the anti-tamper protection feature.

Anti-tamper protection for Microsegmentation protects agent functionality from malicious activity. You can enable it via the agent or agent group.

## Enabling Anti-Tamper Protection for Microsegmentation for an Agent Group

Use this preferred method for managing anti-tamper protection.

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**> **Agent Groups**.
2. [Edit an existing agent group](https://help.zscaler.com/zpa/editing-agent-groups) and change the following options:
  1. In the **Tamper Protection** section, click **Enabled**.
  2. Click **Save**.

## Enabling Anti-Tamper Protection for Microsegmentation for an Agent

Use this method for phased rollout, exceptions, or targeted enforcement.

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud**>**Microsegmentation**> **Agents**.
2. [Edit an existing agent](https://help.zscaler.com/zpa/editing-agents) and change the following options:
  1. In the **Tamper Protection** section, click **Inherit From Group** (**Enabled)**.
  2. Click **Save**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/enabling-microsegmentation-policy-settings","lastmod":"2026-11-01T23:34Z","nid":"1531964"} -->
## Enabling Microsegmentation Policy Settings

- Source: https://help.zscaler.com/microsegmentation/enabling-microsegmentation-policy-settings
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Enabling Microsegmentation Policy Settings
- Last modified: 2026-11-01T23:34Z
- Summary: How to enable Microsegmentation policy settings.

On the Policy Settings page, admins can control policy enforcement settings at a global level.

To enable policy settings:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** > **Policy** **Settings**.
2. Select **Enable** for **Policy Enforcement**.
3. Click **Allow** for**Default Resource Policy Rule**. This rule is what the system defaults to if other policies don’t apply to the traffic observed. The scope of this policy acts as a catch-all for the entirety of your organization's traffic. See image.

You are prompted to confirm policy settings before they take effect.

[Image: The Policy Settings page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/installing-upgrading-agent-manager","lastmod":"2026-11-01T23:33Z","nid":"1531951"} -->
## Installing & Upgrading the Agent Manager

- Source: https://help.zscaler.com/microsegmentation/installing-upgrading-agent-manager
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agents > Installing & Upgrading the Agent Manager
- Last modified: 2026-11-01T23:33Z
- Summary: How to install the Zscaler Microsegmentation agent manager.

The agent manager installs the Zscaler Microsegmentation agent service onto your device. It performs initial installation of the agent as well as agent upgrades.

## Prerequisites

Before you install the agent manager, create a signing certificate to be used for Microsegmentation agents.

A common practice is to generate a new signing certificate named Microsegmentation, which is an intermediate type with the root certificate as its parent. When generating this certificate, set the **Client Certificate Type** to **None**.

See image.

To learn more, see [Creating Certificate Signing Requests for Enrollment (CA) Certificates](https://help.zscaler.com/zpa/creating-certificate-signing-requests-enrollment-ca-certificates).

Microsegmentation agents and Zscaler Client Connector are not compatible and should not be installed on the same host.

## Installing the Agent Manager

To install the agent manager:

1. Download the agent manager installer and view the installation instructions for your operating system (Linux or Windows) by doing either of the following: See image.
  - Create a new agent group. To learn more, see [Configuring Agent Groups](https://help.zscaler.com/zpa/configuring-agent-groups).
  - If an appropriate agent group already exists, go to **Zero Trust Cloud** > **Microsegmentation** > **Agent Groups** and click the **Download** icon in the **Actions** column. To learn more, see [About Agent Groups](https://help.zscaler.com/zpa/about-agent-groups).
2. After the agent manager is installed, the manager automatically downloads and installs the agent version selected in the agent group’s version profile. After the agent is downloaded and installed onto your device, the agent initiates a connection to the Zscaler Zero Trust Exchange (ZTE), and the setup is complete.

If the service fails to start or the agent fails to register with the ZTE, it is likely that a firewall or security appliance is interfering with the agent’s outbound mTLS connection. Ensure that this outbound connectivity to Port 443 is permitted, and that this connectivity is not subjected to DPI or similar security measures. To learn more, see [Zscaler Config](https://config.zscaler.com/private.zscaler.com/micro-segmentation).

## Upgrading the Agent

To configure automatic upgrades to the agent:

1. Edit an existing agent group. To learn more, see [Editing Agent Groups](https://help.zscaler.com/zpa/editing-agent-groups).
2. In the selected agent group, expand **Agent software updates**.
3. For **Version Profile**, select the preferred option:
  - **Default**: One or two versions older than the Latest release.
  - **Latest**: The most recent release.
  - **Custom**: Your selection from the historical list of agent version profiles, which includes the Latest release.
4. Enable **Auto Update**. See image.
5. Complete the remaining steps for editing the agent group.
6. Click **Save**. The agent manager downloads and installs the specified agent version to your device.

## Uninstalling the Agent & Agent Manager

To uninstall the agent and agent manager, see the following steps based on your operating system:

For Linux Debian, run the following command:

```
apt remove -y eyez-agentmanager eyez-agent
```

For Linux RPM, run the following command:

```
yum remove -y eyez-agentmanager eyez-agent
```

For Windows:

1. Go to your applications. There are multiple locations in Windows from which to access your applications. To learn more, refer to the [Microsoft documentation](https://support.microsoft.com/en-us/windows/uninstall-or-remove-apps-and-programs-in-windows-4b55f974-2cc6-2d2b-d092-5905080eaf98).
2. Uninstall the **EyezAgent**.
3. Uninstall the **EyezAgentManager**. See image.

[Image: Generate Microsegmentation certificate]

[Image: Agent Groups page]

[Image: Agent software updates]

[Image: Uninstall the agent and agent manager in Windows]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/managing-agent-groups","lastmod":"2026-11-01T23:34Z","nid":"1540845"} -->
## Managing Agent Groups

- Source: https://help.zscaler.com/microsegmentation/managing-agent-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Agent Groups > Managing Agent Groups
- Last modified: 2026-11-01T23:34Z
- Summary: How to manage agent groups for Microsegmentation in the Zscaler Admin Console.

[Agent groups](https://help.zscaler.com/zpa/about-agent-groups) are agents that are manually organized together based on upgrade plan and location. You can make bulk updates to a set of agents by managing a single agent group.

To manage an agent group:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Microsegmentation** >**Agent Groups**.
2. Select an agent group from the list. See image. The selected agent group page appears.
3. Click the **Manage Agent Group**drop-down menu. From the **Manage Agent Group**drop-down menu, you can: See image.
  - Select **Edit** to change the details of the agent group. To learn more, see [Editing Agent Groups](https://help.zscaler.com/zpa/editing-agent-groups).
  - Select **Upgrade Now** to manually upgrade the agent version for the entire group. You can change the update sequence (Serial or Parallel) and strategy (Halt next agent upgrade or Skip to next agent) when initiating an immediate upgrade. After the agents are upgraded, the upgrade schedule continues as configured. To learn more, see [Installing & Upgrading the Agent Manager](https://help.zscaler.com/zpa/installing-upgrading-agent-manager). This option is available for VM agent groups, not Kubernetes Cluster agent groups.
  - Select **Reset Upgrade Status** to refresh the results of the current upgrade status for the agent group. This option is available for VM agent groups, not Kubernetes Cluster agent groups.
  - Click **Delete** to remove the agent group. To learn more, see [Deleting Agent Groups](https://help.zscaler.com/zpa/deleting-agent-groups).

[Image: A list of agent groups]

[Image: Manage Agent Group options]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/reviewing-policy-recommendations-resource-groups","lastmod":"2026-11-01T23:34Z","nid":"1542954"} -->
## Reviewing Policy Recommendations for Resource Groups

- Source: https://help.zscaler.com/microsegmentation/reviewing-policy-recommendations-resource-groups
- Product: Microsegmentation
- Path: Microsegmentation Help > Policy Management > Resource Policies > Reviewing Policy Recommendations for Resource Groups
- Last modified: 2026-11-01T23:34Z
- Summary: How to review policy recommendations for resource groups in the Zscaler Admin Console

Microsegmentation provides policy recommendations for resource groups based on historical network flows. Recommendations are listed on a page within the resource group and organized by managed and unmanaged flows. You can filter managed and unmanaged flows by direction (i.e., inbound and outbound).

If there are no conflicts to resolve, you can review and accept a recommendation as is, quickly applying the new policy and protecting flows. Alternatively, you can edit the recommendation, resolving conflicts as required, before applying it as a new policy or merging it with an existing policy for your organization.

Policy recommendations are generated based on flows observed within the past 14 days.

To review policy recommendations for resource groups:

1. From the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Dashboard** > **Microsegmentation** **Application Map**.
2. On the Application Map, configure the filters as needed, then select a resource group. See image. The drawer for the resource group opens.
3. In the drawer, click **Review Recommended Policies**. See image. The policy recommendations page for the resource group appears.
4. Choose to view recommendations in the **Managed**or **Unmanaged** tabs and filter the recommendations by **Inbound** or **Outbound** flows. See image.
5. Review policy recommendations:
  1. If the policy recommendation has no conflicts, the **Accept** toggle is enabled. To apply the policy:
    1. Click **Review Policies**. See image. A drawer opens. You can edit the policy name. See image.
    2. Click **Save**.
  2. If the policy recommendation has conflicts, see Resolving Conflicts in Policy Recommendations. See image.

## Resolving Conflicts in Policy Recommendations

Policy recommendations can have two types of conflicts—port conflicts and policy conflicts—that you must resolve in order to accept the recommendation and apply the policy.

- A port conflict means the port is not mapped to an application in the [Application Catalog](https://help.zscaler.com/zpa/about-application-catalog-microsegmentation) and must be defined in the recommended policy. Optionally, you can add the port as a manual port in the recommended policy.
- A policy conflict means the source and destination resource groups in the recommended policy are used in an existing policy. You can either create the recommended policy as a new policy or merge it into an existing policy.

A single policy recommendation can have both types of conflicts, as shown in the following steps:

To resolve conflicts in a policy recommendation:

1. Click the **Resolve Conflicts** icon for the policy recommendation. See image. A drawer opens.
2. On the **Port Conflicts** tab, select the application from the [Application Catalog](https://help.zscaler.com/zpa/about-application-catalog-microsegmentation) that should be used by the port for the policy, or optionally add the port as a manual port, resolving the overlap. See image.
3. Click the **Policy Conflicts** tab, select **Create as new policy** or **Merge into existing policy** to resolve the overlap. See image. If you select **Merge into existing policy**, details of the existing policy are displayed. See image.
4. Click **Update**. You are redirected to the policy recommendations page for the resource group. If you selected **Create as new policy**, the **Accept** toggle is enabled. If you selected **Merge into existing policy**, the **Accept** toggle is enabled and shows **Merged**. You have the options to view the flows protected by the policy as well as edit or revert the policy recommendation. See image.
5. Click **Review Policies**. See image. A drawer opens. You can edit the policy name in the **New Policies** or **Existing Policies** tabs, depending on your resolution, as needed. See image.
6. Click **Save**. The policy is applied, and you are redirected to the Application Map. You can view the newly applied policy on the [Resource Policies](https://help.zscaler.com/zpa/about-microsegmentation-policies) page.

[Image: A resource group selected on Microsegmentation's Application Map]

[Image: Review Recommended Policies button for a resource group]

[Image: Policy recommendations for a resource group]

[Image: An accepted policy recommendation for a resource group]

[Image: Review a new policy to be applied]

[Image: A policy recommendation with port and policy conflicts]

[Image: The Resolve Conflict icon for the policy recommendation]

[Image: Resolving a port conflict in the policy recommendation]

[Image: Resolving a policy conflict in a policy recommendation]

[Image: Merging a policy recommendation into an existing policy to resolve the conflict]

[Image: A merged policy recommendation with options to view protected flows, edit the policy recommendation, or revert it]

[Image: Review Policies button]

[Image: Review an existing policy to be applied]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/supported-versions-os-compatibility-microsegmentation","lastmod":"2026-11-01T23:33Z","nid":"1531938"} -->
## Supported Versions & OS Compatibility for Microsegmentation

- Source: https://help.zscaler.com/microsegmentation/supported-versions-os-compatibility-microsegmentation
- Product: Microsegmentation
- Path: Microsegmentation Help > Supported Versions & OS Compatibility for Microsegmentation
- Last modified: 2026-11-01T23:33Z
- Summary: Information on version compatibility and operating systems that a user needs to deploy to use agents for Microsegmentation.

Before configuring [Microsegmentation policies](https://help.zscaler.com/zpa/about-microsegmentation-policies), you must configure and deploy agents to your physical or virtual machine (VM) based on your operating system (OS).

To learn more, see [About Agents](https://help.zscaler.com/zpa/about-agents), [Configuring Agent Groups](https://help.zscaler.com/zpa/configuring-agent-groups), and [Installing & Upgrading the Agent Manager](https://help.zscaler.com/zpa/installing-upgrading-agent-manager).

Microsegmentation only supports the x86_64 CPU architecture, not ARM64.

## Minimum OS Version Compatibility

Each OS requires a specific compatible version to configure Microsegmentation. Additionally, a minimum required agent version is listed for the OS, if applicable.

| OS | Version | Minimum Required Agent Version |
| --- | --- | --- |
| Amazon Linux | AL2 AL 2023 | N/A |
| Red Hat Enterprise Linux (RHEL) Distribution | RHEL 7.9 (A minimum kernel version of 3.10.0-1160.160.1.el7.x86_64 is required.) RHEL 8 RHEL 9 RHEL 10 | N/A |
| SUSE Linux Enterprise | SLES 15 SLES 16 | Agent version 1.11.1; Agent manager version 1.11.1 |
| Ubuntu Distribution | 16.04.7+ LTS 18.04 LTS 20.04 LTS 22.04 LTS 24.04 LTS 26.04 LTS | N/A |
| Windows | Server 2012 R2 Server 2016 Server 2019 Server 2022 Server 2025 | N/A |

## Supported Platforms for Kubernetes Clusters

You can use Kubernetes clusters as an alternative option to VMs when configuring agent groups for Microsegmentation. The following platforms are supported:

- Amazon Elastic Kubernetes Service (Amazon EKS)
- Azure Kubernetes Solution (AKS)
- Google Kubernetes Engine (GKE)

Other container runtimes, such as Docker, are not supported.

To learn more, see [About Kubernetes Clusters for Microsegmentation](https://help.zscaler.com/zpa/about-kubernetes-clusters-microsegmentation).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/understanding-anti-tamper-protection-microsegmentation","lastmod":"2026-11-01T23:33Z","nid":"1538752"} -->
## Understanding Anti-Tamper Protection for Microsegmentation

- Source: https://help.zscaler.com/microsegmentation/understanding-anti-tamper-protection-microsegmentation
- Product: Microsegmentation
- Path: Microsegmentation Help > Agent Management > Understanding Anti-Tamper Protection for Microsegmentation
- Last modified: 2026-11-01T23:33Z
- Summary: Information about anti-tamper agent protection for Microsegmentation.

Agent version 1.12.2 is the minimum-supported version for the anti-tamper protection feature.

Anti-tampering protection for Microsegmentation ensures that agent functionality, such as reporting, enforcement, upgrades, etc., can't be maliciously disabled or altered. This includes alterations attempted by an untrusted actor running with admin privileges.

Microsegmentation uses Zscaler Endpoint Protection (ZEP) integration to implement the anti-tamper functionality. The process uses a zero trust approach to prevent actions even if they seem to be made by an admin. ZEP is a solution based on configurable access to files, registry, and process objects. It comes with a simple AT-control API (DLL), that is permissible only for properly signed processes. It installs as a part of the Zscaler Client Connector installation, but is available as a standalone option (ZEPInstaller.exe). This feature supports Windows Server 2022/2025, Linux systems Ubuntu/Debian kernel 5.7 and later, and RHEL-like kernel 4.18.0-372 and later.

After an anti-malware service is launched as protected, other non-protected processes (and even admins) aren't able to stop the service. In the case of updates to the service binaries, the anti-malware service must receive a callback from the installer to stop itself so that it can be serviced. After the service is stopped, the anti-malware installer can perform upgrades.

Agent anti-tamper notifications are logged within the [Agent Telemetry](https://help.zscaler.com/zpa/about-agent-telemetry) page.

For enabling anti-tamper protection on agents and agent groups, see [Enabling Anti-Tamper Protection for Microsegmentation](https://help.zscaler.com/zpa/enabling-anti-tamper-protection-microsegmentation).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/viewing-agent-dashboard","lastmod":"2026-11-01T23:33Z","nid":"1498186"} -->
## Viewing the Agent Dashboard

- Source: https://help.zscaler.com/microsegmentation/viewing-agent-dashboard
- Product: Microsegmentation
- Path: Microsegmentation Help > Dashboard > Viewing the Agent Dashboard
- Last modified: 2026-11-01T23:33Z
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

<!-- ZS-ARTICLE {"url":"/microsegmentation/viewing-application-map","lastmod":"2026-11-01T23:33Z","nid":"1534383"} -->
## Viewing the Application Map

- Source: https://help.zscaler.com/microsegmentation/viewing-application-map
- Product: Microsegmentation
- Path: Microsegmentation Help > Dashboard > Viewing the Application Map
- Last modified: 2026-11-01T23:33Z
- Summary: How to view the Application Map for Microsegmentation in the Zscaler Admin Console.

The Application Map visualizes network data in your organization. It provides a high-level view of how resource groups and their connected resources are performing, as well as options to view each group in detail.

To view the Application Map, from the [navigation menu](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal), go to **Zero Trust Cloud** > **Dashboard** > **Microsegmentation Application Map**.

By default, the Application Map displays the first resource group in alphabetical order. Use the Resource Group filter to select another group or reset it to show all resource groups. You can use additional filters to view data about your organization's resource groups or VPCs/VNETs within a specific timeframe and AppZone. You can use the search function to quickly locate a specific resource group on the map.

[Image: Filter and adjust the Application Map]

The Application Map provides information about the following:

- Managed Resource Groups
- Unmanaged Resource Groups
- Unmapped Resource Groups
- Public Internet
- Private Infrastructure
- VPC/VNET

You can click a resource group to open a drawer containing its details. Additionally, when you click a resource group, the map displays information about the group's managed and unmanaged flows, and if applicable, provides the option to review policy recommendations for the resource group based on those flows.

[Image: Resource group details on the Application Map]

To learn more about reviewing policy recommendations for resource groups, see [About Policy Recommendations for Resource Groups](https://help.zscaler.com/zpa/about-policy-recommendations-resource-groups) and [Reviewing Policy Recommendations for Resource Groups](https://help.zscaler.com/zpa/reviewing-policy-recommendations-resource-groups).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/microsegmentation/viewing-flow-dashboard","lastmod":"2026-11-01T23:33Z","nid":"1498176"} -->
## Viewing the Flow Dashboard

- Source: https://help.zscaler.com/microsegmentation/viewing-flow-dashboard
- Product: Microsegmentation
- Path: Microsegmentation Help > Dashboard > Viewing the Flow Dashboard
- Last modified: 2026-11-01T23:33Z
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

<!-- ZS-ARTICLE {"url":"/microsegmentation/viewing-overview-dashboard","lastmod":"2026-11-01T23:33Z","nid":"1531939"} -->
## Viewing the Overview Dashboard

- Source: https://help.zscaler.com/microsegmentation/viewing-overview-dashboard
- Product: Microsegmentation
- Path: Microsegmentation Help > Dashboard > Viewing the Overview Dashboard
- Last modified: 2026-11-01T23:33Z
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

<!-- ZS-ARTICLE {"url":"/microsegmentation/what-is-microsegmentation","lastmod":"2026-11-01T23:33Z","nid":"1531937"} -->
## What Is Microsegmentation?

- Source: https://help.zscaler.com/microsegmentation/what-is-microsegmentation
- Product: Microsegmentation
- Path: Microsegmentation Help > What Is Microsegmentation?
- Last modified: 2026-11-01T23:33Z
- Summary: Information about Microsegmentation in the Zscaler Admin Console.

Zscaler Microsegmentation is a platform within the Zscaler Admin Console that provides the capability to visualize traffic flows within private applications and segment them on a fractional level, reducing the attack surface and preventing lateral movement of any threats on your network. It is a multi-tenant Software as a Service solution optimized for security, reliability, and scale, using a system divided into the Zscaler cloud and deployed agents. The Zscaler cloud and agents work together to collect and analyze application flow and telemetry data and also monitor the health of all managed systems.

Microsegmentation agents and Zscaler Client Connector are not compatible and should not be installed on the same host.

The deployed agents sit on Windows or Linux hosts in your environment and collect information about application activity. Agents are deployed to your servers, whether they are virtual or physical, cloud based, or in your data center. The agents are responsible for downloading the latest access policies from the Zscaler cloud and translating them to rules that are specific to local OS enforcement points (e.g., Windows Filtering Platform and Linux nftables). You can create [Agent Groups](https://help.zscaler.com/zpa/configuring-agent-groups), [Resource Groups](https://help.zscaler.com/zpa/configuring-resource-groups), and [AppZones](https://help.zscaler.com/zpa/configuring-appzones) that group together specific machines and their applications that you want to monitor for certain data flows.

The backend application is hosted and managed by the Zscaler cloud and is available across the US region. The data collection is localized to the region of choice of the administrator, and the data retention cycle is a 14-day rolling period. You can deploy the agent to resources in any region. The installed agents operate in continuous mode.

If you want Microsegmentation provisioned for your organization, contact your Zscaler Account team.
<!-- /ZS-ARTICLE -->
