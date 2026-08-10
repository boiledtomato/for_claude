# Zscaler Help — ZDX — Digital Experience Monitoring (part 1)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-10 01:47 UTC
Articles in this file: 130

---

<!-- ZS-ARTICLE {"url":"/zdx/about-admin-groups","lastmod":"2025-04-29T07:06Z","nid":"1507736"} -->
## About Admin Groups

- Source: https://help.zscaler.com/zdx/about-admin-groups
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > About Admin Groups
- Last modified: 2025-04-29T07:06Z
- Summary: Overview information about how ZDX Admins can organize admins into Admin Groups to organize admin scopes and roles.

Admin groups are created when you enable SCIM Auto Provisioning on the Administrator Management page. When enabled, they are auto-provisioned based on the user's SCIM group. You can then associate the admin group with admin scopes and roles. With Zscaler role-based administration, you can create Admin Groups to meet the needs of your organization. You can create, edit, or delete an admin group to better manage your use of admin groups.

Admin Groups provide the following benefits and enable you to:

- Configure admin groups in the ZDX Admin Portal for specific admin roles (specified by role).
- Configure admin groups for different administrators for the entire organization, location, or department (specified by scope).

## Prerequisites

In order to view the Admin Groups page, make sure the following prerequisites are met:

- Have Full or View access to Administrator Management to access the Admin Groups page. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- Enable SCIM Auto Provisioning on the Administrator Management page. To learn more, see [Configuring Administrator Management Settings](https://help.zscaler.com/zdx/configuring-administrator-management-settings).

## About the Admin Groups Page

You can view the different admin groups that have been set up, including their details, on the Admin Group page.

On the Admin Groups page (Administrator > Administrator Management > Administrator > Admin Groups tab), you can do the following:

1. [Add an admin group.](https://help.zscaler.com/zdx/managing-admin-groups)
2. Search for an admin group.
3. View a list of admin groups with the following information:
  - **Group Name**: The SCIM group name.
  - **Scope**: The type of admin scope. To learn more, see [Understanding the Admin Scope](https://help.zscaler.com/zdx/understanding-admin-scope).
  - **Role**: The type of admin role. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
  - **Comments**: Any comments to describe the admin group.
  - **Actions**: The actions you can use to manage the admin group.
4. [View, edit, or delete an admin group.](https://help.zscaler.com/zdx/managing-admin-groups) Based on the Admin Group and your ZDX admin's scope and role, you can have different actions. For example, if you have a scope based on an HR department, then you can view admin groups outside the HR department. You cannot add, edit, or delete them. You can view them.
5. Go to the [Administrator Management](https://help.zscaler.com/zdx/configuring-saml-zdx-admins) page or [Admins](https://help.zscaler.com/zdx/about-administrators) tab.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-administrators","lastmod":"2026-08-07T11:13Z","nid":"1374826"} -->
## About Administrators

- Source: https://help.zscaler.com/zdx/about-administrators
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > About Administrators
- Last modified: 2026-08-07T11:13Z
- Summary: Provide overview information for the Administrators page for ZDX admins.

[Watch a video about Administrators in ZDX](https://fast.wistia.net/embed/iframe/eidy76se80) (shows legacy UI).

On the Administrators page for Zscaler Digital Experience (ZDX), admins can manage users, create new admins, and reassign existing admins. With Zscaler role-based administration, you can add as many additional admins as necessary to meet the specific needs of your organization. You can also edit and delete admins as necessary at any time. Also, depending on their admin role and scope, configured admins can add, edit, or delete admin accounts.

ZDX Administration provides the following benefits and enables you to:

- Configure administration for ZDX admins (specified by role).
- Configure administration responsibilities between different administrators for the entire organization, location, or department (specified by scope).
- Configure administration roles relevant to productivity and compliance (specified by role).

On the Administrators page, the admins are listed as read-only if you are subscribed to Authentication Service You can configure ZDX admins in the Zscaler Admin Console. To learn more, see [What Is Authentication Service?](https://help.zscaler.com/authentication-service/what-authentication-service) and [About Administrative Entitlements](https://help.zscaler.com/authentication-service/about-administrative-entitlements).

## About the Administrators Page

You can view the different administrators that have been set up, including their details, on the Administrators page.

On the Administrators page (Administration > Admin Management > Role Based Access Control > Administrators), you can do the following:

1. [Add a new ZDX Admin](https://help.zscaler.com/zdx/managing-zdx-admins).
2. Export a CSV file of ZDX admins.
3. Search for an admin. Enter a term in the search bar. To delete content in the search bar, click the **Delete** icon to cancel and reset.
4. View a list of admins that are configured for your organization. For each admin, you can view:
  - **Login ID**: The email address that is used to log in. You can sort this field by clicking the arrow in the title.
  - **Name**: The name of the admin.
  - **Role**: The role that the admin was assigned during configuration.
  - **Scope**: The admin scope (**Organization**, **Location**, **Applications**, or **Department**). If a user switches departments or locations, the user's data is reported as the latest configured department or location.
  - **Login Type**: This is either a configured password for the ZDX admin or SAML.
  - **Password Expired**: If the configured password has expired, this field indicates **True**. If the configured password has not expired, this field indicates **False**. To learn more, see [Configuring Password Expiration](https://help.zscaler.com/zdx/configuring-password-expiration).
  - **Comments**: Displays any comments that were entered during configuration.
  - **Status**: Indicates if the admin is currently **Enabled** or **Disabled**.
  - **Actions**: The types of action you can take for the ZDX admin. Depending on your scope and role, you have different actions.
5. [View, edit, or delete an admin's settings.](https://help.zscaler.com/zdx/managing-zdx-admins)
6. Go to the [Administrator Management](https://help.zscaler.com/zdx/configuring-administrator-management-settings) page to configure admins, or go to the [Admin Groups](https://help.zscaler.com/zdx/about-admin-groups) page to manage admin groups.

[Image: Overview of the Administrators Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-alerts","lastmod":"2026-07-10T09:56Z","nid":"1364426"} -->
## About Alerts

- Source: https://help.zscaler.com/zdx/about-alerts
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > About Alerts
- Last modified: 2026-07-10T09:56Z
- Summary: Information on alerts for ZDX.

[Watch a video about Alerts in ZDX.](https://fast.wistia.net/embed/iframe/q2kipsz543)

Alerts provide a primary and timely source of information to monitor device, application, network performance, and ZDX Score on the Alerts page so that you can analyze and remediate issues.

Alerts provide the following benefits and enable you to:

- Review in-depth alert details in the Zscaler Admin Console.
- Create configurable alert rules that are triggered when a preset threshold is reached for different types of events.
- Receive triggered alert details that are sent via email or webhook.

For ZDX, you can view alerts triggered over the past two weeks. You can select options from 2 hours to 14 days in the time range filter to view triggered alerts in the Alert History tab. To learn more, see [Triggering an Alert](https://help.zscaler.com/zdx/triggering-alert).

The alerts triggered have a display delay of 30 minutes.

The Alerts page shows the following functionality:

- Alert Rules, Impacted Devices and Impacted Applications filters: Apply filters to drill down further into the data. By default, all filters are applied and the values are set to All.
- Time Range filter: At the top of the page, select the time (2 Hours to 14 Days) from the drop-down menu. This filter applies to the Alert History tab, which shows historical details over the time selected. The default time range is 2 Hours.
- Compare Alerts: Click the Open in a New Tab icon next to the alert name. You can use this icon to open multiple alerts and compare their details.

If you configure an alert rule by ZDX Score, depending on how you choose to group the scoring, these filters and icons are displayed. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).

## About the Alerts Page

On the Alerts page (Analytics > Digital Experience > Alerts > Ongoing Alerts), you can do the following:

1. Use the time range filter to help narrow your scope of information. Time range options are available in increments from the previous 2 Hours to 48 Hours, or a Custom range within the last 14 Days.
2. Use the filters to sort and view alerts. You can hide the filters bar if you do not want the filters displayed.
3. View the overall metrics for alerts:
  - **Ongoing Alerts**: The number of ongoing alerts.
  - **Alert History**: The number of completed alerts.
  - **Impacted Devices**: The number of impacted devices.
  - **Impacted Geolocations**: The number of impacted geolocations.
  - **Impacted Applications**: The number of impacted applications.
4. Switch the view to see **Ongoing Alerts** or **Alert History**.
5. Search and select for which columns to display. Click **Apply** to confirm the columns to display. See image.
6. View details of the alerts triggered for ZDX. The Ongoing Alerts tab displays ongoing alerts, and the Alert History tab displays historical alert details over the time selected. You can view the following for all the configured alerts: By default, the Alerts are sorted by the **Started On** column, but you can sort any of the columns by clicking the arrows next to them.
  - **Severity**: The severity of the event. Red indicates High severity, orange is Medium severity, and green indicates Low severity.
  - **Alert Rule**: The name entered for this rule from configuration.
  - **Monitoring**: The type of monitoring for this rule. End User indicates the alert rule was created by the user. Hosted indicates the alert rule was created for Hosted Monitoring.
  - **Type**: The type is Application, Network, or Device.
  - **Impacted Application**: The number of applications impacted by this alert.
  - **Impacted Geolocation**: The number of geolocations impacted by this alert.
  - **Impacted Devices / Users**: Depending on the type of alert rule, you see the number of impacted devices or users.
  - **Started On**: The date and time this alert was triggered.
  - **Ended On**: The date and time this alert ended. This column is on the Alert History tab.
  - **Status**: The status of the alert. To learn more, see [Understanding the Alert Status](https://help.zscaler.com/zdx/understanding-alert-status).
7. View details about an alert by clicking the **View** icon. To learn more, see [Evaluating Individual Alert Details](https://help.zscaler.com/zdx/evaluating-individual-alert-details).
8. Go to the [Rules](https://help.zscaler.com/zdx/about-rules) page to view the alert rules or access the [Templates](https://help.zscaler.com/zdx/about-templates) page.

[Image: Search and select which columns to display]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-api-key-management","lastmod":"2026-08-06T12:14Z","nid":"1397201"} -->
## About API Key Management

- Source: https://help.zscaler.com/zdx/about-api-key-management
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > API Key Management > About API Key Management
- Last modified: 2026-08-06T12:14Z
- Summary: To provide overview information on how to use the API Key Management.

API Key Management is a repository for an organization to store created API keys for admin roles. An API key consists of an API Key ID and Secret. These two items are required for access to Zscaler Digital Experience (ZDX) APIs.

API Key Management provides the following benefits and enables you to:

- Provide a repository of generated, authorized API keys for programmatic access to ZDX APIs.
- Maintain the configuration of API keys by creating, viewing, editing, or deleting the API keys as required.
- Identify the life cycle of an API key to maintain security.

If you must obtain API keys or secrets to access [Zscaler OneAPI](https://help.zscaler.com/oneapi) endpoints, see [About API Clients](https://help.zscaler.com/authentication-service/about-api-clients).

## About the API Key Management Page

On the API Key Management page (Administration > API Configuration > Legacy API > Digital Experience API), you can do the following:

1. [Create a new API key.](https://help.zscaler.com/zdx/managing-zdx-api-keys/#CreateAPIKey)
2. View a list of created API keys. For each API key, you can view:
  - **Name**: The name of the API key.
  - **Key ID**: The ID of the API key.
  - **Role**: The role the API key is assigned to.
  - **Created By**: The username that created the API key.
  - **Last Updated On**: The timestamp of when the API key was last updated.
  - **Actions**: The View, Edit, and Delete actions that can be performed on the API key.
3. [View an API key.](https://help.zscaler.com/zdx/managing-zdx-api-keys/#ViewAPIKey)
4. [Edit an API key.](https://help.zscaler.com/zdx/managing-zdx-api-keys/#EditAPIKey)
5. [Delete an API key.](https://help.zscaler.com/zdx/managing-zdx-api-keys/#DeleteAPIKey)

[Image: The Digital Experience API page displaying the different aspects on the page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-audit-logs","lastmod":"2026-04-20T15:51Z","nid":"1416976"} -->
## About Audit Logs

- Source: https://help.zscaler.com/zdx/about-audit-logs
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > About Audit Logs
- Last modified: 2026-04-20T15:51Z
- Summary: To an overview of information about the ZDX Audit Logs and its use.

Zscaler records the session information for each admin that signs in to Digital Experience features. The audit log displays information related to sign-in or sign-out attempts (e.g., timestamps, actions, IP addresses, etc.) and any configuration changes that were completed during their session such as updates or deletes.

Audit logs provide the following benefits and enable you to:

- Analyze administration sessions by reviewing in-depth information such as actions, categories, interface, or configuration changes.
- Customize filters to search for selected items and export them to a CSV file.
- Review configuration changes for comparison of the before-and-after administration sessions.

If an admin makes 5 consecutive unsuccessful attempts to sign in within 1 minute, the account is locked out for 5 minutes and the failed attempts are recorded.

Audit logs are stored for a period of 6 months.

## About the Audits Log Page

On the Audit Logs page (Administration > Admin Management > Audit Logs > Digital Experience), you can do the following:

1. Filter by Time Range, Action, Category, Sub-Category, Interface, or Result. You can also search for selected items on each filter. The default Time Range is Current Day while the defaults of the other filters are set to All.
  - List of potential Time Ranges
  - List of potential Actions
  - List of potential Categories
  - List of potential Sub-Categories
  - List of potential Interface
  - List of potential Results
2. Download a CSV file of the displayed Audit Log. The times in the CSV file are in PDT.
3. Search for a specific Resource, Admin ID, or Client IP. Resource is the default.
4. View a list of Admin Logins. For each admin login, you see:
  - **Timestamp**: The local time of the admin's last login or last logout.
  - **Actions**: The action performed by the admin in Digital Experience.
  - **Category**: A location in Digital Experience where the action was performed.
  - **Sub-Category**: A sublocation in Digital Experience where the action was performed.
  - **Resource**: The specific location within a subcategory.
  - **Admin ID**: The admin's login ID.
  - **Client IP**: The source IP address for the admin.
  - **Interface**: Where the user performs their actions.
  - **Result**: The outcome of an action in either Success or Failure.
  - **Modification**: View configuration changes from an admin login. See image.

[Image: Audit Logs Page]

- Current Day
- Current Week
- Current Month
- Previous Day
- Previous Week
- Previous Month
- Custom

- Activate
- Audit Operation
- Create
- Delete
- Download
- Forced Activate
- Import
- Patch
- Policy Sync
- Report
- Sign In
- Sign Out
- Update

- Activation
- Administration
- Administrator Management
- Alert
- Cloud Service API Key
- Configuration
- Help
- Inventory Management
- Login
- Organization Info
- Remediation
- Role Management
- SaaS Security API
- Software Device Inventory
- Tags
- Traffic Forwarding Resource
- ZDX Snapshot

- Activation
- Administrator
- Alert
- Applications
- Auditor
- Cloud Service API Key
- Deep Tracing
- Diagnostics
- EUSA Info
- Location
- Login
- Microsoft Endpoint Analytics
- Password Expiry
- Probe
- Remediation Jobs
- Remote Assistance
- Remote Script Configuration
- Role Management
- SAAS App Tenant
- SAML
- Scripts
- Self Service Settings
- Software and Device Inventory
- Tags
- Tenant
- Webhook
- Wi-Fi Access Point
- ZDX Snapshot
- ZDX SSO Login
- Zscaler Hosted Monitoring

- ZDX UI

- Failure
- Success

[Image: View any modifications that required a configuration change]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-configuration","lastmod":"2026-06-09T21:13Z","nid":"1535088"} -->
## About Configuration

- Source: https://help.zscaler.com/zdx/about-configuration
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > About Configuration
- Last modified: 2026-06-09T21:13Z
- Summary: Information on configuring collections, applications, and probes.

Configuration for Digital Experience in the Zscaler Admin Console allows you to configure collections for configured applications with Web and Cloud Path probes. After an application is configured successfully with a probe, you can monitor the digital experience of impacted users, groups, locations, and departments. The End User collection starts with three types of collections called Unified Communication Collection, Predefined Apps Collection, and Custom Apps Collection. The Managed collection starts with the Default Managed Collection. You can also view and configure top private applications.

Configuration provides the following benefits and enables you to:

- Create collections and organize applications into their respective collection.
- Organize configured applications into multiple collections and rank customized collections for end user applications.
- Configure applications or probes in the Configurations page.
- View metrics for an End User Collection or a Managed Collection to determine how many probes you have configured.
- Search and configure for top private applications based on port and protocol.

You can rank [collections](https://help.zscaler.com/zdx/understanding-collections) if an application is in multiple collections for the end user, and you can configure the collections to have [probing criteria](https://help.zscaler.com/zdx/understanding-probing-criteria-logic).

## About the Configuration Page

On the Configuration page (Policies > Digital Experience Monitoring > Probes), you can do the following:

1. Switch between the views. The default view is **End User**. Depending on your [ZDX role](https://help.zscaler.com/zdx/adding-zdx-roles) and [subscription level](https://help.zscaler.com/zdx/ranges-limitations), you might not see some of the views.
  - **End User**: View all the collections of [configured applications](https://help.zscaler.com/zdx/about-applications) and their [configured probes](https://help.zscaler.com/zdx/configuring-probe). The total number of probes are displayed. You can drag and drop customized collections to alternate their ranking. Default collections cannot alternate their rankings (e.g., Unified Communication Collection).
  - **Managed**: View all collections of applications with configured applications and their [configured Zscaler Managed probes](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes). The total number of probes are displayed.
  - **Top Private Apps**: Search and configure for [top private applications](https://help.zscaler.com/zdx/managing-top-private-applications) based on ports and protocol.
2. View a list of collections where you can access their applications and probes with the following information:
  - End User Monitoring
  - Managed Monitoring
3. Search through your collections for applications.
4. [Add a collection.](https://help.zscaler.com/zdx/managing-collections#add)
5. Sort your collection list in the menu (**Latest**, **A-Z**, **Z-A**).
6. View the Collections menu where you can access their applications and probes.
7. [Edit the collection.](https://help.zscaler.com/zdx/managing-collections#edit)
8. [Delete the collection.](https://help.zscaler.com/zdx/managing-collections#delete) You cannot delete a default collection (e.g., Unified Communication Collection, Predefined Apps Collection, Custom Apps Collection).
9. Hide the Collections menu.

The End User and Managed views have similar collection configurations and probe metrics.

[Image: View the Configuration page]

- For End User Monitoring:
  - **Total**: The total number of probes that are enabled and disabled.
  - **Limits**: The limited number of probes that can be configured.
  - **Cloud Path**: The total number of Cloud Path probes that are enabled and disabled.
  - **Web**: The total number of Web probes that are enabled and disabled.
- For filters:
  - **Departments**
  - **Status**
  - **Zscaler Locations**
  - **Devices**
  - **Users**
  - **User Groups**
  - **Location Groups**
- For the list:
  - **Rank**: The ranking of the collection.
  - **Collection**: The collection name.
  - **Status**: The status of the collection
  - **Exclusion**: The selected exclusions from the collection.
  - **Inclusion**: The selected inclusions from the collection.
  - **Last Modified**: The timestamp of when the collection was last modified.
  - **Actions**: [Edit, remove application from collection, or delete the collection.](https://help.zscaler.com/zdx/managing-collections)

- For Managed Monitoring:
  - **Total**: The total number of probes that are enabled and disabled.
  - **Limits**: The limited number of probes that can be configured.
  - **Cloud Path**: The total number of Cloud Path probes that are enabled and disabled.
  - **Web**: The total number of Web probes that are enabled and disabled.
- Use the **Status** filter to view enabled or disabled collections.
- For the list:
  - **Collection**: The name of the collection.
  - **Status**: The status of the collection.
  - **Last Modified**: The timestamp of when the collection was last modified.
  - **Actions**: [Edit or delete the collection.](https://help.zscaler.com/zdx/managing-collections)

A configured application for Managed Monitoring can exist in only one collection.

[Image: View Managed Collections]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-departments","lastmod":"2025-09-08T12:39Z","nid":"1464226"} -->
## About Departments

- Source: https://help.zscaler.com/zdx/about-departments
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > User Management > About Departments
- Last modified: 2025-09-08T12:39Z
- Summary: Information on the Zscaler Departments page in the ZDX Admin Portal.

[Watch a video about Departments in ZDX.](https://fast.wistia.net/embed/iframe/ony6dp3uhw)

Departments categorize users into different units. Departments were created in the [ZIA Admin Portal](https://help.zscaler.com/zia/about-departments) and are seen in the ZDX Admin Portal. Departments are used to help identify which users are impacted in dashboards and reports. You can configure [alerts](https://help.zscaler.com/zdx/configuring-alert-rule) specific to departments to notify you when they are experiencing a decrease in real-time user experience.

Departments provide the following benefits and enable you to:

- View your organization's defined departments from the ZIA Admin Portal for easy identification by searching for or managing department details.
- Export a list of departments into a CSV file for your own reporting use.

## About the Departments Page

On the Departments page (Administration > Administration Controls > User Management > Departments), you can do the following:

1. Download a list of departments in CSV format.
2. Search for a specific department. Enter the search term in the Search bar. To delete content in the Search bar, click the **Delete** icon to cancel and reset.
3. View a list of departments. For each department, you see:
  - **Department Name**: The name of the department.
  - **Comments**: The comments regarding the department if they are available.
  - **Actions**: The actions you can use for a department.
4. Sort by Department Name in ascending or descending order.
5. View a department.
6. Go to the [Users](https://help.zscaler.com/zdx/about-user-management) page to manage your users or go to the [Groups](https://help.zscaler.com/zdx/viewing-groups) page to manage your groups.

[Image: Departments page on ZDX Admin Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-diagnostics","lastmod":"2026-07-23T21:06Z","nid":"1370081"} -->
## About Diagnostics

- Source: https://help.zscaler.com/zdx/about-diagnostics
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Diagnostics > About Diagnostics
- Last modified: 2026-07-23T21:06Z
- Summary: Overview information about Diagnostics in the [variable:zscaler-admin-console]].

[Watch a video about Diagnostics](https://fast.wistia.net/embed/iframe/e2o61x5z73) (shows legacy UI).

Diagnostics in ZDX can provide deeper granularity into process-level information for a user. During a Diagnostics session, information is collected every minute for the Web probe and Cloud Path probe, as well as device statistics.

## Prerequisites

To start a Diagnostics session, ensure:

- You're running the minimum required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#Diagnostics).
- Your ZDX subscription level supports Diagnostics. To learn more, see [Ranges and Limitations](https://help.zscaler.com/unified/ranges-limitations).
- Your admin role is configured for Diagnostics. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

If you still can't view the Diagnostics details in the Zscaler Admin Console, contact your ZDX admin to check your permissions.

You can [start a Diagnostics session](https://help.zscaler.com/zdx/starting-new-diagnostics-session) to analyze issues that a user, device, or application is facing. You can choose to run the session to monitor issues from 5 minutes to 60 minutes. You can [view session information](https://help.zscaler.com/zdx/evaluating-diagnostics-session-information) on the Diagnostics page and also export it as a PDF file for reference and information sharing.

Diagnostics provides the following benefits and enables you to:

- Configure Diagnostics sessions to capture granular details. For example, Packet Capture (PCAP) information, device statistics, or application information.
- Analyze, evaluate, and troubleshoot issues for a user, device, or application.
- Share the results and information of the Diagnostics session for reference by exporting it to a PDF file.

## About the Diagnostics Page

On the Diagnostics page (Analytics > Digital Experience > Troubleshooting > Diagnostics), you can do the following:

1. [View information on sessions in the In Progress table](https://help.zscaler.com/zdx/evaluating-diagnostics-session-information).
2. [View PCAP information](https://help.zscaler.com/zdx/evaluating-diagnostics-session-information#PCAP) and copy specific PCAP information (e.g., Packet Capture Filter, Network Interface) to your clipboard.
3. End a session in the In Progress table.
4. [View information on sessions in the History table](https://help.zscaler.com/zdx/evaluating-diagnostics-session-information).
5. [View the individual Diagnostics session](https://help.zscaler.com/zdx/viewing-diagnostics-session-results) and export a PDF file of the results.
6. [Copy the details of the selected Diagnostics session](https://help.zscaler.com/zdx/starting-new-diagnostics-session) and start a new Diagnostics session with all details of an existing session copied. All fields are editable.
7. Delete a session by using the Delete icon. Deleting a session removes it from the Diagnostics page.
8. [Start a new session.](https://help.zscaler.com/zdx/starting-new-diagnostics-session) To start a Diagnostics session, an active probe is required on the device. The active probe must run for a minimum duration of 30 minutes. To learn more, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe).

[Image: Diagnostics Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-groups","lastmod":"2025-08-22T13:34Z","nid":"1464221"} -->
## About Groups

- Source: https://help.zscaler.com/zdx/about-groups
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > User Management > About Groups
- Last modified: 2025-08-22T13:34Z
- Summary: Information on the Zscaler Groups page in the ZDX Admin Portal.

[Watch a video about Groups in ZDX.](https://fast.wistia.net/embed/iframe/n28kybdi5x)

Groups categorize users into different admin groups. The groups were created in the ZIA Admin Portal and are seen in the ZDX Admin Portal. To learn more, see Zscaler Internet Access (ZIA)'s [About Groups](https://help.zscaler.com/zia/about-groups).

Groups provide the following benefits and enable you to:

- Identify your organization's groups from the ZIA Admin Portal.
- Search for a specific group.
- Export a list of groups into a CSV file.

## About the Groups Page

On the Groups page (Administration > Administration Controls > User Management > Groups), you can do the following:

1. Download a list of groups in CSV format.
2. Search for a specific group. Enter the search term in the Search bar. To delete content in the Search bar, click the **Delete** icon to cancel and reset.
3. View a list of groups. For each group, you see:
  - **Group Name**: The name of the group.
  - **Comments**: The comments regarding the group if they are available.
  - **Actions**: The action you can use for a group.
4. Sort by Group Name.
5. View a selected group.
6. Go to the [Users](https://help.zscaler.com/zdx/about-user-management) page.
7. Go to the [Departments](https://help.zscaler.com/zdx/viewing-departments) page.

[Image: Groups Page on ZDX Admin Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-integrations","lastmod":"2026-06-25T21:06Z","nid":"1478616"} -->
## About Integrations

- Source: https://help.zscaler.com/zdx/about-integrations
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Tenant Integrations > About Integrations
- Last modified: 2026-06-25T21:06Z
- Summary: Overview information about SaaS application tenant integrations with ZDX.

Zscaler uses integrations to provide visibility and security for SaaS applications used in your organization. You can authorize SaaS applications with Zscaler by adding them as tenants. In ZDX, you can configure an integration to a tenant for data collection, or webhooks.

Integration provides the following benefits and enables you to:

- Configure integrations (e.g., Microsoft Intune) for data collection to access Endpoint Analytics.
- Configure webhooks to create alert notifications.

## About the Integrations Page

On the Integrations page (Policies > Digital Experience Monitoring > Settings > Data Collection Integrations), you can do the following:

1. Search for a data collection integration.
2. [Add a new integration for Microsoft Intune data collection](https://help.zscaler.com/zdx/configuring-microsoft-intune-zdx).
3. Select which column fields to display.
4. View the following for each data collection integration:
  - **Name**: The name of the data collection integration.
  - **Vendor**: The vendor name.
  - **Onboarded On**: The date the integration was configured.
  - **Status**: The status of the integration.
  - **Actions**: Manage your data collection integration.
5. [Configure a webhook for alerting.](https://help.zscaler.com/zdx/configuring-webhooks)

[Image: Integrations Overview]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-labels","lastmod":"2026-07-10T10:00Z","nid":"1461826"} -->
## About Labels

- Source: https://help.zscaler.com/zdx/about-labels
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Alert Rule Configuration > About Labels
- Last modified: 2026-07-10T10:00Z
- Summary: Provides an overview of labels that are used to group alert rules.

Admins can create labels and then assign the labels as an option during alert rule configuration. After assigning the label, the label becomes part of the alert details and is seen in the delivered alert details when you configure webhooks.

Labels provide the following benefits and enable you to:

- Create and apply multiple labels to an alert rule for grouping purposes.
- Increase the number of alert details for ease of use when configuring webhooks.

To manage and assign labels, you must have the Full Alerts permission. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

On the Labels page (Policies > Digital Experience Monitoring > Labels), you can do the following:

1. Search for a label.
2. [Add a new label.](https://help.zscaler.com/zdx/managing-labels#addlabel) After creating the label, you can assign it to an alert rule in either theAdd New Alert Rule or Edit Alert Rule window. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule) and [Editing an Alert Rule](https://help.zscaler.com/zdx/editing-alert-rule).
3. Select which table options to display on the created labels list. You can also sort the labels by clicking the Sort icon next to the column name.
4. View a list of created labels. For each label, you can view:
  - **Name**: The label name.
  - **Description**: The label description.
  - **Alert Rules Tagged**: The number of alert rules tagged with the label.
  - **Actions**: The actions you can do for a label.
5. [View the label.](https://help.zscaler.com/zdx/managing-labels#viewlabel)
6. [Edit the label.](https://help.zscaler.com/zdx/managing-labels#editlabel)
7. [Delete the label.](https://help.zscaler.com/zdx/managing-labels#deletelabel) You cannot delete a label if an alert rule is tagged with it.

[Image: Labels Overview Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-locations","lastmod":"2025-10-10T09:12Z","nid":"1379346"} -->
## About Locations

- Source: https://help.zscaler.com/zdx/about-locations
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > User Management > About Locations
- Last modified: 2025-10-10T09:12Z
- Summary: Information about Location Management in the ZDX Admin Portal

[Watch a video about Locations in ZDX.](https://fast.wistia.net/embed/iframe/9zellefvwh)

Locations identify the various networks from which your organization sends its internet traffic. When an organization forwards its traffic to the Zscaler service through a GRE or IPSec tunnel, Zscaler provisions your organization's IP addresses, which are then displayed as locations in the ZDX Admin Portal. You can view sublocation information and perceive individual traffic information from a known or unknown location.

Locations provide the following benefits and enables you to:

- Identify locations from which your organization sends its traffic.
- Search for a location or sublocation.
- Sort and customize columns to export a list of locations into a CSV file.

## About the Locations Page

On the Locations page (Administration > Location Management), you can do the following:

1. Download a list of locations and sublocations in CSV format.
2. Manage filters for the Locations list.
3. Search by Name or IP address for a specific location or sublocation. Click the **X** icon in the Search bar to cancel and reset.
4. View a list of all locations and sublocations that were configured for your organization. You can see:
  - **Name**: The name of the location or sublocation.
  - **Sublocations**: The number of sublocations assigned to the location.
  - **IP Addresses**: The static IP addresses for your local gateway for the location.
  - **Proxy Ports**: The [subscribed proxy ports](https://help.zscaler.com/zia/configuring-dedicated-proxy-ports) for the location, if applicable.
  - **Use XFF from Client Request**: Indicates whether the [Use XFF from Client Request](https://help.zscaler.com/zia/how-do-i-add-location#EnableXFFForwarding) feature is enabled for the location.
  - **Authentication**: Indicates whether the [Enforce Authentication](https://help.zscaler.com/zia/how-do-i-add-location#EnforceAuthentication) feature is enabled for the location.
  - **Firewall Filtering**: Indicates whether the [Enforce Firewall Control](https://help.zscaler.com/zia/how-do-i-add-location#EnforceFirewallControl) feature is enabled for the location.
  - **Bandwidth**: If the [Enforce Bandwidth Control](https://help.zscaler.com/zia/how-do-i-add-location#EnforceBandwidthControl) feature is enabled for the location, the download and upload bandwidth limits are displayed in Mbps.
  - **Group**: The location group associated with the location and its sublocations.
  - **Location Type**: The type of location associated with the location and its sublocations.
5. View the sublocations assigned to the location.
6. View details about a location by clicking the **View** icon.
7. Customize which columns to display.

[Image: Location Management in ZDX Admin Portal]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-rules","lastmod":"2026-07-17T13:56Z","nid":"1364451"} -->
## About Rules

- Source: https://help.zscaler.com/zdx/about-rules
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Alert Rule Configuration > About Rules
- Last modified: 2026-07-17T13:56Z
- Summary: Provides an overview about alert rules in ZDX.

[Watch a video about alerts and alert rules in ZDX.](https://fast.wistia.net/embed/iframe/5ozc9luba9)

The Rules page for ZDX provides an overview of details of all the configured rules, including each rule's status and alert delivery method. Rules allow you to configure metrics for alert throttling when a predetermined threshold is met.

Rules provide the following benefits and enable you to configure rules by:

- Grouping ZDX Scores based on Departments, Cities, Organization, Region, or Zscaler Locations (specified Rule Type as ZDX Score).
- Providing application details (e.g., ZDX Score, Page Fetch Time) that meets the predefined threshold (specified Rule Type as Application).
- Customizing alert triggering based on predefined throttling thresholds and In Group criteria (e.g., Organization, Cities).

The predetermined threshold is defined when you create a rule for alert throttling. To learn more, see [Understanding Alert Triggers](https://help.zscaler.com/zdx/understanding-alert-triggers).

If the rules are not muted, you can configure alerts to be sent via email or webhooks. If the rules are muted, you can view the alerts in the Zscaler Admin Console and no information is sent via email or webhooks.

In the Rules page, you can use the following filters:

- **All Applications & Probes**: You can filter for specific applications or probes.
- **All Severities**: You can filter for rules configured for High, Medium, or Low severity.
- **All Locations**: You can filter for specific locations.

Apply filters to further sort the data. By default, all filters are applied and the values are set to **All**. Click **Reset** to reset the filters.

## About the Rules Page

On the Rules page (Administration > Alerts > Digital Experience Management > Rules), you can view the following for all the configured rules:

- **Rule Name**: The alert rule name entered at configuration
- **Status**: This can be Enabled or Disabled.
- **Last Triggered**: The date and time that the rule last triggered an alert.
- **Type**: This is for an Application, Device, or Network.
- **Application**: The application associated with this rule.
- **Probe**: The probe configured for this application.
- **Alert Delivery Method**: Email or webhook, if configured. If the rule is muted, or no alert delivery method has been configured, this field is empty.

In the Rules page, you can do the following:

1. Configure a new rule by clicking **Add Alert Rule**. To learn more, see [Configuring a Rule](https://help.zscaler.com/zdx/configuring-alert-rule).
2. Apply filters to sort rules and view details or hide the filters if you do not want to display them.
3. Sort the rules by column name or organize to select which rule columns to display.
4. View criteria details of configured rules. Click the arrow to the left of the rule name to view details.
5. Edit, mute, copy, or delete a rule. To learn more, see [Editing a Rule](https://help.zscaler.com/zdx/editing-alert-rule). The Edit icon opens the Edit a Rule window for the selected rule. The Copy icon opens the Add New Alert Rule window with editable fields and copied details of the selected rule.
6. Access the navigation menu to go to the following pages:
  - [Alerts](https://help.zscaler.com/zdx/about-alerts)
  - [Templates](https://help.zscaler.com/zdx/about-templates)

[Image: View all configured alert rules for ZDX]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-scripts","lastmod":"2026-05-01T15:21Z","nid":"1531183"} -->
## About Scripts

- Source: https://help.zscaler.com/zdx/about-scripts
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Remediation > About Scripts
- Last modified: 2026-05-01T15:21Z
- Summary: Overview information on the Scripts page where you can manage the number of scripts.

You can run PowerShell scripts remotely to schedule system updates, gather information on the issue, or fix the issue on impacted devices. You can configure for two types of scripts: customized scripts or predefined scripts. If you create a customized script or import a predefined script and configure it specific to your needs, then you can store the scripts for on-demand or later use. You can then monitor the progress of any script jobs on the Remediation Jobs page.

Scripts provide the following benefits and enable you to:

- Upload custom scripts that are specific to the customer's specific use cases.
- Import predefined scripts that are readily available and do not require any signing.
- Create flexible parameters when configuring a script that can be sent or create confirmation pop-ups for different scenarios.

Prior to running a custom script, all custom scripts must be [signed with a certificate](https://help.zscaler.com/zdx/preparing-certification-and-signing-script). Zscaler recommends testing all scripts (custom or predefined) in a non-production environment prior to deploying them to users to ensure the script is working adequately and as expected.

## Prerequisites

To access the Scripts page, you must have the following:

- The appropriate ZDX subscription level to support scripts. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- The appropriate permission level to configure scripts. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
  - Script Management
  - Script Templates
- Enabled Remediation in the Zscaler Admin Console. To learn more, see [Configuring Remediation Settings](https://help.zscaler.com/zdx/configuring-remediation-settings).

## About the Scripts Page

On the Scripts page (Policies > Digital Experience Monitoring > Scripts), you can do the following:

1. [Add a script.](https://help.zscaler.com/zdx/managing-scripts#add)
2. Search for scripts. Click the **Cancel** icon to reset your search.
3. View a list of all configured scripts. For each script, you can see:
  - **Name**: The name of the script. Click the **name** or the **View** icon to view the script details where you can run, download, or copy the script. You can view the parameters and preview the end user confirmation message if they are [defined](https://help.zscaler.com/zdx/managing-scripts#add) in the script. See image. This is the same action as viewing the script.
  - **Description**: The description of the script.
  - **Type**: The type of script (**Custom** or **Predefined**).
  - **Operating System**: The operating system the script can run on.
  - **Status**: The status of the script (**Enabled**, **Disabled**, or **Revoked**).
    - **Enabled**: The remote script is enabled for remediation job use.
    - **Disabled**: The remote script is disabled from remediation job use.
    - **Revoked**: The remote script is outdated and cannot run as there is a newer version of the predefined script from the [Scripts Templates](https://help.zscaler.com/zdx/managing-scripts) page. Remove the outdated script and import the latest predefined script. See image.
  - **Created By**: The user that created the script.
  - **Created On**: The timestamp of when the script was created.
  - **Last Modified**: The timestamp of when the script was last modified.
  - **Last Run**: The timestamp of when the script last ran.
4. Modify the columns displayed in the table.
5. View the script. This is the same action as clicking the script name.
6. [Run the script.](https://help.zscaler.com/zdx/managing-scripts#run) If the script is disabled, then you cannot run the script. [Enable it](https://help.zscaler.com/zdx/managing-scripts#edit) to run the script.
7. [Edit the script.](https://help.zscaler.com/zdx/managing-scripts#edit)
8. [Delete the script.](https://help.zscaler.com/zdx/managing-scripts#delete)
9. Go to the [Remediation Jobs](https://help.zscaler.com/zdx/viewing-managing-remediation-jobs) page to manage and view your script runs or go to the [Script Templates](https://help.zscaler.com/zdx/managing-scripts#predefined) to see predefined scripts.

[Image: View the Remote Scripts page]

[Image: Run, download, or copy remote script details]

[Image: Viewing Revoked script]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-templates","lastmod":"2026-07-10T10:00Z","nid":"1488201"} -->
## About Templates

- Source: https://help.zscaler.com/zdx/about-templates
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Alert Rule Configuration > About Templates
- Last modified: 2026-07-10T10:00Z
- Summary: Provides configuration steps on how to create a template for alert rules.

[Watch a video about Templates in ZDX](https://fast.wistia.net/embed/iframe/t4mafl179c) (shows legacy UI).

You can create templates to save frequently used alert rule criteria. After saving a template, you can then use it to prefill an alert rule when you configure a Zscaler Managed probe. To learn more, see [Configuring Zscaler Managed Probes](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes).

Templates provide the following benefits and allow you to:

- Create prefilled criteria and fields when configuring an alert rule for Zscaler Managed probes.
- Save time and maintain consistency when configuring alert rules.
- Have the flexibility to configure an alert rule.

## About the Templates Page

On the Templates page (Analytics > Digital Experience > Alerts > Alert Rules and Templates > Templates), you can do the following:

1. [Add a new template](https://help.zscaler.com/zdx/managing-templates).
2. Configure the columns to display.
3. View a list of created templates.
  - **Name**: The template's name during configuration.
  - **Severity**: The severity level of the event. Red indicates high severity, orange is medium severity, and green indicates low severity.
  - **Type**: The template type is **Predefined** or **Custom**.
  - **Probe Type**: The probe type is **Cloud Path** or **Web**.
  - **Criteria**: The alert rule criteria configured for the template. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).
  - **Actions**: Edit, copy, or delete the template. To learn more, see [Managing Templates](https://help.zscaler.com/zdx/managing-templates). You cannot edit or delete a predefined template. You can copy the predefined template.
4. Hover and click the arrow to use the filters to sort and view templates.
5. [Edit the template.](https://help.zscaler.com/zdx/managing-templates)
6. [Copy the template.](https://help.zscaler.com/zdx/managing-templates)
7. [Delete the template.](https://help.zscaler.com/zdx/managing-templates)
8. Go to the [Rules](https://help.zscaler.com/zdx/about-rules) page to configure rules for alerts.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-webhooks","lastmod":"2026-07-07T19:18Z","nid":"1534194"} -->
## About Webhooks

- Source: https://help.zscaler.com/zdx/about-webhooks
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > About Webhooks
- Last modified: 2026-07-07T19:18Z
- Summary: Information about webhooks for ZDX.

[Watch a video about Configuring Webhooks](https://fast.wistia.net/embed/iframe/sizniej3b3) (shows legacy UI).

Webhooks are a great way to send alerts about events (i.e., incidents, network performance) on your application. When an event occurs, the webhook sends real-time data to a specific URL or API endpoint as an HTTP callback.

Webhooks provide the following benefits and enable you to:

- Receive real-time updates on events (i.e., incidents, network performance) on your application.
- Customize which events can trigger the webhook to send data.
- Automates the workflow of alert notification.

## About the Webhooks Page

On the Webooks page (Policies > Digital Experience Monitoring > Webhooks), you can do the following:

1. Search for a webhook.
2. [Add a webhook.](https://help.zscaler.com/zdx/configuring-webhooks)
3. Adjust the columns for display.
4. View a list of all configured webhooks. For each webhook, you can see:
  - **Name**: The name of the webhook.
  - **URL**: The specified URL where the webhook sends data.
  - **Authentication Type**: The type of authentication for the webhook (**Basic**, **Token**, **OAuth**).
  - **Status**: The status of the webhook (**Enabled**, **Disabled**).
5. Edit the webhook.
6. Delete the webhook.
7. Go to the [Data Collection](https://help.zscaler.com/zdx/about-integrations) page.

[Image: View the Webhooks page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/about-zdx-role-based-administration","lastmod":"2026-04-28T11:28Z","nid":"1358781"} -->
## About ZDX Role-Based Administration

- Source: https://help.zscaler.com/zdx/about-zdx-role-based-administration
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > About ZDX Role-Based Administration
- Last modified: 2026-04-28T11:28Z
- Summary: Introduces information about role-based administration for ZDX admins.

[Watch a video about ZDX Role-Based Administration.](https://fast.wistia.net/embed/iframe/aouq1i024g)

With role-based administration, organizations can easily add admins and assign them multiple or specific roles with different levels of access. It is possible to add admins in ZDX who are also admins in Internet & SaaS.

ZDX Role-Based Administration provides the following benefits and enables you to:

- Facilitate, organize, and manage administration roles for specified responsibilities and permissions.
- Assign admins to multiple or specific roles with varying levels of access.
- Provide obfuscation permissions to limit functionalities as required.

Your Internet & SaaS credentials can't be used to log in to Digital Experience. For example, an Internet & SaaS admin can't log in to Digital Experience if their organization is not using that service.

Attributes configured in ZDX (excluding Login ID) are overwritten across all admin profiles. If you create an admin profile in Digital Experience, using the same credentials used in a Internet & SaaS admin profile, all attributes except Login ID created in Digital Experience overwrite the attributes in Internet & SaaS. To learn more, see [Adding ZDX Admins](https://help.zscaler.com/zdx/adding-zdx-admins) and [Adding Internet & SaaS Admins](https://help.zscaler.com/zia/adding-zia-admins).

## About ZDX Roles

Depending on your permissions in Digital Experience or Internet & SaaS, certain functions are limited. For example, an admin cannot edit their own role. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

For each admin, you can choose from one of the following predefined roles:

- **ZDX Super Admin**: An admin with this role has read, add, edit, delete, and manage permissions in Digital Experience. The default admin account uses this role.
- **ZDX Read-Only Admin**: An admin with this role only has read permissions in Digital Experience.
- **ZDX Service Desk Tier 1**: An admin with this role has read permissions to the Users Dashboard and access to the User Search. To learn more, see the [Understanding the ZDX Service Desk Role](https://help.zscaler.com/zdx/about-zdx-service-desk-role).

If you are an admin with ZDX Super Admin level privileges, you can also create a custom ZDX Role for your organization. Zscaler recommends that you add roles before adding admins, because you must select a role for each admin that you create.

## About the Role Management Page

On the Role Management page, certain options are not available if you are subscribed to Authentication Service due to read-only permissions. Instead, you can manage and configure the options in Authentication Service.

On the Role Management page (Administration > Admin Management > Role Based Access Control > Digital Experience), you can do the following:

1. [Add a new role](https://help.zscaler.com/zdx/adding-zdx-roles).
2. Export a CSV file of ZDX roles.
3. Search for a role.
4. View a list of all admin roles that are configured for your organization. For each role, you can see:
  - **Name**: The name of the role.
  - **Full Access**: Which Full Access the role has.
  - **View-Only Access**: Which View-Only Access the role has.
  - **Visible**: Which information the role can view.
  - **Obfuscated**: Which information the role cannot view.
  - **Actions**: Displays which actions you can take for the role. ZDX roles to which you have Full access are indicated by the **Edit** and **Delete** icon. If you have View-Only access, then the ZDX Role shows a **View** icon and you cannot edit or delete them.
5. [Edit, delete, or view the ZDX Role details](https://help.zscaler.com/zdx/managing-zdx-roles).

[Image: Role Management - Add ZDX Role]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/adding-zdx-roles","lastmod":"2026-06-10T13:02Z","nid":"1358811"} -->
## Adding ZDX Roles

- Source: https://help.zscaler.com/zdx/adding-zdx-roles
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Adding ZDX Roles
- Last modified: 2026-06-10T13:02Z
- Summary: Information on how to create administration roles in the Zscaler Admin Console.

ZDX roles are used by admins to create levels of permissions for other admin users within an organization. To learn more, see [About ZDX Role-Based Administration](https://help.zscaler.com/zdx/about-zdx-role-based-administration).

Access permissions for some features depend on the subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).

To add a ZDX role:

1. Go to **Administration**>**Admin Management**>**Role Based Access Control**>**Digital Experience**.
2. Click **Add ZDX Role**. The **Add ZDX Role** window appears.
3. In the **Add ZDX Role** window: See image.
  - **Name**: Enter a name for the role.
  - **Permissions**: Select the permissions for the administrator role:
    - Dashboard Access
    - Device and User Information
    - UCaaS Monitoring
    - Analytics
    - Configuration Access
    - Diagnostics
    - Administrator Management
    - Alerts
    - Locations
    - Webhooks
    - User Management
    - Zscaler Client Connector Portal
    - Remote Assistance Management
    - Remediation
    - Self Service
    - Wi-Fi Dashboard
    - Device Health Scoring
    - Inventory Management
    - Copilot
    - Time Duration
4. Click **Save** and [activate the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

[Image: Add ZDX Role Window]

Choose one of the following permissions for access to the predefined dashboards and overviews:

- **View Only**: Allows admins to view all dashboards and overviews.
- **None**: Does not allow admins access to dashboards and overviews.
- **Custom**: Allows admins to view specific dashboards or overviews.

If the Dashboard permission setting is set to View Only or the UCaaS permission setting is set to View Only or Full, you can view the ZDX Dashboard and applications.

For **Custom**, choose to give the following specified permissions for access to specific dashboards or overviews:

- ZDX Dashboard
- Application Overview
- Application Dashboard
- User Overview
- User Dashboard
- Incident Dashboard
- Probe Assignments
- ZIA PSE Health Dashboard
- Network Intelligence
- Device Events

Choose one of the following permissions for access to the Performance dashboard:

- **View Only**: Allows admins to view the Performance dashboard.
- **None**: Does not allow admins access to the Performance dashboard.

To learn more, see [Monitoring the Performance Dashboard](https://help.zscaler.com/zdx/monitoring-performance-dashboard).

Choose one of the following permissions for access to the Application Overview:

- **View Only**: Allows admins to view the Application Overview.
- **None**: Does not allow admins access to the Application Overview.

To learn more, see [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

Choose one of the following permissions for access to the Application details:

- **View Only**: Allows admins to view the Application Details.
- **None**: Does not allow admins access to the Application Details.

To learn more, see [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

Choose one of the following permissions for access to the Users Overview:

- **View Only**: Allows admins to view the Users Overview.
- **None**: Does not allow admins access to the Users Overview.

To learn more, see [Monitoring the Users Overview](https://help.zscaler.com/zdx/monitoring-users-overview).

Choose one of the following permissions for access to the User Details:

- **View Only**: Allows admins to view the User Details.
- **None**: Does not allow admins access to the User Details.

To learn more, see [Evaluating User Details](https://help.zscaler.com/zdx/evaluating-user-details).

Choose one of the following permissions for access to the Incidents dashboard:

- **View Only**: Allows admins to view the Incidents dashboard.
- **None**: Does not allow admins access to the Incidents dashboard.

To learn more, see [Monitoring the Incidents Dashboard](https://help.zscaler.com/zdx/monitoring-incidents-dashboard).

Choose one of the following permissions for access to the Probe Assignment system-generated report:

- **View Only**: Allows admins to view the Probe Assignment system-generated report.
- **None**: Does not allow admins access to the Probe Assignment system-generated report.

To learn more, see [Viewing System-Generated Reports](https://help.zscaler.com/zdx/viewing-system-generated-reports).

Choose one of the following permissions for access to the Zscaler Internet Access (ZIA) Private Service Edge Health dashboard:

- **View Only**: Allows admins to view the ZIA Private Service Edge Health dashboard.
- **None**: Does not allow admins to manage or view the ZIA Private Service Edge Health dashboard.

To learn more, see [Monitoring the ZIA Private Service Edge Dashboard](https://help.zscaler.com/zdx/monitoring-zia-private-service-edge-dashboard).

Choose one of the following permissions for access to the Network Intelligence dashboard:

- **View Only**: Allows admins to view the Network Intelligence dashboard.
- **None**: Does not allow admins to manage or view the Network Intelligence dashboard.

To learn more, see [Monitoring the Network Intelligence Dashboard](https://help.zscaler.com/zdx/monitoring-network-intelligence-dashboard).

Choose one of the following permissions for access to the Device Events reports:

- **View Only**: Allows admins to view the Device Events reports.
- **None**: Does not allow admins to manage or view the Device Events reports.

To learn more, see [Viewing the Device Events Reports](https://help.zscaler.com/zdx/viewing-device-events-reports).

Choose one of the following permissions for access to the Applications and Probes configuration:

- **Full**: Allows admins full access to Configuration Access.
- **View Only**: Allows admins to view what has been set up in Configuration Access.
- **None**: Does not allow admins to have access to Configuration Access.

You cannot configure for users when the User Namepermission is set to Obfuscated.

To learn more, see [Configuration](https://help.zscaler.com/zdx/configuration).

If the Configuration Access permission setting is set to Full:

- You can enable data collection for Software and Device Inventory on the Inventory Settings page. To learn more, see [Configuring Inventory Settings](https://help.zscaler.com/zdx/configuring-inventory-settings).
- You can configure Zscaler Hosted Probes. To learn more, see [Configuring Zscaler Hosted Probes](https://help.zscaler.com/zdx/configuring-zscaler-hosted-probes).

Choose one of the following permissions for access to Administrator Management, Role Management, Location Management, and Audit Logs:

Although roles are created in Role Management, they are managed by Super Admins.

- **Full**: Allows admins full access to Administrator Management.
- **View Only**: Allows admins to view what has been set up in Administrator Management.
- **None**: Does not allow admins to have access to Administrator Management.

Choose one of the following permissions for access to the Zscaler Client Connector Portal:

- **Full**: Allows admins full access to manage the Zscaler Client Connector Portal.
- **View Only**: Allows admins to view the current setup in the Zscaler Client Connector Portal.
- **None**: Does not allow admins access to the Zscaler Client Connector Portal.

Choose one of the following permissions for access to the device and user information:

- **Visible**: Allows admins to view device and user information.
- **Obfuscated**: Does not allow admins to view device and user information.
- **Custom**: Allows admins to view specific device and user information.

For **Custom**, choose to give the following specified permissions for access to User Name, Location, Device Name, or IP Address:

- User Name
- Location
- Device Name
- IP Address
- Wi-Fi Name

If Zscaler Client Connector's Collect Machine Hostname Information or Collect Device Owner Information is disabled, then ZDX obfuscates BSSID, SSID, Device Name, and hostname. To learn more, see [About User Privacy](https://help.zscaler.com/client-connector/about-user-privacy).

Choose one of the following permissions for access to view the user name:

- **Visible**: Allows admins to view the user name.
- **Obfuscated**: Does not allow admins to view the user name.

If the User Name permission is set to Visible, then an admin has access to User Search and the ability to download a table in CSV format for Software Inventory. If the User Name permission is set to Obfuscated, then the admin does not have access to User Search and cannot download a table in CSV format for Software Inventory.

Choose one of the following permissions for access to the user location:

- **Visible**: Allows admins to view the user location.
- **Obfuscated**: Does not allow admins to view the user location.

Location obfuscation occurs on Users Overview and User Details pages only.

Choose one of the following permissions for access to the device name:

- **Visible**: Allows admins to view the device name.
- **Obfuscated**: Does not allow admins to view the device name.

If the Device Name permission is set to Visible, then an admin has the ability to download a table in CSV format for Software Inventory. If the Device Name permission is set to Obfuscated, then the admin cannot download a table in CSV format for Software Inventory.

Choose one of the following permissions for access to the device's IP Address:

- **Visible**: Allows admins to view the device's IP Address.
- **Obfuscated**: Does not allow admins to view the device's IP Address.

Choose one of the following permissions for access to the device's Wi-Fi Name:

- **Visible**: Allows admins to view the device's Wi-Fi Name.
- **Obfuscated**: Does not allow admins to view the device's Wi-Fi Name.

Choose one of the following permissions for access to Users, Groups, and Departments in User Management:

- **View Only**: Allows admins to view what has been set up in User Management.
- **None**: Does not allow admins to view the User Management setup.

Choose one of the following permissions for access to Zscaler locations:

- **View Only**: Allows admins to view Zscaler locations.
- **None**: Does not allow admins to view Zscaler locations.

Choose one of the following permissions for access toRemote Assistance:

- **Full**: Allows admins to have full access to Remote Assistance Management.
- **View Only**: Allows admins to view what has been set up in Remote Assistance Management.
- **None**: Does not allow admins access to Remote Assistance Management.

View or manage the following [Remediation](https://help.zscaler.com/zdx/understanding-remediation) pages:

- Script Management
- Script Templates
- Remediation Jobs
- Remediation Settings

Choose one of the following permissions for access to Diagnostics:

- **Full**: Allows admins to manage and view Diagnostics sessions, and also view and export the results.
- **View Only**: Allows admins to view details of Diagnostics sessions, but not export the results.
- **None**: Does not allow admins to manage or view Diagnostics sessions or results.

If User Name and Device Information are set to Obfuscated, then Diagnostics is not accessible.

Choose one of the following permissions for access to Alerts:

- **Full**: Allows admins to configure and view alerts, and also receive alert notifications.
- **View Only**: Allows admins to view alerts and receive alert notifications, depending on rule configuration.
- **None**: Does not allow admins to manage or view alerts, nor receive alert notifications.

If you have Full permission for Alerts:

- You can manage alert rules on the Rules page. To learn more, see [About Rules](https://help.zscaler.com/zdx/about-rules).
- You can assign labels to an alert rule. You can also create or assign a Workflow Automation as an alert delivery method. To learn more, see [About Labels](https://help.zscaler.com/zdx/about-labels).
- You can manage templates on the Templates page. To learn more, see [About Templates](https://help.zscaler.com/zdx/about-templates).

Choose one of the following permissions for access to Webhooks:

- **Full**: Allows admins to create, manage, and view webhooks.
- **View Only**: Allows admins to view webhooks.
- **None**: Does not allow admins to manage or view webhooks.

To learn more, see [Configuring Webhooks](https://help.zscaler.com/zdx/configuring-webhooks).

Choose one of the following permissions for access to UCaaS Monitoring:

- **Full**: Allows admins to manage and view Call Quality applications and Call Quality Meetings pages.
- **View Only**: Allows admins to view Call Quality applications and Call Quality Meetings pages, but not manage them.
- **None**: Does not allow admins to manage or view Call Quality applications or Call Quality Meetings pages.
- **Custom**: Allows admins to manage and view specific Call Quality applications and Call Quality Meetings pages.

If the Dashboard permission setting is set to View Only or the UCaaS permission setting is set to View Only or Full, you can view the ZDX Dashboard and applications.

For **Custom**, choose to give the following specified permissions for access to manage or view specific Call Quality Applications and Call Quality Meetings pages:

- Call Quality Configuration
- Call Quality Meetings
- Call Quality Applications

Choose one of the following permissions for access to Call Quality Configuration pages:

- **Full**: Allows admins to manage and view Call Quality Configuration pages.
- **View Only**: Allows admins to view Call Quality Configuration pages, but not manage them.
- **None**: Does not allow admins to manage or view Call Quality Configuration pages.

Choose one of the following permissions for access to Call Quality Meetings pages:

- **Full**: Allows admins to manage and view Call Quality Meetings pages.
- **View Only**: Allows admins to view Call Quality Meetings pages, but not manage them.
- **None**: Does not allow admins to manage or view Call Quality Meetings pages.

Choose one of the following permissions for access to Call Quality Applications pages:

- **Full**: Allows admins to manage and view Call Quality Applications pages.
- **View Only**: Allows admins to view Call Quality Applications pages, but not manage them.
- **None**: Does not allow admins to manage or view Call Quality Applications pages.

Choose one of the following permissions for access to Inventory Management pages:

- **Full**: Allows admins full access to Inventory Management pages.
- **View Only**: Allows admins to view the current setup on the Inventory Management pages, but not manage them.
- **None**: Does not allow admins access to Inventory Management pages.

Inventory Management includes the Software, Hardware, and Process Settings pages.

To learn more, see [Inventory](https://help.zscaler.com/zdx/analytics/inventory).

Choose one of the following time durations for the ZDX Role:

- 2 hours
- 4 hours
- 6 hours
- 12 hours
- 24 hours
- 48 hours
- Full Access

The default is set to Full Access.

Choose one of the following permissions for access to Analytics:

- **Full** or **View Only**: Allows admins to view Analytics pages, but not manage them.
- **None**: Does not allow admins to manage or view Analytics pages.
- **Custom**: Allows admins to manage and view specific Analytics pages.

For **Custom**, choose to give the following specified permissions for access to manage or view specific Analytics pages:

- QBR
- System Generated Reports
- ZDX Snapshots
- Data Explorer
- Hosted Monitoring

Choose one of the following permissions for access to view the System-Generated Reports page:

- **Full** or **View Only**: Allows admins to view the System-Generated Reports page, but not manage them.
- **None**: Does not allow admins to manage or view the System-Generated Reports page.

To learn more, see [Viewing System-Generated Reports](https://help.zscaler.com/zdx/viewing-system-generated-reports).

Choose one of the following permissions for access to view the Quarterly Business Review (QBR) page:

- **Full** or **View Only**: Allows admins to view the QBR page, but not manage it.
- **None**: Does not allow admins to manage or view the QBR page.

To learn more, see [Viewing Quarterly Business Review Reports](https://help.zscaler.com/zdx/viewing-quarterly-business-review-reports).

Choose one of the following permissions for access to the ZDX Snapshots page:

- **Full**: Allows admins to manage the ZDX Snapshots page.
- **View Only**: Allows admins to view the ZDX Snapshots page.
- **None**: Does not allow admins to manage or view the ZDX Snapshots page.

To learn more, see [Sharing ZDX Snapshots](https://help.zscaler.com/zdx/sharing-zdx-snapshots).

Choose one of the following permissions for access to the Data Explorer page:

- **Full**: Allows admins to manage their views on the Data Explorer page.
- **View Only**: Allows admins to view their Data Explorer page.
- **None**: Does not allow admins to manage or view their Data Explorer page.

An admin cannot manage another admin's Data Explorer views.

To learn more, see [Monitoring Data Explorer](https://help.zscaler.com/zdx/monitoring-data-explorer-views) and [Configuring Data Explorer](https://help.zscaler.com/zdx/configuring-data-explorer-views).

Choose one of the following permissions for access to the Zscaler Hosted Monitoring page:

- **Full** or **View Only**: Allows admins to view the Zscaler Hosted Monitoring page, but not manage it.
- **None**: Does not allow admins to view the Zscaler Hosted Monitoring page.

To learn more, see [Understanding Zscaler Hosted Monitoring](https://help.zscaler.com/zdx/understanding-zscaler-hosted-monitoring).

Choose one of the following permissions for access to Self Service:

- **Full**: Allows admins to manage Self Service notifications for users and access to the Self Service dashboard.
- **View Only**: Allows admins to view the Self Service dashboard, but not manage Self Service notifications.
- **None**: Does not allow admins to manage Self Service notifications or view the Self Service dashboard.

To learn more, see [Monitoring the Self Service Dashboard](https://help.zscaler.com/zdx/monitoring-self-service-dashboard).

Choose one of the following permissions for access to the ZDX Copilot page:

- **Full**: Allows admins to access and utilize the ZDX Copilot page.
- **View Only**: Allows admins to view the ZDX Copilot page.
- **None**: Does not allow admins access to ZDX Copilot page.

To learn more, see [About ZDX Copilot](https://help.zscaler.com/zdx/about-zdx-copilot).

Choose one of the following permissions for access to the Wi-Fi Dashboard:

- **View Only**: Allows admins to view the Wi-Fi Dashboard.
- **None**: Does not allow admins access to the Wi-Fi Dashboard.

To learn more, see [Monitoring the Wi-Fi Dashboard](https://help.zscaler.com/zdx/monitoring-wi-fi-dashboard).

Choose one of the following permissions for access to the Device Health dashboard:

- **Full**or **View Only**: Allows admins to view the Device Health dashboard.
- **None**: Does not allow admins to manage or view the Device Health dashboard.

To learn more, see [Monitoring the Device Health Dashboard](https://help.zscaler.com/zdx/monitoring-device-health-dashboard).

Choose one of the following permissions for access to the Scripts page:

- **Full**: Allows admins to access and manage the scripts.
- **View Only**: Allows admins to view the scripts.
- **None**: Does not allow admins to manage or view the scripts.

To learn more, see [About Scripts](https://help.zscaler.com/zdx/about-scripts).

Choose one of the following permissions for access to the Script Templates page:

- **Full**: Allows admins to import the templates as scripts.
- **View Only**: Allows admins to view the script templates.
- **None**: Does not allow admins to access or import the script templates.

To learn more, see [Managing Scripts](https://help.zscaler.com/zdx/managing-scripts).

Choose one of the following permissions for access to the Remediation Jobs page:

- **Full**: Allows admins to access and manage the Remediation jobs.
- **View Only**: Allows admins to view the Remediation jobs.
- **None**: Does not allow admins to access or view Remediation jobs.

To learn more, see [Viewing and Managing Remediation Jobs](https://help.zscaler.com/zdx/viewing-and-managing-remediation-jobs).

Choose one of the following permissions for access to the Remediation Settings page:

- **Full**: Allows admins to access and manage the Remediation settings.
- **View Only**: Allows admins to view the Remediation settings.
- **None**: Does not allow admins to access or view the Remediation settings.

To learn more, see [Configuring Remediation Settings](https://help.zscaler.com/zdx/configuring-remediation-settings).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/admin-saml-configuration-guide-ad-fs-3.0","lastmod":"2023-10-18T16:40Z","nid":"1420001"} -->
## Admin SAML Configuration Guide for AD FS 3.0

- Source: https://help.zscaler.com/zdx/admin-saml-configuration-guide-ad-fs-3.0
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Admin SAML Configuration Guides > Admin SAML Configuration Guide for AD FS 3.0
- Last modified: 2023-10-18T16:40Z
- Summary: How to configure AD FS as the identity provider for the Zscaler service and use SAML single sign-on for your organization's admins.

This guide illustrates how to configure a Windows Server 2012 R2 running Active Directory Federation Services (AD FS) 3.0 as the identity provider (IdP) for the Zscaler service and use [SAML single sign-on (SSO) for your organization's admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins/). Refer to the [Microsoft AD FS documentation](https://docs.microsoft.com/en-us/windows-server/identity/active-directory-federation-services) for the Windows Server 2012 R2 steps.

## Prerequisites

Ensure you have the following before configuring AD FS:

- [AD FS account with admin privileges](https://learn.microsoft.com/en-us/windows-server/identity/ad-fs/overview/ad-fs-requirements#permissions-requirements)
- [ZDX Admin accounts](https://help.zscaler.com/zdx/adding-zdx-admins) created for your organization's admins
- Zscaler Admin XML Metadata

## Configuring Admin SAML SSO in AD FS

To configure AD FS as the IdP for the Zscaler service and use SAML SSO for admins:

1. Add a Relying Party Trust and Claim Rule.
2. Export the IdP SAML SSL Certificate.
3. [Configure SAML Admin SSO in the ZDX Admin Portal.](https://help.zscaler.com/zdx/configuring-saml-zdx-admins#configuring-saml-admins)
4. Add the Relay State URL as required.

## **Verifying ZDX Admin Portal Access via SSO**

To verify the ZDX Admin Portal access via SSO:

1. On a Windows device, browse to the following URL:

```
https://
<AD FS Server>
/adfs/ls/idpinitiatedSignOn.aspx
```

The <AD FS Server> depends on your AD FS server name. For example, if your AD FS server name is adfs.safemarch.com, browse and select https://adfs.safemarch.com/adfs/ls/idpinitiatedSignOn.aspx.

1. Verify that you are directed to the AD FS login screen.
2. Log in using your SAML admin login credentials to authenticate.

In AD FS, a relying party is a Federation Service or application that requests and processes claims from a claims provider in a particular transaction. Configure the Zscaler service as a relying party trust. After, add a claim rule, which is a statement that provides information about a user. It is used by the Zscaler service to determine if the user is allowed access.

To add Zscaler as a relying party trust and to add a claim rule:

1. On the **Server Manager**, go to **Tools** >**AD FS Management**.
2. In the left navigation panel of the **AD FS** window, go to **AD FS** >**Trust Relationships** > **Relying Party Trusts**.

See image.

1. In the **Actions** panel on the right, under **Relying Party Trusts**, click **Add Relying Party Trust…**.

See image.

1. When the **Add Relying Party Trust** **Wizard** appears, click **Start**. The wizard sections are listed on the left pane.

See image.

1. In **Select Data Source**:
  1. Select **Import data about the relying party from a file**.
  2. Under **Federation metadata file location**, click **Browse**.
  3. **Open** your downloaded Admin SP XML metadata file from the prerequisites.

When the location of the Admin SP XML metadata file displays, click **Next**.

See image.

1. In **Specify Display Name** , enter a display name for the Zscaler service, such as Zscaler Admin SAML.

Click **Next**.

See image.

1. In **Configure Multi-factor Authentication Now?** ,select **I do not want to configure multi-factor authentication settings for this relying party trust at this time**.

Click **Next**.

See image.

1. In **Choose Issuance Authorization Rules** , select **Permit all users to access this relying party**.

Click **Next**.

See image.

1. In **Ready to Add Trust** , review your settings.

Click **Next**.

See image.

1. In **Finish** , select **Open the** **Edit Claim Rules dialog for this relying party trust when the wizard closes**.

Click **Finish** to add the relying party trust to the database.

See image.

1. When the **Edit Claim Rules** window appears, click **Add Rule**.

See image.

1. In **Choose Rule Type** of the **Add Transform Claim Rule** **Wizard**, choose **Send LDAP Attributes as Claims** from the drop-down menu.

Click **Next**.

See image.

1. In **Configure Claim Rule**:
  1. Enter a name for the claim rule, such as `ZDX claims`.
  2. Choose **Active Directory** from the **Attribute Store** drop-down menu.
  3. Map the LDAP attributes that represent the admin's login name, full name, department, group to fields in the outgoing claim type.
    - Map the LDAP attribute for login name to an outgoing claim type.
      - In the **LDAP Attribute** column, choose **User-Principal-Name**.
      - In the **Outgoing Claim Type** column, choose **Name ID**. The email address is sent as the Name ID.
    - Map the LDAP attribute for full name to an outgoing claim type.
      - In the **LDAP Attribute**column, choose **Display-Name**.
      - In the **Outgoing Claim****Type**column, enter **displayName**.

Click **Finish** to add the claim rule.

See image.

1. When the **Edit Claim Rules** window displays the newly added claim rule in the list, click **Apply**.

Click **OK**.

See image.

[Image: Find Relying Trust Folder]

[Image: Select Add Relying Party Trust]

[Image: Start Add Relying Party Trust Wizard]

[Image: Import Admin SAML Metadata]

[Image: Enter your display name]

[Image: Select I do not want to configure Multi-factor Authentication settings.]

[Image: ​​​​Select Issuance Authorization Rules]

[Image: Ready to Add Trust]

[Image: Select Edit Claim Rules]

[Image: Issuance Transform Rules - Add Rule]

[Image: Configure Claim Rules - LDAP]

[Image: Fill out the Claim Rule Name]

[Image: Apply ZDX Claims]

To export the AD FS token-signing certificate that you will upload to the Zscaler service:

1. In the left navigation panel of the **AD FS** window, go to **Service** > **Certificates**.

See image.

1. In the **Certificates** panel, right-click the certificate under **Token-signing**, and click **View Certificate...**.

See image.

1. In the **Certificate** window, select the **Details** tab, and click **Copy to File…**.

See image.

1. When the**Certificate Export** **Wizard** appears, click **Next**.

See image.

1. In **Export File Format**, select **Base-64 encoded X.509 (.CER)**.

Click **Next**.

See image.

1. In **File to Export**, click **Browse** to navigate to the location where you want to export the certificate, enter a certificate name. In this example, the certificate is called `adfsadmin`.

Click **Next**.

See image.

1. When the export is complete, click **Finish**.

See image.

1. Click **OK** to close the **Certificate** window.
2. Go to the exported certificate, and ensure the following:
  - The certificate file name has a .pem extension. (For example, rename `adfsadmin.cer` to `adfsadmin.pem`.) The Zscaler service accepts certificates with the .pem extension only.
  - The file name contains one dot (".") only.

By default, Windows hides extensions for known file types.

- Change the Windows Folder Properties to View and Edit Extensions

Upload this IdP SAML SSL certificate to the ZDX Admin Portal in step 3.

1. Start Windows **Control Panel**.
2. Go to **Appearance** > **Folder Options** > **View**.
3. When the **Folder Option** window appears, deselect **Hide extensions for known file types** to view extensions.
4. Rename the extension of the exported certificate.

[Image: Select Certificates]

[Image: View Certificate]

[Image: Certificate - Details - Copy to File]

[Image: Certificate Export Wizard]

[Image: Select Certificate Format]

[Image: Browse Folder to export]

[Image: Complete Certificate Export Wizard]

The relay state is required if you have a domain defined on multiple ZIA clouds, enter the ZIA cloud name that is associated with ZDX in the Relay State field (for example, `zscalertwo.net`) for each application. To learn more, see [Microsoft's AD FS 2.0 RelayState](https://techcommunity.microsoft.com/t5/ask-the-directory-services-team/ad-fs-2-0-relaystate/ba-p/400145).

To download the XML Metadata from ZDX:

1. Sign in to ZDX as an administrator.
2. Go to **Administration** > **Administrator Management** > **Administrator Management**.
3. Click **Download**.

See image.

[Image: Download XML Metadata.]

To learn more, see [Configuring SAML for ZDX Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/admin-saml-configuration-guide-azure-active-directory","lastmod":"2023-10-18T16:40Z","nid":"1415166"} -->
## Admin SAML Configuration Guide for Azure Active Directory

- Source: https://help.zscaler.com/zdx/admin-saml-configuration-guide-azure-active-directory
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Admin SAML Configuration Guides > Admin SAML Configuration Guide for Azure Active Directory
- Last modified: 2023-10-18T16:40Z
- Summary: Configuration guide for Azure Active Directory (Azure AD) as the identity provider (IdP ) for the Zscaler Service and use SAML single sign-on (SSO) for the organization's admins.

ZDX provides information on the configuration of Microsoft Azure Active Directory (Azure AD) as an identity provider (IdP) service for the use of [SAML single sign-on (SSO) for your organization's admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins). To learn more about the steps in the Azure portal, see the [Microsoft Azure AD documentation](https://docs.microsoft.com/en-us/azure/active-directory/saas-apps/zscaler-internet-access-administrator-tutorial).

## Prerequisites

Ensure that you have the following before you start configuring Azure AD as the IdP:

- [Existing Azure AD account](https://learn.microsoft.com/en-us/azure/active-directory/saas-apps/zscaler-internet-access-administrator-tutorial#prerequisites)
- [ZDX Admin accounts](https://help.zscaler.com/zdx/adding-zdx-admins) created for your organization's admins
- ZDX cloud URL

## Configuring SAML Admin SSO with Azure

To configure Azure AD as the IdP for ZDX and use SAML SSO for admins:

1. Add the Zscaler Digital Experience Administrator Application
2. Configure SAML Admin SSO in Azure
3. Assign Admins to ZDX Admin Application
4. (Optional) Enable IdP-Initiated SSO
5. [Configure SAML Admin SSO in the ZDX Admin Portal](https://help.zscaler.com/zdx/configuring-saml-zdx-admins)

## Testing the SAML Configuration

To test the SAML admin SSO, users can initiate the SAML connection from the ZDX SAML SSO application. There are two ways to do this:

- Go to Microsoft My Apps Portal
- Browse to the User Access URL

Ensure you enable the new experience preview for Enterprise applications and the Azure AD gallery.

To add the ZDX Administrator application in Azure:

1. Sign in to the [Azure portal](http://portal.azure.com/).
2. Go to **Azure Active Directory**.

See image.

1. On the left navigation pane, click **Enterprise applications**.

See image.

1. Click **New application**.

See image.

The **Browse Azure AD Gallery (Preview)** page appears.

1. On the **Browse Azure AD Gallery (Preview)** page, enter `zscaler` in the search bar, and click the **Zscaler Internet Access Administrator** application.

See image.

1. Rename the application to a preferred name. Then click **Create**.

For example purposes, the application is named ZDX SAML SSO.

See image.

The Azure AD service displays a notification that the ZDX SAML SSO application was added.

**[Image: Screenshot highlighting the Azure Active Directory menu in the Azure portal]**

**[Image: Screenshot highlighting the Enterprise applications menu for Azure AD]**

[Image: New application link on the Enterprise applications | All applications (Preview) page]

[Image: Screenshot highlighting the search bar and the Zscaler Internet Access Administrator application on the Browse Azure AD Gallery (Preview) page]

**[Image: Screenshot highlighting the Create button for the Zscaler Internet Access Administrator application]**

To configure SAML admin SSO in Azure:

1. On the left navigation pane of the Azure Active Directory, go to **Enterprise Applications** > **ZDX SAML SSO**.

See image.

1. On the left navigation pane for the ZDX SAML SSO application, click **Single sign-on**.

See image.

1. Choose **SAML**.

See image.

1. In **Basic SAML Configuration**, click the **Edit** icon.

See image.

1. In the **Basic SAML Configuration** window:

- **Identifier (Entity ID)**: Enter the following identifier.

```
admin.
<ZDX Cloud>
.net
```

The identifier depends on the URL you use to log in to the Zscaler service. For example, if you log in to https://admin.zdxbeta.net, then the identifier is admin.zdxbeta.net. To learn more, see [Configuring SAML for ZDX Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins#CloudURLs).

- **Reply URL (Assertion Consumer Service URL)**: Click **Add reply URL**to select one of the following Zscaler admin SSO URLs with your Zscaler cloud name.
  - **https://admin.zdxcloud.net/zdx/idp-auth**
  - **https://admin.zdxpreview.net/zdx/idp-auth**
  - **https://admin.zdxbeta.net/zdx/idp-auth**

The Zscaler cloud name depends on the URL you use to log in to the Zscaler service. For example, if you log in to https://admin.zdxbeta.net, then select **https://admin.zdxbeta.net/zdx/idp-auth**. To learn more, see [Configuring SAML for ZDX Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins#CloudURLs).

See image.

- **Sign on URL**: Leave this field blank.
- **Relay State (As Required)**: If tenants are defined on multiple Zscaler Internet Access (ZIA) clouds and have a common domain, you need to configure authentication with a specific ZIA cloud by entering and selecting the ZIA cloud domain name (e.g., zscalertwo.net).
- **Logout URL**: Leave this field blank.

See image.

1. Click **Save** and exit the window.
2. In **SAML Signing Certificate**, download **Certificate (Base64)**. You need it for Step 5. [Configure SAML Admin SSO in the ZDX Admin Portal](https://help.zscaler.com/zdx/configuring-saml-zdx-admins).

See image.

**[Image: Screenshot highlighting the Single sign-on menu for the added Zscaler cloud application]**

**[Image: Screenshot highlighting SAML for the single sign-on method]**

[Image: Screenshot of the Edit icon for the the Basic SAML Configuration section.]

[Image: Screenshot of the admin SAML configuration in the Basic SAML Configuration window.]

**[Image: Screenshot highlighting the Download button for the Base64 Azure signing certificate in the SAML Signing Certificate section.]**

In order for Azure AD admins to authenticate through the Zscaler service, you must assign Azure AD admins to the ZDX SAML SSO application.

To assign admins to the Zscaler cloud application in Azure:

1. On the left navigation pane of the ZDX SAML SSO application, click **Users and groups**.

See image.

1. Click **Add user/group**.

See image.

1. In the **Add Assignment** window, click **Users and groups**.

See image.

1. In the **Users and groups** window, select the admins you want to assign to the ZDX SAML SSO application, and click **Select**.

See image.

1. In the **Add Assignment** window, click **Assign**.

See image.

[Image: Screenshot highlighting the Users and groups menu for the added Zscaler cloud application.]

[Image: Screenshot highlighting the Add user button on the Users and groups page.]

[Image: Screenshot highlighting the Users and groups button in the Add Assignment window.]

[Image: Screenshot of the selected admins in the Users and groups window.]

[Image: Screenshot highlighting the Assign button in the Add Assignment window.]

By default, the ZDX SAML SSO application is visible to admins in their My Apps portal.

To enable or disable application visibility:

1. On the left navigation pane for the ZDX SAML SSO application, click **Properties**.​​​​​

See image.

1. For **Visible to users?**, choose **Yes** or **No**.​​​

See image.

[Image: Screenshot highlighting the Properties menu for the ZDX Admin application.]

[Image: Screenshot highlighting the Visible to users? field on the Properties page.]

You use this method if you've enabled application visibility in Step b of 4. (Optional) Enable IdP-Initiated SSO.

To go to the Microsoft My Apps portal:

1. Sign in to the [Microsoft My Apps portal](https://myapps.microsoft.com) to get access to all your assigned visible applications.
2. Click the **Zscaler Digital Experience**.

You are automatically signed in to the ZDX Admin Portal.

If you've disabled application visibility in Step b of 4. (Optional) Enable IdP-Initiated SSO, you use this method to directly access the ZDX SAML SSO application from the browser.

To browse to the user access URL:

1. On the left navigation pane for the ZDX SAML SSO application, click **Properties**.

See image.

1. Copy the **User access URL**.

See image.

1. Browse to the user access URL.

See image.

You are automatically signed in to the ZDX SAML SSO Portal.

[Image: Screenshot highlighting the Copy icon for the User access URL.]

[Image: Screenshot of the Sign In page for the ZDX Admin application.]

[Image: Select Enterprise Applications to access ZDX SAML SSO Configuration.]

[Image: Select ZDX SAML SSO Configuration from Enterprise Applications.]

[Image: SAML Single-Sign on configuration for Reply URL.]

[Image: Screenshot highlighting the Properties menu for the ZDX Admin application.]

When configuring IdPs, the following information might be required for ZDX.

- ACS URL:

For ZDX Cloud:

```
https://admin.zdxcloud.net/zdx/idp-auth
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net/zdx/idp-auth
```

- Download the SAML SSL certificate from the IdP. It must be in Base64-encoded PEM format.
- Entity ID:

For ZDX Cloud:

```
https://admin.zdxcloud.net
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net
```

If you have a domain defined on multiple ZIA clouds, enter the ZIA cloud name that is associated with ZDX in the **Relay State** field (for example, `zscalertwo.net`) for each application.

You must also create admin accounts for your organization's admins. To learn more, see [Adding ZDX Admins](https://help.zscaler.com/zdx/adding-zdx-admins).

To learn more, see [Configuring SAML for ZDX Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/admin-saml-configuration-guide-okta","lastmod":"2024-10-30T11:03Z","nid":"1413011"} -->
## Admin SAML Configuration Guide for Okta

- Source: https://help.zscaler.com/zdx/admin-saml-configuration-guide-okta
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Admin SAML Configuration Guides > Admin SAML Configuration Guide for Okta
- Last modified: 2024-10-30T11:03Z
- Summary: How to configure Okta as the identity provider for the Zscaler service and use SAML single sign-on for administrators.

This guide illustrates how to configure Okta as the identity provider for the Zscaler service and use [SAML single sign-on (SSO) for admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins). Refer to the [Okta documentation](https://developer.okta.com/docs/guides/build-sso-integration/saml2/main/#create-your-integration) for additional information about the steps in the guide.

## Prerequisites

Ensure you have the following before configuring Okta:

- [Okta account with admin privileges](https://developer.okta.com/docs/guides/build-sso-integration/saml2/main/)
- [ZDX Admin accounts](https://help.zscaler.com/zdx/adding-zdx-admins) created for your organization's admins
- ZDX cloud URL

## Configuring Admin SAML SSO in Okta

To configure Okta as the IdP for the Zscaler service and use SAML SSO for admins:

1. Go **Applications** > **Create App Integration**.
2. Enter `ZDX` in the **Search** field to select **ZDX SAML**.
3. Select **SAML 2.0** as the sign-in method and click **Next**. See image.
4. In the **Create SAML Integration** wizard, for **General Settings**, enter the **App Name** for the Zscaler service's display name and then click **Next.** See image.
5. For **Configure SAML**, enter your Access (ACS) URL to **Single sign on URL**, **Recipient URL**, **Destination URL**, and **Audience URI (SP Entity ID)**. Click **Next**. See image.

If **Use this for Recipient URL and Destination URL** is selected, then your **Single sign on URL** is copied into the **Single sign on URL** and **Audience URI (SP Entity ID)** fields.

If you have a domain defined on multiple ZIA clouds, then enter the ZIA cloud name that is associated with ZDX in the **Default RelayState** field (e.g., zscaler.net).

See image.

1. For **Feedback**, choose **I'm a software vendor. I'd like to integrate my app with Okta** and then click **Finish** to complete the SAML integration. See image.
2. In the **Assign ZDX SAML SSO to People**, enter the admin's name or email address (Username) to search, and click **Assign**. See image.
3. Confirm the selected admin by their user name and click **Save and go back**. See image.
4. Review the assigned admin in the SAML Service Provider and exit from the window. See image.

The admin can now access the ZDX Admin Portal through Okta by clicking the configured Zscaler application for Admin SAML.

See image.

[Image: General Settings Tab for Add SAML Service Provider.]

[Image: Feedback selection]

[Image: Select Admin to assign SAML provider.]

[Image: Save and go back to confirm assignment to admin.]

[Image: Exit upon confirmation of assignment]

[Image: Screenshot of the Okta app with the admin SAML app now available]

[Image: Choose SAML 2.0.]

[Image: Configure SAML with Access URLs]

[Image: Add Relay State URL]

When configuring IdPs, the following information might be required for ZDX.

- ACS URL:

For ZDX Cloud:

```
https://admin.zdxcloud.net/zdx/idp-auth
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net/zdx/idp-auth
```

- Download the SAML SSL certificate from the IdP. It must be in Base64-encoded PEM format.
- Entity ID:

For ZDX Cloud:

```
https://admin.zdxcloud.net
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net
```

If you have a domain defined on multiple ZIA clouds, enter the ZIA cloud name that is associated with ZDX in the **Relay State** field (for example, `zscalertwo.net`) for each application.

You must also create admin accounts for your organization's admins. To learn more, see [Adding ZDX Admins](https://help.zscaler.com/zdx/adding-zdx-admins).

To learn more, see [Configuring SAML for ZDX Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/admin-saml-sso-configuration-guide-pingfederate","lastmod":"2024-09-16T15:45Z","nid":"1452751"} -->
## Admin SAML SSO Configuration Guide for PingFederate

- Source: https://help.zscaler.com/zdx/admin-saml-sso-configuration-guide-pingfederate
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Admin SAML Configuration Guides > Admin SAML SSO Configuration Guide for PingFederate
- Last modified: 2024-09-16T15:45Z
- Summary: How to configure Ping Identity's PingFederate server as the identity provider for ZDX.

This guide illustrates how to configure Ping Identity's PingFederate server as the identity provider (IdP) for ZDX.

## Prerequisites

Ensure that you have the following before you start configuring PingFederate as your IdP:

- [PingFederate admin account](http://docs.pingidentity.com/r/en-us/pingfederate-112/help_administrativeaccountstasklet_administrativeaccountsstate)
- PingFederate server with the Zscaler Internet Access Connector add-on
- Export PingFederate Signing Certificate for Zscaler Services
- Zscaler cloud name
- ZDX XML metadata

To download the Zscaler Internet Access Connector:

1. Go to the [PingFederate Server Add-Ons](https://www.pingidentity.com/en/resources/downloads/pingfederate.html) page.
2. Under **SaaS Connectors**, download **Zscaler Internet Access Connector 1.1**. See image.
3. Unzip the connector folder to extract the **pf-zscaler-zia-quickconnection-1.1.jar** file.
4. Add the **pf-zscaler-zia-quickconnection-1.1.jar** file to **PingFederate** > **Server** > **default** > **deploy**folder.

When configuring IdPs, the following information might be required for ZDX.

- ACS URL:

For ZDX Cloud:

```
https://admin.zdxcloud.net/zdx/idp-auth
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net/zdx/idp-auth
```

- Download the SAML SSL certificate from the IdP. It must be in Base64-encoded PEM format.
- Entity ID:

For ZDX Cloud:

```
https://admin.zdxcloud.net
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net
```

If you have a domain defined on multiple ZIA clouds, enter the ZIA cloud name that is associated with ZDX in the **Relay State** field (for example, `zscalertwo.net`) for each application.

You must also create admin accounts for your organization's admins. To learn more, see [Adding ZDX Admins](https://help.zscaler.com/zdx/adding-zdx-admins).

To download the XML Metadata from ZDX:

1. Sign in to ZDX as an administrator.
2. Go to **Administration** > **Administrator Management** > **Administrator Management**.
3. Click **Download**.

See image.

[Image: Download XML Metadata.]

Remember where you saved the metadata as you will upload it for creating a service provider (SP) connection.

To learn more, see [Configuring SAML for ZDX Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins).

To export your PingFederate signing certificate on the PingFederate admin console:

1. Log in to your PingFederate administrative console.
2. Go to **Security** > **Signing & Decryption Keys & Certificates**. See image.
3. Click **Select Action** on the certificate you want to use.
4. Click **Export**. See image.
5. On the **Export Certificate** tab, click **Next**. See image.
6. On the **Export & Summary** tab, click **Export**. See image.
7. Rename the downloaded certificate's extension to `.pem`.
8. Save this certificate for when you are ready to add PingFederate as an IdP in ZDX.

## Configuring SAML SSO on Zscaler Services

You need to register PingFederate as an IdP in Zscaler Services for SAML Single Sign-On (SSO).

To add PingFederate as an IdP in ZDX:

1. If you haven't renamed your certificate from the prerequisites step, rename your certificate's extension to `.pem`.
2. Upload your IdP signing certificate as described in [Configuring SAML SSO for ZDX Admin.](https://help.zscaler.com/zdx/configuring-saml-zdx-admins#configuring-saml-admins)
3. Click **Save**.
4. Save your configuration changes by [activating the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

## Configuring a Service Provider Connection on PingFederate

To configure a Service Provider (SP) Connection on the PingFederate administrative console:

1. Verify SAML 2.0 entity ID:
  1. Go to **System** > **Server** > **Protocol Settings** > **Federation Info**.
  2. In the **SAML 2.0 Entity ID** field: Enter a name for PingFederate to use when SAML applications need to identify it.
  3. Click **Save**.
2. Create a service provider connection:
  1. Use the **SP Connections** shortcut or go to **Applications**>**Integration** > **SP Connections**.
  2. Click **Create Connection**. See image.
  3. In the Create Connection wizard, configure:
    - Connection Template
    - Connection Type
    - Connection Options
    - General Info
    - Browser SSO
    - Credentials

## Initiate SSO

ZDX and PingFederate support Identity Provider- and Service Provider-initiated single sign-on. PingFederate's documentation provides information for invoking IdP initiated SSO. Refer to the [PingFederate documentation](https://docs.pingidentity.com/r/en-us/pingfederate-112/idp_endpoints).

When using IdP-initiated SSO, ZDX requires the cloud name (e.g., `zscalerthree.net`) passed through the SAML Relay State if you have a domain defined on multiple ZIA clouds. Zscaler recommends using the SAML Relay State in a single ZIA cloud deployment to avoid any disruption if a second ZIA cloud is added in the future. PingFederate supports this by passing the necessary Relay State value by using the TargetResource query parameter in the `/idp/startSSO.ping` application endpoint.

For example (the green text shows where to insert the ZIA Cloud Name associated with ZDX):

```
https://{PingFederate hostname}/idp/startSSO.ping?PartnerSpId={ZDX Connection ID}&TargetResource=
zscalerthree.net
```

[Image: Start the Create SP Connection Wizard]

1. Select **Use a template for this connection**.
2. For **Connection Template**, select **Zscaler ZIA Connector**.
3. For **Metadata File**, upload your metadata file into the **Metadata File** field.

Click **Next**.

[Image: SP Connection Connection Template]

Ensure the **Browser SSO Profiles** checkbox is selected, then click **Next**.

[Image: SP Connection Connection Type]

Ensure the **Browser SSO** checkbox is selected, then click **Next**.

[Image: Connection Options]

On the **General Info** tab:

- **Partner's Entity ID (Connection ID)**: Verify the Partner's Entity ID.
- **Connection Name**: Enter a connection name. This might be pre-populated and can be revised to your preference.
- **Base URL**: Verify the Base URL. You must append `:443` to the end of your base URL.

For example, if your base URL is `https://login.zscaler.net`, then your new base URL is:

```
https://login.zscaler.net:443
```

Click **Next**.

[Image: Configure General Info]

1. On the **Browser SSO** page, click **Configure Browser SSO.**
2. On the **Assertion Creation**page, click **Configure Assertion Creation**.
3. On the **Authentication Source Mapping** page, click **Map New Authentication Policy**.
4. On the **Authentication Policy Contract** tab, select **subject** for the **Authentication Policy Contract** field. This allows the authentication to be connected to policies. If required, you can configure the contract attribute that is applicable to you. Click **Next**. See image.
5. On the **Mapping Method**page, select **Retrieve Additional Attributes from a data store -- includes options to use alternate data stores and/or a failsafe mapping**. Click **Next**. See image.
6. On the **Attribute Sources & User Lookup** tab, click **Add Attribute Source**.
7. On the **Data Store** page: Click **Next**. See image.
  - **Attribute Source Description**: Enter a description for the Attribute Source.
  - **Active Data Store**: Select **PingDirectory**.
8. On the **LDAP Directory Search** page: Click **Next**. See image.
  - **Base DN**: Enter `ou=Zscaler Users,dc=example,dc=com`.
  - **Search Scope**: Select **Subtree** from the drop-down menu.
  - Attributes to select from the search:
    - **Root Object Class**: Select **<Show All Attributes>**.
    - **Attribute**: Select **mail**.
    - After **mail** is added, click **Add Attribute** next to it.
9. On the **LDAP Filter** page, for the **Filter** field, enter `uid=${subject}`. Click **Next**. See image.
10. On the **Attribute Contract Fulfillment** page: Click **Next**. See image.
  - **SAML_Subject**: Select **LDAP (pd)**.
  - **Value**: Select **mail**.
11. On the **Summary** page, click **Done**after you verify your attribute source configuration.
12. On the **Attribute Sources & User Lookup** page, click **Next** after you verify your data store to supply user information in the SAML assertion to the SP. See image.
13. On the**Failsafe Attribute Source** page, select **Abort the SSO Transaction**. Click **Next**. See image.
14. Click **Done** after reviewing your **Authentication Source Mapping** configuration. See image.
15. Click **Done** after reviewing your **Summary**.
16. On the **Assertion Creation** page, click **Next**. See image.
17. On the **Protocol Settings** page, click **Next**. See image.
18. On the **Summary** page, click **Done** to save your configuration. See image.

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
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/administrator-management-settings","lastmod":"2026-08-07T09:42Z","nid":"1456556"} -->
## Administrator Management Settings

- Source: https://help.zscaler.com/zdx/administrator-management-settings
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Administrator Management Settings
- Last modified: 2026-08-07T09:42Z
- Summary: Information on password management for administrators in ZDX.

To access the Administrator Management page, go to **Administration** > **Admin Management** > **Role Based Access Control** > **Administrators** > **Administrator Management**. You can configure restricted access, password expiration, and SAML authentication for admins.

The Administrator Management page shows a link to the [Authentication Service](https://help.zscaler.com/authentication-service) if you are subscribed to Authentication Service. You can manage your admins from the Authentication Service in the Zscaler Admin Console. To learn more, see [What Is Authentication Service?](https://help.zscaler.com/authentication-service/what-authentication-service)

## Password Management

If you're using the Zscaler-hosted [admin](https://help.zscaler.com/zdx/about-administrators) database to authenticate admins, you can enable password expiration for all admins logging in to the Internet & SaaS and Zscaler Digital Experience (ZDX). To learn more, see [Configuring Password Expiration](https://help.zscaler.com/zdx/configuring-password-expiration).

## SAML Authentication for Administrators

Admins can be authenticated via SAML using an external admin database rather than the admin database in ZDX. The Zscaler service supports identity provider (IdP)-initiated SAML to authenticate admins. You can integrate admin authentication with your existing two-factor authentication solution. The Zscaler service supports SAML 2.0 and later. To learn more, see [Configuring SAML for Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins).

The Zscaler service doesn't enforce password expiration for external admin databases using SAML.

## Advanced Configuration

Admins can configure an action when SCIM deletes a linked user account.

- **Do Nothing**: When SCIM deletes a linked user account, the ZDX admin account is not deleted.
- **Delete Account**: When SCIM deletes a linked user account, the ZDX admin account is also deleted.

See image.

[Image: Administrator Management Page (Shows Legacy UI)]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/allowlist-domains-zdx","lastmod":"2026-06-24T21:06Z","nid":"1355766"} -->
## Allowlist Domains for ZDX

- Source: https://help.zscaler.com/zdx/allowlist-domains-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Allowlist Domains for ZDX
- Last modified: 2026-06-24T21:06Z
- Summary: Information for destination domains to allowlist if using ZDX for your organization.

Certain destination domains are required to be placed on the allowlist on your corporate firewall or non-Zscaler proxy, including probes that are initiated by Zscaler Client Connector, as well as the monitored information sent by Zscaler Client Connector to the Zscaler cloud. If you are tunneling probe traffic through a GRE/IPSec tunnel at a corporate location, this action might optional.

These domains are tied to IP addresses. Be sure to allow access to the following domains per Zscaler cloud:

## Internet & SaaS

- gateway.<Zscaler Cloud Name>.net
- login.<Zscaler Cloud Name>.net
- mobile.<Zscaler Cloud Name>.net
- config.zscaler.com/<Zscaler Cloud Name>.net
- mtr.<Zscaler Cloud Name>.net

To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).

## ZDX

- login.zdxcloud.net
- pac.zdxcloud.net
- smres.zdxcloud.net
- https://d3l44rcogcb7iv.cloudfront.net

## Zscaler Client Connector

- https://d32a6ru7mhaq0c.cloudfront.net

Ensure that certain domains or IPs are on the allowlist for Zscaler Client Connector. To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/cloud-path-errors","lastmod":"2026-05-04T21:06Z","nid":"1358806"} -->
## Cloud Path Errors

- Source: https://help.zscaler.com/zdx/cloud-path-errors
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Cloud Path Errors
- Last modified: 2026-05-04T21:06Z
- Summary: Information on Cloud Path error codes as seen in the User details.

If there are any errors in the Cloud Path in the Users dashboard, users see error icons in the Hop View as well as the Command Line View. In the Hop View, the icons are displayed under the hop, while in the Command Line View, icons are displayed next to the IP address. Clicking the icon displays the error message.

See image.

The icons in the Cloud Path indicate the following:

- [Image: Information Icon]: This message is for informational purposes only. No action is required.
- [Image: Warning Error Message in ZDX]: This is a warning message. This indicates that action might be necessary to resolve an issue and achieve accurate Cloud Path results.
- [Image: Critical Error in ZDX]: This is a critical error. This indicates an issue that could affect the user experience.

| Error Message | Error Description | Solution |
| --- | --- | --- |
| Zscaler Client Connector error. Contact Zscaler Support. | There is an internal error incurred by the Zscaler Client Connector. | Contact Zscaler Support. |
| Network communication failure. Either network interface is down or traffic is blocked. | There is a network error. One of the interfaces is down, the probes are not receiving a response, or the ISP upstream connectivity might be down. | Verify your ISP connectivity and that the ICMP/UDP protocol configured for the probe is not blocked on the network. |
| The domain is invalid or not resolvable. Verify your domain. | The domain is invalid or not resolvable. | Verify the name of the domain or your DNS configuration. |
| Proxy connection failed. | There were issues when connecting to your web proxy. | Verify your proxy policy and authentication mechanism and that access is allowed for this application URL. |
| Invalid HTTP URL. Check the Web probe configuration. | Connection to the host is successful, but there are issues with connecting to your URL. | Verify that your URL for the Web probe is correct. |
| The probe result was discarded due to a device network change. | The network changed during the Cloud Path probe run. Cloud Path probes for that sample were aborted. | Zscaler Client Connector detected the network change. No action is required. |
| The network path to the client egress cannot be traced. Configuring a GRE/IPSec tunnel bypass rule for the client egress router is recommended. | The network path to the client egress IP address cannot be traced correctly because the egress IP is tunneled. This also means that the end-to-end latency value does not include the latency from the client to the Internet egress point. | Configure an access list for your router or SD-WAN device to bypass ICMP/UDP from the tunnel (GRE/IPSec) for your client egress IP address. |
| The network path to the client egress cannot be traced. Zscaler Client Connector was unable to fetch ZDX service data from Zscaler cloud. | The network path from the client to the Internet egress point (client egress) cannot be traced. Zscaler Client Connector was unable to fetch ZDX service data from the Zscaler cloud. This also means that the end-to-end latency value does not include the latency from the client to the internet egress point. | Contact Zscaler Support. |
| Client egress detection was not possible with the configured protocol type in the Cloud Path. Try a different protocol type. | The Zscaler Client Connector could not discover the user's Internet egress IP address. | Verify the configuration and try a different protocol (ICMP/UDP). The current Cloud Path protocol is blocked. |
| Hop information from ZPA Public Service Edge to the application is not collected by ZDX. | We are not able to display the actual Cloud Path for applications accessed through Private Access. | No action required. |
| The Zscaler Public Service Edge is not reachable from Zscaler Client Connector. | The TCP traceroute to the Zscaler Service Edge was dropped. | Check your network connection. |
| Probe not allowed in NDR. | ICMP and UDP probes are not supported in a No Default Route (NDR) environment. | Ensure you're running probes via TCP. |
| Traceroute packets are not reaching the Zscaler Service Edge. | ICMP, TCP, or UDP protocol traceroute might not be supported on the network. | Ensure the underlying network permits the configured protocol. |
| Data from external proxy to destination is not discoverable. | Path is not available. | No action required. |
| Data from external proxy to egress is not discoverable. | Path is not available. | No action required. |
| Cloud Path packets are not reaching the external proxy. | ICMP protocol might not be supported on the network. | Ensure the underlying network allows ICMP packets. |
| Data from external proxy to data center egress is not discoverable. | Path is not available. | No action required. |
| Data from data center egress to destination is not discoverable when external proxy is present. | Path is not available. | No action required. |
| Data from egress to destination is not discoverable when external proxy is present. | Path is not available. | No action required. |
| The client egress router did not respond to Cloud Path probes coming from the ZIA Public Service Edge. | The egress could not be probed. It did not respond with an ICMP TTL expired message for the ZDX Cloud Path probe. | Configure the router to return ICMP TTL expired messages for packets with IP TTL1. |

## Private Access and ZDX Error Codes

ZDX can report error codes for the following Private Access errors. These errors are applicable to either the Public Service Edge for Private Access or Private Service Edge for Private Access component.

| Session Status | Description | Resolution |
| --- | --- | --- |
| ZPA internal error. | The probe might have encountered a Private Access internal error. | Contact Zscaler Support. |
| ZPA application is not reachable. | The probe might have failed to reach the Private Access destination. | Contact Zscaler Support. |
| Error in finding customer. | The Public Service Edge for Private Access or Private Service Edge for Private Access cannot retrieve customer information due to a configuration error when processing the data connection request. | Ask the user to reauthenticate. If the error persists, contact Zscaler Support. |
| User session expired. | The Public Service Edge for Private Access or Private Service Edge for Private Access cannot set up a data connection because reauthentication is required. | Ask the user to reauthenticate. If the error persists, contact Zscaler Support. |
| Error in filling assistant groups. | The Public Service Edge for Private Access or Private Service Edge for Private Access cannot fill assistant groups due to a configuration error when processing the data request. | Ask the user to validate configuration. If the error persists, contact Zscaler Support. |
| Policy or attributes misconfigured for access. | A valid policy cannot be matched to an application access request. There is a missing or mismatched configuration in policy settings, SAML/SCIM attributes, Posture Profiles, Trusted Networks, Client Types, Cloud Connector Groups, or Machine Groups. The application request is also blocked when an App Segment or App Group Segment is disabled. | Update the policy to allow the user.; Ensure all SAML attributes are present in the SAML assertion and restart the Zscaler Client Connector.; Ensure all SCIM attributes or SCIM groups are present.; Modify policies to match the user's client type.; Enable the App Segment or Segment Group. |
| App Connector group not configured. | The Public Service Edge for Private Access was unable to process the application request since an App Connector group was not specified in the Server group configuration. | Edit the Server group to add the App Connector groups. To learn more, see [Editing Server Groups](https://help.zscaler.com/zpa/editing-server-groups). |
| Application policy blocked access. | The Private Access service blocked the application request because the user isn't allowed to access the requested application. | Update the policy to allow the user access. |
| Timeout policy blocked access. | The Private Access service blocked the application request because the timeout policy requires the user to authenticate. | The user must reauthenticate in Zscaler Client Connector. |
| Application not configured. | The Public Service Edge for Private Access or the Private Service Edge for Private Access cannot set up a connection since the application is not configured. | Ensure that the Application and Application Segment are configured in the Zscaler Admin Console.; Ask the user to access the application again. If the error persists, contact Zscaler Admin Console. |
| Connection request timed out. | The Public Service Edge for Private Access or Private Service Edge for Private Access was waiting for a data connection request from an App Connector that could provide access to the application, but the request timed out while waiting. The request from an App Connector is triggered in response to the initial application request from Zscaler Client Connector. | Ensure that the App Connectors can reach the Public Service Edge for Private Access or Private Service Edge for Private Access and the requested application. |

[Image: Cloud Path Errors on the User Page]

[Image: Cloud Path Errors on Command Line View]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-administrator-management-settings","lastmod":"2026-03-19T07:06Z","nid":"1507761"} -->
## Configuring Administrator Management Settings

- Source: https://help.zscaler.com/zdx/configuring-administrator-management-settings
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Configuring Administrator Management Settings
- Last modified: 2026-03-19T07:06Z
- Summary: Configuration information about the Administrator Management page.

On the Administrator Management page (Administration > Administration Controls > Administrator Management > Administrator Management), you can configure password management, SAML authentication, advanced configuration, and SCIM auto provisioning for ZDX admins.

The Administrator Management page allows you to configure the following:

See image.

- ZDX Portal Session Timeout
- [Password Management](https://help.zscaler.com/zdx/configuring-password-expiration)
- [SAML Authentication for Administrators](https://help.zscaler.com/zdx/configuring-saml-zdx-admins)
- SCIM Auto Provisioning

Admins can configure the Session Timeout Duration to determine when a user's session in the ZDX Admin Portal times out due to inactivity.

To configure the duration for a user's session:

1. Go to the **ZDX Portal Session Timeout** section.
2. Choose a **Session Timeout Duration** (**10 minutes**, **20 minutes**, **30 minutes**, **1 hour**, **2 hours**, **3 hours**, **4 hours**). The default is **30 minutes**.
3. Click **Save** and [activate the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

SCIM Auto Provisioning allows Zscaler to automatically create admin groups in the ZDX Admin Portal based on a SCIM user's group information. By enabling this setting, you can manage limitations and access to the ZDX Admin Portal for ZDX admins with admin groups.

To enable SCIM Auto Provisioning for admins:

1. Go to the **SCIM Auto Provisioning** section.
2. Select to **Enable SCIM Auto Provisioning**.
3. Click **Save** and [activate the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

Admins can choose the action to be taken against an admin account if the linked admin user account is deleted using the System for Cross-Domain Identity Management (SCIM) protocol. Admins can allow SCIM to delete the admin user and its linked user account, or they can prevent SCIM from deleting the admin user and its linked user account.

To configure SCIM protocol for admins:

1. Choose one of the following options for the **Admin Account Action When SCIM Deletes Linked User Account** drop-down menu:
  - Select **Delete** if you want to allow SCIM to delete the admin user and its linked user account.
  - Select **Do Nothing** if you do not want to allow SCIM to delete the admin user and its linked user account. SCIM will return error `409` (Admin user cannot be deleted). Do Nothing is the default.
2. Click **Save** and [activate the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

[Image: Administrator Management Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-alert-rule","lastmod":"2026-07-17T13:56Z","nid":"1364456"} -->
## Configuring an Alert Rule

- Source: https://help.zscaler.com/zdx/configuring-alert-rule
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Alert Rule Configuration > Configuring an Alert Rule
- Last modified: 2026-07-17T13:56Z
- Summary: How to configure alert rules for ZDX.

[Watch a video about Configuring Alerts for ZDX.](https://fast.wistia.net/embed/iframe/5ozc9luba9)

You can configure alert rules to modify an expression to create criteria based on real-time user experience.

To configure a rule for an alert:

1. Go to **Analytics** > **Digital Experience**> **Alerts** > **Alert Rules and Templates**.
2. Click **Add Alert Rule**. The **Add Alert Rule** window appears. See image.
3. In the **Add New Alert Rule** window:
  - a. Configure Rule
  - b. Filters
  - c. Criteria
  - d. Actions
  - e. Review
4. Click **Save** and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

The alerts triggered have a display delay of 30 minutes.

You can create a dynamic alert rule whenever the Network or Application rule type is applied. You can then modify the expression and add dynamic alerting with ZDX Score or ZDX Score Drops in the Criteria step. To learn more about Dynamic Alerting, see [Evaluating Individual Alert Details](https://help.zscaler.com/zdx/evaluating-individual-alert-details#DynamicAlert).

On the **Configure Rule** tab:

- **Name**: Enter a name to identify the rule.
- **Status**: Select from **Enabled** or **Disabled**. Select **Enabled** to enable the rule.
- **Type**: Choose from **Application**, **Device**, **Incident**, **Network**, **Network Intelligence**, or **Real User Monitoring (RUM)**. Application and Network include ZDX Score and ZDX Score Drops detection as a criteria for Dynamic Alerting. This feature and its procedures are available based on your subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience). If you select **Incident** as your type, you can select one or more checkboxes from the **Incident Type** drop-down menu to configure an alert rule for. If you select multiple incidents, the criteria are predefined for you based on their minimum thresholds. To learn more, see [Monitoring the Incidents Dashboard](https://help.zscaler.com/zdx/monitoring-incidents-dashboard) and [Understanding Alert Triggers](https://help.zscaler.com/zdx/triggering-alert). See image.
- **Severity**: Select **High**, **Medium**, or **Low** options for severity, depending on the impact of this event on users.
- **Labels (Optional)**: Select the applicable labels for the alert rule. You can also search for the label name to select. To learn more, see [About Labels](https://help.zscaler.com/zdx/about-labels).

See image.

On the **Filters** tab, depending on your rule type, you get different parameters and filters are displayed.

You can make additional selections from the drop-down menu or add multiple filters to further sort the information. You can select to include or exclude items from a filter, but you cannot choose to have both include and exclude for the same filter. For example, you can select Geolocations as a filter and specify to include North America. You cannot select Geolocations as a filter again to exclude other Geolocations.

See image.

When configuring an alert rule, consider the following:

- You cannot select deleted or unknown users for the include and exclude criteria.
- The limitation for selected items in the **Add Filter** menu is 250 items.

| Rule Type | Parameters | Filters |
| --- | --- | --- |
| Application | **Application**: Choose a [predefined application or a custom application](https://help.zscaler.com/zdx/about-applications).; **Web Probe**: Choose the Web probe for this application. | Geolocations; Zscaler Locations; Location Groups; Departments; User Groups; Users; Devices |
| Call Quality | **Application**: Choose a [predefined application](https://help.zscaler.com/zdx/about-applications) that is a Unified Communications as a Service (UCaaS) application. | Geolocations; Zscaler Locations; Location Groups; Departments; User Groups; Users; Devices |
| Device | Filters only | Geolocations; Zscaler Locations; Location Groups; Departments; User Groups; Users; Devices |
| Incident | Filters only | Geolocations; Devices; Users |
| Network | **Application**: Choose a predefined application or [custom application that is a network application type](https://help.zscaler.com/zdx/adding-custom-application).; **Cloud Path Probe**: Choose the Cloud Path probe for this application. | Geolocations; Zscaler Locations; Location Groups; Departments; User Groups; Users; Devices |
| Network Intelligence | Filters only | Zero Trust Exchange; Geolocations; ISPs |
| Real User Monitoring (RUM) | **Application**: Choose a [predefined application or a custom application](https://help.zscaler.com/zdx/about-applications) with RUM enabled. | Geolocations; Zscaler Locations; Location Groups; Departments; User Groups; Users; Devices |

On the **Criteria** tab and depending on what you select as your Rule Type in the Configure Rule tab, you can select metrics.

See image.

| Rule Type | Metric |
| --- | --- |
| Application | DNS Time; Page Fetch Time; Server Processing Time; Web Request Availability; ZDX Score; ZDX Score Drops; Zscaler Time to First Byte |
| Call Quality | MOS; ZDX Score |
| Device | Bandwidth in mbps; Battery Level; CPU Idle; CPU Kernel Usage; CPU Usage; CPU User Usage; Disk Reads in bps; Disk Usage; Disk Writes in bps; Memory Usage; Memory Used; Received Bits in mbps; Sent Bits in mbps; Wi-Fi Signal |
| Incident | Impacted Devices Each incident type or subtype has a different minimum number of impacted devices. To learn more, see [Understanding Alert Triggers](https://help.zscaler.com/zdx/triggering-alert). If you select multiple incidents, the criteria are already configured for you based on their minimum thresholds. |
| Network | Latency; Number of Hops; Packet Count; Packet Loss; ZDX Score; ZDX Score Drops |
| Network Intelligence | Threshold Latency; Network Latency Deviation |
| Real User Monitoring (RUM) | API Call Duration; DNS; DOM Content Load Time; DOM Interactive Time; DOM Interactive to Complete; DOM Processing to Interactive; Error Count; Javascript Errors; Javascript Load Time; Network Errors; Page Download Time; Page Fetch Time; Page Load Time; Redirect; Request-Response; Resource Load Time; Response Code; SSL Handshake; TCP Connect; TTFB; ZDX Score |

You can change the boolean logic of the metrics for the alert rule. Select **All** (and) for the alert to trigger if all of these thresholds are reached. Select **Any**(or) for the alert to trigger if any of these thresholds are reached.

See image.

You can select additional metrics for the alert rule type by clicking **Add**. Depending on the metric, the criteria can use the <, >, **<=**,or **>=** symbols and the time (in ms) or percent (%) options to set up the criteria for your alert rule. For ZDX Score, choose between 1 and 100 for your alert rule. For ZDX Score Drops, you can choose the threshold sensitivity (e.g., high, medium, low), which is based on a baseline score.

Click **Show Preview** to show the modified expression of your selected criteria, or click **Hide Preview** to hide them.

See image.

On the **Actions** tab:

1. Depending on the Alert Rule type selected, the **Throttling** options are:
  - Application, Device, Network, or Real User Monitoring (RUM)
  - Call Quality
  - Network Intelligence
  - Incident
2. For **Actions**:
  - If **Muted** is enabled, no alerts are sent, and you can view the status of alerts on the Alertspage in the ZDX Admin Portal.
  - If **Muted** is disabled, select the **Alert Delivery Method** from the drop-down menu:
    - **Email**: Enter the email address you want the alerts to be sent to. Click **Email Preview** to preview the email that will be sent. To learn more about the information sent, see [Understanding the Alert Email](https://help.zscaler.com/zdx/understanding-alerts-email).
    - **Webhook**: Set up a webhook to provide alerts. In the drop-down menu, select from previously configured webhooks or [configure a new webhook](https://help.zscaler.com/zdx/configuring-webhooks).
    - **Workflow Automation**: If you are subscribed to Workflow Automation, you can select Workflow Automation to send alerts to. To learn more, see [What Is Workflow Automation?](https://help.zscaler.com/workflow-automation/what-workflow-automation) You cannot select Webhook and Workflow Automation together, therefore your options for Alert Delivery Methods are: You can access the Workflow Automation Admin Portal to configure workflows. Workflow Automation is configurable with the rule types: Application, Network, and Device.
      - You can select Email and Workflow Automation together, but not with Webhook.
      - You can select Email and Webhook together, but not with Workflow Automation.
      - You can select Email, Webhook, or Workflow Automation individually.

- **Alert Only if Repeated**: Enter the number of times a triggering event occurs before an alert is sent. Zscaler recommends entering 3 or more.
- **Number of Active Devices**: Enter the number of active devices.
- **Minimum Devices Impacted**: Choose by **Number** or **Percentage**. The alert triggers only if this minimum number is reached. Alerts trigger even if only one device is present in a specific group and the device meets the alert criteria.
- **In Group**: Select the groups these throttling options apply to: **Departments**, **Cities**, **Organization**, **Regions**, or **Locations**. These options apply to the **Number** or **Percentage** of impacted devices, and the devices are also grouped based on these options. For example, in the following criteria: If only one device is present in Cairo, the PFT of a device exceeds 1000ms, and this situation repeats 3 times in a row, an alert is not triggered. The alert won't trigger because there must be at least 5 active devices in Cairo. An alert is sent when all the criteria you have set up for [triggering an alert](https://help.zscaler.com/zdx/triggering-alert) are met. See image.
  - **Number of Active Devices**: 5 **Minimum Devices Impacted**: 20%
  - **Page Fetch Time (PFT)**: >1000ms
  - **In Group**: Cities (city = Cairo)
  - **Alert Only if Repeated** 3 **Times in a Row**

- **Number of Meetings is**: Enter the number of impacted meetings in a 15-minute rolling time duration within the range of 1 and 10.
- **Minimum Number of Active Participants is**: Enter the number of total active participants in a meeting within the range of 3 and 10.
- **Number of Impacted Active Participants is:** Enter the number of impacted active participants within the range of 1 and 20 from a selected group (**Department**, **Organization**). See image.

- **Alert Only if Repeated**: Enter the number of times a triggering event occurs before an alert is sent. Zscaler recommends entering 3 or more.
- **Minimum Users Impacted**: The alert triggers only if this minimum number of impacted users is reached. See image.

Only Incident Type alert rules can configure **Actions**.

See image.

On the **Review** tab, review your rule configuration and then click **Submit**.

See image.

[Image: Configure Alert Rule]

[Image: Under Type, select ZDX Score to see Group ZDX Score By]

[Image: Configure criteria that you want to monitor for alerts]

[Image: Review Configured Alert Rule]

[Image: Dynamic Alerting with ZDX Score]

[Image: Select Filters]

[Image: Throttling]

[Image: Configuring an Alert Rule for Incident]

[Image: Incident Alert Rule Action]

[Image: Choose All or Any]

[Image: Show Preview displays the expression]

[Image: Enter the throttling options for a Call Quality alert rule]

[Image: Configure Throttling criteria a Network Intelligence alert]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-data-explorer-views","lastmod":"2026-08-06T14:15Z","nid":"1487146"} -->
## Configuring Data Explorer Views

- Source: https://help.zscaler.com/zdx/configuring-data-explorer-views
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Configuring Data Explorer Views
- Last modified: 2026-08-06T14:15Z
- Summary: How to configure Data Explorer views in the Zscaler Admin Console.

Data Explorer provides the flexibility to build and organize your own customized views of applications and metrics to analyze data. To learn more, see [Monitoring Data Explorer Views](https://help.zscaler.com/zdx/monitoring-data-explorer-views).

## Prerequisites

Before configuring a view in Data Explorer, ensure:

- Your ZDX subscription level supports Data Explorer. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Your ZDX role has the proper permission level. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Creating a View

You have the option to create a view from either End User or Managed Monitoring data. To learn more about Managed Monitoring, see [Understanding Zscaler Managed Monitoring](https://help.zscaler.com/zdx/understanding-managed-monitoring).

To begin creating your view:

1. Go to **Analytics**> **Digital Experience** > **Reports** > **Data Explorer**.
2. Click **Create New View**. See image.

### Create a View for End User Monitoring

To create a view for End User Monitoring:

1. On the **Untitled View** page, select **End User Monitoring** from the **Data Source** drop-down menu.
2. Use the time range filter to narrow the results of your view. Some filters are unavailable if your selected time range is greater than 48 hours. Your time range also determines the granularity of the data displayed in your views:
  - If 24 Hoursor less, data is displayed in 5-minute intervals.
  - If greater than 24 Hours but less than (or equal to) 14 Days, data is displayed in 1-hour intervals.
3. Structure your view:
  - a. Applications
  - b. Metrics
  - c. Filters
  - d. Operations
  - e. View Type
4. Apply filters such as **User Groups** to analyze interactions tied to a specific group or **Geolocations**to focus on data based on user locations.
5. Click **Run Query** to display your customized view:
  - The chart format displays individual charts for your selected applications and metrics. For details about the interaction and format of Data Explorer charts, see [Viewing Predefined Reports](https://help.zscaler.com/zdx/viewing-predefined-reports). See image.
  - The tabular format provides the flexibility to filter your selected applications and metrics for an instant view of the data. To capture your configured table information in a CSV file, click **Export**, then click the **Download**icon. See image.
6. Click **Save**to keep your view for future access. To learn more, see [Monitoring Data Explorer Views](https://help.zscaler.com/zdx/monitoring-data-explorer-views).
7. Enter a name for your view in the dialog window, and click **Save**to save your configuration.

The total number of views you can save depends on your ZDX subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).

[Image: Tabular view]

### Create a View for Managed Monitoring

To create a view for Managed Monitoring:

1. On the **Untitled View** page, select **Managed Monitoring** from the **Data Source** drop-down menu.
2. Use the time range filter to narrow the results of your view. The default time range is 24 Hours. Your time range also determines the granularity of the data displayed in your views.
3. Use the **Zscaler Managed Locations** filter to select the Zscaler data centers to run your probes. You can select the following:
  - List of Zscaler Managed Locations
4. Structure your view:
  - a. Probes
  - b. Metrics
  - c. Filters
  - d. Operations
  - e. View Type
  - f. Overlays
5. Click **Run Query** to display your customized view:
  - The chart format displays individual charts for your selected applications and metrics. For details about the interaction and format of Data Explorer charts, see [Viewing Predefined Reports](https://help.zscaler.com/zdx/viewing-predefined-reports). See image.
  - The scatter format displays your selected applications and metrics as dispersed data points. See image.
  - The tabular format provides the flexibility to filter your selected applications and metrics for an instant view of the data. Only the top 1,000 rows are displayed. To capture your configured table information in a CSV file, click **Export**, then click the **Download**icon. See image.
  - The range format displays your selected applications and metrics as dispersed data points across a continuous range of values, helping you analyze distribution patterns. Note: This format is unavailable when multiple probes or metrics are selected. See image.
  - The multipath visualization format displays Cloud Path visualization data corresponding to the selected timestamp in the chart view. Located under the chart, this format helps you analyze how data traverses different cloud paths for the selected applications and metrics. Use it to gain deeper insight into routing behaviors and path performance at specific moments in time. See image. This view is unavailable if you select multiple probes.; You can select a time range only between 2 hours and 7 days.; You cannot choose an option from the **Group By** drop-down menu; Zscaler Managed Locations is set by default.; You cannot change the selection under **Metrics**, latency metrics are selected by default.; By default, the Cloud Path visualization shows data from the last 24 hours.
6. Click **Save**to keep your view for future access. To learn more, see [Monitoring Data Explorer Views](https://help.zscaler.com/zdx/monitoring-data-explorer-views).
7. Enter a name for your view in the dialog window, and click **Save**to save your configuration.

The total number of views you can save depends on your ZDX subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).

## Editing a View

Only the ZDX Super Admin or the admin who created the view can edit a configured Data Explorer view.

To edit a Data Explorer view:

1. Go to **Analytics**> **Digital Experience** > **Reports** > **Data Explorer**.
2. Search for the view you want to edit in the Data Explorer table.
3. Under **Actions**, click the **View**icon.
4. Make your edits.
5. Click **Run Query** to display your view.
6. Click **Update**.
7. [Activate your changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

## Deleting a View

Only the ZDX Super Admin or the admin who created the view can delete a configured Data Explorer view.

To delete a Data Explorer view:

1. Go to **Analytics**> **Digital Experience** > **Reports** > **Data Explorer**.
2. Search for the view you want to delete in the Data Explorer table.
3. Under **Actions**, click the **Delete**icon.
4. In the dialog window, confirm you want to delete the view.
5. [Activate your change](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

[Image: Click the Create New View button]

[Image: Chart view]

[Image: Scatter View]

[Image: Chart View]

[Image: Range View]

[Image: Multipath View]

Click **Add**to specify the applications for your view:

- If you select a United Communications as a Service (UCaaS) application, you can add other UCaaS applications for your view.
- If you select a non-UCaaS application, you can add other non-UCaaS applications for your view.

The applications you select determine the particular metrics that are available. The number of applications you can add depends on your ZDX subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).

[Image: Add applications to your view]

Click **Add**to specify the probes for your view. You can add up to 10 probes for Managed Monitoring.

[Image: Add probes to your view]

Click **Add**to specify the metrics for your view. The number of metrics you can add depends on your ZDX subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).

After selecting your metrics, you can apply Metric-Based filters to refine the dataset being displayed in your view. These filters allow you to specify conditions or thresholds based on the metrics you've chosen.

[Image: Adding Metrics to your view]

Click **Add** to select filters for your view.

[Image: Filters Selection]

You can group data based on selected filters and select how data is aggregated.

- Group By
- Aggregation Type

Click **Add**to specify the metrics for your view. You can add up to 10 metrics for Managed Monitoring.

If a Web probe is configured with a Cloud Path probe as a companion probe, **Page Fetch Time** and **End-to-End Latency** metrics are automatically selected by default.

After selecting your metrics, you can apply Metric-Based filters to refine the dataset being displayed in your view. These filters allow you to specify conditions or thresholds based on the metrics you've chosen.

[Image: Adding Metrics to Managed Monitoring]

Select an option to group data by **Zscaler Locations**, **Geolocations**, **Departments**, **Applications, Last Mile ISPs,**or **Zscaler Data Center**. If you group by Applications, views are rendered for each metric. If you group by other options in the drop-down menu, views are rendered for each application and metric.

If you've selected more than one metric for your view in chart format, your **Group By** option is limited to **Applications**.

[Image: Select how to group data]

Select the option to group data by**Probes**or **Zscaler Managed Locations**.

If you've selected more than one metric for your view in chart format, your **Group By** option is limited to **Probes**.

[Image: Select how to group data]

Click **Add** to select filters for your view.

[Image: Filters Selection]

You can group data based on selected filters and select how data is aggregated.

- Group By
- Aggregation Type

- Zscaler-Amsterdam-NE
- Zscaler-Atlanta-GA-US
- Zscaler-Chennai-IN
- Zscaler-Chicago-IL-US
- Zscaler-Dallas-TX-US
- Zscaler-Delhi-IN
- Zscaler-Frankfurt-DE
- Zscaler-Los Angeles-CA-US
- Zscaler-London-UK
- Zscaler-Mumbai-IN
- Zscaler-New York-NY-US
- Zscaler-Osaka-JP
- Zscaler-Paris-FR
- Zscaler-San Jose-CA-US
- Zscaler-Singapore-SG
- Zscaler-Sydney-AU
- Zscaler-Tokyo-JP
- Zscaler-Washington-DC-US
- Zscaler-Zurich-CH

Select the **Average**, **Minimum**, **Maximum**, or **Median**method for data aggregation across all users. The default setting is Average.

[Image: Select Aggregation Type]

Select the **Average**, **Minimum**, **Maximum**, or **Median**method for data aggregation across all users. The default setting is Average.

[Image: Select Aggregation Type]

Select a **View Type** icon to display views in a chart or tabular format.

[Image: Select View Type options]

Select a **View Type** icon to display your view in a chart, scatter, range, tabular, or multipath visualization format.

[Image: Managed Monitoring - View Type]

[Image: Displays a tabular view of the Managed data.]

Select a **Percentile Threshold**, such as the 99th (P99) or 95th (P95) percentile, to display statistical measures that highlight worst-case and typical performance scenarios. The**Show Errors** toggle, disabled by default, displays red markers on charts to indicate probe errors. You can click these markers within the chart to view detailed information about the error, including the timestamp, error type, and relevant metrics.

[Image: Data Explorer view showing the Overlays section configured with the P99 percentile threshold and 'Show Errors' enabled, alongside a line chart displaying average end-to-end latency for selected probes.]

[Image: Line chart showing average page fetch time with HTTP error markers displayed in red for specific timestamps. Includes a tooltip with percentile metrics and error details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-inventory-settings","lastmod":"2026-04-20T15:44Z","nid":"1411346"} -->
## Configuring Inventory Settings

- Source: https://help.zscaler.com/zdx/configuring-inventory-settings
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Configuring Inventory Settings
- Last modified: 2026-04-20T15:44Z
- Summary: Provides configuration steps of Inventory Settings.

The Inventory Settings page provides admins with the ability to configure the settings for data collection for Software Inventory, Software Patch Inventory, Wi-Fi data collection, or Process Inventory.

Minimum versions of Zscaler Client Connector and ZDX Module are required. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).

To access the Inventory Settings page, go to Policies > Digital Experience Monitoring > Inventory Settings.

See image.

[Image: Configure data collection for specific features]

## Configuring Software Inventory Data Collection

If Collect Software Inventory Data is enabled, Zscaler collects software inventory data. To learn more, see [Viewing Software Inventory](https://help.zscaler.com/zdx/viewing-software-inventory).

To configure data collection for Software Inventory:

1. Enable **Collect Software Inventory Data**.
2. Click **Save**.

## Configuring Software Patch Inventory Data Collection

If Collect Patch Inventory Data is enabled, Zscaler collects the current distribution of software patches on user devices as inventory data. To learn more, see [Viewing Software Patch Inventory](https://help.zscaler.com/zdx/viewing-software-patch-inventory).

To configure data collection for Software Patch Inventory:

1. Enable **Collect Patch Inventory Data**.
2. Click **Save**.

## Configuring Wi-Fi Data Collection

You can opt for Wi-Fi data collection to collect the signal strength and retransmission rate to identify low-performing Wi-Fi devices. To learn more, see [Monitoring the Wi-Fi Dashboard](https://help.zscaler.com/zdx/monitoring-wi-fi-dashboard).

To configure Wi-Fi data collection:

1. Enable **Use Signal Strength and Retransmission Rate for Wi-Fi Data Collection**.
2. Click **Save**.

## Configuring Process Inventory CPU Incidents

Process Inventory monitors the number of CPU Incidents that exceed the CPU Usage threshold (e.g., CPU Usage > 10%) for a duration of 5 minutes. To learn more, see [Viewing Process Inventory](https://help.zscaler.com/zdx/viewing-process-inventory).

1. Enter a CPU Usage threshold between `0` and `100`%.
2. Click **Save**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-microsoft-intune-zdx","lastmod":"2026-06-24T21:06Z","nid":"1409701"} -->
## Configuring Microsoft Intune for ZDX

- Source: https://help.zscaler.com/zdx/configuring-microsoft-intune-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Configuring Microsoft Intune for ZDX
- Last modified: 2026-06-24T21:06Z
- Summary: How to integrate Microsoft Intune with ZDX to generate Microsoft Endpoint analytics.

The integration of Microsoft Intune with Zscaler Digital Experience (ZDX) provides access to Endpoint Analytics for user and device insights. To learn more, see [Understanding Microsoft Endpoint Analytics for ZDX](https://help.zscaler.com/zdx/understanding-microsoft-endpoint-analytics-zdx).

## Adding the Intune Integration

In the Zscaler Admin Console:

1. Go to **Policies**> **Digital Experience Monitoring** > **Data Collection Integrations**.
2. Click **Add New Integration**.
3. Select **Microsoft Intune**from the drop-down menu. The **Add New Microsoft Intune Integration** window appears. See image.
4. In the **Add New Microsoft Intune Integration** window:
  1. **Name**: Enter a name for Microsoft Intune integration.
  2. **Status**: Click **Enable** to allow for data collection from Microsoft Intune.
  3. **Monitoring Criteria**: Use the filters to help identify the users from whom data can be collected for Endpoint Analytics. Selections among the filters are cumulative, whereas selections within a single filter are not cumulative. For example, if you select DevTest and Service Admin in the User Groups filter, and then select Engineering and IT in the Departments filter, you can identify users who belong to the DevTest or Service Admin user group *and*the Engineering or IT department.
  4. **Authentication**: Click **Microsoft Office 365 Authentication**. You must authenticate with Microsoft before you can save the Intune integration. You must also reauthenticate whenever you update the **Monitoring Criteria** settings.
  5. Sign in and enter your credentials. Verify your identity if multi-factor authentication is required. See image.
  6. **Accept**the resource permissions from Microsoft. See image. Zscaler uses sign-in and read permissions for the Microsoft Intune integration. To learn more about Intune permission scopes, refer to the [Microsoft Intune documentation](https://learn.microsoft.com/en-us/intune/intune-service/developer/intune-graph-apis).
  7. After the **Add New Microsoft Intune** **Integration** window reappears, click **Save**. The Intune integration appears in the table for Data Collection.
  8. (Optional) Select the **Edit**icon in the table to re-open the **Edit Microsoft Intune** **Integration** window. Click **Validate**to verify your setup with Microsoft was successful. See image.
5. Click **Save**.

## Disabling the Intune Integration

To disable the Microsoft Intune integration on the **Data Collection** page:

1. Click the **Edit**icon for the Microsoft Intune integration.
2. In the **Edit Microsoft Intune Integration** window, configure the **Status** to **Disable**.
3. Click **Save**.
4. [Activate your change](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

## Deleting the Intune Integration

To delete the Microsoft Intune integration from the **Data Collection** page:

1. Click the **Delete**icon for the Microsoft Intune integration.
2. In the dialog window, confirm you want to delete the integration.
3. [Activate your change](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

[Image: Add New Microsoft Intune Integration Window]

[Image: Microsoft sign-in modal]

[Image: Modal to accept Microsoft permissions]

[Image: Button on Add New Microsoft Intune modal to validate setup]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-microsoft-teams-call-quality-zdx","lastmod":"2026-05-26T11:56Z","nid":"1386186"} -->
## Configuring Microsoft Teams Call Quality for ZDX

- Source: https://help.zscaler.com/zdx/configuring-microsoft-teams-call-quality-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Configuring Microsoft Teams Call Quality for ZDX
- Last modified: 2026-05-26T11:56Z
- Summary: How to configure a new tenant to monitor Microsoft Teams Call Quality.

You can configure Microsoft Teams Call Quality to monitor audio calls among two or more users. Call Quality can help you pinpoint issues that are unique to a device or the network by working in parallel with its Cloud Path probe. When onboarding a Microsoft Teams Call Quality tenant for the first time, a ZDX Autosense Cloud Path probe is automatically generated that detects the destination IP address. To learn more, see [Understanding Microsoft Teams Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-microsoft-teams-call-quality-zdx).

If your organization does not use an email ID to access Microsoft Teams Call Quality, log in with your Zscaler Client Connector Login ID to ensure successful alignment between Microsoft and the Zscaler service.

## Prerequisites

Verify the following before onboarding a Microsoft Teams Call Quality tenant:

- You're running the required versions of Zscaler Client Connector and ZDX Module to configure a ZDX Autosense probe. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#ZDXAutosenseZoom).
- Your ZDX subscription level supports ZDX Autosense. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- You've enabled the Windows Filtering Platform (WFP) driver installation setting for ZDX Autosense in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
- You've enabled the setting to collect device hostname information in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector to Collect Hostnames](https://help.zscaler.com/client-connector/configuring-zscaler-client-connector-collect-hostnames).

## Onboarding a Tenant

In the Zscaler Admin Console:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **Unified Communication Collection** > **Microsoft Teams Call Quality**.
2. From the **Actions** menu (vertical ellipsis), select **Onboard Application**.. See image.
3. In the **Microsoft Teams Call Quality** drawer, click **Authenticate**. See image.
4. Sign in and enter your password. Verify your identity if multi-factor authentication is required. See image. To authenticate M365 for application integration, you must be a Global Administrator in the Azure Active Directory (AD). To learn more, refer to the [Azure AD documentation](https://docs.microsoft.com/en-us/azure/active-directory/roles/permissions-reference).
5. Accept the resource permissions from Microsoft. See image. A **Microsoft Teams Web App**is automatically added under **Predefined Apps Collection**.
6. (Optional) Select the tenant name and click **Validate**in the **Application** drawer to verify your setup with Microsoft was successful.
7. [Activate your changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

## Adding a Tenant

In the Zscaler Admin Console:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**>**End User** > **Unified Communication Collection** > **Microsoft Teams Call Quality**.
2. Click **Add** and then click **Tenant** to begin configuring a Unified Communications as a Service (UCaaS) tenant. See image.
3. For **Select an Application**: Select a UCaaS application (i.e., Microsoft Teams Call Quality). See image.
4. For **Configure Tenant**: See image.
  - **Name**: Enter the name for the tenant.
  - **Status**: Toggle to enable or disable the tenant.
  - **Monitoring Criteria**: Configure the Inclusion Criteria for **User Groups**, **Users**, and **Departments**. Meetings are monitored and displayed only for your selected ZDX users in the **Inclusion Criteria** of the **Monitoring Criteria**. Selections among the filters are cumulative, whereas selections within a single filter are not cumulative. For example, if you select DevTest and Service Admin in the User Groups filter, and then select Engineering and IT in the Departments filter, you can identify users who belong to the DevTest or Service Admin user group and the Engineering or IT department. You can specify:
    - **User groups**: Include one or more user groups.
    - **Users**: Include multiple users.
    - **Departments**: Include entire departments.
  - **Authentication**: Authenticate your Microsoft Teams tenant for ZDX to gather monitoring metrics.
5. For **Review**: Review your configuration settings for the UCaaS tenant and click **Save**.
6. [Activate the changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

After configuring a tenant, you can:

- [Edit the probes and ZDX autosense](https://help.zscaler.com/zdx/configuring-probe) to monitor Microsoft Teams Call Quality. Meetings are monitored for the selected users according to the [Inclusion and Exclusion probing criteria](https://help.zscaler.com/zdx/understanding-probing-criteria-logic).
- Edit the tenant configuration. See image.
- Delete the tenant.

You must reauthenticate with Microsoft Teams if you configure or update the criteria settings.

To learn more about managing your collections, see [Managing Collections](https://help.zscaler.com/zdx/managing-collections).

[Image: Onboard the Microsoft Teams Call Quality application]

[Image: Authenticate Microsoft Teams Call Quality]

[Image: Add Tenant]

[Image: Microsoft sign-in window]

[Image: Window to accept Microsoft permissions]

[Image: Select an Application]

[Image: Configure Tenant]

[Image: View Tenants]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-password-expiration","lastmod":"2025-10-30T12:07Z","nid":"1374821"} -->
## Configuring Password Expiration

- Source: https://help.zscaler.com/zdx/configuring-password-expiration
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Configuring Password Expiration
- Last modified: 2025-10-30T12:07Z
- Summary: How to configure password expiration in the ZDX Admin Portal.

[Watch a video about Configuring Password Expiration in ZDX.](https://fast.wistia.net/embed/iframe/5llwj5nox8)

To provide additional security, admins have the option to enable password expiration for all admins logging in to the ZIA and ZDX Admin Portals. The latest password expiration settings configured in either the ZDX Admin Portal or the ZIA Admin Portal apply to both.

If you do not enable this feature, passwords will never expire.

## Configuring Password Expiration

If this feature is enabled, a column showing if an admin's password has expired can be seen on the Administrators tab in Administration > Administration Management. To learn more about the Administrators page, see [About Administrators](https://help.zscaler.com/zdx/about-administrators).

To enable this feature:

1. Go to Administration > Administrator Management.
2. Click the **Administrator Management** tab.
3. In the **Password Management** section:
  - **Password Expiration**: Enable passwords to expire for all admins in the ZIA and ZDX Admin Portals. If enabled, you can see a column showing if an admin's password has expired on the Administrators page (Administration > Administration Management). To learn more, see [About Administrators](https://help.zscaler.com/zia/about-administrators) (ZIA) and [About Administrators](https://help.zscaler.com/zdx/about-administrators) (ZDX). The following field appears: See image.
    - **Password Expires After**: Enter the number of days you want passwords to be valid for ZIA and ZDX admins. The default is 180 days. The days can range from 15 to 365. When there are 15 days left, the Zscaler service reminds admins to change their password and shows how many days it will remain valid for when they log in. If admins change their password before it expires, their new password will be valid for the specified time. However, if their password expires, admins must register a new password to access any features in the ZIA and ZDX Admin Portals. The new password can't be the same as the previous one. Also, the password used must be at least 8 characters in length and include at least one uppercase letter, one number, and one special character.
4. Click Save and [activate the change](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

[Image: Setting Password Expiration in ZDX]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-predefined-application","lastmod":"2026-07-07T08:41Z","nid":"1355646"} -->
## Configuring a Predefined Application

- Source: https://help.zscaler.com/zdx/configuring-predefined-application
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Configuring a Predefined Application
- Last modified: 2026-07-07T08:41Z
- Summary: Information about how to configure a predefined application for ZDX.

[Watch a video about Predefined Applications for ZDX.](https://fast.wistia.net/embed/iframe/nomenzv7yc)

Predefined applications are made with readily available data for ZDX to allow fast and seamless configuration. To learn more, see [About Applications](https://help.zscaler.com/zdx/about-applications).

To configure a predefined application:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes** > **End User** > **Predefined Apps Collection**. See image.
2. Select an application from the [Predefined Applications list](https://help.zscaler.com/zdx/predefined-applications-zdx).
3. Click **Add Probe**. If you want to use Real User Monitoring metrics, verify that **Real User Monitoring**is **Enabled**. To learn more, see [Understanding Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring). See image.
4. [Configure the probe fields.](https://help.zscaler.com/zdx/configuring-probe)
5. Click **Submit**.
6. [Activate your changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

[Image: View the Predefined Apps Collection]

[Image: Click Add Probe]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-probe","lastmod":"2026-06-26T11:06Z","nid":"1350846"} -->
## Configuring a Probe

- Source: https://help.zscaler.com/zdx/configuring-probe
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Probes > Configuring a Probe
- Last modified: 2026-06-26T11:06Z
- Summary: How to add a probe for an application for ZDX.

[Watch a video about probes in ZDX](https://fast.wistia.net/embed/iframe/ni88xj64fh) (shows legacy UI).

You can configure a probe by doing the following:

- Adding a probe.
- [Editing a probe.](https://help.zscaler.com/zdx/editing-probe)
- Copying a probe.
- Deleting a probe.

## Adding a Probe

To add a probe for an application:

1. Go to one of the following:
  - **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **Predefined Apps Collection**
  - **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **Custom Apps Collection**
  - **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **<Custom Collection name>**
2. Select an application from the **Collections** menu. See image.
3. Click **Add Probe**. The **Add Probe** drawer appears. See image. A Web probe and Cloud Path probe are automatically enabled by default when you onboard a predefined application. The **Add Probe** link is disabled if you've reached the maximum number of allowed applications or probes for your subscription level. To learn more about applications and probe limits, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
4. Under **Select a Probe Type**:
  1. **Add To Application**: Select which application to associate the configured probes.
  2. **Select a Probe Type**: Select which type of probes to configure for the end user (**End User Web**, **End User Cloud Path**).
5. Depending on which probe type is selected, you can configure for:
  - End User Web
  - End User Cloud Path
6. Review your probe configurations and click **Submit**.
7. [Activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

## Copying a Probe

To copy a probe for an application:

1. Go to one of the following:
  - **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **Custom Apps Collection**
  - **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **<Custom Collection name>**.
2. Select an application from the **Collections** menu.
3. Under the **Actions** column, click the 3 dots, and then click **Copy** on the probe you want to copy. [Image: Click Copy]
4. The **Copy Probe** configuration allows you to configure certain fields similar to when you add a probe. See image. Click **Next** to go through the configuration.
5. Review the probe configuration and click **Submit**.
6. [Activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

## Deleting a Probe

To delete a probe for an application:

1. Go to one of the following:
  - **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **Custom Apps Collection**
  - **Policies** > **Digital Experience Monitoring** > **Probes**> **End User** > **<Custom Collection name>**.
2. Select an application from the **Collections** menu.
3. Under the **Actions** column, click the 3 dots, and then click **Delete** on the probe you want to delete. [Image: Click Delete]
4. In the **Delete** window, click **Delete** to confirm the deletion. [Image: Deletion Window]

When End User Web is selected, you can configure the following for a Web probe:

See image.

- For **General**:
  - **Name**: Enter a name for your probe. The maximum length is 64 characters. Accepted characters are alphanumerics and a limited range of symbols, such as underscore (_), hyphen (-), space ( ), forward slash (/), period (.), pipe symbol (|), and parentheses ().
  - **Status**: Select **Enable** or **Disable** to indicate the status of your probe.
  - **App Name**: This view-only field is automatically populated with the previously selected application.
  - **Probe Type**: This view-only field is automatically populated with the previously selected probe. If you've selected a Network application, the Cloud Path probe type is selected by default. Network applications do not require Web probes.
  - **Run Frequency (minutes)**: Enter the number of minutes for how frequently your probe should run. The default probe frequency varies based on your subscription plan. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
  - **Probe Class**: This field is automatically selected based on the selected application (**Predefined** or **Custom**).
- For **Probing Criteria**: Select the criteria that you want to probe for the application. To learn more, see [Understanding Probing Criteria Logic](https://help.zscaler.com/zdx/understanding-probing-criteria-logic). You can configure the probe to monitor multiple user groups, users in particular locations or departments, or a combination of these. For example, you might want to run a probe for the sales group using a sales business application (e.g., Salesforce.com). Running a probe for the sales group helps to limit the number of probes associated with the application and avoid unnecessary traffic from non-sales users. Similarly, you can configure a probe to only run on multiple devices logged in by one user, or configure it to run on only specific devices. By default, all user groups, users, Zscaler locations, location groups, departments, and devices in Internet & SaaS are applied. However, the maximum number of each selection for user groups, users, Zscaler locations, location groups, departments, or devices cannot exceed 100. If your organization has an Internet & SaaS account, each field defaults to your organization's information, such as the following examples: When configuring Web probes for internal applications through Private Access, probe only for users, user groups, and departments that use the application.
  - **User Groups**: Data Engineering
  - **Users**: (Your organization's users)
  - **Locations**: Zscaler HQ
  - **Location Groups**: Server Traffic Group
  - **Departments**: Dev Ops
  - **Devices**: Desktop
  - **OS**: Windows
- For **Exclusion Criteria**: Select the criteria that you do not want to monitor for the application. To learn more, see [Understanding Probing Criteria Logic](https://help.zscaler.com/zdx/understanding-probing-criteria-logic). You can configure probes to run on all devices in a particular location by specifying the location in Monitoring Criteria, then exclude specific users or devices. For instance, you might want to run a probe for a productivity web application for all engineering group users but exclude the New York location, as that location does not use productivity web applications. By default, no user groups, users, Zscaler locations, location groups, departments, or devices are excluded. The maximum number of each selection for user groups, users, Zscaler locations, location groups, departments, or devices cannot exceed 100. The following are examples of excluded criteria: When configuring Web probes for internal applications through Private Access, probe only for users, user groups, and departments that use the application. If you configure Zscaler location-based inclusion or exclusion criteria, ZDX must be able to determine the device’s Zscaler location (location ID). If the device location can’t be determined, the probe is skipped.
  - **User Groups**: Mobile Development
  - **Users**: DEFAULT ADMIN
  - **Locations**: Zscaler Lab
  - **Location Groups**: HQ IT Group
  - **Departments**: Marketing
  - **Devices**: AUROUS
  - **OS**: Windows
- For **Additional Parameters**:
  - **Probe Name**: This is the name you entered on the previous tab.
  - **Application Name**: This is the name of the applicationthat was either preselected for you or that you selected manually on the previous tab.
  - **Request Type**: **GET** is the only request type applicable to **Web**monitoring. POST is not supported. This field is view-only.
  - **Destination URL**: Enter the web destination, either an HTTP or HTTPS URL. This is the web address the probe requests. This is editable if the URL requires a tenant name.
  - **Request Header**: Enter the **Name** and **Value** for the request header. If you need to enter more than one name and value pair, click **Add More**. This specifies the HTTP request header to pass as part of the probe. For example, you can specify authorization of the header to pass a security token. These fields are disabled for predefined applications.
  - **HTTP Response Status Codes**: You can probe for specific [HTTP Status Codes](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml). You can add HTTP codes that are not in the default list by entering any number between the range of 100–599. By default, the following 1xx (Informational), 2xx (Success), and 3xx (Redirection) codes are applied: You can remove any of these codes by clicking the **Close**icon. However, you cannot change the default codes for a predefined app.
    - 100 Continue
    - 101 Switching Protocol
    - 102 Processing
    - 103 Early Hints
    - 200 OK
    - 201 Created
    - 202 Accepted
    - 203 Non-Authoritative Information
    - 204 No Content
    - 205 Reset Content
    - 206 Partial Content
    - 207 Multi-Status
    - 208 Already Reported
    - 226 IM Used
    - 300 Multiple Choices
    - 301 Moved Permanently
    - 302 Found
    - 303 See Other
    - 304 Not Modified
    - 307 Temporary Redirect
    - 308 Permanent Redirect
  - **Number of Attempts**: The number of attempts before considering the monitor request failed. The default is 1 attempt, but can be increased. You cannot change the default number of attempts for a predefined application.
  - **Timeout (seconds)**: The default is 60 seconds and cannot be changed for a predefined application.
  - **Follow Redirect**: **Enable** or **Disable** this feature. To ensure accurate performance measurements for the following predefined applications, this setting is disabled by default and you cannot enable it:
    - Box
    - Microsoft Teams Web App
    - OneDrive for Business
    - Outlook Online
    - ServiceNow
    - SharePoint Online
  - **Maximum Redirects**: This specifies the number of times the probe attempts to follow the HTTP redirect before it is considered failed. The default is 5 redirects. You cannot change the number of redirects for a predefined application. If **Follow Redirect** is disabled, **Maximum Redirects** is also disabled.

Click **Next**.

When End User Cloud Path is selected, you can configure the following for a Cloud Path probe:

See image.

- For **General**:
  - **Name**: Enter a name for your probe. The maximum length is 64 characters. Accepted characters are alphanumerics and a limited range of symbols, such as underscore (_), hyphen (-), space ( ), forward slash (/), period (.), pipe symbol (|), and parentheses ().
  - **Status**: Select **Enable** or **Disable** to indicate the status of your probe.
  - **App Name**: This view-only field is automatically populated with the previously selected application.
  - **Probe Type**: This view-only field is automatically populated with the previously selected probe. If you are configuring for a Call Quality Monitoring (CQM) application, this field automatically shows the probe type and is the ZDX Autosense feature. ZDX Autosense is useful for automatically discovering the Cloud Path Host that is associated to the CQM application and allows you to monitor the CQM application and meetings. This is automatically configured as part of the Cloud Path probe when you first onboard a CQM application. You must have ZDX Autosense enabled to use this feature. To learn more, see: If you've selected a Network application, the Cloud Path probe type is selected by default. Network applications do not require Web probes.
    - [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience)
    - [Understanding Webex Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-webex-call-quality-zdx)
    - [Understanding Microsoft Teams Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-microsoft-teams-call-quality-zdx)
    - [Understanding Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-zoom-call-quality-zdx)
    - [Zoom Call Quality for ZDX Integration Requirements](https://help.zscaler.com/zdx/zoom-call-quality-zdx-integration-requirements)
  - **Follow Web Probe**: Select an accompanying Web probe to better visualize both Cloud Path probe and Web probe.
  - **Run Frequency (minutes)**: Enter the number of minutes for how frequently your probe should run. The default probe frequency varies based on your subscription plan.
  - **Probe Class**: This field is automatically selected based on the selected application (**Predefined** or **Custom**).
- For **Probing Criteria**: Select the criteria that you want to probe for the application. To learn more, see [Understanding Probing Criteria Logic](https://help.zscaler.com/zdx/understanding-probing-criteria-logic). You can configure the probe to monitor multiple user groups, users in particular locations or departments, or a combination of these. For example, you might want to run a probe for the sales group using a sales business application (e.g., Salesforce.com). Running a probe for the sales group helps to limit the number of probes associated with the application and avoid unnecessary traffic from non-sales users. Similarly, you can configure a probe to only run on multiple devices logged in by one user, or configure it to run on only specific devices. By default, all user groups, users, Zscaler locations, location groups, departments, and devices in Internet & SaaS are applied. However, the maximum number of each selection for user groups, users, Zscaler locations, location groups, departments, or devices cannot exceed 100. If your organization has an Internet & SaaS account, each field defaults to your organization's information, such as the following examples: When configuring Web probes for internal applications through Private Access, probe only for users, user groups, and departments that use the application.
  - **User Groups**: Data Engineering
  - **Users**: (Your organization's users)
  - **Locations**: Zscaler HQ
  - **Location Groups**: Server Traffic Group
  - **Departments**: Dev Ops
  - **Devices**: Desktop
  - **OS**: Windows
- For **Exclusion Criteria**: Select the criteria that you do not want to monitor for the application. To learn more, see [Understanding Probing Criteria Logic](https://help.zscaler.com/zdx/understanding-probing-criteria-logic). You can configure probes to run on all devices in a particular location by specifying the location in Monitoring Criteria, then exclude specific users or devices. For instance, you might want to run a probe for a productivity web application for all engineering group users but exclude the New York location, as that location does not use productivity web applications. By default, no user groups, users, Zscaler locations, location groups, departments, or devices are excluded. The maximum number of each selection for user groups, users, Zscaler locations, location groups, departments, or devices cannot exceed 100. The following are examples of excluded criteria: When configuring Web probes for internal applications through Private Access, probe only for users, user groups, and departments that use the application. If you configure Zscaler location-based inclusion or exclusion criteria, Zscaler Digital Experience (ZDX) must be able to determine the device’s Zscaler location (location ID). If the device location can’t be determined, the probe is skipped.
  - **User Groups**: Mobile Development
  - **Users**: DEFAULT ADMIN
  - **Locations**: Zscaler Lab
  - **Location Groups**: HQ IT Group
  - **Departments**: Marketing
  - **Devices**: AUROUS
  - **OS**: Windows
- For **Additional Parameters**:
  - **Protocol**: Select a protocol from the drop-down menu. Options include **Adaptive**, **ICMP**, **TCP**, and **UDP**. If you choose Adaptive Mode, the best protocol for each leg in the cloud is selected via an auto-discovery process. If you choose any of the other protocols, they are used for the path of the Cloud Path probe. To learn more, see [Using Adaptive Mode](https://help.zscaler.com/zdx/using-adaptive-mode).
    - **TCP Port**: If you choose **Adaptive**or **TCP**as the protocol, this field is automatically displayed and populated with the standard TCP Port for HTTPS traffic, 443, though this can be edited. If you have chosen to follow a Web probe and then choose **TCP**as the protocol, you cannot edit the port value.
    - **UDP Port**: If you choose **Adaptive**or **UDP**as the protocol, this field is automatically displayed and populated with the RFC-defined port for the destination server, 33434, though this can be edited.
  - **Packet Count**: The number of probe packets sent per hop discovery that have the same TTL value. The default is `5` packets, the maximum is `20` packets, and the minimum is `3` packets. For Private Access, the recommended packet count is `3` packets, and the maximum is `6` packets. The Packet Count determines the number of these iterations or cycles. While the default packet count is 5, a higher packet count provides a more accurate measurement of packet loss.
  - **Interval (ms)**: The time interval between probe packets with the same TTL. Probe packets of incremental TTL are paced evenly within this time interval. The number of iterations or cycles is defined by the configured Packet Count. The default is `1000`, the minimum is `1000`, and the maximum is `10000`. If the Interval is configured as `6000` ms, the packets for a run are spaced over 6000 ms. For a maximum of 30 hops, packets are paced at every 200 ms: the first packet with TTL 1 is sent at 0 ms, the packet with TTL 2 at 200 ms, the packet with TTL 3 at 400 ms, and so on.
  - **Timeout (ms)**: The time to wait for a response to a probe packet before considering loss. The default is `1000`, the minimum is `500`, and the maximum is `5000`. The recommended setting for Private Access is `500`. If the Timeout is configured as `2000` ms, the first probe would time out at 2,000 ms, the second at 2,200 ms, the third at 2,400 ms, and so on.
  - **Cloud Path Host**: The host IP address or fully qualified domain name for the host (i.e., the IPv4 IP address; IPv6 is not supported). Zscaler partially provides the domain name if the application selected is predefined. This is editable if a tenant name (i.e., CQM application) is required.
  - **Force Reverse Cloud Path in Trusted Network**: The option to force a reverse traceroute in the trusted environment when a network device blocks the forward Cloud Path. Enable only if you cannot implement a different device configuration. Ideally, you should reconfigure the firewall or the device blocking the forward traceroute. If that isn't possible, this setting provides calculations for reverse latency from the Public Service Edge for Internet & SaaS to the Egress in the Cloud Path. During a reverse Cloud Path, the Egress IP address is derived from the location API, and therefore, can differ from the IP address used within the forward Cloud Path. In this scenario, the Egress is noted as a Reverse Egress, along with the IP address derived from the location API.

Click **Next**.

Cloud Path probes are sent every 15 minutes by default for Standard ZDX subscriptions, and every 5 minutes for Advanced ZDX subscriptions.

[Image: Configure a Web probe for an end user]

[Image: Configure a Cloud Path probe for an end user]

[Image: Add New Probe]

[Image: Select a collection with no probes]

[Image: Copy configuration]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-remediation-settings","lastmod":"2026-04-20T14:31Z","nid":"1531248"} -->
## Configuring Remediation Settings

- Source: https://help.zscaler.com/zdx/configuring-remediation-settings
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Configuring Remediation Settings
- Last modified: 2026-04-20T14:31Z
- Summary: Provides steps on the configuration settings for Remediation settings.

Remediation empowers IT administrators to remotely and securely schedule script jobs to remediate IT issues. When enabled for your users, you can run certified PowerShell scripts remotely on compatible devices.

## Prerequisites

In order to enable Remediation for users, you must have the following:

- The minimum required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#SelfService).
- Your ZDX subscription level supports Remediation. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Full permission level for Remediation Settings. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- Enable End User Notifications for Zscaler Client Connector. To learn more, see [Configuring End User Notifications for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/configuring-end-user-notifications-zscaler-client-connector).

## Configure Remediation Settings for Users

To configure the Remediation settings:

1. In the Zscaler Admin Console, go to **Policies**> **Digital Experience Monitoring**> **Remediation Settings**.
2. Select **Enable Remediation** to create user criteria for Remediation.
3. After enabling, you can configure the following: See image.
  - **General**: Configure the General settings of Remediation jobs to include and exclude specific users, user groups, locations, location groups, departments, and devices. This allows specific users to receive Remediation jobs to solve their own IT issues. You cannot select deleted or unknown users for the include and exclude criteria.
  - **Security**: Enable **Require Definitive Certification Revocation Status** to ensure each script is checked for certification validity. When enabled, a script cannot run on any device if its certification revocation status is invalid.
4. **Save** your Remediation Settings.

If Remediation is enabled and the jobs have started to run scripts, you can review all jobs on the [Remediation Jobs page](https://help.zscaler.com/zdx/viewing-and-managing-remediation-jobs).

[Image: Enable Remediation for users on the Remediation Settings page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-saml-zdx-admins","lastmod":"2025-04-24T13:58Z","nid":"1382401"} -->
## Configuring SAML for ZDX Admins

- Source: https://help.zscaler.com/zdx/configuring-saml-zdx-admins
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Configuring SAML for ZDX Admins
- Last modified: 2025-04-24T13:58Z
- Summary: How to configure SAML authentication for administrators in the ZDX Admin Portal.

The Zscaler service supports identity provider (IdP)-initiated SAML to authenticate administrators. The admin can log in to the ZDX Admin Portal directly from a Single Sign-On (SSO) provider's portal. This feature also enables you to integrate admin authentication with your existing two-factor authentication solution.

Admins are not added through auto-provisioning. Rather, an admin must be [added](https://help.zscaler.com/zdx/adding-zdx-admins) in the ZDX Admin Portal, and then the admin can use SAML authentication to log in. The Zscaler service provides a password authentication option for admins, but the Zscaler service recommends that admins use SAML authentication to log in to the ZDX Admin Portal. However, the service also recommends that you have at least one super admin with password authentication enabled to ensure an admin can still access the ZDX Admin Portal if SAML servers external to the Zscaler service become unreachable. The Zscaler service supports SAML 2.0 and later.

## Prerequisites

Before you configure SAML SSO for ZDX admins, you must configure an IdP, such as AD FS, Okta, or Azure Active Directory.

The following are guides for configuring admin SAML SSO with specific IdPs:

- [Admin SAML Configuration Guide for AD FS 3.0](https://help.zscaler.com/zdx/admin-saml-configuration-guide-ad-fs-3.0)
- [Admin SAML Configuration Guide for Azure Active Directory](https://help.zscaler.com/zdx/admin-saml-configuration-guide-azure-active-directory)
- [Admin SAML Configuration Guide for Okta](https://help.zscaler.com/zdx/admin-saml-configuration-guide-okta)
- [Admin SAML Configuration Guide for PingFederate](https://help.zscaler.com/zdx/admin-saml-sso-configuration-guide-pingfederate)

When configuring IdPs, the following information might be required for ZDX.

- ACS URL:

For ZDX Cloud:

```
https://admin.zdxcloud.net/zdx/idp-auth
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net/zdx/idp-auth
```

- Download the SAML SSL certificate from the IdP. It must be in Base64-encoded PEM format.
- Entity ID:

For ZDX Cloud:

```
https://admin.zdxcloud.net
```

For ZDX Beta Cloud:

```
https://admin.zdxbeta.net
```

If you have a domain defined on multiple ZIA clouds, enter the ZIA cloud name that is associated with ZDX in the **Relay State** field (for example, `zscalertwo.net`) for each application.

You must also create admin accounts for your organization's admins. To learn more, see [Adding ZDX Admins](https://help.zscaler.com/zdx/adding-zdx-admins).

## Configuring SAML SSO for ZDX Admins

To configure SAML SSO for admins in the ZDX Admin Portal:

1. Go to**Administration**>**Administrator Management**.
2. Click the **Administrator Management** tab.
3. In the **SAML Authentication for Administrators** section, do the following:
  - **Enable** **SAML Authentication**: Enable this setting to allow admins to log in to the ZDX Admin Portal directly from your SSO provider portal. An IdP (such as AD FS or Okta) must already be configured for your organization, and you must add the admin account in the ZDX Admin Portal, rather than through auto-provisioning.
  - **IdP SAML Certificate**:Upload the SAML public certificate that is used to verify the digital signature of the IdP. This is the Base64-encoded PEM format that you downloaded from the IdP. The file extension must be .pem or .cer and have only alphanumeric characters in the file name. If the file name contains non-alphanumeric characters (e.g., period, hyphen), rename the file name to consist of only alphanumeric characters. See image.
  - **Download XML Metadata**:Download the XML metadata of the Zscaler service. The metadata details Zscaler SAML capabilities and is used for auto-configuration. Some IdPs require the metadata to configure service providers.
  - **Issuer**: (Optional) Enter the IdP issuer associated with the Zscaler service, and click **Add Items**. You can enter multiple entries. Press `Enter` after each entry. For item lists, you can view up to 500 items on a page; filter the list by searching for a word, phrase, or number contained in an item; and remove all items from the list (**Remove All**) or only items from a specific page (**Remove Page**). If you select **Remove All** or **Remove Page**, a confirmation window appears. See image.
4. Click **Save** and [activate the change](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

[Image: Screenshot of Upload button for the IdP SAML certificate]

[Image: Screenshot of the SAML Authentication for Administrators section on the Administrator Management page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-self-service-settings","lastmod":"2026-04-20T15:53Z","nid":"1459021"} -->
## Configuring Self Service Settings

- Source: https://help.zscaler.com/zdx/configuring-self-service-settings
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Configuring Self Service Settings
- Last modified: 2026-04-20T15:53Z
- Summary: Provides steps on the configuration settings for Self Service settings.

[Watch a video about Self Service](https://fast.wistia.net/embed/iframe/3lcjrdtwo6) (shows legacy UI).

Self Service can help users identify the root cause of issues related to CPU usage and Wi-Fi access, allowing users to investigate potential solutions without the need to contact customer support. When enabled for your users, Self Service provides notifications when issues are detected and need attention. Each notification contains a brief diagnosis and recommendation that might resolve the CPU or Wi-Fi issue.

## Prerequisites

In order to enable Self Service notifications for users, you must:

- Have the minimum required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#SelfService).
- Ensure your ZDX subscription level supports Self Service. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Have full permission level for Self Service on ZDX. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- Enable Zscaler Client Connector Notification Framework on the Notifications page in the Zscaler Admin Console. To learn more, see [Using the Zscaler Notification Framework](https://help.zscaler.com/client-connector/using-zscaler-notification-framework).

## Configure Self Service Notifications for Users

Admins can configure the Self Service Settings page to enable Self Service notifications for users.

To configure the Self Service notifications for users:

1. Go to **Policies** > **Digital Experience Monitoring** > **Self Service**.
2. Select **Enable Self Service** to create criteria for Self Service notifications.
3. After enabling, you can configure the following: See image.
  - **General Settings**: Admins can configure the General Settings of Self Service to include and exclude specific users, user groups, locations, location groups, departments, and devices. This allows specific users to receive a diagnosis and recommendations to solve their own IT issues when the Zscaler Notifications are enabled. You cannot select deleted or unknown users for the include and exclude criteria.
  - **Notification Settings**: Admins can configure the Notification Settings of Self Service to send push notifications via Zscaler Client Connector or enable users the ability to configure notifications.
4. **Save** your Self Service Settings.

If Self Service is enabled and notifications have started, you can review the notifications sent to the impacted users on the Self Service Overview dashboard. To learn more, see [Monitoring the Self Service Dashboard](https://help.zscaler.com/zdx/monitoring-self-service-dashboard).

[Image: Enable Self Service]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-webex-call-quality-zdx","lastmod":"2026-05-26T11:57Z","nid":"1443551"} -->
## Configuring Webex Call Quality for ZDX

- Source: https://help.zscaler.com/zdx/configuring-webex-call-quality-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Configuring Webex Call Quality for ZDX
- Last modified: 2026-05-26T11:57Z
- Summary: How to configure a new tenant to monitor Webex Call Quality.

You can configure Webex Call Quality to monitor audio calls or meetings among two or more users. Call Quality can help you pinpoint issues that are unique to a device or the network by working in parallel with its Cloud Path probe. To learn more, see [Understanding Webex Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-webex-call-quality-zdx).

## Prerequisites

Before onboarding a Webex Call Quality tenant, ensure:

- You're running the required versions of Zscaler Client Connector and ZDX Module to configure a ZDX Autosense probe. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#ZDXAutosenseZoom).
- Your ZDX subscription level supports ZDX Autosense. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- You enable the Windows Filtering Platform (WFP) driver installation setting for ZDX Autosense in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
- You enable the setting to collect device hostname information in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector to Collect Hostnames](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-hostnames).

## Onboarding a Tenant

In the Zscaler Admin Console:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**> **Unified Communication Collection** > **Webex Call Quality**. See image.
2. Click **Onboard Application**. The Webex Call Quality drawer appears. See image.
3. Click **Authenticate**. You must authenticate with Webex before you can save the new tenant. You must also reauthenticate whenever you update the Monitoring Criteria settings.
4. Enter your Webex credentials to sign in. See image.
5. Accept the requested permissions from Webex. See image.
6. Return to the **Add New Webex Call Quality Tenant** window and click **Save**.
7. (Optional) From the Predefined Applications list, select the Webex Call Quality tenant name. Click **Validate**within the dialog window to verify your Webex setup was successful.
8. [Activate your changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

## Adding a Tenant

In the Zscaler Admin Console:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**>**End User** > **Unified Communication Collection** > **Webex Call Quality**.
2. Click **Add** and then click **Tenant** to begin configuring a Unified Communications as a Service (UCaaS) tenant. See image.
3. For **Select an Application**: Select a UCaaS application (i.e., Webex Call Quality). See image.
4. For **Configure Tenant**: See image.
  - **Name**: Enter the name for the tenant.
  - **Status**: Toggle to enable or disable the tenant.
  - **Monitoring Criteria**: Configure the Inclusion Criteria for **User Groups**, **Users**, and **Departments**. Meetings are monitored and displayed only for your selected ZDX users in the **Inclusion Criteria** of the **Monitoring Criteria**. Selections among the filters are cumulative, whereas selections within a single filter are not cumulative. For example, if you select DevTest and Service Admin in the User Groups filter, and then select Engineering and IT in the Departments filter, you can identify users who belong to the DevTest or Service Admin user group and the Engineering or IT department. You can specify:
    - **User groups**: Include one or more user groups.
    - **Users**: Include multiple users.
    - **Departments**: Include entire departments.
  - **Authentication**: Authenticate your Webex tenant for ZDX to gather monitoring metrics.
5. For **Review**: Review your configuration settings for the UCaaS tenant and click **Save**.
6. [Activate the changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

After configuring a tenant, you can:

- [Edit the probes and ZDX autosense](https://help.zscaler.com/zdx/configuring-probe) to monitor Webex Call Quality. Meetings are monitored for the selected users according to the [Inclusion and Exclusion probing criteria](https://help.zscaler.com/zdx/understanding-probing-criteria-logic).
- Edit the tenant configuration. See image.
- Delete the tenant.

You must reauthenticate with Webex if you configure or update the criteria settings.

To learn more about managing your collections, see [Managing Collections](https://help.zscaler.com/zdx/managing-collections).

[Image: Onboard Webex tenant]

[Image: Click Authenticate to be directed to Webex login page]

[Image: Webex Sign In Window]

[Image: Window to accept Webex permissions]

[Image: Add Tenant]

[Image: Select an Application]

[Image: Configure Tenant]

[Image: View Tenants]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-webhooks","lastmod":"2026-07-10T09:57Z","nid":"1364556"} -->
## Configuring Webhooks

- Source: https://help.zscaler.com/zdx/configuring-webhooks
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Configuring Webhooks
- Last modified: 2026-07-10T09:57Z
- Summary: Information on configuring webhooks to deliver alerts.

[Watch a video about Configuring Webhooks for ZDX](https://fast.wistia.net/embed/iframe/sizniej3b3) (shows legacy UI).

You can configure webhooks to deliver alerts about your application, device, or network performance. You can also use webhooks in an alert rule and configure multiple alert rules to the same webhook from your third-party provider. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).

To configure a new webhook for ZDX:

1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
2. Click **Add Webhook**.
3. In the **Add Webhook** window:
  - **Name**: Enter the name of the webhook.
  - **Status**: Choose either **Enabled** or **Disabled**.
  - **URL**: Enter the URL of the webhook provider. Ensure your URL does not include any spaces.
  - **Authentication Type**: Choose either **Basic**, **Token**, or **OAuth** for authentication. The webhook provider determines the authentication type used. Refer to your provider for details. You can configure OAuth 2.0 for ServiceNow when configuring a webhook. To learn more, see [ServiceNow Webhook Configuration Guide](https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide). See image.
    - Basic
    - Token
    - OAuth
4. Click **Test Webhook** to check the configuration.
  1. If the test is successful, a message indicating success appears. Click **Save** to save the webhook configuration and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console). Test Webhook for OAuth does not post a test message. Instead, it acquires the OAuth token.
  2. If the test is unsuccessful, an error message appears.
    - To resolve the error, check for issues in the **URL** or the **Authentication Type** fields.
    - If the error persists, click **Cancel** so that the webhook configuration containing errors is not saved.

After the webhook is configured on your webhook provider site, you can view alert information such as:

- Alert start and end time
- Devices impacted
- Severity
- Rule name
- Alert criteria

The following provides guidance for webhook configuration for a supported platform:

- [Microsoft Teams Webhook Configuration Guide](https://help.zscaler.com/zdx/microsoft-teams-webhook-configuration-guide)
- [OpsGenie Webhook Configuration Guide](https://help.zscaler.com/zdx/opsgenie-webhook-configuration-guide)
- [PagerDuty Webhook Configuration Guide](https://help.zscaler.com/zdx/pagerduty-webhook-configuration-guide)
- [Slack Webhook Configuration Guide](https://help.zscaler.com/zdx/slack-webhook-configuration-guide)
- [Splunk Webhook Configuration Guide](https://help.zscaler.com/zdx/splunk-webhook-configuration-guide)
- ServiceNow Configuration Guides:
  - [ServiceNow Webhook Configuration Guide](https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide)
  - [ServiceNow Webhook Configuration Guide for Developers](https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide-developers)

Enter a **Username**and**Password**. Password information is hidden by default, but you can view it by clicking the **View** icon.

See image.

Enter the bearer token. A bearer token is a unique alphanumeric string used for authentication. You can obtain the bearer token from your webhook provider.

Some webhooks do not generate a bearer token if they have valid URLs. For those specific webhooks, enter any text (e.g., `1234`) for the bearer token field to meet webhook configuration requirements.

See image.

Enter the following:

- **Application**: Select an application.
- **Client ID**: Enter your Client ID.
- **Client Secret**: Enter your Client Secret.
- **Refresh Token Expiration**: Select the date your token expires. The default is the current date.

Click **Authenticate Tenant** to authorize access to your application as a tenant.

See image.

[Image: Add Webhook Window]

[Image: Enter a bearer token]

[Image: Enter basic fields for webhook authentication]

[Image: Enter OAuth fields for webhook authentication]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-zoom-call-quality-zdx","lastmod":"2026-05-26T11:53Z","nid":"1386326"} -->
## Configuring Zoom Call Quality for ZDX

- Source: https://help.zscaler.com/zdx/configuring-zoom-call-quality-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Configuring Zoom Call Quality for ZDX
- Last modified: 2026-05-26T11:53Z
- Summary: How to configure Zoom Call Quality monitoring for ZDX.

You can configure Zoom Call Quality to monitor calls among two or more users. Call Quality can help you pinpoint issues that are unique to a device or the network by working in parallel with its Cloud Path probe. To learn more, see [Understanding Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-zoom-call-quality-zdx).

When onboarding a Zoom Call Quality tenant for the first time, a ZDX Autosense Cloud Path probe is automatically generated that detects the destination IP address.

## Prerequisites

Before onboarding a Zoom Call Quality tenant, ensure:

- You're running the required versions of Zscaler Client Connector and ZDX Module to configure a ZDX Autosense probe. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#ZDXAutosenseZoom).
- Your ZDX subscription level supports ZDX Autosense. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- You enable the Windows Filtering Platform (WFP) driver installation setting for ZDX Autosense in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector App Profiles](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-app-profiles).
- You enable the setting to collect device hostname information in the Zscaler Admin Console. To learn more, see [Configuring Zscaler Client Connector to Collect Hostnames](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-hostnames).

## Onboarding a Tenant

In the Zscaler Admin Console:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**>**End User** > **Unified Communication Collection** > **Zoom Call Quality** > **Onboard Application**. See image
2. On the **Zoom Call Quality** window, click **Authenticate**. See image.
3. Sign in with your email and password, and then click **Authorize**to accept the Zoom permissions. A Zoom API tenant is created under **Zoom Call Quality** on the Applications page. You can either onboard the Zoom API tenant or onboard a Zoom Quality of Service Subscription (QSS) tenant for Zoom's data streaming service. See image. You cannot onboard both a Zoom API tenant and a Zoom QSS tenant at the same time.
4. (Optional) Click **Validate**to verify your setup with Zoom was successful.
5. Click **Save**.
6. [Activate the changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

## Adding a Tenant

In the Zscaler Admin Console:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**>**End User** > **Unified Communication Collection** > **Zoom Call Quality**.
2. Click **Add** and then click **Tenant** to begin configuring a Unified Communications as a Service (UCaaS) tenant. See image.
3. For **Select an Application**: Click **Next**. See image.
  - **Add To Application**: Select a UCaaS application (i.e., Zoom Call Quality).
  - **Tenant type**: Choose a Zoom tenant (**Zoom API**, **Zoom QSS**).
4. For **Configure Tenant**:
  - **Name**: Enter the name for the tenant.
  - **Status**: Toggle to enable or disable the tenant.
  - **Monitoring Criteria**: Configure the Inclusion and Exclusion Criteria for **User Groups**, **Users**, and **Departments**. To learn more, see [Understanding Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-zoom-call-quality-zdx).
    - **Inclusion Criteria**: Meetings are monitored and displayed only for your selected ZDX users in the **Inclusion Criteria** of the **Monitoring Criteria**. Selections among the filters are cumulative, whereas selections within a single filter are not cumulative. For example, if you select DevTest and Service Admin in the User Groups filter, and then select Engineering and IT in the Departments filter, you can identify users who belong to the DevTest or Service Admin user group and the Engineering or IT department. You can specify:
      - **User groups**: Include one or more user groups.
      - **Users**: Include multiple users.
      - **Departments**: Include entire departments.
    - **Exclusion Criteria**: Meetings exclude selected users from monitoring. Exclusions remove matching users from the set defined by **Exclusion Criteria**. You can specify:
      - **User groups**: Exclude one or more groups.
      - **Users**: Exclude individual users.
      - **Departments**: Exclude entire departments.
  - **Authentication**: Authenticate your Zoom tenant for ZDX to gather monitoring metrics.
5. For **Review**: Review your configuration settings for the UCaaS tenant and click **Save**.
6. [Activate the changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

After configuring a Zoom tenant, you can:

- [Edit the probes and ZDX autosense](https://help.zscaler.com/zdx/configuring-probe) to monitor Zoom meetings. Zoom Meetings are monitored for the selected users according to the [Inclusion and Exclusion probing criteria](https://help.zscaler.com/zdx/understanding-probing-criteria-logic).
- Edit the tenant. See image.
- Delete the tenant.

You must reauthenticate with Zoom if you configure or update the criteria settings.

To learn more about managing your collections, see [Managing Collections](https://help.zscaler.com/zdx/managing-collections).

[Image: Zoom Call Quality Onboarding]

[Image: Authenticate the Zoom tenant]

[Image: Enter Zoom credentials to sign in]

[Image: Add Tenant]

[Image: Select an Application]

[Image: View Tenants]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/configuring-zscaler-managed-probes","lastmod":"2026-06-25T21:06Z","nid":"1505996"} -->
## Configuring Zscaler Managed Probes

- Source: https://help.zscaler.com/zdx/configuring-zscaler-managed-probes
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Probes > Configuring Zscaler Managed Probes
- Last modified: 2026-06-25T21:06Z
- Summary: How to configure Zscaler Managed collections and probes.

[Watch a video about configuring and managing Zscaler Managed Probes.](https://fast.wistia.net/embed/iframe/3kol3ib93j)

Zscaler Managed probes operate from within the Zscaler cloud infrastructure as a multi-tenant service. The service allows you to logically group Web and Cloud Path probes into independent collections to set up your own tests for monitoring performance. As an extension to ZDX probes for predefined and custom applications, Zscaler Managed probes monitor the performance of a specific service or application directly from a Zscaler data center to an endpoint destination. To learn about managed probe metrics, see [Understanding Managed Monitoring](https://help.zscaler.com/zdx/understanding-managed-monitoring).

## Prerequisites

Before configuring Zscaler Managed probes, ensure:

- Your ZDX subscription level supports Managed Monitoring. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience). Zscaler Managed probes and companion probes count towards your Managed Monitoring probes.
- Your ZDX role has the proper permission level. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- You have [configured an application](https://help.zscaler.com/zdx/adding-custom-application) and associated it to a [collection](https://help.zscaler.com/zdx/understanding-collections).

## Adding a Zscaler Managed Probe

After configuring an application, add a probe:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes** > **Managed Collection** > <application name>.
2. Click **Add Probe**.
3. In the **Select a Probe Type** tab:
  1. **Add to Application**: Select which collection you are adding the probe to.
  2. **Select a Probe Type**: Select the Managed Web or Managed Cloud Path configuration, and then click **Next**:
    - Configuring a Web probe.
    - Configuring a Cloud Path probe.

To learn more about managing your active probes, see [Managing a Zscaler Managed Probes](https://help.zscaler.com/zdx/managing-zscaler-hosted-probes).

Web probe configuration consists of the following steps:

- a. Web Probe
- b. Additional Options
- c. (Optional) Companion Probe
- d. (Optional) Add Alerts
- e. Review

[Image: Configure a Web probe]

- **Probe Name**: Enter a probe name. The maximum length is 64 characters. Accepted characters are alphanumeric and a limited range of symbols, such as underscore (_), hyphen (-), space ( ), forward slash (/), period (.), pipe symbol (|), and parentheses ().
- **Status**: Select **Enable**or **Disable**. **Enable** is selected by default.
- **Destination URL or IP address (IPv4 or IPv6)**: Enter the web destination (HTTP or HTTPS URL) or an IP address.
- **Request Header**: Enter the HTTP request header name and value to pass as part of the probe.
- **HTTP Response Status Code**: By default, **Informational responses (100–199)** and **Successful responses (200–299)** are selected.
- **Timeout (seconds)**: The default timeout is set to `60` seconds.
- **Run Frequency (minutes)**: Enter the number of minutes that indicates how frequently your probe should run. The default frequency can vary, based on your subscription plan. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- **Follow Redirect**: Select **Enable** or **Disable** to follow redirect URLs.
- **Maximum Redirects**: Enter the number of times the probe attempts to follow the HTTP redirect before it is considered failed. The default is `5` redirects. If **Follow Redirect** is disabled, **Maximum Redirects**is also disabled.
- **Zscaler Managed Locations**: From the drop-down menu, select the Zscaler data centers from where the probe is running.

See image.

Click **Next**.

Configure additional options for the Web probe by entering the following fields:

- **Default HTTP Version**: Select the version of HTTP that is used.
- **Override DNS**: Select **Enable** to alter DNS settings. **Disable** is the default setting.
- **Override**: If **Override DNS** is set to **Enable**, you can select to override the **DNS Server** or **DNS Resolution**.
- **DNS Server**: If **Override DNS** is set to **Enable**, you can enter a DNS Server.
- **Cache DNS Results**: **Enable** to cache the DNS results. **Disable** is the default setting.

See image.

Click **Next**.

Pair the Web probe with a Cloud Path probe. Companion probe supports Cloud Path probe configuration by entering the following fields:

- **Companion Probe Type**: Select **Cloud Path Probe**.
- **Status**: **Enable**the companion probe for use. **Enable** is selected by default.
- **Protocol**: Select the protocol from the drop-down menu (e.g., **ICMP**, **TCP**). The default is **TCP**.
- **End-to-end Metrics Probing Type**: Select the method for assessing end-to-end network performance metrics, such as latency, jitter, and packet loss. The available options depend on the selected protocol:
  - **For TCP Protocol:**
    - **None (Default)**: No additional probing is performed.
    - **Prefer SACK**: Uses TCP Selective Acknowledgment (SACK) to measure retransmission efficiency, ideal for lossy or high-latency networks.
    - **Force SACK**: Forces SACK probing, even if the destination does not explicitly advertise support.
    - **Force SYN**: Sends SYN packets for lightweight, connectionless probing.
  - **For ICMP Protocol:**
    - **None (Default)**: No additional probing is performed.
    - **ICMP**: Measures network performance using Internet Control Message Protocol (ICMP) probes.
- **Probing Type (TCP Only)**: Specify the method for running Cloud Path probes when TCP is selected. The default setting is **SYN**.
  - **SYN**: Sends SYN packets to perform path discovery.
  - **SACK**: Uses TCP SACK for connection-based path discovery and network diagnostics.
- **Capture PMTU**: Enable or disable Path Maximum Transmission Unit (PMTU) discovery to detect packet size-related issues along the network path.
  - **Enabled**: Automatically detects the MTU size along the network path.
  - **Disabled**: Disables automatic MTU detection.
- **DSCP Configuration**: Select the Differentiated Services Code Point (DSCP) value to specify a priority for packets sent during probing.
  - **Best Effort (DSCP 0)**: Default traffic prioritization.
  - **CS1–CS7**: Levels indicating progressively higher priority.
  - **AF11–AF43**: Assured forwarding classifications to manage packet drop precedence.
  - **EF PHB (DSCP 46)**: Best for expedited forwarding and high-priority traffic management.
  - **Voice Admit (DSCP 44)**: Specialized configuration for voice traffic prioritization.
- **Hop Count**: Enter the maximum number of Cloud Path hops allowed before reaching the destination. If the hop count exceeds this setting, the probe run has failed. The default setting is `30` hops, the maximum setting is `64`, and the minimum setting is `10`.
- **Packet Count**: Enter the number of probe packets to send per hop discovery that have the same Time to Live (TTL) value. The default setting is `3` packets, the maximum setting is `11` packets, and the minimum setting is `1` packet.
- **Interval (ms)**: Enter the time interval between probe packets with the same TTL. Probe packets of incremental TTL are paced evenly within this time interval. The number of iterations or cycles is defined by the configured **Packet Count**. The default is `1000`, the maximum is `10000`, and the minimum is `1000`.
- **Timeout (ms)**: Enter the time to wait for a response to a probe packet before considering loss. The default is `500`, the maximum is `2000`, and the minimum is `500`.

See image.

Click **Next**.

When the Web probe resolves to an IP address, then the Cloud Path probe runs to the same IP address.

1. Click **Add**.
2. Click **Create New Template** to add a new alert template or select an existing alert with predefined criteria. Each existing template is noted with a color for High, Medium, or Low alert severity. Alert templates are useful when reusing settings for alert rules. To learn more about alert templates and alert rules, see [About Templates](https://help.zscaler.com/zdx/about-templates) and [About Rules](https://help.zscaler.com/zdx/about-rules).
3. Click **Next**.

[Image: Configure an alert with a template]

1. Click **Add**.
2. Click **Create New Template** to add a new alert template or select an existing alert with predefined criteria. Each existing template is noted with a color for High, Medium, or Low alert severity. Alert templates are useful when reusing settings for alert rules. To learn more about alert templates and alert rules, see [About Templates](https://help.zscaler.com/zdx/about-templates) and [About Rules](https://help.zscaler.com/zdx/about-rules).
3. Click **Next**.

[Image: Configure an alert with a template]

On the **Review**tab, confirm your configuration, and click **Submit**. The probe is added to your collection.

On the **Review**tab, confirm your configuration, and click **Submit**. The probe is added to your collection.

Managed Cloud Path configuration consists of the following steps:

- a. Cloud Path Probe
- b. (Optional) Add Alerts
- c. Review

[Image: Configure a Cloud Path probe]

- **Probe Name**: Enter a probe name. The maximum length is 64 characters. Accepted characters are alphanumeric and a limited range of symbols, such as underscore (_), hyphen (-), space ( ), forward slash (/), period (.), pipe symbol (|), and parentheses ().
- **Status**: Select **Enable**or **Disable**. **Enable** is selected by default.
- **Protocol**: Select the protocol from the drop-down menu (e.g., **ICMP**, **TCP**). The default is **TCP**.
- **End-to-end Metrics Probing Type**: Select the method for assessing end-to-end network performance metrics, such as latency, jitter, and packet loss. The available options depend on the selected protocol:
  - **For TCP Protocol:**
    - **None (Default)**: No additional probing is performed.
    - **Prefer SACK**: Uses TCP SACK to measure retransmission efficiency, ideal for lossy or high-latency networks.
    - **Force SACK**: Forces SACK probing, even if the destination does not explicitly advertise support.
    - **Force SYN**: Sends SYN packets for lightweight, connectionless probing.
  - **For ICMP Protocol:**
    - **None (Default)**: No additional probing is performed.
    - **ICMP**: Measures network performance using Internet Control Message Protocol (ICMP) probes.
- **Probing Type (TCP Only)**: Specify the method for running Cloud Path probes when TCP is selected. The default setting is **SYN**.
  - **SYN**: Sends SYN packets to perform path discovery.
  - **SACK**: Uses TCP SACK for connection-based path discovery and network diagnostics.
- **Capture PMTU**: Enable or disable Path Maximum Transmission Unit (PMTU) discovery to detect packet size-related issues along the network path.
  - **Enabled**: Automatically detects the MTU size along the network path.
  - **Disabled**: Disables automatic MTU detection.
- **DSCP Configuration**: Select the Differentiated Services Code Point (DSCP) value to specify a priority for packets sent during probing.
  - **Best Effort (DSCP 0)**: Default traffic prioritization.
  - **CS1–CS7**: Levels indicating progressively higher priority.
  - **AF11–AF43**: Assured forwarding classifications to manage packet drop precedence.
  - **EF PHB (DSCP 46)**: Best for expedited forwarding and high-priority traffic management.
  - **Voice Admit (DSCP 44)**: Specialized configuration for voice traffic prioritization.
- **Run Frequency (minutes)**: Enter the number of minutes that indicate how frequently your probe should run. The default frequency can vary, based on your subscription plan. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- **Hop Count**: Enter the maximum number of Cloud Path hops allowed before reaching the destination. If the hop count exceeds this setting, the probe run has failed. The default setting is `30` hops, the maximum setting is `64`, and the minimum setting is `10`.
- **Packet Count**: Enter the number of probe packets to send per hop discovery that have the same Time to Live (TTL) value. The default setting is `3` packets, the maximum setting is `11` packets, and the minimum setting is `1` packet.
- **Interval (ms)**: Enter the time interval between probe packets with the same TTL. Probe packets of incremental TTL are paced evenly within this time interval. The number of iterations or cycles is defined by the configured **Packet Count**. The default is `1000`, the maximum is `10000`, and the minimum is `1000`.
- **Timeout (ms)**: Enter the time to wait for a response to a probe packet before considering loss. The default is `500`, the maximum is `2000`, and the minimum is `500`.
- **Host**: Enter the host IP address or fully qualified domain name for the host (i.e., the IPv4 IP address, as IPv6 is not supported).
- **Zscaler Managed Locations**: From the drop-down menu, select the Zscaler data centers from where the probe is run.

See image.

Click **Next**.

[Image: Configure Web probe]

[Image: Configure Additional Options]

[Image: Configure a Companion probe for the Web probe]

[Image: Configure Cloud Path probe parameters]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/downloading-quarterly-business-review-reports","lastmod":"2024-03-17T07:06Z","nid":"1456811"} -->
## Downloading Quarterly Business Review Reports

- Source: https://help.zscaler.com/zdx/downloading-quarterly-business-review-reports
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Downloading Quarterly Business Review Reports
- Last modified: 2024-03-17T07:06Z
- Summary: How to download QBR reports from the left-side navigation menu of the ZDX Admin Portal.

The Quarterly Business Review (QBR) report provides extensive insight into how Zscaler is helping protect your network, quarter to quarter. The reports can help you observe emerging traffic trends and the types of threats that Zscaler is blocking. To learn more, see [Viewing Quarterly Business Review Reports](https://help.zscaler.com/zdx/viewing-quarterly-business-review-reports).

A new QBR report is generated on the first weekend of every month, and each report is securely stored as a PowerPoint file in the Zscaler cloud. These reports are available to ZDX users of all ZDX subscription levels. If you don't see the reports, contact your support team or open a Zscaler Support ticket to have it enabled.

You must have a minimum of 100 active devices for 30 days to generate and view QBR reports.

You can download and view the reports from the left-side navigation of the ZDX Admin Portal:

1. Go to **Analytics**> **Quarterly Business Reviews**.
2. Click the **Download**icon within the table to download a specific report.

Use the search field if you're unable to find a report. Search for any character string that might be part of the report name or time period.

[Image: Download Quarterly Business Review Reports]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/editing-alert-rule","lastmod":"2026-07-10T09:59Z","nid":"1364461"} -->
## Editing an Alert Rule

- Source: https://help.zscaler.com/zdx/editing-alert-rule
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Alert Rule Configuration > Editing an Alert Rule
- Last modified: 2026-07-10T09:59Z
- Summary: Provide step-by-step instructions on how to edit an alert rule.

After configuring an alert rule and saving it, you can edit certain rule fields. The editable fields for a rule depend on the rule type.

The ZDX Score alert rule type is no longer recommended for use. Any pre-existing ZDX Score alert rule type has migrated to an Application alert rule type. If you have an existing ZDX Score alert rule type, you must create an alert rule with the type as Application or Network and then select ZDX Score as a criteria.

- Example

| **Configuration Field Name** | **Example Values** | **Translated Example Values** |
| --- | --- | --- |
| Type | ZDX Score | Application or Network |
| Criteria | ZDX Score < 33 | ZDX Score < 33 |

The throttling and other field values remain the same.

1. Go to **Analytics** > **Digital Experience** > **Alerts** > **Alert Rules and Templates**.
2. Click the **Edit** icon listed next to the details for a particular rule. The **Edit**window opens.
3. In the **Edit**window:
  - a. Configure Rule
  - b. Filters
  - c. Criteria
  - d. Actions
  - e. Review
4. Click **Save** and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

The alerts triggered have a display delay of 30 minutes.

On the **Configure Rule** tab, you can edit:

- **Name**: Enter a name to identify the rule.
- **Status**: Select from **Enabled** or **Disabled**. Select **Enabled** to enable the rule.
- **Severity**: Select **High**, **Medium**, or **Low** options for severity, depending on the impact of this event on users.
- **Type**: This was previously selected. Application and Network include ZDX Score and ZDX Score Drops detection as a criteria for Dynamic Alerting. This feature and its procedures are available based on your subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience). If you selected **Incident** as your type, you can specify which Incident Types to configure an alert rule for. To learn more, see [Monitoring the Incidents Dashboard](https://help.zscaler.com/zdx/monitoring-incidents-dashboard). See image.
- **Labels (Optional)**: Select the applicable labels for the alert rule. You can also search for the label name to select. To learn more, see [About Labels](https://help.zscaler.com/zdx/about-labels).

See image.

On the **Filters** tab, depending on your rule type, modify the parameters and filters as necessary. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).

On the **Criteria** tab and depending on what you select as your Rule Type in the Configure Rule tab, modify the fields as necessary. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).

On the **Actions** tab and depending on what you select as your Rule Type in the Configure Rule tab, modify the fields as necessary. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).

On the **Review** tab, review your rule configuration and click **Submit**.

[Image: Editing an Alert Rule]

[Image: Editing an Incident Alert Rule Type]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/editing-application","lastmod":"2026-06-24T21:06Z","nid":"1355651"} -->
## Editing an Application

- Source: https://help.zscaler.com/zdx/editing-application
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Editing an Application
- Last modified: 2026-06-24T21:06Z
- Summary: Information about how to edit an application for ZDX.

To edit an application:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**, and select a collection or search for the application.
2. Under **Actions**, click **Edit**for the application you want to edit. See image. The **Edit Application** drawer appears. See image.
3. In the **Edit Application**drawer, you can edit the following:
  - **Name:**Change the name of any custom application. You cannot change the name of predefined applications.
  - **Status:** Toggle to **Enable** or **Disable** the application.
  - **Description**: Change the description of the application. You cannot edit the Application type.
4. Click **Update**.
5. [Activate your changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

[Image: Select which action for to configure the application]

[Image: Editing an application]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/editing-probe","lastmod":"2026-04-28T21:06Z","nid":"1355656"} -->
## Editing a Probe

- Source: https://help.zscaler.com/zdx/editing-probe
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Probes > Editing a Probe
- Last modified: 2026-04-28T21:06Z
- Summary: Information about how to edit a probe for ZDX.

After adding an application and configuring its probes, you can edit any probe details that are not preconfigured.

You cannot reconfigure preset probe configurations. To learn more about configuring probes, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe).

To edit a probe:

1. Go to **Policies** > **Digital Experience Monitoring** > **Probes**,select a collection, then select an application.
2. Under **Actions**, click **Edit**. See image.
3. In the **Edit Probe** window, configure the fields as needed. To learn more about the fields, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe). See image. Some fields cannot be edited due to the nature of the previously selected probe configuration. Click **Next**.
4. **Submit** your settings on the **Review** tab.
5. [Activate your changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

Zscaler recommends waiting at least 30 minutes after editing probe settings to begin seeing changes in the dashboard.

[Image: Click Edit to open the Edit Probe configuration]

[Image: Edit the probe configuration]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/evaluating-application-details","lastmod":"2026-06-18T11:15Z","nid":"1535278"} -->
## Evaluating Application Details

- Source: https://help.zscaler.com/zdx/evaluating-application-details
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Applications > Evaluating Application Details
- Last modified: 2026-06-18T11:15Z
- Summary: Information on evaluating application details to summarize the application's digital experience.

For each application on the Applications Overview, you can click the name in the table to view more information about it. This includes the application score over time, the impact it has on different groups (e.g., regions, departments), and the probes that are tracking the status of the application. If the application has [Real User Monitoring (RUM) enabled](https://help.zscaler.com/zdx/adding-custom-application), you can view additional, RUM-specific performance metrics. For network applications that use a single Cloud Path probe, End-to-End Latency information is provided in place of Page Fetch Time to calculate the [ZDX Score](https://help.zscaler.com/zdx/understanding-zdx-score). To learn more, see [Adding a Custom Application](https://help.zscaler.com/zdx/adding-custom-application).

To access the application details, go to the Applications Overview and select an application to view details. Alternatively, in the Applications Overview, you can search for an application to view application details.

You can view the following application tabs depending if [Real User Monitoring is enabled](https://help.zscaler.com/zdx/adding-custom-application) on the configured application.

| Application Tabs | Disabled Real User Monitoring | Enabled Real User Monitoring |
| --- | --- | --- |
| Summary | No | Yes |
| Web Probe Performance | Yes | Yes |
| Real-User Performance | No | Yes |
| Errors | No | Yes |
| Network Probe Performance | Yes | Yes |

Review the [prerequisites for Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring) in order to view Real User Monitoring details.

Any filters you select on the Applications Overview page remain in place after selecting an application. You can adjust the filters or remove all of your filter selections by clicking **Reset**. Similar to the Applications Overview page, you can also click **Share Snapshot**to capture a snapshot of the current state of the details page to share with other ZDX users or admins. To learn more, see [Sharing ZDX Snapshots](https://help.zscaler.com/zdx/sharing-zdx-snapshots).

- Departments
- Zscaler Locations
- Geolocations
- User Groups
- Location Groups
- Last Mile ISPs
- Operating Systems
- Browsers The Browsers filter is available only for Real User Monitoring applications.

## Summary

On the **Summary** tab, you view an overall summary of how the web application is doing. You see the following information:

- **Web & Network Probe Performance**: The network performance metrics for both the web and network probe.
  - **Average Page Fetch Time**: The average, the percentage change, and a graphical representation of the page fetch time.
  - **Average Latency**: The average, the percentage change, and a graphical representation of the network latency.
- **Real User Performance**: Real user performance and experience with the following metrics:
  - **Total Page Views**: The total number, percentage change, and a graphical representation of page views.
  - **Total Route Changes**: The total number, percentage change, and a graphical representation of route changes.
  - **Average Page Load Time**: The average, percentage change, and a graphical representation of the page load time.
  - **Average Time To First Byte**: The average, percentage change, and a graphical representation of the Time To First Byte (TTFB).
- **Errors**: The web application's error metrics. Alternatively, you can access the Errors tab for more details.
  - **Total Errors**: The total number, percentage change, and a graphical representation of errors.
  - **Error Rate**: The error rate and a graphical representation of the error rate.
- **ZDX Score Trend**: The [ZDX Score](https://help.zscaler.com/zdx/understanding-zdx-score) trend chart over the selected time period for the application. You can hover over to view the ZDX Score at the selected time and click to view impacted users in a drawer.
- **Users by Experience Score**: A chart to display the number of users by experience score category.
- **Geolocations by ZDX Score**: A geographical map to indicate where the impacted users are based on their ZDX Score. You can hover over a location to view the ZDX Score, total users, and click to view impacted users in a drawer.
- **Browsers Distribution**: A distribution chart of the browsers used to access the application.
- **Operating Systems Distribution**: A distribution chart of devices and their associated operating systems accessing the application.

See image.

## Web Probe Performance

The **Web Probe Performance** tab provides details on the Web probe that captures page fetch time metrics. You can view the following metrics:

- **Application Probe Status**: The overall probe status of the configured application and its URL. Each metric includes the minimum, average, and maximum for the following: If the Web probe is configured for different URLs, then you can opt to switch between views.
  - Page Fetch Time
  - Server Response Time
  - DNS Time
  - Availability
- **Synthetic Probe Score and Page Fetch Time Trends**: Compare the Synthetic Probe Score trend with the Page Fetch Time trend to understand when the drop in digital experience occurs.
- **Geolocations by Synthetic Probe Score**: A geographical map to indicate where the impacted users are based on their Synthetic Probe Score. You can hover over a location to view the score, total users, and click to view impacted users in a drawer.
- **Impacted Departments**: Up to the 5 most impacted departments and their Synthetic Probe Score.
- **Impacted Regions**: Up to the 5 most impacted regions and their Synthetic Probe Score.
- **Impacted Locations**: Up to the 5 most impacted locations and their Synthetic Probe Score.

See image.

## Real-User Performance

The Real-User Performance tab evaluates page views, route changes, page load time, and time to first byte. These metrics provide a comprehensive visibility into the application pages to understand which pages is most used for the application.

- **Total Page Views**: The total number, percentage change, and a graphical representation of page views.
- **Total Route Changes**: The total number, percentage change, and a graphical representation of route changes.
- **Average Page Load Time**: The average, percentage change, and a graphical representation of the page load time.
- **Average Time To First Byte**: The average, percentage change, and a graphical representation of the TTFB.
- **RUM Score, Page Views, and Load Time Trend Analysis**: Comparison between the RUM Score, page views, and load time trends to analyze at a selected time.
- **Geolocations by RUM Score**: A geographical map to indicate where the impacted users are based on their RUM Score. You can hover over a location to view the RUM Score, total users, and click to view impacted users in a drawer.
- **Most Viewed Pages**: A list of the pages that are most frequently accessed by users with the following information: You can view the **Page Analysis** page. See image. On the **Page Analysis** page, you can:
  - **View Path Group**: The path URL to the page.
  - **Page Views**: The number of page views.
  - **Users**: The number of users accessing that page.
  - **Average PFT**: The average of the page fetch time (PFT).
  - **P95 PFT**: The 95th percentile of the PFT.
  - Switch between the Page and Resource view.
  - Click a point on the **Total Users and Average Page Load Time** graph to view the **Total Users** and **Average Page Load Time**.
  - View a list of the following details:
    - **Device**: The device ID. Clicking the device displays its Device Details page.
    - **User**: The user ID. Clicking the user displays its [User Details page](https://help.zscaler.com/zdx/evaluating-user-details).
    - **Operating System**: The OS of the device.
    - **Browser**: The web browser to access the web page.
    - **Page Views**: The number of views of the web page.
    - **Errors**: The number of web errors that occurred.
    - **Average Error Rate**: The average error rate that occurs.
    - **Average Page Load Time**: The average page load time.
  - Under **Waterfall Chart**, click the **View** icon to go to the **Waterfall Analysis** page of the user. See image. You can see the following metrics:
    - User Page Load Time Summary
    - Page Overview that includes Total Requests, Resources, Resource Size, Uncompressed Resources, Uncached Resources, Number of Users, Total Errors, and Core Web Vitals (LCP, INP, CLS).
    - Resource Overview that includes Document Request, Size, Protocol, Status.
- **Top XHR and Fetch Analysis**: A list of XMLHttpRequests (XHR) and fetch calls with the following information: You can view the **Resource Analysis** page. See image. On the **Resource Analysis** page, you can:
  - **Resource Path**: The resource path URL to the page.
  - **Requests**: The number of XHR requests sent.
  - **Average Resource Duration**: The average resource duration between when the XHR request is sent and when the time it ended.
  - **Average Resource Size**: The average resource size of the XHR request.
  - Switch between the Page and Resource view.
  - Click a point on the **Total Users and Average Page Load Time** graph to view the **Total Users** and **Average Page Load Time**.
  - View a list of the following details:
    - **Device**: The device ID. Clicking the device displays its Device Details page.
    - **User**: The user ID. Clicking the user displays its [User Details page](https://help.zscaler.com/zdx/evaluating-user-details).
    - **Operating System**: The OS of the device.
    - **Browser**: The web browser to access the page.
    - **Views**: The number of views of the resource page.
    - **Average Duration**: The average duration the user spent on the resource page.
- **Core Web Vitals Metrics**: Computed metrics based on the user experience accessing the page that include: To learn more about Core Web Vitals, refer to [Web.dev - Core Web Vitals](https://web.dev/explore/learn-core-web-vitals).
  - First Contentful Paint
  - Total Blocking Time
  - Total Long Tasks: Longest Long Task Duration
  - Total Long Task Duration
  - Users by Largest Contentful Paint (LCP)
  - Users by Interaction to Next Paint (INP)
  - Users by Cumulative Layout Shift (CLS)
- **Core Web Vitals**: Switch between **Largest Contentful Paint**, **Interaction to Next Paint**, and **Cumulative Layout Shift** views. See image.

See image.

## Errors

The Errors tab accounts how many errors the application's page has encountered by displaying the following information:

- **Total Errors**: The total number, percentage change, and a graphical representation of errors.
- **Error Rate**: The error rate and a graphical representation of the error rate.
- **Errors and Error Rate**: A graph displaying the total errors, error rate, and average error rate over time.
- **Javascript Errors**or**Network Errors**: Switch between JavaScript Errors and Network Errors views to view the number of JavaScript errors over time or the number of network errors over time.

See image.

## Network Probe Performance

The Network Probe Performance tab provides details on the probe that was configured for the network when configuring an application. The Network Probe Performance tab provides the following information:

- **Application Probe Status**: View the overall probe status of the configured application and its URL. Each metric includes the minimum, average, and maximum for the following: If the network probe is configured for different URLs, then you can opt to switch between views.
  - End-to-End
  - Packet Loss
  - Total Hops
  - Packet Count
- **Synthetic Probe Score and Latency Trends**: Compare the Synthetic Probe trend with the Latency trend to understand when the drop in digital experience occurs. Switch between the **Latency** and **Packet Loss** views.
- **Impacted Departments**: Up to the 5 most impacted departments and their Synthetic Probe Score.
- **Impacted Regions**: Up to the 5 most impacted regions and their Synthetic Probe Score.
- **Impacted Locations**: Up to the 5 most impacted locations and their Synthetic Probe Score.

See image.

[Image: View the application's Summary tab]

[Image: View the Web Probe Performance tab]

[Image: View the Real-User Performance tab]

[Image: View the Errors tab]

[Image: View the Network Probe Performance tab]

[Image: View the Page Analysis of the web application]

[Image: View the Resource Analysis of the web application]

[Image: View the waterfall analysis of the user]

[Image: Switch between the Core Web Vital views]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/evaluating-cloud-path","lastmod":"2026-06-11T13:43Z","nid":"1450291"} -->
## Evaluating the Cloud Path

- Source: https://help.zscaler.com/zdx/evaluating-cloud-path
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Evaluating the Cloud Path
- Last modified: 2026-06-11T13:43Z
- Summary: Information about monitoring the Cloud Path Hop View and Command Line View.

The Cloud Path provides a path visualization of metrics between hop points of traffic. It can capture a direct traffic path, as in the case of Zscaler Client Connector to Egress to destination, or tunneling through a Public Service Edge for Internet & SaaS (from Zscaler Client Connector to Egress to Public Service Edge to destination). The Cloud Path has the following views:

- A graph that shows latency or packet lossfor a particular time period.
- A Hop View and Command Line View that detail the path from the user's device to the application or destination.

For Cloud Path with IPv6, ZDX provides visibility into the hop-by-hop network performance for destinations reachable over IPv6. When a test target resolves to an IPv6 address, ZDX traces the end-to-end path, measuring latency, packet loss, and jitter across each IPv6 hop.

## Monitoring Latency and Packet Loss

Select either **Latency**or **Packet Loss** in the drop-down menu to view the graph. Click a point in the graph to see the time period and either the latency in milliseconds or the packet loss percentage. In the Latency graph, metrics over the different legs display in the tooltip, and you can also select the metrics from the checkbox options below the graph. If an error is present, that is also displayed in the tooltip. Select a point to update the path tracked from the device to the application.

[Image: View Latency over the legs in the Cloud Path]

## Monitoring the Hop View and Command Line View

The Hop View and Command Line View each reflect the path from the user's device to the application.

### Hop View

Hover over different sections of the path to see more information. You can also use the arrows on either side to expand this view. Depending on the part of the path you hover over, details such as device information, the service provider, latency details, packet loss, hop count, etc. are displayed.

See image.

- Maximum Latency
- Tunnels
- VPN Concentrator
- External Proxy
- Cloud Path Icons
- Device Location
- Public Service Edges
- Cloud Path Protocols
- SIPA on Cloud Path

Differential latency is shown over the different legs of the Cloud Path, and the leg with the highest latency displays in orange. The latency shown on top of the Public Service Edge represents the network latency between the Public Service Edge and the first router in the Zscaler Data Center. This might indicate Public Service Edge congestion. Click the magnifying glass icon to expand the Hop View and to see the individual values. To learn more about Service Edges, see [Service Edges](https://help.zscaler.com/zia/traffic-forwarding/service-edges) for Internet & SaaS and [Understanding Service Edges](https://help.zscaler.com/zpa/understanding-service-edges) for Private Access.

See image.

You can see the flow of traffic through the applicable tunnels as per the configured policies. For GRE tunnels, the Hop View displays underlay hops in the routing path to provide more accurate metrics for latency and packet loss. In cases where the Private Service Edge for Internet & SaaS is on a private network (such as a VPN) with a private IP address, the Hop View provides a path from the client to the Private Service Edge. To learn more about tunnels in the Cloud Path, see [About Tunnel Information in the Cloud Path](https://help.zscaler.com/zdx/about-tunnel-information-cloud-path).

See image.

When Citrix is the network interface for TCP or UDP probes, ZDX displays the path to the VPN Concentrator. ZDX also supports the topology where traffic from the VPN Concentrator flows to the Public Service Edge via a GRE/IPSec tunnel.

See image.

When an External Proxy is detected in the path, the Cloud Path supports several proxy topologies within the Hop View. Two examples are represented here.

The following scenario shows a third-party proxy forwarding traffic to the destination:

[Image: External proxy forwarding traffic in direct use case]

The following scenario shows the External Proxy forwarding traffic to the Private Service Edge through a GRE tunnel:

[Image: External proxy forwarding traffic to Private Service Edge for Internet & SaaS]

If there are any errors, icons are displayed on the Cloud Path. Click the icon to display the error message. To learn more, see [Cloud Path Errors](https://help.zscaler.com/zdx/cloud-path-errors).

See image.

Network interface types that are neither Wi-Fi nor Ethernet are indicated with one of the following icons above the client icon ([Image: Client icon in hop view]) in the Hop View:

- Cellular (SIM card) ([Image: Cellular network type icon in hop view]) network type
- Bluetooth ([Image: Bluetooth network type icon in hop view]) network type
- USB ([Image: USB network type icon in hop view]) network type

If the network type cannot be detected, the icon for an unknown network type ([Image: Unknown network type icon in hop view]) is displayed.

Geolocation information and the Zscaler location are displayed for the device, and geolocation information is also displayed for the Zscaler node in both the Hop View and the Command Line View.

See image.

A device location icon confirms that a device's location was determined by latitude and longitude coordinates. If no icon is displayed, the device was located by its IP address.

See image.

When accessing an internet application, you can see the path from Zscaler Client Connector to the application, and if there is a Public Service Edge for Internet & SaaS or Public Service Edge for Private Access in the path, that is also displayed. If a private application is accessed, you can view the path from Zscaler Client Connector to the Public Service Edge for Private Access or Private Service Edge for Private Access. In case you have configured a path for traffic through the Public Service Edge for Internet & SaaS, both the Public Service Edge for Internet & SaaS and the Public Service Edge for Private Access or the Private Service Edge for Private Access are seen in the Cloud Path. This is displayed in the Hop View and the Command Line View. To learn more about Service Edges, see [About Public Service Edges for Internet & SaaS](https://help.zscaler.com/zia/about-public-service-edges-internet-saas), and [Understanding Service Edges](https://help.zscaler.com/zpa/understanding-service-edges) for Private Access.

The following image shows both the Public Service Edge for Internet & SaaS and Public Service Edge for Private Access in the Hop view. The Public Service Edge for Private Access sends packets to the App Connector in the path to the application. To learn more, see [About App Connectors](https://help.zscaler.com/zpa/about-connectors), [About Public Service Edges for Internet & SaaS](https://help.zscaler.com/zia/about-public-service-edges-internet-saas), and [Understanding Service Edges for Private Access](https://help.zscaler.com/zpa/understanding-service-edges).

See image.

Both views display a 4-digit number for the Public Service Edge for Internet & SaaS or Public Service Edge for Private Access. This internal hash value helps Zscaler Support quickly locate and troubleshoot any issues.

See image.

Both views display the protocol used over the different legs of the Cloud Path. If ICMP, TCP, or UDP was selected, it is displayed in the Cloud Path. If Adaptive mode was selected, the protocol used for the different legs of the Cloud Path is displayed.

Adaptive mode is not supported for internal applications through Private Access. Only ICMP, TCP, and UDP are supported.

See image.

For Adaptive probes, legs that are grouped according to tunnel type also show their corresponding protocol. The following image shows the grouping of Zscaler Tunnel (Z-Tunnel) 2.0 and ICMP protocol within the Hop View:

See image.

To learn how Adaptive mode works, see [Using Adaptive Mode](https://help.zscaler.com/zdx/using-adaptive-mode).

To learn more about configuring probes for the Cloud Path, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe).

When the traffic is configured for routing to an internal or external destination through Internet & SaaS Source IP Anchoring (SIPA), the Cloud Path detects any network issues (latencies, packet loss, jitter) and displays a path with full end-to-end visibility.

See image.

This feature is in Limited Availability. To enable this feature, contact Zscaler Support.

### Command Line View

Click the **Command Line View** tab to view further details about the Cloud Path, including the Hop Direction (probe direction, from the client to your egress IP ↓, from Public Service Edge or Private Service Edge to your egress IP ↑, and from Service Edges to destination ↓), Region and Geolocation, Packet Loss (%) and Packets Failed, as well as Latency metrics. If there are any errors, they are indicated by an icon next to the IP address. Click the icon to display the error message. To learn more, see [Cloud Path Errors](https://help.zscaler.com/zdx/cloud-path-errors).

See image.

[Image: View Maximum Zscaler Latency in the Cloud Path]

[Image: Example of reverse traceroute for GRE tunnels]

[Image: VPN Concentrator shown in Hop View]

[Image: Cloud Path Errors on the User Page]

[Image: Cloud Path Errors in Command Line View]

[Image: Four-digit number for Public Service Edge for Internet & SaaS]

[Image: Viewing Private Applications in the Cloud Path]

[Image: Device profile in Cloud Path]

[Image: Geo Information in the Cloud Path]

[Image: Example of device location icon in the Hop View]

[Image: Choose protocol to view in the graph using the drop-down menu]

[Image: Tunnel and protocol for Adaptive probe]

[Image: View SIPA on the Cloud Path]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/evaluating-diagnostics-session-information","lastmod":"2024-05-09T17:08Z","nid":"1443581"} -->
## Evaluating Diagnostics Session Information

- Source: https://help.zscaler.com/zdx/evaluating-diagnostics-session-information
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Diagnostics > Evaluating Diagnostics Session Information
- Last modified: 2024-05-09T17:08Z
- Summary: Provides in-depth information about Diagnostics sessions for a user's evaluation.

Diagnostics sessions for your organization are displayed in the **In Progress** and the **History** tables. Sessions in both tables display the following information:

- **Expand**: This describes the Packet Capture (PCAP) information entered during configuration for the selected session.
- **Name**: The name entered for the session during configuration.
- **Session Type**: The type of session selected during configuration.
- **User**: The name and email address of the user are listed.
- **Device**: The device analyzed for the session.
- **Created Time**: The time the session was created by the ZDX admin.
- **Start Time**: The time that Zscaler Client Connector accepted the request and started collecting data.
- **Status**: The current session status differs depending on the table in which the session is listed. To learn more, see [Understanding the Diagnostics Session Status](https://help.zscaler.com/zdx/understanding-diagnostics-session-status).
- **Application**: The application being monitored for the session.
- **Created By**: The name and email address of the admin who created the session.

The following information is seen only in their respective tables:

- **Duration (minutes)**: How long the Diagnostics Session is, in minutes, in the **In Progress** table.
- **Probe Settings**: The type of probes used to monitor the Diagnostics Session in the **History** table.
- **End Time**: The time the session ended in the **History** table.

By default, the lists are sorted by the Start Time, displaying the session with the latest Start Time first. You can also sort the sessions by using the Sort arrows next to the column names.

[Image: Diagnostics]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/evaluating-individual-alert-details","lastmod":"2026-07-10T09:57Z","nid":"1414451"} -->
## Evaluating Individual Alert Details

- Source: https://help.zscaler.com/zdx/evaluating-individual-alert-details
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Evaluating Individual Alert Details
- Last modified: 2026-07-10T09:57Z
- Summary: Information on individual alert details where you can view the impacted devices and their respective departments and locations.

Individual alert details provide in-depth information about the impacted devices and their respective departments and locations.

To view an individual alert's details on the Alerts page, click the **Rule Name** or the **View** icon of a selected alert.

See image.

The alert details can differ depending on the type of monitoring you selected for the alert.

See image.

## End User Monitoring

If an alert was configured to monitor end users, the Alert Details page shows the following:

- **Impacted Departments**: The number of devices by department and the total number of impacted devices. Click **View All** to view all the impacted departments in a dialog window. See image.
- **Impacted Geolocations**: The number of devices by geolocation and the total number of impacted geolocations. Click **View All** to view all the impacted geolocations in a dialog window. See image.
- **Impacted Zscaler Locations**: The number of devices by location and the total number of impacted Zscaler locations. Click **View All** to view all impacted locations in a dialog window. See image.
- **Expression Triggers**: The alert rule's expression triggers as well as the average and maximum values for the time period.

The maximum number of devices displayed on the Alert Details page is 128 devices. In the View All dialog window, the maximum number of devices displayed is 6,000 devices.

The Impacted Geolocations map displays the location of the impacted user devices. The map has the following functionality:

| **Map Interaction** | **Results** |
| --- | --- |
| Click Filter Unknown Locations | The global filter and all widgets are updated with this selection. |
| Hover over a pin | View a tooltip with the geolocation and number of users. |
| Click a pin | The global filter and all widgets are updated with this selection. |
| Click the tooltip | The tooltip remains open. |
| Double-click a pin | Zoom in to the map to view details. |
| Zoom in/out on a pin | Zoom in to the map to view details. |
| Drag the map | Data is displayed as per the map boundary. No additional interaction or data is loaded. |
| Draw a fence around a pin and then click Filter Selection. | The global filter and all widgets are updated with this selection. To learn more, see [Drawing a Fence](https://help.zscaler.com/zdx/about-applications-dashboard#draw). |

The Impacted User Devices table displays the Device name, User ID, Department, Zscaler location, Geolocation, and the ZDX Score of the device.

The ZDX Score includes an auto-baseline score based on historical data and a ZDX Score Drop detection based on threshold sensitivity.

The dotted reference line is based on the criteria set from configuring an alert rule.

Click the **Expand** or **Collapse** icon to the left of the device name to view details of that individual device.

See image.

### Network Intelligence

If you are monitoring end users, and you configured an alert rule with Network Intelligence as a rule type, then you see the following alert details:

- **Impacted Geolocations**: The number of devices by geolocation and the total number of impacted geolocations. Click **View All** to view all the impacted geolocations in a dialog window.
- **Associated Zscaler Data Centers**: The number of Zscaler data centers by geolocation and the total number of impacted Zscaler data centers. Click **View All** to view all impacted Zscaler data centers in a dialog window.
- **Expression Triggers**: The alert rule's expression triggers.
- **Impacted Geolocations**: This map has similar functionality to other rule types. For Network Intelligence alerts, the impacted geolocations that are anomalies are highlighted as red circles.
- **Impacted Users**: A table that lists impacted geolocations and displays the following information:
  - **Geolocation**: The impacted geolocation.
  - **Users**: The number of impacted users at the geolocation.
  - **Zero Trust Exchange**: The name of the impacted Zero Trust Exchange.
  - **Impacted ISPs**: The impacted Internet Service Provider (ISP) or the impacted ISP links.

See image.

## Hosted Monitoring

If an alert was configured using a hosted probe, the Alert Details page shows the following:

- Alert Details include the following information:
  - **Status**: The status of the alert. To learn more, see [Understanding the Alert Status](https://help.zscaler.com/zdx/understanding-alert-status).
  - **Severity**: The severity level of the event. Red indicates High severity, orange is Medium severity, and green indicates Low severity.
  - **Monitoring Type**: The type of monitoring for this rule. End User indicates the alert rule was created by the user. Hosted indicates the alert rule was created for Zscaler Hosted Monitoring.
  - **Probe**: The name of the probe.
  - **Criteria**: The selected criteria for the alert rule.
  - **Started On**: The date and time this alert was triggered.
  - **Ended On**: The date and time this alert ended.
- Depending on the selected criteria for the alert, you can view the associated details such as latency, number of hops, packet loss, or DNS Time from the selected Zscaler Hosted probe. To learn more, see [Understanding Zscaler Hosted Monitoring](https://help.zscaler.com/zdx/understanding-zscaler-hosted-monitoring).

See image.

## Dynamic Alert

Dynamic alerting provides the ability to auto-baseline by referring to historical data to create the current baselines and detect ZDX Score Drops based on your threshold sensitivity to be notified on critical changes. To configure a dynamic alert, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).

After an alert rule is created and completed with the ZDX Score Drops as the criteria, you can see the ZDX Score Drops of the impacted device.

- The dotted line indicates the reference line for threshold sensitivity based on your criteria.
- The gradient line indicates the ZDX Score over time.
- The shaded region indicates when the ZDX Score Drops are below the reference line.

See image.

[Image: Select an alert to view individual alert details]

[Image: View a network intelligence alert to analyze details about users and ISPs]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/evaluating-user-details","lastmod":"2026-08-06T13:56Z","nid":"1391316"} -->
## Evaluating User Details

- Source: https://help.zscaler.com/zdx/evaluating-user-details
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Evaluating User Details
- Last modified: 2026-08-06T13:56Z
- Summary: Information about the user details page, accessed from the Users Dashboard.

To better understand the digital experience for a user, view the user details page for user and device metrics.

To access user details, choose one of the following options:

- From the User Overview page, select one or more applications from the Applications filter drop-down menu and click **Apply.**Click the table cell for User or Devices to view a page with details about the user's digital experience.
- Search for a user in the Search panel and view their page. To learn more, see [Using Search in the ZDX Admin Portal](https://help.zscaler.com/zdx/using-user-search-zdx-admin-portal).

Any filters used on the User Overview page remain in place after selecting a user or the user's device. Click **Reset**to adjust or remove the filters.

See image.

You can access the following user details:

- Viewing Device Details
- Viewing Applications
- Viewing ZDX Score Over Time
- Viewing Performances

Alternatively, if you would like to troubleshoot device issues or share user details, you can:

- [Start a Diagnostics session](https://help.zscaler.com/zdx/starting-new-diagnostics-session)
- [Run Remediation](https://help.zscaler.com/zdx/viewing-and-managing-remediation-jobs#start)
- [Share a ZDX Snapshot](https://help.zscaler.com/zdx/sharing-zdx-snapshots)

This section displays the devices in use and specific device information, such as the OS type and version, CPU, Memory, Private IP, Public IP, etc. If there is more than one device, the first device is automatically selected. You can also use the scroll bar on the left to select and view device information when multiple devices are in use. The device remains selected even with time range and other filter changes. Devices in this section are displayed depending on the time selected and factors such as device network issues, offline/sleep status, etc. The data on the remainder of the page also populates depending on the device selected.

The Tunnel Type that displays depends on the device profile. The available tunnel types are:

- Tunnel 1.0 or TWLP
- Tunnel 2.0 in DTLS Mode
- Tunnel 2.0 in TLS Mode
- Tunnel Unknown

If a Tunnel Type is Unknown, it means ZDX could not detect it.

[Image: View device details in the user details page]

The User Device Information window displays additional device information organized under the Hardware, Network, or Software tabs. Click the different tabs to view additional information. For example, on the Software tab, you can view the ZDX and Zscaler Client Connector versions for the device.

[Image: View Additional User Device Details]

All the applications selected in the Applications filter are shown. You can see the period of time that is covered above the applications.

Each application has a ZDX Score. If the application has [Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring) enabled, then the Real User score is displayed. Compare the different applications to understand how they are impacting a user's particular ZDX Score. If there are more than three applications, scroll left or right to see more applications. By default, the application with the lowest ZDX Score is selected.

[Image: Select an application on user details page]

Selecting an application updates the information that appears in the page. By changing the application, the subsequent sections update accordingly. Each of these allow you to further understand the application and its impact to the user for the selected point in time. The metric information is based on what was chosen when setting up a probe. To learn more, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe).

The ZDX Score Over Time graph shows how the ZDX Score trends over the selected time period for this user by device. If a device is not selected, this graph is not populated.

In the ZDX Score Over Time graph on the User page, the Smooth ZDX Score displays by default. The Smooth ZDX Score uses historical data to reduce short-term variations in the ZDX Score and provides a more representative metric with reduced variability. Select the checkbox option under the graph to view the ZDX Score. This selection remains even when changing filters and applications.

All scores track as lines across the graph. Scores fall into three categories:

- **Good**: The score is above an acceptable threshold and ranges from 66–100. The color for this range is green.
- **Okay**: The score is acceptable and ranges from 34–65. The color for this range is amber.
- **Poor**: The score is below an acceptable threshold and ranges from 0–33. The color for this range is red.

You can select a point on the graph to see the exact date, time, and Smooth ZDX Score for that period in a tooltip. If the ZDX Score option is selected for the graph, the tooltip displays the ZDX Score at that time as well. If there is an **Error** icon ([Image: Error icon on the ZDX Score Over Time graph]) at the point, you can view more information about the cause and the related application. Click the **Details**icon ([Image: Details icon in the ZDX Score Over Time graph]) next to the score in the tooltip to go to the Applications page for further details about the application.

See image.

If [Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring) is enabled for the selected application, then the Real-User Score trend is displayed alongside the ZDX Score trend.

See image.

A **Zoom In** icon (+) appears as you hover over the ZDX Score Over Time graph. Use this icon to select the time period to zoom in for greater detail. This shows the graph over that smaller time period. The Web Probe Metrics, Device Health, and Cloud Path also reflect the same period of time. The time limit to zoom in is 5 minutes. Click **Zoom Out** to view the original graphs.

For in-depth details at a single point of time or a comparison between different times, consider the following actions:

- Analyzing the ZDX Score: Single Date and Time
- Analyzing the ZDX Score: Time Range
- Comparing ZDX Scores

You can also enable and display incident data in the ZDX Score Over Time graph that's reflected on the Incidents Dashboard, based on the timeline of a particular incident. To view incident data:

1. Click **Show Incidents**.
2. Hover over an icon that represents one of the incident types: Wi-Fi, Last Mile ISP, Zscaler, or Application. The names of the incident type and epicenter are displayed, along with the number of impacted users.
3. Click **View Incident** to see the incident's details and key metrics.

See image.

To learn more about incident types, see [Monitoring the Incidents Dashboard](https://help.zscaler.com/zdx/monitoring-incidents-dashboard).

For Poor scores that range from 0–33, the ZDX Score Over Time graph includes a feature to help identify reasons for the low score. The application tile with the lowest Poor score is preselected, and ZDX runs root cause analysis automatically on the most recent Poor score. As a result of the analysis, potential factors are provided that might have contributed to the score. If you select a different application tile, ZDX analyzes its most recent Poor score within your selected timeline.

Automated analysis for Poor ZDX Scores is not applicable to ZDX Call Quality applications.

[Image: Automated analysis for the lowest Poor ZDX Score]

If you select a different data point on the graph for a Poor score at a specific date and time, click **Analyze Score** within the tooltip to, again, trigger potential factors that might have contributed to the score.

[Image: Analyze Score button in tooltip of ZDX Score Over Time graph with corresponding table]

The analysis table provides the following information for a particular date and time in the graph:

- **Factor**: Shows a probable cause for the low score.
- **Explanation**: Addresses why the corresponding factor might be the issue.
- **Confidence Level**: Quantifies the assumed accuracy of the analysis, based on probes with similar issues.
- **Provide Feedback**: Lets Zscaler know the accuracy level of the analysis.
  - Click the **thumbs-up** icon ([Image: Thumbs-up icon]) if the analysis for the low score was helpful. A success message confirms Zscaler has received your feedback.
  - Click the **thumbs-down** icon ([Image: Thumbs-down icon]) if you believe the analysis was inaccurate and the low score is related to a different issue. [Image: Pop-up to provide feedback when analysis results are inaccurate]
    1. Select one of the potential factors from the drop-down menu. If you believe a different factor caused the issue, select **Other**and specify the factor.
    2. Share any other details about the issue.
    3. Click **Submit**.

As an additional method of analyzing Poor ZDX Scores beyond a particular date and time, the ZDX Score Over Time graph also includes an option to analyze scores within a time range. After you select a point on the graph for a Poor score at a specific date and time, complete the following steps:

1. Move the slider to the right on a second point in the graph. This point represents the end of your time range.
2. Click **Analyze Range**to display potential factors that might have contributed to the low scores within your specified time range. [Image: Analyze Range button with corresponding table]

To learn more about the details in the analysis table, see Analyzing the ZDX Score: Single Date and Time.

You can compare ZDX Scores for an application to understand why they might vary at different points in time. A score comparison can highlight why a current score might be considerably different from a previous score. The feature utilizes web, device, and Cloud Path metrics to help determine differences in scoring.

Web metrics are not used or provided when comparing scores for a Network application.

After selecting a point within the ZDX Score Over Time graph to start your comparison, do one of the following from the Compare todrop-down menu:

- Compare the ZDX Score of your selected point to a previous ZDX Score.
- Compare the ZDX Score of your selected point to a future ZDX Score, up to the current date and time.

[Image: Drop-down options to compare ZDX scores]

To compare your selected point to a previous ZDX Score, use a provided timeline from the drop-down menu:

1. Click **Compare to**.
2. Select **Last known good score** or one of the time range options. The option for **Last known good score** searches the score history for the last available score between 66 and 100. The time range options show the score from the same time on the previous day, 2 days, or 7 days. The **Compare ZDX Scores** page appears. See image. The page provides the following details and metrics:
  - **Analyzed Point**and **Comparison Point**: The **Analyzed Point** represents the initial date and time you selected for the application. The **Comparison Point** represents the date and time to which you're comparing the **Analyzed Point**. Use the **Comparison Point** time range filter if you need to specify a different date and time for comparison.
  - **ZDX Score**: Shows the side-by-side ZDX Score graphs for each selected point. To learn more about ZDX Scores, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score). See image.
  - **Key Differences**: Shows the differences in metric values for each selected point. The higher value for each metric is highlighted.
  - **Cloud Path**: Shows the hop views for each selected point. Click **View Detailed Cloud Path**to access either a larger **Hop View** or the **Command Line View**. See image.
  - **Hop View**: Shows a comparison and any differences in the Cloud Paths from the user's device to the application. To learn more, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path). See image.
  - **Command Line View**: Shows a text-based comparison of the Cloud Paths. To learn more, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path). See image.
  - **Cloud Path Metrics**: Shows the Cloud Path metric values for each selected point. The higher value for each metric is highlighted.
  - **Web Metrics**: Shows the web metric values for each selected point. The higher value for each metric is highlighted. Web metrics are not provided for Network applications.
  - **Device Metrics**: Shows the device metric values for each selected point. See image.

To compare your selected point to either a previous ZDX Score at a custom date and time or to a future ZDX Score (up to the current date and time), use the **Custom time** option:

1. Click **Compare to**.
2. Select **Custom time**from the drop-down menu. The **Compare ZDX Scores** page appears.
3. Select your **Comparison Point** in the ZDX Score Over Time graph. Use the time range filter if you need to specify a different time range not shown in the graph. See image.
4. Click **Compare** to show ZDX Score details for both the **Analyzed Point** and the **Comparison Point**. See image.

There are 4 different metrics to evaluate performance details of the device with access to the selected application:

- Web Probe Performance
- Real-User Performance
- Network Performance
- Device Performance

See image.

To learn more about performance data, see [About Probes](https://help.zscaler.com/zdx/about-probes), [Understanding Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring), [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path), [Monitoring the Device Health Dashboard](https://help.zscaler.com/zdx/monitoring-device-health-dashboard), and [Viewing Device Events Reports](https://help.zscaler.com/zdx/viewing-device-events-reports).

If the selected application uses a Web probe, then the Web Probe Metrics section shows the application being monitored and provides the following metrics for the application:

- Page Fetch Time: The time duration for a selected application to transfer or load a page for a user. This data is useful to know as it is an indication of a user's digital experience with an application. It encompasses the following:
  - PAC Parsing Time: The time duration to process a Proxy Auto-Configuration (PAC) file.
  - DNS Time: The time duration taken during a DNS query to translate a domain name into a corresponding IP address.
  - TCP Connection Time: The time duration to establish a TCP connection between the browser and a server.
  - SSL Handshake Time: The time duration for a client and server to establish a secure connection.
  - Server Processing Time: The time duration from when the server processes a request to when it begins to send a response back. This metric specifically focuses on server-side processing aspects by measuring efficiency of the server after it generates a response.
- Zscaler Time to First Byte: The time duration from when a user's browser sends a request to an application until it receives the very first byte of data from the server. Zscaler TTFB is a great indicator of a website's initial loading speed and server responsiveness. This includes:
  - Redirect Time
  - TCP Connection Time
  - SSL Handshake Time
  - Server Processing Time
  - Reponse Transmission Time

Web probe metrics are not provided for Network applications. To learn more, see [Monitoring the Performance Dashboard](https://help.zscaler.com/zdx/monitoring-performance-dashboard).

Page Fetch Time reflects how long it takes the selected application to load a page for the user. The Page Fetch Time graph includes a baseline for any given region with at least one active device. For predefined applications, the baseline represents an average score based on device metrics across all organizations. For custom applications, the baseline represents an average score based on device metrics from a given organization.

Users with an [Advanced Plus subscription](https://help.zscaler.com/unified/ranges-limitations#digital-experience) can view a tooltip with DNS, SSL, TCP, Server Processing Time (SPT), and TTLB (Time to Last Byte) metrics that comprise the total Page Fetch Time. Additional graphs are displayed for PAC parsing time, DNS time, TCP connect time, HTTP connect time, and SSL time. Where applicable, individual server redirects are included for each graph to reflect one of the following:

- Client to Public Service Edge
- Public Service Edge to the application
- Client to the application

See image.

If more than one redirect is displayed, the last redirect indicates the time to the final destination.

Baseline metrics are calculated daily for each application on a rolling timeline of the previous 7 days. The baseline value is measured in milliseconds.

[Image: Example graph for Web Probe Metrics with baseline]

Click **View Details** within the tooltip to see the Total Page Fetch Time, as well as the metrics for Total Bytes Transferred and Total Throughput. Hover over the bar graph to view the individual time metrics for each server redirect.

See image.

All metrics are preselected, but you can click the menu and deselect metrics that are not relevant to your understanding of the application. Click a point on a graph to see the value or percentage for that exact date and time. When you click a point on one graph, all graphs show their values for that same point.

Whenever traffic is steered through Source IP Anchoring (SIPA), **via SIPA** is displayed and the corresponding traffic within the timeline is highlighted in the graphs. Traffic forwarding through SIPA enables you to control the source IP address of the traffic to the final destination without bypassing the Zscaler security service. To learn more, see [Understanding Source IP Anchoring](https://help.zscaler.com/zia/understanding-source-ip-anchoring).

If SIPA traffic is intermittent within a particular timeline, only the time windows with SIPA Web probes are highlighted, as shown in the following example:

[Image: SIPA rendered in Web Probe Metrics]

At times, you might see icons that indicate warnings, errors, or Private Access rate limiting. For example, the following graph shows the link that represents Private Access Web probe rate limiting. Click the link to display related metrics information.

[Image: Example icon for rate limiting in Web Probe Metrics]

To learn more about possible errors associated with the icons, see [Web Probe Errors](https://help.zscaler.com/zdx/web-probe-errors).

The Device Performance section is useful in viewing device health metrics (i.e., CPU usage, memory) and device events that impacted the user device's overall performance. There are two metrics displayed:

- Device Health
- User Device Events

The Real-User Performance captures the user device's interactions with the selected web application to compile a detailed view of the application's digital experience. The Real-User Performance metrics capture the following metrics:

- Page Load Time: The average page load time for the application.
- Page Load Time and Blocking Time Trend: A comparison between the Page Load Time and Blocking Time to evaluate if there were any preventions to input responsiveness.
- Errors Over Time: The total number of errors (e.g., JavaScript or Network errors) that have occurred when the user device is accessing the web application.

See image.

To learn more, see [Understanding Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring).

[Image: View a variety of performance metrics related to the selected application and user device]

Depending on the device the user was on at the time, different information is available about that device. The Network and Disk options are preselected by default, and you can choose the elements you want to review. This selection remains even when navigating to a different user page or viewing a Diagnostics session.

[Image: View Device Health metrics in the user details page]

Select a place on a graph to see the value or percentage for that exact date and time. By selecting a point on one graph, all graphs show their values for that same point. For users with an Advanced Plus subscription, the most impacted processes are also displayed for applicable CPU, Memory, Disk I/O, and Network I/O, regardless of which graph is selected. A maximum of 5 processes is shown for each element within a given 5-minute interval. Click the **Expand**icon or **Collapse**icon to the right of the section label to expand or collapse this section.

See image.

If the selected application uses a Cloud Path probe, then the Cloud Path provides a path visualization of metrics between hop points of traffic. It can capture a direct traffic path, as in the case of Zscaler Client Connector to Egress to destination, or tunneling through a Public Service Edge for Internet & SaaS (from Zscaler Client Connector to Egress to Public Service Edge to destination). To learn more, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path).

User Device Events tracks the events for a device over a selected period of time. Each event is categorized in terms of severity, as represented at the bottom of the timeline: Informational, Warning, Error, and Critical. To interpret events based on a single severity level, you can filter these events by clicking directly on the severity labels. The following table provides detailed descriptions of each event type.

| Event Name | Description |
| --- | --- |
| Informational | Events that display non-critical details, like a notification. |
| Warning | Events that warn of potential issues, indicating a component or application is not in an ideal state and could lead to a critical error. |
| Error | Events that indicate problems without requiring immediate attention. |
| Critical | Events that require immediate attention, often at the system-wide level, indicate failures or unresponsive applications. |

Additionally, click the event to view detailed information about each event in a side panel.

[Image: User Device Events]

The side panel reveals a granular breakdown that includes timestamps, category, and attribute changes related to the event.

[Image: User Device Events]

The Category drop-down menu provides the option to filter the event categories for Zscaler, Hardware, Software, and Network:

[Image: Screen shows the category drop-down menu]

To learn more, see [Viewing Device Events Reports](https://help.zscaler.com/zdx/viewing-device-events-reports).

[Image: Select applications from filter to view user details]

[Image: Graph showing Smooth ZDX Score]

[Image: Compare ZDX Scores page that shows last good score]

[Image: Comparing ZDX points and scores]

[Image: Compare key differences and cloud paths]

[Image: Compare Hop Views]

[Image: Compare Command Line Views]

[Image: Compare cloud path, web, and device metrics]

[Image: Custom Comparison Point]

[Image: Compare button for custom compare]

[Image: Device Health Process Stats]

[Image: Show incidents in ZDX Score Over Time graph]

[Image: View Web Probe Metrics]

[Image: View the Page Fetch Time breakdown]

[Image: View the Real-User Performance metrics]

[Image: View both ZDX Score and Real-User Score Trends Over Time]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/first-time-provisioning-zdx-admins","lastmod":"2024-07-01T09:38Z","nid":"1358786"} -->
## First Time Provisioning for ZDX Admins 

- Source: https://help.zscaler.com/zdx/first-time-provisioning-zdx-admins
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > First Time Provisioning for ZDX Admins 
- Last modified: 2024-07-01T09:38Z
- Summary: How to configure initial provisioning for your organization to use Zscaler Digital Experience (ZDX).

html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd"

To onboard ZDX for your organization, first reach out to Zscaler Support. Support will set up a default admin role and send an email requesting the password be reset for that role. After you have reset that password, you can act as the first default admin to log in and begin creating other admins and roles for ZDX.

You will be able to see all functions of the ZDX Admin Portal due to the default admin role. This default admin role is necessary to onboard ZDX for your organization, but it is not recommended for day-to-day use. Zscaler recommends creating your own admin user for yourself. This should be used as your primary user details for better auditing and tracking in ZDX. The default admin user will still exist, but is not recommended for further use.

To complete first time provisioning and establish your own admin user details:

1. Go to **Administration** > **Administration Management**to configure other admins and admin roles. To learn more, see [About ZDX Role-Based Administration](https://help.zscaler.com/zdx/about-zdx-role-based-administration).
2. Click **Add New ZDX Role**. This role is to pair with the default admin created initially. Even though the default admin settings cannot be changed, a role type is still needed to allow the creation of other admins and role types. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
3. Edit the new role to have the highest level of permissions:
  - **Dashboard**: View Only
  - **Configuration**: Full
  - **Admin Management**: Full
  - **User & Device Names**: Visible
  - **Locations**: View Only
  - **User Management**: View Only
4. **Save** your changes.
5. Click **Add New ZDX Admin**. This will be the admin user you will use as your own to manage the admin settings for your organization. To learn more, see [Adding ZDX Admins](https://help.zscaler.com/zdx/adding-zdx-admins). See image.
6. Edit your admin user settings and set your role to the one you just created that has the highest levels of permissions.
7. **Save** and [activate your changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

Now that you've created your personal admin user, use these credentials to manage role-based administration for ZDX in your organization.

[Image: ZDX Admin Management page showing the button to Add a ZDX Admin]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-admin-groups","lastmod":"2025-04-29T07:06Z","nid":"1507751"} -->
## Managing Admin Groups

- Source: https://help.zscaler.com/zdx/managing-admin-groups
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Managing Admin Groups
- Last modified: 2025-04-29T07:06Z
- Summary: Configuration steps on how to manage admin groups in the ZDX Admin Portal.

If you have the Full permission level for Administrator Management and you have SCIM Auto Provisioning enabled on the Administrator Management page, you can manage (add, edit, or delete) Admin Groups. If there is an admin group outside your scope or role, you can view an admin group. You can assign an admin scope or role to an admin group to organize your admins into groups and provide limitations and access to certain features of the ZDX Admin Portal.

An admin group consists of the following:

- Group Selection: A SCIM group based on what you have uploaded to the Administrator Management page. To learn more, see [Configuring SAML for ZDX Admins](https://help.zscaler.com/zdx/configuring-saml-zdx-admins).
- Role: A ZDX role is associated with the admin group to provide limitations and access to the ZDX Admin Portal. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- Scope: An admin scope is associated with the admin group to provide limitations and access to granular details of your organization's users. To learn more, see [Understanding the Admin Scope](https://help.zscaler.com/zdx/understanding-admin-scope).

On the Admin Groups page (Administration > Administration Controls > Administrator Management > Admin Groups), you can perform the following actions:

- Add an Admin Group
- Edit an Admin Group
- Delete an Admin Group
- View an Admin Group

Depending on your scope and role, you might be unable to configure an admin group and instead only view an admin group.

To add an admin group:

1. Click **Add Admin Group**. The **Add Admin Group**window appears. See image.
2. In the **Add Admin Group** window:
  - **Group Selection**: Select a SCIM group to associate a role and scope to.
  - **Role**: Select a [ZDX role](https://help.zscaler.com/zdx/adding-zdx-roles) that you have created.
  - **Scope**: Select Organization, Department, Application, or Location. Organization is selected by default.
3. Click **Save**.

To edit an admin group:

1. Click the **Edit** icon of an admin group. The **Edit Admin Group**window appears. See image.
2. In the **Edit Admin Group** window:
  - **Group Selection**: Select an SCIM group to associate a role and scope with.
  - **Role**: Select a [ZDX role](https://help.zscaler.com/zdx/adding-zdx-roles) that you have created.
  - **Scope**: Select Organization, Department, Application, or Location.
3. Click **Save**.

To delete an admin group:

1. Click the **Delete** icon of an admin group. The **Warning**window appears. See image.
2. In the **Warning** window, click **Delete** to confirm the deletion of the selected admin group.

To view an admin group:

1. Click the **View** icon of an admin group. The **View Admin Group**window appears. See image.
2. In the **View Admin Group** window, you can view:
  - **Group Selection**: The name of the SCIM group.
  - **Role**: The ZDX role associated with the admin group.
  - **Scope**: The scope associated with the admin group.
3. Click **Save**.

[Image: Add Admin Group Window]

[Image: Edit Admin Group Window]

[Image: Delete Confirmation Window]

[Image: View Admin Group Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-collections","lastmod":"2026-06-26T11:04Z","nid":"1535219"} -->
## Managing Collections

- Source: https://help.zscaler.com/zdx/managing-collections
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Managing Collections
- Last modified: 2026-06-26T11:04Z
- Summary: Information on managing collections in the Zscaler Admin Console.

ZDX allows you to manage your collections to easily organize your applications and probes.

The difference between the configuration steps for managing collections for End User and Managed users depends on where the collections are located:

- End User Monitoring page: Policies > Digital Experience Monitoring > Probes > End User
- Managed Monitoring page: Policies > Digital Experience Monitoring > Probes > Managed

You can manage your collections with the following actions:

- Adding a collection.
- Editing the collection.
- Deleting the collection.
- Removing an application from an End User Collection.
- Ranking collections on the End User Monitoring page.
- Disabling or enabling within a collection.
- [Configuring a predefined application.](https://help.zscaler.com/zdx/configuring-predefined-application)
- [Adding a custom application.](https://help.zscaler.com/zdx/adding-custom-application)
- [Editing an application.](https://help.zscaler.com/zdx/editing-application)
- Deleting the application.

## Limitations and Considerations

Consider the following when you manage collections:

- To remove probes, you must either delete the collection or delete the application. You cannot delete probes by themselves.
- For End User collections:
  - If you remove the application from a collection, then it is only removed for that collection.
  - If the application is in multiple collections, then there are rankings. To learn more, see [Understanding Collections](https://help.zscaler.com/zdx/understanding-collections).
- For Managed collections, you can configure an application for only one collection. Therefore, if you remove the application from a collection, it also removes all the associated probes.
- The number of applications and probes you can configure depends on your subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).

You can add a collection using the following steps:

- End User
- Managed

On the End User Monitoring page:

1. Click **Add Collection**. The **Add End User Collection** drawer appears. See image.
2. Configure the following:
  1. Under **End User Collection Settings**: Click **Next**.
    - **Name**: Enter the name of the collection.
    - **Status**: Click to enable or disable the collection.
    - **Tags**: Select or create tags for the collection to easily identify your collection.
    - **Description**: Enter a description of the collection.
  2. Under **Exclusion Criteria** and **Probing Criteria**, you can configure policies for the following criteria: See image. To learn more, see [Understanding Probing Criteria Logic](https://help.zscaler.com/zdx/understanding-probing-criteria-logic). Any application under the collection follows the collection's probing criteria instead of its own probing criteria.
    - User Groups
    - Users
    - Location Groups
    - Zscaler Locations
    - Departments
    - Devices
    - OS
  3. Click **Next**.
3. Click **Add**.

On the Managed Monitoring page:

1. Click **Add Collection**. The **Add Managed Collection** drawer appears. See image.
2. Configure the following:
  - **Name**: Enter the name of the collection.
  - **Status**: Toggle to enable or disable the collection.
  - **Tags**: Select or create tags for the collection to easily identify your collection.
  - **Description**: Enter a description of the collection.
3. Click **Add**.

You can edit a collection using the following steps:

- End User
- Managed

On the End User Monitoring page:

1. Under **Actions**, click **Edit** on the collection you want to edit. The **Edit End User Collection** drawer appears. See image.
2. Configure the fields as needed. These are the same configurable fields when you add the collection.
3. Click **Next**.
4. Click **Update**.

On the Managed Monitoring page:

1. Under **Actions**, click **Edit** on the collection you want to edit. The **Edit Managed Collection** drawer appears. See image.
2. Configure the fields as needed. These are the same configurable fields when you add the collection.
3. Click **Next**.
4. Click **Update**.

Deleting a collection means you are also removing any applications and their associated probes under the collection. If you delete an End User collection where the application is in multiple collections, the application is only removed from that deleted collection.

The Delete action is similar for both the End User Monitoring and Managed Monitoring pages.

For Managed Monitoring, you must delete the probes attached to an application before you can delete a collection.

To delete a collection:

1. Under Actions, click **Delete** on the collection you want to delete. [Image: Click Delete]
2. On the **Deletion** window, click **Delete** to confirm deletion. [Image: Delete Collection Window]

You cannot delete the default collections (i.e., Unified Communication Collection, Predefined Apps Collection, Custom Apps Collection).

You can remove an application from an End User collection. You cannot remove an application from the Unified Communication Collection. You cannot remove an application from Predefined Apps Collection if its status is **Pending Onboard**.

To remove an application:

1. Go to the collection where you want to remove the application.
2. Under **Actions**, click **Remove from Collection** on the application you want to remove. [Image: Click Remove from Collection]
3. On the **Remove App from Collection** window, click **Remove** to confirm the removal. [Image: Click Remove to confirm removal]
4. [Activate the changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

On the End User Monitoring page, you can drag and drop customized collections to rank them for priority.

You cannot change the rankings of the default End User collections (e.g., Predefined Apps Collection).

[Image: Drag and drop collections to adjust rankings]

You can enable or disable the following on either the End User Monitoring or Managed Monitoring pages:

- **Collections**: On the collection you want to update, toggle to enable or disable the collection. [Image: Toggle to enable or disable collection]
- **Applications**: On the application you want to update, toggle to enable or disable the application. [Image: Toggle to enable or disable application]
- **Probes**: On the probe you want to update, [edit the probe](https://help.zscaler.com/zdx/editing-probe) to toggle its status.

After any change in the status, you must [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

If you disable a collection, then you disable any applications within it. For End User collections, if the application is in another collection, then the application is only disabled for the disabled collection.

If you disable an application, then you disable all the probes associated with it. For End User collections, if the application is in another collection, then the application is disabled for all collections.

To delete an application:

- End User
- Managed

For End User Monitoring, you can delete an application from a custom collection or the Custom Apps Collection. You cannot delete an application from the Unified Communication Collection or the Predefined Apps Collection.

Prior to deleting an application, you must [delete its corresponding probes](https://help.zscaler.com/zdx/configuring-probe#delete).

To delete an application for End User Monitoring:

1. Go to the collection where the application resides that you want to delete.
2. Under **Actions**, click **Delete**. [Image: Click Delete]
3. On the **Deletion** window, click **Delete** to confirm the deletion. [Image: Deletion window]

For the Managed Monitoring page, you must [delete the probe](https://help.zscaler.com/zdx/managing-zscaler-hosted-probes#deleteProbe) before you can delete an application.

After you have deleted the probe, you can delete the application on the Managed Monitoring page:

1. Go to the collection where the application resides that you want to delete.
2. Under **Actions**, click **Delete**. [Image: Click Delete]
3. On the **Deletion** window, click **Delete** to confirm the deletion. [Image: Deletion window]

[Image: Add End User Collection drawer]

[Image: Add Managed Collection drawer]

[Image: Edit End User Collection drawer]

[Image: Edit Managed  Collection drawer]

[Image: Configure criteria for probes]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-labels","lastmod":"2026-07-23T21:06Z","nid":"1458916"} -->
## Managing Labels

- Source: https://help.zscaler.com/zdx/managing-labels
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Alert Rule Configuration > Managing Labels
- Last modified: 2026-07-23T21:06Z
- Summary: To provide instructions on how to manage labels on the Labels page for alert rules.

After you have acquired the full permission for Alerts, you can manage your labels and access the Labels page for alert rules.

To manage your labels, you can:

- Add a new label.
- View the label.
- Edit the label.
- Delete the label.

1. Go to one of the following to add a label:
  - **Policies** > **Digital Experience Monitoring** > **Labels** > **Add New Label** See image.
  - **Analytics** > **Digital Experience**> **Alerts** > **Alert Rules and Templates** > **Add Alert Rule** > **Labels (Optional)** > **Add New Label** See image.
2. In the **Create New Label** window:
  - **Name**: Enter the name of the label.
  - **Description** (Optional): Enter the description of the label. The Label Name and Description can each consist of a maximum 120 alphanumeric characters including: hyphen (-), space ( ), or underscore (_). See image.
3. Click **Save**.

You can assign the label to an alert rule in either the**Add New Alert Rule** or **Edit Alert Rule** window. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule) and [Editing an Alert Rule](https://help.zscaler.com/zdx/editing-alert-rule).

On the Labels page (Policies > Digital Experience Monitoring > Labels):

1. Click the **View**icon on your selected label.
2. In the **View Label** window, you can view:
  - **Name**: The label name.
  - **Description**: The label description. See image.
3. Click **Cancel**.

On the Labels page (Policies > Digital Experience Monitoring > Labels):

1. Click the **Edit**icon on your selected label.
2. In the **Edit Label** window:
  - **Name**: Enter the name of the label.
  - **Description** (Optional): Enter the description of the label. The Label Name and Description can each consist of a maximum 120 alphanumeric characters including: hyphen (-), space ( ), or underscore (_). See image.
3. Click **Save**.

You cannot delete a label if an alert rule is tagged with it.

On the Labels page (Policies > Digital Experience Monitoring > Labels):

1. Click the **Delete**icon on your selected label.
2. Click **Delete** in the **Delete <Label Name>** window. A confirmation on the Labels page appears briefly. See image.

[Image: Labels for Alert Rule Configuration]

[Image: Configure Rule - Add New Label]

[Image: Labels Page - Add New Label]

[Image: Create New Label Window]

[Image: Edit Label Window]

[Image: Delete Label Window]

[Image: View Label Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-scripts","lastmod":"2026-06-03T12:54Z","nid":"1526076"} -->
## Managing Scripts

- Source: https://help.zscaler.com/zdx/managing-scripts
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Remediation > Managing Scripts
- Last modified: 2026-06-03T12:54Z
- Summary: Configuration steps on remote scripts for remediation.

Scripts are a great way to automate PowerShell scripts for specific device tasks. Zscaler provides predefined scripts to help you get started.

## Prerequisites

To manage the Scripts page, you must have the following:

- The appropriate subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- The Script Management permission level. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- If you are running a script, the selected devices must have the minimum version compatibility and supporting OS. If a device is not compatible, then the script does not run on that device. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
- If you are uploading a customized script, the script must have a signed certificate. To learn more, see [Preparing Custom Script Signing](https://help.zscaler.com/zdx/preparing-custom-script-signing).

On the **Scripts** page (Policies > Digital Experience Monitoring > Scripts), you can:

- Add a script.
- Run a script.
- Edit a script.
- Delete a script.

## Predefined Scripts

Predefined scripts are useful because they are certified and ready for use script templates. Zscaler recommends testing the script prior to deploying production to devices.

Predefined scripts are found on the Scripts Templates page (Policies > Digital Experience Monitoring > Scripts > View Script Templates). For each predefined script, you see:

- **Name**: The name of the predefined script.
- **Description**: The description of the predefined script.
- **Operating System**: The supported operating system.
- **Last Updated**: The timestamp of when the predefined script was released.

You can view, search, or import the predefined scripts.

See image.

## Limitations

Consider the following when configuring scripts:

- The maximum number of scripts you can configure is based on your subscription. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- You can upload to the maximum script file size of 512 KB.
- You can import a predefined script only once.
- You are unable to run an older version of a predefined remote script if it is revoked. You must delete the previously configured predefined script and import the latest predefined script to continue use.
- A script runs for a maximum of 10 minutes.

1. Click the **Run** icon
2. In the **Start Remediation Job** window: See image.
  1. Under **Select Script**:
    1. **Script**: Search and select which script you want to run. You can also [create a script](https://help.zscaler.com/zdx/managing-remote-scripts#add).
    2. **Script Details**: If you configured [script parameters or an end user notification](https://help.zscaler.com/zdx/managing-remote-scripts#fields), then you can modify the script parameters and preview the end user notification.
    3. **Script Run Settings**: Enter the number of seconds the script can run for.
  2. Under **Select Devices**, click **Add** to open the **Select Devices** drawer. If you cannot select a device, check its version compatibility or OS. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility). See image.
    1. **Criteria**: Select **Devices**, **Users**, **User Groups**, **Location**, **Location Groups**, or **Departments**.
    2. **Search**: You can search for the name of the selected criteria. Click the **Remove** icon to reset your search.
    3. Click **Done** to save your selection.
  3. Under **Job Details**:
    1. **Name**: Enter the name of the Remediation job.
    2. **Wait for devices to be online for a maximum of**: Enter the number of minutes or days a script must wait to run on a device.
    3. **Schedule Job for later**: Enable to select a date for when you want to run the script.
3. Click **Start**.

View the job's progress on the [Device Remediation](https://help.zscaler.com/zdx/viewing-and-managing-device-remediation) page.

1. Click **Add Script**.
2. In the **Add Script** window: See image.
  - **Script Name**: Enter a name for the script.
  - **Description**: Enter a description of the script.
  - **Operating System**: Select which operating system this impacts (**Windows**).
  - **Upload Script File**: Upload a PS1 file to run the script. The maximum script file size when uploading is 512 KB.
  - **Run Script with Elevated Permissions (System Account)**: Select if you want to run the script on a system account.
  - **Script Parameters (Optional)**: Add parameters to determine when to run the script. See image.
    - **Parameter Name**: Enter a parameter name (e.g., Domain) for the script to evaluate.
    - **Default Value**: Enter an example value (e.g., `google.com`) for the script to evaluate.
    - **Mandatory**: Select the checkbox if the parameter is mandatory. By default, this is selected.
  - **End User Notifications**: **Show** or **Hide** notifications to the end user. When **Show** is selected, configure the following to show to the end user prior to running the script: See image.
    - **Message From**: Enter the name of the team you want to indicate the message is from.
    - **Header**: Enter the name of the header to display.
    - **Question/Message**: Enter a question or message.
    - **Accept Button**: Enter the text for the Accept button (e.g., Okay).
    - **Decline Button**: Enter the text for the Decline button (e.g., Cancel).
    - **Preview**: Displays a preview of the configured notification.
3. Click **Add**.

1. Click the **Edit** icon on the script that you want to modify.
2. In the **Edit Script** window, modify any of the fields as needed. See image.
3. Click **Save Changes**.

1. Click the **Delete** icon.
2. In the **Delete Script** window, click **Delete**. [Image: Confirm the script deletion]

[Image: Add a script]

[Image: Add script parameters]

[Image: Create an end-user notification prior to running the script]

[Image: Enter Remediation job fields]

[Image: Select devices]

[Image: Edit the script fields]

[Image: Predefined scripts are in the Script Templates page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-templates","lastmod":"2026-07-10T10:00Z","nid":"1499921"} -->
## Managing Templates

- Source: https://help.zscaler.com/zdx/managing-templates
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Alert Rule Configuration > Managing Templates
- Last modified: 2026-07-10T10:00Z
- Summary: To provide steps for managing or configuring templates.

[Watch a video about Templates for ZDX](https://fast.wistia.net/embed/iframe/t4mafl179c) (shows legacy UI).

On the Templates page, you can manage and configure templates by adding, editing, copying, or deleting a Zscaler Managed probe. You can use the template to create an alert rule with prefilled criteria. To learn more, see [Configuring a Zscaler Managed Probe](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes).

## Prerequisites

Before you manage templates, ensure that:

- Your admin role supports Alerts and Configuration. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- Your subscription level supports Managed Monitoring. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).

## Managing Templates

To manage your templates (Analytics > Digital Experience > Alerts > Alert Rules and Templates > Templates), you can perform the following actions:

- Add a new template.
- Edit a template.
- Copy a template.
- Delete a template.

## Predefined Templates

To start creating alert rules for Zscaler Managed probes, there are a number of predefined templates ready for use.

You cannot delete or edit predefined templates.

| Predefined Template | Severity | Probe Type | Criteria |
| --- | --- | --- | --- |
| Warning Availability | High | Web | **Availability** < 100%; **Occurs** 2 **times in**60 **minutes**; **Across any**1 **Zscaler Managed Locations** |
| Critical Page Fetch Time | High | Web | **Page Fetch Time** > 1000 ms; **Occurs** 3 **times in**15 **minutes**; **Across any**2 **Zscaler Managed Locations** |
| Warning Page Fetch Time | Medium | Web | **Page Fetch Time** > 1000 ms; **Occurs** 3 **times in**15 **minutes**; **Across any**1 **Zscaler Managed Locations** |
| Probe Failure | High | Web | **Availability** = 0%; **Occurs** 3 **times in 15** **minutes**; **Across any**1 **Zscaler Managed Locations** |
| Critical Latency | High | Cloud Path | **Latency**> 5 ms; **Occurs** 3 **times in 15** **minutes**; **Across any**2 **Zscaler Managed Locations** |
| Warning Latency | Medium | Cloud Path | **Latency**> 5 ms; **Occurs** 3 **times in 15** **minutes**; **Across any**1 **Zscaler Managed Locations** |

To add a new template:

1. Click **New Template**.
2. In the**Add New Template** drawer, enter the required information: See image.
  - **Name**: Enter a name to identify the template.
  - **Severity**: Select **High**, **Medium**, or **Low**for severity, depending on the impact of this event on users.
  - **Probe Type**: Select **Cloud Path** or **Web** for the probe type.
  - **Criteria**: Select your criteria for the template. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).
3. Click **Save**.

To edit a template:

1. Click the **Edit** icon.
2. In the **Edit Template**drawer, you can edit: See image.
  - **Name**: Enter a name to identify the template.
  - **Severity**: Select **High**, **Medium**, or **Low**for severity, depending on the impact of this event on users.
  - **Probe Type**: Select **Cloud Path** or **Web** for the probe type.
  - **Criteria**: Select your criteria for the template. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).
3. Click **Save**.

To copy a template:

1. Click the **Copy** icon.
2. In the **Add New Template** drawer, enter the following: See image.
  - **Name**: Enter a name to identify the template.
  - **Severity**: Select **High**, **Medium**, or **Low**for severity, depending on the impact of this event on users.
  - **Probe Type**: Select **Cloud Path** or **Web** for the probe type.
  - **Criteria**: Select your criteria for the template. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).
3. Click **Save**.

To delete a template:

1. Click the **Delete** icon.
2. In the **Delete** window, review the deletion request for the specified template.
3. Click **Delete** to confirm deletion.

See image.

[Image: Add New Template Drawer]

[Image: Edit Template configuration fields]

[Image: Copied template is prefilled into an Add New Template drawer]

[Image: Confirm template deletion]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-top-private-applications","lastmod":"2026-06-09T21:24Z","nid":"1456301"} -->
## Managing Top Private Applications

- Source: https://help.zscaler.com/zdx/managing-top-private-applications
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Managing Top Private Applications
- Last modified: 2026-06-09T21:24Z
- Summary: Provides an overview and summary of how to manage and configure top private applications from Private Access.

The list of Top Private Apps is created with readily available data from Private Access to provide an overview of the top private applications and a seamless probe configuration. You can search for application data based on the port numbers that you enter and then sort by users or bandwidth. You can configure the private applications and their respective probes in the configuration wizard, which pre-populates with application information from Private Access.

ZDX can monitor top private applications accessed over IPv6 when integrated with Private Access. To enable this, ensure the Zscaler Client Connector and Private Access App Connectors are IPv6-capable and that private app segments are defined using IPv6 addresses or dual-stack DNS names. Then, ZDX can track performance, availability, and user experience for top private applications accessed over IPv6. On the Top Private Apps page, ZDX provides visibility into response times, path health, and error rates across both IPv4 and IPv6 access paths.

To view a list of Top Private Apps:

1. Go to **Configuration**> **Probes**> **Top Private Apps**.
2. Enter the port numbers that a private application uses. Separate each port number with a comma.
3. Sort by users or bandwidth. The default is set to users.
4. Click **Submit**. See image.
5. For the list of top private applications, you can view:
  - **Application**: The name of the application.
  - **Port**: The port number the application uses
  - **Protocol**: The type of protocol used.
  - **Users**: The number of users using the application.
  - **Bandwidth**: The bandwidth usage for multiple devices.
  - **Configured for ZDX**: Provides status information if the application's probe configuration was enabled or configured on ZDX.
  - **Configure**: The option to configure the probing configuration.

If an application has the field **Configured for ZDX** set to **Yes**, then you cannot configure its probe configuration in the Top Private Apps page. You can access the probe configuration for the application in **Configuration** > **Probes**. To learn more, see [Editing a Probe](https://help.zscaler.com/zdx/editing-probe).

See image.

To configure a private application's probe on the Top Private Apps page:

1. Select an application and click **Configure**.
2. On the **Application Details** tab, you can configure the following as required: Click **Next**. See image.
  - **Name**: Enter the name of the application.
  - **Status**: Enable or disable the application's probing status.
  - **Configure**: Select which probing paths to use.
3. For Web probe:
  - a. Configure Web probe
  - b. Additional Parameters
  - c. Review
4. For Cloud Path probe: If you selected only the Web probe in previous step, then you do not have the option to configure a Cloud Path probe.
  - a. Configure Cloud Path probe
  - b. Additional Parameters
  - c. Review
5. Click **Submit** to save all of your configurations. See image.
6. [Activate the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

To learn more, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe).

- **Name**: Enter a name for the Web probe.
- **Status**: Enable or disable the probe.
- **Run Frequency (minutes)**: Enter the number of minutes for how frequently your probe should run. The default probe frequency varies based on your subscription plan. To learn more, see [Ranges & Limitations](https://help.zscaler.com/zdx/ranges-limitations).

You cannot configure the **Application**, **Probe Type**, or **Probe Class**.

- **Users**: Select which users to include.
- **User Groups**: Select which user groups to include.
- **Locations**: Select which Zscaler locations to include.
- **Departments**: Select which departments to include.
- **Devices**: Select which devices to include.

- **Users**: Select which users to exclude.
- **User Groups**: Select which user groups to exclude.
- **Locations**: Select which Zscaler locations to exclude.
- **Departments**: Select which departments to exclude.
- **Devices**: Select which devices to exclude.

- **Name**: Enter a name for the Cloud Path probe.
- **Status**: Enable or disable the probe.
- **Run Frequency (minutes)**: Enter the number of minutes for how frequently your probe should run. The default probe frequency varies based on your subscription plan. To learn more, see [Ranges & Limitations](https://help.zscaler.com/zdx/ranges-limitations).

You cannot configure the **Application**, **Probe Type**, or **Probe Class**.

- **Users**: Select which users to include.
- **User Groups**: Select which user groups to include.
- **Locations**: Select which Zscaler locations to include.
- **Departments**: Select which departments to include.
- **Devices**: Select which devices to include.

- **Users**: Select which users to exclude.
- **User Groups**: Select which user groups to exclude.
- **Locations**: Select which Zscaler locations to exclude.
- **Departments**: Select which departments to exclude.
- **Devices**: Select which devices to exclude.

- i. Configure General information.
- ii. Configure the Probing Criteria to include criteria for probe monitoring.
- iii. Configure the Exclusion Criteria to exclude criteria from probe monitoring.

Click **Next**.

See image.

You cannot configure the **Probe Name**, **Application Name**, or **Request Type**.

You can configure the following:

- **Destination URL**: Enter the web destination, either an HTTP or HTTPS URL. This is the web address the probe requests. This is editable if the URL requires a tenant name.
- **Request Header**:
  - **Name**: Enter the name of the request header.
  - **Value**: Enter the value of the request header.
  - **Add More**: Allows the option to add more than one name and value pair.
  - **HTTP Response Status Code**: You can probe for specific [HTTP Status Codes](https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml). You can add HTTP codes that are not in the default list by entering any number between the range of 100 to 499. By default, the following 1xx (Informational), 2xx (Success), and 3xx (Redirection) codes are applied: You can remove any of these codes by clicking the **Close** icon.
    - 100 Continue
    - 101 Switching Protocol
    - 102 Processing
    - 103 Early Hints
    - 200 OK
    - 201 Created
    - 202 Accepted
    - 203 Non-Authoritative Information
    - 204 No Content
    - 205 Reset Content
    - 206 Partial Content
    - 207 Multi-Status
    - 208 Already Reported
    - 226 IM Used
    - 300 Multiple Choices
    - 301 Moved Permanently
    - 302 Found
    - 303 See Other
    - 304 Not Modified
    - 307 Temporary Redirect
    - 308 Permanent Redirect
- **Number of Attempts**: The number of attempts before considering the monitor request failed. The default is 1 attempt, but can be increased.
- **Timeout (seconds)**: The default is 60 seconds.
- **Follow Redirect**: Enable or disable this feature.
- **Maximum Redirects**: This specifies the number of times the probe attempts to follow the HTTP redirect before it is considered failed. The default is 5 redirects. If Follow Redirect is disabled, Maximum Redirects is also disabled.

Click **Next**.

See image.

Review your **Web probe** configuration, or you can edit any section as required.

Click **Next** to save your Web probe configuration.

See image.

- i. Configure General information.
- ii. Configure the Probing Criteria to include criteria for probe monitoring.
- iii. Configure the Exclusion Criteria to exclude criteria from probe monitoring.

Click **Next**.

See image.

You cannot configure the **Probe Name** or **Application Name**.

You can configure the following:

- **Protocol**: Select a protocol from the drop-down menu. Options include **Adaptive**, **ICMP**, **TCP**, and **UDP**.
- **TCP Port**: If you choose **TCP**as the protocol, this field is automatically displayed and populated with the standard TCP Port for HTTPS traffic, 443, though this can be edited.
- **Packet Count**: The number of probe packets sent per hop discovery that have the same TTL value. The default is 5 packets, the maximum is 20 packets, and the minimum is 3 packets. For Private Access, the recommended packet count is 3 packets, and the maximum is 6 packets.
- **Interval (ms)**: The time interval between probe packets with the same TTL. Probe packets of incremental TTL are paced evenly within this time interval. The number of iterations or cycles is defined by the configured Packet Count. The default is `1000`, the minimum is `1000`, and the maximum is `10000`.
- **Timeout (ms)**: The time to wait for a response to a probe packet before considering loss. The default is `1000`, the minimum is `500`, and the maximum is `5000`. The recommended setting for Private Access is `500`.
- **Cloud Path Host**: The tenant name or fully qualified domain name for the host (i.e., the IPv4 IP address; IPv6 is not supported). Zscaler partially provides the domain name if the application selected is predefined. This is editable if a tenant name is required.

Click **Next**.

See image.

Review your **Cloud Path probe** configuration or edit any field as required.

[Image: Submit a search criteria for ports.]

[Image: Search Results of Ports 8080 and 80]

[Image: Application Details]

[Image: Configure Web Probe]

[Image: Configure Web Probe - Additional Parameters]

[Image: Review of Web Probe Configuration]

[Image: Configure Cloud Path Probe]

[Image: Cloud Path Probe - Additional Parameters]

[Image: Review of Cloud Path Probe Configuration]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-zdx-admins","lastmod":"2026-04-20T15:56Z","nid":"1479156"} -->
## Managing ZDX Admins

- Source: https://help.zscaler.com/zdx/managing-zdx-admins
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Managing ZDX Admins
- Last modified: 2026-04-20T15:56Z
- Summary: Provides steps on how to manage and configure a ZDX Admin.

After you have the Full permission level for Administrator Management, you can manage and configure a ZDX Admin by adding, editing, or deleting a ZDX Admin. You can assign a scope to a ZDX Admin to provide limitations and access to certain areas within an organization. To learn more, see [Understanding the Admin Scope](https://help.zscaler.com/zdx/understanding-admin-scope).

To manage or configure a ZDX Admin (Administration > Admin Management > Role Based Access Control> Administrators),you can perform the following actions:

- Add a ZDX Admin
- Edit a ZDX Admin
- Delete a ZDX Admin
- View a ZDX Admin

## Admin Caveats

When managing ZDX admins, be aware of the following caveats:

- Creating a ZDX Admin with the Same Credentials in the Zscaler Admin Console
- Changed ZDX Admin Scope for Department or Location
- Password Expiration

If a user switches departments or locations, the user's data is reported for the latest configured department or location.

You can configure password expiration for admins in the Zscaler Admin Console. Based on the settings configured, admins are reminded to change their password and shown the number of days their current password remains valid. If the password expires, admins must provide a new password to access the service.

If you create an admin user for ZDX with the same credentials used to previously create an admin for Internet & SaaS, the admin credentials for the new user in ZDX override what was created for Internet & SaaS, excluding the Login ID.

For example, if you used example@email.com to create an admin for Internet & SaaS and then create an admin with the same email in ZDX, the user credentials associated with that email in ZDX take priority and rewrite all credentials for that email in Internet & SaaS, excluding the Login ID. So, upon your next login to Internet & SaaS, the Login ID remains the same, but the admin's password will have changed.

To learn more, see [About ZDX Role-Based Administration](https://help.zscaler.com/zdx/about-zdx-role-based-administration).

To add a ZDX Admin:

1. Click **Add ZDX Admin**. The **Add ZDX Admin** window appears. See image.
2. In the **Add ZDX Admin** window:
  - **Login ID**: Enter an ID in the format of `<admin-name>``@``<company-name>``.com`*.* This field cannot be overwritten, even if the other credentials are also used for Internet & SaaS.
  - **Email**: Enter a valid email for the admin.
  - **Name**: Enter the admin name.
  - **Role**: Select a role from the available options. You can also search for roles or click the **Add**icon to [add a new role](https://help.zscaler.com/zdx/adding-zdx-roles). The predefined roles are **ZDX Read-only Admin**, **ZDX Service Desk Tier 1**, and **ZDX Super Admin**. To learn more, see [About ZDX Role-Based Administration](https://help.zscaler.com/zdx/about-zdx-role-based-administration). See image.
  - **Status**: Select **Enabled**or **Disabled**.
  - **Scope**: Select **Organization**, **Department**, **Applications**, or **Location**.
  - **Comments**: (Optional) Add any comments about the admin.
  - **Set Password**: Create a password and enter it again to confirm it. The password must contain at least 10 characters and include 1 number, 1 uppercase character, 1 lowercase character, and 1 special character. The password must not contain whitespace. See image.
3. Click **Save** and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

You can edit a configured admin. Admins to whom you have access, based on your scope, are indicated by the **Edit**icon.

To edit an administrator:

1. Click the **Edit** icon. The **Edit ZDX Admin** window appears. See image.
2. Edit the following fields as necessary:
  - **Login ID**: The email address that is used to log in. This field cannot be edited.
  - **Email**: The email address used to log in.
  - **Name**: The name of the admin.
  - **Role**: The role that the admin was assigned. To learn more, see [About ZDX Role-Based Administration](https://help.zscaler.com/zdx/about-zdx-role-based-administration).
  - **Status**: Select **Enabled** or **Disabled**.
  - **Scope**: Select **Organization**, **Department**, **Applications**, or **Location**.
  - **Comments**: Add additional comments as desired.
3. To change the password, enter a **New Password** and enter it again in the **Confirm Password** field. The password must contain at least 10 characters and include 1 number, 1 uppercase character, 1 lowercase character, and 1 special character. The password must not contain whitespace.
4. Click **Save** and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

You can delete a configured admin. Admins to whom you have access, based on your scope, are indicated by the **Delete**icon.

To delete an administrator:

1. Click the **Delete** icon. The **Confirm Changes** window appears. See image.
2. Click **Yes** to confirm you are deleting the admin. This removes the admin from the Zscaler Admin Console. Upon deletion, a confirmation window briefly appears on the **Administrators** page.
3. [Activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

You can view a configured admin outside your scope and role by clicking the **View** icon. If you have view permission for a configured admin, you cannot manage the configured admin (e.g., delete, edit, create).

See image.

[Image: Add a ZDX Admin by entering the required fields]

[Image: Role dropdown options displayed in the Add ZDX Admin window]

[Image: Set Password if enabling password]

[Image: Edit ZDX Admin Window]

[Image: Confirm deletion]

[Image: View ZDX Admin Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-zdx-api-keys","lastmod":"2026-08-06T12:19Z","nid":"1403316"} -->
## Managing ZDX API Keys

- Source: https://help.zscaler.com/zdx/managing-zdx-api-keys
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > API Key Management > Managing ZDX API Keys
- Last modified: 2026-08-06T12:19Z
- Summary: Information about how to manage the ZDX API Key Management.

After your API subscription is enabled, the Zscaler Digital Experience (ZDX) API Key Management page is accessible to a ZDX admin. From there, the admin can provision and display available API keys.

If you must obtain API keys or secrets to access [Zscaler OneAPI](https://help.zscaler.com/oneapi) endpoints, see [About API Clients](https://help.zscaler.com/authentication-service/about-api-clients).

The API Key Management page allows you to perform the following actions:

- Create a new API key.
- View an API key.
- Edit the API key.
- Delete the API key.

To learn more, see [About API Key Management](https://help.zscaler.com/zdx/about-api-key-management).

1. Go to **Administration > API Configuration > Legacy API > Digital Experience API**.
2. Click the **View** icon for a specific API key to open the **API Key** window.
3. Click **Copy** to copy the API Key ID.

[Image: Window that displays the available information for viewing an API key]

1. Go to **Administration > API Configuration**> **Legacy API**> **Digital Experience API**.
2. Click **Create API Key**.
3. Enter the required information: [Image: Create an API Key]
  - **Name**: Enter the API key name.
  - **Select Role**: Select an admin role to be assigned the API key.
4. Click **Create Key** to confirm, and the **API Key** window appears.
5. Click **Copy** to copy the API Key ID or Key Secret, or click **Download** to download the JSON file. You need both the API Key ID and Key Secret for [authentication](https://help.zscaler.com/zdx/getting-started-zdx-api/#authenticate-api). [Image: Window that displays the information needed once an API key is created]
6. Close the window.

1. Go to **Administration > API Configuration > Legacy API > Digital Experience API.**
2. Click the **Edit** icon for a specific API key to open the **Edit API Key** window.
3. Edit the **Name** or choose a new role from the **Select Role** drop-down menu.
4. Click **Save** to confirm the changes.

[Image: Edit the API key name or role]

The **Delete** action allows you to delete the specified API key.

To delete a specific API key:

1. Go to **Administration > API Configuration > Legacy API > Digital Experience API**.
2. Click the **Delete** icon for a specific API key to open the **Delete API Key** window.
3. Click **Delete** to confirm the deletion.

[Image: Delete API Key]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-zdx-roles","lastmod":"2026-04-20T16:11Z","nid":"1487681"} -->
## Managing ZDX Roles

- Source: https://help.zscaler.com/zdx/managing-zdx-roles
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Managing ZDX Roles
- Last modified: 2026-04-20T16:11Z
- Summary: How to manage ZDX Roles in the Zscaler Admin Console.

You can add, edit, delete, or view a ZDX Role in the ZDX Admin Portal depending on what permission levels you have. ZDX Roles to which you have Full access are indicated by the **Edit** and **Delete**icon. If you have View Only access, then the ZDX Role shows a **View** icon and you cannot edit or delete them.

The Role Management page (Administration > Admin Management > Role Based Access Control > Digital Experience) allows you to perform the following actions:

- [Add a ZDX Role](https://help.zscaler.com/zdx/adding-zdx-roles)
- Edit a ZDX Role
- Delete a ZDX Role
- View a ZDX Role

You cannot edit or delete a predefined role (e.g., Super Admin).

To view a specific ZDX Role:

1. Click the **View** icon. The **View ZDX Role**window appears. See image.
2. View the following fields as necessary:
  - **Name**: The name of the role.
  - **Permissions**: The permissions that the role was assigned. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
3. Click **Cancel** to return to the Role Management page.

To edit a specific ZDX Role:

1. Click the **Edit** icon. The **Edit ZDX Role**window appears. See image.
2. Edit the following fields as necessary:
  - **Name**: The name of the role.
  - **Permissions**: The permissions that the role was assigned. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
3. Click **Save** and [activate the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

To delete a specific ZDX Role:

1. Click the **Delete** icon. The **Confirm Changes** window appears. See image.
2. To delete the admin, click **Confirm**. This removes the admin from the ZDX Admin Portal.
3. Click **Save** and [activate the changes](https://help.zscaler.com/zdx/saving-and-activating-changes-admin-portal).

[Image: Edit ZDX Role]

[Image: View ZDX Role]

[Image: Confirm Changes Window]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/managing-zscaler-managed-probes","lastmod":"2026-06-25T21:06Z","nid":"1506026"} -->
## Managing Zscaler Managed Probes

- Source: https://help.zscaler.com/zdx/managing-zscaler-managed-probes
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Probes > Managing Zscaler Managed Probes
- Last modified: 2026-06-25T21:06Z
- Summary: How to manage Zscaler Managed probes within an application.

[Watch a video about configuring and managing Zscaler Managed Probes.](https://fast.wistia.net/embed/iframe/3kol3ib93j)

Managed Monitoring operates from within the Zscaler cloud infrastructure as a multi-tenant service. The service allows you to logically group Web and Cloud Path probes into independent collections to create your own tests to monitor performance. To learn about configuring Zscaler Managed probes and the required prerequisites for monitoring, see [Configuring Zscaler Managed Probes](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes).

## Managing an Application's Zscaler Managed Probes

To view and manage configured probes within a collection, go to **Policies** > **Digital Experience Monitoring** > **Configuration** > **Probes**> **Managed** > <collection name> > <application name>.

The page provides the following details and actions for the selected application:

1. **Application Name**: Click the **Edit**icon to change the application name.
2. **Status**: Displayed as either Enabled or Disabled. Click the blue toggle to change the Status.
3. **Total**:The total number of active probes that for the application.
4. **Probes**:The number of active Web probes and Cloud Path probes for the application.
5. **Filters**:The options to monitor a specific status or probe type.
6. Edit or delete the application that the probes are running on.
7. **Search**: Find a specific probe within the collection. Search for any character string that might be part of the probe name.
8. **Add Probe**: Add a probe to the selected collection.
9. The following information and actions are provided within the table for the selected collection:
  - **Type**: Identified with an icon as either a Web probe or Cloud Path probe.
  - **Name**: The configured probes within the selected collection. Click any probe name to view its specific settings.
  - **Host**: The URL of the managed location.
  - **Locations**: The number of Zscaler managed locations from where the probe is run.
  - **Status**: The probe is currently either **Enabled** or **Disabled**. If the probe is **Enabled**, then it is an active probe.
  - **Protocol/Methods**: The method for Web probes or the protocol for Cloud Path probes.
  - **Alert Rules**: The number of alert templates associated with the probe.
  - **Frequency**: The time interval for how often the probe is run.
  - **Actions**: The option to edit or delete the probe, or to configure alert rules.
10. Edit or delete the probes.

[Image: Managed Collection with probes]

You can manage the number of active probes with the following actions:

- Enabling or disabling a collection.
- Adding or deleting a collection.
- Editing the probe or companion probe.
- Adding or deleting a probe.

You can enable a collection for active use or disable a collection if you want to use it later and to manage the amount of active Zscaler Managed probes.

1. Go to **Configuration**> **Managed**. Select the collection you want to configure.
2. Toggle the blue toggle of the collection to **Enabled** or **Disabled**. [Image: Disable the Enabled toggle] A confirmation window appears briefly to confirm that the collection was enabled or disabled and its configuration was saved.

When disabling a collection, you disable all probes within the collection.

You can [create a collection](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes) to organize your Zscaler Managed probes or delete a collection if it is no longer of use to you.

To delete a collection:

1. Go to **Configuration**> **Managed**. Select the collection you want to delete.
2. Click the **Menu** icon and click **Delete** to begin collection deletion. The **Delete Collection** window appears.
3. Confirm the deletion of the collection by clicking **Delete**. [Image: Confirm the deletion of the collection]

If you delete a collection, then all probes and associated companion probes are deleted.

After you add a probe to a collection, you can enable or disable any probe within a collection by clicking the **Edit** icon. You manage the status if there are other probes within a collection that you want to remain active, but you are selecting one to disable.

In the Edit Probe window, you can toggle the **Status** of the probe or companion probe.

[Image: Enable or disable the probe]

When you disable a probe, then the companion probe is also disabled.

You cannot enable a companion probe while the parent probe is disabled.

When you enable the parent probe, the companion probe is not enabled immediately as you must enable its status in the **Edit Probe - Companion Probe** configuration.

You can [add a probe](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes) and enable it to become an active probe to provide you with telemetry data.

You can delete a probe and its companion probe if they are no longer of use to you.

To delete a probe, click on the **Delete** icon, then you can confirm its deletion in the **Delete Probe** window.

[Image: Confirm probe deletion]

If you delete a probe with a companion probe, then the companion probe is also deleted.

You cannot selectively delete a companion probe, but you can disable it so that it does not count towards your active probes.

## Managing Alert Rules

You can add or edit templates for alert rules directly from the probe collection table. Alert templates can be useful when reusing settings for alert rules. To learn more about the templates, see [About Templates](https://help.zscaler.com/zdx/about-templates) and [Managing Templates](https://help.zscaler.com/zdx/managing-templates). To add alert templates as an option when configuring Zscaler Managed Probes, see [Configuring Zscaler Managed Probes](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes).

### Adding Alert Rules

Create an alert template or add a predefined alert template from the collection table:

- Creating a Template.
- Adding a Predefined Template.

When you configure an alert for a Zscaler Managed probe, you can create a new alert template:

1. On the **Add Alerts (Optional)** configuration, click **Add**, and then click **Create New Template**. See image.
2. Configure the template details and criteria in the **Add New Template** window and click **Save**. To learn more, see [Managing Templates](https://help.zscaler.com/zdx/managing-templates).
3. Select your new template from the drop-down menu. The alert criteria appears.
4. Under **Define Alert Action**, confirm whether the alert should be muted or unmuted. If you disable **Muted**, select your alert delivery method from the drop-down menu. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule). See image.
5. Click **Save**.

To add a template with predefined criteria for an alert rule:

1. Locate a probe within the table to add the alert rule.
2. Click the probe's **Alert**icon located on the right side of the table.
3. Click **Add**, and then select a predefined template from the drop-down menu. The alert criteria appears. See image.
4. Under **Define Alert Action**, confirm whether the alert should be muted or unmuted. If you disable **Muted**, select your alert delivery method from the drop-down menu. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule). See image.
5. Click **Save**.

### Editing Alert Rules

To edit an existing template from the application page:

1. Locate a probe within the application to add the alert rule.
2. Under **Actions**, click **Alert**. See image.
3. If the probe has more than one alert rule, locate the template and update the alert criteria. To learn more about configuring templates, see [Managing Templates](https://help.zscaler.com/zdx/managing-templates).
4. Under **Define Alert Action**, configure the fields that are similar when adding a template.
5. Click **Save**.

To learn more about monitoring the probes, see [Understanding Managed Monitoring](https://help.zscaler.com/zdx/understanding-managed-monitoring).

[Image: Create a new template for the alert rule]

[Image: Define Alert Action]

[Image: Add existing alert template]

[Image: Add existing predefined template]

[Image: Click Alerts to add or edit the alert]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/microsoft-teams-webhook-configuration-guide","lastmod":"2026-07-10T10:01Z","nid":"1440861"} -->
## Microsoft Teams Webhook Configuration Guide

- Source: https://help.zscaler.com/zdx/microsoft-teams-webhook-configuration-guide
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > Microsoft Teams Webhook Configuration Guide
- Last modified: 2026-07-10T10:01Z
- Summary: Information on how to configure webhooks for alerts using Microsoft Teams.

You must have a Microsoft Teams account to configure a webhook.

This guide provides information on configuring webhooks using Microsoft Teams for alerts in ZDX. The following instructions provide a sample configuration that you can use based on the user’s requirements.

1. In Microsoft Teams:
  1. Select the team in which you want to add a channel for webhook notifications.
  2. Click **More Actions** (**…**) > **Add channel**. See image.
  3. Enter the name of the channel and save your changes.
  4. On the **Posts** tab, where you can see the conversation, go to **Channel** > **Connectors** > **Incoming Webhook** > **Configure**. See image.
  5. Enter the name of the webhook. Adding an image is optional. Click **Create**. See image.
  6. Copy the Zscaler Webhook Channel’s URL from the webhook configuration page. See image.
  7. Click **Save**.
2. [Create a webhook in the Zscaler Admin Console](https://help.zscaler.com/zdx/configuring-webhooks).
  1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
  2. Click **Add Webhook**.
  3. Enter the following information: See image.
    - **Name**: The name of the webhook for Microsoft Teams.
    - **Status**: Select **Enabled**.
    - **URL**: Paste the URL you copied from the Microsoft Teams webhook configuration page from the previous step.
    - **Authentication Type**: Select **Token**.
    - **Bearer Token**: Microsoft Teams does not generate a bearer token. To meet webhook configuration requirements, enter any text for the bearer token (e.g., `1234`).
  4. (Optional) Click **Test Webhook** to confirm the webhook configuration works. See image.
  5. Click **Save**.

## Managing Incoming Webhooks in Microsoft Teams

To manage your configured webhooks for Microsoft Teams to update the URL or generate a new URL, select the impacted channel and go to **Configured** > **Manage**.

See image.

[Image: On Microsoft Teams, add a channel to generate a webhook.]

[Image: Select Connectors]

[Image: Select Configure from the Incoming Webhook on Configured.]

[Image: Enter name for the webhook.]

[Image: Copy Webhook URL]

[Image: Add Webhook]

[Image: Test Webhook Result]

[Image: Manage the Microsoft Teams webhook as needed.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-applications-overview","lastmod":"2026-05-28T07:06Z","nid":"1355811"} -->
## Monitoring the Applications Overview

- Source: https://help.zscaler.com/zdx/monitoring-applications-overview
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Applications > Monitoring the Applications Overview
- Last modified: 2026-05-28T07:06Z
- Summary: Information on the Applications Overview and widgets accessible within the ZDX Admin Portal.

The Applications Overview provides information about the applications users are accessing and the impact of those applications on your organization's digital experience. If you have [Real User Monitoring (RUM)](https://help.zscaler.com/zdx/understanding-real-user-monitoring) enabled for an application, then you can view RUM metrics (e.g., RUM Users, application details specific to RUM).

See image.

## Overview Tools

The Applications Overview allows you to:

- **View performance data over time**: Use the Time Range filter to choose a specific time range to view data. The selected period applies to all data within the overview. The default time range is **2 Hours**.
  - **Current**: View the most current ZDX Score captured within the previous 30 minutes.
  - **2 Hours** to **48 Hours**: Specify a time interval between 2 hours and 48 hours as shown in the drop-down menu.
  - **Custom**: Specify a custom time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments.
- **Filter data**: Click the filters to select options for Departments, Zscaler Locations, User Groups, Geolocations, Location Groups, Last Mile ISPs, Operating Systems, and Monitoring Type. Each filter allows you to include or exclude individual options. Click **Select All Displayed** to select all options at once. See image.
- **Share a ZDX Snapshot**: Click **Share Snapshot**to capture a snapshot of the current state of the Applications Overview page. Share it with other ZDX users or admins for view-only access. To learn more, see [Sharing ZDX Snapshots](https://help.zscaler.com/zdx/sharing-zdx-snapshots).
- **Compare Applications**: Click the **Open in a New Tab** icon next to the application name to open the application page in a new tab. You can use this icon to open multiple applications and compare their details.

### Filtering

To configure filters:

1. Determine which options to include or exclude from each of the filter drop-down menus:
  - **Departments**: Your departments, as defined in ZDX. To learn more, see [About Departments](https://help.zscaler.com/zdx/about-departments).
  - **Zscaler Locations**: The Zscaler locations where your devices are connected to the application.
  - **User Groups**: The names of user groups in your organization.
  - **Geolocations**: The geographic area where users accessed the applications.
  - **Location Groups**: The names of groups based on location in your organization.
  - **Last Mile ISPs**: The Internet Service Providers (ISPs) to which your users are connecting.
  - **Operating Systems**: The operating system versions installed on user devices in your organization.
  - **Monitoring Type**: The type of application monitoring to which your users are connecting.
2. Click **Apply** after completing your selections.

You can adjust the filters as needed, or remove all of your filter selections by clicking **Reset**.

## Applications List

The table displays the following information about applications and the impact they have to the digital experience:

- **Application**: These names are defined when you configure the application. To learn more, see [About Applications](https://help.zscaler.com/zdx/about-applications). Any disabled applications are shown in gray, and deleted applications are indicated with a strikethrough on the application name. Click a name to see more information about [each application](https://help.zscaler.com/zdx/evaluating-application-details).
- **ZDX Score**: The application's ZDX Score from 1 to 100, with 1 being the lowest and 100 being the highest. To learn more, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).
- **Active Users**: The number of active users accessing the application.
- **RUM Users**: The number of RUM users accessing the RUM-enabled application.
- **Impacted Geolocations**: The geolocation most impacted by the application. These areas are where users are accessing the application. ZDX uses a device's location service to determine a user's location. It takes the longitude and latitude coordinates from the location service and compares them to Zscaler's geographic IP database. That information is then listed, by corresponding major cities and towns, in the ZDX Admin Portal. If a user's latitude and longitude are closer to the center of another city, instead of their own city, the user's location might be misidentified as connecting from the neighboring city. If the location service is not enabled on the device, then ZDX uses the device's IP address to determine the location. ZDX currently supports Windows, macOS, Android, Android on ChromeOS, and iOS devices only.
- **Impacted Zscaler Locations**: The Zscaler location most impacted by the application.
- **Impacted Departments**: The department most impacted by the application.

[Image: View the Applications Overview page]

[Image: Filter drop-down in the Applications Dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-data-explorer-views","lastmod":"2026-06-11T18:17Z","nid":"1487211"} -->
## Monitoring Data Explorer Views

- Source: https://help.zscaler.com/zdx/monitoring-data-explorer-views
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring Data Explorer Views
- Last modified: 2026-06-11T18:17Z
- Summary: Information about monitoring the Data Explorer views configured for ZDX.

Data Explorer offers the flexibility to build and organize your own customized views of applications and metrics. This flexibility can help provide context when analyzing and correlating data across your organization.

Make sure you meet the feature prerequisites to monitor Data Explorer views. To learn more, see [Configuring Data Explorer Views](https://help.zscaler.com/zdx/configuring-data-explorer-views).

To monitor Data Explorer views, go to **Analytics** > **Digital Experience** > **Reports** > **Data Explorer**. The Data Explorer table provides the following information:

1. **Name**: The name of the configured view.
2. **Data Source**: Indicates the view is for End User Monitoring or Managed Monitoring.
3. **Applications**: The applications included within the view.
4. **Probes**: The associated probes (if the Data Source is for Managed Monitoring).
5. **Metrics**: The metrics included within the view.
6. **Aggregation**: The Aggregation type across all users, designated as Average, Minimum, or Maximum.
7. **Created By**: The admin who created the view.
8. **Last Updated On**: The date and time when the view was last edited or created.

[Image: Table of Data Explorer views]

In addition to Search, the following actions are available within the Data Explorer table:

- [Create a Data Explorer view](https://help.zscaler.com/zdx/configuring-data-explorer-views).
- Click a name to display its Data Explorer view. For details about the interaction and format of Data Explorer charts, see [Viewing System-Generated Reports](https://help.zscaler.com/zdx/viewing-system-generated-reports).
- [Edit or delete a Data Explorer view](https://help.zscaler.com/zdx/configuring-data-explorer-views).

See image.

[Image: Data Explorer table actions]

Only the Super Admin, or the admin who created the view, can edit or delete a configured Data Explorer view.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-device-health-dashboard","lastmod":"2026-06-04T14:48Z","nid":"1529520"} -->
## Monitoring the Device Health Dashboard

- Source: https://help.zscaler.com/zdx/monitoring-device-health-dashboard
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring the Device Health Dashboard
- Last modified: 2026-06-04T14:48Z
- Summary: Information on devices and their Device Health Score to determine the overall device health across an organization.

The Device Health Dashboard uses existing user and device data to create a comprehensive device health scoreboard that monitors the performance of Windows and macOS devices. The dashboard provides useful data in evaluating trends to identify and understand the root causes of poor-performing devices. Then you can proactively collect device data and identify which devices require an IT admin's attention to remedy the device's poor performance (e.g., high CPU usage). With a dedicated dashboard, you can target hardware upgrades based on the device's usage for greater cost-effectiveness.

The Device Health Score provides a comprehensive view of struggling devices across an entire organization, department, user group, or location. The score is determined by individual contributing metrics (e.g., CPU usage, memory usage, disk usage). The Device Health Scores are categorized as:

- **Good**: The usage profile has an acceptable Device Health Score and ranges from 66-100. The color for this range is green.
- **Okay**: The usage profile has an acceptable Device Health Score and ranges from 34-65. The color for this range is amber.
- **Poor**: The usage profile has a below an acceptable Device Health Score and ranges from 0-33. The color for this range is red.

All scores are rounded to the nearest whole number.

## Prerequisites

To monitor device performance within the Device Health dashboard, ensure:

- Your ZDX subscription level supports monitoring the Device Health dashboard. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Your ZDX role has the proper permission level. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Viewing the Device Health Dashboard

To access the Device Health Dashboard, go to **Dashboard** > **Device Health Dashboard**. You can view Thresholds to understand the device metrics.

Access the Summary, Device List, or Hardware Analysis tab to monitor device performance at varying levels in your organization.

### Summary

The Summary provides device health analysis, a geographical map of impacted devices, and profiles based on device usage. You can filter by **Vendors**, **Models**, **Profiles**, **Operating System**, **Geolocations**, **User Groups**, or **Departments**. Time range options are available in increments from the previous **2 Hours** to **48 Hours**, or a **Custom Range** within the last 14 days. Use the time range filter and page filters to help narrow your scope of information.

You can view the following:

1. **Organization Device Health Score**: Displays the overall health of devices across the organization by using key performance indicators (e.g., CPU Usage, Memory Usage, Disk Usage).
2. **Impacted Device Over Time**: View how devices are impacted over time. Switch between the **Impacted Device** and **Device Health Trend**views. You can click a data point to view the Health Score and impacted devices' distribution for the selected time range.
  - **Impacted Device**: Displays the number of impacted devices over time.
  - **Device Health Trend**: Displays the health of devices using the categories of Good, Okay, and Poor devices. See image.
3. **Device Health by Region**: A map to highlight the most impacted geolocations with a high number of impacted devices with poor experience.
4. **Useful Links**: Navigate to additional device monitoring data pages on the ZDX Admin Portal.
5. **Hardware Usage Analysis**: Devices are categorized into usage profiles based on their average CPU and memory usage.
6. **Device Count By Usage Profile**: The number of impacted devices are categorized into Light, Normal, and Power based on their device usage profile.
7. **Device Count by Hardware Profile**: The number of impacted devices are categorized into Low, Standard, and High based on the hardware profiles,

See image.

### Device List

The Device List provides an exportable list of impacted devices for viewing purposes.

You can filter by **Device Scores**, **Vendors**, **Models**, **Profiles**, **Operating System**, **Geolocations**, **User Groups**, **Departments**,**Users**, or **Devices**. Time range options are available in increments from the previous **2 Hours** to **48 Hours**, or a **Custom Range** within the last 14 days. Use the time range filter and page filters to help narrow your scope of information.

On the **Device List** tab, you can view a list of impacted devices with the following information:

- **Name**: The name of the device. Clicking the device name allows you to [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details).
- **User**: The user associated with the device name. Clicking the user allows you to [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details).
- **Device Health**: The Device Health Score and its classification (**Poor**, **Okay**,or **Good**).
- **User Groups**: The user group of the user.
- **Geolocations**: The geolocation of the user.
- **OS**: The operating system of the device.
- **Hardware Profile**: The number of devices and their hardware profile of RAM, Disk Size, Disk Type, CPU speed, number of cores, and logical processors. The hardware profiles are categorized into:
  - **Low**: The device uses a lower than acceptable hardware configuration.
  - **Standard**: The device uses an acceptable hardware configuration.
  - **High**: The device uses a higher than acceptable hardware configuration.
- **Usage Profile**: The number of devices based on CPU and memory usage over the previous 14 days and based on the OS. The usage profiles are categorized into: There might be inaccurate metrics or scores due to insufficient data if the device is unavailable for the majority of the time. You can opt to hide these data points.
  - **Light**: The device uses a lower than acceptable amount of processing usage.
  - **Normal**: The device uses an acceptable amount of processing usage.
  - **Power**: The device uses a higher than acceptable amount of processing usage.

You can export the Device List as a CSV file.

See image.

#### Contributing Factors

You can configure the view of the Device List if you click **Select Contributing Factors**.

Instead of the **Hardware Profile** and **Usage Profile**, you see in each view:

- **Device Vendor**: The hardware vendor of the device.
- **Hardware Model**: The hardware model number.

Each Contributing Factor view adds additional information about the factor on the Device List:

- **CPU Usage**: View the **CPU Usage Score** that reflects the device's CPU usage.
- **Memory Usage**: View the **Memory Usage Score** that reflects the device's RAM usage.
- **Disk Usage**: View the **Disk Usage Score** to reflect the device's disk usage.
- **Average Disk Queue Length**: View the **Average Disk Queue Length Score** that reflects disk queue length as a metric for disk I/O performance.
- **Battery**: View the **Battery Score** that reflects the device's battery usage performance.
- **Wi-Fi Signal Quality**: View the **Wi-Fi Signal Quality Score** that reflects the Wi-Fi performance that the device is connected to.
- **System Crashes**: View the following key metrics about system crashes on the device:
  - **Unexpected Shutdowns**: The number of unexpected shutdowns the device experienced in the selected time range.
  - **Unexpected Reboots**: The number of unexpected reboots the device experienced in the selected time range.
  - **Total System Crashes**: The total number of system crashes the device experienced in the selected time range.
  - **System Crashes Score**: The calculated score for the device system's health.
- **Software Crashes**: View the following key metrics about software crashes on the device:
  - **Software Crashes**: The number of software crashes the device experienced in the selected time range.
  - **Software Hangs**: The number of software hangs the device experienced in the selected time range.
  - **Total Software Issues**: The total number of software issues when the device experienced in the selected time range.
  - **Software Issues Score**: The calculated score for software stability and performance based on the number of software issues (i.e., software crashes or software hangs).

Click **Clear Selection** to reset to the default view.

When you are on a **Contributing Factor** view, and you see inaccurate scores, you can hide them by enabling the toggle. You can then focus on which scores are accurate.

See image.

### Hardware Analysis

View Hardware Analysis to understand the profiling of hardware and usage. Devices are categorized into profiles based on real-time usage and current hardware resources. Hardware Analysis includes:

- **Usage Analysis**: Comprehensive analysis of device usage (e.g., CPU usage, memory usage) and the number of impacted devices.
- **Inventory**: Holistic view of your organization's current hardware information (e.g., OS, vendor, memory, disk).

You can switch between the **Usage Analysis** and **Inventory** views.

#### Usage Analysis

Usage Analysis provides a comprehensive analysis of device usage (e.g., hardware usage, CPU usage, memory usage) and the number of impacted devices. This information is useful in identifying devices that are using a high number of resources based on their usage. An IT admin can focus and analyze the data to determine if a remediation solution is required. The Usage Analysis analyzes every 7 days, and you can look up to the previous 14 days of data.

You can filter by **Vendors**, **Models**, **Profiles**, **Operating System**, **Geolocations**, **User Groups**, or **Departments**.

You can view:

- **Hardware Usage Analysis**: The hardware profiles and usage profiles for each device. A hardware profile is based on the hardware allocation, while a usage profile is based on the users and device usage trends. You can view when the last hardware analysis occurred. Each device is categorized based on its hardware profile and usage profile as follows:
  - **Over Provisioned**: A device has a higher number of acceptable hardware resources. This indicates there is an inefficient use of resources allocated and there is an opportunity to optimize resource allocation to reduce costs or redistribute resources. The color for this range is amber.
  - **Right Sized**: A device is right sized when the acceptable amount of resources matches its usage level. This indicates an efficient allocation of resources. The color for this range is green.
  - **Under Provisioned**: A device does not have enough resources for its current usage level. This indicates potential performance issues. Zscaler recommends upgrading hardware to adjust for the device's necessary resources. The color for this range is red.
- **Device Count By Usage Profile** or **Hardware Profile**: Switch between **Usage Profile** or **Hardware Profile** views. Each profile is categorized using predefined thresholds for comparison.
  - **Usage Profile**: The number of devices based on CPU and memory usage over the previous 14 days and based on the OS. The usage profiles are categorized into:
    - **Light**: The device uses a lower than acceptable amount of processing usage.
    - **Normal**: The device uses an acceptable amount of processing usage.
    - **Power**: The device uses a higher than acceptable amount of processing usage.
  - **Hardware Profile**: The number of devices and their hardware profile of RAM, Disk Size, Disk Type, CPU speed, number of cores, and logical processors. The hardware profiles are categorized into:
    - **Low**: The device uses a lower than acceptable hardware configuration.
    - **Standard**: The device uses an acceptable hardware configuration.
    - **High**: The device uses a higher than acceptable hardware configuration.
- **Device Distribution**: View hardware distribution based on the number of devices.
  - Switch between **Vendor**, **Model**, **CPU**, **Memory**, or **Disk** views.
    - **Vendor**: View the distribution of devices across vendors.
    - **Model**: View the distribution of devices across device models.
    - **CPU**: View the distribution of devices with CPUs.
    - **Memory**: View the distribution of devices with RAM allocation.
    - **Disk**: View the distribution of devices with disk storage allocated.
  - Switch between **Department** and **User Groups** views.
    - **Department**: View the number of devices associated with a department.
    - **User Groups**: view the number of devices associated with a user group.
- A Device List table with the following information: There might be inaccurate metrics or scores due to insufficient data if the device is unavailable for the majority of the time. You can disable **Hide Insufficient Metrics** to see these data points on the Device List.
  - **Name**: The name of the device. Clicking the device name allows you to [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details).
  - **User**: The user associated with the device name. Clicking the user allows you to [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details).
  - **Device Health**: The Device Health Score and its classification.
  - **User Groups**: The user group of the user.
  - **OS**: The operating system of the device.
  - **Hardware Profile**: The hardware profile classification of the device.
  - **Usage Profile**: The usage profile classification of the device.

See image.

#### Inventory

Inventory allows you to view all current hardware information (e.g., **OS**, **Vendor**, **Memory**, **Disk**) and provide a holistic view of your organization's device inventory. The device data are gathered from the previous 14 days.

You can filter by **Vendors**, **Models**, **Operating System**, **Geolocations**, **User Groups**, or **Departments**.

You can view:

- **Device Distribution**: View hardware distribution based on the number of devices.
  - Switch between the **OS**, **Vendor**, or **Model** views.
    - **OS**: View a pie chart of the number of devices associated with an OS.
    - **Vendor**: View a bar chart of the number of devices associated with a vendor.
    - **Model**: View a bar chart of the number of devices and their respective device model.
  - Switch between **Department**, **User Groups**, **CPU**, **Memory**, or **Disk** views.
    - **Department**: View a bar chart of the number of devices associated with a department.
    - **User Groups**: View a bar chart of the number of devices associated with a user group.
    - **CPU**: View a bar chart of the distribution of devices with CPUs.
    - **Memory**: View a bar chart of the distribution of devices with RAM allocation.
    - **Disk**: View a bar chart of the distribution of devices with disk storage allocated.
- A Device List table with the following information: You can export to download a CSV file of the device list.
  - **Name**: The name of the device. Clicking the device name allows you to [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details).
  - **User**: The user associated with the device name. Clicking the user allows you to [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details).
  - **Operating System**: The operating system of the device.
  - **Geo Location**: The geolocation of the device.
  - **Device Vendor**: The vendor of the device.
  - **Device Model**: The model name of the device.
  - **CPU**: The CPU of the device.
  - **Memory (RAM)**: The amount of memory RAM of the device.
  - **Disk Size**: The storage disk size of the device.
  - **Disk Type**: The type of storage disk for the device.

See image.

### Thresholds

You can view the thresholds for Device Scoring or Hardware Performance to understand how scoring is determined by clicking the Thresholds icon ([Image: Click the Thresholds icon]) located in the upper-right corner of the page.

- Device Scoring
- Hardware Performance

The key performance metrics are determined by the optimal values for what is considered Good, Warning, or Critical health. The thresholds are predetermined for the following metrics:

- **CPU Usage**: The percentage of processing power used by the device's CPU.
- **Memory Usage**: The percentage of RAM used by a device for a specific application.
- **Disk Usage**: The percentage of disk space used by a device.
- **Average Disk Queue Length**: The average amount of disk read and write requests the device experiences.
- **Battery**: The percentage of battery power used for the device.
- **Wi-Fi Signal Quality**: The percentage of Wi-Fi signal quality the device is connected to.
- **System Crashes**: The number of system crashes a device experiences.
- **Software Crashes**: The number of software crashes a device experiences.

See image.

The hardware profile is calculated based on the CPU speed, RAM, disk type, number of cores, and logical processors to distribute each profile into:

- **Low**: The hardware profile is categorized for less than 10% of the organization's distribution.
- **Standard**: The hardware profile is categorized for greater than 10% and below 90% of the organization's distribution.
- **High**: The hardware profile is categorized for greater than 90% of the organization's distribution.

Each hardware profile is compared to other devices within the organization.

The Utilization Profile Calculation calculates and categorizes based on the device's CPU and memory usage for the previous 14 days. The Utilization Profile Calculation distributes each usage profile into:

- **Light Usage**: The usage profile is considered acceptable when the device's CPU and memory usage is below 80%
- **Normal Usage**: The usage profile is considered moderate and acceptable when the device's CPU and memory usage range from 80 to 90%.
- **Power Usage**: The usage profile is considered high and strained when the device's CPU and memory usage is above 90%.

See image.

## Caveat

ZDX collects data for single-user access per device. If there are multiple users on a device, then ZDX collects data for each user and the associated device. This scenario increases the device data aggregation.

For example:

- User A is on Device X.
- User B is also on Device X.
- User A and User B access Device X at different times.

In this scenario, ZDX collects two separate device data when:

- User A is on Device X.
- User B is on Device X.

[Image: View the Summary tab]

[Image: Analyze the device usage]

[Image: View the inventory of your devices]

[Image: View the Device Scoring thresholds]

[Image: View the thresholds for Hardware Performance]

[Image: View the Device List Tab]

[Image: Select a Contributing Factor to view]

[Image: View the Device Health Trend of impacted devices or click to view Health Score distribution]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-incidents-dashboard","lastmod":"2026-04-20T18:19Z","nid":"1459026"} -->
## Monitoring the Incidents Dashboard

- Source: https://help.zscaler.com/zdx/monitoring-incidents-dashboard
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring the Incidents Dashboard
- Last modified: 2026-04-20T18:19Z
- Summary: Information on the Incidents dashboard and accessible data within the Zscaler Admin Console.

The Incidents Dashboard displays incidents in 7 area types: Device, Wi-Fi, Last Mile ISP, Intermediate ISP, ZIA (Internet & SaaS) Public Service Edge, ZPA (Private Access), or Application. Incidents are issues that impact the device performance of multiple users. ZDX uses AI and machine learning (ML) to detect and identify incidents using the best metrics that correlate to the issues. The incidents displayed are based on the selected time range in the UI and show incidents over time, impacted users, and where on the map they occur.

## Prerequisites

To access the Incidents Dashboard, you must have the following:

- Your ZDX subscription level supports Incidents. To learn more, see [Ranges and Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- View Only permission for the Incidents Dashboard. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Incidents Dashboard

To access the Incidents Dashboard, go to **Analytics** > **Digital Experience** > **Alerts** > **Incidents**.

Use the filters on the Incidents Dashboard page to narrow your scope of Incidents information.

- **Type**: Select the incident type. The types are Device, Wi-Fi, Last Mile ISP, Intermediate ISP, ZIA Public Service Edge, ZPA, or Application.
- **Time Range**: Select a time range to view when incidents occurred. The default is 14 days.

You can view the total incidents and the total counts across the key metrics and impacted users.

- **Total Incidents**: The total number of incidents detected within a time range.
- **Impacted Users**: The number of impacted users from the total incidents within a time range.
- **Incidents Across Key Areas**: The distribution of incidents across the key areas. Click the **number** icon, or **text** under each key area to filter specifically for the selected incident type.

See image.

If no incidents are found, try one of the following actions:

- Select a different time range.
- Remove filters.
- Apply different filters.

### Incidents Over Time

The Incidents Over Time section displays data for incidents and impacted users to provide the following information:

- **Incidents Over Time**: The number of incidents that have occurred based on the impacted devices within a time range.
- **Impacted Users Over Time**: The number of impacted users from the incident over time. If there are no impacted users, no data is shown.

See image.

### Incidents By Epicenters

The Incidents By Epicenters displays all incidents that have occurred within the time range on a map. Different types of incidents: Device, Wi-Fi, Last Mile ISP, Intermediate ISP, ZIA Public Service Edge, ZPA, or Application are displayed using a different icon to represent the types of incidents. After an incident is positioned on the map in an area, an epicenter is defined at the center of the incident.

You can review the Incidents by Epicenters list with the following information:

- **Type**: The types are Device, Wi-Fi, Last Mile ISP, Intermediate ISP, ZIA Public Service Edge, ZPA, or Application.
- **Epicenter**: Represents the center of the incident. Depending on the type, the epicenter is displayed as:
  - **Device**: The geographical area of impacted Windows devices with system software hangs and crashes.
  - **Wi-Fi**: The geographical area of impacted service set identifiers (SSIDs).
  - **Last Mile ISP**: The geographical area of impacted users with the ISP. Click **Last Mile ISP** under **Incidents Across Key Areas** or the **View** icon underneath the **Incidents by Epicenter** map to view what subtype of Last Mile ISP Incident occurred.
    - **Blackout**: A subtype of Last Mile ISP. The area of impacted users with connectivity issues on the Last Mile ISP.
    - **Brownout**: A subtype of Last Mile ISP. The area of impacted users with a performance degradation on the Last Mile ISP.
  - **Intermediate ISP**: Represents network issues that occur between ISPs or within an ISP backbone. Incidents can be Internal (affected hops inside a single ISP/ASN) , between ISP-ISP (affected hops between two ISPs/ASNs at the peering point), or peering ISP to Zscaler Data Center. If probes are routed through a Zscaler Data Center, the epicenter is the data center location. If probes are direct, the epicenter is the approximate geolocation of affected users.
  - **ZIA Public Service Edge**: The location of the Public Service Edge at the Zscaler Data Center.
  - **ZPA**: The geographical area of impacted users with ZPA.
    - **ZPA App Connector**: The location of the App Connector at the Zscaler Data Center.
    - **ZPA Public Service Edge**: The location of the Public Service Edge at the Zscaler Data Center.
  - **Application**: The area of impacted users and can go across multiple countries.
- **Total User(s)**: See the total number of users within the incident.
- **Impacted Users**: See the number of impacted users within the incident.
- **Started On**: See the date and time this incident started.
- **Ended On**: See the date and time this incident ended.
- **View**: View the selected incident for more granular details.

See image.

#### Viewing Incident Details

The Incident Details page provides granular information about a specific incident.

- Incident Details
- Impact
- Impacted Users by Geolocations
- Top Impacted Users
- Key Metrics

Click **Set Up Alert** () to configure an alert rule for an incident. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule). Click **Share Snapshot** to capture a static image of the current incident details view for sharing or reporting.

See image.

Provides an overview of the selected incident with the following details:

- **Type**: The type of incident.
- **Severity**: The level of severity of the incident.
- **Epicenter**: Represents the center of the incident depending on the type. Depending on the type, the epicenter is displayed as:
  - **Device**: The geographical area of impacted Windows devices with system software hangs and crashes.
    - **System Software Hangs**: A subtype of Device. The area of impacted Windows devices with system software hangs.
    - **System Software Crashes**: A subtype of Device. The area of impacted Windows devices with system software crashes.
  - **Wi-Fi**: The geographical area of impacted SSIDs. A Wi-Fi incident includes the selection of Wi-Fi Access Point information.
  - **Last Mile ISP**: The geographical area of impacted users with the ISP.
    - **Blackout**: A subtype of Last Mile ISP. The area of impacted users with connectivity issues on the Last Mile ISP.
    - **Brownout**: A subtype of Last Mile ISP. The area of impacted users with a performance degradation on the Last Mile ISP.
  - **Intermediate ISP**: The geographical area of impacted users when degradation occurs between ISPs or within an ISP backbone. Incidents can be Internal or Peering; the epicenter is shown at the Zscaler Data Center if probes route via a data center, or at the approximate user location if probes are direct.
  - **ZIA Public Service Edge**: The location of the Public Service Edge for Internet & SaaS at the Zscaler Data Center.
  - **ZPA**: The geographical area of impacted users with Private Access.
    - **ZPA App Connector**: The location of the App Connector fat the Zscaler Data Center.
    - **ZPA Public Service Edge**: The location of the Public Service Edge at the Zscaler Data Center.
  - **Application**: The area of impacted users and can go across multiple countries.
- **Started On**: The date and time the incident started.
- **Ended On**: The date and time the incident ended.
- **Duration**: The duration of the incident.

See image.

Displays the impacted number of users, geolocations, and applications.

- : The number of impacted users.
- : The number of impacted geolocations.
- : The number of applications.

A map of the impacted users' geolocations. You can zoom in and out of the map to better view regions of interest.

A list of the Top Impacted Users. Click **View All Impacted Users** to view and verify more impacted users.

The Top Impacted Users list displays up to 11 impacted users.

Displays key metrics based on the Incident type.

- Device
- Wi-Fi
- Last Mile ISP
- Intermediate ISP
- ZIA Public Service Edge
- ZPA
- Application

New Intermediate ISP Key Metrics section

Intermediate ISP key metrics provide visibility into degradation occurring between ISPs or within an ISP backbone.

- **Impacted Intermediate ISP:**Based on the affected leg, a network path is plotted to display where the incident occurred.
- **ZDX Score for Impacted Users**: The aggregated ZDX Score across impacted users and their configured applications.
- **ISP Internal/Peering Latency**: Latency measured on internal ISP hops or across peering points.
- **End-to-End Latency**: The overall latency across the path.
- **Leg Latency**: The latency on the affected leg.
- **Packet Loss**: The number of lost packets along the affected leg.
- **DNS Resolution Time**: Time to resolve DNS entries observed on the path.

There are two types of device incidents with different key metrics that provide analytical data of impacted Windows devices with anomalous behaviors that are caused by system software hangs or crashes:

- System Software Hangs
- System Software Crashes

System Software Crashes key metrics provide analytical data of where impacted Windows devices experience no computer activity because the software has crashed.

- **Impacted OSs**: The distribution of impacted operating systems.
- **Impacted Software**: The distribution of impacted software.
- **CPU Usage**: The percentage of CPU usage for impacted Windows devices.
- **Memory Usage**: The percentage of memory usage for impacted Windows devices.
- **Event Trend**: The number of system software crashes for impacted Windows devices that occur over time.

System Software Hangs key metrics provide analytical data on where impacted Windows devices have become unresponsive to user input on applications despite ongoing computer activity.

- **Impacted OSs**: The distribution of impacted operating systems.
- **Impacted Software**: The distribution of impacted software.
- **CPU Usage**: The percentage of CPU usage for impacted Windows devices.
- **Memory Usage**: The percentage of memory usage for impacted Windows devices.
- **Event Trend**: The number of system software hangs for impacted Windows devices that occur over time.

Application key metrics provide an overview of an application and its impacted users.

- **ZDX Score**: The ZDX Score of the application.
- **HTTP Errors**: The number of HTTP errors the application has encountered over time.
- **TTFB-PFT Ratio**: The ratio between the server response time (time to first byte) and the time to load the page (page fetch time).
- **Last Server Leg Latency**: The latency from the Public Service Edge to the application.
- **Number of Redirects**: The number of redirections going through the application over time.

Depending on whether it's a Blackout or Brownout, you get different key metrics.

- Blackout
- Brownout

Wi-Fi key metrics provide an overview of the signal strength and latency of Wi-Fi access points.

- **ZDX Score Drop**: The ZDX Score variation through the Wi-Fi access point.
- **Maximum Wi-Fi Access Point Latency**: The maximum Wi-Fi Access Point Latency.
- **Average Wi-Fi Access Point Latency**: The average Wi-Fi Access Point Latency.
- **Packet Loss at Wi-Fi Access Point (Hop 1)**: The packet loss at the first Wi-Fi Access Point.
- **Packet Loss after Wi-Fi Access Point (Hop 2)**: The packet loss after the 1st Wi-Fi Access Point.
- **Packet Loss after Wi-Fi Access Point (Hop 3)**: The packet loss after the 2nd Wi-Fi Access Point.
- **Packet Loss after Wi-Fi Access Point (Hop 4)**: The packet loss after the 3rd Wi-Fi Access Point.
- **Wi-Fi Signal Strength**: The average signal strength of users going through the Wi-Fi Access Point.

ZIA Public Service Edge provides an overview on ZIA transactions, connectivity, and latency for impacted users.

- **ZDX Score for Impacted Users**: The ZDX Score for Impacted Users is calculated across each impacted user and their aggregated ZDX Score from all their configured applications.
- **First Hop Latency Around SME**: The time calculated, from the ZIA Public Service Edge perspective, across all the users of the SME going through the affected Zscaler Data Center.
- **Second Hop Latency Around SME**: The average Second Hop Latency, from the ZIA Public Service Edge perspective, across all the users of the SME going through the affected Zscaler Data Center.
- **ZIA Transactions**: Displays the number of ZIA transactions going through the affected Zscaler Data Center.
- **ZIA Connectivity Errors**: Displays the number of ZIA errors connecting to the affected Zscaler Data Center.
- **Cloud Path Probe Errors**: The number of Cloud Path probe errors at the Zscaler Data Center.
- **Web Probe Errors**: The number of Web probe errors at the Zscaler Data Center.

Depending on the Service Edge, you get different key metrics.

- ZPA App Connector
- ZPA Public Service Edge

ZPA App Connector key metrics provides an overview of App Connector traffic, connectivity, and latency for impacted users.

- **ZDX Score**: The ZDX Score is calculated across each impacted user and their aggregated ZDX Score from all their configured applications.
- **First Hop Latency Around ZPA**: The average First Hop Latency across all the users going through Private Access.
- **Second Hop Latency Around ZPA**: The average Second Hop Latency across all the users going through Private Access.
- **ZPA App Connector Cloud Path Probe Errors**: The number of Cloud Path probe errors at the Zscaler Data Center.

ZPA Public Service Edge key metrics provides an overview of ZPA Public Service Edge traffic, connectivity, and latency for impacted users.

- **ZDX Score**: The ZDX Score is calculated across each impacted user and their aggregated ZDX Score from all their configured applications.
- **First Hop Latency around ZPA**: The average First Hop Latency, from the Public Service Edge perspective, across all the users going through Private Access.
- **Second Hop Latency around ZPA**: The average Second Hop Latency, from the Public Service Edge perspective, across all the users going through Private Access.
- **ZPA Public Service Edge Cloud Path Probe Errors**: The number of Cloud Path probe errors at the Zscaler Data Center.
- **ZPA Public Service Edge Web Probe Errors**: The number of Public Service Edge Web probe errors at the Zscaler Data Center.

Blackout key metrics provides an overview of connectivity issues with an ISP.

- **Application Score**: The ZDX Score of the application.
- **All Probe Errors**: The total number of probe errors.
- **DNS Resolution Time**: The amount of time it takes for a DNS to resolve DNS entries.
- **Cloud Path Probe Errors**: The number of Cloud Path probe errors.

Brownout key metrics provides an overview of performance degradation with an ISP.

- **ZDX Score Drop**: The shaded region indicates the ZDX Score Drop for the Last Mile ISP incident.
- **DNS Latency**: The amount of time DNS takes to resolve for impacted users from the Last Mile ISP incident.
- **Leg Latency**: The latency for impacted legs from the Last Mile ISP incident.
- **Packet Loss**: The number of packets lost from the Last Mile ISP incident.

[Image: Incidents Overview Summary]

[Image: Incidents over Time]

[Image: Incidents by Epicenter]

Depending on the type of incident you have, you have different key metrics. The following is an example of a selected Device incident with a subtype of System Software Crashes.

[Image: Device - System Software Crashes Incident]

[Image: Incident Details]

## Understanding Incident Type Metrics and Thresholds

Each incident type has a different set of metrics and thresholds to monitor specific device behaviors. These metrics and thresholds are predetermined to provide insights into your organization's impacted devices.

In order to be considered an incident, the following must be considered to categorize the event as an incident:

- Incident: The incident type or subtype.
- Minimum Devices: The minimum number of impacted devices with bad scores.
- Dimensions: The dimensions that determine an impacted area.
- Key Metrics: The key metrics to describe the incident's digital experience.

| Type - Subtype | Minimum Devices | Dimensions | Key Metrics |
| --- | --- | --- | --- |
| Application | 50 | Country; Application | ZDX Score; HTTP errors; TTFB-PFT Ratio; Last Server Leg Latency; Number of redirects; TTFB:PFT ratio |
| Last Mile ISP - Blackout | 20 | Geohash; ISP | Application Score; All Probe Errors; DNS Resolution Time; Cloud Path Probe Errors |
| Last Mile ISP - Brownout | Minimum 10 across all customers; Minimum 5 per customer | Geohash; ISP | ZDX Score Drop; DNS Latency; Leg Latency; Packet Loss |
| Intermediate ISP | Minimum 10 users per ASN - ASN edge in Network Intelligence; Minimum 10 users per customer | Geohash; ISP; CCID; ASN; ZIA Data Center | ZDX Score for Impacted Users; ISP Internal/Peering Latency; End-to-End Latency; Leg Latency; Packet Loss; DNS Resolution Time; Intermediate ISP Latency |
| ZIA Public Service Edge | Minimum 100 across all customers; Minimum 10 per customer | ZIA Data Center | ZDX Score for Impacted Users; First Hop Latency around SME; Second Hop Latency around SME; ZIA Transactions; ZIA Connectivity Errors; Cloud Path Probe Errors; Web Probe Errors |
| Wi-Fi | 4 | ZIA Location; SSID | ZDX Score; First Hop Latency around ZPA; Second Hop Latency around ZPA; ZPA App Connector Cloud Path Probe Errors |
| ZPA - App Connector | Minimum 10 per customer per App Connector | App Connector | ZDX Score; First Hop Latency around ZPA; Second Hop Latency around ZPA; ZPA App Connector Cloud Path Probe Errors |
| ZPA - Public Service Edge | Minimum 100 across all customers; Minimum 10 per customer | ZPA Data Center | ZDX Score; First Hop Latency around ZPA; Second Hop Latency around ZPA; ZPA Public Service Edge Cloud Path Probe Errors; ZPA Public Service Edge Web Probe Errors |
| Device - System Software Crash | 50 | Devices | Impacted OSs; Impacted Software; CPU Usage; Memory Usage; Event Trend |
| Device - System Software Hang | 50 | Devices | Impacted OSs; Impacted Software; CPU Usage; Memory Usage; Event Trend |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-network-intelligence-dashboard","lastmod":"2026-07-13T13:35Z","nid":"1529289"} -->
## Monitoring the Network Intelligence Dashboard

- Source: https://help.zscaler.com/zdx/monitoring-network-intelligence-dashboard
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring the Network Intelligence Dashboard
- Last modified: 2026-07-13T13:35Z
- Summary: Information on the Network Intelligence Dashboard and accessible data within the Zscaler Admin Console.

Network Intelligence provides end-to-end multi-path network visibility from Last Mile Internet Service Providers (ISPs) to Zero Trust Exchange to applications. ZDX runs Cloud Path probes to gather network metrics (e.g., network latency, packet loss) to establish a baseline for network latency and compares the network performance against the baseline. ZDX detects and analyzes network anomalies to create a deep analysis and allows you to investigate root causes. You can analyze and pinpoint Last Mile or Intermediate ISP issues, understand root causes, and assess their impact on end users using ML-based algorithms to extract patterns and identify anomalies, and then you can observe and determine the most optimal routing paths to data centers. With all this knowledge at your disposal, you can take proactive measures to resolve network issues to improve the overall organization's digital experience.

## Prerequisites

To access the Network Intelligence Dashboard, you must have the following:

- Your ZDX subscription level supports Network Intelligence. To learn more, see [Ranges and Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- View Only permission for the Network Intelligence Dashboard. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

To access the Network Intelligence Dashboard, go to **Analytics** > **Digital Experience** > **Networks** > **Network Intelligence**.

## Overview of Network Health

The Network Intelligence dashboard opens with a high-level overview of network health. This section displays recent network anomalies for the last 14 days, cities with the highest user count, and top Autonomous System Numbers (ASNs) showing elevated latency. Together, these widgets summarize where and how network performance is impacted across your organization.

- **Network Anomalies for Last 14 Days**: Displays total anomalies per 24-hour period, highlighting severe and medium issues across geolocations with more than 50 users. Selecting a bar adjusts the dashboard’s time range to that day.
- **Top 5 Cities by User Count**: Lists the cities with the highest user counts and corresponding anomaly levels based on the time range selected. Clicking a city highlights it across other charts and widgets but does not set a global filter.
- **Top 5 High-Latency ASNs**: Shows the ASNs with the highest measured latency compared to a baseline. Taller red bars indicate above-baseline latency, while thinner marks show near-baseline performance.

See image.

[Image: Top Summary widgets showing 14-day anomalies, top cities, and high-latency ASNs]

Interactions in these widgets do not apply to global filters. Use the map or filter bar to adjust the dashboard scope.

## Adjusting Network Intelligence View

To adjust your Network Intelligence Dashboard view, you can:

- Filter by **Zero Trust Exchange**, **Application**, **Zscaler Locations**, or **Geolocations**. When you select the **Application** filter, the map displays all geolocations where users are accessing the selected application and does not contain flickering anomalies. Instead, anomalies are detected at runtime and you must select a geolocation on the map to populate the anomaly data.
- Select a time range (**2 Hours**, **4 Hours**, **6 Hours**, **12 Hours**, **24 Hours**, **48 Hours**, **Current**, **Custom**).

You can view the total of each impacted geolocation when the network latency deviates from the baseline and is considered an anomaly.

On the map, you can:

- View highlighted geolocations with a rippling effect which indicate a network latency deviation from the performance baseline. Each geolocation is categorized into green, orange, or red. Click a flickering anomaly location on the map to zoom in to the location.
- Use the left-side panel to navigate anomalies by region. It lists**Continents**, then **Countries**, then **Cities**, each sorted by anomaly count for faster navigation. Click a continent to expand its countries; click a country to expand its cities. Selecting any item filters the map to that scope.
- Pan around, zoom in, or zoom out to adjust the view. Click **Reset** to revert to the default world map view.

See image.

[Image: Regions by ZDX Score and Network Anomalies]

A rippling effect appears on the map for geolocations with more than 50 users. For geolocations with fewer than 50 users, anomalies are detected at runtime and do not have the rippling effect.

## Viewing Network Anomalies

After selecting a network anomaly geolocation from the drill-down view, you can view:

- User Experience & Network Insights
- ISP or ASN View
- Performance Tables

If you do not select a network anomaly geolocation from the drill-down view, the network data is not populated.

### Leg Views

You can switch views for the following legs and their network metrics, pathways, and performance tables:

- **Client to Zero Trust Exchange**: From the client's ISP to the Zero Trust Exchange.
  - **Forward Path**: From the ISP to the Zero Trust Exchange data center.
  - **Reverse Path**: From Zero Trust Exchange to the Last Mile ISP.
- **Zero Trust Exchange to Application**: From the Zero Trust Exchange to the application.
- **Client to Application (Direct)**: From the direct path to the application.
- **Client to Application**: From the client's ISP to the application.

[Image: Select a leg to view network metrics, pathways, and performance tables.]

#### User Experience & Network Insights

The User Experience & Network Insights provides multiple charts about network anomalies and metrics for network latency, probe count, probe errors, packet loss, and ZDX Score. The network latency and ZDX Score are used to indicate how users are impacted over time when the network latency is high.

See image.

From these insights, you can configure the following:

1. Filter to up to top 5 ISPs or Zero Trust Exchange with the highest number of probes depending on your view. Click **Reset** to default to the top 5 impacted.
2. Select metrics to display from the drop-down menu. Available metrics include the following:
  - **P50 Latency**: The 50th percentile latency over time.
  - **P95 Latency**: The 95th percentile latency over time.
  - **Probe Count**: The number of network probes available during the selected time frame. This displays up to the top 5 impacted probes.
  - **ZDX Score**: The number of users and their ZDX Score over time.
  - **Cloud Path Probe Errors**: The number of Cloud Path probe errors over time. This displays up to the top 5 impacted Cloud Path probes.
  - **P50 Packet Loss**: The 95th-percentile value of packet loss over time.
3. Switch views between ISP and Zero Trust Exchange.
  - ISP: View the top 5 ISPs with the highest number of probes and their ISP metrics. This is the default view.
  - Zero Trust Exchange: View the top 5 Zero Trust Exchanges based on the following metrics of all connected ISPs to the selected Zero Trust Exchange:
    - P50 latency
    - P95 latency
    - Total number of probes
4. Click **Compare** to view the historical comparison between different times or view geographical comparison between different locations. Up to 5 ISPs or locations are selected. See image.

The chart updates dynamically based on your selection.

If there is a network anomaly (red shade) and no users with poor ZDX Score (gray shade), then the network anomaly is not impacting the users' digital experience. For example, if the network latency goes from 10 to 40 ms, then this is considered an anomaly since it is 3 times the baseline of 10 ms. However, a network latency of 40 ms does not normally impact the users' digital experience.

#### ISP or ASN View

The ISP or ASN View displays a Sankey visualization for the aggregation of all the IP addresses that belong to the same ASN.

In the Sankey visualization, you can:

1. Switch between Forward Path or Reverse Path where you can see all the IP addresses connected to the ISP over the internet, and then you can determine if there is a network problem.
2. Drag and drop the time range slicer to focus on the selected time range.
3. Configure the Probe Count criteria to display pathways based on the number of probes.
4. Configure which pathways to highlight based on criteria selection (**Probe Count**, **Number of Users**, **Average Latency**, **P50 Latency**, **P90 Latency**, **P95 Latency**, **P50 Baseline**, **Severity**, **Average Leg Loss**, **IP Search**). **Probe Count** is the default. Click the **Add** icon to configure more criteria.
5. Hover over a pathway to view ASN details. You can click the ASN details or pathway to continuously display the ASN details.

See image.

When the line connections on the Sankey visualization are highlighted:

- **Yellow**: The IP addresses are impacted by at least 2 times the baseline with an average latency threshold between 50 ms and 100 ms. Then they are considered medium severity.
- **Red**: The IP addresses are impacted by at least 3 times the baseline with an average latency threshold above 100 ms. Then they are considered high severity.
- **Dotted Red**: Applications with a poor ZDX Score to indicate potential application-side impact.

If a baseline is not established, then the baseline uses the values of 50 ms (red) and 25 ms (yellow). If the IP addresses experience a latency threshold between 50 ms and 100 ms, then it is a medium (yellow) severity. If the IP addresses experience a latency threshold of more than 100 ms, then it is considered high (red) severity.

##### ASN Details

Click a pathway to view the ISP's ASN Details.

- **Intra ASN**: Metrics within the same ASN.
- **Inter ASN**: Metrics between two ASNs.
- **P50 Latency**: The 50th percentile latency for probes traversing this link during the selected time window. Half of the probes have latency at or below this value; half are higher.
- **Severity**: Indicates the level of latency severity based on the P50 Latency deviation from the baseline:
  - **High**:
    - P50 ≥ 3× baseline
    - P50 > 50 ms
    - Average leg latency > 100 ms
  - **Medium**:
    - P50 ≥ 2× and < 3× baseline
    - 50 ms > P50 > 100 ms
    - 50 ms > Average leg latency > 100 ms
  - **Low**:
    - P50 < 2× baseline
    - P50 < 50 ms
    - Average leg latency < 50 ms
- **Probe Count**: The total number of measurement probes sent to and from this ASN during the selected time interval.
- **Number of Users**: The count of unique end-users whose traffic passed through this ASN during the selected time window.
- **Average Latency**: The average latency of probes between ASNs or within the same ASN during the selected time interval.
- **P90 Latency**: The 90th percentile latency. 90% of probes have latency at or below this value; 10% are higher.
- **P95 Latency**: The 95th percentile latency. 95% of probes have latency at or below this value; 5% are higher.
- **Average Leg Latency**: The mean latency for individual legs of the path to and from the Zero Trust Exchange during the selected time window.
- **Leg Loss**: The percentage of packet loss for individual legs of the path to and from the Zero Trust Exchange during the selected time window.
- **Peer Impact Analysis**: Click **Peer Impact Analysis** to see if customers on the same Inter ASN path are also impacted and displays the number of impacted customers when applicable.
- **IP View**: Click IP View to understand which IP addresses are impacted and how they connect.
- **Deviation from Baseline**: Displays deviation from baseline metrics (P50, P90, P95, average latency, average leg latency, and leg loss) with up and down arrows.

See image.

[Image: Image of ASN details from selecting the ASN links]

##### IP View

You can click an ISP circle to view granular details on its network performance by clicking **IP View**.

View a Sankey visualization of all impacted IP addresses and their geolocation to observe their connections to the ISP. You can switch between the IP views:

- **IP by ASN**: View IP addresses based on how they connect to the ISP. See image.
- **IP by Geo**: View geolocations with IP addresses on how they connect to the ISP. See image.

Click **Return to ASN** to revert to the **ISP or ASN View**.

#### Performance Tables

The performance tables summarize how well the Last Mile ISP or application is doing. Depending on what your ISP or ASN View is, you might have one or both of the performance tables.

The ISP Performance table displays the top 200 Last Mile ISPs by ASN with the following information:

- **Last Mile ISP (ASN)**: The name of the Last Mile ISP and its associated ASN.
- **Users**: The total number of impacted users.
- **Avg Hops**: The average number of hops from the the user to the Last Mile ISP.
- **Avg Latency**: The average latency of the Last Mile ISP over the selected time range.
- **P50 Latency**: The latency for the 50th percentile of impacted users.
- **P95 Latency**: The latency for the 95th percentile of impacted users.
- **Baseline**: The established baseline for the Last Mile ISP from the previous 7 days when users access the Last Mile ISP.
- **Baseline Deviation**: The deviation from the baseline for the Last Mile ISP. This information determines the Last Mile ISP performance by comparing it to the baseline.
- **Zero Trust Exchange**: The names of the Zero Trust Exchange data centers connected to the ISP.

See image.

The ISP Performance Table is sorted by **Baseline Deviation** as the default to list the most deviated ISPs.

The Application Performance table displays the performance of applications that are accessed by users with the following information:

- **Applications**: The name of the application.
- **ZDX Score**: The ZDX Score of the application. To learn more, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).
- **Users**: The number of users accessing the application.
- **Avg Hop**: The average number of hops the application encounters.
- **Avg Latency**: The average latency the application experiences.
- **P50 Latency**: The network latency for the 50th percentile of impacted users.
- **P95 Latency**: The network latency for the 95th percentile of impacted users.
- **Baseline**: The established baseline from the previous 7 days when users access the application.
- **Baseline Deviation**: The deviation from the baseline that determines the performance of the application.

See image.

[Image: View the ISP Performance Table]

[Image: View the Application Performance table]

[Image: View Network Insights and User Experience metrics]

[Image: Configure the ISP or ASN View]

[Image: View Comparisons between 2 time ranges or locations]

[Image: IP Details in the IP by ASN View]

[Image: IP Details in the IP by Geo View]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-performance-dashboard","lastmod":"2026-04-03T15:36Z","nid":"1355786"} -->
## Monitoring the Performance Dashboard

- Source: https://help.zscaler.com/zdx/monitoring-performance-dashboard
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring the Performance Dashboard
- Last modified: 2026-04-03T15:36Z
- Summary: Information on the ZDX dashboard and widgets accessible within the Zscaler Admin Console.

The Performance Dashboard provides an overview about the Zscaler Digital Experience (ZDX) for your organization.

See image.

## Dashboard Tools

The Performance Dashboard allows you to:

- **View performance data over time**: Use the Time Range filter to choose a specific time range to view data. The selected period applies to all data within the dashboard. The default time range is**2 Hours**.
  - **Current**: View the most current ZDX Score captured within the previous 30 minutes.
  - **2 Hours** to **48 Hours**: Specify a time interval between 2 hours and 48 hours as shown in the drop-down menu.
  - **Custom**: Specify a custom time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments.
- **Filter data**: Click the filters to select options for Departments, Zscaler Locations, User Groups, Geolocations, Location Groups, Last Mile ISPs, and Operating System. Each filter allows you to include or exclude individual options. Click **Select All Displayed** to select all options at once. See image.

Click the drop-down menu option to choose locations in the **Geolocations** filter. This hierarchical display includes the continent, country, states, and cities within the US, while non-US geolocations show the continent, countries, and then cities. Click **Reset** on the right side of the page to reset your selection.

See image.

### Filtering

To configure filters:

1. Determine which options to include or exclude from each of the filter drop-down menus:
  - **Departments**: Your departments, as defined in Internet & SaaS. To learn more, see [About Departments](https://help.zscaler.com/zia/about-departments).
  - **Zscaler Locations**: Your locations, as defined in Internet & SaaS. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
  - **User Groups**: The names of user groups in your organization.
  - **Geolocations**: The geographic areas where your users are located.
  - **Location Groups**: The names of groups based on location in your organization.
  - **Last Mile ISPs**: The Internet Service Providers (ISPs) to which your users are connecting.
  - **Operating Systems**: The operating system versions installed on user devices in your organization.
2. Click **Apply** after completing your selections.

You can adjust the filters as needed or remove all of your filter selections by clicking **Reset**.

## Widgets

The Performance Dashboard provides the following widgets:

- Most Impacted Applications
- Regions by ZDX Score
- ZDX Score
- Page Fetch Time
- Page Views and Load Time Trend

The dashboard preselects the application with the lowest score, and the rest of the page automatically tracks information based on that application. By selecting another application, the page adjusts to reflect information based on the newly selected application.

Each application shows:

- **Synthetic Monitoring** (blue) or **Real-User Monitoring** (orange): Synthetic monitoring indicates the application is not enabled for [Real User Monitoring (RUM)](https://help.zscaler.com/zdx/understanding-real-user-monitoring). Real-User monitoring indicates the application is enabled for RUM.
- **Score Trend**: The score trend of the application for the selected time period that is dependent on the type of monitoring (Synthetic Monitoring or Real-User Monitoring). The trend can be one of the following:
  - If the application is disabled for RUM and uses only Synthetic Monitoring, then the Score Trend is based on ZDX Scores.
  - If the application is enabled for RUM and uses only Real-User Monitoring, then the Score Trend is based on Real User Scores.
  - If the application is enabled for RUM and uses both Synthetic Monitoring and Real-User Monitoring, the Score Trend includes both ZDX Scores and Real User Scores.
- **Score**: If the application does not have RUM enabled and uses synthetic monitoring, then the score is the ZDX Score. The ZDX Score represents the total experience of all users in your organization for all locations during the selected time period. The ZDX Score is based on a scale of 1 (lowest) to 100 (highest), with the lowest numbers indicating a poor score and highest numbers indicating a good score. To learn more, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score). If the application has RUM enabled, then the score is the **Real User Score**. The Real User Score represents the experience of all users accessing the RUM-enabled application across all locations during the selected time period. The Real User Score uses a similar scale and categories to the ZDX Score.
- Viewing the application: Click the **Open in New Tab** icon to view the application details. To learn more, see [Evaluating Application Details](https://help.zscaler.com/zdx/evaluating-application-details).

You can use the following actions on the Most Impacted Applications menu:

- Search for an application.
- Click the **Sort** button to filter and sort applications. See image.
- Collapse the menu.

The **Regions by ZDX Score** map takes the geographic locations of all users accessing the selected application and organizes the data for the score down to the city level for major cities around the world. This information is displayed in a map. You can zoom in and out of the map to better view regions of interest.

ZDX uses a device's location service to determine a user's location. It takes the longitude and latitude coordinates from the location service and compares it to Zscaler's geographic IP database. That information is then displayed in the ZDX Admin Portal in a map with major cities and towns.

If a user's latitude and longitude are closer to the center of another city, instead of their own city, the user's location might be misidentified as connecting from the neighboring city. If the location service is not enabled on the device, then ZDX uses the device's IP address to determine the location.

ZDX currently supports Windows, macOS, Android, Android on ChromeOS, and iOS devices only.

For each marked location on the map, you can see the name of the city, the ZDX Score, and the number of users while hovering the mouse over the marked spot. Click the **Details**icon ([Image: Details icon within tooltip]) to view user information in the Users Overview specific to the location.

[Image: City information in tooltip of the Regions by ZDX Score map]

The ZDX Score falls into one of three classifications: Good, Okay, or Poor.

To see more about a city or a set of cities, you can set up a fence on the map. To learn more, see Drawing a Fence.

### Drawing a Fence

Within the **Regions by ZDX Score** map, you can filter the data seen on the map by creating a fence.

To draw a fence:

1. Adjust the map to an area you want to view, and click **Draw Fence**.
2. Select a portion of the map. A window with the number of locations selected appears. See image.
3. Click **Filter Selection**. A list of the locations within the fence appears at the bottom of the map.
4. (Optional) Deselect any locations in the **Custom fence** table you do not want to use in a filter.
5. Click **View in Users Page** to see data filtered by these locations. The [Users Overview](https://help.zscaler.com/zdx/monitoring-users-dashboard) appears with data relevant only to those locations. After applying the fence, you can also go to the [Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-dashboard) or return to the Performance Dashboard and the location fence remains as part of your filters.

To redraw the fence, click **Clear Selection** in the map or **Clear Fence** at the bottom of the **Regions by ZDX Score**map. To move the map and select another region, click **Reset** above the map.

See image.

### Unknown Locations

The Unknown Locations widget is visible in the bottom-left corner of the map when a user location is determined to be unknown. Use this widget to filter the users from unknown locations and view their details. If there are no users from unknown locations, this widget is not visible.

[Image: Map shows widget for Unknown Locations]

To use the Unknown Locations widget:

1. Click **Filter Unknown Locations**. A **Custom fence** table appears that lists the unknown locations, the users, and their ZDX Scores. Click **Clear Fence**to clear the filter.
2. Select the unknown locations as desired and then click **View in Users Page** to apply the **Unknown Locations** filter.
3. The number of users from unknown locations and their ZDX Scores are visible in the Users Overview and Applications Overview.

### Map Functionality

The following is the functionality in the **Regions by ZDX Score**map in the Performance Dashboard:

| Map Interaction | Results |
| --- | --- |
| Mouse over a pin | View a tooltip that shows the geolocation, ZDX Score, and number of users. Click the **Details**icon ([Image: Details icon within tooltip]) to view user and location information in the Users Overview. |
| Zoom in/out on a pin | Zoom closer to or farther from the city/region/state/country. |
| Double-click a pin | Zoom into the city level of a region/state/country. |
| Drag the map | Data is displayed per the map boundary. No additional interaction or data is loaded. |
| Draw a fence around a pin and then click **Filter Selection** | View a table that shows the location, users, and ZDX Score. |

The **ZDX Score**graph shows how the ZDX Score trends over the selected time period.

The overall score tracks as a line across the graph. Scores fall into three classifications:

- **Good**: The score is above an acceptable threshold and ranges from 66–100. The color for this range is green.
- **Okay**: The score is acceptable and ranges from 34–65. The color for this range is amber.
- **Poor**: The score is below an acceptable threshold and ranges from 0–33. The color for this range is red.

The graph also tracks the score of the most impacted application, as determined by the applied filters, for comparison. This lets you view the application's impact on the overall score.

For comparison, you can select up to four additional applications by clicking **Add Another Application** in the application selector below the graph. In the drop-down menu, select the additional applications to view. The ZDX Score for the selected applications is displayed. Clicking a point in the graph displays the ZDX Score for the selected applications at that time; you can also click the arrow within the display to go to that application page. To remove an application, deselect it in the application selector. Selections made in the application selector for the ZDX Score are also reflected in the Page Fetch Time graph.

[Image: Example of ZDX Score graph]

The **Page Fetch Time** graph tracks how long it takes the selected application to transfer the fetched page to the user during the selected time period. The time is tracked in milliseconds.

A line runs across the graph that indicates the 95th percentile, as identified by **P95**. It indicates that 95 percent of the fetch time is below this amount.

You can select a point on the graph to see the exact date and time, the application's fetch time at that point, and the 95th percentile fetch time for comparison. To explore more about the application, click **Analyze** to view the Applications Overview and filter by the application. To learn more, see [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-dashboard#indivapp).

For comparison, you can select up to four additional applications by clicking **Add Another Application** in the application selector below the graph. In the drop-down menu, select the additional applications to view. The Page Fetch Time for the selected applications is displayed. Clicking a point in the graph displays the Page Fetch Time for the selected applications at that time; you can also click the arrow within the display to go to that application page. To remove an application, deselect it in the application selector. Selections made in the application selector for the Page Fetch Time are also reflected in the ZDX Score graph.

See image.

If the selected application has RUM enabled, then the Page Views and Load Time trend are displayed. The graph captures the amount of page views and page load times across the selected time period.

A line runs across the graph that indicates the 95th percentile, as identified by **P95**. It indicates that 95 percent of the page loading time is below this amount.

See image.

[Image: Performance Dashboard]

[Image: Example of drawing a fence on the ZDX Score map]

[Image: Shows buttons to clear fence in a Regions by ZDX Score map]

[Image: Example of Page Fetch Time graph]

[Image: View the Page Views and Load Time Trend]

[Image: Example of Geolocations filter drop-down in Performance Dashboard]

[Image: Filter drop-down in Performance Dashboard]

[Image: Filter or sort the most impacted applications]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-self-service-dashboard","lastmod":"2026-04-14T21:06Z","nid":"1462916"} -->
## Monitoring the Self Service Dashboard

- Source: https://help.zscaler.com/zdx/monitoring-self-service-dashboard
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring the Self Service Dashboard
- Last modified: 2026-04-14T21:06Z
- Summary: Overview of notification data in the Self Service Dashboard.

[Watch a video about Monitoring the Self Service Dashboard](https://fast.wistia.net/embed/iframe/3lcjrdtwo6) (shows legacy UI).

Self Service can help users identify the root cause of issues related to CPU usage and Wi-Fi access, allowing users to investigate potential solutions without the need to contact customer support. When enabled for your users, Self Service provides notifications when issues are detected and might need attention. Each notification contains a brief diagnosis and recommendation that might help resolve the CPU or Wi-Fi issue. To learn more about the notifications, see [Viewing Self Service User Notifications](https://help.zscaler.com/zdx/viewing-self-service-user-notifications). To learn more about configuring the notifications for your users, see [Configuring Self Service Settings](https://help.zscaler.com/zdx/configuring-self-service-settings).

The Self Service Dashboard consolidates user data pulled from the user notifications, and can provide a correlation between your users and the number, type, and frequency of notifications. To access the Self Service Dashboard in the Zscaler Admin Console, go to **Analytics** > **Digital Experience** > **Users** > **Self Service**.

## Prerequisites

Before you can monitor notification and user data in the Self Service Dashboard, ensure:

- You're running the required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#SelfService).
- Your subscription level supports Self Service. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Your admin role is configured for Self Service. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Selecting Filters

Use the time range filter and page filters to help narrow the scope of notifications. Time range options are available in increments from the previous **2 Hours** to **14 Days**.

- **Departments**: Your departments, as defined in Internet & SaaS. To learn more, see [About Departments](https://help.zscaler.com/zia/about-departments).
- **Zscaler Locations**: Your locations, as defined in Internet & SaaS. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
- **Geolocations**: The geographic areas where your users are located.
- **Notification Types**: Identified as Wi-Fi, CPU, or Other.

## Viewing Notification and User Counts

View the numerical counts for notifications and users, based on your selected time range:

- **Total Notifications Sent**: The number of notifications sent, including a percentage increase or decrease.
- **Notifications By Type**: The number of notifications related to Wi-Fi, CPU, or Other.
- **Total Users Notified**: The number of users who received notifications, including a percentage increase or decrease.
- **Users Found Notifications Helpful**: The number of users who provided feedback and indicated their notifications were helpful, including a percentage increase or decrease.
- **Active Users with Self Service Enabled**: The number of active users who have Self Service enabled, including a percentage increase or decrease.
- **Users Who Disabled Notifications**: The number of users who disabled their notifications, including a percentage increase or decrease.

[Image: Notification and user counts]

## Viewing Notifications Sent Over Time

The **Notifications Sent Over Time** graph shows the number of user notifications that were generated during your selected time range. The corresponding table provides a detailed list of user notifications that are specific to the time or dates within that time range:

- **User**: The user who received the notification.
- **Device**: The user's device that launched the notification.
- **Notification Type**: Indicates whether the notification was related to CPU, Wi-Fi, or another issue.
- **Found It Helpful?**: Indicates **Yes**or **No** if feedback was provided by the user. **Not available** indicates feedback was not provided.
- **Timestamp**: The date and time of the notification.

[Image: Graph and table that show notifications]

### Viewing User Device Events

Click the username in the notifications table to view the related event in the **User Device Events** graph. Notification icons indicate the dates and times when a notification was sent to the user. For example:

[Image: Notification icon shown in User Device Events graph]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-users-overview","lastmod":"2026-04-03T09:00Z","nid":"1355791"} -->
## Monitoring the Users Overview

- Source: https://help.zscaler.com/zdx/monitoring-users-overview
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Monitoring the Users Overview
- Last modified: 2026-04-03T09:00Z
- Summary: Information on the Users Overview and widgets accessible within the Zscaler Admin Console.

The Users Overview provides information about the digital experience of users in your organization during the selected time frame. By default, the Active User Details are displayed in the overview without a selected application.

See image.

If the application has [Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring) enabled, then the Real User Monitoring (RUM) User Details are also displayed.

See image.

If the application does not have Real User Monitoring enabled, then RUM User details are not displayed.

See image.

The Users Overview allows you to:

- View user information: Select one or more applications in the Applications filter drop-down menu and click **Apply**to view user information in the ZDX Score User Distribution and the Users list. By default, no information is displayed in the ZDX Score User Distribution and the Users list until an application is selected. Applications chosen in the Users Overview are carried over to the user details page.
- View performance data over time: Use the Time Range filter to choose a specific time range to view data. The selected period applies to all data within the overview. The default time range is **2 Hours**.
  - **Current**: View the most current ZDX Score captured within the previous 30 minutes.
  - **2 Hours**to **48 Hours**: Specify a time interval between 2 hours and 48 hours as shown in the drop-down menu.
  - **Custom**: Specify a custom time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments.
- Filter data: Click the filters to select options for Applications, Departments, Zscaler Locations, Geolocations, User Groups, Users, Location Groups, Last Mile ISPs, and Operating System. Each filter allows you to include or exclude individual options. Click **Select All Displayed** to select all options at once. These filters apply to only this page and do not apply to the Probes configuration page. Any disabled applications in the Applications filter are shown in gray, and deleted applications are indicated with a strikethrough on the application name. See image.
- Understand user digital experience: Click the table cell for User or Devices in the User list table to view a page with details about the user's digital experience.

To view user information using filters:

1. Determine which options to include or exclude from each of the filter drop-down menus:
  - **Applications**: The applications used by users.
  - **Departments**: Your departments, as defined in Internet & SaaS. To learn more, see [About Departments](https://help.zscaler.com/zia/about-departments).
  - **Zscaler Locations**: Your locations, as defined in Internet & SaaS. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
  - **Geolocations**: The geographic areas where your users are located.
  - **User Groups**: The names of user groups in your organization.
  - **Users**: The names of your users.
  - **Location Groups**: The names of groups based on location in your organization.
  - **Last Mile ISPs**: The Internet Service Providers (ISPs) to which your users are connecting.
  - **Operating System**: The operating system versions installed on user devices in your organization.
2. Click **Apply**after completing your selections.
3. You can select other options in the Time Range filter to view data over a time period.

You can adjust the filters as needed or remove all of your filter selections by clicking **Reset**.

## Reviewing Digital Experience Overview

To get a brief overview of your organization's digital experience, use the following:

- Active User Details
- Real-User Monitoring (RUM) User Details

## Viewing User Information

The User list displays up to 100 users in the **Poor**, **Okay**, or **Good**ZDX Score category for the selected time period. By default, users with Poor, Okay, or Good scores are displayed under their respective tabs within the table and the page displays the Poor tab on loading. However, if there are no users with a specific score (e.g., Poor), then the table displays the next tab that applies to any users in your organization.

The **Download** icon ([Image: Using the download CSV icon for current table view]) allows you to download the current table view of listed users in CSV format for each ZDX Score category. If no users are listed for any ZDX Score category, the icon does not appear.

To further sort user information, use the filters on the page. You can also use the Search functionality to search for a specific user. To learn more, see [Using Search in the ZDX Admin Portal](https://help.zscaler.com/zdx/using-user-search-zdx-admin-portal).

The table displays the following information about users and their digital experience:

- User
- ZDX Score
- Real User Score
- Geolocations
- Zscaler Locations
- Device Count
- [Start Diagnostics](https://help.zscaler.com/zdx/starting-new-diagnostics-session)

In the Users list, you can:

- Compare user details: Click the **Open in a New Tab** icon ( [Image: Open in a New Tab Icon]) to open the user page in a new tab. You can compare multiple users using this option.
- View user details: Click a user name to go to the user page and view user details.
- View device details: Click the Device Count icon () and then click the device name to view device details.
- Start a Diagnostics session: Click the **Diagnostics** icon ( [Image: Using the Diagnostics icon to start a session]) to start a session for a user.

To learn more about user and device details, see [Evaluating User Details](https://help.zscaler.com/zdx/evaluating-user-details).

View the number of active users for a selected time period:

- A percentage indicates how the number of users has increased or decreased for the selected time period.
- A distribution of how many users have a **Poor**, **Okay**, or **Good** ZDX Score in the selected time period. To learn more, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).

If the applications have RUM enabled, then you can view the number of active RUM users for a selected time period:

- A percentage indicates how the number of RUM users has increased or decreased for the selected time period.
- A distribution of how many users have a **Poor**, **Okay**, or **Good** Real User Score in the selected time period.

The **User** column provides a list of users by their name. These names come from your identity providers configured in Internet & SaaS. To learn more, see [About Identity Providers](https://help.zscaler.com/zia/about-identity-providers).

You can click a name to see more information about each user. To learn more, see [Evaluating User Details](https://help.zscaler.com/zdx/evaluating-user-details).

The **ZDX Score**column shows the user's ZDX Score from 1–100, with 1 being the lowest and 100 being the highest. It is an average score for the selected time period. To learn more, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).

The **Real User Score** column shows the user's Real User Score from 1–100, with 1 being the lowest and 100 being the highest. It is an average score for the selected time period. It uses similar categories to the ZDX Score.

The **Zscaler Locations** column provides a list of the locations where the user accessed their device. These locations are defined in ZIA. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).

The **Geolocations** column lists all the areas where users accessed their devices for the selected time period.

ZDX uses a device's location service to determine a user's location. It takes the longitude and latitude coordinates from the location service and compares them to Zscaler's geographic IP database. That information is then listed, by corresponding major cities and towns, in the ZDX Admin Portal.

If a user's latitude and longitude are closer to the center of another city, instead of their own city, the user's location might be misidentified as connecting from the neighboring city. If the location service is not enabled on the device, then ZDX uses the device's IP address to determine the location.
ZDX currently supports Windows, macOS, Android, Android on ChromeOS, and iOS devices only.

The **Device Count** column lists the total number of devices associated with the user.

Click the device name to view detailed information about the user's device. To learn more, see [Evaluating User Details](https://help.zscaler.com/zdx/evaluating-user-details).

[Image: View Users Overview of a selected application]

[Image: View the Users Overview of a filtered application]

[Image: View Real-User Monitoring (RUM) User Details on the Users Overview]

[Image: Filter to select one or more applications]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-wi-fi-dashboard","lastmod":"2026-04-20T18:26Z","nid":"1503411"} -->
## Monitoring the Wi-Fi Dashboard

- Source: https://help.zscaler.com/zdx/monitoring-wi-fi-dashboard
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring the Wi-Fi Dashboard
- Last modified: 2026-04-20T18:26Z
- Summary: Information about Wi-Fi access point status on user devices.

The Wi-Fi Dashboard utilizes existing user and device Wi-Fi data in your organization to monitor device performance. This data can help identify specific locations in which users might have issues with their Wi-Fi access points.

## Prerequisites

To monitor Wi-Fi performance within the Wi-Fi Dashboard, ensure:

- Your ZDX subscription level supports monitoring the Wi-Fi Dashboard. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Your ZDX role has the proper permission level. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Viewing the Wi-Fi Dashboard

To access the Wi-Fi Dashboard, go to **Analytics**> **Digital Experience** > **Networks** > **WiFi**. Time range options are available in increments from the previous **2 Hours** to **48 Hours**, or a **Custom Range** within the last 14 Days. Use the time range filter and page filters to help narrow your scope of information:

- **Wi-Fi SSID**: The network Service Set Identifier. You can select up to 5 SSIDs from the drop-down menu. The SSID associated with the highest number of devices is selected by default.
- **Departments**: See [About Departments](https://help.zscaler.com/zdx/about-departments).
- **Zscaler Locations**: See [About Locations](https://help.zscaler.com/zdx/about-locations).
- **Geolocations**: The geographic areas where your users are located.
- **Operating System**: The operating system versions installed on user devices in your organization.
- **Vendors**: Supported Wi-Fi vendors.
- **AP Prefix**: Enter up to 6 prefixed characters to filter access points.

Certain OSs (i.e., macOS, Windows 11), with the latest updates, do not enable [Zscaler Client Connector's privacy settings](https://help.zscaler.com/zscaler-client-connector/configuring-zscaler-client-connector-collect-zdx-location-information) by default. When Zscaler Client Connector's **Collection Location Info for ZDX** is not enabled, privacy information for Wi-Fi details, such as SSID and BSSID, are not captured in the Wi-Fi Dashboard.

[Image: View an overview about Wi-Fi access points on the Wi-Fi Dashboard]

View details about the distribution and performance of Wi-Fi access points and connected devices:

- **Total Access Points**: The current number of active access points with a percentage increase or decrease over the selected time period.
- **Wi-Fi Performance of Access Points**: A performance distribution based on Good, Okay, and Poor ZDX Scores. Hover over the bar chart to view the performance distribution.
- **Total Devices Connected**: The current number of active devices with a percentage increase or decrease over the selected time period.
- **ZDX Score Distribution**: The distribution of Good, Okay, and Poor ZDX Scores for all connected devices. Hover over the bar chart to view the device count distribution.
- **Wi-Fi Band Distribution**: The estimated number of total active devices delineated by Wi-Fi band percentage. The device count is duplicated for any device using more than one Wi-Fi band at a given time.
- **Top Geolocations by Devices Connected**: The top 5 geolocations with the highest device counts, along with the distribution of their respective ZDX Scores.
  - Click an individual location to launch a consolidated view of its Wi-Fi access points.
  - Hover over the **Score Distribution** bar chart to view a device count per ZDX Score.
- **Wi-Fi Performance by Geolocation**: The SSID performance distribution via location. You can switch between **Map View** or **List View**. The **Map View** is the default view.
  - On the **Map View**, you can:
    - View color-coded locations to reflect the Wi-Fi performance of devices in that location.
    - Hover over a Wi-Fi location to launch a tooltip with Wi-Fi and device details.
    - Click **View Access Points** within the tooltip to launch a consolidated view of Wi-Fi access points within the location.
  - On the **List View**, you can see the following information: See image.
    - **Name**: The name of the Wi-Fi access point.
    - **Wi-Fi Performance**: The category of Wi-Fi performance for the Wi-Fi access point (**Good**, **Okay**, **Poor**).
    - **Access Points**: The number of access points at the location.
    - **Connected Devices**: The total number of connected devices at the location.
    - **Poor ZDX Score Devices**: The number of poor ZDX Score devices at the location.
    - **% Poor ZDX Score Devices**: The percentage of poor ZDX Score devices at the location.

Optionally, you can configure your Wi-Fi data collection to use signal strength and retransmission rate to identify low-performing Wi-Fi devices instead of ZDX Score. To learn more, see [Configuring Inventory Settings](https://help.zscaler.com/zdx/configuring-inventory-settings#Wi-Fi).

To learn more about ZDX Score categories, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).

## Viewing Wi-Fi Access Points

You have the option to display a consolidated view of access points in either a **Tree View** or **List View**.

### Access Points in Tree View

The default Tree View of a location's access points is color-coded according to Wi-Fi performance, with each tile color based on a ZDX Score. The size of each tile is based on user volume, with the largest user count shown on the left side of the page, and the lowest user count shown towards the right side of the page.

See image.

Hover over an access point tile to view the following information:

- The number of users and devices connected to the access point.
- The latency and jitter metrics for the access point.
- A link to view granular details about the access point.
- A link to optionally customize the access point info. See image.

### Access Points in List View

Click **List View** to display the Wi-Fi access points in a tabular format that provides the following information:

See image.

- **MAC Address**: The access point identifier.
- **Custom Name**: If applicable, the customized name for the access point.
- **Wi-Fi Performance**: The ZDX Score rating as Good, Okay, or Poor.
- **Number of Users**: The total count of active users connected to the access point.
- **Number of Devices**: The total count of active devices connected to the access point.
- **Avg Latency**: The average latency of the access point, measured in milliseconds.
- **Avg Jitter**: The average jitter of the access point, measured in milliseconds.

Click an access point to view its details. You can also click the **Edit**icon to customize the access point info.

## Viewing Access Point Details

The access point details page provides a brief summary of a selected access point:

- **SSID**: The network Service Set Identifier for the access point.
- **Location**: The geographic location of the access point.
- **Wi-Fi Performance**: The ZDX Score rating as Good, Okay, or Poor.
- **AP MAC**: The MAC address of the access point.
- **Access Point Info**: The renamed or customized access point, or the link to add info to customize the access point.
- **Devices**: The number of devices within the access point location.

See image.

### Key Metrics

Salient metrics are provided for the access point in the following charts:

- **Source to Gateway Latency**: The average time for a data packet to travel from the source device to the gateway within the selected time range.
- **Source to Gateway Jitter**: The average variation in latency between data packets traveling from the source device to the gateway within the selected time range.
- **Active Users**: The number of active users and devices connected to the access point within the selected time range.

A table that identifies each user connected to the access point provides additional Wi-Fi information. Click a user or device name within the table to view details about each entry.

- **Device**: A user's device connected to the access point.
- **User**: A user connected to the access point.
- **BSSID**: The Basic Service Set Identifier.
- **Wi-Fi Adapter**: The adapter brand of the access point.
- **ZDX Score**: The score for the device connected to the access point.
- **Signal Strength**: The average Wi-Fi signal strength of the device.
- **Wi-Fi Type**: The wireless communication standard of the access point.
- **Wi-Fi Channel**: The channel number associated with the band frequency.
- **OS**: The device's operating system.

[Image: Access point details page]

[Image: Tree view of access points]

[Image: Customizing an access point]

[Image: View a list of Wi-Fi access points]

[Image: View details about the Wi-Fi Performance by Locations in List View]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/monitoring-zia-private-service-edge-dashboard","lastmod":"2026-05-06T21:06Z","nid":"1525341"} -->
## Monitoring the ZIA Private Service Edge Dashboard

- Source: https://help.zscaler.com/zdx/monitoring-zia-private-service-edge-dashboard
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Monitoring the ZIA Private Service Edge Dashboard
- Last modified: 2026-05-06T21:06Z
- Summary: A statistical overview of Private Service Edges for Internet & SaaS in data centers.

Internet & SaaS (ZIA) Private Service Edge Health provides a statistical overview of Zscaler Private Service Edges deployed in your data centers. View details of overall traffic, internal latency, and transactions within the previous 24 hours to 14 days, as allowed per your ZDX subscription level.

## Prerequisites

To monitor Private Service Edge for Internet & SaaS metrics within the dashboard, ensure:

- Your ZDX subscription level supports monitoring the ZIA Private Service Edge Health dashboard. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Your admin role has view permission. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Viewing the Dashboard

To access the ZIA Private Service Edge Health dashboard, go to **Analytics**> **Digital Experience** > **Zscaler Health** > **ZIA PSE Health**. The tiles provide metrics from the previous 24-hour period to the current time. Click **Refresh**in the upper-right corner of the page to reload any real-time updates.

- **Total PSE Data Centers**: The total number of data centers with Private Service Edges within your organization.
- **Total Throughput**: The average amount of data (in bits) processed in your data centers within the previous 24 hours.
- **Transactions/Sec**: The aggregate number of transactions processed per second in your data centers within the previous 24 hours.

[Image: ZIA PSE Health Dashboard]

The **Overall Traffic** chart provides an overview of traffic from all data centers processed by Private Service Edge instances within your selected time range. If you select 24 Hours or 48 Hours, traffic displays in 5-minute intervals. If you select 7 Days or 14 Days, traffic displays in 1-hour intervals.

Optionally, click **Export CSV** to capture total throughput for the data centers, designated by date and time.

The dashboard table provides the following information:

- **PSE Data Center Name**: The specific data center in your organization with Private Service Edges.
- **Location**: The geographic location of the data center.
- **Instances**: The number of Private Service Edge proxy instances.
- **Throughput**: The average amount of data processed per second for the date and time within your selected time range.
- **Latency**: The average time (in milliseconds) to send data from the beginning to the end of your selected time range.
- **Transactions/Sec**: The aggregate number of transactions processed per second within your selected time range.

Click any data center name to view its details, including all Private Service Edges deployed within it.

## Viewing a Data Center

The time range selected for **Overall Traffic** from the dashboard is carried over to the data center page by default. If you change the time range, all chart metrics reflect your updated selection, accordingly.

You can download metrics for each data center chart in CSV format. You also have the option to click **Report Issue** and submit a help ticket if you experience issues that require administrator support.

- **PSE Details**: A summary of metrics for the selected data center within the data time range.
  - **Throughput**: The average amount of data processed per second within the data time range.
  - **Latency**: The time (in milliseconds) to send data within the data time range.
  - **Location**: The geographic location of the data center.
  - **VIP Address**: The Virtual IP address.
  - **Cluster**: The individual names for the VIP address.
- **Overall Traffic**: The overview of traffic from all your data centers, as shown in the ZIA Private Service Edge Health dashboard.
- **PSE Internal Latency**: The P95 latency (in milliseconds) calculated for the data point within the selected time range.
- **Transactions/Sec**: The total number of transactions processed per second for the data point within the selected time range.

The data center table provides the following information:

- **Instance**: A Private Service Edge identified by node name extension (e.g., pchi1-**1a1-sme**, pchi1-**1a2-sme**, pchi1-**1a3-sme**, etc). Each Private Service Edge instance belongs to a node cluster that is grouped by a physical node name (e.g., **1a**, **1b**, **1c**, etc).
- **Node Name**: The name of the physical server on which the instance resides. Optionally, you can use the Node Name drop-down menu to search for Private Service Edge clusters: See image.
- **Service IP Address**: The IP address for the corresponding Private Service Edge instance.
- **Asset Tag**: A physical ID label for the Private Service Edge.
- **Model Number**: The provided Zscaler server model number.

Click any Private Service Edge instance to view its details.

See image.

## Viewing a Private Service Edge Instance

For each Private Service Edge instance, metrics are provided for the following charts:

- **Traffic**: The traffic processed by the Private Service Edge instance.
- **Latency**: Includes the average P95 latency (in milliseconds) for the Private Service Edge.
- **Memory Usage**: Indicates usage (in gigabytes) for the selected time range. Metrics for memory usage can be useful for troubleshooting purposes.

See image.

[Image: View Data Center metrics]

[Image: Node Name drop-down menu]

[Image: PSE instance]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/opsgenie-webhook-configuration-guide","lastmod":"2026-07-10T10:01Z","nid":"1450476"} -->
## OpsGenie Webhook Configuration Guide

- Source: https://help.zscaler.com/zdx/opsgenie-webhook-configuration-guide
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > OpsGenie Webhook Configuration Guide
- Last modified: 2026-07-10T10:01Z
- Summary: Information on how to configure webhooks for alerts using OpsGenie.

This guide provides information on configuring webhooks using OpsGenie for alerts in ZDX. The following instructions provide a sample configuration that you can use based on the user's requirements.

## Setting Up Integration Keys on OpsGenie

1. Log in to your OpsGenie account.
2. Go to **Teams** > **Add Team** and enter the following information: Click **Add Team** to complete the team creation. See image.
  - **Name**: Enter a team name.
  - **Description**: Enter a team description.
  - **Add Members**: Search and select which members you want to view the alerts.
3. Go to the created Team page.
4. From the left-side navigation, go to **Integrations** > **Add Integration** > **API**. See image.
5. Click **Copy** or save your API Key. This is also called GenieKey. See image.
6. Make sure the following are selected: See image. If these are already selected by default, skip to the next step.
  - Read Access
  - Create and Update Access
  - Delete Access
  - Enabled
7. Click **Save**.

## Setting Up Alert Webhooks on Zscaler Admin Console

1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
2. Click **Add Webhook**.
3. Enter the following information:
  1. **Name**: Enter the name of the webhook for OpsGenie.
  2. **Status**: Select **Enabled**.
  3. **URL**: Enter your OpsGenie URL. It is normally `https://api.opsgenie.com/v2/alerts?isOpsGenie=true`.

The generic OpsGenie URL is `https://api.opsgenie.com/v2/alerts`.

For ZDX Integration, `?isOpsGenie=true` must be appended to the URL. The final URL to enter the ZDX UI is then `https://api.opsgenie.com/v2/alerts?isOpsGenie=true`.

The URL might be different when using an enterprise setup. If there is a specific URL for your organization, you must append `?isOpsGenie=true` at the end of your URL to integrate with ZDX.

1. **Authentication Type**: Select **Token**.
2. **Bearer Token**: Enter your Bearer Token with the API Key from OpsGenie (also known as GenieKey) in the following format. The green text indicates the insertion of Setting Up Integration Keys on OpsGenie..

```
GenieKey
{GenieKey from the OpsGenie API Portal}
```

1. Click **Save**. See image.
2. Go to **Alerts** > **Rules** > **Add New Alert Rule**.
3. Configure the Alert Rule settings as needed based on your organization's needs. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).
  1. On the **Action** tab and under the **Action** section, the following fields are required to integrate with OpsGenie:
    - **Muted**: Disable this setting.
    - **Webhooks**: Select the Ops Webhook that you created.
    - **Alert Delivery Method**: Select **Webhook**. Email is optional.
  2. Click **Next**.
  3. On the **Review** tab, click **Submit** to save the Alert Rule configuration.
4. Click **Save.**
5. [Activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

## Viewing Alerts on OpsGenie

1. Go to your OpsGenie home page.
2. Go to the **Alerts** page to view a list of alerts, or select an individual alert for more details.

The Alert Title might not display all the characters if the character size exceeds 130 characters. If this happens, the fields are displayed within the description.

See image.

[Image: Add Team Window]

[Image: Select API from the Integration list]

[Image: Copy or Save the API Key]

[Image: Default Configuration]

[Image: Webhook Configuration]

[Image: Alerts List]

[Image: Individual Alert]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/pagerduty-webhook-configuration-guide","lastmod":"2026-07-10T10:02Z","nid":"1376326"} -->
## PagerDuty Webhook Configuration Guide

- Source: https://help.zscaler.com/zdx/pagerduty-webhook-configuration-guide
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > PagerDuty Webhook Configuration Guide
- Last modified: 2026-07-10T10:02Z
- Summary: Information on configuring webhooks for alerts using PagerDuty.

This guide provides information on using PagerDuty for configuring webhooks for alerts in ZDX. These instructions provide a sample configuration that you can use based on user requirements.

1. Create a PagerDuty account at `www.pagerduty.com`.
2. On your PagerDuty account page:
  1. Click **Developer Mode**. See image.
  2. Click **Create New App**. See image.
  3. On the **Build App** window: Click **Save**. See image.
    - **AppName**: Enter a name for the application.
    - **Description**: Enter a description.
    - **Category**: Select **Error Tracking**.
    - **We would like to help you publish a public app for all PagerDuty users. Do you intend to publish the app for all PagerDuty users and the app ecosystem?**: Select **No, I am not interested**.
  4. When the **Configure App** page appears, go to **Events Integration** > **Manage**. See image.
  5. Select **Yes** for **Event Integration** and enter the event transformation script. See image.
    - Script for Event Transformation
  6. **Copy** the Events API Endpoint address as the URL to use when configuring a webhook in the Zscaler Admin Console. See image.
  7. Click **Save**.
3. [Configure a webhook in the Zscaler Admin Console.](https://help.zscaler.com/zdx/configuring-webhooks)
  1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
  2. Click **Add Webhook**.
  3. In the **Add Webhook** window:
    - **Name**: Enter the name of the webhook for PagerDuty.
    - **Status**: Select **Enabled**.
    - **URL**: Enter the URL you copied from the previous step.
    - **Authentication Type**: Select **Token**.
    - **Bearer Token**: PagerDuty does not generate a bearer token. To meet webhook configuration requirements, enter any text for the bearer token (e.g., `1234`).
  4. (Optional) Click **Test Webhook** to confirm the webhook configuration works.
  5. Verify the incident within PagerDuty if an alert is raised from the Test Webhook. See image.
  6. Click **Save**.
  7. [Activate your changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

[Image: Access Developer Mode for Pager Duty]

[Image: Create Application in PagerDuty]

[Image: Manage Events in PagerDuty]

[Image: Build an App in PagerDuty]

[Image: Event Transformation Script for PagerDuty]

[Image: Events Integration in PagerDuty]

[Image: ZDX Alerts in PagerDuty]

```
export function transform(PD) {
  // Sample Event Transformation
  let body = PD.inputRequest.body;
  let zdxLinkURL = body.zdxUrl;
  let emitEv = true;
 
  let ruleName = body.ruleName;
 
  if (ruleName.toLowerCase() == "test")
    emitEv = false;
 
  let payloadCount = "\n\n";
 
  if ( body.status == "STARTED" ) {
    payloadCount += "Affected: \nGeolocations: " +  
                    body.geolocationCount + "\nDepartments: " + 
                    body.deptCount + "\nImpacted Devices: " + 
                    body.impactedDeviceCount + "\nOS Versions: " + 
                    body.osverCount
  }
 
  let normalized_event = {
    event_action: PD.Trigger,
    // optionally include a key to prevent creating duplicate
    // incidents when the same event is sent more than once
    // dedup_key: body.event_key,
 
 
    payload: {
      summary: `Rule: ${body.ruleName}, Severity: ${body.severity} ${body.status}`,
      source: 'ZDX',
      severity: PD.Critical,
      custom_details: `AlertId: ${body.alertId}\nRule Name: ${body.ruleName}\nSeverity: ${body.severity}\nCriteria: ${body.criteriaString}\nLink: ${zdxLinkURL} ${payloadCount}`
    },
    dedup_key: "",
 
    // optionally display links or images on web and mobile
    links: [{
      "href": zdxLinkURL,
      "text": "Alert Details"
    }],
  };
 
  if ( emitEv)
    PD.emitEventsV2([normalized_event]);
}
```
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/predefined-applications-zdx","lastmod":"2026-07-07T08:40Z","nid":"1355886"} -->
## Predefined Applications for ZDX

- Source: https://help.zscaler.com/zdx/predefined-applications-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Applications > Predefined Applications for ZDX
- Last modified: 2026-07-07T08:40Z
- Summary: Information about predefined applications and how to configure them for ZDX.

[Watch a video about Predefined Applications for ZDX.](https://fast.wistia.net/embed/iframe/nomenzv7yc)

Predefined applications are available for ZDX when you log in. The predefined applications provide quick and seamless application onboarding for admins.

To find the list of predefined applications in the Zscaler Admin Console, go to **Policies** > **Digital Experience Monitoring** > **Configuration** > **Probes** > **Applications**.

Your available predefined applications depend upon your particular ZDX subscription:

- Standard Subscription
- Microsoft 365 Subscription
- Advanced Subscription
- Advanced Plus Subscription

The Standard subscription provides the following applications:

- Box
- ServiceNow
- Salesforce Classic
- Salesforce Lightning
- Okta
- OneDrive for Business
- Outlook Online
- SharePoint Online
- Microsoft Login
- Microsoft Teams Web App
- Webex Web App
- Zoom Web App

The Microsoft 365 subscription provides the following applications:

- Unified Communication
  - Microsoft Teams Call Quality
- Web Applications
  - Microsoft Teams Web App
  - Microsoft Login
  - OneDrive for Business
  - Outlook Online
  - SharePoint Online

The Advanced subscription provides the following applications:

- Unified Communication
  - Microsoft Teams Call Quality
  - Webex Call Quality
  - Zoom Call Quality
- Web Applications
  - Box
  - ServiceNow
  - Salesforce Classic
  - Salesforce Lightning
  - Okta
  - OneDrive for Business
  - Outlook Online
  - SharePoint Online
  - Microsoft Login
  - Microsoft Teams Web App
  - Webex Web App
  - Zoom Web App

The Advanced Plus subscription provides the following applications:

- Unified Communication
  - Microsoft Teams Call Quality
  - Webex Call Quality
  - Zoom Call Quality
- Web Applications
  - Box
  - ServiceNow
  - Salesforce Classic
  - Salesforce Lightning
  - Okta
  - OneDrive for Business
  - Outlook Online
  - SharePoint Online
  - Microsoft Login
  - Microsoft Teams Web App
  - Webex Web App
  - Zoom Web App

The Microsoft 365 subscription and Advanced subscription both support Unified Communications as a Service (UCaaS) voice monitoring. However, only Microsoft Teams Call Quality is currently supported for the Microsoft 365 subscription.

To learn more about predefined applications, see [About Applications](https://help.zscaler.com/zdx/about-applications) and [Configuring a Predefined Application](https://help.zscaler.com/zdx/configuring-predefined-application).

To learn more about ZDX features unique to each ZDX subscription, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/preparing-custom-script-signing","lastmod":"2026-06-08T16:26Z","nid":"1531247"} -->
## Preparing Custom Script Signing

- Source: https://help.zscaler.com/zdx/preparing-custom-script-signing
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Remediation > Preparing Custom Script Signing
- Last modified: 2026-06-08T16:26Z
- Summary: These are the device preparation and configuration steps that are required to sign a script certificate prior to uploading it to the Zscaler Admin Console.

Each customized and uploaded script must have a signed certificate to allow any scripts to run on a device. This ensures each script meets the minimum security requirements and is validated by an admin for use.

You must have the following conditions for Windows devices to allow scripts to run:

- PowerShell's Execution Policy on the device is not restricted.
  - Enable PowerShell scripts to run on your device.
- Your Code Signing `cert` file is:
  - Added to the Trusted Publisher's directories. To learn more, refer to [Microsoft's Trusted Publishers Certificate Store.](https://learn.microsoft.com/en-us/windows-hardware/drivers/install/trusted-publishers-certificate-store)
  - Valid and not expired.
- Allowlist the following based on your Windows version: To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).
  - 32-bit
  - 64-bit
- The Windows device must have the [minimum version compatibility or later](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).

1. Open the PowerShell and run it as an `Administrator`.
2. Enter one of the following prompts depending on which Execution Policy you want for the device: `Set-ExecutionPolicy AllSigned -Scope LocalMachine``Set-ExecutionPolicy RemoteSigned -Scope LocalMachine`
3. Enter `A` to indicate *yes to all* to allow you to run remote scripts.
4. Verify if the policy is set correctly by running the following command to ensure rewritten and custom scripts for Remediation can run: `Get-ExecutionPolicy -List`

```
%ProgramFiles(x86)%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptOrchestratorService.exe
%ProgramFiles(x86)%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptExecutor.exe
```

```
%ProgramFiles%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptOrchestratorService.exe
%ProgramFiles%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptExecutor.exe
```

## Zscaler Client Connector & ZDX Preparation

You must have the following prepared for Zscaler:

- Zscaler Client Connector: Must download the [minimum or later version of Zscaler Client Connector and ZDX Module](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
- ZDX:
  - [Enable Remediation from the Remediation Settings](https://help.zscaler.com/zdx/configuring-remediation-settings) in the Zscaler Admin Console.
  - [Script management access permission](https://help.zscaler.com/zdx/adding-zdx-roles) to upload the signed script.

## Certificate Authority and Code Signing Certificate

All custom scripts must be signed. There are two options depending on how you sign a custom script:

- Select a certificate authority (CA) that exists and is ready for use within your organization that can generate a code signing certificate.
- Generate a self-signed CA for code signing certificate.

After your CA and code signing certificate are ready for use, you can start to sign a script to run on a device.

## Example Custom Scripts

As you go through the following example scripts, these are the following items you encounter:

- Parameters: The required parameters needed to run the script. If the parameters are mandatory, you must select the parameter as Mandatory in the [Script configuration](https://help.zscaler.com/zdx/managing-scripts#img_add_script).
- Custom cmdlets for `set-zresult` and `set-zoutput`: Routes output to ZDX.
- (Optional)`-Test flag`: Local testing of the script before it is signed and uploaded. When the `-Test flag` is passed, the script's output is routed through PowerShell's write-output commands, rather than the Zscaler's `z-output` and `z-result` output commands.

The following are example scripts using a basic notification pop-up:

- Example Script without Mandatory Parameters
- Example Script with Mandatory Parameters

Only ASCII/UTF-8 without Byte Order Mark (BOM) encoding are accepted for scripts in the Zscaler Admin Console.

```
# Script Name: HelloWorld.ps1

<#
.SYNOPSIS
A basic demonstration of a script for the ZDX environment without parameters.

.VERSION
1.0.1

.CATEGORY
Documentation

.DESCRIPTION
This script serves as a minimal template. It demonstrates how to log details 
using Show-Output and how to report a final status using Show-Result.
All details are routed via ZOutput, and ZResult is Success/Failure only.

.PARAMETER None

.EXAMPLE
.\HelloWorld.ps1

.EXAMPLE
# Local test mode (routes to Write-Host)
.\HelloWorld.ps1 -Test

.EXEC_LEVEL
USER

Escalated system privileges required: No
User confirmation suggested: No
#>

param (
    # Internal testing only
    [switch]$Test
)

# --- HELPER FUNCTIONS ---
# -test flag is used for local testing
function Show-Output ($Message) {
    if ($Test) { Write-Host "[OUTPUT] $Message" }
    else { set-ZOutput $Message }
}

function Show-Result ($Result) {
    if ($Test) { 
        Write-Host "[RESULT] $Result" 
    } else { 
        set-ZResult $Result
        $ExitCode = if ($Result -eq "Success") { 0 } else { 1 }
        exit $ExitCode
    }
}

# --- MAIN LOGIC ---
try {
    Show-Output "ZDX TEST: Starting execution..."
    
    # Core Logic
    Show-Output "Hello World! This is a standard ZDX log message."
    
    # Final Result and Process Exit
    Show-Result "Success"

} catch {
    Show-Output "Critical Error: $($_.Exception.Message)"
    Show-Result "Failure"
    }
```

```
### HelloWorld-WithParameters.ps1
    
    # Script Name: HelloWorld-WithParameters.ps1
    
    <#
    .SYNOPSIS
    A simple demonstration of a script using mandatory and optional parameters.
    
    .VERSION
    1.0.0
    
    .CATEGORY
    Documentation
    
    .DESCRIPTION
    This script serves as a template for handling user input. It requires a 'Name' 
    but provides a default 'Greeting' if one is not specified. It demonstrates the 
    standard ZDX script pattern for parameters and reporting.
    
    .PARAMETER Name
    The name of the entity to greet. (Mandatory)
    
    .PARAMETER Greeting
    The greeting word to use. Defaults to 'Hello'. (Optional)
    
    .EXAMPLE
    # Basic usage
    .\HelloWorld-WithParameters.ps1 -Name "ZDX User"
    
    .EXAMPLE
    # Usage with optional greeting
    .\HelloWorld-WithParameters.ps1 -Name "ZDX Test" -Greeting "Welcome"
    
    .EXAMPLE
    # Local test mode (routes to Write-Host)
    .\HelloWorld-WithParameters.ps1 -Name "ZDX Test" -Greeting "Welcome" -Test
    
    .EXEC_LEVEL
    USER
    
    Escalated system privileges required: No
    User confirmation suggested: No
    #>
    
    param (
        [Parameter(Mandatory = $true)]
        [string]$Name,
    
        [Parameter(Mandatory = $false)]
        [string]$Greeting = "Hello",
    
        # Internal testing only
        [switch]$Test
    )
    
    # --- HELPER FUNCTIONS ---
    # -test flag is used for local testing
    function Show-Output ($Message) {
        if ($Test) { Write-Host "[OUTPUT] $Message" }
        else { set-ZOutput $Message }
    }
    
    function Show-Result ($Result) {
        if ($Test) { 
            Write-Host "[RESULT] $Result" 
        } else { 
            set-ZResult $Result
            $ExitCode = if ($Result -eq "Success") { 0 } else { 1 }
            exit $ExitCode
        }
    }
    
    # --- MAIN LOGIC ---
    try {
        Show-Output "ZDX TEST with Params: Initializing..."
    
        # Combine parameters into a simple message
        $FullMessage = "$Greeting, $Name!"
        
        Show-Output "Final Message: $FullMessage"
        
        # Final Result and Process Exit
        Show-Result "Success"
    
    } catch {
        Show-Output "An error occurred: $($_.Exception.Message)"
        Show-Result "Failure"
}
```

## Signing a Script

You can sign PowerShell scripts so that they can run scripts under `AllSigned` and `RemoteSigned` permissions on Windows devices.

The following is an example of a signed command:

```
# ----- Pick the certificate by Thumbprint -----
# LocalMachine (if you created it there):
$cert = Get-ChildItem Cert:\LocalMachine\My -CodeSigningCert | Where-Object { $_.Subject -eq "CN=
ZDX-ScriptSigner
" }

# If multiple matches, select Thumbprint explicitly:
# $thumb = ($cert | Select-Object -First 1).Thumbprint
# $cert = ($cert | Where-Object Thumbprint -eq $thumb)

# ----- Sign the script (timestamp recommended so signature survives cert expiry) -----
$scriptPath = "C:\test_script.ps1"   # change to your script path
Set-AuthenticodeSignature -FilePath $scriptPath -Certificate $cert -TimeStampServer "http://timestamp.digicert.com"

# ----- Verify signature -----
(Get-AuthenticodeSignature -FilePath $scriptPath).Status  # Expect: Valid
```

It is important that each script is signed with at least the following command prompt to ensure a timestamp is recorded to indicate when the code signing certificate is expiring.

```
$cert = Get-ChildItem Cert:\LocalMachine\My | Where-Object { $_.Subject -like "*CodeSigning Client*" }
Set-AuthenticodeSignature -FilePath ".\cpu_sage_script.ps1" -Certificate -TimestampServer "http://timestamp.digicert.com" $cert
```

After your script is signed, you can [upload the script to the Zscaler Admin Console](https://help.zscaler.com/zdx/managing-scripts#add).

If you have an existing CA for use, you can distribute the CA and code signing certificates by uploading them to the Windows Trust store in the following places:

- **Trusted Root Certification Authorities** > **Certificates**: Upload your CA that is used to generate the code signing certificate.
- **Trusted Publishers** > **Certificates**: Upload your code signing certificate.

[Image: Distribute code signing CA and certification]

To learn more about where to locate the Windows Trust store, refer to [Windows - Trusted Root Certification Authorities Certificate Store](https://learn.microsoft.com/en-us/windows-hardware/drivers/install/trusted-root-certification-authorities-certificate-store).

To generate a code-signing certificate for Windows, you must:

1. Create a self-signed code-signing certificate on the signer's device.
2. Export the public certificate as a `.cer` file.
3. Trust the certificate on machines that are going to run the script.
4. (Optional) Create a private key to sign the certificate on other Windows devices.

This is an example certificate to do all of those steps. The green text indicates where you can revise as needed.

```
# ----- Create the certificate -----
# System-wide (LocalMachine; requires Administrator)
$cert = New-SelfSignedCertificate -DnsName "ZDX-ScriptSigner" -Type CodeSigningCert -CertStoreLocation "Cert:\LocalMachine\My"

# ----- Export the public certificate (no private key) -----
Export-Certificate -Cert $cert -FilePath "
.\ZDX-ScriptSigner.cer
"

# ----- Trust the certificate on machines that will RUN the scripts -----
# If you created it in LocalMachine, import into LocalMachine stores:
Import-Certificate -FilePath ".\ZDX-ScriptSigner.cer" -CertStoreLocation "Cert:\LocalMachine\TrustedPublisher"
Import-Certificate -FilePath ".\ZDX-ScriptSigner.cer" -CertStoreLocation "Cert:\LocalMachine\Root"

# ----- Optional: export a PFX (with private key) for signing on other Windows machines/CI -----
$mypwd = Read-Host -AsSecureString "Enter password to protect the PFX"
Export-PfxCertificate -Cert $cert -FilePath ".\ZDX-ScriptSigner.pfx" -Password $
mypwd
```

After the certificates are created, check if they exist in the `Certificates` folders:

- **Trusted Root Certification Authorities** > **Certificates**: The CA used for generating the code signing certificate (e.g., `ZDX-ScriptSigner.cer`).
- **Trusted Publishers** > **Certificates**: The code signing certificate (e.g., `ZDX-ScriptSigner.cer`).

[Image: Check the certificates]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/release-upgrade-summary-2020","lastmod":"2020-10-20T22:29Z","nid":"1358971"} -->
## Release Upgrade Summary (2020)

- Source: https://help.zscaler.com/zdx/release-upgrade-summary-2020
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Release Notes > Release Upgrade Summary (2020)
- Last modified: 2020-10-20T22:29Z
- Summary: Zscaler Digital Experience (ZDX) Release Upgrade Summary for service updates deployed per cloud in 2020.

This article provides a summary of all new features and enhancements per Zscaler cloud for Zscaler Digital Experience (ZDX). Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/release-upgrade-summary-2021","lastmod":"2022-02-28T13:12Z","nid":"1374116"} -->
## Release Upgrade Summary (2021)

- Source: https://help.zscaler.com/zdx/release-upgrade-summary-2021
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Release Notes > Release Upgrade Summary (2021)
- Last modified: 2022-02-28T13:12Z
- Summary: Zscaler Digital Experience (ZDX) Release Upgrade Summary for service updates deployed per cloud in 2021.

This article provides a summary of all new features and enhancements per Zscaler cloud for Zscaler Digital Experience (ZDX). Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/release-upgrade-summary-2022","lastmod":"2022-03-14T09:52Z","nid":"1388626"} -->
## Release Upgrade Summary (2022)

- Source: https://help.zscaler.com/zdx/release-upgrade-summary-2022
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Release Notes > Release Upgrade Summary (2022)
- Last modified: 2022-03-14T09:52Z
- Summary: Zscaler Digital Experience (ZDX) Release Upgrade Summary for service updates deployed per cloud in 2022.

This article provides a summary of all new features and enhancements per Zscaler cloud for Zscaler Digital Experience (ZDX). Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/release-upgrade-summary-2023","lastmod":"2023-04-04T07:06Z","nid":"1440731"} -->
## Release Upgrade Summary (2023)

- Source: https://help.zscaler.com/zdx/release-upgrade-summary-2023
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Release Notes > Release Upgrade Summary (2023)
- Last modified: 2023-04-04T07:06Z
- Summary: Zscaler Digital Experience (ZDX) Release Upgrade Summary for service updates deployed per cloud in 2023.

This article provides a summary of all new features and enhancements per Zscaler cloud for Zscaler Digital Experience (ZDX). Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/release-upgrade-summary-2024","lastmod":"2024-12-23T09:42Z","nid":"1473296"} -->
## Release Upgrade Summary (2024)

- Source: https://help.zscaler.com/zdx/release-upgrade-summary-2024
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Release Notes > Release Upgrade Summary (2024)
- Last modified: 2024-12-23T09:42Z
- Summary: Zscaler Digital Experience (ZDX) Release Upgrade Summary for service updates deployed per cloud in 2024.

This article provides a summary of all new features and enhancements per Zscaler cloud for Zscaler Digital Experience (ZDX). Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/release-upgrade-summary-2025","lastmod":"2026-07-13T17:31Z","nid":"1515696"} -->
## Release Upgrade Summary (2025)

- Source: https://help.zscaler.com/zdx/release-upgrade-summary-2025
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Release Notes > Release Upgrade Summary (2025)
- Last modified: 2026-07-13T17:31Z
- Summary: Zscaler Digital Experience (ZDX) Release Upgrade Summary for service updates deployed per cloud in 2025.

This article provides a summary of all new features and enhancements per Zscaler cloud for Zscaler Digital Experience (ZDX). Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/release-upgrade-summary-2026","lastmod":"2026-08-07T08:09Z","nid":"1534310"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/zdx/release-upgrade-summary-2026
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-08-07T08:09Z
- Summary: Zscaler Digital Experience (ZDX) Release Upgrade Summary for service updates deployed per cloud in 2026.

This article provides a summary of all new features and enhancements per Zscaler cloud for Zscaler Digital Experience (ZDX). Zscaler will email a notification to your organization's registered support contacts approximately one week before your cloud is upgraded. To see scheduled maintenance updates for your cloud, visit the [Trust Portal](https://trust.zscaler.com).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/remediation-errors","lastmod":"2026-03-01T06:06Z","nid":"1532069"} -->
## Remediation Errors

- Source: https://help.zscaler.com/zdx/remediation-errors
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Remediation > Remediation Errors
- Last modified: 2026-03-01T06:06Z
- Summary: Information on error messages when you use Remediation.

The following table provides a list of error messages you might encounter when running a script for a Remediation job:

| Error Message | Internal Reason | Description | Recommended Action |
| --- | --- | --- | --- |
| Zscaler Internal Error | ZUPM_WORKFLOW_ECODE_INTERNAL_ERR | There was an unexpected internal error. | No action required. |
| Workflow failed due to timeout during execution | ZUPM_WORKFLOW_ECODE_EXECUTION_TIMEOUT | The Remediation job reached its expiration and cannot run anymore. | Extend the Remediation job's expiration. |
| Workflow expired before it could start. | ZUPM_WORKFLOW_ECODE_EXPIRED_BEFORE_START | The Remediation job expired before it could start the run. | Extend the Remediation job's expiration. |
| Workflow expired during execution. | ZUPM_WORKFLOW_ECODE_EXPIRED_DURING_EXEC | The script expired during its run. | Extend the Remediation job's duration. |
| Workflow is aborted due to policy rules. | ZUPM_WORKFLOW_ECODE_POLICY_ABORTED | The Remediation job was aborted by an admin. | No action required. |
| Workflow is aborted due to Remote Execution is disabled for the user/device. | ZUPM_WORKFLOW_ECODE_REM_DISABLED | The Remediation job aborted because the Remediation Settings were not enabled for the user or device. | Enable Remediation Settings for the user or device. To learn more, see [Configuring Remediation Settings](https://help.zscaler.com/zdx/configuring-remote-script-run-settings). |
| Workflow is aborted due to task error. | ZUPM_WORKFLOW_ECODE_ABORTED_ON_TASK_ERR | The Remediation job aborted because there is a task that the user's device does not recognize or support. | Edit the script to ensure all tasks are applicable to the user's device. |
| Workflow failed due to unsupported task category received. | ZUPM_WORKFLOW_ECODE_UNSUPPORTED_TASK_CATEGORY | The Remediation job aborted because there is a task with a category that the user's device does not recognize or support. | Edit the script to ensure all tasks with a category are applicable to the user's device. The currently supported categories are `SCRIPT` and `LOG_COLLECTION`. |
| Workflow failed due to policy config for this workflow is not received. | ZUPM_WORKFLOW_ECODE_POLICY_CONFIG_MISSING | The Remediation job aborted because the script is invalid due to removal or expired certification. | Upload the Remediation job with a valid certification. |
| Workflow rescheduled due to user deferral. | ZUPM_WORKFLOW_ECODE_USER_DEFERRED | The Remediation job was rescheduled. | No action required. |
| Workflow failed due to hitting maximum defer count limit. | ZUPM_WORKFLOW_ECODE_MAX_DEFER_REACHED | The Remediation job exceeded the maximum number of Remediation jobs. | No action required. |
| Task validation failure. | ZUPM_WORKFLOW_ECODE_TASK_VALIDATION_ERR | The script contains an invalid task object, a null task, or the category is not supported. | Revise your script to contain valid task objects, ensure the task is not set to null, and ensure the category is supported. |
| Task failed due to hitting maximum retry count limit. | ZUPM_WORKFLOW_ECODE_TASK_MAX_RETRY_REACHED | The Remediation job failed and reached the maximum number of retries. | Rerun the script when the user's device is available. |
| Task is aborted due to policy rules. | ZUPM_WORKFLOW_ECODE_TASK_ABORTED | The Remediation job was aborted because an admin aborted it or there was an overriding script run. | No action required. |
| Workflow is skipped as it's not found on device. | ZUPM_WORKFLOW_ECODE_NOT_FOUND_ON_DEVICE | The Remediation job failed because an admin aborted it. | No action required. |
| Failed to download script. | ZUPM_WORKFLOW_ECODE_SCRIPT_DOWNLOAD_FAILED | The script cannot be downloaded due to the network connection or invalid URL. | Retry downloading when the network connection is stable. |
| Script certificate verification failure. | ZUPM_WORKFLOW_ECODE_SCRIPT_CERT_VALIDATION_FAILED | The script's certification is unverified or missing. | Check the client's device to see if there are missing certificates. |
| Script signature validation failure. | ZUPM_WORKFLOW_ECODE_SCRIPT_CERT_VALIDATION_FAILED | The script's certificate cannot be verified. | Contact Zscaler Support. |
| Script signature is invalid. | ZUPM_WORKFLOW_ECODE_SCRIPT_INVALID_SIGNATURE | The script's digital signature is invalid. | Check the script's properties to validate the digital signature. |
| Script is unsigned. | ZUPM_WORKFLOW_ECODE_SCRIPT_UNSIGNED | The script is not signed, or the signature is invalid. | Check the script's signature for validity as it is invalid in the Properties of Windows File Explorer. |
| Script certificate is revoked. | ZUPM_WORKFLOW_ECODE_SCRIPT_CERT_REVOKED | The script certificate is revoked. | Check if the revocation server is accessible and the OS is up-to-date. If these actions do not work, then disable the revocation check. To learn more, see [Configuring Remediation Settings](https://help.zscaler.com/zdx/configuring-remote-script-run-settings). |
| Script execution blocked by execution policy: disallowed. | ZUPM_WORKFLOW_ECODE_SCRIPT_EXECUTION_POL_DISALLOWED | The Remediation job is blocked due to Powershell Execution Policy's Restricted Mode. | No action required. |
| Script execution blocked by execution policy: only signed scripts are allowed. | ZUPM_WORKFLOW_ECODE_SCRIPT_EXECUTION_POL_SIGNED_ONLY | The Remediation job is blocked due to PowerShell Execution Policy's Restricted Mode. | No action required. |
| Unsupported script type. | ZUPM_WORKFLOW_ECODE_SCRIPT_TYPE_NOT_SUPPORTED | The Remediation job has an unsupported type. | No action required. |
| Script execution failed due to unknown result. | ZUPM_WORKFLOW_ECODE_SCRIPT_UNKNOWN_RESULT | The script failed to run due to an unknown JSON response. | No action required. |
| Script execution error or exception occurred. | ZUPM_WORKFLOW_ECODE_SCRIPT_EXECUTION_ERR | The script failed to run due to runtime errors within the script. | Check and resolve the runtime errors in the script. |
| Script execution failed due to timeout. | ZUPM_WORKFLOW_ECODE_SCRIPT_EXECUTION_TIMEDOUT | The script failed to run due to the defined timeout. | Extend the runtime of the Remediation job. To learn more, see [Viewing and Managing Remediation jobs](https://help.zscaler.com/zdx/viewing-and-managing-remote-script-runs). |
| Script execution aborted. | ZUPM_WORKFLOW_ECODE_SCRIPT_EXECUTION_ABORTED | The Remediation job was aborted by an admin. | No action required. |
| Script execution is deferred by user action. | ZUPM_WORKFLOW_ECODE_SCRIPT_RUN_USER_DEFERRED | The user postponed the Remediation job. | No action required. |
| Script execution is declined by user action. | ZUPM_WORKFLOW_ECODE_SCRIPT_RUN_USER_DECLINED | The user declined the Remediation job. | No action required. |
| Script execution failed due to hitting maximum defer count limit. | ZUPM_WORKFLOW_ECODE_LOG_FETCH_MAX_DEFER_REACHED | The Remediation job did not run because you have exceeded the maximum number of Remediation jobs. | Wait to complete the other Remediation jobs and then re-run the job. To learn more, see [Ranges & Limitations](https://help.zscaler.com/zdx/ranges-limitations). |
| Script execution failed due to handler init failure. | ZUPM_WORKFLOW_ECODE_SCRIPT_HANDLER_INIT_FAILED | The Remediation job failed to initialize. | Contact Zscaler Support |
| Script job ID not found in ZCC SE Platform. | ZUPM_WORKFLOW_ECODE_SCRIPT_JOB_ID_NOT_FOUND | The Remediation job has an invalid ID in the Zscaler Client Connector Script Execution (SE) Platform. | No action required. |
| Script execution failed due to Script Orchestrator Service is not started or unavailable. | ZUPM_WORKFLOW_ECODE_SCRIPT_ORCHESTRATOR_RPC_FAILURE | The Remediation job cannot run because of the unavailable Script Orchestrator Service. | No action required. |
| Log collection error or exception occurred. | ZUPM_WORKFLOW_ECODE_LOG_FETCH_ERR | Unable to collect data logs due to an internal error. | No action required. |
| Log collection failed due to timeout. | ZUPM_WORKFLOW_ECODE_LOG_FETCH_TIMEDOUT | Unable to collect data logs because the upload process exceeds runtime. | No action required. |
| Internal error when handling notification. | ZUPM_WORKFLOW_ECODE_NOTIFICATION_INTERNAL_ERR | An internal error occurred during notification. | Contact Zscaler Support. |
| Failed to send user notification. | ZUPM_WORKFLOW_ECODE_NOTIFICATION_SEND_FAILURE | Unable to send user notification. | Contact Zscaler Support. |
| No response from the user. | ZUPM_WORKFLOW_ECODE_NOTIFICATION_NO_RESPONSE | Unable to receive the user's response due to timeout. | No action required. |
| Notification handler init failure. | ZUPM_WORKFLOW_ECODE_NOTIFICATION_HANDLER_INIT_FAILED | Unable to send notification due to error initializing. | Contact Zscaler Support. |
| Zscaler Notification Framework is disabled. | ZUPM_WORKFLOW_ECODE_NOTIFICATION_FRAMEWORK_DISABLED | Unable to send notification because Zscaler Notification framework is disabled. | No action required. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/servicenow-webhook-configuration-guide","lastmod":"2026-07-10T10:03Z","nid":"1414521"} -->
## ServiceNow Webhook Configuration Guide

- Source: https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > ServiceNow Configuration Guides > ServiceNow Webhook Configuration Guide
- Last modified: 2026-07-10T10:03Z
- Summary: Information on configuring webhook integration for ServiceNow Incident Management and Event Management.

ZDX supports the integration of IT Service Management (ITSM) via Incident Management (IM) and IT Operations Management (ITOM) via Event Management (EM). You can configure webhooks to deliver alerts about an application, device, or network performance. You can also use webhooks in an alert rule and configure multiple alert rules for the webhook. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule).

## Prerequisites

Ensure the following steps are completed prior to integrating with ServiceNow Event Management:

- Enable ITOM. To learn more, refer to [ServiceNow IT Operations Management](https://www.servicenow.com/products/it-operations-management.html).
- Request the EM plugin and install it from the store when it is available. To learn more, refer to ServiceNow's [Zscaler Digital Experience: Event Management](https://store.servicenow.com/sn_appstore_store.do#!/store/application/161c06b787ad8110419aff77cebb35f8/1.0.0?referer=%2Fstore%2Fsearch%3Flistingtype%3Dallintegrations%25253Bancillary_app%25253Bcertified_apps%25253Bcontent%25253Bindustry_solution%25253Boem%25253Butility%25253Btemplate%26q%3Dzscaler&sl=sh).

To configure a webhook for ServiceNow, you must:

- 1. Create Service users and an OAuth 2.0 API endpoint in ServiceNow.
- 2. Create a ZDX Role in the Zscaler Admin Console.
- 3. Configure a webhook in the Zscaler Admin Console.
- 4. Confirm the webhook test result and Deep Tracing connection on ServiceNow.

Deep Tracing is a type of [Diagnostics session](https://help.zscaler.com/zdx/starting-new-diagnostics-session). In ServiceNow, you can only configure for Deep Tracing.

Prior to creating a webhook, you must create users in ServiceNow and admins in the Zscaler Admin Console. Creating an OAuth 2.0 API endpoint is optional based on your authentication requirements.

In your ServiceNow Console:

- Create the required service user.
- (Optional) Create an OAuth 2.0 API endpoint.

1. Go to **All** > **User Administration** > **Users**.
2. Click **New**.
3. Enter your **User ID**. You can enter an email address as your User ID. Zscaler recommends using an email address in lieu of a user ID.
4. Select the **Web Services Access only** checkbox.
5. Click **Submit**.
6. Reopen the user's record and click **Set Password**.
7. In the **Set Password** window:
  1. Click **Generate**.
  2. **Copy** the generated password. Save the user ID and password information. See image.
  3. Click **Save Password**.
8. Click **Set Password**on the User Record to save the password.
9. In the Roles related list, click **Edit**. See image.
10. Based on your service user's access needs, assign the respective role to the service user:
  - Incident Management
  - Event Management
11. Click **Save**.
12. Click **Update** on the user's record.

1. Search for `oauth` and go to **Application Registry** > **New** > **Create an OAuthAPI endpoint for external clients**. See image.
2. In the **Application Registries** page: See image.
  - **Name**: Enter a name for the OAuth support.
  - **Client ID**: This is not configurable. Save the Client ID for later use.
  - **Client Secret**: If this field is left empty, it auto-generates a Client Secret code. Save this Client Secret for later use.
  - **Redirect UR**L: Enter your URL based on the cloud name of the ZDX tenant. The format must be: ``https://admin.``<cloud name>``/zdx/admin/webhooks``Replace `<cloud name`> with your designated cloud name. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).
  - **Refresh Token Lifespan**: Enter your refresh token lifespan. Zscaler recommends a lifespan of 5 years from the current date.
  - **Access Token Lifespan**: Enter your access token lifespan. Zscaler recommends a lifespan of 1 hour to maximize reuse.
3. Click **Submit** to save your settings.

1. [Add a ZDX Role](https://help.zscaler.com/zdx/adding-zdx-roles) to have the following permissions:
  - **Diagnostics**: Full
  - **Webhooks**: Full
  - **User & Device Names**: Visible
  - **Configuration Access**: Full
  - **Alerts**: Full
  - **UCaaS Monitoring**: View Only
2. Assign the ServiceNow Role from the previous step to an existing ZDX Admin or by [adding a ZDX Admin](https://help.zscaler.com/zdx/adding-zdx-admins).
3. Click **Save**.
4. [Activate the changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
2. Click **Add Webhook**.
3. In the **Add Webhook** window:
  - **Name**: Enter a name for the webhook.
  - **Status**: Select **Enabled** to enable the webhook.
  - **URL**: Enter one of the following URLs based on the integration:
    - Incident Management
    - Event Management
  - **Authentication Type**: Select your authentication type.
    - Basic
    - OAuth
4. Click **Test Webhook** to see if it functions correctly. Test Webhook for OAuth does not post a test message on ServiceNow. Instead, it acquires the OAuth token.
5. If the test is successful, a message indicating success appears.
  1. Click **Save**.
  2. [Activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).
6. If the test is unsuccessful, an error message appears.
  - To troubleshoot the error, check for issues in the **URL** or the **Authentication Type** fields.
  - If the error persists, record to provide details of your error (e.g., screenshots or small video). Click **Cancel** so that the webhook configuration containing errors is not saved, and contact Zscaler Support with the error information.

To confirm if the webhook test result is on ServiceNow:

1. Go to the ServiceNow Service Portal.
2. In the **Filter Navigator** search bar, enter `Zscaler Digital Experience`.
3. From the menu, select **ServiceNow Incidents** or **ServiceNow Events** module to see if the test webhook is recorded. See image.

To confirm the Deep Tracing connection is on ServiceNow for IM:

1. Go to the **ServiceNow**portal.
2. In the **Filter Navigator** search bar, enter `Zscaler Digital Experience`.
3. From the menu, select the **ServiceNow Incidents** module.
4. Open your targeted incident record.
5. Go to the Deep Tracing section and see if one of the following occurs to confirm the Deep Tracing connection:
  - The information message, *There are no related Deep Tracing Sessions*, indicates there are no existing deep tracing sessions related to the current incident.
  - A table of Deep Tracing sessions.

After the webhook configuration is complete and the ServiceNow users are assigned the Incident Management role, x_zsca2_zdx_manage.zdx_management, you can configure the **Settings** and **Mapping** modules in the ZDX application on ServiceNow to meet your alerting requirements. To learn more, see [Understanding the ZDX Application Fields on ServiceNow](https://help.zscaler.com/zdx/mapping-zdx-application-fields-servicenow).

If your service user requires access to IM:

- For web service users that require access to IM, add the **x_zsca2_zdx_manage.zdx_management** role.
- For users working with the Deep Tracing feature, add the **x_zsca2_zdx_manage.zdx_dt_management** role.

If your service user requires access to EM:

- For web service users that require access to EM, add the **evt_mgmt_integration** role.
- For active users that require managing the application, add the **x_zsca2_zdx_manage.zdx_em_admin** and **evt_mgmt_user** roles.

```
https://
<your-instance-ID>
.service-now.com/api/x_zsca2_zdx_manage/incident_management_api
```

To learn more about installation and configuration details for ServiceNow Incident Management for ZDX, refer to the [ServiceNow Store](https://store.servicenow.com/sn_appstore_store.do#!/store/application/faa68b0987510510d52bca2e0ebb358d/2.0.1?referer=%2Fstore%2Fsearch%3Flistingtype%3Dallintegrations%25253Bancillary_app%25253Bcertified_apps%25253Bcontent%25253Bindustry_solution%25253Boem%25253Butility%25253Btemplate%26q%3Dzscaler&sl=sh).

```
https://
<your-instance-ID>
.service-now.com/api/global/em/jsonv2
```

To learn more about installation and configuration details for ServiceNow Event Management for ZDX, refer to the [ServiceNow Store](https://store.servicenow.com/sn_appstore_store.do#!/store/application/161c06b787ad8110419aff77cebb35f8/1.0.0?referer=%2Fstore%2Fsearch%3Flistingtype%3Dallintegrations%25253Bancillary_app%25253Bcertified_apps%25253Bcontent%25253Bindustry_solution%25253Boem%25253Butility%25253Btemplate%26q%3Dzscaler&sl=sh).

[Image: Copy your generated password on the service user]

[Image: Edit the user]

ServiceNow Incidents

[Image: Incidents Test Results]

ServiceNow Events

[Image: Events Test Result]

For Basic Authentication:

- **Username**: Enter the username of the previously created service user.
- **Password**: Enter the password of the previously created service user.

Click **Save**to save your webhook configuration.

For OAuth Authentication:

- **Application**: Select **ServiceNow**.
- **Client ID**: Enter the Client ID of the previously created OAuth 2.0 API endpoint.
- **Client Secret**: Enter the Client Secret of the previously created OAuth 2.0 API endpoint.
- **Refresh Token Expiration**: Select the date of when the refresh token expires from the previously created OAuth API 2.0 endpoint.
- Click **Authenticate Tenant**.

Click **Save**to save your webhook configuration.

[Image: Create an OAuth API endpoint for external clients]

[Image: Enter OAuth Details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/servicenow-webhook-configuration-guide-developers","lastmod":"2026-07-10T10:03Z","nid":"1367296"} -->
## ServiceNow Webhook Configuration Guide for Developers

- Source: https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide-developers
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > ServiceNow Configuration Guides > ServiceNow Webhook Configuration Guide for Developers
- Last modified: 2026-07-10T10:03Z
- Summary: How to configure webhooks using ServiceNow with a ServiceNow Developer account.

This guide provides information on configuring webhooks using ServiceNow for alerts in ZDX. This article provides a sample configuration that you can build upon per user requirements. The ServiceNow webhook sample configuration here uses a ServiceNow developer configuration.

## Creating a ServiceNow Developer Account

You must create a ServiceNow Developer Account to configure instances where you can enter Scripted REST APIs.

1. Create a ServiceNow developer account at [https://developer.servicenow.com/](https://developer.servicenow.com/).
2. Create a developer instance. See image. This instance automatically hibernates, so you should wake it every 24 hours. Also, if you don’t use it for 10 days, it is reclaimed.
3. In the **Filter Navigator**search bar on the left-side navigation menu, enter `rest`.
4. Go to **Scripted Web Services** > **Scripted REST APIs**.
5. Click **New**to create a new resource. See image.
6. Enter the API name and click **Submit**.
7. Search by the API name for the newly created Scripted REST API to access its resource page.
8. On the bottom tabs of the same page, under **Resources**, select **New**. See image.
9. On the **Scripted Rest Resource** page, create an authentication based on your needs. See image. If you are using OAuth for authentication, you must create an OAuth 2.0 API endpoint in ServiceNow. To learn more, see [ServiceNow Webhook Configuration Guide](https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide).
  - Basic
  - Token
  - OAuth

## Creating a Webhook in the Zscaler Admin Console

To create a webhook in the Zscaler Admin Console:

1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
2. Click **Add Webhook**.
3. In the **Add Webhook** window:
  - **Name**: Enter a name for the webhook.
  - **Status**: Select **Enabled** to enable the webhook.
  - **URL**: Enter the ServiceNow developer instance hostname and concatenate it with the API name from the Scripted REST service page. For example: `https://dev91028.service-now.com/api/516508/alertsincident`
  - **Authentication Type**: Select **Basic**, **Token**, or **OAuth** based on your authentication from the previous step. To learn more, see [ServiceNow Webhook Configuration Guide](https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide) and [Configuring Webhooks](https://help.zscaler.com/zdx/configuring-webhooks).
    - For **Basic**, enter the credentials from when the user was created.
    - For **Token**, enter the same token as you are using in your script.
    - For **OAuth**, enter the required fields from your OAuth 2.0 API endpoint that you created in ServiceNow.
4. Click **Test Webhook** to verify that it functions correctly.
5. To view the webhook notification on ServiceNow:
  1. In the **Filter Navigator** search bar in the left-side navigation menu, enter `incidents`.
  2. Go to **Service Desk** > **Incidents**. You see a list of incidents, and you can double-click an incident to view individual incident details. The webhook output is also viewable here for integration with other third-party providers. See image.
6. Click **Save** and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

Now, when your alert triggers and sends a webhook notification, an entry is created in the ServiceNow Incident list.

To use basic authentication for your webhook, you must create a non-admin user on ServiceNow so that admin credentials are shared.

1. **Name**: Enter your preferred name.
2. **HTTP method**: Select **POST**.
3. Select the **Active** checkbox.
4. Copy and paste the following script into the Script box.
  - Script for Basic authentication
5. On the **Security** tab, select the following checkboxes:
  - **Requires authentication**
  - **Requires ACL authorization**
6. Click **Submit**. See image.
7. In the **Filter Navigator** search bar in the left-side navigation menu, enter `users`.
8. Go to **Users and Groups** > **Users** > **New**.
9. Enter a User ID and password for this user.
10. Click **Submit**. See image.

1. **Name**: Enter your preferred name.
2. **HTTP method**: Select **POST**.
3. Deselect the **Requires authentication** checkbox.
4. Copy and paste the following script into the **Script** box. Remember to edit the token in your script (`12345679` in the following script) to the one your webhook in ZDX is using.
  - Script for Token authentication
5. Click **Submit**.

Your resource populates on the **Scripted REST Service** page.

After creating the OAuth 2.0 API endpoint in ServiceNow, enter the following on the **Scripted Rest Resource** page:

1. **Name**: Enter your preferred name.
2. **HTTP method**: Select **POST**.
3. Deselect the **Requires authentication** checkbox.
4. Copy and paste the following script into the **Script** box. Since the token is not applicable, you can leave it as `12345679`.
  - Script for Oauth authentication
5. Click **Submit**.

Your resource populates on the **Scripted REST Service** page.

```
(function process(/*RESTAPIRequest*/ request, /*RESTAPIResponse*/ response) {
var reqData = request.body.dataString; 
    var jData = new global.JSON().decode(reqData);
    var alertId = jData.alertId;
    var ruleName = jData.ruleName; 
    if ( ruleName.equalsIgnoreCase("test") ) {
	// test webhook
	return;
    }
   
    var status = jData.status;
    var zdxurl = jData.zdxUrl;
    var startTime = jData.startTime;
    var endTime = jData.endTime;
    var state;
    if ( status == "STARTED")
            state = 2; // in progress
        else if ( status == "ENDED_ON_RULE_DEL" || status == "ENDED_ON_RULE_MODIFY")
            state = 8; // cancelled
        else 
            state = 6; // resolved
    
    var impact = 1;
    if ( jData.severity == "High" ) 
            impact = 1;
        else if ( jData.severity == "Medium" ) 
            impact = 2;
        else if ( jData.severity == "Low" )
            impact = 3;
    
    var gdt = new GlideDateTime();
    var gr = new GlideRecord('incident'); 
    gr.addQuery('short_description', 'CONTAINS 1', alertId);
    gr.query();
    
	if ( jData.alertType == "Incident") {
		impacted = jData.geolocationCount + " Geolocations\n" + jData.impactedDeviceCount + " Devices\n" + jData.impactedUserCount + " Users\n";
		context = jData.context;
        epicenter = context;
    } else if ( jData.alertType == "ZProbe") {
		impacted = "Zscaler Hosted Locations: " + jData.zscaler_hosted_locations + "\n";
    } else {
		impacted = jData.geolocationCount + " Geolocations\n" + jData.deptCount + " Departments\n" + jData.osverCount + " OS Versions\n" + jData.impactedDeviceCount + " Devices\n";
    }
    var criteria = jData.criteria;
    var condOp = criteria.op;
    var conditions = criteria.conditions;
    
    var text = "";
    for (i = 0; i < conditions.length; i++) { 
        if ( i != 0) 
            text += condOp + " ";
        text += conditions[i].conditionString;
        
        if ( conditions[i].isHit)
            text += "*";
        if (conditions[i].stats ) {
            text += ", min: " + conditions[i].stats.min + ", max: "  + conditions[i].stats.max + ", avg: "  + conditions[i].stats.avg;
        }
        
        text += "\n";
    }
    
    var desc = "";	
	if ( jData.alertType == "Incident") {
        if ( status == "STARTED" )
			desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered" + "\n\nEpicenter: " + epicenter.attribute + ", " + epicenter.epicenter;
        else 
			desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered" + "\n\nEpicenter: " + epicenter.attribute + ", " + epicenter.epicenter;
	} else if ( jData.alertType == "ZProbe") {
        if ( status == "STARTED" )
			desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered";
        else 
			desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered";
	} else {
        if ( status == "STARTED" )
			desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered";
        else 
			desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered";
	}
    var category = "ZDX Alerts";
    if (gr.next()) {
        gr.state = state;
        gr.impact = impact;
        
        if ( state == 6 ) {
            gr.close_code = "Alert resolved";
        } else if ( state == 8 ) {
            gr.close_code = "Rule deleted or modified";
        }
        gr.close_notes = " ";
        
        var sd = gr.short_description;
		if ( jData.alertType == "ZProbe") {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.zscaler_hosted_locations + " impacted";
		} else {
			gr.short_description = "ZDX Alert " + alertId + " " + jData.impactedDeviceCount + " devices impacted";
		}
    
        gr.description = desc;
        gr.u_linked_incident = zdxurl;
        gr.u_alert_details = reqData;
        
        gdt.setValue(startTime*1000);
        gr.work_start = gdt.getDisplayValue();
        
        if ( status == "STARTED") {
            gr.work_end = "";
        } else {
            gdt.setValue(endTime*1000);
            gr.work_end = gdt.getDisplayValue();
        }
        //gr.caller_id = e9f176e2db54101087f7478239961941;
        gr.update();
    } 
    else {
        gr.newRecord(); 
        if ( state == 2 ) // in progress
            state = 1; // new
        gr.state = state;
        gr.impact = impact;
        gr.category = category;
        
        if ( state == 6 ) {
            gr.close_code = "Alert resolved";
        } else if ( state == 8 ) {
            gr.close_code = "Rule deleted or modified";
        }
        gr.close_notes = " ";
        
		if ( jData.alertType == "ZProbe") {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.zscaler_hosted_locations + " impacted";
		} else {
			gr.short_description = "ZDX Alert " + alertId + " " + jData.impactedDeviceCount + " devices impacted";
		}
    
        gr.description = desc;
        gr.u_linked_incident = zdxurl;
        gr.u_alert_details = reqData;
        
        gdt.setValue(startTime*1000);
        gr.work_start = gdt.getDisplayValue();
        
        if ( status == "STARTED") {
            gr.work_end = "";
        } else {
            gdt.setValue(endTime*1000);
            gr.work_end = gdt.getDisplayValue();
        }
        
        //gr.caller_id = e9f176e2db54101087f7478239961941;
        var result = gr.insert(); 
    }
})(request, response);
```

```
(function process(/*RESTAPIRequest*/ request, /*RESTAPIResponse*/ response) {
    
    var headers = request.headers;
    var authHeader = request.getHeader('authorization');
    var token = authHeader.split(" ");
    
    if (token[1] != "12345679")
        response.setError(new sn_ws_err.BadRequestError('Bad token'));
    var reqData = request.body.dataString; 
    var jData = new global.JSON().decode(reqData);
    var alertId = jData.alertId;
    var ruleName = jData.ruleName; 
    if ( ruleName.equalsIgnoreCase("test") ) {
    // test webhook
    return;
    }
   
    var status = jData.status;
    var zdxurl = jData.zdxUrl;
    var startTime = jData.startTime;
    var endTime = jData.endTime;
    var state;
    if ( status == "STARTED")
            state = 2; // in progress
        else if ( status == "ENDED_ON_RULE_DEL" || status == "ENDED_ON_RULE_MODIFY")
            state = 8; // cancelled
        else 
            state = 6; // resolved
    
    var impact = 1;
    if ( jData.severity == "High" ) 
            impact = 1;
        else if ( jData.severity == "Medium" ) 
            impact = 2;
        else if ( jData.severity == "Low" )
            impact = 3;
    
    var gdt = new GlideDateTime();
    var gr = new GlideRecord('incident'); 
    gr.addQuery('short_description', 'CONTAINS 1', alertId);
    gr.query();
    
    if ( jData.alertType == "Incident") {
        impacted = jData.geolocationCount + " Geolocations\n" + jData.impactedDeviceCount + " Devices\n" + jData.impactedUserCount + " Users\n";
        context = jData.context;
        epicenter = context;
    } else if ( jData.alertType == "ZProbe") {
        impacted = "Zscaler Hosted Locations: " + jData.zscaler_hosted_locations + "\n";
    } else {
        impacted = jData.geolocationCount + " Geolocations\n" + jData.deptCount + " Departments\n" + jData.osverCount + " OS Versions\n" + jData.impactedDeviceCount + " Devices\n";
    }
    var criteria = jData.criteria;
    var condOp = criteria.op;
    var conditions = criteria.conditions;
    
    var text = "";
    for (i = 0; i < conditions.length; i++) { 
        if ( i != 0) 
            text += condOp + " ";
        text += conditions[i].conditionString;
        
        if ( conditions[i].isHit)
            text += "*";
        if (conditions[i].stats ) {
            text += ", min: " + conditions[i].stats.min + ", max: "  + conditions[i].stats.max + ", avg: "  + conditions[i].stats.avg;
        }
        
        text += "\n";
    }
    
    var desc = "";  
    if ( jData.alertType == "Incident") {
        if ( status == "STARTED" )
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered" + "\n\nEpicenter: " + epicenter.attribute + ", " + epicenter.epicenter;
        else 
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered" + "\n\nEpicenter: " + epicenter.attribute + ", " + epicenter.epicenter;
    } else if ( jData.alertType == "ZProbe") {
        if ( status == "STARTED" )
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered";
        else 
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered";
    } else {
        if ( status == "STARTED" )
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered";
        else 
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered";
    }
    var category = "ZDX Alerts";
    if (gr.next()) {
        gr.state = state;
        gr.impact = impact;
        
        if ( state == 6 ) {
            gr.close_code = "Alert resolved";
        } else if ( state == 8 ) {
            gr.close_code = "Rule deleted or modified";
        }
        gr.close_notes = " ";
        
        var sd = gr.short_description;
        if ( jData.alertType == "ZProbe") {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.zscaler_hosted_locations + " impacted";
        } else {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.impactedDeviceCount + " devices impacted";
        }
    
        gr.description = desc;
        gr.u_linked_incident = zdxurl;
        gr.u_alert_details = reqData;
        
        gdt.setValue(startTime*1000);
        gr.work_start = gdt.getDisplayValue();
        
        if ( status == "STARTED") {
            gr.work_end = "";
        } else {
            gdt.setValue(endTime*1000);
            gr.work_end = gdt.getDisplayValue();
        }
        gr.update();
    } 
    else {
        gr.newRecord(); 
        if ( state == 2 ) // in progress
            state = 1; // new
        gr.state = state;
        gr.impact = impact;
        gr.category = category;
        
        if ( state == 6 ) {
            gr.close_code = "Alert resolved";
        } else if ( state == 8 ) {
            gr.close_code = "Rule deleted or modified";
        }
        gr.close_notes = " ";
        
        if ( jData.alertType == "ZProbe") {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.zscaler_hosted_locations + " impacted";
        } else {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.impactedDeviceCount + " devices impacted";
        }
    
        gr.description = desc;
        gr.u_linked_incident = zdxurl;
        gr.u_alert_details = reqData;
        
        gdt.setValue(startTime*1000);
        gr.work_start = gdt.getDisplayValue();
        
        if ( status == "STARTED") {
            gr.work_end = "";
        } else {
            gdt.setValue(endTime*1000);
            gr.work_end = gdt.getDisplayValue();
        }
        
        var result = gr.insert(); 
    }
})(request, response);
```

```
(function process(/*RESTAPIRequest*/ request, /*RESTAPIResponse*/ response) {
    
    var headers = request.headers;
    var authHeader = request.getHeader('authorization');
    var token = authHeader.split(" ");
    
    if (token[1] != "12345679")
        response.setError(new sn_ws_err.BadRequestError('Bad token'));
    var reqData = request.body.dataString; 
    var jData = new global.JSON().decode(reqData);
    var alertId = jData.alertId;
    var ruleName = jData.ruleName; 
    if ( ruleName.equalsIgnoreCase("test") ) {
    // test webhook
    return;
    }
   
    var status = jData.status;
    var zdxurl = jData.zdxUrl;
    var startTime = jData.startTime;
    var endTime = jData.endTime;
    var state;
    if ( status == "STARTED")
            state = 2; // in progress
        else if ( status == "ENDED_ON_RULE_DEL" || status == "ENDED_ON_RULE_MODIFY")
            state = 8; // cancelled
        else 
            state = 6; // resolved
    
    var impact = 1;
    if ( jData.severity == "High" ) 
            impact = 1;
        else if ( jData.severity == "Medium" ) 
            impact = 2;
        else if ( jData.severity == "Low" )
            impact = 3;
    
    var gdt = new GlideDateTime();
    var gr = new GlideRecord('incident'); 
    gr.addQuery('short_description', 'CONTAINS 1', alertId);
    gr.query();
    
    if ( jData.alertType == "Incident") {
        impacted = jData.geolocationCount + " Geolocations\n" + jData.impactedDeviceCount + " Devices\n" + jData.impactedUserCount + " Users\n";
        context = jData.context;
        epicenter = context;
    } else if ( jData.alertType == "ZProbe") {
        impacted = "Zscaler Hosted Locations: " + jData.zscaler_hosted_locations + "\n";
    } else {
        impacted = jData.geolocationCount + " Geolocations\n" + jData.deptCount + " Departments\n" + jData.osverCount + " OS Versions\n" + jData.impactedDeviceCount + " Devices\n";
    }
    var criteria = jData.criteria;
    var condOp = criteria.op;
    var conditions = criteria.conditions;
    
    var text = "";
    for (i = 0; i < conditions.length; i++) { 
        if ( i != 0) 
            text += condOp + " ";
        text += conditions[i].conditionString;
        
        if ( conditions[i].isHit)
            text += "*";
        if (conditions[i].stats ) {
            text += ", min: " + conditions[i].stats.min + ", max: "  + conditions[i].stats.max + ", avg: "  + conditions[i].stats.avg;
        }
        
        text += "\n";
    }
    
    var desc = "";  
    if ( jData.alertType == "Incident") {
        if ( status == "STARTED" )
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered" + "\n\nEpicenter: " + epicenter.attribute + ", " + epicenter.epicenter;
        else 
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered" + "\n\nEpicenter: " + epicenter.attribute + ", " + epicenter.epicenter;
    } else if ( jData.alertType == "ZProbe") {
        if ( status == "STARTED" )
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered";
        else 
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered";
    } else {
        if ( status == "STARTED" )
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\nImpacted: \n\n" + impacted + "\n\n * - Condition triggered";
        else 
            desc = "Rule Name: " + ruleName + "\n\nAlert criteria triggers: \n\n" + text + "\n\n * - Condition triggered";
    }
    var category = "ZDX Alerts";
    if (gr.next()) {
        gr.state = state;
        gr.impact = impact;
        
        if ( state == 6 ) {
            gr.close_code = "Alert resolved";
        } else if ( state == 8 ) {
            gr.close_code = "Rule deleted or modified";
        }
        gr.close_notes = " ";
        
        var sd = gr.short_description;
        if ( jData.alertType == "ZProbe") {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.zscaler_hosted_locations + " impacted";
        } else {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.impactedDeviceCount + " devices impacted";
        }
    
        gr.description = desc;
        gr.u_linked_incident = zdxurl;
        gr.u_alert_details = reqData;
        
        gdt.setValue(startTime*1000);
        gr.work_start = gdt.getDisplayValue();
        
        if ( status == "STARTED") {
            gr.work_end = "";
        } else {
            gdt.setValue(endTime*1000);
            gr.work_end = gdt.getDisplayValue();
        }
        gr.update();
    } 
    else {
        gr.newRecord(); 
        if ( state == 2 ) // in progress
            state = 1; // new
        gr.state = state;
        gr.impact = impact;
        gr.category = category;
        
        if ( state == 6 ) {
            gr.close_code = "Alert resolved";
        } else if ( state == 8 ) {
            gr.close_code = "Rule deleted or modified";
        }
        gr.close_notes = " ";
        
        if ( jData.alertType == "ZProbe") {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.zscaler_hosted_locations + " impacted";
        } else {
            gr.short_description = "ZDX Alert " + alertId + " " + jData.impactedDeviceCount + " devices impacted";
        }
    
        gr.description = desc;
        gr.u_linked_incident = zdxurl;
        gr.u_alert_details = reqData;
        
        gdt.setValue(startTime*1000);
        gr.work_start = gdt.getDisplayValue();
        
        if ( status == "STARTED") {
            gr.work_end = "";
        } else {
            gdt.setValue(endTime*1000);
            gr.work_end = gdt.getDisplayValue();
        }
        
        var result = gr.insert(); 
    }
})(request, response);
```

[Image: ServiceNow Developer Instance]

[Image: Create a new ServiceNow API]

[Image: Select to fill in the API name]

[Image: Basic Authentication in ServiceNow]

[Image: Create a Non-Admin User]

[Image: Webhook JSON Format]

[Image: Scripted API Resource Page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/sharing-zdx-snapshots","lastmod":"2026-04-29T09:20Z","nid":"1462931"} -->
## Sharing ZDX Snapshots

- Source: https://help.zscaler.com/zdx/sharing-zdx-snapshots
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Sharing ZDX Snapshots
- Last modified: 2026-04-29T09:20Z
- Summary: How to share ZDX Snapshots with non-admin users.

The ZDX Snapshot feature captures the current state of a UI page, and provides a URL that admins can share with ZDX users or other admins for view-only access. ZDX Snapshot enables users without Digital Experience login access to view a subset of ZDX features while bypassing the login authentication process.

## Prerequisites

To create and share ZDX Snapshots, ensure:

- Your ZDX subscription level supports ZDX Snapshot sharing. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Your admin role is configured to share ZDX Snapshots. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Sharing Your ZDX Snapshot

You can create and share a ZDX Snapshot by clicking **Share** **Snapshot**in the upper-right corner of the following pages:

- [User details](https://help.zscaler.com/zdx/evaluating-user-details)
- [Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview)
- [Application details](https://help.zscaler.com/zdx/evaluating-application-details)
- [Alert details](https://help.zscaler.com/zdx/evaluating-individual-alert-details)
- [Diagnostics details](https://help.zscaler.com/zdx/viewing-diagnostics-session-results)
- [Incident details](https://help.zscaler.com/zdx/monitoring-incidents-dashboard)

For the user details page, the **Share Snapshot**link is enabled only when an application has been selected, and only for the selected device.

[Image: Share Snapshot link on user details page]

1. Click **Share Snapshot**. The **Share ZDX Snapshot**window appears.
2. Enter a **Name** for the ZDX Snapshot.
3. Select a time range from the **Valid For** drop-down menu, based on increments from **2 Hours** to **90 Days**. This time range represents the duration in which you can share the ZDX Snapshot.
4. Enable or disable **Obfuscate Data**. If you enable the setting, select the data types from the **Obfuscation**drop-down menu to be hidden from the recipient. The availability of these data types corresponds to the recipient's privileges in viewing the ZDX Snapshot. See image.
5. Click **Next**to generate the ZDX Snapshot.
6. Click **Copy URL** to copy the generated URL and share with trusted recipients. Make sure you only share ZDX Snapshots with trusted recipients to avoid inadvertent disclosure of data. See image.
7. (Optional) Click **Manage ZDX Snapshots**to view an accrued list of ZDX Snapshots.

## Managing ZDX Snapshots

Go to **Analytics** > **Digital Experience** > **Reports** > **Snapshots** to view a history of ZDX Snapshots from an accrued list of generated URLs. You have the option to copy or delete any of the ZDX Snapshots.

- **Name**: The unique name of a generated ZDX Snapshot.
- **Created On**: The date and time when the ZDX Snapshot was created.
- **Valid Until**: The end date and time when access to the ZDX Snapshot expires. The ZDX Snapshot is no longer valid after expiration, and a message confirms the URL is not found if you attempt to share it.
- **Status**: The current state of the ZDX Snapshot (Expired, Active).
- **URL**: The generated URL for sharing the ZDX Snapshot.
- **Views**: The number of times the ZDX Snapshot URL has been generated.
- **Actions**: Copy the ZDX Snapshot URL or Delete the ZDX Snapshot.

[Image: Manage ZDX Snapshots]

## Viewing ZDX Snapshots

Users can identify a ZDX Snapshot by the green icon located in the upper-left corner of the page:

[Image: ZDX Snapshot icon and page from user's viewpoint]

A ZDX Snapshot has only limited user interaction within the page, depending on what data is obfuscated and what details are shared. For example, user interaction for the following features is disabled by default when viewing a snapshot of the user details page:

- Time range filter
- Drop-down menu filters
- Device selection
- Applications other than the application already selected
- Data points for root cause analysis other than the data point already selected
- Web Probe Metrics
- Cloud Path

[Image: Obfuscate ZDX Snapshot data]

[Image: Copy the ZDX Snapshot URL]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/slack-webhook-configuration-guide","lastmod":"2026-07-10T10:02Z","nid":"1376321"} -->
## Slack Webhook Configuration Guide

- Source: https://help.zscaler.com/zdx/slack-webhook-configuration-guide
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > Slack Webhook Configuration Guide
- Last modified: 2026-07-10T10:02Z
- Summary: Information on configuring webhooks using Slack.

This guide provides information on configuring webhooks using Slack for alerts in ZDX per user requirements. Incoming webhooks provide a simple way to post messages from ZDX to Slack. The incoming webhook provides a unique URL that sends a JSON payload with the message text.

## Configuring a Webhook for a Paid Slack Subscription

A paid Slack subscription is required to use the Slack Workflow Builder.

To configure a workflow for your Slack application to send notifications to a selected Slack channel:

1. In your Slack application:
  1. Go to**Workspace**> **Tools**> **Workflow Builder**. See image.
  2. In the **Workflow Builder**, click **Create**. See image.
    1. Enter a name for the workflow.
    2. Select **Webhook - Advanced**. See image.
    3. Add variables as needed for ZDX alerts. These variables dictate which fields from the webhook notification are displayed in the Slack notification. See image.
  3. Click **Save**.
  4. Select the action for this webhook. The action sends a message to the selected Slack channel. You can adjust the message as preferred. Click **Save**. See image.
  5. Click **Publish Changes**.
  6. In your desktop Slack app, go to **Workspace** > **Tools** > **Workflow Builder**.
  7. Select your newly created workflow and go to **Edit** > **Webhook** > **Copy URL**. See image.
2. [Create a webhook in the Zscaler Admin Console](https://help.zscaler.com/zdx/configuring-webhooks).
  1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
  2. Click **Add Webhook**.
  3. In the **Add Webhook** window:
    - **Name**: Enter a webhook name.
    - **Status**: Select **Enable**.
    - **URL**: Enter your copied URL from previous step.
    - **Authentication Type**: Select **Token**.
    - **Bearer Token**: Enter any text as a bearer token is not required.
  4. Click **Test Webhook** and verify the test result appears on the assigned Slack channel.
  5. Click **Save** and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

## Configuring a Webhook for a Free Slack Subscription

To configure a workflow for your free Slack application to send notifications to the selected Slack channel:

1. On your desktop Slack application's workspace:
  1. Go to [Slack's Your Apps website](https://api.slack.com/apps).
  2. Click **Create an App**. See image.
  3. Select **From scratch**. See image.
  4. In the **Name app & choose workspace** window: Click **Create App**. See image.
    - **App Name**: Enter a name for your application.
    - **Pick a workspace to develop your app in**: Assign a workspace for your application.
  5. After the redirection to the Slack settings page, go to **Features** > **Incoming Webhooks**.
  6. Enable **Activate Incoming Webhooks**.
  7. After the Slack settings page refreshes, click **Add New Webhook to Workspace**.
  8. Select an existing channel or create a new one to send the webhook messages to.
  9. Click **Copy** for the webhook URL.
2. [Create a webhook in the Zscaler Admin Console](https://help.zscaler.com/zdx/configuring-webhooks).
  1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
  2. Click **Add Webhook**.
  3. In the **Add a New Webhook** window:
    - **Name**: Enter a webhook name.
    - **Status**: Select **Enable**.
    - **URL**: Enter your copied URL from the previous step.
    - **Authentication Type**: Select **Token**.
    - **Bearer Token**: Enter any text as Slack does not require a bearer token.
  4. Click **Test Webhook** and verify the test result appears on the assigned Slack channel.
  5. Click **Save**and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

[Image: Workflow Builder in Slack]

[Image: Create a Workflow in Slack]

[Image: Choose Webhook in Slack]

[Image: Add Variables in Slack Workflow]

[Image: Add the Action in Slack]

[Image: Copy Webhook URL]

[Image: Click Create an App]

[Image: Select From Scratch]

[Image: Enter Application Name and Select Workspace]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/splunk-webhook-configuration-guide","lastmod":"2026-07-10T10:03Z","nid":"1390521"} -->
## Splunk Webhook Configuration Guide

- Source: https://help.zscaler.com/zdx/splunk-webhook-configuration-guide
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > Splunk Webhook Configuration Guide
- Last modified: 2026-07-10T10:03Z
- Summary: How to configure webhooks using Splunk.

This guide provides information on configuring webhooks using Splunk Enterprise for alerts in ZDX. The instructions provide a sample configuration that you can configure based on user requirements.

## Setting Up Webhooks in the Splunk Enterprise Instance

After your Splunk Enterprise instance has been installed and is running, follow this sequence to create webhooks:

1. Log in to your instance. The home page is displayed.
2. In the top-right corner of the page, click **Settings**. See image.
3. Go to **Data** > **Data inputs**. See image.

### Configure HTTP Event Collector

Configure HTTP Event Collector (HEC) to receive data:

1. Select **HTTP Event Collector**from the list of inputs. See image.
2. In the top-right corner of the page, click **Global Settings**. See image.
3. Configure the following Global Settings: Click **Save**. See image.
  - **All Tokens**: Select **Enabled**.
  - **Default Source Type**: Select **_json**.
  - **Default Index**: Select **Default**.
  - **Default Output Group**: Select **None**.
  - Deselect the **Use Deployment Server** checkbox.
  - Deselect the **Enable SSL** checkbox.
  - **HTTP Port Number**: Enter your HTTP Port Number.

To learn more about configuring HTTP Event Collector, refer to [Splunk Documentation](https://docs.splunk.com/Documentation/SplunkCloud/latest/Data/UsetheHTTPEventCollector).

### Create a Token

Create a token to receive data:

1. In the top-right corner of the page, click **New Token**.
2. Enter a name for the token. Leave the remaining settings as shown in the following dialog: See image.
3. Click **Next**.
4. Configure any additional input settings, then click **Review**. Your settings are displayed for review. See image.
5. Click **Submit**. A confirmation dialog displays the created token. See image.
6. Highlight and copy the Token Value.

## Configuring Splunk Webhooks in ZDX

[To configure a webhook in the Zscaler Admin Console](https://help.zscaler.com/zdx/configuring-webhooks):

1. Go to **Policies**> **Digital Experience Monitoring** > **Webhooks**.
2. Click **Add Webhook**.
3. In the **Add Webhook** window:
  - **Name**: Enter a webhook name for Splunk.
  - **Status**: Select **Enabled**to enable the webhook.
  - **URL**: Include the following parameter in your URL:
  - **Authentication Type**: Select **Basic**or **Token**.
    - For Basic, enter any string as the username, and enter the configured HEC Token Value from the previous step.
    - For Token, enter the configured HEC Token Value from the previous step as the Bearer Token.
4. Click **Save**and [activate the changes](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console).

[Image: Edit Global Settings Window]

[Image: Enter name of new token]

[Image: Settings link in upper-right corner of home page]

[Image: Link to select data inputs]

[Image: Select HTTP Event Collector]

[Image: Global Settings button]

[Image: Review Settings Window]

[Image: Window confirms token has been created]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/starting-new-diagnostics-session","lastmod":"2026-07-23T21:06Z","nid":"1370101"} -->
## Starting a New Diagnostics Session

- Source: https://help.zscaler.com/zdx/starting-new-diagnostics-session
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Diagnostics > Starting a New Diagnostics Session
- Last modified: 2026-07-23T21:06Z
- Summary: Information about configuration steps for starting a new diagnostics session in ZDX.

To start a new Diagnostics session:

1. Perform one of the following actions:
  - In the Zscaler Admin Console, go to **Analytics** > **Digital Experience** > **Troubleshooting** > **Diagnostics** > **Start New Diagnostics Session**.
  - Go to **Analytics** > **Digital Experience** > **Users**. Select a user. On the **User Details** page, click the **Start New Diagnostics Session**button. This opens a new Start New Diagnostics Session window with the user name and device details prefilled.
  - Go to **Analytics** > **Digital Experience** > **Users**. Select a user. On the **User Overview** page, click the **Start New Diagnostics Session** button in the user's row. This opens a new Start New Diagnostics Session window with the user name prefilled.
  - On the **Diagnostics** page, copy an existing session using the **Copy** icon. This opens a new Start New Diagnostics Session window with all details of an existing session copied. All fields are editable. If the device has a session in progress or is no longer associated with the user, it is not copied. The **Start New Diagnostics Session** window appears. See image.
2. In the **Start New Diagnostics Session** window:
  1. **Name**: Enter a name for the session.
  2. **User**: Choose the user for this session from the drop-down menu.
  3. **Device**: After selecting a user, choose the device for this session from the drop-down menu. If the device has an ongoing troubleshooting session or there is a version incompatibility, the device appears grayed out in the menu. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility). See image.
  4. **What would you like to run?** Choose among the selections: Depending on which type of session you select, the minimum versions of Zscaler Client Connector and ZDX Module are required. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility). If there are no compatible devices for selection, then you cannot select Packet Capture Probing as an option. You can select Packet Capture Probing as an add-on with Deep Tracing, Bandwidth Test, or Hi-Fi Cloud Path. You cannot select Deep Tracing, Bandwidth Test, or Hi-Fi Cloud Path as an add-on to each other. If you select Bandwidth Test, you cannot set a duration for the session. However, if you select both Packet Capture Probing and either Bandwidth Test, Deep Tracing, or Hi-Fi Cloud Path, you can set the duration to 5 minutes, 15 minutes, 30 minutes, or 60 minutes using the **Run Session For** option. Additionally, if you select only Packet Capture Probing, you can set the session duration to 5 minutes, 15 minutes, 30 minutes, 60 minutes, 24 hours, or 48 hours.
    - Deep Tracing
    - Hi-Fi Cloud Path
    - Bandwidth Test
    - Packet Capture Probing
3. Click **Next**to start configuring the session if you chose Deep Tracing, Packet Capture Probing, or Hi-Fi Cloud Path as an option. If you chose only Bandwidth Testing, proceed to the next step.
  - Deep Tracing
  - Hi-Fi Cloud Path
  - Bandwidth Test
  - Packet Capture Probing
4. Click **Save** to create and start the session.

After you click **Save**, you see the session in the In Progress table on the Diagnostics page. As the session progresses, its status is updated and it eventually moves to the History table. You can view session results by clicking the **View** icon. To learn more, see [Viewing Diagnostics Session Results](https://help.zscaler.com/zdx/viewing-diagnostics-session-results).

You can start a Deep Tracing session to analyze issues that a user, device, or application is facing when connecting to the network.

You can configure the following:

- **Device Probing**: Enable or disable this option. When this option is enabled, device data is available. When this option is disabled, user device statistics are not collected and device data is unavailable.
- **Application**: Choose the application for the session. You can also choose **Add Special Application**. In this option, you can add a URL or a tenant URL to start a Deep Tracing session for an application without an existing probe. The special application added here is not included as part of the sessions limit. To learn more, see [Ranges and Limitations](https://help.zscaler.com/unified/ranges-limitations).
- Depending on what application is chosen, you can see at least one of the following probes: To learn more, see [About Probes](https://help.zscaler.com/zdx/about-probes).
  - **Web Probe**: Choose the configured Web probe for this application from the drop-down menu. Choose at least one probe: **Web**or **Cloud Path**.
  - **Cloud Path Probe**: Choose the configured Cloud Path probe for this application from the drop-down menu. When you add a Cloud Path probe, you can optionally enter thresholds for **Packet Loss** (in %) and **Latency** (in ms). If you select a Cloud Path probe that has been configured to follow a Web probe, the Web probe is selected. Network applications require only a single Cloud Path probe and do not require a Web probe. To learn more, see [About Applications](https://help.zscaler.com/zdx/about-applications) and [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

See image.

You can start a bandwidth test to view network connectivity and latency, then proceed to the next step.

See image.

If you want to capture the Cloud Path network connectivity or latency details, you can start a Hi-Fi (High Fidelity) Cloud Path session where a Cloud Path run sends a high number of packets.

You can configure the following:

- **Packet Count**: Enter the number of probe packets to send per hop discovery that have the same TTL value between 20 and 300. The default is 300.
- **Protocol**: Select **ICMP**, **TCP**, or **UDP**. If you select **TCP** or **UDP**, enter your port number.
- **Interval (ms)**: Enter the time interval between probe packets with the same TTL. Probe packets of incremental TTL are paced evenly within this time interval. The number of iterations or cycles is defined by the configured Packet Count. The time interval must be within the range of 1000 to 3000.
- **Timeout (ms)**: Enter the time to wait for a response to a probe packet before considering loss. The timeout must be within the range of 1000 to 3000.
- **Destination**: Select **IP/FQDN** or **Zscaler Service Edge**.
  - If you select **IP/FQDN**, enter the IP Address or FQDN.
  - If you select **Zscaler Service Edge**, you can select **Force Reverse Cloud Path in Trusted Network**. This option allows you to force a reverse traceroute when a network device blocks the forward Cloud Path. Enable only if you cannot implement a different device configuration. Ideally, you should reconfigure the firewall or the device blocking the forward traceroute. If that isn't possible, this setting provides calculations for reverse latency from the Public Service Edge for Internet & SaaS to the egress in the Cloud Path.

To learn more, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe).

See image.

You can start Packet Capture Probing to analyze and identify network performance issues that manage network traffic.

You can configure the following:

- **Packet Capture Filter**: Enter destination IP addresses and/or ports to filter Packet Captures (e.g., `ip host 1.1.1 and port 80 or port 53`).
- **Network Interface**: Choose a Network Interface. The default is All Interfaces.
- **Frame Size Limit:** Choose the frame size limit of the Packet Capture from 100 to 65,536 bytes. The default value is 1514 bytes.
- **Disk Space Limit:**(For ZDX version 4.5 and later) Select the storage space for the PCAP file from 100 MB, 200 MB, 500 MB, 1 GB, 2 GB, or 4 GB.

See image.

[Image: Starting a New Diagnostics Session]

[Image: Configure Device Probe Settings]

[Image: Hi-Fi Cloud Path]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/step-step-configuration-guide-zdx","lastmod":"2026-03-31T12:07Z","nid":"1355756"} -->
## Step-by-Step Configuration Guide for ZDX

- Source: https://help.zscaler.com/zdx/step-step-configuration-guide-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Step-by-Step Configuration Guide for ZDX
- Last modified: 2026-03-31T12:07Z
- Summary: This is a chronological process that must be completed for the ZDX service to be used by an organization.

This guide provides the configuration steps needed to begin using Zscaler Digital Experience (ZDX) for your organization.

Before you begin configuring ZDX, Zscaler recommends reading the articles:

- [What Is Zscaler Digital Experience?](https://help.zscaler.com/zdx/about-zscaler-digital-experience)
- Accessing and Navigating the Zscaler Admin Console, including:
  - [Monitoring the Performance Dashboard](https://help.zscaler.com/zdx/monitoring-performance-dashboard)
  - [Monitoring the Users Overview](https://help.zscaler.com/zdx/monitoring-users-overview)
  - [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview)
- [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience)
- [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility)

## Configuring ZDX

To configure ZDX, complete the steps:

- Step 1: Configure Authentication and Provisioning Settings in Internet & SaaS
- Step 2: Configure Role-Based Administration
- Step 3: Allowlist Domains on Zscaler cloud
- Step 4: Configure Zscaler Client Connector for ZDX
- Step 5: Configure an Application
- Step 6: Configure a Probe
- Step 7: Configure Alerting
- Step 8: Configure Diagnostics

To configure ZDX, have users, authentication, and provisioning set up for your organization in Internet & SaaS. To configure these settings in ZIA, see [About Provisioning and Authenticating Users](https://help.zscaler.com/zia/about-provisioning-authenticating-users) and [Choosing Provisioning and Authentication Methods](https://help.zscaler.com/zia/choosing-provisioning-authentication-methods). If you cannot configure these settings in ZIA, contact Zscaler Support.

To create admin users and roles in the ZDX Admin Portal, complete the steps to set up role-based administration. To learn more, see [About ZDX Role-Based Administration](https://help.zscaler.com/zdx/about-zdx-role-based-administration) and [First Time Provisioning for ZDX Admins](https://help.zscaler.com/zdx/first-time-provisioning-zdx-admins).

Each cloud requires a list of domains to be placed on the allowlist for your organization to configure ZDX. To learn more, see [Allowlist Domains for ZDX](https://help.zscaler.com/zdx/allowlist-domains-zdx).

To configure ZDX, you must first deploy Zscaler Client Connector. Minimum versions of Zscaler Client Connector and ZDX Module are required. To configure Zscaler Client Connector for your organization, see [What is the Zscaler Client Connector?](https://help.zscaler.com/z-app/what-zscaler-app), [Step-by-Step Configuration Guide for Zscaler Client Connector](https://help.zscaler.com/z-app/step-step-configuration-guide-zscaler-app), and [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/feature-version-compatibilities).

After you configure Zscaler Client Connector for your organization, you can also enable ZDX for a select group of users. To learn more, see [Selective Entitlement: Enabling ZDX for a Group of Users](https://help.zscaler.com/z-app/selective-entitlement-enabling-zdx-group-users).

Configure the SaaS web-based applications on your network that you want to probe via ZDX. To add and configure applications, see the following articles:

1. [About Applications](https://help.zscaler.com/zdx/about-applications)
2. [Adding a Custom Application](https://help.zscaler.com/zdx/adding-custom-application)
3. [Configuring a Predefined Application](https://help.zscaler.com/zdx/configuring-predefined-application)
4. [Editing an Application](https://help.zscaler.com/zdx/editing-application)

Configure the probes for the SaaS Web-based applications. To configure probes, see the following articles:

1. [About Probes](https://help.zscaler.com/zdx/about-probes)
2. [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe)
3. [Editing a Probe](https://help.zscaler.com/zdx/editing-probe)

Configure rules to trigger alerts when a preset threshold is reached. To learn more, see the following articles:

1. [About Alerts](https://help.zscaler.com/zdx/about-alerts)
2. [About Rules](https://help.zscaler.com/zdx/about-rules)
3. [Configuring an Alert Rule](https://help.zscaler.com/zdx/configuring-alert-rule)
4. [Editing an Alert Rule](https://help.zscaler.com/zdx/editing-alert-rule)
5. [Triggering an Alert](https://help.zscaler.com/zdx/triggering-alert)
6. [Configuring Webhooks](https://help.zscaler.com/zdx/configuring-webhooks)
7. [Understanding the Alert Email](https://help.zscaler.com/zdx/understanding-alerts-email)

Start a Diagnostics session to analyze any issues that users might be facing. To learn more, see following the articles:

1. [About Diagnostics](https://help.zscaler.com/zdx/about-diagnostics)
2. [Starting a Diagnostics Session](https://help.zscaler.com/zdx/starting-new-diagnostics-session)
3. [Viewing Diagnostics Session Results](https://help.zscaler.com/zdx/viewing-diagnostics-session-results)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/supported-versions-feature-compatibility","lastmod":"2026-06-11T12:59Z","nid":"1433851"} -->
## Supported Versions & Feature Compatibility

- Source: https://help.zscaler.com/zdx/supported-versions-feature-compatibility
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Supported Versions & Feature Compatibility
- Last modified: 2026-06-11T12:59Z
- Summary: To provide information on version compatibility and operating system that a user needs in order to use specific features of ZDX with Zscaler Client Connector.

To begin configuring Zscaler Digital Experience (ZDX), you must first deploy the minimum required or later version of Zscaler Client Connector based on your OS.

To configure Zscaler Client Connector for your organization, see [What Is Zscaler Client Connector?](https://help.zscaler.com/zscaler-client-connector/what-is-zscaler-client-connector) and [Step-by-Step Configuration Guide for Zscaler Client Connector](https://help.zscaler.com/zscaler-client-connector/step-step-configuration-guide-zscaler-client-connector).

Zscaler supports the latest software version and the two previous versions for Zscaler Client Connector due to the commitment of continuous improvement for Zscaler products. To learn more, see [Supported Versions](https://help.zscaler.com/eos-eol/supported-versions), and [Viewing and Configuring ZDX Module Upgrades](https://help.zscaler.com/zscaler-client-connector/viewing-and-configuring-zdx-module-upgrades).

Additional prerequisites might also be required to access or use certain features. These can include:

- [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience): To ensure the feature is supported by your subscription level.
- [ZDX Role](https://help.zscaler.com/zdx/adding-zdx-roles): To ensure you have the proper permissions for feature access.

To determine if a feature requires additional prerequisites, see the feature's respective help articles.

## Minimum OS Version Compatibility

Each OS with a base ZDX Module version requires a compatible Zscaler Client Connector version.

| OS | Zscaler Client Connector Version | Base ZDX Module Version |
| --- | --- | --- |
| Windows | 2.2.1 | 1.0.0 |
| macOS | 2.2.1 | 1.0.0 |
| Android | 1.12 | 1.0.0 |
| Android on ChromeOS | 1.12 | 1.0.0 |
| iOS | 3.8 | 3.6 |

If you have the base ZDX Module version with the respective Zscaler Client Connector version per OS, then you have access to the following in the Zscaler Admin Console:

- Dashboard
- Analytics
- Applications
- Users
- Configuration
- Administration
- Alerts
- Search
- Activation
- Profile

Specific features (e.g., Software Inventory) require a later version compatibility or they are not available to the OS (e.g., Software and Device Inventory is unavailable for Android).

## Supported Versions & Feature Compatibility

Some ZDX features require a Zscaler Client Connector version for certain OS as indicated. The feature version compatibility is applicable to later versions (e.g., Software and Device Inventory requires a Zscaler Client Connector version 3.7.1.56 or later for Windows). If your organization does not meet the required feature version compatibility, upgrade your Zscaler Client Connector or ZDX Module as required.

### Adaptive Mode

The required version compatibility for the [Adaptive Mode](https://help.zscaler.com/zdx/about-adaptive-mode) feature is:

| OS | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- |
| Windows | 3.4 | 2.3 |
| macOS | 3.2 | 2.3.1 |

### Diagnostics

The [Diagnostics](https://help.zscaler.com/zdx/about-diagnostics) feature consists of multiple types of Diagnostics. Version compatibility is specific to the Diagnostics type and OS.

#### Windows

| Type | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- |
| Deep Tracing | 3.1.0.103 | 2.0.0.21 |
| Hi-Fi Cloud Path | 4.2 | 4.1 |
| Bandwidth Test | 3.9 | 3.8 |
| Bandwidth Test with Destination Network Address Translation (DNAT) Details | N/A | 4.4 |
| Packet Capture Probing | 3.9 | 3.5 |

#### macOS

| Type | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- |
| Deep Tracing | 3.0.0.144 | 2.0.0.15 |
| Hi-Fi Cloud Path | 4.5.1 | 4.4 |
| Bandwidth Test | 4.3.1 | 3.9 |
| Packet Capture Probing | 3.6 | 3.5 |

#### Android and Android on ChromeOS

| Type | Zscaler Client Connector Version | ZDX Module Version for Windows |
| --- | --- | --- |
| Deep Tracing | 3.7 | 3.2 |

### Device Events Reports

The required version compatibility for the [Device Events reports](https://help.zscaler.com/zdx/viewing-device-events-reports) are:

| Type | OS | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- | --- |
| System Crashes | Windows | 4.5 | 4.5 |
| Software Crashes | Windows | 4.5 | 4.2 |
| macOS | 4.5.1 | 4.4 |  |

### Software Inventory

The required version compatibility for [Software Inventory](https://help.zscaler.com/zdx/viewing-software-inventory) and [Configuring Software Inventory Data Collection](https://help.zscaler.com/zdx/configuring-inventory-settings#SoftwareInventoryDataCollection) is:

| OS | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- |
| Windows | 3.7.1.56 | 3.3.0.50 |
| macOS | 3.6 | 3.3.2 |

### Wi-Fi Data Collection

The required version compatibility for [Wi-Fi data collection](https://help.zscaler.com/zdx/configuring-inventory-settings#Wi-Fi) of the [Wi-Fi Dashboard](https://help.zscaler.com/zdx/monitoring-wi-fi-dashboard) is:

| OS | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- |
| Windows | 4.5.0.1 | 4.5.0.1 |

### Process Inventory

The required version compatibility for [Process Inventory](https://help.zscaler.com/zdx/viewing-process-inventory) and [Configuring Process Inventory CPU Incidents](https://help.zscaler.com/zdx/configuring-inventory-settings#ProcessInventoryCPUIncidents) is:

| OS | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- |
| Windows | 3.9.0.189 | 3.8.0.37 |
| macOS | 3.6 | 3.5 |

### Software Patch Inventory

The required version compatibility for [Software Patch Inventory](https://help.zscaler.com/zdx/viewing-software-patch-inventory) is:

| **OS** | **Zscaler Client Connector Version** | **ZDX Module Version** |
| --- | --- | --- |
| Windows | 3.9 | 4.0 |

### Real User Monitoring

The required version compatibility for [Real User Monitoring](https://help.zscaler.com/zdx/understanding-real-user-monitoring) is:

| **OS** | **Zscaler Client Connector Version** | **ZDX Module Version** |
| --- | --- | --- |
| Windows | 4.8 | 4.7 |
| macOS | 4.8 | 4.6 |

### Remediation

The required version compatibility for [Remediation](https://help.zscaler.com/zdx/understanding-remediation) is:

| **OS** | **Zscaler Client Connector Version** | **ZDX Module Version** |
| --- | --- | --- |
| Windows | 4.8.0.151 | 4.7.0.48 |

### Self Service

The required version compatibility for [Self Service](https://help.zscaler.com/zdx/monitoring-self-service-dashboard) and [Configuring Self Service Settings](https://help.zscaler.com/zdx/configuring-self-service-settings) is:

| **OS** | **Zscaler Client Connector Version** | ZDX Module Version |
| --- | --- | --- |
| Windows | 4.4 | 4.0.1 |
| macOS | 4.3.1 | 3.9 |

### ZDX Autosense for Call Quality

ZDX Autosense feature is available for [Microsoft Teams](https://help.zscaler.com/zdx/configuring-microsoft-teams-call-quality-zdx), [Webex](https://help.zscaler.com/zdx/configuring-webex-call-quality-zdx), and [Zoom](https://help.zscaler.com/zdx/configuring-zoom-call-quality-zdx). Version compatibility is specific to the Call Quality and OS.

#### Windows

| Call Quality | Zscaler Client Connector Version | ZDX Module Version |
| --- | --- | --- |
| Microsoft Teams | 4.3 | 3.8 |
| Webex | 4.3.0.121 | 3.8.0.80 |
| Zoom | 4.3 | 3.8 |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/troubleshooting-remediation","lastmod":"2026-06-29T07:06Z","nid":"1538986"} -->
## Troubleshooting Remediation

- Source: https://help.zscaler.com/zdx/troubleshooting-remediation
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Remediation > Troubleshooting Remediation
- Last modified: 2026-06-29T07:06Z
- Summary: Detailed information on how to troubleshoot issues with Remediation.

This article provides troubleshooting information and guidelines for [Remediation](https://help.zscaler.com/zdx/understanding-remediation).

When troubleshooting Remediation, consider the following:

- Unable to Select Devices
- Unable to Run Remote Scripts on Specific Devices
- Invalid Certificate

If the devices you want to select are grayed out during device selection, it might be due to:

- Incompatible [version compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility)
- Inactive devices

If the Remediation job did not complete because the remote script did not run on the selected device, these can be the following reasons:

- Selected device is unavailable and cannot run the remote script from the Remediation job. This can be due to:
  - The device is not powered on.
  - The device is not connected to Wi-Fi.
- Selected device is running Zscaler Client Connector version that is older than 4.8.0.151 or ZDX Module is older than 4.6.0. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
- An admin aborted the Remediation job mid-way and the remote script only ran on selected devices before the abortion.
- Internet & SaaS policy blocks remote scripts from running on device. If this occurs, check to see if: If any of these are the issue, configure them accordingly to allow `ps1` files to run on devices and allow your specified remediation URL into a URL category.
  - `ps1` files are blocked. To learn more, see [About File Type Control](https://help.zscaler.com/zia/about-file-type-control).
  - The URL policy is blocking unknown URLs. To learn more, see [About URL Categories](https://help.zscaler.com/zia/about-url-categories).

You cannot run a remote script that is signed with an invalid certificate due to certificate expiration or invalid credentials.

You must sign the script again with an appropriate certificate expiration and valid credentials.

To learn more, see [Preparing Custom Script Signing](https://help.zscaler.com/zdx/preparing-certification-and-signing-script).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-admin-scope","lastmod":"2026-03-31T15:26Z","nid":"1449966"} -->
## Understanding the Admin Scope

- Source: https://help.zscaler.com/zdx/understanding-admin-scope
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Understanding the Admin Scope
- Last modified: 2026-03-31T15:26Z
- Summary: Provides in-depth information on the admin scope, such as the benefits of using a role and scope for ZDX admins.

With [role-based administration](https://help.zscaler.com/zdx/about-administrators), a ZDX admin's scope specifies which areas of the organization an admin can manage and view for ZDX. The default ZDX admin is assigned the Organization scope over the entire organization. For each additional admin you create, you must assign one of the following scopes:

- Organization
- Department
- Applications
- Location

You can assign a scope over the entire organization or either location or department. When selecting **Location** as a scope, you can assign a Zscaler **Location**to limit their area of impact. When selecting **Department**, you can assign a department to limit their area of impact. To learn more, see [Adding ZDX Admins](https://help.zscaler.com/zdx/adding-zdx-admins).

See image.

## The Effect of a Scope on an Admin

An admin’s scope affects the following areas:

- Alert Rule Criteria
- Configuring Alert Rules or Accessing Information
- Assigning Scope for Admins
- Access to Organizational Resources
- Access to Digital Experience Features

## The Benefits of Admin Scope and Role

The process of creating an admin by assigning a [role](https://help.zscaler.com/zdx/adding-zdx-admins), ensures that alert rules and settings configured by that admin aren't impacted when the admin account is modified or deleted in the future. This is because an alert rule or setting is associated with an admin’s role and admin scope rather than a particular admin. If an admin account is deleted, you won't lose all the distinct permissions and functional scopes associated with that admin. You can simply reassign the admin scope and role, which includes the configured permissions and functional scope, to another admin.

For example, your organization has an admin account with access to all ZDX administration access and is assigned the Organization scope. If that admin leaves the organization and their account is deleted, the alert rules they created are not affected and remain. Furthermore, you can easily assign the next admin the same role and admin scope as the previous admin, without the need to redefine permissions and functional scopes from scratch.

Admins can define alert rules and settings for their assigned locations or departments.

- Admin with Scope over Location
- Admin with Scope over Department

For example, consider Admin A who is assigned a scope over two locations: Germany and France. When Admin A creates an alert rule:

- Admin A is required to make a selection for the Locations criteria. Because of her scope, only Germany and France are available for selection.
- Admin A can choose any or all users, departments, and groups. However, the alert rule only applies to users, department, or group members who are in Germany or France.

In the following scenario depicted, Admin A creates an alert rule and specifies Germany as a location. Admin A then chooses any user, group, or department. The alert rule applies only to users inside the orange box.

[Image: Graphic depicting admin with scope over Germany and France and choosing only to apply rules to Germany]

For example, consider Admin B, assigned scope over two departments, HR and IT. When Admin B creates an alert rule:

- Admin B is required to make a selection for the Departments criteria. Only the HR and IT departments are available for selection.
- Admin B is required to make a selection for the Users criteria. Only users from the HR and IT departments are available for selection. If Admin B selects a department under the Departments criteria, the alert rule applies to all users in that department, no matter which users Admin B selects under the Users criteria. Thus, specifying users from the Users criteria is useful only if Admin B is selecting users from a department different than the one Admin B selects in the Departments criteria (for example, if Admin B selects the IT department in the Departments criteria and then selects users from HR under the Users criteria).
- Admin B is required to select a User Group. The alert rule applies to all members of the specified group, regardless of their associated department. To limit the alert rule to members of the department specified in the Departments criteria, Zscaler recommends that admins choose a user group that includes only those department members. For example, if Admin B wants to ensure an alert rule applies to only members of Finance, the admin must select a User Group with only the Finance department members. Zscaler recommends that you avoid selecting All User Group for a User Group.
- Admin B can select any or all locations. The alert rule applies only to specified users and department or group members in the selected locations.

In the following scenario depicted, Admin B creates an alert rule and specifies the following for each criteria:

- **Users**: John Doe from IT
- **Departments**: HR
- **Groups**: HR-Group
- **Location**: Germany, France, and Belgium

This alert rule applies only to users inside the orange box.

[Image: Graphic illustrating admin with Scope over Department]

Admins can configure an alert rule or access information (e.g., software inventory) depending on their scope.

For example, consider an alert rule that has a location criterion for Germany and France. Only an admin with scope over both Germany and France can configure this alert rule. An admin who has scope for Germany or only for France is unable to be able to configure this alert rule.

As another example, consider an admin with the scope assigned to HR and has access to the ZDX Dashboard. The admin can view the ZDX Dashboard and the impacted applications if the impacted application is assigned to HR employees. The admin only sees applications that are assigned to HR.

If admins have permission to manage admins, their scope limits the scope that they can assign other admins. For example, if Admin A, who has scope over Germany, creates an admin, and she wants to assign a scope by location, only Germany is available as an option. If she wants to assign the admin a scope in the department category, she can choose any (or all) departments.

Only admins who have scope over the entire organization can configure organization-wide alert rules, inventory settings, and information. For example, admins with Organization scope can access Inventory Settings, SaaS Integrations, and Authentication Settings.

The following table outlines how admin scope impacts the ability to access ZDX features.

| Dashboard |  |
| --- | --- |
| Features | Admin Scope Impact |
| ZDX Dashboard; Applications; User; Inventory | Admins can view traffic information for areas over which they have scope. For ZDX Dashboard, Applications, User, and Inventory, an admin can only view impacted users and their devices based on their area of scope. |

| Configurations |  |
| --- | --- |
| Features | Admin Scope Impact |
| Alerts; Diagnostics; Probes | For alerts, while configuring an alert rule and specifying users, departments, or locations, the admin can only configure alert rules for users in their assigned departments or for their assigned locations based on their area of scope. For example, if an admin is configuring an alert rule, the admin can only configure the filters for users in their assigned departments or for their assigned locations. For example, if Admin A who has scope over Germany creates an alert, and they want to have the filter applied by location, only Germany is available as an option. For Admin B who has scope over the HR and IT departments, only HR and IT are available as options for the department, and if specifying the users, only users from those departments are available as options.; For Diagnostics, admins can configure sessions for users based on their assigned scope.; For probes, a user can configure probes for applications within their area of scope. |

| Analytics |  |
| --- | --- |
| Features | Admin Scope Impact |
| **Reporting** System-Generated Reports; Quarterly Business Reports; ZDX Snapshots; Self Service | Admins can access reports based on the areas included in their assigned scope and must have the appropriate permission settings. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles). |

| Administration |  |
| --- | --- |
| Features | Admin Scope Impact |
| **Administration**> **Administration Controls** Administrator Management; User Management; Location Management; Audit Logs | For Administration Controls, admins can only manage ZDX Admins based on their scope.; For Authentication settings, admins require Organization scope to make changes.; For User Management, if admins are assigned scope over specific departments, they can only view users from those departments. If admins are assigned Organization scope or scope over locations, admins can view all users.; For Location Management, if admins are assigned scope over specific locations, they can only view locations from their scope location. If admins are assigned Organization scope or scope over departments, admins can view all locations.; Scope does not impact My Profile. |
| **Administration** > **Settings** Inventory; Self Service | For Settings, admins require Organization scope to configure Inventory or Self Service settings. |
| **Administration** > **Integration** Data Collection; SaaS App Tenants; Webhooks | For Integration Settings, admins require Organization scope to make changes. |

[Image: Selecting an Admin Scope]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-alert-status","lastmod":"2022-09-13T07:00Z","nid":"1414456"} -->
## Understanding the Alert Status

- Source: https://help.zscaler.com/zdx/understanding-alert-status
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Understanding the Alert Status
- Last modified: 2022-09-13T07:00Z
- Summary: Provides a summary of the available Alert Status

The Alert Status provides details on the current status of an alert. The alert status can be seen in the Alerts Overview, Ongoing Alerts, Alert History, and View Alert pages. To learn more, see [About Alerts](https://help.zscaler.com/zdx/about-alerts).

An alert status can be:

- **Started**: The alert has started.
- **Ongoing**: The alert is ongoing.
- **Completed**: The alert is completed.
- **Completed by Exceeded Time**: The alert ended due to an expired time duration.
- **Completed by Rule Deletion**: The alert ended due to a rule deletion.
- **Completed by Modified Rule**: The alert ended due to a modification of the rule.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-alert-triggers","lastmod":"2026-01-23T06:06Z","nid":"1389151"} -->
## Understanding Alert Triggers

- Source: https://help.zscaler.com/zdx/understanding-alert-triggers
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Understanding Alert Triggers
- Last modified: 2026-01-23T06:06Z
- Summary: Detailed information about how alerts are triggered in the ZDX Admin Portal.

[Watch a video about configuring alerts in ZDX.](https://fast.wistia.net/embed/iframe/5ozc9luba9)

Each alert rule can be triggered differently based on the type of alert rule. Each alert rule type (Application, Device, Incident, Network, or Call Quality) has different criteria for an alert rule to trigger based on their metrics. If the criteria are met, then the alert triggers and provides notification through an [alert email](https://help.zscaler.com/zdx/understanding-alerts-email).

The alerts triggered have a display delay of 30 minutes.

Based on the alert rule type, you get the following throttling criteria:

- Application, Device, or Network
- Incident
- Call Quality

## Alert Criteria Examples

The following are examples of alert criteria based on the rule type:

- Application, Device, or Network
- Incident
- Call Quality

You can configure the following criteria for an application, device, or network rule type:

- **Alert Only if Repeated**: The number of times a situation should repeat for an alert to be triggered.
- **Number of Active Devices is**: The number of active devices impacted based on the In Group selection.
- **Minimum Devices Impacted**: The number or percentage of devices impacted based on the In Group selection.
- **In Group**: The departments, cities, organization, regions, or Zscaler locations.

For any [incident type](https://help.zscaler.com/zdx/monitoring-incidents-dashboard#understand), you can configure throttling criteria based on Impacted Devices. The minimum number of impacted devices depends on the incident type.

If you select multiple incident types, the criteria are predefined to meet the minimum devices and, if applicable, the global minimum threshold.

For Call Quality, you can configure the following throttling criteria:

- **MOS**: The Mean Opinion Score (MOS) is integrated into the ZDX Score to rate a call's quality.
- **ZDX Score**: The following Call Quality metrics are used to determine the score: The ZDX Score for Call Quality is calculated by one of the following methods, utilizing values for latency, jitter, and packet loss: To learn more, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).
  - **Latency**: The time taken to send a data packet from point A to point B, such as the hop between legs within the Cloud Path.
  - **Jitter**: The variance in time delay between data packets over a network.
  - **Packet Loss**: When data packets that travel across a computer network fail to reach their destination.
  - Using the MOS (Rated from 1 to 5, worst to best)
  - Using metric thresholds

You can have the following alert criteria for application, device, or network type:

- **Alert Only if Repeated** 3 **Times in a Row**
- **Number of Active Devices**: 5
- **Minimum Devices Impacted**: 20%
- **Page Fetch Time (PFT)**: > 1000ms
- **In Group**: Cities (city = Cairo)

### Scenario with Alert Criteria Not Met

If there is only one device present in Cairo and the following conditions are not met:

- The PFT of the device exceeds `1000`ms.
- This situation repeats `3` times in a row.
- The minimum devices impacted is `1`.

The alert won't trigger because there is only one active device in Cairo and therefore does not meet the **Number of Active Devices: 5** condition.

### Scenario with Alert Criteria Met

If there are 5 active devices (Device 1 to 5) and the following conditions are met:

- The PFT of the device exceeds `1000`ms.
- This situation repeats `3` times in a row.
- The minimum devices impacted is `1`.

Then an alert is triggered at T3 as shown in the following table.

| **Device #** | **Times in a Row (T1)** | **Times in a Row (T2)** | **Times in a Row (T3)** | **Alert Result** |
| --- | --- | --- | --- | --- |
| **Device 1** | Device impacted | Device impacted | Device impacted | Alert triggered |
| **Device 2** | Device impacted | Device impacted | Device impacted | Alert triggered |
| **Device 3** | Device impacted | Device impacted | Device impacted | Alert triggered |
| **Device 4** | Device impacted | Device impacted | Device impacted | Alert triggered |
| **Device 5** | Device impacted | Device impacted | Device impacted | Alert triggered |

### Alerts by Cities Filter

If you are setting up alerts based on the cities the devices are in, select **Cities** from the **In Group** drop-down menu. An alert triggers when the criteria you have set up for the alert are met. In this example, an alert triggers when all of the following occur:

- Page Fetch Time (PFT) of an application exceeds `1000`ms (added in the Configure Rule tab).
- There are `10` devices in `<city_name>` city.
- There are at a minimum `5` devices impacted.
- The above situation repeats `5` times in a row.

### Alerts by Organization Filter

If you are setting up alerts based on the organization the devices are in, select **Organization** from the **In Group** drop-down menu. An alert triggers when the criteria you have set up for the alert are met. In this example, an alert triggers when all of the following occur:

- Page Fetch Time (PFT) of an application exceeds `1000`ms (added in the Configure Rule tab).
- There are `10` devices across the organization.
- There are at a minimum `5` devices impacted.
- The above situation repeats `5` times in a row.

### Alerts by Cities and Geolocations Filter

If you are setting up alerts based on city grouping using the geolocation filter, click **Add Filter** in the **Filters** tab, and select **Geolocations**. Choose the desired cities from the drop-down menu. An alert triggers when the criteria you have set up for the alert are met. In this example, an alert triggers when all of the following occur:

- Cities are defined in the **Geolocations** filter (e.g., city = Atlanta).
- Page Fetch Time (PFT) of an application exceeds `1000`ms (added in the Configure Rule tab).
- There are `10` devices in group=city.
- There are at a minimum `5` devices impacted.
- The above situation repeats `5` times in a row.

An incident requires a minimum number of devices impacted depending on if it is impacted globally (across an organization) or locally (per location).

An incident that requires both a global and local threshold (Intermediate ISP, Last Mile ISP - Brownout, ZIA Public Service Edge, ZPA Public Service Edge), must meet both requirements for the alert to trigger. For example, if a Last Mile ISP - Brownout incident involves the following:

- Globally, there are 14 impacted devices.
- Locally, there are 4 or 5 impacted devices per location.

Since there are 4 impacted devices for a location, the alert does not trigger because it requires a minimum of 5 impacted devices per location. This is because the minimum global threshold (10) and local threshold (5) are not met.

For ZPA - App Connector incidents, you must have at least 10 impacted devices across all ZPA App Connector locations to trigger an alert.

In the following table are the alert conditions and example totals to trigger an alert:

- **Incident Type**: The type of incident.
- **Global Minimum**: The minimum number of global impacted devices required.
- **Local Minimum**: The minimum number of local impacted devices required.
- **Global Total**: The actual total number of global impacted devices as an example.
- **Local Total**: The actual total number of local impacted devices as an example.
- **Alert Result**: The alert result is based on the conditions given (Not Triggered, Triggered).

| Incident Type | Global Minimum | Local Minimum | Global Total | Local Total | Alert Result |
| --- | --- | --- | --- | --- | --- |
| Application | 50 | N/A | 40 | N/A | Not triggered |
| Application | 50 | N/A | 50 | N/A | Triggered |
| Device - System Software Crash | 50 | N/A | 49 | N/A | Not triggered |
| Device - System Software Crash | 50 | N/A | 55 | N/A | Triggered |
| Device - System Software Hang | 50 | N/A | 45 | N/A | Not Triggered |
| Device - System Software Hang | 50 | 50 | N/A | N/A | Triggered |
| Intermediate ISP | 10 | 10 | 9 | 10 | Not Triggered |
| Intermediate ISP | 10 | 10 | 11 | 12 | Triggered |
| Last Mile ISP - Blackout | 20 | N/A | 19 | N/A | Not Triggered |
| Last Mile ISP - Blackout | 20 | N/A | 22 | N/A | Triggered |
| Wi-Fi | 4 | N/A | 3 | N/A | Not Triggered |
| Wi-Fi | 4 | N/A | 5 | N/A | Triggered |
| ZIA Public Service Edge | 15 | 120 | 10 | 120 | Not triggered |
| ZIA Public Service Edge | 15 | 120 | 16 | 90 | Not triggered |
| ZIA Public Service Edge | 15 | 120 | 16 | 120 | Triggered |
| ZPA - App Connector | N/A | 10 per App Connector location | N/A | 9 | Not Triggered |
| ZPA - App Connector | N/A | 10 per App Connector location | N/A | 11 | Triggered |
| ZPA - Public Service Edge | 100 | 10 | 90 | 11 | Not Triggered |
| ZPA - Public Service Edge | 100 | 10 | 110 | 9 | Not Triggered |
| ZPA - Public Service Edge | 100 | 10 | 110 | 11 | Triggered |
| Last Mile ISP - Brownout | 10 | 5 | 9 | 5 | Not Triggered |
| Last Mile ISP - Brownout | 10 | 5 | 12 | 4 | Not Triggered |
| Last Mile ISP - Brownout | 10 | 5 | 12 | 5 | Triggered |
| Wi-Fi | 4 | N/A | 3 | N/A | Not triggered |
| Wi-Fi | 4 | N/A | 5 | N/A | Triggered |
| ZIA Public Service Edge | 15 | 120 | 10 | 120 | Not triggered |
| ZIA Public Service Edge | 15 | 120 | 16 | 90 | Not triggered |
| ZIA Public Service Edge | 15 | 120 | 16 | 120 | Triggered |
| ZPA - App Connector | N/A | 10 per App Connector location | N/A | 9 | Not Triggered |
| ZPA - App Connector | N/A | 10 per App Connector location | N/A | 11 | Triggered |
| ZPA - Public Service Edge | 100 | 10 | 90 | 11 | Not Triggered |
| ZPA - Public Service Edge | 100 | 10 | 110 | 9 | Not Triggered |
| ZPA - Public Service Edge | 100 | 10 | 110 | 11 | Triggered |

An alert for Call Quality is triggered when the Mean Opinion Score (MOS) or ZDX Score criteria and throttling conditions are met. For example, if you configured an alert with the following:

- Criteria
  - MOS < 2
  - ZDX Score < 60
- Throttling Conditions
  - Number of Meetings is: 5
  - Minimum Number of Active Participants is: 5
  - Number of Impacted Active Participants is: 10

The alert for Call Quality triggers only if all the criteria and conditions are met.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-alerts-email","lastmod":"2025-07-28T07:06Z","nid":"1364566"} -->
## Understanding the Alert Email

- Source: https://help.zscaler.com/zdx/understanding-alerts-email
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Understanding the Alert Email
- Last modified: 2025-07-28T07:06Z
- Summary: Information about the alert email sent in ZDX.

Alert notifications are sent via email if this option is chosen when configuring the alert rule. To learn more, see [Configuring a Rule](https://help.zscaler.com/zdx/configuring-rule).

The alert email shows the following:

- **Alert Criteria Triggers**: These are the criteria selected when the alert rule was configured. If one or more alert criteria are met, then it is highlighted.
- **Alert Start Time**: This indicates the alert start time and if the alert has ended, then the alert end time is indicated. If the alert end time has not ended, then it indicates Ongoing.
- **Alert Rule**: The name of the alert rule. Click the name of the alert rule to view further details in the ZDX Admin Portal.
- **Alert Severity**: The level of severity for this alert rule.
- **Impacted**: The impacted Geolocations, Departments, OS Versions, and Devices. If the alert has ended, then devices are no longer listed.

See image.

Click **View Alert** to view further details in the ZDX Admin Portal.

## Incident Alert Email

If you configure alerts for incidents, the alert email shows the following:

- **Alert Criteria Triggers**: These are the criteria selected when the alert rule was configured. If one or more alert criteria are met, then it is highlighted.
- **Incident Type**: The type of incident (e.g., Application, Last Mile ISP).
- **Epicenter**: The impacted epicenter displays where the center of the incident is. For example, if the incident type is Application, then the epicenter shows the area of impacted users. To learn more, see [Monitoring the Incidents Dashboard](https://help.zscaler.com/zdx/monitoring-incidents-dashboard).
- **Alert Timeline**: This indicates the alert start time and if the alert has ended, then the incident end time is indicated. If the incident has not ended, then it indicates **Ongoing** in the **Incident Status** and no end time is shown.
- **Alert Rule**: The name of the alert rule. Click the name of the alert rule to view further details in the ZDX Admin Portal.
- **Impacted**: The impacted Geolocations, Devices, and Users. If the alert has ended, then devices are no longer listed.

See image.

## Call Quality Alert Email

If you configure alerts for call quality, the alert email shows the following:

- **Alert Criteria Triggers**: These are the criteria selected when the alert rule was configured. If one or more alert criteria are met, then it is highlighted.
- **Alert Timeline**: This indicates the alert start time and if the alert has ended, then the incident end time is indicated. If the incident has not ended, then it indicates **Ongoing** in the **Incident Status** and no end time is shown.
- **Alert Rule**: The name of the alert rule. Click the name of the alert rule to view further details in the ZDX Admin Portal.
- **Alert Severity**: The level of severity of this alert rule.
- **Impacted**: The impacted Geolocations, Departments, Meetings, and Users.

See image.

[Image: Sample Alert Email]

[Image: Incident alert email sent after alert conditions are met]

[Image: Call quality alert email is sent after alert conditions are met]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-collections","lastmod":"2026-04-12T07:06Z","nid":"1535199"} -->
## Understanding Collections

- Source: https://help.zscaler.com/zdx/understanding-collections
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Understanding Collections
- Last modified: 2026-04-12T07:06Z
- Summary: Information on collections in the ZDX Admin Portal.

A collection is useful in organizing and categorizing your applications with their probes. Each collection consists of the following:

- Applications
- Probes

When you configure an [application](https://help.zscaler.com/zdx/about-applications), you assign it to a collection and then configure the corresponding [probes](https://help.zscaler.com/zdx/about-probes) to it. Optionally, you can [create a collection](https://help.zscaler.com/zdx/managing-collections#add) as needed and then add the application and probe at a later time.

Each collection can configure [Inclusion and Exclusion criteria](https://help.zscaler.com/zdx/understanding-probing-criteria-logic) based on the probing criteria.

A probe can be a:

- Web probe
- Cloud Path probe

To learn more, see [About Probes](https://help.zscaler.com/zdx/about-probes).

If you have a [Hosted Monitoring subscription](https://help.zscaler.com/zdx/ranges-limitations), you can [configure Zscaler Hosted probes](https://help.zscaler.com/zdx/configuring-zscaler-hosted-probes). Zscaler Hosted probes monitor the performance of a specific service or application directly from a Zscaler data center to an endpoint destination and allows you to logically group Web and Cloud Path probes into independent collections for easy organization.

If you configure an application for the end user, then you can assign the application to different collections. If you configure an application for a Hosted user, then you can only assign the application to one collection.

## Viewing Collections

You can view the following collections on the Configuration page:

- End User: A configured application in an End User collection can be in multiple collections.
  - Unified Communication Collection
  - Predefined Apps Collection
  - Custom Apps Collection
  - Any other customized collection you created
- Hosted: A configured application in the Hosted collection can only be in one collection.
  - Default Hosted Collection
  - Any other customized collection you created

Each collection allows you to configure an [application](https://help.zscaler.com/zdx/about-applications), and then you can [configure for probes](https://help.zscaler.com/zdx/configuring-probe).

## Ranking

When you configure an application for the end user and the application is assigned to different collections, then the probes are ranked based on their collections as follows with 1 being the highest priority:

1. Unified Communication Collection
2. Predefined Apps Collection
3. Custom Apps Collection

If the application is in multiple collections, then the ranking is:

1. Ranking of Collections
  1. Unified Communication (Highest ranking)
  2. Predefined Apps
  3. Custom Apps (Lowest ranking)
2. Alphabetical order if the application is in multiple Custom Apps collections.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-diagnostics-session-status","lastmod":"2024-04-18T14:52Z","nid":"1443586"} -->
## Understanding the Diagnostics Session Status

- Source: https://help.zscaler.com/zdx/understanding-diagnostics-session-status
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Diagnostics > Understanding the Diagnostics Session Status
- Last modified: 2024-04-18T14:52Z
- Summary: To describe each existing Diagnostics status in the table.

The Diagnostics Session Status indicates the current status of a Diagnostics Session. The Diagnostics Session status can be seen in both the In Progress and History tables as an overview or in the Individual Diagnostics Session Information window as part of the in-depth information of the Diagnostics Session.

Diagnostics Sessions in the **In Progress** table can have one of the following statuses:

- **Created**: The session was created by an admin in ZDX.
- **Started**: Zscaler Client Connector confirms that it has received a request to start a session and probe execution will begin. It might take a few minutes for the state to change from Created to Started.
- **In Progress**: Zscaler Client Connector is executing this session; this status indicates an ongoing session. These sessions provide updated session data every minute.

Diagnostics Sessions in the **History** table can have one of the following statuses:

- **Abort Initiated**: An admin canceled the session prematurely. The request is forwarded to the Zscaler Client Connector.
- **Aborted**: After receiving the Abort Initiated request, Zscaler Client Connector acknowledges the request and the state is changed to Aborted. It might take a few minutes for the state to be updated.
- **Expired**: The request to start the session timed out due to lack of response from the Zscaler Client Connector. This could be because the device is offline or facing network issues.
- **Failed**: After restarting, Zscaler Client Connector was not able to run the previously scheduled probes due to some internal errors.
- **Incomplete**: The session was not completed due to issues in the pipeline. Partial data is displayed in this case.
- **Completed**: The session has completed successfully.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-managed-monitoring","lastmod":"2026-08-06T14:23Z","nid":"1505966"} -->
## Understanding Managed Monitoring

- Source: https://help.zscaler.com/zdx/understanding-managed-monitoring
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Understanding Managed Monitoring
- Last modified: 2026-08-06T14:23Z
- Summary: Understanding Zscaler Managed collections and probes.

[Watch a video about Managed Monitoring.](https://fast.wistia.net/embed/iframe/vcmq8namoq)

Managed Monitoring operates from within the Zscaler cloud infrastructure as a multi-tenant service. The service allows you to logically group Web and Cloud Path probes into independent collections to set up your own tests for monitoring performance. As an extension to ZDX probes for predefined and custom applications, Zscaler Managed Probes monitor the performance of a specific service or application directly from a Zscaler data center to an endpoint destination.

To access Managed Monitoring, make sure you've configured Zscaler Managed Probes and met the feature prerequisites. To learn more, see [Configuring Zscaler Managed Probes](https://help.zscaler.com/zdx/configuring-zscaler-managed-probes).

To access Managed Monitoring:

1. Go to **Analytics**> **Digital Experience** > **Applications** > **Managed Monitoring**.
2. Select a probe from a collection in the left-side navigation.
3. Select the **Zscaler Managed Locations** from the drop-down menu to specify the Zscaler data centers from where the probe is run. All locations are selected by default. You can select the following:
  - List of Zscaler Managed Locations
4. Click **Apply**. The applicable probe charts are displayed for either a Web probe or Cloud Path probe. See image.

- Zurich, CH
- Frankfurt, DE
- Amsterdam, NE
- Chicago, IL, USA
- San Jose, CA, USA
- Washington, DC, USA
- Sydney, AU
- Delhi, IN
- Mumbai, IN
- Chennai, IN
- Tokyo, JP
- Osaka, JP
- Paris, FR
- Singapore, SG
- London, UK
- Atlanta, GA, USA
- Dallas, TX, USA
- Los Angeles, CA, USA
- New York, NY, USA

## Setting a Time Range

Depending on the probe you've selected, charts are displayed by default for Availability and Page Fetch Time for Web probes, and End-to-End Latency for Cloud Path probes. The default time range for the initial chart on the page is the previous 30 days. Each subsequent chart reflects the time range and metrics reflected by the time range widget.

### Using the Time Range Widget

You can customize the time range to capture a subset of metrics. As you move, expand, or shorten the widget's time range, the identical time range is reflected within the chart for Page Fetch Time (Web probe) or End-to-End Latency (Cloud Path probe):

- Select one side of the widget to expand or shorten a specific time range.
- Select the middle of the widget to move it to a specific time range.

The minimum time range for the widget is 2 hours, and the maximum time range is 48 hours. The default time range of the widget is 24 hours.

See image.

## Monitoring Web Probe Metrics

For Web probes, **Availability**and **Page Fetch Time** metrics are automatically displayed.

### Metrics for Availability

The **Availability**chart provides the following metrics:

- **Month To Date (MTD)**: The average availability from the first day of the month to the current date, along with a percentage increase or decrease from the previous month.
- **Past 30 Days**: The average availability within the past 30 days, along with a percentage increase or decrease from the prior 30 days.
- **Time range from widget**: The average availability within your designated time range, along with a percentage increase or decrease from the prior 30 days.

See image.

### Metrics for Page Fetch Time

The **Page Fetch Time**chart provides the following metrics and actions:

- Click any data point to view metrics for the managed locations.
- Select the drop-down menu to incrementally add Web probe metrics and charts to Page Fetch Time. To learn more about Web probe metrics, see [About Probes](https://help.zscaler.com/zdx/about-probes) and [Evaluating User Details](https://help.zscaler.com/zdx/evaluating-user-details).
- Click the chart format icon to switch views between a line chart and a scatter chart. Each data point within a scatter chart represents an individual probe run.

See image.

### Key Metrics

To view granular metrics for the Web probe:

1. Click the chart format icon to ensure the scatter chart is displayed for Page Fetch Time.
2. Hover over a data point to view metrics for the managed location.
3. Click **View Details**.

See image.

The following key metrics are provided in milliseconds on the Run Details page:

- **Redirect Time**: The time measurement of traffic redirects.
- **DNS**: The resolution time for the DNS name.
- **TCP**: The time measurement of the Transmission Control Protocol.
- **SSL Handshake**: The communication time to the device.
- **Server Response Time**: The Time to First Byte (TTFB).
- **Page Fetch Time**: The time it takes the application to load a page for the user.

See image.

Request sizes are delineated for Download, Header, and the HTTP Request in milliseconds. A percentage increase or decrease accompanies each metric, based on an hourly average.

Server redirect information is also provided per URL:

- **URL**: The redirected URL.
- **Response Code**: The HTTP informational or success code.
- **Timing Information**: The distribution of individual key metrics for each URL, in milliseconds.

## Monitoring Cloud Path Probe Metrics

For Cloud Path probes, **End-to-End Latency** metrics are automatically displayed.

### Metrics for End-to-End Latency

The **End-to-End Latency**chart provides the following metrics and actions:

- Click any data point to view metrics for the managed locations.
- Select the drop-down menu to incrementally add Cloud Path probe metrics and charts to End-to-End Latency. To learn more about Cloud Path probe metrics, see [About Probes](https://help.zscaler.com/zdx/about-probes) and [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path).
- Click the chart format icon to switch views between a line chart and a scatter chart. Each data point within a scatter chart represents an individual probe run.

See image.

### Key Metrics

To view granular metrics for the Cloud Path probe:

1. Click the chart format icon to ensure the scatter chart is displayed for End-to-End Latency.
2. Hover over a data point to view metrics for the managed location.
3. Click **View Details**.

See image.

The following key metrics are provided on the Probe Run Details page:

- **End-to-End Latency**: The time to send a data packet from source to destination, including hops between legs in the Cloud Path.
- **Packet Loss**: When data packets that travel across a network fail to reach their destination.
- **Jitter**: The variance in time delay between data packets over a network.
- **Max Hop Count**: The maximum number of Cloud Path hops before reaching the destination.

See image.

### Cloud Path Visualization

A Cloud Path representation is provided for the specified Cloud Path probe. This visualization consolidates all managed locations into one image. Hover over any leg in the path to view route details.

See image.

To learn more about the visualization of data traffic, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path).

[Image: Select filter locations and probe]

[Image: Time range widget]

[Image: Web probe availability chart]

[Image: Page Fetch Time chart]

[Image: View Web probe details]

[Image: Web probe details]

[Image: End-to-End Latency Chart]

[Image: View Cloud Path Probe Details]

[Image: Cloud Path probe granular details page]

[Image: Cloud Path Hop View]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-microsoft-endpoint-analytics-zdx","lastmod":"2023-10-03T19:30Z","nid":"1413026"} -->
## Understanding Microsoft Endpoint Analytics for ZDX

- Source: https://help.zscaler.com/zdx/understanding-microsoft-endpoint-analytics-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Understanding Microsoft Endpoint Analytics for ZDX
- Last modified: 2023-10-03T19:30Z
- Summary: Information about the integration of Microsoft Endpoint analytics in the ZDX Admin Portal.

Microsoft Endpoint Analytics can help identify issues with user software or devices that might be impacting performance and reliability. After you integrate Microsoft Intune with Zscaler Digital Experience (ZDX), metrics are garnered from the Microsoft Intune API and mapped to individual ZDX users and devices to provide Endpoint Analytics scores and metrics.

To view Endpoint Analytics data, you should first configure the Intune application integration. To learn more, see [Configuring Microsoft Intune for ZDX](https://help.zscaler.com/zdx/configuring-microsoft-intune-zdx).

To access Endpoint Analytics, choose one of the following options to view the user details page:

- From the User Overview page, select one or more applications from the Applications filter drop-down menu and click **Apply.**Click the table cell for a user or device to view a page with user details.
- Search for a specific user in Search and view their page. To learn more, see [Using Search in the ZDX Admin Portal](https://help.zscaler.com/zdx/using-user-search-zdx-admin-portal).

## Viewing User Details

The user details page provides the following Endpoint Analytics information for a specified user device:

- **Endpoint Analytics Score**: Reflects the weighted average of the Startup Performance score and Software Reliability score.
- **Health Status**: Shows the most recent status of the device, designated as **Unknown**, has **Insufficient Data**, **Needs Attention**, or is **Meeting Goals**.
- **Last Updated**: Indicates the date and time when metrics were last collected from the Microsoft Intune API. Although data is collected from the API every 3 hours, metrics might not repopulate every 3 hours within the UI. Some Startup Performance events might take up to 24 hours to be displayed, and some Software Reliability events might take up to 3 days to be displayed.
- **View Endpoint Analytics**: Accesses scores and metrics for Startup Performance and Software Reliability.

[Image: Link to View Endpoint Analytics from user details page]

## Viewing Startup Performance Information

**Startup Performance** can help you assess the health and performance of a user's device over time, based on Endpoint Analytics scores, boot and sign-in times, and processes that might affect device startup.

See image.

View the following information on the **Startup Performance** page:

- Score Categories
- Score Over Last 14 Days
- Boot History and Sign-in History
- Top Ten Processes Impacting Startup

The Endpoint Analytics scores are calculated on a scale from 0 (poor) to 100 (exceptional):

- **Startup Performance**: Reflects the weighted average time from system power-on to completion of a user's successful sign in.
- **Software Reliability**: Reflects the reliability of all installed applications and software, based on crash frequency and duration of usage.

[Image: Endpoint Analytics Score Categories]

Click a point in the graph within the previous 14 days to display **Startup Performance** scores, from 0 (poor or slow) to 100 (exceptional or fast):

- **Startup Score**: A weighted average of both **Core Sign-in Score**and **Core Boot Score**.
- **Core Sign-in Score**: An average based on the time when a user enters credentials to when the user accesses a rendered desktop, and CPU usage has fallen below 50% for at least 2 seconds.
- **Core Boot Score**: An average based on the time from system power-on to sign in.

[Image: Endpoint Analytics Score for last 14 days]

Hover over a date within the past 14 days to view the average time it took (in seconds) for device bootup and sign in:

[Image: Boot History and Sign-in History graphs]

View the top processes in which CPU usage might be running above 50% and impacting users:

- **Process Name**: The service or application name.
- **Vendor**: The associated software vendor.
- **Load Time** **(Seconds)**: The time interval from system power-on to when the desktop becomes responsive.

[Image: Table that shows the top ten processes impacting startup]

To learn more about **Startup Performance** insights and recommendations, see the [Microsoft documentation](https://learn.microsoft.com/en-us/mem/analytics/startup-performance).

## Viewing Software Reliability Information

**Software Reliability** can help identify potential problems with software and applications on user devices so you can troubleshoot the cause of the issues. The **Software Reliability Score**, history of software events, and details of those software events can collectively provide insight into the health status of individual devices.

See image.

View the following information on the **Software Reliability** page:

- Software Events Over Last 14 Days
- Software Events

Hover over an event within the past 14 days to view the number of event types that occurred on that particular day:

[Image: Graph that shows the number of software events occurred over past 14 days]

View details of software events that occurred within the past 14 days:

- **Event**: The type of application or software event.
- **Time**: The date and time when the event occurred.
- **Name**: The name of the associated service, application, or software being used.
- **Publisher**: The company that licenses the service, application, or software.
- **Version**: The numbered version of the service, application, or software.

[Image: Table that shows software events by date and time]

To learn more about **Software Reliability**, see the [Microsoft documentation](https://learn.microsoft.com/en-us/mem/analytics/app-reliability).

[Image: Startup Performance drawer]

[Image: Software Reliability drawer]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-microsoft-teams-call-quality-zdx","lastmod":"2024-10-04T13:32Z","nid":"1386191"} -->
## Understanding Microsoft Teams Call Quality for ZDX

- Source: https://help.zscaler.com/zdx/understanding-microsoft-teams-call-quality-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Applications > Understanding Microsoft Teams Call Quality for ZDX
- Last modified: 2024-10-04T13:32Z
- Summary: Information about monitoring Microsoft Teams Call Quality in the ZDX Admin Portal.

Microsoft Teams Call Quality allows you to monitor actual one-to-one calls or meetings among two or more participants in a configured Microsoft Teams tenant. The ZDX Score for Call Quality can reflect either a Mean Opinion Score (MOS) average, or metric thresholds for latency, jitter, and packet loss. Call Quality works in parallel with Cloud Path probes, device metrics, and device events to help you identify issues that are unique to a device, application, or network.

When a call has ended, Call Quality data is retrieved using the Microsoft Graph API. Currently, the Microsoft Graph API only provides data for the entire call after the call has completed, and does not provide incremental data while a call is in progress. User and device information garnered from Microsoft for call participants is mapped to ZDX users and devices.

To monitor calls or meetings with Microsoft Teams Call Quality, you first must configure the application and meet the feature prerequisites. To learn more, see [Configuring Microsoft Teams Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-microsoft-teams-call-quality-zdx).

To access Microsoft Teams Call Quality monitoring, do one of the following from the ZDX Admin Portal:

- Go to **Applications**and click **Microsoft Teams** **Call Quality** in the Applications Overview page.
- Click the **Microsoft Teams Call Quality** application card.

See image.

## Viewing Microsoft Teams Call Quality Data

From the Microsoft Teams Call Quality page, you can set filters for time range, Departments, Zscaler Locations, User Groups, Geolocations, Location Groups, and Last Mile ISPs to help assess Call Quality data. For time range, select a period between 2 Hours and 48 Hours, or a custom range to specify a date and time. The default time range is 2 Hours. To learn more about Department and Location filters, see [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

The Microsoft Teams Call Quality page reports on the Call Quality for all users participating in Microsoft Teams calls, and includes the following information:

- **ZDX Score Over Time**: The ZDX Score for Call Quality reflects the entire duration of a call for all users. The score is based on the MOS or metric thresholds. To learn more, see Calculating the ZDX Score.
- **Regions by ZDX Score**: The Microsoft Teams meeting participant locations.
- **Impacted Meetings**: The meetings with low ZDX Scores, with the lowest ZDX Score at the top. Hover over any Meeting ID to view the time and length of that meeting.
- **Probe Status**: The metrics for Cloud Path probes configured as part of the Microsoft Teams Call Quality application. Web probes are not allowed for this application. The following image shows metrics calculated for a Cloud Path host discovered by ZDX Autosense, which can dynamically detect a destination IP address and provide an automated Cloud Path probe when you onboard a Microsoft Teams Call Quality tenant. To learn more, see [Configuring Microsoft Teams Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-microsoft-teams-call-quality-zdx).
  - **IP Address**: The destination IP address.
  - **Domain Name**: The URL for the Cloud Path host.
  - **Users**: The number of managed users in the call.
  - **Devices**: The number of managed devices in the call.

See image.

## Viewing Meetings Data

The Meetings page shows a list of meetings (including one-on-one calls) for your organization, sorted by date, based on your time range and filter settings:

- **Zscaler Locations**: Your locations, as defined in ZIA. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
- **Users**: The names of your users participating in calls.

The table provides the following information for each meeting:

Meetings are not reported if none of the participants are monitored ZDX users for Call Quality. At least one meeting participant must be a monitored ZDX user. To learn more about configuring ZDX users for meetings, see [Configuring Microsoft Teams Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-microsoft-teams-call-quality-zdx).

1. **Meeting ID**: You can click any meeting ID to view meeting details. For confidentiality, the internal meeting ID is displayed, and not the actual meeting topic name.
2. **ZDX Score**: The following Call Quality metrics are used to determine the score:
  - **Latency**:The time taken to send a data packet from point A to point B, such as the hop between legs within the Cloud Path.
  - **Jitter**: The variance in time delay between data packets over a network.
  - **Packet loss**: When data packets that travel across a computer network fail to reach their destination.

The ZDX Score for Call Quality is calculated by one of the following methods, utilizing values for latency, jitter, and packet loss:

- Using the MOS (rated from 1 to 5, worst to best)
- Using metric thresholds

To learn more, see Calculating the ZDX Score.

1. **Mean Opinion Score**: The MOS average might be integrated into the ZDX Score to rate a call's quality.
2. **Host**: The name of the meeting host.
3. **Start Time and Duration**: The length of the entire call. Partial data for meetings in progress is not captured. Call data from the Microsoft Graph API might take 15 minutes to 1 hour to be available after a meeting has ended.
4. **Active participants**: The number of participants as reflected in the session data. Participants who join a call from multiple devices or who leave and rejoin a call are counted per session. This number might differ from Active Participants in the Meeting Details summary, which shows the number of participants derived from the Microsoft Graph API.
5. **Action**: Click the **View**icon to view meeting details.

Up to 1,000 meetings are displayed. Use the filters for time range, Zscaler Locations, and Users to help identify a particular meeting. You can also use the Search field by entering any character string or numbers for a Meeting ID, ZDX Score, MOS Score, Host, or Active Participants. Blank fields for the ZDX Score and MOS indicate that no score is available.

[Image: Table data for meetings]

### Meeting Details

If you click a meeting name or its associated **View**icon within the Meetings tab, the following details are displayed:

- **Meeting Details**: Includes the host of the designated meeting. A MOS Score is displayed only if metrics for latency, jitter, and packet loss are all available for MOS input and MOS output.
- **Sessions and User Devices**: In addition to meeting start and duration times, ZDX Score, and MOS, you can also view the Session ID (this is the internal Microsoft Teams session ID), the device OS, IP Address, Geolocation, and the Audio type. Some IP addresses shown might be truncated by Microsoft for privacy reasons.

You can click usernames within the page to view ZDX details for user device, application, and metrics. However, details are not available for users and devices identified as follows:

- External users who do not belong to the configured Microsoft Teams Call Quality tenant. These users might appear as an "External User" or have no username displayed. Microsoft hides the names of external users for privacy reasons.
- Guest users who are either not part of a Microsoft Teams tenant account or they haven't logged in to their Microsoft Teams account, but participate in the Teams meeting using their web browser. These users might appear as a "Guest User" or have no username displayed.
- Some ZIA users might not have ZDX enabled, and therefore, their user details are unavailable.
- Some user devices might not map to a corresponding registered ZDX device.
- Some ZDX users are not being monitored for Call Quality.
- Insufficient information is available to map a user to a ZDX device.

See image.

### Meeting Monitoring Metrics

Metrics for your meetings are included within the user details page. The values for Audio, Video, and Sharing Quality are displayed as follows:

- **Latency**: Range starts at 0ms
- **Jitter**: Range starts at 0ms
- **Average Loss**: 0 to 100%
- **Max Loss**: 0 to 100%

To learn how the latency, jitter, and packet loss values are used to determine the ZDX Score, see Calculating the ZDX Score.

See image.

## Calculating the ZDX Score

The ZDX Score for Call Quality is calculated by one of the following methods:

- Mean Opinion Score
- Metric Thresholds

If metrics for latency, jitter, and packet loss are all available for calculation, the MOS is used to determine the ZDX Score. MOS uses a transmission rating factor (R-Factor) that generates range levels it derives from those particular metrics. The range levels help determine the ZDX Score for Call Quality as Poor, Okay, or Good.

The range levels for MOS are as follows:

- If the MOS is 0 to 3.6, the ZDX Score = Poor.
- If the MOS is greater than 3.6 but less than 4.34, the ZDX Score = Okay.
- If the MOS is 4.34 or greater, the ZDX Score = Good.

If only one or two of the metrics is available for calculation, metric thresholds are used to determine the ZDX Score.

The ZDX Score is rated Poor when metrics exceed their respective thresholds:

- If the latency range is above 500ms
- If the jitter range is above 30ms
- If packet loss is above 0.1%

Ranges above and below the threshold are assigned a corresponding metric-based score to help define whether the ZDX Score is rated Poor or Good. The ZDX Score is calculated by using the lowest metric-based score of two metrics, or the lowest metric-based score of one metric. For example, if latency is 550ms with a corresponding metric-based score of 30, and jitter is 26ms with a corresponding metric-based score of 70, the ZDX Score is based on the lower metric-based score, which is 30.

If all three metrics for latency, jitter, and packet loss are unavailable, the ZDX Score for Call Quality cannot be calculated and data is not displayed.

[Image: Application card for Microsoft Teams Call Quality]

[Image: Microsoft Teams Call Quality page]

[Image: Page that shows meeting details]

[Image: Example of Meeting Monitoring Metrics graph]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-probing-criteria-logic","lastmod":"2026-06-26T11:08Z","nid":"1486366"} -->
## Understanding Probing Criteria Logic

- Source: https://help.zscaler.com/zdx/understanding-probing-criteria-logic
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Probes > Understanding Probing Criteria Logic
- Last modified: 2026-06-26T11:08Z
- Summary: Provides supplemental details on the logic when configuring a probe for an application.

When you configure a probe for an application, the inclusion and exclusion configuration fields use different logic based on what you select. You can create a combination of the inclusion and exclusion criteria to be specific towards your probing data needs.

## Simple Probing Criteria

In the following scenarios, we are considering separate Probing and Exclusion Criteria and multiple items selection. These are helpful in analyzing a broader use case for a user, department, or location.

- Probing Criteria
- Exclusion Criteria
- Multiple Items Selection

See image.

The OS selection is supported for [end user probe configurations](https://help.zscaler.com/zdx/configuring-probe).

When you want to include criteria for a probe, the probe uses the AND logic.

For example, you create a probe to include a user named John Doe in Group B and in the Finance Department.

- User: John Doe
- User Group: Group B
- Department: Finance

Expression: John Doe AND Group B AND Finance

When you want to exclude criteria for a probe, the probe uses the OR logic. The Exclusion Criteria avoids gathering probing data for the selected criteria. This helps avoid gathering data for non-impacted users.

For example, you create a probe to exclude multiple users in Group C or in Finance.

Exclusion Criteria:

- User Group: Group C
- Department: Finance

Expression: NOT (Group C OR Finance)

You can select multiple items within the field criteria that uses the OR Logic. This is applicable to both the Probing and Exclusion Criteria. You can select multiple items to expand your selection criteria (e.g., multiple locations, user groups, departments).

**Probing Criteria**

Scenario: You want a probe for multiple locations and user groups.

- Location:
  - San Jose
  - Los Angeles
- User Group:
  - Group B
  - Group C

Expression: (San Jose OR Los Angeles) AND (Group B OR Group C)

**Exclusion Criteria**

Scenario: You want a probe to exclude selected locations and departments.

- Location:
  - Sacramento
  - San Francisco
- Department:
  - Engineering
  - Finance

Expression: NOT (Sacramento OR San Francisco) AND NOT (Engineering OR Finance)

## Complex Probing Criteria Logic

You can combine the Probing Criteria and Exclusion Criteria for more specific cases.

The Exclusion Criteria is evaluated first and then the Probing Criteria.

For example, you create a probe to include a specific user groups in a location, but want to exclude specific departments or a location so that you can isolate to only users of interest.

- Probing Criteria:
  - User Group: Group B
  - Location: California
- Exclusion Criteria:
  - Department: Finance
  - Location: San Jose

Expression: NOT (Finance OR San Jose) AND (Group B AND California)

## Using Criteria Effectively

In order to use criteria effectively, consider the following:

- Using OR can expand your results.
- Using AND reduces your results.
- Combining inclusion and exclusion criteria decreases the amount of your probe results.

If you do not find any results, Zscaler recommends reducing the amount of criteria.

[Image: Probing Criteria Logic]

[Image: Multiple Items Selection]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-real-user-monitoring","lastmod":"2026-05-03T07:06Z","nid":"1535279"} -->
## Understanding Real User Monitoring

- Source: https://help.zscaler.com/zdx/understanding-real-user-monitoring
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Understanding Real User Monitoring
- Last modified: 2026-05-03T07:06Z
- Summary: Information on Real User Monitoring that captures metrics to analyze user interactions with applications and their web browsers.

Real User Monitoring (RUM) captures metrics where you can analyze user interactions with applications through their web browsers on Windows and macOS devices. RUM creates a comprehensive view into the application's digital experience. Use RUM to identify bottlenecks a user encounters while they are accessing their applications through their web browsers. RUM provides metrics based on web pages such as Page Fetch Time, Page Loading Time, and Core Web Vitals (i.e., Largest Contentful Paint).

## Prerequisites

Before RUM can gather metrics, you must have the following:

- The appropriate subscription level. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- The appropriate permissions for a ZDX role where you can: To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
  - Configure applications.
  - View user information and the performance dashboard.
- The appropriate version compatibility for Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
- The device's browser has installed the latest RUM browser extension. For Chrome browsers, refer to [Chrome Web Store - Zscaler Real User Monitoring (RUM)](https://chromewebstore.google.com/detail/zscaler-real-user-monitor/eojpolfgdcogokpbcgejpgeblcjellak?utm_source=item-share-cb). For Microsoft Edge browsers, refer to [Microsoft Edge Add-ons - Zscaler Real Time User Monitoring](https://microsoftedge.microsoft.com/addons/detail/realtimeusermonitoring/dggndchpbpgadddajngjekkkegacfaea).
- You must allowlist `ZUpmApplication.exe` on the device. To learn more, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).

## Configuring for Real User Monitoring

Zscaler recommends preparing Real User Monitoring by configuring the following steps:

1. Ensure you have the prerequisites.
2. Install the latest version of the RUM browser extension for the browser on a device. If the device does not have the Real User Monitoring browser extension, then you cannot continue configuring Real User Monitoring. You can use a mobile device management solution (e.g., Microsoft Intune) to install the extension on multiple devices.
3. (Recommended) [Create a custom RUM collection](https://help.zscaler.com/zdx/managing-collections#enduser_add) for end users to organize and view all enabled Real User Monitoring applications.
4. [Enable Real User Monitoring](https://help.zscaler.com/zdx/adding-custom-application#realusermonitoring) when you configure an application.
5. [Activate changes.](https://help.zscaler.com/unified/saving-and-activating-changes-admin-console)

## Viewing Real User Monitoring

You can view Real User Monitoring in the following:

- [Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview)
- [Application Details](https://help.zscaler.com/zdx/evaluating-application-details)
- [Users Overview](https://help.zscaler.com/zdx/monitoring-users-overview)
- [User Details](https://help.zscaler.com/zdx/evaluating-user-details)
- [Performance Dashboard](https://help.zscaler.com/zdx/monitoring-performance-dashboard)

You can configure an [alert rule for RUM](https://help.zscaler.com/zdx/configuring-alert-rule) to receive notifications.

Depending on your ZDX role, you might not see some of these overviews, details, dashboard, or alert configuration.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-remediation","lastmod":"2026-06-15T16:42Z","nid":"1526086"} -->
## Understanding Remediation

- Source: https://help.zscaler.com/zdx/understanding-remediation
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Remediation > Understanding Remediation
- Last modified: 2026-06-15T16:42Z
- Summary: Information for Remediation for ZDX where you can remotely configure specific PowerShell scripts or use a predefined script to remediate device issues.

Remediation for ZDX allows IT admins to anticipate potential issues and proactively resolve them by deploying remote scripts before end users can report a problem. With fewer support tickets reported, IT admins have improved focus and productivity on resolving device issues directly that enhance the overall digital experience.

You can configure specific scripts to run devices and schedule to remotely run the scripts at an appropriate time when the device is able to update or provide maintenance. After proper configuration, IT admins can view all configured scripts and Remediation jobs that are In Progress, Completed, or Scheduled. This provides a comprehensive visibility from when the script was first configured to the details of when the Remediation job ends.

## Preparing Remediation in the Zscaler Admin Console

You must have the following prerequisites in the Zscaler Admin Console to start running a remote script:

- The appropriate ZDX subscription level for Remediation. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- The appropriate permission level for the ZDX admin to manage Remediation pages. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles). An admin with one of the following permissions can:
  - Script Management: Configure or view scripts on the Scripts page.
  - Script Templates: Import or view predefined scripts from the Scripts Templates page.
  - Remediation Jobs: Run or view Remediation jobs from the Remediation Jobs page.
  - Remediation Settings: Configure or view the Remediation settings.
- If you are running a script, the selected devices must have the appropriate version compatibility and supporting OS. If a device is not compatible, then the script does not run on that device. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
- Enable Remediation. To learn more, see [Configuring Remediation Settings](https://help.zscaler.com/zdx/configuring-remediation-settings).

## Preparing Remediation on End User Devices

You must have the following prerequisites on an end user device that you want to run a remote script on:

- PowerShell's Execution Policy on the device is not restricted.
- Allowlist-specific applications based on your Windows version.

If you are using predefined scripts, you can start to prepare and configure for Remediation.

If you are using custom scripts, you can prepare a certificate and have it added to the Trusted Publisher's directories. To learn more, see [Preparing Custom Script Signing](https://help.zscaler.com/zdx/preparing-custom-script-signing).

## Remediation Scripts

There are two types of scripts you can use for Remediation:

- Predefined Scripts
- Custom Scripts

Zscaler provides predefined scripts to make it easier to run remote scripts.

Custom scripts provide more customization and flexibility to run scripts as needed.

### Predefined Scripts Overview

Zscaler recommends preparing and configuring predefined scripts in the following order:

1. Ensure you have the prerequisites for Remediation:
  - Prerequisites for Remediation in the Zscaler Admin Console
  - Prerequisites for Remediation on end user devices
2. [Import the predefined script as a remote script.](https://help.zscaler.com/zdx/managing-scripts#predefinedscripts)
3. [Configure the remote script](https://help.zscaler.com/zdx/managing-scripts#add) and [start a remediation job](https://help.zscaler.com/zdx/viewing-and-managing-device-remediation#start) to test the script.
4. Deploy the tested script to production devices.

You can view the job status and details on the [Device Remediation](https://help.zscaler.com/zdx/viewing-and-managing-device-remediation#tables) page.

[Image: Predefined Script Overview]

### Custom Scripts Overview

Zscaler recommends preparing and configuring custom scripts in the following order:

1. Ensure you have the following prerequisites for Remediation:
  - Prerequisites for Remediation in the Zscaler Admin Console
  - Prerequisites for Remediation on end user devices
2. [Prepare custom script signing.](https://help.zscaler.com/zdx/preparing-custom-script-signing)
3. Write your script and sign it.
4. [Configure the remote script](https://help.zscaler.com/zdx/managing-scripts#add) and [start a remediation job](https://help.zscaler.com/zdx/viewing-and-managing-device-remediation#start) to test the script.
5. Deploy the tested script to production devices.

You can view the job status and details on the [Device Remediation](https://help.zscaler.com/zdx/viewing-and-managing-device-remediation#tables) page.

[Image: Custom Script Overview]

1. Open the PowerShell and run it as an `Administrator`.
2. Enter one of the following prompts depending on which Execution Policy you want for the device: `Set-ExecutionPolicy AllSigned -Scope LocalMachine``Set-ExecutionPolicy RemoteSigned -Scope LocalMachine`
3. Enter `A` to indicate *yes to all* to allow you to run remote scripts.
4. Verify if the policy is set correctly by running the following command to ensure rewritten and custom scripts for Remediation can run: `Get-ExecutionPolicy -List`

- 32-bit
- 64-bit

```
%ProgramFiles(x86)%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptOrchestratorService.exe
%ProgramFiles(x86)%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptExecutor.exe
```

```
%ProgramFiles%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptOrchestratorService.exe
%ProgramFiles%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptExecutor.exe
```
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-tunnel-information-cloud-path","lastmod":"2026-05-07T21:06Z","nid":"1374021"} -->
## Understanding Tunnel Information in the Cloud Path

- Source: https://help.zscaler.com/zdx/understanding-tunnel-information-cloud-path
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Understanding Tunnel Information in the Cloud Path
- Last modified: 2026-05-07T21:06Z
- Summary: Information about the tunnels shown in the Cloud Path section in the User details.

In the Cloud Path section in the User details, you can view the flow of traffic through the applicable tunnels as per the configured policies.

## Tunnel Types

Tunnels in ZDX are of two types:

- The client connects to the Public Service Edge for Internet & SaaS using Zscaler Tunnel (Z-Tunnel) 1.0 or Z-Tunnel 2.0. To learn more, see [About Z-Tunnel 1.0 and Z-Tunnel 2.0](https://help.zscaler.com/zscaler-client-connector/about-z-tunnel-1.0-z-tunnel-2.0).
- The client connects using a location tunnel such as GRE or IPSec. To learn more, see [Understanding Generic Routing Encapsulation (GRE)](https://help.zscaler.com/zia/understanding-generic-routing-encapsulation-gre).

The tunnels that you can see in the Cloud Path section are:

- GRE Tunnel
- IPSec Tunnel
- Z-Tunnel 1.0
- Z-Tunnel 1.0 over IPSec
- Z-Tunnel 1.0 over GRE
- Z-Tunnel 2.0
- Z-Tunnel 2.0 over GRE
- Z-Tunnel 2.0 over IPSec

[Image: Tunnel Information in Cloud Path]

If the client connects to an application using a Direct Request, then this situation indicates the client bypasses the tunnel and goes directly to the application. In this situation, no tunnel graphic is displayed in the Hop View because there is no tunnel information to view.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-webex-call-quality-zdx","lastmod":"2024-10-04T13:59Z","nid":"1446941"} -->
## Understanding Webex Call Quality for ZDX

- Source: https://help.zscaler.com/zdx/understanding-webex-call-quality-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Applications > Understanding Webex Call Quality for ZDX
- Last modified: 2024-10-04T13:59Z
- Summary: Information about monitoring Webex Call Quality in the ZDX Admin Portal.

Webex Call Quality allows you to monitor actual one-to-one calls or meetings among two or more participants in a configured Webex tenant. The ZDX Score for Call Quality can reflect either a Mean Opinion Score (MOS) average, or metric thresholds for latency, jitter, and packet loss. Call Quality works in parallel with cloud path probes, device metrics, and device events to help you identify issues that are unique to a device, application, or network.

Call Quality data is retrieved using the Webex API, and data is captured in real time, available 5 minutes after the start of a call. User and device information garnered from the API for call participants is mapped to ZDX users and devices. While incremental data is available for calls in progress, complete call data is available approximately 10 minutes after a call has ended.

To monitor calls or meetings with Webex Call Quality, you'll first need to configure the application and meet the feature prerequisites. To learn more, see [Configuring Webex Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-webex-call-quality-zdx).

To access Webex Call Quality monitoring, do one of the following from the ZDX Admin Portal:

- Go to **Applications**and click **Webex Call Quality** in the Applications Overview page.
- Click the **Webex Call Quality** application card in the Performance Overview page.

See image.

## Viewing Webex Call Quality Data

From the Webex Call Quality page, you can set filters for time range, Departments, Zscaler Locations, User Groups, Geolocations, Location Groups, and Last Mile ISPs to help assess Call Quality data. For time range, select a period between 2 Hours and 48 Hours, or a custom range to specify a date and time. The default time range is 2 Hours. To learn more about application filters, see [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

The Webex Call Quality page reports on Call Quality for all users participating in Webex calls, and includes the following information:

- **ZDX Score Over Time**: The ZDX Score for Call Quality reflects the entire duration of a call for all users. The score is based on the MOS or metric thresholds. To learn more, see Calculating the ZDX Score.
- **Regions by ZDX Score**: The Webex meeting participant locations.
- **Impacted Meetings**: The meetings with low ZDX Scores, with the lowest ZDX Score at the top. Hover over any meeting ID to view the time and length of that meeting.
- **Probe Status**: The metrics for Cloud Path probes configured for the Webex Call Quality application. Only Cloud Path probes can be configured, as Web probes are not allowed for this application. The following image shows metrics calculated for a Cloud Path host discovered by ZDX Autosense, which can dynamically detect a destination IP address and provide an automated Cloud Path probe when you onboard a Webex Call Quality tenant. To learn more, see [Configuring Webex Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-webex-call-quality-zdx).
  - **Domain Name**: The URL for the Cloud Path host.
  - **IP Address**: The destination IP address.
  - **Users**: The number of managed users in the call.
  - **Devices**: The number of managed devices in the call.

See image.

## Viewing Meetings Data

The Meetings page shows a list of meetings (including one-on-one calls) for your organization, sorted by date, based on your time range and filter settings:

- **Zscaler Locations**: Your locations, as defined in ZIA. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
- **Users**: The names of your users participating in calls.

The table provides the following information for each meeting:

Meetings are not reported if none of the participants are monitored ZDX users for Call Quality. At least one meeting participant must be a monitored ZDX user. To learn more about configuring ZDX users for meetings, see [Configuring Webex Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-webex-call-quality-zdx).

1. **Meeting ID**: You can click any meeting ID to view details of scheduled meetings. For confidentiality, the internal meeting ID is displayed, and not the actual meeting topic name.
2. **ZDX Score:** The following Call Quality metrics are used to determine the score:
  - **Latency**:The time taken to send a data packet from point A to point B, such as the hop between legs within the Cloud Path.
  - **Jitter**: The variance in time delay between data packets over a network.
  - **Packet loss**: When data packets that travel across a computer network fail to reach their destination.

The ZDX Score for Call Quality is calculated by one of the following methods, utilizing values for latency, jitter, and packet loss:

- Using the MOS (rated from 1 to 5, worst to best)
- Using metric thresholds

To learn more, see Calculating the ZDX Score.

1. **Mean Opinion Score**: The MOS average might be integrated into the ZDX Score to rate a call's quality.
2. **Host**: The name of the meeting host.
3. **Start Time and Duration**: The beginning and duration of the call, indicated by either the time of the entire call or a call in progress. Ongoing calls are shown as In Progress to indicate the call has not ended. Call data for meetings in progress is captured every 5 minutes. Availability of call data begins 5 minutes after a meeting has started.
4. **Active Participants**: The number of participants as reflected in the session data. Participants who join a call from multiple devices or who leave and rejoin a call are counted per session. This number might differ from Active Participants in the Meeting Details summary, which shows the number of participants derived from the Webex API.
5. **Action**: Click the **View**icon to view meeting details.

Up to 1,000 meetings are displayed. Use the filters for time range, Zscaler Locations, and Users to help identify a particular meeting. You can also use the Search field by entering any character string or numbers for a Meeting ID, ZDX Score , MOS Score, Host, or Active Participants. Blank fields for the ZDX Score and MOS indicate that no score is available.

[Image: Table headings on Webex Call Quality Meetings page]

### Meeting Details

If you click a meeting name or its associated **View**icon within the Meetings tab, the following details are displayed:

- **Meeting Details**: Includes the host of the designated meeting. A MOS Score is displayed only if metrics for latency, jitter, and packet loss are all available for MOS input and MOS output.
- **Sessions and User Devices**: In addition to meeting start and duration times, ZDX Score , and MOS, you can also view the Session ID (this is the internal Webex session ID), the device OS, IP Address, Geolocation, and the Audio type.

You can click usernames within the page to view ZDX details for user device, application, and metrics. However, details are not available for users and devices identified as follows:

- External users do not belong to the configured Webex Call Quality tenant. These users might appear as an "External User" or have no username displayed. Webex hides the names of external users for privacy reasons.
- Guest users are either not part of a Webex tenant account or they haven't logged in to their account, but they participate in the Webex meeting using their web browser. These users might appear as a "Guest User" or have no username displayed.
- Some ZIA users might not have ZDX enabled, and therefore, their user details are unavailable.
- Some user devices might not map to a corresponding registered ZDX device.
- Some ZDX users are not being monitored for Call Quality.
- Insufficient information is available to map a user to a ZDX device.

See image.

### Meeting Monitoring Metrics

Metrics for your meetings are included within the user details page. The values for Audio, Video, and Sharing Quality are displayed as follows:

- **Latency**: Range starts at 0ms
- **Jitter**: Range starts at 0ms
- **Average Loss**: 0 to 100%
- **Max Loss**: 0 to 100%

To learn how the latency, jitter, and packet loss values are used to determine the ZDX Score, see Calculating the ZDX Score.

See image.

## Calculating the ZDX Score

The ZDX Score for Call Quality is calculated by one of the following methods:

- Mean Opinion Score
- Metric Thresholds

If metrics for latency, jitter, and packet loss are all available for calculation, the MOS is used to determine the ZDX Score. MOS uses a transmission rating factor (R-Factor) that generates range levels it derives from those particular metrics. The range levels help determine the ZDX Score for Call Quality as Poor, Okay, or Good.

The range levels for MOS are as follows:

- If the MOS is 0 to 3.6, the ZDX Score = Poor.
- If the MOS is greater than 3.6 but less than 4.34, the ZDX Score = Okay.
- If the MOS is 4.34 or greater, the ZDX Score = Good.

If only one or two of the metrics is available for calculation, metric thresholds are used to determine the ZDX Score.

The ZDX Score is rated Poor when metrics exceed their respective thresholds:

- If the latency range is above 500ms
- If jitter range is above 30ms
- If packet loss is above 0.1%

Ranges above and below the threshold are assigned a corresponding metric-based score to help define whether the ZDX Score is rated Poor or Good. The ZDX Score is calculated by using the lowest metric-based score of two metrics, or the lowest metric-based score of one metric. For example, if latency is 550ms with a corresponding metric-based score of 30, and jitter is 26ms with a corresponding metric-based score of 70, the ZDX Score is based on the lower metric-based score, which is 30.

If all three metrics for latency, jitter, and packet loss are unavailable, the ZDX Score for Call Quality cannot be calculated and data is not displayed.

[Image: Webex Call Quality application card]

[Image: Viewing the Webex Call Quality page]

[Image: Example of Meeting Details page for Webex Call Quality]

[Image: Meeting Monitoring Metrics graph]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-zdx-application-fields-servicenow","lastmod":"2026-07-10T10:04Z","nid":"1456846"} -->
## Understanding the ZDX Application Fields on ServiceNow

- Source: https://help.zscaler.com/zdx/understanding-zdx-application-fields-servicenow
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > ServiceNow Configuration Guides > Understanding the ZDX Application Fields on ServiceNow
- Last modified: 2026-07-10T10:04Z
- Summary: To describe the configurable settings and mappings module fields in the ZDX application on ServiceNow for alerting requirements.

After assigning the Incident Management role `x_zsca2_zdx_manage.zdx_management` to your ServiceNow service user, you can configure the following in the ZDX Application on ServiceNow:

- The **Settings** module to meet your ZDX integration needs.
- The **Mappings** module to map categories and subcategories for incoming alerts or created incidents.

## Settings Module

In the **Settings** module under the Zscaler Digital Experience application menu, you can configure the following setting properties:

- **Enter a username to use for Caller Name field (Make sure to use web service user's ID)**: The name or ID of the user who is designated to create the incidents. It is mandatory to use the user ID of the service user created for the application. Otherwise, the Caller name remains empty in incidents.
- **Specify the logging level for the transform script**: Specifies the minimum level of log messages to be created. The default is **Information**. For example, if the logging level is set to Information, only Information and Error messages are logged, and the debug messages are skipped.
- **Only create incidents for severity level or higher**: Specifies the minimum level of severity for incoming alerts. The default level is **High**. For example, if the severity is set to Medium, only alerts with the severity of Medium or High are created, and Low severity alerts are skipped.
- **Automatically resolve incidents if the alert ended**: Enables the application to automatically resolve incidents whose source alert ended in ZDX. For example, if the value is set to **Yes**, then the state of the incident is set to **Resolved**. Otherwise, the state does not change, but the Active field is set to **false**.
- **Enter a default name or ID of the resolver for automatically resolved incidents (Make sure to use web service user's ID)**: The name or ID of the user who is designated to resolve the incidents. It is mandatory to use the user ID of the service user created for the application. Otherwise, the Caller name remains empty in incidents.
- **Select resolution code for automatically resolved incidents**: The code used when resolving an incident. You can select one of the options defined for the property. The options match SeviceNow's resolution code values. The default is **Closed/Resolved by the caller**.
- **Enter a ZDX PORTAL URL**: The URL of a target Zscaler Admin Console that is used to run Deep Tracing sessions and ZDX Score Analysis.
- The supported format is `[subdomain].[second-level-domain].[top-level-domain]`. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).
- **Enter a ZDX API URL**: The URL where the public API has access. The supported format is `[subdomain].[second-level-domain].[top-level-domain]`. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).
- **Enter the Key ID used to access the Zscaler Admin Console. Should be created via ZDX API Key Management**: The Key ID from your API Key.
- **Enter the Key Secret used to access the Zscaler Admin Console. Should be created via ZDX API Key Management**: The Key Secret from your API Key.

See image.

## Mappings Module

Prior to configuring the Mappings module, you must map the ZDX Alert Types to the ServiceNow categories and subcategories. To learn more, see [ZDX Integration with ServiceNow](https://help.zscaler.com/zdx/zdx-integration-servicenow).

In the **Mappings** module under the Zscaler Digital Experience application menu, you can configure the following to map ZDX Alert types to ServiceNow Incident's category and subcategory:

- **Mapping for Zscaler Alerts**: To map all the ZDX Alerts to one category and use subcategories for specific ZDX types. For example: The **Zscaler Alert Category** is used for all ZDX Alerts, ZDX Alert Device, ZDX Alert Network, ZDX Score, and ZDX Alert Application. The property value remains empty if:
  - Each ZDX alert type is mapped to a specific category from ServiceNow.
  - No mapping is provided.
- **Mapping for Zscaler Alert type - Device**: Provides mapping for all the alerts that have been configured as Device alerts in the Zscaler Admin Console.
  - If the root mapping is not empty, then the value is mapped to the **subcategory** field of the incident table.
  - If the root mapping is empty, then the value is mapped to the **category** field of the incident table.
- **Mapping for Zscaler Alert type - Network**: Provides mapping for all alerts that have been configured as Network alerts in the Zscaler Admin Console.
  - If the root mapping is not empty, then the value is mapped to the **subcategory** field of the incident table.
  - If the root mapping is empty, then the value of this field is mapped to the **category** field of the incident table.
- **Mapping for the Zscaler alert type - ZDX Score**: Provides mapping for all alerts that have been configured in Zscaler Admin Console as ZDX Score.
  - If the root mapping is not empty, then the value is mapped to the **subcategory** field of the incident table.
  - If the root mapping is empty, then the value is mapped to the **category** field of the incident table.
- **Mapping for Zscaler alert type - Application**: Provides mapping for all alerts that have been configured as Application alerts in the Zscaler Admin Console.
  - If the root mapping is not empty, then the value is mapped to the **subcategory** field of the incident table.
  - If the root mapping is empty, then the value is mapped to the **category** field of the incident table.
  - If no mapping was provided at all, then the value for **category** field is mapped to **Inquiry / Help**.

See image.

[Image: Select Settings under the ZDX application.]

[Image: Zscaler Digital Experience System Properties]

[Image: Select Mappings under the ZDX Application Menu.]

[Image: Mappings Module]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-zdx-cloud-architecture","lastmod":"2026-03-31T12:57Z","nid":"1391111"} -->
## Understanding the ZDX Cloud Architecture

- Source: https://help.zscaler.com/zdx/understanding-zdx-cloud-architecture
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Understanding the ZDX Cloud Architecture
- Last modified: 2026-03-31T12:57Z
- Summary: Information on the Zscaler Digital Experience (ZDX) service and key components.

With Zscaler Digital Experience (ZDX), organizations can fully monitor the cloud application experience simply and intuitively from the end user perspective. ZDX delivers holistic, end-to-end user experience monitoring across any network, helping IT teams streamline troubleshooting and improve user productivity.

ZDX provides application performance monitoring for customer-defined applications and predefined applications such as Zoom, Box, Salesforce, ServiceNow, and Microsoft 365 applications, including Microsoft Teams, SharePoint Online, OneDrive for Business, and Outlook.

### ZDX Components

The ZDX architecture is built to scale with the monitoring requirements of our clients. The components of our solution are illustrated and described next:

[Image: ZDX components show in a diagram]

- End User Device Performance
- Cloud Path Performance
- Application Performance
- ZDX Scoring

### ZDX Architecture

The ZDX solution architecture is composed of the following blocks:

#### ZDX Central Authority (CA)

The ZDX CA is the brain and nervous system of ZDX. It monitors the cloud and provides a central location for software and database updates as well as policy and configuration settings. The design is similar to that of the Internet & SaaS CA.

#### Zscaler Client Connector

The Zscaler Client Connector provides device metrics at negligible additional CPU consumption. The Zscaler Client Connector exchanges information with the telemetry and policy gateway to receive configuration from ZDX and reports metrics to the cloud service for consumption. The service also provides latitude and longitude coordinates for geolocation if the operating system location services are enabled.

#### Zero Trust Exchange (ZTE)

The ZDX cloud connects and authenticates to Internet & SaaS and Private Access clouds to retrieve users, departments, and locations. It also connects to the Zscaler Client Connector Portal for integrated management of Zscaler Client Connector and ZIA definitions. User-definition infrastructure and integration for ZDX standalone deployments without ZIA/ZPA services are also included.

#### Telemetry and Policy Gateway (TPG)

This is a multi-tenant RESTFUL application for traffic control. The TPG acts as a gateway for monitoring metrics, policies, and data lake. Zscaler Client Connector metrics are sent to Microsoft Azure Data Explorer (ADX) and policies are sent to the Zscaler Client Connector. This also includes a stateless design for scalability.

#### ZDX Admin Portal

With administrator access for configuration, reporting, alerting, and analysis, the ZDX Admin Portal integrates with ZIA/ZPA management to provide a centralized configuration. ZDX provides granular role-based access control with single sign-on (SSO) in the Zscaler Client Connector Portal for administrators.

#### ZDX Analytics

The ZDX cloud leverages the Microsoft ADX analytics service.

#### Call Quality Monitoring

The ZDX cloud integrates with Microsoft Graph API or Zoom to read meetings and call quality data. Customer-specific onboarding is needed so that ZDX can read call quality data.

The service continuously gathers and analyzes data on end user devices, resource utilization, health metrics, and multiple events such as CPU and memory usage, including Wi-Fi connectivity issues that impact the end user experience.

Hop-by-hop network path metrics are measured and analyzed from end-to-end over time, from every user device to the application. With Cloud Path visibility, you can proactively detect and resolve end user connectivity issues to cloud applications, understand ISP usage, and gain visibility through the Zscaler cloud.

ZDX continuously monitors and measures application metrics such as response time and DNS resolution, as well as broader availability metrics of the application.

An aggregated user experience performance score is tracked over time at the user, application, location, department, and organizational level. The ZDX Score ranks user performance and provides visualization for anomaly detection.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-zdx-score","lastmod":"2026-07-19T07:06Z","nid":"1355816"} -->
## Understanding the ZDX Score

- Source: https://help.zscaler.com/zdx/understanding-zdx-score
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Understanding the ZDX Score
- Last modified: 2026-07-19T07:06Z
- Summary: Information on Zscaler Digital Experience (ZDX) scores for an organization, applications, locations, departments, and users.

[Watch a video about the ZDX Score](https://fast.wistia.net/embed/iframe/1dbnua67qk) (shows legacy UI).

A ZDX Score represents all users in your organization, across all applications, all locations, and all cities. You can see the score on the Zscaler Admin Console dashboards that capture Zscaler Digital Experience (ZDX) metrics. Depending on the time period and filters selected within the dashboards, the score adjusts accordingly.

The ZDX Score is based on a scale of 0 (lowest) to 100 (highest) with the lowest numbers indicating a Poor score and the highest numbers indicating a Good score.

A ZDX Score falls into one of three categories:

- **Good**: The score is above an acceptable threshold and ranges from 66-100. The color for this range is green.
- **Okay**: The score is acceptable and ranges from 34-65. The color for this range is amber.
- **Poor**: The score is below an acceptable threshold and ranges from 0-33. The color for this range is red.

Low scores can often be attributed to underlying issues related to the network or local environment. Some potential reasons for a low score include:

- Issues with a user's device (e.g., restarts, amount of CPU in use, etc.).
- Issues with the connection (e.g., Wi-Fi signal strength).
- Slow applications.
- High latency on the network (e.g., Wi-Fi, home network, ISP).

You can start a [Diagnostics](https://help.zscaler.com/zdx/about-diagnostics) session to help evaluate and troubleshoot issues related to low scores.

## Understanding ZDX Score Measurements

Zscaler sends a probe from Zscaler Client Connector to an application every 5 minutes. For each 5-minute period, measurements are taken and given a numerical value from 0 to 100. The lowest value within an hour becomes the value for that hour. This is done for every defined application in the Zscaler Admin Console across all users, their devices, and their locations. From there, Zscaler calculates the score based on what is measured:

- Applications
- Departments, Locations, and Cities
- Organization
- Users

All scores are rounded to the nearest whole number.

## Understanding ZDX Score Types

There are two different ZDX Score types and one combined ZDX Score type depending on how you configure your applications to measure ZDX Score:

- **Synthetic Probe Score**: The score is measured using probes (i.e., Cloud Path probe or Web probe).
- **RUM Score**: The score is measured using [Real User Monitoring (RUM)](https://help.zscaler.com/zdx/understanding-real-user-monitoring).
- **Combined Score**: The score is a combination of both Synthetic Probe Score and RUM Score.

These scores can differ from each other as they use different application configurations and use different metrics to measure the digital experience of a device.

| Metrics | Synthetic Probe Configuration Only | RUM Configuration Only | Combined Configurations |
| --- | --- | --- | --- |
| Web probe metrics: Page Fetch Time; Server Response Time; DNS Time; Availability | Synthetic Probe Score | RUM Score | Both Scores |
| Cloud Path probe metrics for network performance: End-to-End latency; Packet Loss; Hops; Packet Count | Synthetic Probe Score | RUM Score | Both Scores |
| Web browser metrics: Page Views; Route Changes; Page Load Time; Core Web Vitals | N/A | RUM Score | RUM Score |

To determine the score for an application, Zscaler takes all the users that accessed the application for the selected time period and finds the lowest value each user would have experienced for the application. The lowest values for each user are added together and divided by the number of users. This is the application's ZDX Score.

The ZDX Score, for applications, is based primarily on the Page Fetch Time of an application, which is then compared to the weighted average of the Page Fetch of others in the same region. Page Fetch Time includes a baseline for any given region with at least one active device, and baseline metrics are calculated daily for each application on a rolling timeline of the previous 7 days. Regions by ZDX Score assesses the country locations of all users accessing a selected application. In addition, availability of an application can also impact the score (e.g., probes fail because the network is down). To learn more, see [Evaluating User Details](https://help.zscaler.com/zdx/evaluating-user-details) and [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

For example, three users accessed an application during a 24-hour period. The lowest values for the users are: 42, 76, and 62. When calculated, the application's score is 60.

The ZDX Score, for departments, locations, and cities, identifies the lowest value for users accessing the applications from those places and groups during time intervals based on the selected time range. The lowest value represents the department's, location's, or city's score for each time interval. An average of all the time intervals for the selected time period is calculated to provide the score for the time range.

For example, the time interval for the 24-hour time range is one hour. Each hour's score is added together and divided by 25 (24 hours + 1 for the starting score) to provide the ZDX Score.

To find the ZDX Score for your organization, Zscaler identifies the lowest value for each application for time intervals based on the selected time range. The application with the lowest value represents your organization's score for that time interval. An average of all the time intervals for the selected time period is calculated to provide your organization's ZDX Score.

For example, the time interval for the 24-hour time range is one hour. The application with the lowest value represents your organization's score for that hour. Each hour's score is added together and divided by 25 (24 hours + 1 for the starting score) to provide the ZDX Score.

For a user's ZDX Score, a comparison of the values across each application they accessed is done for the selected time period. The application with the lowest value is the user's score, since it represents the user's poorest digital experience for the selected time range.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-zdx-service-desk-role","lastmod":"2023-09-12T18:03Z","nid":"1398521"} -->
## Understanding the ZDX Service Desk Role

- Source: https://help.zscaler.com/zdx/understanding-zdx-service-desk-role
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Administration > Admin Configuration > Understanding the ZDX Service Desk Role
- Last modified: 2023-09-12T18:03Z
- Summary: To provide information on the ZDX Service Desk Role and its tiers.

The ZDX Service Desk Role is a predefined role and can support the following goals:

- Seeks to exceed supported Service Level Agreements (SLAs).
- Reduce escalations, cost, and time spent per ticket.
- Rapidly resolve user-reported issues caused by devices, networks, and applications by using ZDX.
- Proactively alert users of outages and slowdowns before complaints arise.

The ZDX Service Desk Role is divided into different tiers. Each tier allows the admin varying levels of access based on the permissions assigned. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles) and [About ZDX Role-Based Administration](https://help.zscaler.com/zdx/about-zdx-role-based-administration).

Tier 1 has the following permissions:

- User Name

When an admin has access to User Name, then the admin can access User Search.

- User Dashboard

See image.

[Image: The ZDX Service Desk User Search Portal is accessible from the User Dashboard.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/understanding-zoom-call-quality-zdx","lastmod":"2026-05-26T12:01Z","nid":"1386321"} -->
## Understanding Zoom Call Quality for ZDX

- Source: https://help.zscaler.com/zdx/understanding-zoom-call-quality-zdx
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Applications > Understanding Zoom Call Quality for ZDX
- Last modified: 2026-05-26T12:01Z
- Summary: Information about monitoring Zoom Call Quality in the Zscaler Admin Console.

Zoom Call Quality allows you to monitor actual one-to-one calls or meetings among two or more participants in a configured Zoom tenant. The ZDX Score for Call Quality can reflect either a Mean Opinion Score (MOS) average, or metric thresholds for latency, jitter, and packet loss. Call Quality works in parallel with Cloud Path probes, device metrics, and device events to help you identify issues that are unique to a device, application, or network.

Call Quality data is retrieved using the Zoom API, and data is captured in real time as a meeting is in progress. User and device information garnered from the API for call participants is mapped to ZDX users and devices. While incremental data is available for calls in progress, complete call data is available approximately 10 minutes after a call has ended.

To monitor calls or meetings with Zoom Call Quality, you must first configure the application and onboard a Zoom API tenant or Zoom QSS tenant, as well as meet the feature prerequisites. To learn more, see [Configuring Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-zoom-call-quality-zdx).

To access Zoom Call Quality monitoring in the Zscaler Admin Console: Go to **Analytics** > **Digital Experience** > **Applications**> **Zoom Call Quality**.

## Viewing Zoom Call Quality Data

From the Zoom Call Quality page, you can set filters for time range, Departments, Zscaler Locations, User Groups, Geolocations, Location Groups, and Last Mile ISPs to help assess Call Quality data. For time range, select a period between **2 Hours** and **48 Hours**, or a custom range to specify a date and time. The default time range is **2 Hours**. To learn more about application filters, see [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

The Zoom Call Quality page reports on Call Quality for all users participating in Zoom calls, and includes the following information:

- **ZDX Score Over Time**: The ZDX Score for Call Quality reflects the entire duration of a call for all users. The score is based on the MOS or metric thresholds. To learn more, see Calculating the ZDX Score.
- **Regions by ZDX Score**: The Zoom meeting participant locations.
- **Impacted Meetings**: The meetings with low ZDX Scores, with the lowest ZDX Score at the top. Hover over any Meeting ID to view the time and length of that meeting.
- **Probe Status**: The metrics for Cloud Path probes configured for the Zoom Call Quality application. You can configure only Cloud Path probes, as Web probes are not allowed for this application. The following image shows metrics calculated for a Cloud Path host discovered by ZDX Autosense, which can dynamically detect a destination IP address and provide an automated Cloud Path probe when you onboard a Zoom Call Quality tenant. To learn more, see [Configuring Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-zoom-call-quality-zdx).
  - **IP Address**: The destination IP address.
  - **Domain Name**: The URL for the Cloud Path host.
  - **Users**: The number of managed users in the call.
  - **Devices**: The number of managed devices in the call.

See image.

## Viewing Meetings Data

The Meetings page shows a list of meetings (including one-on-one calls) for your organization, sorted by date, based on your time range and filter settings:

Only meetings with two or more participants that last more than one minute are collected and displayed.

- **Zscaler Locations**: Your locations, as defined in Internet & SaaS. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
- **Users**: The names of your users participating in calls.

The table provides the following information for each meeting:

Meetings are not reported if none of the participants are monitored ZDX users for Call Quality. At least one meeting participant must be a monitored ZDX user. To learn more about configuring ZDX users for meetings, see [Configuring Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-zoom-call-quality-zdx).

1. **Meeting UUID**: The meeting's Universally Unique Identifier that can be used for troubleshooting purposes.
2. **Meeting ID**: The meeting identifier visible to the end user. For confidentiality, this identifier is displayed in place of any meeting topic name.
3. **ZDX Score:** The following Call Quality metrics are used to determine the score: The ZDX Score for Call Quality is calculated by one of the following methods, utilizing values for latency, jitter, and packet loss: To learn more, see Calculating the ZDX Score.
  - **Latency**:The time taken to send a data packet from point A to point B, such as the hop between legs within the Cloud Path.
  - **Jitter**: The variance in time delay between data packets over a network.
  - **Packet loss**: When data packets that travel across a computer network fail to reach their destination.
  - Using the MOS (rated from 1 to 5, worst to best)
  - Using metric thresholds
4. **Mean Opinion Score**: The MOS average might be integrated into the ZDX Score to rate a call's quality.
5. **Host**: The name of the meeting host.
6. **Start Time and Duration**: The beginning and duration of the call, indicated by either the time of the entire call or a call in progress. Ongoing calls are shown as In Progress to indicate the call has not ended. Call data for meetings in progress is captured every 5 minutes.
7. **Active Participants**: The number of participants as reflected in the session data. Participants who join a call from multiple devices or who leave and rejoin a call are counted per session. This number might differ from Active Participants in the Meeting Details summary, which shows the number of participants derived from the Zoom API.
8. **Action**: Click the **View**icon to view meeting details.

Up to 1,000 meetings are displayed. Use the filters for time range, Zscaler Locations, and Users to help identify a particular meeting. You can also use the Search field by entering any character string or numbers for a Meeting ID, ZDX Score, MOS Score, Host, or Active Participants. Blank fields for the ZDX Score and MOS indicate that no score is available.

[Image: Meetings data shown on Zoom Call Quality Meetings page]

Due to the Zoom API rate limit of 60,000 per day, Zoom Call Quality might be impacted by other applications using the API, which could cause some meeting information to be unavailable.

### Meeting Details

If you click a Meeting UUID, Meeting ID, or the associated **View**icon within the Meetings page, the following details are displayed:

- **Meeting Details**: Includes the host of the designated meeting. A MOS Score is displayed only if metrics for latency, jitter, and packet loss are all available for MOS input and MOS output.
- **Sessions and User Devices**: In addition to meeting start and duration times, ZDX Score, and MOS, you can also view the Session ID (this is the internal Zoom session ID), the device OS, IP Address, Geolocation, and the Audio type.

You can click usernames within the page to view ZDX details for user device, application, and metrics. However, details are not available for users and devices identified as follows:

- External users do not belong to the configured Zoom Call Quality tenant. These users might appear as an **External User** or have no username displayed. Zoom hides the names of external users for privacy reasons.
- Guest users are either not part of a Zoom tenant account or they haven't logged in to their account, but they participate in the Zoom meeting using their web browser. These users might appear as a **Guest User** or have no username displayed.
- Some Internet & SaaS users might not have ZDX enabled, and therefore, their user details are unavailable.
- Some user devices might not map to a corresponding registered ZDX device.
- Some ZDX users are not being monitored for Call Quality.
- Insufficient information is available to map a user to a ZDX device.

See image.

### Meeting Monitoring Metrics

Metrics for your meetings are included within the user details page. The values for Audio, Video, and Sharing Quality are displayed as follows:

- **Latency**: Range starts at 0ms
- **Jitter**: Range starts at 0ms
- **Average Loss**: 0 to 100%
- **Max Loss**: 0 to 100%

To learn how the latency, jitter, and packet loss values are used to determine the ZDX Score, see Calculating the ZDX Score.

See image.

#### Monitoring Criteria

Monitoring metrics are observed based on the [Monitoring Criteria](https://help.zscaler.com/zdx/configuring-zoom-call-quality-zdx#monitoring-criteria) when you add a Unified Communications as a Service (UCaaS) application tenant. There are two types of monitoring criteria:

- Inclusion Criteria: Meetings are monitored and displayed only for your selected ZDX users
- Exclusion Criteria: Meetings exclude selected users from monitoring. Exclusions remove matching users from the set defined.

Here are examples of common meetings:

- Example 1: There are 3 attendees in a meeting. Result: Meeting metrics for the meeting are not monitored because the host is excluded.
  - 1 attendee is the host AND excluded
  - 2 attendees are included.
- Example 2: There are 3 attendees in a meeting. Result: Meeting metrics for the meeting are visible, but monitoring metrics for the excluded participant are not visible. See image.
  - 1 attendee is excluded AND NOT a host.
  - 2 attendees are included AND 1 is a host.

## Calculating the ZDX Score

The ZDX Score for Call Quality is calculated by one of the following methods:

- Mean Opinion Score
- Metric Thresholds

If metrics for latency, jitter, and packet loss are all available for calculation, the MOS is used to determine the ZDX Score. MOS uses a transmission rating factor (R-Factor) that generates range levels it derives from those particular metrics. The range levels help determine the ZDX Score for Call Quality as Poor, Okay, or Good.

The range levels for MOS are as follows:

- If the MOS is 0 to 3.6, the ZDX Score = Poor.
- If the MOS is greater than 3.6 but less than 4.34, the ZDX Score = Okay.
- If the MOS is 4.34 or greater, the ZDX Score = Good.

If only one or two of the metrics is available for calculation, metric thresholds are used to determine the ZDX Score.

The ZDX Score is rated Poor when metrics exceed their respective thresholds:

- If the latency range is above 500ms
- If jitter range is above 30ms
- If packet loss is above 0.1%

Ranges above and below the threshold are assigned a corresponding metric-based score to help define whether the ZDX Score is rated Poor or Good. The ZDX Score is calculated by using the lowest metric-based score of two metrics, or the lowest metric-based score of one metric. For example, if latency is 550ms with a corresponding metric-based score of 30, and jitter is 26ms with a corresponding metric-based score of 70, the ZDX Score is based on the lower metric-based score, which is 30.

If all three metrics for latency, jitter, and packet loss are unavailable, the ZDX Score for Call Quality cannot be calculated and data is not displayed.

[Image: Viewing the Zoom Call Quality page]

[Image: Example of Meeting Details page for Zoom Call Quality]

[Image: Meeting Monitoring Metrics graph]

[Image: Meeting Details for an Excluded Attendee]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/using-adaptive-mode","lastmod":"2026-06-25T21:06Z","nid":"1379636"} -->
## Using Adaptive Mode

- Source: https://help.zscaler.com/zdx/using-adaptive-mode
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Probes > Using Adaptive Mode
- Last modified: 2026-06-25T21:06Z
- Summary: This article contains information about the Adaptive mode in Cloud Path probe configuration.

Adaptive mode selects the best protocol for the Cloud Path probe to reach the destination. Adaptive mode tries the TCP, UDP, and ICMP protocols for each run, and picks the best available protocol for the probe. The best protocol for each leg in the Cloud Path is selected via an auto-discovery process and results in a combination of more than one protocol for the Cloud Path.

Minimum versions of Zscaler Client Connector and ZDX Module are required. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).

## Adaptive Mode Process

You can select Adaptive mode when configuring a Cloud Path probe for either a predefined or custom application. To learn more, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe) and [Best Practices in Operationalizing ZDX](https://www.zscaler.com/resources/white-papers/best-practices-operationalizing-zdx.pdf).

See image.

The following factors are considered for detecting the best protocol for each leg:

- Least latency to the destination (egress/server)
- Least loss to the destination (egress/server)

If Adaptive detection fails, ICMP is used for the leg to the egress router, and TCP is used for the path to the destination server.

## Results in the Cloud Path

The protocol used is visible in the Cloud Path section on the user details page. To learn more, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path).

[Image: Selecting Adaptive in Cloud Path probe configuration]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-and-managing-device-remediation","lastmod":"2026-06-23T14:40Z","nid":"1528756"} -->
## Viewing and Managing Device Remediation

- Source: https://help.zscaler.com/zdx/viewing-and-managing-device-remediation
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Remediation > Viewing and Managing Device Remediation
- Last modified: 2026-06-23T14:40Z
- Summary: Information about viewing and managing Device Remediation.

Device Remediation runs a script on selected devices as a job, and you can schedule the job as needed. Scripts are useful for remediating software issues by running PowerShell scripts on the selected devices. The Device Remediation page gathers parameter support and script output to categorize each job into the In Progress, Completed, or Scheduled tables for easy visibility and management. If a script fails, you can re-run the job or edit the script as required to run the job again.

## Prerequisites

Prior to running scripts on a device, you must have:

- The appropriate subscription level to support Remediation. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- The appropriate permission level to configure scripts. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- The minimum required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).

## Jobs Management

To manage the number of jobs on the Device Remediation page (Analytics > Digital Experience > Remediation > Device Remediation), you can:

- Start a new job.
- Edit a job.
- Delete a job.

## Device Remediation Tables

There are 3 Device Remediation tables based on the status of the job: In Progress, Completed, or Scheduled. These tables help you focus on which jobs need attention by providing granular details of the success rate or progress of each job.

For each table on the Device Remediation page (Analytics > Digital Experience > Remediation > Device Remediation), you can:

- View the In Progress, Completed, or Scheduled tables based on the job's progress.
- Go to the [Scripts page](https://help.zscaler.com/zdx/about-scripts) to view a collection of configured or predefined scripts.
- Start a new job.
- Search for jobs in the respective table.

### Job Status

Each job has a status at any point of time and can be one of the following:

| Status | Description |
| --- | --- |
| In Progress | The job is currently running the remote script on selected devices. |
| Scheduled | The job is scheduled to run a remote script on selected devices. |
| Completed - Aborted | The job was stopped while running a remote script. Jobs are capable of being stopped or aborted if the job has not entered its ending phase. |
| Completed | The job has successfully completed running the remote script on all selected devices. |

### In Progress Table

The In Progress table provides the following:

1. A list of jobs that are in progress to view the following information:
  - **Name**: The name of the job.
  - **Progress**: A progression bar to display the percentage of job completion.
  - **Script**: The name of the script.
  - **Devices**: The number of devices selected for the job.
  - **Created By**: The user that started the job.
  - **Started On**: The timestamp of when the job started.
2. Modify the columns displayed in the table.
3. Stop the job.

[Image: View the remote script runs that are in progress]

### Completed Table

The Completed table provides the following:

1. A list of jobs that are completed to view the following information:
  - **Name**: The name of the job. The status of the job is displayed if the script run has **Expired**or **Aborted**,
  - **Success Rate**: A percentage display bar to indicate how many of the jobs were completed over the number of selected devices.
  - **Script**: The name of the script.
  - **Devices**: The number of devices selected for the job.
  - **Created By**: The user that started the job.
  - **Started On**: The timestamp of when the job started.
  - **Ended On**: The timestamp of when the job ended.
2. Modify the columns displayed in the table.
3. View the job details where you can view the impacted devices, select and export a list of the impacted devices, or view the script output. See image.
4. Rerun the job.
5. Delete the job.

[Image: View a list of completed remote script runs]

### Scheduled Table

The Scheduled table provides the following:

1. A list of jobs that are completed to view the following information:
  - **Name**: The name of the job. Click the name to edit the job.
  - **Script**: The name of the script.
  - **Devices**: The number of devices selected for the job.
  - **Created By**: The user that started the job.
  - **Scheduled for**: The timestamp of when the job is scheduled to start.
2. Modify the columns displayed in the table.
3. Edit the job.
4. Delete the job.

[Image: View the scheduled remote script runs]

### Job Details

You can view a job's granular details by clicking a row on any of the tables. Each job includes:

- Under **Job Details**:
  - **Success Rate**: The percentage bar to indicate how many times the job was completed.
  - **Success**: The number of devices that have completed the job.
  - **Failed**: The number of devices that have failed to run the script. If the job is aborted, then ZDX tries its best effort to stop the script.
  - **Script**: The name of the script.
  - **Operating System**: The operating system the script is for.
  - **Devices**: The number of selected devices.
  - **Created By**: The user that started the job.
  - **Started On**: The timestamp of when the job started.
  - **Ended On**: The timestamp of when the job ended.
  - **Targeted**: The number of devices, users, user groups, locations, location groups, or departments that are targeted for the job. The targeted number is based on the job's selection.
- Under the **Devices** table, you can view which group of devices based on their job status (**All**, **Success**, or **Failed**). **All** is the default view of both **Success** and **Failed** jobs on selected devices. Each row has:
  - **Device**: The name of the device.
  - **User**: The name of the user.
  - **Execution Status**: The status of the job.
  - **Error Message**: A descriptive error message as to why the job failed.
  - **Result**: The result of the job.
  - **Script Output**: The script output when the job is completed successfully. You can click the **Script** icon ([Image: Click the Script icon to view the Script Output]) to view the entire script output or request logs.
  - **Last Updated On**: The timestamp of when the script was last updated to run on the device.

If the job failed to run the script on a device, you can view where the remote script did not run on inactive or unavailable devices by clicking **View Devices Where Script Did Not Run**. Click **Rerun Job** to run the job as a new job with prefilled criteria.

[Image: View the Job Details]

## Considerations & Limitations

Consider the following when configuring scripts:

- You can configure only up to the maximum number of scripts based on your subscription. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- You cannot select a device if it does not meet the version compatibility or OS. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
- When the user accepts a job and the script does not run due to an expired certificate or an invalid certificate, then you must upload the script again with a valid script certificate. To learn more, see [Preparing Custom Script Signing](https://help.zscaler.com/zdx/preparing-custom-script-signing).

1. Click **Start New Job**.
2. Under **Select Script**:
  1. **Script**: Search and select which script you want to run for the job. You can also [create a script](https://help.zscaler.com/zdx/managing-remote-scripts#add).
  2. **Script Details**: If you configured [script parameters or an end user notification](https://help.zscaler.com/zdx/managing-remote-scripts#fields), then you can modify the script parameters and preview the end user notification.
  3. **Script Run Settings**: Enter the number of seconds the script runs for the job.
3. Under **Select Devices**, click **Add** to open the **Select Devices** drawer. You cannot select a device if it does not meet the version compatibility or OS. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility). See image.
  1. **Criteria**: Select **Devices**, **Users**, **User Groups**, **Location**, **Location Groups**, or **Departments**.
  2. **Search**: You can search for the name of the selected criteria. Click the **Remove** icon to reset your search.
  3. Click **Done** to save your selection.
4. Under **Job Details**: See image.
  1. **Name**: Enter the name of the job.
  2. **Wait for devices to be online for a maximum of**: Enter the number of minutes or days a script must wait to run on a device.
  3. **Schedule Job for later**: Enable to select a date for when to run the script.
5. Click **Start**.

You can edit a job on the page if it's **Scheduled**.

1. Click the **Edit** icon on the job that you want to modify.
2. In the **Edit Job** window, modify the fields as needed. See image.
3. Click **Save**.

[Image: Modify the fields for the remote script run]

You can delete to cancel or abort a job on any table.

1. Click the **Delete** icon.
2. In the **Delete Remediation Job** window, click **Delete**. [Image: Confirm the remote script run deletion]

[Image: Select which devices to run the script on]

[Image: Start a remote script run]

[Image: View the job details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-device-events-reports","lastmod":"2026-07-15T13:20Z","nid":"1529314"} -->
## Viewing Device Events Reports

- Source: https://help.zscaler.com/zdx/viewing-device-events-reports
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Viewing Device Events Reports
- Last modified: 2026-07-15T13:20Z
- Summary: Provide details about viewing device events in the Zscaler Admin Console.

Device events are captured in the Zscaler Admin Console to provide aggregated insights into common system and software crashes that impact users and their devices. You can analyze system crashes and software crashes across your organization or drill down into the user details page to understand the specific device event's information. You can then plan your next course of action to remediate the crashes.

Go to Analytics > Digital Experience > Devices > Device Events to view the aggregated view of critical device events.

The System Crashes and Software Crashes reports are shown for the previous 30 days by default.

## Prerequisites

To view the Device Events reports, ensure:

- You have an Advanced subscription level for the Device Events reports. To learn more, see [Ranges & Limitations](https://help.zscaler.com/zdx/ranges-limitations).
- You're running the following on the monitored device: To learn more, see [Supported Versions & Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
  - The required minimum or later versions of Zscaler Client Connector and ZDX Module
  - The supported OS
- Your admin role is configured to view the Device Events reports. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## System Crashes Report

The System Crashes report displays impacted Windows devices that experience system crashes.

System crashes are categorized into:

- Blue Screens, commonly referred to as Blue Screen of Death (BSOD): System events that occur when the system reboots due to a stop code or bug check code.
- Unexpected Shutdown/Reboots: System events that occur when the system reboots without a stop code (e.g., long power button press).

In the System Crashes report, you see:

1. Filter tools to view System Crashes data:
  - Time range selection (**2 Hours** to **30 Days**)
  - Filters (**Users**, **Devices**, **Vendors**, **Models**, **Operating System**, **User Groups**, **Departments**, or **Geolocations**)
2. An overview of the total impacted users, devices, and system crashes for the selected time range.
  - **Total Users Affected**: The total number of users affected that experience software crashes and its percentage change.
  - **Total Devices Affected**: The total number of devices affected that experience software crashes and its percentage change.
  - **Total System Crashes**: The total number of system crashes across your organization and its percentage change.
3. View the supported versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
4. **Impacted Devices and Users**: Displays the number of impacted users and devices by date (i.e., the previous 30 days) when there is a system crash.
5. **System Crashes By Type**: Displays the number of Blue Screens and Unexpected Shutdowns/Reboots.
6. **System Crashes Tables**: Switch between views to review the following information:
  - **Impacted Operating System**: Displays Impacted OS, Blue Screens, Unexpected Shutdowns/Reboots, and Total Devices.
  - **Impacted Geolocations**: Displays Impacted Geolocations, Blue Screens, Unexpected Shutdowns/Reboots, and Total Devices.
  - **Impacted Vendors**: Displays Impacted Vendors, Blue Screens, Unexpected Shutdowns/Reboots, and Total Devices.
  - **Impacted Models**: Displays Impacted Models, Blue Screens, Unexpected Shutdowns/Reboots, and Total Devices.
7. **Device List**: Displays a list of impacted devices with the following information: On the Device list, you can:
  - **Device Name**: The name of the device.
  - **User**: The user associated with the device name.
  - **OS**: The operating system the device runs on.
  - **Vendor**: The vendor that manufactured the device.
  - **Model**: The device model.
  - **Blue Screen (BSOD)**: The number of Blue Screens.
  - **Unexpected Shutdowns/Reboots**: The number of unexpected shutdowns or reboots.
  - **Crashes Timeline**: View the Crashes Timeline to understand the types of crash events that occurred and their metrics details. Under **Time**, you can [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details) at the selected time, and then you can analyze granular information (e.g., Applications, CPU Utilization, Memory, Battery Level, Disk, Network Bandwidth, User Device Events). You can access the user details page from the previous 14 days. See image.
  - Download the **Device List** table by clicking the **Export** button.
  - Arrange the displayed columns with the **Table Options** button.
  - Sort the **Device List** based on the field (**Device Name**, **User**, **OS**, **Vendor**, **Model**, **Crashes**, **Hangs, Total Issues)**.
  - Navigate through pages of impacted devices.

See image.

## Software Crashes Report

The Software Crashes report displays impacted devices that experience software crashes or software hangs. You can view software crashes for both Windows and macOS devices, while software hangs are shown for Windows devices only.

In the Software Crashes report, you see:

- Filter tools to view Software Crashes data:
  - Filter by **Users**, **Devices**, **Software Crash Types**, **Vendors**, **Models**, **Operating System**, **User Groups**, **Departments**, or **Geolocations**.
  - Select a time range to display Software Crashes data.
  - Search for software by name.
- In the Software list, you see: On the Software list, you can:
  - **Name**: The name of the impacted software that experienced a software crash. Select a software to view its details.
  - **OS**: The operating system of the software.
  - **Publisher**: The publisher of the software.
  - **User Interface:** Whether there is a user interface impacted.
  - **Total Crashes**: The total number of software crashes.
  - **Total Hangs**: The total number of hangs.
  - **Impacted Devices**: The number of impacted devices.
  - Arrange the displayed columns with the **Table Options** button.
  - Sort the **Device List** based on the field (**Device Name**, **User**, **OS**, **Vendor**, **Model**, **Blue Screen (BSOD)**, **Unexpected Shutdowns/Reboots**).
  - Navigate through pages of impacted devices.

See image.

Software details are not displayed until a software is selected.

### Software Details

When you select software, you see:

1. **Software Name**: The name of the software. You can opt to select a different software to view by clicking **Expand to select different software** and then selecting a different software. See image.
2. **Software Overview**: An overview of the total impacted users, devices, and software crashes.
  - **Total Users Affected**: The total number of users affected that experience software crashes and its percentage change
  - **Total Devices Affected**: The total number of devices affected that experience software crashes and its percentage change.
  - **Total Software Crashes**: The total number of software crashes across your organization and its percentage change.
3. View the supported versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility).
4. **Impacted Devices and Users**: The number of impacted devices and users across the selected time range.
5. **Software Crashes By Type**: The number of software crashes across the selected time range.
6. **Software Crashes and Hangs Tables**: Switch between views to analyze where the most impacted software crashes and hangs are. Each selected view contains:
  - **Versions**: A list of impacted software versions.
  - **Geolocations**: A list of impacted geolocations.
  - **Vendors**: A list of impacted vendors.
  - **Models**: A list of impacted models.
  - **OS**: A list of impacted OSs.
  - **Software Crashes**: The number of software crashes.
  - **Software Hangs**: The number of software hangs associated.
  - **Total Devices**: The total number of impacted devices.
7. **Device List**: View the device list for the following information: On the Device list, you can:
  - **Device Name**: The name of the device.
  - **User**: The user associated with the device name.
  - **OS**: The operating system of the software.
  - **Vendor**: The vendor that manufactured the device.
  - **Model**: The device model.
  - **Crashes**: The total number of crashes the device is experiencing.
  - **Hangs**: The total number of software hangs the device is experiencing.
  - **Total Issues**: The total number of crashes and hangs.
  - **Issues Timeline**: View the Issues Timeline to understand the types of crash events that occurred and their metrics details. Under **Time**, you can [evaluate user details](https://help.zscaler.com/zdx/evaluating-user-details) at the selected time, and then you can analyze granular information (e.g., Applications, CPU Utilization, Memory, Battery Level, Disk, Network Bandwidth, User Device Events). You can access the user details page from the previous 14 days. See image.
  - Download the **Device List** table by clicking the **Export** button.
  - Arrange the displayed columns with the **Table Options** button.
  - Sort the **Device List** based on the field (**Device Name**, **User**, **OS**, **Vendor**, **Model**, **Blue Screen (BSOD)**, **Unexpected Shutdowns/Reboots**).
  - Navigate through pages of impacted devices.

See image.

## Caveat

ZDX collects data for single-user access per device. If there are multiple users on a device, then ZDX collects data for each user and the associated device. This scenario increases the device data aggregation.

For example:

- User A is on Device X.
- User B is also on Device X.
- User A and User B access Device X at different times.

In this scenario, ZDX collects two separate device data when:

- User A is on Device X.
- User B is on Device X.

[Image: View which devices are experiencing system crashes]

[Image: View the Crashes Timeline of a user's device]

[Image: Expand to select a different software]

[Image: View which software are experiencing crashes]

[Image: Select to view an individual software details on the crash]

[Image: View the Issues Timeline to view device event metrics]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-diagnostics-session-results","lastmod":"2025-04-18T09:24Z","nid":"1370176"} -->
## Viewing Diagnostics Session Results

- Source: https://help.zscaler.com/zdx/viewing-diagnostics-session-results
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Troubleshoot > Diagnostics > Viewing Diagnostics Session Results
- Last modified: 2025-04-18T09:24Z
- Summary: How to view and store Diagnostics session results in the ZDX Admin Portal.

Diagnostics sessions can provide much more granular information to analyze any issues that your users might be facing. After a session is run, you can see the results by clicking the View icon in the session row.

Data for In Progress sessions is updated every minute. Completed Diagnostics sessions show complete session data. All other sessions show partial session data. If the session status is Expired, then no data is seen in the session report.

Each configured diagnostics session has a [version compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#Diagnostics) that must be met to view the results.

## Diagnostics Session Results

To view Diagnostics session results:

- Click the name of the session or the **View** icon in the **In Progress** or the **History** table. This displays the session information in the same tab.
- Click the **Open in a New Tab** icon to open session results in a new tab. You can use this feature to compare multiple session results.

You can then click the **Export PDF** button to export and save a PDF file of the session information.

See image.

### Deep Tracing

Deep tracing provides an overview of a user, device, or application connecting to a network with the following:

- General Diagnostic
- Application
- Device

The Diagnostics session results display information based on options chosen during configuration (e.g., application or device).

See image.

- **Name of the User**: The name and email address of the user.
- **Session Type**: The type of session selected during configuration.
- **Status**: This lists the status of the session. To learn more, see [Understanding the Diagnostics Status](https://help.zscaler.com/zdx/understanding-diagnostics-session-status).
- **Application**: The application the session was run on. If you did not choose an application, this field is empty.
- **Device**: The selected device. If you disabled **Device Probing**, nothing is listed. To learn more, see [Starting a New Diagnostics Session](https://help.zscaler.com/zdx/starting-new-diagnostics-session).
- **End time**: This shows the date and time the session ended.

- **Web Probe Metrics**: This section shows data for the Page Fetch Time, Server Response Time, DNS Resolve Time, and Availability. The name of the probe is also listed in parentheses. If you had not selected a Web probe while configuring a session, this section is not displayed. The start and end times of the graphs align with the times for the Diagnostics session.
- **Cloud Path**: This section displays a graph where you can choose to view either Latency or Packet Loss from the drop-down menu. It shows the Hop View and the Command Line View of the path taken by the probe. You can click the **Zoom** icon to view details of the Cloud Path till the probe reached its destination. If you did not select a Cloud Path probe while configuring a session, this section is not displayed. The Cloud Path also displays details such as tunnel information and differential latency. To learn more, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path). If you select a point on a graph, a tooltip with the appropriate metric, the date and time are displayed in all the charts. If there is no data for two consecutive time points, a gap in the charts is displayed. An error message in the chart indicates the issue.

Network applications do not have a web front end, and therefore, Web Probe Metrics are not available. ZDX Scores are calculated using latency with only a single Cloud Path probe. To learn more, see [About Applications](https://help.zscaler.com/zdx/about-applications) and [Monitoring the Applications Overview](https://help.zscaler.com/zdx/monitoring-applications-overview).

- **Device Health**: Displays graphs for CPU Usage, Wi-Fi Signal, Memory, Battery Level, Disk information, and Network information are displayed. Not all Device Health metrics are preselected; in the drop-down menu, you can select the elements you want to review. If you chose to disable the Device Probing while configuring the session, this section is not listed.
- **Top Processes**: Lists the top processes that are consuming resources by Memory Usage, CPU Usage, Disk Usage, and Network Usage, with the Process ID in parentheses. If the Device Probing option is not selected during configuration, this section is not listed.
- **User Device Events**: Shows the events for a device for the selected period of time. If the Device Probing option is disabled during configuration, this section is not listed.

### Hi-Fi Cloud Path

A High Fidelity (Hi-Fi) Cloud path displays Cloud Path network connectivity or latency details where a Cloud Path sends a high number of packets.

Each Hi-Fi Cloud Path shows the following:

- General Information
- Overview
- Cloud Path

See image.

- **Name of the User**: The name and email address of the user.
- **Session Type**: The type of session selected during configuration.
- **Status**: This lists the status of the session. To learn more, see [Understanding the Diagnostics Status](https://help.zscaler.com/zdx/understanding-diagnostics-session-status).
- **Device**: The selected device name.

- Under **Data Center Details**:
  - **Data Center Name**: The name of the Zscaler data center.
  - **Location**: The location of the data center.
  - **Proxy Hostname**: The proxy hostname of the Zscaler data center.
  - **IP**: The IP address of the Zscaler data center.
  - **Cloud Name**: The ZDX cloud name. To learn more, see [What Is My Cloud Name for ZDX?](https://help.zscaler.com/zdx/what-my-cloud-name-zdx)
- Under **User Details**:
  - **User Name**: The name of the user.
  - **Location**: The location of the user device.
  - **IP**: The IP address of the user device.
  - **ISP**: The internet service provider (ISP) the user device is connected to.

You can also view a map of where the impacted device and data center are. Click to zoom in or out with the map buttons or use your mouse scroll.

This section displays a graph where you can choose to view either Latency or Packet Loss from the drop-down menu. It shows the Hop View and the Command Line View of the path taken by the probe. You can click the **Zoom** icon to view details of the Cloud Path till the probe reached its destination. If you did not select a Cloud Path probe while configuring a session, this section is not displayed. The Cloud Path also displays details such as tunnel information and differential latency. To learn more, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path).

If you select a point on a graph, a tooltip with the appropriate metric, the date and time are displayed in all the charts. If there is no data for two consecutive time points, a gap in the charts is displayed. An error message in the chart indicates the issue.

[Image: View Hi-Fi Cloud Path results]

### Bandwidth Test

A bandwidth test can provide information on a device's network connectivity or latency details.

Each bandwidth test displays the following:

- General Information
- Overview
- Results
- Basic Diagnostics
- Cloud Path

See image.

- **Name of the User**: The name and email address of the user.
- **Session Type**: The type of session selected during configuration.
- **Status**: This lists the status of the session. To learn more, see [Understanding the Diagnostics Status](https://help.zscaler.com/zdx/understanding-diagnostics-session-status).
- **Device**: The selected device name.

- Under **Data Center Details**:
  - **Data Center Name**: The name of the Zscaler data center.
  - **Location**: The location of the data center.
  - **Proxy Hostname**: The proxy hostname of the Zscaler data center.
  - **IP**: The IP address of the Zscaler data center.
  - **Cloud Name**: The ZDX cloud name. To learn more, see [What Is My Cloud Name for ZDX?](https://help.zscaler.com/zdx/what-my-cloud-name-zdx)
- Under **User Details**:
  - **User Name**: The name of the user.
  - **Location**: The location of the user device.
  - **IP**: The IP address of the user device.
  - **ISP**: The internet service provider (ISP) the user device is connected to.

You can also view a map of where the impacted device and data center are. Click to zoom in or out with the map buttons or use your mouse scroll.

Depending on how the device is connected to a network, there are different bandwidth results displayed.

- If the device is connected via Z-Tunnel 1.0 or Z-Tunnel 2.0, then the download and upload bandwidth speeds are displayed for those tunnels.
- If the device is connected directly to the network with Internet Protocol Security (IPSec) and Generic Routing Ecapsulation (GRE) tunnels, then the Destination Network Address Translation (DNAT) download and upload bandwidth speeds are displayed. See image. To capture DNAT information, the minimum required ZDX Module version is required. To learn more, see [Supported Versions & Feature Capability](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#Diagnostics).

- **Fragmentation Test**: Indicates whether fragmentation is supported.
- **UDP Connectivity**: The latency of the UDP connection.
- **DNS Connectivity**: The latency of the DNS connection.
- **ICMP**: The latency and jitter metrics of the ICMP.

This section displays a graph where you can choose to view either Latency or Packet Loss from the drop-down menu. It shows the Hop View and the Command Line View of the path taken by the probe. You can click the **Zoom** icon to view details of the Cloud Path till the probe reached its destination. If you did not select a Cloud Path probe while configuring a session, this section is not displayed. The Cloud Path also displays details such as tunnel information and differential latency. To learn more, see [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path).

If you select a point on a graph, a tooltip with the appropriate metric, the date and time are displayed in all the charts. If there is no data for two consecutive time points, a gap in the charts is displayed. An error message in the chart indicates the issue.

[Image: View bandwidth test when device is connected via Z-Tunnel 1.0]

[Image: View bandwidth test DNAT information if device is connected directly]

## Saving the Session Report

You can access session information by clicking the **View** icon in the session row in the Diagnostics page. For information on data retention limits, see [Ranges and Limitations](https://help.zscaler.com/zdx/ranges-limitations).

You can also choose to store the Diagnostics session report. Click the **Export PDF** button to convert the report into PDF format. You can then save the PDF report for future reference.

If you have View Only permission for Diagnostics details, you cannot use the Export PDF feature to save the results. To learn more, see [About Diagnostics](https://help.zscaler.com/zdx/about-diagnostics).

[Image: Viewing Deep Tracing session results]

[Image: Export PDF functionality for Deep Tracing]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-ipv6-configurations","lastmod":"2026-04-20T18:34Z","nid":"1528983"} -->
## Viewing IPv6 Configurations

- Source: https://help.zscaler.com/zdx/viewing-ipv6-configurations
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Viewing IPv6 Configurations
- Last modified: 2026-04-20T18:34Z
- Summary: Information on how to view IPv6 details for ZDX.

ZDX supports IPv6 configurations for secure and reliable access to applications. You can view dual-stack (IPv4 and IPv6) or IPv6-only configurations. ZDX relies on Internet & SaaS to route and inspect IPv6 traffic for telemetry collection and for clients inside a location. ZDX relies on Zscaler Client Connector to forward and detect IPv6 traffic for clients outside a location.

To learn more, see [Understanding IPv6 Support](https://help.zscaler.com/zia/understanding-ipv6-support).

## Prerequisites

To begin viewing IPv6 details, you must:

- Enable IPv6 for Internet & SaaS. To learn more, see [Configuring IPv6 Settings](https://help.zscaler.com/zia/configuring-ipv6-settings#enable-ipv6).
- Enable IPv6 for Zscaler Client Connector. To learn more, see [Enable IPv6 Resolution for Zscaler Domains](https://help.zscaler.com/zscaler-client-connector/enabling-ipv6-resolution-zscaler-domains)

## Viewing IPv6 Configurations for ZDX

To view IPv6 configurations, see:

- [Evaluating the Cloud Path](https://help.zscaler.com/zdx/evaluating-cloud-path)
- [Evaluating User Device Details](https://help.zscaler.com/zdx/evaluating-user-details)
- [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe)
- [Configuring Zscaler Hosted Probes](https://help.zscaler.com/zdx/configuring-zscaler-hosted-probes)
- [Managing Top Private Applications](https://help.zscaler.com/zdx/managing-top-private-applications)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-predefined-reports","lastmod":"2026-05-15T07:06Z","nid":"1456566"} -->
## Viewing Predefined Reports

- Source: https://help.zscaler.com/zdx/viewing-predefined-reports
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Viewing Predefined Reports
- Last modified: 2026-05-15T07:06Z
- Summary: Provides details about predefined reports, available in the Zscaler Admin Console.

Predefined reports allow you to view user data across your organization that can reveal distinctive patterns among various metrics and applications. Details for each report are aggregated day-to-day and most reports are captured in a rolling 14-day cycle.

## Prerequisites

To access and view system-generated reports, ensure:

- Your ZDX subscription level supports viewing system-generated reports. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- Your admin role is configured to view system-generated reports. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).

## Accessing Reports

To access the reports:

1. Go to **Analytics**>**Digital Experience** > **Reports** > **Predefined Reports**.
2. Select the report name or click the **View**icon to access a report.

Use the search field to find a specific report. Search for any character string that might be part of the report name.

The report table provides the following information:

- **Name**: The name of the system-generated report.
- **Type**: Indicates the report is predefined.
- **Application**: Indicates either a particular application or all applications.
- **Data Source**: Specifies which data is captured (**All Cloud Path**, **All Web Probes**, **All Devices**).
- **Metrics**: Identifies the type of metrics reflected in the report.
- **Last Updated On**: Indicates the date and time when the report was revised.

[Image: View a list of predefined system-generated reports]

## Viewing Report Details

View details for each of the following system-generated reports:

- Cloud Path: End-to-End
- Last Mile ISP Performance
- DNS Performance
- Application Performance
- ZDX Score by Application
- Wi-Fi Distribution
- Active Users by Zscaler Destination
- Top Users with Poor ZDX Scores
- Probe Assignments
- User Location

The Cloud Path: End-to-End report captures the average latency in the Cloud Path within a 14-day time range.

Filter by **Departments**, **Zscaler Locations**, **Geolocations**, or **Applications**. You can hide the filters or reset your selection.

Similar to other system-generated reports, you can view a more granular time range:

1. Select a point in the graph. A tooltip displays the latency metrics for the selected locations.
2. Drag your mouse from left-to-right to capture your time range. The graph automatically adjusts to show the latency within the new timeline.
3. Click **Zoom Out** to reset the graph back to the original view.

Up to 20 device locations are displayed at a time within the legend on the right side of the graph, based on user device information. Each location is color-coded to correspond to its trend line within the graph, with the number of devices per location included in parentheses. A minimum of one location is always displayed. The P95 line is calculated based on the latency of the selected locations.

[Image: Example report for end-to-end latency in the Cloud Path]

The Last Mile ISP Performance report captures ISP latency within a 14-day time range.

Filter by **Departments**, **Zscaler Locations**, or **Geolocations**. You can hide the filters or reset your selection.

Similar to other system-generated reports, you can view a more granular time range:

1. Select a point in the graph. A tooltip displays the latency metrics for the selected ISPs.
2. Drag your mouse from left-to-right to capture your time range. The graph automatically adjusts to show the latency within the new timeline.
3. Click **Zoom Out** to reset the graph back to the original view.

Up to 20 ISPs are displayed at a time within the legend on the right side of the graph, based on the ISPs to which your users are connecting. Each ISP is color-coded to correspond to its trend line within the graph. A minimum of one ISP is always displayed.

[Image: Example report for ISP latency]

The DNS Performance report captures the average DNS latency within a 14-day time range.

Filter by **Departments**, **Zscaler Locations**, **Geolocations**, or **Applications**. You can hide the filters or reset your selection.

Similar to other system-generated reports, you can view a more granular time range:

1. Select a point in the graph. A tooltip displays the latency metrics for the selected locations.
2. Drag your mouse from left-to-right to capture your time range. The graph automatically adjusts to show the latency within the new timeline.
3. Click **Zoom Out** to reset the graph back to the original view.

Up to 20 device locations are displayed at a time within the legend on the right side of the graph, based on user device information. Each location is color-coded to correspond to its trend line within the graph, with the number of devices per location included in parentheses. A minimum of one location is always displayed.

[Image: Example report for average DNS latency]

The Application Performance report captures the daily distribution of ZDX Scores within a 14-day time range.

Filter by **Departments**, **Zscaler Locations**, **Geolocations**, or **Applications**. You can hide the filters or reset your selection.

On hover, the graph shows the count and percentage of users with a corresponding ZDX Score level for the selected application. To learn more about the range of ZDX Scores, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).

[Image: Example report for Application Performance]

The ZDX Score by Application report captures the average ZDX Score per application within a 14-day time range.

Filter by **Departments**, **Zscaler Locations**, or **Geolocations**. You can hide the filters or reset your selection.

Similar to other system-generated reports, you can view a more granular time range:

1. Select a point in the graph. A tooltip displays the ZDX Scores for all active applications selected on the right-side of the graph.
2. Drag your mouse from left-to-right to capture your time range. The graph automatically adjusts to show the latency within the new timeline.
3. Click **Zoom Out** to reset the graph back to the original view.

Up to 20 applications are displayed at one time on the right side of the graph, based on available applications. Each application is color-coded to correspond to its trend line within the graph. A minimum of one application is always displayed. To learn more about the range of ZDX Scores, see [About the ZDX Score](https://help.zscaler.com/zdx/about-zdx-score).

[Image: Report for ZDX Score by Application]

The Wi-Fi Distribution report captures the daily distribution of ZDX Scores within a 14-day time range.

Filter by **Departments**, **Zscaler Locations**, or **Geolocations**. You can hide the filters or reset your selection.

On hover, the graph shows the device count per Wi-Fi band and its percentage from the total number of user devices across your organization.

[Image: Report that shows the device distribution per Wi-Fi band]

The Active Users by Zscaler Destination report captures the current distribution of active users and devices per Zscaler destination.

Filter by **Departments**, **Zscaler Locations**, or **Geolocations**. You can hide the filters or reset your selection.

On hover, the graph shows the user and device counts within a 2-hour time range.

[Image: Example report for Active Users by Zscaler Destination]

Switch views between the graph or list view. You can view up to 100 destinations within the table.

[Image: Example report for Active Users by Zscaler Destination in table format]

The Top Users with Poor ZDX Scores report captures the top 100 users within a 14-day time range who have the lowest Poor ZDX Scores, based on the selected application.

Filter by **Departments**, **Zscaler Locations**, **Geolocations** or **Applications**. You can hide the filters or reset your selection.

The report provides the following information:

- **User**: Click the user to view details. To learn more, see [Evaluating User Details](https://help.zscaler.com/zdx/evaluating-user-details).
- **ZDX Score**: The value of the user's Poor ZDX Score.
- **Zscaler Location**: The location of the user. To learn more, see [About Locations](https://help.zscaler.com/zdx/about-locations).
- **Geolocations**: The geographic area where the user is located.
- **Device**: The user device.
- **# Probe Runs**: The number of times probes were run for the selected application within the 14-day timeline.

[Image: View the top users with poor ZDX Scores]

The Probe Assignments report captures the number of enabled probes assigned to a given user in a 24-hour time range based on probe usage.

To access Probe Assignments, ensure you have the [appropriate permission](https://help.zscaler.com/zdx/adding-zdx-roles#ProbeAssignments).

The report provides the following:

- Filter by **User Name**, **User Groups**, **Location Groups**, **Departments**, **Zscaler Locations**, or **Geolocations**. You can hide the filters or reset your selection.
- In the summary section, you can view:
  - **Total Enabled Probes**: The total number of enabled probes with an active application.
  - **Total User**: The total number of impacted users assigned to enabled probes. You can also view the percentage of impacted users and a trend chart of total users.
  - **Probe Usage**: The number of devices assigned to a number of enabled probes. You can click one of the sections of the bar to filter for the report. The probe ranges are **0-10**, **11-20**, **21-25**,or **>25**. The default filter is **>25**.
- In the **Users** list, you can:
  - Download a CSV file of impacted users.
  - Search for an impacted user.
  - View a list of impacted users with the following details:
    - **User Name**: The impacted username.
    - **Department**: The department associated with the username. To learn more, see [About Departments](https://help.zscaler.com/zdx/about-departments).
    - **Zscaler Location**: The location of the impacted user. To learn more, see [About Locations](https://help.zscaler.com/zdx/about-locations).
    - **Geolocation**: The geographical location of the impacted user.
    - **Device Count**: The number of impacted devices.
    - **Active Probes**: The number of active probes assigned to the impacted user.

If the report query finds more than 1,000 impacted users, then the report displays the first 1,000 users. Use filters to narrow your results or download a CSV file to view all impacted users.

[Image: View Probe Assignments report to identify number probes assigned to a user]

The User Location report provides an overview of your user devices' geolocations and granular information about the user device's geolocation in Location History.

The report provides the following information:

- Filter by **Geolocations**, **Zscaler Locations**, **User Groups**, **Departments**, **Location Groups**, **Last Mile ISPs**, **Operating System**, or **Users**. Click **Reset** to go back to the default filters.
- View the **Device Locations** map for an overview of your user devices' geolocation. You can click a geolocation to view the **Total Users** and **Total Devices**. Optionally, you can filter to that location to focus on those users and devices on the list. You can click to **Zoom In** or **Zoom Out**. Click **Reset** on the map to go back to your default view of the map.
- Search for specific devices.
- View a list with the following information: Click **Location History** to view the history of the location for that user. See image.
  - **User Name**: The user name. You can click the user name to view [user details](https://help.zscaler.com/zdx/evaluating-user-details).
  - **Device**: The device name.
  - **Geo Location**: The geolocation of the user's device.
  - **Latitude / Longitude**: The latitude and longitude of the geolocation.
  - **Source**: The geolocation's source information.

The report displays up to 5,000 devices. You can use the filters to reduce the amount of displayed devices.

[Image: View the User Location Report to see where your users are located]

[Image: View the Location History for the user]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-process-inventory","lastmod":"2026-05-22T07:06Z","nid":"1440651"} -->
## Viewing Process Inventory

- Source: https://help.zscaler.com/zdx/viewing-process-inventory
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Inventory > Viewing Process Inventory
- Last modified: 2026-05-22T07:06Z
- Summary: Information about viewing process statistics for a user's device.

Process Inventory allows you to monitor processes that might be impacting the behavior of your users' devices. Process calculations are updated in one-minute rolling intervals, and the top processes are displayed every five minutes.

## Prerequisites

To view the processes, ensure:

- You're running the minimum required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#ProcessInventory).
- Your ZDX subscription level supports Process Inventory. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- You've set a CPU usage threshold for devices in your organization. To learn more, see [Configuring Inventory Settings](https://help.zscaler.com/zdx/configuring-inventory-settings).

## Viewing the Process Overview

To access the Process Overview page, go to **Analytics** > **Digital Experience** > **Devices** > **Process Overview**. Use the time range filter and page filters to help narrow the scope of CPU incidents based on your CPU usage threshold setting:

- **Device Vendors**: The names of supported device vendors.
- **Departments**: The names of your organization's departments.
- **Zscaler Locations**: The list of locations where a user accessed a device. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
- **Geolocations**: The geographic areas where users accessed their devices.

Hover over the bar charts to view the OS distribution and CPU cores for devices:

- **OS Distribution**: The number of devices per operating system across your organization.
- **Number of CPU Cores**: The number of CPU cores with associated devices in your organization.

[Image: Bar charts for OS distribution and CPU cores]

### Viewing Top Processes By Incidents

View the top processes for CPU usage incidents based on the threshold percentage you've defined in [Configuring Inventory Settings](https://help.zscaler.com/zdx/configuring-inventory-settings). These processes have exceeded the threshold within a 5-minute interval, and are color-coded according to number of incidents, as indicated in the legend. The darker the color, the more incidents that have occurred. The size of each tile visually indicates the number of impacted devices relative to other tiles. For example, the following image shows that Microsoft.Flow.RPA.LogShipper.exe has more incidents that have exceeded the CPU usage threshold than other processes.

See image.

To view details about a specific process:

1. Hover over a process tile. A tooltip displays the total number of incidents impacting the number of devices for that process.
2. Select the tile or click **View Process Details**. See image. The process details page is displayed.

[Image: Color tree of processes by number of CPU incidents]

## Viewing Process Inventory

To access the Process Inventory page, go to **Analytics** > **Digital Experience** > **Devices** > **Process Inventory**. Use the time range filter and page filters to help narrow your scope for the top processes based on your threshold setting to trigger CPU incidents. The page provides a current snapshot of CPU usage that has exceeded the threshold on user devices. The table on the Process Inventory page provides the following information:

1. **Name**: The name of the process.
2. **Devices**: The number of devices impacted.
3. **CPU Incidents**: The number of incidents that have exceeded your configured CPU threshold.
4. **Avg Memory**: The average percentage of RAM used for the process.

[Image: Table that shows the Top Processes by CPU Incidents]

You can click the **Modify Table** icon to select, deselect, or rearrange the table columns. To view details about a specific process, select a process name within the table. The details page for that process is displayed.

### Viewing Process Details

The process details page provides a graph that shows the CPU usage percentage in conjunction with the number of associated user devices. CPU usage is indicated by the shading of color based on the percentages shown in the graph legend.

[Image: Example of CPU Usage Over Time by Devices]

The process details table provides the following information per device:

- **Device**: The user's device type.
- **User**: The name of the device user.
- **CPU Incidents:**The number of incidents that have exceeded your configured CPU threshold.
- **Avg Memory**: The average percentage of RAM used for the process.
- **Disk Read**: The number of bytes read from the disk.
- **Disk Write**: The number of bytes written from the disk.
- **Network Upload**: The number of bytes uploaded by the device.
- **OS**: The operating system running on the device.
- **Device Vendor**: The device vendor's name.

[Image: Table that shows process details per device]

To view details about the device user and device performance, select the user name in the table.

### Viewing User Details

By default, the user details page provides **Probe Analytics** to display web metrics and the Cloud Path graphs for the selected application:

[Image: Device performance details]

Click **Device Performance** to view Device Health metrics for the selected device. If more than one process is shown for the device, you can select a process to view its percentage of CPU usage within your set time range.

[Image: Select device process to view CPU usage]

[Image: Accessing process details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-quarterly-business-review-reports","lastmod":"2024-03-17T07:06Z","nid":"1458626"} -->
## Viewing Quarterly Business Review Reports

- Source: https://help.zscaler.com/zdx/viewing-quarterly-business-review-reports
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Viewing Quarterly Business Review Reports
- Last modified: 2024-03-17T07:06Z
- Summary: Descriptions of individual sections within a ZDX Quarterly Business Review report.

html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd"

Quarterly Business Review (QBR) reports are available for download to ZDX users of all subscription levels. The reports can help provide insight into emerging traffic trends and the types of threats that Zscaler is blocking to protect your network. You can access the reports from the **Analytics**icon in the left-side navigation of the ZDX Admin Portal. To learn more, see [Downloading Quarterly Business Review Reports](https://help.zscaler.com/zdx/downloading-quarterly-business-review-reports).

You must have a minimum of 100 active devices for 30 days to generate and view QBR reports.

## Viewing the QBR Reports

Each report provides the following information:

- Quarterly Snapshot
- Where Are Your End Users
- Incidents Overview
- Network Incidents Overview
- Top ISPs by Users
- Application Incident Overview
- End-to-End ZDX Score
- Average DNS Resolution Time
- Average Latency from Source Network
- Alerts Overview
- Wi-Fi Analysis
- Device Analysis
- License Comparison

Shows a quarterly snapshot of your Application ZDX Score, Network Average Latency, and Users Monitored. To determine the score for an application, Zscaler identifies all the users that accessed the application for the calendar quarter, and then finds the lowest value each user experienced for the application. The average value is used to determine the application's ZDX Score for the quarterly snapshot.

Shows a geospatial distribution of each user's footprint around the world by visualizing where your users are located.

Shows a monthly overview of your incidents compared to the previous 6 months. An incident is an event that disrupts operational processes and isn't part of normal operations. For example, an incident occurs when there is an impact involving more than a normal or acceptable percentage of users in a department or region for one or more services. An incident might also involve the failure of a feature or service that should have been delivered.

Shows a monthly overview of your network incidents by network type and Internet Service Provider (ISP), compared to the previous 6 months.

Shows a monthly performance overview of your top ISPs by your users. Performance is calculated by the average time taken by the Egress router at the user’s location to reach the ISP.

Shows a monthly overview of your application incidents by application type, compared to the previous 6 months.

Shows a monthly overview of your application-specific ZDX Scores compared to the previous 6 months. To determine the score for an application, Zscaler identifies all the users that accessed the application for the calendar quarter, and then finds the lowest value each user experienced for the application. The lowest values for each user are added together and divided by the number of users. This is the application's ZDX Score for the end-to-end application experience.

Shows a monthly overview and your average DNS resolution time by location, compared to the previous 6 months. Any metric under 100ms is considered acceptable.

Shows a monthly overview of the average latency from your source network to Zscaler, by geolocation and user distribution, compared to the previous 6 months.

Shows a monthly overview and trend of alerts triggered, along with the average remediation time for an alert. If you have a high level of incidents, enable alerting in ZDX to help with monitoring and triage.

Shows device counts for the quarter that reflect Wi-Fi connectivity versus wired connectivity.

Shows monthly device counts per operating systems, event names, and models across the organization.

Shows a matrix that delineates ZDX feature support for Standard, M365, Advanced, and Advanced Plus subscriptions.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-self-service-user-notifications","lastmod":"2023-10-19T09:00Z","nid":"1463121"} -->
## Viewing Self Service User Notifications

- Source: https://help.zscaler.com/zdx/viewing-self-service-user-notifications
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Viewing Self Service User Notifications
- Last modified: 2023-10-19T09:00Z
- Summary: Information about user notifications displayed for ZDX Self Service.

This article consolidates the Zscaler Digital Experience (ZDX) Self Service user notifications that can help identify the root cause of device and network issues, allowing users to investigate potential solutions without the need to contact customer support. Each notification contains a brief description, diagnosis, and recommendation for issues that have been detected and might need attention.

See image.

To learn more about configuring the notifications for users, see [Configuring Self Service Settings](https://help.zscaler.com/zdx/configuring-self-service-settings). To learn more about notification data and its correlation with users, see [Monitoring the Self Service Dashboard](https://help.zscaler.com/zdx/monitoring-self-service-dashboard).

| Issue | Notification | Description | Diagnosis | Recommendation |
| --- | --- | --- | --- | --- |
| High CPU | CPU is running around <value> and might reduce performance. | CPU is running at <value> for a sustained period of time and might affect the performance of your applications. | Top processes affecting performance: <list of processes> | Close applications contributing to high CPU usage. Alternatively, go to the Activity Monitor or Task Manager to end the processes. |
| Degraded Wi-Fi | Caused by suboptimal band or the Wi-Fi access point is far from your location. | The current Wi-Fi network <home Wi-Fi> signal is weak. | Latency to the access point <Basic Service Set Identifier> is high.; Signal strength at <value> is low.; Bandwidth at <value> is low. | Move closer to the access point or adjust the antenna of your access point to get a better signal.; If you have administrative access to the access point, configure a different channel, or set auto-channel if your access point supports it.; Check for other radio sources that might create noise and interference (e.g., a microwave, cordless phone, or poorly-shielded satellite dishes or cables). Also check wireless devices, such as a garage door opener, speaker, baby or camera monitor, and LCD displays running at 2.4 GHz.; Verify that your access point is installed away from power lines or breaker rooms. |
| Suboptimal Wi-Fi Access Point | Weak signal detected for the connected Wi-Fi access point with SSID <value>. | The connected Wi-Fi access point <home basement> is suboptimal. | Latency to the access point <Basic Service Set Identifier> is high.; Signal strength at <value> is low.; Bandwidth at <value> is low. | Connect to a better access point in your SSID network.; Review your mesh network setup.; Turn your Wi-Fi off and on so the device reconnects to the access point with the strongest signal. <list from history home living room> |
| Suboptimal Wi-Fi Band | Connecting to 802.11ac provides better performance. | The connected SSID <ATT-4G> is suboptimal. Better band connectivity is available. | Latency to the access point <Basic Service Set Identifier> is high.; Physical mode is 802.11n.; Signal strength at <value> is low.; Bandwidth at <value> is low. | Connect to the SSID on the 5 GHz band.  <list from history> |

[Image: Example Self-Service IT User Notification]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-software-inventory","lastmod":"2026-06-26T16:17Z","nid":"1391206"} -->
## Viewing Software Inventory

- Source: https://help.zscaler.com/zdx/viewing-software-inventory
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Inventory > Viewing Software Inventory
- Last modified: 2026-06-26T16:17Z
- Summary: Information about viewing the history of software versions and updates for a user's device.

[Watch a video about Viewing Software Inventory in ZDX.](https://fast.wistia.net/embed/iframe/yrkhm4ia2y)

Software Inventory allows you to view current and historical information about software versions and updates on your users' devices.

## Prerequisites

To view Software Inventory data, ensure:

- You're running the minimum required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#SoftwareAndDeviceInventory).
- Your ZDX subscription level supports Software Inventory. To learn more, see [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations#digital-experience).
- You've enabled the setting for inventory data collection. To learn more, see [Configuring Inventory Settings](https://help.zscaler.com/zdx/configuring-inventory-settings).

Availability of Software Inventory data might take up to one day after Data Collection is enabled.

## Viewing the Software Overview

You can access the Software Inventory page by doing either of the following:

- Go to **Analytics**> **Digital Experience** > **Devices** > **Software Overview**. Use the filters to help you find specific software or applications, and select the associated color-coded tile.
- Go to **Analytics**>**Digital Experience** > **Devices** > **Software Inventory**. Use the filters to help you find specific software or applications listed within the table.

Use the filters on the Software Overview page to narrow your scope of inventory information:

- **Software App Groups**: Supported vendor software or individual applications, grouped by name.
- **Vendors**: Software or application vendors.
- **Zscaler Locations**: The list of locations where a user accessed a device. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
- **Geolocations**: The geographic areas where users accessed their devices.

View the current count for installed software, vendors, users, and devices from the day Software Inventory was enabled until the start of the UTC day. The percentage in each card indicates how the count has increased or decreased over the past 24 hours.

[Image: Software Inventory Overview]

### Select the Software or Application

Installed software and applications are grouped and color-coded according to their vendors, with the size of each tile visually indicating the number of installations per software or application relative to other software or applications installed on user devices. For example, the following image shows the grouping of vendors, with Microsoft Teams and Microsoft Visual C++ as having the most installations among Microsoft Corporation applications:

[Image: Software Inventory vendor grouping]

To view details about a specific application or software:

1. Hover over the software or application tile within the vendor group. A tooltip displays the total number of installations for the software or application, as well as the total number of version names for the software or application.
2. Select the tile. [Image: Example of tile tooltip on Software Overview page]
3. One of the following pages is displayed:
  - If the software or application has more than one version name, the version names are displayed within a table on the Software Inventory page.
  - If the software or application does not have more than one version name, the software details page is displayed.

## Viewing Software Inventory

The Software Inventory page provides a snapshot of your users' installed applications and software. Use the filters at the top of the page to narrow your search for a specific application or software. The table on the Software Inventory page provides the following information:

- **Name**: The software or application version name.
- **Version:**The numbered version of the software or application.
- **Software Group**: The group name to which the software or application belongs.
- **OS**: The operating system on which the software or application is run.
- **Users**: The number of software or application users.
- **Devices**: The number of hardware devices on which the software or application is running.
- **End of Life**: The date when the installed software is no longer supported or maintained by the vendor.
- **End of Support**: The date when the installed software no longer receives updates, patches, or technical assistance from the vendor.
- **Vendor**: The software or application provider.

[Image: Example of Software Inventory Table]

The following actions are available within the software inventory table:

- To view details about a specific version of the software or application, select its name within the table. The software details page is displayed.
- To capture all filtered installations within the table in CSV format, click **Export**, then click the **Download**icon. The download process captures all known installations every 24 hours.

### View Software Details

The software details page provides granular information about a specific version of an application or software:

- **Users**: A percentage indicates how the user count for the selected software or application has increased or decreased over the past 24 hours.
- **OS Distribution**: On hover, the bar chart shows the operating systems and number of users per operating system for the selected software or application, delineated by color.
- **Version Distribution**: On hover, the bar chart shows the selected software or application versions and number of users per version, delineated by color.

See image.

The software details table provides the following information for each software or application version:

- **Device**: The hardware device on which the software or application is running.
- **User**: The name of the user running the software or application. To learn more, see View User Details.
- **Software Version**: The numbered version of the software or application.
- **OS**: The operating system on which the software or application is run.
- **Location**: The folder on the user's system where the software or application is found.
- **Install Date**: The date when the software or application was originally installed.
- **End of Life**: The date when the installed software is no longer supported or maintained by the vendor.
- **End of Support**: The date when the installed software no longer receives updates, patches, or technical assistance from the vendor.
- **Version History**: Click the **View**icon to display the date when the software or application was last updated.

See image.

To view user details about the software or application, select the user name in the table.

### View User Details

The user details page provides granular information about the applications or software installed for a specific user. The **Vendor Distribution** bar chart indicates the user's total software or application count per vendor. Hover over any color within the chart to view an individual vendor and its associated software count.

[Image: Vendor Distribution graph]

The user details table provides the following information for the selected user:

- **Name**: The software or application name run by the user.
- **Device**: The hardware device on which the software or application is running.
- **Version**: The specific version of the software or application.
- **Status**: Indicates whether the software or application is currently installed.
- **Vendor**: The name of the software or application provider.
- **Location**: Indicates where the software or application is installed on the user's system.
- **Install Date**: The date when the software or application was originally installed.
- **Version History**: Launches a window to display the date when the software or application version was last updated.

See image.

[Image: Software Details Page]

[Image: View Software Version History]

[Image: Software Inventory user details page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/viewing-software-patch-inventory","lastmod":"2025-05-29T19:13Z","nid":"1471776"} -->
## Viewing Software Patch Inventory

- Source: https://help.zscaler.com/zdx/viewing-software-patch-inventory
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Inventory > Viewing Software Patch Inventory
- Last modified: 2025-05-29T19:13Z
- Summary: Information about the distribution of software patches installed on user devices.

Software Patch Inventory allows you to monitor the current distribution of software patches on user devices across your organization. Each patch is associated with a user and device, and identified as either a software or security patch update, as applicable.

## Prerequisites

To view software patches, first ensure:

- You're running the required versions of Zscaler Client Connector and ZDX Module. To learn more, see [Supported Versions & Feature Compatibility](https://help.zscaler.com/zdx/supported-versions-feature-compatibility#PatchInventory).
- Your ZDX subscription level supports Software Patch Inventory. To learn more, see [Ranges & Limitations](https://help.zscaler.com/zdx/ranges-limitations).
- Your admin role is configured for inventory management. To learn more, see [Adding ZDX Roles](https://help.zscaler.com/zdx/adding-zdx-roles).
- You've enabled the setting to collect Software Patch Inventory data. To learn more, see [Configuring Inventory Settings](https://help.zscaler.com/zdx/configuring-inventory-settings).

## Viewing Software Patch Inventory

To access current software patches, go to **Inventory**> **Software Patch Inventory**. The page consolidates the distribution of all software patches with their associated devices.

Use the page filters to help narrow the scope of patches, based on Zscaler location and geolocation. View the numerical counts for software patches and devices:

- **Patches**: The current number of software patches available.
- **Devices**: The current number of devices with software patches.
- **OS Distribution**: Visually indicates the number of devices per operating system.

The Software Patch Inventory table provides the following details:

- **Patch Name**: The alphanumeric name of the software patch.
- **Patch Type**: Identified as an **Update**, **Security Update**, or **N/A**(Not Applicable).
- **Devices**: The number of devices on which the software patch is installed.

Click a patch name or its **View**icon to access details about the software patch.

[Image: Software Patch Inventory page]

### Viewing Patch Details

The software patch details page provides details of a specific patch selected from the Software Patch Inventory page:

- **Device**: The user device on which the software patch is installed.
- **User**: The user of the software patch.
- **Hostname**: The device hostname.
- **OS**: The associated operating system.
- **Version**: The version of the operating system.
- **Install Date**: The date when the software patch was installed on the corresponding device.

Click the patch device, user, or **View**icon to access user details about the patch. If selecting the device, a device ID is appended to the URL and the device is automatically included in the **Devices**filter on the corresponding user details page.

[Image: Software patch details page]

### Viewing User Details

The details page for a specific user provides the following software patch information:

- **Patch Name**: The alphanumeric name of the software patch.
- **Device**: The user device on which the software patch is installed.
- **Patch Type**: Identified as an **Update**, **Security Update**, or **N/A**(Not Applicable).
- **Install Date**: The date when the software patch was installed on the corresponding device.

[Image: Software patch inventory user details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/visualizing-user-connectivity-zscaler-digital-experience","lastmod":"2025-06-07T07:06Z","nid":"1499616"} -->
## Visualizing User Connectivity with Zscaler Digital Experience

- Source: https://help.zscaler.com/zdx/visualizing-user-connectivity-zscaler-digital-experience
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Reference Architecture > Visualizing User Connectivity with Zscaler Digital Experience
- Last modified: 2025-06-07T07:06Z
- Summary: The Visualizing User Connectivity with Zscaler Digital Experience (ZDX) reference architecture guide steers you through the architecture process, and provides technical deep dives into specific platform functionality and integrations.

The Zscaler Reference Architecture series delivers best practices based on real-world deployments. The recommendations in this series were developed by Zscaler's transformation experts from across the company. This guide will steer you through the architecture process and provide technical deep dives into specific platform functionality and integrations. The Zscaler Reference Architecture series is designed to be modular, so this guide will show you how to configure a different aspect of the platform in order to allow you meet your specific policy goals.

Zscaler Digital Experience (ZDX) provides visibility into the experience for your end user. ZDX delivers Digital Experience Monitoring (DEM) via Zscaler Client Connector, a small agent that probes the device itself. Zscaler Client Connector sends probes to cloud applications you define and performs network health checks from your client devices. ZDX also proactively alerts you to problems that are occurring.

[Visualizing User Connectivity with Zscaler Digital Experience: Reference Architecture Guide](https://help.zscaler.com/downloads/zdx/reference-architecture/visualizing-user-connectivity-zscaler-digital-experience/visualizing-user-connectivity-with-zscaler-digital-experience.pdf) [Download PDF](https://help.zscaler.com/downloads/zdx/reference-architecture/visualizing-user-connectivity-zscaler-digital-experience/visualizing-user-connectivity-with-zscaler-digital-experience.pdf)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/web-probe-errors","lastmod":"2026-05-06T21:06Z","nid":"1368676"} -->
## Web Probe Errors

- Source: https://help.zscaler.com/zdx/web-probe-errors
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Users > Web Probe Errors
- Last modified: 2026-05-06T21:06Z
- Summary: Information on Web probe error messages as seen in the User details.

At times, you might see error icons in the Web probe Metrics section of the User details. When you click the icon, you see related metrics information.

See image.

The icons in the graph indicate the following:

- [Image: Caution Icon] : This is a warning message. This indicates that action might be necessary to resolve an issue and achieve accurate results.
- [Image: Critical Error in ZDX]: This is a critical error. This indicates an issue that could affect the user experience.
- [Image: Image of icon for rate limiting]: This indicates a Private Access Web probe is rate limited.

[Image: Web Probe Errors in ZDX]

The following table provides a list of possible error messages and a description of the error.

| Error Message | Error Description | Recommended Action |  |
| --- | --- | --- | --- |
| The domain is invalid or not resolvable. Verify your domain. | After redirection, DNS resolution failed. This could be an issue with the application or its domain. | Verify the domain configured in the application is still valid or that it exists. Also check the application redirection. |  |
| TCP connection was reset | The TCP connection was reset. This could be due to a firewall or security policies, lack of server resources, server error, or congestion. | Verify that the security policies allow traffic to this application, or that the server is listening on the config port, or that server resources are adequate. |  |
| TCP connection timed out | The TCP connection timed out while waiting for a response from the application. | Check that the application configured for the Web probe is correct. Also check for any network devices that might be dropping SYN packets silently. |  |
| TCP connection aborted | The TCP connection was aborted, as a TCP connection reset message was received after the connection was established. | High numbers of aborted connections can point to network or server problems. |  |
| TCP connection refused | The TCP connection was refused. This means that no port is listening or that a firewall is blocking the port. | Check the configuration to verify that the correct port was used and your server is listening on that port. Also verify that your security policies allow traffic to this port. |  |
| TCP connection error | There was a generic TCP connection error | Contact Zscaler Support. |  |
| The Web probe HTTP method is not supported by the application | Zscaler does not currently support this HTTP request method. Currently, you can configure only the GET method from the UI. | Check the Web probe configuration. Consider adding a 40X/50X code to build a valid success code in the Web probe configuration. |  |
| TCP connection was reset during HTTP CONNECT request | The Public Service Edge for Internet & SaaS timed out the HTTP connection as no response was received from the destination server. | Check that you are authenticated to use the Zscaler service and there is a valid policy. |  |
| TCP connection timed out during HTTP CONNECT request | The Public Service Edge for Internet & SaaS timed out the HTTP connection as no response was received from the destination server. | Check that the URL in the Web probe configuration is correct. |  |
| TCP connection aborted during HTTP CONNECT request | The Public Service Edge for Internet & SaaS aborted the HTTP connection after receiving a TCP reset from the destination server. | Check the configuration to verify that the URL is correct. |  |
| TCP connection refused during HTTP CONNECT request | The Public Service Edge for Internet & SaaS refused the HTTP connection after receiving a TCP reset from the destination server. | Check the configuration to verify that the correct port was used and the destination server is listening on that port. Also check the security policy. |  |
| Invalid HTTP response received during HTTP CONNECT request | The Public Service Edge for Internet & SaaS HTTP response code had an error. | Contact Zscaler Support. |  |
| HTTP CONNECT request failed | There was a generic exception in sending the connection to the Public Service Edge for Internet & SaaS. | Verify that the Public Service Edge for Internet & SaaS is not blocked by the firewall. |  |
| HTTP response code xxx not a success code | The HTTP response code was a mismatch and is not in the configured list of successful HTTP codes. | The Web probe is configured to consider successful HTTP connections in the range (100-199), (200-299), and (300-399). The response code received was not in this range. Consider reconfiguring the HTTP success code to include (400-499) client errors. If (500-599) server errors are also transiently received, you might consider adding them as an HTTP success code. To learn more, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe). |  |
| TCP connection was reset during HTTP request to application. | The TCP connection was reset by the application server while the HTTP request was in progress. | The application server closed the TCP connection. This is probably due to a high load on the application. |  |
| TCP connection timed out during HTTP request to application | The TCP connection timed out while sending the HTTP request to the application server. | A response was not received from the server in the configured timeout (60 seconds by default). |  |
| TCP connection aborted during HTTP request to application | The TCP connection was aborted by the application server during the HTTP request. | The application server sent a TCP connection reset. This is probably due to a high load on the application. |  |
| TCP connection refused during HTTP request to application | The TCP connection was refused by the server during the HTTP request. | Verify that the configured port is open on the server. Also check if there is a firewall in the path that is blocking the connection. |  |
| TCP connection error during HTTP request to application | A generic application server error was received. | The TCP connection with the application could not be established. This is probably due to a high load on the application. |  |
| HTTPS connection failed due to invalid certificate | The certificate received from the application server is invalid. | Verify the validity of the certificate and that the certificate has not expired. |  |
| HTTPS connection failed due to SSL context exception | An SSL context exception error occurred. | The application server SSL handshake has a generic exception. Possible causes could be: Improperly formatted SSL certificate; Improperly installed certificate; Wrong cipher; Problem in the certificate’s chain of trust |  |
| HTTPS connection failed due to SSL error | A generic SSL exception occurred. This could be due to multiple possible causes. | The application server SSL handshake has a generic exception. Possible causes could be: Improperly formatted SSL certificate; Improperly installed certificate; Wrong cipher; Problem in the certificate’s chain of trust |  |
| Web probe request timed out | The probe timed out as there was no response. The Web probe HTTP request exceeded the configured timeout value (60, by default) in the probe configuration. | Verify that the URL is correctly configured or change the default timeout value. |  |
| Web probe is rate limited | The Web probe failed due to Private Access rate limiting to control the probe threshold. | When configuring Web probes for internal applications through Private Access, configure probes only for users, user groups, and departments that use the application. |  |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/zdx-integration-servicenow","lastmod":"2026-07-10T10:04Z","nid":"1461996"} -->
## ZDX Integration on ServiceNow

- Source: https://help.zscaler.com/zdx/zdx-integration-servicenow
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Alerts > Webhook Configuration Guides for Supported Platforms > ServiceNow Configuration Guides > ZDX Integration on ServiceNow
- Last modified: 2026-07-10T10:04Z
- Summary: To provide configuration steps for ZDX integration with ServiceNow.

With your Incident Management service user role and webhook configured, you can integrate ServiceNow with ZDX to:

- Create Deep Tracing sessions to provide deeper granularity and process-level information for a user.
- Run a Root Cause Analysis to help detect and identify the root cause of a drop in an application's ZDX Score.
- Map the categories and subcategories for incoming alerts or created incidents.

Deep Tracing is a type of [Diagnostics session](https://help.zscaler.com/zdx/starting-new-diagnostics-session). In ServiceNow, you can only configure for Deep Tracing.

## Prerequisites

There are different prerequisites based on which ZDX integration you are implementing. Depending on which service user role is assigned access, you might get different ZDX information.

### Assign an Incident Management Service User Role

Depending on which ZDX integration your service user must access, you are required to have one of the Incident Management roles assigned to your service user. Optionally, you can assign both roles to a service user if they require access to Root Cause Analysis, Alerts, and Deep Tracing.

| **ZDX Integration** | **Incident Management Service User Role** |
| --- | --- |
| Root Cause Analysis; Alerts | x_zsca2_zdx_manage.zdx_management |
| Deep Tracing | x_zsca2_zdx_manage.zdx_dt_management |

To learn more about how to assign the role, see [ServiceNow Webhook Configuration Guide](https://help.zscaler.com/zdx/servicenow-webhook-configuration-guide#CreateServiceUser_SNOW).

### Required ZDX Information for Deep Tracing and Root Cause Analysis

To start using ZDX integration for Deep Tracing or Root Cause Analysis, you must have:

- A ZDX Role with the correct permissions to create an API Key
- A Zscaler Admin Console URL
- A ZDX Public API URL

To create an API Key:

1. [Add a ZDX Role](https://help.zscaler.com/zdx/adding-zdx-roles) with the following permissions to create an API Key:
  - **Dashboard Access**: **View Only**
  - **Deep Tracing**: **Full**
  - **Device and User Information**: **Visible**
2. After creating the ZDX Role, [create an API Key](https://help.zscaler.com/zdx/managing-zdx-api-keys#CreateAPIKey) on the API Key Management page.
3. Save the API Key ID and Secret for the ServiceNow Settings Module.

Your Zscaler Admin Console URL is where you run Deep Tracing Sessions and Root Cause Analysis.

The format must be: `[subdomain].[second-level-domain].[top-level-domain]` (e.g., `admin.zdxcloud.net`). To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).

Your ZDX Public API URL is where the public API has access.

The format must be: `[subdomain].[second-level-domain].[top-level-domain]` (e.g., `api.zdxcloud.net`). To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).

## Configuring the Settings Module on ServiceNow for ZDX Integration

You must configure the ZDX application fields to enable Deep Tracing and Root Cause Analysis.

To configure the Settings Module on ServiceNow for ZDX integration:

1. Go to the **Settings** module of your Zscaler Digital Experience application on ServiceNow.
2. In the **Settings** module:
  - **ZDX PORTAL URL**: Enter your Zscaler Admin Console URL.
  - **ZDX API URL**: Enter your ZDX Public API URL.
  - **Key ID**: Enter your Key ID from your ZDX API Key.
  - **Key Secret**: Enter your Key Secret from your ZDX API Key.
3. Click **Save**. See image.

### Deep Tracing Integration on ServiceNow

To start a new Deep Tracing session on the Incident Management page of your ServiceNow console:

1. Select the incident assigned to you based on your user's Caller name.
2. Click the **Deep Tracing** button.
3. On the **Deep Tracing** page:
  1. **Name**: Enter the name of the Deep Tracing session. This includes the current incident number by default.
  2. **Run Session For**: Select the duration of the Deep Tracing session. You can select from **5 minutes**, **15 minutes**, **30 minutes**, or **60 minutes**.
  3. **User**: Select the impacted user.
  4. **Device**: Select the impacted user's device.
  5. **Device Probing**: Enable to gather device data for Deep Tracing.
  6. **Application**: Select the impacted application.
  7. **Web Probe**: Select the application's Web probe.
  8. **Cloud Path Probe**: Select the application's Cloud Path probe to configure the **Cloud Path Probe Thresholds**.
  9. (Optional) **Cloud Path Probe Thresholds**: Enter the following information:
    - **Packet Loss (%)**: Enter a percentage of packet loss for Deep Tracing.
    - **Latency (ms)**: Enter the latency to measure for Deep Tracing.
4. Click **Save**. See image.

After completing a Deep Tracing session, you can view a list of Deep Tracing sessions in the Incident's Deep Tracing section with the following information:

- **ZDX Link**: A link to the Deep Tracing session in the Zscaler Admin Console.
- **Name**: The name of the Deep Tracing session on the Zscaler Admin Console.
- **User**: The impacted user.
- **Device**: The impacted user's device.
- **Created Time**: The time the session was created by the ZDX admin.
- **Start Time**: The time that the Zscaler Client Connector accepted the request and started collecting data.
- **End Time**: The time the session ended.
- **Web Probe**: The application's Web probe that is monitored for the session.
- **Cloud Path**: The application's Cloud Path probe that is monitored for the session.
- **Status**: The current session status differs depending on the table in which the session is listed. To learn more, see [Understanding the Diagnostics Session Status](https://help.zscaler.com/zdx/understanding-diagnostics-session-status).

See image.

### Root Cause Analysis Integration on ServiceNow

To run a Root Cause Analysis on the Incident Management page of your ServiceNow console:

1. Select the incident assigned to you based on your user's Caller name.
2. Click the **ZDX Summary** tab.
3. On the **ZDX Summary** page:
  1. **From**: Select when the Root Cause Analysis starts.
  2. **To**: Select when the Root Cause Analysis stops.
  3. Click **Submit**.
4. Under the **Application** section, you can click **Analyze Score** on one of the existing applications if a low score is detected (less than or equal to 34). See image. A progress bar for the Root Cause Analysis appears. See image.
5. When the Root Cause Analysis is completed, view a list of factors that determined the ZDX Score. See image.

## Mapping the ZDX Alert Types to ServiceNow Categories and Subcategories

To map the ZDX Alert types to ServiceNow Categories and Subcategories:

1. Open an existing ServiceNow instance.
2. In the Global Application scope, enter `sys_choice.list` in the **Filter Navigator** type.
3. Click **New**.
4. Configure the following properties to create a new record:
  - **Table**: Select **Incident**.
  - **Element**: Enter `category`or `subcategory`.
  - **Label**: Enter the name of the label (e.g., `ZDX Alert Category`).
  - **Value**: Enter a `code` to access the label record. For example, if you create the label, `ZDX Alert Category`, then you can create the code value, `zdx_alert_category`, to associate them together.
  - **Dependent Value**: If you are creating a subcategory, enter the value of the root category as required.
5. Click **Submit**. See image.

You can now view the Alert Impact and Impacted Users pages. You can also configure the Mappings module. To learn more, see [Understanding the ZDX Application Fields on ServiceNow](https://help.zscaler.com/zdx/understanding-zdx-application-fields-servicenow).

### Alert Impact

The Alert Impact page provides an overview of the impacted devices that meet the alert rule's criteria. The impacted devices are sorted by Departments, Geolocations, and Zscaler Locations.

To access Alert Impact:

1. Select an Incident.
2. Click **Alert Impact**. See image.

You see results except for Alert Impact or Impacted Users if the ZDX Score is an alert type based on Public API support.

### Impacted Users

The Impacted Users page displays a list of impacted devices and their users that meet the alert rule's criteria. The maximum number of devices displayed is 100.

You can view:

- **Device**: The device name.
- **User Name**: The user's name.
- **User Email**: The user's email address.
- **Link**: A link to the impacted device page in the Zscaler Admin Console.

See image.

You see results except for Alert Impact or Impacted Users if the ZDX Score is an alert type based on Public API support.

## User Domain Settings

If your users have emails or usernames that differ from the Zscaler Client Connector login, you can configure the User Domain Settings to append or replace the domain as needed. By configuring the User Domain Settings, the user has continued access to basic operations (e.g., fetching a ZDX Summary or starting a Deep Tracing).

To configure the User Domain Settings on ServiceNow:

1. Go to **Zscaler Digital Experience** > **User Domain Settings**.
2. Under **Choose Domain Modification type**, select one of the following:
  - **None**: No modifications to the domain settings are required. This is the default selection.
  - **Append**: Indicates you want to append a domain to the user identity.
  - **Replace**: Indicates you want to replace the user identity domain to match the new domain string.
3. Depending on what you selected, enter the following:
  - **Append Domain to user identify**: If you previously selected **Append**, enter the domain you want to associate with the user identity (e.g., `@gmail.com`).
  - **Domain to be replaced in user identity**: If you previously selected **Replace**, enter the domain that replaces the domain identity (e.g., `yahoo.com`).
  - **New Domain String to match user identity**: If you previously selected **Replace**, enter the domain that matches the previously entered domain with the user identity in Zscaler Client Connector (e.g., `google.com`).
4. Click **Save**. See image.

Configuring user domain settings does not manipulate the existing data in the ServiceNow user record.

[Image: ZDX Category]

[Image: Configuring the ZDX Settings Module]

[Image: Start a New Deep Tracing Session]

[Image: In Progress Analyze Score]

[Image: Completed ZDX Analyze Score]

[Image: Alert Impact]

[Image: Impacted Users]

[Image: Completed Deep Tracing Sessions List]

[Image: Progression bar appears after you click Analyze Score]

[Image: Configure domain settings for user identity]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/zdx-module-processes-allowlist","lastmod":"2026-08-06T13:03Z","nid":"1541220"} -->
## ZDX Module Processes to Allowlist

- Source: https://help.zscaler.com/zdx/zdx-module-processes-allowlist
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Getting Started > ZDX Module Processes to Allowlist
- Last modified: 2026-08-06T13:03Z
- Summary: Information on binaries and processes that the users' devices should allowlist for ZDX Module.

Zscaler recommends that your users' devices have rules that allow the binaries and processes for ZDX Module. These are important prerequisites to allow ZDX Module to run specific ZDX features.

For some endpoint protection products like antivirus and personal firewalls, you might need to update additional allowlists for full functionality.

If you need to learn more about Zscaler Client Connector's processes to allowlist and bypasses for firewall, see [Zscaler Client Connector Processes to Allowlist](https://help.zscaler.com/zscaler-client-connector/zscaler-client-connector-processes-allowlist).

## Allowlist Processes

This section lists the file or file paths to allowlist.

- Windows
- macOS

You must allowlist `ZSUpmInstaller` to allow ZDX Module to upgrade to the latest version.

The location of files to allowlist depends on your version of ZDX Module.

If your organization uses Group Policy Object (GPO) to push policies on both Windows 32-bit and Windows 64-bit systems, you must add the file paths of both versions to your GPO policy.

- Windows: 32-bit version
- Windows: 64-bit version

`%ProgramFiles(x86)%` and `%ProgramFiles%` are environmental variables that represent the drive where the Windows program files are located. Typically, program files are located on the C drive. However, there are exceptions. For example, on Amazon WorkSpaces, program files are on the D drive.

Allowlist the file or file path of the following:

These files reside in the `%ProgramFiles(x86)` folder even if you are running the 32-bit version of ZDX Module on a 64-bit system.

| Feature | Allowlist |
| --- | --- |
| [Real User Monitoring (RUM)](https://help.zscaler.com/zdx/understanding-real-user-monitoring) | `%Program Files(x86)\Zscaler\ZSAUpm\ZUpmApplication.exe` |
| [Remediation](https://help.zscaler.com/zdx/understanding-remediation) | `%ProgramFiles(x86)%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptOrchestratorService.exe %ProgramFiles(x86)%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptExecutor.exe` |

Allowlist the file or file path of the following:

| Feature | Allowlist |
| --- | --- |
| [Real User Monitoring (RUM)](https://help.zscaler.com/zdx/understanding-real-user-monitoring) | `%Program Files\Zscaler\ZSAUpm\ZUpmApplication.exe` |
| [Remediation](https://help.zscaler.com/zdx/understanding-remediation) | `%ProgramFiles%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptOrchestratorService.exe %ProgramFiles%\Zscaler\ZSAScriptOrchestratorService\ZSAScriptExecutor.exe` |

Allowlist the entire path of the following files:

| Feature | Allowlist |
| --- | --- |
| [Real User Monitoring (RUM)](https://help.zscaler.com/zdx/understanding-real-user-monitoring) | `/Library/Application Support/Zscaler/UPM/ZUpmApplication` |

## Process Usage

The following list describes what each process is used for:

| Process | Description |
| --- | --- |
| ZSAUpm | Runs the ZDX service. |
| ZSAUpmInstaller | Runs the ZDX service installer for ZDX Module upgrades. |
| ZSAScript | Runs remote scripts on the device. |
| ZUpmApplication | Gathers RUM metrics. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/zoom-call-quality-zdx-integration-requirements","lastmod":"2023-01-05T21:43Z","nid":"1389081"} -->
## Zoom Call Quality for ZDX Integration Requirements

- Source: https://help.zscaler.com/zdx/zoom-call-quality-zdx-integration-requirements
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Analytics > Applications > Zoom Call Quality for ZDX Integration Requirements
- Last modified: 2023-01-05T21:43Z
- Summary: Integration requirements for Zoom Call Quality

Review the following Zoom application requirements before configuring and using Zoom Call Quality for Zscaler Digital Experience (ZDX). You must already have a ZDX subscription and ZDX admin access.

## Installing Zoom Call Quality from the Zoom App Marketplace

To install Zoom Call Quality for ZDX from the Zoom App Marketplace:

1. Sign in to the [Zoom App Marketplace](https://zoom.us/signin).
2. Search for **Zscaler Digital Experience**.
3. Click **Visit Site to Install**. You are automatically redirected to the following URL to access the ZDX Admin Portal:

```
https://admin.zdxcloud.net/zdx/login
```

1. Log in to the ZDX Admin Portal and follow the configuration steps provided in [Configuring Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/configuring-zoom-call-quality-zdx).

## Using Zoom API Scopes

The following Zoom API scopes are used with Zoom Call Quality for ZDX:

| **Name** | **Description** |
| --- | --- |
| dashboard_meetings:read:admin | Required by the Zoom API for meeting data. |
| meeting:read:admin | Required by the Zoom API webhook start and stop meeting notifications. |
| user:read:admin | Required by the Zoom API for user data. |

Zoom Call Quality for ZDX receives webhook notifications from Zoom for your organization when a meeting starts or stops. ZDX then makes periodic Quality of Service (QoS) user API calls to retrieve meeting QoS data and makes the data available via APIs to display on the UI.

Data is captured in real time as a meeting is in progress. User and device information garnered from the API for call participants is mapped to ZDX users and devices. While incremental data is available for calls in progress, complete call data is available approximately 10 minutes after a call has ended.

To learn more, see [Understanding Zoom Call Quality for ZDX](https://help.zscaler.com/zdx/understanding-zoom-call-quality-zdx).

## Removing Zoom Call Quality from the ZDX Admin Portal

To remove Zoom Call Quality for ZDX from the ZDX Admin Portal:

1. Go to **Configuration**>**Applications**.
2. Click **Delete** to remove the tenant for Zoom Call Quality.

## Removing Zoom Call Quality from the Zoom App Marketplace

To remove Zoom Call Quality for ZDX from the Zoom App Marketplace:

1. Sign in to the [Zoom App Marketplace](https://zoom.us/signin).
2. Go to **Manage**>**Installed Apps**.
3. Click the **Zoom Call Quality for ZDX** app.
4. Click **Uninstall**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/zscaler-managed-probe-errors","lastmod":"2026-06-25T21:06Z","nid":"1506091"} -->
## Zscaler Managed Probe Errors

- Source: https://help.zscaler.com/zdx/zscaler-managed-probe-errors
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Configuration > Probes > Zscaler Managed Probe Errors
- Last modified: 2026-06-25T21:06Z
- Summary: Information on Zscaler Managed probe error messages seen.

At times, you might see errors for the Zscaler Managed probes. The types of errors seen are based on the type of probe:

- Web probe
- Cloud Path probe

The following table provides a list of possible Web probe error messages, including a description of the error and the recommended action.

| Error Message | Error Description | Recommended Action |
| --- | --- | --- |
| Web probe has insufficient memory | The Web probe ran out of memory. | Report the error to Zscaler Support. |
| Web probe has incorrect configuration | The Web probe contains an incorrect configuration. | Review and validate the Web probe's configuration. |
| Invalid location | The Web probe has an invalid location. | Report the error to Zscaler Support. |
| Invalid Web probe Destination URL | The Web probe contains an invalid destination URL. | Check that your destination URL for the Web probe is correct. |
| Web probe has met the maximum redirects | The Web probe reached the maximum amount of redirects. | Increase the maximum amount of redirects. |
| Not within the HTTP response code range | The Web probe has reached an error code that does not exist within the HTTP response code range. | No action required. |
| Web probe request timed out | The Web probe has timed out. | Increase the maximum allowed timeout. |
| System error while processing analytics | There is a system error where data could not be gathered for analytics. | Report the error to Zscaler Support. |
| Internal error | There is an unknown internal error with the Web probe. | Report the error to Zscaler Support. |

The following table provides a list of possible Cloud Path probe error messages, including a description of the error and the recommended action.

| Error Message | Error Description | Recommended Action |
| --- | --- | --- |
| Error processing a timeout event | The Cloud Path probe timed out while waiting for a response. | Report the error to Zscaler Support. |
| Error processing socket event | The Cloud Path probe experienced an error while processing. | Report the error to Zscaler Support. |
| Failed DNS response | The Cloud Path probe aborted due to an internal error and cannot read/write DNS packets to/from the probe. | Report the error to Zscaler Support. |
| Cloud Path probe has insufficient memory | The Cloud Path probe has exceeded the memory limit for the connection. | Report the error to Zscaler Support. |
| Cloud Path probe is currently in progress. | A Cloud Path probe is already in progress for the device. | Report the error to Zscaler Support. |
| User logging callback already set | Tried to set an existing user logging callback. | Report the error to Zscaler Support. |
| User logging expanded callback already set | Tried to set an existing user logging callback. | Report the error to Zscaler Support. |
| Unable to generate random numbers | Internal error with random number generator. | Report the error to Zscaler Support. |
| Unable to create hash table | Internal error creating hash table. | Report the error to Zscaler Support. |
| Duplicate entry in hash table | Duplicate Cloud Path probe | Report the error to Zscaler Support. |
| Reached maximum capacity for hash table | Cannot create more as limitation has been reached. | Report the error to Zscaler Support. |
| Unable to create timeout list | Internal error when trying to create a timeout list. | Report the error to Zscaler Support. |
| Duplicate entry in timeout list | There is a duplicate entry in the timeout list. | Report the error to Zscaler Support. |
| Unable to read/write network socket | Aborted Cloud Path probe due to network or system error. | Report the error to Zscaler Support. |
| Unable to create socket handle | Aborted Cloud Path probe due to a system error possibly related to limited system resources. | Report the error to Zscaler Support. |
| Cannot retrieve socket address | Aborted probe due to system error related to no availability of network interfaces. | Report the error to Zscaler Support. |
| Cannot read/write socket option's reuse address | Aborted probe due to system error. | Report the error to Zscaler Support. |
| Cannot read/write socket option's reuse port | Aborted probe due to system error. | Report the error to Zscaler Support. |
| Cannot write socket option's IP header include | Aborted probe due to system error. | Report the error to Zscaler Support. |
| Cannot read/write the socket recv buffer | Aborted probe due to system error with Zscaler Managed probe. | Report the error to Zscaler Support. |
| Cannot read/write socket option's error | Aborted probe due to system error related to an unsupported socket option. | Report the error to Zscaler Support. |
| Cannot connect to the host | The probe is unable to connect to the host. | Review probe configuration for a valid DNS name or IP address. If the error persists, report the error to Zscaler Support. |
| Socket address not binded | Aborted probe due to system error related to host. | Report the error to Zscaler Support. |
| Unable to read packets | There is an internal error with reading packets. | Report the error to Zscaler Support. |
| Cannot write to the socket | There is an internal error with writing packets to the network. | Report the error to Zscaler Support. |
| Socket cannot close for reading | There was an internal error while trying to close the connection to the network. | Report the error to Zscaler Support. |
| Socket cannot close for writing | There was an internal error while trying to close the connection to the network. | Report the error to Zscaler Support. |
| Network protocol not supported | Network protocol is not supported. | Provide a supported network protocol. |
| Protocol not supported | A protocol other than TCP or ICMP was provided and is not supported. | Provide a TCP or ICMP as the protocol. |
| Invalid IP address | Invalid IP address was provided. | Check your IP address for validity. |
| Resolved IP address type does not match the requested type | The provided IP address does not match the requested type (IP or IPv6). | Report the error to Zscaler Support. |
| Cloud Path probe did not reach destination. | The Cloud Path probe is unable to reach the destination. | Check if the destination IP or domain is valid. |
| Timeout list hint was not provided | Aborted probe due to an internal error. | Report the error to Zscaler Support. |
| Invalid socket event | Aborted probe due to an unexpected error. | Report the error to Zscaler Support. |
| Invalid timeout event | Aborted probe due to an internal error with an invalid timeout event. | Report the error to Zscaler Support. |
| Triggered unhandled event | Probe was aborted due to an internal error with an unhandled event. | Report the error to Zscaler Support. |
| Error resolving domain | There was an issue resolving the domain name. | Verify the DNS name for probe destination. |
| DNS request timed out while waiting for a reply. | There was an issue with the DNS server response to the DNS request. | Report the error to Zscaler Support. |
| Non-existent domain | The DNS name is not resolvable. | Verify the DNS name for probe destination. |
| Empty DNS response | There was no response from the DNS server with the provided IPs. | Verify if the domain name is valid. |
| Cannot write socket due to timeout | There is an internal error. | Report the error to Zscaler Support. |
| Cannot read socket due to timeout | Aborted probe due to no destination response in the allotted timeout. | Review timeout field in probe configuration. |
| Invalid Cloud Path probe state | There is an internal error with the Cloud Path probe. | Report the error to Zscaler Support. |
| Destination server for the Binary Search of the Cloud Path probe is unresponsive. | The destination server of the probe is unresponsive. | Review the number of maximum hops for the probe destination. |
| Destination server of the Cloud Path probe is unresponsive. | The destination server of the probe is unresponsive causing a timeout. | Review the timeout for the destination server. |
| Received unexpected ICMP TIME EXCEEDED message. | Aborted probe due to an unexpected packet. | Report the error to Zscaler Support. |
| Received unexpected ICMP DST UNREACHABLE/PORT UNREACHABLE message. | Aborted probe due to an unexpected packet. | Report the error to Zscaler Support. |
| Received unexpected ICMP DST UNREACHABLE message. | Aborted probe due to an unexpected packet. | Report the error to Zscaler Support. |
| TCP Cloud Path probe reached maximum retries. | Reached maximum number of packet retries within the received timeout. | Review probe timeout configuration. |
| TCP SYN packet received unexpectedly | Unexpected SYN packet sent. | No action required. |
| Internal error | Aborted probe due to an unexpected internal error. | No action required. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/zdx/zscaler-service-continuity-customer-notification-protocol","lastmod":"2026-03-27T17:36Z","nid":"1358921"} -->
## Zscaler Service Continuity Customer Notification Protocol

- Source: https://help.zscaler.com/zdx/zscaler-service-continuity-customer-notification-protocol
- Product: Digital Experience Monitoring (ZDX)
- Path: Digital Experience Monitoring (ZDX) Help > Zscaler Service Continuity Customer Notification Protocol
- Last modified: 2026-03-27T17:36Z
- Summary: Information about the Service Continuity Customer Notification Protocol for Zscaler Digital Experience (ZDX).

[Image: ZIA Service Continuity Matrix]

Zscaler's Service Continuity Policy governs how and when we make changes to our services. Our End-of-Sale (EOS) and End-of-Life (EOL) Policy is separate from our Service Continuity Policy. To learn more, see [EOS and EOL Policy](https://help.zscaler.com/eos-eol/eos-and-eol-policy).
<!-- /ZS-ARTICLE -->
