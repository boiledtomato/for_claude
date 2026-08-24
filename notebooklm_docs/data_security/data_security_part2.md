# Zscaler Help — Data Security — DSPM (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-24 01:16 UTC
Articles in this file: 20

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-graph-databases","lastmod":"2026-08-13T00:41Z","nid":"1532158"} -->
## Viewing the Graph for Databases

- Source: https://help.zscaler.com/dspm/viewing-graph-databases
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Graphs > Viewing the Graph for Databases
- Last modified: 2026-08-13T00:41Z
- Summary: Graph for a database (unmanaged, on-premises, or Snowflake) is a visual representation of the scan result.

The graph for a database (unmanaged, on-premises, or Snowflake) is a visual representation of the scan result. The graph provides in-depth details of the databases containing sensitive data, the DLP engines and dictionaries that match the sensitive data, whether data is publicly exposed to the internet, including the public exposure path, and the list of entities that can access the databases. These details are helpful to quickly evaluate and remediate the issues, protect the sensitive data, and maintain a strong security posture.

You can view graphs for the following databases:

- Managed Database
- Unmanaged Database
- Snowflake
- On-Premises File Servers
- Databricks
- Unmanaged AWS and Azure MongoDB Servers
- On-Premises MongoDB Servers

To view the graph for a database:

1. Go to **Analytics**> **Data Security** > **DSPM**> **Resource Inventory**.
2. Click any resource name to view the resource's details page. See image.
3. In the resource's details page, select the **Risk Explorer** tab. The following information is displayed:
  - On-Premises Database
  - Unmanaged Database
  - Snowflake Database

[Image: View all the resource details]

The following graph is for an on-premises database:

[Image: Shows the risk explorer graph for an on-premises database]

Click the nodes to view additional details of each entity:

- 1. Primary Resource
- 2. Database with Sensitive Data
- 3. Table with Sensitive Data
- 4. Sensitive Records
- 5. Databases
- 6. Database Principals
- 7. Admin Principals
- 8. Server Principals

View the following details of the primary resource:

- **Resource Category**: The type of data store.
- **Resource Type**: The type of resource.
- **Data Center ID**: The unique identifier of the data center.
- **Data Center Name**: The name of the data center.
- **Region**: The region where the resource is located.
- **Last Scan Status**: The status (e.g., completed) of the last scan.
- **Last Completed Scan**: The date and time when the last scan was completed.
- **Data Scanned**: The amount of data scanned.
- **Triggers**: The number of alerts raised for this resource.
- **Matched Tables**: The number of tables that match the DLP engines.
- **DLP Engines**: The [DLP engines](https://help.zscaler.com/dspm/understanding-dlp-engines-and-dictionaries) that match sensitive records.
- **DLP Dictionaries**: The [DLP dictionaries](https://help.zscaler.com/dspm/understanding-dlp-engines-and-dictionaries) associated with DLP engines.
- **ID**: The unique identifier of the resource.
- **Posture**: The [security posture](https://help.zscaler.com/dspm/understanding-security-posture-state) of the resource.
- **Metadata**: Click to view the metadata for the resource.

[Image: Shows the details related to the primary resource]

View the details of the database that contains sensitive data.

[Image: Shows the details of database with sensitive data]

View the details of the table within the impacted database that contains sensitive data.

[Image: Shows the details of the table within the database that contains sensitive data]

View the details of sensitive records.

[Image: Shows details of sensitive records]

View all the databases, including those that do not contain any sensitive data and the ones that are not scanned.

[Image: Shows the databases associated with the resource]

- View the list of all database principals that can access the resource. [Image: Shows details of database principals that can access the resource]

- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

[Image: Shows details of on-prem database principals]

[Image: Shows access path graph for a database principal]

- View the list of all admin principals that can access the resource. [Image: Shows details of admin principals that access the resource]

- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

[Image: Shows details of the admin principals]

[Image: Shows access path of the admin principal]

- View the list of all server principals that can access the resource. [Image: Shows details of server principals that can access the resource]

- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

[Image: Shows details of server principals]

[Image: Shows the access path details of server principal]

The following graph is for an unmanaged database:

[Image: Shows the Risk Explorer graph for an unmanaged database.]

Click the nodes to view additional details of each entity:

- 1. Primary Resource
- 2. Resource with Sensitive Data
- 3. Sensitive Records
- 4. Resource
- 5. Admin Principals
- 6. Server Principals
- 7. Database Principals

- **Data Store Type**: The type of data store.
- **Resource Type**: The type of resource.
- **Subscription ID**: The unique identifier of the subscription in which the resource is stored.
- **Subscription Name**: The name of the subscription in which the resource is stored.
- **Tenant ID**: The unique identifier of the tenant to which the subscription belongs.
- **Region**: The region where the resource is located.
- **Last Completed Scan**: The date and time when the resource was last scanned.
- **Data Scanned**: The amount of data scanned.
- **Triggers**: The number of sensitive records in the resource.
- **Matched Tables**: The number of tables that matched the DLP engines.
- **DLP Engines**: The [DLP engines](https://help.zscaler.com/dspm/understanding-dlp-engines-and-dictionaries) that match sensitive records.
- **DLP Dictionaries**: The [DLP dictionaries](https://help.zscaler.com/dspm/understanding-dlp-engines-and-dictionaries) associated with DLP engines.
- **ID**: Copy the tenant ID to identify the resource.
- **Tags**: The tags associated with the resource.
- **Posture**: The [security posture](https://help.zscaler.com/dspm/understanding-security-posture-state) of the resource.
- **Metadata**: Click to view the metadata for the resource.

[Image: Shows the details of the primary resource]

View the details of the database that contains sensitive data.

[Image: Shows the database that contains sensitive data]

View the details of sensitive records.

[Image: Shows details of sensitive records]

View all the associated resources, including those that do not contain any sensitive data and the ones that are not scanned. In this scenario, the Azure PostgresSQL server has two databases that contain sensitive data.

[Image: Shows the details of the associated resources]

- View the list of admin principals that can access the resource. [Image: Shows the details of all the admins that can access the resource]
- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

[Image: Shows details of unmanaged database admin principals]

[Image: Shows access path of admin principals for an unmanaged database]

- View the list of all server principals that can access the resource. [Image: Shows the details of all the entities that can access server resources]
- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

[Image: Shows details of unmanaged database server principals]

[Image: Shows access path of server principals]

- View the list of all database principals that can access the resource. [Image: Shows details of database principals]
- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

[Image: Shows details of database principals]

[Image: Shows access path of database principals]

The following graph is for a Snowflake database:

[Image: Shows the risk explorer graph for Snowflake]

Click the nodes to view additional details of each entity:

- 1. Primary Resource
- 2. Resource with Sensitive Data
- 3. Sensitive Records
- 4. Tables
- 5. Users
- 6. Services
- 7. Admin Principals
- 8. Public Internet

- **Resource Category**: The type of datastore.
- **Resource Type**: The type of resource.
- **Account ID**: The unique identifier of the account in which the resource is located.
- **Account Name**: The name of the account in which the resource is located.
- **Organization ID**: The unique identifier of the organization to which the account belongs.
- **Region**: The region where the organization is located.
- **Latest Scan Status**: The status of the most recent scan.
- **Last Completed Scan**: The date and time when the resource was last scanned.
- **Data Scanned**: The amount of data scanned.
- **Triggers**: The number of alerts raised for this resource.
- **Matched Tables**: The number of tables that matched the DLP engines.
- **DLP Engines**: The [DLP engines](https://help.zscaler.com/dspm/understanding-dlp-engines-and-dictionaries) that match sensitive records.
- **DLP Dictionaries**: The [DLP dictionaries](https://help.zscaler.com/dspm/understanding-dlp-engines-and-dictionaries) associated with DLP engines.
- **ID**: Copy the tenant ID to identify this resource in the tenant.
- **Tags**: The tags associated with the resource.
- **Posture**: The [security posture](https://help.zscaler.com/dspm/understanding-security-posture-state) of the resource.
- **Metadata**: Click to view the metadata for the resource.

[Image: Shows the details of the primary resource]

View the details of the tables that contain sensitive data.

[Image: Shows details of tables that contain sensitive data]

- View the details of sensitive records.[Image: Shows the details of sensitive records]
- Click **View Sensitive Data** to view the sensitive data discovered by the scan. See image.

View all the associated resources, including those that do not contain any sensitive data and the ones that are not scanned.

[Image: Shows details of all the associated resources]

- View the list of all the services that can access the Snowflake database. [Image: Shows the details of services that can access the Snowflake database]
- Click **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

- View the list of all admin entities that can access the Snowflake database. [Image: Shows the details of all the admins that can access the Snowflake database]
- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

- View the list of all the users who can access the Snowflake database. [Image: Shows the details of users that can access the Snowflake database]
- Click the **Entity Name** to view additional details. See image.
- Click **Show Access Path** to view another graph that displays the entities and their assigned permissions that allow access to the database. See image.

- View the details of public internet.[Image: Snowflake database exposure screen showing Public Internet access and exposure]
- **View Metadata**: Click to view metadata for the network or user policy that defines how the database is accessed over the internet.

[Image: Shows detail about Snowflake user]

[Image: Shows the access path graph for Snowflake user]

[Image: Shows the details of Snowflake service]

[Image: Shows the access path for a Snowflake service]

[Image: Shows details of Snowflake admin principal]

[Image: Shows the access path graph of Snowflake admin principal]

[Image: Shows the sensitive information]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-graph-gcp-data-stores","lastmod":"2026-04-24T21:06Z","nid":"1520001"} -->
## Viewing the Graph for GCP Data Stores

- Source: https://help.zscaler.com/dspm/viewing-graph-gcp-data-stores
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Graphs > Viewing the Graph for GCP Data Stores
- Last modified: 2026-04-24T21:06Z
- Summary: Information on the Resource Inventory graph that depicts the GCP data stores containing sensitive data.

The Resource Inventory graph for GCP data stores is a visual representation of the scan result. The graph provides in-depth details of the GCP storage bucket, GCP Cloud Storage instance, and GCP Cloud SQL instance that contain sensitive data, the DLP engines and dictionaries that match the sensitive data, whether it is publicly exposed to the internet, including the public exposure path, the list of entities that can access the resource, and the vulnerabilities and malware detected in the resource. These details are helpful to quickly evaluate and remediate the issues, protect the data, and maintain the security posture.

You can view graphs for the following GCP data stores:

- GCP BigQuery
- GCP Compute Instance
- GCP Cloud SQL Instance
- GCP Storage Bucket

The following graph depicts the scan results for a GCP storage bucket.

[Image: Graph for a storage bucket containing sensitive data along with the associated resources.]

The graph includes the following nodes:

- 1. Public Exposure Path
- 2. Primary Resource
- 3. Managed Folder
- 4. Sensitive Records
- 5. Service Accounts
- 6. Domains
- 7. External
- 8. Users
- 9. Services

View the GCP storage bucket details. You can:

- **Resource Category**: The type of data store.
- **Resource Type**: The primary resource.
- **Project ID**: The unique identifier of the project in which the resource is located.
- **Project Name**: The name of the project in which the resource is located.
- **Organization ID**: The unique identifier of the organization to which the project belongs.
- **Region**: The region where the organization is located.
- **Latest Scan Status**: The status of the last scan.
- **Last Completed Scan**: The date and time when the last scan was completed.
- **Data Scanned**: The amount of data scanned.
- **Triggers**: The number of alerts raised for this resource.
- **Matched Tables**: The number of files that matched the DLP engines.
- **DLP Engines**: The [DLP engines](https://help.zscaler.com/dspm/understanding-dlp-engines-and-dictionaries) that match the sensitive records.
- **DLP Dictionaries**: The dictionaries associated with the DLP engines.
- **ID**: Copy the project ID to identify this resource in the GCP organization.
- **Tags**: The tags associated with the resource.
- **Posture**: The [security posture](https://help.zscaler.com/dspm/understanding-security-posture-state) of the resource.
- **Metadata**: Click to view the metadata for the resource.

[Image: Details of the storage bucket that contains sensitive data]

View the publicly exposed resources. To learn more, see [Viewing the Public Exposure Path](https://help.zscaler.com/dspm/viewing-public-exposure-path).

[Image: Describes how the storage bucket is publicly exposed and option to view the list of exposed files.]

View the details of a sensitive record.

[Image: Details of the storage container that contains sensitive data.]

View the details of the managed folder in the storage bucket.

[Image: Managed folders associated with the storage bucket.]

View the external entities that can access the resource. External entities are users, services, or roles that are part of a different cloud account that is not onboarded to DSPM.

[Image: External entities that can access the primary resource.]

View the list of users who can access the primary resource.

[Image: Users who can access the primary resource.]

View the services that can access the primary resource.

[Image: The services that can access the primary resource.]

View the domains that can access the primary resource.

[Image: The domain that can access the primary resource.]

View the service accounts that can access the primary resource.

[Image: The service accounts that can access the primary resource.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-investigation-results","lastmod":"2026-04-28T21:06Z","nid":"1478451"} -->
## Viewing the Investigation Results

- Source: https://help.zscaler.com/dspm/viewing-investigation-results
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Investigation > Viewing the Investigation Results
- Last modified: 2026-04-28T21:06Z
- Summary: Information about the investigation queries created in the Zscaler Admin Console.

The investigation queries you create are displayed on the [Investigation page](https://help.zscaler.com/dspm/about-investigation). You can run the investigation query and view the investigation results.

To view the investigation results:

1. Go to **Logs** >**Insights**>**Investigation**.
2. On the **Investigation** page, you can do the following: The investigation results are displayed. See image. Resources with the highest level of severity are displayed in hierarchical order. When you sort the data in the **Risk** or **Resource Name** column, resources with the highest risk are displayed first, followed by the ones with lower risk. Click the **Resource Name** to view the [resource details](https://help.zscaler.com/dspm/viewing-resource-details).
  - By default, the **History** tab displays all the queries that you've created. Click the **Actions** icon () for any query, then select **Run Query**. See image.
  - Select the **Saved** tab to view the saved queries. Click the **Actions** icon () for any query, then select **Run Query**. See image.
  - On the **Saved** tab, click the**Investigation Name** or **Query** to view the drawer. Click the **Run Query** icon at the top right corner. See image.

[Image: Run a saved query]

[Image: Click the Run Query icon]

[Image: Run the query]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-malware-details","lastmod":"2026-06-30T04:25Z","nid":"1504446"} -->
## Viewing the Malware Details

- Source: https://help.zscaler.com/dspm/viewing-malware-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Resource Inventory > Resource Details > Viewing the Malware Details
- Last modified: 2026-06-30T04:25Z
- Summary: Information on malware and malicious files in the cloud resources that are scanned and displayed on the Zscaler Admin Console.

Malware is malicious software or code that can compromise systems, steal data, or disrupt operations. Common forms of malware include viruses, worms, ransomware, spyware, etc. DSPM detects malware in your resources and displays the details on the [Resource Inventory page](https://help.zscaler.com/dspm/about-resource-inventory).

To view the malware details:

1. Go to **Analytics**> **Data Security** > **DSPM**> **Resource Inventory**.
2. Click any **Resource Name** to view the resource's details page. See image.
3. In the resource's details page, select the **Malware**tab. The following information is displayed:
  - **Total Malware Files**: The total number of malware files.
  - **Malware**: The type of malware (e.g., Virus, Exploit) with their respective numbers.
  - The list of resources containing malware data is displayed. For each file, you can see:
    - **Volume**: The name of the volume.
    - **File Name**: The name of the file containing malware.
    - **File Type**: The type of file (e.g., .txt, .doc).
    - **File Size**: The file size.
    - **Path**: The file path.
    - **Malware Name**: The malware file name.
    - **Malware Type**: The malware type.
    - **Hash**: The hash value of the file.
    - **Last Completed Scanned**: The date and time the resource was last scanned.
    - **Created Date**: The date and time the resource was created.
  - Click the **Malware Type** filter to view the data for a specific malware type. See image.

[Image: View the malware details detected in the resource.]

[Image: View malware type filter]

[Image: The Report Management page shows information about your Excel files and the chance to download them]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-onboarded-account-details","lastmod":"2026-04-24T22:29Z","nid":"1520536"} -->
## Viewing the Onboarded Account Details

- Source: https://help.zscaler.com/dspm/viewing-onboarded-account-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Cloud Accounts Onboarding > Cloud Account Management > Viewing the Onboarded Account Details
- Last modified: 2026-04-24T22:29Z
- Summary: Information about the Cloud Accounts page and the actions available in the Zscaler Admin Console.

After onboarding the accounts successfully, you can view the details of all the onboarded accounts on the Cloud Accounts page.

To view the onboarded account details:

1. Go to **Policies** > **Common Configuration** > **DSPM** > **Cloud Accounts**.
2. Click the required cloud account (AWS, Azure, or GCP). See image.
3. Select the **Accounts** tab for AWS, **Subscriptions** tab for Azure, and **Projects** tab for GCP. See image. The tab name varies depending on the selected cloud account.

In this article, the **Subscriptions** tab is shown as an example. On the **Subscriptions** tab, you can do the following:

1. Select different actions from the **Manage** drop-down menu. To learn more, see [About Cloud Accounts](https://help.zscaler.com/dspm/about-cloud-accounts). See image.
2. [Add filters to view specific accounts.](https://help.zscaler.com/unified/using-tables#filter)
3. Search for a specific account in the searchable columns.
4. For each subscription, you can see:
  - **Subscription Name:** The name of the subscription. Click the name to view additional details:
    - Details
    - Issues
  - **Subscription ID:**The unique identifier of the subscription.
  - **Business Unit:** The business unit assigned to the account.
  - **Status:** The status of the scan configuration (Successfully Configured, Pending Configuration, Needs Attention).
  - **Last Validated**: The date and time when the account is validated to check all configurations.
5. Select multiple accounts and perform the same action on all of them at the same time, like [changing the business unit](https://help.zscaler.com/dspm/changing-business-unit) or [deleting the onboarded accounts](https://help.zscaler.com/dspm/deleting-onboarded-account).
6. [Modify the table and its columns](https://help.zscaler.com/unified/using-tables#hide).
7. Click the **Action** icon to [change the business unit](https://help.zscaler.com/dspm/changing-business-unit) or [delete](https://help.zscaler.com/dspm/deleting-onboarded-account) a subscription.

- **Status:** The [status of the scan configuration](https://help.zscaler.com/dspm/viewing-orchestrator-status) (Successfully Configured, Pending Configuration, Needs Attention) for the account.
- **Business Unit:** The [business unit](https://help.zscaler.com/dspm/changing-business-unit) assigned with the account.
- **Onboarding Date:** The date and time the account was onboarded
- **CloudTrail**: Cloud Trail details are displayed only for AWS accounts. See image.
  - **Bucket Name**: The name of the S3 bucket associated with CloudTrail.
  - **Prefix**: The prefix specified in the CloudTrail bucket path.
  - **Bucket Account ID**: The AWS account ID where the CloudTrail S3 bucket is present.

- View the configuration issues that occurred while onboarding the account along with the resolution steps. To learn more, see [Viewing Onboarding Issues](https://help.zscaler.com/dspm/viewing-onboarding-issues).
- From the **Actions** drop-down menu, you can [change the business unit](https://help.zscaler.com/dspm/changing-business-unit) or [delete an onboarded account](https://help.zscaler.com/dspm/deleting-onboarded-account). See image.

[Image: DSPM cloud account details page with the Issues tab selected, showing issues and resolution steps. Within the Issues tab, the Action menu is annotated, showing available options.]

[Image: Account details page opened in the Details tab, showing the details of the cloud account.]

[Image: Manage action menu, displaying available options.]

[Image: The Cloud Accounts page with the Subscriptions tab selected showing basic details about each subscription.]

[Image: The Cloud Accounts page, with an annotation around the Subscriptions tab, showing the basic details for each subscription.]

[Image: The list of onboarded cloud accounts with one account selected.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-onboarding-issues","lastmod":"2024-10-27T07:06Z","nid":"1474921"} -->
## Viewing Onboarding Issues

- Source: https://help.zscaler.com/dspm/viewing-onboarding-issues
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Cloud Accounts Onboarding > Cloud Account Management > Viewing Onboarding Issues
- Last modified: 2024-10-27T07:06Z
- Summary: Information about how to resolve issues in the cloud account.

DSPM detects issues in the [onboarded AWS organization](https://help.zscaler.com/dspm/onboarding-aws-organization), [GCP organization](https://help.zscaler.com/dspm/onboarding-gcp-organization), or [Azure tenant](https://help.zscaler.com/dspm/onboarding-microsoft-azure-tenant) and lists them on the Issues tab. You can also view the resolution steps to resolve the issue.

If the issues are not addressed, they might impact the [scanning of the data stores](https://help.zscaler.com/dspm/about-scan-settings).

The following issues might occur while onboarding accounts:

| AWS | Microsoft Azure | GCP |
| --- | --- | --- |
| Role not found in the orchestrator or target accounts | Role not present or misconfigured in the Microsoft Entra tenant | Missing permissions in the target project |
| Missing permissions in the orchestrator or target accounts | Admin consent not granted | Missing permissions in the Orchestrator project |
| Configuration issues in the orchestrator account | Client Secret is invalid | Missing permissions in the service account |
| Configuration issues in the CloudTrail or incorrect CloudTrail configuration | Application object unavailable | Missing resources in the Orchestrator project |
| Orchestrator validation is pending | Role not found or misconfigured in the orchestrator subscription. | Orchestrator validation is pending |
| Orchestrator unable to communicate with DSPM | Onboarded account is currently suspended. | Orchestrator is unable to communicate with the DSPM |
| Resources missing in the orchestrator account | Onboarded account is deleted | Onboarded account is deleted |
| Onboarded account is currently suspended | Onboarded account is currently suspended | Onboarded account is currently suspended |

If an issue is detected in the target accounts, or projects, the status of the target accounts or projects moves to the **Needs Attention** state until the issue is resolved.

To view the onboarding issues:

1. Go to **Administration** > **Configuration** > **Cloud Accounts**.
2. Select the organization, project, or tenant from the list.
3. Select the **Issues** tab to view the list of issues along with the resolution steps. See image.
4. Click **See Accounts** to view the list of affected accounts, projects, or subscriptions. See image.

[Image: View the issues in the onboarded account]

[Image: Target account issues]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-onboarding-status","lastmod":"2026-04-24T22:31Z","nid":"1520571"} -->
## Viewing the Onboarding Status

- Source: https://help.zscaler.com/dspm/viewing-onboarding-status
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Cloud Accounts Onboarding > Cloud Account Management > Viewing the Onboarding Status
- Last modified: 2026-04-24T22:31Z
- Summary: How to view the onboarding status for a cloud account in the Zscaler Admin Console.

DSPM runs a health validation service to check if the onboarding templates are deployed successfully and roles and permissions are configured for each account. After the validation is completed, the onboarding status of the target accounts and [orchestrator accounts](https://help.zscaler.com/dspm/understanding-orchestrator) are displayed on the Overview tab.

In this article, an AWS account's status is shown as an example.

The field names vary depending on the cloud account: **Account**for AWS, **Subscription**for Azure, and **Project**for GCP.

On the **Overview**tab, you can view the following details:

1. **New template available for deployment** (Optional): A notification banner is displayed if a new template (e.g., Tree Discovery, Onboarding) is available for deployment. DSPM releases new templates that include additional functionalities for scanning and collecting metadata. Click **See Details** to view the available templates on the [Roles and Templates](https://help.zscaler.com/dspm/viewing-roles-and-templates) tab. See image. If you close the notification banner, it is removed only for the current session. The message is displayed when you log in again.
2. **Account Status**: The number of accounts configured for [data scan](https://help.zscaler.com/dspm/about-scan-settings) versus the total number of accounts in the organization displayed in the donut chart. You can see the following statuses:
  - **Successfully Configured**: The number of accounts that are configured and validated successfully.
  - **Needs Attention**: The number of accounts that have [misconfigurations or permission issues](https://help.zscaler.com/dspm/resolving-onboarding-issues).
  - **Pending Configuration**: The number of accounts for which the roles and permissions are yet to be configured and validated.
  - **Monitored Regions**: The list of [regions](https://help.zscaler.com/dspm/managing-regions) where the target accounts are located.
  - The list of [services](https://help.zscaler.com/dspm/managing-services)selected for monitoring and scanning.
3. **Orchestrator Details**: The details about the [orchestrator account](https://help.zscaler.com/dspm/understanding-orchestrator) in which the DSPM's orchestrator template is deployed. You can see:
  - **Account Name**: The account name.
  - **Account ID**: The account ID.
  - **Custom Tags**: The number of [custom tags](https://help.zscaler.com/dspm/managing-custom-tags) added.
  - **Region**: The primary region selected while onboarding.
  - **Network Type**: The network configuration used for onboarding the account or organization.
  - **DSPM Connection Status**: The [connection status](https://help.zscaler.com/dspm/viewing-orchestrator-status) (**Successful** or **Failed**) of the orchestrator instance with DSPM.
  - **Configuration Status**: The [configuration status](https://help.zscaler.com/dspm/viewing-orchestrator-status) (**Successful**, **Warning**, **Failed**, or **Pending Validation**) indicating whether all accounts are available and permissions are configured correctly in the orchestrator subscription.
  - **Last Connected**: The last time the orchestrator instance was successfully connected with DSPM.
4. **CloudTrail**: The details of the [organization CloudTrail](https://help.zscaler.com/dspm/understanding-aws-cloudtrail) provided while [onboarding](https://help.zscaler.com/dspm/onboarding-aws-organization). You can see: This field is available only for AWS organizations.
  - **CloudTrail Bucket Name**: The name of the S3 bucket where the CloudTrail events are logged.
  - **Prefix**: The prefix specified in the CloudTrail bucket path.
  - **Bucket Account ID**: The AWS account ID where the CloudTrail S3 bucket is present.
  - **Status**: The status (**Enabled** or **Failed**) of the CloudTrail configuration.
5. **Evidence**: The details of the S3 bucket where the evidence data is stored.
  - **S3 Bucket Name**: The S3 bucket name.
  - **Storage Type**: The storage type (**Zscaler** or **Custom**).
  - **S3 Bucket Account ID**: The S3 bucket account ID.
6. **Data Events**: The details of the CloudTrail where the data events are stored. You can see: This field is available only for AWS organizations.
  - **CloudTrail Bucket Name**: The name of the S3 bucket where the data events are stored.
  - **Prefix**: The prefix specified in the CloudTrail bucket path.
  - **Bucket Account ID**: The AWS account ID where the S3 bucket is present.
  - **Status**: The status (**Enabled** or **Failed**) of the CloudTrail configuration.

[Image: The Overview tab for an AWS account that shows the account status, orchestrator account, and CloudTrail details.]

[Image: View the notification banner at the top of the Overview tab.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-orchestrator-status","lastmod":"2026-04-24T22:30Z","nid":"1487776"} -->
## Viewing Orchestrator Status

- Source: https://help.zscaler.com/dspm/viewing-orchestrator-status
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Cloud Accounts Onboarding > Cloud Account Management > Viewing Orchestrator Status
- Last modified: 2026-04-24T22:30Z
- Summary: Information about the orchestrator status in the Zscaler Admin Console.

DSPM regularly scans the data stores (databases, virtual machines, etc.) within your target accounts by leveraging the [orchestrator](https://help.zscaler.com/dspm/understanding-orchestrator)that is deployed in your account during the onboarding process. The orchestrator instance launches [scanner instances](https://help.zscaler.com/dspm/understanding-scanner-instances) in the regions where the target account data stores exist and scans the data.

On the **Overview** tab, you can view the following status types for the orchestrator connection and configuration:

- **DSPM Connection Status**: The connection status between the orchestrator and DSPM.
  - **Successful**: The orchestrator instance is connected to DSPM successfully.
  - **Failed**: The orchestrator instance is unable to establish a connection with DSPM.
  - **Waiting for connection**: The orchestrator template is not yet deployed in the cloud service provider (CSP).
- **Configuration Status**:Indicates whether all resources are available and permissions are configured in the orchestrator account. The configuration status can be one of the following:
  - **Successful**: All resources are successfully deployed and permissions are configured in the orchestrator account.
  - **Warning**: Some resources or permissions are unavailable, tampered with, or not visible.
  - **Failed**: The orchestrator instance cannot launch scanner instances, or some roles and permissions are missing in the orchestrator account.
  - **Pending**: The orchestrator account has changed or the template is yet to be deployed in the CSP.
- **Last Connected**: The last time the orchestrator instance was successfully connected with DSPM.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-policy-details","lastmod":"2026-04-21T21:06Z","nid":"1478166"} -->
## Viewing the Policy Details

- Source: https://help.zscaler.com/dspm/viewing-policy-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Data Posture Policies > Viewing the Policy Details
- Last modified: 2026-04-21T21:06Z
- Summary: Information about the data posture policy details in the Zscaler Admin Console.

Data posture policies are queries that looks for vulnerabilties or misconfigurations in the data stores and detect any potential possibilities of a data breach. You can view the details of policies that are used to query data stores in AWS, Azure, and GCP cloud resources. You can see the policy description, the query used to define the policy, remediation steps, and options to disable or clone the policy. To learn more, see [About Data Posture Policies](https://help.zscaler.com/dspm/about-data-posture-policies).

To view the policy details:

1. Go to **Policies**> **Data Protection** > **Policy**> **Data Posture Policies**.
2. Under the **Policy Name** column, click any policy. See image. The Policy page appears with the following tabs:
  - Policy Overview
  - Remediation
3. Click the **Actions** menu and choose to disable or clone a policy. See image.

On the **Policy Overview** tab, you can view:

- **Policy Details**:
  - **Policy Rationale**: A brief explanation about what the policy detects.
  - **MITRE**: The MITRE techniques that are related to the detected issue. Click the number to view all the associated MITRE techniques. See image.
  - **Policy ID**: The unique identifier for the policy.
  - **Policy Category**: The threat category to which the policy belongs.
  - **Resource Type**: The type of data store (EC2, Storage Account, RDS Instance, etc.)
  - **Compliance Framework**: The list of compliance frameworks that are applicable for this policy.
- **Policy Query**: The details of the query used to create this policy. You can view the query only for custom policies. You cannot view policies that are temporarily hidden. See image.

See image.

On the **Remediation** tab, you can see the remediation steps to resolve the issue detected in the resource.

See image.

[Image: The policy rationale for an Azure virtual machine]

[Image: Remediation steps to resolve the misconfiguration in the Azure virtual machine]

[Image: Select to disable or clone the policy]

[Image: Click any policy name to view the policy details]

[Image: List of MITRE techniques that are applicable for the policy]

[Image: The Policy Overview tab with information at the end about why a query for the policy is temporarily hidden.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-public-exposure-path","lastmod":"2026-04-24T21:06Z","nid":"1480566"} -->
## Viewing the Public Exposure Path

- Source: https://help.zscaler.com/dspm/viewing-public-exposure-path
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Graphs > Viewing the Public Exposure Path
- Last modified: 2026-04-24T21:06Z
- Summary: How to view the graph related to the public exposure path in the Zscaler Admin Console.

Attack paths allow you to address security issues in your cloud infrastructure. A public exposure path is an attack path that shows the resources that contain sensitive data and are publicly exposed, which could allow adversaries to directly access such resources from the internet, leading to data breaches.

DSPM scans your cloud accounts and detects such publicly exposed resources along with the vulnerabilities or misconfigurations in the different components (network security group, load balancer, virtual network, etc.) that are associated with the resource. This information enables you to quickly analyze and remediate the issue.

The public exposure path is enabled only for the EC2 instances, virtual machines, and DB cluster resources.

To view the graph for a public exposure path:

1. Go to **Analytics** > **Data Security**> **DSPM**> **Resource Inventory**.
2. On the **Resource Inventory** page, click the **Resource Name**. See image. In the resource's details page, the graph is displayed on the **Risk Explorer** tab. See image.
3. Click the **Public Internet** node to view the associated components through which the primary resource is exposed to the internet. See image.
4. Click **Show Public Exposure Path**to view a graph that visually represents the associated components, including those that have misconfigurations and vulnerabilities. See image. For cloud storage buckets and Azure containers, the reason for exposure is displayed with the following options: See image.
  1. **View Metadata**: View the JSON file, copy or download the metadata, or go to the cloud service provider (CSP) portal to view the specific resource details. See image.
  2. **Verify Exposed Files**: View the list of publicly exposed files that can be accessed anonymously. You can copy the file path, download the file, or export the files in CSV format to investigate the issue. You can also go to the CSP portal to view the file details. See image.
  3. **Verify Static Website**: View the static website hosted in the storage bucket or container.
5. Click each node to view additional details for the entity. See image.
  - Click the **Warning** icon () to see the security issue. See image.
  - Click **Go to AWS**to view the details in the AWS Management Console.
  - Click **</> Metadata** to view the JSON file, copy or download the metadata, or go to the CSP portal. See image.

## How the Resources are Publicly Exposed

The resources are publicly exposed in the following ways:

- AWS
- Azure
- GCP

- **Exposure via Amazon S3 Block Public Access Misconfigurations**: AWS provides settings to block account level misconfigurations that might lead to public exposure. To block the S3’s public exposure at the account level, ensure IgnorePublicAcls and RestrictPublicBuckets settings are set to true.
- **Exposure via AWS S3 Bucket ACLs**: This S3 bucket can use bucket ACLs to manage access control of S3 buckets. One or more ACLs are granting access to the principals http://acs.amazonaws.com/groups/global/AllUsers or http://acs.amazonaws.com/groups/global/AuthenticatedUsers, allowing the S3 bucket to be publicly accessible. The bucket level exposure created by this configuration leads to the S3’ bucket’s directory listing and potential loss of sensitive data.
- **Exposure via AWS S3 Bucket Policies**: S3 bucket policies provide granular access control and governance for S3 buckets. One or more policy statements as part of the bucket policy is granting access to the principal "*" which is resulting in the S3 bucket’s public exposure. A publicly exposed S3 bucket has the potential to disclose sensitive data to unauthorized entities.
- **Exposure via Hosted Website**: Hosted websites allow an S3 bucket to serve static web content like HTML, CSS, JS, etc. An enabled static website might accidentally transmit sensitive data over the web and this should be monitored carefully.

[Image: View public internet node drawer]

[Image: Public exposure path]

[Image: Vulnerability description]

[Image: View metadata]

- **Exposed to the Internet**: An SQL server configured with a firewall rule that permits traffic from 0.0.0.0 - 255.255.255.255 allows traffic to reach the SQL server from any source over the public internet. To mitigate exposure risk, configure a firewall rule to permit traffic only from specific IP addresses of the application instances that require access to the SQL server.
- **Exposed to All Azure Tenants**: An SQL server firewall configuration offers an option to create an exception to "Allow Azure services and resources to access this server". With this configuration, the server not only becomes available to resources within the Azure subscriptions and resource groups of the customer but to every Azure tenant including subscriptions that do not belong to the customer. To mitigate exposure risk, configure a firewall rule to permit traffic only from a specific source (IP addresses) that might require access to the SQL server.
- **Static Websites**: This Azure storage account supports hosting static websites. When enabled, this creates a container named $web. Any object stored within this $web container gets instantly exposed anonymously (irrespective of firewall or container ACL) via the storage account’s primary endpoint. Ensure no sensitive information is stored in this folder as it will become publicly accessible.
- **Azure Storage Account is Publicly Exposed via Shared Key**: Shared access keys are configured on this storage account along with public network access. Enabling public network access allows the storage account to access all the resources in the storage account via those access keys from anywhere on the internet. It is recommended to use Entra authorization for authenticated access to storage accounts, or prevent public network access for this storage account by allowing access only from trusted networks or IP addresses.
- **Containers with Anonymous Access**: Storage containers with an access list configured with "Anonymous Container Read Access" allow anyone to access data (blobs) stored within the container. This particular configuration results in directory listing and access to all files in a storage container. To allow public access to specific files, set the access list to "Blob Anonymous Access" and configure public visibility for individual files.
- **SFTP/SSH Access**: This Azure storage account supports SFTP/SSH access for Azure data lake storage accounts. The containers exposed via SFTP can be accessed publicly as network ACLs settings are not appropriately configured. If this account needs to be exposed publicly for SFTP access, ensure to use an SSH key. To reduce exposure, disable Public SFTP access and restrict it to specific networks or IPs.

[Image: Shows the Resource Inventory listing all the scanned resources.]

[Image: Shows the public exposure path details.]

- **Google Cloud Storage Bucket is Publicly Exposed Anonymously**: This storage bucket is publicly accessible to anyone over the internet. Check the role bindings in JSON to know the storage bucket’s public access level. To remove public exposure, delete the allUsers principal from the storage bucket's IAM policy bindings.
- **Google Cloud Storage Bucket is Publicly Exposed to Google Accounts**: This storage bucket is accessible to anyone with a Google account. To remove public exposure, delete the allAuthenticatedUsers principal from the storage bucket's IAM policy bindings.
- **Google Cloud Storage Bucket is Publicly Exposed Anonymously via Managed Folders**: This storage bucket contains one or more managed folders that are accessible to anyone over the internet. Check the IAM policy bindings in JSON to know the folders’ public access level. To remove public exposure, delete the allUsers principal from the folder's IAM policy bindings.
- **Google Cloud Storage Bucket is Publicly Exposed to Google Accounts via Managed Folders**: This bucket contains one or more managed folders that are accessible to anyone with a Google account. To remove public exposure, delete the allAuthenticatedUsers principal from these folders’ IAM policy bindings.
- **Static Website**: This storage bucket contains publicly exposed resources along with a static website. This might make sensitive data publicly accessible over the advertised public endpoint of the bucket. If this bucket should not have static website enabled, disable the bucket’s static website feature.

[Image: View metadata for storage bucket]

[Image: Verify exposed files]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-remediation-details","lastmod":"2026-04-15T00:34Z","nid":"1478186"} -->
## Viewing Remediation Details

- Source: https://help.zscaler.com/dspm/viewing-remediation-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Alerts > Alert Details > Viewing Remediation Details
- Last modified: 2026-04-15T00:34Z
- Summary: Information on DSPM alert remediation details.

You can manually remediate the [alerts](https://help.zscaler.com/dspm/about-alerts) generated for a [policy](https://help.zscaler.com/dspm/about-data-posture-policies). The Remediation tab provides detailed steps that you need to perform in your cloud service provider (CSP) to address the policy violation. After the violation is addressed in the CSP, all the corresponding alerts generated for that policy are [resolved](https://help.zscaler.com/dspm/alert-status).

When you remediate an alert, the policy violation is resolved in the CSP and the Zscaler Admin Console. The alert moves from the Open state to the Resolved state.

To view the remediation details:

1. Go to **Administration**> **Alerts**> **Alerts**.
2. Select the **All Alerts** tab.
3. Click the **Alert ID**. See image.
4. On the individual alert's page, select the **Remediation** tab to view the details. See image. You can also access the Remediation tab from the [Grouped by Policy](https://help.zscaler.com/dspm/viewing-alerts-grouped-policy) tab, [Grouped by Resource](https://help.zscaler.com/dspm/viewing-alerts-grouped-resource) tab, [Dashboard](https://help.zscaler.com/dspm/about-dashboard), and the [Resource Inventory](https://help.zscaler.com/dspm/about-resource-inventory) page.

[Image: View all alerts triggered in the Zscaler Admin Console.]

[Image: View the remediation steps to resolve an alert.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-resource-details","lastmod":"2026-06-30T05:54Z","nid":"1474781"} -->
## Viewing the Resource Details

- Source: https://help.zscaler.com/dspm/viewing-resource-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Resource Inventory > Resource Details > Viewing the Resource Details
- Last modified: 2026-06-30T05:54Z
- Summary: Information about vulnerabilities and sensitive data detected in resources and displayed in the Zscaler Admin Console.

You can view additional details of the resource that contains sensitive data or that has vulnerabilities. The granular information about the resource allows you to quickly investigate and take the necessary action.

To view the resource details:

1. Go to **Analytics** > **Data Security**> **DSPM**> **Resource Inventory**. The **Resource Inventory** page appears. See image.
2. Click any **Resource Name** to view the following tabs:
  - Risk Explorer
  - Sensitive Data
  - Alerts
  - Access
  - Vulnerabilities
  - Malware
  - Timeline

[Image: View the Resource Inventory page]

The scan results are [displayed on a graph](https://help.zscaler.com/dspm/viewing-resource-inventory-graph).

See image.

[Image: View the scan results on a graph]

View the [sensitive data details](https://help.zscaler.com/dspm/viewing-sensitive-data-details).

View the[alert details](https://help.zscaler.com/dspm/viewing-alert-details).

View the [access level details](https://help.zscaler.com/dspm/viewing-resource-access-levels).

View the [vulnerabilities](https://help.zscaler.com/dspm/viewing-vulnerability-details) detected in the EC2 instances.

See image.

View the [malware](https://help.zscaler.com/dspm/viewing-malware-details) details.

View the [timeline](https://help.zscaler.com/dspm/viewing-timeline-details-resources) details.

The resource timeline is limited to a maximum of 30 days.

See image.

[Image: View the vulnerability details]

[Image: View the timeline details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-resource-inventory-graph","lastmod":"2026-04-27T21:06Z","nid":"1478111"} -->
## Viewing the Resource Inventory Graph

- Source: https://help.zscaler.com/dspm/viewing-resource-inventory-graph
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Graphs > Viewing the Resource Inventory Graph
- Last modified: 2026-04-27T21:06Z
- Summary: Viewing the resource details and its associated vulnerabilities as a graph in the Zscaler Admin Console.

After DSPM completes the [data scan](https://help.zscaler.com/dspm/about-scan-settings) of the resources, the scan results are displayed in the form of graphs that are visually appealing and highly interactive. The graphs consist of interactive nodes that provide contextual information (e.g., cloud account name, primary resource type, ID, file path, number of files containing sensitive data, access levels) about the primary resource and the associated secondary resources.

The graphs also include attack paths that show how the resource is compromised either through public exposure, malware, by an IAM entity (user, service, role) with access privileges, vulnerabilities, etc. This information helps you analyze the issues in detail and remediate them immediately.

The following nodes and attack paths are depicted in the Resource Inventory graph:

- List of Nodes
- Attack Paths

To view the Resource Inventory graph for a resource:

1. Go to **Analytics**> **Data Security** > **DSPM**> **Resource Inventory**. On the **Resource Inventory** page, you can see the scanned results for AWS, Azure, and GCP resources.
2. Click any **Resource Name** to view the drawer. See image.
3. In the resource's details page, select the **Risk Explorer** tab. You can see the graph for the selected resource: The nodes and access paths vary depending on the [primary resource](https://help.zscaler.com/dspm/supported-data-stores) and its associated resources.
  - [AWS](https://help.zscaler.com/dspm/viewing-graph-aws-data-stores)
  - [Azure](https://help.zscaler.com/dspm/viewing-graph-azure-data-stores)
  - [GCP](https://help.zscaler.com/dspm/viewing-graph-gcp-data-stores)

[Image: Select the resource name]

The following table explains each node and the entity it represents on the graph.

| Node | Description |
| --- | --- |
| Account | The cloud account that contains the resource with sensitive data. |
| Applications | The applications that can access the resource. |
| Auto Scaling Group | A logical group that contains a collection of EC2 instances for automatic scaling and management. |
| AWS Elastic Network Interface (ENI) | A networking component (virtual card interface) attached to an EC2 instance for enabling network connectivity. |
| External | The external entities that can access the resource. |
| Federated | The federated entities that can access the resource. |
| Group | A logical container that consists of several users who are assigned the same role. |
| Instance Profile | A container for an IAM role that is used to pass the role information to an EC2 instance when the instance starts. |
| Internet Gateway | A virtual private cloud (VPC) component that enables resources in your public subnets (e.g., EC2 instances) to connect to the internet. |
| Load Balancer | Distributes incoming application traffic across multiple targets, such as EC2 instances, in multiple availability zones. |
| Managed Identity | The managed entities that can access the resource. |
| Network Access Control List (ACL) | The ACL defines which accounts and groups are granted access to S3 buckets along with the type of access. |
| Organization | The onboarded AWS organization comprising all the resources that are scanned by DSPM. |
| Organization Unit | A logical unit comprising a list of cloud accounts in a tenant. |
| Policy | A security control in the DSPM Admin Portal that detects misconfigurations or vulnerabilities in the cloud resources and triggers an alert. |
| Public Internet | This node is shown when DSPM detects that a resource containing sensitive data is publicly exposed and is accessed by unauthorized users. |
| Roles | The roles assigned to users and groups to perform various actions in the DSPM Admin Portal. |
| Route Table | A set of rules called routes that determine the destination of the network traffic from your subnet or gateway. |
| Security Group | Controls the inbound and outbound traffic for the cloud resource. |
| Services | The services (e.g., Azure App Services) that have access to the resource. |
| Service Accounts | The service accounts that have access to the resource. |
| Subnet | A range of IP addresses used to launch the resources in your VPC. You can connect a subnet to the internet, other VPCs, etc., and route traffic to and from your subnets using route tables. |
| [Unmanaged Database](https://help.zscaler.com/dspm/about-unmanaged-database) | Databases that are deployed on virtual machines in the cloud. DSPM scans these databases for sensitive data. |
| User | The IAM users who can access the resource. |
| Web Application Firewall (WAF) | Allows to monitor the HTTP(S) requests and control access to your data. |

- **Public Exposure Path**: Represents the misconfigurations in the primary and associated resources that could allow adversaries to gain initial access or perform lateral movement.
- **Access Path**: Represents the mapping of identity and access management (IAM) roles, users, external entities, federated identities, and services that have permissions to access the resource.
- **Known Vulnerabilities Path**: Represents the common vulnerabilities and exposures (CVEs) found in the resource.
- **Sensitive Data Path**: Represents the files that contain [sensitive data](https://help.zscaler.com/dspm/viewing-sensitive-data-details).
- **Associated Resources Path**: Represents all the resources associated with the primary resource (e.g., all the EBS volumes in an EC2 instance). You can also see the details of resources that don't contain any sensitive data and the resources that are not scanned by DSPM.
- **Malware Path**: Represents the malware found in the resource.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-roles-and-templates","lastmod":"2026-04-24T22:29Z","nid":"1518586"} -->
## Viewing the Roles and Templates

- Source: https://help.zscaler.com/dspm/viewing-roles-and-templates
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Cloud Accounts Onboarding > Cloud Account Management > Viewing the Roles and Templates
- Last modified: 2026-04-24T22:29Z
- Summary: Information on how to view roles and download onboarding templates in the Zscaler Admin Console.

DSPM creates IAM or custom roles in the cloud accounts, and these roles are assigned with various permissions that allow DSPM to connect to the cloud account and discover resources for data scanning. You can view these roles on the Roles and Templates tab, copy them and verify if they are the same in the respective cloud account, in case of any [issues](https://help.zscaler.com/dspm/viewing-onboarding-issues).

To view the roles or download the templates:

1. Go to **Policies** > **Common Configuration** > **DSPM** > **Cloud Accounts**.
2. Select the cloud account from the list.
3. Select the **Roles and Templates** tab. You can view the templates and the corresponding roles created for the organization. The file name of the downloaded template includes the template version number.
  - AWS
  - Microsoft Azure
  - GCP

## Availability of New Templates

DSPM releases new templates that include additional functionalities for scanning and collecting metadata. A notification banner is displayed on the **Roles and Templates** tab whenever a new template is available.

See image.

You can download the template to review the changes. If you click **Acknowledge**, the new template overrides the existing template, and DSPM validations and health checks are then performed on the new template upon deployment. If not acknowledged, DSPM validations and health checks continue on the existing template.

- You can acknowledge the template only if you are assigned an [Administrator role](https://help.zscaler.com/dspm/predefined-roles-and-permissions). If you have a role with the View Cloud Accounts permission, you can only view or download the templates, but you cannot acknowledge them.
- If you acknowledge the new template for one organization or tenant, it is updated for all the onboarded organizations or tenants.

## Redeploying Templates

You can redeploy the DSPM templates in the following scenarios:

- Upgrade to a new template
- Experience issues with custom roles that are created during onboarding
- Modify or delete a role

Download either the CloudFormation or Terraform template to your local system, extract the ZIP file, and run it on the AWS organization.

- Tree Discovery
- Orchestrator
- Monitoring Scope
- Evidence
- Data Events

You can view and copy the external ID, an optional identifier that is attached to the role. AWS recognizes this external ID and allows DSPM to access the AWS resources. To learn more, refer to the [AWS documentation](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_common-scenarios_third-party.html).

See image.

Download the following templates to your local system, extract the ZIP file, and run it on the Azure tenant.

- Tree Discovery
- Azure Onboarding
- Evidence

See image.

Download the following templates to your local system, extract the ZIP file, and run it on the GCP organization.

- Tree Discovery
- GCP Onboarding

See image.

[Image: View the Azure Roles and Templates]

[Image: New template notification]

[Image: View the AWS Roles and Templates]

[Image: View the GCP Roles and Templates]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-saas-overview-dashboard","lastmod":"2026-08-12T21:25Z","nid":"1542064"} -->
## Viewing the SaaS Overview Dashboard

- Source: https://help.zscaler.com/dspm/viewing-saas-overview-dashboard
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Dashboards > Viewing the SaaS Overview Dashboard
- Last modified: 2026-08-12T21:25Z
- Summary: Information on the SaaS Overview dashboard in DSPM.

Software as a Service (SaaS) applications often handle sensitive data, including customer information, financial data, and intellectual property. This data needs to be protected from unauthorized access, breaches, and data loss. Identifying and addressing vulnerabilities within SaaS applications reduces the risk of security incidents, such as data breaches, malware infections, or insider threats.

SaaS incidents refer to data loss events associated with SaaS applications, categorized into three types: Private (incidents that compromise sensitive information), Internal (incidents that represent a data security breach within your organization), and External (incidents that originated outside your organization). The incident counts are displayed for applications like Gmail, ServiceNow, Google Drive, etc.

DSPM systematically scans the [SaaS applications](https://help.zscaler.com/dspm/about-saas-applications) within your organization and visually displays the detected incidents on the SaaS Overview dashboard, showing their distribution and identifying affected applications and users. You can view the total number of SaaS incidents, list of user accounts with the most number of SaaS incidents, and SaaS applications containing sensitive data that are publicly exposed to the internet. This information allows you to identify the overall security risk and take the necessary remediation steps immediately to protect the applications and maintain a strong security posture.

To view the SaaS Overview dashboard:

1. Go to **Analytics** > **Data Security** >**DSPM** > **SaaS Overview**.
2. On the **SaaS Overview Dashboard** page, you can view the following widgets: See image.
  - SaaS Incidents
  - Top Users with SaaS Incidents
  - SaaS Application with Data Exposure

[Image: The SaaS Overview dashboard shows SaaS incidents, top users with SaaS incidents, and SaaS apps with data exposure]

This bar chart shows the data loss incidents from SaaS applications in your organization. Hover over the chart to see more details about the incidents:

- **Private**: Incidents that compromise sensitive information.
- **Internal**: Incidents that represent a data security breach within your organization.
- **External**: Incidents that originated outside your organization.

This bar chart shows the users who experienced the most data loss incidents from SaaS applications. Hover over the chart to see more details about the incidents for each user:

- **Private**: Incidents that compromise sensitive information.
- **Internal**: Incidents that represent a data security breach within your organization.
- **External**: Incidents that originated outside your organization.

This table shows details about the SaaS applications with the most data exposure in your organization.

- **Application Tenant Name**: The application responsible for a data loss incident.
- **Total Incidents**: The total number of data loss incidents from this application.
- **Private**: Incidents from this application that compromise sensitive information.
- **Internal**: Incidents from this application that represent a data security breach within your organization.
- **External**: Incidents from this application that originated outside your organization.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-sensitive-data-details","lastmod":"2026-08-13T03:31Z","nid":"1478121"} -->
## Viewing the Sensitive Data Details

- Source: https://help.zscaler.com/dspm/viewing-sensitive-data-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Resource Inventory > Resource Details > Viewing the Sensitive Data Details
- Last modified: 2026-08-13T03:31Z
- Summary: Information about cloud resources containing sensitive data that is detected by DSPM.

You can view additional details of sensitive data found in the data stores.

To view the sensitive data:

1. Go to **Analytics**> **Data Security** > **DSPM**> **Resource Inventory**.
2. On the **Resource Inventory** page, click any **Resource Name** to view the resource details. See image.
3. On the resource's details page, select the **Sensitive Data** tab to view the following details: See image. Some columns are shown for both files and tables and some are specific to files or tables:
  - **Matched Files**/**Matched Tables**: The total number of files or tables that matched the DLP engines.
  - **Triggers**: The total number of records that matched the DLP engines.
  - **File Name**/**Table Name**: The name of the file or table containing sensitive data. Click to view the **Details**, **Data Duplications**, and **Evidence** tabs and [apply Microsoft Information Protection (MIP) label](https://help.zscaler.com/dspm/viewing-and-managing-data-inventory-details#manual-label).
  - Common for Both Types
  - File
  - Table

- **Resource Type**: The type of resource (e.g., AWS Bedrock Agent, Azure AI Foundry Hub) that contains the file.
- **Resource ID**: The unique identifier of the resource.
- **File Path**: The file's location.
- **File Type**: The file extension (e.g., .txt, .doc).
- **File Size**: The size of the file.
- **Document Types**: The document type (e.g, legal, medical) associated with the file.
- **Document Categories**: The document categories (e.g, DMV, Financial, HR) to which the file belongs.
- **Label**: The [sensitivity label](https://help.zscaler.com/dspm/about-action-rules) (e.g., [Microsoft Purview Information Protection label](https://learn.microsoft.com/en-us/graph/security-information-protection-overview)) applied to the file.
- **Hash**: The hash value that uniquely identifies the file. Identical files have the same hash value.

- **DLP Engines**: The DLP engines that matched the sensitive data.
- **DLP Dictionaries**: The DLP dictionaries within the matched DLP engines.
- **Last Completed Scan**: The date and time the file or table was last scanned.
- **Created Date**: The date and time the file or table was created.

**Column**: The name of the database table column containing sensitive data.

[Image: Sensitive data discovered in an EBS volume]

[Image: Click the resource name to view the sensitive data details]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-timeline-details-resources","lastmod":"2026-06-30T04:25Z","nid":"1529338"} -->
## Viewing the Timeline Details for Resources

- Source: https://help.zscaler.com/dspm/viewing-timeline-details-resources
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Resource Inventory > Resource Details > Viewing the Timeline Details for Resources
- Last modified: 2026-06-30T04:25Z
- Summary: Information on the resource timeline, which provides a consolidated view of events related to configuration and management changes made to a resource for a given period of time.

The Timeline tab on the [Resource Inventory page](https://help.zscaler.com/dspm/about-resource-inventory) provides a consolidated view of events related to configuration changes made to a resource for a given period of time. DSPM provides complete visibility into the changes made to the resource, the entity that made the change, and the time at which it occurred. This allows you to investigate and remediate the issue and maintain the security posture.

The resource timeline is limited to a maximum of 30 days.

To view the events related to a resource:

1. Go to **Analytics**> **Data Security**> **DSPM**> **Resource Inventory**.
2. Click any Resource Name to view the resource's details page. See image.
3. In the resource's details page, select the **Timeline**tab. The following information is displayed: See image.
  - **Events Over Time**: A timeline chart that shows events for the selected time range.
  - **Events Found**: A list of all the events that occurred in the selected time range with the following details:
    - **Event Time**: The date and time when the event occurred.
    - **Details**:
      - **Principal:**The identity that accessed the resource.
      - **Principal Type**: The principal type (application, managed identity, etc.) that accessed the resource.
      - **Event Name**: The name of the event that occurred.
      - **IP Address**: The IP address of the entity that accessed the resource.

[Image: Timeline tab provides a consolidated view of events related to configuration changes made to a resource.]

[Image: Shows the Resource Inventory page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-user-access-path","lastmod":"2026-04-22T21:06Z","nid":"1482986"} -->
## Viewing the User Access Path

- Source: https://help.zscaler.com/dspm/viewing-user-access-path
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Graphs > Viewing the User Access Path
- Last modified: 2026-04-22T21:06Z
- Summary: Information about the graph that depicts the user access to cloud resources.

The user access path includes details of identity and access management (IAM) entities that can access the primary resource containing sensitive data. The nodes in the graph are interactive. You can click each node to view granular details of each entity.

To view the access path for an IAM entity:

1. Go to **Analytics**> **Data Security** > **DSPM**> **Resource Inventory**.
2. Click any **Resource Name** to view the Resource Inventory graph. You can view the user access path for the following cloud environments:
  - AWS
  - Azure
  - GCP

The Resource Inventory graph displays the sensitive records found in the primary resource along with external, federated, roles, services, and users that have access to this primary resource.
See image.

Click the IAM nodes to see:

- **ARN**: The Amazon Resource Name (ARN) assigned to the user. ARN is a string that uniquely identifies an Amazon resource.
- **Entity Type**: The type of IAM entity.
- **Account ID**: The account ID of the entity.
- **Access Level**: The permissions assigned to the entity.
- **Last Activity**: The date and time the IAM entity last accessed the primary resource. See image.

Click **ARN** to view the additional details.
See image.

Click **Show Access Path** to view another graph.
See image.
Another graph displays the roles and permissions assigned to the entity, allowing it to access the primary resource.
See image.

[Image: View the user entity details]

[Image: Click the ARN to view additional details]

[Image: Shows the ARN details.]

[Image: Shows the user access path]

[Image: View the Resource Inventory graph]

The Resource Inventory graph displays the sensitive records found in the primary resource along with users, external, applications, managed identity, and services that have access to this primary resource.
See image.

Click the IAM nodes to see:

- **Entity Name**: The name of the Azure entity.
- **Entity Type**: The type of IAM entity.
- **Access Level**: The permissions assigned to the entity.
- **Last Activity**: The date and time the IAM entity last accessed the primary resource. See image.

Click **Entity Name**to view the additional details.
See image.

Click **Show Access Path** to view another graph.
See image.
Another graph displays the roles and permissions assigned to the entity, allowing it to access the primary resource.
See image.

[Image: Viewing the Resource Inventory graph]

[Image: Shows the entity names.]

[Image: Clicking entity name to view additional details]

[Image: Shows the entity details.]

[Image: Shows the user access path.]

The Resource Inventory graph displays the sensitive records found in the primary resource along with users, external, service accounts, domains, and services that have access to this primary resource.
See image.

Click the IAM nodes to see:

- **Entity Name**: The name of the Azure entity.
- **Entity Type**: The type of IAM entity.
- **Access Level**: The permissions assigned to the entity.
- **Last Activity**: The date and time the IAM entity last accessed the primary resource. See image.

Click **Entity Name**to view the additional details.
See image.

Click **Show Access Path** to view another graph.
See image.
Another graph displays the roles and permissions assigned to the entity, allowing it to access the primary resource.
See image.

[Image: View Resource Inventory graph]

[Image: View the user entity details]

[Image: Entity name details]

[Image: Viewing entity details]

[Image: View user access path]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/viewing-vulnerability-details","lastmod":"2026-06-30T04:22Z","nid":"1478116"} -->
## Viewing the Vulnerability Details

- Source: https://help.zscaler.com/dspm/viewing-vulnerability-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Resource Inventory > Resource Details > Viewing the Vulnerability Details
- Last modified: 2026-06-30T04:22Z
- Summary: Information about viewing the vulnerabilities associated with the cloud resources in the Zscaler Admin Console.

A security vulnerability refers to a system misconfiguration, an error in the software code, etc., that increases the risk of a data breach. Some of the common types of vulnerabilities include publicly exposed resources containing sensitive data, insufficient monitoring and logs, lack of data encryption, misconfigured system components, weak credentials, and inadequate authentication.

DSPM detects vulnerabilities in the Amazon EC2 instances and virtual machines and displays the details on the [Resource Inventory page](https://help.zscaler.com/dspm/about-resource-inventory).

To view the vulnerability details:

1. Go to **Analytics**> **Data Security**> **DSPM**> **Resource Inventory**.
2. Click any **Resource Name** to view the resource's details page. See image.
3. In the resource's details page, select the **Vulnerabilities**tab. For each package, you can see:
  - **Package Name**: The name of the package.
  - **Package Version**: The version number of the package.
  - **File Path**: The path where the file is stored in the resource.
  - **Vulnerabilities**: Whether vulnerabilities are present or not in the package.
  - **Severity Count**: The number of vulnerabilities detected in the package along with the severity level (Critical, High, Medium, Low).
4. Click the **CVE** tab to view the CVE details. See image.

[Image: The vulnerabilities detected in the resource]

[Image: The CVEs that match the vulnerability]

[Image: Shows the Resource Inventory page.]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/dspm/what-data-security-posture-management","lastmod":"2026-04-01T01:22Z","nid":"1474181"} -->
## What is Data Security Posture Management?

- Source: https://help.zscaler.com/dspm/what-data-security-posture-management
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > What is Data Security Posture Management?
- Last modified: 2026-04-01T01:22Z
- Summary: Information about the DSPM product and its key benefits.

Zscaler Data Security Posture Management (DSPM) is an AI-powered solution to protect your organization’s data stored on-premises and in the cloud against data theft, misuse, or loss by continuously scanning the data for numerous potential misconfigurations, vulnerabilities, and permissions that might contribute to attack vectors. DSPM provides detailed insights into where this sensitive data resides in your cloud environment, identifies and classifies sensitive data, detects duplicate data and misconfigurations in data stores containing sensitive data, and contextualizes data exposure and security posture.

DSPM aggregates security data and prioritizes risk based on severity, coupled with step-by-step remediation guidance and configurable, near-real-time alerts and notifications. Knowing the risk in full context enables security teams to resolve potential risks or threats. DSPM provides enriched and interactive dashboards, graphs, and reports that enable security teams to quickly evaluate the severity of the risk, remediate the issues, and prevent data breaches at scale. DSPM is crucial for an effective cloud data security posture management.

DSPM is robust and extends data security for cloud resources and on-premises data centers. It provides complete visibility into data flows, blocks unsanctioned apps, and eliminates risks, enabling granular control over sensitive data. This helps prevent accidental exposure, overly permissive or unauthorized access, and potential compliance violations.

## Key Features and Benefits

DSPM includes the following key features and benefits:

- **Monitor data**: Continuous data monitoring for any potential risks or vulnerabilities.
- **Secure sensitive data**: Discover sensitive data and its access level in your cloud environments, perform AI-powered data classification based on severity, threat category, and identify areas of exposure.
- **Policy and compliance**: Implement security policies to comply with stringent security and privacy regulations (e.g., GDPR, NIST).
- **Incident response**: Expedite identification, investigation, containment, and recovery in the event of a security incident.
- **Data duplication**: Detect duplicate files at multiple locations to manage redundant data, reduce attack surface, and limit exposure to unauthorized users.

## How Does DSPM Work?

DSPM comprises the following features to achieve continuous security, compliance, and governance for data in your cloud infrastructure:

- **Scan and monitor cloud and on-premises resources**: Works with minimal access to resources in cloud environments (AWS, Azure, and GCP), on-premises data centers, and discovers sensitive data.
- **Agentless deep scanning**: Uses an agentless approach to continuously scan resources (cloud storage, databases, virtual machines, on-premises data centers).
- **Detect data misconfigurations**: Scans data for any vulnerabilities such as data exposure, publicly exposed sensitive data, over-privileged access to data without proper role-based access control (RBAC), sensitive data exposed to unmanaged services and models, etc.
- **Apply security policies**: Identifies security policy violations at the resource and data level. It also provides a complete mapping of security policies within various compliance frameworks.
- **Dashboard, graphs, and reports**: Intuitive dashboards, graphs, and analytical reports provide detailed insights into the risks and issues associated with sensitive data.
- **Remediate issues**: Provides remediation for every issue, enabling you to immediately take action and maintain a strong cloud data security posture for your organization.

[Image: DSPM architecture]
<!-- /ZS-ARTICLE -->
