# Zscaler Help — Risk & Exposure Management (part 3)

Source: https://help.zscaler.com / help.zscaler.com
Generated: 2026-07-30 13:36 UTC
Articles in this file: 67

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

<!-- ZS-ARTICLE {"url":"/uvm/configuring-wiz-connector","lastmod":"2026-03-26T12:29Z","nid":"1530953"} -->
## Configuring the Wiz Connector

- Source: https://help.zscaler.com/uvm/configuring-wiz-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Wiz Connector
- Last modified: 2026-03-26T12:29Z
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

<!-- ZS-ARTICLE {"url":"/uvm/configuring-zia-devices-and-users-connector","lastmod":"2026-03-26T12:29Z","nid":"1530878"} -->
## Configuring the ZIA Devices and Users Connector

- Source: https://help.zscaler.com/uvm/configuring-zia-devices-and-users-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the ZIA Devices and Users Connector
- Last modified: 2026-03-26T12:29Z
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

<!-- ZS-ARTICLE {"url":"/uvm/configuring-zscaler-client-connector-devices-connector","lastmod":"2026-03-26T12:29Z","nid":"1530862"} -->
## Configuring the Zscaler Client Connector Devices Connector

- Source: https://help.zscaler.com/uvm/configuring-zscaler-client-connector-devices-connector
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Configuring the Zscaler Client Connector Devices Connector
- Last modified: 2026-03-26T12:29Z
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

<!-- ZS-ARTICLE {"url":"/uvm/configuring-zscaler-secops-platform-gateway","lastmod":"2025-12-11T07:51Z","nid":"1528011"} -->
## Configuring the Zscaler SecOps Platform Gateway

- Source: https://help.zscaler.com/uvm/configuring-zscaler-secops-platform-gateway
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > Admin Configuration and Deployment > Configuring the Zscaler SecOps Platform Gateway
- Last modified: 2025-12-11T07:51Z
- Summary: How to configure the Zscaler SecOps platform gateway to connect on-premises sources and outegrations.

The Zscaler Security Operations (SecOps) platform supports connecting on-premises outegrations and data sources through the Zscaler gateway at the account level, allowing for seamless onboarding of customers with on-premises installations for their vendors. In this guide, the resource you intend to connect is referred to as the on-premises service. The objective is to make the on-premises service accessible through the SecOps platform. This article outlines a recommended setup using a virtual machine (VM).

The SecOps gateway setup process begins with creating a VM and generating a public SSH key. You then share the public key along with the VM's public IP address and gateway IP addresses with Zscaler. Using this information, Zscaler creates a representative VM instance and notifies you when the setup is complete. You then establish a reverse SSH tunnel and inform Zscaler. Upon confirmation, Zscaler completes the gateway configuration and notifies you. At this point, the Zscaler gateway is fully operational and ready for use.

[Image: Architecture of the Zscaler gateway configuration]

You should receive this guide and the zscaler-gateway.sh script file after communication with Zscaler Support. If you have not received the script, contact Zscaler Support or your Zscaler Account team for assistance with setting up a gateway for your account.

## Using the Zscaler Gateway

The Zscaler gateway is used in one of the following configuration processes:

- When [creating a data source](https://help.zscaler.com/uvm/creating-data-sources), select the relevant gateway in the Retrieval section of the source setup page.
- When [configuring the Jira Data Center outegration](https://help.zscaler.com/uvm/configuring-jira-outegration), select the relevant gateway in the Details section.

## Prerequisites

For this setup, you must create or use a VM dedicated to running a reverse SSH tunnel. Do not use your local computer.

Ensure that you have the following requirements for the VM:

- Connectivity:Deploy the instance in a private subnet with a dedicated virtual network interface (vNIC) for secure communication within the private network. Ensure that the on-premises service is accessible from the VM you intend to use. It is crucial to test this connectivity before proceeding.
- Operating System:Your VM should be set up with one of the supported Linuxdistributions:
  - Ubuntu
  - Red Hat Enterprise Linux (RHEL)
  - Centos
  - Amazon-Linux
- Instance Specification:
  - Use small instance types with at least 2 vCPUs and 4 GB of RAM.
  - The instance size should be flexible and might vary based on traffic patterns. Ensure the chosen instance type supports scalability (both vertical and horizontal scaling) to handle workload fluctuations.
  - Select instance resources based on expected traffic volume, with the ability to adjust instance sizing dynamically depending on actual usage.
- Network Configuration: The VM must be configured to listen on port 22 for incoming SSH connections.

## Configuring the Zscaler Gateway

This guide accompanies the zscaler-gateway.sh script. The script first checks for an existing SSH key pair. If none are found, it creates one and displays the public key for you to send to Zscaler. Next, it verifies whether autossh is installed and attempts to install it if necessary. Finally, the script configures a service to establish the reverse SSH tunnel.

- Step 1: Generate the SSH Key Pair
- Step 2: Request the VM Instance Creation
- Step 3: Zscaler Creates a Representative Instance for the Gateway
- Step 4: Rerun the Script to Establish the Reverse SSH Tunnel
- Step 5: Zscaler Sets Up the Gateway in the Platform

The first step in configuring the gateway involves running the script, which installs autossh and generates the public key to send to Zscaler.

### Prerequisite

Before running the script, make sure your user account has root privileges, but is not logged in as the root user. You can verify this by running the `sudo -v` command. If you encounter an error such as `Sorry, user <username> may not run sudo on <hostname>`, switch to a different user with root access.

### Copying the Script

To copy the script to your server, you can either use the Secure Copy Protocol (`scp`) command or copy the script manually.

- Copy the Script Using the scp Command (Recommended)
- Copy the Script Manually

### Running the Script

After transferring the script content either via `scp` or manual copy, run the script using the following command, as a user with root privileges (but not as the root user):

```
bash zscaler-gateway.sh
```

After running the script:

1. Select **No** if prompted to create an SSH tunnel to the Zscaler gateway; the tunnel is established later.
2. Copy and save the newly generated public key displayed in yellow between the `Start copy` and `End copy` prompts to send to Zscaler in the next step.
3. Ensure that the autossh package was installed, as this is essential for establishing the tunnel. If the installation fails, manually install autossh using the link provided in the script output, and then rerun the script.

The tunnel is not active at this stage.

Copy the script to the server on which you want to configure the tunnel, using the `scp` command:

```
scp /
<Path>
/zscaler-gateway.sh
<User>
@
<Server IP>
:/
<Destination Path>
/
```

Replace the following variables:

- Replace `<Path>` with the local path to the script.
- Replace `<User>` with your username.
- Replace `<Server IP>` with your server IP address.
- Replace `<Destination Path>` with the destination directory on the server.

If `scp` is unavailable, you can manually create a new file on the server and paste the script contents. Manual copying can be prone to errors, so use this method with caution and only when necessary.

To copy the script manually:

1. Connect to the server where you want to configure the tunnel.
2. Create a new file using the following command: `touch zscaler-gateway.sh`
3. Open the file in your text editor. The following instructions are for Vim:
  1. Run the following command: vim zscaler-gateway.sh
  2. Press `i` to enter edit mode.
  3. Copy and paste the content of the downloaded script into the new file.
  4. Verify that the file starts with `#!/bin/bash` and ends with `# End of file`.
4. To save and exit edit mode, press `Esc`, then enter `:wq` and press `Enter`.

After you create the SSH key pair, [submit a support ticket](https://help.zscaler.com/uvm/accessing-and-navigating-security-operations-secops-platform#support-portal) to Zscaler Support to provision a VM with the following details:

- The Public SSH key that was previously generated.
- Gateway IP address(es):The public IP address that the VM will use to access the external network.
  - If your VM is configured with a public IP address, include that IP address.
  - If your VM only has a private IP address, provide the full list of possible gateway IP addresses.

This information is essential for allowlisting access to the Zscaler instance, ensuring that only your VM can connect.

In this step, your Zscaler Account team team provisions the representative instance based on the information you provided in Step 2. When the setup is complete, Zscaler Support contacts you to confirm that the tunnel is ready to be established.

If the your Zscaler Account team team encounters issues in this step, you might be asked to provide logs from your server to assist with troubleshooting. You can do this by running the following command:

```
journalctl -u avalor-tunnel | tail -n
30
```

The number `30` indicates the number of lines to return. You can increase or decrease this number to adjust the number of log lines returned.

Rerun the script from Step 1 (provided again here). Select **Yes**when prompted to create the tunnel (you can skip displaying the existing key).

```
bash zscaler-gateway.sh
```

When complete, notify Zscaler Support by [submitting a support ticket](https://help.zscaler.com/uvm/accessing-and-navigating-security-operations-secops-platform#support-portal).

After you inform Zscaler Support that the tunnel was successfully established, your Zscaler Account team proceeds with configuring the gateway on the platform. You receive confirmation from Zscaler Support when the setup is complete.

## Troubleshooting

During the Zscaler gateway setup process, you might encounter an error message such as `May 06 08:53:28 xxxxxxxx autossh[392026]: Host key verification failed`. This typically means that the remote server (Zscaler gateway) has not yet been authorized for its initial SSH connection.

To resolve the authorization issue:

1. Determine which user created the service under `/etc/systemd/system/`. In the example below, the user is `root`. `ls -la /etc/systemd/system/zscaler-gateway -rw-r--r-- 1 root root 0 May 7 14:41 zscaler-gateway`
2. Authorize the server from the appropriate user (e.g., `root`) by running the following command. The variables needed for this command are defined in the script provided by Zscaler Support. `ssh -i $<Key Path> -o StrictHostKeyChecking=no $<Gateway User>@$<Gateway Public DNS Name> "exit"`
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/connecting-anysource","lastmod":"2026-07-22T07:17Z","nid":"1528226"} -->
## Connecting AnySource

- Source: https://help.zscaler.com/uvm/connecting-anysource
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Connecting AnySource
- Last modified: 2026-07-22T07:17Z
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

<!-- ZS-ARTICLE {"url":"/uvm/connecting-anysource-using-aws-s3","lastmod":"2026-07-22T07:17Z","nid":"1528386"} -->
## Connecting AnySource Using AWS S3

- Source: https://help.zscaler.com/uvm/connecting-anysource-using-aws-s3
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Data Sources & Outegration Configuration Guides > Source Configuration Guides > Connecting AnySource Using AWS S3
- Last modified: 2026-07-22T07:17Z
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

<!-- ZS-ARTICLE {"url":"/uvm/creating-custom-roles","lastmod":"2026-05-22T07:06Z","nid":"1527841"} -->
## Creating Custom Roles

- Source: https://help.zscaler.com/uvm/creating-custom-roles
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > User Management > Creating Custom Roles
- Last modified: 2026-05-22T07:06Z
- Summary: How to create custom role permissions in the Zscaler SecOps platform.

User roles control access to features and actions within the platform. After [creating users](https://help.zscaler.com/uvm/creating-managing-users) in your account, you can [assign roles](https://help.zscaler.com/uvm/assigning-roles-users) to define their access and permissions. You can choose from [predefined system roles](https://help.zscaler.com/uvm/understanding-system-roles), or the account admin can create and assign custom roles. Custom roles are configured to reflect your internal policies or workflows.

When new platform features are released, related permissions must be manually added to your custom roles.

To create a custom role:

1. Click the **Profile** menu on the top right of the page.
2. Go to **Account Settings**> **Permissions**> **Roles**.
3. On the **Roles**page, you can create a custom role in the following ways:
  - Click **Create**.
  - Duplicate an existing role by hovering over the role in the list and clicking the **Duplicate** icon (), or selecting the role and clicking **Duplicate**. The duplicated role is added to the list of roles. You can't duplicate the system Admin role.
4. On the **New Role** page:
  1. **Role Name**: Enter a unique name for the role.
  2. **Resource**: Select the relevant permissions (e.g., **Read**, **Create**, **Edit**, **Delete**, **Audit**) for each resource. To apply the same permission to all items in a category, select the checkbox at the top of the category. Selecting a higher-level permission (e.g., **Edit**) automatically enables required lower-level permissions (e.g., **Read**). See image.
5. Click **Next**. The **Add Users** window appears.
6. Select the users you want to assign to the role.
7. Click **Add**.
8. You can continue to manage users after clicking **Add**:
  - To add more users, click **Add Users**.
  - To remove users, select them and click **Remove From Role**.
9. Click **Finish**.

After changes are saved, the selected users' roles are updated immediately.

## Resources

Role permissions are organized into distinct categories, each representing a group of related system resources. Within each category, you can select permissions (i.e., Read, Create, Edit, Delete, and Audit) for custom roles to control access and operations on those resources. These assignments form a permissions matrix for the custom roles that are subsequently assigned to users.

### Platform

The following table outlines key platform resources and the specific types of access that can be granted to users through custom roles:

| **Resource** | **Access Granted** |
| --- | --- |
| Model Management | Manage data model entities and their fields |
| Data Source Mapping | View and manage data source mapping |
| Data Source | View, create, and manage data sources |
| User Saved Views | View, create, and manage user-saved views across the platform |
| Outegrations | View and manage outegrations |
| Authentications | View and manage authentications |

### Explore

The following table outlines key analytics resources and the specific types of access that can be granted to users through custom roles:

| **Resource** | **Access Granted** |
| --- | --- |
| Search | View and search logs |
| Reports & Dashboards | View and manage custom dashboards and reports |

### Vulnerabilities App (UVM)

The following table outlines key resources of the UVM application and the specific types of access that can be granted to users through custom roles:

| **Resource** | **Access Granted** |
| --- | --- |
| Dashboards & Analytics | Built-in vulnerability dashboard and analytics |
| Tickets Page | View and manage tickets |
| Tickets Settings | Ticket scoring, grouping rules, and life-cycle customization |
| Findings Page | View and manage findings |
| Assets Page | View and manage assets |
| Exceptions Page | View and manage exceptions |
| Exceptions Settings | Exceptions form, assignment rules, and notification customization |

### Assets App (AEM)

The following table outlines key resources of the AEM application and the specific types of access that can be granted to users through custom roles:

| **Resource** | **Access Granted** |
| --- | --- |
| Violations Tickets | View and manage policy violations |
| Policy Violations | Violation ticket grouping rules and life-cycle customization |
| Policy Violations Outegrations | Trigger third-party outegrations from policy violations |
| Policy Rule | View and manage policy rules |
| Analytics & Insights | Built-in vulnerability dashboard and analytics |
| Assets Page | View and manage assets |
| Asset Outegrations | Trigger third-party outegrations from assets |

### SOC Workbench

The following table outlines key resources of the SOC Workbench application and the specific types of access that can be granted to users through custom roles:

| **Resource** | **Access Granted** |
| --- | --- |
| Dashboards & Analytics | View and manage built-in SOC Workbench dashboards and analytics |
| Incidents Page | View and manage incidents |
| Incident Settings | Incident scoring, grouping rules, and life-cycle customization |
| Alerts Page | View and manage alerts |

[Image: new role page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/creating-formatting-rules","lastmod":"2026-03-31T07:06Z","nid":"1531110"} -->
## Creating Formatting Rules

- Source: https://help.zscaler.com/uvm/creating-formatting-rules
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Creating Formatting Rules
- Last modified: 2026-03-31T07:06Z
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

<!-- ZS-ARTICLE {"url":"/uvm/creating-managing-content-permissions","lastmod":"2026-07-16T23:45Z","nid":"1527791"} -->
## Creating & Managing Content Permissions

- Source: https://help.zscaler.com/uvm/creating-managing-content-permissions
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > User Management > Creating & Managing Content Permissions
- Last modified: 2026-07-16T23:45Z
- Summary: How to create and manage content permission sets.

Admins can configure content permission sets using specific data attributes that can be assigned to users to specify the data they can access. While role permissions dictate the actions the user can perform (e.g., create a dashboard, merge tickets), content permissions limit the scope of data the user can view and is authorized to perform those actions on. For example, a user might be assigned a role allowing them to create reports, but limited by content permissions, they’ll only see data relating to their team (e.g., the team responsible for all Linux assets). To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles) and [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles).

After content permissions are created, you can assign permission sets to users. When assigned, access changes take effect on the user's next browser refresh. To learn more, see [Assigning Content Permissions](https://help.zscaler.com/uvm/assigning-content-permissions).

## Creating Content Permission Sets

To create a content permission set:

1. Click the **Profile** menu on the top right of the page.
2. Go to **Account Settings**> **Permissions**> **Content**.
3. Click **Create**. The **Create new permission set** drawer appears.
4. In the **Create new permission set** drawer:
  1. **Name**: Enter a unique name for the permission set.
  2. **Rule**: Define filter conditions for the permission set. Conditions define the data included in the permission set (i.e., what data should be included in the user's access).
    1. Select a field that the condition should be based on.
    2. Select an operator (e.g., **Equals**, **Contains**). Available operators vary depending on the selected field type, indicated to the left of the field name.
    3. Enter the value that the rule should apply to. See image. To apply content permissions to users, without having to manually enter unique identifiers, you can add a user's email as a dynamic value.
    4. (Optional) Use **AND**/**OR** logic to define compound rules.
      - **AND** applies the content permissions to users only if all conditions in the rule are met.
      - **OR** applies the content permissions to users if any of the conditions in the rule are met.
5. Click **Save** to create the permission set.

After saving the permission set, you can assign it to users on the User Management page. The newly assigned permission set is applied on the user's next browser refresh. To learn more, see [Assigning Content Permissions](https://help.zscaler.com/uvm/assigning-content-permissions).

### Scope of Content Permissions

Content permissions only apply to an account's mapped data, so they won't restrict access to unmapped data across the platform. For example, if a user's role grants them access to Logs or Data Mapping, their content permissions won't apply to those resources.

Additionally, content restrictions for scheduled reports and custom dashboard exports conform to the permission set of the user that created the report or the dashboard. The permission set of the receiver is not taken into account, so the delivered report can include data that the receiver can't access by their own permissions if they are also a user in the system.

### Setting a Dynamic Value

You can grant dynamic access based on the email address of the current user to avoid the need to create a separate condition for each individual user email address. For example, to grant access to all Linux Team users to the tickets assigned to each individual team member, you can add a condition to the Linux Team permission set, selecting the Assignee Email field and entering the dynamic `{user_email}` value.

To create dynamic rule conditions:

1. Click the **Profile** menu on the top right of the page.
2. Go to **Account Settings**> **Permissions**> **Content**.
3. Click **Create**. The **Create new permission set** drawer appears.
4. In the **Create new permission set** drawer:
  1. **Name**: Enter a unique name for the permission set.
  2. **Rule**: Define filter conditions for the permission set. Conditions define the data included in the permission set (i.e., what data should be included in the user's access).
    1. Select the relevant field from the list that contains your users' email address information (e.g., **Assignee Email**).
    2. Select the **Equals**operator. Only the Equals operator is supported, as the field's value should be a unique identifier.
    3. Enter the `{user_email}` value. See image.
    4. (Optional) Use **AND**/**OR** logic to define compound rules.
      - **AND** applies the content permissions to users only if all conditions in the rule are met.
      - **OR** applies the content permissions to users if any of the conditions in the rule are met.
5. Click **Save** to create the permission set.

After saving the permission set, you can assign it to users on the User Management page. The newly assigned permission set is applied on the user's next browser refresh. To learn more, see [Assigning Content Permissions](https://help.zscaler.com/uvm/assigning-content-permissions).

## Managing Content Permission Sets

After creating content permission sets, you can can manage and monitor them from the Content page. This page provides a centralized view of all configured content permission sets in your account.

Go to **Account Settings** > **Permissions**> **Content**to perform the following actions:

- Edit a Permission Set
- Delete a Permission Set

You can edit any of your existing permission sets on the Content Permissions page.

To edit a permission set:

1. Hover over the permission set you want to edit and click the **Edit**icon, or select the permission set and click **Edit**at the top of the page.
2. Adjust the permission set as needed.
3. Click **Save** to apply the changes to the set.

Adjustments to permission sets apply on the user's next login or on browser refresh.

You can delete a permission set that is no longer necessary in your account. This removes the data access restrictions from users that were assigned the deleted set.

This action is irreversible and will delete the permission set immediately.

To delete a permission set:

1. Select the permission sets you want to delete.
2. Click **Delete**. See image.

Adjustments to permission sets apply on the user's next login or on browser refresh.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/creating-managing-saved-views","lastmod":"2026-01-08T07:24Z","nid":"1528016"} -->
## Creating & Managing Saved Views

- Source: https://help.zscaler.com/uvm/creating-managing-saved-views
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Creating & Managing Saved Views
- Last modified: 2026-01-08T07:24Z
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

<!-- ZS-ARTICLE {"url":"/uvm/creating-managing-third-party-tickets","lastmod":"2025-08-25T22:49Z","nid":"1528131"} -->
## Creating & Managing Third-Party Tickets

- Source: https://help.zscaler.com/uvm/creating-managing-third-party-tickets
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Creating & Managing Third-Party Tickets
- Last modified: 2025-08-25T22:49Z
- Summary: How to create and manage third-party tickets in the SecOps platform applications.

Third-party outegrations allow organizations to create third-party tickets in external work management systems directly from the Zscaler Security Operations (SecOps) applications' tickets (e.g., Tickets in UVM, Violation Tickets in AEM) to facilitate a streamlined workflow. After a work management outegration is created and configured, third-party tickets can be dispatched and managed directly from the SecOps application's tickets to external systems (e.g., [Jira](https://help.zscaler.com/uvm/configuring-jira-outegration), [ServiceNow](https://help.zscaler.com/uvm/configuring-servicenow-outegration), and other supported outegrations). To learn more, see [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations).

## Creating Third-Party Tickets

To create a third-party ticket:

1. Go to the SecOps app's tickets page (e.g., [Tickets](https://help.zscaler.com/uvm/about-tickets-operational-view-uvm) in UVM, [Violation Tickets](https://help.zscaler.com/uvm/about-violation-tickets-operational-view-aem) in AEM).
2. Click the ticket you want to dispatch to the external system. The ticket drawer appears.
3. In the ticket drawer, click **Create**<Vendor> **Ticket**in the bottom-right corner. The SecOps ticket is dispatched to the external system, and is populated according to the mapping configuration you set up for the outegration.

To create multiple third-party tickets:

1. Select the checkboxes of the tickets you want to dispatch from the tickets table.
2. Click **Create 3rd Party Issue**. See image. The SecOps tickets are dispatched to the external system, and are populated according to the mapping configuration you set up for the outegration.

When dispatching multiple tickets in bulk, a separate external ticket is created for each SecOps ticket. The external ticket is created and populated based on the field mappings defined during the outegration setup. To learn more, see [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations).

SecOps tickets cannot be dispatched in bulk to different outegrations (e.g., Jira Bugs and Jira Tasks). Each ticket must be dispatched individually to ensure compatibility with their respective outegration configurations.

If two-way sync from the external system to the SecOps platform is configured, creating a third-party ticket triggers the sync and updates the SecOps ticket based on the outegration's settings set by your account admin.

## Managing Third-Party Tickets

You can manage the connection between third-party tickets and SecOps tickets, including unlinking or manually linking tickets. To filter your tickets by whether they're linked to third-party tickets, you can add the Ticket External Issue Type field to the filters and select the desired integrations, or Empty to display tickets with no linked third-party tickets. To learn more, see [Using Filters](https://help.zscaler.com/uvm/using-filters).

### Linking Existing Third-Party Tickets

To link an existing third-party ticket to a SecOps ticket:

1. Click the SecOps ticket you want to link. The ticket drawer opens.
2. In the ticket drawer, click the **Create <**Outegration**> Ticket** drop-down menu, and select **Manually connect <**Outegration**>**. See image.
3. Enter the third-party ticket ID. Enter only the third-party ticket ID (e.g., `INC0012345` for a ServiceNow ticket ID), not the ticket's URL or link.
4. Click **Apply**.

A third-party ticket can only be linked to one SecOps ticket.

If two-way sync from the external system to SecOps is configured, linking a third-party ticket triggers the sync and updates the SecOps ticket based on the outegration's settings set by your account admin. To learn more, see [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations).

### Unlinking Third-Party Tickets

To unlink a third-party ticket from the SecOps ticket:

1. Click the SecOps ticket you want to unlink. The ticket drawer opens.
2. Click the **Create <**Outegration**> Ticket** drop-down menu, and select **Unlink <**Outegration**>**. See image.

After the tickets are unlinked, updates between the third-party ticket and the SecOps ticket no longer sync.

[Image: bulk create third party ticket button]

[Image: create outegration dropdown menu open]

[Image: unlink outegration dropdown menu open]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/creating-managing-users","lastmod":"2025-11-26T20:06Z","nid":"1527796"} -->
## Creating & Managing Users

- Source: https://help.zscaler.com/uvm/creating-managing-users
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > User Management > Creating & Managing Users
- Last modified: 2025-11-26T20:06Z
- Summary: How to create and manage users in your SecOps account.

Admins can create and manage all users in your organization's Zscaler Security Operations (SecOps) account. Managing users includes defining their access and permissions by assigning role permissions (RBAC - Role-Based Access Control), optionally assigning content permissions (ABAC - Attribute-Based Access Control), and deactivating or deleting users.

Only users with admin privileges can create and manage users.

## Creating Users

Admins can create new users by filling out basic information and assigning a user role.

To create a user:

1. Click the **Profile** menu on the top right of the page.
2. Go to **Account Settings** > **User Management**.
3. Click **Create**. The **Create User** page appears.
4. On the **Create User** page:
  1. **Email**: Enter the user's organization email.
  2. **First Name**: Enter the user's first name.
  3. **Last Name**: Enter the user's last name.
  4. **Main Account**: (Optional) If you have more than one account, set the user's default account.
  5. **Role**: Select a role for the user. If you don’t, the user is assigned the default **No Access** role. To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles) and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users). See image.
5. Click **Save**. The new user appears in the User Management list.

## Managing Users

As an admin, you can manage your account's users (e.g., adding and deleting users, assigning user roles, and applying content permissions).

To manage users, go to **Account Settings** > **User Management** to access the list of existing users. On the User Management page, you can use the column sort and filters to navigate the users list.

- To sort the users list in ascending or descending order by a specific column,hover over the header of the column you want to sort by and click the arrow icon.
- To filter the users list, hover over theheader of the column you want to filter by and click the Filter icon. You can select the users you want to filter by, or create a Condition filter. See image.

When managing users, you can perform the following actions:

- Assign User Roles and Content Permissions
- Deactivate Users
- Delete Users

You can assign users roles and content permission sets to control user access to features and data.

Every new user is assigned a system role or a custom role when created, and you can change this role at any time. To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles), [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles), and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users).

In addition to roles, you can assign content permission sets to control the data users can access. For example, you might want a Vulnerabilities Admin to see only the data related to their team, rather than all data in the account. To learn more, see [Creating & Managing Content Permissions](https://help.zscaler.com/uvm/creating-managing-content-permissions).

Content permissions can only be assigned after a user is created and saved, not during the user creation process. To learn more, see [Assigning Content Permissions](https://help.zscaler.com/uvm/assigning-content-permissions).

You can deactivate users in your organization's account to revoke their access to the system, without removing their user information. You can only deactivate one user at a time.

Deactivating users doesn't remove data associated with the user's account (e.g., reports or dashboards they created).

To deactivate a user:

1. Click the **Profile**menu on the top right of the page.
2. Go to **Account Setting** > **User Management**.
3. Hover over the user you want to deactivate, and click the **Edit** icon. The **Edit User** page appears.
4. In the **Personal Info** section, disable**Active** to the right of the user’s email.
5. Click **Save**.

You can delete users from your organization's account to fully remove their information from the system and revoke their access to the system.

Deleting users doesn't remove data associated with the user's account (e.g., reports or dashboards they created).

To delete a single user:

1. Click the **Profile**menu on the top right of the page.
2. Go to **Account Setting** > **User Management**.
3. Hover over the user you want to delete, and click the **Delete**icon. The **Confirm Deletion** window appears.
4. Click **Delete**.

To delete multiple users:

1. Click the **Profile**menu on the top right of the page.
2. Go to **Account Setting** > **User Management**.
3. From the users list, select the users that you want to delete.
4. Click **Delete** at the top of the page. The **Confirm Deletion** window appears.
5. Click **Delete**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/creating-reports","lastmod":"2026-01-18T00:44Z","nid":"1527941"} -->
## Creating Reports

- Source: https://help.zscaler.com/uvm/creating-reports
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Reports > Creating Reports
- Last modified: 2026-01-18T00:44Z
- Summary: How to create and configure reports by adding measurements and dimensions and applying filters.

Reports provide a structured way to analyze and present data in the SecOps platform. You can create reports focused on key entities, such as tickets, assets, and findings, customizing them with relevant measurements and dimensions to highlight specific trends or areas of concern. Reports can be processed manually or scheduled for automatic delivery, helping you track progress, support audits, and keep stakeholders informed.

To create a report:

1. Go to **Explore** > **Reports**. A list of your private reports and public reports you have access to appears. See image.
2. Click **New**. A new report page appears.
3. In the top-left corner of the report, click the **Edit** icon. The **Edit Report Details** window appears.
4. In the **Edit Report Details** window:
  1. **Name**: Enter a unique name for the report.
  2. **Viewers**: Set the report's viewer access. To set the report as private and only visible to you, leave the **Viewers** drop-down menu blank.
    1. **Public**: Select this option to grant view access to all users in the account.
    2. **Selected Users**: Select users from the list to set them as viewers of the report.
  3. **Editors**: Set the report's editor access. To set the report as private and editable only by you, leave the **Editors** drop-down menu blank.
    1. **Public**: Select this option to grant edit access to all users in the account.
    2. **Selected Users:** Select users from the list to set them as editors of the report. Users designated as editors override the **Viewers** setting.
  4. **Pin to Apps**: (Optional) Select the application to which you want to pin the report. This allows you to easily find the report under **My Reports** on the app.
  5. Close the window.
5. Select the data type for your report.
  1. On the top of the page, set the date filter to either **Current** or **History**. See image.
  2. For historicalreports, click the date filter to specify the desired date range. You can select a preconfigured range, create a custom range, or set a dynamic range. To learn more about historical data and dynamic range filters, see [Using Filters](https://help.zscaler.com/uvm/filtering-operational-views). See image.
6. Customize the report's displayed data and filters.
  - Configure the data displayed in the report.
  - (Optional) Apply filters to the report.
7. Save the report in one of the following ways:
  - Click **Done**to save and close the report, redirecting you to the **Reports**page.
  - Click **Save**to save the report. If scheduled, the report exports at the set time.
  - From the **Save**drop-down menu, click **Save & Run**to save the report and immediately apply export settings, if configured.
  - From the **Save**drop-down menu, click **Save As New**to save your changes as a new report.

Your saved report is accessible on the Explore > Reports page, where it can be accessed, viewed, and exported with all applied configurations.

You can take the following actions with reports:

- View reports directly in the SecOps platform.
- [Manually download reports](https://help.zscaler.com/uvm/manually-exporting-reports) as a file.
- [Schedule reports](https://help.zscaler.com/uvm/scheduling-reports-export) for automated delivery.
- [Trigger report exports](https://help.zscaler.com/uvm/triggering-report-export-through-api) programmatically through an API.

[Image: explore > reports]

[Image: date filter current > history toggle]

1. Select the **Main Entity** type.
2. From the list on the left, select the dimensions and measurements to include in the report. To remove fields, either deselect them from the list or click the **Remove** icon next to the field name in the report. To learn more, see [Understanding Measurements & Dimensions](https://help.zscaler.com/uvm/understanding-measurements-dimensions). After adding measurements and dimensions to the report, changing the main entity type or the data type (i.e., from **Current** to **History**) resets and discards your selections.
3. (Optional) Adjust the table columns and sorting. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).

[Image: date filter for historical data]

You can add filters to your report to customize it to your needs. The available filters vary depending on the report's **Main Entity**.

1. Click **Add Filters**(if no filters are active) or **More**(if filters are already applied) at the top of the report and select the filter fields. You can use the search bar to refine the list.
2. Filter options change according to the filter type (e.g., string, number, or date). To learn more, see [Using Filters](https://help.zscaler.com/uvm/filtering-operational-views).
3. To remove a specific filter's values, click the filter field and click **Clear Selection**. See image.
4. To remove all filters, click **Clear Filters**. See image.

[Image: clear selection for a specific filter field]

**[Image: clear all filters]**

## Reporting Examples

The following examples illustrate different types of reports you can create to support operational visibility, risk management, and data validation. Each report highlights a specific use case and suggests relevant fields to include.

- Tickets Approaching SLA
- Ticket Remediation Rate by Application
- Asset Information and Tag Validation

To monitor tickets nearing their service level agreement (SLA) deadlines, create a report with the following fields:

- **Dimensions**: Ticket ID, Ticket Title, Ticket Severity
- **Measurements**: Ticket Severity Score, Ticket Status

Apply a filter on the **Ticket SLA** field and select the **Next 7 Days** option to focus on tickets at risk of breaching their SLA within the upcoming week.

See image.

[Image: next seven days dynamic filter for tickets approaching SLA example report]

To track the remediation rate across business applications, set up a report with the following fields:

- **Dimension**: Asset Business Application
- **Measurements**: New Tickets Last 7 Days, Undetected Tickets Last 7 Days, Closed Tickets Last 7 Days

This configuration provides insight into ticket volume and remediation activity for each application over the past week.

To validate asset data and ensure critical asset tags are accurate, set the **Main Entity** to **Asset** and include the following dimensions:

- Asset Is Crown Jewel
- Asset Is Publicly Accessible
- Asset Owner ID

This report helps confirm whether asset tagging is complete and aligned with your organization's classification and ownership requirements.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/enabling-email-notifications-failures","lastmod":"2026-02-17T06:06Z","nid":"1529806"} -->
## Enabling Email Notifications for Failures

- Source: https://help.zscaler.com/uvm/enabling-email-notifications-failures
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Enabling Email Notifications for Failures
- Last modified: 2026-02-17T06:06Z
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

<!-- ZS-ARTICLE {"url":"/uvm/generating-saml-details","lastmod":"2025-12-04T20:06Z","nid":"1530692"} -->
## Generating SAML Details

- Source: https://help.zscaler.com/uvm/generating-saml-details
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > Admin Configuration and Deployment > Generating SAML Details
- Last modified: 2025-12-04T20:06Z
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

<!-- ZS-ARTICLE {"url":"/uvm/grouping-data-entity-pages","lastmod":"2025-08-25T22:52Z","nid":"1528096"} -->
## Grouping Data on Entity Pages

- Source: https://help.zscaler.com/uvm/grouping-data-entity-pages
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Grouping Data on Entity Pages
- Last modified: 2025-08-25T22:52Z
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

<!-- ZS-ARTICLE {"url":"/uvm/managing-ai-capabilities-secops-platform","lastmod":"2026-06-29T03:44Z","nid":"1533911"} -->
## Managing AI Capabilities in the SecOps Platform

- Source: https://help.zscaler.com/uvm/managing-ai-capabilities-secops-platform
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > Managing AI Capabilities in the SecOps Platform
- Last modified: 2026-06-29T03:44Z
- Summary: How to manage the availability of AI capabilities in the SecOps Platform.

AI capabilities within the Security Operations Platform (SecOps Platform) offer users access to AI-powered tools (e.g., [Remediation Copilot](https://help.zscaler.com/uvm/what-remediation-copilot), [Mapping Copilot](https://help.zscaler.com/uvm/what-mapping-copilot)). Admins can manage the availability of AI capabilities within the platform to control whether these tools are available to users.

To manage AI capabilities:

1. Click the **Profile**menu on the top right of the navigation bar.
2. Select **Account Settings**.
3. In the**AI Capabilities**section, select or deselect **Enable AI Capabilities**to opt in or out of AI features. See image.
4. Click **Save**.

Opting in or out of AI capabilities applies the change at the account level and makes them available or unavailable to all users within the platform.

## Privacy and Consent

AI capabilities within the SecOps Platform process data entirely within Zscaler's secured services and are not used to train the underlying AI models. The data is not transmitted to external third parties and is protected by encryption and strict access controls. Though the system attempts to strip personally identifiable information (PII) before processing, users should exercise caution with sensitive information. By using the AI capabilities, users consent to the processing of the information they provide.

To learn more about Zscaler's privacy practices and AI data handling, see [Acceptable Use Policy](https://help.zscaler.com/legal/acceptable-use-policy).

[Image: Enable AI Features Settings]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-authentications","lastmod":"2025-12-02T01:32Z","nid":"1529503"} -->
## Managing Authentications

- Source: https://help.zscaler.com/uvm/managing-authentications
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Connectors > Authentications > Managing Authentications
- Last modified: 2025-12-02T01:32Z
- Summary: How to manage configured authentications, including editing and deleting authentications.

After [creating an authentication](https://help.zscaler.com/uvm/configuring-authentications), you can manage and monitor it from the Authentications page. This page provides a centralized view of all configured authentications and their usage across data sources and outegrations. To learn more, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources) and [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations).

Authentication is critical to maintaining stable data connections and access control. Manage authentications cautiously to avoid service disruptions, failed data ingestion, or unintended access issues.

[Image: The authentications page displaying the table of authentications with their details.]

For each authentication, you can see:

- **Authentication Name**: The name of the authentication.
- **Vendor**: The name of the third-party vendor for which the authentication was created.
- **In Use**: The number of data Sources or Outegrations using the authentication. Hovering over the value displays additional details on the specific sources and outegrations that are using the authentication.
- **Created By**: The user who created the authentication.
- **Last Update**: The time and date when the authentication was last updated.
- **Updated By**: The user who last updated the authentication.

You can modify or remove authentications as needed directly from the Authentications page (Configure > Authentications).

For access to Authentications, your assigned role must include the Read, Create, Edit, and Delete permissions under the Platform - Authentications resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users). 
See image.

- Edit Authentications
- Delete Authentications

You can edit an authentication to update expired credentials, adjust access permissions for new integrations, or rename it for easier identification.

To edit an authentication:

1. Hover over the authentication you want to modify, and click the **Edit**icon.
2. Update the necessary details.
3. Click **Save**to apply changes to the authentication.

If the authentication is currently in use by sources or outegrations, a confirmation message appears to ensure you're aware of the impact your changes might have.

- Click **Continue** to apply the changes to the existing authentication.
- Click **Save as New**to create a new authentication instance while preserving the original.

See image.

You can delete an authentication that is no longer in use. This might be the case if the source or outegration it was created for now uses a different authentication, or if your organization no longer works with the associated vendor.

Authentications currently in use by data sources or outegrations cannot be deleted. If your goal is to stop data ingestion from a third-party vendor, consider deactivating the relevant data source instead. To learn more, see [Managing Data Sources](https://help.zscaler.com/uvm/managing-data-sources).

To delete a specific authentication:

1. Hover over the authentication you want to delete, and click the **Delete**icon. A warning message appears. See image.
2. Click **Delete**.

To delete multiple authentications:

1. Select the checkboxes for the authentications that you want to delete.
2. Click the **Delete** button at the top of the page. A warning message appears.
3. Click **Delete**.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-custom-dashboards","lastmod":"2026-07-27T07:37Z","nid":"1527891"} -->
## Managing Custom Dashboards

- Source: https://help.zscaler.com/uvm/managing-custom-dashboards
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Dashboards > Managing Custom Dashboards
- Last modified: 2026-07-27T07:37Z
- Summary: How to manage existing dashboards, including editing the dashboard, pinning it to an app, and accessing pinned dashboards.

You can view and manage the dashboards that you have access to. To view dashboards, go to Explore > Dashboards.

When managing dashboards, you can perform the following actions:

- Edit a Dashboard
- Edit a Dashboard's Details
- Pin a Dashboard to an App
- Access Pinned Dashboards

To edit a dashboard:

1. Go to **Explore** > **Dashboards**.
2. Hover over the dashboard and click the **Edit** icon.
3. Make your changes to the dashboard widgets, and click **Save**.

You can edit the dashboard's details, including the dashboard name, access permissions, and the apps to which it's pinned.

There are two ways to access the dashboard details window:

- On the **Dashboards** page, hover over the dashboard and click the **Edit Details** icon.
- At the top of the dashboard, click the **Edit Dashboard**icon, then click the **Edit Dashboard Details**icon next to the dashboard's name.

After updating the dashboard's details, click **Save**to apply the changes.

For quick and easy access to your dashboards, you can pin them to an app. Pinned dashboards are listed on the app's My Dashboards page.

To pin a dashboard to an app:

1. Go to the dashboard details window using one of the following options: The dashboard details window appears.
  - On the **Dashboards** page, hover over the dashboard you want to pin and click the **Edit Details** icon.
  - At the top of the dashboard, click the **Edit Dashboard**icon, then click the **Edit Dashboard Details** icon next to the dashboard's name.
2. In the **Pin to Apps**drop-down menu, select the apps that you want to pin to the dashboard. See image.

To access a pinned dashboard in the app:

1. Go to the app where the dashboard is pinned (e.g., **Vulnerabilities**).
2. In the left-side navigation, click **My Dashboards**. You can view the full list of the dashboards pinned to this app. See image.

[Image: My Dashboards page in Vulnerabilities]
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

<!-- ZS-ARTICLE {"url":"/uvm/managing-system-and-custom-roles","lastmod":"2025-10-07T21:06Z","nid":"1530679"} -->
## Managing System and Custom Roles

- Source: https://help.zscaler.com/uvm/managing-system-and-custom-roles
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > User Management > Managing System and Custom Roles
- Last modified: 2025-10-07T21:06Z
- Summary: How to manage role permissions, including system roles and custom roles.

You can manage and monitor roles from the Roles page, where you'll find a centralized view of all configured system roles and custom roles in your account. To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles) and [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles). To assign roles to users, see [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users).

To manage roles, go to **Account Settings** > **Permissions** > **Roles** to access the list of existing roles.

When managing roles, you can perform the following actions:

- Edit Users in the Role
- Duplicate a Role
- Edit a Custom Role
- Delete a Role

You can edit users assigned a role to add or remove access permissions defined by the role. To learn more, see [Assigning Roles to Users](https://help.zscaler.com/uvm/assigning-roles-users).

You can duplicate an existing role, including system roles or custom roles, to create a custom role with similar configuration.

The system Admin role can't be duplicated.

To duplicate a role:

1. Hover over the role you want to duplicate, and click the **Duplicate**icon. The role is duplicated and appears in the list of roles. It has the same name as the original role from which it was duplicated, followed by (copy).
2. Edit the duplicated role as needed, including updating the role's name.
3. Click **Save**.

You can edit a custom role to update its name or the resources it grants access to.

System roles can't be edited.

To edit a role:

1. Hover over the role you want to edit, and click the **Edit** icon. The **Edit Role** page appears.
2. On the **Edit Role** page, make the necessary changes to the role.
3. Click **Save**.

You can delete a custom role that is no longer needed.

System roles can't be deleted.

To delete a specific role, hover over the role you want to delete and click the **Delete** icon.

To delete multiple roles:

1. From the roles list, select the roles that you want to delete.
2. Click the **Delete** button at the top of the page. The role is deleted immediately.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-table-columns","lastmod":"2026-07-22T14:32Z","nid":"1529239"} -->
## Managing Table Columns

- Source: https://help.zscaler.com/uvm/managing-table-columns
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Managing Table Columns
- Last modified: 2026-07-22T14:32Z
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

<!-- ZS-ARTICLE {"url":"/uvm/managing-user-roles","lastmod":"2026-03-09T03:53Z","nid":"1530652"} -->
## Managing User Roles

- Source: https://help.zscaler.com/uvm/managing-user-roles
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > User Management > Managing User Roles
- Last modified: 2026-03-09T03:53Z
- Summary: How to assign and manage user roles.

User roles control access to features and actions within the platform. After [creating users](https://help.zscaler.com/uvm/creating-managing-users) in your account, you can assign roles to define their access and permissions within the system.

You can assign either system roles or custom roles:

- System roles are predefined by the platform and cannot be modified. These roles are maintained by the system and are updated automatically when new features are introduced. To learn more, see [Understanding System Roles](https://help.zscaler.com/uvm/understanding-system-roles).
- Custom roles are created and managed by the account admin. These are roles configured specifically in your account to meet organizational requirements. When new system features are introduced, related permissions must be added to custom roles manually. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-managing-custom-roles).

When managing roles, you can perform the following actions:

- Assign Roles to Users
- Edit Roles
- Remove Roles

You can assign a role to users to grant them specific permissions and access within the system.

To assign a role to users:

1. Click the **Profile** menu on the top right of the page.
2. Select **Account Settings**.
3. On the left-hand navigation, go to **Permissions**> **Roles**.
4. Hover over the role and click the **Edit Users** icon (). The **Edit Role** page appears with a list of all users that are assigned the role.
5. On the **Edit Role** page, click **Add** **Users**. The **Add Users** window appears.
6. In the **Add Users** window, select the users you want to assign the role to, then click **Add**.
7. Click **Save**. Assigned roles are applied on the user's next browser refresh.

You can edit a user's role to update their permissions and access, either individually or for multiple users at once.

To edit a single user's role:

1. Click the **Profile** menu on the top right of the page.
2. Go to **Account Settings**> **User Management**.
3. Click the user whose role you want to update.
4. In the **Roles** section, select the role you want to assign from the drop-down menu.
5. Click **Save**.

To edit multiple users' roles:

1. Click the **Profile** menu on the top right of the page.
2. Go to **Account Settings**> **User Management**.
3. Select the users from the list.
4. Click **Edit Role** at the top of the page.
5. Select the role you want to assign to the selected users. Assigned roles are applied on the user's next browser refresh.

You can remove a user from a role to revoke their associated permissions while maintaining their access to the system.

To remove a user from the role:

1. Click the **Profile** menu on the top right of the page.
2. Go to **Account Settings**> **User Management**.
3. Select the user and then click **Remove** **From** **Role**.
4. Click **Save**.

When removing a user's role without assigning them a new role, the user is automatically assigned the default role. While the default role grants no permissions within the account, it preserves the user's access to the system, enabling the option to assign a new role in the future.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/managing-widgets","lastmod":"2026-01-23T06:06Z","nid":"1527886"} -->
## Managing Widgets

- Source: https://help.zscaler.com/uvm/managing-widgets
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Dashboards > Managing Widgets
- Last modified: 2026-01-23T06:06Z
- Summary: How to manage existing widgets, including editing, duplicating, and deleting widgets.

Each custom dashboard in the Zscaler Security Operations (SecOps) platform includes dynamic widgets that can be configured to display relevant measurements and dimensions. You can drag and drop the widgets into a layout that fits your needs. To learn more, see [Configuring Custom Dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards), [Managing Custom Dashboards](https://help.zscaler.com/uvm/managing-custom-dashboards), and [Understanding Measurements & Dimensions](https://help.zscaler.com/uvm/understanding-measurements-dimensions).

After saving a widget, you can edit, duplicate, delete, and rearrange widgets in a dashboard. To view or manage widgets, go to Explore > Dashboards.

When managing widgets, you can perform the following actions:

- Edit a Widget
- Duplicate a Widget
- Delete a Widget
- Adjust Widget Size and Position

Edit widgets to adjust the displayed data, or update their configurations to better fit the purpose of your dashboard.

To edit a widget:

1. Go to **Explore**> **Dashboards**.
2. Hover over the dashboard, and click the **Edit**icon.
3. Hover over the widget you want to edit, and click the **Edit Widget**icon. See image. The widget configuration page appears.
4. Make the necessary adjustments to the appearance, filters, measurements, or dimensions.
5. Click **Save** to apply the changes to the widget. Saving the widget redirects you to the dashboard.
6. Click **Save**to apply the changes to the dashboard.

[Image: Editing a widget]

Duplicate widgets to reuse configurations when you need similar data visualizations on the same dashboard.

To duplicate a widget:

1. Go to **Explore**> **Dashboards**.
2. Hover over the dashboard, and click the **Edit**icon.
3. Hover over the widget you want to edit, and click the **More Options**icon.
4. Click **Duplicate**. The duplicated widget appears on the dashboard.
5. (Optional) Edit the widget as needed and save the changes.
6. Click **Save**to apply the changes to the dashboard.

Delete widgets that are no longer relevant to keep your dashboard up to date.

To delete a widget:

1. Go to **Explore**> **Dashboards**.
2. Hover over the dashboard, and click the **Edit**icon.
3. Hover over the widget you want to edit, and click the **More Options**icon. See image.
4. Click **Delete**. The widget is removed from the dashboard.
5. Click **Save**to apply the changes to the dashboard.

[Image: Deleting a widget]

Resize or move widgets on the dashboard to improve layout usability or emphasize specific data visualizations.

To adjust the size of a widget:

1. Go to **Explore**> **Dashboards**.
2. Hover over the dashboard, and click the **Edit**icon.
3. Use the widget borders to resize it. See image.
4. Click **Save**to apply the changes to the dashboard.

To reposition the widget:

1. Go to **Explore**> **Dashboards**.
2. Hover over the dashboard, and click the **Edit**icon.
3. Click and drag the widget to the desired location.
4. Click **Save**to apply the changes to the dashboard.

[Image: Adjusting widget size and position]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/manually-exporting-reports","lastmod":"2026-07-27T07:37Z","nid":"1529238"} -->
## Manually Exporting Reports

- Source: https://help.zscaler.com/uvm/manually-exporting-reports
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Reports > Manually Exporting Reports
- Last modified: 2026-07-27T07:37Z
- Summary: How to manually download reports and export them from the Reports page.

After [creating a report](https://help.zscaler.com/uvm/creating-reports), you can access the saved report on the Explore > Reports page. From there, you can manually export the report by downloading it directly from the Reports page. Manually downloading reports can be useful for quick reviews, one-time analyses, or sharing data without setting up automation. For details on automated exporting methods, see [Scheduling Reports to Export](https://help.zscaler.com/uvm/scheduling-reports-export) and [Triggering Report Export Through an API](https://help.zscaler.com/uvm/triggering-report-export-through-api).

There are two methods for manually downloading the report:

- Exporting as CSV
- Downloading the Report

You can export reports directly within the report page by clicking the **Export as CSV** icon, located at the top right of the report.

See image.

This method is ideal for smaller datasets. There is a limit of 100K rows; reports with more than 100K rows export only the first 100K. For larger datasets, consider the Download Report File option.

[Image: Export as CSV icon located at the top right of the report]

To export reports with over 100K rows of data, or to export reports in one of the three available formats (CSV, JSONL, EXCEL):

1. Schedule the report, or locate an existing scheduled report.
2. On the **Reports** page, hover over the report and click the **See Runs** icon. See image.
3. Click the latest run.
4. Click **Download Report File**.This downloads the report in the format configured in the scheduled report. See image.

You must run the report at least once before the download option is available. To run the report, hover over the report and click the **Rerun** icon. 
See image.

[Image: See Runs icon when hovering on a report]

**[Image: Download Report File button in the report's expanded run]**

[Image: Rerun icon when hovering on a report]
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

<!-- ZS-ARTICLE {"url":"/uvm/release-upgrade-summary-2026","lastmod":"2026-07-22T10:55Z","nid":"1534299"} -->
## Release Upgrade Summary (2026)

- Source: https://help.zscaler.com/uvm/release-upgrade-summary-2026
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Release Notes > Release Upgrade Summary (2026)
- Last modified: 2026-07-22T10:55Z
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

<!-- ZS-ARTICLE {"url":"/uvm/scheduling-reports-export","lastmod":"2026-06-09T22:44Z","nid":"1529237"} -->
## Scheduling Reports to Export

- Source: https://help.zscaler.com/uvm/scheduling-reports-export
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Reports > Scheduling Reports to Export
- Last modified: 2026-06-09T22:44Z
- Summary: How to schedule reports to be delivered automatically to your email or AWS S3 bucket.

After [creating a report](https://help.zscaler.com/uvm/creating-reports), you can schedule the report to be automatically delivered to a specified email address, Google Cloud Storage, or S3 bucket at regular intervals. This helps automate reporting workflows and ensures timely data delivery without manual effort. You can configure the report format, delivery frequency, recipients, and other delivery details as necessary. For details on additional methods for exporting reports, see [Manually Exporting Reports](https://help.zscaler.com/uvm/manually-exporting-reports) and [Triggering Report Export through an API](https://help.zscaler.com/uvm/triggering-report-export-through-api).

Newly created reports must be saved before they can be scheduled. To create a new report, see [Creating Reports](https://help.zscaler.com/uvm/creating-reports).

To schedule automatic report delivery:

1. Open the desired report and click the **Schedule Export** icon ([Image: Scheduling Reports Icon]) in the top-right corner of the page. The **Schedule Export Details** window appears.
2. In the **Schedule Export Details** window: See image.
  1. **Export Format**: Select the export format (**CSV**, **JSONL**, or **Excel**).
  2. **Active**: Enable to activate the scheduled export.
  3. **Compression**: From the drop-down menu, select a compression option (**None**, **ZIP File**, **ZST File**).
  4. **Delivery Method**: Select the delivery method for the report.
    - **Email**: Enter one or more recipient email addresses (press `Enter` after each entry). Customize the email **Title** and **Message**content.
    - **S3 - AWS S3**: Enter your connection credentials and destination path.
    - **Slack**: Configure delivery settings (i.e., **Title**, **Channel Type**, **Channel**, and **Message**content).
    - **GCP Storage - GCP Storage**: Enter the Google Cloud Storage's bucket name and project ID.
  5. **Frequency and Time**: Set how often and when the report should run (**Daily**, **Weekly**, **Monthly**, or **Custom**).
  6. Click **Save**.

After saving the export settings, save the report to complete the process in one of the following ways:

- Click **Save**to save the report. The report will be exported on schedule.
- From the **Save**drop-down menu, click **Save & Run**to save the report and immediately export it.
- From the **Save**drop-down menu, click **Save As New**to save your changes as a new report.

## Troubleshooting Scheduled Reports

To monitor the progress and status of scheduled reports, on the Reports screen, hover over the relevant report and click the See Runs icon ([Image: See Runs Icon]). The report's Runs page appears, where each row represents an individual report run.

You can customize the displayed columns to add relevant details for troubleshooting. To learn more, see [Managing Table Columns](https://help.zscaler.com/uvm/managing-table-columns).

From the Runs page, you can also hover over a run to [download the generated report file](https://help.zscaler.com/uvm/manually-exporting-reports), if available. Reviewing this file can help you verify the exported data and troubleshoot issues related to report output or configuration.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/supported-browsers-zscaler-secops","lastmod":"2025-08-17T08:38Z","nid":"1527766"} -->
## Supported Browsers for Zscaler SecOps

- Source: https://help.zscaler.com/uvm/supported-browsers-zscaler-secops
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Supported Browsers for Zscaler SecOps
- Last modified: 2025-08-17T08:38Z
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

<!-- ZS-ARTICLE {"url":"/uvm/triggering-asset-scans-uvm","lastmod":"2026-04-11T07:06Z","nid":"1535394"} -->
## Triggering Asset Scans in UVM

- Source: https://help.zscaler.com/uvm/triggering-asset-scans-uvm
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Remediate for UVM > Triggering Asset Scans in UVM
- Last modified: 2026-04-11T07:06Z
- Summary: How to trigger asset scans on a UVM asset.

This feature is in limited availability. To learn more, contact Zscaler Support.

Zscaler Unified Vulnerability Management (UVM) enables you to trigger on-demand asset scans directly from the Zscaler Security Operations (SecOps) platform. By initiating scans immediately rather than waiting for scheduled cycles, you can accelerate validation workflows and instantly verify that patches have been successfully applied to your assets and findings are no longer being detected.

## Prerequisites

Before you can trigger a scan, ensure the following requirements are met:

- The assets you intend to scan must be ingested and managed by a configured data source connector. To learn more, see [Creating Data Sources](https://help.zscaler.com/uvm/creating-data-sources).
- You must have a configured and active scanner outegration to perform the scan. To learn more, see [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations).
- Your assigned role must include the Read and Edit permissions under the Vulnerabilities App - Asset Operational View resource. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles) and [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles).

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

After you trigger a scan, you can monitor its progress and completion status through the activity log for your scanner outegration. The activity log provides real-time updates so you can track each scan from initiation through completion. To learn more, see [Managing Outegrations](https://help.zscaler.com/uvm/managing-outegrations#outegration-activity-log).

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

<!-- ZS-ARTICLE {"url":"/uvm/triggering-report-export-through-api","lastmod":"2025-11-15T23:59Z","nid":"1527711"} -->
## Triggering Report Export Through an API

- Source: https://help.zscaler.com/uvm/triggering-report-export-through-api
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Reports > Triggering Report Export Through an API
- Last modified: 2025-11-15T23:59Z
- Summary: How to trigger a report to export through the Reporting API.

After [creating a report](https://help.zscaler.com/uvm/creating-reports), you can trigger it to be exported programmatically using the Reporting API. For details on additional methods for exporting reports, see [Manually Exporting Reports](https://help.zscaler.com/uvm/manually-exporting-reports) and [Scheduling Reports to Export](https://help.zscaler.com/uvm/scheduling-reports-export).

The Reporting API is a GraphQL-based interface that enables you to export data from the platform to an AWS S3 bucket, or to retrieve a downloadable link to the report data. This article provides a step-by-step guide to using the Reporting API, including example requests for each endpoint.

## Prerequisites

To get started with the Reporting API, you first need to define the data you want to export by creating a new report or using an existing report. In addition to defining the data for export, you need to contact your platform representative or support team to obtain API credentials (i.e., a client ID and client secret).

- Creating a Report
- Obtaining Client Credentials

There are two methods available for retrieving the exported data:

- Exporting the data to your S3 bucket using the [AWS S3 retrieval method](https://help.zscaler.com/uvm/anysource-aws-s3-method).
- Getting a downloadable link to report data.

The steps for using the Reporting API vary slightly depending on the method you choose.

To create a report:

1. Go to **Explore** > **Reports**.
2. Click **New**to create a new report, or hover over an existing report and click the **Edit** icon.
3. Set the report’s **Viewers** access and **Editors** access to **Public**. Only public reports can be accessed through the Reporting API.
4. Configure the report to include the data you want to export.
5. Click **Save** to apply your changes.

### Configuring AWS S3 Export Settings

If you're exporting the report to an S3 bucket, you need to configure the destination settings of the exported report.

To configure the report's destination settings:

1. Click the **Schedule Export** icon located at the top right of the report. See image.
2. In the **Schedule Export Details** window:
  1. **Export Format**: Select the format for the exported report. If you request the report using the pre-signed URL, the scheduling configuration is ignored, and the report is generated in the default CSV format.
  2. **Delivery Method**: Select **S3 - AWS S3**.
  3. **AWS S3 Details**: Enter your connection credentials and destination path. The integration uses role-based authentication. To learn more, see [Connecting AnySource Using AWS S3](https://help.zscaler.com/uvm/connecting-anysource-using-aws-s3#create-a-role-for-the-platform).
  4. **Frequency and Time**: Set how oftenandwhen the report should run.
  5. **Active**: Enable if you want the export to run on a schedule. If you prefer to run the export manually, you can skip the scheduling settings and leave the **Active** toggle disabled.
3. Click **Save** to apply the schedule settings.

[Image: Report's Schedule Export icon on the top right]

The Reporting API uses token-based authentication with client credentials. To authenticate API requests, you must first obtain client credentials (client ID and client secret) from your platform representative or support team.

## Using the API

After completing the prerequisites, you can begin using the Reporting API. Start by authenticating your application—use the client ID and client secret you obtained earlier to request an access token. When authenticated, you can submit API requests to initiate a report and poll for its status.

- Generating a Token
- Making the API Request

Using your client ID and client secret, access the `oauth2/token` endpoint to generate a token. The token is valid for one hour only.

Insert the following details in the script below:

- `URL`
  - For US: `https://auth.us01.app.avalor.io/oauth2/token`
  - For EU: `https://auth.eu.app.avalor.io/oauth2/token`
- `client ID`
- `client secret`

To generate a token, run the following command:

```
curl --location '
<URL>
' \
--header 'accept: application/json' \
--header 'content-type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=
client_credentials
' \
--data-urlencode 'client_id=
<client ID>
' \
--data-urlencode 'client_secret=
<client secret>
'
```

This API uses GraphQL as its query language to execute requests and retrieve data.

To use the API, review and prepare the following details:

- **Required Headers**: Each request must include the following headers:
  - **Authorization**: The access token obtained in the Generating a Token step.
  - **accountId**: Your account ID found in the URL of your platform instance (i.e., `https://app.io/``<Account ID>``/platform`).
- **Report ID**: The report ID from the report editing page URL (i.e., `.../explore/reports/edit/``<Report ID>`).
- **GraphQL API Endpoint**:
  - For US: `https://api.us01.app.avalor.io/api/graphql`
  - For EU: `https://api.eu.app.avalor.io/api/graphql`

### API Endpoints

The following API endpoints allow you to interact with the report processing system. Use these endpoints to initiate report processing, check the status of a report, and optionally retrieve a downloadable link to the report.

- processReport
- getReportRunStatus

Run a report by providing a report ID.

Insert the following details in the script below:

- `URL`
  - For US: `https://api.us01.app.avalor.io/api/graphql`
  - For EU: `https://api.eu.app.avalor.io/api/graphql`
- `account ID`
- `bearer token`
- `report ID`

```
curl --location '
<URL>
' \\
--header 'Content-Type: application/json' \\
--header 'accountId:
<Account ID>
' \\
--header 'graphqlname: processReport' \\
--header 'Authorization: Bearer
<Bearer Token>
' \\
--data '{"query":"mutation ($id: String!) {processReport(id:$id,
isPresignedUrl: true
)}","variables":{"id":"
<Report ID>
"}}'
```

The `isPresignedUrl` parameter is only required if you want to generate a downloadable link for the report. If you configured the report to export to an S3 bucket, you can omit this parameter. The report that is generated with this method is exported in the format configured in the report scheduling settings.

Response fields include the `runId` of the execution to be used in the `getReportRunStatus` endpoint.

Retrieve the status of the specific report run. If you include the `isPresignedUrl` parameter, this endpoint also returns the downloadable link for the report data.

Insert the following details in the script below:

- `URL`
  - For US: `https://api.us01.app.avalor.io/api/graphql`
  - For EU: `https://api.eu.app.avalor.io/api/graphql`
- `account ID`
- `bearer token`
- `report ID`
- `run ID`: Found in the response to the `processReport` request.

```
curl --location '
<URL>
' \
--header 'Content-Type: application/json' \
--header 'accountId:
<Account ID>
' \
--header 'graphqlname: getReportRunStatus' \
--header 'Authorization: Bearer
<Bearer Token>
' \
--data '{"query":"query ($reportId: String, $runId: String) { getReportRunStatus(reportId: $reportId, runId: $runId) }","variables":{"reportId":"
<report ID>
", "runId":"
<Run ID>
"}}'
```

Response fields include:

- The current `status` of the report run (e.g., **Running,** **Completed**, **Failed**).
- If the report was exported to an S3 bucket, the `resultFilePath` field is returned with the S3 path where the file was saved, including the file name.
- If the `isPresignedUrl` was included in the `processReport` request, the `presignedUrl` field returns the downloadable link of the report.
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

<!-- ZS-ARTICLE {"url":"/uvm/understanding-dashboard-widget-types","lastmod":"2026-02-17T06:06Z","nid":"1527816"} -->
## Understanding Dashboard Widget Types

- Source: https://help.zscaler.com/uvm/understanding-dashboard-widget-types
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Dashboards > Understanding Dashboard Widget Types
- Last modified: 2026-02-17T06:06Z
- Summary: Information about widget types for presenting data in custom dashboards.

When adding widgets to your [custom dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards), you can choose a widget type that is appropriate for presenting the measurements and dimensions of the data you want to view. To learn more, see [Understanding Measurements & Dimensions](https://help.zscaler.com/uvm/understanding-measurements-dimensions).

See image.

The following widget types are available for creating custom dashboards:

- Bar
- Line
- Pie
- Table
- Tile

Bar widgets are effective for visualizing data and comparing measurement values across different dimensions.

Bar widgets include the following variations:

- Vertical and horizontal bars
- Vertical and horizontal stacked bars
- Vertical and horizontal 100% stacked bars

In a standard bar chart, the height (for vertical bars) or length (for horizontal bars) represents the quantity of the measurement. For stacked or 100% stacked bar charts, the height of each segment within the bar represents the quantity of a subcategory, allowing you to analyze how subcategories contribute to the overall total. This makes stacked charts useful for showing detailed breakdowns within categories.

The choice between horizontal and vertical orientation, as well as the use of stacking, depends on the type of data and the questions being addressed.

## Configuration Requirements

When building bar widgets, you must include:

- Dimensions: 1 to 2
- Measurements: 1 to 5

## Example Bar Widget

The following image shows the number of Total Tickets by Ticket Severity, where each bar represents a severity (Critical, High, Medium). Using stacked bars, you can display the total tickets assigned to each team, with the segments within each bar representing ticket severities. The x-axis represents the teams assigned to resolve the tickets, and the height of each bar indicates the total number of tickets for that team.

See image.

**[Image: Total Tickets by Ticket Severity Bar Widget]**

Line widgets are effective for visualizing data trends and progression over time. They enable you to display changes in a continuous data set for tracking historical patterns, correlations, or behaviors.

Line widgets include the following variations:

- Line charts (straight segments)
- Spline charts (smooth continuous curves)

In a line chart, the x-axis typically represents the dimension of time, and the y-axis represents the measurement. Each line connects a set of data points, highlighting trends or patterns. While classic line charts are best for discrete data with distinct changes between points, spline charts are ideal for continuous data, emphasizing smooth trends and gradual progression. Multiple lines can be displayed on the same chart to allow for comparisons across multiple dimensions.

Line widgets are especially effective for displaying historical data. They also allow the use of the Breakdown By feature (e.g., Day, Week, Month) for further granularity, which can be set during configuration. To learn more, see [Understanding Historical Data](https://help.zscaler.com/uvm/understanding-historical-data) and [Using Filters](https://help.zscaler.com/uvm/using-filters).

## Configuration Requirements

When building line widgets, you must include:

- Dimensions: 1 The Date Breakdown setting in historical data (i.e., Day, Week, Month) is not mandatory, but counts as a dimension.
- Measurements: 1 to 5

## Example Line Widget

The following image shows the trend of Open Tickets by Severity over 3 months. The x-axis shows time broken down by month, with each line representing a severity level (Critical, High, Medium), and the y-axis shows the number of Open Tickets for each severity level within the selected time frame.

See image.

[Image: Open Tickets by Severity Over Time Spline Widget]

Pie widgets are designed to visualize proportions of a measurement within a dimension, showing how individual components contribute to the total. They represent data in a circular format, where the chart is split into slices, with each slice representing a category's proportion of a whole.

Pie widgets include the following variations:

- Pie chart (standard)
- Donut chart

Pie charts are most effective with a small number of categories per dimension. If the data includes too many categories or similar values, the visualization can become cluttered or difficult to interpret. For datasets with larger or more complex categories, consider alternative chart types (e.g., bar or stacked bar charts).

## Configuration Requirements

When building pie widgets, you must include:

- Dimensions: 0 to 1
- Measurements: 1

## Example Pie Widget

The following image shows the proportion of Total Open Tickets by Asset Type, where each slice represents an Asset Type. The size of the slice corresponds to the number of open tickets for that asset type. This highlights which asset types require the most attention and helps prioritize security efforts accordingly.

See image.

[Image: Total Open Tickets by Asset Type Pie Widget]

Table widgets display data in a structured grid format using rows and columns. They are effective for showcasing detailed datasets, representing non-numerical data, combining multiple dimensions and measurements, and presenting precise numerical values.

Table widgets include the following variations:

- Table (standard)
- Pivot table

While the standard table displays data in a simple grid, where every row represents a unique combination of measurements and dimensions, a pivot table allows you to arrange data by assigning one dimension to rows and another to columns, and filling the intersections with the measurement values.

In addition to functioning as standalone visualizations, all other widget types can be previewed as a table by enabling Table View on the top right of the widget in configuration mode, or by clicking the Table View icon on a saved widget.

## Configuration Requirements

Standard table widgets can include any number of measurements and dimensions.

When building pivot table widgets, you must include:

- Row Dimensions: 1
- Column Dimensions: 1
- Measurements: 0 to 5

## Example Table Widget

Examples of table widgets include:

- Displaying active CVEs in a standard table, with columns such as CVE Name, Impact Severity, Description, Affected Asset, and Resolution Status. See image.
- Displaying tickets by severity and status, where the row dimension is set to Ticket Severity (i.e., Critical, High, Medium, Low), the column dimension is set to Ticket Status (e.g., Opened, Remediated, Closed), and the measurements display Total Active Findings (i.e., the total number of active findings in the ticket) and Active Findings (i.e., the percentage of active findings as a proportion of the total findings). See image.

[Image: CVE Description Table Widget]

[Image: Widgets Pivot Table Example]

Tile widgets display a single data point or measurement. They are designed to act as key performance indicator (KPI) highlights, providing teams with quick, high-level visibility into critical metrics.

## Tile

The standard tile widget displays a single measurement of interest (i.e., a single data point), such as counts, percentages, or totals.

For example, you can create a tile widget to display measurements such as Total Critical Tickets or Tickets Over SLA. These measurements calculate the total number of tickets that meet their respective conditions (e.g., tickets marked as critical or tickets exceeding SLA thresholds), and the resulting count is displayed in the tile widget.

See image.

## Trend

The trend widget is used to display a measurement with its directional trend over time, showing how the value has changed or progressed. The widget includes both the data point and a trend indicator (e.g., arrows or percentage change).

Trend widgets are only available when displaying historical data.

For example, you can use a trend widget to track the percentage change in Total Active Findings over the last week, displaying the current count alongside the percentage increase, such as 120 Active Findings (+15%). Similarly, a trend widget can be used to show the reduction in open tickets over the past month, with the count and percentage decrease displayed, such as 70 Open Tickets (↓-10%). This format provides not only the key measurement but also context on how it has changed over time.

## Text

Text widgets can add context or annotations to complement visualizations. They are useful for adding descriptions or instructions for the data displayed on the dashboard, or for embedding small snippets of code or images for additional clarity.

## Configuration Requirements

When building tile and trend widgets, you must include:

- Dimensions: None (tile widgets cannot include dimensions)
- Measurements: Exactly 1

Text widgets are manually configured using free text input (e.g., context descriptions, goals), links, code, or images.

[Image: Tile Widgets for Tickets]

The following table summarizes dimension and measurement specifications per widget type:

| Widget Type | Allowed Dimensions | Allowed Measurements | Common Use |
| --- | --- | --- | --- |
| Bar | 1 to 2 | 1 to 5 | Comparative analysis across dimensions |
| Line | 1 | 1 to 5 | Time-based trends and progression |
| Pie | 0 to 1 | 1 | Proportional distribution of data |
| Table | No limit | No limit | Detailed tabular data |
| Pivot Table | 1 row dimension 1 column dimension | 0 to 5 | Detailed tabular data |
| Tile and Trend | 0 | 1 | Highlighting single metrics or KPIs |

[Image: Creating Custom Dashboard Widgets Types]
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

<!-- ZS-ARTICLE {"url":"/uvm/understanding-historical-data","lastmod":"2026-02-17T06:06Z","nid":"1528151"} -->
## Understanding Historical Data

- Source: https://help.zscaler.com/uvm/understanding-historical-data
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Understanding Historical Data
- Last modified: 2026-02-17T06:06Z
- Summary: Information about historical data in the Zscaler SecOps platform and how it's calculated.

The Zscaler Security Operations (SecOps) platform captures the historical state of each entity at every point in time, allowing flexible reporting of over-time behavior based on change logs saved in the platform. You can filter this historical data to detail your performance in different time frames. This enables you to monitor trends, verify the impact of configuration changes, and track progress on security and operational initiatives over time.

Historical data is available in dashboards and reports across the platform. Charts and reports that display historical data are marked with the Historical Data icon ([Image: Historical data icon]). The historical view has two controls: time granularity and a time filter.

Time granularity sets the level at which data is broken down in a chart or report (month, week, or day). For example, when granularity is set to Week, measurements are grouped by the selected dimensions and by week (e.g., Assignee and Week).

See image.

The time filter in the date selector sets the date range for the displayed data. While time granularity controls how the data is bucketed, the time filter controls the scope of the data included. For example, if the time filter is set to Last 3 Months, data outside that range is not displayed. With time granularity, the data within that 3-month period is broken down by day, week, or month.

See image.

The time filter always takes precedence over granularity. For example, if the date range selected is Last 3 Days and the granularity is Month, the Month bucket includes only those 3 days that fall within the selected range, and not the entire month. The same applies to any partial period: when the selected range covers only part of a week or month, the corresponding week or month bucket includes only the days within the selected range.

This article explains different capabilities and nuances of historical analytics across different pages on the platform.

[Image: Table displaying historical data of ticket SLA by week and assignee]

[Image: The date selector set to display historical data of the last three months by week]

## Historical Data in System Dashboards

System dashboards displaying historical data have a date selector at the top right of the page (e.g., the Remediation History dashboard in UVM and the Policy Compliance dashboard in AEM). The date selector allows users to:

- Adjust the date range to filter the scope of displayed data.
- Specify the data granularity—day, week, or month—to adjust how the historical data is broken down within the selected date range.

See image.

Historical system dashboards can include widgets displaying current data. To distinguish between these widgets, those representing historical data are marked with the Historical Data icon.

Other system dashboards do not have a date selector to filter historical data, but they can still include specific widgets to display this data. These widgets explicitly state the set historical filter applied and are similarly marked with the Historical Data icon.

See image.

[Image: The Policy Compliance page displaying compliance percentages over time and date selector to filter historical data]

[Image: Historical data widgets on the Risk page in the Vulnerabilities app]

## Historical Data in Custom Dashboards

By default, widgets in custom dashboards display current data. When creating custom dashboards, you can also create historical widgets to visualize over-time trends and performance. To learn more, see [Configuring Custom Dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards).

When configuring historical widgets, in addition to selecting the measurements and dimensions that determine the type of data and how it is grouped or categorized, you need to set a time filter to define the date range of the displayed data (e.g., Last 30 Days, Last 6 Months).

You can also set the time granularity (i.e., the date breakdown) as a dimension. This allows you to break the historical data into day, week, or month intervals for more granular insights.

Historical widgets in custom dashboards are also marked with the Historical Data icon to help distinguish them from widgets displaying current data. When a widget is configured for historical data, certain measurements and dimensions that are not supported in historical views might not be available.

## Historical Data in Reports

By default, reports display current data. You can configure reports that display historical data by enabling History mode in the date selector.

When configuring historical reports, in addition to selecting the measurements and dimensions that determine the type of data and how it is grouped or categorized, you need to set a time filter to define the date range of the displayed data (e.g., Last 30 Days, Last 6 Months).

You can also set the time granularity (i.e., the date breakdown) as a dimension. This allows you to break the historical data into day, week, or month intervals for more granular insights.

When configuring historical reports, certain measurements and dimensions that are not supported in historical views might not be available.

## Understanding Measurements in Historical Data

To maximize the value of historical data, it is essential to understand the different types of measurements available and how they behave. Each measurement type is calculated differently and, as a result, reflects different information about the entity. Additionally, not all measurements are supported in the historical data view.

This section explains the 4 key types of measurements and uses an example of Ticket ID 123 to demonstrate how each type behaves over time.

Ticket ID 123 progresses through various statuses across three months:

- January: Ticket 123 is opened, remediated, and subsequently reopened by the end of the month.
- February: The ticket turns from open to remediated, and it is closed before the end of the month.
- March: The ticket is closed.

Based on the progression of Ticket ID 123, different measurement types capture its historical data accordingly.

[Image: Historical Data Example]

### Types of Measurement in Historical Data

Different measurements behave in distinct ways when applied to historical data. These behaviors depend on how the data is calculated and what aspects of the entities or conditions the measurement is focusing on.

There are 4 key types of measurements:

#### Was at Measurements

Was at measurements count entities when they meet a condition at any point during the selected time range, regardless of whether they later changed. For example, the Total Open Tickets measurement counts all tickets that were open at any point during the time range set in the historical date selector, including those subsequently closed. Similarly, Total Closed Tickets counts all tickets marked as closed at any time during the period.

The following table shows the output of Ticket ID 123:

| Measurement | January | February | March |
| --- | --- | --- | --- |
| **Total Open Tickets** | 1 | 1 | 0 |
| **Total Closed Tickets** | 0 | 1 | 1 |

#### Last at Measurements

Last at measurements reflect whether the entity met the measurement's condition on the last day of the specified time range. These measurements provide a snapshot view of conditions as they were at the end of the period.

For example, the Active Findings measurement counts the number of findings still active on the final day of the time range. Similarly, the Max Finding Severity Score measurement captures the highest severity score observed on the last day of the period.

The following table shows the output data if Ticket ID 123 started off with 300 active findings, but by the end of January only had 25 active findings, and the highest severity score of the findings is 8.8:

| Measurement | January | February | March |
| --- | --- | --- | --- |
| **Active Findings** | 25 | 0 | 0 |
| **Max Finding Severity Score** | 8.8 | - | - |

#### Turn to Measurements

Turn to measurements count entities that transitioned to a specific condition during a given time period. These metrics highlight the transition or the change that occurred during the period. For example, the Opened Tickets measurement counts tickets first opened during the time range, the Remediated Tickets measurement counts tickets that were remediated (moved to the Remediated status) during the period, and the Closed Tickets measurement counts tickets that were closed during the period.

The following table shows the measurement output of Ticket ID 123:

| Measurement | January | February | March |
| --- | --- | --- | --- |
| **Opened Tickets** | 1 | 0 | 0 |
| **Remediated Tickets** | 1 | 1 | 0 |
| **Closed Tickets** | 0 | 1 | 0 |

#### Mean time to (MTTX) Measurements

Mean time to (MTTX) measurements calculate the average number of days it takes for an entity to transition from one condition to another. For example, Ticket Mean Time to Remediate measures the average duration (in days) it takes for a ticket to transition from the Active to the Inactive state. The key difference between current and historical data for MTTX measurements is that in current data, only the most recent remediation period (the time from when the ticket is open to when it's remediated) is considered, while in historical data, every remediation period within the time range is captured, displaying separate values for each.

The following table shows the output data if the first remediation period for Ticket ID 123 lasted 20 days in January, and the second remediation period lasted 15 days in February:

| Measurement | January | February | March |
| --- | --- | --- | --- |
| **Ticket Mean Time to Remediate** | 10 | 15 | - |

## Understanding Dimensions in Historical Data

Dimensions in historical data allow you to categorize and group measurements based on specific attributes, such as assignee, team, or status, helping you track shifts and transitions over time.

To illustrate how dimensions behave and interact with measurements in historical data, consider again the following example of Ticket ID 123:

[Image: Historical data example with assignee]

Ticket ID 123 progresses through various statuses across three months:

- January: Ticket 123 is opened, remediated, and subsequently reopened by the end of the month.
- February: The ticket turns from open to remediated, and it is closed before the end of the month.
- March: The ticket is closed.

Additionally in February, Ticket ID 123 changed assignee from Team 1 to Team 5.

When viewing the historical data for this ticket and adding the Assignee dimension to the table, the data is broken down by both assignees—Team 1 and Team 5—and the respective measurements are displayed for each, as shown in the following table:

|  | January | February | March |  |  |  |
| --- | --- | --- | --- | --- | --- | --- |
| Assignee | Total Open Tickets | Closed Tickets | Total Open Tickets | Closed Tickets | Total Open Tickets | Closed Tickets |
| **Team 1** | 1 | 0 | 1 | 0 | 0 | 0 |
| **Team 5** | 0 | 0 | 1 | 1 | 0 | 0 |

Adding the Assignee dimension creates another level of granularity in addition to the time granularity, so the selected measurements are further broken down by the selected dimension:

- Total Open Tickets (Was at): As a Was at measurement, the Total Open Tickets measurement represents all tickets that were open at any point during the time period, regardless of their status at the end of that period. For Team 1, the ticket is counted during its assignment to them in January and February. For Team 5, it is counted during its reassignment in February.
- Closed Tickets (Turn to): As a Turn to measurement, Closed Tickets represent tickets that transitioned into the Closed status during the time period. For Team 1, no tickets were closed while assigned to them. In contrast, after being reassigned to Team 5 in February, the ticket transitioned to Closed and is therefore counted once in January. Although it was still Closed in March, it did not turn to Closed in March, and is thus not counted for Team 5 in that month.

Let's assume Ticket ID 123 started with 300 active findings at the beginning of January. In that month, Team 1 remediated all but 25 findings. In February, Team 1 remediated an additional 12 findings before the ticket was reassigned to Team 5, at which point the remaining findings were remediated, and the ticket was closed. The output of the data is as follows:

| Measurement | January | February | March |
| --- | --- | --- | --- |
| Assignee | Active Findings | Active Findings | Active Findings |
| **Team 1** | 25 | 13 | 0 |
| **Team 5** | 0 | 0 | 0 |

Since Active Findings is a Last at measurement, it reflects the last recorded state of findings for each dimension and time interval:

- Team 1: In January, the ticket ended the month with 25 active findings still assigned to Team 1. In February, this number was reduced to 13 active findings on the last day the ticket was assigned to them. By March, Team 1 no longer had data for this ticket, as it had been reassigned.
- Team 5: After the ticket was reassigned in February, no findings remained active by the time Team 5 assumed responsibility for the ticket. Therefore, Active Findings = 0 for Team 5 in both February and March.

In other words, for January, the Active Findings measurement displays the findings still active on the final day of the month under Team 1. For February, Active Findings displays the remaining findings on the last day that the ticket was assigned to Team 1. For Team 5, the findings had already been resolved by the end of February, resulting in 0 Active Findings at the team's final state in February.

## Applying Filters to Historical Data

When applying filters to historical data, the platform evaluates entities based on their current state to determine if they meet the filter criteria, and only entities that match the filter condition in their current state have their historical data displayed. This means that even if an entity matched the filter at some point in the past, it is not included in the results unless it still matches the filter criteria today.

[Image: Historical data example with assignee]

Using the Ticket ID 123 example that progresses through various statuses across three months:

- January: Ticket 123 is opened, remediated, and subsequently reopened by the end of the month. The ticket is assigned to Team 1.
- February: The ticket turns from open to remediated, and it is closed before the end of the month. The ticket is reassigned to Team 5.
- March: The ticket is closed. The ticket remains assigned to Team 5.

We want to view all tickets in the month of January only, so we apply the filter Assignee = Team 1 to view tickets assigned to Team 1.

Here's how the filter behaves:

- Ticket ID 123: Was assigned to Team 1 in January, but is now assigned to Team 5.
- Ticket ID 456: Is currently assigned to Team 1 and has been throughout the selected time range.

With the filter Assignee = Team 1:

- Ticket ID 123 will not appear in the results, even though it was assigned to Team 1 in January, because it is no longer assigned to Team 1 today.
- Ticket ID 456 will appear, and all of its historical January data will be displayed because it is currently assigned to Team 1.

## Technical Considerations

The platform stores historical data in a separate dataset designed to capture all changes made to entities over time. This dataset is distinct from the one used for current data, which only retains the most recent version of the data. While the historical dataset enables detailed insights and flexible over-time analysis, it comes with the following considerations:

- Retrieving historical data can result in slower performance compared to current data. This is because the platform processes and queries large volumes of data to ensure all the relevant changes, transitions, and activity are included. Performance slowdowns are most noticeable when analyzing extended time ranges or applying detailed filters and dimensions.
- Historical data is updated in periodic processing intervals, typically every 4 hours. As a result, new changes might not appear in the historical view until the next processing cycle. This can lead to temporary discrepancies between historical data and current data, which is updated in real time.
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-measurements-dimensions","lastmod":"2025-09-04T02:28Z","nid":"1529098"} -->
## Understanding Measurements & Dimensions

- Source: https://help.zscaler.com/uvm/understanding-measurements-dimensions
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Understanding Measurements & Dimensions
- Last modified: 2025-09-04T02:28Z
- Summary: Information on measurements and dimensions and examples of each used in the SecOps platform and its apps (e.g., UVM, AEM).

The Zscaler SecOps platform's analytic capabilities enable you to view and [filter](https://help.zscaler.com/uvm/using-filters) data by measurements and dimensions.

- Measurements
- Dimensions

A measurement is a quantitative field that represents a calculated value. Common calculation types include sums, averages, and maximums. Measurements are typically used to track or assess numerical aspects of the data. Measurements are calculated for the dataset currently in view and are updated dynamically based on the dimensions and filters applied.

Examples of measurements include:

- Total Undetected Findings: The number of findings in a ticket that have not been detected, and are thus considered remediated.
- Ticket Mean Time to Remediate: The average time it takes to remediate tickets.

A dimension is a categorical field used to group and segment data. When a dimension is added, the measurements are organized according to the unique values of that dimension. Examples of dimensions include Asset Type, Source, and Severity Level.

Using dimensions, you can explore how a measurement varies across its different categories. For example, viewing the Total Tickets measurement by Severity Level can help you understand how ticket volume is distributed across severity categories.

Adding multiple dimensions increases the granularity of the analysis. For example, adding Ticket Assignee as a dimension along with the % Active Findings measurement shows the percentage of active findings per assignee. Adding Ticket Severity as an additional dimension recalculates the same measurement for each unique combination of Ticket Assignee and Ticket Severity.

See image.

Measurements and dimensions are entity specific and dynamically displayed based on the selected Main Entity type, which also determines which records are included in the results. For example, when Ticket is the main entity, only assets linked to tickets are displayed. In contrast, selecting Asset as the main entity shows all assets, including those without any associated tickets or findings (such as assets imported from a CMDB).

Additionally, when searching for measurements, the timeframe of the element you're configuring (i.e., current versus historical) can affect the available measurements. For example, the % Vulnerable Assets measurement is only available when viewing historical data.

## Navigating Measurements and Dimensions

Measurements and dimensions are used across the SecOps platform to configure and adjust the displayed data (e.g., when applying [filters](https://help.zscaler.com/uvm/using-filters) in [reports](https://help.zscaler.com/uvm/creating-reports) and [custom dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards), or when [creating saved views](https://help.zscaler.com/uvm/creating-managing-saved-views)).

Dimensions are grouped by entity type (e.g., Ticket, Asset), and measurements appear at the bottom of the dimensions list.

In measurement and dimension lists, you can adjust the displayed fields:

- Click the **Measurements**icon to view the list of available measurements.
- Click the **Dimensions**icon to view the list of available dimensions.
- Click **In Use**to display the fields currently in use.
- Use the search bar to locate specific fields.

See image.

[Image: an example of adding a dimension]

[Image: measurements and dimensions filters]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-severity-scores","lastmod":"2026-07-15T21:06Z","nid":"1527926"} -->
## Understanding Severity Scores

- Source: https://help.zscaler.com/uvm/understanding-severity-scores
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Settings for UVM > Understanding Severity Scores
- Last modified: 2026-07-15T21:06Z
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

<!-- ZS-ARTICLE {"url":"/uvm/understanding-system-roles","lastmod":"2026-05-22T07:06Z","nid":"1530653"} -->
## Understanding System Roles

- Source: https://help.zscaler.com/uvm/understanding-system-roles
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Administration > Account Management > User Management > Understanding System Roles
- Last modified: 2026-05-22T07:06Z
- Summary: Information about the different system roles in the Zscaler SecOps platform.

System roles are predefined, built-in roles that grant users specific permissions through established access levels. These roles simplify user management by providing a consistent way to assign the necessary privileges for users to perform their tasks while maintaining the platform's security and operational integrity.

The set of available system roles depends on the applications installed within the platform (e.g., UVM, AEM, SOC Workbench), with each application offering roles specific to its features and workflows. To assign and manage roles, see [Managing User Roles](https://help.zscaler.com/uvm/managing-user-roles) and [Managing System and Custom Roles](https://help.zscaler.com/uvm/managing-system-and-custom-roles).

System roles are ideal for standard use cases where common permission sets suffice. When your access requirements extend beyond the system roles, you can create custom roles and configure the access granted to users assigned to those roles. To learn more, see [Creating Custom Roles](https://help.zscaler.com/uvm/creating-custom-roles).

The following system roles apply per application:

- Unified Vulnerability Management (UVM)
- Asset Exposure Management (AEM)
- SOC Workbench

To view the specific permissions assigned to a role, click the role name on the Roles page. This opens the role's matrix, where you can view the detailed actions the role can perform. To learn more, see [Managing System and Custom Roles](https://help.zscaler.com/uvm/managing-system-and-custom-roles).

The following table details the different system roles for the UVM app and the actions each role allows users to perform:

| **Role** | **Permissions** |
| --- | --- |
| Admin | Manage data model entities and their fields; View and manage data source mapping; View, create, and manage data sources; View, create, and manage user-saved views across the platform; View and manage outegrations; View and search logs; View and manage custom dashboards and reports; View and manage built-in vulnerability dashboard and analytics; View and manage tickets; View and manage ticket scoring, grouping rules, and life-cycle customization; View and manage findings; View and manage assets; Trigger third-party outegration from tickets |
| Vulnerabilities Admin | View all built-in dashboards; Manage and view custom dashboards and reports; Create and configure integrations; Run search queries; View, create, and edit tickets (split and merge); Edit and view ticket settings; View assets and findings |
| Vulnerabilities Editor | View all built-in dashboards; Manage and view custom dashboards and reports; Run search queries; View, create, and edit tickets (split and merge); View ticket settings; View assets and findings |
| Vulnerabilities Reader | View all built-in dashboards; View custom reports and dashboards; View tickets and ticket settings; View findings; View tickets |

The following table details the different system roles for the AEM app and the actions each role allows users to perform:

| **Role** | **Permissions** |
| --- | --- |
| Admin | Manage data model entities and their fields; View and manage data source mapping; View, create, and manage data sources; View, create, and manage user-saved views across the platform; View and manage outegrations; View and search logs; View and manage custom dashboards and reports; View and manage built-in dashboards and analytics; View and manage violation tickets; View and manage violation ticket scoring, grouping rules, and life-cycle customization; View and manage policy violations; View and manage assets; Trigger third-party outegration from violation tickets |
| Assets Admin | View all built-in dashboards; Manage and view custom dashboards and reports; Create and configure outegrations; Run search queries; View, create, and edit violation tickets (split and merge); Edit and view violation ticket settings; View assets and policy violations |
| Assets Editor | View all built-in dashboards; Manage and view custom dashboards and reports; Run search queries; View, create, and edit violation tickets (split and merge); View violation ticket settings; View assets and policy violations |
| Assets Reader | View all built-in dashboards; View custom reports and dashboards; View violation tickets and violation ticket settings; View policy violations; View violation tickets |

The following table details the different system roles for the SOC Workbench app and the actions each role allows users to perform:

| **Role** | **Permissions** |
| --- | --- |
| Admin | Manage data model entities and their fields; Trigger third-party outegration from incidents; View and manage assets; View and manage custom dashboards and reports; View and manage data source mapping; View and manage alerts; View and manage incidents; View and manage outegrations; View and manage incident scoring, grouping rules, and life-cycle customization; View and search logs; View built-in threats dashboard and analytics; View, create, and manage data sources; View, create, and manage user-saved views across the platform; View and manage users |
| SOC Workbench Admin | View and manage alerts; View and manage assets; View and manage custom dashboards and reports; View and manage data source mapping; View and manage incident settings; View and manage incidents; View and manage outegrations; View and search logs; View built-in threats dashboard and analytics; View, create, and manage data sources; View and manage users |
| SOC Workbench Analyst | View and manage alerts; View assets; View and manage incident settings; View and manage incidents; View and search logs; View built-in threats dashboard and analytics; View custom dashboards and reports; View data sources and data source mapping; View outegrations; View users |
| SOC Workbench Compliance | View alerts; View assets; View and manage custom dashboards and reports; View and search logs; View built-in threats dashboard and analytics; View data sources and data source mapping; View incident settings; View incidents; View outegrations; View users |
| SOC Workbench Manager | View and manage alerts; View assets; View and manage incident settings; View and manage incidents; View and search logs; View built-in threats dashboard and analytics; View custom dashboards and reports; View data sources and data source mapping; View outegrations; View users |
| SOC Workbench Security Engineer | View and manage alerts; View assets; View and manage data source mapping; View and manage incident settings; View and manage incidents; View and manage outegrations; View and search logs; View built-in threats dashboard and analytics; View custom dashboards and reports; View, create, and manage data sources; View users |
| SOC Workbench Viewer | View alerts; View assets; View and search logs; View built-in threats dashboard and analytics; View custom dashboards and reports; View data sources and data source mapping; View incident settings; View incidents; View outegrations; View users |
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/understanding-uvm-measurement-terminology","lastmod":"2025-12-22T06:06Z","nid":"1533637"} -->
## Understanding UVM Measurement Terminology

- Source: https://help.zscaler.com/uvm/understanding-uvm-measurement-terminology
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Understanding UVM Measurement Terminology
- Last modified: 2025-12-22T06:06Z
- Summary: Describes built-in measurements used in the Zscaler SecOps platform's dashboards and reports.

This article describes built-in measurements used in the Zscaler Security Operations (SecOps) platform's dashboards and reports. These measurements depend on the states and statuses of tickets. To learn more, see [About Tickets](https://help.zscaler.com/uvm/about-tickets).

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

<!-- ZS-ARTICLE {"url":"/uvm/using-dashboard-templates","lastmod":"2026-07-29T05:00Z","nid":"1527881"} -->
## Using Dashboard Templates

- Source: https://help.zscaler.com/uvm/using-dashboard-templates
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Dashboards > Using Dashboard Templates
- Last modified: 2026-07-29T05:00Z
- Summary: How to use dashboard templates to create a custom dashboard.

When [configuring custom dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards), you can save time and effort by starting with preconfigured system dashboards from the template gallery. These templates include preconfigured widgets designed to address common use cases, which you can customize to meet your specific requirements.

To access the dashboard template gallery:

1. In the SecOps Platform Admin Portal, go to **Explore**> **Dashboards**. See image.
2. Click the **Template Gallery** tab. A list of all available templates appears. You can view the template name, description, and associated app (e.g., **Vulnerabilities**, **Assets**).
3. Click a template to open it and view its components.

To create a custom version of a template:

1. In the SecOps Platform Admin Portal, go to **Explore**> **Dashboards**.
2. Click the **Template Gallery** tab.
3. In the **Template Gallery**, locate the template you want to copy, and choose one of the following options:
  - Edit an existing template.
  - Duplicate an existing template.

To learn more, see [Configuring Custom Dashboards](https://help.zscaler.com/uvm/configuring-custom-dashboards), [Managing Custom Dashboards](https://help.zscaler.com/uvm/managing-custom-dashboards), and [Managing Widgets](https://help.zscaler.com/uvm/managing-widgets).

[Image: All Dashboards page displaying list of all dashboards created across apps]

1. Click the template you want to edit.
2. Click the **Edit Dashboard**icon. See image.
3. Make changes to the dashboard widgets as necessary.
4. Click **Save As**. The **Save as a New Dashboard**window appears.
5. In the **Save as a New Dashboard** window:
  1. **Name**: Edit the dashboard's name.
  2. **Description**: (Optional) Edit the existing description, or enter a new description.
  3. **Viewers**: Set the dashboard's viewer access. To set the dashboard as private and only visible to you, leave the **Viewers**drop-down menu blank.
    - Select **Public**to grant view access to all users in the account.
    - Select specific users from the list to set them as viewers of the dashboard.
  4. **Editors**: Set the dashboard's editor access. To set the dashboard as private and editable only by you, leave the **Editors**drop-down menu blank.
    - Select **Public**to grant edit access to all users in the account.
    - Select specific users from the list to set them as editors of the dashboard. Users designated as editors override the **Viewers**setting.
  5. **Pin to Apps**: (Optional) Select the application that you want to pin the dashboard to. This allows you to easily find the dashboard under **My Dashboards**in the app.
6. Click **Save**. The modified dashboard appears on the **My Dashboards**tab.

Use one of the following methods to duplicate a template:

- Select the checkbox next to the template, then click **Duplicate**above the table.
- Hover over the template, and click the **Duplicate Dashboard**icon.

Duplicating a template creates a new dashboard entry on the **My Dashboards**tab. You can customize the duplicated dashboard as necessary.

[Image: Edit Dashboard Icon]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/using-entity-explorer","lastmod":"2026-07-17T12:21Z","nid":"1527851"} -->
## Using the Entity Explorer

- Source: https://help.zscaler.com/uvm/using-entity-explorer
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Data Exploration > Using the Entity Explorer
- Last modified: 2026-07-17T12:21Z
- Summary: How to use Entity Explorer to view and investigate data in the SecOps platform.

Entity Explorer in the Zscaler Security Operations (SecOps) platform allows you to display all relevant data by entity, where you can explore and inspect it. Unlike an entity-specific view (e.g., [Tickets](https://help.zscaler.com/uvm/about-tickets), [Findings](https://help.zscaler.com/uvm/about-findings-operational-view-uvm), [Assets](https://help.zscaler.com/uvm/about-assets-operational-view-uvm)) which displays active components, Entity Explorer displays all relevant information, both active and inactive.

## Viewing Entity Data

To view entity data:

1. In the SecOps platform, go to **Explore** > **Entities**.
2. Select the entity type for which you want to view data. See image. You can change the fields displayed in the table, apply filters, and create custom views according to your needs, similar to the other entity views.
3. To export the data as a CSV file, click the **Export As CSV** icon. See image.

[Image: Select an entity type]

[Image: Export entity data as a CSV]

## Investigating the Data

Entity Explorer allows you to investigate the relationships between your source data and the uploaded data enriched by the SecOps platform.

To enable Investigate mode, click **Investigate**in the top-right corner of the page. The table displays source fields next to the platform fields.

See image.

[Image: Investigate mode on the Entity Explorer page]
<!-- /ZS-ARTICLE -->

---

<!-- ZS-ARTICLE {"url":"/uvm/using-filters","lastmod":"2025-08-25T22:51Z","nid":"1528106"} -->
## Using Filters

- Source: https://help.zscaler.com/uvm/using-filters
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Using Filters
- Last modified: 2025-08-25T22:51Z
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

<!-- ZS-ARTICLE {"url":"/uvm/using-queries-library","lastmod":"2026-01-23T06:06Z","nid":"1527831"} -->
## Using the Queries Library

- Source: https://help.zscaler.com/uvm/using-queries-library
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Data Exploration > Using the Queries Library
- Last modified: 2026-01-23T06:06Z
- Summary: How to use and manage queries in the Queries Library.

The Queries Library allows you to save and later access multiple queries when viewing and analyzing your [logs](https://help.zscaler.com/uvm/building-queries-searching-logs).

To access the Queries Library:

1. In the Zscaler Security Operations (SecOps) platform, go to**Explore**> **Logs**.
2. Click **Library**. The **Queries Library** appears. See image.
3. You can view the following information for each query:
  - **Name**: The name of the query.
  - **Query Type**: Whether the query was built using the basic or advanced query builder.
  - **Category**: The category of the query, if assigned.
  - **Description**: The description of the query.
  - **Updated At**: The latest date that the query was saved.
  - **Updated By**: The last person to update the query.

[Image: Queries Library Button]

## Managing Saved Queries

After [saving a query](https://help.zscaler.com/uvm/building-queries-searching-logs), you can manage it from the Queries Library. Click a query in the list to expand and view its contents.

See image.

When managing saved queries, you can perform the following actions:

- Categorize Saved Queries
- Edit Saved Queries
- Delete Saved Queries

[Image: Expanded Query in Query Library]

You can organize saved queries by assigning them categories.

To categorize saved queries:

1. Go to**Explore**> **Logs**.
2. Click **Library**. The **Queries Library**window appears.
3. Select the queries you want to categorize from the list.
4. Click **Category**.
5. Select a category from the menu to assign it to the selected queries. The category is assigned immediately.

You can edit queries to update their settings or adjust their configurations.

To edit a saved query:

1. Go to**Explore**> **Logs**.
2. Click **Library**. The **Queries Library**window appears.
3. Choose one of the following methods:
  - Hover over the query, and click the **Edit**icon.
  - Select the query from the list, and click **Edit**on the toolbar.
4. Make the necessary changes to the query.
5. Save the query in one of the following ways:
  - Click **Save**to override the saved query configuration with the edited version.
  - From the **Save**drop-down menu, click **Save As New**to save your changes as a new query.

You can delete saved queries that you no longer use.

Deleting a query doesn't trigger a warning message and deletes the query immediately.

To delete saved queries:

1. Go to**Explore**> **Logs**.
2. Click **Library**. The **Queries Library**window appears.
3. Choose one of the following methods: The query is deleted and removed from the library.
  - To delete a single query, hover over the query, and click the **Delete**icon.
  - To delete multiple queries, select the checkboxes of the queries you want to delete, and click **Delete**on the toolbar.

## Managing Query Categories

You can categorize queries saved in the Queries Library to maintain an organized and easily accessible list.

When managing query categories, you can perform the following actions:

- Add a Category
- Edit a Category
- Delete a Category

You can create categories to organize your saved queries for easier management.

To create a new category for saved queries:

1. Go to**Explore**> **Logs**.
2. Click **Library**. The **Queries Library**window appears.
3. Click **New Category**. An untitled category is added to the left-side navigation. See image.
4. Enter a name for the category and click outside the input field. The category can now be assigned to queries.

[Image: New Category button in the Queries Library]

You can edit query category names to keep them relevant and descriptive.

To edit a category name:

1. Go to**Explore**> **Logs**.
2. Click **Library**. The **Queries Library**window appears.
3. In the left-side navigation, hover over the query category that you want to edit, and click the **Edit**icon.
4. Edit the query name and click outside the input field.

You can delete categories to remove unused or redundant query categories.

To delete a category:

1. Go to**Explore**> **Logs**.
2. Click **Library**. The **Queries Library**window appears.
3. In the left-side navigation, hover over the query category that you want to delete, and click the **Delete**icon. The **Delete**window appears.
4. Select a deletion option: See image.
  - **Delete only the folder**: Delete the category, but not the queries saved to it.
  - **Delete all**: Delete the selected category and all queries saved to it.
5. Click **Delete**.

[Image: Delete Queries window]
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

<!-- ZS-ARTICLE {"url":"/uvm/viewing-audit-logs","lastmod":"2026-05-23T07:06Z","nid":"1539932"} -->
## Viewing Audit Logs

- Source: https://help.zscaler.com/uvm/viewing-audit-logs
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Getting Started > Admin Portal > Viewing Audit Logs
- Last modified: 2026-05-23T07:06Z
- Summary: How to view and export audit logs in the Zscaler Security Operations platform.

Audit logs track user-initiated actions within the Zscaler Security Operations (SecOps) platform. This enables you to monitor configuration changes, such as updates to reports, data source instances, and outegrations. You can download specific audit logs for immediate review, and schedule automated exports to an external destination such as an Amazon S3 bucket.

Audit logs track key events including:

- Configuration of grouping rules and rule sets.
- Configuration of severity, score, and SLA settings.
- Creating, managing, and assigning roles and content permissions.
- Configuring field unification rule set.
- Configuring UI configurations.
- Updates to account settings and user settings.
- Management of data source mapping, data source instances, and data source scheduling.
- Configuration and mapping of outegrations.
- Changes to dashboards and reports.

## Viewing the Audit Logs Page

To view the Audit Logs page:

1. Go to a configuration page to: The configuration page for a user is provided here as an example. See image.
  - For a user: Click the profile menu in the top navigation bar, then click **Profile Settings**.
  - For an account: Click the profile menu in the top navigation bar, then click **Account Settings**.
  - For a source: Go to **Configure** > **Sources**, then click a source.
  - For an outegration: Go to **Configure**> **Outegrations**, then click an outegration.
  - For a report: Go to an app (**Vulnerabilities**, **Assets**, etc.), click **My Reports**, and click a report.
2. Click the **More**menu, and click **Audit Logs**. The **Audit Logs** page appears.
3. On the **Audit Logs** page for a user, you can do the following: See image.
  - Filter and sort the logs by operation, type, and user name.
  - Download the logs as a CSV file.
  - View additional information by clicking the arrow icon. See image.

Audit logs are retained for 90 days. To extend the retention period and to maintain a long-term history, you can export the logs to an Amazon S3 bucket or any other configured log destination.

[Image: Configuration Page of a User]

## Configuring Audit Log Export

You can schedule audit logs to be sent on a daily or hourly basis via an S3 outegration.

To configure automated audit log export for a user:

1. Click the profile menu in the top navigation bar, then click **Account Settings**. The **Settings**page appears.
2. Click the **Audit Logs Export** drop-down menu. See image.
3. Select **Enable Scheduling**, then select the following options: See image.
  1. **Included Entities**: Select the entity types to be included in the audit log (e.g., **Severity Score Settings**, **UI Configuration**).
  2. **Frequency**: Define the time interval for the audit log export (e.g., **Daily**or **Hourly**).
  3. **Select S3 Outegration**: Select **AWS S3**. To create an AWS S3 outegration, see [Creating Outegrations](https://help.zscaler.com/uvm/creating-outegrations).
4. Click **Save**. Audit logs are automatically exported to the designated AWS S3 bucket based on the defined scheduling frequency.

[Image: Audit Logs Page]

[Image: Additional Details]

[Image: Account Settings Page]

[Image: Audit Logs Export Drop-Down Menu]
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

<!-- ZS-ARTICLE {"url":"/uvm/viewing-items-widget-segment","lastmod":"2026-01-08T07:36Z","nid":"1529159"} -->
## Viewing Items in a Widget Segment

- Source: https://help.zscaler.com/uvm/viewing-items-widget-segment
- Product: Unified Vulnerability Management (UVM)
- Path: Unified Vulnerability Management (UVM) Help > Analytics > Dashboards > Viewing Items in a Widget Segment
- Last modified: 2026-01-08T07:36Z
- Summary: How to view and configure the display for specific items in dashboard widgets.

You can view detailed data associated with a specific segment in a widget (e.g., a slice of a pie chart, or a bar in a bar chart) by clicking the segment. Viewing a segment's items opens a table view that displays the data for the selected segment.

Viewing items is distinct from widget drilldown, which enables users to explore additional dimensions set in the widget configuration. Viewing items displays the underlying data for a specific segment of a widget, while drilling down displays additional dimensions that are configured in the drilldown hierarchy. For example, a drilldown on open tickets can further break down the data by severity or ticket status.

Clicking a segment of a widget displays the items that make up that segment. For example, in a pie chart showing open tickets by asset type, clicking the Linux segment opens a table listing all tickets assigned to Linux.

See image.

If you configure drilldown in the widget when [adding widgets to the dashboard](https://help.zscaler.com/uvm/configuring-custom-dashboards#adding-widgets-to-dashboards), an Interactions menu appears, allowing you to either click through to a table detailing the individual items that make up the measurement's value or to drill down into additional dimensions.

## Customizing and Saving the View Items Table

When you click a segment to view its items, the initial columns displayed in the table that appears are those defined in the entity's default saved view in the relevant app (e.g., [Tickets](https://help.zscaler.com/uvm/about-tickets) in UVM, [Violation Tickets](https://help.zscaler.com/uvm/about-violation-tickets) in AEM). The default view is indicated by the Set as Account Default View icon in the saved view's drop-down menu on the entity's page.

See image.

You can customize the displayed columns and save the view, making it accessible in the saved views drop-down menu across all widgets and dashboards referencing the same entity. For example, the Total Assets and the Vulnerable Assets measurements share the same list of saved views. To learn more, see [Creating & Managing Saved Views](https://help.zscaler.com/uvm/creating-managing-saved-views#create-new-view).

[Image: Widget View Items in a Widget Linux Example]

[Image: Default Entity View]

## Exporting the Table

To export the data shown in the table as a CSV file, click the Export as CSV icon located in the top-right corner of the table.

See image.

The data is exported based on the currently displayed columns.

[Image: Export as CSV Icon]
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
