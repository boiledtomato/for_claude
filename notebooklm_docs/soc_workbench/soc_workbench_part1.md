# Zscaler Help — SOC Workbench (part 1)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-10 01:47 UTC
Articles in this file: 61

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-agentic-soc","lastmod":"2026-07-27T10:27Z","nid":"1540781"} -->
## About Agentic SOC

- Source: https://help.zscaler.com/soc-workbench/about-agentic-soc
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > About Agentic SOC
- Last modified: 2026-07-27T10:27Z
- Summary: Information on the Agentic SOC page in the SOC Workbench application of the SecOps platform.

The Agentic SOC page is designed as a starting point in the Zscaler SOC Workbench Portal, providing a quick visual representation of your organization's overall security posture. You can instantly see which sources are responsible for alerts, the total number of alerts, the number of incidents created from alerts, and the triaged incidents.

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-alerts","lastmod":"2026-06-22T21:06Z","nid":"1534212"} -->
## About Alerts

- Source: https://help.zscaler.com/soc-workbench/about-alerts
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > About Alerts
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-assets","lastmod":"2026-06-22T21:06Z","nid":"1539869"} -->
## About Assets

- Source: https://help.zscaler.com/soc-workbench/about-assets
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > About Assets
- Last modified: 2026-06-22T21:06Z
- Summary: Information on the Assets page in the SOC Workbench application of the SecOps platform.

The Assets page provides a centralized view of the assets in SOC Workbench.Each asset represents a single entity in your environment, unified (i.e., merged) and enriched with information from multiple sources. On this page, you can explore asset details and statuses, view the sources and records from which it was merged, view the findings it contains, and view the tickets it's related to.

The Assets page provides the following benefits and enables you to:

- Access and organize assets using system-saved views, filters, grouping options, and customizable table columns to focus on specific scenarios.
- Explore detailed information about each asset, including associated tickets and findings, risk scores, ownership, and the sources that the assets were detected on.
- Understand how each asset was merged from multiple sources into a unified entity.

## About the Assets Page

On the Assets page (SOC Workbench > Assets), you can do the following:

1. Select from system-saved views, or views [you previously saved](https://help.zscaler.com/uvm/creating-managing-saved-views).
  - List of System-Saved Views
2. Search for specific assets by entering keywords in the search bar.
3. [Save your view](https://help.zscaler.com/uvm/creating-managing-saved-views) for quick access after making adjustments to it (e.g., applying filters, adjusting columns, or grouping).
4. [Filter](https://help.zscaler.com/soc-workbench/using-filters) assets by **Sources, Type, Site,**or**Operating System**.
5. Explore the **Overview**charts to gain high-level insights into the assets and their risk level in your environment. The charts are adjusted by the selected view and filters.
  - **Number of Assets by Risk Score**: Displays the number of assets in the different risk score ranges (in increments of 0.5). The X-axis represents the max severity score of active findings related to the asset, and the colors represent the risk category. You can hover over the bars to view the number of assets and the exact score range.
  - **Asset Count by Type**: Presents asset count categorized by asset type, displaying the 5 most frequently occurring types.
  - **Asset Count by Operating System**: Displays the number of assets categorized by OS, displaying the 5 most frequently occurring types.
6. [Group assets](https://help.zscaler.com/soc-workbench/grouping-data-entity-pages) by fields such as **Asset State**, **Asset ID**, or **Asset Owner ID**.
7. Refresh the page to reflect the most current information.
8. Export the list of assets and their associated details as a CSV file.
9. [Modify the columns displayed in the table.](https://help.zscaler.com/soc-workbench/managing-table-columns)
10. Select some or all assets on the page.
11. When the default **Active**saved view is selected, you can see the following details for each asset (or you can click an asset to open individual asset drawers):
  - **ID**: The asset's ID on the SecOps platform.
  - **Type**: The asset type (e.g., **Windows Workstation**, **Web Application**, **Container Image**).
  - **Name**: The asset's name.
  - **Risk Score**: The risk level of findings associated with the asset. The risk score is initially set by the default [reconciliation function](https://help.zscaler.com/uvm/attribute-reconciliation-default-functions) and reflects the highest severity score among the findings. The default can be customized through [data unification](https://help.zscaler.com/soc-workbench/what-data-unification).
  - **Risk Mass**: The sum of all severity scores of the active findings associated with the asset.
  - **Owner ID**: The unit within the organization assigned to handling the asset.
  - **Sources**: The sources that the information on the asset is retrieved from.
  - **Is Crown** **Jewel**: A Boolean field with values of **TRUE**or **FALSE**indicating whether the asset is defined as a crown jewel asset (i.e., one of your organization's most valuable assets).
  - **Site**: The site that the asset is located on.
  - **First Seen**: The earliest date on which a finding on the asset was first detected.
  - **Last Seen**: The latest date on which a finding on the asset was detected.
  - **Total Findings**: The sum of the active findings per severity.
  - **Tags**: Tags pulled from your sources that include information about the asset that can be [extracted](https://help.zscaler.com/uvm/configuring-field-unification) and used to enrich the asset data.

[Image: About Assets operational view]

The **Assets** page includes system views with predefined filter selections, providing quick access to common data scopes:

- **Active**: All active assets (i.e., all assets ingested into the account from your sources that have not been aged yet). This is the default view.
- **Vulnerable**: All active assets that have at least one active finding.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-dashboard","lastmod":"2026-06-22T21:06Z","nid":"1534209"} -->
## About the Dashboard

- Source: https://help.zscaler.com/soc-workbench/about-dashboard
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > About the Dashboard
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-incidents","lastmod":"2026-06-22T21:06Z","nid":"1534211"} -->
## About Incidents

- Source: https://help.zscaler.com/soc-workbench/about-incidents
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > About Incidents
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/about-users","lastmod":"2026-06-22T21:06Z","nid":"1539870"} -->
## About Users

- Source: https://help.zscaler.com/soc-workbench/about-users
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > About Users
- Last modified: 2026-06-22T21:06Z
- Summary: Information on the Users page in the SOC Workbench application of the SecOps platform.

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
11. View the list of users. Click a user to [view detailed information](https://help.zscaler.com/soc-workbench/viewing-user-details). For each user in the list, you can see:
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform","lastmod":"2026-03-28T05:22Z","nid":"1539525"} -->
## Accessing and Navigating the Zscaler Security Operations (SecOps) Platform

- Source: https://help.zscaler.com/soc-workbench/accessing-and-navigating-zscaler-security-operations-secops-platform
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Admin Portal > Accessing and Navigating the Zscaler Security Operations (SecOps) Platform
- Last modified: 2026-03-28T05:22Z
- Summary: How to sign in to and navigate the Zscaler SecOps platform.

This article covers the following topics to help you get started with the Security Operations Platform (SecOps Platform):

- Signing In to the SecOps Platform
- Accessing the Admin Settings
- Navigating within the SecOps Platform

After your organization is provisioned for the SecOps Platform, you receive an email with a username (this is your registered email address) and a password to sign in to the SecOps Platform. Go to the login page provided in the email (app.avalor.io).

If your account is configured without single sign-on (SSO):

1. Enter your email address.
2. Click **Continue**. The **Login**page appears.
3. Enter your email address and password.
4. Click **Sign In**.

If your account is configured to use SSO:

1. Enter your email address.
2. Click **Continue**. You are redirected to your IdP's login page.
3. Log in with your credentials. If you are logged in successfully, you are redirected to the SecOps Platform.

The SecOps Platform account admin can configure SSO, user management, and email notifications for data processing failures in the account.

To access the Account Settings:

1. Click the **Profile**menu in the top-right corner of the page.
2. Click **Account Settings**. See image.
3. In the left-side navigation:
  - Click **Settings**to configure and manage SSO (e.g., Okta).
  - Click **User Management**to configure and manage [users](https://help.zscaler.com/uvm/creating-managing-users).
  - Click **Permissions**to configure and manage [roles permissions](https://help.zscaler.com/uvm/managing-system-and-custom-roles), [content permissions](https://help.zscaler.com/uvm/creating-managing-content-permissions) and [email notifications](https://help.zscaler.com/uvm/enabling-email-notifications-source-run-failures).

[Image: Account Settings page in the Security Operations Platform]

The SecOps Platform has the following items in the top navigation bar:

- Search
- Apps
- Explore
- Configure
- Knowledge Center
- Support
- Profile Menu

You can click the Zscaler logo in the top left of the navigation bar to return to the main dashboard.

The left-side navigation changes dynamically based on your current location within the SecOps Platform, and can be collapsed or expanded. To set the mode, hover over the menu and click the arrow icon.

[Image: Security Operations Platform top navigation bar]

You can search to locate pages, features, or tools within the SecOps Platform. Press `Ctrl+K` (Windows) or `Cmd+K` (Mac) to open the search bar, then enter keywords to find relevant results.

Click the **Profile**menu to access various settings and account management options.

To learn more about each component, see the following:

Profile Settings >

- Personal Info: Update personal details associated with your account.
- [Content Permissions](https://help.zscaler.com/uvm/creating-managing-content-permissions)
- [Email Notifications](https://help.zscaler.com/uvm/enabling-email-notifications-source-run-failures)

Account Settings >

- [Authenticate](https://help.zscaler.com/uvm/generating-saml-details)
- [AI Capabilities](https://help.zscaler.com/uvm/managing-ai-capabilities-secops-platform)
- [Audit Logs Export](https://help.zscaler.com/uvm/viewing-audit-logs)
- [Zscaler Gateway](https://help.zscaler.com/uvm/configuring-zscaler-secops-platform-gateway)
- [User Management](https://help.zscaler.com/uvm/creating-managing-users)
- Permissions >
  - [Roles](https://help.zscaler.com/uvm/managing-system-and-custom-roles)
  - [Content](https://help.zscaler.com/uvm/creating-managing-content-permissions)

Click **Log Out**to log out of the SecOps Platform. You can enable automatic logout after a period of inactivity in Account Settings > Authenticate, after an email domain is added.

Click the **Contact Support**icon for assistance with technical issues, account configurations, or general inquiries regarding the SecOps Platform.

Click the **Knowledge Center**icon to access the SecOps Platform's knowledge base directly within the interface, featuring resources such as configuration guides, deployment guides, and FAQs.

Click **Configure**to access settings for customizing and managing the data in the SecOps Platform.

To learn more about each component, see the following:

- [Sources](https://help.zscaler.com/uvm/creating-data-sources)
- [Authentications](https://help.zscaler.com/uvm/configuring-authentications)
- [Outegrations](https://help.zscaler.com/uvm/creating-outegrations)
- [Unification](https://help.zscaler.com/uvm/what-data-unification) >
  - [Entities](https://help.zscaler.com/uvm/configuring-entity-unification)
  - [Fields](https://help.zscaler.com/uvm/configuring-field-unification)
- [Asset Aging](https://help.zscaler.com/uvm/aging-assets)

Click **Explore**to access various data analysis and visualization tools.

To learn more about each component, see the following:

- [Entity Explorer](https://help.zscaler.com/uvm/using-entity-explorer)
- [Logs](https://help.zscaler.com/uvm/building-queries-searching-logs)
- [Reports](https://help.zscaler.com/uvm/creating-reports)
- [Dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards)

Click the **App Menu**icon to access SecOps applications included in your license, such as Unified Vulnerability Management (UVM) and Asset Exposure Management (AEM).

To pin an app to the top navigation bar, click the **App Menu** icon, then click the **Add to Favorite** icon ([Image: app menu add to favorite star icon]) next to the app.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-alert-scores","lastmod":"2026-06-22T21:06Z","nid":"1534213"} -->
## Configuring Alert Scores

- Source: https://help.zscaler.com/soc-workbench/configuring-alert-scores
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Configuring Alert Scores
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-azure-blob-connector","lastmod":"2026-04-16T12:17Z","nid":"1539811"} -->
## Configuring the Azure Blob Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-azure-blob-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Azure Blob Connector
- Last modified: 2026-04-16T12:17Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-azure-cloud-assets-connector","lastmod":"2026-04-16T12:21Z","nid":"1539812"} -->
## Configuring the Azure Cloud Assets Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-azure-cloud-assets-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Azure Cloud Assets Connector
- Last modified: 2026-04-16T12:21Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-crowdstrike-connector","lastmod":"2026-04-16T12:22Z","nid":"1539813"} -->
## Configuring the CrowdStrike Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-crowdstrike-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the CrowdStrike Connector
- Last modified: 2026-04-16T12:22Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-crowdstrike-identity-protection-connector","lastmod":"2026-04-16T12:23Z","nid":"1539814"} -->
## Configuring the CrowdStrike Identity Protection Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-crowdstrike-identity-protection-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the CrowdStrike Identity Protection Connector
- Last modified: 2026-04-16T12:23Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-incident-rules","lastmod":"2026-06-22T21:06Z","nid":"1534214"} -->
## Configuring Incident Rules

- Source: https://help.zscaler.com/soc-workbench/configuring-incident-rules
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Configuring Incident Rules
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-jira-outegration","lastmod":"2026-04-16T12:35Z","nid":"1539824"} -->
## Configuring the Jira Outegration

- Source: https://help.zscaler.com/soc-workbench/configuring-jira-outegration
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Outegrations > Outegration Configuration Guides > Configuring the Jira Outegration
- Last modified: 2026-04-16T12:35Z
- Summary: How to configure the Jira outegration for disptaching tickets to the Jira Data Center or Jira Cloud work management systems.

The Jira outegration is used to dispatch tickets from the Zscaler Security Operations (SecOps) platform applications (e.g., UVM) to your Jira project, creating a Jira issue that can then be tracked, assigned, and managed by your remediation teams working with Jira.

This article is a step-by-step guide to setting up the Jira work management outegration. The process involves setting up authentication, outegration visibility in the platform, outegration mapping, and when relevant, configuring a Jira webhook to enable bidirectional synchronization.

Each Jira issue type (e.g., Bug, Task, Feature) requires a separate outegration configuration.

## Prerequisites

Before getting started, identify the Jira platform your organization uses: Jira Cloud or Jira Data Center. While the setup process for both Jira outegrations is mostly similar, Jira Data Center users must first set up a gateway and then proceed to follow the standard Jira outegration setup process. To learn more, see [Configuring the Zscaler SecOps Platform Gateway](https://help.zscaler.com/uvm/configuring-zscaler-secops-platform-gateway).

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

Before proceeding, make sure a Zscaler Gateway has been configured. To learn more, see [Configuring the Zscaler SecOps Platform Gateway](https://help.zscaler.com/uvm/configuring-zscaler-secops-platform-gateway).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-jira-outegration-webhook","lastmod":"2026-04-16T12:46Z","nid":"1539825"} -->
## Configuring the Jira Outegration Webhook

- Source: https://help.zscaler.com/soc-workbench/configuring-jira-outegration-webhook
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Outegrations > Outegration Configuration Guides > Configuring the Jira Outegration Webhook
- Last modified: 2026-04-16T12:46Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-microsoft-defender-cloud-findings-connector","lastmod":"2026-04-16T12:24Z","nid":"1539815"} -->
## Configuring the Microsoft Defender for Cloud Findings Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-microsoft-defender-cloud-findings-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Microsoft Defender for Cloud Findings Connector
- Last modified: 2026-04-16T12:24Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-microsoft-defender-endpoint-connector","lastmod":"2026-04-16T12:25Z","nid":"1539816"} -->
## Configuring the Microsoft Defender for Endpoint Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-microsoft-defender-endpoint-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Microsoft Defender for Endpoint Connector
- Last modified: 2026-04-16T12:25Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-microsoft-entra-id-connector","lastmod":"2026-04-16T12:26Z","nid":"1539817"} -->
## Configuring the Microsoft Entra ID Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-microsoft-entra-id-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Microsoft Entra ID Connector
- Last modified: 2026-04-16T12:26Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

You can locate the client ID on the Overview page. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/healthcare-apis/register-application).

You can create a client secret on the App registrations > Certificates & secrets page. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/how-to-add-credentials?tabs=client-secret).

You can locate the tenant ID on the Properties tab of the Overview page. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/fundamentals/how-to-find-tenant).

[Image: Microsoft Entra ID connector tiles]

[Image: The Microsoft Entra ID Authentication window displaying the Name, Client Id, Client Secret, and Tenant Id fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-microsoft-entra-id-sso","lastmod":"2026-04-14T14:24Z","nid":"1539762"} -->
## Configuring Microsoft Entra ID SSO

- Source: https://help.zscaler.com/soc-workbench/configuring-microsoft-entra-id-sso
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Account Management > Admin Configuration and Deployment > Configuring Microsoft Entra ID SSO
- Last modified: 2026-04-14T14:24Z
- Summary: How to configure Microsoft Entra ID SSO account authentication.

You can configure Microsoft Entra ID (formerly Azure AD) single sign-on (SSO) as the authentication method for the Zscaler Security Operations (SecOps) platform, allowing users to sign in through the Microsoft Entra ID SSO provider, instead of using a username and password. To do this, you can specify a domain, and users with email addresses matching that domain are redirected to authenticate through Microsoft Entra ID. Each user must have an account with the same email address in both the platform and Microsoft Entra ID. After SSO is enabled for a domain, it becomes the only authentication method for the platform.

To configure Microsoft Entra ID SSO, complete the following steps:

- Step 1: Generate SAML Details
- Step 2: Register an Application in Microsoft Entra ID
- Step 3: Share Metadata With Zscaler

To set up SSO account authentication, you must generate a SAML Entity ID and Reply URL within the SecOps platform. To learn more, see [Generating SAML Details](https://help.zscaler.com/uvm/generating-saml-details).

After generating SAML details (Entity ID and Reply URL), you can proceed to registering a Microsoft Entra ID application and assigning users to the new application.

To register a Microsoft Entra ID application:

1. Sign in to the Azure portal.
2. Select the **Microsoft Entra ID**service.
3. In the left-side navigation, go to **Manage** > **Enterprise applications**.
4. Click **New application**.
5. On the **Browse Microsoft Entra Gallery** page, click**Create your own application**. The **Create your own application** drawer appears.
6. In the **Create your own application** drawer:
  1. **Name**: Enter a name for the application.
  2. **What are you looking to do with your application?**: Select **Integrate any other application you don’t find in the gallery (Non-gallery)**.
  3. Click **Create** to complete the initial app registration.
7. The app registration's **Overview** page appears.
8. In the **Getting Started** section, locate the**Set up single sign on** tile and click **Get Started**. The **Single sign-on** page appears.
9. On the **Single sign-on** page, select **SAML**as the single sign-on method. The **SAML-based Sign-on** page appears.
10. On the **Basic SAML Configuration** tile, click **Edit**. See image.
11. In the **Basic SAML Configuration** drawer:
  1. **Identifier (Entity ID)**: Click **Add Identifier** and paste the **Entity ID** copied from Zscaler.
  2. **Reply URL (Assertion Consumer Service URL)**: Click **Add reply URL** and paste the **Reply URL** copied from Zscaler.
  3. **Sign on URL**: (Optional) Enter `https://app.avalor.io`. See image.
12. Click **Save**.
13. On the **Attributes & Claims** tile, click **Edit**.
14. Click **Add a group claim**.
15. In the **Group Claims** drawer:
  1. Select **Groups assigned to the application**.
  2. **Source Attribute**: Select **Group ID**from the drop-down menu. See image.
16. Click **Save**.
17. Close the**Attributes & Claims** page to return to the **Set up Single Sign-on with SAML**page.

After registering the Microsoft Entra ID application, you can assign users to the new app.

To assign users to the app:

1. In the left-side navigation, click **Users and groups.** See image.
2. Click **Add user/group**.
3. Add the relevant user in your organization.
4. Click **Assign**.

After creating a Microsoft Entra ID app, share XML metadata with your Zscaler Account team.

To retrieve the XML metadata:

1. Sign in to the Azure portal, and select the **Microsoft Entra ID**service.
2. Open the application you created.
3. In the left-side navigation, select **Single sign-on**. See image.
4. Scroll down to the **SAML Signing Certificate** section and copy the **App Federation Metadata URL**. See image.

To share metadata with Zscaler:

1. In the SecOps platform, click the **Profile** menu on the top right of the page and select **Account Settings**.
2. In the **Authenticate** section, paste the XML metadata into the **SAML XML MetaData**field. If the Authenticate section is not visible, share the XML metadata with your Zscaler Account team. See image. While a metadata URL is also supported, Zscaler recommends pasting the XML metadata directly.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-okta-sso","lastmod":"2026-04-14T14:26Z","nid":"1539763"} -->
## Configuring Okta SSO

- Source: https://help.zscaler.com/soc-workbench/configuring-okta-sso
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Account Management > Admin Configuration and Deployment > Configuring Okta SSO
- Last modified: 2026-04-14T14:26Z
- Summary: How to configure Okta SSO account authentication.

You can configure Okta single sign-on (SSO) as the authentication method for the Zscaler Security Operations (SecOps) platform, allowing users to sign in through your Okta SSO provider, instead of using a username and password. To do this, you can specify a domain, and users with email addresses matching that domain are redirected to authenticate through Okta. Each user must have an account with the same email address in both the platform and in Okta. After SSO is enabled for a domain, it becomes the only authentication method for the platform.

To configure Okta SSO, complete the following steps:

- Step 1: Generate SAML Details
- Step 2: Create a Bookmark App
- Step 3: Create an App Integration
- Step 4: Share Metadata With Zscaler

To set up SSO account authentication, you must generate a SAML Entity ID and Reply URL within the SecOps platform. To learn more, see [Generating SAML Details](https://help.zscaler.com/uvm/generating-saml-details).

The SecOps platform doesn't natively support identity provider (IdP)-initiated login. Instead, implement the following process using a Bookmark app that redirects to app.avalor.io.

To configure a Bookmark app:

1. Sign in to the Okta Admin Center.
2. Go to the **Applications** page and click **Browse App Catalog.**
3. Search for and add **Bookmark App**. See image.
4. In the **General Settings**section:
  1. **Application label**: Enter a name for the Bookmark app.
  2. **URL**: Enter `https://app.avalor.io?domain=``<Your Org Domain>`, replacing `<Your Org Domain>` with your actual organization domain.
  3. **Application Visibility**: Leave the checkbox unselected so the **Bookmark**app isn't hidden. See image.
  4. Click **Done**.
5. Click the **Edit** icon on the logo to add Zscaler's logo: [Download Logo](https://help.zscaler.com/downloads/uvm/administration/account-management/admin-configuration-and-deployment/configuring-okta-sso/LOGO.png) See image.
6. Click **Done**.

To enable SAML-based authentication with Okta, you need to create and configure a new app integration.

To create an app integration:

1. Sign in to the Okta Admin Console.
2. In the navigation menu, expand **Applications**, and then select **Applications**.
3. Click **Create App Integration**.
4. In the **Create a New Application Integration** window, select **SAML 2.0**as the **Sign on method**, and then click **Create**. See image.
5. On the **Create SAML Integration** page:
  1. On the **General Settings**tab:
    1. **App name**: Enter a name for the app integration.
    2. **App Visibility**: Select **Do not display application icon to users**.
    3. Click **Next**.
  2. On the **Configure SAML**tab:
    1. **Single sign on URL**: Paste the **Reply URL** copied from Zscaler.
    2. **Audience URI (SP Entity ID)**: Paste the **Entity ID** copied from Zscaler.
    3. **Name ID format**: Enter `EmailAddress`.
    4. **Application username**: Select **Okta username**.
    5. In the **Attribute Statements (optional)** section:
      1. **Name:** Enter `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`.
      2. **Value**: Enter `user.email`. See image.
      3. Click **Next**.
  3. On the **Feedback** tab, select **I'm an Okta customer adding an internal app**. See image.
  4. Click **Finish**.
6. Assign users or groups to authenticate using Okta:
  1. Go to the **Assignments** tab of the application you added.
  2. Click **Assign**.
  3. Select **Assign to People**or **Assign to Groups**.
  4. Enter the people or groups that you want to authenticate with the Okta IdP.
  5. Click **Assign**.
  6. Verify the attributes, and click **Save and Go Back**.
7. Click **Done**.

After creating a Bookmark App and an app integration, share XML metadata with your Zscaler Account team. To learn more, refer to the [Okta documentation](https://support.okta.com/help/s/article/Location-to-download-Okta-IDP-XML-metadata-for-a-SAML-app-in-the-new-Admin-User-Interface?language=en_US).

To retrieve the XML metadata:

1. In the Okta console, click the **Sign On** tab of the SAML application.
2. Scroll down and click **View SAML setup instructions**. In the new tab that opens, all the required values are displayed.
3. Copy the metadata from the **Optional** section. See image.

To share metadata with Zscaler:

1. In the SecOps platform, click the **Profile** menu on the top right of the page and select **Account Settings**.
2. In the **Authenticate** section, paste the XML metadata into the **SAML XML MetaData**field. If the Authenticate section is not visible, share the XML metadata with your Zscaler Account team. See image. While a metadata URL is also supported, Zscaler recommends pasting the XML metadata directly.

[Image: Okta Metadata XML]

**[Image: SAML XML Metadata field]**
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-pingfederate-sso","lastmod":"2026-04-14T14:33Z","nid":"1539765"} -->
## Configuring PingFederate SSO

- Source: https://help.zscaler.com/soc-workbench/configuring-pingfederate-sso
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Account Management > Admin Configuration and Deployment > Configuring PingFederate SSO
- Last modified: 2026-04-14T14:33Z
- Summary: How to configure PingFederate SSO account authentication.

You can configure PingFederate single sign-on (SSO) as the authentication method for the Zscaler Security Operations (SecOps) platform, allowing users to sign in through your PingFederate SSO provider, instead of using a username and password. To do this, you can specify a domain, and users with email addresses matching that domain are redirected to authenticate through PingFederate. Each user must have an account with the same email address in both the platform and PingFederate. After SSO is enabled for a domain, it becomes the only authentication method for the platform.

To configure PingFederate SSO, complete the following steps:

- Step 1: Generate SAML Details
- Step 2: Register an Application in PingFederate
- Step 3: Share Metadata With Zscaler

To set up SSO account authentication, you must generate a SAML Entity ID and Reply URL within the SecOps platform. To learn more, see [Generating SAML Details](https://help.zscaler.com/uvm/generating-saml-details).

After generating SAML details (Entity ID and Reply URL), you can register a PingFederate application. The app registration process assumes you already have an IdP Adapter in place. To learn more, refer to the [PingFederate documentation](https://docs.pingidentity.com/integrations/azure/azure_ad_and_office_365_integration_guide/pf_azuread_office365_integration_create_an_idp_adapter.html).

To register a PingFederate application:

1. Sign in to the PingFederate Admin console.
2. Go to **Identity Provider** > **SP Connections**.
3. Click **Create Connection**. See image.
4. On the **Connection Template** step, select **DO NOT USE A TEMPLATE FOR THIS CONNECTION** and click **Next**. See image.
5. On the **Connection Type** step:
  1. Select the **BROWSER SSO PROFILES**checkbox.
  2. Select **SAML 2.0** from the **PROTOCOL** drop-down menu.
  3. Click **Next**. See image.
6. On the **Connection Options**step, select the **BROWSER SSO** checkbox and click **Next**. See image.
7. On the **Import Metadata** step, select **None** for **METADATA** and click **Next**. See image.
8. On the **General Info** step:
  1. **Partner's Entity ID**: Paste the **Entity ID** copied from Zscaler.
  2. **Connection Name**: Enter a name for the application.
  3. **Base URL**: Leaveempty.
  4. Click **Next**.
9. On the **Browser SSO** step, click **Configure Browser SSO**.
  1. On the **SAML Profiles** tab, under **Single Sign-On (SSO) Profiles**, select the **SP-INITIATED SSO**checkbox, and then click **Next**. IdP-initiated SSO is not supported. See image.
  2. On the **Assertion Lifetime** tab, leave the settings as is and click **Next**.
10. On the **Assertion Creation** step, click **Configure Assertion Creation**. Select **STANDARD**, andclick **Next**. See image.
11. On the **Attribute Contract** step, under **Extend the Contract**, enter `email`, and then click **Add**. See image.
12. Click **Next**.
13. On the **Authentication Source Mapping** step, click **Map New Adapter Instance.**
  1. On the **Adapter Instance** tab, select the **Adapter Instance** for this app, and click **Next**.
  2. On the **Mapping Method** tab, leave the settings as is and click **Next**.
  3. On the **Attribute Contract Fulfillment** tab, configure the Attribute Contracts: **SAML SUBJECT** and **email**. For each:
    1. Select **Adapter** from the **Source** drop-down menu.
    2. Select **Email**from the **Value**drop-down menu.
  4. On the **Issuance Criteria** tab, click **Next**.
  5. On the **Summary**tab, review your entries, and then click **Done**.
14. On the **Authentication Source Mapping** step, click **Next**.
  1. On the **Summary** tab, review your entries, and then click **Done**.
15. On the **Assertion Creation** step, click **Next**.
  1. On the**Protocol Settings** tab, click **Configure Protocol Settings**.
  2. On the **Assertion Consumer Service URL** tab, select the **Default**checkbox.
    1. **Binding**: Select **POST** from the drop-down menu.
    2. **EndpointUrl**: Paste the **Reply URL** copied from Zscaler.
    3. Click **Add**, and then click **Next**. See image.
  3. On the **Allowable SAML Bindings** tab, select the **POST**and **REDIRECT**checkboxes and click **Next**. See image.
  4. On the **Signature Policy** tab, select **Always Sign Assertion** and click **Next**.
  5. On the **Encryption Policy** tab, select **None**. Click **Next**.
  6. On the **Summary** tab, review your entries, and then click **Done**.
  7. On the **Protocol Settings** tab, click **Next**.
  8. On the **Summary** tab, review your entries, and then click **Done**.
16. On the **Browser SSO** step, click **Next**.
17. On the **Credentials** step, click **Configure Credentials**, select the signature on the SAML, and click **Next**.
18. On the **Summary** tab, review your entries, and then click **Done**.
19. On the **Credentials** tab, click **Next**.
20. On the **Activation & Summary** step, scroll to the bottom and click **Save**.

After registering the SAML app in PingFederate, you are redirected to the SP Connections page, where you can copy your application's metadata to be used in the next step.

After registering an app in PingFederate, share XML metadata with your Zscaler Account team.

To retrieve the XML metadata:

1. On the **SP Connections**page of the application you registered, click **Select Action** > **Export Metadata**. See image.
2. Select the signing certificate and click **Next**.
3. Scroll to the bottom of the page and click **Export**. The signing certificate file is saved to your computer.
4. Click **Done**.

To share metadata with Zscaler:

1. In the SecOps platform, click the **Profile** menu on the top right of the page and select **Account Settings**.
2. In the **Authenticate** section, paste the XML metadata into the **SAML XML MetaData**field. If the Authenticate section is not visible, share the XML metadata with your Zscaler Account team. See image. While a metadata URL is also supported, Zscaler recommends pasting the XML metadata directly.

**[Image: SAML XML metadata field]**
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-pingone-sso","lastmod":"2026-04-14T14:28Z","nid":"1539764"} -->
## Configuring PingOne SSO

- Source: https://help.zscaler.com/soc-workbench/configuring-pingone-sso
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Account Management > Admin Configuration and Deployment > Configuring PingOne SSO
- Last modified: 2026-04-14T14:28Z
- Summary: How to configure PingOne SSO account authentication.

You can configure PingOne SSO as the authentication method for the Zscaler Security Operations (SecOps) platform, allowing users to sign in through your PingOne SSO provider, instead of using a username and password. To do this, you can specify a domain, and users with email addresses matching that domain are redirected to authenticate through PingOne. Each user must have an account with the same email address in both the platform and PingOne. After SSO is enabled for a domain, it becomes the only authentication method for the platform.

To configure PingOne SSO, complete the following steps:

- Step 1: Generate SAML Details
- Step 2: Create an Application Portal Link
- Step 3: Register an Application
- Step 4: Share Metadata with Zscaler

To set up SSO account authentication, you must generate a SAML Entity ID and Reply URL within the SecOps platform. To learn more, see [Generating SAML Details](https://help.zscaler.com/uvm/generating-saml-details).

The SecOps platform doesn't natively support identity provider (IdP)-initiated login. Instead, implement the following process using an Application Portal Link app that redirects to app.avalor.io.

1. Log in to the Ping Identity admin center.
2. In the **Applications**menu, click **Application Portal**. See image.
3. On the **Application Portal** page, click the **Add**icon to add a new link. See image.
4. In the **Add Link** window: See image.
  1. **Link Name**: Enter a name for the application portal.
  2. **Description**: Add a description.
  3. **Icon**: Upload a logo from your system.
  4. **URL**: Enter `https://app.avalor.io?domain=<Your Org Domain>`, replacing `<Your Org Domain>` with your actual organization domain.
5. Click **Save**.

[Image: Application Portal in the Ping Identity admin center]

[Image: Page to add links to applications]

**[Image: Profile details needed to add an application]**

After generating SAML details, you can register an application in the Ping Identity admin center to define how your application interacts with the platform. The application registration process is performed within the centralized PingOne admin center.

1. Log in to the Ping Identity admin center.
2. In the **Applications**menu, click **Applications**. See image.
3. On the **Applications**page, click the + icon. See image.
4. In the **Application Name**field, enter `SecOps Platform- Authentication Only`.
5. Use the attached SecOps platform's logo as the application icon.
6. Under **Choose Application Type**, select **SAML Application**. See image.
7. In the**SAML Application** window, click **Configure**. See image. The **SAML Configuration** page appears.
8. On the **SAML Configuration**page: See image.
  1. Under **Provide Application Metadata**, select **Manually Enter**.
  2. Under **ACS URLs**, enter the **Reply URL** you copied from the SecOps platform.
  3. For **Entity ID**, enter the **Entity ID** you copied from the SecOps platform.
9. Click **Save**.
10. Go to the **Attribute Mappings** tab. See image.
11. Click the **Edit**icon next to **Attributes**. The **Edit Attribute Mappings** page appears.
12. On the **Edit Attribute Mappings** page: See image.
  1. Under **Attributes**, enter `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`.
  2. From the **PingOne Mappings**drop-down menu, select **Email Address**.
  3. Select the **Required**checkbox.
13. Click **Save**.
14. Go to the **Access** tab. See image.
15. Click the **Edit**icon next to **Attributes**.
16. On the **Edit Access**page, deselect **Display this application in the Application Portal**. See image.
17. Click **Save**.

[Image: Applications under Connections]

**[Image: Add a new application]**

[Image: Application type of the application to be registered]

[Image: Configurations required to create a SAML application]

[Image: Application metadata required for SAML configuration]

[Image: Attribute mapping details]

[Image: Edit Attribute Mappings]

[Image: Access details of the SAML application]

[Image: Edit the Access tab details]

After registering an app in the Ping Identity admin center, share XML metadata with your Zscaler Account team.

1. Go to the **Configuration** tab. See image.
2. Click **Download Metadata**.

To share metadata with Zscaler:

1. In the SecOps platform, click the **Profile** menu on the top right of the page and select **Account Settings**.
2. In the **Authenticate** section, paste the XML metadata into the **SAML XML MetaData**field. If the Authenticate section is not visible, share the XML metadata with your Zscaler Account team. See image. While a metadata URL is also supported, Zscaler recommends pasting the XML metadata directly.

[Image: Configuration details of the application]

**[Image: Add the SAML XML link in the SecOps platform]**
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-secureauth-sso","lastmod":"2026-04-14T14:35Z","nid":"1539766"} -->
## Configuring SecureAuth SSO

- Source: https://help.zscaler.com/soc-workbench/configuring-secureauth-sso
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Account Management > Admin Configuration and Deployment > Configuring SecureAuth SSO
- Last modified: 2026-04-14T14:35Z
- Summary: How to configure SecureAuth SSO account authentication.

You can configure SecureAuth SSO as the authentication method for the Zscaler Security Operations (SecOps) platform, allowing users to sign in through your SecureAuth SSO provider, instead of using a username and password. To do this, you can specify a domain, and users with email addresses matching that domain are redirected to authenticate through SecureAuth. Each user must have an account with the same email address in both the platform and SecureAuth. After SSO is enabled for a domain, it becomes the only authentication method for the platform.

To configure SecureAuth SSO, complete the following steps:

- Step 1: Generate SAML Details
- Step 2: Create an App Integration
- Step 3: Share Metadata with Zscaler

To set up SecureAuth SSO account authentication, you must generate a SAML Entity ID and Reply URL within the SecOps platform. To learn more, see [Generating SAML Details](https://help.zscaler.com/uvm/generating-saml-details).

To enable SAML-based authentication with SecureAuth, you need to create and configure a new app integration.

To create an app integration:

1. Log in to the SecureAuth Identity Platform.
2. In the left-side navigation, go to **New Experience**> **SecureAuth IdP**.
3. Click **Application Manager**. See image.
4. Click **Add an Application** to open the application template library.
5. Select **SAML Application** from the library. See image.
6. On the **Applications Details**page: See image.
  1. **Application Name:** Enter the SecOps platform name.
  2. **Application Description**: Enter a description for the application.
  3. **Upload**: Upload a logo from your system.
  4. **Data Stores:** Enter the data stores to authenticate and allow user access.
  5. **Groups:** Enter the groups allowed to access the application. You can also enable **Allow users from every group in your selected data stores access to this application**.
7. Click **Continue.** The **Connection Settings**page appears.
8. On the **Connection Settings** page:
  1. In the **Configure Connection**section: See image.
    1. Select **SP Initiated** from the **Connection Type** drop-down menu.
    2. Select **By Redirect**.
  2. In the **User ID Mapping**section: See image.
    1. **User ID Profile Field:** Select the relevant user ID from the drop-down menu.
    2. **Name ID Format:** The format is selected by default.
  3. In the**SAML Assertion**section: See image.
    1. **​​IdP Issuer:** Enter the entity ID from Step 1.
    2. **Assertion Consumer Service (ACS):** Enter the Reply URL from Step 1.
    3. **SP Login URL:** Enter the URL `https://app.avalor.io?domain=<your domain>`.
    4. **Assertion will be valid for:** Select an appropriate value.
    5. **Encrypt SAML Assertion:** Disable the toggle. All the remaining settings should be set to default.
  4. In the **SAML Attributes** section, click **Add SAML Attribute**: See image.
    1. **Attribute Name:** Enter the attribute name.
    2. **Data Store Property:** Choose the value that represents the user's email (**Email**).
    3. **Namespace (1.1)**: Enter `http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress`.
9. Click **Add Application**. The **Information for Service Providers** page appears. See image.
10. Click **Download Metadata**. You will need this information for the next step.
11. Enter the SecureAuth IdP appliance URL or IP address as the domain name (e.g., `https://secureauth.company.com or https://111.222.33.44`). See image.
12. Click **Download**to download the metadata.

**[Image: Add an application]**

[Image: Select the SAML application from the library]

[Image: Enter all the application details]

[Image: Add the configuration connection details]

[Image: Add the user ID mapping details]

[Image: Enter the SAML assertion details]

[Image: Enter the SAML attribute details]

[Image: Information for Service Providers page]

After registering an app in the SecureAuth Identity Platform, share XML metadata with your Zscaler Account team.

To share metadata with Zscaler:

1. In the SecOps platform, click the **Profile** menu on the top right of the page and select **Account Settings**.
2. In the **Authenticate** section, paste the XML metadata into the **SAML XML MetaData**field. If the Authenticate section is not visible, share the XML metadata with your Zscaler Account team. See image. While a metadata URL is also supported, Zscaler recommends pasting the XML metadata directly.

[Image: Enter the domain name details to download the metadata file]

[Image: Add the SAML XML link in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-sentinelone-connector","lastmod":"2026-04-16T12:29Z","nid":"1539818"} -->
## Configuring the SentinelOne Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-sentinelone-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the SentinelOne Connector
- Last modified: 2026-04-16T12:29Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-servicenow-outegration","lastmod":"2026-04-16T12:56Z","nid":"1539826"} -->
## Configuring the ServiceNow Outegration

- Source: https://help.zscaler.com/soc-workbench/configuring-servicenow-outegration
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Outegrations > Outegration Configuration Guides > Configuring the ServiceNow Outegration
- Last modified: 2026-04-16T12:56Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-servicenow-outegration-webhook","lastmod":"2026-04-16T12:58Z","nid":"1539827"} -->
## Configuring the ServiceNow Outegration Webhook

- Source: https://help.zscaler.com/soc-workbench/configuring-servicenow-outegration-webhook
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Outegrations > Outegration Configuration Guides > Configuring the ServiceNow Outegration Webhook
- Last modified: 2026-04-16T12:58Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-severity-categories","lastmod":"2026-06-22T21:06Z","nid":"1541100"} -->
## Configuring Severity Categories

- Source: https://help.zscaler.com/soc-workbench/configuring-severity-categories
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Configuring Severity Categories
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-snyk-connector","lastmod":"2026-04-16T12:30Z","nid":"1539820"} -->
## Configuring the Snyk Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-snyk-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Snyk Connector
- Last modified: 2026-04-16T12:30Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

To learn more, see [Zscaler UVM and Snyk Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-snyk-deployment-guide).

To learn more, see [Zscaler UVM and Snyk Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-snyk-deployment-guide).

[Image: The Snyk SAST and Snyk tiles]

[Image: The Snyk Authentication window displaying the Name, Token, and Org Id fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-suppression-rules","lastmod":"2026-04-14T14:58Z","nid":"1539769"} -->
## Configuring Suppression Rules

- Source: https://help.zscaler.com/soc-workbench/configuring-suppression-rules
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Configuring Suppression Rules
- Last modified: 2026-04-14T14:58Z
- Summary: How to configure suppression rules for data sources to either exclude data from ingestion, or to include a subset of the source data.

[[nid:1527996]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-wiz-connector","lastmod":"2026-04-16T12:30Z","nid":"1539821"} -->
## Configuring the Wiz Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-wiz-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Wiz Connector
- Last modified: 2026-04-16T12:30Z
- Summary: How to configure the Wiz connector, including setting up authentication and data retrieval filters and specifications.

Wiz analyzes all layers of the cloud stack to identify high-risk attack vectors to be prioritized and fixed.

There are 4 Wiz streams. Select those that are based on your Wiz feature plan and use cases. The following is one of the available streams:

Wiz Assets: Retrieves detailed information about cloud resources, with optional enrichment for exposed resources data.

To learn more about the other available Wiz streams, see [Zscaler UVM and Wiz Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-wiz-deployment-guide).

## Prerequisites

Before you configure the Wiz connector, make sure the following prerequisites are met:

- Retrieve the source authentication configuration parameters.
- Ensure the proper roles and permissions are set.

For the Wiz Assets stream, the value supplied in the Client ID field must carry at least the following permissions:

- `read:resources`
- `read:network_exposure` (if the Network Exposure Info checkbox is selected)

To learn more about permissions for the other Wiz streams, see [Zscaler UVM and Wiz Deployment Guide](https://help.zscaler.com/zscaler-technology-partners/zscaler-and-wiz-deployment-guide).

## Configuring the Connector

To create the Wiz data source in the Zscaler Security Operations (SecOps) platform:

1. Go to **Configure** > **Sources**.
2. Click **Create**.
3. Search for and select the connector tile from the available data sources. See image.

### Configuring Retrieval Filters and Specifications

After you create the Wiz data source, enter the following information in the source setup Retrieval section:

- Authentication
- Project ID
- Asset Type
- Fetch configuration findings from the past selected days
- Findings Status
- Issue Type
- Issue Severity
- Issue Status
- (Optional) Include Network Exposure Info

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

## Troubleshooting and FAQs

The Wiz Issues stream might return fewer results than expected, or miss data due to a 50,000 row limit on full report runs enforced by Wiz. If your dataset exceeds this threshold, only the first 50,000 rows are included in the export. The export is the data exported from Wiz to the SecOps platform. The remainder is omitted. For larger datasets, use incremental exports, which support up to 500,000 rows to ensure full data coverage.

To configure authentication:

1. In the **Authentication** section, click **Create New**. The **Wiz Authentication** window appears.
2. In the **Wiz Authentication** window, enter the information from the Prerequisites section. See image.
3. Click **Create**.

To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications) and [Managing Authentications](https://help.zscaler.com/uvm/managing-authentications).

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

Select the types of issues to include in the scope of the ingested data (i.e., Toxic Combination, Threat Detection, Cloud Configuration).

This menu is available in the Wiz Issues stream.

Select the issue severity levels to include in the scope of the ingested data (i.e., Critical, High, Medium, Low, Informational).

This menu is available in the Wiz Issues stream.

Select the status you want to include in the scope of the ingested data (i.e., Open, In Progress, Resolved, Rejected, or All Statuses).

This menu is available in the Wiz Issues stream.

Enable the Include Network Exposure Infocheckbox to add enrichment for exposed resources data. This data provides insights into which resources are exposed, how they are exposed (e.g., to the public internet, cross-account access, custom IP ranges), and details like IP ranges, ports, and protocols.

This checkbox is available in the Assets stream.

[Image: The Wiz - Vulnerability Findings, Wiz - Issues, and Wiz - Configuration Findings tiles]

[Image: The Wiz Authentication window displaying the Name, Client Id, Client Secret, Url, and Token Url fields in the SecOps platform]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-zia-devices-and-users-connector","lastmod":"2026-04-16T12:32Z","nid":"1539823"} -->
## Configuring the ZIA Devices and Users Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-zia-devices-and-users-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the ZIA Devices and Users Connector
- Last modified: 2026-04-16T12:32Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/configuring-zscaler-client-connector-devices-connector","lastmod":"2026-04-16T12:31Z","nid":"1539822"} -->
## Configuring the Zscaler Client Connector Devices Connector

- Source: https://help.zscaler.com/soc-workbench/configuring-zscaler-client-connector-devices-connector
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Configuring the Zscaler Client Connector Devices Connector
- Last modified: 2026-04-16T12:31Z
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

For complete configuration instructions, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/connecting-anysource","lastmod":"2026-04-16T11:30Z","nid":"1539809"} -->
## Connecting AnySource

- Source: https://help.zscaler.com/soc-workbench/connecting-anysource
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Connecting AnySource
- Last modified: 2026-04-16T11:30Z
- Summary: How to connect and set up an AnySource data connector, including uploading files manually, or using AWS S3, GCP, webhook, and an uploading file API.

When [creating a data source](https://help.zscaler.com/uvm/creating-data-sources) to ingest data into the platform, you can either use a dedicated vendor connector, or you can use the AnySource connector. The AnySource connector allows you to upload files directly to the platform. Uploaded files are stored in their original format and can be mapped to the platform's data model. Each source maintains a consistent field mapping, so all files uploaded to a given source should match the structure of the original file (e.g., headers, file type).

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

To complete the AnySource connector setup, including Remediation Detection Settings and Suppression Rules, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).

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

When ingesting data through the AnySource connector, it's important to include specific fields to enable effective data unification (i.e., entity resolution and data normalization) within the platform's unified data model. To learn more, see [What Is Data Unification?](https://help.zscaler.com/uvm/what-data-unification)

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/connecting-anysource-using-aws-s3","lastmod":"2026-04-16T12:16Z","nid":"1539810"} -->
## Connecting AnySource Using AWS S3

- Source: https://help.zscaler.com/soc-workbench/connecting-anysource-using-aws-s3
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Source Configuration Guides > Connecting AnySource Using AWS S3
- Last modified: 2026-04-16T12:16Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/creating-custom-incident","lastmod":"2026-06-22T21:06Z","nid":"1540861"} -->
## Creating a Custom Incident

- Source: https://help.zscaler.com/soc-workbench/creating-custom-incident
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Creating a Custom Incident
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/creating-data-sources","lastmod":"2026-04-14T14:40Z","nid":"1539767"} -->
## Creating Data Sources

- Source: https://help.zscaler.com/soc-workbench/creating-data-sources
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Creating Data Sources
- Last modified: 2026-04-14T14:40Z
- Summary: How to create a new data source in the Zscaler SecOps platform, including source details, retrieval settings, scheduling, remediation detection settings, and suppression rules.

[[nid:1527861]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/creating-formatting-rules","lastmod":"2026-03-28T06:06Z","nid":"1539532"} -->
## Creating Formatting Rules

- Source: https://help.zscaler.com/soc-workbench/creating-formatting-rules
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Admin Portal > Creating Formatting Rules
- Last modified: 2026-03-28T06:06Z
- Summary: How to create and apply formatting rules to visually distinguish measurements using color-coded thresholds.

Formatting rules allow you to apply coloring rules to [measurements](https://help.zscaler.com/aem/understanding-measurements-dimensions) and [dimensions](https://help.zscaler.com/aem/understanding-measurements-dimensions), making data interpretation clear and intuitive. These rules help emphasize key measurements, highlight data based on dimension values, and track trends across dashboards and compliance policies.

## Creating Formatting Rules

To create a formatting rule:

1. In the Zscaler Security Operations (SecOps) platform, go to **Configure**> **Formatting Rules**. The **Formatting Rules**page appears. See image.
2. Click **New Rule**. The **Create Formatting Rule**drawer appears. See image.
3. In the **Create Formatting Rule** drawer:
  1. **Name**: Enter a name (e.g., Policy Compliance Threshold).
  2. **Type**: Select the rule type:
    - Numeric Rules
    - Dimension-Based Rules
  3. In the **Fallback Formatting Rule Logic** section, select the rule logic when data does not meet the defined conditions:
    - **Back to Default**: Restores the default color assigned to the rule.
    - **Customize**: Select a color for values that do not meet the conditions.
  4. (Optional) Click **Reset to Default** to clear all custom inputs and revert the rule configuration to its original state.
  5. Click **Save**.

You can apply formatting rules to compliance policies or custom dashboards. To learn more, see [Configuring Asset Compliance Policies](https://help.zscaler.com/aem/configuring-asset-compliance-policies)and [Configuring Custom Dashboards](https://help.zscaler.com/aem/configuring-custom-dashboards).

Numeric rules allow you to define custom coloring rules for measurements.

1. Define the conditions:
  1. Select an operator (e.g., **>**, **<**, **=**, **Between**).
  2. Enter a threshold value for the condition. The **Between**operator requires both a starting and ending value for the numerical range.
2. Assign a color for the condition (e.g., red for <25%).
3. (Optional) Click the **Trash Can** icon to delete a rule. See image.
4. (Optional) Click **Add**to add a new rule. See image.

See image.

Numeric rules are evaluated in the order they appear (from top to bottom). When there is an overlap between conditions, the color of the top condition takes priority.

Dimension-based rules allow you to define custom coloring rules for dimensions. This is an effective way to visualize good versus bad indicators, such as Severity Levels or risk-based metrics like Asset Criticality.

1. Select a value from the Dimension drop-down menu. The dimension values appear. See image. Rules can be configured for the top 25 dimension values.
2. Assign a color to each dimension value.

See image.

- This rule applies only to pie, bar, and table charts.
- [Repeated fields](https://help.zscaler.com/uvm/using-filters) are not currently supported.

[Image: Create dimension-based rule]

[Image: Delete icon]

[Image: Add a new rule]

[Image: List of dimension values]

[Image: Create Formatting Rule drawer]

[Image: Create Formatting Rule Drawer]

[Image: Formatting Rules Page]

## Applying Formatting Rules to a Widget

To apply a formatting rule to a widget:

1. In the SecOps platform, go to **Explore**> **Dashboards**. The **All Dashboards**page appears. See image.
2. Click the dashboard you want to edit. The selected dashboard appears. See image.
3. Click the **Edit Dashboard**icon.
4. Hover over the desired widget, and click the **Edit**icon. See image.
5. Click the **Style**tab. See image.
6. Select a formatting rule from the **Formatting Rules**drop-down menu. See image. You can also click **New Rule** to create a new formatting rule.
7. Click **Save**.
8. Click **Save**on the dashboard to apply formatting rules to the widget.

## Applying Formatting Rules to a Policy

To apply a formatting rule (numeric rule) to an Asset Exposure Management (AEM) policy:

1. Click **Assets**(the AEM app) in the top navigation bar.
2. In the left-side navigation, click **Policies**. The **Policies**page appears. See image.
3. Hover over the policy, and click the **Edit**icon. See image. The **Edit Policy**page appears. See image.
4. In the **Formatting Rule**section, select the relevant formatting rule. You can also click **New Rule** to create a new formatting rule.
5. Click **Save** to apply the rule to the policy.

[Image: Policies page]

[Image: Select the policy to edit]

[Image: Edit the policy]

[Image: All Dashboards page]

[Image: Edit the dashboard]

[Image: Edit the widget]

[Image: Style Tab]

[Image: Select a formatting rule from the Formatting Rules drop-down menu]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/creating-managing-saved-views","lastmod":"2026-03-28T05:51Z","nid":"1539529"} -->
## Creating & Managing Saved Views

- Source: https://help.zscaler.com/soc-workbench/creating-managing-saved-views
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Admin Portal > Creating & Managing Saved Views
- Last modified: 2026-03-28T05:51Z
- Summary: How to create and manage saved views for entity pages and system dashboards.

Saved views are configurable, reusable views that apply filters, grouping, sorting, and field selections to data. They improve data exploration and analysis by allowing users to return to specific data configurations without reapplying settings each time.

Saved views are available in the following areas of the Zscaler Security Operations (SecOps) platform:

- Entity pages
  - UVM: [Tickets](https://help.zscaler.com/uvm/about-tickets), [Findings](https://help.zscaler.com/uvm/about-findings-operational-view-uvm), [Assets](https://help.zscaler.com/uvm/about-assets-operational-view-uvm), and [Exceptions](https://help.zscaler.com/uvm/understanding-exception-requests)
  - AEM: Assets and [Policy Violations](https://help.zscaler.com/aem/about-policy-violations)
- System dashboards
  - UVM: [Risk](https://help.zscaler.com/uvm/viewing-risk-dashboard) and [Remediation History](https://help.zscaler.com/uvm/viewing-remediation-history-dashboard)
  - AEM: Policy Compliance and Coverage and Gap
- When [viewing items in a dashboard widget](https://help.zscaler.com/uvm/viewing-items-widget-segment)

Your account is equipped with system-saved views, which are configured to reflect common use cases. You can create additional custom views that reflect your organization's specific workflows, investigation patterns, and reporting needs.

Saved views are located in the drop-down menu above the entity table.

See image.

When managing saved views, you can perform the following actions:

- Reset the Default View
- Favorite Views
- Create a New View
- Edit Views
- Delete Views

[Image: Saved views drop-down menu]

Your default view determines what you first see when opening a particular page. For example, the default view of the Tickets page in the UVM app is Active, which is set to display only active tickets.

You can customize the default view at two levels:

- Personal default view: In the saved views drop-down menu, click the **Set as my default view** icon next to the view name. This sets the selected view as the default for you only. See image.
- Set the account-wide default view: In the saved views drop-down menu, click the **Set as Account Default View** icon next to the view name. This sets the selected view as the default for all users in the account. See image.

[Image: Set My Default View icon]

[Image: Set as Account Default View icon]

To access frequently used views, you can add views to the favorites list. Favorited views are displayed at the top of the saved views list.

To add a view to the favorites list, click the **Add to Favorite** icon next to the view name.

See image.

[Image: Add to Favorite icon]

You can create a custom view by adjusting filters, displayed fields, grouping, and sorting.

To create a new saved view:

1. Apply filters, sorting, and other settings to the table.
2. Click **Save as View**at the top-right corner of the page. See image.
3. In the dialog window: See image.
  - **View Name**: Enter a name for the view.
  - **View Permissions**: Set to either **Public** (i.e., visible to all users in the account) or **Private** (i.e., visible only to you).
  - **Set as Account Default View**: Select the checkbox to set this view as the default view for all users in the account.
4. Click **Save**.

[Image: Save as View option]

[Image: Save as new view window]

You can edit custom views that you created to update the view's settings (i.e., the view's name and permissions).

You can't edit system views and views that were created by other users.

To edit a custom saved view:

1. Select the view from the saved views drop-down menu.
2. Hover over the view and click the **Edit**icon. The **Edit Current View** window appears.
3. In the**Edit Current View** window, apply your changes.
4. Click **Save**.

To edit the view's configuration (e.g., filtering, displayed fields, grouping), adjust the view as needed and click **Save View** at the top-right of the page.

You can delete custom views that you created to remove them from the available saved views.

You can't delete system views and views that were created by other users.

To delete a custom saved view:

1. Select the view from the saved views drop-down menu.
2. Hover over the view and click the **Delete**icon.

The view is deleted immediately.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/creating-outegrations","lastmod":"2026-04-16T11:18Z","nid":"1539807"} -->
## Creating Outegrations

- Source: https://help.zscaler.com/soc-workbench/creating-outegrations
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Outegrations > Creating Outegrations
- Last modified: 2026-04-16T11:18Z
- Summary: How to create a new outegration in the Zscaler SecOps platform.

[[nid:1530646]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/customizing-alert-and-incident-detail-pages","lastmod":"2026-06-22T21:06Z","nid":"1534217"} -->
## Customizing Alert and Incident Detail Pages

- Source: https://help.zscaler.com/soc-workbench/customizing-alert-and-incident-detail-pages
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Customizing Alert and Incident Detail Pages
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/customizing-incident-and-alert-statuses","lastmod":"2026-06-22T21:06Z","nid":"1541098"} -->
## Customizing Incident and Alert Statuses

- Source: https://help.zscaler.com/soc-workbench/customizing-incident-and-alert-statuses
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Customizing Incident and Alert Statuses
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/enabling-email-notifications-failures","lastmod":"2026-03-28T05:59Z","nid":"1539530"} -->
## Enabling Email Notifications for Failures

- Source: https://help.zscaler.com/soc-workbench/enabling-email-notifications-failures
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Admin Portal > Enabling Email Notifications for Failures
- Last modified: 2026-03-28T05:59Z
- Summary: How to enable outegration failure email notifications.

You can enable email notifications to receive alerts on errors for outegration workflows, source run failures, and issues with Extract, Transform, and Load (ETL) and data pipeline. This enables you to proactively resolve issues and minimize disruptions.

To enable email notifications, your assigned role must include the Edit permissions under the Platform - Outegrations resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/aem/creating-custom-roles) and [Assigning Roles to Users](https://help.zscaler.com/aem/assigning-roles-users).

To enable an email notification:

1. In the Zscaler Security Operations (SecOps) platform, click the **Profile**menu in the top right of the navigation bar, and click **Profile Settings**. See image. The **Settings**page appears. See image.
2. In the **Email Notifications**section, select **Enable failure alert notifications**.
3. In the**Email Notifications**settings:
  1. **Select alert type**: Select the options that you want to set the alert for:
    - **Source Runs**:Source runs fail for various reasons, including API rate limits, expired or invalid credentials, schema changes, or upstream outages. Select this option to receive notifications and reduce the need to manually check the status.
    - **ETL & Data Pipeline**: ETL and data pipeline processes can fail when data manipulation encounters errors such as data type mismatches, out-of-range values, invalid values, etc.
    - **Outegrations**: Failures can occur during third-party outegrations. For example, expired tokens for Jira can stop tickets from being created.
  2. **Select accounts**: Select the accounts for which you want to enable email notifications.
  3. **Frequency**: The default setting is **Daily**. This setting cannot be modified.
  4. **Time**: Set the local time you want the email delivered (based on your time zone).
4. Click **Save**. Emails are sent at the configured time to the email address associated with the admin who enabled the notifications.

[Image: Account name in the SecOps platform]

[Image: Email Notifications settings]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/generating-saml-details","lastmod":"2026-04-14T14:25Z","nid":"1539761"} -->
## Generating SAML Details

- Source: https://help.zscaler.com/soc-workbench/generating-saml-details
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Account Management > Admin Configuration and Deployment > Generating SAML Details
- Last modified: 2026-04-14T14:25Z
- Summary: How to generate SAML details for setting up single sign-on to the Zscaler SecOps platform.

Setting up single sign-on (SSO) account authentication requires generating a SAML Entity ID and Reply URL in the Zscaler Security Operations (SecOps) platform.

If you don't have access to this feature, contact your Zscaler Account team or Zscaler Support for assistance.

To generate SAML details:

1. In the SecOps platform, click the **Profile**menu on the top right of the navigation bar.
2. Select **Account Settings**.
3. In the **Authenticate** section:
  1. **Email Domain**: Enter your organization's email domain including the suffix (e.g., `gmail.com`).
  2. **Authentication Type**: Select SAML from the drop-down menu. If the **Authentication Type**drop-down menu is disabled, enter an email domain name and save your changes to enable it.
  3. **Identity Provider Name**: Select the identity provider your organization uses (e.g., **Okta**).
4. Click **Generate SAML Details**.
5. Copy the **Entity ID** and **Reply URL**. See image.

Use the Entity ID and Reply URL to configure your SSO, following the setup steps provided by your SSO provider.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/grouping-data-entity-pages","lastmod":"2026-03-28T05:28Z","nid":"1539528"} -->
## Grouping Data on Entity Pages

- Source: https://help.zscaler.com/soc-workbench/grouping-data-entity-pages
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Admin Portal > Grouping Data on Entity Pages
- Last modified: 2026-03-28T05:28Z
- Summary: How to group data on entity pages by key attributes.

You can use the grouping feature to organize the data on pages (e.g., Tickets, Assets, Findings for UVM; Assets, Violation Tickets, Violation Findings for AEM). Grouping your view creates collapsible sections based on the field you select. For example, grouping the UVM Tickets view by Ticket Severity categorizes the tickets into the different severity values (i.e., Critical, High, Medium, Low, Info).

To add grouping to a page:

1. Go to the SecOps app's entity page (e.g., [Tickets](https://help.zscaler.com/uvm/about-tickets-operational-view-uvm) or [Findings](https://help.zscaler.com/uvm/about-findings-operational-view-uvm) in UVM, [Violation Tickets](https://help.zscaler.com/uvm/about-violation-tickets-operational-view-aem) or [Policy Violations](https://help.zscaler.com/uvm/about-policy-violations-operational-view-aem) in AEM).
2. Click the **Group By**drop-down menu. See image.
3. Select the field you want to group the view by (e.g., **Assignee Tags**, **Assignee ID**, etc.).

The view is updated by your selected grouping. To remove the grouping, click **Clear Selection**at the bottom of the **Group By**drop-down menu.

See image.

Your grouping preferences can be saved as a set view. To learn more, see [Creating & Managing Saved Views](https://help.zscaler.com/uvm/creating-managing-saved-views).

[Image: operational views group by]

[Image: group by clear selection]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/managing-data-sources","lastmod":"2026-04-14T14:56Z","nid":"1539768"} -->
## Managing Data Sources

- Source: https://help.zscaler.com/soc-workbench/managing-data-sources
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Managing Data Sources
- Last modified: 2026-04-14T14:56Z
- Summary: How to manage existing data sources, including configuring auto-scheduling, editing, deleting, deactivating, mapping, processing, rerunning the last execution, viewing source runs, and checking audit logs.

[[nid:1527971]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/managing-outegrations","lastmod":"2026-04-16T11:26Z","nid":"1539808"} -->
## Managing Outegrations

- Source: https://help.zscaler.com/soc-workbench/managing-outegrations
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Outegrations > Managing Outegrations
- Last modified: 2026-04-16T11:26Z
- Summary: How to manage configured outegrations, including editing the outegration mapping and settings, deleting, cloning, and deactivating outegrations, and viewing the outegration activity log.

[[nid:1527726]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/managing-table-columns","lastmod":"2026-03-28T05:26Z","nid":"1539527"} -->
## Managing Table Columns

- Source: https://help.zscaler.com/soc-workbench/managing-table-columns
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Admin Portal > Managing Table Columns
- Last modified: 2026-03-28T05:26Z
- Summary: How to adjust the displayed columns in tables across the platform, including Operational Views, Reports, and Runs.

You can customize the displayed columns and their sorting settings in tables throughout the platform. This article explains how to add and remove the displayed columns, which include measurements (quantitative fields) and dimensions (categorical fields). The Tickets page in the Vulnerabilities app is shown as an example.

To view the available fields that can be displayed as columns, click the **Settings** icon at the top right of the table. The **Manage Table Columns** window appears, displaying columns currently in use on the right, and columns that can be added on the left.

See image.

[Image: Managing table columns on the Tickets page]

## Adding and Removing Columns

To add and remove columns from the table:

1. To add a column, click the **+** icon on the field you want to add. See image.
2. To remove a column, click the **X** icon on the field you want to remove. See image.
3. Click **Apply**after you add or remove a column to save the change.

[Image: Add a column to display]

[Image: Remove a currently displayed column]

You can also add or remove columns by dragging and dropping them in the window.

- To add a field, drag it to the **IN USE** list.
- To remove a field, drag it back to the **AVAILABLE** list.

See image.

[Image: Drag and drop to add or remove columns]

Use the search box to find specific columns that you want to add or remove.

See image.

[Image: Search for a field]

## Arranging Columns

To arrange the displayed columns to your desired order:

1. In the **IN USE** list, drag the columns in the order you want them to appear. See image.
2. Click **Apply**to save your changes.

To sort a table by a column, click the column header you want to sort by. An arrow icon appears next to the column name, indicating the current sort direction (ascending or descending). Click the column header again to change the sort direction.

[Image: Arrange the order of columns to be displayed]

## Resetting to Default

To undo the changes and revert to the default display, click **Reset to Default**and then click **Apply**.

See image.

[Image: Reset columns to their default order]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/step-by-step-config-guide-soc-workbench","lastmod":"2026-06-22T21:06Z","nid":"1534195"} -->
## Step-by-Step Configuration Guide for SOC Workbench

- Source: https://help.zscaler.com/soc-workbench/step-by-step-config-guide-soc-workbench
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > Step-by-Step Configuration Guide for SOC Workbench
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/supported-browsers-zscaler-secops","lastmod":"2026-03-28T05:16Z","nid":"1539522"} -->
## Supported Browsers for Zscaler SecOps

- Source: https://help.zscaler.com/soc-workbench/supported-browsers-zscaler-secops
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Supported Browsers for Zscaler SecOps
- Last modified: 2026-03-28T05:16Z
- Summary: Information on browser compatibility with the Zscaler SecOps platform.

Zscaler actively tests browser compatibility with the Zscaler Security Operations (SecOps) platform.

The Zscaler SecOps platform supports the following browsers:

- Apple Safari (two most recent versions)
- Google Chrome (two most recent versions)
- Microsoft Edge (two most recent versions)
- Mozilla Firefox (two most recent versions)

Older versions of these browsers should be compatible, but are no longer actively tested.

Other browsers should be compatible, but are not actively tested.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/taking-action-based-incidents","lastmod":"2026-06-22T21:06Z","nid":"1540857"} -->
## Taking Action Based on Incidents

- Source: https://help.zscaler.com/soc-workbench/taking-action-based-incidents
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Taking Action Based on Incidents
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/tracking-source-runs","lastmod":"2026-04-14T15:00Z","nid":"1539770"} -->
## Tracking Source Runs

- Source: https://help.zscaler.com/soc-workbench/tracking-source-runs
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Tracking Source Runs
- Last modified: 2026-04-14T15:00Z
- Summary: How to track source runs and view run details to assist in troubleshooting errors.

[[nid:1527671]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/understanding-severity-scores","lastmod":"2026-06-22T21:06Z","nid":"1541099"} -->
## Understanding Severity Scores

- Source: https://help.zscaler.com/soc-workbench/understanding-severity-scores
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Understanding Severity Scores
- Last modified: 2026-06-22T21:06Z
- Summary: Information on how SOC Workbench severity scores are calculated.

In alert management, multiple factors within categories (e.g., the product that produced an alert, whether a user is an admin, etc.) are given severity scores that are used to prioritize remediation efforts. Individual severity scores typically range from 0 to 10, where a higher score indicates greater criticality requiring immediate attention and resolution, and thus takes precedence over vulnerabilities with lower scores. SOC Workbench then adds the total score from each factor to produce the overall severity score.

Standard severity scoring frameworks often fall short in addressing an organization's unique context. SOC Workbench calculates severity scores based on context and incorporates data from various sources and analyses, providing more effective and accurate risk assessment. To learn more, see [Configuring Alert Scores](https://help.zscaler.com/soc-workbench/configuring-alert-scores).

You can drill down into the details for an alert to see how SOC Workbench calculated the severity score.

See image.

[Image: The Alert Detail page with score calculation in the SOC Workbench Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/understanding-threatlabz-source","lastmod":"2026-04-14T15:01Z","nid":"1539771"} -->
## Understanding the ThreatLabz Source

- Source: https://help.zscaler.com/soc-workbench/understanding-threatlabz-source
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > Security Operations Platform > Connectors > Sources > Understanding the ThreatLabz Source
- Last modified: 2026-04-14T15:01Z
- Summary: Information on the ThreatLabz source in the SecOps platform.

[[nid:1527651]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/understanding-zscaler-soc-workbench","lastmod":"2026-06-22T21:06Z","nid":"1534207"} -->
## Understanding Zscaler SOC Workbench

- Source: https://help.zscaler.com/soc-workbench/understanding-zscaler-soc-workbench
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > Understanding Zscaler SOC Workbench
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/using-filters","lastmod":"2026-03-28T05:25Z","nid":"1539526"} -->
## Using Filters

- Source: https://help.zscaler.com/soc-workbench/using-filters
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Admin Portal > Using Filters
- Last modified: 2026-03-28T05:25Z
- Summary: How to use filters to adjust the displayed data by the relevant scope.

You can use filters to adjust and refine the data displayed in views across the platform, from dashboards and reports to operational views (e.g., Tickets, Assets). By applying filters, you can focus on specific information relevant to your current task, such as reviewing vulnerabilities on a particular asset, addressing critical policy violations, or identifying high-priority tickets discovered in the last week. This helps security teams efficiently prioritize their workload and focus on the most critical risks in their environment.

Filters are applied using available fields, including measurements (e.g., counts, averages) and dimensions (e.g., Status or Severity categories). The specific fields available for filtering depend on where you are in the platform. For example, dashboards with data from multiple entities support filtering by dimensions but not measurements, while operational views typically allow filtering by both. To learn more, see [Understanding Measurements & Dimensions](https://help.zscaler.com/uvm/understanding-measurements-dimensions).

## Adding Filters

Different fields have filtering options that vary according to the field type (e.g., Boolean, Text, Number, Date, IP) and whether it can contain multiple values (i.e., repeated fields).

To add a filter:

1. At the top of the page, click **Add Filters** (if no filters are active) or **More**(if filters are already applied) to open the list of available fields for filtering in the current page. See image.
2. Search for and select the field you want to filter by. Selecting a field adds it to the list of filters.
3. Select the checkbox for the field you want to filter by. The field's filter dialog window appears.
4. In the field's filter dialog window, set the values you want to filter the field by:
  - List
  - Date Selector
  - Condition

Applying filters adjusts the displayed data to what is included in the filter's scope. This resets when you leave the page. Your applied filters can be saved as a set view. To learn more, see [Creating & Managing Saved Views](https://help.zscaler.com/uvm/creating-managing-saved-views).

## Filtering Repeated Fields

Repeated fields are fields that can store multiple values, such as the Asset or the Ticket Sources field. Filters applied to these fields allow you to focus on your data by including or excluding records based on one or more of the field's values.

### Repeated Fields in List Filters

When filtering repeated fields from the list of the field's values, selecting one or more values returns records containing at least one of the selected values.

See image.

For example, to view assets retrieved from specific sources, such as Qualys Vulns and ServiceNow Assets, select these values from the field filter drop-down menu. This returns assets that include either of these as a source, and not necessarily both. To exclude assets retrieved exclusively from a specific source (e.g., ServiceNow Assets), select all other values except the one you want to exclude. This excludes assets that list ServiceNow Assets as their only source, and includes assets that contain at least one of the selected sources (e.g., Qualys Vulns).

Filtering repeated fields using the list of values functions similarly to the `Equals (Any)` operator in conditional filtering, checking the filter against any of the field's values.

### Repeated Fields in Conditional Filters

For more granular filtering, you can configure conditional filters on repeated fields to define specific rules for their values. When configuring conditional filters for these fields, you can specify whether the filter should be checked against any or against all the field's values.

- All Operator
- Any Operator

The `Is Empty` and `Is Not Empty` operators are Boolean filters and do not have All or Any variations. These operators check whether a repeated field contains any values (i.e., `Is Not Empty`) or has no values at all (i.e., `Is Empty`). Unlike other conditional operators on repeated fields that evaluate individual values within the field, these operators apply to the field as a whole, making the distinction between All and Any unnecessary.

## Managing Filters

Filters can be adjusted, cleared, or reset as needed. When managing filters, you can perform the following actions:

- To clear the values from an applied filter field without removing the field itself, click the field in the filters bar and click **Clear Selection**.
- To remove a field from the filters bar, open the **More**drop-down menu and deselect the field from the list of active filters.
- To reset the filters, click **Clear All Filters**. This removes all active filters and displays all available data.

For fields with a set list of values (e.g., **Status**or **Severity**, or Boolean fields), select one or more options in the filter dialog window. For example, select **Opened** or **In Progress**for the **Status**filter to focus on active tickets. Use the search bar to locate values in long lists.

See image.

Multiple selections apply a logical OR (e.g., selecting **Opened** and **In Progress**shows items matching either value). If no values are selected, the filter will show all data for that field.

[Image: tickets more filters dialog open]

[Image: filters list status field]

You can define time ranges or conditions in the date selector window to filter data. For example, you can filter records to display only upcoming deadlines within the next month, or identify entries that lack assigned dates.

See image.

Date filters can be configured using the following methods:

- **Date Range**: Choose a method to filter records based on specific timeframes.
  - **Preconfigured**: Select a preset option on the left to filter records based on common timeframes (e.g., **Last 7 Days**, **Next 14 Days**).
  - **Custom**: Define a range to include only records within specific timeframes.
    - **Fixed Date Range**: Select a fixed start and end date using the calendar display, or manually enter dates in the date field at the bottom left of the filter dialog window.
    - **Dynamic Date Range**: Define relative timeframes that automatically update based on the current date (e.g., **Last 7 Days** or **Next 1 Month**).
      1. Select a timeframe from the drop-down menu:
        - **Next**: Displays records from today forward (e.g., **Next 1 Month**includes today + 31 days; **Next 1 Day**includes today + 1 day). This timeframe is often used for SLA and due-date fields. **Next**is not available for historical data.
        - **Last**: Displays records from the start of the current time unit (calendar Month, Week, Day) to today (e.g., **Last 1 Month**includes the 1st of the current month to today, including today; **Last 1 Day**includes today).
        - **Previous**: Displays records from the full previous time unit (calendar Month, Week, Day), not including the current time unit (e.g., **Previous 1 Month**in July includes the entire month of June; **Previous 1 Day**includes yesterday).
        - **Before**: Displays all records up to and including the selected date (the default selected date is today).
      2. Enter a value for the time unit.
      3. Select a time unit (i.e., **Day**, **Week**, **Months**) from the drop-down menu to define how the range is calculated. Weekly filters follow a Monday-to-Sunday format.
- **Is Empty**: Display records with no date assigned (e.g., records missing SLA deadlines).
- **Is Not Empty**: Display records with a date assigned.

[Image: filters date picker]

Use conditional filters to apply rules that filter your data based on field values. Conditional filters are available depending on the field and the view you're filtering in, and are not available for Boolean and Date fields. You can combine multiple conditions using AND or OR to create compound filtering rules for more granular control.

See image.

To apply conditional filters:

1. In the filter dialog window of the field you are filtering by, select **Condition**.
2. Configure the filter conditions:
  1. Select an operator (e.g., **Equals**, **>**). Available operators vary depending on the field type (i.e., Text, Number).
  2. Enter the value that the rule should apply to. Filter conditions are case sensitive.
  3. (Optional) Use **AND**/**OR**logic to define compound rules:
    - **AND**includes records only if they meet all conditions in the rule.
    - **OR**includes records if they meet any conditions in the rule.
3. Click **Apply**.

The following examples show conditional filters:

- Ticket Severity Score: Filter tickets based on their severity score to focus on high-risk issues. For example, to view tickets with a severity score between 7 and 10, add the Ticket Severity Score field to the filters, and configure the conditional filter `>= 7`.
- Asset Type: Filter assets by type to focus on related data and streamline your analysis. For example, to exclude Container assets, add the Asset Type to the filters, and configure the conditional filter `Not Equals Container`. This helps you concentrate on non-containerized assets, such as virtual machines or databases, when assessing infrastructure-level risks or vulnerabilities.

[Image: filters conditions]

[Image: filters list repeated fields asset sources]

The All operator returns records only if all the values in the field meet the filter condition.

Examples:

- To include assets retrieved exclusively from the sources Qualys Assets and Wiz Assets, use the `Equals (All)` operator (i.e., `Asset Sources Equals (All) Qualys Assets AND Wiz Assets`). This displays assets where all listed sources are Qualys Assets and Wiz Assets, excluding any records with additional sources (e.g., ServiceNow Assets).
- To exclude all assets retrieved from the ServiceNow Assets source, even if they were retrieved by other sources, use the `Not Equals (All)` operator (i.e., `Asset Sources Not Equals (All) ServiceNow Assets`). This excludes all assets retrieved from ServiceNow Assets, even those retrieved by additional sources (e.g., an asset retrieved by ServiceNow Assets and Qualys Assets is excluded).

The Any operator returns records if at least one (i.e., any) value in the field meets the filter condition.

Examples:

- To view all assets retrieved from ServiceNow Assets, even if they were retrieved by additional sources, use the `Equals (Any)` operator (i.e., `Asset Sources Equals (Any) ServiceNow Assets`). Assets retrieved from ServiceNow Assets are retrieved, including those retrieved by other sources (e.g., an asset retrieved by ServiceNow Assets and Qualys Assets is included).
- To exclude records retrieved only from ServiceNow Assets (and no other sources), use the `Not Equals (Any)` operator (i.e., `Asset Sources Not Equals (Any) ServiceNow Assets`). Assets retrieved from ServiceNow Assets but also from other sources are not excluded (e.g., an asset retrieved by ServiceNow Assets and Qualys Assets is not excluded).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/viewing-incident-details","lastmod":"2026-06-22T21:06Z","nid":"1540782"} -->
## Viewing Incident Details

- Source: https://help.zscaler.com/soc-workbench/viewing-incident-details
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > Viewing Incident Details
- Last modified: 2026-06-22T21:06Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/viewing-user-details","lastmod":"2026-06-22T21:06Z","nid":"1539871"} -->
## Viewing User Details

- Source: https://help.zscaler.com/soc-workbench/viewing-user-details
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > SOC Workbench Portal > Viewing User Details
- Last modified: 2026-06-22T21:06Z
- Summary: How to view a user's detailed information in SOC Workbench.

You can view detailed information about users and identity findings. This helps security teams to review each user's severity level and details of these findings to understand the potential security risks and remediate any issues.

To view user details:

1. In the Zscaler Security Operations (SecOps) platform, go to **Identities**> **Users**.
2. On the **Users**page, click a user. A user details drawer appears.
3. In the user details drawer, you can access the following tabs:
  - Details
  - Identity findings

On the **Details**tab, you can view:

- **Name**: The name of the user.
- **Type**: The type of the user (**Internal**or **External**).
- **Email**: The email ID of the user.
- **Is Admin**: The user's admin role status. If the user is an admin, this field shows `True`.
- **Identity Findings**: The total number of **Critical**, **High**, **Medium**, and **Low**identity findings for the user. See image.

On the **Identity findings** tab, you can do the following:

- Click the **Export as CSV**icon to export the identity findings list for a user to a CSV file.
- Click the **Settings**icon to modify the columns displayed in the table.
- Apply filters to the identity findings list (e.g., filtering the findings by a particular state, title, or severity score). To learn more, see [Using Filters](https://help.zscaler.com/identity-protection/using-filters).
- View the identity findings details: See image.
  - **Severity**: The severity level (**Critical**, **High**, **Medium**, or **Low**). The **Critical**and **High**severity findings are prioritized.
  - **Name**: The name of the identity finding.
  - **Type of Risk**: The type of risk (e.g., **Kerberos Abuse**, **Account Management**, **Credential Exposure**, etc.).
  - **MITRE Tactics**: The [MITRE ATT&CK](https://attack.mitre.org/tactics/) tactic ID.

[Image: Details tab on Users page displaying detailed information about user.]

[Image: Identity findings tab displaying findings list for a user.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/viewing-zscaler-secops-status-board","lastmod":"2026-03-28T05:20Z","nid":"1539524"} -->
## Viewing the Zscaler SecOps Status Board

- Source: https://help.zscaler.com/soc-workbench/viewing-zscaler-secops-status-board
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Viewing the Zscaler SecOps Status Board
- Last modified: 2026-03-28T05:20Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/what-zscaler-security-operations","lastmod":"2026-03-28T05:12Z","nid":"1539521"} -->
## What Is Zscaler Security Operations?

- Source: https://help.zscaler.com/soc-workbench/what-zscaler-security-operations
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > What Is Zscaler Security Operations?
- Last modified: 2026-03-28T05:12Z
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

<!-- ZS-ARTICLE {"url":"/soc-workbench/what-zscaler-soc-workbench","lastmod":"2026-06-22T21:06Z","nid":"1534206"} -->
## What Is Zscaler SOC Workbench?

- Source: https://help.zscaler.com/soc-workbench/what-zscaler-soc-workbench
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > SOC Workbench > What Is Zscaler SOC Workbench?
- Last modified: 2026-06-22T21:06Z
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

---

<!-- ZS-ARTICLE {"url":"/soc-workbench/zscaler-secops-public-ip-addresses","lastmod":"2026-03-28T05:18Z","nid":"1539523"} -->
## Zscaler SecOps Public IP Addresses

- Source: https://help.zscaler.com/soc-workbench/zscaler-secops-public-ip-addresses
- Product: SOC Workbench
- Path: SOC Workbench Help > Getting Started > Security Operations Platform > Zscaler SecOps Public IP Addresses
- Last modified: 2026-03-28T05:18Z
- Summary: A list of public IP addresses used by the Zscaler Security Operations platform and its applications (e.g., UVM, AEM, SOC Workbench) for allowlisting when configuring integrations with the platform.

To ensure seamless connectivity and uninterrupted access to essential resources, you can allowlist the public IP addresses used by outgoing traffic from the Zscaler Security Operations (SecOps) platform and its applications (e.g., UVM, AEM). These IP addresses serve as the source for all egress traffic originating from the Zscaler tenant.

Add the following IP addresses associated with your instance's region to your firewall's allowlist to ensure uninterrupted access to the required resources.

| **Region** | **IP Addresses** |
| --- | --- |
| **US** | 3.137.47.190/32 3.15.110.62/32 3.129.232.141/32 |
| **EU** | The recommended IP addresses listed in [Zscaler Hub IP Addresses](https://config.zscaler.com/zscalertwo.net/hubs) |
<!-- /ZS-ARTICLE -->
