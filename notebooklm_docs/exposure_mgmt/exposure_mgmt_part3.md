# Zscaler Help — Risk & Exposure Management (part 3)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-10 01:47 UTC
Articles in this file: 8

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

<!-- ZS-ARTICLE {"url":"/uvm/viewing-zscaler-secops-status-board","lastmod":"2025-08-25T22:54Z","nid":"1527821"} -->
## Viewing the Zscaler SecOps Status Board

- Source: https://help.zscaler.com/uvm/viewing-zscaler-secops-status-board
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Viewing the Zscaler SecOps Status Board
- Last modified: 2025-08-25T22:54Z
- Summary: How to view information in the Zscaler SecOps status board.

To view the Zscaler Security Operations (SecOps) status board, refer to the [status page](https://avalorstatus.statuspage.io/). The status board presents historic and current incidents in the SecOps platform.

On the status board, you can perform the following actions:

- Report Issues
- Subscribe to Updates and Feeds
- View Current Status
- View Incident History and Historical Uptime

If you're a SecOps user and you've encountered an issue with the platform or one of its apps, you can report it to the SecOps team.

To report an issue, click **Submit a request** at the top right of the page.

You can subscribe to incident updates by email, phone, and Slack, and to the status board's Atom and RSS feeds.

To subscribe to updates:

1. Click **Subscribe to Updates**at the top right of the page.
2. Click the icon of your preferred update method, and follow the onscreen instructions. See image.

The SecOps platform's current status presents incidents in the last 90 days by region (US, EU), and each region is broken down into additional components:

- Platform
- Data Ingestion
- Query Engine
- UVM App
- Detections App (only for US)

The SecOps platform reports past incidents and historical uptime.

## Incidents

You can view past incident descriptions in 90-day increments.

To view incident history, choose one of the following methods:

- On the **Current Status** page, scroll to the bottom of the page and click **Incident History**.
- On the **Uptime** page, click the **Incident**tab.

## Uptime

You can view historical uptime in 90-day increments and by one of the following components:

- Platform
- Data Ingestion
- Query Engine
- UVM App
- Detections App (only for US)

To view uptime history, choose one of the following methods:

- On the **Current Status** page, click **View historical uptime** in the top right.
- On the **Incidents** page, click the **Uptime**tab.
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

---

<!-- ZS-ARTICLE {"url":"/uvm/what-zscaler-security-operations","lastmod":"2026-05-22T07:06Z","nid":"1531027"} -->
## What Is Zscaler Security Operations?

- Source: https://help.zscaler.com/uvm/what-zscaler-security-operations
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > What Is Zscaler Security Operations?
- Last modified: 2026-05-22T07:06Z
- Summary: Information on the Zscaler Security Operations (SecOps) platform.

Zscaler Security Operations (SecOps) is a unified approach to security that integrates proactive and reactive measures to enhance an organization's overall security posture. It focuses on bridging the gap between traditional exposure management and threat management, creating a cohesive strategy for identifying, prioritizing, and responding to cyber risks. This comprehensive view helps accelerate incident response, improve operational efficiency, and simplify compliance efforts.

## Key Features and Benefits

Zscaler SecOps provides the following benefits and enables you to:

- Integrate Zscaler SecOps and third-party data for rich context, providing a comprehensive view of your security landscape.
- Offer automated workflows for remediation and updates.
- Provide real-time visibility into security posture and metrics.

There are two applications in the Zscaler SecOps platform:

- Asset Exposure Management (AEM) enables organizations to collect and manage asset data from various sources to track asset inventory and coverage. It allows organizations to understand their attack surface, create policies, and track and remediate policy violations to reduce overall risk.
- Unified Vulnerability Management (UVM) provides a single platform for managing vulnerabilities, simplifying the process of identifying and remediating security risks.

The Zscaler SecOps platform natively integrates External Attack Surface Management (EASM) capabilities to automatically discover, inventory, and monitor internet-facing assets and detect their associated risk findings. This scanned asset and finding information is automatically merged with the context-rich data and capabilities of AEM and UVM to provide comprehensive asset visibility and enriched security insights, helping organizations secure their assets and remediate risks.

## How Does Zscaler SecOps Work?

Zscaler SecOps operates on a robust data fabric for security that centralizes and transforms disparate security data. Supported by machine learning and AI, the fabric harmonizes, deduplicates, correlates, and enriches information from various sources, including Zscaler telemetry and third-party tools. The clean, contextualized data leads to more accurate and informative security outcomes. These insights power exposure management solutions, such as AEM and UVM, enabling organizations to understand and address asset risk, prioritize vulnerabilities, and effectively quantify overall cyber risk.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/zscaler-secops-public-ip-addresses","lastmod":"2025-08-17T08:31Z","nid":"1527781"} -->
## Zscaler SecOps Public IP Addresses

- Source: https://help.zscaler.com/uvm/zscaler-secops-public-ip-addresses
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Zscaler SecOps Public IP Addresses
- Last modified: 2025-08-17T08:31Z
- Summary: A list of public IP addresses used by the Zscaler Security Operations platform and its applications (e.g., UVM, AEM) for allowlisting when configuring integrations with the platform.

To ensure seamless connectivity and uninterrupted access to essential resources, you can allowlist the public IP addresses used by outgoing traffic from the Zscaler Security Operations (SecOps) platform and its applications (e.g., UVM, AEM). These IP addresses serve as the source for all egress traffic originating from the Zscaler tenant.

Add the following IP addresses associated with your instance's region to your firewall's allowlist to ensure uninterrupted access to the required resources.

| **Region** | **IP Addresses** |
| --- | --- |
| **US** | 3.137.47.190/32 3.15.110.62/32 3.129.232.141/32 |
| **EU** | The recommended IP addresses listed in [Zscaler Hub IP Addresses](https://config.zscaler.com/zscalertwo.net/hubs) |
<!-- /ZS-ARTICLE -->
