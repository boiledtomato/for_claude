# Zscaler Help — Unified Platform / Admin / Logs (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-03 02:47 UTC
Articles in this file: 96

---

<!-- ZS-ARTICLE {"url":"/unified/configuring-suppression-rules","lastmod":"2026-07-29T03:42Z","nid":"1541934"} -->
## Configuring Suppression Rules

- Source: https://help.zscaler.com/unified/configuring-suppression-rules
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Configuring Suppression Rules
- Last modified: 2026-07-29T03:42Z
- Summary: How to configure suppression rules for data sources to either exclude data from ingestion, or to include a subset of the source data.

When [creating a data source](https://help.zscaler.com/unified/creating-data-sources) to ingest data into the Security Operations Platform (SecOps Platform), you can apply suppression rules to control which data is included in the ingestion process. Suppression rules allow you to either exclude specific data (e.g., from decommissioned assets or test environments) or include only a targeted subset of the source data. This helps reduce noise, avoid processing irrelevant or sensitive information, and ensure that only actionable data is brought into the platform.

Suppression rules can be configured either during initial source setup or after the source has ingested data for the first time. The best approach depends on how familiar you are with the structure of the source fields. After configuring your rules and reprocessing the data, be sure to validate that the suppression is working as intended.

Suppression rules only apply to data ingested after the rules are configured. The rules do not retroactively affect data that was previously processed and ingested into the platform.

## Configuring Rules on an Existing Source

Suppression rules rely on the original field names provided by the third-party data source. If you're not already familiar with the source field names and field types, Zscaler recommends allowing the source to ingest data once without any suppression rules configured.

This initial ingestion populates the field name drop-down menu in the suppression rules configuration screen, making it easier to select the correct fields for filtering. When the fields are visible, you can set up rules to include or exclude specific data.

- Step 1: Configure Suppression Rules
- Step 2: Delete Existing Data
- Step 3: Reprocess the Data

To set up suppression rules on a source that has already ingested data:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**.
2. Choose a data source using one of the following methods: See image.
  - Hover over the data source, and click the **Edit**icon.
  - Select a data source from the list, and click **Edit** on the toolbar.
3. On the source setup page, scroll down to the **Suppression Rules** section.
4. Select the rule scope:
  - **Exclude Rows**: Filter out data that should not be ingested.
  - **Include Rows**: Ingest only data that matches your criteria. See image.
5. Select the field from the drop-down menu to serve as the filter criterion for data suppression. The drop-down menu is populated with field names from the source after at least one ingestion run.
6. Select the field type (e.g., **Text**, **Date**, **Number**, or **Boolean**). The field type determines the available filter operators. See image.
7. Select a filter operator (e.g., **Equals**, **Contains**, **<**).
8. Enter the value to filter by (i.e., to exclude or include in the data ingestion). Suppression rules arecase sensitive.
9. (Optional) Use **AND**/**OR** logic to define compound rules.
10. Click **Save** to save the rules to the source.

Saving the source along with your configured suppression rules returns you to the Sources page.

**[Image: Edit icon on the Sources page]**

**[Image: Suppression Rules section displaying the field type drop-down menu]**

**[Image: Suppression Rules type]**

Data retrieved from the source before setting up the suppression rules is not retroactively removed. Before reprocessing the data source, remove the data that was ingested in the first run to ensure clean and accurate data.

To delete existing data ingested by the source:

1. Go to **Configure**> **Sources**.
2. Choose a data source using one of the following methods: See image.
  - Hover over the data source, and click the **Delete** icon.
  - Select a data source from the list, and click **Delete** on the toolbar.
3. Select **Keep the data source, delete the underlying data**.
4. Click **Delete**.

[Image: Delete icon on the Sources page]

After you delete the existing data from the system, make sure to re-ingest the data excluding the suppressed fields.

To re-ingest the source data:

1. Go to **Configure**> **Sources**.
2. Choose a data source using one of the following methods: See image. A message appears at the top of the page, indicating whether the process action was successful. To view the run status, hover over the source and click the **See Runs**icon. To learn more, see [Tracking Data Source Runs](https://help.zscaler.com/unified/tracking-data-source-runs).
  - Hover over the data source, and click the **Process Now** icon.
  - Select a data source from the list, and click **Process Now** on the toolbar.

[Image: Process Now icon on the Sources page]

## Configuring Rules on a New Source

If you're familiar with the source's original field names, you can also apply suppression rules when setting up a new source. Here, you'll need to add the field names manually in the rule configuration process.

To configure rules on a new source:

1. Go to **Configure** > **Sources.**
2. Click **Create**.
3. Select the desired source from the gallery, and follow the setup process in [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).
4. Select the rule scope:
  - **Exclude Rows**: Filter out data that should not be ingested.
  - **Include Rows**: Ingest only data that matches your criteria. See image.
5. In the **Select Field** drop-down menu, enter the field name, then press`Enter`.
6. Select the field from the drop-down menu to serve as the filter criterion for data suppression. The drop-down menu is populated with field names from the source after at least one ingestion run.
7. Select the field type (e.g., **Text**, **Date**, **Number**, **Boolean**). The field type determines the available filter operators. See image.
8. Select a filter operator (e.g., **Equals**, **Contains**, **<**).
9. Enter the value to filter by (i.e., to exclude or include in the data ingestion). Suppression rules arecase sensitive.
10. (Optional) Use **AND**/**OR** logic to define compound rules.
11. Click **Save** to save the rules to the source.

Saving the source along with your configured suppression rules returns you to the Sources page.

Saving the source does not run the source. To process the source immediately, hover over the source in the list and click the **Process Now** icon. Otherwise, the source runs automatically based on its configured [scheduling settings](https://help.zscaler.com/unified/creating-data-sources).

**[Image: Suppression Rules type]**

**[Image: Suppression Rules section displaying the field type drop-down menu]**

## Validating Suppression Rules

After configuring suppression rules and reprocessing the source, it's important to confirm that the rules are working as intended. You can validate suppression in one of two ways.

### Check for a Decrease in Row Count

Checking for a decrease in row count is a quick way to verify that suppression rules are filtering data during ingestion.

To check for a decrease in source runs:

1. Go to **Configure** > **Sources**.
2. Choose a data source using one of the following methods:
  - Hover over the data source, and click the **See Runs** icon.
  - Select a data source from the list, and click **See Runs** on the toolbar.
3. Expand the most recent run to view the total number of ingested rows.
4. Asses the row count, or compare the row count with a previous run to confirm that data has been suppressed. To learn more, see [Tracking Data Source Runs](https://help.zscaler.com/unified/tracking-data-source-runs).

### Review Ingested Logs

To verify that your suppression rule is working as intended, you can search the logs to check whether the targeted data was successfully excluded. To learn more, see [Building Queries and Searching Logs](https://help.zscaler.com/uvm/building-queries-searching-logs).

To search the logs for suppressed data:

1. Go to **Explore** > **Logs**.
2. Build a query to search for the suppressed or included data: See image.
  1. Select the suppressed source field.
  2. Use the **Equals** operator.
  3. Enter the relevant value based on the suppression rule type:
    - **Exclude Rows**: Enter a value you specified in the suppression rule.
    - **Include Rows**: Enter a value not included in the suppression rule.
3. Click **Search**. If the query returns no results, the suppression rule is likely to be functioning as expected.

**[Image: Query searching for Prisma Cloud CWP Compliance logs]**
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/configuring-url-filtering","lastmod":"2026-02-12T08:54Z","nid":"1488016"} -->
## Configuring URL Filtering Policies

- Source: https://help.zscaler.com/unified/configuring-url-filtering
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Setting Up Policies > Configuring URL Filtering Policies
- Last modified: 2026-02-12T08:54Z
- Summary: How to configure your initial URL filtering policies to limit the exposure of your users to potentially risky websites.

URL filtering policies allow you to use predefined URL categories to determine how internet traffic in each of the categories is handled by Zscaler. There are three ways to route traffic:

- **Block**: websites belonging to blocked categories cannot be accessed by users in your organization.
- **Isolate**: websites belonging to isolated categories will open in a remote browser in a Zscaler data center, preventing any active content from the web page from reaching the user's device. To learn more, see [About Isolation Policy](https://help.zscaler.com/zpa/about-isolation-policy).
- **Allow**: websites belonging to allowed categories will open normally within the user's browser.

To define URL filtering policies:

1. After you have set up users and traffic forwarding, click **Set Up Policies**.
2. The U**RL Filtering** page shows a recommended setup to route internet traffic when users visit websites matching each of the specified URL categories. You can drag the URL categories to the **Block**, **Isolate**, or **Allow**columns to set up the filtering policy appropriate for your organization.
3. When done, click **Next** to move on to [configure SSL inspection](https://help.zscaler.com/unified/configuring-ssl-inspection).

You can fine-tune your configuration later in the Admin Portal. To learn more about how URLs are categorized, see [About URL Categories](https://help.zscaler.com/zia/about-url-categories)*.*
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/configuring-user-privacy","lastmod":"2024-08-20T17:01Z","nid":"1488036"} -->
## Configuring User Privacy

- Source: https://help.zscaler.com/unified/configuring-user-privacy
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Setting Up Policies > Configuring User Privacy
- Last modified: 2024-08-20T17:01Z
- Summary: How to configure policies to protect user and device privacy in your organization.

User privacy policies protect your users from having their personally identifiable information (PII) and device information viewable by administrators within your organization.

You can configure these user privacy policies during onboarding, or later at any time in the Admin Portal:

- **Collect device owner information and traffic**: When this setting is enabled, administrators in your organization are able to view user device information (i.e., device hostname, device owner, and device name) when they view dashboards, reports, or insights. This setting is enabled by default; it should be disabled where required by law, such as regions governed by the General Data Protection Regulation (GDPR). Click the toggle to disable this feature.
- **Turn off automatic crash reporting**: When this setting is enabled, Zscaler will upload reports of system crashes experienced by your users for analysis. These reports might contain user identifiable information. This setting is disabled by default in compliance with GDPR. Click the toggle to enable this feature.

Click **Next** when you are done reviewing the policies to [configure blocked countries](https://help.zscaler.com/unified/configuring-blocked-countries) and [customize user notifications](https://help.zscaler.com/unified/customizing-user-notifications).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/creating-custom-dashboard","lastmod":"2026-02-26T08:28Z","nid":"1535232"} -->
## Creating a Custom Dashboard

- Source: https://help.zscaler.com/unified/creating-custom-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Custom Dashboards > Creating a Custom Dashboard
- Last modified: 2026-02-26T08:28Z
- Summary: How to create a custom dashboard in Zscaler Admin Console.

Zscaler provides a robust collection of [preconfigured analytics dashboards](https://help.zscaler.com/unified/analytics/unified-dashboards) that give you insight into different aspects of your organization's security profile, including network health, cybersecurity and data security metrics, user digital experience, and more.

You can create your own custom dashboards that contain personalized views of your organization's security profile so that you can monitor the metrics most important to you at a glance. You can create a dashboard completely from scratch, or use and combine templates provided by Zscaler.

After you create a custom dashboard, you can[share it with others in your organization](https://help.zscaler.com/unified/sharing-custom-dashboard) for viewing or collaboration. You can later [manage a custom dashboard](https://help.zscaler.com/unified/modifying-custom-dashboard) at any time.

To create a custom dashboard:

1. Go to **Analytics > Custom Dashboards**.
2. Click **Create Dashboard.** See image.
3. In the **Create Dashboard** window, choose a data source template (such as **Cybersecurity** or **Networking**) to start your custom dashboard with preloaded widgets that you can modify or remove, or choose **Blank Dashboard** to create a dashboard entirely from scratch. Your custom dashboard can contain widgets from multiple data sources. See image.
4. Click **Add Widget**, then select a widget you want to add to the dashboard. You can move, resize, and relocate widgets as needed and adjust the time range for the data shown. If you are using a template, delete any widgets you do not wish to use in this custom dashboard. Repeat for as many widgets as you want to add. Watch how to build a custom dashboard.
5. Click **Save**to save the dashboard. To name and describe the dashboard, click the **Actions**icon ([Image: Actions icon]) in the upper right and select **Rename**. See image.
6. In the **Rename Dashboard** window, enter a name and description for the dashboard that are shown in the Custom Dashboard list. See image.
7. Click the Custom Dashboards link in the upper-left corner to return to the **Custom Dashboards** page. See image.

[Image: Custom Dashboards page with the Create Dashboard button highlighted.]

[Image: Create Dashboards window.]

[Image: Animated gif showing how to create a custom dashboard.]

[Image: Actions drop-down menu with Rename selected.]

[Image: Rename Dashboard dialog on the Custom Dashboards page.]

[Image: Create Dashboard page showing the Custom Dashboard link that returns to the main Custom Dashboards page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/creating-data-sources","lastmod":"2026-08-02T00:03Z","nid":"1541932"} -->
## Creating Data Sources

- Source: https://help.zscaler.com/unified/creating-data-sources
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Creating Data Sources
- Last modified: 2026-08-02T00:03Z
- Summary: How to create a new data source in the Security Operations Platform, including source details, retrieval settings, scheduling, remediation detection settings, and suppression rules.

The Security Operations Platform (SecOps Platform) collects and correlates security data and business context from a wide range of external tools, such as vulnerability scanners, asset inventories, and cloud providers. To begin ingesting this data into your environment, you must first connect the relevant sources to your account. Establishing these connections ensures that the SecOps Platform can continuously retrieve and normalize data for analysis, prioritization, and workflows.

Before you create a source, see [Deploying Data Source Configurations](https://help.zscaler.com/unified/deploying-data-source-configurations) for deployment prerequisites, setup considerations, and planning guidance. To identify the available sources, supported streams, and source-specific configuration requirements, see [Security Operations Platform Configurations by Data Source](https://help.zscaler.com/unified/security-operations-platform-configurations-data-source).

This article explains how to create a new data source. For information on managing existing sources, see [Managing Data Sources](https://help.zscaler.com/unified/managing-data-sources).

To create a data source:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**. A list of all existing sources appears.
2. Click**Create**.
3. Select a source from the available tiles. You can also search for a source in the search field. The source setup page appears. You can connect a generic AnySource connector, which allows you to upload or extract files from data storage platforms such as Google Cloud Platform (GCP) and AWS S3. To learn more, see [Connecting AnySource](https://help.zscaler.com/uvm/connecting-anysource).
4. On the source setup page, configure the setup in the following sections:
  - Details
  - Retrieval
  - Scheduling
  - Remediation Detection Settings
  - Suppression Rules
5. (Optional) Click **Test** to verify the source's authentication and connectivity.
6. Click **Save** to save the data source.

Saving the source redirects you to the Sourcespage, where all connected sources are listed. Your new data is retrieved on the next data run. To retrieve the data immediately, hover over the source and click the **Process Now** icon.

See image.

To ensure the data can be correctly processed and used across the SecOps Platform, you must configure field mappings. Hover over the newly added source and click **Map Data** to map source fields to the corresponding SecOps Platform fields. To learn more, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources) and [Using Mapping Copilot](https://help.zscaler.com/unified/using-mapping-copilot).

In the **Details** section, enter the source's basic details:

- **Name**: Enter a unique name for the data source that accurately reflects the specific data stream instance.
- **Source Name**: Enter the display name for the source.
- **Override Icon Name**: (for AnySource only) If you're connecting an AnySource connector, you can override the default icon with one that matches the vendor's branding.
- **Description**: (Optional) Enter a brief description of the data the source retrieves.

In the **Retrieval** section, define how data is fetched from the vendor’s system:

- **Authentication**: Select an existing authentication or click **Create New** to add an authentication for the vendor. To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications). Each source has its own unique set of required parameters outlined in the specific vendor's deployment guide in the [Source Configuration Guides](https://help.zscaler.com/uvm/administration/connectors/sources/source-configuration-guides)section.
- **Filters and Specifications**: Configure available data retrieval filters and specifications, when supported by the source. For example, on the **CrowdStrike Managed Hosts** source, you can filter the ingested data by **Asset Types** and/or specify the **CrowdStrike Cloud Region**to target the appropriate environment. See image.
- **Gateway**: (Optional) Select a gateway. To learn more, see [Configuring the Zscaler Security Operations Platform Gateway](https://help.zscaler.com/uvm/configuring-zscaler-secops-platform-gateway).

[Image: The Retrieval window for CrowdStrike Managed Hosts]

In the **Scheduling** section, set the frequency and time at which the SecOps Platform retrieves data from the vendor.

- **Full Refresh Frequency**: Set the frequency and time for ingesting the full dataset.
- **Incremental Refresh Frequency**: (Optional) When supported by the source, set the frequency and time for ingesting new or updated data only. Zscaler recommends enabling Auto Scheduling for all data sources to ensure that related data is ingested in sync, supporting more accurate data transformations. To learn more, see [Managing Data Sources](https://help.zscaler.com/unified/managing-data-sources). If you choose to schedule the run at the individual source level, Zscaler recommends setting an incremental refresh to run once daily, supplemented by a full refresh weekly, to ensure that automatic remediation detection takes effect.

See image.

For streaming AnySource connectors, scheduling is not required. These sources push data to the SecOps Platform in real time.

[Image: Scheduling settings during data source setup]

In the **Remediation Detection Settings** section, configure finding aging to set when findings automatically turn undetected.

- **Aging Criteria**:
  - **Built-in Rule**: Enable the built-in aging rule to set a finding as **Undetected** immediately if it was not seen in the latest full refresh, but the asset on which the finding was found was seen with other findings in the latest full data refresh.
  - **Custom Rule**: Build a custom aging rule to support use cases that aren't covered by the fallback and built-in criteria. The aging criteria conditions apply to a specific data source (i.e., the asset must be seen on the same data source that the finding was not seen on). Custom remediation detection rules are applied on every data run. For incremental ingestions, only findings that are included in that specific run are evaluated and aged, and not all existing findings that were ingested in previous runs.
- **Fallback**: Enable the fallback rule to set a finding as **Undetected**based on the number of days that have passed since it was last seen. From the drop-down menu, select which run types the rule should be applied to (i.e., **Full data run**, **Full and incremental data run**). For incremental ingestions, only findings that are included in that specific run are evaluated and aged, and not all existing findings that were ingested in previous runs.

You can enable both the aging criteria and the fallback rule. These rules are evaluated with an OR relationship, and when a finding matches multiple criteria, it is set to **Undetected**according to the first applicable rule.

You can also enable the aging of findings through asset aging settings. When both remediation detection settings and asset aging are configured, they operate independently but in parallel. Findings are marked as **Undetected**when they meet the conditions of either the remediation detection criteria or when the asset they are in is aged. To learn more, see [Aging Assets](https://help.zscaler.com/uvm/aging-assets).

In the **Advanced Settings** > **Suppression Rules** section, define rules and conditions to exclude specific data or to include only a subset of the dataset before it enters the SecOps Platform.

Zscaler recommends configuring suppression rules only after ingesting data from the source at least once, to ensure that you can reference the correct field names when creating the rules. To learn more, see [Configuring Suppressions Rules](https://help.zscaler.com/unified/configuring-suppression-rules).

[Image: Process Now icon on the Sources page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/creating-outegrations","lastmod":"2026-08-02T00:11Z","nid":"1541968"} -->
## Creating Outegrations

- Source: https://help.zscaler.com/unified/creating-outegrations
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Outegration Configuration > Creating Outegrations
- Last modified: 2026-08-02T00:11Z
- Summary: How to create a new outegration in the Security Operations Platform.

The Security Operations Platform (SecOps Platform) allows you to create outbound integrations (i.e., outegrations) that connect the SecOps Platform to external systems such as work management tools (e.g., [Jira](https://help.zscaler.com/uvm/configuring-jira-outegration), [ServiceNow](https://help.zscaler.com/uvm/configuring-servicenow-outegration)), storage destinations, alert systems (e.g., Slack, email), automation tools, scanners (e.g., [Tenable](https://help.zscaler.com/uvm/configuring-tenable-outegration)), and other supported third-party services. Where supported, the SecOps Platform can synchronize updates between the systems. For information on managing existing outegrations, see [Managing Outegrations](https://help.zscaler.com/unified/managing-outegrations).

To learn more about deployment prerequisites, setup considerations, and planning guidance, see [Deploying Outegration Configurations](https://help.zscaler.com/unified/deploying-outegration-configurations). To identify the available outegrations, see [Security Operations Platform Configurations by Outegration](https://help.zscaler.com/unified/security-operations-platform-configurations-outegration).

To create an outegration:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Outegrations**. A list of all existing outegrations appears.
2. Click**Create**.
3. Select an outegration from the available tiles. You can also search for an outegration in the search field. The outegration setup wizard appears.
4. In the outegration setup wizard, configure the setup in the following steps:
  1. **Connect**: Enter the outegration details. Depending on the outegration type, this step includes selecting an [authentication](https://help.zscaler.com/uvm/configuring-authentications) method to communicate with the external system.
  2. **Settings**: Configure the outegration settings. All outegrations require selecting an entity to create the outegration from. Some types might include additional settings (e.g., work management outegrations allow you to configure the Create Ticket button).
  3. **Mapping**: Configure the outegration mapping to define how data is exchanged and synchronized between the two systems.
5. Click **Finish** to save the outegration.

The new outegration appears on the Outegrations page and becomes available in the relevant SecOps Platform workflows. If the outegration supports bidirectional synchronization, you might also need to configure the associated webhook by using the applicable [vendor-specific guide](https://help.zscaler.com/unified/security-operations-platform-configurations-outegration).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/customizing-branding","lastmod":"2026-06-14T21:36Z","nid":"1500616"} -->
## Customizing Branding

- Source: https://help.zscaler.com/unified/customizing-branding
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Administration > Account Management > Customizing Branding
- Last modified: 2026-06-14T21:36Z
- Summary: How to customize the logo and email subject in the Zscaler Admin Console.

Branding allows you to add your organization's logo and customize the email that users receive to access the Zscaler Admin Console. The custom logo appears on the login, logout, and password reset pages. The email that Zscaler sends is rebranded and sent from the custom email address.

Only admins with Full permissions can customize the logo and email address.

To customize the logo and email:

1. Go to **Administration** > **Account Management** > **Branding**. The **Branding** page appears with the default Zscaler logo.
2. Click **Edit Logo** to upload a different image.
3. Browse to the required folder and upload the image file in any required format. The new logo is displayed on the page. This logo appears on the login and logout pages, and in emails. The image size should not exceed 70 KB.
4. In case you want to revert the changes, click **Delete**.
5. Under **Customize Email Subject**, change the email subject line for the following, if required: The maximum length of the subject line is limited to 70 characters. See image.
  - **One-Time Code for Email Change**
  - **One-Time Code for Password Reset**
  - **One-Time Code for Account Reset**
  - **Email OTP for Authentication**
  - **New User Setup Link**
  - **One-Time Code for MFA Enrollment**
  - **New Service Enabled for Tenant**
  - **New Zscaler Service Account Created**
  - **End User Migration**
  - **SAML Cert Expiry Notification**
  - **SAML Cert Expired Notification**
6. Click **Save**.

[Image: Customize the logo and email subject per your organization's needs]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/customizing-your-account-settings","lastmod":"2026-06-08T17:33Z","nid":"1498541"} -->
## Customizing Your Account Settings

- Source: https://help.zscaler.com/unified/customizing-your-account-settings
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Customizing Your Account Settings
- Last modified: 2026-06-08T17:33Z
- Summary: How to customize your account settings in the Zscaler Admin Console.

Zscaler automatically creates a profile for each account in the Zscaler Admin Console where you can change your display language, time zone, password, and default Zscaler cloud.

To view and change your account settings:

1. In the top navigation, click the **Account Settings** icon to display the following items: See image.
  - **Login ID**: The ID you use to log in to the Zscaler Admin Console.
  - **Organization ID**: The unique identifier of your organization.
  - **Private Access Microtenant**: If your organization has multiple [private access Microtenants](https://help.zscaler.com/zpa/about-microtenants), you can select the one you want from the drop-down menu, or use the default.
  - **Zscaler Cloud**: Select the Internet & SaaS (ZIA) cloud that has been provisioned for your organization. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names).
  - **Private Access Cloud**: Select the Private Access (ZPA) that has been provisioned for your organization. To learn more, see [Understanding Zscaler Cloud Names](https://help.zscaler.com/unified/understanding-zscaler-cloud-names). When you change clouds, you only see the entitlements granted to you on that cloud. This might affect the menu options available to you within the Zscaler Admin Console.
  - **Account Settings**: Click to edit your account settings.
  - **Sign Out**: Click to sign out of the Zscaler Admin Console.
2. When you click **Account Settings**, you can see your login ID, your organization's unique identifier, and the Zscaler cloud you are currently using.
3. Under **Settings**, you can change the following options: See image.
  - **Language**: In some locations, you can change the user interface to display one of the following languages:
    - English (US)
    - Chinese (Traditional)
    - French
    - German
    - Japanese
    - Spanish
  - **Time Zone**: Change your local time zone. When the Zscaler service saves transactions, it uses UTC (Coordinated Universal Time). The time zone is used only for exporting logs to CSV files. It does not affect the generated logs in the Zscaler Admin Console because the system time zone determines the time stamp of the generated logs.
  - **Password**: Click **Change Password** to open a window to change your Zscaler Admin Console password. The Zscaler service applies the strongest restrictions and complexity. The password must contain at least 8 characters and include 1 number, 1 uppercase character, and 1 special character. Only ASCII characters are allowed.
  - **Theme**: Choose the display setting to apply to the Zscaler Admin Console for your account:
    - **Light Theme**: The light mode display theme is always used.
    - **Dark Theme**: The dark mode display theme is always used.
    - **System Theme**: Applies your device's system display theme preference.
  - **Default Zscaler Cloud**: Select the Internet & SaaS cloud you want to log in to by default.
  - **Default Private Access Cloud**: Select the Private Access cloud you want to log in to by default. The drop-down menu is displayed if there is only one cloud that has been provisioned for your organization. When you change clouds, you only see the entitlements granted to you on that cloud. This might affect the menu options available to you within the Zscaler Admin Console.
4. Click **Save.**

[Image: Account Settings menu]

[Image: Account Settings page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/deploying-data-source-configurations","lastmod":"2026-08-02T00:25Z","nid":"1541606"} -->
## Deploying Data Source Configurations

- Source: https://help.zscaler.com/unified/deploying-data-source-configurations
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Deploying Data Source Configurations
- Last modified: 2026-08-02T00:25Z
- Summary: Information about deploying data source configuration in the Security Operations Platform.

The Security Operations Platform (SecOps Platform) collects and correlates security data and business context from a wide array of external tools, including vulnerability scanners, asset inventories, and cloud platforms. Connecting these external sources to your account establishes a continuous Extract, Transform, and Load (ETL) pipeline, ensuring raw telemetry is normalized and made available across your licensed SecOps Platform applications.

SecOps Platform data sources range from API-driven cloud services and Endpoint Protection Platforms (EPP) to Identity and Access Management (IAM) directories, SIEMs, and other third-party tools. If a data source is not available for a specific tool, you can use the [AnySource connector](https://help.zscaler.com/uvm/connecting-anysource) to ingest proprietary or unsupported data feeds (e.g., through an [AWS S3 bucket](https://help.zscaler.com/uvm/connecting-anysource-using-aws-s3)).

The specific data sources you need depend on the external tools you work with and your licensed SecOps Platform applications:

- Unified Vulnerability Management (UVM): Requires telemetry from vulnerability scanners and Cloud Security Posture Management (CSPM) tools to correlate vulnerabilities with asset context and drive remediation workflows.
- Asset Exposure Management (AEM): Requires telemetry from CMDBs (e.g., ServiceNow), IT asset management tools, and cloud inventories to maintain a comprehensive, continuous view of your asset inventory, coverage, and attack surface; EASM augments this by discovering internet-facing assets.
- Identity Protection: Requires signals from IAM directories (e.g., Active Directory, Entra ID, Okta) and endpoint telemetry (via Zscaler Client Connector) to detect risky identity posture, anomalous behavior, and exposed credentials.
- SOC Workbench: Ingests and correlates alerts from EDR, identity, and Zscaler network telemetry (e.g., ZIA), enriching them with asset and identity context to triage and investigate threats.

To learn more about the available data sources, see [Security Operations Platform Configurations by Data Source.](https://help.zscaler.com/unified/security-operations-platform-configurations-data-source)

## Prerequisites

Before configuring a data source in the SecOps Platform, verify the following prerequisites:

- Vendor Administrative Access: Confirm you have sufficient privileges within the third-party vendor's console to generate authentication credentials (e.g., API tokens, OAuth keys) and define access scopes. Depending on the vendor, this might require full administrative access or specific role-based permissions.
- Licensing & Permissions: Verify that your third-party license permits data export or API access. Ensure the credentials you generate possess the correct read-only permissions for the targeted data streams.
- Network & API Limits: Determine if the vendor requires [SecOps Platform public IP addresses](https://help.zscaler.com/uvm/zscaler-secops-public-ip-addresses) to be allowlisted. Review API rate limits that might impact large-scale data extractions.
- Security & Secret Management: All API keys, secrets, and tokens entered into the SecOps Platform are securely encrypted. Ensure you have a secure method for transferring these credentials from the vendor portal to the SecOps Platform during setup.

## Standard Deployment Flow

Deploying a data source as a fully operational, continuously syncing configuration involves a defined sequence of setup and validation steps. Zscaler recommends following this standard deployment flow:

1. Collect required parameters from the vendor: Before configuring the data source in the SecOps Platform, log in to your third-party vendor's portal. Configure the necessary access permissions, allowlist IP addresses if required, and generate the authentication credentials. To learn more, see [Source Configuration Guides](https://help.zscaler.com/uvm/administration/connectors/sources/source-configuration-guides).
2. Create the data source: Log in to the SecOps Platform and create the new source. You will input the credentials gathered previously to establish a secure handshake, and select the specific data streams (e.g., Assets, Vulnerabilities, Alerts) you want to ingest. To learn more, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).
3. Run the data source: After the source is created, you must trigger the initial extraction. Running the source prompts the platform to reach out to the vendor and pull in the first batch of raw telemetry. To learn more, see [Managing Data Sources](https://help.zscaler.com/unified/managing-data-sources#processing).
4. Map your data source: Because every third-party vendor labels their data differently, you must map the ingested external fields to the platform’s standardized data model (for example, telling the platform that the vendor's `host_name` field equals the platform's `Asset Name` field). To learn more, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources).
5. Rerun the data source: After your field mappings are saved, run the data source a second time. This applies your mapping rules to the extracted data, completing the transformation process. Once this is done, your data source is fully operational and will enter a state of continuous, scheduled synchronization. To learn more, see [Managing Data Sources](https://help.zscaler.com/unified/managing-data-sources#processing).
6. (Recommended) Set scheduling: Use Auto‑Scheduling to coordinate source runs as a single process for consistent, in‑sync ingestion across related sources; it’s enabled by default and can be adjusted to your data windows. If you schedule at the individual source level, follow the platform's guidance for full and incremental refresh cadence. To learn more, see [Managing Data Sources.](https://help.zscaler.com/unified/managing-data-sources#auto-scheduling-settings)

## Maintenance and Monitoring

After a data source is successfully deployed, you should monitor source activity to verify that telemetry is being retrieved and processed as expected. Ongoing monitoring helps ensure successful ingestion, supports accurate correlation across data sources, and helps identify issues before they affect downstream workflows.

You can monitor source activity by reviewing source run history, validating completed runs, and investigating failed or canceled runs. Run details can help identify issues such as invalid credentials, missing permissions, or other configuration problems. To learn more, see [Tracking Data Source Runs](https://help.zscaler.com/unified/tracking-data-source-runs).

To reduce the need for manual monitoring, you can enable email notifications for failures related to source runs and ETL processing. To learn more, see [Enabling Email Notifications for Errors](https://help.zscaler.com/uvm/enabling-email-notifications-failures).

If a source behaves unexpectedly, you can also review audit logs to track changes to data source instances, mappings, and scheduling settings. Audit logs are useful for troubleshooting and confirming when configuration changes were introduced. To learn more, see [Viewing Audit Logs](https://help.zscaler.com/uvm/viewing-audit-logs).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/deploying-outegration-configurations","lastmod":"2026-08-02T00:23Z","nid":"1541895"} -->
## Deploying Outegration Configurations

- Source: https://help.zscaler.com/unified/deploying-outegration-configurations
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Outegration Configuration > Deploying Outegration Configurations
- Last modified: 2026-08-02T00:23Z
- Summary: Information about deploying outegration configuration in the Security Operations Platform.

The Security Operations Platform (SecOps Platform) enables you to send security findings, alerts, remediation tasks, and operational context to a wide array of external tools, including ticketing systems, work management platforms, cloud storage services, streaming platforms, and other third-party systems. Connecting these external destinations to your account establishes outbound workflows that move SecOps Platform data and actions into the systems where security, IT, and engineering teams already work.

SecOps Platform outegrations range from ticketing and workflow connectors to storage and streaming destinations. Depending on the connector, outegrations can dispatch tasks, export data, trigger supported third-party actions, and synchronize updates between the SecOps Platform and external systems.

The specific outegrations you need depend on the external tools you work with and the applications available in your account:

- Unified Vulnerability Management (UVM): Uses outegrations to send remediation tasks to external ticketing and workflow systems and to support validation workflows through supported third-party tools.
- Asset Exposure Management (AEM): Uses outegrations to route asset exposure findings and related operational context into external systems so teams can track and remediate issues within established workflows.

To learn more about the available outegrations, see [Security Operations Platform Configurations by Outegration.](https://help.zscaler.com/unified/security-operations-platform-configurations-outegration)

## Prerequisites

Before configuring an outegration in the SecOps Platform, verify the following prerequisites:

- Confirm you have sufficient privileges within the third-party vendor's console to generate authentication credentials (e.g., API tokens, OAuth keys, or service accounts) and create or manage the destination objects used by the outegration. Depending on the vendor, this might include defining projects, queues, topics, callback URLs, or other destination settings.
- Verify that your third-party license permits the required API access, record creation, status updates, data export, or action triggers. Ensure the credentials you generate possess the correct scopes and read or write permissions for the intended workflow, including bidirectional synchronization where supported.
- Determine if the vendor requires [SecOps Platform public IP addresses](https://help.zscaler.com/uvm/zscaler-secops-public-ip-addresses) to be allowlisted, or specific endpoints to be reachable. Review API rate limits, payload limits, and any workflow or event-processing limits that might impact high-volume outegrations.
- All API keys, secrets, and tokens entered into the SecOps Platform are securely encrypted. Ensure you have a secure method for transferring these credentials from the vendor portal to the SecOps Platform during setup.

## Standard Deployment Flow

Deploying an outegration as a fully operational configuration involves a defined sequence of setup and validation steps. Zscaler recommends following this standard deployment flow:

1. Collect required parameters from the vendor: Before configuring the outegration in the SecOps Platform, log in to your third-party vendor's portal. Configure the necessary permissions, allowlist IP addresses if required, generate the authentication credentials, and identify the destination settings the outegration uses. Depending on the vendor, this might include projects, queues, buckets, topics, callback URLs, or other target objects. To learn more, see [Outegration Configuration Guides](https://help.zscaler.com/uvm/administration/connectors/outegrations/outegration-configuration-guides).
2. Configure the authentication: In the SecOps Platform, create the authentication required for the outegration or select an existing one. This establishes the secure connection between the platform and the third-party system. To learn more, see [Configuring Authentications](https://help.zscaler.com/uvm/configuring-authentications).
3. Create and configure the outegration: Create the outegration in the SecOps Platform, select the appropriate authentication, define the destination settings, configure any visibility or behavior options, and map SecOps Platform fields to the destination system's schema. Depending on the outegration, this configuration can also define how records are dispatched and how synchronized updates are handled. To learn more, see [Creating Outegrations](https://help.zscaler.com/unified/creating-outegrations) and the applicable guide in [Outegration Configuration Guides](https://help.zscaler.com/uvm/administration/connectors/outegrations/outegration-configuration-guides). If needed, you can deactivate an outegration to temporarily stop outbound activity or synchronization without removing its configuration. This is useful during maintenance windows, troubleshooting, or external system changes.
4. Test and validate the outegration: Test the configuration to verify that records, actions, or updates are sent successfully to the third-party system and that the configured mappings behave as expected.
5. (Optional) Configure the webhook: If the outegration supports bidirectional synchronization, configure the webhook required to receive updates from the third-party system. This enables the SecOps Platform to process external changes, such as status updates or other synchronized events.

After the outegration is saved and tested, and any required webhook is configured, it becomes available in the relevant SecOps Platform workflows. Users can then send supported items to the external system, and any configured synchronization behavior can begin processing updates between the platforms.

## Maintenance and Monitoring

After an outegration is successfully deployed, you should monitor outegration activity to verify that records, actions, and synchronized updates are being processed as expected. Ongoing monitoring helps ensure successful outbound workflows, supports ongoing synchronization, and helps identify issues before they affect downstream processes.

You can monitor outegration activity by reviewing activity logs and investigating failed or incomplete operations. Activity log details can help identify issues such as invalid credentials, missing permissions, or field mapping problems. If the external system's schema changes, you can also update the outegration configuration and mappings as needed. To learn more, see [Managing Outegrations](https://help.zscaler.com/unified/managing-outegrations).

To reduce the need for manual monitoring, you can enable email notifications for outegration failures. To learn more, see [Enabling Email Notifications for Errors](https://help.zscaler.com/uvm/enabling-email-notifications-failures).

If an outegration behaves unexpectedly, you can also review audit logs to track changes to outegration configurations, mappings, and related settings. Audit logs are useful for troubleshooting and confirming when configuration changes were introduced. To learn more, see [Viewing Audit Logs](https://help.zscaler.com/uvm/viewing-audit-logs).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/downloading-risk-reports","lastmod":"2026-07-08T23:34Z","nid":"1526906"} -->
## Downloading Risk Reports

- Source: https://help.zscaler.com/unified/downloading-risk-reports
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Downloading Risk Reports
- Last modified: 2026-07-08T23:34Z
- Summary: Information about how to download various risk reports in the Admin Portal.

You can download various risk-related reports from the Reports page (Analytics > Risk360 > Reports). The page contains the following reports and documents:

- **CISO Board Slides**: The slides provide customers with extensive insight into how Risk360 is helping quantify and measure risk in their network. A new slide is generated for a period of 7 days. The reports are securely stored as a PowerPoint file.
- **Attack Surface Report**: A report that provides you with the details of your organization's exposed applications and servers to a public network, such as the internet, and their possible exploitation.
- **Cybersecurity Maturity Assessment**: A report that provides a holistic view of your zero trust journey. It is generated by a custom large language model (LLM) developed by Zscaler.
- **SEC Disclosures**: A document with a sample format and content that can be a helpful starting point for security and legal teams in addressing the Securities and Exchange Commission's (SEC's) new cyber risk reporting regulations.
- **Miscellaneous**: Various materials on risk management, including books published on how board members can manage cyber risk and how translating cybersecurity into financial terms enables strategic decision-making.
- **Risk Management Mitigation Strategy Report**: A report that outlines the mitigation strategy for important risk factors impacting your organization's risk. This report is autogenerated once a week.
- **Operating Entity Risk**: A weekly and on-demand report for portfolio risk assessment by quantifying risk at the operating entity level.
- **Cyber Insurance Addendum**: A comprehensive quarterly and on-demand report of your zero trust security posture for cyber insurance underwriters.

Click **View All** to view all the reports for a specific category.

[Image: Reports page in the Risk360 Admin Portal.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/editing-newly-onboarded-users","lastmod":"2026-07-27T04:54Z","nid":"1498456"} -->
## Editing Newly Onboarded Users

- Source: https://help.zscaler.com/unified/editing-newly-onboarded-users
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Onboarding New Users > Editing Newly Onboarded Users
- Last modified: 2026-07-27T04:54Z
- Summary: How to edit and delete users created while onboarding to Experience Center.

After you have [onboarded users](https://help.zscaler.com/unified/onboarding-users-experience-center) to Experience Center, you can edit the user details or delete users before completing the onboarding process.

At least one user in your organization must be a Full Admin. If you delete or remove admin privileges from the only Full Admin user, you will be prompted to select a different user to become a Full Admin.

Post the onboarding process, you can update user details by going to the Users page (Administration > Identity > Authentication Service > Users) in the Zscaler Admin Console. To learn more, see [Adding Users](https://help.zscaler.com/authentication-service/adding-users).

To edit the user details:

After you have imported, uploaded, or added a user, the **Users**page is displayed, showing the list of users. The list can consist of multiple pages.
See image.

1. On the **Users** page, click the **Edit**icon next to a user to change the user's name, email address or change the user role between End User or Full Admin.
2. Click **Save**. During onboarding, you can only [assign users to either the Full Admin or End User role](https://help.zscaler.com/unified/setting-up-secure-access#add-users). However, after you complete onboarding, any Full Admin can assign users to other admin roles in the Zscaler Admin Console. To learn more about Internet & SaaS (ZIA) roles, see [Adding Admin Roles](https://help.zscaler.com/zia/adding-admin-roles). To learn more about Private Access (ZPA) roles, see [About Roles](https://help.zscaler.com/zpa/about-private-access-roles). To learn more about Zscaler Client Connector roles, see [Adding Roles](https://help.zscaler.com/zscaler-client-connector/adding-roles).
3. Click the **Delete**icon next to a user you want to remove. You cannot delete individual users that were imported from an IdP.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/editing-or-deleting-adaptive-access-profile","lastmod":"2026-02-11T06:47Z","nid":"1515906"} -->
## Editing or Deleting an Adaptive Access Profile

- Source: https://help.zscaler.com/unified/editing-or-deleting-adaptive-access-profile
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Policies > Adaptive Access Engine > Editing or Deleting an Adaptive Access Profile
- Last modified: 2026-02-11T06:47Z
- Summary: How to edit or delete and Adaptive Access profile in Experience Center.

You can review the Adaptive Access profiles over a period of time for their effectiveness, and modify or delete the profiles as required.

## Editing an Adaptive Access Profile

To edit an Adaptive Access profile:

1. Go to **Policies** > **Common Configuration** > **Adaptive Access** > **Profiles**.
2. On the **Profiles** page, click the **Edit** icon for the required profile. See image.
3. In the **Edit Profiles** window, modify the values as required. See image.
4. Click **Update**.

## Deleting an Adaptive Access Profile

To delete an Adaptive Access profile:

1. Go to **Policies** > **Common Configuration** > **Adaptive Access** > **Profiles**.
2. On the **Profiles** page, click the **Delete** icon for the required profile. See image.
3. In the **Confirmation** window, enter `YES`, then click **Delete**. See image.

[Image: Change the profile details]

[Image: Delete a profile]

[Image: Click the Edit icon]

[Image: Click the Delete icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/enabling-remote-assistance","lastmod":"2026-07-23T11:55Z","nid":"1535138"} -->
## Enabling Remote Assistance

- Source: https://help.zscaler.com/unified/enabling-remote-assistance
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Enabling Remote Assistance
- Last modified: 2026-07-23T11:55Z
- Summary: How to give Zscaler Support engineers view-only or full admin access to the Zscaler Admin Console.

At times, Zscaler Support might need access to your organization's account for a limited period to troubleshoot issues. With Remote Assistance, you can allow Zscaler Support to securely and remotely log in to your Zscaler Admin Console. This can be done with either view-only or full admin privileges. You do not need to create new admin accounts or share passwords to enable access for either option.

Only admin users with [Remote Assistance Management](https://help.zscaler.com/zia/adding-admin-roles) enabled for their role can make changes in the **Remote Assistance** window.

To enable remote assistance:

1. Log in to the Zscaler Admin Console.
2. Click the **Help**icon and then select the **Tools & Resources** tab.
3. Click **Remote Assistance**. In the **Remote Assistance** window:
  - **Allow Remote Assistance**: Enable to turn on remote assistance, then choose the access you want to give to Zscaler Support. You can choose one or both of the following options:
    - **View-Only**: Select this option to allow Zscaler Support to be able to view your screen, but not have access to make changes in the Zscaler Admin Console.
      - **Access Ends On**: Enter a date within 90 days. After this date, view-only access by Zscaler Support automatically expires.
    - **Full Access (Read/Write)**: Select this option to allow Zscaler Support to read data and make changes in the Zscaler Admin Console.
      - **Access Ends In**: Select a time, after which full access by Zscaler Support automatically expires.
  - **Obfuscate Data:**Enable to conceal user names, data, and device information (e.g., device name, hostname, and owner) from Zscaler Support. To learn more about user name obfuscation, see [Obfuscating User Names for Admins](https://help.zscaler.com/zia/obfuscating-user-names-admins).
4. Click **Save**. See image.
5. After Remote Assistance is enabled, the Zscaler Admin Console displays a message alerting users that Remote Assistance is enabled until the access time expires or Remote Assistance is disabled. See image.

[Image: Remote Assistance options.]

[Image: Remote Assistance options.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/executive-insights-app-errors-and-troubleshooting","lastmod":"2026-02-12T08:50Z","nid":"1520876"} -->
## Executive Insights App Errors and Troubleshooting

- Source: https://help.zscaler.com/unified/executive-insights-app-errors-and-troubleshooting
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Executive Insights App > Executive Insights App Errors and Troubleshooting
- Last modified: 2026-02-12T08:50Z
- Summary: Information on common errors that might occur in the Executive Insights App and the respective troubleshooting steps.

The following tables provide lists of error messages users might see on the Executive Insights App while the app is in use and the steps to resolve those errors:

- User Authentication Issues
- Connectivity and Network Problems
- Data Visibility and Accuracy
- Subscription and Access Control
- App Functionality Issues
- Content and Guidance Issues

To learn how to install and use the Executive Insights App, see [Accessing and Using the Executive Insights App](https://help.zscaler.com/unified/accessing-and-using-executive-insights-app).

| Error Type | Error Message or Issue Description | Resolution |
| --- | --- | --- |
| Invalid Email Address | The user gets the following error message on the Confirmation Code screen: Email Address is not valid. Contact support if you have any further questions. | In the Zscaler Admin Console, ensure that the user exists and is assigned a role with permission to access the Executive Insights App. |
| Invalid Email Entry | The user gets the following error message on the Login screen: You have entered an invalid email. Please try again. | The user must enter their correct email address. |
| Missing Confirmation Code Email | The user is waiting on the Confirmation Code screen but has not received an email containing the confirmation code. | Check whether the countdown timer "Resend in XX seconds" has started on the Confirmation Code screen. If you see the countdown timer, it implies that the API call to send the confirmation code was successful and that the email had been sent from the back end to the user. To resolve this issue further: The user must check their spam folder for emails with the confirmation code.; If the user has not received the Confirmation Code email, the user must click the **Resend Code** button, wait until the countdown timer starts, and then check their email inbox for a new email with the code. |
| Invalid Confirmation Code | The user has entered the confirmation code but the following error message displays: Validation failed. Please re-enter the correct confirmation code or have a new one sent. | To resolve this issue: The user must verify that the confirmation code entered is correct.; The user clicks **Resend Code** to generate a new confirmation code. It is recommended that users wait to receive the confirmation code instead of clicking the **Resend Code** button multiple times. Generating multiple codes within a short span might lead to using the wrong code. In such cases, the user must close and reopen the app to initiate the login process. |
| Contact IT Admin for Access | Some users can log in to the app successfully but get the following error: Contact your IT admin to provide you access to a tenant. | This issue happens only in the case of Authentication Service-based authentication. If the user has been given a Authentication Service role with permission to access the Executive Insights App but has not been given access to the Internet & SaaS Administration Management for any tenant, ensure the following: In the Zscaler Admin Console, under Administration > Admin Management > Role Based Access Control > Internet & SaaS: The user exists.; The user has a role with permission to access the Executive Insights App. |
| Stuck on Select Tenant Screen | The user is able to log in to the app successfully but is unable to see their specific tenant organization on the Select Tenant screen. | In the Zscaler Admin Console of that specific tenant organization, under Administration > Admin Management > Role Based Access Control > Internet & SaaS, ensure that the user exists and is assigned a role with permission to access the Executive Insights App. |

| Error Type | Error Message or Issue Description | Resolution |
| --- | --- | --- |
| No Internet Connection | The following error messages are shown on the Login screen: No internet connection. Make sure Wi-Fi or cellular data is turned on. Then try again.; We are unable to proceed with the next steps due to a connectivity issue. Please check your connection and ensure all the necessary applications are active. | Ensure that proper network connectivity is available on the phone. |
| OTP Connection Issue | The following error message is shown on the Confirmation Code screen after entering a one-time password (OTP): Connection issue detected. Please check your network connection. If you are using Zscaler Client Connector, ensure you're successfully authenticated for Private Access. | If you are using Zscaler Client Connector on the phone, ensure that Private Access on Zscaler Client Connector is turned on and that authentication has not expired. |
| Error Loading Screens or Widgets | A loading error displays in screens and widgets. | To resolve this issue: Check whether proper network connectivity is available on the phone.; If Zscaler Client Connector is installed on the phone, ensure that Private Access on Zscaler Client Connector is turned on and that authentication has not expired. |
| Poor App Performance | The loading screen displays for a longer duration after successful login. | To resolve this issue: Check whether proper network connectivity is available on the phone.; If Zscaler Client Connector is installed on the phone, ensure that Private Access on Zscaler Client Connector is turned on and that authentication has not expired.; Check that the network bandwidth on the phone is optimal.If you find no issues, close the app, clear the app data, and start again. The app prefetches all the data and stores it in the cache on startup. The app performance should improve after the prefetch is complete. |

| Error Type | Error Message or Issue Description | Resolution |
| --- | --- | --- |
| No Data in Widgets | The following message displays on some widgets: No Data | Try changing the filters. For example, you can change the time range selection (Last 7 Days, Last 14 Days, etc.) or any widget-specific filter. If the error message still displays, no further action is required. It is possible that no data is available for that tenant for the selected time range. The data would start showing up when it becomes available. |
| Wrong Data in Widgets | The data on the Executive Insights App does not match with the data in the Zscaler Admin Consoles of Internet & SaaS, Private Applications, or Digital Experience Monitoring. | To resolve this issue: Verify that the user has selected the correct tenant on the Profile screen.; Close the app, clear the app data, and restart the app. This sequence forces the latest data to be fetched from the back end.; Confirm that the user is not using the Demo mode, as it contains mock data. |
| Stale Data in Widgets | The app is not showing the latest data. | To fetch the latest data, close the app, clear the app data, and restart the app. This sequence forces the latest data to be fetched from the back end. The Executive Insights App data refreshes after a day (i.e., the data for today is visible only tomorrow). |
| Stale Data in Widgets (includes timing discrepancies) | When the Last X Days filter is applied, data older than two days is shown instead of data until the previous day. | Data refreshes once a day starting at the UTC day boundary (00:00 UTC). Users in UTC+ time zones continue to see data that is two days older until their local time crosses the UTC day boundary. For example, in India (UTC+05:30), users cannot see data for the previous day until 05:30 hrs (Indian time). Additionally, even after the UTC boundary is crossed, it might take a few hours for the data to be processed and analyzed before being made available to users. For instance, if data processing takes approximately two hours, users in India might not see the previous day’s data until around 07:30 hrs (Indian time). Note that this is an illustrative example, and the processing time can vary depending on system load and other factors. |
| Missing Time Range Selection on Screens | The Time Range is not available in: The Risk360 screen; The Experience screen | Digital Experience Monitoring and Risk360 products support only the most current data and fixed time ranges. Hence, the app does not provide any option to select time ranges for these screens. |

| Error Type | Error Message or Issue Description | Resolution |
| --- | --- | --- |
| Subscription Required Message | The user sees "Subscription Required" message on some of the widgets instead of the actual data. | Widgets are rendered based on the subscriptions included for the user's tenant. To check the Digital Experience Monitoring subscriptions for the tenant, go to the Profile screen and check the bottom line. |
| Demo Mode on Risk360 Screen | No data displays on the Risk360 screen, and only an option to start the Demo mode is available. | The tenant must be provisioned on Risk360 before any data for that tenant would start showing up on the Risk360 screen. Until then, the tenant users can only see the Demo mode. |

| Error Type | Error Message or Issue Description | Resolution |
| --- | --- | --- |
| Sandbox Threat Widget Behavior | The month value on the Sandbox Threat widget is not always selectable. | Zscaler Sandbox threats data is aggregated monthly, which happens at the end of the month. If you select the **Last 7**, **Last 14**, or **Last 30 Days** filter, you can only see last month's data. When you select **Last 90 Days**, you can select any of the past three months for which you want to see the data. You can see the data only for those months when some threats were detected. There might not have been any threats in a month, in which case that specific month does not show up on the selection. |
| Stuck on Loader for an Extended Time | After successfully logging in, the user is unable to select the tenant on the Select Tenant screen and an error is shown. | The app tries to determine the subscriptions included for the tenant on the Select Tenant screen, which might fail due to network connectivity issues or reachability of the API endpoint. To resolve this issue: Check the network connectivity from the user's phone. If the user is using Zscaler Client Connector on the phone, then make sure that Private Access is turned on and that authentication has not expired.; In rare cases, if there are reachability issues with the API endpoint, the user must retry selecting the tenant. |

| Error Type | Error Message or Issue Description | Resolution |
| --- | --- | --- |
| Unclear Content on Screens | The data shown on the screen is not understandable. | Refer to the [documentation](https://help.zscaler.com/unified/accessing-and-using-executive-insights-app). |
| Outdated News Content | Only a few news items display, and the latest news content is not shown for many days. | News items are published periodically across Zscaler's official communication channels, such as [News and Announcements](https://www.zscaler.com/company/news-press), [Blog](https://www.zscaler.com/blogs), and Zscaler Trust Portal. Whenever new content is made available, it shows up on the Executive Insights App. |
| Call-to-Action Items Not Fully Covered | For the news items in the For You section, under Threats Detected, the status Partially Covered or Not Covered displays. | To ensure complete coverage and enhanced protection, we recommend reaching out to the Zscaler Account team. They can help assess your environment, identify any issues, and provide the necessary solutions to fully protect your organization against emerging threats. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/exploring-risk-problems","lastmod":"2026-02-11T06:35Z","nid":"1526916"} -->
## Exploring Risk Problems

- Source: https://help.zscaler.com/unified/exploring-risk-problems
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Exploring Risk Problems
- Last modified: 2026-02-11T06:35Z
- Summary: Information on how to explore a risk problem on the Explore page in the Admin Portal.

The Explore page (Analytics > Risk360 > Insights > click the Explore link on a problem card) shows in-depth analysis of a problem by presenting multiple sections highlighting the affected areas within your organization. The following screenshot illustrates an example problem analyzed on the Explore page. You can scroll down to view all the sections available for that problem.

[Image: The Explore page in the Risk360 Portal]

Click the Investigate link available for each section and you're redirected to the [Investigate](https://help.zscaler.com/unified/investigating-sections-risk-problem) page where you can view detailed data for the sections in a tabular form.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/exploring-source-data-mapping","lastmod":"2026-07-29T03:50Z","nid":"1541965"} -->
## Exploring Source Data in Mapping

- Source: https://help.zscaler.com/unified/exploring-source-data-mapping
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Exploring Source Data in Mapping
- Last modified: 2026-07-29T03:50Z
- Summary: How to explore the source fields during data source mapping.

When [mapping a data source](https://help.zscaler.com/unified/mapping-data-sources) in the Security Operations Platform (SecOps Platform), you can explore the ingested source data to better understand its structure, values, and quality. This helps you accurately align vendor-provided fields with the SecOps Platform data model (e.g., choosing fields that are suitable as unique identifiers or keys).

Exploring source data gives you direct visibility into the raw values from the most recent successful ingestion run. By reviewing actual values and their distribution, you can validate that your mapping logic accounts for common variations such as null or missing values, inconsistent date formats, and nested data structures.

The availability of source data for exploration depends on the source's run history. If the source has had a successful run within the last 90 days, the Explore window includes the full dataset from that run. Otherwise, the SecOps Platform displays a 50-record preview for exploration.

To explore source data:

1. In the SecOps Platform Admin Portal, go to **Configure**> **Sources**.
2. Open the mapping page using one of the following methods:
  - Hover over a source, and click the **Map Data** icon.
  - Select the checkbox of a data source from the list, and click**Map Data** on the toolbar.
3. On the Mapping page that appears, click **Explore Data**. See image. A window opens that displays your ingested data from the most recent successful run.

In the window, you can review real source values and confirm exact field names, including nested paths shown in dot notation, before you map them. You can customize the displayed columns in the window to add, remove, and reorder the displayed fields. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).

## Viewing Field Statistics

If the source has had a successful run within the last 90 days, you can use Explore to view field-level analytics across the full run. These statistics help you make informed mapping decisions, such as validating whether a field is suitable as a unique identifier (key), identifying data gaps (null or empty coverage), and spotting value patterns or inconsistencies that might require normalization or fallback logic.

You can also use Mapping Copilot to get AI-assisted insights and automated mapping suggestions based on your ingested source data. To learn more, see [Using Mapping Copilot](https://help.zscaler.com/unified/using-mapping-copilot).

To view field statistics:

1. Click **Explore**in the window toolbar. See image. The exploring statistics panel appears.
2. From the **Explore Values For**drop-down menu, select the field you want to explore. See image.
3. Review the statistics:
  - **Record Count**: The total number of records analyzed from the run. This helps you understand how consistent or duplicated the field is.
  - **Distinct Value**: The number of unique entries found in the selected field. This helps you understand how consistent or duplicated the field is.
  - **Value Coverage**: Null, empty, or populated coverage helps you identify missing-data patterns that can impact mapping logic.
  - **Value Distribution**: A common values histogram (ordered from most to least common) to quickly see dominant values and outliers. You can view the top 20, 50, 100, or 200 values.

After exploring the ingested source data, you can use your insights to map fields, define entity keys, or create transformation scripts. Return to the mapping editor and finish your configuration. To learn more, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources).

[Image: Explore Data in source mapping]

[Image: Exploring source data statistics]

[Image: Exploring source data statistics panel]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/integrating-crowdstrike-adaptive-access-engine","lastmod":"2026-06-26T02:03Z","nid":"1508496"} -->
## Integrating CrowdStrike with Adaptive Access Engine

- Source: https://help.zscaler.com/unified/integrating-crowdstrike-adaptive-access-engine
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Policies > Adaptive Access Engine > Integrating CrowdStrike with Adaptive Access Engine
- Last modified: 2026-06-26T02:03Z
- Summary: How to integrate CrowdStrike with the Adaptive Access Engine in the Experience Center.

The Adaptive Access Engine receives user and device context signals from CrowdStrike, which are required to control user and device access to applications via Zscaler. You can integrate CrowdStrike with Adaptive Access Engine to establish a connection and automate the event response workflows using webhooks and API client configurations. CrowdStrike shares the following context signals with the Adaptive Access Engine: CrowdStrike Operating System (OS) Score, Zero Trust Assessment (ZTA) Score, ZTA Sensor Score, and Overall Score.

This integration is in limited availability. Contact Zscaler Support for assistance. 
CrowdStrike has deprecated the Incidents functionality, so the Adaptive Access integration to ingest CrowdStrike Incidents is defunct. Make sure to remove the Incidents configuration.

## Prerequisites

Ensure that you have:

- A CrowdStrike account with admin privileges.
- An admin role in Experience Center that allows you to integrate CrowdStrike with Adaptive Access Engine.

To integrate CrowdStrike with Adaptive Access Engine:

- 1. Create an API client.
- 2. Configure the webhook.
- 3. Integrate CrowdStrike in Experience Center.
- 4. Configure the workflow.

After completing the integration, you can proceed to [add the Adaptive Access profiles](https://help.zscaler.com/unified/adding-adaptive-access-profile).

The CrowdStrike API client acts as a unique identifier and authentication mechanism, allowing the Adaptive Access Engine to securely access and interact with the CrowdStrike Falcon platform.

1. Log in to the [CrowdStrike Falcon console](https://falcon.crowdstrike.com/login/).
2. Go to **Support and resources** > **API Clients and Keys**> **Create** **API client**. See image.
3. In the **Create API Client** window, enter the**Client Name** and **Description**.
4. Select the following **API scopes** with Read permissions: Workflows, API integration scope, Hosts, and Zero Trust Assessment (ZTA). API scope defines the level of access granted to Adaptive Access Engine to access specific data when interacting with the CrowdStrike API. See image.
5. Click **Create**. The API client is created. See image.
6. Copy and save the **Client ID**, **Client Secret**, and **Base URL**. Make sure to copy the values because this information is not shown again. In case you don't copy the values, you need to create another API client.

1. Go to **Policies** > **Common Configuration** > **Adaptive Access** > **Integrations**.
2. Click the **Edit**icon for CrowdStrike. See image.
3. On the **Integrations** page, enter the following details:
  - **Base URL**: Enter the URL that was generated when the API client was created on the CrowdStrike Falcon console. This URL is required for Adaptive Access Engine to connect to the CrowdStrike account.
  - **Webhook URL**: The Webhook URL is generated after you enter the Base URL. Copy the Webhook URL, as you need to add this value while configuring the webhook on the CrowdStrike Falcon console. The webhook URL is the designated address where CrowdStrike can automatically send real-time context signals.
  - **Customer ID**: The customer ID is available on the **Profile** page on the CrowdStrike Falcon console:
    1. On the CrowdStrike home page, click the **Profile** icon in the top-right corner.
    2. Copy the **Customer ID** that is displayed on the **User profile** page. See image.
  - **Client ID**: Enter the client ID you copied after the API client was created on the CrowdStrike Falcon console.
  - **Client Secret**: Enter the client secret you copied after the API client was created on the CrowdStrike Falcon console.
  - **Shared Secret**: Enter the secret key you added while configuring the webhook.
  - **Status**: By default, the connection status is disabled. Click the toggle to enable the status.
4. Click **Test Integration** to verify if the connection is successful. A message appears indicating the connection between CrowdStrike and Adaptive Access Engine is successful. See image.
5. Click **Save**.

A webhook is required to send context signals from the CrowdStrike Falcon platform to Adaptive Access Engine.

1. Log in to the [CrowdStrike Falcon console](https://falcon.crowdstrike.com/login/).
2. Go to **CrowdStrike store** > **All apps** > **CrowdStrike Webhook**.
3. Click **Configure**. See image.
4. In the next window that appears, click **Add Configuration**.
5. On the **Configure CrowdStrike Webhook** page: See image.
  - **Name**: Enter a name for the webhook.
  - **Webhook URL**: Add the webhook URL you copied while integrating CrowdStrike in the Experience Center.
  - **HMAC Secret key**: Use any Hash-based Message Authentication Code (HMAC) generator tool to obtain a key and enter that value. The secret key is used to verify the authenticity and integrity of data exchanged between CrowdStrike and Adaptive Access Engine using the HMAC algorithm. Enter the same value in the Experience Center while integrating CrowdStrike.
6. Click **Save Configuration**.

A workflow is an automated action that is initiated by a predefined trigger (security event). The events can be defined in the workflow and these signals are sent to Adaptive Access Engine via the webhook plugin. You can create a Score Changed workflow.

1. Log in to the [CrowdStrike Falcon console](https://falcon.crowdstrike.com/login/).
2. Search for **Workflows | Fusin SOAR** and select it.
3. Click **Create workflow** > **Create workflow from scratch**, then click**Next**.
4. On the **Add trigger** page:
  1. **Pick a Trigger**: Search for and select the required event (e.g., Zero Trust Assessment > Host assessment change) that must initiate the workflow.
  2. **Type**: Select **Overall assessment** from the drop-down menu.
  3. Under **Event triggers**, choose the relevant event category based on your organization's requirements.
5. To define the workflow, click the arrow on the trigger. See image.
6. Next, click the **Action**icon. See image.
7. On the **Add action** page: See image. The webhook is added as an action and displayed on the page.
  1. Search for and select **Call webhook**.
  2. **Webhook name**: Select the webhook you created earlier. This webhook is used to send the context signals to Adaptive Access Engine.
  3. **Data format**: Select **Default**from the drop-down menu.
  4. **Data to include**: Select the required data that must be included in the action based on your organization's requirement.
  5. Click **Next**.
8. Click **Finish**. See image.
9. In the **Add workflow name and description** window, enter the **Name** and **Description**.
10. Select **On** for the **Workflow status**. See image.
11. Click**Save workflow**. Whenever there is a change in the trigger, the workflow calls the webhook and signals are sent to the Adaptive Access Engine.

[Image: The Integrations page displaying a table and an annotation around the Edit icon under the Actions column.]

[Image: The Integrations page displaying the different fields, and a list of signals and their value type.]

[Image: Click the Create API client button to add a new API client]

[Image: The Create API client window with fields, listed scopes, and Read and Write checkboxes.]

[Image: CrowdStrike Webhook description and a Configure button at the end.]

[Image: The API client's ID and secret are displayed]

[Image: View the user profile details]

[Image: The Configure CrowdStrike Webhook page displaying different fields and buttons to Cancel or Save configuration.]

[Image: Trigger and three sequential options under it with annotation around Action icon.]

[Image: Trigger and a down arrow under it displaying three sequential options.]

[Image: The Add action page with fields Webhook name and Data format and buttons Cancel and Next.]

[Image: Trigger with the Call webhook action listed under it and annotation around Finish button.]

[Image: Add workflow name and description page with Name, Display, and Workflow status fields and options to cancel or save workflow.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/integrating-microsoft-defender-adaptive-access-engine","lastmod":"2026-02-11T19:51Z","nid":"1529562"} -->
## Integrating Microsoft Defender with Adaptive Access Engine

- Source: https://help.zscaler.com/unified/integrating-microsoft-defender-adaptive-access-engine
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Policies > Adaptive Access Engine > Integrating Microsoft Defender with Adaptive Access Engine
- Last modified: 2026-02-11T19:51Z
- Summary: How to integrate Microsoft Defender with Adaptive Access Engine in Experience Center.

The Adaptive Access Engine receives device signals from Microsoft Defender, which might be used to control user access to applications via Zscaler. You can configure the Microsoft Defender settings in the Experience Center and establish the connection between Microsoft Defender and Adaptive Access Engine. This enables Microsoft Defender to share the user and device context signals with the Adaptive Access Engine.

The following signals are evaluated by Microsoft Defender for Endpoint and sent to Adaptive Access Engine:

- **Risk Score**: Values are None, Informational, Low, Medium, and High. Exposure Level: Values are None, Low, Medium, and High.
- **Health Status**: The machine health status and values are Active, Inactive, ImpairedCommunication, NoSensorData, NoSensorDataImpairedCommunication, and Unknown.
- **Device Tag (Machine Tag)**: Set of machine tags.
- **RBAC Group**: The machine group name.
- **RBAC Group ID**: The machine group ID.

## Prerequisites

Before integrating Microsoft Defender with Adaptive Access Engine, ensure that you have the following:

- A Microsoft Entra user account with admin privileges.
- A Microsoft Defender for Endpoint account.
- A Microsoft Azure account with subscription for Event Hub and Logic App.
- Register a Microsoft Entra application with the required permissions in the Azure tenant account and create a service principal. This is required for the Zscaler integration. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/entra/identity-platform/howto-create-service-principal-portal).

Zscaler Client Connector Windows 4.8 version or higher is required on devices using Microsoft Defender and Zscaler Client Connector and which require Adaptive Access profiles for Zscaler Internet Access (ZIA) or Zscaler Private Access (ZPA) policy enforcement.

## Configure Azure Event Hub and Logic App

The Azure resources are required to fetch the Microsoft Defender signals and transmit them to Adaptive Access Engine.

To configure the Azure resources:

1. Log in to [Microsoft Azure portal](https://portal.azure.com/#home).
2. Create a resource group to store all resources related to the Zscaler Adaptive Access Engine application. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/azure-resource-manager/management/manage-resource-groups-portal#create-resource-groups).
3. Run the [Bicep script (hosted in Zscaler GitHub)](https://github.com/zscaler/zscaler-adaptive-access-engine/blob/main/zscaler-defender-connector/zscaler-azure-logic-app/zscaler-azure-logic-app.bicep) to create the resources and pass the appropriate parameter values. Bicep is a declarative domain specific language (DSL) that is used to provision Azure resources. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/azure-resource-manager/bicep/overview?tabs=bicep).

## Configure Microsoft Defender Integration in Adaptive Access

To configure the Microsoft Defender settings in Experience Center:

1. Go to **Policies** > **Common Configuration** > **Adaptive Access** > **Integrations**.
2. On the **Integrations** page, click the **Edit** icon for Microsoft Defender. See image. The **Edit Microsoft Defender** window appears.
3. In the **Edit Microsoft Defender** window: See image.
  - **Event Hub Instance**: Enter the event hub instance.
  - **Event Hub Host**: Enter the host name of the event hub.
  - **Consumer Group**: Enter the name of the consumer group that is used to read and process the Microsoft Defender events. The name should be `$Default`. You can also create a different consumer group on the event hub instance and use it.
  - **Connection String**: Enter the connection string corresponding to the shared access service (SAS) policy created in the event hub. This allows the Adaptive Access Engine to connect to the event hub and receive signals. To get the connection string:
    1. In the Azure portal, go to **All services** > **Analytics** > **Event Hubs**.
    2. Select your event hub and go to the **Event Hubs Namespace** page
    3. In the left-side navigation, under **Settings**, select **Shared access policies**.
    4. Select the policy and click the **Edit** icon.
    5. Select the **Copy** icon next to the **Connection string-primary key** field to copy the connection string. To learn more, refer to the [Microsoft documentation](https://learn.microsoft.com/en-us/azure/event-hubs/event-hubs-get-connection-string?source=recommendations).
  - **Status**: Enable to make the integration active.
  - **Signal**and**Value Type**: The context signal type that Microsoft Defender shares with Adaptive Access Engine.
4. Click **Save**.
5. Click **Test Integration** to verify the connection between Adaptive Access Engine and Microsoft Defender. A message appears indicating that the connection is successful. You can proceed to create device profiles.

### Service Permissions

You need to manage permissions in the following services, to allow Adaptive Access Engine to communicate with Microsoft Defender:

| Service | Permission | Type | Description | Admin Consent Required? |
| --- | --- | --- | --- | --- |
| Microsoft Graph | DeviceManagementManagedDevices.Read.All | Application | Read Microsoft Intune devices | Yes |
| SecurityEvents.Read.All | Application | Read your organization’s security events | Yes |  |
| User.Read | Delegated | Sign in and read user profile | No |  |
| Microsoft Threat Protection | AdvancedHunting.Read.All | Application | Run advanced hunting queries | Yes |
| CustomDetections.ReadWrite.All | Application | Read and write all custom detection rules | Yes |  |
| Incident.Read.All | Application | Read all incidents | Yes |  |
| Incident.ReadWrite.All | Application | Read and write all incidents | Yes |  |
| WindowsDefenderATP | Ip.Read.All | Application | Read IP address profiles | Yes |
| Machine.Read.All | Application | Read all machine profiles | Yes |  |
| Machine.ReadWrite.All | Application | Read and write all machine information | Yes |  |
| Machine.Scan | Application | Scan machine | Yes |  |
| Alert.Read.All | Application | Read all alerts | Yes |  |
| Score.Read.All | Application | Read threat and vulnerability management score | Yes |  |

[Image: Enter the details to integrate Microsoft Defender with Adaptive Access Engine]

[Image: Click the Edit icon to configure Microsoft Defender]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/integrating-okta-adaptive-access-engine","lastmod":"2026-05-17T20:19Z","nid":"1508501"} -->
## Integrating Okta with Adaptive Access Engine

- Source: https://help.zscaler.com/unified/integrating-okta-adaptive-access-engine
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Policies > Adaptive Access Engine > Integrating Okta with Adaptive Access Engine
- Last modified: 2026-05-17T20:19Z
- Summary: How to integrate Okta with Adaptive Access Engine in the Experience Center.

The Adaptive Access Engine receives user-related context signals from Okta, which are required to control user access to applications via Zscaler. You can configure the Okta settings in the Experience Center and establish the connection between Okta and the Adaptive Access Engine. Okta shares the context signals (Credentials Change and Session Revoked) with the Adaptive Access Engine.

This feature is in limited availability. Contact Zscaler Support for assistance.

## Prerequisites

Ensure that you have:

- An Okta account with admin privileges.
- An admin role in Experience Center that allows you to configure the Okta settings.
- Created an app integration.

## Configure Okta SSF

To configure the Okta SSF settings in Experience Center:

- 1. Configure the Okta integration in Experience Center.
- 2. Configure the SSF settings in the Okta portal.
- 3. Test the integration in Experience Center.

1. Log in to the [Okta portal](https://login.okta.com/).
2. Go to **Applications**.
3. Click **Create App Integration**.
4. Select **API Services** as the sign-in method, then click **Next**.
5. On the **New API Services App Integration** page, enter a name for the app integration.
6. Click **Save**.
7. The app integration is successful.
8. Go to **Applications** and select the **General** tab to see the app integration details. Copy and save the **Client ID**, as you need to provide this value while configuring Okta in the Experience Center.

1. Go to **Policies** > **Common Configuration** > **Adaptive Access** > **Integrations**.
2. On the **Integrations** page, click the **Edit** icon for Okta. See image.
3. In the **Integrations** window: See image.
  - **Base URL**: Enter the URL as `<https://customeraccountname>-okta.com`. Replace `<customeraccountname>` with your organization's account name.
  - **Client ID**: Enter the Client ID that you copied from the Okta portal while creating the app integration.
  - **Public Keys URL**: Click **Generate Keys** to generate a URL. Copy and save this URL, as you need to provide this value in the Okta portal.
  - **Status**: Click to enable the status.
  - **Signal** and **Value Type**: The context signal type (Credentials Change and Session Revoke) that Okta shares with the Adaptive Access Engine. Under **General Settings**, the **Proof of Possession** option is selected by default. Make sure to disable this option, as it is currently not supported.

1. Log in to the [Okta portal](https://login.okta.com/) and complete the following steps:
2. Go to **Applications** and search for the app that you integrated earlier and select it.
3. Click **Edit** to update the client credentials.
4. Under **Public Keys**, enter the URL that was generated in the Experience Center. See image.
5. Click **Save**.
6. Select the **Okta API Scopes** tab and ensure that the **ssf.read**, **ssf.manage**,**okta.users.read**,and **okta.sessions.read**API scopes are enabled. See image. For Apple services, if the SSF scopes are not shown, locate and enable the **Managed Apple ID Federation and Provisioning** feature. See image. To learn more, refer to the [Okta documentation](https://help.okta.com/oie/en-us/content/topics/apps/configure-apple-business-manager.htm).
7. Click **Save**.
8. On the **Admin Roles** tab, ensure that you are assigned the Super Adminsitrator role.

1. Go back to Experience Center and on the **Integrations** page:
2. Click **Test Integration** to verify the connection between Adaptive Access Engine and Okta. A message appears indicating that the connection is successful.
3. Click **Save**. The Okta configuration is completed. You can proceed to [create user and device profiles](https://help.zscaler.com/unified/adding-profile).

[Image: Add the details to configure the Okta integration]

[Image: Provide the authentication and public key details]

[Image: List of granted Okta API Scopes]

[Image: Edit the Okta configuration]

[Image: Enable the option to be able to select the required scopes]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/integrating-silverfort-adaptive-access-engine","lastmod":"2026-02-11T06:48Z","nid":"1532397"} -->
## Integrating Silverfort with Adaptive Access Engine

- Source: https://help.zscaler.com/unified/integrating-silverfort-adaptive-access-engine
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Policies > Adaptive Access Engine > Integrating Silverfort with Adaptive Access Engine
- Last modified: 2026-02-11T06:48Z
- Summary: How to integrate Silverfort with Adaptive Access Engine in Experience Center.

The Adaptive Access Engine receives user risk signals from Silverfort, which might be used to control user access to applications via Zscaler. You can configure the Silverfort settings in Experience Center and establish the connection between Silverfort and Adaptive Access Engine. Silverfort shares the context signals (User Risk Level) with the Adaptive Access Engine.

## Prerequisites

Ensure that you have:

- A Silverfort version 5.3 and above.
- A Silverfort account with admin privileges.
- An admin role in Experience Center that allows you to configure the Adaptive Access Engine settings.

Ensure you complete the following on Silverfort:

- Get the shared signals framework (SSF) well-known endpoint for the Silverfort tenant.
- Generate a bearer token on the Silverfort console. Refer to the Silverfort documentation to get these details.

## Configuring Silverfort Integration

To configure the Silverfort integration in Experience Center:

1. Go to **Policies** > **Common Configuration** > **Adaptive Access** > **Integrations**.
2. On the **Integrations** page, click the **Edit** icon for Silverfort. See image. The**Edit Silverfort** window appears.
3. In the**Edit Silverfort** window: See image.
  - **Bearer Token**: Enter the bearer token generated on the Silverfort console.
  - **SSF Config Endpoint**: Enter the URL SSF well-known endpoint from Silverfort.
  - **Status**: Enable to make the integration active.
  - **Signal**and**Value Type**: The context signal type (User Risk Level) that Silverfort shares with Adaptive Access Engine.
4. Click **Save**.
5. Click **Test Integration** to verify the connection between Adaptive Access Engine and Silverfort. A message appears indicating that the connection is successful. The Silverfort configuration is complete. You can proceed to create user and device profiles.

[Image: Configure Silverfort]

[Image: Click the Edit icon to configure Silverfort.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/investigating-sections-risk-problem","lastmod":"2026-02-11T06:36Z","nid":"1526921"} -->
## Investigating Sections of a Risk Problem

- Source: https://help.zscaler.com/unified/investigating-sections-risk-problem
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Investigating Sections of a Risk Problem
- Last modified: 2026-02-11T06:36Z
- Summary: Information about how to investigate the Explore page sections in the Admin Portal.

When you click on the Investigate link for a section on the [Explore](https://help.zscaler.com/unified/exploring-risk-problems) page, you are redirected to the Investigate page where you can view the data shown in the section in detail for each asset involved in the section (e.g., server, ports, etc.). You can filter the table using the available filter options to narrow your search. The filter options vary based on the section that you investigate.

If available, you can include or exclude an entity in the table from risk score computation by providing an explanation:

1. Enable or disable the **Include** toggle of your choice. See image.
2. Click **Save**. The **Entity Override Note** window appears.
3. In the **Entity Override Note** window, provide an explanation as to why you chose to include or exclude the entity. See image.
4. Click **Save**. You can modify the **Include** toggle for multiple entities at once and provide an explanation in the same **Entity Override Note** window.

The changes made by the admin are captured in the [audit logs](https://help.zscaler.com/risk360/about-audit-logs) with the username and reason provided for the override.

The following screenshot illustrates an example section from a problem analyzed on its Investigate page:
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-custom-dashboard","lastmod":"2026-02-26T08:29Z","nid":"1535261"} -->
## Managing a Custom Dashboard

- Source: https://help.zscaler.com/unified/managing-custom-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Custom Dashboards > Managing a Custom Dashboard
- Last modified: 2026-02-26T08:29Z
- Summary: How to modify, rename, duplicate, or delete a custom dashboard in Zscaler Admin Console.

Custom dashboards let you build personalized views of your organization's security profile so you can monitor the metrics most important to you.

You can perform the following actions on [custom dashboards you have created](https://help.zscaler.com/unified/creating-custom-dashboard) from the **Custom Dashboards** page (Analytics > Custom Dashboards):

- Edit a custom dashboard.
- Rename a custom dashboard.
- Duplicate a custom dashboard.
- Delete a custom dashboard.

To modify an existing custom dashboard:

1. Click the name of the dashboard you want to edit. See image.
2. The dashboard opens in edit mode. See image. In edit mode, you can do the following to the dashboard:
  - Favorite it.
  - Share it with other users.
  - Rename it.
  - Duplicate it.
  - Delete it.
  - Export it to a PDF.
  - Add new widgets.
  - Rearrange widgets.
  - Resize widgets.
  - Minimize and maximize widgets.
  - Change the time range of a widget.
  - Duplicate a widget.
  - Delete a widget.
3. When done making changes, click **Save**. To return to the **Custom Dashboards** page without making changes, click the **Custom Dashboards** link in the upper-left corner to return to the **Custom Dashboards** page. See image.

To rename a custom dashboard:

1. In the row for the dashboard you want to rename, click the **Actions**icon ([Image: Actions icon]) and select **Rename**. See image.
2. In the **Rename Dashboard** window, enter a name and, optionally, a description for the dashboard and click **Save**. See image.

To duplicate a custom dashboard:

1. In the row for the dashboard you want to duplicate, click the **Actions**icon ([Image: Actions icon]) and select **Duplicate**. See image.
2. In the **Duplicate Dashboard** window, enter a name and, optionally, a description for the duplicated dashboard and click **Save**. See image.

You can only delete dashboards that you have created.

To delete a custom dashboard:

1. In the row for the dashboard you want to duplicate, click the **Actions**icon ([Image: Actions icon]) and select **Delete**. See image.
2. In the **Delete Dashboard** window, confirm the deletion by typing `CONFIRM` in the dialog box. See image.

Click the **Favorite**icon () in the upper-right corner to mark this dashboard as a favorite. On the **Custom Dashboards** page, you can filter custom dashboards to only show your favorites.

See image.

1. Click the **Share**icon () in the upper-right corner to share this dashboard with others in your organization for viewing or collaboration. See image.
2. In the **Share Dashboard** window:
  - **Add People**: Enter others in your organization who can access the dashboard.
  - **Access**: For each person, select the type of access: For either access type, users you share the dashboard with cannot delete it.
    - **Viewer**: User can view the dashboard, but cannot edit it.
    - **Editor**: User can edit the widgets and layout of the dashboard.
  - **People with Access**: Users you have previously shared the dashboard with are listed here. You can change their access or select **Remove Access**.
3. (Optional) Click **Copy Link** to copy the direct link to this dashboard.
4. Click **Share** to save the changes and close the window.

See image.

1. Click the **Actions**icon ([Image: Actions icon]) in the upper-right corner and select **Rename**. See image.
2. In the **Rename Dashboard** window, enter a name and, optionally, a description for the dashboard and click **Save**. See image.

1. Click the **Actions**icon ([Image: Actions icon]) in the upper-right corner and select **Duplicate**. See image.
2. In the **Duplicate Dashboard** window, enter a name and, optionally, a description for the duplicated dashboard and click **Save**. See image.

1. Click the **Actions**icon ([Image: Actions icon]) in the upper-right corner and select **Delete**. See image.
2. In the **Delete Dashboard** window, confirm the deletion by typing `CONFIRM` in the dialog box. See image. You can only delete dashboards that you have created.

1. Click the **Actions**icon ([Image: Actions icon]) in the upper-right corner and select **Export**. See image.
2. Zscaler downloads a PDF of the dashboard to your computer.

1. Click **Add Widget** in the upper-corner. See image.
2. Select a widget from the widget library. See image.

Hover over the top part of a widget to change the pointer to a **Grab**cursor, then drag the widget where you want it on the dashboard.

See image.

Click a widget to display the resizing handles, then drag the handles to adjust the widget to the size you want.

See image.

Hover over a widget to display the **Minimize/Maximize** icon. Click the icon to switch between its maximum and minimum sizes.

See image.

For many widgets, you can click the **Time Range** icon and select a preset time range, or choose a custom range.

See image.

Click the **Actions**icon () on a widget and select **Duplicate**.

See image.

Click the **Actions**icon () on a widget and select **Delete**.

See image.

[Image: Actions drop-down menu with Rename selected.]

[Image: Rename Dashboard window on the Custom Dashboards page.]

[Image: Actions drop-down menu with Duplicate selected.]

[Image: Duplicate Dashboard window on the Custom Dashboards page.]

[Image: Actions drop-down menu with Delete selected.]

[Image: Delete Dashboard window on the Custom Dashboards page.]

[Image: Favorite icon on the edit dashboard page.]

[Image: Share icon on the edit dashboard page.]

[Image: Share Dashboard window on the edit custom dashboard page.][Image: Actions icon on the edit dashboard page with Rename selected.]

[Image: Actions icon on the edit dashboard page with Export selected.]

[Image: Selecting a custom dashboard on the Custom Dashboard page.]

[Image: Add Widget button on the edit custom dashboard page.]

[Image: Widget Library window on the edit custom dashboard page.]

[Image: Actions drop-down menu with Rename selected.]

[Image: Rename Dashboard window on the edit dashboard page.]

[Image: Actions drop-down menu with Duplicate selected.]

[Image: Duplicate Dashboard window on the edit dashboard page.]

[Image: Actions drop-down menu with Delete selected.]

[Image: Delete Dashboard window on the edit dashboard page.]

[Image: Grab icon for a widget on the edit dashboard page.]

[Image: Resize handles for a widget on the edit dashboard page.]

[Image: Minimum/Maximum icon for a widget on the edit dashboard page.]

[Image: Time Range selector for a widget on the edit dashboard page.]

[Image: Duplicating a widget on the edit dashboard page.]

[Image: Deleting a widget on the edit dashboard page.]

[Image: Editing a custom dashboard.]

[Image: Edit dashboard page showing the Custom Dashboard link that returns to the main Custom Dashboards page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-data-sources","lastmod":"2026-08-02T00:08Z","nid":"1541933"} -->
## Managing Data Sources

- Source: https://help.zscaler.com/unified/managing-data-sources
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Managing Data Sources
- Last modified: 2026-08-02T00:08Z
- Summary: How to manage existing data sources, including configuring auto-scheduling, editing, deleting, deactivating, mapping, processing, rerunning the last execution, viewing source runs, and checking audit logs.

After [creating a data source](https://help.zscaler.com/unified/creating-data-sources) in the Security Operations Platform (SecOps Platform), you can manage it within a comprehensive list of all your data sources. This page provides access to the administrative actions used to maintain existing source configurations after deployment. For an overview of the deployment process, see [Deploying Data Source Configurations](https://help.zscaler.com/unified/deploying-data-source-configurations). For information about available source types, see [Security Operations Platform Configurations by Data Source](https://help.zscaler.com/unified/security-operations-platform-configurations-data-source).

When managing data sources, you can perform the following actions:

- Configure Auto-Scheduling
- Edit Data Sources
- Delete Data Sources
- Deactivate Data Sources
- View Source Runs
- Map Source Data
- Process Data Sources
- Rerun Last Execution
- View the Audit Logs

Source auto-scheduling coordinates the ingestion of multiple data sources so they run as a single process. Scheduling ensures that sources contributing to the same entity are processed at the same time, leading to more consistent and up-to-date data, and reducing the need for manual scheduling coordination.

Auto-scheduling is enabled by default. You can adjust the schedule to better align with your data availability or processing windows.

To configure auto-scheduling:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**.
2. Click the **More Actions** icon ([Image: more actions icon in the sources page]). The **Auto Scheduling Settings** window appears.
3. Set the UTC hour at which all sources should run.
4. Click **Save**.

All configured sources will run their ingestion processes simultaneously at the specified UTC time each day.

You can edit a data source to update its general details, retrieval details, scheduling, remediation detection settings, and suppression rules.

To edit a data source:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**.
2. Choose a data source using one of the following methods: The **Edit Connector**page appears.
  - Hover over the data source, and click the **Edit**icon.
  - Select a data source from the list, and click **Edit**on the toolbar.
3. Make the necessary changes to the data source.
4. Click **Save**.

This action cannot be performed in bulk.

You can delete a data source when it is no longer required, such as when the associated vendor is no longer used by your organization.

To delete a data source:

1. Go to **Configure** > **Sources**.
2. Choose one of the following deletion methods: The **Delete data from source** window appears. See image.
  - To delete a single data source, hover over the source and click the **Delete**icon.
  - To delete multiple data sources, select the sources from the list, and click **Delete**on the toolbar.
3. Select a deletion option:
  - **Keep the data source, delete the underlying data**: Delete the data only, leaving the data source configuration unchanged. This is useful during initial source setup if you want to fix errors without having to recreate the entire source from scratch. After applying the changes or fixes to the source, you can rerun the last execution to repopulate the source correctly.
  - **Delete all**: Delete the data source along with all associated data.
4. Click **Delete**. All data is permanently deleted from the system and cannot be restored.

[Image: Warning message for deleting data from source with two deletion options]

You can deactivate a data source to temporarily suspend data retrieval, allowing you to pause the process without losing historical data. This is useful during transitions, such as when migrating to a new security product, where you might need to interrupt the current data flow while preserving existing data records.

To deactivate a data source:

1. Go to **Configure**> **Sources**.
2. Choose a data source using one of the following methods:
  - Hover over the data source, and click the **Edit**icon.
  - Select a data source from the list, and click **Edit**on the toolbar.
3. Disable **Active** located to the right of the source's **Name** to deactivate the data source.
4. Click **Save** to apply your change.

To deactivate multiple data sources:

1. From the list of sources, select the data sources you want to deactivate.
2. Click **Deactivate** on the toolbar.

You can monitor source runs to track execution timestamps and status updates, including success, cancellation, or failure. This is useful during troubleshooting, allowing you to identify and resolve issues with failed runs.

To view a data source's runs:

1. Go to **Configure**> **Sources**.
2. Choose one of the following methods: The <Source**> Runs**page appears.
  - Hover over the source, and click the **See Runs**icon.
  - Select the data source from the list, and click **Runs**on the toolbar.
3. View the run for the data source.

To learn more, see [Tracking Data Source Runs](https://help.zscaler.com/unified/tracking-data-source-runs).

You can view and edit the mapped connections of a data source.

To view mapping for a data source:

1. Go to **Configure**> **Sources**.
2. Choose one of the following methods: The **Map <**Source**>**page appears.
  - Hover over the source, and click the **Map Data** icon.
  - Select the data source from the list, and click **Map Data**on the toolbar.
3. View the mapping for the data source.

To learn more, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources) and [Managing Source Mapping](https://help.zscaler.com/unified/managing-source-mapping).

You can process a source manually to bypass its regular scheduled processing and run the source immediately.

To process a data source manually:

1. Go to **Configure** > **Sources**.
2. Choose one of the following methods:
  - Hover over the source, and click the **Process Now**icon.
  - Select the data source from the list, and click **Process Now**on the toolbar.
3. In the window that appears, click **Process Now**. See image.

Processing AnySource sources manually opens a file upload window. To learn more, see [Connecting AnySource](https://help.zscaler.com/uvm/connecting-anysource).

[Image: image.png]

You can rerun the latest data retrieval from a source to re-execute the previous run, maintaining the same run type (incremental or full refresh) to refresh the data. This is useful when implementing changes to the source settings or mapping to ensure that the changes are applied correctly.

To rerun the data source's last run manually:

1. Go to **Configure**> **Sources**.
2. Choose one of the following methods:
  - Hover over the source, and click the **Rerun Last Execution**icon.
  - Select the data source from the list, and click **Rerun Last Execution**on the toolbar.
3. In the window that appears, click **Rerun**.

You can view the source's audit logs to track changes that were made to it.

To view the source's audit logs:

1. Go to **Configure**> **Sources**.
2. Click the source that you want to view. The source setup page appears.
3. Click the **More**icon, and select **Audit Logs**. A list of audit logs appears. See image.

[Image: the more icon with the audit logs visible on the rapid7 vulnerabilities connector setup page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-entity-unification","lastmod":"2026-07-30T11:14Z","nid":"1541921"} -->
## Managing Entity Unification

- Source: https://help.zscaler.com/unified/managing-entity-unification
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Management > Data Unification > Managing Entity Unification
- Last modified: 2026-07-30T11:14Z
- Summary: How to manage existing entity unification rules, including duplicating, editing, and deleting rules.

After [creating entity unification rules](https://help.zscaler.com/unified/configuring-entity-unification), you can manage the rules in the rule sets to refine how records are merged.

For access to entity unification, your assigned role must include the Read, Create, Edit, and Delete permissions under the Platform - Model Management resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/unified/creating-managing-role-permissions) and [Assigning Roles to Users](https://help.zscaler.com/unified/assigning-roles-users).
See image.

When managing entity unification rules, you can perform the following actions:

- Process a Rule Set
- Duplicate an Entity Unification Rule
- Edit an Entity Unification Rule
- Delete an Entity Unification Rule

[Image: Model Management resource with all scopes enabled]

By default, newly configured unification rules apply to your data on the following data run. You can manually process all entities or specific entities.

To manually process unification rules:

1. In the SecOps Platform Admin Portal, go to **Configure**> **Data Unification**> **Entities**.
2. Choose one of the following options: See image. [Image: Process entities in page]
  - **Process All**: Click to process all entities.
  - **Process**: Click to process specific entities.

Manually processing an entity without processing its related entities can cause data misalignment issues until the next full data run.

Duplicating a rule is useful when you need to create multiple rules with similar logic or structure. Instead of building each rule from scratch, you can copy an existing rule and modify only the parts that differ, such as field values, conditions, or merge criteria.

To duplicate a rule:

1. Go to **Configure**> **Data Unification**> **Entities**.
2. Click the rule set that you want to modify. See image.
3. Hover over the rule you want to duplicate, and click the **Duplicate** icon. See image.
4. A copy of the rule is created with the same name as the original.

[Image: Ruleset selected]

[Image: Duplicate a rule in an entity rule set]

To update an existing rule's merge logic, you can edit the rule directly rather than deleting and recreating it. This is useful for updating an existing rule's merge logic to reflect changes in data structure, filtering requirements, or matching criteria.

To edit a rule:

1. Go to **Configure**> **Data Unification**> **Entities**.
2. Click the rule set that you want to modify. See image. [Image: Ruleset selected]
3. Hover over the rule you want to edit, and click the**Edit** icon. See image. The **Edit** **Merge Rule**drawer opens.
4. Make the necessary changes.
5. Click **Save** to save the rule.
6. Save the rule set to complete the process in one of the following ways:
  - Click **Save**to save the rule set and apply changes on the next data run.
  - From the **Save** drop-down menu, click **Save & Run**to apply the changes immediately.

[Image: Edit a rule in an entity rule set]

You can remove rules that are outdated or no longer relevant due to changes in data structure or merge logic.

Deleting a rule doesn't trigger a warning message and deletes the rule immediately.

To delete a rule:

1. Go to **Configure**> **Data Unification**> **Entities**.
2. Click the rule set that you want to modify.
3. Hover over the rule you want to delete, and click the **Delete** icon. See image.
4. Save the rule set to complete the process in one of the following ways:
  - Click **Save**to save the rule set and apply changes on the next data run.
  - From the **Save** drop-down menu, click **Save & Run**to apply the changes immediately.

[Image: Delete a rule in an entity rule set]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-field-unification","lastmod":"2026-07-30T11:26Z","nid":"1541727"} -->
## Managing Field Unification

- Source: https://help.zscaler.com/unified/managing-field-unification
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Management > Data Unification > Managing Field Unification
- Last modified: 2026-07-30T11:26Z
- Summary: How to manage existing field unification rulesets and rules.

After [configuring field unification](https://help.zscaler.com/unified/configuring-field-unification), you can manage unification rule sets and the rules they contain.

For access to field unification, your assigned role must include the Read, Create, Edit, and Delete permissions under the Platform - Model Management resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/unified/creating-custom-roles) and [Assigning Roles to Users](https://help.zscaler.com/unified/assigning-roles-users). 
See image.

[Image: The Model Management resource with all permissions enabled]

## Managing Field Unification Rule Sets

When managing field unification rule sets, you can perform the following actions:

- Process Entity Rule Sets
- Edit Rule Sets
- Copy/Paste Rule Sets
- Delete Rule Sets

By default, newly configured unification rules apply to your data on the following data run. You can manually process all entities or specific entities.

To manually process unification rules:

1. In the SecOps Platform Admin Portal, go to **Configure**> **Data Unification**> **Fields**.
2. Choose one of the following options: See image. [Image: Process all rule sets or just one rule set]
  - **Process All**: Click to process all entities.
  - **Process**: Click to process specific entities.

Manually processing an entity without processing its related entities can cause data misalignment issues until the next full data run.

You can copy all rules of a rule set and paste them into a new rule set.

To copy and paste a rule set:

1. Go to **Configure**> **Data Unification**> **Fields**.
2. Hover over the rule set you want to copy, and click the**Edit** icon.
3. Click the **Column Menu** icon, and select **Copy All Rules**.
4. Click **Cancel** to return to the **Data Unification - Fields**page.
5. Locate and click the rule set that you want to paste the copied rules to.
6. In the rule set, click the **Column Menu**icon, and select **Paste Rules**. See image. [Image: Copy and paste rules between different rulesets]
7. Save the rule set to complete the process in one of the following ways:
  - Click **Save**to save the rule set and apply changes on the next data run.
  - From the **Save** drop-down menu, click **Save & Run**to save the rule set and apply the changes immediately.

You can edit an existing rule set to modify the rules it contains.

To edit a rule set:

1. Go to **Configure**> **Data Unification**> **Fields**.
2. Hover over the rule set you want to edit, and click the**Edit** icon. The**Rule Set**page appears. If prompted, click **Unlink & Override**.
3. Make the necessary changes.
4. Save the rule set to complete the process in one of the following ways:
  - Click **Save**to save the rule set and apply changes on the next data run.
  - From the **Save** drop-down menu, click **Save & Run**to save the rule set and apply the changes immediately.

You can delete a rule set that is outdated or no longer relevant due to changes in how data is sourced or structured.

To delete a rule set:

1. Go to **Configure**> **Data Unification**> **Fields**.
2. Hover over the rule set you want to delete, and click the**Delete** icon. The **Confirm Deletion** window appears. See image. [Image: Delete a ruleset]
3. Click **Delete**. The field's logic reverts to its default logic.

## Managing Field Unification Rules

When managing field unification rules, you can perform the following actions:

- Clone a Rule
- Edit a Rule
- Delete a Rule

Cloning a field unification rule is useful when you need to create multiple rules with similar logic or structure. Instead of building each rule from scratch, you can clone an existing rule and modify only the parts that differ, such as conditions or field values.

To clone a rule:

1. Go to **Configure**> **Data Unification**> **Fields**.
2. Click the rule set that you want to clone rules from.
3. Hover over the rule in the rule set that you want to clone, and click the **Clone**icon. See image. [Image: Duplicate a rule] A copy of the rule is created with the same name as the original and `[clone]` appended to the end.

If you need to modify an existing rule, you can edit it to make the necessary adjustments. To update an existing rule's logic, you can edit the rule directly rather than deleting and recreating it.

To edit a rule:

1. Go to **Configure**> **Data Unification**> **Fields**.
2. Click the rule set that you want to modify.
3. Hover over the rule you want to edit, and click the**Edit** icon. See image. [Image: Edit a rule]
4. Make the necessary changes.
5. Save the rule set to complete the process in one of the following ways:
  - Click **Save**to save the rule set and apply changes on the next data run.
  - From the **Save** drop-down menu, click **Save & Run**to save the rule set and apply the changes immediately.

You can delete a rule if it's no longer applicable due to changes in how data is sourced or structured.

Deleting a rule doesn't trigger a warning message and deletes the rule immediately.

To delete a rule:

1. Go to **Configure**> **Data Unification**> **Fields**.
2. Click the rule set that you want to modify.
3. Hover over the rule you want to delete, and click the**Delete** icon. See image. [Image: Delete a rule]
4. Save the rule set to complete the process in one of the following ways:
  - Click **Save**to save the rule set and apply changes on the next data run.
  - From the **Save** drop-down menu, click **Save & Run**to save the rule set and apply the changes immediately.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-financial-risk-settings","lastmod":"2026-02-16T23:04Z","nid":"1532202"} -->
## Managing Financial Risk Settings

- Source: https://help.zscaler.com/unified/managing-financial-risk-settings
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Financial Risk > Managing Financial Risk Settings
- Last modified: 2026-02-16T23:04Z
- Summary: Information on managing financial risk settings in the Admin Portal.

You can customize the parameters of the financial model that determines the financial exposure of your organization.

To customize the parameters:

1. Go to **Analytics > Risk360 > Financial Risk** > **Settings**. The **Financial Risk Settings** drawer appears.
2. Under the**Default Values**, you can view the default values set for your organization during onboarding. To customize these values, under **Customized Values**: See image.
  - **Industry Vertical**: Select the industry vertical that best represents your organization.
  - **Industry Annual Revenue Range**: Select the annual revenue range for the organization's industry vertical.
  - **Financial Loss Range**: Enter the lower and upper bounds of the financial loss, in case your organization faces a breach. These values are used for simulating financial loss estimations within the set range.
3. Click **Save**. You can click **Reset to Default** if you want the Risk360 service to use the default values for calculation.

The [Financial Risk](https://help.zscaler.com/unified/viewing-financial-risk) model inherits the latest values from the preceding settings to perform loss estimations.

[Image: The Financial Risk Settings showing the fields for the Default Values and Customized Values]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-outegrations","lastmod":"2026-08-02T00:12Z","nid":"1541969"} -->
## Managing Outegrations

- Source: https://help.zscaler.com/unified/managing-outegrations
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Outegration Configuration > Managing Outegrations
- Last modified: 2026-08-02T00:12Z
- Summary: How to manage configured outegrations, including editing the outegration mapping and settings, deleting, cloning, and deactivating outegrations, and viewing the outegration activity log.

After [creating an outegration](https://help.zscaler.com/unified/creating-outegrations) in the Security Operations Platform (SecOps Platform), you can manage and monitor it from the Outegrations page. This page provides a centralized view of all configured outegrations in your account.

When managing outegrations, you can perform the following actions:

- Edit Outegration Mapping
- Edit Outegration Details and Settings
- Delete an Outegration
- Clone an Outegration
- Deactivate an Outegration
- See Activity Log

You can edit an outegration's mapping as needed (e.g., if your external vendor schema changed).

When you open the Edit Mapping page, the system automatically retrieves the latest schema from the external vendor, if available. Any changes made to the vendor's schema are reflected on the mapping page. As a result, you might need to update the mapping to include newly added required fields.

To edit an outegration's mapping:

1. In the SecOps Platform Admin Portal, go to **Configure**> **Sources**.
2. Hover over the outegration you want to edit, and click the **Edit Mapping**icon. See image. The **Edit Mapping** page appears.
3. Make the necessary changes, and click **Save**.

**[Image: Edit Mapping icon on an outegration]**

You can edit an outegration to update its authentication, adjust the outegration visibility and behavior, or rename it for easier identification.

To avoid connectivity disruption and potential data loss, make sure you are cautious when editing an outegration's details or authentication.

To edit an outegration:

1. Go to **Configure** > **Sources**.
2. Choose one of the following methods: See image. The **Edit Outegration** page appears.
  - Hover over the outegration, and click the **Edit** icon.
  - Select an outegration from the list, and click **Edit** on the toolbar.
3. Make the necessary changes.
4. Click **Next**to advance to the **Settings**step
5. Click **Map**to advance to the **Mapping**step.
6. Click **Finish**.

**[Image: Edit icon on an outegration]**

You can delete an outegration that is no longer in use (e.g., if your organization no longer works with the associated vendor). If your goal is to stop communication between the SecOps Platform and the third-party vendor, consider deactivating the relevant outegration instead.

To delete an outegration:

1. Go to **Configure** > **Sources**.
2. Choose one of the following methods: The **Confirm Deletion** window appears.
  - Hover over the outegration, and click the **Delete** icon.
  - Select one or more outegrations from the list, and click **Delete** on the toolbar.
3. Click **Delete**.

You can clone an existing outegration to create a new one with similar configuration. This is useful when setting up multiple outegrations of the same type that share similar settings (e.g., creating separate Jira Issue Type outegrations for Bug, Feature, and Task).

To clone an outegration:

1. Go to **Configure** > **Sources**.
2. Hover over the outegration you want to clone, and click the **Clone**icon. See image. The setup page of the cloned outegration appears with the same name as the original outegration from which it was cloned.
3. Edit the cloned outegration as needed, including updating its name. The entity type of the original outegration can't be changed when cloning an outegration.
4. Click **Next**to advance to the **Settings**step
5. Click **Map**to advance to the **Mapping**step.
6. Click **Finish**.

You can deactivate an outegration to halt the bidirectional data synchronization and communication between the SecOps Platform and your third-party service or tool, while still keeping any setup configurations available for future use (e.g., if you're conducting a system upgrade and need to temporarily pause data syncing).

To deactivate an outegration:

1. Go to **Configure** > **Sources**.
2. Choose one of the following methods:
  - Hover over the outegration, and click the **Edit** icon.
  - Select an outegration from the list, and click **Edit** on the toolbar.
3. In the **Connect** step, disable **Active** next to the outegration's Display Name.
4. Click **Next**to advance to the **Settings**step
5. Click **Map**to advance to the **Mapping**step.
6. Click **Finish**.

The outegration is deactivated immediately. To reactivate it later, return to the **Edit** **Outegration**page and re-enable **Active**.

You can view the activity log for a specific connection and monitor updates on an outegration and its status. If a connection fails, you can view the reason it failed and fix the issue.

To view an outegration's activity log:

1. Go to **Configure** > **Sources**.
2. Hover over the outegration, and click the **See activity log** icon. The **Runs** page appears. See image.
3. To drill down into the details and view the connection's payload, click an activity log. See image.

[Image: See Activity log icon on an outegration]

[Image: A ServiceNow outegration expanded activity log]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-peer-score-settings","lastmod":"2026-02-16T23:04Z","nid":"1533796"} -->
## Managing Peer Score Settings

- Source: https://help.zscaler.com/unified/managing-peer-score-settings
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Managing Peer Score Settings
- Last modified: 2026-02-16T23:04Z
- Summary: Information on managing peer score settings in the Risk360 Dashboard.

The Peer Score Settings drawer (Analytics > Risk360 > click the Settings icon in the Risk Score Trend section) shows the strategy selected for calculating your industry's peer risk score. The defaultstrategy is Zscaler-defined. You can add, modify, or delete custom strategies for peer score calculation.

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

<!-- ZS-ARTICLE {"url":"/unified/managing-source-mapping","lastmod":"2026-07-29T03:48Z","nid":"1541963"} -->
## Managing Source Mapping

- Source: https://help.zscaler.com/unified/managing-source-mapping
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Managing Source Mapping
- Last modified: 2026-07-29T03:48Z
- Summary: How to manage a data source's mapping in the Security Operations Platform.

Data source mapping defines how data from your external vendors is translated in the Security Operations Platform (SecOps Platform). You can manage data source mapping to ensure that your ingested data remains accurate and aligned with the SecOps Platform data model as your security environment or vendor schemas evolve. To learn more, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources) and [Using Mapping Copilot](https://help.zscaler.com/unified/using-mapping-copilot).

To help you track the state of your source mappings, a status label is displayed next to the source name on its mapping page:

- Default Mapping: The source is using a preconfigured mapping provided by Zscaler based on industry best practices.
- Custom Mapping: Appears automatically when you modify any part of a default mapping. This includes adding connections, editing logic, or pasting connections from another source.
- System Managed Mapping: The source is using a read-only mapping maintained by Zscaler. These mappings cannot be modified or edited.

See image.

When managing data source mapping, you can perform the following actions:

- Revert to default mapping.
- View audit logs.
- Copy and paste mapping connections.
- Unmap all fields.

[Image: Map CrowdStrike Assets Custom Mapping label]

Some data sources include preconfigured default mapping. You can revert any custom changes to return to the original default configuration. When you revert the mapping, the source label changes from Custom Mapping to Default Mapping.

To revert to the default mapping (when supported):

1. In the SecOps Platform Admin Portal, go to **Configure**> **Sources**.
2. Open the mapping page using one of the following methods: The **Map <**Source**>** page appears.
  - Hover over a source, and click the **Map Data** icon.
  - Select the checkbox of a data source from the list, and click**Map Data** on the toolbar.
3. Click the **More**icon, and select **Apply Default Mapping**. This overwrites any custom logic you created for that source. See image.
4. Review the updated mapping, and click **Save**to apply your changes.

You can track changes made to a source's mapping configuration to monitor updates and troubleshoot issues. Audit logs provide a history of who modified the mapping and when the changes occurred.

To view mapping audit logs:

1. Go to **Configure**> **Sources**.
2. Open the mapping page using one of the following methods: The **Map <**Source**>** page appears.
  - Hover over a source, and click the **Map Data** icon.
  - Select the checkbox of a data source from the list, and click**Map Data** on the toolbar.
3. Click the **More**icon, and select **Audit Logs**. This overwrites any custom logic you created for that source. See image. The **Audit Logs <**Source**> connector**page appears.
4. Review the mapping change history.

You can copy an entire source mapping to use in another source. This copies the full mapping configuration (including all connections and any custom logic) and can be useful when you're onboarding similar sources.

To copy and paste a source mapping:

1. Go to **Configure**> **Sources**.
2. Open the mapping page for the source you want to copy: The **Map <**Source**>** page appears.
  - Hover over a source, and click the **Map Data** icon.
  - Select the checkbox for a data source from the list, and click**Map Data** on the toolbar.
3. In the **Mapped Connections**section, click the **More**icon, and select **Copy All Connections**. See image.
4. Click **Cancel** to return to the **Sources** page, and open the mapping page for the source you want to update.
5. In the **Mapped Connections**section, click the **More**icon, and select **Paste Connections**. This replaces the current mapping of the source.
6. Review the updated mapping, and click **Save**to apply your changes.

You can clear all existing field mappings for a source. This can be useful when you want to manually redefine the entire mapping configuration without reverting to a default setup.

To unmap all fields:

1. Go to **Configure**> **Sources**.
2. Open the mapping page for the source: The **Map <**Source**>**page appears.
  - Hover over a source and click the **Map Data**icon.
  - Select a checkbox for a data source from the list, and click **Map Data**on the toolbar.
3. In the **Mapped Connections**section, click the **More**icon, and select **Unmap All Fields**. This removes all currently mapped connections from the mapping section. See image.
4. Click **Save**to apply your changes.

[Image: Data Source Mapping More icon]

[Image: Data Source Mapping More icon]

[Image: Mapped Connections More icon]

[Image: Mapped Connections More icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/managing-zscaler-use-russia","lastmod":"2026-04-01T16:36Z","nid":"1538939"} -->
## Managing Zscaler Use in Russia

- Source: https://help.zscaler.com/unified/managing-zscaler-use-russia
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Managing Zscaler Use in Russia
- Last modified: 2026-04-01T16:36Z
- Summary: Information on how to obtain and submit a Zscaler End User Statement to receive Zscaler services in Russia.

Zscaler is committed to complying with all applicable economic and trade sanctions. Zscaler provides cybersecurity services in compliance with applicable laws and does not provide services to Russian-owned entities.

The United States Government has continued to expand prohibitions on providing services within and to Russia. Due to the increase in restrictions by the United States Government and to align with U.S. policy goals, Zscaler requires customers to complete an End User Statement to receive Zscaler services in Russia.

## Submitting a Zscaler End User Statement Form

To receive Zscaler services in Russia, you must read and sign the [Zscaler End User Statement form](https://powerforms.docusign.net/781308bd-b65f-474b-9556-c3cffebc9f2b?env=na3&acct=1332b593-c312-4596-8861-f0122ba1543a&accountId=1332b593-c312-4596-8861-f0122ba1543a). You can also download, sign, and return the form via email to exportcompliance@zscaler.com. The form must be signed by an authorized signatory representing your company or organization.

After Zscaler receives and approves your signed form, we will provide instructions on how to continue to use our services in Russia.

If you have any questions about the End User Statement, send an email to exportcompliance@zscaler.com. If you have any questions regarding technical deployment, send an email to z-ask-russia@zscaler.com.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/mapping-data-sources","lastmod":"2026-08-02T00:31Z","nid":"1541959"} -->
## Mapping Data Sources

- Source: https://help.zscaler.com/unified/mapping-data-sources
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Mapping Data Sources
- Last modified: 2026-08-02T00:31Z
- Summary: How to map a data source's third-party vendor fields to the Security Operations Platform.

After [creating a data source](https://help.zscaler.com/unified/creating-data-sources) in the Security Operations Platform (SecOps Platform), and ingesting data at least once, you must map the ingested fields to the SecOps Platform data model fields. Mapping is configured per source. After all sources are configured and mapped, the platform can deduplicate and merge records across multiple sources through [data unification](https://help.zscaler.com/unified/what-data-unification), to be used and consumed by the different [SecOps Platform applications](https://help.zscaler.com/uvm/what-zscaler-security-operations) (e.g., UVM, AEM).

If you are mapping a data source for the first time, Zscaler recommends consulting with your Zscaler Account team.

## Prerequisites

Before you map a data source, ensure that you have:

- Set up the data source and run it at least once so there is ingested source data available to explore, preview, and map. To learn more, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources) and [Managing Data Sources](https://help.zscaler.com/unified/managing-data-sources#processing).
- Check that the run was completed successfully in source runs. To learn more, see [Tracking Data Source Runs](https://help.zscaler.com/unified/tracking-data-source-runs).

For access to data source mapping, your assigned role must include the Read and Edit permissions under the Platform - Data Source Mapping resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles).
See image.

## Mapping a Data Source

To map a data source:

1. In the SecOps Platform Admin Portal, go to **Configure**> **Sources**.
2. Choose one of the following methods: The **Map <**Source**>**page appears.
  - Hover over the source, and click the **Map Data**icon.
  - Select the data source from the list, and click **Map Data**on the toolbar.

There are three main workspace sections on the mapping page:

- **Ingested Data**: The source fields (left) detected from the most recent successful data ingestion run.
- **Entities**: The SecOps Platform data model entities and fields (right) that you can select from to map data source fields for normalization and unification.
- **Create New Connection**: The section (center) where you add mappings and configure how to populate the target SecOps Platform fields.

See image.

The main objective of mapping is to populate the SecOps Platform data model with complete, consistent, and identifiable values. Each mapping involves dragging a SecOps Platform field from the Entities column to the Create New Connection section, and then configuring the corresponding field value to its left with a data source field from the Ingested Data column or with a custom value (i.e., composite, value, or script).

The fields under the Entities column are organized by entity (e.g., Asset, Component, Finding). The SecOps Platform uses a unified, entity-based data model to normalize and harmonize data from Zscaler and third-party sources into a single source of truth. When you map fields into the correct entity and populate its key attributes, the platform can deduplicate, correlate, enrich, and operationalize the data across sources for managing your security environment, reporting, and ticketing.

Some data source connectors include a preconfigured default mapping based on common use cases and industry best practices. This is indicated by a Default Mapping label next to the source name. If your source includes a default mapping, review it to ensure that it matches your environment and the data you want to ingest. You can modify and customize the default mapping as needed. After you edit a default mapping, the label changes to Custom Mapping. To learn more, see [Managing Source Mapping](https://help.zscaler.com/unified/managing-source-mapping).

See image.

Some sources use system-managed mappings. These sources are maintained by Zscaler and can't be edited or customized.

File parsing defaults to Auto for all sources and is recommended for most use cases. If you require more granular control over how the file is parsed, or if the Auto parser does not correctly identify the file type, you can manually select the appropriate Parser Type to control how the platform parses the file. To learn more, see [Source File Parser Types](https://help.zscaler.com/unified/source-file-parser-types).

See image.

## Creating a New Mapping

As a best practice, start by mapping each entity's key field. This unique identifier (e.g., an asset's external ID) is the anchor the platform uses to reliably identify, track, and deduplicate records. Continue with mapping the core attributes (e.g., status, severity, and timestamps) that drive security reporting and workflow automation. After these essentials are validated, you can map additional enrichment fields and optional metadata.

Building an effective mapping configuration requires a deep understanding of your source data's structure and how it aligns with the SecOps Platform data model. Mapping Copilot simplifies this by analyzing your ingested data to provide intelligent mapping recommendations. This AI-powered assistant helps you interpret source fields and navigate the SecOps Platform schema to ensure your data is accurately mapped. To learn more, see [Using Mapping Copilot](https://help.zscaler.com/unified/using-mapping-copilot).

To create a new mapping:

1. Drag a SecOps Platform field from the **Entities** column to the **Create New Connection**section in the center. See image. You can copy and paste mapping connections from an existing mapped source or unmap all connections. To learn more, see [Managing Source Mapping](https://help.zscaler.com/unified/managing-source-mapping#copy-paste-mappings).
2. For mapping use cases that require additional entities or fields beyond the existing SecOps Platform system defaults, you can extend the data model from this page: See image. While you can extend the data model with custom entities and fields, these additions typically require advanced downstream configuration (e.g., defining entity relationships and aliases) to deliver significant value. Apply customizations cautiously to ensure consistent normalization and reliable data correlation.
  - Click **Add Entity** to create a custom entity.
  - Click the **Add field to <**entity**>** icon on an existing entity to add a custom field to that entity.
3. Open the editor to set the value to map to the SecOps Platform field in one of the following ways:
  - Drag a source field from the **Ingested Data** column to the **Create New Connection**section, then click the **Editor**icon. See image.
  - Click **Editor**in the empty left field. See image.
4. In the editor, select one of the following methods to set the SecOps Platform field's value on the left: Click **Explore Data**to review the ingested fields and their values to identify which fields you want to map. If the source has had a successful run in the last 90 days, the Explore panel includes the full dataset from that run; otherwise, it displays a 50-record preview. To learn more, see [Exploring Source Fields in Mapping](https://help.zscaler.com/unified/exploring-source-data-mapping).
  - Script
  - Value
  - Field
  - Composite
5. (Optional) Explode a field containing an array to treat each array element as an individual record in the mapped output. This transforms a single ingested record that contains an array into multiple mapped records, with one record per array element (e.g., a list of hostnames, CVEs, or tags). You can explode a field containing an array in one of the following ways: The SecOps Platform supports the following use cases for exploding arrays: Only one field can be exploded per mapping. When you explode an array field, you must map the exploded value to a key field for that entity to ensure each resulting record is unique. This is typically achieved using a Composite mapping to combine the exploded value with a parent identifier (e.g., `asset_id` + `cve_id`). If the exploded value is not part of the key, every record generated from the array shares the same ID, causing each record to overwrite the previous one until only the final value remains.
  - Click the **Explode Field**icon that appears when hovering over the data source field. See image.
  - Select the **Explode Values**checkbox in the script editor when configuring the field. Use this option when your script generates a custom array that needs to be exploded into individual records. For example, if a source field provides a single comma-separated string containing valid service IDs mixed with invalid text (e.g., "`SNSVC1234, pending, BSN5678, n/a`"), you can use a Python script to split the string, filter out the invalid values, and output an array (e.g., ["`SNSVC1234`", "`BSN5678`"]). Selecting this checkbox can then explode that single array into distinct output records. See image.
  - Standard array: Explode a field containing an array of strings to create a single record per string. For example, you can explode the array field vulnerabilities with the value `["CVE-2024-1234", "CVE-2024-5678"]` into two records, one for `CVE-2024-1234` and one for `CVE-2024-5678`.
  - Array within a JSON object: Expand a field to reveal its nested fields, then explode a nested array field to create a single record per array element. For example, for the field `{"asset": {region: "east", "hostnames": ["web-srv-01", "prod-lb-02"]}}`, click the arrow to expand and reveal the nested fields `region` and `hostnames`. You can then explode the array field `hostnames` to create two individual records, one for `web-srv-01` and one for `prod-lb-02`.
  - Array of objects: Explode a field containing an array of objects to create a single record per object. You can then either map the entire object or expand it to reveal and map the unique nested keys found across the array elements to specific SecOps Platform fields. For example, you can explode an array of tag objects `[{"tag_id": "t1", "name": "production"}, {"tag_id": "t2", "name": "staging"}]` into individual records for each tag. You can then expand the exploded field to map a specific nested key, such as `name`, to your target field.
6. Click **Map**to add the mapping. Repeat the mapping process for all the fields you want to use in the platform. Only mapped fields are available for unification and consumption.
7. Click the **Full Preview**icon to verify the mapped output. Make changes as necessary until it looks correct. See image.
8. Click **Save**to save the configured data source mapping.

After saving, mapping changes apply to future runs scheduled on the data source setup page. To apply the new mapping to the data immediately, process the data source on the Sources page. To learn more, see [Managing Data Sources](https://help.zscaler.com/unified/managing-data-sources#processing).

## Data Mapping Guidelines

To ensure high-quality data and reliable correlation across your environment, follow these standards when configuring your mappings. These guidelines help the platform consistently normalize, deduplicate, and prepare your data for downstream security analytics and automated workflows.

### Minimally Required Fields

Populating core attributes is essential for record creation and downstream visibility. Map the following fields at minimum to ensure records can be created and used downstream:

| Entity | Fields to Always Map |
| --- | --- |
| **Asset** | `source_key`, `type`, `name` |
| **Finding** | `source_key`, `type`, `title`, `description`, `original_severity_score`, `original_severity` |

If your source provides timestamps, also map lifecycle fields (e.g., `first_seen` and `last_seen`) when available.

### Configuring Entity Keys

When mapping an entity, you must select a field to be the entity key. The platform uses the key to uniquely identify records so it can reliably track, correlate, and deduplicate data across runs and across sources. When selecting a field to be the entity's key:

- Use source-provided IDs that are unique and stable over time (e.g., UUIDs or instance/device IDs) instead of values that commonly change (e.g., display names or IP addresses).
- Ensure one key value represents one real-world record. If multiple distinct records share the same key, records can overwrite or merge incorrectly.
- Normalize formatting where needed (e.g., consistent MAC address delimiters) and avoid leading or trailing whitespace.
- If no single field is unique or when exploding arrays, build a composite key that preserves uniqueness (e.g., `asset_id + cve`).

Use Python scripts when you need more granular control over how a value is mapped, such as when you need to transform or normalize data, apply conditional logic, or derive a value when the source doesn't provide it in the exact format you need.

Source fields referenced within a script do not display visual connection lines in the mapping workspace. Additionally, they do not appear in the source fields list when applying the Mapped filter.

Common use cases for using scripts include:

- Data Formatting: Clean up raw source data (e.g., lowercasing hostnames or stripping `.local` domain suffixes) or reformat delimiters in hardware identifiers (e.g., converting MAC addresses from `00-0C-29-4F-8B-3C` to `00:0C:29:4F:8B:3C`).
- Conditional Filtering: Map values only if they meet specific criteria (e.g., using the `ipaddress` module to map only public IP addresses while ignoring internal ones).
- Field Priority (Fallback Logic): Create a hierarchy for missing data (e.g., if `display_name` is null, check for hostname; if both are missing, return `asset_id`).
- Custom String Logic: While the platform automatically handles common transformations (e.g., normalizing OS versions), you can use scripts for unique logic, such as using Regex to extract a specific ID from an unstructured description field or matching custom naming patterns.

### Validating and Testing Your Logic

Before finalizing your mapping, use the validation and testing tools to ensure your logic is correct, and the output matches your expectations.

#### Validating the Script

Click **Validate**to verify that the mapping script is syntactically valid. This ensures that the code can compile and identifies common errors like missing brackets, indentation issues, or invalid Python characters.

#### Testing the Script

You can run your mapping script against example input values and preview the mapped value that the script returns for the target field you're configuring. This helps you validate transformation logic, troubleshoot unexpected results, and test edge cases without affecting live data.

The Test Logic panel displays actual records from your source. If the source has had a successful run in the last 90 days, the panel includes the full dataset from that run; otherwise, it displays a 50-record preview. Each input field at the top of the panel is generated dynamically based on the source fields referenced in your script.

To test the script:

1. In the **Script Editor**, click **Test Script**. See image. The **Test Logic**panel appears.
2. In the **Test Logic**panel: See image.
  1. Provide test input examples using one of these options:
    - Select a record (row) from the sample data table to populate the input fields with values from that record. Adjust values as needed.
    - Enter the test values manually.
  2. Click **Run Test**to run the script with the current inputs and review the result.
  3. Review the result shown in the panel (below the sample data). This is the value the script returns for the target field using the current input values.
  4. Update the script in the editor to refine your transformation logic as needed, and repeat until the script produces the intended mapped values.

The script runs against a source row passed in as a dictionary (row). Access values using `row["``<field>``"]` or `row.get("``<field>``")`, and ensure the value you return matches the type of the target field you're mapping to.

### Example: Formatting the Asset First Seen Date

When mapping entities like assets and findings, you should always map the First Seen date. Because sources provide timestamps in various formats (e.g., epoch, various ISO-8601 strings, or non-standard date patterns), you can use a simple script to normalize them to the platform's required format: `%Y-%m-%dT%H:%M:%SZ`.

For example, when mapping the Asset First Seen date, you can replace `<Source's Original Date Format>` under `ORIGINAL_DATE_FORMAT` with the format provided by your source:

```
from datetime import datetime

def evaluate(row: dict) -> str:
    # Access the source field
    date_string = row.get("asset_first_seen")
    
    # Replace with the date format used by your source
    ORIGINAL_DATE_FORMAT = '
<Source's Original Date Format>
' 

    # Parse the source date and return it in the platform's required format
    return datetime.strptime(date_string, ORIGINAL_DATE_FORMAT).strftime('%Y-%m-%dT%H:%M:%SZ')
```

The script references the `asset_first_seen` date, so this field should appear in the Test Logic panel. You can test the conversion to ensure that your source's original date format correctly transforms into the platform's format. Use the Test Logic panel to input a sample date from your source and verify the output.

The following table details examples of possible original date formats:

| Source Original Date Format |  | Input | Expected Output |
| --- | --- | --- | --- |
| Standard String (date with spaces) | `%Y-%m-%d %H:%M:%S` | 2024-03-10 15:26:56 | 2024-03-10T15:26:56Z |
| Timezone Offset (normalizes `+/-HH:MM` to `Z`) | `%Y-%m-%dT%H:%M:%S%z` | 2024-03-10T15:26:56+05:00 | 2024-03-10T15:26:56Z |
| High Precision (truncates 7+ decimals to 6) | `%Y-%m-%dT%H:%M:%S.%fZ` | 2024-03-10T15:26:56.123456789Z | 2024-03-10T15:26:56Z |

By default, the platform handles edge cases and invalid inputs by returning `None` if the script fails to produce a valid result. You can test the primary conversion and the edge cases by manually entering values in the test panel and checking the output.

The following table details possible edge cases that the platform handles:

| Edge Case | Input | Expected Output |
| --- | --- | --- |
| Missing Data (field exists but is empty) | "" (Empty String) | None |
| Null Value (field is missing from the source) | null | None |
| Invalid Numeric (number is too short for a valid date) | 12345 | None |
| Pre-Epoch (dates before 1970-01-01) | Jan 1, 1960 | None |

Use the Value method when you want to populate a SecOps Platform field with a fixed (constant) value that you provide. Every record ingested from that source receives the same value for that field.

This is useful when the field should be constant for the entire source. For example:

- Providing a consistent default for known attributes when the source doesn't include a field you still want set (e.g., a default type or status that's the same for all records in that feed).
- Tagging records to simplify filtering and troubleshooting (e.g., marking everything ingested a specific source instance).

For example, a Snyk source that retrieves repositories typically won't provide an explicit asset type field. In that case, you can set the SecOps Platform Asset Type field using Value = `Repository` to ensure all assets ingested from that source are consistently categorized.

Avoid using a fixed value for fields that should vary per record (especially identifiers, keys, and timestamps).

Use the Field method to create a direct, one-to-one mapping between a source field and a SecOps Platform target field. This passes the source value through exactly as it is received, without transformations, formatting changes, or conditional logic. This is useful when types and formats of the source and the SecOps Platform field already match (e.g., mapping `serial_number` directly to the SecOps Platform Serial Number field).

### Mapping Nested Fields

Some sources return fields inside nested objects in the source payload (often JSON). A nested field is a value stored inside one or more parent objects, and you reference it by its path using dot notation. For example, `vulnerability.cvss.base_score` refers to the score value stored within the `cvss` object under `vulnerability`.

Use nested fields when the attribute you need is not available as a top-level source field and is instead grouped under a parent object such as asset, finding, or device. In these cases, make sure to map the specific nested property that contains the actual value you want, not the parent object itself.

To map a nested field:

1. In the **Ingested Data**column, locate the parent field, and click the arrow to view the nested properties. See image.
2. Drag and drop the nested field into the **Create New Connection**section.

The platform supports nested fields in the UI only up to the third level. If the field you need is deeper, enter the full path manually using dot notation in the Field method (e.g., `asset.cloud.account.details.arn`, `finding.evidence.event.metadata.user.id`).

Use the Composite method to combine multiple source fields into a single SecOps Platform field. This is primarily used to generate a unique identifier (key) when a single source field is insufficient to distinguish individual records. For example, if a vendor does not provide an explicit finding key or if the available ID does not capture the specific relationship between a finding and an asset, you can concatenate multiple fields (e.g., `cve` or `vulnerability_id` and `asset_id`) to create a robust finding key.

When building a composite mapping, select the source fields in the order you want them to appear in the concatenated value. The platform automatically inserts an underscore (`_`) as the separator between the values in the final string.

### Using Composite for Exploded Fields

This method also provides a straightforward way to ensure each record remains distinct after an array is exploded. When you explode a field containing an array (e.g., vulnerabilities, tags), each array element becomes a separate output record. Because the original record ID repeats across these new records, it is no longer unique on its own. You can use Composite to create a new unique key by combining the original record ID with the exploded value.

For example, you can explode the source field `vulnerabilities` (an array) so that each CVE value becomes its own output record and can be mapped to the SecOps Platform Finding field. For a single source record with `asset_id` = `host-99` and `vulnerabilites` = `[CVE-2024-1234, CVE-2024-2345, CVE-2024-3456, CVE-2024-4567]`, the explode produces 4 output records:

| Record | asset_id | vulnerabilities (exploded value) | Key |
| --- | --- | --- | --- |
| 1 | host-99 | CVE-2024-1234 | host-99_CVE-2024-1234 |
| 2 | host-99 | CVE-2024-2345 | host-99_CVE-2024-2345 |
| 3 | host-99 | CVE-2024-3456 | host-99_CVE-2024-3456 |
| 4 | host-99 | CVE-2024-4567 | host-99_CVE-2024-4567 |

If you mapped only `asset_id` as the key, all 4 records would share the same identifier (i.e., `host-99`), causing them to overwrite one another. By using Composite to combine `asset_id` with the exploded vulnerability value, each record receives a unique identifier that preserves the specific asset-to-finding relationship.

[Image: Data Source Mapping Role Permissions]

[Image: Map CyCognito - Issues connector page]

[Image: Map CrowdStrike Alerts connector page]

[Image: Map Qualys Vulnerabilities connector Parser Type]

[Image: Dragging a field from the Entities column into the Create New Connection section]

[Image: Add Entity and Add field to <entity> icons]

[Image: Create New Connection Field Editor]

[Image: Create New Connection open Editor]

[Image: Test Script button in Script Editor window]

[Image: Run Test on the Test Logic panel]

[Image: Expanded nested device field]

[Image: Explode Field icon]

[Image: Explode Values checkbox]

[Image: Data Source Mapping Full Preview icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/onboarding-users-experience-center","lastmod":"2026-07-29T03:26Z","nid":"1542045"} -->
## Onboarding Users to Experience Center

- Source: https://help.zscaler.com/unified/onboarding-users-experience-center
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Onboarding New Users > Onboarding Users to Experience Center
- Last modified: 2026-07-29T03:26Z
- Summary: How to onboard users to Experience Center.

While onboarding to Experience Center for the first time, as a super admin, you can import users via your identity provider (IdP), import user details using a CSV file, or add individual users.

To add users:

1. Sign in to [Experience Center](https://console.zscaler.com).
2. On the **Welcome** page, click **Initial Set Up** under **Set Up New Users**. See image. The **Users** page appears. See image.
3. On the **Users** page, add users by using all or any of the following three methods: The user details are displayed on the **Users** page.
  - Import Users via Your IdP
  - Import User Details using a CSV File
  - Add Individual Users Manually

You can add additional users after completing the onboarding process. To learn more, see [Adding Users](https://help.zscaler.com/authentication-service/adding-users).

[Image: Add users via IdP, CSV, or manually]

If you manage users with an IdP, Zscaler can import those users directly. You need to ensure your IdP is set up for SAML authentication and SCIM provisioning. To learn more, see [About External Identity Providers](https://help.zscaler.com/zidentity/about-external-identity-providers).

1. On the **Users** page, click **Connect IdP** at the top of the page. See image.
2. Under **Select Identity Provider**, select your IdP and click **Next**. Commonly used IdPs are shown at the top, or you can select one from the drop-down menu.
3. Under **Link Your**<IdP Name>**Identities**, enter the following under **General Details**:
  - **Name**: A unique name for this IdP connection (e.g., "Corp IdP Connection").
  - **Domain**: Select the domain associated with the tenant for this connection from the drop-down menu. This list is generated from the domains associated with your Authentication Service account.
  - **Login ID Attribute**: Enter the attribute from your IdP that will be used as the Authentication Service login ID.
4. Select the protocol for your IdP, either **OIDC**or **SAML** then complete the information to retrieve the metadata:
  - OIDC Connection
  - SAML Connection
5. Click **Fetch**next to the **Metadata URL** box. Zscaler retrieves the metadata information required to connect your users. Depending on your protocol:
  - OIDC Connection
  - SAML Connection
6. Under **Provisioning**, click **Generate Token** to generate a bearer token, which is used by the IdP to connect to Zscaler. Click the toggle if you want to skip this step and disable SCIM provisioning. To learn more, see[Enabling SCIM for Identity Management](https://help.zscaler.com/zpa/enabling-scim-identity-management).
7. Click **Finish** to link to your IdP and import your users.

If you want to remove all imported users from Zscaler, click the **Disconnect**<IdP Name> link at the top of the page. This action cannot be undone. See image.

- **Redirect URI**: Filled in automatically. Your unique URI to log into Zscaler.
- **Client ID**: Enter ID to log in to the IdP.
- **Client Secret**: Enter the secret associated with the client ID.
- **Metadata URL**: Enter the URL to retrieve metadata on your IdP.

- **SP Entity ID**: Filled in automatically. Your unique entity identifier on Zscaler as the service provider.
- **SP URL:** Filled in automatically. Your unique URL to access Zscaler as the service provider.
- **Metadata URL**: Enter the URL to retrieve metadata from your IdP.

Click **Next**to continue.

The following IdP information is displayed:

- **IdP Entity URL**: The unique URL for this IdP entity.
- **IdP Single Sign-On URL**: The SSO URL to which users are sent for authentication for this IdP.
- **IdP Certificate**: The certificate used to verify the digital signature of the IdP.

Review the information and click **Next**to continue.

1. On the **Users**page, click **Upload CSV**.
2. In the **Upload CSV** dialog box, click **Download CSV Template** to download Zscaler's user template file. See image. The template file has the following format: `Action,Login Name,Display Name,First Name,Last Name,Primary Email,Secondary Email,Language,Department,Timezone,Password Reset,Group Names,, +,psmith@example.com,Priya Smith,Priya,Smith,psmith@example.com,,English (US),Engineering,(UTC+00:00) GMT,true,Group1,Group2,Group3 +,aramirez@aramirez.com,Alonso Ramirez,Alonso,Ramirez,aramirez@example.com,,English (US),Human Resource,(UTC+00:00) GMT,false,Group2,Group3,`
3. Add users to the CSV file and ensure the following:
  - Retain the first line of the file as the header row.
  - The file name must have a .csv extension.
  - In the **Action**column, enter one of the following:
    - + (plus sign) To add a user, enter values in at least the **Primary Email**, **Login Name**, **First Name**. and **Last Name** columns.
    - - (minus sign) to delete a user, you need only enter the **Login Name** of the user you are deleting.
  - The password can be left blank if you don’t want to upload passwords in clear text.
  - You can use one-time passwords to enable users to log in and set passwords.
  - Each user must be on a separate line.
  - Each user's email address must have a domain name that was defined in the Zscaler Admin Console.
  - If the authentication method is a one-time token or one-time link, then the **Primary Email** field must contain a valid email address.
  - The **Secondary Email** can specify any domain, but it must be a valid email address.
4. When you have your CSV in the correct format, drag the CSV file from your computer to the **Upload File** box and click **Save**. Zscaler displays a message indicating whether your upload was successful. If unsuccessful, correct the file according to the requirements in the previous step. Click **Retry**when you are ready to upload a new file.
5. When users are successfully uploaded, the user details are displayed on the **Users**page.

1. On the **Users** page, click **Add User**. See image.
2. Enter the user's first and last name and their email address within your organization.
3. Select the role you want to assign to this user: See image.
  - **End User**: A regular user who does not require any administrator access. These users are assigned to the Authentication Service Dynamic Group Registered Domains group.
    - Go to **Administration** > **Entitlements** > **End User Entitlements**to view the entitlements. To learn more, see [About Service Entitlements](https://help.zscaler.com/authentication-service/about-service-entitlements).
  - **Full Admin**: An administrator with full access to add, edit, import, and delete users in the Zscaler Admin Console. These users are assigned to the Authentication Service Global Administrators group. This type of user is commonly called a super admin.
    - Go to **Administration** > **Admin Management** > **Role-Based Access Control** > **Administrative Entitlements** to view the admin entitlements. To learn more, see [About Administrative Entitlements](https://help.zscaler.com/authentication-service/about-administrative-entitlements).
4. Click **Add User**.
5. Repeat the steps to add more users.

[Image: Click the Add User button to add individual users manually]

[Image: Enter the user details and select the user type]

[Image: Upload the CSV file containing the user details]

[Image: Click Disconnect <IdP> to remove users imported from the IdP]

[Image: Click Initial Set Up to add users]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/quantifying-loss-monte-carlo-simulation","lastmod":"2026-02-16T22:11Z","nid":"1533794"} -->
## Quantifying Loss With Monte Carlo Simulation

- Source: https://help.zscaler.com/unified/quantifying-loss-monte-carlo-simulation
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Financial Risk > Quantifying Loss With Monte Carlo Simulation
- Last modified: 2026-02-16T22:11Z
- Summary: Information about Monte Carlo Simulation and how it helps you quantify the probability of exceeding loss in millions of dollars.

The Monte Carlo Simulation is a renowned method to determine the probability of an outcome from a range of outcomes with a random set of variables as the source of uncertainty. This simulation helps organizations quantify various risk-related parameters.

The Risk360 service runs the Monte Carlo simulations 1,000 times. In each simulation iteration, the service measures the financial loss based on a randomized cyber breach event and a randomized financial loss within a predefined confidence interval defined by the lower and upper bounds of a loss when a breach occurs. The result of the simulations is used to estimate the yearly average loss and the loss exceedance curve (i.e., the curve that shows the probability that a loss exceeds a certain amount). The process is repeated 4 times to calculate the yearly average loss and the loss exceedance curve under the following 4 distinct scenarios:

- **Inherent Risk**: The current risk score of the organization.
- **Residual Risk**: The risk score of an organization after mitigating the top 10 risk factors.
- **Last 30 Days Average Risk**: The average risk score of an organization in the last 30 days.
- **Industry Peer Risk**: The average risk score of peer organizations.

## Analyzing the Monte Carlo Simulation

The Monte Carlo Simulation page (Analytics > Risk360 > Financial Risk > Monte Carlo Simulation) consists of the following metrics to help you analyze the simulated results:

### Loss Exceedance

This graph shows the probability of exceeding loss values (in percentage) in millions of dollars. The graph shows the simulation across 4 risk parameters:

- **Inherent Risk (Current Risk Score)**: This simulation is based on your current organization's risk score. This shows the probability of loss based on your overall organization risk score.
- **Residual Risk (Risk score after addressing top 10 factors)**: The residual risk is the risk score obtained after rectifying the 10 [Risk360 Factors](https://help.zscaler.com/unified/viewing-risk-factors). This simulation helps you see the financial impact of addressing the top 10 [Risk360 Factors](https://help.zscaler.com/unified/viewing-risk-factors).
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

<!-- ZS-ARTICLE {"url":"/unified/ranges-limitations","lastmod":"2026-07-20T10:31Z","nid":"1492411"} -->
## Ranges & Limitations

- Source: https://help.zscaler.com/unified/ranges-limitations
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Ranges & Limitations
- Last modified: 2026-07-20T10:31Z
- Summary: Information about product and feature ranges and limitations.

This article lists the ranges and limitations of rules, policies, fields, and other features. All values are per organization unless noted otherwise.

- Internet & SaaS (ZIA) Ranges and Limitations
- Private Access (ZPA) Ranges and Limitations
- Zscaler Digital Experience (ZDX) Ranges and Limitations
- Cloud & Branch Connector Ranges and Limitations
- Authentication Service Ranges and Limitations
- Zero Trust Browser Ranges and Limitations
- Zero Trust Branch Ranges and Limitations

If you must increase the maximum or adjust limits for your organization, send a request to Zscaler Support.

### Alerts

The following table shows the ranges and limitations for [Alerts](https://help.zscaler.com/zdx/alerts) settings:

| **Feature** | **Standard** | **Microsoft 365** | **Advanced** | **Advanced Plus** |
| --- | --- | --- | --- | --- |
| Total Alerts | 512 | 512 | 512 | 512 |
| Total Active Alerts (Alerts with Incident Correlation) | Up to 3 | 10 | 25 | 100 |
| Total Alert Rules | Up to 3 | 10 | 25 | 100 |
| Dynamic Alerting | Not supported | Not supported | Supported | Supported |

### Analytics

The following table shows the support settings for [Analytics](https://help.zscaler.com/zdx/analytics):

| **Feature** | **Standard** | **Microsoft 365** | **Advanced** | **Advanced Plus** |
| --- | --- | --- | --- | --- |
| Hop View (Granular Expanded Cloud Path) | Supported | Supported | Supported | Supported |
| Software and Device Inventory | Not supported | Not supported | Supported | Supported |
| Process Inventory | Not supported | Not supported | Not supported | Supported |
| Software Patch Inventory | Supported | Supported | Supported | Supported |
| UCaaS Monitoring | Not supported | Microsoft Teams Call Quality (only) | Supported | Supported |
| UCaaS Monitoring (tenant limit) | Not supported | 40 | 40 | 40 |
| Root Cause Analysis | Not supported | Not supported | Supported | Supported |
| System-Generated Reports | Not supported | Not supported | Supported | Supported |
| Incidents | Not supported | Not supported | Not supported | Supported |
| ZDX Snapshots | Not supported | Not supported | Supported | Supported |
| Self Service | Not supported | Not supported | Not supported | Supported |
| Data Explorer Views | Not supported | Not supported | 30 | 100 |
| Data Explorer Applications | Not supported | Not supported | 1 | 4 |
| Data Explorer Metrics | Not supported | Not supported | 1 | 4 |
| Wi-Fi | Not supported | Not supported | Supported | Supported |
| Internet & SaaS PSE Health | Supported | Supported | Supported | Supported |
| Managed Monitoring* | Supported only as add-on | Supported only as add-on | Supported only as add-on | 1 probe per 1,000 users for each managed location |
| Network Intelligence | Not supported | Not supported | Not supported | Supported |
| Device Health Dashboard | Not supported | Not supported | Not supported | Supported |
| Device Events Reports | Not supported | Not supported | Supported | Supported |
| Remediation | Not supported | Not supported | Not Supported | Supported |
| Real User Monitoring | Not supported | Not supported | Not supported | Supported |

*Identical ranges and limitations across add-ons for Standard, Microsoft 365, and Advanced subscriptions.

### Applications and Probes

The following table shows the ranges and limitations for [Applications and Probes](https://help.zscaler.com/zdx/configuration) settings:

| **Feature** | **Standard** | **Microsoft 365** | **Advanced** | **Advanced Plus** |
| --- | --- | --- | --- | --- |
| Total Number of Internet Web Applications | 512 | 512 | 512 | 512 |
| Active Probes (per tenant) | 3 | 7 | 30 | 100 |
| Active Probes (per user) | 3 | 7 | 30 | 30 |
| Total Number of Cloud Path and Web Monitoring Probes | 512 | 512 | 512 | 512 |
| Active Probes | 6 | 13 | 30 | 1,000 |
| Probing Interval | 15 minutes | 5 minutes | 5 minutes | 5 minutes |
| Page Fetch Time Server Redirects | Not supported | Not supported | Not supported | Supported |
| ZDX Autosense (Webex Call Quality) | Not supported | Not supported | Supported | Supported |
| ZDX Autosense (Zoom Call Quality) | Not supported | Not supported | Supported | Supported |
| ZDX Autosense (Microsoft Teams Call Quality) | Not supported | Supported | Supported | Supported |

## Diagnostics

The following table shows the ranges and limitations for [Diagnostics](https://help.zscaler.com/zdx/diagnostics) settings:

| **Feature** | **Standard** | **Microsoft 365** | **Advanced** | **Advanced Plus** |
| --- | --- | --- | --- | --- |
| Total Diagnostics Sessions | Not supported | 25 | 25 | 100 |
| Total Active Sessions | Not supported | 25 | 25 | 100 |

### Integrations and Data Retention

The following table shows the ranges and limitations for Integrations and Data Retention settings:

| **Feature** | **Standard** | **Microsoft 365** | **Advanced** | **Advanced Plus** |
| --- | --- | --- | --- | --- |
| Webhooks | Not supported | 10 | 10 | 50 |
| ZDX APIs | Not supported | Supported for Microsoft 365 events | Supported | Supported |
| Query Limit Data Retention | 2 days | 14 days | 14 days | 14 days |
| Query Limit Time Span Selection Range | 2 to 24 hours | 2 to 48 hours | 2 to 48 hours | 2 to 48 hours |
| ZDX Snapshots | Not supported | Not supported | 90 days | 90 days |
| ZDX Copilot | Not supported | Not supported | Not supported | Supported |
| Workflow Automation | Not supported | Not supported | Not supported | Supported |
| Remediation | Not supported | Not supported | Not supported | 180 days |

### Organization

The following table shows the ranges and limitations for Organization settings:

| Feature | Limit |
| --- | --- |
| Admin Users per Organization | 10K admins Admin users can reside in Internet & SaaS or Private Access. |
| Active Admins | 255 active admins Admins connected to the cloud are considered active admins. |
| API Keys | 50 |

### Remediation

[Remediation](https://help.zscaler.com/zdx/understanding-remediation) is currently supported for Advanced Plus subscriptions.

The following table shows the ranges and limitations for Remediation:

| Feature | Limit |
| --- | --- |
| [Scripts](https://help.zscaler.com/zdx/about-scripts) | 1,000 scripts 100 enabled scripts |
| [Jobs](https://help.zscaler.com/zdx/viewing-and-managing-device-remediation) | 1,000 jobs Jobs can be In Progress, Scheduled, or Completed. |

To learn more about how Remediation retains data, see [ZDX Logs](https://help.zscaler.com/logs-fair-use/zdx-logs).

### Active Directory & OpenLDAP Synchronization

The following table shows the ranges and limitations for Active Directory (AD) and OpenLDAP synchronization:

| Feature | Limit |
| --- | --- |
| Primary/Secondary Directory Name | 255 characters |
| Authentication Agent URL | 1,023 characters |
| Directory Server Address | 1,023 characters |
| Port | 0–65,535 ports |
| Bind DN | 255 characters |
| Bind Password | 255 characters |
| Base DN | 1,023 characters |
| User Login | 255 characters |
| User Full Name | 255 characters |
| User Search Filter | 1,023 bytes |
| Department Membership | 255 characters |
| Group Name | 255 characters |
| Group Membership (AD only) | 255 characters |
| Group Search Filter | 1,023 bytes |
| Group Base DN (OpenLDAP only) | 255 characters |
| User Attribute (OpenLDAP only) | 255 characters |
| User Membership (OpenLDAP only) | 255 characters |
| User Entry | 1,023 characters |
| Users/Groups/Departments Search (Synchronization Results) | 255 characters |
| User Authentication Filter | 1,023 bytes |
| Test User Login | 255 characters |
| Test User Password | 255 characters |

### Advanced Threat Protection

The following table shows the ranges and limitations for blocked malicious URLs:

| Feature | Limit |
| --- | --- |
| Blocked Malicious URLs | 25K FQDNs, domains, or URLs |

### Data Loss Prevention

The following table shows the ranges and limitations for Data Loss Prevention (DLP):

| Feature | Limit |
| --- | --- |
| Custom DLP Dictionaries | 801 dictionaries (with DLP Dictionary Expansion enabled); 160 dictionaries (with DLP Dictionary Expansion disabled) |
| Custom DLP Parent Dictionaries | 64 dictionaries |
| Custom DLP Sub-Dictionaries | 63 per parent dictionary |
| Custom DLP Engines | 480 engines (with DLP Engine Expansion enabled); 58 engines (with DLP Engine Expansion disabled) |
| DLP Incident Evidence Files | 100 MB If an evidence file for a DLP incident exceeds 100 MB in size, the Zscaler service replaces the original file with a placeholder evidence file. The placeholder file retains the original name with ".txt" appended (e.g., attachment.pdf.txt and samplefile.xlsx.txt). When the placeholder is open or downloaded, the placeholder provides a message indicating that the file is too large to send. |

### Departments

The following table shows the ranges and limitations for departments:

| Feature | Limit |
| --- | --- |
| Departments per Organization | 140K departments |
| Departments per admin with Department Scope | 2,048 departments |
| Department Name | 128 characters |
| Comments | 10,240 KB |
| Imported Departments per CSV file | 3,000 entries |

### Endpoint Data Loss Prevention

The following table shows the ranges and limitations for Endpoint DLP:

| Feature | Limit |
| --- | --- |
| CSV Import File Size for DLP Resources | 20 MB/1,024 Rows |
| Exception Rules per Parent Rule | 32 exception rules |
| Policy Rules | 1,023 total policy rules (parent rules + exception rules) |
| Resource Items per Resource Type | 1,024 resource items (printers, network shares, etc.) |
| Resources per Resource Group | 1,024 resources (e.g., a printer group can contain up to 1,024 printers) |
| Resource groups per Resource Type for Each Channel | 256 resource groups (e.g., 256 printer groups per organization) |

### EUNs

The following table shows the ranges and limitations for EUNs:

| Feature | Limit |
| --- | --- |
| Custom Messages for Zscaler Client Connector-Based EUNs | 64 custom messages |
| Notification Message Length for Zscaler Client Connector-Based EUNs | 500 characters |
| Custom Redirect URL | 1,023 characters |
| Notification Message | 15K bytes |
| AUP Message | 30K bytes |
| URL Categorization Notification | 15K bytes |
| Security Violation Notification | 15K bytes |
| DLP Violation Notification | 15K bytes |
| Caution Notification Text | 15K bytes |
| Support Phone Number | 20 characters |
| Policy Link | 1,023 characters |
| IT Support Email | 254 characters |

### Extranet

The following table shows the ranges and limitations for extranet:

| Feature | Limit |
| --- | --- |
| Extranet resources | 1,000 extranets |
| Extranet locations | 5,000 extranet locations |
| Traffic selectors per extranet | 16 traffic selectors |
| DNS servers per extranet | 16 DNS servers |

### Groups

The following table shows the ranges and limitations for groups:

| Feature | Limit |
| --- | --- |
| Group Name | 128 characters or 127 bytes |
| Comments | 10,240 bytes |
| Imported Groups per CSV file | 3,000 entries |
| Network Service Groups | 121 groups |
| Network Services per Group | 8,000 network services |
| Network Application Groups | 126 groups |
| Network Applications per Group | 8,000 network applications |
| Custom Application Service Groups | 128 groups |
| IP Address or Wildcard FQDN per Custom Application Service Group | 32 entries |
| Source IP Address Groups | 4,000 groups |
| Destination Groups (Destination IP or FQDN Groups) | 4,000 groups |
| FQDNs or IP Address Entries per Group | 8,000 IP address entries The total number of IP entries across groups must adhere to the overall base IP limit, as noted in the Other section. |

### HTTP Header Control

The following table shows the ranges and limitations for HTTP Header Control:

| Feature | Limit |
| --- | --- |
| HTTP headers per HTTP Header Profile | 16 HTTP headers |
| HTTP header profiles per Rule | 16 HTTP header profiles |
| HTTP headers per HTTP Header Insertion Profile | 16 HTTP headers |
| HTTP header insertion profiles per Rule | 16 HTTP header insertion profiles |
| HTTP header (Key) | Up to a maximum of 128 characters. |
| Value | Up to a maximum of 1,024 characters. |

### Locations

The following table shows the ranges and limitations for locations:

| Feature | Limit |
| --- | --- |
| Locations and Sublocations per Organization | 32K locations Contact Zscaler Support for a possible increase in limit from 32K locations to 64K locations. Requires Advanced Firewall. |
| Sublocations per Location | 2,000 sublocations |
| IP Address Ranges per Sublocation | 2,000 IP address ranges |
| Scopes per Workload traffic type Sublocations |  |
| Namespace Scope per Sublocation 10 scope values | 10 scope values |
| Account Scope per Sublocation | 10 scope values |
| VPC Scope per Sublocation | 50 scope values |
| VPC Endpoint Scope per Sublocation | 50 scope values |
| Location Name | 128 characters |
| Location State | 128 characters |
| Location Groups per Organization | 256 groups |
| Locations and Sublocations per Group | 32K locations Contact Zscaler Support for a possible increase in limit from 32K locations to 64K locations. Requires Advanced Firewall. |
| Imported Locations per CSV file | 1,000 entries |
| Download CSV file | One file per hour |

### NSS

The following table shows the ranges and limitations for Nanolog Streaming Service (NSS):

| Feature | Limit | Comments |
| --- | --- | --- |
| Number of Users per NSS Feed Filter | 1,024 users |  |
| Number of Departments per NSS Feed Filter | 1,024 departments |  |
| Number of Locations per NSS Feed Filter | 1,024 locations |  |
| Number of Clients per NSS Feed Filter | 1,024 clients |  |
| Number of Threat Names per NSS Feed Filter | 1,024 threat names |  |
| Number of Web Transactions per Nanolog Cluster | 1 billion web transactions | If your organization surpasses more than 1 billion web transactions, additional Nanolog clusters are required. |
| Number of Nanolog Clusters per NSS Virtual Machine (VM) Server | 1 Nanolog cluster | If additional Nanolog clusters are required, your organization must support an adequate number of NSS VM servers. |

### Organization

The following table shows the ranges and limitations for the organization:

| Feature | Limit |
| --- | --- |
| Address Line 1 | 10,240 bytes |
| Address Line 2 | 10,240 bytes |
| City/State/ZIP | 1,024 bytes |
| Name/Title/Phone/Alternate Phone | 1,024 bytes |
| Admin Users per Organization | 10K admins |
| Admin User Login ID | 128 characters |
| Admin User Email | 254 characters |
| Admin User Name | 256 characters |
| Admin User Comments | 10,240 bytes |
| Admin User Password | 100 characters |
| ADP Clients | 16 clients |
| Admin Roles | 64 roles |
| API Roles | 16 roles |
| Identity Providers | 64 identity providers |

### Outbound Email Data Loss Prevention

The following table shows the ranges and limitations for outbound email DLP:

| Feature | Limit |
| --- | --- |
| Domain Profiles per Organization | 32 profiles Contact Zscaler Support for a possible increase in this limit. |
| Recipient Profiles per Organization | 32 profiles Contact Zscaler Support for a possible increase in this limit. |
| Recipients per Recipient Profile | 32 recipients Contact Zscaler Support for a possible increase in this limit to 8,192 total recipients. |
| Domain Profiles per Rule | 8 profiles |
| Recipient Profiles per Rule | 8 profiles |
| Custom Domains per Domain Profile | 32 domains Contact Zscaler Support for a possible increase in this limit from 32 domains to 1,024 domains. |
| Outbound Email Policies | 1,024 policies |

### PAC File

The following table shows the ranges and limitations for PAC files:

| Feature | Limit |
| --- | --- |
| Name | 255 characters |
| Description | 255 characters |
| File Size | 256 KB |
| PAC Files per Organization | 256 PAC files Contact Zscaler Support to increase the limit of PAC files to 1,024. |
| Non-ASCII Characters | The file can contain up to 12% of non-ASCII characters (binary). |

### Policies

The following table shows the ranges and limitations for policies and rules:

| Feature | Limit | Comments |
| --- | --- | --- |
| Bandwidth Control Policy Rules per Organization | 125 rules |  |
| Cloud App Control Policy Rules per Cloud App Category | 127 rules | Contact Zscaler Support for a possible increase in the limit of Cloud App Control policy rules per cloud app category from 127 to 2,048. |
| File Type Control Policy | 2,048 rules |  |
| SaaS Security API Scans |  |  |
| Amazon S3 | 1,000 buckets | To enable scanning of up to 1,000 Amazon S3 buckets, contact your Zscaler Account team. |
| Bitbucket | 32 repositories |  |
| Google Cloud Platform | 1,000 buckets | To enable scanning of up to 1,000 Google Cloud Platform buckets, contact your Zscaler Account team. |
| Microsoft Azure | 1,000 blob containers | To enable scanning of up to 1,000 Azure blob containers, contact your Zscaler Account team. |
| DNS Control Policy Rules per Organization | 1,000 rules, if subscribed to Advanced DNS Control. Only 64 rules are supported for Essential DNS Control. |  |
| NAT Control Policy Rules per Organization | 1,023 rules |  |
| Firewall Filtering Policy Rules (including DNAT) per Organization | 1,021 rules, if subscribed to Advanced Firewall. Only 10 rules are supported for Standard Firewall. | An increased limit of 4,000 rules is available for the Firewall Filtering policy, subject to Zscaler's strict criteria requirements and restricted availability (also requires Advanced Firewall). To learn more about the enhanced limit and qualified use cases for an upgrade, contact your Zscaler Account team. |
| Source IP/Destination Groups IP Address Entries and FQDNs per Organization | 16K IP address entries | Contact Zscaler Support for a possible increase in limit from 16K IP address entries to 64K IP address entries. Requires Advanced Firewall. For destination-side increases, use the Custom URLs subscription and contact Zscaler Support to subscribe to an additional limit of 50K custom URLs. To learn more, see the URL Filtering & Cloud App Control section. |
| Destination Groups FQDNs per Organization | 5,000 address entries 16K address entries with Advanced Firewall |  |
| Source IP Groups IP Address Entries per Rule | 8,000 IP address entries |  |
| Destination Groups IP Address Entries and FQDNS per Rule | 8,000 IP address entries |  |
| Source IP/Destination Groups per Rule | 1,000 groups |  |
| Service Groups/Application Groups per Rule | 1,000 groups |  |
| Destination Groups FQDNs per Rule | 5,000 address entries |  |
| Destination Groups IP Address Entries and FQDNs per Group | 8,000 IP address entries |  |
| Destination Groups FQDNs per Group | 100 address entries 8,000 address entries with Advanced Firewall |  |
| URL Filtering Policy Rules | 1,000 rules | Contact Zscaler Support for a possible increase in the limit of URL filtering policy rules. |
| Forwarding Policy Rules per Organization | 1,000 rules |  |
| Third-Party Proxies | 8 proxies | The default limit for third-party proxies is 8. Contact Zscaler Support to increase the limit to a maximum of 255 proxies. |
| Gateways for Third-Party Proxies | 8 gateways | The default limit for third-party proxy gateways is 8. Contact Zscaler Support to increase the limit to a maximum of 255 gateways. |
| Private Access Gateways Rules | 55 gateways |  |
| Source IP Anchoring Application Segments | 255 segments |  |
| SSL Inspection Policy Rules | 255 rules (245 custom rules and 10 predefined rules) |  |
| All Other Policy Rules (i.e., DLP Policy, Inline Web DLP Policy, IPS Control Policy, etc.) | 1,024 rules | Contact your Zscaler Account team for a possible increase in this limit from 1,024 rules to 2,048 rules. |
| All Policy Rule Types: |  |  |
| Users per Rule | 32 users |  |
| Groups per Rule | 32 groups |  |
| Departments per Rule | 32 departments |  |
| Locations per Rule | 32 locations |  |
| Location Groups per Rule | 32 groups |  |
| Rule Labels | 1,024 labels |  |
| Times per Rule | 8 times |  |
| Devices per Rule | 64 devices |  |
| Device Groups per Rule | 8 device groups |  |
| Workload Groups per Rule | 8 workload groups |  |
| Comments | 10,240 bytes | Some languages use multi-byte characters, so they have fewer characters than bytes. |
| File Type Control Policy File Size | 400 MB |  |

### Reporting

The following table shows the ranges and limitations for reporting:

| Feature | Limit |
| --- | --- |
| Interactive Report Name | 50 characters |
| Widget Name | 50 characters |
| Widgets | 20 widgets |
| Favorites per User | 50 favorites |
| Scheduled Report Recipient (i.e., Email Address) | 254 characters |
| Export to CSV (Web, Mobile, Firewall, DNS, and Tunnel Insights Logs) | 20 requests/hour |

### SaaS Application Tenants

The following table shows the ranges and limitations for SaaS application tenants:

| Feature | Limit | Comments |
| --- | --- | --- |
| Number of tenants per SaaS application | 16 tenants | Up to 16 tenants can be added for each sanctioned SaaS application. Contact Zscaler Support for a possible increase in this limit. |
| Number of external trusted domain and user profiles per application | 32 profiles | Up to 32 profiles can be added for each sanctioned SaaS application. To learn more, see [Adding SaaS Application Tenants](https://help.zscaler.com/zia/adding-saas-application-tenants) and [About Email Profiles](https://help.zscaler.com/zia/about-email-profiles). |

### URL Filtering & Cloud App Control

The following table shows the ranges and limitations for URL Filtering and Cloud App Control:

| Feature | Limit | Comments |
| --- | --- | --- |
| Custom Keywords per Category | 256 keywords | There can be a maximum of 2,048 custom keywords across all categories. |
| Keywords retaining parent category per Category | 2,048 keywords | There can be a maximum of 2,048 keywords retaining the parent category across all categories. |
| Total Custom Keywords and Keywords retaining parent category per Organization | 2,048 keywords |  |
| Custom URLs/TLDs | 25K URLs/TLDs | Includes: Custom URLs/TLDs in all URL Categories/TLD Categories; Auth Exemption URLs in Advanced settings; Blocked Malicious URLs in Advanced Threat Protection settings; Blocked URLs in SSL Inspection settings; Allowed URLs in FTP Control settings; Bandwidth Class DomainsDuplicate URLs/TLDs are counted once. The default limit for custom URLs/TLDs is 25K. Contact your Zscaler Account team to subscribe to up to an additional 50K custom URLs/TLDs. You can subscribe up to 5 times to additional URLs/TLDs, which are added beyond the default limit at an additional cost and license. |
| Do Not Scan Content from these URLs | 1,024 URLs |  |
| Custom Categories/TLD Categories | 64 categories | The default limit for custom categories is 64. Contact Zscaler Support to increase the limit to a maximum of 1,024 categories. |
| Custom Cloud Applications per Organization | 64 applications |  |
| URLs per Custom Cloud Application | 128 URLs |  |
| URLs | 253 characters |  |
| IP Ranges | 2,048 IP ranges |  |
| Cloud Application Instance | 512 cloud application instances | Contact Zscaler Support for a possible increase in the limit of cloud application instances from 512 to 4,096. |
| Instance Identifiers | 1,024 instance identifiers | Each instance identifier can have up to 128 characters. There can be a maximum of 2,048 instance identifiers across all instances. Contact Zscaler Support for a possible increase in the limit of instance identifiers across all instances per organization from 2,048 to 8,192. |
| Cloud Application Instance per Rule | 8 cloud application instances |  |
| Cloud Application Tags per Organization | 16 tags | Each tag can have up to 127 characters. |
| Tenant Profiles per Rule | 16 tenant profiles | Each Cloud App Control Policy rule can have up to 16 tenant profiles associated with it. |
| Amazon Web Services | 256 account IDs | Each account ID must have 12 digits. There can be a maximum of 2,048 account IDs across all profiles. |
| ChatGPT | 128 workspace IDs | Each workspace ID can have up to 64 characters. You can associate a maximum of 16 tenant profiles or 20 workspace IDs per rule. |
| Dropbox Team ID | 100 team IDs | Each team ID can have up to 64 characters. |
| GitHub | One enterprise slug | Each enterprise slug can have up to 256 characters. There can be a maximum of 100 tenant profiles per organization. You can associate only one tenant profile per rule. |
| Google App Domains | 100 domains | Each domain name can have up to 160 characters. There can be a maximum of 2,048 domains across all profiles. |
| Google Cloud Platform | 100 organization IDs | Each organization ID can have up to 64 characters. There can be a maximum of 2,048 organization IDs across all profiles. |
| IBM SmartCloud | 100 account IDs | Each account ID can have up to 64 characters. There can be a maximum of 100 account IDs per rule and 256 account IDs across all profiles. |
| Microsoft Login Services (Version 1) Tenant Directory ID | One tenant directory | Each tenant directory can have up to 64 characters. |
| Microsoft Login Services (Version 2) Tenant Directory ID:Policy ID | One tenant directory:policy ID | Each tenant directory:policy ID can have up to 256 characters. |
| Microsoft Login Services (Version 1) Microsoft 365 Tenants or Tenant IDs | 500 Microsoft 365 tenants | Each Microsoft 365 tenant or tenant ID can have up to 64 characters. |
| Slack Your Workspace ID | 100 workspace IDs | Each workspace ID can have up to 64 characters. |
| Slack Allowed Workspace ID | 256 workspace IDs | Each workspace ID can have up to 64 characters. |
| YouTube Channel ID | 200 channel IDs | Each channel ID can have up to 100 characters. |
| YouTube School ID | 100 school IDs | Each school ID can have up to 127 characters. |
| Webex Login Services | 100 Webex tenants | There can be a maximum of 250 tenants across all profiles. |
| Zoho Login Services | 120 Zoho IDs | Each Zoho ID can have up to 127 characters. There can be a maximum of 2,048 Zoho IDs across all profiles. |
| Zoom | One policy label | Each policy label can have up to 64 characters. |

### Users

The following table shows the ranges and limitations for users:

| Feature | Limit |
| --- | --- |
| Users per Organization | 1,400K users |
| User Name | 128 characters |
| User Password | 255 characters |
| Groups per User | 127 groups by default |
| Comments | 10,240 bytes |
| Imported Users per CSV file | 3,000 entries |
| User Groups per Organization | 140K groups |
| User Temporary Authentication Email | 254 characters |

### VPN Credentials

The following table shows the ranges and limitations for VPN credentials:

| Feature | Limit |
| --- | --- |
| VPN Credentials per Organization | 16K credentials Contact Zscaler Support for a possible increase in limit from 16K credentials to 64K IP credentials. Requires Advanced Firewall. |
| Imported VPN Credentials per CSV file | 3,000 entries |
| User ID (for FQDN authentication type) | 256 characters |
| Pre-Shared Key (for FQDN and IP authentication types) | 255 characters |
| Comments | 10,240 bytes |

### Static IPs

The following table shows the ranges and limitations for static IPs:

| Feature | Limit |
| --- | --- |
| Static IP Address Entries per Organization | 100 IP address entries by default Contact Zscaler Support to increase the limit for your organization. |
| Imported Static IPs per CSV file | 3,000 entries |
| Description | 10,240 characters |

### Other

The following table shows the ranges and limitations for other Internet & SaaS features:

| Feature | Limit |
| --- | --- |
| String Inputs (All features) | **Accepted characters**: Letters (both uppercase A–Z and lowercase a–z), numbers (0–9), underscores (_), hyphens(-), periods(.), and spaces. Inputs must begin with at least one letter, number, underscore, hyphen, or period, and can optionally include spaces or additional valid characters afterward. For example: test_user1, File-Name_123.txt, and my_file with_spaces are accepted string inputs. **Characters not accepted**: Special characters such as #, @, or $. For example: abc!@# or #file1 are not accepted string inputs. |
| Source IP and Destination Groups | 4,000 groups |
| IP Address Entries or FQDNs per Group | 8,000 IP address entries The total number of IP entries across groups must adhere to the overall base IP limit. |
| IP Address Entries per Organization | 16K IP address entries The limit for destination IP entries can be increased by using [Custom URL Categories](https://help.zscaler.com/zia/about-url-categories) with Custom URLs and Custom IP Ranges. This applies only to the destination IP addresses. To learn more, see the URL Filtering & Cloud App Control section. |
| Predefined Bandwidth Classes | 8 classes |
| Custom Bandwidth Classes | 245 classes |
| Bandwidth Class Name | 255 characters |
| Time Intervals | 64 intervals |
| Virtual Service Edge Nodes per Cluster | 16 nodes |
| Exported Transactions | 100K entries |
| Admin Role Name | 128 characters |
| SAML Certificate Filename | 128 characters |
| SAML Certificate Key Name | 1,024 characters |
| Alerts | 128 alerts |
| Alert Definition Comments | 10,240 bytes |
| Alert Subscription Email | 254 characters |
| Restore Point Name | 128 characters |
| Restore Point Description | 10,240 bytes |
| ICAP Name | 128 characters |
| ICAP Receiver URL | 128 characters |
| Firewall Network Services | 832 services |
| Network Service Name | 255 characters |
| Network Service Description | 1,024 bytes |
| Custom IPS Signature Rules | 500 signature rules |
| Custom IPS Threat Categories | 64 threat categories |
| Auditor Email | 254 characters |
| Admin Audit Log | 1,000 entries |
| Workload Groups | 1,024 entries |
| SCIM Servers | 5 requests/second |
| EDNS Client Subnet (ECS) Prefix Objects per Organization | 128 prefixes |
| DNS Gateways | 254 DNS Gateways |
| Custom Path URL Length for DNS Gateway Server | 1,024 characters |
| URL Length in Destination Group | 255 characters |
| Sub-URL Length in Insight Logs display and CSV file | 2,041 characters Sub-URLs are truncated if they exceed the character limit. |
| Remote Assistance View-Only and Full Access | 90 days |

### Administration

The following table shows the ranges and limitations for administration:

| **Feature** | **Limit** |
| --- | --- |
| Admins | 5,000 admins |
| Roles | 100 roles |

### App Connector Management

The following table shows the ranges and limitations for [App Connector](https://help.zscaler.com/zpa/app-connector-management) management:

| **Feature** | **Limit** |
| --- | --- |
| App Connectors | 100 App Connectors |
| App Connector Groups | 100 groups |
| App Connector Provisioning Keys | 100 keys |

### Application Management

The following table shows the ranges and limitations for application management:

| **Feature** | **Limit** |
| --- | --- |
| Applications | 6,000 applications Contact Zscaler Support to increase the limit up to 50K. This is only available with certain versions of Zscaler Client Connector: Version 4.0 and later for Windows; Version 4.1 and later for macOS; Version 3.9 and later for Android; Version 4.4 and later for iOS; Version 4.2 and later for Linux2,000 applications per application segment The 2,000 applications per application segment limit applies to both IPs and domains. Wildcards also fall in the same category (i.e., every entry for the application in the Zscaler Admin Console counts as one). 4,000 Source IP Anchoring-enabled domains or IP addresses DNS resolution can resolve a single domain (such as example.com or host.example.com) to no more than 200 IP addresses on the App Connector. The cloud can only handle up to 100 TXT records for any domain that it looks up. The DNS TXT records are ignored if the lookup surpasses 100 DNS TXT records. 1,024 port pairs If Multimatch is enabled, similar domains such as *.example.com, Abc.example.com, and Xyz.example.com are merged and considered the same domain when counting the first 1,024 port pairs. If disabled, each of those domains is considered separate when counting the first 1,024 port pairs. |
| Application Segments | 6,000 segments 255 Source IP Anchoring-enabled segments |
| DNS Suffixes | 50 suffixes |
| Segment Groups | 200 groups |
| Servers | 10,000 servers |
| Server Groups | 1,000 groups |
| Pattern Matching | 500 patterns |

### AppProtection Management

The following table shows the ranges and limitations for AppProtection management:

| **Feature** | **Limit** |
| --- | --- |
| Custom Control Parameters | 100 custom control parameters per custom control 100 custom control parameters per AppProtection profile |

### Authentication

The following table shows the ranges and limitations for authentication configuration:

| **Feature** | **Limit** |
| --- | --- |
| IdP Configurations | 10 configurations |
| SAML Attributes | 100 attributes |

### Backup and Restore

| Feature | Limit |
| --- | --- |
| Backups | 10 backups per day The 10 backups per day limit applies to manually added backups, scheduled backups, and backups that are created within a Microtenant and have a **Completed** or **In Progress** status. |
| Restores | 10 restores per day |

### Browser Protection Management

The following table shows the ranges and limitations for Browser Protection:

| **Feature** | **Limit** |
| --- | --- |
| Monitored Users | 20,000 users |

### Business-to-Business (B2B) Federation

The following table shows the ranges and limitations for [B2B Federation](https://help.zscaler.com/zpa/understanding-business-business-b2b-federation):

| Feature | Limit |
| --- | --- |
| Federated Applications | 1,000 federated applications |
| Partners | 25 partners |

Contact Zscaler Support to upgrade Business-to-Business (B2B) Federation limits.

### Certificate Management

The following table shows the ranges and limitations for certificate management:

| **Feature** | **Limit** |
| --- | --- |
| (web server) Certificates | 1,000 certificates |
| Enrollment Certificates | 1,000 certificates |

### Client Type Management

The following table shows the ranges and limitations for client type management:

| **Feature** | **Limit** |
| --- | --- |
| Client Type Microtunnel (M-Tunnel) Requests | 100 M-Tunnels per second The 100 M-Tunnels per second limit applies to the Zscaler Client Connector, Web Browser, Web Browser Unauthenticated, or Internet & SaaS Public Service Edge client types. To learn more, see [Accessing User Activity Diagnostics](https://help.zscaler.com/zpa/accessing-user-activity-diagnostics). The 100 M-Tunnels per second limit can be changed. To learn more, contact Zscaler Support. |

### Cloud Connector Management

The following table shows the ranges and limitations for Cloud Connector management:

| **Feature** | **Limit** |
| --- | --- |
| Cloud Connector M-Tunnel Requests | 200 M-Tunnels per second The 200 M-Tunnels per second limit can be changed. To learn more, contact Zscaler Support. |
| Workload Groups | 64 workload groups |

### Identity Management

The following table shows the ranges and limitations for identity management:

| **Feature** | **Limit** |
| --- | --- |
| SCIM updates | 50 per second |
| SCIM Groups | 1,000 groups per user The 1,000 groups per user limit means that if a user is a part of more than 1,000 groups, the remaining groups are not synced until some of them are removed for the user on the IdP. There is no limit to the number of SCIM groups that can be synced. |

### Machine Management

The following table shows the ranges and limitations for machine management:

| **Feature** | **Limit** |
| --- | --- |
| Machine Groups | 100 groups |

### Microtenants

The following table shows the ranges and limitations for [Microtenants](https://help.zscaler.com/zpa/about-microtenants):

| **Feature** | **Limit** |
| --- | --- |
| Microtenants | 100 Microtenants |

### Organization

The following table shows the organization ranges and limitations for the organization:

| **Feature** | **Limit** |
| --- | --- |
| Admin User Password | 100 characters |

### Policies

The following table shows the ranges and limitations for policies and rules:

| Feature | Limit |
| --- | --- |
| Access Policy | 2,000 policy rules 1,000 application segments per policy rule 48 App Connector groups per policy rule The 48 App Connector groups per policy rule limit applies even if **All App Connector groups for the application** is selected when configuring an access policy rule. To learn more, see [Configuring Access Policies](https://help.zscaler.com/zpa/configuring-access-policies). 50 locations for extranet application support 10 location groups for extranet application support |
| AppProtection Policy | 500 policy rules 1,000 application segments per policy rule |
| Client Forwarding Policy | 500 policy rules 1,000 application segments per policy rule |
| Isolation Policy | 500 policy rules 1,000 application segments per policy rule |
| Log Receiver Policy | 1,000 application segments per policy rule |
| Consoles Policy | 5,000 policy rules 200 privileged consoles per consoles policy |
| Privileged Credentials Policy | 5,000 policy rules 1,000 privileged consoles per privileged credentials policy |
| Redirection Policy | 2,000 policy rules |
| Timeout Policy | 500 policy rules 1,000 application segments per policy rule |

### Private Applications Private Service Edge Management

The following table shows the ranges and limitations for Private Access Private Service Edge management:

| **Feature** | **Limit** |
| --- | --- |
| Private Service Edges | 100 Private Service Edges |
| Private Service Edge Groups | 100 groups |
| Private Service Edge Provisioning Keys | 100 keys |

### Private Cloud Controller Management

The following table shows the ranges and limitations for Private Cloud Controller management:

| Feature | Limit |
| --- | --- |
| Private Cloud Controllers | 100 Private Cloud Controllers |
| Private Cloud Controller Groups | 100 Private Cloud Controller groups |
| Private Cloud Controller Provisioning Keys | 100 keys |
| Maximum Number of Backups | 100 backups |

### Privileged Remote Access

The following table shows the ranges and limitations for Privileged Remote Access (PRA):

| **Feature** | **Limit** |
| --- | --- |
| Privileged Approvals | 20,000 privileged approvals 200 application segments per privileged approval Only privileged approvals with approval statuses of Future or Active are counted. Privileged approvals with an approval status of Expired are not considered as part of the total amount. Each user can create up to 20 privileged approval requests on the [My Requests](https://help.zscaler.com/zpa/about-my-approvals) page in the Privileged Remote Access (PRA) Portal. |
| Privileged Consoles | 10 privileged consoles With a license, this can be increased to the maximum limit of 9,000 privileged consoles. Contact your Zscaler Account team for more information. |
| Privileged Credentials | 10,000 privileged credentials |
| Privileged Credential Pools | 500 privileged credential pools 100 privileged credentials per privileged credential pool |
| Privileged Portals | 100 privileged portals When configuring a privileged portal within the Default [Microtenant](https://help.zscaler.com/zpa/about-microtenants), you can link a maximum of 9,000 privileged consoles to the privileged portal. |

### Support Information Management

The following table shows the ranges and limitations for support information management:

| **Feature** | **Limit** |
| --- | --- |
| App Connectors | 100 App Connectors per session |
| Private Service Edges | 100 Private Service Edges per session |
| Private Cloud Controllers | 100 Private Cloud Controllers per session |
| Actions | 10 Actions per session |
| Targets | 10 Targets per session |
| Concurrent Sessions | 5 Concurrent Sessions per customer The 5 Concurrent Sessions per customer limit is only on sessions that are in a Pending or Processing state. There is no limit on Completed, Failed, or Partially_Completed sessions. |

### Tag Management

The following table shows the ranges and limitations for tag management:

| **Feature** | **Limit** |
| --- | --- |
| Tags applied to all applications per scope | 25,000 tags per scope |
| Tags applied to all applications per customer | 25,000 tags per customer |

### User Portal

The following table shows the ranges and limitations for User Portal:

| **Feature** | **Limit** |
| --- | --- |
| Portal Links | 500 links You can only configure 150 links at a time when [configuring portal links](https://help.zscaler.com/zpa/configuring-user-portal-links) in the Zscaler Admin Console. |

### VPN (for Legacy Apps)

The following table shows the ranges and limitations for VPN (for Legacy Apps):

Some limits can be adjusted based on user count and onboarded applications.

| **Feature** | **Limit** |
| --- | --- |
| Number of Network Connectors per Customer | 100 per customer |
| Number of VPN Service Edge Regions | 3 per customer |
| Number of VPN Service Edges per Customer | 3 per customer |
| Number of Network Connector Groups per Customer | 100 per customer |
| Number of LAN Subnets (Network Segments) per Network Connector Group | 64 per Network Connector group |
| Number of Client Subnets per VPN Service Edge | 16 per VPN Service Edge |
| Number of Client IP Pools per VPN Service Edge | 16 per customer |
| Number of LAN Subnets per Connector Group | 1,024 |
| Number of LAN IP Addresses and Subnets per Network Segment | 64 per Network Segment |
| VPN Access Policy | 1,000 access policy rules 64 IP subnets or IP addresses for source network per policy 64 IP subnets or IP addresses for destination network per policy 128 address book objects 64 IP subnets or IP addresses per address book object |
| Number of External Routers | 64 per customer |

### Groups

The following table shows group ranges and limitations:

| Feature | Limit |
| --- | --- |
| Group Name | 255 characters |
| Network Services Groups | 126 groups |
| Source IP Address Groups | 4,000 groups |
| Destination Groups (Destination IP or FQDN Groups) | 4,000 groups |
| Fully Qualified Domain Names (FQDNs) or IP Addresses per Group | 8,000 addresses |
| Cloud Connector Groups | 1,000 groups |
| Branch Connector Groups | 1,000 groups |
| Virtual Machines (VMs) per Group | 16 VMs |
| Application Segment Groups | 600 groups |

### Locations

The following table shows location ranges and limitations:

| Feature | Limit |
| --- | --- |
| Locations | 1,000 locations |
| Location Name | 255 characters |
| Location Templates | 500 templates |

### NSS

The following table shows ranges and limitations for Nanolog Streaming Service (NSS) filter feeds:

| Feature | Limit |
| --- | --- |
| NSS Servers | 2 servers |
| NSS Feeds per Server | 8 feeds |
| NSS Users per Feed | 1,024 users |
| NSS Departments per Feed | 1,024 departments |
| NSS Locations per Feed | 1,024 locations |
| NSS Clients per Feed | 1,024 clients |
| NSS Threat Names per Feed | 1,024 threat names |

### Organization

The following table shows the ranges and limitations for information related to the organization:

| Feature | Limit |
| --- | --- |
| Address Line 1 | 10,240 bytes |
| Address Line 2 | 10,240 bytes |
| City/State/ZIP | 1,024 bytes |
| Name/Title/Phone/Alternate Phone | 1,024 bytes |
| Admin Users per Organization | 10K admins |
| Admin User Login ID | 128 characters |
| Admin User Email | 254 characters |
| Admin User Name | 256 characters |
| Admin User Comments | 10,240 bytes |
| Admin User Password | 100 characters |
| Admin Roles | 64 roles |
| Identity Providers | 16 identity providers |

### Forwarding

The following table shows the forwarding ranges and limitations:

| Feature | Limit |
| --- | --- |
| Traffic Forwarding Rules | 1,020 rules, 8 locations or sublocations, 32 Cloud Connector or Branch Connector groups |
| Log & Control Forwarding Rules | 1,020 rules |
| Domains/FQDNs per Organization | 1,024 domains/FQDNs |
| Domains/FQDNs per Rule | 1,024 domains/FQDNs |
| ZIA Gateways | 1,000 gateways |
| Log and Control Gateways | 1,000 gateways |
| DNS Gateways | 255 gateways |
| DNS Control Policy Rules | 1,020 rules |
| Application Segments | 50,000 segments |
| Description | 10,240 characters |

### Provisioning and Configuration

The following table shows the provisioning and configuration ranges and limitations:

| Feature | Limit |
| --- | --- |
| Cloud Provisioning Templates | 1,000 templates |
| Branch Configuration Templates | 1,000 templates |
| Branch Connector Static Routes | 32 routes |
| Branch Connector DHCP Server Static Leases | 32 leases |
| Branch Connector Virtual local area networks (VLANs) per Interface | 9 subinterface VLANs and one main untagged VLAN per interface, or 10 subinterface VLANs without the main untagged VLAN |

### Zero Trust Gateways

| **Feature** | **Limit** |
| --- | --- |
| Amazon Web Services (AWS) Accounts per Gateway | 128 AWS accounts |
| Traffic Tests | 128 tests |
| Endpoints | 128 endpoints per availability zone (AWS recommends 50 per availability zone) |

### Partner Integrations

| **Feature** | **Limit** |
| --- | --- |
| AWS Accounts per Organization | 512 accounts |
| AWS Accounts per Group | 96 accounts |
| Regions in an AWS Account | 32 regions |
| AWS Groups per Organization | 128 groups |
| Cloud Connector Groups per AWS Group | 64 groups |
| Microsoft Azure Accounts per Organization | 128 accounts |
| Regions in an Azure Account | 32 regions |
| Subscriptions in an Azure Account | 128 subscriptions |
| Cloud Connector Groups in an Azure Account | 64 groups |
| Google Cloud Platform (GCP) Accounts per Organization | 128 accounts |
| Projects in a GCP Account | 64 projects |
| Cloud Connector Groups in a GCP Account | 64 groups |

### Advanced Settings

The following table shows the ranges and limitations for Advanced Settings:

| Feature | Minimum Limit | Maximum Limit |
| --- | --- | --- |
| Session Timeout | 5 seconds | 600 minutes |

### API Clients

The following table shows the ranges and limitations for API Clients:

| Feature | Limit |
| --- | --- |
| API clients per tenant | 256 |

### External Identities

The following table shows the ranges and limitations for secondary identity providers (IdPs):

| Feature | Limit |
| --- | --- |
| Number of secondary IdPs a tenant can have | 64 |
| SCIM read requests (GET) per IdP/second | 50 |
| SCIM write requests (POST/PUT/DELETE) per IdP/second | 50 |

### IP Locations

The following table shows the ranges and limitations for IP locations:

| Feature | Limit |
| --- | --- |
| Total IP Locations | 65,536 |
| Total IP addresses within a single IP Location | 2,048 |

### IP Location Groups

The following table shows the ranges and limitations for IP location groups:

| Feature | Limit |
| --- | --- |
| Total IP Location Groups | 4,096 |
| IP Locations within a single Location Group | 2,048 |

### Policies

The following table shows the ranges and limitations for policies:

| Feature | Minimum Limit | Maximum Limit |
| --- | --- | --- |
| Admin Sign-On Policy: Total policy numbers and maximum criteria within a single policy | 0 | 256 |
| Password Policy: Password expiration period | 15 days | 365 days |

### Users, Groups, and User Attributes

The following table shows the ranges and limitations for users, groups, and user attributes:

| Feature | Limit |
| --- | --- |
| Maximum groups allowed per user | 128 |
| Maximum groups per tenant | No limit |
| User attributes | 128 |

### Zscaler Services

The following table shows the ranges and limitations for Zscaler services:

| Feature | Limit |
| --- | --- |
| User or group assignment using the Add operation | 4,096 |
| Maximum admins for each service | 10,000 |

### File Transfers

The following table shows the ranges and limitations for file transfers in isolation.

| Feature | Limit |
| --- | --- |
| File upload size | 1 GB |
| File download size | 1 GB |

### Clipboard Data

| Feature | Limit |
| --- | --- |
| Copying and pasting text | No limit |
| Copying and pasting images | 3 GB |

### Isolation Sessions

| Feature | Limit |
| --- | --- |
| Debug mode timeout period | 10 minutes |
| Session idle time | 10 minutes |
| Persistent session data | 100 MB |
| Web page text searches | 1,017 entries |
| Mobile isolation user experience operating systems | Android v. 13 and after iOS v. 17 and after |

### VLAN

The following table shows the ranges and limitations for VLAN:

| Feature | Limit |
| --- | --- |
| Network name | 255 characters |
| Subnet range | LAN: /8 to /29; WAN: /8 to /31 |
| IP/Subnet validation range | Ranges must fall within valid CIDR blocks |
| VLAN tags | 1 to 4,094 |

### Performance Parameters

The following table shows the ranges and limitations for Zero Trust Branch connection rates and sessions:

| Feature | Limit |
| --- | --- |
| Connection rate | ZT400: 3,250 connections per second; ZT600: 3,250 connections per second; ZT800: 6,500 connections per second; ZT8010: 12K connections per second |
| Concurrent sessions | ZT400: 500K sessions; ZT600: 500K sessions; ZT800: 1M sessions; ZT8010: 2M sessions |
| Maximum flow logs that can be exported | 10K records |
| Log retention | 7 days |

### Segmentation Policies

The following table shows the ranges and limitations for segmentation policies:

| Feature | Limit |
| --- | --- |
| Policy name | 255 characters |
| Policy description | 2,000 characters |

### Forwarding Policies

The following table shows the ranges and limitations for forwarding policies (policy-based routing):

| Feature | Minimum Limit | Maximum Limit |
| --- | --- | --- |
| Policy name | 2 characters | 32 characters |
| Internet & SaaS gateways | 1 unique gateway per policy | 2 unique gateways per policy |
| WAN failover score threshold | 3 | 30 |

### DNS Policies

The following table shows the ranges and limitations for DNS policies:

| Feature | Maximum Limit |
| --- | --- |
| Policy name | 255 characters |
| Policy description | 2,000 characters |

### App Connectors

The following table shows the ranges and limitations for App Connectors:

| Feature | Limit |
| --- | --- |
| App Connector name | 63 characters |

### DHCP Configuration

| Feature | Limit |
| --- | --- |
| Maximum DHCP servers or relay IP addresses per site | 7 IP addresses |
| DHCP profile name | 3 characters |

### Sites & Gateways

The following table shows the ranges and limitations for sites and gateways:

| Feature | Limit |
| --- | --- |
| Site name | 255 characters |
| Gateway name | 255 characters |
| Maximum isolation gateways per cluster | 2 |

### Static Routes

The following table shows the ranges and limitations for static routes:

| Feature | Limit |
| --- | --- |
| Static route name | 255 characters |
| Administrative Distance (AD) metrics | 255 |

### OSPF Routing

The following table shows the ranges and limitations for Open Shortest Path First (OSPF) routing protocol:

| Feature | Minimum Limit | Maximum Limit |
| --- | --- | --- |
| Area ID | 1 | 4,294,967,295 |
| Cost | 1 | 65,535 |
| Dead interval | 1 | 65,535 |
| Hello interval | 1 | 65,535 |
| Authentication MD5 key length | 1 | 16 characters |
| Authentication plaintext key length | 1 | 8 characters |

### BGP Routing

The following table shows the ranges and limitations for border gateway protocol (BGP) routing:

| Feature | Minimum Limit | Maximum Limit |
| --- | --- | --- |
| Graceful restart time | 1 | 3,600 seconds |
| BGP password length | - | 80 characters |
| Local AS | - | 32 bits (232) |
| Remote AS | - | 32 bits (232) |

### GRE

The following table shows the ranges and limitations for Generic Routing Encapsulation (GRE):

| Feature | Limit |
| --- | --- |
| Local underlay IP address | Must be a valid IP address |
| Remote underlay IP address | Must be a valid IP address |
| Local GRE IP address | Must be a valid IP address |
| GRE IP address subnet mask | 0 to 32 (via min_subnet to max_subnet) |

### Settings

The following table shows the ranges and limitations for DHCP option and broadcast relay:

| Feature | Minimum Limit | Maximum Limit |
| --- | --- | --- |
| DHCP option address time | 300 seconds | 4,294,967,295 seconds |
| Broadcast relay port number | 1 | 65,535 (valid TCP/UDP port range) |

### Resources

The following table shows the ranges and limitations for objects:

| Feature | Limit/Range |
| --- | --- |
| Object group name | 255 characters |
| Time schedule dates | End date must be after the start date |
| Domains/FQDN | Valid FQDN or wildcard |
| Internet & SaaS gateway IP address or prefix | Valid IP address or CIDR format |
| DNS servers | Valid IP address |
| Object group type | Configurable as: Device, network, LAN zone, WAN zone, L4 port, MAC, applications, time schedule, domains, DNS servers, Internet & SaaS gateway, app segments, SaaS applications |

### Networking, Subnets, and Interfaces

The following table shows the ranges and limitations for networking, subnets, and interfaces:

| Feature | Minimum Limit | Maximum Limit |
| --- | --- | --- |
| Interface maximum transmission unit (MTU) size | 576 | 9,000 bytes |
| VLAN tag range | 1 | 4,094 |
| SNMPv3 authentication or private (Priv) password | 8 characters | 64 characters |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/resetting-login-credentials-or-mfa","lastmod":"2026-02-12T15:49Z","nid":"1507596"} -->
## Resetting the Login Credentials or MFA

- Source: https://help.zscaler.com/unified/resetting-login-credentials-or-mfa
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Resetting the Login Credentials or MFA
- Last modified: 2026-02-12T15:49Z
- Summary: How to reset the password, MFA, security key, or biometric authentication in the Admin Portal.

You can reset the password or multi-factor authentication (MFA) when you've forgotten the credentials or when required.

- Resetting the Password
- Resetting the Second-Factor Authentication

Resetting the Password by Using the Self Recovery Option

Resetting the Password by Requesting an Administrator

1. On the login page, enter your **Login ID**, then click **Next**. See image.
2. On the next page, click **Having trouble logging in?** See image.
3. Click **Reset Password**. See image. The link to reset the password is sent to your email address. This link remains valid for 15 minutes. After 15 minutes, you need to resend the link again.
4. Open the email and click the link. You must verify with the registered MFA option for resetting a password.
5. On the second-factor authentication page, enter details for authentication type and click **Verify**. You can reset the password after successful verification. See image.
6. On the **Reset Password**page: See image.
  1. **New Password**: Enter a password.
  2. **Confirm New Password**: Re-enter the password to confirm.
7. Click **Next**. Your credentials are reset and the **Success**page appears.
8. Click **Re-Login** to log in to Admin Portal. See image.

To reset the password by requesting another administrator, see [Configuring Security Settings for Users](https://help.zscaler.com/zidentity/configuring-security-settings-users).

To reset the MFA option, contact your Authentication Service administrator. An administrator might delete the configured MFA option from the **Security Settings** tab for users. The user is then prompted to reconfigure MFA the next time they log in to the Admin Portal.

Administrators can also delete the Email OTP option for users who have opted it as their MFA option.

See image.

[Image: ZIdentity Admin Portal Welcome page with blurred Login ID.]

[Image: ZIdentity Admin Portal Welcome page with blurred login ID and highlighted "Having Trouble Logging In" option.]

[Image: Having Trouble Logging In page with blurred Login ID.]

[Image: Second Factor Authentication page displaying TOTP Authenticator for verification.]

[Image: Reset Password page with blurred Login ID.]

[Image: Account Creation Success page with blurred login ID and option to Re-Login.]

[Image: Security Settings tab displaying configured MFA Authenticators for users.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/reviewing-activating-policies","lastmod":"2026-02-12T09:00Z","nid":"1490911"} -->
## Reviewing and Activating Policies

- Source: https://help.zscaler.com/unified/reviewing-activating-policies
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Setting Up Policies > Reviewing and Activating Policies
- Last modified: 2026-02-12T09:00Z
- Summary: How to review and activate the security policies to complete your Zscaler installation.

The final onboarding step displays all the policy configurations you have selected. To make changes to any set of policies, click the **Edit**icon to return to that section. You can also make changes later in the Zscaler Admin Console.

Click **Activate and Launch**to activate Zscaler with the policies you have selected and launch the [Networking dashboard](https://help.zscaler.com/unified/viewing-networking-dashboard) in the Zscaler Admin Console. From there, you have access to all Zscaler capabilities.

To learn more, see [Signing in to the Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/reviewing-cyber-threat-protection-policies","lastmod":"2026-02-12T08:57Z","nid":"1488026"} -->
## Reviewing Cyber Threat Protection Policies

- Source: https://help.zscaler.com/unified/reviewing-cyber-threat-protection-policies
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Setting Up Policies > Reviewing Cyber Threat Protection Policies
- Last modified: 2026-02-12T08:57Z
- Summary: Review the policies that protect your users from cyber threats such as botnets, phishing, fraud, and malware.

Cyber threat protection policies protect your users from a variety of security threats. Zscaler offers the following types of cyberthreat protection:

- **Isolation**: uses browser isolation to open suspicious URLs which have not yet been categorized in a remote browser in a Zscaler data center. These include newly registered URLs, dormant URLs, as well as URLs which have not yet been observed and categorized by Zscaler.
- **Botnet Protection**: protects your users from botnets with a series of methods including blocking known command and control (C2) servers, blocking domain generation algorithms, and examining potential botnet traffic.
- **Phishing & Fraud Protection**: blocks known and suspected phishing sites, and blocks adware, spyware, web spam, and cryptomining to protect your users from fraud.
- **Malicious Active Content Prevention**: blocks a number of vulnerabilities that may cause users to download malware, including websites that attempt to load dangerous content, exploitable file formats, and web browser vulnerabilities.
- **P2P Protection**: blocks the use of peer-to-peer filing sharing applications, many of which encrypt content so they cannot be inspected for data leakage.
- **Malware Protection**: blocks malware by inspecting inbound and outbound traffic, HTTP, FTP, and FTP over HTTP using signature-based detection and protection using malware feeds from trusted industry partners.
- **Zero-Day Attack Protection**: isolates and analyzes zero-day threats in a sandbox environment.

During onboarding, Zscaler sets up its default cyber threat protection policies. You can fine-tune these policies later in the Admin Portal. To learn more, see [Configuring the Advanced Threat Protection Policy](https://help.zscaler.com/zia/configuring-advanced-threat-protection-policy).

When you are done reviewing the cyber threat protection policies, click **Next** to move on to review the [data protection policies](https://help.zscaler.com/unified/configuring-data-protection).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/reviewing-data-protection-policies","lastmod":"2026-02-12T08:58Z","nid":"1488031"} -->
## Reviewing Data Protection Policies

- Source: https://help.zscaler.com/unified/reviewing-data-protection-policies
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Setting Up Policies > Reviewing Data Protection Policies
- Last modified: 2026-02-12T08:58Z
- Summary: Review data protection policies that ensure that users do not leak corporate or sensitive data outside your organization.

Data protection policies protect your organization from users intentionally or unintentionally leaking data via attachments or social media.

Zscaler provides the following types of data protection:

- **Visibility & Classification**: Data Loss Prevention (DLP) dictionaries contain algorithms to inspect user traffic for potential data leakage, such as bank information, PII, sensitive file formats and more. To learn more, see [About DLP Dictionaries](https://help.zscaler.com/zia/about-dlp-dictionaries).
- **Data Classification Dashboard**: This dashboard gives you high-level visibility and insight into your organization's Data Loss Prevention (DLP) content. To learn more, see [About the Data Discovery Report](https://help.zscaler.com/zia/about-data-discovery-report).

During onboarding, Zscaler sets up its default set of data protection policies. You can fine-tune these policies later in the Admin Portal. To learn more about Zscaler's data protection, see [About Data Loss Prevention](https://help.zscaler.com/zia/about-data-loss-prevention).

Click **Next** when you are done reviewing the policies to review the [user privacy settings](https://help.zscaler.com/unified/configuring-user-privacy).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/reviewing-ssl-inspection","lastmod":"2026-02-12T08:56Z","nid":"1488021"} -->
## Reviewing SSL Inspection Policies

- Source: https://help.zscaler.com/unified/reviewing-ssl-inspection
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Setting Up Policies > Reviewing SSL Inspection Policies
- Last modified: 2026-02-12T08:56Z
- Summary: How to configure the SSL inspection policies that protect traffic sent via HTTPS from being exploited to deliver security threats.

SSL inspection policies allow you to inspect traffic sent using the secure HTTPS protocol to ensure that it is, in fact, encrypted and fully secure and is not exploited to deliver security threats. Zscaler inspects SSL traffic by establishing a separate SSL tunnel between the user's browser and the destination server.

To review SSL inspection policies:

1. Under**Exempted Categories**, review the categories that Zscaler exempts from SSL inspection. These categories (Healthcare and Finance) are not inspected because they may contain a user's personally identifiable information (PII).
2. By default, Zscaler inspects SSL traffic from Microsoft's web-based applications such as OneDrive. If you want to use Microsoft's recommended setting, which exempts that traffic from SSL inspection, click **Advanced SSL Settings**, select **M365 Exempted**, and click **Save**.
3. Under **All Users, All Other Destinations**, note that categories that are not exempted above will have their SSL traffic inspected.
4. Under **SSL Root Certificate**, the Zscaler's intermediate root certificate is installed. You can select your own certificate later in the Admin Portal.
5. When you are done reviewing the SSL inspection policies, click **Next**to move on to review the [cyber threat protection policies](https://help.zscaler.com/unified/configuring-cyber-threat-protection).

Zscaler installs its recommended SSL inspection policies by default. You can fine-tune these policies later in the Admin Portal. To learn more, see [Configuring SSL Inspection Policy](https://help.zscaler.com/zia/configuring-ssl-inspection-policy).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/saving-and-activating-changes-admin-console","lastmod":"2026-06-08T17:35Z","nid":"1498291"} -->
## Saving and Activating Changes in the Zscaler Admin Console

- Source: https://help.zscaler.com/unified/saving-and-activating-changes-admin-console
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Saving and Activating Changes in the Zscaler Admin Console
- Last modified: 2026-06-08T17:35Z
- Summary: How to save and activate changes in the Zscaler Admin Console.

When making configuration changes that must be propagated across the Zscaler cloud (e.g., adding, deleting, or editing policy rules), you must activate the changes before they can take effect.

Propagating configuration changes is a two-step process:

1. Make your configuration changes (e.g., adding, editing, or deleting policy rules) and then click **Save**to store the changes in the local database.

Saving your changes is important in the event that multiple users are editing a configuration setting concurrently, because only the last saved configuration persists. For example, assume User 1 adds Facebook to the list of cloud applications blocked by a social networking policy. Another user, User 2, starts editing the same networking policy simultaneously before User 1 has saved their changes and adds Twitter to the cloud applications list. Now, if User 1 saves their changes followed by User 2, the changes made by User 1 are overwritten with changes made by User 2, adding only Twitter to the cloud applications list.

1. Queue the changes for activation.

Activating the changes pushes the configuration changes to the Central Authority (CA), which among other things, serves as the central repository for policies and configuration settings.

## Activating Changes

Ensure you're making changes to the Zscaler Admin Console in a single browser session. If you make changes across multiple browser sessions using the same admin account, activation errors might occur.

To activate queued configuration changes in the Zscaler Admin Console:

1. On the top navigation menu, click the **Activation** icon on the right. See image.
2. Click **Activate**. See image.

After you activate your configuration updates, you cannot undo the Activate action. However, you can delete or modify the undesired configuration and activate it again.

If there are no other admins editing, the service sends the updates to the CA immediately. If there are multiple admins editing, the service pushes the changes to the CA after all admins activate their changes.

## About the Activation Menu

The **Activation**menu displays your activation status. It also displays the number and status of other admins who are currently editing.
See image.

- **My Activation Status**: Displays your activation status.
  - **No pending changes to activate**: You haven't saved or activated your changes.
  - **Editing**: You saved your changes but haven't activated them.
  - **Activation queued**: Your activated changes are pending and haven't been pushed to the CA. This status displays when you activate your changes, and there are other admins still editing. The service pushes the changes to the CA after all admins activate their changes.
- **Currently Editing**: Lists the usernames of admins who saved their changes but haven't activated them.
- **Queued Activations**: Lists the usernames of admins with pending activations. When an admin activates their changes and there are other admins still editing, the changes are queued for activation, and the admin's username is moved to this list. A queued activation cannot be canceled.
- **Force Activate**: Immediately pushes all saved changes to the CA. Only [super admins](https://help.zscaler.com/zia/adding-zia-super-admins) and full admins can **Force Activate** changes.

The service automatically activates an admin's saved changes if the admin:

- is inactive for 30 minutes (duration can be configured in the [Advanced Settings](https://help.zscaler.com/zia/configuring-advanced-settings))
- logs out
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/searching-zscaler-admin-console","lastmod":"2026-06-08T17:22Z","nid":"1503801"} -->
## Searching in the Zscaler Admin Console

- Source: https://help.zscaler.com/unified/searching-zscaler-admin-console
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Searching in the Zscaler Admin Console
- Last modified: 2026-06-08T17:22Z
- Summary: How to search in the Zscaler Admin Console.

To help you navigate faster, you can search for pages in the Zscaler Admin Console. Search results include Zscaler Admin Console pages (except for items in the Help menu) and do not include:

- Tabs
- Field names
- Toggle names
- Tooltips

To search the Zscaler Admin Console:

1. Click the **Search**icon in the navigation menu to display the search box, or press `Ctrl`+`K`. See image.
2. Enter your search text in the search box to display a list of menus that match your search term. See image.
3. Click any search result to navigate to that page.

The search provides auto-complete suggestions as you type, and keeps a history of your recent searches.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/security-operations-platform-configurations-data-source","lastmod":"2026-08-02T00:19Z","nid":"1541925"} -->
## Security Operations Platform Configurations by Data Source

- Source: https://help.zscaler.com/unified/security-operations-platform-configurations-data-source
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Security Operations Platform Configurations by Data Source
- Last modified: 2026-08-02T00:19Z
- Summary: The current Security Operations Platform data sources by vendor.

The Security Operations Platform (SecOps Platform) supports a wide range of third-party data sources that ingest security, asset, identity, cloud, and operational data into the platform. Use this article to identify the available data sources, the streams each one supports, and the types of data each stream retrieves. For deployment prerequisites and setup steps, see [Deploying Data Source Configurations](https://help.zscaler.com/unified/deploying-data-source-configurations). To configure a specific source, see [Source Configuration Guides](https://help.zscaler.com/uvm/administration/connectors/sources/source-configuration-guides).

The following table lists the supported SecOps Platform data sources by vendor, listing the streams it supports and the types of data each stream retrieves.

| Vendor | Data Source | Streams and Data Retrieved |
| --- | --- | --- |
| Apiiro | [Apiiro](https://help.zscaler.com/uvm/configuring-apiiro-connector) | **Apiiro SCA:** Retrieves vulnerabilities for open-source and third-party components in application code.; **Apiiro Secrets:** Retrieves vulnerabilities that expose access to sensitive application data.; **Apiiro Terraform Misconfiguration:** Retrieves vulnerabilities related to misconfigurations in Terraform IaC deployments. |
| Aqua Security | [Aqua Security](https://help.zscaler.com/uvm/configuring-aqua-security-connector) | **Aqua Security - CWPP Functions Vulnerabilities:** Retrieves vulnerability data for functions.; **Aqua Security - CWPP Hosts Vulnerabilities:** Retrieves vulnerability data for hosts.; **Aqua Security - CWPP Images Vulnerabilities:** Retrieves vulnerability data for container images.; **Aqua Security Containers:** Retrieves details about running containers.; **Aqua Security CSPM:** Retrieves cloud misconfigurations, compliance violations, and security alerts.; **Aqua Security Hosts:** Retrieves metadata about managed hosts.; **Aqua Security Images:** Retrieves metadata about container images. |
| Armis | [Armis](https://help.zscaler.com/uvm/configuring-armis-connector) | **Armis CVE:** Retrieves vulnerability data related to devices, including matched device details.; **Armis Devices:** Retrieves information about discovered devices, including their attributes and states.; **Armis Reports:** Retrieves data from specific reports in Armis using a report ID. |
| AttackForge | [AttackForge](https://help.zscaler.com/uvm/configuring-attackforge-connector) | **AttackForge:** Retrieves vulnerability data accessible to the user associated with the API key. |
| AWS | [AWS](https://help.zscaler.com/uvm/configuring-aws-connector) | **AWS Accounts:** Retrieves metadata about AWS accounts, including ID, ARN, email, status, joined method, and tags.; **AWS EC2:** Retrieves metadata about EC2 instances, including ID, type, public IP, security groups, and configuration.; **AWS ECR:** Retrieves metadata about Elastic Container Registries, such as registry ID, image tags, and region.; **AWS ECR Findings:** Retrieves metadata about security vulnerabilities and findings related to container images in ECR.; **AWS EKS Clusters API:** Retrieves metadata about EKS clusters, including name, ARN, endpoint, and tags.; **AWS Inspector Findings:** Retrieves security vulnerability metadata.; **AWS Network:** Retrieves metadata about AWS networking components, such as VPCs, subnets, and security configurations.; **AWS RDS:** Retrieves metadata about RDS database clusters, including ID, engine, status, endpoint, and security groups.; **AWS S3 Buckets:** Retrieves metadata about S3 buckets.; **AWS Security Hub API:** Not explicitly listed. |
| Axonius | [Axonius](https://help.zscaler.com/uvm/configuring-axonius-connector) | **Axonius:** Retrieves asset inventory data, including device details, custom fields, and associated metadata. |
| Azure | [Azure Blob](https://help.zscaler.com/uvm/configuring-azure-blob-connector) | **Azure Blob:** Retrieves data from Azure Blob Storage. |
| Azure | [Azure Cloud Assets](https://help.zscaler.com/uvm/configuring-azure-cloud-assets-connector) | **Azure Cloud - Assets:** Retrieves comprehensive cloud asset data. |
| Azure | [Microsoft Defender for Cloud Findings](https://help.zscaler.com/uvm/configuring-microsoft-defender-cloud-findings-connector) | **Microsoft Defender for Cloud - Findings:** Retrieves security findings for cloud assets, including vulnerability and compliance issue details. |
| Bitsight | [Bitsight](https://help.zscaler.com/uvm/configuring-bitsight-connector) | **Bitsight Findings:** Retrieves information about findings, which are security-related events or configurations on an asset.; **Bitsight Observations:** Retrieves detailed observation data about risk categories for companies in your portfolio. |
| Black Duck | [Black Duck SCA](https://help.zscaler.com/uvm/configuring-black-duck-sca-connector) | **Black Duck SCA:** Retrieves discovered open-source component details. |
| Burp Suite | [Burp Suite Issues](https://help.zscaler.com/uvm/configuring-burp-suite-issues-connector) | **Burp Suite Issues:** Retrieves Burp Suite sites, scans, and issues data. |
| Claroty xDome for Healthcare | [Claroty xDome for Healthcare](https://help.zscaler.com/uvm/configuring-claroty-xdome-healthcare-connector) | **Claroty xDome Healthcare - Assets:** Retrieves device information, network details, vulnerabilities, and risk scores.; **Claroty xDome Healthcare - Vulnerabilities:** Retrieves vulnerability data, including severity scores, exploitability, and remediation recommendations. |
| Code42 | [Code42](https://help.zscaler.com/uvm/configuring-code42-connector) | **Code42:** Retrieves alerts from configured alert rules, including details on alert destinations and alert actors. |
| CrowdStrike | [CrowdStrike](https://help.zscaler.com/uvm/configuring-crowdstrike-connector) | **CrowdStrike Vulnerabilities:** Retrieves vulnerabilities, including IDs, severity levels, affected systems, and descriptions.; **CrowdStrike Managed Hosts:** Retrieves devices managed by CrowdStrike, such as endpoints running the Falcon sensor.; **CrowdStrike Environmental Assets:** Retrieves assets in your environment, including unsupported and unmanaged assets not directly managed by CrowdStrike.; **CrowdStrike Incidents:** Retrieves security incidents, including status, severity, descriptions, and affected resources.; **CrowdStrike Alerts:** Retrieves security alerts, including activity IDs, timestamps, descriptions, affected resources, and severity. |
| CrowdStrike | [CrowdStrike CSPM](https://help.zscaler.com/uvm/configuring-crowdstrike-cspm-connector) | **CrowdStrike CSPM Indicators of Misconfiguration (IOM):** Flags suspicious or unauthorized usage patterns within monitored systems or networks.; **CrowdStrike CSPM Indicators of Attack (IOA):** Flags potential signs of active threats or malicious activities detected by CrowdStrike. |
| CrowdStrike | [CrowdStrike Identity Protection](https://help.zscaler.com/uvm/configuring-crowdstrike-identity-protection-connector) | **CrowdStrike Identity Protection Security Assessment:** Retrieves security risk details, including factors, likelihood, severity, recommendations, and assessment scores.; **CrowdStrike Identity Protection Domain Users:** Retrieves user data, including display names, risk scores, admin status, and archival status. |
| CyCognito | [CyCognito](https://help.zscaler.com/uvm/configuring-cycognito-connector) | **CyCognito Issues:** Retrieves a detailed, actionable view of security issues.; **CyCognito Assets:** Retrieves a comprehensive view of enterprise digital assets. |
| Cyera | [Cyera](https://help.zscaler.com/uvm/configuring-cyera-connector) | **Cyera Assets:** Retrieves metadata and security details for assets and data stores.; **Cyera Issues:** Retrieves security and compliance issues related to data misconfigurations and vulnerabilities. |
| Datto | [Datto](https://help.zscaler.com/uvm/configuring-datto-connector) | **Datto Alerts:** Retrieves open alerts in your Datto account.; **Datto Devices:** Retrieves an inventory of devices, including details such as hostname, site, IP address, and operating system. |
| Digital Shadows | [Digital Shadows](https://help.zscaler.com/uvm/configuring-digital-shadows-connector) | **Digital Shadows:** Retrieves triage alerts and incidents from your Digital Shadows account.; **Digital Shadows Assets:** Retrieves asset events and detailed asset records, including domains and IP addresses, with asset type, value, status, and timestamps. |
| Dragos | [Dragos](https://help.zscaler.com/uvm/configuring-dragos-connector) | **Dragos Assets:** Retrieves asset information from your Dragos account.; **Dragos Vulnerabilities:** Retrieves vulnerability data from your Dragos account. |
| Endor Labs | [Endor Labs](https://help.zscaler.com/uvm/configuring-endor-labs-connector) | **Endor Labs - Containers:** Retrieves container-related findings.; **Endor Labs - SAST:** Retrieves SAST findings.; **Endor Labs - SCA Findings:** Retrieves SCA findings.; **Endor Labs - Secrets:** Retrieves exposed secret findings.; **Endor Labs - Vulnerabilities:** Retrieves vulnerability types with associated metadata. |
| Flexera | [Flexera Assets](https://help.zscaler.com/uvm/configuring-flexera-assets-connector) | **Flexera Assets:** Retrieves detailed hardware, lifecycle, and ownership information for each asset in your organization. |
| Freshservice | [Freshservice](https://help.zscaler.com/uvm/configuring-freshservice-connector) | **Freshservice:** Retrieves ticket ID, requester details, ticket status, priority, assignee, and related metadata. |
| GitHub | [GitHub Advanced Security](https://help.zscaler.com/uvm/configuring-github-advanced-security-connector) | **GitHub Advanced Security - Code Scanning:** Retrieves code scanning data from GitHub repositories, including security vulnerabilities and misconfigurations.; **GitHub Advanced Security - Secret Scanning:** Retrieves secret scanning data, including exposed sensitive information such as API keys, credentials, and tokens. |
| GitHub | [GitHub Repositories](https://help.zscaler.com/uvm/configuring-github-repositories-connector) | **GitHub Repositories:** Retrieves repository metadata, including name, owner, organization, visibility, URL, and timestamps. |
| Google Cloud Platform | [Google Cloud Platform](https://help.zscaler.com/uvm/configuring-google-cloud-platform-connector) | **Google Cloud Platform Assets:** Retrieves inventory and metadata for Google Cloud assets.; **Google Cloud Platform Misconfigurations:** Retrieves misconfigurations and compliance risks.; **Google Cloud Platform Vulnerabilities:** Retrieves vulnerability occurrences for resources and container images. |
| Google Sheets | [Google Sheets](https://help.zscaler.com/uvm/configuring-google-sheets-connector) | **Google Sheets:** Retrieves data from a specified Google Sheets spreadsheet and can optionally filter by specific sheets. |
| Halo | [Halo Assets](https://help.zscaler.com/uvm/configuring-halo-assets-connector) | **Halo Assets:** Retrieves asset data, including detailed asset records, optional site and asset group information, and CAB owner names. |
| HCL BigFix | [HCL BigFix Computers](https://help.zscaler.com/uvm/configuring-hcl-bigfix-computers-connector) | **HCL BigFix - Computers:** Retrieves lists of computers and core properties such as IDs, names, operating systems, system statuses, and IP addresses. |
| Horizon3.ai | [Horizon3.ai](https://help.zscaler.com/uvm/configuring-horizon3-ai-connector) | **Horizon3 Assets:** Retrieves discovered asset details from penetration tests.; **Horizon3 Flat Impacts:** Retrieves flat impact results identified in tests.; **Horizon3 Weaknesses Evidence:** Retrieves weaknesses and supporting evidence. |
| Invicti | [Invicti](https://help.zscaler.com/uvm/configuring-invicti-connector) | **Invicti:** Retrieves vulnerability data, including severity levels, CVSS scores, remediation guidance, and website group information. |
| Ionix | [Ionix](https://help.zscaler.com/uvm/configuring-ionix-connector) | **Ionix Assets:** Retrieves organizational assets being monitored, including metadata such as asset ID, asset type, and asset status.; **Ionix Findings:** Retrieves remediation action items, including vulnerabilities, assigned assets, and CVE-level details. |
| JFrog | [JFrog XRay](https://help.zscaler.com/uvm/configuring-jfrog-xray-connector) | **JFrog XRay:** Retrieves vulnerability data from Artifactory and Xray, including vulnerability details, affected components, and associated security metadata. |
| Lacework | [Lacework](https://help.zscaler.com/uvm/configuring-lacework-connector) | **Lacework:** Retrieves vulnerability findings, including CVE details, affected assets, risk scores, and fix information. |
| Legit Security | [Legit Security](https://help.zscaler.com/uvm/configuring-legit-security-connector) | **Legit:** Retrieves all issue types except incidents, including issue source, integration, metadata, issue type, and full payload details.; **Legit Incidents:** Retrieves security incidents, including source, integration, metadata, and full payload details. |
| Mandiant ASM | [Mandiant ASM](https://help.zscaler.com/uvm/configuring-mandiant-asm-connector) | **Mandiant ASM Entities:** Retrieves exposed assets within the external attack surface, including domains, subdomains, IP addresses, hostnames, URLs, and SSL/TLS certificates.; **Mandiant ASM Issues:** Retrieves detailed issues such as misconfigurations, open ports, vulnerabilities, SSL/TLS weaknesses, statuses, and remediation recommendations. |
| Microsoft Defender for Endpoint | [Microsoft Defender for Endpoint](https://help.zscaler.com/uvm/configuring-microsoft-defender-endpoint-connector) | **Microsoft Defender for Endpoint Assets:** Retrieves device information, including ID, owner, operating system, and network information.; **Microsoft Defender for Endpoint Vulnerabilities:** Retrieves vulnerabilities affecting the organization by machine and software.; **Microsoft Defender for Endpoint Software Vulnerabilities by Machine:** Retrieves detailed software vulnerabilities for individual machines.; **Microsoft Defender for Endpoint Alerts:** Retrieves security alerts, including impacted resources, severity, detection source, recommendations, and timestamps.; **Microsoft Defender for Endpoint Incidents:** Retrieves incidents, including affected resources, severity, status, related alerts, and timelines. |
| Microsoft Entra | [Entra ID](https://help.zscaler.com/uvm/configuring-microsoft-entra-id-connector) | **Entra ID - Administrative Units:** Retrieves administrative units and the members assigned to each unit.; **Entra ID - Applications:** Retrieves applications registered in the tenant and the owners for each application.; **Entra ID - Devices:** Retrieves registered devices and the owners for each device.; **Entra ID - Users & Groups:** Retrieves users and the groups each user belongs to. |
| Microsoft Intune | [Microsoft Intune Assets](https://help.zscaler.com/uvm/configuring-microsoft-intune-assets-connector) | **Microsoft Intune Assets:** Retrieves Microsoft Intune devices that are mapped to assets in the system. |
| Microsoft Intune | [Microsoft Intune Audit Events](https://help.zscaler.com/uvm/configuring-microsoft-intune-audit-events-connector) | **Microsoft Intune Audit Events:** Retrieves Microsoft Intune audit events and logs using the Microsoft Graph API. |
| NetBox | [NetBox](https://help.zscaler.com/uvm/configuring-netbox-connector) | **NetBox Devices:** Retrieves inventory of physical and virtual network devices, including hardware types, roles, sites, and operational status.; **NetBox IP Addresses:** Retrieves IPAM data, including networks, VRFs, VLANs, assignment status, and device associations.; **NetBox Virtual Machines:** Retrieves virtualized infrastructure data, including VM configurations, cluster associations, CPU, and memory allocations. |
| Prisma | [Prisma Cloud](https://help.zscaler.com/uvm/configuring-prisma-cloud-connector) | **Prisma Cloud - Container Issues:** Retrieves container scan compliance reports.; **Prisma Cloud - All Findings:** Retrieves deployed scan vulnerability reports. |
| Prisma | [Prisma Cloud CSPM](https://help.zscaler.com/uvm/configuring-prisma-cloud-cspm-connector) | **Prisma Cloud CSPM:** Retrieves vulnerabilities and asset data on cloud resources. |
| Qualys VMDR & PC | [Qualys VMDR & PC](https://help.zscaler.com/uvm/configuring-qualys-vmdr-pc-connector) | **Qualys Assets:** Retrieves host data, including IP addresses, DNS names, serial numbers, operating systems, and tags.; **Qualys Vulnerabilities:** Retrieves vulnerabilities from the vulnerabilities and potential vulnerabilities categories.; **Qualys Policy Compliance:** Retrieves compliance posture info records, including the compliance posture ID. |
| Qualys WAS | [Qualys WAS](https://help.zscaler.com/uvm/configuring-qualys-was-connector) | **Qualys Assets:** Retrieves the list of web applications within the user's scope.; **Qualys Vulnerabilities:** Retrieves findings, including vulnerabilities, sensitive content, and gathered information associated with web applications within the user's scope. |
| Quokka | [Quokka](https://help.zscaler.com/uvm/configuring-quokka-connector) | **Quokka:** Retrieves findings data. |
| Rapid7 InsightVM | [Rapid7 InsightVM](https://help.zscaler.com/uvm/configuring-rapid7-insightvm-connector) | **Rapid7 InsightVM Vulnerabilities:** Retrieves all vulnerabilities found on the assets to which you have access.; **Rapid7 InsightVM Assets:** Retrieves all assets to which you have access.; **Rapid7 InsightVM Policy Compliance:** Retrieves policy compliance data. |
| runZero | [runZero Assets](https://help.zscaler.com/uvm/configuring-runzero-assets-connector) | **runZero Assets:** Retrieves asset and network device inventory data discovered by runZero. |
| runZero | [runZero Vulnerabilities](https://help.zscaler.com/uvm/configuring-runzero-vulnerabilities-connector) | **runZero Vulnerabilities:** Retrieves per-asset vulnerability findings and related metadata ID. |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/security-operations-platform-configurations-outegration","lastmod":"2026-07-29T03:29Z","nid":"1541931"} -->
## Security Operations Platform Configurations by Outegration

- Source: https://help.zscaler.com/unified/security-operations-platform-configurations-outegration
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Outegration Configuration > Security Operations Platform Configurations by Outegration
- Last modified: 2026-07-29T03:29Z
- Summary: The current Security Operations Platform outegrations by vendor.

The Security Operations Platform (SecOps Platform) supports a wide range of third-party outegrations that dispatch tickets, trigger scans, update CMDB records, and export reports to external platforms. Use this article to identify the available outegrations, the category each one belongs to, the actions each one performs, and whether it supports webhook configuration for bidirectional sync. For deployment prerequisites and setup steps, see [Deploying Outegration Configurations](https://help.zscaler.com/unified/deploying-outegration-configurations). To configure a specific outegration, see [Outegration Configuration Guides](https://help.zscaler.com/uvm/administration/connectors/outegrations/outegration-configuration-guides).

The following table lists the supported SecOps Platform outegrations in alphabetical order by vendor. If an outegration supports bidirectional sync, the corresponding webhook guide is listed.

| Vendor | Outegration | Category | Data Dispatched and Actions Triggered | Webhook Guide |
| --- | --- | --- | --- | --- |
| Apache | [Apache Kafka](https://help.zscaler.com/uvm/configuring-apache-kafka-outegration) | Alerts | Publishes entity data and ticket events to an external Kafka topic. | Not supported |
| Atlassian | [Jira (Cloud and Data Center)](https://help.zscaler.com/uvm/configuring-jira-outegration) | Work Management | Dispatches SecOps Platform tickets to a Jira project as mapped issue types, such as Task or Bug. | [Configuring the Jira Outegration Webhook](https://help.zscaler.com/uvm/configuring-jira-outegration-webhook) |
| Freshworks | [Freshservice](https://help.zscaler.com/uvm/configuring-freshservice-outegration) | Work Management | Dispatches SecOps Platform tickets to a specified Freshservice tenant for remediation tracking and management. | [Configuring the Freshservice Outegration Webhook](https://help.zscaler.com/uvm/configuring-freshservice-outegration-webhook) |
| Google | [GCP Storage](https://help.zscaler.com/uvm/configuring-google-cloud-storage-outegration) | Storage | Delivers exported SecOps Platform report files to a designated Google Cloud Storage bucket. | Not supported |
| Microsoft | [Azure DevOps](https://help.zscaler.com/uvm/configuring-azure-devops-outegration) | Work Management | Dispatches tickets and policy violation tickets to Azure DevOps as mapped work item types. | [Configuring the Azure DevOps Outegration Webhook](https://help.zscaler.com/uvm/configuring-azure-devops-outegration-webhook) |
| ServiceNow | [ServiceNow](https://help.zscaler.com/uvm/configuring-servicenow-outegration) | Work Management | Dispatches SecOps Platform tickets to a configured ServiceNow table directly from the ticket drawer. | [Configuring the ServiceNow Outegration Webhook](https://help.zscaler.com/uvm/configuring-servicenow-outegration-webhook) |
| ServiceNow | [ServiceNow CMDB](https://help.zscaler.com/uvm/configuring-servicenow-cmdb-outegration) | CMDB | Dispatches asset details from violation tickets to update specified ServiceNow CMDB records. | Not supported |
| Tenable | [Tenable Vulnerability Management / Security Center](https://help.zscaler.com/uvm/configuring-tenable-outegration) | Scanner | Triggers on-demand vulnerability scans on managed assets to validate remediation after patching. | Not supported |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/servicenow-webhook-configuration-guide-risk360","lastmod":"2026-04-08T21:28Z","nid":"1533800"} -->
## ServiceNow Webhook Configuration Guide for Risk360

- Source: https://help.zscaler.com/unified/servicenow-webhook-configuration-guide-risk360
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Alerts > ServiceNow Webhook Configuration Guide for Risk360
- Last modified: 2026-04-08T21:28Z
- Summary: How to configure webhooks using ServiceNow with a ServiceNow Developer account.

This guide provides information on configuring webhooks using ServiceNow for alerts in Risk360. This article provides a sample configuration that can be built on per user requirements. The following ServiceNow webhook sample configuration uses a ServiceNow developer configuration.

To configure webhook using ServiceNow:

1. Create a ServiceNow developer account at [https://developer.servicenow.com/](https://developer.servicenow.com/).
2. Create a developer instance. See image. This instance automatically hibernates, so you must wake it every 24 hours. Also, the instance is reclaimed if you don’t use it for 10 days.
3. In the **Filter Navigator**search window on the left sidebar, enter `rest`.
4. Under **Scripted Web Services**, click **Scripted REST APIs**.
5. Click **New** at the top of the page to create a new resource. See image.
6. Enter the API name and click **Submit**. The API appears on the page.
7. Click the API name to open it.
8. At the bottom of the page, under the **Resources**tab, select **New**. See image.
9. Based on the authentication type you want to configure the webhook, choose one of the following methods:
  - Basic Authentication
  - Token authentication
10. In the Experience Center, go to **Analytics** > **Risk360** > **Alerts** > **Webhooks** > **Add Webhooks**.
11. Complete the following fields:
  1. **Name**: Enter the name of the webhook.
  2. **Status**: Select **Enabled** or **Disabled** for the webhook.
  3. **URL**: Enter the ServiceNow developer instance hostname concatenated with the API name from the Scripted REST APIs page in the ServiceNow instance (e.g., `https://dev91028.service-now.com/api/516508/alertsincident`).
  4. **Authentication Type**: Select the authentication type for the webhook from Basic or Token.
    - Basic
    - Token
12. Click **Save**. The webhook is successfully configured. Whenever an alert is triggered, a webhook notification is sent, thereby creating an entry in the ServiceNow Incident table.

To view the webhook notification:

1. Go to the ServiceNow instance.
2. Enter `incidents` in the **Filter Navigator**search box on the left side of the page.
3. Click **Incidents**. The Incidents page appears, showing the list of incidents (alerts). Click an incident to view more details about the alert.

[Image: ServiceNow Developer Instance]

[Image: Create a new ServiceNow API]

[Image: Select to fill in the API name]

1. Enter your preferred name and select **POST** as the http method.
2. Check the authentication box.
3. Paste the following script in the Script field and click **Submit**. See image. To use basic authentication for your webhook, you must create a non-admin user on ServiceNow so that admin credentials need not be shared.
  - Script for Basic authentication
4. Enter `Users` in the **Filter Navigator** search box on the left side of the page.
5. Select **Users** under **Users and Groups**.
6. Click **New** on the top bar to open a new user page.
7. Enter the username and password for this user. See image.

1. Enter your preferred name and select **POST** as the http method.
2. Disable the authentication box.
3. Paste the script under **Token Authentication** in the Script field and click **Submit**.
  - Script for Token authentication
4. Edit the token in your script to the one used for the webhook configuration in the Experience Center. The resource is populated on the **Scripted REST Service** page.

```
(function process( /*RESTAPIRequest*/ request, /*RESTAPIResponse*/ response) {
// implement resource here
var reqData = request.body.dataString;
var jData = new global.JSON().decode(reqData);
var alertId = jData.alertId;
var ruleName = jData.ruleName;
if (ruleName.equalsIgnoreCase("test")) {
// test webhook
return;
}
var alertstatus = jData.status;
var url = '[code]<a href="' + jData.url + '" target="_blank">View alert in Risk360</a>[/code]';
var startTime = jData.startTime;
var endTime = jData.endTime;
var state;
if (alertstatus == "STARTED" || alertstatus == "ONGOING")
state = 2; // in progress
else if (alertstatus == "ENDED_ON_RULE_DEL" || alertstatus == "ENDED_ON_RULE_MODIFY")
state = 8; // cancelled
else
state = 6; // resolved
var impact = 1;
if (jData.severity == "High" || jData.severity == "Critical")
impact = 1;
else if (jData.severity == "Medium")
impact = 2;
else if (jData.severity == "Low")
impact = 3;
var gdt = new GlideDateTime();
var criteria = jData.criteriaString;
//based on type of alert cause
var orgAlertCauses = [];
var categoryAlertCauses = [];
var factorgroupAlertCauses = [];
var factorAlertCauses = [];
// Manually iterate through the alert causes and categorize them
for (var i = 0; i < jData.alert_causes.length; i++) {
var cause = jData.alert_causes[i];
if (cause.type === "Org") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
orgAlertCauses.push({
id: cause.id,
status: status
});
} else if (cause.type === "Category") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
categoryAlertCauses.push({
id: cause.id,
status: status
});
} else if (cause.type === "FactorGroup") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
factorgroupAlertCauses.push({
id: cause.id,
status: status
});
} else if (cause.type === "Factor") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
factorAlertCauses.push({
id: cause.id,
status: status
});
}
}
// Initialize a string to hold the output
var output = "";
var numbering = 1;
// Build the output string dynamically for "Org" type
if (orgAlertCauses.length > 0) {
output += numbering + ". Organization Level Change\n";
for (var j = 0; j < orgAlertCauses.length; j++) {
output += "\t. " + orgAlertCauses[j].id + " (" + orgAlertCauses[j].status + ")\n";
}
numbering++;
}
// Build the output string dynamically for "Category" type
if (categoryAlertCauses.length > 0) {
output += "\n" + numbering + ". Category Level Change\n";
for (var k = 0; k < categoryAlertCauses.length; k++) {
output += "\t. " + categoryAlertCauses[k].id + " (" + categoryAlertCauses[k].status + ")\n";
}
numbering++;
}
if (factorgroupAlertCauses.length > 0) {
output += "\n" + numbering + ". Factor Group Level Change\n";
for (var l = 0; l < factorgroupAlertCauses.length; l++) {
output += "\t. " + factorgroupAlertCauses[l].id + " (" + factorgroupAlertCauses[l].status + ")\n";
}
numbering++;
}
if (factorAlertCauses.length > 0) {
output += "\n" + numbering + ". Factor Level Change\n";
for (var m = 0; m < factorAlertCauses.length; m++) {
output += "\t. " + factorAlertCauses[m].id + " (" + factorAlertCauses[m].status + ")\n";
}
}
if (jData.custom_msg == undefined || jData.custom_msg === null) {
jData.custom_msg = "";
}
var desc = "";
desc = "Rule Name: " + ruleName + "\n\nCriteria: \n" + criteria + "\n\nAlert Cause:\n" + output + "\nCustom Message:\n" + jData.custom_msg;
var gr = new GlideRecord('incident');
gr.addQuery('short_description', 'CONTAINS', alertId);
gr.query();
if (gr.next()) {
gr.state = state;
gr.impact = impact;
if (state == 6) {
gr.close_code = "Alert resolved";
} else if (state == 8) {
gr.close_code = "Rule deleted or modified";
}
gr.close_notes = " ";
var sd = gr.short_description;
gr.short_description = "Risk360 Alert " + alertId;
gr.description = desc;
gr.work_notes = url;
gdt.setValue(startTime * 1000);
gr.work_start = gdt.getDisplayValue();
if (alertstatus == "STARTED" || alertstatus == "ONGOING") {
gr.work_end = "";
} else {
gdt.setValue(endTime * 1000);
gr.work_end = gdt.getDisplayValue();
}
//gr.caller_id = e9f176e2db54101087f7478239961941;
gr.update();
} else {
gr.newRecord();
if (state == 2) // in progress
state = 1; // new
gr.state = state;
gr.impact = impact;
if (state == 6) {
gr.close_code = "Alert resolved";
} else if (state == 8) {
gr.close_code = "Rule deleted or modified";
}
gr.close_notes = " ";
gr.short_description = "Risk360 Alert " + alertId;
gr.description = desc;
gr.work_notes = url;
gdt.setValue(startTime * 1000);
gr.work_start = gdt.getDisplayValue();
if (alertstatus == "STARTED" || alertstatus == "ONGOING") {
gr.work_end = "";
} else {
gdt.setValue(endTime * 1000);
gr.work_end = gdt.getDisplayValue();
}
//gr.caller_id = e9f176e2db54101087f7478239961941;
var result = gr.insert();
}
})(request, response);
```

```
(function process(/*RESTAPIRequest*/ request, /*RESTAPIResponse*/ response) {
// implement resource here
var headers = request.headers;
var authHeader = request.getHeader('authorization');
var token = authHeader.split(" ");
if (token[1] != gs.getProperty("risk.360.token"))
response.setError(new sn_ws_err.BadRequestError('Bad token'));
var reqData = request.body.dataString;
var jData = new global.JSON().decode(reqData);
var alertId = jData.alertId;
var ruleName = jData.ruleName;
if (ruleName.equalsIgnoreCase("test")) {
// test webhook
return;
}
var alertstatus = jData.status;
var url = '[code]<a href="' + jData.url + '" target="_blank">View alert in Risk360</a>[/code]';
var startTime = jData.startTime;
var endTime = jData.endTime;
var state;
if (alertstatus == "STARTED" || alertstatus == "ONGOING")
state = 2; // in progress
else if (alertstatus == "ENDED_ON_RULE_DEL" || alertstatus == "ENDED_ON_RULE_MODIFY")
state = 8; // cancelled
else
state = 6; // resolved
var impact = 1;
if (jData.severity == "High" || jData.severity == "Critical")
impact = 1;
else if (jData.severity == "Medium")
impact = 2;
else if (jData.severity == "Low")
impact = 3;
var gdt = new GlideDateTime();
var criteria = jData.criteriaString;
//based on type of alert cause
var orgAlertCauses = [];
var categoryAlertCauses = [];
var factorgroupAlertCauses = [];
var factorAlertCauses = [];
// Manually iterate through the alert causes and categorize them
for (var i = 0; i < jData.alert_causes.length; i++) {
var cause = jData.alert_causes[i];
if (cause.type === "Org") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
orgAlertCauses.push({
id: cause.id,
status: status
});
} else if (cause.type === "Category") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
categoryAlertCauses.push({
id: cause.id,
status: status
});
} else if (cause.type === "FactorGroup") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
factorgroupAlertCauses.push({
id: cause.id,
status: status
});
} else if (cause.type === "Factor") {
var status;
if (cause.prev < 0)
prev = 0;
if (cause.cur > cause.prev) {
status = "Increase";
} else if (cause.cur < cause.prev) {
status = "Decrease";
} else {
status = "No Change";
}
factorAlertCauses.push({
id: cause.id,
status: status
});
}
}
// Initialize a string to hold the output
var output = "";
var numbering = 1;
// Build the output string dynamically for "Org" type
if (orgAlertCauses.length > 0) {
output += numbering + ". Organization Level Change\n";
for (var j = 0; j < orgAlertCauses.length; j++) {
output += "\t. " + orgAlertCauses[j].id + " (" + orgAlertCauses[j].status + ")\n";
}
numbering++;
}
// Build the output string dynamically for "Category" type
if (categoryAlertCauses.length > 0) {
output += "\n" + numbering + ". Category Level Change\n";
for (var k = 0; k < categoryAlertCauses.length; k++) {
output += "\t. " + categoryAlertCauses[k].id + " (" + categoryAlertCauses[k].status + ")\n";
}
numbering++;
}
if (factorgroupAlertCauses.length > 0) {
output += "\n" + numbering + ". Factor Group Level Change\n";
for (var l = 0; l < factorgroupAlertCauses.length; l++) {
output += "\t. " + factorgroupAlertCauses[l].id + " (" + factorgroupAlertCauses[l].status + ")\n";
}
numbering++;
}
if (factorAlertCauses.length > 0) {
output += "\n" + numbering + ". Factor Level Change\n";
for (var m = 0; m < factorAlertCauses.length; m++) {
output += "\t. " + factorAlertCauses[m].id + " (" + factorAlertCauses[m].status + ")\n";
}
}
if (jData.custom_msg == undefined || jData.custom_msg === null) {
jData.custom_msg = "";
}
var desc = "";
desc = "Rule Name: " + ruleName + "\n\nCriteria: \n" + criteria + "\n\nAlert Cause:\n" + output + "\nCustom Message:\n" + jData.custom_msg;
var gr = new GlideRecord('incident');
gr.addQuery('short_description', 'CONTAINS', alertId);
gr.query();
if (gr.next()) {
gr.state = state;
gr.impact = impact;
if (state == 6) {
gr.close_code = "Alert resolved";
} else if (state == 8) {
gr.close_code = "Rule deleted or modified";
}
gr.close_notes = " ";
var sd = gr.short_description;
gr.short_description = "Risk360 Alert " + alertId;
gr.description = desc;
gr.work_notes = url;
gdt.setValue(startTime * 1000);
gr.work_start = gdt.getDisplayValue();
if (alertstatus == "STARTED" || alertstatus == "ONGOING") {
gr.work_end = "";
} else {
gdt.setValue(endTime * 1000);
gr.work_end = gdt.getDisplayValue();
}
//gr.caller_id = e9f176e2db54101087f7478239961941;
gr.update();
} else {
gr.newRecord();
if (state == 2) // in progress
state = 1; // new
gr.state = state;
gr.impact = impact;
if (state == 6) {
gr.close_code = "Alert resolved";
} else if (state == 8) {
gr.close_code = "Rule deleted or modified";
}
gr.close_notes = " ";
gr.short_description = "Risk360 Alert " + alertId;
gr.description = desc;
gr.work_notes = url;
gdt.setValue(startTime * 1000);
gr.work_start = gdt.getDisplayValue();
if (alertstatus == "STARTED" || alertstatus == "ONGOING") {
gr.work_end = "";
} else {
gdt.setValue(endTime * 1000);
gr.work_end = gdt.getDisplayValue();
}
//gr.caller_id = e9f176e2db54101087f7478239961941;
var result = gr.insert();
}
})(request, response);
```

[Image: Basic Authentication in ServiceNow]

[Image: Create a Non-Admin User]

Enter the **Username**and**Password** for the user in ServiceNow.

Enter the same bearer token used in the script. A bearer token is a unique alphanumeric string used for authentication.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/setting-up-secure-access","lastmod":"2026-07-27T04:47Z","nid":"1486816"} -->
## Setting Up Secure Access

- Source: https://help.zscaler.com/unified/setting-up-secure-access
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Setting Up Secure Access
- Last modified: 2026-07-27T04:47Z
- Summary: How to set up secure internet access to Experience Center for all the users in your organization.

You can get started protecting your users quickly with Zscaler. Once you're set up, you'll be able to adjust your configuration as needed in the Zscaler Admin Console.

Before you begin, you must be logged in to an existing Zscaler account. To learn more, see [Signing in to the Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console).

Click the **Test Security** button to check the status of your current security configuration to understand your current vulnerabilities using Zscaler's [Internet Threat Exposure Analysis](http://securitypreview.zscaler.com/). You can return to this test after you have completed the onboarding process to see how you are protected.

To make your setup go as smoothly as possible, review the steps before beginning and make sure you have immediate access to all the information you will need, such as login information for your IdP.

You can click **Skip**to bypass all or part of the guided onboarding setup and go directly to the [Experience Center home page](https://help.zscaler.com/unified/viewing-experience-center-home-page). You must then manually configure the skipped parts of the setup.

- Step 1: Set up users.
- Step 2: Set up traffic forwarding.
- Step 3: Set up security policies.
- Step 4: Fine-tune your security setup.

Click **Initiate Setup**to start adding users to be protected by Zscaler. You can repeat this process as many times as you need to from the Zscaler Admin Console. To learn more, see [Onboarding Users to Experience Center](https://help.zscaler.com/unified/onboarding-users-experience-center).

During onboarding, you can assign users to one of these two roles:

- **End User**: A regular user who does not require any administrator access. These users are assigned to the Authentication Service Dynamic Group Registered Domains group.
- **Full Admin**: An administrator with full access to add, edit, import, and delete users within the Zscaler Admin Console. These users are assigned to the Authentication Service Global Administrators group. This type of user is commonly called a super admin.

To learn more about Authentication Service groups, see [About User Groups](https://help.zscaler.com/authentication-service/about-user-groups). If you want to change user roles before you complete onboarding, see [Editing Newly Onboarded Users](https://help.zscaler.com/unified/editing-newly-onboarded-users).

After you've added users to your setup, you'll send those users a link to install Zscaler Client Connector, an application that will ensure that all user traffic is protected with the security settings you decide. You can change these settings later if you need to.

Users must have administrator permissions on their devices that allow them to install applications.

To set up traffic forwarding:

1. Click**Secure Setup** to specify the users you want to protect.
2. On the **Clients**page, select the checkboxes for the applicable platforms for your users. Click **Next**to continue.
3. On the **Traffic Forwarding** page, select whether your users currently connect to your organization via a VPN:
  - If you're not using a VPN, select **No**and click **Next**.
  - If you are using a VPN, select **Yes** and enter the IP addresses and hostnames for the VPN. Zscaler will ignore traffic to those addresses so that the VPN can continue to work. Click **Next**to continue.
4. On the **Select Users for Zscaler Client Connector Distribution** page, select the users who you want to receive an email containing a link to download Zscaler Client Connector. You can check the box next to the **Name**heading to select all users. Click **Next**to continue.
5. On the **Distribute Zscaler Client Connector Application** page:
  - By default, users will receive automatic software updates to Zscaler Client Connector. Disable the toggle if you do not want users to receive automatic updates.
  - Review the email that will be sent to the users you selected with links to install Zscaler Client Connector.
  - Click **Finish**to send the email with installation links to your users.

The final step in securing your internet is defining the security policies for your organization.

Click **Set Up Policies** to start configuring the following types of security policies:

- [URL filtering](https://help.zscaler.com/unified/configuring-url-filtering): block, isolate, or allow internet destinations by URL category.
- [SSL inspection](https://help.zscaler.com/unified/configuring-ssl-inspection): configure what types of secure internet traffic (SSL) is inspected for added security.
- [Cyber threat protection](https://help.zscaler.com/unified/reviewing-cyber-threat-protection-policies): review additional protection policies for cyber threats, such as botnets, phishing, and malware.
- [Data protection](https://help.zscaler.com/unified/reviewing-data-protection-policies): review data protection policies that ensure sensitive data is not inadvertently leaked via email, social media, file sharing, or other means.
- [Privacy](https://help.zscaler.com/unified/configuring-user-privacy): configure how you protect your users' personally identifiable information (PII).

You can also configure the following settings:

- [Block countries](https://help.zscaler.com/unified/configuring-blocked-countries): block internet traffic from specific countries
- [Add your company logo to banners, emails, and notifications](https://help.zscaler.com/unified/adding-your-company-logo): add your company logo to banners, emails, and user notifications sent from Zscaler

When you have finished defining your initial policy configuration, click **Activate & Launch** to enable the policies you have selected and launch the Zscaler Admin Console.

When you complete the steps above, your organization's initial security configuration and access to the internet is complete and the [Networking dashboard](https://help.zscaler.com/unified/viewing-networking-dashboard) in the Zscaler Admin Console is displayed by default.

From the Zscaler Admin Console, you have full access to all Zscaler capabilities, such as fine-tuning security policies, [viewing analytics](https://help.zscaler.com/unified/signing-admin-portal#analytics), extending security to other SaaS and private applications and more. To learn more, see [Signing into the Zscaler Admin Console](https://help.zscaler.com/unified/signing-zscaler-admin-console#navigating-admin-portal).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/sharing-custom-dashboard","lastmod":"2026-02-26T08:28Z","nid":"1535262"} -->
## Sharing a Custom Dashboard

- Source: https://help.zscaler.com/unified/sharing-custom-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Custom Dashboards > Sharing a Custom Dashboard
- Last modified: 2026-02-26T08:28Z
- Summary: How to share and collaborate on a custom dashboard in Zscaler Admin Console.

Custom dashboards let you build personalized views of your organization's security profile so you can monitor the metrics most important to you.

After you have [created a custom dashboard](https://help.zscaler.com/unified/creating-custom-dashboard), you can share it with others in your organization for viewing or collaboration.

To share a custom dashboard:

1. Go to**Analytics> Custom Dashboards**.
2. In the row for the dashboard you want to share, click the **Share**icon ([Image: Share icon]). See image.
3. In the **Share Dashboard** window:
  1. **Add People**: Enter others in your organization who can access the dashboard.
  2. **Access**: For each person, select the type of access: For either access type, users you share the dashboard with cannot delete it.
    - **Viewer**: User can view the dashboard, but cannot edit it.
    - **Editor**: User can edit the widgets and layout of the dashboard.
  3. **People with Access**: Users you have previously shared the dashboard with are listed here. You can change their access or select **Remove Access**.
4. (Optional) Click **Copy Link** to copy the direct link to this dashboard.
5. Click **Share** to save the changes and close the window. See image.

[Image: Actions drop-down menu with Delete selected.]

[Image: Share Dashboard window on the edit custom dashboard page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/signing-zscaler-admin-console","lastmod":"2026-07-01T13:41Z","nid":"1491881"} -->
## Signing in to the Zscaler Admin Console

- Source: https://help.zscaler.com/unified/signing-zscaler-admin-console
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Signing in to the Zscaler Admin Console
- Last modified: 2026-07-01T13:41Z
- Summary: How to sign in to and navigate the Zscaler Admin Console, the unified user interface for Experience Center.

The Zscaler Admin Console is the unified user interface for Experience Center (i.e., console.zscaler.com).

This article covers the following topics:

- Signing In to the Zscaler Admin Console
- Navigating Within the Zscaler Admin Console
- Maintenance and Upgrades

After your organization is provisioned, you can access the Zscaler Admin Console at [https://console.zscaler.com](https://console.zscaler.com).

When the Sign In page appears:

1. Enter your**Login ID** and select the **Remember my Login ID** checkbox if you want the service to remember your username the next time you log in.
2. Click **Next**.
3. If you have access to multiple Zscaler clouds or tenants, select the one you want to use and click **Proceed**. Your selection is saved for future logins. You can change the default cloud and tenant in[Account Settings](https://help.zscaler.com/unified/customizing-your-account-settings).
4. Based on your organization's [authentication preference](https://help.zscaler.com/zidentity/configuring-multi-factor-authentication), sign in to Authentication Service using one of the following methods: Password; Multi-Factor Authentication (MFA); Security Key or Biometric Enter your **Login ID and**select **Remember me**if you want the service to remember your login ID the next time you log in.; Click**Next**.; Enter your **Password**and click **Sign In**.If you forget your password, click **Having trouble signing in?**> **Reset Password**, and a reset email is sent to your email ID. The password reset link within the email expires after 5 minutes. To learn more, see [Resetting the Password or MFA](https://help.zscaler.com/unified/resetting-login-credentials-or-mfa). Enter your **Login ID**and select **Remember me**if you want the service to remember your login ID the next time you log in.; Click**Next**.; Enter your **Password**and click **Sign In**.; Based on your organization's MFA policy, two-factor authentication (2FA) is required. Complete your 2FA to access the Authentication Service landing page.If you forget your password or want to configure a different secondary authenticator, click **Having trouble signing in?**> **Reset Password**or**Reset Second Factor**, and a reset email is sent to your email ID. The reset link within the email expires after 5 minutes. To learn more, see [Resetting the Password or MFA](https://help.zscaler.com/unified/resetting-login-credentials-or-mfa). Enter your **Login ID**and select **Remember me**if you want the service to remember your login ID the next time you log in.; Select **Sign-in using Security Key or Biometric**.; Click**Next**.; Based on your configuration, enter your security key or complete the biometric to access the Authentication Service landing page.If you want to configure with a different security key or biometric, click **Having trouble signing in?**> **Reset Security Key or Biometric**, and a reset email is sent to your email ID. The reset link within the email expires after 5 minutes. To learn more, see [Resetting the Password or MFA](https://help.zscaler.com/unified/resetting-login-credentials-or-mfa).

To sign out, go to the **Account**menu in the upper-right corner and click **Sign Out**.

See image.

You are automatically logged out after a configurable number of inactive minutes. To learn more, see [Configuring the Authentication Session](https://help.zscaler.com/zidentity/configuring-authentication-session).

If you are an admin or user with access to Internet & SaaS features within the Zscaler Admin Console and you have 5 unsuccessful attempts to sign in within one minute, your account is locked out for 5 minutes. The failed attempts are recorded in the audit log. Audit logs are stored for up to 6 months. 

If you are an admin or user with access to Private Access application features within the Zscaler Admin Console and you have three unsuccessful attempts to sign in, your account is locked out for 30 minutes. However, another admin with the same level of access or a full admin can reset your password.

The Zscaler Admin Console has the following items in the top navigation:

- Analytics
- Administration
- Policies
- Infrastructure
- Logs
- Search
- Help
- Activation
- Account

Click the **Analytics** menu to view dashboards and reports that allow you to analyze your organization's traffic, application security, user experience, and more. By default, enhanced dashboards and reports that are unique to the Experience Center are displayed. To display classic reports for Internet & SaaS, Private Access, etc., select the **Switch to Existing Reports** toggle.

To learn more, see [Analytics](https://help.zscaler.com/unified/analytics).

Click the **Administration** menu to view and configure administration settings for various Zscaler services.

Click the **Policies**menuto view and configure policies for various Zscaler services.

Click the

**Infrastructure**

menu to configure networking settings for various Zscaler services.

Click the **Logs** menu to configure log streaming and other log-related settings.

Click the **Search** icon to search for menus in the Zscaler Admin Console. To learn more, see [Searching in the Zscaler Admin Console](https://help.zscaler.com/unified/searching-zscaler-admin-console).

See image.

Click the **Help** icon to display context-sensitive help in the **Page Help** tab. This technical documentation includes, but is not limited to, overview, configuration, and deployment information.

See image.

Click the**Tools & Resources**tab to access these additional help resources:

- **Remote Assistance**: Allows you to enable remote assistance so that Zscaler Support can securely and remotely log in to your Zscaler Admin Console. To learn more, see [Enabling Remote Assistance](https://help.zscaler.com/unified/enabling-remote-assistance).
- **Help Portal**: Opens the Zscaler Help Portal in a new tab.
- **Support Portal**: Opens the Zscaler Support Portal in a new tab.
- **Terms and Policies**: Displays links where you can access Zscaler legal notices, the privacy policy, and the end user subscription agreement.
- **Share Feedback**: Opens a page with contact information for Zscaler.

See image.

Click the **Activation** icon to activate configuration changes that impact Internet & SaaS features. To learn more, see [Saving and Activating Changes in the Zscaler Admin Console](https://help.zscaler.com/unified/saving-and-activating-changes-admin-portal).

See image.

Click the **Account**icon to switch between Zscaler clouds and tenants (if applicable), view your username and organization information, and view or change your password, display language, or time zone. To learn more, see [Customizing Your Account Settings](https://help.zscaler.com/unified/customizing-your-account-settings). If you want to sign out of your account, click **Sign Out**.

See image.

During the scheduled maintenance and regular upgrade periods, the Zscaler Admin Console is in read-only mode. You can view content and navigate through the Zscaler Admin Console to view various details.

You cannot configure or edit any parameters when the Zscaler Admin Console is in read-only mode. After the upgrade is complete, the Zscaler Admin Console automatically refreshes and enables editing.

If you need to onboard users, see [Setting up Secure Access](https://help.zscaler.com/unified/setting-up-secure-access).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/source-file-parser-types","lastmod":"2026-07-29T03:49Z","nid":"1541964"} -->
## Source File Parser Types

- Source: https://help.zscaler.com/unified/source-file-parser-types
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Source File Parser Types
- Last modified: 2026-07-29T03:49Z
- Summary: Information about file parser types used for parsing data source files when ingesting data into the Security Operations Platform.

A parser type controls how the Security Operations Platform (SecOps Platform) reads your source file and converts it into records that are available for field mapping. Selecting a parser type is most relevant when you ingest data using [AnySource](https://help.zscaler.com/uvm/connecting-anysource), but it can also be used when [mapping a system data source](https://help.zscaler.com/unified/mapping-data-sources) connector to determine how the source is parsed.

In most cases, Zscaler recommends selecting **Auto**to automatically detect the file type and parse it accordingly.

If you require more granular control over how the source file is parsed, or if the Auto parser does not correctly identify the file type, you can manually select the appropriate parser type based on your file's format.

The following parser types are available:

- JSONL
- JSON
- CSV
- Excel
- XML
- PYTHON_STREAM

No additional configuration is required.

You can enter a Root JSONPath to access specific data elements. The Root JSONPath uses JSONPath expressions. To learn more, refer to the [JSON documentation](https://www.rfc-editor.org/rfc/rfc9535#name-overview-of-jsonpath-expres).

To configure the CSV parser, you must enter the **Delimiter**(e.g., comma, period, pipe) used in the file.

You can also enter a **Quote Character**, which can be any character. The double quote (") is the standard and most commonly used in CSV files.

To configure the Excel parser, enter the **Sheet Name**from which the data should be extracted. You can enter the exact name of the sheet, or you can use a wildcard (i.e., an asterisk `*`) to dynamically select the sheet name.

Using only the wildcard extracts the first sheet from the Excel file. You can combine the wildcard with a pattern to extract sheets with a recurring name format. For example, entering `pen_test*` retrieves the first sheet that begins with `pen_test`, regardless of any characters that follow.

Select the **Use first sheet as default**checkbox to return the first sheet if the specified sheet name is not found. If this option is left unchecked and the specified sheet does not exist, an error occurs and no data is retrieved.

You can enter the Root XPath to access specific elements within the XML structure. To learn more, refer to the [W3Schools documentation](https://www.w3schools.com/xml/xpath_syntax.asp).

The PYTHON_STREAM parser allows you to configure custom data manipulations and parsing logic necessary for accurately and optimally mapping data.

A common use case is exploding a single row in the ingested data into multiple rows in the transformed data. For example, you can explode a single row that contains multiple CVEs in a column into separate rows of CVEs that can then be mapped to individual findings.

The following functions are available:

- `parse_to_json()`: The primary function where you must implement your custom parsing logic. It is mandatory to define all parsing and output logic within this function for the parser to function correctly.
- `AvalorInputStream()`: Provides access to the uploaded file via the PYTHON_STREAM parser. This function supports two key methods for reading file content:
  - `read()`: Reads the entire content of the uploaded file in a single operation and returns it as a single string.
  - `readline()`: Reads a single line from the uploaded file and returns it as a string.
- `output_writer.write(json_formatted_string)`: A method used to write rows of data into the SecOps Platform using the PYTHON_STREAM parser. Accepts a JSON-formatted string as its input.

The following example script for parsing files uses the PYTHON_STREAM parser:

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
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/supported-browsers","lastmod":"2026-06-08T16:09Z","nid":"1491891"} -->
## Supported Browsers

- Source: https://help.zscaler.com/unified/supported-browsers
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Supported Browsers
- Last modified: 2026-06-08T16:09Z
- Summary: Information on operating system and browser compatibility with the Zscaler Admin Console.

Zscaler actively tests operating system and browser compatibility with the Zscaler Admin Console.

## Windows

The Zscaler Admin Console supports the following browsers on Windows 10 and later:

- Google Chrome (two most recent versions)
- Mozilla Firefox (two most recent versions)
- Microsoft Edge (two most recent versions)

Older versions of these browsers should be compatible, but are no longer actively tested.

Other browsers should be compatible, but are not actively tested.

## macOS

The Zscaler Admin Console supports the following browsers on macOS 10 and later:

- Google Chrome (two most recent versions)
- Mozilla Firefox (two most recent versions)

Older versions of these browsers should be compatible, but are no longer actively tested.

Other browsers should be compatible, but are not actively tested.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/tracking-data-source-runs","lastmod":"2026-08-02T01:56Z","nid":"1541966"} -->
## Tracking Data Source Runs

- Source: https://help.zscaler.com/unified/tracking-data-source-runs
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Tracking Data Source Runs
- Last modified: 2026-08-02T01:56Z
- Summary: How to track data source runs and view run details to assist in troubleshooting errors.

When connecting a data source to ingest data into the Security Operations Platform (SecOps Platform), you can schedule automatic full runs and incremental runs, as well as process data manually. To monitor the source's run status, track execution, and troubleshoot errors, you can view the data source run history on the [See Runs page](https://help.zscaler.com/unified/managing-data-sources#runs).

## Viewing Source Runs

To view a data source's runs:

1. In the SecOps Platform Admin Portal, go to **Configure** > **Sources**.
2. Choose one of the following methods:
  - Hover over the source, and click the **See Runs**icon.
  - From the list of sources, select the data source and click **Runs**on the top toolbar.

The **Runs**page appears.

## Viewing Run Details

Each entry in the source's run history corresponds to a single run and includes its details.

[Image: CrowdStrike Assets Runs]

The **Runs**page includes the following information:

- **Status**: The status column displays the outcome of each run:
  - **Completed**: The run successfully completed without authentication or data ingestion issues.
  - **Canceled**: The run was intentionally stopped by the user.
  - **Failed**: The run encountered an error or issue that prevented it from completing successfully.
- **ID**: A unique identifier for the run that can be used for reference when troubleshooting run failures.
- **Category**: The run's category displays the processing type, indicating the level of processing applied to the ingested data:
  - **Map and Aggregate**: The data has undergone unification processes, resulting in a standardized and aggregated format.
  - **Map**: The data has not been unified and is presented in its original, raw format.
  - **N/A**: This value is displayed for cancelled or failed runs, where no processing has occurred.
- **Data Retrieval Type**: The method used to retrieve data:
  - **Full**: The entire dataset is retrieved from the source.
  - **Incremental**: Only new or updated data is retrieved from the source.
- **Triggered By**: Displays who initiated the run, either the system through the configured schedule or a user through a manual action such as Process Now or Rerun Last Execution.
- **Run Type**: Indicates how the run was performed. For example, Source Processed identifies a manually processed run, Scheduled identifies a run initiated by the configured schedule, and Batched identifies a run initiated as part of a platform-managed batch process.
- **File Format**: The ingested file format
- **Duration**: The total amount of time the run took from start to finish.
- **Start Time**and **End Time**: The timestamps when the run started and ended. This information can reveal potential issues with data ingestion and assist during troubleshooting.

## Validating Completed Runs

When a run is successful, the Status column displays Completed. To further validate the run, expand the row to view the run's activity details.

See image.

The expanded run includes an Activities section that shows the processing stages completed during the run, such as Data Retrieval, Mapping, and Entities. Depending on the source and processing type, additional nested activities can appear under Entities, such as Assets, Findings, Tickets, Merge, and Data Manipulation.

For each activity, the following information is displayed:

- **Name**: The name of the processing stage.
- **Status**: The result of the activity (e.g., Completed, Failed, or Partially Completed).
- **Duration**: The amount of time the activity took to complete.
- **Start Time**and End Time: The timestamp when the activity started and ended.

Use these details to confirm that all expected stages have completed successfully and to identify where processing might have slowed or stopped.

Additional details that can indicate the health of the run include the following:

- **File Size**: The size of the ingested file in megabytes.
- **Number of Files**: The number of files ingested in the run.
- **Total Number of Rows**: The total number of rows ingested.

[Image: CrowdStrike Assets Completed Run]

## Troubleshooting Failed Runs

When a run fails, the Status column displays Failed. To investigate the issue, expand the row to view the error details and activity history for the run. From the error details section, you can click the Copy icon to copy the error information for troubleshooting or to share with Zscaler Support.

See image.

The expanded run can include the following troubleshooting information:

- **Error type**: A categorized description of the failure (e.g., Vendor Credentials Issue / Authentication Failure).
- **Error description**: A summary of why the run failed.
- **Actionable Resolution Steps**: Recommended steps you can take to resolve the issue.
- **Original Error Message**: The source error returned by the external system or service.

The expanded run also includes an Activities section that shows which processing stage failed, along with its status, duration, start time, and end time. Use this information to determine whether the failure occurred during data retrieval or a later processing stage.

### Common Errors

Common errors you might encounter when troubleshooting failed runs include:

- Authentication errors: The run failed because the configured authentication credentials are invalid, missing, or expired.
- Permission errors: The run failed because the authenticated account or user does not have the required access to retrieve or process the data.
- Configuration errors: The run failed because of an invalid source, mapping, or processing configuration.
- Connectivity errors: The run failed because the platform could not connect to the external source or service.
- Vendor service errors: The run failed because the external source or service was unavailable, rate-limited, or returned an unexpected response.
- Processing errors: The run failed during a later stage, such as mapping, merge, or data manipulation.

Review the error details and resolution steps shown for the failed run to identify the cause and the recommended next steps. If the error persists, [contact Zscaler Support](https://help.zscaler.com/uvm/accessing-and-navigating-security-operations-secops-platform) for assistance.

[Image: CrowdStrike Assets failed run]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-adaptive-access-engine","lastmod":"2026-06-24T21:06Z","nid":"1508246"} -->
## Understanding Adaptive Access Engine

- Source: https://help.zscaler.com/unified/understanding-adaptive-access-engine
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Policies > Adaptive Access Engine > Understanding Adaptive Access Engine
- Last modified: 2026-06-24T21:06Z
- Summary: Information about the Zscaler Adaptive Access Engine used for monitoring user and device activities and managing access control to applications.

Cyber threats are becoming increasingly sophisticated, and organizations must ensure that access to sensitive applications is dynamically managed based on real-time context and risk signals.

Zscaler offers continuous adaptive access to monitor users, devices, and security context to enforce adaptive access control at policy enforcement points in Internet & SaaS (ZIA) and Private Access (ZPA). The Zscaler Adaptive Access Engine ingests security signals and distributes [adaptive access profiles](https://help.zscaler.com/unified/about-adaptive-access-profiles) in real time to policy decision points, so security policies can be evaluated and adapted based on the nature of the identities, their profiles, and the applications being accessed. The Adaptive Access Engine provides a unified way to analyze and interpret events in a specific context to derive insightful results. These events might occur inside or outside the Zscaler ecosystem.

Adaptive Access Engine includes the following key features and benefits:

- **Real-Time Risk Mitigation**: Leverage real-time signals from Zscaler and third-party security systems, to dynamically adjust user access, thereby immediately closing the window of opportunity for malicious actors.
- **Enhanced Security Posture**: Integrate diverse signals (user risk profiles, device postures, behavioral analytics) into access decisions to ensure a robust security framework.
- **Operational Efficiency**: Automated policy adjustments based on real-time data, reducing the need for manual intervention, administrative overhead, and the potential for human error.
- **User Experience**: Improve overall user experience with conditional access based on real-time evaluations that prevent unnecessary access denials.

## Prerequisites

To leverage Adaptive Access Engine, your organization must meet the following prerequisites:

- Authentication Service for end users is enabled or end user migration to Authentication Service is completed.
- Zscaler Admin Console is enabled.
- Internet & SaaS or Private Access is used.

If your organization does not meet these prerequisites, Zscaler recommends that you complete the following steps to be able to use Adaptive Access Engine:

- Migrate your admins to Authentication Service. To learn more, see, [Migrating Zscaler Service Admins to Authentication Service](https://help.zscaler.com/zidentity/migrating-zscaler-service-admins-zidentity).
- Migrate your end users to Authentication Service. To learn more, see [Migrating End Users to Authentication Service](https://help.zscaler.com/authentication-service/migrating-end-users-authentication-service).

For any assistance or queries, contact your Zscaler Account team.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-alert-email-risk360","lastmod":"2026-02-11T06:27Z","nid":"1533801"} -->
## Understanding Alert Email for Risk360

- Source: https://help.zscaler.com/unified/understanding-alert-email-risk360
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Alerts > Understanding Alert Email for Risk360
- Last modified: 2026-02-11T06:27Z
- Summary: Information about the alert email sent in Risk360.

Alert notifications are sent via email if this option is chosen when configuring the alert rule. To learn more, see [Configuring an Alert Rule](https://help.zscaler.com/risk360/configuring-alert-rule).

The alert email shows the following details:

- **Alert Rule Name**: The name of the alert rule. Click the name of the alert rule to view further details in the Admin Portal.
- **Alert Severity**: The level of severity for this alert rule. The severity is set when the rule is configured.
- **Alert Criteria**: These are the criteria selected when the alert rule was configured. If one or more alert criteria are met, then it is highlighted.
- **Started On**: This indicates the alert start time.
- **Ended On**: The alert end time. If the alert end time has not ended, then it indicates Ongoing.
- **Alert Cause**: The activity that triggered the criteria in the rule.
- **Customer Message**: The custom message specified when the rule was configured.

Click **View Alerts in Risk360** to view further details in the Admin Portal.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-copilot-use-cases","lastmod":"2026-05-26T21:06Z","nid":"1503891"} -->
## Understanding Copilot with Use Cases

- Source: https://help.zscaler.com/unified/understanding-copilot-use-cases
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Copilot > Understanding Copilot with Use Cases
- Last modified: 2026-05-26T21:06Z
- Summary: Provides use case examples when conversing with Copilot.

Copilot for Digital Experience Monitoring engages with you as if it were a conversational partner. It can be your teacher, expert, or assistant to enhance your digital experience by creating in-depth and knowledge-based conversations powered by AI/ML. These insights empower you with the knowledge and expertise to navigate through digital experience issues within your organization.

The following use case examples show how you can engage in conversation with Copilot:

- Analytics
- Troubleshooting
- Configuration
- Learning
- Optimization

Copilot's analytics, powered by AI/ML, creates a deep dive into granular details and overview of users and their impacted devices.

The following are examples of questions or commands for analytics:

- What is John Doe's ZDX Score for the Zoom application in the last 4 hours?
- Show me John Doe's page fetch time for SharePoint in the last 4 hours.
- Analyze John Doe's Microsoft Teams call quality issue.
- Show me the Incidents in the last 48 hours.
- Show me the Wi-Fi signal quality for John Doe.
- Analyze John Doe's Wi-Fi issue.
- Show me the number of ongoing alerts.
- Show me the number of applications impacted by alerts.

Wi-Fi Analysis Scenario

You can use Copilot to find root causes that can impact multiple users. Copilot is an expert with in-depth knowledge of finding complex correlations and patterns that require remediation.

The following are examples of questions or commands for troubleshooting:

- What can I do when I see a blackout incident with an ISP?
- For the impacted users with a low ZDX Score, what is the recommended course of action to resolve their issues?
- Why is John Doe's packet loss low?
- Troubleshoot John Doe's ZDX Score for SharePoint in the last 4 hours.

ISP-Related Issues Scenario

Copilot provides guidance on how to create configurations within the Zscaler Admin Console. These can include alerts, probes, administration, or webhook configurations.

The following are examples of questions or commands for configuration:

- How do I onboard Digital Experience Monitoring for my organization?
- How do I configure a webhook for OpsGenie?
- What are the steps to add a ZDX Admin?
- Configure a Web probe for Box.

Copilot is an expert on Digital Experience and can provide answers to your knowledge-based questions to enhance your learning.

The following are examples of questions for learning:

- What is the ZDX Score?
- How is the ZDX Score calculated?
- What is Dynamic Alerting?
- Where is my data stored?

You can ask Copilot for best practices and areas of focus to optimize your digital experience.

The following are examples of questions for optimization:

- What are the best practices for latency to a Private Service Edge for Internet & SaaS?
- Can you find the impacted users that have a ZDX Score below 50 and propose a method of alerting?
- What probe type should I configure for Microsoft Teams?

The following conversation example is a scenario where users are impacted by an ISP that is reducing their access to their organization's resources:

Admin: What is the issue with high latency on devices in the Dallas office?

Copilot: Analyzing Cloud Paths...The issue is with an ISP outage.

Admin: Do you have any recommendations?

Copilot: I suggest notifying the ISP for a resolution or rerouting traffic through an alternate ISP.

Admin: Can you compile Cloud Path data for an impacted user? I will be using this data to contact the ISP.

Copilot: Certainly. Compiling the Cloud Path data.

The following conversation example is a scenario where users are impacted by their Wi-Fi instability:

Admin: There are several users reporting Wi-Fi drops. Can you identify the cause?

Copilot: Checking device telemetry...There's interference in Wi-Fi channels 6 and 11.

Admin: Which channels are currently less congested?

Copilot: Channels 1 and 13 are less congested.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-entity-types","lastmod":"2026-07-29T12:38Z","nid":"1541700"} -->
## Understanding Entity Types

- Source: https://help.zscaler.com/unified/understanding-entity-types
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Management > Understanding Entity Types
- Last modified: 2026-07-29T12:38Z
- Summary: Information about the entity types in the Security Operations Platform.

An entity is a distinct object or resource with defined attributes used to organize information. In security operations, entities like vulnerabilities, assets, alerts, and findings structure the data essential for identifying risks and managing exposures. These entities create relationships that drive effective prioritization and streamlined remediation, empowering teams to efficiently organizational risk. While there are many entities in the Security Operations Platform, the following sections describe the core entities for Unified Vulnerability Management (UVM), Asset Exposure Management (AEM), SOC Workbench, and Identity Protection.

test note

UVM Entity Types

The following sections show information about UVM entity types:

## Global Vulnerability

A global vulnerability represents a publicly disclosed security vulnerability documented by the National Vulnerability Database (NVD) and is assigned a unique CVE identifier (e.g., CVE-2023-32360). These vulnerabilities are enriched with additional context such as:

- Reference tags for cross-verification
- CVSS scores for severity assessment
- Details on affected Common Platform Enumerations (CPEs) and associated Common Weakness Enumerations (CWEs)

A global vulnerability can exist on multiple assets and components across your environment. Each occurrence of the vulnerability is treated as a unique finding, linked back to the original global vulnerability entity for centralized tracking and contextual analysis.

## Finding

A finding is a specific instance of a vulnerability or misconfiguration detected on a particular component of an asset. For example, CVE-2024-30068 can represent a vulnerability affecting Windows 10 on a workstation.

## Asset

An asset refers to any organization-owned resource that can carry risk due to potential vulnerabilities or misconfigurations. Assets represent the core targets for security monitoring and remediation efforts and can include:

- Servers (e.g., production or cloud instances)
- Endpoints (e.g., workstations, laptops)
- Code Repositories (e.g., GitHub repositories)
- Containers (e.g., Docker images)
- Images (e.g., virtual machine templates)

## Component

A component is the specific part of an asset that introduces a vulnerability or misconfiguration to the environment. Components often require remediation or patching and can be classified as:

- Software (e.g., Adobe Acrobat, Google Chrome)
- Packages (e.g., Python libraries, Java dependencies)
- Operating systems (e.g., Windows 10, Linux)

## Ticket

A ticket acts as the operational entity for tracking, grouping, and initiating the remediation process for vulnerabilities and misconfigurations. It provides a structured mechanism for collaboration across teams and integration with case management platforms (e.g., JIRA, ServiceNow). Tickets typically aggregate findings based on configurable grouping rules tailored to your organization's needs. For example:

- Findings with the same component name across assets can be grouped together.
- Findings tied to similar asset types (e.g., all endpoints running Adobe Acrobat vulnerabilities) can be consolidated into a single ticket.

Tickets serve as actionable work items assigned to relevant teams. For instance, a ticket can represent multiple vulnerabilities (e.g., various CVEs affecting Adobe Acrobat) that can be remediated collectively by updating the software to its latest version.

AEM Entity Types

A violation ticket in AEM functions similarly to a standard ticket, but it is specifically designed to consolidate and track policy violations. A violation ticket aggregates violations based on configurable grouping rules, with the default grouping rule based on the policy assignee and policy name. This ensures that all policies assigned to the same person are grouped together for streamlined management and remediation.

For example, policy violations assigned to the same individual and related to the same policy name (e.g., "Missing in CMDB") are consolidated into a single violation ticket. Violations grouped this way help ensure clear accountability and facilitate efficient remediation efforts across assigned teams.

Violation tickets also serve as actionable items and assist in maintaining compliance while reducing complexity in addressing policy violations.

SOC Workbench Entity Types

The following sections show information about SOC Workbench entity types:

## Alerts

An [alert](https://help.zscaler.com/soc-workbench/about-alerts) is a security notification generated by third-party products and Zscaler. SOC Workbench aggregates and reduces alerts via agentic AI.

## Incidents

An [incident](https://help.zscaler.com/soc-workbench/about-incidents) is a grouping of alerts, representing the full attack story rather than isolated data points.

Identity Protection Entity Types

An [identity finding](https://help.zscaler.com/identity-protection/about-identity-findings) is a specific instance of a risk or misconfiguration detected in affected identity entities such as users, groups, or applications.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-field-unification-data-model-and-application-settings","lastmod":"2026-07-29T12:20Z","nid":"1541702"} -->
## Understanding Field Unification, Data Model, and Application Settings

- Source: https://help.zscaler.com/unified/understanding-field-unification-data-model-and-application-settings
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Management > Understanding Field Unification, Data Model, and Application Settings
- Last modified: 2026-07-29T12:20Z
- Summary: Understanding the interaction between field unification and other functionalities that affect field values.

Field unification is the process of resolving conflicts and transforming data attributes in records merged during entity unification. Field unification is a two-step process. First, attribute reconciliation addresses conflicts in merged entity attribute values, resolving them via system defaults or source-prioritized logic. Second, attribute transformation applies business rules to classify and enrich data. To learn more, see [What Is Data Unification?](https://help.zscaler.com/unified/what-data-unification) and [Configuring Field Unification](https://help.zscaler.com/unified/configuring-field-unification).

In addition to field unification, the data model and grouping rules affect field values. Grouping rules update the corresponding field unification rules, and field unification rules update the corresponding rules in the data model unidirectionally.

[Image: Grouping rules will update Field Unification which will then update Data Model rules.]

## Field Unification vs. Data Model

All fields within the Security Operations Platform (SecOps Platform) are preconfigured with system-defined reconciliation logic in the data model. While the data model allows for advanced default value configurations, reconciliation logic can be achieved with the simple, no-code field unification rules. When you first configure field unification for a specific field, the system prompts you to unlink and override on the [Data Unification - Fields page](https://help.zscaler.com/unified/configuring-field-unification) to confirm this change. This action overrides the existing data model configuration.

See image.

[Image: Unlink and override field unification configured through data model]

Creating or updating a field unification rule populates and overrides the corresponding data model logic, but any subsequent changes made to the data model's rule for that field are not reflected in the field unification rule. Always save after unlinking and overriding to confirm updates.

## Field Unification vs. Grouping Rules

SecOps Platform ticket (e.g., Ticket, Violation Ticket) [grouping rules](https://help.zscaler.com/uvm/configuring-grouping-rules) are typically managed within Unified Vulnerability Management (UVM) or Asset Exposure Management (AEM), but they can also be configured on the Data Unification - Fields page. When you create or update a SecOps Platform ticket grouping rule in UVM or AEM, it unidirectionally overrides the corresponding rule in field unification. This means that changes are reflected in the associated field unification rules, but not vice versa. So while it's possible to configure grouping rules in field unification and apply them to tickets, these changes won't sync back to the grouping rules setup page.

See image.

[Image: Unlink and override field unification configured through data model]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-internet-saas-and-private-access-clouds","lastmod":"2026-04-08T15:53Z","nid":"1539020"} -->
## Understanding Internet & SaaS and Private Access Clouds

- Source: https://help.zscaler.com/unified/understanding-internet-saas-and-private-access-clouds
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Understanding Internet & SaaS and Private Access Clouds
- Last modified: 2026-04-08T15:53Z
- Summary: Provides information about the Private Access (ZPA), Internet & SaaS (ZIA), and Zscaler Client Connector clouds.

Internet & SaaS (ZIA) and Private Access (ZPA) have multi-cloud infrastructures. An organization is provisioned on one Internet & SaaS cloud, and its traffic is processed by that Internet & SaaS cloud only. To learn more about Internet & SaaS and to find the name of your Internet & SaaS cloud, see [Understanding the ZIA Cloud Architecture](https://help.zscaler.com/zia/understanding-zscaler-cloud-architecture) and [Customizing Your Account Settings](https://help.zscaler.com/unified/customizing-your-account-settings).

For organizations that have Private Access, an organization is provisioned on one Private Access cloud, and its traffic is processed by that Private Access cloud only. To learn more about Private Access and to find the name of your Private Access cloud, see [Understanding the Private Access Architecture](https://help.zscaler.com/zpa/understanding-private-access-architecture) and [Customizing Your Account Settings](https://help.zscaler.com/unified/customizing-your-account-settings).

If you have multiple Internet & SaaS and Private Access tenants, any postures, machine tunnel tokens, and trusted networks you are using come from one Internet & SaaS tenant and one Private Access tenant. If a user needs both Internet & SaaS and Private Access, consolidate those users to a single Internet & SaaS tenant and a single Private Access tenant.

Every cloud is associated to the [Zscaler Admin Console](https://help.zscaler.com/unified/getting-started-experience-center/zscaler-admin-console-access-navigation). The Internet & SaaS clouds (e.g., zscaler.net and zscalertwo.net) can communicate with either of the Private Access clouds (private.zscaler.com and zpatwo.net). The following diagram shows the association between the Private Access, Zscaler Client Connector, and Internet & SaaS clouds at a high level.

[Image: Diagram of the Internet & SaaS, Private Access, and Zscaler Client Connector clouds]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-original-source-and-merged-entity-values","lastmod":"2026-07-29T12:24Z","nid":"1541701"} -->
## Understanding Original Source and Merged Entity Values

- Source: https://help.zscaler.com/unified/understanding-original-source-and-merged-entity-values
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Management > Understanding Original Source and Merged Entity Values
- Last modified: 2026-07-29T12:24Z
- Summary: Information on the original source value compared to the merged entity value when configuring rules in the Security Operations Platform.

Some rule setups in the Security Operations Platform (SecOps Platform) support referencing both the original attribute values from your data sources and the unified values created through [unification](https://help.zscaler.com/unified/what-data-unification). For example, when [creating asset compliance policies](https://help.zscaler.com/aem/configuring-asset-compliance-policies) in Asset Exposure Management (AEM) to maintain an accurate asset inventory, you can check whether critical asset field values are accurate in your Configuration Management Database (CMDB) (e.g., in the ServiceNow CMDB original source).

Referencing original source values is not supported across all rule configuration setups.

The following table describes the difference between the original source value and merged entity value options:

| Field Type | Reference |
| --- | --- |
| Original source value | The raw value retrieved from the original source (e.g., ServiceNow CMDB, CrowdStrike Vulnerabilities) prior to any data processing or deduplication |
| Merged entity value | The unified SecOps Platform value, often merged from multiple sources |

The following diagram demonstrates how raw data from multiple sources (original source values) is merged into a unified asset with standardized attributes (merged entity values).

[Image: Original source value vs merged entity value]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-threatlabz-source","lastmod":"2026-08-02T04:13Z","nid":"1541967"} -->
## Understanding the ThreatLabz Source

- Source: https://help.zscaler.com/unified/understanding-threatlabz-source
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Understanding the ThreatLabz Source
- Last modified: 2026-08-02T04:13Z
- Summary: Information on the ThreatLabz source in the Security Operations Platform.

ThreatLabz is a research organization within Zscaler that focuses on identifying and analyzing emerging threats, vulnerabilities, and attack techniques. As part of their effort, the ThreatLabz team maintains a database of CVEs with information onhow they're mitigated by different Zscaler services. The ThreatLabz source retrieves this data, which is then correlated with your deduplicated asset records and Zscaler service coverage. This information is used to calculate contextualized risk scores based on the level of protection provided by your Zscaler services.

The ThreatLabz source is provisioned in every new account created within the Security Operations Platform (SecOps Platform). To access the ThreatLabz source in the SecOps Platform Admin Portal, go to Configure > Sources. To learn more, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources).

See image.

## ThreatLabz and the Zscaler Client Connector Source

The ThreatLabz source retrieves data on which CVEs are mitigated by which Zscaler services, including Internet & SaaS (ZIA) and Private Access (ZPA). The Zscaler Client Connector Devices source retrieves data on assets in your organization, including which Zscaler services are installed on which assets. By cross-referencing ThreatLabz data with Zscaler Client Connector data, you can determine which of your Zscaler services are protecting against specific CVEs in your security environment. This insight can be used as a mitigating risk factor to inform finding scoring. To learn more, see [Configuring the Zscaler Client Connector Devices Connector](https://help.zscaler.com/uvm/configuring-zscaler-client-connector-devices-connector) and [Configuring the ZIA Devices and Users Connector](https://help.zscaler.com/uvm/configuring-zia-devices-and-users-connector).

### Finding Is Mitigated Field

The Finding Is Mitigated field is populated based on the overlap between data from the two sources, returning TRUE when a match is found (i.e., when a CVE found on your asset is mitigated by a Zscaler service installed on it) or FALSE otherwise. The [unification](https://help.zscaler.com/uvm/what-data-unification) rule checks if any of the services that can mitigate the vulnerability (as reported by the ThreatLabz source) are actually installed on the asset where the vulnerability was detected (as reported by the Zscaler Client Connector Devices source). If the service is installed on the asset, it sets the Finding Is Mitigated field to TRUE. Otherwise, it sets the value to FALSE. To learn more, see [Configuring Field Unification](https://help.zscaler.com/uvm/configuring-field-unification).

See image.

### Score Settings

The Finding Is Mitigated field is used in the score settings for Unified Vulnerability Management (UVM) findings to create the CVE Mitigated By factor, which refines your risk assessment and scoring practices. You can adjust this setting to align with your organization's specific requirements. To learn more, see [Configuring Severity Score Formulas](https://help.zscaler.com/uvm/configuring-severity-score-formulas).

See image.

### Use Case Example

Consider the following example of a CVE finding:

- The ThreatLabz source reports that CVE-2017-3044 is mitigated by Internet & SaaS (i.e., the value of the Vulnerability Mitigation Products field is ZIA).
- The Zscaler Client Connector Devices source reports that Asset A has Internet & SaaS installed and contains the CVE-2017-3044 finding (i.e., the Asset Mitigation Products field value is ZIA).
- CVE-2017-3044 is mitigated by Internet & SaaS, and Asset A has Internet & SaaS installed on it.

These points indicate an overlap between the vulnerability mitigation services (i.e., Internet & SaaS) and the asset mitigation services (i.e., Internet & SaaS). This sets the Finding Is Mitigated field on Asset A for CVE-2017-3044 to TRUE, indicating that the vulnerability is no longer a significant threat to the asset. This information is then used to reduce the severity score of the finding.

The Internet & SaaS service is displayed as ZIA in the SecOps Platform.

[Image: The ThreatLabz source on the Sources page]

[Image: Finding Is Mitigated Unification Rule]

[Image: Finding Is Mitigated mitigating factor]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/understanding-zscaler-cloud-names","lastmod":"2026-06-12T10:39Z","nid":"1538551"} -->
## Understanding Zscaler Cloud Names

- Source: https://help.zscaler.com/unified/understanding-zscaler-cloud-names
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Understanding Zscaler Cloud Names
- Last modified: 2026-06-12T10:39Z
- Summary: Information about the various Zscaler clouds accessed for each Zscaler service.

To maximize operational efficiency, Zscaler built a highly scalable, global multi-cloud infrastructure. An organization is provisioned on one cloud and its traffic is processed by that cloud only. However, some configurations require that you specify the name of the cloud on which your organization is provisioned. For example, you must specify a cloud name when you configure SAML for admin Single Sign-On (SSO). You might also need to know the clouds your organization is provisioned on in order to locate configuration information on [config.zscaler.com](https://config.zscaler.com), maintenance notifications on [Zscaler Trust Portal](https://trust.zscaler.com), or when you are working with Zscaler Support and Zscaler Account teams.

You can find the names of the Internet & SaaS (ZIA) and Private Access (ZPA) clouds on which your organization has been provisioned by accessing the [Account Settings page](https://help.zscaler.com/unified/customizing-your-account-settings) within the Zscaler Admin Console.

The following table lists the production clouds your organization might be provisioned on for each service:

| Zscaler Service | Zscaler Cloud |
| --- | --- |
| Internet & SaaS | zscaler.net; zscalerone.net; zscalertwo.net; zscalerthree.net; zscloud.net |
| Private Access | private.zscaler.com; zpatwo.net |
| Zscaler Digital Experience (ZDX) | zdxcloud.net |
| Zscaler Client Connector | The cloud your organization is provisioned on is based on the Internet & SaaS (ZIA) cloud. For example, if you are provisioned for zscaler.net for Internet & SaaS, then the cloud for Zscaler Client Connector is mobile.zscaler.net. mobile.zscaler.net; mobile.zscalerone.net; mobile.zscalertwo.net; mobile.zscalerthree.net; mobile.zscloud.net |
| Cloud & Branch Connector | The cloud your organization is provisioned on is based on the Internet & SaaS (ZIA) cloud. For example, if you are provisioned for zscaler.net for Internet & SaaS, then the cloud for Cloud & Branch Connector is connector.zscaler.net. connector.zscaler.net; connector.zscalerone.net; connector.zscalertwo.net; connector.zscalerthree.net; connector.zscloud.net |
| Zero Trust Branch | goairgap.com |
| Zscaler Cellular | ztsim.com |
| Zscaler Risk360 | zscalerrisk.net |
| Authentication Service | zslogin.net |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/upgrading-zscaler-experience-center","lastmod":"2026-06-18T14:29Z","nid":"1508581"} -->
## Upgrading to Zscaler Experience Center

- Source: https://help.zscaler.com/unified/upgrading-zscaler-experience-center
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Upgrading to Zscaler Experience Center
- Last modified: 2026-06-18T14:29Z
- Summary: Information about Zscaler Experience Center and how to migrate to this new unified workflow.

[Watch a video about Zscaler Experience Center.](https://fast.wistia.net/embed/iframe/mnoj1tyik0)

Zscaler Experience Center aims to simplify Zero Trust adoption and operations by providing an intuitive interface (i.e., the Zscaler Admin Console) for all users.

## Key Benefits

Experience Center has the following key benefits:

- Simplicity in Zero Trust Implementation: Experience Center offers a guided workflow for onboarding users and configuring security policies, making it easier for organizations to start their Zero Trust journey.
- Enhanced Management: Boost user experience and cybersecurity efficiency with advanced management. Experience Center brings together management for Internet & SaaS applications, Private Applications, Digital Experience Monitoring (DEM), and Endpoint Agents.
- Unifed Analytics and AI Integration: The new interface delivers a cohesive and dynamic experience, featuring an intuitive layout that facilitates persona-focused unified analytics views, as well as interactive experiences powered by a generative AI copilot.

## Migration and Upgrade Processes

The following processes detail how to migrate and upgrade to Experience Center.

### Prerequisites

- You must have administrative users (i.e., admins) who have already migrated to Authentication Service for a centralized identity experience to access Zscaler Internet Access (ZIA), Zscaler Private Access (ZPA), Zscaler Digital Experience (ZDX), Zscaler Client Connector, and other Admin Portals. To learn more, see [Migrating Zscaler Service Admins to Authentication Service](https://help.zscaler.com/authentication-service/migrating-zscaler-service-admins-authentication-service).
- Admins do not need to take any further action in order to upgrade to Experience Center. Upon receiving the migration notification, admins can log in to the Zscaler Admin Console using their existing Authentication Service authentication credentials.
- Migrate your end users to Authentication Service. To learn more, see [Migrating End Users to Authentication Service](https://help.zscaler.com/authentication-service/migrating-end-users-authentication-service).

### Accessing Experience Center after Migration

Zscaler has simplified the login process by learning the admin's identity and redirecting them to their respective tenant. Irrespective of the Zscaler cloud that the tenant belongs to, the login URL is [https://console.zscaler.com](https://console.zscaler.com) for the production cloud and [https://beta.console.zscaler.com](https://beta.console.zscaler.com) for the beta cloud. All existing configurations are retained and can be managed via the new Experience Center UI, the Zscaler Admin Console.

Alternatively, you can access Experience Center through the banner displayed on the Authentication Service Admin Portal.

[Image: Banner in the Authentication Service Admin Portal allowing you to access Experience Center]

#### Key Features

All of your existing configurations, reports, and dashboards are available on the unified user interface (i.e., the Zscaler Admin Console). In addition, Experience Center offers the following:

- Unified analytics make it a breeze to navigate across consolidated views, from users to cyber and data protection, ensuring admins and IT leaders can quickly get a snapshot of events across their organization.
- Streamlined onboarding workflow for new tenants to add users, distribute the Zscaler Client Connector app, and secure traffic by enabling policies that are recommended by Zscaler as a best practice.

#### Accessing Standalone Portals

Access to the current standalone portals (i.e., Admin Portals) will continue to be supported at this time. All portals can be accessed via Authentication Service.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/using-mapping-copilot","lastmod":"2026-07-29T03:47Z","nid":"1541960"} -->
## Using Mapping Copilot

- Source: https://help.zscaler.com/unified/using-mapping-copilot
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Source Configuration > Using Mapping Copilot
- Last modified: 2026-07-29T03:47Z
- Summary: How to use Mapping Copilot in the Security Operations Platform to assist in mapping data sources to the data model.

Mapping Copilot is an AI-powered assistant within the Security Operations Platform (SecOps Platform). It supports admins by accelerating the data mapping process for data source integrations. This is especially useful when the source schema is unfamiliar, which is common with organization-specific proprietary sources (i.e., [AnySource](https://help.zscaler.com/uvm/connecting-anysource)) and integrations that do not include default mappings. By analyzing file statistics and system guidelines, Mapping Copilot provides intelligent, context-aware recommendations for mapping third-party source data to the SecOps data model, transforming complex data investigations into simple, guided conversations.

Using natural language prompts, you can instruct Mapping Copilot to automate schema alignment, identify target entities (e.g., [Assets](https://help.zscaler.com/uvm/about-assets-uvm), [Findings](https://help.zscaler.com/uvm/about-findings)), and generate mapping logic, including Python scripts. This ensures source data is accurately mapped to the SecOps data model for downstream [unification](https://help.zscaler.com/uvm/what-data-unification) and subsequent consumption in SecOps applications workflows and analytics (e.g., the UVM and AEM [applications](https://help.zscaler.com/uvm/what-zscaler-security-operations)). To learn more, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources).

Mapping Copilot analyzes the source data to identify and suggest mapping logic for the SecOps Platform's core data model entities:

- Assets: Identifies cloud resources, host machines, and device metadata to recommend mappings. It automatically distinguishes between primary identifiers (e.g., UUIDs and MAC addresses) and descriptive attributes, supporting accurate asset inventory and deduplication.
- Findings: Suggests mappings for security vulnerabilities, misconfigurations, and compliance gaps, and aligns third-party finding data (e.g., severity, status, and descriptions) with the SecOps Platform's schema for use in Unified Vulnerability Management (UVM).
- Other standard entities: Simplifies the mapping of Components, Org Entities, and Global Vulnerabilities. Early-stage support is available for Alerts, IoC, and MITRE. These suggestions should be reviewed with extra scrutiny. Mapping to custom entities is not currently supported.

## Prerequisites

Before using Mapping Copilot, ensure the following:

- You have set up a data source and completed at least one successful run within the last 90 days. Mapping Copilot requires the data from a successful run to analyze the data context. To learn more, see [Creating Data Sources](https://help.zscaler.com/unified/creating-data-sources) and [Tracking Data Source Runs](https://help.zscaler.com/unified/tracking-data-source-runs).
- Your assigned role must include the Read and Edit permissions under the Platform - Data Source Mapping resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles). See image.

Account admins can remove access to Mapping Copilot by turning off AI capabilities in Account Settings. To learn more, see [Managing AI Capabilities in the Security Operations Platform](https://help.zscaler.com/uvm/managing-ai-capabilities-secops-platform).

## Using Mapping Copilot to Map Sources

After [creating a data source](https://help.zscaler.com/unified/creating-data-sources), you must [map its data](https://help.zscaler.com/unified/mapping-data-sources) to the SecOps Platform to ensure it is consumable by the data model for the downstream Extract, Transform, and Load (ETL) processes. You can use Mapping Copilot to accelerate this process by generating recommendations for aligning your source data to the SecOps model.

To use Mapping Copilot:

1. In the SecOps Platform Admin Portal, go to **Configure**> **Sources**.
2. Choose one of the following methods: The **Map <**Source**>** page appears.
  - Hover over a source, and click the **Map Data**icon.
  - Select the checkbox of a data source from the list, and click **Map Data**on the toolbar.
3. On the **Map <**Source**>** page, click the **Mapping Copilot**icon in the top-right corner. The **Mapping Copilot**chat appears. See image. The system performs an initial analysis of the source file to extract context, data types, and statistics. This can take a few minutes depending on the file size. You cannot send prompts until this analysis is finished.
4. Enter a prompt describing your mapping guidance request, or select a default prompt. Build mappings in small, testable increments. For example, start with the Asset entity. When asset mappings are stable, you can expand to Findings, instead of trying to map everything the first time. Common use cases for mapping guidance include: Mapping Copilot does not currently support configuring explode for array fields. If your mapping requires exploding an array, configure it manually in the mapping editor. To learn more, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources).
  - Data exploration: Identify which SecOps entities your source data represents (e.g., Assets, Findings), or inspect a field's format, type, and sample values. Try asking: "Which entity does this source map to?", "What do the `severity` and `created_at` fields look like?", "What is the most common value for <field>?", "How many distinct values does <field> have?", or "What is the null rate for <field>?"
  - Targeted mapping: Generate suggested mappings for a specific entity, or align source values to SecOps values or scales. Try asking: "Map to Asset" or "Map to Asset using `device_id`, `hostname`, and `ip_address`" or "Normalize `severity` and `status` values to match the SecOps schema"
  - Mapping justification: Understand the reasoning behind a specific suggestion, mapping, or generated script. Try asking: "Why did you map `device_id` as the primary identifier instead of `hostname`?" or "Explain the logic in the script used to map `asset_type`"
  - Transformation logic and scripting: Create transformation logic for parsing or reformatting data. Try asking: "Generate a Python script to parse/transform <field>" or "Generate a Python script to convert `event_time` from epoch milliseconds to Zscaler's required date format"
5. Review the suggestions provided by Mapping Copilot in the chat, and choose how to insert editable draft mappings into your workspace:
  - **Apply**: Overwrite your current mapping configuration with Mapping Copilot's suggestions.
  - **Append**: Add the new mappings to your workspace without altering existing configurations.
6. Ask follow-up questions, compare alternatives, and refine the mapping until it fits your environment. If you are unsure about a mapping suggestion, ask Mapping Copilot to validate it against the source data and provide options:
  - Provide sample values and basic statistics for the fields used (e.g., null rate and distinct value count for candidate identifiers).
  - Explain its assumptions about the data (e.g., identifier uniqueness and stability, timestamp units and time zone, and severity or status scale).
  - Propose alternative mapping approaches (e.g., two identifier strategies), and describe the benefits, risks, and when to use each.
7. Review the resulting connections in the **Mapped Connections** section. Mappings suggested by Mapping Copilot are marked with an AI icon. See image. Mapping Copilot provides guidance only and cannot be relied on as a final mapping configuration. Make sure to thoroughly test suggestions in your environment before saving them.
8. Click **Save**to finalize the mapping.

After saving the mapping, [run the source](https://help.zscaler.com/unified/managing-data-sources#processing) again and verify the results in your data and downstream workflows. You can inspect the raw ingested records in [Logs](https://help.zscaler.com/uvm/building-queries-searching-logs) to confirm incoming values and formats, or validate mapped output by entity in [Entity Explorer](https://help.zscaler.com/uvm/using-entity-explorer). Mapping Copilot does not verify end-to-end correctness or downstream behavior, so refine the mapping as needed and rerun until the output is consistent. For end-to-end mapping guidance, see [Mapping Data Sources](https://help.zscaler.com/unified/mapping-data-sources).

When you complete the mapping of all data sources for your account, configure data unification to process and merge the mapped output from all sources into a single, consistent source of truth. To learn more, see [What Is Data Unification?](https://help.zscaler.com/uvm/what-data-unification), [Configuring Entity Unification](https://help.zscaler.com/uvm/configuring-entity-unification), and [Configuring Field Unification](https://help.zscaler.com/uvm/configuring-field-unification).

## Privacy and Consent

Mapping Copilot operates within Zscaler services, and data shared with it is not transmitted to external third parties. Some source data and mapping content might include sensitive information, so users should review content before submitting it. By using the assistant, users consent to sharing the information they enter for generating mapping guidance and remain responsible for the data they choose to provide. To learn more about Zscaler's privacy practices and AI data handling, see the [Acceptable Use Policy](https://help.zscaler.com/legal/acceptable-use-policy).

Use of AI-powered features in the SecOps Platform is optional. Availability is controlled via admin opt-in in your account. To learn more, see [Managing AI Capabilities in the Security Operations Platform](https://help.zscaler.com/uvm/managing-ai-capabilities-secops-platform).

[Image: Mapping Copilot icon on the Map Qualys Assets connector page]

[Image: Mapping Copilot role permissions]

[Image: Mapped Connections AI icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/using-tables","lastmod":"2026-06-08T17:24Z","nid":"1492406"} -->
## Using Tables

- Source: https://help.zscaler.com/unified/using-tables
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Using Tables
- Last modified: 2026-06-08T17:24Z
- Summary: How to use tables in the Zscaler Admin Console.

In the Zscaler Admin Console, some of the data is organized and displayed in tables. You can modify the default settings for each table. Every time you make a change, the settings are stored in your browser's local storage so that your changes are preserved the next time you log on. If you clear your cache or use a different browser, your settings are lost.

Not all settings are available for all tables.

You can do the following:

- Expand data in the table
- Filter data in the table
- Hide columns
- Reorder columns
- Reset table to default settings
- Resize columns
- Search tables
- Sort the column data

Some tables have rows that can expand to show more detail. Click the Expand icon in the first column within a table to expand it. To expand all rows in a table, click **Expand All**. After a row is expanded, additional details are provided. After a row is expanded, you can view or edit a resource.

To view or edit a resource, click the name of the resource within the expanded row of the table to view or edit the resource.

You can filter the information shown in some tables. To filter the data in the table:

1. Click a filter (e.g., **Name**, **Created By**).
2. Select or enter values.
3. (Optional) Click **Apply**if available for the table**.**
4. (Optional) Select from the following options, if available for the table:
  - **Clear**to remove selections
  - **Reset**to undo applied filters
  - **Filter** icon to hide the filters

On certain pages, you can select the following additional functions:

- Save and manage applied filters
- Switch between applied filters

To save and manage applied filters:

1. Click the **Menu**icon.
2. Click **Save Applied Filter**. The **Save Applied Filter** window appears.
3. In the **Save Applied Filter** window, give the filter a name.
4. Click **Save**.

After applying filters, you can switch between applied filters by clicking the filter name next to the **Filter**icon. You can also delete a saved filter with the **Delete**icon.

If you modify a saved filter, click the **Menu**icon, and then click **Save Changes** (or **Discard Changes**). An Information icon next to the filter name indicates unsaved changes.

You can select and hide columns in some tables. The hide function is useful when you don't need to view all the columns. You can select the columns that you don't want to view.

To hide a column, hover over the **Column Menu** icon on the top right of the table, or click the **Gear**icon, and uncheck the column names you wish to hide. After you click on a checkbox, the table refreshes and displays only the checked columns.

For some tables, you must have at least one column visible at all times. For example, for Insight log tables, you can **Deselect All** columns and render the table empty.

[Image: Screenshot of hiding columns for Zscaler tables]

You can reorder columns in many tables. When the option is available, you can reorder columns in the following ways:

- From the Table
- From the Column Menu settings

For some tables, when you hover over a column header in the table, a **Move** cursor appears. To reorder, drag and drop the column to the desired location. An arrow indicates where the column is placed after the drop.

After you have dragged the column to the desired location, the table refreshes and displays data in the new column order.

The column menu settings also change to reflect the new order.

For some tables, when you hover over a listed column name in the settings, a **Move** cursor appears. Drag and drop the column to the desired position. After you drop the column name, the table refreshes and displays the data in the new column order.

For some tables, when you make changes to the columns, you can revert to the default settings by clicking on the **Reset** icon.

Resetting columns to default settings does not affect sort order.

[Image: Screenshot of Reset icon for Zscaler tables]

For some tables, when the data in a column takes up more space than the viewing area allows, it gets clipped. You can view the clipped data in the tooltip. For many tables, you can also increase the column width.

To increase the column width, hover over the right border of the column. A resize icon appears. Drag and drop to the desired column width.

You can use the search field to filter data in tables. The table displays the rows containing your search term.

The search results display the applicable rows even if the term is in a hidden column.

For some tables, there is also a search field that you can access even with the hiding or showing columns option. The menu displays the column names containing your search term.

For some tables, you can sort data. Sort is indicated by an arrow icon. When you hover over a column header, you see the arrow icon. You can sort data in ascending or descending order.

To sort a column, click the arrow in the table column header.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-activity-dashboard","lastmod":"2026-02-11T06:05Z","nid":"1498216"} -->
## Viewing the Activity Dashboard

- Source: https://help.zscaler.com/unified/viewing-activity-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Digital Experience > Viewing the Activity Dashboard
- Last modified: 2026-02-11T06:05Z
- Summary: Information on the Activity dashboard and widgets accessible within the Admin Portal.

The Activity dashboard provides an overview of the user, device, and application activity in your organization.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between 2 hours and 48 hours in which to view data. Select **Custom** to select a specific time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments. The selected period applies to all data within the dashboard. The default time range is **2 Hours**.
- **Filter data**: Click the filters in the top left to limit the data shown. Each filter allows you to include or exclude individual options. Click **Apply**to update the dashboard with your selections. To remove all filter selections, click **Reset**.
  - **Departments**: Your departments. To learn more, see [About Departments](https://help.zscaler.com/unified/about-departments).
  - **Zscaler Locations**: Your locations. To learn more, see [About Locations](https://help.zscaler.com/unified/about-locations).
  - **User Groups**: The names of user groups in your organization.
  - **Geolocations**: The geographic areas where your users are located.

## Dashboard Widgets

- Active Users/Active Devices
- User Distribution by Activity Score
- Top 5 Applications with Lowest Experience Score
- Regions by Average Application Activity Score
- Wi-Fi Performance

This widget shows the number of users and devices currently active in your organization.

See image.

This widget shows the users in your organization classified by Digital Experience category: Poor, Okay, and Good. To learn more about the Digital Experience score (also called the ZDX Score), see [About the Digital Experience Score](https://help.zscaler.com/zdx/about-zdx-score).

See image.

This line graph shows the five applications in your organization that have the lowest Digital Experience score over the selected time period. To learn more about the Digital Experience score (also called the ZDX Score), see [About the Digital Experience Score](https://help.zscaler.com/zdx/about-zdx-score).

See image.

This widget shows a map indicating the Digital Experience in various regions around the world. Hover over a circle on the map to view the average latency at that location and the number of users in that region classified by Digital Experience category. To learn more about the Digital Experience score (also called the ZDX Score), see [About the Digital Experience Score](https://help.zscaler.com/zdx/about-zdx-score).

See image.

This bar graph shows the devices in your organization that are using Wi-Fi, broken down by Wi-Fi connection speed.

See image.

[Image: Widget showing User Distribution by Experience Score on the Activity dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-advanced-threats-dashboard","lastmod":"2026-02-11T06:09Z","nid":"1498791"} -->
## Viewing the Advanced Threats Dashboard

- Source: https://help.zscaler.com/unified/viewing-advanced-threats-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Cybersecurity > Viewing the Advanced Threats Dashboard
- Last modified: 2026-02-11T06:09Z
- Summary: Information on the Advanced Threats dashboard and widgets accessible within the Admin Portal.

The Advanced Threats dashboard provides an overview of cybersecurity threats in your organization.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **90 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **14 Days**.
- **Threat Category**: Select the categories of threat you want to include or exclude:
  - Threat Categories

## Dashboard Widgets

- Incoming Real Time Threats
- Advanced Threat Incidents

This line graph shows the number of incidents recorded for the selected time period. Hover over a date in the graph to see the total number of incidents for that date and the breakdown among the threat categories.

See image.

This table provides a detailed list of threat incidents for the specified time range and type.

- **Name**: The name of the threat incident.
- **Category**: The threat category (e.g. **Spyware**, **Phishing**, etc.)
- **Impacted Systems**: The number of systems affected by the incident.
- **Status**: The action taken on the threat, e.g. **Allowed**or **Blocked**.
- **Last Known Date**: The date and time of the last known attempt by the threat.
- **First Known Date**: The date and time of the first known attempt by the threat.

See image.

Click any row in the table to see additional detail on that advanced threat incident on two tabs:

- The **Details**tab has information about the threat:
  - **Policy Action**: The policy action enforced on transactions related to the incident.
  - **Event Type**: The type of threat event.
  - **Last Known Attempt**: The date and time of the last known attempt by the threat.
  - **First Known Attempt**: The date and time of the first known attempt by the threat.
  - **Duration**: The duration between the first and last known attempt by the threat.
  - **Total Transactions**: The total number of transactions related to the threat.
  - **Total Bytes**: The total bytes of data for all the transactions related to the threat.
  - **File Name**: The name of the file related to the incident.
  - **Sandbox Category**: The name of the sandbox category, if the file is sent for sandbox analysis.
  - **File Type**: The type of file involved in the incident.
  - **File Size**: The file size involved in the incident.
  - **MD5**: The MD5 hash for the file that triggered the rule.
  - **SHA-2 (256-bit)**: The hash of identical files.
  - **Destination IP**: The destination IP.
  - **Hostname**: The hostname involved in the incident.
  - **Application Category**: The application category related to the incident, if applicable.
  - **Application**: The name of the application involved in the incident, if applicable.
  - **URL Category**: The [URL category](https://help.zscaler.com/zia/about-url-categories) of the incident.

See image.

- The **Impacted Systems**tab has information about the systems and users affected by the threat:
  - **User Name**: The name of the user.
  - **Client IP**: The IP address from which the transaction originated. This is the IP address of the client device.
  - **Client External IP**: The internet gateway location IP address of the client.
  - **Last Known Attempt**: The date and time of the last known attempt by the threat for the user.
  - **First Known Attempt**: The date and time of the first known attempt by the threat for the user.

See image.

- Malware
- Spyware
- Virus
- Sandbox Adware
- Sandbox Anonymizer
- Sandbox Malware
- Sandbox Offensive Security Tools
- Sandbox Ransomware
- Suspected Spyware or Adware
- Adware/Spyware Sites
- Advanced Security
- Botnet Callback
- Browser Exploit
- Crypto Mining
- Domain Generated Algorithm Domains
- Malicious Content
- Suspicious Content
- Peer-to-Peer
- Phishing
- Suspicious Destinations
- Unauthorized Communication
- Webspam
- Cross-site Scripting
- Unknown (this represents all the incidents that the Zscaler service was unable to classify under a threat category or were not classifiable under any of the preceding categories)
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-appliances-dashboard","lastmod":"2026-02-11T06:02Z","nid":"1518036"} -->
## Viewing the Appliances Dashboard

- Source: https://help.zscaler.com/unified/viewing-appliances-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Operational > Viewing the Appliances Dashboard
- Last modified: 2026-02-11T06:02Z
- Summary: Information on the Appliances dashboard and widgets accessible within the Admin Portal.

The Appliances dashboard shows you the deployment and active status of the Cloud & Branch Connector appliances in your organization so you can see at a glance how many connectors are available, deployed, in use, or disabled.

## Filtering

**Filter data**: Click the filters in the top-left corner to limit the data shown. Each filter allows you to include or exclude individual options.

- **Type**: The type of connector: **Cloud Connector** or**Branch Connector**.
- **Cloud Services**: The type of cloud services used for this connector (e.g., **AWS**, **Azure**, or **GCP**).

After you choose your filters, click **Apply**to update the dashboard with your selections. To remove all applied filter selections, click **Reset**.

See image.

## Dashboard Widgets

- Deployment Status
- Active Status

This ring chart shows the number of Cloud & Branch Connectors deployed. You can hover over segments of the chart to view the number of connectors in each deployment status:

- **Deployed**: The Cloud & Branch Connectors that are available and deployed.
- **Ready to Deploy**: The Cloud & Branch Connectors that are available but not deployed.

See image.

This ring chart shows the number of Cloud & Branch Connectors that are active, inactive, or disabled. You can hover over segments of the chart to view data for each status:

- **Active**: The active Cloud & Branch Connectors that are deployed.
- **Inactive**: The inactive Cloud & Branch Connectors that are deployed.
- **Disabled**: The Cloud & Branch Connectors that are currently disabled.

See image.

[Image: Applying filters on the Appliances dashboard]

[Image: Chart showing Traffic Volume Across Service widget on the Connector Activity dashboard]

[Image: Chart showing Session Across Service on the Connector Activity dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-applications-dashboard-0","lastmod":"2026-02-11T06:13Z","nid":"1498746"} -->
## Viewing the Applications Dashboard

- Source: https://help.zscaler.com/unified/viewing-applications-dashboard-0
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Digital Experience > Viewing the Applications Dashboard
- Last modified: 2026-02-11T06:13Z
- Summary: Information on the Applications dashboard and widgets accessible within the Admin Portal.

The Applications dashboard provides an overview of application performance in your organization.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between 2 hours and 48 hours in which to view data. Select **Custom** to select a specific time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments. The selected period applies to all data within the dashboard. The default time range is **2 Hours**.
- **Filter data**: Click the filters in the top left to limit the data shown. Each filter allows you to include or exclude individual options. Click **Apply** to update the dashboard with your selections. To remove all filter selections, click **Reset**.
  - **Applications**: Applications used in your organization.
  - **Departments**: Your departments. To learn more, see [About Departments](https://help.zscaler.com/zia/about-departments).
  - **Zscaler Locations**: Your locations. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
  - **User Groups**: The names of user groups in your organization.
  - **Geolocations**: The geographic areas where your users are located.
  - **Location Groups**: The names of groups based on location in your organization.
  - **Last Mile ISPs**: The Internet Service Providers (ISPs) to which your users are connecting.
  - **Operating System**: The operating system versions installed on user devices in your organization.

## Dashboard Widgets

- Application Experience Trend
- Applications

This widget shows the trend of the digital experience score for all applications in your organization. Initially, the score represents applications across your organization for the default time range of 2 hours. Depending on the time period and filters selected within the dashboards, the score adjusts accordingly. The score represents all users in your organization, across all applications, all locations, and all cities. Depending on the time period and filters selected within the dashboards, the score adjusts accordingly.

The score falls into one of three categories:

- **Good**: The score is above an acceptable threshold and ranges from 66-100. The color for this range is green.
- **Okay**: The score is acceptable and ranges from 34-65. The color for this range is amber.
- **Poor**: The score is below an acceptable threshold and ranges from 0-33. The color for this range is red.

Click **View Activity** to view more detail about the users, devices, and applications that contribute to the score on the Activity dashboard.

To learn more about the Digital Experience score, see [About the Digital Experience Score](https://help.zscaler.com/zdx/about-zdx-score).

See image.

This table provides a detailed list of the applications being used in your organization for the selected filters.

- **Application Name**: The name of the application.
- **Type**: The type of application, either Internet or Private.
- **Score**: The ZDX score of the application.
- **Departments**: The number of departments where the application is used.
- **Regions**: The number of regions where the application is used.
- **Locations**: The number of locations where the application is used.

Click any application row in the table to see additional detail about that application:

- ZDX Score
- Page Fetch Time
- Regions by ZDX Score
- Impacted Departments
- Impacted Regions
- Impacted Zscaler Locations
- Probe Status

See image.

The **ZDX Score Trend** column shows the application's ZDX Score from 1–100, with 1 being the lowest and 100 being the highest. To learn more, see [About the Digital Experience Score](https://help.zscaler.com/zdx/about-zdx-score).

The **Page Fetch Time** graph tracks how long it takes the selected application to transfer the fetched page to the user during the selected time period. The time is tracked in milliseconds.

A line runs across the graph that indicates the 95th percentile, as identified by **P95**. It indicates that 95 percent of the fetch time is below this amount.

You can select a point on the graph to see the exact date and time, the application's fetch time at that point, and the 95th percentile fetch time for comparison. If there is a Web probe error at that time, it is also displayed in the tooltip. To learn more about Web probe errors, see [Web Probe Errors](https://help.zscaler.com/zdx/web-probe-errors).

The **Regions by ZDX Score** map takes the geographic locations of all users accessing the selected application and organizes the data for the score down to the city level for major cities around the world. This information is displayed in a map. You can zoom in and out of the map to better view regions of interest.

For each marked location on the map, you can see the name of the city, the ZDX Score, and the number of users while hovering the mouse over the marked spot. Click the **Details**icon ([Image: Details icon within tooltip]) to view user information in the Users Overview specific to the location.

The **Impacted Departments** table shows the departments with the lowest ZDX Score for this application.

The departments are listed by score with the lowest score on top. This allows you to compare how different departments are scoring in relation to each other with use of the application.

The **Impacted Regions**widget shows the regions with the lowest ZDX Score for this application.

The regions are listed in a chart by score with the lowest score at the top. This allows you to compare how different regions are scoring in relation to each other with use of the application.

The **Impacted Locations** widget shows the Zscaler locations with the lowest ZDX Score for this application.

The Zscaler locations are listed in a chart by score with the lowest score at the top. This allows you to compare how different Zscalerlocations are scoring in relation to each other with use of the application. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).

For each application, you can see the metrics that previously configured probes are tracking. Each probe has the parameters it uses to evaluate the application with the relevant data. Minimum, average, and maximum values are provided for all the users in a given timeframe. To learn more, see [Configuring a Probe](https://help.zscaler.com/zdx/configuring-probe).

The Probe Status shows metrics for either a Web probes or a Cloud Path probe.

[Image: Bar graph showing Application Experience Trend on the Applications dashboard]

[Image: Table showing Applications on the Applications dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-asset-level-risk","lastmod":"2026-02-16T21:51Z","nid":"1526581"} -->
## Viewing Asset-Level Risk

- Source: https://help.zscaler.com/unified/viewing-asset-level-risk
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Viewing Asset-Level Risk
- Last modified: 2026-02-16T21:51Z
- Summary: Information about assets and their risk score significance in your organization's overall risk.

Asset-level risk (Analytics > Risk360 > Assets) aggregates and visualizes the total number of assets in your organization, highlights risky assets, and further provides drill-downs on these risky assets to understand what's driving the risk. In progression to Risk360's organizational-level risk score representation, and over 100 factors that can drill down to show specific users and locations at risk, asset-level risk helps view the risk score assigned at the asset level. It facilitates the monitoring and remediation of risky assets.

Zscaler’s asset-level risk scoring model considers more than 65 indicators that influence the risk score. The model accounts for the fact that not all indicators are equal; each indicator variably contributes to the risk score based on the severity and frequency of the associated threat. Hence, these indicators are separated into three major categories:

- Pre-infection Behavior
- Post-infection Behavior
- Suspicious Behavior

## Assets Page Widgets

- Overview
- Distribution of Assets by Authentication Status
- Authenticated Assets
- Risky Asset Inventory

View your assets in the following ways:

- Risk Score
- Location

The pie chart shows the percentage split between the number of authenticated and unauthenticated assets. The total number of assets is displayed at the center of the pie chart.

See image.

The chart shows the types of assets available in your organization's traffic with their count. This data is for authenticated assets only.

See image.

The table shows the top 1,000 risky asset details. For each asset, you can view the following information:

- **Asset ID**: The unique ID assigned to the asset by the Zscaler service.
- **Private IP Address**: The IP address of the asset.
- **Egress IP Address**: The client's gateway IP address that sends the local network traffic to other networks on the internet.
- **Username**: The user responsible or owns the asset.
- **Asset Type**: The type of asset (iOS, Android OS, etc.).
- **Authentication Status**: Indicates whether the asset is authenticated or not.
- **Risk Score**: The risk score computed for the asset.
- **Last Seen**: The time and date when the last activity was observed on the asset.
- **Location**: The city where the asset is located. Displays Road Warrior if the asset isn't tied to a location. Click an asset row to view additional details about the risky assets on the [Asset Details](https://help.zscaler.com/unified/viewing-assets-risk) page. You can use the following operators for the table:
  - Filter the data for specific Asset ID, Asset Type, or Location.
  - Download the table data into a CSV file.
  - Search for a specific asset using the asset ID.
  - Use the arrows at the bottom of the table to go to the next page. You can also select the number of entries you want to view on a page.

See image.

The Location view shows all the assets in your organization spread across the globe. The map shows the country where the assets are located. Hover over the country to view the number of assets in them. The size of the bubble signifies the number of assets present, relative to other locations. The top-left of the section displays the number of assets with unknown locations.

The **Top Risky Locations** section at the bottom-left displays the locations with the highest percentage of risky assets in descending order.

See image.

The Risk Score view displays a three-dimensional graph with the Risk Score, Asset Count, and Days. This helps you analyze the number of assets under each severity for a select date:

- Each risk severity is highlighted with a unique color code:
  - No Risk (0–1)
  - Low (>1–25)
  - Medium (>25–50)
  - High (>50–75)
  - Critical (>75–100)
- Your organization's total number of assets is displayed at the top of the section.
- You can view the data for specific severities using the severity checkboxes.
- Hover over the severity dots in the graph to view the number of assets under specific severity for that day. This helps you view the number of assets under each severity for a particular date.
- You can left-click, hold the mouse, and then move the graph to the position in which you want to view the data. Click **Reset Chart Position**to set the graph to its original position.
- You can use your mouse scroll to increase or decrease the size of the graph.

See image.

[Image: Assets Page: Risk Score View]

[Image: Location View]

Pre-infection behavior indicators encompass a range of blocked actions that could lead to an asset infection, such as blocked malware, known and suspected malicious URLs, phishing sites, pages with browser exploits, and more. Some sample indicators for asset risk scoring include:

- Malware blocked by Zscaler’s Advanced Threat Protection or inline Sandbox
- Blocked known and suspected malicious URLs
- Blocked websites with known and suspected phishing content
- Blocked pages with known browser exploits
- Blocked known and suspected adware and spyware
- Blocked pages with a high PageRisk score
- Quarantined pages
- Blocked files with known vulnerabilities
- Blocked emails containing viruses
- Detected mobile app vulnerabilities

Post-infection behavior indicators include a range of blocked actions that are attempted after an asset is infected. Some sample indicators for asset risk scoring include:

- Botnet traffic
- Command-and-control traffic

Suspicious behavior indicators are similar to pre-infection indicators, with less severity and less guarantee of leading to infection. This includes policy violations, risky activities like browsing deny-listed URLs, DLP compliance violations, and anonymizing sites that could lead to an infected asset. Some sample indicators for asset risk scoring include:

- URLs that are denylisted
- DLP compliance violations
- Pages with known dangerous ActiveX controls
- Pages vulnerable to cross-site scripting attacks
- Possible browser cookie theft
- Internet Relay Chat (IRC) tunneling use
- Anonymizing sites
- Blocks or warnings from secure browsing about an outdated/disallowed component
- Peer-to-peer (P2P) site denials
- Webspam sites
- Attempts to browse blocked URL categories
- Mobile app issues that include denial of the mobile app, insecure user credentials, location information leaks, personally identifiable information (PII), information identifying the asset, or communication with unknown servers.
- Tunnel blocks
- Fake proxy authentication
- SMTP (email) issues including rejected password-encrypted attachments, unscannable attachments, detected or suspected spam, rejected recipients, DLP blocks or quarantines, and blocked attachments.
- IPS blocks of cryptomining & blockchain traffic
- Reputation-based blocks of suspected adware/spyware sites
- Disallowed use of a DNS-over-HTTPS site

[Image: Authenticated Assets Section]

[Image: Distribution of Assets by Authentication Status Section]

[Image: Risky Asset Inventory Section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-assets-risk","lastmod":"2026-02-16T21:52Z","nid":"1526651"} -->
## Viewing an Asset's Risk

- Source: https://help.zscaler.com/unified/viewing-assets-risk
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Viewing an Asset's Risk
- Last modified: 2026-02-16T21:52Z
- Summary: Information on analyzing risk for an asset in the Admin Portal.

When you click an asset on the [Assets](https://help.zscaler.com/unified/viewing-asset-level-risk) page (Analytics > Risk360 > Assets), you are redirected to the Asset Details page where you can view the asset's risk score, its risk score trend, location, events that contributed to the risk score change, metadata information, etc. Asset-level risk monitoring is vital in maintaining a healthy risk score and protecting your organization from various security incidents specific to the asset type, location, user, and other parameters that malicious actors can potentially target.

## Asset Risk Widgets

- Asset Risk Score
- Asset Risk Score Trend
- Asset Details
- Asset Location
- Events Contributing to Risk Score for Last 7 Days

This section shows the Zscaler-computed risk score for the asset. The following severities appear for the score ranges:

- Low (1–25)
- Medium (26–50)
- High (51–75)
- Critical (76–100)

See image.

The graph shows the Zscaler-computed risk score trend. Hover over a point in the graph to view the risk score for that date.

See image.

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
- **Enrolled Device Type Version**: The Zscaler Client Connector version installed on the asset.
- **Device Hostname**: The unique ID assigned to the asset to identify it on the internet.
- **OS Type**: The asset's operating system.
- **OS Version**: The operating system version information.

See image.

This section shows where the asset is located on the map. Hover over the asset to view the city and country information.

See image.

This section lists the top events observed within the last 7 days that have contributed to the risk score change of the asset. These events are listed in ascending order with the timestamp. For each event, you can view the:

- Threat Category
- Threat Name
- URL Category
- URL Name

You can click the **Export**icon () to download the risky events to a CSV file and click **View All Events** to view all the risky events in a drawer.

See image.

[Image: Asset Risk Score]

[Image: Asset Risk Score Trend]

[Image: Asset Details]

[Image: Asset Location]

[Image: Events Contributing to Risk for Last 7 Days Section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-connector-activity-dashboard","lastmod":"2026-02-11T06:04Z","nid":"1517171"} -->
## Viewing the Connector Activity Dashboard

- Source: https://help.zscaler.com/unified/viewing-connector-activity-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Networking > Viewing the Connector Activity Dashboard
- Last modified: 2026-02-11T06:04Z
- Summary: Information on the Connector Activity dashboard and widgets accessible within the Admin Portal.

The Connector Activity dashboard provides an overview of Cloud Connector and Branch Connector activity in your organization.

## Filtering

Click **Cloud Connectors** or **Branch Connectors** to toggle between viewing activity for each type of connector.

**Filter data**: Click the filters in the top left to limit the data shown. Each filter allows you to include or exclude individual options.

- **Geolocation**: The geographic area where the Cloud Connector or Branch Connector is located.
- **Status**: The status of the Cloud Connector or Branch Connector (i.e., **Active**or **Inactive**).
- **Connector Type**: The type of Branch Connector (i.e., **Physical**or **Virtual**).
- **Connector Location**: The named location of a Cloud Connector or Branch Connector.

After you make your selection, click **Apply**to update the dashboard with your selections. To remove all applied filter selections, click **Reset**.

See image.

## Dashboard Widgets

- Traffic Volume Across Service
- Session Across Service
- Connectors

This ring chart shows the throughput of the traffic flowing through the Cloud Connectors or Branch Connectors that are provisioned and deployed in your cloud or branch accounts. You can hover over segments of the chart to view data for each type of traffic:

- **Internet**: The throughput forwarded through the Internet & SaaS cloud.
- **Private**: The throughput forwarded through the Private Applications cloud.
- **Direct**: The throughput forwarded directly.
- **Log & Control**: The throughput forwarded through the Log and Control gateway.

See image.

This ring chart shows the session count data for the Cloud Connectors or Branch Connectors that are provisioned and deployed in your cloud or branch accounts. You can hover over segments of the chart to view the session count for each type of data:

- **Internet**: The session count data forwarded through the Internet & SaaS cloud.
- **Private**: The session count data forwarded through the Private Applications cloud.
- **Direct**: The session count data forwarded directly.
- **Log & Control**: The session count data forwarded through the Log and Control gateway.

See image.

This table provides a list of Cloud Connectors or Branch Connectors with selected details. Use **Search**to narrow the results.

- **Name**: The name of the Cloud Connector or Branch Connector.
- **Type**: The type of Branch Connector (i.e., **Physical**or **Virtual**).
- **Group**: The group to which the Cloud Connector or Branch Connector is assigned.
- **Location**: The named location of this Cloud Connector or Branch Connector.
- **Geolocation**: The geographic area where this Cloud Connector or Branch Connector is located.
- **Autoscaling**: The status of autoscaling (i.e., **True**or **False**).
- **Status**: The status of this Cloud Connector or Branch Connector (i.e., **Active**or **Inactive**).
- **HA Status**: The high availability status of the Branch Connector.
- **VM Size**: The size of the virtual machine for a Cloud Connector (i.e., **Small**or **Medium**).
- **Model**: For a physical Branch Connector, the type of hardware device (e.g., **ZT800**); **Virtual**for a virtual connector.

See image.

Click any row in the table to see complete details about a Cloud Connector or Branch Connector.

- To learn more about the Cloud Connector details shown, see [Analyzing Cloud Connector Details](https://help.zscaler.com/cloud-branch-connector/analyzing-cloud-connector-details). You can also view these details by going to **Infrastructure > Connectors > Cloud > Cloud Connector Monitoring**.
- To learn more about the Branch Connector details shown, see [Analyzing Branch Connector Details](https://help.zscaler.com/cloud-branch-connector/analyzing-branch-connector-details). You can also view these details by going to **Infrastructure > Connectors > Edge > Branch Connector Monitoring**.

See image.

[Image: Applying filters on the Connectors dashboard]

[Image: Chart showing Traffic Volume Across Service widget on the Connector Activity dashboard]

[Image: Chart showing Session Across Service on the Connector Activity dashboard]

[Image: Table showing list of Connectors on the Connector Activity dashboard]

[Image: Table showing list of Connectors on the Connector Activity dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-cybersecurity-dashboard","lastmod":"2026-02-11T06:14Z","nid":"1497896"} -->
## Viewing the Cybersecurity Dashboard

- Source: https://help.zscaler.com/unified/viewing-cybersecurity-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Cybersecurity > Viewing the Cybersecurity Dashboard
- Last modified: 2026-02-11T06:14Z
- Summary: Information on the Cybersecurity dashboard and widgets accessible within the Admin Portal.

The Cybersecurity dashboard provides information about malicious activity that affects your organization.

## Filtering

**Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **90 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **14 Days**.

## Dashboard Widgets

- Your Cybersecurity Transactions from Last ___ Days
- Advanced Threat Categories
- Top Threat Locations
- Sandbox Threats
- Your SSL Inspection Review

This widget shows the cybersecurity transactions detected and blocked by Zscaler during the selected time period. Click the toggle to switch between **Malicious Transactions** and **Policy Blocks**.

Click **View Transaction Activity**to see more detail on the [Transactional Activity Dashboard](https://help.zscaler.com/unified/viewing-transactional-activity-dashboard).

See image.

This widget shows the top threat categories stopped by Zscaler. Click **View All Advanced Threats**to see all advanced threat activity on the [Advanced Threats Dashboard](https://help.zscaler.com/unified/viewing-advanced-threats-dashboard).

See image.

This widget shows the countries where threats are originating. Click **View All Threat Locations**to see more detail on the [Threat Locations Dashboard](https://help.zscaler.com/unified/viewing-threat-locations-dashboard).

See image.

This widget shows the flow and dispensation of files sent to the sandbox. Click **View All Sandbox Threats**to see more detail on the [Sandbox Threats Dashboard](https://help.zscaler.com/unified/viewing-sandbox-threats-dashboard).

See image.

This widget shows the SSL traffic that occurred and that was inspected. Click View SSL Inspection to see more detail on the [SSL Inspection Dashboard](https://help.zscaler.com/unified/viewing-ssl-inspection-dashboard).

See image.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-data-channels-dashboard","lastmod":"2026-02-11T06:23Z","nid":"1533709"} -->
## Viewing the Data Channels Dashboard

- Source: https://help.zscaler.com/unified/viewing-data-channels-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Data Security > Viewing the Data Channels Dashboard
- Last modified: 2026-02-11T06:23Z
- Summary: Information on the Data Channels dashboard and widgets accessible within the Admin Portal.

The Data Channels dashboard provides detailed information about data protection-related incidents in your organization.

The top of the page shows the total number of incidents for each data channel type:

- **Inline**: Data loss incidents from inline (web) sources.
- **SaaS Security**: Data loss incidents from SaaS applications.
- **Endpoint**: Data loss incidents from endpoints (printers, network shares, etc.).
- **Email**: Data loss incidents from email. See image.

## Filtering

You can filter the data by time range and, for inline and endpoint data only, by incident status:

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **30 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **7 Days**.
- **Status**: For Inline and Endpoint data channels only, you can filter data to show only incidents that were **Allowed**or **Blocked**.

## Inline Dashboard Widgets

Click the **Inline** box at the top of the page to view the following widgets related to inline data protection:

- Top Sensitive Data Types
- Sensitive GenAI Applications
- Top High Risk Applications to Eliminate
- Top High Risk Applications to Secure
- Sensitive Files in Top 10 ML Categories

## SaaS Security Dashboard Widgets

Click the **SaaS Security** box at the top of the page to view the following widgets related to SaaS security data protection:

- SaaS Incidents
- Top Users with SaaS Incidents
- SaaS Applications with Data Exposure

## Endpoint Dashboard Widgets

Click the **Endpoint** box at the top of the page to view the following widgets related to endpoint data protection:

- Total Endpoint Incidents
- Top Users with Endpoint Incidents
- Top Endpoints with Sensitive Data Being Exfiltrated

## Email Dashboard Widgets

Click the **Email**box at the top of the page to view the following widgets related to email data protection:

- Top Domains Sensitive Data is Being Sent To
- Top Users with Email Incidents

This bar chart shows the types of sensitive inline data in your organization.

See image.

This ring chart shows information about generative AI applications in your organization. Click **Transactions**to view the number of generative AI transactions, or **Volume**to view the volume of data. Hover over the segments in the chart to view information for each application.

Click **View All Gen AI Activity** to open the [Gen AI Security Report](https://help.zscaler.com/zia/about-generative-ai-security-report).

See image.

This table shows the list of inline applications that have the biggest risk of data loss and the lowest usage by your users. These are applications you should consider eliminating from your organization.

- **Application Name**: The name of the high-risk application.
- **Risk**: The application's color-coded risk score, from riskiest (risk index 4, red) to least risky (risk index 1, green)
- **Users**: The number of users who used this application within the current time range.
- **Action**: The recommended action for this application.

See image.

This table shows the list of inline applications that have the biggest risk of data loss and the highest usage by your users. These are applications you should consider securing within your organization.

- **Application Name**: The name of the high-risk application.
- **Risk**: The application's color-coded risk score, from riskiest (risk index 4, red) to least risky (risk index 1, green)
- **Users**: The number of users who used this application within the current time range.
- **Action**: The recommended action for this application.

See image.

This widget shows information about files within various machine learning categories within your organization. Hover over a line in the chart to see the percentage that the files in the category represents as it relates to the total number of files.

Click **View All Data Discovery** to open the [Data Discovery Report](https://help.zscaler.com/zia/about-data-discovery-report), filtered for machine learning categories.

See image.

This bar chart shows the data loss incidents from SaaS applications in your organization. Hover over the chart to see more detail about the incidents:

- **Private**: Incidents that compromise personal information.
- **Internal**: Incidents that represent a security breach within your organization.
- **External**: Incidents that originated outside your organization.

See image.

This bar chart shows the users who experienced the most data loss incidents from SaaS applications. Hover over the chart to see more detail about the incidents for each user:

- **Private**: Incidents that compromise personal information.
- **Internal**: Incidents that represent a security breach within your organization.
- **External**: Incidents that originated outside your organization.

See image.

This table shows detail about the SaaS applications with the most data exposure in your organization.

- **Application Tenant Name**: The application responsible for a data loss incident.
- **Total Incidents**: The total number of data loss incidents from this application.
- **Private**: Incidents from this application that compromise personal information.
- **Internal**: Incidents from this application that represent a security breach within your organization.
- **External**: Incidents from this application that originated outside your organization.

See image.

This ring chart shows the data loss incidents from endpoints in your organization. Segments of the chart show the types of endpoints most commonly responsible for the incidents. Hover over the chart to see more detail about the incidents.

See image.

This bar chart shows the users who experienced the most data loss incidents from endpoints. Hover over the chart to see more detail about the endpoints responsible for the incidents for each user.

See image.

This bar chart shows the endpoints most responsible for sensitive data exposure in your organization. Click **DLP Engines** to categories based on DLP engines, or **ML Categories** to view based on machine learning categories.

Click the **Chart**icon to view data in a chart, or the **Graph**icon to view the data in a graph.

Click **View Endpoint Activity**to open the [Endpoin](https://help.zscaler.com/zia/about-endpoint-dlp-report)[t D](https://help.zscaler.com/zia/about-endpoint-dlp-report)[LP](https://help.zscaler.com/zia/about-endpoint-dlp-report)[Report](https://help.zscaler.com/zia/about-endpoint-dlp-report) to explore in more detail.

See image.

This bar chart shows the top email domains to which data has been exposed. Hover over the chart to see more detail about the type of sensitive data exposed.

Click the **Chart**icon to view data in a chart, or the **Graph**icon to view the data in a graph.

Click **View Email Security Activity**to open the [Email S](https://help.zscaler.com/zia/about-email-security-report)[ecurity](https://help.zscaler.com/zia/about-email-security-report)[Report](https://help.zscaler.com/zia/about-email-security-report) to explore in more detail.

See image.

This bar chart shows the users who experienced the most data loss incidents via email and the number of incidents.

See image.

[Image: Data Channels tabs on the Data Channels dashboard.]

[Image: Top Sensitive Data Types graph on the Data Channels dashboard.]

[Image: Sensitive GenAI Applications chart on the Data Channels dashboard.]

[Image: Top High Risk Applications to Eliminate chart on the Data Channels dashboard.]

[Image: Top High Risk Applications to Secure chart on the Data Channels dashboard.]

[Image: Sensitive Files in Top 10 ML Categories chart on the Data Channels dashboard.]

[Image: SaaS Incidents graph on the Data Channels dashboard.]

[Image: Top Users with SaaS Incidents graph on the Data Channels dashboard.]

[Image: SaaS Applications wtih Data Exposure on the Data Channels dashboard.]

[Image: Total Endpoint Incidents chart on the Data Channels dashboard.]

[Image: Top Users with Endpoint Incidents graph on the Data Channels dashboard.]

[Image: Top Endpoints with Sensitive Data Being Exfiltrated graph on the Data Channels dashboard.]

[Image: Top Domains Sensitive Data is Being Sent To chart on the Data Channels dashboard.]

[Image: Top Users with Email Incidents table on the Data Channels dashboard.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-data-security-dashboard","lastmod":"2026-07-02T07:52Z","nid":"1532636"} -->
## Viewing the Data Security Dashboard

- Source: https://help.zscaler.com/unified/viewing-data-security-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Data Security > Viewing the Data Security Dashboard
- Last modified: 2026-07-02T07:52Z
- Summary: Information on the Data Security dashboard and widgets accessible within the Admin Portal.

The Data Security dashboard provides information about incidents, users, channels, and data-at-rest in your organization that might indicate areas at risk of data loss or leakage.

## Filtering

Use the **Time Range** filter in the upper right to choose a specific time range between **1 Day** and **30 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **7 Days**.

## Dashboard Widgets

The following widgets are available:

- Total Incidents
- Users with Most Incidents
- Incidents by channel
- Top Sensitive Data at Rest

This donut chart shows the total number of data loss incidents that occurred in your organization. You can hover over segments of the chart to view data for each type of incident:

- **Inline**: Data loss incidents from inline (web) sources.
- **SaaS Security**: Data loss incidents from SaaS applications.
- **Endpoint**: Data loss incidents from endpoints (printers, network shares, etc.).
- **Email**: Data loss incidents from email.

See image.

This bar chart shows the users with the most data loss incidents. You can hover over segments of the chart to view data for each type of incident:

- **Inline**: Data loss incidents from inline (web) sources
- **SaaS Security**: Data loss incidents from SaaS applications
- **Endpoint**: Data loss incidents from endpoints (printers, network shares, etc.)
- **Email**: Data loss incidents from email.

See image.

This widget shows an overview of the data loss incidents for all channels. Click a channel to open a dashboard with more detail about incidents on that channel.

See image.

This widget shows the total amount of sensitive data at rest in your organization, along with each category of data such as social security information, HIPAA data, personal card information (PCI) data, and more. If more than 6 categories of data are available, you can click the arrows at the bottom to scroll through more categories. Click each bubble to view the total number of files containing sensitive data across public clouds, on-premises, or SaaS. Click the number to view additional file details on the [Data Inventory](https://help.zscaler.com/dspm/viewing-data-inventory) page.

- **Channels**: Click this filter to narrow the view to only data at rest in either **Public Clouds**,**On-Premises**, or**SaaS**.
- **AI Classification**: View only sensitive data that is exposed to AI models and services, such as financial or medical documents. This sensitive data is classified using DLP engines. Select the **Exact AI Type** checkbox to display the **Gen AI Category** filter to narrow the view to different specific types of generated AI data at rest. See image.
- **DLP Engines:**View data at rest detected by DLP engines, such as social security data, HIPAA data, personal card information (PCI) data, and more.

See image.

[Image: Top Sensitive Data At Rest widget with Exact AI type checkbox selected and list of GenAI categories available]

[Image: Total Incidents chart on the Data Security dashboard.]

[Image: Users with Most Incidents bar graph on the Data Security dashboard.]

[Image: All Channels widget on the Data Security dashboard.]

[Image: Top Sensitive Data At Rest widget with DLP Engines filter selected on the Data Security dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-devices-dashboard","lastmod":"2026-02-11T06:02Z","nid":"1518396"} -->
## Viewing the Devices Dashboard

- Source: https://help.zscaler.com/unified/viewing-devices-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Operational > Viewing the Devices Dashboard
- Last modified: 2026-02-11T06:02Z
- Summary: Information on the Devices dashboard and widgets accessible within the Admin Portal.

The Devices dashboard provides an overview of the devices in use in your organization. This allows you to see at a glance information such as models and operating systems in use, device enrollment state, operating system status, and more. To learn more about devices, see [About Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/about-enrolled-devices).

## Filtering

**Filter data**: Click the filters in the top-left corner to limit the data shown. Each filter allows you to include or exclude individual options.

- **Users**: The email addresses of individual users of devices in your organization.
- **State**: The state of the device (e.g., **Registered**, **Unregistered**, **Removal Pending**, **Removed**, or **Quarantined**).
- **Platform**: The software platform of the device (e.g., **iOS**, **Android**, **Windows**, **macOS**, or **Linux**).

After you choose your filters, click **Apply**to update the dashboard with your selections. To remove all applied filter selections, click **Reset**.

See image.

## Dashboard Widgets

- User Devices
- Version Distribution
- Device Operating System
- Device Model
- Device State

This bar graph shows the distribution of operating systems for all the devices in your organization.

See image.

This ring chart shows the number of devices in your organization that are using supported and unsupported versions of software.

See image.

This bar chart shows the operating systems in use in your organization.

See image.

This bar chart shows the device models in use in your organization.

See image.

This ring chart shows the enrollment state of devices in your organization.

- **Registered**: Devices enrolled in your organization.
- **Unregistered**:Devices that are logged out or have been removed by an admin.
- **Removal Pending**: Devices that have been soft-removed and are pending full removal.
- **Removed**: Devices that have been removed from the organization.
- **Quarantined**: Devices that are quarantined and can't be used to log in again.

To learn more about device enrollment states, see [Device States for Enrolled Devices](https://help.zscaler.com/zscaler-client-connector/device-states-enrolled-devices).

See image.

[Image: Applying filters on the Connectors dashboard]

[Image: Chart showing Traffic Volume Across Service widget on the Connector Activity dashboard]

[Image: Chart showing Session Across Service on the Connector Activity dashboard]

[Image: Chart showing Session Across Service on the Connector Activity dashboard]

[Image: Chart showing Session Across Service on the Connector Activity dashboard]

[Image: Chart showing Session Across Service on the Connector Activity dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-digital-experience-dashboard","lastmod":"2026-02-11T06:07Z","nid":"1497891"} -->
## Viewing the Digital Experience Dashboard

- Source: https://help.zscaler.com/unified/viewing-digital-experience-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Digital Experience > Viewing the Digital Experience Dashboard
- Last modified: 2026-02-11T06:07Z
- Summary: Information on the Digital Experience dashboard and widgets accessible within the Admin Portal.

The Digital Experience dashboard provides an overview of your organization's digital experience in terms of user, devices, applications, and unified communication experience, as well as overall network activity and latency.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between 2 hours and 48 hours in which to view data. Select **Custom** to select a specific time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments. The selected period applies to all data within the dashboard. The default time range is **2 Hours**.
- **Filter data**: Click the filters in the top left to limit the data shown. Each filter allows you to include or exclude individual options. Click **Apply** to update the dashboard with your selections. To remove all filter selections, click **Reset**.
  - **Departments**: Your departments. To learn more, see [About Departments](https://help.zscaler.com/unified/about-departments).
  - **Zscaler Locations**: Your locations. To learn more, see [About Locations](https://help.zscaler.com/unified/about-locations).
  - **User Groups**: The names of user groups in your organization.
  - **Geolocations**: The geographic areas where your users are located.

## Dashboard Widgets

- How is My Overall Digital Experience?
- Network Latency Geoview
- Application Experience
- Unified Communication Experience
- What is Impacting My User Experience?
- End Point Self Service

This bar graph indicates how many of the applications in your organization are in each of the Digital Experience score categories. Initially, the score represents all users in your organization, across all applications, all locations, and all cities. Depending on the time period and filters selected within the dashboards, the score adjusts accordingly.

The digital experience score categories are:

- **Good**: The score is above an acceptable threshold and ranges from 66-100. The color for this range is green.
- **Okay**: The score is acceptable and ranges from 34-65. The color for this range is amber.
- **Poor**: The score is below an acceptable threshold and ranges from 0-33. The color for this range is red.

Click **View Activity** to view more detail about the users, devices, and applications that contribute to the score on the [Activity Dashboard](https://help.zscaler.com/unified/viewing-activity-dashboard).

To learn more about the Digital Experience score (also called the ZDX Score), see [About the Digital Experience Score](https://help.zscaler.com/zdx/about-zdx-score).

See image.

This widget shows the average network latency from user locations to one of three locations on the network. You can toggle among:

- **Zscaler**: Latency to Zscaler data center
- **DNS Resolution**: Latency to the DNS server
- **Last Mile ISP**: Latency to the closest ISP node

Click a dot on the map to filter latency to that location. The dot colors correspond to the digital experience scores of good (green), okay (amber), and poor (red).

See image.

This table shows the performance of applications in your organization. The most commonly used applications are shown in the list. Click **View All Applications** to see a list of all applications on the [Applications Dashboard](https://help.zscaler.com/unified/viewing-applications-dashboard-0).

See image.

This widget shows the performance of meeting applications in your organization. The circle graph shows the overall Digital Experience score for all meetings. The most recent meetings are shown in the list. Click **View All Meetings** to see a list of all meetings on the [Meetings Dashboard](https://help.zscaler.com/unified/viewing-meetings-dashboard).

See image.

This widget shows the impact of incidents that have affected digital experience, including the total number of incidents, the number of users affected, and the devices, networks, and applications affected. The most recent incidents are shown in the list. Click the link in the Networking box to expand to show all areas in your network (Wi-Fi, DNS, ISP, etc.) Click **View All Incidents**to see a list of all incidents on the [Incidents Dashboard](https://help.zscaler.com/unified/viewing-incidents-dashboard).

See image.

This widget shows the total number of notifications sent, the number of unique users notified, and the types of notificaations sent to your users about issues requiring attention. Click **View Self Service** to see more information about user notifications on the [Self Service Dashboard](https://help.zscaler.com/unified/viewing-self-service-dashboard).

See image.

[Image: Bar graph showing How is My Digital Experience on the Digital Experience dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-email-data-security-dashboard","lastmod":"2026-02-11T06:15Z","nid":"1535052"} -->
## Viewing the Email Data Security Dashboard

- Source: https://help.zscaler.com/unified/viewing-email-data-security-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Data Security > Viewing the Email Data Security Dashboard
- Last modified: 2026-02-11T06:15Z
- Summary: Information on the Email data security dashboard and widgets accessible within the Admin Portal.

The Email data security dashboard provides detailed information about data loss incidents from email that affect your organization.

## Filtering

Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **30 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **7 Days**.

## Dashboard Widgets

The following widgets are available:

- Top Domains Sensitive Data is Being Sent To
- Top Users with Email Incidents

This bar chart shows the top email domains to which data has been exposed. Hover over the chart to see more detail about the type of sensitive data exposed.

Click the **Chart**icon to view data in a chart, or the **Graph**icon to view the data in a graph.

Click **View Email Security Activity**to open the [Email Security](https://help.zscaler.com/zia/about-email-security-report)[Report](https://help.zscaler.com/zia/about-email-security-report) to explore in more detail.

See image.

This bar chart shows the users who experienced the most data loss incidents via email and the number of incidents.

See image.

[Image: Top Domains Sensitive Data is Being Sent To chart on the Email data security dashboard.]

[Image: Top Users with Email Incidents table on the Email data security dashboard.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-endpoint-data-security-dashboard","lastmod":"2026-02-11T06:19Z","nid":"1535051"} -->
## Viewing the Endpoint Data Security Dashboard

- Source: https://help.zscaler.com/unified/viewing-endpoint-data-security-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Data Security > Viewing the Endpoint Data Security Dashboard
- Last modified: 2026-02-11T06:19Z
- Summary: Information on the Endpoint data security dashboard and widgets accessible within the Admin Portal.

The Endpoint data security dashboard provides detailed information about data loss incidents from endpoints (e.g., printers, network shares, etc.) in your organization.

## Filtering

You can filter the data by time range and incident status:

- **Time Range**: Use the**Time Range** filter in the upper right to choose a specific time range between **1 Day** and **30 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **7 Days**.
- **Status**: Use the **Status**filter to show only incidents that were **Allowed**or **Blocked**.

## Dashboard Widgets

The following widgets are available:

- Total Endpoint Incidents
- Top Users with Endpoint Incidents
- Top Endpoints with Sensitive Data Being Exfiltrated

This ring chart shows the data loss incidents from endpoints in your organization. Segments of the chart show the types of endpoints most commonly responsible for the incidents. Hover over the chart to see more detail about the incidents.

See image.

This bar chart shows the users who experienced the most data loss incidents from endpoints. Hover over the chart to see more detail about the endpoints responsible for the incidents for each user.

See image.

This bar chart shows the endpoints most responsible for sensitive data exposure in your organization. Click **DLP Engines** to categories based on DLP engines, or **ML Categories** to view based on machine learning categories.

Click the **Chart**icon to view data in a chart, or the **Graph**icon to view the data in a graph.

Click **View Endpoint Activity**to open the [Endpoin](https://help.zscaler.com/zia/about-endpoint-dlp-report)[t D](https://help.zscaler.com/zia/about-endpoint-dlp-report)[LP](https://help.zscaler.com/zia/about-endpoint-dlp-report)[Report](https://help.zscaler.com/zia/about-endpoint-dlp-report) to explore in more detail.

See image.

[Image: Total Endpoint Incidents chart on the Endpoint data security dashboard.]

[Image: Top Users with Endpoint Incidents graph on the Endpoint data security dashboard.]

[Image: Top Endpoints with Sensitive Data Being Exfiltrated graph on the Endpoint data security dashboard.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-experience-center-home-page","lastmod":"2026-02-12T09:28Z","nid":"1529129"} -->
## Viewing the Experience Center Home Page

- Source: https://help.zscaler.com/unified/viewing-experience-center-home-page
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Experience Center Set Up, Onboarding, & Access > Zscaler Admin Console Access & Navigation > Viewing the Experience Center Home Page
- Last modified: 2026-02-12T09:28Z
- Summary: How to view and customize the Experience Center home page within the Zscaler Admin Console.

On the Zscaler Experience Center home page is a customizable start page where you can select from a variety of information cards and customize the view to your own preferences and work requirements.

You can view dashboards of analytics information, see a list of recently-visited pages, browse security-related news, discover information about Zscaler products and services, and quickly access key resources, such as technical documentation and product tours.

The home page appears when you log into the Zscaler Admin Console. You can add, delete, rearrange and resize the cards on the page to best suit your working requirements and preferences.

See image.

## Customizing the Home Page

Click **Customize**in the lower-right corner to begin editing the page, where you can do one or more of the following customizations:

- **Add a card**: While in customize mode, hover over a space between existing cards and click the **Add card** (+) icon. In the resulting pop-up window, select the type of card you want to add. When done, click **Save**in the upper-right corner. See image.
- **Move a card**: While in customize mode, grab a card and drag it where you want to move it. When done, click **Save**in the upper-right corner. See image.
- **Resize a card**: While in customize mode, hover over a card and click the resize icon to toggle between a half- and a full-size card. When done, click **Save**in the upper-right corner. See image.
- **Delete a card**: While in customize mode, hover over a card and click the delete icon to remove it. When done, click **Save**in the upper-right corner. See image.

## Viewing Analytics Cards

Analytics cards contain dashboards showing some key analytics from [unified dashboards](https://help.zscaler.com/unified/analytics/unified-dashboards). You can add one or more analytics cards to the home page.

See image.

The available analytics cards are:

- **Networking**: Shows the network traffic information graphs from the [Networking dashboard](https://help.zscaler.com/unified/viewing-networking-dashboard).
- **Digital Experience**: Shows the overall digital experience graph from the [Digital Experience dashboard](https://help.zscaler.com/unified/viewing-digital-experience-dashboard).
- **Cybersecurity**: Shows the cybersecurity transactions graph from the [Cybersecurity dashboard](https://help.zscaler.com/unified/viewing-cybersecurity-dashboard).
- **Devices**: Shows the user devices graph from the [Devices dashboard](https://help.zscaler.com/unified/viewing-devices-dashboard).
- **Analytics**: Shows the deployment and active status charts from the [Appliances dashboard](https://help.zscaler.com/unified/viewing-appliances-dashboard).

To display the complete dashboard for any analytics card, click **Go to Full View** in the lower-right corner of the card.

## Viewing the Recently Viewed Card

The Recently Viewed card contains a list of the pages that you navigated to most recently.

Click **Load More** in the lower-right corner of the card to expand the list to show more pages you have visited recently.

See image.

## Viewing the News Card

The News card contains a list of news articles, curated by Zscaler, that are internet security-related topics of interest.

- Click a news category name (shown above the articles) to filter articles by that category.
- Click any article to open it in the Zscaler Admin Console.
- Click **View more** in the lower-right corner of the card to open the [Zscaler Blog](https://www.zscaler.com/blogs) to view other articles of interest.

See image.

## Viewing the Learn About Products Card

The Learn About Products card contains videos about Zscaler products and services.

- Click a product category name (shown above the videos) to filter videos by that category.
- Click a video to watch it in a pop-up window.
- Click the left and right arrow icons (**<** and **>**) to scroll through additional videos.

See image.

## Viewing the Resources Card

The Resources card contains links to Zscaler technical documentation, product tours, and a form where you can provide feedback.

See image.

[Image: Typical Experience Center home page]

[Image: Typical Experience Center home page]

[Image: Typical Experience Center home page]

[Image: Typical Experience Center home page]

[Image: Typical Experience Center home page]

[Image: Typical analytics card on the home page]

[Image: Recently Viewed card on the home page]

[Image: News card on the home page]

[Image: Products card on the home page]

[Image: Resources card on the home page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-financial-risk","lastmod":"2026-02-16T21:53Z","nid":"1526666"} -->
## Viewing Financial Risk

- Source: https://help.zscaler.com/unified/viewing-financial-risk
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Financial Risk > Viewing Financial Risk
- Last modified: 2026-02-16T21:53Z
- Summary: Information on the financial factors affecting your organization financially in the Admin Portal.

The Financial Risk page (Analytics > Risk360 > Financial Risk) gives visibility into your organization's potential financial loss exposure for your current risk score. You can also analyze the factors affecting financial loss exposure and view the loss curve over time to take appropriate actions to mitigate your organization's financial risks.

## Filtering

1. **Settings**: You can manage the financial risk calculation parameters. To learn more, see [Managing Financial Risk Settings](https://help.zscaler.com/risk360/managing-financial-risk-settings).
2. Download the complete list of factors affecting the financial risk to a CSV file.

## Financial Risk Page Widgets

- Financial Summary
- Loss Curve
- Top 10 Financial Contributing Factors

View our organization's risk score-based financial exposure and the exposure without Zscaler. The section also provides an estimate of how much of this financial risk exposure can be reduced by implementing the recommended actions from the Top 10 Financial Contributing Factors section.

See image.

The graph shows the loss estimate change over the last 6 months.

See image.

View the top 10 factors contributing to your organization's financial risk exposure. For each factor, you can see:

- **Financial Exposure**: The financial loss estimate is in US dollars.
- **Factor Name**: The name of the contributing factor affecting the risk exposure.
- **Category**: The category the factor falls under.
- **Your Score**: The total score assigned to the factor and your score based on the configuration (0 being a healthy score). The total score for a factor depends on its severity.
- **Licensed?**: Whether you are subscribed to the required feature to implement the recommended action (**Y** for Yes and **N** for No).
- **Recommended Actions**: The recommended action that needs implementation to reduce your financial risk exposure.

Click on a financial contributing factor; you are redirected to the [Factors](https://help.zscaler.com/risk360/about-factors) page where you can see all the factors and analyze them.

See image.

[Image: Financial Summary Section]

[Image: Loss Curve Section]

[Image: Top 10 Financial Contributing Factors Section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-incidents-dashboard","lastmod":"2026-02-11T06:09Z","nid":"1498766"} -->
## Viewing the Incidents Dashboard

- Source: https://help.zscaler.com/unified/viewing-incidents-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Digital Experience > Viewing the Incidents Dashboard
- Last modified: 2026-02-11T06:09Z
- Summary: Information on the Incidents dashboard and widgets accessible within the Admin Portal.

The Incidents dashboard provides an overview of incidents affecting your organization.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between 2 hours and 48 hours in which to view data. Select **Custom** to select a specific time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments. The selected period applies to all data within the dashboard. The default time range is **2 Hours**.
- **Type**: Filter incidents by type. Available types are **Device**, **Wi-Fi**, **DNS**, **Last Mile ISP**, **Intermediate ISP**, **Zscaler**, and **Application**.

## Dashboard Widgets

- Total Incidents/Incidents Across Key Areas
- Incidents Over Time/Impacted Users Over Time
- Incidents by Epicenters
- Incidents

You can view the total incidents and the total counts across the key metrics and impacted users.

- **Total Incidents**: The total number of incidents detected within a time range.
- **Impacted Users**: The number of impacted users from the total incidents within a time range.
- **Incidents Across Key Areas**: The distribution of incidents in critical parts of the infrastructure. Click the number, icon, or text under each key area to filter specifically for the selected incident type.

See image.

This widget contains bar charts showing the number of incidents for the specified time range and type and the number of impacted users from the incidents for the specified time range and type. If there are no impacted users, no data is shown.

See image.

This map displays the incidents that have occurred for the specified time range and type. Incidents are displayed using icons to represent the types of incident. After an incident is positioned on the map in an area, an epicenter is defined at the center of the incident. Click an icon to view more information.

See image.

This table provides a detailed list of incidents for the specified time range and type.

- **Incident Type**: The type of incident.
- **Epicenter**: Represents the center of the incident depending on the type.
- **Users Impacted**: The number of users impacted by the incident.
- **Start Time**: The date and time the incident started.
- **End Time**: The date and time the incident ended.
- **Duration**: The duration of the incident.

See image.

Click any incident row in the table to see additional detail about that incident:

- Incident Details/Impact
- Impacted Users by Geolocations
- Top Impacted Users
- Key Metrics

Provides an overview of the selected incident with the following details:

- **Type**: The type of incident.
- **Severity**: The level of severity of the incident.
- **Epicenter**: Represents the center of the incident depending on the type.
- **Started On**: The date and time the incident started.
- **Ended On**: The date and time the incident ended.
- **Duration**: The duration of the incident.

The Impact section shows the number of users, geolocations, and applications impacted.

See image.

A map showing the geolocations of impacted users. You can zoom in and out of the map to better view regions of interest.

See image.

A list of the Top Impacted Users. Click **View All Impacted Users** to view and verify more impacted users.

The Top Impacted Users list displays up to 11 impacted users.

See image.

Displays key metrics based on the Incident type.

- Wi-Fi
- Last Mile ISP
- Internet & SaaS Public Service Edge
- Private Applications Public Service Edge
- Application

See image.

Application Key Metrics provide an overview of an application and its impacted users.

- **ZDX Score**: The Digital Experience score (also called the ZDX Score) of the application.
- **HTTP Errors**: The number of HTTP errors the application has encountered over time.
- **TTFB-PFT Ratio**: The ratio between the server response time (time to first byte) and the time to load the page (page fetch time).
- **Last Server Leg Latency**: The latency from the Public Service Edge to the application.
- **Number of Redirects**: The number of redirections going through the application over time.

Depending on if it's a Blackout or Brownout, you get different key metrics.

- Blackout
- Brownout

Wi-Fi key metrics provide an overview of the signal strength and latency of Wi-Fi access points.

- **ZDX Score Drop**: The Digital Experience score variation through the Wi-Fi access point.
- **Maximum Wi-Fi Access Point Latency**: The maximum Wi-Fi Access Point Latency.
- **Average Wi-Fi Access Point Latency**: The average Wi-Fi Access Point Latency.
- **Packet Loss at Wi-Fi Access Point (Hop 1)**: The packet loss at the first Wi-Fi Access Point.
- **Packet Loss after Wi-Fi Access Point (Hop 2)**: The packet loss after the 1st Wi-Fi Access Point.
- **Packet Loss after Wi-Fi Access Point (Hop 3)**: The packet loss after the 2nd Wi-Fi Access Point.
- **Packet Loss after Wi-Fi Access Point (Hop 4)**: The packet loss after the 3rd Wi-Fi Access Point.
- **Wi-Fi Signal Strength**: The average signal strength of users going through the Wi-Fi Access Point.

Internet and SaaS Public Service Edge key metrics provide an overview on ZIA transactions, connectivity, and latency for impacted users.

- **ZDX Score for Impacted Users**: The Digital Experience score for Impacted Users is calculated across each impacted user and their aggregated score from all their configured applications.
- **First Hop Latency around PSE**: The time calculated, from the Public Service Edge (PSE) perspective, across all the users going through the affected Zscaler Data Center.
- **Second Hop Latency around PSE**: The average Second Hop Latency, from the PSE perspective, across all the users going through the affected Zscaler Data Center.
- **ZIA Transactions**: Displays the number of Zscaler Internet Access (ZIA) transactions going through the affected Zscaler Data Center.
- **ZIA Connectivity Errors**: Displays the number of errors connecting to the affected Zscaler Data Center.
- **Cloud Path Probe Errors**: The number of Cloud Path probe errors at the Zscaler Data Center.
- **Web Probe Errors**: The number of Web probe errors at the Zscaler Data Center.

Private Applications Public Service Edge key metrics provide an overview on ZPA traffic, connectivity, and latency for impacted users.

- **ZDX Score**: The Digital Experience score (also called the ZDX Score) is calculated across each impacted user and their aggregated score from all their configured applications.
- **First Hop Latency around ZPA**: The average First Hop Latency, from the Public Service Edge perspective, across all the users going through Private Applications.
- **Second Hop Latency around ZPA**: The average Second Hop Latency, from the Public Service Edge perspective, across all the users going through Private Applications.
- **Private Applications Public Service Edge Cloud Path Probe Errors**: The number of Public Service Edge errors at the Zscaler Data Center.
- **Private Applications Traffic for Current Week**: The number of Private Applications transactions that create Private Applications traffic for the current week.
- **Private Applications Traffic for Previous Week**: The number of Private Applications transactions that create Private Applications traffic for the previous week.
- **Private Applications Traffic for 2 Weeks Ago**: The number of Private Applications transactions that create Private Applications traffic for 2 weeks before the current week, excluding the current week.
- **Private Applications Traffic for 3 Weeks Ago**: The number of Private Applications transactions that create Private Applications traffic for 3 weeks before the current week, excluding the current week.
- **Private Applications Traffic for 4 Weeks Ago**: The number of Private Applications transactions that create Private Applications traffic for 4 weeks before the current week, excluding the current week.
- **Private Applications Public Service Edge Web Probe Errors**: The number of Private Applications Public Service Edge Web probe errors at the Zscaler Data Center.

Blackouts Key Metrics provides an overview of connectivity issues with an ISP.

- **Application Score**: The Digital Experience score of the application.
- **All Probe Errors**: The total number of probe errors.
- **DNS Resolution Time**: The amount of time it takes for a DNS to resolve DNS entries.
- **Cloud Path Probe Errors**: The number of Cloud Path probe errors.

Brownouts Key Metrics provides an overview of performance degradation with an ISP.

- **ZDX Score Drop**: The shaded region indicates the Digital Experience score drop for the Last Mile ISP incident.
- **DNS Latency**: The amount of time DNS takes to resolve for impacted users from the Last Mile ISP incident.
- **Leg Latency**: The latency for impacted legs from the Last Mile ISP incident.
- **Packet Loss**: The number of packets lost from the Last Mile ISP incident.

The following is an example of a selected Wi-Fi Incident. Depending on the type of incident you have, different key metrics will be shown.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-inline-data-security-dashboard","lastmod":"2026-02-11T06:18Z","nid":"1535049"} -->
## Viewing the Inline Data Security Dashboard

- Source: https://help.zscaler.com/unified/viewing-inline-data-security-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Data Security > Viewing the Inline Data Security Dashboard
- Last modified: 2026-02-11T06:18Z
- Summary: Information on the Inline data security dashboard and widgets accessible within the Admin Portal.

The Inline data security dashboard provides detailed information about data loss incidents from inline (web) sources in your organization.

## Filtering

You can filter the data by time range and incident status:

- **Time Range**: Use the **Time Range** filter in the upper right to choose a specific time range between **1 Day** and **30 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **7 Days**.
- **Status**: Use the **Status**filter to show only incidents that were **Allowed**or **Blocked**.

## Dashboard Widgets

The following widgets are available:

- Top Sensitive Data Types
- Sensitive GenAI Applications
- Top High Risk Applications to Eliminate
- Top High Risk Applications to Secure
- Sensitive Files in Top 10 ML Categories

This bar chart shows the types of sensitive inline data in your organization.

See image.

This ring chart shows information about generative AI applications in your organization. Click **Transactions**to view the number of generative AI transactions, or **Volume**to view the volume of data. Hover over the segments in the chart to view information for each application.

Click **View All Gen AI Activity** to open the [Gen AI Security Report](https://help.zscaler.com/zia/about-generative-ai-security-report).

See image.

This table shows the list of inline applications that have the biggest risk of data loss and the lowest usage by your users. These are applications you should consider eliminating from your organization.

- **Application Name**: The name of the high-risk application.
- **Risk**: The application's color-coded risk score, from riskiest (risk index 4, red) to least risky (risk index 1, green)
- **Users**: The number of users who used this application within the current time range.
- **Action**: The recommended action for this application.

See image.

This table shows the list of inline applications that have the biggest risk of data loss and the highest usage by your users. These are applications you should consider securing within your organization.

- **Application Name**: The name of the high-risk application.
- **Risk**: The application's color-coded risk score, from riskiest (risk index 4, red) to least risky (risk index 1, green)
- **Users**: The number of users who used this application within the current time range.
- **Action**: The recommended action for this application.

See image.

This widget shows information about files within various machine learning categories within your organization. Hover over a line in the chart to see the percentage that the files in the category represents as it relates to the total number of files.

Click **View All Data Discovery** to open the [Data Discovery Report](https://help.zscaler.com/zia/about-data-discovery-report), filtered for machine learning categories.

See image.

[Image: Top Sensitive Data Types graph on the Inline data security dashboard.]

[Image: Sensitive GenAI Applications chart on the Inline data security dashboard.]

[Image: Top High Risk Applications to Eliminate chart on the Inline data security dashboard.]

[Image: Top High Risk Applications to Secure chart on the Inline data security dashboard.]

[Image: Sensitive Files in Top 10 ML Categories chart on the Inline data security dashboard.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-meetings-dashboard","lastmod":"2026-02-11T06:11Z","nid":"1498756"} -->
## Viewing the Meetings Dashboard

- Source: https://help.zscaler.com/unified/viewing-meetings-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Digital Experience > Viewing the Meetings Dashboard
- Last modified: 2026-02-11T06:11Z
- Summary: Information on the Meetings dashboard and widgets accessible within the Admin Portal.

The Meetings dashboard provides an overview of the performance of meetings and videoconferencing applications in your organization.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between 2 hours and 48 hours in which to view data. Select **Custom** to select a specific time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments. The selected period applies to all data within the dashboard. The default time range is **2 Hours**.
- **Filter data**: Click the filters in the top left to limit the data shown. Each filter allows you to include or exclude individual options. Click **Apply**to update the dashboard with your selections. To remove all filter selections, click **Reset**.
  - **Applications**: The meeting applications in your organization.
  - **Departments**: Your departments. To learn more, see [About Departments](https://help.zscaler.com/zia/about-departments).
  - **Zscaler Locations**: Your locations. To learn more, see [About Locations](https://help.zscaler.com/zia/about-locations).
  - **User Groups**: The names of user groups in your organization.
  - **Geolocations**: The geographic areas where your users are located.
  - **Location Groups**: The names of groups based on location in your organization.
  - **Last Mile ISPs**: The Internet Service Providers (ISPs) to which your users are connecting.
  - **Operating System**: The operating systems used in your organization,

## Meetings

This table shows all the meetings that occurred in your organization for the selected time range and filters:

- **Meeting ID**: The unique identifier of the meeting.
- **Application Name**: The name of the meeting application used for this meeting.
- **MOS Score**: The Mean Opinion Score of the meeting, on a scale of 1 (worst) to 5 (best). MOS is calculated based on metrics such as latency, jitter, and packet loss.
- **Host**: The user who hosted the meeting.
- **Start Time**: The beginning time of the meeting.
- **Active Participants**: The number of participants as reflected in the session data. Participants who join a call from multiple devices or who leave and rejoin a call are counted per session.

See image.

Click any meeting row to see additional details about that meeting:

- **Meeting Details**: Includes the unique identifier of the meeting, the host, number of participants, start and end times, and the ZDX Score, which is derived from the MOS Score.
  - If the MOS is 0 to 3.6 the ZDX Score is **Poor**.
  - If the MOS is greater than 3.6 but less than 4.34, the ZDX Score is **Okay**.
  - If the MOS is 4.34 or greater, the ZDX Score is **Good**.
- **Participants**: The list of users, the Session ID (this is the internal meeting session ID), the user's start and end times, their device OS, IP Address, Geolocation, and individual MOS and ZDX scores. Some information may not be available for all participants (such as if the user does not have Digital Experience enabled).

See image.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-networking-dashboard","lastmod":"2026-03-13T07:41Z","nid":"1497291"} -->
## Viewing the Networking Dashboard

- Source: https://help.zscaler.com/unified/viewing-networking-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Networking > Viewing the Networking Dashboard
- Last modified: 2026-03-13T07:41Z
- Summary: Information on the Networking dashboard and widgets accessible within the Admin Portal.

The Networking dashboard provides information about traffic, data centers, and devices in your organization.

## Filtering

**Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **90 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **14 Days**.

The following widgets are available:

- Traffic in my Organization
- Internet Traffic Distribution
- Top 10 Locations Sending Traffic to Zscaler
- Data Centers, App Connectors, and Cloud & Branch Connectors
- User Devices and Device OS Distribution
- Devices Discovered

This widget shows three line graphs:

- Number of transactions and percentage change for the selected time period
- Overall traffic volume and percentage change for the selected time period
- Traffic volume in your network, for **Road Warrior**, or totally remote users (indicated by the blue line) and **In-Office** users (indicated by the green line).

Click **Internet** or **Private**to toggle between viewing internet or private application traffic.

See image.

This circle graph shows the total traffic volume and a circle graph that shows the distribution of traffic between road warriors and in-office users in your organization.

See image.

This bar chart shows the top locations that are sending traffic through Zscaler.

See image.

This widget shows a map indicating the locations where users are accessing Zscaler services. Click the **Data Centers**,**App Connector**s, and **Cloud & Branch Connectors** tabs to toggle between maps that display nformation about each of the types of services.

- On the **Data Centers** tab, the relative size of the dot indicates how much traffic is flowing through the location. Click a location to see the total volume and total transactions at that location.
- On the **App Connectors** tab, the relative size of the dot indicates how many connectors are in use at that location. Click a location to view more information on the connect.
- On the **Cloud & Branch Connectors** tab, the relative size of the dot indicates how many connectors are in use at that location. Click a location to see the type of connector, its health status, location, and traffic. Check the boxes to view only Cloud Connectors or Branch Connectors. Click **View Connector Activity** to open the [Connector Activity Dashboard](https://help.zscaler.com/unified/viewing-connector-activity-dashboard).

See image.

This widget shows the total number of devices that have been discovered in your organization and the percentage time for the selected time period. A bar graph shows the distribution of operating systems for all the devices in your organization.

See image.

This widget shows the breakdown of devices, such as smartphones, computers, printers and peripherals, and IOT devices, that have been discovered in your organization in the most recent 24 hours.

See image.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-overrides","lastmod":"2026-04-03T21:06Z","nid":"1508506"} -->
## Viewing Overrides

- Source: https://help.zscaler.com/unified/viewing-overrides
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Policies > Adaptive Access Engine > Viewing Overrides
- Last modified: 2026-04-03T21:06Z
- Summary: Information on overrides related to the Adaptive Access Engine.

Overrides are time-bound changes set by admins to a subject’s (user or device) context signal value that temporarily replace the signal value from the context source, such as Okta, CrowdStrike, etc., while retaining the original value for reference and auditing.

Overrides are required to rectify inaccurate signal values from the context source or make urgent exceptions, so policy decisions are not blocked by these inaccurate signals. Overrides take effect immediately across the Adaptive Access Engine-driven evaluations (e.g., Internet & SaaS or Private Access), so the next access attempt reflects the change.

Overrides help maintain control and accountability via mandatory expiration and audit logging. They preserve policy integrity by only allowing context-signal overrides and not direct profile overrides.

To view the overrides:

1. Go to **Policies** > **Common Configuration** > **Adaptive Access** > **Overrides**.
2. On the **Overrides** page, you can view the following: See image.
  - **Subject Identifier**: The login ID of the user or device.
  - **Subject Type**: The type of subject (user or device).
  - **Context Count**: The number of context signals received.
  - **Override Count**: The number of overrides.

[Image: View the override details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-risk-factors","lastmod":"2026-02-16T21:51Z","nid":"1526551"} -->
## Viewing Risk Factors

- Source: https://help.zscaler.com/unified/viewing-risk-factors
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Viewing Risk Factors
- Last modified: 2026-02-16T21:51Z
- Summary: Information about the factors affecting your risk score in the Admin Portal.

The Factors page (Analytics > Risk360 > Factors) shows the list of contributing factors that are affecting your organization's risk score. The Risk360 service quantifies each factor according to its risk weight, which then adds to your overall organization risk score and also maps these factors to various renowned risk and security frameworks like MITRE, NIST, etc.

## Contributing Factors to Organizational Risk Score

From the top right of the page, you can select how you want to view the page. Based on your selection, the page updates to the following views:

- Attack-Based
- Entity-Based

The attack view shows the factors in a single list.

In this view, you can:

1. Export the factors contributing to your organization's risk score into a CSV file. The downloaded file shows all the factors contributing to your organization's risk score, irrespective of any filter selected at the time of the download.
2. Switch to the entity-based view (**Tree View**).
3. View the category-based risk score for your organization.
4. Search for a factor.
5. View a list of all the contributing factors to your organization's risk score. For each factor, you can see:
  - **Factor Name**: The name of the contributing factor affecting the risk score.
  - **Category**: The category the factor falls under.
  - **Your Score**: The score for the contributing factor. The total score for a factor depends on its severity (0 being a healthy score).
  - **Last 30 Days**: The graph showing the last 30-day score trend for that factor.
  - **Entities**: The entities affected by the factor.
  - **Licensed?**: Whether you are subscribed to the required feature to implement the recommended action (**Y** for Yes and **N** for No).
  - **Recommended Actions**: The recommended action proposed to attain a healthy risk score. Click on any of the columns (except Licensed?, Include, and Entities) to view more information about the factor in the drawer view:
    - Drawer
6. Disable this option to remove the factor from risk score computation by providing an explanation. By default, this option is enabled for all factors. You can exclude a factor if you have a compensating control over the factor or any other reason. Compensating controls are supplementary security measures that are implemented to protect against identified risks or threats (e.g., multi-factor authentication, firewalls, antivirus software). The changes made by the admin are captured in the [audit logs](https://help.zscaler.com/unified/about-risk360-audit-logs) with the username and reason provided for the change. See image.

[Image: Contributing Factors to Organizational Risk Score]

The drawer consists of the following tabs:

### Details

On the Details tab, you can view the following information:

- The name of the factor.
- **Severity**: The severity of the factor.
- **Recommended Actions**: The recommended action proposed to attain a healthy risk score.
- **Description**: Detailed information about the factor, useful help article links, and a link that redirects you to the Zscaler service's admin portal that is responsible for the factor, if available.
- **Related Recommendation Cards**: The related discovered problems and their recommendations.

Each problem or factor affecting your organization's risk is visualized in the form of Cards on the Insights Page.

[Image: The Drawer view for the factors in the Risk360 Portal]

### Compliance

On the Compliance tab, you can view a list of recognized cybersecurity frameworks and their control IDs mapped to the factor. Click on the control ID; you're redirected to the framework's website where the control IDs are explained in detail.

[Image: Compliance tab in the Factors tab]

The entity-based view shows the factors listed within their category.

In this view, you can:

1. Export the factors contributing to your organization's risk score into a CSV file. The downloaded file shows all the factors contributing to your organization's risk score, irrespective of any filter selected at the time of the download.
2. Switch to the attack-based view (**List View**).
3. Filter factors for specific entities by clicking the entity tiles. To deselect an entity, click on it again.
4. View the contributing factors to your organization's risk score. For each factor, you can see:
  - **Factor Name**: The name of the contributing factor affecting the risk score.
  - **Recommended Actions**: The recommended action proposed to attain a healthy risk score.
  - **Licensed?**: Whether you are subscribed to the required feature to implement the recommended action (**Y** for Yes and **N** for No).
  - **Your Score**: The score for the contributing factor. The total score for a factor depends on its severity (0 being a healthy score).
  - **Last 30 Days**: The graph shows the last 30-day score trend for the factor.
  - **Include**: Whether the factor is enabled for risk score computation. Click on any of the columns (except Licensed? and Include) to view more information about the factor in the drawer view:
    - Drawer
5. Disable this option to remove the factor from risk score computation by providing an explanation. By default, this option is enabled for all factors. You can exclude a factor if you have a compensating control over the factor or any other reason. Compensating controls are supplementary security measures that are implemented to protect against identified risks or threats (e.g., multi-factor authentication, firewalls, antivirus software). The changes made by the admin are captured in the [audit logs](https://help.zscaler.com/unified/about-risk360-audit-logs) with the username and reason provided for the change. See image.
6. Show or hide the factors for a specific category.

The drawer consists of the following tabs:

### Details

On the Details tab, you can view the following information:

- The name of the factor.
- **Severity**: The severity of the factor.
- **Recommended Actions**: The recommended action proposed to attain a healthy risk score.
- **Description**: Detailed information about the factor, useful help article links, and a link that redirects you to the Zscaler service's admin portal that is responsible for the factor, if available.
- **Related Recommendation Cards**: The related discovered problems and their recommendations.

[Image: The Drawer view for the factors in the Risk360 Portal]

### Compliance

On the Compliance tab, you can view a list of recognized cybersecurity frameworks and their control IDs mapped to the factor. Click on the control ID; you're redirected to the framework's website where the control IDs are explained in detail.

[Image: Compliance tab in the drawer]

[Image: Factor Override Note WIndow]

[Image: Factor Override Note WIndow]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-risk-insights","lastmod":"2026-02-11T06:35Z","nid":"1526911"} -->
## Viewing Risk Insights

- Source: https://help.zscaler.com/unified/viewing-risk-insights
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Viewing Risk Insights
- Last modified: 2026-02-11T06:35Z
- Summary: Information on Risk360 Insights, the different types of problems causing higher risk scores and further analyze them with interactive widgets.

The Insights page (Analytics > Risk360 > Insights) gives visibility into your organization's current risks and vulnerabilities. The page keeps updating in real time with the latest risks and recommendations discovered within your Zscaler environment.

## Viewing Insights

- View the category-based risk score for your organization at the top of the page.
- View a list of all problems discovered by the Risk360 service. For each problem, you can see the following information:
  - The problem title, its category, and the day it was generated.
  - **Problem**: The statement explaining the problem.
  - **Recommendation**: The recommendation to remediate the problem.
  - **Trend**: The section shows the problem trend (if applicable).
- Analyze the problem further using the Explore link. To learn more, see [Exploring Risk Problems](https://help.zscaler.com/unified/exploring-risk-problems).

See image.

[Image: Inisghts page showing problem cards]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-risk360-dashboard","lastmod":"2026-02-11T20:49Z","nid":"1526546"} -->
## Viewing the Risk360 Dashboard

- Source: https://help.zscaler.com/unified/viewing-risk360-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Risk360 > Viewing the Risk360 Dashboard
- Last modified: 2026-02-11T20:49Z
- Summary: Information on the Risk360 dashboard and widgets accessible within the Admin Portal.

The Risk360 Dashboard (Analytics > Risk360) gives visibility and insight into your organization's risk score, contributed by various underlying factors such as exposed servers, recent malware outbreaks, segmentation posture, and data uploads to risky applications. Zscaler's architecture quantifies these events across 4 major categories, such as exposure of attack surfaces, asset compromise, lateral propagation, and sensitive data loss. You can study how your organization's risk score has changed over time and compare your score against industry peers. Different risk factors bear different weights on the score. For example, an active infection is more severe than a blocked access attempt to a blocked destination.

## Dashboard Widgets

- Organization Risk Score
- Risk Score Trend
- Risk Event by Location
- Contributing Factors by Entity
- Top 10 Factors
- High Impact Recommendations

The section shows the Zscaler-computed risk score for your organization and the industry peer average risk score. Your organization's risk score is an average risk score across 4 categories, i.e., External Attack Surface, Compromise, Lateral Propagation, and Data Loss. You can study how the risk score has changed over time and compare your score against your industry peers. The following severities appear for the score ranges:

- Low (0–25)
- Medium (26–50)
- High (51–75)
- Critical (76–100)

Hover over the dollar symbol to view the financial risk estimates. You can click **View Details**to further analyze it; you are redirected to the [Financial Risk](https://help.zscaler.com/unified/viewing-financial-risk) page.

See image.

The graph shows the Zscaler-computed and industry peer average risk score trend for the last 90 days. Hover over a point in the graph to view the risk score for that date. You can select to view the risk trend for a specific risk score type by using the checkboxes at the bottom of the graph.

See image.

Click **Key Events** to view significant events that contributed to your risk score in an enlarged view.

- Enlarged View

The map shows a category-based number of risky events from geolocation coordinates derived by looking at the client or server IP. As geo-IP lookups are only possible for a subset of overall risky events, the location visualizations represent a small fraction of overall risky events, but the map view allows you to visualize the geospatial distribution of risky events wherever possible. Hover over a location to view the number of risky events across each category. Use the mouse to drag within the maps or zoom in (+) and out (-). The bottom left of the section shows the top risky locations with the percentage of risky events in descending order.

See image.

This section shows the total number of contributing factors from each entity. The circle chart shows the segregation across each category affected by these factors:

- **Workforce**: The factors contributing to the risk score due to risky user activity.
- **3rd Parties**: The factors contributing to the risk score due to activities by 3rd-party users (e.g., contract workers).
- **Applications**: The factors contributing to the risk score due to the usage of unsanctioned or less secure SaaS applications.
- **Assets**: The factors contributing to the risk score due to exposed organizational assets.

Hover over the circle chart to view the number of factors from each category and the percentage contribution to the total number of factors in that entity. You can click **View All**to further analyze it; you are redirected to the[Factors](https://help.zscaler.com/risk360/about-factors) page.

See image.

View top 10 factors contributing to your organization's risk score. For each factor, you can view the following information:

- **Category**: The category the factor falls under.
- **Factor Name**: The name of the contributing factor affecting the risk score.
- **Your Score**: The score for the contributing factor. The total score for a factor depends on its severity (0 being a healthy score).
- **Last 30 Days**: The graph showing the last 30-day score trend for that factor.
- **Entities**: The entities affected by the factor.
- **Licensed?**: Whether you are subscribed to the required feature to implement the recommended action (**Y** for Yes and **N** for No).
- **Recommended Actions**: The recommended action required to lower the risk score.

You can click **View All**to further analyze it; you are redirected to the[Factors](https://help.zscaler.com/risk360/about-factors) page.

See image.

The section shows top factors with high impact on your risk score and the recommendation to lower your organization's risk score. Each recommendation consists of the category name, the day it was discovered, the consequence of not implementing the recommendation, and the trend (if applicable). You can click **Explore**to further analyze a particular recommendation or click **View All** to view the list of all the recommended actions; you are redirected to the [Insights](https://help.zscaler.com/unified/viewing-risk-insights) page.

See image.

The **Risk Score Trend** graph shows the top 10 events impacting the risk score. Hover over an event to view the change in the risk score because of the event and the list of contributing [factors](https://help.zscaler.com/risk360/about-factors) within the event.

The **Top 10 Events** section lists the top 10 events observed within the last 90 days. These events are numbered in the order of their occurrence. Click **View All Events** to go to the [Score Change Log](https://help.zscaler.com/unified/viewing-risk360-score-change-logs) page, where you can view *all* the events that contributed to the risk score change in detail.

See image.

[Image: Enlarged-View]

[Image: Organization Risk Score Section]

[Image: Risk Score Trend Section]

[Image: Risk Events by Location Section]

[Image: Contributing Factors by Entity Section]

[Image: Top 10 Factors Section]

[Image: High Impact Recommendations Section]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-saas-data-security-dashboard","lastmod":"2026-02-11T05:59Z","nid":"1535050"} -->
## Viewing the SaaS Data Security Dashboard

- Source: https://help.zscaler.com/unified/viewing-saas-data-security-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Data Security > Viewing the SaaS Data Security Dashboard
- Last modified: 2026-02-11T05:59Z
- Summary: Information on the SaaS data security dashboard and widgets accessible within the Admin Portal.

The SaaS data security dashboard provides detailed information about data loss incidents from SaaS applications in your organization.

## Filtering

Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **30 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **7 Days**.

## Dashboard Widgets

The following widgets are available:

- SaaS Incidents
- Top Users with SaaS Incidents
- SaaS Applications with Data Exposure

This bar chart shows the data loss incidents from SaaS applications in your organization. Hover over the chart to see more detail about the incidents:

- **Private**: Incidents that compromise personal information.
- **Internal**: Incidents that represent a security breach within your organization.
- **External**: Incidents that originated outside your organization.

See image.

This bar chart shows the users who experienced the most data loss incidents from SaaS applications. Hover over the chart to see more detail about the incidents for each user:

- **Private**: Incidents that compromise personal information.
- **Internal**: Incidents that represent a security breach within your organization.
- **External**: Incidents that originated outside your organization.

See image.

This table shows detail about the SaaS applications with the most data exposure in your organization.

- **Application Tenant Name**: The application responsible for a data loss incident.
- **Total Incidents**: The total number of data loss incidents from this application.
- **Private**: Incidents from this application that compromise personal information.
- **Internal**: Incidents from this application that represent a security breach within your organization.
- **External**: Incidents from this application that originated outside your organization.

See image.

[Image: SaaS Incidents graph on the SaaS data security dashboard.]

[Image: Top Users with SaaS Incidents graph on the SaaS data security dashboard.]

[Image: SaaS Applications wtih Data Exposure on the SaaS data security dashboard.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-sandbox-threats-dashboard","lastmod":"2026-02-11T06:08Z","nid":"1498801"} -->
## Viewing the Sandbox Threats Dashboard

- Source: https://help.zscaler.com/unified/viewing-sandbox-threats-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Cybersecurity > Viewing the Sandbox Threats Dashboard
- Last modified: 2026-02-11T06:08Z
- Summary: Information on the Sandbox Threats dashboard and widgets accessible within the Admin Portal.

The Sandbox Threats dashboard provides an overview of [Sandbox policy actions](https://help.zscaler.com/zia/configuring-sandbox-policy) taken for known and unknown files in your organization. Known files are files that the Sandbox analyzed and classified as malicious or benign. Unknown files are files that the Sandbox encounters for the first time.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **90 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **14 Days**.
- **Status**: Select the result of the policy action: **Allowed**or **Blocked**.

## Dashboard Widgets

- Policy Actions & Verdicts for Known Files
- Policy Actions & Verdicts for Unknown Files
- Top Users Generating Sandbox Threats
- Sandbox Incidents

This bar chart shows the number of benign or malicious files allowed or blocked for the selected time range.

See image.

This bar chart shows the number of files that were identified as benign or malicious after being sent for Sandbox analysis, categorized into three categories: **Allow & Scan**, **Quarantine**, and **Allow/Do Not Scan**.

A situation might occur where a file that has been quarantined could also be counted under **Allow & Scan** if the file was allowed while in quarantine. For example, user A has a quarantine Sandbox rule applied to them, and user B has an allow and scan rule applied to them. User A attempts to download an unknown file that is sent for Sandbox analysis because of the quarantine rule. While the unknown file is being analyzed, user B can download the same file because of the allow and scan rule. In this case, the file would be counted under **Quarantine** and **Allow & Scan**.

Malicious files downloaded due to the Allow & Scan policy action are [patient 0 events](https://help.zscaler.com/zia/configuring-patient-0-alert). Zscaler recommends investigating these files.

See image.

This bar chart shows the users who generated the most sandbox threats in your organization and the number of threats generated.

See image.

This table provides a detailed list of sandbox incidents for the specified time range and category.

- **Name**: The name of the threat incident.
- **Category**: The threat category (e.g. **Sandbox Malware**, **Sandbox Adware**, etc.)
- **Impacted Systems**: The number of systems affected by the incident.
- **Status**: The action taken on the threat, e.g. **Allowed**or **Blocked**.
- **Last Known Date**: The date and time of the last known attempt by the threat.
- **First Known Date**: The date and time of the first known attempt by the threat.

Click any row in the table to see additional detail on that sandbox threat incident on two tabs:

- The **Details**tab has information about the threat:
  - **Policy Action**: The policy action enforced on transactions related to the incident.
  - **Event Type**: The type of threat event.
  - **Last Known Attempt**: The date and time of the last known attempt by the threat.
  - **First Known Attempt**: The date and time of the first known attempt by the threat.
  - **Duration**: The duration between the first and last known attempt by the threat.
  - **Total Transactions**: The total number of transactions related to the threat.
  - **Total Bytes**: The total bytes of data for all the transactions related to the threat.
  - **File Name**: The name of the file related to the incident.
  - **Sandbox Category**: The name of the sandbox category, if the file is sent for sandbox analysis.
  - **File Type**: The type of file involved in the incident.
  - **File Size**: The file size involved in the incident.
  - **MD5**: The MD5 hash for the file that triggered the rule.
  - **SHA-2 (256-bit)**: The hash of identical files.
  - **Destination IP**: The destination IP.
  - **Hostname**: The hostname involved in the incident.
  - **Application Category**: The application category related to the incident, if applicable.
  - **Application**: The name of the application involved in the incident, if applicable.
  - **URL Category**: The [URL category](https://help.zscaler.com/zia/about-url-categories) of the incident.
- The **Impacted Systems**tab has information about the systems and users affected by the threat:
  - **User Name**: The name of the user.
  - **Client IP**: The IP address from which the transaction originated. This is the IP address of the client device.
  - **Client External IP**: The internet gateway location IP address of the client.
  - **Last Known Attempt**: The date and time of the last known attempt by the threat for the user.
  - **First Known Attempt**: The date and time of the first known attempt by the threat for the user.

See image.

[Image: Bar chart showing Top Users Generating Sandbox Threats on the Sandbox Threats dashboard]

[Image: Table showing Sandbox Incidents on the Sandbox Threats dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-self-service-dashboard","lastmod":"2026-02-11T06:10Z","nid":"1498761"} -->
## Viewing the Self Service Dashboard

- Source: https://help.zscaler.com/unified/viewing-self-service-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Digital Experience > Viewing the Self Service Dashboard
- Last modified: 2026-02-11T06:10Z
- Summary: Information on the Self Service dashboard and widgets accessible within the Admin Portal.

The Self Service dashboard provides an overview of user notification activity in your organization.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between 2 hours and 48 hours in which to view data. Select **Custom** to select a specific time range. The start date must be within the last 14 days, and the minimum time range is 15 minutes. You can set any time range greater than 15 minutes in 5-minute increments. The selected period applies to all data within the dashboard. The default time range is **2 Hours**.
- **Filter data**: Click the filters in the top left to limit the data shown. Each filter allows you to include or exclude individual options.
  - **Departments**: Your departments. To learn more, see [About Departments](https://help.zscaler.com/unified/about-departments).
  - **Zscaler Locations**: Your locations. To learn more, see [About Locations](https://help.zscaler.com/unified/about-locations).
  - **Geolocations**: The geographic areas where your users are located.
  - **Notification Types**: Identified as Wi-Fi, CPU, Low Memory, or Other.

Click **Apply**to update the dashboard with your selections. To remove all filter selections, click **Reset**.

## Dashboard Widgets

- Total Notifications Sent/Notifications by Type
- Total Users Notified/Users Found Notifications Helpful
- Active Users with Self Service/Users Who Disabled Notifications
- Notifications Sent Over Time
- Notifications

This widget shows the number of notifications sent, including the percentage increase or decrease. The **Notifications by Type** bar graph shows the number of notifications broken out by type: **Wi-Fi**, **CPU**, **Low Memory**, or **Other**.

See image.

This widget shows the number of users who received notifications, including the percentage increase or decrease, and the number of users who provided feedback that their notifications were helpful, including the percentage increase or decrease.

See image.

This widget shows the number of active users who have Self Service enabled, including the percentage increase or decrease, and the number of users who disabled their notifications, including the percentage increase or decrease.

See image.

This graph shows the number of user notifications that were generated for the selected time period.

See image.

This table provides a detailed list of user notifications for the specified time period and type.

- **User**: The user who received the notification.
- **Device**: The user's device that launched the notification.
- **Notification Type**: Indicates whether the notification was related to CPU, Wi-Fi, or another issue.
- **Found It Helpful?**: Indicates **Yes**or **No** if feedback was provided by the user. **Not available** indicates feedback was not provided.
- **Timestamp**: The date and time of the notification.

Click any notification row in the table to see additional detail about that notification.

See image.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-ssl-inspection-dashboard","lastmod":"2026-02-11T06:07Z","nid":"1498811"} -->
## Viewing the SSL Inspection Dashboard

- Source: https://help.zscaler.com/unified/viewing-ssl-inspection-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Cybersecurity > Viewing the SSL Inspection Dashboard
- Last modified: 2026-02-11T06:07Z
- Summary: Information on the SSL Inspection dashboard and widgets accessible within the Admin Portal.

The SSL Inspection dashboard provides an overview of the SSL inspection traffic within your organization.

## Filtering

**Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **90 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **14 Days**.

## Dashboard Widgets

- Threats Blocked by SSL Inspection
- Top High Volume Applications
- Top Locations
- Top URL Categories

This widget shows two charts: a circle chart indicating threats blocked due to SSL inspection, broken out by application. and a bar chart that indicates the total number of threats blocked for the specified time range.

See image.

This bar graph shows the applications with the most traffic for which you have not enabled SSL inspection, and the volume of traffic for those applications.

See image.

This bar graph shows the locations with the most traffic for which you have not enabled SSL inspection, and the volume of traffic for those locations.

See image.

This bar graph shows the URL categories with the most traffic for which you have not enabled SSL inspection, and the volume of traffic for those URL categories.

See image.

[Image: Charts showing Threats Blocked by SSL Inspection on the SSL Inspection dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-subscriptions","lastmod":"2026-06-12T11:15Z","nid":"1488226"} -->
## Viewing Subscriptions

- Source: https://help.zscaler.com/unified/viewing-subscriptions
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Administration > Account Management > Viewing Subscriptions
- Last modified: 2026-06-12T11:15Z
- Summary: How to view your organization's subscriptions in the Zscaler Admin Console.

Subscriptions define the various features and levels of functionality that are available to your organization. In the Zscaler Admin Console, you can view the features subscribed to your account. The Subscriptions page lists the information that is applicable to your admin accounts.

Contact your Zscaler Account team to update your subscriptions. To learn more, see [Zscaler Pricing and Plans](https://www.zscaler.com/pricing-and-plans) and [Ranges & Limitations](https://help.zscaler.com/unified/ranges-limitations).

To view your subscriptions:

1. Go to **Administration** > **Account Management**.
2. On the Company Profile page, under **Subscriptions**, you can view the following:
  - The subscription number.
  - **Name**: The name of the subscription.
  - **SKU**: The SKU of the subscription.
  - **Status**: The status of the subscription (e.g., **Subscribed**or **Trial**).
  - **Number of Licenses**: The number of licenses for the subscription.
  - **Service Start Date (PST)**: The date (in the Pacific Standard Time Zone) when the subscription was enabled.
  - **Service End Date (PST)**: The date (in the Pacific Standard Time Zone) when the subscription expires.

See image.

You can modify the table and its columns by clicking the Column Menu icon. To learn more, see [Using Tables](https://help.zscaler.com/unified/using-tables).

[Image: Subcriptions page in the [[variable:Zscaler Admin Console]]]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-threat-locations-dashboard","lastmod":"2026-02-11T06:07Z","nid":"1498806"} -->
## Viewing the Threat Locations Dashboard

- Source: https://help.zscaler.com/unified/viewing-threat-locations-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Cybersecurity > Viewing the Threat Locations Dashboard
- Last modified: 2026-02-11T06:07Z
- Summary: Information on the Threat Locations dashboard and widgets accessible within the Admin Portal.

The Threat Locations dashboard provides an overview of the threats in your organization and the users and applications responsible for them.

## Filtering

- **Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **90 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **14 Days**.
- **Filter data**: Click the filters in the top left to limit the data shown. Each filter allows you to include or exclude individual options.
  - **Zscaler Locations**: Your locations. To learn more, see [About Locations](https://help.zscaler.com/unified/about-locations).

## Dashboard Widgets

- Total Threat Trends
- Top Threat Categories
- Top Users Generating Threats
- Top Applications Generating Threats

This line graph shows the number of threats to your organization in the specified time range.

See image.

This widget shows the threats to your organization, broken out by threat category, for the specified time range.

See image.

This bar chart shows the users who generated the most threats in your organization and the number of threats generated.

See image.

This bar chart shows the applications that generated the most threats in your organization and the number of threats generated.

See image.

[Image: Graph showing Top Threat Categories on the Threat Locations dashboard]

[Image: Bar graph showing Top Users Generating Threats on the Threat Locations dashboard]

[Image: Bar graph showing Top Applications Generating Threats on the Threat Locations dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/viewing-transactional-activity-dashboard","lastmod":"2026-02-11T06:09Z","nid":"1498786"} -->
## Viewing the Transactional Activity Dashboard

- Source: https://help.zscaler.com/unified/viewing-transactional-activity-dashboard
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Experience Center > Unified Analytics > Unified Dashboards > Cybersecurity > Viewing the Transactional Activity Dashboard
- Last modified: 2026-02-11T06:09Z
- Summary: Information on the Transactional Activity dashboard and widgets accessible within the Admin Portal.

The Transactional Activity dashboard provides an overview of malicious threat activity in your organization.

## Filtering

**Time Range**: Use the Time Range filter in the upper right to choose a specific time range between **1 Day** and **90 Days** in which to view data. The selected period applies to all data within the dashboard. The default time range is **14 Days**.

## Dashboard Widgets

The Transactional Activity page has two tabs: Threats and Policy Blocks.

### Threats Widgets

- All Threats
- Top Users Generating Threats
- Top Departments Generating Threats

### Policy Block Widgets

- Your Policy Blocks
- Top Application Class
- Top URL Categories Blocked
- Top Cloud Applications Blocked
- Top Blocked File Types

This line graph shows the number of malicious threats for the selected time period.

See image.

This bar chart shows the users who generated the most threats in your organization and the number of threats generated.

See image.

This proportional area chart shows the departments that generated the most threats and the number of threats generated.

See image.

This line graph shows the number of transactions that were blocked for policy reasons for the selected time period.

See image.

This bar chart shows the classes of applications that resulted in the most policy blocks and the number of blocks generated.

See image.

This bar chart shows the URL categories that resulted in the most policy blocks and the number of blocks generated.

See image.

This bubble chart shows the cloud applications that resulted in the most policy blocks.

See image.

This proportional area chart shows the file types that resulted in the most policy blocks and the number of blocks generated.

See image.

[Image: Bar chart showing Top Users Generating Threats on the Transactional Activity dashboard]

[Image: Bar chart showing Top Departments Generating Threats on the Transactional Activity dashboard]

[Image: Bar chart showing Top Application Class on the Transactional Activity dashboard]

[Image: Bar chart showing Top URL Categories Blocked on the Transactional Activity dashboard]

[Image: Bubble chart showing Top Cloud Apps Blocked on the Transactional Activity dashboard]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/what-data-unification","lastmod":"2026-07-29T12:04Z","nid":"1541703"} -->
## What Is Data Unification?

- Source: https://help.zscaler.com/unified/what-data-unification
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > Getting Started with Security Operations Platform > Data Sources, Outegrations, & Data Management > Data Management > Data Unification > What Is Data Unification?
- Last modified: 2026-07-29T12:04Z
- Summary: Information about the data unification process, including entity unification and field unification.

Data unification is a fundamental process of transforming disparate data into actionable business insights. It involves correlating data from multiple sources, merging duplicate records, and establishing data consistency by applying standardized rules and conditions to yield a reliable and accurate unified record.

[Image: Diagram illustrating the three steps of data unifiecation]

## Key Features and Benefits

Data unification provides the following features and benefits:

- Data integrity: Unifying data from multiple sources creates a single, accurate view of key business objects like assets, components, and findings. This consolidated view helps reduce operational inefficiencies caused by fragmented or duplicated data, fills information gaps, and supports more reliable decision making.
- Deduplication: Entity unification ensures that redundant or conflicting records are automatically resolved through configurable logic. This results in a single source of truth while preserving raw data for lineage tracking and audit purposes.
- Attribute-level conflict resolution: Field unification incorporates robust attribute reconciliation processes that address inconsistencies between merged records. This ensures data integrity at the attribute level, enhancing the quality and trustworthiness of the unified dataset.
- Flexible business rule application: Attribute transformation enables the application of organization-specific business rules to enrich and standardize data. This flexibility supports diverse use cases across analytics, compliance, and operational workflows.
- Data lineage and transparency: Maintaining access to raw, source-level data alongside unified records provides visibility into the data's origin and transformation steps, which are essential for traceability, compliance, and troubleshooting.

## How Data Unification Works

Unification involves standardizing data from multiple sources by identifying duplicate entities, resolving attribute conflicts, and applying business logic. The process consists of two main components: entity unification and field unification.

### Entity Unification

Ingesting data from multiple sources often results in duplicate records for the same entity. Entity unification is the data normalization process that merges (i.e., deduplicates) these duplicate records, creating a single trusted source of truth for your organization's data, while still maintaining raw data for lineage and visibility. To learn more, see [Configuring Entity Unification](https://help.zscaler.com/unified/configuring-entity-unification) and [Managing Entity Unification](https://help.zscaler.com/unified/managing-entity-unification).

### Field Unification

Field unification includes two steps:

- Attribute reconciliation: During entity unification, conflicts can arise between the values of the merged entities' attributes (e.g., discrepancies in the asset's type format, or a finding's severity score). These conflicts are typically reconciled (i.e., resolved) using system defaults, or through applying Priority By Source logic. To learn more, see [Configuring Field Unification](https://help.zscaler.com/unified/configuring-field-unification) and [Attribute Reconciliation Default Functions](https://help.zscaler.com/unified/attribute-reconciliation-default-functions).
- Attribute transformation: After your data is properly deduplicated and reconciled, the next step is applying business logic rules to classify and enrich the data through field unification rules. To learn more, see [Configuring Field Unification](https://help.zscaler.com/unified/configuring-field-unification) and [Managing Field Unification](https://help.zscaler.com/unified/managing-field-unification).

## When Unification Rules Run

Unification rules run when data related to the entity is ingested, directly processed, or indirectly impacted by a different processed entity. Unification rules are most commonly triggered in the following scenarios:

- Data ingestion: Unification rules are triggered automatically when new data related to an entity is ingested. For example, the Asset entity unification rules run when asset data is ingested.
- Entity processing: Unification rules run when an entity is directly processed, or indirectly impacted by changes to a related entity. For example, running the Asset Is Crown Jewel field's unification rule can trigger the Ticket Severity unification rule tied to that asset.
- Manual processing: Unification rules run when you trigger manual processing when splitting or merging tickets, or when you click Process or Process All on the Data Unification pages. To learn more, see [Managing Entity Unification](https://help.zscaler.com/unified/managing-entity-unification) and [Managing Field Unification](https://help.zscaler.com/unified/managing-field-unification).
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/unified/what-zscaler-experience-center","lastmod":"2026-06-02T11:24Z","nid":"1538541"} -->
## What Is Zscaler Experience Center?

- Source: https://help.zscaler.com/unified/what-zscaler-experience-center
- Product: Getting Started with Zscaler
- Path: Getting Started with Zscaler > What Is Zscaler Experience Center?
- Last modified: 2026-06-02T11:24Z
- Summary: Introductory information about the centralized Zscaler Experience Center console.

Zscaler Experience Center is a unified, AI-powered administrative and operations console that consolidates the management, configuration, and monitoring of the Zscaler Zero Trust Exchange (ZTE) platform into a single interface, eliminating the need for separate dashboards and fragmented workflows. It serves as the central hub for managing Zscaler's core security and networking services, including, but not limited to, Internet & SaaS (ZIA), Private Access (ZPA), Zscaler Digital Experience (ZDX), Zscaler Client Connector, etc.

## How Experience Center Works

Experience Center acts as an orchestration layer, connecting Zscaler's platform components through a shared data and identity architecture, enabling:

- **Unified Identity Layer**: Provides a single source of truth for users, groups, and roles with granular role-based access control (RBAC), that are applied consistently across all Zscaler services.
- **Simplified, Integrated Experience**: Consolidates Zscaler services into a unified interface for consistent navigation, shared global search, and efficient operation among common configuration modules.
- **Common Policy Framework**: Allows the creation of global policies that govern access to any application, regardless of whether it is hosted in a public cloud, private data center, or the open internet.
- **Generative AI Interactions**: Leverages Zscaler Copilot for generative AI-driven assistance and persona-focused analytics to help organizations secure their users, workloads, and IoT/OT devices more efficiently.
- **Unified Data and Analytics**: Aggregates logs and telemetry from Internet & SaaS, Private Access, and ZDX into a unified analytics engine, enabling cross-platform correlation of performance and security events.

[Image: DIagram showing the key features of Experience Center.]

## Key Features and Benefits

Experience Center transforms how IT and security teams interact with the Zscaler platform through the following core capabilities:

- **Unified Management**: Experience Center provides a centralized hub to manage all Zscaler offerings. By unifying administrative functions, it ensures that security policies are consistent across different traffic types (internet, private apps, and SaaS). This integration reduces the complexity of managing disparate security silos and accelerates the deployment of zero trust across the enterprise.
- **Guided Point-and-Click Workflows**: To simplify the adoption of zero trust, Experience Center features guided onboarding and configuration walkthroughs. These interactive modules follow industry best practices to help you rapidly onboard users, configure traffic forwarding, and deploy security policies to secure web, cloud, and private application access.
- **AI-Powered Operations**: Experience Center integrates Zscaler generative AI Copilot to act as an intelligent assistant for IT operations. Copilot accelerates troubleshooting by allowing natural language questions to identify the root cause of connectivity or security issues and gives proactive recommendations for policy optimizations or threat remediation.
- **Persona-Focused Insights**: Experience Center provides real-time visibility tailored to specific organizational roles. Rather than a "one-size-fits-all" view, Experience Center delivers high-level summaries for executives and deep-dive technical data for practitioners. [Image: Image showing persona types and corresponding activities available in Experience Center.]
<!-- /ZS-ARTICLE -->
