# Zscaler Help — Data Security — DSPM (part 2)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-08-10 01:47 UTC
Articles in this file: 10

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

<!-- ZS-ARTICLE {"url":"/dspm/viewing-sensitive-data-details","lastmod":"2026-06-30T22:58Z","nid":"1478121"} -->
## Viewing the Sensitive Data Details

- Source: https://help.zscaler.com/dspm/viewing-sensitive-data-details
- Product: Data Security Posture Management (DSPM)
- Path: Data Security Posture Management (DSPM) Help > Resource Inventory > Resource Details > Viewing the Sensitive Data Details
- Last modified: 2026-06-30T22:58Z
- Summary: Information about cloud resources containing sensitive data that is detected by DSPM.

You can view additional details of sensitive data found in the data stores.

To view the sensitive data:

1. Go to **Analytics**> **Data Security** > **DSPM**> **Resource Inventory**.
2. On the **Resource Inventory** page, click any **Resource Name** to view the resource details. See image.
3. On the resource's details page, select the **Sensitive Data** tab to view the following details: Some columns are shown for both files and tables and some are specific to files or tables: See image.
  - **Matched Files**/**Matched Tables**: The total number of files or tables that matched the DLP engines.
  - **Triggers**: The total number of records that matched the DLP engines.
  - **File Name**/**Table Name**: The name of the file or table containing sensitive data. Click to view the **Details**, **Data Duplications**, and **Evidence** tabs in a drawer.
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
