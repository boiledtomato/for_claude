# Zscaler Help — SOC Workbench (part 1)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-24 01:16 UTC
Articles in this file: 29

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

<!-- ZS-ARTICLE {"url":"/soc-workbench/taking-action-based-incidents","lastmod":"2026-08-17T16:47Z","nid":"1540857"} -->
## Taking Action Based on Incidents

- Source: https://help.zscaler.com/soc-workbench/taking-action-based-incidents
- Product: SOC Workbench
- Path: SOC Workbench Help > Administration > SOC Workbench > Taking Action Based on Incidents
- Last modified: 2026-08-17T16:47Z
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
